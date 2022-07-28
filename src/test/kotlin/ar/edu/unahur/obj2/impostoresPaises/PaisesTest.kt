package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaisesTest: DescribeSpec({

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

    bolivia.aniadirPaisesLimitrofes(argentina)
    argentina.aniadirPaisesLimitrofes(bolivia)

    describe("Test sobre Bolivia") {
        it("No es plurinacional") {
            bolivia.esPlurinacional().shouldBe(true)
        }
        it("No es una isla") {
            bolivia.esUnaIsla().shouldBe(false)
        }
        it("La densidad poblacional es de 10") {
            bolivia.densidadPoblacional().shouldBe(10)
        }
        it("Es limitrofe de Argentina") {
            bolivia.esLimitrofeDe(argentina).shouldBe(true)
        }
        it("No necesita traducción con Argentina") {
            bolivia.necesitaTraduccionCon(argentina).shouldBe(false)
        }
        it("Es potencial aliado de Argentina") {
            bolivia.esPotencialAliadoDe(argentina).shouldBe(true)
        }
        it("Conviene ir de compras a Argentina") {
            bolivia.convieneIrDeComprasA(argentina).shouldBe(true)
        }
        it("100 BOL equivalen a 1828.09 ARG") {
            bolivia.aCuantoEquivaleEn(100 , argentina).shouldBe(1828.09)
        }
    }
})