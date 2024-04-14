
document.addEventListener('DOMContentLoaded', function() {
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    event.stopPropagation();
     success = do_login();
     if (success) {
      window.location.href="dashboard.html";
     } else {
      alert("Invalid usernam/password")
     }
    });
  });
   
   function do_login() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    let result = false;
    if (username != "" && password != "") {
     $.ajax({
      type: 'post',
      async: false,
      cache: false,
      url: '/user_login',
      xhrFields: {
       withCredentials: true
      },
      headers: {
       'Accept': 'application/json, text/plain, */*',
       'Content-Type':'application/json',
       
      },
      data: JSON.stringify({
       username: username,
       password: password
      }),
      crossOrigin: true,
      
      success: function(data){
       localStorage.setItem('token-info', JSON.stringify(data));
       result = true;
      },
       error: function (data) {
        result = false;
       }
     });
    }
    return result;
   }
   