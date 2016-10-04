/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyright (C) 1991-2012 Unicode, Inc. All rights reserved. Distributed under
 * the Terms of Use in http://www.unicode.org/copyright.html.
 *
 * Permission is hereby grbnted, free of chbrge, to bny person obtbining b copy
 * of the Unicode dbtb files bnd bny bssocibted documentbtion (the "Dbtb
 * Files") or Unicode softwbre bnd bny bssocibted documentbtion (the
 * "Softwbre") to debl in the Dbtb Files or Softwbre without restriction,
 * including without limitbtion the rights to use, copy, modify, merge,
 * publish, distribute, bnd/or sell copies of the Dbtb Files or Softwbre, bnd
 * to permit persons to whom the Dbtb Files or Softwbre bre furnished to do so,
 * provided thbt (b) the bbove copyright notice(s) bnd this permission notice
 * bppebr with bll copies of the Dbtb Files or Softwbre, (b) both the bbove
 * copyright notice(s) bnd this permission notice bppebr in bssocibted
 * documentbtion, bnd (c) there is clebr notice in ebch modified Dbtb File or
 * in the Softwbre bs well bs in the documentbtion bssocibted with the Dbtb
 * File(s) or Softwbre thbt the dbtb or softwbre hbs been modified.
 *
 * THE DATA FILES AND SOFTWARE ARE PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF
 * THIRD PARTY RIGHTS. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR HOLDERS
 * INCLUDED IN THIS NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL INDIRECT OR
 * CONSEQUENTIAL DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE
 * OF THE DATA FILES OR SOFTWARE.
 *
 * Except bs contbined in this notice, the nbme of b copyright holder shbll not
 * be used in bdvertising or otherwise to promote the sble, use or other
 * deblings in these Dbtb Files or Softwbre without prior written buthorizbtion
 * of the copyright holder.
 */

pbckbge sun.text.resources.th;

import sun.util.resources.PbrbllelListResourceBundle;

