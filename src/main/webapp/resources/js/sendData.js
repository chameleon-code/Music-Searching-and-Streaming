/**
 *	JS for Pass data to webplayer 
 *
 * */


//Global Var
var popupPlayer;  //popup (child window)
totalforPrint = new Array(); //total playlist
afterforPrint = new Array(); 
doc = document;  //window

//CheckBox total check event
$(document).ready(function(){
	$("#totalListCheck").click(function(){
		if($("#totalListCheck").prop("checked")){
			$("input[name='addsong']").prop("checked", true);
		}else{
			$("input[name='addsong']").prop("checked", false);
		}
	});
});

// make song Object
function songWrapper(form){
	var oneObj = {
		"title" : form.title.value,
		"artist" : form.artist.value,
		"album" : form.album.value,
		"duration" : form.duration.value,
		"filePath" : form.filePath.value
	};
	return oneObj;
}

//Play All data --> Array
function allsongWrapper(){
	var allArr = new Array();
	var allforms = doc.paramValue;
	for(var i=0; i<allforms.length; i++){
		allArr.push(songWrapper(allforms[i]));
	}
	return allArr;
}

//Make index array clicked checkBox
function checkedSong(jQuery){
	var check = $("input[name='addsong']:checked");
	var checkLen = $("input[name='addsong']:checked").length;
	var numArr = new Array();
	var i = 0;
	
	if(checkLen > 1){
		check.each(function(index){
			numArr[i] = $(this).val();
			i++;
		});
	}else if(checkLen == 1){
		numArr[0] = check.val();
	}
	return numArr;
}

//Create Selected Song Data
function playAdd(){
	var checked = checkedSong();	
	var addSongArr = new Array();  //total selected data
	
	if(checked.length == 0){
		alert("담고 싶은 노래에 체크를 해주세요.");
	}else{
		
		for(var i=0; i<checked.length; i++){
			var idx = checked[i];
			idx = parseInt(idx);
		
			var addObj = songWrapper(doc.paramValue[idx-1]);
			addSongArr.push(addObj);
		}
		returnUrl(addSongArr);
		playing();
	}
}

//Play One (1)
function playnow(no){
	this.no = no;
	var oneArr = new Array();
	
	if(doc.paramValue[0].type == "hidden"){  // data = 1 (only form)
		var song = songWrapper(doc.paramValue);
		oneArr.push(song);
	}else{	// data > 1 --> Object (several form)
		var playform = doc.paramValue[no-1];
		var song = songWrapper(playform);
		oneArr.push(song);
	}
	returnUrl(oneArr); 
	playing();
}
		
//Play All (15)
function playAllNow(){
	if( $("#totalListCheck").prop("checked") ){ //When checked all list 
		returnUrl(allsongWrapper());
		playing();
	}else {  //if do not check list, checking list
		$("input[name='addsong']").prop("checked", true);
		$("#totalListCheck").prop("checked", true);
		returnUrl(allsongWrapper());
		playing();
	}
}

//Return URL & Save Data 
function returnUrl(data){
		
	$.ajax({
		type : "POST",
		url  : "/player/playList",
		data : JSON.stringify(data),
		success : function(urlData) {
			if(totalforPrint == null){
				afterforPrint.push(urlData);
			}else{
				totalforPrint.push(urlData); //Before redirect this window, added song
			}
		},
		error : function(err){
			alert("오디오를 불러오는데 실패했습니다.");
		},
		headers : {
			'Accept' : 'application/json; charset=UTF-8',
			'Content-Type' : 'application/json; charset=UTF-8'
		}
	});
}

//popup Player & Play song 
function playing(){
	var option = "width=650, height=450, resizeable=no, scrollbars=yes, left=220, top=50";	
	popupPlayer = window.open("", "Player", option);
	
	$.get("/player/webplayer", function(){
		popupPlayer.location ="/player/webplayer";
	});
	
}

