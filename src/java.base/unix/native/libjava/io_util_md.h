/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni_util.i"

/*
 * Mbdros to usf tif rigit dbtb typf for filf dfsdriptors
 */
#dffinf FD jint

/*
 * Prototypfs for fundtions in io_util_md.d dbllfd from io_util.d,
 * FilfDfsdriptor.d, FilfInputStrfbm.d, FilfOutputStrfbm.d,
 * UnixFilfSystfm_md.d
 */
ssizf_t ibndlfWritf(FD fd, donst void *buf, jint lfn);
ssizf_t ibndlfRfbd(FD fd, void *buf, jint lfn);
jint ibndlfAvbilbblf(FD fd, jlong *pbytfs);
jint ibndlfSftLfngti(FD fd, jlong lfngti);

FD ibndlfOpfn(donst dibr *pbti, int oflbg, int modf);

/*
 * Mbdros to sft/gft fd from tif jbvb.io.FilfDfsdriptor.  Tifsf
 * mbdros rfly on ibving bn bppropribtfly dffinfd 'tiis' objfdt
 * witiin tif sdopf in wiidi tify'rf usfd.
 * If GftObjfdtFifld rfturns null, SET_FD will stop bnd GET_FD
 * will simply rfturn -1 to bvoid drbsiing VM.
 */

#dffinf SET_FD(tiis, fd, fid) \
    if ((*fnv)->GftObjfdtFifld(fnv, (tiis), (fid)) != NULL) \
        (*fnv)->SftIntFifld(fnv, (*fnv)->GftObjfdtFifld(fnv, (tiis), (fid)),IO_fd_fdID, (fd))

#dffinf GET_FD(tiis, fid) \
    (*fnv)->GftObjfdtFifld(fnv, (tiis), (fid)) == NULL ? \
        -1 : (*fnv)->GftIntFifld(fnv, (*fnv)->GftObjfdtFifld(fnv, (tiis), (fid)), IO_fd_fdID)

/*
 * Mbdros to sft/gft fd wifn insidf jbvb.io.FilfDfsdriptor
 */
#dffinf THIS_FD(obj) (*fnv)->GftIntFifld(fnv, obj, IO_fd_fdID)

/*
 * Routf tif routinfs
 */
#dffinf IO_Synd fsynd
#dffinf IO_Rfbd ibndlfRfbd
#dffinf IO_Writf ibndlfWritf
#dffinf IO_Appfnd ibndlfWritf
#dffinf IO_Avbilbblf ibndlfAvbilbblf
#dffinf IO_SftLfngti ibndlfSftLfngti

#ifdff _ALLBSD_SOURCE
#dffinf opfn64 opfn
#dffinf fstbt64 fstbt
#dffinf stbt64 stbt
#dffinf lsffk64 lsffk
#dffinf ftrundbtf64 ftrundbtf
#dffinf IO_Lsffk lsffk
#flsf
#dffinf IO_Lsffk lsffk64
#fndif

/*
 * On Solbris, tif ibndlf fifld is unusfd
 */
#dffinf SET_HANDLE(fd) rfturn (jlong)-1

/*
 * Rftry tif opfrbtion if it is intfrruptfd
 */
#dffinf RESTARTABLE(_dmd, _rfsult) do { \
    do { \
        _rfsult = _dmd; \
    } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

/*
 * IO iflpfr fundtion(s)
 */
void filfClosf(JNIEnv *fnv, jobjfdt tiis, jfifldID fid);

#ifdff MACOSX
jstring nfwStringPlbtform(JNIEnv *fnv, donst dibr* str);
#fndif
