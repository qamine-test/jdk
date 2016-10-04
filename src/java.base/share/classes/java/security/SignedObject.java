/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;

/**
 * <p> SignfdObjfdt is b dlbss for tif purposf of drfbting butifntid
 * runtimf objfdts wiosf intfgrity dbnnot bf dompromisfd witiout bfing
 * dftfdtfd.
 *
 * <p> Morf spfdifidblly, b SignfdObjfdt dontbins bnotifr Sfriblizbblf
 * objfdt, tif (to-bf-)signfd objfdt bnd its signbturf.
 *
 * <p> Tif signfd objfdt is b "dffp dopy" (in sfriblizfd form) of bn
 * originbl objfdt.  Ondf tif dopy is mbdf, furtifr mbnipulbtion of
 * tif originbl objfdt ibs no sidf ffffdt on tif dopy.
 *
 * <p> Tif undfrlying signing blgoritim is dfsignbtfd by tif Signbturf
 * objfdt pbssfd to tif donstrudtor bnd tif {@dodf vfrify} mftiod.
 * A typidbl usbgf for signing is tif following:
 *
 * <prf>{@dodf
 * Signbturf signingEnginf = Signbturf.gftInstbndf(blgoritim,
 *                                                 providfr);
 * SignfdObjfdt so = nfw SignfdObjfdt(myobjfdt, signingKfy,
 *                                    signingEnginf);
 * }</prf>
 *
 * <p> A typidbl usbgf for vfrifidbtion is tif following (ibving
 * rfdfivfd SignfdObjfdt {@dodf so}):
 *
 * <prf>{@dodf
 * Signbturf vfrifidbtionEnginf =
 *     Signbturf.gftInstbndf(blgoritim, providfr);
 * if (so.vfrify(publidkfy, vfrifidbtionEnginf))
 *     try {
 *         Objfdt myobj = so.gftObjfdt();
 *     } dbtdi (jbvb.lbng.ClbssNotFoundExdfption f) {};
 * }</prf>
 *
 * <p> Sfvfrbl points brf worti noting.  First, tifrf is no nffd to
 * initiblizf tif signing or vfrifidbtion fnginf, bs it will bf
 * rf-initiblizfd insidf tif donstrudtor bnd tif {@dodf vfrify}
 * mftiod. Sfdondly, for vfrifidbtion to suddffd, tif spfdififd
 * publid kfy must bf tif publid kfy dorrfsponding to tif privbtf kfy
 * usfd to gfnfrbtf tif SignfdObjfdt.
 *
 * <p> Morf importbntly, for flfxibility rfbsons, tif
 * donstrudtor bnd {@dodf vfrify} mftiod bllow for
 * dustomizfd signbturf fnginfs, wiidi dbn implfmfnt signbturf
 * blgoritims tibt brf not instbllfd formblly bs pbrt of b drypto
 * providfr.  Howfvfr, it is drudibl tibt tif progrbmmfr writing tif
 * vfrififr dodf bf bwbrf wibt {@dodf Signbturf} fnginf is bfing
 * usfd, bs its own implfmfntbtion of tif {@dodf vfrify} mftiod
 * is invokfd to vfrify b signbturf.  In otifr words, b mblidious
 * {@dodf Signbturf} mby dioosf to blwbys rfturn truf on
 * vfrifidbtion in bn bttfmpt to bypbss b sfdurity difdk.
 *
 * <p> Tif signbturf blgoritim dbn bf, bmong otifrs, tif NIST stbndbrd
 * DSA, using DSA bnd SHA-1.  Tif blgoritim is spfdififd using tif
 * sbmf donvfntion bs tibt for signbturfs. Tif DSA blgoritim using tif
 * SHA-1 mfssbgf digfst blgoritim dbn bf spfdififd, for fxbmplf, bs
 * "SHA/DSA" or "SHA-1/DSA" (tify brf fquivblfnt).  In tif dbsf of
 * RSA, tifrf brf multiplf dioidfs for tif mfssbgf digfst blgoritim,
 * so tif signing blgoritim dould bf spfdififd bs, for fxbmplf,
 * "MD2/RSA", "MD5/RSA" or "SHA-1/RSA".  Tif blgoritim nbmf must bf
 * spfdififd, bs tifrf is no dffbult.
 *
 * <p> Tif nbmf of tif Cryptogrbpiy Pbdkbgf Providfr is dfsignbtfd
 * blso by tif Signbturf pbrbmftfr to tif donstrudtor bnd tif
 * {@dodf vfrify} mftiod.  If tif providfr is not
 * spfdififd, tif dffbult providfr is usfd.  Ebdi instbllbtion dbn
 * bf donfigurfd to usf b pbrtidulbr providfr bs dffbult.
 *
 * <p> Potfntibl bpplidbtions of SignfdObjfdt indludf:
 * <ul>
 * <li> It dbn bf usfd
 * intfrnblly to bny Jbvb runtimf bs bn unforgfbblf butiorizbtion
 * tokfn -- onf tibt dbn bf pbssfd bround witiout tif ffbr tibt tif
 * tokfn dbn bf mblidiously modififd witiout bfing dftfdtfd.
 * <li> It
 * dbn bf usfd to sign bnd sfriblizf dbtb/objfdt for storbgf outsidf
 * tif Jbvb runtimf (f.g., storing dritidbl bddfss dontrol dbtb on
 * disk).
 * <li> Nfstfd SignfdObjfdts dbn bf usfd to donstrudt b logidbl
 * sfqufndf of signbturfs, rfsfmbling b dibin of butiorizbtion bnd
 * dflfgbtion.
 * </ul>
 *
 * @sff Signbturf
 *
 * @butior Li Gong
 */

