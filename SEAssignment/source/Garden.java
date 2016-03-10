import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public class Garden implements GardenEntity {
	public Rectangle garden;
	public ArrayList<GardenEntity> children;
	double height;
	double width;
	public Point2D point;
	public Garden()
	{
		this.point = new Point2D(320,10);
		children = new ArrayList<GardenEntity>();
		buildGarden(this.point, 735, 400);
	}
	public void buildGarden(Point2D topLeft, int height, int width)
	{
		this.height = height;
		this.width = width;
		garden = new Rectangle();
		garden.setHeight(height);
		garden.setWidth(width);
		garden.setX(topLeft.getX());
		garden.setY(topLeft.getY());
		garden.setFill(Color.CORNSILK);
		garden.setStroke(Color.BLACK);		
		garden.setStrokeWidth(1);
	}
	
	@Override
	public void move(double dx, double dy) {
		// The Garden class does not move
		
	}
	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return garden;
	}
	@Override
	public boolean clicked(Point2D clickpoint) {
		double a = garden.getX();
		double b = garden.getY();
		if ((clickpoint.getX() >= a && clickpoint.getX() <= (a + width)) 
		&& (clickpoint.getY() >= b && clickpoint.getY() <= (b + height)))
			return true;
		else
			return false;
	}
	@Override
	public GardenEntity deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GardenEntity getClickedEntity(Point2D clickPoint) {
		for (GardenEntity child : children)
		{
			GardenEntity g = child.getClickedEntity(clickPoint);
			if (g != null)
			{
				return g;
			}
		}
		System.out.println("parent = garden");
		return this;
		
	}
	@Override
	public void add(GardenEntity g) {
		children.add(g);
		
	}
	@Override
	public void remove(GardenEntity g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public GardenEntity getNode(Point2D clickPoint) {
		GardenEntity gardenItem = null;
		for (GardenEntity g : children)
		{
			if (g.clicked(clickPoint))
			{
				gardenItem = g.getNode(clickPoint);
				if (gardenItem != null)
				{
					return gardenItem;
				}
			}
		}
		return null;
	}
	@Override
	public void setParent(GardenEntity node) {
	// Garden has no parent
		
	}
	@Override
	public GardenEntity getParent() {
		return null;
	}
	@Override
	public GardenEntitySerializable serialize() {
		return new GardenSerializable (point.getX(), point.getY(), height, width, "#FFF8DC", children);
		
	}

}
