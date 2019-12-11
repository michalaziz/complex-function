package Ex1;

import java.nio.file.OpenOption;

public class ComplexFunction implements complex_function {

	function left;
	function right;
	Operation op=Operation.None;

	//Default ctor
	public ComplexFunction()
	{
		this.left=null;
		this.right=null;
	}

	//string ctor
	public ComplexFunction(String o, function l, function r)
	{
		this.left=l;
		this.right=r;
		this.op=stringToOp(o);
	}

	//operator ctor
	public ComplexFunction(Operation o, function l, function r)
	{
		this.left=l;
		this.right=r;
		this.op=o;
	}

	//no right fuction ctor
	public ComplexFunction(function l)
	{
		this.left=l;
		this.right=null;
		this.op=Operation.None;
	}

	@Override
	public double f(double x) { 
		Operation o= this.op;
		switch (o) {
		case Plus:
			return this.left.f(x) + this.right.f(x);
		case Times:
			return this.left.f(x) * this.right.f(x);
		case Divid:
			return this.left.f(x) / this.right.f(x);
		case Max:
			if(this.left.f(x) > this.right.f(x))
				return this.left.f(x);
			else 
				return this.right.f(x);
		case Min:
			if(this.left.f(x) > this.right.f(x))
				return this.right.f(x);
			else 
				return this.left.f(x);
		case Comp:
			return this.left.f(this.right.f(x));
		case None:
			if(this.left()==null)
				return this.right.f(x);
			if(this.right()==null)
				return this.left.f(x);
		default:
			throw new IllegalArgumentException("Unknown operation: " + o);
		}
	}

	@Override
	public function initFromString(String s) {
		return null;
	}

	@Override
	public function copy() {
		if(right==null)
			return new ComplexFunction(this.left);
		return new ComplexFunction(this.op.toString(),this.left, this.right());
	}

	@Override
	public void plus(function f1) {
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Times;
	}

	@Override
	public void div(function f1) {
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Divid;
	}

	@Override
	public void max(function f1) {
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Max;
	}

	@Override
	public void min(function f1) {
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Min;

	}

	@Override
	public void comp(function f1) {
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Comp;
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}


	//convert the string to operator
	private Operation stringToOp(String s)
	{
		Operation o=null;
		s=s.toLowerCase();
		try {
			switch (s) {
			case "plus":
				o=o.Plus;
				break;
			case"times":
				o=o.Times;
				break;
			case"mul":
				o=o.Times;
				break;
			case"div":
				o=o.Divid;
				break;
			case"divid":
				o=o.Divid;
				break;
			case"max":
				o=o.Max;
				break;
			case"min":
				o=o.Min;
				break;
			case"comp":
				o=o.Comp;
			case"none":
				o=o.None;
				break;
			}
			if(o==null)
			{
				throw new Exception("no operator");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public String toString()
	{
		Operation o=this.getOp();
		if(right==null)
			return left.toString();
		switch (o) {
		case Plus:
			return"("+"("+ this.left()+")"+"+" + "("+this.right()+")"+")";
		case Times:
			return"("+"("+ this.left()+")"+ "*" + "("+this.right()+")"+")"; 
		case Divid:
			return"("+"("+ this.left()+")"+ "/" + "("+this.right()+")"+")";
		case Max:
			return "Max"+"("+this.left()+","+this.right()+")";
		case Min:
			return "Min"+"("+this.left()+","+this.right()+")";
		case Comp:
			return "Comp("+this.left()+","+this.right()+")";
		case None:
			if(right==null)
				return "("+this.left()+")";
			break;	
		}
		return this.getOp()+"("+this.left()+","+this.right()+")";

	}
	public boolean equals(Object obj) {

		ComplexFunction F= new ComplexFunction((function) obj);
		int x=-50; 
		while(x<=50)
		{
			if(this.f(x)!=F.f(x))
				return false;
			x++;
		}
		return true;
	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom("8x");
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		cf3.plus(p2);
		System.out.println(cf3.left);
		System.out.println(cf3.right);
		System.out.println(cf3.f(2));
		System.out.println(cf3.equals(p2));
		Polynom p4 = new Polynom("x+1");
		Polynom p5 = new Polynom("1+x");
		System.out.println(p4.equals(p5));
	}


}
