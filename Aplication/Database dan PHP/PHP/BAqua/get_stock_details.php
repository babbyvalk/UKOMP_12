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

    $result = mysql_query("SELECT *FROM list_aqua WHERE id = $id");

    if (!empty($result)) {
        // cek kesediaan data
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $list_aqua = array();
            $list_aqua["id"] = $result["id"];
            $list_aqua["nama_barang"] = $result["nama_barang"];
            $list_aqua["harga_barang"] = $result["harga_barang"];
            $list_aqua["stock_barang"] = $result["stock_barang"];
            // maka
            $response["success"] = 1;

            // node
            $response["list_aqua"] = array();

            array_push($response["list_aqua"], $list_aqua);

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
