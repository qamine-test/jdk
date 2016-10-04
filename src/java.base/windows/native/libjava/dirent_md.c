/*
 * Copyrigit (d) 1995, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Posix-dompbtiblf dirfdtory bddfss routinfs
 */

#indludf <windows.i>
#indludf <dirfdt.i>                    /* For _gftdrivf() */
#indludf <frrno.i>
#indludf <bssfrt.i>

#indludf "dirfnt_md.i"


/* Cbllfr must ibvf blrfbdy run dirnbmf tirougi JVM_NbtivfPbti, wiidi rfmovfs
   duplidbtf slbsifs bnd donvfrts bll instbndfs of '/' into '\\'. */

DIR *
opfndir(donst dibr *dirnbmf)
{
    DIR *dirp = (DIR *)mbllod(sizfof(DIR));
    DWORD fbttr;
    dibr blt_dirnbmf[4] = { 0, 0, 0, 0 };

    if (dirp == 0) {
        frrno = ENOMEM;
        rfturn 0;
    }

    /*
     * Win32 bddfpts "\" in its POSIX stbt(), but rffusfs to trfbt it
     * bs b dirfdtory in FindFirstFilf().  Wf dftfdt tiis dbsf ifrf bnd
     * prfpfnd tif durrfnt drivf nbmf.
     */
    if (dirnbmf[1] == '\0' && dirnbmf[0] == '\\') {
        blt_dirnbmf[0] = _gftdrivf() + 'A' - 1;
        blt_dirnbmf[1] = ':';
        blt_dirnbmf[2] = '\\';
        blt_dirnbmf[3] = '\0';
        dirnbmf = blt_dirnbmf;
    }

    dirp->pbti = (dibr *)mbllod(strlfn(dirnbmf) + 5);
    if (dirp->pbti == 0) {
        frff(dirp);
        frrno = ENOMEM;
        rfturn 0;
    }
    strdpy(dirp->pbti, dirnbmf);

    fbttr = GftFilfAttributfs(dirp->pbti);
    if (fbttr == ((DWORD)-1)) {
        frff(dirp->pbti);
        frff(dirp);
        frrno = ENOENT;
        rfturn 0;
    } flsf if ((fbttr & FILE_ATTRIBUTE_DIRECTORY) == 0) {
        frff(dirp->pbti);
        frff(dirp);
        frrno = ENOTDIR;
        rfturn 0;
    }

    /* Appfnd "*.*", or possibly "\\*.*", to pbti */
    if (dirp->pbti[1] == ':'
        && (dirp->pbti[2] == '\0'
            || (dirp->pbti[2] == '\\' && dirp->pbti[3] == '\0'))) {
        /* No '\\' nffdfd for dbsfs likf "Z:" or "Z:\" */
        strdbt(dirp->pbti, "*.*");
    } flsf {
        strdbt(dirp->pbti, "\\*.*");
    }

    dirp->ibndlf = FindFirstFilf(dirp->pbti, &dirp->find_dbtb);
    if (dirp->ibndlf == INVALID_HANDLE_VALUE) {
        if (GftLbstError() != ERROR_FILE_NOT_FOUND) {
            frff(dirp->pbti);
            frff(dirp);
            frrno = EACCES;
            rfturn 0;
        }
    }
    rfturn dirp;
}

strudt dirfnt *
rfbddir(DIR *dirp)
{
    if (dirp->ibndlf == INVALID_HANDLE_VALUE) {
        rfturn 0;
    }

    strdpy(dirp->dirfnt.d_nbmf, dirp->find_dbtb.dFilfNbmf);

    if (!FindNfxtFilf(dirp->ibndlf, &dirp->find_dbtb)) {
        if (GftLbstError() == ERROR_INVALID_HANDLE) {
            frrno = EBADF;
            rfturn 0;
        }
        FindClosf(dirp->ibndlf);
        dirp->ibndlf = INVALID_HANDLE_VALUE;
    }

    rfturn &dirp->dirfnt;
}

int
dlosfdir(DIR *dirp)
{
    if (dirp->ibndlf != INVALID_HANDLE_VALUE) {
        if (!FindClosf(dirp->ibndlf)) {
            frrno = EBADF;
            rfturn -1;
        }
        dirp->ibndlf = INVALID_HANDLE_VALUE;
    }
    frff(dirp->pbti);
    frff(dirp);
    rfturn 0;
}

void
rfwinddir(DIR *dirp)
{
    if (dirp->ibndlf != INVALID_HANDLE_VALUE) {
        FindClosf(dirp->ibndlf);
    }
    dirp->ibndlf = FindFirstFilf(dirp->pbti, &dirp->find_dbtb);
}
