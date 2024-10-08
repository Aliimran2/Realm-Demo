package com.example.realmdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.realmdemo.databinding.FragmentAddStudentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddEditStudentFragment(
    private val student: Student? = null
) : BottomSheetDialogFragment() {

    private val studentViewModel: StudentViewModel by activityViewModels()
    private var _binding: FragmentAddStudentBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)

        if (student != null) {
            binding.etStudentName.setText(student.name)
            binding.etStudentMarks.setText(student.marks.toString())
            binding.saveBtn.text = "Update"
        } else {
            binding.saveBtn.text = "Add"
        }

        binding.saveBtn.setOnClickListener {
            val stuName = binding.etStudentName.text.toString()
            val stuMarks = binding.etStudentMarks.text.toString().toIntOrNull()

            if (stuName.isNotEmpty() && stuMarks != null) {

                if (student != null) {
                    student.apply {
                        name = stuName
                        marks = stuMarks
                    }
                    studentViewModel.updateStudent(student)
                    Toast.makeText(requireContext(), "Data updated", Toast.LENGTH_SHORT).show()
                    dismiss()
                } else {
                    val student = Student().apply {
                        id = System.currentTimeMillis().toString()
                        this.name = stuName
                        this.marks = stuMarks
                    }
                    studentViewModel.addStudent(student)
                    Toast.makeText(requireContext(), "Data Added", Toast.LENGTH_SHORT).show()
                    dismiss()
                }

            } else {
                Toast.makeText(requireContext(), "Enter in both fields", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}

