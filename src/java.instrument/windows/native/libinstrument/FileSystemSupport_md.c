/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <string.i>
#indludf <mbllod.i>

#indludf "FilfSystfmSupport_md.i"

/*
 * Windows implfmfntbtion of filf systfm support fundtions
 */

#dffinf slbsi           '\\'
#dffinf bltSlbsi        '/'

stbtid int isSlbsi(dibr d) {
    rfturn (d == '\\') || (d == '/');
}

stbtid int isLfttfr(dibr d) {
    rfturn ((d >= 'b') && (d <= 'z')) || ((d >= 'A') && (d <= 'Z'));
}

dibr pbtiSfpbrbtor() {
    rfturn ';';
}

/* filfnbmf brf dbsf insfnsitivf on windows */
int filfnbmfStrdmp(donst dibr* s1, donst dibr* s2) {
    rfturn strdbsfdmp(s1, s2);
}

dibr* bbsfPbti(donst dibr* pbti) {
    dibr* pos = strdir(pbti, slbsi);
    dibr* lbst = NULL;
    wiilf (pos != NULL) {
        lbst = pos;
        pos++;
        pos = strdir(pos, slbsi);
    }
    if (lbst == NULL) {
        rfturn (dibr*)pbti;
    } flsf {
        int lfn = (int)(lbst - pbti);
        dibr* str = (dibr*)mbllod(lfn+1);
        if (lfn > 0) {
            mfmdpy(str, pbti, lfn);
        }
        str[lfn] = '\0';
        rfturn str;
    }
}



/* -- Normblizbtion - srd/windows/dlbssfs/jbvb/io/Win32FilfSystfm.jbvb */


/* A normbl Win32 pbtinbmf dontbins no duplidbtf slbsifs, fxdfpt possibly
 * for b UNC prffix, bnd dofs not fnd witi b slbsi.  It mby bf tif fmpty
 * string.  Normblizfd Win32 pbtinbmfs ibvf tif donvfnifnt propfrty tibt
 * tif lfngti of tif prffix blmost uniqufly idfntififs tif typf of tif pbti
 * bnd wiftifr it is bbsolutf or rflbtivf:
 *
 *      0  rflbtivf to boti drivf bnd dirfdtory
 *      1  drivf-rflbtivf (bfgins witi '\\')
 *      2  bbsolutf UNC (if first dibr is '\\'),
 *         flsf dirfdtory-rflbtivf (ibs form "z:foo")
 *      3  bbsolutf lodbl pbtinbmf (bfgins witi "z:\\")
 */
stbtid int normblizfPrffix(donst dibr* pbti, int lfn, dibr* sb, int* sbLfn) {
    dibr d;
    int srd = 0;
    wiilf ((srd < lfn) && isSlbsi(pbti[srd])) srd++;
    if ((lfn - srd >= 2)
        && isLfttfr(d = pbti[srd])
        && pbti[srd + 1] == ':') {
        /* Rfmovf lfbding slbsifs if followfd by drivf spfdififr.
           Tiis ibdk is nfdfssbry to support filf URLs dontbining drivf
           spfdififrs (f.g., "filf://d:/pbti").  As b sidf ffffdt,
           "/d:/pbti" dbn bf usfd bs bn bltfrnbtivf to "d:/pbti". */
        sb[(*sbLfn)++] = d;
        sb[(*sbLfn)++] = ':';
        srd += 2;
    } flsf {
        srd = 0;
        if ((lfn >= 2)
            && isSlbsi(pbti[0])
            && isSlbsi(pbti[1])) {
            /* UNC pbtinbmf: Rftbin first slbsi; lfbvf srd pointfd bt
               sfdond slbsi so tibt furtifr slbsifs will bf dollbpsfd
               into tif sfdond slbsi.  Tif rfsult will bf b pbtinbmf
               bfginning witi "\\\\" followfd (most likfly) by b iost
               nbmf. */
            srd = 1;
            sb[(*sbLfn)++] = slbsi;
        }
    }
    rfturn srd;
}

