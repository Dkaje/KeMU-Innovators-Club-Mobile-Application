<?php
include('../database/connection.php');
$theme=$_POST['theme'];
$venue=$_POST['venue'];
$deter=$_POST['deter'];
$alrt=$_POST['alrt'];
$club_name=$_POST['club_name'];
$entry_date=$_POST['entry_date'];
$org_no=$_POST['org_no'];
$org_name=$_POST['org_name'];
$org_phone=$_POST['org_phone'];
if($deter==4){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from club"))>6){
        $response["success"] = 0;
        $response["message"] = " Failed!! Our system allows upto 7 clubs. Select an Existing club.";
    }else{
        if(mysqli_query($con,"INSERT into club(club_name,entry_date)values('$club_name','$entry_date')")){
            $pup=mysqli_query($con, "SELECT * FROM summer where theme='$theme' and venue='$venue' and approval=1 and org=0");
            if (mysqli_num_rows($pup) > 0) {
                while ($row = mysqli_fetch_array($pup)) {
                    $user = $row['reg_no'];
                    mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$entry_date')");
                }
            }
            if (mysqli_query($con,"UPDATE summer set org=1,club='$club_name',org_no='$org_no',org_name='$org_name',org_phone='$org_phone' where theme='$theme' and venue='$venue' and approval=1 and org=0")) {
                $response["success"] = 1;
                $response["message"] = "List submitted successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "  Failed to submit record";
            } 
        }
    }
}else{
    $pup=mysqli_query($con, "SELECT * FROM summer where theme='$theme' and venue='$venue' and approval=1 and org=0");
    if (mysqli_num_rows($pup) > 0) {
        while ($row = mysqli_fetch_array($pup)) {
            $user = $row['reg_no'];
            mysqli_query($con,"INSERT into quick_alrt(user,alrt,reg_date)values('$user','$alrt','$entry_date')");
        }
    }
    if (mysqli_query($con,"UPDATE summer set org=1,club='$club_name',org_no='$org_no',org_name='$org_name',org_phone='$org_phone' where theme='$theme' and venue='$venue' and approval=1 and org=0")) {
        $response["success"] = 1;
        $response["message"] = "List submitted successfully";
    } else {
        $response["success"] = 0;
        $response["message"] = "  Failed to submit record";
    } 
}
    echo json_encode($response);
    mysqli_close($con);