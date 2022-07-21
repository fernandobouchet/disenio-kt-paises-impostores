package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaisesTest: DescribeSpec({

    val bolivia = Pais("Bolivia", "BOL",10985059, 1098581.0,"América", "BOB", 100.0,mutableListOf<Pais>(), mutableListOf<String>("UNASUR"), mutableListOf<String>("Español", "Quechua", "Aymara"))
    val argentina = Pais("Argentina", "ARG",20985059, 3098581.0,"América", "ARG", 200.0,mutableListOf<Pais>(),mutableListOf<String>("UNASUR"), mutableListOf<String>("Español"))

    describe("Test Bolivia") {
        bolivia.esPlurinacional().shouldBe(true)
        bolivia.esUnaIsla().shouldBe(true)
        bolivia.densidadPoblacional().shouldBe(10)
        bolivia.esLimitrofeDe(argentina).shouldBe(false)
        bolivia.necesitaTraduccionCon(argentina).shouldBe(false)
        bolivia.esPotencialAliadoDe(argentina).shouldBe(true)
        bolivia.convieneIrDeComprasA(argentina).shouldBe(true)
        bolivia.aCuantoEquivaleEn(100 , argentina).shouldBe(200)
    }
})