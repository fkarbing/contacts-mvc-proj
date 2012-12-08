steal('jupiter/scrollable_grid',
		//'jquery/dom/fixture', 
		'jupiter/style', 
		'mxui/data/list',
		'//crm/js/google-maps-api.js'
)
.then(function() {	  

	$.Controller('CrmMap',
			{
//				defaults :{
//					message : "Remove Me"
//				}
				_map : null,
				_markerConf : null
				
			},
			{
				init : function(el, options){
					this._TEST = true;
					console.log(">>> CrmMap init, TESTING?: "+this._TEST);
					
					// Create and set _map
					this._markerConf = { dir : "crm/mvc/map/markers" };
					var targetDivId = "crmmap-content";
					var strCenterLatLng = "59.33230529999999,18.05283570";
					if(this._TEST === false)
						this._map = gmApiLoadMapAndShow(targetDivId, strCenterLatLng);
					
					console.log("--- created map.");
					if(this._TEST === false)
						this.reset();
				},
				reset : function() {
					console.log(">>> CrmMap reset");
					
					// Clear map
					this.clear();
					
					
					// TEST! - get from controllers!
					var _companys = [ "H&M", "Aftonbladet", "Cygni" ];
					//var _companys = [ "Aftonbladet", "Cygni" ];
			        var _address = [ "Liljeholmstorget 5, 117 63 Stockholm", "V??stra J??rnv??gsgatan 21 111 64 STOCKHOLM", "Sturegatan 34, 114 36 Stockholm" ];
			        var _latlng = [ [59.309737,18.022169], [59.33230529999999,18.05283570], [59.339762,18.075985] ];
			        
			        var companys = new Array();
			        
			        // test companies
			        for(var i = 0; i < _companys.length; i++) {
			        	var __company = {
			        			name : _companys[i], address : _address[i], latlng : _latlng[i]
			        	};
			        	companys.push(__company);
			        }
			        
			        // test companies
			        var consultants = [
			                           { name : "Kalle" } //, { name : "Pelle" }, { name : "Lars" }, { name : "Nils" }
			        ];
			        // end: TEST
			        
			        console.log("--- Before CrmMap addItemAndChildItems...");
			        
			        // ADD: [ [company + consultants], [...], ... ] to Map
			        for ( var i = 0; i < companys.length; i++) {
			        	this.addItemAndChildItems(companys[i], consultants);
					}
			        
			        console.log("<<< CrmMap reset");
			        
				}, // end: reset()
				
				clear : function() {
					console.log(">>> CrmMap clear");
					console.log("<<< CrmMap clear");
				}, // end: clear()
				
				// PUBLIC - MAP CRUD operations
				addItem : function(item) {
					throw "addItem() Not implemented.";
				},
				addItemAndChildItems : function(markerItem, children) {
					console.log(">>> CrmMap addItemAndChildItems");
					
					// Create MarkerGroup - move to function.
					
		        	var markerGroup = new Array();
		        	var fLat = parseFloat(markerItem.latlng.split(",")[0]);
		        	var fLng = parseFloat(markerItem.latlng.split(",")[1]);
		        	var _marker = [ markerItem.name, fLat, fLng, 1];
		        	//markerGroup.push([ companys[i], latlng[i][0], latlng[i][1], i+1]);
		        	markerGroup.push(_marker);
		        	// add children
		        	var _childMarker;
		        	for(var j = 0; j < children.length; j++) {
		        		_childMarker = [ children[j].name, _marker[1], _marker[2], _marker[3]+j+1];
		        		markerGroup.push(_childMarker);
		        	}
		        	
		        	// Add MarkerGroup
		        	if(this._TEST === false)
		        		this._addMarkerGroup(markerGroup);
		        	
		        	console.log("<<< CrmMap addItemAndChildItems");
		        	
				}, // end: addItemAndChildItems
				clearAndAddItemAndChildItems : function(item, children) {
					console.log(">>> CrmMap clearAndAddItemAndChildItems");
					if(item == undefined)
						throw "clearAndAddItemAndChildItems(), item undefined!"
					if(children == undefined)
							children = [];
					console.log("item:");
					console.dir(item.serialize());
					this.clear();
					this.addItemAndChildItems(item, children);
					console.log("<<< CrmMap clearAndAddItemAndChildItems");
				},
				
				removeItem : function(item) {
					throw "removeItem() Not implemented.";
				},
				removeItems : function(items) {
					throw "removeItems() Not implemented.";
				},
				
				// PRIVATE - MAP CRUD operations
				_addMarkerGroup : function(markerGroup) {
					console.log(">>> CrmMap _addMarkerGroup");
					
					gmApiSetMarkersOnMap(this._map, markerGroup, this._markerConf);
					
					console.log("<<< CrmMap _addMarkerGroup");
				},
				
				imalive : function(msg) {
					alert("CrmMap is alive, msg: "+msg);
				}
				
			}
	); // Controller
	
	$('#crmmap').crm_map();
	
});