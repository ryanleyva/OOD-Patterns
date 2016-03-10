import java.io.Serializable;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class GardenSerializable implements GardenEntitySerializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double x;
	double y;
	double height;
	double width;
	ArrayList<GardenEntitySerializable> children;
	String RGB;
	
	GardenSerializable(double x, double y, double height, double width, String RGB, ArrayList<GardenEntity> nodes)
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
		ArrayList<GardenEntitySerializable> children = new ArrayList<GardenEntitySerializable>();
		for (GardenEntity node : nodes)
		{
			children.add(node.serialize());
		}
	}
	public GardenEntity unSerialize()
	{
		GardenEntity garden = new Garden();
		for (GardenEntitySerializable child : children)
		{
			garden.add(child.unSerialize());
		}
		return new Garden();
	}
}