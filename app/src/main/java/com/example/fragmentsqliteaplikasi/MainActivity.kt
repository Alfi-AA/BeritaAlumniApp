package com.example.fragmentsqliteaplikasi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Set default fragment ke Home saat pertama kali dibuka
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Listerner untuk Bottom Navigation (pindah-pindah fragment)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_berita -> {
                    loadFragment(BeritaFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Fungsi untuk membuat fragment ke dalam FrameLayout
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // --- KODE UNTUK MENU OPTION (TITIK TIGA DI ATAS) ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, TambahDataActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_data_alumni -> {
                val intent = Intent(this, DataAlumniActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                // Proses Logout
                showLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Fungsi untuk menampilkan Popup Konfirmasi
    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { _, _ ->
                performLogout() // Jika user pilih Tidak, tutup popup
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Jika user pilih Tidak, tutup popup
            }
            .create()
            .show()
    }

    // Logika Logout yang sesungguhnya dipindah ke sini
    private fun performLogout(){
        // 1. Hapus data session dari SharedPreferences
        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Menghapus semua data
        editor.apply()

        // 2. Kembali ke halaman Login
        Toast.makeText(this, "Logout berhasil!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        // Clear task agar user tidak bisa back ke MainActivity
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}