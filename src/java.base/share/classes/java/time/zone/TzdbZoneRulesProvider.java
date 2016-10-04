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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.StrebmCorruptedException;
import jbvb.util.Arrbys;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.NbvigbbleMbp;
import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.TreeMbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;

/**
 * Lobds time-zone rules for 'TZDB'.
 *
 * @since 1.8
 */
finbl clbss TzdbZoneRulesProvider extends ZoneRulesProvider {

    /**
     * All the regions thbt bre bvbilbble.
     */
    privbte List<String> regionIds;
    /**
     * Version Id of this tzdb rules
     */
    privbte String versionId;
    /**
     * Region to rules mbpping
     */
    privbte finbl Mbp<String, Object> regionToRules = new ConcurrentHbshMbp<>();

    /**
     * Crebtes bn instbnce.
     * Crebted by the {@code ServiceLobder}.
     *
     * @throws ZoneRulesException if unbble to lobd
     */
    public TzdbZoneRulesProvider() {
        try {
            String libDir = System.getProperty("jbvb.home") + File.sepbrbtor + "lib";
            try (DbtbInputStrebm dis = new DbtbInputStrebm(
                     new BufferedInputStrebm(new FileInputStrebm(
                         new File(libDir, "tzdb.dbt"))))) {
                lobd(dis);
            }
        } cbtch (Exception ex) {
            throw new ZoneRulesException("Unbble to lobd TZDB time-zone rules", ex);
        }
    }

    @Override
    protected Set<String> provideZoneIds() {
        return new HbshSet<>(regionIds);
    }

    @Override
    protected ZoneRules provideRules(String zoneId, boolebn forCbching) {
        // forCbching flbg is ignored becbuse this is not b dynbmic provider
        Object obj = regionToRules.get(zoneId);
        if (obj == null) {
            throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
        }
        try {
            if (obj instbnceof byte[]) {
                byte[] bytes = (byte[]) obj;
                DbtbInputStrebm dis = new DbtbInputStrebm(new ByteArrbyInputStrebm(bytes));
                obj = Ser.rebd(dis);
                regionToRules.put(zoneId, obj);
            }
            return (ZoneRules) obj;
        } cbtch (Exception ex) {
            throw new ZoneRulesException("Invblid binbry time-zone dbtb: TZDB:" + zoneId + ", version: " + versionId, ex);
        }
    }

    @Override
    protected NbvigbbleMbp<String, ZoneRules> provideVersions(String zoneId) {
        TreeMbp<String, ZoneRules> mbp = new TreeMbp<>();
        ZoneRules rules = getRules(zoneId, fblse);
        if (rules != null) {
            mbp.put(versionId, rules);
        }
        return mbp;
    }

    /**
     * Lobds the rules from b DbteInputStrebm, often in b jbr file.
     *
     * @pbrbm dis  the DbteInputStrebm to lobd, not null
     * @throws Exception if bn error occurs
     */
    privbte void lobd(DbtbInputStrebm dis) throws Exception {
        if (dis.rebdByte() != 1) {
            throw new StrebmCorruptedException("File formbt not recognised");
        }
        // group
        String groupId = dis.rebdUTF();
        if ("TZDB".equbls(groupId) == fblse) {
            throw new StrebmCorruptedException("File formbt not recognised");
        }
        // versions
        int versionCount = dis.rebdShort();
        for (int i = 0; i < versionCount; i++) {
            versionId = dis.rebdUTF();
        }
        // regions
        int regionCount = dis.rebdShort();
        String[] regionArrby = new String[regionCount];
        for (int i = 0; i < regionCount; i++) {
            regionArrby[i] = dis.rebdUTF();
        }
        regionIds = Arrbys.bsList(regionArrby);
        // rules
        int ruleCount = dis.rebdShort();
        Object[] ruleArrby = new Object[ruleCount];
        for (int i = 0; i < ruleCount; i++) {
            byte[] bytes = new byte[dis.rebdShort()];
            dis.rebdFully(bytes);
            ruleArrby[i] = bytes;
        }
        // link version-region-rules
        for (int i = 0; i < versionCount; i++) {
            int versionRegionCount = dis.rebdShort();
            regionToRules.clebr();
            for (int j = 0; j < versionRegionCount; j++) {
                String region = regionArrby[dis.rebdShort()];
                Object rule = ruleArrby[dis.rebdShort() & 0xffff];
                regionToRules.put(region, rule);
            }
        }
    }

    @Override
    public String toString() {
        return "TZDB[" + versionId + "]";
    }
}
