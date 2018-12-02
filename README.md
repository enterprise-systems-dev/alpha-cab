# Alpha-Cab Ltd Booking System

Implementation of a web-based booking system for Alpha-Cab Ltd minicab company.

## use cases to implement
* Admin 
  * List all drivers (Working)
  * List all customers (Working)
  * Daily report (turnover, no. customers served)
    * Added `daily_report.jsp` that gets an arraylist of type Journey from servlet and displays in table with total turnover
  * List all customers served per day (including: destination, charge)
  *	Create invoice (cost, costWithVAT)
  *	Change price of destination
  *	Assign customer to driver
*	Driver
    *	List all jobs
*	Customer
    *	Book (name, address, destination address, date, time,) [if first time add a new customer record, in the case of a guest customer]

-----

I have been working on the Customers functionalities, they can now book a cab and view all of their booking history. Guest customers are now working too. I also got the header to work again and did a bug fix which breaks the login for the users "dirver - driver" and "customer - customer" because they are not present in the driver and customer tables. The Guest customer is not very good but it works and seems dificult to implement properly and the customer view has no formating. Another problem is that the guests can view the cab history for all guests because there is no way to differentiate between them. Tommorw I will continue working with Customer related things or with getting the admin and drivers to deal with the customers requests (unless someone else wants to do it). 

I am going to get admin to pass demands to drivers and drivers to complete them and turn them into journeys. List journeys might not work because there are no journeys today.

Before letting the admin assign jobs to drivers we need to know how will the driver know what jobs to do. Will it be through objects or though the database?

Marcus


