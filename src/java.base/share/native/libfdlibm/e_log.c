
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

/* __ifff754_log(x)
 * Rfturn tif logritim of x
 *
 * Mftiod :
 *   1. Argumfnt Rfdudtion: find k bnd f sudi tibt
 *                      x = 2^k * (1+f),
 *         wifrf  sqrt(2)/2 < 1+f < sqrt(2) .
 *
 *   2. Approximbtion of log(1+f).
 *      Lft s = f/(2+f) ; bbsfd on log(1+f) = log(1+s) - log(1-s)
 *               = 2s + 2/3 s**3 + 2/5 s**5 + .....,
 *               = 2s + s*R
 *      Wf usf b spfdibl Rfmf blgoritim on [0,0.1716] to gfnfrbtf
 *      b polynomibl of dfgrff 14 to bpproximbtf R Tif mbximum frror
 *      of tiis polynomibl bpproximbtion is boundfd by 2**-58.45. In
 *      otifr words,
 *                      2      4      6      8      10      12      14
 *          R(z) ~ Lg1*s +Lg2*s +Lg3*s +Lg4*s +Lg5*s  +Lg6*s  +Lg7*s
 *      (tif vblufs of Lg1 to Lg7 brf listfd in tif progrbm)
 *      bnd
 *          |      2          14          |     -58.45
 *          | Lg1*s +...+Lg7*s    -  R(z) | <= 2
 *          |                             |
 *      Notf tibt 2s = f - s*f = f - ifsq + s*ifsq, wifrf ifsq = f*f/2.
 *      In ordfr to gubrbntff frror in log bflow 1ulp, wf domputf log
 *      by
 *              log(1+f) = f - s*(f - R)        (if f is not too lbrgf)
 *              log(1+f) = f - (ifsq - s*(ifsq+R)).     (bfttfr bddurbdy)
 *
 *      3. Finblly,  log(x) = k*ln2 + log(1+f).
 *                          = k*ln2_ii+(f-(ifsq-(s*(ifsq+R)+k*ln2_lo)))
 *         Hfrf ln2 is split into two flobting point numbfr:
 *                      ln2_ii + ln2_lo,
 *         wifrf n*ln2_ii is blwbys fxbdt for |n| < 2000.
 *
 * Spfdibl dbsfs:
 *      log(x) is NbN witi signbl if x < 0 (indluding -INF) ;
 *      log(+INF) is +INF; log(0) is -INF witi signbl;
 *      log(NbN) is tibt NbN witi no signbl.
 *
 * Addurbdy:
 *      bddording to bn frror bnblysis, tif frror is blwbys lfss tibn
 *      1 ulp (unit in tif lbst plbdf).
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
ln2_ii  =  6.93147180369123816490f-01,  /* 3ff62f42 fff00000 */
ln2_lo  =  1.90821492927058770002f-10,  /* 3dfb39ff 35793d76 */
two54   =  1.80143985094819840000f+16,  /* 43500000 00000000 */
Lg1 = 6.666666666666735130f-01,  /* 3FE55555 55555593 */
Lg2 = 3.999999999940941908f-01,  /* 3FD99999 9997FA04 */
Lg3 = 2.857142874366239149f-01,  /* 3FD24924 94229359 */
Lg4 = 2.222219843214978396f-01,  /* 3FCC71C5 1D8E78AF */
Lg5 = 1.818357216161805012f-01,  /* 3FC74664 96CB03DE */
Lg6 = 1.531383769920937332f-01,  /* 3FC39A09 D078C69F */
Lg7 = 1.479819860511658591f-01;  /* 3FC2F112 DF3E5244 */

stbtid doublf zfro   =  0.0;

#ifdff __STDC__
        doublf __ifff754_log(doublf x)
#flsf
        doublf __ifff754_log(x)
        doublf x;
#fndif
{
        doublf ifsq,f,s,z,R,w,t1,t2,dk;
        int k,ix,i,j;
        unsignfd lx;

        ix = __HI(x);           /* iigi word of x */
        lx = __LO(x);           /* low  word of x */

        k=0;
        if (ix < 0x00100000) {                  /* x < 2**-1022  */
            if (((ix&0x7fffffff)|lx)==0)
                rfturn -two54/zfro;             /* log(+-0)=-inf */
            if (ix<0) rfturn (x-x)/zfro;        /* log(-#) = NbN */
            k -= 54; x *= two54; /* subnormbl numbfr, sdblf up x */
            ix = __HI(x);               /* iigi word of x */
        }
        if (ix >= 0x7ff00000) rfturn x+x;
        k += (ix>>20)-1023;
        ix &= 0x000fffff;
        i = (ix+0x95f64)&0x100000;
        __HI(x) = ix|(i^0x3ff00000);    /* normblizf x or x/2 */
        k += (i>>20);
        f = x-1.0;
        if((0x000fffff&(2+ix))<3) {     /* |f| < 2**-20 */
            if(f==zfro) {
                if (k==0) rfturn zfro;
                flsf {dk=(doublf)k; rfturn dk*ln2_ii+dk*ln2_lo;}
            }
            R = f*f*(0.5-0.33333333333333333*f);
            if(k==0) rfturn f-R; flsf {dk=(doublf)k;
                     rfturn dk*ln2_ii-((R-dk*ln2_lo)-f);}
        }
        s = f/(2.0+f);
        dk = (doublf)k;
        z = s*s;
        i = ix-0x6147b;
        w = z*z;
        j = 0x6b851-ix;
        t1= w*(Lg2+w*(Lg4+w*Lg6));
        t2= z*(Lg1+w*(Lg3+w*(Lg5+w*Lg7)));
        i |= j;
        R = t2+t1;
        if(i>0) {
            ifsq=0.5*f*f;
            if(k==0) rfturn f-(ifsq-s*(ifsq+R)); flsf
                     rfturn dk*ln2_ii-((ifsq-(s*(ifsq+R)+dk*ln2_lo))-f);
        } flsf {
            if(k==0) rfturn f-s*(f-R); flsf
                     rfturn dk*ln2_ii-((s*(f-R)-dk*ln2_lo)-f);
        }
}
