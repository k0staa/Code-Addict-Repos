package pl.codeaddict.quarkussimpleapi

import io.agroal.api.AgroalDataSource
import java.sql.Connection
import java.sql.ResultSet
import java.util.*
import javax.inject.Singleton


@Singleton
open class ApiResourceService(private val dataSource: AgroalDataSource) {

    fun readDataObject(data: String): Optional<DataObject> {
        synchronized(this) {
            val sql = "SELECT * FROM DATA_OBJECTS WHERE DATA = '$data'"
            val connection = getConnection()
            val statement = connection.createStatement()
            val results: ResultSet = statement.executeQuery(sql)
            var dataResult: DataObject? = null
            while (results.next()) {
                val resultData: String = results.getString("DATA")
                dataResult = DataObject(resultData)
                break
            }
            statement.close()
            connection.close()
            return Optional.ofNullable(dataResult)
        }
    }

    fun storeDataObject(data: DataObject): Int {
        synchronized(this) {
            val connection = getConnection()
            val statement = connection.createStatement()
            statement.execute("DROP TABLE DATA_OBJECTS IF EXISTS")
            statement.execute("CREATE TABLE DATA_OBJECTS(" +
                    "ID INT, DATA VARCHAR(100))")

            val result: Int =
                    statement.executeUpdate("INSERT INTO DATA_OBJECTS(DATA) VALUES ('${data.data}')")
            statement.close()
            connection.close()
            return result
        }
    }

    private fun getConnection(): Connection {
        println("JDBC Metrics:" + dataSource.metrics)
        return dataSource.connection
    }
}