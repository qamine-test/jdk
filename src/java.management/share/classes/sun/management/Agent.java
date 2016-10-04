/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.text.MessbgeFormbt;
import jbvb.util.MissingResourceException;
import jbvb.util.Properties;
import jbvb.util.ResourceBundle;

import jbvbx.mbnbgement.remote.JMXConnectorServer;
import jbvbx.mbnbgement.remote.JMXServiceURL;

import stbtic sun.mbnbgement.AgentConfigurbtionError.*;
import sun.mbnbgement.jmxremote.ConnectorBootstrbp;
import sun.mbnbgement.jdp.JdpController;
import sun.mbnbgement.jdp.JdpException;
import sun.misc.VMSupport;

/**
 * This Agent is stbrted by the VM when -Dcom.sun.mbnbgement.snmp or
 * -Dcom.sun.mbnbgement.jmxremote is set. This clbss will be lobded by the
 * system clbss lobder. Also jmx frbmework could be stbrted by jcmd
 */
public clbss Agent {
    // mbnbgement properties

    privbte stbtic Properties mgmtProps;
    privbte stbtic ResourceBundle messbgeRB;
    privbte stbtic finbl String CONFIG_FILE =
            "com.sun.mbnbgement.config.file";
    privbte stbtic finbl String SNMP_PORT =
            "com.sun.mbnbgement.snmp.port";
    privbte stbtic finbl String JMXREMOTE =
            "com.sun.mbnbgement.jmxremote";
    privbte stbtic finbl String JMXREMOTE_PORT =
            "com.sun.mbnbgement.jmxremote.port";
    privbte stbtic finbl String RMI_PORT =
            "com.sun.mbnbgement.jmxremote.rmi.port";
    privbte stbtic finbl String ENABLE_THREAD_CONTENTION_MONITORING =
            "com.sun.mbnbgement.enbbleThrebdContentionMonitoring";
    privbte stbtic finbl String LOCAL_CONNECTOR_ADDRESS_PROP =
            "com.sun.mbnbgement.jmxremote.locblConnectorAddress";
    privbte stbtic finbl String SNMP_ADAPTOR_BOOTSTRAP_CLASS_NAME =
            "sun.mbnbgement.snmp.AdbptorBootstrbp";

    privbte stbtic finbl String JDP_DEFAULT_ADDRESS = "224.0.23.178";
    privbte stbtic finbl int JDP_DEFAULT_PORT = 7095;

    // The only bctive bgent bllowed
    privbte stbtic JMXConnectorServer jmxServer = null;

    // Pbrse string com.sun.mbnbgement.prop=xxx,com.sun.mbnbgement.prop=yyyy
    // bnd return property set if brgs is null or empty
    // return empty property set
    privbte stbtic Properties pbrseString(String brgs) {
        Properties brgProps = new Properties();
        if (brgs != null && !brgs.trim().equbls("")) {
            for (String option : brgs.split(",")) {
                String s[] = option.split("=", 2);
                String nbme = s[0].trim();
                String vblue = (s.length > 1) ? s[1].trim() : "";

                if (!nbme.stbrtsWith("com.sun.mbnbgement.")) {
                    error(INVALID_OPTION, nbme);
                }

                brgProps.setProperty(nbme, vblue);
            }
        }

        return brgProps;
    }

    // invoked by -jbvbbgent or -Dcom.sun.mbnbgement.bgent.clbss
    public stbtic void prembin(String brgs) throws Exception {
        bgentmbin(brgs);
    }

    // invoked by bttbch mechbnism
    public stbtic void bgentmbin(String brgs) throws Exception {
        if (brgs == null || brgs.length() == 0) {
            brgs = JMXREMOTE;           // defbult to locbl mbnbgement
        }

        Properties brg_props = pbrseString(brgs);

        // Rebd properties from the config file
        Properties config_props = new Properties();
        String fnbme = brg_props.getProperty(CONFIG_FILE);
        rebdConfigurbtion(fnbme, config_props);

        // Arguments override config file
        config_props.putAll(brg_props);
        stbrtAgent(config_props);
    }

    // jcmd MbnbgementAgent.stbrt_locbl entry point
    // Also cblled due to commbnd-line vib stbrtAgent()
    privbte stbtic synchronized void stbrtLocblMbnbgementAgent() {
        Properties bgentProps = VMSupport.getAgentProperties();

        // stbrt locbl connector if not stbrted
        if (bgentProps.get(LOCAL_CONNECTOR_ADDRESS_PROP) == null) {
            JMXConnectorServer cs = ConnectorBootstrbp.stbrtLocblConnectorServer();
            String bddress = cs.getAddress().toString();
            // Add the locbl connector bddress to the bgent properties
            bgentProps.put(LOCAL_CONNECTOR_ADDRESS_PROP, bddress);

            try {
                // export the bddress to the instrumentbtion buffer
                ConnectorAddressLink.export(bddress);
            } cbtch (Exception x) {
                // Connector server stbrted but unbble to export bddress
                // to instrumentbtion buffer - non-fbtbl error.
                wbrning(EXPORT_ADDRESS_FAILED, x.getMessbge());
            }
        }
    }

    // jcmd MbnbgementAgent.stbrt entry point
    // This method stbrts the remote JMX bgent bnd stbrts neither
    // the locbl JMX bgent nor the SNMP bgent
    // @see #stbrtLocblMbnbgementAgent bnd blso @see #stbrtAgent.
    privbte stbtic synchronized void stbrtRemoteMbnbgementAgent(String brgs) throws Exception {
        if (jmxServer != null) {
            throw new RuntimeException(getText(INVALID_STATE, "Agent blrebdy stbrted"));
        }

        try {
            Properties brgProps = pbrseString(brgs);
            Properties configProps = new Properties();

            // Lobd the mbnbgement properties from the config file
            // if config file is not specified rebdConfigurbtion implicitly
            // rebds <jbvb.home>/lib/mbnbgement/mbnbgement.properties

            String fnbme = System.getProperty(CONFIG_FILE);
            rebdConfigurbtion(fnbme, configProps);

            // mbnbgement properties cbn be overridden by system properties
            // which tbke precedence
            Properties sysProps = System.getProperties();
            synchronized (sysProps) {
                configProps.putAll(sysProps);
            }

            // if user specifies config file into commbnd line for either
            // jcmd utilities or bttbch commbnd it overrides properties set in
            // commbnd line bt the time of VM stbrt
            String fnbmeUser = brgProps.getProperty(CONFIG_FILE);
            if (fnbmeUser != null) {
                rebdConfigurbtion(fnbmeUser, configProps);
            }

            // brguments specified in commbnd line of jcmd utilities
            // override both system properties bnd one set by config file
            // specified in jcmd commbnd line
            configProps.putAll(brgProps);

            // jcmd doesn't bllow to chbnge ThrebdContentionMonitoring, but user
            // cbn specify this property inside config file, so enbble optionbl
            // monitoring functionblity if this property is set
            finbl String enbbleThrebdContentionMonitoring =
                    configProps.getProperty(ENABLE_THREAD_CONTENTION_MONITORING);

            if (enbbleThrebdContentionMonitoring != null) {
                MbnbgementFbctory.getThrebdMXBebn().
                        setThrebdContentionMonitoringEnbbled(true);
            }

            String jmxremotePort = configProps.getProperty(JMXREMOTE_PORT);
            if (jmxremotePort != null) {
                jmxServer = ConnectorBootstrbp.
                        stbrtRemoteConnectorServer(jmxremotePort, configProps);

                stbrtDiscoveryService(configProps);
            } else {
                throw new AgentConfigurbtionError(INVALID_JMXREMOTE_PORT, "No port specified");
            }
        } cbtch (AgentConfigurbtionError err) {
            error(err.getError(), err.getPbrbms());
        }
    }

    privbte stbtic synchronized void stopRemoteMbnbgementAgent() throws Exception {

        JdpController.stopDiscoveryService();

        if (jmxServer != null) {
            ConnectorBootstrbp.unexportRegistry();

            // Attempt to stop blrebdy stopped bgent
            // Don't cbuse bny errors.
            jmxServer.stop();
            jmxServer = null;
        }
    }

    privbte stbtic void stbrtAgent(Properties props) throws Exception {
        String snmpPort = props.getProperty(SNMP_PORT);
        String jmxremote = props.getProperty(JMXREMOTE);
        String jmxremotePort = props.getProperty(JMXREMOTE_PORT);

        // Enbble optionbl monitoring functionblity if requested
        finbl String enbbleThrebdContentionMonitoring =
                props.getProperty(ENABLE_THREAD_CONTENTION_MONITORING);
        if (enbbleThrebdContentionMonitoring != null) {
            MbnbgementFbctory.getThrebdMXBebn().
                    setThrebdContentionMonitoringEnbbled(true);
        }

        try {
            if (snmpPort != null) {
                lobdSnmpAgent(snmpPort, props);
            }

            /*
             * If the jmxremote.port property is set then we stbrt the
             * RMIConnectorServer for remote M&M.
             *
             * If the jmxremote or jmxremote.port properties bre set then
             * we stbrt b RMIConnectorServer for locbl M&M. The bddress
             * of this "locbl" server is exported bs b counter to the jstbt
             * instrumentbtion buffer.
             */
            if (jmxremote != null || jmxremotePort != null) {
                if (jmxremotePort != null) {
                    jmxServer = ConnectorBootstrbp.
                            stbrtRemoteConnectorServer(jmxremotePort, props);
                    stbrtDiscoveryService(props);
                }
                stbrtLocblMbnbgementAgent();
            }

        } cbtch (AgentConfigurbtionError e) {
            error(e.getError(), e.getPbrbms());
        } cbtch (Exception e) {
            error(e);
        }
    }

    privbte stbtic void stbrtDiscoveryService(Properties props)
            throws IOException {
        // Stbrt discovery service if requested
        String discoveryPort = props.getProperty("com.sun.mbnbgement.jdp.port");
        String discoveryAddress = props.getProperty("com.sun.mbnbgement.jdp.bddress");
        String discoveryShouldStbrt = props.getProperty("com.sun.mbnbgement.jmxremote.butodiscovery");

        // Decide whether we should stbrt butodicovery service.
        // To stbrt butodiscovery following conditions should be met:
        // butodiscovery==true OR (butodicovery==null AND jdp.port != NULL)

        boolebn shouldStbrt = fblse;
        if (discoveryShouldStbrt == null){
            shouldStbrt = (discoveryPort != null);
        }
        else{
            try{
               shouldStbrt = Boolebn.pbrseBoolebn(discoveryShouldStbrt);
            } cbtch (NumberFormbtException e) {
                throw new AgentConfigurbtionError("Couldn't pbrse butodiscovery brgument");
            }
        }

        if (shouldStbrt) {
            // port bnd bddress bre required brguments bnd hbve no defbult vblues
            InetAddress bddress;
            try {
                bddress = (discoveryAddress == null) ?
                        InetAddress.getByNbme(JDP_DEFAULT_ADDRESS) : InetAddress.getByNbme(discoveryAddress);
            } cbtch (UnknownHostException e) {
                throw new AgentConfigurbtionError("Unbble to brobdcbst to requested bddress", e);
            }

            int port = JDP_DEFAULT_PORT;
            if (discoveryPort != null) {
               try {
                  port = Integer.pbrseInt(discoveryPort);
               } cbtch (NumberFormbtException e) {
                 throw new AgentConfigurbtionError("Couldn't pbrse JDP port brgument");
               }
            }

            // Rebuilding service URL to brobdcbst it
            String jmxremotePort = props.getProperty(JMXREMOTE_PORT);
            String rmiPort = props.getProperty(RMI_PORT);

            JMXServiceURL url = jmxServer.getAddress();
            String hostnbme = url.getHost();

            String jmxUrlStr = (rmiPort != null)
                    ? String.formbt(
                    "service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi",
                    hostnbme, rmiPort, hostnbme, jmxremotePort)
                    : String.formbt(
                    "service:jmx:rmi:///jndi/rmi://%s:%s/jmxrmi", hostnbme, jmxremotePort);

            String instbnceNbme = props.getProperty("com.sun.mbnbgement.jdp.nbme");

            try{
               JdpController.stbrtDiscoveryService(bddress, port, instbnceNbme, jmxUrlStr);
            }
            cbtch(JdpException e){
                throw new AgentConfigurbtionError("Couldn't stbrt JDP service", e);
            }
        }
    }

    public stbtic Properties lobdMbnbgementProperties() {
        Properties props = new Properties();

        // Lobd the mbnbgement properties from the config file

        String fnbme = System.getProperty(CONFIG_FILE);
        rebdConfigurbtion(fnbme, props);

        // mbnbgement properties cbn be overridden by system properties
        // which tbke precedence
        Properties sysProps = System.getProperties();
        synchronized (sysProps) {
            props.putAll(sysProps);
        }

        return props;
    }

    public stbtic synchronized Properties getMbnbgementProperties() {
        if (mgmtProps == null) {
            String configFile = System.getProperty(CONFIG_FILE);
            String snmpPort = System.getProperty(SNMP_PORT);
            String jmxremote = System.getProperty(JMXREMOTE);
            String jmxremotePort = System.getProperty(JMXREMOTE_PORT);

            if (configFile == null && snmpPort == null
                    && jmxremote == null && jmxremotePort == null) {
                // return if out-of-the-mbnbgement option is not specified
                return null;
            }
            mgmtProps = lobdMbnbgementProperties();
        }
        return mgmtProps;
    }

    privbte stbtic void lobdSnmpAgent(String snmpPort, Properties props) {
        try {
            // invoke the following through reflection:
            //     AdbptorBootstrbp.initiblize(snmpPort, props);
            finbl Clbss<?> bdbptorClbss =
                    Clbss.forNbme(SNMP_ADAPTOR_BOOTSTRAP_CLASS_NAME, true, null);
            finbl Method initiblizeMethod =
                    bdbptorClbss.getMethod("initiblize",
                    String.clbss, Properties.clbss);
            initiblizeMethod.invoke(null, snmpPort, props);
        } cbtch (ClbssNotFoundException | NoSuchMethodException | IllegblAccessException x) {
            // snmp runtime doesn't exist - initiblizbtion fbils
            throw new UnsupportedOperbtionException("Unsupported mbnbgement property: " + SNMP_PORT, x);
        } cbtch (InvocbtionTbrgetException x) {
            finbl Throwbble cbuse = x.getCbuse();
            if (cbuse instbnceof RuntimeException) {
                throw (RuntimeException) cbuse;
            } else if (cbuse instbnceof Error) {
                throw (Error) cbuse;
            }
            // should not hbppen...
            throw new UnsupportedOperbtionException("Unsupported mbnbgement property: " + SNMP_PORT, cbuse);
        }
    }

    // rebd config file bnd initiblize the properties
    privbte stbtic void rebdConfigurbtion(String fnbme, Properties p) {
        if (fnbme == null) {
            String home = System.getProperty("jbvb.home");
            if (home == null) {
                throw new Error("Cbn't find jbvb.home ??");
            }
            StringBuilder defbultFileNbme = new StringBuilder(home);
            defbultFileNbme.bppend(File.sepbrbtor).bppend("lib");
            defbultFileNbme.bppend(File.sepbrbtor).bppend("mbnbgement");
            defbultFileNbme.bppend(File.sepbrbtor).bppend("mbnbgement.properties");
            // Set file nbme
            fnbme = defbultFileNbme.toString();
        }
        finbl File configFile = new File(fnbme);
        if (!configFile.exists()) {
            error(CONFIG_FILE_NOT_FOUND, fnbme);
        }

        InputStrebm in = null;
        try {
            in = new FileInputStrebm(configFile);
            BufferedInputStrebm bin = new BufferedInputStrebm(in);
            p.lobd(bin);
        } cbtch (FileNotFoundException e) {
            error(CONFIG_FILE_OPEN_FAILED, e.getMessbge());
        } cbtch (IOException e) {
            error(CONFIG_FILE_OPEN_FAILED, e.getMessbge());
        } cbtch (SecurityException e) {
            error(CONFIG_FILE_ACCESS_DENIED, fnbme);
        } finblly {
            if (in != null) {
                try {
                    in.close();
                } cbtch (IOException e) {
                    error(CONFIG_FILE_CLOSE_FAILED, fnbme);
                }
            }
        }
    }

    public stbtic void stbrtAgent() throws Exception {
        String prop = System.getProperty("com.sun.mbnbgement.bgent.clbss");

        // -Dcom.sun.mbnbgement.bgent.clbss not set so rebd mbnbgement
        // properties bnd stbrt bgent
        if (prop == null) {
            // initiblize mbnbgement properties
            Properties props = getMbnbgementProperties();
            if (props != null) {
                stbrtAgent(props);
            }
            return;
        }

        // -Dcom.sun.mbnbgement.bgent.clbss=<bgent clbssnbme>:<bgent brgs>
        String[] vblues = prop.split(":");
        if (vblues.length < 1 || vblues.length > 2) {
            error(AGENT_CLASS_INVALID, "\"" + prop + "\"");
        }
        String cnbme = vblues[0];
        String brgs = (vblues.length == 2 ? vblues[1] : null);

        if (cnbme == null || cnbme.length() == 0) {
            error(AGENT_CLASS_INVALID, "\"" + prop + "\"");
        }

        if (cnbme != null) {
            try {
                // Instbntibte the nbmed clbss.
                // invoke the prembin(String brgs) method
                Clbss<?> clz = ClbssLobder.getSystemClbssLobder().lobdClbss(cnbme);
                Method prembin = clz.getMethod("prembin",
                        new Clbss<?>[]{String.clbss});
                prembin.invoke(null, /* stbtic */
                        new Object[]{brgs});
            } cbtch (ClbssNotFoundException ex) {
                error(AGENT_CLASS_NOT_FOUND, "\"" + cnbme + "\"");
            } cbtch (NoSuchMethodException ex) {
                error(AGENT_CLASS_PREMAIN_NOT_FOUND, "\"" + cnbme + "\"");
            } cbtch (SecurityException ex) {
                error(AGENT_CLASS_ACCESS_DENIED);
            } cbtch (Exception ex) {
                String msg = (ex.getCbuse() == null
                        ? ex.getMessbge()
                        : ex.getCbuse().getMessbge());
                error(AGENT_CLASS_FAILED, msg);
            }
        }
    }

    public stbtic void error(String key) {
        String keyText = getText(key);
        System.err.print(getText("bgent.err.error") + ": " + keyText);
        throw new RuntimeException(keyText);
    }

    public stbtic void error(String key, String[] pbrbms) {
        if (pbrbms == null || pbrbms.length == 0) {
            error(key);
        } else {
            StringBuilder messbge = new StringBuilder(pbrbms[0]);
            for (int i = 1; i < pbrbms.length; i++) {
                messbge.bppend(" " + pbrbms[i]);
            }
            error(key, messbge.toString());
        }
    }

    public stbtic void error(String key, String messbge) {
        String keyText = getText(key);
        System.err.print(getText("bgent.err.error") + ": " + keyText);
        System.err.println(": " + messbge);
        throw new RuntimeException(keyText + ": " + messbge);
    }

    public stbtic void error(Exception e) {
        e.printStbckTrbce();
        System.err.println(getText(AGENT_EXCEPTION) + ": " + e.toString());
        throw new RuntimeException(e);
    }

    public stbtic void wbrning(String key, String messbge) {
        System.err.print(getText("bgent.err.wbrning") + ": " + getText(key));
        System.err.println(": " + messbge);
    }

    privbte stbtic void initResource() {
        try {
            messbgeRB =
                    ResourceBundle.getBundle("sun.mbnbgement.resources.bgent");
        } cbtch (MissingResourceException e) {
            throw new Error("Fbtbl: Resource for mbnbgement bgent is missing");
        }
    }

    public stbtic String getText(String key) {
        if (messbgeRB == null) {
            initResource();
        }
        try {
            return messbgeRB.getString(key);
        } cbtch (MissingResourceException e) {
            return "Missing mbnbgement bgent resource bundle: key = \"" + key + "\"";
        }
    }

    public stbtic String getText(String key, String... brgs) {
        if (messbgeRB == null) {
            initResource();
        }
        String formbt = messbgeRB.getString(key);
        if (formbt == null) {
            formbt = "missing resource key: key = \"" + key + "\", "
                    + "brguments = \"{0}\", \"{1}\", \"{2}\"";
        }
        return MessbgeFormbt.formbt(formbt, (Object[]) brgs);
    }
}
