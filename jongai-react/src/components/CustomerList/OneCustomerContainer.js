import React from 'react';
//import PropTypes from 'prop-types';
import OneCustomerComponent from './OneCustomerComponent';
import axios from 'axios';

class OneCustomerContainer extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      customerCode: "",
      firstName: "",
      lastName: '',
      birthday: '',
      phoneNumber: "",
      customerType: '',
      
      addedCountries: [],
      allCountries: [],
      countriesToAdd: [],
      countriesToRemove: []
    }; 
  }

  //  handleAddToCart = (userName) => {      
  //     console.log("--------------vartotojo vardas yra " + userName);
  //     axios.post('http://localhost:8080/api/users/' + userName + '/cart-products', this.state)

  //         .then((response) => {
  //             console.log("Sėkminga" + response);
  //         })
  //         .catch(function (error) {
  //             console.log(error);
  //         });
  // }

  // handleChangeOfQuantity = (event) => {
  //     this.setState({ quantity: event.target.value });
  //     console.log(this.state.quantity);
  //   }

  componentDidMount() {
    this.getOneCustomer();
    
    // TODO kitus dalykus nuskaityti 

    //this.getHolidayCountryList();
    //this.getCountryList();
  }

  getOneCustomer() {
    const position = this.props.match.params.customerCode;
    axios.get('http://localhost:8080/api/customers/' + (position))
      .then((response) => {
        //this.setState(response.data);
        console.log("-----------------Response data id yra: " + response.data.id);
        console.log("-----------------Response data title yra: " + response.data.title);
        console.log("-----------------Response data simpleDate yra: " + response.data.simpleDate);
        this.setState({
          customerCode: response.data.customerCode,
          firstName: response.data.firstName,
          lastName: response.data.lastName,
          birthday: response.data.birthday,
          phoneNumber: response.data.phoneNumber,
          customerType: response.data.customerType
        
          

          // addedCountries: response.data.countries
          // aš galėčiau čia jau turėti visas šventės šalis, nes po @JsonIgnore galiu Listą iškart turėti prie šalies objekto
        })
      })
      .catch((error) => {
        console.log(error);
      });
  }

  getHolidayCountryList() {
    const position = this.props.match.params.code;
    axios.get('http://localhost:8080/api/holidays/' + position + '/addedCountries')
      .then((response) => {
        this.setState({ addedCountries: response.data })
      })
      .catch((error) => {
        console.log(error);
      });
  }

  getCountryList() {
    axios.get('http://localhost:8080/api/countries')
      .then((response) => {
        this.setState({ allCountries: response.data.map(item => item.title) })
      })
      .catch((error) => {
        console.log(error);
      });
  }

  availableCountrySelectionHandler = event => {
    this.setState({ countriesToAdd: [...event.target.selectedOptions].map(o => o.value) })
  }

  countryRemovingHandler = event => {
    this.setState({ countriesToRemove: [...event.target.selectedOptions].map(o => o.value) })
  }

  showAvailableCountries = () => {
    if (this.state.allCountries.length === 0) {
      return (
        <option value="" disabled>
          Nėra šalių pasirinkimui
            </option>
      );
    } else {
      let countries = this.state.allCountries
        .map((country, index) => {

          let isShown = true;

          this.state.addedCountries.forEach((c, index) => {
            if (c === country) {
              isShown = false;
            }
          });

          if (isShown)
            return (
              <option key={country + index} value={country}>
                {country}
              </option>
            );
          else {
            return null;
          }
        })
        .filter(c => c !== null);
      if (countries.length === 0) {
        return (
          <option value="" disabled>
            Visos šalys jau pasirinktos
              </option>
        );
      } else return countries;
    }
  };

  addCountriesToHoliday = event => {
    const position = this.props.match.params.code;
    axios.put('http://localhost:8080/api/holidays/' + position + '/addingCountries', this.state.countriesToAdd)
      .then(() => this.getHolidayCountryList())
      .catch(function (error) {
        console.log(error);
      });
  }

  removeCountriesFromHoliday = event => {
    const position = this.props.match.params.code;
    axios.put('http://localhost:8080/api/holidays/' + position + '/removingCountries', this.state.countriesToRemove)
      .then(() => this.getHolidayCountryList())
      .catch(function (error) {
        console.log(error);
      });
  }

  render() {
    if (this.state.customerCode) {
      return (
        <div>
          <OneCustomerComponent
            customerCode={this.state.customerCode}
            firstName={this.state.firstName}
            lastName={this.state.lastName}
            birthday={this.state.birthday}
            phoneNumber={this.state.phoneNumber}
            customerType={this.state.customerType}
            
            
            addedCountries={this.state.addedCountries}
            allCountries={this.state.allCountries}
            showAvailableCountries={this.showAvailableCountries}
            availableCountrySelectionHandler={this.availableCountrySelectionHandler}
            addCountriesToHoliday={this.addCountriesToHoliday}
            removeCountriesFromHoliday={this.removeCountriesFromHoliday}
            countryRemovingHandler={this.countryRemovingHandler}
          />
        </div>
      );
    } else {
      return (
        <div className="text-center">
            <div className="spinner-border text-danger" role="status">
                <span className="sr-only">Loading data...</span>
            </div>
        </div>        
      );
    } 
  }
}

export default OneCustomerContainer;