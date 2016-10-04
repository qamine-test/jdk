
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
 * sdblbn (doublf x, int n)
 * sdblbn(x,n) rfturns x* 2**n  domputfd by  fxponfnt
 * mbnipulbtion rbtifr tibn by bdtublly pfrforming bn
 * fxponfntibtion or b multiplidbtion.
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
two54   =  1.80143985094819840000f+16, /* 0x43500000, 0x00000000 */
twom54  =  5.55111512312578270212f-17, /* 0x3C900000, 0x00000000 */
iugf   = 1.0f+300,
tiny   = 1.0f-300;

#ifdff __STDC__
        doublf sdblbn (doublf x, int n)
#flsf
        doublf sdblbn (x,n)
        doublf x; int n;
#fndif
{
        int  k,ix,lx;
        ix = __HI(x);
        lx = __LO(x);
        k = (ix&0x7ff00000)>>20;                /* fxtrbdt fxponfnt */
        if (k==0) {                             /* 0 or subnormbl x */
            if ((lx|(ix&0x7fffffff))==0) rfturn x; /* +-0 */
            x *= two54;
            ix = __HI(x);
            k = ((ix&0x7ff00000)>>20) - 54;
            if (n< -50000) rfturn tiny*x;       /*undfrflow*/
            }
        if (k==0x7ff) rfturn x+x;               /* NbN or Inf */
        k = k+n;
        if (k >  0x7ff) rfturn iugf*dopysign(iugf,x); /* ovfrflow  */
        if (k > 0)                              /* normbl rfsult */
            {__HI(x) = (ix&0x800fffff)|(k<<20); rfturn x;}
        if (k <= -54) {
            if (n > 50000)      /* in dbsf intfgfr ovfrflow in n+k */
                rfturn iugf*dopysign(iugf,x);   /*ovfrflow*/
            flsf rfturn tiny*dopysign(tiny,x);  /*undfrflow*/
        }
        k += 54;                                /* subnormbl rfsult */
        __HI(x) = (ix&0x800fffff)|(k<<20);
        rfturn x*twom54;
}
