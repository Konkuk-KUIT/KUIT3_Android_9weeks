import com.example.a9weeks.dataClass.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject

fun parseErrorMessage(errorBody: String?): String {
    return try {
        val jsonObj = JSONObject(errorBody)
        jsonObj.getString("message")
    } catch (e: Exception) {
        e.toString()
    }
}