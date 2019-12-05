
package Ex1;

import java.util.Comparator;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}

	public Monom(){
		this.set_coefficient(0);
		this.set_power(0);
	} 

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/*
	 * this method represent the monom class
	 * convert string to monom devided to coeffiecient and power    
	 */
	public Monom(String s) {
		if(s.contains("(") || s.contains(")")) {
			throw new RuntimeException("Error. Polynom cannot contains the char ( or ) ");
		}
		else {
			Monom th = init_from_string(s);
			this.set_coefficient(th.get_coefficient());
			this.set_power(th.get_power());
		}
	}

	private Monom init_from_string(String s) {
		if(s.startsWith("+")) {
			s = s.replaceFirst("\\+", "");
		}
		if(s == null) {
			throw new RuntimeException("String is Null.");
		}
		s = s.replaceAll("\\s",""); //to avoid spaces
		double  coef = 1;
		int pow = 0;
		Monom ans = new Monom();
		if(s.contains("x")) {
			int ind = s.indexOf("x");
			String co = s.substring(0, ind);
			if(co.equals("-")) {
				co = "-1";
			}
			try{
				double c = Double.parseDouble(co);
				coef = c;
			}
			catch(Exception e) {
				coef = 1;
			}
			if(s.contains("^")){
				int powerIndex = s.indexOf("^");
				String po = s.substring(powerIndex +1);
				try{
					int p = Integer.parseInt(po);
					pow = p;
				}
				catch(Exception e) {
					pow = 1;
				}
			}
			else {
				pow = 1;
			}
		}
		else {
			coef = Double.parseDouble(s); //just a  number.
		}
		ans = new Monom(coef, pow);	
		return ans;	
	}

	/*
	 * add monom m to this monom only if the powers are equals
	 */
	public void add(Monom m) {
		if(m.isZero()) {
			this._coefficient = this._coefficient;
		}
		else if(this.isZero()) {
			this._coefficient = m._coefficient;
			this._power = m._power;
		}
		else{
			if(m.get_power() == this._power){
				this._coefficient += m.get_coefficient();
			}
			else {
				throw new IllegalArgumentException("illegal move");
			}
		}
	}

	/*
	 * multiply monom d with this monom
	 */
	public void multipy(Monom d) {
		if (d.get_coefficient()==0 || this.get_coefficient()==0) {
			this.set_coefficient(0);
			this.set_power(0);
		}
		else
		{
			d._coefficient=this._coefficient*d._coefficient;
			d._power=this._power+d._power;
		}
	}

	/*
	 * print the monom
	 */
	public String toString() {
		String ans = "";
		if(this._coefficient==0)
			return "0";
		if(this._power==0)
			ans=Double.toString(this._coefficient);
		else
			ans=Double.toString(this._coefficient) + "x^"+Integer.toString(this._power);
		return ans;
	}
	
	//substruct
	
	public void substract(Monom m2){
		if(m2.isZero()) {
			this._coefficient = this._coefficient;
		}
		else if(this.isZero()) {
			this._coefficient = m2._coefficient;
			this._power = m2._power;
		}
		else {
			if(m2.get_power() == this._power){
				this._coefficient -= m2.get_coefficient();
			}
			else {
				throw new IllegalArgumentException("illegal move");
			}
		}
	}
	
	public boolean equals(Monom m) { 
		if(this._coefficient==m._coefficient && this._power==m._power) {
			return true;
		}
		else if(this._power==m._power) {
			if(Math.abs(this._coefficient) - Math.abs(m._coefficient) <= EPSILON){
				return true;
			}
		}
			return false;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}


}
