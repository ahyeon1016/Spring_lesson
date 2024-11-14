var btn = document.querySelector("#btn");
btn.addEventListener("click", add);

function add(){
	var addBook = document.addBook;
	
	var bookId = document.querySelector("#bookId").value;
	var name = document.querySelector("#name").value;
	var unitPrice = document.querySelector("#unitPrice").value;
	var description = document.querySelector("#description").value;
	var unitInStock = document.querySelector("#unitInStock").value;

	console.log(bookId);
	console.log(name);
	console.log(unitPrice);
	console.log(description);
	console.log(unitInStock);
	
	//도서 아이디 체크
	var regExpBookId = /^ISBN[0-9]{4,11}$/;
	if( !regExpBookId.test(bookId) ){
		alert("[도서코드]\nISBN과 숫자를 조합하여 5~12자 까지 입력하세요. \n첫 글자는 반드시 ISBN로 시작하세요!");
		addBook.bookId.select();
		return false;
	}
	console.log("bookId 통과");
	
	//도서명 체크
	if(name.length<4||name.length>50){
		alert("[도서명]\n최소 4자에서 최대 50자까지 입력하세요.")
		addBook.name.select();
		return false;
	}
	console.log("name 통과");
	
	//도서 가격 체크
	if(unitPrice<0){
		alert("[가격]\n음수를 입력할 수 없습니다.");
		addBook.unitPrice.focus();
		return false;
	}
	
	if(unitPrice.length==0 || isNaN(unitPrice)){
		alert("[가격]\n숫자만 입력하세요.");
		addBook.unitPrice.focus();
		return false;
	}
	console.log("unitPrice 통과");
	
	//상세정보 체크
	if(description.length<30){
		alert("[상세설명]\n최소 30자 이상 입력하세요.");
		addBook.description.focus();
		return false;	}
	
	//재고 수 체크
	if(unitInStock.length==0||isNaN(unitInStock)){
		alert("[재고 수]\n숫자만 입력하세요.");
		addBook.unitInStock.focus();
		return false;
	}
	console.log("unitInStock 통과");
	alert("책을 등록합니다");
	addBook.submit();
}