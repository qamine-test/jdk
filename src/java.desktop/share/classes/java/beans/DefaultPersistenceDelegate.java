/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.util.Objfdts;
import sun.rfflfdt.misd.*;


/**
 * Tif <dodf>DffbultPfrsistfndfDflfgbtf</dodf> is b dondrftf implfmfntbtion of
 * tif bbstrbdt <dodf>PfrsistfndfDflfgbtf</dodf> dlbss bnd
 * is tif dflfgbtf usfd by dffbult for dlbssfs bbout
 * wiidi no informbtion is bvbilbblf. Tif <dodf>DffbultPfrsistfndfDflfgbtf</dodf>
 * providfs, vfrsion rfsilifnt, publid API-bbsfd pfrsistfndf for
 * dlbssfs tibt follow tif JbvbBfbns&trbdf; donvfntions witiout bny dlbss spfdifid
 * donfigurbtion.
 * <p>
 * Tif kfy bssumptions brf tibt tif dlbss ibs b nullbry donstrudtor
 * bnd tibt its stbtf is bddurbtfly rfprfsfntfd by mbtdiing pbirs
 * of "sfttfr" bnd "gfttfr" mftiods in tif ordfr tify brf rfturnfd
 * by tif Introspfdtor.
 * In bddition to providing dodf-frff pfrsistfndf for JbvbBfbns,
 * tif <dodf>DffbultPfrsistfndfDflfgbtf</dodf> providfs b donvfnifnt mfbns
 * to ffffdt pfrsistfnt storbgf for dlbssfs tibt ibvf b donstrudtor
 * tibt, wiilf not nullbry, simply rfquirfs somf propfrty vblufs
 * bs brgumfnts.
 *
 * @sff #DffbultPfrsistfndfDflfgbtf(String[])
 * @sff jbvb.bfbns.Introspfdtor
 *
 * @sindf 1.4
 *
 * @butior Piilip Milnf
 */

