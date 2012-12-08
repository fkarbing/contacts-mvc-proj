// comment
steal('./js/js-utils/js-utils.js',
	  './global.js',
	  //'./js/local-rest.js',
	  './js/app-rest.js',
	  'jupiter/scrollable_grid',
	  //'jquery/dom/fixture', 
	  'jupiter/style', 
	  'mxui/data/list',
	  './crm.css',
	  './models/location.js', 
	  './models/company.js',
	  './models/consultantskill.js',
	  './models/consultant.js',
//	  './models/contact.js',
	  './mvc/map/map-controller.js'
	)
	.then(function(){
		$.Controller("Contacts.Controller", {
			init: function(){
				this.params = new Mxui.Data();
				
				$("#company .list_wrapper").mxui_data_list({
					model : Contacts.Models.Company,
					show : "//crm/views/companyList",
					create: "//crm/views/companyCreate"
				});
				
				$("#company .create").jupiter_create({
					model: Contacts.Models.Company,
					form: "//crm/views/companyCreate",
					insertInto: $("#company .list_wrapper")
				});
		
				
				$("#location .list_wrapper").mxui_data_list({
					model : Contacts.Models.Location,
					show : "//crm/views/locationList",
					create: "//crm/views/locationCreate"
				});
		
				$("#location .create").jupiter_create({
					model: Contacts.Models.Location,
					form: "//crm/views/locationCreate",
					insertInto: $("#location .list_wrapper")
				});
				
				
				$("#consultantskill .list_wrapper").mxui_data_list({
					model : Contacts.Models.Consultantskill,
					show : "//crm/views/consultantskillList",
					create: "//crm/views/consultantskillCreate"
				});
				
				$("#consultantskill .create").jupiter_create({
					model: Contacts.Models.Consultantskill,
					form: "//crm/views/consultantskillCreate",
					insertInto: $("#consultantskill .list_wrapper")
				});
				
				$("#consultant .list_wrapper").mxui_data_list({
					model : Contacts.Models.Consultant,
					show : "//crm/views/consultantList",
					create: "//crm/views/consultantCreate"
				});
				
				$("#consultant .create").jupiter_create({
					model: Contacts.Models.Consultant,
					form: "//crm/views/consultantCreate",
					insertInto: $("#consultant .list_wrapper")
				});
				
		
//				$("#contacts").jupiter_scrollable_grid({
//					model : Contacts.Models.Contact,
//					params : this.params,
//					columns: {
//						last: "Name",
//						company: "Company",
//						location: "Location",
//						consultantskill: "Skill"
//					},
//					row : "//crm/views/contactRow",
//					create: "//crm/views/contactCreate"
//				})
//				.find(".wrapper").mxui_layout_fill()
				
				$("h3").style$().header()
				$(".lists > div").style$().box()
			}, 
			"#consultantskill .list_wrapper activate": function(el, ev, item){
				console.log('>>> consultantskill list_wrapper activated!');
				console.dir(item.serialize());
				//$('#crmmap').controller().imalive("hello!");
				var _consultants = Contacts.Models.Consultant._findItemsBySkillId(item.id);
				console.log('--- found #_consultants: '+_consultants.length);
				if(_consultants.length === 0)
					return;
					
				// for each - get company / store in 2 maps:
				//	[compId, comp]
				//  [compId, cons...]
				// then for all compIds - get [comp + consults] = ccGroup
				// for each ccGrou - call addItemAndChildItems.
				
				// TODO: finish this! - now just uses first consultants skill ...
				var _company = Contacts.Models.Company._findItem(_consultants[0].companyId);
				console.log("--- found _company: "+_company.name);
				$('#crmmap').controller().clearAndAddItemAndChildItems(_company, [_consultants[0]]);
				
				console.log('<<< consultantskill list_wrapper activated, Done.');
			}, 
			"#consultantskill .list_wrapper deactivate": function(el, ev, item) {
				this.params.attr("consultantskillId", null);
			}, 
			"#location .list_wrapper activate": function(el, ev, item) {
				this.params.attr("locationId", item.id);
			}, 
			"#location .list_wrapper deactivate": function(el, ev, item) {
				this.params.attr("locationId", null);
			}, 
			"#company .list_wrapper activate": function(el, ev, item) {
				console.log('>>> company list_wrapper activated!');
				console.dir(item.serialize());
				//$('#crmmap').controller().imalive("hello!");
				var children = new Array();
				Contacts.Models.Consultant.list.each(function(i, consultant) {
					if(item.id === consultant.companyId) {
						console.log('!-- child matched: '+consultant.name);
						children.push(consultant);
					}
		        });
				console.log("--- matched children: "+children.length);
				$('#crmmap').controller().clearAndAddItemAndChildItems(item, children);
				//this.params.attr("companyId", item.id);
				console.log('<<< company list_wrapper activated Done.!');
			}, 
			"#company .list_wrapper deactivate": function(el, ev, item){
				this.params.attr("companyId", null);
			},
			"#consultant .list_wrapper activate": function(el, ev, item){
				console.log('>>> consultant list_wrapper activated!');
				console.dir(item.serialize());
				var _company = Contacts.Models.Company._findItem(item.companyId);
				console.log("--- found _company: "+_company.name);
				$('#crmmap').controller().clearAndAddItemAndChildItems(_company, [item]);
				this.params.attr("consultantId", item.id);
			}, 
			"#consultant .list_wrapper deactivate": function(el, ev, item){
				this.params.attr("consultantId", null);
			},
			"#company .x_formItem onblur": function() {
				alert('Im blured!');
			},
			".list_wrapper .item mouseover": function(el, ev) {
				if(typeof el === 'undefined')
					return;
				console.log($(el).text().trim());
			},	
			"windowresize": function(el, ev){
				$("#contacts").trigger("resize");
			},
			
		}); // $.Controller("Contacts.Controller", {
		
		$(document.body).contacts();
		
		/* Additional Controllers */
		
		$.Controller('AddressField',{
					listensTo : ['onsubmit', 'keypress', 'keydown', 'onblur', 'onfocus']
				},
				{
					init : function(rawEl, rawOptions){ 
					    //console.log("Im a AddressField...");
					},
					'input:text .address keydown' : function(input, e) {
						console.log("input keydown");//this.element.show();
						if ( e.which == 13 ) {
			                alert("im preventing again!!!");
			                e.stopPropagation();
			                e.preventDefault();
			                return false;
			           };
					},
					"input:text .address mouseover" : function(input, ev){
						console.log("mouse3 over");//this.element.show();
					},
					"input:text onblur" : function(input, ev){
						alert("onblur");//this.element.show();
					},
					"input:text onfocus" : function(input, ev){
						alert("onfocus");//this.element.show();
					}
				}

		);
		
		
		$('#company').address_field();

		
		//_decorate_elements();
		
	});
