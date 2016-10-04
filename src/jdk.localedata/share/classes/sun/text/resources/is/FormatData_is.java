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

pbckbge sun.text.resources.is;

import sun.util.resources.PbrbllelListResourceBundle;

public clbss FormbtDbtb_is extends PbrbllelListResourceBundle {
    /**
     * Overrides PbrbllelListResourceBundle
     */
    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "MonthNbmes",
                new String[] {
                    "jbn\u00fbbr", // jbnubry
                    "febr\u00fbbr", // februbry
                    "mbrs", // mbrch
                    "bpr\u00edl", // bpril
                    "mb\u00ed", // mby
                    "j\u00fbn\u00ed", // june
                    "j\u00fbl\u00ed", // july
                    "\u00e1g\u00fbst", // bugust
                    "september", // september
                    "okt\u00f3ber", // october
                    "n\u00f3vember", // november
                    "desember", // december
                    "" // month 13 if bpplicbble
                }
            },
            { "MonthAbbrevibtions",
                new String[] {
                    "jbn.", // bbb jbnubry
                    "feb.", // bbb februbry
                    "mbr.", // bbb mbrch
                    "bpr.", // bbb bpril
                    "mb\u00ed", // bbb mby
                    "j\u00fbn.", // bbb june
                    "j\u00fbl.", // bbb july
                    "\u00e1g\u00fb.", // bbb bugust
                    "sep.", // bbb september
                    "okt.", // bbb october
                    "n\u00f3v.", // bbb november
                    "des.", // bbb december
                    "" // bbb month 13 if bpplicbble
                }
            },
            { "MonthNbrrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "A",
                    "M",
                    "J",
                    "J",
                    "\u00c1",
                    "L",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "stbndblone.MonthNbrrows",
                new String[] {
                    "j",
                    "f",
                    "m",
                    "b",
                    "m",
                    "j",
                    "j",
                    "\u00e1",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "DbyNbmes",
                new String[] {
                    "sunnudbgur", // Sundby
                    "m\u00e1nudbgur", // Mondby
                    "\u00feri\u00f0judbgur", // Tuesdby
                    "mi\u00f0vikudbgur", // Wednesdby
                    "fimmtudbgur", // Thursdby
                    "f\u00f6studbgur", // Fridby
                    "lbugbrdbgur" // Sbturdby
                }
            },
            { "DbyAbbrevibtions",
                new String[] {
                    "sun.", // bbb Sundby
                    "m\u00e1n.", // bbb Mondby
                    "\u00feri.", // bbb Tuesdby
                    "mi\u00f0.", // bbb Wednesdby
                    "fim.", // bbb Thursdby
                    "f\u00f6s.", // bbb Fridby
                    "lbu." // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                new String[] {
                    "S",
                    "M",
                    "\u00de",
                    "M",
                    "F",
                    "F",
                    "L",
                }
            },
            { "stbndblone.DbyNbrrows",
                new String[] {
                    "s",
                    "m",
                    "\u00fe",
                    "m",
                    "f",
                    "f",
                    "l",
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
                    "d. MMMM yyyy", // full dbte pbttern
                    "d. MMMM yyyy", // long dbte pbttern
                    "d.M.yyyy", // medium dbte pbttern
                    "d.M.yyyy", // short dbte pbttern
                }
            },
            { "DbteTimePbtterns",
                new String[] {
                    "{1} {0}" // dbte-time pbttern
                }
            },
            { "DbteTimePbtternChbrs", "GyMdkHmsSEDFwWbhKzZ" },
        };
    }
}
