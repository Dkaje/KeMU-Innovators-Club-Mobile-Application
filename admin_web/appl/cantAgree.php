<?php
include('../database/connection.php');
    $issue_date = $_POST['issue_date'];
    $expiry_date = $_POST['expiry_date'];
    $idi = $_POST['idi'];
    $secsta = $_POST['secsta'];
    $ses = $_POST['ses'];
    $reg_no = $_POST['reg_no'];
    $alrt = $_POST['alrt'];
    $entry_date = $_POST['entry_date'];
    if (mysqli_query($con, "UPDATE cardy_one set secsta='$secsta' WHERE idi='$idi'")) {
        mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$reg_no','$alrt','$entry_date')");
mysqli_query($con, "UPDATE issued set idi='$idi',ses='$ses',issue_date='$issue_date',expiry_date='$expiry_date',entry_date='$entry_date',status=1,ended=0,expiry=0 WHERE reg_no='$reg_no'");
            $response["success"] = 1;
            $response["message"] = "card renewal was successfully";
        } else {
                $response["success"] = 0;
                $response["message"] = "Failed to renew";
            } 
    echo json_encode($response);
    mysqli_close($con);