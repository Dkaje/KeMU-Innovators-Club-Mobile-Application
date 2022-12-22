<?php
include('../database/connection.php');
    $notice=$_POST['notice'];
    $subject=$_POST['subject'];
    $ann=$_POST['ann'];
    if (mysqli_query($con,"UPDATE announce set subject='$subject',notice='$notice' where ann='$ann'")) {
            $response["success"] = 1;
            $response["message"] = "announcement Update was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to update announcement";
        }
    echo json_encode($response);
    mysqli_close($con);