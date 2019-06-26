package lt.akademijait.warehouse.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.akademijait.warehouse.countries.service.CountryService;
import lt.akademijait.warehouse.customer.model.HolidayData;
import lt.akademijait.warehouse.customer.model.CustomerData;
import lt.akademijait.warehouse.customer.service.CustomerService;
import lt.akademijait.warehouse.inventory.model.Inventory;

@RestController
@Api(value = "customer")
@RequestMapping(value = "/api/customers")
public class CustomerController {

	private final CustomerService customerService;
	//private final CountryService countryService;

	// konstruktorius
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
		//this.countryService = countryService;
	}

	// Visų švenčių gavimas
	/*@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get holiday list", notes = "Returns list of existing holidays")
	public List<HolidayData> getHolidayList() {
		return holidayService.getFullListOfHolidays();
	}*/
	
	// Visų klientų gavimas
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get customer list", notes = "Returns list of existing customers")
	public List<CustomerData> getCustomersList() {
		return customerService.getFullListOfCustomers();
	}

	// vieno kliento gavimas
	@RequestMapping(path = "/{customerCode}", method = RequestMethod.GET)
	@ApiOperation(value = "Get customer", notes = "Returns selected customer")
	public CustomerData getCustomerByCustomerCode(
			@ApiParam(value = "Customer code", required = true) @PathVariable final String customerCode) {
			return customerService.findOneCustomerByCustomercode(customerCode);
			//return holidayService.findHolidayByTitle(title);
	}

	// naujos šventės suvedimas
	/*@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new holiday", notes = "Creates new holiday with provided data")
	public ResponseEntity<?> createHoliday(
			@ApiParam(value = "Holiday data", required = true) @Valid @RequestBody final CreateHolidayCommand chc) {
		return holidayService.createHoliday(chc.getTitle(), chc.getDescription(), chc.getImage(), chc.getType(),
				chc.isFlag(), new ArrayList<Country>());
	}*/
	
	// naujo kliento SU DATA suvedimas
		@RequestMapping(method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.CREATED)
		@ApiOperation(value = "Create new customer", notes = "Creates new customer with provided data")
		public ResponseEntity<?> createCustomer(
				@ApiParam(value = "Customer data", required = true) @Valid @RequestBody final CreateCustomerCommand ccc) {
			return customerService.createCustomer(ccc.getFirstName(), ccc.getLastName(), ccc.getBirthday(), ccc.getPhoneNumber(),
					ccc.getCustomerType());
		}

	// klineto atnaujinimas
	@RequestMapping(path = "/{customerCode}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Edit customer", notes = "Change selected customer's data")
	public void updateHoliday(
			@ApiParam(value = "Customer code", required = true) @PathVariable final String customerCode,
			@ApiParam(value = "Customer data", required = true) @Valid @RequestBody final CreateCustomerCommand ccc) {

		customerService.updateCustomer(customerCode, ccc.getFirstName(), ccc.getLastName(), ccc.getBirthday(), ccc.getPhoneNumber(),
				ccc.getCustomerType());
	}

	// Kliento trynimas------------------------------------------

	@RequestMapping(path = "/{customerCode}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete customer", notes = "Deletes selected customer")
	public void deleteHoliday(@PathVariable final String customerCode) {
		customerService.deleteCustomer(customerCode);
		System.out.println("Deleting holiday: " + customerCode);
	}
	
	
	//////////////////////////////////////////////////////
	
	// Čia tolimesnis tarpusavio ryšių kodas 
	
	
	// Vienos šventės šalių nuskaitymas
	/*@RequestMapping(path = "/{code}/addedCountries", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Vienos šventės šalių gavimas", notes = "Kokioms šalims priskirta šventė")
	public List<String> getHolidayCountries(
			@ApiParam(value = "Holiday title", required = true)
			@PathVariable final String code) {
		return customerService.getHolidayCountries(code);
	}
	
	//Šalių pridėjimas šventei
	@RequestMapping(path = "/{code}/addingCountries", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Šalių šventei pridėjimas", notes = "Pažymėtų šalių pridėjimas šventei")
	public void addCountriesToHoliday(
			@ApiParam(value = "Holiday title", required = true)
			@PathVariable final String code,
			@ApiParam(value = "Countries list", required = true)
			@Valid @RequestBody final List<String> countryList) {
		
		customerService.addCountryToHoliday(code, countryList);
		return;
	}
	
	//Šalių pašalinimas iš šventės
	@RequestMapping(path = "/{code}/removingCountries", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Šalių pašalinimas iš šventės", notes = "Pažymėtų šalių pašalinimas iš šventės")
	public void removeCountries (
			@ApiParam(value = "Holiday title another", required = true)
			@PathVariable final String code,
			@ApiParam(value = "Countries list another", required = true)
			@Valid @RequestBody final List<String> countryList) {
		customerService.removeCountryFromHoliday(code, countryList);
		return;
	}*/
}
