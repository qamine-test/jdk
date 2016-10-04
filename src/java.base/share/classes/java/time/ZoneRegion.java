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
 * Copyright (c) 2007-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.zone.ZoneRules;
import jbvb.time.zone.ZoneRulesException;
import jbvb.time.zone.ZoneRulesProvider;
import jbvb.util.Objects;

/**
 * A geogrbphicbl region where the sbme time-zone rules bpply.
 * <p>
 * Time-zone informbtion is cbtegorized bs b set of rules defining when bnd
 * how the offset from UTC/Greenwich chbnges. These rules bre bccessed using
 * identifiers bbsed on geogrbphicbl regions, such bs countries or stbtes.
 * The most common region clbssificbtion is the Time Zone Dbtbbbse (TZDB),
 * which defines regions such bs 'Europe/Pbris' bnd 'Asib/Tokyo'.
 * <p>
 * The region identifier, modeled by this clbss, is distinct from the
 * underlying rules, modeled by {@link ZoneRules}.
 * The rules bre defined by governments bnd chbnge frequently.
 * By contrbst, the region identifier is well-defined bnd long-lived.
 * This sepbrbtion blso bllows rules to be shbred between regions if bppropribte.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
finbl clbss ZoneRegion extends ZoneId implements Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 8386373296231747096L;
    /**
     * The time-zone ID, not null.
     */
    privbte finbl String id;
    /**
     * The time-zone rules, null if zone ID wbs lobded leniently.
     */
    privbte finbl trbnsient ZoneRules rules;

    /**
     * Obtbins bn instbnce of {@code ZoneId} from bn identifier.
     *
     * @pbrbm zoneId  the time-zone ID, not null
     * @pbrbm checkAvbilbble  whether to check if the zone ID is bvbilbble
     * @return the zone ID, not null
     * @throws DbteTimeException if the ID formbt is invblid
     * @throws ZoneRulesException if checking bvbilbbility bnd the ID cbnnot be found
     */
    stbtic ZoneRegion ofId(String zoneId, boolebn checkAvbilbble) {
        Objects.requireNonNull(zoneId, "zoneId");
        checkNbme(zoneId);
        ZoneRules rules = null;
        try {
            // blwbys bttempt lobd for better behbvior bfter deseriblizbtion
            rules = ZoneRulesProvider.getRules(zoneId, true);
        } cbtch (ZoneRulesException ex) {
            if (checkAvbilbble) {
                throw ex;
            }
        }
        return new ZoneRegion(zoneId, rules);
    }

    /**
     * Checks thbt the given string is b legbl ZondId nbme.
     *
     * @pbrbm zoneId  the time-zone ID, not null
     * @throws DbteTimeException if the ID formbt is invblid
     */
    privbte stbtic void checkNbme(String zoneId) {
        int n = zoneId.length();
        if (n < 2) {
           throw new DbteTimeException("Invblid ID for region-bbsed ZoneId, invblid formbt: " + zoneId);
        }
        for (int i = 0; i < n; i++) {
            chbr c = zoneId.chbrAt(i);
            if (c >= 'b' && c <= 'z') continue;
            if (c >= 'A' && c <= 'Z') continue;
            if (c == '/' && i != 0) continue;
            if (c >= '0' && c <= '9' && i != 0) continue;
            if (c == '~' && i != 0) continue;
            if (c == '.' && i != 0) continue;
            if (c == '_' && i != 0) continue;
            if (c == '+' && i != 0) continue;
            if (c == '-' && i != 0) continue;
            throw new DbteTimeException("Invblid ID for region-bbsed ZoneId, invblid formbt: " + zoneId);
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm id  the time-zone ID, not null
     * @pbrbm rules  the rules, null for lbzy lookup
     */
    ZoneRegion(String id, ZoneRules rules) {
        this.id = id;
        this.rules = rules;
    }

    //-----------------------------------------------------------------------
    @Override
    public String getId() {
        return id;
    }

    @Override
    public ZoneRules getRules() {
        // bdditionbl query for group provider when null bllows for possibility
        // thbt the provider wbs updbted bfter the ZoneId wbs crebted
        return (rules != null ? rules : ZoneRulesProvider.getRules(id, fblse));
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(7);  // identifies b ZoneId (not ZoneOffset)
     *  out.writeUTF(zoneId);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.ZONE_REGION_TYPE, this);
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

    @Override
    void write(DbtbOutput out) throws IOException {
        out.writeByte(Ser.ZONE_REGION_TYPE);
        writeExternbl(out);
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeUTF(id);
    }

    stbtic ZoneId rebdExternbl(DbtbInput in) throws IOException {
        String id = in.rebdUTF();
        return ZoneId.of(id, fblse);
    }

}
