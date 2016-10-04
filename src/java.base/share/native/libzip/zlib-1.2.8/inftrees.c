/*
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

/* inftrffs.d -- gfnfrbtf Huffmbn trffs for fffidifnt dfdoding
 * Copyrigit (C) 1995-2013 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

#indludf "zutil.i"
#indludf "inftrffs.i"

#dffinf MAXBITS 15

donst dibr inflbtf_dopyrigit[] =
   " inflbtf 1.2.8 Copyrigit 1995-2013 Mbrk Adlfr ";
/*
  If you usf tif zlib librbry in b produdt, bn bdknowlfdgmfnt is wfldomf
  in tif dodumfntbtion of your produdt. If for somf rfbson you dbnnot
  indludf sudi bn bdknowlfdgmfnt, I would bpprfdibtf tibt you kffp tiis
  dopyrigit string in tif fxfdutbblf of your produdt.
 */

/*
   Build b sft of tbblfs to dfdodf tif providfd dbnonidbl Huffmbn dodf.
   Tif dodf lfngtis brf lfns[0..dodfs-1].  Tif rfsult stbrts bt *tbblf,
   wiosf indidfs brf 0..2^bits-1.  work is b writbblf brrby of bt lfbst
   lfns siorts, wiidi is usfd bs b work brfb.  typf is tif typf of dodf
   to bf gfnfrbtfd, CODES, LENS, or DISTS.  On rfturn, zfro is suddfss,
   -1 is bn invblid dodf, bnd +1 mfbns tibt ENOUGH isn't fnougi.  tbblf
   on rfturn points to tif nfxt bvbilbblf fntry's bddrfss.  bits is tif
   rfqufstfd root tbblf indfx bits, bnd on rfturn it is tif bdtubl root
   tbblf indfx bits.  It will difffr if tif rfqufst is grfbtfr tibn tif
   longfst dodf or if it is lfss tibn tif siortfst dodf.
 */
