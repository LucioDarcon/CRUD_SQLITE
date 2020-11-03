package com.example.controlsales.util

import android.widget.EditText
import android.widget.TextView
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher

object CreateMaskToTextView {

    fun setMaskToTextViewData(editText: EditText): MaskTextWatcher {
        val simpleMaskFormatter = SimpleMaskFormatter("NN/NN/NNNN")
        return MaskTextWatcher(editText, simpleMaskFormatter)
    }

    fun setMaskToTextViewCpf(editText: EditText): MaskTextWatcher {
        val simpleMaskFormatter = SimpleMaskFormatter("NNN.NNN.NNN.NN")
        return MaskTextWatcher(editText, simpleMaskFormatter)
    }

    fun setMaskToTextViewTelephone(editText: EditText): MaskTextWatcher {
        val simpleMaskFormatter = SimpleMaskFormatter("(NN) NNNNN-NNNN")
        return MaskTextWatcher(editText, simpleMaskFormatter)
    }

}