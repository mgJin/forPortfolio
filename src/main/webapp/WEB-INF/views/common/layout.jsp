<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><tiles:insertAttribute name="title"/></title>
    <style>
        #wrap{
            margin:0 auto;
            width:1200px;
            height: 1100px;
         /*   background-color: blanchedalmond;*/
        }
        
        #header{
            width: 1200px;
            height:100px;
            /*background-color: blue;*/
            box-sizing: border-box;
        }
       
        #body{
            clear: both;
            margin-top:50px;
            width:1200px;
            height:950px;
            box-sizing: border-box;
            
            /* background-color: blueviolet; */
        }

     
        footer{
            width: 1200px;
            height:50px;
            box-sizing: border-box;
            /*background-color: cadetblue;*/
        }



    </style>
    
</head>

<body>
    <div id="wrap">
        
        <div id="header">
            <tiles:insertAttribute name="header"/>
        </div>

        <div id="body">
       		<tiles:insertAttribute name="body"/>
        </div>
       
        <footer>
            <tiles:insertAttribute name="footer"/>
        </footer>
    </div>
</body>
</html>