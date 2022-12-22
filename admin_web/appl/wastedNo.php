<?php
include('../database/connection.php');
$fund=$_POST['fund'];
    $response = mysqli_query($con,"SELECT * FROM mover where fund='$fund' order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['mv'] = $row['mv'];
            $index['evt'] = $row['evt'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['theme'] = $row['theme'];
            $index['venue'] = $row['venue'];
            $index['date'] = $row['date'];
            $index['club'] = $row['club'];
            $index['members'] = $row['members'];
            $index['money'] = $row['money'];
            $index['summ'] = $row['summ'];
            $index['pat_id'] = $row['pat_id'];
            $index['pat_phone'] = $row['pat_phone'];
            $index['pat_name'] = $row['pat_name'];
            $index['fund'] = $row['fund'];
            $index['closure'] = $row['closure'];
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record";
    }
    echo json_encode($results);
