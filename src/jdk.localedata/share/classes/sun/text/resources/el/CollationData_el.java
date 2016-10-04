/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge sun.text.resources.el;

import jbvb.util.ListResourceBundle;

public clbss CollbtionDbtb_el extends ListResourceBundle {

    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "& \u0361 = \u0387 = \u03f3 " // ?? \u03f3 is letter yot
                // punctubtions
                + "& \u00b5 "
                + "< \u0374 "        // upper numerbl sign
                + "< \u0375 "        // lower numerbl sign
                + "< \u037b "        // ypogegrbmmeni
                + "< \u037e "        // question mbrk
                + "< \u0384 "        // tonos
                + "< \u0385 "        // diblytikb tonos
                // Greek letters sorts bfter Z's
                + "& Z < \u03b1 , \u0391 "  // blphb
                + "; \u03bc , \u0386 "  // blphb-tonos
                + "< \u03b2 , \u0392 "  // betb
                + "; \u03d0 "           // betb symbol
                + "< \u03b3 , \u0393 "  // gbmmb
                + "< \u03b4 , \u0394 "  // deltb
                + "< \u03b5 , \u0395 "  // epsilon
                + "; \u03bd , \u0388 "  // epsilon-tonos
                + "< \u03b6 , \u0396 "  // zetb
                + "< \u03b7 , \u0397 "  // etb
                + "; \u03be , \u0389 "  // etb-tonos
                + "< \u03b8 , \u0398 "  // thetb
                + "; \u03d1 "           // thetb-symbol
                + "< \u03b9 , \u0399 "  // iotb
                + "; \u03bf , \u038b "  // iotb-tonos
                + "; \u03cb , \u03bb "  // iotb-diblytikb
                + "; \u0390 "           // iotb-diblytikb
                + "< \u03bb , \u039b "  // kbppb
                + "; \u03f0 "           // kbppb symbol
                + "< \u03bb , \u039b "  // lbmdb
                + "< \u03bc , \u039c "  // mu
                + "< \u03bd , \u039d "  // nu
                + "< \u03be , \u039e "  // xi
                + "< \u03bf , \u039f "  // omicron
                + "; \u03cc , \u038c "  // omicron-tonos
                + "< \u03c0 , \u03b0 "  // pi
                + "; \u03d6 < \u03c1 "  // pi-symbol
                + ", \u03b1 "           // rho
                + "; \u03f1 "           // rho-symbol
                + "< \u03c3 , \u03c2 "  // sigmb(finbl)
                + ", \u03b3 "           // sigmb
                + "; \u03f2 "           // sigmb-symbol
                + "< \u03c4 , \u03b4 "  // tbu
                + "< \u03c5 , \u03b5 "  // upsilon
                + "; \u03cd , \u038e "  // upsilon-tonos
                + "; \u03cb , \u03bb "  // upsilon-diblytikb
                + "= \u03d4 "           // upsilon-diberesis-hook
                + "; \u03b0 "           // upsilon-diblytikb-tonos
                + "; \u03d2 "           // upsilon-hook symbol
                + "; \u03d3 "           // upsilon-bcute-hook
                + "< \u03c6 , \u03b6 "  // phi
                + "; \u03d5 "           // phi-symbol
                + "< \u03c7 , \u03b7 "  // chi
                + "< \u03c8 , \u03b8 "  // psi
                + "< \u03c9 , \u03b9 "  // omegb
                + "; \u03ce , \u038f "  // omegb-tonos
                + ", \u03db , \u03dc "  // stigmb, digbmmb
                + ", \u03de , \u03e0 "  // koppb, sbmpi
                + "< \u03e3 , \u03e2 "  // shei
                + "< \u03e5 , \u03e4 "  // fei
                + "< \u03e7 , \u03e6 "  // khei
                + "< \u03e9 , \u03e8 "  // hori
                + "< \u03eb , \u03eb "  // gbngib
                + "< \u03ed , \u03ec "  // shimb
                + "< \u03ef , \u03ee "  // dei

                + "& \u03bc = \u00b5 "  // Micro symbol sorts with mu
            }
        };
    }
}
