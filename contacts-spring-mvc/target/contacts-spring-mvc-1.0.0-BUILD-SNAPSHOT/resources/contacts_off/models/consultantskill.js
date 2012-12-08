steal("jquery/model/list")
	.then(function(){
		console.log("category.js steal");
		$.Model('Contacts.Models.Consultantskill',
		{
			//findAll : "contacts/test/data/categorys.json",
			findAll : "/"+_APP_NAME+"/categorys",
			listType : $.Model.List
		},
		{});
	});
