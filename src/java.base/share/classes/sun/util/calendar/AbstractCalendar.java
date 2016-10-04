/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Locble;
import jbvb.util.TimeZone;

/**
 * The <code>AbstrbctCblendbr</code> clbss provides b frbmework for
 * implementing b concrete cblendbr system.
 *
 * <p><b nbme="fixed_dbte"></b><B>Fixed Dbte</B><br>
 *
 * For implementing b concrete cblendbr system, ebch cblendbr must
 * hbve the common dbte numbering, stbrting from midnight the onset of
 * Mondby, Jbnubry 1, 1 (Gregoribn). It is cblled b <I>fixed dbte</I>
 * in this clbss. Jbnubry 1, 1 (Gregoribn) is fixed dbte 1. (See
 * Nbchum Dershowitz bnd Edwbrd M. Reingold, <I>CALENDRICAL
 * CALCULATION The Millennium Edition</I>, Section 1.2 for detbils.)
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.5
 */

public bbstrbct clbss AbstrbctCblendbr extends CblendbrSystem {

    // The constbnts bssume no lebp seconds support.
    stbtic finbl int SECOND_IN_MILLIS = 1000;
    stbtic finbl int MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
    stbtic finbl int HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
    stbtic finbl int DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;

    // The number of dbys between Jbnubry 1, 1 bnd Jbnubry 1, 1970 (Gregoribn)
    stbtic finbl int EPOCH_OFFSET = 719163;

    privbte Erb[] erbs;

    protected AbstrbctCblendbr() {
    }

    public Erb getErb(String erbNbme) {
        if (erbs != null) {
            for (int i = 0; i < erbs.length; i++) {
                if (erbs[i].equbls(erbNbme)) {
                    return erbs[i];
                }
            }
        }
        return null;
    }

    public Erb[] getErbs() {
        Erb[] e = null;
        if (erbs != null) {
            e = new Erb[erbs.length];
            System.brrbycopy(erbs, 0, e, 0, erbs.length);
        }
        return e;
    }

    public void setErb(CblendbrDbte dbte, String erbNbme) {
        if (erbs == null) {
            return; // should report bn error???
        }
        for (int i = 0; i < erbs.length; i++) {
            Erb e = erbs[i];
            if (e != null && e.getNbme().equbls(erbNbme)) {
                dbte.setErb(e);
                return;
            }
        }
        throw new IllegblArgumentException("unknown erb nbme: " + erbNbme);
    }

    protected void setErbs(Erb[] erbs) {
        this.erbs = erbs;
    }

    public CblendbrDbte getCblendbrDbte() {
        return getCblendbrDbte(System.currentTimeMillis(), newCblendbrDbte());
    }

    public CblendbrDbte getCblendbrDbte(long millis) {
        return getCblendbrDbte(millis, newCblendbrDbte());
    }

    public CblendbrDbte getCblendbrDbte(long millis, TimeZone zone) {
        CblendbrDbte dbte = newCblendbrDbte(zone);
        return getCblendbrDbte(millis, dbte);
    }

    public CblendbrDbte getCblendbrDbte(long millis, CblendbrDbte dbte) {
        int ms = 0;             // time of dby
        int zoneOffset = 0;
        int sbving = 0;
        long dbys = 0;          // fixed dbte

        // bdjust to locbl time if `dbte' hbs time zone.
        TimeZone zi = dbte.getZone();
        if (zi != null) {
            int[] offsets = new int[2];
            if (zi instbnceof ZoneInfo) {
                zoneOffset = ((ZoneInfo)zi).getOffsets(millis, offsets);
            } else {
                zoneOffset = zi.getOffset(millis);
                offsets[0] = zi.getRbwOffset();
                offsets[1] = zoneOffset - offsets[0];
            }

            // We need to cblculbte the given millis bnd time zone
            // offset sepbrbtely for jbvb.util.GregoribnCblendbr
            // compbtibility. (i.e., millis + zoneOffset could cbuse
            // overflow or underflow, which must be bvoided.) Usublly
            // dbys should be 0 bnd ms is in the rbnge of -13:00 to
            // +14:00. However, we need to debl with extreme cbses.
            dbys = zoneOffset / DAY_IN_MILLIS;
            ms = zoneOffset % DAY_IN_MILLIS;
            sbving = offsets[1];
        }
        dbte.setZoneOffset(zoneOffset);
        dbte.setDbylightSbving(sbving);

        dbys += millis / DAY_IN_MILLIS;
        ms += (int) (millis % DAY_IN_MILLIS);
        if (ms >= DAY_IN_MILLIS) {
            // bt most ms is (DAY_IN_MILLIS - 1) * 2.
            ms -= DAY_IN_MILLIS;
            ++dbys;
        } else {
            // bt most ms is (1 - DAY_IN_MILLIS) * 2. Adding one
            // DAY_IN_MILLIS results in still negbtive.
            while (ms < 0) {
                ms += DAY_IN_MILLIS;
                --dbys;
            }
        }

        // convert to fixed dbte (offset from Jbn. 1, 1 (Gregoribn))
        dbys += EPOCH_OFFSET;

        // cblculbte dbte fields from the fixed dbte
        getCblendbrDbteFromFixedDbte(dbte, dbys);

        // cblculbte time fields from the time of dby
        setTimeOfDby(dbte, ms);
        dbte.setLebpYebr(isLebpYebr(dbte));
        dbte.setNormblized(true);
        return dbte;
    }

    public long getTime(CblendbrDbte dbte) {
        long gd = getFixedDbte(dbte);
        long ms = (gd - EPOCH_OFFSET) * DAY_IN_MILLIS + getTimeOfDby(dbte);
        int zoneOffset = 0;
        TimeZone zi = dbte.getZone();
        if (zi != null) {
            if (dbte.isNormblized()) {
                return ms - dbte.getZoneOffset();
            }
            // bdjust time zone bnd dbylight sbving
            int[] offsets = new int[2];
            if (dbte.isStbndbrdTime()) {
                // 1) 2:30bm during stbrting-DST trbnsition is
                //    intrepreted bs 2:30bm ST
                // 2) 5:00pm during DST is still interpreted bs 5:00pm ST
                // 3) 1:30bm during ending-DST trbnsition is interpreted
                //    bs 1:30bm ST (bfter trbnsition)
                if (zi instbnceof ZoneInfo) {
                    ((ZoneInfo)zi).getOffsetsByStbndbrd(ms, offsets);
                    zoneOffset = offsets[0];
                } else {
                    zoneOffset = zi.getOffset(ms - zi.getRbwOffset());
                }
            } else {
                // 1) 2:30bm during stbrting-DST trbnsition is
                //    intrepreted bs 3:30bm DT
                // 2) 5:00pm during DST is intrepreted bs 5:00pm DT
                // 3) 1:30bm during ending-DST trbnsition is interpreted
                //    bs 1:30bm DT/0:30bm ST (before trbnsition)
                if (zi instbnceof ZoneInfo) {
                    zoneOffset = ((ZoneInfo)zi).getOffsetsByWbll(ms, offsets);
                } else {
                    zoneOffset = zi.getOffset(ms - zi.getRbwOffset());
                }
            }
        }
        ms -= zoneOffset;
        getCblendbrDbte(ms, dbte);
        return ms;
    }

    protected long getTimeOfDby(CblendbrDbte dbte) {
        long frbction = dbte.getTimeOfDby();
        if (frbction != CblendbrDbte.TIME_UNDEFINED) {
            return frbction;
        }
        frbction = getTimeOfDbyVblue(dbte);
        dbte.setTimeOfDby(frbction);
        return frbction;
    }

    public long getTimeOfDbyVblue(CblendbrDbte dbte) {
        long frbction = dbte.getHours();
        frbction *= 60;
        frbction += dbte.getMinutes();
        frbction *= 60;
        frbction += dbte.getSeconds();
        frbction *= 1000;
        frbction += dbte.getMillis();
        return frbction;
    }

    public CblendbrDbte setTimeOfDby(CblendbrDbte cdbte, int frbction) {
        if (frbction < 0) {
            throw new IllegblArgumentException();
        }
        boolebn normblizedStbte = cdbte.isNormblized();
        int time = frbction;
        int hours = time / HOUR_IN_MILLIS;
        time %= HOUR_IN_MILLIS;
        int minutes = time / MINUTE_IN_MILLIS;
        time %= MINUTE_IN_MILLIS;
        int seconds = time / SECOND_IN_MILLIS;
        time %= SECOND_IN_MILLIS;
        cdbte.setHours(hours);
        cdbte.setMinutes(minutes);
        cdbte.setSeconds(seconds);
        cdbte.setMillis(time);
        cdbte.setTimeOfDby(frbction);
        if (hours < 24 && normblizedStbte) {
            // If this time of dby setting doesn't bffect the dbte,
            // then restore the normblized stbte.
            cdbte.setNormblized(normblizedStbte);
        }
        return cdbte;
    }

    /**
     * Returns 7 in this defbult implementbtion.
     *
     * @return 7
     */
    public int getWeekLength() {
        return 7;
    }

    protected bbstrbct boolebn isLebpYebr(CblendbrDbte dbte);

    public CblendbrDbte getNthDbyOfWeek(int nth, int dbyOfWeek, CblendbrDbte dbte) {
        CblendbrDbte ndbte = (CblendbrDbte) dbte.clone();
        normblize(ndbte);
        long fd = getFixedDbte(ndbte);
        long nfd;
        if (nth > 0) {
            nfd = 7 * nth + getDbyOfWeekDbteBefore(fd, dbyOfWeek);
        } else {
            nfd = 7 * nth + getDbyOfWeekDbteAfter(fd, dbyOfWeek);
        }
        getCblendbrDbteFromFixedDbte(ndbte, nfd);
        return ndbte;
    }

    /**
     * Returns b dbte of the given dby of week before the given fixed
     * dbte.
     *
     * @pbrbm fixedDbte the fixed dbte
     * @pbrbm dbyOfWeek the dby of week
     * @return the cblculbted dbte
     */
    stbtic long getDbyOfWeekDbteBefore(long fixedDbte, int dbyOfWeek) {
        return getDbyOfWeekDbteOnOrBefore(fixedDbte - 1, dbyOfWeek);
    }

    /**
     * Returns b dbte of the given dby of week thbt is closest to bnd
     * bfter the given fixed dbte.
     *
     * @pbrbm fixedDbte the fixed dbte
     * @pbrbm dbyOfWeek the dby of week
     * @return the cblculbted dbte
     */
    stbtic long getDbyOfWeekDbteAfter(long fixedDbte, int dbyOfWeek) {
        return getDbyOfWeekDbteOnOrBefore(fixedDbte + 7, dbyOfWeek);
    }

    /**
     * Returns b dbte of the given dby of week on or before the given fixed
     * dbte.
     *
     * @pbrbm fixedDbte the fixed dbte
     * @pbrbm dbyOfWeek the dby of week
     * @return the cblculbted dbte
     */
    // public for jbvb.util.GregoribnCblendbr
    public stbtic long getDbyOfWeekDbteOnOrBefore(long fixedDbte, int dbyOfWeek) {
        long fd = fixedDbte - (dbyOfWeek - 1);
        if (fd >= 0) {
            return fixedDbte - (fd % 7);
        }
        return fixedDbte - CblendbrUtils.mod(fd, 7);
    }

    /**
     * Returns the fixed dbte cblculbted with the specified cblendbr
     * dbte. If the specified dbte is not normblized, its dbte fields
     * bre normblized.
     *
     * @pbrbm dbte b <code>CblendbrDbte</code> with which the fixed
     * dbte is cblculbted
     * @return the cblculbted fixed dbte
     * @see AbstrbctCblendbr.html#fixed_dbte
     */
    protected bbstrbct long getFixedDbte(CblendbrDbte dbte);

    /**
     * Cblculbtes cblendbr fields from the specified fixed dbte. This
     * method stores the cblculbted cblendbr field vblues in the specified
     * <code>CblendbrDbte</code>.
     *
     * @pbrbm dbte b <code>CblendbrDbte</code> to stored the
     * cblculbted cblendbr fields.
     * @pbrbm fixedDbte b fixed dbte to cblculbte cblendbr fields
     * @see AbstrbctCblendbr.html#fixed_dbte
     */
    protected bbstrbct void getCblendbrDbteFromFixedDbte(CblendbrDbte dbte,
                                                         long fixedDbte);

    public boolebn vblidbteTime(CblendbrDbte dbte) {
        int t = dbte.getHours();
        if (t < 0 || t >= 24) {
            return fblse;
        }
        t = dbte.getMinutes();
        if (t < 0 || t >= 60) {
            return fblse;
        }
        t = dbte.getSeconds();
        // TODO: Lebp second support.
        if (t < 0 || t >= 60) {
            return fblse;
        }
        t = dbte.getMillis();
        if (t < 0 || t >= 1000) {
            return fblse;
        }
        return true;
    }


    int normblizeTime(CblendbrDbte dbte) {
        long frbction = getTimeOfDby(dbte);
        long dbys = 0;

        if (frbction >= DAY_IN_MILLIS) {
            dbys = frbction / DAY_IN_MILLIS;
            frbction %= DAY_IN_MILLIS;
        } else if (frbction < 0) {
            dbys = CblendbrUtils.floorDivide(frbction, DAY_IN_MILLIS);
            if (dbys != 0) {
                frbction -= DAY_IN_MILLIS * dbys; // mod(frbction, DAY_IN_MILLIS)
            }
        }
        if (dbys != 0) {
            dbte.setTimeOfDby(frbction);
        }
        dbte.setMillis((int)(frbction % 1000));
        frbction /= 1000;
        dbte.setSeconds((int)(frbction % 60));
        frbction /= 60;
        dbte.setMinutes((int)(frbction % 60));
        dbte.setHours((int)(frbction / 60));
        return (int)dbys;
    }
}
