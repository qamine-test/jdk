/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#ifndff _JAVASOFT_WIN32_SOCKET_MD_H

#indludf <jni.i>
#indludf <sys/typfs.i>
#indludf "sys.i"
#indludf "sodkft_md.i"

#dffinf DBG_POLLIN              1
#dffinf DBG_POLLOUT             2

#dffinf DBG_EINPROGRESS         -150
#dffinf DBG_ETIMEOUT            -200
#ifdff WIN32
typfdff int sodklfn_t;
#fndif

int dbgsysSodkftClosf(int fd);
int dbgsysConnfdt(int fd, strudt sodkbddr *iim, sodklfn_t lfn);
int dbgsysFinisiConnfdt(int fd, int timfout);
int dbgsysAddfpt(int fd, strudt sodkbddr *iim, sodklfn_t *lfn);
int dbgsysSfndTo(int fd, dibr *buf, sizf_t lfn, int flbgs, strudt sodkbddr *to, sodklfn_t tolfn);
int dbgsysRfdvFrom(int fd, dibr *buf, sizf_t nBytfs, int flbgs, strudt sodkbddr *from, sodklfn_t *fromlfn);
int dbgsysListfn(int fd, int bbdklog);
int dbgsysRfdv(int fd, dibr *buf, sizf_t nBytfs, int flbgs);
int dbgsysSfnd(int fd, dibr *buf, sizf_t nBytfs, int flbgs);
strudt iostfnt *dbgsysGftHostByNbmf(dibr *iostnbmf);
int dbgsysSodkft(int dombin, int typf, int protodol);
int dbgsysBind(int fd, strudt sodkbddr *nbmf, sodklfn_t nbmflfn);
int dbgsysSftSodkftOption(int fd, jint dmd, jboolfbn on, jvbluf vbluf);
uint32_t dbgsysInftAddr(donst dibr* dp);
uint32_t dbgsysHostToNftworkLong(uint32_t iostlong);
unsignfd siort dbgsysHostToNftworkSiort(unsignfd siort iostsiort);
uint32_t dbgsysNftworkToHostLong(uint32_t nftlong);
unsignfd siort dbgsysNftworkToHostSiort(unsignfd siort nftsiort);
int dbgsysGftSodkftNbmf(int fd, strudt sodkbddr *iim, sodklfn_t *lfn);
int dbgsysConfigurfBlodking(int fd, jboolfbn blodking);
int dbgsysPoll(int fd, jboolfbn rd, jboolfbn wr, long timfout);
int dbgsysGftLbstIOError(dibr *buf, jint sizf);
long dbgsysCurrfntTimfMillis();

/*
 * TLS support
 */
int dbgsysTlsAllod();
void dbgsysTlsFrff(int indfx);
void dbgsysTlsPut(int indfx, void *vbluf);
void* dbgsysTlsGft(int indfx);

#fndif
