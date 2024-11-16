package com.quito.santiago.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.quito.santiago.laboratoriocalificado03.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchTeachers()
    }

    private fun fetchTeachers() {
        val call = RetrofitClient.apiService.getTeachers()
        call.enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(call: Call<TeacherResponse>, response: Response<TeacherResponse>) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.teachers ?: emptyList()
                    val adapter = TeacherAdapter(teachers)
                    binding.recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
