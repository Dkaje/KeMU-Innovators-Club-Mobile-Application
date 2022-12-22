<?php
include('../database/connection.php');
$userid=$_POST["userid"];
    $response = mysqli_query($con,"SELECT * FROM feedback where userid='$userid' order by auto asc");
    if (mysqli_num_rows($response) > 0) {
        $results['success'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['post'] = $row['post'];
            $index['userid'] = $row['userid'];
            $index['phone'] = $row['phone'];
            $index['name'] = $row['name'];
            $index['host'] = $row['host'];
            $index['message'] = $row['message'];
            $index['time'] = $row['time'];
            $index['date'] = $row['date'];
            $index['auto'] = $row['auto'];
            $index['okay'] = $row['okay'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['success'] = 0;
        $results['mine'] = "No Message was Found";
    }
    echo json_encode($results);