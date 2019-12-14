package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

import org.junit.jupiter.api.Test;

class MonomTest {

	
	
	@Test
	void testFx()
	{
		Monom a= new Monom(6,3);
		double f,Fres;
		for(int i=0; i<10; i++)
		{
			f=a.f(i);
			Fres=6*(Math.pow(i, 3));
			assertEquals(f, Fres);
		}	
	}
	
	@Test
	void testAdd() {
		Monom a1=new Monom (2,2);
		Monom a2=new Monom (8,3);
		
		Monom b1=new Monom (6,2);
		Monom b2=new Monom (9,3);
		
		Monom res1=new Monom(8,2);
		Monom res2=new Monom(17,2);
		
		a1.add(b1);
		a2.add(b2);
		
		assertEquals(a1.toString(), res1.toString());
		assertEquals(a2.toString(), res2.toString());
		
	}
	
	@Test
	void testMul() {
		Monom a =new Monom(3,2);
		Monom b =new Monom(1,1);
		Monom res =new Monom(3,3);
		
		a.multipy(b);
		assertEquals(a.toString(), res.toString());
	}
	
	@Test
	void testIsZero() {
		Monom a=new Monom (0,6);
		Monom b= new Monom(9,9);
		if(!(a.isZero()))
			fail("fail");
		if(b.isZero())
			fail("fail");	
	}
	@Test
	void testToString() {
		Monom a=new Monom(4,5);
		String s="4x^5";
		assertEquals(a.toString(), s);
	}
	
	@Test
	void testInitFromString() {
		String s="4x^3";
		Monom a= new Monom (4,3);
		Monom b= new Monom(s);
		assertEquals(a, s);
	}
	
	
	@Test
	void testDerivative() {
		Monom a1=new Monom (2,2);
		Monom a2=new Monom (8,3);

		
		Monom d1=(Monom)a1.derivative();
		Monom d2=(Monom)a2.derivative();

		
		Monom res1=new Monom (4,1);
		Monom res2=new Monom (24,2);

		
		assertEquals(d1.toString(), res1.toString());
		assertEquals(d2.toString(), res2.toString());

	}
	@Test
	void testCopy() {
		Monom a= new Monom(15,4);
		Monom b= (Monom) a.copy();
		assertEquals(b.toString(), a.toString());
	}

}
