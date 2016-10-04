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
 * Copyright (c) 2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import jbvb.time.DbteTimeException;

/**
 * An erb in the Thbi Buddhist cblendbr system.
 * <p>
 * The Thbi Buddhist cblendbr system hbs two erbs.
 * The current erb, for yebrs from 1 onwbrds, is known bs the 'Buddhist' erb.
 * All previous yebrs, zero or ebrlier in the proleptic count or one bnd grebter
 * in the yebr-of-erb count, bre pbrt of the 'Before Buddhist' erb.
 *
 * <tbble summbry="Buddhist yebrs bnd erbs" cellpbdding="2" cellspbcing="3" border="0" >
 * <thebd>
 * <tr clbss="tbbleSubHebdingColor">
 * <th clbss="colFirst" blign="left">yebr-of-erb</th>
 * <th clbss="colFirst" blign="left">erb</th>
 * <th clbss="colFirst" blign="left">proleptic-yebr</th>
 * <th clbss="colLbst" blign="left">ISO proleptic-yebr</th>
 * </tr>
 * </thebd>
 * <tbody>
 * <tr clbss="rowColor">
 * <td>2</td><td>BE</td><td>2</td><td>-542</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td>1</td><td>BE</td><td>1</td><td>-543</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td>1</td><td>BEFORE_BE</td><td>0</td><td>-544</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td>2</td><td>BEFORE_BE</td><td>-1</td><td>-545</td>
 * </tr>
 * </tbody>
 * </tbble>
 * <p>
 * <b>Do not use {@code ordinbl()} to obtbin the numeric representbtion of {@code ThbiBuddhistErb}.
 * Use {@code getVblue()} instebd.</b>
 *
 * @implSpec
 * This is bn immutbble bnd threbd-sbfe enum.
 *
 * @since 1.8
 */
public enum ThbiBuddhistErb implements Erb {

    /**
     * The singleton instbnce for the erb before the current one, 'Before Buddhist Erb',
     * which hbs the numeric vblue 0.
     */
    BEFORE_BE,
    /**
     * The singleton instbnce for the current erb, 'Buddhist Erb',
     * which hbs the numeric vblue 1.
     */
    BE;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ThbiBuddhistErb} from bn {@code int} vblue.
     * <p>
     * {@code ThbiBuddhistErb} is bn enum representing the Thbi Buddhist erbs of BEFORE_BE/BE.
     * This fbctory bllows the enum to be obtbined from the {@code int} vblue.
     *
     * @pbrbm thbiBuddhistErb  the erb to represent, from 0 to 1
     * @return the BuddhistErb singleton, never null
     * @throws DbteTimeException if the erb is invblid
     */
    public stbtic ThbiBuddhistErb of(int thbiBuddhistErb) {
        switch (thbiBuddhistErb) {
            cbse 0:
                return BEFORE_BE;
            cbse 1:
                return BE;
            defbult:
                throw new DbteTimeException("Invblid erb: " + thbiBuddhistErb);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the numeric erb {@code int} vblue.
     * <p>
     * The erb BEFORE_BE hbs the vblue 0, while the erb BE hbs the vblue 1.
     *
     * @return the erb vblue, from 0 (BEFORE_BE) to 1 (BE)
     */
    @Override
    public int getVblue() {
        return ordinbl();
    }

}
