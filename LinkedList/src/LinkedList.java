import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
	Node head, tail;
	private int size;
	
	private class Node {
		Node next, prev;
		T data;
		
		public Node() {}
		
		public Node(T item) {
			data = item;
			next = null;
		}
		
		
	}
	
	public LinkedList() {}
	
	
	
	public void add(T item) {
		//create new node for the new data 
		Node newNode = new Node(item);
		newNode.next = null;
		
		//after the if statements both head and tail should be pointing to the new node
		if(head == null) {
			head = newNode;

		} if(tail == null) {
			tail = newNode;
			//else statement is here if the add method is called for a second time or the head or tail nodes are already set
		} else {
			//change the head pointer to this node - secondNode 
			Node secondNode = head;
			//reassign the tail pointer to the new node -newNode
			tail = newNode;
			/** 
			 * 
			 * tells the program to look for a secondNode node with a .next value of null. should be the last node in line
			 */
			while(secondNode.next != null) {
				//changes the node next to it to secondNode unless the tail is reached
				secondNode = secondNode.next;
			}
			/**
			 * activates once the tail is reached
			 * 
			 * establishes the next and prev relationships of the nodes
			 */
			secondNode.next = newNode;
			newNode.prev = secondNode;
		}
		//increments size 
		size++;
	}
	
	public void addFirst(T item) {
		Node newHead = new Node(item);
		newHead.prev = null;
		
		if(head == null) {
			head = newHead;
		} if(tail == null) {
			tail = newHead;
		} else {
			Node oldHead = tail;
			
			while(oldHead.prev != null) {
				oldHead = oldHead.prev;
			}
			oldHead.prev = newHead;
			head = newHead;
			newHead.next = oldHead;
		}
		size++;
	}
	
	public void addLast(T item) {
		Node newNode = new Node(item);
		newNode.next = null;
		
		//after the if statements both head and tail should be pointing to the new node
		if(head == null) {
			head = newNode;

		} if(tail == null) {
			tail = newNode;
			//else statement is here if the add method is called for a second time or the head or tail nodes are already set
		} else {
			//change the head pointer to this node - secondNode 
			Node secondNode = head;
			//reassign the tail pointer to the new node -newNode
			tail = newNode;
			/** 
			 * 
			 * tells the program to look for a secondNode node with a .next value of null. should be the last node in line
			 */
			while(secondNode.next != null) {
				//changes the node next to it to secondNode unless the tail is reached
				secondNode = secondNode.next;
			}
			/**
			 * activates once the tail is reached
			 * 
			 * establishes the next and prev relationships of the nodes
			 */
			secondNode.next = newNode;
			newNode.prev = secondNode;
		}
		//increments size 
		size++;
	}
	
	
	
	public T getFirst () {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return head.data;
	}
	// this can throw an exception if the list is empty
	
	
	
	public T getLast () {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}
	// this can throw an exception if the list is empty
	
	
	
	
	
	public T removeFirst() {
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			Node newHead = head.next;
			head = newHead;
			head.prev = null;
		}
		size--;
		return head.data;
	}
	
	
	public T removeLast() {
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			Node newTail = tail.prev;
			tail = newTail;
			tail.next = null;
		}
		size--;
		return tail.data;
	}
	
	

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;
		tail = null;
		size = 0;
	}

	

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size == 0) {
			return true;
		} else {
			return false;
		}
	}

	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;                                                                                                                                                                                                                                 
	}

	

	@Override
	public void add(T item, int index) {
		// TODO Auto-generated method stub
		int count = 0;
		Node addIn = new Node(item);
		Node add = head;
		
		
		while(count != index) {
			add.next = add;
			
			count++;
		}
		add.next = addIn;
		addIn.prev = add;
		
		Node realign = tail;
		while(realign.prev!= null) {
			realign = realign.prev;
		}
		realign.prev = addIn;
		size++;
	}
	
	
	
	

	@Override 
	public T get(int index) {
		// TODO Auto-generated method stub
		if(index == 0) {
			return head.data;
		} else {
		
		int count = 0;
		T match = null;
		Node get = head;
		while(get.next != null) {
			get = get.next;
			
			count++;
			if(count == index) {
				match = get.data;
			}
		}
		return match;
		}
	}
	
	@Override
	public T remove(int index) {
		if(index == 0) {
			head = null;
			size--;
			return head.data;
			
		} 
		else {
		
		int count = 0;
		T match = null;
		Node get = head;
		while(count < index) {
			get = get.next;
			
			count++;
			if(count == index) {
				match = get.data;
			}
		}
		size--;
		return match;
		}
	}
	// using get(int) this becomes the same as remove(Object)
	
	

	@Override
	public int indexOf(Object item) {
		// TODO Auto-generated method stub
		Node index = head;
		int count = 0;
		while(index.next != item) {
			index = index.next;
			count++;
		}
		return count;
	}
	
	
	@Override
	public boolean contains(Object item) {
		// TODO Auto-generated method stub
		Node findIt = head;
		Node isThatIt = new Node();

		while(findIt.next != null) {
			
			findIt = findIt.next;
			if(findIt.data == item) {
				isThatIt.data = findIt.data;
			}
		}
		if(isThatIt.data != item) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		
		LinkedList<T> ofIt = new LinkedList();
		Node iterate = head;
		while(iterate.next != null) {
			ofIt.add(iterate.data);
			iterate = iterate.next;
		}
		
		Iterator it = ofIt.iterator();
		return it;
		
	}
	
	@Override
	public T remove(Object item) {
		// TODO Auto-generated method stub
		Node remove = head;
		Node blank = null;
		
		while(remove.data != item) {
			remove = remove.next;
			
			if(remove.data == item) {
				blank = remove; 
			}
		}
		remove.next = blank.next;
		blank.next.prev = remove.next;
		
		size--;
		
		return blank.data;
	}

	
		
	
	
	
}
