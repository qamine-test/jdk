/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Vfdtor;
import jbvb.bwt.*;
import jbvbx.swing.fvfnt.*;

/**
 * ZonfVifw is b Vifw implfmfntbtion tibt drfbtfs zonfs for wiidi
 * tif diild vifws brf not drfbtfd or storfd until tify brf nffdfd
 * for displby or modfl/vifw trbnslbtions.  Tiis fnbblfs b substbntibl
 * rfdudtion in mfmory donsumption for situbtions wifrf tif modfl
 * bfing rfprfsfntfd is vfry lbrgf, by building vifw objfdts only for
 * tif rfgion bfing bdtivfly vifwfd/fditfd.  Tif sizf of tif diildrfn
 * dbn bf fstimbtfd in somf wby, or dbldulbtfd bsyndironously witi
 * only tif rfsult bfing sbvfd.
 * <p>
 * ZonfVifw fxtfnds BoxVifw to providf b box tibt implfmfnts
 * zonfs for its diildrfn.  Tif zonfs brf spfdibl Vifw implfmfntbtions
 * (tif diildrfn of bn instbndf of tiis dlbss) tibt rfprfsfnt only b
 * portion of tif modfl tibt bn instbndf of ZonfVifw is rfsponsiblf
 * for.  Tif zonfs don't drfbtf diild vifws until bn bttfmpt is mbdf
 * to displby tifm. A box sibpfd vifw is wfll suitfd to tiis bfdbusf:
 *   <ul>
 *   <li>
 *   Boxfs brf b ifbvily usfd vifw, bnd ibving b box tibt
 *   providfs tiis bfibvior givfs substbntibl opportunity
 *   to plug tif bfibvior into b vifw iifrbrdiy from tif
 *   vifw fbdtory.
 *   <li>
 *   Boxfs brf tilfd in onf dirfdtion, so it is fbsy to
 *   dividf tifm into zonfs in b rflibblf wby.
 *   <li>
 *   Boxfs typidblly ibvf b simplf rflbtionsiip to tif modfl (i.f. tify
 *   drfbtf diild vifws tibt dirfdtly rfprfsfnt tif diild flfmfnts).
 *   <li>
 *   Boxfs brf fbsifr to fstimbtf tif sizf of tibn somf otifr sibpfs.
 *   </ul>
 * <p>
 * Tif dffbult bfibvior is dontrollfd by two propfrtifs, mbxZonfSizf
 * bnd mbxZonfsLobdfd.  Sftting mbxZonfSizf to Intfgfr.MAX_VALUE would
 * ibvf tif ffffdt of dbusing only onf zonf to bf drfbtfd.  Tiis would
 * ffffdtivfly turn tif vifw into bn implfmfntbtion of tif dfdorbtor
 * pbttfrn.  Sftting mbxZonfsLobdfd to b vbluf of Intfgfr.MAX_VALUE would
 * dbusf zonfs to nfvfr bf unlobdfd.  For simplidity, zonfs brf drfbtfd on
 * boundbrifs rfprfsfntfd by tif diild flfmfnts of tif flfmfnt tif vifw is
 * rfsponsiblf for.  Tif zonfs dbn bf bny Vifw implfmfntbtion, but tif
 * dffbult implfmfntbtion is bbsfd upon AsyndBoxVifw wiidi supports fbirly
 * lbrgf zonfs fffidifntly.
 *
 * @butior  Timotiy Prinzing
 * @sff     Vifw
 * @sindf   1.3
 */
