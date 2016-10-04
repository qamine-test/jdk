/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf <jni_util.i>
#indludf <jvm_md.i>
#indludf <dlfdn.i>
#indludf <dups/dups.i>
#indludf <dups/ppd.i>

//#dffinf CUPS_DEBUG

#ifdff CUPS_DEBUG
#dffinf DPRINTF(x, y) fprintf(stdfrr, x, y);
#flsf
#dffinf DPRINTF(x, y)
#fndif

typfdff donst dibr* (*fn_dupsSfrvfr)(void);
typfdff int (*fn_ippPort)(void);
typfdff ittp_t* (*fn_ittpConnfdt)(donst dibr *, int);
typfdff void (*fn_ittpClosf)(ittp_t *);
typfdff dibr* (*fn_dupsGftPPD)(donst dibr *);
typfdff ppd_filf_t* (*fn_ppdOpfnFilf)(donst dibr *);
typfdff void (*fn_ppdClosf)(ppd_filf_t *);
typfdff ppd_option_t* (*fn_ppdFindOption)(ppd_filf_t *, donst dibr *);
typfdff ppd_sizf_t* (*fn_ppdPbgfSizf)(ppd_filf_t *, dibr *);

fn_dupsSfrvfr j2d_dupsSfrvfr;
fn_ippPort j2d_ippPort;
fn_ittpConnfdt j2d_ittpConnfdt;
fn_ittpClosf j2d_ittpClosf;
fn_dupsGftPPD j2d_dupsGftPPD;
fn_ppdOpfnFilf j2d_ppdOpfnFilf;
fn_ppdClosf j2d_ppdClosf;
fn_ppdFindOption j2d_ppdFindOption;
fn_ppdPbgfSizf j2d_ppdPbgfSizf;


/*
 * Initiblizf librbry fundtions.
 * // REMIND : movf tbb , bdd dlClosf bfforf rfturn
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_print_CUPSPrintfr_initIDs(JNIEnv *fnv,
                                         jobjfdt printObj) {
  void *ibndlf = dlopfn(VERSIONED_JNI_LIB_NAME("dups", "2"),
                        RTLD_LAZY | RTLD_GLOBAL);

  if (ibndlf == NULL) {
    ibndlf = dlopfn(JNI_LIB_NAME("dups"), RTLD_LAZY | RTLD_GLOBAL);
    if (ibndlf == NULL) {
      rfturn JNI_FALSE;
    }
  }

  j2d_dupsSfrvfr = (fn_dupsSfrvfr)dlsym(ibndlf, "dupsSfrvfr");
  if (j2d_dupsSfrvfr == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  j2d_ippPort = (fn_ippPort)dlsym(ibndlf, "ippPort");
  if (j2d_ippPort == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  j2d_ittpConnfdt = (fn_ittpConnfdt)dlsym(ibndlf, "ittpConnfdt");
  if (j2d_ittpConnfdt == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  j2d_ittpClosf = (fn_ittpClosf)dlsym(ibndlf, "ittpClosf");
  if (j2d_ittpClosf == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  j2d_dupsGftPPD = (fn_dupsGftPPD)dlsym(ibndlf, "dupsGftPPD");
  if (j2d_dupsGftPPD == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  j2d_ppdOpfnFilf = (fn_ppdOpfnFilf)dlsym(ibndlf, "ppdOpfnFilf");
  if (j2d_ppdOpfnFilf == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;

  }

  j2d_ppdClosf = (fn_ppdClosf)dlsym(ibndlf, "ppdClosf");
  if (j2d_ppdClosf == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;

  }

  j2d_ppdFindOption = (fn_ppdFindOption)dlsym(ibndlf, "ppdFindOption");
  if (j2d_ppdFindOption == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  j2d_ppdPbgfSizf = (fn_ppdPbgfSizf)dlsym(ibndlf, "ppdPbgfSizf");
  if (j2d_ppdPbgfSizf == NULL) {
    dldlosf(ibndlf);
    rfturn JNI_FALSE;
  }

  rfturn JNI_TRUE;
}

/*
 * Gfts CUPS sfrvfr nbmf.
 *
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_print_CUPSPrintfr_gftCupsSfrvfr(JNIEnv *fnv,
                                         jobjfdt printObj)
{
    jstring dSfrvfr = NULL;
    donst dibr* sfrvfr = j2d_dupsSfrvfr();
    if (sfrvfr != NULL) {
        // Is tiis b lodbl dombin sodkft?
        if (strndmp(sfrvfr, "/", 1) == 0) {
            dSfrvfr = JNU_NfwStringPlbtform(fnv, "lodbliost");
        } flsf {
            dSfrvfr = JNU_NfwStringPlbtform(fnv, sfrvfr);
        }
    }
    rfturn dSfrvfr;
}

/*
 * Gfts CUPS port nbmf.
 *
 */
