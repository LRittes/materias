import Text.Parsec
import Text.Parsec.Expr
import qualified Text.Parsec.Token as T
import Text.Parsec.Language
import System.IO
import Control.Monad (unless, when)


type Id = String

data Tipo = TDouble | TInt | TString | TVoid deriving (Eq, Show) --

data TCons = CDouble Double | CInt Integer deriving Show

data Expr = Expr :+: Expr | Expr :-: Expr | Expr :*: Expr | Expr :/: Expr | Neg Expr | 
    Const TCons | IdVar String | Chamada Id [Expr] | Lit String | IntDouble Expr | DoubleInt Expr  deriving Show

data ExprR = Expr :==: Expr | Expr :/=: Expr | Expr :<: Expr | Expr :>: Expr | Expr :<=: 
    Expr | Expr :>=: Expr deriving Show

data ExprL = ExprL :&: ExprL | ExprL :|: ExprL | Not ExprL | Rel ExprR deriving Show

data Var = Id :#: Tipo deriving Show --

data Funcao = Id :->: ([Var], Tipo) deriving Show  --

data Programa = Prog [Funcao] [(Id, [Var], Bloco)] [Var] Bloco deriving Show --

type Bloco = [Comando]

data Comando = If ExprL Bloco Bloco | While ExprL Bloco | Atrib Id Expr | Leitura Id | Imp 
    Expr | Ret (Maybe Expr) | Proc Id [Expr] deriving Show



exprR = do {
         e1 <- expr;
         o <- opR;
         e2 <- expr;
         return (Rel (o e1 e2))
 }

opR =   do { reservedOp "=="; return (:==:)}
    <|> do { reservedOp "/="; return (:/=:)}
    <|> do { reservedOp "<="; return (:<=:)}
    <|> do { reservedOp ">="; return (:>=:)}
    <|> do { reservedOp "<"; return (:<:)}
    <|> do { reservedOp ">"; return (:>:)}

comando :: Parsec String u Comando
comando = do {  reservedNames "return";
                e<-expr;
                semi;
                return (Ret (Just e)); }
            <|> try ( do { 
                        id <- identifier;
                        symbol "=";
                        e <- expr;
                        symbol ";";           
                        return (Atrib id e)})
            <|> do { reservedNames "if"; l <- parens exprL; b1<-bloco; b2<-senao; return (If l b1 b2) }
            <|> do { reservedNames "while"; l <- parens exprL; b <- bloco; return (While l b) }
            <|> do { reservedNames "print"; e <- parens expr; symbol ";"; return (Imp e) }
            <|> do { reservedNames "read"; id <- parens identifier; symbol ";"; return (Leitura id) }
            <|> do { id <- identifier; lp <- parens listaParans; symbol ";"; return (Proc id lp)}

listaCmd = do {
        c <- comando;
        cs <- listaCmd;
        return (c : cs);
    }
    <|> return [];

bloco = do {
        braces listaCmd;
    }

senao = do {
           reservedNames "else";
           bloco;
        }
        <|> return [];

-- data Var = Id :#: Tipo deriving Show

defineTipo :: Parsec String u Tipo
defineTipo =   do { reservedOp "int"; return TInt}
            <|> do { reservedOp "double"; return TDouble}
            <|> do { reservedOp "string"; return TString}

tipoRetorno :: Parsec String u Tipo
tipoRetorno = try (do {
          defineTipo;
        })
        <|> do {reservedNames "void"; return TVoid}

var = do {
    t <- defineTipo;
    n <- identifier;
    return (n :#: t);
}


listaParans :: Parsec String u [Expr]
listaParans = listaParans'
            <|> return [];

listaParans' :: Parsec String u [Expr]
listaParans' = do {
        e <- expr;
        l <- listaParans'';
        return (e:l);
        }

listaParans'' :: Parsec String u [Expr]
listaParans'' = do {
            symbol ",";
            listaParans';
            }
            <|> return [];

montaVarsTipo _ [] = []
montaVarsTipo t (n:ln) = (n :#: t) : montaVarsTipo t ln

varBlocoPrincipal = do {
    t <- defineTipo;
    n <- commaSep1 identifier;
    semi;
    return (montaVarsTipo t n)
}

blocoPrincipal' = do {
    lv <- many varBlocoPrincipal;
    lc <- listaCmd;
    return (concat lv, lc)
}

blocoPrincipal = do {
    braces blocoPrincipal';
}

-- data Funcao = Id :->: ([Var], Tipo) deriving Show
funcao = do {
    t <- tipoRetorno;
    n <- identifier;
    lv <- parens (commaSep var);
    return (n :->: (lv, t))
}

funcaoEBloco = do {
            f <- funcao;
            lfv <- blocoPrincipal;
            lfb <- funcaoEBloco;
            return ((f,lfv) : lfb);
        }
        <|> return [];


funcaoId (id :->: _) = id
funcaoVar (_ :->: (vars, _)) = vars

retornaFuncao [] = []
retornaFuncao ((f, _):t) = f : retornaFuncao t

somaVars [] ys = ys
somaVars (x:xs) ys = x:somaVars ys xs

retornaIdVarBloco :: [(Funcao, ([Var], Bloco))] -> [(Id, [Var], Bloco)]
retornaIdVarBloco [] = []
retornaIdVarBloco ((f,(lv, b)):t) = (funcaoId f, somaVars (funcaoVar f) lv, b) : retornaIdVarBloco t

retornaListaVarDeBlocoPrincipal (lv, _) = lv
retornaBlocoDeBlocoPrincipal (_, b) = b

-- data Programa = Prog [Funcao] [(Id, [Var], Bloco)] [Var] Bloco deriving Show
programa = do
            lfb <- funcaoEBloco;
            let lf = retornaFuncao lfb;
            let idVarBloco = retornaIdVarBloco lfb;
            b <- blocoPrincipal;
            return (Prog lf idVarBloco (retornaListaVarDeBlocoPrincipal b) (retornaBlocoDeBlocoPrincipal b));

lingDef = emptyDef {
        T.commentStart = "{-"
    ,   T.commentEnd = "-}"
    ,   T.commentLine = "--"
    ,   T.identStart = letter <|> char '_'
    ,   T.identLetter = alphaNum <|> char '_'
    ,   T.reservedOpNames = ["+", "-", "/", "*", "&&", "||", "!", "<", ">", "<=", ">=", "==", "/=", "="]
    ,   T.reservedNames = ["while", "return", "if", "else", "print", "read"]
}

lexico = T.makeTokenParser lingDef

natural = T.natural lexico
symbol = T.symbol lexico
parens = T.parens lexico
reservedOp = T.reservedOp lexico
identifier = T.identifier lexico
reservedNames = T.reserved lexico
semi = T.semi lexico
commaSep = T.commaSep lexico
float = T.float lexico
stringLiteral = T.stringLiteral lexico
braces = T.braces lexico
commaSep1 = T.commaSep1 lexico

tabela = [
        [prefix "-" Neg],
        [binario "*" (:*:) AssocLeft, binario "/" (:/:) AssocLeft ],
        [binario "+" (:+:) AssocLeft, binario "-" (:-:) AssocLeft ]
    ]