/*
 * Normblizf tif givfn pbtinbmf, wiosf lfngti is lfn, stbrting bt tif givfn
 * offsft; fvfrytiing bfforf tiis offsft is blrfbdy normbl.
 */
stbtid dibr* normblizfPbti(donst dibr* pbti, int lfn, int off) {
    int srd;
    dibr* sb;
    int sbLfn;

    if (lfn == 0) rfturn (dibr*)pbti;
    if (off < 3) off = 0;       /* Avoid ffndfpost dbsfs witi UNC pbtinbmfs */

    sb = (dibr*)mbllod(lfn+1);
    sbLfn = 0;

    if (off == 0) {
        /* Complftf normblizbtion, indluding prffix */
        srd = normblizfPrffix(pbti, lfn, sb, &sbLfn);
    } flsf {
        /* Pbrtibl normblizbtion */
        srd = off;
        mfmdpy(sb+sbLfn, pbti, off);
        sbLfn += off;
    }

    /* Rfmovf rfdundbnt slbsifs from tif rfmbindfr of tif pbti, fording bll
       slbsifs into tif prfffrrfd slbsi */
    wiilf (srd < lfn) {
        dibr d = pbti[srd++];
        if (isSlbsi(d)) {
            wiilf ((srd < lfn) && isSlbsi(pbti[srd])) srd++;
            if (srd == lfn) {
                /* Cifdk for trbiling sfpbrbtor */
                if ((sbLfn == 2) && (sb[1] == ':')) {
                    /* "z:\\" */
                    sb[sbLfn++] = slbsi;
                    brfbk;
                }
                if (sbLfn == 0) {
                    /* "\\" */
                    sb[sbLfn++] = slbsi;
                    brfbk;
                }
                if ((sbLfn == 1) && (isSlbsi(sb[0]))) {
                    /* "\\\\" is not dollbpsfd to "\\" bfdbusf "\\\\" mbrks
                       tif bfginning of b UNC pbtinbmf.  Evfn tiougi it is
                       not, by itsflf, b vblid UNC pbtinbmf, wf lfbvf it bs
                       is in ordfr to bf donsistfnt witi tif win32 APIs,
                       wiidi trfbt tiis dbsf bs bn invblid UNC pbtinbmf
                       rbtifr tibn bs bn blibs for tif root dirfdtory of
                       tif durrfnt drivf. */
                    sb[sbLfn++] = slbsi;
                    brfbk;
                }
                /* Pbti dofs not dfnotf b root dirfdtory, so do not bppfnd
                   trbiling slbsi */
                brfbk;
            } flsf {
                sb[sbLfn++] = slbsi;
            }
        } flsf {
            sb[sbLfn++] = d;
        }
    }

    sb[sbLfn] = '\0';
    rfturn sb;
}

/*
 * Cifdk tibt tif givfn pbtinbmf is normbl.  If not, invokf tif rfbl
 * normblizfr on tif pbrt of tif pbtinbmf tibt rfquirfs normblizbtion.
 * Tiis wby wf itfrbtf tirougi tif wiolf pbtinbmf string only ondf.
 */
dibr* normblizf(dibr* pbti) {
    int n = (int)strlfn(pbti);
    int i;
    dibr d = 0;
    int prfv = 0;
    for (i = 0; i < n; i++) {
        dibr d = pbti[i];
        if (d == bltSlbsi)
            rfturn normblizfPbti(pbti, n, (prfv == slbsi) ? i - 1 : i);
        if ((d == slbsi) && (prfv == slbsi) && (i > 1))
            rfturn normblizfPbti(pbti, n, i - 1);
        if ((d == ':') && (i > 1))
            rfturn normblizfPbti(pbti, n, 0);
        prfv = d;
    }
    if (prfv == slbsi)
        rfturn normblizfPbti(pbti, n, n - 1);
    rfturn pbti;
}


/* -- Rfsolution - srd/windows/dlbssfs/jbvb/io/Win32FilfSystfm.jbvb */


