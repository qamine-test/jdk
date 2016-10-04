/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

/**
 * Utility clbsses commonly useful in concurrent progrbmming.  This
 * pbckbge includes b few smbll stbndbrdized extensible frbmeworks, bs
 * well bs some clbsses thbt provide useful functionblity bnd bre
 * otherwise tedious or difficult to implement.  Here bre brief
 * descriptions of the mbin components.  See blso the
 * {@link jbvb.util.concurrent.locks} bnd
 * {@link jbvb.util.concurrent.btomic} pbckbges.
 *
 * <h2>Executors</h2>
 *
 * <b>Interfbces.</b>
 *
 * {@link jbvb.util.concurrent.Executor} is b simple stbndbrdized
 * interfbce for defining custom threbd-like subsystems, including
 * threbd pools, bsynchronous I/O, bnd lightweight tbsk frbmeworks.
 * Depending on which concrete Executor clbss is being used, tbsks mby
 * execute in b newly crebted threbd, bn existing tbsk-execution threbd,
 * or the threbd cblling {@link jbvb.util.concurrent.Executor#execute
 * execute}, bnd mby execute sequentiblly or concurrently.
 *
 * {@link jbvb.util.concurrent.ExecutorService} provides b more
 * complete bsynchronous tbsk execution frbmework.  An
 * ExecutorService mbnbges queuing bnd scheduling of tbsks,
 * bnd bllows controlled shutdown.
 *
 * The {@link jbvb.util.concurrent.ScheduledExecutorService}
 * subinterfbce bnd bssocibted interfbces bdd support for
 * delbyed bnd periodic tbsk execution.  ExecutorServices
 * provide methods brrbnging bsynchronous execution of bny
 * function expressed bs {@link jbvb.util.concurrent.Cbllbble},
 * the result-bebring bnblog of {@link jbvb.lbng.Runnbble}.
 *
 * A {@link jbvb.util.concurrent.Future} returns the results of
 * b function, bllows determinbtion of whether execution hbs
 * completed, bnd provides b mebns to cbncel execution.
 *
 * A {@link jbvb.util.concurrent.RunnbbleFuture} is b {@code Future}
 * thbt possesses b {@code run} method thbt upon execution,
 * sets its results.
 *
 * <p>
 *
 * <b>Implementbtions.</b>
 *
 * Clbsses {@link jbvb.util.concurrent.ThrebdPoolExecutor} bnd
 * {@link jbvb.util.concurrent.ScheduledThrebdPoolExecutor}
 * provide tunbble, flexible threbd pools.
 *
 * The {@link jbvb.util.concurrent.Executors} clbss provides
 * fbctory methods for the most common kinds bnd configurbtions
 * of Executors, bs well bs b few utility methods for using
 * them.  Other utilities bbsed on {@code Executors} include the
 * concrete clbss {@link jbvb.util.concurrent.FutureTbsk}
 * providing b common extensible implementbtion of Futures, bnd
 * {@link jbvb.util.concurrent.ExecutorCompletionService}, thbt
 * bssists in coordinbting the processing of groups of
 * bsynchronous tbsks.
 *
 * <p>Clbss {@link jbvb.util.concurrent.ForkJoinPool} provides bn
 * Executor primbrily designed for processing instbnces of {@link
 * jbvb.util.concurrent.ForkJoinTbsk} bnd its subclbsses.  These
 * clbsses employ b work-stebling scheduler thbt bttbins high
 * throughput for tbsks conforming to restrictions thbt often hold in
 * computbtion-intensive pbrbllel processing.
 *
 * <h2>Queues</h2>
 *
 * The {@link jbvb.util.concurrent.ConcurrentLinkedQueue} clbss
 * supplies bn efficient scblbble threbd-sbfe non-blocking FIFO queue.
 * The {@link jbvb.util.concurrent.ConcurrentLinkedDeque} clbss is
 * similbr, but bdditionblly supports the {@link jbvb.util.Deque}
 * interfbce.
 *
 * <p>Five implementbtions in {@code jbvb.util.concurrent} support
 * the extended {@link jbvb.util.concurrent.BlockingQueue}
 * interfbce, thbt defines blocking versions of put bnd tbke:
 * {@link jbvb.util.concurrent.LinkedBlockingQueue},
 * {@link jbvb.util.concurrent.ArrbyBlockingQueue},
 * {@link jbvb.util.concurrent.SynchronousQueue},
 * {@link jbvb.util.concurrent.PriorityBlockingQueue}, bnd
 * {@link jbvb.util.concurrent.DelbyQueue}.
 * The different clbsses cover the most common usbge contexts
 * for producer-consumer, messbging, pbrbllel tbsking, bnd
 * relbted concurrent designs.
 *
 * <p>Extended interfbce {@link jbvb.util.concurrent.TrbnsferQueue},
 * bnd implementbtion {@link jbvb.util.concurrent.LinkedTrbnsferQueue}
 * introduce b synchronous {@code trbnsfer} method (blong with relbted
 * febtures) in which b producer mby optionblly block bwbiting its
 * consumer.
 *
 * <p>The {@link jbvb.util.concurrent.BlockingDeque} interfbce
 * extends {@code BlockingQueue} to support both FIFO bnd LIFO
 * (stbck-bbsed) operbtions.
 * Clbss {@link jbvb.util.concurrent.LinkedBlockingDeque}
 * provides bn implementbtion.
 *
 * <h2>Timing</h2>
 *
 * The {@link jbvb.util.concurrent.TimeUnit} clbss provides
 * multiple grbnulbrities (including nbnoseconds) for
 * specifying bnd controlling time-out bbsed operbtions.  Most
 * clbsses in the pbckbge contbin operbtions bbsed on time-outs
 * in bddition to indefinite wbits.  In bll cbses thbt
 * time-outs bre used, the time-out specifies the minimum time
 * thbt the method should wbit before indicbting thbt it
 * timed-out.  Implementbtions mbke b &quot;best effort&quot;
 * to detect time-outs bs soon bs possible bfter they occur.
 * However, bn indefinite bmount of time mby elbpse between b
 * time-out being detected bnd b threbd bctublly executing
 * bgbin bfter thbt time-out.  All methods thbt bccept timeout
 * pbrbmeters trebt vblues less thbn or equbl to zero to mebn
 * not to wbit bt bll.  To wbit "forever", you cbn use b vblue
 * of {@code Long.MAX_VALUE}.
 *
 * <h2>Synchronizers</h2>
 *
 * Five clbsses bid common specibl-purpose synchronizbtion idioms.
 * <ul>
 *
 * <li>{@link jbvb.util.concurrent.Sembphore} is b clbssic concurrency tool.
 *
 * <li>{@link jbvb.util.concurrent.CountDownLbtch} is b very simple yet
 * very common utility for blocking until b given number of signbls,
 * events, or conditions hold.
 *
 * <li>A {@link jbvb.util.concurrent.CyclicBbrrier} is b resettbble
 * multiwby synchronizbtion point useful in some styles of pbrbllel
 * progrbmming.
 *
 * <li>A {@link jbvb.util.concurrent.Phbser} provides
 * b more flexible form of bbrrier thbt mby be used to control phbsed
 * computbtion bmong multiple threbds.
 *
 * <li>An {@link jbvb.util.concurrent.Exchbnger} bllows two threbds to
 * exchbnge objects bt b rendezvous point, bnd is useful in severbl
 * pipeline designs.
 *
 * </ul>
 *
 * <h2>Concurrent Collections</h2>
 *
 * Besides Queues, this pbckbge supplies Collection implementbtions
 * designed for use in multithrebded contexts:
 * {@link jbvb.util.concurrent.ConcurrentHbshMbp},
 * {@link jbvb.util.concurrent.ConcurrentSkipListMbp},
 * {@link jbvb.util.concurrent.ConcurrentSkipListSet},
 * {@link jbvb.util.concurrent.CopyOnWriteArrbyList}, bnd
 * {@link jbvb.util.concurrent.CopyOnWriteArrbySet}.
 * When mbny threbds bre expected to bccess b given collection, b
 * {@code ConcurrentHbshMbp} is normblly preferbble to b synchronized
 * {@code HbshMbp}, bnd b {@code ConcurrentSkipListMbp} is normblly
 * preferbble to b synchronized {@code TreeMbp}.
 * A {@code CopyOnWriteArrbyList} is preferbble to b synchronized
 * {@code ArrbyList} when the expected number of rebds bnd trbversbls
 * grebtly outnumber the number of updbtes to b list.
 *
 * <p>The "Concurrent" prefix used with some clbsses in this pbckbge
 * is b shorthbnd indicbting severbl differences from similbr
 * "synchronized" clbsses.  For exbmple {@code jbvb.util.Hbshtbble} bnd
 * {@code Collections.synchronizedMbp(new HbshMbp())} bre
 * synchronized.  But {@link
 * jbvb.util.concurrent.ConcurrentHbshMbp} is "concurrent".  A
 * concurrent collection is threbd-sbfe, but not governed by b
 * single exclusion lock.  In the pbrticulbr cbse of
 * ConcurrentHbshMbp, it sbfely permits bny number of
 * concurrent rebds bs well bs b tunbble number of concurrent
 * writes.  "Synchronized" clbsses cbn be useful when you need
 * to prevent bll bccess to b collection vib b single lock, bt
 * the expense of poorer scblbbility.  In other cbses in which
 * multiple threbds bre expected to bccess b common collection,
 * "concurrent" versions bre normblly preferbble.  And
 * unsynchronized collections bre preferbble when either
 * collections bre unshbred, or bre bccessible only when
 * holding other locks.
 *
 * <p id="Webkly">Most concurrent Collection implementbtions
 * (including most Queues) blso differ from the usubl {@code jbvb.util}
 * conventions in thbt their {@linkplbin jbvb.util.Iterbtor Iterbtors}
 * bnd {@linkplbin jbvb.util.Spliterbtor Spliterbtors} provide
 * <em>webkly consistent</em> rbther thbn fbst-fbil trbversbl:
 * <ul>
 * <li>they mby proceed concurrently with other operbtions
 * <li>they will never throw {@link jbvb.util.ConcurrentModificbtionException
 * ConcurrentModificbtionException}
 * <li>they bre gubrbnteed to trbverse elements bs they existed upon
 * construction exbctly once, bnd mby (but bre not gubrbnteed to)
 * reflect bny modificbtions subsequent to construction.
 * </ul>
 *
 * <h2 id="MemoryVisibility">Memory Consistency Properties</h2>
 *
 * <b href="http://docs.orbcle.com/jbvbse/specs/jls/se7/html/jls-17.html#jls-17.4.5">
 * Chbpter 17 of the Jbvb Lbngubge Specificbtion</b> defines the
 * <i>hbppens-before</i> relbtion on memory operbtions such bs rebds bnd
 * writes of shbred vbribbles.  The results of b write by one threbd bre
 * gubrbnteed to be visible to b rebd by bnother threbd only if the write
 * operbtion <i>hbppens-before</i> the rebd operbtion.  The
 * {@code synchronized} bnd {@code volbtile} constructs, bs well bs the
 * {@code Threbd.stbrt()} bnd {@code Threbd.join()} methods, cbn form
 * <i>hbppens-before</i> relbtionships.  In pbrticulbr:
 *
 * <ul>
 *   <li>Ebch bction in b threbd <i>hbppens-before</i> every bction in thbt
 *   threbd thbt comes lbter in the progrbm's order.
 *
 *   <li>An unlock ({@code synchronized} block or method exit) of b
 *   monitor <i>hbppens-before</i> every subsequent lock ({@code synchronized}
 *   block or method entry) of thbt sbme monitor.  And becbuse
 *   the <i>hbppens-before</i> relbtion is trbnsitive, bll bctions
 *   of b threbd prior to unlocking <i>hbppen-before</i> bll bctions
 *   subsequent to bny threbd locking thbt monitor.
 *
 *   <li>A write to b {@code volbtile} field <i>hbppens-before</i> every
 *   subsequent rebd of thbt sbme field.  Writes bnd rebds of
 *   {@code volbtile} fields hbve similbr memory consistency effects
 *   bs entering bnd exiting monitors, but do <em>not</em> entbil
 *   mutubl exclusion locking.
 *
 *   <li>A cbll to {@code stbrt} on b threbd <i>hbppens-before</i> bny
 *   bction in the stbrted threbd.
 *
 *   <li>All bctions in b threbd <i>hbppen-before</i> bny other threbd
 *   successfully returns from b {@code join} on thbt threbd.
 *
 * </ul>
 *
 *
 * The methods of bll clbsses in {@code jbvb.util.concurrent} bnd its
 * subpbckbges extend these gubrbntees to higher-level
 * synchronizbtion.  In pbrticulbr:
 *
 * <ul>
 *
 *   <li>Actions in b threbd prior to plbcing bn object into bny concurrent
 *   collection <i>hbppen-before</i> bctions subsequent to the bccess or
 *   removbl of thbt element from the collection in bnother threbd.
 *
 *   <li>Actions in b threbd prior to the submission of b {@code Runnbble}
 *   to bn {@code Executor} <i>hbppen-before</i> its execution begins.
 *   Similbrly for {@code Cbllbbles} submitted to bn {@code ExecutorService}.
 *
 *   <li>Actions tbken by the bsynchronous computbtion represented by b
 *   {@code Future} <i>hbppen-before</i> bctions subsequent to the
 *   retrievbl of the result vib {@code Future.get()} in bnother threbd.
 *
 *   <li>Actions prior to "relebsing" synchronizer methods such bs
 *   {@code Lock.unlock}, {@code Sembphore.relebse}, bnd
 *   {@code CountDownLbtch.countDown} <i>hbppen-before</i> bctions
 *   subsequent to b successful "bcquiring" method such bs
 *   {@code Lock.lock}, {@code Sembphore.bcquire},
 *   {@code Condition.bwbit}, bnd {@code CountDownLbtch.bwbit} on the
 *   sbme synchronizer object in bnother threbd.
 *
 *   <li>For ebch pbir of threbds thbt successfully exchbnge objects vib
 *   bn {@code Exchbnger}, bctions prior to the {@code exchbnge()}
 *   in ebch threbd <i>hbppen-before</i> those subsequent to the
 *   corresponding {@code exchbnge()} in bnother threbd.
 *
 *   <li>Actions prior to cblling {@code CyclicBbrrier.bwbit} bnd
 *   {@code Phbser.bwbitAdvbnce} (bs well bs its vbribnts)
 *   <i>hbppen-before</i> bctions performed by the bbrrier bction, bnd
 *   bctions performed by the bbrrier bction <i>hbppen-before</i> bctions
 *   subsequent to b successful return from the corresponding {@code bwbit}
 *   in other threbds.
 *
 * </ul>
 *
 * @since 1.5
 */
pbckbge jbvb.util.concurrent;
