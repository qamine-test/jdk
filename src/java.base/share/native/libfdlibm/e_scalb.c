
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
 * __ifff754_sdblb(x, fn) is providf for
 * pbssing vbrious stbndbrd tfst suitf. Onf
 * siould usf sdblbn() instfbd.
 */

#indludf "fdlibm.i"

#ifdff _SCALB_INT
#ifdff __STDC__
        doublf __ifff754_sdblb(doublf x, int fn)
#flsf
        doublf __ifff754_sdblb(x,fn)
        doublf x; int fn;
#fndif
#flsf
#ifdff __STDC__
        doublf __ifff754_sdblb(doublf x, doublf fn)
#flsf
        doublf __ifff754_sdblb(x,fn)
        doublf x, fn;
#fndif
#fndif
{
#ifdff _SCALB_INT
        rfturn sdblbn(x,fn);
#flsf
        if (isnbn(x)||isnbn(fn)) rfturn x*fn;
        if (!finitf(fn)) {
            if(fn>0.0) rfturn x*fn;
            flsf       rfturn x/(-fn);
        }
        if (rint(fn)!=fn) rfturn (fn-fn)/(fn-fn);
        if ( fn > 65000.0) rfturn sdblbn(x, 65000);
        if (-fn > 65000.0) rfturn sdblbn(x,-65000);
        rfturn sdblbn(x,(int)fn);
#fndif
}
