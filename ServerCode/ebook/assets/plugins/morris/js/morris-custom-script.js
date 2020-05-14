
 $(function () {
    "use strict";
	
	
		// chart 1
		Morris.Area({
		  element: 'morris-chart-1',
		  data: [
			{x: '2010 Q4', y: 3, z: 7},
			{x: '2011 Q1', y: 3, z: 4},
			{x: '2011 Q2', y: null, z: 1},
			{x: '2011 Q3', y: 2, z: 5},
			{x: '2011 Q4', y: 8, z: 2},
			{x: '2012 Q1', y: 4, z: 4}
		  ],
		  xkey: 'x',
		  ykeys: ['y', 'z'],
		  labels: ['Y', 'Z'],
		  lineColors: ['#008cff', '#fd3550'],
		  resize: true
		});
	
	
	
	    // chart 2
		Morris.Area({
		  element: 'morris-chart-2',
		  behaveLikeLine: true,
		  data: [
			{x: '2011 Q1', y: 3, z: 3},
			{x: '2011 Q2', y: 2, z: 1},
			{x: '2011 Q3', y: 2, z: 4},
			{x: '2011 Q4', y: 3, z: 3}
		  ],
		  xkey: 'x',
		  ykeys: ['y', 'z'],
		  labels: ['Y', 'Z'],
		  lineColors: ['#15ca20', '#0dceec'],
		  resize: true,
		  fillOpacity: 0.1,
		});
	
	  
	   // chart 3
		Morris.Bar({
		  element: 'morris-chart-3',
		  data: [
			{x: '2011 Q1', y: 3, z: 2, a: 3},
			{x: '2011 Q2', y: 2, z: null, a: 1},
			{x: '2011 Q3', y: 0, z: 2, a: 4},
			{x: '2011 Q4', y: 2, z: 4, a: 3}
		  ],
		  xkey: 'x',
		  ykeys: ['y', 'z', 'a'],
		  labels: ['Y', 'Z', 'A'],
		  resize: true,
		  barColors: ['#15ca20', '#0dceec', '#fd3550']
		});
	
	  
	   // chart 4
		Morris.Bar({
		  element: 'morris-chart-4',
		  data: [
			{x: '2011 Q1', y: 3, z: 2, a: 3},
			{x: '2011 Q2', y: 2, z: null, a: 1},
			{x: '2011 Q3', y: 0, z: 2, a: 4},
			{x: '2011 Q4', y: 2, z: 4, a: 3}
		  ],
		  xkey: 'x',
		  ykeys: ['y', 'z', 'a'],
		  labels: ['Y', 'Z', 'A'],
		  resize: true,
		  stacked: true,
		  barColors: ['#15ca20', '#0dceec', '#fd3550']
		});
	
	
	    // chart 5
		Morris.Donut({
		  element: 'morris-chart-5',
		  data: [
			{value: 70, label: 'Primary'},
			{value: 15, label: 'Success'},
			{value: 10, label: 'Danger'},
			{value: 5, label: 'Info'}
		  ],
		  colors: [
			'#008cff',
			'#15ca20',
			'#fd3550',
			'#0dceec'
		  ],
		  resize: true,
		  formatter: function (x) { return x + "%"}
		});
	
	
	
	    // chart 6
		Morris.Donut({
		  element: 'morris-chart-6',
		  data: [
			{value: 30, label: 'Purple'},
			{value: 20, label: 'Warning'},
			{value: 20, label: 'Dark'},
			{value: 20, label: 'Light'}
		  ],
		  colors: [
			'#b81cff',
			'#ff9700',
			'#223035',
			'#e9eaea'
		  ],
		  resize: true,
		  formatter: function (x) { return x + "%"}
		});
	   
	
	
	
	
   }); 