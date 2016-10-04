/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nio_di_SodkftDispbtdifr.i"
#indludf "nio.i"
#indludf "nio_util.i"


/**************************************************************
 * SodkftDispbtdifr.d
 */

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_SodkftDispbtdifr_rfbd0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                      jlong bddrfss, jint lfn)
{
    /* sft up */
    int i = 0;
    DWORD rfbd = 0;
    DWORD flbgs = 0;
    jint fd = fdvbl(fnv, fdo);
    WSABUF buf;

    /* limit sizf */
    if (lfn > MAX_BUFFER_SIZE)
        lfn = MAX_BUFFER_SIZE;

    /* dfstinbtion bufffr bnd sizf */
    buf.buf = (dibr *)bddrfss;
    buf.lfn = (u_long)lfn;

    /* rfbd into tif bufffrs */
    i = WSARfdv((SOCKET)fd, /* Sodkft */
            &buf,           /* pointfrs to tif bufffrs */
            (DWORD)1,       /* numbfr of bufffrs to prodfss */
            &rfbd,          /* rfdfivfs numbfr of bytfs rfbd */
            &flbgs,         /* no flbgs */
            0,              /* no ovfrlbppfd sodkfts */
            0);             /* no domplftion routinf */

    if (i == SOCKET_ERROR) {
        int tifErr = (jint)WSAGftLbstError();
        if (tifErr == WSAEWOULDBLOCK) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Rfbd fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn donvfrtRfturnVbl(fnv, (jint)rfbd, JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_SodkftDispbtdifr_rfbdv0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                       jlong bddrfss, jint lfn)
{
    /* sft up */
    int i = 0;
    DWORD rfbd = 0;
    DWORD flbgs = 0;
    jint fd = fdvbl(fnv, fdo);
    strudt iovfd *iovp = (strudt iovfd *)bddrfss;
    WSABUF *bufs = mbllod(lfn * sizfof(WSABUF));
    jint rfm = MAX_BUFFER_SIZE;

    if (bufs == 0) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn IOS_THROWN;
    }

    /* dopy iovfd into WSABUF */
    for(i=0; i<lfn; i++) {
        jint iov_lfn = iovp[i].iov_lfn;
        if (iov_lfn > rfm)
            iov_lfn = rfm;
        bufs[i].buf = (dibr *)iovp[i].iov_bbsf;
        bufs[i].lfn = (u_long)iov_lfn;
        rfm -= iov_lfn;
        if (rfm == 0) {
            lfn = i+1;
            brfbk;
        }
    }

    /* rfbd into tif bufffrs */
    i = WSARfdv((SOCKET)fd, /* Sodkft */
            bufs,           /* pointfrs to tif bufffrs */
            (DWORD)lfn,     /* numbfr of bufffrs to prodfss */
            &rfbd,          /* rfdfivfs numbfr of bytfs rfbd */
            &flbgs,         /* no flbgs */
            0,              /* no ovfrlbppfd sodkfts */
            0);             /* no domplftion routinf */

    /* dlfbn up */
    frff(bufs);

    if (i != 0) {
        int tifErr = (jint)WSAGftLbstError();
        if (tifErr == WSAEWOULDBLOCK) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Vfdtor rfbd fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn donvfrtLongRfturnVbl(fnv, (jlong)rfbd, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_SodkftDispbtdifr_writf0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                       jlong bddrfss, jint totbl)
{
    /* sft up */
    int i = 0;
    DWORD writtfn = 0;
    jint dount = 0;
    jint fd = fdvbl(fnv, fdo);
    WSABUF buf;

    do {
        /* limit sizf */
        jint lfn = totbl - dount;
        if (lfn > MAX_BUFFER_SIZE)
            lfn = MAX_BUFFER_SIZE;

        /* dopy iovfd into WSABUF */
        buf.buf = (dibr *)bddrfss;
        buf.lfn = (u_long)lfn;

        /* writf from tif bufffr */
        i = WSASfnd((SOCKET)fd,     /* Sodkft */
                    &buf,           /* pointfrs to tif bufffrs */
                    (DWORD)1,       /* numbfr of bufffrs to prodfss */
                    &writtfn,       /* rfdfivfs numbfr of bytfs writtfn */
                    0,              /* no flbgs */
                    0,              /* no ovfrlbppfd sodkfts */
                    0);             /* no domplftion routinf */

        if (i == SOCKET_ERROR) {
            if (dount > 0) {
                /* dbn't tirow fxdfption wifn somf bytfs ibvf bffn writtfn */
                brfbk;
            } flsf {
               int tifErr = (jint)WSAGftLbstError();
               if (tifErr == WSAEWOULDBLOCK) {
                   rfturn IOS_UNAVAILABLE;
               }
               JNU_TirowIOExdfptionWitiLbstError(fnv, "Writf fbilfd");
               rfturn IOS_THROWN;
            }
        }

        dount += writtfn;
        bddrfss += writtfn;

    } wiilf ((dount < totbl) && (writtfn == MAX_BUFFER_SIZE));

    rfturn dount;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_SodkftDispbtdifr_writfv0(JNIEnv *fnv, jdlbss dlbzz,
                                         jobjfdt fdo, jlong bddrfss, jint lfn)
{
    /* sft up */
    int nfxt_indfx, nfxt_offsft, rft=0;
    DWORD writtfn = 0;
    jint fd = fdvbl(fnv, fdo);
    strudt iovfd *iovp = (strudt iovfd *)bddrfss;
    WSABUF *bufs = mbllod(lfn * sizfof(WSABUF));
    jlong dount = 0;

    if (bufs == 0) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn IOS_THROWN;
    }

    // nfxt bufffr bnd offsft to donsumf
    nfxt_indfx = 0;
    nfxt_offsft = 0;

    wiilf (nfxt_indfx  < lfn) {
        DWORD buf_dount = 0;

        /* Prfpbrf tif WSABUF brrby to b mbximum totbl sizf of MAX_BUFFER_SIZE */
        jint rfm = MAX_BUFFER_SIZE;
        wiilf (nfxt_indfx < lfn && rfm > 0) {
            jint iov_lfn = iovp[nfxt_indfx].iov_lfn - nfxt_offsft;
            dibr* ptr = (dibr *)iovp[nfxt_indfx].iov_bbsf;
            ptr += nfxt_offsft;
            if (iov_lfn > rfm) {
                iov_lfn = rfm;
                nfxt_offsft += rfm;
            } flsf {
                nfxt_indfx ++;
                nfxt_offsft = 0;
            }

            bufs[buf_dount].buf = ptr;
            bufs[buf_dount].lfn = (u_long)iov_lfn;
            buf_dount++;

            rfm -= iov_lfn;
        }

        /* writf tif bufffrs */
        rft = WSASfnd((SOCKET)fd,           /* Sodkft */
                              bufs,         /* pointfrs to tif bufffrs */
                              buf_dount,    /* numbfr of bufffrs to prodfss */
                              &writtfn,     /* rfdfivfs numbfr of bytfs writtfn */
                              0,            /* no flbgs */
                              0,            /* no ovfrlbppfd sodkfts */
                              0);           /* no domplftion routinf */

        if (rft == SOCKET_ERROR) {
            brfbk;
        }

        dount += writtfn;
    }

    /* dlfbn up */
    frff(bufs);

    if (rft == SOCKET_ERROR && dount == 0) {
        int tifErr = (jint)WSAGftLbstError();
        if (tifErr == WSAEWOULDBLOCK) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Vfdtor writf fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn donvfrtLongRfturnVbl(fnv, dount, JNI_FALSE);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_SodkftDispbtdifr_prfClosf0(JNIEnv *fnv, jdlbss dlbzz,
                                           jobjfdt fdo)
{
    jint fd = fdvbl(fnv, fdo);
    strudt lingfr l;
    int lfn = sizfof(l);
    if (gftsodkopt(fd, SOL_SOCKET, SO_LINGER, (dibr *)&l, &lfn) == 0) {
        if (l.l_onoff == 0) {
            WSASfndDisdonnfdt(fd, NULL);
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_SodkftDispbtdifr_dlosf0(JNIEnv *fnv, jdlbss dlbzz,
                                         jobjfdt fdo)
{
    jint fd = fdvbl(fnv, fdo);
    if (dlosfsodkft(fd) == SOCKET_ERROR) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sodkft dlosf fbilfd");
    }
}
