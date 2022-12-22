<?php
include('../database/connection.php');
$mv=$_POST['mv'];
    $response = mysqli_query($con,"SELECT * FROM mpesa where mv='$mv'");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['pay'] = $row['pay'];
            $index['mv'] = $row['mv'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['mpesa'] = $row['mpesa'];
            $index['club_amt'] = $row['club_amt'];
            $index['utility_token'] = $row['utility_token'];
            $index['amount'] = $row['amount'];
            $index['club'] = $row['club'];
            $index['pat_id'] = $row['pat_id'];
            $index['pat_phone'] = $row['pat_phone'];
            $index['pat_name'] = $row['pat_name'];
            if ($row['closure'] == 0) {
                $index['closure'] = 'Pending';
            }else {
                $index['closure'] = 'Expired';
            }
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        } 
    } else {
        $results['trust'] = 0;
        $results['mine'] = "Oops!! No record was found";
    }
    echo json_encode($results);
