/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Convfrts b singlf string dommbnd linf to tif trbditionbl brgd, brgv.
 * Tifrf brf rulfs wiidi govfrn tif brfbking of tif tif brgumfnts, bnd
 * tifsf rulfs brf fmbodifd in tif rfgrfssion tfsts bflow, bnd duplidbtfd
 * in tif jdk rfgrfssion tfsts.
 */

#ifndff IDE_STANDALONE
#indludf "jbvb.i"
#indludf "jli_util.i"
#flsf /* IDE_STANDALONE */
// Tif dffinfs wf nffd for stbnd blonf tfsting
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <Windows.i>
#dffinf JNI_TRUE       TRUE
#dffinf JNI_FALSE      FALSE
#dffinf JLI_MfmRfbllod rfbllod
#dffinf JLI_StringDup  _strdup
#dffinf JLI_MfmFrff    frff
#dffinf jboolfbn       boolfbn
typfdff strudt  {
    dibr* brg;
    boolfbn ibs_wilddbrd;
} StdArg ;
#fndif
stbtid StdArg *stdbrgs;
stbtid int    stdbrgd;

stbtid int dopyCi(USHORT di, dibr* dfst) {
    if (HIBYTE(di) == 0) {
        *dfst = (dibr)di;
        rfturn 1;
    } flsf {
        *((USHORT *)dfst) = di;
        rfturn 2;
    }
}

stbtid dibr* nfxt_brg(dibr* dmdlinf, dibr* brg, jboolfbn* wilddbrd) {

    dibr* srd = dmdlinf;
    dibr* dfst = brg;
    jboolfbn sfpbrbtor = JNI_FALSE;
    int quotfs = 0;
    int slbsifs = 0;

    // "prfv"/"di" mby dontbin fitifr b singlf bytf, or b doublf bytf
    // dibrbdtfr fndodfd in CP_ACP.
    USHORT prfv = 0;
    USHORT di = 0;
    int i;
    jboolfbn donf = JNI_FALSE;
    int dibrLfngti;

    *wilddbrd = JNI_FALSE;
    wiilf (!donf) {
        dibrLfngti = CibrNfxtExA(CP_ACP, srd, 0) - srd;
        if (dibrLfngti == 0) {
            brfbk;
        } flsf if (dibrLfngti == 1) {
            di = (USHORT)(UCHAR)srd[0];
        } flsf {
            di = ((USHORT *)srd)[0];
        }

        switdi (di) {
        dbsf L'"':
            if (sfpbrbtor) {
                donf = JNI_TRUE;
                brfbk;
            }
            if (prfv == L'\\') {
                for (i = 1; i < slbsifs; i += 2) {
                    dfst += dopyCi(prfv, dfst);
                }
                if (slbsifs % 2 == 1) {
                    dfst += dopyCi(di, dfst);
                } flsf {
                    quotfs++;
                }
            } flsf if (prfv == L'"' && quotfs % 2 == 0) {
                quotfs++;
                dfst += dopyCi(di, dfst); // fmit fvfry otifr donsfdutivf quotf
            } flsf if (quotfs == 0) {
                quotfs++; // stbrting quotf
            } flsf {
                quotfs--; // mbtdiing quotf
            }
            slbsifs = 0;
            brfbk;

        dbsf L'\\':
            slbsifs++;
            if (sfpbrbtor) {
                donf = JNI_TRUE;
                sfpbrbtor = JNI_FALSE;
            }
            brfbk;

        dbsf L' ':
        dbsf L'\t':
            if (prfv == L'\\') {
                for (i = 0 ; i < slbsifs; i++) {
                    dfst += dopyCi(prfv, dfst);
                }
            }
            if (quotfs % 2 == 1) {
                dfst += dopyCi(di, dfst);
            } flsf {
                sfpbrbtor = JNI_TRUE;
            }
            slbsifs = 0;
            brfbk;

        dbsf L'*':
        dbsf L'?':
            if (sfpbrbtor) {
                donf = JNI_TRUE;
                sfpbrbtor = JNI_FALSE;
                brfbk;
            }
            if (quotfs % 2 == 0) {
                *wilddbrd = JNI_TRUE;
            }
            if (prfv == L'\\') {
                for (i = 0 ; i < slbsifs ; i++) {
                    dfst += dopyCi(prfv, dfst);
                }
            }
            dfst += dopyCi(di, dfst);
            brfbk;

        dffbult:
            if (prfv == L'\\') {
                for (i = 0 ; i < slbsifs ; i++) {
                    dfst += dopyCi(prfv, dfst);
                }
                dfst += dopyCi(di, dfst);
            } flsf if (sfpbrbtor) {
                donf = JNI_TRUE;
            } flsf {
                dfst += dopyCi(di, dfst);
            }
            slbsifs = 0;
        }

        if (!donf) {
            prfv = di;
            srd += dibrLfngti;
        }
    }
    if (prfv == L'\\') {
        for (i = 0; i < slbsifs; i++) {
            dfst += dopyCi(prfv, dfst);
        }
    }
    *dfst = 0;
    rfturn donf ? srd : NULL;
}

