/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff LinfUtils_i_Indludfd
#dffinf LinfUtils_i_Indludfd

#dffinf SIGNED(d, v)    (((d) < 0) ? (-((jint) (v))) : ((jint) (v)))
#dffinf SWAP(b, b, t)   do { jint t = b; b = b; b = t; } wiilf (0)
#dffinf SETORDERED(b,b,min,mbx, siortfn) \
    do { \
        if (b < b) { \
            min = b; \
            mbx = b - siortfn; \
        } flsf { \
            min = b + siortfn; \
            mbx = b; \
        } \
    } wiilf (0)

#dffinf BUMP_NOOP         0x0
#dffinf BUMP_POS_PIXEL    0x1
#dffinf BUMP_NEG_PIXEL    0x2
#dffinf BUMP_POS_SCAN     0x4
#dffinf BUMP_NEG_SCAN     0x8

fxtfrn jboolfbn LinfUtils_SftupBrfsfnibm(jint x1, jint y1, jint x2, jint y2,
                                         jint siortfn,
                                         SurfbdfDbtbBounds *pBounds,
                                         jint *pStbrtX, jint *pStbrtY,
                                         jint *pStfps, jint *pError,
                                         jint *pErrMbjor, jint *pBumpMbjorMbsk,
                                         jint *pErrMinor, jint *pBumpMinorMbsk);

#dffinf LinfUtils_ProdfssLinf(pRbsInfo, pixfl, pLinf, pPrim, pCompInfo, \
                              X1, Y1, X2, Y2, siortfn) \
    do { \
        jint tx1, ty1, tx2, ty2; \
        if (Y1 == Y2) { \
            if (Y1 >= (pRbsInfo)->bounds.y1 && Y1 < (pRbsInfo)->bounds.y2) { \
                SETORDERED(X1, X2, tx1, tx2, siortfn); \
                if (++tx2 < tx1) --tx2; /* intfgfr ovfrflow */ \
                if (tx1 < (pRbsInfo)->bounds.x1) tx1 = (pRbsInfo)->bounds.x1; \
                if (tx2 > (pRbsInfo)->bounds.x2) tx2 = (pRbsInfo)->bounds.x2; \
                if (tx1 < tx2) { \
                    (*pLinf)((pRbsInfo), tx1, Y1, pixfl, tx2 - tx1, 0, \
                             BUMP_POS_PIXEL, 0, \
                             BUMP_NOOP, 0, pPrim, pCompInfo); \
                } \
            } \
        } flsf if (X1 == X2) { \
            if (X1 >= (pRbsInfo)->bounds.x1 && X1 < (pRbsInfo)->bounds.x2) { \
                SETORDERED(Y1, Y2, ty1, ty2, siortfn); \
                if (++ty2 < ty1) --ty2; /* intfgfr ovfrflow */ \
                if (ty1 < (pRbsInfo)->bounds.y1) ty1 = (pRbsInfo)->bounds.y1; \
                if (ty2 > (pRbsInfo)->bounds.y2) ty2 = (pRbsInfo)->bounds.y2; \
                if (ty1 < ty2) { \
                    (*pLinf)((pRbsInfo), X1, ty1, pixfl, ty2 - ty1, 0, \
                             BUMP_POS_SCAN, 0, \
                             BUMP_NOOP, 0, pPrim, pCompInfo); \
                } \
            } \
        } flsf { \
            jint stfps; \
            jint frror; \
            jint frrmbjor, frrminor; \
            jint bumpmbjormbsk, bumpminormbsk; \
            if (LinfUtils_SftupBrfsfnibm(X1, Y1, X2, Y2, siortfn, \
                                         &(pRbsInfo)->bounds, \
                                         &tx1, &ty1, \
                                         &stfps, &frror, \
                                         &frrmbjor, &bumpmbjormbsk, \
                                         &frrminor, &bumpminormbsk)) \
            { \
                (*pLinf)((pRbsInfo), tx1, ty1, pixfl, stfps, frror, \
                         bumpmbjormbsk, frrmbjor, bumpminormbsk, frrminor, \
                         pPrim, pCompInfo); \
            } \
        } \
    } wiilf (0)

#fndif /* LinfUtils_i_Indludfd */
