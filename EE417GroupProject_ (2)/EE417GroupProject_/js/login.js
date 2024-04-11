document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    if (username === "admin" && password === "password") {
      // login successful
      window.location.href = "dash.html";
    } else {
      // Error message
      var errorMessage = document.getElementById('error-message');
      errorMessage.textContent = "Nom d'utilisateur ou mot de passe incorrect.";
    }
});