<?php
include('../database/connection.php');
    $response = mysqli_query($con,"SELECT * FROM club order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['club_name'] = $row['club_name'];
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Club Record";
    }
    echo json_encode($results);
