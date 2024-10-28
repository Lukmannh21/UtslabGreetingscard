package com.example.utslabgreetingscard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.greetingcardmaker.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonCreate: Button = binding.buttonCreate
        val editRecipient: EditText = binding.editRecipient
        val editMessage: EditText = binding.editMessage

        buttonCreate.setOnClickListener {
            val recipient = editRecipient.text.toString()
            val message = editMessage.text.toString()

            if (recipient.isEmpty() || message.isEmpty()) {
                Snackbar.make(binding.root, R.string.error_empty_fields, Snackbar.LENGTH_SHORT).show()
            } else {
                // Explicit Intent to navigate to GreetingCardActivity
                val intent = Intent(this, GreetingCardActivity::class.java)
                intent.putExtra("EXTRA_RECIPIENT", recipient)
                intent.putExtra("EXTRA_MESSAGE", message)
                startActivity(intent)
            }
        }
    }
}