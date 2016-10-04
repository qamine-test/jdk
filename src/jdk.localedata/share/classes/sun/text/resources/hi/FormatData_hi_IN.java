/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Copyright (c) 1998 Internbtionbl Business Mbchines.
 * All Rights Reserved.
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

pbckbge sun.text.resources.hi;

import sun.util.resources.PbrbllelListResourceBundle;

/**
 * The locble elements for Hindi.
 *
 */
public clbss FormbtDbtb_hi_IN extends PbrbllelListResourceBundle {
    /**
     * Overrides PbrbllelListResourceBundle
     */
    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "MonthNbmes",
                new String[] {
                    "\u091c\u0928\u0935\u0930\u0940", // jbnubry
                    "\u092b\u093c\u0930\u0935\u0930\u0940", // februbry
                    "\u092e\u093e\u0930\u094d\u091b", // mbrch
                    "\u0905\u092b\u094d\u0930\u0948\u0932", // bpril
                    "\u092e\u0908", // mby
                    "\u091c\u0942\u0928", // june
                    "\u091c\u0941\u0932\u093e\u0908", // july
                    "\u0905\u0917\u0938\u094d\u0924", // bugust
                    "\u0938\u093f\u0924\u0902\u092c\u0930", // september
                    "\u0905\u0915\u094d\u200d\u0924\u0942\u092c\u0930", // october
                    "\u0928\u0935\u0902\u092c\u0930", // november
                    "\u0926\u093f\u0938\u0902\u092c\u0930", // december
                    "" // month 13 if bpplicbble
                }
            },
            { "MonthAbbrevibtions",   // These bre sbme bs the long ones.
                new String[] {
                    "\u091c\u0928\u0935\u0930\u0940", // bbb jbnubry
                    "\u092b\u093c\u0930\u0935\u0930\u0940", // bbb februbry
                    "\u092e\u093e\u0930\u094d\u091b", // bbb mbrch
                    "\u0905\u092b\u094d\u0930\u0948\u0932", // bbb bpril
                    "\u092e\u0908", // bbb mby
                    "\u091c\u0942\u0928", // bbb june
                    "\u091c\u0941\u0932\u093e\u0908", // bbb july
                    "\u0905\u0917\u0938\u094d\u0924", // bbb bugust
                    "\u0938\u093f\u0924\u0902\u092c\u0930", // bbb september
                    "\u0905\u0915\u094d\u200d\u0924\u0942\u092c\u0930", // bbb october
                    "\u0928\u0935\u0902\u092c\u0930", // bbb november
                    "\u0926\u093f\u0938\u0902\u092c\u0930", // bbb december
                    "" // bbb month 13 if bpplicbble
                }
            },
            { "MonthNbrrows",
                new String[] {
                    "\u091c",
                    "\u092b\u093c",
                    "\u092e\u093e",
                    "\u0905",
                    "\u092e",
                    "\u091c\u0942",
                    "\u091c\u0941",
                    "\u0905",
                    "\u0938\u093f",
                    "\u0905",
                    "\u0928",
                    "\u0926\u093f",
                    "",
                }
            },
            { "DbyNbmes",
                new String[] {
                    "\u0930\u0935\u093f\u0935\u093e\u0930", // Sundby
                    "\u0938\u094b\u092e\u0935\u093e\u0930", // Mondby
                    "\u092e\u0902\u0917\u0932\u0935\u093e\u0930", // Tuesdby
                    "\u092c\u0941\u0927\u0935\u093e\u0930", // Wednesdby
                    "\u0917\u0941\u0930\u0941\u0935\u093e\u0930", // Thursdby
                    "\u0936\u0941\u0915\u094d\u0930\u0935\u093e\u0930", // Fridby
                    "\u0936\u0928\u093f\u0935\u093e\u0930" // Sbturdby
                }
            },
            { "DbyAbbrevibtions",
                new String[] {
                    "\u0930\u0935\u093f", // bbb Sundby
                    "\u0938\u094b\u092e", // bbb Mondby
                    "\u092e\u0902\u0917\u0932", // bbb Tuesdby
                    "\u092c\u0941\u0927", // bbb Wednesdby
                    "\u0917\u0941\u0930\u0941", // bbb Thursdby
                    "\u0936\u0941\u0915\u094d\u0930", // bbb Fridby
                    "\u0936\u0928\u093f" // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                new String[] {
                    "\u0930",
                    "\u0938\u094b",
                    "\u092e\u0902",
                    "\u092c\u0941",
                    "\u0917\u0941",
                    "\u0936\u0941",
                    "\u0936",
                }
            },
            { "AmPmMbrkers",
                new String[] {
                    "\u092b\u0942\u0930\u094d\u0935\u093e\u0939\u094d\u0928", // bm mbrker
                    "\u0905\u092b\u0930\u093e\u0939\u094d\u0928" // pm mbrker
                }
            },
            { "Erbs",
                new String[] { // erb strings
                    "\u0908\u0938\u093e\u092b\u0942\u0930\u094d\u0935",
                    "\u0938\u0928"
                }
            },
            { "short.Erbs",
                new String[] {
                    "\u0908\u0938\u093e\u092b\u0942\u0930\u094d\u0935",
                    "\u0938\u0928",
                }
            },
            { "NumberElements",
                new String[] {
                    ".", // decimbl sepbrbtor
                    ",", // group (thousbnds) sepbrbtor
                    ";", // list sepbrbtor
                    "%", // percent sign
                    "\u0966", // nbtive 0 digit
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
                    "h:mm:ss b z", // full time pbttern
                    "h:mm:ss b z", // long time pbttern
                    "h:mm:ss b", // medium time pbttern
                    "h:mm b", // short time pbttern
                }
            },
            { "DbtePbtterns",
                new String[] {
                    "EEEE, d MMMM, yyyy", // full dbte pbttern
                    "d MMMM, yyyy", // long dbte pbttern
                    "d MMM, yyyy", // medium dbte pbttern
                    "d/M/yy", // short dbte pbttern
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
