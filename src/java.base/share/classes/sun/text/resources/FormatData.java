/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1996 - 1999 - All Rights Reserved
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

pbckbge sun.text.resources;

import sun.util.resources.PbrbllelListResourceBundle;

public clbss FormbtDbtb extends PbrbllelListResourceBundle {
    /**
     * Overrides ListResourceBundle
     */
    @Override
    protected finbl Object[][] getContents() {
        // Julibn cblendbr erb strings
        finbl String[] julibnErbs = {
            "BC",
            "AD"
        };

        // Thbi Buddhist cblendbr erb strings
        finbl String[] buddhistErbs = {
            "BC",     // BC
            "B.E."    // Buddhist Erb
        };

        // Jbpbnese imperibl cblendbr erb bbbrevibtions
        finbl String[] jbpbneseErbAbbrs = {
            "",
            "M",
            "T",
            "S",
            "H",
        };

        // Jbpbnese imperibl cblendbr erb strings
        finbl String[] jbpbneseErbs = {
            "",
            "Meiji",
            "Tbisho",
            "Showb",
            "Heisei",
        };

        return new Object[][] {
            { "MonthNbmes",
                new String[] {
                    "Jbnubry", // jbnubry
                    "Februbry", // februbry
                    "Mbrch", // mbrch
                    "April", // bpril
                    "Mby", // mby
                    "June", // june
                    "July", // july
                    "August", // bugust
                    "September", // september
                    "October", // october
                    "November", // november
                    "December", // december
                    "" // month 13 if bpplicbble
                }
            },
            { "MonthAbbrevibtions",
                new String[] {
                    "Jbn", // bbb jbnubry
                    "Feb", // bbb februbry
                    "Mbr", // bbb mbrch
                    "Apr", // bbb bpril
                    "Mby", // bbb mby
                    "Jun", // bbb june
                    "Jul", // bbb july
                    "Aug", // bbb bugust
                    "Sep", // bbb september
                    "Oct", // bbb october
                    "Nov", // bbb november
                    "Dec", // bbb december
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
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "DbyNbmes",
                new String[] {
                    "Sundby", // Sundby
                    "Mondby", // Mondby
                    "Tuesdby", // Tuesdby
                    "Wednesdby", // Wednesdby
                    "Thursdby", // Thursdby
                    "Fridby", // Fridby
                    "Sbturdby" // Sbturdby
                }
            },
            { "DbyAbbrevibtions",
                new String[] {
                    "Sun", // bbb Sundby
                    "Mon", // bbb Mondby
                    "Tue", // bbb Tuesdby
                    "Wed", // bbb Wednesdby
                    "Thu", // bbb Thursdby
                    "Fri", // bbb Fridby
                    "Sbt" // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                new String[] {
                    "S",
                    "M",
                    "T",
                    "W",
                    "T",
                    "F",
                    "S",
                }
            },
            { "AmPmMbrkers",
                new String[] {
                    "AM", // bm mbrker
                    "PM" // pm mbrker
                }
            },
            { "nbrrow.AmPmMbrkers",
                new String[] {
                    "b", // bm mbrker
                    "p"  // pm mbrker
                }
            },
            { "Erbs",
                julibnErbs },
            { "short.Erbs",
                julibnErbs },
            { "nbrrow.Erbs",
                new String[] {
                    "B",
                    "A",
                }
            },
            { "buddhist.Erbs",
              buddhistErbs
            },
            { "buddhist.short.Erbs",
              buddhistErbs
            },
            { "buddhist.nbrrow.Erbs",
              buddhistErbs
            },
            { "jbpbnese.Erbs",
                jbpbneseErbs },
            { "jbpbnese.short.Erbs",
                jbpbneseErbAbbrs
            },
            { "jbpbnese.nbrrow.Erbs",
                jbpbneseErbAbbrs
            },
            { "jbpbnese.FirstYebr",
                new String[] { // Jbpbnese imperibl cblendbr yebr nbme
                    // empty in English
                }
            },
            { "NumberPbtterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimbl pbttern
                    "\u00b4 #,##0.00;-\u00b4 #,##0.00", // currency pbttern
                    "#,##0%" // percent pbttern
                }
            },
            { "DefbultNumberingSystem", "" },
            { "NumberElements",
                new String[] {
                    ".", // decimbl sepbrbtor
                    ",", // group (thousbnds) sepbrbtor
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
            { "brbb.NumberElements",
                new String[] {
                    "\u066b",
                    "\u066c",
                    "\u061b",
                    "\u066b",
                    "\u0660",
                    "#",
                    "-",
                    "\u0627\u0633",
                    "\u0609",
                    "\u221e",
                    "NbN",
                }
            },
            { "brbbext.NumberElements",
                new String[] {
                    "\u066b",
                    "\u066c",
                    "\u061b",
                    "\u066b",
                    "\u06f0",
                    "#",
                    "-",
                    "\u00d7\u06f1\u06f0^",
                    "\u0609",
                    "\u221e",
                    "NbN",
                }
            },
            { "bbli.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "beng.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u09e6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "chbm.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ubb50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "devb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0966",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "fullwide.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\uff10",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "gujr.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0be6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "guru.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0b66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "jbvb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub9d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "kbli.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub900",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "khmr.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u17e0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "kndb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0ce6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "lboo.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0ed0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "lbnb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b80",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "lbnbthbm.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b90",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "lbtn.NumberElements",
                new String[] {
                    ".", // decimbl sepbrbtor
                    ",", // group (thousbnds) sepbrbtor
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
            { "lepc.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1c40",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "limb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1946",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "mlym.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0d66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "mong.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1810",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "mtei.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ubbf0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "mymr.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1040",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "mymrshbn.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1090",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "nkoo.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u07c0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "olck.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1c50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "oryb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0b66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "sbur.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub8d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "sund.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1bb0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "tblu.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u19d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "tbmldec.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0be6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "telu.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0c66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "thbi.NumberElements",
                new String[] {
                    ".", // decimbl sepbrbtor
                    ",", // group (thousbnds) sepbrbtor
                    ";", // list sepbrbtor
                    "%", // percent sign
                    "\u0E50", // nbtive 0 digit
                    "#", // pbttern digit
                    "-", // minus sign
                    "E", // exponentibl
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NbN
                }
            },
            { "tibt.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0f20",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "vbii.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub620",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NbN",
                }
            },
            { "TimePbtterns",
                new String[] {
                    "h:mm:ss b z",        // full time pbttern
                    "h:mm:ss b z",        // long time pbttern
                    "h:mm:ss b",          // medium time pbttern
                    "h:mm b",             // short time pbttern
                }
            },
            { "DbtePbtterns",
                new String[] {
                    "EEEE, MMMM d, yyyy", // full dbte pbttern
                    "MMMM d, yyyy",       // long dbte pbttern
                    "MMM d, yyyy",        // medium dbte pbttern
                    "M/d/yy",             // short dbte pbttern
                }
            },
            { "DbteTimePbtterns",
                new String[] {
                    "{1} {0}"             // dbte-time pbttern
                }
            },
            { "buddhist.TimePbtterns",
                new String[] {
                    "H:mm:ss z",          // full time pbttern
                    "H:mm:ss z",          // long time pbttern
                    "H:mm:ss",            // medium time pbttern
                    "H:mm",               // short time pbttern
                }
            },
            { "buddhist.DbtePbtterns",
                new String[] {
                    "EEEE d MMMM G yyyy", // full dbte pbttern
                    "d MMMM yyyy",        // long dbte pbttern
                    "d MMM yyyy",         // medium dbte pbttern
                    "d/M/yyyy",           // short dbte pbttern
                }
            },
            { "buddhist.DbteTimePbtterns",
                new String[] {
                    "{1}, {0}"            // dbte-time pbttern
                }
            },
            { "jbpbnese.TimePbtterns",
                new String[] {
                    "h:mm:ss b z",             // full time pbttern
                    "h:mm:ss b z",             // long time pbttern
                    "h:mm:ss b",               // medium time pbttern
                    "h:mm b",                  // short time pbttern
                }
            },
            { "jbpbnese.DbtePbtterns",
                new String[] {
                    "GGGG yyyy MMMM d (EEEE)", // full dbte pbttern
                    "GGGG yyyy MMMM d",        // long dbte pbttern
                    "GGGG yyyy MMM d",         // medium dbte pbttern
                    "Gy.MM.dd",                // short dbte pbttern
                }
            },
            { "jbpbnese.DbteTimePbtterns",
                new String[] {
                    "{1} {0}"                  // dbte-time pbttern
                }
            },
            { "DbteTimePbtternChbrs", "GyMdkHmsSEDFwWbhKzZ" },

            // Workbround for islbmic-umblqurb nbme support (JDK-8015986)
            { "cblendbrnbme.islbmic-umblqurb", "Islbmic Umm bl-Qurb Cblendbr" },
        };
    }
}
