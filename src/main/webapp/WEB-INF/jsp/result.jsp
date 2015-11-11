<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Go game</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<table border="1" id="table">
	<tr>
		<td>1/1</td>
		<td>1/2</td>
	</tr>
	<tr>
		<td>2/1</td>
		<td>2/2</td>
	</tr>
</table>
<script src="http://code.jquery.com/jquery-1.9.1.min.js" ></script>     
<script>
    $(document).ready(function(){
        $("#myTable td").click(function() {     
 
            var column_num = parseInt( $(this).index() ) + 1;
            var row_num = parseInt( $(this).parent().index() )+1;    
 
            location.href='GoGame/'+row_num + "/" + column_num;
            location.href='www.kaleva.fi';
        });
    });
</script>
 <!--      <script language="javascript">
        var tbl = document.getElementById("table");
        if (tbl != null) {
            for (var i = 0; i < tbl.rows.length; i++) {
                for (var j = 0; j < tbl.rows[i].cells.length; j++)
                    tbl.rows[i].cells[j].onclick = function () { getval(this); };
            }
        }

        function getval(cel) {
        	location.href='GoGame/1/1';
        }
    </script>
-->
</body>
</html>