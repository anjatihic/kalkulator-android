package hr.tvz.android.kalkulatortihic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import hr.tvz.android.kalkulatortihic.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener { view ->
            calculateDiscountedPrice(view)
        }

    }

    private fun calculateDiscountedPrice(view: View){
        //close keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        val resultViewer = binding.textViewCalculationResult
        val resultTitle = binding.textViewResult

        val oPriceStr = binding.origPriceInput.text.toString()
        val percentStr = binding.percentInput.text.toString()

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
            Snackbar.make(view, R.string.valid_message, Snackbar.LENGTH_LONG).show()
        }

    }
}