/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var module = (function() {
    // write everything as private first
    function private1() { /* things */ }; 
    function private2() { /* things */ }; 
    function private3() { /* things */ }; 
    function private4() { /* things */ }; 
    function private5() { /* things */ }; 
    
    // then in the return 'reveal' what needs to be public
    return {
        'public1': private1,
        'public2': private3,
        'public3': private5
    };
})();