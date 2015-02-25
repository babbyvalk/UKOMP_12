<?php

/*
 * syntax untuk update form daftarbuku
 */

// array JSON response
$response = array();

if (isset($_POST['id']) && isset($_POST['nama_barang']) && isset($_POST['harga_barang']) && isset($_POST['stock_barang'])) {
    
    $id = $_POST['id'];
    $nama_barang = $_POST['nama_barang'];
    $harga_barang = $_POST['harga_barang'];
    $stock_barang = $_POST['stock_barang'];

    // include db connect
    require_once __DIR__ . '/db_connect.php';

    // konek ke db
    $db = new DB_CONNECT();

    // update daftarbuku by daftarbuku pid (pid)
    $result = mysql_query("UPDATE list_aqua SET nama_barang = '$nama_barang', harga_barang = '$harga_barang', stock_barang = '$stock_barang' WHERE id = $id");

    // cek data sudah masuk apa belum
    if ($result) {
        // kalo sukses
        $response["success"] = 1;
        $response["message"] = "Data anda berhasil di perbarui";
        
        // echo JSON response
        echo json_encode($response);
    } else {
        
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Mohon kelengkapan data anda";

    // echoJSON response
    echo json_encode($response);
}
?>
