package com.example.realmdemo

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository {

    private val config = RealmConfiguration.create(schema = setOf(Student::class))
    private fun getRealmInstance(): Realm {

        return Realm.open(config)
    }

    suspend fun addStudent(student: Student) {
        withContext(Dispatchers.IO) {
            val realm = getRealmInstance()
            realm.write {
                copyToRealm(student)
            }
        }
    }

    fun getAllStudents(): List<Student> {
        val realm = getRealmInstance()
        return realm.query<Student>().find()
    }

    suspend fun updateStudent(student: Student) {
        withContext(Dispatchers.IO) {
            val realm = getRealmInstance()
            realm.write {
                findLatest(student)?.apply {
                    this.name = student.name
                    this.marks = student.marks
                }
            }
        }
    }

    suspend fun deleteStudent(id: String) {
        withContext(Dispatchers.IO) {
            val realm = getRealmInstance()
            realm.write {
                val student = query<Student>("id == $0", id).first().find()
                student?.let { delete(it) }
            }
            realm.close() // Close the realm instance after the operation
        }
    }
}















