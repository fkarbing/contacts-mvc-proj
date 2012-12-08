//------------------------NAME SPACE-------------------------------

var js_utils = {};


//
//--------------------------- CONSTANTS---------------------------
//
//
// --------------------------- STYLE-HTML CONSTANTS---------------------------
//
var isChrome = navigator.userAgent.indexOf("Chrome") > -1;


////////////////////////////////////////////////////////////////////////////
//
//						Core Functionality.
//
////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////
//							END: Core Functionality.
////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////
//
//								Core Class Extensions.
//
////////////////////////////////////////////////////////////////////////////

//----- ARRAYS -----//

//Array: remove() - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};

//Array: removeAt() - Added by Fredrik Karbing
Array.prototype.removeAt = function(i) {
	  return this.splice(i,1);
};

//Array: size() - Added by Fredrik Karbing
Array.prototype.size = function () {
	return this.length;
}

//Array: size() - Added by Fredrik Karbing
Array.prototype.getLastElement = function () {
	return (0 < this.length) ? this[this.length-1] : false;
}

//Array: noOccurencesOfStr() - Added by Fredrik Karbing
Array.prototype.noOccurencesOfStr = function (str) {
	if(!jsUtils.hasValue(str))
		return 0;
	var noOccurences = 0;
	for ( var i = 0; i < this.length; i++) {
		this[i] === str;
		noOccurences++;
	}
	return noOccurences;
}

//Array: removeElem() - Added by Fredrik Karbing
Array.prototype.removeElem = function(aElems, elem) {
	for(var i=0; i<aElems.length; i++) {
		if(aElems[i] === elem) {
			aElems.removeAt(i);
		}
	}
}

//----- /ARRAYS -----//



//----- STRINGS -----//

// Simple Tokenizer functions. NO RegExp is used here.

String.prototype.equals = function (str) {
	if(!jsUtils.isDefined(str))
		return false;
	return (this.toString() === str);
}

String.prototype.getFirstElementByToken = function (token) {
	if(!jsUtils.hasValue(token)) return null;
	return this.split(token)[0];
}

String.prototype.getLastElementByToken = function (token) {
	if(!jsUtils.hasValue(token)) return null;
	var arr = this.split(token);
	return arr[arr.length-1];
}

String.prototype.getNthElementByToken = function (n, token) {
	if(!jsUtils.hasValue(token)) return null;
	var arr = this.split(token);
	if(arr.length <= n) return null;
	return arr[n-1];
}

String.prototype.countTokens = function (token) {
	if(!jsUtils.hasValue(token)) return -1;
	return (this.split(token).length-1);
}

String.prototype.trimFirstElementByToken = function (token) {
	if(!jsUtils.hasValue(token)) return this;
	return this.substr(this.indexOf(token));
}

String.prototype.trimLastElementByToken = function (token) {
	if(!jsUtils.hasValue(token)) return this;
	return this.substr(0, this.lastIndexOf(token));
}

String.prototype.trimByToken = function (token) {
	if(!jsUtils.hasValue(token)) return this;
	var str = this;
	if(str.startsWith(token))
		str = str.substr(token.length);
	if(str.endsWith(token)) 
		str = str.substr(0, str.length-token.length);
	return str;
}

if (typeof String.prototype.startsWith != 'function') {
  String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
  };
}

if (typeof String.prototype.endsWith != 'function') {
  String.prototype.endsWith = function(str) {
    return this.indexOf(str, this.length - str.length) !== -1;
  }
}

// Java 7 String.hashCode
if (typeof String.prototype.xHashCode != 'function') {
  String.prototype.xHashCode = function(e) {
    for(var r=0,i=0;i<this.length;i++)
      r=(r<<5)-r+this.charCodeAt(i),r&=r;
      return r
    }
}

//----- /STRINGS -----//



//----- MATH -----//

if(!Math.randomFromTo){
    Math.randomFromTo = function(from, to){
        return Math.floor(Math.random() * (to - from + 1) + from);
    };
}


//----- /MATH -----//



////////////////////////////////////////////////////////////////////////////
//
//								Core Classes.
//
////////////////////////////////////////////////////////////////////////////


//---------------------------------- MAP ------------------------------//

