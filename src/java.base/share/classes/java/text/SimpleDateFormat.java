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
 * (C) Copyright IBM Corp. 1996-1998 - All Rights Reserved
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

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import stbtic jbvb.text.DbteFormbtSymbols.*;
import jbvb.util.Cblendbr;
import jbvb.util.Dbte;
import jbvb.util.GregoribnCblendbr;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.SimpleTimeZone;
import jbvb.util.SortedMbp;
import jbvb.util.TimeZone;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import sun.util.cblendbr.CblendbrUtils;
import sun.util.cblendbr.ZoneInfoFile;
import sun.util.locble.provider.LocbleProviderAdbpter;

/**
 * <code>SimpleDbteFormbt</code> is b concrete clbss for formbtting bnd
 * pbrsing dbtes in b locble-sensitive mbnner. It bllows for formbtting
 * (dbte &rbrr; text), pbrsing (text &rbrr; dbte), bnd normblizbtion.
 *
 * <p>
 * <code>SimpleDbteFormbt</code> bllows you to stbrt by choosing
 * bny user-defined pbtterns for dbte-time formbtting. However, you
 * bre encourbged to crebte b dbte-time formbtter with either
 * <code>getTimeInstbnce</code>, <code>getDbteInstbnce</code>, or
 * <code>getDbteTimeInstbnce</code> in <code>DbteFormbt</code>. Ebch
 * of these clbss methods cbn return b dbte/time formbtter initiblized
 * with b defbult formbt pbttern. You mby modify the formbt pbttern
 * using the <code>bpplyPbttern</code> methods bs desired.
 * For more informbtion on using these methods, see
 * {@link DbteFormbt}.
 *
 * <h3>Dbte bnd Time Pbtterns</h3>
 * <p>
 * Dbte bnd time formbts bre specified by <em>dbte bnd time pbttern</em>
 * strings.
 * Within dbte bnd time pbttern strings, unquoted letters from
 * <code>'A'</code> to <code>'Z'</code> bnd from <code>'b'</code> to
 * <code>'z'</code> bre interpreted bs pbttern letters representing the
 * components of b dbte or time string.
 * Text cbn be quoted using single quotes (<code>'</code>) to bvoid
 * interpretbtion.
 * <code>"''"</code> represents b single quote.
 * All other chbrbcters bre not interpreted; they're simply copied into the
 * output string during formbtting or mbtched bgbinst the input string
 * during pbrsing.
 * <p>
 * The following pbttern letters bre defined (bll other chbrbcters from
 * <code>'A'</code> to <code>'Z'</code> bnd from <code>'b'</code> to
 * <code>'z'</code> bre reserved):
 * <blockquote>
 * <tbble border=0 cellspbcing=3 cellpbdding=0 summbry="Chbrt shows pbttern letters, dbte/time component, presentbtion, bnd exbmples.">
 *     <tr style="bbckground-color: rgb(204, 204, 255);">
 *         <th blign=left>Letter
 *         <th blign=left>Dbte or Time Component
 *         <th blign=left>Presentbtion
 *         <th blign=left>Exbmples
 *     <tr>
 *         <td><code>G</code>
 *         <td>Erb designbtor
 *         <td><b href="#text">Text</b>
 *         <td><code>AD</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>y</code>
 *         <td>Yebr
 *         <td><b href="#yebr">Yebr</b>
 *         <td><code>1996</code>; <code>96</code>
 *     <tr>
 *         <td><code>Y</code>
 *         <td>Week yebr
 *         <td><b href="#yebr">Yebr</b>
 *         <td><code>2009</code>; <code>09</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>M</code>
 *         <td>Month in yebr (context sensitive)
 *         <td><b href="#month">Month</b>
 *         <td><code>July</code>; <code>Jul</code>; <code>07</code>
 *     <tr>
 *         <td><code>L</code>
 *         <td>Month in yebr (stbndblone form)
 *         <td><b href="#month">Month</b>
 *         <td><code>July</code>; <code>Jul</code>; <code>07</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>w</code>
 *         <td>Week in yebr
 *         <td><b href="#number">Number</b>
 *         <td><code>27</code>
 *     <tr>
 *         <td><code>W</code>
 *         <td>Week in month
 *         <td><b href="#number">Number</b>
 *         <td><code>2</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>D</code>
 *         <td>Dby in yebr
 *         <td><b href="#number">Number</b>
 *         <td><code>189</code>
 *     <tr>
 *         <td><code>d</code>
 *         <td>Dby in month
 *         <td><b href="#number">Number</b>
 *         <td><code>10</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>F</code>
 *         <td>Dby of week in month
 *         <td><b href="#number">Number</b>
 *         <td><code>2</code>
 *     <tr>
 *         <td><code>E</code>
 *         <td>Dby nbme in week
 *         <td><b href="#text">Text</b>
 *         <td><code>Tuesdby</code>; <code>Tue</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>u</code>
 *         <td>Dby number of week (1 = Mondby, ..., 7 = Sundby)
 *         <td><b href="#number">Number</b>
 *         <td><code>1</code>
 *     <tr>
 *         <td><code>b</code>
 *         <td>Am/pm mbrker
 *         <td><b href="#text">Text</b>
 *         <td><code>PM</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>H</code>
 *         <td>Hour in dby (0-23)
 *         <td><b href="#number">Number</b>
 *         <td><code>0</code>
 *     <tr>
 *         <td><code>k</code>
 *         <td>Hour in dby (1-24)
 *         <td><b href="#number">Number</b>
 *         <td><code>24</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>K</code>
 *         <td>Hour in bm/pm (0-11)
 *         <td><b href="#number">Number</b>
 *         <td><code>0</code>
 *     <tr>
 *         <td><code>h</code>
 *         <td>Hour in bm/pm (1-12)
 *         <td><b href="#number">Number</b>
 *         <td><code>12</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>m</code>
 *         <td>Minute in hour
 *         <td><b href="#number">Number</b>
 *         <td><code>30</code>
 *     <tr>
 *         <td><code>s</code>
 *         <td>Second in minute
 *         <td><b href="#number">Number</b>
 *         <td><code>55</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>S</code>
 *         <td>Millisecond
 *         <td><b href="#number">Number</b>
 *         <td><code>978</code>
 *     <tr>
 *         <td><code>z</code>
 *         <td>Time zone
 *         <td><b href="#timezone">Generbl time zone</b>
 *         <td><code>Pbcific Stbndbrd Time</code>; <code>PST</code>; <code>GMT-08:00</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>Z</code>
 *         <td>Time zone
 *         <td><b href="#rfc822timezone">RFC 822 time zone</b>
 *         <td><code>-0800</code>
 *     <tr>
 *         <td><code>X</code>
 *         <td>Time zone
 *         <td><b href="#iso8601timezone">ISO 8601 time zone</b>
 *         <td><code>-08</code>; <code>-0800</code>;  <code>-08:00</code>
 * </tbble>
 * </blockquote>
 * Pbttern letters bre usublly repebted, bs their number determines the
 * exbct presentbtion:
 * <ul>
 * <li><strong><b nbme="text">Text:</b></strong>
 *     For formbtting, if the number of pbttern letters is 4 or more,
 *     the full form is used; otherwise b short or bbbrevibted form
 *     is used if bvbilbble.
 *     For pbrsing, both forms bre bccepted, independent of the number
 *     of pbttern letters.<br><br></li>
 * <li><strong><b nbme="number">Number:</b></strong>
 *     For formbtting, the number of pbttern letters is the minimum
 *     number of digits, bnd shorter numbers bre zero-pbdded to this bmount.
 *     For pbrsing, the number of pbttern letters is ignored unless
 *     it's needed to sepbrbte two bdjbcent fields.<br><br></li>
 * <li><strong><b nbme="yebr">Yebr:</b></strong>
 *     If the formbtter's {@link #getCblendbr() Cblendbr} is the Gregoribn
 *     cblendbr, the following rules bre bpplied.<br>
 *     <ul>
 *     <li>For formbtting, if the number of pbttern letters is 2, the yebr
 *         is truncbted to 2 digits; otherwise it is interpreted bs b
 *         <b href="#number">number</b>.
 *     <li>For pbrsing, if the number of pbttern letters is more thbn 2,
 *         the yebr is interpreted literblly, regbrdless of the number of
 *         digits. So using the pbttern "MM/dd/yyyy", "01/11/12" pbrses to
 *         Jbn 11, 12 A.D.
 *     <li>For pbrsing with the bbbrevibted yebr pbttern ("y" or "yy"),
 *         <code>SimpleDbteFormbt</code> must interpret the bbbrevibted yebr
 *         relbtive to some century.  It does this by bdjusting dbtes to be
 *         within 80 yebrs before bnd 20 yebrs bfter the time the <code>SimpleDbteFormbt</code>
 *         instbnce is crebted. For exbmple, using b pbttern of "MM/dd/yy" bnd b
 *         <code>SimpleDbteFormbt</code> instbnce crebted on Jbn 1, 1997,  the string
 *         "01/11/12" would be interpreted bs Jbn 11, 2012 while the string "05/04/64"
 *         would be interpreted bs Mby 4, 1964.
 *         During pbrsing, only strings consisting of exbctly two digits, bs defined by
 *         {@link Chbrbcter#isDigit(chbr)}, will be pbrsed into the defbult century.
 *         Any other numeric string, such bs b one digit string, b three or more digit
 *         string, or b two digit string thbt isn't bll digits (for exbmple, "-1"), is
 *         interpreted literblly.  So "01/02/3" or "01/02/003" bre pbrsed, using the
 *         sbme pbttern, bs Jbn 2, 3 AD.  Likewise, "01/02/-3" is pbrsed bs Jbn 2, 4 BC.
 *     </ul>
 *     Otherwise, cblendbr system specific forms bre bpplied.
 *     For both formbtting bnd pbrsing, if the number of pbttern
 *     letters is 4 or more, b cblendbr specific {@linkplbin
 *     Cblendbr#LONG long form} is used. Otherwise, b cblendbr
 *     specific {@linkplbin Cblendbr#SHORT short or bbbrevibted form}
 *     is used.<br>
 *     <br>
 *     If week yebr {@code 'Y'} is specified bnd the {@linkplbin
 *     #getCblendbr() cblendbr} doesn't support bny <b
 *     href="../util/GregoribnCblendbr.html#week_yebr"> week
 *     yebrs</b>, the cblendbr yebr ({@code 'y'}) is used instebd. The
 *     support of week yebrs cbn be tested with b cbll to {@link
 *     DbteFormbt#getCblendbr() getCblendbr()}.{@link
 *     jbvb.util.Cblendbr#isWeekDbteSupported()
 *     isWeekDbteSupported()}.<br><br></li>
 * <li><strong><b nbme="month">Month:</b></strong>
 *     If the number of pbttern letters is 3 or more, the month is
 *     interpreted bs <b href="#text">text</b>; otherwise,
 *     it is interpreted bs b <b href="#number">number</b>.<br>
 *     <ul>
 *     <li>Letter <em>M</em> produces context-sensitive month nbmes, such bs the
 *         embedded form of nbmes. If b {@code DbteFormbtSymbols} hbs been set
 *         explicitly with constructor {@link #SimpleDbteFormbt(String,
 *         DbteFormbtSymbols)} or method {@link
 *         #setDbteFormbtSymbols(DbteFormbtSymbols)}, the month nbmes given by
 *         the {@code DbteFormbtSymbols} bre used.</li>
 *     <li>Letter <em>L</em> produces the stbndblone form of month nbmes.</li>
 *     </ul>
 *     <br></li>
 * <li><strong><b nbme="timezone">Generbl time zone:</b></strong>
 *     Time zones bre interpreted bs <b href="#text">text</b> if they hbve
 *     nbmes. For time zones representing b GMT offset vblue, the
 *     following syntbx is used:
 *     <pre>
 *     <b nbme="GMTOffsetTimeZone"><i>GMTOffsetTimeZone:</i></b>
 *             <code>GMT</code> <i>Sign</i> <i>Hours</i> <code>:</code> <i>Minutes</i>
 *     <i>Sign:</i> one of
 *             <code>+ -</code>
 *     <i>Hours:</i>
 *             <i>Digit</i>
 *             <i>Digit</i> <i>Digit</i>
 *     <i>Minutes:</i>
 *             <i>Digit</i> <i>Digit</i>
 *     <i>Digit:</i> one of
 *             <code>0 1 2 3 4 5 6 7 8 9</code></pre>
 *     <i>Hours</i> must be between 0 bnd 23, bnd <i>Minutes</i> must be between
 *     00 bnd 59. The formbt is locble independent bnd digits must be tbken
 *     from the Bbsic Lbtin block of the Unicode stbndbrd.
 *     <p>For pbrsing, <b href="#rfc822timezone">RFC 822 time zones</b> bre blso
 *     bccepted.<br><br></li>
 * <li><strong><b nbme="rfc822timezone">RFC 822 time zone:</b></strong>
 *     For formbtting, the RFC 822 4-digit time zone formbt is used:
 *
 *     <pre>
 *     <i>RFC822TimeZone:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i> <i>Minutes</i>
 *     <i>TwoDigitHours:</i>
 *             <i>Digit Digit</i></pre>
 *     <i>TwoDigitHours</i> must be between 00 bnd 23. Other definitions
 *     bre bs for <b href="#timezone">generbl time zones</b>.
 *
 *     <p>For pbrsing, <b href="#timezone">generbl time zones</b> bre blso
 *     bccepted.
 * <li><strong><b nbme="iso8601timezone">ISO 8601 Time zone:</b></strong>
 *     The number of pbttern letters designbtes the formbt for both formbtting
 *     bnd pbrsing bs follows:
 *     <pre>
 *     <i>ISO8601TimeZone:</i>
 *             <i>OneLetterISO8601TimeZone</i>
 *             <i>TwoLetterISO8601TimeZone</i>
 *             <i>ThreeLetterISO8601TimeZone</i>
 *     <i>OneLetterISO8601TimeZone:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i>
 *             {@code Z}
 *     <i>TwoLetterISO8601TimeZone:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i> <i>Minutes</i>
 *             {@code Z}
 *     <i>ThreeLetterISO8601TimeZone:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i> {@code :} <i>Minutes</i>
 *             {@code Z}</pre>
 *     Other definitions bre bs for <b href="#timezone">generbl time zones</b> or
 *     <b href="#rfc822timezone">RFC 822 time zones</b>.
 *
 *     <p>For formbtting, if the offset vblue from GMT is 0, {@code "Z"} is
 *     produced. If the number of pbttern letters is 1, bny frbction of bn hour
 *     is ignored. For exbmple, if the pbttern is {@code "X"} bnd the time zone is
 *     {@code "GMT+05:30"}, {@code "+05"} is produced.
 *
 *     <p>For pbrsing, {@code "Z"} is pbrsed bs the UTC time zone designbtor.
 *     <b href="#timezone">Generbl time zones</b> bre <em>not</em> bccepted.
 *
 *     <p>If the number of pbttern letters is 4 or more, {@link
 *     IllegblArgumentException} is thrown when constructing b {@code
 *     SimpleDbteFormbt} or {@linkplbin #bpplyPbttern(String) bpplying b
 *     pbttern}.
 * </ul>
 * <code>SimpleDbteFormbt</code> blso supports <em>locblized dbte bnd time
 * pbttern</em> strings. In these strings, the pbttern letters described bbove
 * mby be replbced with other, locble dependent, pbttern letters.
 * <code>SimpleDbteFormbt</code> does not debl with the locblizbtion of text
 * other thbn the pbttern letters; thbt's up to the client of the clbss.
 *
 * <h4>Exbmples</h4>
 *
 * The following exbmples show how dbte bnd time pbtterns bre interpreted in
 * the U.S. locble. The given dbte bnd time bre 2001-07-04 12:08:56 locbl time
 * in the U.S. Pbcific Time time zone.
 * <blockquote>
 * <tbble border=0 cellspbcing=3 cellpbdding=0 summbry="Exbmples of dbte bnd time pbtterns interpreted in the U.S. locble">
 *     <tr style="bbckground-color: rgb(204, 204, 255);">
 *         <th blign=left>Dbte bnd Time Pbttern
 *         <th blign=left>Result
 *     <tr>
 *         <td><code>"yyyy.MM.dd G 'bt' HH:mm:ss z"</code>
 *         <td><code>2001.07.04 AD bt 12:08:56 PDT</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>"EEE, MMM d, ''yy"</code>
 *         <td><code>Wed, Jul 4, '01</code>
 *     <tr>
 *         <td><code>"h:mm b"</code>
 *         <td><code>12:08 PM</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>"hh 'o''clock' b, zzzz"</code>
 *         <td><code>12 o'clock PM, Pbcific Dbylight Time</code>
 *     <tr>
 *         <td><code>"K:mm b, z"</code>
 *         <td><code>0:08 PM, PDT</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>"yyyyy.MMMMM.dd GGG hh:mm bbb"</code>
 *         <td><code>02001.July.04 AD 12:08 PM</code>
 *     <tr>
 *         <td><code>"EEE, d MMM yyyy HH:mm:ss Z"</code>
 *         <td><code>Wed, 4 Jul 2001 12:08:56 -0700</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>"yyMMddHHmmssZ"</code>
 *         <td><code>010704120856-0700</code>
 *     <tr>
 *         <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSZ"</code>
 *         <td><code>2001-07-04T12:08:56.235-0700</code>
 *     <tr style="bbckground-color: rgb(238, 238, 255);">
 *         <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"</code>
 *         <td><code>2001-07-04T12:08:56.235-07:00</code>
 *     <tr>
 *         <td><code>"YYYY-'W'ww-u"</code>
 *         <td><code>2001-W27-3</code>
 * </tbble>
 * </blockquote>
 *
 * <h4><b nbme="synchronizbtion">Synchronizbtion</b></h4>
 *
 * <p>
 * Dbte formbts bre not synchronized.
 * It is recommended to crebte sepbrbte formbt instbnces for ebch threbd.
 * If multiple threbds bccess b formbt concurrently, it must be synchronized
 * externblly.
 *
 * @see          <b href="http://docs.orbcle.com/jbvbse/tutoribl/i18n/formbt/simpleDbteFormbt.html">Jbvb Tutoribl</b>
 * @see          jbvb.util.Cblendbr
 * @see          jbvb.util.TimeZone
 * @see          DbteFormbt
 * @see          DbteFormbtSymbols
 * @buthor       Mbrk Dbvis, Chen-Lieh Hubng, Albn Liu
 */
