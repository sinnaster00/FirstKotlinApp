package com.joma.firstkotlinapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.joma.firstkotlinapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initWord()
        initListener()
    }

    private fun initListener() {
        binding.bntSend.setOnClickListener {
            val secondTitle = binding.etSecondTitle.text.toString()
            if (secondTitle.isNotBlank()) {
                back(secondTitle)
            } else {
                getToast(this, getString(R.string.empty_edit_text))
            }
        }
    }

    private fun back(secondTitle: String) {
        val intent = Intent()
        intent.putExtra("SendMessage", secondTitle)
        setResult(RESULT_OK, intent)
        finish()
    }
        private fun initWord() {
            val extras: Bundle? = intent.extras
            val title = extras?.getString("main")
            binding.etSecondTitle.setText(title)
        }

    private fun getToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}