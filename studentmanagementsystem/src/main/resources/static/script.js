const API="http://localhost:8080/students";

loadStudents();

function loadStudents(){

fetch(API)

.then(res=>res.json())

.then(data=>{

let rows="";

data.forEach(student=>{

rows+=`

<tr>

<td>${student.id}</td>

<td>${student.name}</td>

<td>${student.email}</td>

<td>${student.department}</td>

<td>

<button onclick="deleteStudent(${student.id})">Delete</button>

</td>

</tr>

`;

});

document.getElementById("students").innerHTML=rows;

});

}

function addStudent(){

const student={

name:document.getElementById("name").value,

email:document.getElementById("email").value,

department:document.getElementById("department").value

};

fetch(API,{

method:"POST",

headers:{

"Content-Type":"application/json"

},

body:JSON.stringify(student)

})

.then(()=>{

loadStudents();

});

}

function deleteStudent(id){

fetch(API+"/"+id,{

method:"DELETE"

})

.then(()=>{

loadStudents();

});

}