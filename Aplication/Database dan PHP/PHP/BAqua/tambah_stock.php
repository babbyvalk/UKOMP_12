<?php

/*
 * Buat daftarbuku baru
 */

$response = array();

// cek form
if (isset($_POST['nama_barang']) && isset($_POST['harga_barang']) && isset($_POST['stock_barang'])) {
    
    
    $nama_barang = $_POST['nama_barang'];
    $harga_barang = $_POST['harga_barang'];
    $stock_barang = $_POST['stock_barang'];

  

    // include db connect
    require_once __DIR__ . '/db_connect.php';

    // konekin db
    $db = new DB_CONNECT();

    // insert ke db
    $result = mysql_query("INSERT INTO list_aqua(nama_barang, harga_barang, stock_barang, stock) VALUES ('$nama_barang', '$harga_barang', '$stock_barang', '10 PCS')");

    // cek data udah masuk belumx`
    if ($result) {
        // kalo sukses
        $response["success"] = 1;
        $response["message"] = "pesanan anda berhasil";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // fkalo gagal
        $response["success"] = 0;
        $response["message"] = "Sistem mendeteksi kesalahan, silahkan coba lagi";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Silahkan lengkapi aksi sebelum memulai permintaan anda";

    // echoing JSON response
    echo json_encode($response);
}
?>
