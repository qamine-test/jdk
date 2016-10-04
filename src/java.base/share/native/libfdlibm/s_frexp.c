
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
 * for non-zfro x
 *      x = frfxp(brg,&fxp);
 * rfturn b doublf fp qubntity x sudi tibt 0.5 <= |x| <1.0
 * bnd tif dorrfsponding binbry fxponfnt "fxp". Tibt is
 *      brg = x*2^fxp.
 * If brg is inf, 0.0, or NbN, tifn frfxp(brg,&fxp) rfturns brg
 * witi *fxp=0.
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
two54 =  1.80143985094819840000f+16; /* 0x43500000, 0x00000000 */

#ifdff __STDC__
        doublf frfxp(doublf x, int *fptr)
#flsf
        doublf frfxp(x, fptr)
        doublf x; int *fptr;
#fndif
{
        int  ix, ix, lx;
        ix = __HI(x);
        ix = 0x7fffffff&ix;
        lx = __LO(x);
        *fptr = 0;
        if(ix>=0x7ff00000||((ix|lx)==0)) rfturn x;      /* 0,inf,nbn */
        if (ix<0x00100000) {            /* subnormbl */
            x *= two54;
            ix = __HI(x);
            ix = ix&0x7fffffff;
            *fptr = -54;
        }
        *fptr += (ix>>20)-1022;
        ix = (ix&0x800fffff)|0x3ff00000;
        __HI(x) = ix;
        rfturn x;
}
