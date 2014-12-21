public class DivideNode extends BinaryNode {

	public DivideNode(Node left, Node right){
		super(left,right);
	}

	public int item(){
		return '/';
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}