js_utils.Map = function() {
    // members
    this.keyArray = new Array(); // Keys
    this.valArray = new Array(); // Values
    this.length = 0;
    this.trace = false;
       
    /*---- methods -----/
	    this.put = put;
	    this.putAll(Map m) 
	    this.get = get;
	    this.size = size;  
	    this.clear = clear;
	    this.keySet = keySet;
	    this.valSet = valSet;
	    this.showMe = showMe;   // returns a string with all keys and values in map.
	    this.indexOfKey = indexOfKey;
	    this.remove = remove;
    /---- methods -----*/
}

js_utils.Map.prototype.put = function( key, val )
{
    var elementIndex = this.indexOfKey( key );
    
    if( elementIndex == (-1) )
    {
        this.keyArray.push( key );
        this.valArray.push( val );
    }
    else
    {
        this.valArray[ elementIndex ] = val;
    }
    
    this.length = this.keyArray.length;
    
    (this.trace) && console.log("Map.put -->  key: ["+key+"], value: ["+val+"]. New length: "+this.length);
    
    return this.length;
}

js_utils.Map.prototype.putAll = function( map )
{
	
	var _keys = map.keys();
	var _length = _keys.length;
	
	for ( var i = 0; i < _length; i=i+1) {
		this.put(_keys[i], map.get(_keys[i]));
	}
	return this.size();
}

js_utils.Map.prototype.get = function( key )
{
    var result = null;
    var elementIndex = this.indexOfKey( key );

    if( elementIndex != (-1) )
    {   
        result = this.valArray[ elementIndex ];
    }  
    
    return result;
}

js_utils.Map.prototype.remove = function( key )
{
    var elementIndex = this.indexOfKey( key );

    if( elementIndex != (-1) )
    {
        this.keyArray.splice(elementIndex, 1);
        this.valArray.splice(elementIndex, 1);
    }  
    
    this.length = this.keyArray.length;
    
    (this.trace) && console.log("Map.remove -->  key: ["+key+"]. New length: "+this.length);
    
    return this.length;
}

js_utils.Map.prototype.size = function()
{
    (this.trace) && console.log("Map.size: "+this.keyArray.length);
    return (this.keyArray.length);  
}

js_utils.Map.prototype.clear = function() { 
      while (this.keyArray.length > 0) { this.keyArray.pop(); this.valArray.pop(); }
      this.length = this.keyArray.length;
      
      (this.trace)  && console.log("Map.clear --> length: "+this.length);
      
      return this.length;
}  

js_utils.Map.prototype.keys = function()
{
    return (this.keyArray);
}

js_utils.Map.prototype.values = function()
{
    return (this.valArray);   
}

js_utils.Map.prototype.showMe = function()
{
    var result = "";
    
    for( var i = 0; i < this.keyArray.length; i++ )
    {
    	var strValues = "";
    	for (myKey in this.valArray[ i ]) {
    		strValues+= this.valArray[ i ][myKey]+", ";
    	}
        result += "Key: " + this.keyArray[ i ] + "\tValues: " + strValues + "\n";
    }
    return result;
}

js_utils.Map.prototype.indexOfKey = function( key )
{
    var result = (-1);

    for( var i = 0; i < this.keyArray.length; i++ )
    {
        if( this.keyArray[ i ] == key )
        {
            result = i;
            break;
        }
    }
    return result;
}

js_utils.Map.prototype.removeAt = function( index )
{
  var part1 = this.slice( 0, index);
  var part2 = this.slice( index+1 );
  
  this.length = this.keyArray.length;
  (this.trace)  && console.log("Map.removeAt: "+index+" New length: "+this.length);

  return( part1.concat( part2 ) );
}

js_utils.Map.prototype.containsKey = function( key )
{
	return (this.indexOfKey(key) !== -1); 
}

js_utils.Map.prototype.containsValue = function( value )
{
	return (this.values.indexOf(value) !== -1); 
}

//---------------------------------- /MAP ------------------------------//


//---------------------------------- Multi Value MAP ------------------------------//

js_utils.MultiValueMap = function() {
    // members
    this._map = new js_utils.Map(); // The internal map
}

/*
 * Inserts a value for the given key. If a value already exists, the value is appended at the end of the array.
 */
