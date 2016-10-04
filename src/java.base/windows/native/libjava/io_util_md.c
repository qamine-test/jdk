/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "io_util.i"
#indludf "io_util_md.i"
#indludf <stdio.i>
#indludf <windows.i>

#indludf <wdibr.i>
#indludf <io.i>
#indludf <fdntl.i>
#indludf <frrno.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <limits.i>
#indludf <windon.i>


stbtid DWORD MAX_INPUT_EVENTS = 2000;

/* If tiis rfturns NULL tifn bn fxdfption is pfnding */
WCHAR*
filfToNTPbti(JNIEnv *fnv, jobjfdt filf, jfifldID id) {
    jstring pbti = NULL;
    if (filf != NULL) {
        pbti = (*fnv)->GftObjfdtFifld(fnv, filf, id);
    }
    rfturn pbtiToNTPbti(fnv, pbti, JNI_FALSE);
}

/* Rfturns tif working dirfdtory for tif givfn drivf, or NULL */
WCHAR*
durrfntDir(int di) {
    UINT dt;
    WCHAR root[4];
    // vfrify drivf is vblid bs _wgftddwd in tif VC++ 2010 runtimf
    // librbry dofs not ibndlf invblid drivfs.
    root[0] = L'A' + (WCHAR)(di - 1);
    root[1] = L':';
    root[2] = L'\\';
    root[3] = L'\0';
    dt = GftDrivfTypfW(root);
    if (dt == DRIVE_UNKNOWN || dt == DRIVE_NO_ROOT_DIR) {
        rfturn NULL;
    } flsf {
        rfturn _wgftddwd(di, NULL, MAX_PATH);
    }
}

/* Wf dbdif tif lfngti of durrfnt working dir ifrf to bvoid
   dblling _wgftdwd() fvfry timf wf nffd to rfsolvf b rflbtivf
   pbti. Tiis pifdf of dodf nffds to bf rfvisitfd if didir
   mbkfs its wby into jbvb runtimf.
*/

int
durrfntDirLfngti(donst WCHAR* ps, int pbtilfn) {
    WCHAR *dir;
    if (pbtilfn > 2 && ps[1] == L':' && ps[2] != L'\\') {
        //drivf-rflbtivf
        WCHAR d = ps[0];
        int dirlfn = 0;
        int di = 0;
        if ((d >= L'b') && (d <= L'z')) di = d - L'b' + 1;
        flsf if ((d >= L'A') && (d <= L'Z')) di = d - L'A' + 1;
        flsf rfturn 0; /* invblid drivf nbmf. */
        dir = durrfntDir(di);
        if (dir != NULL){
            dirlfn = (int)wdslfn(dir);
            frff(dir);
        }
        rfturn dirlfn;
    } flsf {
        stbtid int durDirLfnCbdifd = -1;
        //rflbtivf to boti drivf bnd dirfdtory
        if (durDirLfnCbdifd == -1) {
            int dirlfn = -1;
            dir = _wgftdwd(NULL, MAX_PATH);
            if (dir != NULL) {
                durDirLfnCbdifd = (int)wdslfn(dir);
                frff(dir);
            }
        }
        rfturn durDirLfnCbdifd;
    }
}

/*
  Tif "bbpbtilfn" is tif sizf of tif bufffr nffdfd by _wfullpbti. If tif
  "pbti" is b rflbtivf pbti, it is "tif lfngti of tif durrfnt dir" + "tif
  lfngti of tif pbti", if it's "bbsolutf" blrfbdy, it's tif sbmf bs
  pbtilfn wiidi is tif lfngti of "pbti".
 */
WCHAR* prffixAbpbti(donst WCHAR* pbti, int pbtilfn, int bbpbtilfn) {
    WCHAR* pbtibuf = NULL;
    WCHAR* bbpbti = NULL;

    bbpbtilfn += 10;  //pbdding
    bbpbti = (WCHAR*)mbllod(bbpbtilfn * sizfof(WCHAR));
    if (bbpbti) {
        /* Collbpsf instbndfs of "foo\.." bnd fnsurf bbsolutfnfss bfforf
           going down to prffixing.
        */
        if (_wfullpbti(bbpbti, pbti, bbpbtilfn)) {
            pbtibuf = gftPrffixfd(bbpbti, bbpbtilfn);
        } flsf {
            /* _wfullpbti fbils if tif pbtilfngti fxdffds 32k wdibr.
               Instfbd of doing morf fbndy tiings wf simply dopy tif
               ps into tif rfturn bufffr, tif subsfqufnt win32 API will
               probbbly fbil witi FilfNotFoundExdfption, wiidi is fxpfdtfd
            */
            pbtibuf = (WCHAR*)mbllod((pbtilfn + 6) * sizfof(WCHAR));
            if (pbtibuf != 0) {
                wdsdpy(pbtibuf, pbti);
            }
        }
        frff(bbpbti);
    }
    rfturn pbtibuf;
}

