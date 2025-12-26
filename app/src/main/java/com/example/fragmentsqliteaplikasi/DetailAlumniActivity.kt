package com.example.fragmentsqliteaplikasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class DetailAlumniActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var alumniId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_alumni)

        dbHelper = DatabaseHelper(this)

        // Ambil data dari Intent (dikirim dari DataAlumniActivity)
        alumniId = intent.getStringExtra("id") // Pastikan DataAlumniActivity mengirim ID ini!
        val nim = intent.getStringExtra("nim")
        val nama = intent.getStringExtra("nama")
        val tempatLahir = intent.getStringExtra("tempat_lahir")
        val tanggalLahir = intent.getStringExtra("tanggal_lahir")
        val alamat = intent.getStringExtra("alamat")
        val agama = intent.getStringExtra("agama")
        val tlp_hp = intent.getStringExtra("tlp_hp")
        val tahunMasuk = intent.getStringExtra("tahun_masuk")
        val tahunLulus = intent.getStringExtra("tahun_lulus")
        val pekerjaan = intent.getStringExtra("pekerjaan")
        val jabatan = intent.getStringExtra("jabatan")

        // Set Data ke TextView
        findViewById<TextView>(R.id.tvDetailNim).text = nim
        findViewById<TextView>(R.id.tvDetailNama).text = nama
        findViewById<TextView>(R.id.tvDetailTempatLahir).text = tempatLahir
        findViewById<TextView>(R.id.tvDetailTanggalLahir).text = tanggalLahir
        findViewById<TextView>(R.id.tvDetailAlamat).text = alamat
        findViewById<TextView>(R.id.tvDetailAgama).text = agama
        findViewById<TextView>(R.id.tvDetailTlp).text = tlp_hp
        findViewById<TextView>(R.id.tvDetailTahunMasuk).text = tahunMasuk
        findViewById<TextView>(R.id.tvDetailTahunLulus).text = tahunLulus
        findViewById<TextView>(R.id.tvDetailPekerjaan).text = pekerjaan
        findViewById<TextView>(R.id.tvDetailJabatan).text = jabatan

        // Tombol Hapus
        findViewById<Button>(R.id.btnHapus).setOnClickListener {
            showDeleteConfirmation()
        }
        // Tombol Ubah
        findViewById<Button>(R.id.btnUbah).setOnClickListener {
            val intentEdit = Intent(this, EditDataActivity::class.java)
            // Kirim semua data ke halaman Edit agar form terisi otomatis
            intentEdit.putExtra("id", alumniId)
            intentEdit.putExtra("nim", nim)
            intentEdit.putExtra("nama", nama)
            intentEdit.putExtra("tempat_lahir", tempatLahir)
            intentEdit.putExtra("tanggal_lahir", tanggalLahir)
            intentEdit.putExtra("alamat", alamat)
            intentEdit.putExtra("agama", agama)
            intentEdit.putExtra("tlp_hp", tlp_hp)
            intentEdit.putExtra("tahun_masuk", tahunMasuk)
            intentEdit.putExtra("tahun_lulus", tahunLulus)
            intentEdit.putExtra("pekerjaan", pekerjaan)
            intentEdit.putExtra("jabatan", jabatan)
            startActivity(intentEdit)
            finish() // Tutup halaman detail agar nanti refresh saat kembali
        }
    }

    private fun showDeleteConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Hapus Data")
            .setMessage("Apakah anda yakin ingin menghapus data alumni ini?")
            .setPositiveButton("Ya") { _, _ ->
                val result = dbHelper.deleteAlumni(alumniId ?: "")
                if (result > 0) {
                    Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke list alumni
                } else {
                    Toast.makeText(this, "Gagal menghapus data", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}