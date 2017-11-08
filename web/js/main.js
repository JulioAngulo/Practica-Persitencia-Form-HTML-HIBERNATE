/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function senddata2Back()
{
    var nombre=document.getElementById("nombre").value;
    var paterno=document.getElementById("paterno").value;
    var materno=document.getElementById("materno").value;
    var telefono=document.getElementById("telefono").value;
    var tipo=document.getElementById("tipo").value;
    
    document.getElementById("loading").style.display="block";
    
    $.post("Servlet",
    {
        nombre:nombre,
        paterno:paterno,
        materno:materno,
        telefono:telefono,
        tipo:tipo
    }
    ,function(data)
    {
        document.getElementById("loading").style.display="none";
        console.log("Hey tu data");
        console.log(data);


        if(data.resultado===true)
        {
            window.alert("Hey funciono");
        }
    });
}

/*<div id='loading' style='display: none'><img src="U2_gif.gif" title="Loading" /></div>

//$.post(el servlet que se llamará por medio del post,los datos que enviaré en formato JSON,una función que se va a ejecutar cuando el servlet responda);

<script language='javascript' type='text/javascript'>
//Before starting ajax request display the div (paste it in  the beginning of your ajax request function
var loadingdiv = document.getElementById('loading');
loadingdiv.style.display = "block";

//execute the code below after the ajax request is complete
var loadingdiv = document.getElementById('loading');
loadingdiv.style.display = "none";
</script>


<script type="text/javascript">
  alert("Un mensaje de prueba");
</script>
*/