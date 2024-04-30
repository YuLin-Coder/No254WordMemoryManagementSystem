/*******************************************************************************\
 * by Ryze for plugs
 \*******************************************************************************/
(function ($) {
    // 'use strict';
    /** *******初始化******** */
    $.fn.ian = function (v) {
        $.fn.ian.o = this;
        return $.fn.ian;
    };

    $.ian = function () {
        return  $.ian;
    };


    /** *******图片切换（360°车型展示demo）******** */
    $.fn.ian.picChoose = function (options, arg2) {
        var $t = $.fn.ian.o;
        var imgsNum = $t.find("img").length;//有多少张图片
        var showNum = 0;//显示的是第几张 从0开始 默认显示的是第一张
        var w = $t.width();
        var sensitive = 10;//灵敏度
        $t.find("img:not(:eq(" + showNum + "))").hide();

        $t.live("mousemove", function (e) {
            var pprev = $t.data("mousemovep");

            if (!pprev || pprev == undefined || pprev == "undefined") {
                $t.data("mousemovep", {x: e.pageX, y: e.pageY});
                return false
            }
            if (e.pageX > pprev.x) {
                console.log(e.pageX - pprev.x);
                if (e.pageX - pprev.x > 2)
                    prevShow(showNum);
            } else {
                if (e.pageX - pprev.x < -2)
                    nextShow(showNum);
            }
            $t.data("mousemovep", {x: e.pageX, y: e.pageY});
        });

        var nextShow = function (n) {
            $t.find("img:eq(" + n + ")").hide();
            n = (n + 1) >= imgsNum ? 0 : (n + 1);
            console.log("next n=" + n);
            $t.find("img:eq(" + n + ")").show();
            showNum = n;
        };
        var prevShow = function (n) {
            $t.find("img:eq(" + n + ")").hide();
            n = (n - 1) < 0 ? (imgsNum - 1) : (n - 1);
            console.log("prev n=" + n);
            $t.find("img:eq(" + n + ")").show();
            showNum = n;
        };
    };


    /**ajax请求，得到的数据封存到dom**/
    var ajax = function (p, $t) {
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: p.url,
            data: p.param ? p.param : "",
            timeout: p.time ? p.time : 3000,
            beforeSend: function () {
                if (p.beforeSend) {
                    p.beforeSend();
                }
            }
        }).fail(function () {
            //   alert("ajax fail ");
            if (p.error) {
                p.error();
            }
        }).done(function (data) {
            if ($t) {
//                    $t.data("data", data);    //数据持久化
            }
            if (p.success) {
                p.success(data);
            }
        });
    };
    $.fn.ian.ajax = function (p) {
        var $t = $.fn.ian.o;
        if ($t.selector) {
//        $t.die("click");
            $(document).on("tap", $t, function () {
                ajax(p, $t);
            });
        }
    };
    $.ian.ajax = function (p) {
        ajax(p);
    };
    $.ian.ajax = function (url, params, succ, err,timeout) {
        ajax({"url": url, "param": params, "success": succ, "error": err,"time":timeout});
    };


    /****截取数据****/
    $.fn.ian.partData = function (p, pageNum, num) {
        var $t = $.fn.ian.o;
        var data = p || $t.data("data");
        if (!data) {
            console.log("暂无数据！！");
            return false;
        }
        return data.splice((pageNum - 1) * num, pageNum * num);
    }


    $.fn.ian.changePage = function () {
        var $t = $.fn.ian.o;
//        window.location = $t.selector;
        $.mobile.changePage($t, { transition: "" });
    }


    /********json to string***************/
    $.ian.toString = function (o) {
        var arr = [];
        var fmt = function (s) {
            if (typeof s == 'object' && s != null) return  $.ian.toString(s);
            return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
        }
        for (var i in o) arr.push("'" + i + "':" + fmt(o[i]));
        return '{' + arr.join(',') + '}';
    }


    /**********DATA 封装************/
    $.Params = {
        create: function () {
            var dat1a = {};
            dat1a.json = {};
            str1ing = "";
            dat1a.addParams = function (key, value) {
                dat1a.json[key] = value;
                //		var str= $.ian.toString(dat1a.json);
                //		alert(str);
                //        str1ing +=   "'"+key + "':'" + value+"',";
            };
            dat1a.toString = function () {
                return $.ian.toString(dat1a.json);
            };
            dat1a.toJson = function () {

                return dat1a.json;
//            return eval( '(' + dat1a.toString() + ')' );
            };

            dat1a.ajax = function (url, successfun, errorfun,timeout) {
                $.ian.ajax(url, dat1a.toJson(), function (data) {
                    //TODO  Success
                    successfun(data);
                }, function () {
                    //TODO error
                    errorfun();
                },timeout?timeout:3000);
            };
            return dat1a;
        }
    };


})(jQuery);

window.ParamJson = {};


/**
 * 中文转UTF-8
 * @param str
 * @returns {string}
 */
