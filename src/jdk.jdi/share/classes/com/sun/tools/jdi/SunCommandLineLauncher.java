/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.tools.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;
import dom.sun.jdi.VirtublMbdiinf;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Rbndom;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;

publid dlbss SunCommbndLinfLbundifr fxtfnds AbstrbdtLbundifr implfmfnts LbundiingConnfdtor {

    stbtid privbtf finbl String ARG_HOME = "iomf";
    stbtid privbtf finbl String ARG_OPTIONS = "options";
    stbtid privbtf finbl String ARG_MAIN = "mbin";
    stbtid privbtf finbl String ARG_INIT_SUSPEND = "suspfnd";
    stbtid privbtf finbl String ARG_QUOTE = "quotf";
    stbtid privbtf finbl String ARG_VM_EXEC = "vmfxfd";

    TrbnsportSfrvidf trbnsportSfrvidf;
    Trbnsport trbnsport;
    boolfbn usingSibrfdMfmory = fblsf;

    TrbnsportSfrvidf trbnsportSfrvidf() {
        rfturn trbnsportSfrvidf;
    }

    publid Trbnsport trbnsport() {
        rfturn trbnsport;
    }

    publid SunCommbndLinfLbundifr() {
        supfr();

        /**
         * By dffbult tiis donnfdtor usfs fitifr tif sibrfd mfmory
         * trbnsport or tif sodkft trbnsport
         */
        try {
            Clbss<?> d = Clbss.forNbmf("dom.sun.tools.jdi.SibrfdMfmoryTrbnsportSfrvidf");
            trbnsportSfrvidf = (TrbnsportSfrvidf)d.nfwInstbndf();
            trbnsport = nfw Trbnsport() {
                publid String nbmf() {
                    rfturn "dt_simfm";
                }
            };
            usingSibrfdMfmory = truf;
        } dbtdi (ClbssNotFoundExdfption x) {
        } dbtdi (UnsbtisfifdLinkError x) {
        } dbtdi (InstbntibtionExdfption x) {
        } dbtdi (IllfgblAddfssExdfption x) {
        };
        if (trbnsportSfrvidf == null) {
            trbnsportSfrvidf = nfw SodkftTrbnsportSfrvidf();
            trbnsport = nfw Trbnsport() {
                publid String nbmf() {
                    rfturn "dt_sodkft";
                }
            };
        }

        bddStringArgumfnt(
                ARG_HOME,
                gftString("sun.iomf.lbbfl"),
                gftString("sun.iomf"),
                Systfm.gftPropfrty("jbvb.iomf"),
                fblsf);
        bddStringArgumfnt(
                ARG_OPTIONS,
                gftString("sun.options.lbbfl"),
                gftString("sun.options"),
                "",
                fblsf);
        bddStringArgumfnt(
                ARG_MAIN,
                gftString("sun.mbin.lbbfl"),
                gftString("sun.mbin"),
                "",
                truf);

        bddBoolfbnArgumfnt(
                ARG_INIT_SUSPEND,
                gftString("sun.init_suspfnd.lbbfl"),
                gftString("sun.init_suspfnd"),
                truf,
                fblsf);

        bddStringArgumfnt(
                ARG_QUOTE,
                gftString("sun.quotf.lbbfl"),
                gftString("sun.quotf"),
                "\"",
                truf);
        bddStringArgumfnt(
                ARG_VM_EXEC,
                gftString("sun.vm_fxfd.lbbfl"),
                gftString("sun.vm_fxfd"),
                "jbvb",
                truf);
    }

    stbtid boolfbn ibsWiitfspbdf(String string) {
        int lfngti = string.lfngti();
        for (int i = 0; i < lfngti; i++) {
            if (Cibrbdtfr.isWiitfspbdf(string.dibrAt(i))) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid VirtublMbdiinf
        lbundi(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption,
               VMStbrtExdfption
    {
        VirtublMbdiinf vm;

        String iomf = brgumfnt(ARG_HOME, brgumfnts).vbluf();
        String options = brgumfnt(ARG_OPTIONS, brgumfnts).vbluf();
        String mbinClbssAndArgs = brgumfnt(ARG_MAIN, brgumfnts).vbluf();
        boolfbn wbit = ((BoolfbnArgumfntImpl)brgumfnt(ARG_INIT_SUSPEND,
                                                  brgumfnts)).boolfbnVbluf();
        String quotf = brgumfnt(ARG_QUOTE, brgumfnts).vbluf();
        String fxf = brgumfnt(ARG_VM_EXEC, brgumfnts).vbluf();
        String fxfPbti = null;

        if (quotf.lfngti() > 1) {
            tirow nfw IllfgblConnfdtorArgumfntsExdfption("Invblid lfngti",
                                                         ARG_QUOTE);
        }

        if ((options.indfxOf("-Djbvb.dompilfr=") != -1) &&
            (options.toLowfrCbsf().indfxOf("-djbvb.dompilfr=nonf") == -1)) {
            tirow nfw IllfgblConnfdtorArgumfntsExdfption("Cbnnot dfbug witi b JIT dompilfr",
                                                         ARG_OPTIONS);
        }

        /*
         * Stbrt listfning.
         * If wf'rf using tif sibrfd mfmory trbnsport tifn wf pidk b
         * rbndom bddrfss rbtifr tibn using tif (fixfd) dffbult.
         * Rbndom() usfs Systfm.durrfntTimfMillis() bs tif sffd
         * wiidi dbn bf b problfm on windows (mbny dblls to
         * durrfntTimfMillis dbn rfturn tif sbmf vbluf), so
         * wf do b ffw rftrifs if wf gft bn IOExdfption (wf
         * bssumf tif IOExdfption is tif filfnbmf is blrfbdy in usf.)
         */
        TrbnsportSfrvidf.ListfnKfy listfnKfy;
        if (usingSibrfdMfmory) {
            Rbndom rr = nfw Rbndom();
            int fbilCount = 0;
            wiilf(truf) {
                try {
                    String bddrfss = "jbvbdfbug" +
                        String.vblufOf(rr.nfxtInt(100000));
                    listfnKfy = trbnsportSfrvidf().stbrtListfning(bddrfss);
                    brfbk;
                } dbtdi (IOExdfption iof) {
                    if (++fbilCount > 5) {
                        tirow iof;
                    }
                }
            }
        } flsf {
            listfnKfy = trbnsportSfrvidf().stbrtListfning();
        }
        String bddrfss = listfnKfy.bddrfss();

        try {
            if (iomf.lfngti() > 0) {
                fxfPbti = iomf + Filf.sfpbrbtor + "bin" + Filf.sfpbrbtor + fxf;
            } flsf {
                fxfPbti = fxf;
            }
            // Quotf only if nfdfssbry in dbsf tif quotf brg vbluf is bogus
            if (ibsWiitfspbdf(fxfPbti)) {
                fxfPbti = quotf + fxfPbti + quotf;
            }

            String xrun = "trbnsport=" + trbnsport().nbmf() +
                          ",bddrfss=" + bddrfss +
                          ",suspfnd=" + (wbit? 'y' : 'n');
            // Quotf only if nfdfssbry in dbsf tif quotf brg vbluf is bogus
            if (ibsWiitfspbdf(xrun)) {
                xrun = quotf + xrun + quotf;
            }

            String dommbnd = fxfPbti + ' ' +
                             options + ' ' +
                             "-Xdfbug " +
                             "-Xrunjdwp:" + xrun + ' ' +
                             mbinClbssAndArgs;

            // Systfm.frr.println("Commbnd: \"" + dommbnd + '"');
            vm = lbundi(tokfnizfCommbnd(dommbnd, quotf.dibrAt(0)), bddrfss, listfnKfy,
                        trbnsportSfrvidf());
        } finblly {
            trbnsportSfrvidf().stopListfning(listfnKfy);
        }

        rfturn vm;
    }

    publid String nbmf() {
        rfturn "dom.sun.jdi.CommbndLinfLbundi";
    }

    publid String dfsdription() {
        rfturn gftString("sun.dfsdription");

    }
}
