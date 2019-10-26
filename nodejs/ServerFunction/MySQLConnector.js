var mysql = require('mysql');
var connection = mysql.createConnection({
    host: '127.0.0.1',
    user: 'root',
    password: 'root',
    database: 'wrapper'
});

connection.connect();

connection.query('SELECT * FROM `scenes` WHERE 1', function (error, results, fields) {
    if (error) throw error;
    console.log('The solution is: ', results);
});