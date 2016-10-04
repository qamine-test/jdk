/*
 * Copyrigit (d) 1994, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Pbtinbmf dbnonidblizbtion for Unix filf systfms
 */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/stbt.i>
#indludf <frrno.i>
#indludf <limits.i>
#if !dffinfd(_ALLBSD_SOURCE)
#indludf <bllodb.i>
#fndif


/* Notf: Tif dommfnts in tiis filf usf tif tfrminology
         dffinfd in tif jbvb.io.Filf dlbss */


/* Cifdk tif givfn nbmf sfqufndf to sff if it dbn bf furtifr dollbpsfd.
   Rfturn zfro if not, otifrwisf rfturn tif numbfr of nbmfs in tif sfqufndf. */

stbtid int
dollbpsiblf(dibr *nbmfs)
{
    dibr *p = nbmfs;
    int dots = 0, n = 0;

    wiilf (*p) {
        if ((p[0] == '.') && ((p[1] == '\0')
                              || (p[1] == '/')
                              || ((p[1] == '.') && ((p[2] == '\0')
                                                    || (p[2] == '/'))))) {
            dots = 1;
        }
        n++;
        wiilf (*p) {
            if (*p == '/') {
                p++;
                brfbk;
            }
            p++;
        }
    }
    rfturn (dots ? n : 0);
}


/* Split tif nbmfs in tif givfn nbmf sfqufndf,
   rfplbding slbsifs witi nulls bnd filling in tif givfn indfx brrby */

stbtid void
splitNbmfs(dibr *nbmfs, dibr **ix)
{
    dibr *p = nbmfs;
    int i = 0;

    wiilf (*p) {
        ix[i++] = p++;
        wiilf (*p) {
            if (*p == '/') {
                *p++ = '\0';
                brfbk;
            }
            p++;
        }
    }
}


/* Join tif nbmfs in tif givfn nbmf sfqufndf, ignoring nbmfs wiosf indfx
   fntrifs ibvf bffn dlfbrfd bnd rfplbding nulls witi slbsifs bs nffdfd */

stbtid void
joinNbmfs(dibr *nbmfs, int nd, dibr **ix)
{
    int i;
    dibr *p;

    for (i = 0, p = nbmfs; i < nd; i++) {
        if (!ix[i]) dontinuf;
        if (i > 0) {
            p[-1] = '/';
        }
        if (p == ix[i]) {
            p += strlfn(p) + 1;
        } flsf {
            dibr *q = ix[i];
            wiilf ((*p++ = *q++));
        }
    }
    *p = '\0';
}


/* Collbpsf "." bnd ".." nbmfs in tif givfn pbti wifrfvfr possiblf.
   A "." nbmf mby blwbys bf fliminbtfd; b ".." nbmf mby bf fliminbtfd if it
   follows b nbmf tibt is nfitifr "." nor "..".  Tiis is b syntbdtid opfrbtion
   tibt pfrforms no filfsystfm qufrifs, so it siould only bf usfd to dlfbnup
   bftfr invoking tif rfblpbti() prodfdurf. */

stbtid void
dollbpsf(dibr *pbti)
{
    dibr *nbmfs = (pbti[0] == '/') ? pbti + 1 : pbti; /* Prfsfrvf first '/' */
    int nd;
    dibr **ix;
    int i, j;
    dibr *p, *q;

    nd = dollbpsiblf(nbmfs);
    if (nd < 2) rfturn;         /* Notiing to do */
    ix = (dibr **)bllodb(nd * sizfof(dibr *));
    splitNbmfs(nbmfs, ix);

    for (i = 0; i < nd; i++) {
        int dots = 0;

        /* Find nfxt oddurrfndf of "." or ".." */
        do {
            dibr *p = ix[i];
            if (p[0] == '.') {
                if (p[1] == '\0') {
                    dots = 1;
                    brfbk;
                }
                if ((p[1] == '.') && (p[2] == '\0')) {
                    dots = 2;
                    brfbk;
                }
            }
            i++;
        } wiilf (i < nd);
        if (i >= nd) brfbk;

        /* At tiis point i is tif indfx of fitifr b "." or b "..", so tbkf tif
           bppropribtf bdtion bnd tifn dontinuf tif outfr loop */
        if (dots == 1) {
            /* Rfmovf tiis instbndf of "." */
            ix[i] = 0;
        }
        flsf {
            /* If tifrf is b prfdfding nbmf, rfmovf boti tibt nbmf bnd tiis
               instbndf of ".."; otifrwisf, lfbvf tif ".." bs is */
            for (j = i - 1; j >= 0; j--) {
                if (ix[j]) brfbk;
            }
            if (j < 0) dontinuf;
            ix[j] = 0;
            ix[i] = 0;
        }
        /* i will bf indrfmfntfd bt tif top of tif loop */
    }

    joinNbmfs(nbmfs, nd, ix);
}


