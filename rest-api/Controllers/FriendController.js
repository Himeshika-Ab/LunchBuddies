var mongoose = require('../DBconfig/db.config');
var FriendSchema = mongoose.model('Friend');
// var nodemailer = require('nodemailer');

var friendController = function () {
    this.addFriend = function (f) {
        console.log(f);
        return new Promise(function(resolve, reject){
            var friend = new  FriendSchema({

                firstName: f.firstName,
                secondName: f.secondName,
                phone:f.phone,
                enabled :f.enabled

            });
            friend.save().then(function () {
                resolve({status: 200, message: 'Friend details registered'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

    //get all added friends
    this.getAllFriends = function () {

        return new Promise(function (resolve, reject) {
            FriendSchema.find().exec().then(function (data) {
                resolve({status: 200, userdata: data});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

    //get friend by id
    this.getFriend = function (id) {
        return new Promise(function (resolve, reject) {
            FriendSchema.find({_id: id}).exec().then(function (friend) {
                resolve({status: 200, data: friend});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

    //update Friend details
    this.updateFriend = function (id, friendObj) {
        return new Promise(function (resolve, reject) {
            FriendSchema.update({_id: id}, friendObj).then(function () {
                resolve({status: 200, message: 'Friend details updated'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }



    //deleting friend
    this.deleteFriend = function (id) {
        return new Promise(function (resolve, reject) {
            FriendSchema.remove({_id: id}).then(function () {
                resolve({status: 200, message: 'Friend deleted'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        })

    }






}

module.exports = new friendController();