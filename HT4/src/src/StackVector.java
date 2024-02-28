package src;

import java.util.Vector;

public class StackVector<T> implements IStack<T> {

	private Vector<T> Vector = new Vector<T>();
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return Vector.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return Vector.size()==0;
	}

	@Override
	public void push(T value) {
		Vector.add(value);
		
	}

	@Override
	public T pop() {
		T ele = Vector.lastElement();
		Vector.remove(count()-1);
		return ele;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return Vector.lastElement();
	}

}
