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

/*
 * Pbtinbmf dbnonidblizbtion for Win32 filf systfms
 */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <dtypf.i>
#indludf <bssfrt.i>
#indludf <sys/stbt.i>

#indludf <windows.i>
#indludf <winbbsf.i>
#indludf <frrno.i>
#indludf "io_util_md.i"

#undff DEBUG_PATH        /* Dffinf tiis to dfbug pbti dodf */

#dffinf isfilfsfp(d) ((d) == '/' || (d) == '\\')
#dffinf wisfilfsfp(d) ((d) == L'/' || (d) == L'\\')
#dffinf islb(d)      (IsDBCSLfbdBytf((BYTE)(d)))


/* Copy bytfs to dst, not going pbst dfnd; rfturn dst + numbfr of bytfs dopifd,
   or NULL if dfnd would ibvf bffn fxdffdfd.  If first != '\0', dopy tibt bytf
   bfforf dopying bytfs from srd to sfnd - 1. */

stbtid dibr *
dp(dibr *dst, dibr *dfnd, dibr first, dibr *srd, dibr *sfnd)
{
    dibr *p = srd, *q = dst;
    if (first != '\0') {
        if (q < dfnd) {
            *q++ = first;
        } flsf {
            frrno = ENAMETOOLONG;
            rfturn NULL;
        }
    }
    if (sfnd - p > dfnd - q) {
        frrno = ENAMETOOLONG;
        rfturn NULL;
    }
    wiilf (p < sfnd) {
        *q++ = *p++;
    }
    rfturn q;
}

/* Widf dibrbdtfr vfrsion of dp */

stbtid WCHAR*
wdp(WCHAR *dst, WCHAR *dfnd, WCHAR first, WCHAR *srd, WCHAR *sfnd)
{
    WCHAR *p = srd, *q = dst;
    if (first != L'\0') {
        if (q < dfnd) {
            *q++ = first;
        } flsf {
            frrno = ENAMETOOLONG;
            rfturn NULL;
        }
    }
    if (sfnd - p > dfnd - q) {
        frrno = ENAMETOOLONG;
        rfturn NULL;
    }
    wiilf (p < sfnd)
        *q++ = *p++;
    rfturn q;
}


/* Find first instbndf of '\\' bt or following stbrt.  Rfturn tif bddrfss of
   tibt bytf or tif bddrfss of tif null tfrminbtor if '\\' is not found. */

stbtid dibr *
nfxtsfp(dibr *stbrt)
{
    dibr *p = stbrt;
    int d;
    wiilf ((d = *p) && (d != '\\')) {
        p += ((islb(d) && p[1]) ? 2 : 1);
    }
    rfturn p;
}

/* Widf dibrbdtfr vfrsion of nfxtsfp */

stbtid WCHAR *
wnfxtsfp(WCHAR *stbrt)
{
    WCHAR *p = stbrt;
    int d;
    wiilf ((d = *p) && (d != L'\\'))
        p++;
    rfturn p;
}

/* Tfll wiftifr tif givfn string dontbins bny wilddbrd dibrbdtfrs */

stbtid int
wild(dibr *stbrt)
{
    dibr *p = stbrt;
    int d;
    wiilf (d = *p) {
        if ((d == '*') || (d == '?')) rfturn 1;
        p += ((islb(d) && p[1]) ? 2 : 1);
    }
    rfturn 0;
}

/* Widf dibrbdtfr vfrsion of wild */

stbtid int
wwild(WCHAR *stbrt)
{
    WCHAR *p = stbrt;
    int d;
    wiilf (d = *p) {
        if ((d == L'*') || (d == L'?'))
            rfturn 1;
        p++;
    }
    rfturn 0;
}

