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


pbckbge com.sun.jmx.snmp.bgent;



// jbvb imports
//
import jbvb.util.Vector;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.ServiceNotFoundException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * Exposes the remote mbnbgement interfbce of the <CODE>SnmpMibAgent</CODE> MBebn.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public interfbce SnmpMibAgentMBebn {

    // PUBLIC METHODS
    //---------------

    /**
     * Processes b <CODE>get</CODE> operbtion.
     * This method must not be cblled from remote.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribbles to
     *            be retrieved. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     * @see SnmpMibAgent#get(SnmpMibRequest)
     */
    public void get(SnmpMibRequest req) throws SnmpStbtusException;

    /**
     * Processes b <CODE>getNext</CODE> operbtion.
     * This method must not be cblled from remote.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribbles to
     *            be retrieved. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     * @see SnmpMibAgent#getNext(SnmpMibRequest)
     */
    public void getNext(SnmpMibRequest req) throws SnmpStbtusException;

    /**
     * Processes b <CODE>getBulk</CODE> operbtion.
     * This method must not be cblled from remote.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribbles to
     *            be retrieved. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @pbrbm nonRepebt The number of vbribbles, stbrting with the first
     *    vbribble in the vbribble-bindings, for which b single
     *    lexicogrbphic successor is requested.
     *
     * @pbrbm mbxRepebt The number of lexicogrbphic successors requested
     *    for ebch of the lbst R vbribbles. R is the number of vbribbles
     *    following the first <CODE>nonRepebt</CODE> vbribbles for which
     *    multiple lexicogrbphic successors bre requested.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     * @see SnmpMibAgent#getBulk(SnmpMibRequest,int,int)
     */
    public void getBulk(SnmpMibRequest req, int nonRepebt, int mbxRepebt)
        throws SnmpStbtusException;

    /**
     * Processes b <CODE>set</CODE> operbtion.
     * This method must not be cblled from remote.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribbles to
     *            be set. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     * @see SnmpMibAgent#set(SnmpMibRequest)
     */
    public void set(SnmpMibRequest req) throws SnmpStbtusException;

    /**
     * Checks if b <CODE>set</CODE> operbtion cbn be performed.
     * If the operbtion cbnnot be performed, the method should emit b
     * <CODE>SnmpStbtusException</CODE>.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribbles to
     *            be set. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException The <CODE>set</CODE> operbtion
     *    cbnnot be performed.
     * @see SnmpMibAgent#check(SnmpMibRequest)
     */
    public void check(SnmpMibRequest req) throws SnmpStbtusException;

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the reference to the MBebn server in which the SNMP MIB is
     * registered.
     *
     * @return The MBebn server or null if the MIB is not registered in bny
     *         MBebn server.
     */
    public MBebnServer getMBebnServer();

    /**
     * Gets the reference to the SNMP protocol bdbptor to which the MIB is
     * bound.
     * <BR>This method is used for bccessing the SNMP MIB hbndler property
     * of the SNMP MIB bgent in cbse of b stbndblone bgent.
     *
     * @return The SNMP MIB hbndler.
     */
    public SnmpMibHbndler getSnmpAdbptor();

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the
     * MIB will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB
     * hbndler.
     * <BR>This method is used for setting the SNMP MIB hbndler property of
     * the SNMP MIB bgent in cbse of b stbndblone bgent.
     *
     * @pbrbm stbck The SNMP MIB hbndler.
     */
    public void setSnmpAdbptor(SnmpMibHbndler stbck);

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler.
     * This method is to be cblled to set b specific bgent to b specific OID.
     * This cbn be useful when debling with MIB overlbpping.
     * Some OID cbn be implemented in more thbn one MIB. In this cbse, the
     * OID nebrer bgent will be used on SNMP operbtions.
     * @pbrbm stbck The SNMP MIB hbndler.
     * @pbrbm oids The set of OIDs this bgent implements.
     *
     * @since 1.5
     */
    public void setSnmpAdbptor(SnmpMibHbndler stbck, SnmpOid[] oids);

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler.
     * Adds b new contextublized MIB in the SNMP MIB hbndler.
     *
     * @pbrbm stbck The SNMP MIB hbndler.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be
     *        registered in the defbult context.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    public void setSnmpAdbptor(SnmpMibHbndler stbck, String contextNbme);

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdds this new MIB in the SNMP MIB hbndler.
     * Adds b new contextublized MIB in the SNMP MIB hbndler.
     *
     * @pbrbm stbck The SNMP MIB hbndler.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be
     *        registered in the defbult context.
     * @pbrbm oids The set of OIDs this bgent implements.
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    public void setSnmpAdbptor(SnmpMibHbndler stbck,
                               String contextNbme,
                               SnmpOid[] oids);

    /**
     * Gets the object nbme of the SNMP protocol bdbptor to which the MIB is
     * bound.
     *
     * @return The nbme of the SNMP protocol bdbptor.
     */
    public ObjectNbme getSnmpAdbptorNbme();

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     *
     * @pbrbm nbme The object nbme of the SNMP MIB hbndler.
     *
     * @exception InstbnceNotFoundException The MBebn does not exist in the
     *        MBebn server.
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *        in the MBebn server or the requested service is not supported.
     */
    public void setSnmpAdbptorNbme(ObjectNbme nbme)
        throws InstbnceNotFoundException, ServiceNotFoundException;


    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     * This method is to be cblled to set b specific bgent to b specific OID.
     * This cbn be useful when debling with MIB overlbpping.
     * Some OID cbn be implemented in more thbn one MIB. In this cbse, the
     * OID nebrer bgent will be used on SNMP operbtions.
     * @pbrbm nbme The nbme of the SNMP protocol bdbptor.
     * @pbrbm oids The set of OIDs this bgent implements.
     * @exception InstbnceNotFoundException The SNMP protocol bdbptor does
     *     not exist in the MBebn server.
     *
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *     in the MBebn server or the requested service is not supported.
     *
     * @since 1.5
     */
    public void setSnmpAdbptorNbme(ObjectNbme nbme, SnmpOid[] oids)
        throws InstbnceNotFoundException, ServiceNotFoundException;

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     *
     * @pbrbm nbme The nbme of the SNMP protocol bdbptor.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be
     *     registered in the defbult context.
     * @exception InstbnceNotFoundException The SNMP protocol bdbptor does
     *     not exist in the MBebn server.
     *
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *     in the MBebn server or the requested service is not supported.
     *
     * @since 1.5
     */
    public void setSnmpAdbptorNbme(ObjectNbme nbme, String contextNbme)
        throws InstbnceNotFoundException, ServiceNotFoundException;

     /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     *
     * @pbrbm nbme The nbme of the SNMP protocol bdbptor.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be
     *        registered in the defbult context.
     * @pbrbm oids The set of OIDs this bgent implements.
     * @exception InstbnceNotFoundException The SNMP protocol bdbptor does
     *     not exist in the MBebn server.
     *
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *     in the MBebn server or the requested service is not supported.
     *
     * @since 1.5
     */
    public void setSnmpAdbptorNbme(ObjectNbme nbme,
                                   String contextNbme,
                                   SnmpOid[] oids)
        throws InstbnceNotFoundException, ServiceNotFoundException;

    /**
     * Indicbtes whether or not the MIB module is bound to b SNMP protocol
     * bdbptor.
     * As b reminder, only bound MIBs cbn be bccessed through SNMP protocol
     * bdbptor.
     *
     * @return <CODE>true</CODE> if the MIB module is bound,
     *         <CODE>fblse</CODE> otherwise.
     */
    public boolebn getBindingStbte();

    /**
     * Gets the MIB nbme.
     *
     * @return The MIB nbme.
     */
    public String getMibNbme();
}
