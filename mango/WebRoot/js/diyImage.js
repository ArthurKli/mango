
var cropper;

function init()
{	
	cropper = new ImageCropper(300, 200, 180, 180);
	cropper.setCanvas("cropper");
	cropper.addPreview("preview180");
	//cropper.addPreview("preview100");
	//cropper.addPreview("preview50");

	if(!cropper.isAvaiable())
	{
		alert("Sorry, your browser doesn't support FileReader, please use Firefox3.6+ or Chrome10+ to run it.");
	}
}

function selectImage(fileList)
{
	//init();
	cropper.loadImage(fileList[0]);
}

function rotateImage(e)
{
	switch(e.target.id)
	{
		case "rotateLeftBtn":
			cropper.rotate(-90);
			break;
		case "rotateRightBtn":
			cropper.rotate(90);
			break;
	}
}

function saveImage()
{
	var imgData = cropper.getCroppedImageData(180, 180);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(e)
	{
		if(xhr.readyState == 4)
		{
			if(xhr.status == 200)
			{
				document.getElementById("status").innerHTML = "<font color='#f00'>上传成功！</font>";
			}
		}
	};
	
	xhr.open("post", "http://localhost:8080/uploadify/cut", true);
	var data = new FormData();
	data.append("sampleText", "flashlizi");
	data.append("sampleFile", imgData);
	xhr.send(data);
	xhr.onload = function(e) {

        if (this.status == 200) {

           alert(this.responseText);

        }

    };                    
}

function trace()
{
	if(typeof(console) != "undefined") console.log(Array.prototype.slice.apply(arguments).join(" "));
};
