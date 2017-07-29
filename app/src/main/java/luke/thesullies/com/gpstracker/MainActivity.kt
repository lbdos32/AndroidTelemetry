package luke.thesullies.com.gpstracker

import android.location.LocationListener
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast




import android.app.Activity
import android.content.Context

import android.content.Intent

import android.content.IntentSender

import android.content.pm.PackageManager
import android.hardware.*

import android.location.Geocoder

import android.location.Location

import android.os.Bundle

import android.support.v4.app.ActivityCompat

import android.support.v7.app.AppCompatActivity

import android.util.Log

import android.view.View

import android.widget.Button

import android.widget.TextView

import com.google.android.gms.common.ConnectionResult

import com.google.android.gms.common.GoogleApiAvailability

import com.google.android.gms.common.api.GoogleApiClient

import com.google.android.gms.common.api.ResultCallback

import com.google.android.gms.common.api.Status

import com.google.android.gms.location.*

import java.text.DateFormat

import java.util.*


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener2 {

    val LOG_TAG = "LOG_TELEMETRY";


    val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate")
        setContentView(R.layout.activity_main)

    }



    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
        sensorManager.unregisterListener(this);
    }
    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")

        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }
    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        Log.d(LOG_TAG, "onCreateOptionsMenu")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(LOG_TAG, "onOptionsItemSelected")

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d(LOG_TAG, "onAccuracyChanged")
    }

    override fun onFlushCompleted(p0: Sensor?) {
        Log.d(LOG_TAG, "onFlushCompleted")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //Log.d(LOG_TAG, "onSensorChanged")
        Log.d(LOG_TAG, event!!.values.zip("XYZ".toList()).fold("") {acc, pair -> "$acc${pair.second}: {$pair.first}"});
    }



}
