angular.module('AngularApp').controller('MenuController',
	['$scope', '$rootScope', '$state', '$location', 'growl', 'blockUI', 'Idle', 'TokenService',
		function($scope, $rootScope, $state, $location, growl, blockUI, Idle, TokenService) {
		
			$rootScope.$broadcast('changeStyle');
			
			function closeMenu() {
				var $showLeft = $("#showLeft");
				var $hamb = $("#btn-menu");
				var $nav = $("#cbp-spmenu-s1");
				
				$showLeft.removeClass("active");
				$hamb.removeClass("is-active");
				$nav.removeClass("cbp-spmenu-open");
			}
			
			$scope.toggleUser = function (e) {
			      if ($("#dropdownEvents").css('display') == 'block') {
			        e.stopPropagation();
			        e.preventDefault();
			        $("#dropdownEvents").toggle();
			      }
			      $("#dropdownUser").toggle();
			      e.stopPropagation();
			      e.preventDefault();
			    };
			
			/*
			$scope.$on('closeSSEs', function (event, message) {
			      if (!angular.isUndefinedOrNull(source2)) {
			        source2.removeEventListener("message", notificationHandler);
			        source2.close();
			      }
			    });
			    */
			
			/*
			$scope.$on('rootChange', function (event, message) {
			      $scope.road = message;
			    })
			*/
			
			
			$scope.$on('closeMenu', function (event, message) {
			      if ($("#dropdownUser").css('display') == 'block') {
			        event.preventDefault();
			        $("#dropdownUser").toggle();
			      }
			      if ($("#dropdownEvents").css('display') == 'block') {
			        event.preventDefault();
			        $("#dropdownEvents").toggle();
			      }
			      closeMenu();
			    });
			  
			
			
			
			
			$scope.initMenu = function () {
			      "use strict";
			      
			      var toggles = document.querySelectorAll(".c-hamburger");

			      for (var i = toggles.length - 1; i >= 0; i--) {
			        var toggle = toggles[i];
			        toggleHandler(toggle);
			      };

			      function toggleHandler(toggle) {
			        toggle.addEventListener("click", function (e) {
			          e.preventDefault();
			          (this.classList.contains("is-active") === true) ? this.classList.remove("is-active") : this.classList.add("is-active");
			        });
			      }


			      var menuLeft = document.getElementById('cbp-spmenu-s1'),
			        showLeft = document.getElementById('showLeft'),
			        body = document.body;

			      showLeft.onclick = function (event) {
			        classie.toggle(this, 'active');
			        classie.toggle(menuLeft, 'cbp-spmenu-open');
			        disableOther('showLeft');
			        if ($("#dropdownUser").css('display') == 'block') {
			          event.preventDefault();
			          $("#dropdownUser").toggle();
			        }
			        if ($("#dropdownEvents").css('display') == 'block') {
			          event.preventDefault();
			          $("#dropdownEvents").toggle();
			        }
			      };

				function closeMenu() {
					var $showLeft = $("#showLeft");
					var $hamb = $("#btn-menu");
					var $nav = $("#cbp-spmenu-s1");
					
					$showLeft.removeClass("active");
					$hamb.removeClass("is-active");
					$nav.removeClass("cbp-spmenu-open");
					
					/*
					var $showLeft = $("#showLeft");
					var $hamb = $("button.c-hamburger");
					
					if ($showLeft.hasClass("active")) {
					//$hamb.removeClass("is-active");
					$hamb.trigger('click');
					}
					*/
				}

			      function disableOther2(button, showLeft2) {
			        if (button !== 'showLeft') {
			          classie.toggle(showLeft2, 'disabled');
			        }
			      }

			      function disableOther(button) {
			        if (button !== 'showLeft') {
			          classie.toggle(showLeft, 'disabled');
			        }
			      }

			      function setMenuDisplay(_this, settings){
			        return _this.each(function () {
			          var obj = $(this);
			          $('li a', obj).click(function (event) {
			            var elem = $(this).next();
			            closeMenu();
			            elem.slideToggle();
			          });
			        });
			      }

			      $.fn.accordion = function (custom) {
			        var defaults = {
			          keepOpen: false,
			          startingOpen: false
			        }

			        var settings = $.extend({}, defaults, custom);
			        if (settings.startingOpen) {
			          $(settings.startingOpen).show();
			        }

			        var _this = this;

			        return setMenuDisplay(_this, settings);
			      };
			      $('.menu').accordion({ keepOpen: false });
			    };
		
		}
	]);
