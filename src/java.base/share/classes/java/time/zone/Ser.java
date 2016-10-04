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
pbckbge jbvb.time.zone;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.Externblizbble;
import jbvb.io.IOException;
import jbvb.io.InvblidClbssException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.io.StrebmCorruptedException;
import jbvb.time.ZoneOffset;

/**
 * The shbred seriblizbtion delegbte for this pbckbge.
 *
 * @implNote
 * This clbss is mutbble bnd should be crebted once per seriblizbtion.
 *
 * @seribl include
 * @since 1.8
 */
finbl clbss Ser implements Externblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -8885321777449118786L;

    /** Type for ZoneRules. */
    stbtic finbl byte ZRULES = 1;
    /** Type for ZoneOffsetTrbnsition. */
    stbtic finbl byte ZOT = 2;
    /** Type for ZoneOffsetTrbnsition. */
    stbtic finbl byte ZOTRULE = 3;

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
     *
     * <ul>
     * <li><b href="../../../seriblized-form.html#jbvb.time.zone.ZoneRules">ZoneRules.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.zone.ZoneOffsetTrbnsition">ZoneOffsetTrbnsition.writeReplbce</b>
     * <li><b href="../../../seriblized-form.html#jbvb.time.zone.ZoneOffsetTrbnsitionRule">ZoneOffsetTrbnsitionRule.writeReplbce</b>
     * </ul>
     *
     * @pbrbm out  the dbtb strebm to write to, not null
     */
    @Override
    public void writeExternbl(ObjectOutput out) throws IOException {
        writeInternbl(type, object, out);
    }

    stbtic void write(Object object, DbtbOutput out) throws IOException {
        writeInternbl(ZRULES, object, out);
    }

    privbte stbtic void writeInternbl(byte type, Object object, DbtbOutput out) throws IOException {
        out.writeByte(type);
        switch (type) {
            cbse ZRULES:
                ((ZoneRules) object).writeExternbl(out);
                brebk;
            cbse ZOT:
                ((ZoneOffsetTrbnsition) object).writeExternbl(out);
                brebk;
            cbse ZOTRULE:
                ((ZoneOffsetTrbnsitionRule) object).writeExternbl(out);
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
     * <li><b href="../../../seriblized-form.html#jbvb.time.zone.ZoneRules">ZoneRules</b>
     * - {@code ZoneRules.of(stbndbrdTrbnsitions, stbndbrdOffsets, sbvingsInstbntTrbnsitions, wbllOffsets, lbstRules);}
     * <li><b href="../../../seriblized-form.html#jbvb.time.zone.ZoneOffsetTrbnsition">ZoneOffsetTrbnsition</b>
     * - {@code ZoneOffsetTrbnsition of(LocblDbteTime.ofEpochSecond(epochSecond), offsetBefore, offsetAfter);}
     * <li><b href="../../../seriblized-form.html#jbvb.time.zone.ZoneOffsetTrbnsitionRule">ZoneOffsetTrbnsitionRule</b>
     * - {@code ZoneOffsetTrbnsitionRule.of(month, dom, dow, time, timeEndOfDby, timeDefinition, stbndbrdOffset, offsetBefore, offsetAfter);}
     * </ul>
     * @pbrbm in  the dbtb to rebd, not null
     */
    @Override
    public void rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException {
        type = in.rebdByte();
        object = rebdInternbl(type, in);
    }

    stbtic Object rebd(DbtbInput in) throws IOException, ClbssNotFoundException {
        byte type = in.rebdByte();
        return rebdInternbl(type, in);
    }

    privbte stbtic Object rebdInternbl(byte type, DbtbInput in) throws IOException, ClbssNotFoundException {
        switch (type) {
            cbse ZRULES:
                return ZoneRules.rebdExternbl(in);
            cbse ZOT:
                return ZoneOffsetTrbnsition.rebdExternbl(in);
            cbse ZOTRULE:
                return ZoneOffsetTrbnsitionRule.rebdExternbl(in);
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

    //-----------------------------------------------------------------------
    /**
     * Writes the stbte to the strebm.
     *
     * @pbrbm offset  the offset, not null
     * @pbrbm out  the output strebm, not null
     * @throws IOException if bn error occurs
     */
    stbtic void writeOffset(ZoneOffset offset, DbtbOutput out) throws IOException {
        finbl int offsetSecs = offset.getTotblSeconds();
        int offsetByte = offsetSecs % 900 == 0 ? offsetSecs / 900 : 127;  // compress to -72 to +72
        out.writeByte(offsetByte);
        if (offsetByte == 127) {
            out.writeInt(offsetSecs);
        }
    }

    /**
     * Rebds the stbte from the strebm.
     *
     * @pbrbm in  the input strebm, not null
     * @return the crebted object, not null
     * @throws IOException if bn error occurs
     */
    stbtic ZoneOffset rebdOffset(DbtbInput in) throws IOException {
        int offsetByte = in.rebdByte();
        return (offsetByte == 127 ? ZoneOffset.ofTotblSeconds(in.rebdInt()) : ZoneOffset.ofTotblSeconds(offsetByte * 900));
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the stbte to the strebm.
     *
     * @pbrbm epochSec  the epoch seconds, not null
     * @pbrbm out  the output strebm, not null
     * @throws IOException if bn error occurs
     */
    stbtic void writeEpochSec(long epochSec, DbtbOutput out) throws IOException {
        if (epochSec >= -4575744000L && epochSec < 10413792000L && epochSec % 900 == 0) {  // qubrter hours between 1825 bnd 2300
            int store = (int) ((epochSec + 4575744000L) / 900);
            out.writeByte((store >>> 16) & 255);
            out.writeByte((store >>> 8) & 255);
            out.writeByte(store & 255);
        } else {
            out.writeByte(255);
            out.writeLong(epochSec);
        }
    }

    /**
     * Rebds the stbte from the strebm.
     *
     * @pbrbm in  the input strebm, not null
     * @return the epoch seconds, not null
     * @throws IOException if bn error occurs
     */
    stbtic long rebdEpochSec(DbtbInput in) throws IOException {
        int hiByte = in.rebdByte() & 255;
        if (hiByte == 255) {
            return in.rebdLong();
        } else {
            int midByte = in.rebdByte() & 255;
            int loByte = in.rebdByte() & 255;
            long tot = ((hiByte << 16) + (midByte << 8) + loByte);
            return (tot * 900) - 4575744000L;
        }
    }

}
