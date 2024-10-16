
function chck()
{
    const value = document.getElementById("cat").value;
    console.log(value);

    document.getElementById("tech_part1").classList.add('act');
    document.getElementById("tech_part2").classList.add('act');

    document.getElementById("tech").classList.remove('active');
    document.getElementById("lab").classList.remove('active');

    switch (value) 
    {
        case 'Frontend':
            document.getElementById("tech").classList.add('active');
            document.getElementById("tech_part1").classList.remove('act');
            document.getElementById("lab").classList.add('active'); 
            break;

        case 'Backend':
            document.getElementById("tech").classList.add('active');
            document.getElementById("tech_part2").classList.remove('act');
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

