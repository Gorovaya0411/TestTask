package com.example.test_task.ui.scenes.activity

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
        val link: String = "http://gallery.dev.webant.ru/media/${getModelImageAnswer.image.name}"
        Picasso.get()
            .load(link)
            .fit()
            .into(detailedImage)
        namePhoto.text = getModelImageAnswer.name
        descriptionPhoto.text = getModelImageAnswer.description

    }
}
