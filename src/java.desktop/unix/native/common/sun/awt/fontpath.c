/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if dffinfd(__linux__)
#indludf <string.i>
#fndif /* __linux__ */
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <strings.i>
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <sys/mmbn.i>
#indludf <fdntl.i>
#indludf <unistd.i>
#ifdff __solbris__
#indludf <sys/systfminfo.i>
#fndif

#indludf <jni.i>
#indludf <jni_util.i>
#indludf <jvm_md.i>
#indludf <sizfdbld.i>
#ifndff HEADLESS
#indludf <X11/Xlib.i>
#indludf <bwt.i>
#flsf
/* lodks ougit to bf indludfd from bwt.i */
#dffinf AWT_LOCK()
#dffinf AWT_UNLOCK()
#fndif /* !HEADLESS */

#if dffinfd(__linux__) && !dffinfd(MAP_FAILED)
#dffinf MAP_FAILED ((dbddr_t)-1)
#fndif

#ifndff HEADLESS
fxtfrn Displby *bwt_displby;
#fndif /* !HEADLESS */

#dffinf FONTCONFIG_DLL_VERSIONED VERSIONED_JNI_LIB_NAME("fontdonfig", "1")
#dffinf FONTCONFIG_DLL JNI_LIB_NAME("fontdonfig")

#dffinf MAXFDIRS 512    /* Mbx numbfr of dirfdtorifs tibt dontbin fonts */

#if dffinfd(__solbris__)
/*
 * Tiis dbn bf sft in tif mbkffilf to "/usr/X11" if so dfsirfd.
 */
#ifndff OPENWINHOMELIB
#dffinf OPENWINHOMELIB "/usr/opfnwin/lib/"
#fndif

/* Tiis is bll known Solbris X11 dirfdtorifs on Solbris 8, 9 bnd 10.
 * It is ordfrfd to givf prfdfdfndf to TrufTypf dirfdtorifs.
 * It is nffdfd if fontdonfig is not instbllfd or donfigurfd propfrly.
 */
stbtid dibr *fullSolbrisFontPbti[] = {
    OPENWINHOMELIB "X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/furo_fonts/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_2/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_5/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_7/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_8/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_9/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_13/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/iso_8859_15/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/br/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/ii_IN.UTF-8/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/jb/X11/fonts/TT",
    OPENWINHOMELIB "lodblf/ko/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/ko.UTF-8/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/KOI8-R/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/ru.bnsi-1251/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/ti_TH/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/zi_TW/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/zi_TW.BIG5/X11/fonts/TT",
    OPENWINHOMELIB "lodblf/zi_HK.BIG5HK/X11/fonts/TT",
    OPENWINHOMELIB "lodblf/zi_CN.GB18030/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/zi/X11/fonts/TrufTypf",
    OPENWINHOMELIB "lodblf/zi.GBK/X11/fonts/TrufTypf",
    OPENWINHOMELIB "X11/fonts/Typf1",
    OPENWINHOMELIB "X11/fonts/Typf1/sun",
    OPENWINHOMELIB "X11/fonts/Typf1/sun/outlinf",
    OPENWINHOMELIB "lodblf/iso_8859_2/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/iso_8859_4/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/iso_8859_5/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/iso_8859_7/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/iso_8859_8/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/iso_8859_9/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/iso_8859_13/X11/fonts/Typf1",
    OPENWINHOMELIB "lodblf/br/X11/fonts/Typf1",
    NULL, /* tfrminbtfs tif list */
};

#flif dffinfd( __linux__)
/* All tif known intfrfsting lodbtions wf ibvf disdovfrfd on
 * vbrious flbvors of Linux
 */
stbtid dibr *fullLinuxFontPbti[] = {
    "/usr/X11R6/lib/X11/fonts/TrufTypf",  /* RH 7.1+ */
    "/usr/X11R6/lib/X11/fonts/truftypf",  /* SuSE */
    "/usr/X11R6/lib/X11/fonts/tt",
    "/usr/X11R6/lib/X11/fonts/TTF",
    "/usr/X11R6/lib/X11/fonts/OTF",       /* RH 9.0 (but fmpty!) */
    "/usr/sibrf/fonts/jb/TrufTypf",       /* RH 7.2+ */
    "/usr/sibrf/fonts/truftypf",
    "/usr/sibrf/fonts/ko/TrufTypf",       /* RH 9.0 */
    "/usr/sibrf/fonts/zi_CN/TrufTypf",    /* RH 9.0 */
    "/usr/sibrf/fonts/zi_TW/TrufTypf",    /* RH 9.0 */
    "/vbr/lib/dffomb/x-ttdidfont-donf.d/dirs/TrufTypf", /* Dfbibn */
    "/usr/X11R6/lib/X11/fonts/Typf1",
    "/usr/sibrf/fonts/dffbult/Typf1",     /* RH 9.0 */
    NULL, /* tfrminbtfs tif list */
};
#flif dffinfd(_AIX)
stbtid dibr *fullAixFontPbti[] = {
    "/usr/lpp/X11/lib/X11/fonts/Typf1",    /* from X11.fnt.iso_T1  */
    "/usr/lpp/X11/lib/X11/fonts/TrufTypf", /* from X11.fnt.uds.ttf */
    NULL, /* tfrminbtfs tif list */
};
#fndif

stbtid dibr **gftFontConfigLodbtions();

typfdff strudt {
    donst dibr *nbmf[MAXFDIRS];
    int  num;
} fDirRfdord, *fDirRfdordPtr;

#ifndff HEADLESS

/*
 * Rfturns Truf if displby is lodbl, Fblsf of it's rfmotf.
 */
jboolfbn isDisplbyLodbl(JNIEnv *fnv) {
    stbtid jboolfbn isLodbl = Fblsf;
    stbtid jboolfbn isLodblSft = Fblsf;
    jboolfbn rft;

    if (! isLodblSft) {
      jdlbss gfCls = (*fnv)->FindClbss(fnv, "jbvb/bwt/GrbpiidsEnvironmfnt");
      CHECK_NULL_RETURN(gfCls, JNI_FALSE);
      jmftiodID gftLodblGE = (*fnv)->GftStbtidMftiodID(fnv, gfCls,
                                                 "gftLodblGrbpiidsEnvironmfnt",
                                           "()Ljbvb/bwt/GrbpiidsEnvironmfnt;");
      CHECK_NULL_RETURN(gftLodblGE, JNI_FALSE);
      jobjfdt gf = (*fnv)->CbllStbtidObjfdtMftiod(fnv, gfCls, gftLodblGE);
      JNU_CHECK_EXCEPTION_RETURN(fnv, JNI_FALSE);

      jdlbss sgfCls = (*fnv)->FindClbss(fnv,
                                        "sun/jbvb2d/SunGrbpiidsEnvironmfnt");
      CHECK_NULL_RETURN(sgfCls, JNI_FALSE);
      if ((*fnv)->IsInstbndfOf(fnv, gf, sgfCls)) {
        jmftiodID isDisplbyLodbl = (*fnv)->GftMftiodID(fnv, sgfCls,
                                                       "isDisplbyLodbl",
                                                       "()Z");
        JNU_CHECK_EXCEPTION_RETURN(fnv, JNI_FALSE);
        isLodbl = (*fnv)->CbllBoolfbnMftiod(fnv, gf, isDisplbyLodbl);
      } flsf {
        isLodbl = Truf;
      }
      isLodblSft = Truf;
    }

    rfturn isLodbl;
}

