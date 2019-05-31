import java.util.*;

public class QueueUsingStack<T> {
    private Stack<T> inputStack;
    private Stack<T> outputStack;
    private int size;

    public QueueUsingStack() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
        size = 0;
    }

    public void enqueue(T e) {
        inputStack.push(e);
        size++;
    }

    public T dequeue() {
        if(outputStack.isEmpty()) {
            while(!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        T tmp = null;
        if(!outputStack.isEmpty()) {
            tmp = outputStack.pop();
            size--;
        }
        return tmp;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size==0;
    }
}