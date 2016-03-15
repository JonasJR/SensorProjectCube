package org.jj.SensorProjectCube;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private FrameLayout mLayout;
    private GLSurfaceView mSurfaceView;
    private SimpleRenderer mRenderer;
    private TextView mFpsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // mLayout will contain the GLSurfaceView and the TextView
        // mFpsView
        mLayout = new FrameLayout(this);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        mLayout.setLayoutParams(params);

        mSurfaceView = new MyGLSurfaceView(this);
        mRenderer = new SimpleRenderer(this);

        mSurfaceView.setRenderer(mRenderer);

        mSurfaceView.setLayoutParams(params);

        mLayout.addView(mSurfaceView);

        mFpsView = new TextView(this);
        mFpsView.setTextColor(Color.WHITE);
        mFpsView.setBackgroundColor(Color.DKGRAY);
        mFpsView.setEms(3);

        // framelayout child views are drawn on top of each other, with the
        // most recently added at the top
        mLayout.addView(mFpsView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP
                | Gravity.END));

        setContentView(mLayout);
    }

    /*
     * GLSurfaceView require to call its onPause/onResume method to manage its
     * rendering thread and the OpenGL display according to Android doc.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSurfaceView.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] quat = new float[4];
        SensorManager.getQuaternionFromVector(quat, event.values);
        mRenderer.quat = quat;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public static class MyGLSurfaceView extends GLSurfaceView {

        public MyGLSurfaceView(Context context) {
            super(context);
            // RGBA_8888 color buffer, 16bits depth buffer
            // and 8bits stencil buffer
            super.setEGLConfigChooser(8, 8, 8, 8, 16, 8);
            // request an OpenGL ES 2.0 context
            setEGLContextClientVersion(2);
        }

    }

}
