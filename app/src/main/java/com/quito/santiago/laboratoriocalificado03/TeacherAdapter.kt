package com.quito.santiago.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quito.santiago.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private val teachers: List<Teacher>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher) {
            binding.name.text = "${teacher.name} ${teacher.last_name}"
            binding.email.text = teacher.email
            Glide.with(binding.photo.context)
                .load(teacher.image_url)
                .into(binding.photo)

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${teacher.phone_number}")
                }
                binding.root.context.startActivity(intent)
            }

            binding.root.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${teacher.email}")
                }
                binding.root.context.startActivity(intent)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)
    }

    override fun getItemCount(): Int = teachers.size
}

