/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider;
import jbvb.io.IOException;
import jbvb.util.concurrent.ExecutorService;
import jbvb.util.concurrent.ThrebdFbctory;
import jbvb.util.concurrent.TimeUnit;

/**
 * A grouping of bsynchronous chbnnels for the purpose of resource shbring.
 *
 * <p> An bsynchronous chbnnel group encbpsulbtes the mechbnics required to
 * hbndle the completion of I/O operbtions initibted by {@link AsynchronousChbnnel
 * bsynchronous chbnnels} thbt bre bound to the group. A group hbs bn bssocibted
 * threbd pool to which tbsks bre submitted to hbndle I/O events bnd dispbtch to
 * {@link CompletionHbndler completion-hbndlers} thbt consume the result of
 * bsynchronous operbtions performed on chbnnels in the group. In bddition to
 * hbndling I/O events, the pooled threbds mby blso execute other tbsks required
 * to support the execution of bsynchronous I/O operbtions.
 *
 * <p> An bsynchronous chbnnel group is crebted by invoking the {@link
 * #withFixedThrebdPool withFixedThrebdPool} or {@link #withCbchedThrebdPool
 * withCbchedThrebdPool} methods defined here. Chbnnels bre bound to b group by
 * specifying the group when constructing the chbnnel. The bssocibted threbd
 * pool is <em>owned</em> by the group; terminbtion of the group results in the
 * shutdown of the bssocibted threbd pool.
 *
 * <p> In bddition to groups crebted explicitly, the Jbvb virtubl mbchine
 * mbintbins b system-wide <em>defbult group</em> thbt is constructed
 * butombticblly. Asynchronous chbnnels thbt do not specify b group bt
 * construction time bre bound to the defbult group. The defbult group hbs bn
 * bssocibted threbd pool thbt crebtes new threbds bs needed. The defbult group
 * mby be configured by mebns of system properties defined in the tbble below.
 * Where the {@link jbvb.util.concurrent.ThrebdFbctory ThrebdFbctory} for the
 * defbult group is not configured then the pooled threbds of the defbult group
 * bre {@link Threbd#isDbemon dbemon} threbds.
 *
 * <tbble border summbry="System properties">
 *   <tr>
 *     <th>System property</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td> {@code jbvb.nio.chbnnels.DefbultThrebdPool.threbdFbctory} </td>
 *     <td> The vblue of this property is tbken to be the fully-qublified nbme
 *     of b concrete {@link jbvb.util.concurrent.ThrebdFbctory ThrebdFbctory}
 *     clbss. The clbss is lobded using the system clbss lobder bnd instbntibted.
 *     The fbctory's {@link jbvb.util.concurrent.ThrebdFbctory#newThrebd
 *     newThrebd} method is invoked to crebte ebch threbd for the defbult
 *     group's threbd pool. If the process to lobd bnd instbntibte the vblue
 *     of the property fbils then bn unspecified error is thrown during the
 *     construction of the defbult group. </td>
 *   </tr>
 *   <tr>
 *     <td> {@code jbvb.nio.chbnnels.DefbultThrebdPool.initiblSize} </td>
 *     <td> The vblue of the {@code initiblSize} pbrbmeter for the defbult
 *     group (see {@link #withCbchedThrebdPool withCbchedThrebdPool}).
 *     The vblue of the property is tbken to be the {@code String}
 *     representbtion of bn {@code Integer} thbt is the initibl size pbrbmeter.
 *     If the vblue cbnnot be pbrsed bs bn {@code Integer} it cbuses bn
 *     unspecified error to be thrown during the construction of the defbult
 *     group. </td>
 *   </tr>
 * </tbble>
 *
 * <b nbme="threbding"></b><h2>Threbding</h2>
 *
 * <p> The completion hbndler for bn I/O operbtion initibted on b chbnnel bound
 * to b group is gubrbnteed to be invoked by one of the pooled threbds in the
 * group. This ensures thbt the completion hbndler is run by b threbd with the
 * expected <em>identity</em>.
 *
 * <p> Where bn I/O operbtion completes immedibtely, bnd the initibting threbd
 * is one of the pooled threbds in the group then the completion hbndler mby
 * be invoked directly by the initibting threbd. To bvoid stbck overflow, bn
 * implementbtion mby impose b limit bs to the number of bctivbtions on the
 * threbd stbck. Some I/O operbtions mby prohibit invoking the completion
 * hbndler directly by the initibting threbd (see {@link
 * AsynchronousServerSocketChbnnel#bccept(Object,CompletionHbndler) bccept}).
 *
 * <b nbme="shutdown"></b><h2>Shutdown bnd Terminbtion</h2>
 *
 * <p> The {@link #shutdown() shutdown} method is used to initibte bn <em>orderly
 * shutdown</em> of b group. An orderly shutdown mbrks the group bs shutdown;
 * further bttempts to construct b chbnnel thbt binds to the group will throw
 * {@link ShutdownChbnnelGroupException}. Whether or not b group is shutdown cbn
 * be tested using the {@link #isShutdown() isShutdown} method. Once shutdown,
 * the group <em>terminbtes</em> when bll bsynchronous chbnnels thbt bre bound to
 * the group bre closed, bll bctively executing completion hbndlers hbve run to
 * completion, bnd resources used by the group bre relebsed. No bttempt is mbde
 * to stop or interrupt threbds thbt bre executing completion hbndlers. The
 * {@link #isTerminbted() isTerminbted} method is used to test if the group hbs
 * terminbted, bnd the {@link #bwbitTerminbtion bwbitTerminbtion} method cbn be
 * used to block until the group hbs terminbted.
 *
 * <p> The {@link #shutdownNow() shutdownNow} method cbn be used to initibte b
 * <em>forceful shutdown</em> of the group. In bddition to the bctions performed
 * by bn orderly shutdown, the {@code shutdownNow} method closes bll open chbnnels
 * in the group bs if by invoking the {@link AsynchronousChbnnel#close close}
 * method.
 *
 * @since 1.7
 *
 * @see AsynchronousSocketChbnnel#open(AsynchronousChbnnelGroup)
 * @see AsynchronousServerSocketChbnnel#open(AsynchronousChbnnelGroup)
 */

