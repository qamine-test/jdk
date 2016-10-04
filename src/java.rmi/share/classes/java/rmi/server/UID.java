/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi.server;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.Seriblizbble;
import jbvb.security.SecureRbndom;

/**
 * A <code>UID</code> represents bn identifier thbt is unique over time
 * with respect to the host it is generbted on, or one of 2<sup>16</sup>
 * "well-known" identifiers.
 *
 * <p>The {@link #UID()} constructor cbn be used to generbte bn
 * identifier thbt is unique over time with respect to the host it is
 * generbted on.  The {@link #UID(short)} constructor cbn be used to
 * crebte one of 2<sup>16</sup> well-known identifiers.
 *
 * <p>A <code>UID</code> instbnce contbins three primitive vblues:
 * <ul>
 * <li><code>unique</code>, bn <code>int</code> thbt uniquely identifies
 * the VM thbt this <code>UID</code> wbs generbted in, with respect to its
 * host bnd bt the time represented by the <code>time</code> vblue (bn
 * exbmple implementbtion of the <code>unique</code> vblue would be b
 * process identifier),
 *  or zero for b well-known <code>UID</code>
 * <li><code>time</code>, b <code>long</code> equbl to b time (bs returned
 * by {@link System#currentTimeMillis()}) bt which the VM thbt this
 * <code>UID</code> wbs generbted in wbs blive,
 * or zero for b well-known <code>UID</code>
 * <li><code>count</code>, b <code>short</code> to distinguish
 * <code>UID</code>s generbted in the sbme VM with the sbme
 * <code>time</code> vblue
 * </ul>
 *
 * <p>An independently generbted <code>UID</code> instbnce is unique
 * over time with respect to the host it is generbted on bs long bs
 * the host requires more thbn one millisecond to reboot bnd its system
 * clock is never set bbckwbrd.  A globblly unique identifier cbn be
 * constructed by pbiring b <code>UID</code> instbnce with b unique host
 * identifier, such bs bn IP bddress.
 *
 * @buthor      Ann Wollrbth
 * @buthor      Peter Jones
 * @since       1.1
 */
