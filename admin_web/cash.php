<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Cash Flow</button>
    </ul></div><br>
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
                                            <th>FreshCards</th>
                                            <th>Renewals</th>
                                            <th>Upgrades</th>
                                            <th>EventPayment</th>
                                            <th>Contribution</th>
                                            <th>TOTAL</th>
                                            <th>Withdrawals</th>
                                            <th>Balance</th>
                                            <th>LastEdit</th>
                                        </tr>
                                    </thead>
                                            <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM account");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td>Kes<?php echo $row['fresh']; ?></td>
                                                    <td>Kes<?php echo $row['renewal']; ?></td>
                                                    <td>Kes<?php echo $row['upgrade']; ?></td>
                                                    <td>Kes<?php echo $row['event_pay']; ?></td>
                                                    <td>Kes<?php echo $row['contribute']; ?></td>
                                                    <td>Kes<?php echo $row['whole']; ?></td>
                                                    <td>Kes<?php echo $row['withdraw']; ?></td>
                                                    <td>Kes<?php echo $row['bal']; ?></td>
                                                    <td><?php echo $row['last']; ?></td>
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