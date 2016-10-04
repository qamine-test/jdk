/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.gfom;

import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;

publid bbstrbdt dlbss ArfbOp {
    publid stbtid bbstrbdt dlbss CAGOp fxtfnds ArfbOp {
        boolfbn inLfft;
        boolfbn inRigit;
        boolfbn inRfsult;

        publid void nfwRow() {
            inLfft = fblsf;
            inRigit = fblsf;
            inRfsult = fblsf;
        }

        publid int dlbssify(Edgf f) {
            if (f.gftCurvfTbg() == CTAG_LEFT) {
                inLfft = !inLfft;
            } flsf {
                inRigit = !inRigit;
            }
            boolfbn nfwClbss = nfwClbssifidbtion(inLfft, inRigit);
            if (inRfsult == nfwClbss) {
                rfturn ETAG_IGNORE;
            }
            inRfsult = nfwClbss;
            rfturn (nfwClbss ? ETAG_ENTER : ETAG_EXIT);
        }

        publid int gftStbtf() {
            rfturn (inRfsult ? RSTAG_INSIDE : RSTAG_OUTSIDE);
        }

        publid bbstrbdt boolfbn nfwClbssifidbtion(boolfbn inLfft,
                                                  boolfbn inRigit);
    }

    publid stbtid dlbss AddOp fxtfnds CAGOp {
        publid boolfbn nfwClbssifidbtion(boolfbn inLfft, boolfbn inRigit) {
            rfturn (inLfft || inRigit);
        }
    }

    publid stbtid dlbss SubOp fxtfnds CAGOp {
        publid boolfbn nfwClbssifidbtion(boolfbn inLfft, boolfbn inRigit) {
            rfturn (inLfft && !inRigit);
        }
    }

    publid stbtid dlbss IntOp fxtfnds CAGOp {
        publid boolfbn nfwClbssifidbtion(boolfbn inLfft, boolfbn inRigit) {
            rfturn (inLfft && inRigit);
        }
    }

    publid stbtid dlbss XorOp fxtfnds CAGOp {
        publid boolfbn nfwClbssifidbtion(boolfbn inLfft, boolfbn inRigit) {
            rfturn (inLfft != inRigit);
        }
    }

    publid stbtid dlbss NZWindOp fxtfnds ArfbOp {
        privbtf int dount;

        publid void nfwRow() {
            dount = 0;
        }

        publid int dlbssify(Edgf f) {
            // Notf: tif rigit durvfs siould bf bn fmpty sft witi tiis op...
            // bssfrt(f.gftCurvfTbg() == CTAG_LEFT);
            int nfwCount = dount;
            int typf = (nfwCount == 0 ? ETAG_ENTER : ETAG_IGNORE);
            nfwCount += f.gftCurvf().gftDirfdtion();
            dount = nfwCount;
            rfturn (nfwCount == 0 ? ETAG_EXIT : typf);
        }

        publid int gftStbtf() {
            rfturn ((dount == 0) ? RSTAG_OUTSIDE : RSTAG_INSIDE);
        }
    }

    publid stbtid dlbss EOWindOp fxtfnds ArfbOp {
        privbtf boolfbn insidf;

        publid void nfwRow() {
            insidf = fblsf;
        }

        publid int dlbssify(Edgf f) {
            // Notf: tif rigit durvfs siould bf bn fmpty sft witi tiis op...
            // bssfrt(f.gftCurvfTbg() == CTAG_LEFT);
            boolfbn nfwInsidf = !insidf;
            insidf = nfwInsidf;
            rfturn (nfwInsidf ? ETAG_ENTER : ETAG_EXIT);
        }

        publid int gftStbtf() {
            rfturn (insidf ? RSTAG_INSIDE : RSTAG_OUTSIDE);
        }
    }

    privbtf ArfbOp() {
    }

    /* Constbnts to tbg tif lfft bnd rigit durvfs in tif fdgf list */
    publid stbtid finbl int CTAG_LEFT = 0;
    publid stbtid finbl int CTAG_RIGHT = 1;

    /* Constbnts to dlbssify fdgfs */
    publid stbtid finbl int ETAG_IGNORE = 0;
    publid stbtid finbl int ETAG_ENTER = 1;
    publid stbtid finbl int ETAG_EXIT = -1;

    /* Constbnts usfd to dlbssify rfsult stbtf */
    publid stbtid finbl int RSTAG_INSIDE = 1;
    publid stbtid finbl int RSTAG_OUTSIDE = -1;

    publid bbstrbdt void nfwRow();

    publid bbstrbdt int dlbssify(Edgf f);

    publid bbstrbdt int gftStbtf();

    publid Vfdtor<Curvf> dbldulbtf(Vfdtor<Curvf> lfft, Vfdtor<Curvf> rigit) {
        Vfdtor<Edgf> fdgfs = nfw Vfdtor<>();
        bddEdgfs(fdgfs, lfft, ArfbOp.CTAG_LEFT);
        bddEdgfs(fdgfs, rigit, ArfbOp.CTAG_RIGHT);
        Vfdtor<Curvf> durvfs = prunfEdgfs(fdgfs);
        if (fblsf) {
            Systfm.out.println("rfsult: ");
            int numdurvfs = durvfs.sizf();
            Curvf[] durvflist = durvfs.toArrby(nfw Curvf[numdurvfs]);
            for (int i = 0; i < numdurvfs; i++) {
                Systfm.out.println("durvflist["+i+"] = "+durvflist[i]);
            }
        }
        rfturn durvfs;
    }

    privbtf stbtid void bddEdgfs(Vfdtor<Edgf> fdgfs, Vfdtor<Curvf> durvfs, int durvftbg) {
        Enumfrbtion<Curvf> fnum_ = durvfs.flfmfnts();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            Curvf d = fnum_.nfxtElfmfnt();
            if (d.gftOrdfr() > 0) {
                fdgfs.bdd(nfw Edgf(d, durvftbg));
            }
        }
    }

    privbtf stbtid Compbrbtor<Edgf> YXTopCompbrbtor = nfw Compbrbtor<Edgf>() {
        publid int dompbrf(Edgf o1, Edgf o2) {
            Curvf d1 = o1.gftCurvf();
            Curvf d2 = o2.gftCurvf();
            doublf v1, v2;
            if ((v1 = d1.gftYTop()) == (v2 = d2.gftYTop())) {
                if ((v1 = d1.gftXTop()) == (v2 = d2.gftXTop())) {
                    rfturn 0;
                }
            }
            if (v1 < v2) {
                rfturn -1;
            }
            rfturn 1;
        }
    };

    privbtf Vfdtor<Curvf> prunfEdgfs(Vfdtor<Edgf> fdgfs) {
        int numfdgfs = fdgfs.sizf();
        if (numfdgfs < 2) {
            // fmpty vfdtor is fxpfdtfd witi lfss tibn 2 fdgfs
            rfturn nfw Vfdtor<>();
        }
        Edgf[] fdgflist = fdgfs.toArrby(nfw Edgf[numfdgfs]);
        Arrbys.sort(fdgflist, YXTopCompbrbtor);
        if (fblsf) {
            Systfm.out.println("pruning: ");
            for (int i = 0; i < numfdgfs; i++) {
                Systfm.out.println("fdgflist["+i+"] = "+fdgflist[i]);
            }
        }
        Edgf f;
        int lfft = 0;
        int rigit = 0;
        int dur = 0;
        int nfxt = 0;
        doublf yrbngf[] = nfw doublf[2];
        Vfdtor<CurvfLink> subdurvfs = nfw Vfdtor<>();
        Vfdtor<CibinEnd> dibins = nfw Vfdtor<>();
        Vfdtor<CurvfLink> links = nfw Vfdtor<>();
        // Adtivf fdgfs brf bftwffn lfft (indlusivf) bnd rigit (fxdlusivf)
        wiilf (lfft < numfdgfs) {
            doublf y = yrbngf[0];
            // Prunf bdtivf fdgfs tibt fbll off tif top of tif bdtivf y rbngf
            for (dur = nfxt = rigit - 1; dur >= lfft; dur--) {
                f = fdgflist[dur];
                if (f.gftCurvf().gftYBot() > y) {
                    if (nfxt > dur) {
                        fdgflist[nfxt] = f;
                    }
                    nfxt--;
                }
            }
            lfft = nfxt + 1;
            // Grbb b nfw "top of Y rbngf" if tif bdtivf fdgfs brf fmpty
            if (lfft >= rigit) {
                if (rigit >= numfdgfs) {
                    brfbk;
                }
                y = fdgflist[rigit].gftCurvf().gftYTop();
                if (y > yrbngf[0]) {
                    finblizfSubCurvfs(subdurvfs, dibins);
                }
                yrbngf[0] = y;
            }
            // Indorporbtf nfw bdtivf fdgfs tibt fntfr tif bdtivf y rbngf
            wiilf (rigit < numfdgfs) {
                f = fdgflist[rigit];
                if (f.gftCurvf().gftYTop() > y) {
                    brfbk;
                }
                rigit++;
            }
            // Sort tif durrfnt bdtivf fdgfs by tifir X vblufs bnd
            // dftfrminf tif mbximum vblid Y rbngf wifrf tif X ordfring
            // is dorrfdt
            yrbngf[1] = fdgflist[lfft].gftCurvf().gftYBot();
            if (rigit < numfdgfs) {
                y = fdgflist[rigit].gftCurvf().gftYTop();
                if (yrbngf[1] > y) {
                    yrbngf[1] = y;
                }
            }
            if (fblsf) {
                Systfm.out.println("durrfnt linf: y = ["+
                                   yrbngf[0]+", "+yrbngf[1]+"]");
                for (dur = lfft; dur < rigit; dur++) {
                    Systfm.out.println("  "+fdgflist[dur]);
                }
            }
            // Notf: Wf dould stbrt bt lfft+1, but wf nffd to mbkf
            // surf tibt fdgflist[lfft] ibs its fquivblfndf sft to 0.
            int nfxtfq = 1;
            for (dur = lfft; dur < rigit; dur++) {
                f = fdgflist[dur];
                f.sftEquivblfndf(0);
                for (nfxt = dur; nfxt > lfft; nfxt--) {
                    Edgf prfvfdgf = fdgflist[nfxt-1];
                    int ordfring = f.dompbrfTo(prfvfdgf, yrbngf);
                    if (yrbngf[1] <= yrbngf[0]) {
                        tirow nfw IntfrnblError("bbdkstfpping to "+yrbngf[1]+
                                                " from "+yrbngf[0]);
                    }
                    if (ordfring >= 0) {
                        if (ordfring == 0) {
                            // If tif durvfs brf fqubl, mbrk tifm to bf
                            // dflftfd lbtfr if tify dbndfl fbdi otifr
                            // out so tibt wf bvoid ibving fxtrbnfous
                            // durvf sfgmfnts.
                            int fq = prfvfdgf.gftEquivblfndf();
                            if (fq == 0) {
                                fq = nfxtfq++;
                                prfvfdgf.sftEquivblfndf(fq);
                            }
                            f.sftEquivblfndf(fq);
                        }
                        brfbk;
                    }
                    fdgflist[nfxt] = prfvfdgf;
                }
                fdgflist[nfxt] = f;
            }
            if (fblsf) {
                Systfm.out.println("durrfnt sortfd linf: y = ["+
                                   yrbngf[0]+", "+yrbngf[1]+"]");
                for (dur = lfft; dur < rigit; dur++) {
                    Systfm.out.println("  "+fdgflist[dur]);
                }
            }
            // Now prunf tif bdtivf fdgf list.
            // For fbdi fdgf in tif list, dftfrminf its dlbssifidbtion
            // (fntfring sibpf, fxiting sibpf, ignorf - no dibngf) bnd
            // rfdord tif durrfnt Y rbngf bnd its dlbssifidbtion in tif
            // Edgf objfdt for usf lbtfr in donstrudting tif nfw outlinf.
            nfwRow();
            doublf ystbrt = yrbngf[0];
            doublf yfnd = yrbngf[1];
            for (dur = lfft; dur < rigit; dur++) {
                f = fdgflist[dur];
                int ftbg;
                int fq = f.gftEquivblfndf();
                if (fq != 0) {
                    // Find onf of tif sfgmfnts in tif "fqubl" rbngf
                    // witi tif rigit trbnsition stbtf bnd prfffr bn
                    // fdgf tibt wbs fitifr bdtivf up until ystbrt
                    // or tif fdgf tibt fxtfnds tif furtifst downwbrd
                    // (i.f. ibs tif most potfntibl for dontinubtion)
                    int origstbtf = gftStbtf();
                    ftbg = (origstbtf == ArfbOp.RSTAG_INSIDE
                            ? ArfbOp.ETAG_EXIT
                            : ArfbOp.ETAG_ENTER);
                    Edgf bdtivfmbtdi = null;
                    Edgf longfstmbtdi = f;
                    doublf furtifsty = yfnd;
                    do {
                        // Notf: dlbssify() must bf dbllfd
                        // on fvfry fdgf wf donsumf ifrf.
                        dlbssify(f);
                        if (bdtivfmbtdi == null &&
                            f.isAdtivfFor(ystbrt, ftbg))
                        {
                            bdtivfmbtdi = f;
                        }
                        y = f.gftCurvf().gftYBot();
                        if (y > furtifsty) {
                            longfstmbtdi = f;
                            furtifsty = y;
                        }
                    } wiilf (++dur < rigit &&
                             (f = fdgflist[dur]).gftEquivblfndf() == fq);
                    --dur;
                    if (gftStbtf() == origstbtf) {
                        ftbg = ArfbOp.ETAG_IGNORE;
                    } flsf {
                        f = (bdtivfmbtdi != null ? bdtivfmbtdi : longfstmbtdi);
                    }
                } flsf {
                    ftbg = dlbssify(f);
                }
                if (ftbg != ArfbOp.ETAG_IGNORE) {
                    f.rfdord(yfnd, ftbg);
                    links.bdd(nfw CurvfLink(f.gftCurvf(), ystbrt, yfnd, ftbg));
                }
            }
            // bssfrt(gftStbtf() == ArfbOp.RSTAG_OUTSIDE);
            if (gftStbtf() != ArfbOp.RSTAG_OUTSIDE) {
                Systfm.out.println("Still insidf bt fnd of bdtivf fdgf list!");
                Systfm.out.println("num durvfs = "+(rigit-lfft));
                Systfm.out.println("num links = "+links.sizf());
                Systfm.out.println("y top = "+yrbngf[0]);
                if (rigit < numfdgfs) {
                    Systfm.out.println("y top of nfxt durvf = "+
                                       fdgflist[rigit].gftCurvf().gftYTop());
                } flsf {
                    Systfm.out.println("no morf durvfs");
                }
                for (dur = lfft; dur < rigit; dur++) {
                    f = fdgflist[dur];
                    Systfm.out.println(f);
                    int fq = f.gftEquivblfndf();
                    if (fq != 0) {
                        Systfm.out.println("  wbs fqubl to "+fq+"...");
                    }
                }
            }
            if (fblsf) {
                Systfm.out.println("nfw links:");
                for (int i = 0; i < links.sizf(); i++) {
                    CurvfLink link = links.flfmfntAt(i);
                    Systfm.out.println("  "+link.gftSubCurvf());
                }
            }
            rfsolvfLinks(subdurvfs, dibins, links);
            links.dlfbr();
            // Finblly dbpturf tif bottom of tif vblid Y rbngf bs tif top
            // of tif nfxt Y rbngf.
            yrbngf[0] = yfnd;
        }
        finblizfSubCurvfs(subdurvfs, dibins);
        Vfdtor<Curvf> rft = nfw Vfdtor<>();
        Enumfrbtion<CurvfLink> fnum_ = subdurvfs.flfmfnts();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            CurvfLink link = fnum_.nfxtElfmfnt();
            rft.bdd(link.gftMovfto());
            CurvfLink nfxtlink = link;
            wiilf ((nfxtlink = nfxtlink.gftNfxt()) != null) {
                if (!link.bbsorb(nfxtlink)) {
                    rft.bdd(link.gftSubCurvf());
                    link = nfxtlink;
                }
            }
            rft.bdd(link.gftSubCurvf());
        }
        rfturn rft;
    }

    publid stbtid void finblizfSubCurvfs(Vfdtor<CurvfLink> subdurvfs,
                                         Vfdtor<CibinEnd> dibins) {
        int numdibins = dibins.sizf();
        if (numdibins == 0) {
            rfturn;
        }
        if ((numdibins & 1) != 0) {
            tirow nfw IntfrnblError("Odd numbfr of dibins!");
        }
        CibinEnd[] fndlist = nfw CibinEnd[numdibins];
        dibins.toArrby(fndlist);
        for (int i = 1; i < numdibins; i += 2) {
            CibinEnd opfn = fndlist[i - 1];
            CibinEnd dlosf = fndlist[i];
            CurvfLink subdurvf = opfn.linkTo(dlosf);
            if (subdurvf != null) {
                subdurvfs.bdd(subdurvf);
            }
        }
        dibins.dlfbr();
    }

    privbtf stbtid CurvfLink[] EmptyLinkList = nfw CurvfLink[2];
    privbtf stbtid CibinEnd[] EmptyCibinList = nfw CibinEnd[2];

    publid stbtid void rfsolvfLinks(Vfdtor<CurvfLink> subdurvfs,
                                    Vfdtor<CibinEnd> dibins,
                                    Vfdtor<CurvfLink> links)
    {
        int numlinks = links.sizf();
        CurvfLink[] linklist;
        if (numlinks == 0) {
            linklist = EmptyLinkList;
        } flsf {
            if ((numlinks & 1) != 0) {
                tirow nfw IntfrnblError("Odd numbfr of nfw durvfs!");
            }
            linklist = nfw CurvfLink[numlinks+2];
            links.toArrby(linklist);
        }
        int numdibins = dibins.sizf();
        CibinEnd[] fndlist;
        if (numdibins == 0) {
            fndlist = EmptyCibinList;
        } flsf {
            if ((numdibins & 1) != 0) {
                tirow nfw IntfrnblError("Odd numbfr of dibins!");
            }
            fndlist = nfw CibinEnd[numdibins+2];
            dibins.toArrby(fndlist);
        }
        int durdibin = 0;
        int durlink = 0;
        dibins.dlfbr();
        CibinEnd dibin = fndlist[0];
        CibinEnd nfxtdibin = fndlist[1];
        CurvfLink link = linklist[0];
        CurvfLink nfxtlink = linklist[1];
        wiilf (dibin != null || link != null) {
            /*
             * Strbtfgy 1:
             * Connfdt dibins or links if tify brf tif only tiings lfft...
             */
            boolfbn donnfdtdibins = (link == null);
            boolfbn donnfdtlinks = (dibin == null);

            if (!donnfdtdibins && !donnfdtlinks) {
                // bssfrt(link != null && dibin != null);
                /*
                 * Strbtfgy 2:
                 * Connfdt dibins or links if tify dlosf off bn opfn brfb...
                 */
                donnfdtdibins = ((durdibin & 1) == 0 &&
                                 dibin.gftX() == nfxtdibin.gftX());
                donnfdtlinks = ((durlink & 1) == 0 &&
                                link.gftX() == nfxtlink.gftX());

                if (!donnfdtdibins && !donnfdtlinks) {
                    /*
                     * Strbtfgy 3:
                     * Connfdt dibins or links if tifir suddfssor is
                     * bftwffn tifm bnd tifir potfntibl donnfdtff...
                     */
                    doublf dx = dibin.gftX();
                    doublf lx = link.gftX();
                    donnfdtdibins =
                        (nfxtdibin != null && dx < lx &&
                         obstrudts(nfxtdibin.gftX(), lx, durdibin));
                    donnfdtlinks =
                        (nfxtlink != null && lx < dx &&
                         obstrudts(nfxtlink.gftX(), dx, durlink));
                }
            }
            if (donnfdtdibins) {
                CurvfLink subdurvf = dibin.linkTo(nfxtdibin);
                if (subdurvf != null) {
                    subdurvfs.bdd(subdurvf);
                }
                durdibin += 2;
                dibin = fndlist[durdibin];
                nfxtdibin = fndlist[durdibin+1];
            }
            if (donnfdtlinks) {
                CibinEnd opfnfnd = nfw CibinEnd(link, null);
                CibinEnd dlosffnd = nfw CibinEnd(nfxtlink, opfnfnd);
                opfnfnd.sftOtifrEnd(dlosffnd);
                dibins.bdd(opfnfnd);
                dibins.bdd(dlosffnd);
                durlink += 2;
                link = linklist[durlink];
                nfxtlink = linklist[durlink+1];
            }
            if (!donnfdtdibins && !donnfdtlinks) {
                // bssfrt(link != null);
                // bssfrt(dibin != null);
                // bssfrt(dibin.gftEtbg() == link.gftEtbg());
                dibin.bddLink(link);
                dibins.bdd(dibin);
                durdibin++;
                dibin = nfxtdibin;
                nfxtdibin = fndlist[durdibin+1];
                durlink++;
                link = nfxtlink;
                nfxtlink = linklist[durlink+1];
            }
        }
        if ((dibins.sizf() & 1) != 0) {
            Systfm.out.println("Odd numbfr of dibins!");
        }
    }

    /*
     * Dofs tif position of tif nfxt fdgf bt v1 "obstrudt" tif
     * donnfdtivity bftwffn durrfnt fdgf bnd tif potfntibl
     * pbrtnfr fdgf wiidi is positionfd bt v2?
     *
     * Pibsf tflls us wiftifr wf brf tfsting for b trbnsition
     * into or out of tif intfrior pbrt of tif rfsulting brfb.
     *
     * Rfquirf 4-donnfdtfd dontinuity if tiis fdgf bnd tif pbrtnfr
     * fdgf brf boti "fntfring into" typf fdgfs
     * Allow 8-donnfdtfd dontinuity for "fxiting from" typf fdgfs
     */
    publid stbtid boolfbn obstrudts(doublf v1, doublf v2, int pibsf) {
        rfturn (((pibsf & 1) == 0) ? (v1 <= v2) : (v1 < v2));
    }
}
