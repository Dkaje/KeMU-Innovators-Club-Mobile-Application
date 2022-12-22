<?php
include('../database/connection.php');
$reg_no = $_POST['reg_no'];
$password = md5($_POST['password']);
$theme = $_POST['theme'];
if($theme=='stu'){
if(mysqli_num_rows(mysqli_query($con,"SELECT * from student where stud_id='$reg_no'"))===1){
    if (mysqli_num_rows(mysqli_query($con,  "SELECT password FROM student WHERE password='$password' and stud_id='$reg_no'")) > 0) {
        $result["status"] = 0;
        $result["message"] = "Try a different password";
    }else{
    mysqli_query($con,"UPDATE student set password='$password' where stud_id='$reg_no'");
     $result['status'] = 1;
    $result['message'] =  "Reset was successful";
    }
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($theme=='pat'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and role='Patron'"))===1){
        if (mysqli_num_rows(mysqli_query($con,  "SELECT password FROM official WHERE password='$password' and stud_id='$reg_no' and role='Patron'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Try a different password";
        }else{
        mysqli_query($con,"UPDATE official set password='$password' where stud_id='$reg_no' and role='Patron'");
         $result['status'] = 1;
        $result['message'] =  "Reset was successful";
        }
    }else{
        $result['status'] = 0;
        $result['message'] = "Account not found";
    }
}elseif($theme=='tre'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and role='Treasurer'"))===1){
        if (mysqli_num_rows(mysqli_query($con,  "SELECT password FROM official WHERE password='$password' and stud_id='$reg_no' and role='Treasurer'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Try a different password";
        }else{
        mysqli_query($con,"UPDATE official set password='$password' where stud_id='$reg_no' and role='Treasurer'");
         $result['status'] = 1;
        $result['message'] =  "Reset was successful";
        }
    }else{
        $result['status'] = 0;
        $result['message'] = "Account not found";
    }
}elseif($theme=='sec'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and role='Secretary'"))===1){
        if (mysqli_num_rows(mysqli_query($con,  "SELECT password FROM official WHERE password='$password' and stud_id='$reg_no' and role='Secretary'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Try a different password";
        }else{
        mysqli_query($con,"UPDATE official set password='$password' where stud_id='$reg_no' and role='Secretary'");
         $result['status'] = 1;
        $result['message'] =  "Reset was successful";
        }
    }else{
        $result['status'] = 0;
        $result['message'] = "Account not found";
    }
}elseif($theme=='org'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and role='Organization'"))===1){
        if (mysqli_num_rows(mysqli_query($con,  "SELECT password FROM official WHERE password='$password' and stud_id='$reg_no' and role='Organization'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Try a different password";
        }else{
        mysqli_query($con,"UPDATE official set password='$password' where stud_id='$reg_no' and role='Organization'");
         $result['status'] = 1;
        $result['message'] =  "Reset was successful";
        }
    }else{
        $result['status'] = 0;
        $result['message'] = "Account not found";
    }
}elseif($theme=='lec'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from lec where serial_no='$reg_no'"))===1){
        if (mysqli_num_rows(mysqli_query($con,  "SELECT password FROM lec WHERE password='$password' and serial_no='$reg_no'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Try a different password";
        }else{
        mysqli_query($con,"UPDATE lec set password='$password' where serial_no='$reg_no'");
         $result['status'] = 1;
        $result['message'] =  "Reset was successful";
        }
    }else{
        $result['status'] = 0;
        $result['message'] = "Account not found";
    }
}
echo json_encode($result);
mysqli_close($con);