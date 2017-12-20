package framework.datasources
import java.sql.*

class DataBase(private val jdbc: String, private val url: String, private val user: String, private val password: String)
{
    val connection: Connection = connection()

    private fun connection(): Connection
    {
        Class.forName(jdbc)
        return DriverManager.getConnection(url, user, password)
    }
}