<?php
include('../database/connection.php');
    $term = $_POST['term'];
    $ses = $_POST['ses'];
    $theme=$_POST['theme'];
    $venue=$_POST['venue'];
    $land=$_POST['land'];
    $site=$_POST['site'];
    $reg_no=$_POST['reg_no'];
    $fullname=$_POST['fullname'];
    $phone=$_POST['phone'];
    $money=$_POST['money'];
    $date=$_POST['date'];
    $time=$_POST['time'];
    $evt=$_POST['evt'];
    $entry_date=$_POST['entry_date']; 

    if (mysqli_num_rows(mysqli_query($con, "SELECT * from summer where reg_no='$reg_no' and evt='$evt' and status!=2")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! Hi $fullname, How many times do you want to register for this event?";
    }elseif (mysqli_query($con,"INSERT INTO summer(term,ses,date,time,entry_date,theme,venue,site,land,evt,money,reg_no,fullname,phone)
        VALUES('$term','$ses','$date','$time','$entry_date','$theme','$venue','$site','$land','$evt','$money','$reg_no','$fullname','$phone')")) {
        mysqli_query($con,"UPDATE event set member=(member-1) where evt='$evt'");
            $response["success"] = 1;
            $response["message"] = "You have registered successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to register for the event";
        }
    echo json_encode($response);
    mysqli_close($con);