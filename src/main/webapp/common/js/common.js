


/**
 *
 * @param base_uri 基础项目uri
 * @param show_id 省市县 在一起展示的id
 * @param province_value 选中省份
 * @param city_value 选中市
 * @param area_value 选中区县
 * ==========省 市县 分开展示
 * @param province_show_id  省显示的id
 * @param city_show_id
 * @param area_show_id
 */
function getProvinces(base_uri,show_id,province_value,city_value,area_value,province_show_id,city_show_id,area_show_id){

    var baseUri = ""
    var showId = ""
    var province = ""
    var city = ""
    var area = ""

    var province_show = ""
    var city_show = ""
    var area_show = ""

    baseUri = base_uri
    showId = show_id
    province = province_value
    city = city_value
    area = area_value

    province_show = province_show_id
    city_show = city_show_id
    area_show = area_show_id

    var provinceSelect = "provinceSelect_"+province_show_id
    var citySelect = "citySelect_"+city_show_id
    var areaSelect = "areaSelect_"+area_show_id

    if(!province_show_id){
        province_show_id = "province"
    }
    if(!city_show_id){
        city_show_id = "city"
    }
    if(!area_show_id){
        area_show_id = "area"
    }
    /*console.log(province)
     console.log(city)
     console.log(area)
    console.log(show_id)
console.log(base_uri)
console.log(provinceSelect)
console.log(citySelect)
console.log(areaSelect)
    console.log('----')*/
    $.ajax({
        type:"post",
        url:baseUri+"/sys/area/ajaxProvince",
        data:{

        },
        success:function(data){
            if(data){
                //console.log(data)
                //data = eval(data)
                if(province_show){
                    $("#"+province_show).append("<select class='form-control' id='"+provinceSelect+"' name='"+province_show_id+"' onchange='changeProvinceToUpdateCitys(\""+baseUri+"\",\""+showId+"\",\""+province+"\",\""+city+"\",\""+area+"\",\""+province_show+"\",\""+city_show+"\",\""+area_show+"\",\""+provinceSelect+"\",\""+citySelect+"\",\""+areaSelect+"\""+")' ><option value=''>--请选择--</option></select>")
                    $("#"+city_show).append("<select class='form-control' id='"+citySelect+"' name='"+city_show_id+"' onchange='changeCityToUpdateAreas(\""+baseUri+"\",\""+showId+"\",\""+province+"\",\""+city+"\",\""+area+"\",\""+province_show+"\",\""+city_show+"\",\""+area_show+"\",\""+provinceSelect+"\",\""+citySelect+"\",\""+areaSelect+"\")' ><option value=''>--请选择--</option></select>")
                    $("#"+area_show).append("<select class='form-control' id='"+areaSelect+"' name='"+area_show_id+"'><option value=''>--请选择--</option></select>")
                }else {
                    $("#" + show_id).append("<select class='form-control' style='width:30%' id='"+provinceSelect+"' name='province' " +
                        "onchange='changeProvinceToUpdateCitys(\""+baseUri+"\",\""+showId+"\",\""+province+"\",\""+city+"\",\""+area+"\",\""+province_show+"\",\""+city_show+"\",\""+area_show+"\",\""+provinceSelect+"\",\""+citySelect+"\",\""+areaSelect+"\""+")' >" +
                        "<option value=''>--请选择--</option></select><select class='form-control' style='width:30%' id='"+citySelect+"' name='city' onchange='changeCityToUpdateAreas(\""+baseUri+"\",\""+showId+"\",\""+province+"\",\""+city+"\",\""+area+"\",\""+province_show+"\",\""+city_show+"\",\""+area_show+"\",\""+provinceSelect+"\",\""+citySelect+"\",\""+areaSelect+"\")' ><option value=''>--请选择--</option></select><select class='form-control' style='width:30%' id='"+areaSelect+"' name='area'><option value=''>--请选择--</option></select>")
                }
                for(var i=0;i<data.length;i++) {
                    //console.log(data[i].name)
                    $("#"+provinceSelect).append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                }
                if(province){
                    $("#"+provinceSelect).val(province)
                    changeProvinceToUpdateCitys(baseUri,showId,province,city,area,province_show,city_show,area_show,provinceSelect,citySelect,areaSelect)
                }
            }
        }
    })
}
function changeProvinceToUpdateCitys(baseUri,showId,province,city,area,province_show,city_show,area_show,provinceSelect,citySelect,areaSelect){

    var provinceId = $("#"+provinceSelect+" option:selected").val();
    if(!provinceId){
        $("#"+citySelect).empty().append("<option value=''>--请选择--</option>");
        $("#"+areaSelect).empty().append("<option value=''>--请选择--</option>");
        return;
    }
    $.ajax({
        type:"post",
        url:baseUri+"/sys/area/ajaxCity",
        data:{
            provinceId:provinceId
        },
        success:function(data){
            $("#"+citySelect).empty().append("<option value=''>--请选择--</option>")
            if(data){
                var flag = false
                for(var i=0;i<data.length;i++) {
                    $("#"+citySelect).append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                    if(data[i].id==city){
                        flag = true
                    }
                }
                if(city){
                    if(flag) {
                        $("#"+citySelect).val(city)
                    }
                    changeCityToUpdateAreas(baseUri,showId,province,city,area,province_show,city_show,area_show,provinceSelect,citySelect,areaSelect)
                }
            }

        }
    })

}
function changeCityToUpdateAreas(baseUri,showId,province,city,area,province_show,city_show,area_show,provinceSelect,citySelect,areaSelect){

    var cityId = $("#"+citySelect+" option:selected").val()
    if(!cityId){
        $("#"+areaSelect).empty().append("<option value=''>--请选择--</option>")
        return;
    }
    $.ajax({
        type:"post",
        url:baseUri+"/sys/area/ajaxArea",
        data:{
            cityId:cityId
        },
        success:function(data){
            $("#"+areaSelect).empty().append("<option value=''>--请选择--</option>")
            if(data){
                var flag = false
                for(var i=0;i<data.length;i++) {
                    $("#"+areaSelect).append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                    if(data[i].id==area){
                        flag = true
                    }
                }
                if(flag){
                    $("#"+areaSelect).val(area)
                }
            }

        }
    })

}




