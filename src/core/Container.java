package core;

import core.items.Item;

public class Container{
	private final int CAPACITY;
	private Item[] contents;
	
	private int currentIndex = 0;

	public Container(int inventoryCapacity) {
		this.CAPACITY =  inventoryCapacity;
		this.contents = new Item[inventoryCapacity];
	}

	public boolean isFull() {
		return this.CAPACITY == this.currentIndex +1;
	}
	
	public boolean add(Item e) {
		if(this.isFull()) {
			return false;
		}else {
			e.setLocation(this);
			this.contents[this.currentIndex] = e;
			this.currentIndex++;
			return true;
		}
	}
	
	public boolean remove(Item e) {
		if(!this.contains(e)) {
			return false;
		}else {
			this.contents[this.find(e)].setLocation(null);
			this.contents[this.find(e)] = null;
			return true;
		}
	}
	
	public boolean contains(Item e) {
		return this.find(e) != -1;
	}
	
	public int find(Item e) {
		/**Returns -1 if not given item is not found (works by reference); its index in the container otherwise**/
		int o = 0;
		for(Item i : this.contents) {
			if(i == e) {
				return o;
			}
			o++;
		}
		
		return -1;
	}
	
	public Item[] toArray() {
		return this.contents.clone();
	}
}
