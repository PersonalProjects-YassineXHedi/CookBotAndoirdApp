package com.example.cookbot.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cookbot.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button openCameraButton;
    Button viewCartsButton;

    private static final List<String> REQUIRED_PERMISSIONS = new ArrayList<String>() {{
        add(Manifest.permission.CAMERA);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openCameraButton = findViewById(R.id.openCamera);
        openCameraButton.setEnabled(false);

        viewCartsButton = findViewById(R.id.viewCarts);

        if (allPermissionsGranted()) {
            openCameraButton.setEnabled(true);
        } else {
            requestPermissions();
        }

        openCameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });
    }




    private void requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS.toArray(new String[0]));
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private final ActivityResultLauncher<String[]> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.RequestMultiplePermissions(),
                    permissions -> {
                        boolean permissionGranted = true;
                        for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                            if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
                                permissionGranted = false;
                            }
                        }

                        if (!permissionGranted) {
                            Toast.makeText(getBaseContext(), "Permission request denied", Toast.LENGTH_SHORT).show();
                        } else {
                            openCameraButton.setEnabled(true);
                        }
                    }
            );


}