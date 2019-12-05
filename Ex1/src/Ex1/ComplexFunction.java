package Ex1;

public class ComplexFunction implements complex_function {

	function left;
	function right;
	Operation op; 

	public ComplexFunction(Operation o, function l, function r)
	{
		if(l==null)
			throw new RuntimeException("no left function");
		this.left=l;
		this.right=r;
		this.op=o;
	}
	public ComplexFunction(String o, function l, function r)
	{
	}
	
	public ComplexFunction(function l)
	{
		this.left=l;
	}
	
	@Override
	public double f(double x) {
		
		
		return 0;
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		
		return null;
	}

	@Override
	public void plus(function f1) {
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Times;
	}

	@Override
	public void div(function f1) {
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Divid;
	}

	@Override
	public void max(function f1) {
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Max;
	}

	@Override
	public void min(function f1) {
	this.left=this.copy();
	this.right=f1.copy();
	this.op=Operation.Min;
		
	}

	@Override
	public void comp(function f1) {
		this.left=this.copy();
		this.right=f1.copy();
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
	
	@Override
	public String toString()
	{
		return "";
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
