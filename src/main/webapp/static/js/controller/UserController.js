'use strict';

var myApp = angular.module('myApp',[]);

myApp.controller('UserController', ['$scope', 'UserService', 'messageService',
    function($scope, UserService, messageService) {
    var self = this;
    self.user={id:null,username:'',password:'',dob:'',ssn:''};
    self.users=[];
    self.submit = submit;
    self.remove = remove;
    self.reset = reset;

    fetchAllUsers();
    $scope.today = new Date();
    $scope.message = {};
    $scope.showMessage = false;
    $scope.hideMessage = function () {
        messageService.remove();
        $scope.showMessage = false;
    };

    $scope.$watch(function(){return messageService.getMessage();}, function(){
        var message = messageService.getMessage();
        if(angular.isDefined(message)) {
            $scope.message = message;
            $scope.showMessage = true;
        }
    });

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                messageService.addMessageFor(400, errResponse.data);
            }
        );
    }

    function register(user){
        UserService.register(user)
            .then(
            function(response) {
                fetchAllUsers();
                messageService.addMessageFor(200, 'registered');
            },
            function(errResponse){
                messageService.addMessageFor(400, errResponse.data);
            }
        );
    }

    function deregister(id){
        UserService.deregister(id)
            .then(
           function() {
               fetchAllUsers();
               messageService.addMessageFor(200, 'deregistered');
           },
            function(errResponse){
                messageService.addMessageFor(500, errResponse.data);
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            register(self.user);
        }
    }

    function remove(id){
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deregister(id);
    }


    function reset(){
        self.user={id:null,username:'',password:'',dob:'',ssn:''};
        $scope.userForm.$setPristine(); //reset Form
    }

}]);
