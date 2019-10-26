var ScenesTypeID = 0;

function ScenesIDCheck() {
    var mysql = require('mysql');
    var connection = mysql.createConnection({
        host: '127.0.0.1',
        user: 'root',
        password: 'root',
        database: 'wrapper'
    });

    connection.connect();
    connection.query('SELECT id FROM `scene_type` WHERE 1', function (error, results, fields) {
        ScenesTypeID = results[results.length - 1].id;
        connection.end();
        console.log(ScenesTypeID);
    });
};

ScenesIDCheck();