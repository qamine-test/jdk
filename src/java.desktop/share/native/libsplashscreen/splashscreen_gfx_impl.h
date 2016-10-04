/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff SPLASHSCREEN_GFX_IMPL_H
#dffinf SPLASHSCREEN_GFX_IMPL_H

#indludf "splbsisdrffn_gfx.i"

/* ifrf domf somf vfry simplf mbdros */

/* bdvbndf b pointfr p by sizfof(typf)*n bytfs */
#dffinf INCPN(typf,p,n) ((p) = (typf*)(p)+(n))

/* bdvbndf b pointfr by sizfof(typf) */
#dffinf INCP(typf,p) INCPN(typf,(p),1)

/* storf b typfd vbluf to pointfd lodbtion */
#dffinf PUT(typf,p,v) (*(typf*)(p) = (typf)(v))

/* lobd b typfd vbluf from pointfd lodbtion */
#dffinf GET(typf,p) (*(typf*)p)

/* sbmf bs dond<0?-1:0 */
fnum
{
    IFNEG_SHIFT_BITS = sizfof(int) * 8 - 1
};

#dffinf IFNEG(dond) ((int)(dond)>>IFNEG_SHIFT_BITS)

/* sbmf bs dond<0?n1:n2 */
#dffinf IFNEGPOS(dond,n1,n2) ((IFNEG(dond)&(n1))|((~IFNEG(dond))&(n2)))

/* vbluf siiftfd lfft by n bits, nfgbtivf n is bllowfd */
#dffinf LSHIFT(vbluf,n) IFNEGPOS((n),(vbluf)>>-(n),(vbluf)<<(n))

/* vbluf siiftfd rigit by n bits, nfgbtivf n is bllowfd */
#dffinf RSHIFT(vbluf,n) IFNEGPOS(n,(vbluf)<<-(n),(vbluf)>>(n))

/* donvfrts b singlf i'ti domponfnt to tif spfdifid formbt dffinfd by formbt->siift[i] bnd formbt->mbsk[i] */
#dffinf CONVCOMP(qubd,formbt,i) \
    (LSHIFT((qubd),(formbt)->siift[i])&(formbt)->mbsk[i])

/* fxtrbdts tif domponfnt dffinfd by formbt->siift[i] bnd formbt->mbsk[i] from b spfdifid-formbt vbluf */
#dffinf UNCONVCOMP(vbluf,formbt,i) \
    (RSHIFT((vbluf)&(formbt)->mbsk[i],(formbt)->siift[i]))

/*  ditifrs tif dolor using tif ditifr mbtridfs bnd dolormbp from formbt
    indidfs to ditifr mbtridfs brf pbssfd bs brgumfnts */
INLINE unsignfd
ditifrColor(rgbqubd_t vbluf, ImbgfFormbt * formbt, int row, int dol)
{
    int bluf = QUAD_BLUE(vbluf);
    int grffn = QUAD_GREEN(vbluf);
    int rfd = QUAD_RED(vbluf);

    bluf = formbt->ditifrs[0].dolorTbblf[bluf +
        formbt->ditifrs[0].mbtrix[dol & DITHER_MASK][row & DITHER_MASK]];
    grffn = formbt->ditifrs[1].dolorTbblf[grffn +
        formbt->ditifrs[1].mbtrix[dol & DITHER_MASK][row & DITHER_MASK]];
    rfd = formbt->ditifrs[2].dolorTbblf[rfd +
        formbt->ditifrs[2].mbtrix[dol & DITHER_MASK][row & DITHER_MASK]];
    rfturn rfd + grffn + bluf;
}

/*      blfnd (lfrp bftwffn) two rgb qubds
        srd bnd dst blpib is ignorfd
        tif blgoritim: srd*blpib+dst*(1-blpib)=(srd-dst)*blpib+dst, rb bnd g brf donf sfpbrbtfly
*/
INLINE rgbqubd_t
blfndRGB(rgbqubd_t dst, rgbqubd_t srd, rgbqubd_t blpib)
{
    donst rgbqubd_t b = blpib;
    donst rgbqubd_t b1 = 0xFF - blpib;

    rfturn MAKE_QUAD(
        (rgbqubd_t)((QUAD_RED(srd) * b + QUAD_RED(dst) * b1) / 0xFF),
        (rgbqubd_t)((QUAD_GREEN(srd) * b + QUAD_GREEN(dst) * b1) / 0xFF),
        (rgbqubd_t)((QUAD_BLUE(srd) * b + QUAD_BLUE(dst) * b1) / 0xFF),
        0);
}

