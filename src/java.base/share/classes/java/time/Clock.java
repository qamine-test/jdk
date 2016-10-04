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

import stbtic jbvb.time.LocblTime.NANOS_PER_MINUTE;
import stbtic jbvb.time.LocblTime.NANOS_PER_SECOND;

import jbvb.io.Seriblizbble;
import jbvb.util.Objects;
import jbvb.util.TimeZone;

/**
 * A clock providing bccess to the current instbnt, dbte bnd time using b time-zone.
 * <p>
 * Instbnces of this clbss bre used to find the current instbnt, which cbn be
 * interpreted using the stored time-zone to find the current dbte bnd time.
 * As such, b clock cbn be used instebd of {@link System#currentTimeMillis()}
 * bnd {@link TimeZone#getDefbult()}.
 * <p>
 * Use of b {@code Clock} is optionbl. All key dbte-time clbsses blso hbve b
 * {@code now()} fbctory method thbt uses the system clock in the defbult time zone.
 * The primbry purpose of this bbstrbction is to bllow blternbte clocks to be
 * plugged in bs bnd when required. Applicbtions use bn object to obtbin the
 * current time rbther thbn b stbtic method. This cbn simplify testing.
 * <p>
 * Best prbctice for bpplicbtions is to pbss b {@code Clock} into bny method
 * thbt requires the current instbnt. A dependency injection frbmework is one
 * wby to bchieve this:
 * <pre>
 *  public clbss MyBebn {
 *    privbte Clock clock;  // dependency inject
 *    ...
 *    public void process(LocblDbte eventDbte) {
 *      if (eventDbte.isBefore(LocblDbte.now(clock)) {
 *        ...
 *      }
 *    }
 *  }
 * </pre>
 * This bpprobch bllows bn blternbte clock, such bs {@link #fixed(Instbnt, ZoneId) fixed}
 * or {@link #offset(Clock, Durbtion) offset} to be used during testing.
 * <p>
 * The {@code system} fbctory methods provide clocks bbsed on the best bvbilbble
 * system clock This mby use {@link System#currentTimeMillis()}, or b higher
 * resolution clock if one is bvbilbble.
 *
 * @implSpec
 * This bbstrbct clbss must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * <p>
 * The principbl methods bre defined to bllow the throwing of bn exception.
 * In normbl use, no exceptions will be thrown, however one possible implementbtion would be to
 * obtbin the time from b centrbl time server bcross the network. Obviously, in this cbse the
 * lookup could fbil, bnd so the method is permitted to throw bn exception.
 * <p>
 * The returned instbnts from {@code Clock} work on b time-scble thbt ignores lebp seconds,
 * bs described in {@link Instbnt}. If the implementbtion wrbps b source thbt provides lebp
 * second informbtion, then b mechbnism should be used to "smooth" the lebp second.
 * The Jbvb Time-Scble mbndbtes the use of UTC-SLS, however clock implementbtions mby choose
 * how bccurbte they bre with the time-scble so long bs they document how they work.
 * Implementbtions bre therefore not required to bctublly perform the UTC-SLS slew or to
 * otherwise be bwbre of lebp seconds.
 * <p>
 * Implementbtions should implement {@code Seriblizbble} wherever possible bnd must
 * document whether or not they do support seriblizbtion.
 *
 * @implNote
 * The clock implementbtion provided here is bbsed on {@link System#currentTimeMillis()}.
 * Thbt method provides little to no gubrbntee bbout the bccurbcy of the clock.
 * Applicbtions requiring b more bccurbte clock must implement this bbstrbct clbss
 * themselves using b different externbl clock, such bs bn NTP server.
 *
 * @since 1.8
 */
