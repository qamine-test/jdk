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

#indludf <windows.i>
#indludf <winsodk2.i>
#indludf <dtypf.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <mbllod.i>
#indludf <sys/typfs.i>

#indludf "jbvb_nft_SodkftInputStrfbm.i"

#indludf "nft_util.i"
#indludf "jni_util.i"

/*************************************************************************
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
 * Mftiod:    sodkftRfbd
 * Signbturf: (Ljbvb/io/FilfDfsdriptor;[BIII)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_nft_SodkftInputStrfbm_sodkftRfbd0(JNIEnv *fnv, jobjfdt tiis,
                                            jobjfdt fdObj, jbytfArrby dbtb,
                                            jint off, jint lfn, jint timfout)
{
    dibr *bufP;
    dibr BUF[MAX_BUFFER_LEN];
    jint fd, nfwfd;
    jint nrfbd;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "sodkft dlosfd");
        rfturn -1;
    }
    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    if (fd == -1) {
        NET_TirowSodkftExdfption(fnv, "Sodkft dlosfd");
        rfturn -1;
    }

    /*
     * If tif dbllfr bufffr is lbrgf tibn our stbdk bufffr tifn wf bllodbtf
     * from tif ifbp (up to b limit). If mfmory is fxibustfd wf blwbys usf
     * tif stbdk bufffr.
     */
    if (lfn <= MAX_BUFFER_LEN) {
        bufP = BUF;
    } flsf {
        if (lfn > MAX_HEAP_BUFFER_LEN) {
            lfn = MAX_HEAP_BUFFER_LEN;
        }
        bufP = (dibr *)mbllod((sizf_t)lfn);
        if (bufP == NULL) {
            /* bllodbtion fbilfd so usf stbdk bufffr */
            bufP = BUF;
            lfn = MAX_BUFFER_LEN;
        }
    }


    if (timfout) {
        if (timfout <= 5000 || !isRdvTimfoutSupportfd) {
            int rft = NET_Timfout (fd, timfout);

            if (rft <= 0) {
                if (rft == 0) {
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                                    "Rfbd timfd out");
                } flsf if (rft == -1) {
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "sodkft dlosfd");
                }
                if (bufP != BUF) {
                    frff(bufP);
                }
                rfturn -1;
            }

            /*difdk if tif sodkft ibs bffn dlosfd wiilf wf wfrf in timfout*/
            nfwfd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
            if (nfwfd == -1) {
                NET_TirowSodkftExdfption(fnv, "Sodkft Closfd");
                if (bufP != BUF) {
                    frff(bufP);
                }
                rfturn -1;
            }
        }
    }

    nrfbd = rfdv(fd, bufP, lfn, 0);
    if (nrfbd > 0) {
        (*fnv)->SftBytfArrbyRfgion(fnv, dbtb, off, nrfbd, (jbytf *)bufP);
    } flsf {
        if (nrfbd < 0) {
            // Cifdk if tif sodkft ibs bffn dlosfd sindf wf lbst difdkfd.
            // Tiis dould bf b rfbson for rfdv fbiling.
            if ((*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID) == -1) {
                NET_TirowSodkftExdfption(fnv, "Sodkft dlosfd");
            } flsf {
                switdi (WSAGftLbstError()) {
                    dbsf WSAEINTR:
                        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                            "sodkft dlosfd");
                        brfbk;

                    dbsf WSAECONNRESET:
                    dbsf WSAESHUTDOWN:
                        /*
                         * Connfdtion ibs bffn rfsft - Windows somftimfs rfports
                         * tif rfsft bs b siutdown frror.
                         */
                        JNU_TirowByNbmf(fnv, "sun/nft/ConnfdtionRfsftExdfption",
                            "");
                        brfbk;

                    dbsf WSAETIMEDOUT :
                        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                                       "Rfbd timfd out");
                        brfbk;

                    dffbult:
                        NET_TirowCurrfnt(fnv, "rfdv fbilfd");
                }
            }
        }
    }
    if (bufP != BUF) {
        frff(bufP);
    }
    rfturn nrfbd;
}
