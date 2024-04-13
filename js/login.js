// Adding an event listener for the login form submission
document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the form from submitting normally

    // Collecting username and password from the form
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // Simple authentication check
    if (username === "admin" && password === "password") {
        // If login is successful, redirect to a dashboard page
        window.location.href = "dash.html";
    } else {
        // If login fails, display an error message on the page
        var errorMessage = document.getElementById('error-message');
        errorMessage.textContent = "Nom d'utilisateur ou mot de passe incorrect.";
    }
});
