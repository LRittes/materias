{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use <$>" #-}
import Text.Parsec
import Text.Parsec.Expr
import qualified Text.Parsec.Token as T
import Text.Parsec.Language
import System.IO
import Control.Monad (unless, when)

data Expr = Expr :+: Expr 
          | Expr :-: Expr 
          | Expr :*: Expr 
          | Expr :/: Expr 
          | Neg Expr 
          | Const TCons 
          | IdVar Id 
          | Chamada Id [Expr] 
          | Lit String
          | IntDouble Expr 
          | DoubleInt Expr 
          deriving (Eq, Show) 

data Comando = If ExprL Bloco Bloco
               | While ExprL Bloco
               | Atrib Id Expr
               | Leitura Id
               | Imp Expr
               | Ret (Maybe Expr)
               | Proc Id [Expr]
               deriving Show

data ExprR = Expr :==: Expr | Expr :/=: Expr | Expr :<: Expr | Expr :>: Expr | Expr :<=: Expr | Expr :>=: Expr deriving Show
data ExprL = ExprL :&: ExprL | ExprL :|: ExprL | Not ExprL | Rel ExprR deriving Show

type Bloco = [Comando]

type Id = String

data Tipo = TDouble | TInt | TString | TVoid deriving (Eq, Show) 
data TCons = CDouble Double | CInt Integer deriving (Eq, Show) 

data Var = Id :#: (Tipo, Int) deriving Show

data Funcao = Id :->: ([Var], Tipo) deriving Show


data Programa = Prog [Funcao] [(Id, [Var], Bloco)] [Var] Bloco deriving Show

lingDef = emptyDef
          { T.commentStart      = "{-"
            , T.commentEnd      = "-}"
            , T.commentLine     = "--"
            , T.reservedOpNames = ["+", "-", "/", "*", "&&", "||", "!", ">", "<", ">=", "<=", "==", "!=", "="]
            , T.reservedNames = ["if", "while","return", "else", "print", "read", "void"]
          }  

lexico = T.makeTokenParser lingDef

natural       = T.natural lexico
symbol        = T.symbol lexico
parens        = T.parens lexico
reservedOp    = T.reservedOp lexico
reservedNames = T.reserved lexico
identifier = T.identifier lexico
braces = T.braces lexico
float = T.float lexico
stringLiteral = T.stringLiteral lexico

tabela   = [[prefix "-" Neg],
            [binario "*" (:*:) AssocLeft, binario "/" (:/:) AssocLeft ],
            [binario "+" (:+:) AssocLeft, binario "-" (:-:) AssocLeft ]
           ]

tabelaL = [
          [prefix "!" Not],
          [binario "&&" (:&:) AssocLeft ],
          [binario "||" (:|:) AssocLeft ]
          ]

binario  name fun assoc = Infix (do{reservedOp name; return fun }) assoc
prefix   name fun       = Prefix (do{reservedOp name; return fun })

expr :: Parsec String u Expr
expr = buildExpressionParser tabela fator
       <?> "expression"   

exprL :: Parsec String u ExprL
exprL = buildExpressionParser tabelaL exprR
      <?> "expression"

fator :: Parsec String u Expr
fator = parens expr
       <|> try (do {
         n <- identifier;
         lp <- parens listaParans;
         return (Chamada n lp)})
      <|> try (do {n <- float; return (Const (CDouble n))})
      <|> do {n <- natural; return (Const (CInt n))}
      <|> do {n <- stringLiteral; return (Lit n)}
      <|> do {n <- identifier; return (IdVar n)}
       <?> "simple expression"

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

defineTipo :: Parsec String u Tipo
defineTipo =   do { reservedOp "int"; return TInt}
            <|> do { reservedOp "double"; return TDouble}
            <|> do { reservedOp "string"; return TString}

var :: Parsec String u Var
var = do {
    t <- defineTipo;
    n <- identifier;
    return (n :#: t);
}

listaId :: Parsec String u [String]
listaId = do {
      id <- identifier;
      l <- listaId';
      return (id:l);
}

listaId' :: Parsec String u [String]
listaId' = do {
        symbol ",";
        listaId;
      }
      <|> return []

declaracoes :: Parsec String u [Var]
declaracoes = do {
            t <- defineTipo;
            li <- listaId;
            symbol ";";
            d <- declaracoes;
            return [listaVar id t | id <- li]
      }
      <|> return []

listaVar :: Id -> Tipo -> Var
listaVar id t = id :#: t
      
tvExpr :: Parsec String u (Maybe Expr)
tvExpr = do { e <- expr; return (Just e)}
          <|> return Nothing

exprR :: Parsec String u ExprL
exprR = do {
         e1 <- expr;
         o <- opR;
         e2 <- expr;
         return (Rel (o e1 e2 ))
}

comando :: Parsec String u Comando
comando = do {  reservedNames "return";
        e<-tvExpr;
        symbol ";";
        return (Ret e); }
            <|> try ( do { 
                        id <- identifier;
                        symbol "=";
                        e <- expr;
                        symbol ";";           
                        return (Atrib id e)})
            <|> do { reservedNames "if"; l <- parens (fmap checkExprL exprL); b1<-bloco; b2<-senao; return (If l b1 b2) }
            <|> do { reservedNames "while"; l <- parens (fmap checkExprL exprL); b <- bloco; return (While l b) }
            <|> do { reservedNames "print"; e <- parens expr; symbol ";"; return (Imp e) }
            <|> do { reservedNames "read"; id <- parens identifier; symbol ";"; return (Leitura id) }
            <|> do { id <- identifier; lp <- parens listaParans; symbol ";"; return (Proc id lp)}

opR :: Parsec String u (Expr -> Expr -> ExprR)
opR =   do { reservedOp "=="; return (:==:)}
        <|> do { reservedOp "/="; return (:/=:)}
        <|> do { reservedOp "<="; return (:<=:)}
        <|> do { reservedOp ">="; return (:>=:)}
        <|> do { reservedOp "<"; return (:<:)}
        <|> do { reservedOp ">"; return (:>:)}


listaCmd :: Parsec String u Bloco
listaCmd = do {
            c <- comando;
            cs <- listaCmd;
            return (c : cs);
        }
        <|> return [];
        
bloco:: Parsec String u Bloco
bloco = do {
        braces listaCmd;
    }

blocoPrincipal :: Parsec String u ([Var], Bloco)
blocoPrincipal = do {
        braces blocoPrincipal';
}

blocoPrincipal' :: Parsec String u ([Var], Bloco)
blocoPrincipal' = do {
        vs <- declaracoes;
        lc <- listaCmd;
        return (vs, lc)
}

