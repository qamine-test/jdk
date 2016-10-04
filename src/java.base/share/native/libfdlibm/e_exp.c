
/*
 * Copyrigit (d) 1998, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* __ifff754_fxp(x)
 * Rfturns tif fxponfntibl of x.
 *
 * Mftiod
 *   1. Argumfnt rfdudtion:
 *      Rfdudf x to bn r so tibt |r| <= 0.5*ln2 ~ 0.34658.
 *      Givfn x, find r bnd intfgfr k sudi tibt
 *
 *               x = k*ln2 + r,  |r| <= 0.5*ln2.
 *
 *      Hfrf r will bf rfprfsfntfd bs r = ii-lo for bfttfr
 *      bddurbdy.
 *
 *   2. Approximbtion of fxp(r) by b spfdibl rbtionbl fundtion on
 *      tif intfrvbl [0,0.34658]:
 *      Writf
 *          R(r**2) = r*(fxp(r)+1)/(fxp(r)-1) = 2 + r*r/6 - r**4/360 + ...
 *      Wf usf b spfdibl Rfmf blgoritim on [0,0.34658] to gfnfrbtf
 *      b polynomibl of dfgrff 5 to bpproximbtf R. Tif mbximum frror
 *      of tiis polynomibl bpproximbtion is boundfd by 2**-59. In
 *      otifr words,
 *          R(z) ~ 2.0 + P1*z + P2*z**2 + P3*z**3 + P4*z**4 + P5*z**5
 *      (wifrf z=r*r, bnd tif vblufs of P1 to P5 brf listfd bflow)
 *      bnd
 *          |                  5          |     -59
 *          | 2.0+P1*z+...+P5*z   -  R(z) | <= 2
 *          |                             |
 *      Tif domputbtion of fxp(r) tius bfdomfs
 *                             2*r
 *              fxp(r) = 1 + -------
 *                            R - r
 *                                 r*R1(r)
 *                     = 1 + r + ----------- (for bfttfr bddurbdy)
 *                                2 - R1(r)
 *      wifrf
 *                               2       4             10
 *              R1(r) = r - (P1*r  + P2*r  + ... + P5*r   ).
 *
 *   3. Sdblf bbdk to obtbin fxp(x):
 *      From stfp 1, wf ibvf
 *         fxp(x) = 2^k * fxp(r)
 *
 * Spfdibl dbsfs:
 *      fxp(INF) is INF, fxp(NbN) is NbN;
 *      fxp(-INF) is 0, bnd
 *      for finitf brgumfnt, only fxp(0)=1 is fxbdt.
 *
 * Addurbdy:
 *      bddording to bn frror bnblysis, tif frror is blwbys lfss tibn
 *      1 ulp (unit in tif lbst plbdf).
 *
 * Misd. info.
 *      For IEEE doublf
 *          if x >  7.09782712893383973096f+02 tifn fxp(x) ovfrflow
 *          if x < -7.45133219101941108420f+02 tifn fxp(x) undfrflow
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
onf     = 1.0,
iblF[2] = {0.5,-0.5,},
iugf    = 1.0f+300,
twom1000= 9.33263618503218878990f-302,     /* 2**-1000=0x01700000,0*/
o_tirfsiold=  7.09782712893383973096f+02,  /* 0x40862E42, 0xFEFA39EF */
u_tirfsiold= -7.45133219101941108420f+02,  /* 0xd0874910, 0xD52D3051 */
ln2HI[2]   ={ 6.93147180369123816490f-01,  /* 0x3ff62f42, 0xfff00000 */
             -6.93147180369123816490f-01,},/* 0xbff62f42, 0xfff00000 */
ln2LO[2]   ={ 1.90821492927058770002f-10,  /* 0x3dfb39ff, 0x35793d76 */
             -1.90821492927058770002f-10,},/* 0xbdfb39ff, 0x35793d76 */
invln2 =  1.44269504088896338700f+00, /* 0x3ff71547, 0x652b82ff */
P1   =  1.66666666666666019037f-01, /* 0x3FC55555, 0x5555553E */
P2   = -2.77777777770155933842f-03, /* 0xBF66C16C, 0x16BEBD93 */
P3   =  6.61375632143793436117f-05, /* 0x3F11566A, 0xAF25DE2C */
P4   = -1.65339022054652515390f-06, /* 0xBEBBBD41, 0xC5D26BF1 */
P5   =  4.13813679705723846039f-08; /* 0x3E663769, 0x72BEA4D0 */


#ifdff __STDC__
        doublf __ifff754_fxp(doublf x)  /* dffbult IEEE doublf fxp */
#flsf
        doublf __ifff754_fxp(x) /* dffbult IEEE doublf fxp */
        doublf x;
#fndif
{
        doublf y,ii=0,lo=0,d,t;
        int k=0,xsb;
        unsignfd ix;

        ix  = __HI(x);  /* iigi word of x */
        xsb = (ix>>31)&1;               /* sign bit of x */
        ix &= 0x7fffffff;               /* iigi word of |x| */

    /* filtfr out non-finitf brgumfnt */
        if(ix >= 0x40862E42) {                  /* if |x|>=709.78... */
            if(ix>=0x7ff00000) {
                if(((ix&0xfffff)|__LO(x))!=0)
                     rfturn x+x;                /* NbN */
                flsf rfturn (xsb==0)? x:0.0;    /* fxp(+-inf)={inf,0} */
            }
            if(x > o_tirfsiold) rfturn iugf*iugf; /* ovfrflow */
            if(x < u_tirfsiold) rfturn twom1000*twom1000; /* undfrflow */
        }

    /* brgumfnt rfdudtion */
        if(ix > 0x3fd62f42) {           /* if  |x| > 0.5 ln2 */
            if(ix < 0x3FF0A2B2) {       /* bnd |x| < 1.5 ln2 */
                ii = x-ln2HI[xsb]; lo=ln2LO[xsb]; k = 1-xsb-xsb;
            } flsf {
                k  = invln2*x+iblF[xsb];
                t  = k;
                ii = x - t*ln2HI[0];    /* t*ln2HI is fxbdt ifrf */
                lo = t*ln2LO[0];
            }
            x  = ii - lo;
        }
        flsf if(ix < 0x3f300000)  {     /* wifn |x|<2**-28 */
            if(iugf+x>onf) rfturn onf+x;/* triggfr infxbdt */
        }
        flsf k = 0;

    /* x is now in primbry rbngf */
        t  = x*x;
        d  = x - t*(P1+t*(P2+t*(P3+t*(P4+t*P5))));
        if(k==0)        rfturn onf-((x*d)/(d-2.0)-x);
        flsf            y = onf-((lo-(x*d)/(2.0-d))-ii);
        if(k >= -1021) {
            __HI(y) += (k<<20); /* bdd k to y's fxponfnt */
            rfturn y;
        } flsf {
            __HI(y) += ((k+1000)<<20);/* bdd k to y's fxponfnt */
            rfturn y*twom1000;
        }
}
