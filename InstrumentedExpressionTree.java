 

import java.util.Iterator;

public class InstrumentedExpressionTree extends ExpressionTree {
 
    private TreeIteratorFactory iteratorFactory = new TreeIteratorFactory();

    public InstrumentedExpressionTree(Node root) {
        super(root);
    }

  
    public boolean isNull() {
        System.out.println("starting isNull() call");
        boolean temp = super.isNull();
        System.out.println("finished isNull() call");
        return temp;
    }

    
    public Node getRoot() {
        System.out.println("starting getRoot() call");
        Node temp = super.getRoot();
        System.out.println("finished getRoot() call");
        return temp;
    }

     
    public int item() throws Exception {
        System.out.println("starting left() call");
        int temp = super.item();
        System.out.println("finished left() call");
        return temp;
    }

     
    public ExpressionTree left() {
        System.out.println("starting left() call");
        ExpressionTree temp = super.getLeft();
        System.out.println("finished right() call");
        return temp;
    }

     
    public ExpressionTree right() {
        System.out.println("starting right() call");
        ExpressionTree temp = super.getRight();
        System.out.println("finished right() call");
        return temp;
    }

     
    public void accept(Visitor visitor) {
        System.out.println("starting accept() call");
        super.accept(visitor);
        System.out.println("finished accept() call");
    }

    public Iterator<ExpressionTree> makeIterator(String traversal) {
        System.out.println("starting makeIterator() call");
        Iterator<ExpressionTree> temp =
        iteratorFactory.makeIterator(this,traversal);
        System.out.println("finished makeIterator() call");
        return temp;
    }
}