function showHideCard() {
 var type = document.getElementById("selectType");
 var card = document.getElementById("card-fieldset")
 if(type.value == 'CREDIT_CARD'){
    card.classList.remove('hide');
 }
 else
 {
    card.classList.add('hide');
 }
}