function encodeUtf8(str) {
//    var $t = $.fn.ian.o;
//    str = $t.selector || str;
    var words = str.trim();
    var rval = "";
    for (var i = 0; i < words.length; i++) {
        rval += function (v) {
            var s = escape(v);
            var sa = s.split("%");
            var retV = "";
            if (sa[0] != "") {
                retV = sa[0];
            }
            for (var i = 1; i < sa.length; i++) {
                if (sa[i].substring(0, 1) == "u") {
                    retV += function (s) {
                        var retS = "";
                        var tempS = "";
                        var ss = "";
                        if (s.length == 16) {
                            tempS = "1110" + s.substring(0, 4);
                            tempS += "10" + s.substring(4, 10);
                            tempS += "10" + s.substring(10, 16);
                            var sss = "0123456789ABCDEF";
                            for (var i = 0; i < 3; i++) {
                                retS += "\\x";
                                ss = tempS.substring(i * 8, (eval(i) + 1)
                                    * 8);
                                retS += sss.charAt(function (s) {
                                    var retV = 0;
                                    if (s.length == 4) {
                                        for (var i = 0; i < 4; i++) {
                                            retV += eval(s
                                                .charAt(i))
                                                * Math.pow(2,
                                                        3 - i);
                                        }
                                        return retV;
                                    }
                                    return -1;
                                }(ss.substring(0, 4)));
                                retS += sss.charAt(function (s) {
                                    var retV = 0;
                                    if (s.length == 4) {
                                        for (var i = 0; i < 4; i++) {
                                            retV += eval(s
                                                .charAt(i))
                                                * Math.pow(2,
                                                        3 - i);
                                        }
                                        return retV;
                                    }
                                    return -1;
                                }(ss.substring(4, 8)));
                            }
                            return retS;
                        }
                        return "";
                    }(function (s) {
                        var c = "";
                        var n;
                        var ss = "0123456789ABCDEF";
                        var digS = "";
                        for (var i = 0; i < s.length; i++) {
                            c = s.charAt(i);
                            n = ss.indexOf(c);
                            digS += function (n1) {
                                var s = "";
                                var n2 = 0;
                                for (var i = 0; i < 4; i++) {
                                    n2 = Math.pow(2, 3 - i);
                                    if (n1 >= n2) {
                                        s += '1';
                                        n1 = n1 - n2;
                                    } else
                                        s += '0';

                                }
                                return s;
                            }(eval(n));

                        }
                        return digS;
                    }(sa[i].substring(1, 5)));
                } else
                    retV += "%" + sa[i];
            }
            return retV;
        }(words[i]);
    }
    return rval;
};


/**
 * UTF-8码 转中文
 * @param strUtf8
 * @returns {string}
 */
function chineseFromUtf8Url(strUtf8) {
    var bstr = "";
    var nOffset = 0; //   processing   point   on   strUtf8

    if (strUtf8 == "")
        return   "";

    strUtf8 = strUtf8.toLowerCase();
    nOffset = strUtf8.indexOf("%e");
    if (nOffset == -1)
        return   strUtf8;

    while (nOffset != -1) {
        bstr += strUtf8.substr(0, nOffset);
        strUtf8 = strUtf8.substr(nOffset, strUtf8.length - nOffset);
        if (strUtf8 == "" || strUtf8.length < 9)       //   bad   string
            return   bstr;

        bstr += function (strYtf8) {
            var iCode, iCode1, iCode2;
            iCode = parseInt("0x" + strUtf8.substr(1, 2));
            iCode1 = parseInt("0x" + strUtf8.substr(4, 2));
            iCode2 = parseInt("0x" + strUtf8.substr(7, 2));

            return   String.fromCharCode(((iCode & 0x0F) << 12) |
                ((iCode1 & 0x3F) << 6) |
                (iCode2 & 0x3F));
        }(strUtf8.substr(0, 9));
        strUtf8 = strUtf8.substr(9, strUtf8.length - 9);
        nOffset = strUtf8.indexOf("%e");
    }

    return   bstr + strUtf8;
}

/**
 * 获取URL参数
 * @param name
 * @returns {*}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


/**
 * 动态加载JS和CSS
 */
var dynamicLoading = {
    css: function (path) {
        if (!path || path.length === 0) {
            throw new Error('argument "path" is required !');
        }
        var scripts = document.getElementsByTagName("link");
        //遍历查询页面中已存在有想要加载的js文件
        for (var i = 0; i < scripts.length; i++) {
            // console.log(scripts[i].href);
            if (scripts[i].href.indexOf(path) > -1) {
                return false;
            }
        }
        var head = document.getElementsByTagName('head')[0];
        var link = document.createElement('link');
        link.href = path;
        link.rel = 'stylesheet';
        link.type = 'text/css';
        head.appendChild(link);
    },
    js: function (path,fun) {
        if (!path || path.length === 0) {
            throw new Error('argument "path" is required !');
        }
        var scripts = document.getElementsByTagName("script");
        //遍历查询页面中已存在有想要加载的js文件
        for (var i = 0; i < scripts.length; i++) {
            if (scripts[i].src.indexOf(path) > -1) {
                fun();
                return false;
            }
        }
        $.getScript(path, function () {
            fun();
        });
//        var head = document.getElementsByTagName('head')[0];
//        var script = document.createElement('script');
//        script.src = path;
//        script.type = 'text/javascript';
//        head.appendChild(script);
    }, cssMore: function (arr) {
        for (var i = 0; i < css.length; i++) {
            this.css(arr[i]);
        }
    }, jsMore: function (arr, fun) {
        var i = 0;
        for (var j = 0; j < js.length; j++) {
            this.js(arr[j],function(){
                i++;
                console.log("i=="+i);
                if(arr.length==i){
                    fun();
                }
            });
        }


    }




};
