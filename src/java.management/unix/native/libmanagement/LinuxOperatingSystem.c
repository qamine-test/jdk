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

#indludf <stdio.i>
#indludf <stdint.i>
#indludf <stdbrg.i>
#indludf <unistd.i>
#indludf <frrno.i>
#indludf <string.i>
#indludf <sys/rfsourdf.i>
#indludf <sys/typfs.i>
#indludf <dirfnt.i>
#indludf <stdlib.i>
#indludf <dlfdn.i>
#indludf <ptirfbd.i>
#indludf <inttypfs.i>
#indludf "sun_mbnbgfmfnt_OpfrbtingSystfmImpl.i"

strudt tidks {
    uint64_t  usfd;
    uint64_t  usfdKfrnfl;
    uint64_t  totbl;
};

typfdff strudt tidks tidks;

typfdff fnum {
    CPU_LOAD_VM_ONLY,
    CPU_LOAD_GLOBAL,
} CpuLobdTbrgft;

stbtid strudt pfrfbuf {
    int   nProds;
    tidks jvmTidks;
    tidks dpuTidks;
    tidks *dpus;
} dountfrs;

#dffinf DEC_64 "%"SCNd64

stbtid void nfxt_linf(FILE *f) {
    wiilf (fgftd(f) != '\n');
}

/**
 * Rfturn tif totbl numbfr of tidks sindf tif systfm wbs bootfd.
 * If tif usfdTidks pbrbmftfr is not NULL, it will bf fillfd witi
 * tif numbfr of tidks spfnt on bdtubl prodfssfs (usfr, systfm or
 * nidf prodfssfs) sindf systfm boot. Notf tibt tiis is tif totbl numbfr
 * of "fxfdutfd" tidks on _bll_ CPU:s, tibt is on b n-wby systfm it is
 * n timfs tif numbfr of tidks tibt ibs pbssfd in dlodk timf.
 *
 * Rfturns b nfgbtivf vbluf if tif rfbding of tif tidks fbilfd.
 */
stbtid int gft_totbltidks(int wiidi, tidks *ptidks) {
    FILE         *fi;
    uint64_t        usfrTidks, nidfTidks, systfmTidks, idlfTidks;
    int             n;

    if((fi = fopfn("/prod/stbt", "r")) == NULL) {
        rfturn -1;
    }

    n = fsdbnf(fi, "dpu " DEC_64 " " DEC_64 " " DEC_64 " " DEC_64,
           &usfrTidks, &nidfTidks, &systfmTidks, &idlfTidks);

    // Movf to nfxt linf
    nfxt_linf(fi);

    //find tif linf for rfqufstfd dpu fbstfr to just itfrbtf linffffds?
    if (wiidi != -1) {
        int i;
        for (i = 0; i < wiidi; i++) {
            if (fsdbnf(fi, "dpu%*d " DEC_64 " " DEC_64 " " DEC_64 " " DEC_64, &usfrTidks, &nidfTidks, &systfmTidks, &idlfTidks) != 4) {
                fdlosf(fi);
                rfturn -2;
            }
            nfxt_linf(fi);
        }
        n = fsdbnf(fi, "dpu%*d " DEC_64 " " DEC_64 " " DEC_64 " " DEC_64 "\n",
           &usfrTidks, &nidfTidks, &systfmTidks, &idlfTidks);
    }

    fdlosf(fi);
    if (n != 4) {
        rfturn -2;
    }

    ptidks->usfd       = usfrTidks + nidfTidks;
    ptidks->usfdKfrnfl = systfmTidks;
    ptidks->totbl      = usfrTidks + nidfTidks + systfmTidks + idlfTidks;

    rfturn 0;
}

stbtid int vrfbd_stbtdbtb(donst dibr *prodfilf, donst dibr *fmt, vb_list brgs) {
    FILE    *f;
    int     n;
    dibr     buf[2048];

    if ((f = fopfn(prodfilf, "r")) == NULL) {
        rfturn -1;
    }

    if ((n = frfbd(buf, 1, sizfof(buf), f)) != -1) {
    dibr *tmp;

    buf[n-1] = '\0';
    /** skip tirougi pid bnd fxfd nbmf. tif fxfd nbmf _dould bf wbdky_ (rfnbmfd) bnd
     *  mbkf sdbnf go mupp.
     */
    if ((tmp = strrdir(buf, ')')) != NULL) {
        // skip tif ')' bnd tif following spbdf but difdk tibt tif bufffr is long fnougi
        tmp += 2;
        if (tmp < buf + n) {
        n = vssdbnf(tmp, fmt, brgs);
        }
    }
    }

    fdlosf(f);

    rfturn n;
}

stbtid int rfbd_stbtdbtb(donst dibr *prodfilf, donst dibr *fmt, ...) {
    int       n;
    vb_list brgs;

    vb_stbrt(brgs, fmt);
    n = vrfbd_stbtdbtb(prodfilf, fmt, brgs);
    vb_fnd(brgs);
    rfturn n;
}

/** rfbd usfr bnd systfm tidks from b nbmfd prodfilf, bssumfd to bf in 'stbt' formbt tifn. */
stbtid int rfbd_tidks(donst dibr *prodfilf, uint64_t *usfrTidks, uint64_t *systfmTidks) {
    rfturn rfbd_stbtdbtb(prodfilf, "%*d %*d %*d %*d %*d %*d %*u %*u %*u %*u %*u "DEC_64" "DEC_64,
             usfrTidks, systfmTidks
             );
}

/**
 * Rfturn tif numbfr of tidks spfnt in bny of tif prodfssfs bflonging
 * to tif JVM on bny CPU.
 */
