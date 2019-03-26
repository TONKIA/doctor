<%--
  Created by IntelliJ IDEA.
  User: TONKIA
  Date: 2019/3/20
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
</head>
<body style="background-color: #3b4249">
<div id="app">
    <div v-if="doctorInfo==null" style="color: #ffffff;margin: 50px">

        <h1>申请成为医生</h1>
        <div class="form-group">
            <label>擅长：</label>
            <textarea class="form-control" rows="3" v-model="expert"></textarea>
        </div>

        <div class="form-group">
            <label>医院：</label>
            <input type="text" class="form-control" rows="3" v-model="hospital"></input>
        </div>
        <div class="form-group">
            <label>职位</label>
            <select class="form-control" v-model="qualificate">
                <option>主任医师</option>
                <option>副主任医师</option>
                <option>主治医师</option>
                <option>医师</option>
            </select>
        </div>
        <button class="btn btn-primary" @click="registryDoctor">提交</button>

    </div>
    <%--主页--%>
    <div v-if="doctorInfo!=null">
        <v-container>
            <v-layout justify-center>
                <v-flex xs12 sm12 md6>
                    <v-card>
                        <v-toolbar>
                            <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
                        </v-toolbar>
                        <person :doctor="doctorInfo" v-if="bottomNav=='person'"></person>

                        <passage :doctor="doctorInfo" v-if="bottomNav=='passage'"></passage>


                        <v-bottom-nav
                                :active.sync="bottomNav"
                                :value="true"
                                absolute
                                color="transparent"
                        >
                            <v-btn
                                    color="teal"
                                    flat
                                    value="person"
                            >
                                <span>我的</span>
                                <v-icon>person</v-icon>
                            </v-btn>
                            <v-btn
                                    color="teal"
                                    flat
                                    value="passage"
                            >
                                <span>文章</span>
                                <v-icon>favorite</v-icon>
                            </v-btn>
                        </v-bottom-nav>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>


        <%--侧边栏--%>
        <v-navigation-drawer
                v-model="drawer"
                absolute
                temporary
        >
            <v-list class="pa-1">
                <v-list-tile avatar>
                    <v-list-tile-avatar>
                        <img :src="doctorInfo.avator">
                    </v-list-tile-avatar>

                    <v-list-tile-content>
                        <v-list-tile-title>{{doctorInfo.nikeName}}</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
            </v-list>

            <v-list class="pt-0" dense>
                <v-divider></v-divider>

                <v-list-tile @click="">
                    <v-list-tile-action>
                        <v-icon>edit</v-icon>
                    </v-list-tile-action>

                    <v-list-tile-content>
                        <v-list-tile-title>修改用户信息</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>

                <v-list-tile @click="">
                    <v-list-tile-action>
                        <v-icon>question_answer</v-icon>
                    </v-list-tile-action>

                    <v-list-tile-content>
                        <v-list-tile-title>关于彩虹医生</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>

            </v-list>
        </v-navigation-drawer>
    </div>
</div>
<template id="person">
    <div>
        <v-card-text>
            {{doctor}}
        </v-card-text>

        <v-card-text style="height: 100px">

        </v-card-text>
    </div>
</template>
<template id="passage">
    <div>
        <v-card-text class="grey lighten-5">
            <v-list two-line>
                <template v-for="item in articleItems">
                    <v-list-tile
                            :key="item.articleId"
                            avatar
                            @click="openArticle(item.articleId)"
                    >
                        <v-list-tile-avatar>
                            <img :src="item.picUrl">
                        </v-list-tile-avatar>

                        <v-list-tile-content>
                            <v-list-tile-title v-html="item.title"></v-list-tile-title>
                            <v-list-tile-sub-title v-html="item.summary"></v-list-tile-sub-title>
                        </v-list-tile-content>
                        <v-btn outline color="indigo" @click.stop="delArticle(item.articleId)">删 除</v-btn>
                    </v-list-tile>
                </template>
            </v-list>
        </v-card-text>
        <v-card-text style="height: 100px; position: relative">
            <v-btn
                    absolute
                    dark
                    fab
                    top
                    right
                    color="pink"
                    @click="addArticle"
            >
                <v-icon>add</v-icon>
            </v-btn>
        </v-card-text>
    </div>

</template>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.js"></script>
<script>
    Vue.http.options.emulateJSON = true;
    Vue.http.options.emulateHTTP = true;


    Vue.component('person', {
        template: '#person',
        data: function () {
            return {}
        },
        props: ["doctor"]
    });

    Vue.component('passage', {
        template: '#passage',
        data: function () {
            return {
                articleItems: null
            }
        },
        methods: {
            addArticle: function () {
                window.open('/article.jsp');
            },
            openArticle: function (id) {
                window.open('/article/' + id);
            },
            delArticle: function (id) {
                this.$http.post("/doctor/delArticle/" + id, {}, {}).then(function (response) {
                    console.info(response.body);
                    this.$http.post("/doctor/getAllArticle", {}, {}).then(function (response) {
                        console.info(response.body);
                        this.articleItems = response.body.data;
                    })
                })
            }
        },
        created: function () {
            this.$http.post("/doctor/getAllArticle", {}, {}).then(function (response) {
                console.info(response.body);
                this.articleItems = response.body.data;
            })
        }
    });

    new Vue({
        el: '#app',
        data: {
            expert: null,
            qualificate: null,
            doctorInfo: null,
            hospital: null,
            bottomNav: 'person',
            drawer: false
        },
        methods: {
            getDoctorInfo: function () {
                this.$http.post("/doctor/getDoctorInfo", {}, {}).then(function (response) {
                    console.info(response.body);
                    this.doctorInfo = response.body.data;
                })
            },
            registryDoctor: function () {
                this.$http.post("/doctor/registryDoctor", {
                    expert: this.expert,
                    qualificate: this.qualificate,
                    hospital: this.hospital
                }, {}).then(function (response) {
                    console.info(response.body);
                    this.doctorInfo = response.body.data;
                })
            }
        }
        ,
        created: function () {
            this.getDoctorInfo()
        }
    })
</script>
</body>
</html>
