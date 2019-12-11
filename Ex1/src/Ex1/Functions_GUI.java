package Ex1;

///////
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;
import Ex1.StdDraw;
import com.google.gson.Gson;

public class Functions_GUI implements functions{
	private ArrayList<function> Funcs = new ArrayList<function>();

	@Override
	public int size() {
		return Funcs.size();
	}

	@Override
	public boolean isEmpty() {
		return Funcs.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return Funcs.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return Funcs.iterator();
	}

	@Override
	public Object[] toArray() {
		return Funcs.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return Funcs.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return Funcs.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return Funcs.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return Funcs.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return Funcs.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return Funcs.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return Funcs.retainAll(c);
	}

	@Override
	public void clear() {}

	@Override
	public void initFromFile(String file) throws IOException {
		ComplexFunction newF= new ComplexFunction(new Monom(Monom.ZERO));
		String line="";
		try {
			BufferedReader reader =new BufferedReader(new FileReader(file));
			while((line=reader.readLine())!=null)
				Funcs.add((function)newF.initFromString(line));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void saveToFile(String file) throws IOException {
		try {
			Iterator<function> iter = Funcs.iterator();
			PrintWriter writer=new PrintWriter(file);
			while(iter.hasNext())
			{
				writer.write(iter.next().toString());
				writer.write("/n");
			}
			writer.close();
		}catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
	}
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
			Color.red, Color.GREEN, Color.PINK};

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setXscale(ry.get_min(), ry.get_max());
		StdDraw.setPenColor(StdDraw.BLACK);

		for(double i=ry.get_min(); i<ry.get_max();i++)
		{
			StdDraw.line(rx.get_min(),i, rx.get_max(),i);
			StdDraw.text(0.2,i+0.2,i+""); 
		}

		for(double i=rx.get_min(); i<rx.get_max(); i++)
		{
			StdDraw.line(i,ry.get_min(), i, ry.get_max());
			StdDraw.text(i+0.2,0.2,i+""); 

		}
		//set the pen color and radios
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.006);
		//X axis drawing 
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		//y axis drawing
		StdDraw.line(0,ry.get_min(),0, ry.get_max());
		double step=(rx.get_max()-rx.get_min())/resolution;
		Double fx=null;
		for(int i=0; i<this.Funcs.size();i++) 
		{
			StdDraw.setPenColor(Colors[i%Colors.length]);
			try {
				fx=Funcs.get(i).f(rx.get_min());
			}
			catch(Exception e)
			{
				fx=null;
			}
			for(double j=rx.get_min(); j<rx.get_max(); j+=step)
				if(fx!=null)
					StdDraw.line(j, fx, j+step, Funcs.get(i).f(j+step));
			{

			}
		}
	}

	@Override
	public void drawFunctions(String json_file) {
		Gson gson= new Gson();
		GUI_params parameters;
		try {
			FileReader reader = new FileReader(json_file);
			parameters = gson.fromJson(reader, GUI_params.class);
		}catch(FileNotFoundException e)
		{
			parameters=new GUI_params();
		}
		Range rx = new Range(parameters.Range_X[0],parameters.Range_X[1]);
		Range ry = new Range(parameters.Range_Y[0],parameters.Range_Y[1]);
		drawFunctions(parameters.Width, parameters.Height, rx, ry, parameters.Resolution);

	}
	private class GUI_params{
		int Width = 1000;
		int Height = 600;
		double[] Range_X = {-10, 10};
		double[] Range_Y = {-10, 10};
		int Resolution = 200;

	}
}
