
public class FlowerSerializable implements GardenEntitySerializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double x;
	double y;
	double radius;
	String RGB;
	
	FlowerSerializable(double x, double y, double radius, String RGB)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.RGB = RGB;
	}
	public GardenEntity unSerialize()
	{
		return null;
	}
}
