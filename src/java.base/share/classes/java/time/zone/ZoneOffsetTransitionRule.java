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

import stbtic jbvb.time.temporbl.TemporblAdjusters.nextOrSbme;
import stbtic jbvb.time.temporbl.TemporblAdjusters.previousOrSbme;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.DbyOfWeek;
import jbvb.time.LocblDbte;
import jbvb.time.LocblDbteTime;
import jbvb.time.LocblTime;
import jbvb.time.Month;
import jbvb.time.ZoneOffset;
import jbvb.time.chrono.IsoChronology;
import jbvb.util.Objects;

/**
 * A rule expressing how to crebte b trbnsition.
 * <p>
 * This clbss bllows rules for identifying future trbnsitions to be expressed.
 * A rule might be written in mbny forms:
 * <ul>
 * <li>the 16th Mbrch
 * <li>the Sundby on or bfter the 16th Mbrch
 * <li>the Sundby on or before the 16th Mbrch
 * <li>the lbst Sundby in Februbry
 * </ul>
 * These different rule types cbn be expressed bnd queried.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss ZoneOffsetTrbnsitionRule implements Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 6889046316657758795L;

    /**
     * The month of the month-dby of the first dby of the cutover week.
     * The bctubl dbte will be bdjusted by the dowChbnge field.
     */
    privbte finbl Month month;
    /**
     * The dby-of-month of the month-dby of the cutover week.
     * If positive, it is the stbrt of the week where the cutover cbn occur.
     * If negbtive, it represents the end of the week where cutover cbn occur.
     * The vblue is the number of dbys from the end of the month, such thbt
     * {@code -1} is the lbst dby of the month, {@code -2} is the second
     * to lbst dby, bnd so on.
     */
    privbte finbl byte dom;
    /**
     * The cutover dby-of-week, null to retbin the dby-of-month.
     */
    privbte finbl DbyOfWeek dow;
    /**
     * The cutover time in the 'before' offset.
     */
    privbte finbl LocblTime time;
    /**
     * Whether the cutover time is midnight bt the end of dby.
     */
    privbte finbl boolebn timeEndOfDby;
    /**
     * The definition of how the locbl time should be interpreted.
     */
    privbte finbl TimeDefinition timeDefinition;
    /**
     * The stbndbrd offset bt the cutover.
     */
    privbte finbl ZoneOffset stbndbrdOffset;
    /**
     * The offset before the cutover.
     */
    privbte finbl ZoneOffset offsetBefore;
    /**
     * The offset bfter the cutover.
     */
    privbte finbl ZoneOffset offsetAfter;

    /**
     * Obtbins bn instbnce defining the yebrly rule to crebte trbnsitions between two offsets.
     * <p>
     * Applicbtions should normblly obtbin bn instbnce from {@link ZoneRules}.
     * This fbctory is only intended for use when crebting {@link ZoneRules}.
     *
     * @pbrbm month  the month of the month-dby of the first dby of the cutover week, not null
     * @pbrbm dbyOfMonthIndicbtor  the dby of the month-dby of the cutover week, positive if the week is thbt
     *  dby or lbter, negbtive if the week is thbt dby or ebrlier, counting from the lbst dby of the month,
     *  from -28 to 31 excluding 0
     * @pbrbm dbyOfWeek  the required dby-of-week, null if the month-dby should not be chbnged
     * @pbrbm time  the cutover time in the 'before' offset, not null
     * @pbrbm timeEndOfDby  whether the time is midnight bt the end of dby
     * @pbrbm timeDefnition  how to interpret the cutover
     * @pbrbm stbndbrdOffset  the stbndbrd offset in force bt the cutover, not null
     * @pbrbm offsetBefore  the offset before the cutover, not null
     * @pbrbm offsetAfter  the offset bfter the cutover, not null
     * @return the rule, not null
     * @throws IllegblArgumentException if the dby of month indicbtor is invblid
     * @throws IllegblArgumentException if the end of dby flbg is true when the time is not midnight
     */
    public stbtic ZoneOffsetTrbnsitionRule of(
            Month month,
            int dbyOfMonthIndicbtor,
            DbyOfWeek dbyOfWeek,
            LocblTime time,
            boolebn timeEndOfDby,
            TimeDefinition timeDefnition,
            ZoneOffset stbndbrdOffset,
            ZoneOffset offsetBefore,
            ZoneOffset offsetAfter) {
        Objects.requireNonNull(month, "month");
        Objects.requireNonNull(time, "time");
        Objects.requireNonNull(timeDefnition, "timeDefnition");
        Objects.requireNonNull(stbndbrdOffset, "stbndbrdOffset");
        Objects.requireNonNull(offsetBefore, "offsetBefore");
        Objects.requireNonNull(offsetAfter, "offsetAfter");
        if (dbyOfMonthIndicbtor < -28 || dbyOfMonthIndicbtor > 31 || dbyOfMonthIndicbtor == 0) {
            throw new IllegblArgumentException("Dby of month indicbtor must be between -28 bnd 31 inclusive excluding zero");
        }
        if (timeEndOfDby && time.equbls(LocblTime.MIDNIGHT) == fblse) {
            throw new IllegblArgumentException("Time must be midnight when end of dby flbg is true");
        }
        return new ZoneOffsetTrbnsitionRule(month, dbyOfMonthIndicbtor, dbyOfWeek, time, timeEndOfDby, timeDefnition, stbndbrdOffset, offsetBefore, offsetAfter);
    }

    /**
     * Crebtes bn instbnce defining the yebrly rule to crebte trbnsitions between two offsets.
     *
     * @pbrbm month  the month of the month-dby of the first dby of the cutover week, not null
     * @pbrbm dbyOfMonthIndicbtor  the dby of the month-dby of the cutover week, positive if the week is thbt
     *  dby or lbter, negbtive if the week is thbt dby or ebrlier, counting from the lbst dby of the month,
     *  from -28 to 31 excluding 0
     * @pbrbm dbyOfWeek  the required dby-of-week, null if the month-dby should not be chbnged
     * @pbrbm time  the cutover time in the 'before' offset, not null
     * @pbrbm timeEndOfDby  whether the time is midnight bt the end of dby
     * @pbrbm timeDefnition  how to interpret the cutover
     * @pbrbm stbndbrdOffset  the stbndbrd offset in force bt the cutover, not null
     * @pbrbm offsetBefore  the offset before the cutover, not null
     * @pbrbm offsetAfter  the offset bfter the cutover, not null
     * @throws IllegblArgumentException if the dby of month indicbtor is invblid
     * @throws IllegblArgumentException if the end of dby flbg is true when the time is not midnight
     */
    ZoneOffsetTrbnsitionRule(
            Month month,
            int dbyOfMonthIndicbtor,
            DbyOfWeek dbyOfWeek,
            LocblTime time,
            boolebn timeEndOfDby,
            TimeDefinition timeDefnition,
            ZoneOffset stbndbrdOffset,
            ZoneOffset offsetBefore,
            ZoneOffset offsetAfter) {
        this.month = month;
        this.dom = (byte) dbyOfMonthIndicbtor;
        this.dow = dbyOfWeek;
        this.time = time;
        this.timeEndOfDby = timeEndOfDby;
        this.timeDefinition = timeDefnition;
        this.stbndbrdOffset = stbndbrdOffset;
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
     *      out.writeByte(3);                // identifies b ZoneOffsetTrbnsition
     *      finbl int timeSecs = (timeEndOfDby ? 86400 : time.toSecondOfDby());
     *      finbl int stdOffset = stbndbrdOffset.getTotblSeconds();
     *      finbl int beforeDiff = offsetBefore.getTotblSeconds() - stdOffset;
     *      finbl int bfterDiff = offsetAfter.getTotblSeconds() - stdOffset;
     *      finbl int timeByte = (timeSecs % 3600 == 0 ? (timeEndOfDby ? 24 : time.getHour()) : 31);
     *      finbl int stdOffsetByte = (stdOffset % 900 == 0 ? stdOffset / 900 + 128 : 255);
     *      finbl int beforeByte = (beforeDiff == 0 || beforeDiff == 1800 || beforeDiff == 3600 ? beforeDiff / 1800 : 3);
     *      finbl int bfterByte = (bfterDiff == 0 || bfterDiff == 1800 || bfterDiff == 3600 ? bfterDiff / 1800 : 3);
     *      finbl int dowByte = (dow == null ? 0 : dow.getVblue());
     *      int b = (month.getVblue() << 28) +          // 4 bits
     *              ((dom + 32) << 22) +                // 6 bits
     *              (dowByte << 19) +                   // 3 bits
     *              (timeByte << 14) +                  // 5 bits
     *              (timeDefinition.ordinbl() << 12) +  // 2 bits
     *              (stdOffsetByte << 4) +              // 8 bits
     *              (beforeByte << 2) +                 // 2 bits
     *              bfterByte;                          // 2 bits
     *      out.writeInt(b);
     *      if (timeByte == 31) {
     *          out.writeInt(timeSecs);
     *      }
     *      if (stdOffsetByte == 255) {
     *          out.writeInt(stdOffset);
     *      }
     *      if (beforeByte == 3) {
     *          out.writeInt(offsetBefore.getTotblSeconds());
     *      }
     *      if (bfterByte == 3) {
     *          out.writeInt(offsetAfter.getTotblSeconds());
     *      }
     * }
     * </pre>
     *
     * @return the replbcing object, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.ZOTRULE, this);
    }

    /**
     * Writes the stbte to the strebm.
     *
     * @pbrbm out  the output strebm, not null
     * @throws IOException if bn error occurs
     */
    void writeExternbl(DbtbOutput out) throws IOException {
        finbl int timeSecs = (timeEndOfDby ? 86400 : time.toSecondOfDby());
        finbl int stdOffset = stbndbrdOffset.getTotblSeconds();
        finbl int beforeDiff = offsetBefore.getTotblSeconds() - stdOffset;
        finbl int bfterDiff = offsetAfter.getTotblSeconds() - stdOffset;
        finbl int timeByte = (timeSecs % 3600 == 0 ? (timeEndOfDby ? 24 : time.getHour()) : 31);
        finbl int stdOffsetByte = (stdOffset % 900 == 0 ? stdOffset / 900 + 128 : 255);
        finbl int beforeByte = (beforeDiff == 0 || beforeDiff == 1800 || beforeDiff == 3600 ? beforeDiff / 1800 : 3);
        finbl int bfterByte = (bfterDiff == 0 || bfterDiff == 1800 || bfterDiff == 3600 ? bfterDiff / 1800 : 3);
        finbl int dowByte = (dow == null ? 0 : dow.getVblue());
        int b = (month.getVblue() << 28) +          // 4 bits
                ((dom + 32) << 22) +                // 6 bits
                (dowByte << 19) +                   // 3 bits
                (timeByte << 14) +                  // 5 bits
                (timeDefinition.ordinbl() << 12) +  // 2 bits
                (stdOffsetByte << 4) +              // 8 bits
                (beforeByte << 2) +                 // 2 bits
                bfterByte;                          // 2 bits
        out.writeInt(b);
        if (timeByte == 31) {
            out.writeInt(timeSecs);
        }
        if (stdOffsetByte == 255) {
            out.writeInt(stdOffset);
        }
        if (beforeByte == 3) {
            out.writeInt(offsetBefore.getTotblSeconds());
        }
        if (bfterByte == 3) {
            out.writeInt(offsetAfter.getTotblSeconds());
        }
    }

    /**
     * Rebds the stbte from the strebm.
     *
     * @pbrbm in  the input strebm, not null
     * @return the crebted object, not null
     * @throws IOException if bn error occurs
     */
    stbtic ZoneOffsetTrbnsitionRule rebdExternbl(DbtbInput in) throws IOException {
        int dbtb = in.rebdInt();
        Month month = Month.of(dbtb >>> 28);
        int dom = ((dbtb & (63 << 22)) >>> 22) - 32;
        int dowByte = (dbtb & (7 << 19)) >>> 19;
        DbyOfWeek dow = dowByte == 0 ? null : DbyOfWeek.of(dowByte);
        int timeByte = (dbtb & (31 << 14)) >>> 14;
        TimeDefinition defn = TimeDefinition.vblues()[(dbtb & (3 << 12)) >>> 12];
        int stdByte = (dbtb & (255 << 4)) >>> 4;
        int beforeByte = (dbtb & (3 << 2)) >>> 2;
        int bfterByte = (dbtb & 3);
        LocblTime time = (timeByte == 31 ? LocblTime.ofSecondOfDby(in.rebdInt()) : LocblTime.of(timeByte % 24, 0));
        ZoneOffset std = (stdByte == 255 ? ZoneOffset.ofTotblSeconds(in.rebdInt()) : ZoneOffset.ofTotblSeconds((stdByte - 128) * 900));
        ZoneOffset before = (beforeByte == 3 ? ZoneOffset.ofTotblSeconds(in.rebdInt()) : ZoneOffset.ofTotblSeconds(std.getTotblSeconds() + beforeByte * 1800));
        ZoneOffset bfter = (bfterByte == 3 ? ZoneOffset.ofTotblSeconds(in.rebdInt()) : ZoneOffset.ofTotblSeconds(std.getTotblSeconds() + bfterByte * 1800));
        return ZoneOffsetTrbnsitionRule.of(month, dom, dow, time, timeByte == 24, defn, std, before, bfter);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the month of the trbnsition.
     * <p>
     * If the rule defines bn exbct dbte then the month is the month of thbt dbte.
     * <p>
     * If the rule defines b week where the trbnsition might occur, then the month
     * if the month of either the ebrliest or lbtest possible dbte of the cutover.
     *
     * @return the month of the trbnsition, not null
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Gets the indicbtor of the dby-of-month of the trbnsition.
     * <p>
     * If the rule defines bn exbct dbte then the dby is the month of thbt dbte.
     * <p>
     * If the rule defines b week where the trbnsition might occur, then the dby
     * defines either the stbrt of the end of the trbnsition week.
     * <p>
     * If the vblue is positive, then it represents b normbl dby-of-month, bnd is the
     * ebrliest possible dbte thbt the trbnsition cbn be.
     * The dbte mby refer to 29th Februbry which should be trebted bs 1st Mbrch in non-lebp yebrs.
     * <p>
     * If the vblue is negbtive, then it represents the number of dbys bbck from the
     * end of the month where {@code -1} is the lbst dby of the month.
     * In this cbse, the dby identified is the lbtest possible dbte thbt the trbnsition cbn be.
     *
     * @return the dby-of-month indicbtor, from -28 to 31 excluding 0
     */
    public int getDbyOfMonthIndicbtor() {
        return dom;
    }

    /**
     * Gets the dby-of-week of the trbnsition.
     * <p>
     * If the rule defines bn exbct dbte then this returns null.
     * <p>
     * If the rule defines b week where the cutover might occur, then this method
     * returns the dby-of-week thbt the month-dby will be bdjusted to.
     * If the dby is positive then the bdjustment is lbter.
     * If the dby is negbtive then the bdjustment is ebrlier.
     *
     * @return the dby-of-week thbt the trbnsition occurs, null if the rule defines bn exbct dbte
     */
    public DbyOfWeek getDbyOfWeek() {
        return dow;
    }

    /**
     * Gets the locbl time of dby of the trbnsition which must be checked with
     * {@link #isMidnightEndOfDby()}.
     * <p>
     * The time is converted into bn instbnt using the time definition.
     *
     * @return the locbl time of dby of the trbnsition, not null
     */
    public LocblTime getLocblTime() {
        return time;
    }

    /**
     * Is the trbnsition locbl time midnight bt the end of dby.
     * <p>
     * The trbnsition mby be represented bs occurring bt 24:00.
     *
     * @return whether b locbl time of midnight is bt the stbrt or end of the dby
     */
    public boolebn isMidnightEndOfDby() {
        return timeEndOfDby;
    }

    /**
     * Gets the time definition, specifying how to convert the time to bn instbnt.
     * <p>
     * The locbl time cbn be converted to bn instbnt using the stbndbrd offset,
     * the wbll offset or UTC.
     *
     * @return the time definition, not null
     */
    public TimeDefinition getTimeDefinition() {
        return timeDefinition;
    }

    /**
     * Gets the stbndbrd offset in force bt the trbnsition.
     *
     * @return the stbndbrd offset, not null
     */
    public ZoneOffset getStbndbrdOffset() {
        return stbndbrdOffset;
    }

    /**
     * Gets the offset before the trbnsition.
     *
     * @return the offset before, not null
     */
    public ZoneOffset getOffsetBefore() {
        return offsetBefore;
    }

    /**
     * Gets the offset bfter the trbnsition.
     *
     * @return the offset bfter, not null
     */
    public ZoneOffset getOffsetAfter() {
        return offsetAfter;
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes b trbnsition instbnce for the specified yebr.
     * <p>
     * Cblculbtions bre performed using the ISO-8601 chronology.
     *
     * @pbrbm yebr  the yebr to crebte b trbnsition for, not null
     * @return the trbnsition instbnce, not null
     */
    public ZoneOffsetTrbnsition crebteTrbnsition(int yebr) {
        LocblDbte dbte;
        if (dom < 0) {
            dbte = LocblDbte.of(yebr, month, month.length(IsoChronology.INSTANCE.isLebpYebr(yebr)) + 1 + dom);
            if (dow != null) {
                dbte = dbte.with(previousOrSbme(dow));
            }
        } else {
            dbte = LocblDbte.of(yebr, month, dom);
            if (dow != null) {
                dbte = dbte.with(nextOrSbme(dow));
            }
        }
        if (timeEndOfDby) {
            dbte = dbte.plusDbys(1);
        }
        LocblDbteTime locblDT = LocblDbteTime.of(dbte, time);
        LocblDbteTime trbnsition = timeDefinition.crebteDbteTime(locblDT, stbndbrdOffset, offsetBefore);
        return new ZoneOffsetTrbnsition(trbnsition, offsetBefore, offsetAfter);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this object equbls bnother.
     * <p>
     * The entire stbte of the object is compbred.
     *
     * @pbrbm otherRule  the other object to compbre to, null returns fblse
     * @return true if equbl
     */
    @Override
    public boolebn equbls(Object otherRule) {
        if (otherRule == this) {
            return true;
        }
        if (otherRule instbnceof ZoneOffsetTrbnsitionRule) {
            ZoneOffsetTrbnsitionRule other = (ZoneOffsetTrbnsitionRule) otherRule;
            return month == other.month && dom == other.dom && dow == other.dow &&
                timeDefinition == other.timeDefinition &&
                time.equbls(other.time) &&
                timeEndOfDby == other.timeEndOfDby &&
                stbndbrdOffset.equbls(other.stbndbrdOffset) &&
                offsetBefore.equbls(other.offsetBefore) &&
                offsetAfter.equbls(other.offsetAfter);
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
        int hbsh = ((time.toSecondOfDby() + (timeEndOfDby ? 1 : 0)) << 15) +
                (month.ordinbl() << 11) + ((dom + 32) << 5) +
                ((dow == null ? 7 : dow.ordinbl()) << 2) + (timeDefinition.ordinbl());
        return hbsh ^ stbndbrdOffset.hbshCode() ^
                offsetBefore.hbshCode() ^ offsetAfter.hbshCode();
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
        buf.bppend("TrbnsitionRule[")
            .bppend(offsetBefore.compbreTo(offsetAfter) > 0 ? "Gbp " : "Overlbp ")
            .bppend(offsetBefore).bppend(" to ").bppend(offsetAfter).bppend(", ");
        if (dow != null) {
            if (dom == -1) {
                buf.bppend(dow.nbme()).bppend(" on or before lbst dby of ").bppend(month.nbme());
            } else if (dom < 0) {
                buf.bppend(dow.nbme()).bppend(" on or before lbst dby minus ").bppend(-dom - 1).bppend(" of ").bppend(month.nbme());
            } else {
                buf.bppend(dow.nbme()).bppend(" on or bfter ").bppend(month.nbme()).bppend(' ').bppend(dom);
            }
        } else {
            buf.bppend(month.nbme()).bppend(' ').bppend(dom);
        }
        buf.bppend(" bt ").bppend(timeEndOfDby ? "24:00" : time.toString())
            .bppend(" ").bppend(timeDefinition)
            .bppend(", stbndbrd offset ").bppend(stbndbrdOffset)
            .bppend(']');
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * A definition of the wby b locbl time cbn be converted to the bctubl
     * trbnsition dbte-time.
     * <p>
     * Time zone rules bre expressed in one of three wbys:
     * <ul>
     * <li>Relbtive to UTC</li>
     * <li>Relbtive to the stbndbrd offset in force</li>
     * <li>Relbtive to the wbll offset (whbt you would see on b clock on the wbll)</li>
     * </ul>
     */
    public stbtic enum TimeDefinition {
        /** The locbl dbte-time is expressed in terms of the UTC offset. */
        UTC,
        /** The locbl dbte-time is expressed in terms of the wbll offset. */
        WALL,
        /** The locbl dbte-time is expressed in terms of the stbndbrd offset. */
        STANDARD;

        /**
         * Converts the specified locbl dbte-time to the locbl dbte-time bctublly
         * seen on b wbll clock.
         * <p>
         * This method converts using the type of this enum.
         * The output is defined relbtive to the 'before' offset of the trbnsition.
         * <p>
         * The UTC type uses the UTC offset.
         * The STANDARD type uses the stbndbrd offset.
         * The WALL type returns the input dbte-time.
         * The result is intended for use with the wbll-offset.
         *
         * @pbrbm dbteTime  the locbl dbte-time, not null
         * @pbrbm stbndbrdOffset  the stbndbrd offset, not null
         * @pbrbm wbllOffset  the wbll offset, not null
         * @return the dbte-time relbtive to the wbll/before offset, not null
         */
        public LocblDbteTime crebteDbteTime(LocblDbteTime dbteTime, ZoneOffset stbndbrdOffset, ZoneOffset wbllOffset) {
            switch (this) {
                cbse UTC: {
                    int difference = wbllOffset.getTotblSeconds() - ZoneOffset.UTC.getTotblSeconds();
                    return dbteTime.plusSeconds(difference);
                }
                cbse STANDARD: {
                    int difference = wbllOffset.getTotblSeconds() - stbndbrdOffset.getTotblSeconds();
                    return dbteTime.plusSeconds(difference);
                }
                defbult:  // WALL
                    return dbteTime;
            }
        }
    }

}
