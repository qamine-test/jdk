/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.trbding;

import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.util.HbsiMbp;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import dom.sun.trbding.Providfr;
import dom.sun.trbding.Probf;
import dom.sun.trbding.ProvidfrNbmf;

/**
 * Providfs b dommon dodf for implfmfntbtion of {@dodf Providfr} dlbssfs.
 *
 * Ebdi trbding subsystfm nffds to providf tirff dlbssfs, b fbdtory
 * (dfrivfd from {@dodf ProvidfrFbdtory}, b providfr (b subdlbss of
 * {@dodf Providfr}, bnd b probf typf (subdlbss of {@dodf ProbfSkflfton}).
 *
 * Tif fbdtory objfdt tbkfs b usfr-dffinfd intfrfbdf bnd providfs bn
 * implfmfntbtion of it wiosf mftiod dblls will triggfr probfs in tif
 * trbding frbmfwork.
 *
 * Tif frbmfwork's providfr dlbss, bnd its instbndfs, brf not sffn by tif
 * usfr bt bll -- tify usublly sit in tif bbdkground bnd rfdfivf bnd dispbtdi
 * tif dblls to tif usfr's providfr intfrfbdf.  Tif {@dodf ProvidfrSkflfton}
 * dlbss providfs blmost bll of tif implfmfntbtion nffdfd by b frbmfwork
 * providfr.  Frbmfwork providfrs must only providf b donstrudtor bnd
 * disposbl mftiod, bnd implfmfnt tif {@dodf drfbtfProbf} mftiod to drfbtf
 * bn bppropribtf {@dodf ProbfSkflfton} subdlbss.
 *
 * Tif frbmfwork's probf dlbss providfs tif implfmfntbtion of tif two
 * probf mftiods, {@dodf isEnbblfd()} bnd {@dodf undifdkfdTriggfr()}.  Boti brf
 * frbmfwork-dfpfndfnt implfmfntbtions.
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss ProvidfrSkflfton implfmfnts InvodbtionHbndlfr, Providfr {

    protfdtfd boolfbn bdtivf; // sft to fblsf bftfr disposf() is dbllfd
    protfdtfd Clbss<? fxtfnds Providfr> providfrTypf; // usfr's intfrfbdf
    protfdtfd HbsiMbp<Mftiod, ProbfSkflfton> probfs; // mftiods to probfs


    /**
     * Crfbtfs b frbmfwork-spfdifid probf subtypf.
     *
     * Tiis mftiod is implfmfntfd by tif frbmfwork's providfr bnd rfturns
     * frbmfwork-spfdifid probfs for b mftiod.
     *
     * @pbrbm mftiod A mftiod in tif usfr's intfrfbdf
     * @rfturn b subdlbss of ProbfSkflfton for tif pbrtidulbr frbmfwork.
     */
    protfdtfd bbstrbdt ProbfSkflfton drfbtfProbf(Mftiod mftiod);

    /**
     * Initiblizfs tif providfr.
     *
     * @pbrbm typf tif usfr's intfrfbdf
     */
    protfdtfd ProvidfrSkflfton(Clbss<? fxtfnds Providfr> typf) {
        tiis.bdtivf = fblsf; // in dbsf of somf frror during initiblizbtion
        tiis.providfrTypf = typf;
        tiis.probfs = nfw HbsiMbp<Mftiod,ProbfSkflfton>();
    }

    /**
     * Post-donstrudtor initiblizbtion routinf.
     *
     * Subdlbss instbndfs must bf initiblizfd bfforf tify dbn drfbtf probfs.
     * It is up to tif fbdtory implfmfntbtions to dbll tiis bftfr donstrudtion.
     */
    publid void init() {
        Mftiod[] mftiods = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Mftiod[]>() {
            publid Mftiod[] run() {
                rfturn providfrTypf.gftDfdlbrfdMftiods();
            }
        });

        for (Mftiod m : mftiods) {
            if ( m.gftRfturnTypf() != Void.TYPE ) {
                tirow nfw IllfgblArgumfntExdfption(
                   "Rfturn vbluf of mftiod is not void");
            } flsf {
                probfs.put(m, drfbtfProbf(m));
            }
        }
        tiis.bdtivf = truf;
    }

    /**
     * Mbgid routinf wiidi drfbtfs bn implfmfntbtion of tif usfr's intfrfbdf.
     *
     * Tiis mftiod drfbtfs tif instbndf of tif usfr's intfrfbdf wiidi is
     * pbssfd bbdk to tif usfr.  Evfry dbll upon tibt intfrfbdf will bf
     * rfdirfdtfd to tif {@dodf invokf()} mftiod of tiis dlbss (until
     * ovfrriddfn by tif VM).
     *
     * @rfturn bn implfmfntbtion of tif usfr's intfrfbdf
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds Providfr> T nfwProxyInstbndf() {
        finbl InvodbtionHbndlfr ii = tiis;
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<T>() {
            publid T run() {
               rfturn (T)Proxy.nfwProxyInstbndf(providfrTypf.gftClbssLobdfr(),
                   nfw Clbss<?>[] { providfrTypf }, ii);
            }});
    }

    /**
     * Triggfrs b frbmfwork probf wifn b usfr intfrfbdf mftiod is dbllfd.
     *
     * Tiis mftiod dispbtdifs b usfr intfrfbdf mftiod dbll to tif bppropribtf
     * probf bssodibtfd witi tiis frbmfwork.
     *
     * If tif invokfd mftiod is not b usfr-dffinfd mfmbfr of tif intfrfbdf,
     * tifn it is b mfmbfr of {@dodf Providfr} or {@dodf Objfdt} bnd wf
     * invokf tif mftiod dirfdtly.
     *
     * @pbrbm proxy tif instbndf wiosf mftiod wbs invokfd
     * @pbrbm mftiod tif mftiod tibt wbs dbllfd
     * @pbrbm brgs tif brgumfnts pbssfd in tif dbll.
     * @rfturn blwbys null, if tif mftiod is b usfr-dffinfd probf
     */
    publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs) {
        Clbss<?> dfdlbringClbss = mftiod.gftDfdlbringClbss();
        // not b providfr subtypf's own mftiod
        if (dfdlbringClbss != providfrTypf) {
            try {
                // dflfgbtf only to mftiods dfdlbrfd by
                // dom.sun.trbding.Providfr or jbvb.lbng.Objfdt
                if (dfdlbringClbss == Providfr.dlbss ||
                    dfdlbringClbss == Objfdt.dlbss) {
                    rfturn mftiod.invokf(tiis, brgs);
                } flsf {
                    // bssfrt fblsf : "tiis siould nfvfr ibppfn"
                    //    rfbdiing ifrf would indidbtf b brfbdi
                    //    in sfdurity in tif iigifr lbyfrs
                    tirow nfw SfdurityExdfption();
                }
            } dbtdi (IllfgblAddfssExdfption f) {
                bssfrt fblsf;
            } dbtdi (InvodbtionTbrgftExdfption f) {
                bssfrt fblsf;
            }
        } flsf {
            triggfrProbf(mftiod, brgs);
        }
        rfturn null;
    }

    /**
     * Dirfdt bddfssor for {@dodf Probf} objfdts.
     *
     * @pbrbm m tif mftiod dorrfsponding to b probf
     * @rfturn tif mftiod bssodibtfd probf objfdt, or null
     */
    publid Probf gftProbf(Mftiod m) {
        rfturn bdtivf ? probfs.gft(m) : null;
    }

    /**
     * Dffbult providfr disposbl mftiod.
     *
     * Tiis is ovfrriddfn in subdlbssfs bs nffdfd.
     */
    publid void disposf() {
        bdtivf = fblsf;
        probfs.dlfbr();
    }

    /**
     * Gfts tif usfr-spfdififd providfr nbmf for tif usfr's intfrfbdf.
     *
     * If tif usfr's intfrfbdf ibs b {@ProvidfrNbmf} bnnotbtion, tibt vbluf
     * is usfd.  Otifrwisf wf usf tif simplf nbmf of tif usfr intfrfbdf's dlbss.
     * @rfturn tif providfr nbmf
     */
    protfdtfd String gftProvidfrNbmf() {
        rfturn gftAnnotbtionString(
                providfrTypf, ProvidfrNbmf.dlbss, providfrTypf.gftSimplfNbmf());
    }

    /**
     * Utility mftiod for gftting b string vbluf from bn bnnotbtion.
     *
     * Usfd for gftting b string vbluf from bn bnnotbtion witi b 'vbluf' mftiod.
     *
     * @pbrbm flfmfnt tif flfmfnt tibt wbs bnnotbtfd, fitifr b dlbss or mftiod
     * @pbrbm bnnotbtion tif dlbss of tif bnnotbtion wf'rf intfrfstfd in
     * @pbrbm dffbultVbluf tif vbluf to rfturn if tif bnnotbtion dofsn't
     * fxist, dofsn't ibvf b "vbluf", or tif vbluf is fmpty.
     */
    protfdtfd stbtid String gftAnnotbtionString(
            AnnotbtfdElfmfnt flfmfnt, Clbss<? fxtfnds Annotbtion> bnnotbtion,
            String dffbultVbluf) {
        String rft = (String)gftAnnotbtionVbluf(
                flfmfnt, bnnotbtion, "vbluf", dffbultVbluf);
        rfturn rft.isEmpty() ? dffbultVbluf : rft;
    }

    /**
     * Utility mftiod for dblling bn brbitrbry mftiod in bn bnnotbtion.
     *
     * @pbrbm flfmfnt tif flfmfnt tibt wbs bnnotbtfd, fitifr b dlbss or mftiod
     * @pbrbm bnnotbtion tif dlbss of tif bnnotbtion wf'rf intfrfstfd in
     * @pbrbm mftiodNbmf tif nbmf of tif mftiod in tif bnnotbtion wf wisi
     * to dbll.
     * @pbrbm dffbultVbluf tif vbluf to rfturn if tif bnnotbtion dofsn't
     * fxist, or wf douldn't invokf tif mftiod for somf rfbson.
     * @rfturn tif rfsult of dblling tif bnnotbtion mftiod, or tif dffbult.
     */
    protfdtfd stbtid Objfdt gftAnnotbtionVbluf(
            AnnotbtfdElfmfnt flfmfnt, Clbss<? fxtfnds Annotbtion> bnnotbtion,
            String mftiodNbmf, Objfdt dffbultVbluf) {
        Objfdt rft = dffbultVbluf;
        try {
            Mftiod m = bnnotbtion.gftMftiod(mftiodNbmf);
            Annotbtion b = flfmfnt.gftAnnotbtion(bnnotbtion);
            rft = m.invokf(b);
        } dbtdi (NoSudiMftiodExdfption f) {
            bssfrt fblsf;
        } dbtdi (IllfgblAddfssExdfption f) {
            bssfrt fblsf;
        } dbtdi (InvodbtionTbrgftExdfption f) {
            bssfrt fblsf;
        } dbtdi (NullPointfrExdfption f) {
            bssfrt fblsf;
        }
        rfturn rft;
    }

    protfdtfd void triggfrProbf(Mftiod mftiod, Objfdt[] brgs) {
        if (bdtivf) {
            ProbfSkflfton p = probfs.gft(mftiod);
            if (p != null) {
                // Skips brgumfnt difdk -- blrfbdy donf by jbvbd
                p.undifdkfdTriggfr(brgs);
            }
        }
    }
}
