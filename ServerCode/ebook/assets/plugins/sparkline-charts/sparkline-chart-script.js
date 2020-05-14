
  $(function() {
    "use strict";

    $('#sparklinechart1').sparkline([5,8,7,10,9,10,8,6,4,6,8,7,6,8,10,8,6,4,6], {
            type: 'bar',
            height: '45',
            barWidth: '3',
            resize: true,
            barSpacing: '3',
            barColor: '#fd3550'
        });
    
    
  $("#sparklinechart2").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
    type: 'line',
    width: '120',
    height: '45',
	resize: true,
    lineColor: '#15ca20',
    lineWidth: '2',
    fillColor: 'rgba(21, 202, 32, 0.2)',
    spotColor: '#15ca20',
    minSpotColor: '#15ca20',
    maxSpotColor: '#15ca20',
    highlightSpotColor: '#15ca20',
    highlightLineColor: '#15ca20'

  });
  

   $("#sparklinechart3").sparkline([0, 5, 10, 5, 15, 10, 20, 10, 5, 10, 5, 15, 10 ], {
		type: 'line',
		width: '120',
		height: '45',
		resize: true,
		lineWidth: '2',
		lineColor: '#008cff',
		fillColor: 'transparent',
		spotColor: '#008cff',
		minSpotColor: '#008cff',
		maxSpotColor: '#008cff',
		highlightSpotColor: '#008cff',
		highlightLineColor: '#008cff'
    }); 

	
     $('#sparklinechart4').sparkline([5,8,7,10,9,10,8,6,4,6,8,7,6,8,10,8,6,4,6], {
		type: 'bar',
		width: '100%',
		height: '170',
		barWidth: '5',
		resize: true,
		barSpacing: '7',
		barColor: '#fd3550'
		
	});
	
	
	$('#sparklinechart5').sparkline([5,8,7,10,9,10,8,6,4,6,8,7,6,8,10,8,6,4,6], {
		type: 'bar',
		width: '100%',
		height: '170',
		barWidth: '5',
		resize: true,
		barSpacing: '7',
		barColor: '#15ca20'
		
	});
	
	
	$('#sparklinechart6').sparkline([5,8,7,10,9,10,8,6,4,6,8,7,6,8,10,8,6,4,6], {
		type: 'bar',
		width: '100%',
		height: '170',
		barWidth: '5',
		resize: true,
		barSpacing: '7',
		barColor: '#008cff'
		
	});
	
	
    $("#sparklinechart7").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
	    type: 'line',
        width: '100%',
        height: '170',
        lineColor: '#0dceec',
		lineWidth: '2',
		resize: true,
		fillColor: 'rgba(13, 206, 236, 0.3)',
		spotColor: '#0dceec',
		minSpotColor: '#0dceec',
		maxSpotColor: '#0dceec',
		highlightSpotColor: '#0dceec',
		highlightLineColor: '#0dceec'
     });


    $("#sparklinechart8").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
	    type: 'line',
        width: '100%',
        height: '170',
        lineColor: '#ff9700',
		lineWidth: '2',
		resize: true,
		fillColor: 'rgba(255, 151, 0, 0.3)',
		spotColor: '#ff9700',
		minSpotColor: '#ff9700',
		maxSpotColor: '#ff9700',
		highlightSpotColor: '#ff9700',
		highlightLineColor: '#ff9700'
     });
	 
	 
	 $("#sparklinechart9").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
	    type: 'line',
        width: '100%',
        height: '170',
        lineColor: '#223035',
		lineWidth: '2',
		resize: true,
		fillColor: 'rgba(34, 48, 53, 0.3)',
		spotColor: '#223035',
		minSpotColor: '#223035',
		maxSpotColor: '#223035',
		highlightSpotColor: '#223035',
		highlightLineColor: '#223035'
     });
	
	
	
    $('#sparklinechart10').sparkline([20, 20, 20], {
            type: 'pie',
            width: '200',
            height: '170',
            resize: true,
            sliceColors: ['#008cff', '#15ca20', '#fd3550']
     });
    

    $('#sparklinechart11').sparkline([20, 20, 20], {
            type: 'pie',
            width: '200',
            height: '170',
            resize: true,
            sliceColors: ['#0dceec', '#ff9700', '#223035']
     });

	 
     $('#sparklinechart12').sparkline([20, 20, 20], {
            type: 'pie',
            width: '200',
            height: '170',
            resize: true,
            sliceColors: ['#75808a', '#939fa9', '#a2abb3']
     });	 
	
	

   });