steal("jquery/model", "jquery/model/list")
	.then(function(){
		console.log("category.js steal");
		$.Model('Contacts.Models.Category',
		{
			findAll : "contacts/test/data/categorys.json",
			listType : $.Model.List
		},
		{});
	});
