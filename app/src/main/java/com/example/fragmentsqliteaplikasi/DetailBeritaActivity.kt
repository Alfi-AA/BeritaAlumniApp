package com.example.fragmentsqliteaplikasi

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        // Ambil data dari Intent
        val judul = intent.getStringExtra("JUDUL")
        val isi = intent.getStringExtra("ISI")
        // val gambar = intent.getIntExtra("GAMBAR", 0) // Jika gambar berupa drawable

        val tvJudul = findViewById<TextView>(R.id.tvDetailJudul)
        val tvIsi = findViewById<TextView>(R.id.tvDetailIsi)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        tvJudul.text = judul
        tvIsi.text = isi

        btnKembali.setOnClickListener {
            finish() // Menutup aktivitas saat tombol "Kembali" ditekan
        }
    }
}