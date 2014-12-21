import java.util.Iterator;

public class ExpressionTree {

	private Node root;
	private TreeIteratorFactory iteratorFactory = new TreeIteratorFactory();

	public ExpressionTree (Node root){
		this.root= root;
	}

	public Iterator<ExpressionTree> getIterator(String traversal){
		return iteratorFactory.makeIterator(this,traversal);
	}

	public boolean isNull(){
		return root==null;
	}

	public ExpressionTree getLeft(){
		return new ExpressionTree(root.getLeft());
	}

	public ExpressionTree getRight(){
		return new ExpressionTree(root.getRight());
	}

	public void accept(Visitor visitor){
		root.accept(visitor);
	}

	public int item(){
		return root.item();
	}

}