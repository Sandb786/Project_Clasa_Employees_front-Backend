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

function  change()
{
    
    let data=document.getElementById("cat").value
   document.getElementById("lab").classList.remove("deactive")


    switch (data) 
    {
        case "NONE":
   document.getElementById("frontend").classList.add("deactive")
   document.getElementById("backend").classList.add("deactive")
   document.getElementById("lab").classList.add("deactive")
            break;

        case "FOR1":
   document.getElementById("frontend").classList.remove("deactive")
   document.getElementById("backend").classList.add("deactive")
            break;

        case "BACK1":
   document.getElementById("backend").classList.remove("deactive")
   document.getElementById("frontend").classList.add("deactive")
            break;
    
    }
}

function newpro()
{
    alert("This feture is coming soon....")
}

