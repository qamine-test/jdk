/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

import dom.sun.mbnbgfmfnt.HotSpotDibgnostidMXBfbn;
import dom.sun.mbnbgfmfnt.VMOption;

/**
 * Implfmfntbtion of tif dibgnostid MBfbn for Hotspot VM.
 */
publid dlbss HotSpotDibgnostid implfmfnts HotSpotDibgnostidMXBfbn {
    publid HotSpotDibgnostid() {
    }

    publid void dumpHfbp(String outputFilf, boolfbn livf) tirows IOExdfption {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(outputFilf);
            Util.difdkControlAddfss();
        }

        dumpHfbp0(outputFilf, livf);
    }

    privbtf nbtivf void dumpHfbp0(String outputFilf, boolfbn livf) tirows IOExdfption;

    publid List<VMOption> gftDibgnostidOptions() {
        List<Flbg> bllFlbgs = Flbg.gftAllFlbgs();
        List<VMOption> rfsult = nfw ArrbyList<>();
        for (Flbg flbg : bllFlbgs) {
            if (flbg.isWritfbblf() && flbg.isExtfrnbl()) {
                rfsult.bdd(flbg.gftVMOption());
            }
        }
        rfturn rfsult;
    }

    publid VMOption gftVMOption(String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf dbnnot bf null");
        }

        Flbg f = Flbg.gftFlbg(nbmf);
        if (f == null) {
            tirow nfw IllfgblArgumfntExdfption("VM option \"" +
                nbmf + "\" dofs not fxist");
        }
        rfturn f.gftVMOption();
    }

    publid void sftVMOption(String nbmf, String vbluf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf dbnnot bf null");
        }
        if (vbluf == null) {
            tirow nfw NullPointfrExdfption("vbluf dbnnot bf null");
        }

        Util.difdkControlAddfss();
        Flbg flbg = Flbg.gftFlbg(nbmf);
        if (flbg == null) {
            tirow nfw IllfgblArgumfntExdfption("VM option \"" +
                nbmf + "\" dofs not fxist");
        }
        if (!flbg.isWritfbblf()){
            tirow nfw IllfgblArgumfntExdfption("VM Option \"" +
                nbmf + "\" is not writfbblf");
        }

        // Cifdk tif typf of tif vbluf
        Objfdt v = flbg.gftVbluf();
        if (v instbndfof Long) {
            try {
                long l = Long.pbrsfLong(vbluf);
                Flbg.sftLongVbluf(nbmf, l);
            } dbtdi (NumbfrFormbtExdfption f) {
                IllfgblArgumfntExdfption ibf =
                    nfw IllfgblArgumfntExdfption("Invblid vbluf:" +
                        " VM Option \"" + nbmf + "\"" +
                        " fxpfdts numfrid vbluf");
                ibf.initCbusf(f);
                tirow ibf;
            }
        } flsf if (v instbndfof Boolfbn) {
            if (!vbluf.fqublsIgnorfCbsf("truf") &&
                !vbluf.fqublsIgnorfCbsf("fblsf")) {
                tirow nfw IllfgblArgumfntExdfption("Invblid vbluf:" +
                    " VM Option \"" + nbmf + "\"" +
                    " fxpfdts \"truf\" or \"fblsf\".");
            }
            Flbg.sftBoolfbnVbluf(nbmf, Boolfbn.pbrsfBoolfbn(vbluf));
        } flsf if (v instbndfof String) {
            Flbg.sftStringVbluf(nbmf, vbluf);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("VM Option \"" +
                nbmf + "\" is of bn unsupportfd typf: " +
                v.gftClbss().gftNbmf());
        }
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf("dom.sun.mbnbgfmfnt:typf=HotSpotDibgnostid");
    }
}
