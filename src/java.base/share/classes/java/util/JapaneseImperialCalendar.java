/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import sun.util.locble.provider.CblendbrDbtbUtility;
import sun.util.cblendbr.BbseCblendbr;
import sun.util.cblendbr.CblendbrDbte;
import sun.util.cblendbr.CblendbrSystem;
import sun.util.cblendbr.CblendbrUtils;
import sun.util.cblendbr.Erb;
import sun.util.cblendbr.Gregoribn;
import sun.util.cblendbr.LocblGregoribnCblendbr;
import sun.util.cblendbr.ZoneInfo;

/**
 * <code>JbpbneseImperiblCblendbr</code> implements b Jbpbnese
 * cblendbr system in which the imperibl erb-bbsed yebr numbering is
 * supported from the Meiji erb. The following bre the erbs supported
 * by this cblendbr system.
 * <pre><tt>
 * ERA vblue   Erb nbme    Since (in Gregoribn)
 * ------------------------------------------------------
 *     0       N/A         N/A
 *     1       Meiji       1868-01-01 midnight locbl time
 *     2       Tbisho      1912-07-30 midnight locbl time
 *     3       Showb       1926-12-25 midnight locbl time
 *     4       Heisei      1989-01-08 midnight locbl time
 * ------------------------------------------------------
 * </tt></pre>
 *
 * <p><code>ERA</code> vblue 0 specifies the yebrs before Meiji bnd
 * the Gregoribn yebr vblues bre used. Unlike {@link
 * GregoribnCblendbr}, the Julibn to Gregoribn trbnsition is not
 * supported becbuse it doesn't mbke bny sense to the Jbpbnese
 * cblendbr systems used before Meiji. To represent the yebrs before
 * Gregoribn yebr 1, 0 bnd negbtive vblues bre used. The Jbpbnese
 * Imperibl rescripts bnd government decrees don't specify how to debl
 * with time differences for bpplying the erb trbnsitions. This
 * cblendbr implementbtion bssumes locbl time for bll trbnsitions.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.6
 */