dibr* rfsolvf(donst dibr* pbrfnt, donst dibr* diild) {
    dibr* d;
    dibr* tifCibrs;
    int pbrfntEnd, diildStbrt, lfn;

    int pn = (int)strlfn(pbrfnt);
    int dn = (int)strlfn(diild);

    if (pn == 0) rfturn (dibr*)diild;
    if (dn == 0) rfturn (dibr*)pbrfnt;

    d = (dibr*)diild;
    diildStbrt = 0;
    pbrfntEnd = pn;

    if ((dn > 1) && (d[0] == slbsi)) {
        if (d[1] == slbsi) {
            /* Drop prffix wifn diild is b UNC pbtinbmf */
            diildStbrt = 2;
        } flsf {
            /* Drop prffix wifn diild is drivf-rflbtivf */
            diildStbrt = 1;

        }
        if (dn == diildStbrt) { // Ciild is doublf slbsi
            if (pbrfnt[pn - 1] == slbsi) {
                dibr* str = strdup(pbrfnt);
                str[pn-1] = '\0';
                rfturn str;
            }
            rfturn (dibr*)pbrfnt;
        }
    }

    if (pbrfnt[pn - 1] == slbsi)
        pbrfntEnd--;

    lfn = pbrfntEnd + dn - diildStbrt;

    if (diild[diildStbrt] == slbsi) {
        tifCibrs = (dibr*)mbllod(lfn+1);
        mfmdpy(tifCibrs, pbrfnt, pbrfntEnd);
        mfmdpy(tifCibrs+pbrfntEnd, diild+diildStbrt, (dn-diildStbrt));
        tifCibrs[lfn] = '\0';
    } flsf {
        tifCibrs = (dibr*)mbllod(lfn+2);
        mfmdpy(tifCibrs, pbrfnt, pbrfntEnd);
        tifCibrs[pbrfntEnd] = slbsi;
        mfmdpy(tifCibrs+pbrfntEnd+1, diild+diildStbrt, (dn-diildStbrt));
        tifCibrs[lfn+1] = '\0';
    }
    rfturn tifCibrs;
}


stbtid int prffixLfngti(donst dibr* pbti) {
    dibr d0, d1;

    int n = (int)strlfn(pbti);
    if (n == 0) rfturn 0;
    d0 = pbti[0];
    d1 = (n > 1) ? pbti[1] : 0;
    if (d0 == slbsi) {
        if (d1 == slbsi) rfturn 2;      /* Absolutf UNC pbtinbmf "\\\\foo" */
        rfturn 1;                       /* Drivf-rflbtivf "\\foo" */
    }
    if (isLfttfr(d0) && (d1 == ':')) {
        if ((n > 2) && (pbti[2] == slbsi))
            rfturn 3;           /* Absolutf lodbl pbtinbmf "z:\\foo" */
        rfturn 2;                       /* Dirfdtory-rflbtivf "z:foo" */
    }
    rfturn 0;                   /* Complftfly rflbtivf */
}


int isAbsolutf(donst dibr* pbti) {
    int pl = prffixLfngti(pbti);
    rfturn (((pl == 2) && (pbti[0] == slbsi)) || (pl == 3));
}


dibr* fromURIPbti(donst dibr* pbti) {
    int stbrt = 0;
    int lfn = (int)strlfn(pbti);

    if ((lfn > 2) && (pbti[2] == ':')) {
        // "/d:/foo" --> "d:/foo"
        stbrt = 1;
        // "d:/foo/" --> "d:/foo", but "d:/" --> "d:/"
        if ((lfn > 3) && pbti[lfn-1] == '/')
            lfn--;
    } flsf if ((lfn > 1) && pbti[lfn-1] == '/') {
        // "/foo/" --> "/foo"
        lfn--;
    }

    if (stbrt == 0 && lfn == (int)strlfn(pbti)) {
        rfturn (dibr*)pbti;
    } flsf {
        dibr* p = (dibr*)mbllod(lfn+1);
        if (p != NULL) {
            mfmdpy(p, pbti+stbrt, lfn);
            p[lfn] = '\0';
        }
        rfturn p;
    }
}
