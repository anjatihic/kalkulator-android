package hr.tvz.android.kalkulatortihic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

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

    }

    fun calculateDiscountedP(view: View){
        //close keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        val resultViewer = findViewById<TextView>(R.id.textViewCalculationResult)
        val resultTitle: TextView = findViewById(R.id.textViewResult)

        val oPriceStr = findViewById<EditText>(R.id.origPriceInput).text.toString()
        val percentStr = findViewById<EditText>(R.id.percentInput).text.toString()

        if(oPriceStr.isNotEmpty() && percentStr.isNotEmpty()){
            val oPrice = oPriceStr.toDouble()
            val percent = percentStr.toDouble()

            val result = oPrice - (oPrice * (percent/100))
            val decimalFormat = DecimalFormat("#.00")

            resultTitle.visibility = TextView.VISIBLE
            resultViewer.text = buildString {
                append(decimalFormat.format(result))
                append(" €")
            }
        }else{
            Snackbar.make(view, "Unesite oba dva broja", Snackbar.LENGTH_LONG).show()
        }

    }
}