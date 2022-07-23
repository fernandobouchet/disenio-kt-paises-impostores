package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt

class Pais private constructor(
    val nombre: String?,
    val codigoIso3: String?,
    val poblacion: Int?,
    val superficie: Double?,
    val continente: String?,
    val codigoMoneda: String?,
    val cotizacionDolar: Double?,
    val bloquesRegionales: MutableList<String>?,
    val idiomasOficiales: MutableList<String>?
) {

    data class Builder (
        var nombre: String? = null,
        var codigoIso3: String? = null,
        var poblacion: Int? = null,
        var superficie: Double? = null,
        var continente: String? = null,
        var codigoMoneda: String? = null,
        var cotizacionDolar: Double? = null,
        var bloquesRegionales: MutableList<String>? = null,
        var idiomasOficiales: MutableList<String>? = null
    )
        {
            fun nombre(nombre: String) = apply {this.nombre = nombre}
            fun codigoIso3(codigoIso3: String) = apply {this.codigoIso3 = codigoIso3}
            fun poblacion(poblacion: Int) = apply {this.poblacion = poblacion}
            fun superficie(superficie: Double) = apply {this.superficie = superficie}
            fun continente(continente: String) = apply {this.continente = continente}
            fun codigoMoneda(codigoMoneda: String) = apply {this.codigoMoneda = codigoMoneda}
            fun cotizacionDolar(cotizacionDolar: Double) = apply {this.cotizacionDolar = cotizacionDolar}
            fun bloquesRegionales(bloquesRegionales: MutableList<String>) = apply {this.bloquesRegionales = bloquesRegionales}
            fun idiomasOficiales(idiomasOficiales: MutableList<String>) = apply {this.idiomasOficiales = idiomasOficiales}
            fun build() = Pais(nombre,codigoIso3, poblacion, superficie, continente, codigoMoneda, cotizacionDolar, bloquesRegionales, idiomasOficiales)
    }



    val paisesLimitrofes: MutableList<Pais> = mutableListOf()

    fun aniadirPaisesLimitrofes(pais: Pais) {
        paisesLimitrofes.add(pais)
    }

    fun esPlurinacional(): Boolean {
        return idiomasOficiales!!.size > 1
    }

    fun esUnaIsla(): Boolean {
        return paisesLimitrofes.isEmpty()
    }

    fun densidadPoblacional(): Int {
        return (poblacion!! / superficie!!).roundToInt()
    }

    fun vecinoMasPoblado(): Pais {
        val limitrofeMasPoblado =  paisesLimitrofes.maxByOrNull { it.poblacion!! }
        return if ((limitrofeMasPoblado != null) && (limitrofeMasPoblado.poblacion!! > this.poblacion!!)) {
            limitrofeMasPoblado
        } else {
            this
        }
    }

    fun esLimitrofeDe(pais: Pais): Boolean {
        return this.paisesLimitrofes.contains(pais)
    }

    fun necesitaTraduccionCon(pais: Pais): Boolean {
        return !this.idiomasOficiales!!.any{idioma -> pais.idiomasOficiales!!.contains(idioma)}
    }

    fun esPotencialAliadoDe(pais: Pais): Boolean {
        return !this.necesitaTraduccionCon(pais) && this.bloquesRegionales!!.any{bloque -> pais.bloquesRegionales!!.contains(bloque)}
    }

    fun convieneIrDeComprasA(pais: Pais): Boolean {
        return this.cotizacionDolar!! < pais.cotizacionDolar!!
    }

    fun aCuantoEquivaleEn(monto : Int, pais: Pais): Double {
        return (((monto / this.cotizacionDolar!!) * pais.cotizacionDolar!!)* 100.0).roundToInt() / 100.0
    }
}