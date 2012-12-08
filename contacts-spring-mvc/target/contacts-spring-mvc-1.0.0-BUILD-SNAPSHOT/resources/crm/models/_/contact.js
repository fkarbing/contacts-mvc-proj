steal("jquery/model", "jquery/model/list/local")
	.then(function(){
		$.Model('Contacts.Models.Contact', {
			name: function(){
				return this.first+" "+this.last;
			},
			getRelated: function(name, id){
				console.log("getRelated()");
				return this.Class.namespace[$.String.capitalize(name)].list.get(this[name+"Id"])[0];
			},
			consultantskill: function(){
				console.log("consultantskill()");
				return this.getRelated("consultantskill");
			},
			location: function(){
				console.log("location()");
				return this.getRelated("location");
			},
			company: function(){
				console.log("company()");
				return this.getRelated("company");
			}
		},
		{});
		
		/*
		$.fixture.make('contact', 5, function(i){
			var names = ["monkey", "cheeta", "bear", "dog", "cat", "hippo", "pony"],
				types = ["audio", "video", "image", "flash"],
				firsts = ["Brian", "Bob", "Ken", "Julia", "Mike", "Deb", "Rory", "Micky"],
				lasts = ["Jones", "Bonds", "Austin", "Park", "Kim", "Johnson"];
			return {
				id: i+1,
				first: firsts[i%8],
				last: lasts[i%6],
				consultantskillId: (i%7)+1,
				companyId: (i%5)+1,
				locationId: (i%9)+1
			}
		}); // end: fixture.make
		*/
		
	});

	
	/**
	 * [{
  "id" : 0,
  "first" : "Justin",
  "last" : "Meyer",
  "consultantskillId" : 284,
  "companyId" : 1,
  "locationId" : 1
}]
	 */