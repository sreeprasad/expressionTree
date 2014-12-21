public abstract class UnaryOperator{

	public UnaryOperator(Symbol left, Symbol right, int precedence){
		super(left,right, precedence);
	}

	public abstract Node build();

}