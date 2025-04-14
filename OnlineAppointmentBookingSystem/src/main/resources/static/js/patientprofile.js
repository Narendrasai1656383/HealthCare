document.addEventListener("DOMContentLoaded", function () {

    const isActive = localStorage.getItem("isActive");
    const logoutBtn = document.getElementById("logout");

    if (logoutBtn && isActive !== "yes") {
		alert("Need to login");
				window.location.replace("login.html");
        logoutBtn.style.setProperty("display", "none", "important");
        console.log("Logout hidden because isActive is not 'yes'");
    }
	patientviwer();
});

function patientviwer() {
    const patientId = getQueryParam("data");

    if (!patientId) {
        console.error("No patient ID provided in URL.");
        alert("Missing patient ID. Returning to home.");
        window.location.href = "doctorprofile.html"; 
        return;
    }

    fetch(`http://localhost:8080/users/getPatientById/${patientId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Patient not found");
            }
            return response.json();
        })
        .then(patient => {
            displaypatient(patient);
        })
        .catch(error => {
            console.error("Error fetching patient details:", error);
            alert("Patient not found or server error.");
        });
}


function displaypatient(patient) {
    const patientContainer = document.getElementById("patient");
    patientContainer.innerHTML = "";

    const patientCard = document.createElement("div");
    patientCard.classList.add("patient-card");
    patientCard.innerHTML = `
        <p>ID: ${patient.id}</p>
        <p>Name: ${patient.name}</p>
        <p>Gender: ${patient.gender}</p>
        <p>Mobile number: ${patient.phone}</p>
    `;

    patientContainer.appendChild(patientCard);
}

function getQueryParam(data) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(data);
}