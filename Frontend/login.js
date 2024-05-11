
function loginUser(email, password) {
   
    var loginData = {
        email: email,
        password: password
    };

    
    fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData),
        credentials: "include"
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/index.html";
        } else {
            
            throw new Error('Błąd podczas logowania:', response.statusText);
        }
    })
    .catch(error => {
        
        console.error('Błąd:', error);
    });
}


document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); 

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    loginUser(email, password);
});

