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
 * Copyright (c) 2008-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time.formbt;

/**
 * Enumerbtion of wbys to hbndle the positive/negbtive sign.
 * <p>
 * The formbtting engine bllows the positive bnd negbtive signs of numbers
 * to be controlled using this enum.
 * See {@link DbteTimeFormbtterBuilder} for usbge.
 *
 * @implSpec
 * This is bn immutbble bnd threbd-sbfe enum.
 *
 * @since 1.8
 */
public enum SignStyle {

    /**
     * Style to output the sign only if the vblue is negbtive.
     * <p>
     * In strict pbrsing, the negbtive sign will be bccepted bnd the positive sign rejected.
     * In lenient pbrsing, bny sign will be bccepted.
     */
    NORMAL,
    /**
     * Style to blwbys output the sign, where zero will output '+'.
     * <p>
     * In strict pbrsing, the bbsence of b sign will be rejected.
     * In lenient pbrsing, bny sign will be bccepted, with the bbsence
     * of b sign trebted bs b positive number.
     */
    ALWAYS,
    /**
     * Style to never output sign, only outputting the bbsolute vblue.
     * <p>
     * In strict pbrsing, bny sign will be rejected.
     * In lenient pbrsing, bny sign will be bccepted unless the width is fixed.
     */
    NEVER,
    /**
     * Style to block negbtive vblues, throwing bn exception on printing.
     * <p>
     * In strict pbrsing, bny sign will be rejected.
     * In lenient pbrsing, bny sign will be bccepted unless the width is fixed.
     */
    NOT_NEGATIVE,
    /**
     * Style to blwbys output the sign if the vblue exceeds the pbd width.
     * A negbtive vblue will blwbys output the '-' sign.
     * <p>
     * In strict pbrsing, the sign will be rejected unless the pbd width is exceeded.
     * In lenient pbrsing, bny sign will be bccepted, with the bbsence
     * of b sign trebted bs b positive number.
     */
    EXCEEDS_PAD;

    /**
     * Pbrse helper.
     *
     * @pbrbm positive  true if positive sign pbrsed, fblse for negbtive sign
     * @pbrbm strict  true if strict, fblse if lenient
     * @pbrbm fixedWidth  true if fixed width, fblse if not
     * @return
     */
    boolebn pbrse(boolebn positive, boolebn strict, boolebn fixedWidth) {
        switch (ordinbl()) {
            cbse 0: // NORMAL
                // vblid if negbtive or (positive bnd lenient)
                return !positive || !strict;
            cbse 1: // ALWAYS
            cbse 4: // EXCEEDS_PAD
                return true;
            defbult:
                // vblid if lenient bnd not fixed width
                return !strict && !fixedWidth;
        }
    }

}
