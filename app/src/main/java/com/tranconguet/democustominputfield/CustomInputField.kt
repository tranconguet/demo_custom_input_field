package com.tranconguet.democustominputfield

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.content.res.ResourcesCompat

class CustomInputField : FrameLayout {

    private var linearLayoutInputField: LinearLayout? = null
    private var suffixIcon: ImageView? = null
    private var suffixText: TextView? = null
    private var editText: EditText? = null
    private var errorText: TextView? = null
    private var isError: Boolean = false

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
        Log.d("Test", "initByAttribute")
        // get attributes
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomInputField, defStyleAttr, 0)
        val suffixTextAttr = a.getString(R.styleable.CustomInputField_suffixText)
        val hintTextAttr = a.getString(R.styleable.CustomInputField_hintText)
        val suffixIconResourceAttr = a.getResourceId(R.styleable.CustomInputField_suffixIcon, 0)
        // get view
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.simple_input_field, null)
        linearLayoutInputField = view.findViewById(R.id.llInputField)
        editText = view.findViewById(R.id.edtCustomEditText)
        suffixText = view.findViewById(R.id.tvSuffixText)
        suffixIcon = view.findViewById(R.id.ivSuffixIcon)
        errorText = view.findViewById(R.id.tvError)
        // set attributes into view
        editText?.hint = hintTextAttr
        suffixText?.text = suffixTextAttr
        suffixIcon?.setImageResource(suffixIconResourceAttr)
        editText?.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            Log.d("Test", "onFocus $hasFocus")
        }
        updateView()
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

    fun getIsError(): Boolean = isError

    fun setIsError(error: Boolean) {
        isError = error
        updateView()
    }


    private fun updateView(){
        if (isError) {
            linearLayoutInputField?.background = ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.bd_red_radius_5dp,
                context.theme
            )
            errorText?.visibility = View.VISIBLE
        } else {
            linearLayoutInputField?.background = ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.bd_purple_3_radius_5dp,
                context.theme
            )
            errorText?.visibility = View.GONE
        }
    }

}