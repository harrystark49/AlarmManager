package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alarmmanager.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var picker:MaterialTimePicker
    lateinit var calendar: Calendar
    lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cancel.setOnClickListener {

            }
            setAlarm.setOnClickListener {

                setAlarms()
            }
            selectTime.setOnClickListener {
                showTimePicker()
            }
        }
    }

    private fun setAlarms() {

        var intent= Intent(this,AlarmReceiver::class.java)
        var pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )

        Toast.makeText(this, "settt", Toast.LENGTH_SHORT).show()
    }

    private fun showTimePicker() {
        picker=MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("select alarm")
            .build()

        picker.show(supportFragmentManager,"alarm")
        picker.addOnPositiveButtonClickListener {
            if(picker.hour>12){
                binding.selectTime.text=String.format(
                    "%2d",picker.hour-12)+" : "+ String.format("%2d",picker.minute)+"PM"

            }else{
                binding.selectTime.text=String.format(
                    "%2d",picker.hour)+" : "+ String.format("%2d",picker.minute)+"AM"

            }

            calendar= Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY]=picker.hour
            calendar[Calendar.MINUTE]=picker.minute
            calendar[Calendar.SECOND]=0
            calendar[Calendar.MILLISECOND]=0
        }
    }
}