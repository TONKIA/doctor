<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<style>
    td {
        border: solid 1px;
        padding: 10px;
    }
</style>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

<table>
    <tr>
        <td>/user/getSmsForLogin/{phoneNumber}</td>
        <td>
            get请求<br/>
            在数据库中注册用户（如果不存在该手机号）并且发出短信验证码<br/>
            如果存在就直接发出短信验证码<br/>
            更新数据库中短信验证码 并设置过期时间<br/>
        </td>
    </tr>
    <tr>
        <td>/user/loginWithSms</td>
        <td>
            {<br/>
            phoneNumber：xxx<br/>
            smsCode：xxx<br/>
            }<br/>
            必须在一分钟内登录<br/>
        </td>
    </tr>
    <tr>
        <td>/file/uploadImg</td>
        <td>
            <form enctype="multipart/form-data" method="post" action="/file/uploadImg">
                <input type="file" name="file"/>
                <input type="submit"/>
            </form>
        </td>
    </tr>
    <tr>
        <td>/file/getImg/{fileName}</td>
        <td>
            获取文件接口，fileName为文件名。
            如：<a href="file/getImg/151a1ad3-631c-409b-8880-316190c25d71.jpg">file/getImg/151a1ad3-631c-409b-8880-316190c25d71.jpg</a>
        </td>
    </tr>
    <tr>
        <td>/home/getBanner</td>
        <td>
            获取所有的Banner
            <div id="banner"></div>
            img 图片url<br/>
            url 跳转网页链接<br/>
        </td>
    </tr>
    <tr>
        <td>/home/getHome</td>
        <td>
            获取其他主页信息：推荐医生和首页文章<br/>
            * @param doctorNumber 获取几个医生<br/>
            * @param articleNumber 获取几篇文章<br/>
        </td>
    </tr>
    <tr>
        <td>/mgr/uploadBanner</td>
        <td>
            上传Banner
            <form enctype="multipart/form-data" method="post" action="/mgr/uploadBanner">
                <input type="file" name="file"/><br/>
                url:<input type="text" name="url"/><br/>
                <input type="submit"/>
            </form>
        </td>
    </tr>
    <tr>
        <td>/mgr/delBanner/{id}</td>
        <td>
            删除Banner
        </td>
    </tr>

</table>

<script>

    $.get("home/getBanner", function (data) {
        data.data.forEach(function (element) {
            console.log(element);
            $("#banner").append(
                "<a href='" + element.url + "'><img  width='50px' height='50px' src='" + element.img + "'/><a/>"
            );
        });
    });

</script>

</body>

</html>