

//部署环境
var wcfurl = "http://" + window.location.host + "/HBDPWCF/Service.svc";

var host = window.location.host;
//地址特殊处理，有需要再实现

//调用ajax函数
var SendAjax = function (url, data, asy, success,
    error,//errormsg,errorfunction
    timeout)//timeoutmsg,timeoutset,timeoutfunction
{
    if (asy == undefined || asy == null)
        asy = true; //默认使用异步
    if (timeout == undefined || timeout.timeoutset == null || timeout.timeoutset < 1000) {
        timeout = {
            timeoutset: -1,
        }
        
    };
    if (error.errorfunction == undefined || error.errorfunction == null) {
        error = {
            errorfunction: new function () { },
        }

    };
    if (timeout.timeoutfunction == undefined || timeout.timeoutfunction == null) {
        timeout = {
            timeoutfunction: new function () { },
        }

    };
    if (success == undefined || success == null) {
        success = new function () { };
    };
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        timeout: timeout.timeoutset,
        url: url,
        data: data,
        async: asy,
        //traditional: true,
        dataType: "json",
        success: success,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (errorThrown == 'timeout') {
                //SendAjax.abort();
                timeout.timeoutfunction;
                if (timeout.timeoutmsg != undefined && timeout.timeoutmsg != null && timeout.timeoutmsg != '')
                    alert(timeout.timeoutmsg);
            }
            else {
                error.errorfunction;
                if (error.errormsg != undefined && error.errormsg != null && error.errormsg != '')
                    alert(error.errormsg);
            }

        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            //status有success,error,timeout等值的情况
        },
    });
}

//获取页面间传递的参数
function GetRequest() {

    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            var tmp = strs[i].split("=")[1];
            tmp = decodeURI(tmp);
            theRequest[strs[i].split("=")[0]] = tmp;
        }
    }
    return theRequest;
}




//增加一天
function addDate(date, days) {
    date = date.replace(/-/g, "/");
    var tempd = new Date(date);
    tempd = +tempd + days * 24 * 60 * 60 * 1000;//一定要2个加号
    var d = new Date(tempd);
    
    var month = d.getMonth() + 1;
    var day = d.getDate();
    var hour = d.getHours();
    var minutes = d.getMinutes();
    var seconds = d.getSeconds();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    var val = d.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    return val;
}


