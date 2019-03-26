<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户登录</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
</head>
<body>

<div id="app">
    <v-app>
        <v-content>
            <v-container fluid fill-height>
                <v-layout align-center justify-center>
                    <v-flex xs12 sm8 md4>
                        <v-card class="elevation-12">
                            <v-toolbar dark color="primary">
                                <v-toolbar-title>医生登录</v-toolbar-title>
                            </v-toolbar>
                            <v-card-text>
                                <v-form>
                                    <v-text-field prepend-icon="phone" label="手机号" v-model="phoneNumber"
                                                  type="text"></v-text-field>
                                    <v-text-field prepend-icon="message" label="验证码" v-model="smsCode"
                                                  type="text"></v-text-field>
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="primary" @click="getSmsCode">获取验证码</v-btn>
                                <v-btn color="primary" @click="login">登 录</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.js"></script>
<script>
    Vue.http.options.emulateJSON = true;
    Vue.http.options.emulateHTTP = true;
    new Vue({
        el: '#app',
        data: {phoneNumber: "", smsCode: ""},
        methods: {
            getSmsCode: function () {
                if (this.phoneNumber.length == 11) {
                    this.$http.post("/user/getSmsForLogin/" + this.phoneNumber, {}, {}).then(function (response) {
                        console.info(response.body);
                    });
                }
            },
            login: function () {
                this.$http.post("/user/loginWithSms", {
                    phoneNumber: this.phoneNumber,
                    smsCode: this.smsCode
                }, {}).then(function (response) {
                    console.info(response.body);
                    if (response.body.code == 1) {
                        localStorage.setItem("phoneNumber", this.phoneNumber);
                        localStorage.setItem("token", response.body.data.token);
                        this.autoLogin();
                    }
                });
            },
            autoLogin: function () {
                var phoneNumber = localStorage.getItem("phoneNumber");
                var token = localStorage.getItem("token");
                if (phoneNumber != null && token != null)
                    this.$http.post("/user/login", {
                        phoneNumber: phoneNumber,
                        token: token
                    }, {}).then(function (response) {
                        console.info(response.body);
                        if (response.body.code == 1) {
                            window.location.href = "home.jsp";
                        }
                    });
            }
        },
        created: function () {
            this.autoLogin();
        }
    })
</script>

</body>
</html>