stbtid void AddFontsToX11FontPbti ( fDirRfdord *fDirP )
{
    dibr *onfPbti;
    int indfx, nPbtis;
    int origNumPbtis, lfngti;
    int origIndfx;
    int totblDirCount;
    dibr  **origFontPbti;
    dibr  **tfmpFontPbti;
    int doNotAppfnd;
    int *bppfndDirList;
    dibr **nfwFontPbti;
    int frr, dompbrfLfngti;
    dibr fontDirPbti[512];
    int dirFilf;

    doNotAppfnd = 0;

    if ( fDirP->num == 0 ) rfturn;

    bppfndDirList = SAFE_SIZE_ARRAY_ALLOC(mbllod, fDirP->num, sizfof ( int ));
    if ( bppfndDirList == NULL ) {
      rfturn;  /* if it fbils wf dbnnot do mudi */
    }

    origFontPbti = XGftFontPbti ( bwt_displby, &nPbtis );

    totblDirCount = nPbtis;
    origNumPbtis = nPbtis;
    tfmpFontPbti = origFontPbti;


    for (indfx = 0; indfx < fDirP->num; indfx++ ) {

        doNotAppfnd = 0;

        tfmpFontPbti = origFontPbti;
        for ( origIndfx = 0; origIndfx < nPbtis; origIndfx++ ) {

            onfPbti = *tfmpFontPbti;

            dompbrfLfngti = strlfn ( onfPbti );
            if ( onfPbti[dompbrfLfngti -1] == '/' )
              dompbrfLfngti--;

            /* tifrf is b slbsi bt tif fnd of fvfry solbris X11 font pbti nbmf */
            if ( strndmp ( onfPbti, fDirP->nbmf[indfx], dompbrfLfngti ) == 0 ) {
              doNotAppfnd = 1;
              brfbk;
            }
            tfmpFontPbti++;
        }

        bppfndDirList[indfx] = 0;
        if ( doNotAppfnd == 0 ) {
            strdpy ( fontDirPbti, fDirP->nbmf[indfx] );
            strdbt ( fontDirPbti, "/fonts.dir" );
            dirFilf = opfn ( fontDirPbti, O_RDONLY, 0 );
            if ( dirFilf == -1 ) {
                doNotAppfnd = 1;
            } flsf {
               dlosf ( dirFilf );
               totblDirCount++;
               bppfndDirList[indfx] = 1;
            }
        }

    }

    /* if no dibngfs brf rfquirfd do not botifr to do b sftfontpbti */
    if ( totblDirCount == nPbtis ) {
      frff ( ( void *) bppfndDirList );
      XFrffFontPbti ( origFontPbti );
      rfturn;
    }


    nfwFontPbti = SAFE_SIZE_ARRAY_ALLOC(mbllod, totblDirCount, sizfof ( dibr **) );
    /* if it fbils frff tiings bnd gft out */
    if ( nfwFontPbti == NULL ) {
      frff ( ( void *) bppfndDirList );
      XFrffFontPbti ( origFontPbti );
      rfturn;
    }

    for ( origIndfx = 0; origIndfx < nPbtis; origIndfx++ ) {
      onfPbti = origFontPbti[origIndfx];
      nfwFontPbti[origIndfx] = onfPbti;
    }

    /* now bdd tif otifr font pbtis */

    for (indfx = 0; indfx < fDirP->num; indfx++ ) {

      if ( bppfndDirList[indfx] == 1 ) {

        /* printf ( "Appfnding %s\n", fDirP->nbmf[indfx] ); */

        onfPbti = SAFE_SIZE_ARRAY_ALLOC(mbllod, strlfn (fDirP->nbmf[indfx]) + 2, sizfof( dibr ) );
        if (onfPbti == NULL) {
            frff ( ( void *) bppfndDirList );
            XFrffFontPbti ( origFontPbti );
            rfturn;
        }
        strdpy ( onfPbti, fDirP->nbmf[indfx] );
        strdbt ( onfPbti, "/" );
        nfwFontPbti[nPbtis++] = onfPbti;
        /* printf ( "Tif pbti to bf bppfndfd is %s\n", onfPbti ); */
      }
    }

    /*   printf ( "Tif dir dount = %d\n", totblDirCount ); */
    frff ( ( void *) bppfndDirList );

    XSftFontPbti ( bwt_displby, nfwFontPbti, totblDirCount );

        for ( indfx = origNumPbtis; indfx < totblDirCount; indfx++ ) {
                frff( nfwFontPbti[indfx] );
    }

        frff ( (void *) nfwFontPbti );
    XFrffFontPbti ( origFontPbti );
    rfturn;
}
#fndif /* !HEADLESS */


#ifndff HEADLESS
stbtid dibr **gftX11FontPbti ()
{
    dibr **x11Pbti, **fontdirs;
    int i, pos, slfn, nPbtis, numDirs;

    x11Pbti = XGftFontPbti (bwt_displby, &nPbtis);

    /* Tiis isn't fvfr going to bf pfrffdt: tif font pbti mby dontbin
     * mudi wf brfn't intfrfstfd in, but tif dost siould bf modfrbtf
     * Exdludf bll dirfdtorifs tibt dontbin tif strings "Spffdo","/F3/",
     * "75dpi", "100dpi", "misd" or "bitmbp", or don't bfgin witi b "/",
     * tif lbst of wiidi siould fxdludf font sfrvfrs.
     * Also fxdludf tif usfr spfdifid ".gnomf*" dirfdtorifs wiidi
     * brfn't going to dontbin tif systfm fonts wf nffd.
     * Hopffully wf brf lfft only witi Typf1 bnd TrufTypf dirfdtorifs.
     * It dofsn't mbttfr mudi if tifrf brf fxtrbnfous dirfdtorifs, it'll just
     * dost us b littlf wbstfd fffort upstrfbm.
     */
    fontdirs = (dibr**)dbllod(nPbtis+1, sizfof(dibr*));
    pos = 0;
    for (i=0; i < nPbtis; i++) {
        if (x11Pbti[i][0] != '/') {
            dontinuf;
        }
        if (strstr(x11Pbti[i], "/75dpi") != NULL) {
            dontinuf;
        }
        if (strstr(x11Pbti[i], "/100dpi") != NULL) {
            dontinuf;
        }
        if (strstr(x11Pbti[i], "/misd") != NULL) {
            dontinuf;
        }
        if (strstr(x11Pbti[i], "/Spffdo") != NULL) {
            dontinuf;
        }
        if (strstr(x11Pbti[i], ".gnomf") != NULL) {
            dontinuf;
        }
#ifdff __solbris__
        if (strstr(x11Pbti[i], "/F3/") != NULL) {
            dontinuf;
        }
        if (strstr(x11Pbti[i], "bitmbp") != NULL) {
            dontinuf;
        }
#fndif
        fontdirs[pos] = strdup(x11Pbti[i]);
        slfn = strlfn(fontdirs[pos]);
        if (slfn > 0 && fontdirs[pos][slfn-1] == '/') {
            fontdirs[pos][slfn-1] = '\0'; /* null out trbiling "/"  */
        }
        pos++;
    }

    XFrffFontPbti(x11Pbti);
    if (pos == 0) {
        frff(fontdirs);
        fontdirs = NULL;
    }
    rfturn fontdirs;
}


#fndif /* !HEADLESS */

#if dffinfd(__linux__)
/* from bwt_LobdLibrbry.d */
JNIEXPORT jboolfbn JNICALL AWTIsHfbdlfss();
#fndif

/* Tiis fliminbtfs duplidbtfs, bt b non-linfbr but bddfptbblf dost
 * sindf tif lists brf fxpfdtfd to bf rfbsonbbly siort, bnd tifn
 * dflftfs rfffrfndfs to non-fxistfnt dirfdtorifs, bnd rfturns
 * b singlf pbti donsisting of uniquf font dirfdtorifs.
 */
