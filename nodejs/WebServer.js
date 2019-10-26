var express = require('express');
var WebServer = express();
var WebServerRootPath = "web/"

WebServer.get('/*.html', function (req, res) {
	res.sendFile(__dirname + "/" + WebServerRootPath + req.url);
})

WebServer.get('/*.js', function (req, res) {
	res.sendFile(__dirname + "/" + WebServerRootPath + req.url);
})

WebServer.get('/*.css', function (req, res) {
	res.sendFile(__dirname + "/" + WebServerRootPath + req.url);
})

WebServer.post('/*', function (req, res) {
	var post = '';     

    req.on('data', function(chunk){    
		post += chunk;
    });
 
    req.on('end', function(){    
        console.log(post);
    });
})

WebServer.listen(8080);