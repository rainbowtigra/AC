package repository.JDBCRepository;

import entity.Brigade;
import entity.Flight;
import entity.Staffer;
import entity.enums.StaffRole;
import entity.enums.Status;
import exception.DataAccessException;
import repository.FlightRepository;
import repository.JDBCRepository.ds.pcLibraryProj.AbstractDAO;
import repository.JDBCRepository.ds.pcLibraryProj.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * implementation of FlightRepository
 */

//@Repository
public class JDBCFlightRepository extends AbstractDAO /*extends JDBCAbstractRepository*/ implements FlightRepository {

    //private static DataSourceProvider dataSourceProvider;
    private static final String GET_FLIGHT_BY_ID = "flight.get.by.id";
    private static final String GET_ALL_FLIGHT = "SELECT * FROM flights";
    private static final String ADD_FLIGHT = "flight.add";
    private static final String UPDATE_FLIGHT = "flight.update";
    private static final String REMOVE_FLIGHT = "flight.delete";
    private static final String GET_BRIGADE = "brigade.get";
    private static final String GET_STAFFER_BY_ID = "staffer.get.by.id";
    private static final String CHANGE_STATUS = "flight.change.status";
    private static final String GET_BY_ROLE = "staffer.get.all.by.role";
    private static final String ADD_BRIGADE = "flight.set.brigade";
    private static final String REMOVE_BRIGADE = "flight.remove.brigade";

    /**
     * Constructs a {@code JDBCFlightRepository}.
     *
     * @param dataSourceProvider for connecting to database.
     */
    /*public JDBCFlightRepository(DataSourceProvider dataSourceProvider) {
        super(dataSourceProvider);
    }*/


    public static void main(String[] args) throws ConnectionPoolException {
        //JDBCFlightRepository r = new JDBCFlightRepository();
        System.out.println("sout");
        System.out.println(getAll());
    }


    //@Override
    protected static Flight extractFromResultSet(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setId(rs.getInt("id"));
        flight.setName(rs.getString("name"));
        flight.setDepartureLocation(rs.getString("departure_location"));
        flight.setArriveLocation(rs.getString("arrive_location"));
        flight.setStatus(Status.values()[rs.getInt("status")]);
        flight.setDepartureDate(rs.getDate("departure_date"));
        flight.setArriveDate(rs.getDate("arrive_date"));
        //flight.setBrigade(getBrigade(flight.getId()));
        return flight;
    }

    //@Override
    public static List<Flight> getAll() throws ConnectionPoolException {
        System.out.println("start getAll");
        //try (Connection connection = dataSourceProvider.getConnection();
        try (Connection connection = retrieveConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_FLIGHT)) {
            ResultSet resultSet = ps.executeQuery();
            List<Flight> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(extractFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            //LOGGER.warn(ERROR_MESSAGE, e);
            throw new DataAccessException("get all flights", e);
        }
    }



    @Override
    public Flight getById(int id) {
        return null;
    }

    @Override
    public boolean add(Flight flight) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean updateFlight(Flight flight) {
        return false;
    }

    @Override
    public Brigade getBrigade(int id) {
        return null;
    }

    @Override
    public Staffer getStaffer(int id) {
        return null;
    }

    @Override
    public List<Staffer> getStaffersByRole(StaffRole role) {
        return null;
    }

    @Override
    public boolean chaneStatus(int id, Status status) {
        return false;
    }

    @Override
    public boolean setBrigade(int id, Brigade brigade) {
        return false;
    }

    /*@Override
    public Flight getById(int id) {
        return (Flight) get(id, Query.get(GET_FLIGHT_BY_ID));
    }

    @Override
    public boolean add(Flight flight) {
        String sql = Query.get(ADD_FLIGHT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setString(k++, flight.getName());
            ps.setString(k++, flight.getDepartureLocation());
            ps.setString(k++, flight.getArriveLocation());
            ps.setInt(k++, Status.index(Status.valueOf(flight.getStatus())));
            ps.setDate(k++, new Date(flight.getDepartureDate().getTime()));
            ps.setDate(k++, new Date(flight.getArriveDate().getTime()));
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        removeBrigade(id);
        return delete(id, Query.get(REMOVE_FLIGHT));
    }

    private boolean removeBrigade(int id) {
        String sql = Query.get(REMOVE_BRIGADE);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
        return false;
    }

    @Override
    public boolean updateFlight(Flight flight) {
        String sql = Query.get(UPDATE_FLIGHT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setString(k++, flight.getName());
            ps.setString(k++, flight.getDepartureLocation());
            ps.setString(k++, flight.getArriveLocation());
            ps.setInt(k++, Status.index(Status.valueOf(flight.getStatus())));
            ps.setDate(k++, new Date(flight.getDepartureDate().getTime()));
            ps.setDate(k++, new Date(flight.getArriveDate().getTime()));
            ps.setInt(k++, flight.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
        return false;
    }

    @Override
    public Brigade getBrigade(int id) {
        String sql = Query.get(GET_BRIGADE);
        Brigade brigade = null;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                brigade = new Brigade();
                createBrigade(brigade, getStaffer(resultSet.getInt("staff_id")));
                while (resultSet.next()) {
                    createBrigade(brigade, getStaffer(resultSet.getInt("staff_id")));
                }
            }
            return brigade;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public Staffer getStaffer(int id) {
        String sql = Query.get(GET_STAFFER_BY_ID);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Staffer staffer = null;
            if (resultSet.next()) {
                staffer = new Staffer();
                staffer.setId(resultSet.getInt("id"));
                staffer.setFirstName(resultSet.getString("first_name"));
                staffer.setLastName(resultSet.getString("last_name"));
                staffer.setRole(StaffRole.values()[resultSet.getInt("role_id") - 1]);
            }
            return staffer;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public List<Staffer> getStaffersByRole(StaffRole role) {
        String sql = Query.get(GET_BY_ROLE);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, StaffRole.getRoleId(role));
            ResultSet resultSet = ps.executeQuery();
            List<Staffer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getStaffer(resultSet.getInt("id")));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public boolean chaneStatus(int id, Status status) {
        String sql = Query.get(CHANGE_STATUS);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setInt(k++, Status.index(status));
            ps.setInt(k++, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
        }
        return false;
    }

    @Override
    public boolean setBrigade(int id, Brigade brigade) {
        String sql = Query.get(ADD_BRIGADE);
        List<Staffer> brigadeList = new ArrayList<>();
        brigadeList.add(brigade.getPilot());
        brigadeList.add(brigade.getNavigator());
        brigadeList.add(brigade.getRadioman());
        for (Staffer staffer : brigade.getStewardess()) {
            brigadeList.add(staffer);
        }
        for (Staffer staffer : brigadeList) {
            try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                int k = 1;
                ps.setInt(k++, id);
                ps.setInt(k++, staffer.getId());
                if (ps.executeUpdate() > 0) {
                    continue;
                }
            } catch (SQLException e) {
                LOGGER.warn(ERROR_MESSAGE, sql, e);
            }
        }
        return true;
    }*/


    private Brigade createBrigade(Brigade brigade, Staffer staffer) {
        switch (staffer.getRole()) {
            case "PILOT": {
                brigade.setPilot(staffer);
                break;
            }
            case "RADIOMAN": {
                brigade.setRadioman(staffer);
                break;
            }
            case "NAVIGATOR": {
                brigade.setNavigator(staffer);
                break;
            }
            case "STEWARDESS": {
                brigade.addStewardess(staffer);
                break;
            }
        }
        return brigade;
    }
}