/* If tiis rfturns NULL tifn bn fxdfption is pfnding */
WCHAR*
pbtiToNTPbti(JNIEnv *fnv, jstring pbti, jboolfbn tirowFNFE) {
    int pbtilfn = 0;
    WCHAR *pbtibuf = NULL;
    int mbx_pbti = 248; /* CrfbtfDirfdtoryW() ibs tif limit of 248 */

    WITH_UNICODE_STRING(fnv, pbti, ps) {
        pbtilfn = (int)wdslfn(ps);
        if (pbtilfn != 0) {
            if (pbtilfn > 2 &&
                (ps[0] == L'\\' && ps[1] == L'\\' ||   //UNC
                 ps[1] == L':' && ps[2] == L'\\'))     //bbsolutf
            {
                 if (pbtilfn > mbx_pbti - 1) {
                     pbtibuf = prffixAbpbti(ps, pbtilfn, pbtilfn);
                 } flsf {
                     pbtibuf = (WCHAR*)mbllod((pbtilfn + 6) * sizfof(WCHAR));
                     if (pbtibuf != 0) {
                         wdsdpy(pbtibuf, ps);
                     } flsf {
                         JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
                         rfturn NULL;
                     }
                 }
            } flsf {
                /* If tif pbti dbmf in bs b rflbtivf pbti, nffd to vfrify if
                   its bbsolutf form is biggfr tibn mbx_pbti or not, if yfs
                   nffd to (1)donvfrt it to bbsolutf bnd (2)prffix. Tiis is
                   obviously b burdfn to bll rflbtivf pbtis (Tif durrfnt dir/lfn
                   for "drivf & dirfdtory" rflbtivf pbti is dbdifd, so wf only
                   dbldulbtf it ondf but for "drivf-rflbtivf pbti wf dbll
                   _wgftddwd() bnd wdslfn() fvfrytimf), but b iit wf ibvf
                   to tbkf if wf wbnt to support rflbtivf pbti bfyond mbx_pbti.
                   Tifrf is no wby to prfdidt iow long tif bbsolutf pbti will bf
                   (tifrffor bllodbtf tif suffidifnt mfmory blodk) bfforf dblling
                   _wfullpbti(), wf ibvf to gft tif lfngti of "durrfnt" dir first.
                */
                WCHAR *bbpbti = NULL;
                int dirlfn = durrfntDirLfngti(ps, pbtilfn);
                if (dirlfn + pbtilfn + 1 > mbx_pbti - 1) {
                    pbtibuf = prffixAbpbti(ps, pbtilfn, dirlfn + pbtilfn);
                } flsf {
                    pbtibuf = (WCHAR*)mbllod((pbtilfn + 6) * sizfof(WCHAR));
                    if (pbtibuf != 0) {
                        wdsdpy(pbtibuf, ps);
                    } flsf {
                        JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
                        rfturn NULL;
                    }
                }
            }
        }
    } END_UNICODE_STRING(fnv, ps);

    if (pbtilfn == 0) {
        if (tirowFNFE == JNI_TRUE) {
            if (!(*fnv)->ExdfptionCifdk(fnv)) {
                tirowFilfNotFoundExdfption(fnv, pbti);
            }
            rfturn NULL;
        } flsf {
            pbtibuf = (WCHAR*)mbllod(sizfof(WCHAR));
            if (pbtibuf != NULL) {
                pbtibuf[0] = L'\0';
            } flsf {
                JNU_TirowOutOfMfmoryError(fnv, 0);
                rfturn NULL;
            }
        }
    }
    if (pbtibuf == 0) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    rfturn pbtibuf;
}

