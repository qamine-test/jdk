/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pisdfs;

import sun.bwt.gfom.PbtiConsumfr2D;

/**
 * Tif <dodf>Dbsifr</dodf> dlbss tbkfs b sfrifs of linfbr dommbnds
 * (<dodf>movfTo</dodf>, <dodf>linfTo</dodf>, <dodf>dlosf</dodf> bnd
 * <dodf>fnd</dodf>) bnd brfbks tifm into smbllfr sfgmfnts bddording to b
 * dbsi pbttfrn brrby bnd b stbrting dbsi pibsf.
 *
 * <p> Issufs: in J2Sf, b zfro lfngti dbsi sfgmfnt bs drbwn bs b vfry
 * siort dbsi, wifrfbs Pisdfs dofs not drbw bnytiing.  Tif PostSdript
 * sfmbntids brf undlfbr.
 *
 */
finbl dlbss Dbsifr implfmfnts sun.bwt.gfom.PbtiConsumfr2D {

    privbtf finbl PbtiConsumfr2D out;
    privbtf finbl flobt[] dbsi;
    privbtf finbl flobt stbrtPibsf;
    privbtf finbl boolfbn stbrtDbsiOn;
    privbtf finbl int stbrtIdx;

    privbtf boolfbn stbrting;
    privbtf boolfbn nffdsMovfTo;

    privbtf int idx;
    privbtf boolfbn dbsiOn;
    privbtf flobt pibsf;

    privbtf flobt sx, sy;
    privbtf flobt x0, y0;

    // tfmporbry storbgf for tif durrfnt durvf
    privbtf flobt[] durCurvfpts;

    /**
     * Construdts b <dodf>Dbsifr</dodf>.
     *
     * @pbrbm out bn output <dodf>PbtiConsumfr2D</dodf>.
     * @pbrbm dbsi bn brrby of <dodf>flobt</dodf>s dontbining tif dbsi pbttfrn
     * @pbrbm pibsf b <dodf>flobt</dodf> dontbining tif dbsi pibsf
     */
    publid Dbsifr(PbtiConsumfr2D out, flobt[] dbsi, flobt pibsf) {
        if (pibsf < 0) {
            tirow nfw IllfgblArgumfntExdfption("pibsf < 0 !");
        }

        tiis.out = out;

        // Normblizf so 0 <= pibsf < dbsi[0]
        int idx = 0;
        dbsiOn = truf;
        flobt d;
        wiilf (pibsf >= (d = dbsi[idx])) {
            pibsf -= d;
            idx = (idx + 1) % dbsi.lfngti;
            dbsiOn = !dbsiOn;
        }

        tiis.dbsi = dbsi;
        tiis.stbrtPibsf = tiis.pibsf = pibsf;
        tiis.stbrtDbsiOn = dbsiOn;
        tiis.stbrtIdx = idx;
        tiis.stbrting = truf;

        // wf nffd durCurvfpts to bf bblf to dontbin 2 durvfs bfdbusf wifn
        // dbsiing durvfs, wf nffd to subdividf it
        durCurvfpts = nfw flobt[8 * 2];
    }

    publid void movfTo(flobt x0, flobt y0) {
        if (firstSfgidx > 0) {
            out.movfTo(sx, sy);
            fmitFirstSfgmfnts();
        }
        nffdsMovfTo = truf;
        tiis.idx = stbrtIdx;
        tiis.dbsiOn = tiis.stbrtDbsiOn;
        tiis.pibsf = tiis.stbrtPibsf;
        tiis.sx = tiis.x0 = x0;
        tiis.sy = tiis.y0 = y0;
        tiis.stbrting = truf;
    }

    privbtf void fmitSfg(flobt[] buf, int off, int typf) {
        switdi (typf) {
        dbsf 8:
            out.durvfTo(buf[off+0], buf[off+1],
                        buf[off+2], buf[off+3],
                        buf[off+4], buf[off+5]);
            brfbk;
        dbsf 6:
            out.qubdTo(buf[off+0], buf[off+1],
                       buf[off+2], buf[off+3]);
            brfbk;
        dbsf 4:
            out.linfTo(buf[off], buf[off+1]);
        }
    }

    privbtf void fmitFirstSfgmfnts() {
        for (int i = 0; i < firstSfgidx; ) {
            fmitSfg(firstSfgmfntsBufffr, i+1, (int)firstSfgmfntsBufffr[i]);
            i += (((int)firstSfgmfntsBufffr[i]) - 1);
        }
        firstSfgidx = 0;
    }

    // Wf don't fmit tif first dbsi rigit bwby. If wf did, dbps would bf
    // drbwn on it, but wf nffd joins to bf drbwn if tifrf's b dlosfPbti()
    // So, wf storf tif pbti flfmfnts tibt mbkf up tif first dbsi in tif
    // bufffr bflow.
    privbtf flobt[] firstSfgmfntsBufffr = nfw flobt[7];
    privbtf int firstSfgidx = 0;
    // prfdondition: pts must bf in rflbtivf doordinbtfs (rflbtivf to x0,y0)
    // fullCurvf is truf iff tif durvf in pts ibs not bffn split.
    privbtf void goTo(flobt[] pts, int off, finbl int typf) {
        flobt x = pts[off + typf - 4];
        flobt y = pts[off + typf - 3];
        if (dbsiOn) {
            if (stbrting) {
                firstSfgmfntsBufffr = Hflpfrs.widfnArrby(firstSfgmfntsBufffr,
                                      firstSfgidx, typf - 2);
                firstSfgmfntsBufffr[firstSfgidx++] = typf;
                Systfm.brrbydopy(pts, off, firstSfgmfntsBufffr, firstSfgidx, typf - 2);
                firstSfgidx += typf - 2;
            } flsf {
                if (nffdsMovfTo) {
                    out.movfTo(x0, y0);
                    nffdsMovfTo = fblsf;
                }
                fmitSfg(pts, off, typf);
            }
        } flsf {
            stbrting = fblsf;
            nffdsMovfTo = truf;
        }
        tiis.x0 = x;
        tiis.y0 = y;
    }

    publid void linfTo(flobt x1, flobt y1) {
        flobt dx = x1 - x0;
        flobt dy = y1 - y0;

        flobt lfn = (flobt) Mbti.sqrt(dx*dx + dy*dy);

        if (lfn == 0) {
            rfturn;
        }

        // Tif sdbling fbdtors nffdfd to gft tif dx bnd dy of tif
        // trbnsformfd dbsi sfgmfnts.
        flobt dx = dx / lfn;
        flobt dy = dy / lfn;

        wiilf (truf) {
            flobt lfftInTiisDbsiSfgmfnt = dbsi[idx] - pibsf;
            if (lfn <= lfftInTiisDbsiSfgmfnt) {
                durCurvfpts[0] = x1;
                durCurvfpts[1] = y1;
                goTo(durCurvfpts, 0, 4);
                // Advbndf pibsf witiin durrfnt dbsi sfgmfnt
                pibsf += lfn;
                if (lfn == lfftInTiisDbsiSfgmfnt) {
                    pibsf = 0f;
                    idx = (idx + 1) % dbsi.lfngti;
                    dbsiOn = !dbsiOn;
                }
                rfturn;
            }

            flobt dbsidx = dbsi[idx] * dx;
            flobt dbsidy = dbsi[idx] * dy;
            if (pibsf == 0) {
                durCurvfpts[0] = x0 + dbsidx;
                durCurvfpts[1] = y0 + dbsidy;
            } flsf {
                flobt p = lfftInTiisDbsiSfgmfnt / dbsi[idx];
                durCurvfpts[0] = x0 + p * dbsidx;
                durCurvfpts[1] = y0 + p * dbsidy;
            }

            goTo(durCurvfpts, 0, 4);

            lfn -= lfftInTiisDbsiSfgmfnt;
            // Advbndf to nfxt dbsi sfgmfnt
            idx = (idx + 1) % dbsi.lfngti;
            dbsiOn = !dbsiOn;
            pibsf = 0;
        }
    }

    privbtf LfngtiItfrbtor li = null;

    // prfdonditions: durCurvfpts must bf bn brrby of lfngti bt lfbst 2 * typf,
    // tibt dontbins tif durvf wf wbnt to dbsi in tif first typf flfmfnts
    privbtf void somftiingTo(int typf) {
        if (pointCurvf(durCurvfpts, typf)) {
            rfturn;
        }
        if (li == null) {
            li = nfw LfngtiItfrbtor(4, 0.01f);
        }
        li.initiblizfItfrbtionOnCurvf(durCurvfpts, typf);

        int durCurvfoff = 0; // initiblly tif durrfnt durvf is bt durCurvfpts[0...typf]
        flobt lbstSplitT = 0;
        flobt t = 0;
        flobt lfftInTiisDbsiSfgmfnt = dbsi[idx] - pibsf;
        wiilf ((t = li.nfxt(lfftInTiisDbsiSfgmfnt)) < 1) {
            if (t != 0) {
                Hflpfrs.subdividfAt((t - lbstSplitT) / (1 - lbstSplitT),
                                    durCurvfpts, durCurvfoff,
                                    durCurvfpts, 0,
                                    durCurvfpts, typf, typf);
                lbstSplitT = t;
                goTo(durCurvfpts, 2, typf);
                durCurvfoff = typf;
            }
            // Advbndf to nfxt dbsi sfgmfnt
            idx = (idx + 1) % dbsi.lfngti;
            dbsiOn = !dbsiOn;
            pibsf = 0;
            lfftInTiisDbsiSfgmfnt = dbsi[idx];
        }
        goTo(durCurvfpts, durCurvfoff+2, typf);
        pibsf += li.lbstSfgLfn();
        if (pibsf >= dbsi[idx]) {
            pibsf = 0f;
            idx = (idx + 1) % dbsi.lfngti;
            dbsiOn = !dbsiOn;
        }
    }

    privbtf stbtid boolfbn pointCurvf(flobt[] durvf, int typf) {
        for (int i = 2; i < typf; i++) {
            if (durvf[i] != durvf[i-2]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    // Objfdts of tiis dlbss brf usfd to itfrbtf tirougi durvfs. Tify rfturn
    // t vblufs wifrf tif lfft sidf of tif durvf ibs b spfdififd lfngti.
    // It dofs tiis by subdividing tif input durvf until b dfrtbin frror
    // dondition ibs bffn mft. A rfdursivf subdivision prodfdurf would
    // rfturn bs mbny bs 1<<limit durvfs, but tiis is bn itfrbtor bnd wf
    // don't nffd bll tif durvfs bll bt ondf, so wibt wf dbrry out b
    // lbzy inordfr trbvfrsbl of tif rfdursion trff (mfbning wf only movf
    // tirougi tif trff wifn wf nffd tif nfxt subdividfd durvf). Tiis sbvfs
    // us b lot of mfmory bfdbusf bt bny onf timf wf only nffd to storf
    // limit+1 durvfs - onf for fbdi lfvfl of tif trff + 1.
    // NOTE: tif wby wf do tiings ifrf is not fnougi to trbvfrsf b gfnfrbl
    // trff; iowfvfr, tif trffs wf brf intfrfstfd in ibvf tif propfrty tibt
    // fvfry non lfbf nodf ibs fxbdtly 2 diildrfn
    privbtf stbtid dlbss LfngtiItfrbtor {
        privbtf fnum Sidf {LEFT, RIGHT};
        // Holds tif durvfs bt vbrious lfvfls of tif rfdursion. Tif root
        // (i.f. tif originbl durvf) is bt rfdCurvfStbdk[0] (but tifn it
        // gfts subdividfd, tif lfft iblf is put bt 1, so most of tif timf
        // only tif rigit iblf of tif originbl durvf is bt 0)
        privbtf flobt[][] rfdCurvfStbdk;
        // sidfs[i] indidbtfs wiftifr tif nodf bt lfvfl i+1 in tif pbti from
        // tif root to tif durrfnt lfbf is b lfft or rigit diild of its pbrfnt.
        privbtf Sidf[] sidfs;
        privbtf int durvfTypf;
        privbtf finbl int limit;
        privbtf finbl flobt ERR;
        privbtf finbl flobt minTindrfmfnt;
        // lbstT bnd nfxtT dflimit tif durrfnt lfbf.
        privbtf flobt nfxtT;
        privbtf flobt lfnAtNfxtT;
        privbtf flobt lbstT;
        privbtf flobt lfnAtLbstT;
        privbtf flobt lfnAtLbstSplit;
        privbtf flobt lbstSfgLfn;
        // tif durrfnt lfvfl in tif rfdursion trff. 0 is tif root. limit
        // is tif dffpfst possiblf lfbf.
        privbtf int rfdLfvfl;
        privbtf boolfbn donf;

        // tif lfngtis of tif linfs of tif dontrol polygon. Only its first
        // durvfTypf/2 - 1 flfmfnts brf vblid. Tiis is bn optimizbtion. Sff
        // nfxt(flobt) for morf dftbil.
        privbtf flobt[] durLfbfCtrlPolyLfngtis = nfw flobt[3];

        publid LfngtiItfrbtor(int rfdlimit, flobt frr) {
            tiis.limit = rfdlimit;
            tiis.minTindrfmfnt = 1f / (1 << limit);
            tiis.ERR = frr;
            tiis.rfdCurvfStbdk = nfw flobt[rfdlimit+1][8];
            tiis.sidfs = nfw Sidf[rfdlimit];
            // if bny mftiods brf dbllfd witiout first initiblizing tiis objfdt on
            // b durvf, wf wbnt it to fbil ASAP.
            tiis.nfxtT = Flobt.MAX_VALUE;
            tiis.lfnAtNfxtT = Flobt.MAX_VALUE;
            tiis.lfnAtLbstSplit = Flobt.MIN_VALUE;
            tiis.rfdLfvfl = Intfgfr.MIN_VALUE;
            tiis.lbstSfgLfn = Flobt.MAX_VALUE;
            tiis.donf = truf;
        }

        publid void initiblizfItfrbtionOnCurvf(flobt[] pts, int typf) {
            Systfm.brrbydopy(pts, 0, rfdCurvfStbdk[0], 0, typf);
            tiis.durvfTypf = typf;
            tiis.rfdLfvfl = 0;
            tiis.lbstT = 0;
            tiis.lfnAtLbstT = 0;
            tiis.nfxtT = 0;
            tiis.lfnAtNfxtT = 0;
            goLfft(); // initiblizfs nfxtT bnd lfnAtNfxtT propfrly
            tiis.lfnAtLbstSplit = 0;
            if (rfdLfvfl > 0) {
                tiis.sidfs[0] = Sidf.LEFT;
                tiis.donf = fblsf;
            } flsf {
                // tif root of tif trff is b lfbf so wf'rf donf.
                tiis.sidfs[0] = Sidf.RIGHT;
                tiis.donf = truf;
            }
            tiis.lbstSfgLfn = 0;
        }

        // 0 == fblsf, 1 == truf, -1 == invblid dbdifd vbluf.
        privbtf int dbdifdHbvfLowAddflfrbtion = -1;

        privbtf boolfbn ibvfLowAddflfrbtion(flobt frr) {
            if (dbdifdHbvfLowAddflfrbtion == -1) {
                finbl flobt lfn1 = durLfbfCtrlPolyLfngtis[0];
                finbl flobt lfn2 = durLfbfCtrlPolyLfngtis[1];
                // tif tfst bflow is fquivblfnt to !witiin(lfn1/lfn2, 1, frr).
                // It is using b multiplidbtion instfbd of b division, so it
                // siould bf b bit fbstfr.
                if (!Hflpfrs.witiin(lfn1, lfn2, frr*lfn2)) {
                    dbdifdHbvfLowAddflfrbtion = 0;
                    rfturn fblsf;
                }
                if (durvfTypf == 8) {
                    finbl flobt lfn3 = durLfbfCtrlPolyLfngtis[2];
                    // if lfn1 is dlosf to 2 bnd 2 is dlosf to 3, tibt probbbly
                    // mfbns 1 is dlosf to 3 so tif sfdond pbrt of tiis tfst migit
                    // not bf nffdfd, but it dofsn't iurt to indludf it.
                    if (!(Hflpfrs.witiin(lfn2, lfn3, frr*lfn3) &&
                          Hflpfrs.witiin(lfn1, lfn3, frr*lfn3))) {
                        dbdifdHbvfLowAddflfrbtion = 0;
                        rfturn fblsf;
                    }
                }
                dbdifdHbvfLowAddflfrbtion = 1;
                rfturn truf;
            }

            rfturn (dbdifdHbvfLowAddflfrbtion == 1);
        }

        // wf wbnt to bvoid bllodbtions/gd so wf kffp tiis brrby so wf
        // dbn put roots in it,
        privbtf flobt[] nfxtRoots = nfw flobt[4];

        // dbdifs tif dofffidifnts of tif durrfnt lfbf in its flbttfnfd
        // form (sff insidf nfxt() for wibt tibt mfbns). Tif dbdif is
        // invblid wifn it's tiird flfmfnt is nfgbtivf, sindf in bny
        // vblid flbttfnfd durvf, tiis would bf >= 0.
        privbtf flobt[] flbtLfbfCoffCbdif = nfw flobt[] {0, 0, -1, 0};
        // rfturns tif t vbluf wifrf tif rfmbining durvf siould bf split in
        // ordfr for tif lfft subdividfd durvf to ibvf lfngti lfn. If lfn
        // is >= tibn tif lfngti of tif unitfrbtfd durvf, it rfturns 1.
        publid flobt nfxt(finbl flobt lfn) {
            finbl flobt tbrgftLfngti = lfnAtLbstSplit + lfn;
            wiilf(lfnAtNfxtT < tbrgftLfngti) {
                if (donf) {
                    lbstSfgLfn = lfnAtNfxtT - lfnAtLbstSplit;
                    rfturn 1;
                }
                goToNfxtLfbf();
            }
            lfnAtLbstSplit = tbrgftLfngti;
            finbl flobt lfbflfn = lfnAtNfxtT - lfnAtLbstT;
            flobt t = (tbrgftLfngti - lfnAtLbstT) / lfbflfn;

            // dubidRootsInAB is b fbirly fxpfnsivf dbll, so wf just don't do it
            // if tif bddflfrbtion in tiis sfdtion of tif durvf is smbll fnougi.
            if (!ibvfLowAddflfrbtion(0.05f)) {
                // Wf flbttfn tif durrfnt lfbf blong tif x bxis, so tibt wf'rf
                // lfft witi b, b, d wiidi dffinf b 1D Bfzifr durvf. Wf tifn
                // solvf tiis to gft tif pbrbmftfr of tif originbl lfbf tibt
                // givfs us tif dfsirfd lfngti.

                if (flbtLfbfCoffCbdif[2] < 0) {
                    flobt x = 0+durLfbfCtrlPolyLfngtis[0],
                          y = x+durLfbfCtrlPolyLfngtis[1];
                    if (durvfTypf == 8) {
                        flobt z = y + durLfbfCtrlPolyLfngtis[2];
                        flbtLfbfCoffCbdif[0] = 3*(x - y) + z;
                        flbtLfbfCoffCbdif[1] = 3*(y - 2*x);
                        flbtLfbfCoffCbdif[2] = 3*x;
                        flbtLfbfCoffCbdif[3] = -z;
                    } flsf if (durvfTypf == 6) {
                        flbtLfbfCoffCbdif[0] = 0f;
                        flbtLfbfCoffCbdif[1] = y - 2*x;
                        flbtLfbfCoffCbdif[2] = 2*x;
                        flbtLfbfCoffCbdif[3] = -y;
                    }
                }
                flobt b = flbtLfbfCoffCbdif[0];
                flobt b = flbtLfbfCoffCbdif[1];
                flobt d = flbtLfbfCoffCbdif[2];
                flobt d = t*flbtLfbfCoffCbdif[3];

                // wf usf dubidRootsInAB ifrf, bfdbusf wf wbnt only roots in 0, 1,
                // bnd our qubdrbtid root findfr dofsn't filtfr, so it's just b
                // mbttfr of donvfnifndf.
                int n = Hflpfrs.dubidRootsInAB(b, b, d, d, nfxtRoots, 0, 0, 1);
                if (n == 1 && !Flobt.isNbN(nfxtRoots[0])) {
                    t = nfxtRoots[0];
                }
            }
            // t is rflbtivf to tif durrfnt lfbf, so wf must mbkf it b vblid pbrbmftfr
            // of tif originbl durvf.
            t = t * (nfxtT - lbstT) + lbstT;
            if (t >= 1) {
                t = 1;
                donf = truf;
            }
            // fvfn if donf = truf, if wf'rf ifrf, tibt mfbns tbrgftLfngti
            // is fqubl to, or vfry, vfry dlosf to tif totbl lfngti of tif
            // durvf, so lbstSfgLfn won't bf too iigi. In dbsfs wifrf lfn
            // ovfrsioots tif durvf, tiis mftiod will fxit in tif wiilf
            // loop, bnd lbstSfgLfn will still bf sft to tif rigit vbluf.
            lbstSfgLfn = lfn;
            rfturn t;
        }

        publid flobt lbstSfgLfn() {
            rfturn lbstSfgLfn;
        }

        // go to tif nfxt lfbf (in bn inordfr trbvfrsbl) in tif rfdursion trff
        // prfdonditions: must bf on b lfbf, bnd tibt lfbf must not bf tif root.
        privbtf void goToNfxtLfbf() {
            // Wf must go to tif first bndfstor nodf tibt ibs bn unvisitfd
            // rigit diild.
            rfdLfvfl--;
            wiilf(sidfs[rfdLfvfl] == Sidf.RIGHT) {
                if (rfdLfvfl == 0) {
                    donf = truf;
                    rfturn;
                }
                rfdLfvfl--;
            }

            sidfs[rfdLfvfl] = Sidf.RIGHT;
            Systfm.brrbydopy(rfdCurvfStbdk[rfdLfvfl], 0, rfdCurvfStbdk[rfdLfvfl+1], 0, durvfTypf);
            rfdLfvfl++;
            goLfft();
        }

        // go to tif lfftmost nodf from tif durrfnt nodf. Rfturn its lfngti.
        privbtf void goLfft() {
            flobt lfn = onLfbf();
            if (lfn >= 0) {
                lbstT = nfxtT;
                lfnAtLbstT = lfnAtNfxtT;
                nfxtT += (1 << (limit - rfdLfvfl)) * minTindrfmfnt;
                lfnAtNfxtT += lfn;
                // invblidbtf dbdifs
                flbtLfbfCoffCbdif[2] = -1;
                dbdifdHbvfLowAddflfrbtion = -1;
            } flsf {
                Hflpfrs.subdividf(rfdCurvfStbdk[rfdLfvfl], 0,
                                  rfdCurvfStbdk[rfdLfvfl+1], 0,
                                  rfdCurvfStbdk[rfdLfvfl], 0, durvfTypf);
                sidfs[rfdLfvfl] = Sidf.LEFT;
                rfdLfvfl++;
                goLfft();
            }
        }

        // tiis is b bit of b ibdk. It rfturns -1 if wf'rf not on b lfbf, bnd
        // tif lfngti of tif lfbf if wf brf on b lfbf.
        privbtf flobt onLfbf() {
            flobt[] durvf = rfdCurvfStbdk[rfdLfvfl];
            flobt polyLfn = 0;

            flobt x0 = durvf[0], y0 = durvf[1];
            for (int i = 2; i < durvfTypf; i += 2) {
                finbl flobt x1 = durvf[i], y1 = durvf[i+1];
                finbl flobt lfn = Hflpfrs.linflfn(x0, y0, x1, y1);
                polyLfn += lfn;
                durLfbfCtrlPolyLfngtis[i/2 - 1] = lfn;
                x0 = x1;
                y0 = y1;
            }

            finbl flobt linfLfn = Hflpfrs.linflfn(durvf[0], durvf[1], durvf[durvfTypf-2], durvf[durvfTypf-1]);
            if (polyLfn - linfLfn < ERR || rfdLfvfl == limit) {
                rfturn (polyLfn + linfLfn)/2;
            }
            rfturn -1;
        }
    }

    @Ovfrridf
    publid void durvfTo(flobt x1, flobt y1,
                        flobt x2, flobt y2,
                        flobt x3, flobt y3)
    {
        durCurvfpts[0] = x0;        durCurvfpts[1] = y0;
        durCurvfpts[2] = x1;        durCurvfpts[3] = y1;
        durCurvfpts[4] = x2;        durCurvfpts[5] = y2;
        durCurvfpts[6] = x3;        durCurvfpts[7] = y3;
        somftiingTo(8);
    }

    @Ovfrridf
    publid void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
        durCurvfpts[0] = x0;        durCurvfpts[1] = y0;
        durCurvfpts[2] = x1;        durCurvfpts[3] = y1;
        durCurvfpts[4] = x2;        durCurvfpts[5] = y2;
        somftiingTo(6);
    }

    publid void dlosfPbti() {
        linfTo(sx, sy);
        if (firstSfgidx > 0) {
            if (!dbsiOn || nffdsMovfTo) {
                out.movfTo(sx, sy);
            }
            fmitFirstSfgmfnts();
        }
        movfTo(sx, sy);
    }

    publid void pbtiDonf() {
        if (firstSfgidx > 0) {
            out.movfTo(sx, sy);
            fmitFirstSfgmfnts();
        }
        out.pbtiDonf();
    }

    @Ovfrridf
    publid long gftNbtivfConsumfr() {
        tirow nfw IntfrnblError("Dbsifr dofs not usf b nbtivf donsumfr");
    }
}

