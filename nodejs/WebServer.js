var express = require('express');
var WebServer = express();
var WebServerRootPath = "/web"

WebServer.use(express.static(__dirname + WebServerRootPath))

WebServer.post('/PostTest', function (req, res) {
    req.on('data', function (data) {
        obj = JSON.parse(data);
        res.send("Post OK");
    });
})

WebServer.listen(8080);