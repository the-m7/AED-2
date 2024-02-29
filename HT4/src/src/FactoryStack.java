package src;

public class FactoryStack {
	public static IStack<String> getInstance_(int n){
		return new StackVector<String>();
		
	}

	public static <T> IStack<T> getInstance(int n){
		return new StackVector<T>();
		
	}
}