js_utils.MultiValueMap.prototype.put = function( key, val )
{
    var elementIndex = this._map.indexOfKey( key );
    
    if( elementIndex == (-1) )
    {
        this._map.keyArray.push( key );
        this._map.valArray.push( [val] );
    }
    else
    {
    	// Might not work for Objects!
    	if(this._map.valArray[ elementIndex ].indexOf(val) == -1)
    		this._map.valArray[ elementIndex ].push(val);
    }
    
    this._map.length = this._map.keyArray.length;
    return this._map.length;
}

/*
 * Returns an array of the posible values.
 */
js_utils.MultiValueMap.prototype.get = function( key ) { return this._map.get(key); }
    
/*
 * Removes the key and its value(s).
 */
js_utils.MultiValueMap.prototype.remove = function( key ) { return this._map.remove(); }

js_utils.MultiValueMap.prototype.size = function() { return this._map.size(); }

js_utils.MultiValueMap.prototype.clear = function() { return this._map.clear(); }

js_utils.MultiValueMap.prototype.keys = function() { return this._map.keys(); }

js_utils.MultiValueMap.prototype.values = function() { return this._map.values(); }

js_utils.MultiValueMap.prototype.showMe = function() { 
    
	var result = "";
    for( var ki = 0; ki < this.keys().length; ki++ ) {
    	var strValues = "";
    	for ( var vai = 0; vai < this.values().length; vai++) {
			var aValues = this.values()[vai];
			for (myKey in aValues) {
	    		//strValues+= aValues[ vai ][myKey]+", ";
	    	}
		}
        result += "Key: " + this.keys()[ ki ] + "\tValues: " + strValues + "\n";
    }
    return result; 
}

js_utils.MultiValueMap.prototype.indexOfKey = function( key ) { return this._map.indexOfKey(key); }

js_utils.MultiValueMap.prototype.removeAt = function( index ) { return this._map.removeAt(index); }

js_utils.MultiValueMap.prototype.containsKey = function( key ) { return this._map.containsKey(key); }

js_utils.MultiValueMap.prototype.containsValue = function( value ) { return this._map.containsValue(value); }

//---------------------------------- /Multi Value MAP ------------------------------//



//---------------------------------- RandomIntegerArray ------------------------------//

/*
 * size: The size of the array created. 
 * 	IF: isNaN --> default: 10.
 * from: The floor limit.
 * 	IF: isNaN --> default: 0.
 * to: The ceiling limit.
 * 	IF: isNaN --> default: 100.
 */
js_utils.RandomIntegerArray = function(size, from, to) {
    if(isNaN(size)) size = 10;
    if(isNaN(from)) from = 0;
    if(isNaN(to)) to = 100;
    var arr = new Array();
    for(var i=0; i<size; i++) {
    	arr.push(Math.randomFromTo(from,to))
    } 
    return arr;
  }

//---------------------------------- /RandomIntegerArray ------------------------------//


//---------------------------------- SimpleTree ------------------------------//


js_utils.SimpleTree = function() {
	this._root = null;
	this.data = null;
}

js_utils.SimpleTree.prototype.setData = function(treeData) {
	this.data = treeData;
}

js_utils.SimpleTree.prototype.getData = function() {
	return this.data;
}

js_utils.SimpleTree.prototype.add = function() {
	alert("not implemented");
}

js_utils.SimpleTree.prototype.contains = function() {
	alert("not implemented");
}

js_utils.SimpleTree.prototype.remove = function() {
	alert("not implemented");
}

js_utils.SimpleTree.prototype.size = function() {
	alert("not implemented");
}

js_utils.SimpleTree.prototype.toArray = function() {
	alert("not implemented");
}

js_utils.SimpleTree.prototype.toString = function() {
	alert("not implemented");
}

//---------------------------------- Dateformat (static) ------------------------------//

/**
 * Based on http://www.php.net/manual/en/function.strftime.php 
 * @param {String} format
 * @param {Boolean} capitalize
 */
