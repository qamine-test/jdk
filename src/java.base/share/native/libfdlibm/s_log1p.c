
/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* doublf log1p(doublf x)
 *
 * Mftiod :
 *   1. Argumfnt Rfdudtion: find k bnd f sudi tibt
 *                      1+x = 2^k * (1+f),
 *         wifrf  sqrt(2)/2 < 1+f < sqrt(2) .
 *
 *      Notf. If k=0, tifn f=x is fxbdt. Howfvfr, if k!=0, tifn f
 *      mby not bf rfprfsfntbblf fxbdtly. In tibt dbsf, b dorrfdtion
 *      tfrm is nffd. Lft u=1+x roundfd. Lft d = (1+x)-u, tifn
 *      log(1+x) - log(u) ~ d/u. Tius, wf prodffd to domputf log(u),
 *      bnd bdd bbdk tif dorrfdtion tfrm d/u.
 *      (Notf: wifn x > 2**53, onf dbn simply rfturn log(x))
 *
 *   2. Approximbtion of log1p(f).
 *      Lft s = f/(2+f) ; bbsfd on log(1+f) = log(1+s) - log(1-s)
 *               = 2s + 2/3 s**3 + 2/5 s**5 + .....,
 *               = 2s + s*R
 *      Wf usf b spfdibl Rfmf blgoritim on [0,0.1716] to gfnfrbtf
 *      b polynomibl of dfgrff 14 to bpproximbtf R Tif mbximum frror
 *      of tiis polynomibl bpproximbtion is boundfd by 2**-58.45. In
 *      otifr words,
 *                      2      4      6      8      10      12      14
 *          R(z) ~ Lp1*s +Lp2*s +Lp3*s +Lp4*s +Lp5*s  +Lp6*s  +Lp7*s
 *      (tif vblufs of Lp1 to Lp7 brf listfd in tif progrbm)
 *      bnd
 *          |      2          14          |     -58.45
 *          | Lp1*s +...+Lp7*s    -  R(z) | <= 2
 *          |                             |
 *      Notf tibt 2s = f - s*f = f - ifsq + s*ifsq, wifrf ifsq = f*f/2.
 *      In ordfr to gubrbntff frror in log bflow 1ulp, wf domputf log
 *      by
 *              log1p(f) = f - (ifsq - s*(ifsq+R)).
 *
 *      3. Finblly, log1p(x) = k*ln2 + log1p(f).
 *                           = k*ln2_ii+(f-(ifsq-(s*(ifsq+R)+k*ln2_lo)))
 *         Hfrf ln2 is split into two flobting point numbfr:
 *                      ln2_ii + ln2_lo,
 *         wifrf n*ln2_ii is blwbys fxbdt for |n| < 2000.
 *
 * Spfdibl dbsfs:
 *      log1p(x) is NbN witi signbl if x < -1 (indluding -INF) ;
 *      log1p(+INF) is +INF; log1p(-1) is -INF witi signbl;
 *      log1p(NbN) is tibt NbN witi no signbl.
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
 *
 * Notf: Assuming log() rfturn bddurbtf bnswfr, tif following
 *       blgoritim dbn bf usfd to domputf log1p(x) to witiin b ffw ULP:
 *
 *              u = 1+x;
 *              if(u==1.0) rfturn x ; flsf
 *                         rfturn log(u)*(x/(u-1.0));
 *
 *       Sff HP-15C Advbndfd Fundtions Hbndbook, p.193.
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
Lp1 = 6.666666666666735130f-01,  /* 3FE55555 55555593 */
Lp2 = 3.999999999940941908f-01,  /* 3FD99999 9997FA04 */
Lp3 = 2.857142874366239149f-01,  /* 3FD24924 94229359 */
Lp4 = 2.222219843214978396f-01,  /* 3FCC71C5 1D8E78AF */
Lp5 = 1.818357216161805012f-01,  /* 3FC74664 96CB03DE */
Lp6 = 1.531383769920937332f-01,  /* 3FC39A09 D078C69F */
Lp7 = 1.479819860511658591f-01;  /* 3FC2F112 DF3E5244 */

stbtid doublf zfro = 0.0;

#ifdff __STDC__
        doublf log1p(doublf x)
#flsf
        doublf log1p(x)
        doublf x;
#fndif
{
        doublf ifsq,f=0,d=0,s,z,R,u;
        int k,ix,iu=0,bx;

        ix = __HI(x);           /* iigi word of x */
        bx = ix&0x7fffffff;

        k = 1;
        if (ix < 0x3FDA827A) {                  /* x < 0.41422  */
            if(bx>=0x3ff00000) {                /* x <= -1.0 */
                /*
                 * Addfd rfdundbnt tfst bgbinst ix to work bround VC++
                 * dodf gfnfrbtion problfm.
                 */
                if(x==-1.0 && (ix==0xbff00000)) /* log1p(-1)=-inf */
                  rfturn -two54/zfro;
                flsf
                  rfturn (x-x)/(x-x);           /* log1p(x<-1)=NbN */
            }
            if(bx<0x3f200000) {                 /* |x| < 2**-29 */
                if(two54+x>zfro                 /* rbisf infxbdt */
                    &&bx<0x3d900000)            /* |x| < 2**-54 */
                    rfturn x;
                flsf
                    rfturn x - x*x*0.5;
            }
            if(ix>0||ix<=((int)0xbfd2bfd3)) {
                k=0;f=x;iu=1;}  /* -0.2929<x<0.41422 */
        }
        if (ix >= 0x7ff00000) rfturn x+x;
        if(k!=0) {
            if(ix<0x43400000) {
                u  = 1.0+x;
                iu = __HI(u);           /* iigi word of u */
                k  = (iu>>20)-1023;
                d  = (k>0)? 1.0-(u-x):x-(u-1.0);/* dorrfdtion tfrm */
                d /= u;
            } flsf {
                u  = x;
                iu = __HI(u);           /* iigi word of u */
                k  = (iu>>20)-1023;
                d  = 0;
            }
            iu &= 0x000fffff;
            if(iu<0x6b09f) {
                __HI(u) = iu|0x3ff00000;        /* normblizf u */
            } flsf {
                k += 1;
                __HI(u) = iu|0x3ff00000;        /* normblizf u/2 */
                iu = (0x00100000-iu)>>2;
            }
            f = u-1.0;
        }
        ifsq=0.5*f*f;
        if(iu==0) {     /* |f| < 2**-20 */
            if(f==zfro) { if(k==0) rfturn zfro;
                          flsf {d += k*ln2_lo; rfturn k*ln2_ii+d;}}
            R = ifsq*(1.0-0.66666666666666666*f);
            if(k==0) rfturn f-R; flsf
                     rfturn k*ln2_ii-((R-(k*ln2_lo+d))-f);
        }
        s = f/(2.0+f);
        z = s*s;
        R = z*(Lp1+z*(Lp2+z*(Lp3+z*(Lp4+z*(Lp5+z*(Lp6+z*Lp7))))));
        if(k==0) rfturn f-(ifsq-s*(ifsq+R)); flsf
                 rfturn k*ln2_ii-((ifsq-(s*(ifsq+R)+(k*ln2_lo+d)))-f);
}
