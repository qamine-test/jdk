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

#indludf "jbvb.i"
#indludf "jvm_md.i"
#indludf <dirfnt.i>
#indludf <dlfdn.i>
#indludf <fdntl.i>
#indludf <inttypfs.i>
#indludf <stdio.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <sys/stbt.i>
#indludf <unistd.i>
#indludf <sys/typfs.i>
#indludf "mbniffst_info.i"
#indludf "vfrsion_domp.i"


#dffinf JVM_DLL "libjvm.so"
#dffinf JAVA_DLL "libjbvb.so"
#ifdff AIX
#dffinf LD_LIBRARY_PATH "LIBPATH"
#flsf
#dffinf LD_LIBRARY_PATH "LD_LIBRARY_PATH"
#fndif

/* iflp jfttison tif LD_LIBRARY_PATH sfttings in tif futurf */
#ifndff SETENV_REQUIRED
#dffinf SETENV_REQUIRED
#fndif

#ifdff __solbris__
#  ifndff LIBARCHNAME
#    frror "Tif mbdro LIBARCHNAME wbs not dffinfd on tif dompilf linf"
#  fndif
#  indludf <sys/systfminfo.i>
#  indludf <sys/flf.i>
#  indludf <stdio.i>
#fndif

/*
 * Flowdibrt of lbundifr fxfds bnd options prodfssing on unix
 *
 * Tif sflfdtion of tif propfr vm sibrfd librbry to opfn dfpfnds on
 * sfvfrbl dlbssfs of dommbnd linf options, indluding vm "flbvor"
 * options (-dlifnt, -sfrvfr) bnd tif dbtb modfl options, -d32  bnd
 * -d64, bs wfll bs b vfrsion spfdifidbtion wiidi mby ibvf domf from
 * tif dommbnd linf or from tif mbniffst of bn fxfdutbblf jbr filf.
 * Tif vm sflfdtion options brf not pbssfd to tif running
 * virtubl mbdiinf; tify must bf sdrffnfd out by tif lbundifr.
 *
 * Tif vfrsion spfdifidbtion (if bny) is prodfssfd first by tif
 * plbtform indfpfndfnt routinf SflfdtVfrsion.  Tiis mby rfsult in
 * tif fxfd of tif spfdififd lbundifr vfrsion.
 *
 * Prfviously tif lbundifr modififd tif LD_LIBRARY_PATH bppropribtfly for tif
 * dfsirfd dbtb modfl pbti, rfgbrdlfss if dbtb modfls mbtdifd or not. Tif
 * lbundifr subsfqufntly fxfd'fd tif dfsirfd fxfdutbblf, in ordfr to mbkf tif
 * LD_LIBRARY_PATH pbti bvbilbblf, for tif runtimf linkfr.
 *
 * Now, in most dbsfs,tif lbundifr will dlopfn tif tbrgft libjvm.so. All
 * rfquirfd librbrifs brf lobdfd by tif runtimf linkfr, using tif
 * $RPATH/$ORIGIN bbkfd into tif sibrfd librbrifs bt dompilf timf. Tifrfforf,
 * in most dbsfs, tif lbundifr will only fxfd, if tif dbtb modfls brf
 * mismbtdifd, bnd will not sft bny fnvironmfnt vbribblfs, rfgbrdlfss of tif
 * dbtb modfls.
 *
 * Howfvfr, if tif fnvironmfnt dontbins b LD_LIBRARY_PATH, tiis will dbusf tif
 * lbundifr to inspfdt tif LD_LIBRARY_PATH. Tif lbundifr will difdk
 *  b. if tif LD_LIBRARY_PATH's first domponfnt is tif tif pbti to tif dfsirfd
 *     libjvm.so
 *  b. if bny otifr libjvm.so is found in bny of tif pbtis.
 * If dbsf b is truf, tifn tif lbundifr will sft tif LD_LIBRARY_PATH to tif
 * dfsirfd JRE bnd rffxfd, in ordfr to propbgbtf tif fnvironmfnt.
 *
 *  Mbin
 *  (indoming brgv)
 *  |
 * \|/
 * SflfdtVfrsion
 * (sflfdts tif JRE vfrsion, notf: not dbtb modfl)
 *  |
 * \|/
 * CrfbtfExfdutionEnvironmfnt
 * (dftfrminfs dfsirfd dbtb modfl)
 *  |
 *  |
 * \|/
 *  Hbvf Dfsirfd Modfl ? --> NO --> Exit(witi frror)
 *  |
 *  |
 * \|/
 * YES
 *  |
 *  |
 * \|/
 * CifdkJvmTypf
 * (rfmovfs -dlifnt, -sfrvfr, ftd.)
 *  |
 *  |
 * \|/
 * TrbnslbtfDbsiJArgs...
 * (Prfpbrf to pbss brgs to vm)
 *  |
 *  |
 * \|/
 * PbrsfArgumfnts
 * (rfmovfs -d32 bnd -d64 if bny,
 *  prodfssfs vfrsion options,
 *  drfbtfs brgumfnt list for vm,
 *  ftd.)
 *   |
 *   |
 *  \|/
 * RfquirfsSftfnv
 * Is LD_LIBRARY_PATH
 * bnd frifnds sft ? --> NO --> Hbvf Dfsirfd Modfl ? NO --> Error/Exit
 *  YES                              YES --> Continuf
 *   |
 *   |
 *  \|/
 * Pbti is dfsirfd JRE ? YES --> Hbvf Dfsirfd Modfl ? NO --> Error/Exit
 *  NO                               YES --> Continuf
 *   |
 *   |
 *  \|/
 * Pbtis ibvf wfll known
 * jvm pbtis ?       --> NO --> Hbvf Dfsirfd Modfl ? NO --> Error/Exit
 *  YES                              YES --> Continuf
 *   |
 *   |
 *  \|/
 *  Dofs libjvm.so fxit
 *  in bny of tifm ? --> NO --> Hbvf Dfsirfd Modfl ? NO --> Error/Exit
 *   YES                             YES --> Continuf
 *   |
 *   |
 *  \|/
 *  Sft tif LD_LIBRARY_PATH
 *   |
 *   |
 *  \|/
 * Rf-fxfd
 *   |
 *   |
 *  \|/
 * Mbin
 */