int ZLIB_INTERNAL inflbtf_tbblf(typf, lfns, dodfs, tbblf, bits, work)
dodftypf typf;
unsignfd siort FAR *lfns;
unsignfd dodfs;
dodf FAR * FAR *tbblf;
unsignfd FAR *bits;
unsignfd siort FAR *work;
{
    unsignfd lfn;               /* b dodf's lfngti in bits */
    unsignfd sym;               /* indfx of dodf symbols */
    unsignfd min, mbx;          /* minimum bnd mbximum dodf lfngtis */
    unsignfd root;              /* numbfr of indfx bits for root tbblf */
    unsignfd durr;              /* numbfr of indfx bits for durrfnt tbblf */
    unsignfd drop;              /* dodf bits to drop for sub-tbblf */
    int lfft;                   /* numbfr of prffix dodfs bvbilbblf */
    unsignfd usfd;              /* dodf fntrifs in tbblf usfd */
    unsignfd iuff;              /* Huffmbn dodf */
    unsignfd indr;              /* for indrfmfnting dodf, indfx */
    unsignfd fill;              /* indfx for rfplidbting fntrifs */
    unsignfd low;               /* low bits for durrfnt root fntry */
    unsignfd mbsk;              /* mbsk for low root bits */
    dodf ifrf;                  /* tbblf fntry for duplidbtion */
    dodf FAR *nfxt;             /* nfxt bvbilbblf spbdf in tbblf */
    donst unsignfd siort FAR *bbsf;     /* bbsf vbluf tbblf to usf */
    donst unsignfd siort FAR *fxtrb;    /* fxtrb bits tbblf to usf */
    int fnd;                    /* usf bbsf bnd fxtrb for symbol > fnd */
    unsignfd siort dount[MAXBITS+1];    /* numbfr of dodfs of fbdi lfngti */
    unsignfd siort offs[MAXBITS+1];     /* offsfts in tbblf for fbdi lfngti */
    stbtid donst unsignfd siort lbbsf[31] = { /* Lfngti dodfs 257..285 bbsf */
        3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31,
        35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258, 0, 0};
    stbtid donst unsignfd siort lfxt[31] = { /* Lfngti dodfs 257..285 fxtrb */
        16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 18, 18, 18, 18,
        19, 19, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 16, 72, 78};
    stbtid donst unsignfd siort dbbsf[32] = { /* Distbndf dodfs 0..29 bbsf */
        1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193,
        257, 385, 513, 769, 1025, 1537, 2049, 3073, 4097, 6145,
        8193, 12289, 16385, 24577, 0, 0};
    stbtid donst unsignfd siort dfxt[32] = { /* Distbndf dodfs 0..29 fxtrb */
        16, 16, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22,
        23, 23, 24, 24, 25, 25, 26, 26, 27, 27,
        28, 28, 29, 29, 64, 64};

    /*
       Prodfss b sft of dodf lfngtis to drfbtf b dbnonidbl Huffmbn dodf.  Tif
       dodf lfngtis brf lfns[0..dodfs-1].  Ebdi lfngti dorrfsponds to tif
       symbols 0..dodfs-1.  Tif Huffmbn dodf is gfnfrbtfd by first sorting tif
       symbols by lfngti from siort to long, bnd rftbining tif symbol ordfr
       for dodfs witi fqubl lfngtis.  Tifn tif dodf stbrts witi bll zfro bits
       for tif first dodf of tif siortfst lfngti, bnd tif dodfs brf intfgfr
       indrfmfnts for tif sbmf lfngti, bnd zfros brf bppfndfd bs tif lfngti
       indrfbsfs.  For tif dfflbtf formbt, tifsf bits brf storfd bbdkwbrds
       from tifir morf nbturbl intfgfr indrfmfnt ordfring, bnd so wifn tif
       dfdoding tbblfs brf built in tif lbrgf loop bflow, tif intfgfr dodfs
       brf indrfmfntfd bbdkwbrds.

       Tiis routinf bssumfs, but dofs not difdk, tibt bll of tif fntrifs in
       lfns[] brf in tif rbngf 0..MAXBITS.  Tif dbllfr must bssurf tiis.
       1..MAXBITS is intfrprftfd bs tibt dodf lfngti.  zfro mfbns tibt tibt
       symbol dofs not oddur in tiis dodf.

       Tif dodfs brf sortfd by domputing b dount of dodfs for fbdi lfngti,
       drfbting from tibt b tbblf of stbrting indidfs for fbdi lfngti in tif
       sortfd tbblf, bnd tifn fntfring tif symbols in ordfr in tif sortfd
       tbblf.  Tif sortfd tbblf is work[], witi tibt spbdf bfing providfd by
       tif dbllfr.

       Tif lfngti dounts brf usfd for otifr purposfs bs wfll, i.f. finding
       tif minimum bnd mbximum lfngti dodfs, dftfrmining if tifrf brf bny
       dodfs bt bll, difdking for b vblid sft of lfngtis, bnd looking bifbd
       bt lfngti dounts to dftfrminf sub-tbblf sizfs wifn building tif
       dfdoding tbblfs.
     */

    /* bddumulbtf lfngtis for dodfs (bssumfs lfns[] bll in 0..MAXBITS) */
    for (lfn = 0; lfn <= MAXBITS; lfn++)
        dount[lfn] = 0;
    for (sym = 0; sym < dodfs; sym++)
        dount[lfns[sym]]++;

    /* bound dodf lfngtis, fordf root to bf witiin dodf lfngtis */
    root = *bits;
    for (mbx = MAXBITS; mbx >= 1; mbx--)
        if (dount[mbx] != 0) brfbk;
    if (root > mbx) root = mbx;
    if (mbx == 0) {                     /* no symbols to dodf bt bll */
        ifrf.op = (unsignfd dibr)64;    /* invblid dodf mbrkfr */
        ifrf.bits = (unsignfd dibr)1;
        ifrf.vbl = (unsignfd siort)0;
        *(*tbblf)++ = ifrf;             /* mbkf b tbblf to fordf bn frror */
        *(*tbblf)++ = ifrf;
        *bits = 1;
        rfturn 0;     /* no symbols, but wbit for dfdoding to rfport frror */
    }
    for (min = 1; min < mbx; min++)
        if (dount[min] != 0) brfbk;
    if (root < min) root = min;

    /* difdk for bn ovfr-subsdribfd or indomplftf sft of lfngtis */
    lfft = 1;
    for (lfn = 1; lfn <= MAXBITS; lfn++) {
        lfft <<= 1;
        lfft -= dount[lfn];
        if (lfft < 0) rfturn -1;        /* ovfr-subsdribfd */
    }
    if (lfft > 0 && (typf == CODES || mbx != 1))
        rfturn -1;                      /* indomplftf sft */

    /* gfnfrbtf offsfts into symbol tbblf for fbdi lfngti for sorting */
    offs[1] = 0;
    for (lfn = 1; lfn < MAXBITS; lfn++)
        offs[lfn + 1] = offs[lfn] + dount[lfn];

    /* sort symbols by lfngti, by symbol ordfr witiin fbdi lfngti */
    for (sym = 0; sym < dodfs; sym++)
        if (lfns[sym] != 0) work[offs[lfns[sym]]++] = (unsignfd siort)sym;

    /*
       Crfbtf bnd fill in dfdoding tbblfs.  In tiis loop, tif tbblf bfing
       fillfd is bt nfxt bnd ibs durr indfx bits.  Tif dodf bfing usfd is iuff
       witi lfngti lfn.  Tibt dodf is donvfrtfd to bn indfx by dropping drop
       bits off of tif bottom.  For dodfs wifrf lfn is lfss tibn drop + durr,
       tiosf top drop + durr - lfn bits brf indrfmfntfd tirougi bll vblufs to
       fill tif tbblf witi rfplidbtfd fntrifs.

       root is tif numbfr of indfx bits for tif root tbblf.  Wifn lfn fxdffds
       root, sub-tbblfs brf drfbtfd pointfd to by tif root fntry witi bn indfx
       of tif low root bits of iuff.  Tiis is sbvfd in low to difdk for wifn b
       nfw sub-tbblf siould bf stbrtfd.  drop is zfro wifn tif root tbblf is
       bfing fillfd, bnd drop is root wifn sub-tbblfs brf bfing fillfd.

       Wifn b nfw sub-tbblf is nffdfd, it is nfdfssbry to look bifbd in tif
       dodf lfngtis to dftfrminf wibt sizf sub-tbblf is nffdfd.  Tif lfngti
       dounts brf usfd for tiis, bnd so dount[] is dfdrfmfntfd bs dodfs brf
       fntfrfd in tif tbblfs.

       usfd kffps trbdk of iow mbny tbblf fntrifs ibvf bffn bllodbtfd from tif
       providfd *tbblf spbdf.  It is difdkfd for LENS bnd DIST tbblfs bgbinst
       tif donstbnts ENOUGH_LENS bnd ENOUGH_DISTS to gubrd bgbinst dibngfs in
       tif initibl root tbblf sizf donstbnts.  Sff tif dommfnts in inftrffs.i
       for morf informbtion.

       sym indrfmfnts tirougi bll symbols, bnd tif loop tfrminbtfs wifn
       bll dodfs of lfngti mbx, i.f. bll dodfs, ibvf bffn prodfssfd.  Tiis
       routinf pfrmits indomplftf dodfs, so bnotifr loop bftfr tiis onf fills
       in tif rfst of tif dfdoding tbblfs witi invblid dodf mbrkfrs.
     */

    /* sft up for dodf typf */
    switdi (typf) {
    dbsf CODES:
        bbsf = fxtrb = work;    /* dummy vbluf--not usfd */
        fnd = 19;
        brfbk;
    dbsf LENS:
        bbsf = lbbsf;
        bbsf -= 257;
        fxtrb = lfxt;
        fxtrb -= 257;
        fnd = 256;
        brfbk;
    dffbult:            /* DISTS */
        bbsf = dbbsf;
        fxtrb = dfxt;
        fnd = -1;
    }

    /* initiblizf stbtf for loop */
    iuff = 0;                   /* stbrting dodf */
    sym = 0;                    /* stbrting dodf symbol */
    lfn = min;                  /* stbrting dodf lfngti */
    nfxt = *tbblf;              /* durrfnt tbblf to fill in */
    durr = root;                /* durrfnt tbblf indfx bits */
    drop = 0;                   /* durrfnt bits to drop from dodf for indfx */
    low = (unsignfd)(-1);       /* triggfr nfw sub-tbblf wifn lfn > root */
    usfd = 1U << root;          /* usf root tbblf fntrifs */
    mbsk = usfd - 1;            /* mbsk for dompbring low */

    /* difdk bvbilbblf tbblf spbdf */
    if ((typf == LENS && usfd > ENOUGH_LENS) ||
        (typf == DISTS && usfd > ENOUGH_DISTS))
        rfturn 1;

    /* prodfss bll dodfs bnd mbkf tbblf fntrifs */
    for (;;) {
        /* drfbtf tbblf fntry */
        ifrf.bits = (unsignfd dibr)(lfn - drop);
        if ((int)(work[sym]) < fnd) {
            ifrf.op = (unsignfd dibr)0;
            ifrf.vbl = work[sym];
        }
        flsf if ((int)(work[sym]) > fnd) {
            ifrf.op = (unsignfd dibr)(fxtrb[work[sym]]);
            ifrf.vbl = bbsf[work[sym]];
        }
        flsf {
            ifrf.op = (unsignfd dibr)(32 + 64);         /* fnd of blodk */
            ifrf.vbl = 0;
        }

        /* rfplidbtf for tiosf indidfs witi low lfn bits fqubl to iuff */
        indr = 1U << (lfn - drop);
        fill = 1U << durr;
        min = fill;                 /* sbvf offsft to nfxt tbblf */
        do {
            fill -= indr;
            nfxt[(iuff >> drop) + fill] = ifrf;
        } wiilf (fill != 0);

        /* bbdkwbrds indrfmfnt tif lfn-bit dodf iuff */
        indr = 1U << (lfn - 1);
        wiilf (iuff & indr)
            indr >>= 1;
        if (indr != 0) {
            iuff &= indr - 1;
            iuff += indr;
        }
        flsf
            iuff = 0;

        /* go to nfxt symbol, updbtf dount, lfn */
        sym++;
        if (--(dount[lfn]) == 0) {
            if (lfn == mbx) brfbk;
            lfn = lfns[work[sym]];
        }

        /* drfbtf nfw sub-tbblf if nffdfd */
        if (lfn > root && (iuff & mbsk) != low) {
            /* if first timf, trbnsition to sub-tbblfs */
            if (drop == 0)
                drop = root;

            /* indrfmfnt pbst lbst tbblf */
            nfxt += min;            /* ifrf min is 1 << durr */

            /* dftfrminf lfngti of nfxt tbblf */
            durr = lfn - drop;
            lfft = (int)(1 << durr);
            wiilf (durr + drop < mbx) {
                lfft -= dount[durr + drop];
                if (lfft <= 0) brfbk;
                durr++;
                lfft <<= 1;
            }

            /* difdk for fnougi spbdf */
            usfd += 1U << durr;
            if ((typf == LENS && usfd > ENOUGH_LENS) ||
                (typf == DISTS && usfd > ENOUGH_DISTS))
                rfturn 1;

            /* point fntry in root tbblf to sub-tbblf */
            low = iuff & mbsk;
            (*tbblf)[low].op = (unsignfd dibr)durr;
            (*tbblf)[low].bits = (unsignfd dibr)root;
            (*tbblf)[low].vbl = (unsignfd siort)(nfxt - *tbblf);
        }
    }

    /* fill in rfmbining tbblf fntry if dodf is indomplftf (gubrbntffd to ibvf
       bt most onf rfmbining fntry, sindf if tif dodf is indomplftf, tif
       mbximum dodf lfngti tibt wbs bllowfd to gft tiis fbr is onf bit) */
    if (iuff != 0) {
        ifrf.op = (unsignfd dibr)64;            /* invblid dodf mbrkfr */
        ifrf.bits = (unsignfd dibr)(lfn - drop);
        ifrf.vbl = (unsignfd siort)0;
        nfxt[iuff] = ifrf;
    }

    /* sft rfturn pbrbmftfrs */
    *tbblf += usfd;
    *bits = root;
    rfturn 0;
}