tabelaL = [
        [prefix "!" Not],
        [binario "&&" (:&:) AssocLeft ],
        [binario "||" (:|:) AssocLeft ]
    ]

binario name fun assoc = Infix (do {reservedOp name; return fun }) assoc
prefix name fun = Prefix (do {reservedOp name; return fun })

exprL = buildExpressionParser tabelaL exprR
    <?> "expression"

expr = buildExpressionParser tabela fator
    <?> "expression"

fator = parens expr
    <|> try (do {
         n <- identifier;
         le<- parens (commaSep expr);
         return (Chamada n le)})
    <|> try (do {n <- float; return (Const (CDouble n))})
    <|> do {n <- natural; return (Const (CInt n))}
    <|> do {n <- stringLiteral; return (Lit n)}
    <|> do {n <- identifier; return (IdVar n)}
    <?> "simple expression"

partida :: Parsec String u Programa
partida = do {e <- programa; eof; return e}

parserE e = runParser partida [] "Expressoes" e


printSemantica' p = if errors == "" then do print seman
                    else do putStrLn errors 
                           -- print (snd p)
                  where
                    (errors, seman) = p

printSemantica p = do let sem = semantica p
                      case sem of
                        MS p -> printSemantica' p

parserExpr s = case parserE s of
    Left er -> print er
    Right v -> printSemantica v

main = do {
    handle <- openFile "teste1.j--" ReadMode;
    e <- hGetContents handle;
    parserExpr e}


------------------------------------------------------------------------


newtype Semantica a = MS (String, a) deriving Show

instance Functor Semantica where
         fmap f (MS (s, a)) = MS (s, f a)

instance Applicative Semantica where
    pure x = MS ("", x)
    MS(s1, f) <*> MS(s2, x) = MS (s1 <> s2, f x)

instance Monad Semantica where
    MS(s, a) >>= f = let MS(s', b) = f a in MS (s++s', b)

erro s = MS ("Error - "   ++ s ++ "\n", ())
adv s = MS ("Warning - " ++ s ++ "\n", ())

semantica programa@(Prog lFuncao lFuncaoBloco lVars bPrincipal) = do
  bPrincipal1 <- normalizaDouble lFuncao lFuncaoBloco lVars bPrincipal
  lFuncaoBloco1 <- normalizaTipoRetorno lFuncao lFuncaoBloco lFuncao lFuncaoBloco
  bPrincipal3 <- normalizaDoubleR lFuncao lFuncaoBloco1 lVars bPrincipal1
  msgDeErroSo <- verificaTipoIncExprFuncao lFuncao lFuncaoBloco1 lFuncao lFuncaoBloco1
  msgDeAdvSo <- chamaVerificaDoubleIntFuncao lFuncao lFuncaoBloco1 lFuncao lFuncaoBloco1;
  msgDeErroSo <- verificaTipoIncExpr lFuncao lFuncaoBloco1 lVars bPrincipal3
  msgDeAdvSo <- verificaDoubleInt lFuncao lFuncaoBloco1 lVars bPrincipal3 "";
  msgDeErroSo <- verificaExiste lFuncao lFuncaoBloco1 lVars bPrincipal3 "";
  msgDeErroSo <- chamaVerificaExisteFuncao lFuncao lFuncaoBloco1 lFuncao lFuncaoBloco1;
  msgDeErroSo <- verificaSeVarRepete lVars "";
  msgDeErroSo <- verificaSeVarRepeteEmFuncao lFuncaoBloco1;
  msgDeErroSo <- verificaSeFuncaoRepete lFuncao;
  return (Prog lFuncao lFuncaoBloco1 lVars bPrincipal3)



verificaParametros [] _ _ _ = return []
verificaParametros (elem:xs) [] listaVars listaFuncoes = do {

     transformedRest <- verificaParametros xs [] listaVars listaFuncoes;
     return (elem : transformedRest)
 }
verificaParametros (elem:xs) ((_:#:tipo):tipos) listaVars listaFuncoes = do {
    transformedElem <- if tipo==TDouble then
        transformaDouble elem listaVars listaFuncoes
    else if tipo==TInt then
        transformaInt elem listaVars listaFuncoes elem
    else
        return elem;
    transformedRest <- verificaParametros xs tipos listaVars listaFuncoes;
    return (transformedElem : transformedRest)
}

transformaDouble (e1 :+: e2) listaVars listaFuncoes = do
                                                    transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                    transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                    return (transformedE1 :+: transformedE2)
transformaDouble (e1 :-: e2) listaVars listaFuncoes = do
                                                    transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                    transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                    return (transformedE1 :-: transformedE2)
transformaDouble (e1 :*: e2) listaVars listaFuncoes = do
                                                    transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                    transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                    return (transformedE1 :*: transformedE2)
transformaDouble (e1 :/: e2) listaVars listaFuncoes = do
                                                    transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                    transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                    return (transformedE1 :/: transformedE2)
transformaDouble (Neg e) listaVars listaFuncoes = do
                                                    transformedE1 <- transformaDouble e listaVars listaFuncoes;
                                                    return (Neg e)
transformaDouble e@(Const (CDouble _)) _ _ = return e
transformaDouble e@(Const (CInt _)) _ _ = do {
    return (IntDouble e)
}
transformaDouble e@(Chamada nome lExpr) listaVars listaFuncoes = do {
    transformedLExpr <- verificaParametros lExpr (getTipoParams nome listaFuncoes) listaVars listaFuncoes;
    if verificaSeDeclaracaoFuncaoDouble nome listaFuncoes then do
        return (Chamada nome transformedLExpr);
    else do
        return (IntDouble (Chamada nome transformedLExpr));
}
transformaDouble e@(IdVar nome) listaVars listaFuncoes = do {
    if verificaSeDeclaracaoDouble nome listaVars then
    return e;
    else
    return $ IntDouble e;
    }
transformaDouble e@(Lit str) listaVars listaFuncoes = do {
    return e;
}

transformaInt (e1 :+: e2) listaVars listaFuncoes elemCompleto = do
                                                transformedE1 <- transformaInt e1 listaVars listaFuncoes elemCompleto;
                                                transformedE2 <- transformaInt e2 listaVars listaFuncoes elemCompleto;
                                                return (transformedE1 :+: transformedE2);
transformaInt (e1 :-: e2) listaVars listaFuncoes elemCompleto = do
                                                transformedE1 <- transformaInt e1 listaVars listaFuncoes elemCompleto;
                                                transformedE2 <- transformaInt e2 listaVars listaFuncoes elemCompleto;
                                                return (transformedE1 :-: transformedE2);
transformaInt (e1 :*: e2) listaVars listaFuncoes elemCompleto = do
                                                transformedE1 <- transformaInt e1 listaVars listaFuncoes elemCompleto;
                                                transformedE2 <- transformaInt e2 listaVars listaFuncoes elemCompleto;
                                                return (transformedE1 :*: transformedE2);
transformaInt (e1 :/: e2) listaVars listaFuncoes elemCompleto = do
                                                transformedE1 <- transformaInt e1 listaVars listaFuncoes elemCompleto;
                                                transformedE2 <- transformaInt e2 listaVars listaFuncoes elemCompleto;
                                                return (transformedE1 :/: transformedE2);
transformaInt (Neg e) listaVars listaFuncoes elemCompleto = do
                                                transformedE <- transformaInt e listaVars listaFuncoes elemCompleto;
                                                return (Neg transformedE);
transformaInt e@(Const (CDouble _)) _ _ elemCompleto = do {
        return (DoubleInt e)
     }
transformaInt e@(Const (CInt _)) _ _ elemCompleto = return e
transformaInt e@(Chamada nome lExpr) listaVars listaFuncoes elemCompleto = do {
        transformedLExpr <- verificaParametros lExpr (getTipoParams nome listaFuncoes) listaVars listaFuncoes;
        if verificaSeDeclaracaoFuncaoDouble nome listaFuncoes then do
            return (DoubleInt (Chamada nome transformedLExpr));
        else do
            return (Chamada nome transformedLExpr);
    }
transformaInt e@(IdVar nome) listaVars listaFuncoes elemCompleto = do {
     if verificaSeDeclaracaoDouble nome listaVars then
        return (DoubleInt e);
     else
        return e;
     }
transformaInt e@(Lit str) listaVars listaFuncoes elemCompleto = do {
    return e;
}

normalizaDouble _ _ _ [] = return []
normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Atrib nome e):xs) = do
    if verificaSeDeclaracaoDouble nome declaracaoMain then do
        transformedE <- transformaDouble e declaracaoMain declaracoesFuncao
        normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain xs >>= \rest -> return (Atrib nome transformedE : rest)
    else do
        transformedE <- transformaInt e declaracaoMain declaracoesFuncao e
        normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain xs >>= \rest -> return (Atrib nome transformedE : rest)
normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain (elem:xs) = do
    normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain xs >>= \rest -> return (elem : rest)

chamaVerificaDoubleIntFuncao [] _ _ _ = return []
chamaVerificaDoubleIntFuncao _ [] _ _ = return []
chamaVerificaDoubleIntFuncao (declaracao@(nome :->: _):restoDeclaracao) ((_,_,bloco):restoBloco) declaracoesFuncao blocosFuncoes = do
    verifica <- verificaDoubleInt declaracoesFuncao blocosFuncoes declaracao bloco nome;
    rest <- chamaVerificaDoubleIntFuncao restoDeclaracao restoBloco declaracoesFuncao blocosFuncoes;
    return (verifica : rest);


verificaDoubleInt _ _ _ [] _ = return [];
verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Atrib nome e):xs) onde = do
    temDoubleInt <- verificaSeDoubleInt e declaracoesFuncao blocosFuncoes declaracaoMain;
    if temDoubleInt then do
        traduzidoE <- traduzExpr e;
        if onde == "" then do
            adv ("Double to integer conversion within Main: "++nome++" = "++traduzidoE++";");
        else
            adv ("Conversão de double para inteiro dentro da função "++onde++" em: "++nome++" = "++traduzidoE++";");
        verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde >>= \rest -> return (elem : rest);
    else
        verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde >>= \rest -> return (elem : rest);
verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Ret (Just e)):xs) onde = do
    temDoubleInt <- verificaSeDoubleInt e declaracoesFuncao blocosFuncoes declaracaoMain;
    if temDoubleInt then do
        traduzidoE <- traduzExpr e;
        if onde == "" then do
            adv ("Double to integer conversion within Main: return "++traduzidoE++";");
        else
            adv ("Conversão de double para inteiro dentro da função "++onde++" em: return "++traduzidoE++";");
        verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde >>= \rest -> return (elem : rest);
    else
        verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde >>= \rest -> return (elem : rest);
verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain (elem@(If _ bloco blocoElse):xs) onde = do {
     verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain bloco onde;
     verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain blocoElse onde;
     verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde >>= \rest -> return (elem : rest);
 }
verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain (elem@(While _ bloco):xs) onde = do {
     verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain bloco onde;
     verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde >>= \rest -> return (elem : rest);
 }
verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain (_:xs) onde = do {
    verificaDoubleInt declaracoesFuncao blocosFuncoes declaracaoMain xs onde;
}

repeteFuncao _ [] = return False
repeteFuncao funcao (e@(nomeFuncao :->: _):xs) = if nomeFuncao==funcao then return True else repeteFuncao funcao xs

verificaSeFuncaoRepete [] = return []
verificaSeFuncaoRepete (x@(nomeFuncao :->: _):xs) = do {
    resu <- repeteFuncao nomeFuncao xs;
    when resu (erro ("Function "++nomeFuncao++"declared more than once"));
    rest <- verificaSeFuncaoRepete xs;
    return (x : rest)
}

verificaSeVarRepeteEmFuncao [] = return []
verificaSeVarRepeteEmFuncao (x@(nome, dec, _):funcoes) = do {
    elem <- verificaSeVarRepete dec nome;
    rest <- verificaSeVarRepeteEmFuncao funcoes;
    return (elem:rest)
}

repeteVar _ [] = return False
repeteVar var (e@(nome :#: _):xs) = if nome==var then return True else repeteVar var xs

verificaSeVarRepete [] onde = return []
verificaSeVarRepete (x@(nome :#: _):xs) onde = do
    resu <- repeteVar nome xs
    if resu then do
        if onde=="" then
            erro ("Variable "++nome++" declared more than once in Main")
        else
            erro ("Variable "++nome++" declared more than once in função "++onde)
        rest <- verificaSeVarRepete xs onde
        return (x : rest)
    else do
        rest <- verificaSeVarRepete xs onde
        return (x : rest)

pegaPrimeiroElemFormatado ((IdVar nome):_) = return ("Variable "++nome);
pegaPrimeiroElemFormatado ((Chamada nome _):_) = return ("Function "++nome);

listaVazia [] = return True
listaVazia _ = return False

chamaVerificaExisteFuncao [] _ _ _ = return []
chamaVerificaExisteFuncao _ [] _ _ = return []
chamaVerificaExisteFuncao (declaracao@(nome :->: _):restoDeclaracao) ((_,dec,bloco):restoBloco) declaracoesFuncao blocosFuncoes = do
    verifica <- verificaExiste declaracoesFuncao blocosFuncoes dec bloco nome;
    rest <- chamaVerificaExisteFuncao restoDeclaracao restoBloco declaracoesFuncao blocosFuncoes;
    return (verifica : rest);

verificaExiste _ _ _ [] _ = return []
verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Atrib nome e):xs) onde = do
    varExiste <- verificaSeVarExiste nome declaracaoMain
    if varExiste then do
        listaExprComErro <- verificaSeExisteEmExpr e declaracoesFuncao blocosFuncoes declaracaoMain
        listaEVazia <- listaVazia listaExprComErro
        if not listaEVazia then do
            traduzidoE <- traduzExpr e
            elemNaoExiste <- pegaPrimeiroElemFormatado listaExprComErro
            if onde == "" then
                erro (elemNaoExiste++", not declared within Main in: "++nome++" = "++traduzidoE++";")
            else
                erro (elemNaoExiste++", not declared within fuction "++onde++" in: "++nome++" = "++traduzidoE++";")
            rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
            return (elem : rest)
        else do
            rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
            return (elem : rest)
    else do
        traduzidoE <- traduzExpr e
        if onde == "" then do
            erro ("Variable "++nome++", not declared within Main in: "++nome++" = "++traduzidoE++";")
        else
            erro ("Variable "++nome++", not declared within fuction "++onde++" in: "++nome++" = "++traduzidoE++";")
        rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
        return (elem : rest)
verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Ret (Just e)):xs) onde = do
    listaExprComErro <- verificaSeExisteEmExpr e declaracoesFuncao blocosFuncoes declaracaoMain
    listaEVazia <- listaVazia listaExprComErro
    if not listaEVazia then do
        traduzidoE <- traduzExpr e
        elemNaoExiste <- pegaPrimeiroElemFormatado listaExprComErro
        if onde == "" then
            erro (elemNaoExiste++", not declared within Main in: return "++traduzidoE++";")
        else
            erro (elemNaoExiste++", not declared within fuction "++onde++" in: return "++traduzidoE++";")
        rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
        return (elem : rest)
    else do
        rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
        return (elem : rest)
verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain (elem@(If _ bloco blocoElse):xs) onde = do
    verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain bloco onde;
    verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain blocoElse onde;
    rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
    return (elem : rest)
verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain (elem@(While _ bloco):xs) onde = do
    verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain bloco onde;
    rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
    return (elem : rest)
verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain (elem:xs) onde = do
    rest <- verificaExiste declaracoesFuncao blocosFuncoes declaracaoMain xs onde
    return (elem : rest)

verificaSeVarExiste _ [] = return False
verificaSeVarExiste nome ((nomeVar :#: _):xs) = if nomeVar==nome then return True else verificaSeVarExiste nome xs

verificaSeFuncaoExiste _ [] = return False
verificaSeFuncaoExiste nome ((nomeFuncao :->: _):xs) = if nomeFuncao==nome then return True else verificaSeFuncaoExiste nome xs

verificaSeExisteEmListaExpr [] _ _ _ = return [];
verificaSeExisteEmListaExpr (x:xs) declaracoesFuncao blocosFuncoes declaracaoMain = do {
     transformed <- verificaSeExisteEmExpr x declaracoesFuncao blocosFuncoes declaracaoMain;
     rest <- verificaSeExisteEmListaExpr xs declaracoesFuncao blocosFuncoes declaracaoMain;
     return (transformed ++ rest);
 }

verificaSeExisteEmExpr (e1 :+: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeExisteEmExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeExisteEmExpr e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
verificaSeExisteEmExpr (e1 :-: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeExisteEmExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeExisteEmExpr e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
verificaSeExisteEmExpr (e1 :*: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeExisteEmExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeExisteEmExpr e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
verificaSeExisteEmExpr (e1 :/: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                        transformedE1 <- verificaSeExisteEmExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                        transformedE2 <- verificaSeExisteEmExpr e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                        return (transformedE1 ++ transformedE2);
                                                    }
verificaSeExisteEmExpr (Neg e) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                        verificaSeExisteEmExpr e declaracoesFuncao blocosFuncoes declaracaoMain;
                                                    }
verificaSeExisteEmExpr e@(Const (CDouble _)) _ _ _ = return []
verificaSeExisteEmExpr e@(Const (CInt _)) _ _ _ = return []
verificaSeExisteEmExpr e@(Chamada nome params) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    existeFuncao <- verificaSeFuncaoExiste nome declaracoesFuncao;
    if existeFuncao then do
        verificaSeExisteEmListaExpr params declaracoesFuncao blocosFuncoes declaracaoMain;
    else
        return [e]
}
verificaSeExisteEmExpr e@(IdVar nome) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    varExiste <- verificaSeVarExiste nome declaracaoMain;
    if varExiste then
        return [];
    else
        return [e];
}
verificaSeExisteEmExpr e@(Lit _) _ _ _ = return []
verificaSeExisteEmExpr e@(DoubleInt elem) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    verificaSeExisteEmExpr elem declaracoesFuncao blocosFuncoes declaracaoMain;
 }
verificaSeExisteEmExpr e@(IntDouble elem) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    verificaSeExisteEmExpr elem declaracoesFuncao blocosFuncoes declaracaoMain;
 }

verificaSeDoubleInt (e1 :+: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeDoubleInt e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeDoubleInt e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaSeDoubleInt (e1 :-: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeDoubleInt e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeDoubleInt e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaSeDoubleInt (e1 :*: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeDoubleInt e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeDoubleInt e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaSeDoubleInt (e1 :/: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaSeDoubleInt e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaSeDoubleInt e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaSeDoubleInt (Neg e) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         verificaSeDoubleInt e declaracoesFuncao blocosFuncoes declaracaoMain;
                                                     }
verificaSeDoubleInt e@(Const (CDouble _)) _ _ _ = return False
verificaSeDoubleInt e@(Const (CInt _)) _ _ _ = return False
verificaSeDoubleInt e@(Chamada nome params) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    doubleIntEmParams <- verificaSeDoubleIntEmParams params declaracoesFuncao blocosFuncoes declaracaoMain;
    paramsTraduzido <- traduzExprComoParams params;
    when doubleIntEmParams (erro ("Converting double to integer in function parameters: "++nome++"( "++paramsTraduzido++" )"));
    return False
 }
verificaSeDoubleInt e@(IdVar nome) declaracoesFuncao blocosFuncoes declaracaoMain = do {
     return False
 }
verificaSeDoubleInt e@(Lit _) _ _ _ = return False
verificaSeDoubleInt e@(DoubleInt elem) _ _ _ = do {
     return (True)
 }
verificaSeDoubleInt e _ _ _ = return False

verificaSeDoubleIntEmParams [] _ _ _ = return False
verificaSeDoubleIntEmParams (x:xs) declaracoesFuncao blocosFuncoes declaracaoMain = do {
     traduzido <- verificaSeDoubleInt x declaracoesFuncao blocosFuncoes declaracaoMain;
     rest <- verificaSeDoubleIntEmParams xs declaracoesFuncao blocosFuncoes declaracaoMain;
     return (traduzido || rest)
 }

verificaTipoIncExprFuncao [] [] _ _ = return []
verificaTipoIncExprFuncao (y:ys) ((_,declaracoes,bloco):xs) declaracoesFuncao blocosFuncoes = do {
    elem <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracoes bloco;
    rest <- verificaTipoIncExprFuncao ys xs declaracoesFuncao blocosFuncoes;
    return (elem : rest)
}

verificaTipoIncExpr _ _ _ [] = return []
verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Atrib nome e):xs) = do
    case getTipoVar nome declaracaoMain of
        Just tipo -> do {
             compartivel <- verificaTipoComExpr e tipo declaracoesFuncao blocosFuncoes declaracaoMain;
             if not compartivel then do
                 traduzidoE <- traduzExpr e
                 erro ("Incompatible types in: " ++ nome ++ " = " ++ traduzidoE ++ ";")
                 rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
                 return (elem : rest)
             else do
                 rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
                 return (elem : rest)
             }
        Nothing -> do
            traduzidoE <- traduzExpr e
            erro ("Variable " ++ nome ++ " not defined in: " ++ nome ++ " = " ++ traduzidoE ++ ";")
            rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
            return (elem : rest)
verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain (elem@(If _ bloco blocoElse):xs) = do
    discart1 <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain bloco;
    discart2 <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain blocoElse;
    rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs;
    return (elem : rest);
verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain (elem@(While cExprL bloco):xs) = do
    lista <- getListaTiposExprL cExprL declaracoesFuncao blocosFuncoes declaracaoMain;
    saoCompativeis <- listaTiposCompativel lista;
    if not saoCompativeis then do
        traduzidoE <- traduzExprL cExprL;
        erro ("Incompatible types within while ( "++ traduzidoE++" )")
        rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
        return (elem : rest)
    else do
        rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
        return (elem : rest)
    discart1 <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain bloco;
    rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs;
    return (elem : rest);
verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain (elem@(Ret (Just exp)):xs) = do
            primeiroTipo <- getPriTipoComExpr exp declaracoesFuncao blocosFuncoes declaracaoMain;
            compartivel <- verificaTipoComExpr exp primeiroTipo declaracoesFuncao blocosFuncoes declaracaoMain;
            if not compartivel then do
                traduzidoE <- traduzExpr exp
                erro ("Incompatible types in: return " ++ traduzidoE ++ ";")
                rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
                return (elem : rest)
            else do
                rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs
                return (elem : rest)
verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain (x:xs) = do {
     rest <- verificaTipoIncExpr declaracoesFuncao blocosFuncoes declaracaoMain xs;
     return (x : rest);
}

listaTiposCompativel' tipo [] = return True
listaTiposCompativel' tipo (x:xs) = do {
     if tipo==TDouble || tipo==TInt then
         if x==TString || x==TVoid then
             return False
         else
             listaTiposCompativel' tipo xs
     else if (tipo==TString) then
         if (x==TDouble || x==TInt || x==TVoid) then
             return False
         else
             listaTiposCompativel' tipo xs
     else
         if (x/=TVoid) then
             return False
         else
             listaTiposCompativel' tipo xs
 }

listaTiposCompativel [] = return True
listaTiposCompativel (tipo:rest) = do{
    elem <- listaTiposCompativel' tipo rest;
    rest <- listaTiposCompativel rest;
    return (elem && rest)
}

getListaTiposExprL (e1 :&: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                            transformedE1 <- getListaTiposExprL e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                            transformedE2 <- getListaTiposExprL e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                            return (transformedE1 ++ transformedE2)
                                                         }
getListaTiposExprL (e1 :|: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                            transformedE1 <- getListaTiposExprL e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                            transformedE2 <- getListaTiposExprL e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                            return (transformedE1 ++ transformedE2)
                                                         }
getListaTiposExprL (Not e) declaracoesFuncao blocosFuncoes declaracaoMain =  do {
                                                            getListaTiposExprL e declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         }
getListaTiposExprL (Rel expR) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    getListaTiposExprR expR declaracoesFuncao blocosFuncoes declaracaoMain
}

getListaTiposExprR e@(e1 :==: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    res1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
    res2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
    return (res1 ++ res2)
 }
getListaTiposExprR e@(e1 :/=: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    res1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
    res2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
    return (res1 ++ res2)
 }
getListaTiposExprR e@(e1 :<=: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    res1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
    res2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
    return (res1 ++ res2)
 }
getListaTiposExprR e@(e1 :>=: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    res1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
    res2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
    return (res1 ++ res2)
 }
getListaTiposExprR e@(e1 :<: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    res1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
    res2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
    return (res1 ++ res2)
 }
getListaTiposExprR e@(e1 :>: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    res1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
    res2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
    return (res1 ++ res2)
 }

getTipoExp (e1 :+: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
getTipoExp (e1 :-: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
getTipoExp (e1 :*: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
getTipoExp (e1 :/: e2) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- getTipoExp e1 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- getTipoExp e2 declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 ++ transformedE2);
                                                     }
getTipoExp (Neg e) declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         getTipoExp e declaracoesFuncao blocosFuncoes declaracaoMain;
                                                     }
getTipoExp e@(Const (CDouble _)) _ _ _ = return [TDouble]
getTipoExp e@(Const (CInt _)) _ _ _ = return [TInt]
getTipoExp e@(Chamada nome params) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    paramsCompativel <- verificaTipoParams params declaracoesFuncao blocosFuncoes declaracaoMain;
    paramsTraduzido <- traduzExprComoParams params;
    unless paramsCompativel (erro ("Incompatible types in function parameters: "++nome++"( "++paramsTraduzido++" )"));
    quantParams <- getQuantidadeParams nome declaracoesFuncao;
    when (quantParams > length params) (erro ("Fewer parameters in the function call: "++nome++"( "++paramsTraduzido++" )"));
    when (quantParams < length params) (erro ("More parameters in the function call: "++nome++"( "++paramsTraduzido++" )"));
    tipoFuncao <- getTipoFuncao nome declaracoesFuncao;
    case tipoFuncao of
        Just tipoFuncao -> return [TVoid];
        Nothing -> return [TVoid];

 }
getTipoExp e@(IdVar nome) declaracoesFuncao blocosFuncoes declaracaoMain = do {
    tipoVar <- getTipoVar nome declaracaoMain;
    return [tipoVar]
 }
getTipoExp e@(Lit _) _ _ _ = return [TString]
getTipoExp e@(IntDouble elem) _ _ _ = return [TDouble]
getTipoExp e _ _ _ = return [TInt]

verificaTipoComExpr (e1 :+: e2) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaTipoComExpr e1 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaTipoComExpr e2 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 && transformedE2);
                                                     }
verificaTipoComExpr (e1 :-: e2) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaTipoComExpr e1 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaTipoComExpr e2 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 && transformedE2);
                                                     }
verificaTipoComExpr (e1 :*: e2) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaTipoComExpr e1 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaTipoComExpr e2 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 && transformedE2);
                                                     }
verificaTipoComExpr (e1 :/: e2) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         transformedE1 <- verificaTipoComExpr e1 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         transformedE2 <- verificaTipoComExpr e2 tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                         return (transformedE1 && transformedE2);
                                                     }
verificaTipoComExpr (Neg e) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
                                                         verificaTipoComExpr e tipo declaracoesFuncao blocosFuncoes declaracaoMain;
                                                     }
