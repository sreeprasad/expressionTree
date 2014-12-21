public class BinaryNode extends UnaryNode{

	private Node left;
	
	public BinaryNode(Node left,Node right){
		super(right);
		this.left=left;
	}

	public Node getLeft(){
		return this.left;
	}
}