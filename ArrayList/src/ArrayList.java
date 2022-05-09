public class ArrayList<T> implements List<T> {

	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	private T[] data;
	private int size;

	/**
	 * Constructs an ArrayList using the default capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		// TODO Complete Constructor
		size = 0;
		data = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	/**
	 * Constructs an ArrayList with an 'initialCapacity'
	 * 
	 * If 'initalCapacity' is non-positive use the default capacity
	 * 
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		// TODO Complete Constructor
		if(initialCapacity > 0) {
		
			data = (T[]) new Object[initialCapacity];
		
		}
		
		if(initialCapacity < 0) {
			
			data = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];		}
		

	}

	@Override
	public void add(T item, int index) {
		// TODO Auto-generated method stub
		data[index] = item;
		size += 1;

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		data.length = 0;

	}

	@Override
	public boolean contains(Object item) {
		// TODO Auto-generated method stub
		for(int i = 0; i < data.length; i++) {
			if(data[i] == item) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if(index > 0 && index <= data.length) {
			return data[index];
		}
		if(index < 0 || index > data.length) {
			return null;
		}
	}

	@Override
	public int indexOf(Object item) {
		// TODO Auto-generated method stub
		int x;
		for(x = 0; x < data.length; x++) {
			if(data[x] == item) {
				return x;
			}
			else {
				x = -1;
			}
		}
		if(x == -1) {
			return x;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(data.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return data.length;
	}
	
}
