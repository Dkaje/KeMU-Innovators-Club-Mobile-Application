<?php
include '../database/connection.php';
    $try = mysqli_query($con, "SELECT distinct stud_id FROM feedback order by current desc");
    if (mysqli_num_rows($try) > 0) {
        $real['success'] = 1;
        $real['victory'] = array();
        while ($row = mysqli_fetch_array($try)) {
            $cool['stud_id'] = $row['stud_id'];
            $name = mysqli_fetch_assoc(mysqli_query($con, "SELECT name as nam from feedback where stud_id='$row[stud_id]'"));
            $current = mysqli_fetch_assoc(mysqli_query($con, "SELECT max(current) as finder from feedback where stud_id='$row[stud_id]'"));
            $date = mysqli_fetch_assoc(mysqli_query($con, "SELECT date as finder from feedback where stud_id='$row[stud_id]' and current='$current[finder]'"));
            $time = mysqli_fetch_assoc(mysqli_query($con, "SELECT time as finder from feedback where stud_id='$row[stud_id]' and current='$current[finder]'"));
            $message = mysqli_fetch_assoc(mysqli_query($con, "SELECT message as finder from feedback where stud_id='$row[stud_id]' and current='$current[finder]'"));
            $phone = mysqli_fetch_assoc(mysqli_query($con, "SELECT phone as finder from feedback where stud_id='$row[stud_id]' and current='$current[finder]'"));
            $cool['phone'] = $phone['finder'];
            $cool['name'] = $name['nam'];
            $cool['message'] = $message['finder'];
            $cool['date'] = $date['finder'];
            $cool['time'] = $time['finder'];
            array_push($real['victory'], $cool);
        }
    } else {
        $real['success'] = 0;
        $real['mine'] = "No Messages";
    }
    echo json_encode($real);