package com.joma.firstkotlinapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.joma.firstkotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var launch: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initListener()
        launchWord()
    }

    private fun initListener() {
        binding.btnPlus.setOnClickListener {
            val title: String = binding.etMainTitle.text.toString()
            if (title.isNotBlank()) {
                openActivity(title)
            } else {
                getToast(this, getString(R.string.empty_edit_text))
            }
        }
    }

    private fun launchWord() {
        launch =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val intent: Intent? = it.data
                    val title = intent?.getStringExtra("SendMessage")
                    binding.etMainTitle.setText(title)
                }
            }
    }

    private fun openActivity(title: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("main", title)
        launch.launch(intent)
    }

    private fun getToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}