if(localStorage.getItem('email') == null){
    location.href="http://localhost:8080/";
}
else{
    var email=localStorage.getItem('email');
    var status=document.getElementById('status');
    fetch('http://localhost:8080/emp/getprofile',{
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email : email
        })
        }).then(response =>{
            if(response.status !=200){
                data.status="Failed to fetch data, Please try again !";
            }
            return response.json();
        }).then(data =>{
            var profile=document.getElementById('profile');
            profile.innerHTML=`
            <tr><td>Id</td><td>${data.id}</td>
            <tr><td>Name</td><td>${data.name}</td>
            <tr><td>Mail Id</td><td>${data.email}</td>
            <tr><td>Salary</td><td>${data.salary}</td>
            `;
        }).catch((err)=>{
            data.status="Failed to fetch data, Please try again !";
        })
}