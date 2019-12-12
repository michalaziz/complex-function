package Ex1;
import java.util.Comparator;

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
	/**
	 * Default constructor.
	 * setting the coefficient and power to zero.
	 */
	public Monom(){
		this.set_coefficient(0);
		this.set_power(0);
	}
	public Monom(double a, int b){
		if(b<0) {
			throw new RuntimeException("error cannot init negativ power in monom"+get_power());
		}
		else {
			this.set_coefficient(a);
			this.set_power(b);
		}
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
		if(this.get_power()==0) {
			Monom m= new Monom(0,0);
			return m;
		}
		if (this.get_coefficient()==0) {
			Monom m= new Monom(0,0);
			return m;
		}
		Monom m = new Monom(this);
		m.set_coefficient(this.get_coefficient()*this.get_power());
		m.set_power(this.get_power()-1);
		return m;
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 

	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************

	public Monom(String s) { 
		s = s.toLowerCase();
		if(s.indexOf('x') != s.lastIndexOf('x'))
			throw new RuntimeException("'" + s + "' isn't a polynom and is"
					+ " an invalid coefficient");
		//find coefficient
		double c;
		if(!s.contains("x"))
			c = parseDouble(s);	
		else if(s.indexOf("x") == 0)
			c = 1;
		else if(s.indexOf('x') == 1) {
			if(s.charAt(0) == '-')
				c = -1;
			else if(s.charAt(0) == '+')
				c = 1;
			else 
				c = parseDouble(s.substring(0, s.indexOf('x')));
		} else 
			c = parseDouble(s.substring(0, s.indexOf('x')));

		// find power
		int p;
		if(!s.contains("x"))
			p = 0;
		else if(!s.contains("^")) {
			if(s.indexOf('x') != s.length()-1)
				throw new RuntimeException("'" +s + "' isn't a polynom and is"
						+ " an invalid coefficient");
			p = 1;
		}else
			p = parseInt(s.substring(s.indexOf('x')+2));
		// set coefficient and power
		this.set_coefficient(c);
		this.set_power(p);
	}


	private double parseDouble(String s) {
		return Double.parseDouble(s);
	}

	private int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			throw new RuntimeException("'" +s + "' isn't a integer number and is"
					+ " an invalid power");		
		}
	}

	/* this function make sum add 2 monom (the sum of the 2 monoms) if they have diferent power it send exeption
	 * @param f2 represent the new monom that the function will add to the monom
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

	public void multipy(Monom d) {
		if (d.get_coefficient()==0 || this.get_coefficient()==0) {
			this.set_coefficient(0);
			this.set_power(0);
		}
		else {
			this.set_coefficient(this.get_coefficient() * d.get_coefficient());
			this.set_power( this.get_power() + d.get_power() );
		}
	}

	/*Substract f2 from this Monom.
	 * @param f2.
	 */
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

	/*test if this Monom is logically equal to m.
	 * @param m - input monom
	 * @return boolean
	 */
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
	public String toString() {
		if(get_coefficient() == 0) {
			this._power = 0;
			return "0";
		}
		if(get_coefficient() != 0 && get_power() == 0) {
			return get_coefficient()+"";
		}
		if(get_coefficient() != 0 && get_power() == 1) {
			return get_coefficient()+"x";
		}
		else {
			return get_coefficient()+"x^"+get_power();
		}
	}

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
		return new Monom(s);
	}
	@Override
	public function copy() {
		return new Monom(this);
	}


}