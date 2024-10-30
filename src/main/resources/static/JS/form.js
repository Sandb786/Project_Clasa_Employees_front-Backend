
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


/*************** Lodder ki Js by Chatgpt***************** */

function showLoading() 
{
    console.log("Lodder Function calling...")
    document.getElementById("loading").style.display = "flex";
}



const indianStates = [
    "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", 
    "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", 
    "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
    "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", 
    "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", 
    "Uttar Pradesh", "Uttarakhand", "West Bengal", 
    "Andaman and Nicobar Islands", "Chandigarh", "Delhi", 
    "Jammu and Kashmir", "Ladakh", "Lakshadweep", "Puducherry"
];

indianStates.forEach(state => {
    document.getElementById('stateSelect').innerHTML += `<option value="${state}">${state}</option>`;
});






