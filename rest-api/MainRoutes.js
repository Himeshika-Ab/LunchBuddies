const express = require('express');
var Routes = express.Router();


var userRoutes = require('./Routes/UserRoutes');
var friendRoutes = require('./Routes/FriendRoutes');
Routes.use('/user/', userRoutes);
Routes.use('/friend/', friendRoutes);

module.exports = Routes;