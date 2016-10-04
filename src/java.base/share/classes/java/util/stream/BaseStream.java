/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.filf.Filfs;
import jbvb.nio.filf.Pbti;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.Prfdidbtf;

/**
 * Bbsf intfrfbdf for strfbms, wiidi brf sfqufndfs of flfmfnts supporting
 * sfqufntibl bnd pbrbllfl bggrfgbtf opfrbtions.  Tif following fxbmplf
 * illustrbtfs bn bggrfgbtf opfrbtion using tif strfbm typfs {@link Strfbm}
 * bnd {@link IntStrfbm}, domputing tif sum of tif wfigits of tif rfd widgfts:
 *
 * <prf>{@dodf
 *     int sum = widgfts.strfbm()
 *                      .filtfr(w -> w.gftColor() == RED)
 *                      .mbpToInt(w -> w.gftWfigit())
 *                      .sum();
 * }</prf>
 *
 * Sff tif dlbss dodumfntbtion for {@link Strfbm} bnd tif pbdkbgf dodumfntbtion
 * for <b irff="pbdkbgf-summbry.itml">jbvb.util.strfbm</b> for bdditionbl
 * spfdifidbtion of strfbms, strfbm opfrbtions, strfbm pipflinfs, bnd
 * pbrbllflism, wiidi govfrns tif bfibvior of bll strfbm typfs.
 *
 * @pbrbm <T> tif typf of tif strfbm flfmfnts
 * @pbrbm <S> tif typf of tif strfbm implfmfnting {@dodf BbsfStrfbm}
 * @sindf 1.8
 * @sff Strfbm
 * @sff IntStrfbm
 * @sff LongStrfbm
 * @sff DoublfStrfbm
 * @sff <b irff="pbdkbgf-summbry.itml">jbvb.util.strfbm</b>
 */
publid intfrfbdf BbsfStrfbm<T, S fxtfnds BbsfStrfbm<T, S>>
        fxtfnds AutoClosfbblf {
    /**
     * Rfturns bn itfrbtor for tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn tif flfmfnt itfrbtor for tiis strfbm
     */
    Itfrbtor<T> itfrbtor();

    /**
     * Rfturns b splitfrbtor for tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn tif flfmfnt splitfrbtor for tiis strfbm
     */
    Splitfrbtor<T> splitfrbtor();

    /**
     * Rfturns wiftifr tiis strfbm, if b tfrminbl opfrbtion wfrf to bf fxfdutfd,
     * would fxfdutf in pbrbllfl.  Cblling tiis mftiod bftfr invoking bn
     * tfrminbl strfbm opfrbtion mftiod mby yifld unprfdidtbblf rfsults.
     *
     * @rfturn {@dodf truf} if tiis strfbm would fxfdutf in pbrbllfl if fxfdutfd
     */
    boolfbn isPbrbllfl();

    /**
     * Rfturns bn fquivblfnt strfbm tibt is sfqufntibl.  Mby rfturn
     * itsflf, fitifr bfdbusf tif strfbm wbs blrfbdy sfqufntibl, or bfdbusf
     * tif undfrlying strfbm stbtf wbs modififd to bf sfqufntibl.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @rfturn b sfqufntibl strfbm
     */
    S sfqufntibl();

    /**
     * Rfturns bn fquivblfnt strfbm tibt is pbrbllfl.  Mby rfturn
     * itsflf, fitifr bfdbusf tif strfbm wbs blrfbdy pbrbllfl, or bfdbusf
     * tif undfrlying strfbm stbtf wbs modififd to bf pbrbllfl.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @rfturn b pbrbllfl strfbm
     */
    S pbrbllfl();

    /**
     * Rfturns bn fquivblfnt strfbm tibt is
     * <b irff="pbdkbgf-summbry.itml#Ordfring">unordfrfd</b>.  Mby rfturn
     * itsflf, fitifr bfdbusf tif strfbm wbs blrfbdy unordfrfd, or bfdbusf
     * tif undfrlying strfbm stbtf wbs modififd to bf unordfrfd.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @rfturn bn unordfrfd strfbm
     */
    S unordfrfd();

    /**
     * Rfturns bn fquivblfnt strfbm witi bn bdditionbl dlosf ibndlfr.  Closf
     * ibndlfrs brf run wifn tif {@link #dlosf()} mftiod
     * is dbllfd on tif strfbm, bnd brf fxfdutfd in tif ordfr tify wfrf
     * bddfd.  All dlosf ibndlfrs brf run, fvfn if fbrlifr dlosf ibndlfrs tirow
     * fxdfptions.  If bny dlosf ibndlfr tirows bn fxdfption, tif first
     * fxdfption tirown will bf rflbyfd to tif dbllfr of {@dodf dlosf()}, witi
     * bny rfmbining fxdfptions bddfd to tibt fxdfption bs supprfssfd fxdfptions
     * (unlfss onf of tif rfmbining fxdfptions is tif sbmf fxdfption bs tif
     * first fxdfption, sindf bn fxdfption dbnnot supprfss itsflf.)  Mby
     * rfturn itsflf.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm dlosfHbndlfr A tbsk to fxfdutf wifn tif strfbm is dlosfd
     * @rfturn b strfbm witi b ibndlfr tibt is run if tif strfbm is dlosfd
     */
    S onClosf(Runnbblf dlosfHbndlfr);

    /**
     * Closfs tiis strfbm, dbusing bll dlosf ibndlfrs for tiis strfbm pipflinf
     * to bf dbllfd.
     *
     * @sff AutoClosfbblf#dlosf()
     */
    @Ovfrridf
    void dlosf();
}
