       
$(function() {
    "use strict";

	   //pie
            $("span.pie").peity("pie",{
                width: 90,
                height: 90 
            });
        
        //donut

          $("span.donut").peity("donut",{
                width: 90,
                height: 90 
            });

         // line
         $('.peity-line').each(function() {
            $(this).peity("line", $(this).data());
         });

         // bar
          $('.peity-bar').each(function() {
            $(this).peity("bar", $(this).data());
         });
         
   });