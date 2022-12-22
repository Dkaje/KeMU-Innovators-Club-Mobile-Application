<?php
include('../database/connection.php');
    $response = mysqli_query($con,"SELECT * FROM account");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['fresh'] = $row['fresh'];
            $index['renewal'] = $row['renewal'];
            $index['upgrade'] = $row['upgrade'];
            $index['contribute'] = $row['contribute'];
            $index['event_pay']=$row['event_pay'];
            $index['withdraw'] = $row['withdraw'];
            $index['whole'] = $row['whole'];
            $index['bal'] = $row['bal'];
            $index['entry'] = $row['entry'];
            $index['last'] = $row['last'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "Oops!! No funds were found";
    }
    echo json_encode($results);