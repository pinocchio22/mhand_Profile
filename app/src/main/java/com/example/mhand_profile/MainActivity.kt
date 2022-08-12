package com.example.mhand_profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mhand_profile.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val pref = this.getPreferences(0)

        setDate()
        getProfile(pref)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val pref = this.getPreferences(0)
        val editor = pref.edit()

        return when (item.itemId) {
            R.id.save -> {
                setProfile(editor)
                Toast.makeText(this, "저장", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SetTextI18n")
    fun setDate() {
        binding.date.setOnClickListener {
            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

            val datepicker = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val month = monthOfYear + 1
                val calendar = Calendar.getInstance()
                val startday = calendar.apply { set(year, monthOfYear, dayOfMonth) }.timeInMillis
                val endday = System.currentTimeMillis()
                val newmonth = (getignore(endday)-getignore(startday))/1000/60/60/24/30

                calendar.set(year, monthOfYear, dayOfMonth)
                binding.date.text = "$year.$month.$dayOfMonth"
                binding.period.text = "${newmonth/12}년 ${newmonth%12}개월"
                }, year, month, day
            )
            datepicker.datePicker.maxDate = System.currentTimeMillis()
            datepicker.show()
        }
    }

    fun getignore(time : Long) : Long {
        return Calendar.getInstance().apply {
            timeInMillis = time
            set(Calendar.DAY_OF_MONTH, 0)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    fun setProfile(editor : SharedPreferences.Editor) {
        // 입사일
        editor.putString("date", binding.date.text.toString()).apply()

        // 재직기간
        editor.putString("period", binding.period.text.toString()).apply()

        //이메일
        editor.putString("email", binding.editEmail.text.toString()).apply()

        // 개발분야
        editor.putBoolean("mobile", binding.mobile.isChecked).apply()
        editor.putBoolean("backend", binding.backend.isChecked).apply()
        editor.putBoolean("frontend", binding.frontend.isChecked).apply()

        // 기술
        editor.putBoolean("android", binding.android.isChecked).apply()
        editor.putBoolean("ios", binding.ios.isChecked).apply()
        editor.putBoolean("kotlin", binding.kotlin.isChecked).apply()
        editor.putBoolean("swift", binding.swift.isChecked).apply()
        editor.putBoolean("compose", binding.compose.isChecked).apply()
        editor.putBoolean("swiftUI", binding.swiftUI.isChecked).apply()
        editor.putBoolean("retrofit", binding.retrofit.isChecked).apply()
        editor.putBoolean("alamofire", binding.alamofire.isChecked).apply()
        editor.putBoolean("firebase", binding.firebase.isChecked).apply()
        editor.putBoolean("blockchain", binding.blockchain.isChecked).apply()
        editor.putBoolean("ethereum", binding.ethereum.isChecked).apply()
        editor.putBoolean("klaytn", binding.klaytn.isChecked).apply()
        editor.putBoolean("caver", binding.caver.isChecked).apply()
        editor.putBoolean("web3j", binding.web3j.isChecked).apply()
        editor.putBoolean("web3swift", binding.web3swift.isChecked).apply()
    }

    fun getProfile(pref : SharedPreferences) {
        // 입사일
        binding.date.text = pref.getString("date", "2020.01.01.")

        // 재직기간
        binding.period.text = pref.getString("period", "2년 7개월")

        // 이메일
        binding.editEmail.setText(pref.getString("email", "hgd200101@fingerlabs.io"))

        // 개발분야
        binding.mobile.isChecked = pref.getBoolean("mobile", true)
        binding.backend.isChecked = pref.getBoolean("backend", false)
        binding.frontend.isChecked = pref.getBoolean("frontend", false)

        // 기술
        binding.android.isChecked = pref.getBoolean("android", true)
        binding.ios.isChecked = pref.getBoolean("ios", false)
        binding.kotlin.isChecked = pref.getBoolean("kotlin", true)
        binding.swift.isChecked = pref.getBoolean("swift", false)
        binding.compose.isChecked = pref.getBoolean("compose", false)
        binding.swiftUI.isChecked = pref.getBoolean("swiftUI", false)
        binding.retrofit.isChecked = pref.getBoolean("retrofit", false)
        binding.alamofire.isChecked = pref.getBoolean("alamofire", false)
        binding.firebase.isChecked = pref.getBoolean("firebase", false)
        binding.blockchain.isChecked = pref.getBoolean("blockchain", false)
        binding.ethereum.isChecked = pref.getBoolean("ethereum", false)
        binding.klaytn.isChecked = pref.getBoolean("klaytn", false)
        binding.caver.isChecked = pref.getBoolean("caver", false)
        binding.web3j.isChecked = pref.getBoolean("web3j", false)
        binding.web3swift.isChecked = pref.getBoolean("web3swift", false)
    }
}