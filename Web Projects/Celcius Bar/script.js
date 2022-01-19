function set(){
    let degree = Number($('.input').val());
    if(degree > 0){
        setDegree(degree*8+200)
    }
    else{
        setDegree(200 + degree*8)
    }
    
}

function setDegree(input){
    $('.slider').css("width", input);
}

window.onload = function() {
    setDegree(200);
  };