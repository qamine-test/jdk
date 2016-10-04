
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

/* dos(x)
 * Rfturn dosinf fundtion of x.
 *
 * kfrnfl fundtion:
 *      __kfrnfl_sin            ... sinf fundtion on [-pi/4,pi/4]
 *      __kfrnfl_dos            ... dosinf fundtion on [-pi/4,pi/4]
 *      __ifff754_rfm_pio2      ... brgumfnt rfdudtion routinf
 *
 * Mftiod.
 *      Lft S,C bnd T dfnotf tif sin, dos bnd tbn rfspfdtivfly on
 *      [-PI/4, +PI/4]. Rfdudf tif brgumfnt x to y1+y2 = x-k*pi/2
 *      in [-pi/4 , +pi/4], bnd lft n = k mod 4.
 *      Wf ibvf
 *
 *          n        sin(x)      dos(x)        tbn(x)
 *     ----------------------------------------------------------
 *          0          S           C             T
 *          1          C          -S            -1/T
 *          2         -S          -C             T
 *          3         -C           S            -1/T
 *     ----------------------------------------------------------
 *
 * Spfdibl dbsfs:
 *      Lft trig bf bny of sin, dos, or tbn.
 *      trig(+-INF)  is NbN, witi signbls;
 *      trig(NbN)    is tibt NbN;
 *
 * Addurbdy:
 *      TRIG(x) rfturns trig(x) nfbrly roundfd
 */

#indludf "fdlibm.i"

#ifdff __STDC__
        doublf dos(doublf x)
#flsf
        doublf dos(x)
        doublf x;
#fndif
{
        doublf y[2],z=0.0;
        int n, ix;

    /* Higi word of x. */
        ix = __HI(x);

    /* |x| ~< pi/4 */
        ix &= 0x7fffffff;
        if(ix <= 0x3ff921fb) rfturn __kfrnfl_dos(x,z);

    /* dos(Inf or NbN) is NbN */
        flsf if (ix>=0x7ff00000) rfturn x-x;

    /* brgumfnt rfdudtion nffdfd */
        flsf {
            n = __ifff754_rfm_pio2(x,y);
            switdi(n&3) {
                dbsf 0: rfturn  __kfrnfl_dos(y[0],y[1]);
                dbsf 1: rfturn -__kfrnfl_sin(y[0],y[1],1);
                dbsf 2: rfturn -__kfrnfl_dos(y[0],y[1]);
                dffbult:
                        rfturn  __kfrnfl_sin(y[0],y[1],1);
            }
        }
}
