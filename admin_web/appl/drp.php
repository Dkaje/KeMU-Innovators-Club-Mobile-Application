<?php
include('../database/connection.php');
    $id = $_POST['id'];
    if (mysqli_query($con, "DELETE from cardy where id='$id'")) {
        $response["success"] = 1;
        $response["message"] = " SamrtCard deleted successfully";
    } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to delete";
        }
    echo json_encode($response);
    mysqli_close($con);