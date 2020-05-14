
(function(window, document, $, undefined) {
	  "use strict";
	$(function() {

		if ($('#lineChart').length) {
			var ctx = document.getElementById('lineChart').getContext('2d');
			var myChart = new Chart(ctx, {
				type: 'line',
				data: {
					labels: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
					datasets: [{
						label: 'Google',
						data: [13, 20, 4, 18, 7, 4, 8],
						backgroundColor: "rgba(0, 140, 255, 0.5)",
						borderColor: "rgba(0, 140, 255)",
						borderWidth: 1
					}, {
						label: 'Facebook',
						data: [3, 30, 6, 6, 3, 4, 11],
						backgroundColor: "rgba(21, 202, 32, 0.5)",
						borderColor: "rgba(21, 202, 32)",
						borderWidth: 1
					}]
				}
			});
		}


		if ($('#barChart').length) {
			var ctx = document.getElementById("barChart").getContext('2d');
			var myChart = new Chart(ctx, {
				type: 'bar',
				data: {
					labels: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
					datasets: [{
						label: 'Google',
						data: [13, 20, 4, 18, 29, 25, 8],
						backgroundColor: "#fd3550"
					}, {
						label: 'Facebook',
						data: [31, 30, 6, 6, 21, 4, 11],
						backgroundColor: "#0dceec"
					}]
				}
			});
		}

		if ($('#radarChart').length) {
			var ctx = document.getElementById("radarChart");
			var myChart = new Chart(ctx, {
				type: 'radar',
				data: {
					labels: ["Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
					datasets: [{
						label: 'Twitter',
						backgroundColor: "rgba(34, 48, 53, 0.5)",
						borderColor: "#223035",
						data: [13, 20, 4, 18, 29, 25, 8]
					}, {
						label: 'Linkedin',
						backgroundColor: "rgba(255, 151, 0, 0.5)",
						borderColor: "#ff9700",
						data: [31, 30, 6, 6, 21, 4, 11]
					}]
				}
			});
		}


		if ($('#polarChart').length) {
			var ctx = document.getElementById("polarChart").getContext('2d');
			var myChart = new Chart(ctx, {
				type: 'polarArea',
				data: {
					labels: ["Primary", "Success", "Danger", "Warning"],
					datasets: [{
						backgroundColor: [
							"#008cff",
							"#15ca20",
							"#fd3550",
							"#ff9700"
						],
						data: [13, 20, 11, 18]
					}]
				}
			});
		}


		if ($('#pieChart').length) {
			var ctx = document.getElementById("pieChart").getContext('2d');
			var myChart = new Chart(ctx, {
				type: 'pie',
				data: {
					labels: ["Info", "Dark", "Danger", "Secondary"],
					datasets: [{
						backgroundColor: [
							"#0dceec",
							"#223035",
							"#fd3550",
							"#75808a"
						],
						data: [13, 20, 11, 18]
					}]
				}
			});
		}


		if ($('#doughnutChart').length) {
			var ctx = document.getElementById("doughnutChart").getContext('2d');
			var myChart = new Chart(ctx, {
				type: 'doughnut',
				data: {
					labels: ["Info", "Success", "Danger", "Dark"],
					datasets: [{
						backgroundColor: [
							"#0dceec",
							"#15ca20",
							"#fd3550",
							"#223035"
						],
						data: [13, 20, 11, 18]
					}]
				}
			});
		}


	});

})(window, document, window.jQuery);