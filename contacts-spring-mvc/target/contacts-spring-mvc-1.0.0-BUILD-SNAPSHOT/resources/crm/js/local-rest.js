
_APP_NAME='riak';

/**
 * RESTFUL HTTP METHODS:
 * 
 * Used then the normal $.post etc. isn't enough due to limitations when specifying: data, contentType etc.
 */

//GET ALL
var _get_restful_json = function (attrs, success, error) {
	
	
	console.log("GET");
	var _this = this;
	var _success = success;
	var resourcePath = "/"+this._shortName+"s";
	var _url = "/"+_APP_NAME+resourcePath;
	
	if(resourcePath != '/locations') {
		return;
	}
	
	_url = "/buckets"+resourcePath+"/keys?keys=true";
	console.log("_get_restful_json, _url: "+_url);
	$.ajax({
	async: false,
	type: "GET",
	url: _url,
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: function(resp) {
		console.log("get success: "+resourcePath);
		console.dir(resp.keys);
		for ( var i = 0; i < resp.keys.length; i++) {
			Contacts.Models.Location.findOne(attrs, success, error, resp.keys[i]);
		}
		//_get_restful_json_1(resp.keys, resourcePath, _success, _this);
	},
	failure: error
	});
};


//GET
var _get_restful_json_1 = function (attrs, success, error, id) {
	
	//var __this = _this;
//	for ( var i = 0; i < ids.length; i++) {
//		var id = ids[i];
		
		console.log("GET 1");
		var resourcePath = "/"+this._shortName+"s";
		var _url = "/"+_APP_NAME+resourcePath+"/"+id;
		console.log("_get_restful_json_1 _url: "+_url);
		$.ajax({
		async: false,
		type: "GET",
		url: _url,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: success,
		failure: error
		});
	//}
	
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
	_url = "/"+_APP_NAME+resourcePath;
	//_url = _url+"/atempId";
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