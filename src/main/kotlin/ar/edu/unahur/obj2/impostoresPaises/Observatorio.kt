package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    val paises = mutableListOf<Pais>()

    fun registrarPais(pais: Pais) {
        paises.add(pais)
    }

    private fun paisQueRepresenta(pais: String): Pais? {
        return if(paises.isNotEmpty() && paises.any { x -> x.nombre == pais }) {
            paises.find { a -> a.nombre == pais }
        } else throw Exception("El pais indicado no se encuentra registrado en el observatorio")
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
        return paises.sortedByDescending { it.densidadPoblacional() }
            .take(5)
            .map { pais -> pais.codigoIso3 }
    }

    fun continenteConMasPaisesPlurinacionales(): String {
        return paises.filter { pais -> pais.esPlurinacional() }
            .groupBy { it.continente }
            .maxByOrNull { it.value.size }!!.key
    }

    fun promedioDensidadPoblacionalInsulares(): Any {
        val paisesInsulares =  paises.filter { pais -> pais.esUnaIsla() }
        return paisesInsulares.sumBy { it.densidadPoblacional() } / paisesInsulares.size
    }
}