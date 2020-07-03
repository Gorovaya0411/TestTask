package com.example.test_task.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_task.R
import com.example.test_task.data.model.ImageAnswer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed_information.*

class DetailedInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_information)
        val getModelImageAnswer = intent.getSerializableExtra("KEY") as ImageAnswer
        namePhoto.text = getModelImageAnswer.name
        descriptionPhoto.text = getModelImageAnswer.description
        Picasso.get().load("http://gallery.dev.webant.ru/api/photos/media/ ${getModelImageAnswer.image.name}").fit().into(detailedImage)
    }
}
