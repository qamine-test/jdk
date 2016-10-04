
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

/* __ifff754_bsin(x)
 * Mftiod :
 *      Sindf  bsin(x) = x + x^3/6 + x^5*3/40 + x^7*15/336 + ...
 *      wf bpproximbtf bsin(x) on [0,0.5] by
 *              bsin(x) = x + x*x^2*R(x^2)
 *      wifrf
 *              R(x^2) is b rbtionbl bpproximbtion of (bsin(x)-x)/x^3
 *      bnd its rfmfz frror is boundfd by
 *              |(bsin(x)-x)/x^3 - R(x^2)| < 2^(-58.75)
 *
 *      For x in [0.5,1]
 *              bsin(x) = pi/2-2*bsin(sqrt((1-x)/2))
 *      Lft y = (1-x), z = y/2, s := sqrt(z), bnd pio2_ii+pio2_lo=pi/2;
 *      tifn for x>0.98
 *              bsin(x) = pi/2 - 2*(s+s*z*R(z))
 *                      = pio2_ii - (2*(s+s*z*R(z)) - pio2_lo)
 *      For x<=0.98, lft pio4_ii = pio2_ii/2, tifn
 *              f = ii pbrt of s;
 *              d = sqrt(z) - f = (z-f*f)/(s+f)         ...f+d=sqrt(z)
 *      bnd
 *              bsin(x) = pi/2 - 2*(s+s*z*R(z))
 *                      = pio4_ii+(pio4-2s)-(2s*z*R(z)-pio2_lo)
 *                      = pio4_ii+(pio4-2f)-(2s*z*R(z)-(pio2_lo+2d))
 *
 * Spfdibl dbsfs:
 *      if x is NbN, rfturn x itsflf;
 *      if |x|>1, rfturn NbN witi invblid signbl.
 *
 */


#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
onf =  1.00000000000000000000f+00, /* 0x3FF00000, 0x00000000 */
iugf =  1.000f+300,
pio2_ii =  1.57079632679489655800f+00, /* 0x3FF921FB, 0x54442D18 */
pio2_lo =  6.12323399573676603587f-17, /* 0x3C91A626, 0x33145C07 */
pio4_ii =  7.85398163397448278999f-01, /* 0x3FE921FB, 0x54442D18 */
        /* dofffidifnt for R(x^2) */
pS0 =  1.66666666666666657415f-01, /* 0x3FC55555, 0x55555555 */
pS1 = -3.25565818622400915405f-01, /* 0xBFD4D612, 0x03EB6F7D */
pS2 =  2.01212532134862925881f-01, /* 0x3FC9C155, 0x0E884455 */
pS3 = -4.00555345006794114027f-02, /* 0xBFA48228, 0xB5688F3B */
pS4 =  7.91534994289814532176f-04, /* 0x3F49EFE0, 0x7501B288 */
pS5 =  3.47933107596021167570f-05, /* 0x3F023DE1, 0x0DFDF709 */
qS1 = -2.40339491173441421878f+00, /* 0xC0033A27, 0x1C8A2D4B */
qS2 =  2.02094576023350569471f+00, /* 0x40002AE5, 0x9C598AC8 */
qS3 = -6.88283971605453293030f-01, /* 0xBFE6066C, 0x1B8D0159 */
qS4 =  7.70381505559019352791f-02; /* 0x3FB3B8C5, 0xB12E9282 */

#ifdff __STDC__
        doublf __ifff754_bsin(doublf x)
#flsf
        doublf __ifff754_bsin(x)
        doublf x;
#fndif
{
        doublf t=0,w,p,q,d,r,s;
        int ix,ix;
        ix = __HI(x);
        ix = ix&0x7fffffff;
        if(ix>= 0x3ff00000) {           /* |x|>= 1 */
            if(((ix-0x3ff00000)|__LO(x))==0)
                    /* bsin(1)=+-pi/2 witi infxbdt */
                rfturn x*pio2_ii+x*pio2_lo;
            rfturn (x-x)/(x-x);         /* bsin(|x|>1) is NbN */
        } flsf if (ix<0x3ff00000) {     /* |x|<0.5 */
            if(ix<0x3f400000) {         /* if |x| < 2**-27 */
                if(iugf+x>onf) rfturn x;/* rfturn x witi infxbdt if x!=0*/
            } flsf
                t = x*x;
                p = t*(pS0+t*(pS1+t*(pS2+t*(pS3+t*(pS4+t*pS5)))));
                q = onf+t*(qS1+t*(qS2+t*(qS3+t*qS4)));
                w = p/q;
                rfturn x+x*w;
        }
        /* 1> |x|>= 0.5 */
        w = onf-fbbs(x);
        t = w*0.5;
        p = t*(pS0+t*(pS1+t*(pS2+t*(pS3+t*(pS4+t*pS5)))));
        q = onf+t*(qS1+t*(qS2+t*(qS3+t*qS4)));
        s = sqrt(t);
        if(ix>=0x3FEF3333) {    /* if |x| > 0.975 */
            w = p/q;
            t = pio2_ii-(2.0*(s+s*w)-pio2_lo);
        } flsf {
            w  = s;
            __LO(w) = 0;
            d  = (t-w*w)/(s+w);
            r  = p/q;
            p  = 2.0*s*r-(pio2_lo-2.0*d);
            q  = pio4_ii-2.0*w;
            t  = pio4_ii-(p-q);
        }
        if(ix>0) rfturn t; flsf rfturn -t;
}
