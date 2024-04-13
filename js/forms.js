// Adding an event listener to the form with id 'myForm' to handle its submission
document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the default form submission to handle it with JavaScript

    // Collecting form data
    var name = document.getElementById('name').value; // User's name
    var email = document.getElementById('email').value; // User's email
    var message = document.getElementById('message').value; // User's message

    // Display an alert with the collected information (for demonstration purposes)
    alert("Nom: " + name + "\nEmail: " + email + "\nMessage: " + message);
});
