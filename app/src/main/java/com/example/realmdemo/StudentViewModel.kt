package com.example.realmdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository):ViewModel() {

    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _students


    fun addStudent(student: Student){
        viewModelScope.launch {
            repository.addStudent(student)
            fetchAllStudents()
        }
    }

    fun fetchAllStudents() {
        _students.value = repository.getAllStudents()
    }

    fun updateStudent(student:Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
            fetchAllStudents()
        }
    }

    fun deleteStudent(id:String) {
        viewModelScope.launch {
            repository.deleteStudent(id)
            fetchAllStudents()
        }
    }

}




