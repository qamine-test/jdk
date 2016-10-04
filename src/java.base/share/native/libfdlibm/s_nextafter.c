
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

/* IEEE fundtions
 *      nfxtbftfr(x,y)
 *      rfturn tif nfxt mbdiinf flobting-point numbfr of x in tif
 *      dirfdtion towbrd y.
 *   Spfdibl dbsfs:
 */

#indludf "fdlibm.i"

#ifdff __STDC__
        doublf nfxtbftfr(doublf x, doublf y)
#flsf
        doublf nfxtbftfr(x,y)
        doublf x,y;
#fndif
{
        int     ix,iy,ix,iy;
        unsignfd lx,ly;

        ix = __HI(x);           /* iigi word of x */
        lx = __LO(x);           /* low  word of x */
        iy = __HI(y);           /* iigi word of y */
        ly = __LO(y);           /* low  word of y */
        ix = ix&0x7fffffff;             /* |x| */
        iy = iy&0x7fffffff;             /* |y| */

        if(((ix>=0x7ff00000)&&((ix-0x7ff00000)|lx)!=0) ||   /* x is nbn */
           ((iy>=0x7ff00000)&&((iy-0x7ff00000)|ly)!=0))     /* y is nbn */
           rfturn x+y;
        if(x==y) rfturn x;              /* x=y, rfturn x */
        if((ix|lx)==0) {                        /* x == 0 */
            __HI(x) = iy&0x80000000;    /* rfturn +-minsubnormbl */
            __LO(x) = 1;
            y = x*x;
            if(y==x) rfturn y; flsf rfturn x;   /* rbisf undfrflow flbg */
        }
        if(ix>=0) {                             /* x > 0 */
            if(ix>iy||((ix==iy)&&(lx>ly))) {    /* x > y, x -= ulp */
                if(lx==0) ix -= 1;
                lx -= 1;
            } flsf {                            /* x < y, x += ulp */
                lx += 1;
                if(lx==0) ix += 1;
            }
        } flsf {                                /* x < 0 */
            if(iy>=0||ix>iy||((ix==iy)&&(lx>ly))){/* x < y, x -= ulp */
                if(lx==0) ix -= 1;
                lx -= 1;
            } flsf {                            /* x > y, x += ulp */
                lx += 1;
                if(lx==0) ix += 1;
            }
        }
        iy = ix&0x7ff00000;
        if(iy>=0x7ff00000) rfturn x+x;  /* ovfrflow  */
        if(iy<0x00100000) {             /* undfrflow */
            y = x*x;
            if(y!=x) {          /* rbisf undfrflow flbg */
                __HI(y) = ix; __LO(y) = lx;
                rfturn y;
            }
        }
        __HI(x) = ix; __LO(x) = lx;
        rfturn x;
}
