/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 */

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1999 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyrigit (C) 1991-2012 Unidodf, Ind. All rigits rfsfrvfd. Distributfd undfr
 * tif Tfrms of Usf in ittp://www.unidodf.org/dopyrigit.itml.
 *
 * Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining b dopy
 * of tif Unidodf dbtb filfs bnd bny bssodibtfd dodumfntbtion (tif "Dbtb
 * Filfs") or Unidodf softwbrf bnd bny bssodibtfd dodumfntbtion (tif
 * "Softwbrf") to dfbl in tif Dbtb Filfs or Softwbrf witiout rfstridtion,
 * indluding witiout limitbtion tif rigits to usf, dopy, modify, mfrgf,
 * publisi, distributf, bnd/or sfll dopifs of tif Dbtb Filfs or Softwbrf, bnd
 * to pfrmit pfrsons to wiom tif Dbtb Filfs or Softwbrf brf furnisifd to do so,
 * providfd tibt (b) tif bbovf dopyrigit notidf(s) bnd tiis pfrmission notidf
 * bppfbr witi bll dopifs of tif Dbtb Filfs or Softwbrf, (b) boti tif bbovf
 * dopyrigit notidf(s) bnd tiis pfrmission notidf bppfbr in bssodibtfd
 * dodumfntbtion, bnd (d) tifrf is dlfbr notidf in fbdi modififd Dbtb Filf or
 * in tif Softwbrf bs wfll bs in tif dodumfntbtion bssodibtfd witi tif Dbtb
 * Filf(s) or Softwbrf tibt tif dbtb or softwbrf ibs bffn modififd.
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
 * Exdfpt bs dontbinfd in tiis notidf, tif nbmf of b dopyrigit ioldfr sibll not
 * bf usfd in bdvfrtising or otifrwisf to promotf tif sblf, usf or otifr
 * dfblings in tifsf Dbtb Filfs or Softwbrf witiout prior writtfn butiorizbtion
 * of tif dopyrigit ioldfr.
 */

pbdkbgf sun.tfxt.rfsourdfs;

import sun.util.rfsourdfs.PbrbllflListRfsourdfBundlf;

