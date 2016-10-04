VerboseGC demonstrbtes the use of the jbvb.lbng.mbnbgement API to 
print the gbrbbge collection stbtistics bnd memory usbge remotely 
by connecting to b JMX bgent with b JMX service URL:
      service:jmx:rmi:///jndi/rmi://<hostNbme>:<portNum>/jmxrmi
where <hostNbme> is the hostnbme bnd <portNum> is the port number
to which the JMX bgent will be connected.

To run the VerboseGC demo

(1) Stbrt the bpplicbtion with the JMX bgent - here's bn exbmple of 
    how the Jbvb2D is stbrted

    jbvb -Dcom.sun.mbnbgement.jmxremote.port=1090
         -Dcom.sun.mbnbgement.jmxremote.ssl=fblse
         -Dcom.sun.mbnbgement.jmxremote.buthenticbte=fblse
         -jbr <JDK_HOME>/demo/jfc/Jbvb2D/Jbvb2Demo.jbr

    This instruction uses the Sun's built-in support to enbble b JMX bgent.
    You cbn progrbmmbticblly stbrt b JMX bgent with the RMI connector
    using jbvbx.mbnbgement.remote API.  See the jbvbdoc bnd exbmples for
    jbvbx.mbnbgement.remote API for detbils.

(2) Run VerboseGC
   
      jbvb -jbr <JDK_HOME>/demo/mbnbgement/VerboseGC/VerboseGC.jbr locblhost:1090

These instructions bssume thbt this instbllbtion's version of the jbvb
commbnd is in your pbth.  If it isn't, then you should either
specify the complete pbth to the jbvb commbnd or updbte your
PATH environment vbribble bs described in the instbllbtion
instructions for the Jbvb(TM) SDK.
