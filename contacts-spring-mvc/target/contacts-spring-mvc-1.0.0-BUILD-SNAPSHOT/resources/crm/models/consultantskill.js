steal("jquery/model/list")
	.then(function(){
		console.log("consultantskill.js steal");
		$.Model('Contacts.Models.Consultantskill',
		{
			//findAll : "crm/test/data/consultantskills.json",
			findAll : _get_restful_json,
			
			create	: _post_restful_json,
			
			listType : $.Model.List
		},
		{});
		
		
		//========= Static functions =========//
		
		// Returns: item if found, undefined otherwise.
		Contacts.Models.Consultantskill._findItem = function(id) {
			    var foundItem = undefined;
				Contacts.Models.Consultantskill.list.each(function(i, item) {
					if(id === item.id) {
						console.log("Consultantskill: found for id: "+id);
						foundItem = item;
						return false;
					}
				});
				return foundItem;
		};
		
		// Returns: item.name if found, "" otherwise.
		Contacts.Models.Consultantskill._findItemName = function(id) {
			var foundItem = Contacts.Models.Consultantskill._findItem(id);
			if(foundItem !== undefined)
				return foundItem.name;
			else
				return "";
		};
		
	});
