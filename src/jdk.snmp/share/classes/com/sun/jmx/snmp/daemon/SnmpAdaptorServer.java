/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.dbemon;


// jbvb imports
//
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.logging.Level;
import jbvb.net.DbtbgrbmSocket;
import jbvb.net.DbtbgrbmPbcket;
import jbvb.net.InetAddress;
import jbvb.net.SocketException;
import jbvb.net.UnknownHostException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InterruptedIOException;


// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.ObjectNbme;
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.SnmpIpAddress;
import com.sun.jmx.snmp.SnmpMessbge;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpPduFbctory;
import com.sun.jmx.snmp.SnmpPduPbcket;
import com.sun.jmx.snmp.SnmpPduRequest;
import com.sun.jmx.snmp.SnmpPduTrbp;
import com.sun.jmx.snmp.SnmpTimeticks;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpVbrBindList;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.InetAddressAcl;
import com.sun.jmx.snmp.SnmpPeer;
import com.sun.jmx.snmp.SnmpPbrbmeters;
// SNMP Runtime imports
//
import com.sun.jmx.snmp.SnmpPduFbctoryBER;
import com.sun.jmx.snmp.bgent.SnmpMibAgent;
import com.sun.jmx.snmp.bgent.SnmpMibHbndler;
import com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory;
import com.sun.jmx.snmp.bgent.SnmpErrorHbndlerAgent;

import com.sun.jmx.snmp.IPAcl.SnmpAcl;

import com.sun.jmx.snmp.tbsks.ThrebdService;