js_utils.DateFormat = function (format, capitalize) {
	
	this.format = format;
	this.capitalize = capitalize;
	
	useUTC = true;
	getMinutes = useUTC ? 'getUTCMinutes' : 'getMinutes';
	getHours = useUTC ? 'getUTCHours' : 'getHours';
	getDay = useUTC ? 'getUTCDay' : 'getDay';
	getDate = useUTC ? 'getUTCDate' : 'getDate';
	getMonth = useUTC ? 'getUTCMonth' : 'getMonth';
	getFullYear = useUTC ? 'getUTCFullYear' : 'getFullYear';
	setMinutes = useUTC ? 'setUTCMinutes' : 'setMinutes';
	setHours = useUTC ? 'setUTCHours' : 'setHours';
	setDate = useUTC ? 'setUTCDate' : 'setDate';
	setMonth = useUTC ? 'setUTCMonth' : 'setMonth';
	setFullYear = useUTC ? 'setUTCFullYear' : 'setFullYear';
	
	var conf = {
			lang: {
				loading: 'Loading...',
				months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 
						'August', 'September', 'October', 'November', 'December'],
				weekdays: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
				decimalPoint: '.',
				thousandsSep: ','
			},
			global: {
				useUTC: true
			},
	};
	
	function pad (number) {
		return number.toString().replace(/^([0-9])$/, '0$1');
	}
	
	/**
	 * @param {Number} timestamp
	 */
	this.doFormat = function(timestamp) {
		
		var _format = new String(this.format);
		
		
		
		if (!jsUtils.isDefined(timestamp) || isNaN(timestamp)) {
			return 'Invalid date';
		}
		_format = jsUtils.pick(_format, '%Y-%m-%d %H:%M:%S');
		
		//var date = new Date(timestamp * timeFactor),
		var date = new Date(timestamp),
		
		// get the basic time values
		hours = date[getHours](),
		day = date[getDay](),
		dayOfMonth = date[getDate](),
		month = date[getMonth](),
		fullYear = date[getFullYear](),
		lang = conf.lang,
		langWeekdays = lang.weekdays,
		langMonths = lang.months,
			
			// list all format keys
			replacements = {
	
				// Day
				'a': langWeekdays[day].substr(0, 3), // Short weekday, like 'Mon'
				'A': langWeekdays[day], // Long weekday, like 'Monday'
				'd': pad(dayOfMonth), // Two digit day of the month, 01 to 31 
				'e': dayOfMonth, // Day of the month, 1 through 31 
				
				// Week (none implemented)
				
				// Month
				'b': langMonths[month].substr(0, 3), // Short month, like 'Jan'
				'B': langMonths[month], // Long month, like 'January'
				'm': pad(month + 1), // Two digit month number, 01 through 12
				
				// Year
				'y': fullYear.toString().substr(2, 2), // Two digits year, like 09 for 2009
				'Y': fullYear, // Four digits year, like 2009
				
				// Time
				'H': pad(hours), // Two digits hours in 24h format, 00 through 23
				'I': pad((hours % 12) || 12), // Two digits hours in 12h format, 00 through 11
				'l': (hours % 12) || 12, // Hours in 12h format, 1 through 12
				'M': pad(date[getMinutes]()), // Two digits minutes, 00 through 59
				'p': hours < 12 ? 'AM' : 'PM', // Upper case AM or PM
				'P': hours < 12 ? 'am' : 'pm', // Lower case AM or PM
				'S': pad(date.getSeconds()) // Two digits seconds, 00 through  59
				
			};
	
	
		// do the replaces
		for (var key in replacements) {
			_format = _format.replace('%'+ key, replacements[key]);
		}
			
		// Optionally capitalize the string and return
		return capitalize ? _format.substr(0, 1).toUpperCase() + _format.substr(1) : _format;
	} // end: doFormat
	
	this.toIsoFormat = function(timestamp) {
		var uglyStr = new Date(timestamp).toISOString();
		uglyStr = uglyStr.replace("T", " ");
		return uglyStr.substr(0, uglyStr.length-5);
	}
	
	this.toIsoFormatShort = function(timestamp) {
		var pretty = this.toIsoFormat(timestamp).substr(5);
		var month = conf.lang.months[parseInt(pretty.substr(0,2))].substr(0,3);
		return (month+pretty.substr(2));
	}
};


//---------------------------------- Utils (static) ------------------------------//

