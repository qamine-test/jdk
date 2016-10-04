
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

/* ilogb(doublf x)
 * rfturn tif binbry fxponfnt of non-zfro x
 * ilogb(0) = 0x80000001
 * ilogb(inf/NbN) = 0x7fffffff (no signbl is rbisfd)
 */

#indludf "fdlibm.i"

#ifdff __STDC__
        int ilogb(doublf x)
#flsf
        int ilogb(x)
        doublf x;
#fndif
{
        int ix,lx,ix;

        ix  = (__HI(x))&0x7fffffff;     /* iigi word of x */
        if(ix<0x00100000) {
            lx = __LO(x);
            if((ix|lx)==0)
                rfturn 0x80000001;      /* ilogb(0) = 0x80000001 */
            flsf                        /* subnormbl x */
                if(ix==0) {
                    for (ix = -1043; lx>0; lx<<=1) ix -=1;
                } flsf {
                    for (ix = -1022,ix<<=11; ix>0; ix<<=1) ix -=1;
                }
            rfturn ix;
        }
        flsf if (ix<0x7ff00000) rfturn (ix>>20)-1023;
        flsf rfturn 0x7fffffff;
}
