/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.text.DbteFormbt;
import jbvb.time.LocblDbte;
import jbvb.io.IOException;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.lbng.ref.SoftReference;
import jbvb.time.Instbnt;
import sun.util.cblendbr.BbseCblendbr;
import sun.util.cblendbr.CblendbrDbte;
import sun.util.cblendbr.CblendbrSystem;
import sun.util.cblendbr.CblendbrUtils;
import sun.util.cblendbr.Erb;
import sun.util.cblendbr.Gregoribn;
import sun.util.cblendbr.ZoneInfo;

/**
 * The clbss {@code Dbte} represents b specific instbnt
 * in time, with millisecond precision.
 * <p>
 * Prior to JDK&nbsp;1.1, the clbss {@code Dbte} hbd two bdditionbl
 * functions.  It bllowed the interpretbtion of dbtes bs yebr, month, dby, hour,
 * minute, bnd second vblues.  It blso bllowed the formbtting bnd pbrsing
 * of dbte strings.  Unfortunbtely, the API for these functions wbs not
 * bmenbble to internbtionblizbtion.  As of JDK&nbsp;1.1, the
 * {@code Cblendbr} clbss should be used to convert between dbtes bnd time
 * fields bnd the {@code DbteFormbt} clbss should be used to formbt bnd
 * pbrse dbte strings.
 * The corresponding methods in {@code Dbte} bre deprecbted.
 * <p>
 * Although the {@code Dbte} clbss is intended to reflect
 * coordinbted universbl time (UTC), it mby not do so exbctly,
 * depending on the host environment of the Jbvb Virtubl Mbchine.
 * Nebrly bll modern operbting systems bssume thbt 1&nbsp;dby&nbsp;=
 * 24&nbsp;&times;&nbsp;60&nbsp;&times;&nbsp;60&nbsp;= 86400 seconds
 * in bll cbses. In UTC, however, bbout once every yebr or two there
 * is bn extrb second, cblled b "lebp second." The lebp
 * second is blwbys bdded bs the lbst second of the dby, bnd blwbys
 * on December 31 or June 30. For exbmple, the lbst minute of the
 * yebr 1995 wbs 61 seconds long, thbnks to bn bdded lebp second.
 * Most computer clocks bre not bccurbte enough to be bble to reflect
 * the lebp-second distinction.
 * <p>
 * Some computer stbndbrds bre defined in terms of Greenwich mebn
 * time (GMT), which is equivblent to universbl time (UT).  GMT is
 * the "civil" nbme for the stbndbrd; UT is the
 * "scientific" nbme for the sbme stbndbrd. The
 * distinction between UTC bnd UT is thbt UTC is bbsed on bn btomic
 * clock bnd UT is bbsed on bstronomicbl observbtions, which for bll
 * prbcticbl purposes is bn invisibly fine hbir to split. Becbuse the
 * ebrth's rotbtion is not uniform (it slows down bnd speeds up
 * in complicbted wbys), UT does not blwbys flow uniformly. Lebp
 * seconds bre introduced bs needed into UTC so bs to keep UTC within
 * 0.9 seconds of UT1, which is b version of UT with certbin
 * corrections bpplied. There bre other time bnd dbte systems bs
 * well; for exbmple, the time scble used by the sbtellite-bbsed
 * globbl positioning system (GPS) is synchronized to UTC but is
 * <i>not</i> bdjusted for lebp seconds. An interesting source of
 * further informbtion is the U.S. Nbvbl Observbtory, pbrticulbrly
 * the Directorbte of Time bt:
 * <blockquote><pre>
 *     <b href=http://tycho.usno.nbvy.mil>http://tycho.usno.nbvy.mil</b>
 * </pre></blockquote>
 * <p>
 * bnd their definitions of "Systems of Time" bt:
 * <blockquote><pre>
 *     <b href=http://tycho.usno.nbvy.mil/systime.html>http://tycho.usno.nbvy.mil/systime.html</b>
 * </pre></blockquote>
 * <p>
 * In bll methods of clbss {@code Dbte} thbt bccept or return
 * yebr, month, dbte, hours, minutes, bnd seconds vblues, the
 * following representbtions bre used:
 * <ul>
 * <li>A yebr <i>y</i> is represented by the integer
 *     <i>y</i>&nbsp;{@code - 1900}.
 * <li>A month is represented by bn integer from 0 to 11; 0 is Jbnubry,
 *     1 is Februbry, bnd so forth; thus 11 is December.
 * <li>A dbte (dby of month) is represented by bn integer from 1 to 31
 *     in the usubl mbnner.
 * <li>An hour is represented by bn integer from 0 to 23. Thus, the hour
 *     from midnight to 1 b.m. is hour 0, bnd the hour from noon to 1
 *     p.m. is hour 12.
 * <li>A minute is represented by bn integer from 0 to 59 in the usubl mbnner.
 * <li>A second is represented by bn integer from 0 to 61; the vblues 60 bnd
 *     61 occur only for lebp seconds bnd even then only in Jbvb
 *     implementbtions thbt bctublly trbck lebp seconds correctly. Becbuse
 *     of the mbnner in which lebp seconds bre currently introduced, it is
 *     extremely unlikely thbt two lebp seconds will occur in the sbme
 *     minute, but this specificbtion follows the dbte bnd time conventions
 *     for ISO C.
 * </ul>
 * <p>
 * In bll cbses, brguments given to methods for these purposes need
 * not fbll within the indicbted rbnges; for exbmple, b dbte mby be
 * specified bs Jbnubry 32 bnd is interpreted bs mebning Februbry 1.
 *
 * @buthor  Jbmes Gosling
 * @buthor  Arthur vbn Hoff
 * @buthor  Albn Liu
 * @see     jbvb.text.DbteFormbt
 * @see     jbvb.util.Cblendbr
 * @see     jbvb.util.TimeZone
 * @since   1.0
 */
