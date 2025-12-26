package com.example.fragmentsqliteaplikasi

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.CursorAdapter
import android.widget.TextView

class AlumniAdapter(context: Context, cursor: Cursor?) : CursorAdapter(context, cursor, 0) {

    // Mengambil layout item_alumni.xml
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.item_alumni, parent, false)
    }

    // Mengisi data ke TextView
    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val tvNim = view?.findViewById<TextView>(R.id.tvItemNim)
        val tvNama = view?.findViewById<TextView>(R.id.tvItemNama)

        // Ambil index kolom (sesuai nama kolom di DatabaseHelper)
        val nim = cursor?.getString(cursor.getColumnIndexOrThrow("nim"))
        val nama = cursor?.getString(cursor.getColumnIndexOrThrow("nama"))

        // Set teks sesuai format di gambar
        tvNim?.text = "Nik: $nim"
        tvNama?.text = "Nama: $nama"
    }

}