package se.codeboss.kxs.repro

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Student(
    @GeneratedValue
    @Id
    val id: Int = 0,
    val name: UserName = UserName("John"),

    @OneToMany(mappedBy = "student", cascade = [ALL])
    val grades: List<Grades> = emptyList(),
) {
    constructor() : this(0, UserName("John"), emptyList())
}

@Entity
class Grades(
    @GeneratedValue
    @Id
    val id: Int? = null,

    @ManyToOne
    val student: Student? = null,
    val grade: Int = 0,
)

@JvmInline
value class UserName(val name: String)
