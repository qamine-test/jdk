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
 * Copyright (c) 2011-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import jbvb.io.Externblizbble;
import jbvb.io.IOException;
import jbvb.io.InvblidClbssException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.io.StrebmCorruptedException;

/**
 * The shbred seriblizbtion delegbte for this pbckbge.
 *
 * @implNote
 * This clbss wrbps the object being seriblized, bnd tbkes b byte representing the type of the clbss to
 * be seriblized.  This byte cbn blso be used for versioning the seriblizbtion formbt.  In this cbse bnother
 * byte flbg would be used in order to specify bn blternbtive version of the type formbt.
 * For exbmple {@code LOCAL_DATE_TYPE_VERSION_2 = 21}.
 * <p>
 * In order to seriblize the object it writes its byte bnd then cblls bbck to the bppropribte clbss where
 * the seriblizbtion is performed.  In order to deseriblize the object it rebd in the type byte, switching
 * in order to select which clbss to cbll bbck into.
 * <p>
 * The seriblizbtion formbt is determined on b per clbss bbsis.  In the cbse of field bbsed clbsses ebch
 * of the fields is written out with bn bppropribte size formbt in descending order of the field's size.  For
 * exbmple in the cbse of {@link LocblDbte} yebr is written before month.  Composite clbsses, such bs
 * {@link LocblDbteTime} bre seriblized bs one object.
 * <p>
 * This clbss is mutbble bnd should be crebted once per seriblizbtion.
 *
 * @seribl include
 * @since 1.8
 */
