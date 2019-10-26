var FileUploadStatus = 0;

function FileUploadStatusInitial() {
    var UploadButton = document.getElementById('UploadButton');
    var FileObject = document.getElementById('UploadFile').files;
    var PostRequest = "FileUploadStatusInitial";
    var PostData = FileObject[0].name + "\r\n" + FileObject[0].size;
    var HttpRequest = new XMLHttpRequest();

    HttpRequest.onreadystatechange = function() {
        if(HttpRequest.readyState == 4 && HttpRequest.status == 200) {
            FileUploadStatus = 1;
            UploadButton.disabled = true;
            FileUpload(HttpRequest.responseText, FileObject[0]);
            FileUploadStatusCheckEnable(HttpRequest.responseText);
        }
    }

    HttpRequest.open("POST", PostRequest, true);
    HttpRequest.send(PostData);
}

function FileUpload(UploadFileName, FileObject) {
    var UploadButton = document.getElementById('UploadButton');
    var PostRequest = "";
    var PostData = "";
    var HttpRequest = new XMLHttpRequest();

    PostRequest = "FileUpload/" + UploadFileName + ";";
    PostData = FileObject;

    HttpRequest.onreadystatechange = function() {
        if(HttpRequest.readyState == 4 && HttpRequest.status == 200) {
            console.log(UploadFileName + "Upload OK");
        }
    }

    HttpRequest.open("POST", PostRequest, true);
    HttpRequest.send(PostData);
}

function FileUploadStatusCheckEnable(UploadFileName) {
    UploadStatusCheck(UploadFileName);

    if(FileUploadStatus == 1) {
        setTimeout(function() {
            UploadStatusCheckEnable(UploadFileName)
        }, 500);
    }
}

function FileUploadStatusCheck(UploadFileName) {
    var UploadButton = document.getElementById('UploadButton');
    var PostRequest = "FileUploadStatusCheck";
    var PostData = UploadFileName;
    var HttpRequest = new XMLHttpRequest();

    HttpRequest.onreadystatechange = function() {
        if(HttpRequest.readyState == 4 && HttpRequest.status == 200) {
            if(FileUploadStatus == 1) {
                UploadButton.innerText = HttpRequest.responseText;
            } else {
                UploadButton.innerText = '¤W¶Ç';
            }
        }
    }

    HttpRequest.open("POST", PostRequest, true);
    HttpRequest.send(PostData);
}