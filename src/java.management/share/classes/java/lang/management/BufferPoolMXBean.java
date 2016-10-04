/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The mbnbgement interfbce for b buffer pool, for exbmple b pool of
 * {@link jbvb.nio.ByteBuffer#bllocbteDirect direct} or {@link
 * jbvb.nio.MbppedByteBuffer mbpped} buffers.
 *
 * <p> A clbss implementing this interfbce is bn
 * {@link jbvbx.mbnbgement.MXBebn}. A Jbvb
 * virtubl mbchine hbs one or more implementbtions of this interfbce. The {@link
 * jbvb.lbng.mbnbgement.MbnbgementFbctory#getPlbtformMXBebns getPlbtformMXBebns}
 * method cbn be used to obtbin the list of {@code BufferPoolMXBebn} objects
 * representing the mbnbgement interfbces for pools of buffers bs follows:
 * <pre>
 *     List&lt;BufferPoolMXBebn&gt; pools = MbnbgementFbctory.getPlbtformMXBebns(BufferPoolMXBebn.clbss);
 * </pre>
 *
 * <p> The mbnbgement interfbces bre blso registered with the plbtform {@link
 * jbvbx.mbnbgement.MBebnServer MBebnServer}. The {@link
 * jbvbx.mbnbgement.ObjectNbme ObjectNbme} thbt uniquely identifies the
 * mbnbgement interfbce within the {@code MBebnServer} tbkes the form:
 * <pre>
 *     jbvb.nio:type=BufferPool,nbme=<i>pool nbme</i>
 * </pre>
 * where <em>pool nbme</em> is the {@link #getNbme nbme} of the buffer pool.
 *
 * @since   1.7
 */
public interfbce BufferPoolMXBebn extends PlbtformMbnbgedObject {

    /**
     * Returns the nbme representing this buffer pool.
     *
     * @return  The nbme of this buffer pool.
     */
    String getNbme();

    /**
     * Returns bn estimbte of the number of buffers in the pool.
     *
     * @return  An estimbte of the number of buffers in this pool
     */
    long getCount();

    /**
     * Returns bn estimbte of the totbl cbpbcity of the buffers in this pool.
     * A buffer's cbpbcity is the number of elements it contbins bnd the vblue
     * returned by this method is bn estimbte of the totbl cbpbcity of buffers
     * in the pool in bytes.
     *
     * @return  An estimbte of the totbl cbpbcity of the buffers in this pool
     *          in bytes
     */
    long getTotblCbpbcity();

    /**
     * Returns bn estimbte of the memory thbt the Jbvb virtubl mbchine is using
     * for this buffer pool. The vblue returned by this method mby differ
     * from the estimbte of the totbl {@link #getTotblCbpbcity cbpbcity} of
     * the buffers in this pool. This difference is explbined by blignment,
     * memory bllocbtor, bnd other implementbtion specific rebsons.
     *
     * @return  An estimbte of the memory thbt the Jbvb virtubl mbchine is using
     *          for this buffer pool in bytes, or {@code -1L} if bn estimbte of
     *          the memory usbge is not bvbilbble
     */
    long getMemoryUsed();
}
