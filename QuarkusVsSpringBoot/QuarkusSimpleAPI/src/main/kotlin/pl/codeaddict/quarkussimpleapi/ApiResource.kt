package pl.codeaddict.quarkussimpleapi

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ApiResource(private val service: ApiResourceService) {

    @GET
    @Path("/{data}")
    fun apiGet(@PathParam("data") data: String): DataObject {
        return service.readDataObject(data)
                .orElseThrow { RuntimeException("No data object with data: $data") }
    }

    @POST
    fun apiPost(dataObject: DataObject): Int {
        return service.storeDataObject(dataObject)
    }
}