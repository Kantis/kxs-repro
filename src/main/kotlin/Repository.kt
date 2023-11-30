package se.codeboss.kxs.repro

import org.springframework.data.repository.CrudRepository

interface MyRepo : CrudRepository<Student, Int> {
}