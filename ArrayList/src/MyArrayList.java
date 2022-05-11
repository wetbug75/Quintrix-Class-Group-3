
public class MyArrayList<T> implements List<T> {
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	private T[] data;
	private int size;
	private int capacity;
	


	/**
	 * Constructs an ArrayList using the default capacity
	 */

	public MyArrayList() {
		// TODO Complete Constructor
		size = 0;
		capacity = DEFAULT_INITIAL_CAPACITY;
		data = (T[]) new Object[capacity];
	}
	
	/**
	 * Constructs an ArrayList with an 'initialCapacity'
	 * 
	 * If 'initalCapacity' is non-positive use the default capacity
	 * 
	 * @param initialCapacity
	 */
	public MyArrayList(int initialCapacity) {
		// TODO Complete Constructor
		size = 0;
		if(initialCapacity < 0) {
			capacity = DEFAULT_INITIAL_CAPACITY;
			data = (T[]) new Object[capacity];
		} else {
			capacity = initialCapacity;
			data = (T[]) new Object[capacity];
		}

	}
	

	@Override
	public void add(T item, int index) {
		// TODO Auto-generated method stub
		
		if(index >= 0 && index <= capacity) {
			data[index] = item;
			size++;
		} 

	}
	

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		data = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	

	
	@Override
	public boolean contains(Object item) {
		// TODO Auto-generated method stub
		int itemSpot = this.indexOf(item);
		if(itemSpot == -1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index > capacity) {
			return null;
		} else {
 			return data[index];
		}
	}

	@Override
	public int indexOf(Object item) {
		int index = -1;
		for(int x = 0; x < size; x++) {
			if(data[x] == item) {
				index = x;
			} 
		}
		return index;
	}
	
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size == 0) {
 			return true;
 		}
 		else {
 			return false;
 		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
}
	

