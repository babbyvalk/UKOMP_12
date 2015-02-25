<?php

/*
 * kode untuk single daftarbuku
 */

$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// konekin ke db
$db = new DB_CONNECT();

// cek data
if (isset($_GET["id"])) {
    $id = $_GET['id'];

    $result = mysql_query("SELECT *FROM penjualan WHERE id = $id");

    if (!empty($result)) {
        // cek kesediaan data
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $penjualan = array();
            $penjualan["id"] = $result["id"];
            $penjualan["nama_barang"] = $result["nama_barang"];
            $penjualan["harga_barang"] = $result["harga_barang"];
            $penjualan["qty"] = $result["qty"];
            $penjualan["total_harga"] = $result["total_harga"];
            $penjualan["nama"] = $result["nama"];
            $penjualan["jalan"] = $result["jalan"];
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
} else {
    $response["success"] = 0;
    $response["message"] = "Silahkan lengkapi permintaan anda";

    echo json_encode($response);
}
?>
