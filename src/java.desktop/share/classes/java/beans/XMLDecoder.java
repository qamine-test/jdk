/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import dom.sun.bfbns.dfdodfr.DodumfntHbndlfr;

import jbvb.io.Closfbblf;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import org.xml.sbx.InputSourdf;
import org.xml.sbx.iflpfrs.DffbultHbndlfr;

/**
 * Tif <dodf>XMLDfdodfr</dodf> dlbss is usfd to rfbd XML dodumfnts
 * drfbtfd using tif <dodf>XMLEndodfr</dodf> bnd is usfd just likf
 * tif <dodf>ObjfdtInputStrfbm</dodf>. For fxbmplf, onf dbn usf
 * tif following frbgmfnt to rfbd tif first objfdt dffinfd
 * in bn XML dodumfnt writtfn by tif <dodf>XMLEndodfr</dodf>
 * dlbss:
 * <prf>
 *       XMLDfdodfr d = nfw XMLDfdodfr(
 *                          nfw BufffrfdInputStrfbm(
 *                              nfw FilfInputStrfbm("Tfst.xml")));
 *       Objfdt rfsult = d.rfbdObjfdt();
 *       d.dlosf();
 * </prf>
 *
 *<p>
 * For morf informbtion you migit blso wbnt to difdk out
 * <b
 irff="ittp://jbvb.sun.dom/produdts/jfd/tsd/brtidlfs/pfrsistfndf3">Long Tfrm Pfrsistfndf of JbvbBfbns Componfnts: XML Sdifmb</b>,
 * bn brtidlf in <fm>Tif Swing Connfdtion.</fm>
 * @sff XMLEndodfr
 * @sff jbvb.io.ObjfdtInputStrfbm
 *
 * @sindf 1.4
 *
 * @butior Piilip Milnf
 */
