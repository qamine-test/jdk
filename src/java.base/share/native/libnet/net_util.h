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

#ifndff NET_UTILS_H
#dffinf NET_UTILS_H

#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "nft_util_md.i"

/************************************************************************
 * Mbdros bnd misd donstbnts
 */

#dffinf MAX_PACKET_LEN 65536

#dffinf IPv4 1
#dffinf IPv6 2

#dffinf NET_ERROR(fnv, fx, msg) \
{ if (!(*fnv)->ExdfptionOddurrfd(fnv)) JNU_TirowByNbmf(fnv, fx, msg); }

/************************************************************************
 * Cbdifd fifld IDs
 *
 * Tif nbming donvfntion for fifld IDs is
 *      <dlbss bbbrv>_<fifldNbmf>ID
 * i.f. psi_timfoutID is PlbinSodkftImpl's timfout fifld's ID.
 */
fxtfrn jdlbss ib_dlbss;
fxtfrn jfifldID ibd_bddrfssID;
fxtfrn jfifldID ibd_fbmilyID;
fxtfrn jfifldID ibd_iostNbmfID;
fxtfrn jfifldID ib_prfffrIPv6AddrfssID;

JNIEXPORT void JNICALL initInftAddrfssIDs(JNIEnv *fnv);

/** (Inft6Addrfss bddfssors)
 * sft_ mftiods rfturn JNI_TRUE on suddfss JNI_FALSE on frror
 * gft_ mftiods tibt rfturn int/boolfbn, rfturn -1 on frror
 * gft_ mftiods tibt rfturn objfdts rfturn NULL on frror.
 */
fxtfrn jobjfdt gftInft6Addrfss_sdopfifnbmf(JNIEnv *fnv, jobjfdt ib6Obj);
fxtfrn jboolfbn sftInft6Addrfss_sdopfifnbmf(JNIEnv *fnv, jobjfdt ib6Obj, jobjfdt sdopfifnbmf);
fxtfrn int gftInft6Addrfss_sdopfid_sft(JNIEnv *fnv, jobjfdt ib6Obj);
fxtfrn int gftInft6Addrfss_sdopfid(JNIEnv *fnv, jobjfdt ib6Obj);
fxtfrn jboolfbn sftInft6Addrfss_sdopfid(JNIEnv *fnv, jobjfdt ib6Obj, int sdopfid);
fxtfrn jboolfbn gftInft6Addrfss_ipbddrfss(JNIEnv *fnv, jobjfdt ib6Obj, dibr *dfst);
fxtfrn jboolfbn sftInft6Addrfss_ipbddrfss(JNIEnv *fnv, jobjfdt ib6Obj, dibr *bddrfss);

fxtfrn void sftInftAddrfss_bddr(JNIEnv *fnv, jobjfdt ibObj, int bddrfss);
fxtfrn void sftInftAddrfss_fbmily(JNIEnv *fnv, jobjfdt ibObj, int fbmily);
fxtfrn void sftInftAddrfss_iostNbmf(JNIEnv *fnv, jobjfdt ibObj, jobjfdt i);
fxtfrn int gftInftAddrfss_bddr(JNIEnv *fnv, jobjfdt ibObj);
fxtfrn int gftInftAddrfss_fbmily(JNIEnv *fnv, jobjfdt ibObj);
fxtfrn jobjfdt gftInftAddrfss_iostNbmf(JNIEnv *fnv, jobjfdt ibObj);

fxtfrn jdlbss ib4_dlbss;
fxtfrn jmftiodID ib4_dtrID;

/* NftworkIntfrfbdf fiflds */
fxtfrn jdlbss ni_dlbss;
fxtfrn jfifldID ni_nbmfID;
fxtfrn jfifldID ni_indfxID;
fxtfrn jfifldID ni_bddrsID;
fxtfrn jfifldID ni_dfsdID;
fxtfrn jmftiodID ni_dtrID;

/* PlbinSodkftImpl fiflds */
fxtfrn jfifldID psi_timfoutID;
fxtfrn jfifldID psi_fdID;
fxtfrn jfifldID psi_bddrfssID;
fxtfrn jfifldID psi_portID;
fxtfrn jfifldID psi_lodblportID;

