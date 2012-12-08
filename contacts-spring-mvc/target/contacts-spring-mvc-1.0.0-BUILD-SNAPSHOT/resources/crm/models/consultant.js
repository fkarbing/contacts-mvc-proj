steal("jquery/model/list")
	.then(function(){
		$.Model('Contacts.Models.Consultant',
		{
			//findAll : "crm/test/data/consultants.json",
			findAll : _get_restful_json,
			
			create	: _post_restful_json,

			listType : $.Model.List
		},
		{});
		
		
		//========= Static functions =========//
		
		// Returns: item if found, undefined otherwise.
		Contacts.Models.Consultant._findItem = function(id) {
		    var foundItem = undefined;
			Contacts.Models.Consultant.list.each(function(i, item) {
				if(id === item.id) {
					console.log("Consultant: found for id: "+id);
					foundItem = item;
					return false;
				}
			});
			return foundItem;
		};
		
		// Returns: item.name if found, "" otherwise.
		Contacts.Models.Consultant._findItemName = function(id) {
			var foundItem = Contacts.Models.Consultant._findItem(id);
			if(foundItem !== undefined)
				return foundItem.name;
			else
				return "";
		};
		
		// Returns: [items..] if found, [] otherwise.
		Contacts.Models.Consultant._findItemsBySkillId = function(skillid) {
			var foundItems = new Array();
			Contacts.Models.Consultant.list.each(function(i, item) {
				if(skillid === item.consultantskillId) {
					console.log("Consultant: for skill id, found : "+item.id);
					foundItems.push(item);
				}
			});
			console.log("_findItemsBySkillId(), found #items: "+foundItems.length);
			return foundItems;
		};
		    
	});
