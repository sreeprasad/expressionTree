public abstract class UnaryOperator{

	public UnaryOperator(Symbol right, int precedence){
		super(null,right, precedence);
	}

	public abstract Node build();

}