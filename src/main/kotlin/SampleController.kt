package se.codeboss.kxs.repro

import kotlinx.serialization.Serializable
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

fun main() {
    runApplication<MyApplication>()
}

@SpringBootApplication
class MyApplication

@RestController
class SampleController {
    @GetMapping("/suspend-and-response-entity")
    suspend fun suspendAndResponseEntity(): ResponseEntity<List<Person>> =
        ResponseEntity.ok(listOf(Person(UserId(1), "John")))

    @GetMapping("/suspend-without-response-entity")
    suspend fun suspendWithoutResponseEntity(): List<Person> =
        listOf(Person(UserId(1), "John"))

    @GetMapping("/non-suspend-with-response-entity")
    fun nonSuspendWithResponseEntity(): ResponseEntity<List<Person>> =
        ResponseEntity.ok(listOf(Person(UserId(1), "John")))

    @GetMapping("/non-suspend-without-response-entity")
    fun nonSuspendWithoutResponseEntity(): List<Person> =
        listOf(Person(UserId(1), "John"))
}

@Serializable
data class Person(
    val userId: UserId,
    val name: String,
)

@JvmInline
@Serializable
value class UserId(val id: Int)
