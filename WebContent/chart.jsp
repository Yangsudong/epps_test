<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
  <head>
	<meta charset="UTF-8">
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '부서');
        data.addColumn('number', '사원수');
        
        var datatable = [];
        //ajax
        $.ajax({
        	async : false,
        	url :"ajax/ChartData.do",
        	dataType : "json",
        	success : function(datas){
        		for(i=0; i<datas.length; i++) {
        			datatable.push( [ datas[i].name, parseInt(datas[i].cnt) ]);
        		}
        		
        	}
        });
        data.addRows(datatable);

        // Set chart options
        var options = {'title':'부서별 사원수',
                       'width':700,
                       'height':500};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
</head>


<body>
 <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
</body>
</html>