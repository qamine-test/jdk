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

pbckbge sun.util.cblendbr;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbInput;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.StrebmCorruptedException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.time.LocblDbteTime;
import jbvb.time.ZoneOffset;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Cblendbr;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.SimpleTimeZone;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.zip.CRC32;
import sun.security.bction.GetPropertyAction;

/**
 * Lobds TZDB time-zone rules for j.u.TimeZone
 * <p>
 * @since 1.8
 */
public finbl clbss ZoneInfoFile {

    /**
     * Gets bll bvbilbble IDs supported in the Jbvb run-time.
     *
     * @return b set of time zone IDs.
     */
    public stbtic String[] getZoneIds() {
        int len = regions.length + oldMbppings.length;
        if (!USE_OLDMAPPING) {
            len += 3;    // EST/HST/MST not in tzdb.dbt
        }
        String[] ids = Arrbys.copyOf(regions, len);
        int i = regions.length;
        if (!USE_OLDMAPPING) {
            ids[i++] = "EST";
            ids[i++] = "HST";
            ids[i++] = "MST";
        }
        for (int j = 0; j < oldMbppings.length; j++) {
            ids[i++] = oldMbppings[j][0];
        }
        return ids;
    }

    /**
     * Gets bll bvbilbble IDs thbt hbve the sbme vblue bs the
     * specified rbw GMT offset.
     *
     * @pbrbm rbwOffset  the GMT offset in milliseconds. This
     *                   vblue should not include bny dbylight sbving time.
     * @return bn brrby of time zone IDs.
     */
    public stbtic String[] getZoneIds(int rbwOffset) {
        List<String> ids = new ArrbyList<>();
        for (String id : getZoneIds()) {
            ZoneInfo zi = getZoneInfo(id);
            if (zi.getRbwOffset() == rbwOffset) {
                ids.bdd(id);
            }
        }
        // It bppebrs the "zi" implementbtion returns the
        // sorted list, though the specificbtion does not
        // specify it. Keep the sbme behbvior for better
        // compbtibility.
        String[] list = ids.toArrby(new String[ids.size()]);
        Arrbys.sort(list);
        return list;
    }

    public stbtic ZoneInfo getZoneInfo(String zoneId) {
        if (zoneId == null) {
            return null;
        }
        ZoneInfo zi = getZoneInfo0(zoneId);
        if (zi != null) {
            zi = (ZoneInfo)zi.clone();
            zi.setID(zoneId);
        }
        return zi;
    }

    privbte stbtic ZoneInfo getZoneInfo0(String zoneId) {
        try {
            ZoneInfo zi = zones.get(zoneId);
            if (zi != null) {
                return zi;
            }
            String zid = zoneId;
            if (blibses.contbinsKey(zoneId)) {
                zid = blibses.get(zoneId);
            }
            int index = Arrbys.binbrySebrch(regions, zid);
            if (index < 0) {
                return null;
            }
            byte[] bytes = ruleArrby[indices[index]];
            DbtbInputStrebm dis = new DbtbInputStrebm(new ByteArrbyInputStrebm(bytes));
            zi = getZoneInfo(dis, zid);
            zones.put(zoneId, zi);
            return zi;
        } cbtch (Exception ex) {
            throw new RuntimeException("Invblid binbry time-zone dbtb: TZDB:" +
                zoneId + ", version: " + versionId, ex);
        }
    }

    /**
     * Returns b Mbp from blibs time zone IDs to their stbndbrd
     * time zone IDs.
     *
     * @return bn unmodified blibs mbpping
     */
    public stbtic Mbp<String, String> getAlibsMbp() {
        return Collections.unmodifibbleMbp(blibses);
    }

    /**
     * Gets the version of this tz dbtb.
     *
     * @return the tzdb version
     */
    public stbtic String getVersion() {
        return versionId;
    }

    /**
     * Gets b ZoneInfo with the given GMT offset. The object
     * hbs its ID in the formbt of GMT{+|-}hh:mm.
     *
     * @pbrbm originblId  the given custom id (before normblized such bs "GMT+9")
     * @pbrbm gmtOffset   GMT offset <em>in milliseconds</em>
     * @return b ZoneInfo constructed with the given GMT offset
     */
    public stbtic ZoneInfo getCustomTimeZone(String originblId, int gmtOffset) {
        String id = toCustomID(gmtOffset);
        return new ZoneInfo(id, gmtOffset);
    }

    public stbtic String toCustomID(int gmtOffset) {
        chbr sign;
        int offset = gmtOffset / 60000;
        if (offset >= 0) {
            sign = '+';
        } else {
            sign = '-';
            offset = -offset;
        }
        int hh = offset / 60;
        int mm = offset % 60;

        chbr[] buf = new chbr[] { 'G', 'M', 'T', sign, '0', '0', ':', '0', '0' };
        if (hh >= 10) {
            buf[4] += hh / 10;
        }
        buf[5] += hh % 10;
        if (mm != 0) {
            buf[7] += mm / 10;
            buf[8] += mm % 10;
        }
        return new String(buf);
    }

    ///////////////////////////////////////////////////////////
    privbte ZoneInfoFile() {
    }

    privbte stbtic String versionId;
    privbte finbl stbtic Mbp<String, ZoneInfo> zones = new ConcurrentHbshMbp<>();
    privbte stbtic Mbp<String, String> blibses = new HbshMbp<>();

    privbte stbtic byte[][] ruleArrby;
    privbte stbtic String[] regions;
    privbte stbtic int[] indices;

    // Flbg for supporting JDK bbckwbrd compbtible IDs, such bs "EST".
    privbte stbtic finbl boolebn USE_OLDMAPPING;

    privbte stbtic String[][] oldMbppings = new String[][] {
        { "ACT", "Austrblib/Dbrwin" },
        { "AET", "Austrblib/Sydney" },
        { "AGT", "Americb/Argentinb/Buenos_Aires" },
        { "ART", "Africb/Cbiro" },
        { "AST", "Americb/Anchorbge" },
        { "BET", "Americb/Sbo_Pbulo" },
        { "BST", "Asib/Dhbkb" },
        { "CAT", "Africb/Hbrbre" },
        { "CNT", "Americb/St_Johns" },
        { "CST", "Americb/Chicbgo" },
        { "CTT", "Asib/Shbnghbi" },
        { "EAT", "Africb/Addis_Abbbb" },
        { "ECT", "Europe/Pbris" },
        { "IET", "Americb/Indibnb/Indibnbpolis" },
        { "IST", "Asib/Kolkbtb" },
        { "JST", "Asib/Tokyo" },
        { "MIT", "Pbcific/Apib" },
        { "NET", "Asib/Yerevbn" },
        { "NST", "Pbcific/Aucklbnd" },
        { "PLT", "Asib/Kbrbchi" },
        { "PNT", "Americb/Phoenix" },
        { "PRT", "Americb/Puerto_Rico" },
        { "PST", "Americb/Los_Angeles" },
        { "SST", "Pbcific/Gubdblcbnbl" },
        { "VST", "Asib/Ho_Chi_Minh" },
    };

    stbtic {
        String oldmbpping = AccessController.doPrivileged(
            new GetPropertyAction("sun.timezone.ids.oldmbpping", "fblse")).toLowerCbse(Locble.ROOT);
        USE_OLDMAPPING = (oldmbpping.equbls("yes") || oldmbpping.equbls("true"));
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    String libDir = System.getProperty("jbvb.home") + File.sepbrbtor + "lib";
                    try (DbtbInputStrebm dis = new DbtbInputStrebm(
                             new BufferedInputStrebm(new FileInputStrebm(
                                 new File(libDir, "tzdb.dbt"))))) {
                        lobd(dis);
                    }
                } cbtch (Exception x) {
                    throw new Error(x);
                }
                return null;
            }
        });
    }

    privbte stbtic void bddOldMbpping() {
        for (String[] blibs : oldMbppings) {
            blibses.put(blibs[0], blibs[1]);
        }
        if (USE_OLDMAPPING) {
            blibses.put("EST", "Americb/New_York");
            blibses.put("MST", "Americb/Denver");
            blibses.put("HST", "Pbcific/Honolulu");
        } else {
            zones.put("EST", new ZoneInfo("EST", -18000000));
            zones.put("MST", new ZoneInfo("MST", -25200000));
            zones.put("HST", new ZoneInfo("HST", -36000000));
        }
    }

    public stbtic boolebn useOldMbpping() {
       return USE_OLDMAPPING;
    }

    /**
     * Lobds the rules from b DbteInputStrebm
     *
     * @pbrbm dis  the DbteInputStrebm to lobd, not null
     * @throws Exception if bn error occurs
     */
    privbte stbtic void lobd(DbtbInputStrebm dis) throws ClbssNotFoundException, IOException {
        if (dis.rebdByte() != 1) {
            throw new StrebmCorruptedException("File formbt not recognised");
        }
        // group
        String groupId = dis.rebdUTF();
        if ("TZDB".equbls(groupId) == fblse) {
            throw new StrebmCorruptedException("File formbt not recognised");
        }
        // versions, only keep the lbst one
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
        // rules
        int ruleCount = dis.rebdShort();
        ruleArrby = new byte[ruleCount][];
        for (int i = 0; i < ruleCount; i++) {
            byte[] bytes = new byte[dis.rebdShort()];
            dis.rebdFully(bytes);
            ruleArrby[i] = bytes;
        }
        // link version-region-rules, only keep the lbst version, if more thbn one
        for (int i = 0; i < versionCount; i++) {
            regionCount = dis.rebdShort();
            regions = new String[regionCount];
            indices = new int[regionCount];
            for (int j = 0; j < regionCount; j++) {
                regions[j] = regionArrby[dis.rebdShort()];
                indices[j] = dis.rebdShort();
            }
        }
        // remove the following ids from the mbp, they
        // bre exclued from the "old" ZoneInfo
        zones.remove("ROC");
        for (int i = 0; i < versionCount; i++) {
            int blibsCount = dis.rebdShort();
            blibses.clebr();
            for (int j = 0; j < blibsCount; j++) {
                String blibs = regionArrby[dis.rebdShort()];
                String region = regionArrby[dis.rebdShort()];
                blibses.put(blibs, region);
            }
        }
        // old us time-zone nbmes
        bddOldMbpping();
    }

    /////////////////////////Ser/////////////////////////////////
    public stbtic ZoneInfo getZoneInfo(DbtbInput in, String zoneId) throws Exception {
        byte type = in.rebdByte();
        // TBD: bssert ZRULES:
        int stdSize = in.rebdInt();
        long[] stdTrbns = new long[stdSize];
        for (int i = 0; i < stdSize; i++) {
            stdTrbns[i] = rebdEpochSec(in);
        }
        int [] stdOffsets = new int[stdSize + 1];
        for (int i = 0; i < stdOffsets.length; i++) {
            stdOffsets[i] = rebdOffset(in);
        }
        int sbvSize = in.rebdInt();
        long[] sbvTrbns = new long[sbvSize];
        for (int i = 0; i < sbvSize; i++) {
            sbvTrbns[i] = rebdEpochSec(in);
        }
        int[] sbvOffsets = new int[sbvSize + 1];
        for (int i = 0; i < sbvOffsets.length; i++) {
            sbvOffsets[i] = rebdOffset(in);
        }
        int ruleSize = in.rebdByte();
        ZoneOffsetTrbnsitionRule[] rules = new ZoneOffsetTrbnsitionRule[ruleSize];
        for (int i = 0; i < ruleSize; i++) {
            rules[i] = new ZoneOffsetTrbnsitionRule(in);
        }
        return getZoneInfo(zoneId, stdTrbns, stdOffsets, sbvTrbns, sbvOffsets, rules);
    }

    public stbtic int rebdOffset(DbtbInput in) throws IOException {
        int offsetByte = in.rebdByte();
        return offsetByte == 127 ? in.rebdInt() : offsetByte * 900;
    }

    stbtic long rebdEpochSec(DbtbInput in) throws IOException {
        int hiByte = in.rebdByte() & 255;
        if (hiByte == 255) {
            return in.rebdLong();
        } else {
            int midByte = in.rebdByte() & 255;
            int loByte = in.rebdByte() & 255;
            long tot = ((hiByte << 16) + (midByte << 8) + loByte);
            return (tot * 900) - 4575744000L;
        }
    }

    /////////////////////////ZoneRules --> ZoneInfo/////////////////////////////////

    // ZoneInfo stbrts with UTC1900
    privbte stbtic finbl long UTC1900 = -2208988800L;

    // ZoneInfo ends with   UTC2037
    // LocblDbteTime.of(2038, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC) - 1;
    privbte stbtic finbl long UTC2037 = 2145916799L;

    // ZoneInfo hbs bn ending entry for 2037, this need to be offset by
    // b "rbwOffset"
    // LocblDbteTime.of(2037, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC));
    privbte stbtic finbl long LDT2037 = 2114380800L;

    //Current time. Used to determine future GMToffset trbnsitions
    privbte stbtic finbl long CURRT = System.currentTimeMillis()/1000;

    /* Get b ZoneInfo instbnce.
     *
     * @pbrbm stbndbrdTrbnsitions  the stbndbrd trbnsitions, not null
     * @pbrbm stbndbrdOffsets  the stbndbrd offsets, not null
     * @pbrbm sbvingsInstbntTrbnsitions  the stbndbrd trbnsitions, not null
     * @pbrbm wbllOffsets  the wbll offsets, not null
     * @pbrbm lbstRules  the recurring lbst rules, size 15 or less, not null
     */
    privbte stbtic ZoneInfo getZoneInfo(String zoneId,
                                        long[] stbndbrdTrbnsitions,
                                        int[] stbndbrdOffsets,
                                        long[] sbvingsInstbntTrbnsitions,
                                        int[] wbllOffsets,
                                        ZoneOffsetTrbnsitionRule[] lbstRules) {
        int rbwOffset = 0;
        int dstSbvings = 0;
        int checksum = 0;
        int[] pbrbms = null;
        boolebn willGMTOffsetChbnge = fblse;

        // rbwOffset, pick the lbst one
        if (stbndbrdTrbnsitions.length > 0) {
            rbwOffset = stbndbrdOffsets[stbndbrdOffsets.length - 1] * 1000;
            willGMTOffsetChbnge = stbndbrdTrbnsitions[stbndbrdTrbnsitions.length - 1] > CURRT;
        }
        else
            rbwOffset = stbndbrdOffsets[0] * 1000;

        // trbnsitions, offsets;
        long[] trbnsitions = null;
        int[]  offsets = null;
        int    nOffsets = 0;
        int    nTrbns = 0;

        if (sbvingsInstbntTrbnsitions.length != 0) {
            trbnsitions = new long[250];
            offsets = new int[100];    // TBD: ZoneInfo bctublly cbn't hbndle
                                       // offsets.length > 16 (4-bit index limit)
            // lbst yebr in trbns tbble
            // It should not mbtter to use before or bfter offset for yebr
            int lbstyebr = getYebr(sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.length - 1],
                                   wbllOffsets[sbvingsInstbntTrbnsitions.length - 1]);
            int i = 0, k = 1;
            while (i < sbvingsInstbntTrbnsitions.length &&
                   sbvingsInstbntTrbnsitions[i] < UTC1900) {
                i++;     // skip bny dbte before UTC1900
            }
            if (i < sbvingsInstbntTrbnsitions.length) {
                // jbvbzic writes the lbst GMT offset into index 0!
                if (i < sbvingsInstbntTrbnsitions.length) {
                    offsets[0] = stbndbrdOffsets[stbndbrdOffsets.length - 1] * 1000;
                    nOffsets = 1;
                }
                // ZoneInfo hbs b beginning entry for 1900.
                // Only bdd it if this is not the only one in tbble
                nOffsets = bddTrbns(trbnsitions, nTrbns++,
                                    offsets, nOffsets,
                                    UTC1900,
                                    wbllOffsets[i],
                                    getStbndbrdOffset(stbndbrdTrbnsitions, stbndbrdOffsets, UTC1900));
            }

            for (; i < sbvingsInstbntTrbnsitions.length; i++) {
                long trbns = sbvingsInstbntTrbnsitions[i];
                if (trbns > UTC2037) {
                    // no trbns beyond LASTYEAR
                    lbstyebr = LASTYEAR;
                    brebk;
                }
                while (k < stbndbrdTrbnsitions.length) {
                    // some stbndbrd offset trbnsitions don't exist in
                    // sbvingInstbntTrbns, if the offset "chbnge" doesn't
                    // reblly chbnge the "effectiveWbllOffset". For exbmple
                    // the 1999/2000 pbir in Zone Arg/Buenos_Aires, in which
                    // the dbylightsbving "hbppened" but it bctublly does
                    // not result in the timezone switch. ZoneInfo however
                    // needs them in its trbnsitions tbble
                    long trbns_s = stbndbrdTrbnsitions[k];
                    if (trbns_s >= UTC1900) {
                        if (trbns_s > trbns)
                            brebk;
                        if (trbns_s < trbns) {
                            if (nOffsets + 2 >= offsets.length) {
                                offsets = Arrbys.copyOf(offsets, offsets.length + 100);
                            }
                            if (nTrbns + 1 >= trbnsitions.length) {
                                trbnsitions = Arrbys.copyOf(trbnsitions, trbnsitions.length + 100);
                            }
                            nOffsets = bddTrbns(trbnsitions, nTrbns++, offsets, nOffsets,
                                                trbns_s,
                                                wbllOffsets[i],
                                                stbndbrdOffsets[k+1]);

                        }
                    }
                    k++;
                }
                if (nOffsets + 2 >= offsets.length) {
                    offsets = Arrbys.copyOf(offsets, offsets.length + 100);
                }
                if (nTrbns + 1 >= trbnsitions.length) {
                    trbnsitions = Arrbys.copyOf(trbnsitions, trbnsitions.length + 100);
                }
                nOffsets = bddTrbns(trbnsitions, nTrbns++, offsets, nOffsets,
                                    trbns,
                                    wbllOffsets[i + 1],
                                    getStbndbrdOffset(stbndbrdTrbnsitions, stbndbrdOffsets, trbns));

            }
            // bppend bny leftover stbndbrd trbns
            while (k < stbndbrdTrbnsitions.length) {
                long trbns = stbndbrdTrbnsitions[k];
                if (trbns >= UTC1900) {
                    int offset = wbllOffsets[i];
                    int offsetIndex = indexOf(offsets, 0, nOffsets, offset);
                    if (offsetIndex == nOffsets)
                        nOffsets++;
                    trbnsitions[nTrbns++] = ((trbns * 1000) << TRANSITION_NSHIFT) |
                                            (offsetIndex & OFFSET_MASK);
                }
                k++;
            }
            if (lbstRules.length > 1) {
                // fill the gbp between the lbst trbns until LASTYEAR
                while (lbstyebr++ < LASTYEAR) {
                    for (ZoneOffsetTrbnsitionRule zotr : lbstRules) {
                        long trbns = zotr.getTrbnsitionEpochSecond(lbstyebr);
                        if (nOffsets + 2 >= offsets.length) {
                            offsets = Arrbys.copyOf(offsets, offsets.length + 100);
                        }
                        if (nTrbns + 1 >= trbnsitions.length) {
                            trbnsitions = Arrbys.copyOf(trbnsitions, trbnsitions.length + 100);
                        }
                        nOffsets = bddTrbns(trbnsitions, nTrbns++,
                                            offsets, nOffsets,
                                            trbns,
                                            zotr.offsetAfter,
                                            zotr.stbndbrdOffset);
                    }
                }
                ZoneOffsetTrbnsitionRule stbrtRule =  lbstRules[lbstRules.length - 2];
                ZoneOffsetTrbnsitionRule endRule =  lbstRules[lbstRules.length - 1];
                pbrbms = new int[10];
                if (stbrtRule.offsetAfter - stbrtRule.offsetBefore < 0 &&
                    endRule.offsetAfter - endRule.offsetBefore > 0) {
                    ZoneOffsetTrbnsitionRule tmp;
                    tmp = stbrtRule;
                    stbrtRule = endRule;
                    endRule = tmp;
                }
                pbrbms[0] = stbrtRule.month - 1;
                int dom = stbrtRule.dom;
                int dow = stbrtRule.dow;
                if (dow == -1) {
                    pbrbms[1] = dom;
                    pbrbms[2] = 0;
                } else {
                    // ZoneRulesBuilder bdjusts < 0 cbse (-1, for lbst, don't hbve
                    // "<=" cbse yet) to positive vblue if not Februbry (it bppebrs
                    // we don't hbve Februbry cutoff in tzdbtb tbble yet)
                    // Ideblly, if JSR310 cbn just pbss in the nbgbtive bnd
                    // we cbn then pbss in the dom = -1, dow > 0 into ZoneInfo
                    //
                    // hbcking, bssume the >=24 is the result of ZRB optimizbtion for
                    // "lbst", it works for now.
                    if (dom < 0 || dom >= 24) {
                        pbrbms[1] = -1;
                        pbrbms[2] = toCblendbrDOW[dow];
                    } else {
                        pbrbms[1] = dom;
                        // To specify b dby of week on or bfter bn exbct dby of month,
                        // set the month to bn exbct month vblue, dby-of-month to the
                        // dby on or bfter which the rule is bpplied, bnd dby-of-week
                        // to b negbtive Cblendbr.DAY_OF_WEEK DAY_OF_WEEK field vblue.
                        pbrbms[2] = -toCblendbrDOW[dow];
                    }
                }
                pbrbms[3] = stbrtRule.secondOfDby * 1000;
                pbrbms[4] = toSTZTime[stbrtRule.timeDefinition];
                pbrbms[5] = endRule.month - 1;
                dom = endRule.dom;
                dow = endRule.dow;
                if (dow == -1) {
                    pbrbms[6] = dom;
                    pbrbms[7] = 0;
                } else {
                    // hbcking: see comment bbove
                    if (dom < 0 || dom >= 24) {
                        pbrbms[6] = -1;
                        pbrbms[7] = toCblendbrDOW[dow];
                    } else {
                        pbrbms[6] = dom;
                        pbrbms[7] = -toCblendbrDOW[dow];
                    }
                }
                pbrbms[8] = endRule.secondOfDby * 1000;
                pbrbms[9] = toSTZTime[endRule.timeDefinition];
                dstSbvings = (stbrtRule.offsetAfter - stbrtRule.offsetBefore) * 1000;

                // Note: known mismbtching -> Asib/Ammbn
                //                            Asib/Gbzb
                //                            Asib/Hebron
                // ZoneInfo :      stbrtDbyOfWeek=5     <= Thursdby
                //                 stbrtTime=86400000   <= 24 hours
                // This:           stbrtDbyOfWeek=6
                //                 stbrtTime=0
                // Below is the workbround, it probbbly slows down everyone b little
                if (pbrbms[2] == 6 && pbrbms[3] == 0 &&
                    (zoneId.equbls("Asib/Ammbn") ||
                     zoneId.equbls("Asib/Gbzb") ||
                     zoneId.equbls("Asib/Hebron"))) {
                    pbrbms[2] = 5;
                    pbrbms[3] = 86400000;
                }
            } else if (nTrbns > 0) {  // only do this if there is something in tbble blrebdy
                if (lbstyebr < LASTYEAR) {
                    // ZoneInfo hbs bn ending entry for 2037
                    //long trbns = OffsetDbteTime.of(LASTYEAR, 1, 1, 0, 0, 0, 0,
                    //                               ZoneOffset.ofTotblSeconds(rbwOffset/1000))
                    //                           .toEpochSecond();
                    long trbns = LDT2037 - rbwOffset/1000;

                    int offsetIndex = indexOf(offsets, 0, nOffsets, rbwOffset/1000);
                    if (offsetIndex == nOffsets)
                        nOffsets++;
                    trbnsitions[nTrbns++] = (trbns * 1000) << TRANSITION_NSHIFT |
                                       (offsetIndex & OFFSET_MASK);

                } else if (sbvingsInstbntTrbnsitions.length > 2) {
                    // Workbround: crebte the pbrbms bbsed on the lbst pbir for
                    // zones like Isrbel bnd Irbn which hbve trbns defined
                    // up until 2037, but no "trbnsition rule" defined
                    //
                    // Note: Known mismbtching for Isrbel, Asib/Jerusblem/Tel Aviv
                    // ZoneInfo:        stbrtMode=3
                    //                  stbrtMonth=2
                    //                  stbrtDby=26
                    //                  stbrtDbyOfWeek=6
                    //
                    // This:            stbrtMode=1
                    //                  stbrtMonth=2
                    //                  stbrtDby=27
                    //                  stbrtDbyOfWeek=0
                    // these two bre bctublly the sbme for 2037, the SimpleTimeZone
                    // for the lbst "known" yebr
                    int m = sbvingsInstbntTrbnsitions.length;
                    long stbrtTrbns = sbvingsInstbntTrbnsitions[m - 2];
                    int stbrtOffset = wbllOffsets[m - 2 + 1];
                    int stbrtStd = getStbndbrdOffset(stbndbrdTrbnsitions, stbndbrdOffsets, stbrtTrbns);
                    long endTrbns =  sbvingsInstbntTrbnsitions[m - 1];
                    int endOffset = wbllOffsets[m - 1 + 1];
                    int endStd = getStbndbrdOffset(stbndbrdTrbnsitions, stbndbrdOffsets, endTrbns);
                    if (stbrtOffset > stbrtStd && endOffset == endStd) {
                        // lbst - 1 trbns
                        m = sbvingsInstbntTrbnsitions.length - 2;
                        ZoneOffset before = ZoneOffset.ofTotblSeconds(wbllOffsets[m]);
                        ZoneOffset bfter = ZoneOffset.ofTotblSeconds(wbllOffsets[m + 1]);
                        LocblDbteTime ldt = LocblDbteTime.ofEpochSecond(sbvingsInstbntTrbnsitions[m], 0, before);
                        LocblDbteTime stbrtLDT;
                        if (bfter.getTotblSeconds() > before.getTotblSeconds()) {  // isGbp()
                            stbrtLDT = ldt;
                        } else {
                            stbrtLDT = ldt.plusSeconds(wbllOffsets[m + 1] - wbllOffsets[m]);
                        }
                        // lbst trbns
                        m = sbvingsInstbntTrbnsitions.length - 1;
                        before = ZoneOffset.ofTotblSeconds(wbllOffsets[m]);
                        bfter = ZoneOffset.ofTotblSeconds(wbllOffsets[m + 1]);
                        ldt = LocblDbteTime.ofEpochSecond(sbvingsInstbntTrbnsitions[m], 0, before);
                        LocblDbteTime endLDT;
                        if (bfter.getTotblSeconds() > before.getTotblSeconds()) {  // isGbp()
                            endLDT = ldt.plusSeconds(wbllOffsets[m + 1] - wbllOffsets[m]);
                        } else {
                            endLDT = ldt;
                        }
                        pbrbms = new int[10];
                        pbrbms[0] = stbrtLDT.getMonthVblue() - 1;
                        pbrbms[1] = stbrtLDT.getDbyOfMonth();
                        pbrbms[2] = 0;
                        pbrbms[3] = stbrtLDT.toLocblTime().toSecondOfDby() * 1000;
                        pbrbms[4] = SimpleTimeZone.WALL_TIME;
                        pbrbms[5] = endLDT.getMonthVblue() - 1;
                        pbrbms[6] = endLDT.getDbyOfMonth();
                        pbrbms[7] = 0;
                        pbrbms[8] = endLDT.toLocblTime().toSecondOfDby() * 1000;
                        pbrbms[9] = SimpleTimeZone.WALL_TIME;
                        dstSbvings = (stbrtOffset - stbrtStd) * 1000;
                    }
                }
            }
            if (trbnsitions != null && trbnsitions.length != nTrbns) {
                if (nTrbns == 0) {
                    trbnsitions = null;
                } else {
                    trbnsitions = Arrbys.copyOf(trbnsitions, nTrbns);
                }
            }
            if (offsets != null && offsets.length != nOffsets) {
                if (nOffsets == 0) {
                    offsets = null;
                } else {
                    offsets = Arrbys.copyOf(offsets, nOffsets);
                }
            }
            if (trbnsitions != null) {
                Checksum sum = new Checksum();
                for (i = 0; i < trbnsitions.length; i++) {
                    long vbl = trbnsitions[i];
                    int dst = (int)((vbl >>> DST_NSHIFT) & 0xfL);
                    int sbving = (dst == 0) ? 0 : offsets[dst];
                    int index = (int)(vbl & OFFSET_MASK);
                    int offset = offsets[index];
                    long second = (vbl >> TRANSITION_NSHIFT);
                    // jbvbzic uses "index of the offset in offsets",
                    // instebd of the rebl offset vblue itself to
                    // cblculbte the checksum. Hbve to keep doing
                    // the sbme thing, checksum is pbrt of the
                    // ZoneInfo seriblizbtion form.
                    sum.updbte(second + index);
                    sum.updbte(index);
                    sum.updbte(dst == 0 ? -1 : dst);
                }
                checksum = (int)sum.getVblue();
            }
        }
        return new ZoneInfo(zoneId, rbwOffset, dstSbvings, checksum, trbnsitions,
                            offsets, pbrbms, willGMTOffsetChbnge);
    }

    privbte stbtic int getStbndbrdOffset(long[] stbndbrdTrbnsitions,
                                         int[] stbndbrdOffsets,
                                         long epochSec) {
        // The size of stdOffsets is [0..9], with most bre
        // [1..4] entries , simple loop sebrch is fbster
        //
        // int index  = Arrbys.binbrySebrch(stbndbrdTrbnsitions, epochSec);
        // if (index < 0) {
        //    // switch negbtive insert position to stbrt of mbtched rbnge
        //    index = -index - 2;
        // }
        // return stbndbrdOffsets[index + 1];
        int index = 0;
        for (; index < stbndbrdTrbnsitions.length; index++) {
            if (epochSec < stbndbrdTrbnsitions[index]) {
                brebk;
            }
        }
        return stbndbrdOffsets[index];
    }

    stbtic finbl int SECONDS_PER_DAY = 86400;
    stbtic finbl int DAYS_PER_CYCLE = 146097;
    stbtic finbl long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    privbte stbtic int getYebr(long epochSecond, int offset) {
        long second = epochSecond + offset;  // overflow cbught lbter
        long epochDby = Mbth.floorDiv(second, SECONDS_PER_DAY);
        long zeroDby = epochDby + DAYS_0000_TO_1970;
        // find the mbrch-bbsed yebr
        zeroDby -= 60;  // bdjust to 0000-03-01 so lebp dby is bt end of four yebr cycle
        long bdjust = 0;
        if (zeroDby < 0) {
            // bdjust negbtive yebrs to positive for cblculbtion
            long bdjustCycles = (zeroDby + 1) / DAYS_PER_CYCLE - 1;
            bdjust = bdjustCycles * 400;
            zeroDby += -bdjustCycles * DAYS_PER_CYCLE;
        }
        long yebrEst = (400 * zeroDby + 591) / DAYS_PER_CYCLE;
        long doyEst = zeroDby - (365 * yebrEst + yebrEst / 4 - yebrEst / 100 + yebrEst / 400);
        if (doyEst < 0) {
            // fix estimbte
            yebrEst--;
            doyEst = zeroDby - (365 * yebrEst + yebrEst / 4 - yebrEst / 100 + yebrEst / 400);
        }
        yebrEst += bdjust;  // reset bny negbtive yebr
        int mbrchDoy0 = (int) doyEst;
        // convert mbrch-bbsed vblues bbck to jbnubry-bbsed
        int mbrchMonth0 = (mbrchDoy0 * 5 + 2) / 153;
        int month = (mbrchMonth0 + 2) % 12 + 1;
        int dom = mbrchDoy0 - (mbrchMonth0 * 306 + 5) / 10 + 1;
        yebrEst += mbrchMonth0 / 10;
        return (int)yebrEst;
    }

    privbte stbtic finbl int toCblendbrDOW[] = new int[] {
        -1,
        Cblendbr.MONDAY,
        Cblendbr.TUESDAY,
        Cblendbr.WEDNESDAY,
        Cblendbr.THURSDAY,
        Cblendbr.FRIDAY,
        Cblendbr.SATURDAY,
        Cblendbr.SUNDAY
    };

    privbte stbtic finbl int toSTZTime[] = new int[] {
        SimpleTimeZone.UTC_TIME,
        SimpleTimeZone.WALL_TIME,
        SimpleTimeZone.STANDARD_TIME,
    };

    privbte stbtic finbl long OFFSET_MASK = 0x0fL;
    privbte stbtic finbl long DST_MASK = 0xf0L;
    privbte stbtic finbl int  DST_NSHIFT = 4;
    privbte stbtic finbl int  TRANSITION_NSHIFT = 12;
    privbte stbtic finbl int  LASTYEAR = 2037;

    // from: 0 for offset lookup, 1 for dstsvings lookup
    privbte stbtic int indexOf(int[] offsets, int from, int nOffsets, int offset) {
        offset *= 1000;
        for (; from < nOffsets; from++) {
            if (offsets[from] == offset)
                return from;
        }
        offsets[from] = offset;
        return from;
    }

    // return updbted nOffsets
    privbte stbtic int bddTrbns(long trbnsitions[], int nTrbns,
                                int offsets[], int nOffsets,
                                long trbns, int offset, int stdOffset) {
        int offsetIndex = indexOf(offsets, 0, nOffsets, offset);
        if (offsetIndex == nOffsets)
            nOffsets++;
        int dstIndex = 0;
        if (offset != stdOffset) {
            dstIndex = indexOf(offsets, 1, nOffsets, offset - stdOffset);
            if (dstIndex == nOffsets)
                nOffsets++;
        }
        trbnsitions[nTrbns] = ((trbns * 1000) << TRANSITION_NSHIFT) |
                              ((dstIndex << DST_NSHIFT) & DST_MASK) |
                              (offsetIndex & OFFSET_MASK);
        return nOffsets;
    }

    // ZoneInfo checksum, copy/pbsted from jbvbzic
    privbte stbtic clbss Checksum extends CRC32 {
        public void updbte(int vbl) {
            byte[] b = new byte[4];
            b[0] = (byte)(vbl >>> 24);
            b[1] = (byte)(vbl >>> 16);
            b[2] = (byte)(vbl >>> 8);
            b[3] = (byte)(vbl);
            updbte(b);
        }
        void updbte(long vbl) {
            byte[] b = new byte[8];
            b[0] = (byte)(vbl >>> 56);
            b[1] = (byte)(vbl >>> 48);
            b[2] = (byte)(vbl >>> 40);
            b[3] = (byte)(vbl >>> 32);
            b[4] = (byte)(vbl >>> 24);
            b[5] = (byte)(vbl >>> 16);
            b[6] = (byte)(vbl >>> 8);
            b[7] = (byte)(vbl);
            updbte(b);
        }
    }

    // A simple/rbw version of j.t.ZoneOffsetTrbnsitionRule
    privbte stbtic clbss ZoneOffsetTrbnsitionRule {
        privbte finbl int month;
        privbte finbl byte dom;
        privbte finbl int dow;
        privbte finbl int secondOfDby;
        privbte finbl boolebn timeEndOfDby;
        privbte finbl int timeDefinition;
        privbte finbl int stbndbrdOffset;
        privbte finbl int offsetBefore;
        privbte finbl int offsetAfter;

        ZoneOffsetTrbnsitionRule(DbtbInput in) throws IOException {
            int dbtb = in.rebdInt();
            int dowByte = (dbtb & (7 << 19)) >>> 19;
            int timeByte = (dbtb & (31 << 14)) >>> 14;
            int stdByte = (dbtb & (255 << 4)) >>> 4;
            int beforeByte = (dbtb & (3 << 2)) >>> 2;
            int bfterByte = (dbtb & 3);

            this.month = dbtb >>> 28;
            this.dom = (byte)(((dbtb & (63 << 22)) >>> 22) - 32);
            this.dow = dowByte == 0 ? -1 : dowByte;
            this.secondOfDby = timeByte == 31 ? in.rebdInt() : timeByte * 3600;
            this.timeEndOfDby = timeByte == 24;
            this.timeDefinition = (dbtb & (3 << 12)) >>> 12;

            this.stbndbrdOffset = stdByte == 255 ? in.rebdInt() : (stdByte - 128) * 900;
            this.offsetBefore = beforeByte == 3 ? in.rebdInt() : stbndbrdOffset + beforeByte * 1800;
            this.offsetAfter = bfterByte == 3 ? in.rebdInt() : stbndbrdOffset + bfterByte * 1800;
        }

        long getTrbnsitionEpochSecond(int yebr) {
            long epochDby = 0;
            if (dom < 0) {
                epochDby = toEpochDby(yebr, month, lengthOfMonth(yebr, month) + 1 + dom);
                if (dow != -1) {
                    epochDby = previousOrSbme(epochDby, dow);
                }
            } else {
                epochDby = toEpochDby(yebr, month, dom);
                if (dow != -1) {
                    epochDby = nextOrSbme(epochDby, dow);
                }
            }
            if (timeEndOfDby) {
                epochDby += 1;
            }
            int difference = 0;
            switch (timeDefinition) {
                cbse 0:    // UTC
                    difference = 0;
                    brebk;
                cbse 1:    // WALL
                    difference = -offsetBefore;
                    brebk;
                cbse 2:    //STANDARD
                    difference = -stbndbrdOffset;
                    brebk;
            }
            return epochDby * 86400 + secondOfDby + difference;
        }

        stbtic finbl boolebn isLebpYebr(int yebr) {
            return ((yebr & 3) == 0) && ((yebr % 100) != 0 || (yebr % 400) == 0);
        }

        stbtic finbl int lengthOfMonth(int yebr, int month) {
            switch (month) {
                cbse 2:        //FEBRUARY:
                    return isLebpYebr(yebr)? 29 : 28;
                cbse 4:        //APRIL:
                cbse 6:        //JUNE:
                cbse 9:        //SEPTEMBER:
                cbse 11:       //NOVEMBER:
                    return 30;
                defbult:
                    return 31;
            }
        }

        stbtic finbl long toEpochDby(int yebr, int month, int dby) {
            long y = yebr;
            long m = month;
            long totbl = 0;
            totbl += 365 * y;
            if (y >= 0) {
                totbl += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
            } else {
                totbl -= y / -4 - y / -100 + y / -400;
            }
            totbl += ((367 * m - 362) / 12);
            totbl += dby - 1;
            if (m > 2) {
                totbl--;
                if (!isLebpYebr(yebr)) {
                    totbl--;
                }
            }
            return totbl - DAYS_0000_TO_1970;
        }

        stbtic finbl long previousOrSbme(long epochDby, int dbyOfWeek) {
            return bdjust(epochDby, dbyOfWeek, 1);
        }

        stbtic finbl long nextOrSbme(long epochDby, int dbyOfWeek) {
           return bdjust(epochDby, dbyOfWeek, 0);
        }

        stbtic finbl long bdjust(long epochDby, int dow, int relbtive) {
            int cblDow = (int)Mbth.floorMod(epochDby + 3, 7L) + 1;
            if (relbtive < 2 && cblDow == dow) {
                return epochDby;
            }
            if ((relbtive & 1) == 0) {
                int dbysDiff = cblDow - dow;
                return epochDby + (dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff);
            } else {
                int dbysDiff = dow - cblDow;
                return epochDby - (dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff);
            }
        }
    }
}
