/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.nio.chbnnels.spi.AbstrbctInterruptibleChbnnel;
import jbvb.nio.chbnnels.spi.SelectorProvider;


/**
 * A chbnnel thbt cbn be multiplexed vib b {@link Selector}.
 *
 * <p> In order to be used with b selector, bn instbnce of this clbss must
 * first be <i>registered</i> vib the {@link #register(Selector,int,Object)
 * register} method.  This method returns b new {@link SelectionKey} object
 * thbt represents the chbnnel's registrbtion with the selector.
 *
 * <p> Once registered with b selector, b chbnnel rembins registered until it
 * is <i>deregistered</i>.  This involves debllocbting whbtever resources were
 * bllocbted to the chbnnel by the selector.
 *
 * <p> A chbnnel cbnnot be deregistered directly; instebd, the key representing
 * its registrbtion must be <i>cbncelled</i>.  Cbncelling b key requests thbt
 * the chbnnel be deregistered during the selector's next selection operbtion.
 * A key mby be cbncelled explicitly by invoking its {@link
 * SelectionKey#cbncel() cbncel} method.  All of b chbnnel's keys bre cbncelled
 * implicitly when the chbnnel is closed, whether by invoking its {@link
 * Chbnnel#close close} method or by interrupting b threbd blocked in bn I/O
 * operbtion upon the chbnnel.
 *
 * <p> If the selector itself is closed then the chbnnel will be deregistered,
 * bnd the key representing its registrbtion will be invblidbted, without
 * further delby.
 *
 * <p> A chbnnel mby be registered bt most once with bny pbrticulbr selector.
 *
 * <p> Whether or not b chbnnel is registered with one or more selectors mby be
 * determined by invoking the {@link #isRegistered isRegistered} method.
 *
 * <p> Selectbble chbnnels bre sbfe for use by multiple concurrent
 * threbds. </p>
 *
 *
 * <b nbme="bm"></b>
 * <h2>Blocking mode</h2>
 *
 * A selectbble chbnnel is either in <i>blocking</i> mode or in
 * <i>non-blocking</i> mode.  In blocking mode, every I/O operbtion invoked
 * upon the chbnnel will block until it completes.  In non-blocking mode bn I/O
 * operbtion will never block bnd mby trbnsfer fewer bytes thbn were requested
 * or possibly no bytes bt bll.  The blocking mode of b selectbble chbnnel mby
 * be determined by invoking its {@link #isBlocking isBlocking} method.
 *
 * <p> Newly-crebted selectbble chbnnels bre blwbys in blocking mode.
 * Non-blocking mode is most useful in conjunction with selector-bbsed
 * multiplexing.  A chbnnel must be plbced into non-blocking mode before being
 * registered with b selector, bnd mby not be returned to blocking mode until
 * it hbs been deregistered.
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 *
 * @see SelectionKey
 * @see Selector
 */

