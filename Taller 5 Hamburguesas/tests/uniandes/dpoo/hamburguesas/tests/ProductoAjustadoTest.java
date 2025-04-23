package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class ProductoAjustadoTest {
	
	private static Restaurante rest;
	

	private static ProductoAjustado productoA;
	
	private static ProductoAjustado productoB;
	
	
	@BeforeAll
    static void setUp( ) throws Exception
    {
		File combo = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		File menu = new File("data/menu.txt");
        rest = new Restaurante();
        rest.cargarInformacionRestaurante(ingredientes, menu, combo);
        ProductoMenu proB = new ProductoMenu("Mac & Cheese",0);
        productoB = new ProductoAjustado(proB);
        productoA = new ProductoAjustado(rest.getMenuBase().getFirst());
        
    }

	
	@Test
	void testAGetInfo() {
		assertEquals(productoB.getNombre(), "Mac & Cheese", "No carga el nombre correcto");
		assertEquals(productoA.getNombre(), "corral", "No carga el nombre correcto desde el archivo");
		assertEquals(productoB.getPrecio(), 0, "No carga el precio correcto en un combo vacio");
		assertEquals(productoA.getPrecio(), 14000, "No carga el precio correcto");
	}
	
	@Test
	void testBModificarPorducto() {
		productoA.agregarIngrediente(rest.getIngredientes().getFirst());
		assertEquals(productoA.getPrecio(), 15000, "No carga el precio correcto");
		productoA.eliminarIngrediente(rest.getIngredientes().getLast());
		assertEquals(productoA.getPrecio(), 12500, "No carga el precio correcto");
		Ingrediente i = new Ingrediente("wagyu", 0);
		Ingrediente ii = new Ingrediente("Mac", 12000);
		productoB.agregarIngrediente(i);
		assertEquals(productoB.getPrecio(), 0, "No carga el precio correcto");
		productoB.eliminarIngrediente(ii);
		assertEquals(productoB.getPrecio(), -12000, "No carga el precio correcto");
	}
	
	@Test
	void testCGetFactura() {
		String facturaA = productoA.generarTextoFactura();
		String facturaB = productoB.generarTextoFactura();
		assertEquals(facturaB.split("\n").length, 1, "La factura no contiene la informacion completa");
		assertEquals(facturaA.split("\n").length, 1, "La factura desde el archivo no contiene la informacion completa");
	}

}
