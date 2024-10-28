package com.example.utslabgreetingscard

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.greetingcardmaker.databinding.ActivityGreetingCardBinding
import com.google.android.material.snackbar.Snackbar

class GreetingCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGreetingCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreetingCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipient = intent.getStringExtra("EXTRA_RECIPIENT")
        val message = intent.getStringExtra("EXTRA_MESSAGE")

        binding.textViewRecipient.text = getString(R.string.greeting_to, recipient)
        binding.textViewMessage.text = message

        binding.buttonShare.setOnClickListener {
            if (recipient.isNullOrEmpty() || message.isNullOrEmpty()) {
                Snackbar.make(binding.root, R.string.error_empty_fields, Snackbar.LENGTH_SHORT).show()
            } else {
                // Implicit Intent to share the card
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "To: $recipient\n\n$message")
                }
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
            }
        }
    }
}