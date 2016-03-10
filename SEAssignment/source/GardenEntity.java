import java.io.Serializable;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;


public interface GardenEntity {
	public void move(double dx, double dy);
	public Shape getShape();
	public boolean clicked(Point2D clickPoint);
	public GardenEntity deepCopy();
	public GardenEntity getClickedEntity(Point2D clickPoint);
	public void add(GardenEntity g);
	public void remove(GardenEntity g);
	public GardenEntity getNode(Point2D clickPoint);
	public void setParent(GardenEntity node);
	public GardenEntity getParent();
	public GardenEntitySerializable serialize();
	
}