js_utils.Utils = function() {
	
	// Constants
	var UNDEFINED = "undefined";
	
	
	// Objects
	this.dateFormatDefault = new js_utils.DateFormat ('%e-%b: %H:%M:%S');
	
	
	// Functions
	
	/**
	 * Return the first value that is defined. Like MooTools' $.pick.
	 */
	this.pick = function() {
		var args = arguments,
			i,
			arg,
			length = args.length;
		for (i = 0; i < length; i++) {
			arg = args[i];
			if (typeof arg !== 'undefined' && arg !== null) {
				return arg;
			}
		}
	}
	
	/**
	 * Returns true if the object is NOT: (Like MooTools' $.defined.)
	 * - null
	 * - undefined
	 * 
	 * @param {Object} obj
	 */
	this.isDefined = function(obj) {
		return (obj !== undefined && obj !== UNDEFINED && obj !== null);
	}
	
	this.isNotDefined = function(obj) {
		return (obj === undefined && obj === UNDEFINED && obj === null);
	}
	
	/**
	 * ... TODO: check this!
	 */
	this.hasValue = function(obj) {
		if(!this.isDefined(obj))
			return false;
		if(obj.constructor.toString().indexOf('function Object()') != -1)
			return true;
		if(obj.constructor.toString().indexOf('function Array()') != -1)
			return (obj.length);
		if(isNaN(obj))
			return (obj.length);
		return true;
	}
	
	this.cutPretty = function(str, maxLength) {
		if(str.length <= maxLength)
			return str;
		else
			return (str.substr(0,maxLength-3)+"...");
	}
	
	
	this.roundTo1decimal = function(val) {
		var n = parseFloat(val);
		if (!isNaN(n)) {
			n *= 10;
			n = parseInt(n);
			n=(Math.round(n)/10);
			return n;
		}
		else
			return false;
	}
	
	
	this.ttlExpired = function(msCreated, msTTL, msLimit) {
		if(!this.hasValue(msLimit)) msLimit = new Date().getTime();
		return ( msLimit < (msCreated+msTTL) );
	}
	
	/* Review...
	this.hasAttr = function(obj, attr) { 
		var attribVal = this.attr(attr); return (attribVal !== undefined) && (attribVal !== false);
	}
	*/
	
	
	
} // end: js_utils.Utils

//js_utils.Validate = function() {}


// Global definitions

var jsUtils = new js_utils.Utils();






//MISC... REVIEW - move?

function xcLogObj(obj) {
	var msg = "Object: ";
	if(typeof obj.id != 'undefined')
		msg+=obj.id;
		
	console.log(msg);
	var strObj = '';
	for(prop in obj) {
	  strObj+= prop + ': ' + obj[prop]+'; ';
	}
	console.log("props: "+strObj);
}

function xcLogObjs(aObjs) {
	for ( var i = 0; i < aObjs.length; i++) {
		xcLogObj(aObjs[i]);
	}
}


function xcObjs2Str(objs) {
	
	/*alert(xcGetClass(objs));
	alert(xcIsArray(objs));*/
	if(xcIsArray(objs)) {
		return __xcObjs2Str(objs);
	}
	else {
		return __xcObj2Str(objs);
	}
}

function __xcObj2Str(obj) {
	var obj2Str = "{ ";

	var sProps = '';
	for(prop in obj) {
		sProps+= prop + ': ';
		if(typeof obj[prop] != 'object')
			sProps+= obj[prop]+'; ';
		else
			sProps+= __xcObj2Str(obj[prop]);
	}
	obj2Str+=sProps;
	obj2Str+=" } ";
	return obj2Str; 
}

function __xcObjs2Str(aObjs) {
	var objs2Str = '[ ';
	for ( var i = 0; i < aObjs.length; i++) {
		objs2Str +="<br>";
		objs2Str += __xcObj2Str(aObjs[i])+", ";
	}
	objs2Str +="<br>";
	objs2Str += ' ]';
	return objs2Str;
}



// Object definitions //

/**
 * Returns: 
 * 		Array --> [object Array]
 */
function xcGetClass(obj) {
	return Object.prototype.toString.call(obj);
}

function xcIsArray(obj) {
	return (Object.prototype.toString.call(obj) === '[object Array]');
}

function xcEnsureArray(obj) {
	return (xcIsArray(obj) && obj || [obj]); 
}

/** IMPROVE TEST! */
function xcGetObjFields(obj) {
	var aProps = new Array();
	for(prop in obj) {
		if(obj[prop] === null)
			aProps.push(prop);
	}
	return aProps;
}