verificaTipoComExpr e@(Const (CDouble _)) tipo _ _ _ = return (tipo==TDouble || tipo==TInt)
verificaTipoComExpr e@(Const (CInt _)) tipo _ _ _ = return (tipo==TDouble || tipo==TInt)
verificaTipoComExpr e@(Chamada nome params) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
    paramsCompativel <- verificaTipoParams params declaracoesFuncao blocosFuncoes declaracaoMain;
    paramsTraduzido <- traduzExprComoParams params;
    unless paramsCompativel (erro ("Incompatible types in function parameters: "++nome++"( "++paramsTraduzido++" )"));
    quantParams <- getQuantidadeParams nome declaracoesFuncao;
    when (quantParams > length params) (erro ("Fewer parameters in the function call: "++nome++"( "++paramsTraduzido++" )"));
    when (quantParams < length params) (erro ("More parameters in the function call: "++nome++"( "++paramsTraduzido++" )"));
    tipoFuncao <- getTipoFuncao nome declaracoesFuncao;
    case tipoFuncao of
        Just tipoFuncao -> do {
         if tipoFuncao==TDouble || tipoFuncao==TInt then
             return (tipo==TDouble || tipo==TInt)
         else
             return (tipo==TString)
         }
        Nothing -> return False;

 }
verificaTipoComExpr e@(IdVar nome) tipo declaracoesFuncao blocosFuncoes declaracaoMain = do {
     tipoVar <- getTipoVar nome declaracaoMain;
     if tipoVar==TDouble || tipoVar==TInt then
         return (tipo==TDouble || tipo==TInt)
     else
         return (tipo==TString)
 }
verificaTipoComExpr e@(Lit _) tipo _ _ _ = return (tipo==TString)
verificaTipoComExpr e@(IntDouble elem) tipo _ _ _ = do {
     return (tipo==TDouble || tipo==TInt)
 }
verificaTipoComExpr e tipo _ _ _ = return (tipo==TDouble || tipo==TInt)

verificaTipoParams [] _ _ _ = return True
verificaTipoParams (x:xs) declaracoesFuncao blocosFuncoes declaracao = do {
     tipo <- getPriTipoComExpr x declaracoesFuncao blocosFuncoes declaracao;
     resu <- verificaTipoComExpr x tipo declaracoesFuncao blocosFuncoes declaracao;
     rest <- verificaTipoParams xs declaracoesFuncao blocosFuncoes declaracao;
     return (rest && resu)
 }

getPriTipoComExpr (e1 :+: e2) declaracoesFuncao blocosFuncoes declaracaoMain = getPriTipoComExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
getPriTipoComExpr (e1 :-: e2) declaracoesFuncao blocosFuncoes declaracaoMain = getPriTipoComExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
getPriTipoComExpr (e1 :*: e2) declaracoesFuncao blocosFuncoes declaracaoMain = getPriTipoComExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
getPriTipoComExpr (e1 :/: e2) declaracoesFuncao blocosFuncoes declaracaoMain = getPriTipoComExpr e1 declaracoesFuncao blocosFuncoes declaracaoMain;
getPriTipoComExpr (Neg e) declaracoesFuncao blocosFuncoes declaracaoMain = getPriTipoComExpr e declaracoesFuncao blocosFuncoes declaracaoMain
getPriTipoComExpr e@(Const (CDouble _)) _ _ _ = return TDouble
getPriTipoComExpr e@(Const (CInt _)) _ _ _ = return TInt
getPriTipoComExpr e@(Chamada nome _) declaracoesFuncao blocosFuncoes declaracaoMain = do { -- ficar de olho
     tipo <- getTipoFuncao nome declaracoesFuncao;
     case tipo of
         Just tipo -> return tipo;
 }
getPriTipoComExpr e@(IdVar nome) declaracoesFuncao blocosFuncoes declaracaoMain = getTipoVar nome declaracaoMain
getPriTipoComExpr e@(Lit _) _ _ _ = return TString
getPriTipoComExpr e _ _ _ = return TInt

-- 1 parte retorno
normalizaTipoRetorno [] [] _ _ = return []
normalizaTipoRetorno ((nome :->: (_, tipo)):ys) ((_,declaracoes,bloco):xs) declaracoesFuncao blocosFuncoes = do {
     transformedBloco <- normalizaTipoRetorno' declaracoesFuncao blocosFuncoes declaracoes bloco tipo;
     rest <- normalizaTipoRetorno ys xs declaracoesFuncao blocosFuncoes;
     return ((nome, declaracoes, transformedBloco) : rest)
 }

normalizaTipoRetorno' _ _ _ [] _ = return []
normalizaTipoRetorno' declaracoesFuncao blocosFuncoes listaVars (elem@(Ret (Just e)):xs) tipo = do {
    if tipo==TDouble then do
        transformedE <- transformaDouble e listaVars declaracoesFuncao;
        normalizaTipoRetorno' declaracoesFuncao blocosFuncoes listaVars xs tipo >>= \rest -> return (Ret (Just transformedE) : rest)
    else if tipo==TInt then do
        transformedE <- transformaInt e listaVars declaracoesFuncao e;
        normalizaTipoRetorno' declaracoesFuncao blocosFuncoes listaVars xs tipo >>= \rest -> return (Ret (Just transformedE) : rest)
    else do
        traduzidoE <- traduzExpr e
        erro ("Unexpected return type: "++traduzidoE);
        normalizaTipoRetorno' declaracoesFuncao blocosFuncoes listaVars xs tipo >>= \rest -> return (elem : rest);
}
normalizaTipoRetorno' declaracoesFuncao blocosFuncoes listaVars (elem:xs) tipo = do
    normalizaTipoRetorno' declaracoesFuncao blocosFuncoes listaVars xs tipo >>= \rest -> return (elem : rest);


verificaExprDouble (e1 :+: e2) listaVars listaFuncoes = do {
                                                         transformedE1 <- verificaExprDouble e1 listaVars listaFuncoes;
                                                         transformedE2 <- verificaExprDouble e2 listaVars listaFuncoes;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaExprDouble (e1 :-: e2) listaVars listaFuncoes = do {
                                                         transformedE1 <- verificaExprDouble e1 listaVars listaFuncoes;
                                                         transformedE2 <- verificaExprDouble e2 listaVars listaFuncoes;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaExprDouble (e1 :*: e2) listaVars listaFuncoes = do {
                                                         transformedE1 <- verificaExprDouble e1 listaVars listaFuncoes;
                                                         transformedE2 <- verificaExprDouble e2 listaVars listaFuncoes;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaExprDouble (e1 :/: e2) listaVars listaFuncoes = do {
                                                         transformedE1 <- verificaExprDouble e1 listaVars listaFuncoes;
                                                         transformedE2 <- verificaExprDouble e2 listaVars listaFuncoes;
                                                         return (transformedE1 || transformedE2);
                                                     }
verificaExprDouble (Neg e) listaVars listaFuncoes = do {
                                                         verificaExprDouble e listaVars listaFuncoes;
                                                     }
verificaExprDouble e@(Const (CDouble _)) _ _ = return True
verificaExprDouble e@(Const (CInt _)) _ _ = return False
verificaExprDouble e@(Chamada nome _) listaVars listaFuncoes = return (verificaSeDeclaracaoFuncaoDouble nome listaFuncoes)
verificaExprDouble e@(IdVar nome) listaVars listaFuncoes = return (verificaSeDeclaracaoDouble nome listaVars)
verificaExprDouble e@(Lit _) _ _ = return False

transformaExprRDouble (e1@(Lit _) :==: e2@(Lit _)) listaVars listaFuncoes = return (e1 :==: e2)
transformaExprRDouble (e1@(Lit _) :==: e2) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " == " ++ traduzidoE2)
                                                        return (e1 :==: e2)
transformaExprRDouble (e1 :==: e2@(Lit _)) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " == " ++ traduzidoE2)
                                                        return (e1 :==: e2)
transformaExprRDouble (e1 :==: e2) listaVars listaFuncoes = do {
                                                        transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                        transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                        return (transformedE1 :==: transformedE2);
                                                    }
transformaExprRDouble (e1@(Lit _) :/=: e2@(Lit _)) listaVars listaFuncoes = return (e1 :/=: e2)
transformaExprRDouble (e1@(Lit _) :/=: e2) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " /= " ++ traduzidoE2)
                                                        return (e1 :/=: e2)
