public class MultiplyNode extends BinaryNode {

	public MultiplyNode(Node left, Node right){
		super(left,right);
	}

	public int item(){
		return '*';
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}



}