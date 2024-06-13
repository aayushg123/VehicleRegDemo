package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.Mapper;
import com.model.Owner;
import com.model.State;
import com.model.Status;
import com.model.Vehicle;
import dao.VehicleDAO;

public class VehicleDAOImpl implements VehicleDAO {
	private Connection connection;
	private SessionFactory sessionFactory;

	public VehicleDAOImpl() {
		// TODO Auto-generated constructor stub
//		connection = DBUtil.getConnection();
//		System.out.println("Vehicle impl's connection= " + connection.hashCode());

		sessionFactory = SFUtil.getSessionFactory();
		System.out.println("Vehicle impl connection =" + sessionFactory.hashCode());
	}

	static final Pattern NUMBER = Pattern.compile("\\d+");

	static String increment(String input) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(input);
		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			int number = Integer.parseInt(matcher.group());
			String replacement = String.format("%0" + matcher.group().length() + "d", number + 1);
			matcher.appendReplacement(result, replacement);
		}

		matcher.appendTail(result);
		return result.toString();
//		return NUMBER.matcher(input)
//				.replaceFirst(s -> String.format("%0" + s.group().length() + "d", Integer.parseInt(s.group()) + 1));
	}

	@Override
	public Status addNewVehicle(Vehicle vehicle, Owner owner) throws SQLException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// TODO Auto-generated method stub
