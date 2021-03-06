/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacab.model;

import com.alphacab.util.JourneyCostCalculator;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul, Trym
 */
public class UserDao {

    //initialise objects
    Connection con = null; //connection to databse
    Statement sm = null; //for executing sql statements on the database
    ResultSet rs = null; //containing the result set returned from the sql query

    //constructor
    public UserDao() {
    }

    //db connection function
    public void connect(Connection con) {
        this.con = con;
    }

    //SQL - update functions
    private boolean query(String query) {
        try {
            sm = con.createStatement();
            rs = sm.executeQuery(query);
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return false;
    }

    private boolean update(String query) {
        try {
            sm = con.createStatement();
            sm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return false;
    }

    private int updateAndGetAutoGeneratedKey(String query) {
        try {
            //execute statement
            sm = con.createStatement();
            sm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            //get autoGenerated key
            rs = sm.getGeneratedKeys();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return 0;
    }

    public boolean saveUser(Admin admin) {

        //get user params
        String user = admin.getUsername();
        String pass = admin.getPassword();
        String role = admin.getRole();

        //save admin to user table
        return update("INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('" + user + "', '" + pass + "', '" + role + "')");
    }

    public boolean saveUser(Driver driver) {

        //get user params
        String user = driver.getUsername();
        String pass = driver.getPassword();
        String role = driver.getRole();

        String name = driver.getName();
        String reg = driver.getRegistration();

        //save driver to users table and get auto generated key
        int userID = updateAndGetAutoGeneratedKey("INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('" + user + "', '" + pass + "', '" + role + "')");
        if (userID != 0) {

            //save driver details to drivers table
            boolean driverDetailsAdded = update("INSERT INTO DRIVERS (REGISTRATION, NAME, USERID) VALUES ('" + reg + "', '" + name + "', " + userID + ")");
            if (driverDetailsAdded == true) {
                return true;
            } else {
                //if fail to add driver details to driver table - remove driver from user table
                update("DELETE FROM USERS WHERE username='" + user + "' AND password='" + pass + "'");
            }
        }
        return false;
    }

    public boolean saveUser(Customer customer) {

        //get user params
        String user = customer.getUsername();
        String pass = customer.getPassword();
        String role = customer.getRole();

        String name = customer.getName();
        String addr = customer.getAddress();

        //save customer to users table and get auto generated key
        int userID = updateAndGetAutoGeneratedKey("INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('" + user + "', '" + pass + "', '" + role + "')");
        if (userID != 0) {

            //save customer details to customer table
            boolean customerDetailsAdded = update("INSERT INTO CUSTOMER (NAME, ADDRESS, USERID) VALUES ('" + name + "', '" + addr + "', " + userID + ")");
            if (customerDetailsAdded == true) {
                return true;
            } else {
                update("DELETE FROM USERS WHERE username='" + user + "' AND password='" + pass + "'");
            }
        }
        return false;
    }

    public boolean removeUser(int userID) {
        String role = "";
        //get role from user table
        try {
            query("SELECT * FROM USERS WHERE ID=" + userID);
            while (rs.next()) {
                role = rs.getString(4).toLowerCase();
            }
            
            query("SELECT REGISTRATION FROM DRIVERS WHERE USERID =" + userID);
            String registration = "";
            while(rs.next()){
                registration = rs.getString(1);
            }
            
            System.out.print("a------------------------------------");

            //remove from drivers/customer table
            switch (role) {
                case ("driver"):
                    update("DELETE FROM JOURNEY WHERE registration='" + registration +"'");

                    update("DELETE FROM DRIVERS WHERE userID=" + userID);
                    break;
                case ("customer"):
                    update("DELETE FROM CUSTOMER WHERE userID=" + userID);
                    break;
                default:
                    break;
            }

            System.out.print("b------------------------------------");

            //remove from user table
            update("DELETE FROM USERS WHERE ID=" + userID);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }

        System.out.print("c------------------------------------");

        return true;
    }

    public User getUser(String username, String password) {
        User u = null;
        try {
            //get user
            boolean querySuccessful = query("SELECT * FROM USERS WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'");
            if (querySuccessful == true) {
                while (rs.next()) {

                    //get general attributes
                    int userID = rs.getInt(1);
                    String user = rs.getString(2);
                    String pass = rs.getString(3);

                    //get user type
                    String userType = rs.getString(4).toLowerCase();

                    switch (userType) {
                        case "admin":

                            System.out.println("loginType: admin");

                            //set user
                            u = new Admin(userID, user, pass);
                            break;
                        case "driver":

                            //get driver specific attributes
                            boolean gotDriver = query("SELECT * FROM DRIVERS WHERE USERID=" + userID + "");
                            if (gotDriver == true) {
                                while (rs.next()) {
                                    String reg = rs.getString(2);
                                    String name = rs.getString(3);

                                    //set user
                                    u = new Driver(userID, user, pass, reg, name);
                                }
                            }
                            break;
                        case "customer":
                            //get customer specific attributes
                            boolean gotCustomer = query("SELECT * FROM CUSTOMER WHERE USERID=" + userID + "");
                            if (gotCustomer == true) {
                                while (rs.next()) {
                                    String name = rs.getString(2);
                                    String addr = rs.getString(3);

                                    //set user
                                    u = new Customer(userID, user, pass, name, addr);
                                }
                            }
                            break;
                        default:
                            System.out.println("glitch in the matrix...");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get user");
        }
        return u;
    }

    public List<User> getAllUsers() {
        //NEED TO DOUBLE CHECK THAT THE DATA USED IN INSTANTIATION IS CORRECT
        List<User> userList = new ArrayList();

        try {
            //get results
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

            //iterate over results
            while (resultSet.next()) {
                User u;

                switch (resultSet.getString(4).toLowerCase()) {
                    case ("driver"):
                        u = new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                        break;
                    case ("customer"):
                        u = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                        break;
                    case ("admin"):
                        u = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                        break;
                    default:
                        u = null;
                        System.out.println("Glitch in the matrix. You aren't supposed to be here...");
                        break;
                }
                userList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }

        return userList;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList();

        try {
            //get results
            query("SELECT * FROM USERS WHERE role='customer'");

            //iterate over results
            while (rs.next()) {

                PreparedStatement ps = con.prepareStatement("SELECT * FROM CUSTOMER WHERE USERID = ?");

                ps.setInt(1, rs.getInt(1));

                ResultSet rSet = ps.executeQuery();

                // some customers are only present in the users table 
                // but not in the customers table this can cause SQL exceptions
                // so use this to skip them.
                if (!rSet.next()) {
                    System.out.println("Customer not fount in customer list!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    continue;
                }

                Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rSet.getString(2), rSet.getString(3));
                customerList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get customer list - SQLException:" + e);
        }

        return customerList;
    }

    public List<Driver> getAllDrivers() {
        List<Driver> driverList = new ArrayList();

        try {
            //get results
            query("SELECT * FROM USERS WHERE role='driver'");

            //iterate over results
            while (rs.next()) {

                PreparedStatement ps = con.prepareStatement("SELECT * FROM DRIVERS WHERE USERID = ?");

                ps.setInt(1, rs.getInt(1));

                ResultSet rSet = ps.executeQuery();
                while (rSet.next()) {
                    Driver u = new Driver(rs.getInt(1), rs.getString(2), rs.getString(3), rSet.getString(2), rSet.getString(3));

                    if (rSet.getString(5) != null) {
                        u.setBusy(true);
                    }

                    driverList.add(u);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get driver list - SQLException:" + e);
        }

        return driverList;
    }

    public boolean addDemand(String name, String startAddress, String endAddress,
            String pickupDate, String pickupTime, int customerID) {

        String sqlStatement = "INSERT INTO DEMANDS (name, address, destination, date, time, status, customerid) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement newDemand = con.prepareStatement(sqlStatement);

            newDemand.setString(1, name);
            newDemand.setString(2, startAddress);
            newDemand.setString(3, endAddress);
            newDemand.setString(4, pickupDate);
            newDemand.setString(5, pickupTime);
            newDemand.setString(6, "Outstanding");
            newDemand.setInt(7, customerID);

            int udatedRows = newDemand.executeUpdate();

            if (udatedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Failed to add new demand - SQLException:" + e);
            System.out.println("-----------------------");
        }

        return false;
    }

    public List<Demand> getDemandsForCustomer(Customer customer) {

        List<Demand> demands = new ArrayList<>();

        String s = "SELECT * FROM DEMANDS WHERE CUSTOMERID = ?";

        Demand tempDemand;

        try {

            PreparedStatement ps = con.prepareStatement(s);
            ps.setInt(1, customer.getId());

            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {

                tempDemand = new Demand(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getTime(6), rSet.getString(7), rSet.getInt(8));

                demands.add(tempDemand);

            }

        } catch (SQLException e) {
            System.out.println("Failed to get demands for customer - SQLException:" + e);
        }

        return demands;

    }

    public List<Journey> getTodaysJourneys(Date date) {

        List<Journey> journeyList = new ArrayList<>();

        String s = "SELECT * FROM JOURNEY WHERE Date = ?";

        System.out.println(date.toString());

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Journey tempJourney;

        try {
            PreparedStatement ps = con.prepareStatement(s);
            ps.setDate(1, sqlDate);

            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {

                String registration = rSet.getString(7);

                tempJourney = new Journey(rSet.getString(2), rSet.getString(3), rSet.getInt(4), rSet.getString(5), rSet.getDouble(6), rSet.getDouble(8), rSet.getDate(9), rSet.getTime(10), getDriverByRegistration(registration));

                journeyList.add(tempJourney);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get journey - SQLException:" + e);
        }

        return journeyList;
    }

    public Driver getDriverById(int id) {

        Driver driver = new Driver(0, "", "");

        String s = "SELECT * FROM Drivers WHERE USERID = ?";

        PreparedStatement ps;

        try {
            ps = con.prepareStatement(s);
            ps.setInt(1, id);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                driver.setId(rSet.getInt(1));
                driver.setName(rSet.getString(3));
                driver.setRegistration(rSet.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return driver;
    }

    public Driver getDriverByRegistration(String registration) {

        Driver driver = new Driver(0, "", "");

        String s = "SELECT * FROM Drivers WHERE Registration = ?";

        PreparedStatement ps;

        try {
            ps = con.prepareStatement(s);
            ps.setString(1, registration);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                driver.setId(rSet.getInt(1));
                driver.setName(rSet.getString(2));
                driver.setBusy(true);
                driver.setRegistration(registration);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return driver;
    }

    public Demand getDemandById(int id) {

        String s = "SELECT * FROM Demands WHERE Id = ?";

        PreparedStatement ps;

        Demand demand = null;

        try {
            ps = con.prepareStatement(s);
            ps.setInt(1, id);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                demand = new Demand(
                        rSet.getInt(1),
                        rSet.getString(2),
                        rSet.getString(3),
                        rSet.getString(4),
                        rSet.getDate(5),
                        rSet.getTime(6),
                        rSet.getString(7),
                        rSet.getInt(8)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return demand;
    }

    public List<Driver> getAvailableDrivers() {

        List<Driver> availableDrivers = new ArrayList<>();

        // Get all drivers
        availableDrivers = getAllDrivers();

        // Remove busy drivers
        // Use the Iterator class to avoid java.util.ConcurrentModificationException
        for (Iterator<Driver> it = availableDrivers.iterator(); it.hasNext();) {
            Driver d = it.next();
            if (d.isBusy()) {
                it.remove();
            }
        }

        return availableDrivers;
    }

    public List<Demand> getOutstandingDemands() {

        List<Demand> outstandingDemands = new ArrayList<>();

        // Get all customers
        List<Customer> tempCustomers = getAllCustomers();

        // Get demands for each customer
        for (Customer c : tempCustomers) {
            for (Demand d : getDemandsForCustomer(c)) {

                // Only keep outstanding demands
                if (d.getStatus().equals("Outstanding")) {
                    outstandingDemands.add(d);
                }
            }
        }

        return outstandingDemands;
    }

    public boolean assignJobToDriver(int driverID, int jobID) {

        String updateDriver = "UPDATE DRIVERS SET STATUS = ? WHERE USERID = ?";
        String updateDemand = "UPDATE DEMANDS SET STATUS = ? WHERE ID = ?";

        try {
            PreparedStatement driverPS = con.prepareStatement(updateDriver);
            PreparedStatement demandPS = con.prepareStatement(updateDemand);

            driverPS.setString(1, "Job id " + jobID);
            driverPS.setInt(2, driverID);

            demandPS.setString(1, "In progress");
            demandPS.setInt(2, jobID);

            driverPS.executeUpdate();
            demandPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public double[] getBaseRateAndRatePerMile() {

        String rateQuery = "SELECT * FROM RATES";

        double[] ret = new double[2];

        try {
            PreparedStatement basePS = con.prepareStatement(rateQuery);

            ResultSet rSet = basePS.executeQuery();

            if (rSet.next()) {

                ret[0] = rSet.getDouble(2);
                ret[1] = rSet.getDouble(3);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return ret;

    }

    public List<Journey> getJourneysByRegistration(Driver user) {
        List<Journey> journeyList = new ArrayList<>();

        try {
            boolean success = query("SELECT * FROM JOURNEY WHERE REGISTRATION = '" + user.getRegistration() + "' ORDER BY DATE, TIME DESC");

            if (success) {
                while (rs.next()) {
                    Journey journey = new Journey(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getDouble(8), rs.getDate(9), rs.getTime(10), user);
                    journeyList.add(journey);
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return journeyList;
    }

    public boolean setBaseRate(double base) {
        String updateBaseRateQuery = "UPDATE Rates SET base_rate = ? WHERE id = (SELECT id FROM Rates FETCH FIRST ROW ONLY)";

        PreparedStatement updateBaseRateStatement;
        try {
            updateBaseRateStatement = con.prepareStatement(updateBaseRateQuery);
            updateBaseRateStatement.setDouble(1, base);
            updateBaseRateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public boolean setPerMile(double perMile) {
        String updatePerMileQuery = "UPDATE Rates SET rate_per_mile = ? WHERE id = (SELECT id FROM Rates FETCH FIRST ROW ONLY)";

        PreparedStatement updatePerMileStatement;

        try {
            updatePerMileStatement = con.prepareStatement(updatePerMileQuery);
            updatePerMileStatement.setDouble(1, perMile);
            updatePerMileStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public void createJourney(Driver driver, Demand demand) {
        String insertJourneyQuery = "INSERT INTO Journey (Name, Address, Destination, Distance, customerid, Registration, Cost, Date, Time) VALUES (?,?,?,?,?,?,?,?,?)";
        int id = getCustomerId(demand.getCustomerid());
        PreparedStatement insertJourneyStatement;

        try {
            
            JourneyCostCalculator costCalculator = new JourneyCostCalculator(con);
            
            insertJourneyStatement = con.prepareStatement(insertJourneyQuery);

            insertJourneyStatement.setString(1, demand.getName());
            insertJourneyStatement.setString(2, demand.getAddress());
            insertJourneyStatement.setString(3, demand.getAddress());
            insertJourneyStatement.setDouble(4, demand.calculateDistance());
            insertJourneyStatement.setInt(5, id);
            insertJourneyStatement.setString(6, driver.getRegistration());
            insertJourneyStatement.setDouble(7, costCalculator.calculateCostIncVAT(demand.calculateDistance()));
            insertJourneyStatement.setDate(8, demand.getDate());
            insertJourneyStatement.setTime(9, demand.getTime());
            
            insertJourneyStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getCustomerId(int customerId) {
        String s = "SELECT id FROM Customer WHERE UserId = ?";

        PreparedStatement ps;
        
        int id = 0;
        
        try {
            ps = con.prepareStatement(s);
            ps.setInt(1, customerId);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                id = rSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
