document.getElementById('admin-login').addEventListener('submit', function(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    if (username === "admin" && password === "admin123") {
      // login successful
      window.location.href = "admin_dashboard.html";
    } else {
      // Error message
      var errorMessage = document.getElementById('error-message');
      errorMessage.textContent = "Nom d'utilisateur ou mot de passe incorrect.";
    }
  });
