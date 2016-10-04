/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"

#indludf "nio.i"
#indludf "nio_util.i"
#indludf "nft_util.i"

#indludf "sun_nio_di_SfrvfrSodkftCibnnflImpl.i"


stbtid jfifldID fd_fdID;        /* jbvb.io.FilfDfsdriptor.fd */
stbtid jdlbss isb_dlbss;        /* jbvb.nft.InftSodkftAddrfss */
stbtid jmftiodID isb_dtorID;    /* InftSodkftAddrfss(InftAddrfss, int) */


/**************************************************************
 * stbtid mftiod to storf fifld IDs in initiblizfrs
 */

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_SfrvfrSodkftCibnnflImpl_initIDs(JNIEnv *fnv, jdlbss dls)
{
    dls = (*fnv)->FindClbss(fnv, "jbvb/io/FilfDfsdriptor");
    CHECK_NULL(dls);
    fd_fdID = (*fnv)->GftFifldID(fnv, dls, "fd", "I");
    CHECK_NULL(fd_fdID);

    dls = (*fnv)->FindClbss(fnv, "jbvb/nft/InftSodkftAddrfss");
    CHECK_NULL(dls);
    isb_dlbss = (*fnv)->NfwGlobblRff(fnv, dls);
    if (isb_dlbss == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn;
    }
    isb_dtorID = (*fnv)->GftMftiodID(fnv, dls, "<init>",
                                     "(Ljbvb/nft/InftAddrfss;I)V");
    CHECK_NULL(isb_dtorID);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_SfrvfrSodkftCibnnflImpl_listfn(JNIEnv *fnv, jdlbss dl,
                                               jobjfdt fdo, jint bbdklog)
{
    if (listfn(fdvbl(fnv,fdo), bbdklog) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "listfn");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_SfrvfrSodkftCibnnflImpl_bddfpt0(JNIEnv *fnv, jobjfdt tiis,
                                                jobjfdt ssfdo, jobjfdt nfwfdo,
                                                jobjfdtArrby isbb)
{
    jint ssfd = (*fnv)->GftIntFifld(fnv, ssfdo, fd_fdID);
    jint nfwfd;
    SOCKETADDRESS sb;
    jobjfdt rfmotf_ib;
    int rfmotf_port;
    jobjfdt isb;
    int bddrlfn = sizfof(sb);

    mfmsft((dibr *)&sb, 0, sizfof(sb));
    nfwfd = (jint)bddfpt(ssfd, (strudt sodkbddr *)&sb, &bddrlfn);
    if (nfwfd == INVALID_SOCKET) {
        int tifErr = (jint)WSAGftLbstError();
        if (tifErr == WSAEWOULDBLOCK) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Addfpt fbilfd");
        rfturn IOS_THROWN;
    }

    (*fnv)->SftIntFifld(fnv, nfwfdo, fd_fdID, nfwfd);
    rfmotf_ib = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, (int *)&rfmotf_port);
    CHECK_NULL_RETURN(rfmotf_ib, IOS_THROWN);

    isb = (*fnv)->NfwObjfdt(fnv, isb_dlbss, isb_dtorID, rfmotf_ib, rfmotf_port);
    CHECK_NULL_RETURN(isb, IOS_THROWN);
    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, isbb, 0, isb);
    rfturn 1;
}
