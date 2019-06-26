package lt.akademijait.warehouse.inventory.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lt.akademijait.warehouse.customer.model.Customer;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column (unique=true, nullable=false)
	private String countryCode;
	@Column(unique=true, nullable=false) 
	private String title;
	@Column
	private String image;
	@Column
	private String president;

	// senasis apjungimo -> čia yra ne savininkas
	// @ManyToMany(mappedBy="countries")
	// List<Holiday> holidays = new ArrayList<>();
	
	// Dabartinis veikiantis variantas, kai čia yra savininko pusė
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "holiday_country", joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "holiday_id", referencedColumnName = "id"))
	List<Customer> holidays = new ArrayList<>();

	//@OneToOne(cascade = {CascadeType.ALL}) // MERGE, CascadeType.DETACH})
	//private ProductDetails productDetails;
	//nebūtinas šitas laukas surišimui
	//@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
	//private List<Cart> cart = new ArrayList<Cart>();

	public Inventory() {
	}

	//konstruktorius be id
	public Inventory(String countryCode, String title, String image, String president) {
		this.countryCode = countryCode;
		this.title = title;
		this.image = image;
		this.president = president;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public List<Customer> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Customer> holidays) {
		this.holidays = holidays;
	}
	
	/*public void addHoliday(Customer customer) {
		this.holidays.add(customer);
		customer.getCountries().add(this);
	}
	
	public void removeHoliday(Customer customer) {
		this.holidays.remove(customer);
		customer.getCountries().remove(this);
	}*/
	
}



	