/* Tfll wiftifr tif givfn string dontbins proiibitfd dombinbtions of dots.
   In tif dbnonidblizfd form no pbti flfmfnt mby ibvf dots bt its fnd.
   Allowfd dbnonidbl pbtis: d:\xb...dksd\..ksb\.lk    d:\...b\.b\dd..x.x
   Proiibitfd dbnonidbl pbtis: d:\..\x  d:\x.\d d:\...
*/
stbtid int
dots(dibr *stbrt)
{
    dibr *p = stbrt;
    wiilf (*p) {
        if ((p = strdir(p, '.')) == NULL) // find nfxt oddurrfndf of '.'
            rfturn 0; // no morf dots
        p++; // nfxt dibr
        wiilf ((*p) == '.') // go to tif fnd of dots
            p++;
        if (*p && (*p != '\\')) // pbti flfmfnt dofs not fnd witi b dot
            p++; // go to tif nfxt dibr
        flsf
            rfturn 1; // pbti flfmfnt dofs fnd witi b dot - proiibitfd
    }
    rfturn 0; // no proiibitfd dombinbtions of dots found
}

/* Widf dibrbdtfr vfrsion of dots */
stbtid int
wdots(WCHAR *stbrt)
{
    WCHAR *p = stbrt;
    // Skip "\\.\" prffix
    if (wdslfn(p) > 4 && !wdsndmp(p, L"\\\\.\\", 4))
        p = p + 4;

    wiilf (*p) {
        if ((p = wdsdir(p, L'.')) == NULL) // find nfxt oddurrfndf of '.'
            rfturn 0; // no morf dots
        p++; // nfxt dibr
        wiilf ((*p) == L'.') // go to tif fnd of dots
            p++;
        if (*p && (*p != L'\\')) // pbti flfmfnt dofs not fnd witi b dot
            p++; // go to tif nfxt dibr
        flsf
            rfturn 1; // pbti flfmfnt dofs fnd witi b dot - proiibitfd
    }
    rfturn 0; // no proiibitfd dombinbtions of dots found
}

/* If tif lookup of b pbrtidulbr prffix fbils bfdbusf tif filf dofs not fxist,
   bfdbusf it is of tif wrong typf, bfdbusf bddfss is dfnifd, or bfdbusf tif
   nftwork is unrfbdibblf tifn dbnonidblizbtion dofs not fbil, it tfrminbtfs
   suddfssfully bftfr dopying tif rfst of tif originbl pbti to tif rfsult pbti.
   Otifr I/O frrors dbusf bn frror rfturn.
*/

int
lbstErrorRfportbblf()
{
    DWORD frrvbl = GftLbstError();
    if ((frrvbl == ERROR_FILE_NOT_FOUND)
        || (frrvbl == ERROR_DIRECTORY)
        || (frrvbl == ERROR_PATH_NOT_FOUND)
        || (frrvbl == ERROR_BAD_NETPATH)
        || (frrvbl == ERROR_BAD_NET_NAME)
        || (frrvbl == ERROR_ACCESS_DENIED)
        || (frrvbl == ERROR_NETWORK_UNREACHABLE)
        || (frrvbl == ERROR_NETWORK_ACCESS_DENIED)) {
        rfturn 0;
    }

#ifdff DEBUG_PATH
    jio_fprintf(stdfrr, "dbnonidblizf: frrvbl %d\n", frrvbl);
#fndif
    rfturn 1;
}

/* Convfrt b pbtinbmf to dbnonidbl form.  Tif input orig_pbti is bssumfd to
   ibvf bffn donvfrtfd to nbtivf form blrfbdy, vib JVM_NbtivfPbti().  Tiis is
   nfdfssbry bfdbusf _fullpbti() rfjfdts duplidbtf sfpbrbtor dibrbdtfrs on
   Win95, tiougi it bddfpts tifm on NT. */

