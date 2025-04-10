document.addEventListener("DOMContentLoaded", function () {
    appointmentviwer();

    const isActive = localStorage.getItem("isActive");
    const logoutBtn = document.getElementById("logout");

    if (logoutBtn && isActive !== "yes") {
		alert("Need to login");
				window.location.replace("login.html");
        logoutBtn.style.setProperty("display", "none", "important");
        console.log("Logout hidden because isActive is not 'yes'");
    }
	
});

function appointmentviwer() {
    fetch("http://localhost:8080/appointments/all")
        .then(response => response.json())
        .then(appointments => {
            displayappointments(appointments);
        })
        .catch(error => console.error("Error fetching booked appointments:", error));
}

function displayappointments(appointments) {
    const appointmentsList = document.getElementById("appointmentsList");
    appointmentsList.innerHTML = "";
    appointments.forEach(appointment => {
        const appointmentCard = document.createElement("div");
        appointmentCard.classList.add("doctor-card");
        appointmentCard.innerHTML = `
					<p>ID: ${appointment.appointmentId}</p>
					<p>BookedDate: ${appointment.date ? new Date(appointment.date).toLocaleString() : "N/A"}</p>
					<p>TimeSlot: ${appointment.timeSlot}</p>
		            <p>Status: ${appointment.status}</p>
        `;
        appointmentsList.appendChild(appointmentCard);
    });
}
