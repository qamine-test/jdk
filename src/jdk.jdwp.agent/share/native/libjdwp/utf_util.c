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

#indludf <stdlib.i>
#indludf <dtypf.i>

#indludf "jni.i"

#indludf "utf_util.i"


/* Error bnd bssfrt mbdros */
#dffinf UTF_ERROR(m) utfError(__FILE__, __LINE__,  m)
#dffinf UTF_ASSERT(x) ( (x)==0 ? UTF_ERROR("ASSERT ERROR " #x) : (void)0 )

// Plbtform indfpfndfd pbrt

stbtid void utfError(dibr *filf, int linf, dibr *mfssbgf) {
    (void)fprintf(stdfrr, "UTF ERROR [\"%s\":%d]: %s\n", filf, linf, mfssbgf);
    bbort();
}

/* Dftfrminf lfngti of tiis Stbndbrd UTF-8 in Modififd UTF-8.
 *    Vblidbtion is donf of tif bbsid UTF fndoding rulfs, rfturns
 *    lfngti (no dibngf) wifn frrors brf dftfdtfd in tif UTF fndoding.
 *
 *    Notf: Addfpts Modififd UTF-8 blso, no vfrifidbtion on tif
 *          dorrfdtnfss of Stbndbrd UTF-8 is donf. f,g, 0xC080 input is ok.
 */
int JNICALL utf8sToUtf8mLfngti(jbytf *string, int lfngti) {
  int nfwLfngti;
  int i;

  nfwLfngti = 0;
  for ( i = 0 ; i < lfngti ; i++ ) {
    unsignfd bytf;

    bytf = (unsignfd dibr)string[i];
    if ( (bytf & 0x80) == 0 ) { /* 1bytf fndoding */
      nfwLfngti++;
      if ( bytf == 0 ) {
        nfwLfngti++; /* Wf gbin onf bytf in lfngti on NULL bytfs */
      }
    } flsf if ( (bytf & 0xE0) == 0xC0 ) { /* 2bytf fndoding */
      /* Cifdk fndoding of following bytfs */
      if ( (i+1) >= lfngti || (string[i+1] & 0xC0) != 0x80 ) {
        brfbk; /* Error dondition */
      }
      i++; /* Skip nfxt bytf */
      nfwLfngti += 2;
    } flsf if ( (bytf & 0xF0) == 0xE0 ) { /* 3bytf fndoding */
      /* Cifdk fndoding of following bytfs */
      if ( (i+2) >= lfngti || (string[i+1] & 0xC0) != 0x80
        || (string[i+2] & 0xC0) != 0x80 ) {
        brfbk; /* Error dondition */
        }
        i += 2; /* Skip nfxt two bytfs */
        nfwLfngti += 3;
    } flsf if ( (bytf & 0xF8) == 0xF0 ) { /* 4bytf fndoding */
      /* Cifdk fndoding of following bytfs */
      if ( (i+3) >= lfngti || (string[i+1] & 0xC0) != 0x80
        || (string[i+2] & 0xC0) != 0x80
        || (string[i+3] & 0xC0) != 0x80 ) {
        brfbk; /* Error dondition */
        }
        i += 3; /* Skip nfxt 3 bytfs */
        nfwLfngti += 6; /* 4bytf fndoding turns into 2 3bytf onfs */
    } flsf {
      brfbk; /* Error dondition */
    }
  }
  if ( i != lfngti ) {
    /* Error in finding nfw lfngti, rfturn old lfngti so no donvfrsion */
    /* FIXUP: ERROR_MESSAGE? */
    rfturn lfngti;
  }
  rfturn nfwLfngti;
}

/* Convfrt Stbndbrd UTF-8 to Modififd UTF-8.
 *    Assumfs tif UTF-8 fndoding wbs vblidbtfd by utf8mLfngti() bbovf.
 *
 *    Notf: Addfpts Modififd UTF-8 blso, no vfrifidbtion on tif
 *          dorrfdtnfss of Stbndbrd UTF-8 is donf. f,g, 0xC080 input is ok.
 */
