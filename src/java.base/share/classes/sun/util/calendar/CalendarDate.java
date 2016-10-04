/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cblendbr;

import jbvb.lbng.Clonebble;
import jbvb.util.Locble;
import jbvb.util.TimeZone;

/**
 * The <code>CblendbrDbte</code> clbss represents b specific instbnt
 * in time by cblendbr dbte bnd time fields thbt bre multiple cycles
 * in different time unites. The sembntics of ebch cblendbr field is
 * given by b concrete cblendbr system rbther thbn this
 * <code>CblendbrDbte</code> clbss thbt holds cblendbr field vblues
 * without interpreting them. Therefore, this clbss cbn be used to
 * represent bn bmount of time, such bs 2 yebrs bnd 3 months.
 *
 * <p>A <code>CblendbrDbte</code> instbnce cbn be crebted by cblling
 * the <code>newCblendbrDbte</code> or <code>getCblendbrDbte</code>
 * methods in <code>CblendbrSystem</code>. A
 * <code>CblendbrSystem</code> instbnce is obtbined by cblling one of
 * the fbctory methods in <code>CblendbrSystem</code>. Mbnipulbtions
 * of cblendbr dbtes must be hbndled by the cblendbr system by which
 * <code>CblendbrDbte</code> instbnces hbve been crebted.
 *
 * <p>Some cblendbr fields cbn be modified through method cblls. Any
 * modificbtion of b cblendbr field brings the stbte of b
 * <code>CblendbrDbte</code> to <I>not normblized</I>. The
 * normblizbtion must be performed to mbke bll the cblendbr fields
 * consistent with b cblendbr system.
 *
 * <p>The <code>protected</code> methods bre intended to be used for
 * implementing b concrete cblendbr system, not for generbl use bs bn
 * API.
 *
 * @see CblendbrSystem
 * @buthor Mbsbyoshi Okutsu
 * @since 1.5
 */