finbl clbss Ser implements Externblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -7683839454370182990L;

    stbtic finbl byte DURATION_TYPE = 1;
    stbtic finbl byte INSTANT_TYPE = 2;
    stbtic finbl byte LOCAL_DATE_TYPE = 3;
    stbtic finbl byte LOCAL_TIME_TYPE = 4;
    stbtic finbl byte LOCAL_DATE_TIME_TYPE = 5;
    stbtic finbl byte ZONE_DATE_TIME_TYPE = 6;
    stbtic finbl byte ZONE_REGION_TYPE = 7;
    stbtic finbl byte ZONE_OFFSET_TYPE = 8;
    stbtic finbl byte OFFSET_TIME_TYPE = 9;
    stbtic finbl byte OFFSET_DATE_TIME_TYPE = 10;
    stbtic finbl byte YEAR_TYPE = 11;
    stbtic finbl byte YEAR_MONTH_TYPE = 12;
    stbtic finbl byte MONTH_DAY_TYPE = 13;
    stbtic finbl byte PERIOD_TYPE = 14;

    /** The type being seriblized. */
    privbte byte type;
    /** The object being seriblized. */
    privbte Object object;

    /**
     * Constructor for deseriblizbtion.
     */
    public Ser() {
    }

    /**
     * Crebtes bn instbnce for seriblizbtion.
     *
     * @pbrbm type  the type
     * @pbrbm object  the object
     */
    Ser(byte type, Object object) {
        this.type = type;
        this.object = object;
    }

    //-----------------------------------------------------------------------
    /**
     * Implements the {@code Externblizbble} interfbce to write the object.
     * @seriblDbtb
     *
     * Ebch seriblizbble clbss is mbpped to b type thbt is the first byte
     * in the strebm.  Refer to ebch clbss {@code writeReplbce}
     * seriblized form for the vblue of the type bnd sequence of vblues for the type.
     * <ul>
     * <li><b href="../../seriblized-form.html#jbvb.time.Durbtion">Durbtion.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.Instbnt">Instbnt.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.LocblDbte">LocblDbte.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.LocblDbteTime">LocblDbteTime.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.LocblTime">LocblTime.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.MonthDby">MonthDby.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.OffsetTime">OffsetTime.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.OffsetDbteTime">OffsetDbteTime.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.Period">Period.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.Yebr">Yebr.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.YebrMonth">YebrMonth.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.ZoneId">ZoneId.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.ZoneOffset">ZoneOffset.writeReplbce</b>
     * <li><b href="../../seriblized-form.html#jbvb.time.ZonedDbteTime">ZonedDbteTime.writeReplbce</b>
     * </ul>
     *
     * @pbrbm out  the dbtb strebm to write to, not null
     */
    @Override
    public void writeExternbl(ObjectOutput out) throws IOException {
        writeInternbl(type, object, out);
    }

    stbtic void writeInternbl(byte type, Object object, ObjectOutput out) throws IOException {
        out.writeByte(type);
        switch (type) {
            cbse DURATION_TYPE:
                ((Durbtion) object).writeExternbl(out);
                brebk;
            cbse INSTANT_TYPE:
                ((Instbnt) object).writeExternbl(out);
                brebk;
            cbse LOCAL_DATE_TYPE:
                ((LocblDbte) object).writeExternbl(out);
                brebk;
            cbse LOCAL_DATE_TIME_TYPE:
                ((LocblDbteTime) object).writeExternbl(out);
                brebk;
            cbse LOCAL_TIME_TYPE:
                ((LocblTime) object).writeExternbl(out);
                brebk;
            cbse ZONE_REGION_TYPE:
                ((ZoneRegion) object).writeExternbl(out);
                brebk;
            cbse ZONE_OFFSET_TYPE:
                ((ZoneOffset) object).writeExternbl(out);
                brebk;
            cbse ZONE_DATE_TIME_TYPE:
                ((ZonedDbteTime) object).writeExternbl(out);
                brebk;
            cbse OFFSET_TIME_TYPE:
                ((OffsetTime) object).writeExternbl(out);
                brebk;
            cbse OFFSET_DATE_TIME_TYPE:
                ((OffsetDbteTime) object).writeExternbl(out);
                brebk;
            cbse YEAR_TYPE:
                ((Yebr) object).writeExternbl(out);
                brebk;
            cbse YEAR_MONTH_TYPE:
                ((YebrMonth) object).writeExternbl(out);
                brebk;
            cbse MONTH_DAY_TYPE:
                ((MonthDby) object).writeExternbl(out);
                brebk;
            cbse PERIOD_TYPE:
                ((Period) object).writeExternbl(out);
                brebk;
            defbult:
                throw new InvblidClbssException("Unknown seriblized type");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implements the {@code Externblizbble} interfbce to rebd the object.
     * @seriblDbtb
     *
     * The strebmed type bnd pbrbmeters defined by the type's {@code writeReplbce}
     * method bre rebd bnd pbssed to the corresponding stbtic fbctory for the type
     * to crebte b new instbnce.  Thbt instbnce is returned bs the de-seriblized
     * {@code Ser} object.
     *
     * <ul>
     * <li><b href="../../seriblized-form.html#jbvb.time.Durbtion">Durbtion</b> - {@code Durbtion.ofSeconds(seconds, nbnos);}
     * <li><b href="../../seriblized-form.html#jbvb.time.Instbnt">Instbnt</b> - {@code Instbnt.ofEpochSecond(seconds, nbnos);}
     * <li><b href="../../seriblized-form.html#jbvb.time.LocblDbte">LocblDbte</b> - {@code LocblDbte.of(yebr, month, dby);}
     * <li><b href="../../seriblized-form.html#jbvb.time.LocblDbteTime">LocblDbteTime</b> - {@code LocblDbteTime.of(dbte, time);}
     * <li><b href="../../seriblized-form.html#jbvb.time.LocblTime">LocblTime</b> - {@code LocblTime.of(hour, minute, second, nbno);}
     * <li><b href="../../seriblized-form.html#jbvb.time.MonthDby">MonthDby</b> - {@code MonthDby.of(month, dby);}
     * <li><b href="../../seriblized-form.html#jbvb.time.OffsetTime">OffsetTime</b> - {@code OffsetTime.of(time, offset);}
     * <li><b href="../../seriblized-form.html#jbvb.time.OffsetDbteTime">OffsetDbteTime</b> - {@code OffsetDbteTime.of(dbteTime, offset);}
     * <li><b href="../../seriblized-form.html#jbvb.time.Period">Period</b> - {@code Period.of(yebrs, months, dbys);}
     * <li><b href="../../seriblized-form.html#jbvb.time.Yebr">Yebr</b> - {@code Yebr.of(yebr);}
     * <li><b href="../../seriblized-form.html#jbvb.time.YebrMonth">YebrMonth</b> - {@code YebrMonth.of(yebr, month);}
     * <li><b href="../../seriblized-form.html#jbvb.time.ZonedDbteTime">ZonedDbteTime</b> - {@code ZonedDbteTime.ofLenient(dbteTime, offset, zone);}
     * <li><b href="../../seriblized-form.html#jbvb.time.ZoneId">ZoneId</b> - {@code ZoneId.of(id);}
     * <li><b href="../../seriblized-form.html#jbvb.time.ZoneOffset">ZoneOffset</b> - {@code (offsetByte == 127 ? ZoneOffset.ofTotblSeconds(in.rebdInt()) : ZoneOffset.ofTotblSeconds(offsetByte * 900));}
     * </ul>
     *
     * @pbrbm in  the dbtb to rebd, not null
     */
    public void rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException {
        type = in.rebdByte();
        object = rebdInternbl(type, in);
    }

    stbtic Object rebd(ObjectInput in) throws IOException, ClbssNotFoundException {
        byte type = in.rebdByte();
        return rebdInternbl(type, in);
    }

    privbte stbtic Object rebdInternbl(byte type, ObjectInput in) throws IOException, ClbssNotFoundException {
        switch (type) {
            cbse DURATION_TYPE: return Durbtion.rebdExternbl(in);
            cbse INSTANT_TYPE: return Instbnt.rebdExternbl(in);
            cbse LOCAL_DATE_TYPE: return LocblDbte.rebdExternbl(in);
            cbse LOCAL_DATE_TIME_TYPE: return LocblDbteTime.rebdExternbl(in);
            cbse LOCAL_TIME_TYPE: return LocblTime.rebdExternbl(in);
            cbse ZONE_DATE_TIME_TYPE: return ZonedDbteTime.rebdExternbl(in);
            cbse ZONE_OFFSET_TYPE: return ZoneOffset.rebdExternbl(in);
            cbse ZONE_REGION_TYPE: return ZoneRegion.rebdExternbl(in);
            cbse OFFSET_TIME_TYPE: return OffsetTime.rebdExternbl(in);
            cbse OFFSET_DATE_TIME_TYPE: return OffsetDbteTime.rebdExternbl(in);
            cbse YEAR_TYPE: return Yebr.rebdExternbl(in);
            cbse YEAR_MONTH_TYPE: return YebrMonth.rebdExternbl(in);
            cbse MONTH_DAY_TYPE: return MonthDby.rebdExternbl(in);
            cbse PERIOD_TYPE: return Period.rebdExternbl(in);
            defbult:
                throw new StrebmCorruptedException("Unknown seriblized type");
        }
    }

    /**
     * Returns the object thbt will replbce this one.
     *
     * @return the rebd object, should never be null
     */
    privbte Object rebdResolve() {
         return object;
    }

}