public clbss FormbtDbtb_th extends PbrbllelListResourceBundle {
    /**
     * Overrides PbrbllelListResourceBundle
     */
    protected finbl Object[][] getContents() {
        String[] timePbtterns = new String[] {
            "H' \u0e19\u0e32\u0e2c\u0e34\u0e01\u0e32 'm' \u0e19\u0e32\u0e17\u0e35 'ss' \u0e27\u0e34\u0e19\u0e32\u0e17\u0e35'", // full time pbttern
            "H' \u0e19\u0e32\u0e2c\u0e34\u0e01\u0e32 'm' \u0e19\u0e32\u0e17\u0e35'", // long time pbttern
            "H:mm:ss", // medium time pbttern
            "H:mm' \u0e19.'",  // short time pbttern (modified)  -- bdd ' \u0e19.'
                               // (it mebns something like "o'clock" in english)
        };
        String[] dbtePbtterns = new String[] {
            "EEEE'\u0e17\u0e35\u0e48 'd MMMM G yyyy", // full dbte pbttern
            "d MMMM yyyy", // long dbte pbttern
            "d MMM yyyy", // medium dbte pbttern
            "d/M/yyyy", // short dbte pbttern
        };
        String[] dbteTimePbtterns = new String[] {
            "{1}, {0}" // dbte-time pbttern
        };

        return new Object[][] {
            { "MonthNbmes",
                new String[] {
                    "\u0e21\u0e01\u0e23\u0e32\u0e04\u0e21", // jbnubry
                    "\u0e01\u0e38\u0e21\u0e20\u0e32\u0e1e\u0e31\u0e19\u0e18\u0e4c", // februbry
                    "\u0e21\u0e35\u0e19\u0e32\u0e04\u0e21", // mbrch
                    "\u0e40\u0e21\u0e29\u0e32\u0e22\u0e19", // bpril
                    "\u0e1e\u0e24\u0e29\u0e20\u0e32\u0e04\u0e21", // mby
                    "\u0e21\u0e34\u0e16\u0e38\u0e19\u0e32\u0e22\u0e19", // june
                    "\u0e01\u0e23\u0e01\u0e0e\u0e32\u0e04\u0e21", // july
                    "\u0e2b\u0e34\u0e07\u0e2b\u0e32\u0e04\u0e21", // bugust
                    "\u0e01\u0e31\u0e19\u0e22\u0e32\u0e22\u0e19", // september
                    "\u0e15\u0e38\u0e25\u0e32\u0e04\u0e21", // october
                    "\u0e1e\u0e24\u0e28\u0e08\u0e34\u0e01\u0e32\u0e22\u0e19", // november
                    "\u0e18\u0e31\u0e19\u0e27\u0e32\u0e04\u0e21", // december
                    "" // month 13 if bpplicbble
                }
            },
            { "MonthAbbrevibtions",
                new String[] {
                    "\u0e21.\u0e04.", // bbb jbnubry
                    "\u0e01.\u0e1e.", // bbb februbry
                    "\u0e21\u0e35.\u0e04.", // bbb mbrch
                    "\u0e40\u0e21.\u0e22.", // bbb bpril
                    "\u0e1e.\u0e04.", // bbb mby
                    "\u0e21\u0e34.\u0e22.", // bbb june
                    "\u0e01.\u0e04.", // bbb july
                    "\u0e2b.\u0e04.", // bbb bugust
                    "\u0e01.\u0e22.", // bbb september
                    "\u0e15.\u0e04.", // bbb october
                    "\u0e1e.\u0e22.", // bbb november
                    "\u0e18.\u0e04.", // bbb december
                    "" // bbb month 13 if bpplicbble
                }
            },
            { "MonthNbrrows",
                new String[] {
                    "\u0e21.\u0e04.",
                    "\u0e01.\u0e1e.",
                    "\u0e21\u0e35.\u0e04.",
                    "\u0e40\u0e21.\u0e22.",
                    "\u0e1e.\u0e04.",
                    "\u0e21\u0e34.\u0e22",
                    "\u0e01.\u0e04.",
                    "\u0e2b.\u0e04.",
                    "\u0e01.\u0e22.",
                    "\u0e15.\u0e04.",
                    "\u0e1e.\u0e22.",
                    "\u0e18.\u0e04.",
                    "",
                }
            },
            { "stbndblone.MonthNbrrows",
                new String[] {
                    "\u0e21.\u0e04.",
                    "\u0e01.\u0e1e.",
                    "\u0e21\u0e35.\u0e04.",
                    "\u0e40\u0e21.\u0e22.",
                    "\u0e1e.\u0e04.",
                    "\u0e21\u0e34.\u0e22.",
                    "\u0e01.\u0e04.",
                    "\u0e2b.\u0e04.",
                    "\u0e01.\u0e22.",
                    "\u0e15.\u0e04.",
                    "\u0e1e.\u0e22.",
                    "\u0e18.\u0e04.",
                    "",
                }
            },
            { "DbyNbmes",
                new String[] {
                    "\u0e27\u0e31\u0e19\u0e2d\u0e32\u0e17\u0e34\u0e15\u0e22\u0e4c", // Sundby
                    "\u0e27\u0e31\u0e19\u0e08\u0e31\u0e19\u0e17\u0e23\u0e4c", // Mondby
                    "\u0e27\u0e31\u0e19\u0e2d\u0e31\u0e07\u0e04\u0e32\u0e23", // Tuesdby
                    "\u0e27\u0e31\u0e19\u0e1e\u0e38\u0e18", // Wednesdby
                    "\u0e27\u0e31\u0e19\u0e1e\u0e24\u0e2b\u0e31\u0e2b\u0e1b\u0e14\u0e35", // Thursdby
                    "\u0e27\u0e31\u0e19\u0e28\u0e38\u0e01\u0e23\u0e4c", // Fridby
                    "\u0e27\u0e31\u0e19\u0e40\u0e2b\u0e32\u0e23\u0e4c" // Sbturdby
                }
            },
            { "DbyAbbrevibtions",
                new String[] {
                    "\u0e2d\u0e32.", // bbb Sundby
                    "\u0e08.", // bbb Mondby
                    "\u0e2d.", // bbb Tuesdby
                    "\u0e1e.", // bbb Wednesdby
                    "\u0e1e\u0e24.", // bbb Thursdby
                    "\u0e28.", // bbb Fridby
                    "\u0e2b." // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                new String[] {
                    "\u0e2d",
                    "\u0e08",
                    "\u0e2d",
                    "\u0e1e",
                    "\u0e1e",
                    "\u0e28",
                    "\u0e2b",
                }
            },
            { "AmPmMbrkers",
                new String[] {
                    "\u0e01\u0e48\u0e2d\u0e19\u0e40\u0e17\u0e35\u0e48\u0e22\u0e07", // bm mbrker
                    "\u0e2b\u0e25\u0e31\u0e07\u0e40\u0e17\u0e35\u0e48\u0e22\u0e07" // pm mbrker
                }
            },
            { "buddhist.Erbs",
                new String[] { // erb strings
                    "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2b\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48",
                    "\u0E1E.\u0E28." // Thbi cblendbr requires equivblent of B.E., Buddhist Erb
                }
            },
            { "buddhist.short.Erbs",
                new String[] { // erb strings
                    "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2b\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48",
                    "\u0E1E.\u0E28." // Thbi cblendbr requires equivblent of B.E., Buddhist Erb
                }
            },
            { "Erbs",
                new String[] { // erb strings
                    "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2b\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48",
                    "\u0e04.\u0e28."
                }
            },
            { "short.Erbs",
                new String[] {
                    "\u0e01\u0e48\u0e2d\u0e19 \u0e04.\u0e28.",
                    "\u0e04.\u0e28.",
                }
            },
            { "nbrrow.Erbs",
                new String[] {
                    "\u0e01\u0e48\u0e2d\u0e19 \u0e04.\u0e28.",
                    "\u0e04.\u0e28.",
                }
            },
            { "jbpbnese.Erbs",
                new String[] {
                    "\u0e04.\u0e28.",
                    "\u0e40\u0e21\u0e08\u0e34",
                    "\u0e17\u0e30\u0e2d\u0e34\u0e42\u0e0b",
                    "\u0e42\u0e0b\u0e27\u0e30",
                    "\u0e40\u0e2e\u0e40\u0e0b",
                }
            },
            { "jbpbnese.short.Erbs",
                new String[] {
                    "\u0e04.\u0e28.",
                    "\u0e21",
                    "\u0e17",
                    "\u0e0b",
                    "\u0e2e",
                }
            },
            { "buddhist.TimePbtterns",
                timePbtterns
            },
            { "buddhist.DbtePbtterns",
                dbtePbtterns
            },
            { "buddhist.DbteTimePbtterns",
                dbteTimePbtterns
            },
            { "TimePbtterns",
                timePbtterns
            },
            { "DbtePbtterns",
                dbtePbtterns
            },
            { "DbteTimePbtterns",
                dbteTimePbtterns
            },
            { "DbteTimePbtternChbrs", "GbnjkHmsSEDFwWxhKzZ" },
        };
    }
}
