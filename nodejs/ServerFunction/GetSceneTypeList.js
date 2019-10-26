var SceneTypeList = new Array();

function GetSceneTypeList() {
    var mysql = require('mysql');
    var connection = mysql.createConnection({
        host: '127.0.0.1',
        user: 'root',
        password: 'root',
        database: 'wrapper'
    });

    connection.connect();
    connection.query('SELECT name FROM `scene_type` WHERE 1', function (error, results, fields) {
        for (i = 0; i < results.length; i++) {
            SceneTypeList.push(results[i].name);
        }
        connection.end();
        console.log(SceneTypeList);
    });
};

GetSceneTypeList();