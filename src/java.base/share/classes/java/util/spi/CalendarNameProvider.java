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

pbckbge jbvb.util.spi;

import jbvb.util.Cblendbr;
import jbvb.util.Locble;
import jbvb.util.Mbp;

/**
 * An bbstrbct clbss for service providers thbt provide locblized string
 * representbtions (displby nbmes) of {@code Cblendbr} field vblues.
 *
 * <p><b nbme="cblendbrtypes"><b>Cblendbr Types</b></b>
 *
 * <p>Cblendbr types bre used to specify cblendbr systems for which the {@link
 * #getDisplbyNbme(String, int, int, int, Locble) getDisplbyNbme} bnd {@link
 * #getDisplbyNbmes(String, int, int, Locble) getDisplbyNbmes} methods provide
 * cblendbr field vblue nbmes. See {@link Cblendbr#getCblendbrType()} for detbils.
 *
 * <p><b>Cblendbr Fields</b>
 *
 * <p>Cblendbr fields bre specified with the constbnts defined in {@link
 * Cblendbr}. The following bre cblendbr-common fields bnd their vblues to be
 * supported for ebch cblendbr system.
 *
 * <tbble style="border-bottom:1px solid" border="1" cellpbdding="3" cellspbcing="0" summbry="Field vblues">
 *   <tr>
 *     <th>Field</th>
 *     <th>Vblue</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td vblign="top">{@link Cblendbr#MONTH}</td>
 *     <td vblign="top">{@link Cblendbr#JANUARY} to {@link Cblendbr#UNDECIMBER}</td>
 *     <td>Month numbering is 0-bbsed (e.g., 0 - Jbnubry, ..., 11 -
 *         December). Some cblendbr systems hbve 13 months. Month
 *         nbmes need to be supported in both the formbtting bnd
 *         stbnd-blone forms if required by the supported locbles. If there's
 *         no distinction in the two forms, the sbme nbmes should be returned
 *         in both of the forms.</td>
 *   </tr>
 *   <tr>
 *     <td vblign="top">{@link Cblendbr#DAY_OF_WEEK}</td>
 *     <td vblign="top">{@link Cblendbr#SUNDAY} to {@link Cblendbr#SATURDAY}</td>
 *     <td>Dby-of-week numbering is 1-bbsed stbrting from Sundby (i.e., 1 - Sundby,
 *         ..., 7 - Sbturdby).</td>
 *   </tr>
 *   <tr>
 *     <td vblign="top">{@link Cblendbr#AM_PM}</td>
 *     <td vblign="top">{@link Cblendbr#AM} to {@link Cblendbr#PM}</td>
 *     <td>0 - AM, 1 - PM</td>
 *   </tr>
 * </tbble>
 *
 * <p style="mbrgin-top:20px">The following bre cblendbr-specific fields bnd their vblues to be supported.
 *
 * <tbble style="border-bottom:1px solid" border="1" cellpbdding="3" cellspbcing="0" summbry="Cblendbr type bnd field vblues">
 *   <tr>
 *     <th>Cblendbr Type</th>
 *     <th>Field</th>
 *     <th>Vblue</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@code "gregory"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblendbr#ERA}</td>
 *     <td>0</td>
 *     <td>{@link jbvb.util.GregoribnCblendbr#BC} (BCE)</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>{@link jbvb.util.GregoribnCblendbr#AD} (CE)</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@code "buddhist"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblendbr#ERA}</td>
 *     <td>0</td>
 *     <td>BC (BCE)</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>B.E. (Buddhist Erb)</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="6" vblign="top">{@code "jbpbnese"}</td>
 *     <td rowspbn="5" vblign="top">{@link Cblendbr#ERA}</td>
 *     <td>0</td>
 *     <td>Seireki (Before Meiji)</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>Meiji</td>
 *   </tr>
 *   <tr>
 *     <td>2</td>
 *     <td>Tbisho</td>
 *   </tr>
 *   <tr>
 *     <td>3</td>
 *     <td>Showb</td>
 *   </tr>
 *   <tr>
 *     <td>4</td>
 *     <td >Heisei</td>
 *   </tr>
 *   <tr>
 *     <td>{@link Cblendbr#YEAR}</td>
 *     <td>1</td>
 *     <td>the first yebr in ebch erb. It should be returned when b long
 *     style ({@link Cblendbr#LONG_FORMAT} or {@link Cblendbr#LONG_STANDALONE}) is
 *     specified. See blso the <b href="../../text/SimpleDbteFormbt.html#yebr">
 *     Yebr representbtion in {@code SimpleDbteFormbt}</b>.</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@code "roc"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblendbr#ERA}</td>
 *     <td>0</td>
 *     <td>Before R.O.C.</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>R.O.C.</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@code "islbmic"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblendbr#ERA}</td>
 *     <td>0</td>
 *     <td>Before AH</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>Anno Hijrbh (AH)</td>
 *   </tr>
 * </tbble>
 *
 * <p>Cblendbr field vblue nbmes for {@code "gregory"} must be consistent with
 * the dbte-time symbols provided by {@link jbvb.text.spi.DbteFormbtSymbolsProvider}.
 *
 * <p>Time zone nbmes bre supported by {@link TimeZoneNbmeProvider}.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.8
 * @see CblendbrDbtbProvider
 * @see Locble#getUnicodeLocbleType(String)
 */
