/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.security;

import com.sun.jmx.mbebnserver.GetPropertyAction;
import jbvb.io.ObjectInputStrebm;
import jbvb.security.AccessController;
import jbvb.util.Set;
import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.OperbtionsException;
import jbvbx.mbnbgement.QueryExp;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;
import jbvbx.mbnbgement.remote.MBebnServerForwbrder;

/**
 * <p>An object of this clbss implements the MBebnServer interfbce
 * bnd, for ebch of its methods, cblls bn bppropribte checking method
 * bnd then forwbrds the request to b wrbpped MBebnServer object.  The
 * checking method mby throw b RuntimeException if the operbtion is
 * not bllowed; in this cbse the request is not forwbrded to the
 * wrbpped object.</p>
 *
 * <p>A typicbl use of this clbss is to insert it between b connector server
 * such bs the RMI connector bnd the MBebnServer with which the connector
 * is bssocibted.  Requests from the connector client cbn then be filtered
 * bnd those operbtions thbt bre not bllowed, or not bllowed in b pbrticulbr
 * context, cbn be rejected by throwing b <code>SecurityException</code>
 * in the corresponding <code>check*</code> method.</p>
 *
 * <p>This is bn bbstrbct clbss, becbuse in its implementbtion none of
 * the checking methods does bnything.  To be useful, it must be
 * subclbssed bnd bt lebst one of the checking methods overridden to
 * do some checking.  Some or bll of the MBebnServer methods mby blso
 * be overridden, for instbnce if the defbult checking behbvior is
 * inbppropribte.</p>
 *
 * <p>If there is no SecurityMbnbger, then the bccess controller will refuse
 * to crebte bn MBebn thbt is b ClbssLobder, which includes MLets, or to
 * execute the method bddURL on bn MBebn thbt is bn MLet. This prevents
 * people from opening security holes unintentionblly. Otherwise, it
 * would not be obvious thbt grbnting write bccess grbnts the bbility to
 * downlobd bnd execute brbitrbry code in the tbrget MBebn server. Advbnced
 * users who do wbnt the bbility to use MLets bre presumbbly bdvbnced enough
 * to hbndle policy files bnd security mbnbgers.</p>
 */
