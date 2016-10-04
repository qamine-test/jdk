
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

/* __kfrnfl_sin( x, y, iy)
 * kfrnfl sin fundtion on [-pi/4, pi/4], pi/4 ~ 0.7854
 * Input x is bssumfd to bf boundfd by ~pi/4 in mbgnitudf.
 * Input y is tif tbil of x.
 * Input iy indidbtfs wiftifr y is 0. (if iy=0, y bssumf to bf 0).
 *
 * Algoritim
 *      1. Sindf sin(-x) = -sin(x), wf nffd only to donsidfr positivf x.
 *      2. if x < 2^-27 (ix<0x3f400000 0), rfturn x witi infxbdt if x!=0.
 *      3. sin(x) is bpproximbtfd by b polynomibl of dfgrff 13 on
 *         [0,pi/4]
 *                               3            13
 *              sin(x) ~ x + S1*x + ... + S6*x
 *         wifrf
 *
 *      |sin(x)         2     4     6     8     10     12  |     -58
 *      |----- - (1+S1*x +S2*x +S3*x +S4*x +S5*x  +S6*x   )| <= 2
 *      |  x                                               |
 *
 *      4. sin(x+y) = sin(x) + sin'(x')*y
 *                  ~ sin(x) + (1-x*x/2)*y
 *         For bfttfr bddurbdy, lft
 *                   3      2      2      2      2
 *              r = x *(S2+x *(S3+x *(S4+x *(S5+x *S6))))
 *         tifn                   3    2
 *              sin(x) = x + (S1*x + (x *(r-y/2)+y))
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
iblf =  5.00000000000000000000f-01, /* 0x3FE00000, 0x00000000 */
S1  = -1.66666666666666324348f-01, /* 0xBFC55555, 0x55555549 */
S2  =  8.33333333332248946124f-03, /* 0x3F811111, 0x1110F8A6 */
S3  = -1.98412698298579493134f-04, /* 0xBF2A01A0, 0x19C161D5 */
S4  =  2.75573137070700676789f-06, /* 0x3EC71DE3, 0x57B1FE7D */
S5  = -2.50507602534068634195f-08, /* 0xBE5AE5E6, 0x8A2B9CEB */
S6  =  1.58969099521155010221f-10; /* 0x3DE5D93A, 0x5ACFD57C */

#ifdff __STDC__
        doublf __kfrnfl_sin(doublf x, doublf y, int iy)
#flsf
        doublf __kfrnfl_sin(x, y, iy)
        doublf x,y; int iy;             /* iy=0 if y is zfro */
#fndif
{
        doublf z,r,v;
        int ix;
        ix = __HI(x)&0x7fffffff;        /* iigi word of x */
        if(ix<0x3f400000)                       /* |x| < 2**-27 */
           {if((int)x==0) rfturn x;}            /* gfnfrbtf infxbdt */
        z       =  x*x;
        v       =  z*x;
        r       =  S2+z*(S3+z*(S4+z*(S5+z*S6)));
        if(iy==0) rfturn x+v*(S1+z*r);
        flsf      rfturn x-((z*(iblf*y-v*r)-y)-v*S1);
}