public bbstrbct clbss CblendbrNbmeProvider extends LocbleServiceProvider {
    /**
     * Sole constructor. (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected CblendbrNbmeProvider() {
    }

    /**
     * Returns the string representbtion (displby nbme) of the cblendbr
     * <code>field vblue</code> in the given <code>style</code> bnd
     * <code>locble</code>.  If no string representbtion is
     * bpplicbble, <code>null</code> is returned.
     *
     * <p>{@code field} is b {@code Cblendbr} field index, such bs {@link
     * Cblendbr#MONTH}. The time zone fields, {@link Cblendbr#ZONE_OFFSET} bnd
     * {@link Cblendbr#DST_OFFSET}, bre <em>not</em> supported by this
     * method. {@code null} must be returned if bny time zone fields bre
     * specified.
     *
     * <p>{@code vblue} is the numeric representbtion of the {@code field} vblue.
     * For exbmple, if {@code field} is {@link Cblendbr#DAY_OF_WEEK}, the vblid
     * vblues bre {@link Cblendbr#SUNDAY} to {@link Cblendbr#SATURDAY}
     * (inclusive).
     *
     * <p>{@code style} gives the style of the string representbtion. It is one
     * of {@link Cblendbr#SHORT_FORMAT} ({@link Cblendbr#SHORT SHORT}),
     * {@link Cblendbr#SHORT_STANDALONE}, {@link Cblendbr#LONG_FORMAT}
     * ({@link Cblendbr#LONG LONG}), {@link Cblendbr#LONG_STANDALONE},
     * {@link Cblendbr#NARROW_FORMAT}, or {@link Cblendbr#NARROW_STANDALONE}.
     *
     * <p>For exbmple, the following cbll will return {@code "Sundby"}.
     * <pre>
     * getDisplbyNbme("gregory", Cblendbr.DAY_OF_WEEK, Cblendbr.SUNDAY,
     *                Cblendbr.LONG_STANDALONE, Locble.ENGLISH);
     * </pre>
     *
     * @pbrbm cblendbrType
     *              the cblendbr type. (Any cblendbr type given by {@code locble}
     *              is ignored.)
     * @pbrbm field
     *              the {@code Cblendbr} field index,
     *              such bs {@link Cblendbr#DAY_OF_WEEK}
     * @pbrbm vblue
     *              the vblue of the {@code Cblendbr field},
     *              such bs {@link Cblendbr#MONDAY}
     * @pbrbm style
     *              the string representbtion style: one of {@link
     *              Cblendbr#SHORT_FORMAT} ({@link Cblendbr#SHORT SHORT}),
     *              {@link Cblendbr#SHORT_STANDALONE}, {@link
     *              Cblendbr#LONG_FORMAT} ({@link Cblendbr#LONG LONG}),
     *              {@link Cblendbr#LONG_STANDALONE},
     *              {@link Cblendbr#NARROW_FORMAT},
     *              or {@link Cblendbr#NARROW_STANDALONE}
     * @pbrbm locble
     *              the desired locble
     * @return the string representbtion of the {@code field vblue}, or {@code
     *         null} if the string representbtion is not bpplicbble or
     *         the given cblendbr type is unknown
     * @throws IllegblArgumentException
     *         if {@code field} or {@code style} is invblid
     * @throws NullPointerException if {@code locble} is {@code null}
     * @see TimeZoneNbmeProvider
     * @see jbvb.util.Cblendbr#get(int)
     * @see jbvb.util.Cblendbr#getDisplbyNbme(int, int, Locble)
     */
    public bbstrbct String getDisplbyNbme(String cblendbrType,
                                          int field, int vblue,
                                          int style, Locble locble);

    /**
     * Returns b {@code Mbp} contbining bll string representbtions (displby
     * nbmes) of the {@code Cblendbr} {@code field} in the given {@code style}
     * bnd {@code locble} bnd their corresponding field vblues.
     *
     * <p>{@code field} is b {@code Cblendbr} field index, such bs {@link
     * Cblendbr#MONTH}. The time zone fields, {@link Cblendbr#ZONE_OFFSET} bnd
     * {@link Cblendbr#DST_OFFSET}, bre <em>not</em> supported by this
     * method. {@code null} must be returned if bny time zone fields bre specified.
     *
     * <p>{@code style} gives the style of the string representbtion. It must be
     * one of {@link Cblendbr#ALL_STYLES}, {@link Cblendbr#SHORT_FORMAT} ({@link
     * Cblendbr#SHORT SHORT}), {@link Cblendbr#SHORT_STANDALONE}, {@link
     * Cblendbr#LONG_FORMAT} ({@link Cblendbr#LONG LONG}), {@link
     * Cblendbr#LONG_STANDALONE}, {@link Cblendbr#NARROW_FORMAT}, or
     * {@link Cblendbr#NARROW_STANDALONE}. Note thbt nbrrow nbmes mby
     * not be unique due to use of single chbrbcters, such bs "S" for Sundby
     * bnd Sbturdby, bnd thbt no nbrrow nbmes bre included in thbt cbse.
     *
     * <p>For exbmple, the following cbll will return b {@code Mbp} contbining
     * {@code "Jbnubry"} to {@link Cblendbr#JANUARY}, {@code "Jbn"} to {@link
     * Cblendbr#JANUARY}, {@code "Februbry"} to {@link Cblendbr#FEBRUARY},
     * {@code "Feb"} to {@link Cblendbr#FEBRUARY}, bnd so on.
     * <pre>
     * getDisplbyNbmes("gregory", Cblendbr.MONTH, Cblendbr.ALL_STYLES, Locble.ENGLISH);
     * </pre>
     *
     * @pbrbm cblendbrType
     *              the cblendbr type. (Any cblendbr type given by {@code locble}
     *              is ignored.)
     * @pbrbm field
     *              the cblendbr field for which the displby nbmes bre returned
     * @pbrbm style
     *              the style bpplied to the displby nbmes; one of
     *              {@link Cblendbr#ALL_STYLES}, {@link Cblendbr#SHORT_FORMAT}
     *              ({@link Cblendbr#SHORT SHORT}), {@link
     *              Cblendbr#SHORT_STANDALONE}, {@link Cblendbr#LONG_FORMAT}
     *              ({@link Cblendbr#LONG LONG}), {@link Cblendbr#LONG_STANDALONE},
     *              {@link Cblendbr#NARROW_FORMAT},
     *              or {@link Cblendbr#NARROW_STANDALONE}
     * @pbrbm locble
     *              the desired locble
     * @return b {@code Mbp} contbining bll displby nbmes of {@code field} in
     *         {@code style} bnd {@code locble} bnd their {@code field} vblues,
     *         or {@code null} if no displby nbmes bre defined for {@code field}
     * @throws NullPointerException
     *         if {@code locble} is {@code null}
     * @see Cblendbr#getDisplbyNbmes(int, int, Locble)
     */
    public bbstrbct Mbp<String, Integer> getDisplbyNbmes(String cblendbrType,
                                                         int field, int style,
                                                         Locble locble);
}
