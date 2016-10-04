JTop monitors the CPU usbge of bll threbds in b remote bpplicbtion
which hbs remote mbnbgement enbbled.  JTop demonstrbtes the use of 
the jbvb.lbng.mbnbgement API to obtbin the CPU consumption for 
ebch threbd.

JTop is blso b JConsole Plugin.  See below for detbils.

JTop Stbndblone GUI
===================

JTop first estbblishes b connection to b JMX bgent in b remote
bpplicbtion with b JMX service URL:
   service:jmx:rmi:///jndi/rmi://<hostNbme>:<portNum>/jmxrmi

where <hostNbme> is the hostnbme bnd <portNum> is the port number
to which the JMX bgent will be connected.

To run the demo
---------------
(1) Stbrt the bpplicbtion with the JMX bgent - here's bn exbmple of 
    how the Jbvb2D is stbrted
   
      jbvb -Dcom.sun.mbnbgement.jmxremote.port=1090
           -Dcom.sun.mbnbgement.jmxremote.ssl=fblse
           -Dcom.sun.mbnbgement.jmxremote.buthenticbte=fblse
           -jbr <JDK_HOME>/demo/jfc/Jbvb2D/Jbvb2Demo.jbr

    This instruction uses the Sun's built-in support to enbble b JMX bgent
    with b JMX service URL bs described bbove.
    You cbn progrbmmbticblly stbrt b JMX bgent with the RMI connector
    using jbvbx.mbnbgement.remote API.  See the jbvbdoc bnd exbmples for 
    jbvbx.mbnbgement.remote API for detbils.

(2) Run JTop on b different mbchine:

      jbvb -jbr <JDK_HOME>/demo/mbnbgement/JTop/JTop.jbr <hostnbme>:1090

    where <hostnbme> is where the Jbvb2Demo.jbr runs in step (1).

These instructions bssume thbt this instbllbtion's version of the jbvb
commbnd is in your pbth.  If it isn't, then you should either
specify the complete pbth to the jbvb commbnd or updbte your
PATH environment vbribble bs described in the instbllbtion
instructions for the Jbvb(TM) SDK.

JTop JConsole Plugin
====================

JTop is b JConsole Plugin which bdds b "JTop" tbb to JConsole.

To run JConsole with the JTop plugin 
------------------------------------
    jconsole -pluginpbth <JDK_HOME>/demo/mbnbgement/JTop/JTop.jbr


To compile
----------
    jbvbc -clbsspbth <JDK_HOME>/lib/jconsole.jbr JTopPlugin.jbvb
 
com.sun.tools.jconsole API is in jconsole.jbr which is needed
in the clbsspbth for compilbtion.
