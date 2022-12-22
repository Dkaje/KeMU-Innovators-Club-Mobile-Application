<?php
include('../database/connection.php');
$theme=$_POST['theme'];
$venue=$_POST['venue'];
$land=$_POST['land'];
$site=$_POST['site'];
$date=$_POST['date'];
$time=$_POST['time'];
$evt=$_POST['evt'];
$member=$_POST['member'];
$money=$_POST['money'];
    if (mysqli_query($con,"UPDATE event set theme='$theme',venue='$venue',land='$land',site='$site',date='$date',time='$time',money='$money',member='$member',opened='$member' where evt='$evt'")) {
            $response["success"] = 1;
            $response["message"] = "event Update was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to update event";
        }
    echo json_encode($response);
    mysqli_close($con);