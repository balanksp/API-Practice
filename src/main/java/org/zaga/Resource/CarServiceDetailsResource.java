package org.zaga.Resource;

import org.zaga.Entity.CarEnum;
import org.zaga.Entity.CarServiceDetails;
import org.zaga.Service.CarServiceDetailsService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/car-management")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarServiceDetailsResource {

    @Inject
    CarServiceDetailsService service;

    @Path("/create-car-details")
    @POST
    public Response createsCarDetails(CarServiceDetails details) {
        System.out.println("<----------------repo--------------<<");
        return Response.ok(service.createCarDetails(details)).build();

    }

    @Path("/view-car-details/{customerName}")
    @GET
    public Response viewCarDetails(@QueryParam("carNumber") String carNumber,
            @PathParam("customerName") String customerName) {
        return Response.ok(service.viewDetails(carNumber, customerName)).build();
    }

    @DELETE
    @Path("/delete-car-details")
    public void deleteCarDetails(@QueryParam("carNumber") String carNumber,
            @QueryParam("customerName") String customerName) {
        try {
            service.deleteServiceDetails(carNumber, customerName);
        } catch (Exception e) {

            System.out.println("enter the correct details");
        }

    }

    @PUT
    @Path("/update-car-details")
    public Response updateCarDetails(@QueryParam("carNumber") String carNumber, @QueryParam("custName") String custName, @QueryParam("serviceStatus") CarEnum carEnum) {
        CarServiceDetails detail = service.updateCarServiceDetails(carNumber, custName, carEnum);
        return Response.ok(detail).build();

    }

}