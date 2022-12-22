<?php
include('../database/connection.php');
    $id = $_POST['id'];
    $ended = $_POST['ended'];
    $end_date=$_POST['end_date'];
    $lec_id=$_POST['lec_id'];
    $lec_name=$_POST['lec_name'];
    $lec_phone=$_POST['lec_phone'];
    $categ=$_POST['categ'];
    $alrt=$_POST['alrt'];
    $ending=$_POST['ending'];
    $normalcy=$_POST['normalcy'];
    if (mysqli_query($con,"UPDATE session set end_date='$end_date', ended='$ended',lec_ide='$lec_id',lec_namee='$lec_name',lec_phonee='$lec_phone' where id='$id'")) {
        $muma=mysqli_query($con, "SELECT * FROM issued where ses='$id' and ended=0");
        if (mysqli_num_rows($muma) > 0) {
            while ($row = mysqli_fetch_array($muma)) {
                $reg_no = $row['reg_no'];
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$reg_no','$normalcy','$end_date')");
               }
        }
        $pup=mysqli_query($con, "SELECT * FROM issued where category='$categ' and expiry_date='$ending' and ses='$id' and expiry=0");
        if (mysqli_num_rows($pup) > 0) {
            while ($row = mysqli_fetch_array($pup)) {
                $iss = $row['iss'];
                $idi = $row['idi'];
                $ses = $row['ses'];
                $reg_no = $row['reg_no'];
                $fullname = $row['fullname'];
                $phone = $row['phone'];
                $profile = $row['profile'];
                $signature = $row['signature'];
                $department = $row['department'];
                $classification = $row['classification'];
                $issue_date = $row['issue_date'];
                $expiry_date = $row['expiry_date'];
                $category = $row['category'];
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$reg_no','$alrt','$end_date')");
                mysqli_query($con,"INSERT INTO expired(iss,idi,ses,reg_no,fullname,phone,profile,signature,department,classification,issue_date,expiry_date,category,entry_date)
                                VALUES('$iss','$idi','$ses','$reg_no','$fullname','$phone','$profile','$signature','$department','$classification','$issue_date','$expiry_date','$category','$end_date')");
               }
        }
        $aky=mysqli_query($con, "SELECT * FROM issued where category!='$categ' and ((expiry_date<('$ending')) or (expiry_date='$ending')) and expiry=0");
        if (mysqli_num_rows($aky) > 0) {
            while ($row = mysqli_fetch_array($aky)) {
                $iss = $row['iss'];
                $idi = $row['idi'];
                $ses = $row['ses'];
                $reg_no = $row['reg_no'];
                $fullname = $row['fullname'];
                $phone = $row['phone'];
                $profile = $row['profile'];
                $signature = $row['signature'];
                $department = $row['department'];
                $classification = $row['classification'];
                $issue_date = $row['issue_date'];
                $expiry_date = $row['expiry_date'];
                $category = $row['category'];
                mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$reg_no','$alrt','$end_date')");
                mysqli_query($con,"INSERT INTO expired(iss,idi,ses,reg_no,fullname,phone,profile,signature,department,classification,issue_date,expiry_date,category,entry_date)
                                VALUES('$iss','$idi','$ses','$reg_no','$fullname','$phone','$profile','$signature','$department','$classification','$issue_date','$expiry_date','$category','$end_date')");
               }
        }
        mysqli_query($con,"UPDATE payment set expiry=1 where ses='$id'");
        mysqli_query($con,"UPDATE issued set ended=1 where ses='$id'");
        mysqli_query($con,"UPDATE mpesa set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE contribution set expiry=1 where ses='$id'");
        mysqli_query($con,"UPDATE contri set expiry=1 where ses='$id'");
        mysqli_query($con,"UPDATE event set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE course set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE summer set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE walk set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE mover set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE announce set closure=1 where ses='$id'");
        mysqli_query($con,"UPDATE report set ended=1,end_date='$end_date' where ses='$id'");
        mysqli_query($con,"UPDATE issued set expiry=1 where (expiry_date='$ending' and ses='$id') or (expiry_date='$ending' and ended=1) or (expiry_date<('$ending'))");
        mysqli_query($con,"UPDATE cardy_one set ended=1 where ses='$id' and ended=0");
        mysqli_query($con,"UPDATE cardy_one set lost=1 where category='$categ' and finsta=1 and secsta=1 and lost=0");
        $response["success"] = 1;
        $response["message"] = "Session Ended successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to end session";
        }
    echo json_encode($response);
    mysqli_close($con);