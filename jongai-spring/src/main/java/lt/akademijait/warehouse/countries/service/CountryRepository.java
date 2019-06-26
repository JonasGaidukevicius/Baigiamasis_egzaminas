package lt.akademijait.warehouse.countries.service;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.akademijait.warehouse.inventory.model.Inventory;

public interface CountryRepository extends JpaRepository<Inventory, Long> {
	
	Inventory findCountryByCountryCode(String countryCode);
	void deleteCountryByCountryCode(String countryCode);
	
	//Šitas metodas naudojamas, kad surasti šalį pagal pavadinimą,
	//kai vartotojas iš galimų šalių renkasi, kokias šalis priskirti šventei
	Inventory findCountryByTitle(String title);
	boolean existsByTitle(String title);
}
