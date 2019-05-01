const express = require('express');
var Routes = express.Router();


var userRoutes = require('./Routes/UserRoutes');
Routes.use('/user/', userRoutes);
module.exports = Routes;