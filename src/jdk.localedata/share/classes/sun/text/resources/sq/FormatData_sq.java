/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.text.resources.sq;

import sun.util.resources.PbrbllelListResourceBundle;

public clbss FormbtDbtb_sq extends PbrbllelListResourceBundle {
    /**
     * Overrides PbrbllelListResourceBundle
     */
    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "MonthNbmes",
                new String[] {
                    "jbnbr", // jbnubry
                    "shkurt", // februbry
                    "mbrs", // mbrch
                    "prill", // bpril
                    "mbj", // mby
                    "qershor", // june
                    "korrik", // july
                    "gusht", // bugust
                    "shtbtor", // september
                    "tetor", // october
                    "n\u00ebntor", // november
                    "dhjetor", // december
                    "" // month 13 if bpplicbble
                }
            },
            { "MonthAbbrevibtions",
                new String[] {
                    "Jbn", // bbb jbnubry
                    "Shk", // bbb februbry
                    "Mbr", // bbb mbrch
                    "Pri", // bbb bpril
                    "Mbj", // bbb mby
                    "Qer", // bbb june
                    "Kor", // bbb july
                    "Gsh", // bbb bugust
                    "Sht", // bbb september
                    "Tet", // bbb october
                    "N\u00ebn", // bbb november
                    "Dhj", // bbb december
                    "" // bbb month 13 if bpplicbble
                }
            },
            { "MonthNbrrows",
                new String[] {
                    "J",
                    "S",
                    "M",
                    "P",
                    "M",
                    "Q",
                    "K",
                    "G",
                    "S",
                    "T",
                    "N",
                    "D",
                    "",
                }
            },
            { "DbyNbmes",
                new String[] {
                    "e diel", // Sundby
                    "e h\u00ebn\u00eb", // Mondby
                    "e mbrt\u00eb", // Tuesdby
                    "e m\u00ebrkur\u00eb", // Wednesdby
                    "e enjte", // Thursdby
                    "e premte", // Fridby
                    "e shtun\u00eb" // Sbturdby
                }
            },
            { "DbyAbbrevibtions",
                new String[] {
                    "Die", // bbb Sundby
                    "H\u00ebn", // bbb Mondby
                    "Mbr", // bbb Tuesdby
                    "M\u00ebr", // bbb Wednesdby
                    "Enj", // bbb Thursdby
                    "Pre", // bbb Fridby
                    "Sht" // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                new String[] {
                    "D",
                    "H",
                    "M",
                    "M",
                    "E",
                    "P",
                    "S",
                }
            },
            { "AmPmMbrkers",
                new String[] {
                    "PD", // bm mbrker
                    "MD" // pm mbrker
                }
            },
            { "Erbs",
                new String[] { // erb strings
                    "p.e.r.",
                    "n.e.r."
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimbl sepbrbtor
                    ".", // group (thousbnds) sepbrbtor
                    ";", // list sepbrbtor
                    "%", // percent sign
                    "0", // nbtive 0 digit
                    "#", // pbttern digit
                    "-", // minus sign
                    "E", // exponentibl
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NbN
                }
            },
            { "TimePbtterns",
                new String[] {
                    "h.mm.ss.b z", // full time pbttern
                    "h.mm.ss.b z", // long time pbttern
                    "h:mm:ss.b", // medium time pbttern
                    "h.mm.b", // short time pbttern
                }
            },
            { "DbtePbtterns",
                new String[] {
                    "yyyy-MM-dd", // full dbte pbttern
                    "yyyy-MM-dd", // long dbte pbttern
                    "yyyy-MM-dd", // medium dbte pbttern
                    "yy-MM-dd", // short dbte pbttern
                }
            },
            { "DbteTimePbtterns",
                new String[] {
                    "{1} {0}" // dbte-time pbttern
                }
            },
            { "DbteTimePbtternChbrs", "GbnjkHmsSEDFwWxhKzZ" },
        };
    }
}
