import java.util.Iterator;

public class LinkedList<T> implements List<T> {
	private class Node {
		Node next, prev;
		T data;
		
		
	}
	
	private Node head, tail;
	private int size;
	
	public LinkedList() {
		this.head = new Node();
		this.tail = new Node();
	}
	
	public void add(T item) {
		
		if(size == 0) {
			head.assignData(item);
			
		} else {
			tail.assignData(item);
			if(size == 1) {
				tail.whoCameFirst(head);
				head.whosNext(tail);
			} else {
				//if size is 2 or greater
				Node newNode = new Node();
				newNode.data = item;
			}
		}	
		size++;
	}
	
	public static void main(String[] args) {
		LinkedList test = new LinkedList();
		test.add(4);
		test.add(5);
		test.add(6);
		
		System.out.println(test.size);
		System.out.println(test.head.data);
		System.out.println(test.tail.data);
		
		
		
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public T remove(Object item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(T item, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
