package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pkg.Cuenta;

class testCuenta {

	static Cuenta cta12345, cta67890, c;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		c = new Cuenta("123", "Antonio", 50.0);
		cta12345 = new Cuenta("12345", "Pepe", 50.0);
		cta67890 = new Cuenta("67890", "Manuel", 0.0);	
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

	@Test
	void testIngresar() throws Exception{
		c.ingreso(50.0);
		assertEquals(100.0, c.getSaldo());
	}
	
	@Test
	void testReintegrar() throws Exception{
		c.reintegro(50.0);
		assertEquals(50.0, c.getSaldo());
	}
	
	@Test
	void test0014() throws Exception{
		cta12345.reintegro(200.0);
		cta67890.reintegro(350.0);
		cta12345.ingreso(100.0);
		try {
			cta67890.reintegro(200.0);
			fail("No ha saltado bien la excepcion");
		}catch(Exception e) {
			
		}
		
		cta67890.reintegro(150.0);
		cta12345.reintegro(200.0);
		cta67890.ingreso(50.0);
		try {
			cta67890.reintegro(100.0);
			fail("No ha saltado bien la excepcion");
		}catch(Exception e){
			
		}
		
		assertEquals(-250.0, cta12345.getSaldo());
		assertEquals(-450.0, cta67890.getSaldo());
	}
}
