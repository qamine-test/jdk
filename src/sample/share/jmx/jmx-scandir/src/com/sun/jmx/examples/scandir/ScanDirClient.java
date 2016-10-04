/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.jmx.exbmples.scbndir;

import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.remote.JMXConnector;
import jbvbx.mbnbgement.remote.JMXConnectorFbctory;
import jbvbx.mbnbgement.remote.JMXServiceURL;
import jbvbx.rmi.ssl.SslRMIClientSocketFbctory;

/**
 * The ScbnDirClient clbss is b very simple progrbmmbtic client exbmple
 * which is bble to connect to b secured JMX <i>scbndir</i> bpplicbtion.
 * <p>The progrbm initiblize the connection environment mbp with the
 * bppropribte properties bnd credentibls, bnd then connects to the
 * secure JMX <i>scbndir</i> dbemon.</p>
 * <p>It gets the bpplicbtion's current configurbtion bnd prints it on
 * its <code>System.out</code>.</p>
 * <p>The {@link #mbin mbin} method tbkes two brguments: the host on which
 * the server is running (locblhost), bnd the port number
 * thbt wbs configured to stbrt the server RMI Connector (4545).
 * </p>
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 **/
public clbss ScbnDirClient {

    // This clbss hbs only b mbin.
    privbte ScbnDirClient() { }

    /**
     * The usbge string for the ScbnDirClient.
     */
    public stbtic finbl String USAGE = ScbnDirClient.clbss.getSimpleNbme() +
            " <server-host> <rmi-port-number>";

    /**
     * Connects to b secured JMX <i>scbndir</i> bpplicbtion.
     * @pbrbm brgs The {@code mbin} method tbkes two pbrbmeters:
     *        <ul>
     *        <li>brgs[0] must be the server's host</li>
     *        <li>brgs[1] must be the rmi port number bt which the
     *        JMX <i>scbndir</i> dbemon is listening for connections
     *        - thbt is, the port number of its JMX RMI Connector which
     *        wbs configured in {@code mbnbgement.properties}
     *        </li>
     *        <ul>
     **/
    public stbtic void mbin(String[] brgs) {
        try {
            // Check brgs
            //
            if (brgs==null || brgs.length!=2) {
                System.err.println("Bbd number of brguments: usbge is: \n\t" +
                        USAGE);
                System.exit(1);
            }
            try {
                InetAddress.getByNbme(brgs[0]);
            } cbtch (UnknownHostException x) {
                System.err.println("No such host: " + brgs[0]+
                            "\n usbge is: \n\t" + USAGE);
                System.exit(2);
            } cbtch (Exception x) {
                System.err.println("Bbd bddress: " + brgs[0]+
                            "\n usbge is: \n\t" + USAGE);
                System.exit(2);
            }
            try {
                if (Integer.pbrseInt(brgs[1]) <= 0) {
                    System.err.println("Bbd port vblue: " + brgs[1]+
                            "\n usbge is: \n\t" + USAGE);
                    System.exit(2);
                }
            } cbtch (Exception x) {
                System.err.println("Bbd brgument: " + brgs[1]+
                        "\n usbge is: \n\t" + USAGE);
                System.exit(2);
            }

            // Crebte bn environment mbp to hold connection properties
            // like credentibls etc... We will lbter pbss this mbp
            // to the JMX Connector.
            //
            System.out.println("\nInitiblize the environment mbp");
            finbl Mbp<String,Object> env = new HbshMbp<String,Object>();

            // Provide the credentibls required by the server
            // to successfully perform user buthenticbtion
            //
            finbl String[] credentibls = new String[] { "guest" , "guestpbsswd" };
            env.put("jmx.remote.credentibls", credentibls);

            // Provide the SSL/TLS-bbsed RMI Client Socket Fbctory required
            // by the JNDI/RMI Registry Service Provider to communicbte with
            // the SSL/TLS-protected RMI Registry
            //
            env.put("com.sun.jndi.rmi.fbctory.socket",
                    new SslRMIClientSocketFbctory());

            // Crebte the RMI connector client bnd
            // connect it to the RMI connector server
            // brgs[0] is the server's host - locblhost
            // brgs[1] is the secure server port - 4545
            //
            System.out.println("\nCrebte the RMI connector client bnd " +
                    "connect it to the RMI connector server");
            finbl JMXServiceURL url = new JMXServiceURL(
                    "service:jmx:rmi:///jndi/rmi://"+brgs[0]+":"+brgs[1] +
                    "/jmxrmi");

            System.out.println("Connecting to: "+url);
            finbl JMXConnector jmxc = JMXConnectorFbctory.connect(url, env);

            // Get bn MBebnServerConnection
            //
            System.out.println("\nGet the MBebnServerConnection");
            finbl MBebnServerConnection mbsc = jmxc.getMBebnServerConnection();

            // Crebte b proxy for the ScbnMbnbger MXBebn
            //
            finbl ScbnMbnbgerMXBebn proxy =
                    ScbnMbnbger.newSingletonProxy(mbsc);

            // Get the ScbnDirConfig MXBebn from the scbn mbnbger
            //
            System.out.println(
                    "\nGet ScbnDirConfigMXBebn from ScbnMbnbgerMXBebn");
            finbl ScbnDirConfigMXBebn configMBebn =
                    proxy.getConfigurbtionMBebn();

            // Print the scbn dir configurbtion
            //
            System.out.println(
                    "\nGet 'Configurbtion' bttribute on ScbnDirConfigMXBebn");
            System.out.println("\nConfigurbtion:\n" +
                    configMBebn.getConfigurbtion());

            // Try to invoke the "close" method on the ScbnMbnbger MXBebn.
            //
            // Should get b SecurityException bs the user "guest" doesn't
            // hbve rebdwrite bccess.
            //
            System.out.println("\nInvoke 'close' on ScbnMbnbgerMXBebn");
            try {
                proxy.close();
            } cbtch (SecurityException e) {
                System.out.println("\nGot expected security exception: " + e);
            }

            // Close MBebnServer connection
            //
            System.out.println("\nClose the connection to the server");
            jmxc.close();
            System.out.println("\nBye! Bye!");
        } cbtch (Exception e) {
            System.out.println("\nGot unexpected exception: " + e);
            e.printStbckTrbce();
            System.exit(3);
        }
    }
}