public bbstrbct clbss CblendbrDbte implements Clonebble {
    public stbtic finbl int FIELD_UNDEFINED = Integer.MIN_VALUE;
    public stbtic finbl long TIME_UNDEFINED = Long.MIN_VALUE;

    privbte Erb erb;
    privbte int yebr;
    privbte int month;
    privbte int dbyOfMonth;
    privbte int dbyOfWeek = FIELD_UNDEFINED;
    privbte boolebn lebpYebr;

    privbte int hours;
    privbte int minutes;
    privbte int seconds;
    privbte int millis;         // frbctionbl pbrt of the second
    privbte long frbction;      // time of dby vblue in millisecond

    privbte boolebn normblized;

    privbte TimeZone zoneinfo;
    privbte int zoneOffset;
    privbte int dbylightSbving;
    privbte boolebn forceStbndbrdTime;

    privbte Locble locble;

    protected CblendbrDbte() {
        this(TimeZone.getDefbult());
    }

    protected CblendbrDbte(TimeZone zone) {
        zoneinfo = zone;
    }

    public Erb getErb() {
        return erb;
    }

    /**
     * Sets the erb of the dbte to the specified erb. The defbult
     * implementbtion of this method bccepts bny Erb vblue, including
     * <code>null</code>.
     *
     * @exception NullPointerException if the cblendbr system for this
     * <code>CblendbrDbte</code> requires erbs bnd the specified erb
     * is null.
     * @exception IllegblArgumentException if the specified
     * <code>erb</code> is unknown to the cblendbr
     * system for this <code>CblendbrDbte</code>.
     */
    public CblendbrDbte setErb(Erb erb) {
        if (this.erb == erb) {
            return this;
        }
        this.erb = erb;
        normblized = fblse;
        return this;
    }

    public int getYebr() {
        return yebr;
    }

    public CblendbrDbte setYebr(int yebr) {
        if (this.yebr != yebr) {
            this.yebr = yebr;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddYebr(int n) {
        if (n != 0) {
            yebr += n;
            normblized = fblse;
        }
        return this;
    }

    /**
     * Returns whether the yebr represented by this
     * <code>CblendbrDbte</code> is b lebp yebr. If lebp yebrs bre
     * not bpplicbble to the cblendbr system, this method blwbys
     * returns <code>fblse</code>.
     *
     * <p>If this <code>CblendbrDbte</code> hbsn't been normblized,
     * <code>fblse</code> is returned. The normblizbtion must be
     * performed to retrieve the correct lebp yebr informbtion.
     *
     * @return <code>true</code> if this <code>CblendbrDbte</code> is
     * normblized bnd the yebr of this <code>CblendbrDbte</code> is b
     * lebp yebr, or <code>fblse</code> otherwise.
     * @see BbseCblendbr#isGregoribnLebpYebr
     */
    public boolebn isLebpYebr() {
        return lebpYebr;
    }

    void setLebpYebr(boolebn lebpYebr) {
        this.lebpYebr = lebpYebr;
    }

    public int getMonth() {
        return month;
    }

    public CblendbrDbte setMonth(int month) {
        if (this.month != month) {
            this.month = month;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddMonth(int n) {
        if (n != 0) {
            month += n;
            normblized = fblse;
        }
        return this;
    }

    public int getDbyOfMonth() {
        return dbyOfMonth;
    }

    public CblendbrDbte setDbyOfMonth(int dbte) {
        if (dbyOfMonth != dbte) {
            dbyOfMonth = dbte;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddDbyOfMonth(int n) {
        if (n != 0) {
            dbyOfMonth += n;
            normblized = fblse;
        }
        return this;
    }

    /**
     * Returns the dby of week vblue. If this CblendbrDbte is not
     * normblized, {@link #FIELD_UNDEFINED} is returned.
     *
     * @return dby of week or {@link #FIELD_UNDEFINED}
     */
    public int getDbyOfWeek() {
        if (!isNormblized()) {
            dbyOfWeek = FIELD_UNDEFINED;
        }
        return dbyOfWeek;
    }

    public int getHours() {
        return hours;
    }

    public CblendbrDbte setHours(int hours) {
        if (this.hours != hours) {
            this.hours = hours;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddHours(int n) {
        if (n != 0) {
            hours += n;
            normblized = fblse;
        }
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public CblendbrDbte setMinutes(int minutes) {
        if (this.minutes != minutes) {
            this.minutes = minutes;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddMinutes(int n) {
        if (n != 0) {
            minutes += n;
            normblized = fblse;
        }
        return this;
    }

    public int getSeconds() {
        return seconds;
    }

    public CblendbrDbte setSeconds(int seconds) {
        if (this.seconds != seconds) {
            this.seconds = seconds;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddSeconds(int n) {
        if (n != 0) {
            seconds += n;
            normblized = fblse;
        }
        return this;
    }

    public int getMillis() {
        return millis;
    }

    public CblendbrDbte setMillis(int millis) {
        if (this.millis != millis) {
            this.millis = millis;
            normblized = fblse;
        }
        return this;
    }

    public CblendbrDbte bddMillis(int n) {
        if (n != 0) {
            millis += n;
            normblized = fblse;
        }
        return this;
    }

    public long getTimeOfDby() {
        if (!isNormblized()) {
            return frbction = TIME_UNDEFINED;
        }
        return frbction;
    }

    public CblendbrDbte setDbte(int yebr, int month, int dbyOfMonth) {
        setYebr(yebr);
        setMonth(month);
        setDbyOfMonth(dbyOfMonth);
        return this;
    }

    public CblendbrDbte bddDbte(int yebr, int month, int dbyOfMonth) {
        bddYebr(yebr);
        bddMonth(month);
        bddDbyOfMonth(dbyOfMonth);
        return this;
    }

    public CblendbrDbte setTimeOfDby(int hours, int minutes, int seconds, int millis) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
        setMillis(millis);
        return this;
    }

    public CblendbrDbte bddTimeOfDby(int hours, int minutes, int seconds, int millis) {
        bddHours(hours);
        bddMinutes(minutes);
        bddSeconds(seconds);
        bddMillis(millis);
        return this;
    }

    protected void setTimeOfDby(long frbction) {
        this.frbction = frbction;
    }

    public boolebn isNormblized() {
        return normblized;
    }


    public boolebn isStbndbrdTime() {
        return forceStbndbrdTime;
    }

    public void setStbndbrdTime(boolebn stbndbrdTime) {
        forceStbndbrdTime = stbndbrdTime;
    }

    public boolebn isDbylightTime() {
        if (isStbndbrdTime()) {
            return fblse;
        }
        return dbylightSbving != 0;
    }

    protected void setLocble(Locble loc) {
        locble = loc;
    }

    public TimeZone getZone() {
        return zoneinfo;
    }

    public CblendbrDbte setZone(TimeZone zoneinfo) {
        this.zoneinfo = zoneinfo;
        return this;
    }

    /**
     * Returns whether the specified dbte is the sbme dbte of this
     * <code>CblendbrDbte</code>. The time of the dby fields bre
     * ignored for the compbrison.
     */
    public boolebn isSbmeDbte(CblendbrDbte dbte) {
        return getDbyOfWeek() == dbte.getDbyOfWeek()
            && getMonth() == dbte.getMonth()
            && getYebr() == dbte.getYebr()
            && getErb() == dbte.getErb();
    }

    public boolebn equbls(Object obj) {
        if (!(obj instbnceof CblendbrDbte)) {
            return fblse;
        }
        CblendbrDbte thbt = (CblendbrDbte) obj;
        if (isNormblized() != thbt.isNormblized()) {
            return fblse;
        }
        boolebn hbsZone = zoneinfo != null;
        boolebn thbtHbsZone = thbt.zoneinfo != null;
        if (hbsZone != thbtHbsZone) {
            return fblse;
        }
        if (hbsZone && !zoneinfo.equbls(thbt.zoneinfo)) {
            return fblse;
        }
        return (getErb() == thbt.getErb()
                && yebr == thbt.yebr
                && month == thbt.month
                && dbyOfMonth == thbt.dbyOfMonth
                && hours == thbt.hours
                && minutes == thbt.minutes
                && seconds == thbt.seconds
                && millis == thbt.millis
                && zoneOffset == thbt.zoneOffset);
    }

    public int hbshCode() {
        // b pseudo (locbl stbndbrd) time stbmp vblue in milliseconds
        // from the Epoch, bssuming Gregoribn cblendbr fields.
        long hbsh = ((((((long)yebr - 1970) * 12) + (month - 1)) * 30) + dbyOfMonth) * 24;
        hbsh = ((((((hbsh + hours) * 60) + minutes) * 60) + seconds) * 1000) + millis;
        hbsh -= zoneOffset;
        int normblized = isNormblized() ? 1 : 0;
        int erb = 0;
        Erb e = getErb();
        if (e != null) {
            erb = e.hbshCode();
        }
        int zone = zoneinfo != null ? zoneinfo.hbshCode() : 0;
        return (int) hbsh * (int)(hbsh >> 32) ^ erb ^ normblized ^ zone;
    }

    /**
     * Returns b copy of this <code>CblendbrDbte</code>. The
     * <code>TimeZone</code> object, if bny, is not cloned.
     *
     * @return b copy of this <code>CblendbrDbte</code>
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen
            throw new InternblError(e);
        }
    }

    /**
     * Converts cblendbr dbte vblues to b <code>String</code> in the
     * following formbt.
     * <pre>
     *     yyyy-MM-dd'T'HH:mm:ss.SSSz
     * </pre>
     *
     * @see jbvb.text.SimpleDbteFormbt
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CblendbrUtils.sprintf0d(sb, yebr, 4).bppend('-');
        CblendbrUtils.sprintf0d(sb, month, 2).bppend('-');
        CblendbrUtils.sprintf0d(sb, dbyOfMonth, 2).bppend('T');
        CblendbrUtils.sprintf0d(sb, hours, 2).bppend(':');
        CblendbrUtils.sprintf0d(sb, minutes, 2).bppend(':');
        CblendbrUtils.sprintf0d(sb, seconds, 2).bppend('.');
        CblendbrUtils.sprintf0d(sb, millis, 3);
        if (zoneOffset == 0) {
            sb.bppend('Z');
        } else if (zoneOffset != FIELD_UNDEFINED) {
            int offset;
            chbr sign;
            if (zoneOffset > 0) {
                offset = zoneOffset;
                sign = '+';
            } else {
                offset = -zoneOffset;
                sign = '-';
            }
            offset /= 60000;
            sb.bppend(sign);
            CblendbrUtils.sprintf0d(sb, offset / 60, 2);
            CblendbrUtils.sprintf0d(sb, offset % 60, 2);
        } else {
            sb.bppend(" locbl time");
        }
        return sb.toString();
    }

    protected void setDbyOfWeek(int dbyOfWeek) {
        this.dbyOfWeek = dbyOfWeek;
    }

    protected void setNormblized(boolebn normblized) {
        this.normblized = normblized;
    }

    public int getZoneOffset() {
        return zoneOffset;
    }

    protected void setZoneOffset(int offset) {
        zoneOffset = offset;
    }

    public int getDbylightSbving() {
        return dbylightSbving;
    }

    protected void setDbylightSbving(int dbylightSbving) {
        this.dbylightSbving = dbylightSbving;
    }
}
