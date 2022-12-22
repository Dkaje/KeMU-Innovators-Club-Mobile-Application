<?php
include 'database/connection.php';
error_reporting(0);


    if (isset($_POST['approveBtn'])) {
        $studid = $_POST['identity'];
        if (mysqli_query($con, "update official set status=1,remarks='' where stud_id = '$studid '")) {
            echo "<script>alert('Approved')</script>";
            echo "<script>location.href='offR.php' </script>";
        } else {
            die("Connection failed: " . $con->connect_error);
        }
    }

    if (isset($_POST['rejectBtn'])) {
        $studid = $_POST['identity'];
        $remarks = $_POST['remarks'];
        if (mysqli_query($con, "update official set status=2 ,remarks='$remarks' where stud_id = '$studid '")) {
            echo "<script>alert('Rejected')</script>";
            echo "<script>location.href='offR.php' </script>";
        } else {
            die("Connection failed: " . $con->connect_error);
        }
    }
include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Manage Officials   <i class="fas fa-caret-down" style="color: #fff;"></i></button>
 <ul class="dropdown-menu alert panel-footer">
        <li>
        <a href="off.php"class="btn btn-secondary btn-xs">Pending</a><br>
        <a href="offA.php"class="btn btn-success btn-xs">Approved</a><br>
        <a href="offR.php"class="btn btn-danger btn-xs">Rejected</a>
        </li>
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
                                            <th>STUD_ID</th>
                                            <th>FNAME</th>
                                            <th>LNAME</th>
                                            <th>PHONE</th>
                                            <th>EMAIL</th>
                                            <th>Department</th>
                                            <th>classification</th>
                                            <th>Role</th>
                                            <th>Status</th>
                                            <th>Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM official where role!='Patron' and status=2");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['stud_id']; ?></td>
                                                    <td><?php echo $row['fname']; ?></td>
                                                    <td><?php echo $row['lname']; ?></td>
                                                    <td> <?php echo $row['phone']; ?></td>
                                                    <td><?php echo $row['email']; ?></td>
                                                    <td><?php echo $row['department']; ?></td>
                                                    <td><?php echo $row['classification']; ?></td>
                                                    <td><?php echo $row['role']; ?></td>
                                                    <td><?php
                                                                    if ($row['status'] == 2) {
                                                                        echo 'Rejected';
                                                                    } ?></td>
                                                    <td><?php echo $row['date']; ?></td>
                                                    <td>
                                                        <div class="dropdown" style="float: left;">
                                                            <button class="btn btn-primary btn-xs" type="button"
                                                                data-toggle="dropdown">Approve</button>
                                                            <ul class="dropdown-menu alert panel-footer">
                                                                <li>
                                                                    <form method="post">
                                                                        <input type="hidden" name="identity"
                                                                            value="<?php echo $row["stud_id"]; ?>" /><br>
                                                                        <input type="submit" name="approveBtn"
                                                                            value="Confirm"
                                                                            class="btn btn-primary btn-xs" />
                                                                    </form>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </td>
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