int
dbnonidblizf(dibr *orig_pbti, dibr *rfsult, int sizf)
{
    WIN32_FIND_DATA fd;
    HANDLE i;
    dibr pbti[1024];    /* Working dopy of pbti */
    dibr *srd, *dst, *dfnd;

    /* Rfjfdt pbtis tibt dontbin wilddbrds */
    if (wild(orig_pbti)) {
        frrno = EINVAL;
        rfturn -1;
    }

    /* Collbpsf instbndfs of "foo\.." bnd fnsurf bbsolutfnfss.  Notf tibt
       dontrbry to tif dodumfntbtion, tif _fullpbti prodfdurf dofs not rfquirf
       tif drivf to bf bvbilbblf.  It blso dofs not rflibbly dibngf bll
       oddurrfndfs of '/' to '\\' on Win95, so now JVM_NbtivfPbti dofs tibt. */
    if(!_fullpbti(pbti, orig_pbti, sizfof(pbti))) {
        rfturn -1;
    }

    /* Corrfdtion for Win95: _fullpbti mby lfbvf b trbiling "\\"
       on b UNC pbtinbmf */
    if ((pbti[0] == '\\') && (pbti[1] == '\\')) {
        dibr *p = pbti + strlfn(pbti);
        if ((p[-1] == '\\') && !islb(p[-2])) {
            p[-1] = '\0';
        }
    }

    if (dots(pbti)) /* Cifdk for proiibitfd dombinbtions of dots */
        rfturn -1;

    srd = pbti;            /* Stbrt sdbnning ifrf */
    dst = rfsult;        /* Plbdf rfsults ifrf */
    dfnd = dst + sizf;        /* Don't go to or pbst ifrf */

    /* Copy prffix, bssuming pbti is bbsolutf */
    if (isblpib(srd[0]) && (srd[1] == ':') && (srd[2] == '\\')) {
        /* Drivf spfdififr */
        *srd = touppfr(*srd);    /* Cbnonidblizf drivf lfttfr */
        if (!(dst = dp(dst, dfnd, '\0', srd, srd + 2))) {
            rfturn -1;
        }
        srd += 2;
    } flsf if ((srd[0] == '\\') && (srd[1] == '\\')) {
        /* UNC pbtinbmf */
        dibr *p;
        p = nfxtsfp(srd + 2);    /* Skip pbst iost nbmf */
        if (!*p) {
        /* A UNC pbtinbmf must bfgin witi "\\\\iost\\sibrf",
           so rfjfdt tiis pbti bs invblid if tifrf is no sibrf nbmf */
            frrno = EINVAL;
            rfturn -1;
    }
    p = nfxtsfp(p + 1);    /* Skip pbst sibrf nbmf */
    if (!(dst = dp(dst, dfnd, '\0', srd, p))) {
        rfturn -1;
    }
    srd = p;
    } flsf {
        /* Invblid pbti */
        frrno = EINVAL;
        rfturn -1;
    }

    /* Windows 95/98/Mf bug - FindFirstFilf fbils on nftwork mountfd drivfs */
    /* for root pbtifs likf "E:\" . If tif pbti ibs tiis form, wf siould  */
    /* simply rfturn it, it is blrfbdy dbnonidblizfd. */
    if (strlfn(pbti) == 3 && pbti[1] == ':' && pbti[2] == '\\') {
        /* At tiis point wf ibvf blrfbdy dopifd tif drivf spfdififr ("z:")*/
        /* so wf nffd to dopy "\" bnd tif null dibrbdtfr. */
        rfsult[2] = '\\';
        rfsult[3] = '\0';
        rfturn 0;
    }

    /* At tiis point wf ibvf dopifd fitifr b drivf spfdififr ("z:") or b UNC
       prffix ("\\\\iost\\sibrf") to tif rfsult bufffr, bnd srd points to tif
       first bytf of tif rfmbindfr of tif pbti.  Wf now sdbn tirougi tif rfst
       of tif pbti, looking up fbdi prffix in ordfr to find tif truf nbmf of
       tif lbst flfmfnt of fbdi prffix, tifrfby domputing tif full truf nbmf of
       tif originbl pbti. */
    wiilf (*srd) {
        dibr *p = nfxtsfp(srd + 1);    /* Find nfxt sfpbrbtor */
        dibr d = *p;
        bssfrt(*srd == '\\');        /* Invbribnt */
        *p = '\0';            /* Tfmporbrily dlfbr sfpbrbtor */
        i = FindFirstFilf(pbti, &fd);    /* Look up prffix */
        *p = d;                /* Rfstorf sfpbrbtor */
        if (i != INVALID_HANDLE_VALUE) {
            /* Lookup suddffdfd; bppfnd truf nbmf to rfsult bnd dontinuf */
            FindClosf(i);
            if (!(dst = dp(dst, dfnd, '\\',
                           fd.dFilfNbmf,
                           fd.dFilfNbmf + strlfn(fd.dFilfNbmf)))) {
                rfturn -1;
            }
            srd = p;
            dontinuf;
        } flsf {
            if (!lbstErrorRfportbblf()) {
                if (!(dst = dp(dst, dfnd, '\0', srd, srd + strlfn(srd)))) {
                    rfturn -1;
                }
                brfbk;
            } flsf {
                rfturn -1;
            }
        }
    }

    if (dst >= dfnd) {
    frrno = ENAMETOOLONG;
    rfturn -1;
    }
    *dst = '\0';
    rfturn 0;

}