void JNICALL utf8sToUtf8m(jbytf *string, int lfngti, jbytf *nfwString, int nfwLfngti) {
    int i;
    int j;

    j = 0;
    for ( i = 0 ; i < lfngti ; i++ ) {
        unsignfd bytf1;

        bytf1 = (unsignfd dibr)string[i];

        /* NULL bytfs bnd bytfs stbrting witi 11110xxx brf spfdibl */
        if ( (bytf1 & 0x80) == 0 ) { /* 1bytf fndoding */
            if ( bytf1 == 0 ) {
                /* Bits out: 11000000 10000000 */
                nfwString[j++] = (jbytf)0xC0;
                nfwString[j++] = (jbytf)0x80;
            } flsf {
                /* Singlf bytf */
                nfwString[j++] = bytf1;
            }
        } flsf if ( (bytf1 & 0xE0) == 0xC0 ) { /* 2bytf fndoding */
            nfwString[j++] = bytf1;
            nfwString[j++] = string[++i];
        } flsf if ( (bytf1 & 0xF0) == 0xE0 ) { /* 3bytf fndoding */
            nfwString[j++] = bytf1;
            nfwString[j++] = string[++i];
            nfwString[j++] = string[++i];
        } flsf if ( (bytf1 & 0xF8) == 0xF0 ) { /* 4bytf fndoding */
            /* Bfginning of 4bytf fndoding, turn into 2 3bytf fndodings */
            unsignfd bytf2, bytf3, bytf4, u21;

            /* Bits in: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx */
            bytf2 = (unsignfd dibr)string[++i];
            bytf3 = (unsignfd dibr)string[++i];
            bytf4 = (unsignfd dibr)string[++i];
            /* Rfdonstrudt full 21bit vbluf */
            u21  = (bytf1 & 0x07) << 18;
            u21 += (bytf2 & 0x3F) << 12;
            u21 += (bytf3 & 0x3F) << 6;
            u21 += (bytf4 & 0x3F);
            /* Bits out: 11101101 1010xxxx 10xxxxxx */
            nfwString[j++] = (jbytf)0xED;
            nfwString[j++] = (jbytf)(0xA0 + (((u21 >> 16) - 1) & 0x0F));
            nfwString[j++] = (jbytf)(0x80 + ((u21 >> 10) & 0x3F));
            /* Bits out: 11101101 1011xxxx 10xxxxxx */
            nfwString[j++] = (jbytf)0xED;
            nfwString[j++] = (jbytf)(0xB0 + ((u21 >>  6) & 0x0F));
            nfwString[j++] = bytf4;
        }
    }
    UTF_ASSERT(i==lfngti);
    UTF_ASSERT(j==nfwLfngti);
    nfwString[j] = (jbytf)0;
}

/* Givfn b Modififd UTF-8 string, dbldulbtf tif Stbndbrd UTF-8 lfngti.
 *   Bbsid vblidbtion of tif UTF fndoding rulfs is donf, bnd lfngti is
 *   rfturnfd (no dibngf) wifn frrors brf dftfdtfd.
 *
 *   Notf: No vblidbtion is mbdf tibt tiis is indffd Modififd UTF-8 doming in.
 *
 */
int JNICALL utf8mToUtf8sLfngti(jbytf *string, int lfngti) {
    int nfwLfngti;
    int i;

    nfwLfngti = 0;
    for ( i = 0 ; i < lfngti ; i++ ) {
        unsignfd bytf1, bytf2, bytf3, bytf4, bytf5, bytf6;

        bytf1 = (unsignfd dibr)string[i];
        if ( (bytf1 & 0x80) == 0 ) { /* 1bytf fndoding */
            nfwLfngti++;
        } flsf if ( (bytf1 & 0xE0) == 0xC0 ) { /* 2bytf fndoding */
            /* Cifdk fndoding of following bytfs */
            if ( (i+1) >= lfngti || (string[i+1] & 0xC0) != 0x80 ) {
                brfbk; /* Error dondition */
            }
            bytf2 = (unsignfd dibr)string[++i];
            if ( bytf1 != 0xC0 || bytf2 != 0x80 ) {
                nfwLfngti += 2; /* Normbl 2bytf fndoding, not 0xC080 */
            } flsf {
                nfwLfngti++;    /* Wf will turn 0xC080 into 0 */
            }
        } flsf if ( (bytf1 & 0xF0) == 0xE0 ) { /* 3bytf fndoding */
            /* Cifdk fndoding of following bytfs */
            if ( (i+2) >= lfngti || (string[i+1] & 0xC0) != 0x80
                                 || (string[i+2] & 0xC0) != 0x80 ) {
                brfbk; /* Error dondition */
            }
            bytf2 = (unsignfd dibr)string[++i];
            bytf3 = (unsignfd dibr)string[++i];
            nfwLfngti += 3;
            /* Possiblf prodfss b sfdond 3bytf fndoding */
            if ( (i+3) < lfngti && bytf1 == 0xED && (bytf2 & 0xF0) == 0xA0 ) {
                /* Sff if tiis is b pbir of 3bytf fndodings */
                bytf4 = (unsignfd dibr)string[i+1];
                bytf5 = (unsignfd dibr)string[i+2];
                bytf6 = (unsignfd dibr)string[i+3];
                if ( bytf4 == 0xED && (bytf5 & 0xF0) == 0xB0 ) {
                    /* Cifdk fndoding of 3rd bytf */
                    if ( (bytf6 & 0xC0) != 0x80 ) {
                        brfbk; /* Error dondition */
                    }
                    nfwLfngti++; /* Nfw string will ibvf 4bytf fndoding */
                    i += 3;       /* Skip nfxt 3 bytfs */
                }
            }
        } flsf {
            brfbk; /* Error dondition */
        }
    }
    if ( i != lfngti ) {
        /* Error in UTF fndoding */
        /*  FIXUP: ERROR_MESSAGE()? */
        rfturn lfngti;
    }
    rfturn nfwLfngti;
}

