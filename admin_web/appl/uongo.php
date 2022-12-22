<?php
include('../database/connection.php');
    $evt=$_POST['evt'];
    if (mysqli_query($con,"DELETE from event where evt='$evt'")) {
            $response["success"] = 1;
            $response["message"] = "event delete was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to delete event";
        }
    echo json_encode($response);
    mysqli_close($con);