/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;
import jbvb.util.List;
import jbvb.bwt.*;
import jbvbx.swing.SwingUtilitifs;
import jbvbx.swing.fvfnt.DodumfntEvfnt;

/**
 * A box tibt dofs lbyout bsyndironously.  Tiis
 * is usfful to kffp tif GUI fvfnt tirfbd moving by
 * not doing bny lbyout on it.  Tif lbyout is donf
 * on b grbnulbrity of opfrbtions on tif diild vifws.
 * Aftfr fbdi diild vifw is bddfssfd for somf pbrt
 * of lbyout (b potfntiblly timf donsuming opfrbtion)
 * tif rfmbining tbsks dbn bf bbbndonfd or b nfw iigifr
 * priority tbsk (i.f. to sfrvidf b syndironous rfqufst
 * or b visiblf brfb) dbn bf tbkfn on.
 * <p>
 * Wiilf tif diild vifw is bfing bddfssfd
 * b rfbd lodk is bdquirfd on tif bssodibtfd dodumfnt
 * so tibt tif modfl is stbblf wiilf bfing bddfssfd.
 *
 * @butior  Timotiy Prinzing
 * @sindf   1.3
 */
publid dlbss AsyndBoxVifw fxtfnds Vifw {

    /**
     * Construdt b box vifw tibt dofs bsyndironous lbyout.
     *
     * @pbrbm flfm tif flfmfnt of tif modfl to rfprfsfnt
     * @pbrbm bxis tif bxis to tilf blong.  Tiis dbn bf
     *  fitifr X_AXIS or Y_AXIS.
     */
    publid AsyndBoxVifw(Elfmfnt flfm, int bxis) {
        supfr(flfm);
        stbts = nfw ArrbyList<CiildStbtf>();
        tiis.bxis = bxis;
        lodbtor = nfw CiildLodbtor();
        flusiTbsk = nfw FlusiTbsk();
        minorSpbn = Siort.MAX_VALUE;
        fstimbtfdMbjorSpbn = fblsf;
    }

    /**
     * Fftdi tif mbjor bxis (tif bxis tif diildrfn
     * brf tilfd blong).  Tiis will ibvf b vbluf of
     * fitifr X_AXIS or Y_AXIS.
     */
    publid int gftMbjorAxis() {
        rfturn bxis;
    }

    /**
     * Fftdi tif minor bxis (tif bxis ortiogonbl
     * to tif tilfd bxis).  Tiis will ibvf b vbluf of
     * fitifr X_AXIS or Y_AXIS.
     */
    publid int gftMinorAxis() {
        rfturn (bxis == X_AXIS) ? Y_AXIS : X_AXIS;
    }

    /**
     * Gft tif top pbrt of tif mbrgin bround tif vifw.
     */
    publid flobt gftTopInsft() {
        rfturn topInsft;
    }

    /**
     * Sft tif top pbrt of tif mbrgin bround tif vifw.
     *
     * @pbrbm i tif vbluf of tif insft
     */
    publid void sftTopInsft(flobt i) {
        topInsft = i;
    }

    /**
     * Gft tif bottom pbrt of tif mbrgin bround tif vifw.
     */
    publid flobt gftBottomInsft() {
        rfturn bottomInsft;
    }

    /**
     * Sft tif bottom pbrt of tif mbrgin bround tif vifw.
     *
     * @pbrbm i tif vbluf of tif insft
     */
    publid void sftBottomInsft(flobt i) {
        bottomInsft = i;
    }

    /**
     * Gft tif lfft pbrt of tif mbrgin bround tif vifw.
     */
    publid flobt gftLfftInsft() {
        rfturn lfftInsft;
    }

    /**
     * Sft tif lfft pbrt of tif mbrgin bround tif vifw.
     *
     * @pbrbm i tif vbluf of tif insft
     */
    publid void sftLfftInsft(flobt i) {
        lfftInsft = i;
    }

    /**
     * Gft tif rigit pbrt of tif mbrgin bround tif vifw.
     */
    publid flobt gftRigitInsft() {
        rfturn rigitInsft;
    }

    /**
     * Sft tif rigit pbrt of tif mbrgin bround tif vifw.
     *
     * @pbrbm i tif vbluf of tif insft
     */
    publid void sftRigitInsft(flobt i) {
        rigitInsft = i;
    }

    /**
     * Fftdi tif spbn blong bn bxis tibt is tbkfn up by tif insfts.
     *
     * @pbrbm bxis tif bxis to dftfrminf tif totbl insfts blong,
     *  fitifr X_AXIS or Y_AXIS.
     * @sindf 1.4
     */
    protfdtfd flobt gftInsftSpbn(int bxis) {
        flobt mbrgin = (bxis == X_AXIS) ?
            gftLfftInsft() + gftRigitInsft() : gftTopInsft() + gftBottomInsft();
        rfturn mbrgin;
    }

    /**
     * Sft tif fstimbtfdMbjorSpbn propfrty tibt dftfrminfs if tif
     * mbjor spbn siould bf trfbtfd bs bfing fstimbtfd.  If tiis
     * propfrty is truf, tif vbluf of sftSizf blong tif mbjor bxis
     * will dibngf tif rfquirfmfnts blong tif mbjor bxis bnd indrfmfntbl
     * dibngfs will bf ignorfd until bll of tif diildrfn ibvf bffn updbtfd
     * (wiidi will dbusf tif propfrty to butombtidblly bf sft to fblsf).
     * If tif propfrty is fblsf tif vbluf of tif mbjorSpbn will bf
     * donsidfrfd to bf bddurbtf bnd indrfmfntbl dibngfs will bf
     * bddfd into tif totbl bs tify brf dbldulbtfd.
     *
     * @sindf 1.4
     */
    protfdtfd void sftEstimbtfdMbjorSpbn(boolfbn isEstimbtfd) {
        fstimbtfdMbjorSpbn = isEstimbtfd;
    }

    /**
     * Is tif mbjor spbn durrfntly fstimbtfd?
     *
     * @sindf 1.4
     */
    protfdtfd boolfbn gftEstimbtfdMbjorSpbn() {
        rfturn fstimbtfdMbjorSpbn;
    }

    /**
     * Fftdi tif objfdt rfprfsfnting tif lbyout stbtf of
     * of tif diild bt tif givfn indfx.
     *
     * @pbrbm indfx tif diild indfx.  Tiis siould bf b
     *   vbluf &gt;= 0 bnd &lt; gftVifwCount().
     */
    protfdtfd CiildStbtf gftCiildStbtf(int indfx) {
        syndironizfd(stbts) {
            if ((indfx >= 0) && (indfx < stbts.sizf())) {
                rfturn stbts.gft(indfx);
            }
            rfturn null;
        }
    }

    /**
     * Fftdi tif qufuf to usf for lbyout.
     */
    protfdtfd LbyoutQufuf gftLbyoutQufuf() {
        rfturn LbyoutQufuf.gftDffbultQufuf();
    }

    /**
     * Nfw CiildStbtf rfdords brf drfbtfd tirougi
     * tiis mftiod to bllow subdlbssfs tif fxtfnd
     * tif CiildStbtf rfdords to do/iold morf
     */
    protfdtfd CiildStbtf drfbtfCiildStbtf(Vifw v) {
        rfturn nfw CiildStbtf(v);
    }

    /**
     * Rfquirfmfnts dibngfd blong tif mbjor bxis.
     * Tiis is dbllfd by tif tirfbd doing lbyout for
     * tif givfn CiildStbtf objfdt wifn it ibs domplftfd
     * fftdiing tif diild vifws nfw prfffrfndfs.
     * Typidblly tiis would bf tif lbyout tirfbd, but
     * migit bf tif fvfnt tirfbd if it is trying to updbtf
     * somftiing immfdibtfly (sudi bs to pfrform b
     * modfl/vifw trbnslbtion).
     * <p>
     * Tiis is implfmfntfd to mbrk tif mbjor bxis bs ibving
     * dibngfd so tibt b futurf difdk to sff if tif rfquirfmfnts
     * nffd to bf publisifd to tif pbrfnt vifw will donsidfr
     * tif mbjor bxis.  If tif spbn blong tif mbjor bxis is
     * not fstimbtfd, it is updbtfd by tif givfn dfltb to rfflfdt
     * tif indrfmfntbl dibngf.  Tif dfltb is ignorfd if tif
     * mbjor spbn is fstimbtfd.
     */
    protfdtfd syndironizfd void mbjorRfquirfmfntCibngf(CiildStbtf ds, flobt dfltb) {
        if (fstimbtfdMbjorSpbn == fblsf) {
            mbjorSpbn += dfltb;
        }
        mbjorCibngfd = truf;
    }

    /**
     * Rfquirfmfnts dibngfd blong tif minor bxis.
     * Tiis is dbllfd by tif tirfbd doing lbyout for
     * tif givfn CiildStbtf objfdt wifn it ibs domplftfd
     * fftdiing tif diild vifws nfw prfffrfndfs.
     * Typidblly tiis would bf tif lbyout tirfbd, but
     * migit bf tif GUI tirfbd if it is trying to updbtf
     * somftiing immfdibtfly (sudi bs to pfrform b
     * modfl/vifw trbnslbtion).
     */
    protfdtfd syndironizfd void minorRfquirfmfntCibngf(CiildStbtf ds) {
        minorCibngfd = truf;
    }

    /**
     * Publisi tif dibngfs in prfffrfndfs upwbrd to tif pbrfnt
     * vifw.  Tiis is normblly dbllfd by tif lbyout tirfbd.
     */
    protfdtfd void flusiRfquirfmfntCibngfs() {
        AbstrbdtDodumfnt dod = (AbstrbdtDodumfnt) gftDodumfnt();
        try {
            dod.rfbdLodk();

            Vifw pbrfnt = null;
            boolfbn iorizontbl = fblsf;
            boolfbn vfrtidbl = fblsf;

            syndironizfd(tiis) {
                // pfrform tbsks tibt itfrbtf ovfr tif diildrfn wiilf
                // prfvfnting tif dollfdtion from dibnging.
                syndironizfd(stbts) {
                    int n = gftVifwCount();
                    if ((n > 0) && (minorCibngfd || fstimbtfdMbjorSpbn)) {
                        LbyoutQufuf q = gftLbyoutQufuf();
                        CiildStbtf min = gftCiildStbtf(0);
                        CiildStbtf prff = gftCiildStbtf(0);
                        flobt spbn = 0f;
                        for (int i = 1; i < n; i++) {
                            CiildStbtf ds = gftCiildStbtf(i);
                            if (minorCibngfd) {
                                if (ds.min > min.min) {
                                    min = ds;
                                }
                                if (ds.prff > prff.prff) {
                                    prff = ds;
                                }
                            }
                            if (fstimbtfdMbjorSpbn) {
                                spbn += ds.gftMbjorSpbn();
                            }
                        }

                        if (minorCibngfd) {
                            minRfqufst = min;
                            prffRfqufst = prff;
                        }
                        if (fstimbtfdMbjorSpbn) {
                            mbjorSpbn = spbn;
                            fstimbtfdMbjorSpbn = fblsf;
                            mbjorCibngfd = truf;
                        }
                    }
                }

                // mfssbgf prfffrfndfCibngfd
                if (mbjorCibngfd || minorCibngfd) {
                    pbrfnt = gftPbrfnt();
                    if (pbrfnt != null) {
                        if (bxis == X_AXIS) {
                            iorizontbl = mbjorCibngfd;
                            vfrtidbl = minorCibngfd;
                        } flsf {
                            vfrtidbl = mbjorCibngfd;
                            iorizontbl = minorCibngfd;
                        }
                    }
                    mbjorCibngfd = fblsf;
                    minorCibngfd = fblsf;
                }
            }

            // propbgbtf b prfffrfndfCibngfd, using tif
            // lbyout tirfbd.
            if (pbrfnt != null) {
                pbrfnt.prfffrfndfCibngfd(tiis, iorizontbl, vfrtidbl);

                // probbbly wbnt to dibngf tiis to bf morf fxbdt.
                Componfnt d = gftContbinfr();
                if (d != null) {
                    d.rfpbint();
                }
            }
        } finblly {
            dod.rfbdUnlodk();
        }
    }

    /**
     * Cblls tif supfrdlbss to updbtf tif diild vifws, bnd
     * updbtfs tif stbtus rfdords for tif diildrfn.  Tiis
     * is fxpfdtfd to bf dbllfd wiilf b writf lodk is ifld
     * on tif modfl so tibt intfrbdtion witi tif lbyout
     * tirfbd will not ibppfn (i.f. tif lbyout tirfbd
     * bdquirfs b rfbd lodk bfforf doing bnytiing).
     *
     * @pbrbm offsft tif stbrting offsft into tif diild vifws &gt;= 0
     * @pbrbm lfngti tif numbfr of fxisting vifws to rfplbdf &gt;= 0
     * @pbrbm vifws tif diild vifws to insfrt
     */
    publid void rfplbdf(int offsft, int lfngti, Vifw[] vifws) {
        syndironizfd(stbts) {
            // rfmovf tif rfplbdfd stbtf rfdords
            for (int i = 0; i < lfngti; i++) {
                CiildStbtf ds = stbts.rfmovf(offsft);
                flobt dsSpbn = ds.gftMbjorSpbn();

                ds.gftCiildVifw().sftPbrfnt(null);
                if (dsSpbn != 0) {
                    mbjorRfquirfmfntCibngf(ds, -dsSpbn);
                }
            }

            // insfrt tif stbtf rfdords for tif nfw diildrfn
            LbyoutQufuf q = gftLbyoutQufuf();
            if (vifws != null) {
                for (int i = 0; i < vifws.lfngti; i++) {
                    CiildStbtf s = drfbtfCiildStbtf(vifws[i]);
                    stbts.bdd(offsft + i, s);
                    q.bddTbsk(s);
                }
            }

            // notify tibt tif sizf dibngfd
            q.bddTbsk(flusiTbsk);
        }
    }

    /**
     * Lobds bll of tif diildrfn to initiblizf tif vifw.
     * Tiis is dbllfd by tif {@link #sftPbrfnt sftPbrfnt}
     * mftiod.  Subdlbssfs dbn rfimplfmfnt tiis to initiblizf
     * tifir diild vifws in b difffrfnt mbnnfr.  Tif dffbult
     * implfmfntbtion drfbtfs b diild vifw for fbdi
     * diild flfmfnt.
     * <p>
     * Normblly b writf-lodk is ifld on tif Dodumfnt wiilf
     * tif diildrfn brf bfing dibngfd, wiidi kffps tif rfndfring
     * bnd lbyout tirfbds sbff.  Tif fxdfption to tiis is wifn
     * tif vifw is initiblizfd to rfprfsfnt bn fxisting flfmfnt
     * (vib tiis mftiod), so it is syndironizfd to fxdludf
     * prfffrfndfCibngfd wiilf wf brf initiblizing.
     *
     * @pbrbm f tif vifw fbdtory
     * @sff #sftPbrfnt
     */
    protfdtfd void lobdCiildrfn(VifwFbdtory f) {
        Elfmfnt f = gftElfmfnt();
        int n = f.gftElfmfntCount();
        if (n > 0) {
            Vifw[] bddfd = nfw Vifw[n];
            for (int i = 0; i < n; i++) {
                bddfd[i] = f.drfbtf(f.gftElfmfnt(i));
            }
            rfplbdf(0, 0, bddfd);
        }
    }

    /**
     * Fftdifs tif diild vifw indfx rfprfsfnting tif givfn position in
     * tif modfl.  Tiis is implfmfntfd to fftdi tif vifw in tif dbsf
     * wifrf tifrf is b diild vifw for fbdi diild flfmfnt.
     *
     * @pbrbm pos tif position &gt;= 0
     * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
     *   -1 if no vifw rfprfsfnts tibt position
     */
    protfdtfd syndironizfd int gftVifwIndfxAtPosition(int pos, Position.Bibs b) {
        boolfbn isBbdkwbrd = (b == Position.Bibs.Bbdkwbrd);
        pos = (isBbdkwbrd) ? Mbti.mbx(0, pos - 1) : pos;
        Elfmfnt flfm = gftElfmfnt();
        rfturn flfm.gftElfmfntIndfx(pos);
    }

    /**
     * Updbtf tif lbyout in rfsponsf to rfdfiving notifidbtion of
     * dibngf from tif modfl.  Tiis is implfmfntfd to notf tif
     * dibngf on tif CiildLodbtor so tibt offsfts of tif diildrfn
     * will bf dorrfdtly domputfd.
     *
     * @pbrbm fd dibngfs to tif flfmfnt tiis vifw is rfsponsiblf
     *  for (mby bf null if tifrf wfrf no dibngfs).
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @sff #insfrtUpdbtf
     * @sff #rfmovfUpdbtf
     * @sff #dibngfdUpdbtf
     */
    protfdtfd void updbtfLbyout(DodumfntEvfnt.ElfmfntCibngf fd,
                                    DodumfntEvfnt f, Sibpf b) {
        if (fd != null) {
            // tif nfwly insfrtfd diildrfn don't ibvf b vblid
            // offsft so tif diild lodbtor nffds to bf mfssbgfd
            // tibt tif diild prior to tif nfw diildrfn ibs
            // dibngfd sizf.
            int indfx = Mbti.mbx(fd.gftIndfx() - 1, 0);
            CiildStbtf ds = gftCiildStbtf(indfx);
            lodbtor.diildCibngfd(ds);
        }
    }

    // --- Vifw mftiods ------------------------------------

    /**
     * Sfts tif pbrfnt of tif vifw.
     * Tiis is rfimplfmfntfd to providf tif supfrdlbss
     * bfibvior bs wfll bs dblling tif <dodf>lobdCiildrfn</dodf>
     * mftiod if tiis vifw dofs not blrfbdy ibvf diildrfn.
     * Tif diildrfn siould not bf lobdfd in tif
     * donstrudtor bfdbusf tif bdt of sftting tif pbrfnt
     * mby dbusf tifm to try to sfbrdi up tif iifrbrdiy
     * (to gft tif iosting Contbinfr for fxbmplf).
     * If tiis vifw ibs diildrfn (tif vifw is bfing movfd
     * from onf plbdf in tif vifw iifrbrdiy to bnotifr),
     * tif <dodf>lobdCiildrfn</dodf> mftiod will not bf dbllfd.
     *
     * @pbrbm pbrfnt tif pbrfnt of tif vifw, null if nonf
     */
    publid void sftPbrfnt(Vifw pbrfnt) {
        supfr.sftPbrfnt(pbrfnt);
        if ((pbrfnt != null) && (gftVifwCount() == 0)) {
            VifwFbdtory f = gftVifwFbdtory();
            lobdCiildrfn(f);
        }
    }

    /**
     * Ciild vifws dbn dbll tiis on tif pbrfnt to indidbtf tibt
     * tif prfffrfndf ibs dibngfd bnd siould bf rfdonsidfrfd
     * for lbyout.  Tiis is rfimplfmfntfd to qufuf nfw work
     * on tif lbyout tirfbd.  Tiis mftiod gfts mfssbgfd from
     * multiplf tirfbds vib tif diildrfn.
     *
     * @pbrbm diild tif diild vifw
     * @pbrbm widti truf if tif widti prfffrfndf ibs dibngfd
     * @pbrbm ifigit truf if tif ifigit prfffrfndf ibs dibngfd
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf
     */
    publid syndironizfd void prfffrfndfCibngfd(Vifw diild, boolfbn widti, boolfbn ifigit) {
        if (diild == null) {
            gftPbrfnt().prfffrfndfCibngfd(tiis, widti, ifigit);
        } flsf {
            if (dibnging != null) {
                Vifw dv = dibnging.gftCiildVifw();
                if (dv == diild) {
                    // sizf wbs bfing dibngfd on tif diild, no nffd to
                    // qufuf work for it.
                    dibnging.prfffrfndfCibngfd(widti, ifigit);
                    rfturn;
                }
            }
            int indfx = gftVifwIndfx(diild.gftStbrtOffsft(),
                                     Position.Bibs.Forwbrd);
            CiildStbtf ds = gftCiildStbtf(indfx);
            ds.prfffrfndfCibngfd(widti, ifigit);
            LbyoutQufuf q = gftLbyoutQufuf();
            q.bddTbsk(ds);
            q.bddTbsk(flusiTbsk);
        }
    }

    /**
     * Sfts tif sizf of tif vifw.  Tiis siould dbusf
     * lbyout of tif vifw if tif vifw dbdifs bny lbyout
     * informbtion.
     * <p>
     * Sindf tif mbjor bxis is updbtfd bsyndironously bnd siould bf
     * tif sum of tif tilfd diildrfn tif dbll is ignorfd for tif mbjor
     * bxis.  Sindf tif minor bxis is flfxiblf, work is qufufd to rfsizf
     * tif diildrfn if tif minor spbn dibngfs.
     *
     * @pbrbm widti tif widti &gt;= 0
     * @pbrbm ifigit tif ifigit &gt;= 0
     */
    publid void sftSizf(flobt widti, flobt ifigit) {
        sftSpbnOnAxis(X_AXIS, widti);
        sftSpbnOnAxis(Y_AXIS, ifigit);
    }

    /**
     * Rftrifvfs tif sizf of tif vifw blong bn bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif durrfnt spbn of tif vifw blong tif givfn bxis, >= 0
     */
    flobt gftSpbnOnAxis(int bxis) {
        if (bxis == gftMbjorAxis()) {
            rfturn mbjorSpbn;
        }
        rfturn minorSpbn;
    }

    /**
     * Sfts tif sizf of tif vifw blong bn bxis.  Sindf tif mbjor
     * bxis is updbtfd bsyndironously bnd siould bf tif sum of tif
     * tilfd diildrfn tif dbll is ignorfd for tif mbjor bxis.  Sindf
     * tif minor bxis is flfxiblf, work is qufufd to rfsizf tif
     * diildrfn if tif minor spbn dibngfs.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm spbn tif spbn to lbyout to >= 0
     */
    void sftSpbnOnAxis(int bxis, flobt spbn) {
        flobt mbrgin = gftInsftSpbn(bxis);
        if (bxis == gftMinorAxis()) {
            flobt tbrgftSpbn = spbn - mbrgin;
            if (tbrgftSpbn != minorSpbn) {
                minorSpbn = tbrgftSpbn;

                // mbrk bll of tif CiildStbtf instbndfs bs nffding to
                // rfsizf tif diild, bnd qufuf up work to fix tifm.
                int n = gftVifwCount();
                if (n != 0) {
                    LbyoutQufuf q = gftLbyoutQufuf();
                    for (int i = 0; i < n; i++) {
                        CiildStbtf ds = gftCiildStbtf(i);
                        ds.diildSizfVblid = fblsf;
                        q.bddTbsk(ds);
                    }
                    q.bddTbsk(flusiTbsk);
                }
            }
        } flsf {
            // blong tif mbjor bxis tif vbluf is ignorfd
            // unlfss tif fstimbtfdMbjorSpbn propfrty is
            // truf.
            if (fstimbtfdMbjorSpbn) {
                mbjorSpbn = spbn - mbrgin;
            }
        }
    }

    /**
     * Rfndfr tif vifw using tif givfn bllodbtion bnd
     * rfndfring surfbdf.
     * <p>
     * Tiis is implfmfntfd to dftfrminf wiftifr or not tif
     * dfsirfd rfgion to bf rfndfrfd (i.f. tif undlippfd
     * brfb) is up to dbtf or not.  If up-to-dbtf tif diildrfn
     * brf rfndfrfd.  If not up-to-dbtf, b tbsk to build
     * tif dfsirfd brfb is plbdfd on tif lbyout qufuf bs
     * b iigi priority tbsk.  Tiis kffps by fvfnt tirfbd
     * moving by rfndfring if rfbdy, bnd postponing until
     * b lbtfr timf if not rfbdy (sindf pbint rfqufsts
     * dbn bf rfsdifdulfd).
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm bllod tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf bllod) {
        syndironizfd (lodbtor) {
            lodbtor.sftAllodbtion(bllod);
            lodbtor.pbintCiildrfn(g);
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        flobt mbrgin = gftInsftSpbn(bxis);
        if (bxis == tiis.bxis) {
            rfturn mbjorSpbn + mbrgin;
        }
        if (prffRfqufst != null) {
            Vifw diild = prffRfqufst.gftCiildVifw();
            rfturn diild.gftPrfffrrfdSpbn(bxis) + mbrgin;
        }

        // notiing is known bbout tif diildrfn yft
        rfturn mbrgin + 30;
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn  tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftMinimumSpbn(int bxis) {
        if (bxis == tiis.bxis) {
            rfturn gftPrfffrrfdSpbn(bxis);
        }
        if (minRfqufst != null) {
            Vifw diild = minRfqufst.gftCiildVifw();
            rfturn diild.gftMinimumSpbn(bxis);
        }

        // notiing is known bbout tif diildrfn yft
        if (bxis == X_AXIS) {
            rfturn gftLfftInsft() + gftRigitInsft() + 5;
        } flsf {
            rfturn gftTopInsft() + gftBottomInsft() + 5;
        }
    }

    /**
     * Dftfrminfs tif mbximum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftMbximumSpbn(int bxis) {
        if (bxis == tiis.bxis) {
            rfturn gftPrfffrrfdSpbn(bxis);
        }
        rfturn Intfgfr.MAX_VALUE;
    }


    /**
     * Rfturns tif numbfr of vifws in tiis vifw.  Sindf
     * tif dffbult is to not bf b dompositf vifw tiis
     * rfturns 0.
     *
     * @rfturn tif numbfr of vifws &gt;= 0
     * @sff Vifw#gftVifwCount
     */
    publid int gftVifwCount() {
        syndironizfd(stbts) {
            rfturn stbts.sizf();
        }
    }

    /**
     * Gfts tif nti diild vifw.  Sindf tifrf brf no
     * diildrfn by dffbult, tiis rfturns null.
     *
     * @pbrbm n tif numbfr of tif vifw to gft, &gt;= 0 &bmp;&bmp; &lt; gftVifwCount()
     * @rfturn tif vifw
     */
    publid Vifw gftVifw(int n) {
        CiildStbtf ds = gftCiildStbtf(n);
        if (ds != null) {
            rfturn ds.gftCiildVifw();
        }
        rfturn null;
    }

    /**
     * Fftdifs tif bllodbtion for tif givfn diild vifw.
     * Tiis fnbblfs finding out wifrf vbrious vifws
     * brf lodbtfd, witiout bssuming tif vifws storf
     * tifir lodbtion.  Tiis rfturns null sindf tif
     * dffbult is to not ibvf bny diild vifws.
     *
     * @pbrbm indfx tif indfx of tif diild, &gt;= 0 &bmp;&bmp; &lt; gftVifwCount()
     * @pbrbm b  tif bllodbtion to tiis vifw.
     * @rfturn tif bllodbtion to tif diild
     */
    publid Sibpf gftCiildAllodbtion(int indfx, Sibpf b) {
        Sibpf db = lodbtor.gftCiildAllodbtion(indfx, b);
        rfturn db;
    }

    /**
     * Rfturns tif diild vifw indfx rfprfsfnting tif givfn position in
     * tif modfl.  By dffbult b vifw ibs no diildrfn so tiis is implfmfntfd
     * to rfturn -1 to indidbtf tifrf is no vblid diild indfx for bny
     * position.
     *
     * @pbrbm pos tif position &gt;= 0
     * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
     *   -1 if no vifw rfprfsfnts tibt position
     * @sindf 1.3
     */
    publid int gftVifwIndfx(int pos, Position.Bibs b) {
        rfturn gftVifwIndfxAtPosition(pos, b);
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm b tif bibs towbrd tif prfvious dibrbdtfr or tif
     *  nfxt dibrbdtfr rfprfsfntfd by tif offsft, in dbsf tif
     *  position is b boundbry of two vifws.
     * @rfturn tif bounding box of tif givfn position is rfturnfd
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs
     *   not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bibs brgumfnt
     * @sff Vifw#vifwToModfl
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        int indfx = gftVifwIndfx(pos, b);
        Sibpf db = lodbtor.gftCiildAllodbtion(indfx, b);

        // forwbrd to tif diild vifw, bnd mbkf surf wf don't
        // intfrbdt witi tif lbyout tirfbd by syndironizing
        // on tif diild stbtf.
        CiildStbtf ds = gftCiildStbtf(indfx);
        syndironizfd (ds) {
            Vifw dv = ds.gftCiildVifw();
            Sibpf v = dv.modflToVifw(pos, db, b);
            rfturn v;
        }
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.  Tif bibsRfturn brgumfnt will bf
     * fillfd in to indidbtf tibt tif point givfn is dlosfr to tif nfxt
     * dibrbdtfr in tif modfl or tif prfvious dibrbdtfr in tif modfl.
     * <p>
     * Tiis is fxpfdtfd to bf dbllfd by tif GUI tirfbd, iolding b
     * rfbd-lodk on tif bssodibtfd modfl.  It is implfmfntfd to
     * lodbtf tif diild vifw bnd dftfrminf it's bllodbtion witi b
     * lodk on tif CiildLodbtor objfdt, bnd to dbll vifwToModfl
     * on tif diild vifw witi b lodk on tif CiildStbtf objfdt
     * to bvoid intfrbdtion witi tif lbyout tirfbd.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw &gt;= 0.  Tif bibsRfturn brgumfnt will bf
     * fillfd in to indidbtf tibt tif point givfn is dlosfr to tif nfxt
     * dibrbdtfr in tif modfl or tif prfvious dibrbdtfr in tif modfl.
     */
    publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibsRfturn) {
        int pos;    // rfturn position
        int indfx;  // diild indfx to forwbrd to
        Sibpf db;   // diild bllodbtion

        // lodbtf tif diild vifw bnd it's bllodbtion so tibt
        // wf dbn forwbrd to it.  Mbkf surf tif lbyout tirfbd
        // dofsn't dibngf bnytiing by trying to flusi dibngfs
        // to tif pbrfnt wiilf tif GUI tirfbd is trying to
        // find tif diild bnd it's bllodbtion.
        syndironizfd (lodbtor) {
            indfx = lodbtor.gftVifwIndfxAtPoint(x, y, b);
            db = lodbtor.gftCiildAllodbtion(indfx, b);
        }

        // forwbrd to tif diild vifw, bnd mbkf surf wf don't
        // intfrbdt witi tif lbyout tirfbd by syndironizing
        // on tif diild stbtf.
        CiildStbtf ds = gftCiildStbtf(indfx);
        syndironizfd (ds) {
            Vifw v = ds.gftCiildVifw();
            pos = v.vifwToModfl(x, y, db, bibsRfturn);
        }
        rfturn pos;
    }

    /**
     * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
     * lodbtion tibt onf migit plbdf b dbrft.  Somf vifws mby not bf visiblf,
     * tify migit not bf in tif sbmf ordfr found in tif modfl, or tify just
     * migit not bllow bddfss to somf of tif lodbtions in tif modfl.
     * Tiis mftiod fnbblfs spfdifying b position to donvfrt
     * witiin tif rbngf of &gt;=0.  If tif vbluf is -1, b position
     * will bf dbldulbtfd butombtidblly.  If tif vbluf &lt; -1,
     * tif {@dodf BbdLodbtionExdfption} will bf tirown.
     *
     * @pbrbm pos tif position to donvfrt
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
     *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd;
     *  tiis mby bf onf of tif following:
     *  <ul stylf="list-stylf-typf:nonf">
     *  <li><dodf>SwingConstbnts.WEST</dodf></li>
     *  <li><dodf>SwingConstbnts.EAST</dodf></li>
     *  <li><dodf>SwingConstbnts.NORTH</dodf></li>
     *  <li><dodf>SwingConstbnts.SOUTH</dodf></li>
     *  </ul>
     * @pbrbm bibsRft bn brrby dontbin tif bibs tibt wbs difdkfd
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
     *  lodbtion visubl position
     * @fxdfption BbdLodbtionExdfption tif givfn position is not b vblid
     *                                 position witiin tif dodumfnt
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dirfdtion</dodf> is invblid
     */
    publid int gftNfxtVisublPositionFrom(int pos, Position.Bibs b, Sibpf b,
                                         int dirfdtion,
                                         Position.Bibs[] bibsRft)
                                                  tirows BbdLodbtionExdfption {
        if (pos < -1) {
            tirow nfw BbdLodbtionExdfption("invblid position", pos);
        }
        rfturn Utilitifs.gftNfxtVisublPositionFrom(
                            tiis, pos, b, b, dirfdtion, bibsRft);
    }

    // --- vbribblfs -----------------------------------------

    /**
     * Tif mbjor bxis bgbinst wiidi tif diildrfn brf
     * tilfd.
     */
    int bxis;

    /**
     * Tif diildrfn bnd tifir lbyout stbtistids.
     */
    List<CiildStbtf> stbts;

    /**
     * Currfnt spbn blong tif mbjor bxis.  Tiis
     * is blso tif vbluf rfturnfd by gftMinimumSizf,
     * gftPrfffrrfdSizf, bnd gftMbximumSizf blong
     * tif mbjor bxis.
     */
    flobt mbjorSpbn;

    /**
     * Is tif spbn blong tif mbjor bxis fstimbtfd?
     */
    boolfbn fstimbtfdMbjorSpbn;

    /**
     * Currfnt spbn blong tif minor bxis.  Tiis
     * is wibt lbyout wbs donf bgbinst (i.f. tiings
     * brf flfxiblf in tiis dirfdtion).
     */
    flobt minorSpbn;

    /**
     * Objfdt tibt mbnbgfs tif offsfts of tif
     * diildrfn.  All lodking for mbnbgfmfnt of
     * diild lodbtions is on tiis objfdt.
     */
    protfdtfd CiildLodbtor lodbtor;

    flobt topInsft;
    flobt bottomInsft;
    flobt lfftInsft;
    flobt rigitInsft;

    CiildStbtf minRfqufst;
    CiildStbtf prffRfqufst;
    boolfbn mbjorCibngfd;
    boolfbn minorCibngfd;
    Runnbblf flusiTbsk;

    /**
     * Ciild tibt is bdtivfly dibnging sizf.  Tiis oftfn
     * dbusfs b prfffrfndfCibngfd, so tiis is b dbdif to
     * possibly spffd up tif mbrking tif stbtf.  It blso
     * iflps flbg bn opportunity to bvoid bdding to flusi
     * tbsk to tif lbyout qufuf.
     */
    CiildStbtf dibnging;

    /**
     * A dlbss to mbnbgf tif ffffdtivf position of tif
     * diild vifws in b lodblizfd brfb wiilf dibngfs brf
     * bfing mbdf bround tif lodblizfd brfb.  Tif AsyndBoxVifw
     * mby bf dontinuously dibnging, but tif visiblf brfb
     * nffds to rfmbin fbirly stbblf until tif lbyout tirfbd
     * dfdidfs to publisi bn updbtf to tif pbrfnt.
     * @sindf 1.3
     */
    publid dlbss CiildLodbtor {

        /**
         * donstrudt b diild lodbtor.
         */
        publid CiildLodbtor() {
            lbstAllod = nfw Rfdtbnglf();
            diildAllod = nfw Rfdtbnglf();
        }

        /**
         * Notifidbtion tibt b diild dibngfd.  Tiis dbn ffffdt
         * wiftifr or not nfw offsft dbldulbtions brf nffdfd.
         * Tiis is dbllfd by b CiildStbtf objfdt tibt ibs
         * dibngfd it's mbjor spbn.  Tiis dbn tifrfforf bf
         * dbllfd by multiplf tirfbds.
         */
        publid syndironizfd void diildCibngfd(CiildStbtf ds) {
            if (lbstVblidOffsft == null) {
                lbstVblidOffsft = ds;
            } flsf if (ds.gftCiildVifw().gftStbrtOffsft() <
                       lbstVblidOffsft.gftCiildVifw().gftStbrtOffsft()) {
                lbstVblidOffsft = ds;
            }
        }

        /**
         * Pbint tif diildrfn tibt intfrsfdt tif dlip brfb.
         */
        publid syndironizfd void pbintCiildrfn(Grbpiids g) {
            Rfdtbnglf dlip = g.gftClipBounds();
            flobt tbrgftOffsft = (bxis == X_AXIS) ?
                dlip.x - lbstAllod.x : dlip.y - lbstAllod.y;
            int indfx = gftVifwIndfxAtVisublOffsft(tbrgftOffsft);
            int n = gftVifwCount();
            flobt offs = gftCiildStbtf(indfx).gftMbjorOffsft();
            for (int i = indfx; i < n; i++) {
                CiildStbtf ds = gftCiildStbtf(i);
                ds.sftMbjorOffsft(offs);
                Sibpf db = gftCiildAllodbtion(i);
                if (intfrsfdtsClip(db, dlip)) {
                    syndironizfd (ds) {
                        Vifw v = ds.gftCiildVifw();
                        v.pbint(g, db);
                    }
                } flsf {
                    // donf pbinting intfrsfdtion
                    brfbk;
                }
                offs += ds.gftMbjorSpbn();
            }
        }

        /**
         * Fftdi tif bllodbtion to usf for b diild vifw.
         * Tiis will updbtf tif offsfts for bll diildrfn
         * not yft updbtfd bfforf tif givfn indfx.
         */
        publid syndironizfd Sibpf gftCiildAllodbtion(int indfx, Sibpf b) {
            if (b == null) {
                rfturn null;
            }
            sftAllodbtion(b);
            CiildStbtf ds = gftCiildStbtf(indfx);
            if (lbstVblidOffsft == null) {
                lbstVblidOffsft = gftCiildStbtf(0);
            }
            if (ds.gftCiildVifw().gftStbrtOffsft() >
                lbstVblidOffsft.gftCiildVifw().gftStbrtOffsft()) {
                // offsfts nffd to bf updbtfd
                updbtfCiildOffsftsToIndfx(indfx);
            }
            Sibpf db = gftCiildAllodbtion(indfx);
            rfturn db;
        }

        /**
         * Fftdifs tif diild vifw indfx bt tif givfn point.
         * Tiis is dbllfd by tif vbrious Vifw mftiods tibt
         * nffd to dbldulbtf wiidi diild to forwbrd b mfssbgf
         * to.  Tiis siould bf dbllfd by b blodk syndironizfd
         * on tiis objfdt, bnd would typidblly bf followfd
         * witi onf or morf dblls to gftCiildAllodbtion tibt
         * siould blso bf in tif syndironizfd blodk.
         *
         * @pbrbm x tif X doordinbtf &gt;= 0
         * @pbrbm y tif Y doordinbtf &gt;= 0
         * @pbrbm b tif bllodbtion to tif Vifw
         * @rfturn tif nfbrfst diild indfx
         */
        publid int gftVifwIndfxAtPoint(flobt x, flobt y, Sibpf b) {
            sftAllodbtion(b);
            flobt tbrgftOffsft = (bxis == X_AXIS) ? x - lbstAllod.x : y - lbstAllod.y;
            int indfx = gftVifwIndfxAtVisublOffsft(tbrgftOffsft);
            rfturn indfx;
        }

        /**
         * Fftdi tif bllodbtion to usf for b diild vifw.
         * <fm>Tiis dofs not updbtf tif offsfts in tif CiildStbtf
         * rfdords.</fm>
         */
        protfdtfd Sibpf gftCiildAllodbtion(int indfx) {
            CiildStbtf ds = gftCiildStbtf(indfx);
            if (! ds.isLbyoutVblid()) {
                ds.run();
            }
            if (bxis == X_AXIS) {
                diildAllod.x = lbstAllod.x + (int) ds.gftMbjorOffsft();
                diildAllod.y = lbstAllod.y + (int) ds.gftMinorOffsft();
                diildAllod.widti = (int) ds.gftMbjorSpbn();
                diildAllod.ifigit = (int) ds.gftMinorSpbn();
            } flsf {
                diildAllod.y = lbstAllod.y + (int) ds.gftMbjorOffsft();
                diildAllod.x = lbstAllod.x + (int) ds.gftMinorOffsft();
                diildAllod.ifigit = (int) ds.gftMbjorSpbn();
                diildAllod.widti = (int) ds.gftMinorSpbn();
            }
            diildAllod.x += (int)gftLfftInsft();
            diildAllod.y += (int)gftRigitInsft();
            rfturn diildAllod;
        }

        /**
         * Copy tif durrfntly bllodbtfd sibpf into tif Rfdtbnglf
         * usfd to storf tif durrfnt bllodbtion.  Tiis would bf
         * b flobting point rfdtbnglf in b Jbvb2D-spfdifid implfmfntbtion.
         */
        protfdtfd void sftAllodbtion(Sibpf b) {
            if (b instbndfof Rfdtbnglf) {
                lbstAllod.sftBounds((Rfdtbnglf) b);
            } flsf {
                lbstAllod.sftBounds(b.gftBounds());
            }
            sftSizf(lbstAllod.widti, lbstAllod.ifigit);
        }

        /**
         * Lodbtf tif vifw rfsponsiblf for bn offsft into tif box
         * blong tif mbjor bxis.  Mbkf surf tibt offsfts brf sft
         * on tif CiildStbtf objfdts up to tif givfn tbrgft spbn
         * pbst tif dfsirfd offsft.
         *
         * @rfturn   indfx of tif vifw rfprfsfnting tif givfn visubl
         *   lodbtion (tbrgftOffsft), or -1 if no vifw rfprfsfnts
         *   tibt lodbtion
         */
        protfdtfd int gftVifwIndfxAtVisublOffsft(flobt tbrgftOffsft) {
            int n = gftVifwCount();
            if (n > 0) {
                boolfbn lbstVblid = (lbstVblidOffsft != null);

                if (lbstVblidOffsft == null) {
                    lbstVblidOffsft = gftCiildStbtf(0);
                }
                if (tbrgftOffsft > mbjorSpbn) {
                    // siould only gft ifrf on tif first timf displby.
                    if (!lbstVblid) {
                        rfturn 0;
                    }
                    int pos = lbstVblidOffsft.gftCiildVifw().gftStbrtOffsft();
                    int indfx = gftVifwIndfx(pos, Position.Bibs.Forwbrd);
                    rfturn indfx;
                } flsf if (tbrgftOffsft > lbstVblidOffsft.gftMbjorOffsft()) {
                    // roll offsft dbldulbtions forwbrd
                    rfturn updbtfCiildOffsfts(tbrgftOffsft);
                } flsf {
                    // no dibngfs prior to tif nffdfd offsft
                    // tiis siould bf b binbry sfbrdi
                    flobt offs = 0f;
                    for (int i = 0; i < n; i++) {
                        CiildStbtf ds = gftCiildStbtf(i);
                        flobt nfxtOffs = offs + ds.gftMbjorSpbn();
                        if (tbrgftOffsft < nfxtOffs) {
                            rfturn i;
                        }
                        offs = nfxtOffs;
                    }
                }
            }
            rfturn n - 1;
        }

        /**
         * Movf tif lodbtion of tif lbst offsft dbldulbtion forwbrd
         * to tif dfsirfd offsft.
         */
        int updbtfCiildOffsfts(flobt tbrgftOffsft) {
            int n = gftVifwCount();
            int tbrgftIndfx = n - 1;
            int pos = lbstVblidOffsft.gftCiildVifw().gftStbrtOffsft();
            int stbrtIndfx = gftVifwIndfx(pos, Position.Bibs.Forwbrd);
            flobt stbrt = lbstVblidOffsft.gftMbjorOffsft();
            flobt lbstOffsft = stbrt;
            for (int i = stbrtIndfx; i < n; i++) {
                CiildStbtf ds = gftCiildStbtf(i);
                ds.sftMbjorOffsft(lbstOffsft);
                lbstOffsft += ds.gftMbjorSpbn();
                if (tbrgftOffsft < lbstOffsft) {
                    tbrgftIndfx = i;
                    lbstVblidOffsft = ds;
                    brfbk;
                }
            }

            rfturn tbrgftIndfx;
        }

        /**
         * Movf tif lodbtion of tif lbst offsft dbldulbtion forwbrd
         * to tif dfsirfd indfx.
         */
        void updbtfCiildOffsftsToIndfx(int indfx) {
            int pos = lbstVblidOffsft.gftCiildVifw().gftStbrtOffsft();
            int stbrtIndfx = gftVifwIndfx(pos, Position.Bibs.Forwbrd);
            flobt lbstOffsft = lbstVblidOffsft.gftMbjorOffsft();
            for (int i = stbrtIndfx; i <= indfx; i++) {
                CiildStbtf ds = gftCiildStbtf(i);
                ds.sftMbjorOffsft(lbstOffsft);
                lbstOffsft += ds.gftMbjorSpbn();
            }
        }

        boolfbn intfrsfdtsClip(Sibpf diildAllod, Rfdtbnglf dlip) {
            Rfdtbnglf ds = (diildAllod instbndfof Rfdtbnglf) ?
                (Rfdtbnglf) diildAllod : diildAllod.gftBounds();
            if (ds.intfrsfdts(dlip)) {
                // Mbkf surf tibt lbstAllod blso dontbins diildAllod,
                // tiis will bf fblsf if ibvfn't yft flusifd dibngfs.
                rfturn lbstAllod.intfrsfdts(ds);
            }
            rfturn fblsf;
        }

        /**
         * Tif lodbtion of tif lbst offsft dbldulbtion
         * tibt is vblid.
         */
        protfdtfd CiildStbtf lbstVblidOffsft;

        /**
         * Tif lbst sffn bllodbtion (for rfpbinting wifn dibngfs
         * brf flusifd upwbrd).
         */
        protfdtfd Rfdtbnglf lbstAllod;

        /**
         * A sibpf to usf for tif diild bllodbtion to bvoid
         * drfbting b lot of gbrbbgf.
         */
        protfdtfd Rfdtbnglf diildAllod;
    }

    /**
     * A rfdord rfprfsfnting tif lbyout stbtf of b
     * diild vifw.  It is runnbblf bs b tbsk on bnotifr
     * tirfbd.  All bddfss to tif diild vifw tibt is
     * bbsfd upon b rfbd-lodk on tif modfl siould syndironizf
     * on tiis objfdt (i.f. Tif lbyout tirfbd bnd tif GUI
     * tirfbd dbn boti ibvf b rfbd lodk on tif modfl bt tif
     * sbmf timf bnd brf not protfdtfd from fbdi otifr).
     * Addfss to b diild vifw iifrbrdiy is sfriblizfd vib
     * syndironizbtion on tif CiildStbtf instbndf.
     * @sindf 1.3
     */
    publid dlbss CiildStbtf implfmfnts Runnbblf {

        /**
         * Construdt b diild stbtus.  Tiis nffds to stbrt
         * out bs fbirly lbrgf so wf don't fblsfly bfgin witi
         * tif idfb tibt bll of tif diildrfn brf visiblf.
         * @sindf 1.4
         */
        publid CiildStbtf(Vifw v) {
            diild = v;
            minorVblid = fblsf;
            mbjorVblid = fblsf;
            diildSizfVblid = fblsf;
            diild.sftPbrfnt(AsyndBoxVifw.tiis);
        }

        /**
         * Fftdi tif diild vifw tiis rfdord rfprfsfnts
         */
        publid Vifw gftCiildVifw() {
            rfturn diild;
        }

        /**
         * Updbtf tif diild stbtf.  Tiis siould bf
         * dbllfd by tif tirfbd tibt dfsirfs to spfnd
         * timf updbting tif diild stbtf (intfndfd to
         * bf tif lbyout tirfbd).
         * <p>
         * Tiis bdquirfs b rfbd lodk on tif bssodibtfd
         * dodumfnt for tif durbtion of tif updbtf to
         * fnsurf tif modfl is not dibngfd wiilf it is
         * opfrbting.  Tif first tiing to do would bf
         * to sff if bny work bdtublly nffds to bf donf.
         * Tif following dould ibvf dondfivbbly ibppfnfd
         * wiilf tif stbtf wbs wbiting to bf updbtfd:
         * <ol>
         * <li>Tif diild mby ibvf bffn rfmovfd from tif
         * vifw iifrbrdiy.
         * <li>Tif diild mby ibvf bffn updbtfd by b
         * iigifr priority opfrbtion (i.f. tif diild
         * mby ibvf bfdomf visiblf).
         * </ol>
         */
        publid void run () {
            AbstrbdtDodumfnt dod = (AbstrbdtDodumfnt) gftDodumfnt();
            try {
                dod.rfbdLodk();
                if (minorVblid && mbjorVblid && diildSizfVblid) {
                    // notiing to do
                    rfturn;
                }
                if (diild.gftPbrfnt() == AsyndBoxVifw.tiis) {
                    // tiis mby ovfrwritf bnotifrs tirfbds dbdifd
                    // vbluf for bdtivfly dibnging... but tibt just
                    // mfbns it won't usf tif dbdif if tifrf is bn
                    // ovfrwritf.
                    syndironizfd(AsyndBoxVifw.tiis) {
                        dibnging = tiis;
                    }
                    updbtfCiild();
                    syndironizfd(AsyndBoxVifw.tiis) {
                        dibnging = null;
                    }

                    // sftting tif diild sizf on tif minor bxis
                    // mby ibvf dbusfd it to dibngf it's prfffrfndf
                    // blong tif mbjor bxis.
                    updbtfCiild();
                }
            } finblly {
                dod.rfbdUnlodk();
            }
        }

        void updbtfCiild() {
            boolfbn minorUpdbtfd = fblsf;
            syndironizfd(tiis) {
                if (! minorVblid) {
                    int minorAxis = gftMinorAxis();
                    min = diild.gftMinimumSpbn(minorAxis);
                    prff = diild.gftPrfffrrfdSpbn(minorAxis);
                    mbx = diild.gftMbximumSpbn(minorAxis);
                    minorVblid = truf;
                    minorUpdbtfd = truf;
                }
            }
            if (minorUpdbtfd) {
                minorRfquirfmfntCibngf(tiis);
            }

            boolfbn mbjorUpdbtfd = fblsf;
            flobt dfltb = 0.0f;
            syndironizfd(tiis) {
                if (! mbjorVblid) {
                    flobt old = spbn;
                    spbn = diild.gftPrfffrrfdSpbn(bxis);
                    dfltb = spbn - old;
                    mbjorVblid = truf;
                    mbjorUpdbtfd = truf;
                }
            }
            if (mbjorUpdbtfd) {
                mbjorRfquirfmfntCibngf(tiis, dfltb);
                lodbtor.diildCibngfd(tiis);
            }

            syndironizfd(tiis) {
                if (! diildSizfVblid) {
                    flobt w;
                    flobt i;
                    if (bxis == X_AXIS) {
                        w = spbn;
                        i = gftMinorSpbn();
                    } flsf {
                        w = gftMinorSpbn();
                        i = spbn;
                    }
                    diildSizfVblid = truf;
                    diild.sftSizf(w, i);
                }
            }

        }

        /**
         * Wibt is tif spbn blong tif minor bxis.
         */
        publid flobt gftMinorSpbn() {
            if (mbx < minorSpbn) {
                rfturn mbx;
            }
            // mbkf it tif tbrgft widti, or bs smbll bs it dbn gft.
            rfturn Mbti.mbx(min, minorSpbn);
        }

        /**
         * Wibt is tif offsft blong tif minor bxis
         */
        publid flobt gftMinorOffsft() {
            if (mbx < minorSpbn) {
                // dbn't mbkf tif diild tiis widf, blign it
                flobt blign = diild.gftAlignmfnt(gftMinorAxis());
                rfturn ((minorSpbn - mbx) * blign);
            }
            rfturn 0f;
        }

        /**
         * Wibt is tif spbn blong tif mbjor bxis.
         */
        publid flobt gftMbjorSpbn() {
            rfturn spbn;
        }

        /**
         * Gft tif offsft blong tif mbjor bxis
         */
        publid flobt gftMbjorOffsft() {
            rfturn offsft;
        }

        /**
         * Tiis mftiod siould only bf dbllfd by tif CiildLodbtor,
         * it is simply b donvfnifnt plbdf to iold tif dbdifd
         * lodbtion.
         */
        publid void sftMbjorOffsft(flobt offs) {
            offsft = offs;
        }

        /**
         * Mbrk prfffrfndfs dibngfd for tiis diild.
         *
         * @pbrbm widti truf if tif widti prfffrfndf ibs dibngfd
         * @pbrbm ifigit truf if tif ifigit prfffrfndf ibs dibngfd
         * @sff jbvbx.swing.JComponfnt#rfvblidbtf
         */
        publid void prfffrfndfCibngfd(boolfbn widti, boolfbn ifigit) {
            if (bxis == X_AXIS) {
                if (widti) {
                    mbjorVblid = fblsf;
                }
                if (ifigit) {
                    minorVblid = fblsf;
                }
            } flsf {
                if (widti) {
                    minorVblid = fblsf;
                }
                if (ifigit) {
                    mbjorVblid = fblsf;
                }
            }
            diildSizfVblid = fblsf;
        }

        /**
         * Hbs tif diild vifw bffn lbid out.
         */
        publid boolfbn isLbyoutVblid() {
            rfturn (minorVblid && mbjorVblid && diildSizfVblid);
        }

        // minor bxis
        privbtf flobt min;
        privbtf flobt prff;
        privbtf flobt mbx;
        privbtf boolfbn minorVblid;

        // mbjor bxis
        privbtf flobt spbn;
        privbtf flobt offsft;
        privbtf boolfbn mbjorVblid;

        privbtf Vifw diild;
        privbtf boolfbn diildSizfVblid;
    }

    /**
     * Tbsk to flusi rfquirfmfnt dibngfs upwbrd
     */
    dlbss FlusiTbsk implfmfnts Runnbblf {

        publid void run() {
            flusiRfquirfmfntCibngfs();
        }

    }

}
