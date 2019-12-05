package Ex1;

public class ComplexFunction implements complex_function {

	function left;
	function right;
	Operation op=Operation.None;

	public ComplexFunction()
	{
		this.left=null;
		this.right=null;
	}
	public ComplexFunction(String o, function l, function r)
	{
		this.left=l;
		this.right=r;
		this.op=stringToOp(o);
	}
	
	public ComplexFunction(function l)
	{
		this.left=l;
		this.right=null;
		this.op=Operation.None;
	}
	
	@Override
	public double f(double x) {
		//נדריך את הפונקציה לאיזה מהפונקציות של פולינום ומונום ללכת כדי לחשב את  של הפונקציה המורכבת(F) 
		
		
		return 0;
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		if(right==null)
			return new ComplexFunction(this.left);
		return new ComplexFunction(opToString(this.op),this.left, this.right());
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
		if(this.right==null)
			throw new RuntimeException("ERR: no function");
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}
	
	private Operation stringToOp(String s)
	{
		//מקבלת אופרטור והופכת אותו לסטרינג כדי להכניס למופע חדש של CF switch 
		return null;
	}
	private String opToString(Operation o)
	{
		//מקבלת סטריניג של אופרטורו והופכת אוטו לאופרטור עם switch 
		return "";
	}
	
	@Override
	public String toString()
	{
		//פונקצית הדפסה 
		return "";
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

	}
	

}