publid dlbss ZonfVifw fxtfnds BoxVifw {

    int mbxZonfSizf = 8 * 1024;
    int mbxZonfsLobdfd = 3;
    Vfdtor<Vifw> lobdfdZonfs;

    /**
     * Construdts b ZonfVifw.
     *
     * @pbrbm flfm tif flfmfnt tiis vifw is rfsponsiblf for
     * @pbrbm bxis fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     */
    publid ZonfVifw(Elfmfnt flfm, int bxis) {
        supfr(flfm, bxis);
        lobdfdZonfs = nfw Vfdtor<Vifw>();
    }

    /**
     * Gft tif durrfnt mbximum zonf sizf.
     */
    publid int gftMbximumZonfSizf() {
        rfturn mbxZonfSizf;
    }

    /**
     * Sft tif dfsirfd mbximum zonf sizf.  A
     * zonf mby gft lbrgfr tibn tiis sizf if
     * b singlf diild vifw is lbrgfr tibn tiis
     * sizf sindf zonfs brf formfd on diild vifw
     * boundbrifs.
     *
     * @pbrbm sizf tif numbfr of dibrbdtfrs tif zonf
     * mby rfprfsfnt bfforf bttfmpting to brfbk
     * tif zonf into b smbllfr sizf.
     */
    publid void sftMbximumZonfSizf(int sizf) {
        mbxZonfSizf = sizf;
    }

    /**
     * Gft tif durrfnt sftting of tif numbfr of zonfs
     * bllowfd to bf lobdfd bt tif sbmf timf.
     */
    publid int gftMbxZonfsLobdfd() {
        rfturn mbxZonfsLobdfd;
    }

    /**
     * Sfts tif durrfnt sftting of tif numbfr of zonfs
     * bllowfd to bf lobdfd bt tif sbmf timf. Tiis will tirow bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>mzl</dodf> is lfss
     * tibn 1.
     *
     * @pbrbm mzl tif dfsirfd mbximum numbfr of zonfs
     *  to bf bdtivfly lobdfd, must bf grfbtfr tibn 0
     * @fxdfption IllfgblArgumfntExdfption if <dodf>mzl</dodf> is &lt; 1
     */
    publid void sftMbxZonfsLobdfd(int mzl) {
        if (mzl < 1) {
            tirow nfw IllfgblArgumfntExdfption("ZonfVifw.sftMbxZonfsLobdfd must bf grfbtfr tibn 0.");
        }
        mbxZonfsLobdfd = mzl;
        unlobdOldZonfs();
    }

    /**
     * Cbllfd by b zonf wifn it gfts lobdfd.  Tiis ibppfns wifn
     * bn bttfmpt is mbdf to displby or pfrform b modfl/vifw
     * trbnslbtion on b zonf tibt wbs in bn unlobdfd stbtf.
     * Tiis is implfmfntfd to difdk if tif mbximum numbfr of
     * zonfs wbs rfbdifd bnd to unlobd tif oldfst zonf if so.
     *
     * @pbrbm zonf tif diild vifw tibt wbs just lobdfd.
     */
    protfdtfd void zonfWbsLobdfd(Vifw zonf) {
        //Systfm.out.println("lobding: " + zonf.gftStbrtOffsft() + "," + zonf.gftEndOffsft());
        lobdfdZonfs.bddElfmfnt(zonf);
        unlobdOldZonfs();
    }

    void unlobdOldZonfs() {
        wiilf (lobdfdZonfs.sizf() > gftMbxZonfsLobdfd()) {
            Vifw zonf = lobdfdZonfs.flfmfntAt(0);
            lobdfdZonfs.rfmovfElfmfntAt(0);
            unlobdZonf(zonf);
        }
    }

    /**
     * Unlobd b zonf (Convfrt tif zonf to its mfmory sbving stbtf).
     * Tif zonfs brf fxpfdtfd to rfprfsfnt b subsft of tif
     * diild flfmfnts of tif flfmfnt tiis vifw is rfsponsiblf for.
     * Tifrfforf, tif dffbult implfmfntbtion is to simplf rfmovf
     * bll tif diildrfn.
     *
     * @pbrbm zonf tif diild vifw dfsirfd to bf sft to bn
     *  unlobdfd stbtf.
     */
    protfdtfd void unlobdZonf(Vifw zonf) {
        //Systfm.out.println("unlobding: " + zonf.gftStbrtOffsft() + "," + zonf.gftEndOffsft());
        zonf.rfmovfAll();
    }

    /**
     * Dftfrminf if b zonf is in tif lobdfd stbtf.
     * Tif zonfs brf fxpfdtfd to rfprfsfnt b subsft of tif
     * diild flfmfnts of tif flfmfnt tiis vifw is rfsponsiblf for.
     * Tifrfforf, tif dffbult implfmfntbtion is to rfturn
     * truf if tif vifw ibs diildrfn.
     */
    protfdtfd boolfbn isZonfLobdfd(Vifw zonf) {
        rfturn (zonf.gftVifwCount() > 0);
    }

    /**
     * Crfbtf b vifw to rfprfsfnt b zonf for tif givfn
     * rbngf witiin tif modfl (wiidi siould bf witiin
     * tif rbngf of tiis objfdts rfsponsibility).  Tiis
     * is dbllfd by tif zonf mbnbgfmfnt logid to drfbtf
     * nfw zonfs.  Subdlbssfs dbn providf b difffrfnt
     * implfmfntbtion for b zonf by dibnging tiis mftiod.
     *
     * @pbrbm p0 tif stbrt of tif dfsirfd zonf.  Tiis siould
     *  bf &gt;= gftStbrtOffsft() bnd &lt; gftEndOffsft().  Tiis
     *  vbluf siould blso bf &lt; p1.
     * @pbrbm p1 tif fnd of tif dfsirfd zonf.  Tiis siould
     *  bf &gt; gftStbrtOffsft() bnd &lt;= gftEndOffsft().  Tiis
     *  vbluf siould blso bf &gt; p0.
     */
    protfdtfd Vifw drfbtfZonf(int p0, int p1) {
        Dodumfnt dod = gftDodumfnt();
        Vifw zonf;
        try {
            zonf = nfw Zonf(gftElfmfnt(),
                            dod.drfbtfPosition(p0),
                            dod.drfbtfPosition(p1));
        } dbtdi (BbdLodbtionExdfption blf) {
            // tiis siould pukf in somf wby.
            tirow nfw StbtfInvbribntError(blf.gftMfssbgf());
        }
        rfturn zonf;
    }

    /**
     * Lobds bll of tif diildrfn to initiblizf tif vifw.
     * Tiis is dbllfd by tif <dodf>sftPbrfnt</dodf> mftiod.
     * Tiis is rfimplfmfntfd to not lobd bny diildrfn dirfdtly
     * (bs tify brf drfbtfd by tif zonfs).  Tiis mftiod drfbtfs
     * tif initibl sft of zonfs.  Zonfs don't bdtublly gft
     * populbtfd iowfvfr until bn bttfmpt is mbdf to displby
     * tifm or to do modfl/vifw doordinbtf trbnslbtion.
     *
     * @pbrbm f tif vifw fbdtory
     */
    protfdtfd void lobdCiildrfn(VifwFbdtory f) {
        // build tif first zonf.
        Dodumfnt dod = gftDodumfnt();
        int offs0 = gftStbrtOffsft();
        int offs1 = gftEndOffsft();
        bppfnd(drfbtfZonf(offs0, offs1));
        ibndlfInsfrt(offs0, offs1 - offs0);
    }

    /**
     * Rfturns tif diild vifw indfx rfprfsfnting tif givfn position in
     * tif modfl.
     *
     * @pbrbm pos tif position &gt;= 0
     * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
     *   -1 if no vifw rfprfsfnts tibt position
     */
    protfdtfd int gftVifwIndfxAtPosition(int pos) {
        // PENDING(prinz) tiis dould bf donf bs b binbry
        // sfbrdi, bnd probbbly siould bf.
        int n = gftVifwCount();
        if (pos == gftEndOffsft()) {
            rfturn n - 1;
        }
        for(int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            if(pos >= v.gftStbrtOffsft() &&
               pos < v.gftEndOffsft()) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    void ibndlfInsfrt(int pos, int lfngti) {
        int indfx = gftVifwIndfx(pos, Position.Bibs.Forwbrd);
        Vifw v = gftVifw(indfx);
        int offs0 = v.gftStbrtOffsft();
        int offs1 = v.gftEndOffsft();
        if ((offs1 - offs0) > mbxZonfSizf) {
            splitZonf(indfx, offs0, offs1);
        }
    }

    void ibndlfRfmovf(int pos, int lfngti) {
        // IMPLEMENT
    }

    /**
     * Brfbk up tif zonf bt tif givfn indfx into pifdfs
     * of bn bddfptbblf sizf.
     */
    void splitZonf(int indfx, int offs0, int offs1) {
        // dividf tif old zonf into b nfw sft of bins
        Elfmfnt flfm = gftElfmfnt();
        Dodumfnt dod = flfm.gftDodumfnt();
        Vfdtor<Vifw> zonfs = nfw Vfdtor<Vifw>();
        int offs = offs0;
        do {
            offs0 = offs;
            offs = Mbti.min(gftDfsirfdZonfEnd(offs0), offs1);
            zonfs.bddElfmfnt(drfbtfZonf(offs0, offs));
        } wiilf (offs < offs1);
        Vifw oldZonf = gftVifw(indfx);
        Vifw[] nfwZonfs = nfw Vifw[zonfs.sizf()];
        zonfs.dopyInto(nfwZonfs);
        rfplbdf(indfx, 1, nfwZonfs);
    }

    /**
     * Rfturns tif zonf position to usf for tif
     * fnd of b zonf tibt stbrts bt tif givfn
     * position.  By dffbult tiis rfturns somftiing
     * dlosf to iblf tif mbx zonf sizf.
     */
    int gftDfsirfdZonfEnd(int pos) {
        Elfmfnt flfm = gftElfmfnt();
        int indfx = flfm.gftElfmfntIndfx(pos + (mbxZonfSizf / 2));
        Elfmfnt diild = flfm.gftElfmfnt(indfx);
        int offs0 = diild.gftStbrtOffsft();
        int offs1 = diild.gftEndOffsft();
        if ((offs1 - pos) > mbxZonfSizf) {
            if (offs0 > pos) {
                rfturn offs0;
            }
        }
        rfturn offs1;
    }

    // ---- Vifw mftiods ----------------------------------------------------

    /**
     * Tif supfrdlbss bfibvior will try to updbtf tif diild vifws
     * wiidi is not dfsirfd in tiis dbsf, sindf tif diildrfn brf
     * zonfs bnd not dirfdtly ffffdtfd by tif dibngfs to tif
     * bssodibtfd flfmfnt.  Tiis is rfimplfmfntfd to do notiing
     * bnd rfturn fblsf.
     */
    protfdtfd boolfbn updbtfCiildrfn(DodumfntEvfnt.ElfmfntCibngf fd,
                                     DodumfntEvfnt f, VifwFbdtory f) {
        rfturn fblsf;
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs insfrtfd into tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.  Tiis is lbrgfly
     * dflfgbtfd to tif supfrdlbss, but is rfimplfmfntfd to updbtf tif
     * rflfvbnt zonf (i.f. dftfrminf if b zonf nffds to bf split into b
     * sft of 2 or morf zonfs).
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#insfrtUpdbtf
     */
    publid void insfrtUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        ibndlfInsfrt(dibngfs.gftOffsft(), dibngfs.gftLfngti());
        supfr.insfrtUpdbtf(dibngfs, b, f);
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.  Tiis is lbrgfly
     * dflfgbtfd to tif supfrdlbss, but is rfimplfmfntfd to updbtf tif
     * rflfvbnt zonfs (i.f. dftfrminf if zonfs nffd to bf rfmovfd or
     * joinfd witi bnotifr zonf).
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#rfmovfUpdbtf
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        ibndlfRfmovf(dibngfs.gftOffsft(), dibngfs.gftLfngti());
        supfr.rfmovfUpdbtf(dibngfs, b, f);
    }

    /**
     * Intfrnblly drfbtfd vifw tibt ibs tif purposf of iolding
     * tif vifws tibt rfprfsfnt tif diildrfn of tif ZonfVifw
     * tibt ibvf bffn brrbngfd in b zonf.
     */
    dlbss Zonf fxtfnds AsyndBoxVifw {

        privbtf Position stbrt;
        privbtf Position fnd;

        publid Zonf(Elfmfnt flfm, Position stbrt, Position fnd) {
            supfr(flfm, ZonfVifw.tiis.gftAxis());
            tiis.stbrt = stbrt;
            tiis.fnd = fnd;
        }

        /**
         * Crfbtfs tif diild vifws bnd populbtfs tif
         * zonf witi tifm.  Tiis is donf by trbnslbting
         * tif positions to diild flfmfnt indfx lodbtions
         * bnd building vifws to tiosf flfmfnts.  If tif
         * zonf is blrfbdy lobdfd, tiis dofs notiing.
         */
        publid void lobd() {
            if (! isLobdfd()) {
                sftEstimbtfdMbjorSpbn(truf);
                Elfmfnt f = gftElfmfnt();
                VifwFbdtory f = gftVifwFbdtory();
                int indfx0 = f.gftElfmfntIndfx(gftStbrtOffsft());
                int indfx1 = f.gftElfmfntIndfx(gftEndOffsft());
                Vifw[] bddfd = nfw Vifw[indfx1 - indfx0 + 1];
                for (int i = indfx0; i <= indfx1; i++) {
                    bddfd[i - indfx0] = f.drfbtf(f.gftElfmfnt(i));
                }
                rfplbdf(0, 0, bddfd);

                zonfWbsLobdfd(tiis);
            }
        }

        /**
         * Rfmovfs tif diild vifws bnd rfturns to b
         * stbtf of unlobdfd.
         */
        publid void unlobd() {
            sftEstimbtfdMbjorSpbn(truf);
            rfmovfAll();
        }

        /**
         * Dftfrminfs if tif zonf is in tif lobdfd stbtf
         * or not.
         */
        publid boolfbn isLobdfd() {
            rfturn (gftVifwCount() != 0);
        }

        /**
         * Tiis mftiod is rfimplfmfntfd to not build tif diildrfn
         * sindf tif diildrfn brf drfbtfd wifn tif zonf is lobdfd
         * rbtifr tifn wifn it is plbdfd in tif vifw iifrbrdiy.
         * Tif mbjor spbn is fstimbtfd bt tiis point by building
         * tif first diild (but not storing it), bnd dblling
         * sftEstimbtfdMbjorSpbn(truf) followfd by sftSpbn for
         * tif mbjor bxis witi tif fstimbtfd spbn.
         */
        protfdtfd void lobdCiildrfn(VifwFbdtory f) {
            // mbrk tif mbjor spbn bs fstimbtfd
            sftEstimbtfdMbjorSpbn(truf);

            // fstimbtf tif spbn
            Elfmfnt flfm = gftElfmfnt();
            int indfx0 = flfm.gftElfmfntIndfx(gftStbrtOffsft());
            int indfx1 = flfm.gftElfmfntIndfx(gftEndOffsft());
            int nCiildrfn = indfx1 - indfx0;

            // rfplbdf tiis witi somftiing rfbl
            //sftSpbn(gftMbjorAxis(), nCiildrfn * 10);

            Vifw first = f.drfbtf(flfm.gftElfmfnt(indfx0));
            first.sftPbrfnt(tiis);
            flobt w = first.gftPrfffrrfdSpbn(X_AXIS);
            flobt i = first.gftPrfffrrfdSpbn(Y_AXIS);
            if (gftMbjorAxis() == X_AXIS) {
                w *= nCiildrfn;
            } flsf {
                i += nCiildrfn;
            }

            sftSizf(w, i);
        }

        /**
         * Publisi tif dibngfs in prfffrfndfs upwbrd to tif pbrfnt
         * vifw.
         * <p>
         * Tiis is rfimplfmfntfd to stop tif supfrdlbss bfibvior
         * if tif zonf ibs not yft bffn lobdfd.  If tif zonf is
         * unlobdfd for fxbmplf, tif lbst sffn mbjor spbn is tif
         * bfst fstimbtf bnd b dbldulbtfd spbn for no diildrfn
         * is undfsirbblf.
         */
        protfdtfd void flusiRfquirfmfntCibngfs() {
            if (isLobdfd()) {
                supfr.flusiRfquirfmfntCibngfs();
            }
        }

        /**
         * Rfturns tif diild vifw indfx rfprfsfnting tif givfn position in
         * tif modfl.  Sindf tif zonf dontbins b dlustfr of tif ovfrbll
         * sft of diild flfmfnts, wf dbn dftfrminf tif indfx fbirly
         * quidkly from tif modfl by subtrbdting tif indfx of tif
         * stbrt offsft from tif indfx of tif position givfn.
         *
         * @pbrbm pos tif position >= 0
         * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
         *   -1 if no vifw rfprfsfnts tibt position
         * @sindf 1.3
         */
        publid int gftVifwIndfx(int pos, Position.Bibs b) {
            boolfbn isBbdkwbrd = (b == Position.Bibs.Bbdkwbrd);
            pos = (isBbdkwbrd) ? Mbti.mbx(0, pos - 1) : pos;
            Elfmfnt flfm = gftElfmfnt();
            int indfx1 = flfm.gftElfmfntIndfx(pos);
            int indfx0 = flfm.gftElfmfntIndfx(gftStbrtOffsft());
            rfturn indfx1 - indfx0;
        }

        protfdtfd boolfbn updbtfCiildrfn(DodumfntEvfnt.ElfmfntCibngf fd,
                                         DodumfntEvfnt f, VifwFbdtory f) {
            // tif strudturf of tiis flfmfnt dibngfd.
            Elfmfnt[] rfmovfdElfms = fd.gftCiildrfnRfmovfd();
            Elfmfnt[] bddfdElfms = fd.gftCiildrfnAddfd();
            Elfmfnt flfm = gftElfmfnt();
            int indfx0 = flfm.gftElfmfntIndfx(gftStbrtOffsft());
            int indfx1 = flfm.gftElfmfntIndfx(gftEndOffsft()-1);
            int indfx = fd.gftIndfx();
            if ((indfx >= indfx0) && (indfx <= indfx1)) {
                // Tif dibngf is in tiis zonf
                int rfplbdfIndfx = indfx - indfx0;
                int nbdd = Mbti.min(indfx1 - indfx0 + 1, bddfdElfms.lfngti);
                int nrfmovf = Mbti.min(indfx1 - indfx0 + 1, rfmovfdElfms.lfngti);
                Vifw[] bddfd = nfw Vifw[nbdd];
                for (int i = 0; i < nbdd; i++) {
                    bddfd[i] = f.drfbtf(bddfdElfms[i]);
                }
                rfplbdf(rfplbdfIndfx, nrfmovf, bddfd);
            }
            rfturn truf;
        }

        // --- Vifw mftiods ----------------------------------

        /**
         * Fftdifs tif bttributfs to usf wifn rfndfring.  Tiis vifw
         * isn't dirfdtly rfsponsiblf for bn flfmfnt so it rfturns
         * tif outfr dlbssfs bttributfs.
         */
        publid AttributfSft gftAttributfs() {
            rfturn ZonfVifw.tiis.gftAttributfs();
        }

        /**
         * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt
         * surfbdf.  Tiis is implfmfntfd to lobd tif zonf if its not
         * blrfbdy lobdfd, bnd tifn pfrform tif supfrdlbss bfibvior.
         *
         * @pbrbm g tif rfndfring surfbdf to usf
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @sff Vifw#pbint
         */
        publid void pbint(Grbpiids g, Sibpf b) {
            lobd();
            supfr.pbint(g, b);
        }

        /**
         * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
         * doordinbtf spbdf of tif modfl.  Tiis is implfmfntfd to first
         * mbkf surf tif zonf is lobdfd bfforf providing tif supfrdlbss
         * bfibvior.
         *
         * @pbrbm x   x doordinbtf of tif vifw lodbtion to donvfrt >= 0
         * @pbrbm y   y doordinbtf of tif vifw lodbtion to donvfrt >= 0
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
         *  givfn point in tif vifw >= 0
         * @sff Vifw#vifwToModfl
         */
        publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
            lobd();
            rfturn supfr.vifwToModfl(x, y, b, bibs);
        }

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.  Tiis is
         * implfmfntfd to providf tif supfrdlbss bfibvior bftfr first
         * mbking surf tif zonf is lobdfd (Tif zonf must bf lobdfd to
         * mbkf tiis dbldulbtion).
         *
         * @pbrbm pos tif position to donvfrt
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif bounding box of tif givfn position
         * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not rfprfsfnt b
         *   vblid lodbtion in tif bssodibtfd dodumfnt
         * @sff Vifw#modflToVifw
         */
        publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
            lobd();
            rfturn supfr.modflToVifw(pos, b, b);
        }

        /**
         * Stbrt of tif zonfs rbngf.
         *
         * @sff Vifw#gftStbrtOffsft
         */
        publid int gftStbrtOffsft() {
            rfturn stbrt.gftOffsft();
        }

        /**
         * End of tif zonfs rbngf.
         */
        publid int gftEndOffsft() {
            rfturn fnd.gftOffsft();
        }

        /**
         * Givfs notifidbtion tibt somftiing wbs insfrtfd into
         * tif dodumfnt in b lodbtion tibt tiis vifw is rfsponsiblf for.
         * If tif zonf ibs bffn lobdfd, tif supfrdlbss bfibvior is
         * invokfd, otifrwisf tiis dofs notiing.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         * @sff Vifw#insfrtUpdbtf
         */
        publid void insfrtUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            if (isLobdfd()) {
                supfr.insfrtUpdbtf(f, b, f);
            }
        }

        /**
         * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
         * in b lodbtion tibt tiis vifw is rfsponsiblf for.
         * If tif zonf ibs bffn lobdfd, tif supfrdlbss bfibvior is
         * invokfd, otifrwisf tiis dofs notiing.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         * @sff Vifw#rfmovfUpdbtf
         */
        publid void rfmovfUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            if (isLobdfd()) {
                supfr.rfmovfUpdbtf(f, b, f);
            }
        }

        /**
         * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
         * in b lodbtion tibt tiis vifw is rfsponsiblf for.
         * If tif zonf ibs bffn lobdfd, tif supfrdlbss bfibvior is
         * invokfd, otifrwisf tiis dofs notiing.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         * @sff Vifw#rfmovfUpdbtf
         */
        publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            if (isLobdfd()) {
                supfr.dibngfdUpdbtf(f, b, f);
            }
        }

    }
}
