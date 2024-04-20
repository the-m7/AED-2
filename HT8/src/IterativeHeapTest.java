import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IterativeHeapTest {

	@Test
	public void test() {
		HeapUsingIterativeBinaryTree<Integer, String> myHeap = new HeapUsingIterativeBinaryTree<Integer, String>(new ComparadorNumeros<Integer>());
		myHeap.Insert(20, "veinte");
		myHeap.Insert(10, "diez");
		myHeap.Insert(15, "quince");
		myHeap.Insert(5, "cinco");
		myHeap.Insert(10, "diez");
		myHeap.Insert(15, "quince");
		
		assertEquals("cinco", myHeap.remove());
		assertEquals("diez", myHeap.remove());
		assertEquals("diez", myHeap.remove());
		assertEquals("quince", myHeap.remove());
		assertEquals("quince", myHeap.remove());
		assertEquals("veinte", myHeap.remove());
		
		
	}

	@Test
	public void test_Process() {
		IHeap<Integer, Process> myHeap = new HeapUsingIterativeBinaryTree<Integer, Process>(new ComparadorNumeros<Integer>());

		String[] datos = {"vi,juan02,0",
			"ls,maria30,-20",
			"firefox,rosa20,5",
			"cat,juan02,5"};

		for(String Linea : datos){
			String[] Pals = Linea.split(",");
			System.out.println(Linea);
			String name = Pals[0];
			String user = Pals[1];
			Integer ni = Integer.parseInt(Pals[2]);

			Process proc = new Process(name, user, ni);

			myHeap.Insert(proc.getPr(), proc);
		}

		//assertEquals("Auto marca: Toyota modelo 2000", myHeap.remove().toString());
		//assertEquals("Auto marca: Yamaha modelo 2008", myHeap.remove().toString());
		//assertEquals("Auto marca: Hino modelo 1995", myHeap.remove().toString());
		assertEquals("ls", myHeap.remove().getName());
		assertEquals("vi", myHeap.remove().getName());
		assertEquals("firefox", myHeap.remove().getName());
		assertEquals("cat", myHeap.remove().getName());
		
		
		
	}

}
