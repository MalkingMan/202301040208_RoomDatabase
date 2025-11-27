package com.example.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.roomdatabase.room.Siswa
import com.example.roomdatabase.repositori.RepositoriSiswa

// -------------------------
// DATA CLASS DETAIL SISWA
// -------------------------
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telepon: String = ""
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
    telepon = telepon
)


// -------------------------
// EXTENSION: Entity Siswa -> UIState
// -------------------------
fun Siswa.toUIStateSiswa(isEntryValid: Boolean = false): UIStateSiswa =
    UIStateSiswa(
        detailSiswa = this.toDetailSiswa(),
        isEntryValid = isEntryValid
    )

// -------------------------
// EXTENSION: Entity Siswa -> DetailSiswa
// -------------------------
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telepon = telepon
)

// -------------------------
// VIEWMODEL
// -------------------------
class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // -------------------------
    // VALIDASI INPUT
    // -------------------------
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telepon.isNotBlank()
        }
    }

    // -------------------------
    // UPDATE STATE UI
    // -------------------------
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(
                detailSiswa = detailSiswa,
                isEntryValid = validasiInput(detailSiswa)
            )
    }

    // -------------------------
    // SIMPAN DATA
    // -------------------------
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(
                siswa = uiStateSiswa.detailSiswa.toSiswa()
            )
        }
    }
}