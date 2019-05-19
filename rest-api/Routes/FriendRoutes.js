/* 
Who's Hungry android application
Authors - IT16067134 & IT16058910
CTSE pair project
Rest API
*/

var express = require('express');
var router = express.Router();
var controller = require('../Controllers/FriendController');

//HTTP method: POST  inputs: friend object
router.post('/', function (req, res) {
    console.log("friend add");
    console.log(req.body);
    controller.addFriend(req.body).then(function (data) {
        res.status(data.status).send(data.message);
    }).catch(function (err) {
        res.status(500).send(err.message);
    });
});

//HTTP method: PUT , inputs: @query_param id, friend json object(@req_body)
router.put('/:id', function (req, res) {
    controller.updateFriend(req.params.id, req.body).then(function (data) {
        res.status(data.status).send(data.message);
    }).catch(function (err) {
        res.status(err.status).send(err.message);
    });
});
//HTTP method: GET
router.get('/',function(req,res){
    controller.getAllFriends().then(function(data){
        res.status(data.status).send({data:data.userdata});
    }).catch(function(err){
        res.status(err.status).send(err.message);
    })
})
//HTTP method: GET , inputs: @query_param id
router.get('/:id', function (req, res) {
    controller.getFriend(req.params.id).then(function (data) {
        res.status(data.status).send(data.data);
    }).catch(function (err) {
        res.status(err.status).send(err.message);
    });
});
//HTTP method: DELETE , inputs: @query_param id
router.delete('/:id', function (req, res) {
    controller.deleteFriend(req.params.id).then(function (data) {
        res.status(data.status).send(data.message);
    }).catch(function (err) {
        res.status(err.status).send(err.message);
    });
});


module.exports = router;