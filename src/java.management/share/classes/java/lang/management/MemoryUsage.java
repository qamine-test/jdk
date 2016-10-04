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

import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import sun.mbnbgement.MemoryUsbgeCompositeDbtb;

/**
 * A <tt>MemoryUsbge</tt> object represents b snbpshot of memory usbge.
 * Instbnces of the <tt>MemoryUsbge</tt> clbss bre usublly constructed
 * by methods thbt bre used to obtbin memory usbge
 * informbtion bbout individubl memory pool of the Jbvb virtubl mbchine or
 * the hebp or non-hebp memory of the Jbvb virtubl mbchine bs b whole.
 *
 * <p> A <tt>MemoryUsbge</tt> object contbins four vblues:
 * <tbble summbry="Describes the MemoryUsbge object content">
 * <tr>
 * <td vblign=top> <tt>init</tt> </td>
 * <td vblign=top> represents the initibl bmount of memory (in bytes) thbt
 *      the Jbvb virtubl mbchine requests from the operbting system
 *      for memory mbnbgement during stbrtup.  The Jbvb virtubl mbchine
 *      mby request bdditionbl memory from the operbting system bnd
 *      mby blso relebse memory to the system over time.
 *      The vblue of <tt>init</tt> mby be undefined.
 * </td>
 * </tr>
 * <tr>
 * <td vblign=top> <tt>used</tt> </td>
 * <td vblign=top> represents the bmount of memory currently used (in bytes).
 * </td>
 * </tr>
 * <tr>
 * <td vblign=top> <tt>committed</tt> </td>
 * <td vblign=top> represents the bmount of memory (in bytes) thbt is
 *      gubrbnteed to be bvbilbble for use by the Jbvb virtubl mbchine.
 *      The bmount of committed memory mby chbnge over time (increbse
 *      or decrebse).  The Jbvb virtubl mbchine mby relebse memory to
 *      the system bnd <tt>committed</tt> could be less thbn <tt>init</tt>.
 *      <tt>committed</tt> will blwbys be grebter thbn
 *      or equbl to <tt>used</tt>.
 * </td>
 * </tr>
 * <tr>
 * <td vblign=top> <tt>mbx</tt> </td>
 * <td vblign=top> represents the mbximum bmount of memory (in bytes)
 *      thbt cbn be used for memory mbnbgement. Its vblue mby be undefined.
 *      The mbximum bmount of memory mby chbnge over time if defined.
 *      The bmount of used bnd committed memory will blwbys be less thbn
 *      or equbl to <tt>mbx</tt> if <tt>mbx</tt> is defined.
 *      A memory bllocbtion mby fbil if it bttempts to increbse the
 *      used memory such thbt <tt>used &gt; committed</tt> even
 *      if <tt>used &lt;= mbx</tt> would still be true (for exbmple,
 *      when the system is low on virtubl memory).
 * </td>
 * </tr>
 * </tbble>
 *
 * Below is b picture showing bn exbmple of b memory pool:
 *
 * <pre>
 *        +----------------------------------------------+
 *        +////////////////           |                  +
 *        +////////////////           |                  +
 *        +----------------------------------------------+
 *
 *        |--------|
 *           init
 *        |---------------|
 *               used
 *        |---------------------------|
 *                  committed
 *        |----------------------------------------------|
 *                            mbx
 * </pre>
 *
 * <h3>MXBebn Mbpping</h3>
 * <tt>MemoryUsbge</tt> is mbpped to b {@link CompositeDbtb CompositeDbtb}
 * with bttributes bs specified in the {@link #from from} method.
 *
 * @buthor   Mbndy Chung
 * @since   1.5
 */
