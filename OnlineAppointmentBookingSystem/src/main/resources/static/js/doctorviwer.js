document.addEventListener("DOMContentLoaded", function () {
    doctorviwer();

    const isActive = localStorage.getItem("isActive");
    const logoutBtn = document.getElementById("logout");

    if (logoutBtn && isActive !== "yes") {
		alert("Need to login");
				window.location.replace("login.html");
        logoutBtn.style.setProperty("display", "none", "important");
        console.log("Logout hidden because isActive is not 'yes'");
    }
	
});

function doctorviwer() {
    fetch("http://localhost:8080/users/doctors")
        .then(response => response.json())
        .then(doctors => {
            displaydoctors(doctors);
        })
        .catch(error => console.error("Error fetching doctors:", error));
}

function displaydoctors(doctors) {
    const doctorsList = document.getElementById("doctorsList");
    doctorsList.innerHTML = "";
    doctors.forEach(doctor => {
        const doctorCard = document.createElement("div");
        doctorCard.classList.add("doctor-card");
        doctorCard.innerHTML = `
					<p>ID: ${doctor.id}</p>
					<p>Name: ${doctor.name}</p>
		            <p>Specalist: ${doctor.role}</p>
		            <p>Gender: ${doctor.gender}</p>
					<p>Mobile number: ${doctor.phone}</p>
					<button onclick="bookappointment(${doctor.id}, '${"10-11"}')">Book Appointment</button>
        `;
        doctorsList.appendChild(doctorCard);
    });
}
function bookappointment(doctorId,timeSlot){
	fetch("http://localhost:8080/appointments/book", {
	                method: "POST",
	                headers: { "Content-Type": "application/json" },
	                body: JSON.stringify({ doctorId, timeSlot })
	            })
	                .then(response => {
	                    if (!response.ok) {
	                        return response.json().then(err => Promise.reject(err));
	                    }
	                    return response.json();
	                })
	                .then(result => {
	                    alert("Appointment booked successfully");
	                    window.location.replace("doctorviewr.html");
	                })
	                .catch(error => {
	                    console.error("Booking error:", error);
	                    alert(error.message || "Something went wrong");
	                });
}