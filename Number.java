public class Number extends Symbol{

	public  int item;
	
	public Number(String input){
		super(null,null,Integer.parseInt(input));
		item = Integer.parseInt(input);
	}

	public Number(int input){
		super(null,null,input);
		this.item=input;
	}

	public int precedence(){
		return precedence;
	}

	@Override
	public Node build(){
		return new LeafNode(item);
	}


	public int addPrecedence(int accumalatedPrecedence){
		return precedence=precedence+accumalatedPrecedence;
	}

}