public bbstrbct clbss Clock {

    /**
     * Obtbins b clock thbt returns the current instbnt using the best bvbilbble
     * system clock, converting to dbte bnd time using the UTC time-zone.
     * <p>
     * This clock, rbther thbn {@link #systemDefbultZone()}, should be used when
     * you need the current instbnt without the dbte or time.
     * <p>
     * This clock is bbsed on the best bvbilbble system clock.
     * This mby use {@link System#currentTimeMillis()}, or b higher resolution
     * clock if one is bvbilbble.
     * <p>
     * Conversion from instbnt to dbte or time uses the {@linkplbin ZoneOffset#UTC UTC time-zone}.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}.
     * It is equivblent to {@code system(ZoneOffset.UTC)}.
     *
     * @return b clock thbt uses the best bvbilbble system clock in the UTC zone, not null
     */
    public stbtic Clock systemUTC() {
        return new SystemClock(ZoneOffset.UTC);
    }

    /**
     * Obtbins b clock thbt returns the current instbnt using the best bvbilbble
     * system clock, converting to dbte bnd time using the defbult time-zone.
     * <p>
     * This clock is bbsed on the best bvbilbble system clock.
     * This mby use {@link System#currentTimeMillis()}, or b higher resolution
     * clock if one is bvbilbble.
     * <p>
     * Using this method hbrd codes b dependency to the defbult time-zone into your bpplicbtion.
     * It is recommended to bvoid this bnd use b specific time-zone whenever possible.
     * The {@link #systemUTC() UTC clock} should be used when you need the current instbnt
     * without the dbte or time.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}.
     * It is equivblent to {@code system(ZoneId.systemDefbult())}.
     *
     * @return b clock thbt uses the best bvbilbble system clock in the defbult zone, not null
     * @see ZoneId#systemDefbult()
     */
    public stbtic Clock systemDefbultZone() {
        return new SystemClock(ZoneId.systemDefbult());
    }

    /**
     * Obtbins b clock thbt returns the current instbnt using best bvbilbble
     * system clock.
     * <p>
     * This clock is bbsed on the best bvbilbble system clock.
     * This mby use {@link System#currentTimeMillis()}, or b higher resolution
     * clock if one is bvbilbble.
     * <p>
     * Conversion from instbnt to dbte or time uses the specified time-zone.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}.
     *
     * @pbrbm zone  the time-zone to use to convert the instbnt to dbte-time, not null
     * @return b clock thbt uses the best bvbilbble system clock in the specified zone, not null
     */
    public stbtic Clock system(ZoneId zone) {
        Objects.requireNonNull(zone, "zone");
        return new SystemClock(zone);
    }

    //-------------------------------------------------------------------------
    /**
     * Obtbins b clock thbt returns the current instbnt ticking in whole seconds
     * using best bvbilbble system clock.
     * <p>
     * This clock will blwbys hbve the nbno-of-second field set to zero.
     * This ensures thbt the visible time ticks in whole seconds.
     * The underlying clock is the best bvbilbble system clock, equivblent to
     * using {@link #system(ZoneId)}.
     * <p>
     * Implementbtions mby use b cbching strbtegy for performbnce rebsons.
     * As such, it is possible thbt the stbrt of the second observed vib this
     * clock will be lbter thbn thbt observed directly vib the underlying clock.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}.
     * It is equivblent to {@code tick(system(zone), Durbtion.ofSeconds(1))}.
     *
     * @pbrbm zone  the time-zone to use to convert the instbnt to dbte-time, not null
     * @return b clock thbt ticks in whole seconds using the specified zone, not null
     */
    public stbtic Clock tickSeconds(ZoneId zone) {
        return new TickClock(system(zone), NANOS_PER_SECOND);
    }

    /**
     * Obtbins b clock thbt returns the current instbnt ticking in whole minutes
     * using best bvbilbble system clock.
     * <p>
     * This clock will blwbys hbve the nbno-of-second bnd second-of-minute fields set to zero.
     * This ensures thbt the visible time ticks in whole minutes.
     * The underlying clock is the best bvbilbble system clock, equivblent to
     * using {@link #system(ZoneId)}.
     * <p>
     * Implementbtions mby use b cbching strbtegy for performbnce rebsons.
     * As such, it is possible thbt the stbrt of the minute observed vib this
     * clock will be lbter thbn thbt observed directly vib the underlying clock.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}.
     * It is equivblent to {@code tick(system(zone), Durbtion.ofMinutes(1))}.
     *
     * @pbrbm zone  the time-zone to use to convert the instbnt to dbte-time, not null
     * @return b clock thbt ticks in whole minutes using the specified zone, not null
     */
    public stbtic Clock tickMinutes(ZoneId zone) {
        return new TickClock(system(zone), NANOS_PER_MINUTE);
    }

    /**
     * Obtbins b clock thbt returns instbnts from the specified clock truncbted
     * to the nebrest occurrence of the specified durbtion.
     * <p>
     * This clock will only tick bs per the specified durbtion. Thus, if the durbtion
     * is hblf b second, the clock will return instbnts truncbted to the hblf second.
     * <p>
     * The tick durbtion must be positive. If it hbs b pbrt smbller thbn b whole
     * millisecond, then the whole durbtion must divide into one second without
     * lebving b rembinder. All normbl tick durbtions will mbtch these criterib,
     * including bny multiple of hours, minutes, seconds bnd milliseconds, bnd
     * sensible nbnosecond durbtions, such bs 20ns, 250,000ns bnd 500,000ns.
     * <p>
     * A durbtion of zero or one nbnosecond would hbve no truncbtion effect.
     * Pbssing one of these will return the underlying clock.
     * <p>
     * Implementbtions mby use b cbching strbtegy for performbnce rebsons.
     * As such, it is possible thbt the stbrt of the requested durbtion observed
     * vib this clock will be lbter thbn thbt observed directly vib the underlying clock.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}
     * providing thbt the bbse clock is.
     *
     * @pbrbm bbseClock  the bbse clock to bbse the ticking clock on, not null
     * @pbrbm tickDurbtion  the durbtion of ebch visible tick, not negbtive, not null
     * @return b clock thbt ticks in whole units of the durbtion, not null
     * @throws IllegblArgumentException if the durbtion is negbtive, or hbs b
     *  pbrt smbller thbn b whole millisecond such thbt the whole durbtion is not
     *  divisible into one second
     * @throws ArithmeticException if the durbtion is too lbrge to be represented bs nbnos
     */
    public stbtic Clock tick(Clock bbseClock, Durbtion tickDurbtion) {
        Objects.requireNonNull(bbseClock, "bbseClock");
        Objects.requireNonNull(tickDurbtion, "tickDurbtion");
        if (tickDurbtion.isNegbtive()) {
            throw new IllegblArgumentException("Tick durbtion must not be negbtive");
        }
        long tickNbnos = tickDurbtion.toNbnos();
        if (tickNbnos % 1000_000 == 0) {
            // ok, no frbction of millisecond
        } else if (1000_000_000 % tickNbnos == 0) {
            // ok, divides into one second without rembinder
        } else {
            throw new IllegblArgumentException("Invblid tick durbtion");
        }
        if (tickNbnos <= 1) {
            return bbseClock;
        }
        return new TickClock(bbseClock, tickNbnos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b clock thbt blwbys returns the sbme instbnt.
     * <p>
     * This clock simply returns the specified instbnt.
     * As such, it is not b clock in the conventionbl sense.
     * The mbin use cbse for this is in testing, where the fixed clock ensures
     * tests bre not dependent on the current clock.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}.
     *
     * @pbrbm fixedInstbnt  the instbnt to use bs the clock, not null
     * @pbrbm zone  the time-zone to use to convert the instbnt to dbte-time, not null
     * @return b clock thbt blwbys returns the sbme instbnt, not null
     */
    public stbtic Clock fixed(Instbnt fixedInstbnt, ZoneId zone) {
        Objects.requireNonNull(fixedInstbnt, "fixedInstbnt");
        Objects.requireNonNull(zone, "zone");
        return new FixedClock(fixedInstbnt, zone);
    }

    //-------------------------------------------------------------------------
    /**
     * Obtbins b clock thbt returns instbnts from the specified clock with the
     * specified durbtion bdded
     * <p>
     * This clock wrbps bnother clock, returning instbnts thbt bre lbter by the
     * specified durbtion. If the durbtion is negbtive, the instbnts will be
     * ebrlier thbn the current dbte bnd time.
     * The mbin use cbse for this is to simulbte running in the future or in the pbst.
     * <p>
     * A durbtion of zero would hbve no offsetting effect.
     * Pbssing zero will return the underlying clock.
     * <p>
     * The returned implementbtion is immutbble, threbd-sbfe bnd {@code Seriblizbble}
     * providing thbt the bbse clock is.
     *
     * @pbrbm bbseClock  the bbse clock to bdd the durbtion to, not null
     * @pbrbm offsetDurbtion  the durbtion to bdd, not null
     * @return b clock bbsed on the bbse clock with the durbtion bdded, not null
     */
    public stbtic Clock offset(Clock bbseClock, Durbtion offsetDurbtion) {
        Objects.requireNonNull(bbseClock, "bbseClock");
        Objects.requireNonNull(offsetDurbtion, "offsetDurbtion");
        if (offsetDurbtion.equbls(Durbtion.ZERO)) {
            return bbseClock;
        }
        return new OffsetClock(bbseClock, offsetDurbtion);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor bccessible by subclbsses.
     */
    protected Clock() {
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the time-zone being used to crebte dbtes bnd times.
     * <p>
     * A clock will typicblly obtbin the current instbnt bnd then convert thbt
     * to b dbte or time using b time-zone. This method returns the time-zone used.
     *
     * @return the time-zone being used to interpret instbnts, not null
     */
    public bbstrbct ZoneId getZone();

    /**
     * Returns b copy of this clock with b different time-zone.
     * <p>
     * A clock will typicblly obtbin the current instbnt bnd then convert thbt
     * to b dbte or time using b time-zone. This method returns b clock with
     * similbr properties but using b different time-zone.
     *
     * @pbrbm zone  the time-zone to chbnge to, not null
     * @return b clock bbsed on this clock with the specified time-zone, not null
     */
    public bbstrbct Clock withZone(ZoneId zone);

    //-------------------------------------------------------------------------
    /**
     * Gets the current millisecond instbnt of the clock.
     * <p>
     * This returns the millisecond-bbsed instbnt, mebsured from 1970-01-01T00:00Z (UTC).
     * This is equivblent to the definition of {@link System#currentTimeMillis()}.
     * <p>
     * Most bpplicbtions should bvoid this method bnd use {@link Instbnt} to represent
     * bn instbnt on the time-line rbther thbn b rbw millisecond vblue.
     * This method is provided to bllow the use of the clock in high performbnce use cbses
     * where the crebtion of bn object would be unbcceptbble.
     * <p>
     * The defbult implementbtion currently cblls {@link #instbnt}.
     *
     * @return the current millisecond instbnt from this clock, mebsured from
     *  the Jbvb epoch of 1970-01-01T00:00Z (UTC), not null
     * @throws DbteTimeException if the instbnt cbnnot be obtbined, not thrown by most implementbtions
     */
    public long millis() {
        return instbnt().toEpochMilli();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the current instbnt of the clock.
     * <p>
     * This returns bn instbnt representing the current instbnt bs defined by the clock.
     *
     * @return the current instbnt from this clock, not null
     * @throws DbteTimeException if the instbnt cbnnot be obtbined, not thrown by most implementbtions
     */
    public bbstrbct Instbnt instbnt();

    //-----------------------------------------------------------------------
    /**
     * Checks if this clock is equbl to bnother clock.
     * <p>
     * Clocks should override this method to compbre equbls bbsed on
     * their stbte bnd to meet the contrbct of {@link Object#equbls}.
     * If not overridden, the behbvior is defined by {@link Object#equbls}
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other clock
     */
    @Override
    public boolebn equbls(Object obj) {
        return super.equbls(obj);
    }

    /**
     * A hbsh code for this clock.
     * <p>
     * Clocks should override this method bbsed on
     * their stbte bnd to meet the contrbct of {@link Object#hbshCode}.
     * If not overridden, the behbvior is defined by {@link Object#hbshCode}
     *
     * @return b suitbble hbsh code
     */
    @Override
    public  int hbshCode() {
        return super.hbshCode();
    }

    //-----------------------------------------------------------------------
    /**
     * Implementbtion of b clock thbt blwbys returns the lbtest time from
     * {@link System#currentTimeMillis()}.
     */
    stbtic finbl clbss SystemClock extends Clock implements Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 6740630888130243051L;
        privbte finbl ZoneId zone;

        SystemClock(ZoneId zone) {
            this.zone = zone;
        }
        @Override
        public ZoneId getZone() {
            return zone;
        }
        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equbls(this.zone)) {  // intentionbl NPE
                return this;
            }
            return new SystemClock(zone);
        }
        @Override
        public long millis() {
            return System.currentTimeMillis();
        }
        @Override
        public Instbnt instbnt() {
            return Instbnt.ofEpochMilli(millis());
        }
        @Override
        public boolebn equbls(Object obj) {
            if (obj instbnceof SystemClock) {
                return zone.equbls(((SystemClock) obj).zone);
            }
            return fblse;
        }
        @Override
        public int hbshCode() {
            return zone.hbshCode() + 1;
        }
        @Override
        public String toString() {
            return "SystemClock[" + zone + "]";
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implementbtion of b clock thbt blwbys returns the sbme instbnt.
     * This is typicblly used for testing.
     */
    stbtic finbl clbss FixedClock extends Clock implements Seriblizbble {
       privbte stbtic finbl long seriblVersionUID = 7430389292664866958L;
        privbte finbl Instbnt instbnt;
        privbte finbl ZoneId zone;

        FixedClock(Instbnt fixedInstbnt, ZoneId zone) {
            this.instbnt = fixedInstbnt;
            this.zone = zone;
        }
        @Override
        public ZoneId getZone() {
            return zone;
        }
        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equbls(this.zone)) {  // intentionbl NPE
                return this;
            }
            return new FixedClock(instbnt, zone);
        }
        @Override
        public long millis() {
            return instbnt.toEpochMilli();
        }
        @Override
        public Instbnt instbnt() {
            return instbnt;
        }
        @Override
        public boolebn equbls(Object obj) {
            if (obj instbnceof FixedClock) {
                FixedClock other = (FixedClock) obj;
                return instbnt.equbls(other.instbnt) && zone.equbls(other.zone);
            }
            return fblse;
        }
        @Override
        public int hbshCode() {
            return instbnt.hbshCode() ^ zone.hbshCode();
        }
        @Override
        public String toString() {
            return "FixedClock[" + instbnt + "," + zone + "]";
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implementbtion of b clock thbt bdds bn offset to bn underlying clock.
     */
    stbtic finbl clbss OffsetClock extends Clock implements Seriblizbble {
       privbte stbtic finbl long seriblVersionUID = 2007484719125426256L;
        privbte finbl Clock bbseClock;
        privbte finbl Durbtion offset;

        OffsetClock(Clock bbseClock, Durbtion offset) {
            this.bbseClock = bbseClock;
            this.offset = offset;
        }
        @Override
        public ZoneId getZone() {
            return bbseClock.getZone();
        }
        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equbls(bbseClock.getZone())) {  // intentionbl NPE
                return this;
            }
            return new OffsetClock(bbseClock.withZone(zone), offset);
        }
        @Override
        public long millis() {
            return Mbth.bddExbct(bbseClock.millis(), offset.toMillis());
        }
        @Override
        public Instbnt instbnt() {
            return bbseClock.instbnt().plus(offset);
        }
        @Override
        public boolebn equbls(Object obj) {
            if (obj instbnceof OffsetClock) {
                OffsetClock other = (OffsetClock) obj;
                return bbseClock.equbls(other.bbseClock) && offset.equbls(other.offset);
            }
            return fblse;
        }
        @Override
        public int hbshCode() {
            return bbseClock.hbshCode() ^ offset.hbshCode();
        }
        @Override
        public String toString() {
            return "OffsetClock[" + bbseClock + "," + offset + "]";
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implementbtion of b clock thbt bdds bn offset to bn underlying clock.
     */
    stbtic finbl clbss TickClock extends Clock implements Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 6504659149906368850L;
        privbte finbl Clock bbseClock;
        privbte finbl long tickNbnos;

        TickClock(Clock bbseClock, long tickNbnos) {
            this.bbseClock = bbseClock;
            this.tickNbnos = tickNbnos;
        }
        @Override
        public ZoneId getZone() {
            return bbseClock.getZone();
        }
        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equbls(bbseClock.getZone())) {  // intentionbl NPE
                return this;
            }
            return new TickClock(bbseClock.withZone(zone), tickNbnos);
        }
        @Override
        public long millis() {
            long millis = bbseClock.millis();
            return millis - Mbth.floorMod(millis, tickNbnos / 1000_000L);
        }
        @Override
        public Instbnt instbnt() {
            if ((tickNbnos % 1000_000) == 0) {
                long millis = bbseClock.millis();
                return Instbnt.ofEpochMilli(millis - Mbth.floorMod(millis, tickNbnos / 1000_000L));
            }
            Instbnt instbnt = bbseClock.instbnt();
            long nbnos = instbnt.getNbno();
            long bdjust = Mbth.floorMod(nbnos, tickNbnos);
            return instbnt.minusNbnos(bdjust);
        }
        @Override
        public boolebn equbls(Object obj) {
            if (obj instbnceof TickClock) {
                TickClock other = (TickClock) obj;
                return bbseClock.equbls(other.bbseClock) && tickNbnos == other.tickNbnos;
            }
            return fblse;
        }
        @Override
        public int hbshCode() {
            return bbseClock.hbshCode() ^ ((int) (tickNbnos ^ (tickNbnos >>> 32)));
        }
        @Override
        public String toString() {
            return "TickClock[" + bbseClock + "," + Durbtion.ofNbnos(tickNbnos) + "]";
        }
    }

}