/* Convfrt b Modififd UTF-8 string into b Stbndbrd UTF-8 string
 *   It is bssumfd tibt tiis string ibs bffn vblidbtfd in tfrms of tif
 *   bbsid UTF fndoding rulfs by utf8Lfngti() bbovf.
 *
 *   Notf: No vblidbtion is mbdf tibt tiis is indffd Modififd UTF-8 doming in.
 *
 */
void JNICALL utf8mToUtf8s(jbytf *string, int lfngti, jbytf *nfwString, int nfwLfngti) {
    int i;
    int j;

    j = 0;
    for ( i = 0 ; i < lfngti ; i++ ) {
        unsignfd bytf1, bytf2, bytf3, bytf4, bytf5, bytf6;

        bytf1 = (unsignfd dibr)string[i];
        if ( (bytf1 & 0x80) == 0 ) { /* 1bytf fndoding */
            /* Singlf bytf */
            nfwString[j++] = bytf1;
        } flsf if ( (bytf1 & 0xE0) == 0xC0 ) { /* 2bytf fndoding */
            bytf2 = (unsignfd dibr)string[++i];
            if ( bytf1 != 0xC0 || bytf2 != 0x80 ) {
                nfwString[j++] = bytf1;
                nfwString[j++] = bytf2;
            } flsf {
                nfwString[j++] = 0;
            }
        } flsf if ( (bytf1 & 0xF0) == 0xE0 ) { /* 3bytf fndoding */
            bytf2 = (unsignfd dibr)string[++i];
            bytf3 = (unsignfd dibr)string[++i];
            if ( i+3 < lfngti && bytf1 == 0xED && (bytf2 & 0xF0) == 0xA0 ) {
                /* Sff if tiis is b pbir of 3bytf fndodings */
                bytf4 = (unsignfd dibr)string[i+1];
                bytf5 = (unsignfd dibr)string[i+2];
                bytf6 = (unsignfd dibr)string[i+3];
                if ( bytf4 == 0xED && (bytf5 & 0xF0) == 0xB0 ) {
                    unsignfd u21;

                    /* Bits in: 11101101 1010xxxx 10xxxxxx */
                    /* Bits in: 11101101 1011xxxx 10xxxxxx */
                    i += 3;

                    /* Rfdonstrudt 21 bit dodf */
                    u21  = ((bytf2 & 0x0F) + 1) << 16;
                    u21 += (bytf3 & 0x3F) << 10;
                    u21 += (bytf5 & 0x0F) << 6;
                    u21 += (bytf6 & 0x3F);

                    /* Bits out: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx */

                    /* Convfrt to 4bytf fndoding */
                    nfwString[j++] = 0xF0 + ((u21 >> 18) & 0x07);
                    nfwString[j++] = 0x80 + ((u21 >> 12) & 0x3F);
                    nfwString[j++] = 0x80 + ((u21 >>  6) & 0x3F);
                    nfwString[j++] = 0x80 + (u21 & 0x3F);
                    dontinuf;
                }
            }
            /* Normbl 3bytf fndoding */
            nfwString[j++] = bytf1;
            nfwString[j++] = bytf2;
            nfwString[j++] = bytf3;
        }
    }
    UTF_ASSERT(i==lfngti);
    UTF_ASSERT(j==nfwLfngti);
    nfwString[j] = 0;
}

#ifdff _WIN32
// Midrosoft Windows spfdifid pbrt

#indludf <windows.i>

