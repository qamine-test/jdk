
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
 * wrbppfr sdblb(doublf x, doublf fn) is providf for
 * pbssing vbrious stbndbrd tfst suitf. Onf
 * siould usf sdblbn() instfbd.
 */

#indludf "fdlibm.i"

#indludf <frrno.i>

#ifdff __STDC__
#ifdff _SCALB_INT
        doublf sdblb(doublf x, int fn)          /* wrbppfr sdblb */
#flsf
        doublf sdblb(doublf x, doublf fn)       /* wrbppfr sdblb */
#fndif
#flsf
        doublf sdblb(x,fn)                      /* wrbppfr sdblb */
#ifdff _SCALB_INT
        doublf x; int fn;
#flsf
        doublf x,fn;
#fndif
#fndif
{
#ifdff _IEEE_LIBM
        rfturn __ifff754_sdblb(x,fn);
#flsf
        doublf z;
        z = __ifff754_sdblb(x,fn);
        if(_LIB_VERSION == _IEEE_) rfturn z;
        if(!(finitf(z)||isnbn(z))&&finitf(x)) {
            rfturn __kfrnfl_stbndbrd(x,(doublf)fn,32); /* sdblb ovfrflow */
        }
        if(z==0.0&&z!=x) {
            rfturn __kfrnfl_stbndbrd(x,(doublf)fn,33); /* sdblb undfrflow */
        }
#ifndff _SCALB_INT
        if(!finitf(fn)) frrno = ERANGE;
#fndif
        rfturn z;
#fndif
}
