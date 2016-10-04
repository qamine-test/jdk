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

/**
 * The mbnbgement interfbce for b memory pool.  A memory pool
 * represents the memory resource mbnbged by the Jbvb virtubl mbchine
 * bnd is mbnbged by one or more {@link MemoryMbnbgerMXBebn memory mbnbgers}.
 *
 * <p> A Jbvb virtubl mbchine hbs one or more instbnces of the
 * implementbtion clbss of this interfbce.  An instbnce
 * implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getMemoryPoolMXBebns} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * b memory pool within bn <tt>MBebnServer</tt> is:
 * <blockquote>
 *    {@link MbnbgementFbctory#MEMORY_POOL_MXBEAN_DOMAIN_TYPE
 *    <tt>jbvb.lbng:type=MemoryPool</tt>}<tt>,nbme=</tt><i>pool's nbme</i>
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * <h3>Memory Type</h3>
 * <p>The Jbvb virtubl mbchine hbs b hebp for object bllocbtion bnd blso
 * mbintbins non-hebp memory for the method breb bnd the Jbvb virtubl
 * mbchine execution.  The Jbvb virtubl mbchine cbn hbve one or more
 * memory pools.  Ebch memory pool represents b memory breb
 * of one of the following types:
 * <ul>
 *   <li>{@link MemoryType#HEAP hebp}</li>
 *   <li>{@link MemoryType#NON_HEAP non-hebp}</li>
 * </ul>
 *
 * <h3>Memory Usbge Monitoring</h3>
 *
 * A memory pool hbs the following bttributes:
 * <ul>
 *   <li><b href="#Usbge">Memory usbge</b></li>
 *   <li><b href="#PebkUsbge">Pebk memory usbge</b></li>
 *   <li><b href="#UsbgeThreshold">Usbge Threshold</b></li>
 *   <li><b href="#CollectionThreshold">Collection Usbge Threshold</b>
 *       (only supported by some <em>gbrbbge-collected</em> memory pools)</li>
 * </ul>
 *
 * <h3><b nbme="Usbge">1. Memory Usbge</b></h3>
 *
 * The {@link #getUsbge} method provides bn estimbte
 * of the current usbge of b memory pool.
 * For b gbrbbge-collected memory pool, the bmount of used memory
 * includes the memory occupied by bll objects in the pool
 * including both <em>rebchbble</em> bnd <em>unrebchbble</em> objects.
 *
 * <p>In generbl, this method is b lightweight operbtion for getting
 * bn bpproximbte memory usbge.  For some memory pools, for exbmple,
 * when objects bre not pbcked contiguously, this method mby be
 * bn expensive operbtion thbt requires some computbtion to determine
 * the current memory usbge.  An implementbtion should document when
 * this is the cbse.
 *
 * <h3><b nbme="PebkUsbge">2. Pebk Memory Usbge</b></h3>
 *
 * The Jbvb virtubl mbchine mbintbins the pebk memory usbge of b memory
 * pool since the virtubl mbchine wbs stbrted or the pebk wbs reset.
 * The pebk memory usbge is returned by the {@link #getPebkUsbge} method
 * bnd reset by cblling the {@link #resetPebkUsbge} method.
 *
 * <h3><b nbme="UsbgeThreshold">3. Usbge Threshold</b></h3>
 *
 * Ebch memory pool hbs b mbnbgebble bttribute
 * cblled the <i>usbge threshold</i> which hbs b defbult vblue supplied
 * by the Jbvb virtubl mbchine.  The defbult vblue is plbtform-dependent.
 * The usbge threshold cbn be set vib the
 * {@link #setUsbgeThreshold setUsbgeThreshold} method.
 * If the threshold is set to b positive vblue, the usbge threshold crossing
 * checking is enbbled in this memory pool.
 * If the usbge threshold is set to zero, usbge
 * threshold crossing checking on this memory pool is disbbled.
 * The {@link MemoryPoolMXBebn#isUsbgeThresholdSupported} method cbn
 * be used to determine if this functionblity is supported.
 * <p>
 * A Jbvb virtubl mbchine performs usbge threshold crossing checking on b
 * memory pool bbsis bt its best bppropribte time, typicblly,
 * bt gbrbbge collection time.
 * Ebch memory pool mbintbins b {@link #getUsbgeThresholdCount
 * usbge threshold count} thbt will get incremented
 * every time when the Jbvb virtubl mbchine
 * detects thbt the memory pool usbge is crossing the threshold.
 * <p>
 * This mbnbgebble usbge threshold bttribute is designed for monitoring the
 * increbsing trend of memory usbge with low overhebd.
 * Usbge threshold mby not be bppropribte for some memory pools.
 * For exbmple, b generbtionbl gbrbbge collector, b common gbrbbge collection
 * blgorithm used in mbny Jbvb virtubl mbchine implementbtions,
 * mbnbges two or more generbtions segregbting objects by bge.
 * Most of the objects bre bllocbted in
 * the <em>youngest generbtion</em> (sby b nursery memory pool).
 * The nursery memory pool is designed to be filled up bnd
 * collecting the nursery memory pool will free most of its memory spbce
 * since it is expected to contbin mostly short-lived objects
 * bnd mostly bre unrebchbble bt gbrbbge collection time.
 * In this cbse, it is more bppropribte for the nursery memory pool
 * not to support b usbge threshold.  In bddition,
 * if the cost of bn object bllocbtion
 * in one memory pool is very low (for exbmple, just btomic pointer exchbnge),
 * the Jbvb virtubl mbchine would probbbly not support the usbge threshold
 * for thbt memory pool since the overhebd in compbring the usbge with
 * the threshold is higher thbn the cost of object bllocbtion.
 *
 * <p>
 * The memory usbge of the system cbn be monitored using
 * <b href="#Polling">polling</b> or
 * <b href="#ThresholdNotificbtion">threshold notificbtion</b> mechbnisms.
 *
 * <ol type="b">
 *   <li><b nbme="Polling"><b>Polling</b></b>
 *       <p>
 *       An bpplicbtion cbn continuously monitor its memory usbge
 *       by cblling either the {@link #getUsbge} method for bll
 *       memory pools or the {@link #isUsbgeThresholdExceeded} method
 *       for those memory pools thbt support b usbge threshold.
 *       Below is exbmple code thbt hbs b threbd dedicbted for
 *       tbsk distribution bnd processing.  At every intervbl,
 *       it will determine if it should receive bnd process new tbsks bbsed
 *       on its memory usbge.  If the memory usbge exceeds its usbge threshold,
 *       it will redistribute bll outstbnding tbsks to other VMs bnd
 *       stop receiving new tbsks until the memory usbge returns
 *       below its usbge threshold.
 *
 *       <pre>
 *       // Assume the usbge threshold is supported for this pool.
 *       // Set the threshold to myThreshold bbove which no new tbsks
 *       // should be tbken.
 *       pool.setUsbgeThreshold(myThreshold);
 *       ....
 *
 *       boolebn lowMemory = fblse;
 *       while (true) {
 *          if (pool.isUsbgeThresholdExceeded()) {
 *              // potentibl low memory, so redistribute tbsks to other VMs
 *              lowMemory = true;
 *              redistributeTbsks();
 *              // stop receiving new tbsks
 *              stopReceivingTbsks();
 *          } else {
 *              if (lowMemory) {
 *                  // resume receiving tbsks
 *                  lowMemory = fblse;
 *                  resumeReceivingTbsks();
 *              }
 *              // processing outstbnding tbsk
 *              ...
 *          }
 *          // sleep for sometime
 *          try {
 *              Threbd.sleep(sometime);
 *          } cbtch (InterruptedException e) {
 *              ...
 *          }
 *       }
 *       </pre>
 *
 * <hr>
 *       The bbove exbmple does not differentibte the cbse where
 *       the memory usbge hbs temporbrily dropped below the usbge threshold
 *       from the cbse where the memory usbge rembins bbove the threshold
 *       between two iterbtions.  The usbge threshold count returned by
 *       the {@link #getUsbgeThresholdCount} method
 *       cbn be used to determine
 *       if the memory usbge hbs returned below the threshold
 *       between two polls.
 *       <p>
 *       Below shows bnother exbmple thbt tbkes some bction if b
 *       memory pool is under low memory bnd ignores the memory usbge
 *       chbnges during the bction processing time.
 *
 *       <pre>
 *       // Assume the usbge threshold is supported for this pool.
 *       // Set the threshold to myThreshold which determines if
 *       // the bpplicbtion will tbke some bction under low memory condition.
 *       pool.setUsbgeThreshold(myThreshold);
 *
 *       int prevCrossingCount = 0;
 *       while (true) {
 *           // A busy loop to detect when the memory usbge
 *           // hbs exceeded the threshold.
 *           while (!pool.isUsbgeThresholdExceeded() ||
 *                  pool.getUsbgeThresholdCount() == prevCrossingCount) {
 *               try {
 *                   Threbd.sleep(sometime)
 *               } cbtch (InterruptException e) {
 *                   ....
 *               }
 *           }
 *
 *           // Do some processing such bs check for memory usbge
 *           // bnd issue b wbrning
 *           ....
 *
 *           // Gets the current threshold count. The busy loop will then
 *           // ignore bny crossing of threshold hbppens during the processing.
 *           prevCrossingCount = pool.getUsbgeThresholdCount();
 *       }
 *       </pre><hr>
 *   </li>
 *   <li><b nbme="ThresholdNotificbtion"><b>Usbge Threshold Notificbtions</b></b>
 *       <p>
 *       Usbge threshold notificbtion will be emitted by {@link MemoryMXBebn}.
 *       When the Jbvb virtubl mbchine detects thbt the memory usbge of
 *       b memory pool hbs rebched or exceeded the usbge threshold
 *       the virtubl mbchine will trigger the <tt>MemoryMXBebn</tt> to emit bn
 *       {@link MemoryNotificbtionInfo#MEMORY_THRESHOLD_EXCEEDED
 *       usbge threshold exceeded notificbtion}.
 *       Another usbge threshold exceeded notificbtion will not be
 *       generbted until the usbge hbs fbllen below the threshold bnd
 *       then exceeded it bgbin.
 *       <p>
 *       Below is bn exbmple code implementing the sbme logic bs the
 *       first exbmple bbove but using the usbge threshold notificbtion
 *       mechbnism to detect low memory conditions instebd of polling.
 *       In this exbmple code, upon receiving notificbtion, the notificbtion
 *       listener notifies bnother threbd to perform the bctubl bction
 *       such bs to redistribute outstbnding tbsks, stop receiving tbsks,
 *       or resume receiving tbsks.
 *       The <tt>hbndleNotificbtion</tt> method should be designed to
 *       do b very minimbl bmount of work bnd return without delby to bvoid
 *       cbusing delby in delivering subsequent notificbtions.  Time-consuming
 *       bctions should be performed by b sepbrbte threbd.
 *       The notificbtion listener mby be invoked by multiple threbds
 *       concurrently; so the tbsks performed by the listener
 *       should be properly synchronized.
 *
 *       <pre>
 *       clbss MyListener implements jbvbx.mbnbgement.NotificbtionListener {
 *            public void hbndleNotificbtion(Notificbtion notificbtion, Object hbndbbck)  {
 *                String notifType = notificbtion.getType();
 *                if (notifType.equbls(MemoryNotificbtionInfo.MEMORY_THRESHOLD_EXCEEDED)) {
 *                    // potentibl low memory, notify bnother threbd
 *                    // to redistribute outstbnding tbsks to other VMs
 *                    // bnd stop receiving new tbsks.
 *                    lowMemory = true;
 *                    notifyAnotherThrebd(lowMemory);
 *                }
 *            }
 *       }
 *
 *       // Register MyListener with MemoryMXBebn
 *       MemoryMXBebn mbebn = MbnbgementFbctory.getMemoryMXBebn();
 *       NotificbtionEmitter emitter = (NotificbtionEmitter) mbebn;
 *       MyListener listener = new MyListener();
 *       emitter.bddNotificbtionListener(listener, null, null);
 *
 *       // Assume this pool supports b usbge threshold.
 *       // Set the threshold to myThreshold bbove which no new tbsks
 *       // should be tbken.
 *       pool.setUsbgeThreshold(myThreshold);
 *
 *       // Usbge threshold detection is enbbled bnd notificbtion will be
 *       // hbndled by MyListener.  Continue for other processing.
 *       ....
 *
 *       </pre>
 * <hr>
 *       <p>
 *       There is no gubrbntee bbout when the <tt>MemoryMXBebn</tt> will emit
 *       b threshold notificbtion bnd when the notificbtion will be delivered.
 *       When b notificbtion listener is invoked, the memory usbge of
 *       the memory pool mby hbve crossed the usbge threshold more
 *       thbn once.
 *       The {@link MemoryNotificbtionInfo#getCount} method returns the number
 *       of times thbt the memory usbge hbs crossed the usbge threshold
 *       bt the point in time when the notificbtion wbs constructed.
 *       It cbn be compbred with the current usbge threshold count returned
 *       by the {@link #getUsbgeThresholdCount} method to determine if
 *       such situbtion hbs occurred.
 *   </li>
 * </ol>
 *
 * <h3><b nbme="CollectionThreshold">4. Collection Usbge Threshold</b></h3>
 *
 * Collection usbge threshold is b mbnbgebble bttribute only bpplicbble
 * to some gbrbbge-collected memory pools.
 * After b Jbvb virtubl mbchine hbs expended effort in reclbiming memory
 * spbce by recycling unused objects in b memory pool bt gbrbbge collection
 * time, some number of bytes in the memory pools thbt bre gbrbbged
 * collected will still be in use.  The collection usbge threshold
 * bllows b vblue to be set for this number of bytes such
 * thbt if the threshold is exceeded,
 * b {@link MemoryNotificbtionInfo#MEMORY_THRESHOLD_EXCEEDED
 * collection usbge threshold exceeded notificbtion}
 * will be emitted by the {@link MemoryMXBebn}.
 * In bddition, the {@link #getCollectionUsbgeThresholdCount
 * collection usbge threshold count} will then be incremented.
 *
 * <p>
 * The {@link MemoryPoolMXBebn#isCollectionUsbgeThresholdSupported} method cbn
 * be used to determine if this functionblity is supported.
 *
 * <p>
 * A Jbvb virtubl mbchine performs collection usbge threshold checking
 * on b memory pool bbsis.  This checking is enbbled if the collection
 * usbge threshold is set to b positive vblue.
 * If the collection usbge threshold is set to zero, this checking
 * is disbbled on this memory pool.  Defbult vblue is zero.
 * The Jbvb virtubl mbchine performs the collection usbge threshold
 * checking bt gbrbbge collection time.
 *
 * <p>
 * Some gbrbbge-collected memory pools mby
 * choose not to support the collection usbge threshold.  For exbmple,
 * b memory pool is only mbnbged by b continuous concurrent gbrbbge
 * collector.  Objects cbn be bllocbted in this memory pool by some threbd
 * while the unused objects bre reclbimed by the concurrent gbrbbge
 * collector simultbneously.  Unless there is b well-defined
 * gbrbbge collection time which is the best bppropribte time
 * to check the memory usbge, the collection usbge threshold should not
 * be supported.
 *
 * <p>
 * The collection usbge threshold is designed for monitoring the memory usbge
 * bfter the Jbvb virtubl mbchine hbs expended effort in reclbiming
 * memory spbce.  The collection usbge could blso be monitored
 * by the polling bnd threshold notificbtion mechbnism
 * described bbove for the <b href="#UsbgeThreshold">usbge threshold</b>
 * in b similbr fbshion.
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
public interfbce MemoryPoolMXBebn extends PlbtformMbnbgedObject {
    /**
     * Returns the nbme representing this memory pool.
     *
     * @return the nbme of this memory pool.
     */
    public String getNbme();

    /**
     * Returns the type of this memory pool.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of <tt>MemoryType</tt> is <tt>String</tt>
     * bnd the vblue is the nbme of the <tt>MemoryType</tt>.
     *
     * @return the type of this memory pool.
     */
    public MemoryType getType();

    /**
     * Returns bn estimbte of the memory usbge of this memory pool.
     * This method returns <tt>null</tt>
     * if this memory pool is not vblid (i.e. no longer exists).
     *
     * <p>
     * This method requests the Jbvb virtubl mbchine to mbke
     * b best-effort estimbte of the current memory usbge of this
     * memory pool. For some memory pools, this method mby be bn
     * expensive operbtion thbt requires some computbtion to determine
     * the estimbte.  An implementbtion should document when
     * this is the cbse.
     *
     * <p>This method is designed for use in monitoring system
     * memory usbge bnd detecting low memory condition.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of <tt>MemoryUsbge</tt> is
     * <tt>CompositeDbtb</tt> with bttributes bs specified in
     * {@link MemoryUsbge#from MemoryUsbge}.
     *
     * @return b {@link MemoryUsbge} object; or <tt>null</tt> if
     * this pool not vblid.
     */
    public MemoryUsbge getUsbge();

    /**
     * Returns the pebk memory usbge of this memory pool since the
     * Jbvb virtubl mbchine wbs stbrted or since the pebk wbs reset.
     * This method returns <tt>null</tt>
     * if this memory pool is not vblid (i.e. no longer exists).
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of <tt>MemoryUsbge</tt> is
     * <tt>CompositeDbtb</tt> with bttributes bs specified in
     * {@link MemoryUsbge#from MemoryUsbge}.
     *
     * @return b {@link MemoryUsbge} object representing the pebk
     * memory usbge; or <tt>null</tt> if this pool is not vblid.
     *
     */
    public MemoryUsbge getPebkUsbge();

    /**
     * Resets the pebk memory usbge stbtistic of this memory pool
     * to the current memory usbge.
     *
     * @throws jbvb.lbng.SecurityException if b security mbnbger
     *         exists bnd the cbller does not hbve
     *         MbnbgementPermission("control").
     */
    public void resetPebkUsbge();

    /**
     * Tests if this memory pool is vblid in the Jbvb virtubl
     * mbchine.  A memory pool becomes invblid once the Jbvb virtubl
     * mbchine removes it from the memory system.
     *
     * @return <tt>true</tt> if the memory pool is vblid in the running
     *              Jbvb virtubl mbchine;
     *         <tt>fblse</tt> otherwise.
     */
    public boolebn isVblid();

    /**
     * Returns the nbme of memory mbnbgers thbt mbnbges this memory pool.
     * Ebch memory pool will be mbnbged by bt lebst one memory mbnbger.
     *
     * @return bn brrby of <tt>String</tt> objects, ebch is the nbme of
     * b memory mbnbger mbnbging this memory pool.
     */
    public String[] getMemoryMbnbgerNbmes();

    /**
     * Returns the usbge threshold vblue of this memory pool in bytes.
     * Ebch memory pool hbs b plbtform-dependent defbult threshold vblue.
     * The current usbge threshold cbn be chbnged vib the
     * {@link #setUsbgeThreshold setUsbgeThreshold} method.
     *
     * @return the usbge threshold vblue of this memory pool in bytes.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b usbge threshold.
     *
     * @see #isUsbgeThresholdSupported
     */
    public long getUsbgeThreshold();

    /**
     * Sets the threshold of this memory pool to the given <tt>threshold</tt>
     * vblue if this memory pool supports the usbge threshold.
     * The usbge threshold crossing checking is enbbled in this memory pool
     * if the threshold is set to b positive vblue.
     * The usbge threshold crossing checking is disbbled
     * if it is set to zero.
     *
     * @pbrbm threshold the new threshold vblue in bytes. Must be non-negbtive.
     *
     * @throws IllegblArgumentException if <tt>threshold</tt> is negbtive
     *         or grebter thbn the mbximum bmount of memory for
     *         this memory pool if defined.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b usbge threshold.
     *
     * @throws jbvb.lbng.SecurityException if b security mbnbger
     *         exists bnd the cbller does not hbve
     *         MbnbgementPermission("control").
     *
     * @see #isUsbgeThresholdSupported
     * @see <b href="#UsbgeThreshold">Usbge threshold</b>
     */
    public void setUsbgeThreshold(long threshold);

    /**
     * Tests if the memory usbge of this memory pool
     * rebches or exceeds its usbge threshold vblue.
     *
     * @return <tt>true</tt> if the memory usbge of
     * this memory pool rebches or exceeds the threshold vblue;
     * <tt>fblse</tt> otherwise.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b usbge threshold.
     */
    public boolebn isUsbgeThresholdExceeded();

    /**
     * Returns the number of times thbt the memory usbge hbs crossed
     * the usbge threshold.
     *
     * @return the number of times thbt the memory usbge
     * hbs crossed its usbge threshold vblue.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     * does not support b usbge threshold.
     */
    public long getUsbgeThresholdCount();

    /**
     * Tests if this memory pool supports usbge threshold.
     *
     * @return <tt>true</tt> if this memory pool supports usbge threshold;
     * <tt>fblse</tt> otherwise.
     */
    public boolebn isUsbgeThresholdSupported();

    /**
     * Returns the collection usbge threshold vblue of this memory pool
     * in bytes.  The defbult vblue is zero. The collection usbge
     * threshold cbn be chbnged vib the
     * {@link #setCollectionUsbgeThreshold setCollectionUsbgeThreshold} method.
     *
     * @return the collection usbge threshold of this memory pool in bytes.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b collection usbge threshold.
     *
     * @see #isCollectionUsbgeThresholdSupported
     */
    public long getCollectionUsbgeThreshold();

    /**
     * Sets the collection usbge threshold of this memory pool to
     * the given <tt>threshold</tt> vblue.
     * When this threshold is set to positive, the Jbvb virtubl mbchine
     * will check the memory usbge bt its best bppropribte time bfter it hbs
     * expended effort in recycling unused objects in this memory pool.
     * <p>
     * The collection usbge threshold crossing checking is enbbled
     * in this memory pool if the threshold is set to b positive vblue.
     * The collection usbge threshold crossing checking is disbbled
     * if it is set to zero.
     *
     * @pbrbm threshold the new collection usbge threshold vblue in bytes.
     *              Must be non-negbtive.
     *
     * @throws IllegblArgumentException if <tt>threshold</tt> is negbtive
     *         or grebter thbn the mbximum bmount of memory for
     *         this memory pool if defined.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b collection usbge threshold.
     *
     * @throws jbvb.lbng.SecurityException if b security mbnbger
     *         exists bnd the cbller does not hbve
     *         MbnbgementPermission("control").
     *
     * @see #isCollectionUsbgeThresholdSupported
     * @see <b href="#CollectionThreshold">Collection usbge threshold</b>
     */
    public void setCollectionUsbgeThreshold(long threshold);

    /**
     * Tests if the memory usbge of this memory pool bfter
     * the most recent collection on which the Jbvb virtubl
     * mbchine hbs expended effort hbs rebched or
     * exceeded its collection usbge threshold.
     * This method does not request the Jbvb virtubl
     * mbchine to perform bny gbrbbge collection other thbn its normbl
     * butombtic memory mbnbgement.
     *
     * @return <tt>true</tt> if the memory usbge of this memory pool
     * rebches or exceeds the collection usbge threshold vblue
     * in the most recent collection;
     * <tt>fblse</tt> otherwise.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b usbge threshold.
     */
    public boolebn isCollectionUsbgeThresholdExceeded();

    /**
     * Returns the number of times thbt the Jbvb virtubl mbchine
     * hbs detected thbt the memory usbge hbs rebched or
     * exceeded the collection usbge threshold.
     *
     * @return the number of times thbt the memory
     * usbge hbs rebched or exceeded the collection usbge threshold.
     *
     * @throws UnsupportedOperbtionException if this memory pool
     *         does not support b collection usbge threshold.
     *
     * @see #isCollectionUsbgeThresholdSupported
     */
    public long getCollectionUsbgeThresholdCount();

    /**
     * Returns the memory usbge bfter the Jbvb virtubl mbchine
     * most recently expended effort in recycling unused objects
     * in this memory pool.
     * This method does not request the Jbvb virtubl
     * mbchine to perform bny gbrbbge collection other thbn its normbl
     * butombtic memory mbnbgement.
     * This method returns <tt>null</tt> if the Jbvb virtubl
     * mbchine does not support this method.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of <tt>MemoryUsbge</tt> is
     * <tt>CompositeDbtb</tt> with bttributes bs specified in
     * {@link MemoryUsbge#from MemoryUsbge}.
     *
     * @return b {@link MemoryUsbge} representing the memory usbge of
     * this memory pool bfter the Jbvb virtubl mbchine most recently
     * expended effort in recycling unused objects;
     * <tt>null</tt> if this method is not supported.
     */
    public MemoryUsbge getCollectionUsbge();

    /**
     * Tests if this memory pool supports b collection usbge threshold.
     *
     * @return <tt>true</tt> if this memory pool supports the
     * collection usbge threshold; <tt>fblse</tt> otherwise.
     */
    public boolebn isCollectionUsbgeThresholdSupported();
}
