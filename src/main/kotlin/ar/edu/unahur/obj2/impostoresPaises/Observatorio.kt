package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    val paisesRegistrados = mutableListOf<Pais>()

    fun registrarPais(pais: Pais) {
        paisesRegistrados.add(pais)
    }

    private fun paisQueRepresenta(pais: String): Pais? {
        return if(paisesRegistrados.isNotEmpty() && paisesRegistrados.any { x -> x.nombre == pais }) {
            paisesRegistrados.find { a -> a.nombre == pais }
        } else throw Exception("Uno de los paises indicado no se encuentra registrado en el observatorio.")
    }

    fun sonLimitrofes(pais1: String, pais2: String): Boolean {
        return paisQueRepresenta(pais1)!!.esLimitrofeDe(paisQueRepresenta(pais2)!!)
    }

    fun necesitanTraduccion(pais1: String, pais2: String): Boolean {
        return paisQueRepresenta(pais1)!!.necesitaTraduccionCon(paisQueRepresenta(pais2)!!)
    }

    fun sonPotencialesAliados(pais1: String, pais2: String): Boolean {
        return paisQueRepresenta(pais1)!!.esPotencialAliadoDe(paisQueRepresenta(pais2)!!)
    }

    fun convieneIrDeCompras(pais1: String, pais2: String): Boolean {
        return paisQueRepresenta(pais1)!!.convieneIrDeComprasA(paisQueRepresenta(pais2)!!)
    }

    fun aCuantoEquivale(monto: Int, pais1: String, pais2: String): Double {
        return paisQueRepresenta(pais1)!!.aCuantoEquivaleEn(monto,paisQueRepresenta(pais2)!!)
    }

    fun codigoIsoPaisesMasPoblacion(): List<String> {
        if (paisesRegistrados.size >= 5) {
            return paisesRegistrados.sortedByDescending { it.densidadPoblacional() }
                .take(5)
                .map { pais -> pais.codigoIso3 }
        } else throw Exception("No se encuentran en el observatorio el mínimo de 5 paises necesarios para esta función.")
    }

    fun continenteConMasPaisesPlurinacionales(): String {
        val paisesPlurincionales = paisesRegistrados.filter { pais -> pais.esPlurinacional() }
        if(paisesPlurincionales.isNotEmpty()) {
            return paisesPlurincionales
                .groupBy { it.continente }
                .maxByOrNull { it.value.size }!!.key
        } else throw Exception("No se encontraron paises plurinacionales en el observatorio")
    }

    fun promedioDensidadPoblacionalInsulares(): Int {
        val paisesInsulares =  paisesRegistrados.filter { pais -> pais.esUnaIsla() }
        if(paisesInsulares.isNotEmpty()) {
            return paisesInsulares.sumBy { it.densidadPoblacional() } / paisesInsulares.size
        } else throw Exception("No se encontraron paises insulares en el observatorio.")
    }
}