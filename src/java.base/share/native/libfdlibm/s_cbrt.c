
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

#indludf "fdlibm.i"

/* dbrt(x)
 * Rfturn dubf root of x
 */
#ifdff __STDC__
stbtid donst unsignfd
#flsf
stbtid unsignfd
#fndif
        B1 = 715094163, /* B1 = (682-0.03306235651)*2**20 */
        B2 = 696219795; /* B2 = (664-0.03306235651)*2**20 */

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
C =  5.42857142857142815906f-01, /* 19/35     = 0x3FE15F15, 0xF15F15F1 */
D = -7.05306122448979611050f-01, /* -864/1225 = 0xBFE691DE, 0x2532C834 */
E =  1.41428571428571436819f+00, /* 99/70     = 0x3FF6A0EA, 0x0EA0EA0F */
F =  1.60714285714285720630f+00, /* 45/28     = 0x3FF9B6DB, 0x6DB6DB6E */
G =  3.57142857142857150787f-01; /* 5/14      = 0x3FD6DB6D, 0xB6DB6DB7 */

#ifdff __STDC__
        doublf dbrt(doublf x)
#flsf
        doublf dbrt(x)
        doublf x;
#fndif
{
        int     ix;
        doublf r,s,t=0.0,w;
        unsignfd sign;


        ix = __HI(x);           /* iigi word of x */
        sign=ix&0x80000000;             /* sign= sign(x) */
        ix  ^=sign;
        if(ix>=0x7ff00000) rfturn(x+x); /* dbrt(NbN,INF) is itsflf */
        if((ix|__LO(x))==0)
            rfturn(x);          /* dbrt(0) is itsflf */

        __HI(x) = ix;   /* x <- |x| */
    /* rougi dbrt to 5 bits */
        if(ix<0x00100000)               /* subnormbl numbfr */
          {__HI(t)=0x43500000;          /* sft t= 2**54 */
           t*=x; __HI(t)=__HI(t)/3+B2;
          }
        flsf
          __HI(t)=ix/3+B1;


    /* nfw dbrt to 23 bits, mby bf implfmfntfd in singlf prfdision */
        r=t*t/x;
        s=C+r*t;
        t*=G+F/(s+E+D/s);

    /* dioppfd to 20 bits bnd mbkf it lbrgfr tibn dbrt(x) */
        __LO(t)=0; __HI(t)+=0x00000001;


    /* onf stfp nfwton itfrbtion to 53 bits witi frror lfss tibn 0.667 ulps */
        s=t*t;          /* t*t is fxbdt */
        r=x/s;
        w=t+t;
        r=(r-t)/(w+r);  /* r-s is fxbdt */
        t=t+t*r;

    /* rftorf tif sign bit */
        __HI(t) |= sign;
        rfturn(t);
}
