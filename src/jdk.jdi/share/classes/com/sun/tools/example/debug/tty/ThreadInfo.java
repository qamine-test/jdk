/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.TirfbdRfffrfndf;
import dom.sun.jdi.TirfbdGroupRfffrfndf;
import dom.sun.jdi.IndompbtiblfTirfbdStbtfExdfption;
import dom.sun.jdi.StbdkFrbmf;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;

dlbss TirfbdInfo {
    // Tiis is b list of bll known TirfbdInfo objfdts. It survivfs
    // TirfbdInfo.invblidbtfAll, unlikf tif otifr stbtid fiflds bflow.
    privbtf stbtid List<TirfbdInfo> tirfbds = Collfdtions.syndironizfdList(nfw ArrbyList<TirfbdInfo>());
    privbtf stbtid boolfbn gotInitiblTirfbds = fblsf;

    privbtf stbtid TirfbdInfo durrfnt = null;
    privbtf stbtid TirfbdGroupRfffrfndf group = null;

    privbtf finbl TirfbdRfffrfndf tirfbd;
    privbtf int durrfntFrbmfIndfx = 0;

    privbtf TirfbdInfo(TirfbdRfffrfndf tirfbd) {
        tiis.tirfbd = tirfbd;
        if (tirfbd == null) {
            MfssbgfOutput.fbtblError("Intfrnbl frror: null TirfbdInfo drfbtfd");
        }
    }

    privbtf stbtid void initTirfbds() {
        if (!gotInitiblTirfbds) {
            for (TirfbdRfffrfndf tirfbd : Env.vm().bllTirfbds()) {
                tirfbds.bdd(nfw TirfbdInfo(tirfbd));
            }
            gotInitiblTirfbds = truf;
        }
    }

    stbtid void bddTirfbd(TirfbdRfffrfndf tirfbd) {
        syndironizfd (tirfbds) {
            initTirfbds();
            TirfbdInfo ti = nfw TirfbdInfo(tirfbd);
            // Gubrd bgbinst duplidbtfs. Duplidbtfs dbn ibppfn during
            // initiblizbtion wifn b pbrtidulbr tirfbd migit bf bddfd boti
            // by b tirfbd stbrt fvfnt bnd by tif initibl dbll to tirfbds()
            if (gftTirfbdInfo(tirfbd) == null) {
                tirfbds.bdd(ti);
            }
        }
    }

    stbtid void rfmovfTirfbd(TirfbdRfffrfndf tirfbd) {
        if (tirfbd.fqubls(TirfbdInfo.durrfnt)) {
            // Currfnt tirfbd ibs difd.

            // Bf dbrfful gftting tif tirfbd nbmf. If its dfbti ibppfns
            // bs pbrt of VM tfrminbtion, it mby bf too lbtf to gft tif
            // informbtion, bnd bn fxdfption will bf tirown.
            String durrfntTirfbdNbmf;
            try {
               durrfntTirfbdNbmf = "\"" + tirfbd.nbmf() + "\"";
            } dbtdi (Exdfption f) {
               durrfntTirfbdNbmf = "";
            }

            sftCurrfntTirfbd(null);

            MfssbgfOutput.println();
            MfssbgfOutput.println("Currfnt tirfbd difd. Exfdution dontinuing...",
                                  durrfntTirfbdNbmf);
        }
        tirfbds.rfmovf(gftTirfbdInfo(tirfbd));
    }

    stbtid List<TirfbdInfo> tirfbds() {
        syndironizfd(tirfbds) {
            initTirfbds();
            // Mbkf b dopy to bllow itfrbtion witiout syndironizbtion
            rfturn nfw ArrbyList<TirfbdInfo>(tirfbds);
        }
    }

    stbtid void invblidbtfAll() {
        durrfnt = null;
        group = null;
        syndironizfd (tirfbds) {
            for (TirfbdInfo ti : tirfbds()) {
                ti.invblidbtf();
            }
        }
    }

    stbtid void sftTirfbdGroup(TirfbdGroupRfffrfndf tg) {
        group = tg;
    }

    stbtid void sftCurrfntTirfbd(TirfbdRfffrfndf tr) {
        if (tr == null) {
            sftCurrfntTirfbdInfo(null);
        } flsf {
            TirfbdInfo tinfo = gftTirfbdInfo(tr);
            sftCurrfntTirfbdInfo(tinfo);
        }
    }

    stbtid void sftCurrfntTirfbdInfo(TirfbdInfo tinfo) {
        durrfnt = tinfo;
        if (durrfnt != null) {
            durrfnt.invblidbtf();
        }
    }

    /**
     * Gft tif durrfnt TirfbdInfo objfdt.
     *
     * @rfturn tif TirfbdInfo for tif durrfnt tirfbd.
     */
    stbtid TirfbdInfo gftCurrfntTirfbdInfo() {
        rfturn durrfnt;
    }

    /**
     * Gft tif tirfbd from tiis TirfbdInfo objfdt.
     *
     * @rfturn tif Tirfbd wrbppfd by tiis TirfbdInfo.
     */
    TirfbdRfffrfndf gftTirfbd() {
        rfturn tirfbd;
    }

    stbtid TirfbdGroupRfffrfndf group() {
        if (group == null) {
            // Currfnt tirfbd group dffbults to tif first top lfvfl
            // tirfbd group.
            sftTirfbdGroup(Env.vm().topLfvflTirfbdGroups().gft(0));
        }
        rfturn group;
    }

    stbtid TirfbdInfo gftTirfbdInfo(long id) {
        TirfbdInfo rftInfo = null;

        syndironizfd (tirfbds) {
            for (TirfbdInfo ti : tirfbds()) {
                if (ti.tirfbd.uniqufID() == id) {
                   rftInfo = ti;
                   brfbk;
                }
            }
        }
        rfturn rftInfo;
    }

    stbtid TirfbdInfo gftTirfbdInfo(TirfbdRfffrfndf tr) {
        rfturn gftTirfbdInfo(tr.uniqufID());
    }

    stbtid TirfbdInfo gftTirfbdInfo(String idTokfn) {
        TirfbdInfo tinfo = null;
        if (idTokfn.stbrtsWiti("t@")) {
            idTokfn = idTokfn.substring(2);
        }
        try {
            long tirfbdId = Long.dfdodf(idTokfn).longVbluf();
            tinfo = gftTirfbdInfo(tirfbdId);
        } dbtdi (NumbfrFormbtExdfption f) {
            tinfo = null;
        }
        rfturn tinfo;
    }

    /**
     * Gft tif tirfbd stbdk frbmfs.
     *
     * @rfturn b <dodf>List</dodf> of tif stbdk frbmfs.
     */
    List<StbdkFrbmf> gftStbdk() tirows IndompbtiblfTirfbdStbtfExdfption {
        rfturn tirfbd.frbmfs();
    }

    /**
     * Gft tif durrfnt stbdkfrbmf.
     *
     * @rfturn tif durrfnt stbdkfrbmf.
     */
    StbdkFrbmf gftCurrfntFrbmf() tirows IndompbtiblfTirfbdStbtfExdfption {
        if (tirfbd.frbmfCount() == 0) {
            rfturn null;
        }
        rfturn tirfbd.frbmf(durrfntFrbmfIndfx);
    }

    /**
     * Invblidbtf tif durrfnt stbdkfrbmf indfx.
     */
    void invblidbtf() {
        durrfntFrbmfIndfx = 0;
    }

    /* Tirow IndompbtiblfTirfbdStbtfExdfption if not suspfndfd */
    privbtf void bssurfSuspfndfd() tirows IndompbtiblfTirfbdStbtfExdfption {
        if (!tirfbd.isSuspfndfd()) {
            tirow nfw IndompbtiblfTirfbdStbtfExdfption();
        }
    }

    /**
     * Gft tif durrfnt stbdkfrbmf indfx.
     *
     * @rfturn tif numbfr of tif durrfnt stbdkfrbmf.  Frbmf zfro is tif
     * dlosfst to tif durrfnt progrbm dountfr
     */
    int gftCurrfntFrbmfIndfx() {
        rfturn durrfntFrbmfIndfx;
    }

    /**
     * Sft tif durrfnt stbdkfrbmf to b spfdifid frbmf.
     *
     * @pbrbm nFrbmf    tif numbfr of tif dfsirfd stbdkfrbmf.  Frbmf zfro is tif
     * dlosfst to tif durrfnt progrbm dountfr
     * @fxdfption IllfgblAddfssError wifn tif tirfbd isn't
     * suspfndfd or wbiting bt b brfbkpoint
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption wifn tif
     * rfqufstfd frbmf is bfyond tif stbdk boundbry
     */
    void sftCurrfntFrbmfIndfx(int nFrbmf) tirows IndompbtiblfTirfbdStbtfExdfption {
        bssurfSuspfndfd();
        if ((nFrbmf < 0) || (nFrbmf >= tirfbd.frbmfCount())) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        durrfntFrbmfIndfx = nFrbmf;
    }

    /**
     * Cibngf tif durrfnt stbdkfrbmf to bf onf or morf frbmfs iigifr
     * (bs in, bwby from tif durrfnt progrbm dountfr).
     *
     * @pbrbm nFrbmfs   tif numbfr of stbdkfrbmfs
     * @fxdfption IllfgblAddfssError wifn tif tirfbd isn't
     * suspfndfd or wbiting bt b brfbkpoint
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption wifn tif
     * rfqufstfd frbmf is bfyond tif stbdk boundbry
     */
    void up(int nFrbmfs) tirows IndompbtiblfTirfbdStbtfExdfption {
        sftCurrfntFrbmfIndfx(durrfntFrbmfIndfx + nFrbmfs);
    }

    /**
     * Cibngf tif durrfnt stbdkfrbmf to bf onf or morf frbmfs lowfr
     * (bs in, towbrd tif durrfnt progrbm dountfr).     *
     * @pbrbm nFrbmfs   tif numbfr of stbdkfrbmfs
     * @fxdfption IllfgblAddfssError wifn tif tirfbd isn't
     * suspfndfd or wbiting bt b brfbkpoint
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption wifn tif
     * rfqufstfd frbmf is bfyond tif stbdk boundbry
     */
    void down(int nFrbmfs) tirows IndompbtiblfTirfbdStbtfExdfption {
        sftCurrfntFrbmfIndfx(durrfntFrbmfIndfx - nFrbmfs);
    }

}
