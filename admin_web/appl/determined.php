<?php
include('../database/connection.php');
    $evt=$_POST['evt'];
    $ses = $_POST['ses'];
    $term = $_POST['term'];
    $theme=$_POST['theme'];
    $venue=$_POST['venue'];
    $date=$_POST['date'];
    $club=$_POST['club'];
    $org_no=$_POST['org_no'];
    $org_phone=$_POST['org_phone'];
    $org_name=$_POST['org_name'];
    $pat_id=$_POST['pat_id'];
    $pat_phone=$_POST['pat_phone'];
    $pat_name=$_POST['pat_name'];
    $members=$_POST['members'];
    $money=$_POST['money'];
    $summ=$_POST['summ'];
    $entry_date=$_POST['entry_date'];
    $venu=$_POST['venu'];

    $pup=mysqli_query($con, "SELECT * FROM summer where theme='$theme' and venue='$venu' and approval=1 and org=1 and pat=0 and closure=0");
    if (mysqli_num_rows($pup) > 0) {
        while ($row = mysqli_fetch_array($pup)) {
            $reg_no = $row['reg_no'];
            $fullname = $row['fullname'];
            $phone = $row['phone'];
            mysqli_query($con,"INSERT INTO walk(term,ses,date,pat_name,entry_date,theme,venue,pat_id,pat_phone,evt,reg_no,fullname,phone,club,org_no,org_phone,org_name)
        VALUES('$term','$ses','$date','$pat_name','$entry_date','$theme','$venue','$pat_id','$pat_phone','$evt','$reg_no','$fullname','$phone','$club','$org_no','$org_phone','$org_name')");
            mysqli_query($con,"INSERT INTO mover(term,ses,date,pat_name,entry_date,theme,venue,pat_id,pat_phone,evt,money,summ,members,club)
        VALUES('$term','$ses','$date','$pat_name','$entry_date','$theme','$venue','$pat_id','$pat_phone','$evt','$money','$summ','$members','$club')");
        }
    }
    if (mysqli_query($con,"UPDATE summer set pat=1 where theme='$theme' and venue='$venu' and approval=1 and org=1 and pat=0 and closure=0")) {
        $response["demo"] = 1;
        $response["message"] = "Submitted successfully";
    } else {
        $response["demo"] = 0;
        $response["message"] = "  Failed to submit record";
    }
    echo json_encode($response);
    mysqli_close($con);