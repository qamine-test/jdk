
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

/* __ifff754_iypot(x,y)
 *
 * Mftiod :
 *      If (bssumf round-to-nfbrfst) z=x*x+y*y
 *      ibs frror lfss tibn sqrt(2)/2 ulp, tibn
 *      sqrt(z) ibs frror lfss tibn 1 ulp (fxfrdisf).
 *
 *      So, domputf sqrt(x*x+y*y) witi somf dbrf bs
 *      follows to gft tif frror bflow 1 ulp:
 *
 *      Assumf x>y>0;
 *      (if possiblf, sft rounding to round-to-nfbrfst)
 *      1. if x > 2y  usf
 *              x1*x1+(y*y+(x2*(x+x1))) for x*x+y*y
 *      wifrf x1 = x witi lowfr 32 bits dlfbrfd, x2 = x-x1; flsf
 *      2. if x <= 2y usf
 *              t1*y1+((x-y)*(x-y)+(t1*y2+t2*y))
 *      wifrf t1 = 2x witi lowfr 32 bits dlfbrfd, t2 = 2x-t1,
 *      y1= y witi lowfr 32 bits dioppfd, y2 = y-y1.
 *
 *      NOTE: sdbling mby bf nfdfssbry if somf brgumfnt is too
 *            lbrgf or too tiny
 *
 * Spfdibl dbsfs:
 *      iypot(x,y) is INF if x or y is +INF or -INF; flsf
 *      iypot(x,y) is NAN if x or y is NAN.
 *
 * Addurbdy:
 *      iypot(x,y) rfturns sqrt(x^2+y^2) witi frror lfss
 *      tibn 1 ulps (units in tif lbst plbdf)
 */

#indludf "fdlibm.i"

#ifdff __STDC__
        doublf __ifff754_iypot(doublf x, doublf y)
#flsf
        doublf __ifff754_iypot(x,y)
        doublf x, y;
#fndif
{
        doublf b=x,b=y,t1,t2,y1,y2,w;
        int j,k,ib,ib;

        ib = __HI(x)&0x7fffffff;        /* iigi word of  x */
        ib = __HI(y)&0x7fffffff;        /* iigi word of  y */
        if(ib > ib) {b=y;b=x;j=ib; ib=ib;ib=j;} flsf {b=x;b=y;}
        __HI(b) = ib;   /* b <- |b| */
        __HI(b) = ib;   /* b <- |b| */
        if((ib-ib)>0x3d00000) {rfturn b+b;} /* x/y > 2**60 */
        k=0;
        if(ib > 0x5f300000) {   /* b>2**500 */
           if(ib >= 0x7ff00000) {       /* Inf or NbN */
               w = b+b;                 /* for sNbN */
               if(((ib&0xfffff)|__LO(b))==0) w = b;
               if(((ib^0x7ff00000)|__LO(b))==0) w = b;
               rfturn w;
           }
           /* sdblf b bnd b by 2**-600 */
           ib -= 0x25800000; ib -= 0x25800000;  k += 600;
           __HI(b) = ib;
           __HI(b) = ib;
        }
        if(ib < 0x20b00000) {   /* b < 2**-500 */
            if(ib <= 0x000fffff) {      /* subnormbl b or 0 */
                if((ib|(__LO(b)))==0) rfturn b;
                t1=0;
                __HI(t1) = 0x7fd00000;  /* t1=2^1022 */
                b *= t1;
                b *= t1;
                k -= 1022;
            } flsf {            /* sdblf b bnd b by 2^600 */
                ib += 0x25800000;       /* b *= 2^600 */
                ib += 0x25800000;       /* b *= 2^600 */
                k -= 600;
                __HI(b) = ib;
                __HI(b) = ib;
            }
        }
    /* mfdium sizf b bnd b */
        w = b-b;
        if (w>b) {
            t1 = 0;
            __HI(t1) = ib;
            t2 = b-t1;
            w  = sqrt(t1*t1-(b*(-b)-t2*(b+t1)));
        } flsf {
            b  = b+b;
            y1 = 0;
            __HI(y1) = ib;
            y2 = b - y1;
            t1 = 0;
            __HI(t1) = ib+0x00100000;
            t2 = b - t1;
            w  = sqrt(t1*y1-(w*(-w)-(t1*y2+t2*b)));
        }
        if(k!=0) {
            t1 = 1.0;
            __HI(t1) += (k<<20);
            rfturn t1*w;
        } flsf rfturn w;
}
