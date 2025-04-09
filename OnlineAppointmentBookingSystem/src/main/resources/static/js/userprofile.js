document.addEventListener("DOMContentLoaded", function () {
    fetchHealthRecords();

    const isActive = localStorage.getItem("isActive");
    const logoutBtn = document.getElementById("logout");

    if (logoutBtn && isActive !== "yes") {
        logoutBtn.style.setProperty("display", "none", "important");
        console.log("Logout hidden because isActive is not 'yes'");
    }

});

function fetchHealthRecords() {
    fetch("http://localhost:8080/healthrecord/patient")
        .then(response => response.json())
        .then(records => {
            displayrecords(records);
        })
        .catch(error => console.error("Error fetching Health Records:", error));
}

function displayrecords(records) {
    const recordList = document.getElementById("recordList");
    recordList.innerHTML = "";
    records.forEach(record => {
        const recordCard = document.createElement("div");
        recordCard.classList.add("record-card");
        recordCard.innerHTML = `
					<p>ID: ${record.healthRecordId}</p>
		            <p>Diagnosis: ${record.diagnosis}</p>
		            <p>Prescription: ${record.prescription}</p>
		            <p>Date: ${record.date ? new Date(record.date).toLocaleString() : "N/A"}</p>       
        `;
        recordList.appendChild(recordCard);
    });
}