/**  
* 日期范围工具类  
*/
var dateRangeUtil = (function () {
    /***  
    * 获得当前时间  
    */
    this.getCurrentDate = function () {
        return new Date();
    };

    /***  
    * 获得本周起止时间  
    */
    this.getCurrentWeek = function () {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间    
        var currentDate = this.getCurrentDate();
        //返回date是一周中的某一天    
        var week = currentDate.getDay();
        //返回date是一个月中的某一天    
        var month = currentDate.getDate();

        //一天的毫秒数    
        var millisecond = 1000 * 60 * 60 * 24;
        //减去的天数    
        var minusDay = week != 0 ? week - 1 : 6;
        //alert(minusDay);    
        //本周 周一    
        var monday = new Date(currentDate.getTime() - (minusDay * millisecond));
        //本周 周日    
        var sunday = new Date(monday.getTime() + (6 * millisecond));
        //添加本周时间    
        startStop.push(monday); //本周起始时间    
        //添加本周最后一天时间    
        startStop.push(sunday); //本周终止时间    
        //返回    
        return startStop;
    };

    /***  
    * 获得本月的起止时间  
    */
    this.getCurrentMonth = function (dateobj) {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间   
        if (dateobj) {
            var currentDate = dateobj;
        }
        else{
            var currentDate = this.getCurrentDate();
        }
        //获得当前月份0-11    
        var currentMonth = currentDate.getMonth();
        //获得当前年份4位年    
        var currentYear = currentDate.getFullYear();
        //求出本月第一天    
        var firstDay = new Date(currentYear, currentMonth, 1);


        //当为12月的时候年份需要加1    
        //月份需要更新为0 也就是下一年的第一个月    
        if (currentMonth == 11) {
            currentYear++;
            currentMonth = 0; //就为    
        } else {
            //否则只是月份增加,以便求的下一月的第一天    
            currentMonth++;
        }


        //一天的毫秒数    
        var millisecond = 1000 * 60 * 60 * 24;
        //下月的第一天    
        var nextMonthDayOne = new Date(currentYear, currentMonth, 1);
        //求出上月的最后一天    
        var lastDay = new Date(nextMonthDayOne.getTime() - millisecond);

        //添加至数组中返回    
        startStop.push(firstDay);
        startStop.push(lastDay);
        //返回    
        return startStop;
    };

    /**  
    * 得到本季度开始的月份  
    * @param month 需要计算的月份  
    ***/
    this.getQuarterSeasonStartMonth = function (month) {
        var quarterMonthStart = 0;
        var spring = 0; //春    
        var summer = 3; //夏    
        var fall = 6;   //秋    
        var winter = 9; //冬    
        //月份从0-11    
        if (month < 3) {
            return spring;
        }

        if (month < 6) {
            return summer;
        }

        if (month < 9) {
            return fall;
        }

        return winter;
    };

    /**  
    * 获得该月的天数  
    * @param year年份  
    * @param month月份  
    * */
    this.getMonthDays = function (year, month) {
        //本月第一天 1-31    
        var relativeDate = new Date(year, month, 1);
        //获得当前月份0-11    
        var relativeMonth = relativeDate.getMonth();
        //获得当前年份4位年    
        var relativeYear = relativeDate.getFullYear();

        //当为12月的时候年份需要加1    
        //月份需要更新为0 也就是下一年的第一个月    
        if (relativeMonth == 11) {
            relativeYear++;
            relativeMonth = 0;
        } else {
            //否则只是月份增加,以便求的下一月的第一天    
            relativeMonth++;
        }
        //一天的毫秒数    
        var millisecond = 1000 * 60 * 60 * 24;
        //下月的第一天    
        var nextMonthDayOne = new Date(relativeYear, relativeMonth, 1);
        //返回得到上月的最后一天,也就是本月总天数    
        return new Date(nextMonthDayOne.getTime() - millisecond).getDate();
    };

    /**  
    * 获得本季度的起止日期  
    */
    this.getCurrentSeason = function (dateobj) {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间 
        if (dateobj) {
            var currentDate = dateobj;
        }
        else {
            var currentDate = this.getCurrentDate();
        }
        
        //获得当前月份0-11    
        var currentMonth = currentDate.getMonth();
        //获得当前年份4位年    
        var currentYear = currentDate.getFullYear();
        //获得本季度开始月份    
        var quarterSeasonStartMonth = this.getQuarterSeasonStartMonth(currentMonth);
        //获得本季度结束月份    
        var quarterSeasonEndMonth = quarterSeasonStartMonth + 2;

        //获得本季度开始的日期    
        var quarterSeasonStartDate = new Date(currentYear, quarterSeasonStartMonth, 1);
        //获得本季度结束的日期    
        var quarterSeasonEndDate = new Date(currentYear, quarterSeasonEndMonth, this.getMonthDays(currentYear, quarterSeasonEndMonth));
        //加入数组返回    
        startStop.push(quarterSeasonStartDate);
        startStop.push(quarterSeasonEndDate);
        //返回    
        return startStop;
    };

    /***  
    * 得到本年的起止日期  
    *   
    */
    this.getCurrentYear = function (dateobj) {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间    
        if (dateobj) {
            var currentDate = dateobj;
        }
        else {
            var currentDate = this.getCurrentDate();
        }
        //获得当前年份4位年    
        var currentYear = currentDate.getFullYear();

        //本年第一天    
        var currentYearFirstDate = new Date(currentYear, 0, 1);
        //本年最后一天    
        var currentYearLastDate = new Date(currentYear, 11, 31);
        //添加至数组    
        startStop.push(currentYearFirstDate);
        startStop.push(currentYearLastDate);
        //返回    
        return startStop;
    };

    /**  
    * 返回上一个月的第一天Date类型  
    * @param year 年  
    * @param month 月  
    **/
    this.getPriorMonthFirstDay = function (year, month) {
        //年份为0代表,是本年的第一月,所以不能减    
        if (month == 0) {
            month = 11; //月份为上年的最后月份    
            year--; //年份减1    
            return new Date(year, month, 1);
        }
        //否则,只减去月份    
        month--;
        return new Date(year, month, 1);;
    };

    /**  
    * 获得上一月的起止日期  
    * ***/
    this.getPreviousMonth = function () {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间    
        var currentDate = this.getCurrentDate();
        //获得当前月份0-11    
        var currentMonth = currentDate.getMonth();
        //获得当前年份4位年    
        var currentYear = currentDate.getFullYear();
        //获得上一个月的第一天    
        var priorMonthFirstDay = this.getPriorMonthFirstDay(currentYear, currentMonth);
        //获得上一月的最后一天    
        var priorMonthLastDay = new Date(priorMonthFirstDay.getFullYear(), priorMonthFirstDay.getMonth(), this.getMonthDays(priorMonthFirstDay.getFullYear(), priorMonthFirstDay.getMonth()));
        //添加至数组    
        startStop.push(priorMonthFirstDay);
        startStop.push(priorMonthLastDay);
        //返回    
        return startStop;
    };

    /**  
    * 获得上一周的起止日期  
    * **/
    this.getPreviousWeek = function () {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间    
        var currentDate = this.getCurrentDate();
        //返回date是一周中的某一天    
        var week = currentDate.getDay();
        //返回date是一个月中的某一天    
        var month = currentDate.getDate();
        //一天的毫秒数    
        var millisecond = 1000 * 60 * 60 * 24;
        //减去的天数    
        var minusDay = week != 0 ? week - 1 : 6;
        //获得当前周的第一天    
        var currentWeekDayOne = new Date(currentDate.getTime() - (millisecond * minusDay));
        //上周最后一天即本周开始的前一天    
        var priorWeekLastDay = new Date(currentWeekDayOne.getTime() - millisecond);
        //上周的第一天    
        var priorWeekFirstDay = new Date(priorWeekLastDay.getTime() - (millisecond * 6));

        //添加至数组    
        startStop.push(priorWeekFirstDay);
        startStop.push(priorWeekLastDay);

        return startStop;
    };

    /**  
    * 得到上季度的起始日期  
    * year 这个年应该是运算后得到的当前本季度的年份  
    * month 这个应该是运算后得到的当前季度的开始月份  
    * */
    this.getPriorSeasonFirstDay = function (year, month) {
        var quarterMonthStart = 0;
        var spring = 0; //春    
        var summer = 3; //夏    
        var fall = 6;   //秋    
        var winter = 9; //冬    
        //月份从0-11    
        switch (month) {//季度的其实月份    
            case spring:
                //如果是第一季度则应该到去年的冬季    
                year--;
                month = winter;
                break;
            case summer:
                month = spring;
                break;
            case fall:
                month = summer;
                break;
            case winter:
                month = fall;
                break;

        };

        return new Date(year, month, 1);
    };

    /**  
    * 得到上季度的起止日期  
    * **/
    this.getPreviousSeason = function () {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间    
        var currentDate = this.getCurrentDate();
        //获得当前月份0-11    
        var currentMonth = currentDate.getMonth();
        //获得当前年份4位年    
        var currentYear = currentDate.getFullYear();
        //上季度的第一天    
        var priorSeasonFirstDay = this.getPriorSeasonFirstDay(currentYear, currentMonth);
        //上季度的最后一天    
        var priorSeasonLastDay = new Date(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2, this.getMonthDays(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2));
        //添加至数组    
        startStop.push(priorSeasonFirstDay);
        startStop.push(priorSeasonLastDay);
        return startStop;
    };

    /**  
    * 得到去年的起止日期  
    * **/
    this.getPreviousYear = function () {
        //起止日期数组    
        var startStop = new Array();
        //获取当前时间    
        var currentDate = this.getCurrentDate();
        //获得当前年份4位年    
        var currentYear = currentDate.getFullYear();
        currentYear--;
        var priorYearFirstDay = new Date(currentYear, 0, 1);
        var priorYearLastDay = new Date(currentYear, 11, 1);
        //添加至数组    
        startStop.push(priorYearFirstDay);
        startStop.push(priorYearLastDay);
        return startStop;
    };
    return this;

})();

