package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaisesTest: DescribeSpec({

    val bolivia = Pais("Bolivia", "BOL",11067000, 1098581.0,"América", "BOB", 6.87, mutableListOf<String>("UNASUR"), mutableListOf<String>("Español", "Quechua", "Aymara"))
    val argentina = Pais("Argentina", "ARG",45038000, 2078000.0,"América", "ARG", 125.59, mutableListOf<String>("MERCOSUR","UNASUR"), mutableListOf<String>("Español"))
    bolivia.aniadirPaisesLimitrofes(argentina)
    argentina.aniadirPaisesLimitrofes(bolivia)

    describe("Test Bolivia") {
        bolivia.esPlurinacional().shouldBe(true)
        bolivia.esUnaIsla().shouldBe(false)
        bolivia.densidadPoblacional().shouldBe(10)
        bolivia.esLimitrofeDe(argentina).shouldBe(true)
        bolivia.necesitaTraduccionCon(argentina).shouldBe(false)
        bolivia.esPotencialAliadoDe(argentina).shouldBe(true)
        bolivia.convieneIrDeComprasA(argentina).shouldBe(true)
        bolivia.aCuantoEquivaleEn(100 , argentina).shouldBe(1828.09)
    }
})