<?php
include('../database/connection.php');
    $term = $_POST['term'];
    $year = $_POST['year'];
    $report=$_POST['report'];
    $ending=$_POST['ending'];
    $entry_date=$_POST['entry_date'];
    $ses=$_POST['ses'];
    $user_id=$_POST['user_id'];
    $username=$_POST['username'];
    $userphone=$_POST['userphone'];
    $status=$_POST['status'];
    if (mysqli_num_rows(mysqli_query($con, "SELECT * from report where user_id='$user_id' and term='$term' and year='$year' and status=1 and ended=0")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Hi there! how many times do you want to report the above session??";
    }elseif (mysqli_num_rows(mysqli_query($con, "SELECT * from report where user_id='$user_id' and term='$term' and year='$year' and status=1 and ended=1")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Hi there! The session was ended.";
    }elseif (mysqli_query($con,"INSERT INTO report(term,year,status,entry_date,report,ending,user_id,username,userphone,ses)
        VALUES('$term','$year',1,'$entry_date','$report','$ending','$user_id','$username','$userphone','$ses')")) {
            $response["success"] = 1;
            $response["message"] = "Session Reported successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to report session";
        }
    echo json_encode($response);
    mysqli_close($con);