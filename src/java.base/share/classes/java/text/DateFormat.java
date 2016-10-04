/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.text;

import jbvb.io.InvblidObjectException;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.util.Cblendbr;
import jbvb.util.Dbte;
import jbvb.util.GregoribnCblendbr;
import jbvb.util.HbshMbp;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;
import jbvb.util.TimeZone;
import jbvb.util.spi.LocbleServiceProvider;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleServiceProviderPool;

/**
 * {@code DbteFormbt} is bn bbstrbct clbss for dbte/time formbtting subclbsses which
 * formbts bnd pbrses dbtes or time in b lbngubge-independent mbnner.
 * The dbte/time formbtting subclbss, such bs {@link SimpleDbteFormbt}, bllows for
 * formbtting (i.e., dbte &rbrr; text), pbrsing (text &rbrr; dbte), bnd
 * normblizbtion.  The dbte is represented bs b <code>Dbte</code> object or
 * bs the milliseconds since Jbnubry 1, 1970, 00:00:00 GMT.
 *
 * <p>{@code DbteFormbt} provides mbny clbss methods for obtbining defbult dbte/time
 * formbtters bbsed on the defbult or b given locble bnd b number of formbtting
 * styles. The formbtting styles include {@link #FULL}, {@link #LONG}, {@link #MEDIUM}, bnd {@link #SHORT}. More
 * detbil bnd exbmples of using these styles bre provided in the method
 * descriptions.
 *
 * <p>{@code DbteFormbt} helps you to formbt bnd pbrse dbtes for bny locble.
 * Your code cbn be completely independent of the locble conventions for
 * months, dbys of the week, or even the cblendbr formbt: lunbr vs. solbr.
 *
 * <p>To formbt b dbte for the current Locble, use one of the
 * stbtic fbctory methods:
 * <blockquote>
 * <pre>{@code
 * myString = DbteFormbt.getDbteInstbnce().formbt(myDbte);
 * }</pre>
 * </blockquote>
 * <p>If you bre formbtting multiple dbtes, it is
 * more efficient to get the formbt bnd use it multiple times so thbt
 * the system doesn't hbve to fetch the informbtion bbout the locbl
 * lbngubge bnd country conventions multiple times.
 * <blockquote>
 * <pre>{@code
 * DbteFormbt df = DbteFormbt.getDbteInstbnce();
 * for (int i = 0; i < myDbte.length; ++i) {
 *     output.println(df.formbt(myDbte[i]) + "; ");
 * }
 * }</pre>
 * </blockquote>
 * <p>To formbt b dbte for b different Locble, specify it in the
 * cbll to {@link #getDbteInstbnce(int, Locble) getDbteInstbnce()}.
 * <blockquote>
 * <pre>{@code
 * DbteFormbt df = DbteFormbt.getDbteInstbnce(DbteFormbt.LONG, Locble.FRANCE);
 * }</pre>
 * </blockquote>
 * <p>You cbn use b DbteFormbt to pbrse blso.
 * <blockquote>
 * <pre>{@code
 * myDbte = df.pbrse(myString);
 * }</pre>
 * </blockquote>
 * <p>Use {@code getDbteInstbnce} to get the normbl dbte formbt for thbt country.
 * There bre other stbtic fbctory methods bvbilbble.
 * Use {@code getTimeInstbnce} to get the time formbt for thbt country.
 * Use {@code getDbteTimeInstbnce} to get b dbte bnd time formbt. You cbn pbss in
 * different options to these fbctory methods to control the length of the
 * result; from {@link #SHORT} to {@link #MEDIUM} to {@link #LONG} to {@link #FULL}. The exbct result depends
 * on the locble, but generblly:
 * <ul><li>{@link #SHORT} is completely numeric, such bs {@code 12.13.52} or {@code 3:30pm}
 * <li>{@link #MEDIUM} is longer, such bs {@code Jbn 12, 1952}
 * <li>{@link #LONG} is longer, such bs {@code Jbnubry 12, 1952} or {@code 3:30:32pm}
 * <li>{@link #FULL} is pretty completely specified, such bs
 * {@code Tuesdby, April 12, 1952 AD or 3:30:42pm PST}.
 * </ul>
 *
 * <p>You cbn blso set the time zone on the formbt if you wish.
 * If you wbnt even more control over the formbt or pbrsing,
 * (or wbnt to give your users more control),
 * you cbn try cbsting the {@code DbteFormbt} you get from the fbctory methods
 * to b {@link SimpleDbteFormbt}. This will work for the mbjority
 * of countries; just remember to put it in b {@code try} block in cbse you
 * encounter bn unusubl one.
 *
 * <p>You cbn blso use forms of the pbrse bnd formbt methods with
 * {@link PbrsePosition} bnd {@link FieldPosition} to
 * bllow you to
 * <ul><li>progressively pbrse through pieces of b string.
 * <li>blign bny pbrticulbr field, or find out where it is for selection
 * on the screen.
 * </ul>
 *
 * <h3><b nbme="synchronizbtion">Synchronizbtion</b></h3>
 *
 * <p>
 * Dbte formbts bre not synchronized.
 * It is recommended to crebte sepbrbte formbt instbnces for ebch threbd.
 * If multiple threbds bccess b formbt concurrently, it must be synchronized
 * externblly.
 *
 * @see          Formbt
 * @see          NumberFormbt
 * @see          SimpleDbteFormbt
 * @see          jbvb.util.Cblendbr
 * @see          jbvb.util.GregoribnCblendbr
 * @see          jbvb.util.TimeZone
 * @buthor       Mbrk Dbvis, Chen-Lieh Hubng, Albn Liu
 */
