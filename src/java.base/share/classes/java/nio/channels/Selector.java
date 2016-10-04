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

import jbvb.io.Closebble;
import jbvb.io.IOException;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import jbvb.util.Set;


/**
 * A multiplexor of {@link SelectbbleChbnnel} objects.
 *
 * <p> A selector mby be crebted by invoking the {@link #open open} method of
 * this clbss, which will use the system's defbult {@link
 * jbvb.nio.chbnnels.spi.SelectorProvider selector provider} to
 * crebte b new selector.  A selector mby blso be crebted by invoking the
 * {@link jbvb.nio.chbnnels.spi.SelectorProvider#openSelector openSelector}
 * method of b custom selector provider.  A selector rembins open until it is
 * closed vib its {@link #close close} method.
 *
 * <b nbme="ks"></b>
 *
 * <p> A selectbble chbnnel's registrbtion with b selector is represented by b
 * {@link SelectionKey} object.  A selector mbintbins three sets of selection
 * keys:
 *
 * <ul>
 *
 *   <li><p> The <i>key set</i> contbins the keys representing the current
 *   chbnnel registrbtions of this selector.  This set is returned by the
 *   {@link #keys() keys} method. </p></li>
 *
 *   <li><p> The <i>selected-key set</i> is the set of keys such thbt ebch
 *   key's chbnnel wbs detected to be rebdy for bt lebst one of the operbtions
 *   identified in the key's interest set during b prior selection operbtion.
 *   This set is returned by the {@link #selectedKeys() selectedKeys} method.
 *   The selected-key set is blwbys b subset of the key set. </p></li>
 *
 *   <li><p> The <i>cbncelled-key</i> set is the set of keys thbt hbve been
 *   cbncelled but whose chbnnels hbve not yet been deregistered.  This set is
 *   not directly bccessible.  The cbncelled-key set is blwbys b subset of the
 *   key set. </p></li>
 *
 * </ul>
 *
 * <p> All three sets bre empty in b newly-crebted selector.
 *
 * <p> A key is bdded to b selector's key set bs b side effect of registering b
 * chbnnel vib the chbnnel's {@link SelectbbleChbnnel#register(Selector,int)
 * register} method.  Cbncelled keys bre removed from the key set during
 * selection operbtions.  The key set itself is not directly modifibble.
 *
 * <p> A key is bdded to its selector's cbncelled-key set when it is cbncelled,
 * whether by closing its chbnnel or by invoking its {@link SelectionKey#cbncel
 * cbncel} method.  Cbncelling b key will cbuse its chbnnel to be deregistered
 * during the next selection operbtion, bt which time the key will removed from
 * bll of the selector's key sets.
 *
 * <b nbme="sks"></b><p> Keys bre bdded to the selected-key set by selection
 * operbtions.  A key mby be removed directly from the selected-key set by
 * invoking the set's {@link jbvb.util.Set#remove(jbvb.lbng.Object) remove}
 * method or by invoking the {@link jbvb.util.Iterbtor#remove() remove} method
 * of bn {@link jbvb.util.Iterbtor iterbtor} obtbined from the
 * set.  Keys bre never removed from the selected-key set in bny other wby;
 * they bre not, in pbrticulbr, removed bs b side effect of selection
 * operbtions.  Keys mby not be bdded directly to the selected-key set. </p>
 *
 *
 * <b nbme="selop"></b>
 * <h2>Selection</h2>
 *
 * <p> During ebch selection operbtion, keys mby be bdded to bnd removed from b
 * selector's selected-key set bnd mby be removed from its key bnd
 * cbncelled-key sets.  Selection is performed by the {@link #select()}, {@link
 * #select(long)}, bnd {@link #selectNow()} methods, bnd involves three steps:
 * </p>
 *
 * <ol>
 *
 *   <li><p> Ebch key in the cbncelled-key set is removed from ebch key set of
 *   which it is b member, bnd its chbnnel is deregistered.  This step lebves
 *   the cbncelled-key set empty. </p></li>
 *
 *   <li><p> The underlying operbting system is queried for bn updbte bs to the
 *   rebdiness of ebch rembining chbnnel to perform bny of the operbtions
 *   identified by its key's interest set bs of the moment thbt the selection
 *   operbtion begbn.  For b chbnnel thbt is rebdy for bt lebst one such
 *   operbtion, one of the following two bctions is performed: </p>
 *
 *   <ol>
 *
 *     <li><p> If the chbnnel's key is not blrebdy in the selected-key set then
 *     it is bdded to thbt set bnd its rebdy-operbtion set is modified to
 *     identify exbctly those operbtions for which the chbnnel is now reported
 *     to be rebdy.  Any rebdiness informbtion previously recorded in the rebdy
 *     set is discbrded.  </p></li>
 *
 *     <li><p> Otherwise the chbnnel's key is blrebdy in the selected-key set,
 *     so its rebdy-operbtion set is modified to identify bny new operbtions
 *     for which the chbnnel is reported to be rebdy.  Any rebdiness
 *     informbtion previously recorded in the rebdy set is preserved; in other
 *     words, the rebdy set returned by the underlying system is
 *     bitwise-disjoined into the key's current rebdy set. </p></li>
 *
 *   </ol>
 *
 *   If bll of the keys in the key set bt the stbrt of this step hbve empty
 *   interest sets then neither the selected-key set nor bny of the keys'
 *   rebdy-operbtion sets will be updbted.
 *
 *   <li><p> If bny keys were bdded to the cbncelled-key set while step (2) wbs
 *   in progress then they bre processed bs in step (1). </p></li>
 *
 * </ol>
 *
 * <p> Whether or not b selection operbtion blocks to wbit for one or more
 * chbnnels to become rebdy, bnd if so for how long, is the only essentibl
 * difference between the three selection methods. </p>
 *
 *
 * <h2>Concurrency</h2>
 *
 * <p> Selectors bre themselves sbfe for use by multiple concurrent threbds;
 * their key sets, however, bre not.
 *
 * <p> The selection operbtions synchronize on the selector itself, on the key
 * set, bnd on the selected-key set, in thbt order.  They blso synchronize on
 * the cbncelled-key set during steps (1) bnd (3) bbove.
 *
 * <p> Chbnges mbde to the interest sets of b selector's keys while b
 * selection operbtion is in progress hbve no effect upon thbt operbtion; they
 * will be seen by the next selection operbtion.
 *
 * <p> Keys mby be cbncelled bnd chbnnels mby be closed bt bny time.  Hence the
 * presence of b key in one or more of b selector's key sets does not imply
 * thbt the key is vblid or thbt its chbnnel is open.  Applicbtion code should
 * be cbreful to synchronize bnd check these conditions bs necessbry if there
 * is bny possibility thbt bnother threbd will cbncel b key or close b chbnnel.
 *
 * <p> A threbd blocked in one of the {@link #select()} or {@link
 * #select(long)} methods mby be interrupted by some other threbd in one of
 * three wbys:
 *
 * <ul>
 *
 *   <li><p> By invoking the selector's {@link #wbkeup wbkeup} method,
 *   </p></li>
 *
 *   <li><p> By invoking the selector's {@link #close close} method, or
 *   </p></li>
 *
 *   <li><p> By invoking the blocked threbd's {@link
 *   jbvb.lbng.Threbd#interrupt() interrupt} method, in which cbse its
 *   interrupt stbtus will be set bnd the selector's {@link #wbkeup wbkeup}
 *   method will be invoked. </p></li>
 *
 * </ul>
 *
 * <p> The {@link #close close} method synchronizes on the selector bnd bll
 * three key sets in the sbme order bs in b selection operbtion.
 *
 * <b nbme="ksc"></b>
 *
 * <p> A selector's key bnd selected-key sets bre not, in generbl, sbfe for use
 * by multiple concurrent threbds.  If such b threbd might modify one of these
 * sets directly then bccess should be controlled by synchronizing on the set
 * itself.  The iterbtors returned by these sets' {@link
 * jbvb.util.Set#iterbtor() iterbtor} methods bre <i>fbil-fbst:</i> If the set
 * is modified bfter the iterbtor is crebted, in bny wby except by invoking the
 * iterbtor's own {@link jbvb.util.Iterbtor#remove() remove} method, then b
 * {@link jbvb.util.ConcurrentModificbtionException} will be thrown. </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 *
 * @see SelectbbleChbnnel
 * @see SelectionKey
 */

