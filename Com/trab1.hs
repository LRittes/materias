import Text.Parsec
import Text.Parsec.Expr
import qualified Text.Parsec.Token as T
import Text.Parsec.Language

data Expr = Add Expr Expr
          | Sub Expr Expr
          | Mul Expr Expr
          | Div Expr Expr
          | Neg Expr
          | Const Integer
          deriving Show

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
data Tipo = TDouble | TInt | TString | TVoid deriving Show
data TCons = CDouble Double | CInt Int deriving Show

lingDef = emptyDef
          { T.commentStart      = "{-"
            , T.commentEnd      = "-}"
            , T.commentLine     = "--"
            , T.reservedOpNames = ["+", "-", "/", "*", "&&", "||", "!", ">", "<", ">=", "<=", "==", "!=", "="]
            , T.reservedNames = ["if", "while","return", "else", "print", "read"]
          }  

lexico = T.makeTokenParser lingDef

natural       = T.natural lexico
symbol        = T.symbol lexico
parens        = T.parens lexico
reservedOp    = T.reservedOp lexico
reservedNames = T.reserved lexico
identifier = T.identifier lexico
braces = T.braces lexico
virSepa = T.commaSep lexico

tabela   = [[prefix "-" Neg]
            , [binario "*" Mul AssocLeft, binario "/" Div AssocLeft ]
            , [binario "+" Add AssocLeft, binario "-" Sub   AssocLeft ]
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

exprL = buildExpressionParser tabelaL exprR
      <?> "expression"

fator :: Parsec String u Expr
fator = parens expr
       <|> (Const <$> natural)
       <?> "simple expression"

partida :: Parsec String u Comando
partida = do {e <- comando; eof; return e}

parserE e = runParser partida [] "Expressoes" e

parserExpr s = case parserE s of
                     Left er -> print er
                     Right v -> (print v)

tvExpr = do { e <- expr; return (Just e)}
          <|> return Nothing

exprR = do {
         e1 <- expr;
         o <- opR;
         e2 <- expr;
         return (Rel (o e1 e2))
}

comando = do { reservedNames "return" ; e <- tvExpr ; symbol ";" ; return (Ret e) }
            <|> do { id <- identifier; symbol "="; e <- expr; symbol ";"; return (Atrib id e) }
            <|> do { reservedNames "if"; l <- parens exprL; b1<-bloco; b2<-senao; return (If l b1 b2) }
            <|> do { reservedNames "while"; l <- parens exprL; b <- bloco; return (While l b) }
            <|> do { reservedNames "print"; e <- parens expr; symbol ";"; return (Imp e) }
            <|> do { reservedNames "read"; id <- parens identifier; symbol ";"; return (Leitura id) }
            <|> do { id <- identifier; lp <- parens (virSepa expr); symbol ";"; return (Proc id lp)}

opR =   do { reservedOp "=="; return (:==:)}
        <|> do { reservedOp "/="; return (:/=:)}
        <|> do { reservedOp "<="; return (:<=:)}
        <|> do { reservedOp ">="; return (:>=:)}
        <|> do { reservedOp "<"; return (:<:)}
        <|> do { reservedOp ">"; return (:>:)}

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

-- listaParans = do {
--         listaParans'
--         }
--         <|> return [];

-- listaParans' = do {
--         e <- expr;
--         listaParans'';
--         }

-- listaParans'' = do {
--             virSepa listaParans';
--             }
--             <|> return [];

main = do 
      putStr "Expressão:"
      e <- getLine 
      parserExpr e  

-- return 2+2;

-- x = 2;

-- if(1==3){}
-- if(1==3 && 1>2){}
-- if(){}
-- if(1>1)
-- if(2>3){}else{}
-- if(2>3){x = 2;}else{}
-- if(2<3){x = 2;}else{return 3/1;}

-- while(2>1){}
-- while(2>1)
-- while(){}

-- print(2+1);

-- read(x);