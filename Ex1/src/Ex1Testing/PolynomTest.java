package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

import org.junit.jupiter.api.Test;

class PolynomTest {

	@Test
	void testFX() {
		Polynom a=new Polynom("4x^3+8x");
		double res;
		for(int i=0; i<5; i++)
		{
			a.f(i);
			res=4*(Math.pow(i, 3))+8*i;
			assertEquals(a, res);

		}
	}
	@Test
	void testAddP() {
		Polynom_able a=new Polynom("2x^2+6");
		Polynom_able b=new Polynom("2x^2+5");
		Polynom_able res=new Polynom("4x^2+11");
		a.add(b);
		assertEquals(a, res);
	}
	@Test
	void testAddM() {
		Polynom_able a=new Polynom("2x^2+6");
		Monom b= new Monom(1,2);
		Polynom_able res=new Polynom("3x^2+6");
		a.add(b);
		assertEquals(a, res);
	}
	@Test
	void testSub() {
		Polynom_able a=new Polynom("4x^2+6");
		Polynom_able b=new Polynom("4x^2+6");

		Polynom_able a1=new Polynom("6x^2+6");
		Polynom_able b1=new Polynom("4");

		a.substract(b);
		assertEquals("0",a.toString());
		a1.substract(b1);
		assertEquals("6x^2+2", a1.toString());
	}
	@Test
	void testZero() {
		Polynom_able a=new Polynom("4x^2+6");
		Polynom_able b=new Polynom("4x^2+6");
		Polynom_able c=new Polynom("0");

		a.substract(b);

		assertEquals("0", c.toString());
		assertEquals("0", a.toString());

	}

	@Test
	void testRoot() {
		Polynom_able a = new Polynom("-5x^2+25x+5");
		try 
		{
			a.root(6, 8, 0.001);
			fail("same side of X");
		}
		catch(Exception e)
		{
			assertTrue(true);
		}
		double x = a.root(4, 8, 0.001);
		assertEquals(5, Math.round(x));
	}
	@Test
	void testDerivative() {
		Polynom a= new Polynom("8x^2+6x");
		Polynom b= new Polynom("16x^2+6");
		Polynom_able c= (Polynom) a.derivative();

		assertEquals(c.toString(),b.toString());
	}
	@Test
	void testCopy() {
		String[] s= {"5x^2+4x+1"  , "x^4-x^2" ,"1.5x^6-6.6x+3"};
		for (int i = 0; i < s.length; i++) {
			Polynom a = new Polynom(s[i]);
			Polynom b=(Polynom) a.copy();
			assertEquals(a,b);
		}
	}

	@Test
	void testArea() {
		//Test equal
		Polynom a = new Polynom("-x^2+4") ;
		a.area(-4, 0, 0.001);
		assertEquals("-1.0x^2 + 4.0", a.toString());

		//Test Not equal
		Polynom b = new Polynom("-x^2+4+2") ;
		b.area(-4, 0, 0.001);
		assertNotEquals("-1.0x^2 + 4.0", b.toString());
	}
	@Test
	public void testInitFromString() {
		String s = "2.0x^4 + 6.2x -8.0";
		Polynom a = new Polynom("2x^4+6.2-8");
		Polynom b = new Polynom();
		Polynom c = (Polynom) b.initFromString(s);
		assertEquals(c.toString(),s);
	}

	@Test
	void testMulP() {
		Polynom_able a=new Polynom("4x^2+6");
		Polynom_able b=new Polynom("-x+1");
		a.multiply(b);
		assertEquals("-4x^3+4x2-6x+6",a.toString());

		Polynom_able a1=new Polynom("6x^2+6x-5");
		Polynom_able b1=new Polynom("0");
		a1.multiply(b1);
		assertEquals("0",a1.toString());
	}


	@Test
	void testMulM() {
		Polynom_able a=new Polynom("4x^2+6");
		Monom b=new Monom (4,2);
		a.multiply(b);
		assertEquals("16x^4+24x^2",a.toString());
	}
	@Test
	void testEqual() {
		Polynom_able a=new Polynom("4x^2+6");
		Polynom_able b=new Polynom("5x^2+6");
		Polynom_able c=new Polynom("4.9999999999x^2+5.9999999999");
		if(a.equals(b))
			fail("not equal");
		if(!(b.equals(c)))
			fail("equal");
	}
}
