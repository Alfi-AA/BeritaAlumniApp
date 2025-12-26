package com.example.fragmentsqliteaplikasi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

class BeritaFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_berita.xml
        val view = inflater.inflate(R.layout.fragment_berita, container, false)

        val listView = view.findViewById<ListView>(R.id.lvBerita)

        // 1.Siapkan Data Dummy
        val dataBerita = listOf(
            Berita(
                "Alumni TI Raih Penghargaan",
                "Seorang alumni angkatan 2020 berhasil meraih...",
                "Isi lengkap berita 1: Alumni bernama Budi berhasil memenangkan kompetisi startup nasional...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Lowongan Kerja BUMN",
                "Dibuka lowongan kerja besar-besaran untuk lulusan...",
                "Isi lengkap berita 2: BUMN membuka kesempatan karir bagi lulusan Teknik Informatika...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Reuni Akbar 2024",
                "Jangan lewatkan reuni akbar tahun ini di kampus...",
                "Isi lengkap berita 3: Panitia mengundang seluruh angkatan untuk hadir di Gedung Serbaguna...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Workshop Android Dev",
                "Pelatihan gratis pengembangan aplikasi Android...",
                "Isi lengkap berita 4: Workshop ini akan membahas Kotlin dan Jetpack Compose...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Beasiswa S2 Luar Negeri",
                "Info beasiswa LPDP terbaru untuk alumni...",
                "Isi lengkap berita 5: Syarat dan ketentuan beasiswa dapat dilihat di website resmi...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Startup Kampus Meroket",
                "Karya mahasiswa kini bernilai miliaran rupiah...",
                "Isi lengkap berita 6: Startup 'JualBeli' mendapat pendanaan seri A...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Dosen Tamu dari Google",
                "Google Engineer berbagi pengalaman di kampus...",
                "Isi lengkap berita 7: Seminar teknologi cloud computing dihadiri 500 peserta...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Update Kurikulum 2025",
                "Perubahan mata kuliah untuk menyesuaikan industri...",
                "Isi lengkap berita 8: Mata kuliah AI akan menjadi wajib mulai tahun depan...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Prestasi Olahraga Kampus",
                "Tim Basket kampus juara 1 tingkat provinsi...",
                "Isi lengkap berita 9: Pertandingan final berlangsung sengit melawan universitas tetangga...",
                R.drawable.ic_launcher_foreground
            ),
            Berita(
                "Jadwal Wisuda Periode 2",
                "Pendaftaran wisuda dibuka mulai minggu depan...",
                "Isi lengkap berita 10: Pastikan seluruh persyaratan administrasi sudah lengkap...",
                R.drawable.ic_launcher_foreground
            )
        )

        // 2.Set Adapter
        val adapter = BeritaAdapter(requireContext(), dataBerita)
        listView.adapter = adapter

        // 3. Handle Click Item
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedBerita = dataBerita[position]

            val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
            intent.putExtra("JUDUL", selectedBerita.judul)
            intent.putExtra("ISI", selectedBerita.isiLengkap)
            // intent.putExtra("GAMBAR", selectedBerita.gambar)
            startActivity(intent)
        }
        return view
    }
}