import java.util.Stack;

public class Intepreter {


	private int accumalatedPrecedence=0; // store precedence of Symbol

	private int multipleDigits=0; //store multiple digits

	private Symbol lastValidInput;// stores last valid Symbol

	final static int addSubPrecedence=1; // stores precedence of + and -

	final static int mulDivPrecedence=2; //stores precedence of * and /

	final static int negatePrecedence=3; // stores precedence of negation op

	final static int numberPrecedence=4; // stores precedence of number

	final static int parentPrecedence=5; // stores precedence of parent number

	//to create expression tree instances
	private ExpressionTreeFactory	expressionTreeFactory = new ExpressionTreeFactory();
	private SymbolTable symbolTable = new SymbolTable();

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
				}else if(exp.charAt(i)==' '||exp.charAt(i)=='n' ){
					handle=true;
				}
				return parseTree;
			}
	
	private Stack<Symbol> insertSymbolDigit(String exp, Stack<Symbol> parseTree, int i, boolean isVariable){
		int end=1;
		for(;i+end<exp.length() && Character.isDigit(exp.charAt(i+end)); end++){
			continue;
		}
		Number number=null;
		if(isVariable){
			number=new Number(symbolTable.get(exp.substring(i,i+end)));
		}else{
			number=new Number(exp.substring(i,i+end));
		}
		i+=end;
		multipleDigits=i;
		lastValidInput=number;
		return insertSymbolByPrecedence(number,parseTree);
	}

	private Stack<Symbol> insertSymbolByPrecedence(Symbol symbol, Stack<Symbol> parseTree){
		 
        if(!parseTree.empty()) {
            Symbol parent = parseTree.peek();
            Symbol child = parent.right;
            if(child != null)                 
                for(;child != null && child.precedence () < symbol.precedence (); child = child.right)
                	parent = child;
            		if(parent.precedence () < symbol.precedence()) {                 
                		if(symbol.left == null){
                    		symbol.left = child;
                    	}
                		parent.right = symbol;
            		} else {
                 
                	UnaryOperator up = new Negate();
					if(symbol.getClass() == up.getClass()) {
                    	for(;child != null && child.precedence() == symbol.precedence();child = child.right)
                        	parent = child;
                    	parent.right = symbol;
                	} else {                     
                    	symbol.left = parent;
                    	parseTree.pop();
                    	parseTree.push(symbol);
                    	parent = child;
                	}
            	}
        	} else{
            	parseTree.push(symbol);
        	}	
        	return parseTree;
		}

		public class Negate extends UnaryOperator{

		 	public Negate(){
				super(null,negatePrecedence);
			}

			public int addPrecedence(int accumaltedPrecedence){
				return precedence=precedence+accumaltedPrecedence;
			}
				
			public Node build(){
				return new NegateNode(right.build());
			}

			public int precedence(){
				return precedence;
			}

		}

		class Add extends Operator {
	         
	        public Add() {
	            super(null, null, addSubPrecedence);
	        }

	        
	        public int addPrecedence(int accumulatedPrecedence) {
	            return precedence =
	                addSubPrecedence + accumulatedPrecedence;
	        }

	       
	        public Node build() {
	            return new AddNode(left.build(),
	                                        right.build());
	        }

	      
	        public int precedence() {
	            return precedence;
	        }
    	}

    	class Subtract extends Operator {
	         
	        public Subtract() {
	            super(null, null, addSubPrecedence);
	        }

	        
	        public int addPrecedence(int accumulatedPrecedence) {
	            return precedence =
	                addSubPrecedence + accumulatedPrecedence;
	        }

	       
	        public Node build() {
	            return new SubtractNode(left.build(),
	                                             right.build());
	        }

	      
	        public int precedence() {
	            return precedence;
	        }
	    }

	    class Multiply extends Operator {
         
        public Multiply() {
            super(null, null, mulDivPrecedence);
        }

   
        public int addPrecedence(int accumulatedPrecedence) {
            return precedence =
                mulDivPrecedence + accumulatedPrecedence;
        }

  
        public Node build() {
            return new MultiplyNode(left.build(),
                                             right.build());
        }

 
        public int precedence() {
            return precedence;
        }
    }


    class Divide extends Operator {
         
        public Divide() {
            super(null, null, mulDivPrecedence);
        }

        
        public int precedence() {
            return precedence;
        }

       
        public int addPrecedence(int accumulatedPrecedence) {
            return precedence = 
                mulDivPrecedence + accumulatedPrecedence;
        }

      
        public Node build() {
            return new DivideNode(left.build(),
                                           right.build());
        }
    }

}
