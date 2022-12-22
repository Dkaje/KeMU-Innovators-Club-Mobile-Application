<?php
include('../database/connection.php');
    $pay = $_POST["pay"];
    $status = $_POST["status"];
    $contribute = $_POST["fresh"];
    $reg_date = $_POST["reg_date"];
    $user = $_POST["user"];
    $alrt = $_POST["alrt"];
        if($status==1){
            if (mysqli_query($con, "UPDATE contri set status='$status' where pay='$pay'")) {
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$reg_date')");
                if(mysqli_num_rows(mysqli_query($con,"SELECT * from account"))>0){
                    if(mysqli_query($con,"UPDATE account set contribute=(contribute+$contribute),whole=(whole+$contribute),bal=(bal+$contribute),last='$reg_date'")){
                        $response["success"] = 1;
                        $response["message"] = "contribution Approved successfully";
                    }else{
                        $response["success"] = 0;
                        $response["message"] = "Failed to approve contribution";
                    }
                }else{
                    if(mysqli_query($con,"INSERT into account(whole,contribute,bal,entry,last)values('$contribute','$contribute','$contribute','$reg_date','$reg_date')")){
                        $response["success"] = 1;
                        $response["message"] = "contribution Approved successfully";
                    }else{
                        $response["success"] = 0;
                        $response["message"] = "Failed to approve contribution";
                    }
                }
            }
        }else{
            if (mysqli_query($con, "UPDATE contri set status='$status' where pay='$pay'")) {
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$reg_date')");
                $response["success"] = 1;
                $response["message"] = "contribution Rejected successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "Failed to reject contribution";
            }
        }
    echo json_encode($response);
    mysqli_close($con);