<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Workouts</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <nav>
        <a href="dashboard">Dashboard</a>
        <a href="workouts">Workouts</a>
        <a href="profile">Profile</a>
        <a href="logout">Logout</a>
    </nav>

    <div class="container">
        <h1>Manage Workouts</h1>

        <c:if test="${not empty sessionScope.aiSuggestion}">
            <div class="ai-suggestion">
                <strong>${sessionScope.aiSuggestion}</strong>
            </div>
            <%-- Remove the attribute so it doesn't show again on refresh --%>
            <c:remove var="aiSuggestion" scope="session" />
        </c:if>

        <div class="form-container">
            <h2>Add New Workout</h2>
            <form action="workouts" method="POST">
                <label>Activity Type:</label>
                <select name="activityType" required>
                    <option value="Running">Running</option>
                    <option value="Cycling">Cycling</option>
                    <option value="Walking">Walking</option>
                    <option value="Gym Workout">Gym Workout</option>
                </select>

                <label>Duration (mins):</label>
                <input type="number" name="durationMins" min="1" required>

                <label>Distance (km):</label>
                <input type="number" name="distanceKm" min="0" step="0.1" required>

                <label>Calories Burned:</label>
                <input type="number" name="caloriesBurned" min="1" required>

                <label>Date:</label>
                <input type="date" name="workoutDate" required>

                <label>Notes:</label>
                <input type="text" name="notes">

                <button type="submit" class="btn">Add Workout</button>
            </form>
        </div>

        <div class="content-box">
            <h2>Your Workout History</h2>
            <form action="workouts" method="GET" style="margin-bottom: 1rem;">
                <label for="type">Filter by Activity:</label>
                <select id="type" name="type">
                    <option value="">All</option>
                    <option value="Running">Running</option>
                    <option value="Cycling">Cycling</option>
                    <option value="Walking">Walking</option>
                    <option value="Gym Workout">Gym Workout</option>
                </select>

                <label for="date">Filter by Date:</label>
                <input type="date" id="date" name="date">

                <button type="submit" class="btn" style="width: auto;">Filter</button>
            </form>
            
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Type</th>
                        <th>Duration (mins)</th>
                        <th>Distance (km)</th>
                        <th>Calories</th>
                        <th>Notes</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="workout" items="${workoutList}">
                        <tr>
                            <td>${workout.workoutDate}</td>
                            <td>${workout.activityType}</td>
                            <td>${workout.durationMins}</td>
                            <td>${workout.distanceKm}</td>
                            <td>${workout.caloriesBurned}</td>
                            <td>${workout.notes}</td>
                            <td>
                                <a href="edit-workout?id=${workout.workoutId}">Edit</a>
                                <a href="workouts?action=delete&id=${workout.workoutId}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty workoutList}">
                        <tr>
                            <td colspan="7">You have not logged any workouts yet.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>