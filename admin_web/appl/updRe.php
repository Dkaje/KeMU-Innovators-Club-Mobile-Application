<?php
include('../database/connection.php');
$status=$_POST['status'];
$sm=$_POST['sm'];
$evt=$_POST['evt'];
$comment=$_POST['comment'];
$rage=1;
if($status==1){
    if (mysqli_query($con,"UPDATE summer set status='$status',comment='$comment' where sm='$sm'")) {
        $response["success"] = 1;
        $response["message"] = "record approval was successful";
    } else {
        $response["success"] = 0;
        $response["message"] = "  Failed to approval record";
    }
}else{
    if (mysqli_query($con,"UPDATE summer set status='$status',comment='$comment' where sm='$sm'")) {
        mysqli_query($con,"UPDATE event set member=(member+$rage) where evt='$evt'");
            $response["success"] = 1;
            $response["message"] = "record rejection was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to reject record";
        }
    }
    echo json_encode($response);
    mysqli_close($con);