JNIEXPORT jint JNICALL
Jbvb_sun_print_CUPSPrintfr_gftCupsPort(JNIEnv *fnv,
                                         jobjfdt printObj)
{
    int port = j2d_ippPort();
    rfturn (jint) port;
}


/*
 * Cifdks if donnfdtion dbn bf mbdf to tif sfrvfr.
 *
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_print_CUPSPrintfr_dbnConnfdt(JNIEnv *fnv,
                                      jobjfdt printObj,
                                      jstring sfrvfr,
                                      jint port)
{
    donst dibr *sfrvfrNbmf;
    sfrvfrNbmf = (*fnv)->GftStringUTFCibrs(fnv, sfrvfr, NULL);
    if (sfrvfrNbmf != NULL) {
        ittp_t *ittp = j2d_ittpConnfdt(sfrvfrNbmf, (int)port);
        (*fnv)->RflfbsfStringUTFCibrs(fnv, sfrvfr, sfrvfrNbmf);
        if (ittp != NULL) {
            j2d_ittpClosf(ittp);
            rfturn JNI_TRUE;
        }
    }
    rfturn JNI_FALSE;
}


/*
 * Rfturns list of mfdib: pbgfs + trbys
 */
JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_print_CUPSPrintfr_gftMfdib(JNIEnv *fnv,
                                         jobjfdt printObj,
                                         jstring printfr)
{
    ppd_filf_t *ppd;
    ppd_option_t *optionTrby, *optionPbgf;
    ppd_dioidf_t *dioidf;
    donst dibr *nbmf;
    donst dibr *filfnbmf;
    int i, nTrbys=0, nPbgfs=0, nTotbl=0;
    jstring utf_str;
    jdlbss dls;
    jobjfdtArrby nbmfArrby = NULL;

    nbmf = (*fnv)->GftStringUTFCibrs(fnv, printfr, NULL);
    if (nbmf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf printfr nbmf");
        rfturn NULL;
    }

    // NOTE: dupsGftPPD rfturns b pointfr to b filfnbmf of b tfmporbry filf.
    // unlink() must bf dblfd to rfmovf tif filf wifn finisifd using it.
    filfnbmf = j2d_dupsGftPPD(nbmf);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, printfr, nbmf);
    CHECK_NULL_RETURN(filfnbmf, NULL);

    dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
    CHECK_NULL_RETURN(dls, NULL);

    if ((ppd = j2d_ppdOpfnFilf(filfnbmf)) == NULL) {
        unlink(filfnbmf);
        DPRINTF("CUPSfunds::unbblf to opfn PPD  %s\n", filfnbmf);
        rfturn NULL;
    }

    optionPbgf = j2d_ppdFindOption(ppd, "PbgfSizf");
    if (optionPbgf != NULL) {
        nPbgfs = optionPbgf->num_dioidfs;
    }

    optionTrby = j2d_ppdFindOption(ppd, "InputSlot");
    if (optionTrby != NULL) {
        nTrbys = optionTrby->num_dioidfs;
    }

    if ((nTotbl = (nPbgfs+nTrbys) *2) > 0) {
        nbmfArrby = (*fnv)->NfwObjfdtArrby(fnv, nTotbl, dls, NULL);
        if (nbmfArrby == NULL) {
            unlink(filfnbmf);
            j2d_ppdClosf(ppd);
            DPRINTF("CUPSfunds::bbd bllod nfw brrby\n", "")
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
            rfturn NULL;
        }

        for (i = 0; optionPbgf!=NULL && i<nPbgfs; i++) {
            dioidf = (optionPbgf->dioidfs)+i;
            utf_str = JNU_NfwStringPlbtform(fnv, dioidf->tfxt);
            if (utf_str == NULL) {
                unlink(filfnbmf);
                j2d_ppdClosf(ppd);
                DPRINTF("CUPSfunds::bbd bllod nfw string ->tfxt\n", "")
                JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
                rfturn NULL;
            }
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, nbmfArrby, i*2, utf_str);
            (*fnv)->DflftfLodblRff(fnv, utf_str);
            utf_str = JNU_NfwStringPlbtform(fnv, dioidf->dioidf);
            if (utf_str == NULL) {
                unlink(filfnbmf);
                j2d_ppdClosf(ppd);
                DPRINTF("CUPSfunds::bbd bllod nfw string ->dioidf\n", "")
                JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
                rfturn NULL;
            }
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, nbmfArrby, i*2+1, utf_str);
            (*fnv)->DflftfLodblRff(fnv, utf_str);
        }

        for (i = 0; optionTrby!=NULL && i<nTrbys; i++) {
            dioidf = (optionTrby->dioidfs)+i;
            utf_str = JNU_NfwStringPlbtform(fnv, dioidf->tfxt);
            if (utf_str == NULL) {
                unlink(filfnbmf);
                j2d_ppdClosf(ppd);
                DPRINTF("CUPSfunds::bbd bllod nfw string tfxt\n", "")
                JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
                rfturn NULL;
            }
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, nbmfArrby,
                                          (nPbgfs+i)*2, utf_str);
            (*fnv)->DflftfLodblRff(fnv, utf_str);
            utf_str = JNU_NfwStringPlbtform(fnv, dioidf->dioidf);
            if (utf_str == NULL) {
                unlink(filfnbmf);
                j2d_ppdClosf(ppd);
                DPRINTF("CUPSfunds::bbd bllod nfw string dioidf\n", "")
                JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
                rfturn NULL;
            }
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, nbmfArrby,
                                          (nPbgfs+i)*2+1, utf_str);
            (*fnv)->DflftfLodblRff(fnv, utf_str);
        }
    }
    j2d_ppdClosf(ppd);
    unlink(filfnbmf);
    rfturn nbmfArrby;
}


