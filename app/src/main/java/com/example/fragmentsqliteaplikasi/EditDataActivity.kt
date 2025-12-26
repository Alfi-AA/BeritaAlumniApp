package com.example.fragmentsqliteaplikasi

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class EditDataActivity : AppCompatActivity() {

    // Deklarasi variabel sama seperti TambahDataActivity
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
    private var alumniId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data) // Reuse layout tambah data

        dbHelper = DatabaseHelper(this)
        initViews()

        // Ambil data dari Intent (Data Lama)
        val intent = intent
        alumniId = intent.getStringExtra("id")
        etNim.setText(intent.getStringExtra("nim"))
        etNama.setText(intent.getStringExtra("nama"))
        etTempatLahir.setText(intent.getStringExtra("tempat_lahir"))
        etTanggalLahir.setText(intent.getStringExtra("tanggal_lahir"))
        etAlamat.setText(intent.getStringExtra("alamat"))
        etAgama.setText(intent.getStringExtra("agama"))
        etTlpHp.setText(intent.getStringExtra("tlp_hp"))
        etTahunMasuk.setText(intent.getStringExtra("tahun_masuk"))
        etTahunLulus.setText(intent.getStringExtra("tahun_lulus"))
        etPekerjaan.setText(intent.getStringExtra("pekerjaan"))
        etJabatan.setText(intent.getStringExtra("jabatan"))

        btnSimpan.text = "Update Data" // Ubah teks tombol

        // Listener DatePicker
        etTanggalLahir.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this,
                { _, year, month, day -> etTanggalLahir.setText("$day/${month + 1}/$year") },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        }

        btnSimpan.setOnClickListener { updateData() }
        btnKembali.setOnClickListener { finish() }
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

    private fun updateData() {
        // Ambil data dari form
        val isUpdated = dbHelper.updateAlumni(
            alumniId ?: "",
            etNim.text.toString(), etNama.text.toString(), etTempatLahir.text.toString(),
            etTanggalLahir.text.toString(), etAlamat.text.toString(), etAgama.text.toString(),
            etTlpHp.text.toString(), etTahunMasuk.text.toString().toIntOrNull() ?: 0,
            etTahunLulus.text.toString().toIntOrNull() ?: 0, etPekerjaan.text.toString(),
            etJabatan.text.toString()
        )

        if (isUpdated) {
            Toast.makeText(this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show()
            finish() // Kembali ke Detail
        } else {
            Toast.makeText(this, "Gagal update data!", Toast.LENGTH_SHORT).show()
        }
    }
}