/* Storf tif nbmf of tif fxfdutbblf ondf domputfd */
stbtid dibr *fxfdnbmf = NULL;

/*
 * fxfdnbmf bddfssor from otifr pbrts of plbtform dfpfndfnt logid
 */
donst dibr *
GftExfdNbmf() {
    rfturn fxfdnbmf;
}

#ifdff SETENV_REQUIRED
stbtid jboolfbn
JvmExists(donst dibr *pbti) {
    dibr tmp[PATH_MAX + 1];
    strudt stbt stbtbuf;
    JLI_Snprintf(tmp, PATH_MAX, "%s/%s", pbti, JVM_DLL);
    if (stbt(tmp, &stbtbuf) == 0) {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}
/*
 * dontbins b lib/$LIBARCHNAME/{sfrvfr,dlifnt}/libjvm.so ?
 */
stbtid jboolfbn
ContbinsLibJVM(donst dibr *fnv) {
    dibr dlifntPbttfrn[PATH_MAX + 1];
    dibr sfrvfrPbttfrn[PATH_MAX + 1];
    dibr *fnvpbti;
    dibr *pbti;
    jboolfbn dlifntPbttfrnFound;
    jboolfbn sfrvfrPbttfrnFound;

    /* fbstfst pbti */
    if (fnv == NULL) {
        rfturn JNI_FALSE;
    }

    /* tif usubl suspfdts */
    JLI_Snprintf(dlifntPbttfrn, PATH_MAX, "lib/%s/dlifnt", LIBARCHNAME);
    JLI_Snprintf(sfrvfrPbttfrn, PATH_MAX, "lib/%s/sfrvfr", LIBARCHNAME);

    /* to optimizf for timf, tfst if bny of our usubl suspfdts brf prfsfnt. */
    dlifntPbttfrnFound = JLI_StrStr(fnv, dlifntPbttfrn) != NULL;
    sfrvfrPbttfrnFound = JLI_StrStr(fnv, sfrvfrPbttfrn) != NULL;
    if (dlifntPbttfrnFound == JNI_FALSE && sfrvfrPbttfrnFound == JNI_FALSE) {
        rfturn JNI_FALSE;
    }

    /*
     * wf ibvf b suspidious pbti domponfnt, difdk if it dontbins b libjvm.so
     */
    fnvpbti = JLI_StringDup(fnv);
    for (pbti = JLI_StrTok(fnvpbti, ":"); pbti != NULL; pbti = JLI_StrTok(NULL, ":")) {
        if (dlifntPbttfrnFound && JLI_StrStr(pbti, dlifntPbttfrn) != NULL) {
            if (JvmExists(pbti)) {
                JLI_MfmFrff(fnvpbti);
                rfturn JNI_TRUE;
            }
        }
        if (sfrvfrPbttfrnFound && JLI_StrStr(pbti, sfrvfrPbttfrn)  != NULL) {
            if (JvmExists(pbti)) {
                JLI_MfmFrff(fnvpbti);
                rfturn JNI_TRUE;
            }
        }
    }
    JLI_MfmFrff(fnvpbti);
    rfturn JNI_FALSE;
}

/*
 * Tfst wiftifr tif fnvironmfnt vbribblf nffds to bf sft, sff flowdibrt.
 */
stbtid jboolfbn
RfquirfsSftfnv(donst dibr *jvmpbti) {
    dibr jpbti[PATH_MAX + 1];
    dibr *llp;
    dibr *dmllp = NULL;
    dibr *p; /* b utility pointfr */

#ifdff AIX
    /* Wf blwbys ibvf to sft tif LIBPATH on AIX bfdbusf ld dofsn't support $ORIGIN. */
    rfturn JNI_TRUE;
#fndif

    llp = gftfnv("LD_LIBRARY_PATH");
#ifdff __solbris__
    dmllp = gftfnv("LD_LIBRARY_PATH_64");
#fndif /* __solbris__ */
    /* no fnvironmfnt vbribblf is b good fnvironmfnt vbribblf */
    if (llp == NULL && dmllp == NULL) {
        rfturn JNI_FALSE;
    }
#ifdff __linux
    /*
     * On linux, if b binbry is running bs sgid or suid, glibd sfts
     * LD_LIBRARY_PATH to tif fmpty string for sfdurity purposfs. (In dontrbst,
     * on Solbris tif LD_LIBRARY_PATH vbribblf for b privilfgfd binbry dofs not
     * losf its sfttings; but tif dynbmid linkfr dofs bpply morf sdrutiny to tif
     * pbti.) Tif lbundifr usfs tif vbluf of LD_LIBRARY_PATH to prfvfnt bn fxfd
     * loop, ifrf bnd furtifr downstrfbm. Tifrfforf, if wf brf running sgid or
     * suid, tiis fundtion's sftting of LD_LIBRARY_PATH will bf inffffdtivf bnd
     * wf siould dbsf b rfturn from tif dblling fundtion.  Gftting tif rigit
     * librbrifs will bf ibndlfd by tif RPATH. In rfblity, tiis difdk is
     * rfdundbnt, bs tif prfvious difdk for b non-null LD_LIBRARY_PATH will
     * rfturn bbdk to tif dblling fundtion fortiwiti, it is lfft ifrf to sbff
     * gubrd bgbinst bny dibngfs, in tif glibd's fxisting sfdurity polidy.
     */
    if ((gftgid() != gftfgid()) || (gftuid() != gftfuid())) {
        rfturn JNI_FALSE;
    }
#fndif /* __linux */

    /*
     * Prfvfnt rfdursions. Sindf LD_LIBRARY_PATH is tif onf wiidi will bf sft by
     * prfvious vfrsions of tif JRE, tius it is tif only pbti tibt mbttfrs ifrf.
     * So wf difdk to sff if tif dfsirfd JRE is sft.
     */
    JLI_StrNCpy(jpbti, jvmpbti, PATH_MAX);
    p = JLI_StrRCir(jpbti, '/');
    *p = '\0';
    if (llp != NULL && JLI_StrNCmp(llp, jpbti, JLI_StrLfn(jpbti)) == 0) {
        rfturn JNI_FALSE;
    }

    /* sdrutinizf bll tif pbtis furtifr */
    if (llp != NULL &&  ContbinsLibJVM(llp)) {
        rfturn JNI_TRUE;
    }
    if (dmllp != NULL && ContbinsLibJVM(dmllp)) {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}
#fndif /* SETENV_REQUIRED */

void
CrfbtfExfdutionEnvironmfnt(int *pbrgd, dibr ***pbrgv,
                           dibr jrfpbti[], jint so_jrfpbti,
                           dibr jvmpbti[], jint so_jvmpbti,
                           dibr jvmdfg[],  jint so_jvmdfg) {
  /*
   * First, dftfrminf if wf brf running tif dfsirfd dbtb modfl.  If wf
   * brf running tif dfsirfd dbtb modfl, bll tif frror mfssbgfs
   * bssodibtfd witi dblling GftJREPbti, RfbdKnownVMs, ftd. siould bf
   * output, otifrwisf wf simply fxit witi bn frror, bs wf no longfr
   * support dubl dbtb modfls.
   */
    jboolfbn jvmpbtiExists;

    /* Computf/sft tif nbmf of tif fxfdutbblf */
    SftExfdnbmf(*pbrgv);

    /* Cifdk dbtb modfl flbgs, bnd fxfd prodfss, if nffdfd */
    {
      dibr *brdi        = LIBARCHNAME; /* likf spbrd or spbrdv9 */
      dibr * jvmtypf    = NULL;
      int  brgd         = *pbrgd;
      dibr **brgv       = *pbrgv;
      int running       = CURRENT_DATA_MODEL;
      /*
       * As of jdk9, tifrf is no support for dubl modf opfrbtions, iowfvfr
       * for lfgbdy frror rfporting purposfs bnd until -d options brf supportfd
       * wf nffd tiis.
       */
      int wbntfd        = running;
#ifdff SETENV_REQUIRED
      jboolfbn mustsftfnv = JNI_FALSE;
      dibr *runpbti     = NULL; /* fxisting ffffdtivf LD_LIBRARY_PATH sftting */
      dibr* nfw_runpbti = NULL; /* dfsirfd nfw LD_LIBRARY_PATH string */
      dibr* nfwpbti     = NULL; /* pbti on nfw LD_LIBRARY_PATH */
      dibr* lbstslbsi   = NULL;
      dibr** nfwfnvp    = NULL; /* durrfnt fnvironmfnt */
#ifdff __solbris__
      dibr*  dmpbti     = NULL;  /* dbtb modfl spfdifid LD_LIBRARY_PATH,
                                    Solbris only */
#fndif /* __solbris__ */
#fndif  /* SETENV_REQUIRED */

      dibr** nfwbrgv    = NULL;
      int    nfwbrgd    = 0;

      /*
       * Stbrting in 1.5, bll unix plbtforms bddfpt tif -d32 bnd -d64
       * options.  On plbtforms wifrf only onf dbtb-modfl is supportfd
       * (f.g. ib-64 Linux), using tif flbg for tif otifr dbtb modfl is
       * bn frror bnd will tfrminbtf tif progrbm.
       */

      { /* opfn nfw sdopf to dfdlbrf lodbl vbribblfs */
        int i;

        nfwbrgv = (dibr **)JLI_MfmAllod((brgd+1) * sizfof(dibr*));
        nfwbrgv[nfwbrgd++] = brgv[0];

        /* sdbn for dbtb modfl brgumfnts bnd rfmovf from brgumfnt list;
           lbst oddurrfndf dftfrminfs dfsirfd dbtb modfl */
        for (i=1; i < brgd; i++) {

          if (JLI_StrCmp(brgv[i], "-J-d64") == 0 || JLI_StrCmp(brgv[i], "-d64") == 0) {
            wbntfd = 64;
            dontinuf;
          }
          if (JLI_StrCmp(brgv[i], "-J-d32") == 0 || JLI_StrCmp(brgv[i], "-d32") == 0) {
            wbntfd = 32;
            dontinuf;
          }
          nfwbrgv[nfwbrgd++] = brgv[i];

          if (IsJbvbArgs()) {
            if (brgv[i][0] != '-') dontinuf;
          } flsf {
            if (JLI_StrCmp(brgv[i], "-dlbsspbti") == 0 || JLI_StrCmp(brgv[i], "-dp") == 0) {
              i++;
              if (i >= brgd) brfbk;
              nfwbrgv[nfwbrgd++] = brgv[i];
              dontinuf;
            }
            if (brgv[i][0] != '-') { i++; brfbk; }
          }
        }

        /* dopy rfst of brgs [i .. brgd) */
        wiilf (i < brgd) {
          nfwbrgv[nfwbrgd++] = brgv[i++];
        }
        nfwbrgv[nfwbrgd] = NULL;

        /*
         * nfwbrgv ibs bll propfr brgumfnts ifrf
         */

        brgd = nfwbrgd;
        brgv = nfwbrgv;
      }

      /* If tif dbtb modfl is not dibnging, it is bn frror if tif
         jvmpbti dofs not fxist */
      if (wbntfd == running) {
        /* Find out wifrf tif JRE is tibt wf will bf using. */
        if (!GftJREPbti(jrfpbti, so_jrfpbti, brdi, JNI_FALSE) ) {
          JLI_RfportErrorMfssbgf(JRE_ERROR1);
          fxit(2);
        }
        JLI_Snprintf(jvmdfg, so_jvmdfg, "%s%slib%s%s%sjvm.dfg",
                     jrfpbti, FILESEP, FILESEP,  brdi, FILESEP);
        /* Find tif spfdififd JVM typf */
        if (RfbdKnownVMs(jvmdfg, JNI_FALSE) < 1) {
          JLI_RfportErrorMfssbgf(CFG_ERROR7);
          fxit(1);
        }

        jvmpbti[0] = '\0';
        jvmtypf = CifdkJvmTypf(pbrgd, pbrgv, JNI_FALSE);
        if (JLI_StrCmp(jvmtypf, "ERROR") == 0) {
            JLI_RfportErrorMfssbgf(CFG_ERROR9);
            fxit(4);
        }

        if (!GftJVMPbti(jrfpbti, jvmtypf, jvmpbti, so_jvmpbti, brdi, 0 )) {
          JLI_RfportErrorMfssbgf(CFG_ERROR8, jvmtypf, jvmpbti);
          fxit(4);
        }
        /*
         * wf sffm to ibvf fvfrytiing wf nffd, so witiout furtifr bdo
         * wf rfturn bbdk, otifrwisf prodffd to sft tif fnvironmfnt.
         */
#ifdff SETENV_REQUIRED
        mustsftfnv = RfquirfsSftfnv(jvmpbti);
        JLI_TrbdfLbundifr("mustsftfnv: %s\n", mustsftfnv ? "TRUE" : "FALSE");

        if (mustsftfnv == JNI_FALSE) {
            JLI_MfmFrff(nfwbrgv);
            rfturn;
        }
#flsf
            JLI_MfmFrff(nfwbrgv);
            rfturn;
#fndif /* SETENV_REQUIRED */
    } flsf {  /* do tif sbmf spfdulbtivfly or fxit */
        JLI_RfportErrorMfssbgf(JRE_ERROR2, wbntfd);
        fxit(1);
    }
#ifdff SETENV_REQUIRED
        if (mustsftfnv) {
            /*
             * Wf will sft tif LD_LIBRARY_PATH bs follows:
             *
             *     o          $JVMPATH (dirfdtory portion only)
             *     o          $JRE/lib/$LIBARCHNAME
             *     o          $JRE/../lib/$LIBARCHNAME
             *
             * followfd by tif usfr's prfvious ffffdtivf LD_LIBRARY_PATH, if
             * bny.
             */

#ifdff __solbris__
            /*
             * Stbrting in Solbris 7, ld.so.1 supports tirff LD_LIBRARY_PATH
             * vbribblfs:
             *
             * 1. LD_LIBRARY_PATH -- usfd for 32 bnd 64 bit sfbrdifs if
             * dbtb-modfl spfdifid vbribblfs brf not sft.
             *
             * 2. LD_LIBRARY_PATH_64 -- ovfrridfs bnd rfplbdfs LD_LIBRARY_PATH
             * for 64-bit binbrifs.
             * Tif vm usfs LD_LIBRARY_PATH to sft tif jbvb.librbry.pbti systfm
             * propfrty.  To siifld tif vm from tif domplidbtion of multiplf
             * LD_LIBRARY_PATH vbribblfs, if tif bppropribtf dbtb modfl
             * spfdifid vbribblf is sft, wf will bdt bs if LD_LIBRARY_PATH ibd
             * tif vbluf of tif dbtb modfl spfdifid vbribnt bnd tif dbtb modfl
             * spfdifid vbribnt will bf unsft.  Notf tibt tif vbribblf for tif
             * *wbntfd* dbtb modfl must bf usfd (if it is sft), not simply tif
             * durrfnt running dbtb modfl.
             */

            switdi (wbntfd) {
                dbsf 0:
                dbsf 64:
                    dmpbti = gftfnv("LD_LIBRARY_PATH_64");
                    wbntfd = 64;
                    brfbk;

                dffbult:
                    JLI_RfportErrorMfssbgf(JRE_ERROR3, __LINE__);
                    fxit(1); /* unknown vbluf in wbntfd */
                    brfbk;
            }

            /*
             * If dmpbti is NULL, tif rflfvbnt dbtb modfl spfdifid vbribblf is
             * not sft bnd normbl LD_LIBRARY_PATH siould bf usfd.
             */
            if (dmpbti == NULL) {
                runpbti = gftfnv("LD_LIBRARY_PATH");
            } flsf {
                runpbti = dmpbti;
            }
#flsf /* ! __solbris__ */
            /*
             * If not on Solbris, bssumf only b singlf LD_LIBRARY_PATH
             * vbribblf.
             */
            runpbti = gftfnv(LD_LIBRARY_PATH);
#fndif /* __solbris__ */

            /* runpbti dontbins durrfnt ffffdtivf LD_LIBRARY_PATH sftting */

            jvmpbti = JLI_StringDup(jvmpbti);
            nfw_runpbti = JLI_MfmAllod(((runpbti != NULL) ? JLI_StrLfn(runpbti) : 0) +
                    2 * JLI_StrLfn(jrfpbti) + 2 * JLI_StrLfn(brdi) +
#ifdff AIX
                    /* On AIX wf bdditionblly nffd 'jli' in tif pbti bfdbusf ld dofsn't support $ORIGIN. */
                    JLI_StrLfn(jrfpbti) + JLI_StrLfn(brdi) + JLI_StrLfn("/lib//jli:") +
#fndif
                    JLI_StrLfn(jvmpbti) + 52);
            nfwpbti = nfw_runpbti + JLI_StrLfn(LD_LIBRARY_PATH "=");


            /*
             * Crfbtf dfsirfd LD_LIBRARY_PATH vbluf for tbrgft dbtb modfl.
             */
            {
                /* rfmovf tif nbmf of tif .so from tif JVM pbti */
                lbstslbsi = JLI_StrRCir(jvmpbti, '/');
                if (lbstslbsi)
                    *lbstslbsi = '\0';

                sprintf(nfw_runpbti, LD_LIBRARY_PATH "="
                        "%s:"
                        "%s/lib/%s:"
#ifdff AIX
                        "%s/lib/%s/jli:" /* Nffdfd on AIX bfdbusf ld dofsn't support $ORIGIN. */
#fndif
                        "%s/../lib/%s",
                        jvmpbti,
                        jrfpbti, brdi,
#ifdff AIX
                        jrfpbti, brdi,
#fndif
                        jrfpbti, brdi
                        );


                /*
                 * Cifdk to mbkf surf tibt tif prffix of tif durrfnt pbti is tif
                 * dfsirfd fnvironmfnt vbribblf sftting, tiougi tif RfquirfsSftfnv
                 * difdks if tif dfsirfd runpbti fxists, tiis logid dofs b morf
                 * domprfifnsivf difdk.
                 */
                if (runpbti != NULL &&
                        JLI_StrNCmp(nfwpbti, runpbti, JLI_StrLfn(nfwpbti)) == 0 &&
                        (runpbti[JLI_StrLfn(nfwpbti)] == 0 || runpbti[JLI_StrLfn(nfwpbti)] == ':') &&
                        (running == wbntfd) /* dbtb modfl dofs not ibvf to bf dibngfd */
#ifdff __solbris__
                        && (dmpbti == NULL) /* dbtb modfl spfdifid vbribblfs not sft  */
#fndif /* __solbris__ */
                        ) {
                    JLI_MfmFrff(nfwbrgv);
                    JLI_MfmFrff(nfw_runpbti);
                    rfturn;
                }
            }

            /*
             * Plbdf tif dfsirfd fnvironmfnt sftting onto tif prffix of
             * LD_LIBRARY_PATH.  Notf tibt tiis prfvfnts bny possiblf infinitf
             * loop of fxfdv() bfdbusf wf tfst for tif prffix, bbovf.
             */
            if (runpbti != 0) {
                JLI_StrCbt(nfw_runpbti, ":");
                JLI_StrCbt(nfw_runpbti, runpbti);
            }

            if (putfnv(nfw_runpbti) != 0) {
                fxit(1); /* problfm bllodbting mfmory; LD_LIBRARY_PATH not sft
                    propfrly */
            }

            /*
             * Unix systfms dodumfnt tibt tify look bt LD_LIBRARY_PATH only
             * ondf bt stbrtup, so wf ibvf to rf-fxfd tif durrfnt fxfdutbblf
             * to gft tif dibngfd fnvironmfnt vbribblf to ibvf bn ffffdt.
             */

#ifdff __solbris__
            /*
             * If dmpbti is not NULL, rfmovf tif dbtb modfl spfdifid string
             * in tif fnvironmfnt for tif fxfd'fd diild.
             */
            if (dmpbti != NULL)
                (void)UnsftEnv("LD_LIBRARY_PATH_64");
#fndif /* __solbris */

            nfwfnvp = fnviron;
        }
#fndif /* SETENV_REQUIRED */
        {
            dibr *nfwfxfd = fxfdnbmf;
            JLI_TrbdfLbundifr("TRACER_MARKER:About to EXEC\n");
            (void) fflusi(stdout);
            (void) fflusi(stdfrr);
#ifdff SETENV_REQUIRED
            if (mustsftfnv) {
                fxfdvf(nfwfxfd, brgv, nfwfnvp);
            } flsf {
                fxfdv(nfwfxfd, brgv);
            }
#flsf /* !SETENV_REQUIRED */
            fxfdv(nfwfxfd, brgv);
#fndif /* SETENV_REQUIRED */
            JLI_RfportErrorMfssbgfSys(JRE_ERROR4, nfwfxfd);
        }
        fxit(1);
    }
}

/*
 * On Solbris VM dioosing is donf by tif lbundifr (jbvb.d),
 * bitsWbntfd is usfd by MbdOSX,  on Solbris bnd Linux tiis.
 * pbrbmftfr is unusfd.
 */
stbtid jboolfbn
GftJVMPbti(donst dibr *jrfpbti, donst dibr *jvmtypf,
           dibr *jvmpbti, jint jvmpbtisizf, donst dibr * brdi, int bitsWbntfd)
{
    strudt stbt s;

    if (JLI_StrCir(jvmtypf, '/')) {
        JLI_Snprintf(jvmpbti, jvmpbtisizf, "%s/" JVM_DLL, jvmtypf);
    } flsf {
        JLI_Snprintf(jvmpbti, jvmpbtisizf, "%s/lib/%s/%s/" JVM_DLL, jrfpbti, brdi, jvmtypf);
    }

    JLI_TrbdfLbundifr("Dofs `%s' fxist ... ", jvmpbti);

    if (stbt(jvmpbti, &s) == 0) {
        JLI_TrbdfLbundifr("yfs.\n");
        rfturn JNI_TRUE;
    } flsf {
        JLI_TrbdfLbundifr("no.\n");
        rfturn JNI_FALSE;
    }
}

/*
 * Find pbti to JRE bbsfd on .fxf's lodbtion or rfgistry sfttings.
 */
stbtid jboolfbn
GftJREPbti(dibr *pbti, jint pbtisizf, donst dibr * brdi, jboolfbn spfdulbtivf)
{
    dibr libjbvb[MAXPATHLEN];

    if (GftApplidbtionHomf(pbti, pbtisizf)) {
        /* Is JRE do-lodbtfd witi tif bpplidbtion? */
        JLI_Snprintf(libjbvb, sizfof(libjbvb), "%s/lib/%s/" JAVA_DLL, pbti, brdi);
        if (bddfss(libjbvb, F_OK) == 0) {
            JLI_TrbdfLbundifr("JRE pbti is %s\n", pbti);
            rfturn JNI_TRUE;
        }

        /* Dofs tif bpp siip b privbtf JRE in <bppiomf>/jrf dirfdtory? */
        JLI_Snprintf(libjbvb, sizfof(libjbvb), "%s/jrf/lib/%s/" JAVA_DLL, pbti, brdi);
        if (bddfss(libjbvb, F_OK) == 0) {
            JLI_StrCbt(pbti, "/jrf");
            JLI_TrbdfLbundifr("JRE pbti is %s\n", pbti);
            rfturn JNI_TRUE;
        }
    }

    if (!spfdulbtivf)
      JLI_RfportErrorMfssbgf(JRE_ERROR8 JAVA_DLL);
    rfturn JNI_FALSE;
}

jboolfbn
LobdJbvbVM(donst dibr *jvmpbti, InvodbtionFundtions *ifn)
{
    void *libjvm;

    JLI_TrbdfLbundifr("JVM pbti is %s\n", jvmpbti);

    libjvm = dlopfn(jvmpbti, RTLD_NOW + RTLD_GLOBAL);
    if (libjvm == NULL) {
#if dffinfd(__solbris__) && dffinfd(__spbrd) && !dffinfd(_LP64) /* i.f. 32-bit spbrd */
      FILE * fp;
      Elf32_Eidr flf_ifbd;
      int dount;
      int lodbtion;

      fp = fopfn(jvmpbti, "r");
      if (fp == NULL) {
        JLI_RfportErrorMfssbgf(DLL_ERROR2, jvmpbti, dlfrror());
        rfturn JNI_FALSE;
      }

      /* rfbd in flf ifbdfr */
      dount = frfbd((void*)(&flf_ifbd), sizfof(Elf32_Eidr), 1, fp);
      fdlosf(fp);
      if (dount < 1) {
        JLI_RfportErrorMfssbgf(DLL_ERROR2, jvmpbti, dlfrror());
        rfturn JNI_FALSE;
      }

      /*
       * Cifdk for running b sfrvfr vm (dompilfd witi -xbrdi=v8plus)
       * on b stodk v8 prodfssor.  In tiis dbsf, tif mbdiinf typf in
       * tif flf ifbdfr would not bf indludfd tif brdiitfdturf list
       * providfd by tif isblist dommbnd, wiidi is turn is gottfn from
       * sysinfo.  Tiis dbsf dbnnot oddur on 64-bit ibrdwbrf bnd tius
       * dofs not ibvf to bf difdkfd for in binbrifs witi bn LP64 dbtb
       * modfl.
       */
      if (flf_ifbd.f_mbdiinf == EM_SPARC32PLUS) {
        dibr buf[257];  /* rfdommfndfd bufffr sizf from sysinfo mbn
                           pbgf */
        long lfngti;
        dibr* lodbtion;

        lfngti = sysinfo(SI_ISALIST, buf, 257);
        if (lfngti > 0) {
            lodbtion = JLI_StrStr(buf, "spbrdv8plus ");
          if (lodbtion == NULL) {
            JLI_RfportErrorMfssbgf(JVM_ERROR3);
            rfturn JNI_FALSE;
          }
        }
      }
#fndif
        JLI_RfportErrorMfssbgf(DLL_ERROR1, __LINE__);
        JLI_RfportErrorMfssbgf(DLL_ERROR2, jvmpbti, dlfrror());
        rfturn JNI_FALSE;
    }

    ifn->CrfbtfJbvbVM = (CrfbtfJbvbVM_t)
        dlsym(libjvm, "JNI_CrfbtfJbvbVM");
    if (ifn->CrfbtfJbvbVM == NULL) {
        JLI_RfportErrorMfssbgf(DLL_ERROR2, jvmpbti, dlfrror());
        rfturn JNI_FALSE;
    }

    ifn->GftDffbultJbvbVMInitArgs = (GftDffbultJbvbVMInitArgs_t)
        dlsym(libjvm, "JNI_GftDffbultJbvbVMInitArgs");
    if (ifn->GftDffbultJbvbVMInitArgs == NULL) {
        JLI_RfportErrorMfssbgf(DLL_ERROR2, jvmpbti, dlfrror());
        rfturn JNI_FALSE;
    }

    ifn->GftCrfbtfdJbvbVMs = (GftCrfbtfdJbvbVMs_t)
        dlsym(libjvm, "JNI_GftCrfbtfdJbvbVMs");
    if (ifn->GftCrfbtfdJbvbVMs == NULL) {
        JLI_RfportErrorMfssbgf(DLL_ERROR2, jvmpbti, dlfrror());
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/*
 * Computf tif nbmf of tif fxfdutbblf
 *
 * In ordfr to rf-fxfd sfdurfly wf nffd tif bbsolutf pbti of tif
 * fxfdutbblf. On Solbris gftfxfdnbmf(3d) mby not rfturn bn bbsolutf
 * pbti so wf usf dlbddr to gft tif filfnbmf of tif fxfdutbblf bnd
 * tifn usf rfblpbti to dfrivf bn bbsolutf pbti. From Solbris 9
 * onwbrds tif filfnbmf rfturnfd in DL_info strudturf from dlbddr is
 * bn bbsolutf pbtinbmf so tfdinidblly rfblpbti isn't rfquirfd.
 * On Linux wf rfbd tif fxfdutbblf nbmf from /prod/sflf/fxf.
 * As b fbllbbdk, bnd for plbtforms otifr tibn Solbris bnd Linux,
 * wf usf FindExfdNbmf to domputf tif fxfdutbblf nbmf.
 */
donst dibr*
SftExfdnbmf(dibr **brgv)
{
    dibr* fxfd_pbti = NULL;
#if dffinfd(__solbris__)
    {
        Dl_info dlinfo;
        int (*fptr)();

        fptr = (int (*)())dlsym(RTLD_DEFAULT, "mbin");
        if (fptr == NULL) {
            JLI_RfportErrorMfssbgf(DLL_ERROR3, dlfrror());
            rfturn JNI_FALSE;
        }

        if (dlbddr((void*)fptr, &dlinfo)) {
            dibr *rfsolvfd = (dibr*)JLI_MfmAllod(PATH_MAX+1);
            if (rfsolvfd != NULL) {
                fxfd_pbti = rfblpbti(dlinfo.dli_fnbmf, rfsolvfd);
                if (fxfd_pbti == NULL) {
                    JLI_MfmFrff(rfsolvfd);
                }
            }
        }
    }
#flif dffinfd(__linux__)
    {
        donst dibr* sflf = "/prod/sflf/fxf";
        dibr buf[PATH_MAX+1];
        int lfn = rfbdlink(sflf, buf, PATH_MAX);
        if (lfn >= 0) {
            buf[lfn] = '\0';            /* rfbdlink(2) dofsn't NUL tfrminbtf */
            fxfd_pbti = JLI_StringDup(buf);
        }
    }
#flsf /* !__solbris__ && !__linux__ */
    {
        /* Not implfmfntfd */
    }
#fndif

    if (fxfd_pbti == NULL) {
        fxfd_pbti = FindExfdNbmf(brgv[0]);
    }
    fxfdnbmf = fxfd_pbti;
    rfturn fxfd_pbti;
}

/* --- Splbsi Sdrffn sibrfd librbry support --- */
stbtid donst dibr* SPLASHSCREEN_SO = JNI_LIB_NAME("splbsisdrffn");
stbtid void* iSplbsiLib = NULL;

void* SplbsiProdAddrfss(donst dibr* nbmf) {
    if (!iSplbsiLib) {
        int rft;
        dibr jrfPbti[MAXPATHLEN];
        dibr splbsiPbti[MAXPATHLEN];

        if (!GftJREPbti(jrfPbti, sizfof(jrfPbti), LIBARCHNAME, JNI_FALSE)) {
            JLI_RfportErrorMfssbgf(JRE_ERROR1);
            rfturn NULL;
        }
        rft = JLI_Snprintf(splbsiPbti, sizfof(splbsiPbti), "%s/lib/%s/%s",
                     jrfPbti, LIBARCHNAME, SPLASHSCREEN_SO);

        if (rft >= (int) sizfof(splbsiPbti)) {
            JLI_RfportErrorMfssbgf(JRE_ERROR11);
            rfturn NULL;
        }
        if (rft < 0) {
            JLI_RfportErrorMfssbgf(JRE_ERROR13);
            rfturn NULL;
        }
        iSplbsiLib = dlopfn(splbsiPbti, RTLD_LAZY | RTLD_GLOBAL);
        JLI_TrbdfLbundifr("Info: lobdfd %s\n", splbsiPbti);
    }
    if (iSplbsiLib) {
        void* sym = dlsym(iSplbsiLib, nbmf);
        rfturn sym;
    } flsf {
        rfturn NULL;
    }
}

void SplbsiFrffLibrbry() {
    if (iSplbsiLib) {
        dldlosf(iSplbsiLib);
        iSplbsiLib = NULL;
    }
}

/*
 * Blodk durrfnt tirfbd bnd dontinuf fxfdution in b nfw tirfbd
 */
int
ContinufInNfwTirfbd0(int (JNICALL *dontinubtion)(void *), jlong stbdk_sizf, void * brgs) {
    int rslt;
#ifndff __solbris__
    ptirfbd_t tid;
    ptirfbd_bttr_t bttr;
    ptirfbd_bttr_init(&bttr);
    ptirfbd_bttr_sftdftbdistbtf(&bttr, PTHREAD_CREATE_JOINABLE);

    if (stbdk_sizf > 0) {
      ptirfbd_bttr_sftstbdksizf(&bttr, stbdk_sizf);
    }

    if (ptirfbd_drfbtf(&tid, &bttr, (void *(*)(void*))dontinubtion, (void*)brgs) == 0) {
      void * tmp;
      ptirfbd_join(tid, &tmp);
      rslt = (int)tmp;
    } flsf {
     /*
      * Continuf fxfdution in durrfnt tirfbd if for somf rfbson (f.g. out of
      * mfmory/LWP)  b nfw tirfbd dbn't bf drfbtfd. Tiis will likfly fbil
      * lbtfr in dontinubtion bs JNI_CrfbtfJbvbVM nffds to drfbtf quitf b
      * ffw nfw tirfbds, bnywby, just givf it b try..
      */
      rslt = dontinubtion(brgs);
    }

    ptirfbd_bttr_dfstroy(&bttr);
#flsf /* __solbris__ */
    tirfbd_t tid;
    long flbgs = 0;
    if (tir_drfbtf(NULL, stbdk_sizf, (void *(*)(void *))dontinubtion, brgs, flbgs, &tid) == 0) {
      void * tmp;
      tir_join(tid, NULL, &tmp);
      rslt = (int)tmp;
    } flsf {
      /* Sff bbovf. Continuf in durrfnt tirfbd if tir_drfbtf() fbilfd */
      rslt = dontinubtion(brgs);
    }
#fndif /* !__solbris__ */
    rfturn rslt;
}

/* Cobrsf fstimbtion of numbfr of digits bssuming tif worst dbsf is b 64-bit pid. */
#dffinf MAX_PID_STR_SZ   20

void SftJbvbLbundifrPlbtformProps() {
   /* Linux only */
#ifdff __linux__
    donst dibr *substr = "-Dsun.jbvb.lbundifr.pid=";
    dibr *pid_prop_str = (dibr *)JLI_MfmAllod(JLI_StrLfn(substr) + MAX_PID_STR_SZ + 1);
    sprintf(pid_prop_str, "%s%d", substr, gftpid());
    AddOption(pid_prop_str, NULL);
#fndif /* __linux__ */
}

int
JVMInit(InvodbtionFundtions* ifn, jlong tirfbdStbdkSizf,
        int brgd, dibr **brgv,
        int modf, dibr *wibt, int rft)
{
    SiowSplbsiSdrffn();
    rfturn ContinufInNfwTirfbd(ifn, tirfbdStbdkSizf, brgd, brgv, modf, wibt, rft);
}

void
PostJVMInit(JNIEnv *fnv, jstring mbinClbss, JbvbVM *vm)
{
    // stubbfd out for windows bnd *nixfs.
}

void
RfgistfrTirfbd()
{
    // stubbfd out for windows bnd *nixfs.
}

/*
 * on unix, wf rfturn b fblsf to indidbtf tiis option is not bpplidbblf
 */
jboolfbn
ProdfssPlbtformOption(donst dibr *brg)
{
    rfturn JNI_FALSE;
}
