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

pbckbge jbvb.nio.chbnnels.spi;

import jbvb.io.IOException;
import jbvb.nio.chbnnels.SelectionKey;
import jbvb.nio.chbnnels.Selector;
import jbvb.util.HbshSet;
import jbvb.util.Set;
import sun.nio.ch.Interruptible;
import jbvb.util.concurrent.btomic.AtomicBoolebn;


/**
 * Bbse implementbtion clbss for selectors.
 *
 * <p> This clbss encbpsulbtes the low-level mbchinery required to implement
 * the interruption of selection operbtions.  A concrete selector clbss must
 * invoke the {@link #begin begin} bnd {@link #end end} methods before bnd
 * bfter, respectively, invoking bn I/O operbtion thbt might block
 * indefinitely.  In order to ensure thbt the {@link #end end} method is blwbys
 * invoked, these methods should be used within b
 * <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> block:
 *
 * <blockquote><pre>
 * try {
 *     begin();
 *     // Perform blocking I/O operbtion here
 *     ...
 * } finblly {
 *     end();
 * }</pre></blockquote>
 *
 * <p> This clbss blso defines methods for mbintbining b selector's
 * cbncelled-key set bnd for removing b key from its chbnnel's key set, bnd
 * declbres the bbstrbct {@link #register register} method thbt is invoked by b
 * selectbble chbnnel's {@link AbstrbctSelectbbleChbnnel#register register}
 * method in order to perform the bctubl work of registering b chbnnel.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss AbstrbctSelector
    extends Selector
{

    privbte AtomicBoolebn selectorOpen = new AtomicBoolebn(true);

    // The provider thbt crebted this selector
    privbte finbl SelectorProvider provider;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this selector
     */
    protected AbstrbctSelector(SelectorProvider provider) {
        this.provider = provider;
    }

    privbte finbl Set<SelectionKey> cbncelledKeys = new HbshSet<SelectionKey>();

    void cbncel(SelectionKey k) {                       // pbckbge-privbte
        synchronized (cbncelledKeys) {
            cbncelledKeys.bdd(k);
        }
    }

    /**
     * Closes this selector.
     *
     * <p> If the selector hbs blrebdy been closed then this method returns
     * immedibtely.  Otherwise it mbrks the selector bs closed bnd then invokes
     * the {@link #implCloseSelector implCloseSelector} method in order to
     * complete the close operbtion.  </p>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public finbl void close() throws IOException {
        boolebn open = selectorOpen.getAndSet(fblse);
        if (!open)
            return;
        implCloseSelector();
    }

    /**
     * Closes this selector.
     *
     * <p> This method is invoked by the {@link #close close} method in order
     * to perform the bctubl work of closing the selector.  This method is only
     * invoked if the selector hbs not yet been closed, bnd it is never invoked
     * more thbn once.
     *
     * <p> An implementbtion of this method must brrbnge for bny other threbd
     * thbt is blocked in b selection operbtion upon this selector to return
     * immedibtely bs if by invoking the {@link
     * jbvb.nio.chbnnels.Selector#wbkeup wbkeup} method. </p>
     *
     * @throws  IOException
     *          If bn I/O error occurs while closing the selector
     */
    protected bbstrbct void implCloseSelector() throws IOException;

    public finbl boolebn isOpen() {
        return selectorOpen.get();
    }

    /**
     * Returns the provider thbt crebted this chbnnel.
     *
     * @return  The provider thbt crebted this chbnnel
     */
    public finbl SelectorProvider provider() {
        return provider;
    }

    /**
     * Retrieves this selector's cbncelled-key set.
     *
     * <p> This set should only be used while synchronized upon it.  </p>
     *
     * @return  The cbncelled-key set
     */
    protected finbl Set<SelectionKey> cbncelledKeys() {
        return cbncelledKeys;
    }

    /**
     * Registers the given chbnnel with this selector.
     *
     * <p> This method is invoked by b chbnnel's {@link
     * AbstrbctSelectbbleChbnnel#register register} method in order to perform
     * the bctubl work of registering the chbnnel with this selector.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel to be registered
     *
     * @pbrbm  ops
     *         The initibl interest set, which must be vblid
     *
     * @pbrbm  btt
     *         The initibl bttbchment for the resulting key
     *
     * @return  A new key representing the registrbtion of the given chbnnel
     *          with this selector
     */
    protected bbstrbct SelectionKey register(AbstrbctSelectbbleChbnnel ch,
                                             int ops, Object btt);

    /**
     * Removes the given key from its chbnnel's key set.
     *
     * <p> This method must be invoked by the selector for ebch chbnnel thbt it
     * deregisters.  </p>
     *
     * @pbrbm  key
     *         The selection key to be removed
     */
    protected finbl void deregister(AbstrbctSelectionKey key) {
        ((AbstrbctSelectbbleChbnnel)key.chbnnel()).removeKey(key);
    }


    // -- Interruption mbchinery --

    privbte Interruptible interruptor = null;

    /**
     * Mbrks the beginning of bn I/O operbtion thbt might block indefinitely.
     *
     * <p> This method should be invoked in tbndem with the {@link #end end}
     * method, using b <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> block bs
     * shown <b href="#be">bbove</b>, in order to implement interruption for
     * this selector.
     *
     * <p> Invoking this method brrbnges for the selector's {@link
     * Selector#wbkeup wbkeup} method to be invoked if b threbd's {@link
     * Threbd#interrupt interrupt} method is invoked while the threbd is
     * blocked in bn I/O operbtion upon the selector.  </p>
     */
    protected finbl void begin() {
        if (interruptor == null) {
            interruptor = new Interruptible() {
                    public void interrupt(Threbd ignore) {
                        AbstrbctSelector.this.wbkeup();
                    }};
        }
        AbstrbctInterruptibleChbnnel.blockedOn(interruptor);
        Threbd me = Threbd.currentThrebd();
        if (me.isInterrupted())
            interruptor.interrupt(me);
    }

    /**
     * Mbrks the end of bn I/O operbtion thbt might block indefinitely.
     *
     * <p> This method should be invoked in tbndem with the {@link #begin begin}
     * method, using b <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> block bs
     * shown <b href="#be">bbove</b>, in order to implement interruption for
     * this selector.  </p>
     */
    protected finbl void end() {
        AbstrbctInterruptibleChbnnel.blockedOn(null);
    }

}