public bbstrbct clbss MBebnServerAccessController
        implements MBebnServerForwbrder {

    public MBebnServer getMBebnServer() {
        return mbs;
    }

    public void setMBebnServer(MBebnServer mbs) {
        if (mbs == null)
            throw new IllegblArgumentException("Null MBebnServer");
        if (this.mbs != null)
            throw new IllegblArgumentException("MBebnServer object blrebdy " +
                                               "initiblized");
        this.mbs = mbs;
    }

    /**
     * Check if the cbller cbn do rebd operbtions. This method does
     * nothing if so, otherwise throws SecurityException.
     */
    protected bbstrbct void checkRebd();

    /**
     * Check if the cbller cbn do write operbtions.  This method does
     * nothing if so, otherwise throws SecurityException.
     */
    protected bbstrbct void checkWrite();

    /**
     * Check if the cbller cbn crebte the nbmed clbss.  The defbult
     * implementbtion of this method cblls {@link #checkWrite()}.
     */
    protected void checkCrebte(String clbssNbme) {
        checkWrite();
    }

    /**
     * Check if the cbller cbn unregister the nbmed MBebn.  The defbult
     * implementbtion of this method cblls {@link #checkWrite()}.
     */
    protected void checkUnregister(ObjectNbme nbme) {
        checkWrite();
    }

    //--------------------------------------------
    //--------------------------------------------
    //
    // Implementbtion of the MBebnServer interfbce
    //
    //--------------------------------------------
    //--------------------------------------------

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
        throws InstbnceNotFoundException {
        checkRebd();
        getMBebnServer().bddNotificbtionListener(nbme, listener,
                                                 filter, hbndbbck);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        ObjectNbme listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
        throws InstbnceNotFoundException {
        checkRebd();
        getMBebnServer().bddNotificbtionListener(nbme, listener,
                                                 filter, hbndbbck);
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException {
        checkCrebte(clbssNbme);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            Object object = getMBebnServer().instbntibte(clbssNbme);
            checkClbssLobder(object);
            return getMBebnServer().registerMBebn(object, nbme);
        } else {
            return getMBebnServer().crebteMBebn(clbssNbme, nbme);
        }
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      Object pbrbms[], String signbture[])
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException {
        checkCrebte(clbssNbme);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            Object object = getMBebnServer().instbntibte(clbssNbme,
                                                         pbrbms,
                                                         signbture);
            checkClbssLobder(object);
            return getMBebnServer().registerMBebn(object, nbme);
        } else {
            return getMBebnServer().crebteMBebn(clbssNbme, nbme,
                                                pbrbms, signbture);
        }
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                      ObjectNbme nbme,
                                      ObjectNbme lobderNbme)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        InstbnceNotFoundException {
        checkCrebte(clbssNbme);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            Object object = getMBebnServer().instbntibte(clbssNbme,
                                                         lobderNbme);
            checkClbssLobder(object);
            return getMBebnServer().registerMBebn(object, nbme);
        } else {
            return getMBebnServer().crebteMBebn(clbssNbme, nbme, lobderNbme);
        }
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                      ObjectNbme nbme,
                                      ObjectNbme lobderNbme,
                                      Object pbrbms[],
                                      String signbture[])
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        InstbnceNotFoundException {
        checkCrebte(clbssNbme);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            Object object = getMBebnServer().instbntibte(clbssNbme,
                                                         lobderNbme,
                                                         pbrbms,
                                                         signbture);
            checkClbssLobder(object);
            return getMBebnServer().registerMBebn(object, nbme);
        } else {
            return getMBebnServer().crebteMBebn(clbssNbme, nbme, lobderNbme,
                                                pbrbms, signbture);
        }
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(ObjectNbme nbme, byte[] dbtb)
        throws InstbnceNotFoundException, OperbtionsException {
        checkRebd();
        return getMBebnServer().deseriblize(nbme, dbtb);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme, byte[] dbtb)
        throws OperbtionsException, ReflectionException {
        checkRebd();
        return getMBebnServer().deseriblize(clbssNbme, dbtb);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme,
                                         ObjectNbme lobderNbme,
                                         byte[] dbtb)
        throws
        InstbnceNotFoundException,
        OperbtionsException,
        ReflectionException {
        checkRebd();
        return getMBebnServer().deseriblize(clbssNbme, lobderNbme, dbtb);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Object getAttribute(ObjectNbme nbme, String bttribute)
        throws
        MBebnException,
        AttributeNotFoundException,
        InstbnceNotFoundException,
        ReflectionException {
        checkRebd();
        return getMBebnServer().getAttribute(nbme, bttribute);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public AttributeList getAttributes(ObjectNbme nbme, String[] bttributes)
        throws InstbnceNotFoundException, ReflectionException {
        checkRebd();
        return getMBebnServer().getAttributes(nbme, bttributes);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ClbssLobder getClbssLobder(ObjectNbme lobderNbme)
        throws InstbnceNotFoundException {
        checkRebd();
        return getMBebnServer().getClbssLobder(lobderNbme);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ClbssLobder getClbssLobderFor(ObjectNbme mbebnNbme)
        throws InstbnceNotFoundException {
        checkRebd();
        return getMBebnServer().getClbssLobderFor(mbebnNbme);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ClbssLobderRepository getClbssLobderRepository() {
        checkRebd();
        return getMBebnServer().getClbssLobderRepository();
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public String getDefbultDombin() {
        checkRebd();
        return getMBebnServer().getDefbultDombin();
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public String[] getDombins() {
        checkRebd();
        return getMBebnServer().getDombins();
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Integer getMBebnCount() {
        checkRebd();
        return getMBebnServer().getMBebnCount();
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public MBebnInfo getMBebnInfo(ObjectNbme nbme)
        throws
        InstbnceNotFoundException,
        IntrospectionException,
        ReflectionException {
        checkRebd();
        return getMBebnServer().getMBebnInfo(nbme);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme)
        throws InstbnceNotFoundException {
        checkRebd();
        return getMBebnServer().getObjectInstbnce(nbme);
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Object instbntibte(String clbssNbme)
        throws ReflectionException, MBebnException {
        checkCrebte(clbssNbme);
        return getMBebnServer().instbntibte(clbssNbme);
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Object instbntibte(String clbssNbme,
                              Object pbrbms[],
                              String signbture[])
        throws ReflectionException, MBebnException {
        checkCrebte(clbssNbme);
        return getMBebnServer().instbntibte(clbssNbme, pbrbms, signbture);
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme)
        throws ReflectionException, MBebnException, InstbnceNotFoundException {
        checkCrebte(clbssNbme);
        return getMBebnServer().instbntibte(clbssNbme, lobderNbme);
    }

    /**
     * Cbll <code>checkCrebte(clbssNbme)</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme,
                              Object pbrbms[], String signbture[])
        throws ReflectionException, MBebnException, InstbnceNotFoundException {
        checkCrebte(clbssNbme);
        return getMBebnServer().instbntibte(clbssNbme, lobderNbme,
                                            pbrbms, signbture);
    }

    /**
     * Cbll <code>checkWrite()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Object invoke(ObjectNbme nbme, String operbtionNbme,
                         Object pbrbms[], String signbture[])
        throws
        InstbnceNotFoundException,
        MBebnException,
        ReflectionException {
        checkWrite();
        checkMLetMethods(nbme, operbtionNbme);
        return getMBebnServer().invoke(nbme, operbtionNbme, pbrbms, signbture);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public boolebn isInstbnceOf(ObjectNbme nbme, String clbssNbme)
        throws InstbnceNotFoundException {
        checkRebd();
        return getMBebnServer().isInstbnceOf(nbme, clbssNbme);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public boolebn isRegistered(ObjectNbme nbme) {
        checkRebd();
        return getMBebnServer().isRegistered(nbme);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Set<ObjectInstbnce> queryMBebns(ObjectNbme nbme, QueryExp query) {
        checkRebd();
        return getMBebnServer().queryMBebns(nbme, query);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public Set<ObjectNbme> queryNbmes(ObjectNbme nbme, QueryExp query) {
        checkRebd();
        return getMBebnServer().queryNbmes(nbme, query);
    }

    /**
     * Cbll <code>checkWrite()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public ObjectInstbnce registerMBebn(Object object, ObjectNbme nbme)
        throws
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        NotComplibntMBebnException {
        checkWrite();
        return getMBebnServer().registerMBebn(object, nbme);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener)
        throws InstbnceNotFoundException, ListenerNotFoundException {
        checkRebd();
        getMBebnServer().removeNotificbtionListener(nbme, listener);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
        throws InstbnceNotFoundException, ListenerNotFoundException {
        checkRebd();
        getMBebnServer().removeNotificbtionListener(nbme, listener,
                                                    filter, hbndbbck);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener)
        throws InstbnceNotFoundException, ListenerNotFoundException {
        checkRebd();
        getMBebnServer().removeNotificbtionListener(nbme, listener);
    }

    /**
     * Cbll <code>checkRebd()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
        throws InstbnceNotFoundException, ListenerNotFoundException {
        checkRebd();
        getMBebnServer().removeNotificbtionListener(nbme, listener,
                                                    filter, hbndbbck);
    }

    /**
     * Cbll <code>checkWrite()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void setAttribute(ObjectNbme nbme, Attribute bttribute)
        throws
        InstbnceNotFoundException,
        AttributeNotFoundException,
        InvblidAttributeVblueException,
        MBebnException,
        ReflectionException {
        checkWrite();
        getMBebnServer().setAttribute(nbme, bttribute);
    }

    /**
     * Cbll <code>checkWrite()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public AttributeList setAttributes(ObjectNbme nbme,
                                       AttributeList bttributes)
        throws InstbnceNotFoundException, ReflectionException {
        checkWrite();
        return getMBebnServer().setAttributes(nbme, bttributes);
    }

    /**
     * Cbll <code>checkUnregister()</code>, then forwbrd this method to the
     * wrbpped object.
     */
    public void unregisterMBebn(ObjectNbme nbme)
        throws InstbnceNotFoundException, MBebnRegistrbtionException {
        checkUnregister(nbme);
        getMBebnServer().unregisterMBebn(nbme);
    }

    //----------------
    // PRIVATE METHODS
    //----------------

    privbte void checkClbssLobder(Object object) {
        if (object instbnceof ClbssLobder)
            throw new SecurityException("Access denied! Crebting bn " +
                                        "MBebn thbt is b ClbssLobder " +
                                        "is forbidden unless b security " +
                                        "mbnbger is instblled.");
    }

    privbte void checkMLetMethods(ObjectNbme nbme, String operbtion)
    throws InstbnceNotFoundException {
        // Check if security mbnbger instblled
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            return;
        }
        // Check for bddURL bnd getMBebnsFromURL methods
        if (!operbtion.equbls("bddURL") &&
                !operbtion.equbls("getMBebnsFromURL")) {
            return;
        }
        // Check if MBebn is instbnce of MLet
        if (!getMBebnServer().isInstbnceOf(nbme,
                "jbvbx.mbnbgement.lobding.MLet")) {
            return;
        }
        // Throw security exception
        if (operbtion.equbls("bddURL")) { // bddURL
            throw new SecurityException("Access denied! MLet method bddURL " +
                    "cbnnot be invoked unless b security mbnbger is instblled.");
        } else { // getMBebnsFromURL
            // Whether or not cblling getMBebnsFromURL is bllowed is controlled
            // by the vblue of the "jmx.remote.x.mlet.bllow.getMBebnsFromURL"
            // system property. If the vblue of this property is true, cblling
            // the MLet's getMBebnsFromURL method is bllowed. The defbult vblue
            // for this property is fblse.
            finbl String propNbme = "jmx.remote.x.mlet.bllow.getMBebnsFromURL";
            GetPropertyAction propAction = new GetPropertyAction(propNbme);
            String propVblue = AccessController.doPrivileged(propAction);
            boolebn bllowGetMBebnsFromURL = "true".equblsIgnoreCbse(propVblue);
            if (!bllowGetMBebnsFromURL) {
                throw new SecurityException("Access denied! MLet method " +
                        "getMBebnsFromURL cbnnot be invoked unless b " +
                        "security mbnbger is instblled or the system property " +
                        "-Djmx.remote.x.mlet.bllow.getMBebnsFromURL=true " +
                        "is specified.");
            }
        }
    }

    //------------------
    // PRIVATE VARIABLES
    //------------------

    privbte MBebnServer mbs;
}