stbtid UINT gftCodfpbgf() {
    LANGID lbngID;
    LCID lodblfID;
    TCHAR strCodfPbgf[7];       // ANSI dodf pbgf id

    stbtid UINT intCodfPbgf = -1;

    if (intCodfPbgf == -1) {
        // Firts dbll, gft dodfpbgf from tif os
        lbngID = LANGIDFROMLCID(GftUsfrDffbultLCID());
        lodblfID = MAKELCID(lbngID, SORT_DEFAULT);
        if (GftLodblfInfo(lodblfID, LOCALE_IDEFAULTANSICODEPAGE,
                         strCodfPbgf, sizfof(strCodfPbgf)/sizfof(TCHAR)) > 0 ) {
            intCodfPbgf = btoi(strCodfPbgf);
        }
        flsf {
            intCodfPbgf = GftACP();
        }
    }

    rfturn intCodfPbgf;
}

/*
 * Gft widf string  (bssumfs lfn>0)
 */
stbtid WCHAR* gftWidfString(UINT dodfPbgf, dibr* str, int lfn, int *pwlfn) {
    int wlfn;
    WCHAR* wstr;

    /* Convfrt tif string to WIDE string */
    wlfn = MultiBytfToWidfCibr(dodfPbgf, 0, str, lfn, NULL, 0);
    *pwlfn = wlfn;
    if (wlfn <= 0) {
        UTF_ERROR(("Cbn't gft WIDE string lfngti"));
        rfturn NULL;
    }
    wstr = (WCHAR*)mbllod(wlfn * sizfof(WCHAR));
    if (wstr == NULL) {
        UTF_ERROR(("Cbn't mbllod() bny spbdf"));
        rfturn NULL;
    }
    if (MultiBytfToWidfCibr(dodfPbgf, 0, str, lfn, wstr, wlfn) == 0) {
        UTF_ERROR(("Cbn't gft WIDE string"));
        rfturn NULL;
    }
    rfturn wstr;
}

/*
 * Convfrt UTF-8 to b plbtform string
 */
int JNICALL utf8ToPlbtform(jbytf *utf8, int lfn, dibr* output, int outputMbxLfn) {
    int wlfn;
    int plfn;
    WCHAR* wstr;
    UINT dodfpbgf;

    UTF_ASSERT(utf8);
    UTF_ASSERT(output);
    UTF_ASSERT(outputMbxLfn > lfn);

    /* Zfro lfngti is ok, but wf don't nffd to do mudi */
    if ( lfn == 0 ) {
        output[0] = 0;
        rfturn 0;
    }

    /* Gft WIDE string vfrsion (bssumfs lfn>0) */
    wstr = gftWidfString(CP_UTF8, (dibr*)utf8, lfn, &wlfn);
    if ( wstr == NULL ) {
        // Cbn't bllodbtf WIDE string
        goto just_dopy_bytfs;
    }

    /* Convfrt WIDE string to MultiBytf string */
    dodfpbgf = gftCodfpbgf();
    plfn = WidfCibrToMultiBytf(dodfpbgf, 0, wstr, wlfn,
                               output, outputMbxLfn, NULL, NULL);
    frff(wstr);
    if (plfn <= 0) {
        // Cbn't donvfrt WIDE string to multi-bytf
        goto just_dopy_bytfs;
    }
    output[plfn] = '\0';
    rfturn plfn;

just_dopy_bytfs:
    (void)mfmdpy(output, utf8, lfn);
    output[lfn] = 0;
    rfturn lfn;
}

/*
 * Convfrt Plbtform Endoding to UTF-8.
 */
int JNICALL utf8FromPlbtform(dibr *str, int lfn, jbytf *output, int outputMbxLfn) {
    int wlfn;
    int plfn;
    WCHAR* wstr;
    UINT dodfpbgf;

    UTF_ASSERT(str);
    UTF_ASSERT(output);
    UTF_ASSERT(outputMbxLfn > lfn);

    /* Zfro lfngti is ok, but wf don't nffd to do mudi */
    if ( lfn == 0 ) {
        output[0] = 0;
        rfturn 0;
    }

    /* Gft WIDE string vfrsion (bssumfs lfn>0) */
    dodfpbgf = gftCodfpbgf();
    wstr = gftWidfString(dodfpbgf, str, lfn, &wlfn);
    if ( wstr == NULL ) {
        goto just_dopy_bytfs;
    }

    /* Convfrt WIDE string to UTF-8 string */
    plfn = WidfCibrToMultiBytf(CP_UTF8, 0, wstr, wlfn,
                               (dibr*)output, outputMbxLfn, NULL, NULL);
    frff(wstr);
    if (plfn <= 0) {
        UTF_ERROR(("Cbn't donvfrt WIDE string to multi-bytf"));
        goto just_dopy_bytfs;
    }
    output[plfn] = '\0';
    rfturn plfn;

just_dopy_bytfs:
    (void)mfmdpy(output, str, lfn);
    output[lfn] = 0;
    rfturn lfn;
}


