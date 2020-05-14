function displayLoader(){
    $("#dvloader").css('display','block');
    $("body").addClass('overlay');
}
function hideLoader(){
    $("#dvloader").css('display','none');
    $("body").removeClass('overlay');
}
function readURL(input,id){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#'+id).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function removefile(imageFile){
        imageFile.val(''); 
}

