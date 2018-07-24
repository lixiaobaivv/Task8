<%--
  Created by IntelliJ IDEA.
  User: Yzzzt
  Date: 2018/6/30
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var xmlhttp;
        function bthClik()
        {
            xmlhttp=null;
            if (window.XMLHttpRequest)
            {xmlhttp=new XMLHttpRequest();
            }
            else if (window.ActiveXObject)
            {// code for IE6 ,IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            if (xmlhttp!=null)
            {
                xmlhttp.onreadystatechange=function(){
                    if (xmlhttp.readyState==4)
                    {// 4 = "loaded"
                        if (xmlhttp.status==200)
                        {
                            console.log(xmlhttp.responseText);
                            document.getElementById('T1').innerHTML=xmlhttp.responseText;
                        }
                        else{
                            alert("Problem retrieving data:" + xmlhttp.responseText);
                        }
                    }
                };
                xmlhttp.open("GET","/ajax",true); //http://loclhost8080:/Task5
                //xmhttp.open("GET","/AJAX",true); //本地获取
                xmlhttp.send();
            }
            else {
                alert("Your browser does not support XMLHTTP.");
            }
        }

        function state_Change() {
            if (xmlhttp.readyState==4)
            {// 4 = "loaded"}
                if (xmlhttp.status==200)
                {// 200 = "ok"
                    document.getElementById('T1').innerHTML=xmlhttp.responseText;
                }
                else {
                    alert("Problem retrieving data:" + xmlhttp.statusText);
                }
            }
        }
    </script>
</head>
<body>
<input type="button" value="点我控制台会打印" onclick="btnClick()">
<p id="T1"></p>
</body>
</html>
