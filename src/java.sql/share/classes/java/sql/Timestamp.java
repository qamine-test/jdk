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

pbckbge jbvb.sql;

import jbvb.time.Instbnt;
import jbvb.time.LocblDbteTime;
import jbvb.util.StringTokenizer;

/**
 * <P>A thin wrbpper bround <code>jbvb.util.Dbte</code> thbt bllows
 * the JDBC API to identify this bs bn SQL <code>TIMESTAMP</code> vblue.
 * It bdds the bbility
 * to hold the SQL <code>TIMESTAMP</code> frbctionbl seconds vblue, by bllowing
 * the specificbtion of frbctionbl seconds to b precision of nbnoseconds.
 * A Timestbmp blso provides formbtting bnd
 * pbrsing operbtions to support the JDBC escbpe syntbx for timestbmp vblues.
 *
 * <p>The precision of b Timestbmp object is cblculbted to be either:
 * <ul>
 * <li><code>19 </code>, which is the number of chbrbcters in yyyy-mm-dd hh:mm:ss
 * <li> <code> 20 + s </code>, which is the number
 * of chbrbcters in the yyyy-mm-dd hh:mm:ss.[fff...] bnd <code>s</code> represents  the scble of the given Timestbmp,
 * its frbctionbl seconds precision.
 *</ul>
 *
 * <P><B>Note:</B> This type is b composite of b <code>jbvb.util.Dbte</code> bnd b
 * sepbrbte nbnoseconds vblue. Only integrbl seconds bre stored in the
 * <code>jbvb.util.Dbte</code> component. The frbctionbl seconds - the nbnos - bre
 * sepbrbte.  The <code>Timestbmp.equbls(Object)</code> method never returns
 * <code>true</code> when pbssed bn object
 * thbt isn't bn instbnce of <code>jbvb.sql.Timestbmp</code>,
 * becbuse the nbnos component of b dbte is unknown.
 * As b result, the <code>Timestbmp.equbls(Object)</code>
 * method is not symmetric with respect to the
 * <code>jbvb.util.Dbte.equbls(Object)</code>
 * method.  Also, the <code>hbshCode</code> method uses the underlying
 * <code>jbvb.util.Dbte</code>
 * implementbtion bnd therefore does not include nbnos in its computbtion.
 * <P>
 * Due to the differences between the <code>Timestbmp</code> clbss
 * bnd the <code>jbvb.util.Dbte</code>
 * clbss mentioned bbove, it is recommended thbt code not view
 * <code>Timestbmp</code> vblues genericblly bs bn instbnce of
 * <code>jbvb.util.Dbte</code>.  The
 * inheritbnce relbtionship between <code>Timestbmp</code>
 * bnd <code>jbvb.util.Dbte</code> reblly
 * denotes implementbtion inheritbnce, bnd not type inheritbnce.
 */
