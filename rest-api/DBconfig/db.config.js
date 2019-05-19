/* 
Who's Hungry android application
Authors - IT16067134 & IT16058910
CTSE pair project
Rest API
*/

const mongoose = require('mongoose');
var UserSchema = require('../Models/UserSchema');
var FriendSchema = require('../Models/FriendSchema')


//registering models
mongoose.model('User',UserSchema);
mongoose.model('Friend',FriendSchema)

//Connecting to db
mongoose.connect('mongodb://admin:admin123@ds021731.mlab.com:21731/whoshungry',
    { useNewUrlParser: true },function (err) {
    if(err){
        console.log(err);
        process.exit(-1);
    }
    console.log('DB connected!');
});

module.exports = mongoose;