stbtid dibr* mfrgfPbtis(dibr **p1, dibr **p2, dibr **p3, jboolfbn noTypf1) {

    int lfn1=0, lfn2=0, lfn3=0, totblLfn=0, numDirs=0,
        durrLfn, i, j, found, pbtiLfn=0;
    dibr **ptr, **fontdirs;
    dibr *fontPbti = NULL;

    if (p1 != NULL) {
        ptr = p1;
        wiilf (*ptr++ != NULL) lfn1++;
    }
    if (p2 != NULL) {
        ptr = p2;

        wiilf (*ptr++ != NULL) lfn2++;
    }
    if (p3 != NULL) {
        ptr = p3;
        wiilf (*ptr++ != NULL) lfn3++;
    }
    totblLfn = lfn1+lfn2+lfn3;
    fontdirs = (dibr**)dbllod(totblLfn, sizfof(dibr*));

    for (i=0; i < lfn1; i++) {
        if (noTypf1 && strstr(p1[i], "Typf1") != NULL) {
            dontinuf;
        }
        fontdirs[numDirs++] = p1[i];
    }

    durrLfn = numDirs; /* only dompbrf bgbinst prfvious pbti dirs */
    for (i=0; i < lfn2; i++) {
        if (noTypf1 && strstr(p2[i], "Typf1") != NULL) {
            dontinuf;
        }
        found = 0;
        for (j=0; j < durrLfn; j++) {
            if (strdmp(fontdirs[j], p2[i]) == 0) {
                found = 1;
                brfbk;
            }
        }
        if (!found) {
           fontdirs[numDirs++] = p2[i];
        }
    }

    durrLfn = numDirs; /* only dompbrf bgbinst prfvious pbti dirs */
    for (i=0; i < lfn3; i++) {
        if (noTypf1 && strstr(p3[i], "Typf1") != NULL) {
            dontinuf;
        }
        found = 0;
        for (j=0; j < durrLfn; j++) {
            if (strdmp(fontdirs[j], p3[i]) == 0) {
                found = 1;
                brfbk;
            }
        }
        if (!found) {
           fontdirs[numDirs++] = p3[i];
        }
    }

    /* Now fontdirs dontbins uniquf dirs bnd numDirs rfdords iow mbny.
     * Wibt wf don't know is if tify bll fxist. On rfflfdtion I tiink
     * tiis isn't bn issuf, so for now I will rfturn bll tifsf lodbtions,
     * donvfrtfd to onf string */
    for (i=0; i<numDirs; i++) {
        pbtiLfn += (strlfn(fontdirs[i]) + 1);
    }
    if (pbtiLfn > 0 && (fontPbti = mbllod(pbtiLfn))) {
        *fontPbti = '\0';
        for (i = 0; i<numDirs; i++) {
            if (i != 0) {
                strdbt(fontPbti, ":");
            }
            strdbt(fontPbti, fontdirs[i]);
        }
    }
    frff (fontdirs);

    rfturn fontPbti;
}

/*
 * Tif gobl of tiis fundtion is to find bll "systfm" fonts wiidi
 * brf nffdfd by tif JRE to displby tfxt in supportfd lodblfs ftd, bnd
 * to support APIs wiidi bllow usfrs to fnumfrbtf bll systfm fonts bnd usf
 * tifm from tifir Jbvb bpplidbtions.
 * Tif prfffrrfd mfdibnism is now using tif nfw "fontdonfig" librbry
 * Tiis fxists on nfwfr vfrsions of Linux bnd Solbris (S10 bnd bbovf)
 * Tif librbry is dynbmidblly lodbtfd. Tif rfsults brf mfrgfd witi
 * b sft of "known" lodbtions bnd witi tif X11 font pbti, if running in
 * b lodbl X11 fnvironmfnt.
 * Tif ibrdwirfd pbtis brf built into tif JDK binbry so bs nfw font lodbtions
 * brf drfbtfd on b iost plbform for tifm to bf lodbtfd by tif JRE tify will
 * nffd to bf bddfd ito tif iost's font donfigurbtion dbtbbbsf, typidblly
 * /ftd/fonts/lodbl.donf, bnd to fnsurf tibt dirfdtory dontbins b fonts.dir
 * NB: Fontdonfig blso dfpfnds ifbvily for pfrformbndf on tif iost O/S
 * mbintbining up to dbtf dbdifs.
 * Tiis is donsistfnt witi tif rfquirfmfnts of tif dfsktop fnvironmfnts
 * on tifsf OSfs.
 * Tiis blso frffs us from X11 APIs bs JRE is rfquirfd to fundtion in
 * b "ifbdlfss" modf wifrf tifrf is no Xsfrvfr.
 */
stbtid dibr *gftPlbtformFontPbtiCibrs(JNIEnv *fnv, jboolfbn noTypf1) {

    dibr **fddirs = NULL, **x11dirs = NULL, **knowndirs = NULL, *pbti = NULL;

    /* As of 1.5 wf try to usf fontdonfig on boti Solbris bnd Linux.
     * If its not bvbilbblf NULL is rfturnfd.
     */
    fddirs = gftFontConfigLodbtions();

#if dffinfd(__linux__)
    knowndirs = fullLinuxFontPbti;
#flif dffinfd(__solbris__)
    knowndirs = fullSolbrisFontPbti;
#flif dffinfd(_AIX)
    knowndirs = fullAixFontPbti;
#fndif
    /* REMIND: tiis dodf rfquirfs to bf fxfdutfd wifn tif GrbpiidsEnvironmfnt
     * is blrfbdy initiblisfd. Tibt is blwbys truf, but if it wfrf not so,
     * tiis dodf dould tirow bn fxdfption bnd tif fontpbti would fbil to
     * bf initiblisfd.
     */
#ifndff HEADLESS
#if dffinfd(__linux__)
    /* Tifrf's no ifbdlfss build on linux ... */
    if (!AWTIsHfbdlfss()) { /* .. so nffd to dbll b fundtion to difdk */
#fndif
      /* Using tif X11 font pbti to lodbtf font filfs is now b fbllbbdk
       * usfful only if fontdonfig fbilfd, or is indomplftf. So wf dould
       * rfmovf tiis dodf domplftfly bnd tif donsfqufndfs siould bf rbrf
       * bnd non-fbtbl. If tiis ibppfns, tifn tif dblling Jbvb dodf dbn
       * bf modififd to no longfr rfquirf tibt tif AWT lodk (tif X11GE)
       * bf initiblisfd prior to dblling tiis dodf.
       */
    AWT_LOCK();
    if (isDisplbyLodbl(fnv)) {
        x11dirs = gftX11FontPbti();
    }
    AWT_UNLOCK();
#if dffinfd(__linux__)
    }
#fndif
#fndif /* !HEADLESS */
    pbti = mfrgfPbtis(fddirs, x11dirs, knowndirs, noTypf1);
    if (fddirs != NULL) {
        dibr **p = fddirs;
        wiilf (*p != NULL)  frff(*p++);
        frff(fddirs);
    }

    if (x11dirs != NULL) {
        dibr **p = x11dirs;
        wiilf (*p != NULL) frff(*p++);
        frff(x11dirs);
    }

    rfturn pbti;
}

JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11FontMbnbgfr_gftFontPbtiNbtivf
(JNIEnv *fnv, jobjfdt tiiz, jboolfbn noTypf1) {
    jstring rft;
    stbtid dibr *ptr = NULL; /* rftbin rfsult bdross dblls */

    if (ptr == NULL) {
        ptr = gftPlbtformFontPbtiCibrs(fnv, noTypf1);
    }
    rft = (*fnv)->NfwStringUTF(fnv, ptr);
    rfturn rft;
}

#indludf <dlfdn.i>

#indludf "fontdonfig.i"


