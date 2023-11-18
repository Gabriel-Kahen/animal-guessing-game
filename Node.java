import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Node
{
    private String value;
    private Node left;
    private Node right;

    public Node(String value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(String value){
        this.value = value;
        left = null;
        right = null;
    }

    public String getValue(){
        return value;
    }

    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public void setLeft(Node l){
        left = l;
    }

    public void setRight(Node r){
        right = r;
    }

    public void setValue(String value){
        this.value = value;
    }

    public void printInOrder(){
        if(left != null){
        left.printInOrder();
        }
        System.out.print(value + " ");
        if(right != null){
        right.printInOrder();
        }
    }

    public void printPreOrder(){
        System.out.print(value + " ");
        if(left != null){
        left.printPreOrder();
        }
        if(right != null){
        right.printPreOrder();
        }
    }

    public void printPostOrder(){
        if(left != null){
        left.printPostOrder();
        }
        if(right != null){
        right.printPostOrder();
        }
        System.out.print(value + " ");

    }

    public String print(){
        return this.print("", true, "");
    }

    public String print(String prefix, boolean isTail, String sb){
        if(right != null){
            right.print(prefix + (isTail ? "|  " : "   "), false, sb);
        }
        System.out.println(prefix+(isTail ? "\\--" : "/--") + value);
        if(left != null){
            left.print(prefix + (isTail ? "   " : "|  "), true, sb);
        }
        return sb;
    }

    public void makeFile(){
      ArrayList<String> vals = new ArrayList<String>();
      makeFileHelper(vals);
      try{
      FileWriter writer = new FileWriter("f.txt"); 
      for(String str: vals) {
        writer.write(str + System.lineSeparator());
      }
      writer.close();
      }
      catch(IOException ioe){
      }
   }

   public void makeFileHelper(ArrayList<String> vals){
    vals.add(value);
    if(left != null){
    left.makeFileHelper(vals);
    }
    else{vals.add("null");}
    if(right != null){
    right.makeFileHelper(vals);
    }
    else{vals.add("null");}
  }

  public static Node buildTree() {
    ArrayList<String> vals = new ArrayList<String>();
       try{
        File file = new File("f.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            vals.add(in.nextLine());
          }
          in.close();
        }
        catch(IOException ioe){
          }

    int[] current = {0}; // This is so stupid I hate my life I had this as an integer and it wouldn't work it would just make the whole array symmetrical and apparently it's because ints are immutable so just making it an int array with one value fixed it hahahhah fml I spent an hour trying to figure out why this wouldn't work im miserable
    return buildTreeHelper(vals, current);
}

private static Node buildTreeHelper(ArrayList<String> vals, int[] current) {
    if (current[0] >= vals.size() || vals.get(current[0]).equals("null")) {
        current[0]++;
        return null;
    }

    String value = vals.get(current[0]);
    Node n = new Node(value);
    current[0]++;

    n.setLeft(buildTreeHelper(vals, current));
    //like I thought that current would be different in these two calls but that's not how it works apparently!!!
    n.setRight(buildTreeHelper(vals, current));
    return n;
}

public static void guessingGame(){
    Node n = Node.buildTree();
    System.out.println("\n\n\n\n\n\n");
    n.print();
    System.out.println("\n\n\n\n\n\n");
    System.out.println("Get ready to play the animal guessing game!");
    try{Thread.sleep(1000);}catch(Exception e){};
    guess(n, n);
}

public static Node guess(Node n, Node original){
    Scanner input = new Scanner(System.in);
    String in = "";
    String in2 = "";
    String in3 = "";
    if(n.getLeft() == null || n.getRight() == null){
        System.out.println("I guess that your animal is a " + n.getValue() + ". Am I correct?");
        in = input.nextLine();
        if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
            System.out.println("I win!");
            try{Thread.sleep(1000);}catch(Exception e){};
            System.out.println("Would you like to play again?");
            in = input.nextLine();
            if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
                    System.out.println("Okay, let's go again!");
                    try{Thread.sleep(1000);}catch(Exception e){};
                    guessingGame();
                }
            else{
                System.out.println("Okay, see you later!");
                System.exit(0);
            }              

        }
        else{
            System.out.println("Dang it! I lose.");
            try{Thread.sleep(1000);}catch(Exception e){};
            System.out.println("Please enter the animal you were thinking.");
            in3 = input.nextLine();
            System.out.println("Please enter a yes/no question that has opposite answers for the guessed animal and the animal you are thinking of.");
            in2 = input.nextLine();
            System.out.println("Is the answer to this question yes or no for your animal?");
            in = input.nextLine();
            if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
                n.setLeft(new Node(in3));
                n.setRight(new Node (n.getValue()));
                n.setValue(in2);
                original.makeFile();
                System.out.println("Your animal is now in my database!");
                try{Thread.sleep(1000);}catch(Exception e){};
                System.out.println("Would you like to play again?");
                in = input.nextLine();
                if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
                    System.out.println("Okay, let's go again!");
                    try{Thread.sleep(1000);}catch(Exception e){};
                    guessingGame();
                }
                else{
                    System.out.println("Okay, see you later!");
                    System.exit(0);
                    } 
            }
            else{
                n.setLeft(new Node(n.getValue()));
                n.setRight(new Node(in3));
                n.setValue(in2);
                original.makeFile();
                System.out.println("Your animal is now in my database!");
                try{Thread.sleep(1000);}catch(Exception e){};
                System.out.println("Would you like to play again?");
                in = input.nextLine();
                if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
                    System.out.println("Okay, let's go again!");
                    try{Thread.sleep(1000);}catch(Exception e){};
                    guessingGame();
                }
            }
            try{Thread.sleep(1000);}catch(Exception e){};
            if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
                System.out.println("Okay, let's go again!");
                try{Thread.sleep(1000);}catch(Exception e){};
                guessingGame();
                }
            else{
                System.out.println("Okay, see you later!");
                System.exit(0);
            }              
        }
        input.close();
        return null;
    }
    //n.print();
    System.out.println(n.getValue());

    try{Thread.sleep(500);}catch(Exception e){};
    in = input.nextLine();
    if(in.contains("y") || in.contains("Y") || in.contains("yes") || in.contains("Yes") || in.contains("YES")){
        guess(n.getLeft(), original);
    }
    else{
        guess(n.getRight(), original);
    }

    input.close();
    return n;
    }
}