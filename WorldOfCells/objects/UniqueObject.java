// ### WORLD OF CELLS ### 
// created by nicolas.bredeche(at)upmc.fr
// date of creation: 2013-1-12

package objects;

import com.jogamp.opengl.GL2;

import worlds.World;

abstract public class UniqueObject // UniqueObject are object defined with particular, unique, properties (ex.: particular location)
{
	protected float x,y;
	protected World world;
	
	public UniqueObject(float __x, float __y, World __world)
	{
		this.world = __world;
		this.x = __x;
		this.y = __y;
	}
	
	public float[] getCoordinate()
	{
		float coordinate[] = new float[2];
		coordinate[0] = this.x;
		coordinate[1] = this.y;
		return coordinate;
	}
	
	abstract public void displayUniqueObject(World myWorld, GL2 gl, int offsetCA_x, int offsetCA_y, float offset, float stepX, float stepY, float lenX, float lenY, float normalizeHeight );

}
