/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;
import jbvbx.mbnbgement.DynbmicMBebn;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.MBebnServerFbctory;
import jbvbx.mbnbgement.MBebnServerPermission;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.StbndbrdEmitterMBebn;
import jbvbx.mbnbgement.StbndbrdMBebn;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Mbp;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvbx.mbnbgement.JMX;
import sun.mbnbgement.MbnbgementFbctoryHelper;

/**
 * The {@code MbnbgementFbctory} clbss is b fbctory clbss for getting
 * mbnbged bebns for the Jbvb plbtform.
 * This clbss consists of stbtic methods ebch of which returns
 * one or more <i>plbtform MXBebns</i> representing
 * the mbnbgement interfbce of b component of the Jbvb virtubl
 * mbchine.
 *
 * <h3><b nbme="MXBebn">Plbtform MXBebns</b></h3>
 * <p>
 * A plbtform MXBebn is b <i>mbnbged bebn</i> thbt
 * conforms to the <b href="../../../jbvbx/mbnbgement/pbckbge-summbry.html">JMX</b>
 * Instrumentbtion Specificbtion bnd only uses b set of bbsic dbtb types.
 * A JMX mbnbgement bpplicbtion bnd the {@linkplbin
 * #getPlbtformMBebnServer plbtform MBebnServer}
 * cbn interoperbte without requiring clbsses for MXBebn specific
 * dbtb types.
 * The dbtb types being trbnsmitted between the JMX connector
 * server bnd the connector client bre
 * {@linkplbin jbvbx.mbnbgement.openmbebn.OpenType open types}
 * bnd this bllows interoperbtion bcross versions.
 * See <b href="../../../jbvbx/mbnbgement/MXBebn.html#MXBebn-spec">
 * the specificbtion of MXBebns</b> for detbils.
 *
 * <b nbme="MXBebnNbmes"></b>
 * <p>Ebch plbtform MXBebn is b {@link PlbtformMbnbgedObject}
 * bnd it hbs b unique
 * {@link jbvbx.mbnbgement.ObjectNbme ObjectNbme} for
 * registrbtion in the plbtform {@code MBebnServer} bs returned by
 * by the {@link PlbtformMbnbgedObject#getObjectNbme getObjectNbme}
 * method.
 *
 * <p>
 * An bpplicbtion cbn bccess b plbtform MXBebn in the following wbys:
 * <h4>1. Direct bccess to bn MXBebn interfbce</h4>
 * <blockquote>
 * <ul>
 *     <li>Get bn MXBebn instbnce by cblling the
 *         {@link #getPlbtformMXBebn(Clbss) getPlbtformMXBebn} or
 *         {@link #getPlbtformMXBebns(Clbss) getPlbtformMXBebns} method
 *         bnd bccess the MXBebn locblly in the running
 *         virtubl mbchine.
 *         </li>
 *     <li>Construct bn MXBebn proxy instbnce thbt forwbrds the
 *         method cblls to b given {@link MBebnServer MBebnServer} by cblling
 *         the {@link #getPlbtformMXBebn(MBebnServerConnection, Clbss)} or
 *         {@link #getPlbtformMXBebns(MBebnServerConnection, Clbss)} method.
 *         The {@link #newPlbtformMXBebnProxy newPlbtformMXBebnProxy} method
 *         cbn blso be used to construct bn MXBebn proxy instbnce of
 *         b given {@code ObjectNbme}.
 *         A proxy is typicblly constructed to remotely bccess
 *         bn MXBebn of bnother running virtubl mbchine.
 *         </li>
 * </ul>
 * <h4>2. Indirect bccess to bn MXBebn interfbce vib MBebnServer</h4>
 * <ul>
 *     <li>Go through the plbtform {@code MBebnServer} to bccess MXBebns
 *         locblly or b specific <tt>MBebnServerConnection</tt> to bccess
 *         MXBebns remotely.
 *         The bttributes bnd operbtions of bn MXBebn use only
 *         <em>JMX open types</em> which include bbsic dbtb types,
 *         {@link jbvbx.mbnbgement.openmbebn.CompositeDbtb CompositeDbtb},
 *         bnd {@link jbvbx.mbnbgement.openmbebn.TbbulbrDbtb TbbulbrDbtb}
 *         defined in
 *         {@link jbvbx.mbnbgement.openmbebn.OpenType OpenType}.
 *         The mbpping is specified in
 *         the {@linkplbin jbvbx.mbnbgement.MXBebn MXBebn} specificbtion
 *         for detbils.
 *        </li>
 * </ul>
 * </blockquote>
 *
 * <p>
 * The {@link #getPlbtformMbnbgementInterfbces getPlbtformMbnbgementInterfbces}
 * method returns bll mbnbgement interfbces supported in the Jbvb virtubl mbchine
 * including the stbndbrd mbnbgement interfbces listed in the tbbles
 * below bs well bs the mbnbgement interfbces extended by the JDK implementbtion.
 * <p>
 * A Jbvb virtubl mbchine hbs b single instbnce of the following mbnbgement
 * interfbces:
 *
 * <blockquote>
 * <tbble border summbry="The list of Mbnbgement Interfbces bnd their single instbnces">
 * <tr>
 * <th>Mbnbgement Interfbce</th>
 * <th>ObjectNbme</th>
 * </tr>
 * <tr>
 * <td> {@link ClbssLobdingMXBebn} </td>
 * <td> {@link #CLASS_LOADING_MXBEAN_NAME
 *             jbvb.lbng:type=ClbssLobding}</td>
 * </tr>
 * <tr>
 * <td> {@link MemoryMXBebn} </td>
 * <td> {@link #MEMORY_MXBEAN_NAME
 *             jbvb.lbng:type=Memory}</td>
 * </tr>
 * <tr>
 * <td> {@link ThrebdMXBebn} </td>
 * <td> {@link #THREAD_MXBEAN_NAME
 *             jbvb.lbng:type=Threbding}</td>
 * </tr>
 * <tr>
 * <td> {@link RuntimeMXBebn} </td>
 * <td> {@link #RUNTIME_MXBEAN_NAME
 *             jbvb.lbng:type=Runtime}</td>
 * </tr>
 * <tr>
 * <td> {@link OperbtingSystemMXBebn} </td>
 * <td> {@link #OPERATING_SYSTEM_MXBEAN_NAME
 *             jbvb.lbng:type=OperbtingSystem}</td>
 * </tr>
 * <tr>
 * <td> {@link PlbtformLoggingMXBebn} </td>
 * <td> {@link jbvb.util.logging.LogMbnbger#LOGGING_MXBEAN_NAME
 *             jbvb.util.logging:type=Logging}</td>
 * </tr>
 * </tbble>
 * </blockquote>
 *
 * <p>
 * A Jbvb virtubl mbchine hbs zero or b single instbnce of
 * the following mbnbgement interfbces.
 *
 * <blockquote>
 * <tbble border summbry="The list of Mbnbgement Interfbces bnd their single instbnces">
 * <tr>
 * <th>Mbnbgement Interfbce</th>
 * <th>ObjectNbme</th>
 * </tr>
 * <tr>
 * <td> {@link CompilbtionMXBebn} </td>
 * <td> {@link #COMPILATION_MXBEAN_NAME
 *             jbvb.lbng:type=Compilbtion}</td>
 * </tr>
 * </tbble>
 * </blockquote>
 *
 * <p>
 * A Jbvb virtubl mbchine mby hbve one or more instbnces of the following
 * mbnbgement interfbces.
 * <blockquote>
 * <tbble border summbry="The list of Mbnbgement Interfbces bnd their single instbnces">
 * <tr>
 * <th>Mbnbgement Interfbce</th>
 * <th>ObjectNbme</th>
 * </tr>
 * <tr>
 * <td> {@link GbrbbgeCollectorMXBebn} </td>
 * <td> {@link #GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE
 *             jbvb.lbng:type=GbrbbgeCollector}<tt>,nbme=</tt><i>collector's nbme</i></td>
 * </tr>
 * <tr>
 * <td> {@link MemoryMbnbgerMXBebn} </td>
 * <td> {@link #MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE
 *             jbvb.lbng:type=MemoryMbnbger}<tt>,nbme=</tt><i>mbnbger's nbme</i></td>
 * </tr>
 * <tr>
 * <td> {@link MemoryPoolMXBebn} </td>
 * <td> {@link #MEMORY_POOL_MXBEAN_DOMAIN_TYPE
 *             jbvb.lbng:type=MemoryPool}<tt>,nbme=</tt><i>pool's nbme</i></td>
 * </tr>
 * <tr>
 * <td> {@link BufferPoolMXBebn} </td>
 * <td> {@code jbvb.nio:type=BufferPool,nbme=}<i>pool nbme</i></td>
 * </tr>
 * </tbble>
 * </blockquote>
 *
 * @see <b href="../../../jbvbx/mbnbgement/pbckbge-summbry.html">
 *      JMX Specificbtion</b>
 * @see <b href="pbckbge-summbry.html#exbmples">
 *      Wbys to Access Mbnbgement Metrics</b>
 * @see jbvbx.mbnbgement.MXBebn
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
public clbss MbnbgementFbctory {
    // A clbss with only stbtic fields bnd methods.
    privbte MbnbgementFbctory() {};

    /**
     * String representbtion of the
     * <tt>ObjectNbme</tt> for the {@link ClbssLobdingMXBebn}.
     */
    public finbl stbtic String CLASS_LOADING_MXBEAN_NAME =
        "jbvb.lbng:type=ClbssLobding";

    /**
     * String representbtion of the
     * <tt>ObjectNbme</tt> for the {@link CompilbtionMXBebn}.
     */
    public finbl stbtic String COMPILATION_MXBEAN_NAME =
        "jbvb.lbng:type=Compilbtion";

    /**
     * String representbtion of the
     * <tt>ObjectNbme</tt> for the {@link MemoryMXBebn}.
     */
    public finbl stbtic String MEMORY_MXBEAN_NAME =
        "jbvb.lbng:type=Memory";

    /**
     * String representbtion of the
     * <tt>ObjectNbme</tt> for the {@link OperbtingSystemMXBebn}.
     */
    public finbl stbtic String OPERATING_SYSTEM_MXBEAN_NAME =
        "jbvb.lbng:type=OperbtingSystem";

    /**
     * String representbtion of the
     * <tt>ObjectNbme</tt> for the {@link RuntimeMXBebn}.
     */
    public finbl stbtic String RUNTIME_MXBEAN_NAME =
        "jbvb.lbng:type=Runtime";

    /**
     * String representbtion of the
     * <tt>ObjectNbme</tt> for the {@link ThrebdMXBebn}.
     */
    public finbl stbtic String THREAD_MXBEAN_NAME =
        "jbvb.lbng:type=Threbding";

    /**
     * The dombin nbme bnd the type key property in
     * the <tt>ObjectNbme</tt> for b {@link GbrbbgeCollectorMXBebn}.
     * The unique <tt>ObjectNbme</tt> for b <tt>GbrbbgeCollectorMXBebn</tt>
     * cbn be formed by bppending this string with
     * "<tt>,nbme=</tt><i>collector's nbme</i>".
     */
    public finbl stbtic String GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE =
        "jbvb.lbng:type=GbrbbgeCollector";

    /**
     * The dombin nbme bnd the type key property in
     * the <tt>ObjectNbme</tt> for b {@link MemoryMbnbgerMXBebn}.
     * The unique <tt>ObjectNbme</tt> for b <tt>MemoryMbnbgerMXBebn</tt>
     * cbn be formed by bppending this string with
     * "<tt>,nbme=</tt><i>mbnbger's nbme</i>".
     */
    public finbl stbtic String MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE=
        "jbvb.lbng:type=MemoryMbnbger";

    /**
     * The dombin nbme bnd the type key property in
     * the <tt>ObjectNbme</tt> for b {@link MemoryPoolMXBebn}.
     * The unique <tt>ObjectNbme</tt> for b <tt>MemoryPoolMXBebn</tt>
     * cbn be formed by bppending this string with
     * <tt>,nbme=</tt><i>pool's nbme</i>.
     */
    public finbl stbtic String MEMORY_POOL_MXBEAN_DOMAIN_TYPE=
        "jbvb.lbng:type=MemoryPool";

    /**
     * Returns the mbnbged bebn for the clbss lobding system of
     * the Jbvb virtubl mbchine.
     *
     * @return b {@link ClbssLobdingMXBebn} object for
     * the Jbvb virtubl mbchine.
     */
    public stbtic ClbssLobdingMXBebn getClbssLobdingMXBebn() {
        return MbnbgementFbctoryHelper.getClbssLobdingMXBebn();
    }

    /**
     * Returns the mbnbged bebn for the memory system of
     * the Jbvb virtubl mbchine.
     *
     * @return b {@link MemoryMXBebn} object for the Jbvb virtubl mbchine.
     */
    public stbtic MemoryMXBebn getMemoryMXBebn() {
        return MbnbgementFbctoryHelper.getMemoryMXBebn();
    }

    /**
     * Returns the mbnbged bebn for the threbd system of
     * the Jbvb virtubl mbchine.
     *
     * @return b {@link ThrebdMXBebn} object for the Jbvb virtubl mbchine.
     */
    public stbtic ThrebdMXBebn getThrebdMXBebn() {
        return MbnbgementFbctoryHelper.getThrebdMXBebn();
    }

    /**
     * Returns the mbnbged bebn for the runtime system of
     * the Jbvb virtubl mbchine.
     *
     * @return b {@link RuntimeMXBebn} object for the Jbvb virtubl mbchine.

     */
    public stbtic RuntimeMXBebn getRuntimeMXBebn() {
        return MbnbgementFbctoryHelper.getRuntimeMXBebn();
    }

    /**
     * Returns the mbnbged bebn for the compilbtion system of
     * the Jbvb virtubl mbchine.  This method returns <tt>null</tt>
     * if the Jbvb virtubl mbchine hbs no compilbtion system.
     *
     * @return b {@link CompilbtionMXBebn} object for the Jbvb virtubl
     *   mbchine or <tt>null</tt> if the Jbvb virtubl mbchine hbs
     *   no compilbtion system.
     */
    public stbtic CompilbtionMXBebn getCompilbtionMXBebn() {
        return MbnbgementFbctoryHelper.getCompilbtionMXBebn();
    }

    /**
     * Returns the mbnbged bebn for the operbting system on which
     * the Jbvb virtubl mbchine is running.
     *
     * @return bn {@link OperbtingSystemMXBebn} object for
     * the Jbvb virtubl mbchine.
     */
    public stbtic OperbtingSystemMXBebn getOperbtingSystemMXBebn() {
        return MbnbgementFbctoryHelper.getOperbtingSystemMXBebn();
    }

    /**
     * Returns b list of {@link MemoryPoolMXBebn} objects in the
     * Jbvb virtubl mbchine.
     * The Jbvb virtubl mbchine cbn hbve one or more memory pools.
     * It mby bdd or remove memory pools during execution.
     *
     * @return b list of <tt>MemoryPoolMXBebn</tt> objects.
     *
     */
    public stbtic List<MemoryPoolMXBebn> getMemoryPoolMXBebns() {
        return MbnbgementFbctoryHelper.getMemoryPoolMXBebns();
    }

    /**
     * Returns b list of {@link MemoryMbnbgerMXBebn} objects
     * in the Jbvb virtubl mbchine.
     * The Jbvb virtubl mbchine cbn hbve one or more memory mbnbgers.
     * It mby bdd or remove memory mbnbgers during execution.
     *
     * @return b list of <tt>MemoryMbnbgerMXBebn</tt> objects.
     *
     */
    public stbtic List<MemoryMbnbgerMXBebn> getMemoryMbnbgerMXBebns() {
        return MbnbgementFbctoryHelper.getMemoryMbnbgerMXBebns();
    }


    /**
     * Returns b list of {@link GbrbbgeCollectorMXBebn} objects
     * in the Jbvb virtubl mbchine.
     * The Jbvb virtubl mbchine mby hbve one or more
     * <tt>GbrbbgeCollectorMXBebn</tt> objects.
     * It mby bdd or remove <tt>GbrbbgeCollectorMXBebn</tt>
     * during execution.
     *
     * @return b list of <tt>GbrbbgeCollectorMXBebn</tt> objects.
     *
     */
    public stbtic List<GbrbbgeCollectorMXBebn> getGbrbbgeCollectorMXBebns() {
        return MbnbgementFbctoryHelper.getGbrbbgeCollectorMXBebns();
    }

    privbte stbtic MBebnServer plbtformMBebnServer;
    /**
     * Returns the plbtform {@link jbvbx.mbnbgement.MBebnServer MBebnServer}.
     * On the first cbll to this method, it first crebtes the plbtform
     * {@code MBebnServer} by cblling the
     * {@link jbvbx.mbnbgement.MBebnServerFbctory#crebteMBebnServer
     * MBebnServerFbctory.crebteMBebnServer}
     * method bnd registers ebch plbtform MXBebn in this plbtform
     * {@code MBebnServer} with its
     * {@link PlbtformMbnbgedObject#getObjectNbme ObjectNbme}.
     * This method, in subsequent cblls, will simply return the
     * initiblly crebted plbtform {@code MBebnServer}.
     * <p>
     * MXBebns thbt get crebted bnd destroyed dynbmicblly, for exbmple,
     * memory {@link MemoryPoolMXBebn pools} bnd
     * {@link MemoryMbnbgerMXBebn mbnbgers},
     * will butombticblly be registered bnd deregistered into the plbtform
     * {@code MBebnServer}.
     * <p>
     * If the system property {@code jbvbx.mbnbgement.builder.initibl}
     * is set, the plbtform {@code MBebnServer} crebtion will be done
     * by the specified {@link jbvbx.mbnbgement.MBebnServerBuilder}.
     * <p>
     * It is recommended thbt this plbtform MBebnServer blso be used
     * to register other bpplicbtion mbnbged bebns
     * besides the plbtform MXBebns.
     * This will bllow bll MBebns to be published through the sbme
     * {@code MBebnServer} bnd hence bllow for ebsier network publishing
     * bnd discovery.
     * Nbme conflicts with the plbtform MXBebns should be bvoided.
     *
     * @return the plbtform {@code MBebnServer}; the plbtform
     *         MXBebns bre registered into the plbtform {@code MBebnServer}
     *         bt the first time this method is cblled.
     *
     * @exception SecurityException if there is b security mbnbger
     * bnd the cbller does not hbve the permission required by
     * {@link jbvbx.mbnbgement.MBebnServerFbctory#crebteMBebnServer}.
     *
     * @see jbvbx.mbnbgement.MBebnServerFbctory
     * @see jbvbx.mbnbgement.MBebnServerFbctory#crebteMBebnServer
     */
    public stbtic synchronized MBebnServer getPlbtformMBebnServer() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnServerPermission("crebteMBebnServer");
            sm.checkPermission(perm);
        }

        if (plbtformMBebnServer == null) {
            plbtformMBebnServer = MBebnServerFbctory.crebteMBebnServer();
            for (PlbtformComponent pc : PlbtformComponent.vblues()) {
                List<? extends PlbtformMbnbgedObject> list =
                    pc.getMXBebns(pc.getMXBebnInterfbce());
                for (PlbtformMbnbgedObject o : list) {
                    // Ebch PlbtformComponent represents one mbnbgement
                    // interfbce. Some MXBebn mby extend bnother one.
                    // The MXBebn instbnces for one plbtform component
                    // (returned by pc.getMXBebns()) might be blso
                    // the MXBebn instbnces for bnother plbtform component.
                    // e.g. com.sun.mbnbgement.GbrbbgeCollectorMXBebn
                    //
                    // So need to check if bn MXBebn instbnce is registered
                    // before registering into the plbtform MBebnServer
                    if (!plbtformMBebnServer.isRegistered(o.getObjectNbme())) {
                        bddMXBebn(plbtformMBebnServer, o);
                    }
                }
            }
            HbshMbp<ObjectNbme, DynbmicMBebn> dynmbebns =
                    MbnbgementFbctoryHelper.getPlbtformDynbmicMBebns();
            for (Mbp.Entry<ObjectNbme, DynbmicMBebn> e : dynmbebns.entrySet()) {
                bddDynbmicMBebn(plbtformMBebnServer, e.getVblue(), e.getKey());
            }
        }
        return plbtformMBebnServer;
    }

    /**
     * Returns b proxy for b plbtform MXBebn interfbce of b
     * given <b href="#MXBebnNbmes">MXBebn nbme</b>
     * thbt forwbrds its method cblls through the given
     * <tt>MBebnServerConnection</tt>.
     *
     * <p>This method is equivblent to:
     * <blockquote>
     * {@link jbvb.lbng.reflect.Proxy#newProxyInstbnce
     *        Proxy.newProxyInstbnce}<tt>(mxbebnInterfbce.getClbssLobder(),
     *        new Clbss[] { mxbebnInterfbce }, hbndler)</tt>
     * </blockquote>
     *
     * where <tt>hbndler</tt> is bn {@link jbvb.lbng.reflect.InvocbtionHbndler
     * InvocbtionHbndler} to which method invocbtions to the MXBebn interfbce
     * bre dispbtched. This <tt>hbndler</tt> converts bn input pbrbmeter
     * from bn MXBebn dbtb type to its mbpped open type before forwbrding
     * to the <tt>MBebnServer</tt> bnd converts b return vblue from
     * bn MXBebn method cbll through the <tt>MBebnServer</tt>
     * from bn open type to the corresponding return type declbred in
     * the MXBebn interfbce.
     *
     * <p>
     * If the MXBebn is b notificbtion emitter (i.e.,
     * it implements
     * {@link jbvbx.mbnbgement.NotificbtionEmitter NotificbtionEmitter}),
     * both the <tt>mxbebnInterfbce</tt> bnd <tt>NotificbtionEmitter</tt>
     * will be implemented by this proxy.
     *
     * <p>
     * <b>Notes:</b>
     * <ol>
     * <li>Using bn MXBebn proxy is b convenience remote bccess to
     * b plbtform MXBebn of b running virtubl mbchine.  All method
     * cblls to the MXBebn proxy bre forwbrded to bn
     * <tt>MBebnServerConnection</tt> where
     * {@link jbvb.io.IOException IOException} mby be thrown
     * when the communicbtion problem occurs with the connector server.
     * An bpplicbtion remotely bccesses the plbtform MXBebns using
     * proxy should prepbre to cbtch <tt>IOException</tt> bs if
     * bccessing with the <tt>MBebnServerConnector</tt> interfbce.</li>
     *
     * <li>When b client bpplicbtion is designed to remotely bccess MXBebns
     * for b running virtubl mbchine whose version is different thbn
     * the version on which the bpplicbtion is running,
     * it should prepbre to cbtch
     * {@link jbvb.io.InvblidObjectException InvblidObjectException}
     * which is thrown when bn MXBebn proxy receives b nbme of bn
     * enum constbnt which is missing in the enum clbss lobded in
     * the client bpplicbtion. </li>
     *
     * <li>{@link jbvbx.mbnbgement.MBebnServerInvocbtionHbndler
     * MBebnServerInvocbtionHbndler} or its
     * {@link jbvbx.mbnbgement.MBebnServerInvocbtionHbndler#newProxyInstbnce
     * newProxyInstbnce} method cbnnot be used to crebte
     * b proxy for b plbtform MXBebn. The proxy object crebted
     * by <tt>MBebnServerInvocbtionHbndler</tt> does not hbndle
     * the properties of the plbtform MXBebns described in
     * the <b href="#MXBebn">clbss specificbtion</b>.
     *</li>
     * </ol>
     *
     * @pbrbm connection the <tt>MBebnServerConnection</tt> to forwbrd to.
     * @pbrbm mxbebnNbme the nbme of b plbtform MXBebn within
     * <tt>connection</tt> to forwbrd to. <tt>mxbebnNbme</tt> must be
     * in the formbt of {@link ObjectNbme ObjectNbme}.
     * @pbrbm mxbebnInterfbce the MXBebn interfbce to be implemented
     * by the proxy.
     * @pbrbm <T> bn {@code mxbebnInterfbce} type pbrbmeter
     *
     * @return b proxy for b plbtform MXBebn interfbce of b
     * given <b href="#MXBebnNbmes">MXBebn nbme</b>
     * thbt forwbrds its method cblls through the given
     * <tt>MBebnServerConnection</tt>, or {@code null} if not exist.
     *
     * @throws IllegblArgumentException if
     * <ul>
     * <li><tt>mxbebnNbme</tt> is not with b vblid
     *     {@link ObjectNbme ObjectNbme} formbt, or</li>
     * <li>the nbmed MXBebn in the <tt>connection</tt> is
     *     not b MXBebn provided by the plbtform, or</li>
     * <li>the nbmed MXBebn is not registered in the
     *     <tt>MBebnServerConnection</tt>, or</li>
     * <li>the nbmed MXBebn is not bn instbnce of the given
     *     <tt>mxbebnInterfbce</tt></li>
     * </ul>
     *
     * @throws jbvb.io.IOException if b communicbtion problem
     * occurred when bccessing the <tt>MBebnServerConnection</tt>.
     */
    public stbtic <T> T
        newPlbtformMXBebnProxy(MBebnServerConnection connection,
                               String mxbebnNbme,
                               Clbss<T> mxbebnInterfbce)
            throws jbvb.io.IOException {

        // Only bllow MXBebn interfbces from rt.jbr lobded by the
        // bootstrbp clbss lobder
        finbl Clbss<?> cls = mxbebnInterfbce;
        ClbssLobder lobder =
            AccessController.doPrivileged(new PrivilegedAction<ClbssLobder>() {
                public ClbssLobder run() {
                    return cls.getClbssLobder();
                }
            });
        if (!sun.misc.VM.isSystemDombinLobder(lobder)) {
            throw new IllegblArgumentException(mxbebnNbme +
                " is not b plbtform MXBebn");
        }

        try {
            finbl ObjectNbme objNbme = new ObjectNbme(mxbebnNbme);
            // skip the isInstbnceOf check for LoggingMXBebn
            String intfNbme = mxbebnInterfbce.getNbme();
            if (!connection.isInstbnceOf(objNbme, intfNbme)) {
                throw new IllegblArgumentException(mxbebnNbme +
                    " is not bn instbnce of " + mxbebnInterfbce);
            }

            finbl Clbss<?>[] interfbces;
            // check if the registered MBebn is b notificbtion emitter
            boolebn emitter = connection.isInstbnceOf(objNbme, NOTIF_EMITTER);

            // crebte bn MXBebn proxy
            return JMX.newMXBebnProxy(connection, objNbme, mxbebnInterfbce,
                                      emitter);
        } cbtch (InstbnceNotFoundException|MblformedObjectNbmeException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * Returns the plbtform MXBebn implementing
     * the given {@code mxbebnInterfbce} which is specified
     * to hbve one single instbnce in the Jbvb virtubl mbchine.
     * This method mby return {@code null} if the mbnbgement interfbce
     * is not implemented in the Jbvb virtubl mbchine (for exbmple,
     * b Jbvb virtubl mbchine with no compilbtion system does not
     * implement {@link CompilbtionMXBebn});
     * otherwise, this method is equivblent to cblling:
     * <pre>
     *    {@link #getPlbtformMXBebns(Clbss)
     *      getPlbtformMXBebns(mxbebnInterfbce)}.get(0);
     * </pre>
     *
     * @pbrbm mxbebnInterfbce b mbnbgement interfbce for b plbtform
     *     MXBebn with one single instbnce in the Jbvb virtubl mbchine
     *     if implemented.
     * @pbrbm <T> bn {@code mxbebnInterfbce} type pbrbmeter
     *
     * @return the plbtform MXBebn thbt implements
     * {@code mxbebnInterfbce}, or {@code null} if not exist.
     *
     * @throws IllegblArgumentException if {@code mxbebnInterfbce}
     * is not b plbtform mbnbgement interfbce or
     * not b singleton plbtform MXBebn.
     *
     * @since 1.7
     */
    public stbtic <T extends PlbtformMbnbgedObject>
            T getPlbtformMXBebn(Clbss<T> mxbebnInterfbce) {
        PlbtformComponent pc = PlbtformComponent.getPlbtformComponent(mxbebnInterfbce);
        if (pc == null)
            throw new IllegblArgumentException(mxbebnInterfbce.getNbme() +
                " is not b plbtform mbnbgement interfbce");
        if (!pc.isSingleton())
            throw new IllegblArgumentException(mxbebnInterfbce.getNbme() +
                " cbn hbve zero or more thbn one instbnces");

        return pc.getSingletonMXBebn(mxbebnInterfbce);
    }

    /**
     * Returns the list of plbtform MXBebns implementing
     * the given {@code mxbebnInterfbce} in the Jbvb
     * virtubl mbchine.
     * The returned list mby contbin zero, one, or more instbnces.
     * The number of instbnces in the returned list is defined
     * in the specificbtion of the given mbnbgement interfbce.
     * The order is undefined bnd there is no gubrbntee thbt
     * the list returned is in the sbme order bs previous invocbtions.
     *
     * @pbrbm mxbebnInterfbce b mbnbgement interfbce for b plbtform
     *                        MXBebn
     * @pbrbm <T> bn {@code mxbebnInterfbce} type pbrbmeter
     *
     * @return the list of plbtform MXBebns thbt implement
     * {@code mxbebnInterfbce}.
     *
     * @throws IllegblArgumentException if {@code mxbebnInterfbce}
     * is not b plbtform mbnbgement interfbce.
     *
     * @since 1.7
     */
    public stbtic <T extends PlbtformMbnbgedObject> List<T>
            getPlbtformMXBebns(Clbss<T> mxbebnInterfbce) {
        PlbtformComponent pc = PlbtformComponent.getPlbtformComponent(mxbebnInterfbce);
        if (pc == null)
            throw new IllegblArgumentException(mxbebnInterfbce.getNbme() +
                " is not b plbtform mbnbgement interfbce");
        return Collections.unmodifibbleList(pc.getMXBebns(mxbebnInterfbce));
    }

    /**
     * Returns the plbtform MXBebn proxy for
     * {@code mxbebnInterfbce} which is specified to hbve one single
     * instbnce in b Jbvb virtubl mbchine bnd the proxy will
     * forwbrd the method cblls through the given {@code MBebnServerConnection}.
     * This method mby return {@code null} if the mbnbgement interfbce
     * is not implemented in the Jbvb virtubl mbchine being monitored
     * (for exbmple, b Jbvb virtubl mbchine with no compilbtion system
     * does not implement {@link CompilbtionMXBebn});
     * otherwise, this method is equivblent to cblling:
     * <pre>
     *     {@link #getPlbtformMXBebns(MBebnServerConnection, Clbss)
     *        getPlbtformMXBebns(connection, mxbebnInterfbce)}.get(0);
     * </pre>
     *
     * @pbrbm connection the {@code MBebnServerConnection} to forwbrd to.
     * @pbrbm mxbebnInterfbce b mbnbgement interfbce for b plbtform
     *     MXBebn with one single instbnce in the Jbvb virtubl mbchine
     *     being monitored, if implemented.
     * @pbrbm <T> bn {@code mxbebnInterfbce} type pbrbmeter
     *
     * @return the plbtform MXBebn proxy for
     * forwbrding the method cblls of the {@code mxbebnInterfbce}
     * through the given {@code MBebnServerConnection},
     * or {@code null} if not exist.
     *
     * @throws IllegblArgumentException if {@code mxbebnInterfbce}
     * is not b plbtform mbnbgement interfbce or
     * not b singleton plbtform MXBebn.
     * @throws jbvb.io.IOException if b communicbtion problem
     * occurred when bccessing the {@code MBebnServerConnection}.
     *
     * @see #newPlbtformMXBebnProxy
     * @since 1.7
     */
    public stbtic <T extends PlbtformMbnbgedObject>
            T getPlbtformMXBebn(MBebnServerConnection connection,
                                Clbss<T> mxbebnInterfbce)
        throws jbvb.io.IOException
    {
        PlbtformComponent pc = PlbtformComponent.getPlbtformComponent(mxbebnInterfbce);
        if (pc == null)
            throw new IllegblArgumentException(mxbebnInterfbce.getNbme() +
                " is not b plbtform mbnbgement interfbce");
        if (!pc.isSingleton())
            throw new IllegblArgumentException(mxbebnInterfbce.getNbme() +
                " cbn hbve zero or more thbn one instbnces");
        return pc.getSingletonMXBebn(connection, mxbebnInterfbce);
    }

    /**
     * Returns the list of the plbtform MXBebn proxies for
     * forwbrding the method cblls of the {@code mxbebnInterfbce}
     * through the given {@code MBebnServerConnection}.
     * The returned list mby contbin zero, one, or more instbnces.
     * The number of instbnces in the returned list is defined
     * in the specificbtion of the given mbnbgement interfbce.
     * The order is undefined bnd there is no gubrbntee thbt
     * the list returned is in the sbme order bs previous invocbtions.
     *
     * @pbrbm connection the {@code MBebnServerConnection} to forwbrd to.
     * @pbrbm mxbebnInterfbce b mbnbgement interfbce for b plbtform
     *                        MXBebn
     * @pbrbm <T> bn {@code mxbebnInterfbce} type pbrbmeter
     *
     * @return the list of plbtform MXBebn proxies for
     * forwbrding the method cblls of the {@code mxbebnInterfbce}
     * through the given {@code MBebnServerConnection}.
     *
     * @throws IllegblArgumentException if {@code mxbebnInterfbce}
     * is not b plbtform mbnbgement interfbce.
     *
     * @throws jbvb.io.IOException if b communicbtion problem
     * occurred when bccessing the {@code MBebnServerConnection}.
     *
     * @see #newPlbtformMXBebnProxy
     * @since 1.7
     */
    public stbtic <T extends PlbtformMbnbgedObject>
            List<T> getPlbtformMXBebns(MBebnServerConnection connection,
                                       Clbss<T> mxbebnInterfbce)
        throws jbvb.io.IOException
    {
        PlbtformComponent pc = PlbtformComponent.getPlbtformComponent(mxbebnInterfbce);
        if (pc == null) {
            throw new IllegblArgumentException(mxbebnInterfbce.getNbme() +
                " is not b plbtform mbnbgement interfbce");
        }
        return Collections.unmodifibbleList(pc.getMXBebns(connection, mxbebnInterfbce));
    }

    /**
     * Returns the set of {@code Clbss} objects, subinterfbce of
     * {@link PlbtformMbnbgedObject}, representing
     * bll mbnbgement interfbces for
     * monitoring bnd mbnbging the Jbvb plbtform.
     *
     * @return the set of {@code Clbss} objects, subinterfbce of
     * {@link PlbtformMbnbgedObject} representing
     * the mbnbgement interfbces for
     * monitoring bnd mbnbging the Jbvb plbtform.
     *
     * @since 1.7
     */
    public stbtic Set<Clbss<? extends PlbtformMbnbgedObject>>
           getPlbtformMbnbgementInterfbces()
    {
        Set<Clbss<? extends PlbtformMbnbgedObject>> result =
            new HbshSet<>();
        for (PlbtformComponent component: PlbtformComponent.vblues()) {
            result.bdd(component.getMXBebnInterfbce());
        }
        return Collections.unmodifibbleSet(result);
    }

    privbte stbtic finbl String NOTIF_EMITTER =
        "jbvbx.mbnbgement.NotificbtionEmitter";

    /**
     * Registers bn MXBebn.
     */
    privbte stbtic void bddMXBebn(finbl MBebnServer mbs, finbl PlbtformMbnbgedObject pmo) {
        // Mbke DynbmicMBebn out of MXBebn by wrbpping it with b StbndbrdMBebn
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws InstbnceAlrebdyExistsException,
                                         MBebnRegistrbtionException,
                                         NotComplibntMBebnException {
                    finbl DynbmicMBebn dmbebn;
                    if (pmo instbnceof DynbmicMBebn) {
                        dmbebn = DynbmicMBebn.clbss.cbst(pmo);
                    } else if (pmo instbnceof NotificbtionEmitter) {
                        dmbebn = new StbndbrdEmitterMBebn(pmo, null, true, (NotificbtionEmitter) pmo);
                    } else {
                        dmbebn = new StbndbrdMBebn(pmo, null, true);
                    }

                    mbs.registerMBebn(dmbebn, pmo.getObjectNbme());
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw new RuntimeException(e.getException());
        }
    }

    /**
     * Registers b DynbmicMBebn.
     */
    privbte stbtic void bddDynbmicMBebn(finbl MBebnServer mbs,
                                        finbl DynbmicMBebn dmbebn,
                                        finbl ObjectNbme on) {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                @Override
                public Void run() throws InstbnceAlrebdyExistsException,
                                         MBebnRegistrbtionException,
                                         NotComplibntMBebnException {
                    mbs.registerMBebn(dmbebn, on);
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw new RuntimeException(e.getException());
        }
    }
}
