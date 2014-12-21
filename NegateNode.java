public class NegateNode extends UnaryNode{

	public NegateNode(Node right){
		super(right);
	}

	public int item(){
		return '-';
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}