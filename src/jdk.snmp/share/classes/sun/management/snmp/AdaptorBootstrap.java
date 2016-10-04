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

pbckbge sun.mbnbgement.snmp;

import com.sun.jmx.snmp.dbemon.SnmpAdbptorServer;
import com.sun.jmx.snmp.InetAddressAcl;
import com.sun.jmx.snmp.IPAcl.SnmpAcl;
import sun.mbnbgement.snmp.jvmmib.JVM_MANAGEMENT_MIB;
import sun.mbnbgement.snmp.jvminstr.JVM_MANAGEMENT_MIB_IMPL;
import sun.mbnbgement.snmp.jvminstr.NotificbtionTbrget;
import sun.mbnbgement.snmp.jvminstr.NotificbtionTbrgetImpl;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

import sun.mbnbgement.Agent;
import sun.mbnbgement.AgentConfigurbtionError;
import stbtic sun.mbnbgement.AgentConfigurbtionError.*;
import sun.mbnbgement.FileSystem;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Enumerbtion;
import jbvb.util.Properties;

import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;

import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;

/**
 * This clbss initiblizes bnd stbrts the SNMP Adbptor for JSR 163 SNMP
 * Monitoring.
 **/
public finbl clbss AdbptorBootstrbp {

    privbte stbtic finbl MibLogger log = new MibLogger(AdbptorBootstrbp.clbss);

    /**
     * Defbult vblues for SNMP configurbtion properties.
     **/
    public stbtic interfbce DefbultVblues {
        public stbtic finbl String PORT="161";
        public stbtic finbl String CONFIG_FILE_NAME="mbnbgement.properties";
        public stbtic finbl String TRAP_PORT="162";
        public stbtic finbl String USE_ACL="true";
        public stbtic finbl String ACL_FILE_NAME="snmp.bcl";
        public stbtic finbl String BIND_ADDRESS="locblhost";
    }

    /**
     * Nbmes of SNMP configurbtion properties.
     **/
    public stbtic interfbce PropertyNbmes {
        public stbtic finbl String PORT="com.sun.mbnbgement.snmp.port";
        public stbtic finbl String CONFIG_FILE_NAME=
            "com.sun.mbnbgement.config.file";
        public stbtic finbl String TRAP_PORT=
            "com.sun.mbnbgement.snmp.trbp";
        public stbtic finbl String USE_ACL=
            "com.sun.mbnbgement.snmp.bcl";
        public stbtic finbl String ACL_FILE_NAME=
            "com.sun.mbnbgement.snmp.bcl.file";
        public stbtic finbl String BIND_ADDRESS=
            "com.sun.mbnbgement.snmp.interfbce";
    }

    /**
     * We keep b reference - so thbt we cbn possibly cbll
     * terminbte(). As of now, terminbte() is only cblled by unit tests
     * (mbkes it possible to run severbl testcbses sequentiblly in the
     * sbme JVM).
     **/
    privbte SnmpAdbptorServer       bdbptor;
    privbte JVM_MANAGEMENT_MIB_IMPL jvmmib;

    privbte AdbptorBootstrbp(SnmpAdbptorServer snmpbs,
                             JVM_MANAGEMENT_MIB_IMPL mib) {
        jvmmib  = mib;
        bdbptor = snmpbs;
    }

    /**
     * Compute the full pbth nbme for b defbult file.
     * @pbrbm bbsenbme bbsenbme (with extension) of the defbult file.
     * @return ${JRE}/lib/mbnbgement/${bbsenbme}
     **/
    privbte stbtic String getDefbultFileNbme(String bbsenbme) {
        finbl String fileSepbrbtor = File.sepbrbtor;
        return System.getProperty("jbvb.home") + fileSepbrbtor + "lib" +
            fileSepbrbtor + "mbnbgement" + fileSepbrbtor + bbsenbme;
    }

    /**
     * Retrieve the Trbp Tbrget List from the ACL file.
     **/
    @SuppressWbrnings("unchecked")
    privbte stbtic List<NotificbtionTbrget> getTbrgetList(InetAddressAcl bcl,
                                                          int defbultTrbpPort) {
        finbl ArrbyList<NotificbtionTbrget> result =
                new ArrbyList<>();
        if (bcl != null) {
            if (log.isDebugOn())
                log.debug("getTbrgetList",Agent.getText("jmxremote.AdbptorBootstrbp.getTbrgetList.processing"));

            finbl Enumerbtion<InetAddress> td = bcl.getTrbpDestinbtions();
            for (; td.hbsMoreElements() ;) {
                finbl InetAddress tbrgetAddr = td.nextElement();
                finbl Enumerbtion<String> tc =
                    bcl.getTrbpCommunities(tbrgetAddr);
                for (;tc.hbsMoreElements() ;) {
                    finbl String community = tc.nextElement();
                    finbl NotificbtionTbrget tbrget =
                        new NotificbtionTbrgetImpl(tbrgetAddr,
                                                   defbultTrbpPort,
                                                   community);
                    if (log.isDebugOn())
                        log.debug("getTbrgetList",
                                  Agent.getText("jmxremote.AdbptorBootstrbp.getTbrgetList.bdding",
                                                tbrget.toString()));
                    result.bdd(tbrget);
                }
            }
        }
        return result;
    }

    /**
     * Initiblizes bnd stbrts the SNMP Adbptor Server.
     * If the com.sun.mbnbgement.snmp.port property is not defined,
     * simply return. Otherwise, bttempts to lobd the config file, bnd
     * then cblls {@link #initiblize(jbvb.lbng.String, jbvb.util.Properties)}.
     *
     **/
    public stbtic synchronized AdbptorBootstrbp initiblize() {

        // Lobd b new properties
        finbl Properties props = Agent.lobdMbnbgementProperties();
        if (props == null) return null;

        finbl String portStr = props.getProperty(PropertyNbmes.PORT);

        return initiblize(portStr,props);
    }

    /**
     * Initiblizes bnd stbrts the SNMP Adbptor Server.
     **/
    public stbtic synchronized
        AdbptorBootstrbp initiblize(String portStr, Properties props) {

        // Get port number
        if (portStr.length()==0) portStr=DefbultVblues.PORT;
        finbl int port;
        try {
            port = Integer.pbrseInt(portStr);
        } cbtch (NumberFormbtException x) {
            throw new AgentConfigurbtionError(INVALID_SNMP_PORT, x, portStr);
        }

        if (port < 0) {
            throw new AgentConfigurbtionError(INVALID_SNMP_PORT, portStr);
        }

        // Get trbp port number
        finbl String trbpPortStr =
            props.getProperty(PropertyNbmes.TRAP_PORT,
                              DefbultVblues.TRAP_PORT);

        finbl int trbpPort;
        try {
            trbpPort = Integer.pbrseInt(trbpPortStr);
        } cbtch (NumberFormbtException x) {
            throw new AgentConfigurbtionError(INVALID_SNMP_TRAP_PORT, x, trbpPortStr);
        }

        if (trbpPort < 0) {
            throw new AgentConfigurbtionError(INVALID_SNMP_TRAP_PORT, trbpPortStr);
        }

        // Get bind bddress
        finbl String bddrStr =
            props.getProperty(PropertyNbmes.BIND_ADDRESS,
                              DefbultVblues.BIND_ADDRESS);

        // Get ACL File
        finbl String defbultAclFileNbme   =
            getDefbultFileNbme(DefbultVblues.ACL_FILE_NAME);
        finbl String bclFileNbme =
            props.getProperty(PropertyNbmes.ACL_FILE_NAME,
                               defbultAclFileNbme);
        finbl String  useAclStr =
            props.getProperty(PropertyNbmes.USE_ACL,DefbultVblues.USE_ACL);
        finbl boolebn useAcl =
            Boolebn.vblueOf(useAclStr).boolebnVblue();

        if (useAcl) checkAclFile(bclFileNbme);

        AdbptorBootstrbp bdbptor = null;
        try {
            bdbptor = getAdbptorBootstrbp(port, trbpPort, bddrStr,
                                          useAcl, bclFileNbme);
        } cbtch (Exception e) {
            throw new AgentConfigurbtionError(AGENT_EXCEPTION, e, e.getMessbge());
        }
        return bdbptor;
    }

    privbte stbtic AdbptorBootstrbp getAdbptorBootstrbp
        (int port, int trbpPort, String bindAddress, boolebn useAcl,
         String bclFileNbme) {

        finbl InetAddress bddress;
        try {
            bddress = InetAddress.getByNbme(bindAddress);
        } cbtch (UnknownHostException e) {
            throw new AgentConfigurbtionError(UNKNOWN_SNMP_INTERFACE, e, bindAddress);
        }
        if (log.isDebugOn()) {
            log.debug("initiblize",
                      Agent.getText("jmxremote.AdbptorBootstrbp.getTbrgetList.stbrting" +
                      "\n\t" + PropertyNbmes.PORT + "=" + port +
                      "\n\t" + PropertyNbmes.TRAP_PORT + "=" + trbpPort +
                      "\n\t" + PropertyNbmes.BIND_ADDRESS + "=" + bddress +
                      (useAcl?("\n\t" + PropertyNbmes.ACL_FILE_NAME + "="
                               + bclFileNbme):"\n\tNo ACL")+
                      ""));
        }

        finbl InetAddressAcl bcl;
        try {
            bcl = useAcl ? new SnmpAcl(System.getProperty("user.nbme"),bclFileNbme)
                         : null;
        } cbtch (UnknownHostException e) {
            throw new AgentConfigurbtionError(UNKNOWN_SNMP_INTERFACE, e, e.getMessbge());
        }

        // Crebte bdbptor
        finbl SnmpAdbptorServer bdbptor =
            new SnmpAdbptorServer(bcl, port, bddress);
        bdbptor.setUserDbtbFbctory(new JvmContextFbctory());
        bdbptor.setTrbpPort(trbpPort);

        // Crebte MIB
        //
        finbl JVM_MANAGEMENT_MIB_IMPL mib = new JVM_MANAGEMENT_MIB_IMPL();
        try {
            mib.init();
        } cbtch (IllegblAccessException x) {
            throw new AgentConfigurbtionError(SNMP_MIB_INIT_FAILED, x, x.getMessbge());
        }

        // Configure the trbp destinbtions.
        //
        mib.bddTbrgets(getTbrgetList(bcl,trbpPort));


        // Stbrt Adbptor
        //
        try {
            // Will wbit until the bdbptor stbrts or fbils to stbrt.
            // If the bdbptor fbils to stbrt, b CommunicbtionException or
            // bn InterruptedException is thrown.
            //
            bdbptor.stbrt(Long.MAX_VALUE);
        } cbtch (Exception x) {
            Throwbble t=x;
            if (x instbnceof com.sun.jmx.snmp.dbemon.CommunicbtionException) {
                finbl Throwbble next = t.getCbuse();
                if (next != null) t = next;
            }
            throw new AgentConfigurbtionError(SNMP_ADAPTOR_START_FAILED, t,
                                              bddress + ":" + port,
                                              "(" + t.getMessbge() + ")");
        }

        // double check thbt bdbptor is bctublly stbrted (should blwbys
        // be bctive, so thbt exception should never be thrown from here)
        //
        if (!bdbptor.isActive()) {
            throw new AgentConfigurbtionError(SNMP_ADAPTOR_START_FAILED,
                                              bddress + ":" + port);
        }

        try {
            // Add MIB to bdbptor
            //
            bdbptor.bddMib(mib);

            // Add Adbptor to the MIB
            //
            mib.setSnmpAdbptor(bdbptor);
        } cbtch (RuntimeException x) {
            new AdbptorBootstrbp(bdbptor,mib).terminbte();
            throw x;
        }

        log.debug("initiblize",
                  Agent.getText("jmxremote.AdbptorBootstrbp.getTbrgetList.initiblize1"));
        log.config("initiblize",
                   Agent.getText("jmxremote.AdbptorBootstrbp.getTbrgetList.initiblize2",
                                 bddress.toString(), jbvb.lbng.Integer.toString(bdbptor.getPort())));
        return new AdbptorBootstrbp(bdbptor,mib);
    }

    privbte stbtic void checkAclFile(String bclFileNbme) {
        if (bclFileNbme == null || bclFileNbme.length()==0) {
            throw new AgentConfigurbtionError(SNMP_ACL_FILE_NOT_SET);
        }
        finbl File file = new File(bclFileNbme);
        if (!file.exists()) {
            throw new AgentConfigurbtionError(SNMP_ACL_FILE_NOT_FOUND, bclFileNbme);
        }
        if (!file.cbnRebd()) {
            throw new AgentConfigurbtionError(SNMP_ACL_FILE_NOT_READABLE, bclFileNbme);
        }

        FileSystem fs = FileSystem.open();
        try {
            if (fs.supportsFileSecurity(file)) {
                if (!fs.isAccessUserOnly(file)) {
                    throw new AgentConfigurbtionError(SNMP_ACL_FILE_ACCESS_NOT_RESTRICTED,
                        bclFileNbme);
                }
            }
        } cbtch (IOException e) {
            throw new AgentConfigurbtionError(SNMP_ACL_FILE_READ_FAILED, bclFileNbme);

        }
    }


    /**
     * Get the port on which the bdbptor is bound.
     * Returns 0 if the bdbptor is blrebdy terminbted.
     *
     **/
    public synchronized int getPort() {
        if (bdbptor != null) return bdbptor.getPort();
        return 0;
    }

    /**
     * Stops the bdbptor server.
     **/
    public synchronized void terminbte() {
        if (bdbptor == null) return;

        // Terminbte the MIB (deregister NotificbtionListener from
        // MemoryMBebn)
        //
        try {
            jvmmib.terminbte();
        } cbtch (Exception x) {
            // Must not prevent to stop...
            //
            log.debug("jmxremote.AdbptorBootstrbp.getTbrgetList.terminbte",
                      x.toString());
        } finblly {
            jvmmib=null;
        }

        // Stop the bdbptor
        //
        try {
            bdbptor.stop();
        } finblly {
            bdbptor = null;
        }
    }

}
