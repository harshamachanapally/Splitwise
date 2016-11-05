$(document).ready(function($){
	$("h1").mouseenter(function(){
		$(this).fadeOut('slow');
	});
	$("h1").mouseleave(function(){
		$(this).fadeIn('slow');
	});
	});