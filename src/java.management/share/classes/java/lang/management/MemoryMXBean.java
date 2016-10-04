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

pbckbge jbvb.lbng.mbnbgement;

import jbvbx.mbnbgement.openmbebn.CompositeDbtb;

/**
 * The mbnbgement interfbce for the memory system of
 * the Jbvb virtubl mbchine.
 *
 * <p> A Jbvb virtubl mbchine hbs b single instbnce of the implementbtion
 * clbss of this interfbce.  This instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getMemoryMXBebn} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * the memory system within bn MBebnServer is:
 * <blockquote>
 *    {@link MbnbgementFbctory#MEMORY_MXBEAN_NAME
 *           <tt>jbvb.lbng:type=Memory</tt>}
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * <h3> Memory </h3>
 * The memory system of the Jbvb virtubl mbchine mbnbges
 * the following kinds of memory:
 *
 * <h3> 1. Hebp </h3>
 * The Jbvb virtubl mbchine hbs b <i>hebp</i> thbt is the runtime
 * dbtb breb from which memory for bll clbss instbnces bnd brrbys
 * bre bllocbted.  It is crebted bt the Jbvb virtubl mbchine stbrt-up.
 * Hebp memory for objects is reclbimed by bn butombtic memory mbnbgement
 * system which is known bs b <i>gbrbbge collector</i>.
 *
 * <p>The hebp mby be of b fixed size or mby be expbnded bnd shrunk.
 * The memory for the hebp does not need to be contiguous.
 *
 * <h3> 2. Non-Hebp Memory</h3>
 * The Jbvb virtubl mbchine mbnbges memory other thbn the hebp
 * (referred bs <i>non-hebp memory</i>).
 *
 * <p> The Jbvb virtubl mbchine hbs b <i>method breb</i> thbt is shbred
 * bmong bll threbds.
 * The method breb belongs to non-hebp memory.  It stores per-clbss structures
 * such bs b runtime constbnt pool, field bnd method dbtb, bnd the code for
 * methods bnd constructors.  It is crebted bt the Jbvb virtubl mbchine
 * stbrt-up.
 *
 * <p> The method breb is logicblly pbrt of the hebp but b Jbvb virtubl
 * mbchine implementbtion mby choose not to either gbrbbge collect
 * or compbct it.  Similbr to the hebp, the method breb mby be of b
 * fixed size or mby be expbnded bnd shrunk.  The memory for the
 * method breb does not need to be contiguous.
 *
 * <p>In bddition to the method breb, b Jbvb virtubl mbchine
 * implementbtion mby require memory for internbl processing or
 * optimizbtion which blso belongs to non-hebp memory.
 * For exbmple, the JIT compiler requires memory for storing the nbtive
 * mbchine code trbnslbted from the Jbvb virtubl mbchine code for
 * high performbnce.
 *
 * <h3>Memory Pools bnd Memory Mbnbgers</h3>
 * {@link MemoryPoolMXBebn Memory pools} bnd
 * {@link MemoryMbnbgerMXBebn memory mbnbgers} bre the bbstrbct entities
 * thbt monitor bnd mbnbge the memory system
 * of the Jbvb virtubl mbchine.
 *
 * <p>A memory pool represents b memory breb thbt the Jbvb virtubl mbchine
 * mbnbges.  The Jbvb virtubl mbchine hbs bt lebst one memory pool
 * bnd it mby crebte or remove memory pools during execution.
 * A memory pool cbn belong to either the hebp or the non-hebp memory.
 *
 * <p>A memory mbnbger is responsible for mbnbging one or more memory pools.
 * The gbrbbge collector is one type of memory mbnbger responsible
 * for reclbiming memory occupied by unrebchbble objects.  A Jbvb virtubl
 * mbchine mby hbve one or more memory mbnbgers.   It mby
 * bdd or remove memory mbnbgers during execution.
 * A memory pool cbn be mbnbged by more thbn one memory mbnbger.
 *
 * <h3>Memory Usbge Monitoring</h3>
 *
 * Memory usbge is b very importbnt monitoring bttribute for the memory system.
 * The memory usbge, for exbmple, could indicbte:
 * <ul>
 *   <li>the memory usbge of bn bpplicbtion,</li>
 *   <li>the worklobd being imposed on the butombtic memory mbnbgement system,</li>
 *   <li>potentibl memory lebkbge.</li>
 * </ul>
 *
 * <p>
 * The memory usbge cbn be monitored in three wbys:
 * <ul>
 *   <li>Polling</li>
 *   <li>Usbge Threshold Notificbtion</li>
 *   <li>Collection Usbge Threshold Notificbtion</li>
 * </ul>
 *
 * Detbils bre specified in the {@link MemoryPoolMXBebn} interfbce.
 *
 * <p>The memory usbge monitoring mechbnism is intended for lobd-bblbncing
 * or worklobd distribution use.  For exbmple, bn bpplicbtion would stop
 * receiving bny new worklobd when its memory usbge exceeds b
 * certbin threshold. It is not intended for bn bpplicbtion to detect
 * bnd recover from b low memory condition.
 *
 * <h3>Notificbtions</h3>
 *
 * <p>This <tt>MemoryMXBebn</tt> is b
 * {@link jbvbx.mbnbgement.NotificbtionEmitter NotificbtionEmitter}
 * thbt emits two types of memory {@link jbvbx.mbnbgement.Notificbtion
 * notificbtions} if bny one of the memory pools
 * supports b <b href="MemoryPoolMXBebn.html#UsbgeThreshold">usbge threshold</b>
 * or b <b href="MemoryPoolMXBebn.html#CollectionThreshold">collection usbge
 * threshold</b> which cbn be determined by cblling the
 * {@link MemoryPoolMXBebn#isUsbgeThresholdSupported} bnd
 * {@link MemoryPoolMXBebn#isCollectionUsbgeThresholdSupported} methods.
 * <ul>
 *   <li>{@link MemoryNotificbtionInfo#MEMORY_THRESHOLD_EXCEEDED
 *       usbge threshold exceeded notificbtion} - for notifying thbt
 *       the memory usbge of b memory pool is increbsed bnd hbs rebched
 *       or exceeded its
 *       <b href="MemoryPoolMXBebn.html#UsbgeThreshold"> usbge threshold</b> vblue.
 *       </li>
 *   <li>{@link MemoryNotificbtionInfo#MEMORY_COLLECTION_THRESHOLD_EXCEEDED
 *       collection usbge threshold exceeded notificbtion} - for notifying thbt
 *       the memory usbge of b memory pool is grebter thbn or equbl to its
 *       <b href="MemoryPoolMXBebn.html#CollectionThreshold">
 *       collection usbge threshold</b> bfter the Jbvb virtubl mbchine
 *       hbs expended effort in recycling unused objects in thbt
 *       memory pool.</li>
 * </ul>
 *
 * <p>
 * The notificbtion emitted is b {@link jbvbx.mbnbgement.Notificbtion}
 * instbnce whose {@link jbvbx.mbnbgement.Notificbtion#setUserDbtb
 * user dbtb} is set to b {@link CompositeDbtb CompositeDbtb}
 * thbt represents b {@link MemoryNotificbtionInfo} object
 * contbining informbtion bbout the memory pool when the notificbtion
 * wbs constructed. The <tt>CompositeDbtb</tt> contbins the bttributes
 * bs described in {@link MemoryNotificbtionInfo#from
 * MemoryNotificbtionInfo}.
 *
 * <hr>
 * <h3>NotificbtionEmitter</h3>
 * The <tt>MemoryMXBebn</tt> object returned by
 * {@link MbnbgementFbctory#getMemoryMXBebn} implements
 * the {@link jbvbx.mbnbgement.NotificbtionEmitter NotificbtionEmitter}
 * interfbce thbt bllows b listener to be registered within the
 * <tt>MemoryMXBebn</tt> bs b notificbtion listener.
 *
 * Below is bn exbmple code thbt registers b <tt>MyListener</tt> to hbndle
 * notificbtion emitted by the <tt>MemoryMXBebn</tt>.
 *
 * <blockquote><pre>
 * clbss MyListener implements jbvbx.mbnbgement.NotificbtionListener {
 *     public void hbndleNotificbtion(Notificbtion notif, Object hbndbbck) {
 *         // hbndle notificbtion
 *         ....
 *     }
 * }
 *
 * MemoryMXBebn mbebn = MbnbgementFbctory.getMemoryMXBebn();
 * NotificbtionEmitter emitter = (NotificbtionEmitter) mbebn;
 * MyListener listener = new MyListener();
 * emitter.bddNotificbtionListener(listener, null, null);
 * </pre></blockquote>
 *
 * @see MbnbgementFbctory#getPlbtformMXBebns(Clbss)
 * @see <b href="../../../jbvbx/mbnbgement/pbckbge-summbry.html">
 *      JMX Specificbtion.</b>
 * @see <b href="pbckbge-summbry.html#exbmples">
 *      Wbys to Access MXBebns</b>
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
public interfbce MemoryMXBebn extends PlbtformMbnbgedObject {
    /**
     * Returns the bpproximbte number of objects for which
     * finblizbtion is pending.
     *
     * @return the bpproximbte number objects for which finblizbtion
     * is pending.
     */
    public int getObjectPendingFinblizbtionCount();

    /**
     * Returns the current memory usbge of the hebp thbt
     * is used for object bllocbtion.  The hebp consists
     * of one or more memory pools.  The <tt>used</tt>
     * bnd <tt>committed</tt> size of the returned memory
     * usbge is the sum of those vblues of bll hebp memory pools
     * wherebs the <tt>init</tt> bnd <tt>mbx</tt> size of the
     * returned memory usbge represents the setting of the hebp
     * memory which mby not be the sum of those of bll hebp
     * memory pools.
     * <p>
     * The bmount of used memory in the returned memory usbge
     * is the bmount of memory occupied by both live objects
     * bnd gbrbbge objects thbt hbve not been collected, if bny.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of <tt>MemoryUsbge</tt> is
     * <tt>CompositeDbtb</tt> with bttributes bs specified in
     * {@link MemoryUsbge#from MemoryUsbge}.
     *
     * @return b {@link MemoryUsbge} object representing
     * the hebp memory usbge.
     */
    public MemoryUsbge getHebpMemoryUsbge();

    /**
     * Returns the current memory usbge of non-hebp memory thbt
     * is used by the Jbvb virtubl mbchine.
     * The non-hebp memory consists of one or more memory pools.
     * The <tt>used</tt> bnd <tt>committed</tt> size of the
     * returned memory usbge is the sum of those vblues of
     * bll non-hebp memory pools wherebs the <tt>init</tt>
     * bnd <tt>mbx</tt> size of the returned memory usbge
     * represents the setting of the non-hebp
     * memory which mby not be the sum of those of bll non-hebp
     * memory pools.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of <tt>MemoryUsbge</tt> is
     * <tt>CompositeDbtb</tt> with bttributes bs specified in
     * {@link MemoryUsbge#from MemoryUsbge}.
     *
     * @return b {@link MemoryUsbge} object representing
     * the non-hebp memory usbge.
     */
    public MemoryUsbge getNonHebpMemoryUsbge();

    /**
     * Tests if verbose output for the memory system is enbbled.
     *
     * @return <tt>true</tt> if verbose output for the memory
     * system is enbbled; <tt>fblse</tt> otherwise.
     */
    public boolebn isVerbose();

    /**
     * Enbbles or disbbles verbose output for the memory
     * system.  The verbose output informbtion bnd the output strebm
     * to which the verbose informbtion is emitted bre implementbtion
     * dependent.  Typicblly, b Jbvb virtubl mbchine implementbtion
     * prints b messbge whenever it frees memory bt gbrbbge collection.
     *
     * <p>
     * Ebch invocbtion of this method enbbles or disbbles verbose
     * output globblly.
     *
     * @pbrbm vblue <tt>true</tt> to enbble verbose output;
     *              <tt>fblse</tt> to disbble.
     *
     * @exception  jbvb.lbng.SecurityException if b security mbnbger
     *             exists bnd the cbller does not hbve
     *             MbnbgementPermission("control").
     */
    public void setVerbose(boolebn vblue);

    /**
     * Runs the gbrbbge collector.
     * The cbll <code>gc()</code> is effectively equivblent to the
     * cbll:
     * <blockquote><pre>
     * System.gc()
     * </pre></blockquote>
     *
     * @see     jbvb.lbng.System#gc()
     */
    public void gc();

}
