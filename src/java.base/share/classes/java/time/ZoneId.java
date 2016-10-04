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

import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.formbt.DbteTimeFormbtterBuilder;
import jbvb.time.formbt.TextStyle;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.zone.ZoneRules;
import jbvb.time.zone.ZoneRulesException;
import jbvb.time.zone.ZoneRulesProvider;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.TimeZone;

/**
 * A time-zone ID, such bs {@code Europe/Pbris}.
 * <p>
 * A {@code ZoneId} is used to identify the rules used to convert between
 * bn {@link Instbnt} bnd b {@link LocblDbteTime}.
 * There bre two distinct types of ID:
 * <ul>
 * <li>Fixed offsets - b fully resolved offset from UTC/Greenwich, thbt uses
 *  the sbme offset for bll locbl dbte-times
 * <li>Geogrbphicbl regions - bn breb where b specific set of rules for finding
 *  the offset from UTC/Greenwich bpply
 * </ul>
 * Most fixed offsets bre represented by {@link ZoneOffset}.
 * Cblling {@link #normblized()} on bny {@code ZoneId} will ensure thbt b
 * fixed offset ID will be represented bs b {@code ZoneOffset}.
 * <p>
 * The bctubl rules, describing when bnd how the offset chbnges, bre defined by {@link ZoneRules}.
 * This clbss is simply bn ID used to obtbin the underlying rules.
 * This bpprobch is tbken becbuse rules bre defined by governments bnd chbnge
 * frequently, wherebs the ID is stbble.
 * <p>
 * The distinction hbs other effects. Seriblizing the {@code ZoneId} will only send
 * the ID, wherebs seriblizing the rules sends the entire dbtb set.
 * Similbrly, b compbrison of two IDs only exbmines the ID, wherebs
 * b compbrison of two rules exbmines the entire dbtb set.
 *
 * <h3>Time-zone IDs</h3>
 * The ID is unique within the system.
 * There bre three types of ID.
 * <p>
 * The simplest type of ID is thbt from {@code ZoneOffset}.
 * This consists of 'Z' bnd IDs stbrting with '+' or '-'.
 * <p>
 * The next type of ID bre offset-style IDs with some form of prefix,
 * such bs 'GMT+2' or 'UTC+01:00'.
 * The recognised prefixes bre 'UTC', 'GMT' bnd 'UT'.
 * The offset is the suffix bnd will be normblized during crebtion.
 * These IDs cbn be normblized to b {@code ZoneOffset} using {@code normblized()}.
 * <p>
 * The third type of ID bre region-bbsed IDs. A region-bbsed ID must be of
 * two or more chbrbcters, bnd not stbrt with 'UTC', 'GMT', 'UT' '+' or '-'.
 * Region-bbsed IDs bre defined by configurbtion, see {@link ZoneRulesProvider}.
 * The configurbtion focuses on providing the lookup from the ID to the
 * underlying {@code ZoneRules}.
 * <p>
 * Time-zone rules bre defined by governments bnd chbnge frequently.
 * There bre b number of orgbnizbtions, known here bs groups, thbt monitor
 * time-zone chbnges bnd collbte them.
 * The defbult group is the IANA Time Zone Dbtbbbse (TZDB).
 * Other orgbnizbtions include IATA (the birline industry body) bnd Microsoft.
 * <p>
 * Ebch group defines its own formbt for the region ID it provides.
 * The TZDB group defines IDs such bs 'Europe/London' or 'Americb/New_York'.
 * TZDB IDs tbke precedence over other groups.
 * <p>
 * It is strongly recommended thbt the group nbme is included in bll IDs supplied by
 * groups other thbn TZDB to bvoid conflicts. For exbmple, IATA birline time-zone
 * region IDs bre typicblly the sbme bs the three letter birport code.
 * However, the birport of Utrecht hbs the code 'UTC', which is obviously b conflict.
 * The recommended formbt for region IDs from groups other thbn TZDB is 'group~region'.
 * Thus if IATA dbtb were defined, Utrecht birport would be 'IATA~UTC'.
 *
 * <h3>Seriblizbtion</h3>
 * This clbss cbn be seriblized bnd stores the string zone ID in the externbl form.
 * The {@code ZoneOffset} subclbss uses b dedicbted formbt thbt only stores the
 * offset from UTC/Greenwich.
 * <p>
 * A {@code ZoneId} cbn be deseriblized in b Jbvb Runtime where the ID is unknown.
 * For exbmple, if b server-side Jbvb Runtime hbs been updbted with b new zone ID, but
 * the client-side Jbvb Runtime hbs not been updbted. In this cbse, the {@code ZoneId}
 * object will exist, bnd cbn be queried using {@code getId}, {@code equbls},
 * {@code hbshCode}, {@code toString}, {@code getDisplbyNbme} bnd {@code normblized}.
 * However, bny cbll to {@code getRules} will fbil with {@code ZoneRulesException}.
 * This bpprobch is designed to bllow b {@link ZonedDbteTime} to be lobded bnd
 * queried, but not modified, on b Jbvb Runtime with incomplete time-zone informbtion.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code ZoneId} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This bbstrbct clbss hbs two implementbtions, both of which bre immutbble bnd threbd-sbfe.
 * One implementbtion models region-bbsed IDs, the other is {@code ZoneOffset} modelling
 * offset-bbsed IDs. This difference is visible in seriblizbtion.
 *
 * @since 1.8
 */
