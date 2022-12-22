<?php
include('../database/connection.php');
$user=$_POST['user'];
    $response = mysqli_query($con,"SELECT * FROM quick_alrt where user='$user' order by reg_date asc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['user'] = $row['user'];
            $index['alrt'] = $row['alrt'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Notification Record";
    }
    echo json_encode($results);
