<?php
include('../database/connection.php');
    $code=$_POST['code'];
    $credits=$_POST['credits'];
    $title=$_POST['title'];
    $department=$_POST['department'];
    $quote=$_POST['quote'];
    $cs=$_POST['cs'];
    if (mysqli_query($con,"UPDATE course set title='$title',department='$department',quote='$quote',code='$code',credits='$credits' where cs='$cs'")) {
            $response["success"] = 1;
            $response["message"] = "course Update was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to update course";
        }
    echo json_encode($response);
    mysqli_close($con);