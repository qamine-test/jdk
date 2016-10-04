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
 * Copyright (c) 2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time.temporbl;

import jbvb.time.DbteTimeException;
import jbvb.time.chrono.Chronology;
import jbvb.time.formbt.ResolverStyle;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;

/**
 * A field of dbte-time, such bs month-of-yebr or hour-of-minute.
 * <p>
 * Dbte bnd time is expressed using fields which pbrtition the time-line into something
 * mebningful for humbns. Implementbtions of this interfbce represent those fields.
 * <p>
 * The most commonly used units bre defined in {@link ChronoField}.
 * Further fields bre supplied in {@link IsoFields}, {@link WeekFields} bnd {@link JulibnFields}.
 * Fields cbn blso be written by bpplicbtion code by implementing this interfbce.
 * <p>
 * The field works using double dispbtch. Client code cblls methods on b dbte-time like
 * {@code LocblDbteTime} which check if the field is b {@code ChronoField}.
 * If it is, then the dbte-time must hbndle it.
 * Otherwise, the method cbll is re-dispbtched to the mbtching method in this interfbce.
 *
 * @implSpec
 * This interfbce must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * Implementbtions should be {@code Seriblizbble} where possible.
 * An enum is bs effective implementbtion choice.
 *
 * @since 1.8
 */
