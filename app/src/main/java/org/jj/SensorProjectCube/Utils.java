package org.jj.SensorProjectCube;

import android.opengl.GLES20;
import android.util.Log;

public class Utils {

	public static final String TAG = "Utils";

	public static int loadShader(int shaderType, String source) {
	    int shader = GLES20.glCreateShader(shaderType);
	        if (shader != 0) {
	            GLES20.glShaderSource(shader, source);
	            GLES20.glCompileShader(shader);
	            int[] compiled = new int[1];
	            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
	            if (compiled[0] == 0) {
	                Log.e(TAG, "Could not compile shader " + shaderType + ":");
	                Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
	                GLES20.glDeleteShader(shader);
	                shader = 0;
	            }
	        }
	        return shader;
	}
	
	public static int createProgram(int vertexShader, int pixelShader) {
	    int program = GLES20.glCreateProgram();
	    if (program != 0) {
	        GLES20.glAttachShader(program, vertexShader);
	        GLES20.glAttachShader(program, pixelShader);
	        GLES20.glLinkProgram(program);
	        int[] linkStatus = new int[1];
	        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
	        if (linkStatus[0] != GLES20.GL_TRUE) {
	            Log.e(TAG, "Could not link program: ");
	            Log.e(TAG, GLES20.glGetProgramInfoLog(program));
	            GLES20.glDeleteProgram(program);
	            program = 0;
	        }
	    }
	    return program;
	}
	
}
