steal("jquery/model/list")
	.then(function(){
		$.Model('Contacts.Models.Company',
		{
			findAll : "contacts/test/data/companys.json",
			listType : $.Model.List
		},
		{});
	});
