<?php
include('../database/connection.php');
$id=$_POST['id'];
$variance=$_POST['variance'];
$club_name=$_POST['club_name'];
if($variance==7){
    if (mysqli_query($con,"UPDATE club set club_name='$club_name' where id='$id'")) {
                $response["success"] = 1;
                $response["message"] = "Club was updated successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "Failed to update Club record";
            }
}else{
    if (mysqli_query($con,"DELETE from club where id='$id'")) {
        $response["success"] = 1;
        $response["message"] = "Club was deleted successfully";
    } else {
        $response["success"] = 0;
        $response["message"] = "  Failed to delete record";
    } 
}
    echo json_encode($response);
    mysqli_close($con);