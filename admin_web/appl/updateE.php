<?php
include('../database/connection.php');
$approval=$_POST['approval'];
$comment=$_POST['comment'];
$evt=$_POST['evt'];
    if (mysqli_query($con,"UPDATE event set approval='$approval',comment='$comment' where evt='$evt'")) {
            $response["success"] = 1;
            $response["message"] = "event Update was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to update event";
        }
    echo json_encode($response);
    mysqli_close($con);