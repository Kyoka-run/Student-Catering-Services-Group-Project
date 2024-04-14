// Event listener for the admin login form submission
document.getElementById('admin-login').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the default form submission behavior

    // Retrieving user inputs from the form
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // Check if the entered credentials match the expected admin credentials
    if (username === "admin" && password === "admin123") {
        // If credentials are correct, redirect to the admin dashboard
        window.location.href = "admin_dashboard.html";
    } else {
        // If credentials are incorrect, display an error message
        var errorMessage = document.getElementById('error-message');
        errorMessage.textContent = "Nom d'utilisateur ou mot de passe incorrect.";
    }
});