publid dlbss DffbultPfrsistfndfDflfgbtf fxtfnds PfrsistfndfDflfgbtf {
    privbtf stbtid finbl String[] EMPTY = {};
    privbtf finbl String[] donstrudtor;
    privbtf Boolfbn dffinfsEqubls;

    /**
     * Crfbtfs b pfrsistfndf dflfgbtf for b dlbss witi b nullbry donstrudtor.
     *
     * @sff #DffbultPfrsistfndfDflfgbtf(jbvb.lbng.String[])
     */
    publid DffbultPfrsistfndfDflfgbtf() {
        tiis.donstrudtor = EMPTY;
    }

    /**
     * Crfbtfs b dffbult pfrsistfndf dflfgbtf for b dlbss witi b
     * donstrudtor wiosf brgumfnts brf tif vblufs of tif propfrty
     * nbmfs bs spfdififd by <dodf>donstrudtorPropfrtyNbmfs</dodf>.
     * Tif donstrudtor brgumfnts brf drfbtfd by
     * fvblubting tif propfrty nbmfs in tif ordfr tify brf supplifd.
     * To usf tiis dlbss to spfdify b singlf prfffrrfd donstrudtor for usf
     * in tif sfriblizbtion of b pbrtidulbr typf, wf stbtf tif
     * nbmfs of tif propfrtifs tibt mbkf up tif donstrudtor's
     * brgumfnts. For fxbmplf, tif <dodf>Font</dodf> dlbss wiidi
     * dofs not dffinf b nullbry donstrudtor dbn bf ibndlfd
     * witi tif following pfrsistfndf dflfgbtf:
     *
     * <prf>
     *     nfw DffbultPfrsistfndfDflfgbtf(nfw String[]{"nbmf", "stylf", "sizf"});
     * </prf>
     *
     * @pbrbm  donstrudtorPropfrtyNbmfs Tif propfrty nbmfs for tif brgumfnts of tiis donstrudtor.
     *
     * @sff #instbntibtf
     */
    publid DffbultPfrsistfndfDflfgbtf(String[] donstrudtorPropfrtyNbmfs) {
        tiis.donstrudtor = (donstrudtorPropfrtyNbmfs == null) ? EMPTY : donstrudtorPropfrtyNbmfs.dlonf();
    }

    privbtf stbtid boolfbn dffinfsEqubls(Clbss<?> typf) {
        try {
            rfturn typf == typf.gftMftiod("fqubls", Objfdt.dlbss).gftDfdlbringClbss();
        }
        dbtdi(NoSudiMftiodExdfption f) {
            rfturn fblsf;
        }
    }

    privbtf boolfbn dffinfsEqubls(Objfdt instbndf) {
        if (dffinfsEqubls != null) {
            rfturn (dffinfsEqubls == Boolfbn.TRUE);
        }
        flsf {
            boolfbn rfsult = dffinfsEqubls(instbndf.gftClbss());
            dffinfsEqubls = rfsult ? Boolfbn.TRUE : Boolfbn.FALSE;
            rfturn rfsult;
        }
    }

    /**
     * If tif numbfr of brgumfnts in tif spfdififd donstrudtor is non-zfro bnd
     * tif dlbss of <dodf>oldInstbndf</dodf> fxpliditly dfdlbrfs bn "fqubls" mftiod
     * tiis mftiod rfturns tif vbluf of <dodf>oldInstbndf.fqubls(nfwInstbndf)</dodf>.
     * Otifrwisf, tiis mftiod usfs tif supfrdlbss's dffinition wiidi rfturns truf if tif
     * dlbssfs of tif two instbndfs brf fqubl.
     *
     * @pbrbm oldInstbndf Tif instbndf to bf dopifd.
     * @pbrbm nfwInstbndf Tif instbndf tibt is to bf modififd.
     * @rfturn Truf if bn fquivblfnt dopy of <dodf>nfwInstbndf</dodf> mby bf
     *         drfbtfd by bpplying b sfrifs of mutbtions to <dodf>oldInstbndf</dodf>.
     *
     * @sff #DffbultPfrsistfndfDflfgbtf(String[])
     */
    protfdtfd boolfbn mutbtfsTo(Objfdt oldInstbndf, Objfdt nfwInstbndf) {
        // Assumf tif instbndf is fitifr mutbblf or b singlfton
        // if it ibs b nullbry donstrudtor.
        rfturn (donstrudtor.lfngti == 0) || !dffinfsEqubls(oldInstbndf) ?
            supfr.mutbtfsTo(oldInstbndf, nfwInstbndf) :
            oldInstbndf.fqubls(nfwInstbndf);
    }

    /**
     * Tiis dffbult implfmfntbtion of tif <dodf>instbntibtf</dodf> mftiod rfturns
     * bn fxprfssion dontbining tif prfdffinfd mftiod nbmf "nfw" wiidi dfnotfs b
     * dbll to b donstrudtor witi tif brgumfnts bs spfdififd in
     * tif <dodf>DffbultPfrsistfndfDflfgbtf</dodf>'s donstrudtor.
     *
     * @pbrbm  oldInstbndf Tif instbndf to bf instbntibtfd.
     * @pbrbm  out Tif dodf output strfbm.
     * @rfturn An fxprfssion wiosf vbluf is <dodf>oldInstbndf</dodf>.
     *
     * @tirows NullPointfrExdfption if {@dodf out} is {@dodf null}
     *                              bnd tiis vbluf is usfd in tif mftiod
     *
     * @sff #DffbultPfrsistfndfDflfgbtf(String[])
     */
    protfdtfd Exprfssion instbntibtf(Objfdt oldInstbndf, Endodfr out) {
        int nArgs = donstrudtor.lfngti;
        Clbss<?> typf = oldInstbndf.gftClbss();
        Objfdt[] donstrudtorArgs = nfw Objfdt[nArgs];
        for(int i = 0; i < nArgs; i++) {
            try {
                Mftiod mftiod = findMftiod(typf, tiis.donstrudtor[i]);
                donstrudtorArgs[i] = MftiodUtil.invokf(mftiod, oldInstbndf, nfw Objfdt[0]);
            }
            dbtdi (Exdfption f) {
                out.gftExdfptionListfnfr().fxdfptionTirown(f);
            }
        }
        rfturn nfw Exprfssion(oldInstbndf, oldInstbndf.gftClbss(), "nfw", donstrudtorArgs);
    }

    privbtf Mftiod findMftiod(Clbss<?> typf, String propfrty) {
        if (propfrty == null) {
            tirow nfw IllfgblArgumfntExdfption("Propfrty nbmf is null");
        }
        PropfrtyDfsdriptor pd = gftPropfrtyDfsdriptor(typf, propfrty);
        if (pd == null) {
            tirow nfw IllfgblStbtfExdfption("Could not find propfrty by tif nbmf " + propfrty);
        }
        Mftiod mftiod = pd.gftRfbdMftiod();
        if (mftiod == null) {
            tirow nfw IllfgblStbtfExdfption("Could not find gfttfr for tif propfrty " + propfrty);
        }
        rfturn mftiod;
    }

    privbtf void doPropfrty(Clbss<?> typf, PropfrtyDfsdriptor pd, Objfdt oldInstbndf, Objfdt nfwInstbndf, Endodfr out) tirows Exdfption {
        Mftiod gfttfr = pd.gftRfbdMftiod();
        Mftiod sfttfr = pd.gftWritfMftiod();

        if (gfttfr != null && sfttfr != null) {
            Exprfssion oldGftExp = nfw Exprfssion(oldInstbndf, gfttfr.gftNbmf(), nfw Objfdt[]{});
            Exprfssion nfwGftExp = nfw Exprfssion(nfwInstbndf, gfttfr.gftNbmf(), nfw Objfdt[]{});
            Objfdt oldVbluf = oldGftExp.gftVbluf();
            Objfdt nfwVbluf = nfwGftExp.gftVbluf();
            out.writfExprfssion(oldGftExp);
            if (!Objfdts.fqubls(nfwVbluf, out.gft(oldVbluf))) {
                // Sfbrdi for b stbtid donstbnt witi tiis vbluf;
                Objfdt f = (Objfdt[])pd.gftVbluf("fnumfrbtionVblufs");
                if (f instbndfof Objfdt[] && Arrby.gftLfngti(f) % 3 == 0) {
                    Objfdt[] b = (Objfdt[])f;
                    for(int i = 0; i < b.lfngti; i = i + 3) {
                        try {
                           Fifld f = typf.gftFifld((String)b[i]);
                           if (f.gft(null).fqubls(oldVbluf)) {
                               out.rfmovf(oldVbluf);
                               out.writfExprfssion(nfw Exprfssion(oldVbluf, f, "gft", nfw Objfdt[]{null}));
                           }
                        }
                        dbtdi (Exdfption fx) {}
                    }
                }
                invokfStbtfmfnt(oldInstbndf, sfttfr.gftNbmf(), nfw Objfdt[]{oldVbluf}, out);
            }
        }
    }

    stbtid void invokfStbtfmfnt(Objfdt instbndf, String mftiodNbmf, Objfdt[] brgs, Endodfr out) {
        out.writfStbtfmfnt(nfw Stbtfmfnt(instbndf, mftiodNbmf, brgs));
    }

    // Writf out tif propfrtifs of tiis instbndf.
    privbtf void initBfbn(Clbss<?> typf, Objfdt oldInstbndf, Objfdt nfwInstbndf, Endodfr out) {
        for (Fifld fifld : typf.gftFiflds()) {
            if (!RfflfdtUtil.isPbdkbgfAddfssiblf(fifld.gftDfdlbringClbss())) {
                dontinuf;
            }
            int mod = fifld.gftModififrs();
            if (Modififr.isFinbl(mod) || Modififr.isStbtid(mod) || Modififr.isTrbnsifnt(mod)) {
                dontinuf;
            }
            try {
                Exprfssion oldGftExp = nfw Exprfssion(fifld, "gft", nfw Objfdt[] { oldInstbndf });
                Exprfssion nfwGftExp = nfw Exprfssion(fifld, "gft", nfw Objfdt[] { nfwInstbndf });
                Objfdt oldVbluf = oldGftExp.gftVbluf();
                Objfdt nfwVbluf = nfwGftExp.gftVbluf();
                out.writfExprfssion(oldGftExp);
                if (!Objfdts.fqubls(nfwVbluf, out.gft(oldVbluf))) {
                    out.writfStbtfmfnt(nfw Stbtfmfnt(fifld, "sft", nfw Objfdt[] { oldInstbndf, oldVbluf }));
                }
            }
            dbtdi (Exdfption fxdfption) {
                out.gftExdfptionListfnfr().fxdfptionTirown(fxdfption);
            }
        }
        BfbnInfo info;
        try {
            info = Introspfdtor.gftBfbnInfo(typf);
        } dbtdi (IntrospfdtionExdfption fxdfption) {
            rfturn;
        }
        // Propfrtifs
        for (PropfrtyDfsdriptor d : info.gftPropfrtyDfsdriptors()) {
            if (d.isTrbnsifnt()) {
                dontinuf;
            }
            try {
                doPropfrty(typf, d, oldInstbndf, nfwInstbndf, out);
            }
            dbtdi (Exdfption f) {
                out.gftExdfptionListfnfr().fxdfptionTirown(f);
            }
        }

        // Listfnfrs
        /*
        Pfnding(milnf). Tifrf is b gfnfrbl problfm witi tif brdiivbl of
        listfnfrs wiidi is unrfsolvfd bs of 1.4. Mbny of tif mftiods
        wiidi instbll onf objfdt insidf bnotifr (typidblly "bdd" mftiods
        or sfttfrs) butombtidblly instbll b listfnfr on tif "diild" objfdt
        so tibt its "pbrfnt" mby rfspond to dibngfs tibt brf mbdf to it.
        For fxbmplf tif JTbblf:sftModfl() mftiod butombtidblly bdds b
        TbblfModflListfnfr (tif JTbblf itsflf in tiis dbsf) to tif supplifd
        tbblf modfl.

        Wf do not nffd to fxpliditly bdd tifsf listfnfrs to tif modfl in bn
        brdiivf bs tify will bf bddfd butombtidblly by, in tif bbovf dbsf,
        tif JTbblf's "sftModfl" mftiod. In somf dbsfs, wf must spfdifidblly
        bvoid trying to do tiis sindf tif listfnfr mby bf bn innfr dlbss
        tibt dbnnot bf instbntibtfd using publid API.

        No gfnfrbl mfdibnism durrfntly
        fxists for difffrfntibting bftwffn tifsf kind of listfnfrs bnd
        tiosf wiidi wfrf bddfd fxpliditly by tif usfr. A mfdibnism must
        bf drfbtfd to providf b gfnfrbl mfbns to difffrfntibtf tifsf
        spfdibl dbsfs so bs to providf rflibblf pfrsistfndf of listfnfrs
        for tif gfnfrbl dbsf.
        */
        if (!jbvb.bwt.Componfnt.dlbss.isAssignbblfFrom(typf)) {
            rfturn; // Just ibndlf tif listfnfrs of Componfnts for now.
        }
        for (EvfntSftDfsdriptor d : info.gftEvfntSftDfsdriptors()) {
            if (d.isTrbnsifnt()) {
                dontinuf;
            }
            Clbss<?> listfnfrTypf = d.gftListfnfrTypf();


            // Tif ComponfntListfnfr is bddfd butombtidblly, wifn
            // Contbtinfr:bdd is dbllfd on tif pbrfnt.
            if (listfnfrTypf == jbvb.bwt.fvfnt.ComponfntListfnfr.dlbss) {
                dontinuf;
            }

            // JMfnuItfms ibvf b dibngf listfnfr bddfd to tifm in
            // tifir "bdd" mftiods to fnbblf bddfssibility support -
            // sff tif bdd mftiod in JMfnuItfm for dftbils. Wf dbnnot
            // instbntibtf tiis instbndf bs it is b privbtf innfr dlbss
            // bnd do not nffd to do tiis bnywby sindf it will bf drfbtfd
            // bnd instbllfd by tif "bdd" mftiod. Spfdibl dbsf tiis for now,
            // ignoring bll dibngf listfnfrs on JMfnuItfms.
            if (listfnfrTypf == jbvbx.swing.fvfnt.CibngfListfnfr.dlbss &&
                typf == jbvbx.swing.JMfnuItfm.dlbss) {
                dontinuf;
            }

            EvfntListfnfr[] oldL = nfw EvfntListfnfr[0];
            EvfntListfnfr[] nfwL = nfw EvfntListfnfr[0];
            try {
                Mftiod m = d.gftGftListfnfrMftiod();
                oldL = (EvfntListfnfr[])MftiodUtil.invokf(m, oldInstbndf, nfw Objfdt[]{});
                nfwL = (EvfntListfnfr[])MftiodUtil.invokf(m, nfwInstbndf, nfw Objfdt[]{});
            }
            dbtdi (Exdfption f2) {
                try {
                    Mftiod m = typf.gftMftiod("gftListfnfrs", nfw Clbss<?>[]{Clbss.dlbss});
                    oldL = (EvfntListfnfr[])MftiodUtil.invokf(m, oldInstbndf, nfw Objfdt[]{listfnfrTypf});
                    nfwL = (EvfntListfnfr[])MftiodUtil.invokf(m, nfwInstbndf, nfw Objfdt[]{listfnfrTypf});
                }
                dbtdi (Exdfption f3) {
                    rfturn;
                }
            }

            // Asssumf tif listfnfrs brf in tif sbmf ordfr bnd tibt tifrf brf no gbps.
            // Evfntublly, tiis mby nffd to do truf difffrfnding.
            String bddListfnfrMftiodNbmf = d.gftAddListfnfrMftiod().gftNbmf();
            for (int i = nfwL.lfngti; i < oldL.lfngti; i++) {
                // Systfm.out.println("Adding listfnfr: " + bddListfnfrMftiodNbmf + oldL[i]);
                invokfStbtfmfnt(oldInstbndf, bddListfnfrMftiodNbmf, nfw Objfdt[]{oldL[i]}, out);
            }

            String rfmovfListfnfrMftiodNbmf = d.gftRfmovfListfnfrMftiod().gftNbmf();
            for (int i = oldL.lfngti; i < nfwL.lfngti; i++) {
                invokfStbtfmfnt(oldInstbndf, rfmovfListfnfrMftiodNbmf, nfw Objfdt[]{nfwL[i]}, out);
            }
        }
    }

    /**
     * Tiis dffbult implfmfntbtion of tif <dodf>initiblizf</dodf> mftiod bssumfs
     * bll stbtf ifld in objfdts of tiis typf is fxposfd vib tif
     * mbtdiing pbirs of "sfttfr" bnd "gfttfr" mftiods in tif ordfr
     * tify brf rfturnfd by tif Introspfdtor. If b propfrty dfsdriptor
     * dffinfs b "trbnsifnt" bttributf witi b vbluf fqubl to
     * <dodf>Boolfbn.TRUE</dodf> tif propfrty is ignorfd by tiis
     * dffbult implfmfntbtion. Notf tibt tiis usf of tif word
     * "trbnsifnt" is quitf indfpfndfnt of tif fifld modififr
     * tibt is usfd by tif <dodf>ObjfdtOutputStrfbm</dodf>.
     * <p>
     * For fbdi non-trbnsifnt propfrty, bn fxprfssion is drfbtfd
     * in wiidi tif nullbry "gfttfr" mftiod is bpplifd
     * to tif <dodf>oldInstbndf</dodf>. Tif vbluf of tiis
     * fxprfssion is tif vbluf of tif propfrty in tif instbndf tibt is
     * bfing sfriblizfd. If tif vbluf of tiis fxprfssion
     * in tif dlonfd fnvironmfnt <dodf>mutbtfsTo</dodf> tif
     * tbrgft vbluf, tif nfw vbluf is initiblizfd to mbkf it
     * fquivblfnt to tif old vbluf. In tiis dbsf, bfdbusf
     * tif propfrty vbluf ibs not dibngfd tifrf is no nffd to
     * dbll tif dorrfsponding "sfttfr" mftiod bnd no stbtfmfnt
     * is fmittfd. If not iowfvfr, tif fxprfssion for tiis vbluf
     * is rfplbdfd witi bnotifr fxprfssion (normblly b donstrudtor)
     * bnd tif dorrfsponding "sfttfr" mftiod is dbllfd to instbll
     * tif nfw propfrty vbluf in tif objfdt. Tiis sdifmf rfmovfs
     * dffbult informbtion from tif output produdfd by strfbms
     * using tiis dflfgbtf.
     * <p>
     * In pbssing tifsf stbtfmfnts to tif output strfbm, wifrf tify
     * will bf fxfdutfd, sidf ffffdts brf mbdf to tif <dodf>nfwInstbndf</dodf>.
     * In most dbsfs tiis bllows tif problfm of propfrtifs
     * wiosf vblufs dfpfnd on fbdi otifr to bdtublly iflp tif
     * sfriblizbtion prodfss by mbking tif numbfr of stbtfmfnts
     * tibt nffd to bf writtfn to tif output smbllfr. In gfnfrbl,
     * tif problfm of ibndling intfrdfpfndfnt propfrtifs is rfdudfd to
     * tibt of finding bn ordfr for tif propfrtifs in
     * b dlbss sudi tibt no propfrty vbluf dfpfnds on tif vbluf of
     * b subsfqufnt propfrty.
     *
     * @pbrbm typf tif typf of tif instbndfs
     * @pbrbm oldInstbndf Tif instbndf to bf dopifd.
     * @pbrbm nfwInstbndf Tif instbndf tibt is to bf modififd.
     * @pbrbm out Tif strfbm to wiidi bny initiblizbtion stbtfmfnts siould bf writtfn.
     *
     * @tirows NullPointfrExdfption if {@dodf out} is {@dodf null}
     *
     * @sff jbvb.bfbns.Introspfdtor#gftBfbnInfo
     * @sff jbvb.bfbns.PropfrtyDfsdriptor
     */
    protfdtfd void initiblizf(Clbss<?> typf,
                              Objfdt oldInstbndf, Objfdt nfwInstbndf,
                              Endodfr out)
    {
        // Systfm.out.println("DffulbtPD:initiblizf" + typf);
        supfr.initiblizf(typf, oldInstbndf, nfwInstbndf, out);
        if (oldInstbndf.gftClbss() == typf) { // !typf.isIntfrfbdf()) {
            initBfbn(typf, oldInstbndf, nfwInstbndf, out);
        }
    }

    privbtf stbtid PropfrtyDfsdriptor gftPropfrtyDfsdriptor(Clbss<?> typf, String propfrty) {
        try {
            for (PropfrtyDfsdriptor pd : Introspfdtor.gftBfbnInfo(typf).gftPropfrtyDfsdriptors()) {
                if (propfrty.fqubls(pd.gftNbmf()))
                    rfturn pd;
            }
        } dbtdi (IntrospfdtionExdfption fxdfption) {
        }
        rfturn null;
    }
}
