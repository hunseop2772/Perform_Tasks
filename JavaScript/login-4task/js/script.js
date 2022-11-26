function sendit(){

    const userid = document.getElementById('userid');
    const userpw = document.getElementById('userpw');
    const userpw_re = document.getElementById('userpw_re')
    const name = document.getElementById('name');
    const hp = document.getElementById('hp');
    const hp1 = document.getElementById('hp1');
    const email = document.getElementById('email');
    const gender = document.getElementsByName('gender');
    const char = document.getElementById('char');

    // 정규 표현식
    const expIdText = /^(?=.*[a-z])(?=.*[A-Z])[A-Za-z0-9]{3,16}$/; 
    // 3~16 자리이며 대문자 소문자 한자 이상 각각 들어가야 하며 숫자까지만 사용가능
    const expPwText = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    const expNameText = /^[가-힣]+$/;
    const expHpText = /^\d{3}\d{3,4}\d{4}$/;// - 제거
    const expHp1Text = /^\d{3}\d{3,4}\d{4}$/;// - 제거
    const expEmailText = /^[A-Za-z0-9\-\.]+@[A-Za-z0-9\-\.]+\.[A-Za-z0-9]+$/;
    const expChartext = /^[가-힣]{2,}[a-zA-Z]{4,}\d{0,}$/;

  

  
    if(userid.value == ''){
        alert('아이디를 입력하세요');
        userid.focus();
        return false;
    }
    if(!expIdText.test(userid.value)){
        alert('아이디는 3자 이상 16자 이하이며 대문자 소문자는 각각 1개 이상 \n들어가야 하며 숫자까지 사용 가능합니다.');
        userid.focus();
        return false;
    }

    if(userpw.value==''){
        alert('비밀번호를 입력하세요');
        userpw.focus();
        return false;
    }

    if(!expPwText.test(userpw.value)){
        alert('비밀번호 형식을 확인하세요\n소문자,대문자,숫자,특수문자 1개씩 꼭 입력해야합니다');
        userpw.focus();
        return false;
    }

    if(userpw.value != userpw_re.value){
        alert('비밀번호와 비밀번호 확인의 값이 다릅니다');
        userpw_re.focus;
        return false;
    }

    if(!expNameText.test(name.value)){
        alert('이름은 한글로 입력하세요');
        name.focus();
        return false;
    }

    if(!expChartext.test(char.value)){
        alert('별명 확인하세요 한글, 영어 순으로 2자 / 4자');
        char.focus();
        return false;
    }

    if(!expEmailText.test(email.value)){
        alert('이메일 형식을 확인하세요');
        email.focus();
        return false;
    }

    if(regform.gender.value == ""){
        alert('성별을 선택하세요');
        regform.gender.focus();
        return false;
    }


    if(!expHpText.test(hp.value)){
        alert('휴대폰번호 형식을 확인하세요\n하이픈(-)을 제거해야 합니다.');
        hp.focus();
        return false;
    }

    if(!expHpText.test(hp1.value)){
        alert('휴대폰번호 형식을 확인하세요\n하이픈(-)을 제거해야 합니다.');
        hp1.focus();
        return false;
    }

 


   
 


    return true;
    
}


if (window.addEventListener) {
    window.addEventListener('load', InitEvent, false);
}
var canvas, context, tool;
function InitEvent() {
    canvas = document.getElementById('drawCanvas');
    if (!canvas) {
	alert("캔버스 객체를 찾을 수 없음");
	return;
    }
    if (!canvas.getContext) {
	alert("Drawing Contextf를 찾을 수 없음");
	return;
    }
    context = canvas.getContext('2d');
    if (!context) {
	alert("getContext() 함수를 호출 할 수 없음");
	return;
    }
    // Pencil tool 객체를 생성 한다.
    tool = new tool_pencil();
    canvas.addEventListener('mousedown', ev_canvas, false);
    canvas.addEventListener('mousemove', ev_canvas, false);
    canvas.addEventListener('mouseup', ev_canvas, false);
    canvas.addEventListener('touchstart', ev_canvas, false);
    canvas.addEventListener('touchmove', ev_canvas, false);
    canvas.addEventListener('touchend', ev_canvas, false);
}
function tool_pencil() {
    var tool = this;
    this.started = false;

    // 마우스를 누르는 순간 그리기 작업을 시작 한다. 
    this.mousedown = function (ev) {
	context.beginPath();
	context.moveTo(ev._x, ev._y);
	tool.started = true;
    };
    // 마우스가 이동하는 동안 계속 호출하여 Canvas에 Line을 그려 나간다
    this.mousemove = function (ev) {
	if (tool.started) {
	    context.lineTo(ev._x, ev._y);
	    context.stroke();
	}
    };
    // 마우스 떼면 그리기 작업을 중단한다
    this.mouseup = function (ev) {
	if (tool.started) {
	    tool.mousemove(ev);
	    tool.started = false;
	}
    };

    // 마우스를 누르는 순간 그리기 작업을 시작 한다. 
    this.touchstart = function (ev) {
	context.beginPath();
	context.moveTo(ev._x, ev._y);
	tool.started = true;
    };
    // 마우스가 이동하는 동안 계속 호출하여 Canvas에 Line을 그려 나간다
    this.touchmove = function (ev) {
	if (tool.started) {
	    context.lineTo(ev._x, ev._y);
	    context.stroke();
	}
    };
    // 마우스 떼면 그리기 작업을 중단한다
    this.touchend = function (ev) {
	if (tool.started) {
	    tool.touchmove(ev);
	    tool.started = false;
	}
    };
}
// Canvas요소 내의 좌표를 결정 한다.
function ev_canvas(ev) {
    if (ev.layerX || ev.layerX == 0) { // Firefox 브라우저
	ev._x = ev.layerX;
	ev._y = ev.layerY;
    }
    else if (ev.offsetX || ev.offsetX == 0) { // Opera 브라우저
	ev._x = ev.offsetX;
	ev._y = ev.offsetY;
    }
    else if (ev.targetTouches[0] || ev.targetTouches[0].pageX == 0) {	//핸드폰
	var left = 0;
	var top = 0;
	var elem = document.getElementById('drawCanvas');

	while (elem) {
	    left = left + parseInt(elem.offsetLeft);
	    top = top + parseInt(elem.offsetTop);
	    elem = elem.offsetParent;
	}

	ev._x = ev.targetTouches[0].pageX - left;
	ev._y = ev.targetTouches[0].pageY - top;
    }
    // tool의 이벤트 핸들러를 호출한다.
    var func = tool[ev.type];
    if (func) {
	func(ev);
    }
}

function onClear() {
    canvas = document.getElementById('drawCanvas');
    context.save();
	context.setTransform(1, 0, 0, 1, 0, 0);
	context.clearRect(0, 0, canvas.width, canvas.height);
	context.restore();
}





function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
                
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}





