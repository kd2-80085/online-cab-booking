import React, { useState, useEffect } from 'react';
import adminService from '../../services/admin.service';
import './css/GetOwnersDetails.css'; // Import custom CSS file for styling

const GetOwnersDetails = () => {
    const [owners, setOwners] = useState([]);
    const [updateStatusMessage, setUpdateStatusMessage] = useState('');
    const [deleteStatusMessage, setDeleteStatusMessage] = useState('');
    const [pageNumber, setPageNumber] = useState(0);
    const [pageSize, setPageSize] = useState(3);
    const [totalPages, setTotalPages] = useState(0);

    const fetchOwners = async () => {
        try {
            const response = await adminService.getOwners(pageNumber, pageSize);
            console.log(response);
    
            // Check if response.data is an array
            if (Array.isArray(response.data)) {
                setOwners(response.data);
            } else {
                console.error('Error: Response data is not an array');
                setOwners([]); // Set owners to an empty array
            }
    
            setTotalPages(response.headers['total-pages']);
        } catch (error) {
            console.error('Error fetching owners:', error);
            setOwners([]); // Set owners to an empty array
        }
    };

    const updateOwnerStatus = async (id) => {
        try {
            await adminService.updateOwnerStatus(id);
            setUpdateStatusMessage('Owner status updated successfully.');
            fetchOwners();
            setTimeout(() => {
                setUpdateStatusMessage('');
            }, 3000); // Remove the message after 3 seconds
        } catch (error) {
            console.error('Error updating owner status:', error);
            setUpdateStatusMessage('Failed to update owner status. Please try again later.');
            setTimeout(() => {
                setUpdateStatusMessage('');
            }, 2000); // Remove the message after 3 seconds
        }
    };

    const deleteOwner = async (id) => {
        try {
            await adminService.deleteOwner(id);
            setDeleteStatusMessage('Owner deleted successfully.');
            fetchOwners();
            setTimeout(() => {
                setDeleteStatusMessage('');
            }, 3000); // Remove the message after 3 seconds
        } catch (error) {
            console.error('Error deleting owner:', error);
            setDeleteStatusMessage('Failed to delete owner. Please try again later.');
            setTimeout(() => {
                setDeleteStatusMessage('');
            }, 3000); // Remove the message after 3 seconds
        }
    };

    const nextPage = () => {
        setPageNumber(pageNumber + 1);
    };

    const prevPage = () => {
        setPageNumber(pageNumber - 1);
    };

    useEffect(() => {
        fetchOwners();
    }, [pageNumber]);

    return (
        <div className="container mt-5">
            <h1 className="mb-4">Owners Details</h1>
            {updateStatusMessage && <div className="alert alert-success">{updateStatusMessage}</div>}
            {deleteStatusMessage && <div className="alert alert-danger">{deleteStatusMessage}</div>}
            <div className="row">
                {owners.map(owner => (
                    <div key={owner.id} className="col-md-4 mb-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">{owner.firstName} {owner.lastName}</h5>
                                <p className="card-text">Email: {owner.email}</p>
                                <p className="card-text">Mobile: {owner.mobile}</p>
                                <p className="card-text">Status: {owner.status}</p> {/* Updated to display status received from the server */}
                                <button className="btn btn-success mr-2" onClick={() => updateOwnerStatus(owner.id)}>Approve</button>
                                <button className="btn btn-danger" onClick={() => deleteOwner(owner.id)}>Delete</button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="mt-4">
                <button className="btn btn-primary mr-2" onClick={prevPage} disabled={pageNumber === 0}>Previous</button>
                <button className="btn btn-primary" onClick={nextPage} disabled={owners.length < pageSize || pageNumber >= totalPages - 1}>Next</button>
            </div>
        </div>
    );
};

export default GetOwnersDetails;