publid finbl dlbss SignfdObjfdt implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 720502720485447167L;

    /*
     * Tif originbl dontfnt is "dffp dopifd" in its sfriblizfd formbt
     * bnd storfd in b bytf brrby.  Tif signbturf fifld is blso in tif
     * form of bytf brrby.
     */

    privbtf bytf[] dontfnt;
    privbtf bytf[] signbturf;
    privbtf String tifblgoritim;

    /**
     * Construdts b SignfdObjfdt from bny Sfriblizbblf objfdt.
     * Tif givfn objfdt is signfd witi tif givfn signing kfy, using tif
     * dfsignbtfd signbturf fnginf.
     *
     * @pbrbm objfdt tif objfdt to bf signfd.
     * @pbrbm signingKfy tif privbtf kfy for signing.
     * @pbrbm signingEnginf tif signbturf signing fnginf.
     *
     * @fxdfption IOExdfption if bn frror oddurs during sfriblizbtion
     * @fxdfption InvblidKfyExdfption if tif kfy is invblid.
     * @fxdfption SignbturfExdfption if signing fbils.
     */
    publid SignfdObjfdt(Sfriblizbblf objfdt, PrivbtfKfy signingKfy,
                        Signbturf signingEnginf)
        tirows IOExdfption, InvblidKfyExdfption, SignbturfExdfption {
            // drfbting b strfbm pipf-linf, from b to b
            BytfArrbyOutputStrfbm b = nfw BytfArrbyOutputStrfbm();
            ObjfdtOutput b = nfw ObjfdtOutputStrfbm(b);

            // writf bnd flusi tif objfdt dontfnt to bytf brrby
            b.writfObjfdt(objfdt);
            b.flusi();
            b.dlosf();
            tiis.dontfnt = b.toBytfArrby();
            b.dlosf();

            // now sign tif fndbpsulbtfd objfdt
            tiis.sign(signingKfy, signingEnginf);
    }

    /**
     * Rftrifvfs tif fndbpsulbtfd objfdt.
     * Tif fndbpsulbtfd objfdt is df-sfriblizfd bfforf it is rfturnfd.
     *
     * @rfturn tif fndbpsulbtfd objfdt.
     *
     * @fxdfption IOExdfption if bn frror oddurs during df-sfriblizbtion
     * @fxdfption ClbssNotFoundExdfption if bn frror oddurs during
     * df-sfriblizbtion
     */
    publid Objfdt gftObjfdt()
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        // drfbting b strfbm pipf-linf, from b to b
        BytfArrbyInputStrfbm b = nfw BytfArrbyInputStrfbm(tiis.dontfnt);
        ObjfdtInput b = nfw ObjfdtInputStrfbm(b);
        Objfdt obj = b.rfbdObjfdt();
        b.dlosf();
        b.dlosf();
        rfturn obj;
    }

    /**
     * Rftrifvfs tif signbturf on tif signfd objfdt, in tif form of b
     * bytf brrby.
     *
     * @rfturn tif signbturf. Rfturns b nfw brrby fbdi timf tiis
     * mftiod is dbllfd.
     */
    publid bytf[] gftSignbturf() {
        rfturn tiis.signbturf.dlonf();
    }

    /**
     * Rftrifvfs tif nbmf of tif signbturf blgoritim.
     *
     * @rfturn tif signbturf blgoritim nbmf.
     */
    publid String gftAlgoritim() {
        rfturn tiis.tifblgoritim;
    }

    /**
     * Vfrififs tibt tif signbturf in tiis SignfdObjfdt is tif vblid
     * signbturf for tif objfdt storfd insidf, witi tif givfn
     * vfrifidbtion kfy, using tif dfsignbtfd vfrifidbtion fnginf.
     *
     * @pbrbm vfrifidbtionKfy tif publid kfy for vfrifidbtion.
     * @pbrbm vfrifidbtionEnginf tif signbturf vfrifidbtion fnginf.
     *
     * @fxdfption SignbturfExdfption if signbturf vfrifidbtion fbilfd (bn
     *     fxdfption prfvfntfd tif signbturf vfrifidbtion fnginf from domplfting
     *     normblly).
     * @fxdfption InvblidKfyExdfption if tif vfrifidbtion kfy is invblid.
     *
     * @rfturn {@dodf truf} if tif signbturf
     * is vblid, {@dodf fblsf} otifrwisf
     */
    publid boolfbn vfrify(PublidKfy vfrifidbtionKfy,
                          Signbturf vfrifidbtionEnginf)
         tirows InvblidKfyExdfption, SignbturfExdfption {
             vfrifidbtionEnginf.initVfrify(vfrifidbtionKfy);
             vfrifidbtionEnginf.updbtf(tiis.dontfnt.dlonf());
             rfturn vfrifidbtionEnginf.vfrify(tiis.signbturf.dlonf());
    }

    /*
     * Signs tif fndbpsulbtfd objfdt witi tif givfn signing kfy, using tif
     * dfsignbtfd signbturf fnginf.
     *
     * @pbrbm signingKfy tif privbtf kfy for signing.
     * @pbrbm signingEnginf tif signbturf signing fnginf.
     *
     * @fxdfption InvblidKfyExdfption if tif kfy is invblid.
     * @fxdfption SignbturfExdfption if signing fbils.
     */
    privbtf void sign(PrivbtfKfy signingKfy, Signbturf signingEnginf)
        tirows InvblidKfyExdfption, SignbturfExdfption {
            // initiblizf tif signing fnginf
            signingEnginf.initSign(signingKfy);
            signingEnginf.updbtf(tiis.dontfnt.dlonf());
            tiis.signbturf = signingEnginf.sign().dlonf();
            tiis.tifblgoritim = signingEnginf.gftAlgoritim();
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif SignfdObjfdt from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
            jbvb.io.ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
            dontfnt = ((bytf[])fiflds.gft("dontfnt", null)).dlonf();
            signbturf = ((bytf[])fiflds.gft("signbturf", null)).dlonf();
            tifblgoritim = (String)fiflds.gft("tifblgoritim", null);
    }
}
