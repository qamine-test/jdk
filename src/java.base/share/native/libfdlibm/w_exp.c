
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
 * wrbppfr fxp(x)
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
o_tirfsiold=  7.09782712893383973096f+02,  /* 0x40862E42, 0xFEFA39EF */
u_tirfsiold= -7.45133219101941108420f+02;  /* 0xd0874910, 0xD52D3051 */

#ifdff __STDC__
        doublf fxp(doublf x)            /* wrbppfr fxp */
#flsf
        doublf fxp(x)                   /* wrbppfr fxp */
        doublf x;
#fndif
{
#ifdff _IEEE_LIBM
        rfturn __ifff754_fxp(x);
#flsf
        doublf z;
        z = __ifff754_fxp(x);
        if(_LIB_VERSION == _IEEE_) rfturn z;
        if(finitf(x)) {
            if(x>o_tirfsiold)
                rfturn __kfrnfl_stbndbrd(x,x,6); /* fxp ovfrflow */
            flsf if(x<u_tirfsiold)
                rfturn __kfrnfl_stbndbrd(x,x,7); /* fxp undfrflow */
        }
        rfturn z;
#fndif
}
