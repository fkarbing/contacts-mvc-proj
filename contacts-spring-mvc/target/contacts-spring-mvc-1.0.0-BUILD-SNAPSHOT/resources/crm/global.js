//
//_APP_NAME='contacts-spring-mvc';
//
///**
// * RESTFUL HTTP METHODS:
// * 
// * Used then the normal $.post etc. isn't enough due to limitations when specifying: data, contentType etc.
// */
//var _post_restful_json = function (attrs, success, error) {
//	// do I need to:  JSON.stringify(new ..Company(attrs)), doesnt seem like that!
//	// TODO: FIXME!!!!
//	if(attrs.name === '') {
//		alert("attrs.name === '':");
//		console.log("attrs.name === '':");
//		error();
//		throw "this is an error";
//	}
////	else if(attrs.email != undefined && attrs.email === '') {
////		alert("attrs.email === '':");
////		console.log("attrs.email === '':");
////		error();
////		throw "this is an error";
////	}
//	
//	var resourcePath = "/"+this._shortName+"s";
//	console.log("stringfy attrs:");
//	var _data = JSON.stringify(attrs);
//	console.log(_data);
//	var _url = "/"+_APP_NAME+resourcePath;
//	console.log("_post_restful_json, _url: "+_url);
//	$.ajax({
//	type: "POST",
//	url: _url,
//	data : _data,
//	contentType: "application/json; charset=utf-8",
//	dataType: "json",
//	success: success,
//	failure: error
//	});
//};


/* Hide form input values on focus*/ 
var _decorate_elements = function(jqId) {
	  alert('im runned...');
	  console.log("length: "+$(document.body).find('input:text').length);
	  console.dir($(document).find('input:text'));
      $('#'+jqId).find('input:text').each(function(){
          var txtval = $(this).val();
          $(this).focus(function(){
              if($(this).val() == txtval){
                  $(this).val('');
              }
          });
          $(this).blur(function(){
              if($(this).val() == ""){
                  $(this).val(txtval);
              }
          });
      });
  };
  
  
  var _on_submit = function(form) {
	  console.log(">>> _on_submit...");
	  var elems = $(form).find(':input');
	  var i = 0;
	  for(; i < elems.length; i = i+1) {
		  if(elems[i] == undefined || elems[i].value == "")
			  throw "required form element empty!";
	  }
	  console.log("<<< _on_submit Done.");
  };
  
  var _onfocus = function(elem) {
	  //console.dir(elem);
	  //if(elem.value === '')
		 // elem.placeholder ===
//	  alert(elem.val());
  };
  
  var _fadingMsg = function(msg) {
	  var msgWrapper = "<div style='padding: 5px 10px; border: 2px solid #333;'></div>";
	  $("document.body").html(msgWrapper).delay(10000).fadeOut();
  }
  
  //$('input:text').on("focus", "", function(){ alert("focus!"); }); 
 
  
  console.log('global.js loaded.');