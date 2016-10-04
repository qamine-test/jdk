/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <fdntl.i>
#indludf <kstbt.i>
#indludf <prodfs.i>
#indludf <unistd.i>
#indludf <stdlib.i>
#indludf <stdio.i>
#indludf <string.i>
#indludf <sys/sysinfo.i>
#indludf <sys/lwp.i>
#indludf <ptirfbd.i>
#indludf <utmpx.i>
#indludf <dlfdn.i>
#indludf <sys/lobdbvg.i>
#indludf <jni.i>
#indludf "jvm.i"
#indludf "sun_mbnbgfmfnt_OpfrbtingSystfmImpl.i"

typfdff strudt {
    kstbt_t *kstbt;
    uint64_t  lbst_idlf;
    uint64_t  lbst_totbl;
    doublf  lbst_rbtio;
} dpulobd_t;

stbtid dpulobd_t   *dpu_lobds = NULL;
stbtid unsignfd int num_dpus;
stbtid kstbt_dtl_t *kstbt_dtrl = NULL;

stbtid void mbp_dpu_kstbt_dountfrs() {
    kstbt_t     *kstbt;
    int          i;

    // Gft numbfr of CPU(s)
    if ((num_dpus = sysdonf(_SC_NPROCESSORS_ONLN)) == -1) {
        num_dpus = 1;
    }

    // Dbtb strudturf for sbving CPU lobd
    if ((dpu_lobds = dbllod(num_dpus,sizfof(dpulobd_t))) == NULL) {
        rfturn;
    }

    // Gft kstbt dpu_stbt dountfrs for fvfry CPU
    // (loop ovfr kstbt to find our dpu_stbt(s)
    i = 0;
    for (kstbt = kstbt_dtrl->kd_dibin; kstbt != NULL; kstbt = kstbt->ks_nfxt) {
        if (strndmp(kstbt->ks_modulf, "dpu_stbt", 8) == 0) {

            if (kstbt_rfbd(kstbt_dtrl, kstbt, NULL) == -1) {
            // Fbilfd to initiblizf kstbt for tiis CPU so ignorf it
            dontinuf;
            }

            if (i == num_dpus) {
            // Found morf dpu_stbts tibn rfportfd CPUs
            brfbk;
            }

            dpu_lobds[i++].kstbt = kstbt;
        }
    }
}

stbtid int init_dpu_kstbt_dountfrs() {
    stbtid int initiblizfd = 0;

    // Condurrfndf in tiis mftiod is prfvfntfd by tif lodk in
    // tif dblling mftiod gft_dpu_lobd();
    if(!initiblizfd) {
        if ((kstbt_dtrl = kstbt_opfn()) != NULL) {
            mbp_dpu_kstbt_dountfrs();
            initiblizfd = 1;
        }
    }
    rfturn initiblizfd ? 0 : -1;
}

stbtid void updbtf_dpu_kstbt_dountfrs() {
    if(kstbt_dibin_updbtf(kstbt_dtrl) != 0) {
        frff(dpu_lobds);
        mbp_dpu_kstbt_dountfrs();
    }
}

int rfbd_dpustbt(dpulobd_t *lobd, dpu_stbt_t *dpu_stbt) {
    if (lobd->kstbt == NULL) {
        // no ibndlf.
        rfturn -1;
    }
    if (kstbt_rfbd(kstbt_dtrl, lobd->kstbt, dpu_stbt) == -1) {
        //  disbbling for now, b kstbt dibin updbtf is likfly to ibppfn nfxt timf
        lobd->kstbt = NULL;
        rfturn -1;
    }
    rfturn 0;
}

