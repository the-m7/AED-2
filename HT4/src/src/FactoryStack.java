package src;

public class FactoryStack {
	public static IStack<String> getInstance_(int n){
		return new StackVector<String>();
		
	}

	public static <T> IStack<T> getInstance(int n){
		switch(n) {
		case 0:
			return new StackVector<T>();
		case 1:
			return new StackDoubleNodes<T>();
		}
		return null;
		
	}
}
