import java.util.Iterator;
public class SynchronizedExpressionTree extends ExpressionTree {

 
	private TreeIteratorFactory iteratorFactory = new TreeIteratorFactory();

	public SynchronizedExpressionTree (Node root){
		super(root);
	}

	public Iterator<ExpressionTree> getIterator(String traversal){
		Iterator<ExpressionTree> synchIterator=null;
		synchronized(this){
				synchIterator=iteratorFactory.makeIterator(this,traversal);
		}
		return synchIterator;
	}

	public boolean isNull(){
		boolean isSuperRootNull = false;
		synchronized(this){
			isSuperRootNull=super.isNull();
		}
		return isSuperRootNull;
	}

	public ExpressionTree getLeft(){
		ExpressionTree superLeft=null;
		synchronized(this){
			superLeft= super.getLeft();
		}
		return superLeft;
	}

	public ExpressionTree getRight(){
		ExpressionTree superRight=null;
		synchronized(this){
			superRight= super.getRight();
		}
		return superRight;
	}

	public void accept(Visitor visitor){
		synchronized(this){
			super.accept(visitor);	
		}
	}

	public int item() throws Exception{
		int item;
		synchronized(this){
			item=super.item();
		}
		return item;
	}

	public Node getRoot(){
		return super.getRoot();
	}

}