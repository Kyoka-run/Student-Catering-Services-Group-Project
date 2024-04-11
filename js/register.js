document.getElementById('register-form').addEventListener('submit', function(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    // just to check
    console.log("Nom d'utilisateur:", username);
    console.log("Adresse e-mail:", email);
    console.log("Mot de passe:", password);
    // delay to see the console log
    setTimeout(function() {
        window.location.href = "dash.html";
      }, 1000);
  });
