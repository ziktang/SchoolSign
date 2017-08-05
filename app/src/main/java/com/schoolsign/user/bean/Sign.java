package com.schoolsign.user.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class Sign {

    /**
     * startTime : 2016-08-11 10:05:54
     * lessonName : 2016-08-11 10:05:54
     * lessonId : 1
     * dueCount : 0
     * lessonStatus : 0
     * signStatus : 0
     * courseId : 35226
     * signCount : 0
     * signCode : 531819
     */

    private LessonBean lesson;
    /**
     * lesson : {"startTime":"2016-08-11 10:05:54","lessonName":"2016-08-11 10:05:54","lessonId":1,"dueCount":0,"lessonStatus":0,"signStatus":0,"courseId":35226,"signCount":0,"signCode":"531819"}
     * result : 1111
     * lessonSigns : [{"username":"CSL201354080331","lessonId":1,"signTime":"1900-01-01","userId":104287,"signStatus":0,"realname":"黑聪","lessonSignId":1},{"username":"CSL201354080306","lessonId":1,"signTime":"1900-01-01","userId":104263,"signStatus":0,"realname":"向荣","lessonSignId":2},{"username":"CSL201354080215","lessonId":1,"signTime":"1900-01-01","userId":104238,"signStatus":0,"realname":"刘杰","lessonSignId":3},{"username":"CSL201354080235","lessonId":1,"signTime":"1900-01-01","userId":104257,"signStatus":0,"realname":"向广浩","lessonSignId":4},{"username":"CSL201354080219","lessonId":1,"signTime":"1900-01-01","userId":104242,"signStatus":0,"realname":"景立新","lessonSignId":5},{"username":"CSL201354080202","lessonId":1,"signTime":"1900-01-01","userId":104225,"signStatus":0,"realname":"李玉青","lessonSignId":6},{"username":"CSL201354080214","lessonId":1,"signTime":"1900-01-01","userId":104237,"signStatus":0,"realname":"罗敏","lessonSignId":7},{"username":"CSL201354080208","lessonId":1,"signTime":"1900-01-01","userId":104231,"signStatus":0,"realname":"陈素丹","lessonSignId":8},{"username":"CSL201354080207","lessonId":1,"signTime":"1900-01-01","userId":104230,"signStatus":0,"realname":"肖纤","lessonSignId":9},{"username":"CSL201354080226","lessonId":1,"signTime":"1900-01-01","userId":104249,"signStatus":0,"realname":"陈磊","lessonSignId":10},{"username":"CSL201354080129","lessonId":1,"signTime":"1900-01-01","userId":104217,"signStatus":0,"realname":"李风","lessonSignId":11},{"username":"CSL201354080128","lessonId":1,"signTime":"1900-01-01","userId":104216,"signStatus":0,"realname":"唐耿帅","lessonSignId":12},{"username":"CSL201354080227","lessonId":1,"signTime":"1900-01-01","userId":104250,"signStatus":0,"realname":"曹伟","lessonSignId":13},{"username":"CSL201354080234","lessonId":1,"signTime":"1900-01-01","userId":104256,"signStatus":0,"realname":"牛伟斌","lessonSignId":14},{"username":"CSL201354080111","lessonId":1,"signTime":"1900-01-01","userId":104201,"signStatus":0,"realname":"彭慧","lessonSignId":15},{"username":"CSL201354080134","lessonId":1,"signTime":"1900-01-01","userId":104222,"signStatus":0,"realname":"林成坤","lessonSignId":16},{"username":"CSL201354080104","lessonId":1,"signTime":"1900-01-01","userId":104195,"signStatus":0,"realname":"宋珊珊","lessonSignId":17},{"username":"CSL201354080103","lessonId":1,"signTime":"1900-01-01","userId":104194,"signStatus":0,"realname":"万静雯","lessonSignId":18},{"username":"CSL201354080121","lessonId":1,"signTime":"1900-01-01","userId":104210,"signStatus":0,"realname":"程立志","lessonSignId":19},{"username":"CSL201354080122","lessonId":1,"signTime":"1900-01-01","userId":104211,"signStatus":0,"realname":"杨裕警","lessonSignId":20},{"username":"CSL201354080320","lessonId":1,"signTime":"1900-01-01","userId":104276,"signStatus":0,"realname":"孙琦","lessonSignId":21},{"username":"CSL201354080323","lessonId":1,"signTime":"1900-01-01","userId":104279,"signStatus":0,"realname":"周强","lessonSignId":22},{"username":"CSL201354080311","lessonId":1,"signTime":"1900-01-01","userId":104268,"signStatus":0,"realname":"李诗娴","lessonSignId":23},{"username":"CSL201354080315","lessonId":1,"signTime":"1900-01-01","userId":104272,"signStatus":0,"realname":"娄增唯","lessonSignId":24},{"username":"CSL201354080312","lessonId":1,"signTime":"1900-01-01","userId":104269,"signStatus":0,"realname":"张梦鑫","lessonSignId":25},{"username":"CSL201354080231","lessonId":1,"signTime":"1900-01-01","userId":104253,"signStatus":0,"realname":"许一","lessonSignId":26},{"username":"CSL201354080210","lessonId":1,"signTime":"1900-01-01","userId":104233,"signStatus":0,"realname":"邹炼炼","lessonSignId":27},{"username":"CSL201354080216","lessonId":1,"signTime":"1900-01-01","userId":104239,"signStatus":0,"realname":"戚杨红","lessonSignId":28},{"username":"CSL201354080224","lessonId":1,"signTime":"1900-01-01","userId":104247,"signStatus":0,"realname":"周文韬","lessonSignId":29},{"username":"CSL201354080324","lessonId":1,"signTime":"1900-01-01","userId":104280,"signStatus":0,"realname":"肖海新","lessonSignId":30},{"username":"CSL201354080303","lessonId":1,"signTime":"1900-01-01","userId":104260,"signStatus":0,"realname":"周玉秋","lessonSignId":31},{"username":"CSL201354080302","lessonId":1,"signTime":"1900-01-01","userId":104259,"signStatus":0,"realname":"袁俊莲","lessonSignId":32},{"username":"CSL201354080332","lessonId":1,"signTime":"1900-01-01","userId":104288,"signStatus":0,"realname":"贾玉玺","lessonSignId":33},{"username":"CSL201354080307","lessonId":1,"signTime":"1900-01-01","userId":104264,"signStatus":0,"realname":"陈慧","lessonSignId":34},{"username":"CSL201354080314","lessonId":1,"signTime":"1900-01-01","userId":104271,"signStatus":0,"realname":"周红","lessonSignId":35},{"username":"CSL201354080106","lessonId":1,"signTime":"1900-01-01","userId":104197,"signStatus":0,"realname":"胡亚男","lessonSignId":36},{"username":"CSL201354080124","lessonId":1,"signTime":"1900-01-01","userId":104213,"signStatus":0,"realname":"刘雄杰","lessonSignId":37},{"username":"CSL201354080115","lessonId":1,"signTime":"1900-01-01","userId":104205,"signStatus":0,"realname":"何晓华","lessonSignId":38},{"username":"CSL201354080109","lessonId":1,"signTime":"1900-01-01","userId":104199,"signStatus":0,"realname":"彭笑笑","lessonSignId":39},{"username":"CSL201354080212","lessonId":1,"signTime":"1900-01-01","userId":104235,"signStatus":0,"realname":"刘佳佳","lessonSignId":40},{"username":"CSL201354080218","lessonId":1,"signTime":"1900-01-01","userId":104241,"signStatus":0,"realname":"李嘉琦","lessonSignId":41},{"username":"CSL201354080118","lessonId":1,"signTime":"1900-01-01","userId":104207,"signStatus":0,"realname":"王浩达","lessonSignId":42},{"username":"CSL201354080114","lessonId":1,"signTime":"1900-01-01","userId":104204,"signStatus":0,"realname":"梁舒婷","lessonSignId":43},{"username":"CSL201354080233","lessonId":1,"signTime":"1900-01-01","userId":104255,"signStatus":0,"realname":"罗浩","lessonSignId":44},{"username":"CSL201354080209","lessonId":1,"signTime":"1900-01-01","userId":104232,"signStatus":0,"realname":"胡钰","lessonSignId":45},{"username":"CSL201354080201","lessonId":1,"signTime":"1900-01-01","userId":104224,"signStatus":0,"realname":"王蒙","lessonSignId":46},{"username":"CSL201354080220","lessonId":1,"signTime":"1900-01-01","userId":104243,"signStatus":0,"realname":"钱俊鹏","lessonSignId":47},{"username":"CSL201354080225","lessonId":1,"signTime":"1900-01-01","userId":104248,"signStatus":0,"realname":"贺旦","lessonSignId":48},{"username":"CSL201354080213","lessonId":1,"signTime":"1900-01-01","userId":104236,"signStatus":0,"realname":"王子璇","lessonSignId":49},{"username":"CSL201354080318","lessonId":1,"signTime":"1900-01-01","userId":104274,"signStatus":0,"realname":"任自东","lessonSignId":50},{"username":"CSL201354080326","lessonId":1,"signTime":"1900-01-01","userId":104282,"signStatus":0,"realname":"陈华","lessonSignId":51},{"username":"CSL201354080330","lessonId":1,"signTime":"1900-01-01","userId":104286,"signStatus":0,"realname":"李栋维","lessonSignId":52},{"username":"CSL201354080123","lessonId":1,"signTime":"1900-01-01","userId":104212,"signStatus":0,"realname":"廖伟杰","lessonSignId":53},{"username":"CSL201354080117","lessonId":1,"signTime":"1900-01-01","userId":104206,"signStatus":0,"realname":"刘康","lessonSignId":54},{"username":"CSL201354080132","lessonId":1,"signTime":"1900-01-01","userId":104220,"signStatus":0,"realname":"张鹏程","lessonSignId":55},{"username":"CSL201354080334","lessonId":1,"signTime":"1900-01-01","userId":104290,"signStatus":0,"realname":"黄伟豪","lessonSignId":56},{"username":"CSL201354080325","lessonId":1,"signTime":"1900-01-01","userId":104281,"signStatus":0,"realname":"曾显珣","lessonSignId":57},{"username":"CSL201354080221","lessonId":1,"signTime":"1900-01-01","userId":104244,"signStatus":0,"realname":"胡兵","lessonSignId":58},{"username":"CSL201354080223","lessonId":1,"signTime":"1900-01-01","userId":104246,"signStatus":0,"realname":"胡凌波","lessonSignId":59},{"username":"CSL201354080206","lessonId":1,"signTime":"1900-01-01","userId":104229,"signStatus":0,"realname":"刘芳","lessonSignId":60},{"username":"CSL201354080230","lessonId":1,"signTime":"1900-01-01","userId":104252,"signStatus":0,"realname":"张海波","lessonSignId":61},{"username":"CSL201354080301","lessonId":1,"signTime":"1900-01-01","userId":104258,"signStatus":0,"realname":"刘亚梅","lessonSignId":62},{"username":"CSL201354080305","lessonId":1,"signTime":"1900-01-01","userId":104262,"signStatus":0,"realname":"陈达姣","lessonSignId":63},{"username":"CSL201354080110","lessonId":1,"signTime":"1900-01-01","userId":104200,"signStatus":0,"realname":"袁胜男","lessonSignId":64},{"username":"CSL201354080112","lessonId":1,"signTime":"1900-01-01","userId":104202,"signStatus":0,"realname":"张依婷","lessonSignId":65},{"username":"CSL201354080205","lessonId":1,"signTime":"1900-01-01","userId":104228,"signStatus":0,"realname":"孔俐","lessonSignId":66},{"username":"CSL201354080211","lessonId":1,"signTime":"1900-01-01","userId":104234,"signStatus":0,"realname":"熊鸽","lessonSignId":67},{"username":"CSL201354080327","lessonId":1,"signTime":"1900-01-01","userId":104283,"signStatus":0,"realname":"何文","lessonSignId":68},{"username":"CSL201354080316","lessonId":1,"signTime":"1900-01-01","userId":104273,"signStatus":0,"realname":"胡书恒","lessonSignId":69},{"username":"CSL201354080319","lessonId":1,"signTime":"1900-01-01","userId":104275,"signStatus":0,"realname":"陈军","lessonSignId":70},{"username":"CSL201354080310","lessonId":1,"signTime":"1900-01-01","userId":104267,"signStatus":0,"realname":"何雨晴","lessonSignId":71},{"username":"CSL201354080113","lessonId":1,"signTime":"1900-01-01","userId":104203,"signStatus":0,"realname":"秦婉佩","lessonSignId":72},{"username":"CSL201354080105","lessonId":1,"signTime":"1900-01-01","userId":104196,"signStatus":0,"realname":"刘亚军","lessonSignId":73},{"username":"CSL201354080229","lessonId":1,"signTime":"1900-01-01","userId":104251,"signStatus":0,"realname":"陈维锋","lessonSignId":74},{"username":"CSL201354080322","lessonId":1,"signTime":"1900-01-01","userId":104278,"signStatus":0,"realname":"熊滔","lessonSignId":75},{"username":"CSL201354080333","lessonId":1,"signTime":"1900-01-01","userId":104289,"signStatus":0,"realname":"陈涵琛","lessonSignId":76},{"username":"CSL201354080328","lessonId":1,"signTime":"1900-01-01","userId":104284,"signStatus":0,"realname":"袁灿明","lessonSignId":77},{"username":"CSL201354080335","lessonId":1,"signTime":"1900-01-01","userId":104291,"signStatus":0,"realname":"霍浪","lessonSignId":78},{"username":"CSL201354080308","lessonId":1,"signTime":"1900-01-01","userId":104265,"signStatus":0,"realname":"袁文挺","lessonSignId":79},{"username":"CSL201354080232","lessonId":1,"signTime":"1900-01-01","userId":104254,"signStatus":0,"realname":"牛鼎","lessonSignId":80},{"username":"CSL201354080107","lessonId":1,"signTime":"1900-01-01","userId":104198,"signStatus":0,"realname":"周鹏佳","lessonSignId":81},{"username":"CSL201354080127","lessonId":1,"signTime":"1900-01-01","userId":104215,"signStatus":0,"realname":"朱文圳","lessonSignId":82},{"username":"CSL201354080126","lessonId":1,"signTime":"1900-01-01","userId":104214,"signStatus":0,"realname":"刘君男","lessonSignId":83},{"username":"CSL201354080130","lessonId":1,"signTime":"1900-01-01","userId":104218,"signStatus":0,"realname":"周文权","lessonSignId":84},{"username":"CSL201354080309","lessonId":1,"signTime":"1900-01-01","userId":104266,"signStatus":0,"realname":"周思敏","lessonSignId":85},{"username":"CSL201354080329","lessonId":1,"signTime":"1900-01-01","userId":104285,"signStatus":0,"realname":"阳正宇","lessonSignId":86},{"username":"CSL201354080304","lessonId":1,"signTime":"1900-01-01","userId":104261,"signStatus":0,"realname":"余晓婷","lessonSignId":87},{"username":"CSL201354080119","lessonId":1,"signTime":"1900-01-01","userId":104208,"signStatus":0,"realname":"吴波","lessonSignId":88},{"username":"CSL201354080131","lessonId":1,"signTime":"1900-01-01","userId":104219,"signStatus":0,"realname":"龙景霖","lessonSignId":89},{"username":"CSL201354080203","lessonId":1,"signTime":"1900-01-01","userId":104226,"signStatus":0,"realname":"蒋坤池","lessonSignId":90},{"username":"CSL201354080101","lessonId":1,"signTime":"1900-01-01","userId":104193,"signStatus":0,"realname":"孙佳","lessonSignId":91},{"username":"CSL201354080120","lessonId":1,"signTime":"1900-01-01","userId":104209,"signStatus":0,"realname":"刘侃","lessonSignId":92},{"username":"CSL201354080204","lessonId":1,"signTime":"1900-01-01","userId":104227,"signStatus":0,"realname":"于秀男","lessonSignId":93},{"username":"CSL201354080321","lessonId":1,"signTime":"1900-01-01","userId":104277,"signStatus":0,"realname":"蔡杰","lessonSignId":94},{"username":"CSL201354080133","lessonId":1,"signTime":"1900-01-01","userId":104221,"signStatus":0,"realname":"陈明良","lessonSignId":95},{"username":"CSL201302030215","lessonId":1,"signTime":"1900-01-01","userId":104192,"signStatus":0,"realname":"徐东篱","lessonSignId":96},{"username":"CSL201354080135","lessonId":1,"signTime":"1900-01-01","userId":104223,"signStatus":0,"realname":"赖瑀","lessonSignId":97},{"username":"CSL201354080217","lessonId":1,"signTime":"1900-01-01","userId":104240,"signStatus":0,"realname":"胡敏","lessonSignId":98},{"username":"CSL201254080323","lessonId":1,"signTime":"1900-01-01","userId":102988,"signStatus":0,"realname":"侯耀文","lessonSignId":99},{"username":"CSL201154080131","lessonId":1,"signTime":"1900-01-01","userId":103062,"signStatus":0,"realname":"刘金蒙","lessonSignId":100},{"username":"CSL201354080331","lessonId":1,"signTime":"1900-01-01","userId":104287,"signStatus":0,"realname":"黑聪","lessonSignId":101},{"username":"CSL201354080202","lessonId":1,"signTime":"1900-01-01","userId":104225,"signStatus":0,"realname":"李玉青","lessonSignId":102},{"username":"CSL201354080214","lessonId":1,"signTime":"1900-01-01","userId":104237,"signStatus":0,"realname":"罗敏","lessonSignId":103},{"username":"CSL201354080208","lessonId":1,"signTime":"1900-01-01","userId":104231,"signStatus":0,"realname":"陈素丹","lessonSignId":104},{"username":"CSL201354080210","lessonId":1,"signTime":"1900-01-01","userId":104233,"signStatus":0,"realname":"邹炼炼","lessonSignId":105},{"username":"CSL201354080219","lessonId":1,"signTime":"1900-01-01","userId":104242,"signStatus":0,"realname":"景立新","lessonSignId":106},{"username":"CSL201354080207","lessonId":1,"signTime":"1900-01-01","userId":104230,"signStatus":0,"realname":"肖纤","lessonSignId":107},{"username":"CSL201354080134","lessonId":1,"signTime":"1900-01-01","userId":104222,"signStatus":0,"realname":"林成坤","lessonSignId":108},{"username":"CSL201354080106","lessonId":1,"signTime":"1900-01-01","userId":104197,"signStatus":0,"realname":"胡亚男","lessonSignId":109},{"username":"CSL201354080122","lessonId":1,"signTime":"1900-01-01","userId":104211,"signStatus":0,"realname":"杨裕警","lessonSignId":110},{"username":"CSL201354080128","lessonId":1,"signTime":"1900-01-01","userId":104216,"signStatus":0,"realname":"唐耿帅","lessonSignId":111},{"username":"CSL201354080111","lessonId":1,"signTime":"1900-01-01","userId":104201,"signStatus":0,"realname":"彭慧","lessonSignId":112},{"username":"CSL201354080129","lessonId":1,"signTime":"1900-01-01","userId":104217,"signStatus":0,"realname":"李风","lessonSignId":113},{"username":"CSL201354080124","lessonId":1,"signTime":"1900-01-01","userId":104213,"signStatus":0,"realname":"刘雄杰","lessonSignId":114},{"username":"CSL201354080107","lessonId":1,"signTime":"1900-01-01","userId":104198,"signStatus":0,"realname":"周鹏佳","lessonSignId":115},{"username":"CSL201354080311","lessonId":1,"signTime":"1900-01-01","userId":104268,"signStatus":0,"realname":"李诗娴","lessonSignId":116},{"username":"CSL201354080215","lessonId":1,"signTime":"1900-01-01","userId":104238,"signStatus":0,"realname":"刘杰","lessonSignId":117},{"username":"CSL201354080235","lessonId":1,"signTime":"1900-01-01","userId":104257,"signStatus":0,"realname":"向广浩","lessonSignId":118},{"username":"CSL201354080227","lessonId":1,"signTime":"1900-01-01","userId":104250,"signStatus":0,"realname":"曹伟","lessonSignId":119},{"username":"CSL201354080226","lessonId":1,"signTime":"1900-01-01","userId":104249,"signStatus":0,"realname":"陈磊","lessonSignId":120},{"username":"CSL201354080212","lessonId":1,"signTime":"1900-01-01","userId":104235,"signStatus":0,"realname":"刘佳佳","lessonSignId":121},{"username":"CSL201354080224","lessonId":1,"signTime":"1900-01-01","userId":104247,"signStatus":0,"realname":"周文韬","lessonSignId":122},{"username":"CSL201354080220","lessonId":1,"signTime":"1900-01-01","userId":104243,"signStatus":0,"realname":"钱俊鹏","lessonSignId":123},{"username":"CSL201354080233","lessonId":1,"signTime":"1900-01-01","userId":104255,"signStatus":0,"realname":"罗浩","lessonSignId":124},{"username":"CSL201354080216","lessonId":1,"signTime":"1900-01-01","userId":104239,"signStatus":0,"realname":"戚杨红","lessonSignId":125},{"username":"CSL201354080234","lessonId":1,"signTime":"1900-01-01","userId":104256,"signStatus":0,"realname":"牛伟斌","lessonSignId":126},{"username":"CSL201354080201","lessonId":1,"signTime":"1900-01-01","userId":104224,"signStatus":0,"realname":"王蒙","lessonSignId":127},{"username":"CSL201354080209","lessonId":1,"signTime":"1900-01-01","userId":104232,"signStatus":0,"realname":"胡钰","lessonSignId":128},{"username":"CSL201354080104","lessonId":1,"signTime":"1900-01-01","userId":104195,"signStatus":0,"realname":"宋珊珊","lessonSignId":129},{"username":"CSL201354080115","lessonId":1,"signTime":"1900-01-01","userId":104205,"signStatus":0,"realname":"何晓华","lessonSignId":130},{"username":"CSL201354080117","lessonId":1,"signTime":"1900-01-01","userId":104206,"signStatus":0,"realname":"刘康","lessonSignId":131},{"username":"CSL201354080103","lessonId":1,"signTime":"1900-01-01","userId":104194,"signStatus":0,"realname":"万静雯","lessonSignId":132},{"username":"CSL201354080121","lessonId":1,"signTime":"1900-01-01","userId":104210,"signStatus":0,"realname":"程立志","lessonSignId":133},{"username":"CSL201354080302","lessonId":1,"signTime":"1900-01-01","userId":104259,"signStatus":0,"realname":"袁俊莲","lessonSignId":134},{"username":"CSL201354080332","lessonId":1,"signTime":"1900-01-01","userId":104288,"signStatus":0,"realname":"贾玉玺","lessonSignId":135},{"username":"CSL201354080307","lessonId":1,"signTime":"1900-01-01","userId":104264,"signStatus":0,"realname":"陈慧","lessonSignId":136},{"username":"CSL201354080314","lessonId":1,"signTime":"1900-01-01","userId":104271,"signStatus":0,"realname":"周红","lessonSignId":137},{"username":"CSL201354080320","lessonId":1,"signTime":"1900-01-01","userId":104276,"signStatus":0,"realname":"孙琦","lessonSignId":138},{"username":"CSL201354080323","lessonId":1,"signTime":"1900-01-01","userId":104279,"signStatus":0,"realname":"周强","lessonSignId":139},{"username":"CSL201354080312","lessonId":1,"signTime":"1900-01-01","userId":104269,"signStatus":0,"realname":"张梦鑫","lessonSignId":140},{"username":"CSL201354080303","lessonId":1,"signTime":"1900-01-01","userId":104260,"signStatus":0,"realname":"周玉秋","lessonSignId":141},{"username":"CSL201354080326","lessonId":1,"signTime":"1900-01-01","userId":104282,"signStatus":0,"realname":"陈华","lessonSignId":142},{"username":"CSL201354080218","lessonId":1,"signTime":"1900-01-01","userId":104241,"signStatus":0,"realname":"李嘉琦","lessonSignId":143},{"username":"CSL201354080324","lessonId":1,"signTime":"1900-01-01","userId":104280,"signStatus":0,"realname":"肖海新","lessonSignId":144},{"username":"CSL201354080318","lessonId":1,"signTime":"1900-01-01","userId":104274,"signStatus":0,"realname":"任自东","lessonSignId":145},{"username":"CSL201354080325","lessonId":1,"signTime":"1900-01-01","userId":104281,"signStatus":0,"realname":"曾显珣","lessonSignId":146},{"username":"CSL201354080330","lessonId":1,"signTime":"1900-01-01","userId":104286,"signStatus":0,"realname":"李栋维","lessonSignId":147},{"username":"CSL201354080221","lessonId":1,"signTime":"1900-01-01","userId":104244,"signStatus":0,"realname":"胡兵","lessonSignId":148},{"username":"CSL201354080223","lessonId":1,"signTime":"1900-01-01","userId":104246,"signStatus":0,"realname":"胡凌波","lessonSignId":149},{"username":"CSL201354080213","lessonId":1,"signTime":"1900-01-01","userId":104236,"signStatus":0,"realname":"王子璇","lessonSignId":150},{"username":"CSL201354080225","lessonId":1,"signTime":"1900-01-01","userId":104248,"signStatus":0,"realname":"贺旦","lessonSignId":151},{"username":"CSL201354080109","lessonId":1,"signTime":"1900-01-01","userId":104199,"signStatus":0,"realname":"彭笑笑","lessonSignId":152},{"username":"CSL201354080114","lessonId":1,"signTime":"1900-01-01","userId":104204,"signStatus":0,"realname":"梁舒婷","lessonSignId":153},{"username":"CSL201354080123","lessonId":1,"signTime":"1900-01-01","userId":104212,"signStatus":0,"realname":"廖伟杰","lessonSignId":154},{"username":"CSL201354080118","lessonId":1,"signTime":"1900-01-01","userId":104207,"signStatus":0,"realname":"王浩达","lessonSignId":155},{"username":"CSL201354080132","lessonId":1,"signTime":"1900-01-01","userId":104220,"signStatus":0,"realname":"张鹏程","lessonSignId":156},{"username":"CSL201354080112","lessonId":1,"signTime":"1900-01-01","userId":104202,"signStatus":0,"realname":"张依婷","lessonSignId":157},{"username":"CSL201354080110","lessonId":1,"signTime":"1900-01-01","userId":104200,"signStatus":0,"realname":"袁胜男","lessonSignId":158},{"username":"CSL201354080231","lessonId":1,"signTime":"1900-01-01","userId":104253,"signStatus":0,"realname":"许一","lessonSignId":159},{"username":"CSL201354080334","lessonId":1,"signTime":"1900-01-01","userId":104290,"signStatus":0,"realname":"黄伟豪","lessonSignId":160},{"username":"CSL201354080301","lessonId":1,"signTime":"1900-01-01","userId":104258,"signStatus":0,"realname":"刘亚梅","lessonSignId":161},{"username":"CSL201354080316","lessonId":1,"signTime":"1900-01-01","userId":104273,"signStatus":0,"realname":"胡书恒","lessonSignId":162},{"username":"CSL201354080315","lessonId":1,"signTime":"1900-01-01","userId":104272,"signStatus":0,"realname":"娄增唯","lessonSignId":163},{"username":"CSL201354080322","lessonId":1,"signTime":"1900-01-01","userId":104278,"signStatus":0,"realname":"熊滔","lessonSignId":164},{"username":"CSL201354080113","lessonId":1,"signTime":"1900-01-01","userId":104203,"signStatus":0,"realname":"秦婉佩","lessonSignId":165},{"username":"CSL201354080206","lessonId":1,"signTime":"1900-01-01","userId":104229,"signStatus":0,"realname":"刘芳","lessonSignId":166},{"username":"CSL201354080230","lessonId":1,"signTime":"1900-01-01","userId":104252,"signStatus":0,"realname":"张海波","lessonSignId":167},{"username":"CSL201354080310","lessonId":1,"signTime":"1900-01-01","userId":104267,"signStatus":0,"realname":"何雨晴","lessonSignId":168},{"username":"CSL201354080308","lessonId":1,"signTime":"1900-01-01","userId":104265,"signStatus":0,"realname":"袁文挺","lessonSignId":169},{"username":"CSL201354080305","lessonId":1,"signTime":"1900-01-01","userId":104262,"signStatus":0,"realname":"陈达姣","lessonSignId":170},{"username":"CSL201354080309","lessonId":1,"signTime":"1900-01-01","userId":104266,"signStatus":0,"realname":"周思敏","lessonSignId":171},{"username":"CSL201354080333","lessonId":1,"signTime":"1900-01-01","userId":104289,"signStatus":0,"realname":"陈涵琛","lessonSignId":172},{"username":"CSL201354080205","lessonId":1,"signTime":"1900-01-01","userId":104228,"signStatus":0,"realname":"孔俐","lessonSignId":173},{"username":"CSL201354080211","lessonId":1,"signTime":"1900-01-01","userId":104234,"signStatus":0,"realname":"熊鸽","lessonSignId":174},{"username":"CSL201354080327","lessonId":1,"signTime":"1900-01-01","userId":104283,"signStatus":0,"realname":"何文","lessonSignId":175},{"username":"CSL201354080319","lessonId":1,"signTime":"1900-01-01","userId":104275,"signStatus":0,"realname":"陈军","lessonSignId":176},{"username":"CSL201354080329","lessonId":1,"signTime":"1900-01-01","userId":104285,"signStatus":0,"realname":"阳正宇","lessonSignId":177},{"username":"CSL201354080126","lessonId":1,"signTime":"1900-01-01","userId":104214,"signStatus":0,"realname":"刘君男","lessonSignId":178},{"username":"CSL201354080127","lessonId":1,"signTime":"1900-01-01","userId":104215,"signStatus":0,"realname":"朱文圳","lessonSignId":179},{"username":"CSL201354080130","lessonId":1,"signTime":"1900-01-01","userId":104218,"signStatus":0,"realname":"周文权","lessonSignId":180},{"username":"CSL201354080105","lessonId":1,"signTime":"1900-01-01","userId":104196,"signStatus":0,"realname":"刘亚军","lessonSignId":181},{"username":"CSL201354080131","lessonId":1,"signTime":"1900-01-01","userId":104219,"signStatus":0,"realname":"龙景霖","lessonSignId":182},{"username":"CSL201354080119","lessonId":1,"signTime":"1900-01-01","userId":104208,"signStatus":0,"realname":"吴波","lessonSignId":183},{"username":"CSL201354080229","lessonId":1,"signTime":"1900-01-01","userId":104251,"signStatus":0,"realname":"陈维锋","lessonSignId":184},{"username":"CSL201354080232","lessonId":1,"signTime":"1900-01-01","userId":104254,"signStatus":0,"realname":"牛鼎","lessonSignId":185},{"username":"CSL201354080203","lessonId":1,"signTime":"1900-01-01","userId":104226,"signStatus":0,"realname":"蒋坤池","lessonSignId":186},{"username":"CSL201354080304","lessonId":1,"signTime":"1900-01-01","userId":104261,"signStatus":0,"realname":"余晓婷","lessonSignId":187},{"username":"CSL201354080328","lessonId":1,"signTime":"1900-01-01","userId":104284,"signStatus":0,"realname":"袁灿明","lessonSignId":188},{"username":"CSL201354080335","lessonId":1,"signTime":"1900-01-01","userId":104291,"signStatus":0,"realname":"霍浪","lessonSignId":189},{"username":"CSL201354080204","lessonId":1,"signTime":"1900-01-01","userId":104227,"signStatus":0,"realname":"于秀男","lessonSignId":190},{"username":"CSL201354080101","lessonId":1,"signTime":"1900-01-01","userId":104193,"signStatus":0,"realname":"孙佳","lessonSignId":191},{"username":"CSL201354080120","lessonId":1,"signTime":"1900-01-01","userId":104209,"signStatus":0,"realname":"刘侃","lessonSignId":192},{"username":"CSL201354080135","lessonId":1,"signTime":"1900-01-01","userId":104223,"signStatus":0,"realname":"赖瑀","lessonSignId":193},{"username":"CSL201302030215","lessonId":1,"signTime":"1900-01-01","userId":104192,"signStatus":0,"realname":"徐东篱","lessonSignId":194},{"username":"CSL201354080133","lessonId":1,"signTime":"1900-01-01","userId":104221,"signStatus":0,"realname":"陈明良","lessonSignId":195},{"username":"CSL201354080217","lessonId":1,"signTime":"1900-01-01","userId":104240,"signStatus":0,"realname":"胡敏","lessonSignId":196},{"username":"CSL201154080131","lessonId":1,"signTime":"1900-01-01","userId":103062,"signStatus":0,"realname":"刘金蒙","lessonSignId":197},{"username":"CSL201385250216","lessonId":1,"signTime":"1900-01-01","userId":132665,"signStatus":0,"realname":"杨宗梭","lessonSignId":198},{"username":"CSL201385250219","lessonId":1,"signTime":"1900-01-01","userId":132668,"signStatus":0,"realname":"张杨","lessonSignId":199},{"username":"CSL201385250225","lessonId":1,"signTime":"1900-01-01","userId":132674,"signStatus":0,"realname":"周明敏","lessonSignId":200},{"username":"CSL201385250209","lessonId":1,"signTime":"1900-01-01","userId":132659,"signStatus":0,"realname":"罗佩","lessonSignId":201},{"username":"CSL201385250125","lessonId":1,"signTime":"1900-01-01","userId":132646,"signStatus":0,"realname":"胡敏","lessonSignId":202},{"username":"CSL201385250214","lessonId":1,"signTime":"1900-01-01","userId":132664,"signStatus":0,"realname":"李利香","lessonSignId":203},{"username":"CSL201385250222","lessonId":1,"signTime":"1900-01-01","userId":132671,"signStatus":0,"realname":"杨宇凡","lessonSignId":204},{"username":"CSL201385250205","lessonId":1,"signTime":"1900-01-01","userId":132655,"signStatus":0,"realname":"欧阳嘉惠","lessonSignId":205},{"username":"CSL201385250110","lessonId":1,"signTime":"1900-01-01","userId":132632,"signStatus":0,"realname":"郭艳芳","lessonSignId":206},{"username":"CSL201385250124","lessonId":1,"signTime":"1900-01-01","userId":132645,"signStatus":0,"realname":"汪常梅","lessonSignId":207},{"username":"CSL201385250203","lessonId":1,"signTime":"1900-01-01","userId":132653,"signStatus":0,"realname":"潘若琳","lessonSignId":208},{"username":"CSL201385250220","lessonId":1,"signTime":"1900-01-01","userId":132669,"signStatus":0,"realname":"霍磊","lessonSignId":209},{"username":"CSL201385250120","lessonId":1,"signTime":"1900-01-01","userId":132641,"signStatus":0,"realname":"熊星宇","lessonSignId":210},{"username":"CSL201385250123","lessonId":1,"signTime":"1900-01-01","userId":132644,"signStatus":0,"realname":"刘洋","lessonSignId":211},{"username":"CSL201385250116","lessonId":1,"signTime":"1900-01-01","userId":132638,"signStatus":0,"realname":"徐铭泽","lessonSignId":212},{"username":"CSL201385250218","lessonId":1,"signTime":"1900-01-01","userId":132667,"signStatus":0,"realname":"张博","lessonSignId":213},{"username":"CSL201385250206","lessonId":1,"signTime":"1900-01-01","userId":132656,"signStatus":0,"realname":"刘丽","lessonSignId":214},{"username":"CSL201385250211","lessonId":1,"signTime":"1900-01-01","userId":132661,"signStatus":0,"realname":"吕丽霞","lessonSignId":215},{"username":"CSL201385250212","lessonId":1,"signTime":"1900-01-01","userId":132662,"signStatus":0,"realname":"游紫涵","lessonSignId":216},{"username":"CSL201385250210","lessonId":1,"signTime":"1900-01-01","userId":132660,"signStatus":0,"realname":"柳珍珍","lessonSignId":217},{"username":"CSL201385250227","lessonId":1,"signTime":"1900-01-01","userId":132675,"signStatus":0,"realname":"符逸","lessonSignId":218},{"username":"CSL201385250208","lessonId":1,"signTime":"1900-01-01","userId":132658,"signStatus":0,"realname":"段杏燕","lessonSignId":219},{"username":"CSL201385250112","lessonId":1,"signTime":"1900-01-01","userId":132634,"signStatus":0,"realname":"李玉洁","lessonSignId":220},{"username":"CSL201385250117","lessonId":1,"signTime":"1900-01-01","userId":132639,"signStatus":0,"realname":"黄鹄","lessonSignId":221},{"username":"CSL201385250129","lessonId":1,"signTime":"1900-01-01","userId":132650,"signStatus":0,"realname":"蔡旺本","lessonSignId":222},{"username":"CSL201385250126","lessonId":1,"signTime":"1900-01-01","userId":132647,"signStatus":0,"realname":"孙宝","lessonSignId":223},{"username":"CSL201385250103","lessonId":1,"signTime":"1900-01-01","userId":132626,"signStatus":0,"realname":"刘畅","lessonSignId":224},{"username":"CSL201385250104","lessonId":1,"signTime":"1900-01-01","userId":132627,"signStatus":0,"realname":"冯昕怡","lessonSignId":225},{"username":"CSL201385250122","lessonId":1,"signTime":"1900-01-01","userId":132643,"signStatus":0,"realname":"刘亮","lessonSignId":226},{"username":"CSL201385250105","lessonId":1,"signTime":"1900-01-01","userId":132628,"signStatus":0,"realname":"徐晓莉","lessonSignId":227},{"username":"CSL201385250114","lessonId":1,"signTime":"1900-01-01","userId":132636,"signStatus":0,"realname":"尹清渝","lessonSignId":228},{"username":"CSL201385250207","lessonId":1,"signTime":"1900-01-01","userId":132657,"signStatus":0,"realname":"黄倩倩","lessonSignId":229},{"username":"CSL201385250223","lessonId":1,"signTime":"1900-01-01","userId":132672,"signStatus":0,"realname":"李超博","lessonSignId":230},{"username":"CSL201385250224","lessonId":1,"signTime":"1900-01-01","userId":132673,"signStatus":0,"realname":"丁泽望","lessonSignId":231},{"username":"CSL201385250107","lessonId":1,"signTime":"1900-01-01","userId":132629,"signStatus":0,"realname":"董辉","lessonSignId":232},{"username":"CSL201385250115","lessonId":1,"signTime":"1900-01-01","userId":132637,"signStatus":0,"realname":"虞如义","lessonSignId":233},{"username":"CSL201385250111","lessonId":1,"signTime":"1900-01-01","userId":132633,"signStatus":0,"realname":"程爽爽","lessonSignId":234},{"username":"CSL201385250221","lessonId":1,"signTime":"1900-01-01","userId":132670,"signStatus":0,"realname":"龙黎明","lessonSignId":235},{"username":"CSL201385250204","lessonId":1,"signTime":"1900-01-01","userId":132654,"signStatus":0,"realname":"黄晶","lessonSignId":236},{"username":"CSL201385250217","lessonId":1,"signTime":"1900-01-01","userId":132666,"signStatus":0,"realname":"庄维","lessonSignId":237},{"username":"CSL201385250108","lessonId":1,"signTime":"1900-01-01","userId":132630,"signStatus":0,"realname":"颜智丽","lessonSignId":238},{"username":"CSL201385250121","lessonId":1,"signTime":"1900-01-01","userId":132642,"signStatus":0,"realname":"刘希同","lessonSignId":239},{"username":"CSL201385250201","lessonId":1,"signTime":"1900-01-01","userId":132651,"signStatus":0,"realname":"刘慧青","lessonSignId":240},{"username":"CSL201385250213","lessonId":1,"signTime":"1900-01-01","userId":132663,"signStatus":0,"realname":"周丽霞","lessonSignId":241},{"username":"CSL201385250113","lessonId":1,"signTime":"1900-01-01","userId":132635,"signStatus":0,"realname":"李培豪","lessonSignId":242},{"username":"CSL201385250101","lessonId":1,"signTime":"1900-01-01","userId":132624,"signStatus":0,"realname":"田小露","lessonSignId":243},{"username":"CSL201385250109","lessonId":1,"signTime":"1900-01-01","userId":132631,"signStatus":0,"realname":"董歆滢","lessonSignId":244},{"username":"CSL201385250128","lessonId":1,"signTime":"1900-01-01","userId":132649,"signStatus":0,"realname":"杨峻","lessonSignId":245},{"username":"CSL201385250102","lessonId":1,"signTime":"1900-01-01","userId":132625,"signStatus":0,"realname":"曹文慧","lessonSignId":246},{"username":"CSL201385250127","lessonId":1,"signTime":"1900-01-01","userId":132648,"signStatus":0,"realname":"宁雄俊","lessonSignId":247},{"username":"CSL201385250202","lessonId":1,"signTime":"1900-01-01","userId":132652,"signStatus":0,"realname":"于春然","lessonSignId":248}]
     */

    private int result;
    /**
     * username : CSL201354080331
     * lessonId : 1
     * signTime : 1900-01-01
     * userId : 104287
     * signStatus : 0
     * realname : 黑聪
     * lessonSignId : 1
     */

    private List<LessonSignsBean> lessonSigns;

    public LessonBean getLesson() {
        return lesson;
    }

    public void setLesson(LessonBean lesson) {
        this.lesson = lesson;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<LessonSignsBean> getLessonSigns() {
        return lessonSigns;
    }

    public void setLessonSigns(List<LessonSignsBean> lessonSigns) {
        this.lessonSigns = lessonSigns;
    }

    public static class LessonBean {
        private String startTime;
        private String lessonName;
        private int lessonId;
        private int dueCount;
        private int lessonStatus;
        private int signStatus;
        private int courseId;
        private int signCount;
        private String signCode;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public int getLessonId() {
            return lessonId;
        }

        public void setLessonId(int lessonId) {
            this.lessonId = lessonId;
        }

        public int getDueCount() {
            return dueCount;
        }

        public void setDueCount(int dueCount) {
            this.dueCount = dueCount;
        }

        public int getLessonStatus() {
            return lessonStatus;
        }

        public void setLessonStatus(int lessonStatus) {
            this.lessonStatus = lessonStatus;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getSignCount() {
            return signCount;
        }

        public void setSignCount(int signCount) {
            this.signCount = signCount;
        }

        public String getSignCode() {
            return signCode;
        }

        public void setSignCode(String signCode) {
            this.signCode = signCode;
        }
    }

    public static class LessonSignsBean {
        private String username;
        private int lessonId;
        private String signTime;
        private int userId;
        private int signStatus;
        private String realname;
        private int lessonSignId;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getLessonId() {
            return lessonId;
        }

        public void setLessonId(int lessonId) {
            this.lessonId = lessonId;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getLessonSignId() {
            return lessonSignId;
        }

        public void setLessonSignId(int lessonSignId) {
            this.lessonSignId = lessonSignId;
        }
    }


}
