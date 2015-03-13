

var c = document.getElementById("can");
var ctx = c.getContext("2d");
var countPoint = 0;
var countMouvement = 0;
var dt = 1000 / 50;
var sumT = 0;
function display() {
//    console.log("coucou");
    var anim;
    var points;
    sumT += dt * 2;
    for (var i = 0; i < animations.length; i++) {
        anim = animations[i];
        if(!anim.countMouvement){
            anim.countMouvement = 0;
        }
        if(!anim.countPoint){
            anim.countPoint = 0;
        }
        if (anim.countPoint === 0) {
            anim.points = anim[anim.countMouvement].points;
        }
        var point =  anim.points[anim.countPoint];
        var curMvt = anim[anim.countMouvement];
        if (sumT > anim[anim.countMouvement].start) {
            ctx.beginPath();
            ctx.moveTo(point.x, point.y);
            anim.countPoint++;
            if (anim.countPoint > anim.points.length - 1) {
                anim.countMouvement++;
                anim.countPoint = 0;

                if (anim.countMouvement === anim.length){
                     animations.splice(animations.indexOf(anim),1);
                     if(animations.length === 0){
                         window.clearInterval(id);
                     }
                }
                   
            } else {
                ctx.strokeStyle  = anim[anim.countMouvement].htmlColor;
                ctx.lineTo(anim.points[anim.countPoint ].x, anim.points[anim.countPoint].y);
                ctx.stroke();
            }
        }

    }



}


var id = window.setInterval(display, dt);
//    display();