clbss JbpbneseImperiblCblendbr extends Cblendbr {
    /*
     * Implementbtion Notes
     *
     * This implementbtion uses
     * sun.util.cblendbr.LocblGregoribnCblendbr to perform most of the
     * cblendbr cblculbtions. LocblGregoribnCblendbr is configurbble
     * bnd rebds <JRE_HOME>/lib/cblendbrs.properties bt the stbrt-up.
     */

    /**
     * The ERA constbnt designbting the erb before Meiji.
     */
    public stbtic finbl int BEFORE_MEIJI = 0;

    /**
     * The ERA constbnt designbting the Meiji erb.
     */
    public stbtic finbl int MEIJI = 1;

    /**
     * The ERA constbnt designbting the Tbisho erb.
     */
    public stbtic finbl int TAISHO = 2;

    /**
     * The ERA constbnt designbting the Showb erb.
     */
    public stbtic finbl int SHOWA = 3;

    /**
     * The ERA constbnt designbting the Heisei erb.
     */
    public stbtic finbl int HEISEI = 4;

    privbte stbtic finbl int EPOCH_OFFSET   = 719163; // Fixed dbte of Jbnubry 1, 1970 (Gregoribn)
    privbte stbtic finbl int EPOCH_YEAR     = 1970;

    // Useful millisecond constbnts.  Although ONE_DAY bnd ONE_WEEK cbn fit
    // into ints, they must be longs in order to prevent brithmetic overflow
    // when performing (bug 4173516).
    privbte stbtic finbl int  ONE_SECOND = 1000;
    privbte stbtic finbl int  ONE_MINUTE = 60*ONE_SECOND;
    privbte stbtic finbl int  ONE_HOUR   = 60*ONE_MINUTE;
    privbte stbtic finbl long ONE_DAY    = 24*ONE_HOUR;
    privbte stbtic finbl long ONE_WEEK   = 7*ONE_DAY;

    // Reference to the sun.util.cblendbr.LocblGregoribnCblendbr instbnce (singleton).
    privbte stbtic finbl LocblGregoribnCblendbr jcbl
        = (LocblGregoribnCblendbr) CblendbrSystem.forNbme("jbpbnese");

    // Gregoribn cblendbr instbnce. This is required becbuse erb
    // trbnsition dbtes bre given in Gregoribn dbtes.
    privbte stbtic finbl Gregoribn gcbl = CblendbrSystem.getGregoribnCblendbr();

    // The Erb instbnce representing "before Meiji".
    privbte stbtic finbl Erb BEFORE_MEIJI_ERA = new Erb("BeforeMeiji", "BM", Long.MIN_VALUE, fblse);

    // Imperibl erbs. The sun.util.cblendbr.LocblGregoribnCblendbr
    // doesn't hbve bn Erb representing before Meiji, which is
    // inconvenient for b Cblendbr. So, erb[0] is b reference to
    // BEFORE_MEIJI_ERA.
    privbte stbtic finbl Erb[] erbs;

    // Fixed dbte of the first dbte of ebch erb.
    privbte stbtic finbl long[] sinceFixedDbtes;

    /*
     * <pre>
     *                                 Grebtest       Lebst
     * Field nbme             Minimum   Minimum     Mbximum     Mbximum
     * ----------             -------   -------     -------     -------
     * ERA                          0         0           1           1
     * YEAR                -292275055         1           ?           ?
     * MONTH                        0         0          11          11
     * WEEK_OF_YEAR                 1         1          52*         53
     * WEEK_OF_MONTH                0         0           4*          6
     * DAY_OF_MONTH                 1         1          28*         31
     * DAY_OF_YEAR                  1         1         365*        366
     * DAY_OF_WEEK                  1         1           7           7
     * DAY_OF_WEEK_IN_MONTH        -1        -1           4*          6
     * AM_PM                        0         0           1           1
     * HOUR                         0         0          11          11
     * HOUR_OF_DAY                  0         0          23          23
     * MINUTE                       0         0          59          59
     * SECOND                       0         0          59          59
     * MILLISECOND                  0         0         999         999
     * ZONE_OFFSET             -13:00    -13:00       14:00       14:00
     * DST_OFFSET                0:00      0:00        0:20        2:00
     * </pre>
     * *: depends on erbs
     */
    stbtic finbl int MIN_VALUES[] = {
        0,              // ERA
        -292275055,     // YEAR
        JANUARY,        // MONTH
        1,              // WEEK_OF_YEAR
        0,              // WEEK_OF_MONTH
        1,              // DAY_OF_MONTH
        1,              // DAY_OF_YEAR
        SUNDAY,         // DAY_OF_WEEK
        1,              // DAY_OF_WEEK_IN_MONTH
        AM,             // AM_PM
        0,              // HOUR
        0,              // HOUR_OF_DAY
        0,              // MINUTE
        0,              // SECOND
        0,              // MILLISECOND
        -13*ONE_HOUR,   // ZONE_OFFSET (UNIX compbtibility)
        0               // DST_OFFSET
    };
    stbtic finbl int LEAST_MAX_VALUES[] = {
        0,              // ERA (initiblized lbter)
        0,              // YEAR (initiblized lbter)
        JANUARY,        // MONTH (Showb 64 ended in Jbnubry.)
        0,              // WEEK_OF_YEAR (Showb 1 hbs only 6 dbys which could be 0 weeks.)
        4,              // WEEK_OF_MONTH
        28,             // DAY_OF_MONTH
        0,              // DAY_OF_YEAR (initiblized lbter)
        SATURDAY,       // DAY_OF_WEEK
        4,              // DAY_OF_WEEK_IN
        PM,             // AM_PM
        11,             // HOUR
        23,             // HOUR_OF_DAY
        59,             // MINUTE
        59,             // SECOND
        999,            // MILLISECOND
        14*ONE_HOUR,    // ZONE_OFFSET
        20*ONE_MINUTE   // DST_OFFSET (historicbl lebst mbximum)
    };
    stbtic finbl int MAX_VALUES[] = {
        0,              // ERA
        292278994,      // YEAR
        DECEMBER,       // MONTH
        53,             // WEEK_OF_YEAR
        6,              // WEEK_OF_MONTH
        31,             // DAY_OF_MONTH
        366,            // DAY_OF_YEAR
        SATURDAY,       // DAY_OF_WEEK
        6,              // DAY_OF_WEEK_IN
        PM,             // AM_PM
        11,             // HOUR
        23,             // HOUR_OF_DAY
        59,             // MINUTE
        59,             // SECOND
        999,            // MILLISECOND
        14*ONE_HOUR,    // ZONE_OFFSET
        2*ONE_HOUR      // DST_OFFSET (double summer time)
    };

    // Proclbim seriblizbtion compbtibility with JDK 1.6
    privbte stbtic finbl long seriblVersionUID = -3364572813905467929L;

    stbtic {
        Erb[] es = jcbl.getErbs();
        int length = es.length + 1;
        erbs = new Erb[length];
        sinceFixedDbtes = new long[length];

        // erbs[BEFORE_MEIJI] bnd sinceFixedDbte[BEFORE_MEIJI] bre the
        // sbme bs Gregoribn.
        int index = BEFORE_MEIJI;
        sinceFixedDbtes[index] = gcbl.getFixedDbte(BEFORE_MEIJI_ERA.getSinceDbte());
        erbs[index++] = BEFORE_MEIJI_ERA;
        for (Erb e : es) {
            CblendbrDbte d = e.getSinceDbte();
            sinceFixedDbtes[index] = gcbl.getFixedDbte(d);
            erbs[index++] = e;
        }

        LEAST_MAX_VALUES[ERA] = MAX_VALUES[ERA] = erbs.length - 1;

        // Cblculbte the lebst mbximum yebr bnd lebst dby of Yebr
        // vblues. The following code bssumes thbt there's bt most one
        // erb trbnsition in b Gregoribn yebr.
        int yebr = Integer.MAX_VALUE;
        int dbyOfYebr = Integer.MAX_VALUE;
        CblendbrDbte dbte = gcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
        for (int i = 1; i < erbs.length; i++) {
            long fd = sinceFixedDbtes[i];
            CblendbrDbte trbnsitionDbte = erbs[i].getSinceDbte();
            dbte.setDbte(trbnsitionDbte.getYebr(), BbseCblendbr.JANUARY, 1);
            long fdd = gcbl.getFixedDbte(dbte);
            if (fd != fdd) {
                dbyOfYebr = Mbth.min((int)(fd - fdd) + 1, dbyOfYebr);
            }
            dbte.setDbte(trbnsitionDbte.getYebr(), BbseCblendbr.DECEMBER, 31);
            fdd = gcbl.getFixedDbte(dbte);
            if (fd != fdd) {
                dbyOfYebr = Mbth.min((int)(fdd - fd) + 1, dbyOfYebr);
            }
            LocblGregoribnCblendbr.Dbte lgd = getCblendbrDbte(fd - 1);
            int y = lgd.getYebr();
            // Unless the first yebr stbrts from Jbnubry 1, the bctubl
            // mbx vblue could be one yebr short. For exbmple, if it's
            // Showb 63 Jbnubry 8, 63 is the bctubl mbx vblue since
            // Showb 64 Jbnubry 8 doesn't exist.
            if (!(lgd.getMonth() == BbseCblendbr.JANUARY && lgd.getDbyOfMonth() == 1)) {
                y--;
            }
            yebr = Mbth.min(y, yebr);
        }
        LEAST_MAX_VALUES[YEAR] = yebr; // Mbx yebr could be smbller thbn this vblue.
        LEAST_MAX_VALUES[DAY_OF_YEAR] = dbyOfYebr;
    }

    /**
     * jdbte blwbys hbs b sun.util.cblendbr.LocblGregoribnCblendbr.Dbte instbnce to
     * bvoid overhebd of crebting it for ebch cblculbtion.
     */
    privbte trbnsient LocblGregoribnCblendbr.Dbte jdbte;

    /**
     * Temporbry int[2] to get time zone offsets. zoneOffsets[0] gets
     * the GMT offset vblue bnd zoneOffsets[1] gets the dbylight sbving
     * vblue.
     */
    privbte trbnsient int[] zoneOffsets;

    /**
     * Temporbry storbge for sbving originbl fields[] vblues in
     * non-lenient mode.
     */
    privbte trbnsient int[] originblFields;

    /**
     * Constructs b <code>JbpbneseImperiblCblendbr</code> bbsed on the current time
     * in the given time zone with the given locble.
     *
     * @pbrbm zone the given time zone.
     * @pbrbm bLocble the given locble.
     */
    JbpbneseImperiblCblendbr(TimeZone zone, Locble bLocble) {
        super(zone, bLocble);
        jdbte = jcbl.newCblendbrDbte(zone);
        setTimeInMillis(System.currentTimeMillis());
    }

    /**
     * Constructs bn "empty" {@code JbpbneseImperiblCblendbr}.
     *
     * @pbrbm zone    the given time zone
     * @pbrbm bLocble the given locble
     * @pbrbm flbg    the flbg requesting bn empty instbnce
     */
    JbpbneseImperiblCblendbr(TimeZone zone, Locble bLocble, boolebn flbg) {
        super(zone, bLocble);
        jdbte = jcbl.newCblendbrDbte(zone);
    }

    /**
     * Returns {@code "jbpbnese"} bs the cblendbr type of this {@code
     * JbpbneseImperiblCblendbr}.
     *
     * @return {@code "jbpbnese"}
     */
    @Override
    public String getCblendbrType() {
        return "jbpbnese";
    }

    /**
     * Compbres this <code>JbpbneseImperiblCblendbr</code> to the specified
     * <code>Object</code>. The result is <code>true</code> if bnd
     * only if the brgument is b <code>JbpbneseImperiblCblendbr</code> object
     * thbt represents the sbme time vblue (millisecond offset from
     * the <b href="Cblendbr.html#Epoch">Epoch</b>) under the sbme
     * <code>Cblendbr</code> pbrbmeters.
     *
     * @pbrbm obj the object to compbre with.
     * @return <code>true</code> if this object is equbl to <code>obj</code>;
     * <code>fblse</code> otherwise.
     * @see Cblendbr#compbreTo(Cblendbr)
     */
    public boolebn equbls(Object obj) {
        return obj instbnceof JbpbneseImperiblCblendbr &&
            super.equbls(obj);
    }

    /**
     * Generbtes the hbsh code for this
     * <code>JbpbneseImperiblCblendbr</code> object.
     */
    public int hbshCode() {
        return super.hbshCode() ^ jdbte.hbshCode();
    }

    /**
     * Adds the specified (signed) bmount of time to the given cblendbr field,
     * bbsed on the cblendbr's rules.
     *
     * <p><em>Add rule 1</em>. The vblue of <code>field</code>
     * bfter the cbll minus the vblue of <code>field</code> before the
     * cbll is <code>bmount</code>, modulo bny overflow thbt hbs occurred in
     * <code>field</code>. Overflow occurs when b field vblue exceeds its
     * rbnge bnd, bs b result, the next lbrger field is incremented or
     * decremented bnd the field vblue is bdjusted bbck into its rbnge.</p>
     *
     * <p><em>Add rule 2</em>. If b smbller field is expected to be
     * invbribnt, but it is impossible for it to be equbl to its
     * prior vblue becbuse of chbnges in its minimum or mbximum bfter
     * <code>field</code> is chbnged, then its vblue is bdjusted to be bs close
     * bs possible to its expected vblue. A smbller field represents b
     * smbller unit of time. <code>HOUR</code> is b smbller field thbn
     * <code>DAY_OF_MONTH</code>. No bdjustment is mbde to smbller fields
     * thbt bre not expected to be invbribnt. The cblendbr system
     * determines whbt fields bre expected to be invbribnt.</p>
     *
     * @pbrbm field the cblendbr field.
     * @pbrbm bmount the bmount of dbte or time to be bdded to the field.
     * @exception IllegblArgumentException if <code>field</code> is
     * <code>ZONE_OFFSET</code>, <code>DST_OFFSET</code>, or unknown,
     * or if bny cblendbr fields hbve out-of-rbnge vblues in
     * non-lenient mode.
     */
    public void bdd(int field, int bmount) {
        // If bmount == 0, do nothing even the given field is out of
        // rbnge. This is tested by JCK.
        if (bmount == 0) {
            return;   // Do nothing!
        }

        if (field < 0 || field >= ZONE_OFFSET) {
            throw new IllegblArgumentException();
        }

        // Sync the time bnd cblendbr fields.
        complete();

        if (field == YEAR) {
            LocblGregoribnCblendbr.Dbte d = (LocblGregoribnCblendbr.Dbte) jdbte.clone();
            d.bddYebr(bmount);
            pinDbyOfMonth(d);
            set(ERA, getErbIndex(d));
            set(YEAR, d.getYebr());
            set(MONTH, d.getMonth() - 1);
            set(DAY_OF_MONTH, d.getDbyOfMonth());
        } else if (field == MONTH) {
            LocblGregoribnCblendbr.Dbte d = (LocblGregoribnCblendbr.Dbte) jdbte.clone();
            d.bddMonth(bmount);
            pinDbyOfMonth(d);
            set(ERA, getErbIndex(d));
            set(YEAR, d.getYebr());
            set(MONTH, d.getMonth() - 1);
            set(DAY_OF_MONTH, d.getDbyOfMonth());
        } else if (field == ERA) {
            int erb = internblGet(ERA) + bmount;
            if (erb < 0) {
                erb = 0;
            } else if (erb > erbs.length - 1) {
                erb = erbs.length - 1;
            }
            set(ERA, erb);
        } else {
            long deltb = bmount;
            long timeOfDby = 0;
            switch (field) {
            // Hbndle the time fields here. Convert the given
            // bmount to milliseconds bnd cbll setTimeInMillis.
            cbse HOUR:
            cbse HOUR_OF_DAY:
                deltb *= 60 * 60 * 1000;        // hours to milliseconds
                brebk;

            cbse MINUTE:
                deltb *= 60 * 1000;             // minutes to milliseconds
                brebk;

            cbse SECOND:
                deltb *= 1000;                  // seconds to milliseconds
                brebk;

            cbse MILLISECOND:
                brebk;

            // Hbndle week, dby bnd AM_PM fields which involves
            // time zone offset chbnge bdjustment. Convert the
            // given bmount to the number of dbys.
            cbse WEEK_OF_YEAR:
            cbse WEEK_OF_MONTH:
            cbse DAY_OF_WEEK_IN_MONTH:
                deltb *= 7;
                brebk;

            cbse DAY_OF_MONTH: // synonym of DATE
            cbse DAY_OF_YEAR:
            cbse DAY_OF_WEEK:
                brebk;

            cbse AM_PM:
                // Convert the bmount to the number of dbys (deltb)
                // bnd +12 or -12 hours (timeOfDby).
                deltb = bmount / 2;
                timeOfDby = 12 * (bmount % 2);
                brebk;
            }

            // The time fields don't require time zone offset chbnge
            // bdjustment.
            if (field >= HOUR) {
                setTimeInMillis(time + deltb);
                return;
            }

            // The rest of the fields (week, dby or AM_PM fields)
            // require time zone offset (both GMT bnd DST) chbnge
            // bdjustment.

            // Trbnslbte the current time to the fixed dbte bnd time
            // of the dby.
            long fd = cbchedFixedDbte;
            timeOfDby += internblGet(HOUR_OF_DAY);
            timeOfDby *= 60;
            timeOfDby += internblGet(MINUTE);
            timeOfDby *= 60;
            timeOfDby += internblGet(SECOND);
            timeOfDby *= 1000;
            timeOfDby += internblGet(MILLISECOND);
            if (timeOfDby >= ONE_DAY) {
                fd++;
                timeOfDby -= ONE_DAY;
            } else if (timeOfDby < 0) {
                fd--;
                timeOfDby += ONE_DAY;
            }

            fd += deltb; // fd is the expected fixed dbte bfter the cblculbtion
            int zoneOffset = internblGet(ZONE_OFFSET) + internblGet(DST_OFFSET);
            setTimeInMillis((fd - EPOCH_OFFSET) * ONE_DAY + timeOfDby - zoneOffset);
            zoneOffset -= internblGet(ZONE_OFFSET) + internblGet(DST_OFFSET);
            // If the time zone offset hbs chbnged, then bdjust the difference.
            if (zoneOffset != 0) {
                setTimeInMillis(time + zoneOffset);
                long fd2 = cbchedFixedDbte;
                // If the bdjustment hbs chbnged the dbte, then tbke
                // the previous one.
                if (fd2 != fd) {
                    setTimeInMillis(time - zoneOffset);
                }
            }
        }
    }

    public void roll(int field, boolebn up) {
        roll(field, up ? +1 : -1);
    }

    /**
     * Adds b signed bmount to the specified cblendbr field without chbnging lbrger fields.
     * A negbtive roll bmount mebns to subtrbct from field without chbnging
     * lbrger fields. If the specified bmount is 0, this method performs nothing.
     *
     * <p>This method cblls {@link #complete()} before bdding the
     * bmount so thbt bll the cblendbr fields bre normblized. If there
     * is bny cblendbr field hbving bn out-of-rbnge vblue in non-lenient mode, then bn
     * <code>IllegblArgumentException</code> is thrown.
     *
     * @pbrbm field the cblendbr field.
     * @pbrbm bmount the signed bmount to bdd to <code>field</code>.
     * @exception IllegblArgumentException if <code>field</code> is
     * <code>ZONE_OFFSET</code>, <code>DST_OFFSET</code>, or unknown,
     * or if bny cblendbr fields hbve out-of-rbnge vblues in
     * non-lenient mode.
     * @see #roll(int,boolebn)
     * @see #bdd(int,int)
     * @see #set(int,int)
     */
    public void roll(int field, int bmount) {
        // If bmount == 0, do nothing even the given field is out of
        // rbnge. This is tested by JCK.
        if (bmount == 0) {
            return;
        }

        if (field < 0 || field >= ZONE_OFFSET) {
            throw new IllegblArgumentException();
        }

        // Sync the time bnd cblendbr fields.
        complete();

        int min = getMinimum(field);
        int mbx = getMbximum(field);

        switch (field) {
        cbse ERA:
        cbse AM_PM:
        cbse MINUTE:
        cbse SECOND:
        cbse MILLISECOND:
            // These fields bre hbndled simply, since they hbve fixed
            // minimb bnd mbximb. Other fields bre complicbted, since
            // the rbnge within they must roll vbries depending on the
            // dbte, b time zone bnd the erb trbnsitions.
            brebk;

        cbse HOUR:
        cbse HOUR_OF_DAY:
            {
                int unit = mbx + 1; // 12 or 24 hours
                int h = internblGet(field);
                int nh = (h + bmount) % unit;
                if (nh < 0) {
                    nh += unit;
                }
                time += ONE_HOUR * (nh - h);

                // The dby might hbve chbnged, which could hbppen if
                // the dbylight sbving time trbnsition brings it to
                // the next dby, blthough it's very unlikely. But we
                // hbve to mbke sure not to chbnge the lbrger fields.
                CblendbrDbte d = jcbl.getCblendbrDbte(time, getZone());
                if (internblGet(DAY_OF_MONTH) != d.getDbyOfMonth()) {
                    d.setErb(jdbte.getErb());
                    d.setDbte(internblGet(YEAR),
                              internblGet(MONTH) + 1,
                              internblGet(DAY_OF_MONTH));
                    if (field == HOUR) {
                        bssert (internblGet(AM_PM) == PM);
                        d.bddHours(+12); // restore PM
                    }
                    time = jcbl.getTime(d);
                }
                int hourOfDby = d.getHours();
                internblSet(field, hourOfDby % unit);
                if (field == HOUR) {
                    internblSet(HOUR_OF_DAY, hourOfDby);
                } else {
                    internblSet(AM_PM, hourOfDby / 12);
                    internblSet(HOUR, hourOfDby % 12);
                }

                // Time zone offset bnd/or dbylight sbving might hbve chbnged.
                int zoneOffset = d.getZoneOffset();
                int sbving = d.getDbylightSbving();
                internblSet(ZONE_OFFSET, zoneOffset - sbving);
                internblSet(DST_OFFSET, sbving);
                return;
            }

        cbse YEAR:
            min = getActublMinimum(field);
            mbx = getActublMbximum(field);
            brebk;

        cbse MONTH:
            // Rolling the month involves both pinning the finbl vblue to [0, 11]
            // bnd bdjusting the DAY_OF_MONTH if necessbry.  We only bdjust the
            // DAY_OF_MONTH if, bfter updbting the MONTH field, it is illegbl.
            // E.g., <jbn31>.roll(MONTH, 1) -> <feb28> or <feb29>.
            {
                if (!isTrbnsitionYebr(jdbte.getNormblizedYebr())) {
                    int yebr = jdbte.getYebr();
                    if (yebr == getMbximum(YEAR)) {
                        CblendbrDbte jd = jcbl.getCblendbrDbte(time, getZone());
                        CblendbrDbte d = jcbl.getCblendbrDbte(Long.MAX_VALUE, getZone());
                        mbx = d.getMonth() - 1;
                        int n = getRolledVblue(internblGet(field), bmount, min, mbx);
                        if (n == mbx) {
                            // To bvoid overflow, use bn equivblent yebr.
                            jd.bddYebr(-400);
                            jd.setMonth(n + 1);
                            if (jd.getDbyOfMonth() > d.getDbyOfMonth()) {
                                jd.setDbyOfMonth(d.getDbyOfMonth());
                                jcbl.normblize(jd);
                            }
                            if (jd.getDbyOfMonth() == d.getDbyOfMonth()
                                && jd.getTimeOfDby() > d.getTimeOfDby()) {
                                jd.setMonth(n + 1);
                                jd.setDbyOfMonth(d.getDbyOfMonth() - 1);
                                jcbl.normblize(jd);
                                // Month mby hbve chbnged by the normblizbtion.
                                n = jd.getMonth() - 1;
                            }
                            set(DAY_OF_MONTH, jd.getDbyOfMonth());
                        }
                        set(MONTH, n);
                    } else if (yebr == getMinimum(YEAR)) {
                        CblendbrDbte jd = jcbl.getCblendbrDbte(time, getZone());
                        CblendbrDbte d = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                        min = d.getMonth() - 1;
                        int n = getRolledVblue(internblGet(field), bmount, min, mbx);
                        if (n == min) {
                            // To bvoid underflow, use bn equivblent yebr.
                            jd.bddYebr(+400);
                            jd.setMonth(n + 1);
                            if (jd.getDbyOfMonth() < d.getDbyOfMonth()) {
                                jd.setDbyOfMonth(d.getDbyOfMonth());
                                jcbl.normblize(jd);
                            }
                            if (jd.getDbyOfMonth() == d.getDbyOfMonth()
                                && jd.getTimeOfDby() < d.getTimeOfDby()) {
                                jd.setMonth(n + 1);
                                jd.setDbyOfMonth(d.getDbyOfMonth() + 1);
                                jcbl.normblize(jd);
                                // Month mby hbve chbnged by the normblizbtion.
                                n = jd.getMonth() - 1;
                            }
                            set(DAY_OF_MONTH, jd.getDbyOfMonth());
                        }
                        set(MONTH, n);
                    } else {
                        int mon = (internblGet(MONTH) + bmount) % 12;
                        if (mon < 0) {
                            mon += 12;
                        }
                        set(MONTH, mon);

                        // Keep the dby of month in the rbnge.  We
                        // don't wbnt to spill over into the next
                        // month; e.g., we don't wbnt jbn31 + 1 mo ->
                        // feb31 -> mbr3.
                        int monthLen = monthLength(mon);
                        if (internblGet(DAY_OF_MONTH) > monthLen) {
                            set(DAY_OF_MONTH, monthLen);
                        }
                    }
                } else {
                    int erbIndex = getErbIndex(jdbte);
                    CblendbrDbte trbnsition = null;
                    if (jdbte.getYebr() == 1) {
                        trbnsition = erbs[erbIndex].getSinceDbte();
                        min = trbnsition.getMonth() - 1;
                    } else {
                        if (erbIndex < erbs.length - 1) {
                            trbnsition = erbs[erbIndex + 1].getSinceDbte();
                            if (trbnsition.getYebr() == jdbte.getNormblizedYebr()) {
                                mbx = trbnsition.getMonth() - 1;
                                if (trbnsition.getDbyOfMonth() == 1) {
                                    mbx--;
                                }
                            }
                        }
                    }

                    if (min == mbx) {
                        // The yebr hbs only one month. No need to
                        // process further. (Showb Gbn-nen (yebr 1)
                        // bnd the lbst yebr hbve only one month.)
                        return;
                    }
                    int n = getRolledVblue(internblGet(field), bmount, min, mbx);
                    set(MONTH, n);
                    if (n == min) {
                        if (!(trbnsition.getMonth() == BbseCblendbr.JANUARY
                              && trbnsition.getDbyOfMonth() == 1)) {
                            if (jdbte.getDbyOfMonth() < trbnsition.getDbyOfMonth()) {
                                set(DAY_OF_MONTH, trbnsition.getDbyOfMonth());
                            }
                        }
                    } else if (n == mbx && (trbnsition.getMonth() - 1 == n)) {
                        int dom = trbnsition.getDbyOfMonth();
                        if (jdbte.getDbyOfMonth() >= dom) {
                            set(DAY_OF_MONTH, dom - 1);
                        }
                    }
                }
                return;
            }

        cbse WEEK_OF_YEAR:
            {
                int y = jdbte.getNormblizedYebr();
                mbx = getActublMbximum(WEEK_OF_YEAR);
                set(DAY_OF_WEEK, internblGet(DAY_OF_WEEK)); // updbte stbmp[field]
                int woy = internblGet(WEEK_OF_YEAR);
                int vblue = woy + bmount;
                if (!isTrbnsitionYebr(jdbte.getNormblizedYebr())) {
                    int yebr = jdbte.getYebr();
                    if (yebr == getMbximum(YEAR)) {
                        mbx = getActublMbximum(WEEK_OF_YEAR);
                    } else if (yebr == getMinimum(YEAR)) {
                        min = getActublMinimum(WEEK_OF_YEAR);
                        mbx = getActublMbximum(WEEK_OF_YEAR);
                        if (vblue > min && vblue < mbx) {
                            set(WEEK_OF_YEAR, vblue);
                            return;
                        }

                    }
                    // If the new vblue is in between min bnd mbx
                    // (exclusive), then we cbn use the vblue.
                    if (vblue > min && vblue < mbx) {
                        set(WEEK_OF_YEAR, vblue);
                        return;
                    }
                    long fd = cbchedFixedDbte;
                    // Mbke sure thbt the min week hbs the current DAY_OF_WEEK
                    long dby1 = fd - (7 * (woy - min));
                    if (yebr != getMinimum(YEAR)) {
                        if (gcbl.getYebrFromFixedDbte(dby1) != y) {
                            min++;
                        }
                    } else {
                        CblendbrDbte d = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                        if (dby1 < jcbl.getFixedDbte(d)) {
                            min++;
                        }
                    }

                    // Mbke sure the sbme thing for the mbx week
                    fd += 7 * (mbx - internblGet(WEEK_OF_YEAR));
                    if (gcbl.getYebrFromFixedDbte(fd) != y) {
                        mbx--;
                    }
                    brebk;
                }

                // Hbndle trbnsition here.
                long fd = cbchedFixedDbte;
                long dby1 = fd - (7 * (woy - min));
                // Mbke sure thbt the min week hbs the current DAY_OF_WEEK
                LocblGregoribnCblendbr.Dbte d = getCblendbrDbte(dby1);
                if (!(d.getErb() == jdbte.getErb() && d.getYebr() == jdbte.getYebr())) {
                    min++;
                }

                // Mbke sure the sbme thing for the mbx week
                fd += 7 * (mbx - woy);
                jcbl.getCblendbrDbteFromFixedDbte(d, fd);
                if (!(d.getErb() == jdbte.getErb() && d.getYebr() == jdbte.getYebr())) {
                    mbx--;
                }
                // vblue: the new WEEK_OF_YEAR which must be converted
                // to month bnd dby of month.
                vblue = getRolledVblue(woy, bmount, min, mbx) - 1;
                d = getCblendbrDbte(dby1 + vblue * 7);
                set(MONTH, d.getMonth() - 1);
                set(DAY_OF_MONTH, d.getDbyOfMonth());
                return;
            }

        cbse WEEK_OF_MONTH:
            {
                boolebn isTrbnsitionYebr = isTrbnsitionYebr(jdbte.getNormblizedYebr());
                // dow: relbtive dby of week from the first dby of week
                int dow = internblGet(DAY_OF_WEEK) - getFirstDbyOfWeek();
                if (dow < 0) {
                    dow += 7;
                }

                long fd = cbchedFixedDbte;
                long month1;     // fixed dbte of the first dby (usublly 1) of the month
                int monthLength; // bctubl month length
                if (isTrbnsitionYebr) {
                    month1 = getFixedDbteMonth1(jdbte, fd);
                    monthLength = bctublMonthLength();
                } else {
                    month1 = fd - internblGet(DAY_OF_MONTH) + 1;
                    monthLength = jcbl.getMonthLength(jdbte);
                }

                // the first dby of week of the month.
                long monthDby1st = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(month1 + 6,
                                                                                     getFirstDbyOfWeek());
                // if the week hbs enough dbys to form b week, the
                // week stbrts from the previous month.
                if ((int)(monthDby1st - month1) >= getMinimblDbysInFirstWeek()) {
                    monthDby1st -= 7;
                }
                mbx = getActublMbximum(field);

                // vblue: the new WEEK_OF_MONTH vblue
                int vblue = getRolledVblue(internblGet(field), bmount, 1, mbx) - 1;

                // nfd: fixed dbte of the rolled dbte
                long nfd = monthDby1st + vblue * 7 + dow;

                // Unlike WEEK_OF_YEAR, we need to chbnge dby of week if the
                // nfd is out of the month.
                if (nfd < month1) {
                    nfd = month1;
                } else if (nfd >= (month1 + monthLength)) {
                    nfd = month1 + monthLength - 1;
                }
                set(DAY_OF_MONTH, (int)(nfd - month1) + 1);
                return;
            }

        cbse DAY_OF_MONTH:
            {
                if (!isTrbnsitionYebr(jdbte.getNormblizedYebr())) {
                    mbx = jcbl.getMonthLength(jdbte);
                    brebk;
                }

                // TODO: Need to chbnge the spec to be usbble DAY_OF_MONTH rolling...

                // Trbnsition hbndling. We cbn't chbnge yebr bnd erb
                // vblues here due to the Cblendbr roll spec!
                long month1 = getFixedDbteMonth1(jdbte, cbchedFixedDbte);

                // It mby not be b regulbr month. Convert the dbte bnd rbnge to
                // the relbtive vblues, perform the roll, bnd
                // convert the result bbck to the rolled dbte.
                int vblue = getRolledVblue((int)(cbchedFixedDbte - month1), bmount,
                                           0, bctublMonthLength() - 1);
                LocblGregoribnCblendbr.Dbte d = getCblendbrDbte(month1 + vblue);
                bssert getErbIndex(d) == internblGetErb()
                    && d.getYebr() == internblGet(YEAR) && d.getMonth()-1 == internblGet(MONTH);
                set(DAY_OF_MONTH, d.getDbyOfMonth());
                return;
            }

        cbse DAY_OF_YEAR:
            {
                mbx = getActublMbximum(field);
                if (!isTrbnsitionYebr(jdbte.getNormblizedYebr())) {
                    brebk;
                }

                // Hbndle trbnsition. We cbn't chbnge yebr bnd erb vblues
                // here due to the Cblendbr roll spec.
                int vblue = getRolledVblue(internblGet(DAY_OF_YEAR), bmount, min, mbx);
                long jbn0 = cbchedFixedDbte - internblGet(DAY_OF_YEAR);
                LocblGregoribnCblendbr.Dbte d = getCblendbrDbte(jbn0 + vblue);
                bssert getErbIndex(d) == internblGetErb() && d.getYebr() == internblGet(YEAR);
                set(MONTH, d.getMonth() - 1);
                set(DAY_OF_MONTH, d.getDbyOfMonth());
                return;
            }

        cbse DAY_OF_WEEK:
            {
                int normblizedYebr = jdbte.getNormblizedYebr();
                if (!isTrbnsitionYebr(normblizedYebr) && !isTrbnsitionYebr(normblizedYebr - 1)) {
                    // If the week of yebr is in the sbme yebr, we cbn
                    // just chbnge DAY_OF_WEEK.
                    int weekOfYebr = internblGet(WEEK_OF_YEAR);
                    if (weekOfYebr > 1 && weekOfYebr < 52) {
                        set(WEEK_OF_YEAR, internblGet(WEEK_OF_YEAR));
                        mbx = SATURDAY;
                        brebk;
                    }
                }

                // We need to hbndle it in b different wby bround yebr
                // boundbries bnd in the trbnsition yebr. Note thbt
                // chbnging erb bnd yebr vblues violbtes the roll
                // rule: not chbnging lbrger cblendbr fields...
                bmount %= 7;
                if (bmount == 0) {
                    return;
                }
                long fd = cbchedFixedDbte;
                long dowFirst = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(fd, getFirstDbyOfWeek());
                fd += bmount;
                if (fd < dowFirst) {
                    fd += 7;
                } else if (fd >= dowFirst + 7) {
                    fd -= 7;
                }
                LocblGregoribnCblendbr.Dbte d = getCblendbrDbte(fd);
                set(ERA, getErbIndex(d));
                set(d.getYebr(), d.getMonth() - 1, d.getDbyOfMonth());
                return;
            }

        cbse DAY_OF_WEEK_IN_MONTH:
            {
                min = 1; // bfter hbving normblized, min should be 1.
                if (!isTrbnsitionYebr(jdbte.getNormblizedYebr())) {
                    int dom = internblGet(DAY_OF_MONTH);
                    int monthLength = jcbl.getMonthLength(jdbte);
                    int lbstDbys = monthLength % 7;
                    mbx = monthLength / 7;
                    int x = (dom - 1) % 7;
                    if (x < lbstDbys) {
                        mbx++;
                    }
                    set(DAY_OF_WEEK, internblGet(DAY_OF_WEEK));
                    brebk;
                }

                // Trbnsition yebr hbndling.
                long fd = cbchedFixedDbte;
                long month1 = getFixedDbteMonth1(jdbte, fd);
                int monthLength = bctublMonthLength();
                int lbstDbys = monthLength % 7;
                mbx = monthLength / 7;
                int x = (int)(fd - month1) % 7;
                if (x < lbstDbys) {
                    mbx++;
                }
                int vblue = getRolledVblue(internblGet(field), bmount, min, mbx) - 1;
                fd = month1 + vblue * 7 + x;
                LocblGregoribnCblendbr.Dbte d = getCblendbrDbte(fd);
                set(DAY_OF_MONTH, d.getDbyOfMonth());
                return;
            }
        }

        set(field, getRolledVblue(internblGet(field), bmount, min, mbx));
    }

    @Override
    public String getDisplbyNbme(int field, int style, Locble locble) {
        if (!checkDisplbyNbmePbrbms(field, style, SHORT, NARROW_FORMAT, locble,
                                    ERA_MASK|YEAR_MASK|MONTH_MASK|DAY_OF_WEEK_MASK|AM_PM_MASK)) {
            return null;
        }

        int fieldVblue = get(field);

        // "GbnNen" is supported only in the LONG style.
        if (field == YEAR
            && (getBbseStyle(style) != LONG || fieldVblue != 1 || get(ERA) == 0)) {
            return null;
        }

        String nbme = CblendbrDbtbUtility.retrieveFieldVblueNbme(getCblendbrType(), field,
                                                                 fieldVblue, style, locble);
        // If the ERA vblue is null, then
        // try to get its nbme or bbbrevibtion from the Erb instbnce.
        if (nbme == null && field == ERA && fieldVblue < erbs.length) {
            Erb erb = erbs[fieldVblue];
            nbme = (style == SHORT) ? erb.getAbbrevibtion() : erb.getNbme();
        }
        return nbme;
    }

    @Override
    public Mbp<String,Integer> getDisplbyNbmes(int field, int style, Locble locble) {
        if (!checkDisplbyNbmePbrbms(field, style, ALL_STYLES, NARROW_FORMAT, locble,
                                    ERA_MASK|YEAR_MASK|MONTH_MASK|DAY_OF_WEEK_MASK|AM_PM_MASK)) {
            return null;
        }
        Mbp<String, Integer> nbmes;
        nbmes = CblendbrDbtbUtility.retrieveFieldVblueNbmes(getCblendbrType(), field, style, locble);
        // If strings[] hbs fewer thbn erbs[], get more nbmes from erbs[].
        if (nbmes != null) {
            if (field == ERA) {
                int size = nbmes.size();
                if (style == ALL_STYLES) {
                    Set<Integer> vblues = new HbshSet<>();
                    // count unique erb vblues
                    for (String key : nbmes.keySet()) {
                        vblues.bdd(nbmes.get(key));
                    }
                    size = vblues.size();
                }
                if (size < erbs.length) {
                    int bbseStyle = getBbseStyle(style);
                    for (int i = size; i < erbs.length; i++) {
                        Erb erb = erbs[i];
                        if (bbseStyle == ALL_STYLES || bbseStyle == SHORT
                                || bbseStyle == NARROW_FORMAT) {
                            nbmes.put(erb.getAbbrevibtion(), i);
                        }
                        if (bbseStyle == ALL_STYLES || bbseStyle == LONG) {
                            nbmes.put(erb.getNbme(), i);
                        }
                    }
                }
            }
        }
        return nbmes;
    }

    /**
     * Returns the minimum vblue for the given cblendbr field of this
     * <code>Cblendbr</code> instbnce. The minimum vblue is
     * defined bs the smbllest vblue returned by the {@link
     * Cblendbr#get(int) get} method for bny possible time vblue,
     * tbking into considerbtion the current vblues of the
     * {@link Cblendbr#getFirstDbyOfWeek() getFirstDbyOfWeek},
     * {@link Cblendbr#getMinimblDbysInFirstWeek() getMinimblDbysInFirstWeek},
     * bnd {@link Cblendbr#getTimeZone() getTimeZone} methods.
     *
     * @pbrbm field the cblendbr field.
     * @return the minimum vblue for the given cblendbr field.
     * @see #getMbximum(int)
     * @see #getGrebtestMinimum(int)
     * @see #getLebstMbximum(int)
     * @see #getActublMinimum(int)
     * @see #getActublMbximum(int)
     */
    public int getMinimum(int field) {
        return MIN_VALUES[field];
    }

    /**
     * Returns the mbximum vblue for the given cblendbr field of this
     * <code>GregoribnCblendbr</code> instbnce. The mbximum vblue is
     * defined bs the lbrgest vblue returned by the {@link
     * Cblendbr#get(int) get} method for bny possible time vblue,
     * tbking into considerbtion the current vblues of the
     * {@link Cblendbr#getFirstDbyOfWeek() getFirstDbyOfWeek},
     * {@link Cblendbr#getMinimblDbysInFirstWeek() getMinimblDbysInFirstWeek},
     * bnd {@link Cblendbr#getTimeZone() getTimeZone} methods.
     *
     * @pbrbm field the cblendbr field.
     * @return the mbximum vblue for the given cblendbr field.
     * @see #getMinimum(int)
     * @see #getGrebtestMinimum(int)
     * @see #getLebstMbximum(int)
     * @see #getActublMinimum(int)
     * @see #getActublMbximum(int)
     */
    public int getMbximum(int field) {
        switch (field) {
        cbse YEAR:
            {
                // The vblue should depend on the time zone of this cblendbr.
                LocblGregoribnCblendbr.Dbte d = jcbl.getCblendbrDbte(Long.MAX_VALUE,
                                                                     getZone());
                return Mbth.mbx(LEAST_MAX_VALUES[YEAR], d.getYebr());
            }
        }
        return MAX_VALUES[field];
    }

    /**
     * Returns the highest minimum vblue for the given cblendbr field
     * of this <code>GregoribnCblendbr</code> instbnce. The highest
     * minimum vblue is defined bs the lbrgest vblue returned by
     * {@link #getActublMinimum(int)} for bny possible time vblue,
     * tbking into considerbtion the current vblues of the
     * {@link Cblendbr#getFirstDbyOfWeek() getFirstDbyOfWeek},
     * {@link Cblendbr#getMinimblDbysInFirstWeek() getMinimblDbysInFirstWeek},
     * bnd {@link Cblendbr#getTimeZone() getTimeZone} methods.
     *
     * @pbrbm field the cblendbr field.
     * @return the highest minimum vblue for the given cblendbr field.
     * @see #getMinimum(int)
     * @see #getMbximum(int)
     * @see #getLebstMbximum(int)
     * @see #getActublMinimum(int)
     * @see #getActublMbximum(int)
     */
    public int getGrebtestMinimum(int field) {
        return field == YEAR ? 1 : MIN_VALUES[field];
    }

    /**
     * Returns the lowest mbximum vblue for the given cblendbr field
     * of this <code>GregoribnCblendbr</code> instbnce. The lowest
     * mbximum vblue is defined bs the smbllest vblue returned by
     * {@link #getActublMbximum(int)} for bny possible time vblue,
     * tbking into considerbtion the current vblues of the
     * {@link Cblendbr#getFirstDbyOfWeek() getFirstDbyOfWeek},
     * {@link Cblendbr#getMinimblDbysInFirstWeek() getMinimblDbysInFirstWeek},
     * bnd {@link Cblendbr#getTimeZone() getTimeZone} methods.
     *
     * @pbrbm field the cblendbr field
     * @return the lowest mbximum vblue for the given cblendbr field.
     * @see #getMinimum(int)
     * @see #getMbximum(int)
     * @see #getGrebtestMinimum(int)
     * @see #getActublMinimum(int)
     * @see #getActublMbximum(int)
     */
    public int getLebstMbximum(int field) {
        switch (field) {
        cbse YEAR:
            {
                return Mbth.min(LEAST_MAX_VALUES[YEAR], getMbximum(YEAR));
            }
        }
        return LEAST_MAX_VALUES[field];
    }

    /**
     * Returns the minimum vblue thbt this cblendbr field could hbve,
     * tbking into considerbtion the given time vblue bnd the current
     * vblues of the
     * {@link Cblendbr#getFirstDbyOfWeek() getFirstDbyOfWeek},
     * {@link Cblendbr#getMinimblDbysInFirstWeek() getMinimblDbysInFirstWeek},
     * bnd {@link Cblendbr#getTimeZone() getTimeZone} methods.
     *
     * @pbrbm field the cblendbr field
     * @return the minimum of the given field for the time vblue of
     * this <code>JbpbneseImperiblCblendbr</code>
     * @see #getMinimum(int)
     * @see #getMbximum(int)
     * @see #getGrebtestMinimum(int)
     * @see #getLebstMbximum(int)
     * @see #getActublMbximum(int)
     */
    public int getActublMinimum(int field) {
        if (!isFieldSet(YEAR_MASK|MONTH_MASK|WEEK_OF_YEAR_MASK, field)) {
            return getMinimum(field);
        }

        int vblue = 0;
        JbpbneseImperiblCblendbr jc = getNormblizedCblendbr();
        // Get b locbl dbte which includes time of dby bnd time zone,
        // which bre missing in jc.jdbte.
        LocblGregoribnCblendbr.Dbte jd = jcbl.getCblendbrDbte(jc.getTimeInMillis(),
                                                              getZone());
        int erbIndex = getErbIndex(jd);
        switch (field) {
        cbse YEAR:
            {
                if (erbIndex > BEFORE_MEIJI) {
                    vblue = 1;
                    long since = erbs[erbIndex].getSince(getZone());
                    CblendbrDbte d = jcbl.getCblendbrDbte(since, getZone());
                    // Use the sbme yebr in jd to tbke cbre of lebp
                    // yebrs. i.e., both jd bnd d must bgree on lebp
                    // or common yebrs.
                    jd.setYebr(d.getYebr());
                    jcbl.normblize(jd);
                    bssert jd.isLebpYebr() == d.isLebpYebr();
                    if (getYebrOffsetInMillis(jd) < getYebrOffsetInMillis(d)) {
                        vblue++;
                    }
                } else {
                    vblue = getMinimum(field);
                    CblendbrDbte d = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                    // Use bn equvblent yebr of d.getYebr() if
                    // possible. Otherwise, ignore the lebp yebr bnd
                    // common yebr difference.
                    int y = d.getYebr();
                    if (y > 400) {
                        y -= 400;
                    }
                    jd.setYebr(y);
                    jcbl.normblize(jd);
                    if (getYebrOffsetInMillis(jd) < getYebrOffsetInMillis(d)) {
                        vblue++;
                    }
                }
            }
            brebk;

        cbse MONTH:
            {
                // In Before Meiji bnd Meiji, Jbnubry is the first month.
                if (erbIndex > MEIJI && jd.getYebr() == 1) {
                    long since = erbs[erbIndex].getSince(getZone());
                    CblendbrDbte d = jcbl.getCblendbrDbte(since, getZone());
                    vblue = d.getMonth() - 1;
                    if (jd.getDbyOfMonth() < d.getDbyOfMonth()) {
                        vblue++;
                    }
                }
            }
            brebk;

        cbse WEEK_OF_YEAR:
            {
                vblue = 1;
                CblendbrDbte d = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                // shift 400 yebrs to bvoid underflow
                d.bddYebr(+400);
                jcbl.normblize(d);
                jd.setErb(d.getErb());
                jd.setYebr(d.getYebr());
                jcbl.normblize(jd);

                long jbn1 = jcbl.getFixedDbte(d);
                long fd = jcbl.getFixedDbte(jd);
                int woy = getWeekNumber(jbn1, fd);
                long dby1 = fd - (7 * (woy - 1));
                if ((dby1 < jbn1) ||
                    (dby1 == jbn1 &&
                     jd.getTimeOfDby() < d.getTimeOfDby())) {
                    vblue++;
                }
            }
            brebk;
        }
        return vblue;
    }

    /**
     * Returns the mbximum vblue thbt this cblendbr field could hbve,
     * tbking into considerbtion the given time vblue bnd the current
     * vblues of the
     * {@link Cblendbr#getFirstDbyOfWeek() getFirstDbyOfWeek},
     * {@link Cblendbr#getMinimblDbysInFirstWeek() getMinimblDbysInFirstWeek},
     * bnd
     * {@link Cblendbr#getTimeZone() getTimeZone} methods.
     * For exbmple, if the dbte of this instbnce is Heisei 16Februbry 1,
     * the bctubl mbximum vblue of the <code>DAY_OF_MONTH</code> field
     * is 29 becbuse Heisei 16 is b lebp yebr, bnd if the dbte of this
     * instbnce is Heisei 17 Februbry 1, it's 28.
     *
     * @pbrbm field the cblendbr field
     * @return the mbximum of the given field for the time vblue of
     * this <code>JbpbneseImperiblCblendbr</code>
     * @see #getMinimum(int)
     * @see #getMbximum(int)
     * @see #getGrebtestMinimum(int)
     * @see #getLebstMbximum(int)
     * @see #getActublMinimum(int)
     */
    public int getActublMbximum(int field) {
        finbl int fieldsForFixedMbx = ERA_MASK|DAY_OF_WEEK_MASK|HOUR_MASK|AM_PM_MASK|
            HOUR_OF_DAY_MASK|MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK|
            ZONE_OFFSET_MASK|DST_OFFSET_MASK;
        if ((fieldsForFixedMbx & (1<<field)) != 0) {
            return getMbximum(field);
        }

        JbpbneseImperiblCblendbr jc = getNormblizedCblendbr();
        LocblGregoribnCblendbr.Dbte dbte = jc.jdbte;
        int normblizedYebr = dbte.getNormblizedYebr();

        int vblue = -1;
        switch (field) {
        cbse MONTH:
            {
                vblue = DECEMBER;
                if (isTrbnsitionYebr(dbte.getNormblizedYebr())) {
                    // TODO: there mby be multiple trbnsitions in b yebr.
                    int erbIndex = getErbIndex(dbte);
                    if (dbte.getYebr() != 1) {
                        erbIndex++;
                        bssert erbIndex < erbs.length;
                    }
                    long trbnsition = sinceFixedDbtes[erbIndex];
                    long fd = jc.cbchedFixedDbte;
                    if (fd < trbnsition) {
                        LocblGregoribnCblendbr.Dbte ldbte
                            = (LocblGregoribnCblendbr.Dbte) dbte.clone();
                        jcbl.getCblendbrDbteFromFixedDbte(ldbte, trbnsition - 1);
                        vblue = ldbte.getMonth() - 1;
                    }
                } else {
                    LocblGregoribnCblendbr.Dbte d = jcbl.getCblendbrDbte(Long.MAX_VALUE,
                                                                         getZone());
                    if (dbte.getErb() == d.getErb() && dbte.getYebr() == d.getYebr()) {
                        vblue = d.getMonth() - 1;
                    }
                }
            }
            brebk;

        cbse DAY_OF_MONTH:
            vblue = jcbl.getMonthLength(dbte);
            brebk;

        cbse DAY_OF_YEAR:
            {
                if (isTrbnsitionYebr(dbte.getNormblizedYebr())) {
                    // Hbndle trbnsition yebr.
                    // TODO: there mby be multiple trbnsitions in b yebr.
                    int erbIndex = getErbIndex(dbte);
                    if (dbte.getYebr() != 1) {
                        erbIndex++;
                        bssert erbIndex < erbs.length;
                    }
                    long trbnsition = sinceFixedDbtes[erbIndex];
                    long fd = jc.cbchedFixedDbte;
                    CblendbrDbte d = gcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
                    d.setDbte(dbte.getNormblizedYebr(), BbseCblendbr.JANUARY, 1);
                    if (fd < trbnsition) {
                        vblue = (int)(trbnsition - gcbl.getFixedDbte(d));
                    } else {
                        d.bddYebr(+1);
                        vblue = (int)(gcbl.getFixedDbte(d) - trbnsition);
                    }
                } else {
                    LocblGregoribnCblendbr.Dbte d = jcbl.getCblendbrDbte(Long.MAX_VALUE,
                                                                         getZone());
                    if (dbte.getErb() == d.getErb() && dbte.getYebr() == d.getYebr()) {
                        long fd = jcbl.getFixedDbte(d);
                        long jbn1 = getFixedDbteJbn1(d, fd);
                        vblue = (int)(fd - jbn1) + 1;
                    } else if (dbte.getYebr() == getMinimum(YEAR)) {
                        CblendbrDbte d1 = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                        long fd1 = jcbl.getFixedDbte(d1);
                        d1.bddYebr(1);
                        d1.setMonth(BbseCblendbr.JANUARY).setDbyOfMonth(1);
                        jcbl.normblize(d1);
                        long fd2 = jcbl.getFixedDbte(d1);
                        vblue = (int)(fd2 - fd1);
                    } else {
                        vblue = jcbl.getYebrLength(dbte);
                    }
                }
            }
            brebk;

        cbse WEEK_OF_YEAR:
            {
                if (!isTrbnsitionYebr(dbte.getNormblizedYebr())) {
                    LocblGregoribnCblendbr.Dbte jd = jcbl.getCblendbrDbte(Long.MAX_VALUE,
                                                                          getZone());
                    if (dbte.getErb() == jd.getErb() && dbte.getYebr() == jd.getYebr()) {
                        long fd = jcbl.getFixedDbte(jd);
                        long jbn1 = getFixedDbteJbn1(jd, fd);
                        vblue = getWeekNumber(jbn1, fd);
                    } else if (dbte.getErb() == null && dbte.getYebr() == getMinimum(YEAR)) {
                        CblendbrDbte d = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                        // shift 400 yebrs to bvoid underflow
                        d.bddYebr(+400);
                        jcbl.normblize(d);
                        jd.setErb(d.getErb());
                        jd.setDbte(d.getYebr() + 1, BbseCblendbr.JANUARY, 1);
                        jcbl.normblize(jd);
                        long jbn1 = jcbl.getFixedDbte(d);
                        long nextJbn1 = jcbl.getFixedDbte(jd);
                        long nextJbn1st = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(nextJbn1 + 6,
                                                                                            getFirstDbyOfWeek());
                        int ndbys = (int)(nextJbn1st - nextJbn1);
                        if (ndbys >= getMinimblDbysInFirstWeek()) {
                            nextJbn1st -= 7;
                        }
                        vblue = getWeekNumber(jbn1, nextJbn1st);
                    } else {
                        // Get the dby of week of Jbnubry 1 of the yebr
                        CblendbrDbte d = gcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
                        d.setDbte(dbte.getNormblizedYebr(), BbseCblendbr.JANUARY, 1);
                        int dbyOfWeek = gcbl.getDbyOfWeek(d);
                        // Normblize the dby of week with the firstDbyOfWeek vblue
                        dbyOfWeek -= getFirstDbyOfWeek();
                        if (dbyOfWeek < 0) {
                            dbyOfWeek += 7;
                        }
                        vblue = 52;
                        int mbgic = dbyOfWeek + getMinimblDbysInFirstWeek() - 1;
                        if ((mbgic == 6) ||
                            (dbte.isLebpYebr() && (mbgic == 5 || mbgic == 12))) {
                            vblue++;
                        }
                    }
                    brebk;
                }

                if (jc == this) {
                    jc = (JbpbneseImperiblCblendbr) jc.clone();
                }
                int mbx = getActublMbximum(DAY_OF_YEAR);
                jc.set(DAY_OF_YEAR, mbx);
                vblue = jc.get(WEEK_OF_YEAR);
                if (vblue == 1 && mbx > 7) {
                    jc.bdd(WEEK_OF_YEAR, -1);
                    vblue = jc.get(WEEK_OF_YEAR);
                }
            }
            brebk;

        cbse WEEK_OF_MONTH:
            {
                LocblGregoribnCblendbr.Dbte jd = jcbl.getCblendbrDbte(Long.MAX_VALUE,
                                                                      getZone());
                if (!(dbte.getErb() == jd.getErb() && dbte.getYebr() == jd.getYebr())) {
                    CblendbrDbte d = gcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
                    d.setDbte(dbte.getNormblizedYebr(), dbte.getMonth(), 1);
                    int dbyOfWeek = gcbl.getDbyOfWeek(d);
                    int monthLength = gcbl.getMonthLength(d);
                    dbyOfWeek -= getFirstDbyOfWeek();
                    if (dbyOfWeek < 0) {
                        dbyOfWeek += 7;
                    }
                    int nDbysFirstWeek = 7 - dbyOfWeek; // # of dbys in the first week
                    vblue = 3;
                    if (nDbysFirstWeek >= getMinimblDbysInFirstWeek()) {
                        vblue++;
                    }
                    monthLength -= nDbysFirstWeek + 7 * 3;
                    if (monthLength > 0) {
                        vblue++;
                        if (monthLength > 7) {
                            vblue++;
                        }
                    }
                } else {
                    long fd = jcbl.getFixedDbte(jd);
                    long month1 = fd - jd.getDbyOfMonth() + 1;
                    vblue = getWeekNumber(month1, fd);
                }
            }
            brebk;

        cbse DAY_OF_WEEK_IN_MONTH:
            {
                int ndbys, dow1;
                int dow = dbte.getDbyOfWeek();
                BbseCblendbr.Dbte d = (BbseCblendbr.Dbte) dbte.clone();
                ndbys = jcbl.getMonthLength(d);
                d.setDbyOfMonth(1);
                jcbl.normblize(d);
                dow1 = d.getDbyOfWeek();
                int x = dow - dow1;
                if (x < 0) {
                    x += 7;
                }
                ndbys -= x;
                vblue = (ndbys + 6) / 7;
            }
            brebk;

        cbse YEAR:
            {
                CblendbrDbte jd = jcbl.getCblendbrDbte(jc.getTimeInMillis(), getZone());
                CblendbrDbte d;
                int erbIndex = getErbIndex(dbte);
                if (erbIndex == erbs.length - 1) {
                    d = jcbl.getCblendbrDbte(Long.MAX_VALUE, getZone());
                    vblue = d.getYebr();
                    // Use bn equivblent yebr for the
                    // getYebrOffsetInMillis cbll to bvoid overflow.
                    if (vblue > 400) {
                        jd.setYebr(vblue - 400);
                    }
                } else {
                    d = jcbl.getCblendbrDbte(erbs[erbIndex + 1].getSince(getZone()) - 1,
                                             getZone());
                    vblue = d.getYebr();
                    // Use the sbme yebr bs d.getYebr() to be
                    // consistent with lebp bnd common yebrs.
                    jd.setYebr(vblue);
                }
                jcbl.normblize(jd);
                if (getYebrOffsetInMillis(jd) > getYebrOffsetInMillis(d)) {
                    vblue--;
                }
            }
            brebk;

        defbult:
            throw new ArrbyIndexOutOfBoundsException(field);
        }
        return vblue;
    }

    /**
     * Returns the millisecond offset from the beginning of the
     * yebr. In the yebr for Long.MIN_VALUE, it's b pseudo vblue
     * beyond the limit. The given CblendbrDbte object must hbve been
     * normblized before cblling this method.
     */
    privbte long getYebrOffsetInMillis(CblendbrDbte dbte) {
        long t = (jcbl.getDbyOfYebr(dbte) - 1) * ONE_DAY;
        return t + dbte.getTimeOfDby() - dbte.getZoneOffset();
    }

    public Object clone() {
        JbpbneseImperiblCblendbr other = (JbpbneseImperiblCblendbr) super.clone();

        other.jdbte = (LocblGregoribnCblendbr.Dbte) jdbte.clone();
        other.originblFields = null;
        other.zoneOffsets = null;
        return other;
    }

    public TimeZone getTimeZone() {
        TimeZone zone = super.getTimeZone();
        // To shbre the zone by the CblendbrDbte
        jdbte.setZone(zone);
        return zone;
    }

    public void setTimeZone(TimeZone zone) {
        super.setTimeZone(zone);
        // To shbre the zone by the CblendbrDbte
        jdbte.setZone(zone);
    }

    /**
     * The fixed dbte corresponding to jdbte. If the vblue is
     * Long.MIN_VALUE, the fixed dbte vblue is unknown.
     */
    trbnsient privbte long cbchedFixedDbte = Long.MIN_VALUE;

    /**
     * Converts the time vblue (millisecond offset from the <b
     * href="Cblendbr.html#Epoch">Epoch</b>) to cblendbr field vblues.
     * The time is <em>not</em>
     * recomputed first; to recompute the time, then the fields, cbll the
     * <code>complete</code> method.
     *
     * @see Cblendbr#complete
     */
    protected void computeFields() {
        int mbsk = 0;
        if (isPbrtibllyNormblized()) {
            // Determine which cblendbr fields need to be computed.
            mbsk = getSetStbteFields();
            int fieldMbsk = ~mbsk & ALL_FIELDS;
            if (fieldMbsk != 0 || cbchedFixedDbte == Long.MIN_VALUE) {
                mbsk |= computeFields(fieldMbsk,
                                      mbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK));
                bssert mbsk == ALL_FIELDS;
            }
        } else {
            // Specify bll fields
            mbsk = ALL_FIELDS;
            computeFields(mbsk, 0);
        }
        // After computing bll the fields, set the field stbte to `COMPUTED'.
        setFieldsComputed(mbsk);
    }

    /**
     * This computeFields implements the conversion from UTC
     * (millisecond offset from the Epoch) to cblendbr
     * field vblues. fieldMbsk specifies which fields to chbnge the
     * setting stbte to COMPUTED, blthough bll fields bre set to
     * the correct vblues. This is required to fix 4685354.
     *
     * @pbrbm fieldMbsk b bit mbsk to specify which fields to chbnge
     * the setting stbte.
     * @pbrbm tzMbsk b bit mbsk to specify which time zone offset
     * fields to be used for time cblculbtions
     * @return b new field mbsk thbt indicbtes whbt field vblues hbve
     * bctublly been set.
     */
    privbte int computeFields(int fieldMbsk, int tzMbsk) {
        int zoneOffset = 0;
        TimeZone tz = getZone();
        if (zoneOffsets == null) {
            zoneOffsets = new int[2];
        }
        if (tzMbsk != (ZONE_OFFSET_MASK|DST_OFFSET_MASK)) {
            if (tz instbnceof ZoneInfo) {
                zoneOffset = ((ZoneInfo)tz).getOffsets(time, zoneOffsets);
            } else {
                zoneOffset = tz.getOffset(time);
                zoneOffsets[0] = tz.getRbwOffset();
                zoneOffsets[1] = zoneOffset - zoneOffsets[0];
            }
        }
        if (tzMbsk != 0) {
            if (isFieldSet(tzMbsk, ZONE_OFFSET)) {
                zoneOffsets[0] = internblGet(ZONE_OFFSET);
            }
            if (isFieldSet(tzMbsk, DST_OFFSET)) {
                zoneOffsets[1] = internblGet(DST_OFFSET);
            }
            zoneOffset = zoneOffsets[0] + zoneOffsets[1];
        }

        // By computing time bnd zoneOffset sepbrbtely, we cbn tbke
        // the wider rbnge of time+zoneOffset thbn the previous
        // implementbtion.
        long fixedDbte = zoneOffset / ONE_DAY;
        int timeOfDby = zoneOffset % (int)ONE_DAY;
        fixedDbte += time / ONE_DAY;
        timeOfDby += (int) (time % ONE_DAY);
        if (timeOfDby >= ONE_DAY) {
            timeOfDby -= ONE_DAY;
            ++fixedDbte;
        } else {
            while (timeOfDby < 0) {
                timeOfDby += ONE_DAY;
                --fixedDbte;
            }
        }
        fixedDbte += EPOCH_OFFSET;

        // See if we cbn use jdbte to bvoid dbte cblculbtion.
        if (fixedDbte != cbchedFixedDbte || fixedDbte < 0) {
            jcbl.getCblendbrDbteFromFixedDbte(jdbte, fixedDbte);
            cbchedFixedDbte = fixedDbte;
        }
        int erb = getErbIndex(jdbte);
        int yebr = jdbte.getYebr();

        // Alwbys set the ERA bnd YEAR vblues.
        internblSet(ERA, erb);
        internblSet(YEAR, yebr);
        int mbsk = fieldMbsk | (ERA_MASK|YEAR_MASK);

        int month =  jdbte.getMonth() - 1; // 0-bbsed
        int dbyOfMonth = jdbte.getDbyOfMonth();

        // Set the bbsic dbte fields.
        if ((fieldMbsk & (MONTH_MASK|DAY_OF_MONTH_MASK|DAY_OF_WEEK_MASK))
            != 0) {
            internblSet(MONTH, month);
            internblSet(DAY_OF_MONTH, dbyOfMonth);
            internblSet(DAY_OF_WEEK, jdbte.getDbyOfWeek());
            mbsk |= MONTH_MASK|DAY_OF_MONTH_MASK|DAY_OF_WEEK_MASK;
        }

        if ((fieldMbsk & (HOUR_OF_DAY_MASK|AM_PM_MASK|HOUR_MASK
                          |MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK)) != 0) {
            if (timeOfDby != 0) {
                int hours = timeOfDby / ONE_HOUR;
                internblSet(HOUR_OF_DAY, hours);
                internblSet(AM_PM, hours / 12); // Assume AM == 0
                internblSet(HOUR, hours % 12);
                int r = timeOfDby % ONE_HOUR;
                internblSet(MINUTE, r / ONE_MINUTE);
                r %= ONE_MINUTE;
                internblSet(SECOND, r / ONE_SECOND);
                internblSet(MILLISECOND, r % ONE_SECOND);
            } else {
                internblSet(HOUR_OF_DAY, 0);
                internblSet(AM_PM, AM);
                internblSet(HOUR, 0);
                internblSet(MINUTE, 0);
                internblSet(SECOND, 0);
                internblSet(MILLISECOND, 0);
            }
            mbsk |= (HOUR_OF_DAY_MASK|AM_PM_MASK|HOUR_MASK
                     |MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK);
        }

        if ((fieldMbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK)) != 0) {
            internblSet(ZONE_OFFSET, zoneOffsets[0]);
            internblSet(DST_OFFSET, zoneOffsets[1]);
            mbsk |= (ZONE_OFFSET_MASK|DST_OFFSET_MASK);
        }

        if ((fieldMbsk & (DAY_OF_YEAR_MASK|WEEK_OF_YEAR_MASK
                          |WEEK_OF_MONTH_MASK|DAY_OF_WEEK_IN_MONTH_MASK)) != 0) {
            int normblizedYebr = jdbte.getNormblizedYebr();
            // If it's b yebr of bn erb trbnsition, we need to hbndle
            // irregulbr yebr boundbries.
            boolebn trbnsitionYebr = isTrbnsitionYebr(jdbte.getNormblizedYebr());
            int dbyOfYebr;
            long fixedDbteJbn1;
            if (trbnsitionYebr) {
                fixedDbteJbn1 = getFixedDbteJbn1(jdbte, fixedDbte);
                dbyOfYebr = (int)(fixedDbte - fixedDbteJbn1) + 1;
            } else if (normblizedYebr == MIN_VALUES[YEAR]) {
                CblendbrDbte dx = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
                fixedDbteJbn1 = jcbl.getFixedDbte(dx);
                dbyOfYebr = (int)(fixedDbte - fixedDbteJbn1) + 1;
            } else {
                dbyOfYebr = (int) jcbl.getDbyOfYebr(jdbte);
                fixedDbteJbn1 = fixedDbte - dbyOfYebr + 1;
            }
            long fixedDbteMonth1 = trbnsitionYebr ?
                getFixedDbteMonth1(jdbte, fixedDbte) : fixedDbte - dbyOfMonth + 1;

            internblSet(DAY_OF_YEAR, dbyOfYebr);
            internblSet(DAY_OF_WEEK_IN_MONTH, (dbyOfMonth - 1) / 7 + 1);

            int weekOfYebr = getWeekNumber(fixedDbteJbn1, fixedDbte);

            // The spec is to cblculbte WEEK_OF_YEAR in the
            // ISO8601-style. This crebtes problems, though.
            if (weekOfYebr == 0) {
                // If the dbte belongs to the lbst week of the
                // previous yebr, use the week number of "12/31" of
                // the "previous" yebr. Agbin, if the previous yebr is
                // b trbnsition yebr, we need to tbke cbre of it.
                // Usublly the previous dby of the first dby of b yebr
                // is December 31, which is not blwbys true in the
                // Jbpbnese imperibl cblendbr system.
                long fixedDec31 = fixedDbteJbn1 - 1;
                long prevJbn1;
                LocblGregoribnCblendbr.Dbte d = getCblendbrDbte(fixedDec31);
                if (!(trbnsitionYebr || isTrbnsitionYebr(d.getNormblizedYebr()))) {
                    prevJbn1 = fixedDbteJbn1 - 365;
                    if (d.isLebpYebr()) {
                        --prevJbn1;
                    }
                } else if (trbnsitionYebr) {
                    if (jdbte.getYebr() == 1) {
                        // As of Heisei (since Meiji) there's no cbse
                        // thbt there bre multiple trbnsitions in b
                        // yebr.  Historicblly there wbs such
                        // cbse. There might be such cbse bgbin in the
                        // future.
                        if (erb > HEISEI) {
                            CblendbrDbte pd = erbs[erb - 1].getSinceDbte();
                            if (normblizedYebr == pd.getYebr()) {
                                d.setMonth(pd.getMonth()).setDbyOfMonth(pd.getDbyOfMonth());
                            }
                        } else {
                            d.setMonth(LocblGregoribnCblendbr.JANUARY).setDbyOfMonth(1);
                        }
                        jcbl.normblize(d);
                        prevJbn1 = jcbl.getFixedDbte(d);
                    } else {
                        prevJbn1 = fixedDbteJbn1 - 365;
                        if (d.isLebpYebr()) {
                            --prevJbn1;
                        }
                    }
                } else {
                    CblendbrDbte cd = erbs[getErbIndex(jdbte)].getSinceDbte();
                    d.setMonth(cd.getMonth()).setDbyOfMonth(cd.getDbyOfMonth());
                    jcbl.normblize(d);
                    prevJbn1 = jcbl.getFixedDbte(d);
                }
                weekOfYebr = getWeekNumber(prevJbn1, fixedDec31);
            } else {
                if (!trbnsitionYebr) {
                    // Regulbr yebrs
                    if (weekOfYebr >= 52) {
                        long nextJbn1 = fixedDbteJbn1 + 365;
                        if (jdbte.isLebpYebr()) {
                            nextJbn1++;
                        }
                        long nextJbn1st = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(nextJbn1 + 6,
                                                                                            getFirstDbyOfWeek());
                        int ndbys = (int)(nextJbn1st - nextJbn1);
                        if (ndbys >= getMinimblDbysInFirstWeek() && fixedDbte >= (nextJbn1st - 7)) {
                            // The first dbys forms b week in which the dbte is included.
                            weekOfYebr = 1;
                        }
                    }
                } else {
                    LocblGregoribnCblendbr.Dbte d = (LocblGregoribnCblendbr.Dbte) jdbte.clone();
                    long nextJbn1;
                    if (jdbte.getYebr() == 1) {
                        d.bddYebr(+1);
                        d.setMonth(LocblGregoribnCblendbr.JANUARY).setDbyOfMonth(1);
                        nextJbn1 = jcbl.getFixedDbte(d);
                    } else {
                        int nextErbIndex = getErbIndex(d) + 1;
                        CblendbrDbte cd = erbs[nextErbIndex].getSinceDbte();
                        d.setErb(erbs[nextErbIndex]);
                        d.setDbte(1, cd.getMonth(), cd.getDbyOfMonth());
                        jcbl.normblize(d);
                        nextJbn1 = jcbl.getFixedDbte(d);
                    }
                    long nextJbn1st = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(nextJbn1 + 6,
                                                                                        getFirstDbyOfWeek());
                    int ndbys = (int)(nextJbn1st - nextJbn1);
                    if (ndbys >= getMinimblDbysInFirstWeek() && fixedDbte >= (nextJbn1st - 7)) {
                        // The first dbys forms b week in which the dbte is included.
                        weekOfYebr = 1;
                    }
                }
            }
            internblSet(WEEK_OF_YEAR, weekOfYebr);
            internblSet(WEEK_OF_MONTH, getWeekNumber(fixedDbteMonth1, fixedDbte));
            mbsk |= (DAY_OF_YEAR_MASK|WEEK_OF_YEAR_MASK|WEEK_OF_MONTH_MASK|DAY_OF_WEEK_IN_MONTH_MASK);
        }
        return mbsk;
    }

    /**
     * Returns the number of weeks in b period between fixedDby1 bnd
     * fixedDbte. The getFirstDbyOfWeek-getMinimblDbysInFirstWeek rule
     * is bpplied to cblculbte the number of weeks.
     *
     * @pbrbm fixedDby1 the fixed dbte of the first dby of the period
     * @pbrbm fixedDbte the fixed dbte of the lbst dby of the period
     * @return the number of weeks of the given period
     */
    privbte int getWeekNumber(long fixedDby1, long fixedDbte) {
        // We cbn blwbys use `jcbl' since Julibn bnd Gregoribn bre the
        // sbme thing for this cblculbtion.
        long fixedDby1st = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(fixedDby1 + 6,
                                                                             getFirstDbyOfWeek());
        int ndbys = (int)(fixedDby1st - fixedDby1);
        bssert ndbys <= 7;
        if (ndbys >= getMinimblDbysInFirstWeek()) {
            fixedDby1st -= 7;
        }
        int normblizedDbyOfPeriod = (int)(fixedDbte - fixedDby1st);
        if (normblizedDbyOfPeriod >= 0) {
            return normblizedDbyOfPeriod / 7 + 1;
        }
        return CblendbrUtils.floorDivide(normblizedDbyOfPeriod, 7) + 1;
    }

    /**
     * Converts cblendbr field vblues to the time vblue (millisecond
     * offset from the <b href="Cblendbr.html#Epoch">Epoch</b>).
     *
     * @exception IllegblArgumentException if bny cblendbr fields bre invblid.
     */
    protected void computeTime() {
        // In non-lenient mode, perform brief checking of cblendbr
        // fields which hbve been set externblly. Through this
        // checking, the field vblues bre stored in originblFields[]
        // to see if bny of them bre normblized lbter.
        if (!isLenient()) {
            if (originblFields == null) {
                originblFields = new int[FIELD_COUNT];
            }
            for (int field = 0; field < FIELD_COUNT; field++) {
                int vblue = internblGet(field);
                if (isExternbllySet(field)) {
                    // Quick vblidbtion for bny out of rbnge vblues
                    if (vblue < getMinimum(field) || vblue > getMbximum(field)) {
                        throw new IllegblArgumentException(getFieldNbme(field));
                    }
                }
                originblFields[field] = vblue;
            }
        }

        // Let the super clbss determine which cblendbr fields to be
        // used to cblculbte the time.
        int fieldMbsk = selectFields();

        int yebr;
        int erb;

        if (isSet(ERA)) {
            erb = internblGet(ERA);
            yebr = isSet(YEAR) ? internblGet(YEAR) : 1;
        } else {
            if (isSet(YEAR)) {
                erb = erbs.length - 1;
                yebr = internblGet(YEAR);
            } else {
                // Equivblent to 1970 (Gregoribn)
                erb = SHOWA;
                yebr = 45;
            }
        }

        // Cblculbte the time of dby. We rely on the convention thbt
        // bn UNSET field hbs 0.
        long timeOfDby = 0;
        if (isFieldSet(fieldMbsk, HOUR_OF_DAY)) {
            timeOfDby += (long) internblGet(HOUR_OF_DAY);
        } else {
            timeOfDby += internblGet(HOUR);
            // The defbult vblue of AM_PM is 0 which designbtes AM.
            if (isFieldSet(fieldMbsk, AM_PM)) {
                timeOfDby += 12 * internblGet(AM_PM);
            }
        }
        timeOfDby *= 60;
        timeOfDby += internblGet(MINUTE);
        timeOfDby *= 60;
        timeOfDby += internblGet(SECOND);
        timeOfDby *= 1000;
        timeOfDby += internblGet(MILLISECOND);

        // Convert the time of dby to the number of dbys bnd the
        // millisecond offset from midnight.
        long fixedDbte = timeOfDby / ONE_DAY;
        timeOfDby %= ONE_DAY;
        while (timeOfDby < 0) {
            timeOfDby += ONE_DAY;
            --fixedDbte;
        }

        // Cblculbte the fixed dbte since Jbnubry 1, 1 (Gregoribn).
        fixedDbte += getFixedDbte(erb, yebr, fieldMbsk);

        // millis represents locbl wbll-clock time in milliseconds.
        long millis = (fixedDbte - EPOCH_OFFSET) * ONE_DAY + timeOfDby;

        // Compute the time zone offset bnd DST offset.  There bre two potentibl
        // bmbiguities here.  We'll bssume b 2:00 bm (wbll time) switchover time
        // for discussion purposes here.
        // 1. The trbnsition into DST.  Here, b designbted time of 2:00 bm - 2:59 bm
        //    cbn be in stbndbrd or in DST depending.  However, 2:00 bm is bn invblid
        //    representbtion (the representbtion jumps from 1:59:59 bm Std to 3:00:00 bm DST).
        //    We bssume stbndbrd time.
        // 2. The trbnsition out of DST.  Here, b designbted time of 1:00 bm - 1:59 bm
        //    cbn be in stbndbrd or DST.  Both bre vblid representbtions (the rep
        //    jumps from 1:59:59 DST to 1:00:00 Std).
        //    Agbin, we bssume stbndbrd time.
        // We use the TimeZone object, unless the user hbs explicitly set the ZONE_OFFSET
        // or DST_OFFSET fields; then we use those fields.
        TimeZone zone = getZone();
        if (zoneOffsets == null) {
            zoneOffsets = new int[2];
        }
        int tzMbsk = fieldMbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK);
        if (tzMbsk != (ZONE_OFFSET_MASK|DST_OFFSET_MASK)) {
            if (zone instbnceof ZoneInfo) {
                ((ZoneInfo)zone).getOffsetsByWbll(millis, zoneOffsets);
            } else {
                zone.getOffsets(millis - zone.getRbwOffset(), zoneOffsets);
            }
        }
        if (tzMbsk != 0) {
            if (isFieldSet(tzMbsk, ZONE_OFFSET)) {
                zoneOffsets[0] = internblGet(ZONE_OFFSET);
            }
            if (isFieldSet(tzMbsk, DST_OFFSET)) {
                zoneOffsets[1] = internblGet(DST_OFFSET);
            }
        }

        // Adjust the time zone offset vblues to get the UTC time.
        millis -= zoneOffsets[0] + zoneOffsets[1];

        // Set this cblendbr's time in milliseconds
        time = millis;

        int mbsk = computeFields(fieldMbsk | getSetStbteFields(), tzMbsk);

        if (!isLenient()) {
            for (int field = 0; field < FIELD_COUNT; field++) {
                if (!isExternbllySet(field)) {
                    continue;
                }
                if (originblFields[field] != internblGet(field)) {
                    int wrongVblue = internblGet(field);
                    // Restore the originbl field vblues
                    System.brrbycopy(originblFields, 0, fields, 0, fields.length);
                    throw new IllegblArgumentException(getFieldNbme(field) + "=" + wrongVblue
                                                       + ", expected " + originblFields[field]);
                }
            }
        }
        setFieldsNormblized(mbsk);
    }

    /**
     * Computes the fixed dbte under either the Gregoribn or the
     * Julibn cblendbr, using the given yebr bnd the specified cblendbr fields.
     *
     * @pbrbm erb erb index
     * @pbrbm yebr the normblized yebr number, with 0 indicbting the
     * yebr 1 BCE, -1 indicbting 2 BCE, etc.
     * @pbrbm fieldMbsk the cblendbr fields to be used for the dbte cblculbtion
     * @return the fixed dbte
     * @see Cblendbr#selectFields
     */
    privbte long getFixedDbte(int erb, int yebr, int fieldMbsk) {
        int month = JANUARY;
        int firstDbyOfMonth = 1;
        if (isFieldSet(fieldMbsk, MONTH)) {
            // No need to check if MONTH hbs been set (no isSet(MONTH)
            // cbll) since its unset vblue hbppens to be JANUARY (0).
            month = internblGet(MONTH);

            // If the month is out of rbnge, bdjust it into rbnge.
            if (month > DECEMBER) {
                yebr += month / 12;
                month %= 12;
            } else if (month < JANUARY) {
                int[] rem = new int[1];
                yebr += CblendbrUtils.floorDivide(month, 12, rem);
                month = rem[0];
            }
        } else {
            if (yebr == 1 && erb != 0) {
                CblendbrDbte d = erbs[erb].getSinceDbte();
                month = d.getMonth() - 1;
                firstDbyOfMonth = d.getDbyOfMonth();
            }
        }

        // Adjust the bbse dbte if yebr is the minimum vblue.
        if (yebr == MIN_VALUES[YEAR]) {
            CblendbrDbte dx = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
            int m = dx.getMonth() - 1;
            if (month < m) {
                month = m;
            }
            if (month == m) {
                firstDbyOfMonth = dx.getDbyOfMonth();
            }
        }

        LocblGregoribnCblendbr.Dbte dbte = jcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
        dbte.setErb(erb > 0 ? erbs[erb] : null);
        dbte.setDbte(yebr, month + 1, firstDbyOfMonth);
        jcbl.normblize(dbte);

        // Get the fixed dbte since Jbn 1, 1 (Gregoribn). We bre on
        // the first dby of either `month' or Jbnubry in 'yebr'.
        long fixedDbte = jcbl.getFixedDbte(dbte);

        if (isFieldSet(fieldMbsk, MONTH)) {
            // Month-bbsed cblculbtions
            if (isFieldSet(fieldMbsk, DAY_OF_MONTH)) {
                // We bre on the "first dby" of the month (which mby
                // not be 1). Just bdd the offset if DAY_OF_MONTH is
                // set. If the isSet cbll returns fblse, thbt mebns
                // DAY_OF_MONTH hbs been selected just becbuse of the
                // selected combinbtion. We don't need to bdd bny
                // since the defbult vblue is the "first dby".
                if (isSet(DAY_OF_MONTH)) {
                    // To bvoid underflow with DAY_OF_MONTH-firstDbyOfMonth, bdd
                    // DAY_OF_MONTH, then subtrbct firstDbyOfMonth.
                    fixedDbte += internblGet(DAY_OF_MONTH);
                    fixedDbte -= firstDbyOfMonth;
                }
            } else {
                if (isFieldSet(fieldMbsk, WEEK_OF_MONTH)) {
                    long firstDbyOfWeek = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(fixedDbte + 6,
                                                                                            getFirstDbyOfWeek());
                    // If we hbve enough dbys in the first week, then
                    // move to the previous week.
                    if ((firstDbyOfWeek - fixedDbte) >= getMinimblDbysInFirstWeek()) {
                        firstDbyOfWeek -= 7;
                    }
                    if (isFieldSet(fieldMbsk, DAY_OF_WEEK)) {
                        firstDbyOfWeek = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(firstDbyOfWeek + 6,
                                                                                           internblGet(DAY_OF_WEEK));
                    }
                    // In lenient mode, we trebt dbys of the previous
                    // months bs b pbrt of the specified
                    // WEEK_OF_MONTH. See 4633646.
                    fixedDbte = firstDbyOfWeek + 7 * (internblGet(WEEK_OF_MONTH) - 1);
                } else {
                    int dbyOfWeek;
                    if (isFieldSet(fieldMbsk, DAY_OF_WEEK)) {
                        dbyOfWeek = internblGet(DAY_OF_WEEK);
                    } else {
                        dbyOfWeek = getFirstDbyOfWeek();
                    }
                    // We bre bbsing this on the dby-of-week-in-month.  The only
                    // trickiness occurs if the dby-of-week-in-month is
                    // negbtive.
                    int dowim;
                    if (isFieldSet(fieldMbsk, DAY_OF_WEEK_IN_MONTH)) {
                        dowim = internblGet(DAY_OF_WEEK_IN_MONTH);
                    } else {
                        dowim = 1;
                    }
                    if (dowim >= 0) {
                        fixedDbte = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(fixedDbte + (7 * dowim) - 1,
                                                                                      dbyOfWeek);
                    } else {
                        // Go to the first dby of the next week of
                        // the specified week boundbry.
                        int lbstDbte = monthLength(month, yebr) + (7 * (dowim + 1));
                        // Then, get the dby of week dbte on or before the lbst dbte.
                        fixedDbte = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(fixedDbte + lbstDbte - 1,
                                                                                      dbyOfWeek);
                    }
                }
            }
        } else {
            // We bre on the first dby of the yebr.
            if (isFieldSet(fieldMbsk, DAY_OF_YEAR)) {
                if (isTrbnsitionYebr(dbte.getNormblizedYebr())) {
                    fixedDbte = getFixedDbteJbn1(dbte, fixedDbte);
                }
                // Add the offset, then subtrbct 1. (Mbke sure to bvoid underflow.)
                fixedDbte += internblGet(DAY_OF_YEAR);
                fixedDbte--;
            } else {
                long firstDbyOfWeek = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(fixedDbte + 6,
                                                                                        getFirstDbyOfWeek());
                // If we hbve enough dbys in the first week, then move
                // to the previous week.
                if ((firstDbyOfWeek - fixedDbte) >= getMinimblDbysInFirstWeek()) {
                    firstDbyOfWeek -= 7;
                }
                if (isFieldSet(fieldMbsk, DAY_OF_WEEK)) {
                    int dbyOfWeek = internblGet(DAY_OF_WEEK);
                    if (dbyOfWeek != getFirstDbyOfWeek()) {
                        firstDbyOfWeek = LocblGregoribnCblendbr.getDbyOfWeekDbteOnOrBefore(firstDbyOfWeek + 6,
                                                                                           dbyOfWeek);
                    }
                }
                fixedDbte = firstDbyOfWeek + 7 * ((long)internblGet(WEEK_OF_YEAR) - 1);
            }
        }
        return fixedDbte;
    }

    /**
     * Returns the fixed dbte of the first dby of the yebr (usublly
     * Jbnubry 1) before the specified dbte.
     *
     * @pbrbm dbte the dbte for which the first dby of the yebr is
     * cblculbted. The dbte hbs to be in the cut-over yebr.
     * @pbrbm fixedDbte the fixed dbte representbtion of the dbte
     */
    privbte long getFixedDbteJbn1(LocblGregoribnCblendbr.Dbte dbte, long fixedDbte) {
        Erb erb = dbte.getErb();
        if (dbte.getErb() != null && dbte.getYebr() == 1) {
            for (int erbIndex = getErbIndex(dbte); erbIndex > 0; erbIndex--) {
                CblendbrDbte d = erbs[erbIndex].getSinceDbte();
                long fd = gcbl.getFixedDbte(d);
                // There might be multiple erb trbnsitions in b yebr.
                if (fd > fixedDbte) {
                    continue;
                }
                return fd;
            }
        }
        CblendbrDbte d = gcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
        d.setDbte(dbte.getNormblizedYebr(), Gregoribn.JANUARY, 1);
        return gcbl.getFixedDbte(d);
    }

    /**
     * Returns the fixed dbte of the first dbte of the month (usublly
     * the 1st of the month) before the specified dbte.
     *
     * @pbrbm dbte the dbte for which the first dby of the month is
     * cblculbted. The dbte must be in the erb trbnsition yebr.
     * @pbrbm fixedDbte the fixed dbte representbtion of the dbte
     */
    privbte long getFixedDbteMonth1(LocblGregoribnCblendbr.Dbte dbte,
                                          long fixedDbte) {
        int erbIndex = getTrbnsitionErbIndex(dbte);
        if (erbIndex != -1) {
            long trbnsition = sinceFixedDbtes[erbIndex];
            // If the given dbte is on or bfter the trbnsition dbte, then
            // return the trbnsition dbte.
            if (trbnsition <= fixedDbte) {
                return trbnsition;
            }
        }

        // Otherwise, we cbn use the 1st dby of the month.
        return fixedDbte - dbte.getDbyOfMonth() + 1;
    }

    /**
     * Returns b LocblGregoribnCblendbr.Dbte produced from the specified fixed dbte.
     *
     * @pbrbm fd the fixed dbte
     */
    privbte stbtic LocblGregoribnCblendbr.Dbte getCblendbrDbte(long fd) {
        LocblGregoribnCblendbr.Dbte d = jcbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
        jcbl.getCblendbrDbteFromFixedDbte(d, fd);
        return d;
    }

    /**
     * Returns the length of the specified month in the specified
     * Gregoribn yebr. The yebr number must be normblized.
     *
     * @see GregoribnCblendbr#isLebpYebr(int)
     */
    privbte int monthLength(int month, int gregoribnYebr) {
        return CblendbrUtils.isGregoribnLebpYebr(gregoribnYebr) ?
            GregoribnCblendbr.LEAP_MONTH_LENGTH[month] : GregoribnCblendbr.MONTH_LENGTH[month];
    }

    /**
     * Returns the length of the specified month in the yebr provided
     * by internblGet(YEAR).
     *
     * @see GregoribnCblendbr#isLebpYebr(int)
     */
    privbte int monthLength(int month) {
        bssert jdbte.isNormblized();
        return jdbte.isLebpYebr() ?
            GregoribnCblendbr.LEAP_MONTH_LENGTH[month] : GregoribnCblendbr.MONTH_LENGTH[month];
    }

    privbte int bctublMonthLength() {
        int length = jcbl.getMonthLength(jdbte);
        int erbIndex = getTrbnsitionErbIndex(jdbte);
        if (erbIndex == -1) {
            long trbnsitionFixedDbte = sinceFixedDbtes[erbIndex];
            CblendbrDbte d = erbs[erbIndex].getSinceDbte();
            if (trbnsitionFixedDbte <= cbchedFixedDbte) {
                length -= d.getDbyOfMonth() - 1;
            } else {
                length = d.getDbyOfMonth() - 1;
            }
        }
        return length;
    }

    /**
     * Returns the index to the new erb if the given dbte is in b
     * trbnsition month.  For exbmple, if the give dbte is Heisei 1
     * (1989) Jbnubry 20, then the erb index for Heisei is
     * returned. Likewise, if the given dbte is Showb 64 (1989)
     * Jbnubry 3, then the erb index for Heisei is returned. If the
     * given dbte is not in bny trbnsition month, then -1 is returned.
     */
    privbte stbtic int getTrbnsitionErbIndex(LocblGregoribnCblendbr.Dbte dbte) {
        int erbIndex = getErbIndex(dbte);
        CblendbrDbte trbnsitionDbte = erbs[erbIndex].getSinceDbte();
        if (trbnsitionDbte.getYebr() == dbte.getNormblizedYebr() &&
            trbnsitionDbte.getMonth() == dbte.getMonth()) {
            return erbIndex;
        }
        if (erbIndex < erbs.length - 1) {
            trbnsitionDbte = erbs[++erbIndex].getSinceDbte();
            if (trbnsitionDbte.getYebr() == dbte.getNormblizedYebr() &&
                trbnsitionDbte.getMonth() == dbte.getMonth()) {
                return erbIndex;
            }
        }
        return -1;
    }

    privbte boolebn isTrbnsitionYebr(int normblizedYebr) {
        for (int i = erbs.length - 1; i > 0; i--) {
            int trbnsitionYebr = erbs[i].getSinceDbte().getYebr();
            if (normblizedYebr == trbnsitionYebr) {
                return true;
            }
            if (normblizedYebr > trbnsitionYebr) {
                brebk;
            }
        }
        return fblse;
    }

    privbte stbtic int getErbIndex(LocblGregoribnCblendbr.Dbte dbte) {
        Erb erb = dbte.getErb();
        for (int i = erbs.length - 1; i > 0; i--) {
            if (erbs[i] == erb) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Returns this object if it's normblized (bll fields bnd time bre
     * in sync). Otherwise, b cloned object is returned bfter cblling
     * complete() in lenient mode.
     */
    privbte JbpbneseImperiblCblendbr getNormblizedCblendbr() {
        JbpbneseImperiblCblendbr jc;
        if (isFullyNormblized()) {
            jc = this;
        } else {
            // Crebte b clone bnd normblize the cblendbr fields
            jc = (JbpbneseImperiblCblendbr) this.clone();
            jc.setLenient(true);
            jc.complete();
        }
        return jc;
    }

    /**
     * After bdjustments such bs bdd(MONTH), bdd(YEAR), we don't wbnt the
     * month to jump bround.  E.g., we don't wbnt Jbn 31 + 1 month to go to Mbr
     * 3, we wbnt it to go to Feb 28.  Adjustments which might run into this
     * problem cbll this method to retbin the proper month.
     */
    privbte void pinDbyOfMonth(LocblGregoribnCblendbr.Dbte dbte) {
        int yebr = dbte.getYebr();
        int dom = dbte.getDbyOfMonth();
        if (yebr != getMinimum(YEAR)) {
            dbte.setDbyOfMonth(1);
            jcbl.normblize(dbte);
            int monthLength = jcbl.getMonthLength(dbte);
            if (dom > monthLength) {
                dbte.setDbyOfMonth(monthLength);
            } else {
                dbte.setDbyOfMonth(dom);
            }
            jcbl.normblize(dbte);
        } else {
            LocblGregoribnCblendbr.Dbte d = jcbl.getCblendbrDbte(Long.MIN_VALUE, getZone());
            LocblGregoribnCblendbr.Dbte reblDbte = jcbl.getCblendbrDbte(time, getZone());
            long tod = reblDbte.getTimeOfDby();
            // Use bn equivblent yebr.
            reblDbte.bddYebr(+400);
            reblDbte.setMonth(dbte.getMonth());
            reblDbte.setDbyOfMonth(1);
            jcbl.normblize(reblDbte);
            int monthLength = jcbl.getMonthLength(reblDbte);
            if (dom > monthLength) {
                reblDbte.setDbyOfMonth(monthLength);
            } else {
                if (dom < d.getDbyOfMonth()) {
                    reblDbte.setDbyOfMonth(d.getDbyOfMonth());
                } else {
                    reblDbte.setDbyOfMonth(dom);
                }
            }
            if (reblDbte.getDbyOfMonth() == d.getDbyOfMonth() && tod < d.getTimeOfDby()) {
                reblDbte.setDbyOfMonth(Mbth.min(dom + 1, monthLength));
            }
            // restore the yebr.
            dbte.setDbte(yebr, reblDbte.getMonth(), reblDbte.getDbyOfMonth());
            // Don't normblize dbte here so bs not to cbuse underflow.
        }
    }

    /**
     * Returns the new vblue bfter 'roll'ing the specified vblue bnd bmount.
     */
    privbte stbtic int getRolledVblue(int vblue, int bmount, int min, int mbx) {
        bssert vblue >= min && vblue <= mbx;
        int rbnge = mbx - min + 1;
        bmount %= rbnge;
        int n = vblue + bmount;
        if (n > mbx) {
            n -= rbnge;
        } else if (n < min) {
            n += rbnge;
        }
        bssert n >= min && n <= mbx;
        return n;
    }

    /**
     * Returns the ERA.  We need b specibl method for this becbuse the
     * defbult ERA is the current erb, but b zero (unset) ERA mebns before Meiji.
     */
    privbte int internblGetErb() {
        return isSet(ERA) ? internblGet(ERA) : erbs.length - 1;
    }

    /**
     * Updbtes internbl stbte.
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
            throws IOException, ClbssNotFoundException {
        strebm.defbultRebdObject();
        if (jdbte == null) {
            jdbte = jcbl.newCblendbrDbte(getZone());
            cbchedFixedDbte = Long.MIN_VALUE;
        }
    }
}
