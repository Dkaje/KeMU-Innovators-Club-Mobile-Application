<?php
include('../database/connection.php');
    $term = $_POST['term'];
    $ses = $_POST['ses'];
    $notice=$_POST['notice'];
    $subject=$_POST['subject'];
    $entry_date=$_POST['entry_date'];

    if (mysqli_num_rows(mysqli_query($con, "SELECT * from announce where subject='$subject' and notice='$notice' and ses='$ses'")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! We have a similar announcement, same venue same date";
    }elseif (mysqli_query($con,"INSERT INTO announce(term,ses,subject,notice,entry_date)
        VALUES('$term','$ses','$subject','$notice','$entry_date')")) {
            $response["success"] = 1;
            $response["message"] = "announcement added successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to register";
        }
    echo json_encode($response);
    mysqli_close($con);