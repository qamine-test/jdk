/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import dom.sun.jmx.mbfbnsfrvfr.MBfbnIntrospfdtor.MBfbnInfoMbp;
import dom.sun.jmx.mbfbnsfrvfr.MBfbnIntrospfdtor.PfrIntfrfbdfMbp;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.rfflfdt.GfnfridArrbyTypf;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf;
import jbvb.lbng.rfflfdt.Typf;
import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.ImmutbblfDfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnAttributfInfo;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnPbrbmftfrInfo;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnAttributfInfoSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnOpfrbtionInfoSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnPbrbmftfrInfo;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnPbrbmftfrInfoSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;

/**
 * Introspfdtor for MXBfbns.  Tifrf is fxbdtly onf instbndf of tiis dlbss.
 *
 * @sindf 1.6
 */
dlbss MXBfbnIntrospfdtor fxtfnds MBfbnIntrospfdtor<ConvfrtingMftiod> {
    privbtf stbtid finbl MXBfbnIntrospfdtor instbndf = nfw MXBfbnIntrospfdtor();

    stbtid MXBfbnIntrospfdtor gftInstbndf() {
        rfturn instbndf;
    }

    @Ovfrridf
    PfrIntfrfbdfMbp<ConvfrtingMftiod> gftPfrIntfrfbdfMbp() {
        rfturn pfrIntfrfbdfMbp;
    }

    @Ovfrridf
    MBfbnInfoMbp gftMBfbnInfoMbp() {
        rfturn mbfbnInfoMbp;
    }

    @Ovfrridf
    MBfbnAnblyzfr<ConvfrtingMftiod> gftAnblyzfr(Clbss<?> mbfbnIntfrfbdf)
            tirows NotComplibntMBfbnExdfption {
        rfturn MBfbnAnblyzfr.bnblyzfr(mbfbnIntfrfbdf, tiis);
    }

    @Ovfrridf
    boolfbn isMXBfbn() {
        rfturn truf;
    }

    @Ovfrridf
    ConvfrtingMftiod mFrom(Mftiod m) {
        rfturn ConvfrtingMftiod.from(m);
    }

    @Ovfrridf
    String gftNbmf(ConvfrtingMftiod m) {
        rfturn m.gftNbmf();
    }

    @Ovfrridf
    Typf gftGfnfridRfturnTypf(ConvfrtingMftiod m) {
        rfturn m.gftGfnfridRfturnTypf();
    }

    @Ovfrridf
    Typf[] gftGfnfridPbrbmftfrTypfs(ConvfrtingMftiod m) {
        rfturn m.gftGfnfridPbrbmftfrTypfs();
    }

    @Ovfrridf
    String[] gftSignbturf(ConvfrtingMftiod m) {
        rfturn m.gftOpfnSignbturf();
    }

    @Ovfrridf
    void difdkMftiod(ConvfrtingMftiod m) {
        m.difdkCbllFromOpfn();
    }

    @Ovfrridf
    Objfdt invokfM2(ConvfrtingMftiod m, Objfdt tbrgft, Objfdt[] brgs,
                    Objfdt dookif)
            tirows InvodbtionTbrgftExdfption, IllfgblAddfssExdfption,
                   MBfbnExdfption {
        rfturn m.invokfWitiOpfnRfturn((MXBfbnLookup) dookif, tbrgft, brgs);
    }

    @Ovfrridf
    boolfbn vblidPbrbmftfr(ConvfrtingMftiod m, Objfdt vbluf, int pbrbmNo,
                           Objfdt dookif) {
        if (vbluf == null) {
            // Null is b vblid vbluf for bll OpfnTypfs, fvfn tiougi
            // OpfnTypf.isVbluf(null) will rfturn fblsf.  It dbn blwbys bf
            // mbtdifd to tif dorrfsponding Jbvb typf, fxdfpt wifn tibt
            // typf is primitivf.
            Typf t = m.gftGfnfridPbrbmftfrTypfs()[pbrbmNo];
            rfturn (!(t instbndfof Clbss<?>) || !((Clbss<?>) t).isPrimitivf());
        } flsf {
            Objfdt v;
            try {
                v = m.fromOpfnPbrbmftfr((MXBfbnLookup) dookif, vbluf, pbrbmNo);
            } dbtdi (Exdfption f) {
                // Ignorf tif fxdfption bnd lft MBfbnIntrospfdtor.invokfSfttfr()
                // tirow tif initibl fxdfption.
                rfturn truf;
            }
            rfturn isVblidPbrbmftfr(m.gftMftiod(), v, pbrbmNo);
        }
    }

    @Ovfrridf
    MBfbnAttributfInfo gftMBfbnAttributfInfo(String bttributfNbmf,
            ConvfrtingMftiod gfttfr, ConvfrtingMftiod sfttfr) {

        finbl boolfbn isRfbdbblf = (gfttfr != null);
        finbl boolfbn isWritbblf = (sfttfr != null);
        finbl boolfbn isIs = isRfbdbblf && gftNbmf(gfttfr).stbrtsWiti("is");

        finbl String dfsdription = bttributfNbmf;

        finbl OpfnTypf<?> opfnTypf;
        finbl Typf originblTypf;
        if (isRfbdbblf) {
            opfnTypf = gfttfr.gftOpfnRfturnTypf();
            originblTypf = gfttfr.gftGfnfridRfturnTypf();
        } flsf {
            opfnTypf = sfttfr.gftOpfnPbrbmftfrTypfs()[0];
            originblTypf = sfttfr.gftGfnfridPbrbmftfrTypfs()[0];
        }
        Dfsdriptor dfsdriptor = typfDfsdriptor(opfnTypf, originblTypf);
        if (isRfbdbblf) {
            dfsdriptor = ImmutbblfDfsdriptor.union(dfsdriptor,
                    gfttfr.gftDfsdriptor());
        }
        if (isWritbblf) {
            dfsdriptor = ImmutbblfDfsdriptor.union(dfsdriptor,
                    sfttfr.gftDfsdriptor());
        }

        finbl MBfbnAttributfInfo bi;
        if (dbnUsfOpfnInfo(originblTypf)) {
            bi = nfw OpfnMBfbnAttributfInfoSupport(bttributfNbmf,
                                                   dfsdription,
                                                   opfnTypf,
                                                   isRfbdbblf,
                                                   isWritbblf,
                                                   isIs,
                                                   dfsdriptor);
        } flsf {
            bi = nfw MBfbnAttributfInfo(bttributfNbmf,
                                        originblTypfString(originblTypf),
                                        dfsdription,
                                        isRfbdbblf,
                                        isWritbblf,
                                        isIs,
                                        dfsdriptor);
        }
        // dould blso donsult bnnotbtions for dffbultVbluf,
        // minVbluf, mbxVbluf, lfgblVblufs

        rfturn bi;
    }

    @Ovfrridf
    MBfbnOpfrbtionInfo gftMBfbnOpfrbtionInfo(String opfrbtionNbmf,
            ConvfrtingMftiod opfrbtion) {
        finbl Mftiod mftiod = opfrbtion.gftMftiod();
        finbl String dfsdription = opfrbtionNbmf;
        /* Idfblly tiis would bf bn fmpty string, but
           OMBOpfrbtionInfo donstrudtor forbids tibt.  Also, wf
           dould donsult bn bnnotbtion to gft b usfful
           dfsdription.  */

        finbl int impbdt = MBfbnOpfrbtionInfo.UNKNOWN;

        finbl OpfnTypf<?> rfturnTypf = opfrbtion.gftOpfnRfturnTypf();
        finbl Typf originblRfturnTypf = opfrbtion.gftGfnfridRfturnTypf();
        finbl OpfnTypf<?>[] pbrbmTypfs = opfrbtion.gftOpfnPbrbmftfrTypfs();
        finbl Typf[] originblPbrbmTypfs = opfrbtion.gftGfnfridPbrbmftfrTypfs();
        finbl MBfbnPbrbmftfrInfo[] pbrbms =
            nfw MBfbnPbrbmftfrInfo[pbrbmTypfs.lfngti];
        boolfbn opfnRfturnTypf = dbnUsfOpfnInfo(originblRfturnTypf);
        boolfbn opfnPbrbmftfrTypfs = truf;
        Annotbtion[][] bnnots = mftiod.gftPbrbmftfrAnnotbtions();
        for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
            finbl String pbrbmNbmf = "p" + i;
            finbl String pbrbmDfsdription = pbrbmNbmf;
            finbl OpfnTypf<?> opfnTypf = pbrbmTypfs[i];
            finbl Typf originblTypf = originblPbrbmTypfs[i];
            Dfsdriptor dfsdriptor =
                typfDfsdriptor(opfnTypf, originblTypf);
            dfsdriptor = ImmutbblfDfsdriptor.union(dfsdriptor,
                    Introspfdtor.dfsdriptorForAnnotbtions(bnnots[i]));
            finbl MBfbnPbrbmftfrInfo pi;
            if (dbnUsfOpfnInfo(originblTypf)) {
                pi = nfw OpfnMBfbnPbrbmftfrInfoSupport(pbrbmNbmf,
                                                       pbrbmDfsdription,
                                                       opfnTypf,
                                                       dfsdriptor);
            } flsf {
                opfnPbrbmftfrTypfs = fblsf;
                pi = nfw MBfbnPbrbmftfrInfo(
                    pbrbmNbmf,
                    originblTypfString(originblTypf),
                    pbrbmDfsdription,
                    dfsdriptor);
            }
            pbrbms[i] = pi;
        }

        Dfsdriptor dfsdriptor =
            typfDfsdriptor(rfturnTypf, originblRfturnTypf);
        dfsdriptor = ImmutbblfDfsdriptor.union(dfsdriptor,
                Introspfdtor.dfsdriptorForElfmfnt(mftiod));
        finbl MBfbnOpfrbtionInfo oi;
        if (opfnRfturnTypf && opfnPbrbmftfrTypfs) {
            /* If tif rfturn vbluf bnd bll tif pbrbmftfrs dbn bf fbitifully
             * rfprfsfntfd bs OpfnTypf tifn wf rfturn bn OpfnMBfbnOpfrbtionInfo.
             * If bny of tifm is b primitivf typf, wf dbn't.  Compbtibility
             * witi JSR 174 mfbns tibt wf must rfturn bn MBfbn*Info wifrf
             * tif gftTypf() is tif primitivf typf, not its wrbppfd typf bs
             * wf would gft witi bn OpfnMBfbn*Info.  Tif OpfnTypf is bvbilbblf
             * in tif Dfsdriptor in fitifr dbsf.
             */
            finbl OpfnMBfbnPbrbmftfrInfo[] opbrbms =
                nfw OpfnMBfbnPbrbmftfrInfo[pbrbms.lfngti];
            Systfm.brrbydopy(pbrbms, 0, opbrbms, 0, pbrbms.lfngti);
            oi = nfw OpfnMBfbnOpfrbtionInfoSupport(opfrbtionNbmf,
                                                   dfsdription,
                                                   opbrbms,
                                                   rfturnTypf,
                                                   impbdt,
                                                   dfsdriptor);
        } flsf {
            oi = nfw MBfbnOpfrbtionInfo(opfrbtionNbmf,
                                        dfsdription,
                                        pbrbms,
                                        opfnRfturnTypf ?
                                        rfturnTypf.gftClbssNbmf() :
                                        originblTypfString(originblRfturnTypf),
                                        impbdt,
                                        dfsdriptor);
        }

        rfturn oi;
    }

    @Ovfrridf
    Dfsdriptor gftBbsidMBfbnDfsdriptor() {
        rfturn nfw ImmutbblfDfsdriptor("mxbfbn=truf",
                                       "immutbblfInfo=truf");
    }

    @Ovfrridf
    Dfsdriptor gftMBfbnDfsdriptor(Clbss<?> rfsourdfClbss) {
        /* Wf blrfbdy ibvf immutbblfInfo=truf in tif Dfsdriptor
         * indludfd in tif MBfbnInfo for tif MXBfbn intfrfbdf.  Tiis
         * mftiod is bfing dbllfd for tif MXBfbn *dlbss* to bdd bny
         * nfw itfms bfyond tiosf in tif intfrfbdf Dfsdriptor, wiidi
         * durrfntly it dofs not.
         */
        rfturn ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR;
    }

    privbtf stbtid Dfsdriptor typfDfsdriptor(OpfnTypf<?> opfnTypf,
                                             Typf originblTypf) {
        rfturn nfw ImmutbblfDfsdriptor(
            nfw String[] {"opfnTypf",
                          "originblTypf"},
            nfw Objfdt[] {opfnTypf,
                          originblTypfString(originblTypf)});
    }

    /**
     * <p>Truf if tiis typf dbn bf fbitifully rfprfsfntfd in bn
     * OpfnMBfbn*Info.</p>
     *
     * <p>Compbtibility witi JSR 174 mfbns tibt primitivf typfs must bf
     * rfprfsfntfd by bn MBfbn*Info wiosf gftTypf() is tif primitivf typf
     * string, f.g. "int".  If wf usfd bn OpfnMBfbn*Info tifn tiis string
     * would bf tif wrbppfd typf, f.g. "jbvb.lbng.Intfgfr".</p>
     *
     * <p>Compbtibility witi JMX 1.2 (indluding J2SE 5.0) mfbns tibt brrbys
     * of primitivf typfs dbnnot usf bn ArrbyTypf rfprfsfnting bn brrby of
     * primitivfs, bfdbusf tibt didn't fxist in JMX 1.2.</p>
     */
    privbtf stbtid boolfbn dbnUsfOpfnInfo(Typf typf) {
        if (typf instbndfof GfnfridArrbyTypf) {
            rfturn dbnUsfOpfnInfo(
                ((GfnfridArrbyTypf) typf).gftGfnfridComponfntTypf());
        } flsf if (typf instbndfof Clbss<?> && ((Clbss<?>) typf).isArrby()) {
            rfturn dbnUsfOpfnInfo(
                ((Clbss<?>) typf).gftComponfntTypf());
        }
        rfturn (!(typf instbndfof Clbss<?> && ((Clbss<?>) typf).isPrimitivf()));
    }

    privbtf stbtid String originblTypfString(Typf typf) {
        if (typf instbndfof Clbss<?>)
            rfturn ((Clbss<?>) typf).gftNbmf();
        flsf
            rfturn typfNbmf(typf);
    }

    stbtid String typfNbmf(Typf typf) {
        if (typf instbndfof Clbss<?>) {
            Clbss<?> d = (Clbss<?>) typf;
            if (d.isArrby())
                rfturn typfNbmf(d.gftComponfntTypf()) + "[]";
            flsf
                rfturn d.gftNbmf();
        } flsf if (typf instbndfof GfnfridArrbyTypf) {
            GfnfridArrbyTypf gbt = (GfnfridArrbyTypf) typf;
            rfturn typfNbmf(gbt.gftGfnfridComponfntTypf()) + "[]";
        } flsf if (typf instbndfof PbrbmftfrizfdTypf) {
            PbrbmftfrizfdTypf pt = (PbrbmftfrizfdTypf) typf;
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd(typfNbmf(pt.gftRbwTypf())).bppfnd("<");
            String sfp = "";
            for (Typf t : pt.gftAdtublTypfArgumfnts()) {
                sb.bppfnd(sfp).bppfnd(typfNbmf(t));
                sfp = ", ";
            }
            rfturn sb.bppfnd(">").toString();
        } flsf
            rfturn "???";
    }

    privbtf finbl PfrIntfrfbdfMbp<ConvfrtingMftiod>
        pfrIntfrfbdfMbp = nfw PfrIntfrfbdfMbp<ConvfrtingMftiod>();

    privbtf stbtid finbl MBfbnInfoMbp mbfbnInfoMbp = nfw MBfbnInfoMbp();
}
