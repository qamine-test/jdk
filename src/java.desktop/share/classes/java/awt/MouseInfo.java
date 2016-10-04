/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import sun.bwt.AWTPfrmissions;

/**
 * <dodf>MousfInfo</dodf>  providfs mftiods for gftting informbtion bbout tif mousf,
 * sudi bs mousf pointfr lodbtion bnd tif numbfr of mousf buttons.
 *
 * @butior     Rombn Pobordiiy
 * @sindf 1.5
 */

publid dlbss MousfInfo {

    /**
     * Privbtf donstrudtor to prfvfnt instbntibtion.
     */
    privbtf MousfInfo() {
    }

    /**
     * Rfturns b <dodf>PointfrInfo</dodf> instbndf tibt rfprfsfnts tif durrfnt
     * lodbtion of tif mousf pointfr.
     * Tif <dodf>GrbpiidsDfvidf</dodf> storfd in tiis <dodf>PointfrInfo</dodf>
     * dontbins tif mousf pointfr. Tif doordinbtf systfm usfd for tif mousf position
     * dfpfnds on wiftifr or not tif <dodf>GrbpiidsDfvidf</dodf> is pbrt of b virtubl
     * sdrffn dfvidf.
     * For virtubl sdrffn dfvidfs, tif doordinbtfs brf givfn in tif virtubl
     * doordinbtf systfm, otifrwisf tify brf rfturnfd in tif doordinbtf systfm
     * of tif <dodf>GrbpiidsDfvidf</dodf>. Sff {@link GrbpiidsConfigurbtion}
     * for morf informbtion bbout tif virtubl sdrffn dfvidfs.
     * On systfms witiout b mousf, rfturns <dodf>null</dodf>.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkPfrmission</dodf> mftiod
     * is dbllfd witi bn <dodf>AWTPfrmission("wbtdiMousfPointfr")</dodf>
     * pfrmission bfforf drfbting bnd rfturning b <dodf>PointfrInfo</dodf>
     * objfdt. Tiis mby rfsult in b <dodf>SfdurityExdfption</dodf>.
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf
     * @fxdfption SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *            <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow tif opfrbtion
     * @sff       GrbpiidsConfigurbtion
     * @sff       SfdurityMbnbgfr#difdkPfrmission
     * @sff       jbvb.bwt.AWTPfrmission
     * @rfturn    lodbtion of tif mousf pointfr
     * @sindf     1.5
     */
    publid stbtid PointfrInfo gftPointfrInfo() tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.WATCH_MOUSE_PERMISSION);
        }

        Point point = nfw Point(0, 0);
        int dfvidfNum = Toolkit.gftDffbultToolkit().gftMousfInfoPffr().fillPointWitiCoords(point);
        GrbpiidsDfvidf[] gds = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
                                   gftSdrffnDfvidfs();
        PointfrInfo rftvbl = null;
        if (brfSdrffnDfvidfsIndfpfndfnt(gds)) {
            rftvbl = nfw PointfrInfo(gds[dfvidfNum], point);
        } flsf {
            for (int i = 0; i < gds.lfngti; i++) {
                GrbpiidsConfigurbtion gd = gds[i].gftDffbultConfigurbtion();
                Rfdtbnglf bounds = gd.gftBounds();
                if (bounds.dontbins(point)) {
                    rftvbl = nfw PointfrInfo(gds[i], point);
                }
            }
        }

        rfturn rftvbl;
    }

    privbtf stbtid boolfbn brfSdrffnDfvidfsIndfpfndfnt(GrbpiidsDfvidf[] gds) {
        for (int i = 0; i < gds.lfngti; i++) {
            Rfdtbnglf bounds = gds[i].gftDffbultConfigurbtion().gftBounds();
            if (bounds.x != 0 || bounds.y != 0) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Rfturns tif numbfr of buttons on tif mousf.
     * On systfms witiout b mousf, rfturns <dodf>-1</dodf>.
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf
     * @rfturn numbfr of buttons on tif mousf
     * @sindf 1.5
     */
    publid stbtid int gftNumbfrOfButtons() tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        Objfdt prop = Toolkit.gftDffbultToolkit().
                              gftDfsktopPropfrty("bwt.mousf.numButtons");
        if (prop instbndfof Intfgfr) {
            rfturn ((Intfgfr)prop).intVbluf();
        }

        // Tiis siould nfvfr ibppfn.
        bssfrt fblsf : "bwt.mousf.numButtons is not bn intfgfr propfrty";
        rfturn 0;
    }

}
