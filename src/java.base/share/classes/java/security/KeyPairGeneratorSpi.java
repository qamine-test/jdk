/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

/**
 * <p> Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@dodf KfyPbirGfnfrbtor} dlbss, wiidi is usfd to gfnfrbtf
 * pbirs of publid bnd privbtf kfys.
 *
 * <p> All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b kfy pbir gfnfrbtor for b pbrtidulbr blgoritim.
 *
 * <p> In dbsf tif dlifnt dofs not fxpliditly initiblizf tif KfyPbirGfnfrbtor
 * (vib b dbll to bn {@dodf initiblizf} mftiod), fbdi providfr must
 * supply (bnd dodumfnt) b dffbult initiblizbtion.
 * For fxbmplf, tif <i>Sun</i> providfr usfs b dffbult modulus sizf (kfysizf)
 * of 1024 bits.
 *
 * @butior Bfnjbmin Rfnbud
 *
 *
 * @sff KfyPbirGfnfrbtor
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 */

publid bbstrbdt dlbss KfyPbirGfnfrbtorSpi {

    /**
     * Initiblizfs tif kfy pbir gfnfrbtor for b dfrtbin kfysizf, using
     * tif dffbult pbrbmftfr sft.
     *
     * @pbrbm kfysizf tif kfysizf. Tiis is bn
     * blgoritim-spfdifid mftrid, sudi bs modulus lfngti, spfdififd in
     * numbfr of bits.
     *
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor.
     *
     * @fxdfption InvblidPbrbmftfrExdfption if tif {@dodf kfysizf} is not
     * supportfd by tiis KfyPbirGfnfrbtorSpi objfdt.
     */
    publid bbstrbdt void initiblizf(int kfysizf, SfdurfRbndom rbndom);

    /**
     * Initiblizfs tif kfy pbir gfnfrbtor using tif spfdififd pbrbmftfr
     * sft bnd usfr-providfd sourdf of rbndomnfss.
     *
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss. (For bbdkwbrds dompbtibility, it dbnnot bf bbstrbdt.)
     * It mby bf ovfrriddfn by b providfr to initiblizf tif kfy pbir
     * gfnfrbtor. Sudi bn ovfrridf
     * is fxpfdtfd to tirow bn InvblidAlgoritimPbrbmftfrExdfption if
     * b pbrbmftfr is inbppropribtf for tiis kfy pbir gfnfrbtor.
     * If tiis mftiod is not ovfrriddfn, it blwbys tirows bn
     * UnsupportfdOpfrbtionExdfption.
     *
     * @pbrbm pbrbms tif pbrbmftfr sft usfd to gfnfrbtf tif kfys.
     *
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor.
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy pbir gfnfrbtor.
     *
     * @sindf 1.2
     */
    publid void initiblizf(AlgoritimPbrbmftfrSpfd pbrbms,
                           SfdurfRbndom rbndom)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
            tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Gfnfrbtfs b kfy pbir. Unlfss bn initiblizbtion mftiod is dbllfd
     * using b KfyPbirGfnfrbtor intfrfbdf, blgoritim-spfdifid dffbults
     * will bf usfd. Tiis will gfnfrbtf b nfw kfy pbir fvfry timf it
     * is dbllfd.
     *
     * @rfturn tif nfwly gfnfrbtfd {@dodf KfyPbir}
     */
    publid bbstrbdt KfyPbir gfnfrbtfKfyPbir();
}
