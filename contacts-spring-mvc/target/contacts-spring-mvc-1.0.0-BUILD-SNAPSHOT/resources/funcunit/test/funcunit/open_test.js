module("funcunit-open")

test("URL Test", function(){
	var path = FuncUnit.getAbsolutePath("http://foo.com")
	equals(path, "http://foo.com", "paths match");
	
	path = FuncUnit.getAbsolutePath("//myapp/mypage.html")
	
	equals(path, steal.root.join("myapp/mypage.html"), "paths match");
})



test("Back to back opens", function(){
	S.open("//funcunit/test/myotherapp.html");
	S.open("//funcunit/test/myapp.html");

	S("#changelink").click(function(){
		equals(S("#changelink").text(), "Changed","href javascript run")
	})
})


test("Back to back opens with hash", function(){
	S.open("//funcunit/test/myapp.html?bar#foo");
	S("#changelink").click(function(){
		equals(S("#changelink").text(), "Changed","href javascript run")
	});
	
	S.open("//funcunit/test/myapp.html?bar#foo2");
	S("#changelink").text(function(text){
		return text === "Change";
	});
})

test('Testing win.confirm in multiple pages', function() {
	S.open('//funcunit/test/open/first.html');
	S('.next').click();

	S('.show-confirm').click();
	S.confirm(true);
	S('.results').text('confirmed!', "Confirm worked!");
})