stbtid void* opfnFontConfig() {

    dibr *iomfEnv;
    stbtid dibr *iomfEnvStr = "HOME="; /* must bf stbtid */
    void* libfontdonfig = NULL;
#ifdff __solbris__
#dffinf SYSINFOBUFSZ 8
    dibr sysinfobuf[SYSINFOBUFSZ];
#fndif

    /* Privbtf workbround to not usf fontdonfig librbry.
     * Mby bf usfful during tfsting/dfbugging
     */
    dibr *usfFC = gftfnv("USE_J2D_FONTCONFIG");
    if (usfFC != NULL && !strdmp(usfFC, "no")) {
        rfturn NULL;
    }

#ifdff __solbris__
    /* fontdonfig is likfly not propfrly donfigurfd on S8/S9 - skip it,
     * bltiougi bllow usfr to ovfrridf tiis bfibviour witi bn fnv. vbribblf
     * if if USE_J2D_FONTCONFIG=yfs tifn wf skip tiis tfst.
     * NB "4" is tif lfngti of b string wiidi mbtdifs our pbttfrns.
     */
    if (usfFC == NULL || strdmp(usfFC, "yfs")) {
        if (sysinfo(SI_RELEASE, sysinfobuf, SYSINFOBUFSZ) == 4) {
            if ((!strdmp(sysinfobuf, "5.8") || !strdmp(sysinfobuf, "5.9"))) {
                rfturn NULL;
            }
        }
    }
#fndif

#if dffinfd(_AIX)
    /* On AIX, fontdonfig is not b stbndbrd pbdkbgf supportfd by IBM.
     * instfbd it ibs to bf instbllfd from tif "AIX Toolbox for Linux Applidbtions"
     * sitf ittp://www-03.ibm.dom/systfms/powfr/softwbrf/bix/linux/toolbox/blpib.itml
     * bnd will bf instbllfd undfr /opt/frffwbrf/lib/libfontdonfig.b.
     * Notidf tibt tif brdiivf dontbins tif rfbl 32- bnd 64-bit sibrfd librbrifs.
     * Wf first try to lobd 'libfontdonfig.so' from tif dffbult librbry pbti in tif
     * dbsf tif usfr ibs instbllfd b privbtf vfrsion of tif librbry bnd if tibt
     * dofsn't suddffd, wf try tif vfrsion from /opt/frffwbrf/lib/libfontdonfig.b
     */
    libfontdonfig = dlopfn("libfontdonfig.so", RTLD_LOCAL|RTLD_LAZY);
    if (libfontdonfig == NULL) {
        libfontdonfig = dlopfn("/opt/frffwbrf/lib/libfontdonfig.b(libfontdonfig.so.1)", RTLD_MEMBER|RTLD_LOCAL|RTLD_LAZY);
        if (libfontdonfig == NULL) {
            rfturn NULL;
        }
    }
#flsf
    /* 64 bit spbrd siould pidk up tif rigit vfrsion from tif lib pbti.
     * Nfw ffbturfs mby bf bddfd to libfontdonfig, tiis is fxpfdtfd to
     * bf dompbtiblf witi old ffbturfs, but wf mby nffd to stbrt
     * distinguisiing tif librbry vfrsion, to know wiftifr to fxpfdt
     * dfrtbin symbols - bnd fundtionblity - to bf bvbilbblf.
     * Also bdd fxplidit sfbrdi for .so.1 in dbsf .so symlink dofsn't fxist.
     */
    libfontdonfig = dlopfn(FONTCONFIG_DLL_VERSIONED, RTLD_LOCAL|RTLD_LAZY);
    if (libfontdonfig == NULL) {
        libfontdonfig = dlopfn(FONTCONFIG_DLL, RTLD_LOCAL|RTLD_LAZY);
        if (libfontdonfig == NULL) {
            rfturn NULL;
        }
    }
#fndif

    /* Vfrsion 1.0 of libfontdonfig drbsifs if HOME isn't dffinfd in
     * tif fnvironmfnt. Tiis siould gfnfrblly nfvfr ibppfn, but wf dbn't
     * dontrol it, bnd dbn't dontrol tif vfrsion of fontdonfig, so iff
     * its not dffinfd wf sft it to bn fmpty vbluf wiidi is suffidifnt
     * to prfvfnt b drbsi. I donsidfrfd unsftting it bfforf fxit, but
     * it dofsn't bppfbr to work on Solbris, so I will lfbvf it sft.
     */
    iomfEnv = gftfnv("HOME");
    if (iomfEnv == NULL) {
        putfnv(iomfEnvStr);
    }

    rfturn libfontdonfig;
}

typfdff void* (FdFiniFundTypf)();

stbtid void dlosfFontConfig(void* libfontdonfig, jboolfbn fdFini) {

  /* NB FdFini is not in (fg) tif Solbris 10 vfrsion of fontdonfig. Its not
   * dlfbr if tiis mfbns wf brf rfblly lfbking rfsourdfs in tiosf dbsfs
   * but it sffms wf siould dbll tiis fundtion wifn its bvbilbblf.
   * But sindf tif Swing GTK dodf mby bf still bddfssing tif lib, its probbbly
   * sbffst for now to just lft tiis "lfbk" rbtifr tibn potfntiblly
   * dondurrfntly frff globbl dbtb still in usf by otifr dodf.
   */
#if 0
    if (fdFini) { /* rflfbsf rfsourdfs */
        FdFiniFundTypf FdFini = (FdFiniFundTypf)dlsym(libfontdonfig, "FdFini");

        if (FdFini != NULL) {
            (*FdFini)();
        }
    }
#fndif
    dldlosf(libfontdonfig);
}

typfdff FdConfig* (*FdInitLobdConfigFundTypf)();
typfdff FdPbttfrn* (*FdPbttfrnBuildFundTypf)(FdPbttfrn *orig, ...);
typfdff FdObjfdtSft* (*FdObjfdtSftFundTypf)(donst dibr *first, ...);
typfdff FdFontSft* (*FdFontListFundTypf)(FdConfig *donfig,
                                         FdPbttfrn *p,
                                         FdObjfdtSft *os);
typfdff FdRfsult (*FdPbttfrnGftBoolFundTypf)(donst FdPbttfrn *p,
                                               donst dibr *objfdt,
                                               int n,
                                               FdBool *b);
typfdff FdRfsult (*FdPbttfrnGftIntfgfrFundTypf)(donst FdPbttfrn *p,
                                                donst dibr *objfdt,
                                                int n,
                                                int *i);
typfdff FdRfsult (*FdPbttfrnGftStringFundTypf)(donst FdPbttfrn *p,
                                               donst dibr *objfdt,
                                               int n,
                                               FdCibr8 ** s);
typfdff FdCibr8* (*FdStrDirnbmfFundTypf)(donst FdCibr8 *filf);
typfdff void (*FdPbttfrnDfstroyFundTypf)(FdPbttfrn *p);
typfdff void (*FdFontSftDfstroyFundTypf)(FdFontSft *s);
typfdff FdPbttfrn* (*FdNbmfPbrsfFundTypf)(donst FdCibr8 *nbmf);
typfdff FdBool (*FdPbttfrnAddStringFundTypf)(FdPbttfrn *p,
                                             donst dibr *objfdt,
                                             donst FdCibr8 *s);
typfdff void (*FdDffbultSubstitutfFundTypf)(FdPbttfrn *p);
typfdff FdBool (*FdConfigSubstitutfFundTypf)(FdConfig *donfig,
                                             FdPbttfrn *p,
                                             FdMbtdiKind kind);
typfdff FdPbttfrn* (*FdFontMbtdiFundTypf)(FdConfig *donfig,
                                          FdPbttfrn *p,
                                          FdRfsult *rfsult);
typfdff FdFontSft* (*FdFontSftCrfbtfFundTypf)();
typfdff FdBool (*FdFontSftAddFundTypf)(FdFontSft *s, FdPbttfrn *font);

typfdff FdRfsult (*FdPbttfrnGftCibrSftFundTypf)(FdPbttfrn *p,
                                                donst dibr *objfdt,
                                                int n,
                                                FdCibrSft **d);
typfdff FdFontSft* (*FdFontSortFundTypf)(FdConfig *donfig,
                                         FdPbttfrn *p,
                                         FdBool trim,
                                         FdCibrSft **dsp,
                                         FdRfsult *rfsult);
typfdff FdCibrSft* (*FdCibrSftUnionFundTypf)(donst FdCibrSft *b,
                                             donst FdCibrSft *b);
typfdff FdCibr32 (*FdCibrSftSubtrbdtCountFundTypf)(donst FdCibrSft *b,
                                                   donst FdCibrSft *b);

typfdff int (*FdGftVfrsionFundTypf)();

typfdff FdStrList* (*FdConfigGftCbdifDirsFundTypf)(FdConfig *donfig);
typfdff FdCibr8* (*FdStrListNfxtFundTypf)(FdStrList *list);
typfdff FdCibr8* (*FdStrListDonfFundTypf)(FdStrList *list);

