$('div').click(function(event) {
    var ele = $(this).get(0);
	clickedOnSomething(ele.tagName, ele.id, ele.className, 'click');
	event.stopPropogation();
});
$('span').click(function(event) {
    var ele = $(this).get(0);
	clickedOnSomething(ele.tagName, ele.id, ele.className, 'click');
    event.stopPropogation();
});
$('input').change(function() {
    var ele = $(this).get(0);
	clickedOnSomething(ele.tagName, ele.id, ele.className, 'change', ele.type, ele.value);
});
$('form').submit(function() {
    var ele = $(this).get(0);
	clickedOnSomething(ele.tagName, ele.id, ele.className, 'form_submit');
});
$('button').click(function() {
    var ele = $(this).get(0);
	clickedOnSomething(ele.tagName, ele.id, ele.className, 'click');
	event.stopPropogation();
});
