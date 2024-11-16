import com.quito.santiago.laboratoriocalificado03.TeacherResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher-b")
    fun getTeachers(): Call<TeacherResponse>
}