transformaExprRDouble (e1 :/=: e2@(Lit _)) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " /= " ++ traduzidoE2)
                                                        return (e1 :/=: e2)
transformaExprRDouble (e1 :/=: e2) listaVars listaFuncoes = do {
                                                        transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                        transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                        return (transformedE1 :/=: transformedE2);
                                                    }
transformaExprRDouble (e1@(Lit _) :<=: e2@(Lit _)) listaVars listaFuncoes = return (e1 :<=: e2)
transformaExprRDouble (e1@(Lit _) :<=: e2) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " <= " ++ traduzidoE2)
                                                        return (e1 :<=: e2)
transformaExprRDouble (e1 :<=: e2@(Lit _)) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " <= " ++ traduzidoE2)
                                                        return (e1 :<=: e2)
transformaExprRDouble (e1 :<=: e2) listaVars listaFuncoes = do {
                                                        transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                        transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                        return (transformedE1 :<=: transformedE2);
                                                    }
transformaExprRDouble (e1@(Lit _) :>=: e2@(Lit _)) listaVars listaFuncoes = return (e1 :>=: e2)
transformaExprRDouble (e1@(Lit _) :>=: e2) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " >= " ++ traduzidoE2)
                                                        return (e1 :>=: e2)
transformaExprRDouble (e1 :>=: e2@(Lit _)) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " >= " ++ traduzidoE2)
                                                        return (e1 :>=: e2)
transformaExprRDouble (e1 :>=: e2) listaVars listaFuncoes =  do {
                                                        transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                        transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                        return (transformedE1 :>=: transformedE2);
                                                    }
transformaExprRDouble (e1@(Lit _) :<: e2@(Lit _)) listaVars listaFuncoes = return (e1 :<: e2)
transformaExprRDouble (e1@(Lit _) :<: e2) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " < " ++ traduzidoE2)
                                                        return (e1 :<: e2)
transformaExprRDouble (e1 :<: e2@(Lit _)) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " < " ++ traduzidoE2)
                                                        return (e1 :<: e2)
transformaExprRDouble (e1 :<: e2) listaVars listaFuncoes =  do {
                                                        transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                        transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                        return (transformedE1 :<: transformedE2);
                                                    }
transformaExprRDouble (e1@(Lit _) :>: e2@(Lit _)) listaVars listaFuncoes = return (e1 :>: e2)
transformaExprRDouble (e1@(Lit _) :>: e2) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " > " ++ traduzidoE2)
                                                        return (e1 :>: e2)
transformaExprRDouble (e1 :>: e2@(Lit _)) listaVars listaFuncoes = do
                                                        traduzidoE1 <- traduzExpr e1
                                                        traduzidoE2 <- traduzExpr e2
                                                        erro ("Incompatible relational expression in: " ++ traduzidoE1 ++ " > " ++ traduzidoE2)
                                                        return (e1 :>: e2)
transformaExprRDouble (e1 :>: e2) listaVars listaFuncoes =  do {
                                                        transformedE1 <- transformaDouble e1 listaVars listaFuncoes;
                                                        transformedE2 <- transformaDouble e2 listaVars listaFuncoes;
                                                        return (transformedE1 :>: transformedE2);
                                                    }


verificaExprRDouble e@(e1 :==: e2) listaVars listaFuncoes = do {
    res1 <- verificaExprDouble e1 listaVars listaFuncoes;
    res2 <- verificaExprDouble e2 listaVars listaFuncoes;
    if res1 || res2 then
        transformaExprRDouble e listaVars listaFuncoes
    else
        return e
 }
verificaExprRDouble e@(e1 :/=: e2) listaVars listaFuncoes = do {
    res1 <- verificaExprDouble e1 listaVars listaFuncoes;
    res2 <- verificaExprDouble e2 listaVars listaFuncoes;
    if res1 || res2 then
        transformaExprRDouble e listaVars listaFuncoes
    else
        return e
 }
verificaExprRDouble e@(e1 :<=: e2) listaVars listaFuncoes = do {
    res1 <- verificaExprDouble e1 listaVars listaFuncoes;
    res2 <- verificaExprDouble e2 listaVars listaFuncoes;
    if res1 || res2 then
        transformaExprRDouble e listaVars listaFuncoes
    else
        return e
 }
verificaExprRDouble e@(e1 :>=: e2) listaVars listaFuncoes = do {
    res1 <- verificaExprDouble e1 listaVars listaFuncoes;
    res2 <- verificaExprDouble e2 listaVars listaFuncoes;
    if res1 || res2 then
        transformaExprRDouble e listaVars listaFuncoes
    else
        return e
 }
verificaExprRDouble e@(e1 :<: e2) listaVars listaFuncoes = do {
    res1 <- verificaExprDouble e1 listaVars listaFuncoes;
    res2 <- verificaExprDouble e2 listaVars listaFuncoes;
    if res1 || res2 then
        transformaExprRDouble e listaVars listaFuncoes
    else
        return e
 }
verificaExprRDouble e@(e1 :>: e2) listaVars listaFuncoes = do {
    res1 <- verificaExprDouble e1 listaVars listaFuncoes;
    res2 <- verificaExprDouble e2 listaVars listaFuncoes;
    if res1 || res2 then
        transformaExprRDouble e listaVars listaFuncoes
    else
        return e
 }

verificaExprLDouble (e1 :&: e2) listaVars listaFuncoes = do {
                                                            transformedE1 <- verificaExprLDouble e1 listaVars listaFuncoes;
                                                            transformedE2 <- verificaExprLDouble e2 listaVars listaFuncoes;
                                                            return (transformedE1 :&: transformedE2)
                                                         }
verificaExprLDouble (e1 :|: e2) listaVars listaFuncoes = do {
                                                            transformedE1 <- verificaExprLDouble e1 listaVars listaFuncoes;
                                                            transformedE2 <- verificaExprLDouble e2 listaVars listaFuncoes;
                                                            return (transformedE1 :|: transformedE2)
                                                         }
verificaExprLDouble (Not e) listaVars listaFuncoes =  do {
                                                            transformedE <- verificaExprLDouble e listaVars listaFuncoes;
                                                            return (Not transformedE)
                                                         }
