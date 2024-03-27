package hr.tvz.android.kalkulatortihic

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        //seekBar listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Toast.makeText(this@MainActivity, "Hello $progress", Toast.LENGTH_LONG).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // This method is called when the user starts tracking touch on the seek bar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // This method is called when the user stops tracking touch on the seek bar
            }
        })

    }
}