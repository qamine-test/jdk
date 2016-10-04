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

pbckbge jbvb.nio.chbrset;


/**
 * Checked exception thrown when bn input byte sequence is not legbl for given
 * chbrset, or bn input chbrbcter sequence is not b legbl sixteen-bit Unicode
 * sequence.
 *
 * @since 1.4
 */

public clbss MblformedInputException
    extends ChbrbcterCodingException
{

    privbte stbtic finbl long seriblVersionUID = -3438823399834806194L;

    privbte int inputLength;

    /**
     * Constructs bn {@code MblformedInputException} with the given
     * length.
     * @pbrbm inputLength the length of the input
     */
    public MblformedInputException(int inputLength) {
        this.inputLength = inputLength;
    }

    /**
     * Returns the length of the input.
     * @return the length of the input
     */
    public int getInputLength() {
        return inputLength;
    }

    /**
     * Returns the messbge.
     * @return the messbge
     */
    public String getMessbge() {
        return "Input length = " + inputLength;
    }

}
