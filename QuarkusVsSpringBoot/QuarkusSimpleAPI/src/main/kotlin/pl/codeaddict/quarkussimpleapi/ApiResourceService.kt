package pl.codeaddict.quarkussimpleapi

import io.agroal.api.AgroalDataSource
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import javax.inject.Singleton


@Singleton
open class ApiResourceService(private val dataSource: AgroalDataSource) {

    fun readDataObject(data: String): Optional<DataObject> {
        val sql = "SELECT * FROM DATA_OBJECTS WHERE DATA = '$data'"
        val statement = getConnectionStatement()
        val results: ResultSet = statement.executeQuery(sql)
        var dataResult: DataObject? = null
        while (results.next()) {
            val resultData: String = results.getString("DATA")
            dataResult = DataObject(resultData)
            break
        }

        return Optional.ofNullable(dataResult)
    }

    fun storeDataObject(data: DataObject): Int {
        val statement = getConnectionStatement()
        statement.execute("DROP TABLE DATA_OBJECTS IF EXISTS")
        statement.execute("CREATE TABLE DATA_OBJECTS(" +
                "ID INT, DATA VARCHAR(100))")

        return statement.executeUpdate("INSERT INTO DATA_OBJECTS(DATA) VALUES ('${data.data}')")
    }

    private fun getConnectionStatement(): Statement {
        return dataSource.connection.createStatement()
    }

}