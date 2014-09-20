
rand = function(i) {
    return Math.floor(Math.random() * i + 1);
};

x = {};

x.get = function(id) {
    return document.getElementById(id);
};
x.write = function(i, j, text) {
    this.get(i + "+" + j).innerHTML = text;
};


al = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
AL = [];

function randTab() {
    for (var i = 0; i < 30; i++) {
        for (var j = 0; j < 20; j++) {
            var r = rand(al.length - 1);
            x.write(i, j, al[r]);
        }
    }

};
for (var i = 0; i < al.length; i++) {
    AL[i] = al[i].toUpperCase();
};
function Casse(i, j) {
    this.i = i;
    this.j = j;
    this.run;
    this.groupName;
    this.el = x.get(i + "+" + j);
    this.go = true;
    this.moy = 0;
    this.final = "<img  src=\"http://www.camizone.com/camizone/emoticone/Love/Coeur/Coeur-5.gif\" />";
    this.finalColor = "white";
    this.stop = function() {
        window.clearInterval(this.run);
    };
    this.isGo = function() {
        return this.go;
    };
    this.ch = function() {
        var a = this;
        var r = rand(al.length - 1);
        if (this.isGo()) {
            this.write(al[r]);
            this.run = window.setTimeout(function() {
                a.ch();
            }, rand(this.moy));
        } else {
            this.write(this.final);
            this.el.style.color = this.finalColor;
            if(this.finishBy){
                this.finishBy(this);
            }
        }

    };
    this.randomLettere = function(moySeconde) {
        this.moy = moySeconde;
        this.ch();

    };
    this.write = function(text) {
        this.el.innerHTML = text;
    };
    this.getMoy = function() {
        return moy;
    };
    this.writeLater = function(string,time){
        var a = this;
      
       // a.final = string;
         window.setTimeout(function() {
                a.go = false;
                a.final = string;
                a.write(string);
            }, time);
    };
};

var mat = [];
var maxI = 45   ;
var maxJ = 13;
for (var i = 0; i < maxI; i++) {
    mat[i] = [];
    for (var j = 0; j < maxJ; j++) {
        var ccase = new Casse(i, j);
        
        ccase.randomLettere(5000);
        mat[i][j] = ccase;
        

    };

};
var ev1 = "mon amour tu memanque,";
var ev2 = "je pense à toi tendrement,";
var ev3 = "mille baisers sur ton coeur,";
var ev4 = "des gilis sur ton corp.";
var groupText = {};
writeWorld(10, 3, ev1);
writeWorld(14, 5, ev2);
writeWorld(7, 7, ev3);
writeWorld(12, 9, ev4);
function writeWorld(i, j, string) {
    for (var i0 = i; i0 < string.length + i; i0++) {
        mat[i0][j].final = string.charAt(i0 - i);
        mat[i0][j].finalColor = "black";
        mat[i0][j].groupName = groupText;
    };
 


};
window.setTimeout(function()
{
    for (var i = 0; i < maxI; i++) {
        for (var j = 0; j < maxJ; j++) {
            if(mat[i][j].groupName === groupText){
                mat[i][j].go = false;
            }
            
        }
    };
}, 4000);
window.setTimeout(function()
{
    for (var i = 3; i < maxI - 3; i++) {
        for (var j = 2; j < maxJ - 2; j++) {
            if(mat[i][j].groupName !== groupText){
                mat[i][j].go = false;
                mat[i][j].writeLater(' ',100+  rand(5000));
               
                
            }
            
        }
    }
}, 6000);
window.setTimeout(function()
{
    for (var i = 0; i < maxI; i++) {
        for (var j = 0; j < maxJ; j++) {
            if(mat[i][j].groupName !== groupText){
                mat[i][j].go = false;
            }
            
        }
    }
}, 11000);








