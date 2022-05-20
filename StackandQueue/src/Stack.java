


public class Stack<V> {
	Object[] stack;
	int numOfItems;
	int capacity;
	
	

    /**
        Constructor to initialize the stack with a maximum capacity of maxnumOfItems.
    */
    @SuppressWarnings("unchecked")
    public Stack(int maxSize) { 
    	stack = new Object[maxSize];
    	capacity = maxSize;
    }
 
    /**
         Pushes an item onto the top of this stack.
    */
    public void push(V element) {
    	if(capacity < 0) {
    		throw new ArrayIndexOutOfBoundsException();
    	} else {
    		
    	stack[capacity - 1] = element;
    	capacity--;
    	
    	}
    	numOfItems++;
    }
    
    
 
    /**
        Removes the object at the top of this stack and returns that object as the value of this function.
    */
    @SuppressWarnings("unchecked")
	public V pop() {
    	
    	
    	int x = 0;
    	while(stack[x] == null) {
    		x++;
    	}
    	
    	V itemToPop = (V) stack[x];
    	System.out.println("The item you are removing is: " + itemToPop);
    	stack[x] = null;

    	numOfItems--;
    	return itemToPop;
    }
 
    /**
        Looks at the object at the top of this stack without removing it from the stack.
    */
    public V peek() {
    	int x = 0;
    	while(stack[x] == null) {
    		x++;
    	}
    	return (V) stack[x];
    }
 
    /**
        Returns the number of items currently in the stack
    */
    public int currentSize() {
    	return numOfItems;
    }
 
    /**
        Tests if this stack is empty.
    */
    public boolean isEmpty() {
    	if(numOfItems == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
 
    /**
        Tests if this stack is full
    */
    public boolean isFull() {
    	if(numOfItems == capacity) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
}