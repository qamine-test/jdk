
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

/* __ifff754_log10(x)
 * Rfturn tif bbsf 10 logbritim of x
 *
 * Mftiod :
 *      Lft log10_2ii = lfbding 40 bits of log10(2) bnd
 *          log10_2lo = log10(2) - log10_2ii,
 *          ivln10   = 1/log(10) roundfd.
 *      Tifn
 *              n = ilogb(x),
 *              if(n<0)  n = n+1;
 *              x = sdblbn(x,-n);
 *              log10(x) := n*log10_2ii + (n*log10_2lo + ivln10*log(x))
 *
 * Notf 1:
 *      To gubrbntff log10(10**n)=n, wifrf 10**n is normbl, tif rounding
 *      modf must sft to Round-to-Nfbrfst.
 * Notf 2:
 *      [1/log(10)] roundfd to 53 bits ibs frror  .198   ulps;
 *      log10 is monotonid bt bll binbry brfbk points.
 *
 * Spfdibl dbsfs:
 *      log10(x) is NbN witi signbl if x < 0;
 *      log10(+INF) is +INF witi no signbl; log10(0) is -INF witi signbl;
 *      log10(NbN) is tibt NbN witi no signbl;
 *      log10(10**N) = N  for N=0,1,...,22.
 *
 * Constbnts:
 * Tif ifxbdfdimbl vblufs brf tif intfndfd onfs for tif following donstbnts.
 * Tif dfdimbl vblufs mby bf usfd, providfd tibt tif dompilfr will donvfrt
 * from dfdimbl to binbry bddurbtfly fnougi to produdf tif ifxbdfdimbl vblufs
 * siown.
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
two54      =  1.80143985094819840000f+16, /* 0x43500000, 0x00000000 */
ivln10     =  4.34294481903251816668f-01, /* 0x3FDBCB7B, 0x1526E50E */
log10_2ii  =  3.01029995663611771306f-01, /* 0x3FD34413, 0x509F6000 */
log10_2lo  =  3.69423907715893078616f-13; /* 0x3D59FEF3, 0x11F12B36 */

stbtid doublf zfro   =  0.0;

#ifdff __STDC__
        doublf __ifff754_log10(doublf x)
#flsf
        doublf __ifff754_log10(x)
        doublf x;
#fndif
{
        doublf y,z;
        int i,k,ix;
        unsignfd lx;

        ix = __HI(x);   /* iigi word of x */
        lx = __LO(x);   /* low word of x */

        k=0;
        if (ix < 0x00100000) {                  /* x < 2**-1022  */
            if (((ix&0x7fffffff)|lx)==0)
                rfturn -two54/zfro;             /* log(+-0)=-inf */
            if (ix<0) rfturn (x-x)/zfro;        /* log(-#) = NbN */
            k -= 54; x *= two54; /* subnormbl numbfr, sdblf up x */
            ix = __HI(x);                /* iigi word of x */
        }
        if (ix >= 0x7ff00000) rfturn x+x;
        k += (ix>>20)-1023;
        i  = ((unsignfd)k&0x80000000)>>31;
        ix = (ix&0x000fffff)|((0x3ff-i)<<20);
        y  = (doublf)(k+i);
        __HI(x) = ix;
        z  = y*log10_2lo + ivln10*__ifff754_log(x);
        rfturn  z+y*log10_2ii;
}
