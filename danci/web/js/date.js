Date.prototype.format = function (format) {  
        var o = {  
            "M+": this.getMonth() + 1,  
            "d+": this.getDate(),  
            "h+": this.getHours(),  
            "m+": this.getMinutes(),  
            "s+": this.getSeconds(),  
            "q+": Math.floor((this.getMonth() + 3) / 3),  
            "S": this.getMilliseconds()  
        }  
        if (/(y+)/.test(format)) {  
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(format)) {  
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return format;  
    }  
    /**   
    *ת�����ڶ���Ϊ�����ַ���   
    * @param date ���ڶ���   
    * @param isFull �Ƿ�Ϊ��������������,   
    *               Ϊtrueʱ, ��ʽ��"2000-03-05 01:05:04"   
    *               Ϊfalseʱ, ��ʽ�� "2000-03-05"   
    * @return ����Ҫ��������ַ���   
    */    
    function getSmpFormatDate(date, isFull) {  
        var pattern = "";  
        if (isFull == true || isFull == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        } else {  
            pattern = "yyyy-MM-dd";  
        }  
        return getFormatDate(date, pattern);  
    }  
    /**   
    *ת����ǰ���ڶ���Ϊ�����ַ���   
    * @param date ���ڶ���   
    * @param isFull �Ƿ�Ϊ��������������,   
    *               Ϊtrueʱ, ��ʽ��"2000-03-05 01:05:04"   
    *               Ϊfalseʱ, ��ʽ�� "2000-03-05"   
    * @return ����Ҫ��������ַ���   
    */    
  
    function getSmpFormatNowDate(isFull) {  
        return getSmpFormatDate(new Date(), isFull);  
    }  
    /**   
    *ת��longֵΪ�����ַ���   
    * @param l longֵ   
    * @param isFull �Ƿ�Ϊ��������������,   
    *               Ϊtrueʱ, ��ʽ��"2000-03-05 01:05:04"   
    *               Ϊfalseʱ, ��ʽ�� "2000-03-05"   
    * @return ����Ҫ��������ַ���   
    */    
  
    function getSmpFormatDateByLong(l, isFull) {  
        return getSmpFormatDate(new Date(l), isFull);  
    }  
    /**   
    *ת��longֵΪ�����ַ���   
    * @param l longֵ   
    * @param pattern ��ʽ�ַ���,���磺yyyy-MM-dd hh:mm:ss   
    * @return ����Ҫ��������ַ���   
    */    
  
    function getFormatDateByLong(l, pattern) {  
        return getFormatDate(new Date(l), pattern);  
    }  
    /**   
    *ת�����ڶ���Ϊ�����ַ���   
    * @param l longֵ   
    * @param pattern ��ʽ�ַ���,���磺yyyy-MM-dd hh:mm:ss   
    * @return ����Ҫ��������ַ���   
    */    
    function getFormatDate(date, pattern) {  
        if (date == undefined) {  
            date = new Date();  
        }  
        if (pattern == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        }  
        return date.format(pattern);  
    } 
	
	 alert(getSmpFormatDateByLong(1436030351344,"yyyy-MM-dd hh:mm:ss")); 