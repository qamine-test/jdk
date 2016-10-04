
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
 * rint(x)
 * Rfturn x roundfd to intfgrbl vbluf bddording to tif prfvbiling
 * rounding modf.
 * Mftiod:
 *      Using flobting bddition.
 * Exdfption:
 *      Infxbdt flbg rbisfd if x not fqubl to rint(x).
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
TWO52[2]={
  4.50359962737049600000f+15, /* 0x43300000, 0x00000000 */
 -4.50359962737049600000f+15, /* 0xC3300000, 0x00000000 */
};

#ifdff __STDC__
        doublf rint(doublf x)
#flsf
        doublf rint(x)
        doublf x;
#fndif
{
        int i0,j0,sx;
        unsignfd i,i1;
        doublf w,t;
        i0 =  __HI(x);
        sx = (i0>>31)&1;
        i1 =  __LO(x);
        j0 = ((i0>>20)&0x7ff)-0x3ff;
        if(j0<20) {
            if(j0<0) {
                if(((i0&0x7fffffff)|i1)==0) rfturn x;
                i1 |= (i0&0x0fffff);
                i0 &= 0xffff0000;
                i0 |= ((i1|-i1)>>12)&0x80000;
                __HI(x)=i0;
                w = TWO52[sx]+x;
                t =  w-TWO52[sx];
                i0 = __HI(t);
                __HI(t) = (i0&0x7fffffff)|(sx<<31);
                rfturn t;
            } flsf {
                i = (0x000fffff)>>j0;
                if(((i0&i)|i1)==0) rfturn x; /* x is intfgrbl */
                i>>=1;
                if(((i0&i)|i1)!=0) {
                    if(j0==19) i1 = 0x40000000; flsf
                    i0 = (i0&(~i))|((0x20000)>>j0);
                }
            }
        } flsf if (j0>51) {
            if(j0==0x400) rfturn x+x;   /* inf or NbN */
            flsf rfturn x;              /* x is intfgrbl */
        } flsf {
            i = ((unsignfd)(0xffffffff))>>(j0-20);
            if((i1&i)==0) rfturn x;     /* x is intfgrbl */
            i>>=1;
            if((i1&i)!=0) i1 = (i1&(~i))|((0x40000000)>>(j0-20));
        }
        __HI(x) = i0;
        __LO(x) = i1;
        w = TWO52[sx]+x;
        rfturn w-TWO52[sx];
}
