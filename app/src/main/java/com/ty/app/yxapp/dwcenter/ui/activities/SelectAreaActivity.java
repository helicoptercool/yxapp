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
import java.util.List;

public class SelectAreaActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView selectLv;
    private ArrayAdapter<String> adapter;
    private List<String> names;
    private String from;

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
        initItems(from);
        View view = getLayoutInflater().inflate(R.layout.activity_select_area,null);
        selectLv = (ListView) view.findViewById(R.id.select_lv);
        selectLv.setAdapter(adapter);
        selectLv.setOnItemClickListener(this);
        return view;
    }

    public void initItems(String from) {
        names.clear();
        switch (from){
            case "area":
                addArea();
                break;
            case "street":
                int index = getIntent().getIntExtra("index",0);
                switch (index){
                    case 0:
                        addStreet();
                        break;
                    case 1:
                        addStreet1();
                        break;
                }
                break;
            case "village":
                addVillage(0,0);
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }

    private void addArea() {
        names.add("大洼区");
        names.add("大洼智能部门");
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

    /**
     * 第三级菜单
     * @param level1 第一级选中的下标
     * @param level2 第二级下标
     */
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
