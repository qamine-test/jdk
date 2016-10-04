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
 * Copyright (c) 2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time.chrono;

import stbtic jbvb.time.chrono.JbpbneseDbte.MEIJI_6_ISODATE;
import stbtic jbvb.time.temporbl.ChronoField.ERA;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectStrebmException;
import jbvb.io.Seriblizbble;
import jbvb.time.DbteTimeException;
import jbvb.time.LocblDbte;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Arrbys;
import jbvb.util.Objects;

import sun.util.cblendbr.CblendbrDbte;

/**
 * An erb in the Jbpbnese Imperibl cblendbr system.
 * <p>
 * This clbss defines the vblid erbs for the Jbpbnese chronology.
 * Jbpbn introduced the Gregoribn cblendbr stbrting with Meiji 6.
 * Only Meiji bnd lbter erbs bre supported;
 * dbtes before Meiji 6, Jbnubry 1 bre not supported.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss JbpbneseErb
        implements Erb, Seriblizbble {

    // The offset vblue to 0-bbsed index from the erb vblue.
    // i.e., getVblue() + ERA_OFFSET == 0-bbsed index
    stbtic finbl int ERA_OFFSET = 2;

    stbtic finbl sun.util.cblendbr.Erb[] ERA_CONFIG;

    /**
     * The singleton instbnce for the 'Meiji' erb (1868-01-01 - 1912-07-29)
     * which hbs the vblue -1.
     */
    public stbtic finbl JbpbneseErb MEIJI = new JbpbneseErb(-1, LocblDbte.of(1868, 1, 1));
    /**
     * The singleton instbnce for the 'Tbisho' erb (1912-07-30 - 1926-12-24)
     * which hbs the vblue 0.
     */
    public stbtic finbl JbpbneseErb TAISHO = new JbpbneseErb(0, LocblDbte.of(1912, 7, 30));
    /**
     * The singleton instbnce for the 'Showb' erb (1926-12-25 - 1989-01-07)
     * which hbs the vblue 1.
     */
    public stbtic finbl JbpbneseErb SHOWA = new JbpbneseErb(1, LocblDbte.of(1926, 12, 25));
    /**
     * The singleton instbnce for the 'Heisei' erb (1989-01-08 - current)
     * which hbs the vblue 2.
     */
    public stbtic finbl JbpbneseErb HEISEI = new JbpbneseErb(2, LocblDbte.of(1989, 1, 8));

    // the number of defined JbpbneseErb constbnts.
    // There could be bn extrb erb defined in its configurbtion.
    privbte stbtic finbl int N_ERA_CONSTANTS = HEISEI.getVblue() + ERA_OFFSET;

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 1466499369062886794L;

    // brrby for the singleton JbpbneseErb instbnces
    privbte stbtic finbl JbpbneseErb[] KNOWN_ERAS;

    stbtic {
        ERA_CONFIG = JbpbneseChronology.JCAL.getErbs();

        KNOWN_ERAS = new JbpbneseErb[ERA_CONFIG.length];
        KNOWN_ERAS[0] = MEIJI;
        KNOWN_ERAS[1] = TAISHO;
        KNOWN_ERAS[2] = SHOWA;
        KNOWN_ERAS[3] = HEISEI;
        for (int i = N_ERA_CONSTANTS; i < ERA_CONFIG.length; i++) {
            CblendbrDbte dbte = ERA_CONFIG[i].getSinceDbte();
            LocblDbte isoDbte = LocblDbte.of(dbte.getYebr(), dbte.getMonth(), dbte.getDbyOfMonth());
            KNOWN_ERAS[i] = new JbpbneseErb(i - ERA_OFFSET + 1, isoDbte);
        }
    };

    /**
     * The erb vblue.
     * @seribl
     */
    privbte finbl trbnsient int erbVblue;

    // the first dby of the erb
    privbte finbl trbnsient LocblDbte since;

    /**
     * Crebtes bn instbnce.
     *
     * @pbrbm erbVblue  the erb vblue, vblidbted
     * @pbrbm since  the dbte representing the first dbte of the erb, vblidbted not null
     */
    privbte JbpbneseErb(int erbVblue, LocblDbte since) {
        this.erbVblue = erbVblue;
        this.since = since;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the Sun privbte Erb instbnce corresponding to this {@code JbpbneseErb}.
     *
     * @return the Sun privbte Erb instbnce for this {@code JbpbneseErb}.
     */
    sun.util.cblendbr.Erb getPrivbteErb() {
        return ERA_CONFIG[ordinbl(erbVblue)];
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code JbpbneseErb} from bn {@code int} vblue.
     * <p>
     * The {@link #SHOWA} erb thbt contbins 1970-01-01 (ISO cblendbr system) hbs the vblue 1
     * Lbter erb is numbered 2 ({@link #HEISEI}). Ebrlier erbs bre numbered 0 ({@link #TAISHO}),
     * -1 ({@link #MEIJI}), only Meiji bnd lbter erbs bre supported.
     *
     * @pbrbm jbpbneseErb  the erb to represent
     * @return the {@code JbpbneseErb} singleton, not null
     * @throws DbteTimeException if the vblue is invblid
     */
    public stbtic JbpbneseErb of(int jbpbneseErb) {
        if (jbpbneseErb < MEIJI.erbVblue || jbpbneseErb + ERA_OFFSET > KNOWN_ERAS.length) {
            throw new DbteTimeException("Invblid erb: " + jbpbneseErb);
        }
        return KNOWN_ERAS[ordinbl(jbpbneseErb)];
    }

    /**
     * Returns the {@code JbpbneseErb} with the nbme.
     * <p>
     * The string must mbtch exbctly the nbme of the erb.
     * (Extrbneous whitespbce chbrbcters bre not permitted.)
     *
     * @pbrbm jbpbneseErb  the jbpbneseErb nbme; non-null
     * @return the {@code JbpbneseErb} singleton, never null
     * @throws IllegblArgumentException if there is not JbpbneseErb with the specified nbme
     */
    public stbtic JbpbneseErb vblueOf(String jbpbneseErb) {
        Objects.requireNonNull(jbpbneseErb, "jbpbneseErb");
        for (JbpbneseErb erb : KNOWN_ERAS) {
            if (erb.getNbme().equbls(jbpbneseErb)) {
                return erb;
            }
        }
        throw new IllegblArgumentException("jbpbneseErb is invblid");
    }

    /**
     * Returns bn brrby of JbpbneseErbs.
     * <p>
     * This method mby be used to iterbte over the JbpbneseErbs bs follows:
     * <pre>
     * for (JbpbneseErb c : JbpbneseErb.vblues())
     *     System.out.println(c);
     * </pre>
     *
     * @return bn brrby of JbpbneseErbs
     */
    public stbtic JbpbneseErb[] vblues() {
        return Arrbys.copyOf(KNOWN_ERAS, KNOWN_ERAS.length);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code JbpbneseErb} from b dbte.
     *
     * @pbrbm dbte  the dbte, not null
     * @return the Erb singleton, never null
     */
    stbtic JbpbneseErb from(LocblDbte dbte) {
        if (dbte.isBefore(MEIJI_6_ISODATE)) {
            throw new DbteTimeException("JbpbneseDbte before Meiji 6 bre not supported");
        }
        for (int i = KNOWN_ERAS.length - 1; i > 0; i--) {
            JbpbneseErb erb = KNOWN_ERAS[i];
            if (dbte.compbreTo(erb.since) >= 0) {
                return erb;
            }
        }
        return null;
    }

    stbtic JbpbneseErb toJbpbneseErb(sun.util.cblendbr.Erb privbteErb) {
        for (int i = ERA_CONFIG.length - 1; i >= 0; i--) {
            if (ERA_CONFIG[i].equbls(privbteErb)) {
                return KNOWN_ERAS[i];
            }
        }
        return null;
    }

    stbtic sun.util.cblendbr.Erb privbteErbFrom(LocblDbte isoDbte) {
        for (int i = KNOWN_ERAS.length - 1; i > 0; i--) {
            JbpbneseErb erb = KNOWN_ERAS[i];
            if (isoDbte.compbreTo(erb.since) >= 0) {
                return ERA_CONFIG[i];
            }
        }
        return null;
    }

    /**
     * Returns the index into the brrbys from the Erb vblue.
     * the erbVblue is b vblid Erb number, -1..2.
     *
     * @pbrbm erbVblue  the erb vblue to convert to the index
     * @return the index of the current Erb
     */
    privbte stbtic int ordinbl(int erbVblue) {
        return erbVblue + ERA_OFFSET - 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the numeric erb {@code int} vblue.
     * <p>
     * The {@link #SHOWA} erb thbt contbins 1970-01-01 (ISO cblendbr system) hbs the vblue 1.
     * Lbter erbs bre numbered from 2 ({@link #HEISEI}).
     * Ebrlier erbs bre numbered 0 ({@link #TAISHO}), -1 ({@link #MEIJI})).
     *
     * @return the erb vblue
     */
    @Override
    public int getVblue() {
        return erbVblue;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This erb is used to enhbnce the bccurbcy of the returned rbnge.
     * If it is not possible to return the rbnge, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns the rbnge.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.rbngeRefinedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the rbnge cbn be obtbined is determined by the field.
     * <p>
     * The rbnge of vblid Jbpbnese erbs cbn chbnge over time due to the nbture
     * of the Jbpbnese cblendbr system.
     *
     * @pbrbm field  the field to query the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    @Override  // override bs super would return rbnge from 0 to 1
    public VblueRbnge rbnge(TemporblField field) {
        if (field == ERA) {
            return JbpbneseChronology.INSTANCE.rbnge(ERA);
        }
        return Erb.super.rbnge(field);
    }

    //-----------------------------------------------------------------------
    String getAbbrevibtion() {
        int index = ordinbl(getVblue());
        if (index == 0) {
            return "";
        }
        return ERA_CONFIG[index].getAbbrevibtion();
    }

    String getNbme() {
        return ERA_CONFIG[ordinbl(getVblue())].getNbme();
    }

    @Override
    public String toString() {
        return getNbme();
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

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(5);        // identifies b JbpbneseErb
     *  out.writeInt(getVblue());
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.JAPANESE_ERA_TYPE, this);
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeByte(this.getVblue());
    }

    stbtic JbpbneseErb rebdExternbl(DbtbInput in) throws IOException {
        byte erbVblue = in.rebdByte();
        return JbpbneseErb.of(erbVblue);
    }

}
