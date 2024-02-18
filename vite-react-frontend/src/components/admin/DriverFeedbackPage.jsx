
import React, { useState, useEffect } from 'react';
import adminService from '../../services/admin.service';
import { useParams, Link } from 'react-router-dom';
import './css/DriverFeedbackPage.css'; // Import CSS file for styling

function DriverFeedbackPage() {
  const { id } = useParams();
  const [driverName, setDriverName] = useState('');
  const [feedbacks, setFeedbacks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchFeedbacks = async () => {
      try {
        const response = await adminService.getFeedback(id);
        console.log(response); // Check response from API
        const feedbackData = response.data;
        if (feedbackData.length > 0) {
          const firstFeedback = feedbackData[0];
          setDriverName(`${firstFeedback.firstName} ${firstFeedback.lastName}`);
          setFeedbacks(feedbackData);
        } else {
          setError("No feedbacks available for this driver.");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
        setError("Error fetching data. Please try again later.");
      } finally {
        setLoading(false);
      }
    };
  
    fetchFeedbacks(); // Fetch feedbacks on component mount
  }, [id]);

  return (
    <div className="driver-feedback-container">
      <h2 className="feedback-title">Feedbacks for {driverName}</h2>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>{error}</p>
      ) : (
        <div className="feedback-list-container">
          <table className="feedback-list">
            <thead>
              <tr>
                <th>ID</th>
                <th>Feedback</th>
                <th>Rating</th>
              </tr>
            </thead>
            <tbody>
              {feedbacks.map((feedback, index) => (
                <tr key={index}>
                  <td>{feedback.id}</td>
                  <td>{feedback.feedback}</td>
                  <td>{feedback.rating}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
      <Link to="/allDriverList">Back to Driver Details</Link>
    </div>
  );
}

export default DriverFeedbackPage;
