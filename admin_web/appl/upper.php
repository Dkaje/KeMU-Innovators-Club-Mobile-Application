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
    $fees = $_POST['fees'];
    $category = $_POST['category'];
    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $term = $_POST['term'];
    $year = $_POST['year'];
    $ses = $_POST['ses'];
    $phone = $_POST['phone'];
    $reg_no = $_POST['reg_no'];
    $classification = $_POST['classification'];
    $department = $_POST['department'];
    $profile = $_POST['profile'];
    $filenameprofile = "IMG" . rand() . ".jpg";
    $signature = $_POST['signature'];
    $filesignature = "IMG" . rand() . ".jpg";
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
        if (mysqli_num_rows(mysqli_query($con, "SELECT * FROM payment WHERE status=0 and reg_no='$reg_no'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "Failed!! Your previous payment is pending approval.";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT * FROM cardy_one WHERE deter='U' and finsta=1 and secsta=0 and reg_no='$reg_no'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "Failed!! You have a card under renewal processing";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT * FROM cardy_one WHERE deter='U' and finsta=1 and secsta=1 and reg_no='$reg_no' and lost=0")) > 0) {
            $response["success"] = 0;
            $response["message"] = "Failed!! You were issued a renewed card whose expiry is yet.";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM contribution WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM payment WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        }elseif (mysqli_num_rows(mysqli_query($con, "SELECT mpesa FROM mpesa WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA code not accepted";
        } else {
            $h=file('mpe.txt');
    $h[0]++;
    $em=fopen('mpe.txt',"w");
    fputs($em,"$h[0]");
    fclose($em);
    $iden=$h[0];
            if (mysqli_query($con, "INSERT INTO payment(pay,deter,mpesa,fees,reg_no,fullname,phone,date,term,year,ses)
            VALUES('$iden','U','$mpesa','$fees','$reg_no','$fullname','$phone','$date','$term','$year','$ses')")) {
                if(mysqli_query($con,"INSERT INTO cardy_one(pay,deter,category,reg_no,fullname,phone,date,profile,signature,department,classification,email,ses)
                VALUES('$iden','U','$category','$reg_no','$fullname','$phone','$date','$filenameprofile','$filesignature','$department','$classification','$email','$ses')")){
file_put_contents("images/" . $filenameprofile, base64_decode($profile));
file_put_contents("images/" . $filesignature, base64_decode($signature));
$response["success"] = 1;
$response["message"] = "Payment was successfully";
                }else{
                    $response["success"] = 0;
                $response["message"] = "Failed!! An error occurred"; 
                }
                
            } else {
                $response["success"] = 0;
                $response["message"] = "Failed to submit payment";
            }
        }
    }
    echo json_encode($response);
    mysqli_close($con);