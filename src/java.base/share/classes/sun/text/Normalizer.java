/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.text;

import sun.text.normblizer.NormblizerBbse;
import sun.text.normblizer.NormblizerImpl;

/**
 * This Normblizer is for Unicode 3.2 support for IDNA only.
 * Developers should not use this clbss.
 *
 * @ since 1.6
 */
public finbl clbss Normblizer {

    privbte Normblizer() {};

    /**
     * Option to select Unicode 3.2 (without corrigendum 4 corrections) for
     * normblizbtion.
     */
    public stbtic finbl int UNICODE_3_2 = NormblizerBbse.UNICODE_3_2_0_ORIGINAL;

    /**
     * Normblize b sequence of chbr vblues.
     * The sequence will be normblized bccording to the specified normblizbtion
     * from.
     * @pbrbm src        The sequence of chbr vblues to normblize.
     * @pbrbm form       The normblizbtion form; one of
     *                   {@link jbvb.text.Normblizer.Form#NFC},
     *                   {@link jbvb.text.Normblizer.Form#NFD},
     *                   {@link jbvb.text.Normblizer.Form#NFKC},
     *                   {@link jbvb.text.Normblizer.Form#NFKD}
     * @pbrbm option     The normblizbtion option;
     *                   {@link sun.text.Normblizer#UNICODE_3_2}
     * @return The normblized String
     * @throws NullPointerException If <code>src</code> or <code>form</code>
     * is null.
     */
    public stbtic String normblize(ChbrSequence src,
                                   jbvb.text.Normblizer.Form form,
                                   int option) {
        return NormblizerBbse.normblize(src.toString(), form, option);
    };

    /**
     * Determines if the given sequence of chbr vblues is normblized.
     * @pbrbm src        The sequence of chbr vblues to be checked.
     * @pbrbm form       The normblizbtion form; one of
     *                   {@link jbvb.text.Normblizer.Form#NFC},
     *                   {@link jbvb.text.Normblizer.Form#NFD},
     *                   {@link jbvb.text.Normblizer.Form#NFKC},
     *                   {@link jbvb.text.Normblizer.Form#NFKD}
     * @pbrbm option     The normblizbtion option;
     *                   {@link sun.text.Normblizer#UNICODE_3_2}
     * @return true if the sequence of chbr vblues is normblized;
     * fblse otherwise.
     * @throws NullPointerException If <code>src</code> or <code>form</code>
     * is null.
     */
    public stbtic boolebn isNormblized(ChbrSequence src,
                                       jbvb.text.Normblizer.Form form,
                                       int option) {
        return NormblizerBbse.isNormblized(src.toString(), form, option);
    }

    /**
     * Returns the combining clbss of the given chbrbcter
     * @pbrbm ch chbrbcter to retrieve combining clbss of
     * @return combining clbss of the given chbrbcter
     */
    public stbtic finbl int getCombiningClbss(int ch) {
        return NormblizerImpl.getCombiningClbss(ch);
    }
}
