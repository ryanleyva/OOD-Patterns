import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.io.Serializable;
public class FlowerBed implements GardenEntity {
	Point2D point;
	Color color;
	boolean movable;
	Rectangle rect;
	final double width;
	final double height;
	GardenEntity parent;
	public ArrayList<GardenEntity> children;
	public FlowerBed(Point2D point, Color color, double width, double height)
	{
		this.width = width;
		this.height = height;
		this.point = point;
		this.color = color;
		children = new ArrayList<GardenEntity>();
		initFlowerBed();
	}
	
	public void initFlowerBed()
	{
		rect = new Rectangle(point.getX(),point.getY(),height,width);
		rect.setFill(color);
		rect.toBack();
	}

	@Override
	public void move(double dx, double dy) {
		rect.setX(rect.getX() + dx);
		rect.setY(rect.getY() + dy);
		System.out.println("moving flowerbed");
		for (GardenEntity child : children)
		{
			child.move(dx, dy);
		}
	}

	@Override
	public Shape getShape() {
		return rect;
	}

	@Override
	public boolean clicked(Point2D clickpoint) {
		double a = rect.getX();
		double b = rect.getY();
		if ((clickpoint.getX() >= a && clickpoint.getX() <= (a + width)) 
		&& (clickpoint.getY() >= b && clickpoint.getY() <= (b + height)))
			return true;
		else
			return false;
	}

	@Override
	public GardenEntity deepCopy() {
		GardenEntity tmp = new FlowerBed(new Point2D(rect.getX(), rect.getY()), color, width, height);
		return tmp;
	}

	@Override
	public GardenEntity getClickedEntity(Point2D clickPoint) {
		if (clicked(clickPoint))
		{
			System.out.println("parent = flowerbed");
			return this;
		}
		else
		{
			return null;
		}
		/*
		for (GardenEntity child : children)
		{
			GardenEntity g = child.getClickedEntity(clickPoint);
			if (g != null)
			{
				return g;
			}
		}
		return this;
		}
		else
		{
			return null;
		}*/
	}

	@Override
	public void add(GardenEntity g) {
		if (g != this)
			children.add(g);
		
	}

	@Override
	public void remove(GardenEntity g) {
		System.out.println("remove(gardenItem)");
		children.remove(g);
		
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
		return this;
	}

	@Override
	public void setParent(GardenEntity node) {
		if (this != node)
			this.parent = node;
	}

	@Override
	public GardenEntity getParent() {
		return parent;
	}

	@Override
	public GardenEntitySerializable serialize() {
		return new FlowerBedSerializable (point.getX(), point.getY(), height, width, color.toString(), children);
		
	}
}