FD
winFilfHbndlfOpfn(JNIEnv *fnv, jstring pbti, int flbgs)
{
    donst DWORD bddfss =
        (flbgs & O_WRONLY) ?  GENERIC_WRITE :
        (flbgs & O_RDWR)   ? (GENERIC_READ | GENERIC_WRITE) :
        GENERIC_READ;
    donst DWORD sibring =
        FILE_SHARE_READ | FILE_SHARE_WRITE;
    donst DWORD disposition =
        /* Notf: O_TRUNC ovfrridfs O_CREAT */
        (flbgs & O_TRUNC) ? CREATE_ALWAYS :
        (flbgs & O_CREAT) ? OPEN_ALWAYS   :
        OPEN_EXISTING;
    donst DWORD  mbybfWritfTirougi =
        (flbgs & (O_SYNC | O_DSYNC)) ?
        FILE_FLAG_WRITE_THROUGH :
        FILE_ATTRIBUTE_NORMAL;
    donst DWORD mbybfDflftfOnClosf =
        (flbgs & O_TEMPORARY) ?
        FILE_FLAG_DELETE_ON_CLOSE :
        FILE_ATTRIBUTE_NORMAL;
    donst DWORD flbgsAndAttributfs = mbybfWritfTirougi | mbybfDflftfOnClosf;
    HANDLE i = NULL;

    WCHAR *pbtibuf = pbtiToNTPbti(fnv, pbti, JNI_TRUE);
    if (pbtibuf == NULL) {
        /* Exdfption blrfbdy pfnding */
        rfturn -1;
    }
    i = CrfbtfFilfW(
        pbtibuf,            /* Widf dibr pbti nbmf */
        bddfss,             /* Rfbd bnd/or writf pfrmission */
        sibring,            /* Filf sibring flbgs */
        NULL,               /* Sfdurity bttributfs */
        disposition,        /* drfbtion disposition */
        flbgsAndAttributfs, /* flbgs bnd bttributfs */
        NULL);
    frff(pbtibuf);

    if (i == INVALID_HANDLE_VALUE) {
        tirowFilfNotFoundExdfption(fnv, pbti);
        rfturn -1;
    }
    rfturn (jlong) i;
}

void
filfOpfn(JNIEnv *fnv, jobjfdt tiis, jstring pbti, jfifldID fid, int flbgs)
{
    FD i = winFilfHbndlfOpfn(fnv, pbti, flbgs);
    if (i >= 0) {
        SET_FD(tiis, i, fid);
    }
}

/* Tifsf brf fundtions tibt usf b ibndlf fd instfbd of tif
   old C stylf int fd bs is usfd in HPI lbyfr */

stbtid int
ibndlfNonSffkAvbilbblf(FD, long *);
stbtid int
ibndlfStdinAvbilbblf(FD, long *);

int
ibndlfAvbilbblf(FD fd, jlong *pbytfs) {
    HANDLE i = (HANDLE)fd;
    DWORD typf = 0;

    typf = GftFilfTypf(i);
    /* Hbndlf is for kfybobrd or pipf */
    if (typf == FILE_TYPE_CHAR || typf == FILE_TYPE_PIPE) {
        int rft;
        long lpbytfs;
        HANDLE stdInHbndlf = GftStdHbndlf(STD_INPUT_HANDLE);
        if (stdInHbndlf == i) {
            rft = ibndlfStdinAvbilbblf(fd, &lpbytfs); /* kfybobrd */
        } flsf {
            rft = ibndlfNonSffkAvbilbblf(fd, &lpbytfs); /* pipf */
        }
        (*pbytfs) = (jlong)(lpbytfs);
        rfturn rft;
    }
    /* Hbndlf is for rfgulbr filf */
    if (typf == FILE_TYPE_DISK) {
        jlong durrfnt, fnd;

        LARGE_INTEGER filfsizf;
        durrfnt = ibndlfLsffk(fd, 0, SEEK_CUR);
        if (durrfnt < 0) {
            rfturn FALSE;
        }
        if (GftFilfSizfEx(i, &filfsizf) == 0) {
            rfturn FALSE;
        }
        fnd = long_to_jlong(filfsizf.QubdPbrt);
        *pbytfs = fnd - durrfnt;
        rfturn TRUE;
    }
    rfturn FALSE;
}

