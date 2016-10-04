/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A typesbfe enumerbtion for coding-error bctions.
 *
 * <p> Instbnces of this clbss bre used to specify how mblformed-input bnd
 * unmbppbble-chbrbcter errors bre to be hbndled by chbrset <b
 * href="ChbrsetDecoder.html#cbe">decoders</b> bnd <b
 * href="ChbrsetEncoder.html#cbe">encoders</b>.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public clbss CodingErrorAction {

    privbte String nbme;

    privbte CodingErrorAction(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Action indicbting thbt b coding error is to be hbndled by dropping the
     * erroneous input bnd resuming the coding operbtion.
     */
    public stbtic finbl CodingErrorAction IGNORE
        = new CodingErrorAction("IGNORE");

    /**
     * Action indicbting thbt b coding error is to be hbndled by dropping the
     * erroneous input, bppending the coder's replbcement vblue to the output
     * buffer, bnd resuming the coding operbtion.
     */
    public stbtic finbl CodingErrorAction REPLACE
        = new CodingErrorAction("REPLACE");

    /**
     * Action indicbting thbt b coding error is to be reported, either by
     * returning b {@link CoderResult} object or by throwing b {@link
     * ChbrbcterCodingException}, whichever is bppropribte for the method
     * implementing the coding process.
     */
    public stbtic finbl CodingErrorAction REPORT
        = new CodingErrorAction("REPORT");

    /**
     * Returns b string describing this bction.
     *
     * @return  A descriptive string
     */
    public String toString() {
        return nbme;
    }

}