senao :: Parsec String u Bloco
senao = do {
           reservedNames "else";
           bloco;
        }
        <|> return [];

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

-- retornando tipo errado
parametros =   do {
          symbol ",";
          declParametros;
      }
      <|> return []

-- retornando tipo errado
declParametros = do {
        defineTipo;
        id <- identifier;
        parametros;
      }
      <|> return []

tipoRetorno :: Parsec String u Tipo
tipoRetorno = try (do {
          defineTipo;
        })
        <|> do {reservedNames "void"; return TVoid}

funcao :: Parsec String u (Funcao, (Id, [Var], Bloco))
funcao = do {
      t <- tipoRetorno;
      id <- identifier;
      lp <- parens declParametros;
      (vs, bc) <- blocoPrincipal;
      return (id :->: (vs, t), (id,vs,bc));
}

listaFuncoes :: Parsec String u ([Funcao], [(Id, [Var], Bloco)])
listaFuncoes = do {
      (f, descF) <- funcao;
      lf <- listaFuncoes;
      return (f:(fst lf), (descF:(snd lf)))
    }
    <|> return ([], [])



programa :: Parsec String u Programa
programa = do {
          (lf, descFs) <- listaFuncoes;
          b <- blocoPrincipal;
          return (Prog lf descFs (fst b) (snd b))
      }

partida :: Parsec String u Programa
partida = do {e <- programa; eof; return e}

parserE e = runParser partida [] "Expressoes" e

parserExpr s = case parserE s of
                     Left er -> print er
                     Right v -> (print v)

main = do 
      -- txt <- getLine
      txt <- readFile "teste1.j--" 
      parserExpr txt

-----------------------------------------------------

--------------------- Conversão de tipo ------------------------
intToDouble :: Integer -> Double
intToDouble n = fromIntegral n

doubleToInt :: Double -> Integer
doubleToInt n = round n

convertType e = case e of
                    Const (CDouble a) -> Const (CInt (doubleToInt a))
                    Const (CInt a) -> Const (CDouble (intToDouble a))
                    _ -> e

checkTypeInt e = case e of
                Const (CInt a) -> True
                _ -> False

checkTypeDouble e = case e of
                      Const (CDouble a) -> True
                      _ -> False

checkExpr :: Expr -> Expr -> (Expr, Expr)
checkExpr e1 e2 | checkTypeInt e1 && checkTypeDouble e2 = (convertType e1, e2)
                | checkTypeInt e2 && checkTypeDouble e1 =  (e1, convertType e2)
                | otherwise = (e1, e2)

convertExpr :: Expr -> Expr
convertExpr e = case e of
                  (a :+: b) -> do {
                                let (a', b') = checkExpr a b in
                                (a' :+: b')
                                 }
                  (a :-: b) -> do {
                                  let (a', b') = checkExpr a b in
                                  (a' :-: b')
                                   }
                  (a :*: b) -> do {
                                 let (a', b') = checkExpr a b in
                                 (a' :*: b')
                                  }
                  (a :/: b) -> do {
                                 let (a', b') = checkExpr a b in
                                 (a' :/: b')
                                  }
                  _ -> e

convertExprR :: ExprR -> ExprR
convertExprR e = case e of
                  (a :==: b) -> (a' :==: b')
                                where
                                (a', b') = checkExpr a b
                                 
                  (a :/=: b) -> (a' :/=: b')
                                where
                                (a', b') = checkExpr a b
                  (a :>: b) -> (a' :>: b')
                                where
                                (a', b') = checkExpr a b
                  (a :<: b) -> (a' :<: b')
                                where
                                (a', b') = checkExpr a b

                  (a :<=: b) -> (a' :<=: b')
                                where
                                (a', b') = checkExpr a b
                  (a :>=: b) -> (a' :>=: b')
                                where
                                (a', b') = checkExpr a b

checkExprL e = case e of
                      (a :&: b) ->  a' :&: b'
                                    where
                                    a' = checkExprL a;
                                    b' = checkExprL b;
                      (a :|: b) ->  a' :|: b'
                                    where
                                    a' = checkExprL a;
                                    b' = checkExprL b;
                      Rel eR -> Rel (convertExprR eR)
                      _ -> e

--------------------- Conversão de tipo ------------------------

test :: Parsec String u ExprL
test = do {
         e1 <- exprL;
         return (checkExprL e1)}