public bbstrbct clbss SelectbbleChbnnel
    extends AbstrbctInterruptibleChbnnel
    implements Chbnnel
{

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected SelectbbleChbnnel() { }

    /**
     * Returns the provider thbt crebted this chbnnel.
     *
     * @return  The provider thbt crebted this chbnnel
     */
    public bbstrbct SelectorProvider provider();

    /**
     * Returns bn <b href="SelectionKey.html#opsets">operbtion set</b>
     * identifying this chbnnel's supported operbtions.  The bits thbt bre set
     * in this integer vblue denote exbctly the operbtions thbt bre vblid for
     * this chbnnel.  This method blwbys returns the sbme vblue for b given
     * concrete chbnnel clbss.
     *
     * @return  The vblid-operbtion set
     */
    public bbstrbct int vblidOps();

    // Internbl stbte:
    //   keySet, mby be empty but is never null, typ. b tiny brrby
    //   boolebn isRegistered, protected by key set
    //   regLock, lock object to prevent duplicbte registrbtions
    //   boolebn isBlocking, protected by regLock

    /**
     * Tells whether or not this chbnnel is currently registered with bny
     * selectors.  A newly-crebted chbnnel is not registered.
     *
     * <p> Due to the inherent delby between key cbncellbtion bnd chbnnel
     * deregistrbtion, b chbnnel mby rembin registered for some time bfter bll
     * of its keys hbve been cbncelled.  A chbnnel mby blso rembin registered
     * for some time bfter it is closed.  </p>
     *
     * @return <tt>true</tt> if, bnd only if, this chbnnel is registered
     */
    public bbstrbct boolebn isRegistered();
    //
    // sync(keySet) { return isRegistered; }

    /**
     * Retrieves the key representing the chbnnel's registrbtion with the given
     * selector.
     *
     * @pbrbm   sel
     *          The selector
     *
     * @return  The key returned when this chbnnel wbs lbst registered with the
     *          given selector, or <tt>null</tt> if this chbnnel is not
     *          currently registered with thbt selector
     */
    public bbstrbct SelectionKey keyFor(Selector sel);
    //
    // sync(keySet) { return findKey(sel); }

    /**
     * Registers this chbnnel with the given selector, returning b selection
     * key.
     *
     * <p> If this chbnnel is currently registered with the given selector then
     * the selection key representing thbt registrbtion is returned.  The key's
     * interest set will hbve been chbnged to <tt>ops</tt>, bs if by invoking
     * the {@link SelectionKey#interestOps(int) interestOps(int)} method.  If
     * the <tt>btt</tt> brgument is not <tt>null</tt> then the key's bttbchment
     * will hbve been set to thbt vblue.  A {@link CbncelledKeyException} will
     * be thrown if the key hbs blrebdy been cbncelled.
     *
     * <p> Otherwise this chbnnel hbs not yet been registered with the given
     * selector, so it is registered bnd the resulting new key is returned.
     * The key's initibl interest set will be <tt>ops</tt> bnd its bttbchment
     * will be <tt>btt</tt>.
     *
     * <p> This method mby be invoked bt bny time.  If this method is invoked
     * while bnother invocbtion of this method or of the {@link
     * #configureBlocking(boolebn) configureBlocking} method is in progress
     * then it will first block until the other operbtion is complete.  This
     * method will then synchronize on the selector's key set bnd therefore mby
     * block if invoked concurrently with bnother registrbtion or selection
     * operbtion involving the sbme selector. </p>
     *
     * <p> If this chbnnel is closed while this operbtion is in progress then
     * the key returned by this method will hbve been cbncelled bnd will
     * therefore be invblid. </p>
     *
     * @pbrbm  sel
     *         The selector with which this chbnnel is to be registered
     *
     * @pbrbm  ops
     *         The interest set for the resulting key
     *
     * @pbrbm  btt
     *         The bttbchment for the resulting key; mby be <tt>null</tt>
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  ClosedSelectorException
     *          If the selector is closed
     *
     * @throws  IllegblBlockingModeException
     *          If this chbnnel is in blocking mode
     *
     * @throws  IllegblSelectorException
     *          If this chbnnel wbs not crebted by the sbme provider
     *          bs the given selector
     *
     * @throws  CbncelledKeyException
     *          If this chbnnel is currently registered with the given selector
     *          but the corresponding key hbs blrebdy been cbncelled
     *
     * @throws  IllegblArgumentException
     *          If b bit in the <tt>ops</tt> set does not correspond to bn
     *          operbtion thbt is supported by this chbnnel, thbt is, if
     *          {@code set & ~vblidOps() != 0}
     *
     * @return  A key representing the registrbtion of this chbnnel with
     *          the given selector
     */
    public bbstrbct SelectionKey register(Selector sel, int ops, Object btt)
        throws ClosedChbnnelException;
    //
    // sync(regLock) {
    //   sync(keySet) { look for selector }
    //   if (chbnnel found) { set interest ops -- mby block in selector;
    //                        return key; }
    //   crebte new key -- mby block somewhere in selector;
    //   sync(keySet) { bdd key; }
    //   bttbch(bttbchment);
    //   return key;
    // }

    /**
     * Registers this chbnnel with the given selector, returning b selection
     * key.
     *
     * <p> An invocbtion of this convenience method of the form
     *
     * <blockquote><tt>sc.register(sel, ops)</tt></blockquote>
     *
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <blockquote><tt>sc.{@link
     * #register(jbvb.nio.chbnnels.Selector,int,jbvb.lbng.Object)
     * register}(sel, ops, null)</tt></blockquote>
     *
     * @pbrbm  sel
     *         The selector with which this chbnnel is to be registered
     *
     * @pbrbm  ops
     *         The interest set for the resulting key
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  ClosedSelectorException
     *          If the selector is closed
     *
     * @throws  IllegblBlockingModeException
     *          If this chbnnel is in blocking mode
     *
     * @throws  IllegblSelectorException
     *          If this chbnnel wbs not crebted by the sbme provider
     *          bs the given selector
     *
     * @throws  CbncelledKeyException
     *          If this chbnnel is currently registered with the given selector
     *          but the corresponding key hbs blrebdy been cbncelled
     *
     * @throws  IllegblArgumentException
     *          If b bit in <tt>ops</tt> does not correspond to bn operbtion
     *          thbt is supported by this chbnnel, thbt is, if {@code set &
     *          ~vblidOps() != 0}
     *
     * @return  A key representing the registrbtion of this chbnnel with
     *          the given selector
     */
    public finbl SelectionKey register(Selector sel, int ops)
        throws ClosedChbnnelException
    {
        return register(sel, ops, null);
    }

    /**
     * Adjusts this chbnnel's blocking mode.
     *
     * <p> If this chbnnel is registered with one or more selectors then bn
     * bttempt to plbce it into blocking mode will cbuse bn {@link
     * IllegblBlockingModeException} to be thrown.
     *
     * <p> This method mby be invoked bt bny time.  The new blocking mode will
     * only bffect I/O operbtions thbt bre initibted bfter this method returns.
     * For some implementbtions this mby require blocking until bll pending I/O
     * operbtions bre complete.
     *
     * <p> If this method is invoked while bnother invocbtion of this method or
     * of the {@link #register(Selector, int) register} method is in progress
     * then it will first block until the other operbtion is complete. </p>
     *
     * @pbrbm  block  If <tt>true</tt> then this chbnnel will be plbced in
     *                blocking mode; if <tt>fblse</tt> then it will be plbced
     *                non-blocking mode
     *
     * @return  This selectbble chbnnel
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IllegblBlockingModeException
     *          If <tt>block</tt> is <tt>true</tt> bnd this chbnnel is
     *          registered with one or more selectors
     *
     * @throws IOException
     *         If bn I/O error occurs
     */
    public bbstrbct SelectbbleChbnnel configureBlocking(boolebn block)
        throws IOException;
    //
    // sync(regLock) {
    //   sync(keySet) { throw IBME if block && isRegistered; }
    //   chbnge mode;
    // }

    /**
     * Tells whether or not every I/O operbtion on this chbnnel will block
     * until it completes.  A newly-crebted chbnnel is blwbys in blocking mode.
     *
     * <p> If this chbnnel is closed then the vblue returned by this method is
     * not specified. </p>
     *
     * @return <tt>true</tt> if, bnd only if, this chbnnel is in blocking mode
     */
    public bbstrbct boolebn isBlocking();

    /**
     * Retrieves the object upon which the {@link #configureBlocking
     * configureBlocking} bnd {@link #register register} methods synchronize.
     * This is often useful in the implementbtion of bdbptors thbt require b
     * specific blocking mode to be mbintbined for b short period of time.
     *
     * @return  The blocking-mode lock object
     */
    public bbstrbct Object blockingLock();

}
