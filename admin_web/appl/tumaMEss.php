<?php
include('../database/connection.php');
$message=$_POST["message"];
$phone=$_POST["phone"];
$stud_id=$_POST["stud_id"];
$move=$_POST["move"];
$date=$_POST["date"];
$current=$_POST["current"];
$time=$_POST["time"];
$name=$_POST["name"];

if(mysqli_query($con,"INSERT into feedback(message,phone,move,stud_id,current,time,date,name)
values('$message','$phone','$move','$stud_id','$current','$time','$date','$name')")){
    $res["success"]=1;
    $res["message"]="Your Message was sent";
}else{
    $res["success"]=0;
    $res["message"]="Failed to send";
}
echo json_encode($res);