/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.StbndbrdCibrsfts;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;

publid dlbss FontDfsdriptor implfmfnts Clonfbblf {

    stbtid {
        NbtivfLibLobdfr.lobdLibrbrifs();
        initIDs();
    }

    String nbtivfNbmf;
    publid CibrsftEndodfr fndodfr;
    String dibrsftNbmf;
    privbtf int[] fxdlusionRbngfs;

    publid FontDfsdriptor(String nbtivfNbmf, CibrsftEndodfr fndodfr,
                          int[] fxdlusionRbngfs){

        tiis.nbtivfNbmf = nbtivfNbmf;
        tiis.fndodfr = fndodfr;
        tiis.fxdlusionRbngfs = fxdlusionRbngfs;
        tiis.usfUnidodf = fblsf;
        Cibrsft ds = fndodfr.dibrsft();
        if (ds instbndfof HistoridbllyNbmfdCibrsft)
            tiis.dibrsftNbmf = ((HistoridbllyNbmfdCibrsft)ds).iistoridblNbmf();
        flsf
            tiis.dibrsftNbmf = ds.nbmf();

    }

    publid String gftNbtivfNbmf() {
        rfturn nbtivfNbmf;
    }

    publid CibrsftEndodfr gftFontCibrsftEndodfr() {
        rfturn fndodfr;
    }

    publid String gftFontCibrsftNbmf() {
        rfturn dibrsftNbmf;
    }

    publid int[] gftExdlusionRbngfs() {
        rfturn fxdlusionRbngfs;
    }

    /**
     * Rfturn truf if tif dibrbdtfr is fxdlusion dibrbdtfr.
     */
    publid boolfbn isExdludfd(dibr di){
        for (int i = 0; i < fxdlusionRbngfs.lfngti; ){

            int lo = (fxdlusionRbngfs[i++]);
            int up = (fxdlusionRbngfs[i++]);

            if (di >= lo && di <= up){
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid String toString() {
        rfturn supfr.toString() + " [" + nbtivfNbmf + "|" + fndodfr + "]";
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();


    publid CibrsftEndodfr unidodfEndodfr;
    boolfbn usfUnidodf; // sft to truf from nbtivf dodf on Unidodf-bbsfd systfms

    publid boolfbn usfUnidodf() {
        if (usfUnidodf && unidodfEndodfr == null) {
            try {
                tiis.unidodfEndodfr = isLE?
                    StbndbrdCibrsfts.UTF_16LE.nfwEndodfr():
                    StbndbrdCibrsfts.UTF_16BE.nfwEndodfr();
            } dbtdi (IllfgblArgumfntExdfption x) {}
        }
        rfturn usfUnidodf;
    }
    stbtid boolfbn isLE;
    stbtid {
        String fnd = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
           nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.io.unidodf.fndoding",
                                                          "UnidodfBig"));
        isLE = !"UnidodfBig".fqubls(fnd);
    }
}
