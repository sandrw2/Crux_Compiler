grammar Crux;
program
 : declarationList EOF
 ;

declarationList
 : declaration*
 ;

declaration
 : variableDeclaration
 | arrayDeclaration
 | functionDefinition
 ;

variableDeclaration
 : type Identifier SemiColon
 ;

type
 : Identifier
 ;

literal
 : Integer
 | True
 | False
 ;

designator : Identifier (OpenBracket expression0 CloseBracket)?;

op0
 : GreaterEqual
 | LesserEqual
 | NotEqual
 | Equal
 | GreaterThan
 | LessThan
 ;

op1
 : Add
 | Sub
 | Or
 ;

op2
 : Mul
 | Div
 | And
 ;

expression0 : expression1 (op0 expression1)?;

expression1
 : expression2
 | expression1 op1 expression2
 ;

expression2
 : expression3
 | expression2 op2 expression3
 ;

expression3
 : Not expression3
 | OpenParen expression0 CloseParen
 | literal
 | designator
 | callExpression
 ;

callExpression : Identifier OpenParen expressionList CloseParen;
expressionList : (expression0 (Comma expression0)*)?;

parameter : type Identifier;
parameterList : (parameter (Comma parameter)*)?;

arrayDeclaration : type Identifier OpenBracket Integer CloseBracket SemiColon;
functionDefinition : type Identifier OpenParen parameterList CloseParen statementBlock;

assignmentStatement : designator Assign expression0 SemiColon;
assignmentStatementNoSemi: designator Assign expression0;
callStatement : callExpression SemiColon;
ifStatement: If expression0 statementBlock (Else statementBlock)?;
forStatement: For OpenParen assignmentStatement expression0 SemiColon assignmentStatementNoSemi CloseParen statementBlock;
breakStatement : Break SemiColon;
returnStatement: Return expression0 SemiColon;
statement
 : variableDeclaration
 | callStatement
 | assignmentStatement
 | ifStatement
 | forStatement
 | breakStatement
 | returnStatement
 ;

statementList : statement*;
statementBlock : OpenBrace statementList CloseBrace;

SemiColon: ';';

Integer
 : '0'
 | [1-9] [0-9]*
 ;

True: 'true';
False: 'false';

And: '&&';
Or: '||';
Not: '!';
If: 'if';
Else: 'else';
For: 'for';
Break: 'break';
Return: 'return';

OpenParen: '(';
CloseParen: ')';
OpenBrace: '{';
CloseBrace: '}';
OpenBracket: '[';
CloseBracket: ']';
Add: '+';
Sub: '-';
Mul: '*';
Div: '/';
GreaterEqual: '>=';
LesserEqual: '<=';
NotEqual: '!=';
Equal: '==';
GreaterThan: '>';
LessThan: '<';
Assign: '=';
Comma: ',';

Identifier
 : [a-zA-Z] [a-zA-Z0-9_]*
 ;

WhiteSpaces
 : [ \t\r\n]+ -> skip
 ;

Comment
 : '//' ~[\r\n]* -> skip
 ;



