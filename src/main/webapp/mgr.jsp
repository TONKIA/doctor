<%--
  Created by IntelliJ IDEA.
  User: TONKIA
  Date: 2019/3/20
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div id="app" style="padding: 50px;">
    <v-app>
        <v-carousel>
            <v-carousel-item
                    v-for="(item,i) in items"
                    :key="i"
                    :src="item.img"
            >
            </v-carousel-item>
        </v-carousel>
    </v-app>

    <div class="card card-body">
        <h1>轮播图管理</h1>
        <table class="table table-bordered">
            <tr>
                <td>
                    图片链接
                </td>
                <td>
                    跳转链接
                </td>
                <td>
                    删除
                </td>
            </tr>
            <tr v-for="item in items" :key="item.id">
                <td>{{item.img}}</td>
                <td><a :href="item.url">{{item.url}}</a></td>
                <td>
                    <button class="btn btn-danger" @click="del(item.id)">删除</button>
                </td>
            </tr>
        </table>
        <form enctype="multipart/form-data" method="post" action="/mgr/uploadBanner">
            <div class="form-group">
                <label>轮播图图片</label>
                <input type="file" name="file" class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>跳转地址</label>
                <input type="text" name="url" class="form-control"/><br/>
            </div>
            <input type="submit" class="btn btn-primary"/>
        </form>
    </div>
    <hr/>
    <div class="card card-body">
        <h1>文章管理</h1>
        <div class="list-group">
            <a href="#" :class="{'list-group-item':true,active:show==2}" @click.prevent="getArticle(2)">2情感</a>
            <a href="#" :class="{'list-group-item':true,active:show==3}" @click.prevent="getArticle(3)">3疾病</a>
            <a href="#" :class="{'list-group-item':true,active:show==4}" @click.prevent="getArticle(4)">4辟谣</a>
            <a href="#" :class="{'list-group-item':true,active:show==5}" @click.prevent="getArticle(5)">5美容</a>
        </div>
        <table class="table table-bordered">
            <tr>
                <td>
                    文章ID
                </td>
                <td>
                    题目
                </td>
                <td>
                    置顶
                </td>
                <td>
                    推荐
                </td>
            </tr>
            <tr v-for="item in articleItems" :key="item.articleId">
                <td>{{item.articleId}}</td>
                <td>{{item.title}}</td>
                <td>
                    <button class="btn btn-primary" @click="top(item.articleId)">置顶</button>
                </td>
                <td>
                    <button class="btn btn-primary" @click="recommand(item.articleId)">推荐</button>
                </td>
            </tr>
        </table>
    </div>
    <hr/>
    <div class="card card-body">
        <h1>置顶文章</h1>
        <table class="table table-bordered">
            <tr>
                <td>
                    文章ID
                </td>
                <td>
                    题目
                </td>
                <td>
                    取消置顶
                </td>
            </tr>
            <tr v-for="item in topItems" :key="item.articleId">
                <td>{{item.articleId}}</td>
                <td>{{item.title}}</td>
                <td>
                    <button class="btn btn-danger" @click="cancelTop(item.articleId)">取消</button>
                </td>
            </tr>
        </table>
    </div>
    <hr/>
    <div class="card card-body">
        <h1>推荐文章</h1>
        <table class="table table-bordered">
            <tr>
                <td>
                    文章ID
                </td>
                <td>
                    题目
                </td>
                <td>
                    取消推荐
                </td>
            </tr>
            <tr v-for="item in recommandItems" :key="item.articleId">
                <td>{{item.articleId}}</td>
                <td>{{item.title}}</td>
                <td>
                    <button class="btn btn-danger" @click="cancelRecommand(item.articleId)">取消</button>
                </td>
            </tr>
        </table>
    </div>
    <hr/>
    <div class="card card-body">
        <h1>小知识管理</h1>
        <table class="table table-bordered">
            <tr>
                <td>
                    小知识ID
                </td>
                <td>
                    题目
                </td>
                <td>
                    图片
                </td>
                <td>
                    简介
                </td>
                <td>
                    删除
                </td>
            </tr>
            <tr v-for="item in tipItems" :key="item.tipId">
                <td>{{item.tipId}}</td>
                <td>{{item.title}}</td>
                <td><img :src="item.picUrl" width="100px"/></td>
                <td>{{item.summary}}</td>
                <td>
                    <button class="btn btn-danger" @click="delTip(item.tipId)">删除</button>
                </td>
            </tr>
        </table>
        <form enctype="multipart/form-data" method="post" action="/mgr/addTip">
            <div class="form-group">
                <label>小知识封面</label>
                <input type="file" name="file" class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>题目</label>
                <input type="text" name="title" class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>简介</label>
                <input type="text" name="summary" class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>内容</label>
                <textarea class="form-control" rows="3" name="content"></textarea>
            </div>
            <input type="submit" class="btn btn-primary"/>
        </form>
    </div>


</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.js"></script>
<script>
    new Vue({
        el: '#app',
        data: {
            items: null,
            topItems: null,
            recommandItems: null,
            articleItems: null,
            show: 3,
            tipItems: null
        },
        methods: {
            del: function (id) {
                this.$http.post("/mgr/delBanner/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/home/getBanner", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.items = response.body.data;
                    });
                });
            },
            cancelTop: function (id) {
                this.$http.post("/mgr/cancelTop/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/popularization/getTop", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.topItems = response.body.data;
                    });
                });
            },
            cancelRecommand: function (id) {
                this.$http.post("/mgr/cancelRecommand/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/popularization/getArticle/1", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.recommandItems = response.body.data;
                    });
                });
            },
            getArticle: function (show) {
                this.show = show;
                this.$http.post("/popularization/getArticle/" + this.show, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.articleItems = response.body.data;
                });
            },
            top: function (id) {
                this.$http.post("/mgr/setTop/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/popularization/getTop", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.topItems = response.body.data;
                    });
                    this.$http.post("/popularization/getArticle/1", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.recommandItems = response.body.data;
                    });
                });
            },
            recommand: function (id) {
                this.$http.post("/mgr/setRecommand/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/popularization/getTop", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.topItems = response.body.data;
                    });
                    this.$http.post("/popularization/getArticle/1", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.recommandItems = response.body.data;
                    });
                });

            },
            delTip: function (id) {
                this.$http.post("/mgr/delTip/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/home/getAllTip", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.tipItems = response.body.data;
                    });
                });
            }
        },
        created: function () {
            this.$http.post("/home/getBanner", {}, {}).then(function (response) {
                console.info(response.body);
                this.items = response.body.data;
            });

            this.$http.post("/popularization/getTop", {}, {}).then(function (response) {
                console.info(response.body);
                this.topItems = response.body.data;
            });

            this.$http.post("/popularization/getArticle/1", {}, {}).then(function (response) {
                console.info(response.body);
                this.recommandItems = response.body.data;
            });

            this.$http.post("/popularization/getArticle/" + this.show, {}, {}).then(function (response) {
                console.info(response.body);
                this.articleItems = response.body.data;
            });

            this.$http.post("/home/getAllTip", {}, {}).then(function (response) {
                console.info(response.body);
                this.tipItems = response.body.data;
            });
        }
    })
</script>
</body>
</html>