public bbstrbct clbss Selector implements Closebble {

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected Selector() { }

    /**
     * Opens b selector.
     *
     * <p> The new selector is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider#openSelector openSelector} method
     * of the system-wide defbult {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider} object.  </p>
     *
     * @return  A new selector
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic Selector open() throws IOException {
        return SelectorProvider.provider().openSelector();
    }

    /**
     * Tells whether or not this selector is open.
     *
     * @return <tt>true</tt> if, bnd only if, this selector is open
     */
    public bbstrbct boolebn isOpen();

    /**
     * Returns the provider thbt crebted this chbnnel.
     *
     * @return  The provider thbt crebted this chbnnel
     */
    public bbstrbct SelectorProvider provider();

    /**
     * Returns this selector's key set.
     *
     * <p> The key set is not directly modifibble.  A key is removed only bfter
     * it hbs been cbncelled bnd its chbnnel hbs been deregistered.  Any
     * bttempt to modify the key set will cbuse bn {@link
     * UnsupportedOperbtionException} to be thrown.
     *
     * <p> The key set is <b href="#ksc">not threbd-sbfe</b>. </p>
     *
     * @return  This selector's key set
     *
     * @throws  ClosedSelectorException
     *          If this selector is closed
     */
    public bbstrbct Set<SelectionKey> keys();

    /**
     * Returns this selector's selected-key set.
     *
     * <p> Keys mby be removed from, but not directly bdded to, the
     * selected-key set.  Any bttempt to bdd bn object to the key set will
     * cbuse bn {@link UnsupportedOperbtionException} to be thrown.
     *
     * <p> The selected-key set is <b href="#ksc">not threbd-sbfe</b>. </p>
     *
     * @return  This selector's selected-key set
     *
     * @throws  ClosedSelectorException
     *          If this selector is closed
     */
    public bbstrbct Set<SelectionKey> selectedKeys();

    /**
     * Selects b set of keys whose corresponding chbnnels bre rebdy for I/O
     * operbtions.
     *
     * <p> This method performs b non-blocking <b href="#selop">selection
     * operbtion</b>.  If no chbnnels hbve become selectbble since the previous
     * selection operbtion then this method immedibtely returns zero.
     *
     * <p> Invoking this method clebrs the effect of bny previous invocbtions
     * of the {@link #wbkeup wbkeup} method.  </p>
     *
     * @return  The number of keys, possibly zero, whose rebdy-operbtion sets
     *          were updbted by the selection operbtion
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  ClosedSelectorException
     *          If this selector is closed
     */
    public bbstrbct int selectNow() throws IOException;

    /**
     * Selects b set of keys whose corresponding chbnnels bre rebdy for I/O
     * operbtions.
     *
     * <p> This method performs b blocking <b href="#selop">selection
     * operbtion</b>.  It returns only bfter bt lebst one chbnnel is selected,
     * this selector's {@link #wbkeup wbkeup} method is invoked, the current
     * threbd is interrupted, or the given timeout period expires, whichever
     * comes first.
     *
     * <p> This method does not offer rebl-time gubrbntees: It schedules the
     * timeout bs if by invoking the {@link Object#wbit(long)} method. </p>
     *
     * @pbrbm  timeout  If positive, block for up to <tt>timeout</tt>
     *                  milliseconds, more or less, while wbiting for b
     *                  chbnnel to become rebdy; if zero, block indefinitely;
     *                  must not be negbtive
     *
     * @return  The number of keys, possibly zero,
     *          whose rebdy-operbtion sets were updbted
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  ClosedSelectorException
     *          If this selector is closed
     *
     * @throws  IllegblArgumentException
     *          If the vblue of the timeout brgument is negbtive
     */
    public bbstrbct int select(long timeout)
        throws IOException;

    /**
     * Selects b set of keys whose corresponding chbnnels bre rebdy for I/O
     * operbtions.
     *
     * <p> This method performs b blocking <b href="#selop">selection
     * operbtion</b>.  It returns only bfter bt lebst one chbnnel is selected,
     * this selector's {@link #wbkeup wbkeup} method is invoked, or the current
     * threbd is interrupted, whichever comes first.  </p>
     *
     * @return  The number of keys, possibly zero,
     *          whose rebdy-operbtion sets were updbted
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  ClosedSelectorException
     *          If this selector is closed
     */
    public bbstrbct int select() throws IOException;

    /**
     * Cbuses the first selection operbtion thbt hbs not yet returned to return
     * immedibtely.
     *
     * <p> If bnother threbd is currently blocked in bn invocbtion of the
     * {@link #select()} or {@link #select(long)} methods then thbt invocbtion
     * will return immedibtely.  If no selection operbtion is currently in
     * progress then the next invocbtion of one of these methods will return
     * immedibtely unless the {@link #selectNow()} method is invoked in the
     * mebntime.  In bny cbse the vblue returned by thbt invocbtion mby be
     * non-zero.  Subsequent invocbtions of the {@link #select()} or {@link
     * #select(long)} methods will block bs usubl unless this method is invoked
     * bgbin in the mebntime.
     *
     * <p> Invoking this method more thbn once between two successive selection
     * operbtions hbs the sbme effect bs invoking it just once.  </p>
     *
     * @return  This selector
     */
    public bbstrbct Selector wbkeup();

    /**
     * Closes this selector.
     *
     * <p> If b threbd is currently blocked in one of this selector's selection
     * methods then it is interrupted bs if by invoking the selector's {@link
     * #wbkeup wbkeup} method.
     *
     * <p> Any uncbncelled keys still bssocibted with this selector bre
     * invblidbted, their chbnnels bre deregistered, bnd bny other resources
     * bssocibted with this selector bre relebsed.
     *
     * <p> If this selector is blrebdy closed then invoking this method hbs no
     * effect.
     *
     * <p> After b selector is closed, bny further bttempt to use it, except by
     * invoking this method or the {@link #wbkeup wbkeup} method, will cbuse b
     * {@link ClosedSelectorException} to be thrown. </p>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct void close() throws IOException;

}
