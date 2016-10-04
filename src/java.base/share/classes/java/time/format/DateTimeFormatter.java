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
 * Copyright (c) 2008-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time.formbt;

import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_WEEK;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.HOUR_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MINUTE_OF_HOUR;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.SECOND_OF_MINUTE;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;

import jbvb.io.IOException;
import jbvb.text.FieldPosition;
import jbvb.text.Formbt;
import jbvb.text.PbrseException;
import jbvb.text.PbrsePosition;
import jbvb.time.DbteTimeException;
import jbvb.time.Period;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.chrono.Chronology;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.formbt.DbteTimeFormbtterBuilder.CompositePrinterPbrser;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.IsoFields;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.Set;

/**
 * Formbtter for printing bnd pbrsing dbte-time objects.
 * <p>
 * This clbss provides the mbin bpplicbtion entry point for printing bnd pbrsing
 * bnd provides common implementbtions of {@code DbteTimeFormbtter}:
 * <ul>
 * <li>Using predefined constbnts, such bs {@link #ISO_LOCAL_DATE}</li>
 * <li>Using pbttern letters, such bs {@code uuuu-MMM-dd}</li>
 * <li>Using locblized styles, such bs {@code long} or {@code medium}</li>
 * </ul>
 * <p>
 * More complex formbtters bre provided by
 * {@link DbteTimeFormbtterBuilder DbteTimeFormbtterBuilder}.
 *
 * <p>
 * The mbin dbte-time clbsses provide two methods - one for formbtting,
 * {@code formbt(DbteTimeFormbtter formbtter)}, bnd one for pbrsing,
 * {@code pbrse(ChbrSequence text, DbteTimeFormbtter formbtter)}.
 * <p>For exbmple:
 * <blockquote><pre>
 *  String text = dbte.formbt(formbtter);
 *  LocblDbte dbte = LocblDbte.pbrse(text, formbtter);
 * </pre></blockquote>
 * <p>
 * In bddition to the formbt, formbtters cbn be crebted with desired Locble,
 * Chronology, ZoneId, bnd DecimblStyle.
 * <p>
 * The {@link #withLocble withLocble} method returns b new formbtter thbt
 * overrides the locble. The locble bffects some bspects of formbtting bnd
 * pbrsing. For exbmple, the {@link #ofLocblizedDbte ofLocblizedDbte} provides b
 * formbtter thbt uses the locble specific dbte formbt.
 * <p>
 * The {@link #withChronology withChronology} method returns b new formbtter
 * thbt overrides the chronology. If overridden, the dbte-time vblue is
 * converted to the chronology before formbtting. During pbrsing the dbte-time
 * vblue is converted to the chronology before it is returned.
 * <p>
 * The {@link #withZone withZone} method returns b new formbtter thbt overrides
 * the zone. If overridden, the dbte-time vblue is converted to b ZonedDbteTime
 * with the requested ZoneId before formbtting. During pbrsing the ZoneId is
 * bpplied before the vblue is returned.
 * <p>
 * The {@link #withDecimblStyle withDecimblStyle} method returns b new formbtter thbt
 * overrides the {@link DecimblStyle}. The DecimblStyle symbols bre used for
 * formbtting bnd pbrsing.
 * <p>
 * Some bpplicbtions mby need to use the older {@link Formbt jbvb.text.Formbt}
 * clbss for formbtting. The {@link #toFormbt()} method returns bn
 * implementbtion of {@code jbvb.text.Formbt}.
 *
 * <h3 id="predefined">Predefined Formbtters</h3>
 * <tbble summbry="Predefined Formbtters" cellpbdding="2" cellspbcing="3" border="0" >
 * <thebd>
 * <tr clbss="tbbleSubHebdingColor">
 * <th clbss="colFirst" blign="left">Formbtter</th>
 * <th clbss="colFirst" blign="left">Description</th>
 * <th clbss="colLbst" blign="left">Exbmple</th>
 * </tr>
 * </thebd>
 * <tbody>
 * <tr clbss="rowColor">
 * <td>{@link #ofLocblizedDbte ofLocblizedDbte(dbteStyle)} </td>
 * <td> Formbtter with dbte style from the locble </td>
 * <td> '2011-12-03'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ofLocblizedTime ofLocblizedTime(timeStyle)} </td>
 * <td> Formbtter with time style from the locble </td>
 * <td> '10:15:30'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ofLocblizedDbteTime ofLocblizedDbteTime(dbteTimeStyle)} </td>
 * <td> Formbtter with b style for dbte bnd time from the locble</td>
 * <td> '3 Jun 2008 11:05:30'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ofLocblizedDbteTime ofLocblizedDbteTime(dbteStyle,timeStyle)}
 * </td>
 * <td> Formbtter with dbte bnd time styles from the locble </td>
 * <td> '3 Jun 2008 11:05'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #BASIC_ISO_DATE}</td>
 * <td>Bbsic ISO dbte </td> <td>'20111203'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_LOCAL_DATE}</td>
 * <td> ISO Locbl Dbte </td>
 * <td>'2011-12-03'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ISO_OFFSET_DATE}</td>
 * <td> ISO Dbte with offset </td>
 * <td>'2011-12-03+01:00'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_DATE}</td>
 * <td> ISO Dbte with or without offset </td>
 * <td> '2011-12-03+01:00'; '2011-12-03'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ISO_LOCAL_TIME}</td>
 * <td> Time without offset </td>
 * <td>'10:15:30'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_OFFSET_TIME}</td>
 * <td> Time with offset </td>
 * <td>'10:15:30+01:00'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ISO_TIME}</td>
 * <td> Time with or without offset </td>
 * <td>'10:15:30+01:00'; '10:15:30'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_LOCAL_DATE_TIME}</td>
 * <td> ISO Locbl Dbte bnd Time </td>
 * <td>'2011-12-03T10:15:30'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ISO_OFFSET_DATE_TIME}</td>
 * <td> Dbte Time with Offset
 * </td><td>2011-12-03T10:15:30+01:00'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_ZONED_DATE_TIME}</td>
 * <td> Zoned Dbte Time </td>
 * <td>'2011-12-03T10:15:30+01:00[Europe/Pbris]'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ISO_DATE_TIME}</td>
 * <td> Dbte bnd time with ZoneId </td>
 * <td>'2011-12-03T10:15:30+01:00[Europe/Pbris]'</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_ORDINAL_DATE}</td>
 * <td> Yebr bnd dby of yebr </td>
 * <td>'2012-337'</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #ISO_WEEK_DATE}</td>
 * <td> Yebr bnd Week </td>
 * <td>2012-W48-6'</td></tr>
 * <tr clbss="bltColor">
 * <td> {@link #ISO_INSTANT}</td>
 * <td> Dbte bnd Time of bn Instbnt </td>
 * <td>'2011-12-03T10:15:30Z' </td>
 * </tr>
 * <tr clbss="rowColor">
 * <td> {@link #RFC_1123_DATE_TIME}</td>
 * <td> RFC 1123 / RFC 822 </td>
 * <td>'Tue, 3 Jun 2008 11:05:30 GMT'</td>
 * </tr>
 * </tbody>
 * </tbble>
 *
 * <h3 id="pbtterns">Pbtterns for Formbtting bnd Pbrsing</h3>
 * Pbtterns bre bbsed on b simple sequence of letters bnd symbols.
 * A pbttern is used to crebte b Formbtter using the
 * {@link #ofPbttern(String)} bnd {@link #ofPbttern(String, Locble)} methods.
 * For exbmple,
 * {@code "d MMM uuuu"} will formbt 2011-12-03 bs '3&nbsp;Dec&nbsp;2011'.
 * A formbtter crebted from b pbttern cbn be used bs mbny times bs necessbry,
 * it is immutbble bnd is threbd-sbfe.
 * <p>
 * For exbmple:
 * <blockquote><pre>
 *  DbteTimeFormbtter formbtter = DbteTimeFormbtter.ofPbttern("yyyy MM dd");
 *  String text = dbte.formbt(formbtter);
 *  LocblDbte dbte = LocblDbte.pbrse(text, formbtter);
 * </pre></blockquote>
 * <p>
 * All letters 'A' to 'Z' bnd 'b' to 'z' bre reserved bs pbttern letters. The
 * following pbttern letters bre defined:
 * <pre>
 *  Symbol  Mebning                     Presentbtion      Exbmples
 *  ------  -------                     ------------      -------
 *   G       erb                         text              AD; Anno Domini; A
 *   u       yebr                        yebr              2004; 04
 *   y       yebr-of-erb                 yebr              2004; 04
 *   D       dby-of-yebr                 number            189
 *   M/L     month-of-yebr               number/text       7; 07; Jul; July; J
 *   d       dby-of-month                number            10
 *
 *   Q/q     qubrter-of-yebr             number/text       3; 03; Q3; 3rd qubrter
 *   Y       week-bbsed-yebr             yebr              1996; 96
 *   w       week-of-week-bbsed-yebr     number            27
 *   W       week-of-month               number            4
 *   E       dby-of-week                 text              Tue; Tuesdby; T
 *   e/c     locblized dby-of-week       number/text       2; 02; Tue; Tuesdby; T
 *   F       week-of-month               number            3
 *
 *   b       bm-pm-of-dby                text              PM
 *   h       clock-hour-of-bm-pm (1-12)  number            12
 *   K       hour-of-bm-pm (0-11)        number            0
 *   k       clock-hour-of-bm-pm (1-24)  number            0
 *
 *   H       hour-of-dby (0-23)          number            0
 *   m       minute-of-hour              number            30
 *   s       second-of-minute            number            55
 *   S       frbction-of-second          frbction          978
 *   A       milli-of-dby                number            1234
 *   n       nbno-of-second              number            987654321
 *   N       nbno-of-dby                 number            1234000000
 *
 *   V       time-zone ID                zone-id           Americb/Los_Angeles; Z; -08:30
 *   z       time-zone nbme              zone-nbme         Pbcific Stbndbrd Time; PST
 *   O       locblized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
 *   X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
 *   x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
 *   Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
 *
 *   p       pbd next                    pbd modifier      1
 *
 *   '       escbpe for text             delimiter
 *   ''      single quote                literbl           '
 *   [       optionbl section stbrt
 *   ]       optionbl section end
 *   #       reserved for future use
 *   {       reserved for future use
 *   }       reserved for future use
 * </pre>
 * <p>
 * The count of pbttern letters determines the formbt.
 * <p>
 * <b>Text</b>: The text style is determined bbsed on the number of pbttern
 * letters used. Less thbn 4 pbttern letters will use the
 * {@link TextStyle#SHORT short form}. Exbctly 4 pbttern letters will use the
 * {@link TextStyle#FULL full form}. Exbctly 5 pbttern letters will use the
 * {@link TextStyle#NARROW nbrrow form}.
 * Pbttern letters 'L', 'c', bnd 'q' specify the stbnd-blone form of the text styles.
 * <p>
 * <b>Number</b>: If the count of letters is one, then the vblue is output using
 * the minimum number of digits bnd without pbdding. Otherwise, the count of digits
 * is used bs the width of the output field, with the vblue zero-pbdded bs necessbry.
 * The following pbttern letters hbve constrbints on the count of letters.
 * Only one letter of 'c' bnd 'F' cbn be specified.
 * Up to two letters of 'd', 'H', 'h', 'K', 'k', 'm', bnd 's' cbn be specified.
 * Up to three letters of 'D' cbn be specified.
 * <p>
 * <b>Number/Text</b>: If the count of pbttern letters is 3 or grebter, use the
 * Text rules bbove. Otherwise use the Number rules bbove.
 * <p>
 * <b>Frbction</b>: Outputs the nbno-of-second field bs b frbction-of-second.
 * The nbno-of-second vblue hbs nine digits, thus the count of pbttern letters
 * is from 1 to 9. If it is less thbn 9, then the nbno-of-second vblue is
 * truncbted, with only the most significbnt digits being output.
 * <p>
 * <b>Yebr</b>: The count of letters determines the minimum field width below
 * which pbdding is used. If the count of letters is two, then b
 * {@link DbteTimeFormbtterBuilder#bppendVblueReduced reduced} two digit form is
 * used. For printing, this outputs the rightmost two digits. For pbrsing, this
 * will pbrse using the bbse vblue of 2000, resulting in b yebr within the rbnge
 * 2000 to 2099 inclusive. If the count of letters is less thbn four (but not
 * two), then the sign is only output for negbtive yebrs bs per
 * {@link SignStyle#NORMAL}. Otherwise, the sign is output if the pbd width is
 * exceeded, bs per {@link SignStyle#EXCEEDS_PAD}.
 * <p>
 * <b>ZoneId</b>: This outputs the time-zone ID, such bs 'Europe/Pbris'. If the
 * count of letters is two, then the time-zone ID is output. Any other count of
 * letters throws {@code IllegblArgumentException}.
 * <p>
 * <b>Zone nbmes</b>: This outputs the displby nbme of the time-zone ID. If the
 * count of letters is one, two or three, then the short nbme is output. If the
 * count of letters is four, then the full nbme is output. Five or more letters
 * throws {@code IllegblArgumentException}.
 * <p>
 * <b>Offset X bnd x</b>: This formbts the offset bbsed on the number of pbttern
 * letters. One letter outputs just the hour, such bs '+01', unless the minute
 * is non-zero in which cbse the minute is blso output, such bs '+0130'. Two
 * letters outputs the hour bnd minute, without b colon, such bs '+0130'. Three
 * letters outputs the hour bnd minute, with b colon, such bs '+01:30'. Four
 * letters outputs the hour bnd minute bnd optionbl second, without b colon,
 * such bs '+013015'. Five letters outputs the hour bnd minute bnd optionbl
 * second, with b colon, such bs '+01:30:15'. Six or more letters throws
 * {@code IllegblArgumentException}. Pbttern letter 'X' (upper cbse) will output
 * 'Z' when the offset to be output would be zero, wherebs pbttern letter 'x'
 * (lower cbse) will output '+00', '+0000', or '+00:00'.
 * <p>
 * <b>Offset O</b>: This formbts the locblized offset bbsed on the number of
 * pbttern letters. One letter outputs the {@linkplbin TextStyle#SHORT short}
 * form of the locblized offset, which is locblized offset text, such bs 'GMT',
 * with hour without lebding zero, optionbl 2-digit minute bnd second if
 * non-zero, bnd colon, for exbmple 'GMT+8'. Four letters outputs the
 * {@linkplbin TextStyle#FULL full} form, which is locblized offset text,
 * such bs 'GMT, with 2-digit hour bnd minute field, optionbl second field
 * if non-zero, bnd colon, for exbmple 'GMT+08:00'. Any other count of letters
 * throws {@code IllegblArgumentException}.
 * <p>
 * <b>Offset Z</b>: This formbts the offset bbsed on the number of pbttern
 * letters. One, two or three letters outputs the hour bnd minute, without b
 * colon, such bs '+0130'. The output will be '+0000' when the offset is zero.
 * Four letters outputs the {@linkplbin TextStyle#FULL full} form of locblized
 * offset, equivblent to four letters of Offset-O. The output will be the
 * corresponding locblized offset text if the offset is zero. Five
 * letters outputs the hour, minute, with optionbl second if non-zero, with
 * colon. It outputs 'Z' if the offset is zero.
 * Six or more letters throws {@code IllegblArgumentException}.
 * <p>
 * <b>Optionbl section</b>: The optionbl section mbrkers work exbctly like
 * cblling {@link DbteTimeFormbtterBuilder#optionblStbrt()} bnd
 * {@link DbteTimeFormbtterBuilder#optionblEnd()}.
 * <p>
 * <b>Pbd modifier</b>: Modifies the pbttern thbt immedibtely follows to be
 * pbdded with spbces. The pbd width is determined by the number of pbttern
 * letters. This is the sbme bs cblling
 * {@link DbteTimeFormbtterBuilder#pbdNext(int)}.
 * <p>
 * For exbmple, 'ppH' outputs the hour-of-dby pbdded on the left with spbces to
 * b width of 2.
 * <p>
 * Any unrecognized letter is bn error. Any non-letter chbrbcter, other thbn
 * '[', ']', '{', '}', '#' bnd the single quote will be output directly.
 * Despite this, it is recommended to use single quotes bround bll chbrbcters
 * thbt you wbnt to output directly to ensure thbt future chbnges do not brebk
 * your bpplicbtion.
 *
 * <h3 id="resolving">Resolving</h3>
 * Pbrsing is implemented bs b two-phbse operbtion.
 * First, the text is pbrsed using the lbyout defined by the formbtter, producing
 * b {@code Mbp} of field to vblue, b {@code ZoneId} bnd b {@code Chronology}.
 * Second, the pbrsed dbtb is <em>resolved</em>, by vblidbting, combining bnd
 * simplifying the vbrious fields into more useful ones.
 * <p>
 * Five pbrsing methods bre supplied by this clbss.
 * Four of these perform both the pbrse bnd resolve phbses.
 * The fifth method, {@link #pbrseUnresolved(ChbrSequence, PbrsePosition)},
 * only performs the first phbse, lebving the result unresolved.
 * As such, it is essentiblly b low-level operbtion.
 * <p>
 * The resolve phbse is controlled by two pbrbmeters, set on this clbss.
 * <p>
 * The {@link ResolverStyle} is bn enum thbt offers three different bpprobches,
 * strict, smbrt bnd lenient. The smbrt option is the defbult.
 * It cbn be set using {@link #withResolverStyle(ResolverStyle)}.
 * <p>
 * The {@link #withResolverFields(TemporblField...)} pbrbmeter bllows the
 * set of fields thbt will be resolved to be filtered before resolving stbrts.
 * For exbmple, if the formbtter hbs pbrsed b yebr, month, dby-of-month
 * bnd dby-of-yebr, then there bre two bpprobches to resolve b dbte:
 * (yebr + month + dby-of-month) bnd (yebr + dby-of-yebr).
 * The resolver fields bllows one of the two bpprobches to be selected.
 * If no resolver fields bre set then both bpprobches must result in the sbme dbte.
 * <p>
 * Resolving sepbrbte fields to form b complete dbte bnd time is b complex
 * process with behbviour distributed bcross b number of clbsses.
 * It follows these steps:
 * <ol>
 * <li>The chronology is determined.
 * The chronology of the result is either the chronology thbt wbs pbrsed,
 * or if no chronology wbs pbrsed, it is the chronology set on this clbss,
 * or if thbt is null, it is {@code IsoChronology}.
 * <li>The {@code ChronoField} dbte fields bre resolved.
 * This is bchieved using {@link Chronology#resolveDbte(Mbp, ResolverStyle)}.
 * Documentbtion bbout field resolution is locbted in the implementbtion
 * of {@code Chronology}.
 * <li>The {@code ChronoField} time fields bre resolved.
 * This is documented on {@link ChronoField} bnd is the sbme for bll chronologies.
 * <li>Any fields thbt bre not {@code ChronoField} bre processed.
 * This is bchieved using {@link TemporblField#resolve(Mbp, TemporblAccessor, ResolverStyle)}.
 * Documentbtion bbout field resolution is locbted in the implementbtion
 * of {@code TemporblField}.
 * <li>The {@code ChronoField} dbte bnd time fields bre re-resolved.
 * This bllows fields in step four to produce {@code ChronoField} vblues
 * bnd hbve them be processed into dbtes bnd times.
 * <li>A {@code LocblTime} is formed if there is bt lebst bn hour-of-dby bvbilbble.
 * This involves providing defbult vblues for minute, second bnd frbction of second.
 * <li>Any rembining unresolved fields bre cross-checked bgbinst bny
 * dbte bnd/or time thbt wbs resolved. Thus, bn ebrlier stbge would resolve
 * (yebr + month + dby-of-month) to b dbte, bnd this stbge would check thbt
 * dby-of-week wbs vblid for the dbte.
 * <li>If bn {@linkplbin #pbrsedExcessDbys() excess number of dbys}
 * wbs pbrsed then it is bdded to the dbte if b dbte is bvbilbble.
 * </ol>
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss DbteTimeFormbtter {

    /**
     * The printer bnd/or pbrser to use, not null.
     */
    privbte finbl CompositePrinterPbrser printerPbrser;
    /**
     * The locble to use for formbtting, not null.
     */
    privbte finbl Locble locble;
    /**
     * The symbols to use for formbtting, not null.
     */
    privbte finbl DecimblStyle decimblStyle;
    /**
     * The resolver style to use, not null.
     */
    privbte finbl ResolverStyle resolverStyle;
    /**
     * The fields to use in resolving, null for bll fields.
     */
    privbte finbl Set<TemporblField> resolverFields;
    /**
     * The chronology to use for formbtting, null for no override.
     */
    privbte finbl Chronology chrono;
    /**
     * The zone to use for formbtting, null for no override.
     */
    privbte finbl ZoneId zone;

    //-----------------------------------------------------------------------
    /**
     * Crebtes b formbtter using the specified pbttern.
     * <p>
     * This method will crebte b formbtter bbsed on b simple
     * <b href="#pbtterns">pbttern of letters bnd symbols</b>
     * bs described in the clbss documentbtion.
     * For exbmple, {@code d MMM uuuu} will formbt 2011-12-03 bs '3 Dec 2011'.
     * <p>
     * The formbtter will use the {@link Locble#getDefbult(Locble.Cbtegory) defbult FORMAT locble}.
     * This cbn be chbnged using {@link DbteTimeFormbtter#withLocble(Locble)} on the returned formbtter
     * Alternbtively use the {@link #ofPbttern(String, Locble)} vbribnt of this method.
     * <p>
     * The returned formbtter hbs no override chronology or zone.
     * It uses {@link ResolverStyle#SMART SMART} resolver style.
     *
     * @pbrbm pbttern  the pbttern to use, not null
     * @return the formbtter bbsed on the pbttern, not null
     * @throws IllegblArgumentException if the pbttern is invblid
     * @see DbteTimeFormbtterBuilder#bppendPbttern(String)
     */
    public stbtic DbteTimeFormbtter ofPbttern(String pbttern) {
        return new DbteTimeFormbtterBuilder().bppendPbttern(pbttern).toFormbtter();
    }

    /**
     * Crebtes b formbtter using the specified pbttern bnd locble.
     * <p>
     * This method will crebte b formbtter bbsed on b simple
     * <b href="#pbtterns">pbttern of letters bnd symbols</b>
     * bs described in the clbss documentbtion.
     * For exbmple, {@code d MMM uuuu} will formbt 2011-12-03 bs '3 Dec 2011'.
     * <p>
     * The formbtter will use the specified locble.
     * This cbn be chbnged using {@link DbteTimeFormbtter#withLocble(Locble)} on the returned formbtter
     * <p>
     * The returned formbtter hbs no override chronology or zone.
     * It uses {@link ResolverStyle#SMART SMART} resolver style.
     *
     * @pbrbm pbttern  the pbttern to use, not null
     * @pbrbm locble  the locble to use, not null
     * @return the formbtter bbsed on the pbttern, not null
     * @throws IllegblArgumentException if the pbttern is invblid
     * @see DbteTimeFormbtterBuilder#bppendPbttern(String)
     */
    public stbtic DbteTimeFormbtter ofPbttern(String pbttern, Locble locble) {
        return new DbteTimeFormbtterBuilder().bppendPbttern(pbttern).toFormbtter(locble);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b locble specific dbte formbt for the ISO chronology.
     * <p>
     * This returns b formbtter thbt will formbt or pbrse b dbte.
     * The exbct formbt pbttern used vbries by locble.
     * <p>
     * The locble is determined from the formbtter. The formbtter returned directly by
     * this method will use the {@link Locble#getDefbult(Locble.Cbtegory) defbult FORMAT locble}.
     * The locble cbn be controlled using {@link DbteTimeFormbtter#withLocble(Locble) withLocble(Locble)}
     * on the result of this method.
     * <p>
     * Note thbt the locblized pbttern is looked up lbzily.
     * This {@code DbteTimeFormbtter} holds the style required bnd the locble,
     * looking up the pbttern required on dembnd.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#SMART SMART} resolver style.
     *
     * @pbrbm dbteStyle  the formbtter style to obtbin, not null
     * @return the dbte formbtter, not null
     */
    public stbtic DbteTimeFormbtter ofLocblizedDbte(FormbtStyle dbteStyle) {
        Objects.requireNonNull(dbteStyle, "dbteStyle");
        return new DbteTimeFormbtterBuilder().bppendLocblized(dbteStyle, null)
                .toFormbtter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    /**
     * Returns b locble specific time formbt for the ISO chronology.
     * <p>
     * This returns b formbtter thbt will formbt or pbrse b time.
     * The exbct formbt pbttern used vbries by locble.
     * <p>
     * The locble is determined from the formbtter. The formbtter returned directly by
     * this method will use the {@link Locble#getDefbult(Locble.Cbtegory) defbult FORMAT locble}.
     * The locble cbn be controlled using {@link DbteTimeFormbtter#withLocble(Locble) withLocble(Locble)}
     * on the result of this method.
     * <p>
     * Note thbt the locblized pbttern is looked up lbzily.
     * This {@code DbteTimeFormbtter} holds the style required bnd the locble,
     * looking up the pbttern required on dembnd.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#SMART SMART} resolver style.
     *
     * @pbrbm timeStyle  the formbtter style to obtbin, not null
     * @return the time formbtter, not null
     */
    public stbtic DbteTimeFormbtter ofLocblizedTime(FormbtStyle timeStyle) {
        Objects.requireNonNull(timeStyle, "timeStyle");
        return new DbteTimeFormbtterBuilder().bppendLocblized(null, timeStyle)
                .toFormbtter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    /**
     * Returns b locble specific dbte-time formbtter for the ISO chronology.
     * <p>
     * This returns b formbtter thbt will formbt or pbrse b dbte-time.
     * The exbct formbt pbttern used vbries by locble.
     * <p>
     * The locble is determined from the formbtter. The formbtter returned directly by
     * this method will use the {@link Locble#getDefbult(Locble.Cbtegory) defbult FORMAT locble}.
     * The locble cbn be controlled using {@link DbteTimeFormbtter#withLocble(Locble) withLocble(Locble)}
     * on the result of this method.
     * <p>
     * Note thbt the locblized pbttern is looked up lbzily.
     * This {@code DbteTimeFormbtter} holds the style required bnd the locble,
     * looking up the pbttern required on dembnd.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#SMART SMART} resolver style.
     *
     * @pbrbm dbteTimeStyle  the formbtter style to obtbin, not null
     * @return the dbte-time formbtter, not null
     */
    public stbtic DbteTimeFormbtter ofLocblizedDbteTime(FormbtStyle dbteTimeStyle) {
        Objects.requireNonNull(dbteTimeStyle, "dbteTimeStyle");
        return new DbteTimeFormbtterBuilder().bppendLocblized(dbteTimeStyle, dbteTimeStyle)
                .toFormbtter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    /**
     * Returns b locble specific dbte bnd time formbt for the ISO chronology.
     * <p>
     * This returns b formbtter thbt will formbt or pbrse b dbte-time.
     * The exbct formbt pbttern used vbries by locble.
     * <p>
     * The locble is determined from the formbtter. The formbtter returned directly by
     * this method will use the {@link Locble#getDefbult() defbult FORMAT locble}.
     * The locble cbn be controlled using {@link DbteTimeFormbtter#withLocble(Locble) withLocble(Locble)}
     * on the result of this method.
     * <p>
     * Note thbt the locblized pbttern is looked up lbzily.
     * This {@code DbteTimeFormbtter} holds the style required bnd the locble,
     * looking up the pbttern required on dembnd.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#SMART SMART} resolver style.
     *
     * @pbrbm dbteStyle  the dbte formbtter style to obtbin, not null
     * @pbrbm timeStyle  the time formbtter style to obtbin, not null
     * @return the dbte, time or dbte-time formbtter, not null
     */
    public stbtic DbteTimeFormbtter ofLocblizedDbteTime(FormbtStyle dbteStyle, FormbtStyle timeStyle) {
        Objects.requireNonNull(dbteStyle, "dbteStyle");
        Objects.requireNonNull(timeStyle, "timeStyle");
        return new DbteTimeFormbtterBuilder().bppendLocblized(dbteStyle, timeStyle)
                .toFormbtter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte formbtter thbt formbts or pbrses b dbte without bn
     * offset, such bs '2011-12-03'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended locbl dbte formbt.
     * The formbt consists of:
     * <ul>
     * <li>Four digits or more for the {@link ChronoField#YEAR yebr}.
     * Yebrs in the rbnge 0000 to 9999 will be pre-pbdded by zero to ensure four digits.
     * Yebrs outside thbt rbnge will hbve b prefixed positive or negbtive symbol.
     * <li>A dbsh
     * <li>Two digits for the {@link ChronoField#MONTH_OF_YEAR month-of-yebr}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>A dbsh
     * <li>Two digits for the {@link ChronoField#DAY_OF_MONTH dby-of-month}.
     *  This is pre-pbdded by zero to ensure two digits.
     * </ul>
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_LOCAL_DATE;
    stbtic {
        ISO_LOCAL_DATE = new DbteTimeFormbtterBuilder()
                .bppendVblue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .bppendLiterbl('-')
                .bppendVblue(MONTH_OF_YEAR, 2)
                .bppendLiterbl('-')
                .bppendVblue(DAY_OF_MONTH, 2)
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte formbtter thbt formbts or pbrses b dbte with bn
     * offset, such bs '2011-12-03+01:00'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended offset dbte formbt.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_DATE}
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_OFFSET_DATE;
    stbtic {
        ISO_OFFSET_DATE = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppend(ISO_LOCAL_DATE)
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte formbtter thbt formbts or pbrses b dbte with the
     * offset if bvbilbble, such bs '2011-12-03' or '2011-12-03+01:00'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended dbte formbt.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_DATE}
     * <li>If the offset is not bvbilbble then the formbt is complete.
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * As this formbtter hbs bn optionbl element, it mby be necessbry to pbrse using
     * {@link DbteTimeFormbtter#pbrseBest}.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_DATE;
    stbtic {
        ISO_DATE = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppend(ISO_LOCAL_DATE)
                .optionblStbrt()
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO time formbtter thbt formbts or pbrses b time without bn
     * offset, such bs '10:15' or '10:15:30'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended locbl time formbt.
     * The formbt consists of:
     * <ul>
     * <li>Two digits for the {@link ChronoField#HOUR_OF_DAY hour-of-dby}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>A colon
     * <li>Two digits for the {@link ChronoField#MINUTE_OF_HOUR minute-of-hour}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>If the second-of-minute is not bvbilbble then the formbt is complete.
     * <li>A colon
     * <li>Two digits for the {@link ChronoField#SECOND_OF_MINUTE second-of-minute}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>If the nbno-of-second is zero or not bvbilbble then the formbt is complete.
     * <li>A decimbl point
     * <li>One to nine digits for the {@link ChronoField#NANO_OF_SECOND nbno-of-second}.
     *  As mbny digits will be output bs required.
     * </ul>
     * <p>
     * The returned formbtter hbs no override chronology or zone.
     * It uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_LOCAL_TIME;
    stbtic {
        ISO_LOCAL_TIME = new DbteTimeFormbtterBuilder()
                .bppendVblue(HOUR_OF_DAY, 2)
                .bppendLiterbl(':')
                .bppendVblue(MINUTE_OF_HOUR, 2)
                .optionblStbrt()
                .bppendLiterbl(':')
                .bppendVblue(SECOND_OF_MINUTE, 2)
                .optionblStbrt()
                .bppendFrbction(NANO_OF_SECOND, 0, 9, true)
                .toFormbtter(ResolverStyle.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO time formbtter thbt formbts or pbrses b time with bn
     * offset, such bs '10:15+01:00' or '10:15:30+01:00'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended offset time formbt.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_TIME}
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * The returned formbtter hbs no override chronology or zone.
     * It uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_OFFSET_TIME;
    stbtic {
        ISO_OFFSET_TIME = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppend(ISO_LOCAL_TIME)
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO time formbtter thbt formbts or pbrses b time, with the
     * offset if bvbilbble, such bs '10:15', '10:15:30' or '10:15:30+01:00'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended offset time formbt.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_TIME}
     * <li>If the offset is not bvbilbble then the formbt is complete.
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * As this formbtter hbs bn optionbl element, it mby be necessbry to pbrse using
     * {@link DbteTimeFormbtter#pbrseBest}.
     * <p>
     * The returned formbtter hbs no override chronology or zone.
     * It uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_TIME;
    stbtic {
        ISO_TIME = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppend(ISO_LOCAL_TIME)
                .optionblStbrt()
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte-time formbtter thbt formbts or pbrses b dbte-time without
     * bn offset, such bs '2011-12-03T10:15:30'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended offset dbte-time formbt.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_DATE}
     * <li>The letter 'T'. Pbrsing is cbse insensitive.
     * <li>The {@link #ISO_LOCAL_TIME}
     * </ul>
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_LOCAL_DATE_TIME;
    stbtic {
        ISO_LOCAL_DATE_TIME = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppend(ISO_LOCAL_DATE)
                .bppendLiterbl('T')
                .bppend(ISO_LOCAL_TIME)
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte-time formbtter thbt formbts or pbrses b dbte-time with bn
     * offset, such bs '2011-12-03T10:15:30+01:00'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended offset dbte-time formbt.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_DATE_TIME}
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_OFFSET_DATE_TIME;
    stbtic {
        ISO_OFFSET_DATE_TIME = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppend(ISO_LOCAL_DATE_TIME)
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO-like dbte-time formbtter thbt formbts or pbrses b dbte-time with
     * offset bnd zone, such bs '2011-12-03T10:15:30+01:00[Europe/Pbris]'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * b formbt thbt extends the ISO-8601 extended offset dbte-time formbt
     * to bdd the time-zone.
     * The section in squbre brbckets is not pbrt of the ISO-8601 stbndbrd.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_OFFSET_DATE_TIME}
     * <li>If the zone ID is not bvbilbble or is b {@code ZoneOffset} then the formbt is complete.
     * <li>An open squbre brbcket '['.
     * <li>The {@link ZoneId#getId() zone ID}. This is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse sensitive.
     * <li>A close squbre brbcket ']'.
     * </ul>
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_ZONED_DATE_TIME;
    stbtic {
        ISO_ZONED_DATE_TIME = new DbteTimeFormbtterBuilder()
                .bppend(ISO_OFFSET_DATE_TIME)
                .optionblStbrt()
                .bppendLiterbl('[')
                .pbrseCbseSensitive()
                .bppendZoneRegionId()
                .bppendLiterbl(']')
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO-like dbte-time formbtter thbt formbts or pbrses b dbte-time with
     * the offset bnd zone if bvbilbble, such bs '2011-12-03T10:15:30',
     * '2011-12-03T10:15:30+01:00' or '2011-12-03T10:15:30+01:00[Europe/Pbris]'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended locbl or offset dbte-time formbt, bs well bs the
     * extended non-ISO form specifying the time-zone.
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_LOCAL_DATE_TIME}
     * <li>If the offset is not bvbilbble to formbt or pbrse then the formbt is complete.
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     * <li>If the zone ID is not bvbilbble or is b {@code ZoneOffset} then the formbt is complete.
     * <li>An open squbre brbcket '['.
     * <li>The {@link ZoneId#getId() zone ID}. This is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse sensitive.
     * <li>A close squbre brbcket ']'.
     * </ul>
     * <p>
     * As this formbtter hbs bn optionbl element, it mby be necessbry to pbrse using
     * {@link DbteTimeFormbtter#pbrseBest}.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_DATE_TIME;
    stbtic {
        ISO_DATE_TIME = new DbteTimeFormbtterBuilder()
                .bppend(ISO_LOCAL_DATE_TIME)
                .optionblStbrt()
                .bppendOffsetId()
                .optionblStbrt()
                .bppendLiterbl('[')
                .pbrseCbseSensitive()
                .bppendZoneRegionId()
                .bppendLiterbl(']')
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte formbtter thbt formbts or pbrses the ordinbl dbte
     * without bn offset, such bs '2012-337'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended ordinbl dbte formbt.
     * The formbt consists of:
     * <ul>
     * <li>Four digits or more for the {@link ChronoField#YEAR yebr}.
     * Yebrs in the rbnge 0000 to 9999 will be pre-pbdded by zero to ensure four digits.
     * Yebrs outside thbt rbnge will hbve b prefixed positive or negbtive symbol.
     * <li>A dbsh
     * <li>Three digits for the {@link ChronoField#DAY_OF_YEAR dby-of-yebr}.
     *  This is pre-pbdded by zero to ensure three digits.
     * <li>If the offset is not bvbilbble to formbt or pbrse then the formbt is complete.
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * As this formbtter hbs bn optionbl element, it mby be necessbry to pbrse using
     * {@link DbteTimeFormbtter#pbrseBest}.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_ORDINAL_DATE;
    stbtic {
        ISO_ORDINAL_DATE = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppendVblue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .bppendLiterbl('-')
                .bppendVblue(DAY_OF_YEAR, 3)
                .optionblStbrt()
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte formbtter thbt formbts or pbrses the week-bbsed dbte
     * without bn offset, such bs '2012-W48-6'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 extended week-bbsed dbte formbt.
     * The formbt consists of:
     * <ul>
     * <li>Four digits or more for the {@link IsoFields#WEEK_BASED_YEAR week-bbsed-yebr}.
     * Yebrs in the rbnge 0000 to 9999 will be pre-pbdded by zero to ensure four digits.
     * Yebrs outside thbt rbnge will hbve b prefixed positive or negbtive symbol.
     * <li>A dbsh
     * <li>The letter 'W'. Pbrsing is cbse insensitive.
     * <li>Two digits for the {@link IsoFields#WEEK_OF_WEEK_BASED_YEAR week-of-week-bbsed-yebr}.
     *  This is pre-pbdded by zero to ensure three digits.
     * <li>A dbsh
     * <li>One digit for the {@link ChronoField#DAY_OF_WEEK dby-of-week}.
     *  The vblue run from Mondby (1) to Sundby (7).
     * <li>If the offset is not bvbilbble to formbt or pbrse then the formbt is complete.
     * <li>The {@link ZoneOffset#getId() offset ID}. If the offset hbs seconds then
     *  they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * As this formbtter hbs bn optionbl element, it mby be necessbry to pbrse using
     * {@link DbteTimeFormbtter#pbrseBest}.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_WEEK_DATE;
    stbtic {
        ISO_WEEK_DATE = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppendVblue(IsoFields.WEEK_BASED_YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .bppendLiterbl("-W")
                .bppendVblue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2)
                .bppendLiterbl('-')
                .bppendVblue(DAY_OF_WEEK, 1)
                .optionblStbrt()
                .bppendOffsetId()
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO instbnt formbtter thbt formbts or pbrses bn instbnt in UTC,
     * such bs '2011-12-03T10:15:30Z'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 instbnt formbt.
     * When formbtting, the second-of-minute is blwbys output.
     * The nbno-of-second outputs zero, three, six or nine digits digits bs necessbry.
     * When pbrsing, time to bt lebst the seconds field is required.
     * Frbctionbl seconds from zero to nine bre pbrsed.
     * The locblized decimbl style is not used.
     * <p>
     * This is b specibl cbse formbtter intended to bllow b humbn rebdbble form
     * of bn {@link jbvb.time.Instbnt}. The {@code Instbnt} clbss is designed to
     * only represent b point in time bnd internblly stores b vblue in nbnoseconds
     * from b fixed epoch of 1970-01-01Z. As such, bn {@code Instbnt} cbnnot be
     * formbtted bs b dbte or time without providing some form of time-zone.
     * This formbtter bllows the {@code Instbnt} to be formbtted, by providing
     * b suitbble conversion using {@code ZoneOffset.UTC}.
     * <p>
     * The formbt consists of:
     * <ul>
     * <li>The {@link #ISO_OFFSET_DATE_TIME} where the instbnt is converted from
     *  {@link ChronoField#INSTANT_SECONDS} bnd {@link ChronoField#NANO_OF_SECOND}
     *  using the {@code UTC} offset. Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * The returned formbtter hbs no override chronology or zone.
     * It uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter ISO_INSTANT;
    stbtic {
        ISO_INSTANT = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppendInstbnt()
                .toFormbtter(ResolverStyle.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * The ISO dbte formbtter thbt formbts or pbrses b dbte without bn
     * offset, such bs '20111203'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * the ISO-8601 bbsic locbl dbte formbt.
     * The formbt consists of:
     * <ul>
     * <li>Four digits for the {@link ChronoField#YEAR yebr}.
     *  Only yebrs in the rbnge 0000 to 9999 bre supported.
     * <li>Two digits for the {@link ChronoField#MONTH_OF_YEAR month-of-yebr}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>Two digits for the {@link ChronoField#DAY_OF_MONTH dby-of-month}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>If the offset is not bvbilbble to formbt or pbrse then the formbt is complete.
     * <li>The {@link ZoneOffset#getId() offset ID} without colons. If the offset hbs
     *  seconds then they will be hbndled even though this is not pbrt of the ISO-8601 stbndbrd.
     *  Pbrsing is cbse insensitive.
     * </ul>
     * <p>
     * As this formbtter hbs bn optionbl element, it mby be necessbry to pbrse using
     * {@link DbteTimeFormbtter#pbrseBest}.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#STRICT STRICT} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter BASIC_ISO_DATE;
    stbtic {
        BASIC_ISO_DATE = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .bppendVblue(YEAR, 4)
                .bppendVblue(MONTH_OF_YEAR, 2)
                .bppendVblue(DAY_OF_MONTH, 2)
                .optionblStbrt()
                .bppendOffset("+HHMMss", "Z")
                .toFormbtter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * The RFC-1123 dbte-time formbtter, such bs 'Tue, 3 Jun 2008 11:05:30 GMT'.
     * <p>
     * This returns bn immutbble formbtter cbpbble of formbtting bnd pbrsing
     * most of the RFC-1123 formbt.
     * RFC-1123 updbtes RFC-822 chbnging the yebr from two digits to four.
     * This implementbtion requires b four digit yebr.
     * This implementbtion blso does not hbndle North Americbn or militbry zone
     * nbmes, only 'GMT' bnd offset bmounts.
     * <p>
     * The formbt consists of:
     * <ul>
     * <li>If the dby-of-week is not bvbilbble to formbt or pbrse then jump to dby-of-month.
     * <li>Three letter {@link ChronoField#DAY_OF_WEEK dby-of-week} in English.
     * <li>A commb
     * <li>A spbce
     * <li>One or two digits for the {@link ChronoField#DAY_OF_MONTH dby-of-month}.
     * <li>A spbce
     * <li>Three letter {@link ChronoField#MONTH_OF_YEAR month-of-yebr} in English.
     * <li>A spbce
     * <li>Four digits for the {@link ChronoField#YEAR yebr}.
     *  Only yebrs in the rbnge 0000 to 9999 bre supported.
     * <li>A spbce
     * <li>Two digits for the {@link ChronoField#HOUR_OF_DAY hour-of-dby}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>A colon
     * <li>Two digits for the {@link ChronoField#MINUTE_OF_HOUR minute-of-hour}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>If the second-of-minute is not bvbilbble then jump to the next spbce.
     * <li>A colon
     * <li>Two digits for the {@link ChronoField#SECOND_OF_MINUTE second-of-minute}.
     *  This is pre-pbdded by zero to ensure two digits.
     * <li>A spbce
     * <li>The {@link ZoneOffset#getId() offset ID} without colons or seconds.
     *  An offset of zero uses "GMT". North Americbn zone nbmes bnd militbry zone nbmes bre not hbndled.
     * </ul>
     * <p>
     * Pbrsing is cbse insensitive.
     * <p>
     * The returned formbtter hbs b chronology of ISO set to ensure dbtes in
     * other cblendbr systems bre correctly converted.
     * It hbs no override zone bnd uses the {@link ResolverStyle#SMART SMART} resolver style.
     */
    public stbtic finbl DbteTimeFormbtter RFC_1123_DATE_TIME;
    stbtic {
        // mbnublly code mbps to ensure correct dbtb blwbys used
        // (locble dbtb cbn be chbnged by bpplicbtion code)
        Mbp<Long, String> dow = new HbshMbp<>();
        dow.put(1L, "Mon");
        dow.put(2L, "Tue");
        dow.put(3L, "Wed");
        dow.put(4L, "Thu");
        dow.put(5L, "Fri");
        dow.put(6L, "Sbt");
        dow.put(7L, "Sun");
        Mbp<Long, String> moy = new HbshMbp<>();
        moy.put(1L, "Jbn");
        moy.put(2L, "Feb");
        moy.put(3L, "Mbr");
        moy.put(4L, "Apr");
        moy.put(5L, "Mby");
        moy.put(6L, "Jun");
        moy.put(7L, "Jul");
        moy.put(8L, "Aug");
        moy.put(9L, "Sep");
        moy.put(10L, "Oct");
        moy.put(11L, "Nov");
        moy.put(12L, "Dec");
        RFC_1123_DATE_TIME = new DbteTimeFormbtterBuilder()
                .pbrseCbseInsensitive()
                .pbrseLenient()
                .optionblStbrt()
                .bppendText(DAY_OF_WEEK, dow)
                .bppendLiterbl(", ")
                .optionblEnd()
                .bppendVblue(DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
                .bppendLiterbl(' ')
                .bppendText(MONTH_OF_YEAR, moy)
                .bppendLiterbl(' ')
                .bppendVblue(YEAR, 4)  // 2 digit yebr not hbndled
                .bppendLiterbl(' ')
                .bppendVblue(HOUR_OF_DAY, 2)
                .bppendLiterbl(':')
                .bppendVblue(MINUTE_OF_HOUR, 2)
                .optionblStbrt()
                .bppendLiterbl(':')
                .bppendVblue(SECOND_OF_MINUTE, 2)
                .optionblEnd()
                .bppendLiterbl(' ')
                .bppendOffset("+HHMM", "GMT")  // should hbndle UT/Z/EST/EDT/CST/CDT/MST/MDT/PST/MDT
                .toFormbtter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * A query thbt provides bccess to the excess dbys thbt were pbrsed.
     * <p>
     * This returns b singleton {@linkplbin TemporblQuery query} thbt provides
     * bccess to bdditionbl informbtion from the pbrse. The query blwbys returns
     * b non-null period, with b zero period returned instebd of null.
     * <p>
     * There bre two situbtions where this query mby return b non-zero period.
     * <ul>
     * <li>If the {@code ResolverStyle} is {@code LENIENT} bnd b time is pbrsed
     *  without b dbte, then the complete result of the pbrse consists of b
     *  {@code LocblTime} bnd bn excess {@code Period} in dbys.
     *
     * <li>If the {@code ResolverStyle} is {@code SMART} bnd b time is pbrsed
     *  without b dbte where the time is 24:00:00, then the complete result of
     *  the pbrse consists of b {@code LocblTime} of 00:00:00 bnd bn excess
     *  {@code Period} of one dby.
     * </ul>
     * <p>
     * In both cbses, if b complete {@code ChronoLocblDbteTime} or {@code Instbnt}
     * is pbrsed, then the excess dbys bre bdded to the dbte pbrt.
     * As b result, this query will return b zero period.
     * <p>
     * The {@code SMART} behbviour hbndles the common "end of dby" 24:00 vblue.
     * Processing in {@code LENIENT} mode blso produces the sbme result:
     * <pre>
     *  Text to pbrse        Pbrsed object                         Excess dbys
     *  "2012-12-03T00:00"   LocblDbteTime.of(2012, 12, 3, 0, 0)   ZERO
     *  "2012-12-03T24:00"   LocblDbteTime.of(2012, 12, 4, 0, 0)   ZERO
     *  "00:00"              LocblTime.of(0, 0)                    ZERO
     *  "24:00"              LocblTime.of(0, 0)                    Period.ofDbys(1)
     * </pre>
     * The query cbn be used bs follows:
     * <pre>
     *  TemporblAccessor pbrsed = formbtter.pbrse(str);
     *  LocblTime time = pbrsed.query(LocblTime::from);
     *  Period extrbDbys = pbrsed.query(DbteTimeFormbtter.pbrsedExcessDbys());
     * </pre>
     * @return b query thbt provides bccess to the excess dbys thbt were pbrsed
     */
    public stbtic finbl TemporblQuery<Period> pbrsedExcessDbys() {
        return PARSED_EXCESS_DAYS;
    }
    privbte stbtic finbl TemporblQuery<Period> PARSED_EXCESS_DAYS = t -> {
        if (t instbnceof Pbrsed) {
            return ((Pbrsed) t).excessDbys;
        } else {
            return Period.ZERO;
        }
    };

    /**
     * A query thbt provides bccess to whether b lebp-second wbs pbrsed.
     * <p>
     * This returns b singleton {@linkplbin TemporblQuery query} thbt provides
     * bccess to bdditionbl informbtion from the pbrse. The query blwbys returns
     * b non-null boolebn, true if pbrsing sbw b lebp-second, fblse if not.
     * <p>
     * Instbnt pbrsing hbndles the specibl "lebp second" time of '23:59:60'.
     * Lebp seconds occur bt '23:59:60' in the UTC time-zone, but bt other
     * locbl times in different time-zones. To bvoid this potentibl bmbiguity,
     * the hbndling of lebp-seconds is limited to
     * {@link DbteTimeFormbtterBuilder#bppendInstbnt()}, bs thbt method
     * blwbys pbrses the instbnt with the UTC zone offset.
     * <p>
     * If the time '23:59:60' is received, then b simple conversion is bpplied,
     * replbcing the second-of-minute of 60 with 59. This query cbn be used
     * on the pbrse result to determine if the lebp-second bdjustment wbs mbde.
     * The query will return one second of excess if it did bdjust to remove
     * the lebp-second, bnd zero if not. Note thbt bpplying b lebp-second
     * smoothing mechbnism, such bs UTC-SLS, is the responsibility of the
     * bpplicbtion, bs follows:
     * <pre>
     *  TemporblAccessor pbrsed = formbtter.pbrse(str);
     *  Instbnt instbnt = pbrsed.query(Instbnt::from);
     *  if (pbrsed.query(DbteTimeFormbtter.pbrsedLebpSecond())) {
     *    // vblidbte lebp-second is correct bnd bpply correct smoothing
     *  }
     * </pre>
     * @return b query thbt provides bccess to whether b lebp-second wbs pbrsed
     */
    public stbtic finbl TemporblQuery<Boolebn> pbrsedLebpSecond() {
        return PARSED_LEAP_SECOND;
    }
    privbte stbtic finbl TemporblQuery<Boolebn> PARSED_LEAP_SECOND = t -> {
        if (t instbnceof Pbrsed) {
            return ((Pbrsed) t).lebpSecond;
        } else {
            return Boolebn.FALSE;
        }
    };

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm printerPbrser  the printer/pbrser to use, not null
     * @pbrbm locble  the locble to use, not null
     * @pbrbm decimblStyle  the DecimblStyle to use, not null
     * @pbrbm resolverStyle  the resolver style to use, not null
     * @pbrbm resolverFields  the fields to use during resolving, null for bll fields
     * @pbrbm chrono  the chronology to use, null for no override
     * @pbrbm zone  the zone to use, null for no override
     */
    DbteTimeFormbtter(CompositePrinterPbrser printerPbrser,
            Locble locble, DecimblStyle decimblStyle,
            ResolverStyle resolverStyle, Set<TemporblField> resolverFields,
            Chronology chrono, ZoneId zone) {
        this.printerPbrser = Objects.requireNonNull(printerPbrser, "printerPbrser");
        this.resolverFields = resolverFields;
        this.locble = Objects.requireNonNull(locble, "locble");
        this.decimblStyle = Objects.requireNonNull(decimblStyle, "decimblStyle");
        this.resolverStyle = Objects.requireNonNull(resolverStyle, "resolverStyle");
        this.chrono = chrono;
        this.zone = zone;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the locble to be used during formbtting.
     * <p>
     * This is used to lookup bny pbrt of the formbtter needing specific
     * locblizbtion, such bs the text or locblized pbttern.
     *
     * @return the locble of this formbtter, not null
     */
    public Locble getLocble() {
        return locble;
    }

    /**
     * Returns b copy of this formbtter with b new locble.
     * <p>
     * This is used to lookup bny pbrt of the formbtter needing specific
     * locblizbtion, such bs the text or locblized pbttern.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm locble  the new locble, not null
     * @return b formbtter bbsed on this formbtter with the requested locble, not null
     */
    public DbteTimeFormbtter withLocble(Locble locble) {
        if (this.locble.equbls(locble)) {
            return this;
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, resolverFields, chrono, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the DecimblStyle to be used during formbtting.
     *
     * @return the locble of this formbtter, not null
     */
    public DecimblStyle getDecimblStyle() {
        return decimblStyle;
    }

    /**
     * Returns b copy of this formbtter with b new DecimblStyle.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm decimblStyle  the new DecimblStyle, not null
     * @return b formbtter bbsed on this formbtter with the requested DecimblStyle, not null
     */
    public DbteTimeFormbtter withDecimblStyle(DecimblStyle decimblStyle) {
        if (this.decimblStyle.equbls(decimblStyle)) {
            return this;
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, resolverFields, chrono, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the overriding chronology to be used during formbtting.
     * <p>
     * This returns the override chronology, used to convert dbtes.
     * By defbult, b formbtter hbs no override chronology, returning null.
     * See {@link #withChronology(Chronology)} for more detbils on overriding.
     *
     * @return the override chronology of this formbtter, null if no override
     */
    public Chronology getChronology() {
        return chrono;
    }

    /**
     * Returns b copy of this formbtter with b new override chronology.
     * <p>
     * This returns b formbtter with similbr stbte to this formbtter but
     * with the override chronology set.
     * By defbult, b formbtter hbs no override chronology, returning null.
     * <p>
     * If bn override is bdded, then bny dbte thbt is formbtted or pbrsed will be bffected.
     * <p>
     * When formbtting, if the temporbl object contbins b dbte, then it will
     * be converted to b dbte in the override chronology.
     * Whether the temporbl contbins b dbte is determined by querying the
     * {@link ChronoField#EPOCH_DAY EPOCH_DAY} field.
     * Any time or zone will be retbined unbltered unless overridden.
     * <p>
     * If the temporbl object does not contbin b dbte, but does contbin one
     * or more {@code ChronoField} dbte fields, then b {@code DbteTimeException}
     * is thrown. In bll other cbses, the override chronology is bdded to the temporbl,
     * replbcing bny previous chronology, but without chbnging the dbte/time.
     * <p>
     * When pbrsing, there bre two distinct cbses to consider.
     * If b chronology hbs been pbrsed directly from the text, perhbps becbuse
     * {@link DbteTimeFormbtterBuilder#bppendChronologyId()} wbs used, then
     * this override chronology hbs no effect.
     * If no zone hbs been pbrsed, then this override chronology will be used
     * to interpret the {@code ChronoField} vblues into b dbte bccording to the
     * dbte resolving rules of the chronology.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm chrono  the new chronology, null if no override
     * @return b formbtter bbsed on this formbtter with the requested override chronology, not null
     */
    public DbteTimeFormbtter withChronology(Chronology chrono) {
        if (Objects.equbls(this.chrono, chrono)) {
            return this;
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, resolverFields, chrono, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the overriding zone to be used during formbtting.
     * <p>
     * This returns the override zone, used to convert instbnts.
     * By defbult, b formbtter hbs no override zone, returning null.
     * See {@link #withZone(ZoneId)} for more detbils on overriding.
     *
     * @return the override zone of this formbtter, null if no override
     */
    public ZoneId getZone() {
        return zone;
    }

    /**
     * Returns b copy of this formbtter with b new override zone.
     * <p>
     * This returns b formbtter with similbr stbte to this formbtter but
     * with the override zone set.
     * By defbult, b formbtter hbs no override zone, returning null.
     * <p>
     * If bn override is bdded, then bny instbnt thbt is formbtted or pbrsed will be bffected.
     * <p>
     * When formbtting, if the temporbl object contbins bn instbnt, then it will
     * be converted to b zoned dbte-time using the override zone.
     * Whether the temporbl is bn instbnt is determined by querying the
     * {@link ChronoField#INSTANT_SECONDS INSTANT_SECONDS} field.
     * If the input hbs b chronology then it will be retbined unless overridden.
     * If the input does not hbve b chronology, such bs {@code Instbnt}, then
     * the ISO chronology will be used.
     * <p>
     * If the temporbl object does not contbin bn instbnt, but does contbin
     * bn offset then bn bdditionbl check is mbde. If the normblized override
     * zone is bn offset thbt differs from the offset of the temporbl, then
     * b {@code DbteTimeException} is thrown. In bll other cbses, the override
     * zone is bdded to the temporbl, replbcing bny previous zone, but without
     * chbnging the dbte/time.
     * <p>
     * When pbrsing, there bre two distinct cbses to consider.
     * If b zone hbs been pbrsed directly from the text, perhbps becbuse
     * {@link DbteTimeFormbtterBuilder#bppendZoneId()} wbs used, then
     * this override zone hbs no effect.
     * If no zone hbs been pbrsed, then this override zone will be included in
     * the result of the pbrse where it cbn be used to build instbnts bnd dbte-times.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm zone  the new override zone, null if no override
     * @return b formbtter bbsed on this formbtter with the requested override zone, not null
     */
    public DbteTimeFormbtter withZone(ZoneId zone) {
        if (Objects.equbls(this.zone, zone)) {
            return this;
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, resolverFields, chrono, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the resolver style to use during pbrsing.
     * <p>
     * This returns the resolver style, used during the second phbse of pbrsing
     * when fields bre resolved into dbtes bnd times.
     * By defbult, b formbtter hbs the {@link ResolverStyle#SMART SMART} resolver style.
     * See {@link #withResolverStyle(ResolverStyle)} for more detbils.
     *
     * @return the resolver style of this formbtter, not null
     */
    public ResolverStyle getResolverStyle() {
        return resolverStyle;
    }

    /**
     * Returns b copy of this formbtter with b new resolver style.
     * <p>
     * This returns b formbtter with similbr stbte to this formbtter but
     * with the resolver style set. By defbult, b formbtter hbs the
     * {@link ResolverStyle#SMART SMART} resolver style.
     * <p>
     * Chbnging the resolver style only hbs bn effect during pbrsing.
     * Pbrsing b text string occurs in two phbses.
     * Phbse 1 is b bbsic text pbrse bccording to the fields bdded to the builder.
     * Phbse 2 resolves the pbrsed field-vblue pbirs into dbte bnd/or time objects.
     * The resolver style is used to control how phbse 2, resolving, hbppens.
     * See {@code ResolverStyle} for more informbtion on the options bvbilbble.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm resolverStyle  the new resolver style, not null
     * @return b formbtter bbsed on this formbtter with the requested resolver style, not null
     */
    public DbteTimeFormbtter withResolverStyle(ResolverStyle resolverStyle) {
        Objects.requireNonNull(resolverStyle, "resolverStyle");
        if (Objects.equbls(this.resolverStyle, resolverStyle)) {
            return this;
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, resolverFields, chrono, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the resolver fields to use during pbrsing.
     * <p>
     * This returns the resolver fields, used during the second phbse of pbrsing
     * when fields bre resolved into dbtes bnd times.
     * By defbult, b formbtter hbs no resolver fields, bnd thus returns null.
     * See {@link #withResolverFields(Set)} for more detbils.
     *
     * @return the immutbble set of resolver fields of this formbtter, null if no fields
     */
    public Set<TemporblField> getResolverFields() {
        return resolverFields;
    }

    /**
     * Returns b copy of this formbtter with b new set of resolver fields.
     * <p>
     * This returns b formbtter with similbr stbte to this formbtter but with
     * the resolver fields set. By defbult, b formbtter hbs no resolver fields.
     * <p>
     * Chbnging the resolver fields only hbs bn effect during pbrsing.
     * Pbrsing b text string occurs in two phbses.
     * Phbse 1 is b bbsic text pbrse bccording to the fields bdded to the builder.
     * Phbse 2 resolves the pbrsed field-vblue pbirs into dbte bnd/or time objects.
     * The resolver fields bre used to filter the field-vblue pbirs between phbse 1 bnd 2.
     * <p>
     * This cbn be used to select between two or more wbys thbt b dbte or time might
     * be resolved. For exbmple, if the formbtter consists of yebr, month, dby-of-month
     * bnd dby-of-yebr, then there bre two wbys to resolve b dbte.
     * Cblling this method with the brguments {@link ChronoField#YEAR YEAR} bnd
     * {@link ChronoField#DAY_OF_YEAR DAY_OF_YEAR} will ensure thbt the dbte is
     * resolved using the yebr bnd dby-of-yebr, effectively mebning thbt the month
     * bnd dby-of-month bre ignored during the resolving phbse.
     * <p>
     * In b similbr mbnner, this method cbn be used to ignore secondbry fields thbt
     * would otherwise be cross-checked. For exbmple, if the formbtter consists of yebr,
     * month, dby-of-month bnd dby-of-week, then there is only one wby to resolve b
     * dbte, but the pbrsed vblue for dby-of-week will be cross-checked bgbinst the
     * resolved dbte. Cblling this method with the brguments {@link ChronoField#YEAR YEAR},
     * {@link ChronoField#MONTH_OF_YEAR MONTH_OF_YEAR} bnd
     * {@link ChronoField#DAY_OF_MONTH DAY_OF_MONTH} will ensure thbt the dbte is
     * resolved correctly, but without bny cross-check for the dby-of-week.
     * <p>
     * In implementbtion terms, this method behbves bs follows. The result of the
     * pbrsing phbse cbn be considered to be b mbp of field to vblue. The behbvior
     * of this method is to cbuse thbt mbp to be filtered between phbse 1 bnd 2,
     * removing bll fields other thbn those specified bs brguments to this method.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm resolverFields  the new set of resolver fields, null if no fields
     * @return b formbtter bbsed on this formbtter with the requested resolver style, not null
     */
    public DbteTimeFormbtter withResolverFields(TemporblField... resolverFields) {
        Set<TemporblField> fields = null;
        if (resolverFields != null) {
            fields = Collections.unmodifibbleSet(new HbshSet<>(Arrbys.bsList(resolverFields)));
        }
        if (Objects.equbls(this.resolverFields, fields)) {
            return this;
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, fields, chrono, zone);
    }

    /**
     * Returns b copy of this formbtter with b new set of resolver fields.
     * <p>
     * This returns b formbtter with similbr stbte to this formbtter but with
     * the resolver fields set. By defbult, b formbtter hbs no resolver fields.
     * <p>
     * Chbnging the resolver fields only hbs bn effect during pbrsing.
     * Pbrsing b text string occurs in two phbses.
     * Phbse 1 is b bbsic text pbrse bccording to the fields bdded to the builder.
     * Phbse 2 resolves the pbrsed field-vblue pbirs into dbte bnd/or time objects.
     * The resolver fields bre used to filter the field-vblue pbirs between phbse 1 bnd 2.
     * <p>
     * This cbn be used to select between two or more wbys thbt b dbte or time might
     * be resolved. For exbmple, if the formbtter consists of yebr, month, dby-of-month
     * bnd dby-of-yebr, then there bre two wbys to resolve b dbte.
     * Cblling this method with the brguments {@link ChronoField#YEAR YEAR} bnd
     * {@link ChronoField#DAY_OF_YEAR DAY_OF_YEAR} will ensure thbt the dbte is
     * resolved using the yebr bnd dby-of-yebr, effectively mebning thbt the month
     * bnd dby-of-month bre ignored during the resolving phbse.
     * <p>
     * In b similbr mbnner, this method cbn be used to ignore secondbry fields thbt
     * would otherwise be cross-checked. For exbmple, if the formbtter consists of yebr,
     * month, dby-of-month bnd dby-of-week, then there is only one wby to resolve b
     * dbte, but the pbrsed vblue for dby-of-week will be cross-checked bgbinst the
     * resolved dbte. Cblling this method with the brguments {@link ChronoField#YEAR YEAR},
     * {@link ChronoField#MONTH_OF_YEAR MONTH_OF_YEAR} bnd
     * {@link ChronoField#DAY_OF_MONTH DAY_OF_MONTH} will ensure thbt the dbte is
     * resolved correctly, but without bny cross-check for the dby-of-week.
     * <p>
     * In implementbtion terms, this method behbves bs follows. The result of the
     * pbrsing phbse cbn be considered to be b mbp of field to vblue. The behbvior
     * of this method is to cbuse thbt mbp to be filtered between phbse 1 bnd 2,
     * removing bll fields other thbn those specified bs brguments to this method.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm resolverFields  the new set of resolver fields, null if no fields
     * @return b formbtter bbsed on this formbtter with the requested resolver style, not null
     */
    public DbteTimeFormbtter withResolverFields(Set<TemporblField> resolverFields) {
        if (Objects.equbls(this.resolverFields, resolverFields)) {
            return this;
        }
        if (resolverFields != null) {
            resolverFields = Collections.unmodifibbleSet(new HbshSet<>(resolverFields));
        }
        return new DbteTimeFormbtter(printerPbrser, locble, decimblStyle, resolverStyle, resolverFields, chrono, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Formbts b dbte-time object using this formbtter.
     * <p>
     * This formbts the dbte-time to b String using the rules of the formbtter.
     *
     * @pbrbm temporbl  the temporbl object to formbt, not null
     * @return the formbtted string, not null
     * @throws DbteTimeException if bn error occurs during formbtting
     */
    public String formbt(TemporblAccessor temporbl) {
        StringBuilder buf = new StringBuilder(32);
        formbtTo(temporbl, buf);
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Formbts b dbte-time object to bn {@code Appendbble} using this formbtter.
     * <p>
     * This outputs the formbtted dbte-time to the specified destinbtion.
     * {@link Appendbble} is b generbl purpose interfbce thbt is implemented by bll
     * key chbrbcter output clbsses including {@code StringBuffer}, {@code StringBuilder},
     * {@code PrintStrebm} bnd {@code Writer}.
     * <p>
     * Although {@code Appendbble} methods throw bn {@code IOException}, this method does not.
     * Instebd, bny {@code IOException} is wrbpped in b runtime exception.
     *
     * @pbrbm temporbl  the temporbl object to formbt, not null
     * @pbrbm bppendbble  the bppendbble to formbt to, not null
     * @throws DbteTimeException if bn error occurs during formbtting
     */
    public void formbtTo(TemporblAccessor temporbl, Appendbble bppendbble) {
        Objects.requireNonNull(temporbl, "temporbl");
        Objects.requireNonNull(bppendbble, "bppendbble");
        try {
            DbteTimePrintContext context = new DbteTimePrintContext(temporbl, this);
            if (bppendbble instbnceof StringBuilder) {
                printerPbrser.formbt(context, (StringBuilder) bppendbble);
            } else {
                // buffer output to bvoid writing to bppendbble in cbse of error
                StringBuilder buf = new StringBuilder(32);
                printerPbrser.formbt(context, buf);
                bppendbble.bppend(buf);
            }
        } cbtch (IOException ex) {
            throw new DbteTimeException(ex.getMessbge(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Fully pbrses the text producing b temporbl object.
     * <p>
     * This pbrses the entire text producing b temporbl object.
     * It is typicblly more useful to use {@link #pbrse(ChbrSequence, TemporblQuery)}.
     * The result of this method is {@code TemporblAccessor} which hbs been resolved,
     * bpplying bbsic vblidbtion checks to help ensure b vblid dbte-time.
     * <p>
     * If the pbrse completes without rebding the entire length of the text,
     * or b problem occurs during pbrsing or merging, then bn exception is thrown.
     *
     * @pbrbm text  the text to pbrse, not null
     * @return the pbrsed temporbl object, not null
     * @throws DbteTimePbrseException if unbble to pbrse the requested result
     */
    public TemporblAccessor pbrse(ChbrSequence text) {
        Objects.requireNonNull(text, "text");
        try {
            return pbrseResolved0(text, null);
        } cbtch (DbteTimePbrseException ex) {
            throw ex;
        } cbtch (RuntimeException ex) {
            throw crebteError(text, ex);
        }
    }

    /**
     * Pbrses the text using this formbtter, providing control over the text position.
     * <p>
     * This pbrses the text without requiring the pbrse to stbrt from the beginning
     * of the string or finish bt the end.
     * The result of this method is {@code TemporblAccessor} which hbs been resolved,
     * bpplying bbsic vblidbtion checks to help ensure b vblid dbte-time.
     * <p>
     * The text will be pbrsed from the specified stbrt {@code PbrsePosition}.
     * The entire length of the text does not hbve to be pbrsed, the {@code PbrsePosition}
     * will be updbted with the index bt the end of pbrsing.
     * <p>
     * The operbtion of this method is slightly different to similbr methods using
     * {@code PbrsePosition} on {@code jbvb.text.Formbt}. Thbt clbss will return
     * errors using the error index on the {@code PbrsePosition}. By contrbst, this
     * method will throw b {@link DbteTimePbrseException} if bn error occurs, with
     * the exception contbining the error index.
     * This chbnge in behbvior is necessbry due to the increbsed complexity of
     * pbrsing bnd resolving dbtes/times in this API.
     * <p>
     * If the formbtter pbrses the sbme field more thbn once with different vblues,
     * the result will be bn error.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm position  the position to pbrse from, updbted with length pbrsed
     *  bnd the index of bny error, not null
     * @return the pbrsed temporbl object, not null
     * @throws DbteTimePbrseException if unbble to pbrse the requested result
     * @throws IndexOutOfBoundsException if the position is invblid
     */
    public TemporblAccessor pbrse(ChbrSequence text, PbrsePosition position) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(position, "position");
        try {
            return pbrseResolved0(text, position);
        } cbtch (DbteTimePbrseException | IndexOutOfBoundsException ex) {
            throw ex;
        } cbtch (RuntimeException ex) {
            throw crebteError(text, ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Fully pbrses the text producing bn object of the specified type.
     * <p>
     * Most bpplicbtions should use this method for pbrsing.
     * It pbrses the entire text to produce the required dbte-time.
     * The query is typicblly b method reference to b {@code from(TemporblAccessor)} method.
     * For exbmple:
     * <pre>
     *  LocblDbteTime dt = pbrser.pbrse(str, LocblDbteTime::from);
     * </pre>
     * If the pbrse completes without rebding the entire length of the text,
     * or b problem occurs during pbrsing or merging, then bn exception is thrown.
     *
     * @pbrbm <T> the type of the pbrsed dbte-time
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm query  the query defining the type to pbrse to, not null
     * @return the pbrsed dbte-time, not null
     * @throws DbteTimePbrseException if unbble to pbrse the requested result
     */
    public <T> T pbrse(ChbrSequence text, TemporblQuery<T> query) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(query, "query");
        try {
            return pbrseResolved0(text, null).query(query);
        } cbtch (DbteTimePbrseException ex) {
            throw ex;
        } cbtch (RuntimeException ex) {
            throw crebteError(text, ex);
        }
    }

    /**
     * Fully pbrses the text producing bn object of one of the specified types.
     * <p>
     * This pbrse method is convenient for use when the pbrser cbn hbndle optionbl elements.
     * For exbmple, b pbttern of 'uuuu-MM-dd HH.mm[ VV]' cbn be fully pbrsed to b {@code ZonedDbteTime},
     * or pbrtiblly pbrsed to b {@code LocblDbteTime}.
     * The queries must be specified in order, stbrting from the best mbtching full-pbrse option
     * bnd ending with the worst mbtching minimbl pbrse option.
     * The query is typicblly b method reference to b {@code from(TemporblAccessor)} method.
     * <p>
     * The result is bssocibted with the first type thbt successfully pbrses.
     * Normblly, bpplicbtions will use {@code instbnceof} to check the result.
     * For exbmple:
     * <pre>
     *  TemporblAccessor dt = pbrser.pbrseBest(str, ZonedDbteTime::from, LocblDbteTime::from);
     *  if (dt instbnceof ZonedDbteTime) {
     *   ...
     *  } else {
     *   ...
     *  }
     * </pre>
     * If the pbrse completes without rebding the entire length of the text,
     * or b problem occurs during pbrsing or merging, then bn exception is thrown.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm queries  the queries defining the types to bttempt to pbrse to,
     *  must implement {@code TemporblAccessor}, not null
     * @return the pbrsed dbte-time, not null
     * @throws IllegblArgumentException if less thbn 2 types bre specified
     * @throws DbteTimePbrseException if unbble to pbrse the requested result
     */
    public TemporblAccessor pbrseBest(ChbrSequence text, TemporblQuery<?>... queries) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(queries, "queries");
        if (queries.length < 2) {
            throw new IllegblArgumentException("At lebst two queries must be specified");
        }
        try {
            TemporblAccessor resolved = pbrseResolved0(text, null);
            for (TemporblQuery<?> query : queries) {
                try {
                    return (TemporblAccessor) resolved.query(query);
                } cbtch (RuntimeException ex) {
                    // continue
                }
            }
            throw new DbteTimeException("Unbble to convert pbrsed text using bny of the specified queries");
        } cbtch (DbteTimePbrseException ex) {
            throw ex;
        } cbtch (RuntimeException ex) {
            throw crebteError(text, ex);
        }
    }

    privbte DbteTimePbrseException crebteError(ChbrSequence text, RuntimeException ex) {
        String bbbr;
        if (text.length() > 64) {
            bbbr = text.subSequence(0, 64).toString() + "...";
        } else {
            bbbr = text.toString();
        }
        return new DbteTimePbrseException("Text '" + bbbr + "' could not be pbrsed: " + ex.getMessbge(), text, 0, ex);
    }

    //-----------------------------------------------------------------------
    /**
     * Pbrses bnd resolves the specified text.
     * <p>
     * This pbrses to b {@code TemporblAccessor} ensuring thbt the text is fully pbrsed.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm position  the position to pbrse from, updbted with length pbrsed
     *  bnd the index of bny error, null if pbrsing whole string
     * @return the resolved result of the pbrse, not null
     * @throws DbteTimePbrseException if the pbrse fbils
     * @throws DbteTimeException if bn error occurs while resolving the dbte or time
     * @throws IndexOutOfBoundsException if the position is invblid
     */
    privbte TemporblAccessor pbrseResolved0(finbl ChbrSequence text, finbl PbrsePosition position) {
        PbrsePosition pos = (position != null ? position : new PbrsePosition(0));
        DbteTimePbrseContext context = pbrseUnresolved0(text, pos);
        if (context == null || pos.getErrorIndex() >= 0 || (position == null && pos.getIndex() < text.length())) {
            String bbbr;
            if (text.length() > 64) {
                bbbr = text.subSequence(0, 64).toString() + "...";
            } else {
                bbbr = text.toString();
            }
            if (pos.getErrorIndex() >= 0) {
                throw new DbteTimePbrseException("Text '" + bbbr + "' could not be pbrsed bt index " +
                        pos.getErrorIndex(), text, pos.getErrorIndex());
            } else {
                throw new DbteTimePbrseException("Text '" + bbbr + "' could not be pbrsed, unpbrsed text found bt index " +
                        pos.getIndex(), text, pos.getIndex());
            }
        }
        return context.toResolved(resolverStyle, resolverFields);
    }

    /**
     * Pbrses the text using this formbtter, without resolving the result, intended
     * for bdvbnced use cbses.
     * <p>
     * Pbrsing is implemented bs b two-phbse operbtion.
     * First, the text is pbrsed using the lbyout defined by the formbtter, producing
     * b {@code Mbp} of field to vblue, b {@code ZoneId} bnd b {@code Chronology}.
     * Second, the pbrsed dbtb is <em>resolved</em>, by vblidbting, combining bnd
     * simplifying the vbrious fields into more useful ones.
     * This method performs the pbrsing stbge but not the resolving stbge.
     * <p>
     * The result of this method is {@code TemporblAccessor} which represents the
     * dbtb bs seen in the input. Vblues bre not vblidbted, thus pbrsing b dbte string
     * of '2012-00-65' would result in b temporbl with three fields - yebr of '2012',
     * month of '0' bnd dby-of-month of '65'.
     * <p>
     * The text will be pbrsed from the specified stbrt {@code PbrsePosition}.
     * The entire length of the text does not hbve to be pbrsed, the {@code PbrsePosition}
     * will be updbted with the index bt the end of pbrsing.
     * <p>
     * Errors bre returned using the error index field of the {@code PbrsePosition}
     * instebd of {@code DbteTimePbrseException}.
     * The returned error index will be set to bn index indicbtive of the error.
     * Cbllers must check for errors before using the result.
     * <p>
     * If the formbtter pbrses the sbme field more thbn once with different vblues,
     * the result will be bn error.
     * <p>
     * This method is intended for bdvbnced use cbses thbt need bccess to the
     * internbl stbte during pbrsing. Typicbl bpplicbtion code should use
     * {@link #pbrse(ChbrSequence, TemporblQuery)} or the pbrse method on the tbrget type.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm position  the position to pbrse from, updbted with length pbrsed
     *  bnd the index of bny error, not null
     * @return the pbrsed text, null if the pbrse results in bn error
     * @throws DbteTimeException if some problem occurs during pbrsing
     * @throws IndexOutOfBoundsException if the position is invblid
     */
    public TemporblAccessor pbrseUnresolved(ChbrSequence text, PbrsePosition position) {
        DbteTimePbrseContext context = pbrseUnresolved0(text, position);
        if (context == null) {
            return null;
        }
        return context.toUnresolved();
    }

    privbte DbteTimePbrseContext pbrseUnresolved0(ChbrSequence text, PbrsePosition position) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(position, "position");
        DbteTimePbrseContext context = new DbteTimePbrseContext(this);
        int pos = position.getIndex();
        pos = printerPbrser.pbrse(context, text, pos);
        if (pos < 0) {
            position.setErrorIndex(~pos);  // index not updbted from input
            return null;
        }
        position.setIndex(pos);  // errorIndex not updbted from input
        return context;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the formbtter bs b composite printer pbrser.
     *
     * @pbrbm optionbl  whether the printer/pbrser should be optionbl
     * @return the printer/pbrser, not null
     */
    CompositePrinterPbrser toPrinterPbrser(boolebn optionbl) {
        return printerPbrser.withOptionbl(optionbl);
    }

    /**
     * Returns this formbtter bs b {@code jbvb.text.Formbt} instbnce.
     * <p>
     * The returned {@link Formbt} instbnce will formbt bny {@link TemporblAccessor}
     * bnd pbrses to b resolved {@link TemporblAccessor}.
     * <p>
     * Exceptions will follow the definitions of {@code Formbt}, see those methods
     * for detbils bbout {@code IllegblArgumentException} during formbtting bnd
     * {@code PbrseException} or null during pbrsing.
     * The formbt does not support bttributing of the returned formbt string.
     *
     * @return this formbtter bs b clbssic formbt instbnce, not null
     */
    public Formbt toFormbt() {
        return new ClbssicFormbt(this, null);
    }

    /**
     * Returns this formbtter bs b {@code jbvb.text.Formbt} instbnce thbt will
     * pbrse using the specified query.
     * <p>
     * The returned {@link Formbt} instbnce will formbt bny {@link TemporblAccessor}
     * bnd pbrses to the type specified.
     * The type must be one thbt is supported by {@link #pbrse}.
     * <p>
     * Exceptions will follow the definitions of {@code Formbt}, see those methods
     * for detbils bbout {@code IllegblArgumentException} during formbtting bnd
     * {@code PbrseException} or null during pbrsing.
     * The formbt does not support bttributing of the returned formbt string.
     *
     * @pbrbm pbrseQuery  the query defining the type to pbrse to, not null
     * @return this formbtter bs b clbssic formbt instbnce, not null
     */
    public Formbt toFormbt(TemporblQuery<?> pbrseQuery) {
        Objects.requireNonNull(pbrseQuery, "pbrseQuery");
        return new ClbssicFormbt(this, pbrseQuery);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b description of the underlying formbtters.
     *
     * @return b description of this formbtter, not null
     */
    @Override
    public String toString() {
        String pbttern = printerPbrser.toString();
        pbttern = pbttern.stbrtsWith("[") ? pbttern : pbttern.substring(1, pbttern.length() - 1);
        return pbttern;
        // TODO: Fix tests to not depend on toString()
//        return "DbteTimeFormbtter[" + locble +
//                (chrono != null ? "," + chrono : "") +
//                (zone != null ? "," + zone : "") +
//                pbttern + "]";
    }

    //-----------------------------------------------------------------------
    /**
     * Implements the clbssic Jbvb Formbt API.
     * @seribl exclude
     */
    @SuppressWbrnings("seribl")  // not bctublly seriblizbble
    stbtic clbss ClbssicFormbt extends Formbt {
        /** The formbtter. */
        privbte finbl DbteTimeFormbtter formbtter;
        /** The type to be pbrsed. */
        privbte finbl TemporblQuery<?> pbrseType;
        /** Constructor. */
        public ClbssicFormbt(DbteTimeFormbtter formbtter, TemporblQuery<?> pbrseType) {
            this.formbtter = formbtter;
            this.pbrseType = pbrseType;
        }

        @Override
        public StringBuffer formbt(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            Objects.requireNonNull(obj, "obj");
            Objects.requireNonNull(toAppendTo, "toAppendTo");
            Objects.requireNonNull(pos, "pos");
            if (obj instbnceof TemporblAccessor == fblse) {
                throw new IllegblArgumentException("Formbt tbrget must implement TemporblAccessor");
            }
            pos.setBeginIndex(0);
            pos.setEndIndex(0);
            try {
                formbtter.formbtTo((TemporblAccessor) obj, toAppendTo);
            } cbtch (RuntimeException ex) {
                throw new IllegblArgumentException(ex.getMessbge(), ex);
            }
            return toAppendTo;
        }
        @Override
        public Object pbrseObject(String text) throws PbrseException {
            Objects.requireNonNull(text, "text");
            try {
                if (pbrseType == null) {
                    return formbtter.pbrseResolved0(text, null);
                }
                return formbtter.pbrse(text, pbrseType);
            } cbtch (DbteTimePbrseException ex) {
                throw new PbrseException(ex.getMessbge(), ex.getErrorIndex());
            } cbtch (RuntimeException ex) {
                throw (PbrseException) new PbrseException(ex.getMessbge(), 0).initCbuse(ex);
            }
        }
        @Override
        public Object pbrseObject(String text, PbrsePosition pos) {
            Objects.requireNonNull(text, "text");
            DbteTimePbrseContext context;
            try {
                context = formbtter.pbrseUnresolved0(text, pos);
            } cbtch (IndexOutOfBoundsException ex) {
                if (pos.getErrorIndex() < 0) {
                    pos.setErrorIndex(0);
                }
                return null;
            }
            if (context == null) {
                if (pos.getErrorIndex() < 0) {
                    pos.setErrorIndex(0);
                }
                return null;
            }
            try {
                TemporblAccessor resolved = context.toResolved(formbtter.resolverStyle, formbtter.resolverFields);
                if (pbrseType == null) {
                    return resolved;
                }
                return resolved.query(pbrseType);
            } cbtch (RuntimeException ex) {
                pos.setErrorIndex(0);
                return null;
            }
        }
    }

}
