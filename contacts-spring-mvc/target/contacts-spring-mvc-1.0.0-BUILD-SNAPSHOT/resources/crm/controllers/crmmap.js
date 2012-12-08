steal('jupiter/scrollable_grid',
		//'jquery/dom/fixture', 
		'jupiter/style', 
		'mxui/data/list',
		'//crm/js/google-map-api.js'
)
.then(function() {	  

	$.Controller('CrmMap',
			{
//				defaults :{
//					message : "Remove Me"
//				}
			},
			{
				init : function(el, options){
					steal.dev.log("CrmMap init.. stela dev log.");
					console.log("CrmMap init");
					
					this.reset();
					
					
//					this.element.append(
//							"<div>"+this.options.message+"</div>"
//					);
				},
				reset : function() {
					
					console.log("CrmMap reset");
					var targetDivId = "crmmap-content";
					var strLatLng = "59.33230529999999,18.05283570";
					
					//targetDivId = "crmmapXX";
					loadMapAndShow(targetDivId, strLatLng);
				},
//				"div click" : function(div, ev){ 
//					div.remove();
//				}
			}
	); // Controller
	
	$('#crmmap').crm_map();
	
});