<?php
$hostname_zkizblog = "localhost";
$database_zkizblog = "xxx";
$username_zkizblog = "xxx";
$password_zkizblog = "xxx";
$zkizblog = mysql_pconnect($hostname_zkizblog, $username_zkizblog, $password_zkizblog) or trigger_error(mysql_error() , E_USER_ERROR);
mysql_query("SET NAMES UTF8");
mysql_select_db($database_zkizblog, $zkizblog);


//Return a cell
function dbRs($query){
	global $queryno; $queryno++;
	$tmp = mysql_query($query) or die(mysql_error());
	if(mysql_num_rows($tmp)==0){
		return false;
	}else{
		return mysql_result($tmp, 0);
	}
}

//Return a 2D Array
function dbAr($query){
global $queryno; $queryno++;
	$arr = array();
	$tmp = mysql_query($query) or die(dbErr(mysql_error(),$query));
	if(mysql_num_rows($tmp)==0){
		return $arr;
	} else {
		while($row = mysql_fetch_assoc($tmp)){
			$arr[] = $row;
		}
		mysql_free_result($tmp);
		return $arr;
	}
}

// This stops SQL Injection in GET vars

foreach($_GET as $key => $value) {
	$_GET[$key] = mysql_real_escape_string($value);
}

if($_GET['type']=='retrivelist'){
	$k = dbAr("SELECT title FROM zzz_novel order by id");
	foreach($k as $v){
		$ll[] = ++$ii."#".$v['title'];
	}
	echo implode("9999",$ll);
}

if($_GET['type']=='downloadbook'){
	echo dbRs("SELECT content FROM zzz_novel WHERE id = {$_GET['id']}");
}
if($_GET['type']==''){
	echo "<h1>The Newest Books Recommend!</h1>";
	echo dbRs("SELECT title FROM zzz_novel order by id DESC LIMIT 1");
}
?>
