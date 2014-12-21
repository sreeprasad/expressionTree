public class UnaryNode extends Node{

	private Node right;

	public UnaryNode(Node right){
		this.right=right;
	}

	 public Node getRight(){
	 	return this.right;
	 }

}