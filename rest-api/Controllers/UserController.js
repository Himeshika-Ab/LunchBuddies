var mongoose = require('../DBconfig/db.config');
var UserSchema = mongoose.model('User');
// var nodemailer = require('nodemailer');

var userController = function () {
    //add student details function, inputs: JSON object of student type
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

    //update student details
    this.updateUser = function (id, u) {
        return new Promise(function (resolve, reject) {
            UserSchema.update({_id: id}, u).then(function () {
                resolve({status: 200, message: 'User details updated'});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }

    //get all registered students from the db
    this.getUsers = function () {
        return new Promise(function (resolve, reject) {
            UserSchema.find().exec().then(function (u) {
                resolve({status: 200, data: u});
            }).catch(function (reason) {
                reject({status: 500, message:'Error occured'+ reason});
            });
        });
    }
    //
    // //getting a single student by id
    // this.getStudent = function (id) {
    //     return new Promise(function (resolve, reject) {
    //         StudentSchema.find({_id: id}).exec().then(function (student) {
    //             resolve({status: 200, data: student});
    //         }).catch(function (reason) {
    //             reject({status: 500, message:'Error occured'+ reason});
    //         });
    //     });
    // }
    //
    // //search students by IT number
    // this.getByITNo = function (ITNo) {
    //     return new Promise(function (resolve, reject) {
    //         StudentSchema.find({ITNo: ITNo}).exec().then(function (student) {
    //             resolve({status: 200, data: student});
    //         }).catch(function (reason) {
    //             reject({status: 500, message:'Error occured'+ reason});
    //         });
    //     });
    // }
    //
    //
    // //deleting students
    // this.deleteStudent = function (id) {
    //     return new Promise(function (resolve, reject) {
    //         StudentSchema.remove({_id: id}).then(function () {
    //             resolve({status: 200, message: 'Student deleted'});
    //         }).catch(function (reason) {
    //             reject({status: 500, message:'Error occured'+ reason});
    //         });
    //     })
    //
    // }
    // //sending email using nodemailer  module
    // this.sendMail = function (mailDetails) {
    //     console.log(mailDetails);
    //     return new Promise(function (resolve, reject) {
    //         const output =  `
    //         <p> Thanks for accepting our student ${mailDetails.name} (${mailDetails.ITNo}) for an internship in your company. Please fill the below form to confirm the students internship </p>
    //         <br/> <br/>
    //         <form>
    //              Name:<br>
    //             <input type="text" name="name">
    //             <br>
    //             Address:<br>
    //             <input type="text" name="address">
    //             <br><br>
    //             <input type="submit" value="Confirm Internship">
    //         </form>
    //         `;
    //         // create reusable transporter object
    //         var transporter = nodemailer.createTransport({
    //             host: "smtp.gmail.com",//use gmail service
    //             port: 465,
    //             secure: true,
    //             auth: {
    //                 user: "minolispencer@gmail.com",//gmail account username
    //                 pass: "19980709" //gmail account password
    //             },
    //             tls: {
    //                 rejectUnauthorized: false
    //             }
    //         });
    //         //setting up email data
    //         var mailOptions = {
    //             from: '"SLIIT Internship Program" <mailDetails.fromEmail>',//sender address
    //             to: mailDetails.toEmail,//list of receivers
    //             subject: 'Confirmation of Internship',//subject of the email
    //             html: output//body in plain text
    //         };
    //
    //         //console.log(mailOptions);
    //         //send mail using defined transport object
    //         transporter.sendMail(mailOptions, function(error, info){
    //             if (error) {
    //                 reject({status: 500, message: error});
    //             } else {
    //                 resolve({status: 200, message: info.response});
    //             }
    //         });
    //     })
    // }

}

module.exports = new userController();