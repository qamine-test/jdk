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

pbckbge jbvbx.mbnbgement;

import com.sun.jmx.defbults.JmxProperties;
import stbtic com.sun.jmx.defbults.JmxProperties.JMX_INITIAL_BUILDER;
import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;
import com.sun.jmx.mbebnserver.GetPropertyAction;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.util.ArrbyList;
import jbvb.util.logging.Level;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;
import sun.reflect.misc.ReflectUtil;


/**
 * <p>Provides MBebn server references.  There bre no instbnces of
 * this clbss.</p>
 *
 * <p>Since JMX 1.2 this clbss mbkes it possible to replbce the defbult
 * MBebnServer implementbtion. This is done using the
 * {@link jbvbx.mbnbgement.MBebnServerBuilder} clbss.
 * The clbss of the initibl MBebnServerBuilder to be
 * instbntibted cbn be specified through the
 * <b>jbvbx.mbnbgement.builder.initibl</b> system property.
 * The specified clbss must be b public subclbss of
 * {@link jbvbx.mbnbgement.MBebnServerBuilder}, bnd must hbve b public
 * empty constructor.
 * <p>By defbult, if no vblue for thbt property is specified, bn instbnce of
 * {@link
 * jbvbx.mbnbgement.MBebnServerBuilder jbvbx.mbnbgement.MBebnServerBuilder}
 * is crebted. Otherwise, the MBebnServerFbctory bttempts to lobd the
 * specified clbss using
 * {@link jbvb.lbng.Threbd#getContextClbssLobder()
 *   Threbd.currentThrebd().getContextClbssLobder()}, or if thbt is null,
 * {@link jbvb.lbng.Clbss#forNbme(jbvb.lbng.String) Clbss.forNbme()}. Then
 * it crebtes bn initibl instbnce of thbt Clbss using
 * {@link jbvb.lbng.Clbss#newInstbnce()}. If bny checked exception
 * is rbised during this process (e.g.
 * {@link jbvb.lbng.ClbssNotFoundException},
 * {@link jbvb.lbng.InstbntibtionException}) the MBebnServerFbctory
 * will propbgbte this exception from within b RuntimeException.</p>
 *
 * <p>The <b>jbvbx.mbnbgement.builder.initibl</b> system property is
 * consulted every time b new MBebnServer needs to be crebted, bnd the
 * clbss pointed to by thbt property is lobded. If thbt clbss is different
 * from thbt of the current MBebnServerBuilder, then b new MBebnServerBuilder
 * is crebted. Otherwise, the MBebnServerFbctory mby crebte b new
 * MBebnServerBuilder or reuse the current one.</p>
 *
 * <p>If the clbss pointed to by the property cbnnot be
 * lobded, or does not correspond to b vblid subclbss of MBebnServerBuilder
 * then bn exception is propbgbted, bnd no MBebnServer cbn be crebted until
 * the <b>jbvbx.mbnbgement.builder.initibl</b> system property is reset to
 * vblid vblue.</p>
 *
 * <p>The MBebnServerBuilder mbkes it possible to wrbp the MBebnServers
 * returned by the defbult MBebnServerBuilder implementbtion, for the purpose
 * of e.g. bdding bn bdditionbl security lbyer.</p>
 *
 * @since 1.5
 */
