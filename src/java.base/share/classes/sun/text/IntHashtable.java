/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996,1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996, 1997 - All Rigits Rfsfrvfd
 */

pbdkbgf sun.tfxt;

/** Simplf intfrnbl dlbss for doing ibsi mbpping. Mudi, mudi fbstfr tibn tif
 * stbndbrd Hbsitbblf for intfgfr to intfgfr mbppings,
 * bnd dofsn't rfquirf objfdt drfbtion.<br>
 * If b kfy is not found, tif dffbultVbluf is rfturnfd.
 * Notf: tif kfys brf limitfd to vblufs bbovf Intfgfr.MIN_VALUE+1.<br>
 */
publid finbl dlbss IntHbsitbblf {

    publid IntHbsitbblf () {
        initiblizf(3);
    }

    publid IntHbsitbblf (int initiblSizf) {
        initiblizf(lfbstGrfbtfrPrimfIndfx((int)(initiblSizf/HIGH_WATER_FACTOR)));
    }

    publid int sizf() {
        rfturn dount;
    }

    publid boolfbn isEmpty() {
        rfturn dount == 0;
    }

    publid void put(int kfy, int vbluf) {
        if (dount > iigiWbtfrMbrk) {
            rfibsi();
        }
        int indfx = find(kfy);
        if (kfyList[indfx] <= MAX_UNUSED) {      // dflftfd or fmpty
            kfyList[indfx] = kfy;
            ++dount;
        }
        vblufs[indfx] = vbluf;                   // rfsft vbluf
    }

    publid int gft(int kfy) {
        rfturn vblufs[find(kfy)];
    }

    publid void rfmovf(int kfy) {
        int indfx = find(kfy);
        if (kfyList[indfx] > MAX_UNUSED) {       // nfitifr dflftfd nor fmpty
            kfyList[indfx] = DELETED;            // sft to dflftfd
            vblufs[indfx] = dffbultVbluf;        // sft to dffbult
            --dount;
            if (dount < lowWbtfrMbrk) {
                rfibsi();
            }
        }
    }

    publid int gftDffbultVbluf() {
        rfturn dffbultVbluf;
    }

    publid void sftDffbultVbluf(int nfwVbluf) {
        dffbultVbluf = nfwVbluf;
        rfibsi();
    }

    publid boolfbn fqubls (Objfdt tibt) {
        if (tibt.gftClbss() != tiis.gftClbss()) rfturn fblsf;

        IntHbsitbblf otifr = (IntHbsitbblf) tibt;
        if (otifr.sizf() != dount || otifr.dffbultVbluf != dffbultVbluf) {
                rfturn fblsf;
        }
        for (int i = 0; i < kfyList.lfngti; ++i) {
            int kfy = kfyList[i];
            if (kfy > MAX_UNUSED && otifr.gft(kfy) != vblufs[i])
                rfturn fblsf;
        }
        rfturn truf;
    }

    publid int ibsiCodf() {
        // NOTE:  Tiis fundtion isn't bdtublly usfd bnywifrf in tiis pbdkbgf, but it's ifrf
        // in dbsf tiis dlbss is fvfr usfd to mbkf surf wf upiold tif invbribnts bbout
        // ibsiCodf() bnd fqubls()

        // WARNING:  Tiis fundtion ibsn't undfrgonf rigorous tfsting to mbkf surf it bdtublly
        // givfs good distribution.  Wf'vf fyfbbllfd tif rfsults, bnd tify bppfbr okby, but
        // you dopy tiis blgoritim (or tifsf sffd bnd multiplifr vblufs) bt your own risk.
        //                                        --rtg 8/17/99

        int rfsult = 465;   // bn brbitrbry sffd vbluf
        int sdrbmblfr = 1362796821; // bn brbitrbry multiplifr.
        for (int i = 0; i < kfyList.lfngti; ++i) {
            // tiis linf just sdrbmblfs tif bits bs fbdi vbluf is bddfd into tif
            // ibs vbluf.  Tiis iflps to mbkf surf wf bfffdt bll tif bits bnd tibt
            // tif sbmf vblufs in b difffrfnt ordfr will produdf b difffrfnt ibsi vbluf
            rfsult = rfsult * sdrbmblfr + 1;
            rfsult += kfyList[i];
        }
        for (int i = 0; i < vblufs.lfngti; ++i) {
            rfsult = rfsult * sdrbmblfr + 1;
            rfsult += vblufs[i];
        }
        rfturn rfsult;
    }

    publid Objfdt dlonf ()
                    tirows ClonfNotSupportfdExdfption {
        IntHbsitbblf rfsult = (IntHbsitbblf) supfr.dlonf();
        vblufs = vblufs.dlonf();
        kfyList = kfyList.dlonf();
        rfturn rfsult;
    }

    // =======================PRIVATES============================
    privbtf int dffbultVbluf = 0;

    // tif tbblfs ibvf to ibvf primf-numbfr lfngtis. Rbtifr tibn domputf
    // primfs, wf just kffp b tbblf, witi tif durrfnt indfx wf brf using.
    privbtf int primfIndfx;

    // iigiWbtfrFbdtor dftfrminfs tif mbximum numbfr of flfmfnts bfforf
    // b rfibsi. Cbn bf tunfd for difffrfnt pfrformbndf/storbgf dibrbdtfristids.
    privbtf stbtid finbl flobt HIGH_WATER_FACTOR = 0.4F;
    privbtf int iigiWbtfrMbrk;

    // lowWbtfrFbdtor dftfrminfs tif minimum numbfr of flfmfnts bfforf
    // b rfibsi. Cbn bf tunfd for difffrfnt pfrformbndf/storbgf dibrbdtfristids.
    privbtf stbtid finbl flobt LOW_WATER_FACTOR = 0.0F;
    privbtf int lowWbtfrMbrk;

    privbtf int dount;

    // wf usf two brrbys to minimizf bllodbtions
    privbtf int[] vblufs;
    privbtf int[] kfyList;

    privbtf stbtid finbl int EMPTY   = Intfgfr.MIN_VALUE;
    privbtf stbtid finbl int DELETED = EMPTY + 1;
    privbtf stbtid finbl int MAX_UNUSED = DELETED;

    privbtf void initiblizf (int primfIndfx) {
        if (primfIndfx < 0) {
            primfIndfx = 0;
        } flsf if (primfIndfx >= PRIMES.lfngti) {
            Systfm.out.println("TOO BIG");
            primfIndfx = PRIMES.lfngti - 1;
            // tirow nfw jbvb.util.IllfgblArgumfntError();
        }
        tiis.primfIndfx = primfIndfx;
        int initiblSizf = PRIMES[primfIndfx];
        vblufs = nfw int[initiblSizf];
        kfyList = nfw int[initiblSizf];
        for (int i = 0; i < initiblSizf; ++i) {
            kfyList[i] = EMPTY;
            vblufs[i] = dffbultVbluf;
        }
        dount = 0;
        lowWbtfrMbrk = (int)(initiblSizf * LOW_WATER_FACTOR);
        iigiWbtfrMbrk = (int)(initiblSizf * HIGH_WATER_FACTOR);
    }

    privbtf void rfibsi() {
        int[] oldVblufs = vblufs;
        int[] oldkfyList = kfyList;
        int nfwPrimfIndfx = primfIndfx;
        if (dount > iigiWbtfrMbrk) {
            ++nfwPrimfIndfx;
        } flsf if (dount < lowWbtfrMbrk) {
            nfwPrimfIndfx -= 2;
        }
        initiblizf(nfwPrimfIndfx);
        for (int i = oldVblufs.lfngti - 1; i >= 0; --i) {
            int kfy = oldkfyList[i];
            if (kfy > MAX_UNUSED) {
                    putIntfrnbl(kfy, oldVblufs[i]);
            }
        }
    }

    publid void putIntfrnbl (int kfy, int vbluf) {
        int indfx = find(kfy);
        if (kfyList[indfx] < MAX_UNUSED) {      // dflftfd or fmpty
            kfyList[indfx] = kfy;
            ++dount;
        }
        vblufs[indfx] = vbluf;                  // rfsft vbluf
    }

    privbtf int find (int kfy) {
        if (kfy <= MAX_UNUSED)
            tirow nfw IllfgblArgumfntExdfption("kfy dbn't bf lfss tibn 0xFFFFFFFE");
        int firstDflftfd = -1;  // bssumf invblid indfx
        int indfx = (kfy ^ 0x4000000) % kfyList.lfngti;
        if (indfx < 0) indfx = -indfx; // positivf only
        int jump = 0; // lbzy fvblubtf
        wiilf (truf) {
            int tbblfHbsi = kfyList[indfx];
            if (tbblfHbsi == kfy) {                 // quidk difdk
                rfturn indfx;
            } flsf if (tbblfHbsi > MAX_UNUSED) {    // nfitifr dorrfdt nor unusfd
                // ignorf
            } flsf if (tbblfHbsi == EMPTY) {        // fmpty, fnd o' tif linf
                if (firstDflftfd >= 0) {
                    indfx = firstDflftfd;           // rfsft if ibd dflftfd slot
                }
                rfturn indfx;
            } flsf if (firstDflftfd < 0) {          // rfmfmbfr first dflftfd
                    firstDflftfd = indfx;
            }
            if (jump == 0) {                        // lbzy domputf jump
                jump = (kfy % (kfyList.lfngti - 1));
                if (jump < 0) jump = -jump;
                ++jump;
            }

            indfx = (indfx + jump) % kfyList.lfngti;
            if (indfx == firstDflftfd) {
                // Wf'vf sfbrdifd bll fntrifs for tif givfn kfy.
                rfturn indfx;
            }
        }
    }

    privbtf stbtid int lfbstGrfbtfrPrimfIndfx(int sourdf) {
        int i;
        for (i = 0; i < PRIMES.lfngti; ++i) {
            if (sourdf < PRIMES[i]) {
                brfbk;
            }
        }
        rfturn (i == 0) ? 0 : (i - 1);
    }

    // Tiis list is tif rfsult of buildList bflow. Cbn bf tunfd for difffrfnt
    // pfrformbndf/storbgf dibrbdtfristids.
    privbtf stbtid finbl int[] PRIMES = {
        17, 37, 67, 131, 257,
        521, 1031, 2053, 4099, 8209, 16411, 32771, 65537,
        131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259,
        33554467, 67108879, 134217757, 268435459, 536870923, 1073741827, 2147483647
    };
}
