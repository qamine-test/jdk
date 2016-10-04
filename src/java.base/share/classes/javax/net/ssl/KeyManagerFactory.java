/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.*;

import sun.sfdurity.jdb.GftInstbndf;

/**
 * Tiis dlbss bdts bs b fbdtory for kfy mbnbgfrs bbsfd on b
 * sourdf of kfy mbtfribl. Ebdi kfy mbnbgfr mbnbgfs b spfdifid
 * typf of kfy mbtfribl for usf by sfdurf sodkfts. Tif kfy
 * mbtfribl is bbsfd on b KfyStorf bnd/or providfr spfdifid sourdfs.
 *
 * @sindf 1.4
 * @sff KfyMbnbgfr
 */
publid dlbss KfyMbnbgfrFbdtory {
    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf KfyMbnbgfrFbdtorySpi fbdtorySpi;

    // Tif nbmf of tif kfy mbnbgfmfnt blgoritim.
    privbtf String blgoritim;

    /**
     * Obtbins tif dffbult KfyMbnbgfrFbdtory blgoritim nbmf.
     *
     * <p>Tif dffbult blgoritim dbn bf dibngfd bt runtimf by sftting
     * tif vbluf of tif {@dodf ssl.KfyMbnbgfrFbdtory.blgoritim}
     * sfdurity propfrty to tif dfsirfd blgoritim nbmf.
     *
     * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
     * @rfturn tif dffbult blgoritim nbmf bs spfdififd by tif
     *          {@dodf ssl.KfyMbnbgfrFbdtory.blgoritim} sfdurity propfrty, or bn
     *          implfmfntbtion-spfdifid dffbult if no sudi propfrty fxists.
     */
    publid finbl stbtid String gftDffbultAlgoritim() {
        String typf;
        typf = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            @Ovfrridf
            publid String run() {
                rfturn Sfdurity.gftPropfrty(
                    "ssl.KfyMbnbgfrFbdtory.blgoritim");
            }
        });
        if (typf == null) {
            typf = "SunX509";
        }
        rfturn typf;
    }

    /**
     * Crfbtfs b KfyMbnbgfrFbdtory objfdt.
     *
     * @pbrbm fbdtorySpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd KfyMbnbgfrFbdtory(KfyMbnbgfrFbdtorySpi fbdtorySpi,
                                Providfr providfr, String blgoritim) {
        tiis.fbdtorySpi = fbdtorySpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns tif blgoritim nbmf of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @rfturn tif blgoritim nbmf of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Rfturns b <dodf>KfyMbnbgfrFbdtory</dodf> objfdt tibt bdts bs b
     * fbdtory for kfy mbnbgfrs.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw KfyMbnbgfrFbdtory objfdt fndbpsulbting tif
     * KfyMbnbgfrFbdtorySpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd blgoritim.
     *          Sff tif <b irff=
     *  "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/jssf/JSSERffGuidf.itml">
     *          Jbvb Sfdurf Sodkft Extfnsion Rfffrfndf Guidf </b>
     *          for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nfw <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          KfyMbnbgfrFbdtorySpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     * @fxdfption NullPointfrExdfption if <dodf>blgoritim</dodf> is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl KfyMbnbgfrFbdtory gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                ("KfyMbnbgfrFbdtory", KfyMbnbgfrFbdtorySpi.dlbss,
                blgoritim);
        rfturn nfw KfyMbnbgfrFbdtory((KfyMbnbgfrFbdtorySpi)instbndf.impl,
                instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b <dodf>KfyMbnbgfrFbdtory</dodf> objfdt tibt bdts bs b
     * fbdtory for kfy mbnbgfrs.
     *
     * <p> A nfw KfyMbnbgfrFbdtory objfdt fndbpsulbting tif
     * KfyMbnbgfrFbdtorySpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.

     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd blgoritim.
     *          Sff tif <b irff=
     *  "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/jssf/JSSERffGuidf.itml">
     *          Jbvb Sfdurf Sodkft Extfnsion Rfffrfndf Guidf </b>
     *          for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @tirows NoSudiAlgoritimExdfption if b KfyMbnbgfrFbdtorySpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @tirows IllfgblArgumfntExdfption if tif providfr nbmf is null or fmpty.
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl KfyMbnbgfrFbdtory gftInstbndf(String blgoritim,
            String providfr) tirows NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption {
        GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                ("KfyMbnbgfrFbdtory", KfyMbnbgfrFbdtorySpi.dlbss,
                blgoritim, providfr);
        rfturn nfw KfyMbnbgfrFbdtory((KfyMbnbgfrFbdtorySpi)instbndf.impl,
                instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b <dodf>KfyMbnbgfrFbdtory</dodf> objfdt tibt bdts bs b
     * fbdtory for kfy mbnbgfrs.
     *
     * <p> A nfw KfyMbnbgfrFbdtory objfdt fndbpsulbting tif
     * KfyMbnbgfrFbdtorySpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd blgoritim.
     *          Sff tif <b irff=
     *  "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/jssf/JSSERffGuidf.itml">
     *          Jbvb Sfdurf Sodkft Extfnsion Rfffrfndf Guidf </b>
     *          for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr bn instbndf of tif providfr.
     *
     * @rfturn tif nfw <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @tirows NoSudiAlgoritimExdfption if b KfyMbnbgfrFbdtorySpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @tirows IllfgblArgumfntExdfption if providfr is null.
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl KfyMbnbgfrFbdtory gftInstbndf(String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                ("KfyMbnbgfrFbdtory", KfyMbnbgfrFbdtorySpi.dlbss,
                blgoritim, providfr);
        rfturn nfw KfyMbnbgfrFbdtory((KfyMbnbgfrFbdtorySpi)instbndf.impl,
                instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }


    /**
     * Initiblizfs tiis fbdtory witi b sourdf of kfy mbtfribl.
     * <P>
     * Tif providfr typidblly usfs b KfyStorf for obtbining
     * kfy mbtfribl for usf during sfdurf sodkft nfgotibtions.
     * Tif KfyStorf is gfnfrblly pbssword-protfdtfd.
     * <P>
     * For morf flfxiblf initiblizbtion, plfbsf sff
     * {@link #init(MbnbgfrFbdtoryPbrbmftfrs)}.
     * <P>
     *
     * @pbrbm ks tif kfy storf or null
     * @pbrbm pbssword tif pbssword for rfdovfring kfys in tif KfyStorf
     * @tirows KfyStorfExdfption if tiis opfrbtion fbils
     * @tirows NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     * @tirows UnrfdovfrbblfKfyExdfption if tif kfy dbnnot bf rfdovfrfd
     *          (f.g. tif givfn pbssword is wrong).
     */
    publid finbl void init(KfyStorf ks, dibr[] pbssword) tirows
            KfyStorfExdfption, NoSudiAlgoritimExdfption,
            UnrfdovfrbblfKfyExdfption {
        fbdtorySpi.fnginfInit(ks, pbssword);
    }


    /**
     * Initiblizfs tiis fbdtory witi b sourdf of providfr-spfdifid
     * kfy mbtfribl.
     * <P>
     * In somf dbsfs, initiblizbtion pbrbmftfrs otifr tibn b kfystorf
     * bnd pbssword mby bf nffdfd by b providfr.  Usfrs of tibt
     * pbrtidulbr providfr brf fxpfdtfd to pbss bn implfmfntbtion of
     * tif bppropribtf <CODE>MbnbgfrFbdtoryPbrbmftfrs</CODE> bs
     * dffinfd by tif providfr.  Tif providfr dbn tifn dbll tif
     * spfdififd mftiods in tif <CODE>MbnbgfrFbdtoryPbrbmftfrs</CODE>
     * implfmfntbtion to obtbin tif nffdfd informbtion.
     *
     * @pbrbm spfd bn implfmfntbtion of b providfr-spfdifid pbrbmftfr
     *          spfdifidbtion
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if bn frror is fndountfrfd
     */
    publid finbl void init(MbnbgfrFbdtoryPbrbmftfrs spfd) tirows
            InvblidAlgoritimPbrbmftfrExdfption {
        fbdtorySpi.fnginfInit(spfd);
    }


    /**
     * Rfturns onf kfy mbnbgfr for fbdi typf of kfy mbtfribl.
     *
     * @rfturn tif kfy mbnbgfrs
     * @tirows IllfgblStbtfExdfption if tif KfyMbnbgfrFbdtory is not initiblizfd
     */
    publid finbl KfyMbnbgfr[] gftKfyMbnbgfrs() {
        rfturn fbdtorySpi.fnginfGftKfyMbnbgfrs();
    }
}
