<?php
include('../database/connection.php');
    $response = mysqli_query($con,"SELECT distinct theme FROM summer where approval=1 and org=1 and pat=0 and closure=0 order by entry_date asc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['theme'] = $row['theme'];
            $meme=mysqli_fetch_assoc(mysqli_query($con,"SELECT venue as venu from summer where theme='$row[theme]' and approval=1 and org=1 and pat=0 and closure=0"));
            $count=mysqli_fetch_assoc(mysqli_query($con,"SELECT count(*) as venu from summer where theme='$row[theme]' and venue='$meme[venu]' and approval=1 and org=1 and pat=0 and closure=0"));
            $club=mysqli_fetch_assoc(mysqli_query($con,"SELECT club as venu from summer where theme='$row[theme]' and venue='$meme[venu]' and approval=1 and org=1 and pat=0 and closure=0"));
            $index['venue'] = $meme['venu'];
            $index['club'] = $club['venu'];
            $index['counter'] = $count['venu'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Registered Record";
    }
    echo json_encode($results);