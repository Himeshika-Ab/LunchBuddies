/* 
Who's Hungry android application
Authors - IT16067134 & IT16058910
CTSE pair project
Rest API
*/

var express = require('express');
var router = express.Router();
var controller = require('../Controllers/UserController');

//HTTP method: POST  inputs: user object
router.post('/addUser', function (req, res) {
    console.log("aaaa");
    console.log(req.body);
    controller.addUser(req.body).then(function (data) {
        res.status(data.status).send(data.message);
    }).catch(function (err) {
        res.status(500).send(err.message);
    });
});

//HTTP method: PUT , inputs: @query_param id, user json object(@req_body)
router.put('/:id', function (req, res) {
    controller.updateUser(req.params.id, req.body).then(function (data) {
        res.status(data.status).send(data.message);
    }).catch(function (err) {
        res.status(err.status).send(err.message);
    });
});

//HTTP method: GET
router.get('/',function(req,res){
    console.log('routes');
    controller.getUsers().then(function(data){
        res.status(data.status).send({data:data.userdata});
    }).catch(function(err){
        res.status(err.status).send(err.message);
    })
})

//HTTP method: DELETE , inputs: @query_param id
router.delete('/:id', function (req, res) {
    controller.deleteUser(req.params.id).then(function (data) {
        res.status(data.status).send(data.message);
    }).catch(function (err) {
        res.status(err.status).send(err.message);
    });
});



module.exports = router;