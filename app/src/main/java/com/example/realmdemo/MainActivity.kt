package com.example.realmdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), StudentAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private val studentViewModel : StudentViewModel by viewModels {
        StudentViewModelFactory(StudentRepository())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        studentViewModel.students.observe(this){students ->
            studentAdapter = StudentAdapter(students, this )
            binding.rvStudent.adapter = studentAdapter
        }

        studentViewModel.fetchAllStudents()


        binding.addBtn.setOnClickListener {
            val addStu = AddEditStudentFragment()
            addStu.show(supportFragmentManager, "Add Student")
        }



    }

    override fun onEditClick(student: Student) {
        val editStu = AddEditStudentFragment(student)
        editStu.show(supportFragmentManager, null)
    }

    override fun onDeleteClick(id : String) {
        studentViewModel.deleteStudent(id)
    }
}