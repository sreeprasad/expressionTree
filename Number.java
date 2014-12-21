public class Number extends Symbol{

	public  int item;
	
	public Symbol(String input){
		super(null,null,input);
		item = Integer.parseInt(item);
	}

	public Symbol(int input){
		super(null,null,input);
		this.item=input;
	}

	public int precedence(){
		return precedence;
	}

	@override
	Node build(){
		return new LeafNode(item);
	}


	public int addPrecedence(int precedence){
		return precedence=numberPrecedence+accumalatedPrecedence;
	}

}