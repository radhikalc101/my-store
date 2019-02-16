function uploadMultipleFiles(files) {
    let formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/store/uploadMultipleFiles");

    xhr.onload = function() {

        let images = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
           let imageTags = images.map((image) => {
                return "<label><input type='radio' name='selectedImage' value='"+image.id+"'/><img style='margin:3px;height:100px;width:100px' src='/store/item/imageDisplay?id=" + image.id +"'/></label>";
            });
         $('.modal-body')[0].innerHTML+= imageTags.join('');

        }
    }
    xhr.send(formData);
}