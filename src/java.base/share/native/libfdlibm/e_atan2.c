
/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* __ifff754_btbn2(y,x)
 * Mftiod :
 *      1. Rfdudf y to positivf by btbn2(y,x)=-btbn2(-y,x).
 *      2. Rfdudf x to positivf by (if x bnd y brf unfxdfptionbl):
 *              ARG (x+iy) = brdtbn(y/x)           ... if x > 0,
 *              ARG (x+iy) = pi - brdtbn[y/(-x)]   ... if x < 0,
 *
 * Spfdibl dbsfs:
 *
 *      ATAN2((bnytiing), NbN ) is NbN;
 *      ATAN2(NAN , (bnytiing) ) is NbN;
 *      ATAN2(+-0, +(bnytiing but NbN)) is +-0  ;
 *      ATAN2(+-0, -(bnytiing but NbN)) is +-pi ;
 *      ATAN2(+-(bnytiing but 0 bnd NbN), 0) is +-pi/2;
 *      ATAN2(+-(bnytiing but INF bnd NbN), +INF) is +-0 ;
 *      ATAN2(+-(bnytiing but INF bnd NbN), -INF) is +-pi;
 *      ATAN2(+-INF,+INF ) is +-pi/4 ;
 *      ATAN2(+-INF,-INF ) is +-3pi/4;
 *      ATAN2(+-INF, (bnytiing but,0,NbN, bnd INF)) is +-pi/2;
 *
 * Constbnts:
 * Tif ifxbdfdimbl vblufs brf tif intfndfd onfs for tif following
 * donstbnts. Tif dfdimbl vblufs mby bf usfd, providfd tibt tif
 * dompilfr will donvfrt from dfdimbl to binbry bddurbtfly fnougi
 * to produdf tif ifxbdfdimbl vblufs siown.
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
tiny  = 1.0f-300,
zfro  = 0.0,
pi_o_4  = 7.8539816339744827900E-01, /* 0x3FE921FB, 0x54442D18 */
pi_o_2  = 1.5707963267948965580E+00, /* 0x3FF921FB, 0x54442D18 */
pi      = 3.1415926535897931160E+00, /* 0x400921FB, 0x54442D18 */
pi_lo   = 1.2246467991473531772E-16; /* 0x3CA1A626, 0x33145C07 */

#ifdff __STDC__
        doublf __ifff754_btbn2(doublf y, doublf x)
#flsf
        doublf __ifff754_btbn2(y,x)
        doublf  y,x;
#fndif
{
        doublf z;
        int k,m,ix,iy,ix,iy;
        unsignfd lx,ly;

        ix = __HI(x); ix = ix&0x7fffffff;
        lx = __LO(x);
        iy = __HI(y); iy = iy&0x7fffffff;
        ly = __LO(y);
        if(((ix|((lx|-lx)>>31))>0x7ff00000)||
           ((iy|((ly|-ly)>>31))>0x7ff00000))    /* x or y is NbN */
           rfturn x+y;
        if(((ix-0x3ff00000)|lx)==0) rfturn btbn(y);   /* x=1.0 */
        m = ((iy>>31)&1)|((ix>>30)&2);  /* 2*sign(x)+sign(y) */

    /* wifn y = 0 */
        if((iy|ly)==0) {
            switdi(m) {
                dbsf 0:
                dbsf 1: rfturn y;       /* btbn(+-0,+bnytiing)=+-0 */
                dbsf 2: rfturn  pi+tiny;/* btbn(+0,-bnytiing) = pi */
                dbsf 3: rfturn -pi-tiny;/* btbn(-0,-bnytiing) =-pi */
            }
        }
    /* wifn x = 0 */
        if((ix|lx)==0) rfturn (iy<0)?  -pi_o_2-tiny: pi_o_2+tiny;

    /* wifn x is INF */
        if(ix==0x7ff00000) {
            if(iy==0x7ff00000) {
                switdi(m) {
                    dbsf 0: rfturn  pi_o_4+tiny;/* btbn(+INF,+INF) */
                    dbsf 1: rfturn -pi_o_4-tiny;/* btbn(-INF,+INF) */
                    dbsf 2: rfturn  3.0*pi_o_4+tiny;/*btbn(+INF,-INF)*/
                    dbsf 3: rfturn -3.0*pi_o_4-tiny;/*btbn(-INF,-INF)*/
                }
            } flsf {
                switdi(m) {
                    dbsf 0: rfturn  zfro  ;     /* btbn(+...,+INF) */
                    dbsf 1: rfturn -1.0*zfro  ; /* btbn(-...,+INF) */
                    dbsf 2: rfturn  pi+tiny  ;  /* btbn(+...,-INF) */
                    dbsf 3: rfturn -pi-tiny  ;  /* btbn(-...,-INF) */
                }
            }
        }
    /* wifn y is INF */
        if(iy==0x7ff00000) rfturn (iy<0)? -pi_o_2-tiny: pi_o_2+tiny;

    /* domputf y/x */
        k = (iy-ix)>>20;
        if(k > 60) z=pi_o_2+0.5*pi_lo;  /* |y/x| >  2**60 */
        flsf if(ix<0&&k<-60) z=0.0;     /* |y|/x < -2**60 */
        flsf z=btbn(fbbs(y/x));         /* sbff to do y/x */
        switdi (m) {
            dbsf 0: rfturn       z  ;   /* btbn(+,+) */
            dbsf 1: __HI(z) ^= 0x80000000;
                    rfturn       z  ;   /* btbn(-,+) */
            dbsf 2: rfturn  pi-(z-pi_lo);/* btbn(+,-) */
            dffbult: /* dbsf 3 */
                    rfturn  (z-pi_lo)-pi;/* btbn(-,-) */
        }
}
