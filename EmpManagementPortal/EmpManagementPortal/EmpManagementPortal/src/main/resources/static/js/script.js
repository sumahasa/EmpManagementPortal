if(localStorage.getItem('email')!=null){
    localStorage.removeItem('email');
    localStorage.removeItem('role');
}

var form=document.getElementById('form');
form.addEventListener('submit',(event)=>{
    event.preventDefault();
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var status=document.getElementById("status");

    fetch('http://localhost:8080/login',{
    method: 'POST',
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        email : email,
        password: password
    })
    }).then(response =>{
            return response.json();
    }).then(data =>{
            if(data.role == "admin"){
                   localStorage.setItem("email",email);
                   localStorage.setItem("role","admin");
                   location.href="http://localhost:8080/hr/addemp";
                    document.getElementById("email").value="";
                    document.getElementById("password").value="";
            }
            else if(data.role == "user"){
                    localStorage.setItem("email",email);
                    localStorage.setItem("role","emp");
                   location.href="http://localhost:8080/emp/profile";
                    document.getElementById("email").value="";
                    document.getElementById("password").value="";
            }
            else{
                status.innerHTML=data.status;
            }
    }).catch((err)=>{
        console.log(err);
    })
})