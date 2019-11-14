<?php
$server = "localhost";
$user = "root";
$pass = "clave";
$bd = "BDRafiki";
//Creamos la conexión
$conexion = mysqli_connect($server, $user, $pass,$bd)
or die("Ha sucedido un error inexperado en la conexion de la base de datos");
//generamos la consulta
$sql = "SELECT * FROM COCHES";
mysqli_set_charset($conexion, "utf8"); //formato de datos utf8
if(!$result = mysqli_query($conexion, $sql)) die();
$coches = array(); //creamos un array
while($row = mysqli_fetch_array($result))
{
    $matricula=$row['matricula'];
    $marca=$row['marca'];
    $modelo=$row['modelo'];
    $combustible=$row['combustible'];
    $precio=$row['precio'];
    $imagen=$row['imagen'};
    $coches[] = array('matricula'=> $matricula, 'marca'=> $marca, 'modelo'=> $modelo, 'combustible' => $combustible, 'precio'=> $precio, 'imagen'=> $imagen);
}
//desconectamos la base de datos
$close = mysqli_close($conexion)
or die("Ha sucedido un error inexperado en la desconexion de la base de datos");
//Creamos el JSON
$json_string = json_encode($coche);
echo " { Coches: ".$json_string." }";
//Si queremos crear un archivo json, sería de esta forma:
/*
$file = 'coches.json';
file_put_contents($file, $json_string);
*/
?>