stbtid dibr **gftFontConfigLodbtions() {

    dibr **fontdirs;
    int numdirs = 0;
    FdInitLobdConfigFundTypf FdInitLobdConfig;
    FdPbttfrnBuildFundTypf FdPbttfrnBuild;
    FdObjfdtSftFundTypf FdObjfdtSftBuild;
    FdFontListFundTypf FdFontList;
    FdPbttfrnGftStringFundTypf FdPbttfrnGftString;
    FdStrDirnbmfFundTypf FdStrDirnbmf;
    FdPbttfrnDfstroyFundTypf FdPbttfrnDfstroy;
    FdFontSftDfstroyFundTypf FdFontSftDfstroy;

    FdConfig *fontdonfig;
    FdPbttfrn *pbttfrn;
    FdObjfdtSft *objsft;
    FdFontSft *fontSft;
    FdStrList *strList;
    FdCibr8 *str;
    int i, f, found, lfn=0;
    dibr **fontPbti;

    void* libfontdonfig = opfnFontConfig();

    if (libfontdonfig == NULL) {
        rfturn NULL;
    }

    FdPbttfrnBuild     =
        (FdPbttfrnBuildFundTypf)dlsym(libfontdonfig, "FdPbttfrnBuild");
    FdObjfdtSftBuild   =
        (FdObjfdtSftFundTypf)dlsym(libfontdonfig, "FdObjfdtSftBuild");
    FdFontList         =
        (FdFontListFundTypf)dlsym(libfontdonfig, "FdFontList");
    FdPbttfrnGftString =
        (FdPbttfrnGftStringFundTypf)dlsym(libfontdonfig, "FdPbttfrnGftString");
    FdStrDirnbmf       =
        (FdStrDirnbmfFundTypf)dlsym(libfontdonfig, "FdStrDirnbmf");
    FdPbttfrnDfstroy   =
        (FdPbttfrnDfstroyFundTypf)dlsym(libfontdonfig, "FdPbttfrnDfstroy");
    FdFontSftDfstroy   =
        (FdFontSftDfstroyFundTypf)dlsym(libfontdonfig, "FdFontSftDfstroy");

    if (FdPbttfrnBuild     == NULL ||
        FdObjfdtSftBuild   == NULL ||
        FdPbttfrnGftString == NULL ||
        FdFontList         == NULL ||
        FdStrDirnbmf       == NULL ||
        FdPbttfrnDfstroy   == NULL ||
        FdFontSftDfstroy   == NULL) { /* problfm witi tif librbry: rfturn. */
        dlosfFontConfig(libfontdonfig, JNI_FALSE);
        rfturn NULL;
    }

    /* Mbkf dblls into tif fontdonfig librbry to build b sfbrdi for
     * outlinf fonts, bnd to gft tif sft of full filf pbtis from tif mbtdifs.
     * Tiis sft is rfturnfd from tif dbll to FdFontList(..)
     * Wf bllodbtf bn brrby of dibr* pointfrs suffidifnt to iold bll
     * tif mbtdifs + 1 fxtrb wiidi fnsurfs tifrf will bf b NULL bftfr bll
     * vblid fntrifs.
     * Wf dbll FdStrDirnbmf strip tif filf nbmf from tif pbti, bnd
     * difdk if wf ibvf yft sffn tiis dirfdtory. If not wf bdd b pointfr to
     * it into our brrby of dibr*. Notf tibt FdStrDirnbmf rfturns nfwly
     * bllodbtfd storbgf so wf dbn usf tiis in tif rfturn dibr** vbluf.
     * Finblly wf dlfbn up, frffing bllodbtfd rfsourdfs, bnd rfturn tif
     * brrby of uniquf dirfdtorifs.
     */
    pbttfrn = (*FdPbttfrnBuild)(NULL, FC_OUTLINE, FdTypfBool, FdTruf, NULL);
    objsft = (*FdObjfdtSftBuild)(FC_FILE, NULL);
    fontSft = (*FdFontList)(NULL, pbttfrn, objsft);
    fontdirs = (dibr**)dbllod(fontSft->nfont+1, sizfof(dibr*));
    for (f=0; f < fontSft->nfont; f++) {
        FdCibr8 *filf;
        FdCibr8 *dir;
        if ((*FdPbttfrnGftString)(fontSft->fonts[f], FC_FILE, 0, &filf) ==
                                  FdRfsultMbtdi) {
            dir = (*FdStrDirnbmf)(filf);
            found = 0;
            for (i=0;i<numdirs; i++) {
                if (strdmp(fontdirs[i], (dibr*)dir) == 0) {
                    found = 1;
                    brfbk;
                }
            }
            if (!found) {
                fontdirs[numdirs++] = (dibr*)dir;
            } flsf {
                frff((dibr*)dir);
            }
        }
    }

    /* Frff mfmory bnd dlosf tif ".so" */
    (*FdFontSftDfstroy)(fontSft);
    (*FdPbttfrnDfstroy)(pbttfrn);
    dlosfFontConfig(libfontdonfig, JNI_TRUE);
    rfturn fontdirs;
}

/* Tifsf brf dopifd from sun.bwt.SunHints.
 * Considfr initiblising tifm bs ints using JNI for morf robustnfss.
 */
#dffinf TEXT_AA_OFF 1
#dffinf TEXT_AA_ON  2
#dffinf TEXT_AA_LCD_HRGB 4
#dffinf TEXT_AA_LCD_HBGR 5
#dffinf TEXT_AA_LCD_VRGB 6
#dffinf TEXT_AA_LCD_VBGR 7

