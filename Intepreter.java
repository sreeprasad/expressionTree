import java.util.Stack;

public class Intepreter {


	private int accumalatedPrecedence=0; // store precedence of Symbol

	private int multipleDigits=0; //store multiple digits

	private Symbol lastValidInput;// stores last valid Symbol

	private int addSubPrecedence=1; // stores precedence of + and -

	private int mulDivPrecedence=2; //stores precedence of * and /

	private int negatePrecedence=3; // stores precedence of negation op

	private int numberPrecedence=4; // stores precedence of number

	private int parentPrecedence=5; // stores precedence of parent number

	//to create expression tree instances
	private ExpressionTreeFactory	expressionTreeFactory = new ExpressionTreeFactory();
	
	public ExpressionTree interpret(String exp){
		Stack<Symbol> parseTree = buildParseTree(exp);
		if(parseTree!=null && !parseTree.isEmpty()){
			optimiseParseTree(parseTree);
			return buildExpressionTree(parseTree);
		}else{
			return expressionTreeFactory.makeExpressionTree(null);
		}
	}

	private Stack<Symbol> buildParseTree(String exp){
		Stack<Symbol> parseTree = new Stack<Symbol>();
		boolean handle=false;
		accumalatedPrecedence=0;
		multipleDigits=0;
		for(int i=0;i<exp.length();i++){
			parseTree = handleNextSymbol(exp,parseTree,i,handle);
			if(multipleDigits>i){
				i=multipleDigits;
			}
		}
		return parseTree;
	}

	private Stack<Symbol> handleNextSymbol(String exp, Stack<Symbol> parseTree, int i, boolean handle){

			if(Character.isDigit(exp.charAt(i))){
				handle=true;
				parseTree= insertSymbolDigit(exp,parseTree,i,false);
			}if(Character.isLetterOrDigit(exp.charAt(i))){
				handle=true;
				parseTree= insertSymbolDigit(exp,parseTree,i,true);
			}else if(exp.charAt(i)=='+'){
				handle=true;
				Add op = new Add();
				op.addPrecedence(accumalatedPrecedence);
				lastValidInput = null;
				parseTree= insertSymbolByPrecedence(op,parseTree);
			}else if(exp.charAt(i)=='-'){
				handle=true;
				Symbol op = null;
					if(lastValidInput==null){
						op= new Negate();
						op.addPrecedence(accumalatedPrecedence);
					}else{
						op= new Subtract();
						op.addPrecedence(accumalatedPrecedence);
					}				
					lastValidInput = null;
					parseTree= insertSymbolByPrecedence(op,parseTree);
				}else if(exp.charAt(i)=='*'){
					handle=true;
					Multiply op = new Multiply();
					op.addPrecedence(accumalatedPrecedence);
					lastValidInput = null;
					parseTree= insertSymbolByPrecedence(op,parseTree);
				}else if(exp.charAt(i)=='/'){
					handle=true;
					Divide op = new Divide();
					op.addPrecedence(accumalatedPrecedence);
					lastValidInput = null;
					parseTree= insertSymbolByPrecedence(op,parseTree);
				}else if(exp.charAt(i)==' '||exp.charAt(i)=='/n' ){
					handle=true;
				}
				return parseTree;
			}
	}

}