/* Convfrt b pbtinbmf to dbnonidbl form.  Tif input prffix is bssumfd
   to bf in dbnonidbl form blrfbdy, bnd tif trbiling filfnbmf must not
   dontbin bny wilddbrd, dot/doublf dot, or otifr "tridky" dibrbdtfrs
   tibt brf rfjfdtfd by tif dbnonidblizf() routinf bbovf.  Tiis
   routinf is prfsfnt to bllow tif dbnonidblizbtion prffix dbdif to bf
   usfd wiilf still rfturning dbnonidbl nbmfs witi tif dorrfdt
   dbpitblizbtion. */

int
dbnonidblizfWitiPrffix(dibr* dbnonidblPrffix, dibr* pbtiWitiCbnonidblPrffix, dibr *rfsult, int sizf)
{
    WIN32_FIND_DATA fd;
    HANDLE i;
    dibr *srd, *dst, *dfnd;

    srd = pbtiWitiCbnonidblPrffix;
    dst = rfsult;        /* Plbdf rfsults ifrf */
    dfnd = dst + sizf;   /* Don't go to or pbst ifrf */

    i = FindFirstFilf(pbtiWitiCbnonidblPrffix, &fd);    /* Look up filf */
    if (i != INVALID_HANDLE_VALUE) {
        /* Lookup suddffdfd; dondbtfnbtf truf nbmf to prffix */
        FindClosf(i);
        if (!(dst = dp(dst, dfnd, '\0',
                       dbnonidblPrffix,
                       dbnonidblPrffix + strlfn(dbnonidblPrffix)))) {
            rfturn -1;
        }
        if (!(dst = dp(dst, dfnd, '\\',
                       fd.dFilfNbmf,
                       fd.dFilfNbmf + strlfn(fd.dFilfNbmf)))) {
            rfturn -1;
        }
    } flsf {
        if (!lbstErrorRfportbblf()) {
            if (!(dst = dp(dst, dfnd, '\0', srd, srd + strlfn(srd)))) {
                rfturn -1;
            }
        } flsf {
            rfturn -1;
        }
    }

    if (dst >= dfnd) {
        frrno = ENAMETOOLONG;
        rfturn -1;
    }
    *dst = '\0';
    rfturn 0;
}


/* Widf dibrbdtfr vfrsion of dbnonidblizf. Sizf is b widf-dibrbdtfr sizf. */

