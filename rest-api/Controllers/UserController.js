/* 
Who's Hungry android application
Authors - IT16067134 & IT16058910
CTSE pair project
Rest API
*/

var mongoose = require('../DBconfig/db.config');
var UserSchema = mongoose.model('User');

var userController = function () {
    //add user details function
    this.addUser = function (u) {
        console.log(u);
        return new Promise(function(resolve, reject){
            var user = new  UserSchema({

                name: u.name,
                phone:u.phone,
                email: u.email,
                password:u.password

            });
            user.save().then(function () {
                resolve({status: 201, message: 'User details registered'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

    //update user details
    this.updateUser = function (id, u) {
        return new Promise(function (resolve, reject) {
            UserSchema.update({_id: id}, u).then(function () {
                resolve({status: 200, message: 'User details updated'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

    //get all registered users 
    this.getUsers = function () {
        console.log("get");
        return new Promise(function (resolve, reject) {
            console.log("get all"); 
            UserSchema.find().exec().then(function (data) {
                resolve({status: 200, userdata: data});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

     //deleting users
    this.deleteUser = function (id) {
        return new Promise(function (resolve, reject) {
            UserSchema.remove({_id: id}).then(function () {
                resolve({status: 200, message: 'User deleted'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        })
    
    }



}

module.exports = new userController();