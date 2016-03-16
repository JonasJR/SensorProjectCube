package org.jj.SensorProjectCube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class CubeBuffers {

	IntBuffer mBuffers = IntBuffer.allocate(4);
	
	IntBuffer mTextures = IntBuffer.allocate(1);
	
	float[] mCubeVertices = { 
			0.5F, 0.5F, 0.5F,
			-0.5F, 0.5F, 0.5F,
			-0.5F, -0.5F, 0.5F,
			
			0.5F, 0.5F, 0.5F,
			-0.5F, -0.5F, 0.5F,
			0.5F, -0.5F, 0.5F,
			
			0.5F, 0.5F, -0.5F,
			0.5F, 0.5F, 0.5F,
			0.5F, -0.5F, 0.5F,
			
			0.5F, 0.5F, -0.5F,
			0.5F, -0.5F, 0.5F,
			0.5F, -0.5F, -0.5F,
			
			-0.5F, 0.5F, -0.5F,
			0.5F, 0.5F, -0.5F,
			0.5F, -0.5F, -0.5F,
			
			-0.5F, 0.5F, -0.5F,
			0.5F, -0.5F, -0.5F,
			-0.5F, -0.5F, -0.5F,
			
			-0.5F, 0.5F, 0.5F,
			-0.5F, 0.5F, -0.5F,
			-0.5F, -0.5F, -0.5F,
			
			-0.5F, 0.5F, 0.5F,
			-0.5F, -0.5F, -0.5F,
			-0.5F, -0.5F, 0.5F,
			
			0.5F, 0.5F, -0.5F,
			-0.5F, 0.5F, -0.5F,
			-0.5F, 0.5F, 0.5F,
			
			0.5F, 0.5F, -0.5F,
			-0.5F, 0.5F, 0.5F,
			0.5F, 0.5F, 0.5F,
			
			0.5F, -0.5F, 0.5F,
			-0.5F, -0.5F, 0.5F,
			-0.5F, -0.5F, -0.5F,
			
			0.5F, -0.5F, 0.5F,
			-0.5F, -0.5F, -0.5F,
			0.5F, -0.5F, -0.5F,
	};

	private float[] mCubeNormals = {
			0F, 0F, 1.0F,
			0F, 0F, 1.0F,
			0F, 0F, 1.0F,
			0F, 0F, 1.0F,
			0F, 0F, 1.0F,
			0F, 0F, 1.0F,
			
			1.0F, 0F, 0F,
			1.0F, 0F, 0F,
			1.0F, 0F, 0F,
			1.0F, 0F, 0F,
			1.0F, 0F, 0F,
			1.0F, 0F, 0F,
			
			0F, 0F, -1.0F,
			0F, 0F, -1.0F,
			0F, 0F, -1.0F,
			0F, 0F, -1.0F,
			0F, 0F, -1.0F,
			0F, 0F, -1.0F,
			
			-1.0F, 0F, 0F,
			-1.0F, 0F, 0F,
			-1.0F, 0F, 0F,
			-1.0F, 0F, 0F,
			-1.0F, 0F, 0F,
			-1.0F, 0F, 0F,
			
			0F, 1.0F, 0F,
			0F, 1.0F, 0F,
			0F, 1.0F, 0F,
			0F, 1.0F, 0F,
			0F, 1.0F, 0F,
			0F, 1.0F, 0F,
			
			0F, -1.0F, 0F,
			0F, -1.0F, 0F,
			0F, -1.0F, 0F,
			0F, -1.0F, 0F,
			0F, -1.0F, 0F,
			0F, -1.0F, 0F,
	};
	
	private float[] mCubeColors = {
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,

			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,

			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,

			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,

			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,

			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
	};

	private float[] mCubeTextureCoords = {
			1.0F, 1.0F, 1.0F,
			-1.0F, 1.0F, 1.0F,
			-1.0F, -1.0F, 1.0F,
			
			1.0F, 1.0F, 1.0F,
			-1.0F, -1.0F, 1.0F,
			1.0F, -1.0F, 1.0F,
			
			1.0F, 1.0F, -1.0F,
			1.0F, 1.0F, 1.0F,
			1.0F, -1.0F, 1.0F,
			
			1.0F, 1.0F, -1.0F,
			1.0F, -1.0F, 1.0F,
			1.0F, -1.0F, -1.0F,
			
			-1.0F, 1.0F, -1.0F,
			1.0F, 1.0F, -1.0F,
			1.0F, -1.0F, -1.0F,
			
			-1.0F, 1.0F, -1.0F,
			1.0F, -1.0F, -1.0F,
			-1.0F, -1.0F, -1.0F,
			
			-1.0F, 1.0F, 1.0F,
			-1.0F, 1.0F, -1.0F,
			-1.0F, -1.0F, -1.0F,
			
			-1.0F, 1.0F, 1.0F,
			-1.0F, -1.0F, -1.0F,
			-1.0F, -1.0F, 1.0F,
			
			1.0F, 1.0F, -1.0F,
			-1.0F, 1.0F, -1.0F,
			-1.0F, 1.0F, 1.0F,
			
			1.0F, 1.0F, -1.0F,
			-1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F,
			
			1.0F, -1.0F, 1.0F,
			-1.0F, -1.0F, 1.0F,
			-1.0F, -1.0F, -1.0F,
			
			1.0F, -1.0F, 1.0F,
			-1.0F, -1.0F, -1.0F,
			1.0F, -1.0F, -1.0F,
		
	};

	public CubeBuffers() {}

	public void init(Context context) {
		
		FloatBuffer bufferVertices;
		FloatBuffer bufferNormals;
		FloatBuffer bufferTextureCoords;
		FloatBuffer bufferColors;

        bufferVertices = ByteBuffer.allocateDirect(mCubeVertices.length * 4)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
        bufferVertices.put(mCubeVertices);
        bufferVertices.position(0);

        bufferColors = ByteBuffer.allocateDirect(mCubeColors.length * 4)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
        bufferColors.put(mCubeColors);
        bufferColors.position(0);

        bufferNormals = ByteBuffer.allocateDirect(mCubeNormals.length * 4)
        		.order(ByteOrder.nativeOrder())
        		.asFloatBuffer();
        bufferNormals.put(mCubeNormals);
        bufferNormals.position(0);

		bufferTextureCoords = ByteBuffer.allocateDirect(mCubeTextureCoords.length * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		bufferTextureCoords.put(mCubeTextureCoords);
		bufferTextureCoords.position(0);

		GLES20.glGenBuffers(4, mBuffers);

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBuffers.get(0));
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER,bufferVertices.capacity() * 4,
				bufferVertices, GLES20.GL_STATIC_DRAW);

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBuffers.get(1));
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, bufferColors.capacity() * 4,
				bufferColors, GLES20.GL_STATIC_DRAW);

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBuffers.get(2));
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, bufferNormals.capacity() * 4,
				bufferNormals, GLES20.GL_STATIC_DRAW);

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBuffers.get(3));
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, bufferTextureCoords.capacity() * 4,
				bufferTextureCoords, GLES20.GL_STATIC_DRAW);

		Bitmap bm1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jj1);
		Bitmap bm2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jj2);
		Bitmap bm3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jj3);
		Bitmap bm4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jj4);
		Bitmap bm5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jj5);
		Bitmap bm6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.jj6);


		GLES20.glGenTextures(1, mTextures);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_CUBE_MAP, mTextures.get(0));


		GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, GLES20.GL_RGBA, bm1,
				GLES20.GL_UNSIGNED_BYTE, 0);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_X,  0, GLES20.GL_RGBA, bm2,
				GLES20.GL_UNSIGNED_BYTE, 0);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, GLES20.GL_RGBA, bm3,
				GLES20.GL_UNSIGNED_BYTE, 0);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, GLES20.GL_RGBA, bm4,
				GLES20.GL_UNSIGNED_BYTE, 0);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, GLES20.GL_RGBA, bm5,
				GLES20.GL_UNSIGNED_BYTE, 0);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, GLES20.GL_RGBA, bm6,
				GLES20.GL_UNSIGNED_BYTE, 0);

		GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_CUBE_MAP);

		GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_MIN_FILTER,
				GLES20.GL_LINEAR);

		GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_MAG_FILTER,
				GLES20.GL_NEAREST);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_WRAP_S,
				GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_WRAP_T,
				GLES20.GL_CLAMP_TO_EDGE);

		bm1.recycle();
		bm2.recycle();
		bm3.recycle();
		bm4.recycle();
		bm5.recycle();
		bm6.recycle();
	}
	
}
