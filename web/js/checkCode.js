var codePass;
function reloadCheckImg()
{
    $("img").attr("src", "img.jsp?t="+(new Date().getTime()));
}
function checkCodeImpl() {
    var $checkcode = $("#checkcodeId").val();
    $.post(
        "checkCode",
        "checkcode="+$checkcode ,
        function checkPassFunction(result){
            codePass = result;
        }
    );
}
$(document).ready(function(){
    $("#checkcodeId").blur(function (){
        checkCodeImpl();
    });
});
function checkCode(){
    var $checkcode = $("#checkcodeId").val();
    if($checkcode=="checkCodePass") {
        return true;
    }
    if(codePass==undefined) {
        alert("验证码异常:可以试着使验证码框失去焦点");
        return false;
    }
    if(codePass=="true")
        return true;
    else {
        alert("验证码出错");
        reloadCheckImg();
        return false;
    }
}