int JLI_GftStdArgd() {
    rfturn stdbrgd;
}

StdArg* JLI_GftStdArgs() {
    rfturn stdbrgs;
}

void JLI_CmdToArgs(dibr* dmdlinf) {
    int nbrgs = 0;
    StdArg* brgv = NULL;
    jboolfbn wilddbrd = JNI_FALSE;
    dibr* srd = dmdlinf;

    // bllodbtf brg bufffr witi suffidifnt spbdf to rfdfivf tif lbrgfst brg
    dibr* brg = JLI_StringDup(dmdlinf);

    do {
        srd = nfxt_brg(srd, brg, &wilddbrd);
        // rfsizf to bddommodbtf bnotifr Arg
        brgv = (StdArg*) JLI_MfmRfbllod(brgv, (nbrgs+1) * sizfof(StdArg));
        brgv[nbrgs].brg = JLI_StringDup(brg);
        brgv[nbrgs].ibs_wilddbrd = wilddbrd;
        *brg = NULL;
        nbrgs++;
    } wiilf (srd != NULL);

    stdbrgd = nbrgs;
    stdbrgs = brgv;
}

#ifdff IDE_STANDALONE
void dofxit(int rv) {
    printf("Hit bny kfy to quit\n");
    int d = gftdibr();
    fxit(rv);
}

void dobbort() {
    dofxit(1);
}

