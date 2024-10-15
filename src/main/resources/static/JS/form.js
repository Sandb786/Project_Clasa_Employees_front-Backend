
function chck()
{
    const value = document.getElementById("cat").value;
    console.log(value);

    document.getElementById("frontend").classList.remove('active');
    document.getElementById("backend").classList.remove('active');
    document.getElementById("lab").classList.remove('active');

    switch (value) 
    {
        case 'Frontend':
            document.getElementById("frontend").classList.add('active');
            document.getElementById("lab").classList.add('active'); 
            break;

        case 'Backend':
            document.getElementById("backend").classList.add('active');
            document.getElementById("lab").classList.add('active');
            break;
    }
}

function otp()
{
   document.getElementById("otp").classList.remove("act");
   document.getElementById("btn").classList.add("act")
   document.getElementById("footer").classList.add("act")
   
}
