<?php
include('../database/connection.php');
function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
    $mpesa = test_input($_POST["mpesa"]);
    $mv = $_POST['mv'];
    $ses = $_POST['ses'];
    $fund = $_POST['fund'];
    $term = $_POST['term'];
    $club_amt = $_POST['club_amt'];
    $utility_token = $_POST['utility_token'];
    $amount = $_POST['amount'];
    $club = $_POST['club'];
    $pat_id = $_POST['pat_id'];
    $pat_phone = $_POST['pat_phone'];
    $pat_name = $_POST['pat_name'];
    $entry_date = $_POST['entry_date'];
    $caps = "/^[A-Z]{10,}$/";
    $nums = "/^[0-9]{10,}$/";
    if (preg_match($caps, $mpesa)) {
        $response["success"] = 0;
        $response["message"] = "Failed!! Your MPESA is invalid";
    } elseif (preg_match($nums, $mpesa)) {
        $response["success"] = 0;
        $response["message"] = "Oops!! Such MPESA code is invalid";
    } else {
        if (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM contribution WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM payment WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM mpesa WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        }  else {
            $h=file('mpe.txt');
    $h[0]++;
    $em=fopen('mpe.txt',"w");
    fputs($em,"$h[0]");
    fclose($em);
    $iden=$h[0];
            if (mysqli_query($con, "INSERT INTO mpesa(pay,mv,ses,term,mpesa,club_amt,utility_token,amount,club,pat_id,pat_phone,pat_name,entry_date)
            VALUES('$iden','$mv','$ses','$term','$mpesa','$club_amt','$utility_token','$amount','$club','$pat_id','$pat_phone','$pat_name','$entry_date')")) {
        mysqli_query($con,"UPDATE mover set fund='$fund' where mv='$mv'");
        mysqli_query($con,"UPDATE account set withdraw=(withdraw+$amount),bal=(bal-$amount),last='$entry_date'");
                $response["success"] = 1;
                $response["message"] = "disbursement was successfully";
                }else{
                    $response["success"] = 0;
                $response["message"] = "Failed!! An error occurred"; 
                }  
        }
    }
    echo json_encode($response);
    mysqli_close($con);