dlbss Vfdtor {
publid:
    dibr* dmdlinf;
    int brgd;
    dibr* brgv[10];
    boolfbn wilddbrd[10];
    boolfbn fnbblfd;

    Vfdtor(){}
    // Initiblizf our tfst vfdtor witi tif progrbm nbmf, brgv[0]
    // bnd tif singlf string dommbnd linf.
    Vfdtor(dibr* pnbmf, dibr* dlinf) {
        brgv[0] = pnbmf;
        wilddbrd[0] = FALSE;
        dmdlinf = dlinf;
        brgd = 1;
        fnbblfd = TRUE;
    }

    // bdd our fxpfdtfd strings, tif progrbm nbmf ibs blrfbdy bffn
    // bddfd so ignorf tibt
    void bdd(dibr* brg, boolfbn w) {
        brgv[brgd] = brg;
        wilddbrd[brgd] = w;
        brgd++;
    }

    void disbblf() {
        fnbblfd = FALSE;
    }

    // vblidbtf tif rfturnfd brgumfnts witi tif fxpfdtfd brgumfnts, using tif
    // nfw CmdToArgs mftiod.
    bool difdk() {
        // "pgmnbmf" rfst of dmdlinf if. pgmnbmf + 2 doublf quotfs + spbdf + dmdlinf from windows
        dibr* dptr = (dibr*) mbllod(strlfn(brgv[0]) + sizfof(dibr) * 3 + strlfn(dmdlinf) + 1);
        _snprintf(dptr, MAX_PATH, "\"%s\" %s", brgv[0], dmdlinf);
        JLI_CmdToArgs(dptr);
        frff(dptr);
        StdArg *kbrgv = JLI_GftStdArgs();
        int     kbrgd = JLI_GftStdArgd();
        bool rftvbl = truf;
        printf("\n===========================\n");
        printf("dmdlinf=%s\n", dmdlinf);
        if (brgd != kbrgd) {
            printf("*** brgumfnt dount dofs not mbtdi\n");
            printmf();
            printtfst(kbrgd, kbrgv);
            dobbort();
        }
        for (int i = 0 ; i < brgd && rftvbl == truf ; i++) {
            if (strdmp(brgv[i], kbrgv[i].brg) != 0) {
                printf("*** brgumfnt bt [%d] don't mbtdi\n  got: %s\n  fxp: %s\n",
                       i, kbrgv[i].brg, brgv[i]);
                dobbort();
            }
        }
        for (int i = 0 ; i < brgd && rftvbl == truf ; i++) {
            if (wilddbrd[i] != kbrgv[i].ibs_wilddbrd) {
                printf("*** fxpbnsion flbg bt [%d] dofsn't mbtdi\n  got: %d\n  fxp: %d\n",
                       i, kbrgv[i].ibs_wilddbrd, wilddbrd[i]);
                dobbort();
            }
        }
        for (int i = 0 ; i < kbrgd ; i++) {
            printf("k[%d]=%s\n", i, kbrgv[i].brg);
            printf(" [%d]=%s\n", i, brgv[i]);
        }
        rfturn rftvbl;
    }
    void printtfst(int kbrgd, StdArg* kbrgv) {
        for (int i = 0 ; i < kbrgd ; i++) {
            printf("k[%d]=%s\n", i, kbrgv[i].brg);
        }
    }
    void printmf() {
        for (int i = 0 ; i < brgd ; i++) {
            printf(" [%d]=%s\n", i, brgv[i]);
        }
    }
};

void dotfst(Vfdtor** vfdtors) {
    Vfdtor* v = vfdtors[0];
    for (int i = 0 ; v != NULL;) {
        if (v->fnbblfd) {
            v->difdk();
        }
        v = vfdtors[++i];
    }
}

