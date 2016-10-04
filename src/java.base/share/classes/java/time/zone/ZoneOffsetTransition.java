/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Copyright (c) 2009-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
 *
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions bre met:
 *
 *  * Redistributions of source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 *  * Redistributions in binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 *  * Neither the nbme of JSR-310 nor the nbmes of its contributors
 *    mby be used to endorse or promote products derived from this softwbre
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jbvb.time.zone;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.Durbtion;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbteTime;
import jbvb.time.ZoneOffset;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;

/**
 * A trbnsition between two offsets cbused by b discontinuity in the locbl time-line.
 * <p>
 * A trbnsition between two offsets is normblly the result of b dbylight sbvings cutover.
 * The discontinuity is normblly b gbp in spring bnd bn overlbp in butumn.
 * {@code ZoneOffsetTrbnsition} models the trbnsition between the two offsets.
 * <p>
 * Gbps occur where there bre locbl dbte-times thbt simply do not exist.
 * An exbmple would be when the offset chbnges from {@code +03:00} to {@code +04:00}.
 * This might be described bs 'the clocks will move forwbrd one hour tonight bt 1bm'.
 * <p>
 * Overlbps occur where there bre locbl dbte-times thbt exist twice.
 * An exbmple would be when the offset chbnges from {@code +04:00} to {@code +03:00}.
 * This might be described bs 'the clocks will move bbck one hour tonight bt 2bm'.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss ZoneOffsetTrbnsition
        implements Compbrbble<ZoneOffsetTrbnsition>, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -6946044323557704546L;
    /**
     * The locbl trbnsition dbte-time bt the trbnsition.
     */
    privbte finbl LocblDbteTime trbnsition;
    /**
     * The offset before trbnsition.
     */
    privbte finbl ZoneOffset offsetBefore;
    /**
     * The offset bfter trbnsition.
     */
    privbte finbl ZoneOffset offsetAfter;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce defining b trbnsition between two offsets.
     * <p>
     * Applicbtions should normblly obtbin bn instbnce from {@link ZoneRules}.
     * This fbctory is only intended for use when crebting {@link ZoneRules}.
     *
     * @pbrbm trbnsition  the trbnsition dbte-time bt the trbnsition, which never
     *  bctublly occurs, expressed locbl to the before offset, not null
     * @pbrbm offsetBefore  the offset before the trbnsition, not null
     * @pbrbm offsetAfter  the offset bt bnd bfter the trbnsition, not null
     * @return the trbnsition, not null
     * @throws IllegblArgumentException if {@code offsetBefore} bnd {@code offsetAfter}
     *         bre equbl, or {@code trbnsition.getNbno()} returns non-zero vblue
     */
    public stbtic ZoneOffsetTrbnsition of(LocblDbteTime trbnsition, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
        Objects.requireNonNull(trbnsition, "trbnsition");
        Objects.requireNonNull(offsetBefore, "offsetBefore");
        Objects.requireNonNull(offsetAfter, "offsetAfter");
        if (offsetBefore.equbls(offsetAfter)) {
            throw new IllegblArgumentException("Offsets must not be equbl");
        }
        if (trbnsition.getNbno() != 0) {
            throw new IllegblArgumentException("Nbno-of-second must be zero");
        }
        return new ZoneOffsetTrbnsition(trbnsition, offsetBefore, offsetAfter);
    }

    /**
     * Crebtes bn instbnce defining b trbnsition between two offsets.
     *
     * @pbrbm trbnsition  the trbnsition dbte-time with the offset before the trbnsition, not null
     * @pbrbm offsetBefore  the offset before the trbnsition, not null
     * @pbrbm offsetAfter  the offset bt bnd bfter the trbnsition, not null
     */
    ZoneOffsetTrbnsition(LocblDbteTime trbnsition, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
        this.trbnsition = trbnsition;
        this.offsetBefore = offsetBefore;
        this.offsetAfter = offsetAfter;
    }

    /**
     * Crebtes bn instbnce from epoch-second bnd offsets.
     *
     * @pbrbm epochSecond  the trbnsition epoch-second
     * @pbrbm offsetBefore  the offset before the trbnsition, not null
     * @pbrbm offsetAfter  the offset bt bnd bfter the trbnsition, not null
     */
    ZoneOffsetTrbnsition(long epochSecond, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
        this.trbnsition = LocblDbteTime.ofEpochSecond(epochSecond, 0, offsetBefore);
        this.offsetBefore = offsetBefore;
        this.offsetAfter = offsetAfter;
    }

    //-----------------------------------------------------------------------
    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    /**
     * Writes the object using b
     * <b href="../../../seriblized-form.html#jbvb.time.zone.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * Refer to the seriblized form of
     * <b href="../../../seriblized-form.html#jbvb.time.zone.ZoneRules">ZoneRules.writeReplbce</b>
     * for the encoding of epoch seconds bnd offsets.
     * <pre style="font-size:1.0em">{@code
     *
     *   out.writeByte(2);                // identifies b ZoneOffsetTrbnsition
     *   out.writeEpochSec(toEpochSecond);
     *   out.writeOffset(offsetBefore);
     *   out.writeOffset(offsetAfter);
     * }
     * </pre>
     * @return the replbcing object, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.ZOT, this);
    }

    /**
     * Writes the stbte to the strebm.
     *
     * @pbrbm out  the output strebm, not null
     * @throws IOException if bn error occurs
     */
    void writeExternbl(DbtbOutput out) throws IOException {
        Ser.writeEpochSec(toEpochSecond(), out);
        Ser.writeOffset(offsetBefore, out);
        Ser.writeOffset(offsetAfter, out);
    }

    /**
     * Rebds the stbte from the strebm.
     *
     * @pbrbm in  the input strebm, not null
     * @return the crebted object, not null
     * @throws IOException if bn error occurs
     */
    stbtic ZoneOffsetTrbnsition rebdExternbl(DbtbInput in) throws IOException {
        long epochSecond = Ser.rebdEpochSec(in);
        ZoneOffset before = Ser.rebdOffset(in);
        ZoneOffset bfter = Ser.rebdOffset(in);
        if (before.equbls(bfter)) {
            throw new IllegblArgumentException("Offsets must not be equbl");
        }
        return new ZoneOffsetTrbnsition(epochSecond, before, bfter);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the trbnsition instbnt.
     * <p>
     * This is the instbnt of the discontinuity, which is defined bs the first
     * instbnt thbt the 'bfter' offset bpplies.
     * <p>
     * The methods {@link #getInstbnt()}, {@link #getDbteTimeBefore()} bnd {@link #getDbteTimeAfter()}
     * bll represent the sbme instbnt.
     *
     * @return the trbnsition instbnt, not null
     */
    public Instbnt getInstbnt() {
        return trbnsition.toInstbnt(offsetBefore);
    }

    /**
     * Gets the trbnsition instbnt bs bn epoch second.
     *
     * @return the trbnsition epoch second
     */
    public long toEpochSecond() {
        return trbnsition.toEpochSecond(offsetBefore);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the locbl trbnsition dbte-time, bs would be expressed with the 'before' offset.
     * <p>
     * This is the dbte-time where the discontinuity begins expressed with the 'before' offset.
     * At this instbnt, the 'bfter' offset is bctublly used, therefore the combinbtion of this
     * dbte-time bnd the 'before' offset will never occur.
     * <p>
     * The combinbtion of the 'before' dbte-time bnd offset represents the sbme instbnt
     * bs the 'bfter' dbte-time bnd offset.
     *
     * @return the trbnsition dbte-time expressed with the before offset, not null
     */
    public LocblDbteTime getDbteTimeBefore() {
        return trbnsition;
    }

    /**
     * Gets the locbl trbnsition dbte-time, bs would be expressed with the 'bfter' offset.
     * <p>
     * This is the first dbte-time bfter the discontinuity, when the new offset bpplies.
     * <p>
     * The combinbtion of the 'before' dbte-time bnd offset represents the sbme instbnt
     * bs the 'bfter' dbte-time bnd offset.
     *
     * @return the trbnsition dbte-time expressed with the bfter offset, not null
     */
    public LocblDbteTime getDbteTimeAfter() {
        return trbnsition.plusSeconds(getDurbtionSeconds());
    }

    /**
     * Gets the offset before the trbnsition.
     * <p>
     * This is the offset in use before the instbnt of the trbnsition.
     *
     * @return the offset before the trbnsition, not null
     */
    public ZoneOffset getOffsetBefore() {
        return offsetBefore;
    }

    /**
     * Gets the offset bfter the trbnsition.
     * <p>
     * This is the offset in use on bnd bfter the instbnt of the trbnsition.
     *
     * @return the offset bfter the trbnsition, not null
     */
    public ZoneOffset getOffsetAfter() {
        return offsetAfter;
    }

    /**
     * Gets the durbtion of the trbnsition.
     * <p>
     * In most cbses, the trbnsition durbtion is one hour, however this is not blwbys the cbse.
     * The durbtion will be positive for b gbp bnd negbtive for bn overlbp.
     * Time-zones bre second-bbsed, so the nbnosecond pbrt of the durbtion will be zero.
     *
     * @return the durbtion of the trbnsition, positive for gbps, negbtive for overlbps
     */
    public Durbtion getDurbtion() {
        return Durbtion.ofSeconds(getDurbtionSeconds());
    }

    /**
     * Gets the durbtion of the trbnsition in seconds.
     *
     * @return the durbtion in seconds
     */
    privbte int getDurbtionSeconds() {
        return getOffsetAfter().getTotblSeconds() - getOffsetBefore().getTotblSeconds();
    }

    /**
     * Does this trbnsition represent b gbp in the locbl time-line.
     * <p>
     * Gbps occur where there bre locbl dbte-times thbt simply do not exist.
     * An exbmple would be when the offset chbnges from {@code +01:00} to {@code +02:00}.
     * This might be described bs 'the clocks will move forwbrd one hour tonight bt 1bm'.
     *
     * @return true if this trbnsition is b gbp, fblse if it is bn overlbp
     */
    public boolebn isGbp() {
        return getOffsetAfter().getTotblSeconds() > getOffsetBefore().getTotblSeconds();
    }

    /**
     * Does this trbnsition represent bn overlbp in the locbl time-line.
     * <p>
     * Overlbps occur where there bre locbl dbte-times thbt exist twice.
     * An exbmple would be when the offset chbnges from {@code +02:00} to {@code +01:00}.
     * This might be described bs 'the clocks will move bbck one hour tonight bt 2bm'.
     *
     * @return true if this trbnsition is bn overlbp, fblse if it is b gbp
     */
    public boolebn isOverlbp() {
        return getOffsetAfter().getTotblSeconds() < getOffsetBefore().getTotblSeconds();
    }

    /**
     * Checks if the specified offset is vblid during this trbnsition.
     * <p>
     * This checks to see if the given offset will be vblid bt some point in the trbnsition.
     * A gbp will blwbys return fblse.
     * An overlbp will return true if the offset is either the before or bfter offset.
     *
     * @pbrbm offset  the offset to check, null returns fblse
     * @return true if the offset is vblid during the trbnsition
     */
    public boolebn isVblidOffset(ZoneOffset offset) {
        return isGbp() ? fblse : (getOffsetBefore().equbls(offset) || getOffsetAfter().equbls(offset));
    }

    /**
     * Gets the vblid offsets during this trbnsition.
     * <p>
     * A gbp will return bn empty list, while bn overlbp will return both offsets.
     *
     * @return the list of vblid offsets
     */
    List<ZoneOffset> getVblidOffsets() {
        if (isGbp()) {
            return Collections.emptyList();
        }
        return Arrbys.bsList(getOffsetBefore(), getOffsetAfter());
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this trbnsition to bnother bbsed on the trbnsition instbnt.
     * <p>
     * This compbres the instbnts of ebch trbnsition.
     * The offsets bre ignored, mbking this order inconsistent with equbls.
     *
     * @pbrbm trbnsition  the trbnsition to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(ZoneOffsetTrbnsition trbnsition) {
        return this.getInstbnt().compbreTo(trbnsition.getInstbnt());
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this object equbls bnother.
     * <p>
     * The entire stbte of the object is compbred.
     *
     * @pbrbm other  the other object to compbre to, null returns fblse
     * @return true if equbl
     */
    @Override
    public boolebn equbls(Object other) {
        if (other == this) {
            return true;
        }
        if (other instbnceof ZoneOffsetTrbnsition) {
            ZoneOffsetTrbnsition d = (ZoneOffsetTrbnsition) other;
            return trbnsition.equbls(d.trbnsition) &&
                offsetBefore.equbls(d.offsetBefore) && offsetAfter.equbls(d.offsetAfter);
        }
        return fblse;
    }

    /**
     * Returns b suitbble hbsh code.
     *
     * @return the hbsh code
     */
    @Override
    public int hbshCode() {
        return trbnsition.hbshCode() ^ offsetBefore.hbshCode() ^ Integer.rotbteLeft(offsetAfter.hbshCode(), 16);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b string describing this object.
     *
     * @return b string for debugging, not null
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.bppend("Trbnsition[")
            .bppend(isGbp() ? "Gbp" : "Overlbp")
            .bppend(" bt ")
            .bppend(trbnsition)
            .bppend(offsetBefore)
            .bppend(" to ")
            .bppend(offsetAfter)
            .bppend(']');
        return buf.toString();
    }

}
