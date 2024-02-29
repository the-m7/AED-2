package src;

public class FactoryStack {
	public IStack<Integer> getInstance(int n){
		return new StackVector<Integer>();
		
	}
}