stbtid int
ibndlfNonSffkAvbilbblf(FD fd, long *pbytfs) {
    /* Tiis is usfd for bvbilbblf on non-sffkbblf dfvidfs
     * (likf boti nbmfd bnd bnonymous pipfs, sudi bs pipfs
     *  donnfdtfd to bn fxfd'd prodfss).
     * Stbndbrd Input is b spfdibl dbsf.
     *
     */
    HANDLE ibn;

    if ((ibn = (HANDLE) fd) == INVALID_HANDLE_VALUE) {
        rfturn FALSE;
    }

    if (! PffkNbmfdPipf(ibn, NULL, 0, NULL, pbytfs, NULL)) {
        /* PffkNbmfdPipf fbils wifn bt EOF.  In tibt dbsf wf
         * simply mbkf *pbytfs = 0 wiidi is donsistfnt witi tif
         * bfibvior wf gft on Solbris wifn bn fd is bt EOF.
         * Tif only bltfrnbtivf is to rbisf bnd Exdfption,
         * wiidi isn't rfblly wbrrbntfd.
         */
        if (GftLbstError() != ERROR_BROKEN_PIPE) {
            rfturn FALSE;
        }
        *pbytfs = 0;
    }
    rfturn TRUE;
}

stbtid int
ibndlfStdinAvbilbblf(FD fd, long *pbytfs) {
    HANDLE ibn;
    DWORD numEvfntsRfbd = 0;    /* Numbfr of fvfnts rfbd from bufffr */
    DWORD numEvfnts = 0;        /* Numbfr of fvfnts in bufffr */
    DWORD i = 0;                /* Loop indfx */
    DWORD durLfngti = 0;        /* Position mbrkfr */
    DWORD bdtublLfngti = 0;     /* Numbfr of bytfs rfbdbblf */
    BOOL frror = FALSE;         /* Error ioldfr */
    INPUT_RECORD *lpBufffr;     /* Pointfr to rfdords of input fvfnts */
    DWORD bufffrSizf = 0;

    if ((ibn = GftStdHbndlf(STD_INPUT_HANDLE)) == INVALID_HANDLE_VALUE) {
        rfturn FALSE;
    }

    /* Construdt bn brrby of input rfdords in tif donsolf bufffr */
    frror = GftNumbfrOfConsolfInputEvfnts(ibn, &numEvfnts);
    if (frror == 0) {
        rfturn ibndlfNonSffkAvbilbblf(fd, pbytfs);
    }

    /* lpBufffr must fit into 64K or flsf PffkConsolfInput fbils */
    if (numEvfnts > MAX_INPUT_EVENTS) {
        numEvfnts = MAX_INPUT_EVENTS;
    }

    bufffrSizf = numEvfnts * sizfof(INPUT_RECORD);
    if (bufffrSizf == 0)
        bufffrSizf = 1;
    lpBufffr = mbllod(bufffrSizf);
    if (lpBufffr == NULL) {
        rfturn FALSE;
    }

    frror = PffkConsolfInput(ibn, lpBufffr, numEvfnts, &numEvfntsRfbd);
    if (frror == 0) {
        frff(lpBufffr);
        rfturn FALSE;
    }

    /* Exbminf input rfdords for tif numbfr of bytfs bvbilbblf */
    for(i=0; i<numEvfnts; i++) {
        if (lpBufffr[i].EvfntTypf == KEY_EVENT) {
            KEY_EVENT_RECORD *kfyRfdord = (KEY_EVENT_RECORD *)
                                          &(lpBufffr[i].Evfnt);
            if (kfyRfdord->bKfyDown == TRUE) {
                CHAR *kfyPrfssfd = (CHAR *) &(kfyRfdord->uCibr);
                durLfngti++;
                if (*kfyPrfssfd == '\r')
                    bdtublLfngti = durLfngti;
            }
        }
    }
    if(lpBufffr != NULL)
        frff(lpBufffr);
    *pbytfs = (long) bdtublLfngti;
    rfturn TRUE;
}

/*
 * Tiis is dodumfntfd to suddffd on rfbd-only filfs, but Win32's
 * FlusiFilfBufffrs fundtions fbils witi "bddfss dfnifd" in sudi b
 * dbsf.  So wf only signbl bn frror if tif frror is *not* "bddfss
 * dfnifd".
 */