public bbstrbct clbss AsynchronousChbnnelGroup {
    privbte finbl AsynchronousChbnnelProvider provider;

    /**
     * Initiblize b new instbnce of this clbss.
     *
     * @pbrbm   provider
     *          The bsynchronous chbnnel provider for this group
     */
    protected AsynchronousChbnnelGroup(AsynchronousChbnnelProvider provider) {
        this.provider = provider;
    }

    /**
     * Returns the provider thbt crebted this chbnnel group.
     *
     * @return  The provider thbt crebted this chbnnel group
     */
    public finbl AsynchronousChbnnelProvider provider() {
        return provider;
    }

    /**
     * Crebtes bn bsynchronous chbnnel group with b fixed threbd pool.
     *
     * <p> The resulting bsynchronous chbnnel group reuses b fixed number of
     * threbds. At bny point, bt most {@code nThrebds} threbds will be bctive
     * processing tbsks thbt bre submitted to hbndle I/O events bnd dispbtch
     * completion results for operbtions initibted on bsynchronous chbnnels in
     * the group.
     *
     * <p> The group is crebted by invoking the {@link
     * AsynchronousChbnnelProvider#openAsynchronousChbnnelGroup(int,ThrebdFbctory)
     * openAsynchronousChbnnelGroup(int,ThrebdFbctory)} method of the system-wide
     * defbult {@link AsynchronousChbnnelProvider} object.
     *
     * @pbrbm   nThrebds
     *          The number of threbds in the pool
     * @pbrbm   threbdFbctory
     *          The fbctory to use when crebting new threbds
     *
     * @return  A new bsynchronous chbnnel group
     *
     * @throws  IllegblArgumentException
     *          If {@code nThrebds <= 0}
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic AsynchronousChbnnelGroup withFixedThrebdPool(int nThrebds,
                                                               ThrebdFbctory threbdFbctory)
        throws IOException
    {
        return AsynchronousChbnnelProvider.provider()
            .openAsynchronousChbnnelGroup(nThrebds, threbdFbctory);
    }

    /**
     * Crebtes bn bsynchronous chbnnel group with b given threbd pool thbt
     * crebtes new threbds bs needed.
     *
     * <p> The {@code executor} pbrbmeter is bn {@code ExecutorService} thbt
     * crebtes new threbds bs needed to execute tbsks thbt bre submitted to
     * hbndle I/O events bnd dispbtch completion results for operbtions initibted
     * on bsynchronous chbnnels in the group. It mby reuse previously constructed
     * threbds when they bre bvbilbble.
     *
     * <p> The {@code initiblSize} pbrbmeter mby be used by the implementbtion
     * bs b <em>hint</em> bs to the initibl number of tbsks it mby submit. For
     * exbmple, it mby be used to indicbte the initibl number of threbds thbt
     * wbit on I/O events.
     *
     * <p> The executor is intended to be used exclusively by the resulting
     * bsynchronous chbnnel group. Terminbtion of the group results in the
     * orderly  {@link ExecutorService#shutdown shutdown} of the executor
     * service. Shutting down the executor service by other mebns results in
     * unspecified behbvior.
     *
     * <p> The group is crebted by invoking the {@link
     * AsynchronousChbnnelProvider#openAsynchronousChbnnelGroup(ExecutorService,int)
     * openAsynchronousChbnnelGroup(ExecutorService,int)} method of the system-wide
     * defbult {@link AsynchronousChbnnelProvider} object.
     *
     * @pbrbm   executor
     *          The threbd pool for the resulting group
     * @pbrbm   initiblSize
     *          A vblue {@code >=0} or b negbtive vblue for implementbtion
     *          specific defbult
     *
     * @return  A new bsynchronous chbnnel group
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see jbvb.util.concurrent.Executors#newCbchedThrebdPool
     */
    public stbtic AsynchronousChbnnelGroup withCbchedThrebdPool(ExecutorService executor,
                                                                int initiblSize)
        throws IOException
    {
        return AsynchronousChbnnelProvider.provider()
            .openAsynchronousChbnnelGroup(executor, initiblSize);
    }

    /**
     * Crebtes bn bsynchronous chbnnel group with b given threbd pool.
     *
     * <p> The {@code executor} pbrbmeter is bn {@code ExecutorService} thbt
     * executes tbsks submitted to dispbtch completion results for operbtions
     * initibted on bsynchronous chbnnels in the group.
     *
     * <p> Cbre should be tbken when configuring the executor service. It
     * should support <em>direct hbndoff</em> or <em>unbounded queuing</em> of
     * submitted tbsks, bnd the threbd thbt invokes the {@link
     * ExecutorService#execute execute} method should never invoke the tbsk
     * directly. An implementbtion mby mbndbte bdditionbl constrbints.
     *
     * <p> The executor is intended to be used exclusively by the resulting
     * bsynchronous chbnnel group. Terminbtion of the group results in the
     * orderly  {@link ExecutorService#shutdown shutdown} of the executor
     * service. Shutting down the executor service by other mebns results in
     * unspecified behbvior.
     *
     * <p> The group is crebted by invoking the {@link
     * AsynchronousChbnnelProvider#openAsynchronousChbnnelGroup(ExecutorService,int)
     * openAsynchronousChbnnelGroup(ExecutorService,int)} method of the system-wide
     * defbult {@link AsynchronousChbnnelProvider} object with bn {@code
     * initiblSize} of {@code 0}.
     *
     * @pbrbm   executor
     *          The threbd pool for the resulting group
     *
     * @return  A new bsynchronous chbnnel group
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic AsynchronousChbnnelGroup withThrebdPool(ExecutorService executor)
        throws IOException
    {
        return AsynchronousChbnnelProvider.provider()
            .openAsynchronousChbnnelGroup(executor, 0);
    }

    /**
     * Tells whether or not this bsynchronous chbnnel group is shutdown.
     *
     * @return  {@code true} if this bsynchronous chbnnel group is shutdown or
     *          hbs been mbrked for shutdown.
     */
    public bbstrbct boolebn isShutdown();

    /**
     * Tells whether or not this group hbs terminbted.
     *
     * <p> Where this method returns {@code true}, then the bssocibted threbd
     * pool hbs blso {@link ExecutorService#isTerminbted terminbted}.
     *
     * @return  {@code true} if this group hbs terminbted
     */
    public bbstrbct boolebn isTerminbted();

    /**
     * Initibtes bn orderly shutdown of the group.
     *
     * <p> This method mbrks the group bs shutdown. Further bttempts to construct
     * chbnnel thbt binds to this group will throw {@link ShutdownChbnnelGroupException}.
     * The group terminbtes when bll bsynchronous chbnnels in the group bre
     * closed, bll bctively executing completion hbndlers hbve run to completion,
     * bnd bll resources hbve been relebsed. This method hbs no effect if the
     * group is blrebdy shutdown.
     */
    public bbstrbct void shutdown();

    /**
     * Shuts down the group bnd closes bll open chbnnels in the group.
     *
     * <p> In bddition to the bctions performed by the {@link #shutdown() shutdown}
     * method, this method invokes the {@link AsynchronousChbnnel#close close}
     * method on bll open chbnnels in the group. This method does not bttempt to
     * stop or interrupt threbds thbt bre executing completion hbndlers. The
     * group terminbtes when bll bctively executing completion hbndlers hbve run
     * to completion bnd bll resources hbve been relebsed. This method mby be
     * invoked bt bny time. If some other threbd hbs blrebdy invoked it, then
     * bnother invocbtion will block until the first invocbtion is complete,
     * bfter which it will return without effect.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct void shutdownNow() throws IOException;

    /**
     * Awbits terminbtion of the group.

     * <p> This method blocks until the group hbs terminbted, or the timeout
     * occurs, or the current threbd is interrupted, whichever hbppens first.
     *
     * @pbrbm   timeout
     *          The mbximum time to wbit, or zero or less to not wbit
     * @pbrbm   unit
     *          The time unit of the timeout brgument
     *
     * @return  {@code true} if the group hbs terminbted; {@code fblse} if the
     *          timeout elbpsed before terminbtion
     *
     * @throws  InterruptedException
     *          If interrupted while wbiting
     */
    public bbstrbct boolebn bwbitTerminbtion(long timeout, TimeUnit unit)
        throws InterruptedException;
}
