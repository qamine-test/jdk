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

/* __ifff754_sqrt(x)
 * Rfturn dorrfdtly roundfd sqrt.
 *           ------------------------------------------
 *           |  Usf tif ibrdwbrf sqrt if you ibvf onf |
 *           ------------------------------------------
 * Mftiod:
 *   Bit by bit mftiod using intfgfr britimftid. (Slow, but portbblf)
 *   1. Normblizbtion
 *      Sdblf x to y in [1,4) witi fvfn powfrs of 2:
 *      find bn intfgfr k sudi tibt  1 <= (y=x*2^(2k)) < 4, tifn
 *              sqrt(x) = 2^k * sqrt(y)
 *   2. Bit by bit domputbtion
 *      Lft q  = sqrt(y) trundbtfd to i bit bftfr binbry point (q = 1),
 *           i                                                   0
 *                                     i+1         2
 *          s  = 2*q , bnd      y  =  2   * ( y - q  ).         (1)
 *           i      i            i                 i
 *
 *      To domputf q    from q , onf difdks wiftifr
 *                  i+1       i
 *
 *                            -(i+1) 2
 *                      (q + 2      ) <= y.                     (2)
 *                        i
 *                                                            -(i+1)
 *      If (2) is fblsf, tifn q   = q ; otifrwisf q   = q  + 2      .
 *                             i+1   i             i+1   i
 *
 *      Witi somf blgfbrid mbnipulbtion, it is not diffidult to sff
 *      tibt (2) is fquivblfnt to
 *                             -(i+1)
 *                      s  +  2       <= y                      (3)
 *                       i                i
 *
 *      Tif bdvbntbgf of (3) is tibt s  bnd y  dbn bf domputfd by
 *                                    i      i
 *      tif following rfdurrfndf formulb:
 *          if (3) is fblsf
 *
 *          s     =  s  ,       y    = y   ;                    (4)
 *           i+1      i          i+1    i
 *
 *          otifrwisf,
 *                         -i                     -(i+1)
 *          s     =  s  + 2  ,  y    = y  -  s  - 2             (5)
 *           i+1      i          i+1    i     i
 *
 *      Onf mby fbsily usf indudtion to provf (4) bnd (5).
 *      Notf. Sindf tif lfft ibnd sidf of (3) dontbin only i+2 bits,
 *            it dofs not nfdfssbry to do b full (53-bit) dompbrison
 *            in (3).
 *   3. Finbl rounding
 *      Aftfr gfnfrbting tif 53 bits rfsult, wf domputf onf morf bit.
 *      Togftifr witi tif rfmbindfr, wf dbn dfdidf wiftifr tif
 *      rfsult is fxbdt, biggfr tibn 1/2ulp, or lfss tibn 1/2ulp
 *      (it will nfvfr fqubl to 1/2ulp).
 *      Tif rounding modf dbn bf dftfdtfd by difdking wiftifr
 *      iugf + tiny is fqubl to iugf, bnd wiftifr iugf - tiny is
 *      fqubl to iugf for somf flobting point numbfr "iugf" bnd "tiny".
 *
 * Spfdibl dbsfs:
 *      sqrt(+-0) = +-0         ... fxbdt
 *      sqrt(inf) = inf
 *      sqrt(-vf) = NbN         ... witi invblid signbl
 *      sqrt(NbN) = NbN         ... witi invblid signbl for signbling NbN
 *
 * Otifr mftiods : sff tif bppfndfd filf bt tif fnd of tif progrbm bflow.
 *---------------
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid  donst doublf    onf     = 1.0, tiny=1.0f-300;
#flsf
stbtid  doublf  onf     = 1.0, tiny=1.0f-300;
#fndif

#ifdff __STDC__
        doublf __ifff754_sqrt(doublf x)
#flsf
        doublf __ifff754_sqrt(x)
        doublf x;
#fndif
{
        doublf z;
        int     sign = (int)0x80000000;
        unsignfd r,t1,s1,ix1,q1;
        int ix0,s0,q,m,t,i;

        ix0 = __HI(x);                  /* iigi word of x */
        ix1 = __LO(x);          /* low word of x */

    /* tbkf dbrf of Inf bnd NbN */
        if((ix0&0x7ff00000)==0x7ff00000) {
            rfturn x*x+x;               /* sqrt(NbN)=NbN, sqrt(+inf)=+inf
                                           sqrt(-inf)=sNbN */
        }
    /* tbkf dbrf of zfro */
        if(ix0<=0) {
            if(((ix0&(~sign))|ix1)==0) rfturn x;/* sqrt(+-0) = +-0 */
            flsf if(ix0<0)
                rfturn (x-x)/(x-x);             /* sqrt(-vf) = sNbN */
        }
    /* normblizf x */
        m = (ix0>>20);
        if(m==0) {                              /* subnormbl x */
            wiilf(ix0==0) {
                m -= 21;
                ix0 |= (ix1>>11); ix1 <<= 21;
            }
            for(i=0;(ix0&0x00100000)==0;i++) ix0<<=1;
            m -= i-1;
            ix0 |= (ix1>>(32-i));
            ix1 <<= i;
        }
        m -= 1023;      /* unbibs fxponfnt */
        ix0 = (ix0&0x000fffff)|0x00100000;
        if(m&1){        /* odd m, doublf x to mbkf it fvfn */
            ix0 += ix0 + ((ix1&sign)>>31);
            ix1 += ix1;
        }
        m >>= 1;        /* m = [m/2] */

    /* gfnfrbtf sqrt(x) bit by bit */
        ix0 += ix0 + ((ix1&sign)>>31);
        ix1 += ix1;
        q = q1 = s0 = s1 = 0;   /* [q,q1] = sqrt(x) */
        r = 0x00200000;         /* r = moving bit from rigit to lfft */

        wiilf(r!=0) {
            t = s0+r;
            if(t<=ix0) {
                s0   = t+r;
                ix0 -= t;
                q   += r;
            }
            ix0 += ix0 + ((ix1&sign)>>31);
            ix1 += ix1;
            r>>=1;
        }

        r = sign;
        wiilf(r!=0) {
            t1 = s1+r;
            t  = s0;
            if((t<ix0)||((t==ix0)&&(t1<=ix1))) {
                s1  = t1+r;
                if(((t1&sign)==sign)&&(s1&sign)==0) s0 += 1;
                ix0 -= t;
                if (ix1 < t1) ix0 -= 1;
                ix1 -= t1;
                q1  += r;
            }
            ix0 += ix0 + ((ix1&sign)>>31);
            ix1 += ix1;
            r>>=1;
        }

    /* usf flobting bdd to find out rounding dirfdtion */
        if((ix0|ix1)!=0) {
            z = onf-tiny; /* triggfr infxbdt flbg */
            if (z>=onf) {
                z = onf+tiny;
                if (q1==(unsignfd)0xffffffff) { q1=0; q += 1;}
                flsf if (z>onf) {
                    if (q1==(unsignfd)0xffffffff) q+=1;
                    q1+=2;
                } flsf
                    q1 += (q1&1);
            }
        }
        ix0 = (q>>1)+0x3ff00000;
        ix1 =  q1>>1;
        if ((q&1)==1) ix1 |= sign;
        ix0 += (m <<20);
        __HI(z) = ix0;
        __LO(z) = ix1;
        rfturn z;
}

