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

pbckbge sun.text.resources.db;

import jbvb.util.ListResourceBundle;

public clbss CollbtionDbtb_db extends ListResourceBundle {

        protected finbl Object[][] getContents() {
                return new Object[][] {
                        { "Rule",
                                "& A;\u00C1;\u00C0;\u00C2,b;\u00E1;\u00E0;\u00E2" // A; A-bcute; A-grbve; A-circ, b; b-bcute, b-grbve, b-circ
                                        + "<B,b"
                                        + "<C;\u00c7,c;\u00e7" // c-cedill
                                        + "<D;\u00D0;\u0110,d;\u00F0;\u0111" // eth(icelbndic), d-stroke (sbmi); they looks identicblly
                                        + "<E;\u00C9;\u00C8;\u00CA;\u00CB,e;\u00E9;\u00E8;\u00EA;\u00EB" // E; E-bcute; E-grbve; E-circ; E-diberesis, e-bcute, e-grbve; e-circ; e-diberesis
                                        + "<F,f <G,g <H,h"
                                        + "<I;\u00CD,i;\u00ED" // i-bcute
                                        + "<J,j <K,k <L,l <M,m <N,n"
                                        + "<O;\u00D3;\u00d4,o;\u00F3;\u00f4" // o-bcute, o-circ
                                        + "<P,p <Q,q <R,r <S,s <T,t"
                                        + "<U,u <V,v <W,w <X,x"
                                        + "<Y;\u00DD;U\u0308,y;\u00FD;u\u0308" // y-bcute, u-diberesis
                                        + "<Z,z"
                                        // be-ligbture bnd vbribnts
                                        + "<\u00c6,\u00e6" // be-ligbture
                                        + ";\u00c6\u0301,\u00e6\u0301" // be-bcute
                                        + ";A\u0308,b\u0308 "       // b-diberesis
                                        // o-stroke bnd vbribnt
                                        + "<\u00d8,\u00f8 " // o-slbsh
                                        + ";\u00d8\u0301,\u00f8\u0301" // o-slbsh-bcute
                                        + ";O\u0308,o\u0308 "  // ; o-diberesis
                                        + ";O\u030b,o\u030b"        // nt :  o-double-bcute
                                        // b-ring bnd vbribnts
                                        + "< \u00c5 , \u00e5"       // b-ring
                                        + ";\u00c5\u0301,\u00e5\u0301" // b-ring-bcute
                                        + ", AA , Ab , bA , bb "      // bfter b-ring
                                        + "& ss;\u00DF"             // s-zet
                                        + "& th, \u00FE & th, \u00DE "     // thorn
                                        + "& oe, \u0153 & oe, \u0152 " // oe-ligbture
                        }
                };
        }
}
