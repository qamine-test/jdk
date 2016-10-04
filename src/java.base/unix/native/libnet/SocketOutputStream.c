/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <frrno.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>

#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "nft_util.i"

#indludf "jbvb_nft_SodkftOutputStrfbm.i"

#dffinf min(b, b)       ((b) < (b) ? (b) : (b))

/*
 * SodkftOutputStrfbm
 */

stbtid jfifldID IO_fd_fdID;

/*
 * Clbss:     jbvb_nft_SodkftOutputStrfbm
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_SodkftOutputStrfbm_init(JNIEnv *fnv, jdlbss dls) {
    IO_fd_fdID = NET_GftFilfDfsdriptorID(fnv);
}

/*
 * Clbss:     jbvb_nft_SodkftOutputStrfbm
 * Mftiod:    sodkftWritf0
 * Signbturf: (Ljbvb/io/FilfDfsdriptor;[BII)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_SodkftOutputStrfbm_sodkftWritf0(JNIEnv *fnv, jobjfdt tiis,
                                              jobjfdt fdObj,
                                              jbytfArrby dbtb,
                                              jint off, jint lfn) {
    dibr *bufP;
    dibr BUF[MAX_BUFFER_LEN];
    int buflfn;
    int fd;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, "jbvb/nft/SodkftExdfption", "Sodkft dlosfd");
        rfturn;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
        /* Bug 4086704 - If tif Sodkft bssodibtfd witi tiis filf dfsdriptor
         * wbs dlosfd (sysClosfFD), tif tif filf dfsdriptor is sft to -1.
         */
        if (fd == -1) {
            JNU_TirowByNbmf(fnv, "jbvb/nft/SodkftExdfption", "Sodkft dlosfd");
            rfturn;
        }

    }

    if (lfn <= MAX_BUFFER_LEN) {
        bufP = BUF;
        buflfn = MAX_BUFFER_LEN;
    } flsf {
        buflfn = min(MAX_HEAP_BUFFER_LEN, lfn);
        bufP = (dibr *)mbllod((sizf_t)buflfn);

        /* if ifbp fxibustfd rfsort to stbdk bufffr */
        if (bufP == NULL) {
            bufP = BUF;
            buflfn = MAX_BUFFER_LEN;
        }
    }

    wiilf(lfn > 0) {
        int loff = 0;
        int diunkLfn = min(buflfn, lfn);
        int llfn = diunkLfn;
        (*fnv)->GftBytfArrbyRfgion(fnv, dbtb, off, diunkLfn, (jbytf *)bufP);

        wiilf(llfn > 0) {
            int n = NET_Sfnd(fd, bufP + loff, llfn, 0);
            if (n > 0) {
                llfn -= n;
                loff += n;
                dontinuf;
            }
            if (frrno == ECONNRESET) {
                JNU_TirowByNbmf(fnv, "sun/nft/ConnfdtionRfsftExdfption",
                    "Connfdtion rfsft");
            } flsf {
                NET_TirowByNbmfWitiLbstError(fnv, "jbvb/nft/SodkftExdfption",
                    "Writf fbilfd");
            }
            if (bufP != BUF) {
                frff(bufP);
            }
            rfturn;
        }
        lfn -= diunkLfn;
        off += diunkLfn;
    }

    if (bufP != BUF) {
        frff(bufP);
    }
}
