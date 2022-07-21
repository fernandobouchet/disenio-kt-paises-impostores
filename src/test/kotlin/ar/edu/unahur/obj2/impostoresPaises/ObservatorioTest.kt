package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec(
    {
        val bolivia = Pais("Bolivia", "BOL",10985059, 1098581.0,"América", "BOB", 6.89,mutableListOf<Pais>(), mutableListOf<String>("UNASUR"), mutableListOf<String>("Español", "Quechua", "Aymara"))
        val argentina = Pais("Argentina", "ARG",45385059, 3098581.0,"América", "ARG", 126.89,mutableListOf<Pais>(),mutableListOf<String>("UNASUR"), mutableListOf<String>("Español"))
        val estadosUnidos = Pais("Estados Unidos", "EEUU",329585059, 3098581.0,"América", "DOL", 1.0,mutableListOf<Pais>(),mutableListOf<String>("UNASUR"), mutableListOf<String>("Inglés"))
        val italia = Pais("Italia", "ITA",59555059, 1098581.0,"Europa", "EUR", 6.89,mutableListOf<Pais>(), mutableListOf<String>("UNASUR"), mutableListOf<String>("Italiano"))
        val portugal = Pais("Portugal", "POR",10985059, 3098581.0,"Europa", "EUR", 6.89,mutableListOf<Pais>(),mutableListOf<String>("UNASUR"), mutableListOf<String>("Portugués"))
        val nigeria = Pais("Nigeria", "NIG",206985059, 1098581.0,"Africa", "NAI", 6.89,mutableListOf<Pais>(), mutableListOf<String>("ECOWAS"), mutableListOf<String>("Yoruba", "Igbo", "Hausa", "Inglés"))



        describe("Test Observatorio") {
            Observatorio.registrarPais(bolivia)
            Observatorio.registrarPais(argentina)
            Observatorio.registrarPais(estadosUnidos)
            Observatorio.registrarPais(italia)
            Observatorio.registrarPais(portugal)
            Observatorio.registrarPais(nigeria)

            Observatorio.sonLimitrofes("Argentina" ,"Bolivia").shouldBe(false)
            Observatorio.necesitanTraduccion("Argentina", "Bolivia").shouldBe(false)
            Observatorio.sonPotencialesAliados("Argentina", "Bolivia").shouldBe(true)
            Observatorio.convieneIrDeCompras("Argentina", "Bolivia").shouldBe(false)
            Observatorio.aCuantoEquivale(25,"Argentina", "Bolivia").shouldBe(1.36)
            Observatorio.codigoIsoPaisesMasPoblacion().shouldBe(listOf("NIG", "EEUU", "ITA", "ARG", "BOL"))
        }
    }
)