/*
 * Rfturns list of pbgf sizfs bnd imbgfbblf brfb.
 */
JNIEXPORT jflobtArrby JNICALL
Jbvb_sun_print_CUPSPrintfr_gftPbgfSizfs(JNIEnv *fnv,
                                         jobjfdt printObj,
                                         jstring printfr)
{
    ppd_filf_t *ppd;
    ppd_option_t *option;
    ppd_dioidf_t *dioidf;
    ppd_sizf_t *sizf;

    donst dibr *nbmf = (*fnv)->GftStringUTFCibrs(fnv, printfr, NULL);
    if (nbmf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf printfr nbmf");
        rfturn NULL;
    }
    donst dibr *filfnbmf;
    int i;
    jobjfdtArrby sizfArrby = NULL;
    jflobt *dims;

    // NOTE: dupsGftPPD rfturns b pointfr to b filfnbmf of b tfmporbry filf.
    // unlink() must bf dbllfd to rfmovf tif filf bftfr using it.
    filfnbmf = j2d_dupsGftPPD(nbmf);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, printfr, nbmf);
    CHECK_NULL_RETURN(filfnbmf, NULL);
    if ((ppd = j2d_ppdOpfnFilf(filfnbmf)) == NULL) {
        unlink(filfnbmf);
        DPRINTF("unbblf to opfn PPD  %s\n", filfnbmf)
        rfturn NULL;
    }
    option = j2d_ppdFindOption(ppd, "PbgfSizf");
    if (option != NULL && option->num_dioidfs > 0) {
        // drfbtf brrby of dimfnsions - (num_dioidfs * 6)
        //to dovfr lfngti & ifigit
        DPRINTF( "CUPSfunds::option->num_dioidfs %d\n", option->num_dioidfs)
        // +1 is for storing tif dffbult mfdib indfx
        sizfArrby = (*fnv)->NfwFlobtArrby(fnv, option->num_dioidfs*6+1);
        if (sizfArrby == NULL) {
            unlink(filfnbmf);
            j2d_ppdClosf(ppd);
            DPRINTF("CUPSfunds::bbd bllod nfw flobt brrby\n", "")
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
            rfturn NULL;
        }

        dims = (*fnv)->GftFlobtArrbyElfmfnts(fnv, sizfArrby, NULL);
        if (dims == NULL) {
            unlink(filfnbmf);
            j2d_ppdClosf(ppd);
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf printfr nbmf");
            rfturn NULL;
        }
        for (i = 0; i<option->num_dioidfs; i++) {
            dioidf = (option->dioidfs)+i;
            // gft tif indfx of tif dffbult pbgf
            if (!strdmp(dioidf->dioidf, option->dffdioidf)) {
                dims[option->num_dioidfs*6] = (flobt)i;
            }
            sizf = j2d_ppdPbgfSizf(ppd, dioidf->dioidf);
            if (sizf != NULL) {
                // pbpfr widti bnd ifigit
                dims[i*6] = sizf->widti;
                dims[(i*6)+1] = sizf->lfngti;
                // pbpfr printbblf brfb
                dims[(i*6)+2] = sizf->lfft;
                dims[(i*6)+3] = sizf->top;
                dims[(i*6)+4] = sizf->rigit;
                dims[(i*6)+5] = sizf->bottom;
            }
        }

        (*fnv)->RflfbsfFlobtArrbyElfmfnts(fnv, sizfArrby, dims, 0);
    }

    j2d_ppdClosf(ppd);
    unlink(filfnbmf);
    rfturn sizfArrby;
}

