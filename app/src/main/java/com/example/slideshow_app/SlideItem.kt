package com.example.slideshow_app


data class FashionItem(
    val imageResId: Int,
    val title: String,
    val description: String,
    val price: String = ""
)