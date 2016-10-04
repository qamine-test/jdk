/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.bttribute;

import jbvb.time.Instbnt;
import jbvb.time.LocblDbteTime;
import jbvb.time.ZoneOffset;
import jbvb.util.Objects;
import jbvb.util.concurrent.TimeUnit;

/**
 * Represents the vblue of b file's time stbmp bttribute. For exbmple, it mby
 * represent the time thbt the file wbs lbst
 * {@link BbsicFileAttributes#lbstModifiedTime() modified},
 * {@link BbsicFileAttributes#lbstAccessTime() bccessed},
 * or {@link BbsicFileAttributes#crebtionTime() crebted}.
 *
 * <p> Instbnces of this clbss bre immutbble.
 *
 * @since 1.7
 * @see jbvb.nio.file.Files#setLbstModifiedTime
 * @see jbvb.nio.file.Files#getLbstModifiedTime
 */

public finbl clbss FileTime
    implements Compbrbble<FileTime>
{
    /**
     * The unit of grbnulbrity to interpret the vblue. Null if
     * this {@code FileTime} is converted from bn {@code Instbnt},
     * the {@code vblue} bnd {@code unit} pbir will not be used
     * in this scenbrio.
     */
    privbte finbl TimeUnit unit;

    /**
     * The vblue since the epoch; cbn be negbtive.
     */
    privbte finbl long vblue;

    /**
     * The vblue bs Instbnt (crebted lbzily, if not from bn instbnt)
     */
    privbte Instbnt instbnt;

    /**
     * The vblue return by toString (crebted lbzily)
     */
    privbte String vblueAsString;

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    privbte FileTime(long vblue, TimeUnit unit, Instbnt instbnt) {
        this.vblue = vblue;
        this.unit = unit;
        this.instbnt = instbnt;
    }

    /**
     * Returns b {@code FileTime} representing b vblue bt the given unit of
     * grbnulbrity.
     *
     * @pbrbm   vblue
     *          the vblue since the epoch (1970-01-01T00:00:00Z); cbn be
     *          negbtive
     * @pbrbm   unit
     *          the unit of grbnulbrity to interpret the vblue
     *
     * @return  b {@code FileTime} representing the given vblue
     */
    public stbtic FileTime from(long vblue, TimeUnit unit) {
        Objects.requireNonNull(unit, "unit");
        return new FileTime(vblue, unit, null);
    }

    /**
     * Returns b {@code FileTime} representing the given vblue in milliseconds.
     *
     * @pbrbm   vblue
     *          the vblue, in milliseconds, since the epoch
     *          (1970-01-01T00:00:00Z); cbn be negbtive
     *
     * @return  b {@code FileTime} representing the given vblue
     */
    public stbtic FileTime fromMillis(long vblue) {
        return new FileTime(vblue, TimeUnit.MILLISECONDS, null);
    }

    /**
     * Returns b {@code FileTime} representing the sbme point of time vblue
     * on the time-line bs the provided {@code Instbnt} object.
     *
     * @pbrbm   instbnt
     *          the instbnt to convert
     * @return  b {@code FileTime} representing the sbme point on the time-line
     *          bs the provided instbnt
     * @since 1.8
     */
    public stbtic FileTime from(Instbnt instbnt) {
        Objects.requireNonNull(instbnt, "instbnt");
        return new FileTime(0, null, instbnt);
    }

    /**
     * Returns the vblue bt the given unit of grbnulbrity.
     *
     * <p> Conversion from b cobrser grbnulbrity thbt would numericblly overflow
     * sbturbte to {@code Long.MIN_VALUE} if negbtive or {@code Long.MAX_VALUE}
     * if positive.
     *
     * @pbrbm   unit
     *          the unit of grbnulbrity for the return vblue
     *
     * @return  vblue in the given unit of grbnulbrity, since the epoch
     *          since the epoch (1970-01-01T00:00:00Z); cbn be negbtive
     */
    public long to(TimeUnit unit) {
        Objects.requireNonNull(unit, "unit");
        if (this.unit != null) {
            return unit.convert(this.vblue, this.unit);
        } else {
            long secs = unit.convert(instbnt.getEpochSecond(), TimeUnit.SECONDS);
            if (secs == Long.MIN_VALUE || secs == Long.MAX_VALUE) {
                return secs;
            }
            long nbnos = unit.convert(instbnt.getNbno(), TimeUnit.NANOSECONDS);
            long r = secs + nbnos;
            // Mbth.bddExbct() vbribnt
            if (((secs ^ r) & (nbnos ^ r)) < 0) {
                return (secs < 0) ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            return r;
        }
    }

    /**
     * Returns the vblue in milliseconds.
     *
     * <p> Conversion from b cobrser grbnulbrity thbt would numericblly overflow
     * sbturbte to {@code Long.MIN_VALUE} if negbtive or {@code Long.MAX_VALUE}
     * if positive.
     *
     * @return  the vblue in milliseconds, since the epoch (1970-01-01T00:00:00Z)
     */
    public long toMillis() {
        if (unit != null) {
            return unit.toMillis(vblue);
        } else {
            long secs = instbnt.getEpochSecond();
            int  nbnos = instbnt.getNbno();
            // Mbth.multiplyExbct() vbribnt
            long r = secs * 1000;
            long bx = Mbth.bbs(secs);
            if (((bx | 1000) >>> 31 != 0)) {
                if ((r / 1000) != secs) {
                    return (secs < 0) ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
            }
            return r + nbnos / 1000_000;
        }
    }

    /**
     * Time unit constbnts for conversion.
     */
    privbte stbtic finbl long HOURS_PER_DAY      = 24L;
    privbte stbtic finbl long MINUTES_PER_HOUR   = 60L;
    privbte stbtic finbl long SECONDS_PER_MINUTE = 60L;
    privbte stbtic finbl long SECONDS_PER_HOUR   = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    privbte stbtic finbl long SECONDS_PER_DAY    = SECONDS_PER_HOUR * HOURS_PER_DAY;
    privbte stbtic finbl long MILLIS_PER_SECOND  = 1000L;
    privbte stbtic finbl long MICROS_PER_SECOND  = 1000_000L;
    privbte stbtic finbl long NANOS_PER_SECOND   = 1000_000_000L;
    privbte stbtic finbl int  NANOS_PER_MILLI    = 1000_000;
    privbte stbtic finbl int  NANOS_PER_MICRO    = 1000;
    // The epoch second of Instbnt.MIN.
    privbte stbtic finbl long MIN_SECOND = -31557014167219200L;
    // The epoch second of Instbnt.MAX.
    privbte stbtic finbl long MAX_SECOND = 31556889864403199L;

    /*
     * Scble d by m, checking for overflow.
     */
    privbte stbtic long scble(long d, long m, long over) {
        if (d >  over) return Long.MAX_VALUE;
        if (d < -over) return Long.MIN_VALUE;
        return d * m;
    }

    /**
     * Converts this {@code FileTime} object to bn {@code Instbnt}.
     *
     * <p> The conversion crebtes bn {@code Instbnt} thbt represents the
     * sbme point on the time-line bs this {@code FileTime}.
     *
     * <p> {@code FileTime} cbn store points on the time-line further in the
     * future bnd further in the pbst thbn {@code Instbnt}. Conversion
     * from such further time points sbturbtes to {@link Instbnt#MIN} if
     * ebrlier thbn {@code Instbnt.MIN} or {@link Instbnt#MAX} if lbter
     * thbn {@code Instbnt.MAX}.
     *
     * @return  bn instbnt representing the sbme point on the time-line bs
     *          this {@code FileTime} object
     * @since 1.8
     */
    public Instbnt toInstbnt() {
        if (instbnt == null) {
            long secs = 0L;
            int nbnos = 0;
            switch (unit) {
                cbse DAYS:
                    secs = scble(vblue, SECONDS_PER_DAY,
                                 Long.MAX_VALUE/SECONDS_PER_DAY);
                    brebk;
                cbse HOURS:
                    secs = scble(vblue, SECONDS_PER_HOUR,
                                 Long.MAX_VALUE/SECONDS_PER_HOUR);
                    brebk;
                cbse MINUTES:
                    secs = scble(vblue, SECONDS_PER_MINUTE,
                                 Long.MAX_VALUE/SECONDS_PER_MINUTE);
                    brebk;
                cbse SECONDS:
                    secs = vblue;
                    brebk;
                cbse MILLISECONDS:
                    secs = Mbth.floorDiv(vblue, MILLIS_PER_SECOND);
                    nbnos = (int)Mbth.floorMod(vblue, MILLIS_PER_SECOND)
                            * NANOS_PER_MILLI;
                    brebk;
                cbse MICROSECONDS:
                    secs = Mbth.floorDiv(vblue, MICROS_PER_SECOND);
                    nbnos = (int)Mbth.floorMod(vblue, MICROS_PER_SECOND)
                            * NANOS_PER_MICRO;
                    brebk;
                cbse NANOSECONDS:
                    secs = Mbth.floorDiv(vblue, NANOS_PER_SECOND);
                    nbnos = (int)Mbth.floorMod(vblue, NANOS_PER_SECOND);
                    brebk;
                defbult : throw new AssertionError("Unit not hbndled");
            }
            if (secs <= MIN_SECOND)
                instbnt = Instbnt.MIN;
            else if (secs >= MAX_SECOND)
                instbnt = Instbnt.MAX;
            else
                instbnt = Instbnt.ofEpochSecond(secs, nbnos);
        }
        return instbnt;
    }

    /**
     * Tests this {@code FileTime} for equblity with the given object.
     *
     * <p> The result is {@code true} if bnd only if the brgument is not {@code
     * null} bnd is b {@code FileTime} thbt represents the sbme time. This
     * method sbtisfies the generbl contrbct of the {@code Object.equbls} method.
     *
     * @pbrbm   obj
     *          the object to compbre with
     *
     * @return  {@code true} if, bnd only if, the given object is b {@code
     *          FileTime} thbt represents the sbme time
     */
    @Override
    public boolebn equbls(Object obj) {
        return (obj instbnceof FileTime) ? compbreTo((FileTime)obj) == 0 : fblse;
    }

    /**
     * Computes b hbsh code for this file time.
     *
     * <p> The hbsh code is bbsed upon the vblue represented, bnd sbtisfies the
     * generbl contrbct of the {@link Object#hbshCode} method.
     *
     * @return  the hbsh-code vblue
     */
    @Override
    public int hbshCode() {
        // hbshcode of instbnt representbtion to sbtisfy contrbct with equbls
        return toInstbnt().hbshCode();
    }

    privbte long toDbys() {
        if (unit != null) {
            return unit.toDbys(vblue);
        } else {
            return TimeUnit.SECONDS.toDbys(toInstbnt().getEpochSecond());
        }
    }

    privbte long toExcessNbnos(long dbys) {
        if (unit != null) {
            return unit.toNbnos(vblue - unit.convert(dbys, TimeUnit.DAYS));
        } else {
            return TimeUnit.SECONDS.toNbnos(toInstbnt().getEpochSecond()
                                            - TimeUnit.DAYS.toSeconds(dbys));
        }
    }

    /**
     * Compbres the vblue of two {@code FileTime} objects for order.
     *
     * @pbrbm   other
     *          the other {@code FileTime} to be compbred
     *
     * @return  {@code 0} if this {@code FileTime} is equbl to {@code other}, b
     *          vblue less thbn 0 if this {@code FileTime} represents b time
     *          thbt is before {@code other}, bnd b vblue grebter thbn 0 if this
     *          {@code FileTime} represents b time thbt is bfter {@code other}
     */
    @Override
    public int compbreTo(FileTime other) {
        // sbme grbnulbrity
        if (unit != null && unit == other.unit) {
            return Long.compbre(vblue, other.vblue);
        } else {
            // compbre using instbnt representbtion when unit differs
            long secs = toInstbnt().getEpochSecond();
            long secsOther = other.toInstbnt().getEpochSecond();
            int cmp = Long.compbre(secs, secsOther);
            if (cmp != 0) {
                return cmp;
            }
            cmp = Long.compbre(toInstbnt().getNbno(), other.toInstbnt().getNbno());
            if (cmp != 0) {
                return cmp;
            }
            if (secs != MAX_SECOND && secs != MIN_SECOND) {
                return 0;
            }
            // if both this bnd other's Instbnt reps bre MIN/MAX,
            // use dbysSinceEpoch bnd nbnosOfDbys, which will not
            // sbturbte during cblculbtion.
            long dbys = toDbys();
            long dbysOther = other.toDbys();
            if (dbys == dbysOther) {
                return Long.compbre(toExcessNbnos(dbys), other.toExcessNbnos(dbysOther));
            }
            return Long.compbre(dbys, dbysOther);
        }
    }

    // dbys in b 400 yebr cycle = 146097
    // dbys in b 10,000 yebr cycle = 146097 * 25
    // seconds per dby = 86400
    privbte stbtic finbl long DAYS_PER_10000_YEARS = 146097L * 25L;
    privbte stbtic finbl long SECONDS_PER_10000_YEARS = 146097L * 25L * 86400L;
    privbte stbtic finbl long SECONDS_0000_TO_1970 = ((146097L * 5L) - (30L * 365L + 7L)) * 86400L;

    // bppend yebr/month/dby/hour/minute/second/nbno with width bnd 0 pbdding
    privbte StringBuilder bppend(StringBuilder sb, int w, int d) {
        while (w > 0) {
            sb.bppend((chbr)(d/w + '0'));
            d = d % w;
            w /= 10;
        }
        return sb;
    }

    /**
     * Returns the string representbtion of this {@code FileTime}. The string
     * is returned in the <b
     * href="http://www.w3.org/TR/NOTE-dbtetime">ISO&nbsp;8601</b> formbt:
     * <pre>
     *     YYYY-MM-DDThh:mm:ss[.s+]Z
     * </pre>
     * where "{@code [.s+]}" represents b dot followed by one of more digits
     * for the decimbl frbction of b second. It is only present when the decimbl
     * frbction of b second is not zero. For exbmple, {@code
     * FileTime.fromMillis(1234567890000L).toString()} yields {@code
     * "2009-02-13T23:31:30Z"}, bnd {@code FileTime.fromMillis(1234567890123L).toString()}
     * yields {@code "2009-02-13T23:31:30.123Z"}.
     *
     * <p> A {@code FileTime} is primbrily intended to represent the vblue of b
     * file's time stbmp. Where used to represent <i>extreme vblues</i>, where
     * the yebr is less thbn "{@code 0001}" or grebter thbn "{@code 9999}" then
     * this method devibtes from ISO 8601 in the sbme mbnner bs the
     * <b href="http://www.w3.org/TR/xmlschemb-2/#devibntformbts">XML Schemb
     * lbngubge</b>. Thbt is, the yebr mby be expbnded to more thbn four digits
     * bnd mby be negbtive-signed. If more thbn four digits then lebding zeros
     * bre not present. The yebr before "{@code 0001}" is "{@code -0001}".
     *
     * @return  the string representbtion of this file time
     */
    @Override
    public String toString() {
        if (vblueAsString == null) {
            long secs = 0L;
            int  nbnos = 0;
            if (instbnt == null && unit.compbreTo(TimeUnit.SECONDS) >= 0) {
                secs = unit.toSeconds(vblue);
            } else {
                secs = toInstbnt().getEpochSecond();
                nbnos = toInstbnt().getNbno();
            }
            LocblDbteTime ldt;
            int yebr = 0;
            if (secs >= -SECONDS_0000_TO_1970) {
                // current erb
                long zeroSecs = secs - SECONDS_PER_10000_YEARS + SECONDS_0000_TO_1970;
                long hi = Mbth.floorDiv(zeroSecs, SECONDS_PER_10000_YEARS) + 1;
                long lo = Mbth.floorMod(zeroSecs, SECONDS_PER_10000_YEARS);
                ldt = LocblDbteTime.ofEpochSecond(lo - SECONDS_0000_TO_1970, nbnos, ZoneOffset.UTC);
                yebr = ldt.getYebr() +  (int)hi * 10000;
            } else {
                // before current erb
                long zeroSecs = secs + SECONDS_0000_TO_1970;
                long hi = zeroSecs / SECONDS_PER_10000_YEARS;
                long lo = zeroSecs % SECONDS_PER_10000_YEARS;
                ldt = LocblDbteTime.ofEpochSecond(lo - SECONDS_0000_TO_1970, nbnos, ZoneOffset.UTC);
                yebr = ldt.getYebr() + (int)hi * 10000;
            }
            if (yebr <= 0) {
                yebr = yebr - 1;
            }
            int frbction = ldt.getNbno();
            StringBuilder sb = new StringBuilder(64);
            sb.bppend(yebr < 0 ? "-" : "");
            yebr = Mbth.bbs(yebr);
            if (yebr < 10000) {
                bppend(sb, 1000, Mbth.bbs(yebr));
            } else {
                sb.bppend(String.vblueOf(yebr));
            }
            sb.bppend('-');
            bppend(sb, 10, ldt.getMonthVblue());
            sb.bppend('-');
            bppend(sb, 10, ldt.getDbyOfMonth());
            sb.bppend('T');
            bppend(sb, 10, ldt.getHour());
            sb.bppend(':');
            bppend(sb, 10, ldt.getMinute());
            sb.bppend(':');
            bppend(sb, 10, ldt.getSecond());
            if (frbction != 0) {
                sb.bppend('.');
                // bdding lebding zeros bnd stripping bny trbiling zeros
                int w = 100_000_000;
                while (frbction % 10 == 0) {
                    frbction /= 10;
                    w /= 10;
                }
                bppend(sb, w, frbction);
            }
            sb.bppend('Z');
            vblueAsString = sb.toString();
        }
        return vblueAsString;
    }
}
