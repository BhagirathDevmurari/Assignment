package com.example.assignment.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.transformationlayout.onTransformationEndContainer
import dagger.android.support.DaggerAppCompatActivity

/** An abstract activity extending [AppCompatActivity] with registering transformation automatically. */
abstract class TransformationAppCompatActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.hasExtra("com.skydoves.transformationlayout"))
            onTransformationEndContainer(intent.getParcelableExtra("com.skydoves.transformationlayout"))
        super.onCreate(savedInstanceState)
    }
}
