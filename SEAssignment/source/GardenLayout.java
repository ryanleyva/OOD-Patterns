import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GardenLayout extends Application {
	AnchorPane root;
	Scene scene;
	GardenEntity copy = null;
	GardenEntity gardenItem = null;
	Garden garden;
	Point2D lastPosition = null;
	ArrayList<GardenEntity> toolkit;
	@Override
	public void start(Stage stage) throws Exception {
		root = new AnchorPane();
		toolkit = new ArrayList<GardenEntity>();
		scene = new Scene(root, 750, 750);
		stage.setScene(scene);
		buildToolkit(new Point2D(10,10), 650, 300);
		
		initGarden();
		initFlowerBed();
		initFlowers();
		initVegetables();
		initButtons();
		stage.setTitle("Garden");
		
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		for (GardenEntity flower: toolkit) root.getChildren().add(flower.getShape());
		stage.show();
		
	}
	

	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent mouseEvent)
		{
			Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			if (lastPosition == null) lastPosition = clickPoint;
			
			String eventName = mouseEvent.getEventType().getName();
			
			switch(eventName){

			case "MOUSE_PRESSED":
				//left side of pane
				if (!inGarden(clickPoint))
				{
					for (GardenEntity g : toolkit)
					{
						// if clicked on an item in toolkit
						if (g.clicked(clickPoint))
						{
							System.out.println("pressed flower");
							copy = g.deepCopy();
							root.getChildren().add(copy.getShape());
							break;
						}		
					}
				}
				//right side of pane
				else
				{
					gardenItem = garden.getNode(clickPoint);
					if (gardenItem != null)
					{
						GardenEntity parent = gardenItem.getParent();
						parent.remove(gardenItem);
					}
				
				}
				break;
				
			case "MOUSE_DRAGGED":
				if (copy != null)
				{
					double deltax = clickPoint.getX()-lastPosition.getX();
					double deltay = clickPoint.getY()-lastPosition.getY();
					copy.move(deltax, deltay);
				}
				if (gardenItem != null)
				{
					double deltax = clickPoint.getX()-lastPosition.getX();
					double deltay = clickPoint.getY()-lastPosition.getY();
					gardenItem.move(deltax, deltay);
				}
				break;
				
			case "MOUSE_RELEASED":
				//have new copy
				if (copy != null)
				{
					// if new copy not placed in the garden
					if (!inGarden(clickPoint))
					{
						System.out.println("not in garden");
						root.getChildren().remove(copy.getShape());	
					}
					// copy is placed in the garden
					else
					{	
						GardenEntity parent = garden.getClickedEntity(clickPoint);
						copy.setParent(parent);
						parent.add(copy);
						// check for parent
						//GardenEntity parent = garden.getClickedEntity(clickPoint);
					
					}
				}
				// releasing existing garden item
				if (gardenItem != null)
				{
					if (!inGarden(clickPoint))
					{
						root.getChildren().remove(gardenItem.getShape());
					}
					else
					{
						GardenEntity parent = garden.getClickedEntity(clickPoint);
						gardenItem.setParent(parent);
						parent.add(gardenItem);
						gardenItem.setParent(parent);
					}
					
				}
				gardenItem = null;
				copy = null;
				break;
			}
			lastPosition = clickPoint;
		}
	};
	
	
	public void initGarden()
	{
		garden = new Garden();
		root.getChildren().add(garden.garden);
	}
	public void initFlowerBed()
	{
		toolkit.add(new FlowerBed(new Point2D(55,210), Color.DARKSEAGREEN, 100, 100));
	}
	public void initFlowers()
	{
		toolkit.add(new Flower(new Point2D(55,60), Color.valueOf("#FF0000")));
		toolkit.add(new Flower(new Point2D(70,60), Color.RED));
		toolkit.add(new Flower(new Point2D(85,60), Color.DARKORCHID));
		toolkit.add(new Flower(new Point2D(100,60), Color.LAVENDER));
		toolkit.add(new Flower(new Point2D(115,60), Color.ORANGE));

	}
	public void initVegetables()
	{
		toolkit.add(new Vegetable(new Point2D(55,150), Color.SALMON));
		toolkit.add(new Vegetable(new Point2D(70,150), Color.DARKRED));
		toolkit.add(new Vegetable(new Point2D(85,150), Color.WHEAT));
		toolkit.add(new Vegetable(new Point2D(100,150), Color.TOMATO));
		toolkit.add(new Vegetable(new Point2D(115,150), Color.ORANGERED));

	}
	public void initButtons()
	{
		Button save = new Button("Save");
		save.setLayoutX(10);
		save.setLayoutY(675);
		save.setOnAction(
				new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						try {
						//some function to save
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					
				}
				);
		root.getChildren().add(save);
		
		Button retrieve = new Button("Retrieve");
		retrieve.setLayoutX(65);
		retrieve.setLayoutY(675);
		retrieve.setOnAction(
				new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						try {
						//some function to retrieve
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					
				}
				);
		root.getChildren().add(retrieve);
		
		Button exit = new Button("Exit");
		exit.setLayoutX(140);
		exit.setLayoutY(675);
		exit.setOnAction( (event) -> Platform.exit());				
		root.getChildren().add(exit);
		
	}
	public boolean inGarden(Point2D clickpoint)
	{	
		if ((clickpoint.getX() >= 320 && clickpoint.getX() <= 720) 
				&& (clickpoint.getY() >= 10 && clickpoint.getY() <= 745))
				return true;
		else return false;
	}
	
	public void buildToolkit(Point2D topLeft, int height, int width){
		Rectangle toolkit = new Rectangle();
		toolkit.setHeight(height);
		toolkit.setWidth(width);
		toolkit.setX(topLeft.getX());
		toolkit.setY(topLeft.getY());
		toolkit.setFill(Color.WHITE);
		toolkit.setStroke(Color.BLACK);		
		toolkit.setStrokeWidth(1);
		Text flowerText = new Text(50, 50, "Flowers");
		Text vegetableText = new Text(50, 140, "Vegetables");
		Text flowerBedText = new Text(50, 200, "Flower BED");
		
		flowerBedText.setFont(Font.font ("Verdana", 18));
		flowerText.setFont(Font.font ("Verdana", 18));
		vegetableText.setFont(Font.font ("Verdana", 18));
		root.getChildren().add(toolkit);
		root.getChildren().add(vegetableText);
		root.getChildren().add(flowerText);
		root.getChildren().add(flowerBedText);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