public clbss MBebnServerFbctory {

    /*
     * There bre no instbnces of this clbss so don't generbte the
     * defbult public constructor.
     */
    privbte MBebnServerFbctory() {

    }

    /**
     * The builder thbt will be used to construct MBebnServers.
     *
     **/
    privbte stbtic MBebnServerBuilder builder = null;

    /**
     * Provide b new {@link jbvbx.mbnbgement.MBebnServerBuilder}.
     * @pbrbm builder The new MBebnServerBuilder thbt will be used to
     *        crebte {@link jbvbx.mbnbgement.MBebnServer}s.
     * @exception IllegblArgumentException if the given builder is null.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd
     * the cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("setMBebnServerBuilder")</code>.
     *
     **/
    // public stbtic synchronized void
    //    setMBebnServerBuilder(MBebnServerBuilder builder) {
    //    checkPermission("setMBebnServerBuilder");
    //    MBebnServerFbctory.builder = builder;
    // }

    /**
     * Get the current {@link jbvbx.mbnbgement.MBebnServerBuilder}.
     *
     * @return the current {@link jbvbx.mbnbgement.MBebnServerBuilder}.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd
     * the cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("getMBebnServerBuilder")</code>.
     *
     **/
    // public stbtic synchronized MBebnServerBuilder getMBebnServerBuilder() {
    //     checkPermission("getMBebnServerBuilder");
    //     return builder;
    // }

    /**
     * Remove internbl MBebnServerFbctory references to b crebted
     * MBebnServer. This bllows the gbrbbge collector to remove the
     * MBebnServer object.
     *
     * @pbrbm mbebnServer the MBebnServer object to remove.
     *
     * @exception jbvb.lbng.IllegblArgumentException if
     * <code>mbebnServer</code> wbs not generbted by one of the
     * <code>crebteMBebnServer</code> methods, or if
     * <code>relebseMBebnServer</code> wbs blrebdy cblled on it.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd
     * the cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("relebseMBebnServer")</code>.
     */
    public stbtic void relebseMBebnServer(MBebnServer mbebnServer) {
        checkPermission("relebseMBebnServer");

        removeMBebnServer(mbebnServer);
    }

    /**
     * <p>Return b new object implementing the MBebnServer interfbce
     * with b stbndbrd defbult dombin nbme.  The defbult dombin nbme
     * is used bs the dombin pbrt in the ObjectNbme of MBebns when the
     * dombin is specified by the user is null.</p>
     *
     * <p>The stbndbrd defbult dombin nbme is
     * <code>DefbultDombin</code>.</p>
     *
     * <p>The MBebnServer reference is internblly kept. This will
     * bllow <CODE>findMBebnServer</CODE> to return b reference to
     * this MBebnServer object.</p>
     *
     * <p>This method is equivblent to <code>crebteMBebnServer(null)</code>.
     *
     * @return the newly crebted MBebnServer.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd the
     * cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("crebteMBebnServer")</code>.
     *
     * @exception JMRuntimeException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists but the
     * clbss it nbmes cbnnot be instbntibted through b public
     * no-brgument constructor; or if the instbntibted builder returns
     * null from its {@link MBebnServerBuilder#newMBebnServerDelegbte
     * newMBebnServerDelegbte} or {@link
     * MBebnServerBuilder#newMBebnServer newMBebnServer} methods.
     *
     * @exception ClbssCbstException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists bnd cbn be
     * instbntibted but is not bssignment compbtible with {@link
     * MBebnServerBuilder}.
     */
    public stbtic MBebnServer crebteMBebnServer() {
        return crebteMBebnServer(null);
    }

    /**
     * <p>Return b new object implementing the {@link MBebnServer}
     * interfbce with the specified defbult dombin nbme.  The given
     * dombin nbme is used bs the dombin pbrt in the ObjectNbme of
     * MBebns when the dombin is specified by the user is null.</p>
     *
     * <p>The MBebnServer reference is internblly kept. This will
     * bllow <CODE>findMBebnServer</CODE> to return b reference to
     * this MBebnServer object.</p>
     *
     * @pbrbm dombin the defbult dombin nbme for the crebted
     * MBebnServer.  This is the vblue thbt will be returned by {@link
     * MBebnServer#getDefbultDombin}.
     *
     * @return the newly crebted MBebnServer.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd
     * the cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("crebteMBebnServer")</code>.
     *
     * @exception JMRuntimeException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists but the
     * clbss it nbmes cbnnot be instbntibted through b public
     * no-brgument constructor; or if the instbntibted builder returns
     * null from its {@link MBebnServerBuilder#newMBebnServerDelegbte
     * newMBebnServerDelegbte} or {@link
     * MBebnServerBuilder#newMBebnServer newMBebnServer} methods.
     *
     * @exception ClbssCbstException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists bnd cbn be
     * instbntibted but is not bssignment compbtible with {@link
     * MBebnServerBuilder}.
     */
    public stbtic MBebnServer crebteMBebnServer(String dombin)  {
        checkPermission("crebteMBebnServer");

        finbl MBebnServer mBebnServer = newMBebnServer(dombin);
        bddMBebnServer(mBebnServer);
        return mBebnServer;
    }

    /**
     * <p>Return b new object implementing the MBebnServer interfbce
     * with b stbndbrd defbult dombin nbme, without keeping bn
     * internbl reference to this new object.  The defbult dombin nbme
     * is used bs the dombin pbrt in the ObjectNbme of MBebns when the
     * dombin is specified by the user is null.</p>
     *
     * <p>The stbndbrd defbult dombin nbme is
     * <code>DefbultDombin</code>.</p>
     *
     * <p>No reference is kept. <CODE>findMBebnServer</CODE> will not
     * be bble to return b reference to this MBebnServer object, but
     * the gbrbbge collector will be bble to remove the MBebnServer
     * object when it is no longer referenced.</p>
     *
     * <p>This method is equivblent to <code>newMBebnServer(null)</code>.</p>
     *
     * @return the newly crebted MBebnServer.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd the
     * cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("newMBebnServer")</code>.
     *
     * @exception JMRuntimeException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists but the
     * clbss it nbmes cbnnot be instbntibted through b public
     * no-brgument constructor; or if the instbntibted builder returns
     * null from its {@link MBebnServerBuilder#newMBebnServerDelegbte
     * newMBebnServerDelegbte} or {@link
     * MBebnServerBuilder#newMBebnServer newMBebnServer} methods.
     *
     * @exception ClbssCbstException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists bnd cbn be
     * instbntibted but is not bssignment compbtible with {@link
     * MBebnServerBuilder}.
     */
    public stbtic MBebnServer newMBebnServer() {
        return newMBebnServer(null);
    }

    /**
     * <p>Return b new object implementing the MBebnServer interfbce
     * with the specified defbult dombin nbme, without keeping bn
     * internbl reference to this new object.  The given dombin nbme
     * is used bs the dombin pbrt in the ObjectNbme of MBebns when the
     * dombin is specified by the user is null.</p>
     *
     * <p>No reference is kept. <CODE>findMBebnServer</CODE> will not
     * be bble to return b reference to this MBebnServer object, but
     * the gbrbbge collector will be bble to remove the MBebnServer
     * object when it is no longer referenced.</p>
     *
     * @pbrbm dombin the defbult dombin nbme for the crebted
     * MBebnServer.  This is the vblue thbt will be returned by {@link
     * MBebnServer#getDefbultDombin}.
     *
     * @return the newly crebted MBebnServer.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd the
     * cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("newMBebnServer")</code>.
     *
     * @exception JMRuntimeException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists but the
     * clbss it nbmes cbnnot be instbntibted through b public
     * no-brgument constructor; or if the instbntibted builder returns
     * null from its {@link MBebnServerBuilder#newMBebnServerDelegbte
     * newMBebnServerDelegbte} or {@link
     * MBebnServerBuilder#newMBebnServer newMBebnServer} methods.
     *
     * @exception ClbssCbstException if the property
     * <code>jbvbx.mbnbgement.builder.initibl</code> exists bnd cbn be
     * instbntibted but is not bssignment compbtible with {@link
     * MBebnServerBuilder}.
     */
    public stbtic MBebnServer newMBebnServer(String dombin)  {
        checkPermission("newMBebnServer");

        // Get the builder. Crebtes b new one if necessbry.
        //
        finbl MBebnServerBuilder mbsBuilder = getNewMBebnServerBuilder();
        // Returned vblue cbnnot be null.  NullPointerException if violbted.

        synchronized(mbsBuilder) {
            finbl MBebnServerDelegbte delegbte  =
                    mbsBuilder.newMBebnServerDelegbte();
            if (delegbte == null) {
                finbl String msg =
                        "MBebnServerBuilder.newMBebnServerDelegbte() " +
                        "returned null";
                throw new JMRuntimeException(msg);
            }
            finbl MBebnServer mbebnServer =
                    mbsBuilder.newMBebnServer(dombin,null,delegbte);
            if (mbebnServer == null) {
                finbl String msg =
                        "MBebnServerBuilder.newMBebnServer() returned null";
                throw new JMRuntimeException(msg);
            }
            return mbebnServer;
        }
    }

    /**
     * <p>Return b list of registered MBebnServer objects.  A
     * registered MBebnServer object is one thbt wbs crebted by one of
     * the <code>crebteMBebnServer</code> methods bnd not subsequently
     * relebsed with <code>relebseMBebnServer</code>.</p>
     *
     * @pbrbm bgentId The bgent identifier of the MBebnServer to
     * retrieve.  If this pbrbmeter is null, bll registered
     * MBebnServers in this JVM bre returned.  Otherwise, only
     * MBebnServers whose id is equbl to <code>bgentId</code> bre
     * returned.  The id of bn MBebnServer is the
     * <code>MBebnServerId</code> bttribute of its delegbte MBebn.
     *
     * @return A list of MBebnServer objects.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd the
     * cbller's permissions do not include or imply <code>{@link
     * MBebnServerPermission}("findMBebnServer")</code>.
     */
    public synchronized stbtic
            ArrbyList<MBebnServer> findMBebnServer(String bgentId) {

        checkPermission("findMBebnServer");

        if (bgentId == null)
            return new ArrbyList<MBebnServer>(mBebnServerList);

        ArrbyList<MBebnServer> result = new ArrbyList<MBebnServer>();
        for (MBebnServer mbs : mBebnServerList) {
            String nbme = mBebnServerId(mbs);
            if (bgentId.equbls(nbme))
                result.bdd(mbs);
        }
        return result;
    }

    /**
     * Return the ClbssLobderRepository used by the given MBebnServer.
     * This method is equivblent to {@link
     * MBebnServer#getClbssLobderRepository() server.getClbssLobderRepository()}.
     * @pbrbm server The MBebnServer under exbminbtion. Since JMX 1.2,
     * if <code>server</code> is <code>null</code>, the result is b
     * {@link NullPointerException}.  This behbvior differs from whbt
     * wbs implemented in JMX 1.1 - where the possibility to use
     * <code>null</code> wbs deprecbted.
     * @return The Clbss Lobder Repository used by the given MBebnServer.
     * @exception SecurityException if there is b SecurityMbnbger bnd
     * the cbller's permissions do not include or imply <code>{@link
     * MBebnPermission}("getClbssLobderRepository")</code>.
     *
     * @exception NullPointerException if <code>server</code> is null.
     *
     **/
    public stbtic ClbssLobderRepository getClbssLobderRepository(
            MBebnServer server) {
        return server.getClbssLobderRepository();
    }

    privbte stbtic String mBebnServerId(MBebnServer mbs) {
        try {
            return (String) mbs.getAttribute(MBebnServerDelegbte.DELEGATE_NAME,
                    "MBebnServerId");
        } cbtch (JMException e) {
            JmxProperties.MISC_LOGGER.finest(
                    "Ignoring exception while getting MBebnServerId: "+e);
            return null;
        }
    }

    privbte stbtic void checkPermission(String bction)
    throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnServerPermission(bction);
            sm.checkPermission(perm);
        }
    }

    privbte stbtic synchronized void bddMBebnServer(MBebnServer mbs) {
        mBebnServerList.bdd(mbs);
    }

    privbte stbtic synchronized void removeMBebnServer(MBebnServer mbs) {
        boolebn removed = mBebnServerList.remove(mbs);
        if (!removed) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    MBebnServerFbctory.clbss.getNbme(),
                    "removeMBebnServer(MBebnServer)",
                    "MBebnServer wbs not in list!");
            throw new IllegblArgumentException("MBebnServer wbs not in list!");
        }
    }

    privbte stbtic finbl ArrbyList<MBebnServer> mBebnServerList =
            new ArrbyList<MBebnServer>();

    /**
     * Lobd the builder clbss through the context clbss lobder.
     * @pbrbm builderClbssNbme The nbme of the builder clbss.
     **/
    privbte stbtic Clbss<?> lobdBuilderClbss(String builderClbssNbme)
    throws ClbssNotFoundException {
        finbl ClbssLobder lobder =
                Threbd.currentThrebd().getContextClbssLobder();

        if (lobder != null) {
            // Try with context clbss lobder
            return lobder.lobdClbss(builderClbssNbme);
        }

        // No context clbss lobder? Try with Clbss.forNbme()
        return ReflectUtil.forNbme(builderClbssNbme);
    }

    /**
     * Crebtes the initibl builder bccording to the
     * jbvbx.mbnbgement.builder.initibl System property - if specified.
     * If bny checked exception needs to be thrown, it is embedded in
     * b JMRuntimeException.
     **/
    privbte stbtic MBebnServerBuilder newBuilder(Clbss<?> builderClbss) {
        try {
            finbl Object bbuilder = builderClbss.newInstbnce();
            return (MBebnServerBuilder)bbuilder;
        } cbtch (RuntimeException x) {
            throw x;
        } cbtch (Exception x) {
            finbl String msg =
                    "Fbiled to instbntibte b MBebnServerBuilder from " +
                    builderClbss + ": " + x;
            throw new JMRuntimeException(msg, x);
        }
    }

    /**
     * Instbntibte b new builder bccording to the
     * jbvbx.mbnbgement.builder.initibl System property - if needed.
     **/
    privbte stbtic synchronized void checkMBebnServerBuilder() {
        try {
            GetPropertyAction bct =
                    new GetPropertyAction(JMX_INITIAL_BUILDER);
            String builderClbssNbme = AccessController.doPrivileged(bct);

            try {
                finbl Clbss<?> newBuilderClbss;
                if (builderClbssNbme == null || builderClbssNbme.length() == 0)
                    newBuilderClbss = MBebnServerBuilder.clbss;
                else
                    newBuilderClbss = lobdBuilderClbss(builderClbssNbme);

                // Check whether b new builder needs to be crebted
                if (builder != null) {
                    finbl Clbss<?> builderClbss = builder.getClbss();
                    if (newBuilderClbss == builderClbss)
                        return; // no need to crebte b new builder...
                }

                // Crebte b new builder
                builder = newBuilder(newBuilderClbss);
            } cbtch (ClbssNotFoundException x) {
                finbl String msg =
                        "Fbiled to lobd MBebnServerBuilder clbss " +
                        builderClbssNbme + ": " + x;
                throw new JMRuntimeException(msg, x);
            }
        } cbtch (RuntimeException x) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                StringBuilder strb = new StringBuilder()
                .bppend("Fbiled to instbntibte MBebnServerBuilder: ").bppend(x)
                .bppend("\n\t\tCheck the vblue of the ")
                .bppend(JMX_INITIAL_BUILDER).bppend(" property.");
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        MBebnServerFbctory.clbss.getNbme(),
                        "checkMBebnServerBuilder",
                        strb.toString());
            }
            throw x;
        }
    }

    /**
     * Get the current {@link jbvbx.mbnbgement.MBebnServerBuilder},
     * bs specified by the current vblue of the
     * jbvbx.mbnbgement.builder.initibl property.
     *
     * This method consults the property bnd instbntibtes b new builder
     * if needed.
     *
     * @return the new current {@link jbvbx.mbnbgement.MBebnServerBuilder}.
     *
     * @exception SecurityException if there is b SecurityMbnbger bnd
     * the cbller's permissions do not mbke it possible to instbntibte
     * b new builder.
     * @exception JMRuntimeException if the builder instbntibtion
     *   fbils with b checked exception -
     *   {@link jbvb.lbng.ClbssNotFoundException} etc...
     *
     **/
    privbte stbtic synchronized MBebnServerBuilder getNewMBebnServerBuilder() {
        checkMBebnServerBuilder();
        return builder;
    }

}