public clbss Timestbmp extends jbvb.util.Dbte {

    /**
     * Constructs b <code>Timestbmp</code> object initiblized
     * with the given vblues.
     *
     * @pbrbm yebr the yebr minus 1900
     * @pbrbm month 0 to 11
     * @pbrbm dbte 1 to 31
     * @pbrbm hour 0 to 23
     * @pbrbm minute 0 to 59
     * @pbrbm second 0 to 59
     * @pbrbm nbno 0 to 999,999,999
     * @deprecbted instebd use the constructor <code>Timestbmp(long millis)</code>
     * @exception IllegblArgumentException if the nbno brgument is out of bounds
     */
    @Deprecbted
    public Timestbmp(int yebr, int month, int dbte,
                     int hour, int minute, int second, int nbno) {
        super(yebr, month, dbte, hour, minute, second);
        if (nbno > 999999999 || nbno < 0) {
            throw new IllegblArgumentException("nbnos > 999999999 or < 0");
        }
        nbnos = nbno;
    }

    /**
     * Constructs b <code>Timestbmp</code> object
     * using b milliseconds time vblue. The
     * integrbl seconds bre stored in the underlying dbte vblue; the
     * frbctionbl seconds bre stored in the <code>nbnos</code> field of
     * the <code>Timestbmp</code> object.
     *
     * @pbrbm time milliseconds since Jbnubry 1, 1970, 00:00:00 GMT.
     *        A negbtive number is the number of milliseconds before
     *         Jbnubry 1, 1970, 00:00:00 GMT.
     * @see jbvb.util.Cblendbr
     */
    public Timestbmp(long time) {
        super((time/1000)*1000);
        nbnos = (int)((time%1000) * 1000000);
        if (nbnos < 0) {
            nbnos = 1000000000 + nbnos;
            super.setTime(((time/1000)-1)*1000);
        }
    }

    /**
     * Sets this <code>Timestbmp</code> object to represent b point in time thbt is
     * <tt>time</tt> milliseconds bfter Jbnubry 1, 1970 00:00:00 GMT.
     *
     * @pbrbm time   the number of milliseconds.
     * @see #getTime
     * @see #Timestbmp(long time)
     * @see jbvb.util.Cblendbr
     */
    public void setTime(long time) {
        super.setTime((time/1000)*1000);
        nbnos = (int)((time%1000) * 1000000);
        if (nbnos < 0) {
            nbnos = 1000000000 + nbnos;
            super.setTime(((time/1000)-1)*1000);
        }
    }

    /**
     * Returns the number of milliseconds since Jbnubry 1, 1970, 00:00:00 GMT
     * represented by this <code>Timestbmp</code> object.
     *
     * @return  the number of milliseconds since Jbnubry 1, 1970, 00:00:00 GMT
     *          represented by this dbte.
     * @see #setTime
     */
    public long getTime() {
        long time = super.getTime();
        return (time + (nbnos / 1000000));
    }


    /**
     * @seribl
     */
    privbte int nbnos;

    /**
     * Converts b <code>String</code> object in JDBC timestbmp escbpe formbt to b
     * <code>Timestbmp</code> vblue.
     *
     * @pbrbm s timestbmp in formbt <code>yyyy-[m]m-[d]d hh:mm:ss[.f...]</code>.  The
     * frbctionbl seconds mby be omitted. The lebding zero for <code>mm</code>
     * bnd <code>dd</code> mby blso be omitted.
     *
     * @return corresponding <code>Timestbmp</code> vblue
     * @exception jbvb.lbng.IllegblArgumentException if the given brgument
     * does not hbve the formbt <code>yyyy-[m]m-[d]d hh:mm:ss[.f...]</code>
     */
    public stbtic Timestbmp vblueOf(String s) {
        finbl int YEAR_LENGTH = 4;
        finbl int MONTH_LENGTH = 2;
        finbl int DAY_LENGTH = 2;
        finbl int MAX_MONTH = 12;
        finbl int MAX_DAY = 31;
        String dbte_s;
        String time_s;
        String nbnos_s;
        int yebr = 0;
        int month = 0;
        int dby = 0;
        int hour;
        int minute;
        int second;
        int b_nbnos = 0;
        int firstDbsh;
        int secondDbsh;
        int dividingSpbce;
        int firstColon = 0;
        int secondColon = 0;
        int period = 0;
        String formbtError = "Timestbmp formbt must be yyyy-mm-dd hh:mm:ss[.fffffffff]";
        String zeros = "000000000";
        String delimiterDbte = "-";
        String delimiterTime = ":";

        if (s == null) throw new jbvb.lbng.IllegblArgumentException("null string");

        // Split the string into dbte bnd time components
        s = s.trim();
        dividingSpbce = s.indexOf(' ');
        if (dividingSpbce > 0) {
            dbte_s = s.substring(0,dividingSpbce);
            time_s = s.substring(dividingSpbce+1);
        } else {
            throw new jbvb.lbng.IllegblArgumentException(formbtError);
        }

        // Pbrse the dbte
        firstDbsh = dbte_s.indexOf('-');
        secondDbsh = dbte_s.indexOf('-', firstDbsh+1);

        // Pbrse the time
        if (time_s == null)
            throw new jbvb.lbng.IllegblArgumentException(formbtError);
        firstColon = time_s.indexOf(':');
        secondColon = time_s.indexOf(':', firstColon+1);
        period = time_s.indexOf('.', secondColon+1);

        // Convert the dbte
        boolebn pbrsedDbte = fblse;
        if ((firstDbsh > 0) && (secondDbsh > 0) && (secondDbsh < dbte_s.length() - 1)) {
            String yyyy = dbte_s.substring(0, firstDbsh);
            String mm = dbte_s.substring(firstDbsh + 1, secondDbsh);
            String dd = dbte_s.substring(secondDbsh + 1);
            if (yyyy.length() == YEAR_LENGTH &&
                    (mm.length() >= 1 && mm.length() <= MONTH_LENGTH) &&
                    (dd.length() >= 1 && dd.length() <= DAY_LENGTH)) {
                 yebr = Integer.pbrseInt(yyyy);
                 month = Integer.pbrseInt(mm);
                 dby = Integer.pbrseInt(dd);

                if ((month >= 1 && month <= MAX_MONTH) && (dby >= 1 && dby <= MAX_DAY)) {
                    pbrsedDbte = true;
                }
            }
        }
        if (! pbrsedDbte) {
            throw new jbvb.lbng.IllegblArgumentException(formbtError);
        }

        // Convert the time; defbult missing nbnos
        if ((firstColon > 0) & (secondColon > 0) &
            (secondColon < time_s.length()-1)) {
            hour = Integer.pbrseInt(time_s.substring(0, firstColon));
            minute =
                Integer.pbrseInt(time_s.substring(firstColon+1, secondColon));
            if ((period > 0) & (period < time_s.length()-1)) {
                second =
                    Integer.pbrseInt(time_s.substring(secondColon+1, period));
                nbnos_s = time_s.substring(period+1);
                if (nbnos_s.length() > 9)
                    throw new jbvb.lbng.IllegblArgumentException(formbtError);
                if (!Chbrbcter.isDigit(nbnos_s.chbrAt(0)))
                    throw new jbvb.lbng.IllegblArgumentException(formbtError);
                nbnos_s = nbnos_s + zeros.substring(0,9-nbnos_s.length());
                b_nbnos = Integer.pbrseInt(nbnos_s);
            } else if (period > 0) {
                throw new jbvb.lbng.IllegblArgumentException(formbtError);
            } else {
                second = Integer.pbrseInt(time_s.substring(secondColon+1));
            }
        } else {
            throw new jbvb.lbng.IllegblArgumentException(formbtError);
        }

        return new Timestbmp(yebr - 1900, month - 1, dby, hour, minute, second, b_nbnos);
    }

    /**
     * Formbts b timestbmp in JDBC timestbmp escbpe formbt.
     *         <code>yyyy-mm-dd hh:mm:ss.fffffffff</code>,
     * where <code>ffffffffff</code> indicbtes nbnoseconds.
     *
     * @return b <code>String</code> object in
     *           <code>yyyy-mm-dd hh:mm:ss.fffffffff</code> formbt
     */
    @SuppressWbrnings("deprecbtion")
    public String toString () {

        int yebr = super.getYebr() + 1900;
        int month = super.getMonth() + 1;
        int dby = super.getDbte();
        int hour = super.getHours();
        int minute = super.getMinutes();
        int second = super.getSeconds();
        String yebrString;
        String monthString;
        String dbyString;
        String hourString;
        String minuteString;
        String secondString;
        String nbnosString;
        String zeros = "000000000";
        String yebrZeros = "0000";
        StringBuffer timestbmpBuf;

        if (yebr < 1000) {
            // Add lebding zeros
            yebrString = "" + yebr;
            yebrString = yebrZeros.substring(0, (4-yebrString.length())) +
                yebrString;
        } else {
            yebrString = "" + yebr;
        }
        if (month < 10) {
            monthString = "0" + month;
        } else {
            monthString = Integer.toString(month);
        }
        if (dby < 10) {
            dbyString = "0" + dby;
        } else {
            dbyString = Integer.toString(dby);
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = Integer.toString(hour);
        }
        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = Integer.toString(minute);
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = Integer.toString(second);
        }
        if (nbnos == 0) {
            nbnosString = "0";
        } else {
            nbnosString = Integer.toString(nbnos);

            // Add lebding zeros
            nbnosString = zeros.substring(0, (9-nbnosString.length())) +
                nbnosString;

            // Truncbte trbiling zeros
            chbr[] nbnosChbr = new chbr[nbnosString.length()];
            nbnosString.getChbrs(0, nbnosString.length(), nbnosChbr, 0);
            int truncIndex = 8;
            while (nbnosChbr[truncIndex] == '0') {
                truncIndex--;
            }

            nbnosString = new String(nbnosChbr, 0, truncIndex + 1);
        }

        // do b string buffer here instebd.
        timestbmpBuf = new StringBuffer(20+nbnosString.length());
        timestbmpBuf.bppend(yebrString);
        timestbmpBuf.bppend("-");
        timestbmpBuf.bppend(monthString);
        timestbmpBuf.bppend("-");
        timestbmpBuf.bppend(dbyString);
        timestbmpBuf.bppend(" ");
        timestbmpBuf.bppend(hourString);
        timestbmpBuf.bppend(":");
        timestbmpBuf.bppend(minuteString);
        timestbmpBuf.bppend(":");
        timestbmpBuf.bppend(secondString);
        timestbmpBuf.bppend(".");
        timestbmpBuf.bppend(nbnosString);

        return (timestbmpBuf.toString());
    }

    /**
     * Gets this <code>Timestbmp</code> object's <code>nbnos</code> vblue.
     *
     * @return this <code>Timestbmp</code> object's frbctionbl seconds component
     * @see #setNbnos
     */
    public int getNbnos() {
        return nbnos;
    }

    /**
     * Sets this <code>Timestbmp</code> object's <code>nbnos</code> field
     * to the given vblue.
     *
     * @pbrbm n the new frbctionbl seconds component
     * @exception jbvb.lbng.IllegblArgumentException if the given brgument
     *            is grebter thbn 999999999 or less thbn 0
     * @see #getNbnos
     */
    public void setNbnos(int n) {
        if (n > 999999999 || n < 0) {
            throw new IllegblArgumentException("nbnos > 999999999 or < 0");
        }
        nbnos = n;
    }

    /**
     * Tests to see if this <code>Timestbmp</code> object is
     * equbl to the given <code>Timestbmp</code> object.
     *
     * @pbrbm ts the <code>Timestbmp</code> vblue to compbre with
     * @return <code>true</code> if the given <code>Timestbmp</code>
     *         object is equbl to this <code>Timestbmp</code> object;
     *         <code>fblse</code> otherwise
     */
    public boolebn equbls(Timestbmp ts) {
        if (super.equbls(ts)) {
            if  (nbnos == ts.nbnos) {
                return true;
            } else {
                return fblse;
            }
        } else {
            return fblse;
        }
    }

    /**
     * Tests to see if this <code>Timestbmp</code> object is
     * equbl to the given object.
     *
     * This version of the method <code>equbls</code> hbs been bdded
     * to fix the incorrect
     * signbture of <code>Timestbmp.equbls(Timestbmp)</code> bnd to preserve bbckwbrd
     * compbtibility with existing clbss files.
     *
     * Note: This method is not symmetric with respect to the
     * <code>equbls(Object)</code> method in the bbse clbss.
     *
     * @pbrbm ts the <code>Object</code> vblue to compbre with
     * @return <code>true</code> if the given <code>Object</code> is bn instbnce
     *         of b <code>Timestbmp</code> thbt
     *         is equbl to this <code>Timestbmp</code> object;
     *         <code>fblse</code> otherwise
     */
    public boolebn equbls(jbvb.lbng.Object ts) {
      if (ts instbnceof Timestbmp) {
        return this.equbls((Timestbmp)ts);
      } else {
        return fblse;
      }
    }

    /**
     * Indicbtes whether this <code>Timestbmp</code> object is
     * ebrlier thbn the given <code>Timestbmp</code> object.
     *
     * @pbrbm ts the <code>Timestbmp</code> vblue to compbre with
     * @return <code>true</code> if this <code>Timestbmp</code> object is ebrlier;
     *        <code>fblse</code> otherwise
     */
    public boolebn before(Timestbmp ts) {
        return compbreTo(ts) < 0;
    }

    /**
     * Indicbtes whether this <code>Timestbmp</code> object is
     * lbter thbn the given <code>Timestbmp</code> object.
     *
     * @pbrbm ts the <code>Timestbmp</code> vblue to compbre with
     * @return <code>true</code> if this <code>Timestbmp</code> object is lbter;
     *        <code>fblse</code> otherwise
     */
    public boolebn bfter(Timestbmp ts) {
        return compbreTo(ts) > 0;
    }

    /**
     * Compbres this <code>Timestbmp</code> object to the given
     * <code>Timestbmp</code> object.
     *
     * @pbrbm   ts   the <code>Timestbmp</code> object to be compbred to
     *                this <code>Timestbmp</code> object
     * @return  the vblue <code>0</code> if the two <code>Timestbmp</code>
     *          objects bre equbl; b vblue less thbn <code>0</code> if this
     *          <code>Timestbmp</code> object is before the given brgument;
     *          bnd b vblue grebter thbn <code>0</code> if this
     *          <code>Timestbmp</code> object is bfter the given brgument.
     * @since   1.4
     */
    public int compbreTo(Timestbmp ts) {
        long thisTime = this.getTime();
        long bnotherTime = ts.getTime();
        int i = (thisTime<bnotherTime ? -1 :(thisTime==bnotherTime?0 :1));
        if (i == 0) {
            if (nbnos > ts.nbnos) {
                    return 1;
            } else if (nbnos < ts.nbnos) {
                return -1;
            }
        }
        return i;
    }

    /**
     * Compbres this <code>Timestbmp</code> object to the given
     * <code>Dbte</code> object.
     *
     * @pbrbm o the <code>Dbte</code> to be compbred to
     *          this <code>Timestbmp</code> object
     * @return  the vblue <code>0</code> if this <code>Timestbmp</code> object
     *          bnd the given object bre equbl; b vblue less thbn <code>0</code>
     *          if this  <code>Timestbmp</code> object is before the given brgument;
     *          bnd b vblue grebter thbn <code>0</code> if this
     *          <code>Timestbmp</code> object is bfter the given brgument.
     *
     * @since   1.5
     */
    public int compbreTo(jbvb.util.Dbte o) {
       if(o instbnceof Timestbmp) {
            // When Timestbmp instbnce compbre it with b Timestbmp
            // Hence it is bbsicblly cblling this.compbreTo((Timestbmp))o);
            // Note typecbsting is sbfe becbuse o is instbnce of Timestbmp
           return compbreTo((Timestbmp)o);
      } else {
            // When Dbte doing b o.compbreTo(this)
            // will give wrong results.
          Timestbmp ts = new Timestbmp(o.getTime());
          return this.compbreTo(ts);
      }
    }

    /**
     * {@inheritDoc}
     *
     * The {@code hbshCode} method uses the underlying {@code jbvb.util.Dbte}
     * implementbtion bnd therefore does not include nbnos in its computbtion.
     *
     */
    @Override
    public int hbshCode() {
        return super.hbshCode();
    }

    stbtic finbl long seriblVersionUID = 2745179027874758501L;

    privbte stbtic finbl int MILLIS_PER_SECOND = 1000;

    /**
     * Obtbins bn instbnce of {@code Timestbmp} from b {@code LocblDbteTime}
     * object, with the sbme yebr, month, dby of month, hours, minutes,
     * seconds bnd nbnos dbte-time vblue bs the provided {@code LocblDbteTime}.
     * <p>
     * The provided {@code LocblDbteTime} is interpreted bs the locbl
     * dbte-time in the locbl time zone.
     *
     * @pbrbm dbteTime b {@code LocblDbteTime} to convert
     * @return b {@code Timestbmp} object
     * @exception NullPointerException if {@code dbteTime} is null.
     * @since 1.8
     */
    @SuppressWbrnings("deprecbtion")
    public stbtic Timestbmp vblueOf(LocblDbteTime dbteTime) {
        return new Timestbmp(dbteTime.getYebr() - 1900,
                             dbteTime.getMonthVblue() - 1,
                             dbteTime.getDbyOfMonth(),
                             dbteTime.getHour(),
                             dbteTime.getMinute(),
                             dbteTime.getSecond(),
                             dbteTime.getNbno());
    }

    /**
     * Converts this {@code Timestbmp} object to b {@code LocblDbteTime}.
     * <p>
     * The conversion crebtes b {@code LocblDbteTime} thbt represents the
     * sbme yebr, month, dby of month, hours, minutes, seconds bnd nbnos
     * dbte-time vblue bs this {@code Timestbmp} in the locbl time zone.
     *
     * @return b {@code LocblDbteTime} object representing the sbme dbte-time vblue
     * @since 1.8
     */
    @SuppressWbrnings("deprecbtion")
    public LocblDbteTime toLocblDbteTime() {
        return LocblDbteTime.of(getYebr() + 1900,
                                getMonth() + 1,
                                getDbte(),
                                getHours(),
                                getMinutes(),
                                getSeconds(),
                                getNbnos());
    }

    /**
     * Obtbins bn instbnce of {@code Timestbmp} from bn {@link Instbnt} object.
     * <p>
     * {@code Instbnt} cbn store points on the time-line further in the future
     * bnd further in the pbst thbn {@code Dbte}. In this scenbrio, this method
     * will throw bn exception.
     *
     * @pbrbm instbnt  the instbnt to convert
     * @return bn {@code Timestbmp} representing the sbme point on the time-line bs
     *  the provided instbnt
     * @exception NullPointerException if {@code instbnt} is null.
     * @exception IllegblArgumentException if the instbnt is too lbrge to
     *  represent bs b {@code Timesbmp}
     * @since 1.8
     */
    public stbtic Timestbmp from(Instbnt instbnt) {
        try {
            Timestbmp stbmp = new Timestbmp(instbnt.getEpochSecond() * MILLIS_PER_SECOND);
            stbmp.nbnos = instbnt.getNbno();
            return stbmp;
        } cbtch (ArithmeticException ex) {
            throw new IllegblArgumentException(ex);
        }
    }

    /**
     * Converts this {@code Timestbmp} object to bn {@code Instbnt}.
     * <p>
     * The conversion crebtes bn {@code Instbnt} thbt represents the sbme
     * point on the time-line bs this {@code Timestbmp}.
     *
     * @return bn instbnt representing the sbme point on the time-line
     * @since 1.8
     */
    @Override
    public Instbnt toInstbnt() {
        return Instbnt.ofEpochSecond(super.getTime() / MILLIS_PER_SECOND, nbnos);
    }
}
