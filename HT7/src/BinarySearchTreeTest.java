import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class BinarySearchTreeTest {
    @Test
    public void testCount() {

        BinarySearchTree<String, String> arbolBST = new BinarySearchTree<String, String>(new StrComparator<String>());
        assertEquals(0, arbolBST.count());

        arbolBST.insert("Veinte", "Veinte");
        assertEquals(1, arbolBST.count());

        arbolBST.insert("Treinta", "Treinta");
        arbolBST.insert("Diez", "Diez");
        assertEquals(3, arbolBST.count());

    }

    @Test
    public void testFind() {
        BinarySearchTree<String, String> arbolBST = new BinarySearchTree<String, String>(new StrComparator<String>());

        arbolBST.insert("Treinta", "Treinta");
        arbolBST.insert("Veinte","Veinte");
        arbolBST.insert("Diez", "Diez");
        arbolBST.insert("Cinco", "Cinco");
        arbolBST.insert("Noventa", "Noventa");
        arbolBST.insert("Noventa y Nueve", "Noventa y Nueve");
        arbolBST.insert("Vienticinco", "Vienticinco");

        assertEquals("Treinta", arbolBST.find("Treinta")); //Busco la raiz
        assertEquals("Noventa y Nueve", arbolBST.find("Noventa y Nueve")); //Busco el hijo más derecho
        assertEquals("Cinco", arbolBST.find("Cinco")); //Busco el hijo mas izquierdo
        assertNull(arbolBST.find("Cien")); //El numero 100 no se encuentra
    }

    @Test
    public void testInsert() {
        BinarySearchTree<String, String> arbolBST = new BinarySearchTree<String, String>(new StrComparator<String>());
        assertEquals(0, arbolBST.count());

        arbolBST.insert("Treinta", "Treinta");
        arbolBST.insert("Veinte", "Veinte");
        arbolBST.insert("Diez", "Diez");
        arbolBST.insert("Cinco", "Cinco");
        arbolBST.insert("Noventa", "Noventa");
        arbolBST.insert("Noventa y Nueve", "Noventa y Nueve");
        arbolBST.insert("Vienticinco", "Vienticinco");

        assertFalse(arbolBST.isEmpty());
        

    }

    @Test
    public void testIsEmpty() {
        BinarySearchTree<String, String> arbolBST = new BinarySearchTree<String, String>(new StrComparator<String>());
        assertTrue(arbolBST.isEmpty());

        arbolBST.insert("Hola", "Hola");
        assertFalse(arbolBST.isEmpty());
        
    }

    @Test
    public void testInOrderWalk() {
        BinarySearchTree<String, String> arbolBST = new BinarySearchTree<String, String>(new StrComparator<String>());

        arbolBST.insert("Treinta", "Treinta");
        arbolBST.insert("Veinte", "Veinte");
        arbolBST.insert("Diez", "Diez");
        arbolBST.insert("Cinco", "Cinco");
        arbolBST.insert("Noventa", "Noventa");
        arbolBST.insert("Noventa y Nueve", "Noventa y Nueve");
        arbolBST.insert("Vienticinco", "Vienticinco");

        //Creo el arrayList que debería ser resultante
        ArrayList<String> arregloEsperado = new ArrayList<String>();
        arregloEsperado.add("Cinco");
        arregloEsperado.add("Diez");
        arregloEsperado.add("Noventa");
        arregloEsperado.add("Noventa y Nueve");
        arregloEsperado.add("Treinta");
        arregloEsperado.add("Veinte");
        arregloEsperado.add("Vienticinco");

        //Creo el objeto del recorrido
        SaveInArrayListWalk<String> recorridoEnOrden = new SaveInArrayListWalk<String>();
        arbolBST.InOrderWalk(recorridoEnOrden); //Guardo todos los valores a través del recorrido

        //Verifico que tanto el arreglo esperado como el arreglo generado por el recorrido tengan el mismo tamaño
        assertEquals(arregloEsperado.size(), recorridoEnOrden.getListado().size());

        //Verifico todas las posiciones
        for (int i = 0; i < arregloEsperado.size(); i++){
            assertEquals(arregloEsperado.get(i), recorridoEnOrden.getListado().get(i));
        }
        
    }
}
