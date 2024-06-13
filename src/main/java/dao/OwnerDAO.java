package dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Owner;
import com.model.OwnerDTO;

public interface OwnerDAO {
	
	Owner ownerHistory(int aadharCard) throws SQLException;
	
	List<OwnerDTO> vehicleHistory(String vehicleRegno) throws SQLException;
}
