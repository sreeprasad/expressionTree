public abstract class Node{

	public  int item() throws Exception{
		throw new UnsupportedOperationException("no item here");
	}

	public void accept(Visitor visit){
		throw new UnsupportedOperationException("no visitor allowed here");
	}
	
	public Node getLeft(){
		return null;
	}

	public Node getRight(){
		return null;
	}

}	

