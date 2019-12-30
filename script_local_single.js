function convert_to_long_array(int_array) {
    var long_array = [];
    for( var i in int_array ) {
        long_array.push(new java.lang.Long(int_array[i]));
    }
    return long_array;
}

var metricIdsArray = [[1479,1480,1481,1482,1483,1484,1485]
                     ];
var context = Packages.com.singularity.ee.controller.beans.ContextImpl.getInstance();
var imdc = context.getClass().getClassLoader().loadClass("com.appdynamics.metadata.cache.IMetadataCache")
var mdc = com.appdynamics.platform.GuiceEJBInjectorHolder.getInjector().getInstance(imdc);
var result = new java.util.ArrayList();

for( var i in metricIdsArray ) {
  try {
    for (var j in metricIdsArray[i]) {
      var metric = mdc.getMetricById(new java.lang.Long(metricIdsArray[i][j]));
      if(metric!= null) {
        result.add(metric.getId() + ",\t" + metric.getApplicationId() + ",\t" + metric.getName() + ";");
      }
    }
  } catch(e) {
    result.add("error: " + i + ", " + j + ", " + e);
  }
  result.add("==================================" + i + "\n\n")
}

result;