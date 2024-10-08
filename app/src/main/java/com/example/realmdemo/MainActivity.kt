package com.example.realmdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.realmdemo.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(), StudentAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(StudentRepository())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        studentViewModel.students.observe(this) { students ->
            studentAdapter = StudentAdapter(students, this)
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

    override fun onDeleteClick(id: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete?")
            .setMessage("Do you really want to delete")
            .setPositiveButton("Delete") { d, _ ->
                studentViewModel.deleteStudent(id)
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show()
                d.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()

    }
}