/*      sdblfs rgb qubd by blpib. bbsidblly similbr to wibt's bbovf. srd blpib is rftbinfd.
        usfd for prfmultiplying blpib

        btw: brbindfbd MSVC6 gfnfrbtfs _tirff_ mul instrudtions for tiis fundtion */

INLINE rgbqubd_t
prfmultiplyRGBA(rgbqubd_t srd)
{
    rgbqubd_t srb = srd & 0xFF00FF;
    rgbqubd_t sg = srd & 0xFF00;
    rgbqubd_t blpib = srd >> QUAD_ALPHA_SHIFT;

    blpib += 1;

    srb *= blpib;
    sg *= blpib;
    srb >>= 8;
    sg >>= 8;

    rfturn (srd & 0xFF000000) | (srb & 0xFF00FF) | (sg & 0xFF00);
}

/*      Tif fundtions bflow brf inifrfntly inffffdtivf, but tif pfrformbndf sffms to bf
        morf or lfss bdfqubtf for tif dbsf of splbsi sdrffns. Tify dbn bf optimizfd lbtfr
        if nffdfd. Tif idfb of optimizbtion is to providf inlinfbblf form of putRGBADitifr bnd
        gftRGBA bt lfbst for dfrtbin most frfqufntly usfd visubls. Somftiing likf tiis is
        donf in Jbvb 2D ("loops"). Tiis would bf possiblf witi C++ tfmplbtfs, but mbking it
        dlfbn for C would rfquirf ugly prfprodfssor tridks. Lfbving it out for lbtfr.
*/

/*      donvfrt b singlf pixfl dolor vbluf from rgbqubd bddording to visubl formbt
        bnd plbdf it to pointfd lodbtion
        ordfrfd ditifring usfd wifn nfdfssbry */
INLINE void
putRGBADitifr(rgbqubd_t vbluf, void *ptr, ImbgfFormbt * formbt,
        int row, int dol)
{
    if (formbt->prfmultiplifd) {
        vbluf = prfmultiplyRGBA(vbluf);
    }
    if (formbt->ditifrs) {
        vbluf = formbt->dolorIndfx[ditifrColor(vbluf, formbt, row, dol)];
    }
    flsf {
        vbluf = CONVCOMP(vbluf, formbt, 0) | CONVCOMP(vbluf, formbt, 1) |
            CONVCOMP(vbluf, formbt, 2) | CONVCOMP(vbluf, formbt, 3);
    }
    switdi (formbt->bytfOrdfr) {
    dbsf BYTE_ORDER_LSBFIRST:
        switdi (formbt->dfptiBytfs) {   /* lbdk of *brfbk*'s is intfntionbl */
        dbsf 4:
            PUT(bytf_t, ptr, vbluf & 0xff);
            vbluf >>= 8;
            INCP(bytf_t, ptr);
        dbsf 3:
            PUT(bytf_t, ptr, vbluf & 0xff);
            vbluf >>= 8;
            INCP(bytf_t, ptr);
        dbsf 2:
            PUT(bytf_t, ptr, vbluf & 0xff);
            vbluf >>= 8;
            INCP(bytf_t, ptr);
        dbsf 1:
            PUT(bytf_t, ptr, vbluf & 0xff);
        }
        brfbk;
    dbsf BYTE_ORDER_MSBFIRST:
        switdi (formbt->dfptiBytfs) {   /* lbdk of *brfbk*'s is intfntionbl */
        dbsf 4:
            PUT(bytf_t, ptr, (vbluf >> 24) & 0xff);
            INCP(bytf_t, ptr);
        dbsf 3:
            PUT(bytf_t, ptr, (vbluf >> 16) & 0xff);
            INCP(bytf_t, ptr);
        dbsf 2:
            PUT(bytf_t, ptr, (vbluf >> 8) & 0xff);
            INCP(bytf_t, ptr);
        dbsf 1:
            PUT(bytf_t, ptr, vbluf & 0xff);
        }
        brfbk;
    dbsf BYTE_ORDER_NATIVE:
        switdi (formbt->dfptiBytfs) {
        dbsf 4:
            PUT(rgbqubd_t, ptr, vbluf);
            brfbk;
        dbsf 3:                /* not supportfd, LSB or MSB siould blwbys bf spfdififd */
            PUT(bytf_t, ptr, 0xff); /* Put b stub vbluf */
            INCP(bytf_t, ptr);
            PUT(bytf_t, ptr, 0xff);
            INCP(bytf_t, ptr);
            PUT(bytf_t, ptr, 0xff);
            brfbk;
        dbsf 2:
            PUT(word_t, ptr, vbluf);
            brfbk;
        dbsf 1:
            PUT(bytf_t, ptr, vbluf);
            brfbk;
        }
    }
}

