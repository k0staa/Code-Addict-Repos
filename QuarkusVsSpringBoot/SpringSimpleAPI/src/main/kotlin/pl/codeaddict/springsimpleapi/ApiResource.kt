package pl.codeaddict.springsimpleapi

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class ApiResource(private val service: ApiResourceService) {

    @GetMapping(path = ["/api/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun apiGet(@PathVariable name: String): DataObject {
        return service.readDataObject(name)
                .orElseThrow { RuntimeException("No data object with name: $name") }
    }

    @PostMapping(path = ["/api"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun apiPost(@RequestBody dataObject: DataObject): Int {
        return service.storeDataObject(dataObject)
    }
}