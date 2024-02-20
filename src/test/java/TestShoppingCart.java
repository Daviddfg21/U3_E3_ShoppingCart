import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.soterohernandez.Product;
import es.soterohernandez.ProductNotFoundException;
import es.soterohernandez.ShoppingCart;

public class TestShoppingCart {
	ShoppingCart sc1;
	Product p1;

	@BeforeEach
	void iniciar() {
		sc1 = new ShoppingCart();
		p1 = new Product("Patatas", 2.3);
	}

	@Test
	// Comprueba que el carro se crea con 0 elementos
	void testConstructor() {
		sc1 = new ShoppingCart();
		assertEquals(sc1.getItemCount(), 0);
	}

	@Test
	// Comprueba que cuando el carro está vacío tiene 0 elementos
	void testVacio() {
		sc1.addItem(p1);
		sc1.empty();
		assertTrue(sc1.getItemCount() == 0);
	}

	@Test
	/*
	 * Comprueba que cuando se añade un producto el número de elementos se
	 * incrementa
	 */
	void aniadeProducto() {
		int antes = sc1.getItemCount();
		sc1.addItem(p1);
		int despues = sc1.getItemCount();
		assertThat(despues, greaterThan(antes));
	}

	@Test
	/*
	 * Cuando se añade un producto, el balance nuevo debe ser la suma del balance
	 * anterior y el precio del producto añadido.
	 */
	void balance() {
		double balAntes = sc1.getBalance();
		sc1.addItem(p1);
		double balDespues = sc1.getBalance();
		assertThat(balDespues, is(balAntes + p1.getPrice()));
	}

	@Test
	/*
	 * Comprueba que cuando se añade un producto el número de elementos se
	 * decrementa
	 */
	void quitaProducto() throws ProductNotFoundException {
		sc1.addItem(p1);
		int antes = sc1.getItemCount();
		sc1.removeItem(p1);
		int despues = sc1.getItemCount();
		assertThat(despues, lessThan(antes));
	}

	@Test
	/*
	 * Cuando se intenta eliminar un producto que no está en el carro, se debe
	 * lanzar una excepción ProductNotFoundException. Pista: inserta la llamada en
	 * un bloque try y pon un método fail() después de la llamada a removeItem()
	 */
	void testFalloEliminacion() {
		try {
			sc1.removeItem(p1);
			fail("El carro está vacío");
		} catch (ProductNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
