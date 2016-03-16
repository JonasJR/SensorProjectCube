package org.jj.SensorProjectCube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.opengl.GLSurfaceView.Renderer;

public class SimpleRenderer implements Renderer {

	public float[] quat = {1, 0, 0, 0};
	public float proximityZoom;
	float distance = 3.0f;

	private float[] mViewMatrix = new float[16];
	private float[] mProjectionMatrix = new float[16];
	private float[] vp = new float[16];
	
	private Context mContext;

	private Cube mCube;
	
	private String mVertexShaderSrc = 
			"attribute vec4 a_position;"
			+ "attribute vec4 a_color;"
			+ "attribute vec3 a_normal;"
			+ "attribute vec3 a_texture_coord;" 
			+ "varying vec4 v_color;"
			+ "varying vec3 v_normal;"
			+ "varying vec3 v_texture_coord;"
			+ "uniform mat4 u_mvp_matrix;"
			+ "void main()" 
			+ "{" 
			+ "gl_Position = u_mvp_matrix * a_position;"
			+ "v_color = a_color;"
			+ "v_normal = a_normal;"
			+ "v_texture_coord = a_texture_coord;" 
			+ "}";

	private String mFragmentShaderSrc =
			"#ifdef GL_FRAGMENT_PRECISION_HIGH \n"
			+ "precision highp float; \n"
			+ "#else \n"
			+ "precision mediump float; \n"
			+ "#endif \n"
			+ "varying vec4 v_color; \n"
			+ "varying vec3 v_normal; \n"
			+ "varying vec3 v_texture_coord; \n"
			+ "uniform samplerCube s_texture; \n"
			+ "void main() \n" + "{ \n"
			+ "vec4 base_color = textureCube(s_texture, v_texture_coord); \n"
			+ "if(base_color.a < 0.5) \n"
			+ "discard; \n"
			+ "vec3 mix_color = base_color.rgb * v_color.rgb; \n"
			+ "gl_FragColor = vec4(mix_color, base_color.a); \n" 
			+ "} \n";

	public SimpleRenderer(Context context) {
		mContext = context;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LESS);
		
		CubeBuffers mCubeBuffers = new CubeBuffers();
        mCubeBuffers.init(mContext);

        mCube = new Cube();
        
        mCube.init(mCubeBuffers, mVertexShaderSrc, mFragmentShaderSrc);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);

		float aspectRatio = (float) width / (float) height;
		Matrix.frustumM(mProjectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, 2, 30);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT
				| GLES20.GL_DEPTH_BUFFER_BIT);

		if(proximityZoom == 5.0){
			if(distance < 7.0f){
				distance += 0.1f;
			}
		} else {
			if(distance>=3.0f){
				distance -= 0.1f;
			}
		}
		Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, distance, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		Matrix.multiplyMM(vp, 0, mProjectionMatrix, 0, mViewMatrix, 0);

		Matrix.setRotateM(mCube.mModelMatrix, 0, (float) ((2.0f * Math.acos(quat[0]) * 180.0f) / Math.PI), quat[1], quat[2], quat[3]);

	    mCube.draw(vp);
	}

}