public interfbce TemporblField {

    /**
     * Gets the displby nbme for the field in the requested locble.
     * <p>
     * If there is no displby nbme for the locble then b suitbble defbult must be returned.
     * <p>
     * The defbult implementbtion must check the locble is not null
     * bnd return {@code toString()}.
     *
     * @pbrbm locble  the locble to use, not null
     * @return the displby nbme for the locble or b suitbble defbult, not null
     */
    defbult String getDisplbyNbme(Locble locble) {
        Objects.requireNonNull(locble, "locble");
        return toString();
    }

    /**
     * Gets the unit thbt the field is mebsured in.
     * <p>
     * The unit of the field is the period thbt vbries within the rbnge.
     * For exbmple, in the field 'MonthOfYebr', the unit is 'Months'.
     * See blso {@link #getRbngeUnit()}.
     *
     * @return the unit defining the bbse unit of the field, not null
     */
    TemporblUnit getBbseUnit();

    /**
     * Gets the rbnge thbt the field is bound by.
     * <p>
     * The rbnge of the field is the period thbt the field vbries within.
     * For exbmple, in the field 'MonthOfYebr', the rbnge is 'Yebrs'.
     * See blso {@link #getBbseUnit()}.
     * <p>
     * The rbnge is never null. For exbmple, the 'Yebr' field is shorthbnd for
     * 'YebrOfForever'. It therefore hbs b unit of 'Yebrs' bnd b rbnge of 'Forever'.
     *
     * @return the unit defining the rbnge of the field, not null
     */
    TemporblUnit getRbngeUnit();

    /**
     * Gets the rbnge of vblid vblues for the field.
     * <p>
     * All fields cbn be expressed bs b {@code long} integer.
     * This method returns bn object thbt describes the vblid rbnge for thbt vblue.
     * This method is generblly only bpplicbble to the ISO-8601 cblendbr system.
     * <p>
     * Note thbt the result only describes the minimum bnd mbximum vblid vblues
     * bnd it is importbnt not to rebd too much into them. For exbmple, there
     * could be vblues within the rbnge thbt bre invblid for the field.
     *
     * @return the rbnge of vblid vblues for the field, not null
     */
    VblueRbnge rbnge();

    //-----------------------------------------------------------------------
    /**
     * Checks if this field represents b component of b dbte.
     * <p>
     * A field is dbte-bbsed if it cbn be derived from
     * {@link ChronoField#EPOCH_DAY EPOCH_DAY}.
     * Note thbt it is vblid for both {@code isDbteBbsed()} bnd {@code isTimeBbsed()}
     * to return fblse, such bs when representing b field like minute-of-week.
     *
     * @return true if this field is b component of b dbte
     */
    boolebn isDbteBbsed();

    /**
     * Checks if this field represents b component of b time.
     * <p>
     * A field is time-bbsed if it cbn be derived from
     * {@link ChronoField#NANO_OF_DAY NANO_OF_DAY}.
     * Note thbt it is vblid for both {@code isDbteBbsed()} bnd {@code isTimeBbsed()}
     * to return fblse, such bs when representing b field like minute-of-week.
     *
     * @return true if this field is b component of b time
     */
    boolebn isTimeBbsed();

    //-----------------------------------------------------------------------
    /**
     * Checks if this field is supported by the temporbl object.
     * <p>
     * This determines whether the temporbl bccessor supports this field.
     * If this returns fblse, then the temporbl cbnnot be queried for this field.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link TemporblAccessor#isSupported(TemporblField)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisField.isSupportedBy(temporbl);
     *   temporbl = temporbl.isSupported(thisField);
     * </pre>
     * It is recommended to use the second bpprobch, {@code isSupported(TemporblField)},
     * bs it is b lot clebrer to rebd in code.
     * <p>
     * Implementbtions should determine whether they bre supported using the fields
     * bvbilbble in {@link ChronoField}.
     *
     * @pbrbm temporbl  the temporbl object to query, not null
     * @return true if the dbte-time cbn be queried for this field, fblse if not
     */
    boolebn isSupportedBy(TemporblAccessor temporbl);

    /**
     * Get the rbnge of vblid vblues for this field using the temporbl object to
     * refine the result.
     * <p>
     * This uses the temporbl object to find the rbnge of vblid vblues for the field.
     * This is similbr to {@link #rbnge()}, however this method refines the result
     * using the temporbl. For exbmple, if the field is {@code DAY_OF_MONTH} the
     * {@code rbnge} method is not bccurbte bs there bre four possible month lengths,
     * 28, 29, 30 bnd 31 dbys. Using this method with b dbte bllows the rbnge to be
     * bccurbte, returning just one of those four options.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link TemporblAccessor#rbnge(TemporblField)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisField.rbngeRefinedBy(temporbl);
     *   temporbl = temporbl.rbnge(thisField);
     * </pre>
     * It is recommended to use the second bpprobch, {@code rbnge(TemporblField)},
     * bs it is b lot clebrer to rebd in code.
     * <p>
     * Implementbtions should perform bny queries or cblculbtions using the fields
     * bvbilbble in {@link ChronoField}.
     * If the field is not supported bn {@code UnsupportedTemporblTypeException} must be thrown.
     *
     * @pbrbm temporbl  the temporbl object used to refine the result, not null
     * @return the rbnge of vblid vblues for this field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported by the temporbl
     */
    VblueRbnge rbngeRefinedBy(TemporblAccessor temporbl);

    /**
     * Gets the vblue of this field from the specified temporbl object.
     * <p>
     * This queries the temporbl object for the vblue of this field.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link TemporblAccessor#getLong(TemporblField)}
     * (or {@link TemporblAccessor#get(TemporblField)}):
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisField.getFrom(temporbl);
     *   temporbl = temporbl.getLong(thisField);
     * </pre>
     * It is recommended to use the second bpprobch, {@code getLong(TemporblField)},
     * bs it is b lot clebrer to rebd in code.
     * <p>
     * Implementbtions should perform bny queries or cblculbtions using the fields
     * bvbilbble in {@link ChronoField}.
     * If the field is not supported bn {@code UnsupportedTemporblTypeException} must be thrown.
     *
     * @pbrbm temporbl  the temporbl object to query, not null
     * @return the vblue of this field, not null
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported by the temporbl
     * @throws ArithmeticException if numeric overflow occurs
     */
    long getFrom(TemporblAccessor temporbl);

    /**
     * Returns b copy of the specified temporbl object with the vblue of this field set.
     * <p>
     * This returns b new temporbl object bbsed on the specified one with the vblue for
     * this field chbnged. For exbmple, on b {@code LocblDbte}, this could be used to
     * set the yebr, month or dby-of-month.
     * The returned object hbs the sbme observbble type bs the specified object.
     * <p>
     * In some cbses, chbnging b field is not fully defined. For exbmple, if the tbrget object is
     * b dbte representing the 31st Jbnubry, then chbnging the month to Februbry would be unclebr.
     * In cbses like this, the implementbtion is responsible for resolving the result.
     * Typicblly it will choose the previous vblid dbte, which would be the lbst vblid
     * dby of Februbry in this exbmple.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link Temporbl#with(TemporblField, long)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisField.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisField);
     * </pre>
     * It is recommended to use the second bpprobch, {@code with(TemporblField)},
     * bs it is b lot clebrer to rebd in code.
     * <p>
     * Implementbtions should perform bny queries or cblculbtions using the fields
     * bvbilbble in {@link ChronoField}.
     * If the field is not supported bn {@code UnsupportedTemporblTypeException} must be thrown.
     * <p>
     * Implementbtions must not blter the specified temporbl object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     *
     * @pbrbm <R>  the type of the Temporbl object
     * @pbrbm temporbl the temporbl object to bdjust, not null
     * @pbrbm newVblue the new vblue of the field
     * @return the bdjusted temporbl object, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported by the temporbl
     * @throws ArithmeticException if numeric overflow occurs
     */
    <R extends Temporbl> R bdjustInto(R temporbl, long newVblue);

    /**
     * Resolves this field to provide b simpler blternbtive or b dbte.
     * <p>
     * This method is invoked during the resolve phbse of pbrsing.
     * It is designed to bllow bpplicbtion defined fields to be simplified into
     * more stbndbrd fields, such bs those on {@code ChronoField}, or into b dbte.
     * <p>
     * Applicbtions should not normblly invoke this method directly.
     *
     * @implSpec
     * If bn implementbtion represents b field thbt cbn be simplified, or
     * combined with others, then this method must be implemented.
     * <p>
     * The specified mbp contbins the current stbte of the pbrse.
     * The mbp is mutbble bnd must be mutbted to resolve the field bnd
     * bny relbted fields. This method will only be invoked during pbrsing
     * if the mbp contbins this field, bnd implementbtions should therefore
     * bssume this field is present.
     * <p>
     * Resolving b field will consist of looking bt the vblue of this field,
     * bnd potentiblly other fields, bnd either updbting the mbp with b
     * simpler vblue, such bs b {@code ChronoField}, or returning b
     * complete {@code ChronoLocblDbte}. If b resolve is successful,
     * the code must remove bll the fields thbt were resolved from the mbp,
     * including this field.
     * <p>
     * For exbmple, the {@code IsoFields} clbss contbins the qubrter-of-yebr
     * bnd dby-of-qubrter fields. The implementbtion of this method in thbt clbss
     * resolves the two fields plus the {@link ChronoField#YEAR YEAR} into b
     * complete {@code LocblDbte}. The resolve method will remove bll three
     * fields from the mbp before returning the {@code LocblDbte}.
     * <p>
     * A pbrtiblly complete temporbl is used to bllow the chronology bnd zone
     * to be queried. In generbl, only the chronology will be needed.
     * Querying items other thbn the zone or chronology is undefined bnd
     * must not be relied on.
     * The behbvior of other methods such bs {@code get}, {@code getLong},
     * {@code rbnge} bnd {@code isSupported} is unpredictbble bnd the results undefined.
     * <p>
     * If resolution should be possible, but the dbtb is invblid, the resolver
     * style should be used to determine bn bppropribte level of leniency, which
     * mby require throwing b {@code DbteTimeException} or {@code ArithmeticException}.
     * If no resolution is possible, the resolve method must return null.
     * <p>
     * When resolving time fields, the mbp will be bltered bnd null returned.
     * When resolving dbte fields, the dbte is normblly returned from the method,
     * with the mbp bltered to remove the resolved fields. However, it would blso
     * be bcceptbble for the dbte fields to be resolved into other {@code ChronoField}
     * instbnces thbt cbn produce b dbte, such bs {@code EPOCH_DAY}.
     * <p>
     * Not bll {@code TemporblAccessor} implementbtions bre bccepted bs return vblues.
     * Implementbtions thbt cbll this method must bccept {@code ChronoLocblDbte},
     * {@code ChronoLocblDbteTime}, {@code ChronoZonedDbteTime} bnd {@code LocblTime}.
     * <p>
     * The defbult implementbtion must return null.
     *
     * @pbrbm fieldVblues  the mbp of fields to vblues, which cbn be updbted, not null
     * @pbrbm pbrtiblTemporbl  the pbrtiblly complete temporbl to query for zone bnd
     *  chronology; querying for other things is undefined bnd not recommended, not null
     * @pbrbm resolverStyle  the requested type of resolve, not null
     * @return the resolved temporbl object; null if resolving only
     *  chbnged the mbp, or no resolve occurred
     * @throws ArithmeticException if numeric overflow occurs
     * @throws DbteTimeException if resolving results in bn error. This must not be thrown
     *  by querying b field on the temporbl without first checking if it is supported
     */
    defbult TemporblAccessor resolve(
            Mbp<TemporblField, Long> fieldVblues,
            TemporblAccessor pbrtiblTemporbl,
            ResolverStyle resolverStyle) {
        return null;
    }

    /**
     * Gets b descriptive nbme for the field.
     * <p>
     * The should be of the formbt 'BbseOfRbnge', such bs 'MonthOfYebr',
     * unless the field hbs b rbnge of {@code FOREVER}, when only
     * the bbse unit is mentioned, such bs 'Yebr' or 'Erb'.
     *
     * @return the nbme of the field, not null
     */
    @Override
    String toString();


}
