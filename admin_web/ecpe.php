<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Expenditure</button>
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
                                            <th>payID</th>
                                            <th>term</th>
                                            <th>mpesa</th>
                                            <th>clubExpense</th>
                                            <th>patronToken</th>
                                            <th>TOTAL</th>
                                            <th>CLUB</th>
                                            <th>patronSerial</th>
                                            <th>fname</th>
                                            <th>phone</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                            <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM mpesa");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['pay']; ?></td>
                                                    <td><?php echo $row['term']; ?></td>
                                                    <td><?php echo $row['mpesa']; ?></td>
                                                    <td>Kes<?php echo $row['club_amt']; ?></td>
                                                    <td>Kes<?php echo $row['utility_token']; ?></td>
                                                    <td>Kes<?php echo $row['amount']; ?></td>
                                                    <td><?php echo $row['club']; ?></td>
                                                    <td><?php echo $row['pat_id']; ?></td>
                                                    <td><?php echo $row['pat_name']; ?></td>
                                                    <td><?php echo $row['pat_phone']; ?></td>
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