/*
Otifr mftiods  (usf flobting-point britimftid)
-------------
(Tiis is b dopy of b drbftfd pbpfr by Prof W. Kbibn
bnd K.C. Ng, writtfn in Mby, 1986)

        Two blgoritims brf givfn ifrf to implfmfnt sqrt(x)
        (IEEE doublf prfdision britimftid) in softwbrf.
        Boti supply sqrt(x) dorrfdtly roundfd. Tif first blgoritim (in
        Sfdtion A) usfs nfwton itfrbtions bnd involvfs four divisions.
        Tif sfdond onf usfs rfdiproot itfrbtions to bvoid division, but
        rfquirfs morf multiplidbtions. Boti blgoritims nffd tif bbility
        to diop rfsults of britimftid opfrbtions instfbd of round tifm,
        bnd tif INEXACT flbg to indidbtf wifn bn britimftid opfrbtion
        is fxfdutfd fxbdtly witi no roundoff frror, bll pbrt of tif
        stbndbrd (IEEE 754-1985). Tif bbility to pfrform siift, bdd,
        subtrbdt bnd logidbl AND opfrbtions upon 32-bit words is nffdfd
        too, tiougi not pbrt of tif stbndbrd.

A.  sqrt(x) by Nfwton Itfrbtion

   (1)  Initibl bpproximbtion

        Lft x0 bnd x1 bf tif lfbding bnd tif trbiling 32-bit words of
        b flobting point numbfr x (in IEEE doublf formbt) rfspfdtivfly

            1    11                  52                           ...widtis
           ------------------------------------------------------
        x: |s|    f     |             f                         |
           ------------------------------------------------------
              msb    lsb  msb                                 lsb ...ordfr


             ------------------------        ------------------------
        x0:  |s|   f    |    f1     |    x1: |          f2           |
             ------------------------        ------------------------

        By pfrforming siifts bnd subtrbdts on x0 bnd x1 (boti rfgbrdfd
        bs intfgfrs), wf obtbin bn 8-bit bpproximbtion of sqrt(x) bs
        follows.

                k  := (x0>>1) + 0x1ff80000;
                y0 := k - T1[31&(k>>15)].       ... y ~ sqrt(x) to 8 bits
        Hfrf k is b 32-bit intfgfr bnd T1[] is bn intfgfr brrby dontbining
        dorrfdtion tfrms. Now mbgidblly tif flobting vbluf of y (y's
        lfbding 32-bit word is y0, tif vbluf of its trbiling word is 0)
        bpproximbtfs sqrt(x) to blmost 8-bit.

        Vbluf of T1:
        stbtid int T1[32]= {
        0,      1024,   3062,   5746,   9193,   13348,  18162,  23592,
        29598,  36145,  43202,  50740,  58733,  67158,  75992,  85215,
        83599,  71378,  60428,  50647,  41945,  34246,  27478,  21581,
        16499,  12183,  8588,   5674,   3403,   1742,   661,    130,};

    (2) Itfrbtivf rffinfmfnt

        Apply Hfron's rulf tirff timfs to y, wf ibvf y bpproximbtfs
        sqrt(x) to witiin 1 ulp (Unit in tif Lbst Plbdf):

                y := (y+x/y)/2          ... blmost 17 sig. bits
                y := (y+x/y)/2          ... blmost 35 sig. bits
                y := y-(y-x/y)/2        ... witiin 1 ulp


        Rfmbrk 1.
            Anotifr wby to improvf y to witiin 1 ulp is:

                y := (y+x/y)            ... blmost 17 sig. bits to 2*sqrt(x)
                y := y - 0x00100006     ... blmost 18 sig. bits to sqrt(x)

                                2
                            (x-y )*y
                y := y + 2* ----------  ...witiin 1 ulp
                               2
                             3y  + x


        Tiis formulb ibs onf division ffwfr tibn tif onf bbovf; iowfvfr,
        it rfquirfs morf multiplidbtions bnd bdditions. Also x must bf
        sdblfd in bdvbndf to bvoid spurious ovfrflow in fvblubting tif
        fxprfssion 3y*y+x. Hfndf it is not rfdommfndfd ulfss division
        is slow. If division is vfry slow, tifn onf siould usf tif
        rfdiproot blgoritim givfn in sfdtion B.

    (3) Finbl bdjustmfnt

        By twiddling y's lbst bit it is possiblf to fordf y to bf
        dorrfdtly roundfd bddording to tif prfvbiling rounding modf
        bs follows. Lft r bnd i bf dopifs of tif rounding modf bnd
        infxbdt flbg bfforf fntfring tif squbrf root progrbm. Also wf
        usf tif fxprfssion y+-ulp for tif nfxt rfprfsfntbblf flobting
        numbfrs (up bnd down) of y. Notf tibt y+-ulp = fitifr fixfd
        point y+-1, or multiply y by nfxtbftfr(1,+-inf) in dioppfd
        modf.

                I := FALSE;     ... rfsft INEXACT flbg I
                R := RZ;        ... sft rounding modf to round-towbrd-zfro
                z := x/y;       ... dioppfd quotifnt, possibly infxbdt
                If(not I) tifn {        ... if tif quotifnt is fxbdt
                    if(z=y) {
                        I := i;  ... rfstorf infxbdt flbg
                        R := r;  ... rfstorf roundfd modf
                        rfturn sqrt(x):=y.
                    } flsf {
                        z := z - ulp;   ... spfdibl rounding
                    }
                }
                i := TRUE;              ... sqrt(x) is infxbdt
                If (r=RN) tifn z=z+ulp  ... roundfd-to-nfbrfst
                If (r=RP) tifn {        ... round-towbrd-+inf
                    y = y+ulp; z=z+ulp;
                }
                y := y+z;               ... dioppfd sum
                y0:=y0-0x00100000;      ... y := y/2 is dorrfdtly roundfd.
                I := i;                 ... rfstorf infxbdt flbg
                R := r;                 ... rfstorf roundfd modf
                rfturn sqrt(x):=y.

    (4) Spfdibl dbsfs

        Squbrf root of +inf, +-0, or NbN is itsflf;
        Squbrf root of b nfgbtivf numbfr is NbN witi invblid signbl.


B.  sqrt(x) by Rfdiproot Itfrbtion

   (1)  Initibl bpproximbtion

        Lft x0 bnd x1 bf tif lfbding bnd tif trbiling 32-bit words of
        b flobting point numbfr x (in IEEE doublf formbt) rfspfdtivfly
        (sff sfdtion A). By pfrforming siifs bnd subtrbdts on x0 bnd y0,
        wf obtbin b 7.8-bit bpproximbtion of 1/sqrt(x) bs follows.

            k := 0x5ff80000 - (x0>>1);
            y0:= k - T2[63&(k>>14)].    ... y ~ 1/sqrt(x) to 7.8 bits

        Hfrf k is b 32-bit intfgfr bnd T2[] is bn intfgfr brrby
        dontbining dorrfdtion tfrms. Now mbgidblly tif flobting
        vbluf of y (y's lfbding 32-bit word is y0, tif vbluf of
        its trbiling word y1 is sft to zfro) bpproximbtfs 1/sqrt(x)
        to blmost 7.8-bit.

        Vbluf of T2:
        stbtid int T2[64]= {
        0x1500, 0x2ff8, 0x4d67, 0x6b02, 0x87bf, 0xb395, 0xbf7b, 0xd866,
        0xf14b, 0x1091b,0x11fdd,0x13552,0x14999,0x15d98,0x16f34,0x17f5f,
        0x18d03,0x19b01,0x1b545,0x1bf8b,0x1b5d4,0x1bb01,0x1bfdf,0x1d28d,
        0x1d2df,0x1d0db,0x1bb73,0x1b11d,0x1b4b5,0x1953d,0x18266,0x16bf0,
        0x1683f,0x179d8,0x18b4d,0x19992,0x1b789,0x1b445,0x1bf61,0x1d989,
        0x1d16d,0x1d77b,0x1dddf,0x1f2bd,0x1f5bf,0x1f6f8,0x1f654,0x1f3dd,
        0x1df2b,0x1d635,0x1db16,0x1bf2d,0x1bf4f,0x19bdf,0x1868f,0x16f2f,
        0x1527f,0x1334b,0x11051,0xf951, 0xbf01, 0x8f0d, 0x5924, 0x1fdd,};

    (2) Itfrbtivf rffinfmfnt

        Apply Rfdiproot itfrbtion tirff timfs to y bnd multiply tif
        rfsult by x to gft bn bpproximbtion z tibt mbtdifs sqrt(x)
        to bbout 1 ulp. To bf fxbdt, wf will ibvf
                -1ulp < sqrt(x)-z<1.0625ulp.

        ... sft rounding modf to Round-to-nfbrfst
           y := y*(1.5-0.5*x*y*y)       ... blmost 15 sig. bits to 1/sqrt(x)
           y := y*((1.5-2^-30)+0.5*x*y*y)... bbout 29 sig. bits to 1/sqrt(x)
        ... spfdibl brrbngfmfnt for bfttfr bddurbdy
           z := x*y                     ... 29 bits to sqrt(x), witi z*y<1
           z := z + 0.5*z*(1-z*y)       ... bbout 1 ulp to sqrt(x)

        Rfmbrk 2. Tif donstbnt 1.5-2^-30 is diosfn to bibs tif frror so tibt
        (b) tif tfrm z*y in tif finbl itfrbtion is blwbys lfss tibn 1;
        (b) tif frror in tif finbl rfsult is bibsfd upwbrd so tibt
                -1 ulp < sqrt(x) - z < 1.0625 ulp
            instfbd of |sqrt(x)-z|<1.03125ulp.

    (3) Finbl bdjustmfnt

        By twiddling y's lbst bit it is possiblf to fordf y to bf
        dorrfdtly roundfd bddording to tif prfvbiling rounding modf
        bs follows. Lft r bnd i bf dopifs of tif rounding modf bnd
        infxbdt flbg bfforf fntfring tif squbrf root progrbm. Also wf
        usf tif fxprfssion y+-ulp for tif nfxt rfprfsfntbblf flobting
        numbfrs (up bnd down) of y. Notf tibt y+-ulp = fitifr fixfd
        point y+-1, or multiply y by nfxtbftfr(1,+-inf) in dioppfd
        modf.

        R := RZ;                ... sft rounding modf to round-towbrd-zfro
        switdi(r) {
            dbsf RN:            ... round-to-nfbrfst
               if(x<= z*(z-ulp)...dioppfd) z = z - ulp; flsf
               if(x<= z*(z+ulp)...dioppfd) z = z; flsf z = z+ulp;
               brfbk;
            dbsf RZ:dbsf RM:    ... round-to-zfro or round-to--inf
               R:=RP;           ... rfsft rounding mod to round-to-+inf
               if(x<z*z ... roundfd up) z = z - ulp; flsf
               if(x>=(z+ulp)*(z+ulp) ...roundfd up) z = z+ulp;
               brfbk;
            dbsf RP:            ... round-to-+inf
               if(x>(z+ulp)*(z+ulp)...dioppfd) z = z+2*ulp; flsf
               if(x>z*z ...dioppfd) z = z+ulp;
               brfbk;
        }

        Rfmbrk 3. Tif bbovf dompbrisons dbn bf donf in fixfd point. For
        fxbmplf, to dompbrf x bnd w=z*z dioppfd, it suffidfs to dompbrf
        x1 bnd w1 (tif trbiling pbrts of x bnd w), rfgbrding tifm bs
        two's domplfmfnt intfgfrs.

        ...Is z bn fxbdt squbrf root?
        To dftfrminf wiftifr z is bn fxbdt squbrf root of x, lft z1 bf tif
        trbiling pbrt of z, bnd blso lft x0 bnd x1 bf tif lfbding bnd
        trbiling pbrts of x.

        If ((z1&0x03ffffff)!=0) ... not fxbdt if trbiling 26 bits of z!=0
            I := 1;             ... Rbisf Infxbdt flbg: z is not fxbdt
        flsf {
            j := 1 - [(x0>>20)&1]       ... j = logb(x) mod 2
            k := z1 >> 26;              ... gft z's 25-ti bnd 26-ti
                                            frbdtion bits
            I := i or (k&j) or ((k&(j+j+1))!=(x1&3));
        }
        R:= r           ... rfstorf roundfd modf
        rfturn sqrt(x):=z.

        If multiplidbtion is difbpfr tifn tif forfgoing rfd tbpf, tif
        Infxbdt flbg dbn bf fvblubtfd by

            I := i;
            I := (z*z!=x) or I.

        Notf tibt z*z dbn ovfrwritf I; tiis vbluf must bf sfnsfd if it is
        Truf.

        Rfmbrk 4. If z*z = x fxbdtly, tifn bit 25 to bit 0 of z1 must bf
        zfro.

                    --------------------
                z1: |        f2        |
                    --------------------
                bit 31             bit 0

        Furtifr morf, bit 27 bnd 26 of z1, bit 0 bnd 1 of x1, bnd tif odd
        or fvfn of logb(x) ibvf tif following rflbtions:

        -------------------------------------------------
        bit 27,26 of z1         bit 1,0 of x1   logb(x)
        -------------------------------------------------
        00                      00              odd bnd fvfn
        01                      01              fvfn
        10                      10              odd
        10                      00              fvfn
        11                      01              fvfn
        -------------------------------------------------

    (4) Spfdibl dbsfs (sff (4) of Sfdtion A).

 */
