<?php
include('../database/connection.php');
    $reg_no = $_POST['reg_no'];
    $role = $_POST['role'];
if(!(mysqli_num_rows(mysqli_query($con, "SELECT * FROM student WHERE stud_id='$reg_no'"))==1)){
            $result["success"] = 0;
            $result["message"] = "First Register as a Student";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT stud_id FROM official WHERE stud_id='$reg_no'")) > 0) {
                    $result["success"] = 0;
                    $result["message"] = "Hi there, your identity cannot be overwritten!!!";
                } elseif(mysqli_num_rows(mysqli_query($con,  "SELECT * FROM official WHERE role='$role' and status!=2")) > 1) {
                        $result["success"] = 8;
                        $result["message"] = "Hi there, your role was taken.";
                    } else{
                        $response = mysqli_query($con,"SELECT * FROM student where stud_id='$reg_no'");
                        if (mysqli_num_rows($response) > 0) {
        $result['success'] = 1;
        $result['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
        $tetra['stud_id']=$reg_no;
        $tetra['fname']=$row['fname'];
        $tetra['lname']=$row['lname'];
        $tetra['gender']=$row['gender'];
        $tetra['phone']=$row['phone'];
        $tetra['email']=$row['email'];
        $tetra['department']=$row['department'];
        $tetra['classification']=$row['classification'];
        $result['success']=1;
        $result['message']='Login was successful';
        array_push($result['victory'],$tetra);
        }
    }
}
    echo json_encode($result);