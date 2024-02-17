import React, { useState, useEffect } from 'react';
import ownerService from '../../services/owner.service';

const CarList = () => {
    const [cars, setCars] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    

    useEffect(() => {
        const ownerId = 5;
    ownerService
            .getCars(ownerId) 
            .then(response => {
                setCars(response.data);
                setLoading(false);
            })
            .catch(error => {
                setError(error.message);
                setLoading(false);
            });
    }, []);

    console.log(cars);
    if (loading) {
        return <div className="text-center">Loading...</div>;
    }

    if (error) {
        return <div className="text-center">Error: {error}</div>;
    }

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Car List</h2>
            {cars.length === 0 ? (
                <div className="text-center">No Cars Are Registered</div>
            ) : (
                <div className="table-responsive">
                    <table className="table table-custom">
                        <thead>
                            <tr>
                                <th>Car ID</th>
                                <th>Model</th>
                                <th>Company</th>
                                <th>Registration No</th>
                                <th>Driver Name</th>
                                <th>Location</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {cars.map(car => (
                                <tr key={car.id}>
                                    <td>{car.id}</td>
                                    <td>{car.model}</td>
                                    <td>{car.company}</td>
                                    <td>{car.registrationNo}</td>
                                    <td>{car.driverName}</td>
                                    <td>{car.location}</td>
                                    <td>{car.status}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default CarList;