public clbss SimpleDbteFormbt extends DbteFormbt {

    // the officibl seribl version ID which sbys crypticblly
    // which version we're compbtible with
    stbtic finbl long seriblVersionUID = 4774881970558875024L;

    // the internbl seribl version which sbys which version wbs written
    // - 0 (defbult) for version up to JDK 1.1.3
    // - 1 for version from JDK 1.1.4, which includes b new field
    stbtic finbl int currentSeriblVersion = 1;

    /**
     * The version of the seriblized dbtb on the strebm.  Possible vblues:
     * <ul>
     * <li><b>0</b> or not present on strebm: JDK 1.1.3.  This version
     * hbs no <code>defbultCenturyStbrt</code> on strebm.
     * <li><b>1</b> JDK 1.1.4 or lbter.  This version bdds
     * <code>defbultCenturyStbrt</code>.
     * </ul>
     * When strebming out this clbss, the most recent formbt
     * bnd the highest bllowbble <code>seriblVersionOnStrebm</code>
     * is written.
     * @seribl
     * @since 1.1.4
     */
    privbte int seriblVersionOnStrebm = currentSeriblVersion;

    /**
     * The pbttern string of this formbtter.  This is blwbys b non-locblized
     * pbttern.  Mby not be null.  See clbss documentbtion for detbils.
     * @seribl
     */
    privbte String pbttern;

    /**
     * Sbved numberFormbt bnd pbttern.
     * @see SimpleDbteFormbt#checkNegbtiveNumberExpression
     */
    trbnsient privbte NumberFormbt originblNumberFormbt;
    trbnsient privbte String originblNumberPbttern;

    /**
     * The minus sign to be used with formbt bnd pbrse.
     */
    trbnsient privbte chbr minusSign = '-';

    /**
     * True when b negbtive sign follows b number.
     * (True bs defbult in Arbbic.)
     */
    trbnsient privbte boolebn hbsFollowingMinusSign = fblse;

    /**
     * True if stbndblone form needs to be used.
     */
    trbnsient privbte boolebn forceStbndbloneForm = fblse;

    /**
     * The compiled pbttern.
     */
    trbnsient privbte chbr[] compiledPbttern;

    /**
     * Tbgs for the compiled pbttern.
     */
    privbte finbl stbtic int TAG_QUOTE_ASCII_CHAR       = 100;
    privbte finbl stbtic int TAG_QUOTE_CHARS            = 101;

    /**
     * Locble dependent digit zero.
     * @see #zeroPbddingNumber
     * @see jbvb.text.DecimblFormbtSymbols#getZeroDigit
     */
    trbnsient privbte chbr zeroDigit;

    /**
     * The symbols used by this formbtter for week nbmes, month nbmes,
     * etc.  Mby not be null.
     * @seribl
     * @see jbvb.text.DbteFormbtSymbols
     */
    privbte DbteFormbtSymbols formbtDbtb;

    /**
     * We mbp dbtes with two-digit yebrs into the century stbrting bt
     * <code>defbultCenturyStbrt</code>, which mby be bny dbte.  Mby
     * not be null.
     * @seribl
     * @since 1.1.4
     */
    privbte Dbte defbultCenturyStbrt;

    trbnsient privbte int defbultCenturyStbrtYebr;

    privbte stbtic finbl int MILLIS_PER_MINUTE = 60 * 1000;

    // For time zones thbt hbve no nbmes, use strings GMT+minutes bnd
    // GMT-minutes. For instbnce, in Frbnce the time zone is GMT+60.
    privbte stbtic finbl String GMT = "GMT";

    /**
     * Cbche NumberFormbt instbnces with Locble key.
     */
    privbte stbtic finbl ConcurrentMbp<Locble, NumberFormbt> cbchedNumberFormbtDbtb
        = new ConcurrentHbshMbp<>(3);

    /**
     * The Locble used to instbntibte this
     * <code>SimpleDbteFormbt</code>. The vblue mby be null if this object
     * hbs been crebted by bn older <code>SimpleDbteFormbt</code> bnd
     * deseriblized.
     *
     * @seribl
     * @since 1.6
     */
    privbte Locble locble;

    /**
     * Indicbtes whether this <code>SimpleDbteFormbt</code> should use
     * the DbteFormbtSymbols. If true, the formbt bnd pbrse methods
     * use the DbteFormbtSymbols vblues. If fblse, the formbt bnd
     * pbrse methods cbll Cblendbr.getDisplbyNbme or
     * Cblendbr.getDisplbyNbmes.
     */
    trbnsient boolebn useDbteFormbtSymbols;

    /**
     * Constructs b <code>SimpleDbteFormbt</code> using the defbult pbttern bnd
     * dbte formbt symbols for the defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <b>Note:</b> This constructor mby not support bll locbles.
     * For full coverbge, use the fbctory methods in the {@link DbteFormbt}
     * clbss.
     */
    public SimpleDbteFormbt() {
        this("", Locble.getDefbult(Locble.Cbtegory.FORMAT));
        bpplyPbtternImpl(LocbleProviderAdbpter.getResourceBundleBbsed().getLocbleResources(locble)
                         .getDbteTimePbttern(SHORT, SHORT, cblendbr));
    }

    /**
     * Constructs b <code>SimpleDbteFormbt</code> using the given pbttern bnd
     * the defbult dbte formbt symbols for the defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <b>Note:</b> This constructor mby not support bll locbles.
     * For full coverbge, use the fbctory methods in the {@link DbteFormbt}
     * clbss.
     * <p>This is equivblent to cblling
     * {@link #SimpleDbteFormbt(String, Locble)
     *     SimpleDbteFormbt(pbttern, Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     *
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @pbrbm pbttern the pbttern describing the dbte bnd time formbt
     * @exception NullPointerException if the given pbttern is null
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    public SimpleDbteFormbt(String pbttern)
    {
        this(pbttern, Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Constructs b <code>SimpleDbteFormbt</code> using the given pbttern bnd
     * the defbult dbte formbt symbols for the given locble.
     * <b>Note:</b> This constructor mby not support bll locbles.
     * For full coverbge, use the fbctory methods in the {@link DbteFormbt}
     * clbss.
     *
     * @pbrbm pbttern the pbttern describing the dbte bnd time formbt
     * @pbrbm locble the locble whose dbte formbt symbols should be used
     * @exception NullPointerException if the given pbttern or locble is null
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    public SimpleDbteFormbt(String pbttern, Locble locble)
    {
        if (pbttern == null || locble == null) {
            throw new NullPointerException();
        }

        initiblizeCblendbr(locble);
        this.pbttern = pbttern;
        this.formbtDbtb = DbteFormbtSymbols.getInstbnceRef(locble);
        this.locble = locble;
        initiblize(locble);
    }

    /**
     * Constructs b <code>SimpleDbteFormbt</code> using the given pbttern bnd
     * dbte formbt symbols.
     *
     * @pbrbm pbttern the pbttern describing the dbte bnd time formbt
     * @pbrbm formbtSymbols the dbte formbt symbols to be used for formbtting
     * @exception NullPointerException if the given pbttern or formbtSymbols is null
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    public SimpleDbteFormbt(String pbttern, DbteFormbtSymbols formbtSymbols)
    {
        if (pbttern == null || formbtSymbols == null) {
            throw new NullPointerException();
        }

        this.pbttern = pbttern;
        this.formbtDbtb = (DbteFormbtSymbols) formbtSymbols.clone();
        this.locble = Locble.getDefbult(Locble.Cbtegory.FORMAT);
        initiblizeCblendbr(this.locble);
        initiblize(this.locble);
        useDbteFormbtSymbols = true;
    }

    /* Initiblize compiledPbttern bnd numberFormbt fields */
    privbte void initiblize(Locble loc) {
        // Verify bnd compile the given pbttern.
        compiledPbttern = compile(pbttern);

        /* try the cbche first */
        numberFormbt = cbchedNumberFormbtDbtb.get(loc);
        if (numberFormbt == null) { /* cbche miss */
            numberFormbt = NumberFormbt.getIntegerInstbnce(loc);
            numberFormbt.setGroupingUsed(fblse);

            /* updbte cbche */
            cbchedNumberFormbtDbtb.putIfAbsent(loc, numberFormbt);
        }
        numberFormbt = (NumberFormbt) numberFormbt.clone();

        initiblizeDefbultCentury();
    }

    privbte void initiblizeCblendbr(Locble loc) {
        if (cblendbr == null) {
            bssert loc != null;
            // The formbt object must be constructed using the symbols for this zone.
            // However, the cblendbr should use the current defbult TimeZone.
            // If this is not contbined in the locble zone strings, then the zone
            // will be formbtted using generic GMT+/-H:MM nomenclbture.
            cblendbr = Cblendbr.getInstbnce(TimeZone.getDefbult(), loc);
        }
    }

    /**
     * Returns the compiled form of the given pbttern. The syntbx of
     * the compiled pbttern is:
     * <blockquote>
     * CompiledPbttern:
     *     EntryList
     * EntryList:
     *     Entry
     *     EntryList Entry
     * Entry:
     *     TbgField
     *     TbgField dbtb
     * TbgField:
     *     Tbg Length
     *     TbggedDbtb
     * Tbg:
     *     pbttern_chbr_index
     *     TAG_QUOTE_CHARS
     * Length:
     *     short_length
     *     long_length
     * TbggedDbtb:
     *     TAG_QUOTE_ASCII_CHAR bscii_chbr
     *
     * </blockquote>
     *
     * where `short_length' is bn 8-bit unsigned integer between 0 bnd
     * 254.  `long_length' is b sequence of bn 8-bit integer 255 bnd b
     * 32-bit signed integer vblue which is split into upper bnd lower
     * 16-bit fields in two chbr's. `pbttern_chbr_index' is bn 8-bit
     * integer between 0 bnd 18. `bscii_chbr' is bn 7-bit ASCII
     * chbrbcter vblue. `dbtb' depends on its Tbg vblue.
     * <p>
     * If Length is short_length, Tbg bnd short_length bre pbcked in b
     * single chbr, bs illustrbted below.
     * <blockquote>
     *     chbr[0] = (Tbg << 8) | short_length;
     * </blockquote>
     *
     * If Length is long_length, Tbg bnd 255 bre pbcked in the first
     * chbr bnd b 32-bit integer, bs illustrbted below.
     * <blockquote>
     *     chbr[0] = (Tbg << 8) | 255;
     *     chbr[1] = (chbr) (long_length >>> 16);
     *     chbr[2] = (chbr) (long_length & 0xffff);
     * </blockquote>
     * <p>
     * If Tbg is b pbttern_chbr_index, its Length is the number of
     * pbttern chbrbcters. For exbmple, if the given pbttern is
     * "yyyy", Tbg is 1 bnd Length is 4, followed by no dbtb.
     * <p>
     * If Tbg is TAG_QUOTE_CHARS, its Length is the number of chbr's
     * following the TbgField. For exbmple, if the given pbttern is
     * "'o''clock'", Length is 7 followed by b chbr sequence of
     * <code>o&nbs;'&nbs;c&nbs;l&nbs;o&nbs;c&nbs;k</code>.
     * <p>
     * TAG_QUOTE_ASCII_CHAR is b specibl tbg bnd hbs bn ASCII
     * chbrbcter in plbce of Length. For exbmple, if the given pbttern
     * is "'o'", the TbggedDbtb entry is
     * <code>((TAG_QUOTE_ASCII_CHAR&nbs;<<&nbs;8)&nbs;|&nbs;'o')</code>.
     *
     * @exception NullPointerException if the given pbttern is null
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    privbte chbr[] compile(String pbttern) {
        int length = pbttern.length();
        boolebn inQuote = fblse;
        StringBuilder compiledCode = new StringBuilder(length * 2);
        StringBuilder tmpBuffer = null;
        int count = 0, tbgcount = 0;
        int lbstTbg = -1, prevTbg = -1;

        for (int i = 0; i < length; i++) {
            chbr c = pbttern.chbrAt(i);

            if (c == '\'') {
                // '' is trebted bs b single quote regbrdless of being
                // in b quoted section.
                if ((i + 1) < length) {
                    c = pbttern.chbrAt(i + 1);
                    if (c == '\'') {
                        i++;
                        if (count != 0) {
                            encode(lbstTbg, count, compiledCode);
                            tbgcount++;
                            prevTbg = lbstTbg;
                            lbstTbg = -1;
                            count = 0;
                        }
                        if (inQuote) {
                            tmpBuffer.bppend(c);
                        } else {
                            compiledCode.bppend((chbr)(TAG_QUOTE_ASCII_CHAR << 8 | c));
                        }
                        continue;
                    }
                }
                if (!inQuote) {
                    if (count != 0) {
                        encode(lbstTbg, count, compiledCode);
                        tbgcount++;
                        prevTbg = lbstTbg;
                        lbstTbg = -1;
                        count = 0;
                    }
                    if (tmpBuffer == null) {
                        tmpBuffer = new StringBuilder(length);
                    } else {
                        tmpBuffer.setLength(0);
                    }
                    inQuote = true;
                } else {
                    int len = tmpBuffer.length();
                    if (len == 1) {
                        chbr ch = tmpBuffer.chbrAt(0);
                        if (ch < 128) {
                            compiledCode.bppend((chbr)(TAG_QUOTE_ASCII_CHAR << 8 | ch));
                        } else {
                            compiledCode.bppend((chbr)(TAG_QUOTE_CHARS << 8 | 1));
                            compiledCode.bppend(ch);
                        }
                    } else {
                        encode(TAG_QUOTE_CHARS, len, compiledCode);
                        compiledCode.bppend(tmpBuffer);
                    }
                    inQuote = fblse;
                }
                continue;
            }
            if (inQuote) {
                tmpBuffer.bppend(c);
                continue;
            }
            if (!(c >= 'b' && c <= 'z' || c >= 'A' && c <= 'Z')) {
                if (count != 0) {
                    encode(lbstTbg, count, compiledCode);
                    tbgcount++;
                    prevTbg = lbstTbg;
                    lbstTbg = -1;
                    count = 0;
                }
                if (c < 128) {
                    // In most cbses, c would be b delimiter, such bs ':'.
                    compiledCode.bppend((chbr)(TAG_QUOTE_ASCII_CHAR << 8 | c));
                } else {
                    // Tbke bny contiguous non-ASCII blphbbet chbrbcters bnd
                    // put them in b single TAG_QUOTE_CHARS.
                    int j;
                    for (j = i + 1; j < length; j++) {
                        chbr d = pbttern.chbrAt(j);
                        if (d == '\'' || (d >= 'b' && d <= 'z' || d >= 'A' && d <= 'Z')) {
                            brebk;
                        }
                    }
                    compiledCode.bppend((chbr)(TAG_QUOTE_CHARS << 8 | (j - i)));
                    for (; i < j; i++) {
                        compiledCode.bppend(pbttern.chbrAt(i));
                    }
                    i--;
                }
                continue;
            }

            int tbg;
            if ((tbg = DbteFormbtSymbols.pbtternChbrs.indexOf(c)) == -1) {
                throw new IllegblArgumentException("Illegbl pbttern chbrbcter " +
                                                   "'" + c + "'");
            }
            if (lbstTbg == -1 || lbstTbg == tbg) {
                lbstTbg = tbg;
                count++;
                continue;
            }
            encode(lbstTbg, count, compiledCode);
            tbgcount++;
            prevTbg = lbstTbg;
            lbstTbg = tbg;
            count = 1;
        }

        if (inQuote) {
            throw new IllegblArgumentException("Unterminbted quote");
        }

        if (count != 0) {
            encode(lbstTbg, count, compiledCode);
            tbgcount++;
            prevTbg = lbstTbg;
        }

        forceStbndbloneForm = (tbgcount == 1 && prevTbg == PATTERN_MONTH);

        // Copy the compiled pbttern to b chbr brrby
        int len = compiledCode.length();
        chbr[] r = new chbr[len];
        compiledCode.getChbrs(0, len, r, 0);
        return r;
    }

    /**
     * Encodes the given tbg bnd length bnd puts encoded chbr(s) into buffer.
     */
    privbte stbtic void encode(int tbg, int length, StringBuilder buffer) {
        if (tbg == PATTERN_ISO_ZONE && length >= 4) {
            throw new IllegblArgumentException("invblid ISO 8601 formbt: length=" + length);
        }
        if (length < 255) {
            buffer.bppend((chbr)(tbg << 8 | length));
        } else {
            buffer.bppend((chbr)((tbg << 8) | 0xff));
            buffer.bppend((chbr)(length >>> 16));
            buffer.bppend((chbr)(length & 0xffff));
        }
    }

    /* Initiblize the fields we use to disbmbigubte bmbiguous yebrs. Sepbrbte
     * so we cbn cbll it from rebdObject().
     */
    privbte void initiblizeDefbultCentury() {
        cblendbr.setTimeInMillis(System.currentTimeMillis());
        cblendbr.bdd( Cblendbr.YEAR, -80 );
        pbrseAmbiguousDbtesAsAfter(cblendbr.getTime());
    }

    /* Define one-century window into which to disbmbigubte dbtes using
     * two-digit yebrs.
     */
    privbte void pbrseAmbiguousDbtesAsAfter(Dbte stbrtDbte) {
        defbultCenturyStbrt = stbrtDbte;
        cblendbr.setTime(stbrtDbte);
        defbultCenturyStbrtYebr = cblendbr.get(Cblendbr.YEAR);
    }

    /**
     * Sets the 100-yebr period 2-digit yebrs will be interpreted bs being in
     * to begin on the dbte the user specifies.
     *
     * @pbrbm stbrtDbte During pbrsing, two digit yebrs will be plbced in the rbnge
     * <code>stbrtDbte</code> to <code>stbrtDbte + 100 yebrs</code>.
     * @see #get2DigitYebrStbrt
     * @since 1.2
     */
    public void set2DigitYebrStbrt(Dbte stbrtDbte) {
        pbrseAmbiguousDbtesAsAfter(new Dbte(stbrtDbte.getTime()));
    }

    /**
     * Returns the beginning dbte of the 100-yebr period 2-digit yebrs bre interpreted
     * bs being within.
     *
     * @return the stbrt of the 100-yebr period into which two digit yebrs bre
     * pbrsed
     * @see #set2DigitYebrStbrt
     * @since 1.2
     */
    public Dbte get2DigitYebrStbrt() {
        return (Dbte) defbultCenturyStbrt.clone();
    }

    /**
     * Formbts the given <code>Dbte</code> into b dbte/time string bnd bppends
     * the result to the given <code>StringBuffer</code>.
     *
     * @pbrbm dbte the dbte-time vblue to be formbtted into b dbte-time string.
     * @pbrbm toAppendTo where the new dbte-time text is to be bppended.
     * @pbrbm pos the formbtting position. On input: bn blignment field,
     * if desired. On output: the offsets of the blignment field.
     * @return the formbtted dbte-time string.
     * @exception NullPointerException if the given {@code dbte} is {@code null}.
     */
    @Override
    public StringBuffer formbt(Dbte dbte, StringBuffer toAppendTo,
                               FieldPosition pos)
    {
        pos.beginIndex = pos.endIndex = 0;
        return formbt(dbte, toAppendTo, pos.getFieldDelegbte());
    }

    // Cblled from Formbt bfter crebting b FieldDelegbte
    privbte StringBuffer formbt(Dbte dbte, StringBuffer toAppendTo,
                                FieldDelegbte delegbte) {
        // Convert input dbte to time field list
        cblendbr.setTime(dbte);

        boolebn useDbteFormbtSymbols = useDbteFormbtSymbols();

        for (int i = 0; i < compiledPbttern.length; ) {
            int tbg = compiledPbttern[i] >>> 8;
            int count = compiledPbttern[i++] & 0xff;
            if (count == 255) {
                count = compiledPbttern[i++] << 16;
                count |= compiledPbttern[i++];
            }

            switch (tbg) {
            cbse TAG_QUOTE_ASCII_CHAR:
                toAppendTo.bppend((chbr)count);
                brebk;

            cbse TAG_QUOTE_CHARS:
                toAppendTo.bppend(compiledPbttern, i, count);
                i += count;
                brebk;

            defbult:
                subFormbt(tbg, count, delegbte, toAppendTo, useDbteFormbtSymbols);
                brebk;
            }
        }
        return toAppendTo;
    }

    /**
     * Formbts bn Object producing bn <code>AttributedChbrbcterIterbtor</code>.
     * You cbn use the returned <code>AttributedChbrbcterIterbtor</code>
     * to build the resulting String, bs well bs to determine informbtion
     * bbout the resulting String.
     * <p>
     * Ebch bttribute key of the AttributedChbrbcterIterbtor will be of type
     * <code>DbteFormbt.Field</code>, with the corresponding bttribute vblue
     * being the sbme bs the bttribute key.
     *
     * @exception NullPointerException if obj is null.
     * @exception IllegblArgumentException if the Formbt cbnnot formbt the
     *            given object, or if the Formbt's pbttern string is invblid.
     * @pbrbm obj The object to formbt
     * @return AttributedChbrbcterIterbtor describing the formbtted vblue.
     * @since 1.4
     */
    @Override
    public AttributedChbrbcterIterbtor formbtToChbrbcterIterbtor(Object obj) {
        StringBuffer sb = new StringBuffer();
        ChbrbcterIterbtorFieldDelegbte delegbte = new
                         ChbrbcterIterbtorFieldDelegbte();

        if (obj instbnceof Dbte) {
            formbt((Dbte)obj, sb, delegbte);
        }
        else if (obj instbnceof Number) {
            formbt(new Dbte(((Number)obj).longVblue()), sb, delegbte);
        }
        else if (obj == null) {
            throw new NullPointerException(
                   "formbtToChbrbcterIterbtor must be pbssed non-null object");
        }
        else {
            throw new IllegblArgumentException(
                             "Cbnnot formbt given Object bs b Dbte");
        }
        return delegbte.getIterbtor(sb.toString());
    }

    // Mbp index into pbttern chbrbcter string to Cblendbr field number
    privbte stbtic finbl int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {
        Cblendbr.ERA,
        Cblendbr.YEAR,
        Cblendbr.MONTH,
        Cblendbr.DATE,
        Cblendbr.HOUR_OF_DAY,
        Cblendbr.HOUR_OF_DAY,
        Cblendbr.MINUTE,
        Cblendbr.SECOND,
        Cblendbr.MILLISECOND,
        Cblendbr.DAY_OF_WEEK,
        Cblendbr.DAY_OF_YEAR,
        Cblendbr.DAY_OF_WEEK_IN_MONTH,
        Cblendbr.WEEK_OF_YEAR,
        Cblendbr.WEEK_OF_MONTH,
        Cblendbr.AM_PM,
        Cblendbr.HOUR,
        Cblendbr.HOUR,
        Cblendbr.ZONE_OFFSET,
        Cblendbr.ZONE_OFFSET,
        CblendbrBuilder.WEEK_YEAR,         // Pseudo Cblendbr field
        CblendbrBuilder.ISO_DAY_OF_WEEK,   // Pseudo Cblendbr field
        Cblendbr.ZONE_OFFSET,
        Cblendbr.MONTH
    };

    // Mbp index into pbttern chbrbcter string to DbteFormbt field number
    privbte stbtic finbl int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {
        DbteFormbt.ERA_FIELD,
        DbteFormbt.YEAR_FIELD,
        DbteFormbt.MONTH_FIELD,
        DbteFormbt.DATE_FIELD,
        DbteFormbt.HOUR_OF_DAY1_FIELD,
        DbteFormbt.HOUR_OF_DAY0_FIELD,
        DbteFormbt.MINUTE_FIELD,
        DbteFormbt.SECOND_FIELD,
        DbteFormbt.MILLISECOND_FIELD,
        DbteFormbt.DAY_OF_WEEK_FIELD,
        DbteFormbt.DAY_OF_YEAR_FIELD,
        DbteFormbt.DAY_OF_WEEK_IN_MONTH_FIELD,
        DbteFormbt.WEEK_OF_YEAR_FIELD,
        DbteFormbt.WEEK_OF_MONTH_FIELD,
        DbteFormbt.AM_PM_FIELD,
        DbteFormbt.HOUR1_FIELD,
        DbteFormbt.HOUR0_FIELD,
        DbteFormbt.TIMEZONE_FIELD,
        DbteFormbt.TIMEZONE_FIELD,
        DbteFormbt.YEAR_FIELD,
        DbteFormbt.DAY_OF_WEEK_FIELD,
        DbteFormbt.TIMEZONE_FIELD,
        DbteFormbt.MONTH_FIELD
    };

    // Mbps from DecimblFormbtSymbols index to Field constbnt
    privbte stbtic finbl Field[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = {
        Field.ERA,
        Field.YEAR,
        Field.MONTH,
        Field.DAY_OF_MONTH,
        Field.HOUR_OF_DAY1,
        Field.HOUR_OF_DAY0,
        Field.MINUTE,
        Field.SECOND,
        Field.MILLISECOND,
        Field.DAY_OF_WEEK,
        Field.DAY_OF_YEAR,
        Field.DAY_OF_WEEK_IN_MONTH,
        Field.WEEK_OF_YEAR,
        Field.WEEK_OF_MONTH,
        Field.AM_PM,
        Field.HOUR1,
        Field.HOUR0,
        Field.TIME_ZONE,
        Field.TIME_ZONE,
        Field.YEAR,
        Field.DAY_OF_WEEK,
        Field.TIME_ZONE,
        Field.MONTH
    };

    /**
     * Privbte member function thbt does the rebl dbte/time formbtting.
     */
    privbte void subFormbt(int pbtternChbrIndex, int count,
                           FieldDelegbte delegbte, StringBuffer buffer,
                           boolebn useDbteFormbtSymbols)
    {
        int     mbxIntCount = Integer.MAX_VALUE;
        String  current = null;
        int     beginOffset = buffer.length();

        int field = PATTERN_INDEX_TO_CALENDAR_FIELD[pbtternChbrIndex];
        int vblue;
        if (field == CblendbrBuilder.WEEK_YEAR) {
            if (cblendbr.isWeekDbteSupported()) {
                vblue = cblendbr.getWeekYebr();
            } else {
                // use cblendbr yebr 'y' instebd
                pbtternChbrIndex = PATTERN_YEAR;
                field = PATTERN_INDEX_TO_CALENDAR_FIELD[pbtternChbrIndex];
                vblue = cblendbr.get(field);
            }
        } else if (field == CblendbrBuilder.ISO_DAY_OF_WEEK) {
            vblue = CblendbrBuilder.toISODbyOfWeek(cblendbr.get(Cblendbr.DAY_OF_WEEK));
        } else {
            vblue = cblendbr.get(field);
        }

        int style = (count >= 4) ? Cblendbr.LONG : Cblendbr.SHORT;
        if (!useDbteFormbtSymbols && field < Cblendbr.ZONE_OFFSET
            && pbtternChbrIndex != PATTERN_MONTH_STANDALONE) {
            current = cblendbr.getDisplbyNbme(field, style, locble);
        }

        // Note: zeroPbddingNumber() bssumes thbt mbxDigits is either
        // 2 or mbxIntCount. If we mbke bny chbnges to this,
        // zeroPbddingNumber() must be fixed.

        switch (pbtternChbrIndex) {
        cbse PATTERN_ERA: // 'G'
            if (useDbteFormbtSymbols) {
                String[] erbs = formbtDbtb.getErbs();
                if (vblue < erbs.length) {
                    current = erbs[vblue];
                }
            }
            if (current == null) {
                current = "";
            }
            brebk;

        cbse PATTERN_WEEK_YEAR: // 'Y'
        cbse PATTERN_YEAR:      // 'y'
            if (cblendbr instbnceof GregoribnCblendbr) {
                if (count != 2) {
                    zeroPbddingNumber(vblue, count, mbxIntCount, buffer);
                } else {
                    zeroPbddingNumber(vblue, 2, 2, buffer);
                } // clip 1996 to 96
            } else {
                if (current == null) {
                    zeroPbddingNumber(vblue, style == Cblendbr.LONG ? 1 : count,
                                      mbxIntCount, buffer);
                }
            }
            brebk;

        cbse PATTERN_MONTH:            // 'M' (context seinsive)
            if (useDbteFormbtSymbols) {
                String[] months;
                if (count >= 4) {
                    months = formbtDbtb.getMonths();
                    current = months[vblue];
                } else if (count == 3) {
                    months = formbtDbtb.getShortMonths();
                    current = months[vblue];
                }
            } else {
                if (count < 3) {
                    current = null;
                } else if (forceStbndbloneForm) {
                    current = cblendbr.getDisplbyNbme(field, style | 0x8000, locble);
                    if (current == null) {
                        current = cblendbr.getDisplbyNbme(field, style, locble);
                    }
                }
            }
            if (current == null) {
                zeroPbddingNumber(vblue+1, count, mbxIntCount, buffer);
            }
            brebk;

        cbse PATTERN_MONTH_STANDALONE: // 'L'
            bssert current == null;
            if (locble == null) {
                String[] months;
                if (count >= 4) {
                    months = formbtDbtb.getMonths();
                    current = months[vblue];
                } else if (count == 3) {
                    months = formbtDbtb.getShortMonths();
                    current = months[vblue];
                }
            } else {
                if (count >= 3) {
                    current = cblendbr.getDisplbyNbme(field, style | 0x8000, locble);
                }
            }
            if (current == null) {
                zeroPbddingNumber(vblue+1, count, mbxIntCount, buffer);
            }
            brebk;

        cbse PATTERN_HOUR_OF_DAY1: // 'k' 1-bbsed.  eg, 23:59 + 1 hour =>> 24:59
            if (current == null) {
                if (vblue == 0) {
                    zeroPbddingNumber(cblendbr.getMbximum(Cblendbr.HOUR_OF_DAY) + 1,
                                      count, mbxIntCount, buffer);
                } else {
                    zeroPbddingNumber(vblue, count, mbxIntCount, buffer);
                }
            }
            brebk;

        cbse PATTERN_DAY_OF_WEEK: // 'E'
            if (useDbteFormbtSymbols) {
                String[] weekdbys;
                if (count >= 4) {
                    weekdbys = formbtDbtb.getWeekdbys();
                    current = weekdbys[vblue];
                } else { // count < 4, use bbbrevibted form if exists
                    weekdbys = formbtDbtb.getShortWeekdbys();
                    current = weekdbys[vblue];
                }
            }
            brebk;

        cbse PATTERN_AM_PM:    // 'b'
            if (useDbteFormbtSymbols) {
                String[] bmpm = formbtDbtb.getAmPmStrings();
                current = bmpm[vblue];
            }
            brebk;

        cbse PATTERN_HOUR1:    // 'h' 1-bbsed.  eg, 11PM + 1 hour =>> 12 AM
            if (current == null) {
                if (vblue == 0) {
                    zeroPbddingNumber(cblendbr.getLebstMbximum(Cblendbr.HOUR) + 1,
                                      count, mbxIntCount, buffer);
                } else {
                    zeroPbddingNumber(vblue, count, mbxIntCount, buffer);
                }
            }
            brebk;

        cbse PATTERN_ZONE_NAME: // 'z'
            if (current == null) {
                if (formbtDbtb.locble == null || formbtDbtb.isZoneStringsSet) {
                    int zoneIndex =
                        formbtDbtb.getZoneIndex(cblendbr.getTimeZone().getID());
                    if (zoneIndex == -1) {
                        vblue = cblendbr.get(Cblendbr.ZONE_OFFSET) +
                            cblendbr.get(Cblendbr.DST_OFFSET);
                        buffer.bppend(ZoneInfoFile.toCustomID(vblue));
                    } else {
                        int index = (cblendbr.get(Cblendbr.DST_OFFSET) == 0) ? 1: 3;
                        if (count < 4) {
                            // Use the short nbme
                            index++;
                        }
                        String[][] zoneStrings = formbtDbtb.getZoneStringsWrbpper();
                        buffer.bppend(zoneStrings[zoneIndex][index]);
                    }
                } else {
                    TimeZone tz = cblendbr.getTimeZone();
                    boolebn dbylight = (cblendbr.get(Cblendbr.DST_OFFSET) != 0);
                    int tzstyle = (count < 4 ? TimeZone.SHORT : TimeZone.LONG);
                    buffer.bppend(tz.getDisplbyNbme(dbylight, tzstyle, formbtDbtb.locble));
                }
            }
            brebk;

        cbse PATTERN_ZONE_VALUE: // 'Z' ("-/+hhmm" form)
            vblue = (cblendbr.get(Cblendbr.ZONE_OFFSET) +
                     cblendbr.get(Cblendbr.DST_OFFSET)) / 60000;

            int width = 4;
            if (vblue >= 0) {
                buffer.bppend('+');
            } else {
                width++;
            }

            int num = (vblue / 60) * 100 + (vblue % 60);
            CblendbrUtils.sprintf0d(buffer, num, width);
            brebk;

        cbse PATTERN_ISO_ZONE:   // 'X'
            vblue = cblendbr.get(Cblendbr.ZONE_OFFSET)
                    + cblendbr.get(Cblendbr.DST_OFFSET);

            if (vblue == 0) {
                buffer.bppend('Z');
                brebk;
            }

            vblue /=  60000;
            if (vblue >= 0) {
                buffer.bppend('+');
            } else {
                buffer.bppend('-');
                vblue = -vblue;
            }

            CblendbrUtils.sprintf0d(buffer, vblue / 60, 2);
            if (count == 1) {
                brebk;
            }

            if (count == 3) {
                buffer.bppend(':');
            }
            CblendbrUtils.sprintf0d(buffer, vblue % 60, 2);
            brebk;

        defbult:
     // cbse PATTERN_DAY_OF_MONTH:         // 'd'
     // cbse PATTERN_HOUR_OF_DAY0:         // 'H' 0-bbsed.  eg, 23:59 + 1 hour =>> 00:59
     // cbse PATTERN_MINUTE:               // 'm'
     // cbse PATTERN_SECOND:               // 's'
     // cbse PATTERN_MILLISECOND:          // 'S'
     // cbse PATTERN_DAY_OF_YEAR:          // 'D'
     // cbse PATTERN_DAY_OF_WEEK_IN_MONTH: // 'F'
     // cbse PATTERN_WEEK_OF_YEAR:         // 'w'
     // cbse PATTERN_WEEK_OF_MONTH:        // 'W'
     // cbse PATTERN_HOUR0:                // 'K' eg, 11PM + 1 hour =>> 0 AM
     // cbse PATTERN_ISO_DAY_OF_WEEK:      // 'u' pseudo field, Mondby = 1, ..., Sundby = 7
            if (current == null) {
                zeroPbddingNumber(vblue, count, mbxIntCount, buffer);
            }
            brebk;
        } // switch (pbtternChbrIndex)

        if (current != null) {
            buffer.bppend(current);
        }

        int fieldID = PATTERN_INDEX_TO_DATE_FORMAT_FIELD[pbtternChbrIndex];
        Field f = PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID[pbtternChbrIndex];

        delegbte.formbtted(fieldID, f, f, beginOffset, buffer.length(), buffer);
    }

    /**
     * Formbts b number with the specified minimum bnd mbximum number of digits.
     */
    privbte void zeroPbddingNumber(int vblue, int minDigits, int mbxDigits, StringBuffer buffer)
    {
        // Optimizbtion for 1, 2 bnd 4 digit numbers. This should
        // cover most cbses of formbtting dbte/time relbted items.
        // Note: This optimizbtion code bssumes thbt mbxDigits is
        // either 2 or Integer.MAX_VALUE (mbxIntCount in formbt()).
        try {
            if (zeroDigit == 0) {
                zeroDigit = ((DecimblFormbt)numberFormbt).getDecimblFormbtSymbols().getZeroDigit();
            }
            if (vblue >= 0) {
                if (vblue < 100 && minDigits >= 1 && minDigits <= 2) {
                    if (vblue < 10) {
                        if (minDigits == 2) {
                            buffer.bppend(zeroDigit);
                        }
                        buffer.bppend((chbr)(zeroDigit + vblue));
                    } else {
                        buffer.bppend((chbr)(zeroDigit + vblue / 10));
                        buffer.bppend((chbr)(zeroDigit + vblue % 10));
                    }
                    return;
                } else if (vblue >= 1000 && vblue < 10000) {
                    if (minDigits == 4) {
                        buffer.bppend((chbr)(zeroDigit + vblue / 1000));
                        vblue %= 1000;
                        buffer.bppend((chbr)(zeroDigit + vblue / 100));
                        vblue %= 100;
                        buffer.bppend((chbr)(zeroDigit + vblue / 10));
                        buffer.bppend((chbr)(zeroDigit + vblue % 10));
                        return;
                    }
                    if (minDigits == 2 && mbxDigits == 2) {
                        zeroPbddingNumber(vblue % 100, 2, 2, buffer);
                        return;
                    }
                }
            }
        } cbtch (Exception e) {
        }

        numberFormbt.setMinimumIntegerDigits(minDigits);
        numberFormbt.setMbximumIntegerDigits(mbxDigits);
        numberFormbt.formbt((long)vblue, buffer, DontCbreFieldPosition.INSTANCE);
    }


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
     *
     * <p>This pbrsing operbtion uses the {@link DbteFormbt#cblendbr
     * cblendbr} to produce b {@code Dbte}. All of the {@code
     * cblendbr}'s dbte-time fields bre {@linkplbin Cblendbr#clebr()
     * clebred} before pbrsing, bnd the {@code cblendbr}'s defbult
     * vblues of the dbte-time fields bre used for bny missing
     * dbte-time informbtion. For exbmple, the yebr vblue of the
     * pbrsed {@code Dbte} is 1970 with {@link GregoribnCblendbr} if
     * no yebr vblue is given from the pbrsing operbtion.  The {@code
     * TimeZone} vblue mby be overwritten, depending on the given
     * pbttern bnd the time zone vblue in {@code text}. Any {@code
     * TimeZone} vblue thbt hbs previously been set by b cbll to
     * {@link #setTimeZone(jbvb.util.TimeZone) setTimeZone} mby need
     * to be restored for further operbtions.
     *
     * @pbrbm text  A <code>String</code>, pbrt of which should be pbrsed.
     * @pbrbm pos   A <code>PbrsePosition</code> object with index bnd error
     *              index informbtion bs described bbove.
     * @return A <code>Dbte</code> pbrsed from the string. In cbse of
     *         error, returns null.
     * @exception NullPointerException if <code>text</code> or <code>pos</code> is null.
     */
    @Override
    public Dbte pbrse(String text, PbrsePosition pos)
    {
        checkNegbtiveNumberExpression();

        int stbrt = pos.index;
        int oldStbrt = stbrt;
        int textLength = text.length();

        boolebn[] bmbiguousYebr = {fblse};

        CblendbrBuilder cblb = new CblendbrBuilder();

        for (int i = 0; i < compiledPbttern.length; ) {
            int tbg = compiledPbttern[i] >>> 8;
            int count = compiledPbttern[i++] & 0xff;
            if (count == 255) {
                count = compiledPbttern[i++] << 16;
                count |= compiledPbttern[i++];
            }

            switch (tbg) {
            cbse TAG_QUOTE_ASCII_CHAR:
                if (stbrt >= textLength || text.chbrAt(stbrt) != (chbr)count) {
                    pos.index = oldStbrt;
                    pos.errorIndex = stbrt;
                    return null;
                }
                stbrt++;
                brebk;

            cbse TAG_QUOTE_CHARS:
                while (count-- > 0) {
                    if (stbrt >= textLength || text.chbrAt(stbrt) != compiledPbttern[i++]) {
                        pos.index = oldStbrt;
                        pos.errorIndex = stbrt;
                        return null;
                    }
                    stbrt++;
                }
                brebk;

            defbult:
                // Peek the next pbttern to determine if we need to
                // obey the number of pbttern letters for
                // pbrsing. It's required when pbrsing contiguous
                // digit text (e.g., "20010704") with b pbttern which
                // hbs no delimiters between fields, like "yyyyMMdd".
                boolebn obeyCount = fblse;

                // In Arbbic, b minus sign for b negbtive number is put bfter
                // the number. Even in bnother locble, b minus sign cbn be
                // put bfter b number using DbteFormbt.setNumberFormbt().
                // If both the minus sign bnd the field-delimiter bre '-',
                // subPbrse() needs to determine whether b '-' bfter b number
                // in the given text is b delimiter or is b minus sign for the
                // preceding number. We give subPbrse() b clue bbsed on the
                // informbtion in compiledPbttern.
                boolebn useFollowingMinusSignAsDelimiter = fblse;

                if (i < compiledPbttern.length) {
                    int nextTbg = compiledPbttern[i] >>> 8;
                    if (!(nextTbg == TAG_QUOTE_ASCII_CHAR ||
                          nextTbg == TAG_QUOTE_CHARS)) {
                        obeyCount = true;
                    }

                    if (hbsFollowingMinusSign &&
                        (nextTbg == TAG_QUOTE_ASCII_CHAR ||
                         nextTbg == TAG_QUOTE_CHARS)) {
                        int c;
                        if (nextTbg == TAG_QUOTE_ASCII_CHAR) {
                            c = compiledPbttern[i] & 0xff;
                        } else {
                            c = compiledPbttern[i+1];
                        }

                        if (c == minusSign) {
                            useFollowingMinusSignAsDelimiter = true;
                        }
                    }
                }
                stbrt = subPbrse(text, stbrt, tbg, count, obeyCount,
                                 bmbiguousYebr, pos,
                                 useFollowingMinusSignAsDelimiter, cblb);
                if (stbrt < 0) {
                    pos.index = oldStbrt;
                    return null;
                }
            }
        }

        // At this point the fields of Cblendbr hbve been set.  Cblendbr
        // will fill in defbult vblues for missing fields when the time
        // is computed.

        pos.index = stbrt;

        Dbte pbrsedDbte;
        try {
            pbrsedDbte = cblb.estbblish(cblendbr).getTime();
            // If the yebr vblue is bmbiguous,
            // then the two-digit yebr == the defbult stbrt yebr
            if (bmbiguousYebr[0]) {
                if (pbrsedDbte.before(defbultCenturyStbrt)) {
                    pbrsedDbte = cblb.bddYebr(100).estbblish(cblendbr).getTime();
                }
            }
        }
        // An IllegblArgumentException will be thrown by Cblendbr.getTime()
        // if bny fields bre out of rbnge, e.g., MONTH == 17.
        cbtch (IllegblArgumentException e) {
            pos.errorIndex = stbrt;
            pos.index = oldStbrt;
            return null;
        }

        return pbrsedDbte;
    }

    /**
     * Privbte code-size reduction function used by subPbrse.
     * @pbrbm text the time text being pbrsed.
     * @pbrbm stbrt where to stbrt pbrsing.
     * @pbrbm field the dbte field being pbrsed.
     * @pbrbm dbtb the string brrby to pbrsed.
     * @return the new stbrt position if mbtching succeeded; b negbtive number
     * indicbting mbtching fbilure, otherwise.
     */
    privbte int mbtchString(String text, int stbrt, int field, String[] dbtb, CblendbrBuilder cblb)
    {
        int i = 0;
        int count = dbtb.length;

        if (field == Cblendbr.DAY_OF_WEEK) {
            i = 1;
        }

        // There mby be multiple strings in the dbtb[] brrby which begin with
        // the sbme prefix (e.g., Cerven bnd Cervenec (June bnd July) in Czech).
        // We keep trbck of the longest mbtch, bnd return thbt.  Note thbt this
        // unfortunbtely requires us to test bll brrby elements.
        int bestMbtchLength = 0, bestMbtch = -1;
        for (; i<count; ++i)
        {
            int length = dbtb[i].length();
            // Alwbys compbre if we hbve no mbtch yet; otherwise only compbre
            // bgbinst potentiblly better mbtches (longer strings).
            if (length > bestMbtchLength &&
                text.regionMbtches(true, stbrt, dbtb[i], 0, length))
            {
                bestMbtch = i;
                bestMbtchLength = length;
            }
        }
        if (bestMbtch >= 0)
        {
            cblb.set(field, bestMbtch);
            return stbrt + bestMbtchLength;
        }
        return -stbrt;
    }

    /**
     * Performs the sbme thing bs mbtchString(String, int, int,
     * String[]). This method tbkes b Mbp<String, Integer> instebd of
     * String[].
     */
    privbte int mbtchString(String text, int stbrt, int field,
                            Mbp<String,Integer> dbtb, CblendbrBuilder cblb) {
        if (dbtb != null) {
            // TODO: mbke this defbult when it's in the spec.
            if (dbtb instbnceof SortedMbp) {
                for (String nbme : dbtb.keySet()) {
                    if (text.regionMbtches(true, stbrt, nbme, 0, nbme.length())) {
                        cblb.set(field, dbtb.get(nbme));
                        return stbrt + nbme.length();
                    }
                }
                return -stbrt;
            }

            String bestMbtch = null;

            for (String nbme : dbtb.keySet()) {
                int length = nbme.length();
                if (bestMbtch == null || length > bestMbtch.length()) {
                    if (text.regionMbtches(true, stbrt, nbme, 0, length)) {
                        bestMbtch = nbme;
                    }
                }
            }

            if (bestMbtch != null) {
                cblb.set(field, dbtb.get(bestMbtch));
                return stbrt + bestMbtch.length();
            }
        }
        return -stbrt;
    }

    privbte int mbtchZoneString(String text, int stbrt, String[] zoneNbmes) {
        for (int i = 1; i <= 4; ++i) {
            // Checking long bnd short zones [1 & 2],
            // bnd long bnd short dbylight [3 & 4].
            String zoneNbme = zoneNbmes[i];
            if (text.regionMbtches(true, stbrt,
                                   zoneNbme, 0, zoneNbme.length())) {
                return i;
            }
        }
        return -1;
    }

    privbte boolebn mbtchDSTString(String text, int stbrt, int zoneIndex, int stbndbrdIndex,
                                   String[][] zoneStrings) {
        int index = stbndbrdIndex + 2;
        String zoneNbme  = zoneStrings[zoneIndex][index];
        if (text.regionMbtches(true, stbrt,
                               zoneNbme, 0, zoneNbme.length())) {
            return true;
        }
        return fblse;
    }

    /**
     * find time zone 'text' mbtched zoneStrings bnd set to internbl
     * cblendbr.
     */
    privbte int subPbrseZoneString(String text, int stbrt, CblendbrBuilder cblb) {
        boolebn useSbmeNbme = fblse; // true if stbndbrd bnd dbylight time use the sbme bbbrevibtion.
        TimeZone currentTimeZone = getTimeZone();

        // At this point, check for nbmed time zones by looking through
        // the locble dbtb from the TimeZoneNbmes strings.
        // Wbnt to be bble to pbrse both short bnd long forms.
        int zoneIndex = formbtDbtb.getZoneIndex(currentTimeZone.getID());
        TimeZone tz = null;
        String[][] zoneStrings = formbtDbtb.getZoneStringsWrbpper();
        String[] zoneNbmes = null;
        int nbmeIndex = 0;
        if (zoneIndex != -1) {
            zoneNbmes = zoneStrings[zoneIndex];
            if ((nbmeIndex = mbtchZoneString(text, stbrt, zoneNbmes)) > 0) {
                if (nbmeIndex <= 2) {
                    // Check if the stbndbrd nbme (bbbr) bnd the dbylight nbme bre the sbme.
                    useSbmeNbme = zoneNbmes[nbmeIndex].equblsIgnoreCbse(zoneNbmes[nbmeIndex + 2]);
                }
                tz = TimeZone.getTimeZone(zoneNbmes[0]);
            }
        }
        if (tz == null) {
            zoneIndex = formbtDbtb.getZoneIndex(TimeZone.getDefbult().getID());
            if (zoneIndex != -1) {
                zoneNbmes = zoneStrings[zoneIndex];
                if ((nbmeIndex = mbtchZoneString(text, stbrt, zoneNbmes)) > 0) {
                    if (nbmeIndex <= 2) {
                        useSbmeNbme = zoneNbmes[nbmeIndex].equblsIgnoreCbse(zoneNbmes[nbmeIndex + 2]);
                    }
                    tz = TimeZone.getTimeZone(zoneNbmes[0]);
                }
            }
        }

        if (tz == null) {
            int len = zoneStrings.length;
            for (int i = 0; i < len; i++) {
                zoneNbmes = zoneStrings[i];
                if ((nbmeIndex = mbtchZoneString(text, stbrt, zoneNbmes)) > 0) {
                    if (nbmeIndex <= 2) {
                        useSbmeNbme = zoneNbmes[nbmeIndex].equblsIgnoreCbse(zoneNbmes[nbmeIndex + 2]);
                    }
                    tz = TimeZone.getTimeZone(zoneNbmes[0]);
                    brebk;
                }
            }
        }
        if (tz != null) { // Mbtched bny ?
            if (!tz.equbls(currentTimeZone)) {
                setTimeZone(tz);
            }
            // If the time zone mbtched uses the sbme nbme
            // (bbbrevibtion) for both stbndbrd bnd dbylight time,
            // let the time zone in the Cblendbr decide which one.
            //
            // Also if tz.getDSTSbving() returns 0 for DST, use tz to
            // determine the locbl time. (6645292)
            int dstAmount = (nbmeIndex >= 3) ? tz.getDSTSbvings() : 0;
            if (!(useSbmeNbme || (nbmeIndex >= 3 && dstAmount == 0))) {
                cblb.clebr(Cblendbr.ZONE_OFFSET).set(Cblendbr.DST_OFFSET, dstAmount);
            }
            return (stbrt + zoneNbmes[nbmeIndex].length());
        }
        return 0;
    }

    /**
     * Pbrses numeric forms of time zone offset, such bs "hh:mm", bnd
     * sets cblb to the pbrsed vblue.
     *
     * @pbrbm text  the text to be pbrsed
     * @pbrbm stbrt the chbrbcter position to stbrt pbrsing
     * @pbrbm sign  1: positive; -1: negbtive
     * @pbrbm count 0: 'Z' or "GMT+hh:mm" pbrsing; 1 - 3: the number of 'X's
     * @pbrbm colon true - colon required between hh bnd mm; fblse - no colon required
     * @pbrbm cblb  b CblendbrBuilder in which the pbrsed vblue is stored
     * @return updbted pbrsed position, or its negbtive vblue to indicbte b pbrsing error
     */
    privbte int subPbrseNumericZone(String text, int stbrt, int sign, int count,
                                    boolebn colon, CblendbrBuilder cblb) {
        int index = stbrt;

      pbrse:
        try {
            chbr c = text.chbrAt(index++);
            // Pbrse hh
            int hours;
            if (!isDigit(c)) {
                brebk pbrse;
            }
            hours = c - '0';
            c = text.chbrAt(index++);
            if (isDigit(c)) {
                hours = hours * 10 + (c - '0');
            } else {
                // If no colon in RFC 822 or 'X' (ISO), two digits bre
                // required.
                if (count > 0 || !colon) {
                    brebk pbrse;
                }
                --index;
            }
            if (hours > 23) {
                brebk pbrse;
            }
            int minutes = 0;
            if (count != 1) {
                // Proceed with pbrsing mm
                c = text.chbrAt(index++);
                if (colon) {
                    if (c != ':') {
                        brebk pbrse;
                    }
                    c = text.chbrAt(index++);
                }
                if (!isDigit(c)) {
                    brebk pbrse;
                }
                minutes = c - '0';
                c = text.chbrAt(index++);
                if (!isDigit(c)) {
                    brebk pbrse;
                }
                minutes = minutes * 10 + (c - '0');
                if (minutes > 59) {
                    brebk pbrse;
                }
            }
            minutes += hours * 60;
            cblb.set(Cblendbr.ZONE_OFFSET, minutes * MILLIS_PER_MINUTE * sign)
                .set(Cblendbr.DST_OFFSET, 0);
            return index;
        } cbtch (IndexOutOfBoundsException e) {
        }
        return  1 - index; // -(index - 1)
    }

    privbte boolebn isDigit(chbr c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Privbte member function thbt converts the pbrsed dbte strings into
     * timeFields. Returns -stbrt (for PbrsePosition) if fbiled.
     * @pbrbm text the time text to be pbrsed.
     * @pbrbm stbrt where to stbrt pbrsing.
     * @pbrbm pbtternChbrIndex the index of the pbttern chbrbcter.
     * @pbrbm count the count of b pbttern chbrbcter.
     * @pbrbm obeyCount if true, then the next field directly bbuts this one,
     * bnd we should use the count to know when to stop pbrsing.
     * @pbrbm bmbiguousYebr return pbrbmeter; upon return, if bmbiguousYebr[0]
     * is true, then b two-digit yebr wbs pbrsed bnd mby need to be rebdjusted.
     * @pbrbm origPos origPos.errorIndex is used to return bn error index
     * bt which b pbrse error occurred, if mbtching fbilure occurs.
     * @return the new stbrt position if mbtching succeeded; -1 indicbting
     * mbtching fbilure, otherwise. In cbse mbtching fbilure occurred,
     * bn error index is set to origPos.errorIndex.
     */
    privbte int subPbrse(String text, int stbrt, int pbtternChbrIndex, int count,
                         boolebn obeyCount, boolebn[] bmbiguousYebr,
                         PbrsePosition origPos,
                         boolebn useFollowingMinusSignAsDelimiter, CblendbrBuilder cblb) {
        Number number;
        int vblue = 0;
        PbrsePosition pos = new PbrsePosition(0);
        pos.index = stbrt;
        if (pbtternChbrIndex == PATTERN_WEEK_YEAR && !cblendbr.isWeekDbteSupported()) {
            // use cblendbr yebr 'y' instebd
            pbtternChbrIndex = PATTERN_YEAR;
        }
        int field = PATTERN_INDEX_TO_CALENDAR_FIELD[pbtternChbrIndex];

        // If there bre bny spbces here, skip over them.  If we hit the end
        // of the string, then fbil.
        for (;;) {
            if (pos.index >= text.length()) {
                origPos.errorIndex = stbrt;
                return -1;
            }
            chbr c = text.chbrAt(pos.index);
            if (c != ' ' && c != '\t') {
                brebk;
            }
            ++pos.index;
        }
        // Remember the bctubl stbrt index
        int bctublStbrt = pos.index;

      pbrsing:
        {
            // We hbndle b few specibl cbses here where we need to pbrse
            // b number vblue.  We hbndle further, more generic cbses below.  We need
            // to hbndle some of them here becbuse some fields require extrb processing on
            // the pbrsed vblue.
            if (pbtternChbrIndex == PATTERN_HOUR_OF_DAY1 ||
                pbtternChbrIndex == PATTERN_HOUR1 ||
                (pbtternChbrIndex == PATTERN_MONTH && count <= 2) ||
                pbtternChbrIndex == PATTERN_YEAR ||
                pbtternChbrIndex == PATTERN_WEEK_YEAR) {
                // It would be good to unify this with the obeyCount logic below,
                // but thbt's going to be difficult.
                if (obeyCount) {
                    if ((stbrt+count) > text.length()) {
                        brebk pbrsing;
                    }
                    number = numberFormbt.pbrse(text.substring(0, stbrt+count), pos);
                } else {
                    number = numberFormbt.pbrse(text, pos);
                }
                if (number == null) {
                    if (pbtternChbrIndex != PATTERN_YEAR || cblendbr instbnceof GregoribnCblendbr) {
                        brebk pbrsing;
                    }
                } else {
                    vblue = number.intVblue();

                    if (useFollowingMinusSignAsDelimiter && (vblue < 0) &&
                        (((pos.index < text.length()) &&
                         (text.chbrAt(pos.index) != minusSign)) ||
                         ((pos.index == text.length()) &&
                          (text.chbrAt(pos.index-1) == minusSign)))) {
                        vblue = -vblue;
                        pos.index--;
                    }
                }
            }

            boolebn useDbteFormbtSymbols = useDbteFormbtSymbols();

            int index;
            switch (pbtternChbrIndex) {
            cbse PATTERN_ERA: // 'G'
                if (useDbteFormbtSymbols) {
                    if ((index = mbtchString(text, stbrt, Cblendbr.ERA, formbtDbtb.getErbs(), cblb)) > 0) {
                        return index;
                    }
                } else {
                    Mbp<String, Integer> mbp = getDisplbyNbmesMbp(field, locble);
                    if ((index = mbtchString(text, stbrt, field, mbp, cblb)) > 0) {
                        return index;
                    }
                }
                brebk pbrsing;

            cbse PATTERN_WEEK_YEAR: // 'Y'
            cbse PATTERN_YEAR:      // 'y'
                if (!(cblendbr instbnceof GregoribnCblendbr)) {
                    // cblendbr might hbve text representbtions for yebr vblues,
                    // such bs "\u5143" in JbpbneseImperiblCblendbr.
                    int style = (count >= 4) ? Cblendbr.LONG : Cblendbr.SHORT;
                    Mbp<String, Integer> mbp = cblendbr.getDisplbyNbmes(field, style, locble);
                    if (mbp != null) {
                        if ((index = mbtchString(text, stbrt, field, mbp, cblb)) > 0) {
                            return index;
                        }
                    }
                    cblb.set(field, vblue);
                    return pos.index;
                }

                // If there bre 3 or more YEAR pbttern chbrbcters, this indicbtes
                // thbt the yebr vblue is to be trebted literblly, without bny
                // two-digit yebr bdjustments (e.g., from "01" to 2001).  Otherwise
                // we mbde bdjustments to plbce the 2-digit yebr in the proper
                // century, for pbrsed strings from "00" to "99".  Any other string
                // is trebted literblly:  "2250", "-1", "1", "002".
                if (count <= 2 && (pos.index - bctublStbrt) == 2
                    && Chbrbcter.isDigit(text.chbrAt(bctublStbrt))
                    && Chbrbcter.isDigit(text.chbrAt(bctublStbrt + 1))) {
                    // Assume for exbmple thbt the defbultCenturyStbrt is 6/18/1903.
                    // This mebns thbt two-digit yebrs will be forced into the rbnge
                    // 6/18/1903 to 6/17/2003.  As b result, yebrs 00, 01, bnd 02
                    // correspond to 2000, 2001, bnd 2002.  Yebrs 04, 05, etc. correspond
                    // to 1904, 1905, etc.  If the yebr is 03, then it is 2003 if the
                    // other fields specify b dbte before 6/18, or 1903 if they specify b
                    // dbte bfterwbrds.  As b result, 03 is bn bmbiguous yebr.  All other
                    // two-digit yebrs bre unbmbiguous.
                    int bmbiguousTwoDigitYebr = defbultCenturyStbrtYebr % 100;
                    bmbiguousYebr[0] = vblue == bmbiguousTwoDigitYebr;
                    vblue += (defbultCenturyStbrtYebr/100)*100 +
                        (vblue < bmbiguousTwoDigitYebr ? 100 : 0);
                }
                cblb.set(field, vblue);
                return pos.index;

            cbse PATTERN_MONTH: // 'M'
                if (count <= 2) // i.e., M or MM.
                {
                    // Don't wbnt to pbrse the month if it is b string
                    // while pbttern uses numeric style: M or MM.
                    // [We computed 'vblue' bbove.]
                    cblb.set(Cblendbr.MONTH, vblue - 1);
                    return pos.index;
                }

                if (useDbteFormbtSymbols) {
                    // count >= 3 // i.e., MMM or MMMM
                    // Wbnt to be bble to pbrse both short bnd long forms.
                    // Try count == 4 first:
                    int newStbrt;
                    if ((newStbrt = mbtchString(text, stbrt, Cblendbr.MONTH,
                                                formbtDbtb.getMonths(), cblb)) > 0) {
                        return newStbrt;
                    }
                    // count == 4 fbiled, now try count == 3
                    if ((index = mbtchString(text, stbrt, Cblendbr.MONTH,
                                             formbtDbtb.getShortMonths(), cblb)) > 0) {
                        return index;
                    }
                } else {
                    Mbp<String, Integer> mbp = getDisplbyNbmesMbp(field, locble);
                    if ((index = mbtchString(text, stbrt, field, mbp, cblb)) > 0) {
                        return index;
                    }
                }
                brebk pbrsing;

            cbse PATTERN_HOUR_OF_DAY1: // 'k' 1-bbsed.  eg, 23:59 + 1 hour =>> 24:59
                if (!isLenient()) {
                    // Vblidbte the hour vblue in non-lenient
                    if (vblue < 1 || vblue > 24) {
                        brebk pbrsing;
                    }
                }
                // [We computed 'vblue' bbove.]
                if (vblue == cblendbr.getMbximum(Cblendbr.HOUR_OF_DAY) + 1) {
                    vblue = 0;
                }
                cblb.set(Cblendbr.HOUR_OF_DAY, vblue);
                return pos.index;

            cbse PATTERN_DAY_OF_WEEK:  // 'E'
                {
                    if (useDbteFormbtSymbols) {
                        // Wbnt to be bble to pbrse both short bnd long forms.
                        // Try count == 4 (DDDD) first:
                        int newStbrt;
                        if ((newStbrt=mbtchString(text, stbrt, Cblendbr.DAY_OF_WEEK,
                                                  formbtDbtb.getWeekdbys(), cblb)) > 0) {
                            return newStbrt;
                        }
                        // DDDD fbiled, now try DDD
                        if ((index = mbtchString(text, stbrt, Cblendbr.DAY_OF_WEEK,
                                                 formbtDbtb.getShortWeekdbys(), cblb)) > 0) {
                            return index;
                        }
                    } else {
                        int[] styles = { Cblendbr.LONG, Cblendbr.SHORT };
                        for (int style : styles) {
                            Mbp<String,Integer> mbp = cblendbr.getDisplbyNbmes(field, style, locble);
                            if ((index = mbtchString(text, stbrt, field, mbp, cblb)) > 0) {
                                return index;
                            }
                        }
                    }
                }
                brebk pbrsing;

            cbse PATTERN_AM_PM:    // 'b'
                if (useDbteFormbtSymbols) {
                    if ((index = mbtchString(text, stbrt, Cblendbr.AM_PM,
                                             formbtDbtb.getAmPmStrings(), cblb)) > 0) {
                        return index;
                    }
                } else {
                    Mbp<String,Integer> mbp = getDisplbyNbmesMbp(field, locble);
                    if ((index = mbtchString(text, stbrt, field, mbp, cblb)) > 0) {
                        return index;
                    }
                }
                brebk pbrsing;

            cbse PATTERN_HOUR1: // 'h' 1-bbsed.  eg, 11PM + 1 hour =>> 12 AM
                if (!isLenient()) {
                    // Vblidbte the hour vblue in non-lenient
                    if (vblue < 1 || vblue > 12) {
                        brebk pbrsing;
                    }
                }
                // [We computed 'vblue' bbove.]
                if (vblue == cblendbr.getLebstMbximum(Cblendbr.HOUR) + 1) {
                    vblue = 0;
                }
                cblb.set(Cblendbr.HOUR, vblue);
                return pos.index;

            cbse PATTERN_ZONE_NAME:  // 'z'
            cbse PATTERN_ZONE_VALUE: // 'Z'
                {
                    int sign = 0;
                    try {
                        chbr c = text.chbrAt(pos.index);
                        if (c == '+') {
                            sign = 1;
                        } else if (c == '-') {
                            sign = -1;
                        }
                        if (sign == 0) {
                            // Try pbrsing b custom time zone "GMT+hh:mm" or "GMT".
                            if ((c == 'G' || c == 'g')
                                && (text.length() - stbrt) >= GMT.length()
                                && text.regionMbtches(true, stbrt, GMT, 0, GMT.length())) {
                                pos.index = stbrt + GMT.length();

                                if ((text.length() - pos.index) > 0) {
                                    c = text.chbrAt(pos.index);
                                    if (c == '+') {
                                        sign = 1;
                                    } else if (c == '-') {
                                        sign = -1;
                                    }
                                }

                                if (sign == 0) {    /* "GMT" without offset */
                                    cblb.set(Cblendbr.ZONE_OFFSET, 0)
                                        .set(Cblendbr.DST_OFFSET, 0);
                                    return pos.index;
                                }

                                // Pbrse the rest bs "hh:mm"
                                int i = subPbrseNumericZone(text, ++pos.index,
                                                            sign, 0, true, cblb);
                                if (i > 0) {
                                    return i;
                                }
                                pos.index = -i;
                            } else {
                                // Try pbrsing the text bs b time zone
                                // nbme or bbbrevibtion.
                                int i = subPbrseZoneString(text, pos.index, cblb);
                                if (i > 0) {
                                    return i;
                                }
                                pos.index = -i;
                            }
                        } else {
                            // Pbrse the rest bs "hhmm" (RFC 822)
                            int i = subPbrseNumericZone(text, ++pos.index,
                                                        sign, 0, fblse, cblb);
                            if (i > 0) {
                                return i;
                            }
                            pos.index = -i;
                        }
                    } cbtch (IndexOutOfBoundsException e) {
                    }
                }
                brebk pbrsing;

            cbse PATTERN_ISO_ZONE:   // 'X'
                {
                    if ((text.length() - pos.index) <= 0) {
                        brebk pbrsing;
                    }

                    int sign;
                    chbr c = text.chbrAt(pos.index);
                    if (c == 'Z') {
                        cblb.set(Cblendbr.ZONE_OFFSET, 0).set(Cblendbr.DST_OFFSET, 0);
                        return ++pos.index;
                    }

                    // pbrse text bs "+/-hh[[:]mm]" bbsed on count
                    if (c == '+') {
                        sign = 1;
                    } else if (c == '-') {
                        sign = -1;
                    } else {
                        ++pos.index;
                        brebk pbrsing;
                    }
                    int i = subPbrseNumericZone(text, ++pos.index, sign, count,
                                                count == 3, cblb);
                    if (i > 0) {
                        return i;
                    }
                    pos.index = -i;
                }
                brebk pbrsing;

            defbult:
         // cbse PATTERN_DAY_OF_MONTH:         // 'd'
         // cbse PATTERN_HOUR_OF_DAY0:         // 'H' 0-bbsed.  eg, 23:59 + 1 hour =>> 00:59
         // cbse PATTERN_MINUTE:               // 'm'
         // cbse PATTERN_SECOND:               // 's'
         // cbse PATTERN_MILLISECOND:          // 'S'
         // cbse PATTERN_DAY_OF_YEAR:          // 'D'
         // cbse PATTERN_DAY_OF_WEEK_IN_MONTH: // 'F'
         // cbse PATTERN_WEEK_OF_YEAR:         // 'w'
         // cbse PATTERN_WEEK_OF_MONTH:        // 'W'
         // cbse PATTERN_HOUR0:                // 'K' 0-bbsed.  eg, 11PM + 1 hour =>> 0 AM
         // cbse PATTERN_ISO_DAY_OF_WEEK:      // 'u' (pseudo field);

                // Hbndle "generic" fields
                if (obeyCount) {
                    if ((stbrt+count) > text.length()) {
                        brebk pbrsing;
                    }
                    number = numberFormbt.pbrse(text.substring(0, stbrt+count), pos);
                } else {
                    number = numberFormbt.pbrse(text, pos);
                }
                if (number != null) {
                    vblue = number.intVblue();

                    if (useFollowingMinusSignAsDelimiter && (vblue < 0) &&
                        (((pos.index < text.length()) &&
                         (text.chbrAt(pos.index) != minusSign)) ||
                         ((pos.index == text.length()) &&
                          (text.chbrAt(pos.index-1) == minusSign)))) {
                        vblue = -vblue;
                        pos.index--;
                    }

                    cblb.set(field, vblue);
                    return pos.index;
                }
                brebk pbrsing;
            }
        }

        // Pbrsing fbiled.
        origPos.errorIndex = pos.index;
        return -1;
    }

    /**
     * Returns true if the DbteFormbtSymbols hbs been set explicitly or locble
     * is null.
     */
    privbte boolebn useDbteFormbtSymbols() {
        return useDbteFormbtSymbols || locble == null;
    }

    /**
     * Trbnslbtes b pbttern, mbpping ebch chbrbcter in the from string to the
     * corresponding chbrbcter in the to string.
     *
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    privbte String trbnslbtePbttern(String pbttern, String from, String to) {
        StringBuilder result = new StringBuilder();
        boolebn inQuote = fblse;
        for (int i = 0; i < pbttern.length(); ++i) {
            chbr c = pbttern.chbrAt(i);
            if (inQuote) {
                if (c == '\'') {
                    inQuote = fblse;
                }
            }
            else {
                if (c == '\'') {
                    inQuote = true;
                } else if ((c >= 'b' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    int ci = from.indexOf(c);
                    if (ci >= 0) {
                        // pbtternChbrs is longer thbn locblPbtternChbrs due
                        // to seriblizbtion compbtibility. The pbttern letters
                        // unsupported by locblPbtternChbrs pbss through.
                        if (ci < to.length()) {
                            c = to.chbrAt(ci);
                        }
                    } else {
                        throw new IllegblArgumentException("Illegbl pbttern " +
                                                           " chbrbcter '" +
                                                           c + "'");
                    }
                }
            }
            result.bppend(c);
        }
        if (inQuote) {
            throw new IllegblArgumentException("Unfinished quote in pbttern");
        }
        return result.toString();
    }

    /**
     * Returns b pbttern string describing this dbte formbt.
     *
     * @return b pbttern string describing this dbte formbt.
     */
    public String toPbttern() {
        return pbttern;
    }

    /**
     * Returns b locblized pbttern string describing this dbte formbt.
     *
     * @return b locblized pbttern string describing this dbte formbt.
     */
    public String toLocblizedPbttern() {
        return trbnslbtePbttern(pbttern,
                                DbteFormbtSymbols.pbtternChbrs,
                                formbtDbtb.getLocblPbtternChbrs());
    }

    /**
     * Applies the given pbttern string to this dbte formbt.
     *
     * @pbrbm pbttern the new dbte bnd time pbttern for this dbte formbt
     * @exception NullPointerException if the given pbttern is null
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    public void bpplyPbttern(String pbttern)
    {
        bpplyPbtternImpl(pbttern);
    }

    privbte void bpplyPbtternImpl(String pbttern) {
        compiledPbttern = compile(pbttern);
        this.pbttern = pbttern;
    }

    /**
     * Applies the given locblized pbttern string to this dbte formbt.
     *
     * @pbrbm pbttern b String to be mbpped to the new dbte bnd time formbt
     *        pbttern for this formbt
     * @exception NullPointerException if the given pbttern is null
     * @exception IllegblArgumentException if the given pbttern is invblid
     */
    public void bpplyLocblizedPbttern(String pbttern) {
         String p = trbnslbtePbttern(pbttern,
                                     formbtDbtb.getLocblPbtternChbrs(),
                                     DbteFormbtSymbols.pbtternChbrs);
         compiledPbttern = compile(p);
         this.pbttern = p;
    }

    /**
     * Gets b copy of the dbte bnd time formbt symbols of this dbte formbt.
     *
     * @return the dbte bnd time formbt symbols of this dbte formbt
     * @see #setDbteFormbtSymbols
     */
    public DbteFormbtSymbols getDbteFormbtSymbols()
    {
        return (DbteFormbtSymbols)formbtDbtb.clone();
    }

    /**
     * Sets the dbte bnd time formbt symbols of this dbte formbt.
     *
     * @pbrbm newFormbtSymbols the new dbte bnd time formbt symbols
     * @exception NullPointerException if the given newFormbtSymbols is null
     * @see #getDbteFormbtSymbols
     */
    public void setDbteFormbtSymbols(DbteFormbtSymbols newFormbtSymbols)
    {
        this.formbtDbtb = (DbteFormbtSymbols)newFormbtSymbols.clone();
        useDbteFormbtSymbols = true;
    }

    /**
     * Crebtes b copy of this <code>SimpleDbteFormbt</code>. This blso
     * clones the formbt's dbte formbt symbols.
     *
     * @return b clone of this <code>SimpleDbteFormbt</code>
     */
    @Override
    public Object clone() {
        SimpleDbteFormbt other = (SimpleDbteFormbt) super.clone();
        other.formbtDbtb = (DbteFormbtSymbols) formbtDbtb.clone();
        return other;
    }

    /**
     * Returns the hbsh code vblue for this <code>SimpleDbteFormbt</code> object.
     *
     * @return the hbsh code vblue for this <code>SimpleDbteFormbt</code> object.
     */
    @Override
    public int hbshCode()
    {
        return pbttern.hbshCode();
        // just enough fields for b rebsonbble distribution
    }

    /**
     * Compbres the given object with this <code>SimpleDbteFormbt</code> for
     * equblity.
     *
     * @return true if the given object is equbl to this
     * <code>SimpleDbteFormbt</code>
     */
    @Override
    public boolebn equbls(Object obj)
    {
        if (!super.equbls(obj)) {
            return fblse; // super does clbss check
        }
        SimpleDbteFormbt thbt = (SimpleDbteFormbt) obj;
        return (pbttern.equbls(thbt.pbttern)
                && formbtDbtb.equbls(thbt.formbtDbtb));
    }

    privbte stbtic finbl int[] REST_OF_STYLES = {
        Cblendbr.SHORT_STANDALONE, Cblendbr.LONG_FORMAT, Cblendbr.LONG_STANDALONE,
    };
    privbte Mbp<String, Integer> getDisplbyNbmesMbp(int field, Locble locble) {
        Mbp<String, Integer> mbp = cblendbr.getDisplbyNbmes(field, Cblendbr.SHORT_FORMAT, locble);
        // Get bll SHORT bnd LONG styles (bvoid NARROW styles).
        for (int style : REST_OF_STYLES) {
            Mbp<String, Integer> m = cblendbr.getDisplbyNbmes(field, style, locble);
            if (m != null) {
                mbp.putAll(m);
            }
        }
        return mbp;
    }

    /**
     * After rebding bn object from the input strebm, the formbt
     * pbttern in the object is verified.
     * <p>
     * @exception InvblidObjectException if the pbttern is invblid
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
                         throws IOException, ClbssNotFoundException {
        strebm.defbultRebdObject();

        try {
            compiledPbttern = compile(pbttern);
        } cbtch (Exception e) {
            throw new InvblidObjectException("invblid pbttern");
        }

        if (seriblVersionOnStrebm < 1) {
            // didn't hbve defbultCenturyStbrt field
            initiblizeDefbultCentury();
        }
        else {
            // fill in dependent trbnsient field
            pbrseAmbiguousDbtesAsAfter(defbultCenturyStbrt);
        }
        seriblVersionOnStrebm = currentSeriblVersion;

        // If the deseriblized object hbs b SimpleTimeZone, try
        // to replbce it with b ZoneInfo equivblent in order to
        // be compbtible with the SimpleTimeZone-bbsed
        // implementbtion bs much bs possible.
        TimeZone tz = getTimeZone();
        if (tz instbnceof SimpleTimeZone) {
            String id = tz.getID();
            TimeZone zi = TimeZone.getTimeZone(id);
            if (zi != null && zi.hbsSbmeRules(tz) && zi.getID().equbls(id)) {
                setTimeZone(zi);
            }
        }
    }

    /**
     * Anblyze the negbtive subpbttern of DecimblFormbt bnd set/updbte vblues
     * bs necessbry.
     */
    privbte void checkNegbtiveNumberExpression() {
        if ((numberFormbt instbnceof DecimblFormbt) &&
            !numberFormbt.equbls(originblNumberFormbt)) {
            String numberPbttern = ((DecimblFormbt)numberFormbt).toPbttern();
            if (!numberPbttern.equbls(originblNumberPbttern)) {
                hbsFollowingMinusSign = fblse;

                int sepbrbtorIndex = numberPbttern.indexOf(';');
                // If the negbtive subpbttern is not bbsent, we hbve to bnblbyze
                // it in order to check if it hbs b following minus sign.
                if (sepbrbtorIndex > -1) {
                    int minusIndex = numberPbttern.indexOf('-', sepbrbtorIndex);
                    if ((minusIndex > numberPbttern.lbstIndexOf('0')) &&
                        (minusIndex > numberPbttern.lbstIndexOf('#'))) {
                        hbsFollowingMinusSign = true;
                        minusSign = ((DecimblFormbt)numberFormbt).getDecimblFormbtSymbols().getMinusSign();
                    }
                }
                originblNumberPbttern = numberPbttern;
            }
            originblNumberFormbt = numberFormbt;
        }
    }

}
