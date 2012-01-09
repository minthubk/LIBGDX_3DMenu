package edu.ronzyfonzy.AndroidGdxMenu.android;

import java.util.Date;

import android.app.Activity;
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class AndroidGdxMenuActivity implements ApplicationListener {
	private Mesh[] faces;
	private PerspectiveCamera camera;
    ActionResolver actionResolver;
	private float transX = 0.0f, transY = 0.0f, transZ = 0.0f;
	
	public AndroidGdxMenuActivity(){
		
	}
	public AndroidGdxMenuActivity(ActionResolver actionResolver)
    {
        this.actionResolver = actionResolver;
}
	
	@Override
	public void create() {
		if (faces == null) {
			faces = new Mesh[4];

			for (int i = 0; i < 4; i++) {
				faces[i] = new Mesh(true, 4, 4,
						new VertexAttribute(Usage.Position, 3, "a_position"),
						new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

				faces[i].setIndices(new short[] { 0, 1, 2, 3 });
			}

			faces[0].setVertices(new float[] {
					0.5f, 0.5f, 1.5f, Color.toFloatBits(96, 0, 0, 255),
					-0.5f, 0.5f, 1.5f, Color.toFloatBits(96, 0, 0, 255),
					0.5f, -0.5f, 1.5f, Color.toFloatBits(96, 0, 0, 255),
					-0.5f, -0.5f, 1.5f, Color.toFloatBits(96, 0, 0, 255) });

			faces[1].setVertices(new float[] {//
					0.5f, 0.5f, -1.5f, Color.toFloatBits(255, 0, 0, 255),
					-0.5f, 0.5f, -1.5f, Color.toFloatBits(255, 0, 0, 255),
					0.5f, -0.5f, -1.5f,  Color.toFloatBits(255, 0, 0, 255),
					-0.5f, -0.5f, -1.5f, Color.toFloatBits(255, 0, 0, 255) });
			
			faces[2].setVertices(new float[] {//
					1.5f, 0.5f, 0.5f, Color.toFloatBits(0, 0, 255, 255),
					1.5f, -0.5f, 0.5f, Color.toFloatBits(0, 0, 255, 255),
					1.5f, 0.5f, -0.5f, Color.toFloatBits(0, 0, 255, 255),
					1.5f, -0.5f, -0.5f, Color.toFloatBits(0, 0, 255, 255) });

			faces[3].setVertices(new float[] {
					-1.5f, 0.5f, 0.5f, Color.toFloatBits(0, 0, 96, 255),
					-1.5f, -0.5f, 0.5f, Color.toFloatBits(0, 0, 96, 255),
					-1.5f, 0.5f, -0.5f, Color.toFloatBits(0, 0, 96, 255),
					-1.5f, -0.5f, -0.5f, Color.toFloatBits(0, 0, 96, 255) });
		}

		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
//		Gdx.gl10.glTranslatef(0.0f,0.0f,-3.0f);
	}

	@Override
	public void resume() { }

	protected int lastTouchX;
	protected int lastTouchY;
	float asd= -1.5f;
	Date a, b;
	boolean tuched = false;
	@Override
	public void render() {
		if (Gdx.input.justTouched()) {
			a = new Date();
			tuched = true;
			lastTouchX = Gdx.input.getX();
//			lastTouchY = Gdx.input.getY();
//			Log.i("CameraPos", "x: "+  Gdx.input.getX() /* + " | y: "+camera.position.y + " | z: "+camera.position.z*/);
		} else if (Gdx.input.isTouched()) {
			camera.rotate(0.2f * (lastTouchX - Gdx.input.getX()), 0, 1.0f, 0);
//			camera.rotate(0.2f * (lastTouchY - Gdx.input.getY()), 1.0f, 0, 0);
//			System.out.println(camera.direction.toString());
			lastTouchX = Gdx.input.getX();
			lastTouchY = Gdx.input.getY();
		}
		if(!Gdx.input.isTouched()){
			if(tuched){
				b = new Date();
				long time = b.getTime()-a.getTime();
				if(time < 60){
                    actionResolver.showMyList();
				}
				tuched = false;
			}
		}
		
		
		camera.update();
		camera.apply(Gdx.gl10);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		for (Mesh face : faces) {
			face.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
		}

		try {
			Thread.sleep(16); // ~60FPS
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);
		camera.near = 0.1f;
		camera.translate(transX, transY, transZ);
	}

	@Override
	public void pause() { }

	@Override
	public void dispose() { }
}
