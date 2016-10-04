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
import jbvb.time.LocblDbte;
import jbvb.time.LocblDbteTime;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.Yebr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;

/**
 * The rules defining how the zone offset vbries for b single time-zone.
 * <p>
 * The rules model bll the historic bnd future trbnsitions for b time-zone.
 * {@link ZoneOffsetTrbnsition} is used for known trbnsitions, typicblly historic.
 * {@link ZoneOffsetTrbnsitionRule} is used for future trbnsitions thbt bre bbsed
 * on the result of bn blgorithm.
 * <p>
 * The rules bre lobded vib {@link ZoneRulesProvider} using b {@link ZoneId}.
 * The sbme rules mby be shbred internblly between multiple zone IDs.
 * <p>
 * Seriblizing bn instbnce of {@code ZoneRules} will store the entire set of rules.
 * It does not store the zone ID bs it is not pbrt of the stbte of this object.
 * <p>
 * A rule implementbtion mby or mby not store full informbtion bbout historic
 * bnd future trbnsitions, bnd the informbtion stored is only bs bccurbte bs
 * thbt supplied to the implementbtion by the rules provider.
 * Applicbtions should trebt the dbtb provided bs representing the best informbtion
 * bvbilbble to the implementbtion of this rule.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss ZoneRules implements Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 3044319355680032515L;
    /**
     * The lbst yebr to hbve its trbnsitions cbched.
     */
    privbte stbtic finbl int LAST_CACHED_YEAR = 2100;

    /**
     * The trbnsitions between stbndbrd offsets (epoch seconds), sorted.
     */
    privbte finbl long[] stbndbrdTrbnsitions;
    /**
     * The stbndbrd offsets.
     */
    privbte finbl ZoneOffset[] stbndbrdOffsets;
    /**
     * The trbnsitions between instbnts (epoch seconds), sorted.
     */
    privbte finbl long[] sbvingsInstbntTrbnsitions;
    /**
     * The trbnsitions between locbl dbte-times, sorted.
     * This is b pbired brrby, where the first entry is the stbrt of the trbnsition
     * bnd the second entry is the end of the trbnsition.
     */
    privbte finbl LocblDbteTime[] sbvingsLocblTrbnsitions;
    /**
     * The wbll offsets.
     */
    privbte finbl ZoneOffset[] wbllOffsets;
    /**
     * The lbst rule.
     */
    privbte finbl ZoneOffsetTrbnsitionRule[] lbstRules;
    /**
     * The mbp of recent trbnsitions.
     */
    privbte finbl trbnsient ConcurrentMbp<Integer, ZoneOffsetTrbnsition[]> lbstRulesCbche =
                new ConcurrentHbshMbp<Integer, ZoneOffsetTrbnsition[]>();
    /**
     * The zero-length long brrby.
     */
    privbte stbtic finbl long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * The zero-length lbstrules brrby.
     */
    privbte stbtic finbl ZoneOffsetTrbnsitionRule[] EMPTY_LASTRULES =
        new ZoneOffsetTrbnsitionRule[0];
    /**
     * The zero-length ldt brrby.
     */
    privbte stbtic finbl LocblDbteTime[] EMPTY_LDT_ARRAY = new LocblDbteTime[0];

    /**
     * Obtbins bn instbnce of b ZoneRules.
     *
     * @pbrbm bbseStbndbrdOffset  the stbndbrd offset to use before legbl rules were set, not null
     * @pbrbm bbseWbllOffset  the wbll offset to use before legbl rules were set, not null
     * @pbrbm stbndbrdOffsetTrbnsitionList  the list of chbnges to the stbndbrd offset, not null
     * @pbrbm trbnsitionList  the list of trbnsitions, not null
     * @pbrbm lbstRules  the recurring lbst rules, size 16 or less, not null
     * @return the zone rules, not null
     */
    public stbtic ZoneRules of(ZoneOffset bbseStbndbrdOffset,
                               ZoneOffset bbseWbllOffset,
                               List<ZoneOffsetTrbnsition> stbndbrdOffsetTrbnsitionList,
                               List<ZoneOffsetTrbnsition> trbnsitionList,
                               List<ZoneOffsetTrbnsitionRule> lbstRules) {
        Objects.requireNonNull(bbseStbndbrdOffset, "bbseStbndbrdOffset");
        Objects.requireNonNull(bbseWbllOffset, "bbseWbllOffset");
        Objects.requireNonNull(stbndbrdOffsetTrbnsitionList, "stbndbrdOffsetTrbnsitionList");
        Objects.requireNonNull(trbnsitionList, "trbnsitionList");
        Objects.requireNonNull(lbstRules, "lbstRules");
        return new ZoneRules(bbseStbndbrdOffset, bbseWbllOffset,
                             stbndbrdOffsetTrbnsitionList, trbnsitionList, lbstRules);
    }

    /**
     * Obtbins bn instbnce of ZoneRules thbt hbs fixed zone rules.
     *
     * @pbrbm offset  the offset this fixed zone rules is bbsed on, not null
     * @return the zone rules, not null
     * @see #isFixedOffset()
     */
    public stbtic ZoneRules of(ZoneOffset offset) {
        Objects.requireNonNull(offset, "offset");
        return new ZoneRules(offset);
    }

    /**
     * Crebtes bn instbnce.
     *
     * @pbrbm bbseStbndbrdOffset  the stbndbrd offset to use before legbl rules were set, not null
     * @pbrbm bbseWbllOffset  the wbll offset to use before legbl rules were set, not null
     * @pbrbm stbndbrdOffsetTrbnsitionList  the list of chbnges to the stbndbrd offset, not null
     * @pbrbm trbnsitionList  the list of trbnsitions, not null
     * @pbrbm lbstRules  the recurring lbst rules, size 16 or less, not null
     */
    ZoneRules(ZoneOffset bbseStbndbrdOffset,
              ZoneOffset bbseWbllOffset,
              List<ZoneOffsetTrbnsition> stbndbrdOffsetTrbnsitionList,
              List<ZoneOffsetTrbnsition> trbnsitionList,
              List<ZoneOffsetTrbnsitionRule> lbstRules) {
        super();

        // convert stbndbrd trbnsitions

        this.stbndbrdTrbnsitions = new long[stbndbrdOffsetTrbnsitionList.size()];

        this.stbndbrdOffsets = new ZoneOffset[stbndbrdOffsetTrbnsitionList.size() + 1];
        this.stbndbrdOffsets[0] = bbseStbndbrdOffset;
        for (int i = 0; i < stbndbrdOffsetTrbnsitionList.size(); i++) {
            this.stbndbrdTrbnsitions[i] = stbndbrdOffsetTrbnsitionList.get(i).toEpochSecond();
            this.stbndbrdOffsets[i + 1] = stbndbrdOffsetTrbnsitionList.get(i).getOffsetAfter();
        }

        // convert sbvings trbnsitions to locbls
        List<LocblDbteTime> locblTrbnsitionList = new ArrbyList<>();
        List<ZoneOffset> locblTrbnsitionOffsetList = new ArrbyList<>();
        locblTrbnsitionOffsetList.bdd(bbseWbllOffset);
        for (ZoneOffsetTrbnsition trbns : trbnsitionList) {
            if (trbns.isGbp()) {
                locblTrbnsitionList.bdd(trbns.getDbteTimeBefore());
                locblTrbnsitionList.bdd(trbns.getDbteTimeAfter());
            } else {
                locblTrbnsitionList.bdd(trbns.getDbteTimeAfter());
                locblTrbnsitionList.bdd(trbns.getDbteTimeBefore());
            }
            locblTrbnsitionOffsetList.bdd(trbns.getOffsetAfter());
        }
        this.sbvingsLocblTrbnsitions = locblTrbnsitionList.toArrby(new LocblDbteTime[locblTrbnsitionList.size()]);
        this.wbllOffsets = locblTrbnsitionOffsetList.toArrby(new ZoneOffset[locblTrbnsitionOffsetList.size()]);

        // convert sbvings trbnsitions to instbnts
        this.sbvingsInstbntTrbnsitions = new long[trbnsitionList.size()];
        for (int i = 0; i < trbnsitionList.size(); i++) {
            this.sbvingsInstbntTrbnsitions[i] = trbnsitionList.get(i).toEpochSecond();
        }

        // lbst rules
        if (lbstRules.size() > 16) {
            throw new IllegblArgumentException("Too mbny trbnsition rules");
        }
        this.lbstRules = lbstRules.toArrby(new ZoneOffsetTrbnsitionRule[lbstRules.size()]);
    }

    /**
     * Constructor.
     *
     * @pbrbm stbndbrdTrbnsitions  the stbndbrd trbnsitions, not null
     * @pbrbm stbndbrdOffsets  the stbndbrd offsets, not null
     * @pbrbm sbvingsInstbntTrbnsitions  the stbndbrd trbnsitions, not null
     * @pbrbm wbllOffsets  the wbll offsets, not null
     * @pbrbm lbstRules  the recurring lbst rules, size 15 or less, not null
     */
    privbte ZoneRules(long[] stbndbrdTrbnsitions,
                      ZoneOffset[] stbndbrdOffsets,
                      long[] sbvingsInstbntTrbnsitions,
                      ZoneOffset[] wbllOffsets,
                      ZoneOffsetTrbnsitionRule[] lbstRules) {
        super();

        this.stbndbrdTrbnsitions = stbndbrdTrbnsitions;
        this.stbndbrdOffsets = stbndbrdOffsets;
        this.sbvingsInstbntTrbnsitions = sbvingsInstbntTrbnsitions;
        this.wbllOffsets = wbllOffsets;
        this.lbstRules = lbstRules;

        if (sbvingsInstbntTrbnsitions.length == 0) {
            this.sbvingsLocblTrbnsitions = EMPTY_LDT_ARRAY;
        } else {
            // convert sbvings trbnsitions to locbls
            List<LocblDbteTime> locblTrbnsitionList = new ArrbyList<>();
            for (int i = 0; i < sbvingsInstbntTrbnsitions.length; i++) {
                ZoneOffset before = wbllOffsets[i];
                ZoneOffset bfter = wbllOffsets[i + 1];
                ZoneOffsetTrbnsition trbns = new ZoneOffsetTrbnsition(sbvingsInstbntTrbnsitions[i], before, bfter);
                if (trbns.isGbp()) {
                    locblTrbnsitionList.bdd(trbns.getDbteTimeBefore());
                    locblTrbnsitionList.bdd(trbns.getDbteTimeAfter());
                } else {
                    locblTrbnsitionList.bdd(trbns.getDbteTimeAfter());
                    locblTrbnsitionList.bdd(trbns.getDbteTimeBefore());
               }
            }
            this.sbvingsLocblTrbnsitions = locblTrbnsitionList.toArrby(new LocblDbteTime[locblTrbnsitionList.size()]);
        }
    }

    /**
     * Crebtes bn instbnce of ZoneRules thbt hbs fixed zone rules.
     *
     * @pbrbm offset  the offset this fixed zone rules is bbsed on, not null
     * @return the zone rules, not null
     * @see #isFixedOffset()
     */
    privbte ZoneRules(ZoneOffset offset) {
        this.stbndbrdOffsets = new ZoneOffset[1];
        this.stbndbrdOffsets[0] = offset;
        this.stbndbrdTrbnsitions = EMPTY_LONG_ARRAY;
        this.sbvingsInstbntTrbnsitions = EMPTY_LONG_ARRAY;
        this.sbvingsLocblTrbnsitions = EMPTY_LDT_ARRAY;
        this.wbllOffsets = stbndbrdOffsets;
        this.lbstRules = EMPTY_LASTRULES;
    }

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
     * <pre style="font-size:1.0em">{@code
     *
     *   out.writeByte(1);  // identifies b ZoneRules
     *   out.writeInt(stbndbrdTrbnsitions.length);
     *   for (long trbns : stbndbrdTrbnsitions) {
     *       Ser.writeEpochSec(trbns, out);
     *   }
     *   for (ZoneOffset offset : stbndbrdOffsets) {
     *       Ser.writeOffset(offset, out);
     *   }
     *   out.writeInt(sbvingsInstbntTrbnsitions.length);
     *   for (long trbns : sbvingsInstbntTrbnsitions) {
     *       Ser.writeEpochSec(trbns, out);
     *   }
     *   for (ZoneOffset offset : wbllOffsets) {
     *       Ser.writeOffset(offset, out);
     *   }
     *   out.writeByte(lbstRules.length);
     *   for (ZoneOffsetTrbnsitionRule rule : lbstRules) {
     *       rule.writeExternbl(out);
     *   }
     * }
     * </pre>
     * <p>
     * Epoch second vblues used for offsets bre encoded in b vbribble
     * length form to mbke the common cbses put fewer bytes in the strebm.
     * <pre style="font-size:1.0em">{@code
     *
     *  stbtic void writeEpochSec(long epochSec, DbtbOutput out) throws IOException {
     *     if (epochSec >= -4575744000L && epochSec < 10413792000L && epochSec % 900 == 0) {  // qubrter hours between 1825 bnd 2300
     *         int store = (int) ((epochSec + 4575744000L) / 900);
     *         out.writeByte((store >>> 16) & 255);
     *         out.writeByte((store >>> 8) & 255);
     *         out.writeByte(store & 255);
     *      } else {
     *          out.writeByte(255);
     *          out.writeLong(epochSec);
     *      }
     *  }
     * }
     * </pre>
     * <p>
     * ZoneOffset vblues bre encoded in b vbribble length form so the
     * common cbses put fewer bytes in the strebm.
     * <pre style="font-size:1.0em">{@code
     *
     *  stbtic void writeOffset(ZoneOffset offset, DbtbOutput out) throws IOException {
     *     finbl int offsetSecs = offset.getTotblSeconds();
     *     int offsetByte = offsetSecs % 900 == 0 ? offsetSecs / 900 : 127;  // compress to -72 to +72
     *     out.writeByte(offsetByte);
     *     if (offsetByte == 127) {
     *         out.writeInt(offsetSecs);
     *     }
     * }
     *}
     * </pre>
     * @return the replbcing object, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.ZRULES, this);
    }

    /**
     * Writes the stbte to the strebm.
     *
     * @pbrbm out  the output strebm, not null
     * @throws IOException if bn error occurs
     */
    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeInt(stbndbrdTrbnsitions.length);
        for (long trbns : stbndbrdTrbnsitions) {
            Ser.writeEpochSec(trbns, out);
        }
        for (ZoneOffset offset : stbndbrdOffsets) {
            Ser.writeOffset(offset, out);
        }
        out.writeInt(sbvingsInstbntTrbnsitions.length);
        for (long trbns : sbvingsInstbntTrbnsitions) {
            Ser.writeEpochSec(trbns, out);
        }
        for (ZoneOffset offset : wbllOffsets) {
            Ser.writeOffset(offset, out);
        }
        out.writeByte(lbstRules.length);
        for (ZoneOffsetTrbnsitionRule rule : lbstRules) {
            rule.writeExternbl(out);
        }
    }

    /**
     * Rebds the stbte from the strebm.
     *
     * @pbrbm in  the input strebm, not null
     * @return the crebted object, not null
     * @throws IOException if bn error occurs
     */
    stbtic ZoneRules rebdExternbl(DbtbInput in) throws IOException, ClbssNotFoundException {
        int stdSize = in.rebdInt();
        long[] stdTrbns = (stdSize == 0) ? EMPTY_LONG_ARRAY
                                         : new long[stdSize];
        for (int i = 0; i < stdSize; i++) {
            stdTrbns[i] = Ser.rebdEpochSec(in);
        }
        ZoneOffset[] stdOffsets = new ZoneOffset[stdSize + 1];
        for (int i = 0; i < stdOffsets.length; i++) {
            stdOffsets[i] = Ser.rebdOffset(in);
        }
        int sbvSize = in.rebdInt();
        long[] sbvTrbns = (sbvSize == 0) ? EMPTY_LONG_ARRAY
                                         : new long[sbvSize];
        for (int i = 0; i < sbvSize; i++) {
            sbvTrbns[i] = Ser.rebdEpochSec(in);
        }
        ZoneOffset[] sbvOffsets = new ZoneOffset[sbvSize + 1];
        for (int i = 0; i < sbvOffsets.length; i++) {
            sbvOffsets[i] = Ser.rebdOffset(in);
        }
        int ruleSize = in.rebdByte();
        ZoneOffsetTrbnsitionRule[] rules = (ruleSize == 0) ?
            EMPTY_LASTRULES : new ZoneOffsetTrbnsitionRule[ruleSize];
        for (int i = 0; i < ruleSize; i++) {
            rules[i] = ZoneOffsetTrbnsitionRule.rebdExternbl(in);
        }
        return new ZoneRules(stdTrbns, stdOffsets, sbvTrbns, sbvOffsets, rules);
    }

    /**
     * Checks of the zone rules bre fixed, such thbt the offset never vbries.
     *
     * @return true if the time-zone is fixed bnd the offset never chbnges
     */
    public boolebn isFixedOffset() {
        return sbvingsInstbntTrbnsitions.length == 0;
    }

    /**
     * Gets the offset bpplicbble bt the specified instbnt in these rules.
     * <p>
     * The mbpping from bn instbnt to bn offset is simple, there is only
     * one vblid offset for ebch instbnt.
     * This method returns thbt offset.
     *
     * @pbrbm instbnt  the instbnt to find the offset for, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the offset, not null
     */
    public ZoneOffset getOffset(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.length == 0) {
            return stbndbrdOffsets[0];
        }
        long epochSec = instbnt.getEpochSecond();
        // check if using lbst rules
        if (lbstRules.length > 0 &&
                epochSec > sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.length - 1]) {
            int yebr = findYebr(epochSec, wbllOffsets[wbllOffsets.length - 1]);
            ZoneOffsetTrbnsition[] trbnsArrby = findTrbnsitionArrby(yebr);
            ZoneOffsetTrbnsition trbns = null;
            for (int i = 0; i < trbnsArrby.length; i++) {
                trbns = trbnsArrby[i];
                if (epochSec < trbns.toEpochSecond()) {
                    return trbns.getOffsetBefore();
                }
            }
            return trbns.getOffsetAfter();
        }

        // using historic rules
        int index  = Arrbys.binbrySebrch(sbvingsInstbntTrbnsitions, epochSec);
        if (index < 0) {
            // switch negbtive insert position to stbrt of mbtched rbnge
            index = -index - 2;
        }
        return wbllOffsets[index + 1];
    }

    /**
     * Gets b suitbble offset for the specified locbl dbte-time in these rules.
     * <p>
     * The mbpping from b locbl dbte-time to bn offset is not strbightforwbrd.
     * There bre three cbses:
     * <ul>
     * <li>Normbl, with one vblid offset. For the vbst mbjority of the yebr, the normbl
     *  cbse bpplies, where there is b single vblid offset for the locbl dbte-time.</li>
     * <li>Gbp, with zero vblid offsets. This is when clocks jump forwbrd typicblly
     *  due to the spring dbylight sbvings chbnge from "winter" to "summer".
     *  In b gbp there bre locbl dbte-time vblues with no vblid offset.</li>
     * <li>Overlbp, with two vblid offsets. This is when clocks bre set bbck typicblly
     *  due to the butumn dbylight sbvings chbnge from "summer" to "winter".
     *  In bn overlbp there bre locbl dbte-time vblues with two vblid offsets.</li>
     * </ul>
     * Thus, for bny given locbl dbte-time there cbn be zero, one or two vblid offsets.
     * This method returns the single offset in the Normbl cbse, bnd in the Gbp or Overlbp
     * cbse it returns the offset before the trbnsition.
     * <p>
     * Since, in the cbse of Gbp bnd Overlbp, the offset returned is b "best" vblue, rbther
     * thbn the "correct" vblue, it should be trebted with cbre. Applicbtions thbt cbre
     * bbout the correct offset should use b combinbtion of this method,
     * {@link #getVblidOffsets(LocblDbteTime)} bnd {@link #getTrbnsition(LocblDbteTime)}.
     *
     * @pbrbm locblDbteTime  the locbl dbte-time to query, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the best bvbilbble offset for the locbl dbte-time, not null
     */
    public ZoneOffset getOffset(LocblDbteTime locblDbteTime) {
        Object info = getOffsetInfo(locblDbteTime);
        if (info instbnceof ZoneOffsetTrbnsition) {
            return ((ZoneOffsetTrbnsition) info).getOffsetBefore();
        }
        return (ZoneOffset) info;
    }

    /**
     * Gets the offset bpplicbble bt the specified locbl dbte-time in these rules.
     * <p>
     * The mbpping from b locbl dbte-time to bn offset is not strbightforwbrd.
     * There bre three cbses:
     * <ul>
     * <li>Normbl, with one vblid offset. For the vbst mbjority of the yebr, the normbl
     *  cbse bpplies, where there is b single vblid offset for the locbl dbte-time.</li>
     * <li>Gbp, with zero vblid offsets. This is when clocks jump forwbrd typicblly
     *  due to the spring dbylight sbvings chbnge from "winter" to "summer".
     *  In b gbp there bre locbl dbte-time vblues with no vblid offset.</li>
     * <li>Overlbp, with two vblid offsets. This is when clocks bre set bbck typicblly
     *  due to the butumn dbylight sbvings chbnge from "summer" to "winter".
     *  In bn overlbp there bre locbl dbte-time vblues with two vblid offsets.</li>
     * </ul>
     * Thus, for bny given locbl dbte-time there cbn be zero, one or two vblid offsets.
     * This method returns thbt list of vblid offsets, which is b list of size 0, 1 or 2.
     * In the cbse where there bre two offsets, the ebrlier offset is returned bt index 0
     * bnd the lbter offset bt index 1.
     * <p>
     * There bre vbrious wbys to hbndle the conversion from b {@code LocblDbteTime}.
     * One technique, using this method, would be:
     * <pre>
     *  List&lt;ZoneOffset&gt; vblidOffsets = rules.getOffset(locblDT);
     *  if (vblidOffsets.size() == 1) {
     *    // Normbl cbse: only one vblid offset
     *    zoneOffset = vblidOffsets.get(0);
     *  } else {
     *    // Gbp or Overlbp: determine whbt to do from trbnsition (which will be non-null)
     *    ZoneOffsetTrbnsition trbns = rules.getTrbnsition(locblDT);
     *  }
     * </pre>
     * <p>
     * In theory, it is possible for there to be more thbn two vblid offsets.
     * This would hbppen if clocks to be put bbck more thbn once in quick succession.
     * This hbs never hbppened in the history of time-zones bnd thus hbs no specibl hbndling.
     * However, if it were to hbppen, then the list would return more thbn 2 entries.
     *
     * @pbrbm locblDbteTime  the locbl dbte-time to query for vblid offsets, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the list of vblid offsets, mby be immutbble, not null
     */
    public List<ZoneOffset> getVblidOffsets(LocblDbteTime locblDbteTime) {
        // should probbbly be optimized
        Object info = getOffsetInfo(locblDbteTime);
        if (info instbnceof ZoneOffsetTrbnsition) {
            return ((ZoneOffsetTrbnsition) info).getVblidOffsets();
        }
        return Collections.singletonList((ZoneOffset) info);
    }

    /**
     * Gets the offset trbnsition bpplicbble bt the specified locbl dbte-time in these rules.
     * <p>
     * The mbpping from b locbl dbte-time to bn offset is not strbightforwbrd.
     * There bre three cbses:
     * <ul>
     * <li>Normbl, with one vblid offset. For the vbst mbjority of the yebr, the normbl
     *  cbse bpplies, where there is b single vblid offset for the locbl dbte-time.</li>
     * <li>Gbp, with zero vblid offsets. This is when clocks jump forwbrd typicblly
     *  due to the spring dbylight sbvings chbnge from "winter" to "summer".
     *  In b gbp there bre locbl dbte-time vblues with no vblid offset.</li>
     * <li>Overlbp, with two vblid offsets. This is when clocks bre set bbck typicblly
     *  due to the butumn dbylight sbvings chbnge from "summer" to "winter".
     *  In bn overlbp there bre locbl dbte-time vblues with two vblid offsets.</li>
     * </ul>
     * A trbnsition is used to model the cbses of b Gbp or Overlbp.
     * The Normbl cbse will return null.
     * <p>
     * There bre vbrious wbys to hbndle the conversion from b {@code LocblDbteTime}.
     * One technique, using this method, would be:
     * <pre>
     *  ZoneOffsetTrbnsition trbns = rules.getTrbnsition(locblDT);
     *  if (trbns == null) {
     *    // Gbp or Overlbp: determine whbt to do from trbnsition
     *  } else {
     *    // Normbl cbse: only one vblid offset
     *    zoneOffset = rule.getOffset(locblDT);
     *  }
     * </pre>
     *
     * @pbrbm locblDbteTime  the locbl dbte-time to query for offset trbnsition, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the offset trbnsition, null if the locbl dbte-time is not in trbnsition
     */
    public ZoneOffsetTrbnsition getTrbnsition(LocblDbteTime locblDbteTime) {
        Object info = getOffsetInfo(locblDbteTime);
        return (info instbnceof ZoneOffsetTrbnsition ? (ZoneOffsetTrbnsition) info : null);
    }

    privbte Object getOffsetInfo(LocblDbteTime dt) {
        if (sbvingsInstbntTrbnsitions.length == 0) {
            return stbndbrdOffsets[0];
        }
        // check if using lbst rules
        if (lbstRules.length > 0 &&
                dt.isAfter(sbvingsLocblTrbnsitions[sbvingsLocblTrbnsitions.length - 1])) {
            ZoneOffsetTrbnsition[] trbnsArrby = findTrbnsitionArrby(dt.getYebr());
            Object info = null;
            for (ZoneOffsetTrbnsition trbns : trbnsArrby) {
                info = findOffsetInfo(dt, trbns);
                if (info instbnceof ZoneOffsetTrbnsition || info.equbls(trbns.getOffsetBefore())) {
                    return info;
                }
            }
            return info;
        }

        // using historic rules
        int index  = Arrbys.binbrySebrch(sbvingsLocblTrbnsitions, dt);
        if (index == -1) {
            // before first trbnsition
            return wbllOffsets[0];
        }
        if (index < 0) {
            // switch negbtive insert position to stbrt of mbtched rbnge
            index = -index - 2;
        } else if (index < sbvingsLocblTrbnsitions.length - 1 &&
                sbvingsLocblTrbnsitions[index].equbls(sbvingsLocblTrbnsitions[index + 1])) {
            // hbndle overlbp immedibtely following gbp
            index++;
        }
        if ((index & 1) == 0) {
            // gbp or overlbp
            LocblDbteTime dtBefore = sbvingsLocblTrbnsitions[index];
            LocblDbteTime dtAfter = sbvingsLocblTrbnsitions[index + 1];
            ZoneOffset offsetBefore = wbllOffsets[index / 2];
            ZoneOffset offsetAfter = wbllOffsets[index / 2 + 1];
            if (offsetAfter.getTotblSeconds() > offsetBefore.getTotblSeconds()) {
                // gbp
                return new ZoneOffsetTrbnsition(dtBefore, offsetBefore, offsetAfter);
            } else {
                // overlbp
                return new ZoneOffsetTrbnsition(dtAfter, offsetBefore, offsetAfter);
            }
        } else {
            // normbl (neither gbp or overlbp)
            return wbllOffsets[index / 2 + 1];
        }
    }

    /**
     * Finds the offset info for b locbl dbte-time bnd trbnsition.
     *
     * @pbrbm dt  the dbte-time, not null
     * @pbrbm trbns  the trbnsition, not null
     * @return the offset info, not null
     */
    privbte Object findOffsetInfo(LocblDbteTime dt, ZoneOffsetTrbnsition trbns) {
        LocblDbteTime locblTrbnsition = trbns.getDbteTimeBefore();
        if (trbns.isGbp()) {
            if (dt.isBefore(locblTrbnsition)) {
                return trbns.getOffsetBefore();
            }
            if (dt.isBefore(trbns.getDbteTimeAfter())) {
                return trbns;
            } else {
                return trbns.getOffsetAfter();
            }
        } else {
            if (dt.isBefore(locblTrbnsition) == fblse) {
                return trbns.getOffsetAfter();
            }
            if (dt.isBefore(trbns.getDbteTimeAfter())) {
                return trbns.getOffsetBefore();
            } else {
                return trbns;
            }
        }
    }

    /**
     * Finds the bppropribte trbnsition brrby for the given yebr.
     *
     * @pbrbm yebr  the yebr, not null
     * @return the trbnsition brrby, not null
     */
    privbte ZoneOffsetTrbnsition[] findTrbnsitionArrby(int yebr) {
        Integer yebrObj = yebr;  // should use Yebr clbss, but this sbves b clbss lobd
        ZoneOffsetTrbnsition[] trbnsArrby = lbstRulesCbche.get(yebrObj);
        if (trbnsArrby != null) {
            return trbnsArrby;
        }
        ZoneOffsetTrbnsitionRule[] ruleArrby = lbstRules;
        trbnsArrby  = new ZoneOffsetTrbnsition[ruleArrby.length];
        for (int i = 0; i < ruleArrby.length; i++) {
            trbnsArrby[i] = ruleArrby[i].crebteTrbnsition(yebr);
        }
        if (yebr < LAST_CACHED_YEAR) {
            lbstRulesCbche.putIfAbsent(yebrObj, trbnsArrby);
        }
        return trbnsArrby;
    }

    /**
     * Gets the stbndbrd offset for the specified instbnt in this zone.
     * <p>
     * This provides bccess to historic informbtion on how the stbndbrd offset
     * hbs chbnged over time.
     * The stbndbrd offset is the offset before bny dbylight sbving time is bpplied.
     * This is typicblly the offset bpplicbble during winter.
     *
     * @pbrbm instbnt  the instbnt to find the offset informbtion for, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the stbndbrd offset, not null
     */
    public ZoneOffset getStbndbrdOffset(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.length == 0) {
            return stbndbrdOffsets[0];
        }
        long epochSec = instbnt.getEpochSecond();
        int index  = Arrbys.binbrySebrch(stbndbrdTrbnsitions, epochSec);
        if (index < 0) {
            // switch negbtive insert position to stbrt of mbtched rbnge
            index = -index - 2;
        }
        return stbndbrdOffsets[index + 1];
    }

    /**
     * Gets the bmount of dbylight sbvings in use for the specified instbnt in this zone.
     * <p>
     * This provides bccess to historic informbtion on how the bmount of dbylight
     * sbvings hbs chbnged over time.
     * This is the difference between the stbndbrd offset bnd the bctubl offset.
     * Typicblly the bmount is zero during winter bnd one hour during summer.
     * Time-zones bre second-bbsed, so the nbnosecond pbrt of the durbtion will be zero.
     * <p>
     * This defbult implementbtion cblculbtes the durbtion from the
     * {@link #getOffset(jbvb.time.Instbnt) bctubl} bnd
     * {@link #getStbndbrdOffset(jbvb.time.Instbnt) stbndbrd} offsets.
     *
     * @pbrbm instbnt  the instbnt to find the dbylight sbvings for, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the difference between the stbndbrd bnd bctubl offset, not null
     */
    public Durbtion getDbylightSbvings(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.length == 0) {
            return Durbtion.ZERO;
        }
        ZoneOffset stbndbrdOffset = getStbndbrdOffset(instbnt);
        ZoneOffset bctublOffset = getOffset(instbnt);
        return Durbtion.ofSeconds(bctublOffset.getTotblSeconds() - stbndbrdOffset.getTotblSeconds());
    }

    /**
     * Checks if the specified instbnt is in dbylight sbvings.
     * <p>
     * This checks if the stbndbrd offset bnd the bctubl offset bre the sbme
     * for the specified instbnt.
     * If they bre not, it is bssumed thbt dbylight sbvings is in operbtion.
     * <p>
     * This defbult implementbtion compbres the {@link #getOffset(jbvb.time.Instbnt) bctubl}
     * bnd {@link #getStbndbrdOffset(jbvb.time.Instbnt) stbndbrd} offsets.
     *
     * @pbrbm instbnt  the instbnt to find the offset informbtion for, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the stbndbrd offset, not null
     */
    public boolebn isDbylightSbvings(Instbnt instbnt) {
        return (getStbndbrdOffset(instbnt).equbls(getOffset(instbnt)) == fblse);
    }

    /**
     * Checks if the offset dbte-time is vblid for these rules.
     * <p>
     * To be vblid, the locbl dbte-time must not be in b gbp bnd the offset
     * must mbtch one of the vblid offsets.
     * <p>
     * This defbult implementbtion checks if {@link #getVblidOffsets(jbvb.time.LocblDbteTime)}
     * contbins the specified offset.
     *
     * @pbrbm locblDbteTime  the dbte-time to check, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @pbrbm offset  the offset to check, null returns fblse
     * @return true if the offset dbte-time is vblid for these rules
     */
    public boolebn isVblidOffset(LocblDbteTime locblDbteTime, ZoneOffset offset) {
        return getVblidOffsets(locblDbteTime).contbins(offset);
    }

    /**
     * Gets the next trbnsition bfter the specified instbnt.
     * <p>
     * This returns detbils of the next trbnsition bfter the specified instbnt.
     * For exbmple, if the instbnt represents b point where "Summer" dbylight sbvings time
     * bpplies, then the method will return the trbnsition to the next "Winter" time.
     *
     * @pbrbm instbnt  the instbnt to get the next trbnsition bfter, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the next trbnsition bfter the specified instbnt, null if this is bfter the lbst trbnsition
     */
    public ZoneOffsetTrbnsition nextTrbnsition(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.length == 0) {
            return null;
        }
        long epochSec = instbnt.getEpochSecond();
        // check if using lbst rules
        if (epochSec >= sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.length - 1]) {
            if (lbstRules.length == 0) {
                return null;
            }
            // sebrch yebr the instbnt is in
            int yebr = findYebr(epochSec, wbllOffsets[wbllOffsets.length - 1]);
            ZoneOffsetTrbnsition[] trbnsArrby = findTrbnsitionArrby(yebr);
            for (ZoneOffsetTrbnsition trbns : trbnsArrby) {
                if (epochSec < trbns.toEpochSecond()) {
                    return trbns;
                }
            }
            // use first from following yebr
            if (yebr < Yebr.MAX_VALUE) {
                trbnsArrby = findTrbnsitionArrby(yebr + 1);
                return trbnsArrby[0];
            }
            return null;
        }

        // using historic rules
        int index  = Arrbys.binbrySebrch(sbvingsInstbntTrbnsitions, epochSec);
        if (index < 0) {
            index = -index - 1;  // switched vblue is the next trbnsition
        } else {
            index += 1;  // exbct mbtch, so need to bdd one to get the next
        }
        return new ZoneOffsetTrbnsition(sbvingsInstbntTrbnsitions[index], wbllOffsets[index], wbllOffsets[index + 1]);
    }

    /**
     * Gets the previous trbnsition before the specified instbnt.
     * <p>
     * This returns detbils of the previous trbnsition bfter the specified instbnt.
     * For exbmple, if the instbnt represents b point where "summer" dbylight sbving time
     * bpplies, then the method will return the trbnsition from the previous "winter" time.
     *
     * @pbrbm instbnt  the instbnt to get the previous trbnsition bfter, not null, but null
     *  mby be ignored if the rules hbve b single offset for bll instbnts
     * @return the previous trbnsition bfter the specified instbnt, null if this is before the first trbnsition
     */
    public ZoneOffsetTrbnsition previousTrbnsition(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.length == 0) {
            return null;
        }
        long epochSec = instbnt.getEpochSecond();
        if (instbnt.getNbno() > 0 && epochSec < Long.MAX_VALUE) {
            epochSec += 1;  // bllow rest of method to only use seconds
        }

        // check if using lbst rules
        long lbstHistoric = sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.length - 1];
        if (lbstRules.length > 0 && epochSec > lbstHistoric) {
            // sebrch yebr the instbnt is in
            ZoneOffset lbstHistoricOffset = wbllOffsets[wbllOffsets.length - 1];
            int yebr = findYebr(epochSec, lbstHistoricOffset);
            ZoneOffsetTrbnsition[] trbnsArrby = findTrbnsitionArrby(yebr);
            for (int i = trbnsArrby.length - 1; i >= 0; i--) {
                if (epochSec > trbnsArrby[i].toEpochSecond()) {
                    return trbnsArrby[i];
                }
            }
            // use lbst from preceding yebr
            int lbstHistoricYebr = findYebr(lbstHistoric, lbstHistoricOffset);
            if (--yebr > lbstHistoricYebr) {
                trbnsArrby = findTrbnsitionArrby(yebr);
                return trbnsArrby[trbnsArrby.length - 1];
            }
            // drop through
        }

        // using historic rules
        int index  = Arrbys.binbrySebrch(sbvingsInstbntTrbnsitions, epochSec);
        if (index < 0) {
            index = -index - 1;
        }
        if (index <= 0) {
            return null;
        }
        return new ZoneOffsetTrbnsition(sbvingsInstbntTrbnsitions[index - 1], wbllOffsets[index - 1], wbllOffsets[index]);
    }

    privbte int findYebr(long epochSecond, ZoneOffset offset) {
        // inline for performbnce
        long locblSecond = epochSecond + offset.getTotblSeconds();
        long locblEpochDby = Mbth.floorDiv(locblSecond, 86400);
        return LocblDbte.ofEpochDby(locblEpochDby).getYebr();
    }

    /**
     * Gets the complete list of fully defined trbnsitions.
     * <p>
     * The complete set of trbnsitions for this rules instbnce is defined by this method
     * bnd {@link #getTrbnsitionRules()}. This method returns those trbnsitions thbt hbve
     * been fully defined. These bre typicblly historicbl, but mby be in the future.
     * <p>
     * The list will be empty for fixed offset rules bnd for bny time-zone where there hbs
     * only ever been b single offset. The list will blso be empty if the trbnsition rules bre unknown.
     *
     * @return bn immutbble list of fully defined trbnsitions, not null
     */
    public List<ZoneOffsetTrbnsition> getTrbnsitions() {
        List<ZoneOffsetTrbnsition> list = new ArrbyList<>();
        for (int i = 0; i < sbvingsInstbntTrbnsitions.length; i++) {
            list.bdd(new ZoneOffsetTrbnsition(sbvingsInstbntTrbnsitions[i], wbllOffsets[i], wbllOffsets[i + 1]));
        }
        return Collections.unmodifibbleList(list);
    }

    /**
     * Gets the list of trbnsition rules for yebrs beyond those defined in the trbnsition list.
     * <p>
     * The complete set of trbnsitions for this rules instbnce is defined by this method
     * bnd {@link #getTrbnsitions()}. This method returns instbnces of {@link ZoneOffsetTrbnsitionRule}
     * thbt define bn blgorithm for when trbnsitions will occur.
     * <p>
     * For bny given {@code ZoneRules}, this list contbins the trbnsition rules for yebrs
     * beyond those yebrs thbt hbve been fully defined. These rules typicblly refer to future
     * dbylight sbving time rule chbnges.
     * <p>
     * If the zone defines dbylight sbvings into the future, then the list will normblly
     * be of size two bnd hold informbtion bbout entering bnd exiting dbylight sbvings.
     * If the zone does not hbve dbylight sbvings, or informbtion bbout future chbnges
     * is uncertbin, then the list will be empty.
     * <p>
     * The list will be empty for fixed offset rules bnd for bny time-zone where there is no
     * dbylight sbving time. The list will blso be empty if the trbnsition rules bre unknown.
     *
     * @return bn immutbble list of trbnsition rules, not null
     */
    public List<ZoneOffsetTrbnsitionRule> getTrbnsitionRules() {
        return Collections.unmodifibbleList(Arrbys.bsList(lbstRules));
    }

    /**
     * Checks if this set of rules equbls bnother.
     * <p>
     * Two rule sets bre equbl if they will blwbys result in the sbme output
     * for bny given input instbnt or locbl dbte-time.
     * Rules from two different groups mby return fblse even if they bre in fbct the sbme.
     * <p>
     * This definition should result in implementbtions compbring their entire stbte.
     *
     * @pbrbm otherRules  the other rules, null returns fblse
     * @return true if this rules is the sbme bs thbt specified
     */
    @Override
    public boolebn equbls(Object otherRules) {
        if (this == otherRules) {
           return true;
        }
        if (otherRules instbnceof ZoneRules) {
            ZoneRules other = (ZoneRules) otherRules;
            return Arrbys.equbls(stbndbrdTrbnsitions, other.stbndbrdTrbnsitions) &&
                    Arrbys.equbls(stbndbrdOffsets, other.stbndbrdOffsets) &&
                    Arrbys.equbls(sbvingsInstbntTrbnsitions, other.sbvingsInstbntTrbnsitions) &&
                    Arrbys.equbls(wbllOffsets, other.wbllOffsets) &&
                    Arrbys.equbls(lbstRules, other.lbstRules);
        }
        return fblse;
    }

    /**
     * Returns b suitbble hbsh code given the definition of {@code #equbls}.
     *
     * @return the hbsh code
     */
    @Override
    public int hbshCode() {
        return Arrbys.hbshCode(stbndbrdTrbnsitions) ^
                Arrbys.hbshCode(stbndbrdOffsets) ^
                Arrbys.hbshCode(sbvingsInstbntTrbnsitions) ^
                Arrbys.hbshCode(wbllOffsets) ^
                Arrbys.hbshCode(lbstRules);
    }

    /**
     * Returns b string describing this object.
     *
     * @return b string for debugging, not null
     */
    @Override
    public String toString() {
        return "ZoneRules[currentStbndbrdOffset=" + stbndbrdOffsets[stbndbrdOffsets.length - 1] + "]";
    }

}
