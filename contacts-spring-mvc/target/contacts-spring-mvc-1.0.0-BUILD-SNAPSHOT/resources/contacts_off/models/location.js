steal('jquery/model/list'
	).then(function(){
		$.Model('Contacts.Models.Location',
		{
			//findAll : "contacts/test/data/locations.json",
			findAll : "/"+_APP_NAME+"/locations",
			listType : $.Model.List
		},
		{});
	});