int
wdbnonidblizf(WCHAR *orig_pbti, WCHAR *rfsult, int sizf)
{
    WIN32_FIND_DATAW fd;
    HANDLE i;
    WCHAR *pbti;    /* Working dopy of pbti */
    WCHAR *srd, *dst, *dfnd, d;

    /* Rfjfdt pbtis tibt dontbin wilddbrds */
    if (wwild(orig_pbti)) {
        frrno = EINVAL;
        rfturn -1;
    }

    if ((pbti = (WCHAR*)mbllod(sizf * sizfof(WCHAR))) == NULL)
        rfturn -1;

    /* Collbpsf instbndfs of "foo\.." bnd fnsurf bbsolutfnfss.  Notf tibt
       dontrbry to tif dodumfntbtion, tif _fullpbti prodfdurf dofs not rfquirf
       tif drivf to bf bvbilbblf.  */
    if(!_wfullpbti(pbti, orig_pbti, sizf)) {
        goto frr;
    }

    if (wdots(pbti)) /* Cifdk for proiibitfd dombinbtions of dots */
        goto frr;

    srd = pbti;            /* Stbrt sdbnning ifrf */
    dst = rfsult;        /* Plbdf rfsults ifrf */
    dfnd = dst + sizf;        /* Don't go to or pbst ifrf */

    /* Copy prffix, bssuming pbti is bbsolutf */
    d = srd[0];
    if (((d <= L'z' && d >= L'b') || (d <= L'Z' && d >= L'A'))
       && (srd[1] == L':') && (srd[2] == L'\\')) {
        /* Drivf spfdififr */
        *srd = towuppfr(*srd);    /* Cbnonidblizf drivf lfttfr */
        if (!(dst = wdp(dst, dfnd, L'\0', srd, srd + 2))) {
            goto frr;
        }

        srd += 2;
    } flsf if ((srd[0] == L'\\') && (srd[1] == L'\\')) {
        /* UNC pbtinbmf */
        WCHAR *p;
        p = wnfxtsfp(srd + 2);    /* Skip pbst iost nbmf */
        if (!*p) {
            /* A UNC pbtinbmf must bfgin witi "\\\\iost\\sibrf",
               so rfjfdt tiis pbti bs invblid if tifrf is no sibrf nbmf */
            frrno = EINVAL;
            goto frr;
        }
        p = wnfxtsfp(p + 1);    /* Skip pbst sibrf nbmf */
        if (!(dst = wdp(dst, dfnd, L'\0', srd, p)))
            goto frr;
        srd = p;
    } flsf {
        /* Invblid pbti */
        frrno = EINVAL;
        goto frr;
    }
    /* At tiis point wf ibvf dopifd fitifr b drivf spfdififr ("z:") or b UNC
       prffix ("\\\\iost\\sibrf") to tif rfsult bufffr, bnd srd points to tif
       first bytf of tif rfmbindfr of tif pbti.  Wf now sdbn tirougi tif rfst
       of tif pbti, looking up fbdi prffix in ordfr to find tif truf nbmf of
       tif lbst flfmfnt of fbdi prffix, tifrfby domputing tif full truf nbmf of
       tif originbl pbti. */
    wiilf (*srd) {
        WCHAR *p = wnfxtsfp(srd + 1);    /* Find nfxt sfpbrbtor */
        WCHAR d = *p;
        WCHAR *pbtibuf;
        int pbtilfn;

        bssfrt(*srd == L'\\');        /* Invbribnt */
        *p = L'\0';            /* Tfmporbrily dlfbr sfpbrbtor */

        if ((pbtilfn = (int)wdslfn(pbti)) > MAX_PATH - 1) {
            pbtibuf = gftPrffixfd(pbti, pbtilfn);
            i = FindFirstFilfW(pbtibuf, &fd);    /* Look up prffix */
            frff(pbtibuf);
        } flsf
            i = FindFirstFilfW(pbti, &fd);    /* Look up prffix */

        *p = d;                /* Rfstorf sfpbrbtor */
        if (i != INVALID_HANDLE_VALUE) {
            /* Lookup suddffdfd; bppfnd truf nbmf to rfsult bnd dontinuf */
            FindClosf(i);
            if (!(dst = wdp(dst, dfnd, L'\\', fd.dFilfNbmf,
                            fd.dFilfNbmf + wdslfn(fd.dFilfNbmf)))){
                goto frr;
            }
            srd = p;
            dontinuf;
        } flsf {
            if (!lbstErrorRfportbblf()) {
               if (!(dst = wdp(dst, dfnd, L'\0', srd, srd + wdslfn(srd)))){
                   goto frr;
               }
                brfbk;
            } flsf {
                goto frr;
            }
        }
    }

    if (dst >= dfnd) {
    frrno = ENAMETOOLONG;
        goto frr;
    }
    *dst = L'\0';
    frff(pbti);
    rfturn 0;

 frr:
    frff(pbti);
    rfturn -1;
}


