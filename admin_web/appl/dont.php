<?php
include('../database/connection.php');
$closure=$_POST['closure'];
$department=$_POST['department'];
$common=$_POST['common'];
    $response = mysqli_query($con,"SELECT * FROM course where closure='$closure' and (department='$department' or department='$common') order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['cs'] = $row['cs'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['code'] = $row['code'];
            $index['title'] = $row['title'];
            $index['credits'] = $row['credits'];
            $index['department'] = $row['department'];
            if($row['closure']==1){
                $index['closure'] = 'Ended';
            }else{
                $index['closure'] = 'Active';
            }
            $index['quote'] = $row['quote'];
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Course Record";
    }
    echo json_encode($results);