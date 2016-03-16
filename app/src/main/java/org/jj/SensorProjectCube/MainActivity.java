package org.jj.SensorProjectCube;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements SensorEventListener {

    private FrameLayout mLayout;
    private GLSurfaceView mSurfaceView;
    private SimpleRenderer mRenderer;
    private SensorManager mSensorManager;
    private Sensor mRotationSensor;
    private Sensor mProximitySensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        setContentView(mLayout);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mProximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
        mSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSurfaceView.onResume();
        mSensorManager.registerListener(this, mRotationSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mProximitySensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] quat = new float[4];
            SensorManager.getQuaternionFromVector(quat, event.values);
            mRenderer.quat = quat;
        } else if(event.sensor.getType() == Sensor.TYPE_PROXIMITY ){
            mRenderer.proximityZoom = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public static class MyGLSurfaceView extends GLSurfaceView {

        public MyGLSurfaceView(Context context) {
            super(context);
            super.setEGLConfigChooser(8, 8, 8, 8, 16, 8);
            setEGLContextClientVersion(2);
        }

    }

}
