/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.Seriblizbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.ServiceNotFoundException;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.MBebnException;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpEngine;

/**
 * Abstrbct clbss for representing bn SNMP bgent.
 *
 * The clbss is used by the SNMP protocol bdbptor bs the entry point in
 * the SNMP bgent to query.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpMibAgent
    implements SnmpMibAgentMBebn, MBebnRegistrbtion, Seriblizbble {

    /**
     * Defbult constructor.
     */
    public SnmpMibAgent() {
    }

    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------

    /**
     * Initiblizes the MIB (with no registrbtion of the MBebns into the
     * MBebn server).
     *
     * @exception IllegblAccessException The MIB cbn not be initiblized.
     */
    public bbstrbct void init() throws IllegblAccessException;

    /**
     * Initiblizes the MIB but ebch single MBebn representing the MIB
     * is inserted into the MBebn server.
     *
     * @pbrbm server The MBebn server to register the service with.
     * @pbrbm nbme The object nbme.
     *
     * @return The nbme of the SNMP MIB registered.
     *
     * @exception jbvb.lbng.Exception
     */
    @Override
    public bbstrbct ObjectNbme preRegister(MBebnServer server,
                                           ObjectNbme nbme)
        throws jbvb.lbng.Exception;

    /**
     * Not used in this context.
     */
    @Override
    public void postRegister (Boolebn registrbtionDone) {
    }

    /**
     * Not used in this context.
     */
    @Override
    public void preDeregister() throws jbvb.lbng.Exception {
    }

    /**
     * Not used in this context.
     */
    @Override
    public void postDeregister() {
    }

    /**
     * Processes b <CODE>get</CODE> operbtion.
     * This method must updbte the SnmpVbrBinds contbined in the
     * <vbr>{@link SnmpMibRequest} req</vbr> pbrbmeter.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribble to
     *            be retrieved. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */
    @Override
    public bbstrbct void get(SnmpMibRequest req)
        throws SnmpStbtusException;

    /**
     * Processes b <CODE>getNext</CODE> operbtion.
     * This method must updbte the SnmpVbrBinds contbined in the
     * <vbr>{@link SnmpMibRequest} req</vbr> pbrbmeter.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of
     *            OIDs from which the next vbribbles should be retrieved.
     *            This list is composed of <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */
    @Override
    public bbstrbct void getNext(SnmpMibRequest req)
        throws SnmpStbtusException;

    /**
     * Processes b <CODE>getBulk</CODE> operbtion.
     * This method must updbte the SnmpVbrBinds contbined in the
     * <vbr>{@link SnmpMibRequest} req</vbr> pbrbmeter.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribble to
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
     */
    @Override
    public bbstrbct void getBulk(SnmpMibRequest req, int nonRepebt,
                                 int mbxRepebt)
        throws SnmpStbtusException;

    /**
     * Processes b <CODE>set</CODE> operbtion.
     * This method must updbte the SnmpVbrBinds contbined in the
     * <vbr>{@link SnmpMibRequest} req</vbr> pbrbmeter.
     * This method is cblled during the second phbse of the SET two-phbse
     * commit.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribble to
     *            be set. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     *            Throwing bn exception in this method will brebk the
     *            btomicity of the SET operbtion. Cbre must be tbken so thbt
     *            the exception is thrown in the {@link #check(SnmpMibRequest)}
     *            method instebd.
     */
    @Override
    public bbstrbct void set(SnmpMibRequest req)
        throws SnmpStbtusException;


    /**
     * Checks if b <CODE>set</CODE> operbtion cbn be performed.
     * If the operbtion cbn not be performed, the method should throw bn
     * <CODE>SnmpStbtusException</CODE>.
     * This method is cblled during the first phbse of the SET two-phbse
     * commit.
     *
     * @pbrbm req The SnmpMibRequest object holding the list of vbribble to
     *            be set. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException The <CODE>set</CODE> operbtion
     *    cbnnot be performed.
     */
    @Override
    public bbstrbct void check(SnmpMibRequest req)
        throws SnmpStbtusException;

    /**
     * Gets the root object identifier of the MIB.
     * <P>The root object identifier is the object identifier uniquely
     * identifying the MIB.
     *
     * @return The root object identifier.
     */
    public bbstrbct long[] getRootOid();

    // ---------------------------------------------------------------------
    // GETTERS AND SETTERS
    // ---------------------------------------------------------------------

    /**
     * Gets the reference to the MBebn server in which the SNMP MIB is
     * registered.
     *
     * @return The MBebn server or null if the MIB is not registered in bny
     *     MBebn server.
     */
    @Override
    public MBebnServer getMBebnServer() {
        return server;
    }

    /**
     * Gets the reference to the SNMP protocol bdbptor to which the MIB is
     * bound.
     *
     * @return The SNMP MIB hbndler.
     */
    @Override
    public SnmpMibHbndler getSnmpAdbptor() {
        return bdbptor;
    }

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler.
     *
     * @pbrbm stbck The SNMP MIB hbndler.
     */
    @Override
    public void setSnmpAdbptor(SnmpMibHbndler stbck) {
        if (bdbptor != null) {
            bdbptor.removeMib(this);
        }
        bdbptor = stbck;
        if (bdbptor != null) {
            bdbptor.bddMib(this);
        }
    }

     /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler.
     * This method is to be cblled to set b specific bgent to b specific OID. This cbn be useful when debling with MIB overlbpping.
     * Some OID cbn be implemented in more thbn one MIB. In this cbse, the OID nebrest the bgent will be used on SNMP operbtions.
     * @pbrbm stbck The SNMP MIB hbndler.
     * @pbrbm oids The set of OIDs this bgent implements.
     *
     * @since 1.5
     */
    @Override
    public void setSnmpAdbptor(SnmpMibHbndler stbck, SnmpOid[] oids) {
        if (bdbptor != null) {
            bdbptor.removeMib(this);
        }
        bdbptor = stbck;
        if (bdbptor != null) {
            bdbptor.bddMib(this, oids);
        }
    }

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdds this new MIB in the SNMP MIB hbndler.
     * Adds b new contextublized MIB in the SNMP MIB hbndler.
     *
     * @pbrbm stbck The SNMP MIB hbndler.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be registered in the defbult context.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    @Override
    public void setSnmpAdbptor(SnmpMibHbndler stbck, String contextNbme) {
        if (bdbptor != null) {
            bdbptor.removeMib(this, contextNbme);
        }
        bdbptor = stbck;
        if (bdbptor != null) {
            bdbptor.bddMib(this, contextNbme);
        }
    }
    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdds this new MIB in the SNMP MIB hbndler.
     * Adds b new contextublized MIB in the SNMP MIB hbndler.
     *
     * @pbrbm stbck The SNMP MIB hbndler.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be registered in the defbult context.
     * @pbrbm oids The set of OIDs this bgent implements.
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    @Override
    public void setSnmpAdbptor(SnmpMibHbndler stbck,
                               String contextNbme,
                               SnmpOid[] oids) {
        if (bdbptor != null) {
            bdbptor.removeMib(this, contextNbme);
        }
        bdbptor = stbck;
        if (bdbptor != null) {
            bdbptor.bddMib(this, contextNbme, oids);
        }
    }

    /**
     * Gets the object nbme of the SNMP protocol bdbptor to which the MIB
     * is bound.
     *
     * @return The nbme of the SNMP protocol bdbptor.
     */
    @Override
    public ObjectNbme getSnmpAdbptorNbme() {
        return bdbptorNbme;
    }

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     *
     * @pbrbm nbme The nbme of the SNMP protocol bdbptor.
     *
     * @exception InstbnceNotFoundException The SNMP protocol bdbptor does
     *     not exist in the MBebn server.
     *
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *     in the MBebn server or the requested service is not supported.
     */
    @Override
    public void setSnmpAdbptorNbme(ObjectNbme nbme)
        throws InstbnceNotFoundException, ServiceNotFoundException {

        if (server == null) {
            throw new ServiceNotFoundException(mibNbme + " is not registered in the MBebn server");
        }
        // First remove the reference on the old bdbptor server.
        //
        if (bdbptor != null) {
            bdbptor.removeMib(this);
        }

        // Then updbte the reference to the new bdbptor server.
        //
        Object[] pbrbms = {this};
        String[] signbture = {"com.sun.jmx.snmp.bgent.SnmpMibAgent"};
        try {
            bdbptor = (SnmpMibHbndler)(server.invoke(nbme, "bddMib", pbrbms,
                                                     signbture));
        } cbtch (InstbnceNotFoundException e) {
            throw new InstbnceNotFoundException(nbme.toString());
        } cbtch (ReflectionException e) {
            throw new ServiceNotFoundException(nbme.toString());
        } cbtch (MBebnException e) {
            // Should never occur...
        }

        bdbptorNbme = nbme;
    }
    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     * This method is to be cblled to set b specific bgent to b specific OID. This cbn be useful when debling with MIB overlbpping.
     * Some OID cbn be implemented in more thbn one MIB. In this cbse, the OID nebrer bgent will be used on SNMP operbtions.
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
    @Override
    public void setSnmpAdbptorNbme(ObjectNbme nbme, SnmpOid[] oids)
        throws InstbnceNotFoundException, ServiceNotFoundException {

        if (server == null) {
            throw new ServiceNotFoundException(mibNbme + " is not registered in the MBebn server");
        }
        // First remove the reference on the old bdbptor server.
        //
        if (bdbptor != null) {
            bdbptor.removeMib(this);
        }

        // Then updbte the reference to the new bdbptor server.
        //
        Object[] pbrbms = {this, oids};
        String[] signbture = {"com.sun.jmx.snmp.bgent.SnmpMibAgent",
        oids.getClbss().getNbme()};
        try {
            bdbptor = (SnmpMibHbndler)(server.invoke(nbme, "bddMib", pbrbms,
                                                     signbture));
        } cbtch (InstbnceNotFoundException e) {
            throw new InstbnceNotFoundException(nbme.toString());
        } cbtch (ReflectionException e) {
            throw new ServiceNotFoundException(nbme.toString());
        } cbtch (MBebnException e) {
            // Should never occur...
        }

        bdbptorNbme = nbme;
    }
    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     *
     * @pbrbm nbme The nbme of the SNMP protocol bdbptor.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be registered in the defbult context.
     * @exception InstbnceNotFoundException The SNMP protocol bdbptor does
     *     not exist in the MBebn server.
     *
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *     in the MBebn server or the requested service is not supported.
     *
     * @since 1.5
     */
    @Override
    public void setSnmpAdbptorNbme(ObjectNbme nbme, String contextNbme)
        throws InstbnceNotFoundException, ServiceNotFoundException {

        if (server == null) {
            throw new ServiceNotFoundException(mibNbme + " is not registered in the MBebn server");
        }

        // First remove the reference on the old bdbptor server.
        //
        if (bdbptor != null) {
            bdbptor.removeMib(this, contextNbme);
        }

        // Then updbte the reference to the new bdbptor server.
        //
        Object[] pbrbms = {this, contextNbme};
        String[] signbture = {"com.sun.jmx.snmp.bgent.SnmpMibAgent", "jbvb.lbng.String"};
        try {
            bdbptor = (SnmpMibHbndler)(server.invoke(nbme, "bddMib", pbrbms,
                                                     signbture));
        } cbtch (InstbnceNotFoundException e) {
            throw new InstbnceNotFoundException(nbme.toString());
        } cbtch (ReflectionException e) {
            throw new ServiceNotFoundException(nbme.toString());
        } cbtch (MBebnException e) {
            // Should never occur...
        }

        bdbptorNbme = nbme;
    }

    /**
     * Sets the reference to the SNMP protocol bdbptor through which the MIB
     * will be SNMP bccessible bnd bdd this new MIB in the SNMP MIB hbndler
     * bssocibted to the specified <CODE>nbme</CODE>.
     *
     * @pbrbm nbme The nbme of the SNMP protocol bdbptor.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be registered in the defbult context.
     * @pbrbm oids The set of OIDs this bgent implements.
     * @exception InstbnceNotFoundException The SNMP protocol bdbptor does
     *     not exist in the MBebn server.
     *
     * @exception ServiceNotFoundException This SNMP MIB is not registered
     *     in the MBebn server or the requested service is not supported.
     *
     * @since 1.5
     */
    @Override
    public void setSnmpAdbptorNbme(ObjectNbme nbme,
                                   String contextNbme, SnmpOid[] oids)
        throws InstbnceNotFoundException, ServiceNotFoundException {

        if (server == null) {
            throw new ServiceNotFoundException(mibNbme + " is not registered in the MBebn server");
        }

        // First remove the reference on the old bdbptor server.
        //
        if (bdbptor != null) {
            bdbptor.removeMib(this, contextNbme);
        }

        // Then updbte the reference to the new bdbptor server.
        //
        Object[] pbrbms = {this, contextNbme, oids};
        String[] signbture = {"com.sun.jmx.snmp.bgent.SnmpMibAgent", "jbvb.lbng.String", oids.getClbss().getNbme()};
        try {
            bdbptor = (SnmpMibHbndler)(server.invoke(nbme, "bddMib", pbrbms,
                                                     signbture));
        } cbtch (InstbnceNotFoundException e) {
            throw new InstbnceNotFoundException(nbme.toString());
        } cbtch (ReflectionException e) {
            throw new ServiceNotFoundException(nbme.toString());
        } cbtch (MBebnException e) {
            // Should never occur...
        }

        bdbptorNbme = nbme;
    }

    /**
     * Indicbtes whether or not the MIB module is bound to b SNMP protocol
     * bdbptor.
     * As b reminder, only bound MIBs cbn be bccessed through SNMP protocol
     * bdbptor.
     *
     * @return <CODE>true</CODE> if the MIB module is bound,
     *         <CODE>fblse</CODE> otherwise.
     */
    @Override
    public boolebn getBindingStbte() {
        if (bdbptor == null)
            return fblse;
        else
            return true;
    }

    /**
     * Gets the MIB nbme.
     *
     * @return The MIB nbme.
     */
    @Override
    public String getMibNbme() {
        return mibNbme;
    }

    /**
     * This is b fbctory method for crebting new SnmpMibRequest objects.
     * @pbrbm reqPdu The received PDU.
     * @pbrbm vblist   The vector of SnmpVbrBind objects in which the
     *        MIB concerned by this request is involved.
     * @pbrbm version  The protocol version of the SNMP request.
     * @pbrbm userDbtb User bllocbted contextubl dbtb.
     *
     * @return A new SnmpMibRequest object.
     *
     * @since 1.5
     **/
    public stbtic SnmpMibRequest newMibRequest(SnmpPdu reqPdu,
                                               Vector<SnmpVbrBind> vblist,
                                               int version,
                                               Object userDbtb)
    {
        return new SnmpMibRequestImpl(null,
                                      reqPdu,
                                      vblist,
                                      version,
                                      userDbtb,
                                      null,
                                      SnmpDefinitions.noAuthNoPriv,
                                      getSecurityModel(version),
                                      null,null);
    }
    /**
     * This is b fbctory method for crebting new SnmpMibRequest objects.
     * @pbrbm engine The locbl engine.
     * @pbrbm reqPdu The received pdu.
     * @pbrbm vblist The vector of SnmpVbrBind objects in which the
     *        MIB concerned by this request is involved.
     * @pbrbm version The protocol version of the SNMP request.
     * @pbrbm userDbtb User bllocbted contextubl dbtb.
     *
     * @return A new SnmpMibRequest object.
     *
     * @since 1.5
     **/
    public stbtic SnmpMibRequest newMibRequest(SnmpEngine engine,
                                               SnmpPdu reqPdu,
                                               Vector<SnmpVbrBind> vblist,
                                               int version,
                                               Object userDbtb,
                                               String principbl,
                                               int securityLevel,
                                               int securityModel,
                                               byte[] contextNbme,
                                               byte[] bccessContextNbme) {
        return new SnmpMibRequestImpl(engine,
                                      reqPdu,
                                      vblist,
                                      version,
                                      userDbtb,
                                      principbl,
                                      securityLevel,
                                      securityModel,
                                      contextNbme,
                                      bccessContextNbme);
    }
    // ---------------------------------------------------------------------
    // PACKAGE METHODS
    // ---------------------------------------------------------------------

    /**
     * Processes b <CODE>getBulk</CODE> operbtion using cbll to
     * <CODE>getNext</CODE>.
     * The method implements the <CODE>getBulk</CODE> operbtion by cblling
     * bppropribtely the <CODE>getNext</CODE> method.
     *
     * @pbrbm req The SnmpMibRequest contbining the vbribble list to be
     *        retrieved.
     *
     * @pbrbm nonRepebt The number of vbribbles, stbrting with the first
     *    vbribble in the vbribble-bindings, for which b single lexicogrbphic
     *    successor is requested.
     *
     * @pbrbm mbxRepebt The number of lexicogrbphic successors
     *    requested for ebch of the lbst R vbribbles. R is the number of
     *    vbribbles following the first nonRepebt vbribbles for which
     *    multiple lexicogrbphic successors bre requested.
     *
     * @return The vbribble list contbining returned vblues.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */
    void getBulkWithGetNext(SnmpMibRequest req, int nonRepebt, int mbxRepebt)
        throws SnmpStbtusException {
        finbl Vector<SnmpVbrBind> list = req.getSubList();

        // RFC 1905, Section 4.2.3, p14
        finbl int L = list.size() ;
        finbl int N = Mbth.mbx(Mbth.min(nonRepebt, L), 0) ;
        finbl int M = Mbth.mbx(mbxRepebt, 0) ;
        finbl int R = L - N ;

        // Let's build the vbrBindList for the response pdu
        //
        // int errorStbtus = SnmpDefinitions.snmpRspNoError ;
        // int errorIndex = 0 ;
        if (L != 0) {

            // Non-repebters bnd first row of repebters
            //
            getNext(req);

            // Now the rembining repebters
            //
            Vector<SnmpVbrBind> repebters= splitFrom(list, N);
            SnmpMibRequestImpl repebtedReq =
                new SnmpMibRequestImpl(req.getEngine(),
                                       req.getPdu(),
                                       repebters,
                                       SnmpDefinitions.snmpVersionTwo,
                                       req.getUserDbtb(),
                                       req.getPrincipbl(),
                                       req.getSecurityLevel(),
                                       req.getSecurityModel(),
                                       req.getContextNbme(),
                                       req.getAccessContextNbme());
            for (int i = 2 ; i <= M ; i++) {
                getNext(repebtedReq);
                concbtVector(req, repebters);
            }
        }
    }


    // ---------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------

    /**
     * This method crebtes b new Vector which does not contbin the first
     * element up to the specified limit.
     *
     * @pbrbm originbl The originbl vector.
     * @pbrbm limit The limit.
     */
    privbte Vector<SnmpVbrBind> splitFrom(Vector<SnmpVbrBind> originbl, int limit) {

        int mbx= originbl.size();
        Vector<SnmpVbrBind> result= new Vector<>(mbx - limit);
        int i= limit;

        // Ok the loop looks b bit strbnge. But in order to improve the
        // perf, we try to bvoid reference to the limit vbribble from
        // within the loop ...
        //
        for(Enumerbtion<SnmpVbrBind> e= originbl.elements(); e.hbsMoreElements(); --i) {
            SnmpVbrBind vbr= e.nextElement();
            if (i >0)
                continue;
            result.bddElement(new SnmpVbrBind(vbr.oid, vbr.vblue));
        }
        return result;
    }

    privbte void concbtVector(SnmpMibRequest req, Vector<SnmpVbrBind> source) {
        for(Enumerbtion<SnmpVbrBind> e= source.elements(); e.hbsMoreElements(); ) {
            SnmpVbrBind vbr= e.nextElement();
            // We need to duplicbte the SnmpVbrBind otherwise it is going
            // to be overlobded by the next get Next ...
            req.bddVbrBind(new SnmpVbrBind(vbr.oid, vbr.vblue));
        }
    }

    privbte stbtic int getSecurityModel(int version) {
        switch(version) {
        cbse SnmpDefinitions.snmpVersionOne:
            return SnmpDefinitions.snmpV1SecurityModel;
        defbult:
            return SnmpDefinitions.snmpV2SecurityModel;
        }
    }

    // ---------------------------------------------------------------------
    // PROTECTED VARIABLES
    // ---------------------------------------------------------------------

    /**
     * The object nbme of the MIB.
     * @seribl
     */
    protected String mibNbme;

    /**
     * The reference to the MBebn server.
     * @seribl
     */
    protected MBebnServer server;

    // ---------------------------------------------------------------------
    // PRIVATE VARIABLES
    // ---------------------------------------------------------------------

    /**
     * The object nbme of the SNMP protocol bdbptor.
     * @seribl
     */
    privbte ObjectNbme bdbptorNbme;

    /**
     * The reference to the SNMP stbck.
     */
    privbte trbnsient SnmpMibHbndler bdbptor;
}
