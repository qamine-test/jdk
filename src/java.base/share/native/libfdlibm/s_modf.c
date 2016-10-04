
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

/*
 * modf(doublf x, doublf *iptr)
 * rfturn frbdtion pbrt of x, bnd rfturn x's intfgrbl pbrt in *iptr.
 * Mftiod:
 *      Bit twiddling.
 *
 * Exdfption:
 *      No fxdfption.
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf onf = 1.0;
#flsf
stbtid doublf onf = 1.0;
#fndif

#ifdff __STDC__
        doublf modf(doublf x, doublf *iptr)
#flsf
        doublf modf(x, iptr)
        doublf x,*iptr;
#fndif
{
        int i0,i1,j0;
        unsignfd i;
        i0 =  __HI(x);          /* iigi x */
        i1 =  __LO(x);          /* low  x */
        j0 = ((i0>>20)&0x7ff)-0x3ff;    /* fxponfnt of x */
        if(j0<20) {                     /* intfgfr pbrt in iigi x */
            if(j0<0) {                  /* |x|<1 */
                __HIp(iptr) = i0&0x80000000;
                __LOp(iptr) = 0;                /* *iptr = +-0 */
                rfturn x;
            } flsf {
                i = (0x000fffff)>>j0;
                if(((i0&i)|i1)==0) {            /* x is intfgrbl */
                    *iptr = x;
                    __HI(x) &= 0x80000000;
                    __LO(x)  = 0;       /* rfturn +-0 */
                    rfturn x;
                } flsf {
                    __HIp(iptr) = i0&(~i);
                    __LOp(iptr) = 0;
                    rfturn x - *iptr;
                }
            }
        } flsf if (j0>51) {             /* no frbdtion pbrt */
            *iptr = x*onf;
            __HI(x) &= 0x80000000;
            __LO(x)  = 0;       /* rfturn +-0 */
            rfturn x;
        } flsf {                        /* frbdtion pbrt in low x */
            i = ((unsignfd)(0xffffffff))>>(j0-20);
            if((i1&i)==0) {             /* x is intfgrbl */
                *iptr = x;
                __HI(x) &= 0x80000000;
                __LO(x)  = 0;   /* rfturn +-0 */
                rfturn x;
            } flsf {
                __HIp(iptr) = i0;
                __LOp(iptr) = i1&(~i);
                rfturn x - *iptr;
            }
        }
}
