<?php
include('../database/connection.php');
    $term = $_POST['term'];
    $ses = $_POST['ses'];
    $theme=$_POST['theme'];
    $venue=$_POST['venue'];
    $land=$_POST['land'];
    $site=$_POST['site'];
    $member=$_POST['member'];
    $money=$_POST['money'];
    $date=$_POST['date'];
    $time=$_POST['time'];
    $entry_date=$_POST['entry_date'];

    if (mysqli_num_rows(mysqli_query($con, "SELECT * from event where theme='$theme' and venue='$venue' and land='$land' and site='$site' and date='$date'")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! We have a similar event, same venue same date";
    }elseif (mysqli_query($con,"INSERT INTO event(term,ses,date,time,entry_date,theme,venue,status,site,land,member,money,opened)
        VALUES('$term','$ses','$date','$time','$entry_date','$theme','$venue',1,'$site','$land','$member','$money','$member')")) {
            $response["success"] = 1;
            $response["message"] = "event added successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to register event";
        }
    echo json_encode($response);
    mysqli_close($con);