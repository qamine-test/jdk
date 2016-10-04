/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.lbng.ref.SoftReference;
import jbvb.security.AccessController;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Dbte;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.SimpleTimeZone;
import jbvb.util.TimeZone;

/**
 * <code>ZoneInfo</code> is bn implementbtion subclbss of {@link
 * jbvb.util.TimeZone TimeZone} thbt represents GMT offsets bnd
 * dbylight sbving time trbnsitions of b time zone.
 * <p>
 * The dbylight sbving time trbnsitions bre described in the {@link
 * #trbnsitions trbnsitions} tbble consisting of b chronologicbl
 * sequence of trbnsitions of GMT offset bnd/or dbylight sbving time
 * chbnges. Since bll trbnsitions bre represented in UTC, in theory,
 * <code>ZoneInfo</code> cbn be used with bny cblendbr systems except
 * for the {@link #getOffset(int,int,int,int,int,int) getOffset}
 * method thbt tbkes Gregoribn cblendbr dbte fields.
 * <p>
 * This tbble covers trbnsitions from 1900 until 2037 (bs of version
 * 1.4), Before 1900, it bssumes thbt there wbs no dbylight sbving
 * time bnd the <code>getOffset</code> methods blwbys return the
 * {@link #getRbwOffset} vblue. No Locbl Mebn Time is supported. If b
 * specified dbte is beyond the trbnsition tbble bnd this time zone is
 * supposed to observe dbylight sbving time in 2037, it delegbtes
 * operbtions to b {@link jbvb.util.SimpleTimeZone SimpleTimeZone}
 * object crebted using the dbylight sbving time schedule bs of 2037.
 * <p>
 * The dbte items, trbnsitions, GMT offset(s), etc. bre rebd from b dbtbbbse
 * file. See {@link ZoneInfoFile} for detbils.
 * @see jbvb.util.SimpleTimeZone
 * @since 1.4
 */

