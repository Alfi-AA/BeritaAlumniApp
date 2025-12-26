package com.example.fragmentsqliteaplikasi


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Alumni.db"
        private const val TABLE_ALUMNI = "alumni_table"

        // Nama Kolom
        private const val COL_ID = "id"
        private const val COL_NIM = "nim"
        private const val COL_NAMA = "nama"
        private const val COL_TEMPAT_LAHIR = "tempat_lahir"
        private const val COL_TANGGAL_LAHIR = "tanggal_lahir"
        private const val COL_ALAMAT = "alamat"
        private const val COL_AGAMA = "agama"
        private const val COL_TLP_HP = "tlp_hp"
        private const val COL_TAHUN_MASUK = "tahun_masuk"
        private const val COL_TAHUN_LULUS = "tahun_lulus"
        private const val COL_PEKERJAAN = "pekerjaan"
        private const val COL_JABATAN = "jabatan"
    }

    // Fungsi ini dipanggil saat database dibuat pertama kali
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE " + TABLE_ALUMNI + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NIM + " TEXT, "
                + COL_NAMA + " TEXT, "
                + COL_TEMPAT_LAHIR + " TEXT, "
                + COL_TANGGAL_LAHIR + " TEXT, "
                + COL_ALAMAT + " TEXT, "
                + COL_AGAMA + " TEXT, "
                + COL_TLP_HP + " TEXT, "
                + COL_TAHUN_MASUK + " INTEGER, "
                + COL_TAHUN_LULUS + " INTEGER, "
                + COL_PEKERJAAN + " TEXT, "
                + COL_JABATAN + " TEXT)")
        db?.execSQL(createTableQuery)
    }

    // Fungsi ini dipanggil jika versi database diubah
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ALUMNI")
        onCreate(db)
    }

    // Fungsi untuk menambah data alumni ke database
    fun insertAlumni(
        nim: String, nama: String, tempatLahir: String, tanggalLahir: String, alamat: String,
        agama: String, tlp_hp: String, tahunMasuk: Int, tahunLulus: Int, pekerjaan: String, jabatan: String
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_NIM, nim)
            put(COL_NAMA, nama)
            put(COL_TEMPAT_LAHIR, tempatLahir)
            put(COL_TANGGAL_LAHIR, tanggalLahir)
            put(COL_ALAMAT, alamat)
            put(COL_AGAMA, agama)
            put(COL_TLP_HP, tlp_hp)
            put(COL_TAHUN_MASUK, tahunMasuk)
            put(COL_TAHUN_LULUS, tahunLulus)
            put(COL_PEKERJAAN, pekerjaan)
            put(COL_JABATAN, jabatan)
        }
        val result = db.insert(TABLE_ALUMNI, null, contentValues)
        // db.insert mengembalikan -1 jika gagal, selain ituberhasil
        return result != -1L
    }

    // Fungsi untuk mengambil semua data alumni
    fun getAllAlumni(): android.database.Cursor {
        val db = this.readableDatabase
        // Mengambil semua kolom dari tabel alumni
        return db.rawQuery("SELECT id AS _id,* FROM $TABLE_ALUMNI", null)
    }

    // Fungsi Update Data
    fun updateAlumni(
        id: String, nim: String, nama: String, tempatLahir: String, tanggalLahir: String,
        alamat: String, agama: String, tlp_hp: String, tahunMasuk: Int,
        tahunLulus: Int, pekerjaan: String, jabatan: String
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_NIM, nim)
            put(COL_NAMA, nama)
            put(COL_TEMPAT_LAHIR, tempatLahir)
            put(COL_TANGGAL_LAHIR, tanggalLahir)
            put(COL_ALAMAT, alamat)
            put(COL_AGAMA, agama)
            put(COL_TLP_HP, tlp_hp)
            put(COL_TAHUN_MASUK, tahunMasuk)
            put(COL_TAHUN_LULUS, tahunLulus)
            put(COL_PEKERJAAN, pekerjaan)
            put(COL_JABATAN, jabatan)
        }
        // Update berdasarkan ID
        val result = db.update(TABLE_ALUMNI, contentValues, "id = ?", arrayOf(id))
        return result > 0
    }

    // Fungsi Hapus Data
    fun deleteAlumni(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_ALUMNI, "id = ?", arrayOf(id))
    }
}