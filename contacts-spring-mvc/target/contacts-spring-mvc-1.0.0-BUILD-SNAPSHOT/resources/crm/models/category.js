steal("jquery/model/list")
	.then(function(){
		console.log("category.js steal");
		$.Model('Contacts.Models.Category',
		{
			findAll : "crm/test/data/category.json",
			listType : $.Model.List
		},
		{});
	});
