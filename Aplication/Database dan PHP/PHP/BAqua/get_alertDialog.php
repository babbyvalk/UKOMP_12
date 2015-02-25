<?php

/*
 * kode untuk single daftarbuku
 */

$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// konekin ke db
$db = new DB_CONNECT();

    $result = mysql_query("select keterangan from penjualan where id='15'");

    if (!empty($result)) {
        // cek kesediaan data
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $penjualan = array();
            //$penjualan["id"] = $result["id"];
            $penjualan["keterangan"] = $result["keterangan"];
            // maka
            $response["success"] = 1;

            // node
            $response["penjualan"] = array();

            array_push($response["penjualan"], $penjualan);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // jika kosong
            $response["success"] = 0;
            $response["message"] = "Tidak ada data";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        $response["success"] = 0;
        $response["message"] = "Tidak ada data";

        echo json_encode($response);
    }
?>