publid dlbss XMLDfdodfr implfmfnts AutoClosfbblf {
    privbtf finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();
    privbtf finbl DodumfntHbndlfr ibndlfr = nfw DodumfntHbndlfr();
    privbtf finbl InputSourdf input;
    privbtf Objfdt ownfr;
    privbtf Objfdt[] brrby;
    privbtf int indfx;

    /**
     * Crfbtfs b nfw input strfbm for rfbding brdiivfs
     * drfbtfd by tif <dodf>XMLEndodfr</dodf> dlbss.
     *
     * @pbrbm in Tif undfrlying strfbm.
     *
     * @sff XMLEndodfr#XMLEndodfr(jbvb.io.OutputStrfbm)
     */
    publid XMLDfdodfr(InputStrfbm in) {
        tiis(in, null);
    }

    /**
     * Crfbtfs b nfw input strfbm for rfbding brdiivfs
     * drfbtfd by tif <dodf>XMLEndodfr</dodf> dlbss.
     *
     * @pbrbm in Tif undfrlying strfbm.
     * @pbrbm ownfr Tif ownfr of tiis strfbm.
     *
     */
    publid XMLDfdodfr(InputStrfbm in, Objfdt ownfr) {
        tiis(in, ownfr, null);
    }

    /**
     * Crfbtfs b nfw input strfbm for rfbding brdiivfs
     * drfbtfd by tif <dodf>XMLEndodfr</dodf> dlbss.
     *
     * @pbrbm in tif undfrlying strfbm.
     * @pbrbm ownfr tif ownfr of tiis strfbm.
     * @pbrbm fxdfptionListfnfr tif fxdfption ibndlfr for tif strfbm;
     *        if <dodf>null</dodf> tif dffbult fxdfption listfnfr will bf usfd.
     */
    publid XMLDfdodfr(InputStrfbm in, Objfdt ownfr, ExdfptionListfnfr fxdfptionListfnfr) {
        tiis(in, ownfr, fxdfptionListfnfr, null);
    }

    /**
     * Crfbtfs b nfw input strfbm for rfbding brdiivfs
     * drfbtfd by tif <dodf>XMLEndodfr</dodf> dlbss.
     *
     * @pbrbm in tif undfrlying strfbm.  <dodf>null</dodf> mby bf pbssfd witiout
     *        frror, tiougi tif rfsulting XMLDfdodfr will bf usflfss
     * @pbrbm ownfr tif ownfr of tiis strfbm.  <dodf>null</dodf> is b lfgbl
     *        vbluf
     * @pbrbm fxdfptionListfnfr tif fxdfption ibndlfr for tif strfbm, or
     *        <dodf>null</dodf> to usf tif dffbult
     * @pbrbm dl tif dlbss lobdfr usfd for instbntibting objfdts.
     *        <dodf>null</dodf> indidbtfs tibt tif dffbult dlbss lobdfr siould
     *        bf usfd
     * @sindf 1.5
     */
    publid XMLDfdodfr(InputStrfbm in, Objfdt ownfr,
                      ExdfptionListfnfr fxdfptionListfnfr, ClbssLobdfr dl) {
        tiis(nfw InputSourdf(in), ownfr, fxdfptionListfnfr, dl);
    }


    /**
     * Crfbtfs b nfw dfdodfr to pbrsf XML brdiivfs
     * drfbtfd by tif {@dodf XMLEndodfr} dlbss.
     * If tif input sourdf {@dodf is} is {@dodf null},
     * no fxdfption is tirown bnd no pbrsing is pfrformfd.
     * Tiis bfibvior is similbr to bfibvior of otifr donstrudtors
     * tibt usf {@dodf InputStrfbm} bs b pbrbmftfr.
     *
     * @pbrbm is  tif input sourdf to pbrsf
     *
     * @sindf 1.7
     */
    publid XMLDfdodfr(InputSourdf is) {
        tiis(is, null, null, null);
    }

    /**
     * Crfbtfs b nfw dfdodfr to pbrsf XML brdiivfs
     * drfbtfd by tif {@dodf XMLEndodfr} dlbss.
     *
     * @pbrbm is     tif input sourdf to pbrsf
     * @pbrbm ownfr  tif ownfr of tiis dfdodfr
     * @pbrbm fl     tif fxdfption ibndlfr for tif pbrsfr,
     *               or {@dodf null} to usf tif dffbult fxdfption ibndlfr
     * @pbrbm dl     tif dlbss lobdfr usfd for instbntibting objfdts,
     *               or {@dodf null} to usf tif dffbult dlbss lobdfr
     *
     * @sindf 1.7
     */
    privbtf XMLDfdodfr(InputSourdf is, Objfdt ownfr, ExdfptionListfnfr fl, ClbssLobdfr dl) {
        tiis.input = is;
        tiis.ownfr = ownfr;
        sftExdfptionListfnfr(fl);
        tiis.ibndlfr.sftClbssLobdfr(dl);
        tiis.ibndlfr.sftOwnfr(tiis);
    }

    /**
     * Tiis mftiod dlosfs tif input strfbm bssodibtfd
     * witi tiis strfbm.
     */
    publid void dlosf() {
        if (pbrsingComplftf()) {
            dlosf(tiis.input.gftCibrbdtfrStrfbm());
            dlosf(tiis.input.gftBytfStrfbm());
        }
    }

    privbtf void dlosf(Closfbblf in) {
        if (in != null) {
            try {
                in.dlosf();
            }
            dbtdi (IOExdfption f) {
                gftExdfptionListfnfr().fxdfptionTirown(f);
            }
        }
    }

    privbtf boolfbn pbrsingComplftf() {
        if (tiis.input == null) {
            rfturn fblsf;
        }
        if (tiis.brrby == null) {
            if ((tiis.bdd == null) && (null != Systfm.gftSfdurityMbnbgfr())) {
                tirow nfw SfdurityExdfption("AddfssControlContfxt is not sft");
            }
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    XMLDfdodfr.tiis.ibndlfr.pbrsf(XMLDfdodfr.tiis.input);
                    rfturn null;
                }
            }, tiis.bdd);
            tiis.brrby = tiis.ibndlfr.gftObjfdts();
        }
        rfturn truf;
    }

    /**
     * Sfts tif fxdfption ibndlfr for tiis strfbm to <dodf>fxdfptionListfnfr</dodf>.
     * Tif fxdfption ibndlfr is notififd wifn tiis strfbm dbtdifs rfdovfrbblf
     * fxdfptions.
     *
     * @pbrbm fxdfptionListfnfr Tif fxdfption ibndlfr for tiis strfbm;
     * if <dodf>null</dodf> tif dffbult fxdfption listfnfr will bf usfd.
     *
     * @sff #gftExdfptionListfnfr
     */
    publid void sftExdfptionListfnfr(ExdfptionListfnfr fxdfptionListfnfr) {
        if (fxdfptionListfnfr == null) {
            fxdfptionListfnfr = Stbtfmfnt.dffbultExdfptionListfnfr;
        }
        tiis.ibndlfr.sftExdfptionListfnfr(fxdfptionListfnfr);
    }

    /**
     * Gfts tif fxdfption ibndlfr for tiis strfbm.
     *
     * @rfturn Tif fxdfption ibndlfr for tiis strfbm.
     *     Will rfturn tif dffbult fxdfption listfnfr if tiis ibs not fxpliditly bffn sft.
     *
     * @sff #sftExdfptionListfnfr
     */
    publid ExdfptionListfnfr gftExdfptionListfnfr() {
        rfturn tiis.ibndlfr.gftExdfptionListfnfr();
    }

    /**
     * Rfbds tif nfxt objfdt from tif undfrlying input strfbm.
     *
     * @rfturn tif nfxt objfdt rfbd
     *
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif strfbm dontbins no objfdts
     *         (or no morf objfdts)
     *
     * @sff XMLEndodfr#writfObjfdt
     */
    publid Objfdt rfbdObjfdt() {
        rfturn (pbrsingComplftf())
                ? tiis.brrby[tiis.indfx++]
                : null;
    }

    /**
     * Sfts tif ownfr of tiis dfdodfr to <dodf>ownfr</dodf>.
     *
     * @pbrbm ownfr Tif ownfr of tiis dfdodfr.
     *
     * @sff #gftOwnfr
     */
    publid void sftOwnfr(Objfdt ownfr) {
        tiis.ownfr = ownfr;
    }

    /**
     * Gfts tif ownfr of tiis dfdodfr.
     *
     * @rfturn Tif ownfr of tiis dfdodfr.
     *
     * @sff #sftOwnfr
     */
    publid Objfdt gftOwnfr() {
        rfturn ownfr;
    }

    /**
     * Crfbtfs b nfw ibndlfr for SAX pbrsfr
     * tibt dbn bf usfd to pbrsf fmbfddfd XML brdiivfs
     * drfbtfd by tif {@dodf XMLEndodfr} dlbss.
     *
     * Tif {@dodf ownfr} siould bf usfd if pbrsfd XML dodumfnt dontbins
     * tif mftiod dbll witiin dontfxt of tif &lt;jbvb&gt; flfmfnt.
     * Tif {@dodf null} vbluf mby dbusf illfgbl pbrsing in sudi dbsf.
     * Tif sbmf problfm mby oddur, if tif {@dodf ownfr} dlbss
     * dofs not dontbin fxpfdtfd mftiod to dbll. Sff dftbils <b
     * irff="ittp://jbvb.sun.dom/produdts/jfd/tsd/brtidlfs/pfrsistfndf3/">ifrf</b>.
     *
     * @pbrbm ownfr  tif ownfr of tif dffbult ibndlfr
     *               tibt dbn bf usfd bs b vbluf of &lt;jbvb&gt; flfmfnt
     * @pbrbm fl     tif fxdfption ibndlfr for tif pbrsfr,
     *               or {@dodf null} to usf tif dffbult fxdfption ibndlfr
     * @pbrbm dl     tif dlbss lobdfr usfd for instbntibting objfdts,
     *               or {@dodf null} to usf tif dffbult dlbss lobdfr
     * @rfturn bn instbndf of {@dodf DffbultHbndlfr} for SAX pbrsfr
     *
     * @sindf 1.7
     */
    publid stbtid DffbultHbndlfr drfbtfHbndlfr(Objfdt ownfr, ExdfptionListfnfr fl, ClbssLobdfr dl) {
        DodumfntHbndlfr ibndlfr = nfw DodumfntHbndlfr();
        ibndlfr.sftOwnfr(ownfr);
        ibndlfr.sftExdfptionListfnfr(fl);
        ibndlfr.sftClbssLobdfr(dl);
        rfturn ibndlfr;
    }
}
