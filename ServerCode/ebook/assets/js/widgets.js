 
$(function() {
    "use strict";



 // widget chart 1
  var ctx = document.getElementById("widget-chart-1").getContext('2d');

    var gradientStroke5 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke5.addColorStop(0, '#7f00ff');
      gradientStroke5.addColorStop(1, '#e100ff');

      var gradientStroke6 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke6.addColorStop(0, '#fc4a1a');
      gradientStroke6.addColorStop(1, '#f7b733');


      var gradientStroke7 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke7.addColorStop(0, '#283c86');
      gradientStroke7.addColorStop(1, '#45a247');

      var myChart = new Chart(ctx, {
        type: 'pie',
        data: {
          labels: ["Samsung", "Apple", "Nokia"],
          datasets: [{
            backgroundColor: [
              gradientStroke5,
              gradientStroke6,
              gradientStroke7
            ],

             hoverBackgroundColor: [
              gradientStroke5,
              gradientStroke6,
              gradientStroke7
            ],

            data: [50, 50, 50]
          }]
        },
        options: {
            legend: {
              display: false
            },
            tooltips: {
			  displayColors:false
            }
        }
      });


	  
// widget chart 2
var ctx = document.getElementById("widget-chart-2").getContext('2d');

  var gradientStroke8 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke8.addColorStop(0, '#42e695');
      gradientStroke8.addColorStop(1, '#3bb2b8');

      var gradientStroke9 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke9.addColorStop(0, '#4776e6');
      gradientStroke9.addColorStop(1, '#8e54e9');


      var gradientStroke10 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke10.addColorStop(0, '#ee0979');
      gradientStroke10.addColorStop(1, '#ff6a00');

      var myChart = new Chart(ctx, {
        type: 'polarArea',
        data: {
          labels: ["Gross Profit", "Revenue", "Expense"],
          datasets: [{
            backgroundColor: [
              gradientStroke8,
              gradientStroke9,
              gradientStroke10
            ],

             hoverBackgroundColor: [
              gradientStroke8,
              gradientStroke9,
              gradientStroke10
            ],
            data: [5, 8, 7]
          }]
        },
        options: {
            legend: {
              display: false
            },
            tooltips: {
			  displayColors:false
            }
        }
      });


// widget chart 3
var ctx = document.getElementById("widget-chart-3").getContext('2d');

    var gradientStroke11 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke11.addColorStop(0, '#ba8b02');
      gradientStroke11.addColorStop(1, '#181818');

      var gradientStroke12 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke12.addColorStop(0, '#2c3e50');
      gradientStroke12.addColorStop(1, '#fd746c');


      var gradientStroke13 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke13.addColorStop(0, '#ff0099');
      gradientStroke13.addColorStop(1, '#493240');

      var myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ["Jeans", "T-Shirts", "Shoes"],
          datasets: [{
            backgroundColor: [
              gradientStroke11,
              gradientStroke12,
              gradientStroke13
            ],
            hoverBackgroundColor: [
              gradientStroke11,
              gradientStroke12,
              gradientStroke13
            ],
            data: [25, 25, 25]
          }]
        },
        options: {
            legend: {
              display: false
            },
            tooltips: {
			  displayColors:false
            }
        }
      });
	  
	  
	  
	// widget chart 4
	$('#widget-chart-4').sparkline([5,8,7,10,9,10,8,6,4,6,8,7,6,8], {
            type: 'bar',
            height: '35',
            barWidth: '3',
            resize: true,
            barSpacing: '3',
            barColor: '#fff'
        });

     
    
    // widget chart 5
    $("#widget-chart-5").sparkline([0,5,3,7,5,10,3,6,5,10], {
            type: 'line',
            width: '80',
            height: '40',
            lineWidth: '2',
            lineColor: '#fff',
            fillColor: 'transparent',
            spotColor: '#fff',
        })
		
	
	// widget chart 6
     $("#widget-chart-6").sparkline([2,3,4,5,4,3,2,3,4,5,6,5,4,3,4,5], {
        type: 'discrete',
        width: '75',
        height: '40',
        lineColor: '#fff',
        lineHeight: 22

     });
	 
	 

	// widget chart 7	
	$("#widget-chart-7").sparkline([5,6,7,9,9,5,3,2,2,4,6,7], {
		type: 'line',
		width: '100',
		height: '25',
		lineWidth: '2',
		lineColor: '#ffffff',
		fillColor: 'transparent'
		
	});  
	  
	  
  // world map

