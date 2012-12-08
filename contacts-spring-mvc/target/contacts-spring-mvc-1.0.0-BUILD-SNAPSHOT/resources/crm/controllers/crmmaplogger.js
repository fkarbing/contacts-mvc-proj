

$.Controller('CrmMapLogger.Controller',
	{
		defaults :{
			message : "Remove Me"
		}
	},
	{
		init : function(rawEl, rawOptions){ 
			this.element.append(
					"<div>"+this.options.message+"</div>"
			);
		},
		"div click" : function(div, ev){ 
			div.remove();
		}
	});