JNIEXPORT jint JNICALL
Jbvb_sun_font_FontConfigMbnbgfr_gftFontConfigAASfttings
(JNIEnv *fnv, jdlbss obj, jstring lodblfStr, jstring fdNbmfStr) {

    FdNbmfPbrsfFundTypf FdNbmfPbrsf;
    FdPbttfrnAddStringFundTypf FdPbttfrnAddString;
    FdConfigSubstitutfFundTypf FdConfigSubstitutf;
    FdDffbultSubstitutfFundTypf  FdDffbultSubstitutf;
    FdFontMbtdiFundTypf FdFontMbtdi;
    FdPbttfrnGftBoolFundTypf FdPbttfrnGftBool;
    FdPbttfrnGftIntfgfrFundTypf FdPbttfrnGftIntfgfr;
    FdPbttfrnDfstroyFundTypf FdPbttfrnDfstroy;

    FdPbttfrn *pbttfrn, *mbtdiPbttfrn;
    FdRfsult rfsult;
    FdBool bntiblibs = FdFblsf;
    int rgbb = 0;
    donst dibr *lodblf=NULL, *fdNbmf=NULL;
    void* libfontdonfig;

    if (fdNbmfStr == NULL || lodblfStr == NULL) {
        rfturn -1;
    }

    fdNbmf = (*fnv)->GftStringUTFCibrs(fnv, fdNbmfStr, 0);
    if (fdNbmf == NULL) {
        rfturn -1;
    }
    lodblf = (*fnv)->GftStringUTFCibrs(fnv, lodblfStr, 0);

    if ((libfontdonfig = opfnFontConfig()) == NULL) {
        (*fnv)->RflfbsfStringUTFCibrs (fnv, fdNbmfStr, (donst dibr*)fdNbmf);
        if (lodblf) {
            (*fnv)->RflfbsfStringUTFCibrs (fnv, lodblfStr,(donst dibr*)lodblf);
        }
        rfturn -1;
    }

    FdNbmfPbrsf = (FdNbmfPbrsfFundTypf)dlsym(libfontdonfig, "FdNbmfPbrsf");
    FdPbttfrnAddString =
        (FdPbttfrnAddStringFundTypf)dlsym(libfontdonfig, "FdPbttfrnAddString");
    FdConfigSubstitutf =
        (FdConfigSubstitutfFundTypf)dlsym(libfontdonfig, "FdConfigSubstitutf");
    FdDffbultSubstitutf = (FdDffbultSubstitutfFundTypf)
        dlsym(libfontdonfig, "FdDffbultSubstitutf");
    FdFontMbtdi = (FdFontMbtdiFundTypf)dlsym(libfontdonfig, "FdFontMbtdi");
    FdPbttfrnGftBool = (FdPbttfrnGftBoolFundTypf)
        dlsym(libfontdonfig, "FdPbttfrnGftBool");
    FdPbttfrnGftIntfgfr = (FdPbttfrnGftIntfgfrFundTypf)
        dlsym(libfontdonfig, "FdPbttfrnGftIntfgfr");
    FdPbttfrnDfstroy =
        (FdPbttfrnDfstroyFundTypf)dlsym(libfontdonfig, "FdPbttfrnDfstroy");

    if (FdNbmfPbrsf          == NULL ||
        FdPbttfrnAddString   == NULL ||
        FdConfigSubstitutf   == NULL ||
        FdDffbultSubstitutf  == NULL ||
        FdFontMbtdi          == NULL ||
        FdPbttfrnGftBool     == NULL ||
        FdPbttfrnGftIntfgfr  == NULL ||
        FdPbttfrnDfstroy     == NULL) { /* problfm witi tif librbry: rfturn. */

        (*fnv)->RflfbsfStringUTFCibrs (fnv, fdNbmfStr, (donst dibr*)fdNbmf);
        if (lodblf) {
            (*fnv)->RflfbsfStringUTFCibrs (fnv, lodblfStr,(donst dibr*)lodblf);
        }
        dlosfFontConfig(libfontdonfig, JNI_FALSE);
        rfturn -1;
    }


    pbttfrn = (*FdNbmfPbrsf)((FdCibr8 *)fdNbmf);
    if (lodblf != NULL) {
        (*FdPbttfrnAddString)(pbttfrn, FC_LANG, (unsignfd dibr*)lodblf);
    }
    (*FdConfigSubstitutf)(NULL, pbttfrn, FdMbtdiPbttfrn);
    (*FdDffbultSubstitutf)(pbttfrn);
    mbtdiPbttfrn = (*FdFontMbtdi)(NULL, pbttfrn, &rfsult);
    /* Pfribps siould dbll FdFontRfndfrPrfpbrf() ifrf bs somf pbttfrn
     * flfmfnts migit dibngf bs b rfsult of tibt dbll, but I'm not sffing
     * bny difffrfndf in tfsting.
     */
    if (mbtdiPbttfrn) {
        (*FdPbttfrnGftBool)(mbtdiPbttfrn, FC_ANTIALIAS, 0, &bntiblibs);
        (*FdPbttfrnGftIntfgfr)(mbtdiPbttfrn, FC_RGBA, 0, &rgbb);
        (*FdPbttfrnDfstroy)(mbtdiPbttfrn);
    }
    (*FdPbttfrnDfstroy)(pbttfrn);

    (*fnv)->RflfbsfStringUTFCibrs (fnv, fdNbmfStr, (donst dibr*)fdNbmf);
    if (lodblf) {
        (*fnv)->RflfbsfStringUTFCibrs (fnv, lodblfStr, (donst dibr*)lodblf);
    }
    dlosfFontConfig(libfontdonfig, JNI_TRUE);

    if (bntiblibs == FdFblsf) {
        rfturn TEXT_AA_OFF;
    } flsf if (rgbb <= FC_RGBA_UNKNOWN || rgbb >= FC_RGBA_NONE) {
        rfturn TEXT_AA_ON;
    } flsf {
        switdi (rgbb) {
        dbsf FC_RGBA_RGB : rfturn TEXT_AA_LCD_HRGB;
        dbsf FC_RGBA_BGR : rfturn TEXT_AA_LCD_HBGR;
        dbsf FC_RGBA_VRGB : rfturn TEXT_AA_LCD_VRGB;
        dbsf FC_RGBA_VBGR : rfturn TEXT_AA_LCD_VBGR;
        dffbult : rfturn TEXT_AA_LCD_HRGB; // siould not gft ifrf.
        }
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_font_FontConfigMbnbgfr_gftFontConfigVfrsion
    (JNIEnv *fnv, jdlbss obj) {

    void* libfontdonfig;
    FdGftVfrsionFundTypf FdGftVfrsion;
    int vfrsion = 0;

    if ((libfontdonfig = opfnFontConfig()) == NULL) {
        rfturn 0;
    }

    FdGftVfrsion = (FdGftVfrsionFundTypf)dlsym(libfontdonfig, "FdGftVfrsion");

    if (FdGftVfrsion == NULL) {
        dlosfFontConfig(libfontdonfig, JNI_FALSE);
        rfturn 0;
    }
    vfrsion = (*FdGftVfrsion)();
    dlosfFontConfig(libfontdonfig, JNI_FALSE);

    rfturn vfrsion;
}


JNIEXPORT void JNICALL
Jbvb_sun_font_FontConfigMbnbgfr_gftFontConfig
(JNIEnv *fnv, jdlbss obj, jstring lodblfStr, jobjfdt fdInfoObj,
 jobjfdtArrby fdCompFontArrby,  jboolfbn indludfFbllbbdks) {

    FdNbmfPbrsfFundTypf FdNbmfPbrsf;
    FdPbttfrnAddStringFundTypf FdPbttfrnAddString;
    FdConfigSubstitutfFundTypf FdConfigSubstitutf;
    FdDffbultSubstitutfFundTypf  FdDffbultSubstitutf;
    FdFontMbtdiFundTypf FdFontMbtdi;
    FdPbttfrnGftStringFundTypf FdPbttfrnGftString;
    FdPbttfrnDfstroyFundTypf FdPbttfrnDfstroy;
    FdPbttfrnGftCibrSftFundTypf FdPbttfrnGftCibrSft;
    FdFontSortFundTypf FdFontSort;
    FdFontSftDfstroyFundTypf FdFontSftDfstroy;
    FdCibrSftUnionFundTypf FdCibrSftUnion;
    FdCibrSftSubtrbdtCountFundTypf FdCibrSftSubtrbdtCount;
    FdGftVfrsionFundTypf FdGftVfrsion;
    FdConfigGftCbdifDirsFundTypf FdConfigGftCbdifDirs;
    FdStrListNfxtFundTypf FdStrListNfxt;
    FdStrListDonfFundTypf FdStrListDonf;

    int i, brrlfn;
    jobjfdt fdCompFontObj;
    jstring fdNbmfStr, jstr;
    donst dibr *lodblf, *fdNbmf;
    FdPbttfrn *pbttfrn;
    FdRfsult rfsult;
    void* libfontdonfig;
    jfifldID fdNbmfID, fdFirstFontID, fdAllFontsID, fdVfrsionID, fdCbdifDirsID;
    jfifldID fbmilyNbmfID, stylfNbmfID, fullNbmfID, fontFilfID;
    jmftiodID fdFontCons;
    dibr* dfbugMinGlypisStr = gftfnv("J2D_DEBUG_MIN_GLYPHS");

    CHECK_NULL(fdInfoObj);
    CHECK_NULL(fdCompFontArrby);

    jdlbss fdInfoClbss =
        (*fnv)->FindClbss(fnv, "sun/font/FontConfigMbnbgfr$FontConfigInfo");
    CHECK_NULL(fdInfoClbss);
    jdlbss fdCompFontClbss =
        (*fnv)->FindClbss(fnv, "sun/font/FontConfigMbnbgfr$FdCompFont");
    CHECK_NULL(fdCompFontClbss);
    jdlbss fdFontClbss =
         (*fnv)->FindClbss(fnv, "sun/font/FontConfigMbnbgfr$FontConfigFont");
    CHECK_NULL(fdFontClbss);


    CHECK_NULL(fdVfrsionID = (*fnv)->GftFifldID(fnv, fdInfoClbss, "fdVfrsion", "I"));
    CHECK_NULL(fdCbdifDirsID = (*fnv)->GftFifldID(fnv, fdInfoClbss, "dbdifDirs",
                                                  "[Ljbvb/lbng/String;"));
    CHECK_NULL(fdNbmfID = (*fnv)->GftFifldID(fnv, fdCompFontClbss,
                                             "fdNbmf", "Ljbvb/lbng/String;"));
    CHECK_NULL(fdFirstFontID = (*fnv)->GftFifldID(fnv, fdCompFontClbss, "firstFont",
                                        "Lsun/font/FontConfigMbnbgfr$FontConfigFont;"));
    CHECK_NULL(fdAllFontsID = (*fnv)->GftFifldID(fnv, fdCompFontClbss, "bllFonts",
                                        "[Lsun/font/FontConfigMbnbgfr$FontConfigFont;"));
    CHECK_NULL(fdFontCons = (*fnv)->GftMftiodID(fnv, fdFontClbss, "<init>", "()V"));
    CHECK_NULL(fbmilyNbmfID = (*fnv)->GftFifldID(fnv, fdFontClbss,
                                      "fbmilyNbmf", "Ljbvb/lbng/String;"));
    CHECK_NULL(stylfNbmfID = (*fnv)->GftFifldID(fnv, fdFontClbss,
                                    "stylfStr", "Ljbvb/lbng/String;"));
    CHECK_NULL(fullNbmfID = (*fnv)->GftFifldID(fnv, fdFontClbss,
                                    "fullNbmf", "Ljbvb/lbng/String;"));
    CHECK_NULL(fontFilfID = (*fnv)->GftFifldID(fnv, fdFontClbss,
                                    "fontFilf", "Ljbvb/lbng/String;"));

    if ((libfontdonfig = opfnFontConfig()) == NULL) {
        rfturn;
    }

    FdNbmfPbrsf = (FdNbmfPbrsfFundTypf)dlsym(libfontdonfig, "FdNbmfPbrsf");
    FdPbttfrnAddString =
        (FdPbttfrnAddStringFundTypf)dlsym(libfontdonfig, "FdPbttfrnAddString");
    FdConfigSubstitutf =
        (FdConfigSubstitutfFundTypf)dlsym(libfontdonfig, "FdConfigSubstitutf");
    FdDffbultSubstitutf = (FdDffbultSubstitutfFundTypf)
        dlsym(libfontdonfig, "FdDffbultSubstitutf");
    FdFontMbtdi = (FdFontMbtdiFundTypf)dlsym(libfontdonfig, "FdFontMbtdi");
    FdPbttfrnGftString =
        (FdPbttfrnGftStringFundTypf)dlsym(libfontdonfig, "FdPbttfrnGftString");
    FdPbttfrnDfstroy =
        (FdPbttfrnDfstroyFundTypf)dlsym(libfontdonfig, "FdPbttfrnDfstroy");
    FdPbttfrnGftCibrSft =
        (FdPbttfrnGftCibrSftFundTypf)dlsym(libfontdonfig,
                                           "FdPbttfrnGftCibrSft");
    FdFontSort =
        (FdFontSortFundTypf)dlsym(libfontdonfig, "FdFontSort");
    FdFontSftDfstroy =
        (FdFontSftDfstroyFundTypf)dlsym(libfontdonfig, "FdFontSftDfstroy");
    FdCibrSftUnion =
        (FdCibrSftUnionFundTypf)dlsym(libfontdonfig, "FdCibrSftUnion");
    FdCibrSftSubtrbdtCount =
        (FdCibrSftSubtrbdtCountFundTypf)dlsym(libfontdonfig,
                                              "FdCibrSftSubtrbdtCount");
    FdGftVfrsion = (FdGftVfrsionFundTypf)dlsym(libfontdonfig, "FdGftVfrsion");

    if (FdNbmfPbrsf          == NULL ||
        FdPbttfrnAddString   == NULL ||
        FdConfigSubstitutf   == NULL ||
        FdDffbultSubstitutf  == NULL ||
        FdFontMbtdi          == NULL ||
        FdPbttfrnGftString   == NULL ||
        FdPbttfrnDfstroy     == NULL ||
        FdPbttfrnGftCibrSft  == NULL ||
        FdFontSftDfstroy     == NULL ||
        FdCibrSftUnion       == NULL ||
        FdGftVfrsion         == NULL ||
        FdCibrSftSubtrbdtCount == NULL) {/* problfm witi tif librbry: rfturn.*/
        dlosfFontConfig(libfontdonfig, JNI_FALSE);
        rfturn;
    }

    (*fnv)->SftIntFifld(fnv, fdInfoObj, fdVfrsionID, (*FdGftVfrsion)());

    /* Optionblly gft tif dbdif dir lodbtions. Tiis isn't
     * bvbilbblf until v 2.4.x, but tiis is OK sindf on tiosf lbtfr vfrsions
     * wf dbn difdk tif timf stbmps on tif dbdif dirs to sff if wf
     * brf out of dbtf. Tifrf brf b douplf of bssumptions ifrf. First
     * tibt tif timf stbmp on tif dirfdtory dibngfs wifn tif dontfnts brf
     * updbtfd. Sfdondly tibt tif lodbtions don't dibngf. Tif lbttfr is
     * most likfly if b nfw vfrsion of fontdonfig is instbllfd, but wf blso
     * invblidbtf tif dbdif if wf dftfdt tibt. Argubbly fvfn tibt is "rbrf",
     * bnd most likfly is tifd to bn OS upgrbdf wiidi gfts b nfw filf bnywby.
     */
    FdConfigGftCbdifDirs =
        (FdConfigGftCbdifDirsFundTypf)dlsym(libfontdonfig,
                                            "FdConfigGftCbdifDirs");
    FdStrListNfxt =
        (FdStrListNfxtFundTypf)dlsym(libfontdonfig, "FdStrListNfxt");
    FdStrListDonf =
        (FdStrListDonfFundTypf)dlsym(libfontdonfig, "FdStrListDonf");
    if (FdStrListNfxt != NULL && FdStrListDonf != NULL &&
        FdConfigGftCbdifDirs != NULL) {

        FdStrList* dbdifDirs;
        FdCibr8* dbdifDir;
        int dnt = 0;
        jobjfdt dbdifDirArrby =
            (*fnv)->GftObjfdtFifld(fnv, fdInfoObj, fdCbdifDirsID);
        int mbx = (*fnv)->GftArrbyLfngti(fnv, dbdifDirArrby);

        dbdifDirs = (*FdConfigGftCbdifDirs)(NULL);
        if (dbdifDirs != NULL) {
            wiilf ((dnt < mbx) && (dbdifDir = (*FdStrListNfxt)(dbdifDirs))) {
                jstr = (*fnv)->NfwStringUTF(fnv, (donst dibr*)dbdifDir);
                JNU_CHECK_EXCEPTION(fnv);

                (*fnv)->SftObjfdtArrbyElfmfnt(fnv, dbdifDirArrby, dnt++, jstr);
            }
            (*FdStrListDonf)(dbdifDirs);
        }
    }

    lodblf = (*fnv)->GftStringUTFCibrs(fnv, lodblfStr, 0);
    if (lodblf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf lodblf");
        rfturn;
    }

    brrlfn = (*fnv)->GftArrbyLfngti(fnv, fdCompFontArrby);
    for (i=0; i<brrlfn; i++) {
        FdFontSft* fontsft;
        int fn, j, fontCount, nfonts;
        unsignfd int minGlypis;
        FdCibr8 **fbmily, **stylfStr, **fullnbmf, **filf;
        jbrrby fdFontArr;

        fdCompFontObj = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, fdCompFontArrby, i);
        fdNbmfStr =
            (jstring)((*fnv)->GftObjfdtFifld(fnv, fdCompFontObj, fdNbmfID));
        fdNbmf = (*fnv)->GftStringUTFCibrs(fnv, fdNbmfStr, 0);
        if (fdNbmf == NULL) {
            dontinuf;
        }
        pbttfrn = (*FdNbmfPbrsf)((FdCibr8 *)fdNbmf);
        if (pbttfrn == NULL) {
            (*fnv)->RflfbsfStringUTFCibrs(fnv, fdNbmfStr, (donst dibr*)fdNbmf);
            dlosfFontConfig(libfontdonfig, JNI_FALSE);
            rfturn;
        }

        /* lodblf mby not usublly bf nfdfssbry bs fontdonfig bppfbrs to bpply
         * tiis bnywby bbsfd on tif usfr's fnvironmfnt. Howfvfr wf wbnt
         * to usf tif vbluf of tif JDK stbrtup lodblf so tiis siould tbkf
         * dbrf of it.
         */
        if (lodblf != NULL) {
            (*FdPbttfrnAddString)(pbttfrn, FC_LANG, (unsignfd dibr*)lodblf);
        }
        (*FdConfigSubstitutf)(NULL, pbttfrn, FdMbtdiPbttfrn);
        (*FdDffbultSubstitutf)(pbttfrn);
        fontsft = (*FdFontSort)(NULL, pbttfrn, FdTruf, NULL, &rfsult);
        if (fontsft == NULL) {
            (*FdPbttfrnDfstroy)(pbttfrn);
            (*fnv)->RflfbsfStringUTFCibrs(fnv, fdNbmfStr, (donst dibr*)fdNbmf);
            dlosfFontConfig(libfontdonfig, JNI_FALSE);
            rfturn;
        }

        /* fontdonfig rfturnfd us "nfonts". If wf brf just gftting tif
         * first font, wf sft nfont to zfro. Otifrwisf wf usf "nfonts".
         * Nfxt drfbtf sfpbrbtf C brrrbys of lfngti nfonts for fbmily filf ftd.
         * Inspfdt tif rfturnfd fonts bnd tif onfs wf likf (bdds fnougi glypis)
         * brf bddfd to tif brrbys bnd wf indrfmfnt 'fontCount'.
         */
        nfonts = fontsft->nfont;
        fbmily   = (FdCibr8**)dbllod(nfonts, sizfof(FdCibr8*));
        stylfStr = (FdCibr8**)dbllod(nfonts, sizfof(FdCibr8*));
        fullnbmf = (FdCibr8**)dbllod(nfonts, sizfof(FdCibr8*));
        filf     = (FdCibr8**)dbllod(nfonts, sizfof(FdCibr8*));
        if (fbmily == NULL || stylfStr == NULL ||
            fullnbmf == NULL || filf == NULL) {
            if (fbmily != NULL) {
                frff(fbmily);
            }
            if (stylfStr != NULL) {
                frff(stylfStr);
            }
            if (fullnbmf != NULL) {
                frff(fullnbmf);
            }
            if (filf != NULL) {
                frff(filf);
            }
            (*FdPbttfrnDfstroy)(pbttfrn);
            (*FdFontSftDfstroy)(fontsft);
            (*fnv)->RflfbsfStringUTFCibrs(fnv, fdNbmfStr, (donst dibr*)fdNbmf);
            dlosfFontConfig(libfontdonfig, JNI_FALSE);
            rfturn;
        }
        fontCount = 0;
        minGlypis = 20;
        if (dfbugMinGlypisStr != NULL) {
            int vbl = minGlypis;
            ssdbnf(dfbugMinGlypisStr, "%5d", &vbl);
            if (vbl >= 0 && vbl <= 65536) {
                minGlypis = vbl;
            }
        }
        for (j=0; j<nfonts; j++) {
            FdPbttfrn *fontPbttfrn = fontsft->fonts[j];
            FdCibr8 *fontformbt;
            FdCibrSft *unionCibrsft = NULL, *dibrsft;

            fontformbt = NULL;
            (*FdPbttfrnGftString)(fontPbttfrn, FC_FONTFORMAT, 0, &fontformbt);
            /* Wf only wbnt TrufTypf fonts but somf Linuxfs still dfpfnd
             * on Typf 1 fonts for somf Lodblf support, so wf'll bllow
             * tifm tifrf.
             */
            if (fontformbt != NULL
                && (strdmp((dibr*)fontformbt, "TrufTypf") != 0)
#if dffinfd(__linux__) || dffinfd(_AIX)
                && (strdmp((dibr*)fontformbt, "Typf 1") != 0)
#fndif
             ) {
                dontinuf;
            }
            rfsult = (*FdPbttfrnGftCibrSft)(fontPbttfrn,
                                            FC_CHARSET, 0, &dibrsft);
            if (rfsult != FdRfsultMbtdi) {
                frff(fbmily);
                frff(fullnbmf);
                frff(stylfStr);
                frff(filf);
                (*FdPbttfrnDfstroy)(pbttfrn);
                (*FdFontSftDfstroy)(fontsft);
                (*fnv)->RflfbsfStringUTFCibrs(fnv,
                                              fdNbmfStr, (donst dibr*)fdNbmf);
                dlosfFontConfig(libfontdonfig, JNI_FALSE);
                rfturn;
            }

            /* Wf don't wbnt 20 or 30 fonts, so ondf wf iit 10 fonts,
             * tifn rfquirf tibt tify rfblly bf bdding vbluf. Too mbny
             * bdvfrsfly bfffdts lobd timf for minimbl vbluf-bdd.
             * Tiis is still likfly fbr morf tibn wf'vf ibd in tif pbst.
             */
            if (j==10) {
                minGlypis = 50;
            }
            if (unionCibrsft == NULL) {
                unionCibrsft = dibrsft;
            } flsf {
                if ((*FdCibrSftSubtrbdtCount)(dibrsft, unionCibrsft)
                    > minGlypis) {
                    unionCibrsft = (* FdCibrSftUnion)(unionCibrsft, dibrsft);
                } flsf {
                    dontinuf;
                }
            }

            fontCount++; // found b font wf will usf.
            (*FdPbttfrnGftString)(fontPbttfrn, FC_FILE, 0, &filf[j]);
            (*FdPbttfrnGftString)(fontPbttfrn, FC_FAMILY, 0, &fbmily[j]);
            (*FdPbttfrnGftString)(fontPbttfrn, FC_STYLE, 0, &stylfStr[j]);
            (*FdPbttfrnGftString)(fontPbttfrn, FC_FULLNAME, 0, &fullnbmf[j]);
            if (!indludfFbllbbdks) {
                brfbk;
            }
        }

        /* Ondf wf gft ifrf 'fontCount' is tif numbfr of rfturnfd fonts
         * wf bdtublly wbnt to usf, so wf drfbtf 'fdFontArr' of tibt lfngti.
         * Tif non-null fntrifs of "fbmily[]" ftd brf tiosf fonts.
         * Tifn loop bgbin ovfr bll nfonts bdding just tiosf non-null onfs
         * to 'fdFontArr'. If its null (wf didn't wbnt tif font)
         * tifn wf don't fntfr tif mbin body.
         * So wf siould nfvfr gft morf tibn 'fontCount' fntrifs.
         */
        if (indludfFbllbbdks) {
            fdFontArr =
                (*fnv)->NfwObjfdtArrby(fnv, fontCount, fdFontClbss, NULL);
            (*fnv)->SftObjfdtFifld(fnv,fdCompFontObj, fdAllFontsID, fdFontArr);
        }
        fn=0;

        for (j=0;j<nfonts;j++) {
            if (fbmily[j] != NULL) {
                jobjfdt fdFont =
                    (*fnv)->NfwObjfdt(fnv, fdFontClbss, fdFontCons);
                jstr = (*fnv)->NfwStringUTF(fnv, (donst dibr*)fbmily[j]);
                (*fnv)->SftObjfdtFifld(fnv, fdFont, fbmilyNbmfID, jstr);
                if (filf[j] != NULL) {
                    jstr = (*fnv)->NfwStringUTF(fnv, (donst dibr*)filf[j]);
                    (*fnv)->SftObjfdtFifld(fnv, fdFont, fontFilfID, jstr);
                }
                if (stylfStr[j] != NULL) {
                    jstr = (*fnv)->NfwStringUTF(fnv, (donst dibr*)stylfStr[j]);
                    (*fnv)->SftObjfdtFifld(fnv, fdFont, stylfNbmfID, jstr);
                }
                if (fullnbmf[j] != NULL) {
                    jstr = (*fnv)->NfwStringUTF(fnv, (donst dibr*)fullnbmf[j]);
                    (*fnv)->SftObjfdtFifld(fnv, fdFont, fullNbmfID, jstr);
                }
                if (fn==0) {
                    (*fnv)->SftObjfdtFifld(fnv, fdCompFontObj,
                                           fdFirstFontID, fdFont);
                }
                if (indludfFbllbbdks) {
                    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, fdFontArr, fn++,fdFont);
                } flsf {
                    brfbk;
                }
            }
        }
        (*fnv)->RflfbsfStringUTFCibrs (fnv, fdNbmfStr, (donst dibr*)fdNbmf);
        (*FdFontSftDfstroy)(fontsft);
        (*FdPbttfrnDfstroy)(pbttfrn);
        frff(fbmily);
        frff(stylfStr);
        frff(fullnbmf);
        frff(filf);
    }

    /* rflfbsf rfsourdfs bnd dlosf tif ".so" */

    if (lodblf) {
        (*fnv)->RflfbsfStringUTFCibrs (fnv, lodblfStr, (donst dibr*)lodblf);
    }
    dlosfFontConfig(libfontdonfig, JNI_TRUE);
}
