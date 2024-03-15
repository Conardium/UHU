package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import pkg.Empleado;


class EmpleadoTest{

	private float ventaMes;
	private String tipoEmpleado;
	private float horasExtras;
	private float nominaBruta;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//--------------- Tests del metodo CalculoNominaBruta() --------------------
	@Test
	void testCalculoNominaBrutaVendedor() {
		//fail("Not yet implemented");
		float expected = 2600;
		ventaMes = 1000;
		horasExtras = 0;
		tipoEmpleado="Vendedor";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaBrutaEncargado() {
		//fail("Not yet implemented");
		float expected = 2100;
		ventaMes = 1000;
		horasExtras = 0;
		tipoEmpleado="Encargado";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaBrutaVentasMes900() {
		//fail("Not yet implemented");
		float expected = 2000;
		ventaMes = 900;
		horasExtras = 0;
		tipoEmpleado="Encargado";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaBrutaVentasMes1200() {
		//fail("Not yet implemented");
		float expected = 2100;
		ventaMes = 1200;
		horasExtras = 0;
		tipoEmpleado="Encargado";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaBrutaVentasMesMayor() {
		//fail("Not yet implemented");
		float expected = 2200;
		ventaMes = 1600;
		horasExtras = 0;
		tipoEmpleado="Encargado";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaBrutaConHorasExtras() {
		//fail("Not yet implemented");
		float expected = 2600;
		ventaMes = 0;
		horasExtras = 20;
		tipoEmpleado="Encargado";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaBrutaSinHorasExtras() {
		//fail("Not yet implemented");
		float expected = 2000;
		ventaMes = 0;
		horasExtras = 0;
		tipoEmpleado="Encargado";
		float actual = Empleado.calculoNominaBruta(tipoEmpleado, ventaMes, horasExtras);
		assertEquals(expected, actual);
	}
	
	//--------------- Tests del metodo CalculoNominaNeta() --------------------
	@Test
	void testCalculoNominaNeta0() {
		//fail("Not yet implemented");
		float expected = 2000;
		nominaBruta=2000;
		float actual = Empleado.calculoNominaNeta(nominaBruta);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaNeta15() {
		//fail("Not yet implemented");
		float expected = 1870;
		nominaBruta=2200;
		float actual = Empleado.calculoNominaNeta(nominaBruta);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculoNominaNeta18() {
		//fail("Not yet implemented");
		float expected = 2132;
		nominaBruta=2600;
		float actual = Empleado.calculoNominaNeta(nominaBruta);
		assertEquals(expected, actual);
	}

}
