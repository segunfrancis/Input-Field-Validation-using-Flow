package com.project.segunfrancis.inputfieldvalidationusingflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.project.segunfrancis.inputfieldvalidationusingflow.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailEditText.addTextChangedListener {
            viewModel.setEmail(it.toString())
        }
        binding.passwordEditText.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isLoginEnabled.collect {
                binding.loginButton.enabled(it)
            }
        }
    }

    private fun View.enabled(value: Boolean) {
        if (value) {
            alpha = 1F
            isEnabled = true
        } else {
            alpha = 0.4F
            isEnabled = false
        }
    }
}