/** 
 * 得到两个时间之间为几个月和几天 2013-8-31 2013-10-5 
 */
function getMonthAndDay(startdate, enddate) {
    var month = 0;
    var day = 0;
    if (enddate.getDate() >= startdate.getDate()) {
        month = (enddate.getFullYear() - startdate.getFullYear()) * 12
                + enddate.getMonth() - startdate.getMonth()+1;//校准处理20160810
        day = enddate.getDate() - startdate.getDate();
    } else {
        if (startdate.getDate() == getlastDay(startdate)) {
            if (enddate.getDate() == getlastDay(enddate)) {
                month = enddate.getFullYear() - startdate.getFullYear() * 12
                        + enddate.getMonth() - startdate.getMonth();
                day = 0;
            } else {
                month = enddate.getFullYear() - startdate.getFullYear() * 12
                        + enddate.getMonth() - startdate.getMonth() - 1;
                day = startdate.getDate();

            }
        } else {
            if (enddate.getDate() == getlastDay(enddate)) {
                month = enddate.getFullYear() - startdate.getFullYear() * 12
                        + enddate.getMonth() - startdate.getMonth();
                day = 0;
            } else {
                month = enddate.getFullYear() - startdate.getFullYear() * 12
                        + enddate.getMonth() - startdate.getMonth() - 1;

                var lastmonthday = getlastmonthDat(enddate);
                day = lastmonthday - startdate.getDate() + enddate.getDate();

            }
        }
    }
    return {
        "month": month,
        "day": day
    };
}

