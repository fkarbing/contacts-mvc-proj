//steal/js crm/scripts/compress.js

load("steal/rhino/steal.js");
steal.plugins('steal/build','steal/build/scripts','steal/build/styles',function(){
	steal.build('crm/scripts/build.html',{to: 'contacts'});
});
