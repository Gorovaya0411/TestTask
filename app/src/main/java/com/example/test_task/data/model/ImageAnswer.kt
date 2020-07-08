package com.example.test_task.data.model

import java.io.Serializable

data class ImageAnswer(
    val id: Int,
    val name: String,
    val description: String,
    val new: Boolean,
    val popular: Boolean,
    val image: ImageInfo
) : Serializable