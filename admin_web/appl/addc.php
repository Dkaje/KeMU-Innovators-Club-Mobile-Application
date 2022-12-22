<?php
include('../database/connection.php');
    $term = $_POST['term'];
    $ses = $_POST['ses'];
    $code=$_POST['code'];
    $credits=$_POST['credits'];
    $title=$_POST['title'];
    $department=$_POST['department'];
    $entry_date=$_POST['entry_date'];
    $quote=$_POST['quote'];

    if (mysqli_num_rows(mysqli_query($con, "SELECT * from course where code='$code' and term='$term'")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! You cannot overwrite an existing course";
    }elseif (mysqli_query($con,"INSERT INTO course(term,ses,title,department,entry_date,code,credits,quote)
        VALUES('$term','$ses','$title','$department','$entry_date','$code','$credits','$quote')")) {
            $response["success"] = 1;
            $response["message"] = "course added successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to register course";
        }
    echo json_encode($response);
    mysqli_close($con);