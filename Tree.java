public class Tree {
    private Node root;
    private Node current;
    public Tree(Node root){
        this.root = root;
        current = root;
    }

    public Tree(){
        //default
    }

    public void printInOrder(){
        root.printInOrder();
    }

    public void printPreOrder(){
        root.printPreOrder();
    }

    public void printPostOrder(){
        root.printPostOrder();
    }

    public void makeFile(){
        root.makeFile();
    }

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node newRoot){
        root = newRoot;
    }

    public boolean isEmpty(){
        return (root == null);
    }

    public Node getCurrent(){
        return current;
    }

    public void setCurrent(Node newCurrent){
        current = newCurrent;
    }

    public int countNodes(){
        if(current.getLeft() != null && current.getRight() != null){
            Tree left = new Tree(current.getLeft());
            Tree right = new Tree(current.getRight());
            return 1 + left.countNodes() + right.countNodes();
            }
        else if(current.getLeft() != null){
            Tree left = new Tree(current.getLeft());
            return 1 + left.countNodes();
            }

        else if(current.getRight() != null){
            Tree right = new Tree(current.getRight());
            return 1 + right.countNodes();
            }

        return 1;
    }


    public boolean search(String data){
        if(current.getLeft() == null && current.getRight() == null){
            return current.getValue().equals(data);
        }
        if(current.getLeft() == null){
            Tree right = new Tree(current.getRight());
            return right.search(data);
        }

        if(current.getRight() == null){
            Tree left = new Tree(current.getLeft());
            return left.search(data);
        }

        Tree left = new Tree(current.getLeft());
        Tree right = new Tree(current.getRight());
        return left.search(data) || right.search(data);
    }

    public void print(){
        root.print();
    }

public static Tree buildTree(){
    return new Tree(Node.buildTree());
}
  
    public static void main(String[] args) {
        
    }
}