package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class ProductoMenuTest {
	
	private static Restaurante rest;

	private static ArrayList<ProductoMenu> menu;
	
	private static ProductoMenu productoA;
	
	private static ProductoMenu productoB;
	
	
	@BeforeAll
    static void setUp( ) throws Exception
    {
		File combo = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		File menus = new File("data/menu.txt");
        rest = new Restaurante();
        rest.cargarInformacionRestaurante(ingredientes, menus, combo);
        menu = rest.getMenuBase();
        productoA = menu.getFirst();
        productoB = new ProductoMenu("Mac & Cheese",0);
        
    }

	@Test
	void testCargarInfo() {
		assertEquals(menu.size(), 22, "No cargo la informacion del menu correctamente");
	}
	
	
	@Test
	void testGetInfo() {
		assertEquals(productoB.getNombre(), "Mac & Cheese", "No carga el nombre correcto");
		assertEquals(productoA.getNombre(), "corral", "No carga el nombre correcto desde el archivo");
		assertEquals(productoB.getPrecio(), 0, "No carga el precio correcto en un combo vacio");
		assertEquals(productoA.getPrecio(), 14000, "No carga el precio correcto");
	}
	
	@Test
	void testGetFactura() {
		String facturaA = productoA.generarTextoFactura();
		String facturaB = productoB.generarTextoFactura();
		assertEquals(facturaB.split("\n").length, 2, "La factura no contiene la informacion completa");
		assertEquals(facturaA.split("\n").length, 2, "La factura desde el archivo no contiene la informacion completa");
	}

}
