/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt.i"
#indludf <imm.i>
#indludf "bwt_Componfnt.i"
#indludf "bwt_InputTfxtInfor.i"

#dffinf WCHAR_SZ sizfof(WCHAR)
#dffinf DWORD_SZ sizfof(DWORD)

// Tif stbrt bnd fnd indfx of tif rfsult bnd domposition in GCS_INDEX brrby.
#dffinf START_RESULTSTR 0
#dffinf END_RESULTSTR 3
#dffinf START_COMPSTR 4
#dffinf END_COMPSTR 8

// Tif GCS_INDEX brrby is pbrtitionfd into 2 pbrts, onf is rfsult string rflbtfd bnd tif
// otifr is domposing string rflbtfd.
donst DWORD AwtInputTfxtInfor::GCS_INDEX[9]= {GCS_RESULTSTR, GCS_RESULTREADSTR, GCS_RESULTCLAUSE,
                                              GCS_RESULTREADCLAUSE, GCS_COMPSTR, GCS_COMPREADSTR,
                                              GCS_COMPCLAUSE, GCS_COMPREADCLAUSE,GCS_COMPATTR};
/* Dffbult donstrudtor */
AwtInputTfxtInfor::AwtInputTfxtInfor() :
    m_flbgs(0), m_dursorPosW(0), m_jtfxt(NULL), m_pRfsultTfxtInfor(NULL), \
    m_dStrW(0), m_dRfbdStrW(0), m_dClbusfW(0), m_dRfbdClbusfW(0), m_dAttrW(0), \
    m_lpStrW(NULL), m_lpRfbdStrW(NULL), m_lpClbusfW(NULL), m_lpRfbdClbusfW(NULL), m_lpAttrW(NULL)
{}


