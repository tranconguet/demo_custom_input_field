package com.tranconguet.democustominputfield

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class CustomInputField : FrameLayout {

    private var suffixIcon: ImageView? = null
    private var suffixText: TextView? = null
    private var editText: EditText? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
//        initByAttribute(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initByAttribute(attrs, defStyleAttr)
    }

    @SuppressLint("InflateParams", "Recycle")
    private fun initByAttribute(attrs: AttributeSet?, defStyleAttr: Int) {
        // get attributes
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomInputField, defStyleAttr, 0)
        val suffixTextAttr = a.getString(R.styleable.CustomInputField_suffixText)
        val hintTextAttr = a.getString(R.styleable.CustomInputField_hintText)
        val suffixIconResourceAttr = a.getResourceId(R.styleable.CustomInputField_suffixIcon, 0)
        // get view
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.simple_input_field, null)
        editText = view.findViewById(R.id.edtCustomEditText)
        suffixText = view.findViewById(R.id.tvSuffixText)
        suffixIcon = view.findViewById(R.id.ivSuffixIcon)
        // set attributes into view
        editText?.hint = hintTextAttr
        suffixText?.text = suffixTextAttr
        suffixIcon?.setImageResource(suffixIconResourceAttr)
        addView(view)
    }

    fun setSuffixIconOnClickListener(onClickListener: () -> Unit) {
        suffixIcon?.setOnClickListener {
            onClickListener.invoke()
        }
        suffixText?.setOnClickListener {
            onClickListener.invoke()
        }
    }

    fun getText(): String = editText?.text.toString()

}