/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import jbvb.util.logging.Level;
import jbvb.util.Vector;

import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.NotComplibntMBebnException;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * Abstrbct clbss for representing bn SNMP MIB.
 * <P>
 * When compiling b SNMP MIB, bmong bll the clbsses generbted by
 * <CODE>mibgen</CODE>, there is one which extends <CODE>SnmpMib</CODE>
 * for representing b whole MIB.
 * <BR>The clbss is used by the SNMP protocol bdbptor bs the entry point in
 * the MIB.
 *
 * <p>This generbted clbss cbn be subclbssed in your code in order to
 * plug in your own specific behbviour.
 * </p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpMib extends SnmpMibAgent implements Seriblizbble {

    /**
     * Defbult constructor.
     * Initiblizes the OID tree.
     */
    public SnmpMib() {
        root= new SnmpMibOid();
    }


    // --------------------------------------------------------------------
    // POLYMORHIC METHODS
    // --------------------------------------------------------------------

    /**
     * <p>
     * This cbllbbck should return the OID bssocibted to the group
     * identified by the given <code>groupNbme</code>.
     * </p>
     *
     * <p>
     * This method is provided bs b hook to plug-in some custom
     * specific behbvior. Although doing so is discourbged you might
     * wbnt to subclbss this method in order to store & provide more metbdbtb
     * informbtion (mbpping OID <-> symbolic nbme) within the bgent,
     * or to "chbnge" the root of the MIB OID by prefixing the
     * defbultOid by bn bpplicbtion dependbnt OID string, for instbnce.
     * </p>
     *
     * <p>
     * The defbult implementbtion of this method is to return the given
     * <code>defbultOid</code>
     * </p>
     *
     * @pbrbm groupNbme   The jbvb-ized nbme of the SNMP group.
     * @pbrbm defbultOid  The OID defined in the MIB for thbt group
     *                    (in dot notbtion).
     *
     * @return The OID of the group identified by <code>groupNbme</code>,
     *         in dot-notbtion.
     */
    protected String getGroupOid(String groupNbme, String defbultOid) {
        return defbultOid;
    }

    /**
     * <p>
     * This cbllbbck should return the ObjectNbme bssocibted to the
     * group identified by the given <code>groupNbme</code>.
     * </p>
     *
     * <p>
     * This method is provided bs b hook to plug-in some custom
     * specific behbvior. You might wbnt to override this method
     * in order to provide b different object nbming scheme thbn
     * thbt proposed by defbult by <code>mibgen</code>.
     * </p>
     *
     * <p>
     * This method is only mebningful if the MIB is registered
     * in the MBebnServer, otherwise, it will not be cblled.
     * </p>
     *
     * <p>
     * The defbult implementbtion of this method is to return bn ObjectNbme
     * built from the given <code>defbultNbme</code>.
     * </p>
     *
     * @pbrbm nbme  The jbvb-ized nbme of the SNMP group.
     * @pbrbm oid   The OID returned by getGroupOid() - in dot notbtion.
     * @pbrbm defbultNbme The nbme by defbult generbted by <code>
     *                    mibgen</code>
     *
     * @return The ObjectNbme of the group identified by <code>nbme</code>
     */
    protected ObjectNbme getGroupObjectNbme(String nbme, String oid,
                                            String defbultNbme)
        throws MblformedObjectNbmeException {
        return new ObjectNbme(defbultNbme);
    }

    /**
     * <p>
     * Register bn SNMP group bnd its metbdbtb node in the MIB.
     * </p>
     *
     * <p>
     * This method is provided bs b hook to plug-in some custom
     * specific behbvior. You might wbnt to override this method
     * if you wbnt to set specibl links between the MBebn, its metbdbtb
     * node, its OID or ObjectNbme etc..
     * </p>
     *
     * <p>
     * If the MIB is not registered in the MBebnServer, the <code>
     * server</code> bnd <code>groupObjNbme</code> pbrbmeters will be
     * <code>null</code>.<br>
     * If the given group MBebn is not <code>null</code>, bnd if the
     * <code>server</code> bnd <code>groupObjNbme</code> pbrbmeters bre
     * not null, then this method will blso butombticblly register the
     * group MBebn with the given MBebnServer <code>server</code>.
     * </p>
     *
     * @pbrbm groupNbme  The jbvb-ized nbme of the SNMP group.
     * @pbrbm groupOid   The OID bs returned by getGroupOid() - in dot
     *                   notbtion.
     * @pbrbm groupObjNbme The ObjectNbme bs returned by getGroupObjectNbme().
     *                   This pbrbmeter mby be <code>null</code> if the
     *                   MIB is not registered in the MBebnServer.
     * @pbrbm node       The metbdbtb node, bs returned by the metbdbtb
     *                   fbctory method for this group.
     * @pbrbm group      The MBebn for this group, bs returned by the
     *                   MBebn fbctory method for this group.
     * @pbrbm server     The MBebnServer in which the groups bre to be
     *                   registered. This pbrbmeter will be <code>null</code>
     *                   if the MIB is not registered, otherwise it is b
     *                   reference to the MBebnServer in which the MIB is
     *                   registered.
     *
     */
    protected void registerGroupNode(String groupNbme,   String groupOid,
                                     ObjectNbme groupObjNbme, SnmpMibNode node,
                                     Object group, MBebnServer server)
        throws NotComplibntMBebnException, MBebnRegistrbtionException,
        InstbnceAlrebdyExistsException, IllegblAccessException {
        root.registerNode(groupOid,node);
        if (server != null && groupObjNbme != null && group != null)
            server.registerMBebn(group,groupObjNbme);
    }

    /**
     * <p>
     * Register bn SNMP Tbble metbdbtb node in the MIB.
     * </p>
     *
     * <p>
     * <b><i>
     * This method is used internblly bnd you should never need to
     * cbll it directly.</i></b><br> It is used to estbblish the link
     * between bn SNMP tbble metbdbtb node bnd its bebn-like counterpbrt.
     * <br>
     * The group metbdbtb nodes will crebte bnd register their
     * underlying tbble metbdbtb nodes in the MIB using this
     * method. <br>
     * The metbdbtb nodes will be lbter retrieved from the MIB by the
     * bebn-like tbble objects using the getRegisterTbbleMetb() method.
     * </p>
     *
     * @pbrbm nbme      The jbvb-ized nbme of the SNMP tbble.
     * @pbrbm tbble     The SNMP tbble metbdbtb node - usublly this
     *                  corresponds to b <code>mibgen</code> generbted
     *                  object.
     */
    public bbstrbct void registerTbbleMetb(String nbme, SnmpMibTbble tbble);

    /**
     * Returns b registered SNMP Tbble metbdbtb node.
     *
     * <p><b><i>
     * This method is used internblly bnd you should never need to
     * cbll it directly.
     * </i></b></p>
     *
     */
    public bbstrbct SnmpMibTbble getRegisteredTbbleMetb(String nbme);

    // --------------------------------------------------------------------
    // PUBLIC METHODS
    // --------------------------------------------------------------------

    /**
     * Processes b <CODE>get</CODE> operbtion.
     *
     **/
    // Implements the method defined in SnmpMibAgent. See SnmpMibAgent
    // for jbvb-doc
    //
    @Override
    public void get(SnmpMibRequest req) throws SnmpStbtusException {

        // Builds the request tree: crebtion is not bllowed, operbtion
        // is not btomic.

        finbl int reqType = SnmpDefinitions.pduGetRequestPdu;
        SnmpRequestTree hbndlers = getHbndlers(req,fblse,fblse,reqType);

        SnmpRequestTree.Hbndler h = null;
        SnmpMibNode metb = null;

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMib.clbss.getNbme(),
                    "get", "Processing hbndlers for GET... ");
        }

        // For ebch sub-request stored in the request-tree, invoke the
        // get() method.
        for (Enumerbtion<SnmpRequestTree.Hbndler> eh=hbndlers.getHbndlers();eh.hbsMoreElements();) {
            h = eh.nextElement();

            // Gets the Metb node. It cbn be either b Group Metb or b
            // Tbble Metb.
            //
            metb = hbndlers.getMetbNode(h);

            // Gets the depth of the Metb node in the OID tree
            finbl int depth = hbndlers.getOidDepth(h);

            for (Enumerbtion<SnmpMibSubRequest> rqs=hbndlers.getSubRequests(h);
                 rqs.hbsMoreElements();) {

                // Invoke the get() operbtion.
                metb.get(rqs.nextElement(),depth);
            }
        }
    }

    /**
     * Processes b <CODE>set</CODE> operbtion.
     *
     */
    // Implements the method defined in SnmpMibAgent. See SnmpMibAgent
    // for jbvb-doc
    //
    @Override
    public void set(SnmpMibRequest req) throws SnmpStbtusException {

        SnmpRequestTree hbndlers = null;

        // Optimizbtion: we're going to get the whole SnmpRequestTree
        // built in the "check" method, so thbt we don't hbve to rebuild
        // it here.
        //
        if (req instbnceof SnmpMibRequestImpl)
            hbndlers = ((SnmpMibRequestImpl)req).getRequestTree();

        // Optimizbtion didn't work: we hbve to rebuild the tree.
        //
        // Builds the request tree: crebtion is not bllowed, operbtion
        // is btomic.
        //
        finbl int reqType = SnmpDefinitions.pduSetRequestPdu;
        if (hbndlers == null) hbndlers = getHbndlers(req,fblse,true,reqType);
        hbndlers.switchCrebtionFlbg(fblse);
        hbndlers.setPduType(reqType);

        SnmpRequestTree.Hbndler h;
        SnmpMibNode metb;

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMib.clbss.getNbme(),
                    "set", "Processing hbndlers for SET... ");
        }

        // For ebch sub-request stored in the request-tree, invoke the
        // get() method.
        for (Enumerbtion<SnmpRequestTree.Hbndler> eh=hbndlers.getHbndlers();eh.hbsMoreElements();) {
            h = eh.nextElement();

            // Gets the Metb node. It cbn be either b Group Metb or b
            // Tbble Metb.
            //
            metb = hbndlers.getMetbNode(h);

            // Gets the depth of the Metb node in the OID tree
            finbl int depth = hbndlers.getOidDepth(h);

            for (Enumerbtion<SnmpMibSubRequest> rqs=hbndlers.getSubRequests(h);
                 rqs.hbsMoreElements();) {

                // Invoke the set() operbtion
                metb.set(rqs.nextElement(),depth);
            }
        }
    }

    /**
     * Checks if b <CODE>set</CODE> operbtion cbn be performed.
     * If the operbtion cbnnot be performed, the method will rbise b
     * <CODE>SnmpStbtusException</CODE>.
     *
     */
    // Implements the method defined in SnmpMibAgent. See SnmpMibAgent
    // for jbvb-doc
    //
    @Override
    public void check(SnmpMibRequest req) throws SnmpStbtusException {

        finbl int reqType = SnmpDefinitions.pduWblkRequest;
        // Builds the request tree: crebtion is bllowed, operbtion
        // is btomic.
        SnmpRequestTree hbndlers = getHbndlers(req,true,true,reqType);

        SnmpRequestTree.Hbndler h;
        SnmpMibNode metb;

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMib.clbss.getNbme(),
                    "check", "Processing hbndlers for CHECK... ");
        }

        // For ebch sub-request stored in the request-tree, invoke the
        // check() method.
        for (Enumerbtion<SnmpRequestTree.Hbndler> eh=hbndlers.getHbndlers();eh.hbsMoreElements();) {
            h = eh.nextElement();

            // Gets the Metb node. It cbn be either b Group Metb or b
            // Tbble Metb.
            //
            metb = hbndlers.getMetbNode(h);

            // Gets the depth of the Metb node in the OID tree
            finbl int depth = hbndlers.getOidDepth(h);

            for (Enumerbtion<SnmpMibSubRequest> rqs=hbndlers.getSubRequests(h);
                 rqs.hbsMoreElements();) {

                // Invoke the check() operbtion
                metb.check(rqs.nextElement(),depth);
            }
        }

        // Optimizbtion: we're going to pbss the whole SnmpRequestTree
        // to the "set" method, so thbt we don't hbve to rebuild it there.
        //
        if (req instbnceof SnmpMibRequestImpl) {
            ((SnmpMibRequestImpl)req).setRequestTree(hbndlers);
        }

    }

    /**
     * Processes b <CODE>getNext</CODE> operbtion.
     *
     */
    // Implements the method defined in SnmpMibAgent. See SnmpMibAgent
    // for jbvb-doc
    //
    @Override
    public void getNext(SnmpMibRequest req) throws SnmpStbtusException {
        // Build the request tree for the operbtion
        // The subrequest stored in the request tree bre vblid GET requests
        SnmpRequestTree hbndlers = getGetNextHbndlers(req);

        SnmpRequestTree.Hbndler h;
        SnmpMibNode metb;

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMib.clbss.getNbme(),
                    "getNext", "Processing hbndlers for GET-NEXT... ");
        }

        // Now invoke get() for ebch subrequest of the request tree.
        for (Enumerbtion<SnmpRequestTree.Hbndler> eh=hbndlers.getHbndlers();eh.hbsMoreElements();) {
            h = eh.nextElement();

            // Gets the Metb node. It cbn be either b Group Metb or b
            // Tbble Metb.
            //
            metb = hbndlers.getMetbNode(h);

            // Gets the depth of the Metb node in the OID tree
            int depth = hbndlers.getOidDepth(h);

            for (Enumerbtion<SnmpMibSubRequest> rqs=hbndlers.getSubRequests(h);
                 rqs.hbsMoreElements();) {

                // Invoke the get() operbtion
                metb.get(rqs.nextElement(),depth);
            }
        }
    }


    /**
     * Processes b <CODE>getBulk</CODE> operbtion.
     * The method implements the <CODE>getBulk</CODE> operbtion by cblling
     * bppropribtely the <CODE>getNext</CODE> method.
     *
     */
    // Implements the method defined in SnmpMibAgent. See SnmpMibAgent
    // for jbvb-doc
    //
    @Override
    public void getBulk(SnmpMibRequest req, int nonRepebt, int mbxRepebt)
        throws SnmpStbtusException {

        getBulkWithGetNext(req, nonRepebt, mbxRepebt);
    }

    /**
     * Gets the root object identifier of the MIB.
     * <P>In order to be bccurbte, the method should be cblled once the
     * MIB is fully initiblized (thbt is, bfter b cbll to <CODE>init</CODE>
     * or <CODE>preRegister</CODE>).
     *
     * @return The root object identifier.
     */
    @Override
    public long[] getRootOid() {

        if( rootOid == null) {
            Vector<Integer> list= new Vector<>(10);

            // Ask the tree to do the job !
            //
            root.getRootOid(list);

            // Now formbt the result
            //
            rootOid= new long[list.size()];
            int i=0;
            for(Enumerbtion<Integer> e= list.elements(); e.hbsMoreElements(); ) {
                Integer vbl= e.nextElement();
                rootOid[i++]= vbl.longVblue();
            }
        }
        return rootOid.clone();
    }

    // --------------------------------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------------------------------

    /**
     * This method builds the temporbry request-tree thbt will be used to
     * perform the SNMP request bssocibted with the given vector of vbrbinds
     * `list'.
     *
     * @pbrbm req The SnmpMibRequest object holding the vbrbind list
     *             concerning this MIB.
     * @pbrbm crebteflbg Indicbtes whether the operbtion bllow for crebtion
     *        of new instbnces (ie: it is b SET).
     * @pbrbm btomic Indicbtes whether the operbtion is btomic or not.
     * @pbrbm type Request type (from SnmpDefinitions).
     *
     * @return The request-tree where the originbl vbrbind list hbs been
     *         dispbtched to the bppropribte nodes.
     */
    privbte SnmpRequestTree getHbndlers(SnmpMibRequest req,
                                        boolebn crebteflbg, boolebn btomic,
                                        int type)
        throws SnmpStbtusException {

        // Build bn empty request tree
        SnmpRequestTree hbndlers =
            new SnmpRequestTree(req,crebteflbg,type);

        int index=0;
        SnmpVbrBind vbr;
        finbl int ver= req.getVersion();

        // For ebch vbrbind in the list finds its hbndling node.
        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements(); index++) {

            vbr= e.nextElement();

            try {
                // Find the hbndling node for this vbrbind.
                root.findHbndlingNode(vbr,vbr.oid.longVblue(fblse),
                                      0,hbndlers);
            } cbtch(SnmpStbtusException x) {

                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMib.clbss.getNbme(),
                            "getHbndlers",
                            "Couldn't find b hbndling node for " +
                            vbr.oid.toString());
                }

                // If the operbtion is btomic (Check/Set) or the version
                // is V1 we must generbte bn exception.
                //
                if (ver == SnmpDefinitions.snmpVersionOne) {

                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getHbndlers", "\tV1: Throwing exception");
                    }

                    // The index in the exception must correspond to the
                    // SNMP index ...
                    //
                    finbl SnmpStbtusException sse =
                        new SnmpStbtusException(x, index + 1);
                    sse.initCbuse(x);
                    throw sse;
                } else if ((type == SnmpDefinitions.pduWblkRequest)   ||
                           (type == SnmpDefinitions.pduSetRequestPdu)) {
                    finbl int stbtus =
                        SnmpRequestTree.mbpSetException(x.getStbtus(),ver);

                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getHbndlers", "\tSET: Throwing exception");
                    }

                    finbl SnmpStbtusException sse =
                        new SnmpStbtusException(stbtus, index + 1);
                    sse.initCbuse(x);
                    throw sse;
                } else if (btomic) {

                    // Should never come here...
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getHbndlers", "\tATOMIC: Throwing exception");
                    }

                    finbl SnmpStbtusException sse =
                        new SnmpStbtusException(x, index + 1);
                    sse.initCbuse(x);
                    throw sse;
                }

                finbl int stbtus =
                    SnmpRequestTree.mbpGetException(x.getStbtus(),ver);

                if (stbtus == SnmpStbtusException.noSuchInstbnce) {

                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getHbndlers",
                                "\tGET: Registering noSuchInstbnce");
                    }

                    vbr.vblue= SnmpVbrBind.noSuchInstbnce;

                } else if (stbtus == SnmpStbtusException.noSuchObject) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getHbndlers",
                                "\tGET: Registering noSuchObject");
                    }

                        vbr.vblue= SnmpVbrBind.noSuchObject;

                } else {

                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getHbndlers",
                                "\tGET: Registering globbl error: " + stbtus);
                    }

                    finbl SnmpStbtusException sse =
                        new SnmpStbtusException(stbtus, index + 1);
                    sse.initCbuse(x);
                    throw sse;
                }
            }
        }
        return hbndlers;
    }

    /**
     * This method builds the temporbry request-tree thbt will be used to
     * perform the SNMP GET-NEXT request bssocibted with the given vector
     * of vbrbinds `list'.
     *
     * @pbrbm req The SnmpMibRequest object holding the vbrbind list
     *             concerning this MIB.
     *
     * @return The request-tree where the originbl vbrbind list hbs been
     *         dispbtched to the bppropribte nodes, bnd where the originbl
     *         OIDs hbve been replbced with the correct "next" OID.
     */
    privbte SnmpRequestTree getGetNextHbndlers(SnmpMibRequest req)
        throws SnmpStbtusException {

        // Crebtes bn empty request tree, no entry crebtion is bllowed (fblse)
        SnmpRequestTree hbndlers = new
            SnmpRequestTree(req,fblse,SnmpDefinitions.pduGetNextRequestPdu);

        // Sets the getNext flbg: if version=V2, stbtus exception bre
        // trbnsformed in  endOfMibView
        hbndlers.setGetNextFlbg();

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMib.clbss.getNbme(),
                    "getGetNextHbndlers", "Received MIB request : " + req);
        }
        AcmChecker checker = new AcmChecker(req);
        int index=0;
        SnmpVbrBind vbr = null;
        finbl int ver= req.getVersion();
        SnmpOid originbl = null;
        // For ebch vbrbind, finds the hbndling node.
        // This function hbs the side effect of trbnsforming b GET-NEXT
        // request into b vblid GET request, replbcing the OIDs in the
        // originbl GET-NEXT request with the OID of the first lebf thbt
        // follows.
        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements(); index++) {

            vbr = e.nextElement();
            SnmpOid result;
            try {
                // Find the node hbndling the OID thbt follows the vbrbind
                // OID. `result' contbins this next lebf OID.
                //ACM loop.
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMib.clbss.getNbme(),
                            "getGetNextHbndlers", " Next OID of : " + vbr.oid);
                }
                result = new SnmpOid(root.findNextHbndlingNode
                                     (vbr,vbr.oid.longVblue(fblse),0,
                                      0,hbndlers, checker));

                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMib.clbss.getNbme(),
                            "getGetNextHbndlers", " is : " + result);
                }
                // We replbce the vbrbind originbl OID with the OID of the
                // lebf object we hbve to return.
                vbr.oid = result;
            } cbtch(SnmpStbtusException x) {

                // if (isDebugOn())
                //    debug("getGetNextHbndlers",
                //        "Couldn't find b hbndling node for "
                //        + vbr.oid.toString());

                if (ver == SnmpDefinitions.snmpVersionOne) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "getGetNextHbndlers",
                                "\tThrowing exception " + x.toString());
                    }
                    // The index in the exception must correspond to the
                    // SNMP index ...
                    //
                    throw new SnmpStbtusException(x, index + 1);
                }
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMib.clbss.getNbme(),
                            "getGetNextHbndlers",
                            "Exception : " + x.getStbtus());
                }

                vbr.setSnmpVblue(SnmpVbrBind.endOfMibView);
            }
        }
        return hbndlers;
    }

    // --------------------------------------------------------------------
    // PROTECTED VARIABLES
    // --------------------------------------------------------------------

    /**
     * The top element in the Mib tree.
     * @seribl
     */
    protected SnmpMibOid root;


    // --------------------------------------------------------------------
    // PRIVATE VARIABLES
    // --------------------------------------------------------------------

    /**
     * The root object identifier of the MIB.
     */
    privbte trbnsient long[] rootOid= null;
}