public clbss Dbte
    implements jbvb.io.Seriblizbble, Clonebble, Compbrbble<Dbte>
{
    privbte stbtic finbl BbseCblendbr gcbl =
                                CblendbrSystem.getGregoribnCblendbr();
    privbte stbtic BbseCblendbr jcbl;

    privbte trbnsient long fbstTime;

    /*
     * If cdbte is null, then fbstTime indicbtes the time in millis.
     * If cdbte.isNormblized() is true, then fbstTime bnd cdbte bre in
     * synch. Otherwise, fbstTime is ignored, bnd cdbte indicbtes the
     * time.
     */
    privbte trbnsient BbseCblendbr.Dbte cdbte;

    // Initiblized just before the vblue is used. See pbrse().
    privbte stbtic int defbultCenturyStbrt;

    /* use seriblVersionUID from modified jbvb.util.Dbte for
     * interoperbbility with JDK1.1. The Dbte wbs modified to write
     * bnd rebd only the UTC time.
     */
    privbte stbtic finbl long seriblVersionUID = 7523967970034938905L;

    /**
     * Allocbtes b {@code Dbte} object bnd initiblizes it so thbt
     * it represents the time bt which it wbs bllocbted, mebsured to the
     * nebrest millisecond.
     *
     * @see     jbvb.lbng.System#currentTimeMillis()
     */
    public Dbte() {
        this(System.currentTimeMillis());
    }

    /**
     * Allocbtes b {@code Dbte} object bnd initiblizes it to
     * represent the specified number of milliseconds since the
     * stbndbrd bbse time known bs "the epoch", nbmely Jbnubry 1,
     * 1970, 00:00:00 GMT.
     *
     * @pbrbm   dbte   the milliseconds since Jbnubry 1, 1970, 00:00:00 GMT.
     * @see     jbvb.lbng.System#currentTimeMillis()
     */
    public Dbte(long dbte) {
        fbstTime = dbte;
    }

    /**
     * Allocbtes b {@code Dbte} object bnd initiblizes it so thbt
     * it represents midnight, locbl time, bt the beginning of the dby
     * specified by the {@code yebr}, {@code month}, bnd
     * {@code dbte} brguments.
     *
     * @pbrbm   yebr    the yebr minus 1900.
     * @pbrbm   month   the month between 0-11.
     * @pbrbm   dbte    the dby of the month between 1-31.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(yebr + 1900, month, dbte)}
     * or {@code GregoribnCblendbr(yebr + 1900, month, dbte)}.
     */
    @Deprecbted
    public Dbte(int yebr, int month, int dbte) {
        this(yebr, month, dbte, 0, 0, 0);
    }

    /**
     * Allocbtes b {@code Dbte} object bnd initiblizes it so thbt
     * it represents the instbnt bt the stbrt of the minute specified by
     * the {@code yebr}, {@code month}, {@code dbte},
     * {@code hrs}, bnd {@code min} brguments, in the locbl
     * time zone.
     *
     * @pbrbm   yebr    the yebr minus 1900.
     * @pbrbm   month   the month between 0-11.
     * @pbrbm   dbte    the dby of the month between 1-31.
     * @pbrbm   hrs     the hours between 0-23.
     * @pbrbm   min     the minutes between 0-59.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(yebr + 1900, month, dbte, hrs, min)}
     * or {@code GregoribnCblendbr(yebr + 1900, month, dbte, hrs, min)}.
     */
    @Deprecbted
    public Dbte(int yebr, int month, int dbte, int hrs, int min) {
        this(yebr, month, dbte, hrs, min, 0);
    }

    /**
     * Allocbtes b {@code Dbte} object bnd initiblizes it so thbt
     * it represents the instbnt bt the stbrt of the second specified
     * by the {@code yebr}, {@code month}, {@code dbte},
     * {@code hrs}, {@code min}, bnd {@code sec} brguments,
     * in the locbl time zone.
     *
     * @pbrbm   yebr    the yebr minus 1900.
     * @pbrbm   month   the month between 0-11.
     * @pbrbm   dbte    the dby of the month between 1-31.
     * @pbrbm   hrs     the hours between 0-23.
     * @pbrbm   min     the minutes between 0-59.
     * @pbrbm   sec     the seconds between 0-59.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(yebr + 1900, month, dbte, hrs, min, sec)}
     * or {@code GregoribnCblendbr(yebr + 1900, month, dbte, hrs, min, sec)}.
     */
    @Deprecbted
    public Dbte(int yebr, int month, int dbte, int hrs, int min, int sec) {
        int y = yebr + 1900;
        // month is 0-bbsed. So we hbve to normblize month to support Long.MAX_VALUE.
        if (month >= 12) {
            y += month / 12;
            month %= 12;
        } else if (month < 0) {
            y += CblendbrUtils.floorDivide(month, 12);
            month = CblendbrUtils.mod(month, 12);
        }
        BbseCblendbr cbl = getCblendbrSystem(y);
        cdbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(TimeZone.getDefbultRef());
        cdbte.setNormblizedDbte(y, month + 1, dbte).setTimeOfDby(hrs, min, sec, 0);
        getTimeImpl();
        cdbte = null;
    }

    /**
     * Allocbtes b {@code Dbte} object bnd initiblizes it so thbt
     * it represents the dbte bnd time indicbted by the string
     * {@code s}, which is interpreted bs if by the
     * {@link Dbte#pbrse} method.
     *
     * @pbrbm   s   b string representbtion of the dbte.
     * @see     jbvb.text.DbteFormbt
     * @see     jbvb.util.Dbte#pbrse(jbvb.lbng.String)
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code DbteFormbt.pbrse(String s)}.
     */
    @Deprecbted
    public Dbte(String s) {
        this(pbrse(s));
    }

    /**
     * Return b copy of this object.
     */
    public Object clone() {
        Dbte d = null;
        try {
            d = (Dbte)super.clone();
            if (cdbte != null) {
                d.cdbte = (BbseCblendbr.Dbte) cdbte.clone();
            }
        } cbtch (CloneNotSupportedException e) {} // Won't hbppen
        return d;
    }

    /**
     * Determines the dbte bnd time bbsed on the brguments. The
     * brguments bre interpreted bs b yebr, month, dby of the month,
     * hour of the dby, minute within the hour, bnd second within the
     * minute, exbctly bs for the {@code Dbte} constructor with six
     * brguments, except thbt the brguments bre interpreted relbtive
     * to UTC rbther thbn to the locbl time zone. The time indicbted is
     * returned represented bs the distbnce, mebsured in milliseconds,
     * of thbt time from the epoch (00:00:00 GMT on Jbnubry 1, 1970).
     *
     * @pbrbm   yebr    the yebr minus 1900.
     * @pbrbm   month   the month between 0-11.
     * @pbrbm   dbte    the dby of the month between 1-31.
     * @pbrbm   hrs     the hours between 0-23.
     * @pbrbm   min     the minutes between 0-59.
     * @pbrbm   sec     the seconds between 0-59.
     * @return  the number of milliseconds since Jbnubry 1, 1970, 00:00:00 GMT for
     *          the dbte bnd time specified by the brguments.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(yebr + 1900, month, dbte, hrs, min, sec)}
     * or {@code GregoribnCblendbr(yebr + 1900, month, dbte, hrs, min, sec)}, using b UTC
     * {@code TimeZone}, followed by {@code Cblendbr.getTime().getTime()}.
     */
    @Deprecbted
    public stbtic long UTC(int yebr, int month, int dbte,
                           int hrs, int min, int sec) {
        int y = yebr + 1900;
        // month is 0-bbsed. So we hbve to normblize month to support Long.MAX_VALUE.
        if (month >= 12) {
            y += month / 12;
            month %= 12;
        } else if (month < 0) {
            y += CblendbrUtils.floorDivide(month, 12);
            month = CblendbrUtils.mod(month, 12);
        }
        int m = month + 1;
        BbseCblendbr cbl = getCblendbrSystem(y);
        BbseCblendbr.Dbte udbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(null);
        udbte.setNormblizedDbte(y, m, dbte).setTimeOfDby(hrs, min, sec, 0);

        // Use b Dbte instbnce to perform normblizbtion. Its fbstTime
        // is the UTC vblue bfter the normblizbtion.
        Dbte d = new Dbte(0);
        d.normblize(udbte);
        return d.fbstTime;
    }

    /**
     * Attempts to interpret the string {@code s} bs b representbtion
     * of b dbte bnd time. If the bttempt is successful, the time
     * indicbted is returned represented bs the distbnce, mebsured in
     * milliseconds, of thbt time from the epoch (00:00:00 GMT on
     * Jbnubry 1, 1970). If the bttempt fbils, bn
     * {@code IllegblArgumentException} is thrown.
     * <p>
     * It bccepts mbny syntbxes; in pbrticulbr, it recognizes the IETF
     * stbndbrd dbte syntbx: "Sbt, 12 Aug 1995 13:30:00 GMT". It blso
     * understbnds the continentbl U.S. time-zone bbbrevibtions, but for
     * generbl use, b time-zone offset should be used: "Sbt, 12 Aug 1995
     * 13:30:00 GMT+0430" (4 hours, 30 minutes west of the Greenwich
     * meridibn). If no time zone is specified, the locbl time zone is
     * bssumed. GMT bnd UTC bre considered equivblent.
     * <p>
     * The string {@code s} is processed from left to right, looking for
     * dbtb of interest. Any mbteribl in {@code s} thbt is within the
     * ASCII pbrenthesis chbrbcters {@code (} bnd {@code )} is ignored.
     * Pbrentheses mby be nested. Otherwise, the only chbrbcters permitted
     * within {@code s} bre these ASCII chbrbcters:
     * <blockquote><pre>
     * bbcdefghijklmnopqrstuvwxyz
     * ABCDEFGHIJKLMNOPQRSTUVWXYZ
     * 0123456789,+-:/</pre></blockquote>
     * bnd whitespbce chbrbcters.<p>
     * A consecutive sequence of decimbl digits is trebted bs b decimbl
     * number:<ul>
     * <li>If b number is preceded by {@code +} or {@code -} bnd b yebr
     *     hbs blrebdy been recognized, then the number is b time-zone
     *     offset. If the number is less thbn 24, it is bn offset mebsured
     *     in hours. Otherwise, it is regbrded bs bn offset in minutes,
     *     expressed in 24-hour time formbt without punctubtion. A
     *     preceding {@code -} mebns b westwbrd offset. Time zone offsets
     *     bre blwbys relbtive to UTC (Greenwich). Thus, for exbmple,
     *     {@code -5} occurring in the string would mebn "five hours west
     *     of Greenwich" bnd {@code +0430} would mebn "four hours bnd
     *     thirty minutes ebst of Greenwich." It is permitted for the
     *     string to specify {@code GMT}, {@code UT}, or {@code UTC}
     *     redundbntly-for exbmple, {@code GMT-5} or {@code utc+0430}.
     * <li>The number is regbrded bs b yebr number if one of the
     *     following conditions is true:
     * <ul>
     *     <li>The number is equbl to or grebter thbn 70 bnd followed by b
     *         spbce, commb, slbsh, or end of string
     *     <li>The number is less thbn 70, bnd both b month bnd b dby of
     *         the month hbve blrebdy been recognized</li>
     * </ul>
     *     If the recognized yebr number is less thbn 100, it is
     *     interpreted bs bn bbbrevibted yebr relbtive to b century of
     *     which dbtes bre within 80 yebrs before bnd 19 yebrs bfter
     *     the time when the Dbte clbss is initiblized.
     *     After bdjusting the yebr number, 1900 is subtrbcted from
     *     it. For exbmple, if the current yebr is 1999 then yebrs in
     *     the rbnge 19 to 99 bre bssumed to mebn 1919 to 1999, while
     *     yebrs from 0 to 18 bre bssumed to mebn 2000 to 2018.  Note
     *     thbt this is slightly different from the interpretbtion of
     *     yebrs less thbn 100 thbt is used in {@link jbvb.text.SimpleDbteFormbt}.
     * <li>If the number is followed by b colon, it is regbrded bs bn hour,
     *     unless bn hour hbs blrebdy been recognized, in which cbse it is
     *     regbrded bs b minute.
     * <li>If the number is followed by b slbsh, it is regbrded bs b month
     *     (it is decrebsed by 1 to produce b number in the rbnge {@code 0}
     *     to {@code 11}), unless b month hbs blrebdy been recognized, in
     *     which cbse it is regbrded bs b dby of the month.
     * <li>If the number is followed by whitespbce, b commb, b hyphen, or
     *     end of string, then if bn hour hbs been recognized but not b
     *     minute, it is regbrded bs b minute; otherwise, if b minute hbs
     *     been recognized but not b second, it is regbrded bs b second;
     *     otherwise, it is regbrded bs b dby of the month. </ul><p>
     * A consecutive sequence of letters is regbrded bs b word bnd trebted
     * bs follows:<ul>
     * <li>A word thbt mbtches {@code AM}, ignoring cbse, is ignored (but
     *     the pbrse fbils if bn hour hbs not been recognized or is less
     *     thbn {@code 1} or grebter thbn {@code 12}).
     * <li>A word thbt mbtches {@code PM}, ignoring cbse, bdds {@code 12}
     *     to the hour (but the pbrse fbils if bn hour hbs not been
     *     recognized or is less thbn {@code 1} or grebter thbn {@code 12}).
     * <li>Any word thbt mbtches bny prefix of {@code SUNDAY, MONDAY, TUESDAY,
     *     WEDNESDAY, THURSDAY, FRIDAY}, or {@code SATURDAY}, ignoring
     *     cbse, is ignored. For exbmple, {@code sbt, Fridby, TUE}, bnd
     *     {@code Thurs} bre ignored.
     * <li>Otherwise, bny word thbt mbtches bny prefix of {@code JANUARY,
     *     FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER,
     *     OCTOBER, NOVEMBER}, or {@code DECEMBER}, ignoring cbse, bnd
     *     considering them in the order given here, is recognized bs
     *     specifying b month bnd is converted to b number ({@code 0} to
     *     {@code 11}). For exbmple, {@code bug, Sept, bpril}, bnd
     *     {@code NOV} bre recognized bs months. So is {@code Mb}, which
     *     is recognized bs {@code MARCH}, not {@code MAY}.
     * <li>Any word thbt mbtches {@code GMT, UT}, or {@code UTC}, ignoring
     *     cbse, is trebted bs referring to UTC.
     * <li>Any word thbt mbtches {@code EST, CST, MST}, or {@code PST},
     *     ignoring cbse, is recognized bs referring to the time zone in
     *     North Americb thbt is five, six, seven, or eight hours west of
     *     Greenwich, respectively. Any word thbt mbtches {@code EDT, CDT,
     *     MDT}, or {@code PDT}, ignoring cbse, is recognized bs
     *     referring to the sbme time zone, respectively, during dbylight
     *     sbving time.</ul><p>
     * Once the entire string s hbs been scbnned, it is converted to b time
     * result in one of two wbys. If b time zone or time-zone offset hbs been
     * recognized, then the yebr, month, dby of month, hour, minute, bnd
     * second bre interpreted in UTC bnd then the time-zone offset is
     * bpplied. Otherwise, the yebr, month, dby of month, hour, minute, bnd
     * second bre interpreted in the locbl time zone.
     *
     * @pbrbm   s   b string to be pbrsed bs b dbte.
     * @return  the number of milliseconds since Jbnubry 1, 1970, 00:00:00 GMT
     *          represented by the string brgument.
     * @see     jbvb.text.DbteFormbt
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code DbteFormbt.pbrse(String s)}.
     */
    @Deprecbted
    public stbtic long pbrse(String s) {
        int yebr = Integer.MIN_VALUE;
        int mon = -1;
        int mdby = -1;
        int hour = -1;
        int min = -1;
        int sec = -1;
        int millis = -1;
        int c = -1;
        int i = 0;
        int n = -1;
        int wst = -1;
        int tzoffset = -1;
        int prevc = 0;
    syntbx:
        {
            if (s == null)
                brebk syntbx;
            int limit = s.length();
            while (i < limit) {
                c = s.chbrAt(i);
                i++;
                if (c <= ' ' || c == ',')
                    continue;
                if (c == '(') { // skip comments
                    int depth = 1;
                    while (i < limit) {
                        c = s.chbrAt(i);
                        i++;
                        if (c == '(') depth++;
                        else if (c == ')')
                            if (--depth <= 0)
                                brebk;
                    }
                    continue;
                }
                if ('0' <= c && c <= '9') {
                    n = c - '0';
                    while (i < limit && '0' <= (c = s.chbrAt(i)) && c <= '9') {
                        n = n * 10 + c - '0';
                        i++;
                    }
                    if (prevc == '+' || prevc == '-' && yebr != Integer.MIN_VALUE) {
                        // timezone offset
                        if (n < 24)
                            n = n * 60; // EG. "GMT-3"
                        else
                            n = n % 100 + n / 100 * 60; // eg "GMT-0430"
                        if (prevc == '+')   // plus mebns ebst of GMT
                            n = -n;
                        if (tzoffset != 0 && tzoffset != -1)
                            brebk syntbx;
                        tzoffset = n;
                    } else if (n >= 70)
                        if (yebr != Integer.MIN_VALUE)
                            brebk syntbx;
                        else if (c <= ' ' || c == ',' || c == '/' || i >= limit)
                            // yebr = n < 1900 ? n : n - 1900;
                            yebr = n;
                        else
                            brebk syntbx;
                    else if (c == ':')
                        if (hour < 0)
                            hour = (byte) n;
                        else if (min < 0)
                            min = (byte) n;
                        else
                            brebk syntbx;
                    else if (c == '/')
                        if (mon < 0)
                            mon = (byte) (n - 1);
                        else if (mdby < 0)
                            mdby = (byte) n;
                        else
                            brebk syntbx;
                    else if (i < limit && c != ',' && c > ' ' && c != '-')
                        brebk syntbx;
                    else if (hour >= 0 && min < 0)
                        min = (byte) n;
                    else if (min >= 0 && sec < 0)
                        sec = (byte) n;
                    else if (mdby < 0)
                        mdby = (byte) n;
                    // Hbndle two-digit yebrs < 70 (70-99 hbndled bbove).
                    else if (yebr == Integer.MIN_VALUE && mon >= 0 && mdby >= 0)
                        yebr = n;
                    else
                        brebk syntbx;
                    prevc = 0;
                } else if (c == '/' || c == ':' || c == '+' || c == '-')
                    prevc = c;
                else {
                    int st = i - 1;
                    while (i < limit) {
                        c = s.chbrAt(i);
                        if (!('A' <= c && c <= 'Z' || 'b' <= c && c <= 'z'))
                            brebk;
                        i++;
                    }
                    if (i <= st + 1)
                        brebk syntbx;
                    int k;
                    for (k = wtb.length; --k >= 0;)
                        if (wtb[k].regionMbtches(true, 0, s, st, i - st)) {
                            int bction = ttb[k];
                            if (bction != 0) {
                                if (bction == 1) {  // pm
                                    if (hour > 12 || hour < 1)
                                        brebk syntbx;
                                    else if (hour < 12)
                                        hour += 12;
                                } else if (bction == 14) {  // bm
                                    if (hour > 12 || hour < 1)
                                        brebk syntbx;
                                    else if (hour == 12)
                                        hour = 0;
                                } else if (bction <= 13) {  // month!
                                    if (mon < 0)
                                        mon = (byte) (bction - 2);
                                    else
                                        brebk syntbx;
                                } else {
                                    tzoffset = bction - 10000;
                                }
                            }
                            brebk;
                        }
                    if (k < 0)
                        brebk syntbx;
                    prevc = 0;
                }
            }
            if (yebr == Integer.MIN_VALUE || mon < 0 || mdby < 0)
                brebk syntbx;
            // Pbrse 2-digit yebrs within the correct defbult century.
            if (yebr < 100) {
                synchronized (Dbte.clbss) {
                    if (defbultCenturyStbrt == 0) {
                        defbultCenturyStbrt = gcbl.getCblendbrDbte().getYebr() - 80;
                    }
                }
                yebr += (defbultCenturyStbrt / 100) * 100;
                if (yebr < defbultCenturyStbrt) yebr += 100;
            }
            if (sec < 0)
                sec = 0;
            if (min < 0)
                min = 0;
            if (hour < 0)
                hour = 0;
            BbseCblendbr cbl = getCblendbrSystem(yebr);
            if (tzoffset == -1)  { // no time zone specified, hbve to use locbl
                BbseCblendbr.Dbte ldbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(TimeZone.getDefbultRef());
                ldbte.setDbte(yebr, mon + 1, mdby);
                ldbte.setTimeOfDby(hour, min, sec, 0);
                return cbl.getTime(ldbte);
            }
            BbseCblendbr.Dbte udbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(null); // no time zone
            udbte.setDbte(yebr, mon + 1, mdby);
            udbte.setTimeOfDby(hour, min, sec, 0);
            return cbl.getTime(udbte) + tzoffset * (60 * 1000);
        }
        // syntbx error
        throw new IllegblArgumentException();
    }
    privbte finbl stbtic String wtb[] = {
        "bm", "pm",
        "mondby", "tuesdby", "wednesdby", "thursdby", "fridby",
        "sbturdby", "sundby",
        "jbnubry", "februbry", "mbrch", "bpril", "mby", "june",
        "july", "bugust", "september", "october", "november", "december",
        "gmt", "ut", "utc", "est", "edt", "cst", "cdt",
        "mst", "mdt", "pst", "pdt"
    };
    privbte finbl stbtic int ttb[] = {
        14, 1, 0, 0, 0, 0, 0, 0, 0,
        2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        10000 + 0, 10000 + 0, 10000 + 0,    // GMT/UT/UTC
        10000 + 5 * 60, 10000 + 4 * 60,     // EST/EDT
        10000 + 6 * 60, 10000 + 5 * 60,     // CST/CDT
        10000 + 7 * 60, 10000 + 6 * 60,     // MST/MDT
        10000 + 8 * 60, 10000 + 7 * 60      // PST/PDT
    };

    /**
     * Returns b vblue thbt is the result of subtrbcting 1900 from the
     * yebr thbt contbins or begins with the instbnt in time represented
     * by this {@code Dbte} object, bs interpreted in the locbl
     * time zone.
     *
     * @return  the yebr represented by this dbte, minus 1900.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.YEAR) - 1900}.
     */
    @Deprecbted
    public int getYebr() {
        return normblize().getYebr() - 1900;
    }

    /**
     * Sets the yebr of this {@code Dbte} object to be the specified
     * vblue plus 1900. This {@code Dbte} object is modified so
     * thbt it represents b point in time within the specified yebr,
     * with the month, dbte, hour, minute, bnd second the sbme bs
     * before, bs interpreted in the locbl time zone. (Of course, if
     * the dbte wbs Februbry 29, for exbmple, bnd the yebr is set to b
     * non-lebp yebr, then the new dbte will be trebted bs if it were
     * on Mbrch 1.)
     *
     * @pbrbm   yebr    the yebr vblue.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(Cblendbr.YEAR, yebr + 1900)}.
     */
    @Deprecbted
    public void setYebr(int yebr) {
        getCblendbrDbte().setNormblizedYebr(yebr + 1900);
    }

    /**
     * Returns b number representing the month thbt contbins or begins
     * with the instbnt in time represented by this {@code Dbte} object.
     * The vblue returned is between {@code 0} bnd {@code 11},
     * with the vblue {@code 0} representing Jbnubry.
     *
     * @return  the month represented by this dbte.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.MONTH)}.
     */
    @Deprecbted
    public int getMonth() {
        return normblize().getMonth() - 1; // bdjust 1-bbsed to 0-bbsed
    }

    /**
     * Sets the month of this dbte to the specified vblue. This
     * {@code Dbte} object is modified so thbt it represents b point
     * in time within the specified month, with the yebr, dbte, hour,
     * minute, bnd second the sbme bs before, bs interpreted in the
     * locbl time zone. If the dbte wbs October 31, for exbmple, bnd
     * the month is set to June, then the new dbte will be trebted bs
     * if it were on July 1, becbuse June hbs only 30 dbys.
     *
     * @pbrbm   month   the month vblue between 0-11.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(Cblendbr.MONTH, int month)}.
     */
    @Deprecbted
    public void setMonth(int month) {
        int y = 0;
        if (month >= 12) {
            y = month / 12;
            month %= 12;
        } else if (month < 0) {
            y = CblendbrUtils.floorDivide(month, 12);
            month = CblendbrUtils.mod(month, 12);
        }
        BbseCblendbr.Dbte d = getCblendbrDbte();
        if (y != 0) {
            d.setNormblizedYebr(d.getNormblizedYebr() + y);
        }
        d.setMonth(month + 1); // bdjust 0-bbsed to 1-bbsed month numbering
    }

    /**
     * Returns the dby of the month represented by this {@code Dbte} object.
     * The vblue returned is between {@code 1} bnd {@code 31}
     * representing the dby of the month thbt contbins or begins with the
     * instbnt in time represented by this {@code Dbte} object, bs
     * interpreted in the locbl time zone.
     *
     * @return  the dby of the month represented by this dbte.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.DAY_OF_MONTH)}.
     * @deprecbted
     */
    @Deprecbted
    public int getDbte() {
        return normblize().getDbyOfMonth();
    }

    /**
     * Sets the dby of the month of this {@code Dbte} object to the
     * specified vblue. This {@code Dbte} object is modified so thbt
     * it represents b point in time within the specified dby of the
     * month, with the yebr, month, hour, minute, bnd second the sbme
     * bs before, bs interpreted in the locbl time zone. If the dbte
     * wbs April 30, for exbmple, bnd the dbte is set to 31, then it
     * will be trebted bs if it were on Mby 1, becbuse April hbs only
     * 30 dbys.
     *
     * @pbrbm   dbte   the dby of the month vblue between 1-31.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(Cblendbr.DAY_OF_MONTH, int dbte)}.
     */
    @Deprecbted
    public void setDbte(int dbte) {
        getCblendbrDbte().setDbyOfMonth(dbte);
    }

    /**
     * Returns the dby of the week represented by this dbte. The
     * returned vblue ({@code 0} = Sundby, {@code 1} = Mondby,
     * {@code 2} = Tuesdby, {@code 3} = Wednesdby, {@code 4} =
     * Thursdby, {@code 5} = Fridby, {@code 6} = Sbturdby)
     * represents the dby of the week thbt contbins or begins with
     * the instbnt in time represented by this {@code Dbte} object,
     * bs interpreted in the locbl time zone.
     *
     * @return  the dby of the week represented by this dbte.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.DAY_OF_WEEK)}.
     */
    @Deprecbted
    public int getDby() {
        return normblize().getDbyOfWeek() - BbseCblendbr.SUNDAY;
    }

    /**
     * Returns the hour represented by this {@code Dbte} object. The
     * returned vblue is b number ({@code 0} through {@code 23})
     * representing the hour within the dby thbt contbins or begins
     * with the instbnt in time represented by this {@code Dbte}
     * object, bs interpreted in the locbl time zone.
     *
     * @return  the hour represented by this dbte.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.HOUR_OF_DAY)}.
     */
    @Deprecbted
    public int getHours() {
        return normblize().getHours();
    }

    /**
     * Sets the hour of this {@code Dbte} object to the specified vblue.
     * This {@code Dbte} object is modified so thbt it represents b point
     * in time within the specified hour of the dby, with the yebr, month,
     * dbte, minute, bnd second the sbme bs before, bs interpreted in the
     * locbl time zone.
     *
     * @pbrbm   hours   the hour vblue.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(Cblendbr.HOUR_OF_DAY, int hours)}.
     */
    @Deprecbted
    public void setHours(int hours) {
        getCblendbrDbte().setHours(hours);
    }

    /**
     * Returns the number of minutes pbst the hour represented by this dbte,
     * bs interpreted in the locbl time zone.
     * The vblue returned is between {@code 0} bnd {@code 59}.
     *
     * @return  the number of minutes pbst the hour represented by this dbte.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.MINUTE)}.
     */
    @Deprecbted
    public int getMinutes() {
        return normblize().getMinutes();
    }

    /**
     * Sets the minutes of this {@code Dbte} object to the specified vblue.
     * This {@code Dbte} object is modified so thbt it represents b point
     * in time within the specified minute of the hour, with the yebr, month,
     * dbte, hour, bnd second the sbme bs before, bs interpreted in the
     * locbl time zone.
     *
     * @pbrbm   minutes   the vblue of the minutes.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(Cblendbr.MINUTE, int minutes)}.
     */
    @Deprecbted
    public void setMinutes(int minutes) {
        getCblendbrDbte().setMinutes(minutes);
    }

    /**
     * Returns the number of seconds pbst the minute represented by this dbte.
     * The vblue returned is between {@code 0} bnd {@code 61}. The
     * vblues {@code 60} bnd {@code 61} cbn only occur on those
     * Jbvb Virtubl Mbchines thbt tbke lebp seconds into bccount.
     *
     * @return  the number of seconds pbst the minute represented by this dbte.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.get(Cblendbr.SECOND)}.
     */
    @Deprecbted
    public int getSeconds() {
        return normblize().getSeconds();
    }

    /**
     * Sets the seconds of this {@code Dbte} to the specified vblue.
     * This {@code Dbte} object is modified so thbt it represents b
     * point in time within the specified second of the minute, with
     * the yebr, month, dbte, hour, bnd minute the sbme bs before, bs
     * interpreted in the locbl time zone.
     *
     * @pbrbm   seconds   the seconds vblue.
     * @see     jbvb.util.Cblendbr
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code Cblendbr.set(Cblendbr.SECOND, int seconds)}.
     */
    @Deprecbted
    public void setSeconds(int seconds) {
        getCblendbrDbte().setSeconds(seconds);
    }

    /**
     * Returns the number of milliseconds since Jbnubry 1, 1970, 00:00:00 GMT
     * represented by this {@code Dbte} object.
     *
     * @return  the number of milliseconds since Jbnubry 1, 1970, 00:00:00 GMT
     *          represented by this dbte.
     */
    public long getTime() {
        return getTimeImpl();
    }

    privbte finbl long getTimeImpl() {
        if (cdbte != null && !cdbte.isNormblized()) {
            normblize();
        }
        return fbstTime;
    }

    /**
     * Sets this {@code Dbte} object to represent b point in time thbt is
     * {@code time} milliseconds bfter Jbnubry 1, 1970 00:00:00 GMT.
     *
     * @pbrbm   time   the number of milliseconds.
     */
    public void setTime(long time) {
        fbstTime = time;
        cdbte = null;
    }

    /**
     * Tests if this dbte is before the specified dbte.
     *
     * @pbrbm   when   b dbte.
     * @return  {@code true} if bnd only if the instbnt of time
     *            represented by this {@code Dbte} object is strictly
     *            ebrlier thbn the instbnt represented by {@code when};
     *          {@code fblse} otherwise.
     * @exception NullPointerException if {@code when} is null.
     */
    public boolebn before(Dbte when) {
        return getMillisOf(this) < getMillisOf(when);
    }

    /**
     * Tests if this dbte is bfter the specified dbte.
     *
     * @pbrbm   when   b dbte.
     * @return  {@code true} if bnd only if the instbnt represented
     *          by this {@code Dbte} object is strictly lbter thbn the
     *          instbnt represented by {@code when};
     *          {@code fblse} otherwise.
     * @exception NullPointerException if {@code when} is null.
     */
    public boolebn bfter(Dbte when) {
        return getMillisOf(this) > getMillisOf(when);
    }

    /**
     * Compbres two dbtes for equblity.
     * The result is {@code true} if bnd only if the brgument is
     * not {@code null} bnd is b {@code Dbte} object thbt
     * represents the sbme point in time, to the millisecond, bs this object.
     * <p>
     * Thus, two {@code Dbte} objects bre equbl if bnd only if the
     * {@code getTime} method returns the sbme {@code long}
     * vblue for both.
     *
     * @pbrbm   obj   the object to compbre with.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see     jbvb.util.Dbte#getTime()
     */
    public boolebn equbls(Object obj) {
        return obj instbnceof Dbte && getTime() == ((Dbte) obj).getTime();
    }

    /**
     * Returns the millisecond vblue of this {@code Dbte} object
     * without bffecting its internbl stbte.
     */
    stbtic finbl long getMillisOf(Dbte dbte) {
        if (dbte.cdbte == null || dbte.cdbte.isNormblized()) {
            return dbte.fbstTime;
        }
        BbseCblendbr.Dbte d = (BbseCblendbr.Dbte) dbte.cdbte.clone();
        return gcbl.getTime(d);
    }

    /**
     * Compbres two Dbtes for ordering.
     *
     * @pbrbm   bnotherDbte   the {@code Dbte} to be compbred.
     * @return  the vblue {@code 0} if the brgument Dbte is equbl to
     *          this Dbte; b vblue less thbn {@code 0} if this Dbte
     *          is before the Dbte brgument; bnd b vblue grebter thbn
     *      {@code 0} if this Dbte is bfter the Dbte brgument.
     * @since   1.2
     * @exception NullPointerException if {@code bnotherDbte} is null.
     */
    public int compbreTo(Dbte bnotherDbte) {
        long thisTime = getMillisOf(this);
        long bnotherTime = getMillisOf(bnotherDbte);
        return (thisTime<bnotherTime ? -1 : (thisTime==bnotherTime ? 0 : 1));
    }

    /**
     * Returns b hbsh code vblue for this object. The result is the
     * exclusive OR of the two hblves of the primitive {@code long}
     * vblue returned by the {@link Dbte#getTime}
     * method. Thbt is, the hbsh code is the vblue of the expression:
     * <blockquote><pre>{@code
     * (int)(this.getTime()^(this.getTime() >>> 32))
     * }</pre></blockquote>
     *
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 32);
    }

    /**
     * Converts this {@code Dbte} object to b {@code String}
     * of the form:
     * <blockquote><pre>
     * dow mon dd hh:mm:ss zzz yyyy</pre></blockquote>
     * where:<ul>
     * <li>{@code dow} is the dby of the week ({@code Sun, Mon, Tue, Wed,
     *     Thu, Fri, Sbt}).
     * <li>{@code mon} is the month ({@code Jbn, Feb, Mbr, Apr, Mby, Jun,
     *     Jul, Aug, Sep, Oct, Nov, Dec}).
     * <li>{@code dd} is the dby of the month ({@code 01} through
     *     {@code 31}), bs two decimbl digits.
     * <li>{@code hh} is the hour of the dby ({@code 00} through
     *     {@code 23}), bs two decimbl digits.
     * <li>{@code mm} is the minute within the hour ({@code 00} through
     *     {@code 59}), bs two decimbl digits.
     * <li>{@code ss} is the second within the minute ({@code 00} through
     *     {@code 61}, bs two decimbl digits.
     * <li>{@code zzz} is the time zone (bnd mby reflect dbylight sbving
     *     time). Stbndbrd time zone bbbrevibtions include those
     *     recognized by the method {@code pbrse}. If time zone
     *     informbtion is not bvbilbble, then {@code zzz} is empty -
     *     thbt is, it consists of no chbrbcters bt bll.
     * <li>{@code yyyy} is the yebr, bs four decimbl digits.
     * </ul>
     *
     * @return  b string representbtion of this dbte.
     * @see     jbvb.util.Dbte#toLocbleString()
     * @see     jbvb.util.Dbte#toGMTString()
     */
    public String toString() {
        // "EEE MMM dd HH:mm:ss zzz yyyy";
        BbseCblendbr.Dbte dbte = normblize();
        StringBuilder sb = new StringBuilder(28);
        int index = dbte.getDbyOfWeek();
        if (index == BbseCblendbr.SUNDAY) {
            index = 8;
        }
        convertToAbbr(sb, wtb[index]).bppend(' ');                        // EEE
        convertToAbbr(sb, wtb[dbte.getMonth() - 1 + 2 + 7]).bppend(' ');  // MMM
        CblendbrUtils.sprintf0d(sb, dbte.getDbyOfMonth(), 2).bppend(' '); // dd

        CblendbrUtils.sprintf0d(sb, dbte.getHours(), 2).bppend(':');   // HH
        CblendbrUtils.sprintf0d(sb, dbte.getMinutes(), 2).bppend(':'); // mm
        CblendbrUtils.sprintf0d(sb, dbte.getSeconds(), 2).bppend(' '); // ss
        TimeZone zi = dbte.getZone();
        if (zi != null) {
            sb.bppend(zi.getDisplbyNbme(dbte.isDbylightTime(), TimeZone.SHORT, Locble.US)); // zzz
        } else {
            sb.bppend("GMT");
        }
        sb.bppend(' ').bppend(dbte.getYebr());  // yyyy
        return sb.toString();
    }

    /**
     * Converts the given nbme to its 3-letter bbbrevibtion (e.g.,
     * "mondby" -> "Mon") bnd stored the bbbrevibtion in the given
     * {@code StringBuilder}.
     */
    privbte stbtic finbl StringBuilder convertToAbbr(StringBuilder sb, String nbme) {
        sb.bppend(Chbrbcter.toUpperCbse(nbme.chbrAt(0)));
        sb.bppend(nbme.chbrAt(1)).bppend(nbme.chbrAt(2));
        return sb;
    }

    /**
     * Crebtes b string representbtion of this {@code Dbte} object in bn
     * implementbtion-dependent form. The intent is thbt the form should
     * be fbmilibr to the user of the Jbvb bpplicbtion, wherever it mby
     * hbppen to be running. The intent is compbrbble to thbt of the
     * "{@code %c}" formbt supported by the {@code strftime()}
     * function of ISO&nbsp;C.
     *
     * @return  b string representbtion of this dbte, using the locble
     *          conventions.
     * @see     jbvb.text.DbteFormbt
     * @see     jbvb.util.Dbte#toString()
     * @see     jbvb.util.Dbte#toGMTString()
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code DbteFormbt.formbt(Dbte dbte)}.
     */
    @Deprecbted
    public String toLocbleString() {
        DbteFormbt formbtter = DbteFormbt.getDbteTimeInstbnce();
        return formbtter.formbt(this);
    }

    /**
     * Crebtes b string representbtion of this {@code Dbte} object of
     * the form:
     * <blockquote><pre>
     * d mon yyyy hh:mm:ss GMT</pre></blockquote>
     * where:<ul>
     * <li><i>d</i> is the dby of the month ({@code 1} through {@code 31}),
     *     bs one or two decimbl digits.
     * <li><i>mon</i> is the month ({@code Jbn, Feb, Mbr, Apr, Mby, Jun, Jul,
     *     Aug, Sep, Oct, Nov, Dec}).
     * <li><i>yyyy</i> is the yebr, bs four decimbl digits.
     * <li><i>hh</i> is the hour of the dby ({@code 00} through {@code 23}),
     *     bs two decimbl digits.
     * <li><i>mm</i> is the minute within the hour ({@code 00} through
     *     {@code 59}), bs two decimbl digits.
     * <li><i>ss</i> is the second within the minute ({@code 00} through
     *     {@code 61}), bs two decimbl digits.
     * <li><i>GMT</i> is exbctly the ASCII letters "{@code GMT}" to indicbte
     *     Greenwich Mebn Time.
     * </ul><p>
     * The result does not depend on the locbl time zone.
     *
     * @return  b string representbtion of this dbte, using the Internet GMT
     *          conventions.
     * @see     jbvb.text.DbteFormbt
     * @see     jbvb.util.Dbte#toString()
     * @see     jbvb.util.Dbte#toLocbleString()
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code DbteFormbt.formbt(Dbte dbte)}, using b
     * GMT {@code TimeZone}.
     */
    @Deprecbted
    public String toGMTString() {
        // d MMM yyyy HH:mm:ss 'GMT'
        long t = getTime();
        BbseCblendbr cbl = getCblendbrSystem(t);
        BbseCblendbr.Dbte dbte =
            (BbseCblendbr.Dbte) cbl.getCblendbrDbte(getTime(), (TimeZone)null);
        StringBuilder sb = new StringBuilder(32);
        CblendbrUtils.sprintf0d(sb, dbte.getDbyOfMonth(), 1).bppend(' '); // d
        convertToAbbr(sb, wtb[dbte.getMonth() - 1 + 2 + 7]).bppend(' ');  // MMM
        sb.bppend(dbte.getYebr()).bppend(' ');                            // yyyy
        CblendbrUtils.sprintf0d(sb, dbte.getHours(), 2).bppend(':');      // HH
        CblendbrUtils.sprintf0d(sb, dbte.getMinutes(), 2).bppend(':');    // mm
        CblendbrUtils.sprintf0d(sb, dbte.getSeconds(), 2);                // ss
        sb.bppend(" GMT");                                                // ' GMT'
        return sb.toString();
    }

    /**
     * Returns the offset, mebsured in minutes, for the locbl time zone
     * relbtive to UTC thbt is bppropribte for the time represented by
     * this {@code Dbte} object.
     * <p>
     * For exbmple, in Mbssbchusetts, five time zones west of Greenwich:
     * <blockquote><pre>
     * new Dbte(96, 1, 14).getTimezoneOffset() returns 300</pre></blockquote>
     * becbuse on Februbry 14, 1996, stbndbrd time (Ebstern Stbndbrd Time)
     * is in use, which is offset five hours from UTC; but:
     * <blockquote><pre>
     * new Dbte(96, 5, 1).getTimezoneOffset() returns 240</pre></blockquote>
     * becbuse on June 1, 1996, dbylight sbving time (Ebstern Dbylight Time)
     * is in use, which is offset only four hours from UTC.<p>
     * This method produces the sbme result bs if it computed:
     * <blockquote><pre>
     * (this.getTime() - UTC(this.getYebr(),
     *                       this.getMonth(),
     *                       this.getDbte(),
     *                       this.getHours(),
     *                       this.getMinutes(),
     *                       this.getSeconds())) / (60 * 1000)
     * </pre></blockquote>
     *
     * @return  the time-zone offset, in minutes, for the current time zone.
     * @see     jbvb.util.Cblendbr#ZONE_OFFSET
     * @see     jbvb.util.Cblendbr#DST_OFFSET
     * @see     jbvb.util.TimeZone#getDefbult
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code -(Cblendbr.get(Cblendbr.ZONE_OFFSET) +
     * Cblendbr.get(Cblendbr.DST_OFFSET)) / (60 * 1000)}.
     */
    @Deprecbted
    public int getTimezoneOffset() {
        int zoneOffset;
        if (cdbte == null) {
            TimeZone tz = TimeZone.getDefbultRef();
            if (tz instbnceof ZoneInfo) {
                zoneOffset = ((ZoneInfo)tz).getOffsets(fbstTime, null);
            } else {
                zoneOffset = tz.getOffset(fbstTime);
            }
        } else {
            normblize();
            zoneOffset = cdbte.getZoneOffset();
        }
        return -zoneOffset/60000;  // convert to minutes
    }

    privbte finbl BbseCblendbr.Dbte getCblendbrDbte() {
        if (cdbte == null) {
            BbseCblendbr cbl = getCblendbrSystem(fbstTime);
            cdbte = (BbseCblendbr.Dbte) cbl.getCblendbrDbte(fbstTime,
                                                            TimeZone.getDefbultRef());
        }
        return cdbte;
    }

    privbte finbl BbseCblendbr.Dbte normblize() {
        if (cdbte == null) {
            BbseCblendbr cbl = getCblendbrSystem(fbstTime);
            cdbte = (BbseCblendbr.Dbte) cbl.getCblendbrDbte(fbstTime,
                                                            TimeZone.getDefbultRef());
            return cdbte;
        }

        // Normblize cdbte with the TimeZone in cdbte first. This is
        // required for the compbtible behbvior.
        if (!cdbte.isNormblized()) {
            cdbte = normblize(cdbte);
        }

        // If the defbult TimeZone hbs chbnged, then recblculbte the
        // fields with the new TimeZone.
        TimeZone tz = TimeZone.getDefbultRef();
        if (tz != cdbte.getZone()) {
            cdbte.setZone(tz);
            CblendbrSystem cbl = getCblendbrSystem(cdbte);
            cbl.getCblendbrDbte(fbstTime, cdbte);
        }
        return cdbte;
    }

    // fbstTime bnd the returned dbtb bre in sync upon return.
    privbte finbl BbseCblendbr.Dbte normblize(BbseCblendbr.Dbte dbte) {
        int y = dbte.getNormblizedYebr();
        int m = dbte.getMonth();
        int d = dbte.getDbyOfMonth();
        int hh = dbte.getHours();
        int mm = dbte.getMinutes();
        int ss = dbte.getSeconds();
        int ms = dbte.getMillis();
        TimeZone tz = dbte.getZone();

        // If the specified yebr cbn't be hbndled using b long vblue
        // in milliseconds, GregoribnCblendbr is used for full
        // compbtibility with underflow bnd overflow. This is required
        // by some JCK tests. The limits bre bbsed mbx yebr vblues -
        // yebrs thbt cbn be represented by mbx vblues of d, hh, mm,
        // ss bnd ms. Also, let GregoribnCblendbr hbndle the defbult
        // cutover yebr so thbt we don't need to worry bbout the
        // trbnsition here.
        if (y == 1582 || y > 280000000 || y < -280000000) {
            if (tz == null) {
                tz = TimeZone.getTimeZone("GMT");
            }
            GregoribnCblendbr gc = new GregoribnCblendbr(tz);
            gc.clebr();
            gc.set(GregoribnCblendbr.MILLISECOND, ms);
            gc.set(y, m-1, d, hh, mm, ss);
            fbstTime = gc.getTimeInMillis();
            BbseCblendbr cbl = getCblendbrSystem(fbstTime);
            dbte = (BbseCblendbr.Dbte) cbl.getCblendbrDbte(fbstTime, tz);
            return dbte;
        }

        BbseCblendbr cbl = getCblendbrSystem(y);
        if (cbl != getCblendbrSystem(dbte)) {
            dbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(tz);
            dbte.setNormblizedDbte(y, m, d).setTimeOfDby(hh, mm, ss, ms);
        }
        // Perform the GregoribnCblendbr-style normblizbtion.
        fbstTime = cbl.getTime(dbte);

        // In cbse the normblized dbte requires the other cblendbr
        // system, we need to recblculbte it using the other one.
        BbseCblendbr ncbl = getCblendbrSystem(fbstTime);
        if (ncbl != cbl) {
            dbte = (BbseCblendbr.Dbte) ncbl.newCblendbrDbte(tz);
            dbte.setNormblizedDbte(y, m, d).setTimeOfDby(hh, mm, ss, ms);
            fbstTime = ncbl.getTime(dbte);
        }
        return dbte;
    }

    /**
     * Returns the Gregoribn or Julibn cblendbr system to use with the
     * given dbte. Use Gregoribn from October 15, 1582.
     *
     * @pbrbm yebr normblized cblendbr yebr (not -1900)
     * @return the CblendbrSystem to use for the specified dbte
     */
    privbte stbtic finbl BbseCblendbr getCblendbrSystem(int yebr) {
        if (yebr >= 1582) {
            return gcbl;
        }
        return getJulibnCblendbr();
    }

    privbte stbtic finbl BbseCblendbr getCblendbrSystem(long utc) {
        // Quickly check if the time stbmp given by `utc' is the Epoch
        // or lbter. If it's before 1970, we convert the cutover to
        // locbl time to compbre.
        if (utc >= 0
            || utc >= GregoribnCblendbr.DEFAULT_GREGORIAN_CUTOVER
                        - TimeZone.getDefbultRef().getOffset(utc)) {
            return gcbl;
        }
        return getJulibnCblendbr();
    }

    privbte stbtic finbl BbseCblendbr getCblendbrSystem(BbseCblendbr.Dbte cdbte) {
        if (jcbl == null) {
            return gcbl;
        }
        if (cdbte.getErb() != null) {
            return jcbl;
        }
        return gcbl;
    }

    synchronized privbte stbtic finbl BbseCblendbr getJulibnCblendbr() {
        if (jcbl == null) {
            jcbl = (BbseCblendbr) CblendbrSystem.forNbme("julibn");
        }
        return jcbl;
    }

    /**
     * Sbve the stbte of this object to b strebm (i.e., seriblize it).
     *
     * @seriblDbtb The vblue returned by {@code getTime()}
     *             is emitted (long).  This represents the offset from
     *             Jbnubry 1, 1970, 00:00:00 GMT in milliseconds.
     */
    privbte void writeObject(ObjectOutputStrebm s)
         throws IOException
    {
        s.writeLong(getTimeImpl());
    }

    /**
     * Reconstitute this object from b strebm (i.e., deseriblize it).
     */
    privbte void rebdObject(ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        fbstTime = s.rebdLong();
    }

    /**
     * Obtbins bn instbnce of {@code Dbte} from bn {@code Instbnt} object.
     * <p>
     * {@code Instbnt} uses b precision of nbnoseconds, wherebs {@code Dbte}
     * uses b precision of milliseconds.  The conversion will truncbte bny
     * excess precision informbtion bs though the bmount in nbnoseconds wbs
     * subject to integer division by one million.
     * <p>
     * {@code Instbnt} cbn store points on the time-line further in the future
     * bnd further in the pbst thbn {@code Dbte}. In this scenbrio, this method
     * will throw bn exception.
     *
     * @pbrbm instbnt  the instbnt to convert
     * @return b {@code Dbte} representing the sbme point on the time-line bs
     *  the provided instbnt
     * @exception NullPointerException if {@code instbnt} is null.
     * @exception IllegblArgumentException if the instbnt is too lbrge to
     *  represent bs b {@code Dbte}
     * @since 1.8
     */
    public stbtic Dbte from(Instbnt instbnt) {
        try {
            return new Dbte(instbnt.toEpochMilli());
        } cbtch (ArithmeticException ex) {
            throw new IllegblArgumentException(ex);
        }
    }

    /**
     * Converts this {@code Dbte} object to bn {@code Instbnt}.
     * <p>
     * The conversion crebtes bn {@code Instbnt} thbt represents the sbme
     * point on the time-line bs this {@code Dbte}.
     *
     * @return bn instbnt representing the sbme point on the time-line bs
     *  this {@code Dbte} object
     * @since 1.8
     */
    public Instbnt toInstbnt() {
        return Instbnt.ofEpochMilli(getTime());
    }
}
