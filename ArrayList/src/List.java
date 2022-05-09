public interface List<T> {

	/**
	 * Adds 'item' to this list such that it is located at 'index' (0-based)
	 * 
	 * Do nothing if index is invalid.
	 * 
	 * @param item
	 * @param index
	 */
	public void add(T item, int index);

	/**
	 * Clears all elements out of this list
	 */
	public void clear();

	/**
	 * Checks if this list contains an object equal to 'item'
	 * 
	 * @param item
	 * @return true if the list contains 'item', false otherwise
	 * 
	 */
	public boolean contains(Object item);

	/**
	 * Returns the object in this list located at 'index'
	 * 
	 * Do nothing and return null if 'index' is invalid
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index);

	/**
	 * @param item
	 * @return the index of 'item' if it exists in this list, -1 if it does not
	 *         exist in this list
	 */
	public int indexOf(Object item);

	/**
	 * @return true if there are no items in this list, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * @return the number of items in this list
	 */
	public int size();

}



