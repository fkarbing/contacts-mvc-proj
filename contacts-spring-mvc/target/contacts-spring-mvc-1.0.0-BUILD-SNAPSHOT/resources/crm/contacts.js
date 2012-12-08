// comment
steal('jupiter/scrollable_grid',
	  //'jquery/dom/fixture', 
	  'jupiter/style', 
	  'mxui/data/list',
	  './contacts.css',
	  './global.js',
	  './models/location.js', 
	  './models/contact.js', 
	  './models/company.js', 
	  './models/consultantskill.js')
	.then(function(){
		$.Controller("Contacts.Controller", {
			init: function(){
				this.params = new Mxui.Data();
				$("#consultantskill .list_wrapper").mxui_data_list({
					model : Contacts.Models.Consultantskill,
					show : "//crm/views/consultantskillList",
					create: "//crm/views/consultantskillCreate"
				})
				
				$("#location .list_wrapper").mxui_data_list({
					model : Contacts.Models.Location,
					show : "//crm/views/consultantskillList",
					create: "//crm/views/consultantskillCreate"
				})
		
				$("#company .list_wrapper").mxui_data_list({
					model : Contacts.Models.Company,
					show : "//crm/views/companyList",
					create: "//crm/views/companyCreate"
				})
				
				$("#consultantskill .create").jupiter_create({
					model: Contacts.Models.Consultantskill,
					form: "//crm/views/consultantskillCreate",
					insertInto: $("#consultantskill .list_wrapper")
				})
				
				$("#company .create").jupiter_create({
					model: Contacts.Models.Company,
					form: "//crm/views/companyCreate",
					insertInto: $("#company .list_wrapper")
				})
				
				$("#location .create").jupiter_create({
					model: Contacts.Models.Location,
					form: "//crm/views/consultantskillCreate",
					insertInto: $("#location .list_wrapper")
				})
		
				$("#contacts").jupiter_scrollable_grid({
					model : Contacts.Models.Contact,
					params : this.params,
					columns: {
						last: "Name",
						consultantskill: "Category",
						company: "Company",
						location: "Location"
					},
					row : "//crm/views/contactRow",
					create: "//crm/views/contactCreate"
				})
				.find(".wrapper").mxui_layout_fill()
				
				$("h3").style$().header()
				$(".lists > div").style$().box()
			}, 
			"#consultantskill .list_wrapper activate": function(el, ev, item){
				this.params.attr("consultantskillId", item.id);
			}, 
			"#consultantskill .list_wrapper deactivate": function(el, ev, item){
				this.params.attr("consultantskillId", null);
			}, 
			"#location .list_wrapper activate": function(el, ev, item){
				this.params.attr("locationId", item.id);
			}, 
			"#location .list_wrapper deactivate": function(el, ev, item){
				this.params.attr("locationId", null);
			}, 
			"#company .list_wrapper activate": function(el, ev, item){
				this.params.attr("companyId", item.id);
			}, 
			"#company .list_wrapper deactivate": function(el, ev, item){
				this.params.attr("companyId", null);
			},
			"windowresize": function(el, ev){
				$("#contacts").trigger("resize")
			}
		})
		$(document.body).contacts();
	})
