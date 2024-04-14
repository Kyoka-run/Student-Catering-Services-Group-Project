document.addEventListener('DOMContentLoaded', function() {
document.getElementById('register-form').addEventListener('submit', function(event) {
  event.preventDefault();

  event.stopPropagation();
   success = do_signup();
   if (success) {
    window.location.href="login.html";
   } else {
    alert("Invalid user input type")
   }
  });
});
 function do_signup() {
  var username = document.getElementById('username').value;
  var email = document.getElementById('email').value;
  var password = document.getElementById('password').value;
  let result = false;
  if (username != "" && password != "") {
   $.ajax({
    type: 'post',
    async: false,
    cache: false,
    url: '/user_register',
    xhrFields: {
     withCredentials: true
    },
    headers: {
     'Accept': 'application/json, text/plain, */*',
     'Content-Type':'application/json',
     
    },
    data: JSON.stringify({
     username: username,
     email:email,
     password: password
    }),
    crossOrigin: true,
    
    success: function(data){

    },
     error: function (data) {
      result = false;
     }
   });
  }
  return result;
 }
