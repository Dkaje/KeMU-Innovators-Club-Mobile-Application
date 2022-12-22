<?php
include('../database/connection.php');
    $response = mysqli_query($con,"SELECT distinct theme FROM summer where status!=0 order by entry_date asc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['theme'] = $row['theme'];
            $meme=mysqli_fetch_assoc(mysqli_query($con,"SELECT venue as venu from summer where theme='$row[theme]' and status!=0"));
            $count=mysqli_fetch_assoc(mysqli_query($con,"SELECT count(*) as venu from summer where theme='$row[theme]' and venue='$meme[venu]' and status=1"));
            $memo=mysqli_fetch_assoc(mysqli_query($con,"SELECT count(*) as vne from summer where theme='$row[theme]' and venue='$meme[venu]' and status=2"));
            $index['venue'] = $meme['venu'];
            $index['counter'] = $count['venu'];
            $index['count_2'] = $memo['vne'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Past Record";
    }
    echo json_encode($results);