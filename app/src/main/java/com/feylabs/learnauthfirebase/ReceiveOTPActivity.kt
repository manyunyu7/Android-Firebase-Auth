package com.feylabs.learnauthfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.feylabs.learnauthfirebase.databinding.ActivityReceiveOtpactivityBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class ReceiveOTPActivity : AppCompatActivity() {

    val binding by lazy { ActivityReceiveOtpactivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val number = intent.getStringExtra("mobile")
        val verifId = intent.getStringExtra("verificationId")

        binding.tvDesc.text = String.format("${binding.tvDesc.text} 62-%s", number)

        binding.btnVerif.setOnClickListener {
            binding.apply {
                val code = "${inputCode1.text}" +
                        "${inputCode2.text}" +
                        "${inputCode3.text}" +
                        "${inputCode4.text}" +
                        "${inputCode5.text}" +
                        "${inputCode6.text}"
                if (verifId != null) {
                    progressBar.visibility = View.VISIBLE
                    btnVerif.visibility = View.GONE

                    val phoneAuthCredential = PhoneAuthProvider.getCredential(
                        verifId, code
                    )

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener {
                            progressBar.visibility = View.GONE
                            btnVerif.visibility = View.VISIBLE

                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this@ReceiveOTPActivity,
                                    "Login Berhasil",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent =
                                    Intent(this@ReceiveOTPActivity, BaseAppContainerActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@ReceiveOTPActivity,
                                    "Kode OTP Salah",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                }
            }
        }



        keyboardOTPListener()


    }


    private fun keyboardOTPListener() {
        binding.inputCode1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    binding.inputCode2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.inputCode2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    binding.inputCode3.requestFocus()
                } else {
                    binding.inputCode1.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.inputCode3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    binding.inputCode4.requestFocus()
                } else {
                    binding.inputCode2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.inputCode4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    binding.inputCode5.requestFocus()
                } else {
                    binding.inputCode3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.inputCode5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    binding.inputCode6.requestFocus()
                } else {
                    binding.inputCode4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.inputCode6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isEmpty()) {
                    binding.inputCode5.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }
}