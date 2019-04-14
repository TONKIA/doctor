package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.*;
import com.tonkia.rainbow.service.ArticleService;
import com.tonkia.rainbow.service.DoctorService;
import com.tonkia.rainbow.service.PopularizationService;
import com.tonkia.rainbow.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;


//假数据
@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    ArticleService articleService;

    @Autowired
    SearchService searchService;

    @Autowired
    PopularizationService popularizationService;

    @RequestMapping("keyword")
    @ResponseBody
    public ResponseMessage search(String keyWords) {
        SearchInfo searchInfo = new SearchInfo();
        if (keyWords != null)
            if (keyWords.contains("齐多夫定") || keyWords.contains("抗艾滋病药品")) {
                fill1(searchInfo);
            } else if (keyWords.contains("拉米夫定") || keyWords.contains("Lamivudine")) {
                fill2(searchInfo);
            } else if (keyWords.contains("司他夫定") || keyWords.contains("外周神经炎")) {
                fill3(searchInfo);
            } else if (keyWords.contains("奈韦拉平") || keyWords.contains("依非韦伦片")) {
                fill4(searchInfo);
            } else if (keyWords.contains("艾滋病")) {
                fill5(searchInfo);
            } else if (keyWords.contains("成都市第二人民医院")) {
                fill6(searchInfo);
            } else if (keyWords.contains("避孕套")) {
                fill7(searchInfo);
            } else if (keyWords.contains("润滑油")) {
                fill8(searchInfo);
            } else if (keyWords.contains("姜治明")) {
                fill9(searchInfo);
            } else if (keyWords.contains("梅毒")) {
                fill10(searchInfo);
            } else if (keyWords.contains("童医生")) {
                fill11(searchInfo);
            } else if (keyWords.contains("方文琪")) {
                fill12(searchInfo);
            } else
                fillNull(searchInfo);
        else
            fillNull(searchInfo);

        return new ResponseMessage("查询成功！", searchInfo, ResponseMessage.SUCCESS);
    }

    @RequestMapping("filter")
    @ResponseBody
    public ResponseMessage filter(FilterInfo filterInfo) {
        SearchInfo searchInfo = new SearchInfo();
        switch (filterInfo.getCategory()) {
            case 0:
                searchInfo.setHospital(searchService.filterHospital(filterInfo));
                searchInfo.setDoctor(searchService.filterDoctor(filterInfo));
                break;
            case 2:
                searchInfo.setHospital(searchService.filterHospital(filterInfo));
                break;
            case 1:
                searchInfo.setDoctor(searchService.filterDoctor(filterInfo));
                break;
        }

        return new ResponseMessage("查询成功！", searchInfo, ResponseMessage.SUCCESS);
    }

    @RequestMapping("hot")
    @ResponseBody
    public ResponseMessage hot() {
        String[] array = {"齐多夫定", "艾滋病", "成都市第二人民医院", "梅毒", "姜治明", "避孕套", "润滑油",};
        return new ResponseMessage("返回数据成功！", array, ResponseMessage.SUCCESS);
    }


    //假数据
    public void fill1(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"齐多夫定", "抗病毒药", "艾滋病", "抗艾滋病药品"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("齐多夫定为抗病毒药，用于艾滋病或与艾滋病有关的综合症患者及免疫缺陷病毒(HIV)感染的治疗。1987年3月19日，美国食品与药品管理署批准该药上市。齐多夫定是世界上第一个获得美国FDA批准生产的抗艾滋病药品，因其疗效确切，成为“鸡尾酒”疗法最基本的组合成分。该品为抗病毒药，在体外对逆转病毒包括人免疫缺陷病毒（HIV）具有高度活性。");
        searchInfo.setEncyclopedias(encyclopedias);
        //用药建议
        searchInfo.setMedicine("禁忌辛辣刺激性食物，禁烟酒。平时一定不要熬夜，注意休息。同时要增加锻炼，增强自己的免疫力。贫血患者或者是孕妇慎用。故治疗过程中应经常作血细胞计数(至少每2周1次)。如发生粒细胞减少或贫血，可能需要调整剂量。\n" +
                "齐多夫定--忌食辛辣、油腻食物。服用三天病证无改善，应停止服用，去医院就诊。药期间，若患者出现高热，或出现喘促气急者，或咳嗽加重，痰量明显增多者应到医院就诊对该药品过敏者禁用，过敏体质者慎用。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(1));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(1));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(1));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("齐多夫定真的有效吗？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("在哪里可以买到齐多夫定？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(2);
        adviceInfo.setTitle("齐多夫定有没有副作用？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill2(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"拉米夫定", "Lamivudine", "抗病毒药物"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("拉米夫定其英文名称为Lamivudine，化学名为2’3’-双脱氧-3’-硫代胞嘧啶(2’－3’deoxy-3’-thiocytidine)，又称3-TC，为白色或类白色结晶性粉末，在水中溶解，在甲醇中略溶，比旋度为-97°至-99°。是核苷类似物，抗病毒药物，对病毒DNA链的合成和延长有竞争性抑制作用。");
        searchInfo.setEncyclopedias(encyclopedias);
        //用药建议
        searchInfo.setMedicine("常见的副反应有上呼吸道感染样症状：疲倦、头痛、不适、胃痛及腹部不适等。可能出现轻度贫血、血不板和白细胞减少，肌无力、ALT、淀粉酶轻度增高，也可有低血糖、恶心和肌痛等。拉米夫定片有严重的耐药现象，服用拉米夫定片超过一年的乙肝患者会出现耐药现象。如果一旦停止服用该药物后，会使病情更加恶化。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(2));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(2));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(2));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("拉米夫定真的有效吗？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("在哪里可以买到拉米夫定？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(2);
        adviceInfo.setTitle("拉米夫定有没有副作用？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill3(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"司他夫定", "HIV", "外周神经炎"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("司他夫定，化学名3'-脱氧-2',3'-双脱氢胸苷；3'-脱氧-2',3'-双脱氢胸甙。无色颗粒状固体，熔点165--166℃，熔点174℃。UV最大吸收(水)：266nm(ε10149)。[α]D20-46.1°(C=0.7，水)。也有报道[α]D23 -39.4°(C=0.701，水)。用于治疗3个月至12岁的儿童HIV感染患者。 主要不良反应为外周神经炎，发生率与剂量相关。");
        searchInfo.setEncyclopedias(encyclopedias);
        //用药建议
        searchInfo.setMedicine("司他夫定--能产生外周神经病变。外周神经病变与剂量有关，有时呈重度，使用去羟肌苷等有神经毒性药物治疗的、HIV感染的晚期、有神经病变病史的患者，较易发生外周神经病变。需监控患者发生外周神经病变的毒性。外周神经病变主要表现为手足麻木刺痛。\n" +
                "司他夫定常见的副作用可能包括: 手或脚无力、麻木、刺痛或烧灼性疼痛；恶心、呕吐、腹泻；皮疹；或者 头痛。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(3));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(3));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(3));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("司他夫定真的有效吗？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("在哪里可以买到司他夫定？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(2);
        adviceInfo.setTitle("司他夫定有没有副作用？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill4(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"奈韦拉平", "耐药性", "抗病毒药物"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("奈韦拉平是一种抗病毒药物奈韦拉平是一种抗病毒药物，极容易产生耐药性，主要常见的副作用，有乏力发热，嗜睡，腹痛等，还有就是肝功能损害，有时还有肌肉关节的疼痛症状，这种药物必须在医生的指导下用药，不可以自行服药也不可以自行停药。");
        searchInfo.setEncyclopedias(encyclopedias);
        //用药建议
        searchInfo.setMedicine("依非韦伦片的副作用，会导致患者产生皮疹、出现水疱、脱屑、或者说发热、还会导致人出现精神，恍惚，产生幻觉的症状。如果需要用此药，需要谨遵医嘱服用。平时可以适当的吃一些富含维生素C的水果来辅助用药。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(4));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(4));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(4));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("奈韦拉平真的有效吗？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("在哪里可以买到奈韦拉平？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(2);
        adviceInfo.setTitle("奈韦拉平有没有副作用？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill5(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"血液传播", "性传播", "潜伏期长", "死亡率高"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("HIV是一种能攻击人体免疫系统的病毒，它把人体免疫系统中最重要的CD4T淋巴细胞作为主要攻击目标，大量破坏该细胞，使人体丧失免疫功能。因此，人体易于感染各种疾病，并可发生恶性肿瘤，病死率较高。HIV在人体内的潜伏期平均为8～9年，患艾滋病以前，可以没有任何症状地生活和工作多年。\n" +
                "治疗艾滋病的药物：一线药物有齐多夫定、拉米夫定、司他夫定，奈韦拉平和依非韦伦。二线药物主要有克力芝和替诺福韦。");
        searchInfo.setEncyclopedias(encyclopedias);
        //用药建议
        searchInfo.setMedicine("应该每半年检测血常规和肝功。建议不和别人共用同一个杯子，尽量做到自己的东西专用。\n" +
                "药物可能影响肝功能，要注意休息。睡眠不好，熬夜，喝酒等多种因素都会对肝功有一定的影响。避免吃辣椒食物、饮酒和吸烟。患者应少食多餐，不要一次吃的太多，导致消化不良的情况。选择蒸煮类的食物是比较好的，好消化。\n" +
                "多吃有益的高蛋白质食物：鱼虾类，如海水鱼、虾、墨鱼、贝、蟹等；家禽类，如鸡肉、鸽肉、兔肉；牛奶及乳制品，如优质奶酪；蛋类，如鸡蛋、鸭蛋；豆类，如豆腐、豆浆或其他豆制品；其他肉类。 注意补充维生素和矿物质应多吃新鲜的水果和蔬菜，特别是富含胡萝卜素(如菠菜、芥蓝、番薯、南瓜、胡萝卜)、维生素C(如青椒、橘子、绿菜花、菠菜)、维生素E(如榛子、松子、开心果、大杏仁)及含锌(如牡蛎、贝类、谷类)的食物。应尽量少吃高脂肪的食物（如油炸食品、肥肉、动物内脏）。 香菇也可增强免疫功能。");
        //饮食建议
        searchInfo.setRecommend("艾滋病食谱推荐\n" +
                "1、补虚正气粥\n" +
                "材料：炙黄芪30-60g，人参3-5g，白糖少许，粳米100-150g。\n" +
                "做法：先将黄芪、人参切成薄片，用冷水浸泡半小时，入砂锅煎沸，后改用小火煎成浓汁。把汤汁取出来，放入冷水，再继续煎。去掉渣，去渣，将一、二煎药液合并，分二份，每天早晚和粳米一起煮粥食用，煮粥后放入白糖少许，人参可以磨成粉放入粥里食用。\n" +
                "功效：补正气，疗虚损，健脾胃。\n" +
                "2、粳米阿胶粥\n" +
                "材料：阿胶20g(捣碎，文火炒至呈黄色，研为细末)，粳米100g，红糖适量。\n" +
                "做法：将淘洗干净的粳米放入锅内，加水适量，用大货继续煮沸以后，用效果继续煮到九成熟的时候，放入阿胶粉还有红糖继续煮，一般煮一遍搅拌均匀。\n" +
                "功效：养血止血。\n" +
                "3、虫草甲鱼\n" +
                "材料：虫草10-15g，甲鱼500g，调味料适量。\n" +
                "做法：把甲鱼洗干净以后去头把内脏洗干净，把虫草放入甲鱼里面，放入汤锅里面，放入适量的水，加入食盐。隔水放入锅里，用大货煮开以后，用小火继续煮到酥烂以后和药一起吃。\n" +
                "功效：滋阴凉血，补肺益肾。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(1));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(1));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(1));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("我和艾滋病的人一起睡觉，有事吗？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("艾滋病有什么症状啊？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(2);
        adviceInfo.setTitle("喝了艾滋病的血会怎么样？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill6(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"三甲医院", "综合医院", "医保定点"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("成都市第二人民医院位于成都中心城区锦江区府河畔，是一所三级甲等综合医院。 医院设有一级临床科室18个、一级医技科室10个、职能科室16个，其中，临床专业67个、医技专业14个。");
        searchInfo.setEncyclopedias(encyclopedias);
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(3));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(3));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(3));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("我经常半夜上厕所是什么原因？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("22岁还尿床正常吗？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(2);
        adviceInfo.setTitle("我高烧不退是不是感染艾滋病了？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill7(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"方便", "安全", "卫生", "舒适"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("避孕套(Condoms)是以非药物的形式阻止受孕，主要用于在性交中阻止人类的精子和卵子结合，防止怀孕。除此之外，避孕套也有防止淋病、艾滋病(HIV)等性病传播的作用，因此也称安全套，现在的避孕套原料通常是用天然橡胶或聚亚安酯。");
        searchInfo.setEncyclopedias(encyclopedias);
        //建议
        searchInfo.setRecommend("随着安全套制作材料和制作工艺的不断改进，现在销售的安全套比以前薄了许多，弹性和韧性也比以前提高了许多倍“使用安全套会降低快感”的担心已不再是一个问题。有男同反应，在带上安全套前在龟头上一点润滑剂能提升快感。\n" +
                "传统天然乳胶避孕套在有效避孕的同时，虽然对病毒有一定阻隔作用，但不能绝对地预防病毒感染。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(2));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(2));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(2));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("避孕套破裂了，怎么办？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("女用避孕套比男用避孕套安全性更好一些吗？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill8(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"润滑", "愉悦", "方便", "安全"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("人体润滑油以甘油和亲水物质为主要的成分，后来改良后加入玻璃酸钠、乙基纤维素、卡波母等成分，改进后减少粘腻性，平衡酸碱度，使其尽量与人体体液相似，减少了过敏症状的发生机率。");
        searchInfo.setEncyclopedias(encyclopedias);
        //建议
        searchInfo.setRecommend("在做爱过程中使用人体润滑油能够减少损伤的发生。\n" +
                "安全套和润滑油同时使用是没问题的，但必须要用水溶性的人体润滑油。");
        //医生
        List<DoctorInfo> doctor = new LinkedList<>();
        doctor.add(doctorService.getDoctorInfoById(4));
        searchInfo.setDoctor(doctor);
        //医院
        List<HospitalInfo> hospital = new LinkedList<>();
        hospital.add(searchService.getHospitalById(4));
        searchInfo.setHospital(hospital);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(4));
        searchInfo.setArticle(article);
        //建议
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("使用润滑油会过敏吗？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fill9(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"医学博士", "彩虹名医", "传染病", "艾滋病"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("姜治明，医学博士，主治医师。南京市北山路医院传染病科主任、南京市艾滋病预防控制中心主任。现兼任南京医科大学研究生导师。");
        searchInfo.setEncyclopedias(encyclopedias);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(5));
        searchInfo.setArticle(article);

    }

    public void fill11(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"医学博士", "经验丰富", "主治医师"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("湿疹、荨麻疹、激素依赖性皮炎等过敏性皮肤病，疣、带状疱疹等病毒性皮肤病，尖锐湿疣、梅毒、淋病、生殖器疱疹等性传播疾病，手癣、足癣、花斑癣、股癣、体癣、甲癣等浅部真菌感染性疾病。");
        searchInfo.setEncyclopedias(encyclopedias);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(4));
        searchInfo.setArticle(article);

    }

    public void fill12(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"彩虹名医", "艾滋病"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("方文琪，主治艾滋病，梅毒，生殖器瘙痒，前列腺炎等");
        searchInfo.setEncyclopedias(encyclopedias);
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(3));
        searchInfo.setArticle(article);

    }

    public void fill10(SearchInfo searchInfo) {
        //百科
        SearchInfo.Encyclopedias encyclopedias = new SearchInfo().new Encyclopedias();
        String[] label = {"血液传播", " 性传播", "潜伏期长", " 病程长"};
        encyclopedias.setLabelArray(label);
        encyclopedias.setSummary("梅毒是由苍白（梅毒）螺旋体引起的慢性、系统性性传播疾病。主要通过性途径传播，部分为间接接触传染，输血、哺乳及胎盘也可传播。临床上可表现为一期梅毒、二期梅毒、三期梅毒、潜伏梅毒和先天梅毒（胎传梅毒）等。");
        searchInfo.setEncyclopedias(encyclopedias);
        //建议
        searchInfo.setRecommend("患者需要注意休息，避免劳累。饮食清淡易消化，吃新鲜富含维生素的蔬菜、水果，少吃油腻的饮食，忌食辛辣刺激食物，不要吃烧烤和油炸食物，戒烟酒，适当多饮水，有利于体内毒素的排除。要注意个人卫生。内衣内裤要注意隔离。");
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(1));
        searchInfo.setArticle(article);
        List<SearchInfo.AdviceInfo> advice = new LinkedList<>();
        SearchInfo.AdviceInfo adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(0);
        adviceInfo.setTitle("梅毒会通过哪些途径传播？");
        advice.add(adviceInfo);
        adviceInfo = new SearchInfo().new AdviceInfo();
        adviceInfo.setAdviceId(1);
        adviceInfo.setTitle("三期梅毒就没有救了吗？");
        advice.add(adviceInfo);
        searchInfo.setAdvice(advice);
    }

    public void fillNull(SearchInfo searchInfo) {

        searchInfo.setMessage("暂无匹配的搜索结果!");
        //文章
        List<ArticleInfo> article = new LinkedList<>();
        article.add(articleService.getArticleById(4));
        searchInfo.setArticle(article);

    }

}
