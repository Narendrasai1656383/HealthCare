document.addEventListener("DOMContentLoaded", function () {
    fetchHealthRecords();

    const isActive = localStorage.getItem("isActive");
    const logoutBtn = document.getElementById("logout");

    if (logoutBtn && isActive !== "yes") {
        logoutBtn.style.setProperty("display", "none", "important");
        console.log("Logout hidden because isActive is not 'yes'");
    }

    const addhealthrecordForm = document.getElementById("addhealthrecordForm"); 
    if (addhealthrecordForm) {
        addhealthrecordForm.addEventListener("submit", (e) => {
            e.preventDefault();

            const diagnosis = document.getElementById("diagnosis").value;
            const prescription = document.getElementById("prescription").value;

            fetch("http://localhost:8080/healthrecord/upload", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ diagnosis, prescription })
            })
                .then(response => {
                    if (!response.ok) {
                        return response.json().then(err => Promise.reject(err));
                    }
                    return response.json();
                })
                .then(result => {
                    alert("added succesfully successfully");
                    window.location.replace("userprofile.html");
                })
                .catch(error => {
                    console.error("Registration error:", error);
                    alert(error.message || "Something went wrong");
                });
        });
    }
	fetchHealthRecords();
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
