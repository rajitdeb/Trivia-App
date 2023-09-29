package com.rajit.triviaapp.ui.interfaces

/**
 * Generic interface for implementing OnClick() functionality to the items of the Adapter
 */
interface OnClickInterface<T> {
    fun onClick(value: T)
}