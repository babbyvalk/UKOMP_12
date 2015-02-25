<?php

/*
 * Buat daftarbuku baru
 */

$response = array();

// cek form
if (isset($_POST['nama_barang']) && isset($_POST['harga_barang']) && isset($_POST['qty']) && isset($_POST['total_harga']) && isset($_POST['nama']) && isset($_POST['jalan']) && isset($_POST['rt']) && isset($_POST['rw']) && isset($_POST['no_rumah']) && isset($_POST['tgl']) && isset($_POST['bln']) && isset($_POST['tahun'])) {
    
    
    $nama_barang = $_POST['nama_barang'];
    $harga_barang = $_POST['harga_barang'];
    $qty = $_POST['qty'];
    $total_harga = $_POST['total_harga'];
    $nama = $_POST['nama'];
    $jalan = $_POST['jalan'];
    $rt = $_POST['rt'];
    $rw = $_POST['rw'];
    $no_rumah = $_POST['no_rumah'];
    $tgl = $_POST['tgl'];
    $bln = $_POST['bln'];
    $tahun = $_POST['tahun'];
  

    // include db connect
    require_once __DIR__ . '/db_connect.php';

    // konekin db
    $db = new DB_CONNECT();

    // insert ke db
    $result = mysql_query("INSERT INTO penjualan(nama_barang, harga_barang, qty, total_harga, nama, jalan, rt, rw, no_rumah, tgl, bln, tahun, waktu, keterangan) VALUES ('$nama_barang', '$harga_barang', '$qty', '$total_harga', '$nama', '$jalan', '$rt', '$rw', '$no_rumah', '$tgl', '$bln', '$tahun', NOW(), 'PROSES')");

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
