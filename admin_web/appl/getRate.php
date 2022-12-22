<?php
include('../database/connection.php');
$closure=$_POST['closure'];
    $response = mysqli_query($con,"SELECT * FROM announce where closure='$closure' order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['ann'] = $row['ann'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['subject']=$row['subject'];
            $index['notice'] = $row['notice'];
            if($row['closure']==1){
                $index['closure'] = 'Ended';
            }else{
                $index['closure'] = 'Active';
            }
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Notice Record";
    }
    echo json_encode($results);