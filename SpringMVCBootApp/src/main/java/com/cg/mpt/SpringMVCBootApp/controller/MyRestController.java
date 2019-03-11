package com.cg.mpt.SpringMVCBootApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.beans.Customer;
import com.cg.springmvc.exception.InvalidCustomerException;
import com.cg.springmvc.exception.InvalidMobileNumber;
import com.cg.springmvc.service.ICustomerService;


@RestController
public class MyRestController {
	
	
	@Autowired
	ICustomerService service;
	
	public ICustomerService getService() {
		return service;
	}
	public void setService(ICustomerService service) {
		this.service = service;
	}
	
/*@RequestMapping(value="/addCustomer",method=RequestMethod.POST,produces="application/json")
public Customer addCustomer(@RequestParam("fname") String firstname,
		@RequestParam("lname") String lastname,
		@RequestParam("age") int age,
		@RequestParam("city") String city,
		@RequestParam("mobno") String mobno,
		@RequestParam("email") String email)
{
	Customer cust=new Customer();
	cust.setFirstName(firstname);
	cust.setLastName(lastname);
	cust.setAge(age);
	cust.setCity(city);
	cust.setMobileNo(mobno);
	cust.setEmail(email);
	
	service.addCustomer(cust);
	return cust;
	
}*/
	@RequestMapping(value="/addCustomer",method=RequestMethod.POST,
			consumes="application/json",
			produces="application/json")
	public Customer addCustomer(@RequestBody Customer cust)
	{
		cust=service.addCustomer(cust);
		return cust;
		
	}
@RequestMapping(value="/findCustomer/{custid}",method=RequestMethod.GET,produces="application/json")
public Customer findCustomer(@PathVariable int custid)
{
	Customer cust=service.findCustomer(custid);
	return cust;
}
@RequestMapping(value="/updateCustomer",method=RequestMethod.POST,
consumes="application/json",
produces="application/json")
public Customer updateCustomer(@RequestBody Customer cust)
{
	 cust=service.updateCustomer(cust);
	return cust;
	
}
@RequestMapping(value="/getCustomerList",method=RequestMethod.POST,produces="application/json")
public List<Customer> getCustomerList()
{
	List<Customer> list=service.getCustomerList();
	return list;
	
}
@RequestMapping(value="/removeCustomer/{custid}",method=RequestMethod.POST,produces="application/json")
public Customer removeCustomer(@PathVariable int custid)
{
	Customer cust=service.removeCustomer(custid);
	return cust;
}
@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Id Does not exist")
@ExceptionHandler({InvalidCustomerException.class})
public void handleException()
{	
}
@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Mobile Id is not valid")
@ExceptionHandler({InvalidMobileNumber.class})
public void handle1Exception()
{	
}
}
