<?php
include('../database/connection.php');
    $issue_date = $_POST['issue_date'];
    $category = $_POST['category'];
    $fullname = $_POST['fullname'];
    $expiry_date = $_POST['expiry_date'];
    $idi = $_POST['idi'];
    $secsta = $_POST['secsta'];
    $ses = $_POST['ses'];
    $phone = $_POST['phone'];
    $reg_no = $_POST['reg_no'];
    $classification = $_POST['classification'];
    $department = $_POST['department'];
    $alrt = $_POST['alrt'];
    $entry_date = $_POST['entry_date'];
    
    if (mysqli_query($con, "UPDATE cardy_one set secsta='$secsta' WHERE idi='$idi'")) {
        mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$reg_no','$alrt','$entry_date')");
        $profile = mysqli_fetch_assoc(mysqli_query($con, "SELECT profile as img from cardy_one where idi='$idi'"));
        $signature = mysqli_fetch_assoc(mysqli_query($con, "SELECT signature as img from cardy_one where idi='$idi'"));
        $h=file('mpe.txt');
$h[0]++;
$em=fopen('mpe.txt',"w");
fputs($em,"$h[0]");
fclose($em);
$iden=$h[0];
mysqli_query($con,"INSERT INTO issued(iss,idi,ses,reg_no,fullname,phone,profile,signature,department,classification,issue_date,expiry_date,category,status,entry_date)
                VALUES('$iden','$idi','$ses','$reg_no','$fullname','$phone','$profile[img]','$signature[img]','$department','$classification','$issue_date','$expiry_date','$category',1,'$entry_date')");
                
            $response["success"] = 1;
            $response["message"] = "card issuing was successfully";
        } else {
                $response["success"] = 0;
                $response["message"] = "Failed to issue";
            } 
    echo json_encode($response);
    mysqli_close($con);