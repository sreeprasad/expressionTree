public class SubtractNode extends BinaryNode{

	public SubtractNode(Node left, Node right){
		super(left,right);
	}

	public int item(){
		return '+';
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}