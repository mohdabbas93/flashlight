package com.mohdabbas.flashlight

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohdabbas.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isLightOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            val cameraId = cameraManager.cameraIdList[0]
            binding.lightSwitchButton.setOnClickListener {
                try {
                    cameraManager.setTorchMode(cameraId, !isLightOn)
                    binding.lightSwitchButton.text = if (isLightOn) "Open" else "Close"
                    isLightOn = isLightOn.not()
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}