package com.example.fragmentsqliteaplikasi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_profile.xml
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inisialiasi Komponen UI
        val tvEmail = view.findViewById<TextView>(R.id.tvProfileEmail)
        val tvNim = view.findViewById<TextView>(R.id.tvProfileNim)
        val tvNama = view.findViewById<TextView>(R.id.tvProfileNama)
        val tvKelas = view.findViewById<TextView>(R.id.tvProfileKelas)
        val btnLogout = view.findViewById<Button>(R.id.btnProfileLogout)

        // 1. Ambil Data dari SharedPreferences
        // Gunakan nama file "UserSession" sesuai yang dibuat di LoginActivity
        val sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)

        val email = sharedPreferences.getString("email", "Tidak ada data")
        val nim = sharedPreferences.getString("nim", "-")
        val nama = sharedPreferences.getString("nama", "-")
        val kelas = sharedPreferences.getString("kelas", "-")

        // 2. Tampilan Data ke TextView
        tvEmail.text = email
        tvNim.text = nim
        tvNama.text = nama
        tvKelas.text = kelas

        // 3. Aksi Tombol Logout
        btnLogout.setOnClickListener {
            showLogoutDialog()
        }

        return view
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext()) // Gunakan requireContext() di Fragment
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                performLogout()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun performLogout() {
        // Hapus Session
        val sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Menghapus semua data login
        editor.apply()

        Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()

        // Kembali ke LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        // Bersihkan back stack agar user tidak bisa kembali ke halaman profile
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}