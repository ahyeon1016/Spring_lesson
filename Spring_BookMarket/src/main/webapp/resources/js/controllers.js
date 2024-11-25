function addToCart(action){
	document.addForm.action = action;
	document.addForm.submit();
	alert("도서가 장바구니에 추가되었습니다.");
}

function removeFromCart(action)
{
   document.removeForm.action = action;
   document.removeForm.submit();
   setTimeout(function()
   {
      window.location.reload();
   }, 100)
}

function clearCart(){
	document.clearForm.submit();
	setTimeout(function()
	{
	   window.location.reload();
	}, 100)
}