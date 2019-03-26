<%--
  Created by IntelliJ IDEA.
  User: TONKIA
  Date: 2019/3/13
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑";
        }

        #l-map {
            height: 500px;
            width: 100%;
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=co5zmm5HcXsCSE3xtjmdxA7KCwI0NZGl"></script>
    <title>地图</title>
</head>
<body>
<div id="l-map"></div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("l-map");
    map.enableScrollWheelZoom(true);

    //34.3485820000,108.9445190000
    var point1 = new BMap.Point(108.9445190000, 34.3485820000);
    map.centerAndZoom(point1, 6);
    var circle1 = new BMap.Circle(point1, 21000);
    circle1.setFillColor("#A6CBA1");
    circle1.setStrokeColor("#A6CBA1");
    map.addOverlay(circle1);

    // var point2 = new BMap.Point(108.7664091657, 34.4422617096);
    // var circle2 = new BMap.Circle(point2, 300000);
    // circle2.setFillColor("#aabbcc");
    // circle2.setStrokeColor("#aabbcc");
    // map.addOverlay(circle2);


</script>
