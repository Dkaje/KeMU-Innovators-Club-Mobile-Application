<?php
include('../database/connection.php');
    $term = $_POST['term'];
    $year = $_POST['year'];
    $report=$_POST['report'];
    $ending=$_POST['ending'];
    $lec_id=$_POST['lec_id'];
    $lec_name=$_POST['lec_name'];
    $lec_phone=$_POST['lec_phone'];
    $entry_date=$_POST['entry_date'];
    if (mysqli_num_rows(mysqli_query($con, "SELECT * from session where term='$term' and year='$year' and ended=1")) > 0) {
        $response["success"] = 7;
        $response["message"] = "Operation Failed!! Why are you trying to REREGISTER a CLOSED session? Once a session is closed, kindly wait for it's maturity in the forthcoming year.";
    }elseif (mysqli_num_rows(mysqli_query($con, "SELECT * from session where status=1 and ended=0")) > 0) {
        $response["success"] = 7;
        $response["message"] = "Failed!! There is an active session. Close it first.";
    } elseif (mysqli_query($con,"INSERT INTO session(term,year,status,entry_date,report,ending,lec_id,lec_name,lec_phone)
        VALUES('$term','$year',1,'$entry_date','$report','$ending','$lec_id','$lec_name','$lec_phone')")) {
            $response["success"] = 1;
            $response["message"] = "Session added successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to register session";
        }
    echo json_encode($response);
    mysqli_close($con);