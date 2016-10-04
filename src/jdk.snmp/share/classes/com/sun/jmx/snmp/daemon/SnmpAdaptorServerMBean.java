/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// jbvb import
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.net.InetAddress;

// jmx imports
//
import com.sun.jmx.snmp.SnmpPduFbctory;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpVbrBindList;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpTimeticks;
import com.sun.jmx.snmp.SnmpIpAddress;
import com.sun.jmx.snmp.SnmpPduPbcket;
import com.sun.jmx.snmp.InetAddressAcl;
import com.sun.jmx.snmp.SnmpPeer;

// SNMP Runtime imports
//
import com.sun.jmx.snmp.bgent.SnmpMibAgent;
import com.sun.jmx.snmp.bgent.SnmpMibHbndler;
import com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory;

/**
 * Exposes the remote mbnbgement interfbce of the {@link SnmpAdbptorServer} MBebn.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public interfbce SnmpAdbptorServerMBebn extends CommunicbtorServerMBebn {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Returns the Ip bddress bbsed ACL used by this SNMP protocol bdbptor.
     * @return The <CODE>InetAddressAcl</CODE> implementbtion.
     *
     * @since 1.5
     */
    public InetAddressAcl getInetAddressAcl();
    /**
     * Returns the port used by this SNMP protocol bdbptor for sending trbps.
     * By defbult, port 162 is used.
     *
     * @return The port number for sending SNMP trbps.
     */
    public Integer getTrbpPort();

    /**
     * Sets the port used by this SNMP protocol bdbptor for sending trbps.
     *
     * @pbrbm port The port number for sending SNMP trbps.
     */
    public void setTrbpPort(Integer port);

    /**
     * Returns the port used by this SNMP protocol bdbptor for sending inform requests.
     * By defbult, port 162 is used.
     *
     * @return The port number for sending SNMP inform requests.
     */
    public int getInformPort();

    /**
     * Sets the port used by this SNMP protocol bdbptor for sending inform requests.
     *
     * @pbrbm port The port number for sending SNMP inform requests.
     */
    public void setInformPort(int port);

    /**
     * Gets the number of mbnbgers thbt hbve been processed by this SNMP protocol bdbptor
     * since its crebtion.
     *
     * @return The number of mbnbgers hbndled by this SNMP protocol bdbptor
     * since its crebtion. This counter is not reset by the <CODE>stop</CODE> method.
     */
    public int getServedClientCount();

    /**
     * Gets the number of mbnbgers currently being processed by this
     * SNMP protocol bdbptor.
     *
     * @return The number of mbnbgers currently being processed by this
     * SNMP protocol bdbptor.
     */
    public int getActiveClientCount();

    /**
     * Gets the mbximum number of mbnbgers thbt this SNMP protocol bdbptor cbn
     * process concurrently.
     *
     * @return The mbximum number of mbnbgers thbt this SNMP protocol bdbptor cbn
     * process concurrently.
     */
    public int getMbxActiveClientCount();

    /**
     * Sets the mbximum number of mbnbgers this SNMP protocol bdbptor cbn
     * process concurrently.
     *
     * @pbrbm c The number of mbnbgers.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    public void setMbxActiveClientCount(int c) throws jbvb.lbng.IllegblStbteException;

    /**
     * Returns the protocol of this SNMP protocol bdbptor.
     *
     * @return The string "snmp".
     */
    @Override
    public String getProtocol();

    /**
     * Returns the buffer size of this SNMP protocol bdbptor.
     * By defbult, buffer size 1024 is used.
     *
     * @return The buffer size.
     */
    public Integer getBufferSize();

    /**
     * Sets the buffer size of this SNMP protocol bdbptor.
     *
     * @pbrbm s The buffer size.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    public void setBufferSize(Integer s) throws jbvb.lbng.IllegblStbteException;

    /**
     * Gets the number of times to try sending bn inform request before giving up.
     * @return The mbximun number of tries.
     */
    public int getMbxTries();

    /**
     * Chbnges the mbximun number of times to try sending bn inform request before giving up.
     * @pbrbm newMbxTries The mbximun number of tries.
     */
    public void setMbxTries(int newMbxTries);

    /**
     * Gets the timeout to wbit for bn inform response from the mbnbger.
     * @return The vblue of the timeout property.
     */
    public int getTimeout();

    /**
     * Chbnges the timeout to wbit for bn inform response from the mbnbger.
     * @pbrbm newTimeout The timeout (in milliseconds).
     */
    public void setTimeout(int newTimeout);

    /**
     * Returns the messbge fbctory of this SNMP protocol bdbptor.
     *
     * @return The fbctory object.
     */
    public SnmpPduFbctory getPduFbctory();

    /**
     * Sets the messbge fbctory of this SNMP protocol bdbptor.
     *
     * @pbrbm fbctory The fbctory object (null mebns the defbult fbctory).
     */
    public void setPduFbctory(SnmpPduFbctory fbctory);


    /**
     * Set the user-dbtb fbctory of this SNMP protocol bdbptor.
     *
     * @pbrbm fbctory The fbctory object (null mebns no fbctory).
     * @see com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory
     */
    public void setUserDbtbFbctory(SnmpUserDbtbFbctory fbctory);

    /**
     * Get the user-dbtb fbctory bssocibted with this SNMP protocol bdbptor.
     *
     * @return The fbctory object (null mebns no fbctory).
     * @see com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory
     */
    public SnmpUserDbtbFbctory getUserDbtbFbctory();

    /**
     * Returns <CODE>true</CODE> if buthenticbtion trbps bre enbbled.
     * <P>
     * When this febture is enbbled, the SNMP protocol bdbptor sends
     * bn <CODE>buthenticbtionFbilure</CODE> trbp ebch time bn buthenticbtion fbils.
     * <P>
     * The defbult behbviour is to send buthenticbtion trbps.
     *
     * @return <CODE>true</CODE> if buthenticbtion trbps bre enbbled, <CODE>fblse</CODE> otherwise.
     */
    public boolebn getAuthTrbpEnbbled();

    /**
     * Sets the flbg indicbting if trbps need to be sent in cbse of buthenticbtion fbilure.
     *
     * @pbrbm enbbled Flbg indicbting if trbps need to be sent.
     */
    public void setAuthTrbpEnbbled(boolebn enbbled);

    /**
     * Returns <code>true</code> if this SNMP protocol bdbptor sends b response in cbse
     * of buthenticbtion fbilure.
     * <P>
     * When this febture is enbbled, the SNMP protocol bdbptor sends b response with <CODE>noSuchNbme</CODE>
     * or <CODE>rebdOnly</CODE> when the buthenticbtion fbiled. If the flbg is disbbled, the
     * SNMP protocol bdbptor trbshes the PDU silently.
     * <P>
     * The defbult behbvior is to send responses.
     *
     * @return <code>true</code> if responses bre sent.
     */
    public boolebn getAuthRespEnbbled();

    /**
     * Sets the flbg indicbting if responses need to be sent in cbse of buthenticbtion fbilure.
     *
     * @pbrbm enbbled Flbg indicbting if responses need to be sent.
     */
    public void setAuthRespEnbbled(boolebn enbbled);

    /**
     * Returns the enterprise OID. It is used by {@link #snmpV1Trbp snmpV1Trbp} to fill
     * the 'enterprise' field of the trbp request.
     *
     * @return The OID in string formbt "x.x.x.x".
     */
    public String getEnterpriseOid();

    /**
     * Sets the enterprise OID.
     *
     * @pbrbm oid The OID in string formbt "x.x.x.x".
     *
     * @exception IllegblArgumentException The string formbt is incorrect
     */
    public void setEnterpriseOid(String oid) throws IllegblArgumentException;

    /**
     * Returns the nbmes of the MIBs bvbilbble in this SNMP protocol bdbptor.
     *
     * @return An brrby of MIB nbmes.
     */
    public String[] getMibs();

    // GETTERS FOR SNMP GROUP (MIBII)
    //-------------------------------

    /**
     * Returns the <CODE>snmpOutTrbps</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutTrbps</CODE> vblue.
     */
    public Long getSnmpOutTrbps();

    /**
     * Returns the <CODE>snmpOutGetResponses</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutGetResponses</CODE> vblue.
     */
    public Long getSnmpOutGetResponses();

    /**
     * Returns the <CODE>snmpOutGenErrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutGenErrs</CODE> vblue.
     */
    public Long getSnmpOutGenErrs();

    /**
     * Returns the <CODE>snmpOutBbdVblues</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutBbdVblues</CODE> vblue.
     */
    public Long getSnmpOutBbdVblues();

    /**
     * Returns the <CODE>snmpOutNoSuchNbmes</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutNoSuchNbmes</CODE> vblue.
     */
    public Long getSnmpOutNoSuchNbmes();

    /**
     * Returns the <CODE>snmpOutTooBigs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutTooBigs</CODE> vblue.
     */
    public Long getSnmpOutTooBigs();

    /**
     * Returns the <CODE>snmpInASNPbrseErrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInASNPbrseErrs</CODE> vblue.
     */
    public Long getSnmpInASNPbrseErrs();

    /**
     * Returns the <CODE>snmpInBbdCommunityUses</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInBbdCommunityUses</CODE> vblue.
     */
    public Long getSnmpInBbdCommunityUses();

    /**
     * Returns the <CODE>snmpInBbdCommunityNbmes</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInBbdCommunityNbmes</CODE> vblue.
     */
    public Long getSnmpInBbdCommunityNbmes();

    /**
     * Returns the <CODE>snmpInBbdVersions</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInBbdVersions</CODE> vblue.
     */
    public Long getSnmpInBbdVersions();

    /**
     * Returns the <CODE>snmpOutPkts</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpOutPkts</CODE> vblue.
     */
    public Long getSnmpOutPkts();

    /**
     * Returns the <CODE>snmpInPkts</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInPkts</CODE> vblue.
     */
    public Long getSnmpInPkts();

    /**
     * Returns the <CODE>snmpInGetRequests</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInGetRequests</CODE> vblue.
     */
    public Long getSnmpInGetRequests();

    /**
     * Returns the <CODE>snmpInGetNexts</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInGetNexts</CODE> vblue.
     */
    public Long getSnmpInGetNexts();

    /**
     * Returns the <CODE>snmpInSetRequests</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInSetRequests</CODE> vblue.
     */
    public Long getSnmpInSetRequests();

    /**
     * Returns the <CODE>snmpInTotblSetVbrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInTotblSetVbrs</CODE> vblue.
     */
    public Long getSnmpInTotblSetVbrs();

    /**
     * Returns the <CODE>snmpInTotblReqVbrs</CODE> vblue defined in MIB-II.
     *
     * @return The <CODE>snmpInTotblReqVbrs</CODE> vblue.
     */
    public Long getSnmpInTotblReqVbrs();

    /**
     * Returns the <CODE>snmpSilentDrops</CODE> vblue defined in rfc 1907 NMPv2-MIB .
     *
     * @return The <CODE>snmpSilentDrops</CODE> vblue.
     *
     * @since 1.5
     */
    public Long getSnmpSilentDrops();

    /**
     * Returns the <CODE>snmpProxyDrops</CODE> vblue defined in rfc 1907 NMPv2-MIB .
     *
     * @return The <CODE>snmpProxyDrops</CODE> vblue.
     *
     * @since 1.5
     */
    public Long getSnmpProxyDrops();

    // PUBLIC METHODS
    //---------------

    /**
     * Adds b new MIB in the SNMP MIB hbndler.
     * This method is cblled butombticblly by {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptor(SnmpMibHbndler)}
     * bnd {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptorNbme(ObjectNbme)}
     * bnd should not be cblled directly.
     *
     * @pbrbm mib The MIB to bdd.
     *
     * @return A reference to the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     */
    public SnmpMibHbndler bddMib(SnmpMibAgent mib) throws IllegblArgumentException;

    /**
     * Adds b new MIB in the SNMP MIB hbndler.
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
    public SnmpMibHbndler bddMib(SnmpMibAgent mib, SnmpOid[] oids) throws IllegblArgumentException;

    /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     * This method is cblled butombticblly by {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptor(SnmpMibHbndler)}
     * bnd {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptorNbme(ObjectNbme)}
     * bnd should not be cblled directly.
     *
     * @pbrbm mib The MIB to be removed.
     *
     * @return <code>true</code> if the specified <CODE>mib</CODE> wbs b MIB included in the SNMP MIB hbndler,
     * <code>fblse</code> otherwise.
     */
    public boolebn removeMib(SnmpMibAgent mib);

    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to ebch destinbtion defined in the ACL file (if bvbilbble).
     * If no ACL file or no destinbtions bre bvbilbble, the trbp is sent to the locbl host.
     *
     * @pbrbm generic The generic number of the trbp.
     * @pbrbm specific The specific number of the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     */
    public void snmpV1Trbp(int generic, int specific, SnmpVbrBindList vbrBindList) throws IOException, SnmpStbtusException;


    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>InetAddress</CODE> destinbtion
     * using the specified community string (bnd the ACL file is not used).
     *
     * @pbrbm bddress The <CODE>InetAddress</CODE> destinbtion of the trbp.
     * @pbrbm cs The community string to be used for the trbp.
     * @pbrbm generic The generic number of the trbp.
     * @pbrbm specific The specific number of the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     */
    public void snmpV1Trbp(InetAddress bddress, String cs, int generic, int specific, SnmpVbrBindList vbrBindList)
        throws IOException, SnmpStbtusException;


    /**
     * Sends b trbp using SNMP V1 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>SnmpPeer</CODE> destinbtion.
     * The community string used is the one locbted in the <CODE>SnmpPeer</CODE> pbrbmeters (<CODE>SnmpPbrbmeters.getRdCommunity() </CODE>).
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
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    public void snmpV1Trbp(SnmpPeer peer,
                           SnmpIpAddress bgentAddr,
                           SnmpOid enterpOid,
                           int generic,
                           int specific,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimeticks time) throws IOException, SnmpStbtusException;

    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>SnmpPeer</CODE> destinbtion.
     * <BR>The community string used is the one locbted in the <CODE>SnmpPeer</CODE> pbrbmeters (<CODE>SnmpPbrbmeters.getRdCommunity() </CODE>).
     * <BR>The vbribble list included in the outgoing trbp is composed of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with the vblue specified by <CODE>time</CODE>
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by <CODE>trbpOid</CODE>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified <CODE>vbrBindList</CODE>
     * </UL>
     *
     * @pbrbm peer The <CODE>SnmpPeer</CODE> destinbtion of the trbp.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     * @pbrbm time The time stbmp (overwrite the current time).
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    public void snmpV2Trbp(SnmpPeer peer,
                           SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimeticks time) throws IOException, SnmpStbtusException;

    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to ebch destinbtion defined in the ACL file (if bvbilbble).
     * If no ACL file or no destinbtions bre bvbilbble, the trbp is sent to the locbl host.
     * <BR>The vbribble list included in the outgoing trbp is composed of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by <CODE>trbpOid</CODE>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified <CODE>vbrBindList</CODE>
     * </UL>
     *
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     */
    public void snmpV2Trbp(SnmpOid trbpOid, SnmpVbrBindList vbrBindList) throws IOException, SnmpStbtusException;


    /**
     * Sends b trbp using SNMP V2 trbp formbt.
     * <BR>The trbp is sent to the specified <CODE>InetAddress</CODE> destinbtion
     * using the specified community string (bnd the ACL file is not used).
     * <BR>The vbribble list included in the outgoing trbp is composed of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by <CODE>trbpOid</CODE>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified <CODE>vbrBindList</CODE>
     * </UL>
     *
     * @pbrbm bddress The <CODE>InetAddress</CODE> destinbtion of the trbp.
     * @pbrbm cs The community string to be used for the trbp.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     */
    public void snmpV2Trbp(InetAddress bddress, String cs, SnmpOid trbpOid, SnmpVbrBindList vbrBindList)
        throws IOException, SnmpStbtusException;

    /**
     * Send the specified trbp PDU to the pbssed <CODE>InetAddress</CODE>.
     * @pbrbm bddress The destinbtion bddress.
     * @pbrbm pdu The pdu to send.
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    public void snmpPduTrbp(InetAddress bddress, SnmpPduPbcket pdu)
        throws IOException, SnmpStbtusException;
    /**
     * Send the specified trbp PDU to the pbssed <CODE>SnmpPeer</CODE>.
     * @pbrbm peer The destinbtion peer. The Rebd community string is used of <CODE>SnmpPbrbmeters</CODE> is used bs the trbp community string.
     * @pbrbm pdu The pdu to send.
     * @exception IOException An I/O error occurred while sending the trbp.
     * @exception SnmpStbtusException If the trbp exceeds the limit defined by <CODE>bufferSize</CODE>.
     * @since 1.5
     */
    public void snmpPduTrbp(SnmpPeer peer,
                            SnmpPduPbcket pdu)
        throws IOException, SnmpStbtusException;

    /**
     * Sends bn inform using SNMP V2 inform request formbt.
     * <BR>The inform request is sent to ebch destinbtion defined in the ACL file (if bvbilbble).
     * If no ACL file or no destinbtions bre bvbilbble, the inform request is sent to the locbl host.
     * <BR>The vbribble list included in the outgoing inform request is composed of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by <CODE>trbpOid</CODE>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified <CODE>vbrBindList</CODE>
     * </UL>
     * To send bn inform request, the SNMP bdbptor server must be bctive.
     *
     * @pbrbm cb The cbllbbck thbt is invoked when b request is complete.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @return A vector of {@link com.sun.jmx.snmp.dbemon.SnmpInformRequest} objects.
     * <P>If there is no destinbtion host for this inform request, the returned vector will be empty.
     *
     * @exception IllegblStbteException  This method hbs been invoked while the SNMP bdbptor server wbs not bctive.
     * @exception IOException An I/O error occurred while sending the inform request.
     * @exception SnmpStbtusException If the inform request exceeds the limit defined by <CODE>bufferSize</CODE>.
     */
    public Vector<?> snmpInformRequest(SnmpInformHbndler cb, SnmpOid trbpOid,
            SnmpVbrBindList vbrBindList)
        throws IllegblStbteException, IOException, SnmpStbtusException;

    /**
     * Sends bn inform using SNMP V2 inform request formbt.
     * <BR>The inform is sent to the specified <CODE>InetAddress</CODE> destinbtion
     * using the specified community string.
     * <BR>The vbribble list included in the outgoing inform request is composed of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by <CODE>trbpOid</CODE>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified <CODE>vbrBindList</CODE>
     * </UL>
     * To send bn inform request, the SNMP bdbptor server must be bctive.
     *
     * @pbrbm bddress The <CODE>InetAddress</CODE> destinbtion for this inform request.
     * @pbrbm cs The community string to be used for the inform request.
     * @pbrbm cb The cbllbbck thbt is invoked when b request is complete.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @return The inform request object.
     *
     * @exception IllegblStbteException  This method hbs been invoked while the SNMP bdbptor server wbs not bctive.
     * @exception IOException An I/O error occurred while sending the inform request.
     * @exception SnmpStbtusException If the inform request exceeds the limit defined by <CODE>bufferSize</CODE>.
     */
    public SnmpInformRequest snmpInformRequest(InetAddress bddress, String cs, SnmpInformHbndler cb,
                                               SnmpOid trbpOid, SnmpVbrBindList vbrBindList)
        throws IllegblStbteException, IOException, SnmpStbtusException;


    /**
     * Sends bn inform using SNMP V2 inform request formbt.
     * <BR>The inform is sent to the specified <CODE>SnmpPeer</CODE> destinbtion.
     * <BR> The community string used is the one locbted in the <CODE>SnmpPeer</CODE> pbrbmeters (<CODE>SnmpPbrbmeters.getInformCommunity() </CODE>).
     * <BR>The vbribble list included in the outgoing inform is composed of the following items:
     * <UL>
     * <LI><CODE>sysUpTime.0</CODE> with its current vblue
     * <LI><CODE>snmpTrbpOid.0</CODE> with the vblue specified by <CODE>trbpOid</CODE>
     * <LI><CODE>bll the (oid,vblues)</CODE> from the specified <CODE>vbrBindList</CODE>
     * </UL>
     * To send bn inform request, the SNMP bdbptor server must be bctive.
     *
     * @pbrbm peer The <CODE>SnmpPeer</CODE> destinbtion for this inform request.
     * @pbrbm cb The cbllbbck thbt is invoked when b request is complete.
     * @pbrbm trbpOid The OID identifying the trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbnces or null.
     *
     * @return The inform request object.
     *
     * @exception IllegblStbteException  This method hbs been invoked while the SNMP bdbptor server wbs not bctive.
     * @exception IOException An I/O error occurred while sending the inform request.
     * @exception SnmpStbtusException If the inform request exceeds the limit defined by <CODE>bufferSize</CODE>.
     *
     * @since 1.5
     */
    public SnmpInformRequest snmpInformRequest(SnmpPeer peer,
                                               SnmpInformHbndler cb,
                                               SnmpOid trbpOid,
                                               SnmpVbrBindList vbrBindList) throws IllegblStbteException, IOException, SnmpStbtusException;
}
