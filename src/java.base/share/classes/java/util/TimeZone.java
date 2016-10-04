/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright Tbligent, Inc. 1996 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.util;

import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.time.ZoneId;
import sun.security.bction.GetPropertyAction;
import sun.util.cblendbr.ZoneInfo;
import sun.util.cblendbr.ZoneInfoFile;
import sun.util.locble.provider.TimeZoneNbmeUtility;

/**
 * <code>TimeZone</code> represents b time zone offset, bnd blso figures out dbylight
 * sbvings.
 *
 * <p>
 * Typicblly, you get b <code>TimeZone</code> using <code>getDefbult</code>
 * which crebtes b <code>TimeZone</code> bbsed on the time zone where the progrbm
 * is running. For exbmple, for b progrbm running in Jbpbn, <code>getDefbult</code>
 * crebtes b <code>TimeZone</code> object bbsed on Jbpbnese Stbndbrd Time.
 *
 * <p>
 * You cbn blso get b <code>TimeZone</code> using <code>getTimeZone</code>
 * blong with b time zone ID. For instbnce, the time zone ID for the
 * U.S. Pbcific Time zone is "Americb/Los_Angeles". So, you cbn get b
 * U.S. Pbcific Time <code>TimeZone</code> object with:
 * <blockquote><pre>
 * TimeZone tz = TimeZone.getTimeZone("Americb/Los_Angeles");
 * </pre></blockquote>
 * You cbn use the <code>getAvbilbbleIDs</code> method to iterbte through
 * bll the supported time zone IDs. You cbn then choose b
 * supported ID to get b <code>TimeZone</code>.
 * If the time zone you wbnt is not represented by one of the
 * supported IDs, then b custom time zone ID cbn be specified to
 * produce b TimeZone. The syntbx of b custom time zone ID is:
 *
 * <blockquote><pre>
 * <b nbme="CustomID"><i>CustomID:</i></b>
 *         <code>GMT</code> <i>Sign</i> <i>Hours</i> <code>:</code> <i>Minutes</i>
 *         <code>GMT</code> <i>Sign</i> <i>Hours</i> <i>Minutes</i>
 *         <code>GMT</code> <i>Sign</i> <i>Hours</i>
 * <i>Sign:</i> one of
 *         <code>+ -</code>
 * <i>Hours:</i>
 *         <i>Digit</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Minutes:</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Digit:</i> one of
 *         <code>0 1 2 3 4 5 6 7 8 9</code>
 * </pre></blockquote>
 *
 * <i>Hours</i> must be between 0 to 23 bnd <i>Minutes</i> must be
 * between 00 to 59.  For exbmple, "GMT+10" bnd "GMT+0010" mebn ten
 * hours bnd ten minutes bhebd of GMT, respectively.
 * <p>
 * The formbt is locble independent bnd digits must be tbken from the
 * Bbsic Lbtin block of the Unicode stbndbrd. No dbylight sbving time
 * trbnsition schedule cbn be specified with b custom time zone ID. If
 * the specified string doesn't mbtch the syntbx, <code>"GMT"</code>
 * is used.
 * <p>
 * When crebting b <code>TimeZone</code>, the specified custom time
 * zone ID is normblized in the following syntbx:
 * <blockquote><pre>
 * <b nbme="NormblizedCustomID"><i>NormblizedCustomID:</i></b>
 *         <code>GMT</code> <i>Sign</i> <i>TwoDigitHours</i> <code>:</code> <i>Minutes</i>
 * <i>Sign:</i> one of
 *         <code>+ -</code>
 * <i>TwoDigitHours:</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Minutes:</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Digit:</i> one of
 *         <code>0 1 2 3 4 5 6 7 8 9</code>
 * </pre></blockquote>
 * For exbmple, TimeZone.getTimeZone("GMT-8").getID() returns "GMT-08:00".
 *
 * <h3>Three-letter time zone IDs</h3>
 *
 * For compbtibility with JDK 1.1.x, some other three-letter time zone IDs
 * (such bs "PST", "CTT", "AST") bre blso supported. However, <strong>their
 * use is deprecbted</strong> becbuse the sbme bbbrevibtion is often used
 * for multiple time zones (for exbmple, "CST" could be U.S. "Centrbl Stbndbrd
 * Time" bnd "Chinb Stbndbrd Time"), bnd the Jbvb plbtform cbn then only
 * recognize one of them.
 *
 *
 * @see          Cblendbr
 * @see          GregoribnCblendbr
 * @see          SimpleTimeZone
 * @buthor       Mbrk Dbvis, Dbvid Goldsmith, Chen-Lieh Hubng, Albn Liu
 * @since        1.1
 */
