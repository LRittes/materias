import Text.Parsec
import Text.Parsec.Expr
import Text.Parsec.Token
import Text.Parsec.Language

-- Definindo o ADT Expr
data Expr = Add Expr Expr
          | Sub Expr Expr
          | Mul Expr Expr
          | Div Expr Expr
          | Neg Expr
          | Const Integer
          deriving Show

-- Definindo a gramática
exprParser :: Parsec String () Expr
exprParser = buildExpressionParser table term <?> "expression"

term :: Parsec String () Expr
term = try (parens exprParser)
       <|> try (do
               n <- natural lexer
               return (Const n))
       <?> "term"

table :: OperatorTable String () Expr
table = [ [Prefix (reservedOp "-" >> return Neg)]
        , [Infix (reservedOp "*" >> return (Mul)) AssocLeft,
           Infix (reservedOp "/" >> return (Div)) AssocLeft]
        , [Infix (reservedOp "+" >> return (Add)) AssocLeft,
           Infix (reservedOp "-" >> return (Sub)) AssocLeft]
        ]

lexer :: TokenParser ()
lexer = makeTokenParser emptyDef

-- Função para analisar uma expressão e retornar o ADT Expr
parseExpr :: String -> Either ParseError Expr
parseExpr = parse exprParser ""

main :: IO ()
main = do
  putStr "Expressão: "
  e <- getLine
  case parseExpr e of
    Left err -> print err
    Right expr -> print expr

