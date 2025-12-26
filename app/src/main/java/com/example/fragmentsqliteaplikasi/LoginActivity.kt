package com.example.fragmentsqliteaplikasi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    // Deklarasi variabel komponen UI
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    // Data Hardcode untuk simulasi login
    // Ganti "admin@email.com" dan "123" sesuai keinginan
    private val hardcodedEmail = "alfiansyahh883@gmail.com"
    private val hardcodedPassword = "Nyobalogin123"

    // Data Profil yang akan disimpan ke SharedPreferences
    private val myNim = "2290343010"
    private val myName = "Alfiansyah"
    private val myClass = "RJ22A"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan Activity dengan layout activity_login.xml
        setContentView(R.layout.activity_login)

        // Cek apakah pengguna sudah login sebelumnya
        checkLoginStatus()

        // Inisialisasi Komponen (hubungkan variabel dengan ID di XML)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Memberi aksi ketika tombol Login diklik
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val inputEmail = etEmail.text.toString().trim()
        val inputPassword = etPassword.text.toString().trim()

        // 1. Validasi: Pastikan Field Terisi
        if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(this, "Email dan Password harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        // 2. Validasi: Cek kecocokan dengan data hardcoded
        if (inputEmail == hardcodedEmail && inputPassword == hardcodedPassword) {

            // 3. Penyimpanan: Simpan data ke SharedPreferences
            saveToSharedPreferences(inputEmail)

            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()

            // Pindah ke halaman berikutnya(MainAcitivity / ProfilActivity)
            val intent = Intent(this, MainActivity::class.java) // Ganti MainActivity dengan ProfileActivity jika sudah ada
            startActivity(intent)
            finish()
        } else {
            // jika email/password salah
            Toast.makeText(this, "Email atau Password salah!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveToSharedPreferences(email: String) {
        // Membuat file SharedPreferences bernama "UserSession"
        // MODE_PRIVATE berarti hanya aplikasi ini yang bisa mengakses data ini
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Menyimpan data sesuai intruksi
        editor.putString("email", email)
        editor.putString("nim", myNim)
        editor.putString("nama", myName)
        editor.putString("kelas", myClass)

        // menandai bahwa user sudah login
        editor.putBoolean("isLoggedIn", true)

        // Menyimpan perubahan
        editor.apply()
    }

    private fun checkLoginStatus() {
        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        // Jika status isLoggedIn = true, langsung lempar ke halaman utama/profile
        if (isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java) // ganti ke ProfileActivity nantinya
            startActivity(intent)
            finish()
        }
    }
}