
public class VegetableSerializable implements GardenEntitySerializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double x;
	double y;
	double radius;
	String RGB;
	
	VegetableSerializable(double x, double y, double radius, String RGB)
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