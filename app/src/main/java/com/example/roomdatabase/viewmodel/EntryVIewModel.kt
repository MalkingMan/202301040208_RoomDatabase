package com.example.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.myroomsatu.repositori.RepositorySiswa
import com.example.myroomsatu.room.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// -------------------------
// DATA CLASS DETAIL SISWA
// -------------------------
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// -------------------------
// UI STATE SISWA
// -------------------------
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

// -------------------------
// EXTENSION: DetailSiswa -> Entity Siswa
// -------------------------
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)