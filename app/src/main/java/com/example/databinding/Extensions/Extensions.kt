package com.example.databinding

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;


fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

