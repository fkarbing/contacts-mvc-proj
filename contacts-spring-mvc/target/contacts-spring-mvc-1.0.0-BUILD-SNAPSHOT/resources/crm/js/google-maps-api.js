/**
 * Pre-requisists:
 *  
 *     jQuery: Must be loaded before using the functions
 *     maps.google.com: http://maps.google.com/maps/api/js?sensor=true
 *  
 */


/** 
 * Takes coordinates on the form "lat,lng" and attaches the fetched map to the div passed as argument.
 *
 * Example: 
 *         var targetDivId = "map";
        var strLatLng = "59.33230529999999,18.05283570";
        loadMapAndShow(map, strLatLng);
        
        var address = "V??stra J??rnv??gsgatan 21 111 64 STOCKHOLM";
 */
   function gmApiLoadMapAndShow(targetDivId, strCenterLatLng) {
        
        console.log(targetDivId);
        console.log(strCenterLatLng);
        
        var asLatLng = strCenterLatLng.split(',');
        var aLatLng = $.map(asLatLng, function(val, i) { return parseFloat(val); });
        // maps.LatLng
        var myLatlng = new google.maps.LatLng(aLatLng[0], aLatLng[1]);
        // map options
        var myOptions = {
            zoom : 11,
            center : myLatlng,
            mapTypeId : google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(
                $('#'+targetDivId)[0],
                myOptions);
        
        return map;
   }
   
   /**
    * 
    * @param map - the map previously created.
    * @param locations - A multiarray, where each elem: [name, lat, lng, z-index]
    * @param markerConf - A markerConf obj (TODO: Improve!) dir - where markers are.
    */
   function gmApiSetMarkersOnMap(map, locations, markerConf) {
	   
	   
      var shape = {
                coord: [1, 1, 1, 20, 18, 20, 18 , 1],
                type: 'poly'
        };
      for (var i = 0; i < locations.length; i++) {
    	  // todo: newflag() method...
    	  var markerImgPath;
    	  if(i === 0)
    		  markerImgPath = markerConf.dir+'/'+'dollar_sign';
    	  else
    		  markerImgPath = markerConf.dir+'/'+'consultant';
    		
    	  // = markerConf.dir+'/'+(locations[i][0].toLowerCase());
          var image = new google.maps.MarkerImage(markerImgPath+'.png',
            new google.maps.Size(17, 19),
            new google.maps.Point(0,0),
            new google.maps.Point(0, 19));
          
          image = new google.maps.MarkerImage(markerImgPath+'.png',
        	      // This marker is 20 pixels wide by 32 pixels tall.
        	      new google.maps.Size(20, 24),
        	      // The origin for this image is 0,0.
        	      new google.maps.Point(0,0),
        	      // The anchor for this image is the base of the flagpole at 0,32.
        	      new google.maps.Point(0, 24));

          
          var place = locations[i];
          var modLat = place[1]+(i*0.0015);
          var modLng = place[2]+(i*0.0015);
          
          
          
          
        	  
          var myLatLng = new google.maps.LatLng(modLat, modLng);
          var marker = new google.maps.Marker({
              position: myLatLng,
              map: map,
              icon: image,
              shape: shape,
              title: place[0],
              zIndex: place[3]
          });
      } // end: for. 
   }