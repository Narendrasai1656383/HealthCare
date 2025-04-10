document.addEventListener("DOMContentLoaded", function () {
    fetchappointments();

    const isActive = localStorage.getItem("isActive");
    const logoutBtn = document.getElementById("logout");

    if (logoutBtn && isActive !== "yes") {
		alert("Need to login");
				window.location.replace("login.html");
        logoutBtn.style.setProperty("display", "none", "important");
        console.log("Logout hidden because isActive is not 'yes'");
    }

});

function fetchappointments() {
    fetch("http://localhost:8080/appointments/all")
        .then(response => response.json())
        .then(appointments => {
            displayappointments(appointments);
        })
        .catch(error => console.error("Error fetching Health Records:", error));
}

function displayappointments(appointments) {
    const AppointmentsList = document.getElementById("AppointmentsList");
    if (!AppointmentsList) {
        console.error("AppointmentsList element not found in the DOM");
        return;
    }
    AppointmentsList.innerHTML = "";

    appointments.forEach(appointment => {
        const appointmentCard = document.createElement("div");
        appointmentCard.classList.add("record-card");

            appointmentCard.innerHTML = `
                <p>ID: ${appointment.appointmentId}</p>
                <p>BookedDate: ${appointment.date ? new Date(appointment.date).toLocaleString() : "N/A"}</p>
                <p>TimeSlot: ${appointment.timeSlot}</p>
                <p>Status: ${appointment.status}</p>
                <button onclick="accept(${appointment.appointmentId}, 'Booked')">Accept Appointment</button>
            `;

        AppointmentsList.appendChild(appointmentCard);
    });
}

function accept(id,status){
	fetch(`http://localhost:8080/appointments/${id}/${status}`, {
	        method: "PUT",
	    })
        .then(response => response.json())
        .then(appointments => {
            fetchappointments();
        })
        .catch(error => console.error("Error accepting appointment:", error));
}

