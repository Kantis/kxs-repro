package se.codeboss.kxs.repro

import kotlinx.coroutines.delay
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SampleService(
    val repo: MyRepo,
) {
    init {
        repo.save(
            Student(
                name = UserName("John"),
                grades = listOf(Grades(grade = 1), Grades(grade = 2)),
            ),
        )
    }

    @Transactional
    suspend fun getEntityFromRepo(): GradesDto {
        delay(1)
        val student = repo.findById(1)
        delay(1)
        return GradesDto(
            studentName = student.get().name.name,
            // loads the lazy list. should be ok since we are still in the transaction
            grades = student.get().grades.map { it.grade },
        )
    }

    data class GradesDto(
        val studentName: String,
        val grades: List<Int>,
    )
}
