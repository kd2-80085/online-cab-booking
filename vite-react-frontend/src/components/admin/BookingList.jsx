import React, { useState, useEffect } from 'react';
import './css/BookingList.css'; // Import your custom CSS file
import adminService from '../../services/admin.service';
const BookingList = () => {
    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
adminService.getBookings()
            .then(response => {
                setBookings(response.data);
                setLoading(false);
            })
            .catch(error => {
                setError(error.message);
                setLoading(false);
            });
    }, []);

    console.log(bookings);
    if (loading) {
        return <div className="text-center">Loading...</div>;
    }

    if (error) {
        return <div className="text-center">Error: {error}</div>;
    }

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Booking List</h2>
            {bookings.length === 0 ? (
                <div className="text-center">No bookings available.</div>
            ) : (
                <div className="table-responsive">
                    <table className="table table-custom">
                        <thead>
                            <tr>
                                <th>Booking ID</th>
                                <th>Booking Date/Time</th>
                                <th>Status</th>
                                <th>Type</th>
                                <th>Car ID</th>
                                <th>Customer ID</th>
                                <th>Driver ID</th>
                                <th>Pickup Location</th>
                                <th>Drop Location</th>
                                <th>Taxi Type</th>
                                <th>Pickup Time</th>
                                <th>Trip ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            {bookings.map(booking => (
                                <tr key={booking.id}>
                                    <td>{booking.id}</td>
                                    <td>{booking.bookingDateTime}</td>
                                    <td>{booking.bookingStatus}</td>
                                    <td>{booking.bookingType}</td>
                                    <td>{booking.carId}</td>
                                    <td>{booking.customerId}</td>
                                    <td>{booking.driverId}</td>
                                    <td>{booking.pickUpLocation || '-'}</td>
                                    <td>{booking.dropLocation || '-'}</td>
                                    <td>{booking.taxiType}</td>
                                    <td>{booking.pickupTime}</td>
                                    <td>{booking.tripId}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default BookingList;