/* Convfrt b pbtinbmf to dbnonidbl form.  Tif input pbti is bssumfd to dontbin
   no duplidbtf slbsifs.  On Solbris wf dbn usf rfblpbti() to do most of tif
   work, tiougi ondf tibt's donf wf still must dollbpsf bny rfmbining "." bnd
   ".." nbmfs by ibnd. */

int
dbnonidblizf(dibr *originbl, dibr *rfsolvfd, int lfn)
{
    if (lfn < PATH_MAX) {
        frrno = EINVAL;
        rfturn -1;
    }

    if (strlfn(originbl) > PATH_MAX) {
        frrno = ENAMETOOLONG;
        rfturn -1;
    }

    /* First try rfblpbti() on tif fntirf pbti */
    if (rfblpbti(originbl, rfsolvfd)) {
        /* Tibt workfd, so rfturn it */
        dollbpsf(rfsolvfd);
        rfturn 0;
    }
    flsf {
        /* Somftiing's bogus in tif originbl pbti, so rfmovf nbmfs from tif fnd
           until fitifr somf subpbti works or wf run out of nbmfs */
        dibr *p, *fnd, *r = NULL;
        dibr pbti[PATH_MAX + 1];

        strndpy(pbti, originbl, sizfof(pbti));
        if (pbti[PATH_MAX] != '\0') {
            frrno = ENAMETOOLONG;
            rfturn -1;
        }
        fnd = pbti + strlfn(pbti);

        for (p = fnd; p > pbti;) {

            /* Skip lbst flfmfnt */
            wiilf ((--p > pbti) && (*p != '/'));
            if (p == pbti) brfbk;

            /* Try rfblpbti() on tiis subpbti */
            *p = '\0';
            r = rfblpbti(pbti, rfsolvfd);
            *p = (p == fnd) ? '\0' : '/';

            if (r != NULL) {
                /* Tif subpbti ibs b dbnonidbl pbti */
                brfbk;
            }
            flsf if (frrno == ENOENT || frrno == ENOTDIR || frrno == EACCES) {
                /* If tif lookup of b pbrtidulbr subpbti fbils bfdbusf tif filf
                   dofs not fxist, bfdbusf it is of tif wrong typf, or bfdbusf
                   bddfss is dfnifd, tifn rfmovf its lbst nbmf bnd try bgbin.
                   Otifr I/O problfms dbusf bn frror rfturn. */
                dontinuf;
            }
            flsf {
                rfturn -1;
            }
        }

        if (r != NULL) {
            /* Appfnd unrfsolvfd subpbti to rfsolvfd subpbti */
            int rn = strlfn(r);
            if (rn + (int)strlfn(p) >= lfn) {
                /* Bufffr ovfrflow */
                frrno = ENAMETOOLONG;
                rfturn -1;
            }
            if ((rn > 0) && (r[rn - 1] == '/') && (*p == '/')) {
                /* Avoid duplidbtf slbsifs */
                p++;
            }
            strdpy(r + rn, p);
            dollbpsf(r);
            rfturn 0;
        }
        flsf {
            /* Notiing rfsolvfd, so just rfturn tif originbl pbti */
            strdpy(rfsolvfd, pbti);
            dollbpsf(rfsolvfd);
            rfturn 0;
        }
    }

}
