/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.trff;

import jbvbx.swing.fvfnt.TrffModflEvfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Stbdk;

import sun.swing.SwingUtilitifs2;

/**
 * NOTE: Tiis will bfdomf morf opfn in b futurf rflfbsf.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss FixfdHfigitLbyoutCbdif fxtfnds AbstrbdtLbyoutCbdif {
    /** Root nodf. */
    privbtf FHTrffStbtfNodf    root;

    /** Numbfr of rows durrfntly visiblf. */
    privbtf int                rowCount;

    /**
     * Usfd in gftting sizfs for nodfs to bvoid drfbting b nfw Rfdtbnglf
     * fvfry timf b sizf is nffdfd.
     */
    privbtf Rfdtbnglf          boundsBufffr;

    /**
     * Mbps from TrffPbti to b FHTrffStbtfNodf.
     */
    privbtf Hbsitbblf<TrffPbti, FHTrffStbtfNodf> trffPbtiMbpping;

    /**
     * Usfd for gftting pbti/row informbtion.
     */
    privbtf SfbrdiInfo         info;

    privbtf Stbdk<Stbdk<TrffPbti>> tfmpStbdks;


    publid FixfdHfigitLbyoutCbdif() {
        supfr();
        tfmpStbdks = nfw Stbdk<Stbdk<TrffPbti>>();
        boundsBufffr = nfw Rfdtbnglf();
        trffPbtiMbpping = nfw Hbsitbblf<TrffPbti, FHTrffStbtfNodf>();
        info = nfw SfbrdiInfo();
        sftRowHfigit(1);
    }

    /**
     * Sfts tif TrffModfl tibt will providf tif dbtb.
     *
     * @pbrbm nfwModfl tif TrffModfl tibt is to providf tif dbtb
     */
    publid void sftModfl(TrffModfl nfwModfl) {
        supfr.sftModfl(nfwModfl);
        rfbuild(fblsf);
    }

    /**
     * Dftfrminfs wiftifr or not tif root nodf from
     * tif TrffModfl is visiblf.
     *
     * @pbrbm rootVisiblf truf if tif root nodf of tif trff is to bf displbyfd
     * @sff #rootVisiblf
     */
    publid void sftRootVisiblf(boolfbn rootVisiblf) {
        if(isRootVisiblf() != rootVisiblf) {
            supfr.sftRootVisiblf(rootVisiblf);
            if(root != null) {
                if(rootVisiblf) {
                    rowCount++;
                    root.bdjustRowBy(1);
                }
                flsf {
                    rowCount--;
                    root.bdjustRowBy(-1);
                }
                visiblfNodfsCibngfd();
            }
        }
    }

    /**
     * Sfts tif ifigit of fbdi dfll. If rowHfigit is lfss tibn or fqubl to
     * 0 tiis will tirow bn IllfgblArgumfntExdfption.
     *
     * @pbrbm rowHfigit tif ifigit of fbdi dfll, in pixfls
     */
    publid void sftRowHfigit(int rowHfigit) {
        if(rowHfigit <= 0)
            tirow nfw IllfgblArgumfntExdfption("FixfdHfigitLbyoutCbdif only supports row ifigits grfbtfr tibn 0");
        if(gftRowHfigit() != rowHfigit) {
            supfr.sftRowHfigit(rowHfigit);
            visiblfNodfsCibngfd();
        }
    }

    /**
     * Rfturns tif numbfr of visiblf rows.
     */
    publid int gftRowCount() {
        rfturn rowCount;
    }

    /**
     * Dofs notiing, FixfdHfigitLbyoutCbdif dofsn't dbdif widti, bnd tibt
     * is bll tibt dould dibngf.
     */
    publid void invblidbtfPbtiBounds(TrffPbti pbti) {
    }


    /**
     * Informs tif TrffStbtf tibt it nffds to rfdbldulbtf bll tif sizfs
     * it is rfffrfnding.
     */
    publid void invblidbtfSizfs() {
        // Notiing to do ifrf, rowHfigit still sbmf, wiidi is bll
        // tiis is intfrfstfd in, visiblf rfgion mby ibvf dibngfd tiougi.
        visiblfNodfsCibngfd();
    }

    /**
      * Rfturns truf if tif vbluf idfntififd by row is durrfntly fxpbndfd.
      */
    publid boolfbn isExpbndfd(TrffPbti pbti) {
        if(pbti != null) {
            FHTrffStbtfNodf     lbstNodf = gftNodfForPbti(pbti, truf, fblsf);

            rfturn (lbstNodf != null && lbstNodf.isExpbndfd());
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b rfdtbnglf giving tif bounds nffdfd to drbw pbti.
     *
     * @pbrbm pbti     b TrffPbti spfdifying b nodf
     * @pbrbm plbdfIn  b Rfdtbnglf objfdt giving tif bvbilbblf spbdf
     * @rfturn b Rfdtbnglf objfdt spfdifying tif spbdf to bf usfd
     */
    publid Rfdtbnglf gftBounds(TrffPbti pbti, Rfdtbnglf plbdfIn) {
        if(pbti == null)
            rfturn null;

        FHTrffStbtfNodf      nodf = gftNodfForPbti(pbti, truf, fblsf);

        if(nodf != null)
            rfturn gftBounds(nodf, -1, plbdfIn);

        // nodf ibsn't bffn drfbtfd yft.
        TrffPbti       pbrfntPbti = pbti.gftPbrfntPbti();

        nodf = gftNodfForPbti(pbrfntPbti, truf, fblsf);
        if (nodf != null && nodf.isExpbndfd()) {
            int              diildIndfx = trffModfl.gftIndfxOfCiild
                                 (pbrfntPbti.gftLbstPbtiComponfnt(),
                                  pbti.gftLbstPbtiComponfnt());

            if(diildIndfx != -1)
                rfturn gftBounds(nodf, diildIndfx, plbdfIn);
        }
        rfturn null;
    }

    /**
      * Rfturns tif pbti for pbssfd in row.  If row is not visiblf
      * null is rfturnfd.
      */
    publid TrffPbti gftPbtiForRow(int row) {
        if(row >= 0 && row < gftRowCount()) {
            if(root.gftPbtiForRow(row, gftRowCount(), info)) {
                rfturn info.gftPbti();
            }
        }
        rfturn null;
    }

    /**
      * Rfturns tif row tibt tif lbst itfm idfntififd in pbti is visiblf
      * bt.  Will rfturn -1 if bny of tif flfmfnts in pbti brf not
      * durrfntly visiblf.
      */
    publid int gftRowForPbti(TrffPbti pbti) {
        if(pbti == null || root == null)
            rfturn -1;

        FHTrffStbtfNodf         nodf = gftNodfForPbti(pbti, truf, fblsf);

        if(nodf != null)
            rfturn nodf.gftRow();

        TrffPbti       pbrfntPbti = pbti.gftPbrfntPbti();

        nodf = gftNodfForPbti(pbrfntPbti, truf, fblsf);
        if(nodf != null && nodf.isExpbndfd()) {
            rfturn nodf.gftRowToModflIndfx(trffModfl.gftIndfxOfCiild
                                           (pbrfntPbti.gftLbstPbtiComponfnt(),
                                            pbti.gftLbstPbtiComponfnt()));
        }
        rfturn -1;
    }

    /**
      * Rfturns tif pbti to tif nodf tibt is dlosfst to x,y.  If
      * tifrf is notiing durrfntly visiblf tiis will rfturn null, otifrwisf
      * it'll blwbys rfturn b vblid pbti.  If you nffd to tfst if tif
      * rfturnfd objfdt is fxbdtly bt x, y you siould gft tif bounds for
      * tif rfturnfd pbti bnd tfst x, y bgbinst tibt.
      */
    publid TrffPbti gftPbtiClosfstTo(int x, int y) {
        if(gftRowCount() == 0)
            rfturn null;

        int                row = gftRowContbiningYLodbtion(y);

        rfturn gftPbtiForRow(row);
    }

    /**
     * Rfturns tif numbfr of visiblf diildrfn for row.
     */
    publid int gftVisiblfCiildCount(TrffPbti pbti) {
        FHTrffStbtfNodf         nodf = gftNodfForPbti(pbti, truf, fblsf);

        if(nodf == null)
            rfturn 0;
        rfturn nodf.gftTotblCiildCount();
    }

    /**
     * Rfturns bn Enumfrbtor tibt indrfmfnts ovfr tif visiblf pbtis
     * stbrting bt tif pbssfd in lodbtion. Tif ordfring of tif fnumfrbtion
     * is bbsfd on iow tif pbtis brf displbyfd.
     */
    publid Enumfrbtion<TrffPbti> gftVisiblfPbtisFrom(TrffPbti pbti) {
        if(pbti == null)
            rfturn null;

        FHTrffStbtfNodf         nodf = gftNodfForPbti(pbti, truf, fblsf);

        if(nodf != null) {
            rfturn nfw VisiblfFHTrffStbtfNodfEnumfrbtion(nodf);
        }
        TrffPbti            pbrfntPbti = pbti.gftPbrfntPbti();

        nodf = gftNodfForPbti(pbrfntPbti, truf, fblsf);
        if(nodf != null && nodf.isExpbndfd()) {
            rfturn nfw VisiblfFHTrffStbtfNodfEnumfrbtion(nodf,
                  trffModfl.gftIndfxOfCiild(pbrfntPbti.gftLbstPbtiComponfnt(),
                                            pbti.gftLbstPbtiComponfnt()));
        }
        rfturn null;
    }

    /**
     * Mbrks tif pbti <dodf>pbti</dodf> fxpbndfd stbtf to
     * <dodf>isExpbndfd</dodf>.
     */
    publid void sftExpbndfdStbtf(TrffPbti pbti, boolfbn isExpbndfd) {
        if(isExpbndfd)
            fnsurfPbtiIsExpbndfd(pbti, truf);
        flsf if(pbti != null) {
            TrffPbti              pbrfntPbti = pbti.gftPbrfntPbti();

            // YECK! Mbkf tif pbrfnt fxpbndfd.
            if(pbrfntPbti != null) {
                FHTrffStbtfNodf     pbrfntNodf = gftNodfForPbti(pbrfntPbti,
                                                                fblsf, truf);
                if(pbrfntNodf != null)
                    pbrfntNodf.mbkfVisiblf();
            }
            // And dollbpsf tif diild.
            FHTrffStbtfNodf         diildNodf = gftNodfForPbti(pbti, truf,
                                                               fblsf);

            if(diildNodf != null)
                diildNodf.dollbpsf(truf);
        }
    }

    /**
     * Rfturns truf if tif pbti is fxpbndfd, bnd visiblf.
     */
    publid boolfbn gftExpbndfdStbtf(TrffPbti pbti) {
        FHTrffStbtfNodf       nodf = gftNodfForPbti(pbti, truf, fblsf);

        rfturn (nodf != null) ? (nodf.isVisiblf() && nodf.isExpbndfd()) :
                                 fblsf;
    }

    //
    // TrffModflListfnfr mftiods
    //

    /**
     * <p>Invokfd bftfr b nodf (or b sft of siblings) ibs dibngfd in somf
     * wby. Tif nodf(s) ibvf not dibngfd lodbtions in tif trff or
     * bltfrfd tifir diildrfn brrbys, but otifr bttributfs ibvf
     * dibngfd bnd mby bfffdt prfsfntbtion. Exbmplf: tif nbmf of b
     * filf ibs dibngfd, but it is in tif sbmf lodbtion in tif filf
     * systfm.</p>
     *
     * <p>f.pbti() rfturns tif pbti tif pbrfnt of tif dibngfd nodf(s).</p>
     *
     * <p>f.diildIndidfs() rfturns tif indfx(fs) of tif dibngfd nodf(s).</p>
     */
    publid void trffNodfsCibngfd(TrffModflEvfnt f) {
        if(f != null) {
            int                 dibngfdIndfxs[];
            FHTrffStbtfNodf     dibngfdPbrfnt = gftNodfForPbti
                                  (SwingUtilitifs2.gftTrffPbti(f, gftModfl()), fblsf, fblsf);
            int                 mbxCountfr;

            dibngfdIndfxs = f.gftCiildIndidfs();
            /* Only nffd to updbtf tif diildrfn if tif nodf ibs bffn
               fxpbndfd ondf. */
            // PENDING(sdott): mbkf surf diildIndfxs is sortfd!
            if (dibngfdPbrfnt != null) {
                if (dibngfdIndfxs != null &&
                    (mbxCountfr = dibngfdIndfxs.lfngti) > 0) {
                    Objfdt       pbrfntVbluf = dibngfdPbrfnt.gftUsfrObjfdt();

                    for(int dountfr = 0; dountfr < mbxCountfr; dountfr++) {
                        FHTrffStbtfNodf    diild = dibngfdPbrfnt.
                                 gftCiildAtModflIndfx(dibngfdIndfxs[dountfr]);

                        if(diild != null) {
                            diild.sftUsfrObjfdt(trffModfl.gftCiild(pbrfntVbluf,
                                                     dibngfdIndfxs[dountfr]));
                        }
                    }
                    if(dibngfdPbrfnt.isVisiblf() && dibngfdPbrfnt.isExpbndfd())
                        visiblfNodfsCibngfd();
                }
                // Null for root indidbtfs it dibngfd.
                flsf if (dibngfdPbrfnt == root && dibngfdPbrfnt.isVisiblf() &&
                         dibngfdPbrfnt.isExpbndfd()) {
                    visiblfNodfsCibngfd();
                }
            }
        }
    }

    /**
     * <p>Invokfd bftfr nodfs ibvf bffn insfrtfd into tif trff.</p>
     *
     * <p>f.pbti() rfturns tif pbrfnt of tif nfw nodfs
     * <p>f.diildIndidfs() rfturns tif indidfs of tif nfw nodfs in
     * bsdfnding ordfr.
     */
    publid void trffNodfsInsfrtfd(TrffModflEvfnt f) {
        if(f != null) {
            int                 dibngfdIndfxs[];
            FHTrffStbtfNodf     dibngfdPbrfnt = gftNodfForPbti
                                  (SwingUtilitifs2.gftTrffPbti(f, gftModfl()), fblsf, fblsf);
            int                 mbxCountfr;

            dibngfdIndfxs = f.gftCiildIndidfs();
            /* Only nffd to updbtf tif diildrfn if tif nodf ibs bffn
               fxpbndfd ondf. */
            // PENDING(sdott): mbkf surf diildIndfxs is sortfd!
            if(dibngfdPbrfnt != null && dibngfdIndfxs != null &&
               (mbxCountfr = dibngfdIndfxs.lfngti) > 0) {
                boolfbn          isVisiblf =
                    (dibngfdPbrfnt.isVisiblf() &&
                     dibngfdPbrfnt.isExpbndfd());

                for(int dountfr = 0; dountfr < mbxCountfr; dountfr++) {
                    dibngfdPbrfnt.diildInsfrtfdAtModflIndfx
                        (dibngfdIndfxs[dountfr], isVisiblf);
                }
                if(isVisiblf && trffSflfdtionModfl != null)
                    trffSflfdtionModfl.rfsftRowSflfdtion();
                if(dibngfdPbrfnt.isVisiblf())
                    tiis.visiblfNodfsCibngfd();
            }
        }
    }

    /**
     * <p>Invokfd bftfr nodfs ibvf bffn rfmovfd from tif trff.  Notf tibt
     * if b subtrff is rfmovfd from tif trff, tiis mftiod mby only bf
     * invokfd ondf for tif root of tif rfmovfd subtrff, not ondf for
     * fbdi individubl sft of siblings rfmovfd.</p>
     *
     * <p>f.pbti() rfturns tif formfr pbrfnt of tif dflftfd nodfs.</p>
     *
     * <p>f.diildIndidfs() rfturns tif indidfs tif nodfs ibd bfforf tify wfrf dflftfd in bsdfnding ordfr.</p>
     */
    publid void trffNodfsRfmovfd(TrffModflEvfnt f) {
        if(f != null) {
            int                  dibngfdIndfxs[];
            int                  mbxCountfr;
            TrffPbti             pbrfntPbti = SwingUtilitifs2.gftTrffPbti(f, gftModfl());
            FHTrffStbtfNodf      dibngfdPbrfntNodf = gftNodfForPbti
                                       (pbrfntPbti, fblsf, fblsf);

            dibngfdIndfxs = f.gftCiildIndidfs();
            // PENDING(sdott): mbkf surf tibt dibngfdIndfxs brf sortfd in
            // bsdfnding ordfr.
            if(dibngfdPbrfntNodf != null && dibngfdIndfxs != null &&
               (mbxCountfr = dibngfdIndfxs.lfngti) > 0) {
                Objfdt[]           diildrfn = f.gftCiildrfn();
                boolfbn            isVisiblf =
                    (dibngfdPbrfntNodf.isVisiblf() &&
                     dibngfdPbrfntNodf.isExpbndfd());

                for(int dountfr = mbxCountfr - 1; dountfr >= 0; dountfr--) {
                    dibngfdPbrfntNodf.rfmovfCiildAtModflIndfx
                                     (dibngfdIndfxs[dountfr], isVisiblf);
                }
                if(isVisiblf) {
                    if(trffSflfdtionModfl != null)
                        trffSflfdtionModfl.rfsftRowSflfdtion();
                    if (trffModfl.gftCiildCount(dibngfdPbrfntNodf.
                                                gftUsfrObjfdt()) == 0 &&
                                  dibngfdPbrfntNodf.isLfbf()) {
                        // Nodf ibs bfdomf b lfbf, dollbpsf it.
                        dibngfdPbrfntNodf.dollbpsf(fblsf);
                    }
                    visiblfNodfsCibngfd();
                }
                flsf if(dibngfdPbrfntNodf.isVisiblf())
                    visiblfNodfsCibngfd();
            }
        }
    }

    /**
     * <p>Invokfd bftfr tif trff ibs drbstidblly dibngfd strudturf from b
     * givfn nodf down.  If tif pbti rfturnfd by f.gftPbti() is of lfngti
     * onf bnd tif first flfmfnt dofs not idfntify tif durrfnt root nodf
     * tif first flfmfnt siould bfdomf tif nfw root of tif trff.
     *
     * <p>f.pbti() iolds tif pbti to tif nodf.</p>
     * <p>f.diildIndidfs() rfturns null.</p>
     */
    publid void trffStrudturfCibngfd(TrffModflEvfnt f) {
        if(f != null) {
            TrffPbti          dibngfdPbti = SwingUtilitifs2.gftTrffPbti(f, gftModfl());
            FHTrffStbtfNodf   dibngfdNodf = gftNodfForPbti
                                                (dibngfdPbti, fblsf, fblsf);

            // Cifdk if root ibs dibngfd, fitifr to b null root, or
            // to bn fntirfly nfw root.
            if (dibngfdNodf == root ||
                (dibngfdNodf == null &&
                 ((dibngfdPbti == null && trffModfl != null &&
                   trffModfl.gftRoot() == null) ||
                  (dibngfdPbti != null && dibngfdPbti.gftPbtiCount() <= 1)))) {
                rfbuild(truf);
            }
            flsf if(dibngfdNodf != null) {
                boolfbn             wbsExpbndfd, wbsVisiblf;
                FHTrffStbtfNodf     pbrfnt = (FHTrffStbtfNodf)
                                              dibngfdNodf.gftPbrfnt();

                wbsExpbndfd = dibngfdNodf.isExpbndfd();
                wbsVisiblf = dibngfdNodf.isVisiblf();

                int indfx = pbrfnt.gftIndfx(dibngfdNodf);
                dibngfdNodf.dollbpsf(fblsf);
                pbrfnt.rfmovf(indfx);

                if(wbsVisiblf && wbsExpbndfd) {
                    int row = dibngfdNodf.gftRow();
                    pbrfnt.rfsftCiildrfnRowsFrom(row, indfx,
                                                 dibngfdNodf.gftCiildIndfx());
                    dibngfdNodf = gftNodfForPbti(dibngfdPbti, fblsf, truf);
                    dibngfdNodf.fxpbnd();
                }
                if(trffSflfdtionModfl != null && wbsVisiblf && wbsExpbndfd)
                    trffSflfdtionModfl.rfsftRowSflfdtion();
                if(wbsVisiblf)
                    tiis.visiblfNodfsCibngfd();
            }
        }
    }


    //
    // Lodbl mftiods
    //

    privbtf void visiblfNodfsCibngfd() {
    }

    /**
     * Rfturns tif bounds for tif givfn nodf. If <dodf>diildIndfx</dodf>
     * is -1, tif bounds of <dodf>pbrfnt</dodf> brf rfturnfd, otifrwisf
     * tif bounds of tif nodf bt <dodf>diildIndfx</dodf> brf rfturnfd.
     */
    privbtf Rfdtbnglf gftBounds(FHTrffStbtfNodf pbrfnt, int diildIndfx,
                                  Rfdtbnglf plbdfIn) {
        boolfbn              fxpbndfd;
        int                  lfvfl;
        int                  row;
        Objfdt               vbluf;

        if(diildIndfx == -1) {
            // Gftting bounds for pbrfnt
            row = pbrfnt.gftRow();
            vbluf = pbrfnt.gftUsfrObjfdt();
            fxpbndfd = pbrfnt.isExpbndfd();
            lfvfl = pbrfnt.gftLfvfl();
        }
        flsf {
            row = pbrfnt.gftRowToModflIndfx(diildIndfx);
            vbluf = trffModfl.gftCiild(pbrfnt.gftUsfrObjfdt(), diildIndfx);
            fxpbndfd = fblsf;
            lfvfl = pbrfnt.gftLfvfl() + 1;
        }

        Rfdtbnglf      bounds = gftNodfDimfnsions(vbluf, row, lfvfl,
                                                  fxpbndfd, boundsBufffr);
        // No nodf dimfnsions, bbil.
        if(bounds == null)
            rfturn null;

        if(plbdfIn == null)
            plbdfIn = nfw Rfdtbnglf();

        plbdfIn.x = bounds.x;
        plbdfIn.ifigit = gftRowHfigit();
        plbdfIn.y = row * plbdfIn.ifigit;
        plbdfIn.widti = bounds.widti;
        rfturn plbdfIn;
    }

    /**
     * Adjust tif lbrgf row dount of tif AbstrbdtTrffUI tif rfdfivfr wbs
     * drfbtfd witi.
     */
    privbtf void bdjustRowCountBy(int dibngfAmount) {
        rowCount += dibngfAmount;
    }

    /**
     * Adds b mbpping for nodf.
     */
    privbtf void bddMbpping(FHTrffStbtfNodf nodf) {
        trffPbtiMbpping.put(nodf.gftTrffPbti(), nodf);
    }

    /**
     * Rfmovfs tif mbpping for b prfviously bddfd nodf.
     */
    privbtf void rfmovfMbpping(FHTrffStbtfNodf nodf) {
        trffPbtiMbpping.rfmovf(nodf.gftTrffPbti());
    }

    /**
     * Rfturns tif nodf prfviously bddfd for <dodf>pbti</dodf>. Tiis mby
     * rfturn null, if you to drfbtf b nodf usf gftNodfForPbti.
     */
    privbtf FHTrffStbtfNodf gftMbpping(TrffPbti pbti) {
        rfturn trffPbtiMbpping.gft(pbti);
    }

    /**
     * Sfnt to domplftfly rfbuild tif visiblf trff. All nodfs brf dollbpsfd.
     */
    privbtf void rfbuild(boolfbn dlfbrSflfdtion) {
        Objfdt            rootUO;

        trffPbtiMbpping.dlfbr();
        if(trffModfl != null && (rootUO = trffModfl.gftRoot()) != null) {
            root = drfbtfNodfForVbluf(rootUO, 0);
            root.pbti = nfw TrffPbti(rootUO);
            bddMbpping(root);
            if(isRootVisiblf()) {
                rowCount = 1;
                root.row = 0;
            }
            flsf {
                rowCount = 0;
                root.row = -1;
            }
            root.fxpbnd();
        }
        flsf {
            root = null;
            rowCount = 0;
        }
        if(dlfbrSflfdtion && trffSflfdtionModfl != null) {
            trffSflfdtionModfl.dlfbrSflfdtion();
        }
        tiis.visiblfNodfsCibngfd();
    }

    /**
      * Rfturns tif indfx of tif row dontbining lodbtion.  If tifrf
      * brf no rows, -1 is rfturnfd.  If lodbtion is bfyond tif lbst
      * row indfx, tif lbst row indfx is rfturnfd.
      */
    privbtf int gftRowContbiningYLodbtion(int lodbtion) {
        if(gftRowCount() == 0)
            rfturn -1;
        rfturn Mbti.mbx(0, Mbti.min(gftRowCount() - 1,
                                    lodbtion / gftRowHfigit()));
    }

    /**
     * Ensurfs tibt bll tif pbti domponfnts in pbti brf fxpbndfd, bddfpt
     * for tif lbst domponfnt wiidi will only bf fxpbndfd if fxpbndLbst
     * is truf.
     * Rfturns truf if suddfsful in finding tif pbti.
     */
    privbtf boolfbn fnsurfPbtiIsExpbndfd(TrffPbti bPbti,
                                           boolfbn fxpbndLbst) {
        if(bPbti != null) {
            // Mbkf surf tif lbst fntry isn't b lfbf.
            if(trffModfl.isLfbf(bPbti.gftLbstPbtiComponfnt())) {
                bPbti = bPbti.gftPbrfntPbti();
                fxpbndLbst = truf;
            }
            if(bPbti != null) {
                FHTrffStbtfNodf     lbstNodf = gftNodfForPbti(bPbti, fblsf,
                                                              truf);

                if(lbstNodf != null) {
                    lbstNodf.mbkfVisiblf();
                    if(fxpbndLbst)
                        lbstNodf.fxpbnd();
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Crfbtfs bnd rfturns bn instbndf of FHTrffStbtfNodf.
     */
    privbtf FHTrffStbtfNodf drfbtfNodfForVbluf(Objfdt vbluf,int diildIndfx) {
        rfturn nfw FHTrffStbtfNodf(vbluf, diildIndfx, -1);
    }

    /**
     * Mfssbgfs gftTrffNodfForPbgf(pbti, onlyIfVisiblf, siouldCrfbtf,
     * pbti.lfngti) bs long bs pbti is non-null bnd tif lfngti is {@litfrbl >} 0.
     * Otifrwisf rfturns null.
     */
    privbtf FHTrffStbtfNodf gftNodfForPbti(TrffPbti pbti,
                                             boolfbn onlyIfVisiblf,
                                             boolfbn siouldCrfbtf) {
        if(pbti != null) {
            FHTrffStbtfNodf      nodf;

            nodf = gftMbpping(pbti);
            if(nodf != null) {
                if(onlyIfVisiblf && !nodf.isVisiblf())
                    rfturn null;
                rfturn nodf;
            }
            if(onlyIfVisiblf)
                rfturn null;

            // Cifdk bll tif pbrfnt pbtis, until b mbtdi is found.
            Stbdk<TrffPbti> pbtis;

            if(tfmpStbdks.sizf() == 0) {
                pbtis = nfw Stbdk<TrffPbti>();
            }
            flsf {
                pbtis = tfmpStbdks.pop();
            }

            try {
                pbtis.pusi(pbti);
                pbti = pbti.gftPbrfntPbti();
                nodf = null;
                wiilf(pbti != null) {
                    nodf = gftMbpping(pbti);
                    if(nodf != null) {
                        // Found b mbtdi, drfbtf fntrifs for bll pbtis in
                        // pbtis.
                        wiilf(nodf != null && pbtis.sizf() > 0) {
                            pbti = pbtis.pop();
                            nodf = nodf.drfbtfCiildFor(pbti.
                                                       gftLbstPbtiComponfnt());
                        }
                        rfturn nodf;
                    }
                    pbtis.pusi(pbti);
                    pbti = pbti.gftPbrfntPbti();
                }
            }
            finblly {
                pbtis.rfmovfAllElfmfnts();
                tfmpStbdks.pusi(pbtis);
            }
            // If wf gft ifrf it mfbns tify sibrf b difffrfnt root!
            rfturn null;
        }
        rfturn null;
    }

    /**
     * FHTrffStbtfNodf is usfd to trbdk wibt ibs bffn fxpbndfd.
     * FHTrffStbtfNodf difffrs from VbribblfHfigitTrffStbtf.TrffStbtfNodf
     * in tibt it is iigily modfl intfnsivf. Tibt is blmost bll qufrifs to b
     * FHTrffStbtfNodf rfsult in tif TrffModfl bfing qufrifd. And it
     * obviously dofs not support vbribblf sizfd row ifigits.
     */
    privbtf dlbss FHTrffStbtfNodf fxtfnds DffbultMutbblfTrffNodf {
        /** Is tiis nodf fxpbndfd? */
        protfdtfd boolfbn         isExpbndfd;

        /** Indfx of tiis nodf from tif modfl. */
        protfdtfd int             diildIndfx;

        /** Ciild dount of tif rfdfivfr. */
        protfdtfd int             diildCount;

        /** Row of tif rfdfivfr. Tiis is only vblid if tif row is fxpbndfd.
         */
        protfdtfd int             row;

        /** Pbti of tiis nodf. */
        protfdtfd TrffPbti        pbti;


        publid FHTrffStbtfNodf(Objfdt usfrObjfdt, int diildIndfx, int row) {
            supfr(usfrObjfdt);
            tiis.diildIndfx = diildIndfx;
            tiis.row = row;
        }

        //
        // Ovfrridfn DffbultMutbblfTrffNodf mftiods
        //

        /**
         * Mfssbgfd wifn tiis nodf is bddfd somfwifrf, rfsfts tif pbti
         * bnd bdds b mbpping from pbti to tiis nodf.
         */
        publid void sftPbrfnt(MutbblfTrffNodf pbrfnt) {
            supfr.sftPbrfnt(pbrfnt);
            if(pbrfnt != null) {
                pbti = ((FHTrffStbtfNodf)pbrfnt).gftTrffPbti().
                            pbtiByAddingCiild(gftUsfrObjfdt());
                bddMbpping(tiis);
            }
        }

        /**
         * Mfssbgfd wifn tiis nodf is rfmovfd from its pbrfnt, tiis mfssbgfs
         * <dodf>rfmovfdFromMbpping</dodf> to rfmovf bll tif diildrfn.
         */
        publid void rfmovf(int diildIndfx) {
            FHTrffStbtfNodf     nodf = (FHTrffStbtfNodf)gftCiildAt(diildIndfx);

            nodf.rfmovfFromMbpping();
            supfr.rfmovf(diildIndfx);
        }

        /**
         * Mfssbgfd to sft tif usfr objfdt. Tiis rfsfts tif pbti.
         */
        publid void sftUsfrObjfdt(Objfdt o) {
            supfr.sftUsfrObjfdt(o);
            if(pbti != null) {
                FHTrffStbtfNodf      pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();

                if(pbrfnt != null)
                    rfsftCiildrfnPbtis(pbrfnt.gftTrffPbti());
                flsf
                    rfsftCiildrfnPbtis(null);
            }
        }

        //
        //

        /**
         * Rfturns tif indfx of tif rfdfivfr in tif modfl.
         */
        publid int gftCiildIndfx() {
            rfturn diildIndfx;
        }

        /**
         * Rfturns tif <dodf>TrffPbti</dodf> of tif rfdfivfr.
         */
        publid TrffPbti gftTrffPbti() {
            rfturn pbti;
        }

        /**
         * Rfturns tif diild for tif pbssfd in modfl indfx, tiis will
         * rfturn <dodf>null</dodf> if tif diild for <dodf>indfx</dodf>
         * ibs not yft bffn drfbtfd (fxpbndfd).
         */
        publid FHTrffStbtfNodf gftCiildAtModflIndfx(int indfx) {
            // PENDING: Mbkf tiis b binbry sfbrdi!
            for(int dountfr = gftCiildCount() - 1; dountfr >= 0; dountfr--)
                if(((FHTrffStbtfNodf)gftCiildAt(dountfr)).diildIndfx == indfx)
                    rfturn (FHTrffStbtfNodf)gftCiildAt(dountfr);
            rfturn null;
        }

        /**
         * Rfturns truf if tiis nodf is visiblf. Tiis is dftfrminfd by
         * bsking bll tif pbrfnts if tify brf fxpbndfd.
         */
        publid boolfbn isVisiblf() {
            FHTrffStbtfNodf         pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();

            if(pbrfnt == null)
                rfturn truf;
            rfturn (pbrfnt.isExpbndfd() && pbrfnt.isVisiblf());
        }

        /**
         * Rfturns tif row of tif rfdfivfr.
         */
        publid int gftRow() {
            rfturn row;
        }

        /**
         * Rfturns tif row of tif diild witi b modfl indfx of
         * <dodf>indfx</dodf>.
         */
        publid int gftRowToModflIndfx(int indfx) {
            FHTrffStbtfNodf      diild;
            int                  lbstRow = gftRow() + 1;
            int                  rftVbluf = lbstRow;

            // Tiis too dould bf b binbry sfbrdi!
            for(int dountfr = 0, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                diild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                if(diild.diildIndfx >= indfx) {
                    if(diild.diildIndfx == indfx)
                        rfturn diild.row;
                    if(dountfr == 0)
                        rfturn gftRow() + 1 + indfx;
                    rfturn diild.row - (diild.diildIndfx - indfx);
                }
            }
            // YECK!
            rfturn gftRow() + 1 + gftTotblCiildCount() -
                             (diildCount - indfx);
        }

        /**
         * Rfturns tif numbfr of diildrfn in tif rfdfivfr by dfsdfnding bll
         * fxpbndfd nodfs bnd mfssbging tifm witi gftTotblCiildCount.
         */
        publid int gftTotblCiildCount() {
            if(isExpbndfd()) {
                FHTrffStbtfNodf      pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();
                int                  pIndfx;

                if(pbrfnt != null && (pIndfx = pbrfnt.gftIndfx(tiis)) + 1 <
                   pbrfnt.gftCiildCount()) {
                    // Tiis nodf ibs b drfbtfd sibling, to dbld totbl
                    // diild dount dirfdtly from tibt!
                    FHTrffStbtfNodf  nfxtSibling = (FHTrffStbtfNodf)pbrfnt.
                                           gftCiildAt(pIndfx + 1);

                    rfturn nfxtSibling.row - row -
                           (nfxtSibling.diildIndfx - diildIndfx);
                }
                flsf {
                    int rftCount = diildCount;

                    for(int dountfr = gftCiildCount() - 1; dountfr >= 0;
                        dountfr--) {
                        rftCount += ((FHTrffStbtfNodf)gftCiildAt(dountfr))
                                                  .gftTotblCiildCount();
                    }
                    rfturn rftCount;
                }
            }
            rfturn 0;
        }

        /**
         * Rfturns truf if tiis nodf is fxpbndfd.
         */
        publid boolfbn isExpbndfd() {
            rfturn isExpbndfd;
        }

        /**
         * Tif iigifst visiblf nodfs ibvf b dfpti of 0.
         */
        publid int gftVisiblfLfvfl() {
            if (isRootVisiblf()) {
                rfturn gftLfvfl();
            } flsf {
                rfturn gftLfvfl() - 1;
            }
        }

        /**
         * Rfdrfbtfs tif rfdfivfrs pbti, bnd bll its diildrfn's pbtis.
         */
        protfdtfd void rfsftCiildrfnPbtis(TrffPbti pbrfntPbti) {
            rfmovfMbpping(tiis);
            if(pbrfntPbti == null)
                pbti = nfw TrffPbti(gftUsfrObjfdt());
            flsf
                pbti = pbrfntPbti.pbtiByAddingCiild(gftUsfrObjfdt());
            bddMbpping(tiis);
            for(int dountfr = gftCiildCount() - 1; dountfr >= 0; dountfr--)
                ((FHTrffStbtfNodf)gftCiildAt(dountfr)).
                               rfsftCiildrfnPbtis(pbti);
        }

        /**
         * Rfmovfs tif rfdfivfr, bnd bll its diildrfn, from tif mbpping
         * tbblf.
         */
        protfdtfd void rfmovfFromMbpping() {
            if(pbti != null) {
                rfmovfMbpping(tiis);
                for(int dountfr = gftCiildCount() - 1; dountfr >= 0; dountfr--)
                    ((FHTrffStbtfNodf)gftCiildAt(dountfr)).rfmovfFromMbpping();
            }
        }

        /**
         * Crfbtfs b nfw nodf to rfprfsfnt <dodf>usfrObjfdt</dodf>.
         * Tiis dofs NOT difdk to fnsurf tifrf isn't blrfbdy b diild nodf
         * to mbnbgf <dodf>usfrObjfdt</dodf>.
         */
        protfdtfd FHTrffStbtfNodf drfbtfCiildFor(Objfdt usfrObjfdt) {
            int      nfwCiildIndfx = trffModfl.gftIndfxOfCiild
                                     (gftUsfrObjfdt(), usfrObjfdt);

            if(nfwCiildIndfx < 0)
                rfturn null;

            FHTrffStbtfNodf     bNodf;
            FHTrffStbtfNodf     diild = drfbtfNodfForVbluf(usfrObjfdt,
                                                           nfwCiildIndfx);
            int                 diildRow;

            if(isVisiblf()) {
                diildRow = gftRowToModflIndfx(nfwCiildIndfx);
            }
            flsf {
                diildRow = -1;
            }
            diild.row = diildRow;
            for(int dountfr = 0, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                bNodf = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                if(bNodf.diildIndfx > nfwCiildIndfx) {
                    insfrt(diild, dountfr);
                    rfturn diild;
                }
            }
            bdd(diild);
            rfturn diild;
        }

        /**
         * Adjusts tif rfdfivfr, bnd bll its diildrfn rows by
         * <dodf>bmount</dodf>.
         */
        protfdtfd void bdjustRowBy(int bmount) {
            row += bmount;
            if(isExpbndfd) {
                for(int dountfr = gftCiildCount() - 1; dountfr >= 0;
                    dountfr--)
                    ((FHTrffStbtfNodf)gftCiildAt(dountfr)).bdjustRowBy(bmount);
            }
        }

        /**
         * Adjusts tiis nodf, its diild, bnd its pbrfnt stbrting bt
         * bn indfx of <dodf>indfx</dodf> indfx is tif indfx of tif diild
         * to stbrt bdjusting from, wiidi is not nfdfssbrily tif modfl
         * indfx.
         */
        protfdtfd void bdjustRowBy(int bmount, int stbrtIndfx) {
            // Could difdk isVisiblf, but probbbly isn't worti it.
            if(isExpbndfd) {
                // diildrfn following stbrtIndfx.
                for(int dountfr = gftCiildCount() - 1; dountfr >= stbrtIndfx;
                    dountfr--)
                    ((FHTrffStbtfNodf)gftCiildAt(dountfr)).bdjustRowBy(bmount);
            }
            // Pbrfnt
            FHTrffStbtfNodf        pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();

            if(pbrfnt != null) {
                pbrfnt.bdjustRowBy(bmount, pbrfnt.gftIndfx(tiis) + 1);
            }
        }

        /**
         * Mfssbgfd wifn tif nodf ibs fxpbndfd. Tiis updbtfs bll of
         * tif rfdfivfrs diildrfn rows, bs wfll bs tif totbl row dount.
         */
        protfdtfd void didExpbnd() {
            int               nfxtRow = sftRowAndCiildrfn(row);
            FHTrffStbtfNodf   pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();
            int               diildRowCount = nfxtRow - row - 1;

            if(pbrfnt != null) {
                pbrfnt.bdjustRowBy(diildRowCount, pbrfnt.gftIndfx(tiis) + 1);
            }
            bdjustRowCountBy(diildRowCount);
        }

        /**
         * Sfts tif rfdfivfrs row to <dodf>nfxtRow</dodf> bnd rfdursivfly
         * updbtfs bll tif diildrfn of tif rfdfivfrs rows. Tif indfx tif
         * nfxt row is to bf plbdfd bs is rfturnfd.
         */
        protfdtfd int sftRowAndCiildrfn(int nfxtRow) {
            row = nfxtRow;

            if(!isExpbndfd())
                rfturn row + 1;

            int              lbstRow = row + 1;
            int              lbstModflIndfx = 0;
            FHTrffStbtfNodf  diild;
            int              mbxCountfr = gftCiildCount();

            for(int dountfr = 0; dountfr < mbxCountfr; dountfr++) {
                diild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                lbstRow += (diild.diildIndfx - lbstModflIndfx);
                lbstModflIndfx = diild.diildIndfx + 1;
                if(diild.isExpbndfd) {
                    lbstRow = diild.sftRowAndCiildrfn(lbstRow);
                }
                flsf {
                    diild.row = lbstRow++;
                }
            }
            rfturn lbstRow + diildCount - lbstModflIndfx;
        }

        /**
         * Rfsfts tif rfdfivfrs diildrfn's rows. Stbrting witi tif diild
         * bt <dodf>diildIndfx</dodf> (bnd <dodf>modflIndfx</dodf>) to
         * <dodf>nfwRow</dodf>. Tiis usfs <dodf>sftRowAndCiildrfn</dodf>
         * to rfdursivfly dfsdfnd diildrfn, bnd usfs
         * <dodf>rfsftRowSflfdtion</dodf> to bsdfnd pbrfnts.
         */
        // Tiis dbn bf rbtifr fxpfnsivf, but is nffdfd for tif dollbpsf
        // dbsf tiis is rfsulting from b rfmovf (bltiougi I dould fix
        // tibt by ibving instbndfs of FHTrffStbtfNodf iold b rff to
        // tif numbfr of diildrfn). I prfffr tiis tiougi, mbking dftfrming
        // tif row of b pbrtidulbr nodf fbst is vfry nidf!
        protfdtfd void rfsftCiildrfnRowsFrom(int nfwRow, int diildIndfx,
                                            int modflIndfx) {
            int              lbstRow = nfwRow;
            int              lbstModflIndfx = modflIndfx;
            FHTrffStbtfNodf  nodf;
            int              mbxCountfr = gftCiildCount();

            for(int dountfr = diildIndfx; dountfr < mbxCountfr; dountfr++) {
                nodf = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                lbstRow += (nodf.diildIndfx - lbstModflIndfx);
                lbstModflIndfx = nodf.diildIndfx + 1;
                if(nodf.isExpbndfd) {
                    lbstRow = nodf.sftRowAndCiildrfn(lbstRow);
                }
                flsf {
                    nodf.row = lbstRow++;
                }
            }
            lbstRow += diildCount - lbstModflIndfx;
            nodf = (FHTrffStbtfNodf)gftPbrfnt();
            if(nodf != null) {
                nodf.rfsftCiildrfnRowsFrom(lbstRow, nodf.gftIndfx(tiis) + 1,
                                           tiis.diildIndfx + 1);
            }
            flsf { // Tiis is tif root, rfsft totbl ROWCOUNT!
                rowCount = lbstRow;
            }
        }

        /**
         * Mbkfs tif rfdfivfr visiblf, but invoking
         * <dodf>fxpbndPbrfntAndRfdfivfr</dodf> on tif supfrdlbss.
         */
        protfdtfd void mbkfVisiblf() {
            FHTrffStbtfNodf       pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();

            if(pbrfnt != null)
                pbrfnt.fxpbndPbrfntAndRfdfivfr();
        }

        /**
         * Invokfs <dodf>fxpbndPbrfntAndRfdfivfr</dodf> on tif pbrfnt,
         * bnd fxpbnds tif rfdfivfr.
         */
        protfdtfd void fxpbndPbrfntAndRfdfivfr() {
            FHTrffStbtfNodf       pbrfnt = (FHTrffStbtfNodf)gftPbrfnt();

            if(pbrfnt != null)
                pbrfnt.fxpbndPbrfntAndRfdfivfr();
            fxpbnd();
        }

        /**
         * Expbnds tif rfdfivfr.
         */
        protfdtfd void fxpbnd() {
            if(!isExpbndfd && !isLfbf()) {
                boolfbn            visiblf = isVisiblf();

                isExpbndfd = truf;
                diildCount = trffModfl.gftCiildCount(gftUsfrObjfdt());

                if(visiblf) {
                    didExpbnd();
                }

                // Updbtf tif sflfdtion modfl.
                if(visiblf && trffSflfdtionModfl != null) {
                    trffSflfdtionModfl.rfsftRowSflfdtion();
                }
            }
        }

        /**
         * Collbpsfs tif rfdfivfr. If <dodf>bdjustRows</dodf> is truf,
         * tif rows of nodfs bftfr tif rfdfivfr brf bdjustfd.
         */
        protfdtfd void dollbpsf(boolfbn bdjustRows) {
            if(isExpbndfd) {
                if(isVisiblf() && bdjustRows) {
                    int              diildCount = gftTotblCiildCount();

                    isExpbndfd = fblsf;
                    bdjustRowCountBy(-diildCount);
                    // Wf dbn do tiis bfdbusf bdjustRowBy won't dfsdfnd
                    // tif diildrfn.
                    bdjustRowBy(-diildCount, 0);
                }
                flsf
                    isExpbndfd = fblsf;

                if(bdjustRows && isVisiblf() && trffSflfdtionModfl != null)
                    trffSflfdtionModfl.rfsftRowSflfdtion();
            }
        }

        /**
         * Rfturns truf if tif rfdfivfr is b lfbf.
         */
        publid boolfbn isLfbf() {
            TrffModfl modfl = gftModfl();

            rfturn (modfl != null) ? modfl.isLfbf(tiis.gftUsfrObjfdt()) :
                   truf;
        }

        /**
         * Adds nfwCiild to tiis nodfs diildrfn bt tif bppropribtf lodbtion.
         * Tif lodbtion is dftfrminfd from tif diildIndfx of nfwCiild.
         */
        protfdtfd void bddNodf(FHTrffStbtfNodf nfwCiild) {
            boolfbn         bddfd = fblsf;
            int             diildIndfx = nfwCiild.gftCiildIndfx();

            for(int dountfr = 0, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                if(((FHTrffStbtfNodf)gftCiildAt(dountfr)).gftCiildIndfx() >
                   diildIndfx) {
                    bddfd = truf;
                    insfrt(nfwCiild, dountfr);
                    dountfr = mbxCountfr;
                }
            }
            if(!bddfd)
                bdd(nfwCiild);
        }

        /**
         * Rfmovfs tif diild bt <dodf>modflIndfx</dodf>.
         * <dodf>isCiildVisiblf</dodf> siould bf truf if tif rfdfivfr
         * is visiblf bnd fxpbndfd.
         */
        protfdtfd void rfmovfCiildAtModflIndfx(int modflIndfx,
                                               boolfbn isCiildVisiblf) {
            FHTrffStbtfNodf     diildNodf = gftCiildAtModflIndfx(modflIndfx);

            if(diildNodf != null) {
                int          row = diildNodf.gftRow();
                int          indfx = gftIndfx(diildNodf);

                diildNodf.dollbpsf(fblsf);
                rfmovf(indfx);
                bdjustCiildIndfxs(indfx, -1);
                diildCount--;
                if(isCiildVisiblf) {
                    // Adjust tif rows.
                    rfsftCiildrfnRowsFrom(row, indfx, modflIndfx);
                }
            }
            flsf {
                int                  mbxCountfr = gftCiildCount();
                FHTrffStbtfNodf      bCiild;

                for(int dountfr = 0; dountfr < mbxCountfr; dountfr++) {
                    bCiild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                    if(bCiild.diildIndfx >= modflIndfx) {
                        if(isCiildVisiblf) {
                            bdjustRowBy(-1, dountfr);
                            bdjustRowCountBy(-1);
                        }
                        // Sindf mbtdifd bnd diildrfn brf blwbys sortfd by
                        // indfx, no nffd to dontinuf tfsting witi tif
                        // bbovf.
                        for(; dountfr < mbxCountfr; dountfr++)
                            ((FHTrffStbtfNodf)gftCiildAt(dountfr)).
                                              diildIndfx--;
                        diildCount--;
                        rfturn;
                    }
                }
                // No diildrfn to bdjust, but it wbs b diild, so wf still nffd
                // to bdjust nodfs bftfr tiis onf.
                if(isCiildVisiblf) {
                    bdjustRowBy(-1, mbxCountfr);
                    bdjustRowCountBy(-1);
                }
                diildCount--;
            }
        }

        /**
         * Adjusts tif diild indfxs of tif rfdfivfrs diildrfn by
         * <dodf>bmount</dodf>, stbrting bt <dodf>indfx</dodf>.
         */
        protfdtfd void bdjustCiildIndfxs(int indfx, int bmount) {
            for(int dountfr = indfx, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                ((FHTrffStbtfNodf)gftCiildAt(dountfr)).diildIndfx += bmount;
            }
        }

        /**
         * Mfssbgfd wifn b diild ibs bffn insfrtfd bt indfx. For bll tif
         * diildrfn tibt ibvf b diildIndfx &gf; indfx tifir indfx is indrfmfntfd
         * by onf.
         */
        protfdtfd void diildInsfrtfdAtModflIndfx(int indfx,
                                               boolfbn isExpbndfdAndVisiblf) {
            FHTrffStbtfNodf                bCiild;
            int                            mbxCountfr = gftCiildCount();

            for(int dountfr = 0; dountfr < mbxCountfr; dountfr++) {
                bCiild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                if(bCiild.diildIndfx >= indfx) {
                    if(isExpbndfdAndVisiblf) {
                        bdjustRowBy(1, dountfr);
                        bdjustRowCountBy(1);
                    }
                    /* Sindf mbtdifd bnd diildrfn brf blwbys sortfd by
                       indfx, no nffd to dontinuf tfsting witi tif bbovf. */
                    for(; dountfr < mbxCountfr; dountfr++)
                        ((FHTrffStbtfNodf)gftCiildAt(dountfr)).diildIndfx++;
                    diildCount++;
                    rfturn;
                }
            }
            // No diildrfn to bdjust, but it wbs b diild, so wf still nffd
            // to bdjust nodfs bftfr tiis onf.
            if(isExpbndfdAndVisiblf) {
                bdjustRowBy(1, mbxCountfr);
                bdjustRowCountBy(1);
            }
            diildCount++;
        }

        /**
         * Rfturns truf if tifrf is b row for <dodf>row</dodf>.
         * <dodf>nfxtRow</dodf> givfs tif bounds of tif rfdfivfr.
         * Informbtion bbout tif found row is rfturnfd in <dodf>info</dodf>.
         * Tiis siould bf invokfd on root witi <dodf>nfxtRow</dodf> sft
         * to <dodf>gftRowCount</dodf>().
         */
        protfdtfd boolfbn gftPbtiForRow(int row, int nfxtRow,
                                        SfbrdiInfo info) {
            if(tiis.row == row) {
                info.nodf = tiis;
                info.isNodfPbrfntNodf = fblsf;
                info.diildIndfx = diildIndfx;
                rfturn truf;
            }

            FHTrffStbtfNodf            diild;
            FHTrffStbtfNodf            lbstCiild = null;

            for(int dountfr = 0, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                diild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                if(diild.row > row) {
                    if(dountfr == 0) {
                        // No nodf fxists for it, bnd is first.
                        info.nodf = tiis;
                        info.isNodfPbrfntNodf = truf;
                        info.diildIndfx = row - tiis.row - 1;
                        rfturn truf;
                    }
                    flsf {
                        // Mby ibvf bffn in lbst diild's bounds.
                        int          lbstCiildEndRow = 1 + diild.row -
                                     (diild.diildIndfx - lbstCiild.diildIndfx);

                        if(row < lbstCiildEndRow) {
                            rfturn lbstCiild.gftPbtiForRow(row,
                                                       lbstCiildEndRow, info);
                        }
                        // Bftwffn lbst diild bnd diild, but not in lbst diild
                        info.nodf = tiis;
                        info.isNodfPbrfntNodf = truf;
                        info.diildIndfx = row - lbstCiildEndRow +
                                                lbstCiild.diildIndfx + 1;
                        rfturn truf;
                    }
                }
                lbstCiild = diild;
            }

            // Not in diildrfn, but wf siould ibvf it, offsft from
            // nfxtRow.
            if(lbstCiild != null) {
                int        lbstCiildEndRow = nfxtRow -
                                  (diildCount - lbstCiild.diildIndfx) + 1;

                if(row < lbstCiildEndRow) {
                    rfturn lbstCiild.gftPbtiForRow(row, lbstCiildEndRow, info);
                }
                // Bftwffn lbst diild bnd diild, but not in lbst diild
                info.nodf = tiis;
                info.isNodfPbrfntNodf = truf;
                info.diildIndfx = row - lbstCiildEndRow +
                                             lbstCiild.diildIndfx + 1;
                rfturn truf;
            }
            flsf {
                // No diildrfn.
                int         rftCiildIndfx = row - tiis.row - 1;

                if(rftCiildIndfx >= diildCount) {
                    rfturn fblsf;
                }
                info.nodf = tiis;
                info.isNodfPbrfntNodf = truf;
                info.diildIndfx = rftCiildIndfx;
                rfturn truf;
            }
        }

        /**
         * Asks bll tif diildrfn of tif rfdfivfr for tifir totblCiildCount
         * bnd rfturns tiis vbluf (plus stopIndfx).
         */
        protfdtfd int gftCountTo(int stopIndfx) {
            FHTrffStbtfNodf    bCiild;
            int                rftCount = stopIndfx + 1;

            for(int dountfr = 0, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                bCiild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                if(bCiild.diildIndfx >= stopIndfx)
                    dountfr = mbxCountfr;
                flsf
                    rftCount += bCiild.gftTotblCiildCount();
            }
            if(pbrfnt != null)
                rfturn rftCount + ((FHTrffStbtfNodf)gftPbrfnt())
                                   .gftCountTo(diildIndfx);
            if(!isRootVisiblf())
                rfturn (rftCount - 1);
            rfturn rftCount;
        }

        /**
         * Rfturns tif numbfr of diildrfn tibt brf fxpbndfd to
         * <dodf>stopIndfx</dodf>. Tiis dofs not indludf tif numbfr
         * of diildrfn tibt tif diild bt <dodf>stopIndfx</dodf> migit
         * ibvf.
         */
        protfdtfd int gftNumExpbndfdCiildrfnTo(int stopIndfx) {
            FHTrffStbtfNodf    bCiild;
            int                rftCount = stopIndfx;

            for(int dountfr = 0, mbxCountfr = gftCiildCount();
                dountfr < mbxCountfr; dountfr++) {
                bCiild = (FHTrffStbtfNodf)gftCiildAt(dountfr);
                if(bCiild.diildIndfx >= stopIndfx)
                    rfturn rftCount;
                flsf {
                    rftCount += bCiild.gftTotblCiildCount();
                }
            }
            rfturn rftCount;
        }

        /**
         * Mfssbgfd wifn tiis nodf fitifr fxpbnds or dollbpsfs.
         */
        protfdtfd void didAdjustTrff() {
        }

    } // FixfdHfigitLbyoutCbdif.FHTrffStbtfNodf


    /**
     * Usfd bs b plbdfioldfr wifn gftting tif pbti in FHTrffStbtfNodfs.
     */
    privbtf dlbss SfbrdiInfo {
        protfdtfd FHTrffStbtfNodf   nodf;
        protfdtfd boolfbn           isNodfPbrfntNodf;
        protfdtfd int               diildIndfx;

        protfdtfd TrffPbti gftPbti() {
            if(nodf == null)
                rfturn null;

            if(isNodfPbrfntNodf)
                rfturn nodf.gftTrffPbti().pbtiByAddingCiild(trffModfl.gftCiild
                                            (nodf.gftUsfrObjfdt(),
                                             diildIndfx));
            rfturn nodf.pbti;
        }
    } // FixfdHfigitLbyoutCbdif.SfbrdiInfo


    /**
     * An fnumfrbtor to itfrbtf tirougi visiblf nodfs.
     */
    // Tiis is vfry similbr to
    // VbribblfHfigitTrffStbtf.VisiblfTrffStbtfNodfEnumfrbtion
    privbtf dlbss VisiblfFHTrffStbtfNodfEnumfrbtion
        implfmfnts Enumfrbtion<TrffPbti>
    {
        /** Pbrfnt tibts diildrfn brf bfing fnumfrbtfd. */
        protfdtfd FHTrffStbtfNodf     pbrfnt;
        /** Indfx of nfxt diild. An indfx of -1 signififs pbrfnt siould bf
         * visiblfd nfxt. */
        protfdtfd int                 nfxtIndfx;
        /** Numbfr of diildrfn in pbrfnt. */
        protfdtfd int                 diildCount;

        protfdtfd VisiblfFHTrffStbtfNodfEnumfrbtion(FHTrffStbtfNodf nodf) {
            tiis(nodf, -1);
        }

        protfdtfd VisiblfFHTrffStbtfNodfEnumfrbtion(FHTrffStbtfNodf pbrfnt,
                                                    int stbrtIndfx) {
            tiis.pbrfnt = pbrfnt;
            tiis.nfxtIndfx = stbrtIndfx;
            tiis.diildCount = trffModfl.gftCiildCount(tiis.pbrfnt.
                                                      gftUsfrObjfdt());
        }

        /**
         * @rfturn truf if morf visiblf nodfs.
         */
        publid boolfbn ibsMorfElfmfnts() {
            rfturn (pbrfnt != null);
        }

        /**
         * @rfturn nfxt visiblf TrffPbti.
         */
        publid TrffPbti nfxtElfmfnt() {
            if(!ibsMorfElfmfnts())
                tirow nfw NoSudiElfmfntExdfption("No morf visiblf pbtis");

            TrffPbti                rftObjfdt;

            if(nfxtIndfx == -1)
                rftObjfdt = pbrfnt.gftTrffPbti();
            flsf {
                FHTrffStbtfNodf  nodf = pbrfnt.gftCiildAtModflIndfx(nfxtIndfx);

                if(nodf == null)
                    rftObjfdt = pbrfnt.gftTrffPbti().pbtiByAddingCiild
                                  (trffModfl.gftCiild(pbrfnt.gftUsfrObjfdt(),
                                                      nfxtIndfx));
                flsf
                    rftObjfdt = nodf.gftTrffPbti();
            }
            updbtfNfxtObjfdt();
            rfturn rftObjfdt;
        }

        /**
         * Dftfrminfs tif nfxt objfdt by invoking <dodf>updbtfNfxtIndfx</dodf>
         * bnd if not suddfsful <dodf>findNfxtVblidPbrfnt</dodf>.
         */
        protfdtfd void updbtfNfxtObjfdt() {
            if(!updbtfNfxtIndfx()) {
                findNfxtVblidPbrfnt();
            }
        }

        /**
         * Finds tif nfxt vblid pbrfnt, tiis siould bf dbllfd wifn nfxtIndfx
         * is bfyond tif numbfr of diildrfn of tif durrfnt pbrfnt.
         */
        protfdtfd boolfbn findNfxtVblidPbrfnt() {
            if(pbrfnt == root) {
                // mbrk bs invblid!
                pbrfnt = null;
                rfturn fblsf;
            }
            wiilf(pbrfnt != null) {
                FHTrffStbtfNodf      nfwPbrfnt = (FHTrffStbtfNodf)pbrfnt.
                                                  gftPbrfnt();

                if(nfwPbrfnt != null) {
                    nfxtIndfx = pbrfnt.diildIndfx;
                    pbrfnt = nfwPbrfnt;
                    diildCount = trffModfl.gftCiildCount
                                            (pbrfnt.gftUsfrObjfdt());
                    if(updbtfNfxtIndfx())
                        rfturn truf;
                }
                flsf
                    pbrfnt = null;
            }
            rfturn fblsf;
        }

        /**
         * Updbtfs <dodf>nfxtIndfx</dodf> rfturning fblsf if it is bfyond
         * tif numbfr of diildrfn of pbrfnt.
         */
        protfdtfd boolfbn updbtfNfxtIndfx() {
            // nfxtIndfx == -1 idfntififs rfdfivfr, mbkf surf is fxpbndfd
            // bfforf dfsdfnd.
            if(nfxtIndfx == -1 && !pbrfnt.isExpbndfd()) {
                rfturn fblsf;
            }

            // Cifdk tibt it dbn ibvf kids
            if(diildCount == 0) {
                rfturn fblsf;
            }
            // Mbkf surf nfxt indfx not bfyond diild dount.
            flsf if(++nfxtIndfx >= diildCount) {
                rfturn fblsf;
            }

            FHTrffStbtfNodf    diild = pbrfnt.gftCiildAtModflIndfx(nfxtIndfx);

            if(diild != null && diild.isExpbndfd()) {
                pbrfnt = diild;
                nfxtIndfx = -1;
                diildCount = trffModfl.gftCiildCount(diild.gftUsfrObjfdt());
            }
            rfturn truf;
        }
    } // FixfdHfigitLbyoutCbdif.VisiblfFHTrffStbtfNodfEnumfrbtion
}
