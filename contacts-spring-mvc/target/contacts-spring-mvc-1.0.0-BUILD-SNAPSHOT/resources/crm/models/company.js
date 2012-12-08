steal("jquery/model/list")
	.then(function(){
		$.Model('Contacts.Models.Company',
		{
			//findAll : "crm/test/data/companys.json",
			findAll : _get_restful_json,

			create	: _post_restful_json,

			listType : $.Model.List
		},
		{});
		
		
		//========= Static functions =========//
		
		// Returns: item if found, undefined otherwise.
		Contacts.Models.Company._findItem = function(id) {
			    var foundItem = undefined;
				Contacts.Models.Company.list.each(function(i, item) {
					if(id === item.id) {
						console.log("Company: found for id: "+id);
						foundItem = item;
						return false;
					}
				});
				return foundItem;
		};
		
		// Returns: item.name if found, "" otherwise.
		Contacts.Models.Company._findItemName = function(id) {
			var foundItem = Contacts.Models.Company._findItem(id);
			if(foundItem !== undefined)
				return foundItem.name;
			else
				return "";
		};
		
	});
