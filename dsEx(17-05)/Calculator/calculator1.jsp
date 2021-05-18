<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html>
   <head>
      <style>
         .title{
         margin-bottom: 10px;
         text-align:center;
         width: 210px;
         color:green;
         border: solid black 2px;
         }
  
         input[type="button"]
         {
         background-color:green;
         color: black;
         border: solid black 2px;
         width:100%
         }
  
         input[type="text"]
         {
         background-color:white;
         border: solid black 2px;
         width:100%
         }
      </style>
   </head>
   <!-- create table -->
	
   <body>
      <div class = title >Calculator</div>
      <table border="1">
         <tr>
         <% String name = ""; 
            name = (String) request.getAttribute("display"); 
         %>
            <td colspan="3"><input type="text" id="result" value=<%=name%> /></td>
            <!-- clr() function will call clr to clear all value -->
            <td><form action="calculate" method="POST"><input type="submit" value="c" name="button"/></form> </td>
         </tr>
         <tr>
            <!-- create button and assign value to each button -->
            <!-- dis("1") will call function dis to display value -->
            <td><form action="calculate" method="POST"><input type="submit" value="1" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="2" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="3" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="/" name="button"/></form> </td>
         </tr>
         <tr>
            <td><form action="calculate" method="POST"><input type="submit" value="4" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="5" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="6" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="-" name="button"/></form> </td>
         </tr>
         <tr>
            <td><form action="calculate" method="POST"><input type="submit" value="7" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="8" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="9" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="+" name="button"/></form> </td>
         </tr>
         <tr>
            <td><form action="calculate" method="POST"><input type="submit" value="." name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="0" name="button"/></form> </td>
            <!-- solve function call function solve to evaluate value -->
            <td><form action="calculate" method="POST"><input type="submit" value="=" name="button"/></form> </td>
            <td><form action="calculate" method="POST"><input type="submit" value="*" name="button"/></form> </td>
         </tr>
      </table>
   </body>
</html>