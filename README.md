# doctor
友好医生比赛项目，后端

# 下载
<img src="https://github.com/TONKIA/doctor/blob/master/img/download1.jpg?raw=true" />

# 界面
<div>
<img src="https://github.com/TONKIA/doctor/blob/master/img/0.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/1.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/2.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/3.jpg?raw=true" width="23%"/>
</div>
<div>
<img src="https://github.com/TONKIA/doctor/blob/master/img/4.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/5.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/6.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/7.jpg?raw=true" width="23%"/>
</div>
<div>
<img src="https://github.com/TONKIA/doctor/blob/master/img/8.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/9.jpg?raw=true" width="23%"/>
<img src="https://github.com/TONKIA/doctor/blob/master/img/10.jpg?raw=true" width="23%"/>
</div>

# 后端
[医生登录](http://doctor.tonkia.xyz/login.jsp)

# 接口

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
<td>/user/login</td>
<td>
phoneNumber<br/>
token<br/>
</td>
</tr>
<tr>
<td>/file/uploadImg</td>
</tr>
<tr>
<td>/file/getImg/{fileName}</td>
<td>
获取文件接口，fileName为文件名。
</td>
</tr>
<tr>
<td>/home/getBanner</td>
<td>
获取所有的Banner
img 图片url<br/>
url 跳转网页链接<br/>
</td>
</tr>
<tr>
<td>/home/getHome</td>
<td>
获取其他主页信息：推荐医生和首页文章<br/>
doctorNumber 获取几个医生<br/>
articleNumber 获取几篇文章<br/>
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
<td>/psychology/getBanner</td>
<td>获取Banner</td>
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
<td>/psychology/getSaying/{sayingId}</td>
<td>
Integer sayingId
</td>
</tr>
<tr>
<td>/psychology/comment</td>
<td>
phoneNumber, token, sayingId, content
</td>
</tr>
<tr>
<td>/psychology/thumbup</td>
<td>
String phoneNumber, String token, Integer sayingId
</td>
</tr>
<tr>
<td>/psychology/publish</td>
<td>
String phoneNumber, String token, String content,Integer category, imgUrl<br/>
</td>
</tr>
<tr>
<td>/search/keyword</td>
<td>
搜索接口</br>
</td>
</tr>
<tr>
<td>/search/hot</td>
<td>
获取热门搜索
</td>
</tr>
<tr>
<td>/user/info/{uid}</td>
<td>
获取 nikename 和 avator
</td>
</tr>
<tr>
<td>/doctor/isFocus</td>
<td>
是否关注</br>
参数 String phoneNumber, String token, Integer doctorId</br>
</td>
</tr>
<tr>
<td>/doctor/focus</td>
<td>
关注</br>
参数 String phoneNumber, String token, Integer doctorId</br>
</td>
</tr>
<tr>
<td>/article/thumbup</td>
<td>
文章点赞</br>
String phoneNumber, String token, Integer articleId</br>
</td>
</tr>
<tr>
<td>/article/isThumbup</td>
<td>
文章是否被点赞</br>
String phoneNumber, String token, Integer articleId</br>
</td>
</tr>
<tr>
<td>/user/detailInfo</td>
<td>
获取用户主页的信息 点赞 关注 评论</br>
String phoneNumber, String token</br>
</td>
</tr>
<tr>
<td>/article/comment</td>
<td>
文章评论接口<br/>
phoneNumber ,token ,articleId, content<br/>
</td>
</tr>
<tr>
<td>/article/comment/{articleId}</td>
<td>
获取文章评论接口 时间降序
</td>
</tr>
<tr>
<td>/doctor/comment</td>
<td>
医生服务评论接口<br/>
phoneNumber ,token ,doctorId, content<br/>
</td>
</tr>
<tr>
<td>/doctor/comment/{doctorId}</td>
<td>
获取医生评论接口 时间降序
</td>
</tr>
<tr>
<td>/user/modifyAvator</td>
<td>
修改头像<br/>
String phoneNumber, String token, MultipartFile file<br/>
</td>
</tr>
<tr>
<td>/user/modifyNikeName</td>
<td>
修改昵称<br/>
String phoneNumber, String token, String nikeName<br/>
</td>
</tr>
<tr>
<td>/search/filter</td>
<td>
筛选<br/>
</td>
</tr>
<tr>
<td>/popularization/search</td>
<td>
科普文章搜索<br/>
参数keyword<br/>
</td>
</tr>
<tr>
<td>/calendar/addItem</td>
<td>
添加日历<br/>
</td>
</tr>
<tr>
<td>/calendar/getItem</td>
<td>
添加获取<br/>
</td>
</tr>
</table>
