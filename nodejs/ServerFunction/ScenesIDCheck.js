var ScenesID = 0;

function ScenesIDCheck() {
    var mysql = require('mysql');
    var connection = mysql.createConnection({
        host: '127.0.0.1',
        user: 'root',
        password: 'root',
        database: 'wrapper'
    });

    connection.connect();
    connection.query('SELECT id FROM `scenes` WHERE 1', function (error, results, fields) {
        ScenesID = results[results.length - 1].id;
        connection.end();
        console.log(ScenesID);
    });
};

ScenesIDCheck();