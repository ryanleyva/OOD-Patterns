import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import java.io.Serializable;
public class Flower implements GardenEntity{
	Point2D point;
	Color color;
	Circle circle;
	public ArrayList<GardenEntity> children;
	GardenEntity parent;
	public Flower(Point2D point, Color color)
	{
		this.point = point;
		this.color = color;
		genFlower();
	}
	
	public void genFlower()
	{
		circle = new Circle();
		circle.setCenterX(point.getX());
		circle.setCenterY(point.getY());
		circle.setRadius(5);
		circle.setFill(color);
		circle.setStrokeWidth(1);
		circle.toFront();
		
	}

	@Override
	public void move(double dx, double dy) {
		circle.setCenterX(circle.getCenterX()+dx);
		circle.setCenterY(circle.getCenterY()+dy);
		
	}

	@Override
	public Shape getShape() {
		return circle;
	}

	@Override
	public boolean clicked(Point2D clickPoint) {
		double x = circle.getCenterX();
		double y = circle.getCenterY();
		double i = clickPoint.getX();
		double j = clickPoint.getY();
		double c = Math.pow(x-i,2) + Math.pow(y - j,2);
		c = Math.sqrt(c);
		return c < circle.getRadius();
	}

	@Override
	public GardenEntity deepCopy() {
		GardenEntity tmp = new Flower(point, color);
		return tmp;
	}
	
	@Override
	public GardenEntity getClickedEntity(Point2D clickPoint) {
		/*if (clicked(clickPoint))
		{
			return this;
		}
		else
			return null;
		*/
		return null;
	}

	@Override
	public void add(GardenEntity g) {
		// Doesn't use add
		
	}

	@Override
	public void remove(GardenEntity g) {
		// Doesn't use remove
		
	}

	@Override
	public GardenEntity getNode(Point2D clickPoint) {
		if (this.clicked(clickPoint))
		{
			return this;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setParent(GardenEntity node) {
		if (this != node)
		{
			parent = node;
		}
		
	}

	@Override
	public GardenEntity getParent() {
		return parent;
	}

	@Override
	public GardenEntitySerializable serialize() {
		return new FlowerSerializable(point.getX(), point.getY(), circle.getRadius(), color.toString());
		
	}
}