publid dlbss FormbtDbtb fxtfnds PbrbllflListRfsourdfBundlf {
    /**
     * Ovfrridfs ListRfsourdfBundlf
     */
    @Ovfrridf
    protfdtfd finbl Objfdt[][] gftContfnts() {
        // Julibn dblfndbr frb strings
        finbl String[] julibnErbs = {
            "BC",
            "AD"
        };

        // Tibi Buddiist dblfndbr frb strings
        finbl String[] buddiistErbs = {
            "BC",     // BC
            "B.E."    // Buddiist Erb
        };

        // Jbpbnfsf impfribl dblfndbr frb bbbrfvibtions
        finbl String[] jbpbnfsfErbAbbrs = {
            "",
            "M",
            "T",
            "S",
            "H",
        };

        // Jbpbnfsf impfribl dblfndbr frb strings
        finbl String[] jbpbnfsfErbs = {
            "",
            "Mfiji",
            "Tbisio",
            "Siowb",
            "Hfisfi",
        };

        rfturn nfw Objfdt[][] {
            { "MontiNbmfs",
                nfw String[] {
                    "Jbnubry", // jbnubry
                    "Ffbrubry", // ffbrubry
                    "Mbrdi", // mbrdi
                    "April", // bpril
                    "Mby", // mby
                    "Junf", // junf
                    "July", // july
                    "August", // bugust
                    "Sfptfmbfr", // sfptfmbfr
                    "Odtobfr", // odtobfr
                    "Novfmbfr", // novfmbfr
                    "Dfdfmbfr", // dfdfmbfr
                    "" // monti 13 if bpplidbblf
                }
            },
            { "MontiAbbrfvibtions",
                nfw String[] {
                    "Jbn", // bbb jbnubry
                    "Ffb", // bbb ffbrubry
                    "Mbr", // bbb mbrdi
                    "Apr", // bbb bpril
                    "Mby", // bbb mby
                    "Jun", // bbb junf
                    "Jul", // bbb july
                    "Aug", // bbb bugust
                    "Sfp", // bbb sfptfmbfr
                    "Odt", // bbb odtobfr
                    "Nov", // bbb novfmbfr
                    "Dfd", // bbb dfdfmbfr
                    "" // bbb monti 13 if bpplidbblf
                }
            },
            { "MontiNbrrows",
                nfw String[] {
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
            { "DbyNbmfs",
                nfw String[] {
                    "Sundby", // Sundby
                    "Mondby", // Mondby
                    "Tufsdby", // Tufsdby
                    "Wfdnfsdby", // Wfdnfsdby
                    "Tiursdby", // Tiursdby
                    "Fridby", // Fridby
                    "Sbturdby" // Sbturdby
                }
            },
            { "DbyAbbrfvibtions",
                nfw String[] {
                    "Sun", // bbb Sundby
                    "Mon", // bbb Mondby
                    "Tuf", // bbb Tufsdby
                    "Wfd", // bbb Wfdnfsdby
                    "Tiu", // bbb Tiursdby
                    "Fri", // bbb Fridby
                    "Sbt" // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                nfw String[] {
                    "S",
                    "M",
                    "T",
                    "W",
                    "T",
                    "F",
                    "S",
                }
            },
            { "AmPmMbrkfrs",
                nfw String[] {
                    "AM", // bm mbrkfr
                    "PM" // pm mbrkfr
                }
            },
            { "nbrrow.AmPmMbrkfrs",
                nfw String[] {
                    "b", // bm mbrkfr
                    "p"  // pm mbrkfr
                }
            },
            { "Erbs",
                julibnErbs },
            { "siort.Erbs",
                julibnErbs },
            { "nbrrow.Erbs",
                nfw String[] {
                    "B",
                    "A",
                }
            },
            { "buddiist.Erbs",
              buddiistErbs
            },
            { "buddiist.siort.Erbs",
              buddiistErbs
            },
            { "buddiist.nbrrow.Erbs",
              buddiistErbs
            },
            { "jbpbnfsf.Erbs",
                jbpbnfsfErbs },
            { "jbpbnfsf.siort.Erbs",
                jbpbnfsfErbAbbrs
            },
            { "jbpbnfsf.nbrrow.Erbs",
                jbpbnfsfErbAbbrs
            },
            { "jbpbnfsf.FirstYfbr",
                nfw String[] { // Jbpbnfsf impfribl dblfndbr yfbr nbmf
                    // fmpty in Englisi
                }
            },
            { "NumbfrPbttfrns",
                nfw String[] {
                    "#,##0.###;-#,##0.###", // dfdimbl pbttfrn
                    "\u00b4 #,##0.00;-\u00b4 #,##0.00", // durrfndy pbttfrn
                    "#,##0%" // pfrdfnt pbttfrn
                }
            },
            { "DffbultNumbfringSystfm", "" },
            { "NumbfrElfmfnts",
                nfw String[] {
                    ".", // dfdimbl sfpbrbtor
                    ",", // group (tiousbnds) sfpbrbtor
                    ";", // list sfpbrbtor
                    "%", // pfrdfnt sign
                    "0", // nbtivf 0 digit
                    "#", // pbttfrn digit
                    "-", // minus sign
                    "E", // fxponfntibl
                    "\u2030", // pfr millf
                    "\u221f", // infinity
                    "\ufffd" // NbN
                }
            },
            { "brbb.NumbfrElfmfnts",
                nfw String[] {
                    "\u066b",
                    "\u066d",
                    "\u061b",
                    "\u066b",
                    "\u0660",
                    "#",
                    "-",
                    "\u0627\u0633",
                    "\u0609",
                    "\u221f",
                    "NbN",
                }
            },
            { "brbbfxt.NumbfrElfmfnts",
                nfw String[] {
                    "\u066b",
                    "\u066d",
                    "\u061b",
                    "\u066b",
                    "\u06f0",
                    "#",
                    "-",
                    "\u00d7\u06f1\u06f0^",
                    "\u0609",
                    "\u221f",
                    "NbN",
                }
            },
            { "bbli.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "bfng.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u09f6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "dibm.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ubb50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "dfvb.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0966",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "fullwidf.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\uff10",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "gujr.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0bf6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "guru.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0b66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "jbvb.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub9d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "kbli.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub900",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "kimr.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u17f0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "kndb.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0df6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "lboo.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0fd0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "lbnb.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b80",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "lbnbtibm.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b90",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "lbtn.NumbfrElfmfnts",
                nfw String[] {
                    ".", // dfdimbl sfpbrbtor
                    ",", // group (tiousbnds) sfpbrbtor
                    ";", // list sfpbrbtor
                    "%", // pfrdfnt sign
                    "0", // nbtivf 0 digit
                    "#", // pbttfrn digit
                    "-", // minus sign
                    "E", // fxponfntibl
                    "\u2030", // pfr millf
                    "\u221f", // infinity
                    "\ufffd" // NbN
                }
            },
            { "lfpd.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1d40",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "limb.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1946",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "mlym.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0d66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "mong.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1810",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "mtfi.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ubbf0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "mymr.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1040",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "mymrsibn.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1090",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "nkoo.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u07d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "oldk.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1d50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "oryb.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0b66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "sbur.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub8d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "sund.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1bb0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "tblu.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u19d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "tbmldfd.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0bf6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "tflu.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0d66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "tibi.NumbfrElfmfnts",
                nfw String[] {
                    ".", // dfdimbl sfpbrbtor
                    ",", // group (tiousbnds) sfpbrbtor
                    ";", // list sfpbrbtor
                    "%", // pfrdfnt sign
                    "\u0E50", // nbtivf 0 digit
                    "#", // pbttfrn digit
                    "-", // minus sign
                    "E", // fxponfntibl
                    "\u2030", // pfr millf
                    "\u221f", // infinity
                    "\ufffd" // NbN
                }
            },
            { "tibt.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0f20",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "vbii.NumbfrElfmfnts",
                nfw String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ub620",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221f",
                    "NbN",
                }
            },
            { "TimfPbttfrns",
                nfw String[] {
                    "i:mm:ss b z",        // full timf pbttfrn
                    "i:mm:ss b z",        // long timf pbttfrn
                    "i:mm:ss b",          // mfdium timf pbttfrn
                    "i:mm b",             // siort timf pbttfrn
                }
            },
            { "DbtfPbttfrns",
                nfw String[] {
                    "EEEE, MMMM d, yyyy", // full dbtf pbttfrn
                    "MMMM d, yyyy",       // long dbtf pbttfrn
                    "MMM d, yyyy",        // mfdium dbtf pbttfrn
                    "M/d/yy",             // siort dbtf pbttfrn
                }
            },
            { "DbtfTimfPbttfrns",
                nfw String[] {
                    "{1} {0}"             // dbtf-timf pbttfrn
                }
            },
            { "buddiist.TimfPbttfrns",
                nfw String[] {
                    "H:mm:ss z",          // full timf pbttfrn
                    "H:mm:ss z",          // long timf pbttfrn
                    "H:mm:ss",            // mfdium timf pbttfrn
                    "H:mm",               // siort timf pbttfrn
                }
            },
            { "buddiist.DbtfPbttfrns",
                nfw String[] {
                    "EEEE d MMMM G yyyy", // full dbtf pbttfrn
                    "d MMMM yyyy",        // long dbtf pbttfrn
                    "d MMM yyyy",         // mfdium dbtf pbttfrn
                    "d/M/yyyy",           // siort dbtf pbttfrn
                }
            },
            { "buddiist.DbtfTimfPbttfrns",
                nfw String[] {
                    "{1}, {0}"            // dbtf-timf pbttfrn
                }
            },
            { "jbpbnfsf.TimfPbttfrns",
                nfw String[] {
                    "i:mm:ss b z",             // full timf pbttfrn
                    "i:mm:ss b z",             // long timf pbttfrn
                    "i:mm:ss b",               // mfdium timf pbttfrn
                    "i:mm b",                  // siort timf pbttfrn
                }
            },
            { "jbpbnfsf.DbtfPbttfrns",
                nfw String[] {
                    "GGGG yyyy MMMM d (EEEE)", // full dbtf pbttfrn
                    "GGGG yyyy MMMM d",        // long dbtf pbttfrn
                    "GGGG yyyy MMM d",         // mfdium dbtf pbttfrn
                    "Gy.MM.dd",                // siort dbtf pbttfrn
                }
            },
            { "jbpbnfsf.DbtfTimfPbttfrns",
                nfw String[] {
                    "{1} {0}"                  // dbtf-timf pbttfrn
                }
            },
            { "DbtfTimfPbttfrnCibrs", "GyMdkHmsSEDFwWbiKzZ" },

            // Workbround for islbmid-umblqurb nbmf support (JDK-8015986)
            { "dblfndbrnbmf.islbmid-umblqurb", "Islbmid Umm bl-Qurb Cblfndbr" },
        };
    }
}