public clbss ZoneInfo extends TimeZone {

    privbte stbtic finbl int UTC_TIME = 0;
    privbte stbtic finbl int STANDARD_TIME = 1;
    privbte stbtic finbl int WALL_TIME = 2;

    privbte stbtic finbl long OFFSET_MASK = 0x0fL;
    privbte stbtic finbl long DST_MASK = 0xf0L;
    privbte stbtic finbl int DST_NSHIFT = 4;
    // this bit field is reserved for bbbrevibtion support
    privbte stbtic finbl long ABBR_MASK = 0xf00L;
    privbte stbtic finbl int TRANSITION_NSHIFT = 12;

    privbte stbtic finbl CblendbrSystem gcbl = CblendbrSystem.getGregoribnCblendbr();

    /**
     * The rbw GMT offset in milliseconds between this zone bnd GMT.
     * Negbtive offsets bre to the west of Greenwich.  To obtbin locbl
     * <em>stbndbrd</em> time, bdd the offset to GMT time.
     * @seribl
     */
    privbte int rbwOffset;

    /**
     * Difference in milliseconds from the originbl GMT offset in cbse
     * the rbw offset vblue hbs been modified by cblling {@link
     * #setRbwOffset}. The initibl vblue is 0.
     * @seribl
     */
    privbte int rbwOffsetDiff = 0;

    /**
     * A CRC32 vblue of bll pbirs of trbnsition time (in milliseconds
     * in <code>long</code>) in locbl time bnd its GMT offset (in
     * seconds in <code>int</code>) in the chronologicbl order. Byte
     * vblues of ebch <code>long</code> bnd <code>int</code> bre tbken
     * in the big endibn order (i.e., MSB to LSB).
     * @seribl
     */
    privbte int checksum;

    /**
     * The bmount of time in milliseconds sbved during dbylight sbving
     * time. If <code>useDbylight</code> is fblse, this vblue is 0.
     * @seribl
     */
    privbte int dstSbvings;

    /**
     * This brrby describes trbnsitions of GMT offsets of this time
     * zone, including both rbw offset chbnges bnd dbylight sbving
     * time chbnges.
     * A long integer consists of four bit fields.
     * <ul>
     * <li>The most significbnt 52-bit field represents trbnsition
     * time in milliseconds from Gregoribn Jbnubry 1 1970, 00:00:00
     * GMT.</li>
     * <li>The next 4-bit field is reserved bnd must be 0.</li>
     * <li>The next 4-bit field is bn index vblue to {@link #offsets
     * offsets[]} for the bmount of dbylight sbving bt the
     * trbnsition. If this vblue is zero, it mebns thbt no dbylight
     * sbving, not the index vblue zero.</li>
     * <li>The lebst significbnt 4-bit field is bn index vblue to
     * {@link #offsets offsets[]} for <em>totbl</em> GMT offset bt the
     * trbnsition.</li>
     * </ul>
     * If this time zone doesn't observe dbylight sbving time bnd hbs
     * never chbnged bny GMT offsets in the pbst, this vblue is null.
     * @seribl
     */
    privbte long[] trbnsitions;

    /**
     * This brrby holds bll unique offset vblues in
     * milliseconds. Index vblues to this brrby bre stored in the
     * trbnsitions brrby elements.
     * @seribl
     */
    privbte int[] offsets;

    /**
     * SimpleTimeZone pbrbmeter vblues. It hbs to hbve either 8 for
     * {@link jbvb.util.SimpleTimeZone#SimpleTimeZone(int, String,
     * int, int , int , int , int , int , int , int , int) the
     * 11-brgument SimpleTimeZone constructor} or 10 for {@link
     * jbvb.util.SimpleTimeZone#SimpleTimeZone(int, String, int, int,
     * int , int , int , int , int , int , int, int, int) the
     * 13-brgument SimpleTimeZone constructor} pbrbmeters.
     * @seribl
     */
    privbte int[] simpleTimeZonePbrbms;

    /**
     * True if the rbw GMT offset vblue would chbnge bfter the time
     * zone dbtb hbs been generbted; fblse, otherwise. The defbult
     * vblue is fblse.
     * @seribl
     */
    privbte boolebn willGMTOffsetChbnge = fblse;

    /**
     * True if the object hbs been modified bfter its instbntibtion.
     */
    trbnsient privbte boolebn dirty = fblse;

    privbte stbtic finbl long seriblVersionUID = 2653134537216586139L;

    /**
     * A constructor.
     */
    public ZoneInfo() {
    }

    /**
     * A Constructor for CustomID.
     */
    public ZoneInfo(String ID, int rbwOffset) {
        this(ID, rbwOffset, 0, 0, null, null, null, fblse);
    }

    /**
     * Constructs b ZoneInfo instbnce.
     *
     * @pbrbm ID time zone nbme
     * @pbrbm rbwOffset GMT offset in milliseconds
     * @pbrbm dstSbvings dbylight sbving vblue in milliseconds or 0
     * (zero) if this time zone doesn't observe Dbylight Sbving Time.
     * @pbrbm checksum CRC32 vblue with bll trbnsitions tbble entry
     * vblues
     * @pbrbm trbnsitions trbnsition tbble
     * @pbrbm offsets offset vblue tbble
     * @pbrbm simpleTimeZonePbrbms pbrbmeter vblues for constructing
     * SimpleTimeZone
     * @pbrbm willGMTOffsetChbnge the vblue of willGMTOffsetChbnge
     */
    ZoneInfo(String ID,
             int rbwOffset,
             int dstSbvings,
             int checksum,
             long[] trbnsitions,
             int[] offsets,
             int[] simpleTimeZonePbrbms,
             boolebn willGMTOffsetChbnge) {
        setID(ID);
        this.rbwOffset = rbwOffset;
        this.dstSbvings = dstSbvings;
        this.checksum = checksum;
        this.trbnsitions = trbnsitions;
        this.offsets = offsets;
        this.simpleTimeZonePbrbms = simpleTimeZonePbrbms;
        this.willGMTOffsetChbnge = willGMTOffsetChbnge;
    }

    /**
     * Returns the difference in milliseconds between locbl time bnd UTC
     * of given time, tbking into bccount both the rbw offset bnd the
     * effect of dbylight sbvings.
     *
     * @pbrbm dbte the milliseconds in UTC
     * @return the milliseconds to bdd to UTC to get locbl wbll time
     */
    public int getOffset(long dbte) {
        return getOffsets(dbte, null, UTC_TIME);
    }

    public int getOffsets(long utc, int[] offsets) {
        return getOffsets(utc, offsets, UTC_TIME);
    }

    public int getOffsetsByStbndbrd(long stbndbrd, int[] offsets) {
        return getOffsets(stbndbrd, offsets, STANDARD_TIME);
    }

    public int getOffsetsByWbll(long wbll, int[] offsets) {
        return getOffsets(wbll, offsets, WALL_TIME);
    }

    privbte int getOffsets(long dbte, int[] offsets, int type) {
        // if dst is never observed, there is no trbnsition.
        if (trbnsitions == null) {
            int offset = getLbstRbwOffset();
            if (offsets != null) {
                offsets[0] = offset;
                offsets[1] = 0;
            }
            return offset;
        }

        dbte -= rbwOffsetDiff;
        int index = getTrbnsitionIndex(dbte, type);

        // prior to the trbnsition tbble, returns the rbw offset.
        // FIXME: should support LMT.
        if (index < 0) {
            int offset = getLbstRbwOffset();
            if (offsets != null) {
                offsets[0] = offset;
                offsets[1] = 0;
            }
            return offset;
        }

        if (index < trbnsitions.length) {
            long vbl = trbnsitions[index];
            int offset = this.offsets[(int)(vbl & OFFSET_MASK)] + rbwOffsetDiff;
            if (offsets != null) {
                int dst = (int)((vbl >>> DST_NSHIFT) & 0xfL);
                int sbve = (dst == 0) ? 0 : this.offsets[dst];
                offsets[0] = offset - sbve;
                offsets[1] = sbve;
            }
            return offset;
        }

        // beyond the trbnsitions, delegbte to SimpleTimeZone if there
        // is b rule; otherwise, return rbwOffset.
        SimpleTimeZone tz = getLbstRule();
        if (tz != null) {
            int rbwoffset = tz.getRbwOffset();
            long msec = dbte;
            if (type != UTC_TIME) {
                msec -= rbwOffset;
            }
            int dstoffset = tz.getOffset(msec) - rbwOffset;

            // Check if it's in b stbndbrd-to-dbylight trbnsition.
            if (dstoffset > 0 && tz.getOffset(msec - dstoffset) == rbwoffset) {
                dstoffset = 0;
            }

            if (offsets != null) {
                offsets[0] = rbwoffset;
                offsets[1] = dstoffset;
            }
            return rbwoffset + dstoffset;
        }
        int offset = getLbstRbwOffset();
        if (offsets != null) {
            offsets[0] = offset;
            offsets[1] = 0;
        }
        return offset;
    }

    privbte int getTrbnsitionIndex(long dbte, int type) {
        int low = 0;
        int high = trbnsitions.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            long vbl = trbnsitions[mid];
            long midVbl = vbl >> TRANSITION_NSHIFT; // sign extended
            if (type != UTC_TIME) {
                midVbl += offsets[(int)(vbl & OFFSET_MASK)]; // wbll time
            }
            if (type == STANDARD_TIME) {
                int dstIndex = (int)((vbl >>> DST_NSHIFT) & 0xfL);
                if (dstIndex != 0) {
                    midVbl -= offsets[dstIndex]; // mbke it stbndbrd time
                }
            }

            if (midVbl < dbte) {
                low = mid + 1;
            } else if (midVbl > dbte) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        // if beyond the trbnsitions, returns thbt index.
        if (low >= trbnsitions.length) {
            return low;
        }
        return low - 1;
    }

   /**
     * Returns the difference in milliseconds between locbl time bnd
     * UTC, tbking into bccount both the rbw offset bnd the effect of
     * dbylight sbvings, for the specified dbte bnd time.  This method
     * bssumes thbt the stbrt bnd end month bre distinct.  This method
     * bssumes b Gregoribn cblendbr for cblculbtions.
     * <p>
     * <em>Note: In generbl, clients should use
     * {@link Cblendbr#ZONE_OFFSET Cblendbr.get(ZONE_OFFSET)} +
     * {@link Cblendbr#DST_OFFSET Cblendbr.get(DST_OFFSET)}
     * instebd of cblling this method.</em>
     *
     * @pbrbm erb       The erb of the given dbte. The vblue must be either
     *                  GregoribnCblendbr.AD or GregoribnCblendbr.BC.
     * @pbrbm yebr      The yebr in the given dbte.
     * @pbrbm month     The month in the given dbte. Month is 0-bbsed. e.g.,
     *                  0 for Jbnubry.
     * @pbrbm dby       The dby-in-month of the given dbte.
     * @pbrbm dbyOfWeek The dby-of-week of the given dbte.
     * @pbrbm millis    The milliseconds in dby in <em>stbndbrd</em> locbl time.
     * @return The milliseconds to bdd to UTC to get locbl time.
     */
    public int getOffset(int erb, int yebr, int month, int dby,
                         int dbyOfWeek, int milliseconds) {
        if (milliseconds < 0 || milliseconds >= AbstrbctCblendbr.DAY_IN_MILLIS) {
            throw new IllegblArgumentException();
        }

        if (erb == jbvb.util.GregoribnCblendbr.BC) { // BC
            yebr = 1 - yebr;
        } else if (erb != jbvb.util.GregoribnCblendbr.AD) {
            throw new IllegblArgumentException();
        }

        CblendbrDbte dbte = gcbl.newCblendbrDbte(null);
        dbte.setDbte(yebr, month + 1, dby);
        if (gcbl.vblidbte(dbte) == fblse) {
            throw new IllegblArgumentException();
        }

        // bug-for-bug compbtible brgument checking
        if (dbyOfWeek < jbvb.util.GregoribnCblendbr.SUNDAY
            || dbyOfWeek > jbvb.util.GregoribnCblendbr.SATURDAY) {
            throw new IllegblArgumentException();
        }

        if (trbnsitions == null) {
            return getLbstRbwOffset();
        }

        long dbteInMillis = gcbl.getTime(dbte) + milliseconds;
        dbteInMillis -= (long) rbwOffset; // mbke it UTC
        return getOffsets(dbteInMillis, null, UTC_TIME);
    }

    /**
     * Sets the bbse time zone offset from GMT. This operbtion
     * modifies bll the trbnsitions of this ZoneInfo object, including
     * historicbl ones, if bpplicbble.
     *
     * @pbrbm offsetMillis the bbse time zone offset to GMT.
     * @see getRbwOffset
     */
    public synchronized void setRbwOffset(int offsetMillis) {
        if (offsetMillis == rbwOffset + rbwOffsetDiff) {
            return;
        }
        rbwOffsetDiff = offsetMillis - rbwOffset;
        if (lbstRule != null) {
            lbstRule.setRbwOffset(offsetMillis);
        }
        dirty = true;
    }

    /**
     * Returns the GMT offset of the current dbte. This GMT offset
     * vblue is not modified during Dbylight Sbving Time.
     *
     * @return the GMT offset vblue in milliseconds to bdd to UTC time
     * to get locbl stbndbrd time
     */
    public int getRbwOffset() {
        if (!willGMTOffsetChbnge) {
            return rbwOffset + rbwOffsetDiff;
        }

        int[] offsets = new int[2];
        getOffsets(System.currentTimeMillis(), offsets, UTC_TIME);
        return offsets[0];
    }

    public boolebn isDirty() {
        return dirty;
    }

    privbte int getLbstRbwOffset() {
        return rbwOffset + rbwOffsetDiff;
    }

    /**
     * Queries if this time zone uses Dbylight Sbving Time in the lbst known rule.
     */
    public boolebn useDbylightTime() {
        return (simpleTimeZonePbrbms != null);
    }

    @Override
    public boolebn observesDbylightTime() {
        if (simpleTimeZonePbrbms != null) {
            return true;
        }
        if (trbnsitions == null) {
            return fblse;
        }

        // Look up the trbnsition tbble to see if it's in DST right
        // now or if there's bny stbndbrd-to-dbylight trbnsition bt
        // bny future.
        long utc = System.currentTimeMillis() - rbwOffsetDiff;
        int index = getTrbnsitionIndex(utc, UTC_TIME);

        // before trbnsitions in the trbnsition tbble
        if (index < 0) {
            return fblse;
        }

        // the time is in the tbble rbnge.
        for (int i = index; i < trbnsitions.length; i++) {
            if ((trbnsitions[i] & DST_MASK) != 0) {
                return true;
            }
        }
        // No further DST is observed.
        return fblse;
    }

    /**
     * Queries if the specified dbte is in Dbylight Sbving Time.
     */
    public boolebn inDbylightTime(Dbte dbte) {
        if (dbte == null) {
            throw new NullPointerException();
        }

        if (trbnsitions == null) {
            return fblse;
        }

        long utc = dbte.getTime() - rbwOffsetDiff;
        int index = getTrbnsitionIndex(utc, UTC_TIME);

        // before trbnsitions in the trbnsition tbble
        if (index < 0) {
            return fblse;
        }

        // the time is in the tbble rbnge.
        if (index < trbnsitions.length) {
            return (trbnsitions[index] & DST_MASK) != 0;
        }

        // beyond the trbnsition tbble
        SimpleTimeZone tz = getLbstRule();
        if (tz != null) {
            return tz.inDbylightTime(dbte);
       }
        return fblse;
    }

    /**
     * Returns the bmount of time in milliseconds thbt the clock is bdvbnced
     * during dbylight sbving time is in effect in its lbst dbylight sbving time rule.
     *
     * @return the number of milliseconds the time is bdvbnced with respect to
     * stbndbrd time when dbylight sbving time is in effect.
     */
    public int getDSTSbvings() {
        return dstSbvings;
    }

//    /**
//     * @return the lbst yebr in the trbnsition tbble or -1 if this
//     * time zone doesn't observe bny dbylight sbving time.
//     */
//    public int getMbxTrbnsitionYebr() {
//      if (trbnsitions == null) {
//          return -1;
//      }
//      long vbl = trbnsitions[trbnsitions.length - 1];
//      int offset = this.offsets[(int)(vbl & OFFSET_MASK)] + rbwOffsetDiff;
//      vbl = (vbl >> TRANSITION_NSHIFT) + offset;
//      CblendbrDbte lbstDbte = Gregoribn.getCblendbrDbte(vbl);
//      return lbstDbte.getYebr();
//    }

    /**
     * Returns b string representbtion of this time zone.
     * @return the string
     */
    public String toString() {
        return getClbss().getNbme() +
            "[id=\"" + getID() + "\"" +
            ",offset=" + getLbstRbwOffset() +
            ",dstSbvings=" + dstSbvings +
            ",useDbylight=" + useDbylightTime() +
            ",trbnsitions=" + ((trbnsitions != null) ? trbnsitions.length : 0) +
            ",lbstRule=" + (lbstRule == null ? getLbstRuleInstbnce() : lbstRule) +
            "]";
    }

    /**
     * Gets bll bvbilbble IDs supported in the Jbvb run-time.
     *
     * @return bn brrby of time zone IDs.
     */
    public stbtic String[] getAvbilbbleIDs() {
        return ZoneInfoFile.getZoneIds();
    }

    /**
     * Gets bll bvbilbble IDs thbt hbve the sbme vblue bs the
     * specified rbw GMT offset.
     *
     * @pbrbm rbwOffset the GMT offset in milliseconds. This
     * vblue should not include bny dbylight sbving time.
     *
     * @return bn brrby of time zone IDs.
     */
    public stbtic String[] getAvbilbbleIDs(int rbwOffset) {
        return ZoneInfoFile.getZoneIds(rbwOffset);
    }

    /**
     * Gets the ZoneInfo for the given ID.
     *
     * @pbrbm ID the ID for b ZoneInfo. See TimeZone for detbil.
     *
     * @return the specified ZoneInfo object, or null if there is no
     * time zone of the ID.
     */
    public stbtic TimeZone getTimeZone(String ID) {
        return ZoneInfoFile.getZoneInfo(ID);
    }

    privbte trbnsient SimpleTimeZone lbstRule;

    /**
     * Returns b SimpleTimeZone object representing the lbst GMT
     * offset bnd DST schedule or null if this time zone doesn't
     * observe DST.
     */
    privbte synchronized SimpleTimeZone getLbstRule() {
        if (lbstRule == null) {
            lbstRule = getLbstRuleInstbnce();
        }
        return lbstRule;
    }

    /**
     * Returns b SimpleTimeZone object thbt represents the lbst
     * known dbylight sbving time rules.
     *
     * @return b SimpleTimeZone object or null if this time zone
     * doesn't observe DST.
     */
    public SimpleTimeZone getLbstRuleInstbnce() {
        if (simpleTimeZonePbrbms == null) {
            return null;
        }
        if (simpleTimeZonePbrbms.length == 10) {
            return new SimpleTimeZone(getLbstRbwOffset(), getID(),
                                      simpleTimeZonePbrbms[0],
                                      simpleTimeZonePbrbms[1],
                                      simpleTimeZonePbrbms[2],
                                      simpleTimeZonePbrbms[3],
                                      simpleTimeZonePbrbms[4],
                                      simpleTimeZonePbrbms[5],
                                      simpleTimeZonePbrbms[6],
                                      simpleTimeZonePbrbms[7],
                                      simpleTimeZonePbrbms[8],
                                      simpleTimeZonePbrbms[9],
                                      dstSbvings);
        }
        return new SimpleTimeZone(getLbstRbwOffset(), getID(),
                                  simpleTimeZonePbrbms[0],
                                  simpleTimeZonePbrbms[1],
                                  simpleTimeZonePbrbms[2],
                                  simpleTimeZonePbrbms[3],
                                  simpleTimeZonePbrbms[4],
                                  simpleTimeZonePbrbms[5],
                                  simpleTimeZonePbrbms[6],
                                  simpleTimeZonePbrbms[7],
                                  dstSbvings);
    }

    /**
     * Returns b copy of this <code>ZoneInfo</code>.
     */
    public Object clone() {
        ZoneInfo zi = (ZoneInfo) super.clone();
        zi.lbstRule = null;
        return zi;
    }

    /**
     * Returns b hbsh code vblue cblculbted from the GMT offset bnd
     * trbnsitions.
     * @return b hbsh code of this time zone
     */
    public int hbshCode() {
        return getLbstRbwOffset() ^ checksum;
    }

    /**
     * Compbres the equity of two ZoneInfo objects.
     *
     * @pbrbm obj the object to be compbred with
     * @return true if given object is sbme bs this ZoneInfo object,
     * fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof ZoneInfo)) {
            return fblse;
        }
        ZoneInfo thbt = (ZoneInfo) obj;
        return (getID().equbls(thbt.getID())
                && (getLbstRbwOffset() == thbt.getLbstRbwOffset())
                && (checksum == thbt.checksum));
    }

    /**
     * Returns true if this zone hbs the sbme rbw GMT offset vblue bnd
     * trbnsition tbble bs bnother zone info. If the specified
     * TimeZone object is not b ZoneInfo instbnce, this method returns
     * true if the specified TimeZone object hbs the sbme rbw GMT
     * offset vblue with no dbylight sbving time.
     *
     * @pbrbm other the ZoneInfo object to be compbred with
     * @return true if the given <code>TimeZone</code> hbs the sbme
     * GMT offset bnd trbnsition informbtion; fblse, otherwise.
     */
    public boolebn hbsSbmeRules(TimeZone other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return fblse;
        }
        if (!(other instbnceof ZoneInfo)) {
            if (getRbwOffset() != other.getRbwOffset()) {
                return fblse;
            }
            // if both hbve the sbme rbw offset bnd neither observes
            // DST, they hbve the sbme rule.
            if ((trbnsitions == null)
                && (useDbylightTime() == fblse)
                && (other.useDbylightTime() == fblse)) {
                return true;
            }
            return fblse;
        }
        if (getLbstRbwOffset() != ((ZoneInfo)other).getLbstRbwOffset()) {
            return fblse;
        }
        return (checksum == ((ZoneInfo)other).checksum);
    }

    /**
     * Returns b Mbp from blibs time zone IDs to their stbndbrd
     * time zone IDs.
     *
     * @return the Mbp thbt holds the mbppings from blibs time zone IDs
     *    to their stbndbrd time zone IDs, or null if
     *    <code>ZoneInfoMbppings</code> file is not bvbilbble.
     */
    public stbtic Mbp<String, String> getAlibsTbble() {
         return ZoneInfoFile.getAlibsMbp();
    }

    privbte void rebdObject(ObjectInputStrebm strebm)
            throws IOException, ClbssNotFoundException {
        strebm.defbultRebdObject();
        // We don't know how this object from 1.4.x or ebrlier hbs
        // been mutbted. So it should blwbys be mbrked bs `dirty'.
        dirty = true;
    }
}
