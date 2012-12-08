steal('jquery/model/list'
	).then(function(){
		$.Model('Contacts.Models.Location',
		{
			//findOne : _get_restful_json_1,
			
			findAll : _get_restful_json,
			
			create	: _post_restful_json,
			
			listType : $.Model.List
		},
		{});
		

		//========= Static functions =========//
		
		// Returns: item if found, undefined otherwise.
		Contacts.Models.Location._findItem = function(id) {
			    var foundItem = undefined;
				Contacts.Models.Location.list.each(function(i, item) {
					if(id === item.id) {
						console.log("Location: found for id: "+id);
						foundItem = item;
						return false;
					}
				});
				return foundItem;
		};
		
		// Returns: item.name if found, "" otherwise.
		Contacts.Models.Location._findItemName = function(id) {
			var foundItem = Contacts.Models.Location._findItem(id);
			if(foundItem !== undefined)
				return foundItem.name;
			else
				return "";
		};
		
	});
