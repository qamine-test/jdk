/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "nft_util.i"

#indludf "jbvb_nft_SodkftInputStrfbm.i"


/************************************************************************
 * SodkftInputStrfbm
 */

stbtid jfifldID IO_fd_fdID;

/*
 * Clbss:     jbvb_nft_SodkftInputStrfbm
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_SodkftInputStrfbm_init(JNIEnv *fnv, jdlbss dls) {
    IO_fd_fdID = NET_GftFilfDfsdriptorID(fnv);
}

/*
 * Clbss:     jbvb_nft_SodkftInputStrfbm
 * Mftiod:    sodkftRfbd0
 * Signbturf: (Ljbvb/io/FilfDfsdriptor;[BIII)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_nft_SodkftInputStrfbm_sodkftRfbd0(JNIEnv *fnv, jobjfdt tiis,
                                            jobjfdt fdObj, jbytfArrby dbtb,
                                            jint off, jint lfn, jint timfout)
{
    dibr BUF[MAX_BUFFER_LEN];
    dibr *bufP;
    jint fd, nrfbd;

    if (IS_NULL(fdObj)) {
        /* siouldn't tiis bf b NullPointfrExdfption? -br */
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn -1;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
        /* Bug 4086704 - If tif Sodkft bssodibtfd witi tiis filf dfsdriptor
         * wbs dlosfd (sysClosfFD), tifn tif filf dfsdriptor is sft to -1.
         */
        if (fd == -1) {
            JNU_TirowByNbmf(fnv, "jbvb/nft/SodkftExdfption", "Sodkft dlosfd");
            rfturn -1;
        }
    }

    /*
     * If tif rfbd is grfbtfr tibn our stbdk bllodbtfd bufffr tifn
     * wf bllodbtf from tif ifbp (up to b limit)
     */
    if (lfn > MAX_BUFFER_LEN) {
        if (lfn > MAX_HEAP_BUFFER_LEN) {
            lfn = MAX_HEAP_BUFFER_LEN;
        }
        bufP = (dibr *)mbllod((sizf_t)lfn);
        if (bufP == NULL) {
            bufP = BUF;
            lfn = MAX_BUFFER_LEN;
        }
    } flsf {
        bufP = BUF;
    }

    if (timfout) {
        nrfbd = NET_Timfout(fd, timfout);
        if (nrfbd <= 0) {
            if (nrfbd == 0) {
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                            "Rfbd timfd out");
            } flsf if (nrfbd == -1) {
                if (frrno == EBADF) {
                     JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
                } flsf if (frrno == ENOMEM) {
                    JNU_TirowOutOfMfmoryError(fnv, "NET_Timfout nbtivf ifbp bllodbtion fbilfd");
                } flsf {
                    NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                                  "sflfdt/poll fbilfd");
                }
            }
            if (bufP != BUF) {
                frff(bufP);
            }
            rfturn -1;
        }
    }

    nrfbd = NET_Rfbd(fd, bufP, lfn);

    if (nrfbd <= 0) {
        if (nrfbd < 0) {

            switdi (frrno) {
                dbsf ECONNRESET:
                dbsf EPIPE:
                    JNU_TirowByNbmf(fnv, "sun/nft/ConnfdtionRfsftExdfption",
                        "Connfdtion rfsft");
                    brfbk;

                dbsf EBADF:
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
                    brfbk;

                dbsf EINTR:
                     JNU_TirowByNbmf(fnv, JNU_JAVAIOPKG "IntfrruptfdIOExdfption",
                           "Opfrbtion intfrruptfd");
                     brfbk;

                dffbult:
                    NET_TirowByNbmfWitiLbstError(fnv,
                        JNU_JAVANETPKG "SodkftExdfption", "Rfbd fbilfd");
            }
        }
    } flsf {
        (*fnv)->SftBytfArrbyRfgion(fnv, dbtb, off, nrfbd, (jbytf *)bufP);
    }

    if (bufP != BUF) {
        frff(bufP);
    }
    rfturn nrfbd;
}
