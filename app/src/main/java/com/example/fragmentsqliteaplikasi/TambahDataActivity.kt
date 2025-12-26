package com.example.fragmentsqliteaplikasi

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class TambahDataActivity : AppCompatActivity() {

    private lateinit var etNim: EditText
    private lateinit var etNama: EditText
    private lateinit var etTempatLahir: EditText
    private lateinit var etTanggalLahir: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etAgama: EditText
    private lateinit var etTlpHp: EditText
    private lateinit var etTahunMasuk: EditText
    private lateinit var etTahunLulus: EditText
    private lateinit var etPekerjaan: EditText
    private lateinit var etJabatan: EditText
    private lateinit var btnSimpan: Button
    private lateinit var btnKembali: Button

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)

        // Inisialisasi Database Helper
        dbHelper = DatabaseHelper(this)

        // Inisialisasi semua view
        initViews()

        // Setup listener untuk Tanggal Lahir
        etTanggalLahir.setOnClickListener {
            showDatePickerDialog()
        }

        // Setup listener untuk tombol Simpan
        btnSimpan.setOnClickListener {
            saveData()
        }

        // Setup listener untuk tombol Kembali
        btnKembali.setOnClickListener {
            finish() // Cukup tutup activitiy ini untuk kembali
        }
    }

    private fun initViews() {
        etNim = findViewById(R.id.etNim)
        etNama = findViewById(R.id.etNamaAlumni)
        etTempatLahir = findViewById(R.id.etTempatLahir)
        etTanggalLahir = findViewById(R.id.etTanggalLahir)
        etAlamat = findViewById(R.id.etAlamat)
        etAgama = findViewById(R.id.etAgama)
        etTlpHp = findViewById(R.id.etTlpHp)
        etTahunMasuk = findViewById(R.id.etTahunMasuk)
        etTahunLulus = findViewById(R.id.etTahunLulus)
        etPekerjaan = findViewById(R.id.etPekerjaan)
        etJabatan = findViewById(R.id.etJabatan)
        btnSimpan = findViewById(R.id.btnSimpan)
        btnKembali = findViewById(R.id.btnKembali)
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Format tanggal: DD/MM/YYYY
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                etTanggalLahir.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun saveData() {
        val nim = etNim.text.toString()
        val nama = etNama.text.toString()
        val tempatLahir = etTempatLahir.text.toString()
        val tanggalLahir = etTanggalLahir.text.toString()
        val alamat = etAlamat.text.toString()
        val agama = etAgama.text.toString()
        val tlp_hp = etTlpHp.text.toString()
        val tahunMasuk = etTahunMasuk.text.toString().toIntOrNull()
        val tahunLulus = etTahunLulus.text.toString().toIntOrNull()
        val pekerjaan = etPekerjaan.text.toString()
        val jabatan = etJabatan.text.toString()

        // Validasi sederhana: pastikan NIM dan Nama tidak kosong
        if (nim.isEmpty() || nama.isEmpty()) {
            Toast.makeText(this, "NIM dan Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        if (tahunMasuk == null || tahunLulus == null) {
            Toast.makeText(this, "Tahun Masuk dan Lulus harus berupa angka!", Toast.LENGTH_SHORT).show()
            return
        }

        val isInserted = dbHelper.insertAlumni(
            nim, nama, tempatLahir, tanggalLahir, alamat, agama, tlp_hp,
            tahunMasuk, tahunLulus, pekerjaan, jabatan
        )

        if (isInserted) {
            Toast.makeText(this, "Data alumni berhasil disimpan!", Toast.LENGTH_SHORT).show()
            finish() // Kembali ke MainActivity setelah berhasil menyimpan
        } else {
            Toast.makeText(this, "Gagal menyimpan data!", Toast.LENGTH_SHORT).show()
        }
    }
}