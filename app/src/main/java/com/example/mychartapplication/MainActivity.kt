package com.example.mychartapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val chart by lazy { findViewById<com.github.mikephil.charting.charts.LineChart>(R.id.chart1) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setData(20, 30f)
    }

    private fun setData(count: Int, range: Float) {

        // now in hours
        val now: Long = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis())
        val values = ArrayList<Entry>()

        // count = hours
        val to = (now + count).toFloat()

        // increment by 1 hour
        var x = now.toFloat()
        while (x < to) {
            val y: Float = (Math.random() * range).toFloat()
            values.add(Entry(x, y))
            x++
        }

        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")
        set1.axisDependency = AxisDependency.LEFT
        set1.setColor(ColorTemplate.getHoloBlue())
        set1.setValueTextColor(ColorTemplate.getHoloBlue())
        set1.setLineWidth(1.5f)
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.fillAlpha = 65
        set1.setFillColor(ColorTemplate.getHoloBlue())
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(9f)

        // set data
        chart.setData(data)
    }

}