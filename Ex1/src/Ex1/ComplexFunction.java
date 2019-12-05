package Ex1;

public class ComplexFunction implements complex_function {

	function left;
	function right;
	Operation op; 

	public ComplexFunction(function l, function r, Operation o)
	{
		this.left=l;
		this.right=r;
		this.op=o;
	}
	
	public ComplexFunction(function l)
	{
		this.left=l;
	}

	public function get_left()
	{
		return this.left;
	}
	
	public function get_right()
	{
		return this.right;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		
		Operation op=Operation.Plus;
		ComplexFunction temp=new ComplexFunction(this.get_left(),this.get_right(),this.getOp());
				ComplexFunction ans=new ComplexFunction(temp,f1,op);
	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
