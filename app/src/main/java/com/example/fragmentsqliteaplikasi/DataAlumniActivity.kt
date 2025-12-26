package com.example.fragmentsqliteaplikasi

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DataAlumniActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var listView: ListView
    private lateinit var adapter: AlumniAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_alumni)

        dbHelper = DatabaseHelper(this)
        listView = findViewById(R.id.lvDataAlumni)

        loadData()

        // Handle Klik Item
        listView.setOnItemClickListener { _, _, position, _ ->
            // Ambil cursor pada posisi yang diklik
            val cursor = adapter.getItem(position) as Cursor

            val intent = Intent(this, DetailAlumniActivity::class.java)
            // Kirim SEMUA kolom
            intent.putExtra("id", cursor.getString(cursor.getColumnIndexOrThrow("_id"))) // ID (alias)
            intent.putExtra("nim", cursor.getString(cursor.getColumnIndexOrThrow("nim")))
            intent.putExtra("nama", cursor.getString(cursor.getColumnIndexOrThrow("nama")))
            intent.putExtra("tempat_lahir", cursor.getString(cursor.getColumnIndexOrThrow("tempat_lahir")))
            intent.putExtra("tanggal_lahir", cursor.getString(cursor.getColumnIndexOrThrow("tanggal_lahir")))
            intent.putExtra("alamat", cursor.getString(cursor.getColumnIndexOrThrow("alamat")))
            intent.putExtra("agama", cursor.getString(cursor.getColumnIndexOrThrow("agama")))
            intent.putExtra("tlp_hp", cursor.getString(cursor.getColumnIndexOrThrow("tlp_hp")))
            intent.putExtra("tahun_masuk", cursor.getString(cursor.getColumnIndexOrThrow("tahun_masuk")))
            intent.putExtra("tahun_lulus", cursor.getString(cursor.getColumnIndexOrThrow("tahun_lulus")))
            intent.putExtra("pekerjaan", cursor.getString(cursor.getColumnIndexOrThrow("pekerjaan")))
            intent.putExtra("jabatan", cursor.getString(cursor.getColumnIndexOrThrow("jabatan")))

            startActivity(intent)
        }
    }

    // Dipanggil saat activity dimulai kembali (misal setelah tambah data)
    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val cursor = dbHelper.getAllAlumni()

        if (cursor.count == 0) {
            Toast.makeText(this, "Belum ada data alumni!", Toast.LENGTH_SHORT).show()
        }

        adapter = AlumniAdapter(this, cursor)
        listView.adapter = adapter
    }
}