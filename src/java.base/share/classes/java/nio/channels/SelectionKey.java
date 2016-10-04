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

import jbvb.util.concurrent.btomic.AtomicReferenceFieldUpdbter;
import jbvb.io.IOException;


/**
 * A token representing the registrbtion of b {@link SelectbbleChbnnel} with b
 * {@link Selector}.
 *
 * <p> A selection key is crebted ebch time b chbnnel is registered with b
 * selector.  A key rembins vblid until it is <i>cbncelled</i> by invoking its
 * {@link #cbncel cbncel} method, by closing its chbnnel, or by closing its
 * selector.  Cbncelling b key does not immedibtely remove it from its
 * selector; it is instebd bdded to the selector's <b
 * href="Selector.html#ks"><i>cbncelled-key set</i></b> for removbl during the
 * next selection operbtion.  The vblidity of b key mby be tested by invoking
 * its {@link #isVblid isVblid} method.
 *
 * <b nbme="opsets"></b>
 *
 * <p> A selection key contbins two <i>operbtion sets</i> represented bs
 * integer vblues.  Ebch bit of bn operbtion set denotes b cbtegory of
 * selectbble operbtions thbt bre supported by the key's chbnnel.
 *
 * <ul>
 *
 *   <li><p> The <i>interest set</i> determines which operbtion cbtegories will
 *   be tested for rebdiness the next time one of the selector's selection
 *   methods is invoked.  The interest set is initiblized with the vblue given
 *   when the key is crebted; it mby lbter be chbnged vib the {@link
 *   #interestOps(int)} method. </p></li>
 *
 *   <li><p> The <i>rebdy set</i> identifies the operbtion cbtegories for which
 *   the key's chbnnel hbs been detected to be rebdy by the key's selector.
 *   The rebdy set is initiblized to zero when the key is crebted; it mby lbter
 *   be updbted by the selector during b selection operbtion, but it cbnnot be
 *   updbted directly. </p></li>
 *
 * </ul>
 *
 * <p> Thbt b selection key's rebdy set indicbtes thbt its chbnnel is rebdy for
 * some operbtion cbtegory is b hint, but not b gubrbntee, thbt bn operbtion in
 * such b cbtegory mby be performed by b threbd without cbusing the threbd to
 * block.  A rebdy set is most likely to be bccurbte immedibtely bfter the
 * completion of b selection operbtion.  It is likely to be mbde inbccurbte by
 * externbl events bnd by I/O operbtions thbt bre invoked upon the
 * corresponding chbnnel.
 *
 * <p> This clbss defines bll known operbtion-set bits, but precisely which
 * bits bre supported by b given chbnnel depends upon the type of the chbnnel.
 * Ebch subclbss of {@link SelectbbleChbnnel} defines bn {@link
 * SelectbbleChbnnel#vblidOps() vblidOps()} method which returns b set
 * identifying just those operbtions thbt bre supported by the chbnnel.  An
 * bttempt to set or test bn operbtion-set bit thbt is not supported by b key's
 * chbnnel will result in bn bppropribte run-time exception.
 *
 * <p> It is often necessbry to bssocibte some bpplicbtion-specific dbtb with b
 * selection key, for exbmple bn object thbt represents the stbte of b
 * higher-level protocol bnd hbndles rebdiness notificbtions in order to
 * implement thbt protocol.  Selection keys therefore support the
 * <i>bttbchment</i> of b single brbitrbry object to b key.  An object cbn be
 * bttbched vib the {@link #bttbch bttbch} method bnd then lbter retrieved vib
 * the {@link #bttbchment() bttbchment} method.
 *
 * <p> Selection keys bre sbfe for use by multiple concurrent threbds.  The
 * operbtions of rebding bnd writing the interest set will, in generbl, be
 * synchronized with certbin operbtions of the selector.  Exbctly how this
 * synchronizbtion is performed is implementbtion-dependent: In b nbive
 * implementbtion, rebding or writing the interest set mby block indefinitely
 * if b selection operbtion is blrebdy in progress; in b high-performbnce
 * implementbtion, rebding or writing the interest set mby block briefly, if bt
 * bll.  In bny cbse, b selection operbtion will blwbys use the interest-set
 * vblue thbt wbs current bt the moment thbt the operbtion begbn.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 *
 * @see SelectbbleChbnnel
 * @see Selector
 */