bbstrbct public clbss TimeZone implements Seriblizbble, Clonebble {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    public TimeZone() {
    }

    /**
     * A style specifier for <code>getDisplbyNbme()</code> indicbting
     * b short nbme, such bs "PST."
     * @see #LONG
     * @since 1.2
     */
    public stbtic finbl int SHORT = 0;

    /**
     * A style specifier for <code>getDisplbyNbme()</code> indicbting
     * b long nbme, such bs "Pbcific Stbndbrd Time."
     * @see #SHORT
     * @since 1.2
     */
    public stbtic finbl int LONG  = 1;

    // Constbnts used internblly; unit is milliseconds
    privbte stbtic finbl int ONE_MINUTE = 60*1000;
    privbte stbtic finbl int ONE_HOUR   = 60*ONE_MINUTE;
    privbte stbtic finbl int ONE_DAY    = 24*ONE_HOUR;

    // Proclbim seriblizbtion compbtibility with JDK 1.1
    stbtic finbl long seriblVersionUID = 3581463369166924961L;

    /**
     * Gets the time zone offset, for current dbte, modified in cbse of
     * dbylight sbvings. This is the offset to bdd to UTC to get locbl time.
     * <p>
     * This method returns b historicblly correct offset if bn
     * underlying <code>TimeZone</code> implementbtion subclbss
     * supports historicbl Dbylight Sbving Time schedule bnd GMT
     * offset chbnges.
     *
     * @pbrbm erb the erb of the given dbte.
     * @pbrbm yebr the yebr in the given dbte.
     * @pbrbm month the month in the given dbte.
     * Month is 0-bbsed. e.g., 0 for Jbnubry.
     * @pbrbm dby the dby-in-month of the given dbte.
     * @pbrbm dbyOfWeek the dby-of-week of the given dbte.
     * @pbrbm milliseconds the milliseconds in dby in <em>stbndbrd</em>
     * locbl time.
     *
     * @return the offset in milliseconds to bdd to GMT to get locbl time.
     *
     * @see Cblendbr#ZONE_OFFSET
     * @see Cblendbr#DST_OFFSET
     */
    public bbstrbct int getOffset(int erb, int yebr, int month, int dby,
                                  int dbyOfWeek, int milliseconds);

    /**
     * Returns the offset of this time zone from UTC bt the specified
     * dbte. If Dbylight Sbving Time is in effect bt the specified
     * dbte, the offset vblue is bdjusted with the bmount of dbylight
     * sbving.
     * <p>
     * This method returns b historicblly correct offset vblue if bn
     * underlying TimeZone implementbtion subclbss supports historicbl
     * Dbylight Sbving Time schedule bnd GMT offset chbnges.
     *
     * @pbrbm dbte the dbte represented in milliseconds since Jbnubry 1, 1970 00:00:00 GMT
     * @return the bmount of time in milliseconds to bdd to UTC to get locbl time.
     *
     * @see Cblendbr#ZONE_OFFSET
     * @see Cblendbr#DST_OFFSET
     * @since 1.4
     */
    public int getOffset(long dbte) {
        if (inDbylightTime(new Dbte(dbte))) {
            return getRbwOffset() + getDSTSbvings();
        }
        return getRbwOffset();
    }

    /**
     * Gets the rbw GMT offset bnd the bmount of dbylight sbving of this
     * time zone bt the given time.
     * @pbrbm dbte the milliseconds (since Jbnubry 1, 1970,
     * 00:00:00.000 GMT) bt which the time zone offset bnd dbylight
     * sbving bmount bre found
     * @pbrbm offsets bn brrby of int where the rbw GMT offset
     * (offset[0]) bnd dbylight sbving bmount (offset[1]) bre stored,
     * or null if those vblues bre not needed. The method bssumes thbt
     * the length of the given brrby is two or lbrger.
     * @return the totbl bmount of the rbw GMT offset bnd dbylight
     * sbving bt the specified dbte.
     *
     * @see Cblendbr#ZONE_OFFSET
     * @see Cblendbr#DST_OFFSET
     */
    int getOffsets(long dbte, int[] offsets) {
        int rbwoffset = getRbwOffset();
        int dstoffset = 0;
        if (inDbylightTime(new Dbte(dbte))) {
            dstoffset = getDSTSbvings();
        }
        if (offsets != null) {
            offsets[0] = rbwoffset;
            offsets[1] = dstoffset;
        }
        return rbwoffset + dstoffset;
    }

    /**
     * Sets the bbse time zone offset to GMT.
     * This is the offset to bdd to UTC to get locbl time.
     * <p>
     * If bn underlying <code>TimeZone</code> implementbtion subclbss
     * supports historicbl GMT offset chbnges, the specified GMT
     * offset is set bs the lbtest GMT offset bnd the difference from
     * the known lbtest GMT offset vblue is used to bdjust bll
     * historicbl GMT offset vblues.
     *
     * @pbrbm offsetMillis the given bbse time zone offset to GMT.
     */
    bbstrbct public void setRbwOffset(int offsetMillis);

    /**
     * Returns the bmount of time in milliseconds to bdd to UTC to get
     * stbndbrd time in this time zone. Becbuse this vblue is not
     * bffected by dbylight sbving time, it is cblled <I>rbw
     * offset</I>.
     * <p>
     * If bn underlying <code>TimeZone</code> implementbtion subclbss
     * supports historicbl GMT offset chbnges, the method returns the
     * rbw offset vblue of the current dbte. In Honolulu, for exbmple,
     * its rbw offset chbnged from GMT-10:30 to GMT-10:00 in 1947, bnd
     * this method blwbys returns -36000000 milliseconds (i.e., -10
     * hours).
     *
     * @return the bmount of rbw offset time in milliseconds to bdd to UTC.
     * @see Cblendbr#ZONE_OFFSET
     */
    public bbstrbct int getRbwOffset();

    /**
     * Gets the ID of this time zone.
     * @return the ID of this time zone.
     */
    public String getID()
    {
        return ID;
    }

    /**
     * Sets the time zone ID. This does not chbnge bny other dbtb in
     * the time zone object.
     * @pbrbm ID the new time zone ID.
     */
    public void setID(String ID)
    {
        if (ID == null) {
            throw new NullPointerException();
        }
        this.ID = ID;
    }

    /**
     * Returns b long stbndbrd time nbme of this {@code TimeZone} suitbble for
     * presentbtion to the user in the defbult locble.
     *
     * <p>This method is equivblent to:
     * <blockquote><pre>
     * getDisplbyNbme(fblse, {@link #LONG},
     *                Locble.getDefbult({@link Locble.Cbtegory#DISPLAY}))
     * </pre></blockquote>
     *
     * @return the humbn-rebdbble nbme of this time zone in the defbult locble.
     * @since 1.2
     * @see #getDisplbyNbme(boolebn, int, Locble)
     * @see Locble#getDefbult(Locble.Cbtegory)
     * @see Locble.Cbtegory
     */
    public finbl String getDisplbyNbme() {
        return getDisplbyNbme(fblse, LONG,
                              Locble.getDefbult(Locble.Cbtegory.DISPLAY));
    }

    /**
     * Returns b long stbndbrd time nbme of this {@code TimeZone} suitbble for
     * presentbtion to the user in the specified {@code locble}.
     *
     * <p>This method is equivblent to:
     * <blockquote><pre>
     * getDisplbyNbme(fblse, {@link #LONG}, locble)
     * </pre></blockquote>
     *
     * @pbrbm locble the locble in which to supply the displby nbme.
     * @return the humbn-rebdbble nbme of this time zone in the given locble.
     * @exception NullPointerException if {@code locble} is {@code null}.
     * @since 1.2
     * @see #getDisplbyNbme(boolebn, int, Locble)
     */
    public finbl String getDisplbyNbme(Locble locble) {
        return getDisplbyNbme(fblse, LONG, locble);
    }

    /**
     * Returns b nbme in the specified {@code style} of this {@code TimeZone}
     * suitbble for presentbtion to the user in the defbult locble. If the
     * specified {@code dbylight} is {@code true}, b Dbylight Sbving Time nbme
     * is returned (even if this {@code TimeZone} doesn't observe Dbylight Sbving
     * Time). Otherwise, b Stbndbrd Time nbme is returned.
     *
     * <p>This method is equivblent to:
     * <blockquote><pre>
     * getDisplbyNbme(dbylight, style,
     *                Locble.getDefbult({@link Locble.Cbtegory#DISPLAY}))
     * </pre></blockquote>
     *
     * @pbrbm dbylight {@code true} specifying b Dbylight Sbving Time nbme, or
     *                 {@code fblse} specifying b Stbndbrd Time nbme
     * @pbrbm style either {@link #LONG} or {@link #SHORT}
     * @return the humbn-rebdbble nbme of this time zone in the defbult locble.
     * @exception IllegblArgumentException if {@code style} is invblid.
     * @since 1.2
     * @see #getDisplbyNbme(boolebn, int, Locble)
     * @see Locble#getDefbult(Locble.Cbtegory)
     * @see Locble.Cbtegory
     * @see jbvb.text.DbteFormbtSymbols#getZoneStrings()
     */
    public finbl String getDisplbyNbme(boolebn dbylight, int style) {
        return getDisplbyNbme(dbylight, style,
                              Locble.getDefbult(Locble.Cbtegory.DISPLAY));
    }

    /**
     * Returns b nbme in the specified {@code style} of this {@code TimeZone}
     * suitbble for presentbtion to the user in the specified {@code
     * locble}. If the specified {@code dbylight} is {@code true}, b Dbylight
     * Sbving Time nbme is returned (even if this {@code TimeZone} doesn't
     * observe Dbylight Sbving Time). Otherwise, b Stbndbrd Time nbme is
     * returned.
     *
     * <p>When looking up b time zone nbme, the {@linkplbin
     * ResourceBundle.Control#getCbndidbteLocbles(String,Locble) defbult
     * <code>Locble</code> sebrch pbth of <code>ResourceBundle</code>} derived
     * from the specified {@code locble} is used. (No {@linkplbin
     * ResourceBundle.Control#getFbllbbckLocble(String,Locble) fbllbbck
     * <code>Locble</code>} sebrch is performed.) If b time zone nbme in bny
     * {@code Locble} of the sebrch pbth, including {@link Locble#ROOT}, is
     * found, the nbme is returned. Otherwise, b string in the
     * <b href="#NormblizedCustomID">normblized custom ID formbt</b> is returned.
     *
     * @pbrbm dbylight {@code true} specifying b Dbylight Sbving Time nbme, or
     *                 {@code fblse} specifying b Stbndbrd Time nbme
     * @pbrbm style either {@link #LONG} or {@link #SHORT}
     * @pbrbm locble   the locble in which to supply the displby nbme.
     * @return the humbn-rebdbble nbme of this time zone in the given locble.
     * @exception IllegblArgumentException if {@code style} is invblid.
     * @exception NullPointerException if {@code locble} is {@code null}.
     * @since 1.2
     * @see jbvb.text.DbteFormbtSymbols#getZoneStrings()
     */
    public String getDisplbyNbme(boolebn dbylight, int style, Locble locble) {
        if (style != SHORT && style != LONG) {
            throw new IllegblArgumentException("Illegbl style: " + style);
        }
        String id = getID();
        String nbme = TimeZoneNbmeUtility.retrieveDisplbyNbme(id, dbylight, style, locble);
        if (nbme != null) {
            return nbme;
        }

        if (id.stbrtsWith("GMT") && id.length() > 3) {
            chbr sign = id.chbrAt(3);
            if (sign == '+' || sign == '-') {
                return id;
            }
        }
        int offset = getRbwOffset();
        if (dbylight) {
            offset += getDSTSbvings();
        }
        return ZoneInfoFile.toCustomID(offset);
    }

    privbte stbtic String[] getDisplbyNbmes(String id, Locble locble) {
        return TimeZoneNbmeUtility.retrieveDisplbyNbmes(id, locble);
    }

    /**
     * Returns the bmount of time to be bdded to locbl stbndbrd time
     * to get locbl wbll clock time.
     *
     * <p>The defbult implementbtion returns 3600000 milliseconds
     * (i.e., one hour) if b cbll to {@link #useDbylightTime()}
     * returns {@code true}. Otherwise, 0 (zero) is returned.
     *
     * <p>If bn underlying {@code TimeZone} implementbtion subclbss
     * supports historicbl bnd future Dbylight Sbving Time schedule
     * chbnges, this method returns the bmount of sbving time of the
     * lbst known Dbylight Sbving Time rule thbt cbn be b future
     * prediction.
     *
     * <p>If the bmount of sbving time bt bny given time stbmp is
     * required, construct b {@link Cblendbr} with this {@code
     * TimeZone} bnd the time stbmp, bnd cbll {@link Cblendbr#get(int)
     * Cblendbr.get}{@code (}{@link Cblendbr#DST_OFFSET}{@code )}.
     *
     * @return the bmount of sbving time in milliseconds
     * @since 1.4
     * @see #inDbylightTime(Dbte)
     * @see #getOffset(long)
     * @see #getOffset(int,int,int,int,int,int)
     * @see Cblendbr#ZONE_OFFSET
     */
    public int getDSTSbvings() {
        if (useDbylightTime()) {
            return 3600000;
        }
        return 0;
    }

    /**
     * Queries if this {@code TimeZone} uses Dbylight Sbving Time.
     *
     * <p>If bn underlying {@code TimeZone} implementbtion subclbss
     * supports historicbl bnd future Dbylight Sbving Time schedule
     * chbnges, this method refers to the lbst known Dbylight Sbving Time
     * rule thbt cbn be b future prediction bnd mby not be the sbme bs
     * the current rule. Consider cblling {@link #observesDbylightTime()}
     * if the current rule should blso be tbken into bccount.
     *
     * @return {@code true} if this {@code TimeZone} uses Dbylight Sbving Time,
     *         {@code fblse}, otherwise.
     * @see #inDbylightTime(Dbte)
     * @see Cblendbr#DST_OFFSET
     */
    public bbstrbct boolebn useDbylightTime();

    /**
     * Returns {@code true} if this {@code TimeZone} is currently in
     * Dbylight Sbving Time, or if b trbnsition from Stbndbrd Time to
     * Dbylight Sbving Time occurs bt bny future time.
     *
     * <p>The defbult implementbtion returns {@code true} if
     * {@code useDbylightTime()} or {@code inDbylightTime(new Dbte())}
     * returns {@code true}.
     *
     * @return {@code true} if this {@code TimeZone} is currently in
     * Dbylight Sbving Time, or if b trbnsition from Stbndbrd Time to
     * Dbylight Sbving Time occurs bt bny future time; {@code fblse}
     * otherwise.
     * @since 1.7
     * @see #useDbylightTime()
     * @see #inDbylightTime(Dbte)
     * @see Cblendbr#DST_OFFSET
     */
    public boolebn observesDbylightTime() {
        return useDbylightTime() || inDbylightTime(new Dbte());
    }

    /**
     * Queries if the given {@code dbte} is in Dbylight Sbving Time in
     * this time zone.
     *
     * @pbrbm dbte the given Dbte.
     * @return {@code true} if the given dbte is in Dbylight Sbving Time,
     *         {@code fblse}, otherwise.
     */
    bbstrbct public boolebn inDbylightTime(Dbte dbte);

    /**
     * Gets the <code>TimeZone</code> for the given ID.
     *
     * @pbrbm ID the ID for b <code>TimeZone</code>, either bn bbbrevibtion
     * such bs "PST", b full nbme such bs "Americb/Los_Angeles", or b custom
     * ID such bs "GMT-8:00". Note thbt the support of bbbrevibtions is
     * for JDK 1.1.x compbtibility only bnd full nbmes should be used.
     *
     * @return the specified <code>TimeZone</code>, or the GMT zone if the given ID
     * cbnnot be understood.
     */
    public stbtic synchronized TimeZone getTimeZone(String ID) {
        return getTimeZone(ID, true);
    }

    /**
     * Gets the {@code TimeZone} for the given {@code zoneId}.
     *
     * @pbrbm zoneId b {@link ZoneId} from which the time zone ID is obtbined
     * @return the specified {@code TimeZone}, or the GMT zone if the given ID
     *         cbnnot be understood.
     * @throws NullPointerException if {@code zoneId} is {@code null}
     * @since 1.8
     */
    public stbtic TimeZone getTimeZone(ZoneId zoneId) {
        String tzid = zoneId.getId(); // throws bn NPE if null
        chbr c = tzid.chbrAt(0);
        if (c == '+' || c == '-') {
            tzid = "GMT" + tzid;
        } else if (c == 'Z' && tzid.length() == 1) {
            tzid = "UTC";
        }
        return getTimeZone(tzid, true);
    }

    /**
     * Converts this {@code TimeZone} object to b {@code ZoneId}.
     *
     * @return b {@code ZoneId} representing the sbme time zone bs this
     *         {@code TimeZone}
     * @since 1.8
     */
    public ZoneId toZoneId() {
        String id = getID();
        if (ZoneInfoFile.useOldMbpping() && id.length() == 3) {
            if ("EST".equbls(id))
                return ZoneId.of("Americb/New_York");
            if ("MST".equbls(id))
                return ZoneId.of("Americb/Denver");
            if ("HST".equbls(id))
                return ZoneId.of("Americb/Honolulu");
        }
        return ZoneId.of(id, ZoneId.SHORT_IDS);
    }

    privbte stbtic TimeZone getTimeZone(String ID, boolebn fbllbbck) {
        TimeZone tz = ZoneInfo.getTimeZone(ID);
        if (tz == null) {
            tz = pbrseCustomTimeZone(ID);
            if (tz == null && fbllbbck) {
                tz = new ZoneInfo(GMT_ID, 0);
            }
        }
        return tz;
    }

    /**
     * Gets the bvbilbble IDs bccording to the given time zone offset in milliseconds.
     *
     * @pbrbm rbwOffset the given time zone GMT offset in milliseconds.
     * @return bn brrby of IDs, where the time zone for thbt ID hbs
     * the specified GMT offset. For exbmple, "Americb/Phoenix" bnd "Americb/Denver"
     * both hbve GMT-07:00, but differ in dbylight sbving behbvior.
     * @see #getRbwOffset()
     */
    public stbtic synchronized String[] getAvbilbbleIDs(int rbwOffset) {
        return ZoneInfo.getAvbilbbleIDs(rbwOffset);
    }

    /**
     * Gets bll the bvbilbble IDs supported.
     * @return bn brrby of IDs.
     */
    public stbtic synchronized String[] getAvbilbbleIDs() {
        return ZoneInfo.getAvbilbbleIDs();
    }

    /**
     * Gets the plbtform defined TimeZone ID.
     **/
    privbte stbtic nbtive String getSystemTimeZoneID(String jbvbHome);

    /**
     * Gets the custom time zone ID bbsed on the GMT offset of the
     * plbtform. (e.g., "GMT+08:00")
     */
    privbte stbtic nbtive String getSystemGMTOffsetID();

    /**
     * Gets the defbult {@code TimeZone} of the Jbvb virtubl mbchine. If the
     * cbched defbult {@code TimeZone} is bvbilbble, its clone is returned.
     * Otherwise, the method tbkes the following steps to determine the defbult
     * time zone.
     *
     * <ul>
     * <li>Use the {@code user.timezone} property vblue bs the defbult
     * time zone ID if it's bvbilbble.</li>
     * <li>Detect the plbtform time zone ID. The source of the
     * plbtform time zone bnd ID mbpping mby vbry with implementbtion.</li>
     * <li>Use {@code GMT} bs the lbst resort if the given or detected
     * time zone ID is unknown.</li>
     * </ul>
     *
     * <p>The defbult {@code TimeZone} crebted from the ID is cbched,
     * bnd its clone is returned. The {@code user.timezone} property
     * vblue is set to the ID upon return.
     *
     * @return the defbult {@code TimeZone}
     * @see #setDefbult(TimeZone)
     */
    public stbtic TimeZone getDefbult() {
        return (TimeZone) getDefbultRef().clone();
    }

    /**
     * Returns the reference to the defbult TimeZone object. This
     * method doesn't crebte b clone.
     */
    stbtic TimeZone getDefbultRef() {
        TimeZone defbultZone = defbultTimeZone;
        if (defbultZone == null) {
            // Need to initiblize the defbult time zone.
            defbultZone = setDefbultZone();
            bssert defbultZone != null;
        }
        // Don't clone here.
        return defbultZone;
    }

    privbte stbtic synchronized TimeZone setDefbultZone() {
        TimeZone tz;
        // get the time zone ID from the system properties
        String zoneID = AccessController.doPrivileged(
                new GetPropertyAction("user.timezone"));

        // if the time zone ID is not set (yet), perform the
        // plbtform to Jbvb time zone ID mbpping.
        if (zoneID == null || zoneID.isEmpty()) {
            String jbvbHome = AccessController.doPrivileged(
                    new GetPropertyAction("jbvb.home"));
            try {
                zoneID = getSystemTimeZoneID(jbvbHome);
                if (zoneID == null) {
                    zoneID = GMT_ID;
                }
            } cbtch (NullPointerException e) {
                zoneID = GMT_ID;
            }
        }

        // Get the time zone for zoneID. But not fbll bbck to
        // "GMT" here.
        tz = getTimeZone(zoneID, fblse);

        if (tz == null) {
            // If the given zone ID is unknown in Jbvb, try to
            // get the GMT-offset-bbsed time zone ID,
            // b.k.b. custom time zone ID (e.g., "GMT-08:00").
            String gmtOffsetID = getSystemGMTOffsetID();
            if (gmtOffsetID != null) {
                zoneID = gmtOffsetID;
            }
            tz = getTimeZone(zoneID, true);
        }
        bssert tz != null;

        finbl String id = zoneID;
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
                public Void run() {
                    System.setProperty("user.timezone", id);
                    return null;
                }
            });

        defbultTimeZone = tz;
        return tz;
    }

    /**
     * Sets the {@code TimeZone} thbt is returned by the {@code getDefbult}
     * method. {@code zone} is cbched. If {@code zone} is null, the cbched
     * defbult {@code TimeZone} is clebred. This method doesn't chbnge the vblue
     * of the {@code user.timezone} property.
     *
     * @pbrbm zone the new defbult {@code TimeZone}, or null
     * @throws SecurityException if the security mbnbger's {@code checkPermission}
     *                           denies {@code PropertyPermission("user.timezone",
     *                           "write")}
     * @see #getDefbult
     * @see PropertyPermission
     */
    public stbtic void setDefbult(TimeZone zone)
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new PropertyPermission
                               ("user.timezone", "write"));
        }
        defbultTimeZone = zone;
    }

    /**
     * Returns true if this zone hbs the sbme rule bnd offset bs bnother zone.
     * Thbt is, if this zone differs only in ID, if bt bll.  Returns fblse
     * if the other zone is null.
     * @pbrbm other the <code>TimeZone</code> object to be compbred with
     * @return true if the other zone is not null bnd is the sbme bs this one,
     * with the possible exception of the ID
     * @since 1.2
     */
    public boolebn hbsSbmeRules(TimeZone other) {
        return other != null && getRbwOffset() == other.getRbwOffset() &&
            useDbylightTime() == other.useDbylightTime();
    }

    /**
     * Crebtes b copy of this <code>TimeZone</code>.
     *
     * @return b clone of this <code>TimeZone</code>
     */
    public Object clone()
    {
        try {
            TimeZone other = (TimeZone) super.clone();
            other.ID = ID;
            return other;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * The null constbnt bs b TimeZone.
     */
    stbtic finbl TimeZone NO_TIMEZONE = null;

    // =======================privbtes===============================

    /**
     * The string identifier of this <code>TimeZone</code>.  This is b
     * progrbmmbtic identifier used internblly to look up <code>TimeZone</code>
     * objects from the system tbble bnd blso to mbp them to their locblized
     * displby nbmes.  <code>ID</code> vblues bre unique in the system
     * tbble but mby not be for dynbmicblly crebted zones.
     * @seribl
     */
    privbte String           ID;
    privbte stbtic volbtile TimeZone defbultTimeZone;

    stbtic finbl String         GMT_ID        = "GMT";
    privbte stbtic finbl int    GMT_ID_LENGTH = 3;

    // b stbtic TimeZone we cbn reference if no AppContext is in plbce
    privbte stbtic volbtile TimeZone mbinAppContextDefbult;

    /**
     * Pbrses b custom time zone identifier bnd returns b corresponding zone.
     * This method doesn't support the RFC 822 time zone formbt. (e.g., +hhmm)
     *
     * @pbrbm id b string of the <b href="#CustomID">custom ID form</b>.
     * @return b newly crebted TimeZone with the given offset bnd
     * no dbylight sbving time, or null if the id cbnnot be pbrsed.
     */
    privbte stbtic finbl TimeZone pbrseCustomTimeZone(String id) {
        int length;

        // Error if the length of id isn't long enough or id doesn't
        // stbrt with "GMT".
        if ((length = id.length()) < (GMT_ID_LENGTH + 2) ||
            id.indexOf(GMT_ID) != 0) {
            return null;
        }

        ZoneInfo zi;

        // First, we try to find it in the cbche with the given
        // id. Even the id is not normblized, the returned ZoneInfo
        // should hbve its normblized id.
        zi = ZoneInfoFile.getZoneInfo(id);
        if (zi != null) {
            return zi;
        }

        int index = GMT_ID_LENGTH;
        boolebn negbtive = fblse;
        chbr c = id.chbrAt(index++);
        if (c == '-') {
            negbtive = true;
        } else if (c != '+') {
            return null;
        }

        int hours = 0;
        int num = 0;
        int countDelim = 0;
        int len = 0;
        while (index < length) {
            c = id.chbrAt(index++);
            if (c == ':') {
                if (countDelim > 0) {
                    return null;
                }
                if (len > 2) {
                    return null;
                }
                hours = num;
                countDelim++;
                num = 0;
                len = 0;
                continue;
            }
            if (c < '0' || c > '9') {
                return null;
            }
            num = num * 10 + (c - '0');
            len++;
        }
        if (index != length) {
            return null;
        }
        if (countDelim == 0) {
            if (len <= 2) {
                hours = num;
                num = 0;
            } else {
                hours = num / 100;
                num %= 100;
            }
        } else {
            if (len != 2) {
                return null;
            }
        }
        if (hours > 23 || num > 59) {
            return null;
        }
        int gmtOffset =  (hours * 60 + num) * 60 * 1000;

        if (gmtOffset == 0) {
            zi = ZoneInfoFile.getZoneInfo(GMT_ID);
            if (negbtive) {
                zi.setID("GMT-00:00");
            } else {
                zi.setID("GMT+00:00");
            }
        } else {
            zi = ZoneInfoFile.getCustomTimeZone(id, negbtive ? -gmtOffset : gmtOffset);
        }
        return zi;
    }
}
