package Ex1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.swing.Spring;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	public ArrayList<Monom> _Polynom = new ArrayList<Monom>();
	private Monom_Comperator cmp = new Monom_Comperator();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this._Polynom=new ArrayList<Monom>();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	/*
	 * convert 
	 */
	public Polynom(String s) {
		s = s.replaceAll(" ", "");
		String[] monoms = s.split("(?=[-,+])");
		for (int i = 0; i < monoms.length; i++) 
			this.add(new Monom(monoms[i]));
	}
	@Override
	public double f(double x) {
		double ans=0;
		Iterator<Monom> Iter=this.iteretor();
		while (Iter.hasNext())
		{
			ans+=Iter.next().f(x);
		}
		return ans;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> Iter=p1.iteretor();
		while (Iter.hasNext())
		{
			this.add(Iter.next());
		}
	}

	@Override
	public void add(Monom m1) {
		//add a monom to a polynom.

		for(int i=0 ; i < this._Polynom.size();i++) {
			if(_Polynom.get(i).get_power() == m1.get_power()) {
				Monom m2=new Monom(this._Polynom.get(i));
				m2.add(m1);
				this._Polynom.set(i,m2);
				return;
			}

		}

		_Polynom.add(m1);
		this._Polynom.sort(cmp);
		return;

	}
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext())
		{
			Monom m2=iter.next();
			Monom temp = new Monom(m2.get_coefficient()*-1,m2.get_power());
			add(temp);
		}
		//		Monom_Comperator cmpByPower=new Monom_Comperator();
		//		this._Polynom.sort(cmpByPower);

	}

	@Override
	public void multiply(Polynom_able p1) 
	{
		Iterator<Monom> It=this.iteretor();
		Polynom temp=new Polynom();
		while(It.hasNext())
		{
			Monom a=It.next();
			Iterator<Monom> ItP1=p1.iteretor();
			while(ItP1.hasNext())
			{
				Monom b=new Monom(a);
				b.multipy(ItP1.next());
				temp.add(b);
			}
		}	
		this._Polynom=((Polynom) temp.copy())._Polynom;
	}


	public boolean equals(Polynom_able p1) 
	{
		Iterator<Monom> It=this._Polynom.iterator();
		Iterator<Monom> ItP1=p1.iteretor();
		if(It.hasNext()&&ItP1.hasNext())
		{
			while (It.hasNext())
			{
				if(!It.next().equals(ItP1.next()))
				{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isZero() {
		Iterator<Monom> iter=this.iteretor();
		Monom temp;
		while(iter.hasNext())
		{
			temp=iter.next();
			if(temp.get_coefficient()!=0)
				return false;
		}

		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		double mid=(x0+x1)/2 , interval=x1-x0;
		if(x0>x1)
		{
			throw new RuntimeException("ERR: x0 need to be smaller then x1");
		}
		else
			while(interval>=eps)
			{
				mid=(x0+x1)/2;
				if(f(mid)==0.0)
					return mid;
				else

					if(f(x0)*f(mid)>=0)
						x0=mid;
					else
						x1=mid;		
			}
		return mid;
	}

	@Override
	public Polynom_able copy() {
		Polynom_able temp = new Polynom();
		Monom mon;
		Iterator<Monom> iter=this.iteretor();
		while(iter.hasNext())
		{
			mon=iter.next();
			temp.add(mon);
		}
		return temp;
	}

	@Override
	public Polynom_able derivative() {
		Iterator<Monom> iter=this.iteretor();
		Polynom_able temp = new Polynom();
		while(iter.hasNext())
		{
			iter.next().derivative();
		}
		return temp;
	}

	@Override
	public double area(double x0, double x1, double eps) 
	{
		double temp;
		double sum = 0.0;
		if(x0>x1) {
			temp=x0;
			x0=x1;
			x1=temp;
		}
		for (Double i=x0;i<x1;i=i+eps)
		{
			if(f(i)>=0)
			{
				sum+=((f(i)+f(i+eps))/2)*eps;	
			}
		}
		return sum;
	}
	/**
	 * because in the first area function we can't find a negative area,
	 * we create a new area function that calculate the negative area in the function.
	 */
	public double areaNegative(double x0, double x1, double eps) 
	{
		double temp;
		double sum = 0.0;
		if(x0>x1) {
			temp=x0;
			x0=x1;
			x1=temp;
		}
		for (Double i=x0;i<x1;i=i+eps)
		{
			if(f(i)<=0)
			{
				sum+=((f(i)+f(i+eps))/2)*eps;	
			}
		}
		return Math.abs(sum);
	}

	@Override
	public Iterator<Monom> iteretor() {
		return this._Polynom.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> iter=this.iteretor();
		Polynom poly=new Polynom();
		Monom temp;
		while(iter.hasNext())
		{
			temp=iter.next();
			temp.multipy(m1);
			poly.add(temp);
		}
		this._Polynom=((Polynom) poly.copy())._Polynom;
	}

	public String toString(){
		//printing the polynom as a string.
		String ans = "";
		Iterator <Monom> i = this.iteretor();
		while(i.hasNext()) {
			ans+=i.next().toString();
			if(i.hasNext()) ans += "+";
		}
		ans = ans.replaceAll("\\s", "");
		ans = ans.replaceAll(Pattern.quote("++"),"+");
		ans = ans.replaceAll(Pattern.quote("++-"),"-");
		ans = ans.replaceAll(Pattern.quote("+-"),"-");
		ans = ans.replaceAll(Pattern.quote("--"),"+");

		return ans;
	}
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}


}
