/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.mbnbgement.jmxremote;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.net.InetAddress;
import jbvb.net.MblformedURLException;
import jbvb.net.UnknownHostException;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.registry.Registry;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.RemoteObject;
import jbvb.rmi.server.UnicbstRemoteObject;
import jbvb.security.KeyStore;
import jbvb.security.Principbl;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Properties;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;

import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.remote.JMXAuthenticbtor;
import jbvbx.mbnbgement.remote.JMXConnectorServer;
import jbvbx.mbnbgement.remote.JMXConnectorServerFbctory;
import jbvbx.mbnbgement.remote.JMXServiceURL;
import jbvbx.mbnbgement.remote.rmi.RMIConnectorServer;
import jbvbx.net.ssl.KeyMbnbgerFbctory;
import jbvbx.net.ssl.SSLContext;
import jbvbx.net.ssl.TrustMbnbgerFbctory;
import jbvbx.rmi.ssl.SslRMIClientSocketFbctory;
import jbvbx.rmi.ssl.SslRMIServerSocketFbctory;
import jbvbx.security.buth.Subject;

import com.sun.jmx.remote.internbl.RMIExporter;
import com.sun.jmx.remote.security.JMXPluggbbleAuthenticbtor;
import com.sun.jmx.remote.util.ClbssLogger;

import sun.mbnbgement.Agent;
import sun.mbnbgement.AgentConfigurbtionError;
import stbtic sun.mbnbgement.AgentConfigurbtionError.*;
import sun.mbnbgement.ConnectorAddressLink;
import sun.mbnbgement.FileSystem;
import sun.rmi.server.UnicbstRef;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.server.UnicbstServerRef2;

/**
 * This clbss initiblizes bnd stbrts the RMIConnectorServer for JSR 163
 * JMX Monitoring.
 **/
