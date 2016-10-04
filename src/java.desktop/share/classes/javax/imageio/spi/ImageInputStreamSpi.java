/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.spi;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

/**
 * Tif sfrvidf providfr intfrfbdf (SPI) for
 * <dodf>ImbgfInputStrfbm</dodf>s.  For morf informbtion on sfrvidf
 * providfr intfrfbdfs, sff tif dlbss dommfnt for tif
 * <dodf>IIORfgistry</dodf> dlbss.
 *
 * <p> Tiis intfrfbdf bllows brbitrbry objfdts to bf "wrbppfd" by
 * instbndfs of <dodf>ImbgfInputStrfbm</dodf>.  For fxbmplf,
 * b pbrtidulbr <dodf>ImbgfInputStrfbmSpi</dodf> migit bllow
 * b gfnfrid <dodf>InputStrfbm</dodf> to bf usfd bs bn input sourdf;
 * bnotifr migit tbkf input from b <dodf>URL</dodf>.
 *
 * <p> By trfbting tif drfbtion of <dodf>ImbgfInputStrfbm</dodf>s bs b
 * pluggbblf sfrvidf, it bfdomfs possiblf to ibndlf futurf input
 * sourdfs witiout dibnging tif API.  Also, iigi-pfrformbndf
 * implfmfntbtions of <dodf>ImbgfInputStrfbm</dodf> (for fxbmplf,
 * nbtivf implfmfntbtions for b pbrtidulbr plbtform) dbn bf instbllfd
 * bnd usfd trbnspbrfntly by bpplidbtions.
 *
 * @sff IIORfgistry
 * @sff jbvbx.imbgfio.strfbm.ImbgfInputStrfbm
 *
 */
