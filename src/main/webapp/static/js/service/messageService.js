'use strict';

angular.module('myApp').service('messageService', function() {
   // Error Messages
   var service = {};
   var message;
   var messages = {
      //success messages
      "registered": 'User Registered Successfully!' ,
      "deregistered": 'De registered successfully!',

      404: 'No Data Found!',
      //server side error messages
      "1001": 'User with the same SSN, has be registered already!' ,
      "1002": 'User with this DOB and SSN, has been blacklisted!' ,
      "1003": 'Invalid DOB format / DOB can not be a future date!' ,
      "500": 'INTERNAL SERVER ERRROR'

   };

   service.remove = function() {
      message = undefined;
   };

   service.getMessage = function (){
      return message;
   };

   service.addMessageFor = function(statusCode, action) {
      var cssClass = "danger";
      if (statusCode === 200) {
         cssClass = "success";
      }
      var msg = messages[action];
      if(!angular.isDefined(msg)){
         msg = messages[statusCode];
      }
      if(!angular.isDefined(msg)){
         msg = "Unknown Error";
      }
      message = {
         "cssClass" : cssClass,
         "messageContent" : msg
      };
   };

   return service;
});