//		int isPresent = 0; // to check whether the owner exists in DB or not.
//		String sql = "SELECT COUNT(*) FROM owner where aadhar_card = ?";
//		try (PreparedStatement pst = connection.prepareStatement(sql)) {
//			pst.setString(1, owner.getAadharNo());
//			try (ResultSet rs = pst.executeQuery()) {
//				int noOfRecords = 0;
//				if (rs.next()) {
//					noOfRecords = rs.getInt(1);
//				}
//				System.out.println(noOfRecords);
//				if (noOfRecords >= 1) {
//					System.out.println("User already exists.");
//					isPresent = 1;
//				}
//			}
//		}
//
//		// If the owner exists already, then proceed for new number plate generation and
//		// registration.
//		if (isPresent == 1) {
//			String newRegno = null;
//			String alphaseries = null;
//			String next_number = null;
//			String sql1 = "SELECT * FROM state where state = ? and district = ?";
//
//			try (PreparedStatement pst = connection.prepareStatement(sql1)) {
//				pst.setString(1, vehicle.getState());
//				pst.setString(2, vehicle.getDistrict());
//				try (ResultSet rs = pst.executeQuery()) {
//					if (rs.next()) {
//						alphaseries = rs.getString("alphaseries");
//						next_number = rs.getString("next_number");
//						newRegno = rs.getString("prefix") + alphaseries + next_number;
//					}
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			String sql2 = "INSERT INTO vehicle (model,engine_no,regDate,state,district,aadhar_card,vehicle_regno) VALUES (?, ?, ?, ?, ?, ?, ?)";
//			try (PreparedStatement pst = connection.prepareStatement(sql2)) {
//
//				pst.setString(1, vehicle.getModel());
//				pst.setString(2, vehicle.getEngineNo());
//				pst.setDate(3, java.sql.Date.valueOf(vehicle.getRegDate()));
//				pst.setString(4, vehicle.getState());
//				pst.setString(5, vehicle.getDistrict());
//				pst.setString(6, owner.getAadharNo());
//				pst.setString(7, newRegno);
//
//				int res = pst.executeUpdate();
//
//				String sql4 = "INSERT INTO MAPPER(vehicle_regno,mobile_no) VALUES (?,?)";
//				try (PreparedStatement pst1 = connection.prepareStatement(sql4)) {
//					pst1.setString(1, newRegno);
//					pst1.setLong(2, owner.getMobileNo());
//					int res1 = pst1.executeUpdate();
//					if (res == 1 && res1 == 1) {
//						String newNo = increment(next_number);
//						String sql3 = "UPDATE state SET next_number= ? where state= ? and district= ?";
//						try (PreparedStatement pst2 = connection.prepareStatement(sql3)) {
//							pst2.setString(1, newNo);
//							pst2.setString(2, vehicle.getState());
//							pst2.setString(3, vehicle.getDistrict());
//
//							int res2 = pst2.executeUpdate();
//							if (res2 == 1) {
//								System.out.println("Hogyo next_number update :)");
//							}
//
//						} catch (SQLException e) {
//							e.printStackTrace();
//							System.out.println("Couldn't update next_number !!!!");
//						}
//					}
//					return new Status(res == 1 && res1 == 1);
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return new Status(false);
//			}
//		}
//
//		/**
//		 * if the owner does not exists, then first store owner in DB and then proceed
//		 * for the new number plate generation and vehicle registration. If anywhere the
//		 * exception arises,then return failed Status (Status (false)).
//		 **/
//		else if (isPresent == 0) {
//			LocalDate today = LocalDate.now();
//			LocalDate dob = owner.getDob();
//
//			Period p = Period.between(dob, today);
////			System.out.println(p.getYears());
//			if (p.getYears() <= 18) {
//				System.out.println(
//						"Owner cannot be Minor. Can be registered by guardian on the minors behalf under third party liability policy.");
//				return new Status(false);
//			}
//
//			String sql1 = "INSERT INTO owner (OWNER_NAME,DOB,AADHAR_CARD,MOBILE_NO) VALUES (?, ?, ?, ?)";
//			try (PreparedStatement pst = connection.prepareStatement(sql1)) {
//				pst.setString(1, owner.getOwner_name());
//				pst.setDate(2, Date.valueOf(owner.getDob()));
//				pst.setString(3, owner.getAadharNo());
//				pst.setLong(4, owner.getMobileNo());
//
//				int res = pst.executeUpdate();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return new Status(false);
//			}
//
//			String newRegno = null;
//			String alphaseries = null;
//			String next_number = null;
//			String sql2 = "SELECT * FROM state where state = ? and district = ? ";
//
//			try (PreparedStatement pst = connection.prepareStatement(sql2)) {
//				pst.setString(1, vehicle.getState());
//				pst.setString(2, vehicle.getDistrict());
//				try (ResultSet rs = pst.executeQuery()) {
//					if (rs.next()) {
//						alphaseries = rs.getString("alphaseries");
//						next_number = rs.getString("next_number");
//						newRegno = rs.getString("prefix") + alphaseries + next_number;
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//			String sql3 = "INSERT INTO vehicle (model,engine_no,regDate,state,district,aadhar_card,vehicle_regno) VALUES (?, ?, ?, ?, ?, ?, ?)";
//			try (PreparedStatement pst = connection.prepareStatement(sql3)) {
//				pst.setString(1, vehicle.getModel());
//				pst.setString(2, vehicle.getEngineNo());
//				pst.setDate(3, Date.valueOf(vehicle.getRegDate()));
//				pst.setString(4, vehicle.getState());
//				pst.setString(5, vehicle.getDistrict());
//				pst.setString(6, owner.getAadharNo());
//				pst.setString(7, newRegno);
//
//				int res = pst.executeUpdate();
//
//				String sql4 = "INSERT INTO MAPPER(vehicle_regno,mobile_no) VALUES (?,?)";
//				try (PreparedStatement pst1 = connection.prepareStatement(sql4)) {
//					pst1.setString(1, newRegno);
//					pst1.setLong(2, owner.getMobileNo());
//					int res1 = pst1.executeUpdate();
//					if (res == 1 && res1 == 1) {
//						String newNo = increment(next_number);
//						String sql5 = "UPDATE state SET next_number= ? where state= ? and district= ?";
//						try (PreparedStatement pst2 = connection.prepareStatement(sql5)) {
//							pst2.setString(1, newNo);
//							pst2.setString(2, vehicle.getState());
//							pst2.setString(3, vehicle.getDistrict());
//
//							int res2 = pst2.executeUpdate();
//							if (res2 == 1) {
//								System.out.println("Hogyo next_number update :)");
//							}
//
//						} catch (SQLException e) {
//							e.printStackTrace();
//							System.out.println("Couldn't update next_number !!!!");
//						}
//					}
//					return new Status(res == 1 && res1 == 1);
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return new Status(false);
//			}
//
//		}
//		return new Status(false);
		try {
			// Check if the owner already exists in the database
			
			Query ownerQuery = session.createQuery("SELECT COUNT(*) FROM Owner WHERE aadharNo = :aadharNo ");
			ownerQuery.setParameter("aadharNo", owner.getAadharNo());
			Long ownerCount = (Long) ownerQuery.getSingleResult();

			if (ownerCount >= 1) {
				// Owner already exists
				System.out.println("User already exists.");
			} 
			else {
				// Owner does not exist, so save the owner
				LocalDate today = LocalDate.now();
				LocalDate dob = owner.getDob();
				Period p = Period.between(dob, today);
				System.out.println(p.getYears());
				if (p.getYears() <= 18) {
					System.out.println("Owner cannot be Minor. Can be registered by guardian on the minors behalf under third party liability policy.");
					session.close();
					return new Status(false);
				}
				
				session.save(owner);
			}

			// Generate new registration number
			String newRegno = null;
			String alphaseries = null;
			String nextNumber = null;
			State state = (State) session.createQuery("from State s where s.state = :state and s.district = :district")
					.setParameter("state", vehicle.getState()).setParameter("district", vehicle.getDistrict())
					.uniqueResult();

			if (state != null) {
				alphaseries = state.getAlphaseries();
				nextNumber = state.getNumTobeused();
				newRegno = state.getPrefix() + alphaseries + nextNumber;

			} else {
				// Handle case where state and district combination not found
				throw new IllegalStateException("State and district combination not found: " + vehicle.getState() + ", "
						+ vehicle.getDistrict());
			}

			// Save the vehicle
			vehicle.setVehicleRegNo(newRegno);
			session.save(vehicle);

			// Create mapper entry
			Mapper mapper = new Mapper();
	        mapper.setVehicleRegNo(newRegno);
	        mapper.setMobileNo(owner.getMobileNo());
	        session.save(mapper);
	        
			// Increment next number
			String newNextNumber = increment(nextNumber);
			state.setNumTobeused(newNextNumber);
			session.update(state);
			tx.commit();
			session.close();
			return new Status(true);
				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			session.close();
			return new Status(false);
		}

	}

	@Override
	public Vehicle getVehicleDetails(String vehicleRegNo) throws SQLException {
		// TODO Auto-generated method stub

//		String sql = "SELECT * FROM vehicle WHERE vehicle_regno = ?";
//
//		try (PreparedStatement pst = connection.prepareStatement(sql)) {
//			pst.setString(1, vehicleRegNo);
//			try (ResultSet rs = pst.executeQuery()) {
//				if (rs.next()) {
//					String model = rs.getString("model");
//					String engineNo = rs.getString("engine_no");
//					LocalDate regDate = rs.getDate("regDate").toLocalDate();
//					String state = rs.getString("state");
//					String district = rs.getString("district");
//					String vehicleregno = rs.getString("vehicle_regno");
//					int noOfChallans = rs.getInt("challanno");
//					boolean isArchived = rs.getBoolean("isArchived");
//
//					return new Vehicle(model, engineNo, regDate, state, district, vehicleregno, noOfChallans,
//							isArchived);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		try {
			Session session = sessionFactory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
			Root<Vehicle> root = query.from(Vehicle.class);
			query.select(root).where(builder.equal(root.get("vehicleRegNo"), vehicleRegNo));
			Vehicle vehicle = session.createQuery(query).uniqueResult();
			if (vehicle == null) {
				System.out.println("No vehicle found.");
				return new Vehicle(null);
			}

			session.close();
			return vehicle;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String GreenCheck(String vehicleRegNo) throws SQLException {
		// TODO Auto-generated method stub

//		String sql = "SELECT * FROM vehicle WHERE vehicle_regno = ?";
//
//		try (PreparedStatement pst = connection.prepareStatement(sql)) {
//			pst.setString(1, vehicleRegNo);
//			try (ResultSet rs = pst.executeQuery()) { // System.out.println(rs.getFetchSize());
//
//				while (rs.next()) {
//					LocalDate regDate = rs.getDate("regDate").toLocalDate();
//					boolean greenTaxPaid = rs.getBoolean("isGreenTaxPaid");
//					if (Period.between(regDate, LocalDate.now()).getYears() >= 15 && greenTaxPaid == false) {
//						String sql1 = "UPDATE vehicle SET isarchived = TRUE WHERE vehicle_regno = ?";
//						try (PreparedStatement pst1 = connection.prepareStatement(sql1)) {
//							pst1.setString(1, vehicleRegNo);
//							int res = pst1.executeUpdate();
//
//							if (res == 1) {
//								return "Green Check conditions not met. Vehicle is marked illegal";
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//							return "SQL's mistake";
//						}
//					} else {
//						return "Vehicle is valid and reg status- active.";
//					}
//				}
//				return "No vehicle found.";
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return "Green Check endpoint failed";
//		}

		try {
			Session session = sessionFactory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
			Root<Vehicle> root = query.from(Vehicle.class);
			query.select(root).where(builder.equal(root.get("vehicleRegNo"), vehicleRegNo));
			Vehicle vehicle = session.createQuery(query).uniqueResult();

			if (vehicle != null) {
				LocalDate regDate = vehicle.getRegDate();
				boolean greenTaxPaid = vehicle.getIsGreenTaxPaid();
				System.out.println(greenTaxPaid);

				if (Period.between(regDate, LocalDate.now()).getYears() >= 15 && !greenTaxPaid) {
					vehicle.setArchived(true);
					Transaction tx = session.beginTransaction();
					session.update(vehicle);
					tx.commit();
					session.close();
					return "Green Check conditions not met. Vehicle is marked illegal";
				} else {
					vehicle.setArchived(false);
					Transaction tx = session.beginTransaction();
					session.update(vehicle);
					tx.commit();
					session.close();
					return "Vehicle is valid and reg status - active.";
				}
			} else {
				session.close();
				return "No vehicle found.";
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return "Green Check endpoint failed";
		}

//		return "Couldn't understand why control fallen here. severity level - FATAL !!!!";
	}

	@Override
	public Status changeOwner(Owner owner, Vehicle vehicle) throws SQLException {
		// TODO Change the owner of the specified vehicle regNo.

//		String ownerSearch = "select COUNT(*) from owner where aadhar_card = ?";
//		try (PreparedStatement pst = connection.prepareStatement(ownerSearch)) {
//			pst.setString(1, owner.getAadharNo());
//			try (ResultSet rs = pst.executeQuery()) {
//				if (rs.next()) {
//					int no = rs.getInt(1); // if owneralready exists in the DB.
//					if (no == 1) {
//						System.out.println("Owner already exists in DB");
//
//						String vehsearch = "Select COUNT(*) from vehicle where vehicle_regno = ?";
//						try (PreparedStatement pst1 = connection.prepareStatement(vehsearch)) {
//							pst1.setString(1, vehicle.getVehicleRegNo());
//							ResultSet rs1 = pst1.executeQuery();
//
//							if (rs.next()) {
//								int noofvehicle = rs.getInt(1);
//								if (noofvehicle == 0) {
//									System.out.println("No vehicle found!!");
//									return new Status(false);
//								}
//							}
//						}
//
//						String sql = "UPDATE vehicle SET aadhar_card= ? where vehicle_regno =?";
//						try (PreparedStatement pst1 = connection.prepareStatement(sql)) {
//							pst1.setString(1, owner.getAadharNo());
//							pst1.setString(2, vehicle.getVehicleRegNo());
//							int res = pst1.executeUpdate();
//
//							String mapperUpdate = "INSERT INTO mapper(vehicle_regno,mobile_no) VALUES (?,?)";
//							try (PreparedStatement pst2 = connection.prepareStatement(mapperUpdate)) {
//								pst2.setString(1, vehicle.getVehicleRegNo());
//								pst2.setLong(2, owner.getMobileNo());
//								int res1 = pst2.executeUpdate();
//								if (res1 == 1)
//									System.out.println("mapper table updated");
//								if (res == 1 && res1 == 1) {
//									return new Status(true);
//								}
//
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//							return new Status(false);
//						}
//
//					}
//
//					// if owner do not exists in the DB, put the owner in the table. if (no == 0)
//					{
//						String sql1 = "INSERT INTO owner (OWNER_NAME,DOB,AADHAR_CARD,MOBILE_NO) VALUES (?, ?, ?, ?)";
//						try (PreparedStatement pst1 = connection.prepareStatement(sql1)) {
//							pst1.setString(1, owner.getOwner_name());
//							pst1.setDate(2, Date.valueOf(owner.getDob()));
//							pst1.setString(3, owner.getAadharNo());
//							pst1.setLong(4, owner.getMobileNo());
//
//							int res = pst1.executeUpdate();
//							if (res == 1) {
//								System.out.println("New Owner inserted successfully!!");
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//							return new Status(false);
//						}
//						String vehsearch = "Select COUNT(*) from vehicle where vehicle_regno = ?";
//						try (PreparedStatement pst1 = connection.prepareStatement(vehsearch)) {
//							pst1.setString(1, vehicle.getVehicleRegNo());
//							ResultSet rs1 = pst1.executeQuery();
//
//							if (rs.next()) {
//								int noofvehicle = rs.getInt(1);
//								if (noofvehicle == 0) {
//									System.out.println("No vehicle found!!");
//									return new Status(false);
//								}
//							}
//						}
//
//						String sql = "UPDATE vehicle SET aadhar_card= ? where vehicle_regno =?";
//						try (PreparedStatement pst1 = connection.prepareStatement(sql)) {
//							pst1.setString(1, owner.getAadharNo());
//							pst1.setString(2, vehicle.getVehicleRegNo());
//							int res = pst1.executeUpdate();
//
//							String mapperUpdate = "insert into mapper (vehicle_regno,mobile_no) values (?,?)";
//							try (PreparedStatement pst2 = connection.prepareStatement(mapperUpdate)) {
//								pst2.setString(1, vehicle.getVehicleRegNo());
//								pst2.setLong(2, owner.getMobileNo());
//								int res1 = pst2.executeUpdate();
//								if (res == 1 && res1 == 1) {
//									return new Status(true);
//								}
//
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//							return new Status(false);
//						}
//
//					}
//				}
//			}
//		}
//
//		catch (SQLException e) {
//			e.printStackTrace();
//			return new Status(false);
//		}
//
//		System.out.println("Owner change API failed");
//		return new Status(false);

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			// Check if owner already exists in the database
			Query ownerQuery = session.createQuery("SELECT COUNT(*) FROM Owner WHERE aadharNo = :aadharNo");
			ownerQuery.setParameter("aadharNo", owner.getAadharNo());
			Long ownerCount = (Long) ownerQuery.getSingleResult();

			if (ownerCount == 1) {
				// Owner already exists in the database

				// Check if vehicle exists in the database
				Query vehicleQuery = session
						.createQuery("SELECT COUNT(*) FROM Vehicle WHERE vehicleRegNo = :vehicleRegNo");
				vehicleQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
				Long vehicleCount = (Long) vehicleQuery.getSingleResult();

				if (vehicleCount == 0) {
					// No vehicle found
					session.close();
					return new Status(false);
				}

				// Update vehicle owner and mapper table
				Query updateQuery = session
						.createQuery("UPDATE Vehicle SET aadharNo = :aadharNo WHERE vehicleRegNo = :vehicleRegNo");
				updateQuery.setParameter("aadharNo", owner.getAadharNo());
				updateQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
				int result = updateQuery.executeUpdate();

				if (result == 1) {
					Query mapperQuery = session.createNativeQuery(
							"INSERT INTO Mapper(vehicle_regno, mobile_no) VALUES (:vehicleRegNo, :mobileNo)");
					mapperQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
					mapperQuery.setParameter("mobileNo", owner.getMobileNo());
					int mapperResult = mapperQuery.executeUpdate();

					if (mapperResult == 1) {
						tx.commit();
						session.close();
						return new Status(true);
					}
				}
			} else if (ownerCount == 0) {

				// Insert owner if doesn't exist
				session.save(owner);

				// Check if vehicle exists in the database
				Query vehicleQuery = session
						.createQuery("SELECT COUNT(*) FROM Vehicle WHERE vehicleRegNo = :vehicleRegNo");
				vehicleQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
				Long vehicleCount = (Long) vehicleQuery.getSingleResult();

				if (vehicleCount == 0) {
					// No vehicle found
					session.close();
					return new Status(false);
				}

				// Update vehicle owner and mapper table
				Query updateQuery = session
						.createQuery("UPDATE Vehicle SET aadharNo = :aadharNo WHERE vehicleRegNo = :vehicleRegNo");
				updateQuery.setParameter("aadharNo", owner.getAadharNo());
				updateQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
				int result = updateQuery.executeUpdate();

				if (result == 1) {
					Query mapperQuery = session.createNativeQuery(
							"INSERT INTO Mapper(vehicleRegNo,mobileNo) VALUES (:vehicleRegNo, :mobileNo)");
					mapperQuery.setParameter("vehicle", vehicle.getVehicleRegNo());
					mapperQuery.setParameter("owner", owner.getMobileNo());
					int mapperResult = mapperQuery.executeUpdate();

					if (mapperResult == 1) {
						tx.commit();
						session.close();
						return new Status(true);
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return new Status(false);
	}

	@Override
	public String vehicleCheck(String vehicleRegNo) throws SQLException {
		// TODO Auto-generated method stub

//		Old Implementation
//		String sql = "SELECT * FROM vehicle WHERE vehicle_regno= ?";
//
//		try (PreparedStatement pst = connection.prepareStatement(sql)) {
//			pst.setString(1, vehicleRegNo);
//			try (ResultSet rs = pst.executeQuery()) { //
//				System.out.println(rs.getFetchSize());
//
//				while (rs.next()) {
//					int noOfChallan = rs.getInt("challanno");
//
//					if (rs.getDate("lastchallanpaid") == null || noOfChallan == 0) {
//						return "Vehicle is valid and reg status- active.";
//					}
//					LocalDate lastChallanPaid = rs.getDate("lastchallanpaid").toLocalDate();
//					Boolean isarchived = false;
//					if (rs.getBoolean("isarchived")) {
//						isarchived = rs.getBoolean("isarchived");
//					}
//
//					if (Period.between(lastChallanPaid, LocalDate.now()).getYears() >= 2 && noOfChallan >= 20) {
//						String sql1 = "UPDATE vehicle SET isarchived = TRUE WHERE vehicle_regno = ?";
//						try (PreparedStatement pst1 = connection.prepareStatement(sql1)) {
//							pst1.setString(1, vehicleRegNo);
//							int res = pst1.executeUpdate();
//
//							if (res == 1) {
//								return "No of unpaid challans >=20. Vehicle registration is cancelled.";
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//							return "SQL's mistake";
//						}
//					}
//
//					else if ((Period.between(lastChallanPaid, LocalDate.now()).getYears() < 2 || noOfChallan < 20)
//							&& isarchived == true) {
//						String sql1 = "UPDATE vehicle SET isarchived = false WHERE vehicle_regno = ?";
//						try (PreparedStatement pst1 = connection.prepareStatement(sql1)) {
//							pst1.setString(1, vehicleRegNo);
//							int res = pst1.executeUpdate();
//
//							if (res == 1) {
//								return "Vehicle remarked valid and reg status - active.";
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//							return "SQL's mistake";
//						}
//					} else {
//						return "Vehicle is valid and reg status- active.";
//					}
//				}
//				return "No vehicle found.";
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return "Vehicle Check endpoint failed";
//		}

		try {
			Session session = sessionFactory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
			Root<Vehicle> root = query.from(Vehicle.class);
			query.select(root).where(builder.equal(root.get("vehicleRegNo"), vehicleRegNo));
			Vehicle vehicle = session.createQuery(query).uniqueResult();

			if (vehicle != null) {
				int noOfChallan = vehicle.getChallanCount();

				if (vehicle.getLastChallanPaid() == null || noOfChallan == 0) {
					session.close();
					return "Vehicle is valid and reg status- active.";
				}
				LocalDate lastChallanPaid = vehicle.getLastChallanPaid();
				boolean isArchived = vehicle.isArchived();

				if (Period.between(lastChallanPaid, LocalDate.now()).getYears() >= 2 && noOfChallan >= 20) {
					vehicle.setArchived(true);
					Transaction tx = session.beginTransaction();
					session.update(vehicle);
					tx.commit();
					session.close();
					return "No of unpaid challans >=20. Vehicle registration is cancelled.";
				} else if ((Period.between(lastChallanPaid, LocalDate.now()).getYears() < 2 || noOfChallan < 20)
						&& isArchived) {
					vehicle.setArchived(false);
					Transaction tx = session.beginTransaction();
					session.update(vehicle);
					tx.commit();
					session.close();
					return "Vehicle remarked valid and reg status - active.";
				} else {
					session.close();
					return "Vehicle is valid and reg status- active.";
				}
			} else {
				session.close();
				return "No vehicle found.";
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return "Vehicle Check endpoint failed";
		}

	}

	@Override
	public Status dataUpdate(Vehicle vehicle) throws SQLException {
		// TODO Auto-generated method stub

//		Old Implementation
//		String sql = "UPDATE vehicle SET challanno = CASE WHEN ? IS NOT NULL THEN ? ELSE challanno END, isgreentaxpaid = ? ,lastchallanpaid = CASE WHEN ?::date IS NOT NULL THEN ?::date ELSE lastchallanpaid END where vehicle_regno = ?";
//		try (PreparedStatement pst = connection.prepareStatement(sql)) {
//			if (vehicle.getChallanCount() > 0) {
//				pst.setInt(1, vehicle.getChallanCount());
//				pst.setInt(2, vehicle.getChallanCount());
//			} else {
//				pst.setInt(1, vehicle.getChallanCount());
//				pst.setInt(2, vehicle.getChallanCount());
//			}
//
//			pst.setBoolean(3, vehicle.getIsGreenTaxPaid());
//			System.out.println(vehicle.getIsGreenTaxPaid());
//			if (vehicle.getLastChallanPaid() != null) {
//				pst.setDate(4, Date.valueOf(vehicle.getLastChallanPaid()));
//				pst.setDate(5, Date.valueOf(vehicle.getLastChallanPaid()));
//			} else {
//				pst.setDate(4, null);
//				pst.setDate(5, null);
//			}
//
//			pst.setString(6, vehicle.getVehicleRegNo());
//
//			int rs = pst.executeUpdate();
//
//			if (rs == 1) {
//				return new Status(true);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return new Status(false);
//
//		}
//		return new Status(false);

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery(
					"UPDATE Vehicle SET challanCount = :challanCount, isGreenTaxPaid = :isGreenTaxPaid, lastChallanPaid = :lastChallanPaid WHERE vehicleRegNo = :vehicleRegNo");
			query.setParameter("challanCount", vehicle.getChallanCount());
			query.setParameter("isGreenTaxPaid", vehicle.getIsGreenTaxPaid());
			query.setParameter("lastChallanPaid", vehicle.getLastChallanPaid());
			query.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());

			int result = query.executeUpdate();

			tx.commit();
			session.close();

			if (result == 1) {
				return new Status(true);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return new Status(false);
	}

	@Override
	public Status changeState(Vehicle vehicle) throws SQLException {
		// TODO Auto-generated method stub
//		String sql1 = "select COUNT(*) from vehicle where vehicle_regno =?";
//
//		try (PreparedStatement pst1 = connection.prepareStatement(sql1)) {
//			pst1.setString(1, vehicle.getVehicleRegNo());
//			try (ResultSet rs = pst1.executeQuery()) {
//				if (rs.next()) {
//					int noOfvehicle = rs.getInt(1);
//					if (noOfvehicle == 0) {
//						System.out.println("No vehicle found of vehicle regno !!");
//						return new Status(false);
//					}
//				}
//			}
//		}
//		String newRegno = null;
//		String alphaseries = null;
//		String next_number = null;
//		String sql2 = "SELECT * FROM state where state = ? and district = ? ";
//
//		try (PreparedStatement pst2 = connection.prepareStatement(sql2)) {
//			pst2.setString(1, vehicle.getState());
//			pst2.setString(2, vehicle.getDistrict());
//			try (ResultSet rs = pst2.executeQuery()) {
//				if (rs.next()) {
//					alphaseries = rs.getString("alphaseries");
//					next_number = rs.getString("next_number");
//					newRegno = rs.getString("prefix") + alphaseries + next_number;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return new Status(false);
//		}
//		Long mobileNo = null;
//		String mobNoquery = "select mobile_no from vehicle v INNER JOIN owner o on v.aadhar_card = o.aadhar_card where vehicle_regno = ?";
//		try (PreparedStatement pst4 = connection.prepareStatement(mobNoquery)) {
//			pst4.setString(1, vehicle.getVehicleRegNo());
//			ResultSet rs = pst4.executeQuery();
//			if (rs.next()) {
//				mobileNo = rs.getLong("mobile_no");
//			}
//		}
//
//		String sql3 = "UPDATE vehicle SET state = ?,district = ?, vehicle_regno = ? where vehicle_regno= ?";
//		try (PreparedStatement pst3 = connection.prepareStatement(sql3)) {
//			pst3.setString(1, vehicle.getState());
//			pst3.setString(2, vehicle.getDistrict());
//			pst3.setString(3, newRegno);
//			pst3.setString(4, vehicle.getVehicleRegNo());
//			int res = pst3.executeUpdate();
//
//			if (res == 1) {
//
//				String mapperUpdate = "INSERT INTO MAPPER (vehicle_regno,mobile_no,old_regno) VALUES (?,?,?)";
//				try (PreparedStatement pst5 = connection.prepareStatement(mapperUpdate)) {
//					pst5.setString(1, newRegno);
//					pst5.setLong(2, mobileNo);
//					pst5.setString(3, vehicle.getVehicleRegNo());
//					int res1 = pst5.executeUpdate();
//					if (res1 == 1) {
//						String newNo = increment(next_number);
//						String regNoUpdater = "UPDATE state SET next_number= ? where state= ? and district= ?";
//						try (PreparedStatement pst6 = connection.prepareStatement(regNoUpdater)) {
//							pst6.setString(1, newNo);
//							pst6.setString(2, vehicle.getState());
//							pst6.setString(3, vehicle.getDistrict());
//
//							int res2 = pst6.executeUpdate();
//							if (res2 == 1) {
//								System.out.println("next_number updated in changeState endpt :)");
//							}
//
//						} catch (SQLException e) {
//							e.printStackTrace();
//							System.out.println("Couldn't update next_number changeState endpt !!");
//						}
//					}
//					if (res == 1 && res1 == 1) {
//						System.out.println("State Migration Done!");
//						return new Status(true);
//					}
//				}
//			}
//
//		}
//
//		System.out.println("Control falled here. Error cause unknown !");
//		return new Status(false);

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query vehicleQuery = session.createQuery("SELECT COUNT(*) FROM Vehicle WHERE vehicleRegNo = :vehicleRegNo");
			vehicleQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
			Long vehicleCount = (Long) vehicleQuery.getSingleResult();

			if (vehicleCount == 0) {
				session.close();
				System.out.println("No vehicle found of vehicle regno !!");
				return new Status(false);
			}

			Query vehicleLoadQuery = session.createQuery("from Vehicle v where v.vehicleRegNo = :vehicleRegNo ");
			vehicleLoadQuery.setParameter("vehicleRegNo", vehicle.getVehicleRegNo());
			Vehicle v = (Vehicle) vehicleLoadQuery.getSingleResult();

			if (v.getDistrict().equalsIgnoreCase(vehicle.getDistrict())) {
				System.out.println(" Vehicle already registered in the desired state and district");
				return new Status(false);
			}

			Query districtQuery = session.createQuery("from State s where s.state = :state and s.district = :district");
			districtQuery.setParameter("state", vehicle.getState());
			districtQuery.setParameter("district", vehicle.getDistrict());
			State state = (State) districtQuery.getSingleResult();

			String newRegno = null;
			String alphaseries = state.getAlphaseries();
			String next_number = state.getNumTobeused();
			newRegno = state.getPrefix() + alphaseries + next_number;

			String sqlQuery = "SELECT o.mobile_no FROM Vehicle v INNER JOIN owner o ON v.aadhar_card = o.aadhar_card WHERE v.vehicle_regno = ?";
			Query query = session.createNativeQuery(sqlQuery);
			query.setParameter(1, vehicle.getVehicleRegNo());
			BigInteger mobileNo = (BigInteger) query.getSingleResult();
			System.out.println(mobileNo.longValue());

			String sqlQ = "UPDATE Vehicle SET state = ?, district= ?, vehicle_regno= ? WHERE vehicle_regno = ?";
			Query updateQuery = session.createNativeQuery(sqlQ);
			updateQuery.setParameter(1, vehicle.getState());
			updateQuery.setParameter(2, vehicle.getDistrict());
			updateQuery.setParameter(3, newRegno);
			updateQuery.setParameter(4, vehicle.getVehicleRegNo());
			int result = updateQuery.executeUpdate();

			if (result == 1) {
				Query mapperQuery = session.createNativeQuery(
						"INSERT INTO Mapper(vehicle_regno,mobile_no,old_regno) VALUES (:vehicleRegNo,:mobileNo,:oldRegNo)");
				mapperQuery.setParameter("vehicleRegNo", newRegno);
				mapperQuery.setParameter("mobileNo", mobileNo);
				mapperQuery.setParameter("oldRegNo", vehicle.getVehicleRegNo());
				int mapperResult = mapperQuery.executeUpdate();
				if (mapperResult == 1) {
					String newNo = increment(next_number);
					System.out.println(newNo);
					try {
						state.setNumTobeused(newNo);
						session.update(state);
						tx.commit();
					} catch (HibernateException he) {
						if (tx != null)
							tx.rollback();
						he.printStackTrace();
					}
				}
				if (result == 1 && mapperResult == 1) {
					session.close();
					System.out.println("State migration done !!");
					return new Status(true);
				}
			}

		} catch (HibernateException he) {
			// TODO: handle exception
			he.printStackTrace();
		}
		System.out.println("Control falled here. Error cause unknown !");
		return new Status(false);
	}

}
