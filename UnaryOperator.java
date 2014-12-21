public abstract class UnaryOperator extends Symbol{

	public UnaryOperator(Symbol right, int precedence){
		super(null,right, precedence);
	}

	public abstract Node build();

}