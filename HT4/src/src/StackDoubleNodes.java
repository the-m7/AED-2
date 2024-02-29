package src;

public class StackDoubleNodes<T> implements IStack<T> {

    private int size;
    private DNode<T> head;
    private DNode<T> tail;

    public StackDoubleNodes(){
        size = 0;
        head = null;
        tail = null;
    }
	
	@Override
	public int count() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public void push(T value) {
		DNode<T> temp = new DNode<T>(value);
		if(isEmpty()) {
			head = temp;
			tail = temp;
		}else {
			temp.next = head;
			head.prev = temp;
			head = temp;
		}
		size++;
	}

	@Override
	public T pop() {
		
		if(!isEmpty()) {
			DNode<T> temp = head;
			if(size==1) {
				head = null;
				tail = null;
			}else {
				head = head.next;
				head.prev = null;
			}
			size--;
			return temp.value;
		}
		
		return null;
	}

	@Override
	public T peek() {
		if(!isEmpty()) {
			return head.value;
		}
		return null;
	}

}