/**
 * Implements bn bdbptor on top of the SNMP protocol.
 * <P>
 * When this SNMP protocol bdbptor is stbrted it crebtes b dbtbgrbm socket
 * bnd is bble to receive requests bnd send trbps or inform requests.
 * When it is stopped, the socket is closed bnd neither requests
 * bnd nor trbps/inform request bre processed.
 * <P>
 * The defbult port number of the socket is 161. This defbult vblue cbn be
 * chbnged by specifying b port number:
 * <UL>
 * <LI>in the object constructor</LI>
 * <LI>using the {@link com.sun.jmx.snmp.dbemon.CommunicbtorServer#setPort
 *     setPort} method before stbrting the bdbptor</LI>
 * </UL>
 * The defbult object nbme is defined by {@link
 * com.sun.jmx.snmp.ServiceNbme#DOMAIN com.sun.jmx.snmp.ServiceNbme.DOMAIN}
 * bnd {@link com.sun.jmx.snmp.ServiceNbme#SNMP_ADAPTOR_SERVER
 * com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_SERVER}.
 * <P>
 * The SNMP protocol bdbptor supports versions 1 bnd 2 of the SNMP protocol
 * in b stbteless wby: when it receives b v1 request, it replies with b v1
 * response, when it receives b v2 request it replies with b v2 response.
 * <BR>The method {@link #snmpV1Trbp snmpV1Trbp} sends trbps using SNMP v1
 * formbt.
 * The method {@link #snmpV2Trbp snmpV2Trbp} sends trbps using SNMP v2 formbt.
 * The method {@link #snmpInformRequest snmpInformRequest} sends inform
 * requests using SNMP v2 formbt.
 * <P>
 * To receive dbtb pbckets, the SNMP protocol bdbptor uses b buffer
 * which size cbn be configured using the property <CODE>bufferSize</CODE>
 * (defbult vblue is 1024).
 * Pbckets which do not fit into the buffer bre rejected.
 * Increbsing <CODE>bufferSize</CODE> bllows the exchbnge of bigger pbckets.
 * However, the underlying networking system mby impose b limit on the size
 * of UDP pbckets.
 * Pbckets which size exceed this limit will be rejected, no mbtter whbt
 * the vblue of <CODE>bufferSize</CODE> bctublly is.
 * <P>
 * An SNMP protocol bdbptor mby serve severbl mbnbgers concurrently. The
 * number of concurrent mbnbgers cbn be limited using the property
 * <CODE>mbxActiveClientCount</CODE>.
 * <p>
 * The SNMP protocol bdbptor specifies b defbult vblue (10) for the
 * <CODE>mbxActiveClientCount</CODE> property. When the bdbptor is stopped,
 * the bctive requests bre interrupted bnd bn error result is sent to
 * the mbnbgers.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpAdbptorServer extends CommunicbtorServer
    implements SnmpAdbptorServerMBebn, MBebnRegistrbtion, SnmpDefinitions,
               SnmpMibHbndler {

    // PRIVATE VARIABLES
    //------------------

    /**
     * Port number for sending SNMP trbps.
     * <BR>The defbult vblue is 162.
     */
    privbte int                 trbpPort = 162;

    /**
     * Port number for sending SNMP inform requests.
     * <BR>The defbult vblue is 162.
     */
    privbte int                 informPort = 162;

    /**
     * The <CODE>InetAddress</CODE> used when crebting the dbtbgrbm socket.
     * <BR>It is specified when crebting the SNMP protocol bdbptor.
     * If not specified, the locbl host mbchine is used.
     */
    InetAddress bddress = null;

    /**
     * The IP bddress bbsed ACL used by this SNMP protocol bdbptor.
     */
    privbte InetAddressAcl ipbcl = null;

    /**
     * The fbctory object.
     */
    privbte SnmpPduFbctory pduFbctory = null;

    /**
     * The user-dbtb fbctory object.
     */
    privbte SnmpUserDbtbFbctory userDbtbFbctory = null;

    /**
     * Indicbtes if the SNMP protocol bdbptor sends b response in cbse
     * of buthenticbtion fbilure
     */
    privbte boolebn buthRespEnbbled = true;

    /**
     * Indicbtes if buthenticbtion trbps bre enbbled.
     */
    privbte boolebn buthTrbpEnbbled = true;

    /**
     * The enterprise OID.
     * <BR>The defbult vblue is "1.3.6.1.4.1.42".
     */
    privbte SnmpOid enterpriseOid = new SnmpOid("1.3.6.1.4.1.42");

    /**
     * The buffer size of the SNMP protocol bdbptor.
     * This buffer size is used for both incoming request bnd outgoing
     * inform requests.
     * <BR>The defbult vblue is 1024.
     */
    int bufferSize = 1024;

    privbte trbnsient long            stbrtUpTime     = 0;
    privbte trbnsient DbtbgrbmSocket  socket          = null;
    trbnsient DbtbgrbmSocket          trbpSocket      = null;
    privbte trbnsient SnmpSession     informSession   = null;
    privbte trbnsient DbtbgrbmPbcket  pbcket          = null;
    trbnsient Vector<SnmpMibAgent>    mibs            = new Vector<>();
    privbte trbnsient SnmpMibTree     root;

    /**
     * Whether ACL must be used.
     */
    privbte trbnsient boolebn         useAcl = true;


    // SENDING SNMP INFORMS STUFF
    //---------------------------

    /**
     * Number of times to try bn inform request before giving up.
     * The defbult number is 3.
     */
    privbte int mbxTries = 3 ;

    /**
     * The bmount of time to wbit for bn inform response from the mbnbger.
     * The defbult bmount of time is 3000 millisec.
     */
    privbte int timeout = 3 * 1000 ;

    // VARIABLES REQUIRED FOR IMPLEMENTING SNMP GROUP (MIBII)
    //-------------------------------------------------------

    /**
     * The <CODE>snmpOutTrbps</CODE> vblue defined in MIB-II.
     */
    int snmpOutTrbps=0;

    /**
     * The <CODE>snmpOutGetResponses</CODE> vblue defined in MIB-II.
     */
    privbte int snmpOutGetResponses=0;

    /**
     * The <CODE>snmpOutGenErrs</CODE> vblue defined in MIB-II.
     */
    privbte int snmpOutGenErrs=0;

    /**
     * The <CODE>snmpOutBbdVblues</CODE> vblue defined in MIB-II.
     */
    privbte int snmpOutBbdVblues=0;

    /**
     * The <CODE>snmpOutNoSuchNbmes</CODE> vblue defined in MIB-II.
     */
    privbte int snmpOutNoSuchNbmes=0;

    /**
     * The <CODE>snmpOutTooBigs</CODE> vblue defined in MIB-II.
     */
    privbte int snmpOutTooBigs=0;

    /**
     * The <CODE>snmpOutPkts</CODE> vblue defined in MIB-II.
     */
    int snmpOutPkts=0;

    /**
     * The <CODE>snmpInASNPbrseErrs</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInASNPbrseErrs=0;

    /**
     * The <CODE>snmpInBbdCommunityUses</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInBbdCommunityUses=0;

    /**
     * The <CODE>snmpInBbdCommunityNbmes</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInBbdCommunityNbmes=0;

    /**
     * The <CODE>snmpInBbdVersions</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInBbdVersions=0;

    /**
     * The <CODE>snmpInGetRequests</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInGetRequests=0;

    /**
     * The <CODE>snmpInGetNexts</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInGetNexts=0;

    /**
     * The <CODE>snmpInSetRequests</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInSetRequests=0;

    /**
     * The <CODE>snmpInPkts</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInPkts=0;

    /**
     * The <CODE>snmpInTotblReqVbrs</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInTotblReqVbrs=0;

    /**
     * The <CODE>snmpInTotblSetVbrs</CODE> vblue defined in MIB-II.
     */
    privbte int snmpInTotblSetVbrs=0;

    /**
     * The <CODE>snmpInTotblSetVbrs</CODE> vblue defined in rfc 1907 MIB-II.
     */
    privbte int snmpSilentDrops=0;

    privbte stbtic finbl String InterruptSysCbllMsg =
        "Interrupted system cbll";
    stbtic finbl SnmpOid sysUpTimeOid = new SnmpOid("1.3.6.1.2.1.1.3.0") ;
    stbtic finbl SnmpOid snmpTrbpOidOid = new SnmpOid("1.3.6.1.6.3.1.1.4.1.0");

    privbte ThrebdService threbdService;

    privbte stbtic int threbdNumber = 6;

    stbtic {
        String s = System.getProperty("com.sun.jmx.snmp.threbdnumber");

        if (s != null) {
            try {
                threbdNumber = Integer.pbrseInt(System.getProperty(s));
            } cbtch (Exception e) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER,
                        SnmpAdbptorServer.clbss.getNbme(),
                        "<stbtic init>",
                        "Got wrong vblue for com.sun.jmx.snmp.threbdnumber: " +
                        s + ". Use the defbult vblue: " + threbdNumber);
            }
        }
    }

    // PUBLIC CONSTRUCTORS
    //--------------------

    /**
     * Initiblizes this SNMP protocol bdbptor using the defbult port (161).
     * Use the {@link com.sun.jmx.snmp.IPAcl.SnmpAcl} defbult
     * implementbtion of the <CODE>InetAddressAcl</CODE> interfbce.
     */
    public SnmpAdbptorServer() {
        this(true, null, com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_PORT,
             null) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the specified port.
     * Use the {@link com.sun.jmx.snmp.IPAcl.SnmpAcl} defbult
     * implementbtion of the <CODE>InetAddressAcl</CODE> interfbce.
     *
     * @pbrbm port The port number for sending SNMP responses.
     */
    public SnmpAdbptorServer(int port) {
        this(true, null, port, null) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the defbult port (161)
     * bnd the specified IP bddress bbsed ACL implementbtion.
     *
     * @pbrbm bcl The <CODE>InetAddressAcl</CODE> implementbtion.
     *        <code>null</code> mebns no ACL - everybody is buthorized.
     *
     * @since 1.5
     */
    public SnmpAdbptorServer(InetAddressAcl bcl) {
        this(fblse, bcl, com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_PORT,
             null) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the defbult port (161)
     * bnd the
     * specified <CODE>InetAddress</CODE>.
     * Use the {@link com.sun.jmx.snmp.IPAcl.SnmpAcl} defbult
     * implementbtion of the <CODE>InetAddressAcl</CODE> interfbce.
     *
     * @pbrbm bddr The IP bddress to bind.
     */
    public SnmpAdbptorServer(InetAddress bddr) {
        this(true, null, com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_PORT,
             bddr) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the specified port bnd the
     * specified IP bddress bbsed ACL implementbtion.
     *
     * @pbrbm bcl The <CODE>InetAddressAcl</CODE> implementbtion.
     *        <code>null</code> mebns no ACL - everybody is buthorized.
     * @pbrbm port The port number for sending SNMP responses.
     *
     * @since 1.5
     */
    public SnmpAdbptorServer(InetAddressAcl bcl, int port) {
        this(fblse, bcl, port, null) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the specified port bnd the
     * specified <CODE>InetAddress</CODE>.
     * Use the {@link com.sun.jmx.snmp.IPAcl.SnmpAcl} defbult
     * implementbtion of the <CODE>InetAddressAcl</CODE> interfbce.
     *
     * @pbrbm port The port number for sending SNMP responses.
     * @pbrbm bddr The IP bddress to bind.
     */
    public SnmpAdbptorServer(int port, InetAddress bddr) {
        this(true, null, port, bddr) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the specified IP
     * bddress bbsed ACL implementbtion bnd the specified
     * <CODE>InetAddress</CODE>.
     *
     * @pbrbm bcl The <CODE>InetAddressAcl</CODE> implementbtion.
     * @pbrbm bddr The IP bddress to bind.
     *
     * @since 1.5
     */
    public SnmpAdbptorServer(InetAddressAcl bcl, InetAddress bddr) {
        this(fblse, bcl, com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_PORT,
             bddr) ;
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the specified port, the
     * specified  bddress bbsed ACL implementbtion bnd the specified
     * <CODE>InetAddress</CODE>.
     *
     * @pbrbm bcl The <CODE>InetAddressAcl</CODE> implementbtion.
     * @pbrbm port The port number for sending SNMP responses.
     * @pbrbm bddr The IP bddress to bind.
     *
     * @since 1.5
     */
    public SnmpAdbptorServer(InetAddressAcl bcl, int port, InetAddress bddr) {
        this(fblse, bcl, port, bddr);
    }

    /**
     * Initiblizes this SNMP protocol bdbptor using the specified port bnd the
     * specified <CODE>InetAddress</CODE>.
     * This constructor bllows to initiblize bn SNMP bdbptor without using
     * the ACL mechbnism (by setting the <CODE>useAcl</CODE> pbrbmeter to
     * fblse).
     * <br>This constructor must be used in pbrticulbr with b plbtform thbt
     * does not support the <CODE>jbvb.security.bcl</CODE> pbckbge like pJbvb.
     *
     * @pbrbm useAcl Specifies if this new SNMP bdbptor uses the ACL mechbnism.
     * If the specified pbrbmeter is set to <CODE>true</CODE>, this
     * constructor is equivblent to
     * <CODE>SnmpAdbptorServer((int)port,(InetAddress)bddr)</CODE>.
     * @pbrbm port The port number for sending SNMP responses.
     * @pbrbm bddr The IP bddress to bind.
     */
    public SnmpAdbptorServer(boolebn useAcl, int port, InetAddress bddr) {
        this(useAcl,null,port,bddr);
    }

    // If forceAcl is `true' bnd InetAddressAcl is null, then b defbult
    // SnmpAcl object is crebted.
    //
    privbte SnmpAdbptorServer(boolebn forceAcl, InetAddressAcl bcl,
                              int port, InetAddress bddr) {
        super(CommunicbtorServer.SNMP_TYPE) ;


        // Initiblize the ACL implementbtion.
        //
        if (bcl == null && forceAcl) {
            try {
                bcl = new SnmpAcl("SNMP protocol bdbptor IP ACL");
            } cbtch (UnknownHostException e) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "constructor", "UnknowHostException when crebting ACL",e);
                }
            }
        } else {
            this.useAcl = (bcl!=null) || forceAcl;
        }

        init(bcl, port, bddr) ;
    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the number of mbnbgers thbt hbve been processed by this
     * SNMP protocol bdbptor  since its crebtion.
     *
     * @return The number of mbnbgers hbndled by this SNMP protocol bdbptor
     * since its crebtion. This counter is not reset by the <CODE>stop</CODE>
     * method.
     */
    @Override
    public int getServedClientCount() {
        return super.getServedClientCount();
    }

    /**
     * Gets the number of mbnbgers currently being processed by this
     * SNMP protocol bdbptor.
     *
     * @return The number of mbnbgers currently being processed by this
     * SNMP protocol bdbptor.
     */
    @Override
    public int getActiveClientCount() {
        return super.getActiveClientCount();
    }

    /**
     * Gets the mbximum number of mbnbgers thbt this SNMP protocol bdbptor cbn
     * process concurrently.
     *
     * @return The mbximum number of mbnbgers thbt this SNMP protocol bdbptor
     *         cbn process concurrently.
     */
    @Override
    public int getMbxActiveClientCount() {
        return super.getMbxActiveClientCount();
    }

    /**
     * Sets the mbximum number of mbnbgers this SNMP protocol bdbptor cbn
     * process concurrently.
     *
     * @pbrbm c The number of mbnbgers.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    @Override
    public void setMbxActiveClientCount(int c)
        throws jbvb.lbng.IllegblStbteException {
        super.setMbxActiveClientCount(c);
    }

    /**
     * Returns the Ip bddress bbsed ACL used by this SNMP protocol bdbptor.
     * @return The <CODE>InetAddressAcl</CODE> implementbtion.
     *
     * @since 1.5
     */
    @Override
    public InetAddressAcl getInetAddressAcl() {
        return ipbcl;
    }

    /**
     * Returns the port used by this SNMP protocol bdbptor for sending trbps.
     * By defbult, port 162 is used.
     *
     * @return The port number for sending SNMP trbps.
     */
    @Override
    public Integer getTrbpPort() {
        return trbpPort;
    }

    /**
     * Sets the port used by this SNMP protocol bdbptor for sending trbps.
     *
     * @pbrbm port The port number for sending SNMP trbps.
     */
    @Override
    public void setTrbpPort(Integer port) {
        setTrbpPort(port.intVblue());
    }

    /**
     * Sets the port used by this SNMP protocol bdbptor for sending trbps.
     *
     * @pbrbm port The port number for sending SNMP trbps.
     */
    public void setTrbpPort(int port) {
        int vbl= port ;
        if (vbl < 0) throw new
            IllegblArgumentException("Trbp port cbnnot be b negbtive vblue");
        trbpPort= vbl ;
    }

    /**
     * Returns the port used by this SNMP protocol bdbptor for sending
     * inform requests. By defbult, port 162 is used.
     *
     * @return The port number for sending SNMP inform requests.
     */
    @Override
    public int getInformPort() {
        return informPort;
    }

    /**
     * Sets the port used by this SNMP protocol bdbptor for sending
     * inform requests.
     *
     * @pbrbm port The port number for sending SNMP inform requests.
     */
    @Override
    public void setInformPort(int port) {
        if (port < 0)
            throw new IllegblArgumentException("Inform request port "+
                                               "cbnnot be b negbtive vblue");
        informPort= port ;
    }

    /**
     * Returns the protocol of this SNMP protocol bdbptor.
     *
     * @return The string "snmp".
     */
    @Override
    public String getProtocol() {
        return "snmp";
    }

    /**
     * Returns the buffer size of this SNMP protocol bdbptor.
     * This buffer size is used for both incoming request bnd outgoing
     * inform requests.
     * By defbult, buffer size 1024 is used.
     *
     * @return The buffer size.
     */
    @Override
    public Integer getBufferSize() {
        return bufferSize;
    }

    /**
     * Sets the buffer size of this SNMP protocol bdbptor.
     * This buffer size is used for both incoming request bnd outgoing
     * inform requests.
     *
     * @pbrbm s The buffer size.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    @Override
    public void setBufferSize(Integer s)
        throws jbvb.lbng.IllegblStbteException {
        if ((stbte == ONLINE) || (stbte == STARTING)) {
            throw new IllegblStbteException("Stop server before cbrrying out"+
                                            " this operbtion");
        }
        bufferSize = s.intVblue() ;
    }

    /**
     * Gets the number of times to try sending bn inform request before
     * giving up.
     * By defbult, b mbximum of 3 tries is used.
     * @return The mbximun number of tries.
     */
    @Override
    finbl public int getMbxTries() {
        return mbxTries;
    }

    /**
     * Chbnges the mbximun number of times to try sending bn inform
     * request before giving up.
     * @pbrbm newMbxTries The mbximun number of tries.
     */
    @Override
    finbl public synchronized void setMbxTries(int newMbxTries) {
        if (newMbxTries < 0)
            throw new IllegblArgumentException();
        mbxTries = newMbxTries;
    }

    /**
     * Gets the timeout to wbit for bn inform response from the mbnbger.
     * By defbult, b timeout of 3 seconds is used.
     * @return The vblue of the timeout property.
     */
    @Override
    finbl public int getTimeout() {
        return timeout;
    }

    /**
     * Chbnges the timeout to wbit for bn inform response from the mbnbger.
     * @pbrbm newTimeout The timeout (in milliseconds).
     */
    @Override
    finbl public synchronized void setTimeout(int newTimeout) {
        if (newTimeout < 0)
            throw new IllegblArgumentException();
        timeout= newTimeout;
    }

    /**
     * Returns the messbge fbctory of this SNMP protocol bdbptor.
     *
     * @return The fbctory object.
     */
    @Override
    public SnmpPduFbctory getPduFbctory() {
        return pduFbctory ;
    }

    /**
     * Sets the messbge fbctory of this SNMP protocol bdbptor.
     *
     * @pbrbm fbctory The fbctory object (null mebns the defbult fbctory).
     */
    @Override
    public void setPduFbctory(SnmpPduFbctory fbctory) {
        if (fbctory == null)
            pduFbctory = new SnmpPduFbctoryBER() ;
        else
            pduFbctory = fbctory ;
    }

    /**
     * Set the user-dbtb fbctory of this SNMP protocol bdbptor.
     *
     * @pbrbm fbctory The fbctory object (null mebns no fbctory).
     * @see com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory
     */
    @Override
    public void setUserDbtbFbctory(SnmpUserDbtbFbctory fbctory) {
        userDbtbFbctory = fbctory ;
    }

    /**
     * Get the user-dbtb fbctory bssocibted with this SNMP protocol bdbptor.
     *
     * @return The fbctory object (null mebns no fbctory).
     * @see com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory
     */
    @Override
    public SnmpUserDbtbFbctory getUserDbtbFbctory() {
        return userDbtbFbctory;
    }

    /**
     * Returns <CODE>true</CODE> if buthenticbtion trbps bre enbbled.
     * <P>
     * When this febture is enbbled, the SNMP protocol bdbptor sends
     * bn <CODE>buthenticbtionFbilure</CODE> trbp ebch time bn
     * buthenticbtion fbils.
     * <P>
     * The defbult behbviour is to send buthenticbtion trbps.
     *
     * @return <CODE>true</CODE> if buthenticbtion trbps bre enbbled,
     *         <CODE>fblse</CODE> otherwise.
     */
    @Override
    public boolebn getAuthTrbpEnbbled() {
        return buthTrbpEnbbled ;
    }

    /**
     * Sets the flbg indicbting if trbps need to be sent in cbse of
     * buthenticbtion fbilure.
     *
     * @pbrbm enbbled Flbg indicbting if trbps need to be sent.
     */
    @Override
    public void setAuthTrbpEnbbled(boolebn enbbled) {
        buthTrbpEnbbled = enbbled ;
    }

    /**
     * Returns <code>true</code> if this SNMP protocol bdbptor sends b
     * response in cbse of buthenticbtion fbilure.
     * <P>
     * When this febture is enbbled, the SNMP protocol bdbptor sends b
     * response with <CODE>noSuchNbme</CODE> or <CODE>rebdOnly</CODE> when
     * the buthenticbtion fbiled. If the flbg is disbbled, the
     * SNMP protocol bdbptor trbshes the PDU silently.
     * <P>
     * The defbult behbvior is to send responses.
     *
     * @return <CODE>true</CODE> if responses bre sent.
     */
    @Override
    public boolebn getAuthRespEnbbled() {
        return buthRespEnbbled ;
    }

    /**
     * Sets the flbg indicbting if responses need to be sent in cbse of
     * buthenticbtion fbilure.
     *
     * @pbrbm enbbled Flbg indicbting if responses need to be sent.
     */
    @Override
    public void setAuthRespEnbbled(boolebn enbbled) {
        buthRespEnbbled = enbbled ;
    }

    /**
     * Returns the enterprise OID. It is used by
     * {@link #snmpV1Trbp snmpV1Trbp} to fill the 'enterprise' field of the
     * trbp request.
     *
     * @return The OID in string formbt "x.x.x.x".
     */
    @Override
    public String getEnterpriseOid() {
        return enterpriseOid.toString() ;
    }

    /**
     * Sets the enterprise OID.
     *
     * @pbrbm oid The OID in string formbt "x.x.x.x".
     *
     * @exception IllegblArgumentException The string formbt is incorrect
     */
    @Override
    public void setEnterpriseOid(String oid) throws IllegblArgumentException {
        enterpriseOid = new SnmpOid(oid) ;
    }

    /**
     * Returns the nbmes of the MIBs bvbilbble in this SNMP protocol bdbptor.
     *
     * @return An brrby of MIB nbmes.
     */
    @Override
    public String[] getMibs() {
        String[] result = new String[mibs.size()] ;
        int i = 0 ;
        for (Enumerbtion<SnmpMibAgent> e = mibs.elements() ; e.hbsMoreElements() ;) {
            SnmpMibAgent mib = e.nextElement() ;
            result[i++] = mib.getMibNbme();
        }
        return result ;
    }

    // GETTERS FOR SNMP GROUP (MIBII)
    //-------------------------------

    /**
     * Returns the <CODE>snmpOutTrbps</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutTrbps</CODE> vblue.
     */
    @Override
    public Long getSnmpOutTrbps() {
        return (long)snmpOutTrbps;
    }

    /**
     * Returns the <CODE>snmpOutGetResponses</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutGetResponses</CODE> vblue.
     */
    @Override
    public Long getSnmpOutGetResponses() {
        return (long)snmpOutGetResponses;
    }

    /**
     * Returns the <CODE>snmpOutGenErrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutGenErrs</CODE> vblue.
     */
    @Override
    public Long getSnmpOutGenErrs() {
        return (long)snmpOutGenErrs;
    }

    /**
     * Returns the <CODE>snmpOutBbdVblues</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutBbdVblues</CODE> vblue.
     */
    @Override
    public Long getSnmpOutBbdVblues() {
        return (long)snmpOutBbdVblues;
    }

    /**
     * Returns the <CODE>snmpOutNoSuchNbmes</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutNoSuchNbmes</CODE> vblue.
     */
    @Override
    public Long getSnmpOutNoSuchNbmes() {
        return (long)snmpOutNoSuchNbmes;
    }

    /**
     * Returns the <CODE>snmpOutTooBigs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutTooBigs</CODE> vblue.
     */
    @Override
    public Long getSnmpOutTooBigs() {
        return (long)snmpOutTooBigs;
    }

    /**
     * Returns the <CODE>snmpInASNPbrseErrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInASNPbrseErrs</CODE> vblue.
     */
    @Override
    public Long getSnmpInASNPbrseErrs() {
        return (long)snmpInASNPbrseErrs;
    }

    /**
     * Returns the <CODE>snmpInBbdCommunityUses</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInBbdCommunityUses</CODE> vblue.
     */
    @Override
    public Long getSnmpInBbdCommunityUses() {
        return (long)snmpInBbdCommunityUses;
    }

    /**
     * Returns the <CODE>snmpInBbdCommunityNbmes</CODE> vblue defined in
     * MIB-II.
     *
     * @return The <CODE>snmpInBbdCommunityNbmes</CODE> vblue.
     */
    @Override
    public Long getSnmpInBbdCommunityNbmes() {
        return (long)snmpInBbdCommunityNbmes;
    }

    /**
     * Returns the <CODE>snmpInBbdVersions</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInBbdVersions</CODE> vblue.
     */
    @Override
    public Long getSnmpInBbdVersions() {
        return (long)snmpInBbdVersions;
    }

    /**
     * Returns the <CODE>snmpOutPkts</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutPkts</CODE> vblue.
     */
    @Override
    public Long getSnmpOutPkts() {
        return (long)snmpOutPkts;
    }

    /**
     * Returns the <CODE>snmpInPkts</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInPkts</CODE> vblue.
     */
    @Override
    public Long getSnmpInPkts() {
        return (long)snmpInPkts;
    }

    /**
     * Returns the <CODE>snmpInGetRequests</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInGetRequests</CODE> vblue.
     */
    @Override
    public Long getSnmpInGetRequests() {
        return (long)snmpInGetRequests;
    }

    /**
     * Returns the <CODE>snmpInGetNexts</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInGetNexts</CODE> vblue.
     */
    @Override
    public Long getSnmpInGetNexts() {
        return (long)snmpInGetNexts;
    }

    /**
     * Returns the <CODE>snmpInSetRequests</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInSetRequests</CODE> vblue.
     */
    @Override
    public Long getSnmpInSetRequests() {
        return (long)snmpInSetRequests;
    }

    /**
     * Returns the <CODE>snmpInTotblSetVbrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInTotblSetVbrs</CODE> vblue.
     */
    @Override
    public Long getSnmpInTotblSetVbrs() {
        return (long)snmpInTotblSetVbrs;
    }

    /**
     * Returns the <CODE>snmpInTotblReqVbrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInTotblReqVbrs</CODE> vblue.
     */
    @Override
    public Long getSnmpInTotblReqVbrs() {
        return (long)snmpInTotblReqVbrs;
    }

    /**
     * Returns the <CODE>snmpSilentDrops</CODE> vblue defined in RFC
     * 1907 NMPv2-MIB .
     *
     * @return The <CODE>snmpSilentDrops</CODE> vblue.
     *
     * @since 1.5
     */
    @Override
    public Long getSnmpSilentDrops() {
        return (long)snmpSilentDrops;
    }

    /**
     * Returns the <CODE>snmpProxyDrops</CODE> vblue defined in RFC
     * 1907 NMPv2-MIB .
     *
     * @return The <CODE>snmpProxyDrops</CODE> vblue.
     *
     * @since 1.5
     */
    @Override
    public Long getSnmpProxyDrops() {
        return 0L;
    }


    // PUBLIC METHODS
    //---------------

    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * registered in the MBebn server.
     * If the nbme of the SNMP protocol bdbptor MBebn is not specified,
     * it is initiblized with the defbult vblue:
     * {@link com.sun.jmx.snmp.ServiceNbme#DOMAIN
     *   com.sun.jmx.snmp.ServiceNbme.DOMAIN}:{@link
     * com.sun.jmx.snmp.ServiceNbme#SNMP_ADAPTOR_SERVER
     * com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_SERVER}.
     * If bny exception is rbised, the SNMP protocol bdbptor MBebn will
     * not be registered in the MBebn server.
     *
     * @pbrbm server The MBebn server to register the service with.
     * @pbrbm nbme The object nbme.
     *
     * @return The nbme of the SNMP protocol bdbptor registered.
     *
     * @exception jbvb.lbng.Exception
     */
    @Override
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
        throws jbvb.lbng.Exception {

        if (nbme == null) {
            nbme = new ObjectNbme(server.getDefbultDombin() + ":" +
                             com.sun.jmx.snmp.ServiceNbme.SNMP_ADAPTOR_SERVER);
        }
        return (super.preRegister(server, nbme));
    }

    /**
     * Not used in this context.
     */
    @Override
    public void postRegister (Boolebn registrbtionDone) {
        super.postRegister(registrbtionDone);
    }

    /**
     * Not used in this context.
     */
    @Override
    public void preDeregister() throws jbvb.lbng.Exception {
        super.preDeregister();
    }

    /**
     * Not used in this context.
     */
    @Override
    public void postDeregister() {
        super.postDeregister();
    }

    /**
     * Adds b new MIB in the SNMP MIB hbndler.
     *
     * @pbrbm mib The MIB to bdd.
     *
     * @return A reference to the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     */
    @Override
    public SnmpMibHbndler bddMib(SnmpMibAgent mib)
        throws IllegblArgumentException {
        if (mib == null) {
            throw new IllegblArgumentException() ;
        }

        if(!mibs.contbins(mib))
            mibs.bddElement(mib);

        root.register(mib);

        return this;
    }

    /**
     * Adds b new MIB in the SNMP MIB hbndler.
     * This method is to be cblled to set b specific bgent to b specific OID.
     * This cbn be useful when debling with MIB overlbpping.
     * Some OID cbn be implemented in more thbn one MIB. In this cbse,
     * the OID nebrer bgent will be used on SNMP operbtions.
     *
     * @pbrbm mib The MIB to bdd.
     * @pbrbm oids The set of OIDs this bgent implements.
     *
     * @return A reference to the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    @Override
    public SnmpMibHbndler bddMib(SnmpMibAgent mib, SnmpOid[] oids)
        throws IllegblArgumentException {
        if (mib == null) {
            throw new IllegblArgumentException() ;
        }

        //If null oid brrby, just bdd it to the mib.
        if(oids == null)
            return bddMib(mib);

        if(!mibs.contbins(mib))
            mibs.bddElement(mib);

        for (int i = 0; i < oids.length; i++) {
            root.register(mib, oids[i].longVblue());
        }
        return this;
    }

    /**
     * Adds b new MIB in the SNMP MIB hbndler. In SNMP V1 bnd V2 the
     * <CODE>contextNbme</CODE> is useless bnd this method
     * is equivblent to <CODE>bddMib(SnmpMibAgent mib)</CODE>.
     *
     * @pbrbm mib The MIB to bdd.
     * @pbrbm contextNbme The MIB context nbme.
     * @return A reference on the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    @Override
    public SnmpMibHbndler bddMib(SnmpMibAgent mib, String contextNbme)
        throws IllegblArgumentException {
        return bddMib(mib);
    }

    /**
     * Adds b new MIB in the SNMP MIB hbndler. In SNMP V1 bnd V2 the
     * <CODE>contextNbme</CODE> is useless bnd this method
     * is equivblent to <CODE>bddMib(SnmpMibAgent mib, SnmpOid[] oids)</CODE>.
     *
     * @pbrbm mib The MIB to bdd.
     * @pbrbm contextNbme The MIB context. If null is pbssed, will be
     *        registered in the defbult context.
     * @pbrbm oids The set of OIDs this bgent implements.
     *
     * @return A reference to the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    @Override
    public SnmpMibHbndler bddMib(SnmpMibAgent mib,
                                 String contextNbme,
                                 SnmpOid[] oids)
        throws IllegblArgumentException {

        return bddMib(mib, oids);
    }

    /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     * In SNMP V1 bnd V2 the <CODE>contextNbme</CODE> is useless bnd this
     * method is equivblent to <CODE>removeMib(SnmpMibAgent mib)</CODE>.
     *
     * @pbrbm mib The MIB to be removed.
     * @pbrbm contextNbme The context nbme used bt registrbtion time.
     *
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs
     * b MIB included in the SNMP MIB hbndler, <CODE>fblse</CODE>
     * otherwise.
     *
     * @since 1.5
     */
    @Override
    public boolebn removeMib(SnmpMibAgent mib, String contextNbme) {
        return removeMib(mib);
    }

    /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     *
     * @pbrbm mib The MIB to be removed.
     *
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs b MIB
     *         included in the SNMP MIB hbndler, <CODE>fblse</CODE> otherwise.
     */
    @Override
    public boolebn removeMib(SnmpMibAgent mib) {
        root.unregister(mib);
        return (mibs.removeElement(mib)) ;
    }

    /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     *
     * @pbrbm mib The MIB to be removed.
     * @pbrbm oids The oid the MIB wbs previously registered for.
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs
     * b MIB included in the SNMP MIB hbndler, <CODE>fblse</CODE>
     * otherwise.
     *
     * @since 1.5
     */
    @Override
    public boolebn removeMib(SnmpMibAgent mib, SnmpOid[] oids) {
        root.unregister(mib, oids);
        return (mibs.removeElement(mib)) ;
    }

     /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     *
     * @pbrbm mib The MIB to be removed.
     * @pbrbm contextNbme The context nbme used bt registrbtion time.
     * @pbrbm oids The oid the MIB wbs previously registered for.
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs
     * b MIB included in the SNMP MIB hbndler, <CODE>fblse</CODE>
     * otherwise.
     *
     * @since 1.5
     */
    @Override
    public boolebn removeMib(SnmpMibAgent mib,
                             String contextNbme,
                             SnmpOid[] oids) {
        return removeMib(mib, oids);
    }

    // SUBCLASSING OF COMMUNICATOR SERVER
    //-----------------------------------

    /**
     * Crebtes the dbtbgrbm socket.
     */
    @Override
    protected void doBind()
        throws CommunicbtionException, InterruptedException {

        try {
            synchronized (this) {
                socket = new DbtbgrbmSocket(port, bddress) ;
            }
            dbgTbg = mbkeDebugTbg();
        } cbtch (SocketException e) {
            if (e.getMessbge().equbls(InterruptSysCbllMsg))
                throw new InterruptedException(e.toString()) ;
            else {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "doBind", "cbnnot bind on port " + port);
                }
                throw new CommunicbtionException(e) ;
            }
        }
    }

    /**
     * Return the bctubl port to which the bdbptor is bound.
     * Cbn be different from the port given bt construction time if
     * thbt port number wbs 0.
     * @return the bctubl port to which the bdbptor is bound.
     **/
    @Override
    public int getPort() {
        synchronized (this) {
            if (socket != null) return socket.getLocblPort();
        }
        return super.getPort();
    }

    /**
     * Closes the dbtbgrbm socket.
     */
    @Override
    protected void doUnbind()
        throws CommunicbtionException, InterruptedException {
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "doUnbind","Finblly close the socket");
        }
        synchronized (this) {
            if (socket != null) {
                socket.close() ;
                socket = null ;
                // Importbnt to inform finblize() thbt the socket is closed...
            }
        }
        closeTrbpSocketIfNeeded() ;
        closeInformSocketIfNeeded() ;
    }

    privbte void crebteSnmpRequestHbndler(SnmpAdbptorServer server,
                                          int id,
                                          DbtbgrbmSocket s,
                                          DbtbgrbmPbcket p,
                                          SnmpMibTree tree,
                                          Vector<SnmpMibAgent> m,
                                          InetAddressAcl b,
                                          SnmpPduFbctory fbctory,
                                          SnmpUserDbtbFbctory dbtbFbctory,
                                          MBebnServer f,
                                          ObjectNbme n) {
        finbl SnmpRequestHbndler hbndler =
            new SnmpRequestHbndler(this, id, s, p, tree, m, b, fbctory,
                                   dbtbFbctory, f, n);
        threbdService.submitTbsk(hbndler);
    }

    /**
     * Rebds b pbcket from the dbtbgrbm socket bnd crebtes b request
     * hbndler which decodes bnd processes the request.
     */
    @Override
    protected void doReceive()
        throws CommunicbtionException, InterruptedException {

        // Let's wbit for something to be received.
        //
        try {
            pbcket = new DbtbgrbmPbcket(new byte[bufferSize], bufferSize) ;
            socket.receive(pbcket);
            int stbte = getStbte();

            if(stbte != ONLINE) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                        "doReceive","received b messbge but stbte not online, returning.");
                }
                return;
            }

            crebteSnmpRequestHbndler(this, servedClientCount, socket,
                                     pbcket, root, mibs, ipbcl, pduFbctory,
                                     userDbtbFbctory, topMBS, objectNbme);
        } cbtch (SocketException e) {
            // Let's check if we hbve been interrupted by stop().
            //
            if (e.getMessbge().equbls(InterruptSysCbllMsg))
                throw new InterruptedException(e.toString()) ;
            else
                throw new CommunicbtionException(e) ;
        } cbtch (InterruptedIOException e) {
            throw new InterruptedException(e.toString()) ;
        } cbtch (CommunicbtionException e) {
            throw e ;
        } cbtch (Exception e) {
            throw new CommunicbtionException(e) ;
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "doReceive", "received b messbge");
        }
    }

    @Override
    protected void doError(Exception e) throws CommunicbtionException {
    }

    /**
     * Not used in this context.
     */
    @Override
    protected void doProcess()
        throws CommunicbtionException, InterruptedException {
    }


    /**
     * The number of times the communicbtor server will bttempt
     * to bind before giving up.
     * We bttempt only once...
     * @return 1
     **/
    @Override
    protected int getBindTries() {
        return 1;
    }

    /**
     * Stops this SNMP protocol bdbptor.
     * Closes the dbtbgrbm socket.
     * <p>
     * Hbs no effect if this SNMP protocol bdbptor is <CODE>OFFLINE</CODE> or
     * <CODE>STOPPING</CODE>.
     */
    @Override
    public void stop(){

        finbl int port = getPort();
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "stop", "Stopping: using port " + port);
        }
        if ((stbte == ONLINE) || (stbte == STARTING)){
            super.stop();
            try {
                DbtbgrbmSocket sn = new DbtbgrbmSocket(0);
                try {
                    byte[] ob = new byte[1];

                    DbtbgrbmPbcket pk;
                    if (bddress != null)
                        pk = new DbtbgrbmPbcket(ob , 1, bddress, port);
                    else
                        pk = new DbtbgrbmPbcket(ob , 1,
                                 jbvb.net.InetAddress.getLocblHost(), port);

                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                            "stop", "Sending: using port " + port);
                    }
                    sn.send(pk);
                } finblly {
                    sn.close();
                }
            } cbtch (Throwbble e){
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "stop", "Got unexpected Throwbble", e);
                }
            }
        }
    }

    // SENDING SNMP TRAPS STUFF
    //-------------------------

    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to ebch destinbtion defined in the ACL file
     * (if bvbilbble).
     * If no ACL file or no destinbtions bre bvbilbble, the trbp is sent
     * to the locbl host.
     *
     * @pbrbm generic The generic number of the trbp.
     * @pbrbm specific The specific number of the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined
     *            by <CODE>bufferSize</CODE>.
     */
    @Override
    public void snmpV1Trbp(int generic, int specific,
                           SnmpVbrBindList vbrBindList)
        throws IOException, SnmpStbtusException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpV1Trbp", "generic=" + generic +
                  ", specific=" + specific);
        }

        // First, mbke bn SNMP V1 trbp pdu
        //
        SnmpPduTrbp pdu = new SnmpPduTrbp() ;
        pdu.bddress = null ;
        pdu.port = trbpPort ;
        pdu.type = pduV1TrbpPdu ;
        pdu.version = snmpVersionOne ;
        pdu.community = null ;
        pdu.enterprise = enterpriseOid ;
        pdu.genericTrbp = generic ;
        pdu.specificTrbp = specific ;
        pdu.timeStbmp = getSysUpTime();

        if (vbrBindList != null) {
            pdu.vbrBindList = new SnmpVbrBind[vbrBindList.size()] ;
            vbrBindList.copyInto(pdu.vbrBindList);
        }
        else
            pdu.vbrBindList = null ;

        // If the locbl host cbnnot be determined, we put 0.0.0.0 in bgentAddr
        try {
            if (bddress != null)
                pdu.bgentAddr = hbndleMultipleIpVersion(bddress.getAddress());
            else pdu.bgentAddr =
              hbndleMultipleIpVersion(InetAddress.getLocblHost().getAddress());
        } cbtch (UnknownHostException e) {
            byte[] zeroedAddr = new byte[4];
            pdu.bgentAddr = hbndleMultipleIpVersion(zeroedAddr) ;
        }

        // Next, send the pdu to bll destinbtions defined in ACL
        //
        sendTrbpPdu(pdu) ;
    }

    privbte SnmpIpAddress hbndleMultipleIpVersion(byte[] bddress) {
        if(bddress.length == 4)
          return new SnmpIpAddress(bddress);
        else {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "hbndleMultipleIPVersion",
                      "Not bn IPv4 bddress, return null");
            }
            return null;
        }
    }

    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>InetAddress</CODE>
     * destinbtion using the specified community string (bnd the ACL file
     * is not used).
     *
     * @pbrbm bddr The <CODE>InetAddress</CODE> destinbtion of the trbp.
     * @pbrbm cs The community string to be used for the trbp.
     * @pbrbm generic The generic number of the trbp.
     * @pbrbm specific The specific number of the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined
     *            by <CODE>bufferSize</CODE>.
     */
    @Override
    public void snmpV1Trbp(InetAddress bddr, String cs, int generic,
                           int specific, SnmpVbrBindList vbrBindList)
        throws IOException, SnmpStbtusException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpV1Trbp", "generic=" + generic + ", specific=" +
                  specific);
        }

        // First, mbke bn SNMP V1 trbp pdu
        //
        SnmpPduTrbp pdu = new SnmpPduTrbp() ;
        pdu.bddress = null ;
        pdu.port = trbpPort ;
        pdu.type = pduV1TrbpPdu ;
        pdu.version = snmpVersionOne ;

        if(cs != null)
            pdu.community = cs.getBytes();
        else
            pdu.community = null ;

        pdu.enterprise = enterpriseOid ;
        pdu.genericTrbp = generic ;
        pdu.specificTrbp = specific ;
        pdu.timeStbmp = getSysUpTime();

        if (vbrBindList != null) {
            pdu.vbrBindList = new SnmpVbrBind[vbrBindList.size()] ;
            vbrBindList.copyInto(pdu.vbrBindList);
        }
        else
            pdu.vbrBindList = null ;

        // If the locbl host cbnnot be determined, we put 0.0.0.0 in bgentAddr
        try {
            if (bddress != null)
                pdu.bgentAddr = hbndleMultipleIpVersion(bddress.getAddress());
            else pdu.bgentAddr =
              hbndleMultipleIpVersion(InetAddress.getLocblHost().getAddress());
        } cbtch (UnknownHostException e) {
            byte[] zeroedAddr = new byte[4];
            pdu.bgentAddr = hbndleMultipleIpVersion(zeroedAddr) ;
        }

        // Next, send the pdu to the specified destinbtion
        //
        if(bddr != null)
            sendTrbpPdu(bddr, pdu) ;
        else
            sendTrbpPdu(pdu);
    }

    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>InetAddress</CODE>
     * destinbtion using the specified pbrbmeters (bnd the ACL file is not
     * used).
     * Note thbt if the specified <CODE>InetAddress</CODE> destinbtion is null,
     * then the ACL file mechbnism is used.
     *
     * @pbrbm bddr The <CODE>InetAddress</CODE> destinbtion of the trbp.
     * @pbrbm bgentAddr The bgent bddress to be used for the trbp.
     * @pbrbm cs The community string to be used for the trbp.
     * @pbrbm enterpOid The enterprise OID to be used for the trbp.
     * @pbrbm generic The generic number of the trbp.
     * @pbrbm specific The specific number of the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     * @pbrbm time The time stbmp (overwrite the current time).
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined
     *            by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    public void snmpV1Trbp(InetAddress bddr,
                           SnmpIpAddress bgentAddr,
                           String cs,
                           SnmpOid enterpOid,
                           int generic,
                           int specific,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimeticks time)
        throws IOException, SnmpStbtusException {
        snmpV1Trbp(bddr,
                   trbpPort,
                   bgentAddr,
                   cs,
                   enterpOid,
                   generic,
                   specific,
                   vbrBindList,
                   time);
    }

    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>SnmpPeer</CODE> destinbtion.
     * The community string used is the one locbted in the
     * <CODE>SnmpPeer</CODE> pbrbmeters
     * (<CODE>SnmpPbrbmeters.getRdCommunity() </CODE>).
     *
     * @pbrbm peer The <CODE>SnmpPeer</CODE> destinbtion of the trbp.
     * @pbrbm bgentAddr The bgent bddress to be used for the trbp.
     * @pbrbm enterpOid The enterprise OID to be used for the trbp.
     * @pbrbm generic The generic number of the trbp.
     * @pbrbm specific The specific number of the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     * @pbrbm time The time stbmp (overwrite the current time).
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit
     * defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    @Override
    public void snmpV1Trbp(SnmpPeer peer,
                           SnmpIpAddress bgentAddr,
                           SnmpOid enterpOid,
                           int generic,
                           int specific,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimeticks time)
        throws IOException, SnmpStbtusException {

        SnmpPbrbmeters p = (SnmpPbrbmeters) peer.getPbrbms();
        snmpV1Trbp(peer.getDestAddr(),
                   peer.getDestPort(),
                   bgentAddr,
                   p.getRdCommunity(),
                   enterpOid,
                   generic,
                   specific,
                   vbrBindList,
                   time);
    }

    privbte void snmpV1Trbp(InetAddress bddr,
                            int port,
                            SnmpIpAddress bgentAddr,
                            String cs,
                            SnmpOid enterpOid,
                            int generic,
                            int specific,
                            SnmpVbrBindList vbrBindList,
                            SnmpTimeticks time)
        throws IOException, SnmpStbtusException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpV1Trbp", "generic=" + generic + ", specific=" +
                  specific);
        }

        // First, mbke bn SNMP V1 trbp pdu
        //
        SnmpPduTrbp pdu = new SnmpPduTrbp() ;
        pdu.bddress = null ;
        pdu.port = port ;
        pdu.type = pduV1TrbpPdu ;
        pdu.version = snmpVersionOne ;

        //Diff stbrt
        if(cs != null)
            pdu.community = cs.getBytes();
        else
            pdu.community = null ;
        //Diff end

        // Diff stbrt
        if(enterpOid != null)
            pdu.enterprise = enterpOid;
        else
            pdu.enterprise = enterpriseOid ;
        //Diff end
        pdu.genericTrbp = generic ;
        pdu.specificTrbp = specific ;
        //Diff stbrt
        if(time != null)
            pdu.timeStbmp = time.longVblue();
        else
            pdu.timeStbmp = getSysUpTime();
        //Diff end

        if (vbrBindList != null) {
            pdu.vbrBindList = new SnmpVbrBind[vbrBindList.size()] ;
            vbrBindList.copyInto(pdu.vbrBindList);
        }
        else
            pdu.vbrBindList = null ;

        if (bgentAddr == null) {
            // If the locbl host cbnnot be determined,
            // we put 0.0.0.0 in bgentAddr
            try {
                finbl InetAddress inetAddr =
                    (bddress!=null)?bddress:InetAddress.getLocblHost();
                bgentAddr = hbndleMultipleIpVersion(inetAddr.getAddress());
            }  cbtch (UnknownHostException e) {
                byte[] zeroedAddr = new byte[4];
                bgentAddr = hbndleMultipleIpVersion(zeroedAddr);
            }
        }

        pdu.bgentAddr = bgentAddr;

        // Next, send the pdu to the specified destinbtion
        //
        // Diff stbrt
        if(bddr != null)
            sendTrbpPdu(bddr, pdu) ;
        else
            sendTrbpPdu(pdu);

        //End diff
    }

    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>SnmpPeer</CODE> destinbtion.
     * <BR>The community string used is the one locbted in the
     * <CODE>SnmpPeer</CODE> pbrbmeters
     * (<CODE>SnmpPbrbmeters.getRdCommunity() </CODE>).
     * <BR>The vbribble list included in the outgoing trbp is composed of
     * the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with the vblue specified by
     *     <CODE>time</CODE></LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm peer The <CODE>SnmpPeer</CODE> destinbtion of the trbp.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     * @pbrbm time The time stbmp (overwrite the current time).
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit
     * defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    @Override
    public void snmpV2Trbp(SnmpPeer peer,
                           SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimeticks time)
        throws IOException, SnmpStbtusException {

        SnmpPbrbmeters p = (SnmpPbrbmeters) peer.getPbrbms();
        snmpV2Trbp(peer.getDestAddr(),
                   peer.getDestPort(),
                   p.getRdCommunity(),
                   trbpOid,
                   vbrBindList,
                   time);
    }

    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to ebch destinbtion defined in the ACL file
     * (if bvbilbble). If no ACL file or no destinbtions bre bvbilbble,
     * the trbp is sent to the locbl host.
     * <BR>The vbribble list included in the outgoing trbp is composed of
     * the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined
     *            by <CODE>bufferSize</CODE>.
     */
    @Override
    public void snmpV2Trbp(SnmpOid trbpOid, SnmpVbrBindList vbrBindList)
        throws IOException, SnmpStbtusException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpV2Trbp", "trbpOid=" + trbpOid);
        }

        // First, mbke bn SNMP V2 trbp pdu
        // We clone vbrBindList bnd insert sysUpTime bnd snmpTrbpOid
        //
        SnmpPduRequest pdu = new SnmpPduRequest() ;
        pdu.bddress = null ;
        pdu.port = trbpPort ;
        pdu.type = pduV2TrbpPdu ;
        pdu.version = snmpVersionTwo ;
        pdu.community = null ;

        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.clone() ;
        else
            fullVbl = new SnmpVbrBindList(2) ;
        SnmpTimeticks sysUpTimeVblue = new SnmpTimeticks(getSysUpTime()) ;
        fullVbl.insertElementAt(new SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insertElementAt(new SnmpVbrBind(sysUpTimeOid, sysUpTimeVblue),
                                0);
        pdu.vbrBindList = new SnmpVbrBind[fullVbl.size()] ;
        fullVbl.copyInto(pdu.vbrBindList) ;

        // Next, send the pdu to bll destinbtions defined in ACL
        //
        sendTrbpPdu(pdu) ;
    }

    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>InetAddress</CODE>
     * destinbtion using the specified community string (bnd the ACL file
     * is not used).
     * <BR>The vbribble list included in the outgoing trbp is composed of
     * the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm bddr The <CODE>InetAddress</CODE> destinbtion of the trbp.
     * @pbrbm cs The community string to be used for the trbp.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit
     *            defined by <CODE>bufferSize</CODE>.
     */
    @Override
    public void snmpV2Trbp(InetAddress bddr, String cs, SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList)
        throws IOException, SnmpStbtusException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpV2Trbp", "trbpOid=" + trbpOid);
        }

        // First, mbke bn SNMP V2 trbp pdu
        // We clone vbrBindList bnd insert sysUpTime bnd snmpTrbpOid
        //
        SnmpPduRequest pdu = new SnmpPduRequest() ;
        pdu.bddress = null ;
        pdu.port = trbpPort ;
        pdu.type = pduV2TrbpPdu ;
        pdu.version = snmpVersionTwo ;

        if(cs != null)
            pdu.community = cs.getBytes();
        else
            pdu.community = null;

        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.clone() ;
        else
            fullVbl = new SnmpVbrBindList(2) ;
        SnmpTimeticks sysUpTimeVblue = new SnmpTimeticks(getSysUpTime()) ;
        fullVbl.insertElementAt(new SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insertElementAt(new SnmpVbrBind(sysUpTimeOid, sysUpTimeVblue),
                                0);
        pdu.vbrBindList = new SnmpVbrBind[fullVbl.size()] ;
        fullVbl.copyInto(pdu.vbrBindList) ;

        // Next, send the pdu to the specified destinbtion
        //
        if(bddr != null)
            sendTrbpPdu(bddr, pdu);
        else
            sendTrbpPdu(pdu);
    }

    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>InetAddress</CODE>
     * destinbtion using the specified pbrbmeters (bnd the ACL file is not
     * used).
     * Note thbt if the specified <CODE>InetAddress</CODE> destinbtion is null,
     * then the ACL file mechbnism is used.
     * <BR>The vbribble list included in the outgoing trbp is composed of the
     * following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with the vblue specified by
     *     <CODE>time</CODE></LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm bddr The <CODE>InetAddress</CODE> destinbtion of the trbp.
     * @pbrbm cs The community string to be used for the trbp.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     * @pbrbm time The time stbmp (overwrite the current time).
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit
     * defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    public void snmpV2Trbp(InetAddress bddr,
                           String cs,
                           SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimeticks time)
        throws IOException, SnmpStbtusException {

        snmpV2Trbp(bddr,
                   trbpPort,
                   cs,
                   trbpOid,
                   vbrBindList,
                   time);
    }

    privbte void snmpV2Trbp(InetAddress bddr,
                            int port,
                            String cs,
                            SnmpOid trbpOid,
                            SnmpVbrBindList vbrBindList,
                            SnmpTimeticks time)
        throws IOException, SnmpStbtusException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            finbl StringBuilder strb = new StringBuilder()
                .bppend("trbpOid=").bppend(trbpOid)
                .bppend("\ncommunity=").bppend(cs)
                .bppend("\nbddr=").bppend(bddr)
                .bppend("\nvbrBindList=").bppend(vbrBindList)
                .bppend("\ntime=").bppend(time)
                .bppend("\ntrbpPort=").bppend(port);
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpV2Trbp", strb.toString());
        }

        // First, mbke bn SNMP V2 trbp pdu
        // We clone vbrBindList bnd insert sysUpTime bnd snmpTrbpOid
        //
        SnmpPduRequest pdu = new SnmpPduRequest() ;
        pdu.bddress = null ;
        pdu.port = port ;
        pdu.type = pduV2TrbpPdu ;
        pdu.version = snmpVersionTwo ;

        if(cs != null)
            pdu.community = cs.getBytes();
        else
            pdu.community = null;

        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.clone() ;
        else
            fullVbl = new SnmpVbrBindList(2) ;

        // Only difference with other
        SnmpTimeticks sysUpTimeVblue;
        if(time != null)
            sysUpTimeVblue = time;
        else
            sysUpTimeVblue = new SnmpTimeticks(getSysUpTime()) ;
        //End of diff

        fullVbl.insertElementAt(new SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insertElementAt(new SnmpVbrBind(sysUpTimeOid, sysUpTimeVblue),
                                0);
        pdu.vbrBindList = new SnmpVbrBind[fullVbl.size()] ;
        fullVbl.copyInto(pdu.vbrBindList) ;

        // Next, send the pdu to the specified destinbtion
        //
        // Diff stbrt
        if(bddr != null)
            sendTrbpPdu(bddr, pdu) ;
        else
            sendTrbpPdu(pdu);
        //End diff
    }

    /**
     * Send the specified trbp PDU to the pbssed <CODE>InetAddress</CODE>.
     * @pbrbm bddress The destinbtion bddress.
     * @pbrbm pdu The pdu to send.
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit
     * defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    @Override
    public void snmpPduTrbp(InetAddress bddress, SnmpPduPbcket pdu)
            throws IOException, SnmpStbtusException {

        if(bddress != null)
            sendTrbpPdu(bddress, pdu);
        else
            sendTrbpPdu(pdu);
    }

    /**
     * Send the specified trbp PDU to the pbssed <CODE>SnmpPeer</CODE>.
     * @pbrbm peer The destinbtion peer. The Rebd community string is used of
     * <CODE>SnmpPbrbmeters</CODE> is used bs the trbp community string.
     * @pbrbm pdu The pdu to send.
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined
     * by <CODE>bufferSize</CODE>.
     * @since 1.5
     */
    @Override
    public void snmpPduTrbp(SnmpPeer peer,
                            SnmpPduPbcket pdu)
        throws IOException, SnmpStbtusException {
        if(peer != null) {
            pdu.port = peer.getDestPort();
            sendTrbpPdu(peer.getDestAddr(), pdu);
        }
        else {
            pdu.port = getTrbpPort().intVblue();
            sendTrbpPdu(pdu);
        }
    }

    /**
     * Send the specified trbp PDU to every destinbtions from the ACL file.
     */
    privbte void sendTrbpPdu(SnmpPduPbcket pdu)
     throws SnmpStbtusException, IOException {

        // Mbke bn SNMP messbge from the pdu
        //
        SnmpMessbge msg = null ;
        try {
            msg = (SnmpMessbge)pduFbctory.encodeSnmpPdu(pdu, bufferSize) ;
            if (msg == null) {
                throw new SnmpStbtusException(
                          SnmpDefinitions.snmpRspAuthorizbtionError) ;
            }
        }
        cbtch (SnmpTooBigException x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "sendTrbpPdu", "Trbp pdu is too big. " +
                     "Trbp hbsn't been sent to bnyone" );
            }
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspTooBig) ;
            // FIXME: is the right exception to throw ?
            // We could simply forwbrd SnmpTooBigException ?
        }

        // Now send the SNMP messbge to ebch destinbtion
        //
        int sendingCount = 0 ;
        openTrbpSocketIfNeeded() ;
        if (ipbcl != null) {
            Enumerbtion<InetAddress> ed = ipbcl.getTrbpDestinbtions() ;
            while (ed.hbsMoreElements()) {
                msg.bddress = ed.nextElement() ;
                Enumerbtion<String> ec = ipbcl.getTrbpCommunities(msg.bddress) ;
                while (ec.hbsMoreElements()) {
                    msg.community = ec.nextElement().getBytes() ;
                    try {
                        sendTrbpMessbge(msg) ;
                        sendingCount++ ;
                    }
                    cbtch (SnmpTooBigException x) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                                "sendTrbpPdu", "Trbp pdu is too big. " +
                                 "Trbp hbsn't been sent to "+msg.bddress);
                        }
                    }
                }
            }
        }

        // If there is no destinbtion defined or if everything hbs fbiled
        // we tried to send the trbp to the locbl host (bs suggested by
        // mister Olivier Reisbcher).
        //
        if (sendingCount == 0) {
            try {
                msg.bddress = InetAddress.getLocblHost() ;
                sendTrbpMessbge(msg) ;
            } cbtch (SnmpTooBigException x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "sendTrbpPdu", "Trbp pdu is too big. " +
                         "Trbp hbsn't been sent.");
                }
            } cbtch (UnknownHostException e) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "sendTrbpPdu", "Trbp pdu is too big. " +
                         "Trbp hbsn't been sent.");
                }
            }
        }

        closeTrbpSocketIfNeeded() ;
    }

    /**
     * Send the specified trbp PDU to the specified destinbtion.
     */
    privbte void sendTrbpPdu(InetAddress bddr, SnmpPduPbcket pdu)
        throws SnmpStbtusException, IOException {

        // Mbke bn SNMP messbge from the pdu
        //
        SnmpMessbge msg = null ;
        try {
            msg = (SnmpMessbge)pduFbctory.encodeSnmpPdu(pdu, bufferSize) ;
            if (msg == null) {
                throw new SnmpStbtusException(
                          SnmpDefinitions.snmpRspAuthorizbtionError) ;
            }
        } cbtch (SnmpTooBigException x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "sendTrbpPdu", "Trbp pdu is too big. " +
                     "Trbp hbsn't been sent to the specified host.");
            }
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspTooBig) ;
            // FIXME: is the right exception to throw ?
            // We could simply forwbrd SnmpTooBigException ?
        }

        // Now send the SNMP messbge to specified destinbtion
        //
        openTrbpSocketIfNeeded() ;
        if (bddr != null) {
            msg.bddress = bddr;
            try {
                sendTrbpMessbge(msg) ;
            } cbtch (SnmpTooBigException x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "sendTrbpPdu", "Trbp pdu is too big. " +
                         "Trbp hbsn't been sent to " +  msg.bddress);
                }
            }
        }

        closeTrbpSocketIfNeeded() ;
    }

    /**
     * Send the specified messbge on trbpSocket.
     */
    privbte void sendTrbpMessbge(SnmpMessbge msg)
        throws IOException, SnmpTooBigException {

        byte[] buffer = new byte[bufferSize] ;
        DbtbgrbmPbcket pbcket = new DbtbgrbmPbcket(buffer, buffer.length) ;
        int encodingLength = msg.encodeMessbge(buffer) ;
        pbcket.setLength(encodingLength) ;
        pbcket.setAddress(msg.bddress) ;
        pbcket.setPort(msg.port) ;
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "sendTrbpMessbge", "sending trbp to " + msg.bddress + ":" +
                  msg.port);
        }
        trbpSocket.send(pbcket) ;
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "sendTrbpMessbge", "sent to " + msg.bddress + ":" +
                  msg.port);
        }
        snmpOutTrbps++;
        snmpOutPkts++;
    }

    /**
     * Open trbpSocket if it's not blrebdy done.
     */
    synchronized void openTrbpSocketIfNeeded() throws SocketException {
        if (trbpSocket == null) {
            trbpSocket = new DbtbgrbmSocket(0, bddress) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "openTrbpSocketIfNeeded", "using port " +
                      trbpSocket.getLocblPort() + " to send trbps");
            }
        }
    }

    /**
     * Close trbpSocket if the SNMP protocol bdbptor is not ONLINE.
     */
    synchronized void closeTrbpSocketIfNeeded() {
        if ((trbpSocket != null) && (stbte != ONLINE)) {
            trbpSocket.close() ;
            trbpSocket = null ;
        }
    }

    // SENDING SNMP INFORMS STUFF
    //---------------------------

    /**
     * Sends bn inform using SNMP V2 inform request formbt.
     * <BR>The inform request is sent to ebch destinbtion defined in the ACL
     * file (if bvbilbble).
     * If no ACL file or no destinbtions bre bvbilbble, the inform request is
     * sent to the locbl host.
     * <BR>The vbribble list included in the outgoing inform is composed of
     * the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     * To send bn inform request, the SNMP bdbptor server must be bctive.
     *
     * @pbrbm cb The cbllbbck thbt is invoked when b request is complete.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @return A vector of {@link com.sun.jmx.snmp.dbemon.SnmpInformRequest}
     *         objects.
     *         <P>If there is no destinbtion host for this inform request,
     *         the returned vector will be empty.
     *
     * @exception IllegblStbteException  This method hbs been invoked while
     *            the SNMP bdbptor server wbs not bctive.
     * @exception IOException An I/O error occurred while sending the
     *            inform request.
     * @exception SnmpStbtusException If the inform request exceeds the
     *            limit defined by <CODE>bufferSize</CODE>.
     */
    @Override
    public Vector<SnmpInformRequest> snmpInformRequest(SnmpInformHbndler cb,
                                                       SnmpOid trbpOid,
                                                       SnmpVbrBindList vbrBindList)
        throws IllegblStbteException, IOException, SnmpStbtusException {

        if (!isActive()) {
            throw new IllegblStbteException(
               "Stbrt SNMP bdbptor server before cbrrying out this operbtion");
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpInformRequest", "trbpOid=" + trbpOid);
        }

        // First, mbke bn SNMP inform pdu:
        // We clone vbrBindList bnd insert sysUpTime bnd snmpTrbpOid vbribbles.
        //
        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.clone() ;
        else
            fullVbl = new SnmpVbrBindList(2) ;
        SnmpTimeticks sysUpTimeVblue = new SnmpTimeticks(getSysUpTime()) ;
        fullVbl.insertElementAt(new SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insertElementAt(new SnmpVbrBind(sysUpTimeOid, sysUpTimeVblue),
                                0);

        // Next, send the pdu to the specified destinbtion
        //
        openInformSocketIfNeeded() ;

        // Now send the SNMP messbge to ebch destinbtion
        //
        Vector<SnmpInformRequest> informReqList = new Vector<>();
        InetAddress bddr;
        String cs;
        if (ipbcl != null) {
            Enumerbtion<InetAddress> ed = ipbcl.getInformDestinbtions() ;
            while (ed.hbsMoreElements()) {
                bddr = ed.nextElement() ;
                Enumerbtion<String> ec = ipbcl.getInformCommunities(bddr) ;
                while (ec.hbsMoreElements()) {
                    cs = ec.nextElement() ;
                    informReqList.bddElement(
                       informSession.mbkeAsyncRequest(bddr, cs, cb,
                                              fullVbl,getInformPort())) ;
                }
            }
        }

        return informReqList ;
    }

    /**
     * Sends bn inform using SNMP V2 inform request formbt.
     * <BR>The inform is sent to the specified <CODE>InetAddress</CODE>
     * destinbtion
     * using the specified community string.
     * <BR>The vbribble list included in the outgoing inform is composed
     *     of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *      <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     * To send bn inform request, the SNMP bdbptor server must be bctive.
     *
     * @pbrbm bddr The <CODE>InetAddress</CODE> destinbtion for this inform
     *             request.
     * @pbrbm cs The community string to be used for the inform request.
     * @pbrbm cb The cbllbbck thbt is invoked when b request is complete.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @return The inform request object.
     *
     * @exception IllegblStbteException  This method hbs been invoked
     *            while the SNMP bdbptor server wbs not bctive.
     * @exception IOException An I/O error occurred while sending the
     *            inform request.
     * @exception SnmpStbtusException If the inform request exceeds the
     *            limit defined by <CODE>bufferSize</CODE>.
     */
    @Override
    public SnmpInformRequest snmpInformRequest(InetAddress bddr,
                                               String cs,
                                               SnmpInformHbndler cb,
                                               SnmpOid trbpOid,
                                               SnmpVbrBindList vbrBindList)
        throws IllegblStbteException, IOException, SnmpStbtusException {

        return snmpInformRequest(bddr,
                                 getInformPort(),
                                 cs,
                                 cb,
                                 trbpOid,
                                 vbrBindList);
    }

    /**
     * Sends bn inform using SNMP V2 inform request formbt.
     * <BR>The inform is sent to the specified <CODE>SnmpPeer</CODE>
     *     destinbtion.
     * <BR>The community string used is the one locbted in the
     *     <CODE>SnmpPeer</CODE> pbrbmeters
     *     (<CODE>SnmpPbrbmeters.getInformCommunity() </CODE>).
     * <BR>The vbribble list included in the outgoing inform is composed
     *     of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     * To send bn inform request, the SNMP bdbptor server must be bctive.
     *
     * @pbrbm peer The <CODE>SnmpPeer</CODE> destinbtion for this inform
     *             request.
     * @pbrbm cb The cbllbbck thbt is invoked when b request is complete.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @return The inform request object.
     *
     * @exception IllegblStbteException  This method hbs been invoked while
     *            the SNMP bdbptor server wbs not bctive.
     * @exception IOException An I/O error occurred while sending the
     *            inform request.
     * @exception SnmpStbtusException If the inform request exceeds the
     *            limit defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    @Override
    public SnmpInformRequest snmpInformRequest(SnmpPeer peer,
                                               SnmpInformHbndler cb,
                                               SnmpOid trbpOid,
                                               SnmpVbrBindList vbrBindList)
        throws IllegblStbteException, IOException, SnmpStbtusException {

        SnmpPbrbmeters p = (SnmpPbrbmeters) peer.getPbrbms();
        return snmpInformRequest(peer.getDestAddr(),
                                 peer.getDestPort(),
                                 p.getInformCommunity(),
                                 cb,
                                 trbpOid,
                                 vbrBindList);
    }

    /**
     * Method thbt mbps bn SNMP error stbtus in the pbssed protocolVersion
     * bccording to the provided pdu type.
     * @pbrbm errorStbtus The error stbtus to convert.
     * @pbrbm protocolVersion The protocol version.
     * @pbrbm reqPduType The pdu type.
     */
    public stbtic int mbpErrorStbtus(int errorStbtus,
                                     int protocolVersion,
                                     int reqPduType) {
        return SnmpSubRequestHbndler.mbpErrorStbtus(errorStbtus,
                                                    protocolVersion,
                                                    reqPduType);
    }

    privbte SnmpInformRequest snmpInformRequest(InetAddress bddr,
                                                int port,
                                                String cs,
                                                SnmpInformHbndler cb,
                                                SnmpOid trbpOid,
                                                SnmpVbrBindList vbrBindList)
        throws IllegblStbteException, IOException, SnmpStbtusException {

        if (!isActive()) {
            throw new IllegblStbteException(
              "Stbrt SNMP bdbptor server before cbrrying out this operbtion");
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "snmpInformRequest", "trbpOid=" + trbpOid);
        }

        // First, mbke bn SNMP inform pdu:
        // We clone vbrBindList bnd insert sysUpTime bnd snmpTrbpOid vbribbles.
        //
        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.clone() ;
        else
            fullVbl = new SnmpVbrBindList(2) ;
        SnmpTimeticks sysUpTimeVblue = new SnmpTimeticks(getSysUpTime()) ;
        fullVbl.insertElementAt(new SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insertElementAt(new SnmpVbrBind(sysUpTimeOid, sysUpTimeVblue),
                                0);

        // Next, send the pdu to the specified destinbtion
        //
        openInformSocketIfNeeded() ;
        return informSession.mbkeAsyncRequest(bddr, cs, cb, fullVbl, port) ;
    }


    /**
     * Open informSocket if it's not blrebdy done.
     */
    synchronized void openInformSocketIfNeeded() throws SocketException {
        if (informSession == null) {
            informSession = new SnmpSession(this) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                   "openInformSocketIfNeeded",
                      "to send inform requests bnd receive inform responses");
            }
        }
    }

    /**
     * Close informSocket if the SNMP protocol bdbptor is not ONLINE.
     */
    synchronized void closeInformSocketIfNeeded() {
        if ((informSession != null) && (stbte != ONLINE)) {
            informSession.destroySession() ;
            informSession = null ;
        }
    }

    /**
     * Gets the IP bddress to bind.
     * This getter is used to initiblize the DbtbgrbmSocket in the
     * SnmpSocket object crebted for the inform request stuff.
     */
    InetAddress getAddress() {
        return bddress;
    }


    // PROTECTED METHODS
    //------------------

    /**
     * Finblizer of the SNMP protocol bdbptor objects.
     * This method is cblled by the gbrbbge collector on bn object
     * when gbrbbge collection determines thbt there bre no more
     * references to the object.
     * <P>Closes the dbtbgrbm socket bssocibted to this SNMP protocol bdbptor.
     */
    @Override
    protected void finblize() {
        try {
            if (socket != null) {
                socket.close() ;
                socket = null ;
            }

            threbdService.terminbte();
        } cbtch (Exception e) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                   "finblize", "Exception in finblizer", e);
            }
        }
    }

    // PACKAGE METHODS
    //----------------

    /**
     * Returns the string used in debug trbces.
     */
    @Override
    String mbkeDebugTbg() {
        return "SnmpAdbptorServer["+ getProtocol() + ":" + getPort() + "]";
    }

    void updbteRequestCounters(int pduType) {
        switch(pduType)  {

        cbse pduGetRequestPdu:
            snmpInGetRequests++;
            brebk;
        cbse pduGetNextRequestPdu:
            snmpInGetNexts++;
            brebk;
        cbse pduSetRequestPdu:
            snmpInSetRequests++;
            brebk;
        defbult:
            brebk;
        }
        snmpInPkts++ ;
    }

    void updbteErrorCounters(int errorStbtus) {
        switch(errorStbtus) {

        cbse snmpRspNoError:
            snmpOutGetResponses++;
            brebk;
        cbse snmpRspGenErr:
            snmpOutGenErrs++;
            brebk;
        cbse snmpRspBbdVblue:
            snmpOutBbdVblues++;
            brebk;
        cbse snmpRspNoSuchNbme:
            snmpOutNoSuchNbmes++;
            brebk;
        cbse snmpRspTooBig:
            snmpOutTooBigs++;
            brebk;
        defbult:
            brebk;
        }
        snmpOutPkts++ ;
    }

    void updbteVbrCounters(int pduType, int n) {
        switch(pduType) {

        cbse pduGetRequestPdu:
        cbse pduGetNextRequestPdu:
        cbse pduGetBulkRequestPdu:
            snmpInTotblReqVbrs += n ;
            brebk ;
        cbse pduSetRequestPdu:
            snmpInTotblSetVbrs += n ;
            brebk ;
        }
    }

    void incSnmpInASNPbrseErrs(int n) {
        snmpInASNPbrseErrs += n ;
    }

    void incSnmpInBbdVersions(int n) {
        snmpInBbdVersions += n ;
    }

    void incSnmpInBbdCommunityUses(int n) {
        snmpInBbdCommunityUses += n ;
    }

    void incSnmpInBbdCommunityNbmes(int n) {
        snmpInBbdCommunityNbmes += n ;
    }

    void incSnmpSilentDrops(int n) {
        snmpSilentDrops += n ;
    }
    // PRIVATE METHODS
    //----------------

    /**
     * Returns the time (in hundreths of second) elbpsed since the SNMP
     * protocol bdbptor stbrtup.
     */
    long getSysUpTime() {
        return (System.currentTimeMillis() - stbrtUpTime) / 10 ;
    }

    /**
     * Control the wby the SnmpAdbptorServer service is deseriblized.
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
        throws IOException, ClbssNotFoundException {

        // Cbll the defbult deseriblizbtion of the object.
        //
        strebm.defbultRebdObject();

        // Cbll the specific initiblizbtion for the SnmpAdbptorServer service.
        // This is for trbnsient structures to be initiblized to specific
        // defbult vblues.
        //
        mibs      = new Vector<>() ;
    }

    /**
     * Common initiblizbtions.
     */
    privbte void init(InetAddressAcl bcl, int p, InetAddress b) {

        root= new SnmpMibTree();

        // The defbult Agent is initiblized with b SnmpErrorHbndlerAgent bgent.
        root.setDefbultAgent(new SnmpErrorHbndlerAgent());

        // For the trbp time, use the time the bgent stbrted ...
        //
        stbrtUpTime= jbvb.lbng.System.currentTimeMillis();
        mbxActiveClientCount = 10;

        // Crebte the defbult messbge fbctory
        pduFbctory = new SnmpPduFbctoryBER() ;

        port = p ;
        ipbcl = bcl ;
        bddress = b ;

        if ((ipbcl == null) && (useAcl == true))
            throw new IllegblArgumentException("ACL object cbnnot be null") ;

        threbdService = new ThrebdService(threbdNumber);
    }

    SnmpMibAgent getAgentMib(SnmpOid oid) {
        return root.getAgentMib(oid);
    }

    @Override
    protected Threbd crebteMbinThrebd() {
        finbl Threbd t = super.crebteMbinThrebd();
        t.setDbemon(true);
        return t;
    }

}
