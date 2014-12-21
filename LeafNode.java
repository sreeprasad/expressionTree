public class LeafNode extends Node{

	public int item;

	public LeafNode(int item){
		this.item=item;
	}

	@Override
	public int item(){
		return this.item;
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public LeafNode(String item){
		this.item=Integer.parseInt(item);
	}

}