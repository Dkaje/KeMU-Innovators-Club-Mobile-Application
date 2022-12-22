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
    $money = $_POST['money'];
    $fullname = $_POST['fullname'];
    $term = $_POST['term'];
    $ses = $_POST['ses'];
    $phone = $_POST['phone'];
    $reg_no = $_POST['reg_no'];
    $date = $_POST['date'];
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
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM contri WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        } else {
            $h=file('mpe.txt');
    $h[0]++;
    $em=fopen('mpe.txt',"w");
    fputs($em,"$h[0]");
    fclose($em);
    $iden=$h[0];
            if (mysqli_query($con, "INSERT INTO contri(pay,mpesa,money,reg_no,fullname,phone,date,term,ses)
            VALUES('$iden','$mpesa','$money','$reg_no','$fullname','$phone','$date','$term','$ses')")) {
$response["success"] = 1;
$response["message"] = "contribution was successful";
                }else{
                    $response["success"] = 0;
                $response["message"] = "Failed!! An error occurred"; 
                }  
        }
    }
    echo json_encode($response);
    mysqli_close($con);