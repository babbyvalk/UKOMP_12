<?php

// array for JSON response
$response = array();

// include db connect class
require_once 'db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["bln"])) {
$bln = $_GET['bln'];

// get a product from products table
$result = mysql_query("SELECT COUNT(bln) FROM penjualan WHERE bln = 'Februari'");


$result = mysql_fetch_array($result);
 $response["success"] = 1;
 $response["voto"] = $result["count"];
 echo json_encode($response);    } 
?>