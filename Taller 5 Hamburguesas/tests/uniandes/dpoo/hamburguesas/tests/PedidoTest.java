package uniandes.dpoo.hamburguesas.tests;


import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class PedidoTest {
	
	private static Restaurante rest;

	private static Pedido pedido;
	
	
	@BeforeAll
    static void setUp( ) throws Exception
    {
		File combo = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		File menu = new File("data/menu.txt");
        rest = new Restaurante();
        rest.cargarInformacionRestaurante(ingredientes, menu, combo);
        rest.iniciarPedido("cliente", "el arbol");
		pedido = rest.getPedidoEnCurso();
        
    }

	

	@Test
	void testPedidoYaEnCurso() {
		String esperado = "Ya existe un pedido en curso, para el cliente " + "cliente" + " así que no se puede crear un pedido para " + "Claudia";
	    Throwable exception = assertThrows(YaHayUnPedidoEnCursoException.class, () -> rest.iniciarPedido("Claudia", "el arbol mas grande"));
	    assertEquals(esperado, exception.getMessage());
	}
	
	
	@Test
	void testAGetInfo() {
		assertEquals(pedido.getNombreCliente(), "cliente", "No carga el nombre correcto");
		assertEquals(pedido.getPrecioTotalPedido(), 0, "No carga el precio para un pedido vacio");
	}
	
	@Test
	void testBAgregarProductos() {
		assertEquals(pedido.getPrecioTotalPedido(), 0, "No carga el precio para un pedido vacio");
		pedido.agregarProducto(rest.getMenuBase().getFirst());
		assertEquals(pedido.getPrecioTotalPedido(), 16660, "No carga el precio para un pedido");
		pedido.agregarProducto(rest.getMenuCombos().getFirst());
		assertEquals(pedido.getPrecioTotalPedido(), 42899, "No carga el precio para un pedido con combo");
		ProductoAjustado ajustado = new ProductoAjustado(rest.getMenuBase().getLast());
		ajustado.agregarIngrediente(rest.getIngredientes().getFirst());
		ajustado.eliminarIngrediente(rest.getIngredientes().getLast());
		pedido.agregarProducto(ajustado);
		assertEquals(pedido.getPrecioTotalPedido(), 47064, "No carga el precio para un pedido con combo y producto ajustado");
	}
	

}