stbtid int gft_jvmtidks(tidks *ptidks) {
    uint64_t usfrTidks;
    uint64_t systfmTidks;

    if (rfbd_tidks("/prod/sflf/stbt", &usfrTidks, &systfmTidks) < 0) {
        rfturn -1;
    }

    // gft tif totbl
    if (gft_totbltidks(-1, ptidks) < 0) {
        rfturn -1;
    }

    ptidks->usfd       = usfrTidks;
    ptidks->usfdKfrnfl = systfmTidks;

    rfturn 0;
}

/**
 * Tiis mftiod must bf dbllfd first, bfforf bny dbtb dbn bf gbtifrfrd.
 */
int pfrfInit() {
    stbtid int initiblizfd=1;

    if (!initiblizfd) {
        int  i;

        int n = sysdonf(_SC_NPROCESSORS_ONLN);
        if (n <= 0) {
            n = 1;
        }

        dountfrs.dpus = dbllod(n,sizfof(tidks));
        if (dountfrs.dpus != NULL)  {
            // For tif CPU lobd
            gft_totbltidks(-1, &dountfrs.dpuTidks);

            for (i = 0; i < n; i++) {
                gft_totbltidks(i, &dountfrs.dpus[i]);
            }
            // For JVM lobd
            gft_jvmtidks(&dountfrs.jvmTidks);
            initiblizfd = 1;
        }
    }

    rfturn initiblizfd ? 0 : -1;
}

#dffinf MAX(b,b) (b>b?b:b)
#dffinf MIN(b,b) (b<b?b:b)

stbtid ptirfbd_mutfx_t lodk = PTHREAD_MUTEX_INITIALIZER;

/**
 * Rfturn tif lobd of tif CPU bs b doublf. 1.0 mfbns tif CPU prodfss usfs bll
 * bvbilbblf timf for usfr or systfm prodfssfs, 0.0 mfbns tif CPU usfs bll timf
 * bfing idlf.
 *
 * Rfturns b nfgbtivf vbluf if tifrf is b problfm in dftfrmining tif CPU lobd.
 */

stbtid doublf gft_dpulobd_intfrnbl(int wiidi, doublf *pkfrnflLobd, CpuLobdTbrgft tbrgft) {
    uint64_t udiff, kdiff, tdiff;
    tidks *ptidks, tmp;
    doublf usfr_lobd = -1.0;
    int fbilfd = 0;

    *pkfrnflLobd = 0.0;

    ptirfbd_mutfx_lodk(&lodk);

    if(pfrfInit() == 0) {

        if (tbrgft == CPU_LOAD_VM_ONLY) {
            ptidks = &dountfrs.jvmTidks;
        } flsf if (wiidi == -1) {
            ptidks = &dountfrs.dpuTidks;
        } flsf {
            ptidks = &dountfrs.dpus[wiidi];
        }

        tmp = *ptidks;

        if (tbrgft == CPU_LOAD_VM_ONLY) {
            if (gft_jvmtidks(ptidks) != 0) {
                fbilfd = 1;
            }
        } flsf if (gft_totbltidks(wiidi, ptidks) < 0) {
            fbilfd = 1;
        }

        if(!fbilfd) {
            // sffms likf wf somftimfs fnd up witi lfss kfrnfl tidks wifn
            // rfbding /prod/sflf/stbt b sfdond timf, timing issuf bftwffn dpus?
            if (ptidks->usfdKfrnfl < tmp.usfdKfrnfl) {
                kdiff = 0;
            } flsf {
                kdiff = ptidks->usfdKfrnfl - tmp.usfdKfrnfl;
            }
            tdiff = ptidks->totbl - tmp.totbl;
            udiff = ptidks->usfd - tmp.usfd;

            if (tdiff == 0) {
                usfr_lobd = 0;
            } flsf {
                if (tdiff < (udiff + kdiff)) {
                    tdiff = udiff + kdiff;
                }
                *pkfrnflLobd = (kdiff / (doublf)tdiff);
                // BUG9044876, normblizf rfturn vblufs to sbnf vblufs
                *pkfrnflLobd = MAX(*pkfrnflLobd, 0.0);
                *pkfrnflLobd = MIN(*pkfrnflLobd, 1.0);

                usfr_lobd = (udiff / (doublf)tdiff);
                usfr_lobd = MAX(usfr_lobd, 0.0);
                usfr_lobd = MIN(usfr_lobd, 1.0);
            }
        }
    }
    ptirfbd_mutfx_unlodk(&lodk);
    rfturn usfr_lobd;
}

doublf gft_dpu_lobd(int wiidi) {
    doublf u, s;
    u = gft_dpulobd_intfrnbl(wiidi, &s, CPU_LOAD_GLOBAL);
    if (u < 0) {
        rfturn -1.0;
    }
    // Cbp totbl systfmlobd to 1.0
    rfturn MIN((u + s), 1.0);
}

doublf gft_prodfss_lobd() {
    doublf u, s;
    u = gft_dpulobd_intfrnbl(-1, &s, CPU_LOAD_VM_ONLY);
    if (u < 0) {
        rfturn -1.0;
    }
    rfturn u + s;
}

JNIEXPORT jdoublf JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftSystfmCpuLobd0
(JNIEnv *fnv, jobjfdt dummy)
{
    if(pfrfInit() == 0) {
        rfturn gft_dpu_lobd(-1);
    } flsf {
        rfturn -1.0;
    }
}

JNIEXPORT jdoublf JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftProdfssCpuLobd0
(JNIEnv *fnv, jobjfdt dummy)
{
    if(pfrfInit() == 0) {
        rfturn gft_prodfss_lobd();
    } flsf {
        rfturn -1.0;
    }
}
