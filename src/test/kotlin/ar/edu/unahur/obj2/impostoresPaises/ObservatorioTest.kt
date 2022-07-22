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

        describe("Test Observatorio") {
            Observatorio.registrarPais(bolivia)
            Observatorio.registrarPais(argentina)
            Observatorio.registrarPais(estadosUnidos)
            Observatorio.registrarPais(italia)
            Observatorio.registrarPais(portugal)
            Observatorio.registrarPais(nigeria)

            Observatorio.sonLimitrofes("Argentina" ,"Bolivia").shouldBe(true)
            Observatorio.necesitanTraduccion("Argentina", "Bolivia").shouldBe(false)
            Observatorio.sonPotencialesAliados("Argentina", "Bolivia").shouldBe(true)
            Observatorio.convieneIrDeCompras("Argentina", "Bolivia").shouldBe(false)
            Observatorio.aCuantoEquivale(25,"Argentina", "Bolivia").shouldBe(1.37)
            Observatorio.codigoIsoPaisesMasPoblacion().shouldBe(listOf("NGA", "PRT", "ITA", "USA", "ARG"))
            Observatorio.continenteConMasPaisesPlurinacionales().shouldBe("América")
            Observatorio.promedioDensidadPoblacionalInsulares().shouldBe(83003)
        }
    }
)