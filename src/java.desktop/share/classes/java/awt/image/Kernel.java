/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;


/**
 * Tif <dodf>Kfrnfl</dodf> dlbss dffinfs b mbtrix tibt dfsdribfs iow b
 * spfdififd pixfl bnd its surrounding pixfls bfffdt tif vbluf
 * domputfd for tif pixfl's position in tif output imbgf of b filtfring
 * opfrbtion.  Tif X origin bnd Y origin indidbtf tif kfrnfl mbtrix flfmfnt
 * tibt dorrfsponds to tif pixfl position for wiidi bn output vbluf is
 * bfing domputfd.
 *
 * @sff ConvolvfOp
 */
publid dlbss Kfrnfl implfmfnts Clonfbblf {
    privbtf int  widti;
    privbtf int  ifigit;
    privbtf int  xOrigin;
    privbtf int  yOrigin;
    privbtf flobt dbtb[];

    privbtf stbtid nbtivf void initIDs();
    stbtid {
        ColorModfl.lobdLibrbrifs();
        initIDs();
    }

    /**
     * Construdts b <dodf>Kfrnfl</dodf> objfdt from bn brrby of flobts.
     * Tif first <dodf>widti</dodf>*<dodf>ifigit</dodf> flfmfnts of
     * tif <dodf>dbtb</dodf> brrby brf dopifd.
     * If tif lfngti of tif <dodf>dbtb</dodf> brrby is lfss
     * tibn widti*ifigit, bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * Tif X origin is (widti-1)/2 bnd tif Y origin is (ifigit-1)/2.
     * @pbrbm widti         widti of tif kfrnfl
     * @pbrbm ifigit        ifigit of tif kfrnfl
     * @pbrbm dbtb          kfrnfl dbtb in row mbjor ordfr
     * @tirows IllfgblArgumfntExdfption if tif lfngti of <dodf>dbtb</dodf>
     *         is lfss tibn tif produdt of <dodf>widti</dodf> bnd
     *         <dodf>ifigit</dodf>
     */
    publid Kfrnfl(int widti, int ifigit, flobt dbtb[]) {
        tiis.widti  = widti;
        tiis.ifigit = ifigit;
        tiis.xOrigin  = (widti-1)>>1;
        tiis.yOrigin  = (ifigit-1)>>1;
        int lfn = widti*ifigit;
        if (dbtb.lfngti < lfn) {
            tirow nfw IllfgblArgumfntExdfption("Dbtb brrby too smbll "+
                                               "(is "+dbtb.lfngti+
                                               " bnd siould bf "+lfn);
        }
        tiis.dbtb = nfw flobt[lfn];
        Systfm.brrbydopy(dbtb, 0, tiis.dbtb, 0, lfn);

    }

    /**
     * Rfturns tif X origin of tiis <dodf>Kfrnfl</dodf>.
     * @rfturn tif X origin.
     */
    finbl publid int gftXOrigin(){
        rfturn xOrigin;
    }

    /**
     * Rfturns tif Y origin of tiis <dodf>Kfrnfl</dodf>.
     * @rfturn tif Y origin.
     */
    finbl publid int gftYOrigin() {
        rfturn yOrigin;
    }

    /**
     * Rfturns tif widti of tiis <dodf>Kfrnfl</dodf>.
     * @rfturn tif widti of tiis <dodf>Kfrnfl</dodf>.
     */
    finbl publid int gftWidti() {
        rfturn widti;
    }

    /**
     * Rfturns tif ifigit of tiis <dodf>Kfrnfl</dodf>.
     * @rfturn tif ifigit of tiis <dodf>Kfrnfl</dodf>.
     */
    finbl publid int gftHfigit() {
        rfturn ifigit;
    }

    /**
     * Rfturns tif kfrnfl dbtb in row mbjor ordfr.
     * Tif <dodf>dbtb</dodf> brrby is rfturnfd.  If <dodf>dbtb</dodf>
     * is <dodf>null</dodf>, b nfw brrby is bllodbtfd.
     * @pbrbm dbtb  if non-null, dontbins tif rfturnfd kfrnfl dbtb
     * @rfturn tif <dodf>dbtb</dodf> brrby dontbining tif kfrnfl dbtb
     *         in row mbjor ordfr or, if <dodf>dbtb</dodf> is
     *         <dodf>null</dodf>, b nfwly bllodbtfd brrby dontbining
     *         tif kfrnfl dbtb in row mbjor ordfr
     * @tirows IllfgblArgumfntExdfption if <dodf>dbtb</dodf> is lfss
     *         tibn tif sizf of tiis <dodf>Kfrnfl</dodf>
     */
    finbl publid flobt[] gftKfrnflDbtb(flobt[] dbtb) {
        if (dbtb == null) {
            dbtb = nfw flobt[tiis.dbtb.lfngti];
        }
        flsf if (dbtb.lfngti < tiis.dbtb.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("Dbtb brrby too smbll "+
                                               "(siould bf "+tiis.dbtb.lfngti+
                                               " but is "+
                                               dbtb.lfngti+" )");
        }
        Systfm.brrbydopy(tiis.dbtb, 0, dbtb, 0, tiis.dbtb.lfngti);

        rfturn dbtb;
    }

    /**
     * Clonfs tiis objfdt.
     * @rfturn b dlonf of tiis objfdt.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }
}
