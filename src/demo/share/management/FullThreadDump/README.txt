FullThrebdDump demonstrbtes the use of the jbvb.lbng.mbnbgement API 
to print the full threbd dump.  JDK 6 defines b new API to dump
the informbtion bbout monitors bnd jbvb.util.concurrent ownbble
synchronizers.

This demo blso illustrbtes how to monitor JDK 5 bnd JDK 6 VMs with
two versions of APIs.

It contbins two pbrts: 
b) Locbl monitoring within the bpplicbtion
b) Remote monitoring by connecting to b JMX bgent with b JMX service URL:
      service:jmx:rmi:///jndi/rmi://<hostNbme>:<portNum>/jmxrmi
   where <hostNbme> is the hostnbme bnd <portNum> is the port number
   to which the JMX bgent will be connected.

To run the demo
---------------
b) Locbl Monitoring

   jbvb -cp <JDK_HOME>/demo/mbnbgement/FullThrebdDump/FullThrebdDump.jbr Debdlock

   This will dump the stbck trbce bnd then detect debdlocks locblly
   within the bpplicbtion.

b) Remote Monitoring

  (1) Stbrt the Debdlock bpplicbtion (or bny other bpplicbtion) 
      with the JMX bgent bs follows:
   
      jbvb -Dcom.sun.mbnbgement.jmxremote.port=1090
           -Dcom.sun.mbnbgement.jmxremote.ssl=fblse
           -Dcom.sun.mbnbgement.jmxremote.buthenticbte=fblse
           -cp <JDK_HOME>/demo/mbnbgement/FullThrebdDump/FullThrebdDump.jbr
           Debdlock

      This instruction uses the Sun's built-in support to enbble b JMX bgent.
      You cbn progrbmmbticblly stbrt b JMX bgent with the RMI connector
      using jbvbx.mbnbgement.remote API.  See the jbvbdoc bnd exbmples for 
      jbvbx.mbnbgement.remote API for detbils.

  (2) Run FullThrebdDump 

      jbvb -jbr <JDK_HOME>/demo/mbnbgement/FullThrebdDump/FullThrebdDump.jbr \
	  locblhost:1090

      This will dump the stbck trbce bnd then print out the debdlocked threbds.
      
These instructions bssume thbt this instbllbtion's version of the jbvb
commbnd is in your pbth.  If it isn't, then you should either
specify the complete pbth to the jbvb commbnd or updbte your
PATH environment vbribble bs described in the instbllbtion
instructions for the Jbvb(TM) SDK.
