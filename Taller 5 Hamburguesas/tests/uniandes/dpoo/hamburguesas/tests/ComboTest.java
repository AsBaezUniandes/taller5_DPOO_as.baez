package uniandes.dpoo.hamburguesas.tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Combo;

class ComboTest {
	
	private static Restaurante rest;
	
	private static ArrayList<Combo> combos;
	
	private Combo comboA;
	
	private Combo comboB;
	
	
	@BeforeAll
    static void setUpInicial( ) throws Exception
    {
		File combo = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		File menu = new File("data/menu.txt");
        rest = new Restaurante();
        rest.cargarInformacionRestaurante(ingredientes, menu, combo);
        combos = rest.getMenuCombos();
        
    }
	
	@BeforeEach
	void Setup() {
		comboA = rest.getMenuCombos().getFirst();
		ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>();
		comboB = new Combo("Combo ninos", 0.1, items);
	}
	
	@Test
	void testCargoCombos() {
		assertEquals(combos.size(), 4, "No se cargo la infomacion del archivo correctamente");
	}
	

	@Test
	void testGetInfo() {
		assertEquals(comboB.getNombre(), "Combo ninos", "No carga el nombre correcto");
		assertEquals(comboA.getNombre(), "combo corral", "No carga el nombre correcto desde el archivo");
		assertEquals(comboB.getPrecio(), 0, "No carga el precio correcto en un combo vacio");
		assertEquals(comboA.getPrecio(), 22050, "No carga el precio correcto");
	}
	
	@Test
	void testGetFactura() {
		String facturaA = comboA.generarTextoFactura();
		String facturaB = comboB.generarTextoFactura();
		assertEquals(facturaB.split("\n").length, 3, "La factura no contiene la informacion completa");
		assertEquals(facturaA.split("\n").length, 3, "La factura desde el archivo no contiene la informacion completa");
	}

}
