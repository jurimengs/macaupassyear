<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style> 
div
{
margin:30px;
width:200px;
height:100px;
background-color:yellow;
/* Rotate div */
transform:rotate(9deg);
-ms-transform:rotate(9deg); /* Internet Explorer */
-moz-transform:rotate(9deg); /* Firefox */
-webkit-transform:rotate(9deg); /* Safari 和 Chrome */
-o-transform:rotate(9deg); /* Opera */

animation: webkitshake 5s 1; /*Safari and Chrome*/
-webkit-animation: webkitshake 5s 1; /*Safari and Chrome*/
}

@keyframes webkitshake /*Safari and Chrome*/
{
from { transform: rotate(85deg)}
to { transform: rotate(-5deg)}
}

@-webkit-keyframes webkitshake /*Safari and Chrome*/
{
from { transform: rotate(85deg)}
to { transform: rotate(-5deg)}
}
</style>
</head>
<body>

<div>Hello Worldsss</div>


</body>
</html>
