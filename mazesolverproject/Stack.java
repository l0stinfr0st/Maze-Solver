package mazesolverproject;

public class Stack<T> {
    public Node<T> topState;
    
    public Stack() {
        topState = null;
    }
    
    public boolean isEmpty() {
        return topState==null;
    }
    
    public void push(T n) {
        Node<T> newNode = new Node<T>(n);
        newNode.next = topState;
        topState = newNode;        
    }
    
    public T pop() {
        Node<T> t = topState;
        if(!isEmpty())
            topState = topState.next;
        return t.data;
    }
    
    

     class Node<T>{
        Node<T> next = null;
        T data;

        Node(T data){
            this.data = data;
        }

    }
}
