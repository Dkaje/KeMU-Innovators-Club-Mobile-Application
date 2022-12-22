<?php
include '../database/connection.php';
$stud_id=$_POST["stud_id"];
    $response = mysqli_query($con,"SELECT * FROM feedback where stud_id='$stud_id' order by current asc");
    if (mysqli_num_rows($response) > 0) {
        $results['success'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['fd'] = $row['fd'];
            $index['stud_id'] = $row['stud_id'];
            $index['phone'] = $row['phone'];
            $index['name'] = $row['name'];
            $index['message'] = $row['message'];
            $index['move'] = $row['move'];
            $index['date'] = $row['date'];
            $index['time'] = $row['time'];
            $index['current'] = $row['current'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['success'] = 0;
        $results['mine'] = "No Message was Found";
    }
    echo json_encode($results);