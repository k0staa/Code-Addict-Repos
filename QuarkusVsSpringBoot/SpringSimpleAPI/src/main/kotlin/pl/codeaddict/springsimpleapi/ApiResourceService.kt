package pl.codeaddict.springsimpleapi

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.util.*


@Service
open class ApiResourceService(private val jdbcTemplate: JdbcTemplate) {

    fun readDataObject(data: String): Optional<DataObject> {
        synchronized(this) {
            val sql = "SELECT * FROM DATA_OBJECTS WHERE DATA = ?"

            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, arrayOf<Any>(data)) { rs: ResultSet, _: Int ->
                DataObject(rs.getString("DATA"))
            })
        }
    }

    fun storeDataObject(dataObject: DataObject): Int {
        synchronized(this) {
            jdbcTemplate.execute("DROP TABLE DATA_OBJECTS IF EXISTS")
            jdbcTemplate.execute("CREATE TABLE DATA_OBJECTS(" +
                    "ID INT, DATA VARCHAR(100))")
            return jdbcTemplate.update("INSERT INTO DATA_OBJECTS(DATA) VALUES (?)", dataObject.data)
        }
    }
}