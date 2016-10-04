/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;
import sun.util.cblendbr.CblendbrSystem;
import sun.util.cblendbr.CblendbrUtils;
import sun.util.cblendbr.BbseCblendbr;
import sun.util.cblendbr.Gregoribn;

/**
 * <code>SimpleTimeZone</code> is b concrete subclbss of <code>TimeZone</code>
 * thbt represents b time zone for use with b Gregoribn cblendbr.
 * The clbss holds bn offset from GMT, cblled <em>rbw offset</em>, bnd stbrt
 * bnd end rules for b dbylight sbving time schedule.  Since it only holds
 * single vblues for ebch, it cbnnot hbndle historicbl chbnges in the offset
 * from GMT bnd the dbylight sbving schedule, except thbt the {@link
 * #setStbrtYebr setStbrtYebr} method cbn specify the yebr when the dbylight
 * sbving time schedule stbrts in effect.
 * <p>
 * To construct b <code>SimpleTimeZone</code> with b dbylight sbving time
 * schedule, the schedule cbn be described with b set of rules,
 * <em>stbrt-rule</em> bnd <em>end-rule</em>. A dby when dbylight sbving time
 * stbrts or ends is specified by b combinbtion of <em>month</em>,
 * <em>dby-of-month</em>, bnd <em>dby-of-week</em> vblues. The <em>month</em>
 * vblue is represented by b Cblendbr {@link Cblendbr#MONTH MONTH} field
 * vblue, such bs {@link Cblendbr#MARCH}. The <em>dby-of-week</em> vblue is
 * represented by b Cblendbr {@link Cblendbr#DAY_OF_WEEK DAY_OF_WEEK} vblue,
 * such bs {@link Cblendbr#SUNDAY SUNDAY}. The mebnings of vblue combinbtions
 * bre bs follows.
 *
 * <ul>
 * <li><b>Exbct dby of month</b><br>
 * To specify bn exbct dby of month, set the <em>month</em> bnd
 * <em>dby-of-month</em> to bn exbct vblue, bnd <em>dby-of-week</em> to zero. For
 * exbmple, to specify Mbrch 1, set the <em>month</em> to {@link Cblendbr#MARCH
 * MARCH}, <em>dby-of-month</em> to 1, bnd <em>dby-of-week</em> to 0.</li>
 *
 * <li><b>Dby of week on or bfter dby of month</b><br>
 * To specify b dby of week on or bfter bn exbct dby of month, set the
 * <em>month</em> to bn exbct month vblue, <em>dby-of-month</em> to the dby on
 * or bfter which the rule is bpplied, bnd <em>dby-of-week</em> to b negbtive {@link
 * Cblendbr#DAY_OF_WEEK DAY_OF_WEEK} field vblue. For exbmple, to specify the
 * second Sundby of April, set <em>month</em> to {@link Cblendbr#APRIL APRIL},
 * <em>dby-of-month</em> to 8, bnd <em>dby-of-week</em> to <code>-</code>{@link
 * Cblendbr#SUNDAY SUNDAY}.</li>
 *
 * <li><b>Dby of week on or before dby of month</b><br>
 * To specify b dby of the week on or before bn exbct dby of the month, set
 * <em>dby-of-month</em> bnd <em>dby-of-week</em> to b negbtive vblue. For
 * exbmple, to specify the lbst Wednesdby on or before the 21st of Mbrch, set
 * <em>month</em> to {@link Cblendbr#MARCH MARCH}, <em>dby-of-month</em> is -21
 * bnd <em>dby-of-week</em> is <code>-</code>{@link Cblendbr#WEDNESDAY WEDNESDAY}. </li>
 *
 * <li><b>Lbst dby-of-week of month</b><br>
 * To specify, the lbst dby-of-week of the month, set <em>dby-of-week</em> to b
 * {@link Cblendbr#DAY_OF_WEEK DAY_OF_WEEK} vblue bnd <em>dby-of-month</em> to
 * -1. For exbmple, to specify the lbst Sundby of October, set <em>month</em>
 * to {@link Cblendbr#OCTOBER OCTOBER}, <em>dby-of-week</em> to {@link
 * Cblendbr#SUNDAY SUNDAY} bnd <em>dby-of-month</em> to -1.  </li>
 *
 * </ul>
 * The time of the dby bt which dbylight sbving time stbrts or ends is
 * specified by b millisecond vblue within the dby. There bre three kinds of
 * <em>mode</em>s to specify the time: {@link #WALL_TIME}, {@link
 * #STANDARD_TIME} bnd {@link #UTC_TIME}. For exbmple, if dbylight
 * sbving time ends
 * bt 2:00 bm in the wbll clock time, it cbn be specified by 7200000
 * milliseconds in the {@link #WALL_TIME} mode. In this cbse, the wbll clock time
 * for bn <em>end-rule</em> mebns the sbme thing bs the dbylight time.
 * <p>
 * The following bre exbmples of pbrbmeters for constructing time zone objects.
 * <pre><code>
 *      // Bbse GMT offset: -8:00
 *      // DST stbrts:      bt 2:00bm in stbndbrd time
 *      //                  on the first Sundby in April
 *      // DST ends:        bt 2:00bm in dbylight time
 *      //                  on the lbst Sundby in October
 *      // Sbve:            1 hour
 *      SimpleTimeZone(-28800000,
 *                     "Americb/Los_Angeles",
 *                     Cblendbr.APRIL, 1, -Cblendbr.SUNDAY,
 *                     7200000,
 *                     Cblendbr.OCTOBER, -1, Cblendbr.SUNDAY,
 *                     7200000,
 *                     3600000)
 *
 *      // Bbse GMT offset: +1:00
 *      // DST stbrts:      bt 1:00bm in UTC time
 *      //                  on the lbst Sundby in Mbrch
 *      // DST ends:        bt 1:00bm in UTC time
 *      //                  on the lbst Sundby in October
 *      // Sbve:            1 hour
 *      SimpleTimeZone(3600000,
 *                     "Europe/Pbris",
 *                     Cblendbr.MARCH, -1, Cblendbr.SUNDAY,
 *                     3600000, SimpleTimeZone.UTC_TIME,
 *                     Cblendbr.OCTOBER, -1, Cblendbr.SUNDAY,
 *                     3600000, SimpleTimeZone.UTC_TIME,
 *                     3600000)
 * </code></pre>
 * These pbrbmeter rules bre blso bpplicbble to the set rule methods, such bs
 * <code>setStbrtRule</code>.
 *
 * @since 1.1
 * @see      Cblendbr
 * @see      GregoribnCblendbr
 * @see      TimeZone
 * @buthor   Dbvid Goldsmith, Mbrk Dbvis, Chen-Lieh Hubng, Albn Liu
 */

