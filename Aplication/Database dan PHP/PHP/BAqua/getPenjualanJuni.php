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
$result = mysql_query("SELECT *FROM penjualan where bln='Juni'") or die(mysql_error());

// cek
if (mysql_num_rows($result) > 0) {
    // looping hasil
    // daftarbuku node
    $response["penjualan"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        $penjualan = array();
        
        $penjualan["nama_barang"] = $row["nama_barang"];
        $penjualan["harga_barang"] = $row["harga_barang"];
        $penjualan["qty"] = $row["qty"];
        $penjualan["total_harga"] = $row["total_harga"];
        $penjualan["nama"] = $row["nama"];
        $penjualan["jalan"] = $row["jalan"];
        $penjualan["bln"] = $row["bln"];
        // masukan daftarbuku pada $response
        array_push($response["penjualan"], $penjualan);
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
