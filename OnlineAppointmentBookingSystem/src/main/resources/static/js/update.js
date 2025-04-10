const API_BASE_URL = "http://localhost:8080/users";

document.addEventListener("DOMContentLoaded", () => {
	const isActive = localStorage.getItem("isActive");
	    const logoutBtn = document.getElementById("logout");

	    if (logoutBtn && isActive !== "yes") {
			alert("Need to login");
					window.location.replace("login.html");
	        logoutBtn.style.setProperty("display", "none", "important");
	        console.log("Logout hidden because isActive is not 'yes'");
	    }
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    if (loginForm) {
        loginForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            fetch(`${API_BASE_URL}/login`, {
                method: "POST",
                headers: { 
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email, password }),
                credentials: "include" 
            })
            .then(response => {
                if (!response.ok) {
                    return response.jsom().then(text => Promise.reject(text));
                }
                return response.json();
            })
            .then(data => {
                alert("Logged in successfully");
				localStorage.setItem("isActive","yes");
				if(data.role==="DOCTOR"){
					window.location.replace("doctorprofile.html");
				}
				else{
					window.location.replace("userprofile.html");
				}
            })
            .catch(error => {
                console.error("Login error:", error);
                alert(error); 
            });
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const name = document.getElementById("newUsername").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("newPassword").value;
			const role=document.getElementById("role").value;
			const phone=document.getElementById("phone").value;
			const gender=document.getElementById("gender").value;

            fetch(`${API_BASE_URL}/update`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, password ,role ,phone ,gender})
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(text => Promise.reject(text));
                }
                return response.json();
            })
            .then(result => {
                alert("Updated successfully"); 
				window.location.replace("login.html");
            })
            .catch(error => {
                console.error("Updation error:", error);
                alert(error);
            });
        });
    }
});