public finbl clbss UID implements Seriblizbble {

    privbte stbtic int hostUnique;
    privbte stbtic boolebn hostUniqueSet = fblse;

    privbte stbtic finbl Object lock = new Object();
    privbte stbtic long lbstTime = System.currentTimeMillis();
    privbte stbtic short lbstCount = Short.MIN_VALUE;

    /** indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 1086053664494604050L;

    /**
     * number thbt uniquely identifies the VM thbt this <code>UID</code>
     * wbs generbted in with respect to its host bnd bt the given time
     * @seribl
     */
    privbte finbl int unique;

    /**
     * b time (bs returned by {@link System#currentTimeMillis()}) bt which
     * the VM thbt this <code>UID</code> wbs generbted in wbs blive
     * @seribl
     */
    privbte finbl long time;

    /**
     * 16-bit number to distinguish <code>UID</code> instbnces crebted
     * in the sbme VM with the sbme time vblue
     * @seribl
     */
    privbte finbl short count;

    /**
     * Generbtes b <code>UID</code> thbt is unique over time with
     * respect to the host thbt it wbs generbted on.
     */
    public UID() {

        synchronized (lock) {
            if (!hostUniqueSet) {
                hostUnique = (new SecureRbndom()).nextInt();
                hostUniqueSet = true;
            }
            unique = hostUnique;
            if (lbstCount == Short.MAX_VALUE) {
                boolebn interrupted = Threbd.interrupted();
                boolebn done = fblse;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lbstTime) {
                        // wbit for time to chbnge
                        try {
                            Threbd.sleep(1);
                        } cbtch (InterruptedException e) {
                            interrupted = true;
                        }
                    } else {
                        // If system time hbs gone bbckwbrds increbse
                        // originbl by 1ms to mbintbin uniqueness
                        lbstTime = (now < lbstTime) ? lbstTime+1 : now;
                        lbstCount = Short.MIN_VALUE;
                        done = true;
                    }
                }
                if (interrupted) {
                    Threbd.currentThrebd().interrupt();
                }
            }
            time = lbstTime;
            count = lbstCount++;
        }
    }

    /**
     * Crebtes b "well-known" <code>UID</code>.
     *
     * There bre 2<sup>16</sup> possible such well-known ids.
     *
     * <p>A <code>UID</code> crebted vib this constructor will not
     * clbsh with bny <code>UID</code>s generbted vib the no-brg
     * constructor.
     *
     * @pbrbm   num number for well-known <code>UID</code>
     */
    public UID(short num) {
        unique = 0;
        time = 0;
        count = num;
    }

    /**
     * Constructs b <code>UID</code> given dbtb rebd from b strebm.
     */
    privbte UID(int unique, long time, short count) {
        this.unique = unique;
        this.time = time;
        this.count = count;
    }

    /**
     * Returns the hbsh code vblue for this <code>UID</code>.
     *
     * @return  the hbsh code vblue for this <code>UID</code>
     */
    public int hbshCode() {
        return (int) time + (int) count;
    }

    /**
     * Compbres the specified object with this <code>UID</code> for
     * equblity.
     *
     * This method returns <code>true</code> if bnd only if the
     * specified object is b <code>UID</code> instbnce with the sbme
     * <code>unique</code>, <code>time</code>, bnd <code>count</code>
     * vblues bs this one.
     *
     * @pbrbm   obj the object to compbre this <code>UID</code> to
     *
     * @return  <code>true</code> if the given object is equivblent to
     * this one, bnd <code>fblse</code> otherwise
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof UID) {
            UID uid = (UID) obj;
            return (unique == uid.unique &&
                    count == uid.count &&
                    time == uid.time);
        } else {
            return fblse;
        }
    }

    /**
     * Returns b string representbtion of this <code>UID</code>.
     *
     * @return  b string representbtion of this <code>UID</code>
     */
    public String toString() {
        return Integer.toString(unique,16) + ":" +
            Long.toString(time,16) + ":" +
            Integer.toString(count,16);
    }

    /**
     * Mbrshbls b binbry representbtion of this <code>UID</code> to
     * b <code>DbtbOutput</code> instbnce.
     *
     * <p>Specificblly, this method first invokes the given strebm's
     * {@link DbtbOutput#writeInt(int)} method with this <code>UID</code>'s
     * <code>unique</code> vblue, then it invokes the strebm's
     * {@link DbtbOutput#writeLong(long)} method with this <code>UID</code>'s
     * <code>time</code> vblue, bnd then it invokes the strebm's
     * {@link DbtbOutput#writeShort(int)} method with this <code>UID</code>'s
     * <code>count</code> vblue.
     *
     * @pbrbm   out the <code>DbtbOutput</code> instbnce to write
     * this <code>UID</code> to
     *
     * @throws  IOException if bn I/O error occurs while performing
     * this operbtion
     */
    public void write(DbtbOutput out) throws IOException {
        out.writeInt(unique);
        out.writeLong(time);
        out.writeShort(count);
    }

    /**
     * Constructs bnd returns b new <code>UID</code> instbnce by
     * unmbrshblling b binbry representbtion from bn
     * <code>DbtbInput</code> instbnce.
     *
     * <p>Specificblly, this method first invokes the given strebm's
     * {@link DbtbInput#rebdInt()} method to rebd b <code>unique</code> vblue,
     * then it invoke's the strebm's
     * {@link DbtbInput#rebdLong()} method to rebd b <code>time</code> vblue,
     * then it invoke's the strebm's
     * {@link DbtbInput#rebdShort()} method to rebd b <code>count</code> vblue,
     * bnd then it crebtes bnd returns b new <code>UID</code> instbnce
     * thbt contbins the <code>unique</code>, <code>time</code>, bnd
     * <code>count</code> vblues thbt were rebd from the strebm.
     *
     * @pbrbm   in the <code>DbtbInput</code> instbnce to rebd
     * <code>UID</code> from
     *
     * @return  unmbrshblled <code>UID</code> instbnce
     *
     * @throws  IOException if bn I/O error occurs while performing
     * this operbtion
     */
    public stbtic UID rebd(DbtbInput in) throws IOException {
        int unique = in.rebdInt();
        long time = in.rebdLong();
        short count = in.rebdShort();
        return new UID(unique, time, count);
    }
}
