<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Track Events Progress</button>
</div><br>
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800"></h1>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered table-hover"
                                            id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>term</th>
                                            <th>eventTheme</th>
                                            <th>venue</th>
                                            <th>eventDate</th>
                                            <th>CLUB</th>
                                            <th>members</th>
                                            <th>clubExpense</th>
                                            <th>patronSerial</th>
                                            <th>fname</th>
                                            <th>phone</th>
                                            <th>fundDisbursement</th>
                                            <th>entry_date</th>
                                        </tr>
                                    </thead>
                                            <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM mover");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['term']; ?></td>
                                                    <td><?php echo $row['theme']; ?></td>
                                                    <td><?php echo $row['venue']; ?></td>
                                                    <td><?php echo $row['date']; ?></td>
                                                    <td><?php echo $row['club']; ?></td>
                                                    <td><?php echo $row['members']; ?></td>
                                                    <td>Kes<?php echo $row['summ']; ?></td>
                                                    <td><?php echo $row['pat_id']; ?></td>
                                                    <td><?php echo $row['pat_name']; ?></td>
                                                    <td><?php echo $row['pat_phone']; ?></td>
                                                    <td><?php echo $row['fund']; ?></td>
                                                    <td><?php echo $row['entry_date']; ?></td>
                                                </tr>
                                                <?php $i = $i + 1;
                                                    }
                                                } ?>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <?php
include('footer.php');
?>