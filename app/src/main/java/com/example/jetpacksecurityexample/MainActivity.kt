package com.example.jetpacksecurityexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        // val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        // Step 1: Create or retrieve the Master Key for encryption/decryption
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

// Step 2: Initialize/open an instance of EncryptedSharedPreferences
        val sharedPreferences = EncryptedSharedPreferences.create(
            "PreferencesFilename",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        savebtn.setOnClickListener {
            // Step 3: Save data to the EncryptedSharedPreferences as usual
            sharedPreferences.edit()
                .putString("DATA", getencrpty.text.toString())
                .apply()
        }

        // Step 4: Read data from EncryptedSharedPreferences as usual
        val value = sharedPreferences.getString("DATA", "tea")
        showtext.text = value
    }
}
