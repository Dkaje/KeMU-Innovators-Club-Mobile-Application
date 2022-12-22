<?php
include('../database/connection.php');
    $pay = $_POST["pay"];
    $status = $_POST["status"];
    $event_pay = $_POST["fresh"];
    $reg_date = $_POST["reg_date"];
    $sm = $_POST["sm"];
    $user = $_POST["user"];
    $alrt = $_POST["alrt"];
        if($status==1){
            if (mysqli_query($con, "UPDATE contribution set status='$status' where pay='$pay'")) {
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$reg_date')");
                if(mysqli_num_rows(mysqli_query($con,"SELECT * from account"))>0){
                    if(mysqli_query($con,"UPDATE account set event_pay=(event_pay+$event_pay),whole=(whole+$event_pay),bal=(bal+$event_pay),last='$reg_date'")){
                        mysqli_query($con, "UPDATE summer set approval='$status' where sm='$sm'");
                        $response["success"] = 1;
                        $response["message"] = "Event payment Approved successfully";
                    }else{
                        $response["success"] = 0;
                        $response["message"] = "Failed to approve Event payment";
                    }
                }else{
                    if(mysqli_query($con,"INSERT into account(whole,event_pay,bal,entry,last)values('$event_pay','$event_pay','$event_pay','$reg_date','$reg_date')")){
                        mysqli_query($con, "UPDATE summer set approval='$status' where sm='$sm'");
                        $response["success"] = 1;
                        $response["message"] = "Event payment Approved successfully";
                    }else{
                        $response["success"] = 0;
                        $response["message"] = "Failed to approve Event payment";
                    }
                }
            }
        }else{
            if (mysqli_query($con, "UPDATE contribution set status='$status' where pay='$pay'")) {
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$reg_date')");
                mysqli_query($con, "UPDATE summer set approval='$status',pay=0 where sm='$sm'");
                $response["success"] = 1;
                $response["message"] = "contribuEvent paymenttion Rejected successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "Failed to reject Event payment";
            }
        }
    echo json_encode($response);
    mysqli_close($con);