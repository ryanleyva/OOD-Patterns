import java.io.Serializable;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class FlowerBedSerializable implements GardenEntitySerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double x;
	double y;
	double height;
	double width;
	ArrayList<Serializable> children;
	String RGB;
	
	FlowerBedSerializable(double x, double y, double height, double width, String RGB, ArrayList<GardenEntity> nodes)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.RGB = RGB;
		addChildren(nodes);
	}
	public void addChildren(ArrayList<GardenEntity> nodes)
	{
		ArrayList<Serializable> children = new ArrayList<Serializable>();
		for (GardenEntity node : nodes)
		{
			children.add(node.serialize());
		}
	}
	public GardenEntity unSerialize()
	{
		return new FlowerBed(new Point2D(x,y), Color.valueOf(RGB), width, height);
	}
}