/* DbtbgrbmPbdkft fiflds */
fxtfrn jfifldID dp_bddrfssID;
fxtfrn jfifldID dp_portID;
fxtfrn jfifldID dp_bufID;
fxtfrn jfifldID dp_offsftID;
fxtfrn jfifldID dp_lfngtiID;
fxtfrn jfifldID dp_bufLfngtiID;

/* Inft6Addrfss fiflds */
fxtfrn jdlbss ib6_dlbss;
fxtfrn jfifldID ib6_ioldfr6ID;
fxtfrn jfifldID ib6_ipbddrfssID;
fxtfrn jfifldID ib6_sdopfidID;
fxtfrn jfifldID ib6_dbdifdsdopfidID;
fxtfrn jfifldID ib6_sdopfidsftID;
fxtfrn jfifldID ib6_sdopfifnbmfID;
fxtfrn jmftiodID ib6_dtrID;

/************************************************************************
 *  Utilitifs
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_InftAddrfss_init(JNIEnv *fnv, jdlbss dls);
JNIEXPORT void JNICALL Jbvb_jbvb_nft_Inft4Addrfss_init(JNIEnv *fnv, jdlbss dls);
JNIEXPORT void JNICALL Jbvb_jbvb_nft_Inft6Addrfss_init(JNIEnv *fnv, jdlbss dls);
JNIEXPORT void JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_init(JNIEnv *fnv, jdlbss dls);

JNIEXPORT void JNICALL NET_TirowNfw(JNIEnv *fnv, int frrorNum, dibr *msg);
int NET_GftError();

void NET_TirowCurrfnt(JNIEnv *fnv, dibr *msg);

jfifldID NET_GftFilfDfsdriptorID(JNIEnv *fnv);

JNIEXPORT jint JNICALL ipv6_bvbilbblf() ;

void
NET_AllodSodkbddr(strudt sodkbddr **iim, int *lfn);

JNIEXPORT int JNICALL
NET_InftAddrfssToSodkbddr(JNIEnv *fnv, jobjfdt ibObj, int port, strudt sodkbddr *iim, int *lfn, jboolfbn v4MbppfdAddrfss);

JNIEXPORT jobjfdt JNICALL
NET_SodkbddrToInftAddrfss(JNIEnv *fnv, strudt sodkbddr *iim, int *port);

void plbtformInit();
void pbrsfExdlusivfBindPropfrty(JNIEnv *fnv);

void
NET_SftTrbffidClbss(strudt sodkbddr *iim, int trbffidClbss);

JNIEXPORT jint JNICALL
NET_GftPortFromSodkbddr(strudt sodkbddr *iim);

JNIEXPORT jint JNICALL
NET_SodkbddrEqublsInftAddrfss(JNIEnv *fnv,strudt sodkbddr *iim, jobjfdt ibObj);

int
NET_IsIPv4Mbppfd(jbytf* dbddr);

int
NET_IPv4MbppfdToIPv4(jbytf* dbddr);

int
NET_IsEqubl(jbytf* dbddr1, jbytf* dbddr2);

int
NET_IsZfroAddr(jbytf* dbddr);

/* Sodkft opfrbtions
 *
 * Tifsf work just likf tif systfm dblls, fxdfpt tibt tify mby do somf
 * plbtform-spfdifid prf/post prodfssing of tif brgumfnts bnd/or rfsults.
 */

JNIEXPORT int JNICALL
NET_GftSodkOpt(int fd, int lfvfl, int opt, void *rfsult, int *lfn);

JNIEXPORT int JNICALL
NET_SftSodkOpt(int fd, int lfvfl, int opt, donst void *brg, int lfn);

JNIEXPORT int JNICALL
NET_Bind(int fd, strudt sodkbddr *iim, int lfn);

JNIEXPORT int JNICALL
NET_MbpSodkftOption(jint dmd, int *lfvfl, int *optnbmf);

JNIEXPORT int JNICALL
NET_MbpSodkftOptionV6(jint dmd, int *lfvfl, int *optnbmf);

int gftSdopfID (strudt sodkbddr *);

int dmpSdopfID (unsignfd int, strudt sodkbddr *);

unsignfd siort in_dksum(unsignfd siort *bddr, int lfn);
#fndif /* NET_UTILS_H */