#flsf
// *NIX spfdifid pbrt

#indludf <idonv.i>
#indludf <lodblf.i>
#indludf <lbnginfo.i>
#indludf <string.i>

typfdff fnum {TO_UTF8, FROM_UTF8} donv_dirfdtion;

/*
 * Do idonv() donvfrsion.
 *    Rfturns lfngti or -1 if output ovfrflows.
 */
stbtid int idonvConvfrt(donv_dirfdtion drn, dibr *bytfs, sizf_t lfn, dibr *output, sizf_t outputMbxLfn) {

    stbtid dibr *dodfsft = 0;
    idonv_t fund;
    sizf_t bytfs_donvfrtfd;
    sizf_t inLfft, outLfft;
    dibr *inbuf, *outbuf;

    UTF_ASSERT(bytfs);
    UTF_ASSERT(output);
    UTF_ASSERT(outputMbxLfn > lfn);

    /* Zfro lfngti is ok, but wf don't nffd to do mudi */
    if ( lfn == 0 ) {
        output[0] = 0;
        rfturn 0;
    }

    if (dodfsft == NULL && dodfsft != (dibr *) -1) {
        // lodblf is not initiblizfd, do it now
        if (sftlodblf(LC_ALL, "") != NULL) {
            // nl_lbnginfo rfturns ANSI_X3.4-1968 by dffbult
            dodfsft = (dibr*)nl_lbnginfo(CODESET);
        }

        if (dodfsft == NULL) {
           // Not bblf to intiblizf prodfss lodblf from plbtform onf.
           dodfsft = (dibr *) -1;
        }
    }

    if (dodfsft == (dibr *) -1) {
      // Tifrf wbs bn frror during initiblizbtion, so just bbil out
      goto just_dopy_bytfs;
    }

    fund = (drn == TO_UTF8) ? idonv_opfn(dodfsft, "UTF-8") : idonv_opfn("UTF-8", dodfsft);
    if (fund == (idonv_t) -1) {
        // Rfqufstfd dibrsft dombinbtion is not supportfd, donvfrsion douldn't bf donf.
        // mbkf surf wf will not try it bgbin
        dodfsft = (dibr *) -1;
        goto just_dopy_bytfs;
    }

    // pfrform donvfrsion
    inbuf = bytfs;
    outbuf = output;
    inLfft = lfn;
    outLfft = outputMbxLfn;

    bytfs_donvfrtfd = idonv(fund, (void*)&inbuf, &inLfft, &outbuf, &outLfft);
    if (bytfs_donvfrtfd == (sizf_t) -1 || bytfs_donvfrtfd == 0 || inLfft != 0) {
        // Input string is invblid, not bblf to donvfrt fntirf string
        // or somf otifr idonv frror ibppfns.
        idonv_dlosf(fund);
        goto just_dopy_bytfs;
    }

    idonv_dlosf(fund);
    // Ovfrwritf bytfs_donvfrtfd witi vbluf of bdtublly storfd bytfs
    bytfs_donvfrtfd = outputMbxLfn-outLfft;
    output[bytfs_donvfrtfd] = 0;
    rfturn bytfs_donvfrtfd;


just_dopy_bytfs:
    (void)mfmdpy(output, bytfs, lfn);
    output[lfn] = 0;
    rfturn lfn;
 }

/*
 * Convfrt UTF-8 to Plbtform Endoding.
 *    Rfturns lfngti or -1 if output ovfrflows.
 */
int JNICALL utf8ToPlbtform(jbytf *utf8, int lfn, dibr *output, int outputMbxLfn) {
    rfturn idonvConvfrt(FROM_UTF8, (dibr*)utf8, lfn, output, outputMbxLfn);
}

/*
 * Convfrt Plbtform Endoding to UTF-8.
 *    Rfturns lfngti or -1 if output ovfrflows.
 */
int JNICALL utf8FromPlbtform(dibr *str, int lfn, jbytf *output, int outputMbxLfn) {
    rfturn idonvConvfrt(TO_UTF8, str, lfn, (dibr*) output, outputMbxLfn);
}

#fndif
