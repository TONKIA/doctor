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
    <a href="mgr.jsp">后台管理</a><br/>
    <a href="login.jsp">医生登录</a><br/>
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
        <td>/user/login</td>
        <td>
            phoneNumber<br/>
            token<br/>
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
            如：<a href="file/getImg/4c64b83e-7ebd-40ec-ba81-da436492441b.jpg">file/getImg/4c64b83e-7ebd-40ec-ba81-da436492441b.jpg</a>
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
        <td>/popularization/getTop</td>
        <td>
            获取置顶
        </td>
    </tr>
    <tr>
        <td>/popularization/getArticle/{category}</td>
        <td>
            0关注 需要提供 String phoneNumber, String token<br/>
            根据分类获取文章<br/>
            0关注 1 推荐 2 情感 3疾病 4辟谣 5美容<br/>
        </td>
    </tr>

    <tr>
        <td>/doctor/thumbup</td>
        <td>
            满意<br/>
            String phoneNumber, String token, Integer doctorId<br/>
        </td>
    </tr>

    <tr>
        <td>/doctor/thumbdown</td>
        <td>
            不满意<br/>
            String phoneNumber, String token, Integer doctorId<br/>
        </td>
    </tr>

    <tr>
        <td>/psychology/getAllSaying/{category}</td>
        <td>
            category<br/>
            0 我的恋爱<br/>
            1 我的治疗<br/>
            2 我的求助<br/>
            3 我的安利<br/>
        </td>
    </tr>

    <tr>
        <td>/psychology/publish</td>
        <td>
            String phoneNumber, String token, String content,Integer category, MultipartFile file<br/>

            <form action="/psychology/publish" method="post" enctype="multipart/form-data">
                phoneNumber:<input type="text" name="phoneNumber"/><br/>
                token:<input type="text" name="token"/><br/>
                content:<input type="text" name="content"/><br/>
                category:
                <select name="category">
                    <option value="0">我的恋爱</option>
                    <option value="1">我的治疗</option>
                    <option value="2">我的求助</option>
                    <option value="3">我的安利</option>
                </select><br/>
                file:<input type="file" name="file"/><br/>
                <input type="submit" value="发布"/>
            </form>
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