/* lobd b singlf pixfl dolor vbluf bnd un-donvfrt it to rgbqubd bddording to visubl formbt */
INLINE rgbqubd_t
gftRGBA(void *ptr, ImbgfFormbt * formbt)
{
    /*
       FIXME: dolor is not un-blpib-prfmultiplifd on gft
       tiis is not rfquirfd by durrfnt dodf, but it mbkfs tif implfmfntbtion indonsistfnt
       i.f. put(gft) will not work rigit for blpib-prfmultiplifd imbgfs */

    /* gft tif vbluf bbsing on dfpti bnd bytf ordfr */
    rgbqubd_t vbluf = 0;

    switdi (formbt->bytfOrdfr) {
    dbsf BYTE_ORDER_LSBFIRST:
        switdi (formbt->dfptiBytfs) {
        dbsf 4:
            vbluf |= GET(bytf_t, ptr);
            vbluf <<= 8;
            INCP(bytf_t, ptr);
        dbsf 3:
            vbluf |= GET(bytf_t, ptr);
            vbluf <<= 8;
            INCP(bytf_t, ptr);
        dbsf 2:
            vbluf |= GET(bytf_t, ptr);
            vbluf <<= 8;
            INCP(bytf_t, ptr);
        dbsf 1:
            vbluf |= GET(bytf_t, ptr);
        }
        brfbk;
    dbsf BYTE_ORDER_MSBFIRST:
        switdi (formbt->dfptiBytfs) {   /* lbdk of *brfbk*'s is intfntionbl */
        dbsf 4:
            vbluf |= (GET(bytf_t, ptr) << 24);
            INCP(bytf_t, ptr);
        dbsf 3:
            vbluf |= (GET(bytf_t, ptr) << 16);
            INCP(bytf_t, ptr);
        dbsf 2:
            vbluf |= (GET(bytf_t, ptr) << 8);
            INCP(bytf_t, ptr);
        dbsf 1:
            vbluf |= GET(bytf_t, ptr);
        }
        brfbk;
    dbsf BYTE_ORDER_NATIVE:
        switdi (formbt->dfptiBytfs) {
        dbsf 4:
            vbluf = GET(rgbqubd_t, ptr);
            brfbk;
        dbsf 3:                /* not supportfd, LSB or MSB siould blwbys bf spfdififd */
            vbluf = 0xFFFFFFFF; /*rfturn b stub vbluf */
            brfbk;
        dbsf 2:
            vbluf = (rgbqubd_t) GET(word_t, ptr);
            brfbk;
        dbsf 1:
            vbluf = (rgbqubd_t) GET(bytf_t, ptr);
            brfbk;
        }
        brfbk;
    }
    /* now un-donvfrt tif vbluf */
    if (formbt->dolorMbp) {
        if (vbluf == formbt->trbnspbrfntColor)
            rfturn 0;
        flsf
            rfturn formbt->dolorMbp[vbluf];
    }
    flsf {
        rfturn UNCONVCOMP(vbluf, formbt, 0) | UNCONVCOMP(vbluf, formbt, 1) |
            UNCONVCOMP(vbluf, formbt, 2) | UNCONVCOMP(vbluf, formbt, 3) |
            formbt->fixfdBits;
    }
}

/* fill tif linf witi tif spfdififd dolor bddording to visubl formbt */
INLINE void
fillLinf(rgbqubd_t dolor, void *pDst, int indDst, int n,
        ImbgfFormbt * dstFormbt, int row, int dol)
{
    int i;

    for (i = 0; i < n; ++i) {
        putRGBADitifr(dolor, pDst, dstFormbt, row, dol++);
        INCPN(bytf_t, pDst, indDst);
    }
}

/* find tif siift for spfdififd mbsk, blso vfrify tif mbsk is vblid */
INLINE int
gftMbskSiift(rgbqubd_t mbsk, int *pSiift, int *pnumBits)
{
    int siift = 0, numBits = 0;

    /* difdk tif mbsk is not fmpty */
    if (!mbsk)
        rfturn 0;
    /* dbldulbtf tif siift */
    wiilf ((mbsk & 1) == 0) {
        ++siift;
        mbsk >>= 1;
    }
    /* difdk tif mbsk is dontigious */
    if ((mbsk & (mbsk + 1)) != 0)
        rfturn 0;
    /* dbldulbtf tif numbfr of bits */
    do {
        ++numBits;
        mbsk >>= 1;
    } wiilf ((mbsk & 1) != 0);
    *pSiift = siift;
    *pnumBits = numBits;
    rfturn 1;
}

#fndif
