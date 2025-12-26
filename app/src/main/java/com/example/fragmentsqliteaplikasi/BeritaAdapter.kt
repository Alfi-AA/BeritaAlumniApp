package com.example.fragmentsqliteaplikasi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

// Model data sederhana untuk Berita
data class Berita(val judul: String, val deskripsi: String, val isiLengkap: String, val gambar: Int)

class BeritaAdapter (context: Context, data: List<Berita>) :
    ArrayAdapter<Berita>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Reuse view jika ada, jika tidak inflate layout baru
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false)
        }

        // Ambil item data saat ini
        val berita = getItem(position)

        // Binding data ke View
        val tvJudul = itemView!!.findViewById<TextView>(R.id.tvJudulBerita)
        val tvDeskripsi = itemView.findViewById<TextView>(R.id.tvDeskripsiPendek)
        val ivGambar = itemView.findViewById<ImageView>(R.id.ivGambarBerita)

        tvJudul.text = berita?.judul
        tvDeskripsi.text = berita?.deskripsi
        ivGambar.setImageResource(berita?.gambar ?: R.drawable.ic_launcher_foreground)

        return itemView
    }
}