package objects;

import com.jogamp.opengl.GL2;

import worlds.World;

public class Snow extends UniqueObject{

    private float size; //width, height
    private static final float GRAVITY = 1f;
    private float altitude = 100f; //z-axis
    private float speed = 1f;

	public Snow ( float __x , float __y , World __world )
	{
		super(__x,__y,__world);
		altitude = altitude + (float) Math.random() * 100f;
		size = .985f;
	}

	 /*
     * The Rain and Snow java classes are basically similar.
     * It consists of a simple particle drawn differently (rain blue, snow white) in different sizes (rain is long and snow short).
     * The snow falls slowly along the z axis at a growing speed to simulate gravity, once it reaches the bottom of the map its altitude is reset to 100f + random_value
     * (top of the map) to simulate raindrops at different heights.
     */
    public void displayUniqueObject(World myWorld, GL2 gl, int offsetCA_x, int offsetCA_y, float offset, float stepX, float stepY, float lenX, float lenY, float normalizeHeight)
    {
 		//speed = myWorld.getLandscape().getWeather().getTimeSpeed()*((float) (Math.random() * 100f + 25f)); //update speed according to time speed
        altitude = (altitude - speed*GRAVITY);
        speed += .005f;
        if (altitude <= -25f) {
            altitude = (float) (Math.random() * 100f) + 100f; //if rain goes under the map put it back on top
            speed = 1f; //reinit speed
        }
    	float x2 = (x-(offsetCA_x%myWorld.getWidth()));
    	if ( x2 < 0) x2+=myWorld.getWidth();
    	float y2 = (y-(offsetCA_y%myWorld.getHeight()));
    	if ( y2 < 0) y2+=myWorld.getHeight();

    	float zoff = myWorld.getLandscape().getZOffset() + altitude;

        float t = (float) Math.cos(myWorld.getLandscape().getWeather().getElapsedTime());

        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(gl.GL_LINES);
        gl.glVertex3f(offset+x2*stepX-lenX, offset+y2*stepY-lenY, 0.1f*normalizeHeight*size + zoff);
        gl.glVertex3f(offset+x2*stepX-lenX, offset+y2*stepY-lenY, 0.1f*normalizeHeight + zoff);
        gl.glEnd();
    }
}
