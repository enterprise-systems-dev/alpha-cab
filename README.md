# Alpha-Cab Ltd Booking System

Implementation of a web-based booking system for Alpha-Cab Ltd minicab company.

## use cases to implement
* Admin 
  * List all drivers (Working)
  * List all customers (Working)
  * Daily report (turnover, no. customers served) (Working)
    * Added `daily_report.jsp` that gets an arraylist of type Journey from servlet and displays in table with total turnover
    * TODO: add date validation in servlet, so that invalid dates cannot be entered
  * List all customers served per day (including: destination, charge)
  *	Create invoice (cost, costWithVAT)
  *	Change price of destination
  *	Assign customer to driver
*	Driver
    *	List all jobs
*	Customer
    *	Book (name, address, destination address, date, time,) [if first time add a new customer record, in the case of a guest customer]


