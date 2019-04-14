<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建文章</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body style="padding: 20px;">
<h1>添加新文章</h1>

<form method="post" action="/doctor/addArticle" enctype="multipart/form-data">
    <div class="form-group">
        <label>题目</label>
        <input type="text" name="title" class="form-control"/><br/>
    </div>
    <div class="form-group">
        <label>简介</label>
        <input type="text" name="summary" class="form-control"/><br/>
    </div>
    <div class="form-group">
        <label>封面图片</label>
        <input type="file" name="file" class="form-control"/><br/>
    </div>

    <div class="form-group">
        <label>文章分类</label>
        <select class="form-control" name="category">
            <option value="2">情感</option>
            <option value="3">疾病</option>
            <option value="4">美容</option>
            <option value="5">辟谣</option>
        </select>
    </div>

    <script id="container" name="content" type="text/plain"></script>
    <hr/>
    <input class="btn btn-primary" type="submit" value="提交文章"/>
</form>


<script type="text/javascript">
    var ue = UE.getEditor('container');
</script>
</body>
</html>