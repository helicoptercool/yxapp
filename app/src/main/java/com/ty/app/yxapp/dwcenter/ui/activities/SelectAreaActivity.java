package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.VideoChatFragment;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectAreaActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView selectLv;
    private ArrayAdapter<String> adapter;
    private List<String> names;
    private String from;

    private String[] areaArr = {"大洼区",
            "大洼职能部门"};

    private String[] streetArr = {"大洼街道",
            "田家街道",
            "榆树街道",
            "前进街道",
            "向海街道",
            "西安镇",
            "新兴镇",
            "东风镇",
            "新开镇",
            "新立镇",
            "清水镇",
            "唐家镇",
            "赵圈河镇",
            "平安镇",
            "三角洲开发区",
            "网格中心"};

    private String[] streetArr1 = {"发改局",
            "经信局",
            "教育局",
            "公安局",
            "司法局",
            "财政局",
            "人社局",
            "国土资源局",
            "交通局",
            "农经局",
            "水利局",
            "服务业局",
            "安监局",
            "规划局",
            "征收办",
            "宜居办",
            "国税局",
            "地税局",
            "气象局",
            "人民银行",
            "移动公司",
            "联通公司",
            "社保分局",
            "电信公司",
            "政法委",
            "统计局",
            "市场监管局",
            "文旅局",
            "卫计局",
            "民政局",
            "供电分公司",
            "环保局",
            "城市建设（集团）",
            "住建局",
            "综合执法局",
            "公交公司"};

    private String[] v01 = {"新兴社区",
            "向阳社区",
            "站前社区",
            "四新社区",
            "东升社区",
            "瀛路社区",
            "桥南社区",
            "兴盛社区",
            "永安社区",
            "繁荣社区",
            "生产社区",
            "振兴社区",
            "欣荣社区",
            "兴顺社区",
            "新园社区",
            "小堡子村",
            "城市管理办公室",
            "文化旅游服务中心",
            "农业服务中心",
            "综合治理办公室",
            "司法所",
            "网格中心"};
    private String[] v02 = {"毛家社区",
            "大堡子村",
            "马圈子社区",
            "田家社区",
            "小洼社区",
            "大高家社区",
            "昆仑社区",
            "盛田社区",
            "欣田社区",
            "东方社区",
            "城市管理办",
            "服务业提升办",
            "宜居乡村",
            "派出所",
            "交警中队",
            "消防站",
            "党群办",
            "网格管理服务中心",
            "公共事业服务站",
            "林海社区"};
    private String[] v03 = {"曾家村",
            "西榆社区",
            "新立社区",
            "郭家社区",
            "兴旺社区",
            "青联社区",
            "榆茂社区",
            "清凤社区",
            "郑家社区",
            "罗家湾",
            "兴海村",
            "建海村",
            "城市管理办公室",
            "城市管理办（城管）",
            "城市管理办（公共事业服务站）",
            "自来水服务站",
            "水利服务站",
            "农业中心",
            "物业办公室",
            "党建中心",
            "综合治理办公室",
            "党群办公室",
            "社会服务办公室",
            "社区服务办（社保所）",
            "社会服务办（民政）",
            "社会服务办（民教）",
            "安管办公室",
            "信访办公室",
            "卫生院",
            "燃气办公室",
            "榆树街道网格中心"};
    private String[] v04 = {"瑞田社区",
            "小锅社区",
            "三十里村",
            "何家村",
            "顾家村",
            "高家村",
            "关家村",
            "宜居乡村",
            "市政设施",
            "路灯管理",
            "环境卫生",
            "农业服务中心",
            "双海社区",
            "碧城社区",
            "香水湖社区",
            "网格管理服务中心"};
    private String[] v05 = {"东三社区",
            "永兴社区",
            "惠安社区",
            "胜利社区",
            "石庙子村",
            "王家村",
            "永盛社区",
            "宝兴社区",
            "双兴社区",
            "永昌社区",
            "曙光村",
            "西海村",
            "华侨村",
            "农业中心",
            "公共事业管理服务中心",
            "医院",
            "物业",
            "司法所",
            "派出所",
            "自来水",
            "市场监督管理所",
            "向海街道网格中心"};
    private String[] v06 = {"桃源村",
            "八家子村",
            "韩家村",
            "小洼村",
            "刘家村",
            "桑林子村",
            "高坎村",
            "洼边子村",
            "上口子村",
            "高坎湾村",
            "小亮沟村",
            "王家塘村",
            "党群办公室",
            "综合办公室",
            "经济发展办公室",
            "社会服务办公室",
            "宜居乡村建设办公室",
            "综合治理办公室",
            "财政所（核算中心）",
            "农业服务中心",
            "派出所",
            "西安镇网格中心"};
    private String[] v07 = {"王家村",
            "两棵树",
            "园林社区",
            "坨子里村",
            "红草沟村",
            "育新村",
            "躺岗子村",
            "腰岗子村",
            "子岗子村",
            "宜居乡村建设办",
            "社会服务办",
            "经济发展办",
            "党群办",
            "综合治理办",
            "综合办",
            "财政所",
            "农业服务中心",
            "党建中心",
            "网格管理服务中心"};
    private String[] v08 = {"大岗子村",
            "河沿村",
            "腰屯村",
            "驾掌寺村",
            "栾家村",
            "马家村",
            "叶家村",
            "二道边村",
            "东风村",
            "黄金带村",
            "大北屯村",
            "水库村",
            "宜居办",
            "派出所",
            "司法所",
            "水利站",
            "自来水",
            "东风镇网格中心"};
    private String[] v09 = {"曲家村",
            "田家村",
            "西武村",
            "张家村",
            "史家村",
            "胥家村",
            "铁南村",
            "八家村",
            "于楼村",
            "新开街道",
            "司法所",
            "城管中队",
            "宜居管理办公室",
            "水利服务站",
            "路灯管理站",
            "园林绿化管理办公室",
            "派出所",
            "道路管理办公室",
            "农业技术服务中心",
            "自来水服务站"};
    private String[] v010 = {"史家村",
            "唐家村",
            "苏家村",
            "杨家村",
            "孙家村",
            "云家村",
            "张家村",
            "新欣社区",
            "宜居办",
            "派出所",
            "水利站",
            "自来水",
            "新立街道网格中心"};
    private String[] v011 = {"立新村 ",
            "大清村",
            "小清村",
            "清河村",
            "育红村",
            "江南村",
            "五岔村",
            "南岗子村",
            "锦红村",
            "永红村",
            "宜居办",
            "派出所",
            "司法所",
            "水利站",
            "自来水",
            "清水镇网格中心"};
    private String[] v012 = {"北窑村",
            "小房村",
            "朱家村",
            "百家村",
            "唐家村",
            "袁家村",
            "杜家村",
            "新建社区",
            "刘家村",
            "四十里村",
            "陈家村",
            "葛家村",
            "环卫办",
            "镇建办",
            "交通办",
            "司法所",
            "派出所",
            "社会服务办",
            "唐家镇网格中心"};
    private String[] v013 = {"红塔村",
            "圈河村",
            "兰石村",
            "园林村",
            "兴盛村",
            "公共事业管理站",
            "自来水",
            "司法所",
            "派出所",
            "农业站",
            "水利站",
            "服务业办公室",
            "物业办",
            "向阳分公司",
            "兴和分公司",
            "小台子分公司",
            "红旗分公司",
            "建设分公司",
            "星海分公司",
            "双星分公司",
            "网格管理中心",
            "红塔分公司"};
    private String[] v014 = {"小房村",
            "新鑫村",
            "哈吧村",
            "建设村",
            "平安村",
            "大房村",
            "大亮村",
            "平房村",
            "新屯村",
            "曹蔡村",
            "宜居站",
            "综合办公室",
            "自来水",
            "派出所",
            "司法所",
            "社会服务办",
            "经济办",
            "市场监管所",
            "网格管理服务中心"};
    private String[] v015 = {"无下属部门"};
    private String[] v016 = {"办公室",
            "联动指挥中心",
            "发展规划股",
            "数据资源股",
            "信息安全股",
            "监督考评股",
            "电子政务股"};

    private String[] v11 = {"新兴社区",
            "向阳社区",
            "站前社区",
            "四新社区",
            "东升社区",
            "瀛路社区",
            "桥南社区",
            "兴盛社区",
            "永安社区",
            "繁荣社区",
            "生产社区",
            "振兴社区",
            "欣荣社区",
            "兴顺社区",
            "新园社区",
            "小堡子村",
            "城市管理办公室",
            "文化旅游服务中心",
            "农业服务中心",
            "综合治理办公室",
            "司法所",
            "网格中心"};
    private String[] v12 = {"大洼街道、向海街道、榆树镇工业企业",
            "赵圈河镇、清水镇、新兴镇、田家街道工业企业",
            "东风镇、西安镇、平安镇工业企业",
            "新立镇、新开镇、唐家镇、前进街道工业企业"};
    private String[] v13 = {"大洼区城郊学校",
            "大洼区田家学校",
            "大洼区榆树学校",
            "大洼区西安学校",
            "大洼区新兴学校",
            "大洼区东风学校",
            "大洼区新开学校",
            "大洼区新立学校",
            "大洼区清水学校",
            "大洼区唐家学校",
            "大洼区新建学校",
            "大洼区平安学校",
            "大洼区王家学校",
            "大洼区赵圈河学校",
            "朝鲜族学校",
            "大洼区第一初级中学",
            "大洼区第二初级中学学校",
            "大洼区实验小学",
            "大洼区第三小学"};
    private String[] v14 = {"治安大队"};
    private String[] v15 = {"大洼司法所",
            "田家司法所",
            "榆树司法所",
            "向海司法所",
            "前进司法所",
            "西安司法所",
            "新兴司法所",
            "东风司法所",
            "新开司法所",
            "新立司法所",
            "清水司法所",
            "唐家司法所",
            "平安司法所",
            "赵圈河司法所"};
    private String[] v16 = {"农业综合开发办",
            "行政事业性收费办公室",
            "财源建设办公室",
            "国库集中收付中心",
            "行政经费收付中心",
            "中小企业信用担保中心"};
    private String[] v17 = {"福利待遇、工资、工龄、参保、退休、特殊工种", "就业培训、劳动维权"};
    private String[] v18 = {"大洼镇",
            "临港经济区，榆树镇，平安镇",
            "三角洲开发区，清水镇，赵圈河镇",
            "新立镇，新开镇",
            "田家镇，新兴镇",
            "西安镇，东风镇",
            "王家镇，唐家镇",
            "不动产登记中心"};
    private String[] v19 = {"无下属部门"};
    private String[] v110 = {"西安镇",
            "新兴镇",
            "东风镇",
            "新开镇",
            "新立镇",
            "清水镇",
            "唐家镇",
            "平安镇",
            "赵圈河镇",
            "大洼街道",
            "田家街道",
            "榆树街道",
            "前进街道",
            "向海街道",
            "三角洲开发区",
            "农监局"};
    private String[] v111 = {"无下属部门"};
    private String[] v112 = {"成品油管理", "酒类管理", "散装水泥管理", "煤炭经营监督管理"};
    private String[] v113 = {"无下属部门"};
    private String[] v114 = {"无下属部门"};
    private String[] v115 = {"第一征收中心", "第二征收中心", "第三征收中心", "第四征收中心"};
    private String[] v116 = {"城市网格", "农村网格"};
    private String[] v117 = {"唐家镇、新开镇、新立镇",
            "田家镇、新兴镇",
            "榆树镇、王家镇、临港经济区",
            "东风镇、平安镇、西安镇",
            "大洼镇",
            "清水镇、赵圈河镇"};
    private String[] v118 = {"唐家镇、新开镇、新立镇",
            "田家镇、新兴镇",
            "榆树镇、王家镇、临港经济区",
            "东风镇、平安镇、西安镇",
            "大洼镇",
            "清水镇、赵圈河镇"};
    private String[] v119 = {"大洼镇",
            "王家镇",
            "榆树镇",
            "平安镇",
            "西安镇",
            "东风镇",
            "新开镇",
            "新立镇",
            "唐家镇",
            "田家镇",
            "新兴镇",
            "清水镇",
            "赵圈河镇"};
    private String[] v120 = {"无下属部门"};
    private String[] v121 = {"无下属部门"};
    private String[] v122 = {"无下属部门"};
    private String[] v123 = {"无下属部门"};
    private String[] v124 = {"无下属部门"};
    private String[] v125 = {"无下属部门"};
    private String[] v126 = {"无下属部门"};
    //25 政法委 无三级部门
    //26统计局 无三级部门
    private String[] v127 = {"大洼监督管理所",
            "田家监督管理所",
            "榆树（临港）监督管理所",
            "向海监督管理所",
            "西安监督管理所",
            "新兴监督管理所",
            "东风监督管理所",
            "新开监督管理所",
            "新立监督管理所",
            "清水监督管理所",
            "唐家监督管理所",
            "平安监督管理所",
            "圈河（红海滩）监督管理所",
            "王家监督管理所",
            "投诉举报中心（网监中心）",
            "大洼县计量测试所",
            "大洼县产品质量监督检验所",
            "大洼县锅炉压力容器检验所",
            "政策法规股",
            "食品生产管理股",
            "食品流通管理股",
            "餐饮服务管理股",
            "企业监督管理股",
            "市场合同监督管理股",
            "商标广告监督管理股",
            "网络经营监督管理股",
            "消费者权益保护股"};
    private String[] v128 = {"文化建设",
            "非遗保护",
            "文物保护",
            "文化市场",
            "电影放映",
            "有线电视安装维护",
            "区域旅游"};
    private String[] v129 = {"区局"};
    private String[] v130 = {"大洼街道",
            "田家街道",
            "榆树街道",
            "向海街道",
            "前进街道",
            "西安镇",
            "新兴镇",
            "东风镇",
            "新开镇",
            "新立镇",
            "清水镇",
            "唐家镇",
            "平安镇",
            "赵圈河镇"};
    private String[] v131 = {"大洼镇",
            "田庄台镇、平安镇",
            "清水镇",
            "新开镇",
            "王家镇",
            "田家镇",
            "东风镇",
            "西安镇",
            "新兴镇",
            "榆树镇、二界沟镇",
            "新立镇",
            "赵圈河镇",
            "荣兴镇",
            "唐家镇"};
    private String[] v132 = {"新开环保所", "西安环保所", "新兴环保所", "大洼环保所"};
    private String[] v133 = {"无下属部门"};
    private String[] v134 = {"小区物业管理（供暖、维修资金）",
            "城区基础设施建设",
            "房屋质量管理",
            "建筑安全管理",
            "城镇燃起管理",
            "城镇供水管理",
            "产权产籍管理"};
    private String[] v135 = {"大洼街道执法大队",
            "田家街道执法大队",
            "榆树街道执法大队",
            "前进街道执法大队",
            "向海街道执法大队",
            "西安镇执法大队",
            "新兴镇执法大队",
            "东风镇执法大队",
            "新开镇执法大队",
            "新立镇执法大队",
            "清水镇执法大队",
            "唐家镇执法大队",
            "平安镇执法大队",
            "赵圈河镇执法大队",
            "局属执法大队",
            "局属机动1队",
            "局属机动2队"};
    private String[] v136 = {"无下属部门"};

    private ArrayList<String[]> village = new ArrayList<>();
    private ArrayList<String[]> village1 = new ArrayList<>();

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        if(actionBar != null){
            actionBar.setVisibility(View.VISIBLE);
            actionBar.setCenterView(AndroidUtils.getString(R.string.choice));
        }
        from = getIntent().getStringExtra("from");
        names = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        addVillages();
        initItems(from);
        View view = getLayoutInflater().inflate(R.layout.activity_select_area,null);
        selectLv = (ListView) view.findViewById(R.id.select_lv);
        selectLv.setAdapter(adapter);
        selectLv.setOnItemClickListener(this);
        return view;
    }

    private void addVillages() {
        village.add(v01);
        village.add(v02);
        village.add(v03);
        village.add(v04);
        village.add(v05);
        village.add(v06);
        village.add(v07);
        village.add(v08);
        village.add(v09);
        village.add(v010);
        village.add(v011);
        village.add(v012);
        village.add(v013);
        village.add(v014);
        village.add(v015);
        village.add(v016);

        village1.add(v11);
        village1.add(v12);
        village1.add(v13);
        village1.add(v14);
        village1.add(v15);
        village1.add(v16);
        village1.add(v17);
        village1.add(v18);
        village1.add(v19);
        village1.add(v110);
        village1.add(v111);
        village1.add(v112);
        village1.add(v113);
        village1.add(v114);
        village1.add(v115);
        village1.add(v116);
        village1.add(v117);
        village1.add(v118);
        village1.add(v119);
        village1.add(v120);
        village1.add(v121);
        village1.add(v122);
        village1.add(v123);
        village1.add(v124);
        village1.add(v125);
        village1.add(v126);
        village1.add(v127);
        village1.add(v128);
        village1.add(v129);
        village1.add(v130);
        village1.add(v131);
        village1.add(v132);
        village1.add(v133);
        village1.add(v134);
        village1.add(v135);
        village1.add(v136);
    }

    public void initItems(String from) {
        names.clear();
        switch (from) {
            case "area":
                for (int i = 0; i < areaArr.length; i++) {
                    names.add(areaArr[i]);
                }
                break;
            case "street":
                int index = getIntent().getIntExtra("index", 0);
                switch (index) {
                    case 0:
                        for (int i = 0; i < streetArr.length; i++) {
                            names.add(streetArr[i]);
                        }
                        break;
                    case 1:
                        for (int i = 0; i < streetArr1.length; i++) {
                            names.add(streetArr1[i]);
                        }
                        break;
                }
                break;
            case "village":
                String areaStreet = getIntent().getStringExtra("areaStreet");
                String[] areaStr = areaStreet.split(",");
                int n = 0;
                if (areaStr[0].equals("大洼区")) {
                    for (int i = 0; i < streetArr.length; i++) {
                        if (areaStr[1].equals(streetArr[i])) {
                            n = i;
                            break;
                        }
                    }
                    String[] v = village.get(n);
                    Collections.addAll(names, v);
                } else {
                    for (int k = 0; k < streetArr1.length; k++) {
                        if (areaStr[1].equals(streetArr1[k])) {
                            n = k;
                            break;
                        }
                    }
                    String[] v = village1.get(n);
                    Collections.addAll(names, v);
                }
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }

/*
    private void addArea() {
        names.add("大洼区");
        names.add("大洼职能部门");
    }


    private void addStreet() {
        names.add("大洼街道");
        names.add("田家街道");
        names.add("榆树街道");
        names.add("前进街道");
        names.add("向海街道");
        names.add("西安镇");
        names.add("新兴镇");
        names.add("东风镇");
        names.add("新开镇");
        names.add("新立镇");
        names.add("清水镇");
        names.add("唐家镇");
        names.add("赵圈河镇");
        names.add("平安镇");
        names.add("三角洲开发区");
        names.add("网格中心");
    }

    private void addStreet1(){
        names.add("发改局");
        names.add("经信局");
        names.add("教育局");
        names.add("公安局");
        names.add("司法局");
        names.add("财政局");
        names.add("人社局");
        names.add("国土资源局");
        names.add("交通局");
        names.add("农经局");
        names.add("水利局");
        names.add("服务业局");
        names.add("安监局");
        names.add("规划局");
        names.add("征收办");
        names.add("宜居办");
        names.add("国税局");
        names.add("地税局");
        names.add("气象局");
        names.add("人民银行");
        names.add("移动公司");
        names.add("联通公司");
        names.add("社保分局");
        names.add("电信公司");
        names.add("政法委");
        names.add("统计局");
        names.add("市场监管局");
        names.add("文旅局");
        names.add("卫计局");
        names.add("民政局");
        names.add("供电分公司");
        names.add("环保局");
        names.add("城市建设（集团）");
        names.add("住建局");
        names.add("综合执法局");
        names.add("公交公司");
    }


    private void addVillage(int level1,int level2){

    }

    private void vDawaquDawajiedao() {
        names.add("新兴社区");
        names.add("向阳社区");
        names.add("站前社区");
        names.add("四新社区");
        names.add("东升社区");
        names.add("瀛路社区");
        names.add("桥南社区");
        names.add("兴盛社区");
        names.add("永安社区");
        names.add("繁荣社区");
        names.add("生产社区");
        names.add("振兴社区");
        names.add("欣荣社区");
        names.add("兴顺社区");
        names.add("新园社区");
        names.add("小堡子村");
        names.add("城市管理办公室");
        names.add("文化旅游服务中心");
        names.add("农业服务中心");
        names.add("综合治理办公室");
        names.add("司法所");
        names.add("网格中心");
    }

    private void vDawaquTianjiajiedao(){
        names.add("毛家社区");
        names.add("大堡子村");
        names.add("马圈子社区");
        names.add("田家社区");
        names.add("小洼社区");
        names.add("大高家社区");
        names.add("昆仑社区");
        names.add("盛田社区");
        names.add("欣田社区");
        names.add("东方社区");
        names.add("城市管理办");
        names.add("服务业提升办");
        names.add("宜居乡村");
        names.add("派出所");
        names.add("交警中队");
        names.add("消防站");
        names.add("党群办");
        names.add("网格管理服务中心");
        names.add("公共事业服务站");
        names.add("林海社区");
    }

    private void vDawaquYushujiedao(){
        names.add("曾家村");
        names.add("西榆社区");
        names.add("新立社区");
        names.add("郭家社区");
        names.add("兴旺社区");
        names.add("青联社区");
        names.add("榆茂社区");
        names.add("清凤社区");
        names.add("郑家社区");
        names.add("罗家湾");
        names.add("兴海村");
        names.add("建海村");
        names.add("城市管理办公室");
        names.add("城市管理办（城管）");
        names.add("城市管理办（公共事业服务站）");
        names.add("自来水服务站");
        names.add("水利服务站");
        names.add("农业中心");
        names.add("物业办公室");
        names.add("党建中心");
        names.add("综合治理办公室");
        names.add("党群办公室");
        names.add("社会服务办公室");
        names.add("社区服务办（社保所）");
        names.add("社会服务办（民政）");
        names.add("社会服务办（民教）");
        names.add("安管办公室");
        names.add("信访办公室");
        names.add("卫生院");
        names.add("燃气办公室");
        names.add("榆树街道网格中心");
    }

    private void vDawaquQianjinjiedao(){
        names.add("瑞田社区");
        names.add("小锅社区");
        names.add("三十里村");
        names.add("何家村");
        names.add("顾家村");
        names.add("高家村");
        names.add("关家村");
        names.add("宜居乡村");
        names.add("市政设施");
        names.add("路灯管理");
        names.add("环境卫生");
        names.add("农业服务中心");
        names.add("双海社区");
        names.add("碧城社区");
        names.add("香水湖社区");
        names.add("网格管理服务中心");
    }

    private void vDawaquXianghaijiedao(){
        names.add("东三社区");
        names.add("永兴社区");
        names.add("惠安社区");
        names.add("胜利社区");
        names.add("石庙子村");
        names.add("王家村");
        names.add("永盛社区");
        names.add("宝兴社区");
        names.add("双兴社区");
        names.add("永昌社区");
        names.add("曙光村");
        names.add("西海村");
        names.add("华侨村");
        names.add("农业中心");
        names.add("公共事业管理服务中心");
        names.add("医院");
        names.add("物业");
        names.add("司法所");
        names.add("派出所");
        names.add("自来水");
        names.add("市场监督管理所");
        names.add("向海街道网格中心");

    }

    private void vDawaquXianzhen(){
        names.add("桃源村");
        names.add("八家子村");
        names.add("韩家村");
        names.add("小洼村");
        names.add("刘家村");
        names.add("桑林子村");
        names.add("高坎村");
        names.add("洼边子村");
        names.add("上口子村");
        names.add("高坎湾村");
        names.add("小亮沟村");
        names.add("王家塘村");
        names.add("党群办公室");
        names.add("综合办公室");
        names.add("经济发展办公室");
        names.add("社会服务办公室");
        names.add("宜居乡村建设办公室");
        names.add("综合治理办公室");
        names.add("财政所（核算中心）");
        names.add("农业服务中心");
        names.add("派出所");
        names.add("西安镇网格中心");

    }

    private void vDawaquXinxingzhen(){
        names.add("王家村");
        names.add("两棵树");
        names.add("园林社区");
        names.add("坨子里村");
        names.add("红草沟村");
        names.add("育新村");
        names.add("躺岗子村");
        names.add("腰岗子村");
        names.add("子岗子村");
        names.add("宜居乡村建设办");
        names.add("社会服务办");
        names.add("经济发展办");
        names.add("党群办");
        names.add("综合治理办");
        names.add("综合办");
        names.add("财政所");
        names.add("农业服务中心");
        names.add("党建中心");
        names.add("网格管理服务中心");
    }

    private void vDawaquDongfengzhen(){
        names.add("大岗子村");
        names.add("河沿村");
        names.add("腰屯村");
        names.add("驾掌寺村");
        names.add("栾家村");
        names.add("马家村");
        names.add("叶家村");
        names.add("二道边村");
        names.add("东风村");
        names.add("黄金带村");
        names.add("大北屯村");
        names.add("水库村");
        names.add("宜居办");
        names.add("派出所");
        names.add("司法所");
        names.add("水利站");
        names.add("自来水");
        names.add("东风镇网格中心");

    }

    private void vDawaquXinkaizhen(){
        names.add("曲家村");
        names.add("田家村");
        names.add("西武村");
        names.add("张家村");
        names.add("史家村");
        names.add("胥家村");
        names.add("铁南村");
        names.add("八家村");
        names.add("于楼村");
        names.add("新开街道");
        names.add("司法所");
        names.add("城管中队");
        names.add("宜居管理办公室");
        names.add("水利服务站");
        names.add("路灯管理站");
        names.add("园林绿化管理办公室");
        names.add("派出所");
        names.add("道路管理办公室");
        names.add("农业技术服务中心");
        names.add("自来水服务站");

    }

    private void vDawaquXinlizhen(){
        names.add("史家村");
        names.add("唐家村");
        names.add("苏家村");
        names.add("杨家村");
        names.add("孙家村");
        names.add("云家村");
        names.add("张家村");
        names.add("新欣社区");
        names.add("宜居办");
        names.add("派出所");
        names.add("水利站");
        names.add("自来水");
        names.add("新立街道网格中心");

    }

    private void vDawaquQingshuizhen(){
        names.add("立新村 ");
        names.add("大清村");
        names.add("小清村");
        names.add("清河村");
        names.add("育红村");
        names.add("江南村");
        names.add("五岔村");
        names.add("南岗子村");
        names.add("锦红村");
        names.add("永红村");
        names.add("宜居办");
        names.add("派出所");
        names.add("司法所");
        names.add("水利站");
        names.add("自来水");
        names.add("清水镇网格中心");

    }

    private void vDawaquTangjiazhen(){
        names.add("北窑村");
        names.add("小房村");
        names.add("朱家村");
        names.add("百家村");
        names.add("唐家村");
        names.add("袁家村");
        names.add("杜家村");
        names.add("新建社区");
        names.add("刘家村");
        names.add("四十里村");
        names.add("陈家村");
        names.add("葛家村");
        names.add("环卫办");
        names.add("镇建办");
        names.add("交通办");
        names.add("司法所");
        names.add("派出所");
        names.add("社会服务办");
        names.add("唐家镇网格中心");

    }

    private void vDawaquZhaoquanhezhen(){
        names.add("红塔村");
        names.add("圈河村");
        names.add("兰石村");
        names.add("园林村");
        names.add("兴盛村");
        names.add("公共事业管理站");
        names.add("自来水");
        names.add("司法所");
        names.add("派出所");
        names.add("农业站");
        names.add("水利站");
        names.add("服务业办公室");
        names.add("物业办");
        names.add("向阳分公司");
        names.add("兴和分公司");
        names.add("小台子分公司");
        names.add("红旗分公司");
        names.add("建设分公司");
        names.add("星海分公司");
        names.add("双星分公司");
        names.add("网格管理中心");
        names.add("红塔分公司");

    }

    private void vDawaquPinganzhen(){
        names.add("小房村");
        names.add("新鑫村");
        names.add("哈吧村");
        names.add("建设村");
        names.add("平安村");
        names.add("大房村");
        names.add("大亮村");
        names.add("平房村");
        names.add("新屯村");
        names.add("曹蔡村");
        names.add("宜居站");
        names.add("综合办公室");
        names.add("自来水");
        names.add("派出所");
        names.add("司法所");
        names.add("社会服务办");
        names.add("经济办");
        names.add("市场监管所");
        names.add("网格管理服务中心");

    }

    private void vDawaquSaojiaozhoukaifaqu(){
        names.add("暂无下属区域");

    }

    private void vDawaquWanggezhongxin(){
        names.add("办公室");
        names.add("联动指挥中心");
        names.add("发展规划股");
        names.add("数据资源股");
        names.add("信息安全股");
        names.add("监督考评股");
        names.add("电子政务股");

    }
*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("return",names.get(position));
        switch (from){
            case "area":
                setResult(VideoChatFragment.AREA_RETURN,intent);
                break;
            case "street":
                setResult(VideoChatFragment.STREET_RETURN,intent);
                break;
            case "village":
                setResult(VideoChatFragment.VILLAGE_RETURN,intent);
                break;
            default:
                break;
        }
        finish();
    }
}
