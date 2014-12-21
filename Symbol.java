public abstract class Symbol{

	public Symbol left, right;
	public int precedence=0;

	public Symbol(Symbol left,Symbol right, int precedence){
		this.left=left;
		this.right=right;
		this.precedence=precedence;
	}

	public int precedence(){
		return precedence;
	}
	public abstract int addPrecedence(int precedence);

	public abstract Node build();
}