publid bbstrbdt dlbss ImbgfInputStrfbmSpi fxtfnds IIOSfrvidfProvidfr {

    /**
     * A <dodf>Clbss</dodf> objfdt indidbting tif lfgbl objfdt typf
     * for usf by tif <dodf>drfbtfInputStrfbmInstbndf</dodf> mftiod.
     */
    protfdtfd Clbss<?> inputClbss;

    /**
     * Construdts b blbnk <dodf>ImbgfInputStrfbmSpi</dodf>.  It is up
     * to tif subdlbss to initiblizf instbndf vbribblfs bnd/or
     * ovfrridf mftiod implfmfntbtions in ordfr to providf working
     * vfrsions of bll mftiods.
     */
    protfdtfd ImbgfInputStrfbmSpi() {
    }

    /**
     * Construdts bn <dodf>ImbgfInputStrfbmSpi</dodf> witi b givfn sft
     * of vblufs.
     *
     * @pbrbm vfndorNbmf tif vfndor nbmf.
     * @pbrbm vfrsion b vfrsion idfntififr.
     * @pbrbm inputClbss b <dodf>Clbss</dodf> objfdt indidbting tif
     * lfgbl objfdt typf for usf by tif
     * <dodf>drfbtfInputStrfbmInstbndf</dodf> mftiod.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>vfndorNbmf</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>vfrsion</dodf>
     * is <dodf>null</dodf>.
     */
    publid ImbgfInputStrfbmSpi(String vfndorNbmf,
                               String vfrsion,
                               Clbss<?> inputClbss) {
        supfr(vfndorNbmf, vfrsion);
        tiis.inputClbss = inputClbss;
    }

    /**
     * Rfturns b <dodf>Clbss</dodf> objfdt rfprfsfnting tif dlbss or
     * intfrfbdf typf tibt must bf implfmfntfd by bn input sourdf in
     * ordfr to bf "wrbppfd" in bn <dodf>ImbgfInputStrfbm</dodf> vib
     * tif <dodf>drfbtfInputStrfbmInstbndf</dodf> mftiod.
     *
     * <p> Typidbl rfturn vblufs migit indludf
     * <dodf>InputStrfbm.dlbss</dodf> or <dodf>URL.dlbss</dodf>, but
     * bny dlbss mby bf usfd.
     *
     * @rfturn b <dodf>Clbss</dodf> vbribblf.
     *
     * @sff #drfbtfInputStrfbmInstbndf(Objfdt, boolfbn, Filf)
     */
    publid Clbss<?> gftInputClbss() {
        rfturn inputClbss;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>ImbgfInputStrfbm</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr dbn
     * optionblly mbkf usf of b dbdif filf for improvfd pfrformbndf
     * bnd/or mfmory footrprint.  If <dodf>fblsf</dodf>, tif vbluf of
     * tif <dodf>usfCbdif</dodf> brgumfnt to
     * <dodf>drfbtfInputStrfbmInstbndf</dodf> will bf ignorfd.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if b dbdif filf dbn bf usfd by tif
     * input strfbms drfbtfd by tiis sfrvidf providfr.
     */
    publid boolfbn dbnUsfCbdifFilf() {
        rfturn fblsf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>ImbgfInputStrfbm</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr rfquirfs
     * tif usf of b dbdif <dodf>Filf</dodf>.  If <dodf>truf</dodf>,
     * tif vbluf of tif <dodf>usfCbdif</dodf> brgumfnt to
     * <dodf>drfbtfInputStrfbmInstbndf</dodf> will bf ignorfd.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if b dbdif filf is nffdfd by tif
     * input strfbms drfbtfd by tiis sfrvidf providfr.
     */
    publid boolfbn nffdsCbdifFilf() {
        rfturn fblsf;
    }

    /**
     * Rfturns bn instbndf of tif <dodf>ImbgfInputStrfbm</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr.  If tif
     * usf of b dbdif filf is optionbl, tif <dodf>usfCbdif</dodf>
     * pbrbmftfr will bf donsultfd.  Wifrf b dbdif is rfquirfd, or
     * not bpplidbblf, tif vbluf of <dodf>usfCbdif</dodf> will bf ignorfd.
     *
     * @pbrbm input bn objfdt of tif dlbss typf rfturnfd by
     * <dodf>gftInputClbss</dodf>.
     * @pbrbm usfCbdif b <dodf>boolfbn</dodf> indidbting wiftifr b
     * dbdif filf siould bf usfd, in dbsfs wifrf it is optionbl.
     * @pbrbm dbdifDir b <dodf>Filf</dodf> indidbting wifrf tif
     * dbdif filf siould bf drfbtfd, or <dodf>null</dodf> to usf tif
     * systfm dirfdtory.
     *
     * @rfturn bn <dodf>ImbgfInputStrfbm</dodf> instbndf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * not bn instbndf of tif dorrfdt dlbss or is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if b dbdif filf is nffdfd
     * but <dodf>dbdifDir</dodf> is non-<dodf>null</dodf> bnd is not b
     * dirfdtory.
     * @fxdfption IOExdfption if b dbdif filf is nffdfd but dbnnot bf
     * drfbtfd.
     *
     * @sff #gftInputClbss
     * @sff #dbnUsfCbdifFilf
     * @sff #nffdsCbdifFilf
     */
    publid bbstrbdt ImbgfInputStrfbm
        drfbtfInputStrfbmInstbndf(Objfdt input,
                                  boolfbn usfCbdif,
                                  Filf dbdifDir) tirows IOExdfption;

    /**
     * Rfturns bn instbndf of tif <dodf>ImbgfInputStrfbm</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr.  A dbdif
     * filf will bf drfbtfd in tif systfm-dfpfndfnt dffbult
     * tfmporbry-filf dirfdtory, if nffdfd.
     *
     * @pbrbm input bn objfdt of tif dlbss typf rfturnfd by
     * <dodf>gftInputClbss</dodf>.
     *
     * @rfturn bn <dodf>ImbgfInputStrfbm</dodf> instbndf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * not bn instbndf of tif dorrfdt dlbss or is <dodf>null</dodf>.
     * @fxdfption IOExdfption if b dbdif filf is nffdfd but dbnnot bf
     * drfbtfd.
     *
     * @sff #gftInputClbss()
     */
    publid ImbgfInputStrfbm drfbtfInputStrfbmInstbndf(Objfdt input)
        tirows IOExdfption {
        rfturn drfbtfInputStrfbmInstbndf(input, truf, null);
    }
}
