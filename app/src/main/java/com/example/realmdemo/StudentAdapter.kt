package com.example.realmdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realmdemo.databinding.StudentRowBinding


class StudentAdapter(
    private val studentList: List<Student>,
    private val listener : OnItemClickListener
) : RecyclerView.Adapter<StudentAdapter.StudentVH>() {


    interface OnItemClickListener {
        fun onEditClick(student: Student)
        fun onDeleteClick(id: String)
    }

    inner class StudentVH(val binding: StudentRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student){
            binding.stuName.text = student.name
            binding.stuMarks.text = student.marks.toString()

            binding.editImg.setOnClickListener { listener.onEditClick(student)}
            binding.deleteImg.setOnClickListener { listener.onDeleteClick(student.id) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val root = StudentRowBinding.inflate(layoutInflater, parent, false)
        return StudentVH(root)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        val currentStudent = studentList[position]
        holder.bind(currentStudent)
    }


}