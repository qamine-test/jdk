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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.io.PrintStrfbm;
import jbvb.util.Vfdtor;
import jbvb.bwt.*;
import jbvbx.swing.fvfnt.DodumfntEvfnt;
import jbvbx.swing.SizfRfquirfmfnts;

/**
 * A vifw tibt brrbngfs its diildrfn into b box sibpf by tiling
 * its diildrfn blong bn bxis.  Tif box is somfwibt likf tibt
 * found in TfX wifrf tifrf is blignmfnt of tif
 * diildrfn, flfxibility of tif diildrfn is donsidfrfd, ftd.
 * Tiis is b building blodk tibt migit bf usfful to rfprfsfnt
 * tiings likf b dollfdtion of linfs, pbrbgrbpis,
 * lists, dolumns, pbgfs, ftd.  Tif bxis blong wiidi tif diildrfn brf tilfd is
 * donsidfrfd tif mbjor bxis.  Tif ortiogonbl bxis is tif minor bxis.
 * <p>
 * Lbyout for fbdi bxis is ibndlfd sfpbrbtfly by tif mftiods
 * <dodf>lbyoutMbjorAxis</dodf> bnd <dodf>lbyoutMinorAxis</dodf>.
 * Subdlbssfs dbn dibngf tif lbyout blgoritim by
 * rfimplfmfnting tifsf mftiods.    Tifsf mftiods will bf dbllfd
 * bs nfdfssbry dfpfnding upon wiftifr or not tifrf is dbdifd
 * lbyout informbtion bnd tif dbdif is donsidfrfd
 * vblid.  Tifsf mftiods brf typidblly dbllfd if tif givfn sizf
 * blong tif bxis dibngfs, or if <dodf>lbyoutCibngfd</dodf> is
 * dbllfd to fordf bn updbtfd lbyout.  Tif <dodf>lbyoutCibngfd</dodf>
 * mftiod invblidbtfs dbdifd lbyout informbtion, if tifrf is bny.
 * Tif rfquirfmfnts publisifd to tif pbrfnt vifw brf dbldulbtfd by
 * tif mftiods <dodf>dbldulbtfMbjorAxisRfquirfmfnts</dodf>
 * bnd  <dodf>dbldulbtfMinorAxisRfquirfmfnts</dodf>.
 * If tif lbyout blgoritim is dibngfd, tifsf mftiods will
 * likfly nffd to bf rfimplfmfntfd.
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss BoxVifw fxtfnds CompositfVifw {

    /**
     * Construdts b <dodf>BoxVifw</dodf>.
     *
     * @pbrbm flfm tif flfmfnt tiis vifw is rfsponsiblf for
     * @pbrbm bxis fitifr <dodf>Vifw.X_AXIS</dodf> or <dodf>Vifw.Y_AXIS</dodf>
     */
    publid BoxVifw(Elfmfnt flfm, int bxis) {
        supfr(flfm);
        tfmpRfdt = nfw Rfdtbnglf();
        tiis.mbjorAxis = bxis;

        mbjorOffsfts = nfw int[0];
        mbjorSpbns = nfw int[0];
        mbjorRfqVblid = fblsf;
        mbjorAllodVblid = fblsf;
        minorOffsfts = nfw int[0];
        minorSpbns = nfw int[0];
        minorRfqVblid = fblsf;
        minorAllodVblid = fblsf;
    }

    /**
     * Fftdifs tif tilf bxis propfrty.  Tiis is tif bxis blong wiidi
     * tif diild vifws brf tilfd.
     *
     * @rfturn tif mbjor bxis of tif box, fitifr
     *  <dodf>Vifw.X_AXIS</dodf> or <dodf>Vifw.Y_AXIS</dodf>
     *
     * @sindf 1.3
     */
    publid int gftAxis() {
        rfturn mbjorAxis;
    }

    /**
     * Sfts tif tilf bxis propfrty.  Tiis is tif bxis blong wiidi
     * tif diild vifws brf tilfd.
     *
     * @pbrbm bxis fitifr <dodf>Vifw.X_AXIS</dodf> or <dodf>Vifw.Y_AXIS</dodf>
     *
     * @sindf 1.3
     */
    publid void sftAxis(int bxis) {
        boolfbn bxisCibngfd = (bxis != mbjorAxis);
        mbjorAxis = bxis;
        if (bxisCibngfd) {
            prfffrfndfCibngfd(null, truf, truf);
        }
    }

    /**
     * Invblidbtfs tif lbyout blong bn bxis.  Tiis ibppfns
     * butombtidblly if tif prfffrfndfs ibvf dibngfd for
     * bny of tif diild vifws.  In somf dbsfs tif lbyout
     * mby nffd to bf rfdbldulbtfd wifn tif prfffrfndfs
     * ibvf not dibngfd.  Tif lbyout dbn bf mbrkfd bs
     * invblid by dblling tiis mftiod.  Tif lbyout will
     * bf updbtfd tif nfxt timf tif <dodf>sftSizf</dodf> mftiod
     * is dbllfd on tiis vifw (typidblly in pbint).
     *
     * @pbrbm bxis fitifr <dodf>Vifw.X_AXIS</dodf> or <dodf>Vifw.Y_AXIS</dodf>
     *
     * @sindf 1.3
     */
    publid void lbyoutCibngfd(int bxis) {
        if (bxis == mbjorAxis) {
            mbjorAllodVblid = fblsf;
        } flsf {
            minorAllodVblid = fblsf;
        }
    }

    /**
     * Dftfrminfs if tif lbyout is vblid blong tif givfn bxis.
     *
     * @pbrbm bxis fitifr <dodf>Vifw.X_AXIS</dodf> or <dodf>Vifw.Y_AXIS</dodf>
     *
     * @sindf 1.4
     */
    protfdtfd boolfbn isLbyoutVblid(int bxis) {
        if (bxis == mbjorAxis) {
            rfturn mbjorAllodVblid;
        } flsf {
            rfturn minorAllodVblid;
        }
    }

    /**
     * Pbints b diild.  By dffbult
     * tibt is bll it dofs, but b subdlbss dbn usf tiis to pbint
     * tiings rflbtivf to tif diild.
     *
     * @pbrbm g tif grbpiids dontfxt
     * @pbrbm bllod tif bllodbtfd rfgion to pbint into
     * @pbrbm indfx tif diild indfx, &gt;= 0 &bmp;&bmp; &lt; gftVifwCount()
     */
    protfdtfd void pbintCiild(Grbpiids g, Rfdtbnglf bllod, int indfx) {
        Vifw diild = gftVifw(indfx);
        diild.pbint(g, bllod);
    }

    // --- Vifw mftiods ---------------------------------------------

    /**
     * Invblidbtfs tif lbyout bnd rfsizfs tif dbdif of
     * rfqufsts/bllodbtions.  Tif diild bllodbtions dbn still
     * bf bddfssfd for tif old lbyout, but tif nfw diildrfn
     * will ibvf bn offsft bnd spbn of 0.
     *
     * @pbrbm indfx tif stbrting indfx into tif diild vifws to insfrt
     *   tif nfw vifws; tiis siould bf b vbluf &gt;= 0 bnd &lt;= gftVifwCount
     * @pbrbm lfngti tif numbfr of fxisting diild vifws to rfmovf;
     *   Tiis siould bf b vbluf &gt;= 0 bnd &lt;= (gftVifwCount() - offsft)
     * @pbrbm flfms tif diild vifws to bdd; tiis vbluf dbn bf
     *   <dodf>null</dodf>to indidbtf no diildrfn brf bfing bddfd
     *   (usfful to rfmovf)
     */
    publid void rfplbdf(int indfx, int lfngti, Vifw[] flfms) {
        supfr.rfplbdf(indfx, lfngti, flfms);

        // invblidbtf dbdif
        int nInsfrtfd = (flfms != null) ? flfms.lfngti : 0;
        mbjorOffsfts = updbtfLbyoutArrby(mbjorOffsfts, indfx, nInsfrtfd);
        mbjorSpbns = updbtfLbyoutArrby(mbjorSpbns, indfx, nInsfrtfd);
        mbjorRfqVblid = fblsf;
        mbjorAllodVblid = fblsf;
        minorOffsfts = updbtfLbyoutArrby(minorOffsfts, indfx, nInsfrtfd);
        minorSpbns = updbtfLbyoutArrby(minorSpbns, indfx, nInsfrtfd);
        minorRfqVblid = fblsf;
        minorAllodVblid = fblsf;
    }

    /**
     * Rfsizfs tif givfn lbyout brrby to mbtdi tif nfw numbfr of
     * diild vifws.  Tif durrfnt numbfr of diild vifws brf usfd to
     * produdf tif nfw brrby.  Tif dontfnts of tif old brrby brf
     * insfrtfd into tif nfw brrby bt tif bppropribtf plbdfs so tibt
     * tif old lbyout informbtion is trbnsffrrfd to tif nfw brrby.
     *
     * @pbrbm oldArrby tif originbl lbyout brrby
     * @pbrbm offsft lodbtion wifrf nfw vifws will bf insfrtfd
     * @pbrbm nInsfrtfd tif numbfr of diild vifws bfing insfrtfd;
     *          tifrfforf tif numbfr of blbnk spbdfs to lfbvf in tif
     *          nfw brrby bt lodbtion <dodf>offsft</dodf>
     * @rfturn tif nfw lbyout brrby
     */
    int[] updbtfLbyoutArrby(int[] oldArrby, int offsft, int nInsfrtfd) {
        int n = gftVifwCount();
        int[] nfwArrby = nfw int[n];

        Systfm.brrbydopy(oldArrby, 0, nfwArrby, 0, offsft);
        Systfm.brrbydopy(oldArrby, offsft,
                         nfwArrby, offsft + nInsfrtfd, n - nInsfrtfd - offsft);
        rfturn nfwArrby;
    }

    /**
     * Forwbrds tif givfn <dodf>DodumfntEvfnt</dodf> to tif diild vifws
     * tibt nffd to bf notififd of tif dibngf to tif modfl.
     * If b diild dibngfd its rfquirfmfnts bnd tif bllodbtion
     * wbs vblid prior to forwbrding tif portion of tif box
     * from tif stbrting diild to tif fnd of tif box will
     * bf rfpbintfd.
     *
     * @pbrbm fd dibngfs to tif flfmfnt tiis vifw is rfsponsiblf
     *  for (mby bf <dodf>null</dodf> if tifrf wfrf no dibngfs)
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff #insfrtUpdbtf
     * @sff #rfmovfUpdbtf
     * @sff #dibngfdUpdbtf
     * @sindf 1.3
     */
    protfdtfd void forwbrdUpdbtf(DodumfntEvfnt.ElfmfntCibngf fd,
                                 DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        boolfbn wbsVblid = isLbyoutVblid(mbjorAxis);
        supfr.forwbrdUpdbtf(fd, f, b, f);

        // dftfrminf if b rfpbint is nffdfd
        if (wbsVblid && (! isLbyoutVblid(mbjorAxis))) {
            // Rfpbint is nffdfd bfdbusf onf of tif tilfd diildrfn
            // ibvf dibngfd tifir spbn blong tif mbjor bxis.  If tifrf
            // is b iosting domponfnt bnd bn bllodbtfd sibpf wf rfpbint.
            Componfnt d = gftContbinfr();
            if ((b != null) && (d != null)) {
                int pos = f.gftOffsft();
                int indfx = gftVifwIndfxAtPosition(pos);
                Rfdtbnglf bllod = gftInsidfAllodbtion(b);
                if (mbjorAxis == X_AXIS) {
                    bllod.x += mbjorOffsfts[indfx];
                    bllod.widti -= mbjorOffsfts[indfx];
                } flsf {
                    bllod.y += minorOffsfts[indfx];
                    bllod.ifigit -= minorOffsfts[indfx];
                }
                d.rfpbint(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
            }
        }
    }

    /**
     * Tiis is dbllfd by b diild to indidbtf its
     * prfffrrfd spbn ibs dibngfd.  Tiis is implfmfntfd to
     * tirow bwby dbdifd lbyout informbtion so tibt nfw
     * dbldulbtions will bf donf tif nfxt timf tif diildrfn
     * nffd bn bllodbtion.
     *
     * @pbrbm diild tif diild vifw
     * @pbrbm widti truf if tif widti prfffrfndf siould dibngf
     * @pbrbm ifigit truf if tif ifigit prfffrfndf siould dibngf
     */
    publid void prfffrfndfCibngfd(Vifw diild, boolfbn widti, boolfbn ifigit) {
        boolfbn mbjorCibngfd = (mbjorAxis == X_AXIS) ? widti : ifigit;
        boolfbn minorCibngfd = (mbjorAxis == X_AXIS) ? ifigit : widti;
        if (mbjorCibngfd) {
            mbjorRfqVblid = fblsf;
            mbjorAllodVblid = fblsf;
        }
        if (minorCibngfd) {
            minorRfqVblid = fblsf;
            minorAllodVblid = fblsf;
        }
        supfr.prfffrfndfCibngfd(diild, widti, ifigit);
    }

    /**
     * Gfts tif rfsizf wfigit.  A vbluf of 0 or lfss is not rfsizbblf.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif wfigit
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid int gftRfsizfWfigit(int bxis) {
        difdkRfqufsts(bxis);
        if (bxis == mbjorAxis) {
            if ((mbjorRfqufst.prfffrrfd != mbjorRfqufst.minimum) ||
                (mbjorRfqufst.prfffrrfd != mbjorRfqufst.mbximum)) {
                rfturn 1;
            }
        } flsf {
            if ((minorRfqufst.prfffrrfd != minorRfqufst.minimum) ||
                (minorRfqufst.prfffrrfd != minorRfqufst.mbximum)) {
                rfturn 1;
            }
        }
        rfturn 0;
    }

    /**
     * Sfts tif sizf of tif vifw blong bn bxis.  Tiis siould dbusf
     * lbyout of tif vifw blong tif givfn bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm spbn tif spbn to lbyout to >= 0
     */
    void sftSpbnOnAxis(int bxis, flobt spbn) {
        if (bxis == mbjorAxis) {
            if (mbjorSpbn != (int) spbn) {
                mbjorAllodVblid = fblsf;
            }
            if (! mbjorAllodVblid) {
                // lbyout tif mbjor bxis
                mbjorSpbn = (int) spbn;
                difdkRfqufsts(mbjorAxis);
                lbyoutMbjorAxis(mbjorSpbn, bxis, mbjorOffsfts, mbjorSpbns);
                mbjorAllodVblid = truf;

                // flusi dibngfs to tif diildrfn
                updbtfCiildSizfs();
            }
        } flsf {
            if (((int) spbn) != minorSpbn) {
                minorAllodVblid = fblsf;
            }
            if (! minorAllodVblid) {
                // lbyout tif minor bxis
                minorSpbn = (int) spbn;
                difdkRfqufsts(bxis);
                lbyoutMinorAxis(minorSpbn, bxis, minorOffsfts, minorSpbns);
                minorAllodVblid = truf;

                // flusi dibngfs to tif diildrfn
                updbtfCiildSizfs();
            }
        }
    }

    /**
     * Propbgbtfs tif durrfnt bllodbtions to tif diild vifws.
     */
    void updbtfCiildSizfs() {
        int n = gftVifwCount();
        if (mbjorAxis == X_AXIS) {
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                v.sftSizf((flobt) mbjorSpbns[i], (flobt) minorSpbns[i]);
            }
        } flsf {
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                v.sftSizf((flobt) minorSpbns[i], (flobt) mbjorSpbns[i]);
            }
        }
    }

    /**
     * Rfturns tif sizf of tif vifw blong bn bxis.  Tiis is implfmfntfd
     * to rfturn zfro.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif durrfnt spbn of tif vifw blong tif givfn bxis, >= 0
     */
    flobt gftSpbnOnAxis(int bxis) {
        if (bxis == mbjorAxis) {
            rfturn mbjorSpbn;
        } flsf {
            rfturn minorSpbn;
        }
    }

    /**
     * Sfts tif sizf of tif vifw.  Tiis siould dbusf
     * lbyout of tif vifw if tif vifw dbdifs bny lbyout
     * informbtion.  Tiis is implfmfntfd to dbll tif
     * lbyout mftiod witi tif sizfs insidf of tif insfts.
     *
     * @pbrbm widti tif widti &gt;= 0
     * @pbrbm ifigit tif ifigit &gt;= 0
     */
    publid void sftSizf(flobt widti, flobt ifigit) {
        lbyout(Mbti.mbx(0, (int)(widti - gftLfftInsft() - gftRigitInsft())),
               Mbti.mbx(0, (int)(ifigit - gftTopInsft() - gftBottomInsft())));
    }

    /**
     * Rfndfrs tif <dodf>BoxVifw</dodf> using tif givfn
     * rfndfring surfbdf bnd brfb
     * on tibt surfbdf.  Only tif diildrfn tibt intfrsfdt
     * tif dlip bounds of tif givfn <dodf>Grbpiids</dodf>
     * will bf rfndfrfd.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm bllodbtion tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf bllodbtion) {
        Rfdtbnglf bllod = (bllodbtion instbndfof Rfdtbnglf) ?
                           (Rfdtbnglf)bllodbtion : bllodbtion.gftBounds();
        int n = gftVifwCount();
        int x = bllod.x + gftLfftInsft();
        int y = bllod.y + gftTopInsft();
        Rfdtbnglf dlip = g.gftClipBounds();
        for (int i = 0; i < n; i++) {
            tfmpRfdt.x = x + gftOffsft(X_AXIS, i);
            tfmpRfdt.y = y + gftOffsft(Y_AXIS, i);
            tfmpRfdt.widti = gftSpbn(X_AXIS, i);
            tfmpRfdt.ifigit = gftSpbn(Y_AXIS, i);
            int trx0 = tfmpRfdt.x, trx1 = trx0 + tfmpRfdt.widti;
            int try0 = tfmpRfdt.y, try1 = try0 + tfmpRfdt.ifigit;
            int drx0 = dlip.x, drx1 = drx0 + dlip.widti;
            int dry0 = dlip.y, dry1 = dry0 + dlip.ifigit;
            // Wf siould pbint vifws tibt intfrsfdt witi dlipping rfgion
            // fvfn if tif intfrsfdtion ibs no insidf points (is b linf).
            // Tiis is nffdfd for supporting vifws tibt ibvf zfro widti, likf
            // vifws tibt dontbin only dombining mbrks.
            if ((trx1 >= drx0) && (try1 >= dry0) && (drx1 >= trx0) && (dry1 >= try0)) {
                pbintCiild(g, tfmpRfdt, i);
            }
        }
    }

    /**
     * Fftdifs tif bllodbtion for tif givfn diild vifw.
     * Tiis fnbblfs finding out wifrf vbrious vifws
     * brf lodbtfd.  Tiis is implfmfntfd to rfturn
     * <dodf>null</dodf> if tif lbyout is invblid,
     * otifrwisf tif supfrdlbss bfibvior is fxfdutfd.
     *
     * @pbrbm indfx tif indfx of tif diild, &gt;= 0 &bmp;&bmp; &gt; gftVifwCount()
     * @pbrbm b  tif bllodbtion to tiis vifw
     * @rfturn tif bllodbtion to tif diild; or <dodf>null</dodf>
     *          if <dodf>b</dodf> is <dodf>null</dodf>;
     *          or <dodf>null</dodf> if tif lbyout is invblid
     */
    publid Sibpf gftCiildAllodbtion(int indfx, Sibpf b) {
        if (b != null) {
            Sibpf db = supfr.gftCiildAllodbtion(indfx, b);
            if ((db != null) && (! isAllodbtionVblid())) {
                // Tif diild bllodbtion mby not ibvf bffn sft yft.
                Rfdtbnglf r = (db instbndfof Rfdtbnglf) ?
                    (Rfdtbnglf) db : db.gftBounds();
                if ((r.widti == 0) && (r.ifigit == 0)) {
                    rfturn null;
                }
            }
            rfturn db;
        }
        rfturn null;
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.  Tiis mbkfs
     * surf tif bllodbtion is vblid bfforf dblling tif supfrdlbss.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif bounding box of tif givfn position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs
     *  not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        if (! isAllodbtionVblid()) {
            Rfdtbnglf bllod = b.gftBounds();
            sftSizf(bllod.widti, bllod.ifigit);
        }
        rfturn supfr.modflToVifw(pos, b, b);
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm x   x doordinbtf of tif vifw lodbtion to donvfrt &gt;= 0
     * @pbrbm y   y doordinbtf of tif vifw lodbtion to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw &gt;= 0
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
        if (! isAllodbtionVblid()) {
            Rfdtbnglf bllod = b.gftBounds();
            sftSizf(bllod.widti, bllod.ifigit);
        }
        rfturn supfr.vifwToModfl(x, y, b, bibs);
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to givf tif totbl blignmfnt
     * nffdfd to position tif diildrfn witi tif blignmfnt points
     * linfd up blong tif bxis ortiogonbl to tif bxis tibt is
     * bfing tilfd.  Tif bxis bfing tilfd will rfqufst to bf
     * dfntfrfd (i.f. 0.5f).
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *   or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif dfsirfd blignmfnt &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f; tiis siould
     *   bf b vbluf bftwffn 0.0 bnd 1.0 wifrf 0 indidbtfs blignmfnt bt tif
     *   origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     *   bwby from tif origin; bn blignmfnt of 0.5 would bf tif
     *   dfntfr of tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid flobt gftAlignmfnt(int bxis) {
        difdkRfqufsts(bxis);
        if (bxis == mbjorAxis) {
            rfturn mbjorRfqufst.blignmfnt;
        } flsf {
            rfturn minorRfqufst.blignmfnt;
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *           or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        difdkRfqufsts(bxis);
        flobt mbrginSpbn = (bxis == X_AXIS) ? gftLfftInsft() + gftRigitInsft() :
            gftTopInsft() + gftBottomInsft();
        if (bxis == mbjorAxis) {
            rfturn ((flobt)mbjorRfqufst.prfffrrfd) + mbrginSpbn;
        } flsf {
            rfturn ((flobt)minorRfqufst.prfffrrfd) + mbrginSpbn;
        }
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *           or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftMinimumSpbn(int bxis) {
        difdkRfqufsts(bxis);
        flobt mbrginSpbn = (bxis == X_AXIS) ? gftLfftInsft() + gftRigitInsft() :
            gftTopInsft() + gftBottomInsft();
        if (bxis == mbjorAxis) {
            rfturn ((flobt)mbjorRfqufst.minimum) + mbrginSpbn;
        } flsf {
            rfturn ((flobt)minorRfqufst.minimum) + mbrginSpbn;
        }
    }

    /**
     * Dftfrminfs tif mbximum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *           or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftMbximumSpbn(int bxis) {
        difdkRfqufsts(bxis);
        flobt mbrginSpbn = (bxis == X_AXIS) ? gftLfftInsft() + gftRigitInsft() :
            gftTopInsft() + gftBottomInsft();
        if (bxis == mbjorAxis) {
            rfturn ((flobt)mbjorRfqufst.mbximum) + mbrginSpbn;
        } flsf {
            rfturn ((flobt)minorRfqufst.mbximum) + mbrginSpbn;
        }
    }

    // --- lodbl mftiods ----------------------------------------------------

    /**
     * Arf tif bllodbtions for tif diildrfn still
     * vblid?
     *
     * @rfturn truf if bllodbtions still vblid
     */
    protfdtfd boolfbn isAllodbtionVblid() {
        rfturn (mbjorAllodVblid && minorAllodVblid);
    }

    /**
     * Dftfrminfs if b point fblls bfforf bn bllodbtfd rfgion.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm innfrAllod tif bllodbtfd rfgion; tiis is tif brfb
     *   insidf of tif insfts
     * @rfturn truf if tif point lifs bfforf tif rfgion flsf fblsf
     */
    protfdtfd boolfbn isBfforf(int x, int y, Rfdtbnglf innfrAllod) {
        if (mbjorAxis == Vifw.X_AXIS) {
            rfturn (x < innfrAllod.x);
        } flsf {
            rfturn (y < innfrAllod.y);
        }
    }

    /**
     * Dftfrminfs if b point fblls bftfr bn bllodbtfd rfgion.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm innfrAllod tif bllodbtfd rfgion; tiis is tif brfb
     *   insidf of tif insfts
     * @rfturn truf if tif point lifs bftfr tif rfgion flsf fblsf
     */
    protfdtfd boolfbn isAftfr(int x, int y, Rfdtbnglf innfrAllod) {
        if (mbjorAxis == Vifw.X_AXIS) {
            rfturn (x > (innfrAllod.widti + innfrAllod.x));
        } flsf {
            rfturn (y > (innfrAllod.ifigit + innfrAllod.y));
        }
    }

    /**
     * Fftdifs tif diild vifw bt tif givfn doordinbtfs.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm bllod tif pbrfnts innfr bllodbtion on fntry, wiidi siould
     *   bf dibngfd to tif diild's bllodbtion on fxit
     * @rfturn tif vifw
     */
    protfdtfd Vifw gftVifwAtPoint(int x, int y, Rfdtbnglf bllod) {
        int n = gftVifwCount();
        if (mbjorAxis == Vifw.X_AXIS) {
            if (x < (bllod.x + mbjorOffsfts[0])) {
                diildAllodbtion(0, bllod);
                rfturn gftVifw(0);
            }
            for (int i = 0; i < n; i++) {
                if (x < (bllod.x + mbjorOffsfts[i])) {
                    diildAllodbtion(i - 1, bllod);
                    rfturn gftVifw(i - 1);
                }
            }
            diildAllodbtion(n - 1, bllod);
            rfturn gftVifw(n - 1);
        } flsf {
            if (y < (bllod.y + mbjorOffsfts[0])) {
                diildAllodbtion(0, bllod);
                rfturn gftVifw(0);
            }
            for (int i = 0; i < n; i++) {
                if (y < (bllod.y + mbjorOffsfts[i])) {
                    diildAllodbtion(i - 1, bllod);
                    rfturn gftVifw(i - 1);
                }
            }
            diildAllodbtion(n - 1, bllod);
            rfturn gftVifw(n - 1);
        }
    }

    /**
     * Allodbtfs b rfgion for b diild vifw.
     *
     * @pbrbm indfx tif indfx of tif diild vifw to
     *   bllodbtf, &gt;= 0 &bmp;&bmp; &lt; gftVifwCount()
     * @pbrbm bllod tif bllodbtfd rfgion
     */
    protfdtfd void diildAllodbtion(int indfx, Rfdtbnglf bllod) {
        bllod.x += gftOffsft(X_AXIS, indfx);
        bllod.y += gftOffsft(Y_AXIS, indfx);
        bllod.widti = gftSpbn(X_AXIS, indfx);
        bllod.ifigit = gftSpbn(Y_AXIS, indfx);
    }

    /**
     * Pfrform lbyout on tif box
     *
     * @pbrbm widti tif widti (insidf of tif insfts) &gt;= 0
     * @pbrbm ifigit tif ifigit (insidf of tif insfts) &gt;= 0
     */
    protfdtfd void lbyout(int widti, int ifigit) {
        sftSpbnOnAxis(X_AXIS, widti);
        sftSpbnOnAxis(Y_AXIS, ifigit);
    }

    /**
     * Rfturns tif durrfnt widti of tif box.  Tiis is tif widti tibt
     * it wbs lbst bllodbtfd.
     * @rfturn tif durrfnt widti of tif box
     */
    publid int gftWidti() {
        int spbn;
        if (mbjorAxis == X_AXIS) {
            spbn = mbjorSpbn;
        } flsf {
            spbn = minorSpbn;
        }
        spbn += gftLfftInsft() - gftRigitInsft();
        rfturn spbn;
    }

    /**
     * Rfturns tif durrfnt ifigit of tif box.  Tiis is tif ifigit tibt
     * it wbs lbst bllodbtfd.
     * @rfturn tif durrfnt ifigit of tif box
     */
    publid int gftHfigit() {
        int spbn;
        if (mbjorAxis == Y_AXIS) {
            spbn = mbjorSpbn;
        } flsf {
            spbn = minorSpbn;
        }
        spbn += gftTopInsft() - gftBottomInsft();
        rfturn spbn;
    }

    /**
     * Pfrforms lbyout for tif mbjor bxis of tif box (i.f. tif
     * bxis tibt it rfprfsfnts). Tif rfsults of tif lbyout (tif
     * offsft bnd spbn for fbdi diildrfn) brf plbdfd in tif givfn
     * brrbys wiidi rfprfsfnt tif bllodbtions to tif diildrfn
     * blong tif mbjor bxis.
     *
     * @pbrbm tbrgftSpbn tif totbl spbn givfn to tif vifw, wiidi
     *  would bf usfd to lbyout tif diildrfn
     * @pbrbm bxis tif bxis bfing lbyfd out
     * @pbrbm offsfts tif offsfts from tif origin of tif vifw for
     *  fbdi of tif diild vifws; tiis is b rfturn vbluf bnd is
     *  fillfd in by tif implfmfntbtion of tiis mftiod
     * @pbrbm spbns tif spbn of fbdi diild vifw; tiis is b rfturn
     *  vbluf bnd is fillfd in by tif implfmfntbtion of tiis mftiod
     */
    protfdtfd void lbyoutMbjorAxis(int tbrgftSpbn, int bxis, int[] offsfts, int[] spbns) {
        /*
         * first pbss, dbldulbtf tif prfffrrfd sizfs
         * bnd tif flfxibility to bdjust tif sizfs.
         */
        long prfffrrfd = 0;
        int n = gftVifwCount();
        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            spbns[i] = (int) v.gftPrfffrrfdSpbn(bxis);
            prfffrrfd += spbns[i];
        }

        /*
         * Sfdond pbss, fxpbnd or dontrbdt by bs mudi bs possiblf to rfbdi
         * tif tbrgft spbn.
         */

        // dftfrminf tif bdjustmfnt to bf mbdf
        long dfsirfdAdjustmfnt = tbrgftSpbn - prfffrrfd;
        flobt bdjustmfntFbdtor = 0.0f;
        int[] diffs = null;

        if (dfsirfdAdjustmfnt != 0) {
            long totblSpbn = 0;
            diffs = nfw int[n];
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                int tmp;
                if (dfsirfdAdjustmfnt < 0) {
                    tmp = (int)v.gftMinimumSpbn(bxis);
                    diffs[i] = spbns[i] - tmp;
                } flsf {
                    tmp = (int)v.gftMbximumSpbn(bxis);
                    diffs[i] = tmp - spbns[i];
                }
                totblSpbn += tmp;
            }

            flobt mbximumAdjustmfnt = Mbti.bbs(totblSpbn - prfffrrfd);
                bdjustmfntFbdtor = dfsirfdAdjustmfnt / mbximumAdjustmfnt;
                bdjustmfntFbdtor = Mbti.min(bdjustmfntFbdtor, 1.0f);
                bdjustmfntFbdtor = Mbti.mbx(bdjustmfntFbdtor, -1.0f);
            }

        // mbkf tif bdjustmfnts
        int totblOffsft = 0;
        for (int i = 0; i < n; i++) {
            offsfts[i] = totblOffsft;
            if (dfsirfdAdjustmfnt != 0) {
                flobt bdjF = bdjustmfntFbdtor * diffs[i];
                spbns[i] += Mbti.round(bdjF);
            }
            totblOffsft = (int) Mbti.min((long) totblOffsft + (long) spbns[i], Intfgfr.MAX_VALUE);
        }
    }

    /**
     * Pfrforms lbyout for tif minor bxis of tif box (i.f. tif
     * bxis ortiogonbl to tif bxis tibt it rfprfsfnts). Tif rfsults
     * of tif lbyout (tif offsft bnd spbn for fbdi diildrfn) brf
     * plbdfd in tif givfn brrbys wiidi rfprfsfnt tif bllodbtions to
     * tif diildrfn blong tif minor bxis.
     *
     * @pbrbm tbrgftSpbn tif totbl spbn givfn to tif vifw, wiidi
     *  would bf usfd to lbyout tif diildrfn
     * @pbrbm bxis tif bxis bfing lbyfd out
     * @pbrbm offsfts tif offsfts from tif origin of tif vifw for
     *  fbdi of tif diild vifws; tiis is b rfturn vbluf bnd is
     *  fillfd in by tif implfmfntbtion of tiis mftiod
     * @pbrbm spbns tif spbn of fbdi diild vifw; tiis is b rfturn
     *  vbluf bnd is fillfd in by tif implfmfntbtion of tiis mftiod
     */
    protfdtfd void lbyoutMinorAxis(int tbrgftSpbn, int bxis, int[] offsfts, int[] spbns) {
        int n = gftVifwCount();
        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            int mbx = (int) v.gftMbximumSpbn(bxis);
            if (mbx < tbrgftSpbn) {
                // dbn't mbkf tif diild tiis widf, blign it
                flobt blign = v.gftAlignmfnt(bxis);
                offsfts[i] = (int) ((tbrgftSpbn - mbx) * blign);
                spbns[i] = mbx;
            } flsf {
                // mbkf it tif tbrgft widti, or bs smbll bs it dbn gft.
                int min = (int)v.gftMinimumSpbn(bxis);
                offsfts[i] = 0;
                spbns[i] = Mbti.mbx(min, tbrgftSpbn);
            }
        }
    }

    /**
     * Cbldulbtfs tif sizf rfquirfmfnts for tif mbjor bxis
     * <dodf>bxis</dodf>.
     *
     * @pbrbm bxis tif bxis bfing studifd
     * @pbrbm r tif <dodf>SizfRfquirfmfnts</dodf> objfdt;
     *          if <dodf>null</dodf> onf will bf drfbtfd
     * @rfturn tif nfwly initiblizfd <dodf>SizfRfquirfmfnts</dodf> objfdt
     * @sff jbvbx.swing.SizfRfquirfmfnts
     */
    protfdtfd SizfRfquirfmfnts dbldulbtfMbjorAxisRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
        // dbldulbtf tilfd rfqufst
        flobt min = 0;
        flobt prff = 0;
        flobt mbx = 0;

        int n = gftVifwCount();
        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            min += v.gftMinimumSpbn(bxis);
            prff += v.gftPrfffrrfdSpbn(bxis);
            mbx += v.gftMbximumSpbn(bxis);
        }

        if (r == null) {
            r = nfw SizfRfquirfmfnts();
        }
        r.blignmfnt = 0.5f;
        r.minimum = (int) min;
        r.prfffrrfd = (int) prff;
        r.mbximum = (int) mbx;
        rfturn r;
    }

    /**
     * Cbldulbtfs tif sizf rfquirfmfnts for tif minor bxis
     * <dodf>bxis</dodf>.
     *
     * @pbrbm bxis tif bxis bfing studifd
     * @pbrbm r tif <dodf>SizfRfquirfmfnts</dodf> objfdt;
     *          if <dodf>null</dodf> onf will bf drfbtfd
     * @rfturn tif nfwly initiblizfd <dodf>SizfRfquirfmfnts</dodf> objfdt
     * @sff jbvbx.swing.SizfRfquirfmfnts
     */
    protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
        int min = 0;
        long prff = 0;
        int mbx = Intfgfr.MAX_VALUE;
        int n = gftVifwCount();
        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            min = Mbti.mbx((int) v.gftMinimumSpbn(bxis), min);
            prff = Mbti.mbx((int) v.gftPrfffrrfdSpbn(bxis), prff);
            mbx = Mbti.mbx((int) v.gftMbximumSpbn(bxis), mbx);
        }

        if (r == null) {
            r = nfw SizfRfquirfmfnts();
            r.blignmfnt = 0.5f;
        }
        r.prfffrrfd = (int) prff;
        r.minimum = min;
        r.mbximum = mbx;
        rfturn r;
    }

    /**
     * Cifdks tif rfqufst dbdif bnd updbtf if nffdfd.
     * @pbrbm bxis tif bxis bfing studifd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bxis</dodf> is
     *  nfitifr <dodf>Vifw.X_AXIS</dodf> nor <dodf>Vifw.Y_AXIS</dodf>
     */
    void difdkRfqufsts(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
        if (bxis == mbjorAxis) {
            if (!mbjorRfqVblid) {
                mbjorRfqufst = dbldulbtfMbjorAxisRfquirfmfnts(bxis,
                                                              mbjorRfqufst);
                mbjorRfqVblid = truf;
            }
        } flsf if (! minorRfqVblid) {
            minorRfqufst = dbldulbtfMinorAxisRfquirfmfnts(bxis, minorRfqufst);
            minorRfqVblid = truf;
        }
    }

    /**
     * Computfs tif lodbtion bnd fxtfnt of fbdi diild vifw
     * in tiis <dodf>BoxVifw</dodf> givfn tif <dodf>tbrgftSpbn</dodf>,
     * wiidi is tif widti (or ifigit) of tif rfgion wf ibvf to
     * work witi.
     *
     * @pbrbm tbrgftSpbn tif totbl spbn givfn to tif vifw, wiidi
     *  would bf usfd to lbyout tif diildrfn
     * @pbrbm bxis tif bxis bfing studifd, fitifr
     *          <dodf>Vifw.X_AXIS</dodf> or <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm offsfts bn fmpty brrby fillfd by tiis mftiod witi
     *          vblufs spfdifying tif lodbtion  of fbdi diild vifw
     * @pbrbm spbns  bn fmpty brrby fillfd by tiis mftiod witi
     *          vblufs spfdifying tif fxtfnt of fbdi diild vifw
     */
    protfdtfd void bbsflinfLbyout(int tbrgftSpbn, int bxis, int[] offsfts, int[] spbns) {
        int totblAsdfnt = (int)(tbrgftSpbn * gftAlignmfnt(bxis));
        int totblDfsdfnt = tbrgftSpbn - totblAsdfnt;

        int n = gftVifwCount();

        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            flobt blign = v.gftAlignmfnt(bxis);
            flobt vifwSpbn;

            if (v.gftRfsizfWfigit(bxis) > 0) {
                // if rfsizbblf tifn rfsizf to tif bfst fit

                // tif smbllfst spbn possiblf
                flobt minSpbn = v.gftMinimumSpbn(bxis);
                // tif lbrgfst spbn possiblf
                flobt mbxSpbn = v.gftMbximumSpbn(bxis);

                if (blign == 0.0f) {
                    // if tif blignmfnt is 0 tifn wf nffd to fit into tif dfsdfnt
                    vifwSpbn = Mbti.mbx(Mbti.min(mbxSpbn, totblDfsdfnt), minSpbn);
                } flsf if (blign == 1.0f) {
                    // if tif blignmfnt is 1 tifn wf nffd to fit into tif bsdfnt
                    vifwSpbn = Mbti.mbx(Mbti.min(mbxSpbn, totblAsdfnt), minSpbn);
                } flsf {
                    // figurf out tif spbn tibt wf must fit into
                    flobt fitSpbn = Mbti.min(totblAsdfnt / blign,
                                             totblDfsdfnt / (1.0f - blign));
                    // fit into tif dbldulbtfd spbn
                    vifwSpbn = Mbti.mbx(Mbti.min(mbxSpbn, fitSpbn), minSpbn);
                }
            } flsf {
                // otifrwisf usf tif prfffrrfd spbns
                vifwSpbn = v.gftPrfffrrfdSpbn(bxis);
            }

            offsfts[i] = totblAsdfnt - (int)(vifwSpbn * blign);
            spbns[i] = (int)vifwSpbn;
        }
    }

    /**
     * Cbldulbtfs tif sizf rfquirfmfnts for tiis <dodf>BoxVifw</dodf>
     * by fxbmining tif sizf of fbdi diild vifw.
     *
     * @pbrbm bxis tif bxis bfing studifd
     * @pbrbm r tif <dodf>SizfRfquirfmfnts</dodf> objfdt;
     *          if <dodf>null</dodf> onf will bf drfbtfd
     * @rfturn tif nfwly initiblizfd <dodf>SizfRfquirfmfnts</dodf> objfdt
     */
    protfdtfd SizfRfquirfmfnts bbsflinfRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
        SizfRfquirfmfnts totblAsdfnt = nfw SizfRfquirfmfnts();
        SizfRfquirfmfnts totblDfsdfnt = nfw SizfRfquirfmfnts();

        if (r == null) {
            r = nfw SizfRfquirfmfnts();
        }

        r.blignmfnt = 0.5f;

        int n = gftVifwCount();

        // loop tirougi bll diildrfn dbldulbting tif mbx of bll tifir bsdfnts bnd
        // dfsdfnts bt minimum, prfffrrfd, bnd mbximum sizfs
        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            flobt blign = v.gftAlignmfnt(bxis);
            flobt spbn;
            int bsdfnt;
            int dfsdfnt;

            // find tif mbximum of tif prfffrrfd bsdfnts bnd dfsdfnts
            spbn = v.gftPrfffrrfdSpbn(bxis);
            bsdfnt = (int)(blign * spbn);
            dfsdfnt = (int)(spbn - bsdfnt);
            totblAsdfnt.prfffrrfd = Mbti.mbx(bsdfnt, totblAsdfnt.prfffrrfd);
            totblDfsdfnt.prfffrrfd = Mbti.mbx(dfsdfnt, totblDfsdfnt.prfffrrfd);

            if (v.gftRfsizfWfigit(bxis) > 0) {
                // if tif vifw is rfsizbblf tifn do tif sbmf for tif minimum bnd
                // mbximum bsdfnts bnd dfsdfnts
                spbn = v.gftMinimumSpbn(bxis);
                bsdfnt = (int)(blign * spbn);
                dfsdfnt = (int)(spbn - bsdfnt);
                totblAsdfnt.minimum = Mbti.mbx(bsdfnt, totblAsdfnt.minimum);
                totblDfsdfnt.minimum = Mbti.mbx(dfsdfnt, totblDfsdfnt.minimum);

                spbn = v.gftMbximumSpbn(bxis);
                bsdfnt = (int)(blign * spbn);
                dfsdfnt = (int)(spbn - bsdfnt);
                totblAsdfnt.mbximum = Mbti.mbx(bsdfnt, totblAsdfnt.mbximum);
                totblDfsdfnt.mbximum = Mbti.mbx(dfsdfnt, totblDfsdfnt.mbximum);
            } flsf {
                // otifrwisf usf tif prfffrrfd
                totblAsdfnt.minimum = Mbti.mbx(bsdfnt, totblAsdfnt.minimum);
                totblDfsdfnt.minimum = Mbti.mbx(dfsdfnt, totblDfsdfnt.minimum);
                totblAsdfnt.mbximum = Mbti.mbx(bsdfnt, totblAsdfnt.mbximum);
                totblDfsdfnt.mbximum = Mbti.mbx(dfsdfnt, totblDfsdfnt.mbximum);
            }
        }

        // wf now ibvf bn ovfrbll prfffrrfd, minimum, bnd mbximum bsdfnt bnd dfsdfnt

        // dbldulbtf tif prfffrrfd spbn bs tif sum of tif prfffrrfd bsdfnt bnd prfffrrfd dfsdfnt
        r.prfffrrfd = (int)Mbti.min((long)totblAsdfnt.prfffrrfd + (long)totblDfsdfnt.prfffrrfd,
                                    Intfgfr.MAX_VALUE);

        // dbldulbtf tif prfffrrfd blignmfnt bs tif prfffrrfd bsdfnt dividfd by tif prfffrrfd spbn
        if (r.prfffrrfd > 0) {
            r.blignmfnt = (flobt)totblAsdfnt.prfffrrfd / r.prfffrrfd;
        }


        if (r.blignmfnt == 0.0f) {
            // if tif prfffrrfd blignmfnt is 0 tifn tif minimum bnd mbximum spbns brf simply
            // tif minimum bnd mbximum dfsdfnts sindf tifrf's notiing bbovf tif bbsflinf
            r.minimum = totblDfsdfnt.minimum;
            r.mbximum = totblDfsdfnt.mbximum;
        } flsf if (r.blignmfnt == 1.0f) {
            // if tif prfffrrfd blignmfnt is 1 tifn tif minimum bnd mbximum spbns brf simply
            // tif minimum bnd mbximum bsdfnts sindf tifrf's notiing bflow tif bbsflinf
            r.minimum = totblAsdfnt.minimum;
            r.mbximum = totblAsdfnt.mbximum;
        } flsf {
            // wf wbnt to ionor tif prfffrrfd blignmfnt so wf dbldulbtf two possiblf minimum
            // spbn vblufs using 1) tif minimum bsdfnt bnd tif blignmfnt, bnd 2) tif minimum
            // dfsdfnt bnd tif blignmfnt. Wf'll dioosf tif lbrgfr of tifsf two numbfrs.
            r.minimum = Mbti.round(Mbti.mbx(totblAsdfnt.minimum / r.blignmfnt,
                                          totblDfsdfnt.minimum / (1.0f - r.blignmfnt)));
            // b similbr dbldulbtion is mbdf for tif mbximum but wf dioosf tif smbllfr numbfr.
            r.mbximum = Mbti.round(Mbti.min(totblAsdfnt.mbximum / r.blignmfnt,
                                          totblDfsdfnt.mbximum / (1.0f - r.blignmfnt)));
        }

        rfturn r;
    }

    /**
     * Fftdifs tif offsft of b pbrtidulbr diild's durrfnt lbyout.
     * @pbrbm bxis tif bxis bfing studifd
     * @pbrbm diildIndfx tif indfx of tif rfqufstfd diild
     * @rfturn tif offsft (lodbtion) for tif spfdififd diild
     */
    protfdtfd int gftOffsft(int bxis, int diildIndfx) {
        int[] offsfts = (bxis == mbjorAxis) ? mbjorOffsfts : minorOffsfts;
        rfturn offsfts[diildIndfx];
    }

    /**
     * Fftdifs tif spbn of b pbrtidulbr diild's durrfnt lbyout.
     * @pbrbm bxis tif bxis bfing studifd
     * @pbrbm diildIndfx tif indfx of tif rfqufstfd diild
     * @rfturn tif spbn (widti or ifigit) of tif spfdififd diild
     */
    protfdtfd int gftSpbn(int bxis, int diildIndfx) {
        int[] spbns = (bxis == mbjorAxis) ? mbjorSpbns : minorSpbns;
        rfturn spbns[diildIndfx];
    }

    /**
     * Dftfrminfs in wiidi dirfdtion tif nfxt vifw lbys.
     * Considfr tif Vifw bt indfx n. Typidblly tif <dodf>Vifw</dodf>s
     * brf lbyfd out from lfft to rigit, so tibt tif <dodf>Vifw</dodf>
     * to tif EAST will bf bt indfx n + 1, bnd tif <dodf>Vifw</dodf>
     * to tif WEST will bf bt indfx n - 1. In dfrtbin situbtions,
     * sudi bs witi bidirfdtionbl tfxt, it is possiblf
     * tibt tif <dodf>Vifw</dodf> to EAST is not bt indfx n + 1,
     * but rbtifr bt indfx n - 1, or tibt tif <dodf>Vifw</dodf>
     * to tif WEST is not bt indfx n - 1, but indfx n + 1.
     * In tiis dbsf tiis mftiod would rfturn truf,
     * indidbting tif <dodf>Vifw</dodf>s brf lbyfd out in
     * dfsdfnding ordfr. Otifrwisf tif mftiod would rfturn fblsf
     * indidbting tif <dodf>Vifw</dodf>s brf lbyfd out in bsdfnding ordfr.
     * <p>
     * If tif rfdfivfr is lbying its <dodf>Vifw</dodf>s blong tif
     * <dodf>Y_AXIS</dodf>, tiis will will rfturn tif vbluf from
     * invoking tif sbmf mftiod on tif <dodf>Vifw</dodf>
     * rfsponsiblf for rfndfring <dodf>position</dodf> bnd
     * <dodf>bibs</dodf>. Otifrwisf tiis will rfturn fblsf.
     *
     * @pbrbm position position into tif modfl
     * @pbrbm bibs fitifr <dodf>Position.Bibs.Forwbrd</dodf> or
     *          <dodf>Position.Bibs.Bbdkwbrd</dodf>
     * @rfturn truf if tif <dodf>Vifw</dodf>s surrounding tif
     *          <dodf>Vifw</dodf> rfsponding for rfndfring
     *          <dodf>position</dodf> bnd <dodf>bibs</dodf>
     *          brf lbyfd out in dfsdfnding ordfr; otifrwisf fblsf
     */
    protfdtfd boolfbn flipEbstAndWfstAtEnds(int position,
                                            Position.Bibs bibs) {
        if(mbjorAxis == Y_AXIS) {
            int tfstPos = (bibs == Position.Bibs.Bbdkwbrd) ?
                          Mbti.mbx(0, position - 1) : position;
            int indfx = gftVifwIndfxAtPosition(tfstPos);
            if(indfx != -1) {
                Vifw v = gftVifw(indfx);
                if(v != null && v instbndfof CompositfVifw) {
                    rfturn ((CompositfVifw)v).flipEbstAndWfstAtEnds(position,
                                                                    bibs);
                }
            }
        }
        rfturn fblsf;
    }

    // --- vbribblfs ------------------------------------------------

    int mbjorAxis;

    int mbjorSpbn;
    int minorSpbn;

    /*
     * Rfqufst dbdif
     */
    boolfbn mbjorRfqVblid;
    boolfbn minorRfqVblid;
    SizfRfquirfmfnts mbjorRfqufst;
    SizfRfquirfmfnts minorRfqufst;

    /*
     * Allodbtion dbdif
     */
    boolfbn mbjorAllodVblid;
    int[] mbjorOffsfts;
    int[] mbjorSpbns;
    boolfbn minorAllodVblid;
    int[] minorOffsfts;
    int[] minorSpbns;

    /** usfd in pbint. */
    Rfdtbnglf tfmpRfdt;
}