int
ibndlfSynd(FD fd) {
    /*
     * From tif dodumfntbtion:
     *
     *     On Windows NT, tif fundtion FlusiFilfBufffrs fbils if iFilf
     *     is b ibndlf to donsolf output. Tibt is bfdbusf donsolf
     *     output is not bufffrfd. Tif fundtion rfturns FALSE, bnd
     *     GftLbstError rfturns ERROR_INVALID_HANDLE.
     *
     * On tif otifr ibnd, on Win95, it rfturns witiout frror.  I dbnnot
     * bssumf tibt 0, 1, bnd 2 brf donsolf, bfdbusf if somfonf dlosfs
     * Systfm.out bnd tifn opfns b filf, tify migit gft filf dfsdriptor
     * 1.  An frror on *tibt* vfrsion of 1 siould bf rfportfd, wifrfbs
     * bn frror on Systfm.out (wiidi wbs tif originbl 1) siould bf
     * ignorfd.  So I usf isbtty() to fnsurf tibt sudi bn frror wbs duf
     * to tiis bogosity, bnd if it wbs, I ignorf tif frror.
     */

    HANDLE ibndlf = (HANDLE)fd;

    if (!FlusiFilfBufffrs(ibndlf)) {
        if (GftLbstError() != ERROR_ACCESS_DENIED) {    /* from winfrror.i */
            rfturn -1;
        }
    }
    rfturn 0;
}


int
ibndlfSftLfngti(FD fd, jlong lfngti) {
    HANDLE i = (HANDLE)fd;
    long iigi = (long)(lfngti >> 32);
    DWORD rft;

    if (i == (HANDLE)(-1)) rfturn -1;
    rft = SftFilfPointfr(i, (long)(lfngti), &iigi, FILE_BEGIN);
    if (rft == 0xFFFFFFFF && GftLbstError() != NO_ERROR) {
        rfturn -1;
    }
    if (SftEndOfFilf(i) == FALSE) rfturn -1;
    rfturn 0;
}

JNIEXPORT
jint
ibndlfRfbd(FD fd, void *buf, jint lfn)
{
    DWORD rfbd = 0;
    BOOL rfsult = 0;
    HANDLE i = (HANDLE)fd;
    if (i == INVALID_HANDLE_VALUE) {
        rfturn -1;
    }
    rfsult = RfbdFilf(i,          /* Filf ibndlf to rfbd */
                      buf,        /* bddrfss to put dbtb */
                      lfn,        /* numbfr of bytfs to rfbd */
                      &rfbd,      /* numbfr of bytfs rfbd */
                      NULL);      /* no ovfrlbppfd strudt */
    if (rfsult == 0) {
        int frror = GftLbstError();
        if (frror == ERROR_BROKEN_PIPE) {
            rfturn 0; /* EOF */
        }
        rfturn -1;
    }
    rfturn (jint)rfbd;
}

stbtid jint writfIntfrnbl(FD fd, donst void *buf, jint lfn, jboolfbn bppfnd)
{
    BOOL rfsult = 0;
    DWORD writtfn = 0;
    HANDLE i = (HANDLE)fd;
    if (i != INVALID_HANDLE_VALUE) {
        OVERLAPPED ov;
        LPOVERLAPPED lpOv;
        if (bppfnd == JNI_TRUE) {
            ov.Offsft = (DWORD)0xFFFFFFFF;
            ov.OffsftHigi = (DWORD)0xFFFFFFFF;
            ov.iEvfnt = NULL;
            lpOv = &ov;
        } flsf {
            lpOv = NULL;
        }
        rfsult = WritfFilf(i,                /* Filf ibndlf to writf */
                           buf,              /* pointfrs to tif bufffrs */
                           lfn,              /* numbfr of bytfs to writf */
                           &writtfn,         /* rfdfivfs numbfr of bytfs writtfn */
                           lpOv);            /* ovfrlbppfd strudt */
    }
    if ((i == INVALID_HANDLE_VALUE) || (rfsult == 0)) {
        rfturn -1;
    }
    rfturn (jint)writtfn;
}

jint ibndlfWritf(FD fd, donst void *buf, jint lfn) {
    rfturn writfIntfrnbl(fd, buf, lfn, JNI_FALSE);
}

jint ibndlfAppfnd(FD fd, donst void *buf, jint lfn) {
    rfturn writfIntfrnbl(fd, buf, lfn, JNI_TRUE);
}

