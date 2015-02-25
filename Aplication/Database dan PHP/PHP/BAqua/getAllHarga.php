<?php

/*
 * kode untuk tampilak semua produk, pada halaman home
 */

$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// ckonekin ke db
$db = new DB_CONNECT();

//  get by daftarbuku
$result = mysql_query("SELECT *FROM list_aqua") or die(mysql_error());

// cek
if (mysql_num_rows($result) > 0) {
    // looping hasil
    // daftarbuku node
    $response["list_aqua"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        $list_aqua = array();
        $list_aqua["id"] = $row["id"];
        $list_aqua["nama_barang"] = $row["nama_barang"];
        $list_aqua["harga_barang"] = $row["harga_barang"];
        $list_aqua["stock_barang"] = $row["stock_barang"];
        $list_aqua["stock"] = $row["stock"];


        // masukan daftarbuku pada $response
        array_push($response["list_aqua"], $list_aqua);
    }
    // sukses
    $response["success"] = 1;

    // echo JSON response
    echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "Tidak ada data yang ditemukan";

    echo json_encode($response);
}
?>
