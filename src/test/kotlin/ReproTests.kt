package se.codeboss.kxs.repro

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When

class ReproTests : FreeSpec(
    {
        "Repro tests" - {
            withData(
                "/suspend-and-response-entity",
                "/suspend-without-response-entity",
                "/non-suspend-and-response-entity",
                "/non-suspend-without-response-entity",
            ) { path ->
                val body = When {
                    get(path)
                } Then {
                    statusCode(200)
                } Extract {
                    body().asString()
                }

                body shouldEqualJson """
                    [
                        {
                            "userId": 1,
                            "name": "John"
                        }
                    ]
                """.trimIndent()
            }
        }
    },
)
