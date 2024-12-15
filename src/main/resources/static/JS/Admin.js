function demo()
{
    console.log("Demo called.....")

    document.getElementById("Application_type").classList.add("deactive")

    document.getElementById("total_Application").classList.remove("deactive")

    document.getElementById("back_btn").classList.remove("deactive")
    document.getElementById("home_btn").classList.add("deactive")

    const cs=document.getElementById("txt1").innerHTML
    let sum="Total "+cs
    document.getElementById("txt1").innerHTML=sum
}

function demo1()
{
    console.log("Demo1 called.....")

    document.getElementById("Application_type").classList.add("deactive")

    document.getElementById("Acpt_appli").classList.remove("deactive")
   
    document.getElementById("back_btn").classList.remove("deactive")
    document.getElementById("home_btn").classList.add("deactive")

    const cs=document.getElementById("txt1").innerHTML
    let sum="Accepted "+cs
    document.getElementById("txt1").innerHTML=sum
    
}
function demo2()
{
    document.getElementById("Application_type").classList.add("deactive")
    document.getElementById("give_pro").classList.remove("deactive")

    document.getElementById("back_btn").classList.remove("deactive")
    document.getElementById("home_btn").classList.add("deactive")

    
}
function back2() 
{
    document.getElementById("home_btn").classList.remove("deactive")
    document.getElementById("Application_type").classList.remove("deactive")
    document.getElementById("give_pro").classList.add("deactive")
    document.getElementById("back_btn").classList.add("deactive")



    
}

function back() 
{
    console.log("Button claaed.....")

    
   document.getElementById("total_Application").classList.add("deactive")
   document.getElementById("Acpt_appli").classList.add("deactive")

   document.getElementById("Application_type").classList.remove("deactive")

   document.getElementById("back_btn").classList.add("deactive")

   
   document.getElementById("home_btn").classList.remove("deactive")

   document.getElementById("txt1").innerHTML="Applications"

}

function home() 
{
    console.log("home......")
   document.getElementById("home_btn").classList.remove("deactive")
    location.href="/Admin_index";

}


/*********************************EMP Detail JS **********************************/

function activeEditDetail(param)
{
    document.getElementById("Info").classList.add("deactive");
    document.getElementById("Edit_info").classList.remove("deactive");


    switch (param) 
    {
        case "Personal":

            document.getElementById("Personal_Details").classList.remove("deactive");
            document.getElementById("Office_Details").classList.add("deactive");
            addStates()
           
            break;
    
        case "Office":
            document.getElementById("Office_Details").classList.remove("deactive");
            document.getElementById("Personal_Details").classList.add("deactive");
            break;

        case "Back":
            document.getElementById("Info").classList.remove("deactive");
            document.getElementById("Edit_info").classList.add("deactive");
            break;
    }
    
}

function newpro()
{
    alert("This feture is coming soon....")
}

function addStates()
{
    const States = [
        "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", 
        "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", 
        "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
        "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", 
        "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", 
        "Uttar Pradesh", "Uttarakhand", "West Bengal", 
        "Andaman and Nicobar Islands", "Chandigarh", "Delhi", 
        "Jammu and Kashmir", "Ladakh", "Lakshadweep", "Puducherry"
    ];
    
    States.forEach(state => 
        {
            document.getElementById('state').innerHTML += `<option value="${state}">${state}</option>`;

    });
}

function changeTechnology()
{
    const value = document.getElementById("techType").value;
    console.log(value);

    document.getElementById("Tech_B").classList.add("deactive")
    document.getElementById("Tech_F").classList.add("deactive")
    document.getElementById("user_tech").classList.add("deactive")



    

    switch (value) 
    {
        case 'Frontend':
           document.getElementById("Tech_B").classList.add("deactive")
           document.getElementById("Tech_F").classList.remove("deactive")
            break;

        case 'Backend':
            document.getElementById("Tech_F").classList.add("deactive")
           document.getElementById("Tech_B").classList.remove("deactive")
            break;

        default :
            document.getElementById("user_tech").classList.remove("deactive")
        break;
    }
}

function chaneRole() 
{
    document.getElementById("role_jd").classList.add("deactive")
}