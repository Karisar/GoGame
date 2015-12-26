<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>GoGame</title>
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
        $("#gametable td").click(function() {     
 
            var column_num = parseInt( $(this).index() )+1 ;
            var row_num = parseInt( $(this).parent().index() )+1;    
 
            location.href='GoGame?row='+row_num + "&col=" + column_num;
        });
    });
</script>
</head>
<%@ page import="com.sarsila.model.GoGame" %>
<body>
    <div id="result"> </div>
    <table align="center" border="1" style="border-collapse: collapse;" cellpadding="8" >
    <tr>
    <td onclick="location.href='skip'">SKIP TURN</td>
    <td onclick="location.href='start'">NEW GAME</td>
    </tr>
    </table>
    <br>
    <%
    	Long game_id = (Long)session.getAttribute("game_id");
    	GoGame go = new GoGame(game_id);
    	int blacks = go.getBlacksCount();
    	int whites = go.getWhitesCount();
    %>
    <center>
	    <p>Whites (O's) = <%= whites %> points</p>
	    <p>Blacks (X's) = <%= blacks %> points </p>
	</center>
    <br>
    <table id="gametable"  border="1" style="border-collapse: collapse;" cellpadding="8" align="center">
    	
    	<%for(int i=1;i<=18;i++){%>
			<tr>
			<%for(int c=1;c<=18;c++){%>
				<td width="40px" height="40px">
            <%String mark;
            mark=go.getMarker(i, c);
            %>
            <font size="6"><%= mark %></font>
            
				</td>
			<%}%>
			</tr>
		<%}%>
    </table>
</body>
</html>