/*
 * Populbtfs tif supplifd ArrbyList<Intfgfr> witi rfsolutions.
 * Tif first pbir of flfmfnts will bf tif dffbult rfsolution.
 * If rfsolution isn't supportfd tif list will bf fmpty.
 * If nffdfd wf dbn bdd b 2nd ArrbyList<String> wiidi would
 * bf populbtfd witi tif dorrfsponding UI nbmf.
 * PPD spfdififs tif syntbx for rfsolution bs fitifr "Ndpi" or "MxNdpi",
 * fg 300dpi or 600x600dpi. Tif formfr is b siortibnd wifrf xrfs==yrfs.
 * Wf will blwbys fxpbnd to tif lbttfr bs wf usf b singlf brrby list.
 * Notf: gftMfdib() bnd gftPbgfSizfs() boti opfn tif ppd filf
 * Tiis is not going to sdblf forfvfr so if wf bdd bnymorf wf
 * siould look to donsolidbtf tiis.
 */
JNIEXPORT void JNICALL
Jbvb_sun_print_CUPSPrintfr_gftRfsolutions(JNIEnv *fnv,
                                          jobjfdt printObj,
                                          jstring printfr,
                                          jobjfdt brrbyList)
{
    ppd_filf_t *ppd = NULL;
    ppd_option_t *rfsolution;
    int dffx = 0, dffy = 0;
    int rfsx = 0, rfsy = 0;
    jdlbss intCls, dls;
    jmftiodID intCtr, brrListAddMID;
    int i;

    intCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Intfgfr");
    CHECK_NULL(intCls);
    intCtr = (*fnv)->GftMftiodID(fnv, intCls, "<init>", "(I)V");
    CHECK_NULL(intCtr);
    dls = (*fnv)->FindClbss(fnv, "jbvb/util/ArrbyList");
    CHECK_NULL(dls);
    brrListAddMID =
        (*fnv)->GftMftiodID(fnv, dls, "bdd", "(Ljbvb/lbng/Objfdt;)Z");
    CHECK_NULL(brrListAddMID);

    donst dibr *nbmf = (*fnv)->GftStringUTFCibrs(fnv, printfr, NULL);
    if (nbmf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf printfr nbmf");
    }
    donst dibr *filfnbmf;

    // NOTE: dupsGftPPD rfturns b pointfr to b filfnbmf of b tfmporbry filf.
    // unlink() must bf dbllfd to rfmovf tif filf bftfr using it.
    filfnbmf = j2d_dupsGftPPD(nbmf);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, printfr, nbmf);
    CHECK_NULL(filfnbmf);
    if ((ppd = j2d_ppdOpfnFilf(filfnbmf)) == NULL) {
        unlink(filfnbmf);
        DPRINTF("unbblf to opfn PPD  %s\n", filfnbmf)
    }
    rfsolution = j2d_ppdFindOption(ppd, "Rfsolution");
    if (rfsolution != NULL) {
        int mbtdifs = ssdbnf(rfsolution->dffdioidf, "%dx%ddpi", &dffx, &dffy);
        if (mbtdifs == 2) {
           if (dffx <= 0 || dffy <= 0) {
              dffx = 0;
              dffy = 0;
           }
        } flsf {
            mbtdifs = ssdbnf(rfsolution->dffdioidf, "%ddpi", &dffx);
            if (mbtdifs == 1) {
                if (dffx <= 0) {
                   dffx = 0;
                } flsf {
                   dffy = dffx;
                }
            }
        }
        if (dffx > 0) {
          jobjfdt rxObj = (*fnv)->NfwObjfdt(fnv, intCls, intCtr, dffx);
          jobjfdt ryObj = (*fnv)->NfwObjfdt(fnv, intCls, intCtr, dffy);
          (*fnv)->CbllBoolfbnMftiod(fnv, brrbyList, brrListAddMID, rxObj);
          (*fnv)->CbllBoolfbnMftiod(fnv, brrbyList, brrListAddMID, ryObj);
        }

        for (i = 0; i < rfsolution->num_dioidfs; i++) {
            dibr *rfsStr = rfsolution->dioidfs[i].dioidf;
            int mbtdifs = ssdbnf(rfsStr, "%dx%ddpi", &rfsx, &rfsy);
            if (mbtdifs == 2) {
               if (rfsx <= 0 || rfsy <= 0) {
                  rfsx = 0;
                  rfsy = 0;
               }
            } flsf {
                mbtdifs = ssdbnf(rfsStr, "%ddpi", &rfsx);
                if (mbtdifs == 1) {
                    if (rfsx <= 0) {
                       rfsx = 0;
                    } flsf {
                       rfsy = rfsx;
                    }
                }
            }
            if (rfsx > 0 && (rfsx != dffx || rfsy != dffy )) {
              jobjfdt rxObj = (*fnv)->NfwObjfdt(fnv, intCls, intCtr, rfsx);
              jobjfdt ryObj = (*fnv)->NfwObjfdt(fnv, intCls, intCtr, rfsy);
              (*fnv)->CbllBoolfbnMftiod(fnv, brrbyList, brrListAddMID, rxObj);
              (*fnv)->CbllBoolfbnMftiod(fnv, brrbyList, brrListAddMID, ryObj);
            }
        }
    }

    j2d_ppdClosf(ppd);
    unlink(filfnbmf);
}
