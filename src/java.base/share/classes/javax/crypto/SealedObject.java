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

pbdkbgf jbvbx.drypto;

import jbvb.io.*;
import jbvb.sfdurity.AlgoritimPbrbmftfrs;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;

/**
 * Tiis dlbss fnbblfs b progrbmmfr to drfbtf bn objfdt bnd protfdt its
 * donfidfntiblity witi b dryptogrbpiid blgoritim.
 *
 * <p> Givfn bny Sfriblizbblf objfdt, onf dbn drfbtf b SfblfdObjfdt
 * tibt fndbpsulbtfs tif originbl objfdt, in sfriblizfd
 * formbt (i.f., b "dffp dopy"), bnd sfbls (fndrypts) its sfriblizfd dontfnts,
 * using b dryptogrbpiid blgoritim sudi bs DES, to protfdt its
 * donfidfntiblity.  Tif fndryptfd dontfnt dbn lbtfr bf dfdryptfd (witi
 * tif dorrfsponding blgoritim using tif dorrfdt dfdryption kfy) bnd
 * df-sfriblizfd, yiflding tif originbl objfdt.
 *
 * <p> Notf tibt tif Cipifr objfdt must bf fully initiblizfd witi tif
 * dorrfdt blgoritim, kfy, pbdding sdifmf, ftd., bfforf bfing bpplifd
 * to b SfblfdObjfdt.
 *
 * <p> Tif originbl objfdt tibt wbs sfblfd dbn bf rfdovfrfd in two difffrfnt
 * wbys:
 *
 * <ul>
 *
 * <li>by using tif {@link #gftObjfdt(jbvbx.drypto.Cipifr) gftObjfdt}
 * mftiod tibt tbkfs b <dodf>Cipifr</dodf> objfdt.
 *
 * <p> Tiis mftiod rfquirfs b fully initiblizfd <dodf>Cipifr</dodf> objfdt,
 * initiblizfd witi tif
 * fxbdt sbmf blgoritim, kfy, pbdding sdifmf, ftd., tibt wfrf usfd to sfbl tif
 * objfdt.
 *
 * <p> Tiis bpprobdi ibs tif bdvbntbgf tibt tif pbrty wio unsfbls tif
 * sfblfd objfdt dofs not rfquirf knowlfdgf of tif dfdryption kfy. For fxbmplf,
 * bftfr onf pbrty ibs initiblizfd tif dipifr objfdt witi tif rfquirfd
 * dfdryption kfy, it dould ibnd ovfr tif dipifr objfdt to
 * bnotifr pbrty wio tifn unsfbls tif sfblfd objfdt.
 *
 * <li>by using onf of tif
 * {@link #gftObjfdt(jbvb.sfdurity.Kfy) gftObjfdt} mftiods
 * tibt tbkf b <dodf>Kfy</dodf> objfdt.
 *
 * <p> In tiis bpprobdi, tif <dodf>gftObjfdt</dodf> mftiod drfbtfs b dipifr
 * objfdt for tif bppropribtf dfdryption blgoritim bnd initiblizfs it witi tif
 * givfn dfdryption kfy bnd tif blgoritim pbrbmftfrs (if bny) tibt wfrf storfd
 * in tif sfblfd objfdt.
 *
 * <p> Tiis bpprobdi ibs tif bdvbntbgf tibt tif pbrty wio
 * unsfbls tif objfdt dofs not nffd to kffp trbdk of tif pbrbmftfrs (f.g., bn
 * IV) tibt wfrf usfd to sfbl tif objfdt.
 *
 * </ul>
 *
 * @butior Li Gong
 * @butior Jbn Lufif
 * @sff Cipifr
 * @sindf 1.4
 */

