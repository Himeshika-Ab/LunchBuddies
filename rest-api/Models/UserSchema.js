/* 
Who's Hungry android application
Authors - IT16067134 & IT16058910
CTSE pair project
Rest API
*/

//import mongoose module
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

//schema definition
const UserSchema = new Schema({

    name: {
        type: String,
        required: true
    },
    phone: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    }
});

module.exports = UserSchema;

