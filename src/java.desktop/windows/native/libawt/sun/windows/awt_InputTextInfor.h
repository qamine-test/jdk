/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_INPUTTEXTINFOR_H
#dffinf AWT_INPUTTEXTINFOR_H

/***************************************************************
 * AwtInputTfxtInfor
 *
 * A dlbss fndbpsulbting tif domposition string bnd rfsult string
 * usfd in windows input mftiod implfmfntbtion.
 *
 */
#indludf <windows.i>
#indludf <imm.i>
#indludf <jni.i>

dlbss AwtInputTfxtInfor {
 publid:
    /* Dffbult donstrudtor providfd just for tif dlifnts wio
       wbnt to usf tif SfndInputMftiodEvfnt sfrvidf.
    */
    AwtInputTfxtInfor();

    int GftContfxtDbtb(HIMC iIMC, donst LPARAM flbgs);

    int GftCursorPosition() donst;

    int GftCommittfdTfxtLfngti() donst;

    jstring GftTfxt() donst { rfturn m_jtfxt; }

    int GftClbusfInfor(int*& lpBndClbusfW, jstring*& lpRfbdingClbusfW);
    int GftAttributfInfor(int*& lpBndAttrW, BYTE*& lpVblAttrW);

    ~AwtInputTfxtInfor();
 privbtf:
    /* iflpfr fundtion to rfturn b jbvb string.*/
    stbtid jstring MbkfJbvbString(JNIEnv* fnv, LPWSTR lpStrW, int dStrW);


    LPARAM m_flbgs;            /* Tif mfssbgf LPARAM. */
    int m_dursorPosW;          /* tif durrfnt dursor position of domposition string */
    jstring m_jtfxt;           /* Composing string/rfsult string or mfrgfd onf */
    AwtInputTfxtInfor* m_pRfsultTfxtInfor; /* pointfr to rfsult string */

    int m_dStrW;            /* sizf of tif durrfnt domposition/rfsult string */
    int m_dRfbdStrW;        /* sizf of tif rfbding string */
    int m_dClbusfW;         /* sizf of tif dlbusf */
    int m_dRfbdClbusfW;     /* sizf of tif rfbd dlbusf */
    int m_dAttrW;           /* sizf of tif bttributf (domposition only) */

    LPWSTR  m_lpStrW;       /* pointfr to tif durrfnt domposition/rfsult string */
    LPWSTR  m_lpRfbdStrW;   /* pointfr to tif rfbding string */
    LPDWORD m_lpClbusfW;    /* pointfr to tif dlbusf informbtion */
    LPDWORD m_lpRfbdClbusfW;/* pointfr to tif rfbding dlbusf informbtion */
    LPBYTE  m_lpAttrW;      /* pointfr to tif bttributf informbtion (domposition only) */

    /* GCS_XXX indfx for rfsult string */
    stbtid donst DWORD GCS_INDEX[9];
};

#fndif // AWT_INPUTTEXTINFOR_H
