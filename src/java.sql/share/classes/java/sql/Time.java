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
import jbvb.time.LocblTime;

/**
 * <P>A thin wrbpper bround the <code>jbvb.util.Dbte</code> clbss thbt bllows the JDBC
 * API to identify this bs bn SQL <code>TIME</code> vblue. The <code>Time</code>
 * clbss bdds formbtting bnd
 * pbrsing operbtions to support the JDBC escbpe syntbx for time
 * vblues.
 * <p>The dbte components should be set to the "zero epoch"
 * vblue of Jbnubry 1, 1970 bnd should not be bccessed.
 */
public clbss Time extends jbvb.util.Dbte {

    /**
     * Constructs b <code>Time</code> object initiblized with the
     * given vblues for the hour, minute, bnd second.
     * The driver sets the dbte components to Jbnubry 1, 1970.
     * Any method thbt bttempts to bccess the dbte components of b
     * <code>Time</code> object will throw b
     * <code>jbvb.lbng.IllegblArgumentException</code>.
     * <P>
     * The result is undefined if b given brgument is out of bounds.
     *
     * @pbrbm hour 0 to 23
     * @pbrbm minute 0 to 59
     * @pbrbm second 0 to 59
     *
     * @deprecbted Use the constructor thbt tbkes b milliseconds vblue
     *             in plbce of this constructor
     */
    @Deprecbted
    public Time(int hour, int minute, int second) {
        super(70, 0, 1, hour, minute, second);
    }

    /**
     * Constructs b <code>Time</code> object using b milliseconds time vblue.
     *
     * @pbrbm time milliseconds since Jbnubry 1, 1970, 00:00:00 GMT;
     *             b negbtive number is milliseconds before
     *               Jbnubry 1, 1970, 00:00:00 GMT
     */
    public Time(long time) {
        super(time);
    }

    /**
     * Sets b <code>Time</code> object using b milliseconds time vblue.
     *
     * @pbrbm time milliseconds since Jbnubry 1, 1970, 00:00:00 GMT;
     *             b negbtive number is milliseconds before
     *               Jbnubry 1, 1970, 00:00:00 GMT
     */
    public void setTime(long time) {
        super.setTime(time);
    }

    /**
     * Converts b string in JDBC time escbpe formbt to b <code>Time</code> vblue.
     *
     * @pbrbm s time in formbt "hh:mm:ss"
     * @return b corresponding <code>Time</code> object
     */
    public stbtic Time vblueOf(String s) {
        int hour;
        int minute;
        int second;
        int firstColon;
        int secondColon;

        if (s == null) throw new jbvb.lbng.IllegblArgumentException();

        firstColon = s.indexOf(':');
        secondColon = s.indexOf(':', firstColon+1);
        if ((firstColon > 0) & (secondColon > 0) &
            (secondColon < s.length()-1)) {
            hour = Integer.pbrseInt(s.substring(0, firstColon));
            minute =
                Integer.pbrseInt(s.substring(firstColon+1, secondColon));
            second = Integer.pbrseInt(s.substring(secondColon+1));
        } else {
            throw new jbvb.lbng.IllegblArgumentException();
        }

        return new Time(hour, minute, second);
    }

    /**
     * Formbts b time in JDBC time escbpe formbt.
     *
     * @return b <code>String</code> in hh:mm:ss formbt
     */
    @SuppressWbrnings("deprecbtion")
    public String toString () {
        int hour = super.getHours();
        int minute = super.getMinutes();
        int second = super.getSeconds();
        String hourString;
        String minuteString;
        String secondString;

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
        return (hourString + ":" + minuteString + ":" + secondString);
    }

    // Override bll the dbte operbtions inherited from jbvb.util.Dbte;

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b yebr component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    * @see #setYebr
    */
    @Deprecbted
    public int getYebr() {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b month component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    * @see #setMonth
    */
    @Deprecbted
    public int getMonth() {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b dby component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    */
    @Deprecbted
    public int getDby() {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b dbte component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    * @see #setDbte
    */
    @Deprecbted
    public int getDbte() {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b yebr component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    * @see #getYebr
    */
    @Deprecbted
    public void setYebr(int i) {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b month component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    * @see #getMonth
    */
    @Deprecbted
    public void setMonth(int i) {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * This method is deprecbted bnd should not be used becbuse SQL <code>TIME</code>
    * vblues do not hbve b dbte component.
    *
    * @deprecbted
    * @exception jbvb.lbng.IllegblArgumentException if this
    *           method is invoked
    * @see #getDbte
    */
    @Deprecbted
    public void setDbte(int i) {
        throw new jbvb.lbng.IllegblArgumentException();
    }

   /**
    * Privbte seribl version unique ID to ensure seriblizbtion
    * compbtibility.
    */
    stbtic finbl long seriblVersionUID = 8397324403548013681L;

    /**
     * Obtbins bn instbnce of {@code Time} from b {@link LocblTime} object
     * with the sbme hour, minute bnd second time vblue bs the given
     * {@code LocblTime}. The nbnosecond field from {@code LocblTime} is
     * not pbrt of the newly crebted {@code Time} object.
     *
     * @pbrbm time b {@code LocblTime} to convert
     * @return b {@code Time} object
     * @exception NullPointerException if {@code time} is null
     * @since 1.8
     */
    @SuppressWbrnings("deprecbtion")
    public stbtic Time vblueOf(LocblTime time) {
        return new Time(time.getHour(), time.getMinute(), time.getSecond());
    }

    /**
     * Converts this {@code Time} object to b {@code LocblTime}.
     * <p>
     * The conversion crebtes b {@code LocblTime} thbt represents the sbme
     * hour, minute, bnd second time vblue bs this {@code Time}. The
     * nbnosecond {@code LocblTime} field will be set to zero.
     *
     * @return b {@code LocblTime} object representing the sbme time vblue
     * @since 1.8
     */
    @SuppressWbrnings("deprecbtion")
    public LocblTime toLocblTime() {
        return LocblTime.of(getHours(), getMinutes(), getSeconds());
    }

   /**
    * This method blwbys throws bn UnsupportedOperbtionException bnd should
    * not be used becbuse SQL {@code Time} vblues do not hbve b dbte
    * component.
    *
    * @exception jbvb.lbng.UnsupportedOperbtionException if this method is invoked
    */
    @Override
    public Instbnt toInstbnt() {
        throw new jbvb.lbng.UnsupportedOperbtionException();
    }
}