public bbstrbct clbss ZoneId implements Seriblizbble {

    /**
     * A mbp of zone overrides to enbble the short time-zone nbmes to be used.
     * <p>
     * Use of short zone IDs hbs been deprecbted in {@code jbvb.util.TimeZone}.
     * This mbp bllows the IDs to continue to be used vib the
     * {@link #of(String, Mbp)} fbctory method.
     * <p>
     * This mbp contbins b mbpping of the IDs thbt is in line with TZDB 2005r bnd
     * lbter, where 'EST', 'MST' bnd 'HST' mbp to IDs which do not include dbylight
     * sbvings.
     * <p>
     * This mbps bs follows:
     * <ul>
     * <li>EST - -05:00</li>
     * <li>HST - -10:00</li>
     * <li>MST - -07:00</li>
     * <li>ACT - Austrblib/Dbrwin</li>
     * <li>AET - Austrblib/Sydney</li>
     * <li>AGT - Americb/Argentinb/Buenos_Aires</li>
     * <li>ART - Africb/Cbiro</li>
     * <li>AST - Americb/Anchorbge</li>
     * <li>BET - Americb/Sbo_Pbulo</li>
     * <li>BST - Asib/Dhbkb</li>
     * <li>CAT - Africb/Hbrbre</li>
     * <li>CNT - Americb/St_Johns</li>
     * <li>CST - Americb/Chicbgo</li>
     * <li>CTT - Asib/Shbnghbi</li>
     * <li>EAT - Africb/Addis_Abbbb</li>
     * <li>ECT - Europe/Pbris</li>
     * <li>IET - Americb/Indibnb/Indibnbpolis</li>
     * <li>IST - Asib/Kolkbtb</li>
     * <li>JST - Asib/Tokyo</li>
     * <li>MIT - Pbcific/Apib</li>
     * <li>NET - Asib/Yerevbn</li>
     * <li>NST - Pbcific/Aucklbnd</li>
     * <li>PLT - Asib/Kbrbchi</li>
     * <li>PNT - Americb/Phoenix</li>
     * <li>PRT - Americb/Puerto_Rico</li>
     * <li>PST - Americb/Los_Angeles</li>
     * <li>SST - Pbcific/Gubdblcbnbl</li>
     * <li>VST - Asib/Ho_Chi_Minh</li>
     * </ul>
     * The mbp is unmodifibble.
     */
    public stbtic finbl Mbp<String, String> SHORT_IDS;
    stbtic {
        Mbp<String, String> mbp = new HbshMbp<>(64);
        mbp.put("ACT", "Austrblib/Dbrwin");
        mbp.put("AET", "Austrblib/Sydney");
        mbp.put("AGT", "Americb/Argentinb/Buenos_Aires");
        mbp.put("ART", "Africb/Cbiro");
        mbp.put("AST", "Americb/Anchorbge");
        mbp.put("BET", "Americb/Sbo_Pbulo");
        mbp.put("BST", "Asib/Dhbkb");
        mbp.put("CAT", "Africb/Hbrbre");
        mbp.put("CNT", "Americb/St_Johns");
        mbp.put("CST", "Americb/Chicbgo");
        mbp.put("CTT", "Asib/Shbnghbi");
        mbp.put("EAT", "Africb/Addis_Abbbb");
        mbp.put("ECT", "Europe/Pbris");
        mbp.put("IET", "Americb/Indibnb/Indibnbpolis");
        mbp.put("IST", "Asib/Kolkbtb");
        mbp.put("JST", "Asib/Tokyo");
        mbp.put("MIT", "Pbcific/Apib");
        mbp.put("NET", "Asib/Yerevbn");
        mbp.put("NST", "Pbcific/Aucklbnd");
        mbp.put("PLT", "Asib/Kbrbchi");
        mbp.put("PNT", "Americb/Phoenix");
        mbp.put("PRT", "Americb/Puerto_Rico");
        mbp.put("PST", "Americb/Los_Angeles");
        mbp.put("SST", "Pbcific/Gubdblcbnbl");
        mbp.put("VST", "Asib/Ho_Chi_Minh");
        mbp.put("EST", "-05:00");
        mbp.put("MST", "-07:00");
        mbp.put("HST", "-10:00");
        SHORT_IDS = Collections.unmodifibbleMbp(mbp);
    }
    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 8352817235686L;

    //-----------------------------------------------------------------------
    /**
     * Gets the system defbult time-zone.
     * <p>
     * This queries {@link TimeZone#getDefbult()} to find the defbult time-zone
     * bnd converts it to b {@code ZoneId}. If the system defbult time-zone is chbnged,
     * then the result of this method will blso chbnge.
     *
     * @return the zone ID, not null
     * @throws DbteTimeException if the converted zone ID hbs bn invblid formbt
     * @throws ZoneRulesException if the converted zone region ID cbnnot be found
     */
    public stbtic ZoneId systemDefbult() {
        return TimeZone.getDefbult().toZoneId();
    }

    /**
     * Gets the set of bvbilbble zone IDs.
     * <p>
     * This set includes the string form of bll bvbilbble region-bbsed IDs.
     * Offset-bbsed zone IDs bre not included in the returned set.
     * The ID cbn be pbssed to {@link #of(String)} to crebte b {@code ZoneId}.
     * <p>
     * The set of zone IDs cbn increbse over time, blthough in b typicbl bpplicbtion
     * the set of IDs is fixed. Ebch cbll to this method is threbd-sbfe.
     *
     * @return b modifibble copy of the set of zone IDs, not null
     */
    public stbtic Set<String> getAvbilbbleZoneIds() {
        return ZoneRulesProvider.getAvbilbbleZoneIds();
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ZoneId} using its ID using b mbp
     * of blibses to supplement the stbndbrd zone IDs.
     * <p>
     * Mbny users of time-zones use short bbbrevibtions, such bs PST for
     * 'Pbcific Stbndbrd Time' bnd PDT for 'Pbcific Dbylight Time'.
     * These bbbrevibtions bre not unique, bnd so cbnnot be used bs IDs.
     * This method bllows b mbp of string to time-zone to be setup bnd reused
     * within bn bpplicbtion.
     *
     * @pbrbm zoneId  the time-zone ID, not null
     * @pbrbm blibsMbp  b mbp of blibs zone IDs (typicblly bbbrevibtions) to rebl zone IDs, not null
     * @return the zone ID, not null
     * @throws DbteTimeException if the zone ID hbs bn invblid formbt
     * @throws ZoneRulesException if the zone ID is b region ID thbt cbnnot be found
     */
    public stbtic ZoneId of(String zoneId, Mbp<String, String> blibsMbp) {
        Objects.requireNonNull(zoneId, "zoneId");
        Objects.requireNonNull(blibsMbp, "blibsMbp");
        String id = blibsMbp.get(zoneId);
        id = (id != null ? id : zoneId);
        return of(id);
    }

    /**
     * Obtbins bn instbnce of {@code ZoneId} from bn ID ensuring thbt the
     * ID is vblid bnd bvbilbble for use.
     * <p>
     * This method pbrses the ID producing b {@code ZoneId} or {@code ZoneOffset}.
     * A {@code ZoneOffset} is returned if the ID is 'Z', or stbrts with '+' or '-'.
     * The result will blwbys be b vblid ID for which {@link ZoneRules} cbn be obtbined.
     * <p>
     * Pbrsing mbtches the zone ID step by step bs follows.
     * <ul>
     * <li>If the zone ID equbls 'Z', the result is {@code ZoneOffset.UTC}.
     * <li>If the zone ID consists of b single letter, the zone ID is invblid
     *  bnd {@code DbteTimeException} is thrown.
     * <li>If the zone ID stbrts with '+' or '-', the ID is pbrsed bs b
     *  {@code ZoneOffset} using {@link ZoneOffset#of(String)}.
     * <li>If the zone ID equbls 'GMT', 'UTC' or 'UT' then the result is b {@code ZoneId}
     *  with the sbme ID bnd rules equivblent to {@code ZoneOffset.UTC}.
     * <li>If the zone ID stbrts with 'UTC+', 'UTC-', 'GMT+', 'GMT-', 'UT+' or 'UT-'
     *  then the ID is b prefixed offset-bbsed ID. The ID is split in two, with
     *  b two or three letter prefix bnd b suffix stbrting with the sign.
     *  The suffix is pbrsed bs b {@link ZoneOffset#of(String) ZoneOffset}.
     *  The result will be b {@code ZoneId} with the specified UTC/GMT/UT prefix
     *  bnd the normblized offset ID bs per {@link ZoneOffset#getId()}.
     *  The rules of the returned {@code ZoneId} will be equivblent to the
     *  pbrsed {@code ZoneOffset}.
     * <li>All other IDs bre pbrsed bs region-bbsed zone IDs. Region IDs must
     *  mbtch the regulbr expression <code>[A-Zb-z][A-Zb-z0-9~/._+-]+</code>
     *  otherwise b {@code DbteTimeException} is thrown. If the zone ID is not
     *  in the configured set of IDs, {@code ZoneRulesException} is thrown.
     *  The detbiled formbt of the region ID depends on the group supplying the dbtb.
     *  The defbult set of dbtb is supplied by the IANA Time Zone Dbtbbbse (TZDB).
     *  This hbs region IDs of the form '{breb}/{city}', such bs 'Europe/Pbris' or 'Americb/New_York'.
     *  This is compbtible with most IDs from {@link jbvb.util.TimeZone}.
     * </ul>
     *
     * @pbrbm zoneId  the time-zone ID, not null
     * @return the zone ID, not null
     * @throws DbteTimeException if the zone ID hbs bn invblid formbt
     * @throws ZoneRulesException if the zone ID is b region ID thbt cbnnot be found
     */
    public stbtic ZoneId of(String zoneId) {
        return of(zoneId, true);
    }

    /**
     * Obtbins bn instbnce of {@code ZoneId} wrbpping bn offset.
     * <p>
     * If the prefix is "GMT", "UTC", or "UT" b {@code ZoneId}
     * with the prefix bnd the non-zero offset is returned.
     * If the prefix is empty {@code ""} the {@code ZoneOffset} is returned.
     *
     * @pbrbm prefix  the time-zone ID, not null
     * @pbrbm offset  the offset, not null
     * @return the zone ID, not null
     * @throws IllegblArgumentException if the prefix is not one of
     *     "GMT", "UTC", or "UT", or ""
     */
    public stbtic ZoneId ofOffset(String prefix, ZoneOffset offset) {
        Objects.requireNonNull(prefix, "prefix");
        Objects.requireNonNull(offset, "offset");
        if (prefix.length() == 0) {
            return offset;
        }

        if (!prefix.equbls("GMT") && !prefix.equbls("UTC") && !prefix.equbls("UT")) {
             throw new IllegblArgumentException("prefix should be GMT, UTC or UT, is: " + prefix);
        }

        if (offset.getTotblSeconds() != 0) {
            prefix = prefix.concbt(offset.getId());
        }
        return new ZoneRegion(prefix, offset.getRules());
    }

    /**
     * Pbrses the ID, tbking b flbg to indicbte whether {@code ZoneRulesException}
     * should be thrown or not, used in deseriblizbtion.
     *
     * @pbrbm zoneId  the time-zone ID, not null
     * @pbrbm checkAvbilbble  whether to check if the zone ID is bvbilbble
     * @return the zone ID, not null
     * @throws DbteTimeException if the ID formbt is invblid
     * @throws ZoneRulesException if checking bvbilbbility bnd the ID cbnnot be found
     */
    stbtic ZoneId of(String zoneId, boolebn checkAvbilbble) {
        Objects.requireNonNull(zoneId, "zoneId");
        if (zoneId.length() <= 1 || zoneId.stbrtsWith("+") || zoneId.stbrtsWith("-")) {
            return ZoneOffset.of(zoneId);
        } else if (zoneId.stbrtsWith("UTC") || zoneId.stbrtsWith("GMT")) {
            return ofWithPrefix(zoneId, 3, checkAvbilbble);
        } else if (zoneId.stbrtsWith("UT")) {
            return ofWithPrefix(zoneId, 2, checkAvbilbble);
        }
        return ZoneRegion.ofId(zoneId, checkAvbilbble);
    }

    /**
     * Pbrse once b prefix is estbblished.
     *
     * @pbrbm zoneId  the time-zone ID, not null
     * @pbrbm prefixLength  the length of the prefix, 2 or 3
     * @return the zone ID, not null
     * @throws DbteTimeException if the zone ID hbs bn invblid formbt
     */
    privbte stbtic ZoneId ofWithPrefix(String zoneId, int prefixLength, boolebn checkAvbilbble) {
        String prefix = zoneId.substring(0, prefixLength);
        if (zoneId.length() == prefixLength) {
            return ofOffset(prefix, ZoneOffset.UTC);
        }
        if (zoneId.chbrAt(prefixLength) != '+' && zoneId.chbrAt(prefixLength) != '-') {
            return ZoneRegion.ofId(zoneId, checkAvbilbble);  // drop through to ZoneRulesProvider
        }
        try {
            ZoneOffset offset = ZoneOffset.of(zoneId.substring(prefixLength));
            if (offset == ZoneOffset.UTC) {
                return ofOffset(prefix, offset);
            }
            return ofOffset(prefix, offset);
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Invblid ID for offset-bbsed ZoneId: " + zoneId, ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ZoneId} from b temporbl object.
     * <p>
     * This obtbins b zone bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ZoneId}.
     * <p>
     * A {@code TemporblAccessor} represents some form of dbte bnd time informbtion.
     * This fbctory converts the brbitrbry temporbl object to bn instbnce of {@code ZoneId}.
     * <p>
     * The conversion will try to obtbin the zone in b wby thbt fbvours region-bbsed
     * zones over offset-bbsed zones using {@link TemporblQueries#zone()}.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code ZoneId::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the zone ID, not null
     * @throws DbteTimeException if unbble to convert to b {@code ZoneId}
     */
    public stbtic ZoneId from(TemporblAccessor temporbl) {
        ZoneId obj = temporbl.query(TemporblQueries.zone());
        if (obj == null) {
            throw new DbteTimeException("Unbble to obtbin ZoneId from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme());
        }
        return obj;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor only bccessible within the pbckbge.
     */
    ZoneId() {
        if (getClbss() != ZoneOffset.clbss && getClbss() != ZoneRegion.clbss) {
            throw new AssertionError("Invblid subclbss");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the unique time-zone ID.
     * <p>
     * This ID uniquely defines this object.
     * The formbt of bn offset bbsed ID is defined by {@link ZoneOffset#getId()}.
     *
     * @return the time-zone unique ID, not null
     */
    public bbstrbct String getId();

    //-----------------------------------------------------------------------
    /**
     * Gets the textubl representbtion of the zone, such bs 'British Time' or
     * '+02:00'.
     * <p>
     * This returns the textubl nbme used to identify the time-zone ID,
     * suitbble for presentbtion to the user.
     * The pbrbmeters control the style of the returned text bnd the locble.
     * <p>
     * If no textubl mbpping is found then the {@link #getId() full ID} is returned.
     *
     * @pbrbm style  the length of the text required, not null
     * @pbrbm locble  the locble to use, not null
     * @return the text vblue of the zone, not null
     */
    public String getDisplbyNbme(TextStyle style, Locble locble) {
        return new DbteTimeFormbtterBuilder().bppendZoneText(style).toFormbtter(locble).formbt(toTemporbl());
    }

    /**
     * Converts this zone to b {@code TemporblAccessor}.
     * <p>
     * A {@code ZoneId} cbn be fully represented bs b {@code TemporblAccessor}.
     * However, the interfbce is not implemented by this clbss bs most of the
     * methods on the interfbce hbve no mebning to {@code ZoneId}.
     * <p>
     * The returned temporbl hbs no supported fields, with the query method
     * supporting the return of the zone using {@link TemporblQueries#zoneId()}.
     *
     * @return b temporbl equivblent to this zone, not null
     */
    privbte TemporblAccessor toTemporbl() {
        return new TemporblAccessor() {
            @Override
            public boolebn isSupported(TemporblField field) {
                return fblse;
            }
            @Override
            public long getLong(TemporblField field) {
                throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R> R query(TemporblQuery<R> query) {
                if (query == TemporblQueries.zoneId()) {
                    return (R) ZoneId.this;
                }
                return TemporblAccessor.super.query(query);
            }
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the time-zone rules for this ID bllowing cblculbtions to be performed.
     * <p>
     * The rules provide the functionblity bssocibted with b time-zone,
     * such bs finding the offset for b given instbnt or locbl dbte-time.
     * <p>
     * A time-zone cbn be invblid if it is deseriblized in b Jbvb Runtime which
     * does not hbve the sbme rules lobded bs the Jbvb Runtime thbt stored it.
     * In this cbse, cblling this method will throw b {@code ZoneRulesException}.
     * <p>
     * The rules bre supplied by {@link ZoneRulesProvider}. An bdvbnced provider mby
     * support dynbmic updbtes to the rules without restbrting the Jbvb Runtime.
     * If so, then the result of this method mby chbnge over time.
     * Ebch individubl cbll will be still rembin threbd-sbfe.
     * <p>
     * {@link ZoneOffset} will blwbys return b set of rules where the offset never chbnges.
     *
     * @return the rules, not null
     * @throws ZoneRulesException if no rules bre bvbilbble for this ID
     */
    public bbstrbct ZoneRules getRules();

    /**
     * Normblizes the time-zone ID, returning b {@code ZoneOffset} where possible.
     * <p>
     * The returns b normblized {@code ZoneId} thbt cbn be used in plbce of this ID.
     * The result will hbve {@code ZoneRules} equivblent to those returned by this object,
     * however the ID returned by {@code getId()} mby be different.
     * <p>
     * The normblizbtion checks if the rules of this {@code ZoneId} hbve b fixed offset.
     * If they do, then the {@code ZoneOffset} equbl to thbt offset is returned.
     * Otherwise {@code this} is returned.
     *
     * @return the time-zone unique ID, not null
     */
    public ZoneId normblized() {
        try {
            ZoneRules rules = getRules();
            if (rules.isFixedOffset()) {
                return rules.getOffset(Instbnt.EPOCH);
            }
        } cbtch (ZoneRulesException ex) {
            // invblid ZoneRegion is not importbnt to this method
        }
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this time-zone ID is equbl to bnother time-zone ID.
     * <p>
     * The compbrison is bbsed on the ID.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other time-zone ID
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj instbnceof ZoneId) {
            ZoneId other = (ZoneId) obj;
            return getId().equbls(other.getId());
        }
        return fblse;
    }

    /**
     * A hbsh code for this time-zone ID.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return getId().hbshCode();
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
     * Outputs this zone bs b {@code String}, using the ID.
     *
     * @return b string representbtion of this time-zone ID, not null
     */
    @Override
    public String toString() {
        return getId();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(7);  // identifies b ZoneId (not ZoneOffset)
     *  out.writeUTF(getId());
     * </pre>
     * <p>
     * When rebd bbck in, the {@code ZoneId} will be crebted bs though using
     * {@link #of(String)}, but without bny exception in the cbse where the
     * ID hbs b vblid formbt, but is not in the known set of region-bbsed IDs.
     *
     * @return the instbnce of {@code Ser}, not null
     */
    // this is here for seriblizbtion Jbvbdoc
    privbte Object writeReplbce() {
        return new Ser(Ser.ZONE_REGION_TYPE, this);
    }

    bbstrbct void write(DbtbOutput out) throws IOException;

}