verificaExprLDouble (Rel expR) listaVars listaFuncoes = do {
    transformedExpR <- verificaExprRDouble expR listaVars listaFuncoes;
    return (Rel transformedExpR)
 }

normalizaDoubleR _ _ _ [] = return []
normalizaDoubleR declaracoesFuncao blocosFuncoes declaracaoMain (elem@(If exprL bloco blocoElse):xs) = do {
        transformedExprL <- verificaExprLDouble exprL declaracaoMain declaracoesFuncao;
        transformedBloco <- normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain bloco;
        transformedBlocoElse <- normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain blocoElse;
        rest <- normalizaDoubleR declaracoesFuncao blocosFuncoes declaracaoMain xs;
        return (If transformedExprL transformedBloco transformedBlocoElse : rest)
    }
normalizaDoubleR declaracoesFuncao blocosFuncoes declaracaoMain (elem@(While exprL bloco):xs) = do {
        transformedExprL <- verificaExprLDouble exprL declaracaoMain declaracoesFuncao;
        transformedBloco <- normalizaDouble declaracoesFuncao blocosFuncoes declaracaoMain bloco;
        rest <- normalizaDoubleR declaracoesFuncao blocosFuncoes declaracaoMain xs;
        return (While transformedExprL transformedBloco : rest)
    }
normalizaDoubleR declaracoesFuncao blocosFuncoes declaracaoMain (elem:xs) = do {
         rest <- normalizaDoubleR declaracoesFuncao blocosFuncoes declaracaoMain xs;
         return (elem : rest)
     }


verificaSeDeclaracaoDouble _ [] = False
verificaSeDeclaracaoDouble var ((nome :#: TDouble):xs) = (var == nome) || verificaSeDeclaracaoDouble var xs
verificaSeDeclaracaoDouble var (x:xs) = verificaSeDeclaracaoDouble var xs

verificaSeDeclaracaoFuncaoDouble _ [] = False
verificaSeDeclaracaoFuncaoDouble var (nome :->: (_,TDouble):xs) = (var == nome) || verificaSeDeclaracaoFuncaoDouble var xs
verificaSeDeclaracaoFuncaoDouble var (x:xs) = verificaSeDeclaracaoFuncaoDouble var xs

verificaSeDeclaracaoFuncaoInt _ [] = False
verificaSeDeclaracaoFuncaoInt var (nome :->: (_,TInt):xs) = (var == nome) || verificaSeDeclaracaoFuncaoInt var xs
verificaSeDeclaracaoFuncaoInt var (x:xs) = verificaSeDeclaracaoFuncaoInt var xs

traduzExprL (e1 :&: e2) = do {
                                                            transformedE1 <- traduzExprL e1;
                                                            transformedE2 <- traduzExprL e2;
                                                            return (transformedE1 ++ "&" ++ transformedE2)
                                                         }
traduzExprL (e1 :|: e2) = do {
                                                            transformedE1 <- traduzExprL e1;
                                                            transformedE2 <- traduzExprL e2;
                                                            return (transformedE1 ++ "|" ++ transformedE2)
                                                         }
traduzExprL (Not e) =  do {
                                                            traduzExprL e;
                                                         }
traduzExprL (Rel expR) = do {
    traduzExprR expR
}

traduzExprR e@(e1 :==: e2) = do {
    res1 <- traduzExpr e1;
    res2 <- traduzExpr e2;
    return (res1 ++ "==" ++ res2)
 }
traduzExprR e@(e1 :/=: e2) = do {
    res1 <- traduzExpr e1;
    res2 <- traduzExpr e2;
    return (res1 ++ "/=" ++ res2)
 }
traduzExprR e@(e1 :<=: e2) = do {
    res1 <- traduzExpr e1;
    res2 <- traduzExpr e2;
    return (res1 ++ "<=" ++ res2)
 }
traduzExprR e@(e1 :>=: e2) = do {
    res1 <- traduzExpr e1;
    res2 <- traduzExpr e2;
    return (res1 ++ ">=" ++ res2)
 }
traduzExprR e@(e1 :<: e2) = do {
    res1 <- traduzExpr e1;
    res2 <- traduzExpr e2;
    return (res1 ++ "<" ++ res2)
 }
traduzExprR e@(e1 :>: e2) = do {
    res1 <- traduzExpr e1;
    res2 <- traduzExpr e2;
    return (res1 ++ ">" ++ res2)
 }

traduzExpr (e1 :+: e2) = do {
                            transformedE1 <- traduzExpr e1;
                            transformedE2 <- traduzExpr e2;
                            return (transformedE1++" + "++transformedE2);
                        }
traduzExpr (e1 :-: e2) = do {
                            transformedE1 <- traduzExpr e1;
                            transformedE2 <- traduzExpr e2;
                            return (transformedE1++" - "++transformedE2);
                        }
traduzExpr (e1 :*: e2) = do {
                            transformedE1 <- traduzExpr e1;
                            transformedE2 <- traduzExpr e2;
                            return (transformedE1++" * "++transformedE2);
                        }
traduzExpr (e1 :/: e2) = do {
                            transformedE1 <- traduzExpr e1;
                            transformedE2 <- traduzExpr e2;
                            return (transformedE1++" / "++transformedE2);
                        }
traduzExpr (Neg e) = do {
                        return ("- "++show e);
                    }
traduzExpr e@(Const (CDouble v)) = return (show v)
traduzExpr e@(Const (CInt v)) = return (show v)
traduzExpr e@(Chamada nome v) = do {
     translatedV <- mapM traduzExpr v;
     return (nome++"("++unwords translatedV++")")
 }
traduzExpr e@(IdVar nome) = return nome;
traduzExpr e@(Lit v) = return ("'"++v++"'");
traduzExpr e@(IntDouble e1) = do { traduzExpr e1; }
traduzExpr e@(DoubleInt e1) = do { traduzExpr e1; }

traduzExprComoParams [] = return ""
traduzExprComoParams (x:[]) = do {
     traduzExpr x;
 }
traduzExprComoParams (x:xs) = do {
     traduzidoE <- traduzExpr x;
     rest <- traduzExprComoParams xs;
     return (traduzidoE ++ ", " ++ rest)
 }

getTipoVar _ [] = return TVoid
getTipoVar nome ((nomeVar :#: tipo):restoDec) = if nomeVar==nome then return tipo else getTipoVar nome restoDec;


getTipoParams _ [] = []
getTipoParams nome ((nomeFuncao :->: (listaVars,_)):funcoes) = if nome==nomeFuncao then listaVars else getTipoParams nome funcoes

getTipoFuncao nome [] = return Nothing
getTipoFuncao nome ((nomeFuncao :->: (_,tipo)):funcoes) = if nome==nomeFuncao then return (Just tipo) else getTipoFuncao nome funcoes

getQuantidadeParams _ [] = return 0
getQuantidadeParams nome ((nomeFuncao :->: (listaVars,_)):funcoes) = if nome==nomeFuncao then return (length listaVars) else getQuantidadeParams nome funcoes


