package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec(
    {
        val bolivia = Pais.Builder()
            .nombre("Bolivia")
            .codigoIso3("BOL")
            .poblacion(11067000)
            .superficie(1098581.0)
            .continente("América")
            .codigoMoneda("BOB")
            .cotizacionDolar(6.87)
            .bloquesRegionales(mutableListOf<String>("UNASUR"))
            .idiomasOficiales(mutableListOf<String>("Español", "Quechua", "Aymara"))
            .build()

        val argentina = Pais.Builder()
            .nombre("Argentina")
            .codigoIso3("ARG")
            .poblacion(45038000)
            .superficie(2078000.0)
            .continente("América")
            .codigoMoneda("ARG")
            .cotizacionDolar(125.59)
            .bloquesRegionales(mutableListOf<String>("MERCOSUR","UNASUR"))
            .idiomasOficiales(mutableListOf<String>("Español"))
            .build()

        val estadosUnidos = Pais.Builder()
            .nombre("Estados Unidos")
            .codigoIso3("USA")
            .poblacion(329005000)
            .superficie(9834000.0)
            .continente("América")
            .codigoMoneda("DOL")
            .cotizacionDolar(1.0)
            .bloquesRegionales(mutableListOf<String>("ALIANZA DEL PACÍFICO"))
            .idiomasOficiales(mutableListOf<String>("Inglés"))
            .build()

        val italia = Pais.Builder()
            .nombre("Italia")
            .codigoIso3("ITA")
            .poblacion(59055000)
            .superficie(301230.0)
            .continente("Europa")
            .codigoMoneda("EUR")
            .cotizacionDolar(1.02)
            .bloquesRegionales(mutableListOf<String>("UNIÓN EUROPEA"))
            .idiomasOficiales(mutableListOf<String>("Italiano"))
            .build()

        val portugal = Pais.Builder()
            .nombre("Portugal")
            .codigoIso3("PRT")
            .poblacion(10031000)
            .superficie(92.212)
            .continente("Europa")
            .codigoMoneda("EUR")
            .cotizacionDolar(1.02)
            .bloquesRegionales(mutableListOf<String>("UNIÓN EUROPEA"))
            .idiomasOficiales(mutableListOf<String>("Portugués", "Español", "Rumano", "Italiano", "Francés"))
            .build()

        val nigeria = Pais.Builder()
            .nombre("Nigeria")
            .codigoIso3("NGA")
            .poblacion(206001000)
            .superficie(923.768)
            .continente("Africa")
            .codigoMoneda("NAI")
            .cotizacionDolar(415.40)
            .bloquesRegionales(mutableListOf<String>("ECOWAS"))
            .idiomasOficiales(mutableListOf<String>("Yoruba", "Igbo", "Hausa", "Inglés"))
            .build()

        bolivia.aniadirPaisesLimitrofes(argentina)
        argentina.aniadirPaisesLimitrofes(bolivia)

        Observatorio.paisesRegistrados.clear()

        Observatorio.registrarPais(bolivia)
        Observatorio.registrarPais(argentina)
        Observatorio.registrarPais(estadosUnidos)
        Observatorio.registrarPais(italia)
        Observatorio.registrarPais(portugal)
        Observatorio.registrarPais(nigeria)

        describe("Test Observatorio") {
            it("Argentina y Bolivia son limitrofes") {
                Observatorio.sonLimitrofes("Argentina" ,"Bolivia").shouldBe(true)
            }
            it("Argentina y Bolivia no necesitan traducción.") {
                Observatorio.necesitanTraduccion("Argentina", "Bolivia").shouldBe(false)
            }
            it("Argentina y Bolivia son potenciales aliados.") {
                Observatorio.sonPotencialesAliados("Argentina", "Bolivia").shouldBe(true)
            }
            it("No conviene ir de compras de Argentina a Bolivia.") {
                Observatorio.convieneIrDeCompras("Argentina", "Bolivia").shouldBe(false)
            }
            it("25 ARG equivalen a 1.37 BOL") {
                Observatorio.aCuantoEquivale(25,"Argentina", "Bolivia").shouldBe(1.37)
            }
            it("Los codigos ISO de los paises más poblados son: NGA, PRT, ITA, USA, ARG ") {
                Observatorio.codigoIsoPaisesMasPoblacion().shouldBe(listOf("NGA", "PRT", "ITA", "USA", "ARG"))
            }
            it("El continente con más paises plurinacionales es América.") {
                Observatorio.continenteConMasPaisesPlurinacionales().shouldBe("América")
            }
            it("El promedio de densidad poblacional de los paises insulares es de 83003") {
                Observatorio.promedioDensidadPoblacionalInsulares().shouldBe(83003)
            }
        }
    }
)