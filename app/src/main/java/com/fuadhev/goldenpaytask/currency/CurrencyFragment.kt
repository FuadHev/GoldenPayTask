package com.fuadhev.goldenpaytask.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuadhev.goldenpaytask.R
import com.fuadhev.goldenpaytask.databinding.FragmentCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.round

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding
    private var isUpdating = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )


        val aznToUsd = 1.7
        val aznToEur = 1.81
        val aznToRub = 60.36


        // Add text watchers dynamically using ConversionType
        binding.etAzn.addTextChangedListener(createTextWatcher(ConversionType.AZN))
        binding.etUsd.addTextChangedListener(createTextWatcher(ConversionType.USD))
        binding.etEuro.addTextChangedListener(createTextWatcher(ConversionType.EURO))
        binding.etRuble.addTextChangedListener(createTextWatcher(ConversionType.RUBLE))
    }

    private fun createTextWatcher(source: ConversionType): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isUpdating) {
                    val value = s?.toString()?.toDoubleOrNull() ?: 0.0
                    val azn = when (source) {
                        ConversionType.USD -> value / source.conversionRate
                        ConversionType.EURO -> value / source.conversionRate
                        ConversionType.RUBLE -> value / source.conversionRate
                        ConversionType.AZN -> value
                    }
                    updateFields(azn, source)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun updateFields(azn: Double, source: ConversionType) {
        if (isUpdating) return
        isUpdating = true
        try {
            val fields = mapOf(
                ConversionType.AZN to Pair(binding.etAzn, azn),
                ConversionType.USD to Pair(binding.etUsd, azn * ConversionType.USD.conversionRate),
                ConversionType.EURO to Pair(binding.etEuro, azn * ConversionType.EURO.conversionRate),
                ConversionType.RUBLE to Pair(binding.etRuble, azn * ConversionType.RUBLE.conversionRate)
            )

            fields.forEach { (key, fieldAndValue) ->
                val field = fieldAndValue.first
                val value = fieldAndValue.second

                if (key != source) field.setText(if (value == 0.0) "" else String.format("%.2f", value))
            }
        } finally {
            isUpdating = false
        }
    }

}