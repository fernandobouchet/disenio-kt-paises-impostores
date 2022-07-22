package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt

class Pais(
    val nombre: String,
    val codigoIso3: String,
    val poblacion: Int,
    val superficie: Double,
    val continente: String,
    val codigoMoneda: String,
    val cotizacionDolar: Double,
    val bloquesRegionales: MutableList<String>,
    val idiomasOficiales: MutableList<String>
) {

    val paisesLimitrofes: MutableList<Pais> = mutableListOf()

    fun aniadirPaisesLimitrofes(pais: Pais) {
        paisesLimitrofes.add(pais)
    }

    fun esPlurinacional(): Boolean {
        return idiomasOficiales.size > 1
    }

    fun esUnaIsla(): Boolean {
        return paisesLimitrofes.isEmpty()
    }

    fun densidadPoblacional(): Int {
        return (poblacion / superficie).roundToInt()
    }

    fun vecinoMasPoblado(): Pais {
        val limitrofeMasPoblado =  paisesLimitrofes.maxByOrNull { it.poblacion }
        if ((limitrofeMasPoblado != null) && (limitrofeMasPoblado.poblacion > this.poblacion)) {
           return limitrofeMasPoblado
        } else {
            return this
        }
    }

    fun esLimitrofeDe(pais: Pais): Boolean {
        return this.paisesLimitrofes.contains(pais)
    }

    fun necesitaTraduccionCon(pais: Pais): Boolean {
        return !this.idiomasOficiales.any{idioma -> pais.idiomasOficiales.contains(idioma)}
    }

    fun esPotencialAliadoDe(pais: Pais): Boolean {
        return !this.necesitaTraduccionCon(pais) && this.bloquesRegionales.any{bloque -> pais.bloquesRegionales.contains(bloque)}
    }

    fun convieneIrDeComprasA(pais: Pais): Boolean {
        return this.cotizacionDolar < pais.cotizacionDolar
    }

    fun aCuantoEquivaleEn(monto : Int, pais: Pais): Double {
        return (((monto / this.cotizacionDolar) * pais.cotizacionDolar)* 100.0).roundToInt() / 100.0
    }
}