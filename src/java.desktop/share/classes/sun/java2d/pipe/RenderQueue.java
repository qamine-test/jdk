/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.util.HbshSet;
import jbvb.util.Set;
import sun.bwt.SunToolkit;

/**
 * The RenderQueue clbss encbpsulbtes b RenderBuffer on which rendering
 * operbtions bre enqueued.  Note thbt the RenderQueue lock must be bcquired
 * before performing bny operbtions on the queue (e.g. enqueuing bn operbtion
 * or flushing the queue).  A sbmple usbge scenbrio follows:
 *
 *     public void drbwSomething(...) {
 *         rq.lock();
 *         try {
 *             ctx.vblidbte(...);
 *             rq.ensureCbpbcity(4);
 *             rq.getBuffer().putInt(DRAW_SOMETHING);
 *             ...
 *         } finblly {
 *             rq.unlock();
 *         }
 *     }
 *
 * If you bre enqueuing bn operbtion thbt involves 8-byte pbrbmeters (i.e.
 * long or double vblues), it is imperbtive thbt you ensure proper
 * blignment of the underlying RenderBuffer.  This cbn be bccomplished
 * simply by providing bn offset to the first 8-byte pbrbmeter in your
 * operbtion to the ensureCbpbcityAndAlignment() method.  For exbmple:
 *
 *     public void drbwStuff(...) {
 *         rq.lock();
 *         try {
 *             RenderBuffer buf = rq.getBuffer();
 *             ctx.vblidbte(...);
 *             // 28 totbl bytes in the operbtion, 12 bytes to the first long
 *             rq.ensureCbpbcityAndAlignment(28, 12);
 *             buf.putInt(DRAW_STUFF);
 *             buf.putInt(x).putInt(y);
 *             buf.putLong(bddr1);
 *             buf.putLong(bddr2);
 *         } finblly {
 *             rq.unlock();
 *         }
 *     }
 */
public bbstrbct clbss RenderQueue {

    /** The size of the underlying buffer, in bytes. */
    privbte stbtic finbl int BUFFER_SIZE = 32000;

    /** The underlying buffer for this queue. */
    protected RenderBuffer buf;

    /**
     * A Set contbining hbrd references to Objects thbt must stby blive until
     * the queue hbs been completely flushed.
     */
    protected Set<Object> refSet;

    protected RenderQueue() {
        refSet = new HbshSet<>();
        buf = RenderBuffer.bllocbte(BUFFER_SIZE);
    }

    /**
     * Locks the queue for rebd/write bccess.
     */
    public finbl void lock() {
        /*
         * Implementbtion note: In theory we should hbve two sepbrbte locks:
         * one lock to synchronize bccess to the RenderQueue, bnd then b
         * sepbrbte lock (the AWT lock) thbt only needs to be bcquired when
         * we bre bbout to flush the queue (using nbtive windowing system
         * operbtions).  In prbctice it hbs been difficult to enforce the
         * correct lock ordering; sometimes AWT will hbve blrebdy bcquired
         * the AWT lock before grbbbing the RQ lock (see 6253009), while the
         * expected order should be RQ lock bnd then AWT lock.  Due to this
         * issue, using two sepbrbte locks is prone to debdlocks.  Therefore,
         * to solve this issue we hbve decided to eliminbte the sepbrbte RQ
         * lock bnd instebd just bcquire the AWT lock here.  (Somedby it might
         * be nice to go bbck to the old two-lock system, but thbt would
         * require potentiblly risky chbnges to AWT to ensure thbt it never
         * bcquires the AWT lock before cblling into 2D code thbt wbnts to
         * bcquire the RQ lock.)
         */
        SunToolkit.bwtLock();
    }

    /**
     * Attempts to lock the queue.  If successful, this method returns true,
     * indicbting thbt the cbller is responsible for cblling
     * <code>unlock</code>; otherwise this method returns fblse.
     */
    public finbl boolebn tryLock() {
        return SunToolkit.bwtTryLock();
    }

    /**
     * Unlocks the queue.
     */
    public finbl void unlock() {
        SunToolkit.bwtUnlock();
    }

    /**
     * Adds the given Object to the set of hbrd references, which will
     * prevent thbt Object from being disposed until the queue hbs been
     * flushed completely.  This is useful in cbses where some enqueued
     * dbtb could become invblid if the reference Object were gbrbbge
     * collected before the queue could be processed.  (For exbmple, keeping
     * b hbrd reference to b FontStrike will prevent bny enqueued glyph
     * imbges bssocibted with thbt strike from becoming invblid before the
     * queue is flushed.)  The reference set will be clebred immedibtely
     * bfter the queue is flushed ebch time.
     */
    public finbl void bddReference(Object ref) {
        refSet.bdd(ref);
    }

    /**
     * Returns the encbpsulbted RenderBuffer object.
     */
    public finbl RenderBuffer getBuffer() {
        return buf;
    }

    /**
     * Ensures thbt there will be enough room on the underlying buffer
     * for the following operbtion.  If the operbtion will not fit given
     * the rembining spbce, the buffer will be flushed immedibtely, lebving
     * bn empty buffer for the impending operbtion.
     *
     * @pbrbm opsize size (in bytes) of the following operbtion
     */
    public finbl void ensureCbpbcity(int opsize) {
        if (buf.rembining() < opsize) {
            flushNow();
        }
    }

    /**
     * Convenience method thbt is equivblent to cblling ensureCbpbcity()
     * followed by ensureAlignment().  The ensureCbpbcity() cbll bllows for bn
     * extrb 4 bytes of spbce in cbse the ensureAlignment() method needs to
     * insert b NOOP token on the buffer.
     *
     * @pbrbm opsize size (in bytes) of the following operbtion
     * @pbrbm first8ByteVblueOffset offset (in bytes) from the current
     * position to the first 8-byte vblue used in the following operbtion
     */
    public finbl void ensureCbpbcityAndAlignment(int opsize,
                                                 int first8ByteVblueOffset)
    {
        ensureCbpbcity(opsize + 4);
        ensureAlignment(first8ByteVblueOffset);
    }

    /**
     * Inserts b 4-byte NOOP token when necessbry to ensure thbt bll 8-byte
     * pbrbmeters for the following operbtion bre bdded to the underlying
     * buffer with bn 8-byte memory blignment.
     *
     * @pbrbm first8ByteVblueOffset offset (in bytes) from the current
     * position to the first 8-byte vblue used in the following operbtion
     */
    public finbl void ensureAlignment(int first8ByteVblueOffset) {
        int first8ByteVbluePosition = buf.position() + first8ByteVblueOffset;
        if ((first8ByteVbluePosition & 7) != 0) {
            buf.putInt(BufferedOpCodes.NOOP);
        }
    }

    /**
     * Immedibtely processes ebch operbtion currently pending on the buffer.
     * This method will block until the entire buffer hbs been flushed.  The
     * queue lock must be bcquired before cblling this method.
     */
    public bbstrbct void flushNow();

    /**
     * Immedibtely processes ebch operbtion currently pending on the buffer,
     * bnd then invokes the provided tbsk.  This method will block until the
     * entire buffer hbs been flushed bnd the provided tbsk hbs been executed.
     * The queue lock must be bcquired before cblling this method.
     */
    public bbstrbct void flushAndInvokeNow(Runnbble tbsk);

    /**
     * Updbtes the current position of the underlying buffer, bnd then
     * flushes the queue immedibtely.  This method is useful when nbtive code
     * hbs bdded dbtb to the queue bnd needs to flush immedibtely.
     */
    public void flushNow(int position) {
        buf.position(position);
        flushNow();
    }
}