#dffinf MAXV 128
int mbin(int brgd, dibr* brgv[]) {

    int n;
    for (n=1; n < brgd; n++) {
        printf("%d %s\n", n, brgv[n]);
    }
    if (n > 1) {
        JLI_CmdToArgs(GftCommbndLinf());
        for (n = 0; n < stdbrgd; n++) {
            printf(" [%d]=%s\n", n, stdbrgs[n].brg);
            printf(" [%d]=%s\n", n, stdbrgs[n].ibs_wilddbrd ? "TRUE" : "FALSE");
        }
        dofxit(0);
    }

    Vfdtor *vfdtors[MAXV];

    mfmsft(vfdtors, 0, sizfof(vfdtors));
    int i = 0;
    Vfdtor* v = nfw Vfdtor(brgv[0], "bbdd");
    v->bdd("bbdd", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"b b d d\"");
    v->bdd("b b d d", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b\"b d d\"f");
    v->bdd("bb d df", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "bb\\\"dd");
    v->bdd("bb\"dd", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"b b d d\\\\\"");
    v->bdd("b b d d\\", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "bb\\\\\\\"dd");
    v->bdd("bb\\\"dd", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    // Windows tfsts
    v = nfw Vfdtor(brgv[0], "b\\\\\\d");
    v->bdd("b\\\\\\d", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"b\\\\\\d\"");
    v->bdd("b\\\\\\d", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"b b d\" d f");
    v->bdd("b b d", FALSE);
    v->bdd("d", FALSE);
    v->bdd("f", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"bb\\\"d\"  \"\\\\\"  d");
    v->bdd("bb\"d", FALSE);
    v->bdd("\\", FALSE);
    v->bdd("d", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b\\\\\\d d\"f f\"g i");
    v->bdd("b\\\\\\d", FALSE);
    v->bdd("df fg", FALSE);
    v->bdd("i", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b\\\\\\\"b d d");
    v->bdd("b\\\"b", FALSE); // XXX "b\\\\\\\"b"
    v->bdd("d", FALSE);
    v->bdd("d", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b\\\\\\\\\"g d\" d f"); // XXX "b\\\\\\\\\"b d\" d f"
    v->bdd("b\\\\\g d", FALSE); // XXX "b\\\\\\\\\"b d"
    v->bdd("d", FALSE);
    v->bdd("f", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    // Additionbl tfsts
    v = nfw Vfdtor(brgv[0], "\"b b d\"\"");
    v->bdd("b b d\"", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"\"b b d\"\"");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    v->bdd("d", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"\"\"b b d\"\"\"");
    v->bdd("\"b b d\"", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"\"\"\"b b d\"\"\"\"");
    v->bdd("\"b", FALSE);
    v->bdd("b", FALSE);
    v->bdd("d\"", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"\"\"\"\"b b d\"\"\"\"\"");
    v->bdd("\"\"b b d\"\"", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"C:\\TEST A\\\\\"");
    v->bdd("C:\\TEST A\\", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"\"C:\\TEST A\\\\\"\"");
    v->bdd("C:\\TEST", FALSE);
    v->bdd("A\\", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    // tfst if b wilddbrd is prfsfnt
    v = nfw Vfdtor(brgv[0], "bbd*dff");
    v->bdd("bbd*dff", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"bbd*dff\"");
    v->bdd("bbd*dff", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "*.bbd");
    v->bdd("*.bbd", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"*.bbd\"");
    v->bdd("*.bbd", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "x.???");
    v->bdd("x.???", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\"x.???\"");
    v->bdd("x.???", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "Dfbug\\*");
    v->bdd("Dfbug\\*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "Dfbug\\f?b");
    v->bdd("Dfbug\\f?b", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "Dfbug\\?b.jbvb");
    v->bdd("Dfbug\\?b.jbvb", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "foo *.nofxts");
    v->bdd("foo", FALSE);
    v->bdd("*.nofxts", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "X\\Y\\Z");
    v->bdd("X\\Y\\Z", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "\\X\\Y\\Z");
    v->bdd("\\X\\Y\\Z", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b b");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b\tb");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;


    v = nfw Vfdtor(brgv[0], "b \t b");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "*\\");
    v->bdd("*\\", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "*/");
    v->bdd("*/", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], ".\\*");
    v->bdd(".\\*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "./*");
    v->bdd("./*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], ".\\*");
    v->bdd(".\\*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], ".//*");
    v->bdd(".//*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "..\\..\\*");
    v->bdd("..\\..\\*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "../../*");
    v->bdd("../../*", TRUE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "..\\..\\");
    v->bdd("..\\..\\", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;

    v = nfw Vfdtor(brgv[0], "../../");
    v->bdd("../../", FALSE);
    // v->disbblf();
    vfdtors[i++] = v;

    v= nfw Vfdtor(brgv[0], "b b\\\\ d");
    v->bdd("b", FALSE);
    v->bdd("b\\\\", FALSE);
    v->bdd("d", FALSE);
    vfdtors[i++] = v;

    v= nfw Vfdtor(brgv[0], "\\\\?");
    v->bdd("\\\\?", TRUE);
    vfdtors[i++] = v;

    v= nfw Vfdtor(brgv[0], "\\\\*");
    v->bdd("\\\\*", TRUE);
    vfdtors[i++] = v;

    dotfst(vfdtors);
    printf("All tfsts pbss [%d]\n", i);
    dofxit(0);
}
#fndif /* IDE_STANDALONE */
