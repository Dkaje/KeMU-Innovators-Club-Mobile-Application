<?php
include('../database/connection.php');
    $response = mysqli_query($con,"SELECT * FROM session where status=1 and ended=0 order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['term'] = $row['term'];
            $index['year'] = $row['year'];
            $index['report'] = $row['report'];
            if($row['status']==1){
                $index['status'] = 'Active';
            }else{
                $index['status'] = 'Pending';
            }
            $index['entry_date'] = $row['entry_date'];
            if($row['ended']==1){
                $index['ended'] = 'Ended';
            }else{
                $index['ended'] = 'Active';
            }
            $index['ending'] = $row['ending'];
            $index['end_date'] = $row['end_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No session Record";
    }
    echo json_encode($results);