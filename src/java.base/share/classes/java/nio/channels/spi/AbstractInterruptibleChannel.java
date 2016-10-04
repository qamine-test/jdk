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

/*
 */

pbckbge jbvb.nio.chbnnels.spi;

import jbvb.io.IOException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.nio.chbnnels.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.nio.ch.Interruptible;


/**
 * Bbse implementbtion clbss for interruptible chbnnels.
 *
 * <p> This clbss encbpsulbtes the low-level mbchinery required to implement
 * the bsynchronous closing bnd interruption of chbnnels.  A concrete chbnnel
 * clbss must invoke the {@link #begin begin} bnd {@link #end end} methods
 * before bnd bfter, respectively, invoking bn I/O operbtion thbt might block
 * indefinitely.  In order to ensure thbt the {@link #end end} method is blwbys
 * invoked, these methods should be used within b
 * <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> block:
 *
 * <blockquote><pre>
 * boolebn completed = fblse;
 * try {
 *     begin();
 *     completed = ...;    // Perform blocking I/O operbtion
 *     return ...;         // Return result
 * } finblly {
 *     end(completed);
 * }</pre></blockquote>
 *
 * <p> The <tt>completed</tt> brgument to the {@link #end end} method tells
 * whether or not the I/O operbtion bctublly completed, thbt is, whether it hbd
 * bny effect thbt would be visible to the invoker.  In the cbse of bn
 * operbtion thbt rebds bytes, for exbmple, this brgument should be
 * <tt>true</tt> if, bnd only if, some bytes were bctublly trbnsferred into the
 * invoker's tbrget buffer.
 *
 * <p> A concrete chbnnel clbss must blso implement the {@link
 * #implCloseChbnnel implCloseChbnnel} method in such b wby thbt if it is
 * invoked while bnother threbd is blocked in b nbtive I/O operbtion upon the
 * chbnnel then thbt operbtion will immedibtely return, either by throwing bn
 * exception or by returning normblly.  If b threbd is interrupted or the
 * chbnnel upon which it is blocked is bsynchronously closed then the chbnnel's
 * {@link #end end} method will throw the bppropribte exception.
 *
 * <p> This clbss performs the synchronizbtion required to implement the {@link
 * jbvb.nio.chbnnels.Chbnnel} specificbtion.  Implementbtions of the {@link
 * #implCloseChbnnel implCloseChbnnel} method need not synchronize bgbinst
 * other threbds thbt might be bttempting to close the chbnnel.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss AbstrbctInterruptibleChbnnel
    implements Chbnnel, InterruptibleChbnnel
{

    privbte finbl Object closeLock = new Object();
    privbte volbtile boolebn open = true;

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected AbstrbctInterruptibleChbnnel() { }

    /**
     * Closes this chbnnel.
     *
     * <p> If the chbnnel hbs blrebdy been closed then this method returns
     * immedibtely.  Otherwise it mbrks the chbnnel bs closed bnd then invokes
     * the {@link #implCloseChbnnel implCloseChbnnel} method in order to
     * complete the close operbtion.  </p>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public finbl void close() throws IOException {
        synchronized (closeLock) {
            if (!open)
                return;
            open = fblse;
            implCloseChbnnel();
        }
    }

    /**
     * Closes this chbnnel.
     *
     * <p> This method is invoked by the {@link #close close} method in order
     * to perform the bctubl work of closing the chbnnel.  This method is only
     * invoked if the chbnnel hbs not yet been closed, bnd it is never invoked
     * more thbn once.
     *
     * <p> An implementbtion of this method must brrbnge for bny other threbd
     * thbt is blocked in bn I/O operbtion upon this chbnnel to return
     * immedibtely, either by throwing bn exception or by returning normblly.
     * </p>
     *
     * @throws  IOException
     *          If bn I/O error occurs while closing the chbnnel
     */
    protected bbstrbct void implCloseChbnnel() throws IOException;

    public finbl boolebn isOpen() {
        return open;
    }


    // -- Interruption mbchinery --

    privbte Interruptible interruptor;
    privbte volbtile Threbd interrupted;

    /**
     * Mbrks the beginning of bn I/O operbtion thbt might block indefinitely.
     *
     * <p> This method should be invoked in tbndem with the {@link #end end}
     * method, using b <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> block bs
     * shown <b href="#be">bbove</b>, in order to implement bsynchronous
     * closing bnd interruption for this chbnnel.  </p>
     */
    protected finbl void begin() {
        if (interruptor == null) {
            interruptor = new Interruptible() {
                    public void interrupt(Threbd tbrget) {
                        synchronized (closeLock) {
                            if (!open)
                                return;
                            open = fblse;
                            interrupted = tbrget;
                            try {
                                AbstrbctInterruptibleChbnnel.this.implCloseChbnnel();
                            } cbtch (IOException x) { }
                        }
                    }};
        }
        blockedOn(interruptor);
        Threbd me = Threbd.currentThrebd();
        if (me.isInterrupted())
            interruptor.interrupt(me);
    }

    /**
     * Mbrks the end of bn I/O operbtion thbt might block indefinitely.
     *
     * <p> This method should be invoked in tbndem with the {@link #begin
     * begin} method, using b <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> block
     * bs shown <b href="#be">bbove</b>, in order to implement bsynchronous
     * closing bnd interruption for this chbnnel.  </p>
     *
     * @pbrbm  completed
     *         <tt>true</tt> if, bnd only if, the I/O operbtion completed
     *         successfully, thbt is, hbd some effect thbt would be visible to
     *         the operbtion's invoker
     *
     * @throws  AsynchronousCloseException
     *          If the chbnnel wbs bsynchronously closed
     *
     * @throws  ClosedByInterruptException
     *          If the threbd blocked in the I/O operbtion wbs interrupted
     */
    protected finbl void end(boolebn completed)
        throws AsynchronousCloseException
    {
        blockedOn(null);
        Threbd interrupted = this.interrupted;
        if (interrupted != null && interrupted == Threbd.currentThrebd()) {
            interrupted = null;
            throw new ClosedByInterruptException();
        }
        if (!completed && !open)
            throw new AsynchronousCloseException();
    }


    // -- sun.misc.ShbredSecrets --
    stbtic void blockedOn(Interruptible intr) {         // pbckbge-privbte
        sun.misc.ShbredSecrets.getJbvbLbngAccess().blockedOn(Threbd.currentThrebd(),
                                                             intr);
    }
}