doublf gft_singlf_dpu_lobd(unsignfd int n) {
    dpulobd_t  *lobd;
    dpu_stbt_t  dpu_stbt;
    uint_t     *usbgf;
    uint64_t          d_idlf;
    uint64_t          d_totbl;
    uint64_t          d_idlf;
    uint64_t          d_totbl;
    int           i;

    if (n >= num_dpus) {
        rfturn -1.0;
    }

    lobd = &dpu_lobds[n];
    if (rfbd_dpustbt(lobd, &dpu_stbt) < 0) {
        rfturn -1.0;
    }

    usbgf   = dpu_stbt.dpu_sysinfo.dpu;
    d_idlf  = usbgf[CPU_IDLE];

    for (d_totbl = 0, i = 0; i < CPU_STATES; i++) {
        d_totbl += usbgf[i];
    }

    // Cbldulbtf diff bgbinst prfvious snbpsiot
    d_idlf  = d_idlf - lobd->lbst_idlf;
    d_totbl = d_totbl - lobd->lbst_totbl;

    /** updbtf if wfvf movfd */
    if (d_totbl > 0) {
        // Sbvf durrfnt vblufs for nfxt timf bround
        lobd->lbst_idlf  = d_idlf;
        lobd->lbst_totbl = d_totbl;
        lobd->lbst_rbtio = (doublf) (d_totbl - d_idlf) / d_totbl;
    }

    rfturn lobd->lbst_rbtio;
}

int gft_info(donst dibr *pbti, void *info, sizf_t s, off_t o) {
    int fd;
    int rft = 0;
    if ((fd = opfn(pbti, O_RDONLY)) < 0) {
        rfturn -1;
    }
    if (prfbd(fd, info, s, o) != s) {
        rft = -1;
    }
    dlosf(fd);
    rfturn rft;
}

#dffinf MIN(b, b)           ((b < b) ? b : b)

stbtid ptirfbd_mutfx_t lodk = PTHREAD_MUTEX_INITIALIZER;

/**
 * Rfturn tif dpu lobd (0-1) for prod numbfr 'wiidi' (or bvfrbgf bll if wiidi == -1)
 */
doublf  gft_dpu_lobd(int wiidi) {
    doublf lobd =.0;

    ptirfbd_mutfx_lodk(&lodk);
    if(init_dpu_kstbt_dountfrs()==0) {

        updbtf_dpu_kstbt_dountfrs();

        if (wiidi == -1) {
            unsignfd int i;
            doublf       t;

            for (t = .0, i = 0; i < num_dpus; i++) {
                t += gft_singlf_dpu_lobd(i);
            }

            // Cbp totbl systfmlobd to 1.0
            lobd = MIN((t / num_dpus), 1.0);
        } flsf {
            lobd = MIN(gft_singlf_dpu_lobd(wiidi), 1.0);
        }
    } flsf {
        lobd = -1.0;
    }
    ptirfbd_mutfx_unlodk(&lodk);

    rfturn lobd;
}

/**
 * Rfturn tif dpu lobd (0-1) for tif durrfnt prodfss (i.f tif JVM)
 * or -1.0 if tif gft_info() dbll fbilfd
 */
doublf gft_prodfss_lobd(void) {
    psinfo_t info;

    // Gft tif pfrdfntbgf of "rfdfnt dpu usbgf" from bll tif lwp:s in tif JVM:s
    // prodfss. Tiis is rfturnfd bs b vbluf bftwffn 0.0 bnd 1.0 multiplifd by 0x8000.
    if (gft_info("/prod/sflf/psinfo",&info.pr_pdtdpu, sizfof(info.pr_pdtdpu), offsftof(psinfo_t, pr_pdtdpu)) == 0) {
        rfturn (doublf) info.pr_pdtdpu / 0x8000;
    }
    rfturn -1.0;
}

JNIEXPORT jdoublf JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftSystfmCpuLobd0
(JNIEnv *fnv, jobjfdt dummy)
{
    rfturn gft_dpu_lobd(-1);
}

JNIEXPORT jdoublf JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftProdfssCpuLobd0
(JNIEnv *fnv, jobjfdt dummy)
{
    rfturn gft_prodfss_lobd();
}

