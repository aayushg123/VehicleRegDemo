package com.vehiclereg.vehiclereg;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.model.Owner;
import com.model.OwnerDTO;
import com.model.Status;
import com.model.Vehicle;
import com.model.Vehicleresponse;

import dao.JWTUtil;
import dao.JsonTokenNeeded;
import dao.OwnerDAOImpl;
import dao.VehicleDAOImpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonObject;

/**
 * Root resource (exposed at "vehiclereg" path)
 */
@Stateless
@Path("vehiclereg")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	private static final String AUTH_HEADER_STRING = "Authorization";
	private static final Integer EXPIRATION_TIME = 3600000;
	private static final String SECRET_KEY = "signingKey";

	private enum Status1 {
		SUCCESS, ERROR
	}

	/** Authorisation API **/
	@GET
	@Path("/login/{username}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getJWTToken(@PathParam("username") String username) {
		if (username != null && !username.isEmpty()) {
			String token = new JWTUtil().createJWT("userId", username, SECRET_KEY, EXPIRATION_TIME);
			JsonObject json = new JsonObject();
			addReponseToJson(json, Status1.SUCCESS.name(), Response.Status.OK.getStatusCode(),
					"Token generated with success.");
			json.addProperty("userName", username);
			json.addProperty("token", token);
			return Response.status(Response.Status.OK).entity(json.toString()).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private void addReponseToJson(JsonObject json, String name, int statusCode, String message) {
		// TODO Auto-generated method stub
		json.addProperty("AppName", "RESTful Java Web Services using JAX-RS and Jersey");
		json.addProperty("status", name);
		json.addProperty("code", statusCode);
		json.addProperty("message", message);
	}

//    <--------------Vehicle DAO Implement API--------------------->
	VehicleDAOImpl vehicleDAOImpl = new VehicleDAOImpl();

	@Path("vehicle/regNewVehicle")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status addNewVehicle(String requestBody) throws SQLException {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			Map<String, Object> requestData = objectMapper.readValue(requestBody,
					new TypeReference<Map<String, Object>>() {
					});

			Vehicle vehicle = objectMapper.convertValue(requestData.get("vehicle"), Vehicle.class);
			Owner owner = objectMapper.convertValue(requestData.get("owner"), Owner.class);

			vehicle.setAadharNo(owner.getAadharNo());

			return vehicleDAOImpl.addNewVehicle(vehicle, owner);
		} catch (IOException e) {
			e.printStackTrace(); // Handle or log the exception appropriately
			return new Status(false);
		}
	}

	@Path("vehicle/getVehicleDetails")
	@JsonTokenNeeded
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vehicleresponse getVehicleDetails(Vehicle vehicle) throws SQLException {
		Vehicle v1 = vehicleDAOImpl.getVehicleDetails(vehicle.getVehicleRegNo());
		if (v1 == null) {
			return null;
		}
		Vehicleresponse vehicle1 = new Vehicleresponse(v1.getModel(), v1.getEngineNo(), v1.getRegDate(), v1.getState(),
				v1.getDistrict(), v1.getVehicleRegNo(), v1.getChallanCount(), v1.getLastChallanPaid());
		return vehicle1;
	}

	@Path("vehicle/vehicleGreenCheck")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String vehicleSeizeAPI(Vehicle vehicle) throws SQLException {
		return vehicleDAOImpl.GreenCheck(vehicle.getVehicleRegNo());
	}

	@Path("vehicle/vehicleChallanCheck")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String vehicleChallanCheck(Vehicle vehicle) throws SQLException {
		return vehicleDAOImpl.vehicleCheck(vehicle.getVehicleRegNo());
	}

	@Path("vehicle/vehicleDataUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status dataUpdate(Vehicle vehicle) throws SQLException {
		return vehicleDAOImpl.dataUpdate(vehicle);
	}

	@Path("vehicle/changeOwner")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status changeOwner(String requestBody) throws SQLException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			Map<String, Object> requestData = objectMapper.readValue(requestBody,
					new TypeReference<Map<String, Object>>() {
					});

			Vehicle vehicle = objectMapper.convertValue(requestData.get("vehicle"), Vehicle.class);
			Owner owner = objectMapper.convertValue(requestData.get("owner"), Owner.class);

			vehicle.setAadharNo(owner.getAadharNo());

			return vehicleDAOImpl.changeOwner(owner, vehicle);
		} catch (IOException e) {
			e.printStackTrace(); // Handle or log the exception appropriately
			return new Status(false);
		}

	}

	/** API for state change Request. **/

	@Path("vehicle/changeState")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status changeState(Vehicle vehicle) throws SQLException {
		return vehicleDAOImpl.changeState(vehicle);
	}

	OwnerDAOImpl ownerDAOImpl = new OwnerDAOImpl();

	@Path("owner/ownerHistory")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<OwnerDTO> vehicleHistory(Vehicle vehicle) throws SQLException {

		return ownerDAOImpl.vehicleHistory(vehicle.getVehicleRegNo());

	}
}
