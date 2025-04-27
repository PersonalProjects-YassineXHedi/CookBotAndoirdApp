package com.example.cookbot.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.example.cookbot.Model.InputImageHelper;
import com.example.cookbot.databinding.ActivityCameraBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {
    private ActivityCameraBinding viewBinding;

    private ImageCapture imageCapture = null;

    private ExecutorService cameraExecutor;

    private static final String TAG = "CameraXApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        startCamera();

        viewBinding.imageCapture.setOnClickListener(v -> takePhoto());

        cameraExecutor = Executors.newSingleThreadExecutor();
    }

    private void takePhoto() {
        if (imageCapture == null) return;

        imageCapture.takePicture(
                ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageCapturedCallback() {
                    @Override
                    public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
                        File tempDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                        Uri imageUri = InputImageHelper.getImageUri(imageProxy, tempDir);

                        Intent intent = new Intent(CameraActivity.this, DisplayImageActivity.class);
                        intent.putExtra("image_uri", imageUri.toString());
                        startActivity(intent);
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        exception.printStackTrace();
                    }
                }
        );

    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                viewBinding.viewFinder.setScaleType(PreviewView.ScaleType.FILL_CENTER);

                Preview preview = new Preview.Builder().build();
                imageCapture =new ImageCapture.Builder().build();
                preview.setSurfaceProvider(viewBinding.viewFinder.getSurfaceProvider());

                // Select back camera as a default
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                // Unbind use cases before rebinding
                cameraProvider.unbindAll();

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture
                );

            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "Use case binding failed", e);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}
