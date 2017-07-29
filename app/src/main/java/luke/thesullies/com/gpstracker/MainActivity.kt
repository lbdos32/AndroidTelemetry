package luke.thesullies.com.gpstracker


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener2 {

    val LOG_TAG = "LOG_TELEMETRY";

    var isSensorRunning :Boolean = true

    val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate")
        setContentView(R.layout.activity_main)

        toggleButton.setOnClickListener {
            // Luke's code here.
             isSensorRunning = ! isSensorRunning
             if (isSensorRunning == true){
                 startSensor()
                 Toast.makeText(this, "Sensor is on, go ahead and smash your phone.", Toast.LENGTH_LONG).show()

             }
            else {
                stopSensor()
                 Toast.makeText(this, "Sensor is off, did you smash your phone?", Toast.LENGTH_SHORT).show()

             }

        }
    }



    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
        sensorManager.unregisterListener(this);
    }


    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
        if (isSensorRunning == true) {
            Log.d(LOG_TAG, "sensor was previously running, restarting it now")
            startSensor()
        }
    }


    fun startSensor() {
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    fun stopSensor() {
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
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
        //Log.d(LOG_TAG, event!!.values.zip("XYZ".toList()).fold("") {acc, pair -> "$acc${pair.second}: {$pair.first}"});
    }



}