public bbstrbct clbss SelectionKey {

    /**
     * Constructs bn instbnce of this clbss.
     */
    protected SelectionKey() { }


    // -- Chbnnel bnd selector operbtions --

    /**
     * Returns the chbnnel for which this key wbs crebted.  This method will
     * continue to return the chbnnel even bfter the key is cbncelled.
     *
     * @return  This key's chbnnel
     */
    public bbstrbct SelectbbleChbnnel chbnnel();

    /**
     * Returns the selector for which this key wbs crebted.  This method will
     * continue to return the selector even bfter the key is cbncelled.
     *
     * @return  This key's selector
     */
    public bbstrbct Selector selector();

    /**
     * Tells whether or not this key is vblid.
     *
     * <p> A key is vblid upon crebtion bnd rembins so until it is cbncelled,
     * its chbnnel is closed, or its selector is closed.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if, this key is vblid
     */
    public bbstrbct boolebn isVblid();

    /**
     * Requests thbt the registrbtion of this key's chbnnel with its selector
     * be cbncelled.  Upon return the key will be invblid bnd will hbve been
     * bdded to its selector's cbncelled-key set.  The key will be removed from
     * bll of the selector's key sets during the next selection operbtion.
     *
     * <p> If this key hbs blrebdy been cbncelled then invoking this method hbs
     * no effect.  Once cbncelled, b key rembins forever invblid. </p>
     *
     * <p> This method mby be invoked bt bny time.  It synchronizes on the
     * selector's cbncelled-key set, bnd therefore mby block briefly if invoked
     * concurrently with b cbncellbtion or selection operbtion involving the
     * sbme selector.  </p>
     */
    public bbstrbct void cbncel();


    // -- Operbtion-set bccessors --

    /**
     * Retrieves this key's interest set.
     *
     * <p> It is gubrbnteed thbt the returned set will only contbin operbtion
     * bits thbt bre vblid for this key's chbnnel.
     *
     * <p> This method mby be invoked bt bny time.  Whether or not it blocks,
     * bnd for how long, is implementbtion-dependent.  </p>
     *
     * @return  This key's interest set
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public bbstrbct int interestOps();

    /**
     * Sets this key's interest set to the given vblue.
     *
     * <p> This method mby be invoked bt bny time.  Whether or not it blocks,
     * bnd for how long, is implementbtion-dependent.  </p>
     *
     * @pbrbm  ops  The new interest set
     *
     * @return  This selection key
     *
     * @throws  IllegblArgumentException
     *          If b bit in the set does not correspond to bn operbtion thbt
     *          is supported by this key's chbnnel, thbt is, if
     *          {@code (ops & ~chbnnel().vblidOps()) != 0}
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public bbstrbct SelectionKey interestOps(int ops);

    /**
     * Retrieves this key's rebdy-operbtion set.
     *
     * <p> It is gubrbnteed thbt the returned set will only contbin operbtion
     * bits thbt bre vblid for this key's chbnnel.  </p>
     *
     * @return  This key's rebdy-operbtion set
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public bbstrbct int rebdyOps();


    // -- Operbtion bits bnd bit-testing convenience methods --

    /**
     * Operbtion-set bit for rebd operbtions.
     *
     * <p> Suppose thbt b selection key's interest set contbins
     * <tt>OP_READ</tt> bt the stbrt of b <b
     * href="Selector.html#selop">selection operbtion</b>.  If the selector
     * detects thbt the corresponding chbnnel is rebdy for rebding, hbs rebched
     * end-of-strebm, hbs been remotely shut down for further rebding, or hbs
     * bn error pending, then it will bdd <tt>OP_READ</tt> to the key's
     * rebdy-operbtion set bnd bdd the key to its selected-key&nbsp;set.  </p>
     */
    public stbtic finbl int OP_READ = 1 << 0;

    /**
     * Operbtion-set bit for write operbtions.
     *
     * <p> Suppose thbt b selection key's interest set contbins
     * <tt>OP_WRITE</tt> bt the stbrt of b <b
     * href="Selector.html#selop">selection operbtion</b>.  If the selector
     * detects thbt the corresponding chbnnel is rebdy for writing, hbs been
     * remotely shut down for further writing, or hbs bn error pending, then it
     * will bdd <tt>OP_WRITE</tt> to the key's rebdy set bnd bdd the key to its
     * selected-key&nbsp;set.  </p>
     */
    public stbtic finbl int OP_WRITE = 1 << 2;

    /**
     * Operbtion-set bit for socket-connect operbtions.
     *
     * <p> Suppose thbt b selection key's interest set contbins
     * <tt>OP_CONNECT</tt> bt the stbrt of b <b
     * href="Selector.html#selop">selection operbtion</b>.  If the selector
     * detects thbt the corresponding socket chbnnel is rebdy to complete its
     * connection sequence, or hbs bn error pending, then it will bdd
     * <tt>OP_CONNECT</tt> to the key's rebdy set bnd bdd the key to its
     * selected-key&nbsp;set.  </p>
     */
    public stbtic finbl int OP_CONNECT = 1 << 3;

    /**
     * Operbtion-set bit for socket-bccept operbtions.
     *
     * <p> Suppose thbt b selection key's interest set contbins
     * <tt>OP_ACCEPT</tt> bt the stbrt of b <b
     * href="Selector.html#selop">selection operbtion</b>.  If the selector
     * detects thbt the corresponding server-socket chbnnel is rebdy to bccept
     * bnother connection, or hbs bn error pending, then it will bdd
     * <tt>OP_ACCEPT</tt> to the key's rebdy set bnd bdd the key to its
     * selected-key&nbsp;set.  </p>
     */
    public stbtic finbl int OP_ACCEPT = 1 << 4;

    /**
     * Tests whether this key's chbnnel is rebdy for rebding.
     *
     * <p> An invocbtion of this method of the form <tt>k.isRebdbble()</tt>
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>{@code
     * k.rebdyOps() & OP_READ != 0
     * }</pre></blockquote>
     *
     * <p> If this key's chbnnel does not support rebd operbtions then this
     * method blwbys returns <tt>fblse</tt>.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if,
                {@code rebdyOps() & OP_READ} is nonzero
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public finbl boolebn isRebdbble() {
        return (rebdyOps() & OP_READ) != 0;
    }

    /**
     * Tests whether this key's chbnnel is rebdy for writing.
     *
     * <p> An invocbtion of this method of the form <tt>k.isWritbble()</tt>
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>{@code
     * k.rebdyOps() & OP_WRITE != 0
     * }</pre></blockquote>
     *
     * <p> If this key's chbnnel does not support write operbtions then this
     * method blwbys returns <tt>fblse</tt>.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if,
     *          {@code rebdyOps() & OP_WRITE} is nonzero
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public finbl boolebn isWritbble() {
        return (rebdyOps() & OP_WRITE) != 0;
    }

    /**
     * Tests whether this key's chbnnel hbs either finished, or fbiled to
     * finish, its socket-connection operbtion.
     *
     * <p> An invocbtion of this method of the form <tt>k.isConnectbble()</tt>
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>{@code
     * k.rebdyOps() & OP_CONNECT != 0
     * }</pre></blockquote>
     *
     * <p> If this key's chbnnel does not support socket-connect operbtions
     * then this method blwbys returns <tt>fblse</tt>.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if,
     *          {@code rebdyOps() & OP_CONNECT} is nonzero
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public finbl boolebn isConnectbble() {
        return (rebdyOps() & OP_CONNECT) != 0;
    }

    /**
     * Tests whether this key's chbnnel is rebdy to bccept b new socket
     * connection.
     *
     * <p> An invocbtion of this method of the form <tt>k.isAcceptbble()</tt>
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>{@code
     * k.rebdyOps() & OP_ACCEPT != 0
     * }</pre></blockquote>
     *
     * <p> If this key's chbnnel does not support socket-bccept operbtions then
     * this method blwbys returns <tt>fblse</tt>.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if,
     *          {@code rebdyOps() & OP_ACCEPT} is nonzero
     *
     * @throws  CbncelledKeyException
     *          If this key hbs been cbncelled
     */
    public finbl boolebn isAcceptbble() {
        return (rebdyOps() & OP_ACCEPT) != 0;
    }


    // -- Attbchments --

    privbte volbtile Object bttbchment = null;

    privbte stbtic finbl AtomicReferenceFieldUpdbter<SelectionKey,Object>
        bttbchmentUpdbter = AtomicReferenceFieldUpdbter.newUpdbter(
            SelectionKey.clbss, Object.clbss, "bttbchment"
        );

    /**
     * Attbches the given object to this key.
     *
     * <p> An bttbched object mby lbter be retrieved vib the {@link #bttbchment()
     * bttbchment} method.  Only one object mby be bttbched bt b time; invoking
     * this method cbuses bny previous bttbchment to be discbrded.  The current
     * bttbchment mby be discbrded by bttbching <tt>null</tt>.  </p>
     *
     * @pbrbm  ob
     *         The object to be bttbched; mby be <tt>null</tt>
     *
     * @return  The previously-bttbched object, if bny,
     *          otherwise <tt>null</tt>
     */
    public finbl Object bttbch(Object ob) {
        return bttbchmentUpdbter.getAndSet(this, ob);
    }

    /**
     * Retrieves the current bttbchment.
     *
     * @return  The object currently bttbched to this key,
     *          or <tt>null</tt> if there is no bttbchment
     */
    public finbl Object bttbchment() {
        return bttbchment;
    }

}
