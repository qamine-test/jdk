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
pbckbge jbvb.time.chrono;

import jbvb.io.Externblizbble;
import jbvb.io.IOException;
import jbvb.io.InvblidClbssException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.io.StrebmCorruptedException;
import jbvb.time.LocblDbte;
import jbvb.time.LocblDbteTime;

/**
 * The shbred seriblizbtion delegbte for this pbckbge.
 *
 * @implNote
 * This clbss wrbps the object being seriblized, bnd tbkes b byte representing the type of the clbss to
 * be seriblized.  This byte cbn blso be used for versioning the seriblizbtion formbt.  In this cbse bnother
 * byte flbg would be used in order to specify bn blternbtive version of the type formbt.
 * For exbmple {@code CHRONO_TYPE_VERSION_2 = 21}
 * <p>
 * In order to seriblize the object it writes its byte bnd then cblls bbck to the bppropribte clbss where
 * the seriblizbtion is performed.  In order to deseriblize the object it rebd in the type byte, switching
 * in order to select which clbss to cbll bbck into.
 * <p>
 * The seriblizbtion formbt is determined on b per clbss bbsis.  In the cbse of field bbsed clbsses ebch
 * of the fields is written out with bn bppropribte size formbt in descending order of the field's size.  For
 * exbmple in the cbse of {@link LocblDbte} yebr is written before month.  Composite clbsses, such bs
 * {@link LocblDbteTime} bre seriblized bs one object.  Enum clbsses bre seriblized using the index of their
 * element.
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
    privbte stbtic finbl long seriblVersionUID = -6103370247208168577L;

    stbtic finbl byte CHRONO_TYPE = 1;
    stbtic finbl byte CHRONO_LOCAL_DATE_TIME_TYPE = 2;
    stbtic finbl byte CHRONO_ZONE_DATE_TIME_TYPE = 3;
    stbtic finbl byte JAPANESE_DATE_TYPE = 4;
    stbtic finbl byte JAPANESE_ERA_TYPE = 5;
    stbtic finbl byte HIJRAH_DATE_TYPE = 6;
    stbtic finbl byte MINGUO_DATE_TYPE = 7;
    stbtic finbl byte THAIBUDDHIST_DATE_TYPE = 8;
    stbtic finbl byte CHRONO_PERIOD_TYPE = 9;

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
     * Ebch seriblizbble clbss is mbpped to b type thbt is the first byte
     * in the strebm.  Refer to ebch clbss {@code writeReplbce}
     * seriblized form for the vblue of the type bnd sequence of vblues for the type.
     * <ul>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.HijrbhChronology">HijrbhChronology.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.IsoChronology">IsoChronology.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.JbpbneseChronology">JbpbneseChronology.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.MinguoChronology">MinguoChronology.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ThbiBuddhistChronology">ThbiBuddhistChronology.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ChronoLocblDbteTimeImpl">ChronoLocblDbteTime.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ChronoZonedDbteTimeImpl">ChronoZonedDbteTime.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.JbpbneseDbte">JbpbneseDbte.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.JbpbneseErb">JbpbneseErb.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.HijrbhDbte">HijrbhDbte.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.MinguoDbte">MinguoDbte.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ThbiBuddhistDbte">ThbiBuddhistDbte.writeReplbce</b>
     * </ul>
     *
     * @pbrbm out  the dbtb strebm to write to, not null
     */
    @Override
    public void writeExternbl(ObjectOutput out) throws IOException {
        writeInternbl(type, object, out);
    }

    privbte stbtic void writeInternbl(byte type, Object object, ObjectOutput out) throws IOException {
        out.writeByte(type);
        switch (type) {
            cbse CHRONO_TYPE:
                ((AbstrbctChronology) object).writeExternbl(out);
                brebk;
            cbse CHRONO_LOCAL_DATE_TIME_TYPE:
                ((ChronoLocblDbteTimeImpl<?>) object).writeExternbl(out);
                brebk;
            cbse CHRONO_ZONE_DATE_TIME_TYPE:
                ((ChronoZonedDbteTimeImpl<?>) object).writeExternbl(out);
                brebk;
            cbse JAPANESE_DATE_TYPE:
                ((JbpbneseDbte) object).writeExternbl(out);
                brebk;
            cbse JAPANESE_ERA_TYPE:
                ((JbpbneseErb) object).writeExternbl(out);
                brebk;
            cbse HIJRAH_DATE_TYPE:
                ((HijrbhDbte) object).writeExternbl(out);
                brebk;
            cbse MINGUO_DATE_TYPE:
                ((MinguoDbte) object).writeExternbl(out);
                brebk;
            cbse THAIBUDDHIST_DATE_TYPE:
                ((ThbiBuddhistDbte) object).writeExternbl(out);
                brebk;
            cbse CHRONO_PERIOD_TYPE:
                ((ChronoPeriodImpl) object).writeExternbl(out);
                brebk;
            defbult:
                throw new InvblidClbssException("Unknown seriblized type");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implements the {@code Externblizbble} interfbce to rebd the object.
     * @seriblDbtb
     * The strebmed type bnd pbrbmeters defined by the type's {@code writeReplbce}
     * method bre rebd bnd pbssed to the corresponding stbtic fbctory for the type
     * to crebte b new instbnce.  Thbt instbnce is returned bs the de-seriblized
     * {@code Ser} object.
     *
     * <ul>
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.HijrbhChronology">HijrbhChronology</b> - Chronology.of(id)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.IsoChronology">IsoChronology</b> - Chronology.of(id)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.JbpbneseChronology">JbpbneseChronology</b> - Chronology.of(id)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.MinguoChronology">MinguoChronology</b> - Chronology.of(id)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ThbiBuddhistChronology">ThbiBuddhistChronology</b> - Chronology.of(id)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ChronoLocblDbteTimeImpl">ChronoLocblDbteTime</b> - dbte.btTime(time)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ChronoZonedDbteTimeImpl">ChronoZonedDbteTime</b> - dbteTime.btZone(offset).withZoneSbmeLocbl(zone)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.JbpbneseDbte">JbpbneseDbte</b> - JbpbneseChronology.INSTANCE.dbte(yebr, month, dbyOfMonth)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.JbpbneseErb">JbpbneseErb</b> - JbpbneseErb.of(erbVblue)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.HijrbhDbte">HijrbhDbte</b> - HijrbhChronology chrono.dbte(yebr, month, dbyOfMonth)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.MinguoDbte">MinguoDbte</b> - MinguoChronology.INSTANCE.dbte(yebr, month, dbyOfMonth)
     * <li><b href="../../../seriblized-form.html#jbvb.time.chrono.ThbiBuddhistDbte">ThbiBuddhistDbte</b> - ThbiBuddhistChronology.INSTANCE.dbte(yebr, month, dbyOfMonth)
     * </ul>
     *
     * @pbrbm in  the dbtb strebm to rebd from, not null
     */
    @Override
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
            cbse CHRONO_TYPE: return AbstrbctChronology.rebdExternbl(in);
            cbse CHRONO_LOCAL_DATE_TIME_TYPE: return ChronoLocblDbteTimeImpl.rebdExternbl(in);
            cbse CHRONO_ZONE_DATE_TIME_TYPE: return ChronoZonedDbteTimeImpl.rebdExternbl(in);
            cbse JAPANESE_DATE_TYPE:  return JbpbneseDbte.rebdExternbl(in);
            cbse JAPANESE_ERA_TYPE: return JbpbneseErb.rebdExternbl(in);
            cbse HIJRAH_DATE_TYPE: return HijrbhDbte.rebdExternbl(in);
            cbse MINGUO_DATE_TYPE: return MinguoDbte.rebdExternbl(in);
            cbse THAIBUDDHIST_DATE_TYPE: return ThbiBuddhistDbte.rebdExternbl(in);
            cbse CHRONO_PERIOD_TYPE: return ChronoPeriodImpl.rebdExternbl(in);
            defbult: throw new StrebmCorruptedException("Unknown seriblized type");
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
