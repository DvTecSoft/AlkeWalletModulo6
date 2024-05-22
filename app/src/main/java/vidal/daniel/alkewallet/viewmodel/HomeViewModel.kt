package vidal.daniel.alkewallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vidal.daniel.alkewallet.model.HomeModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel : ViewModel()
{
    //Variable de lista
    private val _txList = MutableLiveData<List<HomeModel>>()

    //Esta variable es la que se va a encargar de propagar el cambio a sus observadores
    val homeTx: LiveData<List<HomeModel>> = _txList

    //Método para cargar la lista de usuarios desde una fuente de datos (API, base de datos, etc.)
    fun loadTx() {
        // Simulando la carga de datos desde una fuente (reemplazar con tu lógica real)
        val tx = listOf(
            HomeModel(500, "Abono", stringToDate("2024-05-01", "yyyy-MM-dd"), "topup", 1 ,1),
            HomeModel(600, "Abono", stringToDate("2024-05-15", "yyyy-MM-dd"), "topup",1 ,2),
            HomeModel(-200, "Retiro", stringToDate("2024-05-21", "yyyy-MM-dd"), "topup",1 ,3),
            HomeModel(700, "Abono", stringToDate("2024-05-01", "yyyy-MM-dd"), "topup", 1 ,1),
            HomeModel(1000, "Abono", stringToDate("2024-05-15", "yyyy-MM-dd"), "topup",1 ,2),
            HomeModel(-1200, "Retiro", stringToDate("2024-05-21", "yyyy-MM-dd"), "topup",1 ,3),
            HomeModel(3500, "Abono", stringToDate("2024-05-01", "yyyy-MM-dd"), "topup", 1 ,1),
            HomeModel(5600, "Abono", stringToDate("2024-05-15", "yyyy-MM-dd"), "topup",1 ,2),
            HomeModel(-2200, "Retiro", stringToDate("2024-05-21", "yyyy-MM-dd"), "topup",1 ,3),
            HomeModel(35000, "Abono", stringToDate("2024-05-01", "yyyy-MM-dd"), "topup", 1 ,1),
            HomeModel(40600, "Abono", stringToDate("2024-05-15", "yyyy-MM-dd"), "topup",1 ,2),
            HomeModel(-20000, "Retiro", stringToDate("2024-05-21", "yyyy-MM-dd"), "topup",1 ,3),
            HomeModel(100500, "Abono", stringToDate("2024-05-01", "yyyy-MM-dd"), "topup", 1 ,1),
            HomeModel(765600, "Abono", stringToDate("2024-05-15", "yyyy-MM-dd"), "topup",1 ,2),
            HomeModel(-120200, "Retiro", stringToDate("2024-05-21", "yyyy-MM-dd"), "topup",1 ,3),
            HomeModel(478500, "Abono", stringToDate("2024-05-01", "yyyy-MM-dd"), "topup", 1 ,1),
            HomeModel(1452600, "Abono", stringToDate("2024-05-15", "yyyy-MM-dd"), "topup",1 ,2),
            HomeModel(-15200, "Retiro", stringToDate("2024-05-21", "yyyy-MM-dd"), "topup",1 ,3)
        )
        _txList.value = tx
    }

    // Función para convertir a formato fecha un string YYYY-MM-DD
    fun stringToDate(dateString: String, format: String): Date
    {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.parse(dateString) ?: Date() // Si la conversión falla, devuelve la fecha actual
    }





}