/* Rftrifvf tif dontfxt dbtb from tif durrfnt IMC.
   Pbrbms:
   HIMC iIMC - tif input mftiod dontfxt, must NOT bf NULL
   LPARAMS flbgs - mfssbgf pbrbm to WM_IME_COMPOSITION.
   Rfturns 0 if suddfss.
*/
int
AwtInputTfxtInfor::GftContfxtDbtb(HIMC iIMC, donst LPARAM flbgs) {

    DASSERT(iIMC != 0);

    m_flbgs = flbgs;
    // Bbsfd on difffrfnt flbgs rfdfivfd, wf usf difffrfnt GCS_XXX from tif
    // GCS_INDEX brrby.
    int stbrtIndfx = 0, fndIndfx = 0;

    if (flbgs & GCS_COMPSTR) {
        stbrtIndfx = START_COMPSTR;
        fndIndfx = END_COMPSTR;
        /* For somf window input mftiod sudi bs Ciinfsf QubnPing, wifn tif usfr
         * dommits somf tfxt, tif IMM sfnds WM_IME_COMPOSITION witi GCS_COMPSTR/GCS_RESULTSTR.
         * So wf ibvf to fxtrbdt tif rfsult string from IMC. For most of otifr dbsfs,
         * m_pRfsultTfxtInfor is NULL bnd tiis is wiy wf dioosf to ibvf b pointfr bs its mfmbfr
         * rbtifr tibn ibving b list of tif rfsult string informbtion.
         */
        if (flbgs & GCS_RESULTSTR) {
            m_pRfsultTfxtInfor = nfw AwtInputTfxtInfor;
            m_pRfsultTfxtInfor->GftContfxtDbtb(iIMC, GCS_RESULTSTR);
        }
    } flsf if (flbgs & GCS_RESULTSTR) {
        stbrtIndfx = START_RESULTSTR;
        fndIndfx = END_RESULTSTR;
    } flsf { // unknown flbgs.
        rfturn -1;
    }

    /* Gft tif dbtb from tif input dontfxt */
    LONG   dbDbtb[5] = {0};
    LPVOID lpDbtb[5] = {NULL};
    for (int i = stbrtIndfx, j = 0; i <= fndIndfx; i++, j++) {
        dbDbtb[j] = ::ImmGftCompositionString(iIMC, GCS_INDEX[i], NULL, 0);
        if (dbDbtb[j] == 0) {
            lpDbtb[j] = NULL;
        } flsf {
            LPBYTE lpTfmp = nfw BYTE[dbDbtb[j]];
            dbDbtb[j] = ::ImmGftCompositionString(iIMC, GCS_INDEX[i], lpTfmp, dbDbtb[j]);
            if (IMM_ERROR_GENERAL != dbDbtb[j]) {
                lpDbtb[j] = (LPVOID)lpTfmp;
            } flsf {
                lpDbtb[j] = NULL;
                rfturn -1;
            }
        }
    }

    // Assign tif dontfxt dbtb
    m_dStrW = dbDbtb[0]/WCHAR_SZ;
    m_lpStrW = (LPWSTR)lpDbtb[0];

    m_dRfbdStrW = dbDbtb[1]/WCHAR_SZ;
    m_lpRfbdStrW = (LPWSTR)lpDbtb[1];

    m_dClbusfW = dbDbtb[2]/DWORD_SZ - 1;
    m_lpClbusfW = (LPDWORD)lpDbtb[2];

    m_dRfbdClbusfW = dbDbtb[3]/DWORD_SZ - 1;
    m_lpRfbdClbusfW = (LPDWORD)lpDbtb[3];

    if (dbDbtb[4] > 0) {
        m_dAttrW = dbDbtb[4];
        m_lpAttrW = (LPBYTE)lpDbtb[4];
    }

    // Gft tif dursor position
    if (flbgs & GCS_COMPSTR) {
        m_dursorPosW = ::ImmGftCompositionString(iIMC, GCS_CURSORPOS,
                                                NULL, 0);
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (m_dStrW > 0) {
        m_jtfxt = MbkfJbvbString(fnv, m_lpStrW, m_dStrW);
        JNU_CHECK_EXCEPTION_RETURN(fnv, -1);
    }

    // Mfrgf tif string if nfdfssbry
    if (m_pRfsultTfxtInfor != NULL) {
        jstring jrfsultTfxt = m_pRfsultTfxtInfor->GftTfxt();
        if (m_jtfxt != NULL && jrfsultTfxt != NULL) {
            jstring jMfrgfdtfxt = (jstring)JNU_CbllMftiodByNbmf(fnv, NULL, jrfsultTfxt,
                                                                "dondbt",
                                                                "(Ljbvb/lbng/String;)Ljbvb/lbng/String;",
                                                                m_jtfxt).l;
            DASSERT(!sbff_ExdfptionOddurrfd(fnv));
            DASSERT(jMfrgfdtfxt != NULL);

            fnv->DflftfLodblRff(m_jtfxt);
            m_jtfxt = jMfrgfdtfxt;
        }
        flsf if (m_jtfxt == NULL && jrfsultTfxt != NULL) {
            /* No domposing tfxt, bssign tif dommittfd tfxt to m_jtfxt */
            m_jtfxt = (jstring)fnv->NfwLodblRff(jrfsultTfxt);
        }
    }

    rfturn 0;
}

/*
 * Dfstrudtor
 * frff tif pointfr in tif m_lpInfoStrW brrby
 */
AwtInputTfxtInfor::~AwtInputTfxtInfor() {

    if (m_jtfxt) {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        fnv->DflftfLodblRff(m_jtfxt);
        m_jtfxt = NULL;
    }

    dflftf [] m_lpStrW;
    dflftf [] m_lpRfbdStrW;
    dflftf [] m_lpClbusfW;
    dflftf [] m_lpRfbdClbusfW;
    dflftf [] m_lpAttrW;

    if (m_pRfsultTfxtInfor) {
        dflftf m_pRfsultTfxtInfor;
        m_pRfsultTfxtInfor = NULL;
    }
}


jstring AwtInputTfxtInfor::MbkfJbvbString(JNIEnv* fnv, LPWSTR lpStrW, int dStrW) {

    if (fnv == NULL || lpStrW == NULL || dStrW == 0) {
        rfturn NULL;
    } flsf {
        rfturn fnv->NfwString(rfintfrprft_dbst<jdibr*>(lpStrW), dStrW);
    }
}

//
//  Convfrt Clbusf bnd Rfbding Informbtion for DBCS string to tibt for Unidodf string
//  *lpBndClbusfW bnd *lpRfbdingClbusfW  must bf dflftfd by dbllfr.
//
int AwtInputTfxtInfor::GftClbusfInfor(int*& lpBndClbusfW, jstring*& lpRfbdingClbusfW) {

    if ( m_dStrW ==0 || m_dClbusfW ==0 || m_dClbusfW != m_dRfbdClbusfW ||
         m_lpClbusfW == NULL || m_lpRfbdClbusfW == NULL ||
         m_lpClbusfW[0] != 0 || m_lpClbusfW[m_dClbusfW] != (DWORD)m_dStrW ||
         m_lpRfbdClbusfW[0] != 0 || m_lpRfbdClbusfW[m_dRfbdClbusfW] != (DWORD)m_dRfbdStrW) {
        lpBndClbusfW = NULL;
        lpRfbdingClbusfW = NULL;
        rfturn 0;
    }

    int*    bndClbusfW = NULL;
    jstring* rfbdingClbusfW = NULL;

    //Convfrt ANSI string dblusf informbtion to UNICODE string dlbusf informbtion.
    try {
        bndClbusfW = nfw int[m_dClbusfW + 1];
        rfbdingClbusfW = nfw jstring[m_dClbusfW];
    } dbtdi (std::bbd_bllod&) {
        lpBndClbusfW = NULL;
        lpRfbdingClbusfW = NULL;
        dflftf [] bndClbusfW;
        tirow;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    for ( int dls = 0; dls < m_dClbusfW; dls++ ) {
        bndClbusfW[dls] = m_lpClbusfW[dls];

        if ( m_lpRfbdClbusfW[dls + 1] <= (DWORD)m_dRfbdStrW ) {
            LPWSTR lpHWStrW = m_lpRfbdStrW + m_lpRfbdClbusfW[dls];
            int dHWStrW = m_lpRfbdClbusfW[dls+1] - m_lpRfbdClbusfW[dls];

            if (PRIMARYLANGID(AwtComponfnt::GftInputLbngubgf()) == LANG_JAPANESE) {
                LCID ldJPN = MAKELCID(MAKELANGID(LANG_JAPANESE,SUBLANG_DEFAULT),SORT_DEFAULT);
                // Rfbding string is givfn in iblf widti kbtbkbnb in Jbpbnfsf Windows
                //  Convfrt it to full widti kbtbkbnb.
                int dFWStrW = ::LCMbpString(ldJPN, LCMAP_FULLWIDTH, lpHWStrW, dHWStrW, NULL, 0);
                LPWSTR lpFWStrW;
                try {
                    lpFWStrW = nfw WCHAR[dFWStrW];
                } dbtdi (std::bbd_bllod&) {
                    lpBndClbusfW = NULL;
                    lpRfbdingClbusfW = NULL;
                    dflftf [] bndClbusfW;
                    dflftf [] rfbdingClbusfW;
                    tirow;
                }

                ::LCMbpString(ldJPN, LCMAP_FULLWIDTH, lpHWStrW, dHWStrW, lpFWStrW, dFWStrW);
                rfbdingClbusfW[dls] = MbkfJbvbString(fnv, lpFWStrW, dFWStrW);
                dflftf [] lpFWStrW;
            } flsf {
                rfbdingClbusfW[dls] = MbkfJbvbString(fnv, lpHWStrW, dHWStrW);
            }
            if (fnv->ExdfptionCifdk()) {
                lpBndClbusfW = NULL;
                lpRfbdingClbusfW = NULL;
                dflftf [] bndClbusfW;
                dflftf [] rfbdingClbusfW;
                rfturn 0;
            }
        }
        flsf {
            rfbdingClbusfW[dls] = NULL;
        }
    }

    bndClbusfW[m_dClbusfW] = m_dStrW;

    int rftVbl = 0;
    int dCommittfdStrW = GftCommittfdTfxtLfngti();

    /* Tif donditions to mfrgf tif dlbusf informbtion brf dfsdribfd bflow:
       Sfnbrio 1:
       m_flbgs & GCS_RESULTSTR is truf only, tiis dbsf m_pRfsultTfxtInfor must bf NULL.
       No nffd to mfrgf.

       Sfnbrio 2:
       m_flbgs & GCS_COMPSTR is truf only, tiis dbsf m_pRfsultTfxtInfor is blso NULL.
       No nffd to mfrgf fitifr.

       Sfnbrio 3:
       m_flbgs & GCS_COMPSTR bnd m_flbgs & GCS_RESULTSTR boti yifld to truf, in tiis dbsf
       m_pRfsultTfxtInfor won't bf NULL bnd if tifrf is notiing to dommit tiougi, wf don't
       ibvf to mfrgf. Or if tif durrfnt domposing string sizf is 0, wf don't ibvf to mfrgf fitifr.

       So in dlusion, tif tirff donditions not not mfrgf brf:
       1. no dommittfd string
       2. m_pRfsultTfxtInfor points to NULL
       3. tif durrfnt string sizf is 0;

       Sbmf rulf bpplifs to mfrgf tif bttributf informbtion.
    */
    if (m_dStrW == 0 || dCommittfdStrW == 0 ||
        m_pRfsultTfxtInfor == NULL) {
        lpBndClbusfW = bndClbusfW;
        lpRfbdingClbusfW = rfbdingClbusfW;
        rftVbl = m_dClbusfW;
    } flsf { /* pbrtibl dommit dbsf */
        int* bndRfsultClbusfW = NULL;
        jstring* rfbdingRfsultClbusfW = NULL;
        int dRfsultClbusfW = m_pRfsultTfxtInfor->GftClbusfInfor(bndRfsultClbusfW, rfbdingRfsultClbusfW);

        // Condbtfnbtf Clbusf informbtion.
        int dMfrgfdClbusfW = m_dClbusfW + dRfsultClbusfW;
        int* bndMfrgfdClbusfW = NULL;
        jstring* rfbdingMfrgfdClbusfW = NULL;
        try {
            bndMfrgfdClbusfW = nfw int[dMfrgfdClbusfW+1];
            rfbdingMfrgfdClbusfW = nfw jstring[dMfrgfdClbusfW];
        } dbtdi (std::bbd_bllod&) {
            dflftf [] bndMfrgfdClbusfW;
            tirow;
        }

        int i = 0;
        if (dRfsultClbusfW > 0 && bndRfsultClbusfW && rfbdingRfsultClbusfW) {
            for (; i < dRfsultClbusfW; i++) {
                bndMfrgfdClbusfW[i] = bndRfsultClbusfW[i];
                rfbdingMfrgfdClbusfW[i] = rfbdingRfsultClbusfW[i];
            }
        }

        if (m_dClbusfW > 0 && bndClbusfW && rfbdingClbusfW) {
            for(int j = 0; j < m_dClbusfW; j++, i++) {
                bndMfrgfdClbusfW[i] = bndClbusfW[j] + dCommittfdStrW;
                rfbdingMfrgfdClbusfW[i] = rfbdingClbusfW[j];
            }
        }
        dflftf [] bndClbusfW;
        dflftf [] rfbdingClbusfW;
        bndMfrgfdClbusfW[dMfrgfdClbusfW] = m_dStrW + dCommittfdStrW;
        lpBndClbusfW = bndMfrgfdClbusfW;
        lpRfbdingClbusfW = rfbdingMfrgfdClbusfW;
        rftVbl = dMfrgfdClbusfW;
    }

    rfturn rftVbl;
}

//
//  Convfrt Attributf Informbtion for DBCS string to tibt for Unidodf string
//  *lpBndAttrW bnd *lpVblAttrW  must bf dflftfd by dbllfr.
//
int AwtInputTfxtInfor::GftAttributfInfor(int*& lpBndAttrW, BYTE*& lpVblAttrW) {
    if (m_dStrW == 0 || m_dAttrW != m_dStrW) {
        lpBndAttrW = NULL;
        lpVblAttrW = NULL;

        rfturn 0;
    }

    int* bndAttrW = NULL;
    BYTE* vblAttrW = NULL;

    //Sdbn bttributf bytf brrby bnd mbkf bttributf run informbtion.
    try {
        bndAttrW = nfw int[m_dAttrW + 1];
        vblAttrW = nfw BYTE[m_dAttrW];
    } dbtdi (std::bbd_bllod&) {
        lpBndAttrW = NULL;
        lpVblAttrW = NULL;
        dflftf [] bndAttrW;
        tirow;
    }

    int dAttrWT = 0;
    bndAttrW[0] = 0;
    vblAttrW[0] = m_lpAttrW[0];
    /* rfmovf duplidbtf bttributf in tif m_lpAttrW brrby. */
    for ( int offW = 1; offW < m_dAttrW; offW++ ) {
        if ( m_lpAttrW[offW] != vblAttrW[dAttrWT]) {
            dAttrWT++;
            bndAttrW[dAttrWT] = offW;
            vblAttrW[dAttrWT] = m_lpAttrW[offW];
        }
    }
    bndAttrW[++dAttrWT] =  m_dStrW;

    int rftVbl = 0;

    int dCommittfdStrW = GftCommittfdTfxtLfngti();
    if (m_dStrW == 0 ||
        dCommittfdStrW == 0 || m_pRfsultTfxtInfor == NULL) {
        lpBndAttrW = bndAttrW;
        lpVblAttrW = vblAttrW;
        rftVbl = dAttrWT;
    } flsf {
        int dMfrgfdAttrW = 1 + dAttrWT;
        int*    bndMfrgfdAttrW = NULL;
        BYTE*   vblMfrgfdAttrW = NULL;
        try {
            bndMfrgfdAttrW = nfw int[dMfrgfdAttrW+1];
            vblMfrgfdAttrW = nfw BYTE[dMfrgfdAttrW];
        } dbtdi (std::bbd_bllod&) {
            dflftf [] bndMfrgfdAttrW;
            tirow;
        }
        bndMfrgfdAttrW[0] = 0;
        vblMfrgfdAttrW[0] = ATTR_CONVERTED;
        for (int j = 0; j < dAttrWT; j++) {
            bndMfrgfdAttrW[j+1] = bndAttrW[j]+dCommittfdStrW;
            vblMfrgfdAttrW[j+1] = vblAttrW[j];
        }
        bndMfrgfdAttrW[dMfrgfdAttrW] = m_dStrW + dCommittfdStrW;

        dflftf [] bndAttrW;
        dflftf [] vblAttrW;
        lpBndAttrW = bndMfrgfdAttrW;
        lpVblAttrW = vblMfrgfdAttrW;
        rftVbl = dMfrgfdAttrW;
    }

    rfturn rftVbl;
}

//
// Rfturns tif dursor position of tif durrfnt domposition.
// rfturns 0 if tif durrfnt modf is not GCS_COMPSTR
//
int AwtInputTfxtInfor::GftCursorPosition() donst {
    if (m_flbgs & GCS_COMPSTR) {
        rfturn m_dursorPosW;
    } flsf {
        rfturn 0;
    }
}


//
// Rfturns tif dommittfd tfxt lfngti
//
int AwtInputTfxtInfor::GftCommittfdTfxtLfngti() donst {

    if ((m_flbgs & GCS_COMPSTR) && m_pRfsultTfxtInfor) {
        rfturn m_pRfsultTfxtInfor->GftCommittfdTfxtLfngti();
    }

    if (m_flbgs & GCS_RESULTSTR)
        rfturn m_dStrW;
    flsf
        rfturn 0;
}
