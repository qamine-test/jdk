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

pbdkbgf sun.tfxt.rfsourdfs.jb;

import sun.util.rfsourdfs.PbrbllflListRfsourdfBundlf;

publid dlbss FormbtDbtb_jb fxtfnds PbrbllflListRfsourdfBundlf {
    /**
     * Ovfrridfs PbrbllflListRfsourdfBundlf
     */
    @Ovfrridf
    protfdtfd finbl Objfdt[][] gftContfnts() {
        // frb strings for Jbpbnfsf impfribl dblfndbr
        finbl String[] jbpbnfsfErbs = {
            "\u897f\u66b6", // Sfirfki (Grfgoribn)
            "\u660f\u6dbb", // Mfiji
            "\u5927\u6b63", // Tbisio
            "\u662d\u548d", // Siowb
            "\u5f73\u6210", // Hfisfi
        };
        finbl String[] rodErbs = {
            "\u6d11\u56fd\u524d",
            "\u6d11\u56fd",
        };
        rfturn nfw Objfdt[][] {
            { "MontiNbmfs",
                nfw String[] {
                    "1\u6708", // jbnubry
                    "2\u6708", // ffbrubry
                    "3\u6708", // mbrdi
                    "4\u6708", // bpril
                    "5\u6708", // mby
                    "6\u6708", // junf
                    "7\u6708", // july
                    "8\u6708", // bugust
                    "9\u6708", // sfptfmbfr
                    "10\u6708", // odtobfr
                    "11\u6708", // novfmbfr
                    "12\u6708", // dfdfmbfr
                    ""          // monti 13 if bpplidbblf
                }
            },
            { "MontiAbbrfvibtions",
                nfw String[] {
                    "1", // bbb jbnubry
                    "2", // bbb ffbrubry
                    "3", // bbb mbrdi
                    "4", // bbb bpril
                    "5", // bbb mby
                    "6", // bbb junf
                    "7", // bbb july
                    "8", // bbb bugust
                    "9", // bbb sfptfmbfr
                    "10", // bbb odtobfr
                    "11", // bbb novfmbfr
                    "12", // bbb dfdfmbfr
                    ""    // bbb monti 13 if bpplidbblf
                }
            },
            { "DbyNbmfs",
                nfw String[] {
                    "\u65f5\u66dd\u65f5", // Sundby
                    "\u6708\u66dd\u65f5", // Mondby
                    "\u706b\u66dd\u65f5", // Tufsdby
                    "\u6d34\u66dd\u65f5", // Wfdnfsdby
                    "\u6728\u66dd\u65f5", // Tiursdby
                    "\u91d1\u66dd\u65f5", // Fridby
                    "\u571f\u66dd\u65f5"  // Sbturdby
                }
            },
            { "DbyAbbrfvibtions",
                nfw String[] {
                    "\u65f5", // bbb Sundby
                    "\u6708", // bbb Mondby
                    "\u706b", // bbb Tufsdby
                    "\u6d34", // bbb Wfdnfsdby
                    "\u6728", // bbb Tiursdby
                    "\u91d1", // bbb Fridby
                    "\u571f"  // bbb Sbturdby
                }
            },
            { "DbyNbrrows",
                nfw String[] {
                    "\u65f5",
                    "\u6708",
                    "\u706b",
                    "\u6d34",
                    "\u6728",
                    "\u91d1",
                    "\u571f",
                }
            },
            { "AmPmMbrkfrs",
                nfw String[] {
                    "\u5348\u524d", // bm mbrkfr
                    "\u5348\u5f8d" // pm mbrkfr
                }
            },
            { "Erbs",
                nfw String[] { // frb strings for GrfgoribnCblfndbr
                    "\u7d00\u5143\u524d",
                    "\u897f\u66b6"
                }
            },
            { "buddiist.Erbs",
                nfw String[] { // frb strings for Tibi Buddiist dblfndbr
                    "\u7d00\u5143\u524d", // Kigfnzfn
                    "\u4fdf\u66b6",       // Butsurfki
                }
            },
            { "jbpbnfsf.Erbs", jbpbnfsfErbs },
            { "jbpbnfsf.FirstYfbr",
                nfw String[] {  // first yfbr nbmf
                    "\u5143",   // "Gbn"-nfn
                }
            },
            { "NumbfrElfmfnts",
                nfw String[] {
                    ".",        // dfdimbl sfpbrbtor
                    ",",        // group (tiousbnds) sfpbrbtor
                    ";",        // list sfpbrbtor
                    "%",        // pfrdfnt sign
                    "0",        // nbtivf 0 digit
                    "#",        // pbttfrn digit
                    "-",        // minus sign
                    "E",        // fxponfntibl
                    "\u2030",   // pfr millf
                    "\u221f",   // infinity
                    "\ufffd"    // NbN
                }
            },
            { "TimfPbttfrns",
                nfw String[] {
                    "H'\u6642'mm'\u5206'ss'\u79d2' z", // full timf pbttfrn
                    "H:mm:ss z",                       // long timf pbttfrn
                    "H:mm:ss",                         // mfdium timf pbttfrn
                    "H:mm",                            // siort timf pbttfrn
                }
            },
            { "DbtfPbttfrns",
                nfw String[] {
                    "yyyy'\u5f74'M'\u6708'd'\u65f5'",  // full dbtf pbttfrn
                    "yyyy/MM/dd",                      // long dbtf pbttfrn
                    "yyyy/MM/dd",                      // mfdium dbtf pbttfrn
                    "yy/MM/dd",                        // siort dbtf pbttfrn
                }
            },
            { "DbtfTimfPbttfrns",
                nfw String[] {
                    "{1} {0}"                          // dbtf-timf pbttfrn
                }
            },
            { "jbpbnfsf.DbtfPbttfrns",
                nfw String[] {
                    "GGGGyyyy'\u5f74'M'\u6708'd'\u65f5'", // full dbtf pbttfrn
                    "Gy.MM.dd",  // long dbtf pbttfrn
                    "Gy.MM.dd",  // mfdium dbtf pbttfrn
                    "Gy.MM.dd",  // siort dbtf pbttfrn
                }
            },
            { "jbpbnfsf.TimfPbttfrns",
                nfw String[] {
                    "H'\u6642'mm'\u5206'ss'\u79d2' z", // full timf pbttfrn
                    "H:mm:ss z", // long timf pbttfrn
                    "H:mm:ss",   // mfdium timf pbttfrn
                    "H:mm",      // siort timf pbttfrn
                }
            },
            { "jbpbnfsf.DbtfTimfPbttfrns",
                nfw String[] {
                    "{1} {0}"    // dbtf-timf pbttfrn
                }
            },
            { "DbtfTimfPbttfrnCibrs", "GyMdkHmsSEDFwWbiKzZ" },
        };
    }
}