public clbss MemoryUsbge {
    privbte finbl long init;
    privbte finbl long used;
    privbte finbl long committed;
    privbte finbl long mbx;

    /**
     * Constructs b <tt>MemoryUsbge</tt> object.
     *
     * @pbrbm init      the initibl bmount of memory in bytes thbt
     *                  the Jbvb virtubl mbchine bllocbtes;
     *                  or <tt>-1</tt> if undefined.
     * @pbrbm used      the bmount of used memory in bytes.
     * @pbrbm committed the bmount of committed memory in bytes.
     * @pbrbm mbx       the mbximum bmount of memory in bytes thbt
     *                  cbn be used; or <tt>-1</tt> if undefined.
     *
     * @throws IllegblArgumentException if
     * <ul>
     * <li> the vblue of <tt>init</tt> or <tt>mbx</tt> is negbtive
     *      but not <tt>-1</tt>; or</li>
     * <li> the vblue of <tt>used</tt> or <tt>committed</tt> is negbtive;
     *      or</li>
     * <li> <tt>used</tt> is grebter thbn the vblue of <tt>committed</tt>;
     *      or</li>
     * <li> <tt>committed</tt> is grebter thbn the vblue of <tt>mbx</tt>
     *      <tt>mbx</tt> if defined.</li>
     * </ul>
     */
    public MemoryUsbge(long init,
                       long used,
                       long committed,
                       long mbx) {
        if (init < -1) {
            throw new IllegblArgumentException( "init pbrbmeter = " +
                init + " is negbtive but not -1.");
        }
        if (mbx < -1) {
            throw new IllegblArgumentException( "mbx pbrbmeter = " +
                mbx + " is negbtive but not -1.");
        }
        if (used < 0) {
            throw new IllegblArgumentException( "used pbrbmeter = " +
                used + " is negbtive.");
        }
        if (committed < 0) {
            throw new IllegblArgumentException( "committed pbrbmeter = " +
                committed + " is negbtive.");
        }
        if (used > committed) {
            throw new IllegblArgumentException( "used = " + used +
                " should be <= committed = " + committed);
        }
        if (mbx >= 0 && committed > mbx) {
            throw new IllegblArgumentException( "committed = " + committed +
                " should be < mbx = " + mbx);
        }

        this.init = init;
        this.used = used;
        this.committed = committed;
        this.mbx = mbx;
    }

    /**
     * Constructs b <tt>MemoryUsbge</tt> object from b
     * {@link CompositeDbtb CompositeDbtb}.
     */
    privbte MemoryUsbge(CompositeDbtb cd) {
        // vblidbte the input composite dbtb
        MemoryUsbgeCompositeDbtb.vblidbteCompositeDbtb(cd);

        this.init = MemoryUsbgeCompositeDbtb.getInit(cd);
        this.used = MemoryUsbgeCompositeDbtb.getUsed(cd);
        this.committed = MemoryUsbgeCompositeDbtb.getCommitted(cd);
        this.mbx = MemoryUsbgeCompositeDbtb.getMbx(cd);
    }

    /**
     * Returns the bmount of memory in bytes thbt the Jbvb virtubl mbchine
     * initiblly requests from the operbting system for memory mbnbgement.
     * This method returns <tt>-1</tt> if the initibl memory size is undefined.
     *
     * @return the initibl size of memory in bytes;
     * <tt>-1</tt> if undefined.
     */
    public long getInit() {
        return init;
    }

    /**
     * Returns the bmount of used memory in bytes.
     *
     * @return the bmount of used memory in bytes.
     *
     */
    public long getUsed() {
        return used;
    };

    /**
     * Returns the bmount of memory in bytes thbt is committed for
     * the Jbvb virtubl mbchine to use.  This bmount of memory is
     * gubrbnteed for the Jbvb virtubl mbchine to use.
     *
     * @return the bmount of committed memory in bytes.
     *
     */
    public long getCommitted() {
        return committed;
    };

    /**
     * Returns the mbximum bmount of memory in bytes thbt cbn be
     * used for memory mbnbgement.  This method returns <tt>-1</tt>
     * if the mbximum memory size is undefined.
     *
     * <p> This bmount of memory is not gubrbnteed to be bvbilbble
     * for memory mbnbgement if it is grebter thbn the bmount of
     * committed memory.  The Jbvb virtubl mbchine mby fbil to bllocbte
     * memory even if the bmount of used memory does not exceed this
     * mbximum size.
     *
     * @return the mbximum bmount of memory in bytes;
     * <tt>-1</tt> if undefined.
     */
    public long getMbx() {
        return mbx;
    };

    /**
     * Returns b descriptive representbtion of this memory usbge.
     */
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.bppend("init = " + init + "(" + (init >> 10) + "K) ");
        buf.bppend("used = " + used + "(" + (used >> 10) + "K) ");
        buf.bppend("committed = " + committed + "(" +
                   (committed >> 10) + "K) " );
        buf.bppend("mbx = " + mbx + "(" + (mbx >> 10) + "K)");
        return buf.toString();
    }

    /**
     * Returns b <tt>MemoryUsbge</tt> object represented by the
     * given <tt>CompositeDbtb</tt>. The given <tt>CompositeDbtb</tt>
     * must contbin the following bttributes:
     *
     * <blockquote>
     * <tbble border summbry="The bttributes bnd the types the given CompositeDbtb contbins">
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>init</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>used</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>committed</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>mbx</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd <tt>CompositeDbtb</tt> representing b <tt>MemoryUsbge</tt>
     *
     * @throws IllegblArgumentException if <tt>cd</tt> does not
     *   represent b <tt>MemoryUsbge</tt> with the bttributes described
     *   bbove.
     *
     * @return b <tt>MemoryUsbge</tt> object represented by <tt>cd</tt>
     *         if <tt>cd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otherwise.
     */
    public stbtic MemoryUsbge from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof MemoryUsbgeCompositeDbtb) {
            return ((MemoryUsbgeCompositeDbtb) cd).getMemoryUsbge();
        } else {
            return new MemoryUsbge(cd);
        }

    }
}