/** 
 * 得到上一个月有多少天 
 */
function getlastmonthDat(date) {
    date.setDate(1);
    date.setDate(date.getDate() - 1);
    return date.getDate();
}
/** 
 * 得到date 所在的月有多少天 
 *  
 * @param date 
 */
function getlastDay(date) {
    date.setMonth(date.getMonth() + 1);
    date.setDate(1);
    date.setDate(date.getDate() - 1);
    return date.getDate();
}

/**
* 时间对象的格式化;
*/
Date.prototype.format = function (format) {
    /*
    * eg:format="yyyy-MM-dd hh:mm:ss";
    */
    var o = {
        "M+": this.getMonth()+1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarte
        "S": this.getMilliseconds()
        // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
        - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
            ? o[k]
            : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

/**
* 时间改变,返回时间;
*/
Date.prototype.adddays = function (days) {
    tempd = +this + days * 24 * 60 * 60 * 1000;//一定要2个加号
    var d = new Date(tempd);
    return d;
    
}



//计算2个日期时间差 可以是string 也可以是 datatime
function dateDiff(date1, date2) {
    var type1 = typeof date1, type2 = typeof date2;
    if (type1 == 'string')
        date1 = stringToTime(date1);
    else if (date1.getTime)
        date1 = date1.getTime();
    if (type2 == 'string')
        date2 = stringToTime(date2);
    else if (date2.getTime)
        date2 = date2.getTime();
    return (date2 - date1) / 1000 / 60 / 60 / 24;//除1000是毫秒，不加是秒  
    
}

//字符串转成Time(dateDiff)所需方法   
function stringToTime(string) {
    var f = string.split(' ', 2);
    var d = (f[0] ? f[0] : '').split('-', 3);
    var t = (f[1] ? f[1] : '').split(':', 3);
    return (new Date(
    parseInt(d[0], 10) || null,
    (parseInt(d[1], 10) || 1) - 1,
    parseInt(d[2], 10) || null,
    parseInt(t[0], 10) || null,
    parseInt(t[1], 10) || null,
    parseInt(t[2], 10) || null)).getTime();
}

//比较两个日期大小  
function dateCompare(startDate, endDate) {
    var aStart = startDate.split('-'); //转成成数组，分别为年，月，日，下同  
    var aEnd = endDate.split('-');
    var startDateTemp = aStart[0] + "/" + aStart[1] + "/" + aStart[2];
    var endDateTemp = aEnd[0] + "/" + aEnd[1] + "/" + aEnd[2];
    if (startDateTemp > endDateTemp)
        return true;
    else
        return false;
}

//判断文件类型 后缀取出
function filetype_search_zzbds(file_name) {  
    var result = /\.[^\.]+/.exec(file_name);
    //alert(result)
    return result;
}

//判断文件大小
function filesize_get(fileobj) {
    var fileSize = 0;
    try {
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        if (isIE && !fileobj.files) {
            var filePath = fileobj.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = fileobj.files[0].size;
        }
        fileSize = Math.round(fileSize / 1024 * 100) / 100; //单位为KB
        return fileSize;
    }
    catch (ex) {
        return 0;

    };
    
}

//文件大小处理
function filesize_show(filesize_b) {
    try{
        var a = parseFloat(filesize_b);
        if (a < 1024) {//byte
            return filesize_b+"B";
        }
        else if (a < 1024 * 1024) {//kb
            return (a / 1024).toFixed(2).toString() + "K";
        }
        else if (a < 1024 * 1024 * 1024) {//mb
            return (a / (1024 * 1024)).toFixed(2).toString() + "M";
        }
        else if (a < 1024 * 1024 * 1024 * 1024) {//tb
            return (a / (1024 * 1024 * 1024)).toFixed(2).toString() + "T";
        }
        else {//PB以上不考虑
            return "";
        }
    }
    catch(ex){
        return "";
    }
}

//下载文件打开
function filedown(fileurl) {
    location.href = fileurl;
}

//计算长度
String.prototype.getBytesLength = function () {
    return this.replace(/[^\x00-\xff]/gi, "--").length;
}

///获取中文字符长度
String.prototype.getCHBytes = function () {
    var cArr = this.match(/[^\x00-\xff]/ig);
    return this.length + (cArr == null ? 0 : cArr.length);
}

//根据输入参数 控制显示string 长度 超过截断 补省略号
string_cut_show = function (strobj, lset) {

    var ret_str = strobj.substr(0, lset);
    if (strobj.length > lset) {
        ret_str = ret_str + '..';
    }

    return ret_str
 
}

//数字前面补零
function PrefixInterger(num, n) {
    return (Array(n).join(0) + num).slice(-n);
}

/** 
 * 计算两日期时间差 
 * @param   interval 计算类型：D是按照天、H是按照小时、M是按照分钟、S是按照秒、T是按照毫秒 
 * @param  date1 起始日期  格式为年月格式 为2012-06-20 
 * @param  date2 结束日期 
 * @return  
 */
function countTimeLength(interval, date1, date2) {
    var objInterval = { 'D': 1000 * 60 * 60 * 24, 'H': 1000 * 60 * 60, 'M': 1000 * 60, 'S': 1000, 'T': 1 };
    interval = interval.toUpperCase();
    var dt1 = Date.parse(date1.replace(/-/g, "/"));
    var dt2 = Date.parse(date2.replace(/-/g, "/"));
    try {
        return ((dt2 - dt1) / objInterval[interval]).toFixed(2);//保留两位小数点  
    } catch (e) {
        return e.message;
    }
}

//调用接口 进行表的增和改时，字段内容不能有 英文的单引号 否则报错
//发现：双数个单引号可以保存,但是只能保存下来一个单引号。奇数直接报错。
//用html的转移字符 &quot 双引号 / &apos 单引号

//单引号 变成 apos
apos_dyh_convert = function (strtemp) {
    ret_str = strtemp.replace(/'/g, '&apos;');
   
    return ret_str;
}
//apos 变成 单引号
dyh_apos_convert = function (strtemp) {
    ret_str = strtemp.replace(/&apos;/g, "'");

    return ret_str;
    
}

//yyyy-MM-dd hh:mm:ss 格式 时间正则 有闰年 2月份判断
//var reg_yyyyMMdd_hhmmss = /^(((([0-9]{2}([02468][048])|([13579][26])))(-)(2|02)(-)(([1-9])|([0][1-9])|([1-2][0-9])))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([1-9])|([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([1-9])|([0][1-9])|([1-2][0-9])|30|31)))|(([0-9]{4})(-)((([0]{0,1}(4|6))|11))(-)(([1-9])|([0][1-9])|([1-2][0-9])|30)))\s(20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;
//back 20160907
//var reg_yyyyMMdd_hhmmss = /^(((([0-9]{2}([02468][048])|([13579][26])))(-)(2|02)(-)(([1-9])|([0][1-9])|([1-2][0-9])))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([1-9])|([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([1-9])|([0][1-9])|([1-2][0-9])|30|31)))|(([0-9]{4})(-)((([0]{0,1}(4|6|9))|11))(-)(([1-9])|([0][1-9])|([1-2][0-9])|30)))\s(20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;


var reg_yyyyMMdd_hhmmss = /^(((([0-9]{2}(([02468][048])|([13579][26]))))(-)(2|02)(-)(([1-9])|([0][1-9])|([1-2][0-9])))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([1-9])|([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([1-9])|([0][1-9])|([1-2][0-9])|30|31)))|(([0-9]{4})(-)((([0]{0,1}(4|6|9))|11))(-)(([1-9])|([0][1-9])|([1-2][0-9])|30)))\s(20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;



var reg_test = /^((([0-9]{2}([02468][048])|([13579][26])))(-)(2|02)(-)(([0][1-9])|([1-2][0-9]))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0][1-9])|([1-2][0-9])|30|31)))|(([0-9]{4})(-)((([0]{0,1}(4|6))|11))(-)(([0][1-9])|([1-2][0-9])|30)))\s(20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;

//时间控件初始化 只有日期
var form_date_initial = function () {
    $('.form_date').datetimepicker({
        language: 'zh-CN',/*加载日历语言包，可自定义*/
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

};

//时间控件初始化 只有月
var form_month_initial = function () {
    $('.form_datemonth').datetimepicker({
        format:'yyyy-mm',
        language: 'zh-CN',/*加载日历语言包，可自定义*/
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 3,
        minView: 3,
        forceParse: 0
    });

};

//时间控件初始化 只有年
var form_year_initial = function () {
    $('.form_dateyear').datetimepicker({
        format: 'yyyy',
        language: 'zh-CN',/*加载日历语言包，可自定义*/
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 4,
        minView: 4,
        forceParse: 0
    });

};



//时间控件初始化 带分钟
var form_datetime_initial = function () {
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',/*加载日历语言包，可自定义*/
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format:'yyyy-mm-dd hh:ii:00'
    });
};

//初始化 工程分部分项树形结构
var get_projetcttree = function (uid, uidnodata, uiding) {
    if (uiding && uidnodata) {
        $('#' + uidnodata).hide();
        $('#' + uiding).show();
    };
    var zNodes = [];
    var ul = document.getElementById(uid);

    var treeinfo = get_projecttree_info();//工程结构树行结构对象获取
    var obj_ret;
    if (treeinfo.length > 0) {
        if (uiding && uidnodata) {
            $('#' + uiding).hide();
        };
        //
        for (var i = 0; i < treeinfo.length; i++) {
            //
            var obj = treeinfo[i];
            //树里面保存的项目数据，TAG\PLEVEL
            var project_info = {};//暂时不用 根据实际情况再说
            //project_info.PLEVEL = obj.PLEVEL;
            //project_info.TAG = obj.TAG;

            //第一层展开 后面默认闭合 可以看到全貌
            if (obj.PLEVEL == '1') {
                open_t = true;
            }
            else {
                open_t = false;
            };
            //健康检测\强电：层级到分部结束
            //弱电：层级到分项
            //备品备件：层级到单位
            if (obj.PLEVEL == '4') {//到4层级结束
                parent_t = false;
            }
            else if (obj.PLEVEL == '3' && (obj.TAG == '2'||obj.TAG == '3')) {//强电、健康监测 到3层级-分项结束
                parent_t = false;
            }
            else if (obj.PLEVEL == '2' && obj.TAG == '4') {//备品备件 到2层级结束
                parent_t = false;
            }
            else {
                parent_t = true;
            };

            var node = { id: obj.PROJECTID, pId: obj.P_PROJECTID, name: obj.PNAME, open: open_t, isParent: parent_t, data: project_info, checked: false, };
            zNodes.push(node);
        }


        //树的元素dom初始化
        if (!ul.classList.contains("ztree"))
            ul.classList.add("ztree");

        var setting = {
            check: {
                enable: true,
                chkStyle: 'checkbox',
                radioType: "level",
                type: { "Y": "s", "N": "s" }
            },
            view: {
                fontCss: function (treeId, node) {
                    return node.isParent ? { "font-size": "12px" } : { "font-size": "12px" };
                },
                showLine: false,
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {//回调绑定
                onClick: function (e, treeId, treeNode) {
                    var zTreeobj = $.fn.zTree.getZTreeObj(treeId);

                    //展开树
                    zTreeobj.expandNode(treeNode, !(treeNode.open), false, false);
                    //最下面一层 点击的时候 变为checked
                    if (!treeNode.isParent) {
                        zTreeobj.checkNode(treeNode, !(treeNode.checked), true);//点击的对象都变为checked
                    };
                }
            },

        };
        //树初始化
        obj_ret=$.fn.zTree.init($(ul), setting, zNodes);
        return obj_ret;
    }
    else {
        if (uiding && uidnodata) {
            $('#' + uiding).hide();
            $('#' + uidnodata).show();
        };
        return null;
    };


}

//接口获取 工程结构
var get_projecttree_info = function () {
    var arr_treeinfo = new Array();
    var data = {};
    SendAjax(projectdata_service, JSON.stringify(data), false,
        function (datastr) {
            if (datastr) {
                var result = eval("(" + datastr + ")");
                if (result && result.length > 0) {
                    arr_treeinfo = result;
                }
            }
        },
        { 'errormsg': '调取信息失败', 'errorfunction': null },
        { 'timeoutmsg': '调取信息超时', 'timeoutset': -1, 'timeoutfunction': null });
    return arr_treeinfo;
}


//初始化 工程分部分项树形结构 要根据选择的是 整体/单位/分布/分项 来进行显示（下一级的内容隐藏）
//level: 1 的话 只显示第一层 / 以此类推
var get_projetcttree_ex = function (uid, uidnodata, uiding, level) {
    if (uiding && uidnodata) {
        $('#' + uidnodata).hide();
        $('#' + uiding).show();
    };
    var zNodes = [];
    var ul = document.getElementById(uid);

    var treeinfo = get_projecttree_info();//工程结构树行结构对象获取
    var obj_ret;
    if (treeinfo.length > 0) {
        if (uiding && uidnodata) {
            $('#' + uiding).hide();
        };
        //
        for (var i = 0; i < treeinfo.length; i++) {
            //
            var obj = treeinfo[i];
            //树里面保存的项目数据，TAG\PLEVEL
            var project_info = {};//暂时不用 根据实际情况再说
            //project_info.PLEVEL = obj.PLEVEL;
            //project_info.TAG = obj.TAG;

            //第一层展开 后面默认闭合 可以看到全貌
            if (obj.PLEVEL == '1') {
                open_t = true;
            }
            else {
                open_t = false;
            };
            //强电、健康检测：层级到分部结束
            //弱电：层级到分项
            //备品备件：层级到单位
            if (obj.PLEVEL == '4') {//到4层级结束
                parent_t = false;
            }
            else if (obj.PLEVEL == '3' && (obj.TAG == '2'||obj.TAG == '3')) {//强电 到3层级结束
                parent_t = false;
            }
            else if (obj.PLEVEL == '2' && obj.TAG == '4') {//备品备件 到2层级结束
                parent_t = false;
            }
            else {
                parent_t = true;
            };
            //根据 统计选择的层级 显示树的结构
            hidden_t = false;
            //if (level) {
            //    if (parseFloat(obj.PLEVEL) > level) {
            //        hidden_t = true;
            //    }
            //};

            //项目、单位工程进行统计（数量较少）默认全选
            //分部、分项工程进行统计（数量庞大）默认不选
            checked_t = false;
            if (level) {
                if (level < 3 && parseFloat(obj.PLEVEL) == level) {
                    checked_t = true;
                }
            };
            //根据 level 显示checkbox
            //例如：选择单位工程 2层有checkbox；选择分部 3层有checkbox
            chkDisabled_t = true;
            if (level) {
                if (parseFloat(obj.PLEVEL) == level) {
                    chkDisabled_t = false;
                }
            };

            var node = { id: obj.PROJECTID, pId: obj.P_PROJECTID, name: obj.PNAME, open: open_t, isParent: parent_t, data: project_info, checked: checked_t, isHidden: hidden_t, chkDisabled: chkDisabled_t,};
            zNodes.push(node);
        }


        //树的元素dom初始化
        if (!ul.classList.contains("ztree"))
            ul.classList.add("ztree");

        var setting = {
            check: {
                enable: true,
                chkStyle: 'checkbox',
                radioType: "level",
                type: { "Y": "s", "N": "s" }
            },
            view: {
                fontCss: function (treeId, node) {
                    return node.isParent ? { "font-size": "12px" } : { "font-size": "12px" };
                },
                showLine: false,
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {//回调绑定
                onClick: function (e, treeId, treeNode) {
                    var zTreeobj = $.fn.zTree.getZTreeObj(treeId);

                    //展开树
                    zTreeobj.expandNode(treeNode, !(treeNode.open), false, false);
                    //最下面一层 点击的时候 变为checked
                    if (!treeNode.isParent) {
                        zTreeobj.checkNode(treeNode, !(treeNode.checked), true);//点击的对象都变为checked
                    };
                }
            },

        };
        //树初始化
        obj_ret = $.fn.zTree.init($(ul), setting, zNodes);
        return obj_ret;
    }
    else {
        if (uiding && uidnodata) {
            $('#' + uiding).hide();
            $('#' + uidnodata).show();
        };
        return null;
    };


}

function getAllChildrenNodes(treeNode, ret_obj) {
    
    if (treeNode.isParent) {
        var childrenNodes = treeNode.children;
        if (childrenNodes) {
            for (var i = 0; i < childrenNodes.length; i++) {
                ret_obj.push(childrenNodes[i]);
                ret_obj = getAllChildrenNodes(childrenNodes[i], ret_obj);
            }
        }
    }
    return ret_obj;
}

// 清除字符串两边的空格 
String.prototype.trim = function() { 
  return this.replace(/(^\s*)|(\s*$)/g, ''); 
}; 



//表示全局唯一标识符 (GUID)。

function Guid(g){

     var arr = new Array(); //存放32位数值的数组

     

     if (typeof(g) == "string"){ //如果构造函数的参数为字符串

         InitByString(arr, g);

     }

     else{

         InitByOther(arr);

     }

     //返回一个值，该值指示 Guid 的两个实例是否表示同一个值。

     this.Equals = function(o){

         if (o && o.IsGuid){

              return this.ToString() == o.ToString();

         }

         else{

              return false;

         }

     }

     //Guid对象的标记

     this.IsGuid = function(){}

     //返回 Guid 类的此实例值的 String 表示形式。

     this.ToString = function(format){

         if(typeof(format) == "string"){

              if (format == "N" || format == "D" || format == "B" || format == "P"){

                   return ToStringWithFormat(arr, format);

              }

              else{

                   return ToStringWithFormat(arr, "D");

              }

         }

         else{

              return ToStringWithFormat(arr, "D");

         }

     }

     //由字符串加载

     function InitByString(arr, g){

         g = g.replace(/\{|\(|\)|\}|-/g, "");

         g = g.toLowerCase();

         if (g.length != 32 || g.search(/[^0-9,a-f]/i) != -1){

              InitByOther(arr);

         }

         else{

              for (var i = 0; i < g.length; i++){

                   arr.push(g[i]);

              }

         }

     }

     //由其他类型加载

     function InitByOther(arr){

         var i = 32;

         while(i--){

              arr.push("0");

         }

     }

     /*

     根据所提供的格式说明符，返回此 Guid 实例值的 String 表示形式。

     N  32 位： xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

     D  由连字符分隔的 32 位数字 xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx 

     B  括在大括号中、由连字符分隔的 32 位数字：{xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx} 

     P  括在圆括号中、由连字符分隔的 32 位数字：(xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx) 

     */

     function ToStringWithFormat(arr, format){

         switch(format){

              case "N":

                   return arr.toString().replace(/,/g, "");

              case "D":

                   var str = arr.slice(0, 8) + "-" + arr.slice(8, 12) + "-" + arr.slice(12, 16) + "-" + arr.slice(16, 20) + "-" + arr.slice(20,32);

                   str = str.replace(/,/g, "");

                   return str;

              case "B":

                   var str = ToStringWithFormat(arr, "D");

                   str = "{" + str + "}";

                   return str;

              case "P":

                   var str = ToStringWithFormat(arr, "D");

                   str = "(" + str + ")";

                   return str;

              default:

                   return new Guid();

         }

     }

}

//Guid 类的默认实例，其值保证均为零。

Guid.Empty = new Guid();

//初始化 Guid 类的一个新实例。

Guid.NewGuid = function(){

     var g = "";

     var i = 32;

     while(i--){

         g += Math.floor(Math.random()*16.0).toString(16);

     }

     return new Guid(g);

}
//百分数转小数
function toPoint(percent) {
    var str = percent.replace("%", "");
    str = str / 100;
    return str;
}

//小数转百分数
/**
*这里需要先用Number进行数据类型转换，然后去指定截取转换后的小数点后几位(按照四舍五入)，这里是截取i位，0.1266转换后会变成12.7%
*/
function toPercent(point,i){
    var str=Number(point*100).toFixed(i);
    str+="%";
    return str;
}


function sleep(d) {
    for (var t = Date.now() ; Date.now() - t <= d;);
}