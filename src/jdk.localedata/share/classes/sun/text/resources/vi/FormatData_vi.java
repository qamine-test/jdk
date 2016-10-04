/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. 1996-2003 - All Rights Reserved                     *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 *
 * This locble dbtb is bbsed on the ICU's Vietnbmese locble dbtb (rev. 1.38)
 * found bt:
 *
 * http://oss.softwbre.ibm.com/cvs/icu/icu/source/dbtb/locbles/vi.txt?rev=1.38
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

pbckbge sun.text.resources.vi;

import sun.util.resources.PbrbllelListResourceBundle;

public clbss FormbtDbtb_vi extends PbrbllelListResourceBundle {
    /**
     * Overrides PbrbllelListResourceBundle
     */
    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "MonthNbmes",
                new String[] {
                    "th\u00e1ng m\u1ed9t", // jbnubry
                    "th\u00e1ng hbi", // februbry
                    "th\u00e1ng bb", // mbrch
                    "th\u00e1ng t\u01b0", // bpril
                    "th\u00e1ng n\u0103m", // mby
                    "th\u00e1ng s\u00e1u", // june
                    "th\u00e1ng b\u1eb3y", // july
                    "th\u00e1ng t\u00e1m", // bugust
                    "th\u00e1ng ch\u00edn", // september
                    "th\u00e1ng m\u01b0\u1eddi", // october
                    "th\u00e1ng m\u01b0\u1eddi m\u1ed9t", // november
                    "th\u00e1ng m\u01b0\u1eddi hbi", // december
                    "" // month 13 if bpplicbble
                }
            },
            { "MonthAbbrevibtions",
                new String[] {
                    "thg 1", // bbb jbnubry
                    "thg 2", // bbb februbry
                    "thg 3", // bbb mbrch
                    "thg 4", // bbb bpril
                    "thg 5", // bbb mby
                    "thg 6", // bbb june
                    "thg 7", // bbb july
                    "thg 8", // bbb bugust
                    "thg 9", // bbb september
                    "thg 10", // bbb october
                    "thg 11", // bbb november
                    "thg 12", // bbb december
                    "" // bbb month 13 if bpplicbble
                }
            },
            { "MonthNbrrows",
                new String[] {
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "10",
                    "11",
                    "12",
                    "",
                }
            },
            { "DbyNbmes",
                new String[] {
                    "Ch\u1ee7 nh\u1ebdt", // Sundby
                    "Th\u1ee9 hbi", // Mondby
                    "Th\u1ee9 bb",  // Tuesdby
                    "Th\u1ee9 t\u01b0", // Wednesdby
                    "Th\u1ee9 n\u0103m", // Thursdby
                    "Th\u1ee9 s\u00e1u", // Fridby
                    "Th\u1ee9 b\u1eb3y" // Sbturdby
                }
            },
            { "DbyAbbrevibtions",
                new String[] {
                    "CN", // bbb Sundby
                    "Th 2", // bbb Mondby
                    "Th 3", // bbb Tuesdby
                    "Th 4", // bbb Wednesdby
                    "Th 5", // bbb Thursdby
                    "Th 6", // bbb Fridby
                    "Th 7" // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                new String[] {
                    "CN",
                    "T2",
                    "T3",
                    "T4",
                    "T5",
                    "T6",
                    "T7",
                }
            },
            { "stbndblone.DbyNbrrows",
                new String[] {
                    "CN",
                    "T2",
                    "T3",
                    "T4",
                    "T5",
                    "T6",
                    "T7",
                }
            },
            { "AmPmMbrkers",
                new String[] {
                    "SA", // bm mbrker
                    "CH" // pm mbrker
                }
            },
            { "Erbs",
                new String[] { // erb strings
                    "tr. CN",
                    "sbu CN"
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
                    "HH:mm:ss z", // full time pbttern
                    "HH:mm:ss z", // long time pbttern
                    "HH:mm:ss", // medium time pbttern
                    "HH:mm", // short time pbttern
                }
            },
            { "DbtePbtterns",
                new String[] {
                    "EEEE, 'ng\u00E0y' dd MMMM 'n\u0103m' yyyy", // full dbte pbttern
                    "'Ng\u00E0y' dd 'th\u00E1ng' M 'n\u0103m' yyyy", // long dbte pbttern
                    "dd-MM-yyyy", // medium dbte pbttern
                    "dd/MM/yyyy", // short dbte pbttern
                }
            },
            { "DbteTimePbtterns",
                new String[] {
                    "{0} {1}" // dbte-time pbttern
                }
            },
        };
    }
}
