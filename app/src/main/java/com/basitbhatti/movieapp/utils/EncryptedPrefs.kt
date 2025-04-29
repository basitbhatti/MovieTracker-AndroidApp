package com.basitbhatti.movieapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import java.security.GeneralSecurityException

class EncryptedPrefs(
    val sharedPrefs: SharedPreferences
) {

    companion object {
        fun create(context: Context) : EncryptedPrefs? {
            return try {
                val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
                val prefs = EncryptedSharedPreferences.create(
                    "enc-prefs",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                EncryptedPrefs(prefs)
            } catch (e : GeneralSecurityException){
                e.printStackTrace()
                null
            } catch (e: Exception){
                e.printStackTrace()
                null
            }
        }
    }


    fun getString(key: String) : String {
        return sharedPrefs!!.getString(key, "")!!
    }

    fun getBoolean(key: String) : Boolean {
        return sharedPrefs!!.getBoolean(key, false)!!
    }

    fun getInt(key: String) : Int {
        return sharedPrefs!!.getInt(key, 0)!!
    }

    fun setString(key: String, value: String) {
        sharedPrefs!!.edit().putString(key, value).apply()
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPrefs!!.edit().putBoolean(key, value).apply()
    }

    fun setInt(key: String, value: Int) {
        sharedPrefs!!.edit().putInt(key, value).apply()
    }


}