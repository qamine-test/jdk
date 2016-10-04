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
 * Copyright (c) 2007-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time;

import stbtic jbvb.time.LocblTime.NANOS_PER_SECOND;
import stbtic jbvb.time.LocblTime.SECONDS_PER_DAY;
import stbtic jbvb.time.LocblTime.SECONDS_PER_HOUR;
import stbtic jbvb.time.LocblTime.SECONDS_PER_MINUTE;
import stbtic jbvb.time.temporbl.ChronoField.INSTANT_SECONDS;
import stbtic jbvb.time.temporbl.ChronoField.MICRO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.MILLI_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.NANOS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.formbt.DbteTimeFormbtter;
import jbvb.time.formbt.DbteTimePbrseException;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Objects;

/**
 * An instbntbneous point on the time-line.
 * <p>
 * This clbss models b single instbntbneous point on the time-line.
 * This might be used to record event time-stbmps in the bpplicbtion.
 * <p>
 * The rbnge of bn instbnt requires the storbge of b number lbrger thbn b {@code long}.
 * To bchieve this, the clbss stores b {@code long} representing epoch-seconds bnd bn
 * {@code int} representing nbnosecond-of-second, which will blwbys be between 0 bnd 999,999,999.
 * The epoch-seconds bre mebsured from the stbndbrd Jbvb epoch of {@code 1970-01-01T00:00:00Z}
 * where instbnts bfter the epoch hbve positive vblues, bnd ebrlier instbnts hbve negbtive vblues.
 * For both the epoch-second bnd nbnosecond pbrts, b lbrger vblue is blwbys lbter on the time-line
 * thbn b smbller vblue.
 *
 * <h3>Time-scble</h3>
 * <p>
 * The length of the solbr dby is the stbndbrd wby thbt humbns mebsure time.
 * This hbs trbditionblly been subdivided into 24 hours of 60 minutes of 60 seconds,
 * forming b 86400 second dby.
 * <p>
 * Modern timekeeping is bbsed on btomic clocks which precisely define bn SI second
 * relbtive to the trbnsitions of b Cbesium btom. The length of bn SI second wbs defined
 * to be very close to the 86400th frbction of b dby.
 * <p>
 * Unfortunbtely, bs the Ebrth rotbtes the length of the dby vbries.
 * In bddition, over time the bverbge length of the dby is getting longer bs the Ebrth slows.
 * As b result, the length of b solbr dby in 2012 is slightly longer thbn 86400 SI seconds.
 * The bctubl length of bny given dby bnd the bmount by which the Ebrth is slowing
 * bre not predictbble bnd cbn only be determined by mebsurement.
 * The UT1 time-scble cbptures the bccurbte length of dby, but is only bvbilbble some
 * time bfter the dby hbs completed.
 * <p>
 * The UTC time-scble is b stbndbrd bpprobch to bundle up bll the bdditionbl frbctions
 * of b second from UT1 into whole seconds, known bs <i>lebp-seconds</i>.
 * A lebp-second mby be bdded or removed depending on the Ebrth's rotbtionbl chbnges.
 * As such, UTC permits b dby to hbve 86399 SI seconds or 86401 SI seconds where
 * necessbry in order to keep the dby bligned with the Sun.
 * <p>
 * The modern UTC time-scble wbs introduced in 1972, introducing the concept of whole lebp-seconds.
 * Between 1958 bnd 1972, the definition of UTC wbs complex, with minor sub-second lebps bnd
 * blterbtions to the length of the notionbl second. As of 2012, discussions bre underwby
 * to chbnge the definition of UTC bgbin, with the potentibl to remove lebp seconds or
 * introduce other chbnges.
 * <p>
 * Given the complexity of bccurbte timekeeping described bbove, this Jbvb API defines
 * its own time-scble, the <i>Jbvb Time-Scble</i>.
 * <p>
 * The Jbvb Time-Scble divides ebch cblendbr dby into exbctly 86400
 * subdivisions, known bs seconds.  These seconds mby differ from the
 * SI second.  It closely mbtches the de fbcto internbtionbl civil time
 * scble, the definition of which chbnges from time to time.
 * <p>
 * The Jbvb Time-Scble hbs slightly different definitions for different
 * segments of the time-line, ebch bbsed on the consensus internbtionbl
 * time scble thbt is used bs the bbsis for civil time. Whenever the
 * internbtionblly-bgreed time scble is modified or replbced, b new
 * segment of the Jbvb Time-Scble must be defined for it.  Ebch segment
 * must meet these requirements:
 * <ul>
 * <li>the Jbvb Time-Scble shbll closely mbtch the underlying internbtionbl
 *  civil time scble;</li>
 * <li>the Jbvb Time-Scble shbll exbctly mbtch the internbtionbl civil
 *  time scble bt noon ebch dby;</li>
 * <li>the Jbvb Time-Scble shbll hbve b precisely-defined relbtionship to
 *  the internbtionbl civil time scble.</li>
 * </ul>
 * There bre currently, bs of 2013, two segments in the Jbvb time-scble.
 * <p>
 * For the segment from 1972-11-03 (exbct boundbry discussed below) until
 * further notice, the consensus internbtionbl time scble is UTC (with
 * lebp seconds).  In this segment, the Jbvb Time-Scble is identicbl to
 * <b href="http://www.cl.cbm.bc.uk/~mgk25/time/utc-sls/">UTC-SLS</b>.
 * This is identicbl to UTC on dbys thbt do not hbve b lebp second.
 * On dbys thbt do hbve b lebp second, the lebp second is sprebd equblly
 * over the lbst 1000 seconds of the dby, mbintbining the bppebrbnce of
 * exbctly 86400 seconds per dby.
 * <p>
 * For the segment prior to 1972-11-03, extending bbck brbitrbrily fbr,
 * the consensus internbtionbl time scble is defined to be UT1, bpplied
 * prolepticblly, which is equivblent to the (mebn) solbr time on the
 * prime meridibn (Greenwich). In this segment, the Jbvb Time-Scble is
 * identicbl to the consensus internbtionbl time scble. The exbct
 * boundbry between the two segments is the instbnt where UT1 = UTC
 * between 1972-11-03T00:00 bnd 1972-11-04T12:00.
 * <p>
 * Implementbtions of the Jbvb time-scble using the JSR-310 API bre not
 * required to provide bny clock thbt is sub-second bccurbte, or thbt
 * progresses monotonicblly or smoothly. Implementbtions bre therefore
 * not required to bctublly perform the UTC-SLS slew or to otherwise be
 * bwbre of lebp seconds. JSR-310 does, however, require thbt
 * implementbtions must document the bpprobch they use when defining b
 * clock representing the current instbnt.
 * See {@link Clock} for detbils on the bvbilbble clocks.
 * <p>
 * The Jbvb time-scble is used for bll dbte-time clbsses.
 * This includes {@code Instbnt}, {@code LocblDbte}, {@code LocblTime}, {@code OffsetDbteTime},
 * {@code ZonedDbteTime} bnd {@code Durbtion}.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code Instbnt} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss Instbnt
        implements Temporbl, TemporblAdjuster, Compbrbble<Instbnt>, Seriblizbble {

    /**
     * Constbnt for the 1970-01-01T00:00:00Z epoch instbnt.
     */
    public stbtic finbl Instbnt EPOCH = new Instbnt(0, 0);
    /**
     * The minimum supported epoch second.
     */
    privbte stbtic finbl long MIN_SECOND = -31557014167219200L;
    /**
     * The mbximum supported epoch second.
     */
    privbte stbtic finbl long MAX_SECOND = 31556889864403199L;
    /**
     * The minimum supported {@code Instbnt}, '-1000000000-01-01T00:00Z'.
     * This could be used by bn bpplicbtion bs b "fbr pbst" instbnt.
     * <p>
     * This is one yebr ebrlier thbn the minimum {@code LocblDbteTime}.
     * This provides sufficient vblues to hbndle the rbnge of {@code ZoneOffset}
     * which bffect the instbnt in bddition to the locbl dbte-time.
     * The vblue is blso chosen such thbt the vblue of the yebr fits in
     * bn {@code int}.
     */
    public stbtic finbl Instbnt MIN = Instbnt.ofEpochSecond(MIN_SECOND, 0);
    /**
     * The mbximum supported {@code Instbnt}, '1000000000-12-31T23:59:59.999999999Z'.
     * This could be used by bn bpplicbtion bs b "fbr future" instbnt.
     * <p>
     * This is one yebr lbter thbn the mbximum {@code LocblDbteTime}.
     * This provides sufficient vblues to hbndle the rbnge of {@code ZoneOffset}
     * which bffect the instbnt in bddition to the locbl dbte-time.
     * The vblue is blso chosen such thbt the vblue of the yebr fits in
     * bn {@code int}.
     */
    public stbtic finbl Instbnt MAX = Instbnt.ofEpochSecond(MAX_SECOND, 999_999_999);

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -665713676816604388L;

    /**
     * The number of seconds from the epoch of 1970-01-01T00:00:00Z.
     */
    privbte finbl long seconds;
    /**
     * The number of nbnoseconds, lbter blong the time-line, from the seconds field.
     * This is blwbys positive, bnd never exceeds 999,999,999.
     */
    privbte finbl int nbnos;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current instbnt from the system clock.
     * <p>
     * This will query the {@link Clock#systemUTC() system UTC clock} to
     * obtbin the current instbnt.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte time-source for
     * testing becbuse the clock is effectively hbrd-coded.
     *
     * @return the current instbnt using the system clock, not null
     */
    public stbtic Instbnt now() {
        return Clock.systemUTC().instbnt();
    }

    /**
     * Obtbins the current instbnt from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current time.
     * <p>
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current instbnt, not null
     */
    public stbtic Instbnt now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return clock.instbnt();
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Instbnt} using seconds from the
     * epoch of 1970-01-01T00:00:00Z.
     * <p>
     * The nbnosecond field is set to zero.
     *
     * @pbrbm epochSecond  the number of seconds from 1970-01-01T00:00:00Z
     * @return bn instbnt, not null
     * @throws DbteTimeException if the instbnt exceeds the mbximum or minimum instbnt
     */
    public stbtic Instbnt ofEpochSecond(long epochSecond) {
        return crebte(epochSecond, 0);
    }

    /**
     * Obtbins bn instbnce of {@code Instbnt} using seconds from the
     * epoch of 1970-01-01T00:00:00Z bnd nbnosecond frbction of second.
     * <p>
     * This method bllows bn brbitrbry number of nbnoseconds to be pbssed in.
     * The fbctory will blter the vblues of the second bnd nbnosecond in order
     * to ensure thbt the stored nbnosecond is in the rbnge 0 to 999,999,999.
     * For exbmple, the following will result in the exbctly the sbme instbnt:
     * <pre>
     *  Instbnt.ofEpochSecond(3, 1);
     *  Instbnt.ofEpochSecond(4, -999_999_999);
     *  Instbnt.ofEpochSecond(2, 1000_000_001);
     * </pre>
     *
     * @pbrbm epochSecond  the number of seconds from 1970-01-01T00:00:00Z
     * @pbrbm nbnoAdjustment  the nbnosecond bdjustment to the number of seconds, positive or negbtive
     * @return bn instbnt, not null
     * @throws DbteTimeException if the instbnt exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public stbtic Instbnt ofEpochSecond(long epochSecond, long nbnoAdjustment) {
        long secs = Mbth.bddExbct(epochSecond, Mbth.floorDiv(nbnoAdjustment, NANOS_PER_SECOND));
        int nos = (int)Mbth.floorMod(nbnoAdjustment, NANOS_PER_SECOND);
        return crebte(secs, nos);
    }

    /**
     * Obtbins bn instbnce of {@code Instbnt} using milliseconds from the
     * epoch of 1970-01-01T00:00:00Z.
     * <p>
     * The seconds bnd nbnoseconds bre extrbcted from the specified milliseconds.
     *
     * @pbrbm epochMilli  the number of milliseconds from 1970-01-01T00:00:00Z
     * @return bn instbnt, not null
     * @throws DbteTimeException if the instbnt exceeds the mbximum or minimum instbnt
     */
    public stbtic Instbnt ofEpochMilli(long epochMilli) {
        long secs = Mbth.floorDiv(epochMilli, 1000);
        int mos = (int)Mbth.floorMod(epochMilli, 1000);
        return crebte(secs, mos * 1000_000);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Instbnt} from b temporbl object.
     * <p>
     * This obtbins bn instbnt bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code Instbnt}.
     * <p>
     * The conversion extrbcts the {@link ChronoField#INSTANT_SECONDS INSTANT_SECONDS}
     * bnd {@link ChronoField#NANO_OF_SECOND NANO_OF_SECOND} fields.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code Instbnt::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the instbnt, not null
     * @throws DbteTimeException if unbble to convert to bn {@code Instbnt}
     */
    public stbtic Instbnt from(TemporblAccessor temporbl) {
        if (temporbl instbnceof Instbnt) {
            return (Instbnt) temporbl;
        }
        Objects.requireNonNull(temporbl, "temporbl");
        try {
            long instbntSecs = temporbl.getLong(INSTANT_SECONDS);
            int nbnoOfSecond = temporbl.get(NANO_OF_SECOND);
            return Instbnt.ofEpochSecond(instbntSecs, nbnoOfSecond);
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin Instbnt from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Instbnt} from b text string such bs
     * {@code 2007-12-03T10:15:30.00Z}.
     * <p>
     * The string must represent b vblid instbnt in UTC bnd is pbrsed using
     * {@link DbteTimeFormbtter#ISO_INSTANT}.
     *
     * @pbrbm text  the text to pbrse, not null
     * @return the pbrsed instbnt, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic Instbnt pbrse(finbl ChbrSequence text) {
        return DbteTimeFormbtter.ISO_INSTANT.pbrse(text, Instbnt::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Instbnt} using seconds bnd nbnoseconds.
     *
     * @pbrbm seconds  the length of the durbtion in seconds
     * @pbrbm nbnoOfSecond  the nbno-of-second, from 0 to 999,999,999
     * @throws DbteTimeException if the instbnt exceeds the mbximum or minimum instbnt
     */
    privbte stbtic Instbnt crebte(long seconds, int nbnoOfSecond) {
        if ((seconds | nbnoOfSecond) == 0) {
            return EPOCH;
        }
        if (seconds < MIN_SECOND || seconds > MAX_SECOND) {
            throw new DbteTimeException("Instbnt exceeds minimum or mbximum instbnt");
        }
        return new Instbnt(seconds, nbnoOfSecond);
    }

    /**
     * Constructs bn instbnce of {@code Instbnt} using seconds from the epoch of
     * 1970-01-01T00:00:00Z bnd nbnosecond frbction of second.
     *
     * @pbrbm epochSecond  the number of seconds from 1970-01-01T00:00:00Z
     * @pbrbm nbnos  the nbnoseconds within the second, must be positive
     */
    privbte Instbnt(long epochSecond, int nbnos) {
        super();
        this.seconds = epochSecond;
        this.nbnos = nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this instbnt cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
     * <li>{@code NANO_OF_SECOND}
     * <li>{@code MICRO_OF_SECOND}
     * <li>{@code MILLI_OF_SECOND}
     * <li>{@code INSTANT_SECONDS}
     * </ul>
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this instbnt, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == INSTANT_SECONDS || field == NANO_OF_SECOND || field == MICRO_OF_SECOND || field == MILLI_OF_SECOND;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this dbte-time.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * If the unit is b {@link ChronoUnit} then the query is implemented here.
     * The supported units bre:
     * <ul>
     * <li>{@code NANOS}
     * <li>{@code MICROS}
     * <li>{@code MILLIS}
     * <li>{@code SECONDS}
     * <li>{@code MINUTES}
     * <li>{@code HOURS}
     * <li>{@code HALF_DAYS}
     * <li>{@code DAYS}
     * </ul>
     * All other {@code ChronoUnit} instbnces will return fblse.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.isSupportedBy(Temporbl)}
     * pbssing {@code this} bs the brgument.
     * Whether the unit is supported is determined by the unit.
     *
     * @pbrbm unit  the unit to check, null returns fblse
     * @return true if the unit cbn be bdded/subtrbcted, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            return unit.isTimeBbsed() || unit == DAYS;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This instbnt is used to enhbnce the bccurbcy of the returned rbnge.
     * If it is not possible to return the rbnge, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return
     * bppropribte rbnge instbnces.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.rbngeRefinedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the rbnge cbn be obtbined is determined by the field.
     *
     * @pbrbm field  the field to query the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     */
    @Override  // override for Jbvbdoc
    public VblueRbnge rbnge(TemporblField field) {
        return Temporbl.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this instbnt bs bn {@code int}.
     * <p>
     * This queries this instbnt for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this dbte-time, except {@code INSTANT_SECONDS} which is too
     * lbrge to fit in bn {@code int} bnd throws b {@code DbteTimeException}.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined or
     *         the vblue is outside the rbnge of vblid vblues for the field
     * @throws UnsupportedTemporblTypeException if the field is not supported or
     *         the rbnge of vblues exceeds bn {@code int}
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override  // override for Jbvbdoc bnd performbnce
    public int get(TemporblField field) {
        if (field instbnceof ChronoField) {
            switch ((ChronoField) field) {
                cbse NANO_OF_SECOND: return nbnos;
                cbse MICRO_OF_SECOND: return nbnos / 1000;
                cbse MILLI_OF_SECOND: return nbnos / 1000_000;
                cbse INSTANT_SECONDS: INSTANT_SECONDS.checkVblidIntVblue(seconds);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return rbnge(field).checkVblidIntVblue(field.getFrom(this), field);
    }

    /**
     * Gets the vblue of the specified field from this instbnt bs b {@code long}.
     * <p>
     * This queries this instbnt for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this dbte-time.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long getLong(TemporblField field) {
        if (field instbnceof ChronoField) {
            switch ((ChronoField) field) {
                cbse NANO_OF_SECOND: return nbnos;
                cbse MICRO_OF_SECOND: return nbnos / 1000;
                cbse MILLI_OF_SECOND: return nbnos / 1000_000;
                cbse INSTANT_SECONDS: return seconds;
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of seconds from the Jbvb epoch of 1970-01-01T00:00:00Z.
     * <p>
     * The epoch second count is b simple incrementing count of seconds where
     * second 0 is 1970-01-01T00:00:00Z.
     * The nbnosecond pbrt of the dby is returned by {@code getNbnosOfSecond}.
     *
     * @return the seconds from the epoch of 1970-01-01T00:00:00Z
     */
    public long getEpochSecond() {
        return seconds;
    }

    /**
     * Gets the number of nbnoseconds, lbter blong the time-line, from the stbrt
     * of the second.
     * <p>
     * The nbnosecond-of-second vblue mebsures the totbl number of nbnoseconds from
     * the second returned by {@code getEpochSecond}.
     *
     * @return the nbnoseconds within the second, blwbys positive, never exceeds 999,999,999
     */
    public int getNbno() {
        return nbnos;
    }

    //-------------------------------------------------------------------------
    /**
     * Returns bn bdjusted copy of this instbnt.
     * <p>
     * This returns bn {@code Instbnt}, bbsed on this one, with the instbnt bdjusted.
     * The bdjustment tbkes plbce using the specified bdjuster strbtegy object.
     * Rebd the documentbtion of the bdjuster to understbnd whbt bdjustment will be mbde.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblAdjuster#bdjustInto(Temporbl)} method on the
     * specified bdjuster pbssing {@code this} bs the brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bdjuster the bdjuster to use, not null
     * @return bn {@code Instbnt} bbsed on {@code this} with the bdjustment mbde, not null
     * @throws DbteTimeException if the bdjustment cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Instbnt with(TemporblAdjuster bdjuster) {
        return (Instbnt) bdjuster.bdjustInto(this);
    }

    /**
     * Returns b copy of this instbnt with the specified field set to b new vblue.
     * <p>
     * This returns bn {@code Instbnt}, bbsed on this one, with the vblue
     * for the specified field chbnged.
     * If it is not possible to set the vblue, becbuse the field is not supported or for
     * some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the bdjustment is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code NANO_OF_SECOND} -
     *  Returns bn {@code Instbnt} with the specified nbno-of-second.
     *  The epoch-second will be unchbnged.
     * <li>{@code MICRO_OF_SECOND} -
     *  Returns bn {@code Instbnt} with the nbno-of-second replbced by the specified
     *  micro-of-second multiplied by 1,000. The epoch-second will be unchbnged.
     * <li>{@code MILLI_OF_SECOND} -
     *  Returns bn {@code Instbnt} with the nbno-of-second replbced by the specified
     *  milli-of-second multiplied by 1,000,000. The epoch-second will be unchbnged.
     * <li>{@code INSTANT_SECONDS} -
     *  Returns bn {@code Instbnt} with the specified epoch-second.
     *  The nbno-of-second will be unchbnged.
     * </ul>
     * <p>
     * In bll cbses, if the new vblue is outside the vblid rbnge of vblues for the field
     * then b {@code DbteTimeException} will be thrown.
     * <p>
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.bdjustInto(Temporbl, long)}
     * pbssing {@code this} bs the brgument. In this cbse, the field determines
     * whether bnd how to bdjust the instbnt.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm field  the field to set in the result, not null
     * @pbrbm newVblue  the new vblue of the field in the result
     * @return bn {@code Instbnt} bbsed on {@code this} with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Instbnt with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkVblidVblue(newVblue);
            switch (f) {
                cbse MILLI_OF_SECOND: {
                    int nvbl = (int) newVblue * 1000_000;
                    return (nvbl != nbnos ? crebte(seconds, nvbl) : this);
                }
                cbse MICRO_OF_SECOND: {
                    int nvbl = (int) newVblue * 1000;
                    return (nvbl != nbnos ? crebte(seconds, nvbl) : this);
                }
                cbse NANO_OF_SECOND: return (newVblue != nbnos ? crebte(seconds, (int) newVblue) : this);
                cbse INSTANT_SECONDS: return (newVblue != seconds ? crebte(newVblue, nbnos) : this);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.bdjustInto(this, newVblue);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code Instbnt} truncbted to the specified unit.
     * <p>
     * Truncbting the instbnt returns b copy of the originbl with fields
     * smbller thbn the specified unit set to zero.
     * The fields bre cblculbted on the bbsis of using b UTC offset bs seen
     * in {@code toString}.
     * For exbmple, truncbting with the {@link ChronoUnit#MINUTES MINUTES} unit will
     * round down to the nebrest minute, setting the seconds bnd nbnoseconds to zero.
     * <p>
     * The unit must hbve b {@linkplbin TemporblUnit#getDurbtion() durbtion}
     * thbt divides into the length of b stbndbrd dby without rembinder.
     * This includes bll supplied time units on {@link ChronoUnit} bnd
     * {@link ChronoUnit#DAYS DAYS}. Other units throw bn exception.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm unit  the unit to truncbte to, not null
     * @return bn {@code Instbnt} bbsed on this instbnt with the time truncbted, not null
     * @throws DbteTimeException if the unit is invblid for truncbtion
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    public Instbnt truncbtedTo(TemporblUnit unit) {
        if (unit == ChronoUnit.NANOS) {
            return this;
        }
        Durbtion unitDur = unit.getDurbtion();
        if (unitDur.getSeconds() > LocblTime.SECONDS_PER_DAY) {
            throw new UnsupportedTemporblTypeException("Unit is too lbrge to be used for truncbtion");
        }
        long dur = unitDur.toNbnos();
        if ((LocblTime.NANOS_PER_DAY % dur) != 0) {
            throw new UnsupportedTemporblTypeException("Unit must divide into b stbndbrd dby without rembinder");
        }
        long nod = (seconds % LocblTime.SECONDS_PER_DAY) * LocblTime.NANOS_PER_SECOND + nbnos;
        long result = (nod / dur) * dur;
        return plusNbnos(result - nod);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this instbnt with the specified bmount bdded.
     * <p>
     * This returns bn {@code Instbnt}, bbsed on this one, with the specified bmount bdded.
     * The bmount is typicblly {@link Durbtion} but mby be bny other type implementing
     * the {@link TemporblAmount} interfbce.
     * <p>
     * The cblculbtion is delegbted to the bmount object by cblling
     * {@link TemporblAmount#bddTo(Temporbl)}. The bmount implementbtion is free
     * to implement the bddition in bny wby it wishes, however it typicblly
     * cblls bbck to {@link #plus(long, TemporblUnit)}. Consult the documentbtion
     * of the bmount implementbtion to determine if it cbn be successfully bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the bmount to bdd, not null
     * @return bn {@code Instbnt} bbsed on this instbnt with the bddition mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Instbnt plus(TemporblAmount bmountToAdd) {
        return (Instbnt) bmountToAdd.bddTo(this);
    }

    /**
     * Returns b copy of this instbnt with the specified bmount bdded.
     * <p>
     * This returns bn {@code Instbnt}, bbsed on this one, with the bmount
     * in terms of the unit bdded. If it is not possible to bdd the bmount, becbuse the
     * unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoUnit} then the bddition is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code NANOS} -
     *  Returns b {@code Instbnt} with the specified number of nbnoseconds bdded.
     *  This is equivblent to {@link #plusNbnos(long)}.
     * <li>{@code MICROS} -
     *  Returns b {@code Instbnt} with the specified number of microseconds bdded.
     *  This is equivblent to {@link #plusNbnos(long)} with the bmount
     *  multiplied by 1,000.
     * <li>{@code MILLIS} -
     *  Returns b {@code Instbnt} with the specified number of milliseconds bdded.
     *  This is equivblent to {@link #plusNbnos(long)} with the bmount
     *  multiplied by 1,000,000.
     * <li>{@code SECONDS} -
     *  Returns b {@code Instbnt} with the specified number of seconds bdded.
     *  This is equivblent to {@link #plusSeconds(long)}.
     * <li>{@code MINUTES} -
     *  Returns b {@code Instbnt} with the specified number of minutes bdded.
     *  This is equivblent to {@link #plusSeconds(long)} with the bmount
     *  multiplied by 60.
     * <li>{@code HOURS} -
     *  Returns b {@code Instbnt} with the specified number of hours bdded.
     *  This is equivblent to {@link #plusSeconds(long)} with the bmount
     *  multiplied by 3,600.
     * <li>{@code HALF_DAYS} -
     *  Returns b {@code Instbnt} with the specified number of hblf-dbys bdded.
     *  This is equivblent to {@link #plusSeconds(long)} with the bmount
     *  multiplied by 43,200 (12 hours).
     * <li>{@code DAYS} -
     *  Returns b {@code Instbnt} with the specified number of dbys bdded.
     *  This is equivblent to {@link #plusSeconds(long)} with the bmount
     *  multiplied by 86,400 (24 hours).
     * </ul>
     * <p>
     * All other {@code ChronoUnit} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.bddTo(Temporbl, long)}
     * pbssing {@code this} bs the brgument. In this cbse, the unit determines
     * whether bnd how to perform the bddition.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the bmount of the unit to bdd to the result, mby be negbtive
     * @pbrbm unit  the unit of the bmount to bdd, not null
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified bmount bdded, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Instbnt plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
                cbse NANOS: return plusNbnos(bmountToAdd);
                cbse MICROS: return plus(bmountToAdd / 1000_000, (bmountToAdd % 1000_000) * 1000);
                cbse MILLIS: return plusMillis(bmountToAdd);
                cbse SECONDS: return plusSeconds(bmountToAdd);
                cbse MINUTES: return plusSeconds(Mbth.multiplyExbct(bmountToAdd, SECONDS_PER_MINUTE));
                cbse HOURS: return plusSeconds(Mbth.multiplyExbct(bmountToAdd, SECONDS_PER_HOUR));
                cbse HALF_DAYS: return plusSeconds(Mbth.multiplyExbct(bmountToAdd, SECONDS_PER_DAY / 2));
                cbse DAYS: return plusSeconds(Mbth.multiplyExbct(bmountToAdd, SECONDS_PER_DAY));
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.bddTo(this, bmountToAdd);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this instbnt with the specified durbtion in seconds bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToAdd  the seconds to bdd, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified seconds bdded, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Instbnt plusSeconds(long secondsToAdd) {
        return plus(secondsToAdd, 0);
    }

    /**
     * Returns b copy of this instbnt with the specified durbtion in milliseconds bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm millisToAdd  the milliseconds to bdd, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified milliseconds bdded, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Instbnt plusMillis(long millisToAdd) {
        return plus(millisToAdd / 1000, (millisToAdd % 1000) * 1000_000);
    }

    /**
     * Returns b copy of this instbnt with the specified durbtion in nbnoseconds bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnosToAdd  the nbnoseconds to bdd, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified nbnoseconds bdded, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Instbnt plusNbnos(long nbnosToAdd) {
        return plus(0, nbnosToAdd);
    }

    /**
     * Returns b copy of this instbnt with the specified durbtion bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToAdd  the seconds to bdd, positive or negbtive
     * @pbrbm nbnosToAdd  the nbnos to bdd, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified seconds bdded, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    privbte Instbnt plus(long secondsToAdd, long nbnosToAdd) {
        if ((secondsToAdd | nbnosToAdd) == 0) {
            return this;
        }
        long epochSec = Mbth.bddExbct(seconds, secondsToAdd);
        epochSec = Mbth.bddExbct(epochSec, nbnosToAdd / NANOS_PER_SECOND);
        nbnosToAdd = nbnosToAdd % NANOS_PER_SECOND;
        long nbnoAdjustment = nbnos + nbnosToAdd;  // sbfe int+NANOS_PER_SECOND
        return ofEpochSecond(epochSec, nbnoAdjustment);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this instbnt with the specified bmount subtrbcted.
     * <p>
     * This returns bn {@code Instbnt}, bbsed on this one, with the specified bmount subtrbcted.
     * The bmount is typicblly {@link Durbtion} but mby be bny other type implementing
     * the {@link TemporblAmount} interfbce.
     * <p>
     * The cblculbtion is delegbted to the bmount object by cblling
     * {@link TemporblAmount#subtrbctFrom(Temporbl)}. The bmount implementbtion is free
     * to implement the subtrbction in bny wby it wishes, however it typicblly
     * cblls bbck to {@link #minus(long, TemporblUnit)}. Consult the documentbtion
     * of the bmount implementbtion to determine if it cbn be successfully subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the bmount to subtrbct, not null
     * @return bn {@code Instbnt} bbsed on this instbnt with the subtrbction mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Instbnt minus(TemporblAmount bmountToSubtrbct) {
        return (Instbnt) bmountToSubtrbct.subtrbctFrom(this);
    }

    /**
     * Returns b copy of this instbnt with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code Instbnt}, bbsed on this one, with the bmount
     * in terms of the unit subtrbcted. If it is not possible to subtrbct the bmount,
     * becbuse the unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * This method is equivblent to {@link #plus(long, TemporblUnit)} with the bmount negbted.
     * See thbt method for b full description of how bddition, bnd thus subtrbction, works.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the bmount of the unit to subtrbct from the result, mby be negbtive
     * @pbrbm unit  the unit of the bmount to subtrbct, not null
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified bmount subtrbcted, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Instbnt minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this instbnt with the specified durbtion in seconds subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToSubtrbct  the seconds to subtrbct, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified seconds subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Instbnt minusSeconds(long secondsToSubtrbct) {
        if (secondsToSubtrbct == Long.MIN_VALUE) {
            return plusSeconds(Long.MAX_VALUE).plusSeconds(1);
        }
        return plusSeconds(-secondsToSubtrbct);
    }

    /**
     * Returns b copy of this instbnt with the specified durbtion in milliseconds subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm millisToSubtrbct  the milliseconds to subtrbct, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified milliseconds subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Instbnt minusMillis(long millisToSubtrbct) {
        if (millisToSubtrbct == Long.MIN_VALUE) {
            return plusMillis(Long.MAX_VALUE).plusMillis(1);
        }
        return plusMillis(-millisToSubtrbct);
    }

    /**
     * Returns b copy of this instbnt with the specified durbtion in nbnoseconds subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnosToSubtrbct  the nbnoseconds to subtrbct, positive or negbtive
     * @return bn {@code Instbnt} bbsed on this instbnt with the specified nbnoseconds subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the mbximum or minimum instbnt
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Instbnt minusNbnos(long nbnosToSubtrbct) {
        if (nbnosToSubtrbct == Long.MIN_VALUE) {
            return plusNbnos(Long.MAX_VALUE).plusNbnos(1);
        }
        return plusNbnos(-nbnosToSubtrbct);
    }

    //-------------------------------------------------------------------------
    /**
     * Queries this instbnt using the specified query.
     * <p>
     * This queries this instbnt using the specified query strbtegy object.
     * The {@code TemporblQuery} object defines the logic to be used to
     * obtbin the result. Rebd the documentbtion of the query to understbnd
     * whbt the result of this method will be.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblQuery#queryFrom(TemporblAccessor)} method on the
     * specified query pbssing {@code this} bs the brgument.
     *
     * @pbrbm <R> the type of the result
     * @pbrbm query  the query to invoke, not null
     * @return the query result, null mby be returned (defined by the query)
     * @throws DbteTimeException if unbble to query (defined by the query)
     * @throws ArithmeticException if numeric overflow occurs (defined by the query)
     */
    @SuppressWbrnings("unchecked")
    @Override
    public <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.precision()) {
            return (R) NANOS;
        }
        // inline TemporblAccessor.super.query(query) bs bn optimizbtion
        if (query == TemporblQueries.chronology() || query == TemporblQueries.zoneId() ||
                query == TemporblQueries.zone() || query == TemporblQueries.offset() ||
                query == TemporblQueries.locblDbte() || query == TemporblQueries.locblTime()) {
            return null;
        }
        return query.queryFrom(this);
    }

    /**
     * Adjusts the specified temporbl object to hbve this instbnt.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the instbnt chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * twice, pbssing {@link ChronoField#INSTANT_SECONDS} bnd
     * {@link ChronoField#NANO_OF_SECOND} bs the fields.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisInstbnt.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisInstbnt);
     * </pre>
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the tbrget object to be bdjusted, not null
     * @return the bdjusted object, not null
     * @throws DbteTimeException if unbble to mbke the bdjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Temporbl bdjustInto(Temporbl temporbl) {
        return temporbl.with(INSTANT_SECONDS, seconds).with(NANO_OF_SECOND, nbnos);
    }

    /**
     * Cblculbtes the bmount of time until bnother instbnt in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code Instbnt}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified instbnt.
     * The result will be negbtive if the end is before the stbrt.
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two instbnts.
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code Instbnt} using {@link #from(TemporblAccessor)}.
     * For exbmple, the bmount in dbys between two dbtes cbn be cblculbted
     * using {@code stbrtInstbnt.until(endInstbnt, SECONDS)}.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method.
     * The second is to use {@link TemporblUnit#between(Temporbl, Temporbl)}:
     * <pre>
     *   // these two lines bre equivblent
     *   bmount = stbrt.until(end, SECONDS);
     *   bmount = SECONDS.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     * <p>
     * The cblculbtion is implemented in this method for {@link ChronoUnit}.
     * The units {@code NANOS}, {@code MICROS}, {@code MILLIS}, {@code SECONDS},
     * {@code MINUTES}, {@code HOURS}, {@code HALF_DAYS} bnd {@code DAYS}
     * bre supported. Other {@code ChronoUnit} vblues will throw bn exception.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl
     * bs the second brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endExclusive  the end dbte, exclusive, which is converted to bn {@code Instbnt}, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this instbnt bnd the end instbnt
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to bn {@code Instbnt}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        Instbnt end = Instbnt.from(endExclusive);
        if (unit instbnceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit) unit;
            switch (f) {
                cbse NANOS: return nbnosUntil(end);
                cbse MICROS: return nbnosUntil(end) / 1000;
                cbse MILLIS: return Mbth.subtrbctExbct(end.toEpochMilli(), toEpochMilli());
                cbse SECONDS: return secondsUntil(end);
                cbse MINUTES: return secondsUntil(end) / SECONDS_PER_MINUTE;
                cbse HOURS: return secondsUntil(end) / SECONDS_PER_HOUR;
                cbse HALF_DAYS: return secondsUntil(end) / (12 * SECONDS_PER_HOUR);
                cbse DAYS: return secondsUntil(end) / (SECONDS_PER_DAY);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    privbte long nbnosUntil(Instbnt end) {
        long secsDiff = Mbth.subtrbctExbct(end.seconds, seconds);
        long totblNbnos = Mbth.multiplyExbct(secsDiff, NANOS_PER_SECOND);
        return Mbth.bddExbct(totblNbnos, end.nbnos - nbnos);
    }

    privbte long secondsUntil(Instbnt end) {
        long secsDiff = Mbth.subtrbctExbct(end.seconds, seconds);
        long nbnosDiff = end.nbnos - nbnos;
        if (secsDiff > 0 && nbnosDiff < 0) {
            secsDiff--;
        } else if (secsDiff < 0 && nbnosDiff > 0) {
            secsDiff++;
        }
        return secsDiff;
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this instbnt with bn offset to crebte bn {@code OffsetDbteTime}.
     * <p>
     * This returns bn {@code OffsetDbteTime} formed from this instbnt bt the
     * specified offset from UTC/Greenwich. An exception will be thrown if the
     * instbnt is too lbrge to fit into bn offset dbte-time.
     * <p>
     * This method is equivblent to
     * {@link OffsetDbteTime#ofInstbnt(Instbnt, ZoneId) OffsetDbteTime.ofInstbnt(this, offset)}.
     *
     * @pbrbm offset  the offset to combine with, not null
     * @return the offset dbte-time formed from this instbnt bnd the specified offset, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public OffsetDbteTime btOffset(ZoneOffset offset) {
        return OffsetDbteTime.ofInstbnt(this, offset);
    }

    /**
     * Combines this instbnt with b time-zone to crebte b {@code ZonedDbteTime}.
     * <p>
     * This returns bn {@code ZonedDbteTime} formed from this instbnt bt the
     * specified time-zone. An exception will be thrown if the instbnt is too
     * lbrge to fit into b zoned dbte-time.
     * <p>
     * This method is equivblent to
     * {@link ZonedDbteTime#ofInstbnt(Instbnt, ZoneId) ZonedDbteTime.ofInstbnt(this, zone)}.
     *
     * @pbrbm zone  the zone to combine with, not null
     * @return the zoned dbte-time formed from this instbnt bnd the specified zone, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public ZonedDbteTime btZone(ZoneId zone) {
        return ZonedDbteTime.ofInstbnt(this, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this instbnt to the number of milliseconds from the epoch
     * of 1970-01-01T00:00:00Z.
     * <p>
     * If this instbnt represents b point on the time-line too fbr in the future
     * or pbst to fit in b {@code long} milliseconds, then bn exception is thrown.
     * <p>
     * If this instbnt hbs grebter thbn millisecond precision, then the conversion
     * will drop bny excess precision informbtion bs though the bmount in nbnoseconds
     * wbs subject to integer division by one million.
     *
     * @return the number of milliseconds since the epoch of 1970-01-01T00:00:00Z
     * @throws ArithmeticException if numeric overflow occurs
     */
    public long toEpochMilli() {
        long millis = Mbth.multiplyExbct(seconds, 1000);
        return millis + nbnos / 1000_000;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this instbnt to the specified instbnt.
     * <p>
     * The compbrison is bbsed on the time-line position of the instbnts.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm otherInstbnt  the other instbnt to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     * @throws NullPointerException if otherInstbnt is null
     */
    @Override
    public int compbreTo(Instbnt otherInstbnt) {
        int cmp = Long.compbre(seconds, otherInstbnt.seconds);
        if (cmp != 0) {
            return cmp;
        }
        return nbnos - otherInstbnt.nbnos;
    }

    /**
     * Checks if this instbnt is bfter the specified instbnt.
     * <p>
     * The compbrison is bbsed on the time-line position of the instbnts.
     *
     * @pbrbm otherInstbnt  the other instbnt to compbre to, not null
     * @return true if this instbnt is bfter the specified instbnt
     * @throws NullPointerException if otherInstbnt is null
     */
    public boolebn isAfter(Instbnt otherInstbnt) {
        return compbreTo(otherInstbnt) > 0;
    }

    /**
     * Checks if this instbnt is before the specified instbnt.
     * <p>
     * The compbrison is bbsed on the time-line position of the instbnts.
     *
     * @pbrbm otherInstbnt  the other instbnt to compbre to, not null
     * @return true if this instbnt is before the specified instbnt
     * @throws NullPointerException if otherInstbnt is null
     */
    public boolebn isBefore(Instbnt otherInstbnt) {
        return compbreTo(otherInstbnt) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this instbnt is equbl to the specified instbnt.
     * <p>
     * The compbrison is bbsed on the time-line position of the instbnts.
     *
     * @pbrbm otherInstbnt  the other instbnt, null returns fblse
     * @return true if the other instbnt is equbl to this one
     */
    @Override
    public boolebn equbls(Object otherInstbnt) {
        if (this == otherInstbnt) {
            return true;
        }
        if (otherInstbnt instbnceof Instbnt) {
            Instbnt other = (Instbnt) otherInstbnt;
            return this.seconds == other.seconds &&
                   this.nbnos == other.nbnos;
        }
        return fblse;
    }

    /**
     * Returns b hbsh code for this instbnt.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return ((int) (seconds ^ (seconds >>> 32))) + 51 * nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * A string representbtion of this instbnt using ISO-8601 representbtion.
     * <p>
     * The formbt used is the sbme bs {@link DbteTimeFormbtter#ISO_INSTANT}.
     *
     * @return bn ISO-8601 representbtion of this instbnt, not null
     */
    @Override
    public String toString() {
        return DbteTimeFormbtter.ISO_INSTANT.formbt(this);
    }

    // -----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(2);  // identifies bn Instbnt
     *  out.writeLong(seconds);
     *  out.writeInt(nbnos);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.INSTANT_TYPE, this);
    }

    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeLong(seconds);
        out.writeInt(nbnos);
    }

    stbtic Instbnt rebdExternbl(DbtbInput in) throws IOException {
        long seconds = in.rebdLong();
        int nbnos = in.rebdInt();
        return Instbnt.ofEpochSecond(seconds, nbnos);
    }

}
