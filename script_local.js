function convert_to_long_array(int_array) {
    var long_array = [];
    for( var i in int_array ) {
        long_array.push(new java.lang.Long(int_array[i]));
    }
    return long_array;
}

var metricIdsArray = [[1479,1480,1481,1482,1483,1484,1485]
                     ];
var mdc = com.appdynamics.platform.GuiceEJBInjectorHolder.getInjector().getInstance(Packages.com.appdynamics.metadata.cache.IMetadataCache.class);
var result = new java.util.ArrayList();

for( var i in metricIdsArray ) {
  var metricIds = java.util.Arrays.asList(convert_to_long_array(metricIdsArray[i]));
  var metrics = mdc.getMetricsByIds(metricIds);

  var iterator = metrics.iterator();
  while(iterator.hasNext()) {
      var metric = iterator.next();
      if(metric!= null) {
          result.add(metric.getId() + ",\t" + metric.getApplicationId() + ",\t" + metric.getName() + "\n");
      }
  }
  result.add("\n")
  result.add("==================================\n\n")
}

result;