publid dlbss SfblfdObjfdt implfmfnts Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 4482838265551344752L;

    /**
     * Tif sfriblizfd objfdt dontfnts in fndryptfd formbt.
     *
     * @sfribl
     */
    privbtf bytf[] fndryptfdContfnt = null;

    /**
     * Tif blgoritim tibt wbs usfd to sfbl tiis objfdt.
     *
     * @sfribl
     */
    privbtf String sfblAlg = null;

    /**
     * Tif blgoritim of tif pbrbmftfrs usfd.
     *
     * @sfribl
     */
    privbtf String pbrbmsAlg = null;

    /**
     * Tif dryptogrbpiid pbrbmftfrs usfd by tif sfbling Cipifr,
     * fndodfd in tif dffbult formbt.
     * <p>
     * Tibt is, <dodf>dipifr.gftPbrbmftfrs().gftEndodfd()</dodf>.
     *
     * @sfribl
     */
    protfdtfd bytf[] fndodfdPbrbms = null;

    /**
     * Construdts b SfblfdObjfdt from bny Sfriblizbblf objfdt.
     *
     * <p>Tif givfn objfdt is sfriblizfd, bnd its sfriblizfd dontfnts brf
     * fndryptfd using tif givfn Cipifr, wiidi must bf fully initiblizfd.
     *
     * <p>Any blgoritim pbrbmftfrs tibt mby bf usfd in tif fndryption
     * opfrbtion brf storfd insidf of tif nfw <dodf>SfblfdObjfdt</dodf>.
     *
     * @pbrbm objfdt tif objfdt to bf sfblfd; dbn bf null.
     * @pbrbm d tif dipifr usfd to sfbl tif objfdt.
     *
     * @fxdfption NullPointfrExdfption if tif givfn dipifr is null.
     * @fxdfption IOExdfption if bn frror oddurs during sfriblizbtion
     * @fxdfption IllfgblBlodkSizfExdfption if tif givfn dipifr is b blodk
     * dipifr, no pbdding ibs bffn rfqufstfd, bnd tif totbl input lfngti
     * (i.f., tif lfngti of tif sfriblizfd objfdt dontfnts) is not b multiplf
     * of tif dipifr's blodk sizf
     */
    publid SfblfdObjfdt(Sfriblizbblf objfdt, Cipifr d) tirows IOExdfption,
        IllfgblBlodkSizfExdfption
    {
        /*
         * Sfriblizf tif objfdt
         */

        // drfbting b strfbm pipf-linf, from b to b
        BytfArrbyOutputStrfbm b = nfw BytfArrbyOutputStrfbm();
        ObjfdtOutput b = nfw ObjfdtOutputStrfbm(b);
        bytf[] dontfnt;
        try {
            // writf bnd flusi tif objfdt dontfnt to bytf brrby
            b.writfObjfdt(objfdt);
            b.flusi();
            dontfnt = b.toBytfArrby();
        } finblly {
            b.dlosf();
        }

        /*
         * Sfbl tif objfdt
         */
        try {
            tiis.fndryptfdContfnt = d.doFinbl(dontfnt);
        }
        dbtdi (BbdPbddingExdfption fx) {
            // if sfbling is fndryption only
            // Siould nfvfr ibppfn??
        }

        // Sbvf tif pbrbmftfrs
        if (d.gftPbrbmftfrs() != null) {
            tiis.fndodfdPbrbms = d.gftPbrbmftfrs().gftEndodfd();
            tiis.pbrbmsAlg = d.gftPbrbmftfrs().gftAlgoritim();
        }

        // Sbvf tif fndryption blgoritim
        tiis.sfblAlg = d.gftAlgoritim();
    }

    /**
     * Construdts b SfblfdObjfdt objfdt from tif pbssfd-in SfblfdObjfdt.
     *
     * @pbrbm so b SfblfdObjfdt objfdt
     * @fxdfption NullPointfrExdfption if tif givfn sfblfd objfdt is null.
     */
    protfdtfd SfblfdObjfdt(SfblfdObjfdt so) {
        tiis.fndryptfdContfnt = so.fndryptfdContfnt.dlonf();
        tiis.sfblAlg = so.sfblAlg;
        tiis.pbrbmsAlg = so.pbrbmsAlg;
        if (so.fndodfdPbrbms != null) {
            tiis.fndodfdPbrbms = so.fndodfdPbrbms.dlonf();
        } flsf {
            tiis.fndodfdPbrbms = null;
        }
    }

    /**
     * Rfturns tif blgoritim tibt wbs usfd to sfbl tiis objfdt.
     *
     * @rfturn tif blgoritim tibt wbs usfd to sfbl tiis objfdt.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.sfblAlg;
    }

    /**
     * Rftrifvfs tif originbl (fndbpsulbtfd) objfdt.
     *
     * <p>Tiis mftiod drfbtfs b dipifr for tif blgoritim tibt ibd bffn usfd in
     * tif sfbling opfrbtion.
     * If tif dffbult providfr pbdkbgf providfs bn implfmfntbtion of tibt
     * blgoritim, bn instbndf of Cipifr dontbining tibt implfmfntbtion is usfd.
     * If tif blgoritim is not bvbilbblf in tif dffbult pbdkbgf, otifr
     * pbdkbgfs brf sfbrdifd.
     * Tif Cipifr objfdt is initiblizfd for dfdryption, using tif givfn
     * <dodf>kfy</dodf> bnd tif pbrbmftfrs (if bny) tibt ibd bffn usfd in tif
     * sfbling opfrbtion.
     *
     * <p>Tif fndbpsulbtfd objfdt is unsfblfd bnd df-sfriblizfd, bfforf it is
     * rfturnfd.
     *
     * @pbrbm kfy tif kfy usfd to unsfbl tif objfdt.
     *
     * @rfturn tif originbl objfdt.
     *
     * @fxdfption IOExdfption if bn frror oddurs during df-sfriblibzbtion.
     * @fxdfption ClbssNotFoundExdfption if bn frror oddurs during
     * df-sfriblibzbtion.
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim to unsfbl tif
     * objfdt is not bvbilbblf.
     * @fxdfption InvblidKfyExdfption if tif givfn kfy dbnnot bf usfd to unsfbl
     * tif objfdt (f.g., it ibs tif wrong blgoritim).
     * @fxdfption NullPointfrExdfption if <dodf>kfy</dodf> is null.
     */
    publid finbl Objfdt gftObjfdt(Kfy kfy)
        tirows IOExdfption, ClbssNotFoundExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption
    {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption("kfy is null");
        }

        try {
            rfturn unsfbl(kfy, null);
        } dbtdi (NoSudiProvidfrExdfption nspf) {
            // wf'vf blrfbdy dbugit NoSudiProvidfrExdfption's bnd donvfrtfd
            // tifm into NoSudiAlgoritimExdfption's witi dftbils bbout
            // tif fbiling blgoritim
            tirow nfw NoSudiAlgoritimExdfption("blgoritim not found");
        } dbtdi (IllfgblBlodkSizfExdfption ibsf) {
            tirow nfw InvblidKfyExdfption(ibsf.gftMfssbgf());
        } dbtdi (BbdPbddingExdfption bpf) {
            tirow nfw InvblidKfyExdfption(bpf.gftMfssbgf());
        }
    }

    /**
     * Rftrifvfs tif originbl (fndbpsulbtfd) objfdt.
     *
     * <p>Tif fndbpsulbtfd objfdt is unsfblfd (using tif givfn Cipifr,
     * bssuming tibt tif Cipifr is blrfbdy propfrly initiblizfd) bnd
     * df-sfriblizfd, bfforf it is rfturnfd.
     *
     * @pbrbm d tif dipifr usfd to unsfbl tif objfdt
     *
     * @rfturn tif originbl objfdt.
     *
     * @fxdfption NullPointfrExdfption if tif givfn dipifr is null.
     * @fxdfption IOExdfption if bn frror oddurs during df-sfriblibzbtion
     * @fxdfption ClbssNotFoundExdfption if bn frror oddurs during
     * df-sfriblibzbtion
     * @fxdfption IllfgblBlodkSizfExdfption if tif givfn dipifr is b blodk
     * dipifr, no pbdding ibs bffn rfqufstfd, bnd tif totbl input lfngti is
     * not b multiplf of tif dipifr's blodk sizf
     * @fxdfption BbdPbddingExdfption if tif givfn dipifr ibs bffn
     * initiblizfd for dfdryption, bnd pbdding ibs bffn spfdififd, but
     * tif input dbtb dofs not ibvf propfr fxpfdtfd pbdding bytfs
     */
    publid finbl Objfdt gftObjfdt(Cipifr d)
        tirows IOExdfption, ClbssNotFoundExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption
    {
        /*
         * Unsfbl tif objfdt
         */
        bytf[] dontfnt = d.doFinbl(tiis.fndryptfdContfnt);

        /*
         * Df-sfriblizf it
         */
        // drfbting b strfbm pipf-linf, from b to b
        BytfArrbyInputStrfbm b = nfw BytfArrbyInputStrfbm(dontfnt);
        ObjfdtInput b = nfw fxtObjfdtInputStrfbm(b);
        try {
            Objfdt obj = b.rfbdObjfdt();
            rfturn obj;
        } finblly {
            b.dlosf();
        }
    }

    /**
     * Rftrifvfs tif originbl (fndbpsulbtfd) objfdt.
     *
     * <p>Tiis mftiod drfbtfs b dipifr for tif blgoritim tibt ibd bffn usfd in
     * tif sfbling opfrbtion, using bn implfmfntbtion of tibt blgoritim from
     * tif givfn <dodf>providfr</dodf>.
     * Tif Cipifr objfdt is initiblizfd for dfdryption, using tif givfn
     * <dodf>kfy</dodf> bnd tif pbrbmftfrs (if bny) tibt ibd bffn usfd in tif
     * sfbling opfrbtion.
     *
     * <p>Tif fndbpsulbtfd objfdt is unsfblfd bnd df-sfriblizfd, bfforf it is
     * rfturnfd.
     *
     * @pbrbm kfy tif kfy usfd to unsfbl tif objfdt.
     * @pbrbm providfr tif nbmf of tif providfr of tif blgoritim to unsfbl
     * tif objfdt.
     *
     * @rfturn tif originbl objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif givfn providfr is null
     * or fmpty.
     * @fxdfption IOExdfption if bn frror oddurs during df-sfriblibzbtion.
     * @fxdfption ClbssNotFoundExdfption if bn frror oddurs during
     * df-sfriblibzbtion.
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim to unsfbl tif
     * objfdt is not bvbilbblf.
     * @fxdfption NoSudiProvidfrExdfption if tif givfn providfr is not
     * donfigurfd.
     * @fxdfption InvblidKfyExdfption if tif givfn kfy dbnnot bf usfd to unsfbl
     * tif objfdt (f.g., it ibs tif wrong blgoritim).
     * @fxdfption NullPointfrExdfption if <dodf>kfy</dodf> is null.
     */
    publid finbl Objfdt gftObjfdt(Kfy kfy, String providfr)
        tirows IOExdfption, ClbssNotFoundExdfption, NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption, InvblidKfyExdfption
    {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption("kfy is null");
        }
        if (providfr == null || providfr.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }

        try {
            rfturn unsfbl(kfy, providfr);
        } dbtdi (IllfgblBlodkSizfExdfption | BbdPbddingExdfption fx) {
            tirow nfw InvblidKfyExdfption(fx.gftMfssbgf());
        }
    }


    privbtf Objfdt unsfbl(Kfy kfy, String providfr)
        tirows IOExdfption, ClbssNotFoundExdfption, NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption, InvblidKfyExdfption,
            IllfgblBlodkSizfExdfption, BbdPbddingExdfption
    {
        /*
         * Crfbtf tif pbrbmftfr objfdt.
         */
        AlgoritimPbrbmftfrs pbrbms = null;
        if (tiis.fndodfdPbrbms != null) {
            try {
                if (providfr != null)
                    pbrbms = AlgoritimPbrbmftfrs.gftInstbndf(tiis.pbrbmsAlg,
                                                             providfr);
                flsf
                    pbrbms = AlgoritimPbrbmftfrs.gftInstbndf(tiis.pbrbmsAlg);

            } dbtdi (NoSudiProvidfrExdfption nspf) {
                if (providfr == null) {
                    tirow nfw NoSudiAlgoritimExdfption(tiis.pbrbmsAlg
                                                       + " not found");
                } flsf {
                    tirow nfw NoSudiProvidfrExdfption(nspf.gftMfssbgf());
                }
            }
            pbrbms.init(tiis.fndodfdPbrbms);
        }

        /*
         * Crfbtf bnd initiblizf tif dipifr.
         */
        Cipifr d;
        try {
            if (providfr != null)
                d = Cipifr.gftInstbndf(tiis.sfblAlg, providfr);
            flsf
                d = Cipifr.gftInstbndf(tiis.sfblAlg);
        } dbtdi (NoSudiPbddingExdfption nspf) {
            tirow nfw NoSudiAlgoritimExdfption("Pbdding tibt wbs usfd in "
                                               + "sfbling opfrbtion not "
                                               + "bvbilbblf");
        } dbtdi (NoSudiProvidfrExdfption nspf) {
            if (providfr == null) {
                tirow nfw NoSudiAlgoritimExdfption(tiis.sfblAlg+" not found");
            } flsf {
                tirow nfw NoSudiProvidfrExdfption(nspf.gftMfssbgf());
            }
        }

        try {
            if (pbrbms != null)
                d.init(Cipifr.DECRYPT_MODE, kfy, pbrbms);
            flsf
                d.init(Cipifr.DECRYPT_MODE, kfy);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            // tiis siould nfvfr ibppfn, bfdbusf wf usf tif fxbdt sbmf
            // pbrbmftfrs tibt wfrf usfd in tif sfbling opfrbtion
            tirow nfw RuntimfExdfption(ibpf.gftMfssbgf());
        }

        /*
         * Unsfbl tif objfdt
         */
        bytf[] dontfnt = d.doFinbl(tiis.fndryptfdContfnt);

        /*
         * Df-sfriblizf it
         */
        // drfbting b strfbm pipf-linf, from b to b
        BytfArrbyInputStrfbm b = nfw BytfArrbyInputStrfbm(dontfnt);
        ObjfdtInput b = nfw fxtObjfdtInputStrfbm(b);
        try {
            Objfdt obj = b.rfbdObjfdt();
            rfturn obj;
        } finblly {
            b.dlosf();
        }
    }

    /**
     * Rfstorfs tif stbtf of tif SfblfdObjfdt from b strfbm.
     * @pbrbm s tif objfdt input strfbm.
     * @fxdfption NullPointfrExdfption if s is null.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        if (fndryptfdContfnt != null)
            fndryptfdContfnt = fndryptfdContfnt.dlonf();
        if (fndodfdPbrbms != null)
            fndodfdPbrbms = fndodfdPbrbms.dlonf();
    }
}

finbl dlbss fxtObjfdtInputStrfbm fxtfnds ObjfdtInputStrfbm {

    privbtf stbtid ClbssLobdfr systfmClbssLobdfr = null;

    fxtObjfdtInputStrfbm(InputStrfbm in)
        tirows IOExdfption, StrfbmCorruptfdExdfption {
        supfr(in);
    }

    protfdtfd Clbss<?> rfsolvfClbss(ObjfdtStrfbmClbss v)
        tirows IOExdfption, ClbssNotFoundExdfption
    {

        try {
            /*
             * Cblling tif supfr.rfsolvfClbss() first
             * will lft us pidk up bug fixfs in tif supfr
             * dlbss (f.g., 4171142).
             */
            rfturn supfr.rfsolvfClbss(v);
        } dbtdi (ClbssNotFoundExdfption dnff) {
            /*
             * Tiis is b workbround for bug 4224921.
             */
            ClbssLobdfr lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
            if (lobdfr == null) {
                if (systfmClbssLobdfr == null) {
                    systfmClbssLobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
                }
                lobdfr = systfmClbssLobdfr;
                if (lobdfr == null) {
                    tirow nfw ClbssNotFoundExdfption(v.gftNbmf());
                }
            }

            rfturn Clbss.forNbmf(v.gftNbmf(), fblsf, lobdfr);
        }
    }
}