jQuery('#dashboard-map').vectorMap(
{
    map: 'world_mill_en',
    backgroundColor: 'transparent',
    borderColor: '#818181',
    borderOpacity: 0.25,
    borderWidth: 1,
    zoomOnScroll: false,
    color: '#009efb',
    regionStyle : {
        initial : {
          fill : '#15ca20'
        }
      },
    markerStyle: {
      initial: {
                    r: 9,
                    'fill': '#fff',
                    'fill-opacity':1,
                    'stroke': '#000',
                    'stroke-width' : 5,
                    'stroke-opacity': 0.4
                },
                },
    enableZoom: true,
    hoverColor: '#009efb',
    markers : [{
        latLng : [21.00, 78.00],
        name : 'I Love My India'
      
      }],
    hoverOpacity: null,
    normalizeFunction: 'linear',
    scaleColors: ['#b6d6ff', '#005ace'],
    selectedColor: '#c9dfaf',
    selectedRegions: [],
    showTooltip: true
});



  // widget chart 8 
    var ctx = document.getElementById("widget-chart-8").getContext('2d');
   
      var gradientStroke3 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke3.addColorStop(0, '#42e695');
      gradientStroke3.addColorStop(1, '#3bb2b8');

      var gradientStroke4 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke4.addColorStop(0, ' #7f00ff');
      gradientStroke4.addColorStop(0.5, '#e100ff');

      var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: [1, 2, 3, 4, 5, 6, 7, 8],
          datasets: [{
            label: 'Laptops',
            data: [40, 30, 60, 35, 60, 25, 50, 40],
            borderColor: gradientStroke3,
            backgroundColor: gradientStroke3,
            hoverBackgroundColor: gradientStroke3,
            pointRadius: 0,
            fill: false,
            borderWidth: 1
          }, {
            label: 'Mobiles',
            data: [50, 60, 40, 70, 35, 75, 30, 20],
            borderColor: gradientStroke4,
            backgroundColor: gradientStroke4,
            hoverBackgroundColor: gradientStroke4,
            pointRadius: 0,
            fill: false,
            borderWidth: 1
          }]
        },
		options:{
		  legend: {
			  position: 'bottom',
              display: true,
			  labels: {
                boxWidth:12
              }
            },
			tooltips: {
			  displayColors:false,
			},	
		  scales: {
			  xAxes: [{
				barPercentage: .5
			  }]
		     }
		}
      });
	  
	  
	  
	  
  // widget chart 9
 var ctx = document.getElementById('widget-chart-9').getContext('2d');

  var gradientStroke1 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke1.addColorStop(0, '#3bb2b8');
      gradientStroke1.addColorStop(1, '#42e695');

      var myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: [1, 2, 3, 4, 5, 6, 7],
          datasets: [{
            label: 'Views',
            data: [3, 30, 10, 10, 22, 12, 5],
            pointBorderWidth: 2,
            pointHoverBackgroundColor: gradientStroke1,
            backgroundColor: gradientStroke1,
            borderColor: 'transparent',
            borderWidth: 1
          }]
        },
        options: {
            legend: {
			  position: 'bottom',
              display:false
            },
            tooltips: {
			  displayColors:false,	
              mode: 'nearest',
              intersect: false,
              position: 'nearest',
              xPadding: 10,
              yPadding: 10,
              caretPadding: 10
            },
			scales: {
			  xAxes: [{
				ticks: {
                    beginAtZero:true,
                    fontColor: '#ddd'
                },
				gridLines: {
				  display: true ,
				  color: "rgba(221, 221, 221, 0.08)"
				},
			  }],
			   yAxes: [{
				ticks: {
                    beginAtZero:true,
                    fontColor: '#ddd'
                },
				gridLines: {
				  display: true ,
				  color: "rgba(221, 221, 221, 0.08)"
				},
			  }]
		     }

         }
      });  
	  
	  
 // widget chart 10
 var ctx = document.getElementById('widget-chart-10').getContext('2d');

      var gradientStroke1 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke1.addColorStop(0, '#17ead9');
      gradientStroke1.addColorStop(1, '#6078ea');

      var gradientStroke2 = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStroke2.addColorStop(0, '#f80759');
      gradientStroke2.addColorStop(1, '#bc4e9c');

      var myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: [1, 2, 3, 4, 5, 6, 7, 8],
          datasets: [{
            label: 'Apple',
            data: [0, 30, 60, 25, 60, 25, 50, 0],
            pointBorderWidth: 2,
            pointBackgroundColor: 'transparent',
			pointHoverBackgroundColor: gradientStroke1,
            backgroundColor: gradientStroke1,
            borderColor: gradientStroke1,
            borderWidth: 2
          }, {
            label: 'Samsung',
            data: [0, 60, 25, 80, 35, 75, 30, 0],
            pointBorderWidth: 2,
            pointBackgroundColor: 'transparent',
			pointHoverBackgroundColor: gradientStroke2,
            backgroundColor: gradientStroke2,
            borderColor: gradientStroke2,
            borderWidth: 2
          }]
        },
        options: {
            legend: {
              display: true,
			  labels: {
                boxWidth:40
              }
            },
            tooltips: {
			  displayColors:false
            }

         }
      });





});	  
	  
	  
	  
	  
	  