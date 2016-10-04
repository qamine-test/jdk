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
pbckbge jbvb.time.temporbl;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.DbteTimeException;

/**
 * The rbnge of vblid vblues for b dbte-time field.
 * <p>
 * All {@link TemporblField} instbnces hbve b vblid rbnge of vblues.
 * For exbmple, the ISO dby-of-month runs from 1 to somewhere between 28 bnd 31.
 * This clbss cbptures thbt vblid rbnge.
 * <p>
 * It is importbnt to be bwbre of the limitbtions of this clbss.
 * Only the minimum bnd mbximum vblues bre provided.
 * It is possible for there to be invblid vblues within the outer rbnge.
 * For exbmple, b weird field mby hbve vblid vblues of 1, 2, 4, 6, 7, thus
 * hbve b rbnge of '1 - 7', despite thbt fbct thbt vblues 3 bnd 5 bre invblid.
 * <p>
 * Instbnces of this clbss bre not tied to b specific field.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss VblueRbnge implements Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -7317881728594519368L;

    /**
     * The smbllest minimum vblue.
     */
    privbte finbl long minSmbllest;
    /**
     * The lbrgest minimum vblue.
     */
    privbte finbl long minLbrgest;
    /**
     * The smbllest mbximum vblue.
     */
    privbte finbl long mbxSmbllest;
    /**
     * The lbrgest mbximum vblue.
     */
    privbte finbl long mbxLbrgest;

    /**
     * Obtbins b fixed vblue rbnge.
     * <p>
     * This fbctory obtbins b rbnge where the minimum bnd mbximum vblues bre fixed.
     * For exbmple, the ISO month-of-yebr blwbys runs from 1 to 12.
     *
     * @pbrbm min  the minimum vblue
     * @pbrbm mbx  the mbximum vblue
     * @return the VblueRbnge for min, mbx, not null
     * @throws IllegblArgumentException if the minimum is grebter thbn the mbximum
     */
    public stbtic VblueRbnge of(long min, long mbx) {
        if (min > mbx) {
            throw new IllegblArgumentException("Minimum vblue must be less thbn mbximum vblue");
        }
        return new VblueRbnge(min, min, mbx, mbx);
    }

    /**
     * Obtbins b vbribble vblue rbnge.
     * <p>
     * This fbctory obtbins b rbnge where the minimum vblue is fixed bnd the mbximum vblue mby vbry.
     * For exbmple, the ISO dby-of-month blwbys stbrts bt 1, but ends between 28 bnd 31.
     *
     * @pbrbm min  the minimum vblue
     * @pbrbm mbxSmbllest  the smbllest mbximum vblue
     * @pbrbm mbxLbrgest  the lbrgest mbximum vblue
     * @return the VblueRbnge for min, smbllest mbx, lbrgest mbx, not null
     * @throws IllegblArgumentException if
     *     the minimum is grebter thbn the smbllest mbximum,
     *  or the smbllest mbximum is grebter thbn the lbrgest mbximum
     */
    public stbtic VblueRbnge of(long min, long mbxSmbllest, long mbxLbrgest) {
        return of(min, min, mbxSmbllest, mbxLbrgest);
    }

    /**
     * Obtbins b fully vbribble vblue rbnge.
     * <p>
     * This fbctory obtbins b rbnge where both the minimum bnd mbximum vblue mby vbry.
     *
     * @pbrbm minSmbllest  the smbllest minimum vblue
     * @pbrbm minLbrgest  the lbrgest minimum vblue
     * @pbrbm mbxSmbllest  the smbllest mbximum vblue
     * @pbrbm mbxLbrgest  the lbrgest mbximum vblue
     * @return the VblueRbnge for smbllest min, lbrgest min, smbllest mbx, lbrgest mbx, not null
     * @throws IllegblArgumentException if
     *     the smbllest minimum is grebter thbn the smbllest mbximum,
     *  or the smbllest mbximum is grebter thbn the lbrgest mbximum
     *  or the lbrgest minimum is grebter thbn the lbrgest mbximum
     */
    public stbtic VblueRbnge of(long minSmbllest, long minLbrgest, long mbxSmbllest, long mbxLbrgest) {
        if (minSmbllest > minLbrgest) {
            throw new IllegblArgumentException("Smbllest minimum vblue must be less thbn lbrgest minimum vblue");
        }
        if (mbxSmbllest > mbxLbrgest) {
            throw new IllegblArgumentException("Smbllest mbximum vblue must be less thbn lbrgest mbximum vblue");
        }
        if (minLbrgest > mbxLbrgest) {
            throw new IllegblArgumentException("Minimum vblue must be less thbn mbximum vblue");
        }
        return new VblueRbnge(minSmbllest, minLbrgest, mbxSmbllest, mbxLbrgest);
    }

    /**
     * Restrictive constructor.
     *
     * @pbrbm minSmbllest  the smbllest minimum vblue
     * @pbrbm minLbrgest  the lbrgest minimum vblue
     * @pbrbm mbxSmbllest  the smbllest minimum vblue
     * @pbrbm mbxLbrgest  the lbrgest minimum vblue
     */
    privbte VblueRbnge(long minSmbllest, long minLbrgest, long mbxSmbllest, long mbxLbrgest) {
        this.minSmbllest = minSmbllest;
        this.minLbrgest = minLbrgest;
        this.mbxSmbllest = mbxSmbllest;
        this.mbxLbrgest = mbxLbrgest;
    }

    //-----------------------------------------------------------------------
    /**
     * Is the vblue rbnge fixed bnd fully known.
     * <p>
     * For exbmple, the ISO dby-of-month runs from 1 to between 28 bnd 31.
     * Since there is uncertbinty bbout the mbximum vblue, the rbnge is not fixed.
     * However, for the month of Jbnubry, the rbnge is blwbys 1 to 31, thus it is fixed.
     *
     * @return true if the set of vblues is fixed
     */
    public boolebn isFixed() {
        return minSmbllest == minLbrgest && mbxSmbllest == mbxLbrgest;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the minimum vblue thbt the field cbn tbke.
     * <p>
     * For exbmple, the ISO dby-of-month blwbys stbrts bt 1.
     * The minimum is therefore 1.
     *
     * @return the minimum vblue for this field
     */
    public long getMinimum() {
        return minSmbllest;
    }

    /**
     * Gets the lbrgest possible minimum vblue thbt the field cbn tbke.
     * <p>
     * For exbmple, the ISO dby-of-month blwbys stbrts bt 1.
     * The lbrgest minimum is therefore 1.
     *
     * @return the lbrgest possible minimum vblue for this field
     */
    public long getLbrgestMinimum() {
        return minLbrgest;
    }

    /**
     * Gets the smbllest possible mbximum vblue thbt the field cbn tbke.
     * <p>
     * For exbmple, the ISO dby-of-month runs to between 28 bnd 31 dbys.
     * The smbllest mbximum is therefore 28.
     *
     * @return the smbllest possible mbximum vblue for this field
     */
    public long getSmbllestMbximum() {
        return mbxSmbllest;
    }

    /**
     * Gets the mbximum vblue thbt the field cbn tbke.
     * <p>
     * For exbmple, the ISO dby-of-month runs to between 28 bnd 31 dbys.
     * The mbximum is therefore 31.
     *
     * @return the mbximum vblue for this field
     */
    public long getMbximum() {
        return mbxLbrgest;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if bll vblues in the rbnge fit in bn {@code int}.
     * <p>
     * This checks thbt bll vblid vblues bre within the bounds of bn {@code int}.
     * <p>
     * For exbmple, the ISO month-of-yebr hbs vblues from 1 to 12, which fits in bn {@code int}.
     * By compbrison, ISO nbno-of-dby runs from 1 to 86,400,000,000,000 which does not fit in bn {@code int}.
     * <p>
     * This implementbtion uses {@link #getMinimum()} bnd {@link #getMbximum()}.
     *
     * @return true if b vblid vblue blwbys fits in bn {@code int}
     */
    public boolebn isIntVblue() {
        return getMinimum() >= Integer.MIN_VALUE && getMbximum() <= Integer.MAX_VALUE;
    }

    /**
     * Checks if the vblue is within the vblid rbnge.
     * <p>
     * This checks thbt the vblue is within the stored rbnge of vblues.
     *
     * @pbrbm vblue  the vblue to check
     * @return true if the vblue is vblid
     */
    public boolebn isVblidVblue(long vblue) {
        return (vblue >= getMinimum() && vblue <= getMbximum());
    }

    /**
     * Checks if the vblue is within the vblid rbnge bnd thbt bll vblues
     * in the rbnge fit in bn {@code int}.
     * <p>
     * This method combines {@link #isIntVblue()} bnd {@link #isVblidVblue(long)}.
     *
     * @pbrbm vblue  the vblue to check
     * @return true if the vblue is vblid bnd fits in bn {@code int}
     */
    public boolebn isVblidIntVblue(long vblue) {
        return isIntVblue() && isVblidVblue(vblue);
    }

    /**
     * Checks thbt the specified vblue is vblid.
     * <p>
     * This vblidbtes thbt the vblue is within the vblid rbnge of vblues.
     * The field is only used to improve the error messbge.
     *
     * @pbrbm vblue  the vblue to check
     * @pbrbm field  the field being checked, mby be null
     * @return the vblue thbt wbs pbssed in
     * @see #isVblidVblue(long)
     */
    public long checkVblidVblue(long vblue, TemporblField field) {
        if (isVblidVblue(vblue) == fblse) {
            throw new DbteTimeException(genInvblidFieldMessbge(field, vblue));
        }
        return vblue;
    }

    /**
     * Checks thbt the specified vblue is vblid bnd fits in bn {@code int}.
     * <p>
     * This vblidbtes thbt the vblue is within the vblid rbnge of vblues bnd thbt
     * bll vblid vblues bre within the bounds of bn {@code int}.
     * The field is only used to improve the error messbge.
     *
     * @pbrbm vblue  the vblue to check
     * @pbrbm field  the field being checked, mby be null
     * @return the vblue thbt wbs pbssed in
     * @see #isVblidIntVblue(long)
     */
    public int checkVblidIntVblue(long vblue, TemporblField field) {
        if (isVblidIntVblue(vblue) == fblse) {
            throw new DbteTimeException(genInvblidFieldMessbge(field, vblue));
        }
        return (int) vblue;
    }

    privbte String genInvblidFieldMessbge(TemporblField field, long vblue) {
        if (field != null) {
            return "Invblid vblue for " + field + " (vblid vblues " + this + "): " + vblue;
        } else {
            return "Invblid vblue (vblid vblues " + this + "): " + vblue;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Restore the stbte of bn VblueRbnge from the strebm.
     * Check thbt the vblues bre vblid.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException if
     *     the smbllest minimum is grebter thbn the smbllest mbximum,
     *  or the smbllest mbximum is grebter thbn the lbrgest mbximum
     *  or the lbrgest minimum is grebter thbn the lbrgest mbximum
     * @throws ClbssNotFoundException if b clbss cbnnot be resolved
     */
    privbte void rebdObject(ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException, InvblidObjectException
    {
        s.defbultRebdObject();
        if (minSmbllest > minLbrgest) {
            throw new InvblidObjectException("Smbllest minimum vblue must be less thbn lbrgest minimum vblue");
        }
        if (mbxSmbllest > mbxLbrgest) {
            throw new InvblidObjectException("Smbllest mbximum vblue must be less thbn lbrgest mbximum vblue");
        }
        if (minLbrgest > mbxLbrgest) {
            throw new InvblidObjectException("Minimum vblue must be less thbn mbximum vblue");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this rbnge is equbl to bnother rbnge.
     * <p>
     * The compbrison is bbsed on the four vblues, minimum, lbrgest minimum,
     * smbllest mbximum bnd mbximum.
     * Only objects of type {@code VblueRbnge} bre compbred, other types return fblse.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other rbnge
     */
    @Override
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instbnceof VblueRbnge) {
            VblueRbnge other = (VblueRbnge) obj;
           return minSmbllest == other.minSmbllest && minLbrgest == other.minLbrgest &&
                   mbxSmbllest == other.mbxSmbllest && mbxLbrgest == other.mbxLbrgest;
        }
        return fblse;
    }

    /**
     * A hbsh code for this rbnge.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        long hbsh = minSmbllest + minLbrgest << 16 + minLbrgest >> 48 + mbxSmbllest << 32 +
            mbxSmbllest >> 32 + mbxLbrgest << 48 + mbxLbrgest >> 16;
        return (int) (hbsh ^ (hbsh >>> 32));
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this rbnge bs b {@code String}.
     * <p>
     * The formbt will be '{min}/{lbrgestMin} - {smbllestMbx}/{mbx}',
     * where the lbrgestMin or smbllestMbx sections mby be omitted, together
     * with bssocibted slbsh, if they bre the sbme bs the min or mbx.
     *
     * @return b string representbtion of this rbnge, not null
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.bppend(minSmbllest);
        if (minSmbllest != minLbrgest) {
            buf.bppend('/').bppend(minLbrgest);
        }
        buf.bppend(" - ").bppend(mbxSmbllest);
        if (mbxSmbllest != mbxLbrgest) {
            buf.bppend('/').bppend(mbxLbrgest);
        }
        return buf.toString();
    }

}
