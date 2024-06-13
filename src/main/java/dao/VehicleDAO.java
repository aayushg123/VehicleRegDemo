package dao;

import java.sql.SQLException;
import java.util.Optional;

import com.model.Owner;
import com.model.Status;
import com.model.Vehicle;

public interface VehicleDAO {
	public Status addNewVehicle (Vehicle vehicle, Owner owner) throws SQLException;
	
	public Vehicle getVehicleDetails (String vehicleRegNo) throws SQLException;
	
	public String GreenCheck(String vehicleRegNo) throws SQLException;
	
	public String vehicleCheck (String vehicleRegNo) throws SQLException;
	
	public Status dataUpdate (Vehicle Details) throws SQLException;
	
	public Status changeOwner(Owner owner,Vehicle vehicle) throws SQLException;
	
	public Status changeState (Vehicle vehicle) throws SQLException;
	
}
