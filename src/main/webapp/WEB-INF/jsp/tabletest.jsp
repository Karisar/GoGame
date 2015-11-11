<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html>
<head>
<title>Determine Click Position on click of a table cell</title>
<!--CSS -->
<style>
    td{
        cursor:pointer;
        background: -moz-linear-gradient(top, #ffffff, #D1E3E9);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#ffffff), to(#D1E3E9));
        text-align:center;
    }
 
    td:hover{
        background: -moz-linear-gradient(top, #249ee4, #057cc0);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#249ee4), to(#057cc0));
    }
 
    td:active
    {
        background: -moz-linear-gradient(top, #057cc0, #249ee4);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#057cc0), to(#249ee4));
    }
 
    #result{
        font-weight:bold;
        font-size:16pt;
    }
</style>
<!--JAVASCRIPT -->
<script  src="http://code.jquery.com/jquery-1.9.1.min.js" ></script>     
<script>
    $(document).ready(function(){
        $("#myTable td").click(function() {     
 
            var column_num = parseInt( $(this).index() ) + 1;
            var row_num = parseInt( $(this).parent().index() )+1;    
 
            location.href='GoGame/'+row_num + "/" + column_num;
            $("#result").html( "Row_num =" + row_num + "  ,  Rolumn_num ="+ column_num );   
        });
    });
</script>
</head>
<body>
    <div id="result"> </div>
    <table id="myTable" border="1" style="border-collapse: collapse;" cellpadding="8">
        <!--1st ROW-->
        <tr>
            <td>row 1, col 1</td>
            <td>row 1, col 2</td>
            <td>row 1, col 3</td>
            <td>row 1, col 4</td>
            <td>row 1, col 5</td>
        </tr>
 
        <!--2nd ROW-->
        <tr>
            <td>row 2, col 1</td>
            <td>row 2, col 2</td>
            <td>row 2, col 3</td>
            <td>row 2, col 4</td>
            <td>row 2, col 5</td>
        </tr>
 
        <!--3rd ROW-->
        <tr>
            <td>row 3, col 1</td>
            <td>row 3, col 2</td>
            <td>row 3, col 3</td>
            <td>row 3, col 4</td>
            <td>row 3, col 5</td>
        </tr>
 
        <!--4th ROW-->
        <tr>
            <td>row 4, col 1</td>
            <td>row 4, col 2</td>
            <td>row 4, col 3</td>
            <td>row 4, col 4</td>
            <td>row 4, col 5</td>
        </tr>
 
        <!--5th ROW-->
        <tr>
            <td>row 5, col 1</td>
            <td>row 5, col 2</td>
            <td>row 5, col 3</td>
            <td>row 5, col 4</td>
            <td>row 5, col 5</td>
        </tr>
    </table>
</body>
</html>