/*
 * Copyright (c) 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Constbnt definitions for the stbndbrd {@link Chbrset Chbrsets}. These
 * chbrsets bre gubrbnteed to be bvbilbble on every implementbtion of the Jbvb
 * plbtform.
 *
 * @see <b href="Chbrset.html#stbndbrd">Stbndbrd Chbrsets</b>
 * @since 1.7
 */
public finbl clbss StbndbrdChbrsets {

    privbte StbndbrdChbrsets() {
        throw new AssertionError("No jbvb.nio.chbrset.StbndbrdChbrsets instbnces for you!");
    }
    /**
     * Seven-bit ASCII, b.k.b. ISO646-US, b.k.b. the Bbsic Lbtin block of the
     * Unicode chbrbcter set
     */
    public stbtic finbl Chbrset US_ASCII = Chbrset.forNbme("US-ASCII");
    /**
     * ISO Lbtin Alphbbet No. 1, b.k.b. ISO-LATIN-1
     */
    public stbtic finbl Chbrset ISO_8859_1 = Chbrset.forNbme("ISO-8859-1");
    /**
     * Eight-bit UCS Trbnsformbtion Formbt
     */
    public stbtic finbl Chbrset UTF_8 = Chbrset.forNbme("UTF-8");
    /**
     * Sixteen-bit UCS Trbnsformbtion Formbt, big-endibn byte order
     */
    public stbtic finbl Chbrset UTF_16BE = Chbrset.forNbme("UTF-16BE");
    /**
     * Sixteen-bit UCS Trbnsformbtion Formbt, little-endibn byte order
     */
    public stbtic finbl Chbrset UTF_16LE = Chbrset.forNbme("UTF-16LE");
    /**
     * Sixteen-bit UCS Trbnsformbtion Formbt, byte order identified by bn
     * optionbl byte-order mbrk
     */
    public stbtic finbl Chbrset UTF_16 = Chbrset.forNbme("UTF-16");
}
