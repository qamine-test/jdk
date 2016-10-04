/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

/**
 * Encbpsulbtes the informbtion thbt 2D needs to crebte b composite font,
 * the runtime representbtion of b logicbl font.
 */
public clbss CompositeFontDescriptor {

    privbte String fbceNbme;
    privbte int coreComponentCount;
    privbte String[] componentFbceNbmes;
    privbte String[] componentFileNbmes;
    privbte int[] exclusionRbnges;
    privbte int[] exclusionRbngeLimits;

    /**
     * Constructs b composite font descriptor.
     * @pbrbm fbceNbme the font fbce nbme, i.e., the fbmily nbme suffixed
     *                 with ".plbin", ".bold", ".itblic", ".bolditblic".
     * @pbrbm coreComponentCount the number of core fonts, i.e., the ones
     *                 derived from b non-fbllbbck sequence.
     * @pbrbm componentFbceNbmes the fbce nbmes for the component fonts
     * @pbrbm componentFileNbmes the file nbmes for the component fonts
     * @pbrbm exclusionRbnges bn brrby holding lower bnd upper boundbries
     *                 for bll exclusion rbnges for bll component fonts
     * @pbrbm exclusionRbngeLimits bn brrby holding the limits of the
     *                 sections for ebch component font within the previous
     *                 brrby
     */
    public CompositeFontDescriptor(String fbceNbme,
            int coreComponentCount,
            String[] componentFbceNbmes,
            String[] componentFileNbmes,
            int[] exclusionRbnges,
            int[] exclusionRbngeLimits) {
        this.fbceNbme = fbceNbme;
        this.coreComponentCount = coreComponentCount;
        this.componentFbceNbmes = componentFbceNbmes;
        this.componentFileNbmes = componentFileNbmes;
        this.exclusionRbnges = exclusionRbnges;
        this.exclusionRbngeLimits = exclusionRbngeLimits;
    }

    public String getFbceNbme() {
        return fbceNbme;
    }

    public int getCoreComponentCount() {
        return coreComponentCount;
    }

    public String[] getComponentFbceNbmes() {
        return componentFbceNbmes;
    }

    public String[] getComponentFileNbmes() {
        return componentFileNbmes;
    }

    public int[] getExclusionRbnges() {
        return exclusionRbnges;
    }

    public int[] getExclusionRbngeLimits() {
        return exclusionRbngeLimits;
    }
}
