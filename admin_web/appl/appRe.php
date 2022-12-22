<?php
include('../database/connection.php');
    $pay = $_POST["pay"];
    $status = $_POST["status"];
    $renewal = $_POST["renewal"];
    $reg_date = $_POST["reg_date"];
    $finsta = $_POST["finsta"];
    $user = $_POST["user"];
    $alrt = $_POST["alrt"];
        if($status==1){
            if (mysqli_query($con, "UPDATE payment set status='$status' where pay='$pay'")) {
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$reg_date')");
                if(mysqli_num_rows(mysqli_query($con,"SELECT * from account"))>0){
                    if(mysqli_query($con,"UPDATE account set renewal=(renewal+$renewal),whole=(whole+$renewal),bal=(bal+$renewal),last='$reg_date'")){
                        mysqli_query($con, "UPDATE cardy_one set finsta='$finsta' where pay='$pay'");
                        $response["success"] = 1;
                        $response["message"] = "payment Approved successfully";
                    }else{
                        $response["success"] = 0;
                        $response["message"] = "Failed to approve payment";
                    }
                }else{
                    if(mysqli_query($con,"INSERT into account(whole,renewal,bal,entry,last)values('$renewal','$renewal','$renewal','$reg_date','$reg_date')")){
                        mysqli_query($con, "UPDATE cardy_one set finsta='$finsta' where pay='$pay'");
                        $response["success"] = 1;
                        $response["message"] = "payment Approved successfully";
                    }else{
                        $response["success"] = 0;
                        $response["message"] = "Failed to approve payment";
                    }
                }
            }
        }else{
            if (mysqli_query($con, "UPDATE payment set status='$status' where pay='$pay'")) {
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$reg_date')");
                mysqli_query($con, "UPDATE cardy_one set finsta='$finsta' where pay='$pay'");
                $response["success"] = 1;
                $response["message"] = "payment Rejected successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "Failed to reject payment";
            }
        }
    echo json_encode($response);
    mysqli_close($con);