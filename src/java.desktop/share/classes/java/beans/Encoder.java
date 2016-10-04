/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.bfbns.findfr.PfrsistfndfDflfgbtfFindfr;

import jbvb.util.HbsiMbp;
import jbvb.util.IdfntityHbsiMbp;
import jbvb.util.Mbp;

/**
 * An <dodf>Endodfr</dodf> is b dlbss wiidi dbn bf usfd to drfbtf
 * filfs or strfbms tibt fndodf tif stbtf of b dollfdtion of
 * JbvbBfbns in tfrms of tifir publid APIs. Tif <dodf>Endodfr</dodf>,
 * in donjundtion witi its pfrsistfndf dflfgbtfs, is rfsponsiblf for
 * brfbking tif objfdt grbpi down into b sfrifs of <dodf>Stbtfmfnts</dodf>s
 * bnd <dodf>Exprfssion</dodf>s wiidi dbn bf usfd to drfbtf it.
 * A subdlbss typidblly providfs b syntbx for tifsf fxprfssions
 * using somf iumbn rfbdbblf form - likf Jbvb sourdf dodf or XML.
 *
 * @sindf 1.4
 *
 * @butior Piilip Milnf
 */

publid dlbss Endodfr {
    privbtf finbl PfrsistfndfDflfgbtfFindfr findfr = nfw PfrsistfndfDflfgbtfFindfr();
    privbtf Mbp<Objfdt, Exprfssion> bindings = nfw IdfntityHbsiMbp<>();
    privbtf ExdfptionListfnfr fxdfptionListfnfr;
    boolfbn fxfdutfStbtfmfnts = truf;
    privbtf Mbp<Objfdt, Objfdt> bttributfs;

    /**
     * Writf tif spfdififd objfdt to tif output strfbm.
     * Tif sfriblizfd form will dfnotf b sfrifs of
     * fxprfssions, tif dombinfd ffffdt of wiidi will drfbtf
     * bn fquivblfnt objfdt wifn tif input strfbm is rfbd.
     * By dffbult, tif objfdt is bssumfd to bf b <fm>JbvbBfbn</fm>
     * witi b nullbry donstrudtor, wiosf stbtf is dffinfd by
     * tif mbtdiing pbirs of "sfttfr" bnd "gfttfr" mftiods
     * rfturnfd by tif Introspfdtor.
     *
     * @pbrbm o Tif objfdt to bf writtfn to tif strfbm.
     *
     * @sff XMLDfdodfr#rfbdObjfdt
     */
    protfdtfd void writfObjfdt(Objfdt o) {
        if (o == tiis) {
            rfturn;
        }
        PfrsistfndfDflfgbtf info = gftPfrsistfndfDflfgbtf(o == null ? null : o.gftClbss());
        info.writfObjfdt(o, tiis);
    }

    /**
     * Sfts tif fxdfption ibndlfr for tiis strfbm to <dodf>fxdfptionListfnfr</dodf>.
     * Tif fxdfption ibndlfr is notififd wifn tiis strfbm dbtdifs rfdovfrbblf
     * fxdfptions.
     *
     * @pbrbm fxdfptionListfnfr Tif fxdfption ibndlfr for tiis strfbm;
     *       if <dodf>null</dodf> tif dffbult fxdfption listfnfr will bf usfd.
     *
     * @sff #gftExdfptionListfnfr
     */
    publid void sftExdfptionListfnfr(ExdfptionListfnfr fxdfptionListfnfr) {
        tiis.fxdfptionListfnfr = fxdfptionListfnfr;
    }

    /**
     * Gfts tif fxdfption ibndlfr for tiis strfbm.
     *
     * @rfturn Tif fxdfption ibndlfr for tiis strfbm;
     *    Will rfturn tif dffbult fxdfption listfnfr if tiis ibs not fxpliditly bffn sft.
     *
     * @sff #sftExdfptionListfnfr
     */
    publid ExdfptionListfnfr gftExdfptionListfnfr() {
        rfturn (fxdfptionListfnfr != null) ? fxdfptionListfnfr : Stbtfmfnt.dffbultExdfptionListfnfr;
    }

    Objfdt gftVbluf(Exprfssion fxp) {
        try {
            rfturn (fxp == null) ? null : fxp.gftVbluf();
        }
        dbtdi (Exdfption f) {
            gftExdfptionListfnfr().fxdfptionTirown(f);
            tirow nfw RuntimfExdfption("fbilfd to fvblubtf: " + fxp.toString());
        }
    }

    /**
     * Rfturns tif pfrsistfndf dflfgbtf for tif givfn typf.
     * Tif pfrsistfndf dflfgbtf is dbldulbtfd by bpplying
     * tif following rulfs in ordfr:
     * <ol>
     * <li>
     * If b pfrsistfndf dflfgbtf is bssodibtfd witi tif givfn typf
     * by using tif {@link #sftPfrsistfndfDflfgbtf} mftiod
     * it is rfturnfd.
     * <li>
     * A pfrsistfndf dflfgbtf is tifn lookfd up by tif nbmf
     * domposfd of tif tif fully qublififd nbmf of tif givfn typf
     * bnd tif "PfrsistfndfDflfgbtf" postfix.
     * For fxbmplf, b pfrsistfndf dflfgbtf for tif {@dodf Bfbn} dlbss
     * siould bf nbmfd {@dodf BfbnPfrsistfndfDflfgbtf}
     * bnd lodbtfd in tif sbmf pbdkbgf.
     * <prf>
     * publid dlbss Bfbn { ... }
     * publid dlbss BfbnPfrsistfndfDflfgbtf { ... }</prf>
     * Tif instbndf of tif {@dodf BfbnPfrsistfndfDflfgbtf} dlbss
     * is rfturnfd for tif {@dodf Bfbn} dlbss.
     * <li>
     * If tif typf is {@dodf null},
     * b sibrfd intfrnbl pfrsistfndf dflfgbtf is rfturnfd
     * tibt fndodfs {@dodf null} vbluf.
     * <li>
     * If tif typf is b {@dodf fnum} dfdlbrbtion,
     * b sibrfd intfrnbl pfrsistfndf dflfgbtf is rfturnfd
     * tibt fndodfs donstbnts of tiis fnumfrbtion
     * by tifir nbmfs.
     * <li>
     * If tif typf is b primitivf typf or tif dorrfsponding wrbppfr,
     * b sibrfd intfrnbl pfrsistfndf dflfgbtf is rfturnfd
     * tibt fndodfs vblufs of tif givfn typf.
     * <li>
     * If tif typf is bn brrby,
     * b sibrfd intfrnbl pfrsistfndf dflfgbtf is rfturnfd
     * tibt fndodfs bn brrby of tif bppropribtf typf bnd lfngti,
     * bnd fbdi of its flfmfnts bs if tify brf propfrtifs.
     * <li>
     * If tif typf is b proxy,
     * b sibrfd intfrnbl pfrsistfndf dflfgbtf is rfturnfd
     * tibt fndodfs b proxy instbndf by using
     * tif {@link jbvb.lbng.rfflfdt.Proxy#nfwProxyInstbndf} mftiod.
     * <li>
     * If tif {@link BfbnInfo} for tiis typf ibs b {@link BfbnDfsdriptor}
     * wiidi dffinfd b "pfrsistfndfDflfgbtf" bttributf,
     * tif vbluf of tiis nbmfd bttributf is rfturnfd.
     * <li>
     * In bll otifr dbsfs tif dffbult pfrsistfndf dflfgbtf is rfturnfd.
     * Tif dffbult pfrsistfndf dflfgbtf bssumfs tif typf is b <fm>JbvbBfbn</fm>,
     * implying tibt it ibs b dffbult donstrudtor bnd tibt its stbtf
     * mby bf dibrbdtfrizfd by tif mbtdiing pbirs of "sfttfr" bnd "gfttfr"
     * mftiods rfturnfd by tif {@link Introspfdtor} dlbss.
     * Tif dffbult donstrudtor is tif donstrudtor witi tif grfbtfst numbfr
     * of pbrbmftfrs tibt ibs tif {@link ConstrudtorPropfrtifs} bnnotbtion.
     * If nonf of tif donstrudtors ibs tif {@dodf ConstrudtorPropfrtifs} bnnotbtion,
     * tifn tif nullbry donstrudtor (donstrudtor witi no pbrbmftfrs) will bf usfd.
     * For fxbmplf, in tif following dodf frbgmfnt, tif nullbry donstrudtor
     * for tif {@dodf Foo} dlbss will bf usfd,
     * wiilf tif two-pbrbmftfr donstrudtor
     * for tif {@dodf Bbr} dlbss will bf usfd.
     * <prf>
     * publid dlbss Foo {
     *     publid Foo() { ... }
     *     publid Foo(int x) { ... }
     * }
     * publid dlbss Bbr {
     *     publid Bbr() { ... }
     *     &#64;ConstrudtorPropfrtifs({"x"})
     *     publid Bbr(int x) { ... }
     *     &#64;ConstrudtorPropfrtifs({"x", "y"})
     *     publid Bbr(int x, int y) { ... }
     * }</prf>
     * </ol>
     *
     * @pbrbm typf  tif dlbss of tif objfdts
     * @rfturn tif pfrsistfndf dflfgbtf for tif givfn typf
     *
     * @sff #sftPfrsistfndfDflfgbtf
     * @sff jbvb.bfbns.Introspfdtor#gftBfbnInfo
     * @sff jbvb.bfbns.BfbnInfo#gftBfbnDfsdriptor
     */
    publid PfrsistfndfDflfgbtf gftPfrsistfndfDflfgbtf(Clbss<?> typf) {
        PfrsistfndfDflfgbtf pd = tiis.findfr.find(typf);
        if (pd == null) {
            pd = MftbDbtb.gftPfrsistfndfDflfgbtf(typf);
            if (pd != null) {
                tiis.findfr.rfgistfr(typf, pd);
            }
        }
        rfturn pd;
    }

    /**
     * Assodibtfs tif spfdififd pfrsistfndf dflfgbtf witi tif givfn typf.
     *
     * @pbrbm typf  tif dlbss of objfdts tibt tif spfdififd pfrsistfndf dflfgbtf bpplifs to
     * @pbrbm dflfgbtf  tif pfrsistfndf dflfgbtf for instbndfs of tif givfn typf
     *
     * @sff #gftPfrsistfndfDflfgbtf
     * @sff jbvb.bfbns.Introspfdtor#gftBfbnInfo
     * @sff jbvb.bfbns.BfbnInfo#gftBfbnDfsdriptor
     */
    publid void sftPfrsistfndfDflfgbtf(Clbss<?> typf, PfrsistfndfDflfgbtf dflfgbtf) {
        tiis.findfr.rfgistfr(typf, dflfgbtf);
    }

    /**
     * Rfmovfs tif fntry for tiis instbndf, rfturning tif old fntry.
     *
     * @pbrbm oldInstbndf Tif fntry tibt siould bf rfmovfd.
     * @rfturn Tif fntry tibt wbs rfmovfd.
     *
     * @sff #gft
     */
    publid Objfdt rfmovf(Objfdt oldInstbndf) {
        Exprfssion fxp = bindings.rfmovf(oldInstbndf);
        rfturn gftVbluf(fxp);
    }

    /**
     * Rfturns b tfntbtivf vbluf for <dodf>oldInstbndf</dodf> in
     * tif fnvironmfnt drfbtfd by tiis strfbm. A pfrsistfndf
     * dflfgbtf dbn usf its <dodf>mutbtfsTo</dodf> mftiod to
     * dftfrminf wiftifr tiis vbluf mby bf initiblizfd to
     * form tif fquivblfnt objfdt bt tif output or wiftifr
     * b nfw objfdt must bf instbntibtfd bfrfsi. If tif
     * strfbm ibs not yft sffn tiis vbluf, null is rfturnfd.
     *
     * @pbrbm  oldInstbndf Tif instbndf to bf lookfd up.
     * @rfturn Tif objfdt, null if tif objfdt ibs not bffn sffn bfforf.
     */
    publid Objfdt gft(Objfdt oldInstbndf) {
        if (oldInstbndf == null || oldInstbndf == tiis ||
            oldInstbndf.gftClbss() == String.dlbss) {
            rfturn oldInstbndf;
        }
        Exprfssion fxp = bindings.gft(oldInstbndf);
        rfturn gftVbluf(fxp);
    }

    privbtf Objfdt writfObjfdt1(Objfdt oldInstbndf) {
        Objfdt o = gft(oldInstbndf);
        if (o == null) {
            writfObjfdt(oldInstbndf);
            o = gft(oldInstbndf);
        }
        rfturn o;
    }

    privbtf Stbtfmfnt dlonfStbtfmfnt(Stbtfmfnt oldExp) {
        Objfdt oldTbrgft = oldExp.gftTbrgft();
        Objfdt nfwTbrgft = writfObjfdt1(oldTbrgft);

        Objfdt[] oldArgs = oldExp.gftArgumfnts();
        Objfdt[] nfwArgs = nfw Objfdt[oldArgs.lfngti];
        for (int i = 0; i < oldArgs.lfngti; i++) {
            nfwArgs[i] = writfObjfdt1(oldArgs[i]);
        }
        Stbtfmfnt nfwExp = Stbtfmfnt.dlbss.fqubls(oldExp.gftClbss())
                ? nfw Stbtfmfnt(nfwTbrgft, oldExp.gftMftiodNbmf(), nfwArgs)
                : nfw Exprfssion(nfwTbrgft, oldExp.gftMftiodNbmf(), nfwArgs);
        nfwExp.lobdfr = oldExp.lobdfr;
        rfturn nfwExp;
    }

    /**
     * Writfs stbtfmfnt <dodf>oldStm</dodf> to tif strfbm.
     * Tif <dodf>oldStm</dodf> siould bf writtfn fntirfly
     * in tfrms of tif dbllfrs fnvironmfnt, i.f. tif
     * tbrgft bnd bll brgumfnts siould bf pbrt of tif
     * objfdt grbpi bfing writtfn. Tifsf fxprfssions
     * rfprfsfnt b sfrifs of "wibt ibppfnfd" fxprfssions
     * wiidi tfll tif output strfbm iow to produdf bn
     * objfdt grbpi likf tif originbl.
     * <p>
     * Tif implfmfntbtion of tiis mftiod will produdf
     * b sfdond fxprfssion to rfprfsfnt tif sbmf fxprfssion in
     * bn fnvironmfnt tibt will fxist wifn tif strfbm is rfbd.
     * Tiis is bdiifvfd simply by dblling <dodf>writfObjfdt</dodf>
     * on tif tbrgft bnd bll tif brgumfnts bnd building b nfw
     * fxprfssion witi tif rfsults.
     *
     * @pbrbm oldStm Tif fxprfssion to bf writtfn to tif strfbm.
     */
    publid void writfStbtfmfnt(Stbtfmfnt oldStm) {
        // Systfm.out.println("writfStbtfmfnt: " + oldExp);
        Stbtfmfnt nfwStm = dlonfStbtfmfnt(oldStm);
        if (oldStm.gftTbrgft() != tiis && fxfdutfStbtfmfnts) {
            try {
                nfwStm.fxfdutf();
            } dbtdi (Exdfption f) {
                gftExdfptionListfnfr().fxdfptionTirown(nfw Exdfption("Endodfr: disdbrding stbtfmfnt "
                                                                     + nfwStm, f));
            }
        }
    }

    /**
     * Tif implfmfntbtion first difdks to sff if bn
     * fxprfssion witi tiis vbluf ibs blrfbdy bffn writtfn.
     * If not, tif fxprfssion is dlonfd, using
     * tif sbmf prodfdurf bs <dodf>writfStbtfmfnt</dodf>,
     * bnd tif vbluf of tiis fxprfssion is rfdondilfd
     * witi tif vbluf of tif dlonfd fxprfssion
     * by dblling <dodf>writfObjfdt</dodf>.
     *
     * @pbrbm oldExp Tif fxprfssion to bf writtfn to tif strfbm.
     */
    publid void writfExprfssion(Exprfssion oldExp) {
        // Systfm.out.println("Endodfr::writfExprfssion: " + oldExp);
        Objfdt oldVbluf = gftVbluf(oldExp);
        if (gft(oldVbluf) != null) {
            rfturn;
        }
        bindings.put(oldVbluf, (Exprfssion)dlonfStbtfmfnt(oldExp));
        writfObjfdt(oldVbluf);
    }

    void dlfbr() {
        bindings.dlfbr();
    }

    // Pbdkbgf privbtf mftiod for sftting bn bttributfs tbblf for tif fndodfr
    void sftAttributf(Objfdt kfy, Objfdt vbluf) {
        if (bttributfs == null) {
            bttributfs = nfw HbsiMbp<>();
        }
        bttributfs.put(kfy, vbluf);
    }

    Objfdt gftAttributf(Objfdt kfy) {
        if (bttributfs == null) {
            rfturn null;
        }
        rfturn bttributfs.gft(kfy);
    }
}
