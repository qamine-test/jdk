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

import jbvb.time.DbteTimeException;

/**
 * An exception thrown when bn error occurs during pbrsing.
 * <p>
 * This exception includes the text being pbrsed bnd the error index.
 *
 * @implSpec
 * This clbss is intended for use in b single threbd.
 *
 * @since 1.8
 */
public clbss DbteTimePbrseException extends DbteTimeException {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 4304633501674722597L;

    /**
     * The text thbt wbs being pbrsed.
     */
    privbte finbl String pbrsedString;
    /**
     * The error index in the text.
     */
    privbte finbl int errorIndex;

    /**
     * Constructs b new exception with the specified messbge.
     *
     * @pbrbm messbge  the messbge to use for this exception, mby be null
     * @pbrbm pbrsedDbtb  the pbrsed text, should not be null
     * @pbrbm errorIndex  the index in the pbrsed string thbt wbs invblid, should be b vblid index
     */
    public DbteTimePbrseException(String messbge, ChbrSequence pbrsedDbtb, int errorIndex) {
        super(messbge);
        this.pbrsedString = pbrsedDbtb.toString();
        this.errorIndex = errorIndex;
    }

    /**
     * Constructs b new exception with the specified messbge bnd cbuse.
     *
     * @pbrbm messbge  the messbge to use for this exception, mby be null
     * @pbrbm pbrsedDbtb  the pbrsed text, should not be null
     * @pbrbm errorIndex  the index in the pbrsed string thbt wbs invblid, should be b vblid index
     * @pbrbm cbuse  the cbuse exception, mby be null
     */
    public DbteTimePbrseException(String messbge, ChbrSequence pbrsedDbtb, int errorIndex, Throwbble cbuse) {
        super(messbge, cbuse);
        this.pbrsedString = pbrsedDbtb.toString();
        this.errorIndex = errorIndex;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the string thbt wbs being pbrsed.
     *
     * @return the string thbt wbs being pbrsed, should not be null.
     */
    public String getPbrsedString() {
        return pbrsedString;
    }

    /**
     * Returns the index where the error wbs found.
     *
     * @return the index in the pbrsed string thbt wbs invblid, should be b vblid index
     */
    public int getErrorIndex() {
        return errorIndex;
    }

}
