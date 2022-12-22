<?php
include('../database/connection.php');
    $reg_no = $_POST['reg_no'];
    $fname = $_POST["fname"];
    $lname = $_POST['lname'];
    $gender = $_POST['gender'];
    $phone = $_POST['phone'];
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $department = $_POST['department'];
    $classification = $_POST['classification'];
    $theme = $_POST['theme'];
    $date = $_POST['date'];
    $role = $_POST['role'];
    if($theme=='stu'){
    if (mysqli_num_rows(mysqli_query($con, "SELECT stud_id FROM student WHERE stud_id='$reg_no'")) > 0) {
        $result["status"] = 0;
        $result["message"] = "Stud ID not present";
    } elseif (mysqli_num_rows(mysqli_query($con,  "SELECT phone FROM student WHERE phone='$phone'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Phone not present";
        } elseif (mysqli_num_rows(mysqli_query($con, "SELECT email FROM student WHERE email='$email'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "Email Address not Present";
        } elseif (mysqli_query($con, "INSERT INTO student(stud_id,fname,lname,gender,email,department,phone,classification,password,date)
            VALUES('$reg_no','$fname','$lname','$gender','$email','$department','$phone','$classification','$password','$date')")) {
                $result["status"] = 1;
                $result["message"] = "Account created. Kindly wait for Approval";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
}elseif ($theme=='off'){
    if($role=='Patron'){
        /*if (mysqli_num_rows(mysqli_query($con, "SELECT stud_id FROM official WHERE stud_id='$reg_no'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "USER ID not present";
        } else*/if(mysqli_num_rows(mysqli_query($con,  "SELECT phone FROM official WHERE phone='$phone'")) > 0) {
                $result["status"] = 0;
                $result["message"] = "Phone not present";
            } elseif (mysqli_num_rows(mysqli_query($con, "SELECT email FROM official WHERE email='$email'")) > 0) {
                $result["status"] = 0;
                $result["message"] = "Email Address not Present";
            } else{
                $hits = file("staff.txt");
                $hits[0]++;
                $fp = fopen("staff.txt", "w");
                fputs($fp, "$hits[0]");
                fclose($fp);
                $values = $hits[0];
                $year = date("Y");
                $last=$year %100;
                $staf=strtoupper(substr($fname,0,2));
                $entry = $values.$staf.$last;
                if (mysqli_query($con, "INSERT INTO official(stud_id,fname,lname,gender,email,department,phone,classification,password,role,date)
                VALUES('$entry','$fname','$lname','$gender','$email','$department','$phone','$classification','$password','$role','$date')")) {
                $result["status"] = 1;
                $result["message"] = "$entry";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
        }
    }else{
        if (mysqli_query($con, "INSERT INTO official(stud_id,fname,lname,gender,email,department,phone,classification,password,role,date)
                        VALUES('$reg_no','$fname','$lname','$gender','$email','$department','$phone','$classification','$password','$role','$date')")) {
                        $result["status"] = 1;
                        $result["message"] = "Account created. Kindly wait for Approval";
                    } else {
                    $result["status"] = 0;
                    $result["message"] = " Failed to create account";
                }
        }
    }elseif($theme=='lec'){
        /*if (mysqli_num_rows(mysqli_query($con, "SELECT serial_no FROM lec WHERE serial_no='$reg_no'")) > 0) {
            $result["status"] = 0;
            $result["message"] = "SERIAL ID not present";
        } else*/if (mysqli_num_rows(mysqli_query($con,  "SELECT phone FROM lec WHERE phone='$phone'")) > 0) {
                $result["status"] = 0;
                $result["message"] = "Phone not present";
            } else if (mysqli_num_rows(mysqli_query($con, "SELECT email FROM lec WHERE email='$email'")) > 0) {
                $result["status"] = 0;
                $result["message"] = "Email Address not Present";
            } else{
                $hits = file("staff.txt");
                $hits[0]++;
                $fp = fopen("staff.txt", "w");
                fputs($fp, "$hits[0]");
                fclose($fp);
                $values = $hits[0];
                $year = date("Y");
                $last=$year %100;
                $staf=strtoupper(substr($fname,0,2));
                $entry = $values.$staf.$last;
                if (mysqli_query($con, "INSERT INTO lec(serial_no,fname,lname,email,department,phone,password,date)
                VALUES('$entry','$fname','$lname','$email','$department','$phone','$password','$date')")) {
                $result["status"] = 1;
                $result["message"] = "$entry";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
        }
        }
    echo json_encode($result);