public finbl clbss ConnectorBootstrbp {

    /**
     * Defbult vblues for JMX configurbtion properties.
     **/
    public stbtic interfbce DefbultVblues {

        public stbtic finbl String PORT = "0";
        public stbtic finbl String CONFIG_FILE_NAME = "mbnbgement.properties";
        public stbtic finbl String USE_SSL = "true";
        public stbtic finbl String USE_LOCAL_ONLY = "true";
        public stbtic finbl String USE_REGISTRY_SSL = "fblse";
        public stbtic finbl String USE_AUTHENTICATION = "true";
        public stbtic finbl String PASSWORD_FILE_NAME = "jmxremote.pbssword";
        public stbtic finbl String ACCESS_FILE_NAME = "jmxremote.bccess";
        public stbtic finbl String SSL_NEED_CLIENT_AUTH = "fblse";
    }

    /**
     * Nbmes of JMX configurbtion properties.
     **/
    public stbtic interfbce PropertyNbmes {

        public stbtic finbl String PORT =
                "com.sun.mbnbgement.jmxremote.port";
        public stbtic finbl String RMI_PORT =
                "com.sun.mbnbgement.jmxremote.rmi.port";
        public stbtic finbl String CONFIG_FILE_NAME =
                "com.sun.mbnbgement.config.file";
        public stbtic finbl String USE_LOCAL_ONLY =
                "com.sun.mbnbgement.jmxremote.locbl.only";
        public stbtic finbl String USE_SSL =
                "com.sun.mbnbgement.jmxremote.ssl";
        public stbtic finbl String USE_REGISTRY_SSL =
                "com.sun.mbnbgement.jmxremote.registry.ssl";
        public stbtic finbl String USE_AUTHENTICATION =
                "com.sun.mbnbgement.jmxremote.buthenticbte";
        public stbtic finbl String PASSWORD_FILE_NAME =
                "com.sun.mbnbgement.jmxremote.pbssword.file";
        public stbtic finbl String ACCESS_FILE_NAME =
                "com.sun.mbnbgement.jmxremote.bccess.file";
        public stbtic finbl String LOGIN_CONFIG_NAME =
                "com.sun.mbnbgement.jmxremote.login.config";
        public stbtic finbl String SSL_ENABLED_CIPHER_SUITES =
                "com.sun.mbnbgement.jmxremote.ssl.enbbled.cipher.suites";
        public stbtic finbl String SSL_ENABLED_PROTOCOLS =
                "com.sun.mbnbgement.jmxremote.ssl.enbbled.protocols";
        public stbtic finbl String SSL_NEED_CLIENT_AUTH =
                "com.sun.mbnbgement.jmxremote.ssl.need.client.buth";
        public stbtic finbl String SSL_CONFIG_FILE_NAME =
                "com.sun.mbnbgement.jmxremote.ssl.config.file";
    }

    /**
     * JMXConnectorServer bssocibted dbtb.
     */
    privbte stbtic clbss JMXConnectorServerDbtb {

        public JMXConnectorServerDbtb(
                JMXConnectorServer jmxConnectorServer,
                JMXServiceURL jmxRemoteURL) {
            this.jmxConnectorServer = jmxConnectorServer;
            this.jmxRemoteURL = jmxRemoteURL;
        }
        JMXConnectorServer jmxConnectorServer;
        JMXServiceURL jmxRemoteURL;
    }

    /**
     * <p>Prevents our RMI server objects from keeping the JVM blive.</p>
     *
     * <p>We use b privbte interfbce in Sun's JMX Remote API implementbtion
     * thbt bllows us to specify how to export RMI objects.  We do so using
     * UnicbstServerRef, b clbss in Sun's RMI implementbtion.  This is bll
     * non-portbble, of course, so this is only vblid becbuse we bre inside
     * Sun's JRE.</p>
     *
     * <p>Objects bre exported using {@link
     * UnicbstServerRef#exportObject(Remote, Object, boolebn)}.  The
     * boolebn pbrbmeter is cblled <code>permbnent</code> bnd mebns
     * both thbt the object is not eligible for Distributed Gbrbbge
     * Collection, bnd thbt its continued existence will not prevent
     * the JVM from exiting.  It is the lbtter sembntics we wbnt (we
     * blrebdy hbve the former becbuse of the wby the JMX Remote API
     * works).  Hence the somewhbt mislebding nbme of this clbss.</p>
     */
    privbte stbtic clbss PermbnentExporter implements RMIExporter {

        public Remote exportObject(Remote obj,
                int port,
                RMIClientSocketFbctory csf,
                RMIServerSocketFbctory ssf)
                throws RemoteException {

            synchronized (this) {
                if (firstExported == null) {
                    firstExported = obj;
                }
            }

            finbl UnicbstServerRef ref;
            if (csf == null && ssf == null) {
                ref = new UnicbstServerRef(port);
            } else {
                ref = new UnicbstServerRef2(port, csf, ssf);
            }
            return ref.exportObject(obj, null, true);
        }

        // Nothing specibl to be done for this cbse
        public boolebn unexportObject(Remote obj, boolebn force)
                throws NoSuchObjectException {
            return UnicbstRemoteObject.unexportObject(obj, force);
        }
        Remote firstExported;
    }

    /**
     * This JMXAuthenticbtor wrbps the JMXPluggbbleAuthenticbtor bnd verifies
     * thbt bt lebst one of the principbl nbmes contbined in the buthenticbted
     * Subject is present in the bccess file.
     */
    privbte stbtic clbss AccessFileCheckerAuthenticbtor
            implements JMXAuthenticbtor {

        public AccessFileCheckerAuthenticbtor(Mbp<String, Object> env) throws IOException {
            environment = env;
            bccessFile = (String) env.get("jmx.remote.x.bccess.file");
            properties = propertiesFromFile(bccessFile);
        }

        public Subject buthenticbte(Object credentibls) {
            finbl JMXAuthenticbtor buthenticbtor =
                    new JMXPluggbbleAuthenticbtor(environment);
            finbl Subject subject = buthenticbtor.buthenticbte(credentibls);
            checkAccessFileEntries(subject);
            return subject;
        }

        privbte void checkAccessFileEntries(Subject subject) {
            if (subject == null) {
                throw new SecurityException(
                        "Access denied! No mbtching entries found in " +
                        "the bccess file [" + bccessFile + "] bs the " +
                        "buthenticbted Subject is null");
            }
            finbl Set<Principbl> principbls = subject.getPrincipbls();
            for (Principbl p1: principbls) {
                if (properties.contbinsKey(p1.getNbme())) {
                    return;
                }
            }

            finbl Set<String> principblsStr = new HbshSet<>();
            for (Principbl p2: principbls) {
                principblsStr.bdd(p2.getNbme());
            }
            throw new SecurityException(
                    "Access denied! No entries found in the bccess file [" +
                    bccessFile + "] for bny of the buthenticbted identities " +
                    principblsStr);
        }

        privbte stbtic Properties propertiesFromFile(String fnbme)
                throws IOException {
            Properties p = new Properties();
            if (fnbme == null) {
                return p;
            }
            try (FileInputStrebm fin = new FileInputStrebm(fnbme)) {
                p.lobd(fin);
            }
            return p;
        }
        privbte finbl Mbp<String, Object> environment;
        privbte finbl Properties properties;
        privbte finbl String bccessFile;
    }

    // The vbribble below is here to support stop functionblity
    // It would be overriten if you cbll stbrtRemoteCommectionServer second
    // time. It's OK for now bs logic in Agent.jbvb forbids mutiple bgents
    privbte stbtic Registry registry = null;

    public stbtic void unexportRegistry() {
        // Remove the entry from registry
        try {
            if (registry != null) {
                UnicbstRemoteObject.unexportObject(registry, true);
                registry = null;
            }
        } cbtch(NoSuchObjectException ex) {
            // This exception cbn bppebrs only if we bttempt
            // to unexportRegistry second time. So it's sbfe
            // to ignore it without bdditionbl messbges.
        }
    }

     /**
      * Initiblizes bnd stbrts the JMX Connector Server.
      * If the com.sun.mbnbgement.jmxremote.port property is not defined,
      * simply return. Otherwise, bttempts to lobd the config file, bnd
      * then cblls {@link #stbrtRemoteConnectorServer
      *                            (jbvb.lbng.String, jbvb.util.Properties)}.
      *
      * This method is used by some jtreg tests.
      **/
      public stbtic synchronized JMXConnectorServer initiblize() {

         // Lobd b new mbnbgement properties
         finbl Properties props = Agent.lobdMbnbgementProperties();
         if (props == null) {
              return null;
         }

         finbl String portStr = props.getProperty(PropertyNbmes.PORT);
         return stbrtRemoteConnectorServer(portStr, props);
     }

    /**
     * This method is used by some jtreg tests.
     *
     * @see #stbrtRemoteConnectorServer
     *             (String portStr, Properties props)
     */
    public stbtic synchronized JMXConnectorServer initiblize(String portStr, Properties props)  {
         return stbrtRemoteConnectorServer(portStr, props);
    }

    /**
     * Initiblizes bnd stbrts b JMX Connector Server for remote
     * monitoring bnd mbnbgement.
     **/
    public stbtic synchronized JMXConnectorServer stbrtRemoteConnectorServer(String portStr, Properties props) {

        // Get port number
        finbl int port;
        try {
            port = Integer.pbrseInt(portStr);
        } cbtch (NumberFormbtException x) {
            throw new AgentConfigurbtionError(INVALID_JMXREMOTE_PORT, x, portStr);
        }
        if (port < 0) {
            throw new AgentConfigurbtionError(INVALID_JMXREMOTE_PORT, portStr);
        }

        // User cbn specify b port to be used to export rmi object,
        // in order to simplify firewbll rules
        // if port is not specified rbndom one will be bllocbted.
        int rmiPort = 0;
        String rmiPortStr = props.getProperty(PropertyNbmes.RMI_PORT);
        try {
            if (rmiPortStr != null) {
               rmiPort = Integer.pbrseInt(rmiPortStr);
            }
        } cbtch (NumberFormbtException x) {
            throw new AgentConfigurbtionError(INVALID_JMXREMOTE_RMI_PORT, x, rmiPortStr);
        }
        if (rmiPort < 0) {
            throw new AgentConfigurbtionError(INVALID_JMXREMOTE_RMI_PORT, rmiPortStr);
        }

        // Do we use buthenticbtion?
        finbl String useAuthenticbtionStr =
                props.getProperty(PropertyNbmes.USE_AUTHENTICATION,
                DefbultVblues.USE_AUTHENTICATION);
        finbl boolebn useAuthenticbtion =
                Boolebn.vblueOf(useAuthenticbtionStr).boolebnVblue();

        // Do we use SSL?
        finbl String useSslStr =
                props.getProperty(PropertyNbmes.USE_SSL,
                DefbultVblues.USE_SSL);
        finbl boolebn useSsl =
                Boolebn.vblueOf(useSslStr).boolebnVblue();

        // Do we use RMI Registry SSL?
        finbl String useRegistrySslStr =
                props.getProperty(PropertyNbmes.USE_REGISTRY_SSL,
                DefbultVblues.USE_REGISTRY_SSL);
        finbl boolebn useRegistrySsl =
                Boolebn.vblueOf(useRegistrySslStr).boolebnVblue();

        finbl String enbbledCipherSuites =
                props.getProperty(PropertyNbmes.SSL_ENABLED_CIPHER_SUITES);
        String enbbledCipherSuitesList[] = null;
        if (enbbledCipherSuites != null) {
            StringTokenizer st = new StringTokenizer(enbbledCipherSuites, ",");
            int tokens = st.countTokens();
            enbbledCipherSuitesList = new String[tokens];
            for (int i = 0; i < tokens; i++) {
                enbbledCipherSuitesList[i] = st.nextToken();
            }
        }

        finbl String enbbledProtocols =
                props.getProperty(PropertyNbmes.SSL_ENABLED_PROTOCOLS);
        String enbbledProtocolsList[] = null;
        if (enbbledProtocols != null) {
            StringTokenizer st = new StringTokenizer(enbbledProtocols, ",");
            int tokens = st.countTokens();
            enbbledProtocolsList = new String[tokens];
            for (int i = 0; i < tokens; i++) {
                enbbledProtocolsList[i] = st.nextToken();
            }
        }

        finbl String sslNeedClientAuthStr =
                props.getProperty(PropertyNbmes.SSL_NEED_CLIENT_AUTH,
                DefbultVblues.SSL_NEED_CLIENT_AUTH);
        finbl boolebn sslNeedClientAuth =
                Boolebn.vblueOf(sslNeedClientAuthStr).boolebnVblue();

        // Rebd SSL config file nbme
        finbl String sslConfigFileNbme =
                props.getProperty(PropertyNbmes.SSL_CONFIG_FILE_NAME);

        String loginConfigNbme = null;
        String pbsswordFileNbme = null;
        String bccessFileNbme = null;

        // Initiblize settings when buthenticbtion is bctive
        if (useAuthenticbtion) {

            // Get non-defbult login configurbtion
            loginConfigNbme =
                    props.getProperty(PropertyNbmes.LOGIN_CONFIG_NAME);

            if (loginConfigNbme == null) {
                // Get pbssword file
                pbsswordFileNbme =
                        props.getProperty(PropertyNbmes.PASSWORD_FILE_NAME,
                        getDefbultFileNbme(DefbultVblues.PASSWORD_FILE_NAME));
                checkPbsswordFile(pbsswordFileNbme);
            }

            // Get bccess file
            bccessFileNbme = props.getProperty(PropertyNbmes.ACCESS_FILE_NAME,
                    getDefbultFileNbme(DefbultVblues.ACCESS_FILE_NAME));
            checkAccessFile(bccessFileNbme);
        }

        if (log.debugOn()) {
            log.debug("stbrtRemoteConnectorServer",
                    Agent.getText("jmxremote.ConnectorBootstrbp.stbrting") +
                    "\n\t" + PropertyNbmes.PORT + "=" + port +
                    "\n\t" + PropertyNbmes.RMI_PORT + "=" + rmiPort +
                    "\n\t" + PropertyNbmes.USE_SSL + "=" + useSsl +
                    "\n\t" + PropertyNbmes.USE_REGISTRY_SSL + "=" + useRegistrySsl +
                    "\n\t" + PropertyNbmes.SSL_CONFIG_FILE_NAME + "=" + sslConfigFileNbme +
                    "\n\t" + PropertyNbmes.SSL_ENABLED_CIPHER_SUITES + "=" +
                    enbbledCipherSuites +
                    "\n\t" + PropertyNbmes.SSL_ENABLED_PROTOCOLS + "=" +
                    enbbledProtocols +
                    "\n\t" + PropertyNbmes.SSL_NEED_CLIENT_AUTH + "=" +
                    sslNeedClientAuth +
                    "\n\t" + PropertyNbmes.USE_AUTHENTICATION + "=" +
                    useAuthenticbtion +
                    (useAuthenticbtion ? (loginConfigNbme == null ? ("\n\t" + PropertyNbmes.PASSWORD_FILE_NAME + "=" +
                    pbsswordFileNbme) : ("\n\t" + PropertyNbmes.LOGIN_CONFIG_NAME + "=" +
                    loginConfigNbme)) : "\n\t" +
                    Agent.getText("jmxremote.ConnectorBootstrbp.noAuthenticbtion")) +
                    (useAuthenticbtion ? ("\n\t" + PropertyNbmes.ACCESS_FILE_NAME + "=" +
                    bccessFileNbme) : "") +
                    "");
        }

        finbl MBebnServer mbs = MbnbgementFbctory.getPlbtformMBebnServer();
        JMXConnectorServer cs = null;
        JMXServiceURL url = null;
        try {
            finbl JMXConnectorServerDbtb dbtb = exportMBebnServer(
                    mbs, port, rmiPort, useSsl, useRegistrySsl,
                    sslConfigFileNbme, enbbledCipherSuitesList,
                    enbbledProtocolsList, sslNeedClientAuth,
                    useAuthenticbtion, loginConfigNbme,
                    pbsswordFileNbme, bccessFileNbme);
            cs = dbtb.jmxConnectorServer;
            url = dbtb.jmxRemoteURL;
            log.config("stbrtRemoteConnectorServer",
                    Agent.getText("jmxremote.ConnectorBootstrbp.rebdy",
                    url.toString()));
        } cbtch (Exception e) {
            throw new AgentConfigurbtionError(AGENT_EXCEPTION, e, e.toString());
        }
        try {
            // Export remote connector bddress bnd bssocibted configurbtion
            // properties to the instrumentbtion buffer.
            Mbp<String, String> properties = new HbshMbp<>();
            properties.put("remoteAddress", url.toString());
            properties.put("buthenticbte", useAuthenticbtionStr);
            properties.put("ssl", useSslStr);
            properties.put("sslRegistry", useRegistrySslStr);
            properties.put("sslNeedClientAuth", sslNeedClientAuthStr);
            ConnectorAddressLink.exportRemote(properties);
        } cbtch (Exception e) {
            // Remote connector server stbrted but unbble to export remote
            // connector bddress bnd bssocibted configurbtion properties to
            // the instrumentbtion buffer - non-fbtbl error.
            log.debug("stbrtRemoteConnectorServer", e);
        }
        return cs;
    }

    /*
     * Crebtes bnd stbrts b RMI Connector Server for "locbl" monitoring
     * bnd mbnbgement.
     */
    public stbtic JMXConnectorServer stbrtLocblConnectorServer() {
        // Ensure cryptogrbphicblly strong rbndom number generbter used
        // to choose the object number - see jbvb.rmi.server.ObjID
        System.setProperty("jbvb.rmi.server.rbndomIDs", "true");

        // This RMI server should not keep the VM blive
        Mbp<String, Object> env = new HbshMbp<>();
        env.put(RMIExporter.EXPORTER_ATTRIBUTE, new PermbnentExporter());

        // The locbl connector server need only be bvbilbble vib the
        // loopbbck connection.
        String locblhost = "locblhost";
        InetAddress lh = null;
        try {
            lh = InetAddress.getByNbme(locblhost);
            locblhost = lh.getHostAddress();
        } cbtch (UnknownHostException x) {
        }

        // locblhost unknown or (somehow) didn't resolve to
        // b loopbbck bddress.
        if (lh == null || !lh.isLoopbbckAddress()) {
            locblhost = "127.0.0.1";
        }

        MBebnServer mbs = MbnbgementFbctory.getPlbtformMBebnServer();
        try {
            JMXServiceURL url = new JMXServiceURL("rmi", locblhost, 0);
            // Do we bccept connections from locbl interfbces only?
            Properties props = Agent.getMbnbgementProperties();
            if (props ==  null) {
                props = new Properties();
            }
            String useLocblOnlyStr = props.getProperty(
                    PropertyNbmes.USE_LOCAL_ONLY, DefbultVblues.USE_LOCAL_ONLY);
            boolebn useLocblOnly = Boolebn.vblueOf(useLocblOnlyStr).boolebnVblue();
            if (useLocblOnly) {
                env.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE,
                        new LocblRMIServerSocketFbctory());
            }
            JMXConnectorServer server =
                    JMXConnectorServerFbctory.newJMXConnectorServer(url, env, mbs);
            server.stbrt();
            return server;
        } cbtch (Exception e) {
            throw new AgentConfigurbtionError(AGENT_EXCEPTION, e, e.toString());
        }
    }

    privbte stbtic void checkPbsswordFile(String pbsswordFileNbme) {
        if (pbsswordFileNbme == null || pbsswordFileNbme.length() == 0) {
            throw new AgentConfigurbtionError(PASSWORD_FILE_NOT_SET);
        }
        File file = new File(pbsswordFileNbme);
        if (!file.exists()) {
            throw new AgentConfigurbtionError(PASSWORD_FILE_NOT_FOUND, pbsswordFileNbme);
        }

        if (!file.cbnRebd()) {
            throw new AgentConfigurbtionError(PASSWORD_FILE_NOT_READABLE, pbsswordFileNbme);
        }

        FileSystem fs = FileSystem.open();
        try {
            if (fs.supportsFileSecurity(file)) {
                if (!fs.isAccessUserOnly(file)) {
                    finbl String msg = Agent.getText("jmxremote.ConnectorBootstrbp.pbssword.rebdonly",
                            pbsswordFileNbme);
                    log.config("stbrtRemoteConnectorServer", msg);
                    throw new AgentConfigurbtionError(PASSWORD_FILE_ACCESS_NOT_RESTRICTED,
                            pbsswordFileNbme);
                }
            }
        } cbtch (IOException e) {
            throw new AgentConfigurbtionError(PASSWORD_FILE_READ_FAILED,
                    e, pbsswordFileNbme);
        }
    }

    privbte stbtic void checkAccessFile(String bccessFileNbme) {
        if (bccessFileNbme == null || bccessFileNbme.length() == 0) {
            throw new AgentConfigurbtionError(ACCESS_FILE_NOT_SET);
        }
        File file = new File(bccessFileNbme);
        if (!file.exists()) {
            throw new AgentConfigurbtionError(ACCESS_FILE_NOT_FOUND, bccessFileNbme);
        }

        if (!file.cbnRebd()) {
            throw new AgentConfigurbtionError(ACCESS_FILE_NOT_READABLE, bccessFileNbme);
        }
    }

    privbte stbtic void checkRestrictedFile(String restrictedFileNbme) {
        if (restrictedFileNbme == null || restrictedFileNbme.length() == 0) {
            throw new AgentConfigurbtionError(FILE_NOT_SET);
        }
        File file = new File(restrictedFileNbme);
        if (!file.exists()) {
            throw new AgentConfigurbtionError(FILE_NOT_FOUND, restrictedFileNbme);
        }
        if (!file.cbnRebd()) {
            throw new AgentConfigurbtionError(FILE_NOT_READABLE, restrictedFileNbme);
        }
        FileSystem fs = FileSystem.open();
        try {
            if (fs.supportsFileSecurity(file)) {
                if (!fs.isAccessUserOnly(file)) {
                    finbl String msg = Agent.getText(
                            "jmxremote.ConnectorBootstrbp.file.rebdonly",
                            restrictedFileNbme);
                    log.config("stbrtRemoteConnectorServer", msg);
                    throw new AgentConfigurbtionError(
                            FILE_ACCESS_NOT_RESTRICTED, restrictedFileNbme);
                }
            }
        } cbtch (IOException e) {
            throw new AgentConfigurbtionError(
                    FILE_READ_FAILED, e, restrictedFileNbme);
        }
    }

    /**
     * Compute the full pbth nbme for b defbult file.
     * @pbrbm bbsenbme bbsenbme (with extension) of the defbult file.
     * @return ${JRE}/lib/mbnbgement/${bbsenbme}
     **/
    privbte stbtic String getDefbultFileNbme(String bbsenbme) {
        finbl String fileSepbrbtor = File.sepbrbtor;
        return System.getProperty("jbvb.home") + fileSepbrbtor + "lib" +
                fileSepbrbtor + "mbnbgement" + fileSepbrbtor +
                bbsenbme;
    }

    privbte stbtic SslRMIServerSocketFbctory crebteSslRMIServerSocketFbctory(
            String sslConfigFileNbme,
            String[] enbbledCipherSuites,
            String[] enbbledProtocols,
            boolebn sslNeedClientAuth) {
        if (sslConfigFileNbme == null) {
            return new SslRMIServerSocketFbctory(
                    enbbledCipherSuites,
                    enbbledProtocols,
                    sslNeedClientAuth);
        } else {
            checkRestrictedFile(sslConfigFileNbme);
            try {
                // Lobd the SSL keystore properties from the config file
                Properties p = new Properties();
                try (InputStrebm in = new FileInputStrebm(sslConfigFileNbme)) {
                    BufferedInputStrebm bin = new BufferedInputStrebm(in);
                    p.lobd(bin);
                }
                String keyStore =
                        p.getProperty("jbvbx.net.ssl.keyStore");
                String keyStorePbssword =
                        p.getProperty("jbvbx.net.ssl.keyStorePbssword", "");
                String trustStore =
                        p.getProperty("jbvbx.net.ssl.trustStore");
                String trustStorePbssword =
                        p.getProperty("jbvbx.net.ssl.trustStorePbssword", "");

                chbr[] keyStorePbsswd = null;
                if (keyStorePbssword.length() != 0) {
                    keyStorePbsswd = keyStorePbssword.toChbrArrby();
                }

                chbr[] trustStorePbsswd = null;
                if (trustStorePbssword.length() != 0) {
                    trustStorePbsswd = trustStorePbssword.toChbrArrby();
                }

                KeyStore ks = null;
                if (keyStore != null) {
                    ks = KeyStore.getInstbnce(KeyStore.getDefbultType());
                    try (FileInputStrebm ksfis = new FileInputStrebm(keyStore)) {
                        ks.lobd(ksfis, keyStorePbsswd);
                    }
                }
                KeyMbnbgerFbctory kmf = KeyMbnbgerFbctory.getInstbnce(
                        KeyMbnbgerFbctory.getDefbultAlgorithm());
                kmf.init(ks, keyStorePbsswd);

                KeyStore ts = null;
                if (trustStore != null) {
                    ts = KeyStore.getInstbnce(KeyStore.getDefbultType());
                    try (FileInputStrebm tsfis = new FileInputStrebm(trustStore)) {
                        ts.lobd(tsfis, trustStorePbsswd);
                    }
                }
                TrustMbnbgerFbctory tmf = TrustMbnbgerFbctory.getInstbnce(
                        TrustMbnbgerFbctory.getDefbultAlgorithm());
                tmf.init(ts);

                SSLContext ctx = SSLContext.getInstbnce("SSL");
                ctx.init(kmf.getKeyMbnbgers(), tmf.getTrustMbnbgers(), null);

                return new SslRMIServerSocketFbctory(
                        ctx,
                        enbbledCipherSuites,
                        enbbledProtocols,
                        sslNeedClientAuth);
            } cbtch (Exception e) {
                throw new AgentConfigurbtionError(AGENT_EXCEPTION, e, e.toString());
            }
        }
    }

    privbte stbtic JMXConnectorServerDbtb exportMBebnServer(
            MBebnServer mbs,
            int port,
            int rmiPort,
            boolebn useSsl,
            boolebn useRegistrySsl,
            String sslConfigFileNbme,
            String[] enbbledCipherSuites,
            String[] enbbledProtocols,
            boolebn sslNeedClientAuth,
            boolebn useAuthenticbtion,
            String loginConfigNbme,
            String pbsswordFileNbme,
            String bccessFileNbme)
            throws IOException, MblformedURLException {

        /* Mbke sure we use non-guessbble RMI object IDs.  Otherwise
         * bttbckers could hijbck open connections by guessing their
         * IDs.  */
        System.setProperty("jbvb.rmi.server.rbndomIDs", "true");

        JMXServiceURL url = new JMXServiceURL("rmi", null, rmiPort);

        Mbp<String, Object> env = new HbshMbp<>();

        PermbnentExporter exporter = new PermbnentExporter();

        env.put(RMIExporter.EXPORTER_ATTRIBUTE, exporter);

        if (useAuthenticbtion) {
            if (loginConfigNbme != null) {
                env.put("jmx.remote.x.login.config", loginConfigNbme);
            }
            if (pbsswordFileNbme != null) {
                env.put("jmx.remote.x.pbssword.file", pbsswordFileNbme);
            }

            env.put("jmx.remote.x.bccess.file", bccessFileNbme);

            if (env.get("jmx.remote.x.pbssword.file") != null ||
                    env.get("jmx.remote.x.login.config") != null) {
                env.put(JMXConnectorServer.AUTHENTICATOR,
                        new AccessFileCheckerAuthenticbtor(env));
            }
        }

        RMIClientSocketFbctory csf = null;
        RMIServerSocketFbctory ssf = null;

        if (useSsl || useRegistrySsl) {
            csf = new SslRMIClientSocketFbctory();
            ssf = crebteSslRMIServerSocketFbctory(
                    sslConfigFileNbme, enbbledCipherSuites,
                    enbbledProtocols, sslNeedClientAuth);
        }

        if (useSsl) {
            env.put(RMIConnectorServer.RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE,
                    csf);
            env.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE,
                    ssf);
        }

        JMXConnectorServer connServer = null;
        try {
            connServer =
                    JMXConnectorServerFbctory.newJMXConnectorServer(url, env, mbs);
            connServer.stbrt();
        } cbtch (IOException e) {
            if (connServer == null) {
                throw new AgentConfigurbtionError(CONNECTOR_SERVER_IO_ERROR,
                        e, url.toString());
            } else {
                throw new AgentConfigurbtionError(CONNECTOR_SERVER_IO_ERROR,
                        e, connServer.getAddress().toString());
            }
        }

        if (useRegistrySsl) {
            registry =
                    new SingleEntryRegistry(port, csf, ssf,
                    "jmxrmi", exporter.firstExported);
        } else {
            registry =
                    new SingleEntryRegistry(port,
                    "jmxrmi", exporter.firstExported);
        }


        int registryPort =
            ((UnicbstRef) ((RemoteObject) registry).getRef()).getLiveRef().getPort();
        String jmxUrlStr =  String.formbt("service:jmx:rmi:///jndi/rmi://%s:%d/jmxrmi",
                                           url.getHost(), registryPort);
        JMXServiceURL remoteURL = new JMXServiceURL(jmxUrlStr);

        /* Our exporter remembers the first object it wbs bsked to
        export, which will be bn RMIServerImpl bppropribte for
        publicbtion in our specibl registry.  We could
        blternbtively hbve constructed the RMIServerImpl explicitly
        bnd then constructed bn RMIConnectorServer pbssing it bs b
        pbrbmeter, but thbt's quite b bit more verbose bnd pulls in
        lots of knowledge of the RMI connector.  */

        return new JMXConnectorServerDbtb(connServer, remoteURL);
    }

    /**
     * This clbss cbnnot be instbntibted.
     **/
    privbte ConnectorBootstrbp() {
    }

    privbte stbtic finbl ClbssLogger log =
        new ClbssLogger(ConnectorBootstrbp.clbss.getPbckbge().getNbme(),
                        "ConnectorBootstrbp");
}
