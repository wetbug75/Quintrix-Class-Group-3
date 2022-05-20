

public class Queue<V> {
	Object[] queue;
	int numOfItems;
	int capacity;

    /**
    * Constructor to initialize the queue with a maximum capacity of maxSize.
    */
    @SuppressWarnings("unchecked")
    public Queue(int maxSize) {
    	queue = new Object[maxSize];
    	capacity = maxSize;
    }

    /**
        Returns the maximum size this queue can support.
    */
    public int getMaxSize() {
    	return capacity;
    }

     /**
        Returns the current number of items enqueued.
    */
    public int getCurrentSize() {
    	return numOfItems;
    }

    /**
        Returns true if there are no elements in the queue.
    */
    public boolean isEmpty() {
    	if(numOfItems == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
        Returns true if the queue is size is at max size.
    */
    public boolean isFull() {
    	if(numOfItems == capacity) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
        Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    */
    public V peek() {
    	if(numOfItems == 0) {
    		return null;
    	} else {
    		
    		int x = 0;
    		while(queue[x] == null) {
    			x++;
    		}
    		
    		return (V) queue[x];
    	}
    }
    
   /**
        Inserts the specified element into this queue if it is possible to do so 
        immediately without violating capacity restrictions, 
        returning true upon success and 
        throwing an IllegalStateException if no space is currently available.
    */
    public void add(V value) {
    	if(numOfItems == capacity) throw new IllegalStateException();
    	else {
    		int x = 1;
    		while(x < capacity) {
    			queue[x - 1] = queue[x];
    			x++;
    		}
    		queue[capacity - 1] = value;
    	}
   
    	numOfItems++;
		
    }

    /**
        Retrieves and removes the head of this queue, or returns null if this queue is empty.
    */
    public V poll() {
    	if(numOfItems == 0) {
    		return null;
    	} else {
    		
    		int x = 0;
    		V whosNext;
    		while(queue[x] == null) {
    			x++;
    		}
    		whosNext = (V) queue[x];
    		queue[x] = null;
    		numOfItems--;
    		return whosNext;
    	}
    }
    
    /**
        Retrieves and removes the head of this queue.
    */
     public V remove() {
    	 int x = 0;
    	 V next;
    	 while(queue[x] == null) {
    		 x++;
    	 }
    	 next = (V) queue[x];
    	 numOfItems--;
    	 queue[x] = null;
    	 return next;
    }
    
}