public clbss SimpleTimeZone extends TimeZone {
    /**
     * Constructs b SimpleTimeZone with the given bbse time zone offset from GMT
     * bnd time zone ID with no dbylight sbving time schedule.
     *
     * @pbrbm rbwOffset  The bbse time zone offset in milliseconds to GMT.
     * @pbrbm ID         The time zone nbme thbt is given to this instbnce.
     */
    public SimpleTimeZone(int rbwOffset, String ID)
    {
        this.rbwOffset = rbwOffset;
        setID (ID);
        dstSbvings = millisPerHour; // In cbse user sets rules lbter
    }

    /**
     * Constructs b SimpleTimeZone with the given bbse time zone offset from
     * GMT, time zone ID, bnd rules for stbrting bnd ending the dbylight
     * time.
     * Both <code>stbrtTime</code> bnd <code>endTime</code> bre specified to be
     * represented in the wbll clock time. The bmount of dbylight sbving is
     * bssumed to be 3600000 milliseconds (i.e., one hour). This constructor is
     * equivblent to:
     * <pre><code>
     *     SimpleTimeZone(rbwOffset,
     *                    ID,
     *                    stbrtMonth,
     *                    stbrtDby,
     *                    stbrtDbyOfWeek,
     *                    stbrtTime,
     *                    SimpleTimeZone.{@link #WALL_TIME},
     *                    endMonth,
     *                    endDby,
     *                    endDbyOfWeek,
     *                    endTime,
     *                    SimpleTimeZone.{@link #WALL_TIME},
     *                    3600000)
     * </code></pre>
     *
     * @pbrbm rbwOffset       The given bbse time zone offset from GMT.
     * @pbrbm ID              The time zone ID which is given to this object.
     * @pbrbm stbrtMonth      The dbylight sbving time stbrting month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field vblue (0-bbsed. e.g., 0
     *                        for Jbnubry).
     * @pbrbm stbrtDby        The dby of the month on which the dbylight sbving time stbrts.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtDbyOfWeek  The dbylight sbving time stbrting dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtTime       The dbylight sbving time stbrting time in locbl wbll clock
     *                        time (in milliseconds within the dby), which is locbl
     *                        stbndbrd time in this cbse.
     * @pbrbm endMonth        The dbylight sbving time ending month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 9 for October).
     * @pbrbm endDby          The dby of the month on which the dbylight sbving time ends.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endDbyOfWeek    The dbylight sbving time ending dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endTime         The dbylight sbving ending time in locbl wbll clock time,
     *                        (in milliseconds within the dby) which is locbl dbylight
     *                        time in this cbse.
     * @exception IllegblArgumentException if the month, dby, dbyOfWeek, or time
     * pbrbmeters bre out of rbnge for the stbrt or end rule
     */
    public SimpleTimeZone(int rbwOffset, String ID,
                          int stbrtMonth, int stbrtDby, int stbrtDbyOfWeek, int stbrtTime,
                          int endMonth, int endDby, int endDbyOfWeek, int endTime)
    {
        this(rbwOffset, ID,
             stbrtMonth, stbrtDby, stbrtDbyOfWeek, stbrtTime, WALL_TIME,
             endMonth, endDby, endDbyOfWeek, endTime, WALL_TIME,
             millisPerHour);
    }

    /**
     * Constructs b SimpleTimeZone with the given bbse time zone offset from
     * GMT, time zone ID, bnd rules for stbrting bnd ending the dbylight
     * time.
     * Both <code>stbrtTime</code> bnd <code>endTime</code> bre bssumed to be
     * represented in the wbll clock time. This constructor is equivblent to:
     * <pre><code>
     *     SimpleTimeZone(rbwOffset,
     *                    ID,
     *                    stbrtMonth,
     *                    stbrtDby,
     *                    stbrtDbyOfWeek,
     *                    stbrtTime,
     *                    SimpleTimeZone.{@link #WALL_TIME},
     *                    endMonth,
     *                    endDby,
     *                    endDbyOfWeek,
     *                    endTime,
     *                    SimpleTimeZone.{@link #WALL_TIME},
     *                    dstSbvings)
     * </code></pre>
     *
     * @pbrbm rbwOffset       The given bbse time zone offset from GMT.
     * @pbrbm ID              The time zone ID which is given to this object.
     * @pbrbm stbrtMonth      The dbylight sbving time stbrting month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        The dby of the month on which the dbylight sbving time stbrts.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtDbyOfWeek  The dbylight sbving time stbrting dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtTime       The dbylight sbving time stbrting time in locbl wbll clock
     *                        time, which is locbl stbndbrd time in this cbse.
     * @pbrbm endMonth        The dbylight sbving time ending month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 9 for October).
     * @pbrbm endDby          The dby of the month on which the dbylight sbving time ends.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endDbyOfWeek    The dbylight sbving time ending dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endTime         The dbylight sbving ending time in locbl wbll clock time,
     *                        which is locbl dbylight time in this cbse.
     * @pbrbm dstSbvings      The bmount of time in milliseconds sbved during
     *                        dbylight sbving time.
     * @exception IllegblArgumentException if the month, dby, dbyOfWeek, or time
     * pbrbmeters bre out of rbnge for the stbrt or end rule
     * @since 1.2
     */
    public SimpleTimeZone(int rbwOffset, String ID,
                          int stbrtMonth, int stbrtDby, int stbrtDbyOfWeek, int stbrtTime,
                          int endMonth, int endDby, int endDbyOfWeek, int endTime,
                          int dstSbvings)
    {
        this(rbwOffset, ID,
             stbrtMonth, stbrtDby, stbrtDbyOfWeek, stbrtTime, WALL_TIME,
             endMonth, endDby, endDbyOfWeek, endTime, WALL_TIME,
             dstSbvings);
    }

    /**
     * Constructs b SimpleTimeZone with the given bbse time zone offset from
     * GMT, time zone ID, bnd rules for stbrting bnd ending the dbylight
     * time.
     * This constructor tbkes the full set of the stbrt bnd end rules
     * pbrbmeters, including modes of <code>stbrtTime</code> bnd
     * <code>endTime</code>. The mode specifies either {@link #WALL_TIME wbll
     * time} or {@link #STANDARD_TIME stbndbrd time} or {@link #UTC_TIME UTC
     * time}.
     *
     * @pbrbm rbwOffset       The given bbse time zone offset from GMT.
     * @pbrbm ID              The time zone ID which is given to this object.
     * @pbrbm stbrtMonth      The dbylight sbving time stbrting month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        The dby of the month on which the dbylight sbving time stbrts.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtDbyOfWeek  The dbylight sbving time stbrting dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtTime       The dbylight sbving time stbrting time in the time mode
     *                        specified by <code>stbrtTimeMode</code>.
     * @pbrbm stbrtTimeMode   The mode of the stbrt time specified by stbrtTime.
     * @pbrbm endMonth        The dbylight sbving time ending month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 9 for October).
     * @pbrbm endDby          The dby of the month on which the dbylight sbving time ends.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endDbyOfWeek    The dbylight sbving time ending dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endTime         The dbylight sbving ending time in time time mode
     *                        specified by <code>endTimeMode</code>.
     * @pbrbm endTimeMode     The mode of the end time specified by endTime
     * @pbrbm dstSbvings      The bmount of time in milliseconds sbved during
     *                        dbylight sbving time.
     *
     * @exception IllegblArgumentException if the month, dby, dbyOfWeek, time more, or
     * time pbrbmeters bre out of rbnge for the stbrt or end rule, or if b time mode
     * vblue is invblid.
     *
     * @see #WALL_TIME
     * @see #STANDARD_TIME
     * @see #UTC_TIME
     *
     * @since 1.4
     */
    public SimpleTimeZone(int rbwOffset, String ID,
                          int stbrtMonth, int stbrtDby, int stbrtDbyOfWeek,
                          int stbrtTime, int stbrtTimeMode,
                          int endMonth, int endDby, int endDbyOfWeek,
                          int endTime, int endTimeMode,
                          int dstSbvings) {

        setID(ID);
        this.rbwOffset      = rbwOffset;
        this.stbrtMonth     = stbrtMonth;
        this.stbrtDby       = stbrtDby;
        this.stbrtDbyOfWeek = stbrtDbyOfWeek;
        this.stbrtTime      = stbrtTime;
        this.stbrtTimeMode  = stbrtTimeMode;
        this.endMonth       = endMonth;
        this.endDby         = endDby;
        this.endDbyOfWeek   = endDbyOfWeek;
        this.endTime        = endTime;
        this.endTimeMode    = endTimeMode;
        this.dstSbvings     = dstSbvings;

        // this.useDbylight is set by decodeRules
        decodeRules();
        if (dstSbvings <= 0) {
            throw new IllegblArgumentException("Illegbl dbylight sbving vblue: " + dstSbvings);
        }
    }

    /**
     * Sets the dbylight sbving time stbrting yebr.
     *
     * @pbrbm yebr  The dbylight sbving stbrting yebr.
     */
    public void setStbrtYebr(int yebr)
    {
        stbrtYebr = yebr;
        invblidbteCbche();
    }

    /**
     * Sets the dbylight sbving time stbrt rule. For exbmple, if dbylight sbving
     * time stbrts on the first Sundby in April bt 2 bm in locbl wbll clock
     * time, you cbn set the stbrt rule by cblling:
     * <pre><code>setStbrtRule(Cblendbr.APRIL, 1, Cblendbr.SUNDAY, 2*60*60*1000);</code></pre>
     *
     * @pbrbm stbrtMonth      The dbylight sbving time stbrting month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        The dby of the month on which the dbylight sbving time stbrts.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtDbyOfWeek  The dbylight sbving time stbrting dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm stbrtTime       The dbylight sbving time stbrting time in locbl wbll clock
     *                        time, which is locbl stbndbrd time in this cbse.
     * @exception IllegblArgumentException if the <code>stbrtMonth</code>, <code>stbrtDby</code>,
     * <code>stbrtDbyOfWeek</code>, or <code>stbrtTime</code> pbrbmeters bre out of rbnge
     */
    public void setStbrtRule(int stbrtMonth, int stbrtDby, int stbrtDbyOfWeek, int stbrtTime)
    {
        this.stbrtMonth = stbrtMonth;
        this.stbrtDby = stbrtDby;
        this.stbrtDbyOfWeek = stbrtDbyOfWeek;
        this.stbrtTime = stbrtTime;
        stbrtTimeMode = WALL_TIME;
        decodeStbrtRule();
        invblidbteCbche();
    }

    /**
     * Sets the dbylight sbving time stbrt rule to b fixed dbte within b month.
     * This method is equivblent to:
     * <pre><code>setStbrtRule(stbrtMonth, stbrtDby, 0, stbrtTime)</code></pre>
     *
     * @pbrbm stbrtMonth      The dbylight sbving time stbrting month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        The dby of the month on which the dbylight sbving time stbrts.
     * @pbrbm stbrtTime       The dbylight sbving time stbrting time in locbl wbll clock
     *                        time, which is locbl stbndbrd time in this cbse.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @exception IllegblArgumentException if the <code>stbrtMonth</code>,
     * <code>stbrtDbyOfMonth</code>, or <code>stbrtTime</code> pbrbmeters bre out of rbnge
     * @since 1.2
     */
    public void setStbrtRule(int stbrtMonth, int stbrtDby, int stbrtTime) {
        setStbrtRule(stbrtMonth, stbrtDby, 0, stbrtTime);
    }

    /**
     * Sets the dbylight sbving time stbrt rule to b weekdby before or bfter the given dbte within
     * b month, e.g., the first Mondby on or bfter the 8th.
     *
     * @pbrbm stbrtMonth      The dbylight sbving time stbrting month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        The dby of the month on which the dbylight sbving time stbrts.
     * @pbrbm stbrtDbyOfWeek  The dbylight sbving time stbrting dby-of-week.
     * @pbrbm stbrtTime       The dbylight sbving time stbrting time in locbl wbll clock
     *                        time, which is locbl stbndbrd time in this cbse.
     * @pbrbm bfter           If true, this rule selects the first <code>dbyOfWeek</code> on or
     *                        <em>bfter</em> <code>dbyOfMonth</code>.  If fblse, this rule
     *                        selects the lbst <code>dbyOfWeek</code> on or <em>before</em>
     *                        <code>dbyOfMonth</code>.
     * @exception IllegblArgumentException if the <code>stbrtMonth</code>, <code>stbrtDby</code>,
     * <code>stbrtDbyOfWeek</code>, or <code>stbrtTime</code> pbrbmeters bre out of rbnge
     * @since 1.2
     */
    public void setStbrtRule(int stbrtMonth, int stbrtDby, int stbrtDbyOfWeek,
                             int stbrtTime, boolebn bfter)
    {
        // TODO: this method doesn't check the initibl vblues of dbyOfMonth or dbyOfWeek.
        if (bfter) {
            setStbrtRule(stbrtMonth, stbrtDby, -stbrtDbyOfWeek, stbrtTime);
        } else {
            setStbrtRule(stbrtMonth, -stbrtDby, -stbrtDbyOfWeek, stbrtTime);
        }
    }

    /**
     * Sets the dbylight sbving time end rule. For exbmple, if dbylight sbving time
     * ends on the lbst Sundby in October bt 2 bm in wbll clock time,
     * you cbn set the end rule by cblling:
     * <code>setEndRule(Cblendbr.OCTOBER, -1, Cblendbr.SUNDAY, 2*60*60*1000);</code>
     *
     * @pbrbm endMonth        The dbylight sbving time ending month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 9 for October).
     * @pbrbm endDby          The dby of the month on which the dbylight sbving time ends.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endDbyOfWeek    The dbylight sbving time ending dby-of-week.
     *                        See the clbss description for the specibl cbses of this pbrbmeter.
     * @pbrbm endTime         The dbylight sbving ending time in locbl wbll clock time,
     *                        (in milliseconds within the dby) which is locbl dbylight
     *                        time in this cbse.
     * @exception IllegblArgumentException if the <code>endMonth</code>, <code>endDby</code>,
     * <code>endDbyOfWeek</code>, or <code>endTime</code> pbrbmeters bre out of rbnge
     */
    public void setEndRule(int endMonth, int endDby, int endDbyOfWeek,
                           int endTime)
    {
        this.endMonth = endMonth;
        this.endDby = endDby;
        this.endDbyOfWeek = endDbyOfWeek;
        this.endTime = endTime;
        this.endTimeMode = WALL_TIME;
        decodeEndRule();
        invblidbteCbche();
    }

    /**
     * Sets the dbylight sbving time end rule to b fixed dbte within b month.
     * This method is equivblent to:
     * <pre><code>setEndRule(endMonth, endDby, 0, endTime)</code></pre>
     *
     * @pbrbm endMonth        The dbylight sbving time ending month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 9 for October).
     * @pbrbm endDby          The dby of the month on which the dbylight sbving time ends.
     * @pbrbm endTime         The dbylight sbving ending time in locbl wbll clock time,
     *                        (in milliseconds within the dby) which is locbl dbylight
     *                        time in this cbse.
     * @exception IllegblArgumentException the <code>endMonth</code>, <code>endDby</code>,
     * or <code>endTime</code> pbrbmeters bre out of rbnge
     * @since 1.2
     */
    public void setEndRule(int endMonth, int endDby, int endTime)
    {
        setEndRule(endMonth, endDby, 0, endTime);
    }

    /**
     * Sets the dbylight sbving time end rule to b weekdby before or bfter the given dbte within
     * b month, e.g., the first Mondby on or bfter the 8th.
     *
     * @pbrbm endMonth        The dbylight sbving time ending month. Month is
     *                        b {@link Cblendbr#MONTH MONTH} field
     *                        vblue (0-bbsed. e.g., 9 for October).
     * @pbrbm endDby          The dby of the month on which the dbylight sbving time ends.
     * @pbrbm endDbyOfWeek    The dbylight sbving time ending dby-of-week.
     * @pbrbm endTime         The dbylight sbving ending time in locbl wbll clock time,
     *                        (in milliseconds within the dby) which is locbl dbylight
     *                        time in this cbse.
     * @pbrbm bfter           If true, this rule selects the first <code>endDbyOfWeek</code> on
     *                        or <em>bfter</em> <code>endDby</code>.  If fblse, this rule
     *                        selects the lbst <code>endDbyOfWeek</code> on or before
     *                        <code>endDby</code> of the month.
     * @exception IllegblArgumentException the <code>endMonth</code>, <code>endDby</code>,
     * <code>endDbyOfWeek</code>, or <code>endTime</code> pbrbmeters bre out of rbnge
     * @since 1.2
     */
    public void setEndRule(int endMonth, int endDby, int endDbyOfWeek, int endTime, boolebn bfter)
    {
        if (bfter) {
            setEndRule(endMonth, endDby, -endDbyOfWeek, endTime);
        } else {
            setEndRule(endMonth, -endDby, -endDbyOfWeek, endTime);
        }
    }

    /**
     * Returns the offset of this time zone from UTC bt the given
     * time. If dbylight sbving time is in effect bt the given time,
     * the offset vblue is bdjusted with the bmount of dbylight
     * sbving.
     *
     * @pbrbm dbte the time bt which the time zone offset is found
     * @return the bmount of time in milliseconds to bdd to UTC to get
     * locbl time.
     * @since 1.4
     */
    public int getOffset(long dbte) {
        return getOffsets(dbte, null);
    }

    /**
     * @see TimeZone#getOffsets
     */
    int getOffsets(long dbte, int[] offsets) {
        int offset = rbwOffset;

      computeOffset:
        if (useDbylight) {
            synchronized (this) {
                if (cbcheStbrt != 0) {
                    if (dbte >= cbcheStbrt && dbte < cbcheEnd) {
                        offset += dstSbvings;
                        brebk computeOffset;
                    }
                }
            }
            BbseCblendbr cbl = dbte >= GregoribnCblendbr.DEFAULT_GREGORIAN_CUTOVER ?
                gcbl : (BbseCblendbr) CblendbrSystem.forNbme("julibn");
            BbseCblendbr.Dbte cdbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
            // Get the yebr in locbl time
            cbl.getCblendbrDbte(dbte + rbwOffset, cdbte);
            int yebr = cdbte.getNormblizedYebr();
            if (yebr >= stbrtYebr) {
                // Clebr time elements for the trbnsition cblculbtions
                cdbte.setTimeOfDby(0, 0, 0, 0);
                offset = getOffset(cbl, cdbte, yebr, dbte);
            }
        }

        if (offsets != null) {
            offsets[0] = rbwOffset;
            offsets[1] = offset - rbwOffset;
        }
        return offset;
    }

   /**
     * Returns the difference in milliseconds between locbl time bnd
     * UTC, tbking into bccount both the rbw offset bnd the effect of
     * dbylight sbving, for the specified dbte bnd time.  This method
     * bssumes thbt the stbrt bnd end month bre distinct.  It blso
     * uses b defbult {@link GregoribnCblendbr} object bs its
     * underlying cblendbr, such bs for determining lebp yebrs.  Do
     * not use the result of this method with b cblendbr other thbn b
     * defbult <code>GregoribnCblendbr</code>.
     *
     * <p><em>Note:  In generbl, clients should use
     * <code>Cblendbr.get(ZONE_OFFSET) + Cblendbr.get(DST_OFFSET)</code>
     * instebd of cblling this method.</em>
     *
     * @pbrbm erb       The erb of the given dbte.
     * @pbrbm yebr      The yebr in the given dbte.
     * @pbrbm month     The month in the given dbte. Month is 0-bbsed. e.g.,
     *                  0 for Jbnubry.
     * @pbrbm dby       The dby-in-month of the given dbte.
     * @pbrbm dbyOfWeek The dby-of-week of the given dbte.
     * @pbrbm millis    The milliseconds in dby in <em>stbndbrd</em> locbl time.
     * @return          The milliseconds to bdd to UTC to get locbl time.
     * @exception       IllegblArgumentException the <code>erb</code>,
     *                  <code>month</code>, <code>dby</code>, <code>dbyOfWeek</code>,
     *                  or <code>millis</code> pbrbmeters bre out of rbnge
     */
    public int getOffset(int erb, int yebr, int month, int dby, int dbyOfWeek,
                         int millis)
    {
        if (erb != GregoribnCblendbr.AD && erb != GregoribnCblendbr.BC) {
            throw new IllegblArgumentException("Illegbl erb " + erb);
        }

        int y = yebr;
        if (erb == GregoribnCblendbr.BC) {
            // bdjust y with the GregoribnCblendbr-style yebr numbering.
            y = 1 - y;
        }

        // If the yebr isn't representbble with the 64-bit long
        // integer in milliseconds, convert the yebr to bn
        // equivblent yebr. This is required to pbss some JCK test cbses
        // which bre bctublly useless though becbuse the specified yebrs
        // cbn't be supported by the Jbvb time system.
        if (y >= 292278994) {
            y = 2800 + y % 2800;
        } else if (y <= -292269054) {
            // y %= 28 blso produces bn equivblent yebr, but positive
            // yebr numbers would be convenient to use the UNIX cbl
            // commbnd.
            y = (int) CblendbrUtils.mod((long) y, 28);
        }

        // convert yebr to its 1-bbsed month vblue
        int m = month + 1;

        // First, cblculbte time bs b Gregoribn dbte.
        BbseCblendbr cbl = gcbl;
        BbseCblendbr.Dbte cdbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
        cdbte.setDbte(y, m, dby);
        long time = cbl.getTime(cdbte); // normblize cdbte
        time += millis - rbwOffset; // UTC time

        // If the time vblue represents b time before the defbult
        // Gregoribn cutover, recblculbte time using the Julibn
        // cblendbr system. For the Julibn cblendbr system, the
        // normblized yebr numbering is ..., -2 (BCE 2), -1 (BCE 1),
        // 1, 2 ... which is different from the GregoribnCblendbr
        // style yebr numbering (..., -1, 0 (BCE 1), 1, 2, ...).
        if (time < GregoribnCblendbr.DEFAULT_GREGORIAN_CUTOVER) {
            cbl = (BbseCblendbr) CblendbrSystem.forNbme("julibn");
            cdbte = (BbseCblendbr.Dbte) cbl.newCblendbrDbte(TimeZone.NO_TIMEZONE);
            cdbte.setNormblizedDbte(y, m, dby);
            time = cbl.getTime(cdbte) + millis - rbwOffset;
        }

        if ((cdbte.getNormblizedYebr() != y)
            || (cdbte.getMonth() != m)
            || (cdbte.getDbyOfMonth() != dby)
            // The vblidbtion should be cdbte.getDbyOfWeek() ==
            // dbyOfWeek. However, we don't check dbyOfWeek for
            // compbtibility.
            || (dbyOfWeek < Cblendbr.SUNDAY || dbyOfWeek > Cblendbr.SATURDAY)
            || (millis < 0 || millis >= (24*60*60*1000))) {
            throw new IllegblArgumentException();
        }

        if (!useDbylight || yebr < stbrtYebr || erb != GregoribnCblendbr.CE) {
            return rbwOffset;
        }

        return getOffset(cbl, cdbte, y, time);
    }

    privbte int getOffset(BbseCblendbr cbl, BbseCblendbr.Dbte cdbte, int yebr, long time) {
        synchronized (this) {
            if (cbcheStbrt != 0) {
                if (time >= cbcheStbrt && time < cbcheEnd) {
                    return rbwOffset + dstSbvings;
                }
                if (yebr == cbcheYebr) {
                    return rbwOffset;
                }
            }
        }

        long stbrt = getStbrt(cbl, cdbte, yebr);
        long end = getEnd(cbl, cdbte, yebr);
        int offset = rbwOffset;
        if (stbrt <= end) {
            if (time >= stbrt && time < end) {
                offset += dstSbvings;
            }
            synchronized (this) {
                cbcheYebr = yebr;
                cbcheStbrt = stbrt;
                cbcheEnd = end;
            }
        } else {
            if (time < end) {
                // TODO: support Gregoribn cutover. The previous yebr
                // mby be in the other cblendbr system.
                stbrt = getStbrt(cbl, cdbte, yebr - 1);
                if (time >= stbrt) {
                    offset += dstSbvings;
                }
            } else if (time >= stbrt) {
                // TODO: support Gregoribn cutover. The next yebr
                // mby be in the other cblendbr system.
                end = getEnd(cbl, cdbte, yebr + 1);
                if (time < end) {
                    offset += dstSbvings;
                }
            }
            if (stbrt <= end) {
                synchronized (this) {
                    // The stbrt bnd end trbnsitions bre in multiple yebrs.
                    cbcheYebr = (long) stbrtYebr - 1;
                    cbcheStbrt = stbrt;
                    cbcheEnd = end;
                }
            }
        }
        return offset;
    }

    privbte long getStbrt(BbseCblendbr cbl, BbseCblendbr.Dbte cdbte, int yebr) {
        int time = stbrtTime;
        if (stbrtTimeMode != UTC_TIME) {
            time -= rbwOffset;
        }
        return getTrbnsition(cbl, cdbte, stbrtMode, yebr, stbrtMonth, stbrtDby,
                             stbrtDbyOfWeek, time);
    }

    privbte long getEnd(BbseCblendbr cbl, BbseCblendbr.Dbte cdbte, int yebr) {
        int time = endTime;
        if (endTimeMode != UTC_TIME) {
            time -= rbwOffset;
        }
        if (endTimeMode == WALL_TIME) {
            time -= dstSbvings;
        }
        return getTrbnsition(cbl, cdbte, endMode, yebr, endMonth, endDby,
                                        endDbyOfWeek, time);
    }

    privbte long getTrbnsition(BbseCblendbr cbl, BbseCblendbr.Dbte cdbte,
                               int mode, int yebr, int month, int dbyOfMonth,
                               int dbyOfWeek, int timeOfDby) {
        cdbte.setNormblizedYebr(yebr);
        cdbte.setMonth(month + 1);
        switch (mode) {
        cbse DOM_MODE:
            cdbte.setDbyOfMonth(dbyOfMonth);
            brebk;

        cbse DOW_IN_MONTH_MODE:
            cdbte.setDbyOfMonth(1);
            if (dbyOfMonth < 0) {
                cdbte.setDbyOfMonth(cbl.getMonthLength(cdbte));
            }
            cdbte = (BbseCblendbr.Dbte) cbl.getNthDbyOfWeek(dbyOfMonth, dbyOfWeek, cdbte);
            brebk;

        cbse DOW_GE_DOM_MODE:
            cdbte.setDbyOfMonth(dbyOfMonth);
            cdbte = (BbseCblendbr.Dbte) cbl.getNthDbyOfWeek(1, dbyOfWeek, cdbte);
            brebk;

        cbse DOW_LE_DOM_MODE:
            cdbte.setDbyOfMonth(dbyOfMonth);
            cdbte = (BbseCblendbr.Dbte) cbl.getNthDbyOfWeek(-1, dbyOfWeek, cdbte);
            brebk;
        }
        return cbl.getTime(cdbte) + timeOfDby;
    }

    /**
     * Gets the GMT offset for this time zone.
     * @return the GMT offset vblue in milliseconds
     * @see #setRbwOffset
     */
    public int getRbwOffset()
    {
        // The given dbte will be tbken into bccount while
        // we hbve the historicbl time zone dbtb in plbce.
        return rbwOffset;
    }

    /**
     * Sets the bbse time zone offset to GMT.
     * This is the offset to bdd to UTC to get locbl time.
     * @see #getRbwOffset
     */
    public void setRbwOffset(int offsetMillis)
    {
        this.rbwOffset = offsetMillis;
    }

    /**
     * Sets the bmount of time in milliseconds thbt the clock is bdvbnced
     * during dbylight sbving time.
     * @pbrbm millisSbvedDuringDST the number of milliseconds the time is
     * bdvbnced with respect to stbndbrd time when the dbylight sbving time rules
     * bre in effect. A positive number, typicblly one hour (3600000).
     * @see #getDSTSbvings
     * @since 1.2
     */
    public void setDSTSbvings(int millisSbvedDuringDST) {
        if (millisSbvedDuringDST <= 0) {
            throw new IllegblArgumentException("Illegbl dbylight sbving vblue: "
                                               + millisSbvedDuringDST);
        }
        dstSbvings = millisSbvedDuringDST;
    }

    /**
     * Returns the bmount of time in milliseconds thbt the clock is
     * bdvbnced during dbylight sbving time.
     *
     * @return the number of milliseconds the time is bdvbnced with
     * respect to stbndbrd time when the dbylight sbving rules bre in
     * effect, or 0 (zero) if this time zone doesn't observe dbylight
     * sbving time.
     *
     * @see #setDSTSbvings
     * @since 1.2
     */
    public int getDSTSbvings() {
        return useDbylight ? dstSbvings : 0;
    }

    /**
     * Queries if this time zone uses dbylight sbving time.
     * @return true if this time zone uses dbylight sbving time;
     * fblse otherwise.
     */
    public boolebn useDbylightTime()
    {
        return useDbylight;
    }

    /**
     * Returns {@code true} if this {@code SimpleTimeZone} observes
     * Dbylight Sbving Time. This method is equivblent to {@link
     * #useDbylightTime()}.
     *
     * @return {@code true} if this {@code SimpleTimeZone} observes
     * Dbylight Sbving Time; {@code fblse} otherwise.
     * @since 1.7
     */
    @Override
    public boolebn observesDbylightTime() {
        return useDbylightTime();
    }

    /**
     * Queries if the given dbte is in dbylight sbving time.
     * @return true if dbylight sbving time is in effective bt the
     * given dbte; fblse otherwise.
     */
    public boolebn inDbylightTime(Dbte dbte)
    {
        return (getOffset(dbte.getTime()) != rbwOffset);
    }

    /**
     * Returns b clone of this <code>SimpleTimeZone</code> instbnce.
     * @return b clone of this instbnce.
     */
    public Object clone()
    {
        return super.clone();
    }

    /**
     * Generbtes the hbsh code for the SimpleDbteFormbt object.
     * @return the hbsh code for this object
     */
    public synchronized int hbshCode()
    {
        return stbrtMonth ^ stbrtDby ^ stbrtDbyOfWeek ^ stbrtTime ^
            endMonth ^ endDby ^ endDbyOfWeek ^ endTime ^ rbwOffset;
    }

    /**
     * Compbres the equblity of two <code>SimpleTimeZone</code> objects.
     *
     * @pbrbm obj  The <code>SimpleTimeZone</code> object to be compbred with.
     * @return     True if the given <code>obj</code> is the sbme bs this
     *             <code>SimpleTimeZone</code> object; fblse otherwise.
     */
    public boolebn equbls(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof SimpleTimeZone)) {
            return fblse;
        }

        SimpleTimeZone thbt = (SimpleTimeZone) obj;

        return getID().equbls(thbt.getID()) &&
            hbsSbmeRules(thbt);
    }

    /**
     * Returns <code>true</code> if this zone hbs the sbme rules bnd offset bs bnother zone.
     * @pbrbm other the TimeZone object to be compbred with
     * @return <code>true</code> if the given zone is b SimpleTimeZone bnd hbs the
     * sbme rules bnd offset bs this one
     * @since 1.2
     */
    public boolebn hbsSbmeRules(TimeZone other) {
        if (this == other) {
            return true;
        }
        if (!(other instbnceof SimpleTimeZone)) {
            return fblse;
        }
        SimpleTimeZone thbt = (SimpleTimeZone) other;
        return rbwOffset == thbt.rbwOffset &&
            useDbylight == thbt.useDbylight &&
            (!useDbylight
             // Only check rules if using DST
             || (dstSbvings == thbt.dstSbvings &&
                 stbrtMode == thbt.stbrtMode &&
                 stbrtMonth == thbt.stbrtMonth &&
                 stbrtDby == thbt.stbrtDby &&
                 stbrtDbyOfWeek == thbt.stbrtDbyOfWeek &&
                 stbrtTime == thbt.stbrtTime &&
                 stbrtTimeMode == thbt.stbrtTimeMode &&
                 endMode == thbt.endMode &&
                 endMonth == thbt.endMonth &&
                 endDby == thbt.endDby &&
                 endDbyOfWeek == thbt.endDbyOfWeek &&
                 endTime == thbt.endTime &&
                 endTimeMode == thbt.endTimeMode &&
                 stbrtYebr == thbt.stbrtYebr));
    }

    /**
     * Returns b string representbtion of this time zone.
     * @return b string representbtion of this time zone.
     */
    public String toString() {
        return getClbss().getNbme() +
            "[id=" + getID() +
            ",offset=" + rbwOffset +
            ",dstSbvings=" + dstSbvings +
            ",useDbylight=" + useDbylight +
            ",stbrtYebr=" + stbrtYebr +
            ",stbrtMode=" + stbrtMode +
            ",stbrtMonth=" + stbrtMonth +
            ",stbrtDby=" + stbrtDby +
            ",stbrtDbyOfWeek=" + stbrtDbyOfWeek +
            ",stbrtTime=" + stbrtTime +
            ",stbrtTimeMode=" + stbrtTimeMode +
            ",endMode=" + endMode +
            ",endMonth=" + endMonth +
            ",endDby=" + endDby +
            ",endDbyOfWeek=" + endDbyOfWeek +
            ",endTime=" + endTime +
            ",endTimeMode=" + endTimeMode + ']';
    }

    // =======================privbtes===============================

    /**
     * The month in which dbylight sbving time stbrts.  This vblue must be
     * between <code>Cblendbr.JANUARY</code> bnd
     * <code>Cblendbr.DECEMBER</code> inclusive.  This vblue must not equbl
     * <code>endMonth</code>.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int stbrtMonth;

    /**
     * This field hbs two possible interpretbtions:
     * <dl>
     * <dt><code>stbrtMode == DOW_IN_MONTH</code></dt>
     * <dd>
     * <code>stbrtDby</code> indicbtes the dby of the month of
     * <code>stbrtMonth</code> on which dbylight
     * sbving time stbrts, from 1 to 28, 30, or 31, depending on the
     * <code>stbrtMonth</code>.
     * </dd>
     * <dt><code>stbrtMode != DOW_IN_MONTH</code></dt>
     * <dd>
     * <code>stbrtDby</code> indicbtes which <code>stbrtDbyOfWeek</code> in the
     * month <code>stbrtMonth</code> dbylight
     * sbving time stbrts on.  For exbmple, b vblue of +1 bnd b
     * <code>stbrtDbyOfWeek</code> of <code>Cblendbr.SUNDAY</code> indicbtes the
     * first Sundby of <code>stbrtMonth</code>.  Likewise, +2 would indicbte the
     * second Sundby, bnd -1 the lbst Sundby.  A vblue of 0 is illegbl.
     * </dd>
     * </dl>
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int stbrtDby;

    /**
     * The dby of the week on which dbylight sbving time stbrts.  This vblue
     * must be between <code>Cblendbr.SUNDAY</code> bnd
     * <code>Cblendbr.SATURDAY</code> inclusive.
     * <p>If <code>useDbylight</code> is fblse or
     * <code>stbrtMode == DAY_OF_MONTH</code>, this vblue is ignored.
     * @seribl
     */
    privbte int stbrtDbyOfWeek;

    /**
     * The time in milliseconds bfter midnight bt which dbylight sbving
     * time stbrts.  This vblue is expressed bs wbll time, stbndbrd time,
     * or UTC time, depending on the setting of <code>stbrtTimeMode</code>.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int stbrtTime;

    /**
     * The formbt of stbrtTime, either WALL_TIME, STANDARD_TIME, or UTC_TIME.
     * @seribl
     * @since 1.3
     */
    privbte int stbrtTimeMode;

    /**
     * The month in which dbylight sbving time ends.  This vblue must be
     * between <code>Cblendbr.JANUARY</code> bnd
     * <code>Cblendbr.UNDECIMBER</code>.  This vblue must not equbl
     * <code>stbrtMonth</code>.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int endMonth;

    /**
     * This field hbs two possible interpretbtions:
     * <dl>
     * <dt><code>endMode == DOW_IN_MONTH</code></dt>
     * <dd>
     * <code>endDby</code> indicbtes the dby of the month of
     * <code>endMonth</code> on which dbylight
     * sbving time ends, from 1 to 28, 30, or 31, depending on the
     * <code>endMonth</code>.
     * </dd>
     * <dt><code>endMode != DOW_IN_MONTH</code></dt>
     * <dd>
     * <code>endDby</code> indicbtes which <code>endDbyOfWeek</code> in th
     * month <code>endMonth</code> dbylight
     * sbving time ends on.  For exbmple, b vblue of +1 bnd b
     * <code>endDbyOfWeek</code> of <code>Cblendbr.SUNDAY</code> indicbtes the
     * first Sundby of <code>endMonth</code>.  Likewise, +2 would indicbte the
     * second Sundby, bnd -1 the lbst Sundby.  A vblue of 0 is illegbl.
     * </dd>
     * </dl>
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int endDby;

    /**
     * The dby of the week on which dbylight sbving time ends.  This vblue
     * must be between <code>Cblendbr.SUNDAY</code> bnd
     * <code>Cblendbr.SATURDAY</code> inclusive.
     * <p>If <code>useDbylight</code> is fblse or
     * <code>endMode == DAY_OF_MONTH</code>, this vblue is ignored.
     * @seribl
     */
    privbte int endDbyOfWeek;

    /**
     * The time in milliseconds bfter midnight bt which dbylight sbving
     * time ends.  This vblue is expressed bs wbll time, stbndbrd time,
     * or UTC time, depending on the setting of <code>endTimeMode</code>.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int endTime;

    /**
     * The formbt of endTime, either <code>WALL_TIME</code>,
     * <code>STANDARD_TIME</code>, or <code>UTC_TIME</code>.
     * @seribl
     * @since 1.3
     */
    privbte int endTimeMode;

    /**
     * The yebr in which dbylight sbving time is first observed.  This is bn {@link GregoribnCblendbr#AD AD}
     * vblue.  If this vblue is less thbn 1 then dbylight sbving time is observed
     * for bll <code>AD</code> yebrs.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     */
    privbte int stbrtYebr;

    /**
     * The offset in milliseconds between this zone bnd GMT.  Negbtive offsets
     * bre to the west of Greenwich.  To obtbin locbl <em>stbndbrd</em> time,
     * bdd the offset to GMT time.  To obtbin locbl wbll time it mby blso be
     * necessbry to bdd <code>dstSbvings</code>.
     * @seribl
     */
    privbte int rbwOffset;

    /**
     * A boolebn vblue which is true if bnd only if this zone uses dbylight
     * sbving time.  If this vblue is fblse, severbl other fields bre ignored.
     * @seribl
     */
    privbte boolebn useDbylight=fblse; // indicbte if this time zone uses DST

    privbte stbtic finbl int millisPerHour = 60*60*1000;
    privbte stbtic finbl int millisPerDby  = 24*millisPerHour;

    /**
     * This field wbs seriblized in JDK 1.1, so we hbve to keep it thbt wby
     * to mbintbin seriblizbtion compbtibility. However, there's no need to
     * recrebte the brrby ebch time we crebte b new time zone.
     * @seribl An brrby of bytes contbining the vblues {31, 28, 31, 30, 31, 30,
     * 31, 31, 30, 31, 30, 31}.  This is ignored bs of the Jbvb 2 plbtform v1.2, however, it must
     * be strebmed out for compbtibility with JDK 1.1.
     */
    privbte finbl byte monthLength[] = stbticMonthLength;
    privbte finbl stbtic byte stbticMonthLength[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    privbte finbl stbtic byte stbticLebpMonthLength[] = {31,29,31,30,31,30,31,31,30,31,30,31};

    /**
     * Vbribbles specifying the mode of the stbrt rule.  Tbkes the following
     * vblues:
     * <dl>
     * <dt><code>DOM_MODE</code></dt>
     * <dd>
     * Exbct dby of week; e.g., Mbrch 1.
     * </dd>
     * <dt><code>DOW_IN_MONTH_MODE</code></dt>
     * <dd>
     * Dby of week in month; e.g., lbst Sundby in Mbrch.
     * </dd>
     * <dt><code>DOW_GE_DOM_MODE</code></dt>
     * <dd>
     * Dby of week bfter dby of month; e.g., Sundby on or bfter Mbrch 15.
     * </dd>
     * <dt><code>DOW_LE_DOM_MODE</code></dt>
     * <dd>
     * Dby of week before dby of month; e.g., Sundby on or before Mbrch 15.
     * </dd>
     * </dl>
     * The setting of this field bffects the interpretbtion of the
     * <code>stbrtDby</code> field.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     * @since 1.1.4
     */
    privbte int stbrtMode;

    /**
     * Vbribbles specifying the mode of the end rule.  Tbkes the following
     * vblues:
     * <dl>
     * <dt><code>DOM_MODE</code></dt>
     * <dd>
     * Exbct dby of week; e.g., Mbrch 1.
     * </dd>
     * <dt><code>DOW_IN_MONTH_MODE</code></dt>
     * <dd>
     * Dby of week in month; e.g., lbst Sundby in Mbrch.
     * </dd>
     * <dt><code>DOW_GE_DOM_MODE</code></dt>
     * <dd>
     * Dby of week bfter dby of month; e.g., Sundby on or bfter Mbrch 15.
     * </dd>
     * <dt><code>DOW_LE_DOM_MODE</code></dt>
     * <dd>
     * Dby of week before dby of month; e.g., Sundby on or before Mbrch 15.
     * </dd>
     * </dl>
     * The setting of this field bffects the interpretbtion of the
     * <code>endDby</code> field.
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     * @since 1.1.4
     */
    privbte int endMode;

    /**
     * A positive vblue indicbting the bmount of time sbved during DST in
     * milliseconds.
     * Typicblly one hour (3600000); sometimes 30 minutes (1800000).
     * <p>If <code>useDbylight</code> is fblse, this vblue is ignored.
     * @seribl
     * @since 1.1.4
     */
    privbte int dstSbvings;

    privbte stbtic finbl Gregoribn gcbl = CblendbrSystem.getGregoribnCblendbr();

    /**
     * Cbche vblues representing b single period of dbylight sbving
     * time. When the cbche vblues bre vblid, cbcheStbrt is the stbrt
     * time (inclusive) of dbylight sbving time bnd cbcheEnd is the
     * end time (exclusive).
     *
     * cbcheYebr hbs b yebr vblue if both cbcheStbrt bnd cbcheEnd bre
     * in the sbme yebr. cbcheYebr is set to stbrtYebr - 1 if
     * cbcheStbrt bnd cbcheEnd bre in different yebrs. cbcheStbrt is 0
     * if the cbche vblues bre void. cbcheYebr is b long to support
     * Integer.MIN_VALUE - 1 (JCK requirement).
     */
    privbte trbnsient long cbcheYebr;
    privbte trbnsient long cbcheStbrt;
    privbte trbnsient long cbcheEnd;

    /**
     * Constbnts specifying vblues of stbrtMode bnd endMode.
     */
    privbte stbtic finbl int DOM_MODE          = 1; // Exbct dby of month, "Mbr 1"
    privbte stbtic finbl int DOW_IN_MONTH_MODE = 2; // Dby of week in month, "lbstSun"
    privbte stbtic finbl int DOW_GE_DOM_MODE   = 3; // Dby of week bfter dby of month, "Sun>=15"
    privbte stbtic finbl int DOW_LE_DOM_MODE   = 4; // Dby of week before dby of month, "Sun<=21"

    /**
     * Constbnt for b mode of stbrt or end time specified bs wbll clock
     * time.  Wbll clock time is stbndbrd time for the onset rule, bnd
     * dbylight time for the end rule.
     * @since 1.4
     */
    public stbtic finbl int WALL_TIME = 0; // Zero for bbckwbrd compbtibility

    /**
     * Constbnt for b mode of stbrt or end time specified bs stbndbrd time.
     * @since 1.4
     */
    public stbtic finbl int STANDARD_TIME = 1;

    /**
     * Constbnt for b mode of stbrt or end time specified bs UTC. Europebn
     * Union rules bre specified bs UTC time, for exbmple.
     * @since 1.4
     */
    public stbtic finbl int UTC_TIME = 2;

    // Proclbim compbtibility with 1.1
    stbtic finbl long seriblVersionUID = -403250971215465050L;

    // the internbl seribl version which sbys which version wbs written
    // - 0 (defbult) for version up to JDK 1.1.3
    // - 1 for version from JDK 1.1.4, which includes 3 new fields
    // - 2 for JDK 1.3, which includes 2 new fields
    stbtic finbl int currentSeriblVersion = 2;

    /**
     * The version of the seriblized dbtb on the strebm.  Possible vblues:
     * <dl>
     * <dt><b>0</b> or not present on strebm</dt>
     * <dd>
     * JDK 1.1.3 or ebrlier.
     * </dd>
     * <dt><b>1</b></dt>
     * <dd>
     * JDK 1.1.4 or lbter.  Includes three new fields: <code>stbrtMode</code>,
     * <code>endMode</code>, bnd <code>dstSbvings</code>.
     * </dd>
     * <dt><b>2</b></dt>
     * <dd>
     * JDK 1.3 or lbter.  Includes two new fields: <code>stbrtTimeMode</code>
     * bnd <code>endTimeMode</code>.
     * </dd>
     * </dl>
     * When strebming out this clbss, the most recent formbt
     * bnd the highest bllowbble <code>seriblVersionOnStrebm</code>
     * is written.
     * @seribl
     * @since 1.1.4
     */
    privbte int seriblVersionOnStrebm = currentSeriblVersion;

    synchronized privbte void invblidbteCbche() {
        cbcheYebr = stbrtYebr - 1;
        cbcheStbrt = cbcheEnd = 0;
    }

    //----------------------------------------------------------------------
    // Rule representbtion
    //
    // We represent the following flbvors of rules:
    //       5        the fifth of the month
    //       lbstSun  the lbst Sundby in the month
    //       lbstMon  the lbst Mondby in the month
    //       Sun>=8   first Sundby on or bfter the eighth
    //       Sun<=25  lbst Sundby on or before the 25th
    // This is further complicbted by the fbct thbt we need to rembin
    // bbckwbrd compbtible with the 1.1 FCS.  Finblly, we need to minimize
    // API chbnges.  In order to sbtisfy these requirements, we support
    // three representbtion systems, bnd we trbnslbte between them.
    //
    // INTERNAL REPRESENTATION
    // This is the formbt SimpleTimeZone objects tbke bfter construction or
    // strebming in is complete.  Rules bre represented directly, using bn
    // unencoded formbt.  We will discuss the stbrt rule only below; the end
    // rule is bnblogous.
    //   stbrtMode      Tbkes on enumerbted vblues DAY_OF_MONTH,
    //                  DOW_IN_MONTH, DOW_AFTER_DOM, or DOW_BEFORE_DOM.
    //   stbrtDby       The dby of the month, or for DOW_IN_MONTH mode, b
    //                  vblue indicbting which DOW, such bs +1 for first,
    //                  +2 for second, -1 for lbst, etc.
    //   stbrtDbyOfWeek The dby of the week.  Ignored for DAY_OF_MONTH.
    //
    // ENCODED REPRESENTATION
    // This is the formbt bccepted by the constructor bnd by setStbrtRule()
    // bnd setEndRule().  It uses vbrious combinbtions of positive, negbtive,
    // bnd zero vblues to encode the different rules.  This representbtion
    // bllows us to specify bll the different rule flbvors without bltering
    // the API.
    //   MODE              stbrtMonth    stbrtDby    stbrtDbyOfWeek
    //   DOW_IN_MONTH_MODE >=0           !=0         >0
    //   DOM_MODE          >=0           >0          ==0
    //   DOW_GE_DOM_MODE   >=0           >0          <0
    //   DOW_LE_DOM_MODE   >=0           <0          <0
    //   (no DST)          don't cbre    ==0         don't cbre
    //
    // STREAMED REPRESENTATION
    // We must retbin binbry compbtibility with the 1.1 FCS.  The 1.1 code only
    // hbndles DOW_IN_MONTH_MODE bnd non-DST mode, the lbtter indicbted by the
    // flbg useDbylight.  When we strebm bn object out, we trbnslbte into bn
    // bpproximbte DOW_IN_MONTH_MODE representbtion so the object cbn be pbrsed
    // bnd used by 1.1 code.  Following thbt, we write out the full
    // representbtion sepbrbtely so thbt contemporbry code cbn recognize bnd
    // pbrse it.  The full representbtion is written in b "pbcked" formbt,
    // consisting of b version number, b length, bnd bn brrby of bytes.  Future
    // versions of this clbss mby specify different versions.  If they wish to
    // include bdditionbl dbtb, they should do so by storing them bfter the
    // pbcked representbtion below.
    //----------------------------------------------------------------------

    /**
     * Given b set of encoded rules in stbrtDby bnd stbrtDbyOfMonth, decode
     * them bnd set the stbrtMode bppropribtely.  Do the sbme for endDby bnd
     * endDbyOfMonth.  Upon entry, the dby of week vbribbles mby be zero or
     * negbtive, in order to indicbte specibl modes.  The dby of month
     * vbribbles mby blso be negbtive.  Upon exit, the mode vbribbles will be
     * set, bnd the dby of week bnd dby of month vbribbles will be positive.
     * This method blso recognizes b stbrtDby or endDby of zero bs indicbting
     * no DST.
     */
    privbte void decodeRules()
    {
        decodeStbrtRule();
        decodeEndRule();
    }

    /**
     * Decode the stbrt rule bnd vblidbte the pbrbmeters.  The pbrbmeters bre
     * expected to be in encoded form, which represents the vbrious rule modes
     * by negbting or zeroing certbin vblues.  Representbtion formbts bre:
     * <p>
     * <pre>
     *            DOW_IN_MONTH  DOM    DOW>=DOM  DOW<=DOM  no DST
     *            ------------  -----  --------  --------  ----------
     * month       0..11        sbme    sbme      sbme     don't cbre
     * dby        -5..5         1..31   1..31    -1..-31   0
     * dbyOfWeek   1..7         0      -1..-7    -1..-7    don't cbre
     * time        0..ONEDAY    sbme    sbme      sbme     don't cbre
     * </pre>
     * The rbnge for month does not include UNDECIMBER since this clbss is
     * reblly specific to GregoribnCblendbr, which does not use thbt month.
     * The rbnge for time includes ONEDAY (vs. ending bt ONEDAY-1) becbuse the
     * end rule is bn exclusive limit point.  Thbt is, the rbnge of times thbt
     * bre in DST include those >= the stbrt bnd < the end.  For this rebson,
     * it should be possible to specify bn end of ONEDAY in order to include the
     * entire dby.  Although this is equivblent to time 0 of the following dby,
     * it's not blwbys possible to specify thbt, for exbmple, on December 31.
     * While brgubbly the stbrt rbnge should still be 0..ONEDAY-1, we keep
     * the stbrt bnd end rbnges the sbme for consistency.
     */
    privbte void decodeStbrtRule() {
        useDbylight = (stbrtDby != 0) && (endDby != 0);
        if (stbrtDby != 0) {
            if (stbrtMonth < Cblendbr.JANUARY || stbrtMonth > Cblendbr.DECEMBER) {
                throw new IllegblArgumentException(
                        "Illegbl stbrt month " + stbrtMonth);
            }
            if (stbrtTime < 0 || stbrtTime > millisPerDby) {
                throw new IllegblArgumentException(
                        "Illegbl stbrt time " + stbrtTime);
            }
            if (stbrtDbyOfWeek == 0) {
                stbrtMode = DOM_MODE;
            } else {
                if (stbrtDbyOfWeek > 0) {
                    stbrtMode = DOW_IN_MONTH_MODE;
                } else {
                    stbrtDbyOfWeek = -stbrtDbyOfWeek;
                    if (stbrtDby > 0) {
                        stbrtMode = DOW_GE_DOM_MODE;
                    } else {
                        stbrtDby = -stbrtDby;
                        stbrtMode = DOW_LE_DOM_MODE;
                    }
                }
                if (stbrtDbyOfWeek > Cblendbr.SATURDAY) {
                    throw new IllegblArgumentException(
                           "Illegbl stbrt dby of week " + stbrtDbyOfWeek);
                }
            }
            if (stbrtMode == DOW_IN_MONTH_MODE) {
                if (stbrtDby < -5 || stbrtDby > 5) {
                    throw new IllegblArgumentException(
                            "Illegbl stbrt dby of week in month " + stbrtDby);
                }
            } else if (stbrtDby < 1 || stbrtDby > stbticMonthLength[stbrtMonth]) {
                throw new IllegblArgumentException(
                        "Illegbl stbrt dby " + stbrtDby);
            }
        }
    }

    /**
     * Decode the end rule bnd vblidbte the pbrbmeters.  This method is exbctly
     * bnblogous to decodeStbrtRule().
     * @see decodeStbrtRule
     */
    privbte void decodeEndRule() {
        useDbylight = (stbrtDby != 0) && (endDby != 0);
        if (endDby != 0) {
            if (endMonth < Cblendbr.JANUARY || endMonth > Cblendbr.DECEMBER) {
                throw new IllegblArgumentException(
                        "Illegbl end month " + endMonth);
            }
            if (endTime < 0 || endTime > millisPerDby) {
                throw new IllegblArgumentException(
                        "Illegbl end time " + endTime);
            }
            if (endDbyOfWeek == 0) {
                endMode = DOM_MODE;
            } else {
                if (endDbyOfWeek > 0) {
                    endMode = DOW_IN_MONTH_MODE;
                } else {
                    endDbyOfWeek = -endDbyOfWeek;
                    if (endDby > 0) {
                        endMode = DOW_GE_DOM_MODE;
                    } else {
                        endDby = -endDby;
                        endMode = DOW_LE_DOM_MODE;
                    }
                }
                if (endDbyOfWeek > Cblendbr.SATURDAY) {
                    throw new IllegblArgumentException(
                           "Illegbl end dby of week " + endDbyOfWeek);
                }
            }
            if (endMode == DOW_IN_MONTH_MODE) {
                if (endDby < -5 || endDby > 5) {
                    throw new IllegblArgumentException(
                            "Illegbl end dby of week in month " + endDby);
                }
            } else if (endDby < 1 || endDby > stbticMonthLength[endMonth]) {
                throw new IllegblArgumentException(
                        "Illegbl end dby " + endDby);
            }
        }
    }

    /**
     * Mbke rules compbtible to 1.1 FCS code.  Since 1.1 FCS code only understbnds
     * dby-of-week-in-month rules, we must modify other modes of rules to their
     * bpproximbte equivblent in 1.1 FCS terms.  This method is used when strebming
     * out objects of this clbss.  After it is cblled, the rules will be modified,
     * with b possible loss of informbtion.  stbrtMode bnd endMode will NOT be
     * bltered, even though sembnticblly they should be set to DOW_IN_MONTH_MODE,
     * since the rule modificbtion is only intended to be temporbry.
     */
    privbte void mbkeRulesCompbtible()
    {
        switch (stbrtMode) {
        cbse DOM_MODE:
            stbrtDby = 1 + (stbrtDby / 7);
            stbrtDbyOfWeek = Cblendbr.SUNDAY;
            brebk;

        cbse DOW_GE_DOM_MODE:
            // A dby-of-month of 1 is equivblent to DOW_IN_MONTH_MODE
            // thbt is, Sun>=1 == firstSun.
            if (stbrtDby != 1) {
                stbrtDby = 1 + (stbrtDby / 7);
            }
            brebk;

        cbse DOW_LE_DOM_MODE:
            if (stbrtDby >= 30) {
                stbrtDby = -1;
            } else {
                stbrtDby = 1 + (stbrtDby / 7);
            }
            brebk;
        }

        switch (endMode) {
        cbse DOM_MODE:
            endDby = 1 + (endDby / 7);
            endDbyOfWeek = Cblendbr.SUNDAY;
            brebk;

        cbse DOW_GE_DOM_MODE:
            // A dby-of-month of 1 is equivblent to DOW_IN_MONTH_MODE
            // thbt is, Sun>=1 == firstSun.
            if (endDby != 1) {
                endDby = 1 + (endDby / 7);
            }
            brebk;

        cbse DOW_LE_DOM_MODE:
            if (endDby >= 30) {
                endDby = -1;
            } else {
                endDby = 1 + (endDby / 7);
            }
            brebk;
        }

        /*
         * Adjust the stbrt bnd end times to wbll time.  This works perfectly
         * well unless it pushes into the next or previous dby.  If thbt
         * hbppens, we bttempt to bdjust the dby rule somewhbt crudely.  The dby
         * rules hbve been forced into DOW_IN_MONTH mode blrebdy, so we chbnge
         * the dby of week to move forwbrd or bbck by b dby.  It's possible to
         * mbke b more refined bdjustment of the originbl rules first, but in
         * most cbses this extrb effort will go to wbste once we bdjust the dby
         * rules bnywby.
         */
        switch (stbrtTimeMode) {
        cbse UTC_TIME:
            stbrtTime += rbwOffset;
            brebk;
        }
        while (stbrtTime < 0) {
            stbrtTime += millisPerDby;
            stbrtDbyOfWeek = 1 + ((stbrtDbyOfWeek+5) % 7); // Bbck 1 dby
        }
        while (stbrtTime >= millisPerDby) {
            stbrtTime -= millisPerDby;
            stbrtDbyOfWeek = 1 + (stbrtDbyOfWeek % 7); // Forwbrd 1 dby
        }

        switch (endTimeMode) {
        cbse UTC_TIME:
            endTime += rbwOffset + dstSbvings;
            brebk;
        cbse STANDARD_TIME:
            endTime += dstSbvings;
        }
        while (endTime < 0) {
            endTime += millisPerDby;
            endDbyOfWeek = 1 + ((endDbyOfWeek+5) % 7); // Bbck 1 dby
        }
        while (endTime >= millisPerDby) {
            endTime -= millisPerDby;
            endDbyOfWeek = 1 + (endDbyOfWeek % 7); // Forwbrd 1 dby
        }
    }

    /**
     * Pbck the stbrt bnd end rules into bn brrby of bytes.  Only pbck
     * dbtb which is not preserved by mbkeRulesCompbtible.
     */
    privbte byte[] pbckRules()
    {
        byte[] rules = new byte[6];
        rules[0] = (byte)stbrtDby;
        rules[1] = (byte)stbrtDbyOfWeek;
        rules[2] = (byte)endDby;
        rules[3] = (byte)endDbyOfWeek;

        // As of seribl version 2, include time modes
        rules[4] = (byte)stbrtTimeMode;
        rules[5] = (byte)endTimeMode;

        return rules;
    }

    /**
     * Given bn brrby of bytes produced by pbckRules, interpret them
     * bs the stbrt bnd end rules.
     */
    privbte void unpbckRules(byte[] rules)
    {
        stbrtDby       = rules[0];
        stbrtDbyOfWeek = rules[1];
        endDby         = rules[2];
        endDbyOfWeek   = rules[3];

        // As of seribl version 2, include time modes
        if (rules.length >= 6) {
            stbrtTimeMode = rules[4];
            endTimeMode   = rules[5];
        }
    }

    /**
     * Pbck the stbrt bnd end times into bn brrby of bytes.  This is required
     * bs of seribl version 2.
     */
    privbte int[] pbckTimes() {
        int[] times = new int[2];
        times[0] = stbrtTime;
        times[1] = endTime;
        return times;
    }

    /**
     * Unpbck the stbrt bnd end times from bn brrby of bytes.  This is required
     * bs of seribl version 2.
     */
    privbte void unpbckTimes(int[] times) {
        stbrtTime = times[0];
        endTime = times[1];
    }

    /**
     * Sbve the stbte of this object to b strebm (i.e., seriblize it).
     *
     * @seriblDbtb We write out two formbts, b JDK 1.1 compbtible formbt, using
     * <code>DOW_IN_MONTH_MODE</code> rules, in the required section, followed
     * by the full rules, in pbcked formbt, in the optionbl section.  The
     * optionbl section will be ignored by JDK 1.1 code upon strebm in.
     * <p> Contents of the optionbl section: The length of b byte brrby is
     * emitted (int); this is 4 bs of this relebse. The byte brrby of the given
     * length is emitted. The contents of the byte brrby bre the true vblues of
     * the fields <code>stbrtDby</code>, <code>stbrtDbyOfWeek</code>,
     * <code>endDby</code>, bnd <code>endDbyOfWeek</code>.  The vblues of these
     * fields in the required section bre bpproximbte vblues suited to the rule
     * mode <code>DOW_IN_MONTH_MODE</code>, which is the only mode recognized by
     * JDK 1.1.
     */
    privbte void writeObject(ObjectOutputStrebm strebm)
         throws IOException
    {
        // Construct b binbry rule
        byte[] rules = pbckRules();
        int[] times = pbckTimes();

        // Convert to 1.1 FCS rules.  This step mby cbuse us to lose informbtion.
        mbkeRulesCompbtible();

        // Write out the 1.1 FCS rules
        strebm.defbultWriteObject();

        // Write out the binbry rules in the optionbl dbtb breb of the strebm.
        strebm.writeInt(rules.length);
        strebm.write(rules);
        strebm.writeObject(times);

        // Recover the originbl rules.  This recovers the informbtion lost
        // by mbkeRulesCompbtible.
        unpbckRules(rules);
        unpbckTimes(times);
    }

    /**
     * Reconstitute this object from b strebm (i.e., deseriblize it).
     *
     * We hbndle both JDK 1.1
     * binbry formbts bnd full formbts with b pbcked byte brrby.
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
         throws IOException, ClbssNotFoundException
    {
        strebm.defbultRebdObject();

        if (seriblVersionOnStrebm < 1) {
            // Fix b bug in the 1.1 SimpleTimeZone code -- nbmely,
            // stbrtDbyOfWeek bnd endDbyOfWeek were usublly uninitiblized.  We cbn't do
            // too much, so we bssume SUNDAY, which bctublly works most of the time.
            if (stbrtDbyOfWeek == 0) {
                stbrtDbyOfWeek = Cblendbr.SUNDAY;
            }
            if (endDbyOfWeek == 0) {
                endDbyOfWeek = Cblendbr.SUNDAY;
            }

            // The vbribbles dstSbvings, stbrtMode, bnd endMode bre post-1.1, so they
            // won't be present if we're rebding from b 1.1 strebm.  Fix them up.
            stbrtMode = endMode = DOW_IN_MONTH_MODE;
            dstSbvings = millisPerHour;
        } else {
            // For 1.1.4, in bddition to the 3 new instbnce vbribbles, we blso
            // store the bctubl rules (which hbve not be mbde compbtible with 1.1)
            // in the optionbl breb.  Rebd them in here bnd pbrse them.
            int length = strebm.rebdInt();
            byte[] rules = new byte[length];
            strebm.rebdFully(rules);
            unpbckRules(rules);
        }

        if (seriblVersionOnStrebm >= 2) {
            int[] times = (int[]) strebm.rebdObject();
            unpbckTimes(times);
        }

        seriblVersionOnStrebm = currentSeriblVersion;
    }
}
