package com.nollpointer.dates.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.nollpointer.dates.R
import com.nollpointer.dates.databinding.ItemTestAnswerBinding
import com.nollpointer.dates.model.Date
import com.nollpointer.dates.model.Practise.Companion.TYPE_DATE

/**
 * @author Onanov Aleksey (@onanov)
 */
class TestAnswerButton @JvmOverloads constructor(
        context: Context?,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    var setOnAnswerButtonClickListener: ((TestAnswerButton) -> Unit)? = null
    var setOnDetailsClickListener: ((Date) -> Unit)? = null

    private var isDetailsMode = false
    private var currentDate = Date()

    private val binding: ItemTestAnswerBinding = ItemTestAnswerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.root.setOnClickListener {
            if (!this@TestAnswerButton.isClickable) return@setOnClickListener
            if (isDetailsMode)
                setOnDetailsClickListener?.invoke(currentDate)
            else
                setOnAnswerButtonClickListener?.invoke(this)
        }
    }

    fun setDate(type: Int, date: Date) {
        currentDate = date
        isDetailsMode = false
        binding.result.visibility = View.INVISIBLE
        binding.text.text =
                if (type == TYPE_DATE) {
                    date.event
                } else {
                    if (currentDate.containsMonth)
                        "${currentDate.date}, ${currentDate.month}"
                    else
                        currentDate.date
                }
        binding.text.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
    }

    fun setResult(result: Boolean) {
        binding.result.apply {
            visibility = View.VISIBLE
            setBackgroundResource(
                    if (result)
                        R.color.colorTest
                    else
                        R.color.colorPrimary
            )
        }
    }

    fun setDetailsMode() {
        isDetailsMode = true
        binding.text.setCompoundDrawablesRelativeWithIntrinsicBounds(0,
                0, R.drawable.ic_help_gray, 0)
    }


}