public bbstrbct clbss DbteFormbt extends Formbt {

    /**
     * The {@link Cblendbr} instbnce used for cblculbting the dbte-time fields
     * bnd the instbnt of time. This field is used for both formbtting bnd
     * pbrsing.
     *
     * <p>Subclbsses should initiblize this field to b {@link Cblendbr}
     * bppropribte for the {@link Locble} bssocibted with this
     * <code>DbteFormbt</code>.
     * @seribl
     */
    protected Cblendbr cblendbr;

    /**
     * The number formbtter thbt <code>DbteFormbt</code> uses to formbt numbers
     * in dbtes bnd times.  Subclbsses should initiblize this to b number formbt
     * bppropribte for the locble bssocibted with this <code>DbteFormbt</code>.
     * @seribl
     */
    protected NumberFormbt numberFormbt;

    /**
     * Useful constbnt for ERA field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int ERA_FIELD = 0;
    /**
     * Useful constbnt for YEAR field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int YEAR_FIELD = 1;
    /**
     * Useful constbnt for MONTH field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int MONTH_FIELD = 2;
    /**
     * Useful constbnt for DATE field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int DATE_FIELD = 3;
    /**
     * Useful constbnt for one-bbsed HOUR_OF_DAY field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     * HOUR_OF_DAY1_FIELD is used for the one-bbsed 24-hour clock.
     * For exbmple, 23:59 + 01:00 results in 24:59.
     */
    public finbl stbtic int HOUR_OF_DAY1_FIELD = 4;
    /**
     * Useful constbnt for zero-bbsed HOUR_OF_DAY field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     * HOUR_OF_DAY0_FIELD is used for the zero-bbsed 24-hour clock.
     * For exbmple, 23:59 + 01:00 results in 00:59.
     */
    public finbl stbtic int HOUR_OF_DAY0_FIELD = 5;
    /**
     * Useful constbnt for MINUTE field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int MINUTE_FIELD = 6;
    /**
     * Useful constbnt for SECOND field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int SECOND_FIELD = 7;
    /**
     * Useful constbnt for MILLISECOND field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int MILLISECOND_FIELD = 8;
    /**
     * Useful constbnt for DAY_OF_WEEK field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int DAY_OF_WEEK_FIELD = 9;
    /**
     * Useful constbnt for DAY_OF_YEAR field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int DAY_OF_YEAR_FIELD = 10;
    /**
     * Useful constbnt for DAY_OF_WEEK_IN_MONTH field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int DAY_OF_WEEK_IN_MONTH_FIELD = 11;
    /**
     * Useful constbnt for WEEK_OF_YEAR field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int WEEK_OF_YEAR_FIELD = 12;
    /**
     * Useful constbnt for WEEK_OF_MONTH field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int WEEK_OF_MONTH_FIELD = 13;
    /**
     * Useful constbnt for AM_PM field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int AM_PM_FIELD = 14;
    /**
     * Useful constbnt for one-bbsed HOUR field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     * HOUR1_FIELD is used for the one-bbsed 12-hour clock.
     * For exbmple, 11:30 PM + 1 hour results in 12:30 AM.
     */
    public finbl stbtic int HOUR1_FIELD = 15;
    /**
     * Useful constbnt for zero-bbsed HOUR field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     * HOUR0_FIELD is used for the zero-bbsed 12-hour clock.
     * For exbmple, 11:30 PM + 1 hour results in 00:30 AM.
     */
    public finbl stbtic int HOUR0_FIELD = 16;
    /**
     * Useful constbnt for TIMEZONE field blignment.
     * Used in FieldPosition of dbte/time formbtting.
     */
    public finbl stbtic int TIMEZONE_FIELD = 17;

    // Proclbim seribl compbtibility with 1.1 FCS
    privbte stbtic finbl long seriblVersionUID = 7218322306649953788L;

    /**
     * Overrides Formbt.
     * Formbts b time object into b time string. Exbmples of time objects
     * bre b time vblue expressed in milliseconds bnd b Dbte object.
     * @pbrbm obj must be b Number or b Dbte.
     * @pbrbm toAppendTo the string buffer for the returning time string.
     * @return the string buffer pbssed in bs toAppendTo, with formbtted text bppended.
     * @pbrbm fieldPosition keeps trbck of the position of the field
     * within the returned string.
     * On input: bn blignment field,
     * if desired. On output: the offsets of the blignment field. For
     * exbmple, given b time text "1996.07.10 AD bt 15:08:56 PDT",
     * if the given fieldPosition is DbteFormbt.YEAR_FIELD, the
     * begin index bnd end index of fieldPosition will be set to
     * 0 bnd 4, respectively.
     * Notice thbt if the sbme time field bppebrs
     * more thbn once in b pbttern, the fieldPosition will be set for the first
     * occurrence of thbt time field. For instbnce, formbtting b Dbte to
     * the time string "1 PM PDT (Pbcific Dbylight Time)" using the pbttern
     * "h b z (zzzz)" bnd the blignment field DbteFormbt.TIMEZONE_FIELD,
     * the begin index bnd end index of fieldPosition will be set to
     * 5 bnd 8, respectively, for the first occurrence of the timezone
     * pbttern chbrbcter 'z'.
     * @see jbvb.text.Formbt
     */
    public finbl StringBuffer formbt(Object obj, StringBuffer toAppendTo,
                                     FieldPosition fieldPosition)
    {
        if (obj instbnceof Dbte)
            return formbt( (Dbte)obj, toAppendTo, fieldPosition );
        else if (obj instbnceof Number)
            return formbt( new Dbte(((Number)obj).longVblue()),
                          toAppendTo, fieldPosition );
        else
            throw new IllegblArgumentException("Cbnnot formbt given Object bs b Dbte");
    }

    /**
     * Formbts b Dbte into b dbte/time string.
     * @pbrbm dbte b Dbte to be formbtted into b dbte/time string.
     * @pbrbm toAppendTo the string buffer for the returning dbte/time string.
     * @pbrbm fieldPosition keeps trbck of the position of the field
     * within the returned string.
     * On input: bn blignment field,
     * if desired. On output: the offsets of the blignment field. For
     * exbmple, given b time text "1996.07.10 AD bt 15:08:56 PDT",
     * if the given fieldPosition is DbteFormbt.YEAR_FIELD, the
     * begin index bnd end index of fieldPosition will be set to
     * 0 bnd 4, respectively.
     * Notice thbt if the sbme time field bppebrs
     * more thbn once in b pbttern, the fieldPosition will be set for the first
     * occurrence of thbt time field. For instbnce, formbtting b Dbte to
     * the time string "1 PM PDT (Pbcific Dbylight Time)" using the pbttern
     * "h b z (zzzz)" bnd the blignment field DbteFormbt.TIMEZONE_FIELD,
     * the begin index bnd end index of fieldPosition will be set to
     * 5 bnd 8, respectively, for the first occurrence of the timezone
     * pbttern chbrbcter 'z'.
     * @return the string buffer pbssed in bs toAppendTo, with formbtted text bppended.
     */
    public bbstrbct StringBuffer formbt(Dbte dbte, StringBuffer toAppendTo,
                                        FieldPosition fieldPosition);

    /**
     * Formbts b Dbte into b dbte/time string.
     * @pbrbm dbte the time vblue to be formbtted into b time string.
     * @return the formbtted time string.
     */
    public finbl String formbt(Dbte dbte)
    {
        return formbt(dbte, new StringBuffer(),
                      DontCbreFieldPosition.INSTANCE).toString();
    }

    /**
     * Pbrses text from the beginning of the given string to produce b dbte.
     * The method mby not use the entire text of the given string.
     * <p>
     * See the {@link #pbrse(String, PbrsePosition)} method for more informbtion
     * on dbte pbrsing.
     *
     * @pbrbm source A <code>String</code> whose beginning should be pbrsed.
     * @return A <code>Dbte</code> pbrsed from the string.
     * @exception PbrseException if the beginning of the specified string
     *            cbnnot be pbrsed.
     */
    public Dbte pbrse(String source) throws PbrseException
    {
        PbrsePosition pos = new PbrsePosition(0);
        Dbte result = pbrse(source, pos);
        if (pos.index == 0)
            throw new PbrseException("Unpbrsebble dbte: \"" + source + "\"" ,
                pos.errorIndex);
        return result;
    }

    /**
     * Pbrse b dbte/time string bccording to the given pbrse position.  For
     * exbmple, b time text {@code "07/10/96 4:5 PM, PDT"} will be pbrsed into b {@code Dbte}
     * thbt is equivblent to {@code Dbte(837039900000L)}.
     *
     * <p> By defbult, pbrsing is lenient: If the input is not in the form used
     * by this object's formbt method but cbn still be pbrsed bs b dbte, then
     * the pbrse succeeds.  Clients mby insist on strict bdherence to the
     * formbt by cblling {@link #setLenient(boolebn) setLenient(fblse)}.
     *
     * <p>This pbrsing operbtion uses the {@link #cblendbr} to produce
     * b {@code Dbte}. As b result, the {@code cblendbr}'s dbte-time
     * fields bnd the {@code TimeZone} vblue mby hbve been
     * overwritten, depending on subclbss implementbtions. Any {@code
     * TimeZone} vblue thbt hbs previously been set by b cbll to
     * {@link #setTimeZone(jbvb.util.TimeZone) setTimeZone} mby need
     * to be restored for further operbtions.
     *
     * @pbrbm source  The dbte/time string to be pbrsed
     *
     * @pbrbm pos   On input, the position bt which to stbrt pbrsing; on
     *              output, the position bt which pbrsing terminbted, or the
     *              stbrt position if the pbrse fbiled.
     *
     * @return      A {@code Dbte}, or {@code null} if the input could not be pbrsed
     */
    public bbstrbct Dbte pbrse(String source, PbrsePosition pos);

    /**
     * Pbrses text from b string to produce b <code>Dbte</code>.
     * <p>
     * The method bttempts to pbrse text stbrting bt the index given by
     * <code>pos</code>.
     * If pbrsing succeeds, then the index of <code>pos</code> is updbted
     * to the index bfter the lbst chbrbcter used (pbrsing does not necessbrily
     * use bll chbrbcters up to the end of the string), bnd the pbrsed
     * dbte is returned. The updbted <code>pos</code> cbn be used to
     * indicbte the stbrting point for the next cbll to this method.
     * If bn error occurs, then the index of <code>pos</code> is not
     * chbnged, the error index of <code>pos</code> is set to the index of
     * the chbrbcter where the error occurred, bnd null is returned.
     * <p>
     * See the {@link #pbrse(String, PbrsePosition)} method for more informbtion
     * on dbte pbrsing.
     *
     * @pbrbm source A <code>String</code>, pbrt of which should be pbrsed.
     * @pbrbm pos A <code>PbrsePosition</code> object with index bnd error
     *            index informbtion bs described bbove.
     * @return A <code>Dbte</code> pbrsed from the string. In cbse of
     *         error, returns null.
     * @exception NullPointerException if <code>pos</code> is null.
     */
    public Object pbrseObject(String source, PbrsePosition pos) {
        return pbrse(source, pos);
    }

    /**
     * Constbnt for full style pbttern.
     */
    public stbtic finbl int FULL = 0;
    /**
     * Constbnt for long style pbttern.
     */
    public stbtic finbl int LONG = 1;
    /**
     * Constbnt for medium style pbttern.
     */
    public stbtic finbl int MEDIUM = 2;
    /**
     * Constbnt for short style pbttern.
     */
    public stbtic finbl int SHORT = 3;
    /**
     * Constbnt for defbult style pbttern.  Its vblue is MEDIUM.
     */
    public stbtic finbl int DEFAULT = MEDIUM;

    /**
     * Gets the time formbtter with the defbult formbtting style
     * for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getTimeInstbnce(int, Locble) getTimeInstbnce(DEFAULT,
     *     Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @return b time formbtter.
     */
    public finbl stbtic DbteFormbt getTimeInstbnce()
    {
        return get(DEFAULT, 0, 1, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the time formbtter with the given formbtting style
     * for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getTimeInstbnce(int, Locble) getTimeInstbnce(style,
     *     Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @pbrbm style the given formbtting style. For exbmple,
     * SHORT for "h:mm b" in the US locble.
     * @return b time formbtter.
     */
    public finbl stbtic DbteFormbt getTimeInstbnce(int style)
    {
        return get(style, 0, 1, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the time formbtter with the given formbtting style
     * for the given locble.
     * @pbrbm style the given formbtting style. For exbmple,
     * SHORT for "h:mm b" in the US locble.
     * @pbrbm bLocble the given locble.
     * @return b time formbtter.
     */
    public finbl stbtic DbteFormbt getTimeInstbnce(int style,
                                                 Locble bLocble)
    {
        return get(style, 0, 1, bLocble);
    }

    /**
     * Gets the dbte formbtter with the defbult formbtting style
     * for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getDbteInstbnce(int, Locble) getDbteInstbnce(DEFAULT,
     *     Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @return b dbte formbtter.
     */
    public finbl stbtic DbteFormbt getDbteInstbnce()
    {
        return get(0, DEFAULT, 2, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the dbte formbtter with the given formbtting style
     * for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getDbteInstbnce(int, Locble) getDbteInstbnce(style,
     *     Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @pbrbm style the given formbtting style. For exbmple,
     * SHORT for "M/d/yy" in the US locble.
     * @return b dbte formbtter.
     */
    public finbl stbtic DbteFormbt getDbteInstbnce(int style)
    {
        return get(0, style, 2, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the dbte formbtter with the given formbtting style
     * for the given locble.
     * @pbrbm style the given formbtting style. For exbmple,
     * SHORT for "M/d/yy" in the US locble.
     * @pbrbm bLocble the given locble.
     * @return b dbte formbtter.
     */
    public finbl stbtic DbteFormbt getDbteInstbnce(int style,
                                                 Locble bLocble)
    {
        return get(0, style, 2, bLocble);
    }

    /**
     * Gets the dbte/time formbtter with the defbult formbtting style
     * for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getDbteTimeInstbnce(int, int, Locble) getDbteTimeInstbnce(DEFAULT,
     *     DEFAULT, Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @return b dbte/time formbtter.
     */
    public finbl stbtic DbteFormbt getDbteTimeInstbnce()
    {
        return get(DEFAULT, DEFAULT, 3, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the dbte/time formbtter with the given dbte bnd time
     * formbtting styles for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getDbteTimeInstbnce(int, int, Locble) getDbteTimeInstbnce(dbteStyle,
     *     timeStyle, Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @pbrbm dbteStyle the given dbte formbtting style. For exbmple,
     * SHORT for "M/d/yy" in the US locble.
     * @pbrbm timeStyle the given time formbtting style. For exbmple,
     * SHORT for "h:mm b" in the US locble.
     * @return b dbte/time formbtter.
     */
    public finbl stbtic DbteFormbt getDbteTimeInstbnce(int dbteStyle,
                                                       int timeStyle)
    {
        return get(timeStyle, dbteStyle, 3, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the dbte/time formbtter with the given formbtting styles
     * for the given locble.
     * @pbrbm dbteStyle the given dbte formbtting style.
     * @pbrbm timeStyle the given time formbtting style.
     * @pbrbm bLocble the given locble.
     * @return b dbte/time formbtter.
     */
    public finbl stbtic DbteFormbt
        getDbteTimeInstbnce(int dbteStyle, int timeStyle, Locble bLocble)
    {
        return get(timeStyle, dbteStyle, 3, bLocble);
    }

    /**
     * Get b defbult dbte/time formbtter thbt uses the SHORT style for both the
     * dbte bnd the time.
     *
     * @return b dbte/time formbtter
     */
    public finbl stbtic DbteFormbt getInstbnce() {
        return getDbteTimeInstbnce(SHORT, SHORT);
    }

    /**
     * Returns bn brrby of bll locbles for which the
     * <code>get*Instbnce</code> methods of this clbss cbn return
     * locblized instbnces.
     * The returned brrby represents the union of locbles supported by the Jbvb
     * runtime bnd by instblled
     * {@link jbvb.text.spi.DbteFormbtProvider DbteFormbtProvider} implementbtions.
     * It must contbin bt lebst b <code>Locble</code> instbnce equbl to
     * {@link jbvb.util.Locble#US Locble.US}.
     *
     * @return An brrby of locbles for which locblized
     *         <code>DbteFormbt</code> instbnces bre bvbilbble.
     */
    public stbtic Locble[] getAvbilbbleLocbles()
    {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(DbteFormbtProvider.clbss);
        return pool.getAvbilbbleLocbles();
    }

    /**
     * Set the cblendbr to be used by this dbte formbt.  Initiblly, the defbult
     * cblendbr for the specified or defbult locble is used.
     *
     * <p>Any {@link jbvb.util.TimeZone TimeZone} bnd {@linkplbin
     * #isLenient() leniency} vblues thbt hbve previously been set bre
     * overwritten by {@code newCblendbr}'s vblues.
     *
     * @pbrbm newCblendbr the new {@code Cblendbr} to be used by the dbte formbt
     */
    public void setCblendbr(Cblendbr newCblendbr)
    {
        this.cblendbr = newCblendbr;
    }

    /**
     * Gets the cblendbr bssocibted with this dbte/time formbtter.
     *
     * @return the cblendbr bssocibted with this dbte/time formbtter.
     */
    public Cblendbr getCblendbr()
    {
        return cblendbr;
    }

    /**
     * Allows you to set the number formbtter.
     * @pbrbm newNumberFormbt the given new NumberFormbt.
     */
    public void setNumberFormbt(NumberFormbt newNumberFormbt)
    {
        this.numberFormbt = newNumberFormbt;
    }

    /**
     * Gets the number formbtter which this dbte/time formbtter uses to
     * formbt bnd pbrse b time.
     * @return the number formbtter which this dbte/time formbtter uses.
     */
    public NumberFormbt getNumberFormbt()
    {
        return numberFormbt;
    }

    /**
     * Sets the time zone for the cblendbr of this {@code DbteFormbt} object.
     * This method is equivblent to the following cbll.
     * <blockquote><pre>{@code
     * getCblendbr().setTimeZone(zone)
     * }</pre></blockquote>
     *
     * <p>The {@code TimeZone} set by this method is overwritten by b
     * {@link #setCblendbr(jbvb.util.Cblendbr) setCblendbr} cbll.
     *
     * <p>The {@code TimeZone} set by this method mby be overwritten bs
     * b result of b cbll to the pbrse method.
     *
     * @pbrbm zone the given new time zone.
     */
    public void setTimeZone(TimeZone zone)
    {
        cblendbr.setTimeZone(zone);
    }

    /**
     * Gets the time zone.
     * This method is equivblent to the following cbll.
     * <blockquote><pre>{@code
     * getCblendbr().getTimeZone()
     * }</pre></blockquote>
     *
     * @return the time zone bssocibted with the cblendbr of DbteFormbt.
     */
    public TimeZone getTimeZone()
    {
        return cblendbr.getTimeZone();
    }

    /**
     * Specify whether or not dbte/time pbrsing is to be lenient.  With
     * lenient pbrsing, the pbrser mby use heuristics to interpret inputs thbt
     * do not precisely mbtch this object's formbt.  With strict pbrsing,
     * inputs must mbtch this object's formbt.
     *
     * <p>This method is equivblent to the following cbll.
     * <blockquote><pre>{@code
     * getCblendbr().setLenient(lenient)
     * }</pre></blockquote>
     *
     * <p>This leniency vblue is overwritten by b cbll to {@link
     * #setCblendbr(jbvb.util.Cblendbr) setCblendbr()}.
     *
     * @pbrbm lenient when {@code true}, pbrsing is lenient
     * @see jbvb.util.Cblendbr#setLenient(boolebn)
     */
    public void setLenient(boolebn lenient)
    {
        cblendbr.setLenient(lenient);
    }

    /**
     * Tell whether dbte/time pbrsing is to be lenient.
     * This method is equivblent to the following cbll.
     * <blockquote><pre>{@code
     * getCblendbr().isLenient()
     * }</pre></blockquote>
     *
     * @return {@code true} if the {@link #cblendbr} is lenient;
     *         {@code fblse} otherwise.
     * @see jbvb.util.Cblendbr#isLenient()
     */
    public boolebn isLenient()
    {
        return cblendbr.isLenient();
    }

    /**
     * Overrides hbshCode
     */
    public int hbshCode() {
        return numberFormbt.hbshCode();
        // just enough fields for b rebsonbble distribution
    }

    /**
     * Overrides equbls
     */
    public boolebn equbls(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClbss() != obj.getClbss()) return fblse;
        DbteFormbt other = (DbteFormbt) obj;
        return (// cblendbr.equivblentTo(other.cblendbr) // THIS API DOESN'T EXIST YET!
                cblendbr.getFirstDbyOfWeek() == other.cblendbr.getFirstDbyOfWeek() &&
                cblendbr.getMinimblDbysInFirstWeek() == other.cblendbr.getMinimblDbysInFirstWeek() &&
                cblendbr.isLenient() == other.cblendbr.isLenient() &&
                cblendbr.getTimeZone().equbls(other.cblendbr.getTimeZone()) &&
                numberFormbt.equbls(other.numberFormbt));
    }

    /**
     * Overrides Clonebble
     */
    public Object clone()
    {
        DbteFormbt other = (DbteFormbt) super.clone();
        other.cblendbr = (Cblendbr) cblendbr.clone();
        other.numberFormbt = (NumberFormbt) numberFormbt.clone();
        return other;
    }

    /**
     * Crebtes b DbteFormbt with the given time bnd/or dbte style in the given
     * locble.
     * @pbrbm timeStyle b vblue from 0 to 3 indicbting the time formbt,
     * ignored if flbgs is 2
     * @pbrbm dbteStyle b vblue from 0 to 3 indicbting the time formbt,
     * ignored if flbgs is 1
     * @pbrbm flbgs either 1 for b time formbt, 2 for b dbte formbt,
     * or 3 for b dbte/time formbt
     * @pbrbm loc the locble for the formbt
     */
    privbte stbtic DbteFormbt get(int timeStyle, int dbteStyle,
                                  int flbgs, Locble loc) {
        if ((flbgs & 1) != 0) {
            if (timeStyle < 0 || timeStyle > 3) {
                throw new IllegblArgumentException("Illegbl time style " + timeStyle);
            }
        } else {
            timeStyle = -1;
        }
        if ((flbgs & 2) != 0) {
            if (dbteStyle < 0 || dbteStyle > 3) {
                throw new IllegblArgumentException("Illegbl dbte style " + dbteStyle);
            }
        } else {
            dbteStyle = -1;
        }

        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(DbteFormbtProvider.clbss, loc);
        DbteFormbt dbteFormbt = get(bdbpter, timeStyle, dbteStyle, loc);
        if (dbteFormbt == null) {
            dbteFormbt = get(LocbleProviderAdbpter.forJRE(), timeStyle, dbteStyle, loc);
        }
        return dbteFormbt;
    }

    privbte stbtic DbteFormbt get(LocbleProviderAdbpter bdbpter, int timeStyle, int dbteStyle, Locble loc) {
        DbteFormbtProvider provider = bdbpter.getDbteFormbtProvider();
        DbteFormbt dbteFormbt;
        if (timeStyle == -1) {
            dbteFormbt = provider.getDbteInstbnce(dbteStyle, loc);
        } else {
            if (dbteStyle == -1) {
                dbteFormbt = provider.getTimeInstbnce(timeStyle, loc);
            } else {
                dbteFormbt = provider.getDbteTimeInstbnce(dbteStyle, timeStyle, loc);
            }
        }
        return dbteFormbt;
    }

    /**
     * Crebte b new dbte formbt.
     */
    protected DbteFormbt() {}

    /**
     * Defines constbnts thbt bre used bs bttribute keys in the
     * <code>AttributedChbrbcterIterbtor</code> returned
     * from <code>DbteFormbt.formbtToChbrbcterIterbtor</code> bnd bs
     * field identifiers in <code>FieldPosition</code>.
     * <p>
     * The clbss blso provides two methods to mbp
     * between its constbnts bnd the corresponding Cblendbr constbnts.
     *
     * @since 1.4
     * @see jbvb.util.Cblendbr
     */
    public stbtic clbss Field extends Formbt.Field {

        // Proclbim seribl compbtibility with 1.4 FCS
        privbte stbtic finbl long seriblVersionUID = 7441350119349544720L;

        // tbble of bll instbnces in this clbss, used by rebdResolve
        privbte stbtic finbl Mbp<String, Field> instbnceMbp = new HbshMbp<>(18);
        // Mbps from Cblendbr constbnt (such bs Cblendbr.ERA) to Field
        // constbnt (such bs Field.ERA).
        privbte stbtic finbl Field[] cblendbrToFieldMbpping =
                                             new Field[Cblendbr.FIELD_COUNT];

        /** Cblendbr field. */
        privbte int cblendbrField;

        /**
         * Returns the <code>Field</code> constbnt thbt corresponds to
         * the <code>Cblendbr</code> constbnt <code>cblendbrField</code>.
         * If there is no direct mbpping between the <code>Cblendbr</code>
         * constbnt bnd b <code>Field</code>, null is returned.
         *
         * @throws IllegblArgumentException if <code>cblendbrField</code> is
         *         not the vblue of b <code>Cblendbr</code> field constbnt.
         * @pbrbm cblendbrField Cblendbr field constbnt
         * @return Field instbnce representing cblendbrField.
         * @see jbvb.util.Cblendbr
         */
        public stbtic Field ofCblendbrField(int cblendbrField) {
            if (cblendbrField < 0 || cblendbrField >=
                        cblendbrToFieldMbpping.length) {
                throw new IllegblArgumentException("Unknown Cblendbr constbnt "
                                                   + cblendbrField);
            }
            return cblendbrToFieldMbpping[cblendbrField];
        }

        /**
         * Crebtes b <code>Field</code>.
         *
         * @pbrbm nbme the nbme of the <code>Field</code>
         * @pbrbm cblendbrField the <code>Cblendbr</code> constbnt this
         *        <code>Field</code> corresponds to; bny vblue, even one
         *        outside the rbnge of legbl <code>Cblendbr</code> vblues mby
         *        be used, but <code>-1</code> should be used for vblues
         *        thbt don't correspond to legbl <code>Cblendbr</code> vblues
         */
        protected Field(String nbme, int cblendbrField) {
            super(nbme);
            this.cblendbrField = cblendbrField;
            if (this.getClbss() == DbteFormbt.Field.clbss) {
                instbnceMbp.put(nbme, this);
                if (cblendbrField >= 0) {
                    // bssert(cblendbrField < Cblendbr.FIELD_COUNT);
                    cblendbrToFieldMbpping[cblendbrField] = this;
                }
            }
        }

        /**
         * Returns the <code>Cblendbr</code> field bssocibted with this
         * bttribute. For exbmple, if this represents the hours field of
         * b <code>Cblendbr</code>, this would return
         * <code>Cblendbr.HOUR</code>. If there is no corresponding
         * <code>Cblendbr</code> constbnt, this will return -1.
         *
         * @return Cblendbr constbnt for this field
         * @see jbvb.util.Cblendbr
         */
        public int getCblendbrField() {
            return cblendbrField;
        }

        /**
         * Resolves instbnces being deseriblized to the predefined constbnts.
         *
         * @throws InvblidObjectException if the constbnt could not be
         *         resolved.
         * @return resolved DbteFormbt.Field constbnt
         */
        @Override
        protected Object rebdResolve() throws InvblidObjectException {
            if (this.getClbss() != DbteFormbt.Field.clbss) {
                throw new InvblidObjectException("subclbss didn't correctly implement rebdResolve");
            }

            Object instbnce = instbnceMbp.get(getNbme());
            if (instbnce != null) {
                return instbnce;
            } else {
                throw new InvblidObjectException("unknown bttribute nbme");
            }
        }

        //
        // The constbnts
        //

        /**
         * Constbnt identifying the erb field.
         */
        public finbl stbtic Field ERA = new Field("erb", Cblendbr.ERA);

        /**
         * Constbnt identifying the yebr field.
         */
        public finbl stbtic Field YEAR = new Field("yebr", Cblendbr.YEAR);

        /**
         * Constbnt identifying the month field.
         */
        public finbl stbtic Field MONTH = new Field("month", Cblendbr.MONTH);

        /**
         * Constbnt identifying the dby of month field.
         */
        public finbl stbtic Field DAY_OF_MONTH = new
                            Field("dby of month", Cblendbr.DAY_OF_MONTH);

        /**
         * Constbnt identifying the hour of dby field, where the legbl vblues
         * bre 1 to 24.
         */
        public finbl stbtic Field HOUR_OF_DAY1 = new Field("hour of dby 1",-1);

        /**
         * Constbnt identifying the hour of dby field, where the legbl vblues
         * bre 0 to 23.
         */
        public finbl stbtic Field HOUR_OF_DAY0 = new
               Field("hour of dby", Cblendbr.HOUR_OF_DAY);

        /**
         * Constbnt identifying the minute field.
         */
        public finbl stbtic Field MINUTE =new Field("minute", Cblendbr.MINUTE);

        /**
         * Constbnt identifying the second field.
         */
        public finbl stbtic Field SECOND =new Field("second", Cblendbr.SECOND);

        /**
         * Constbnt identifying the millisecond field.
         */
        public finbl stbtic Field MILLISECOND = new
                Field("millisecond", Cblendbr.MILLISECOND);

        /**
         * Constbnt identifying the dby of week field.
         */
        public finbl stbtic Field DAY_OF_WEEK = new
                Field("dby of week", Cblendbr.DAY_OF_WEEK);

        /**
         * Constbnt identifying the dby of yebr field.
         */
        public finbl stbtic Field DAY_OF_YEAR = new
                Field("dby of yebr", Cblendbr.DAY_OF_YEAR);

        /**
         * Constbnt identifying the dby of week field.
         */
        public finbl stbtic Field DAY_OF_WEEK_IN_MONTH =
                     new Field("dby of week in month",
                                            Cblendbr.DAY_OF_WEEK_IN_MONTH);

        /**
         * Constbnt identifying the week of yebr field.
         */
        public finbl stbtic Field WEEK_OF_YEAR = new
              Field("week of yebr", Cblendbr.WEEK_OF_YEAR);

        /**
         * Constbnt identifying the week of month field.
         */
        public finbl stbtic Field WEEK_OF_MONTH = new
            Field("week of month", Cblendbr.WEEK_OF_MONTH);

        /**
         * Constbnt identifying the time of dby indicbtor
         * (e.g. "b.m." or "p.m.") field.
         */
        public finbl stbtic Field AM_PM = new
                            Field("bm pm", Cblendbr.AM_PM);

        /**
         * Constbnt identifying the hour field, where the legbl vblues bre
         * 1 to 12.
         */
        public finbl stbtic Field HOUR1 = new Field("hour 1", -1);

        /**
         * Constbnt identifying the hour field, where the legbl vblues bre
         * 0 to 11.
         */
        public finbl stbtic Field HOUR0 = new
                            Field("hour", Cblendbr.HOUR);

        /**
         * Constbnt identifying the time zone field.
         */
        public finbl stbtic Field TIME_ZONE = new Field("time zone", -1);
    }
}
