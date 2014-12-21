public class AddNode extends BinaryNode {


	public AddNode(Node left, Node right){
		super(left,right);
	}

	public int item(){
		return '+';
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}