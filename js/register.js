// Event listener for handling the submission of the registration form
document.getElementById('register-form').addEventListener('submit', function(event) {
    event.preventDefault();  // Stops the form from submitting normally

    // Gathering the values entered in the form fields
    var username = document.getElementById('username').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    // Logging the information to console for verification
    console.log("Nom d'utilisateur:", username);
    console.log("Adresse e-mail:", email);
    console.log("Mot de passe:", password);

    // Redirecting to a dashboard page after a brief delay for seeing the log
    setTimeout(function() {
        window.location.href = "dash.html";
      }, 1000);
});
