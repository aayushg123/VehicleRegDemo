package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;

import dao.DBUtil;

import com.model.Owner;
import com.model.OwnerDTO;

public class OwnerDAOImpl implements OwnerDAO {
	private Connection connection;
	private SessionFactory sessionFactory;

	public OwnerDAOImpl() {
		// TODO Auto-generated constructor stub
//		connection = DBUtil.getConnection();
//		System.out.println("Owner impl's connection= " + connection.hashCode());
		sessionFactory = SFUtil.getSessionFactory();
		System.out.println("Owner impl connection =" + sessionFactory.hashCode());
	}

	@Override
	public Owner ownerHistory(int aadharCard) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OwnerDTO> vehicleHistory(String vehicleRegno) throws SQLException {
		// TODO Gives the list of owner of particular vehicle Registration no.
		
		
		String hql = "select owner_name,dob,o.mobile_no,vehicle_regno from Owner o inner join Mapper m on o.mobile_no = m.mobile_no where m.vehicle_regno = :vehicleRegno or m.old_regno in (select m.old_regno from Mapper m where m.vehicle_regno = :vehicleRegno)";
		List<OwnerDTO> owners = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createNativeQuery("select owner_name,dob,o.mobile_no from Owner o inner join Mapper m on o.mobile_no = m.mobile_no where m.vehicle_regno = :vehicleRegno or m.old_regno in (select m.old_regno from Mapper m where m.vehicle_regno = :vehicleRegno)");
			query.setParameter("vehicleRegno", vehicleRegno);
			List<Object[]> results = query.getResultList();
			for (Object[] record : results) {
				Long mobileNo = ((BigInteger) record[2]).longValue();
				String ownerName = (String) record[0];
				LocalDate dob = ((java.sql.Date)record[1]).toLocalDate();
				OwnerDTO ownerDTO = new OwnerDTO();
				ownerDTO.setOwner_name(ownerName);
				ownerDTO.setDob(dob);
				ownerDTO.setMobileNo(mobileNo);
				owners.add(ownerDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return owners;
	}

}
