package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec(
    {
        val bolivia = Pais("Bolivia", "BOL",11067000, 1098581.0,"América", "BOB", 6.87, mutableListOf<String>("UNASUR"), mutableListOf<String>("Español", "Quechua", "Aymara"))
        val argentina = Pais("Argentina", "ARG",45038000, 2078000.0,"América", "ARG", 125.59, mutableListOf<String>("MERCOSUR","UNASUR"), mutableListOf<String>("Español"))
        val estadosUnidos = Pais("Estados Unidos", "USA",329005000, 9834000.0,"América", "DOL", 1.0, mutableListOf<String>("ALIANZA DEL PACÍFICO"), mutableListOf<String>("Inglés"))
        val italia = Pais("Italia", "ITA",59055000, 301230.0,"Europa", "EUR", 1.02, mutableListOf<String>("UNIÓN EUROPEA"), mutableListOf<String>("Italiano"))
        val portugal = Pais("Portugal", "PRT",10031000, 92.212,"Europa", "EUR", 1.02, mutableListOf<String>("UNIÓN EUROPEA"), mutableListOf<String>("Portugués", "Español", "Rumano", "Italiano", "Francés"))
        val nigeria = Pais("Nigeria", "NGA",206001000, 923.768,"Africa", "NAI", 415.40, mutableListOf<String>("ECOWAS"), mutableListOf<String>("Yoruba", "Igbo", "Hausa", "Inglés"))
        bolivia.aniadirPaisesLimitrofes(argentina)
        argentina.aniadirPaisesLimitrofes(bolivia)

        val observatorio = Observatorio.getInstance()

        observatorio.paisesRegistrados.clear()

        observatorio.registrarPais(bolivia)
        observatorio.registrarPais(argentina)
        observatorio.registrarPais(estadosUnidos)
        observatorio.registrarPais(italia)
        observatorio.registrarPais(portugal)
        observatorio.registrarPais(nigeria)

        describe("Test Observatorio") {
            it("Una variable nueva contiene la misma instancia") {
                val observatorio2 = Observatorio.getInstance()
                (observatorio2 == observatorio).shouldBe(true)
            }
            it("Argentina y Bolivia son limitrofes") {
                observatorio.sonLimitrofes("Argentina" ,"Bolivia").shouldBe(true)
            }
            it("Argentina y Bolivia no necesitan traducción.") {
                observatorio.necesitanTraduccion("Argentina", "Bolivia").shouldBe(false)
            }
            it("Argentina y Bolivia son potenciales aliados.") {
                observatorio.sonPotencialesAliados("Argentina", "Bolivia").shouldBe(true)
            }
            it("No conviene ir de compras de Argentina a Bolivia.") {
                observatorio.convieneIrDeCompras("Argentina", "Bolivia").shouldBe(false)
            }
            it("25 ARG equivalen a 1.37 BOL") {
                observatorio.aCuantoEquivale(25,"Argentina", "Bolivia").shouldBe(1.37)
            }
            it("Los codigos ISO de los paises más poblados son: NGA, PRT, ITA, USA, ARG ") {
                observatorio.codigoIsoPaisesMasPoblacion().shouldBe(listOf("NGA", "PRT", "ITA", "USA", "ARG"))
            }
            it("El continente con más paises plurinacionales es América.") {
                observatorio.continenteConMasPaisesPlurinacionales().shouldBe("América")
            }
            it("El promedio de densidad poblacional de los paises insulares es de 83003") {
                observatorio.promedioDensidadPoblacionalInsulares().shouldBe(83003)
            }
        }
    }
)