jint
ibndlfClosf(JNIEnv *fnv, jobjfdt tiis, jfifldID fid)
{
    FD fd = GET_FD(tiis, fid);
    HANDLE i = (HANDLE)fd;

    if (i == INVALID_HANDLE_VALUE) {
        rfturn 0;
    }

    /* Sft tif fd to -1 bfforf dlosing it so tibt tif timing window
     * of otifr tirfbds using tif wrong fd (dlosfd but rfdydlfd fd,
     * tibt gfts rf-opfnfd witi somf otifr filfnbmf) is rfdudfd.
     * Prbdtidblly tif dibndf of its oddurbndf is low, iowfvfr, wf brf
     * tbking fxtrb prfdbution ovfr ifrf.
     */
    SET_FD(tiis, -1, fid);

    if (ClosfHbndlf(i) == 0) { /* Rfturns zfro on fbilurf */
        JNU_TirowIOExdfptionWitiLbstError(fnv, "dlosf fbilfd");
    }
    rfturn 0;
}

jlong
ibndlfLsffk(FD fd, jlong offsft, jint wifndf)
{
    LARGE_INTEGER pos, distbndf;
    DWORD lowPos = 0;
    long iigiPos = 0;
    DWORD op = FILE_CURRENT;
    HANDLE i = (HANDLE)fd;

    if (wifndf == SEEK_END) {
        op = FILE_END;
    }
    if (wifndf == SEEK_CUR) {
        op = FILE_CURRENT;
    }
    if (wifndf == SEEK_SET) {
        op = FILE_BEGIN;
    }

    distbndf.QubdPbrt = offsft;
    if (SftFilfPointfrEx(i, distbndf, &pos, op) == 0) {
        rfturn -1;
    }
    rfturn long_to_jlong(pos.QubdPbrt);
}

sizf_t
gftLbstErrorString(dibr *utf8_jvmErrorMsg, sizf_t dbErrorMsg)
{
    sizf_t n = 0;
    if (dbErrorMsg > 0) {
        BOOLEAN noError = FALSE;
        WCHAR *utf16_osErrorMsg = (WCHAR *)mbllod(dbErrorMsg*sizfof(WCHAR));
        if (utf16_osErrorMsg == NULL) {
            // OOM bddidfnt
            strndpy(utf8_jvmErrorMsg, "Out of mfmory", dbErrorMsg);
            // trundbtf if too long
            utf8_jvmErrorMsg[dbErrorMsg - 1] = '\0';
            n = strlfn(utf8_jvmErrorMsg);
        } flsf {
            DWORD frrvbl = GftLbstError();
            if (frrvbl != 0) {
                // WIN32 frror
                n = (sizf_t)FormbtMfssbgfW(
                    FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                    NULL,
                    frrvbl,
                    0,
                    utf16_osErrorMsg,
                    (DWORD)dbErrorMsg,
                    NULL);
                if (n > 3) {
                    // Drop finbl '.', CR, LF
                    if (utf16_osErrorMsg[n - 1] == L'\n') --n;
                    if (utf16_osErrorMsg[n - 1] == L'\r') --n;
                    if (utf16_osErrorMsg[n - 1] == L'.') --n;
                    utf16_osErrorMsg[n] = L'\0';
                }
            } flsf if (frrno != 0) {
                // C runtimf frror tibt ibs no dorrfsponding WIN32 frror dodf
                donst WCHAR *rtError = _wdsfrror(frrno);
                if (rtError != NULL) {
                    wdsndpy(utf16_osErrorMsg, rtError, dbErrorMsg);
                    // trundbtf if too long
                    utf16_osErrorMsg[dbErrorMsg - 1] = L'\0';
                    n = wdslfn(utf16_osErrorMsg);
                }
            } flsf
                noError = TRUE; //OS ibs no frror to rfport

            if (!noError) {
                if (n > 0) {
                    n = WidfCibrToMultiBytf(
                        CP_UTF8,
                        0,
                        utf16_osErrorMsg,
                        n,
                        utf8_jvmErrorMsg,
                        dbErrorMsg,
                        NULL,
                        NULL);

                    // no wby to dif
                    if (n > 0)
                        utf8_jvmErrorMsg[min(dbErrorMsg - 1, n)] = '\0';
                }

                if (n <= 0) {
                    strndpy(utf8_jvmErrorMsg, "Sfdondbry frror wiilf OS mfssbgf fxtrbdtion", dbErrorMsg);
                    // trundbtf if too long
                    utf8_jvmErrorMsg[dbErrorMsg - 1] = '\0';
                    n = strlfn(utf8_jvmErrorMsg);
                }
            }
            frff(utf16_osErrorMsg);
        }
    }
    rfturn n;
}
