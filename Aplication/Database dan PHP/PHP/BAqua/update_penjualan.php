<?php

/*
 * syntax untuk update form daftarbuku
 */

// array JSON response
$response = array();

if (isset($_POST['id'])) {
    
    $id = $_POST['id'];

    // include db connect
    require_once __DIR__ . '/db_connect.php';

    // konek ke db
    $db = new DB_CONNECT();

    // update daftarbuku by daftarbuku pid (pid)
    $result = mysql_query("UPDATE penjualan SET keterangan='KIRIM' WHERE id = '$id'");

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
