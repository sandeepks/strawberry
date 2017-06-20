package com.dataworks.throttle;



import com.cloudera.sqoop.SqoopOptions;
import com.google.common.collect.Lists;
import org.apache.commons.cli.ParseException;
import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.tool.ImportTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Sandeep on 5/22/17.
 */
public class IngestTask<V> implements Callable<V> {

    private ConnectionCounter counter;
    private ImportContext context;

    public IngestTask(ConnectionCounter counter, ImportContext context) {
        this.counter = counter;
        this.context = context;
    }

    public V call() throws Exception {
        V returnValue = null;
        return returnValue;
    }
}

class DBIngest{

    private ImportTool importTool;
    private Configuration conf;
    private ImportContext context;

    public DBIngest() {
        importTool = new ImportTool();
        conf = new Configuration();

    }

    public int doImport(String jobName) throws ParseException, SqoopOptions.InvalidOptionsException {
        List<String> arg = Lists.<String>newLinkedList();

        //set MapReduce options example below
        arg.add("-D");
        arg.add("jobclient.completion.poll.interval=50");
        arg.add("-D");
        arg.add("jobclient.progress.monitor.poll.interval=50");
        //fill the needed parameters.
        //Set MapReduce Job Name

        SqoopOptions sqoopOptions = importTool.parseArguments(arg.toArray(new String[0]), conf, null, true);
        return importTool.run(sqoopOptions);
    }


}

class FileIngest {

}
