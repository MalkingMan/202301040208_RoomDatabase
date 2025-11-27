package com.example.roomdatabase.view.route

interface DestinyNavigation {
    /**
     * Nama unik yang digunakan sebagai jalur untuk setiap composable.
     */
    val route: String

    /**
     * Resource ID berupa string yang menjadi judul halaman.
     */
    val titleRes: Int
}