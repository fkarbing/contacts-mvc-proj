
_APP_NAME='contacts-spring-mvc';

/**
 * RESTFUL HTTP METHODS:
 * 
 * Used then the normal $.post etc. isn't enough due to limitations when specifying: data, contentType etc.
 */

//POST
var _get_restful_json = function (attrs, success, error) {
	console.log("GET");
	var resourcePath = "/"+this._shortName+"s";
	var _url = "/"+_APP_NAME+resourcePath;
	console.log("_get_restful_json, _url: "+_url);
	$.ajax({
	type: "GET",
	url: _url,
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: success,
	failure: error
	});
};


// POST
var _post_restful_json = function (attrs, success, error) {
	console.log("POST");
	// do I need to:  JSON.stringify(new ..Company(attrs)), doesnt seem like that!
	// TODO: FIXME!!!!
	if(attrs.name === '') {
		alert("attrs.name === '':");
		console.log("attrs.name === '':");
		error();
		throw "this is an error";
	}
//	else if(attrs.email != undefined && attrs.email === '') {
//		alert("attrs.email === '':");
//		console.log("attrs.email === '':");
//		error();
//		throw "this is an error";
//	}
	
	var resourcePath = "/"+this._shortName+"s";
	console.log("stringfy attrs:");
	var _data = JSON.stringify(attrs);
	console.log(_data);
	var _url = "/"+_APP_NAME+resourcePath;
	console.log("_post_restful_json, _url: "+_url);
	$.ajax({
	type: "POST",
	url: _url,
	data : _data,
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: success,
	failure: error
	});
};

//PUT
var _put_restful_json = function (attrs, success, error) {
	console.log("PUT");
	// do I need to:  JSON.stringify(new ..Company(attrs)), doesnt seem like that!
	// TODO: FIXME!!!!
	if(attrs.name === '') {
		alert("attrs.name === '':");
		console.log("attrs.name === '':");
		error();
		throw "this is an error";
	}
	
	var resourcePath = "/"+this._shortName+"s";
	console.log("stringfy attrs:");
	var _data = JSON.stringify(attrs);
	console.log(_data);
	var _url = "/"+_APP_NAME+resourcePath;
	console.log("_put_restful_json, _url: "+_url);
	$.ajax({
	type: "PUT",
	url: _url,
	data : _data,
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: success,
	failure: error
	});
};

//DELETE
var _delete_restful_json = function (attrs, success, error) {
	console.log("DELETE");
	
	var resourcePath = "/"+this._shortName+"s";
	console.log("stringfy attrs:");
	var _data = JSON.stringify(attrs);
	console.log(_data);
	var _url = "/"+_APP_NAME+resourcePath;
	console.log("_delete_restful_json, _url: "+_url);
	$.ajax({
	type: "DELETE",
	url: _url,
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: success,
	failure: error
	});
};