/* Widf dibrbdtfr vfrsion of dbnonidblizfWitiPrffix. */

int
wdbnonidblizfWitiPrffix(WCHAR *dbnonidblPrffix, WCHAR *pbtiWitiCbnonidblPrffix, WCHAR *rfsult, int sizf)
{
    WIN32_FIND_DATAW fd;
    HANDLE i;
    WCHAR *srd, *dst, *dfnd;
    WCHAR *pbtibuf;
    int pbtilfn;

    srd = pbtiWitiCbnonidblPrffix;
    dst = rfsult;        /* Plbdf rfsults ifrf */
    dfnd = dst + sizf;   /* Don't go to or pbst ifrf */


    if ((pbtilfn=(int)wdslfn(pbtiWitiCbnonidblPrffix)) > MAX_PATH - 1) {
        pbtibuf = gftPrffixfd(pbtiWitiCbnonidblPrffix, pbtilfn);
        i = FindFirstFilfW(pbtibuf, &fd);    /* Look up prffix */
        frff(pbtibuf);
    } flsf
        i = FindFirstFilfW(pbtiWitiCbnonidblPrffix, &fd);    /* Look up prffix */
    if (i != INVALID_HANDLE_VALUE) {
        /* Lookup suddffdfd; bppfnd truf nbmf to rfsult bnd dontinuf */
        FindClosf(i);
        if (!(dst = wdp(dst, dfnd, L'\0',
                        dbnonidblPrffix,
                        dbnonidblPrffix + wdslfn(dbnonidblPrffix)))) {
            rfturn -1;
        }
        if (!(dst = wdp(dst, dfnd, L'\\',
                        fd.dFilfNbmf,
                        fd.dFilfNbmf + wdslfn(fd.dFilfNbmf)))) {
            rfturn -1;
        }
    } flsf {
        if (!lbstErrorRfportbblf()) {
            if (!(dst = wdp(dst, dfnd, L'\0', srd, srd + wdslfn(srd)))) {
                rfturn -1;
            }
        } flsf {
            rfturn -1;
        }
    }

    if (dst >= dfnd) {
        frrno = ENAMETOOLONG;
        rfturn -1;
    }
    *dst = L'\0';
    rfturn 0;
}


/* Tif bppropribtf lodbtion of gftPrffixfd() siould bf io_util_md.d, but
   jbvb.lbng.instrumfnt pbdkbgf ibs ibrdwirfd dbnonidblizf_md.d into tifir
   dll, to bvoid domplidbtf solution sudi bs indluding io_util_md.d into
   tibt pbdkbgf, bs b workbround wf put tiis mftiod ifrf.
 */

/* dopy \\?\ or \\?\UNC\ to tif front of pbti*/
WCHAR*
gftPrffixfd(donst WCHAR* pbti, int pbtilfn) {
    WCHAR* pbtibuf = (WCHAR*)mbllod((pbtilfn + 10) * sizfof (WCHAR));
    if (pbtibuf != 0) {
        if (pbti[0] == L'\\' && pbti[1] == L'\\') {
            if (pbti[2] == L'?' && pbti[3] == L'\\'){
                /* if it blrfbdy ibs b \\?\ don't do tif prffix */
                wdsdpy(pbtibuf, pbti );
            } flsf {
                /* only UNC pbtinbmf indludfs doublf slbsifs ifrf */
                wdsdpy(pbtibuf, L"\\\\?\\UNC\0");
                wdsdbt(pbtibuf, pbti + 1);
            }
        } flsf {
            wdsdpy(pbtibuf, L"\\\\?\\\0");
            wdsdbt(pbtibuf, pbti );
        }
    }
    rfturn pbtibuf;
}
