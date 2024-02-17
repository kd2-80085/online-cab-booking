import React, { useState, useEffect } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import Autosuggest from 'react-autosuggest';
import './TaxiBooking.css'; // Import custom CSS file for styling

const TaxiBooking = () => {
    const [bookingDetails, setBookingDetails] = useState({
        pickupLocation: '',
        dropLocation: '',
        pickupTime: '',
        distance: '',
        taxiType: ''
    });

    const [pickupSuggestions, setPickupSuggestions] = useState([]);
    const [dropSuggestions, setDropSuggestions] = useState([]);
    const [errors, setErrors] = useState({});
    const [locationOptions, setLocationOptions] = useState([]);
    const taxiTypes = ['Sedan', 'SUV', 'Mini'];

    useEffect(() => {
        async function fetchLocations() {
            try {
                const response = await fetch('http://localhost:8080/customer/bookings/locations');
                if (!response.ok) {
                    throw new Error('Failed to fetch location data');
                }
                const data = await response.json();
                setLocationOptions(data);
            } catch (error) {
                console.error('Error:', error);
            }
        }
        fetchLocations();
    }, []);

    const handleChange = (name, value) => {
        setBookingDetails(prevState => ({
            ...prevState,
            [name]: value
        }));
        setErrors(prevState => ({
            ...prevState,
            [name]: ''
        }));
    };

    const validateForm = () => {
        const errors = {};
        if (!bookingDetails.pickupLocation.trim()) {
            errors.pickupLocation = 'Pickup location is required';
        }
        if (!bookingDetails.dropLocation.trim()) {
            errors.dropLocation = 'Drop location is required';
        }
        if (!bookingDetails.pickupTime) {
            errors.pickupTime = 'Pickup time is required';
        }
        if (!bookingDetails.taxiType) {
            errors.taxiType = 'Taxi type is required';
        }
        setErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const onPickupSuggestionsFetchRequested = ({ value }) => {
        setPickupSuggestions(getSuggestions(value));
    };

    const onPickupSuggestionsClearRequested = () => {
        setPickupSuggestions([]);
    };

    const onDropSuggestionsFetchRequested = ({ value }) => {
        setDropSuggestions(getSuggestions(value));
    };

    const onDropSuggestionsClearRequested = () => {
        setDropSuggestions([]);
    };

    const getSuggestions = (value) => {
        const inputValue = value.trim().toLowerCase();
        const inputLength = inputValue.length;
        return inputLength === 0 ? [] : locationOptions.filter(option =>
            option.label.toLowerCase().includes(inputValue)
        );
    };

    const getSuggestionValue = suggestion => suggestion.label;

    const renderSuggestion = suggestion => (
        <div>
            {suggestion.label}
        </div>
    );

    const handlePickupChange = (event, { newValue }) => {
        handleChange('pickupLocation', newValue);
    };

    const handleDropChange = (event, { newValue }) => {
        handleChange('dropLocation', newValue);
    };

    const handleTaxiTypeChange = (event) => {
        const { value } = event.target;
        handleChange('taxiType', value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validateForm()) {
            try {
                const response = await fetch('http://localhost:8080/customer/bookings/distance');
                if (!response.ok) {
                    throw new Error('Failed to fetch distance data');
                }
                const data = await response.json();
                const matchingBooking = data.find(booking =>
                    booking.pickupLocation.toLowerCase() === bookingDetails.pickupLocation.toLowerCase() &&
                    booking.dropLocation.toLowerCase() === bookingDetails.dropLocation.toLowerCase()
                );
                if (matchingBooking) {
                    const { distance } = matchingBooking;
                    setBookingDetails(prevState => ({
                        ...prevState,
                        distance
                    }));
                    console.log('Form submitted:', bookingDetails);
                } else {
                    console.log('No matching booking found');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        }
    };

    return (
        <div className="booking-page">
            <Container>
                <Row className="justify-content-center">
                    <Col xs={12} md={8} lg={6}>
                        <h2 className="booking-title text-center">Book a Taxi</h2>
                        <div className="booking-form">
                            <Form onSubmit={handleSubmit} noValidate>
                                <Form.Group>
                                    <Form.Label>Pickup Location:</Form.Label>
                                    <Autosuggest
                                        inputProps={{
                                            value: bookingDetails.pickupLocation,
                                            onChange: handlePickupChange,
                                            placeholder: 'Enter pickup location',
                                            className: 'form-control'
                                        }}
                                        suggestions={pickupSuggestions}
                                        onSuggestionsFetchRequested={onPickupSuggestionsFetchRequested}
                                        onSuggestionsClearRequested={onPickupSuggestionsClearRequested}
                                        getSuggestionValue={getSuggestionValue}
                                        renderSuggestion={renderSuggestion}
                                    />
                                    {errors.pickupLocation && <div className="error">{errors.pickupLocation}</div>}
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label>Drop Location:</Form.Label>
                                    <Autosuggest
                                        inputProps={{
                                            value: bookingDetails.dropLocation,
                                            onChange: handleDropChange,
                                            placeholder: 'Enter drop location',
                                            className: 'form-control'
                                        }}
                                        suggestions={dropSuggestions}
                                        onSuggestionsFetchRequested={onDropSuggestionsFetchRequested}
                                        onSuggestionsClearRequested={onDropSuggestionsClearRequested}
                                        getSuggestionValue={getSuggestionValue}
                                        renderSuggestion={renderSuggestion}
                                    />
                                    {errors.dropLocation && <div className="error">{errors.dropLocation}</div>}
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label>Pickup Time:</Form.Label>
                                    <Form.Control
                                        type="datetime-local"
                                        value={bookingDetails.pickupTime}
                                        onChange={(e) => handleChange('pickupTime', e.target.value)}
                                        required
                                    />
                                    {errors.pickupTime && <div className="error">{errors.pickupTime}</div>}
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label>Taxi Type:</Form.Label>
                                    <Form.Control
                                        as="select"
                                        value={bookingDetails.taxiType}
                                        onChange={handleTaxiTypeChange}
                                        className="form-control"
                                    >
                                        <option value="">Select taxi type</option>
                                        {taxiTypes.map(type => (
                                            <option key={type} value={type}>{type}</option>
                                        ))}
                                    </Form.Control>
                                    {errors.taxiType && <div className="error">{errors.taxiType}</div>}
                                </Form.Group>
                                <div className="button-container">
                                    <Button variant="primary" type="submit">Book Taxi</Button>
                                    <Button variant="secondary" onClick={() => setBookingDetails({
                                        pickupLocation: '',
                                        dropLocation: '',
                                        pickupTime: '',
                                        distance: '',
                                        taxiType: ''
                                    })}>Reset Details</Button>
                                </div>
                            </Form>
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default TaxiBooking;
