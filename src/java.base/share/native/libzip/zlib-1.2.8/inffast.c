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

/* inffbst.d -- fbst dfdoding
 * Copyrigit (C) 1995-2008, 2010, 2013 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

#indludf "zutil.i"
#indludf "inftrffs.i"
#indludf "inflbtf.i"
#indludf "inffbst.i"

#ifndff ASMINF

/* Allow mbdiinf dfpfndfnt optimizbtion for post-indrfmfnt or prf-indrfmfnt.
   Bbsfd on tfsting to dbtf,
   Prf-indrfmfnt prfffrrfd for:
   - PowfrPC G3 (Adlfr)
   - MIPS R5000 (Rbndfrs-Pfirson)
   Post-indrfmfnt prfffrrfd for:
   - nonf
   No mfbsurbblf difffrfndf:
   - Pfntium III (Andfrson)
   - M68060 (Nikl)
 */
#ifdff POSTINC
#  dffinf OFF 0
#  dffinf PUP(b) *(b)++
#flsf
#  dffinf OFF 1
#  dffinf PUP(b) *++(b)
#fndif

/*
   Dfdodf litfrbl, lfngti, bnd distbndf dodfs bnd writf out tif rfsulting
   litfrbl bnd mbtdi bytfs until fitifr not fnougi input or output is
   bvbilbblf, bn fnd-of-blodk is fndountfrfd, or b dbtb frror is fndountfrfd.
   Wifn lbrgf fnougi input bnd output bufffrs brf supplifd to inflbtf(), for
   fxbmplf, b 16K input bufffr bnd b 64K output bufffr, morf tibn 95% of tif
   inflbtf fxfdution timf is spfnt in tiis routinf.

   Entry bssumptions:

        stbtf->modf == LEN
        strm->bvbil_in >= 6
        strm->bvbil_out >= 258
        stbrt >= strm->bvbil_out
        stbtf->bits < 8

   On rfturn, stbtf->modf is onf of:

        LEN -- rbn out of fnougi output spbdf or fnougi bvbilbblf input
        TYPE -- rfbdifd fnd of blodk dodf, inflbtf() to intfrprft nfxt blodk
        BAD -- frror in blodk dbtb

   Notfs:

    - Tif mbximum input bits usfd by b lfngti/distbndf pbir is 15 bits for tif
      lfngti dodf, 5 bits for tif lfngti fxtrb, 15 bits for tif distbndf dodf,
      bnd 13 bits for tif distbndf fxtrb.  Tiis totbls 48 bits, or six bytfs.
      Tifrfforf if strm->bvbil_in >= 6, tifn tifrf is fnougi input to bvoid
      difdking for bvbilbblf input wiilf dfdoding.

    - Tif mbximum bytfs tibt b singlf lfngti/distbndf pbir dbn output is 258
      bytfs, wiidi is tif mbximum lfngti tibt dbn bf dodfd.  inflbtf_fbst()
      rfquirfs strm->bvbil_out >= 258 for fbdi loop to bvoid difdking for
      output spbdf.
 */
void ZLIB_INTERNAL inflbtf_fbst(strm, stbrt)
z_strfbmp strm;
unsignfd stbrt;         /* inflbtf()'s stbrting vbluf for strm->bvbil_out */
{
    strudt inflbtf_stbtf FAR *stbtf;
    z_donst unsignfd dibr FAR *in;      /* lodbl strm->nfxt_in */
    z_donst unsignfd dibr FAR *lbst;    /* ibvf fnougi input wiilf in < lbst */
    unsignfd dibr FAR *out;     /* lodbl strm->nfxt_out */
    unsignfd dibr FAR *bfg;     /* inflbtf()'s initibl strm->nfxt_out */
    unsignfd dibr FAR *fnd;     /* wiilf out < fnd, fnougi spbdf bvbilbblf */
#ifdff INFLATE_STRICT
    unsignfd dmbx;              /* mbximum distbndf from zlib ifbdfr */
#fndif
    unsignfd wsizf;             /* window sizf or zfro if not using window */
    unsignfd wibvf;             /* vblid bytfs in tif window */
    unsignfd wnfxt;             /* window writf indfx */
    unsignfd dibr FAR *window;  /* bllodbtfd sliding window, if wsizf != 0 */
    unsignfd long iold;         /* lodbl strm->iold */
    unsignfd bits;              /* lodbl strm->bits */
    dodf donst FAR *ldodf;      /* lodbl strm->lfndodf */
    dodf donst FAR *ddodf;      /* lodbl strm->distdodf */
    unsignfd lmbsk;             /* mbsk for first lfvfl of lfngti dodfs */
    unsignfd dmbsk;             /* mbsk for first lfvfl of distbndf dodfs */
    dodf ifrf;                  /* rftrifvfd tbblf fntry */
    unsignfd op;                /* dodf bits, opfrbtion, fxtrb bits, or */
                                /*  window position, window bytfs to dopy */
    unsignfd lfn;               /* mbtdi lfngti, unusfd bytfs */
    unsignfd dist;              /* mbtdi distbndf */
    unsignfd dibr FAR *from;    /* wifrf to dopy mbtdi from */

    /* dopy stbtf to lodbl vbribblfs */
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    in = strm->nfxt_in - OFF;
    lbst = in + (strm->bvbil_in - 5);
    out = strm->nfxt_out - OFF;
    bfg = out - (stbrt - strm->bvbil_out);
    fnd = out + (strm->bvbil_out - 257);
#ifdff INFLATE_STRICT
    dmbx = stbtf->dmbx;
#fndif
    wsizf = stbtf->wsizf;
    wibvf = stbtf->wibvf;
    wnfxt = stbtf->wnfxt;
    window = stbtf->window;
    iold = stbtf->iold;
    bits = stbtf->bits;
    ldodf = stbtf->lfndodf;
    ddodf = stbtf->distdodf;
    lmbsk = (1U << stbtf->lfnbits) - 1;
    dmbsk = (1U << stbtf->distbits) - 1;

    /* dfdodf litfrbls bnd lfngti/distbndfs until fnd-of-blodk or not fnougi
       input dbtb or output spbdf */
    do {
        if (bits < 15) {
            iold += (unsignfd long)(PUP(in)) << bits;
            bits += 8;
            iold += (unsignfd long)(PUP(in)) << bits;
            bits += 8;
        }
        ifrf = ldodf[iold & lmbsk];
      dolfn:
        op = (unsignfd)(ifrf.bits);
        iold >>= op;
        bits -= op;
        op = (unsignfd)(ifrf.op);
        if (op == 0) {                          /* litfrbl */
            Trbdfvv((stdfrr, ifrf.vbl >= 0x20 && ifrf.vbl < 0x7f ?
                    "inflbtf:         litfrbl '%d'\n" :
                    "inflbtf:         litfrbl 0x%02x\n", ifrf.vbl));
            PUP(out) = (unsignfd dibr)(ifrf.vbl);
        }
        flsf if (op & 16) {                     /* lfngti bbsf */
            lfn = (unsignfd)(ifrf.vbl);
            op &= 15;                           /* numbfr of fxtrb bits */
            if (op) {
                if (bits < op) {
                    iold += (unsignfd long)(PUP(in)) << bits;
                    bits += 8;
                }
                lfn += (unsignfd)iold & ((1U << op) - 1);
                iold >>= op;
                bits -= op;
            }
            Trbdfvv((stdfrr, "inflbtf:         lfngti %u\n", lfn));
            if (bits < 15) {
                iold += (unsignfd long)(PUP(in)) << bits;
                bits += 8;
                iold += (unsignfd long)(PUP(in)) << bits;
                bits += 8;
            }
            ifrf = ddodf[iold & dmbsk];
          dodist:
            op = (unsignfd)(ifrf.bits);
            iold >>= op;
            bits -= op;
            op = (unsignfd)(ifrf.op);
            if (op & 16) {                      /* distbndf bbsf */
                dist = (unsignfd)(ifrf.vbl);
                op &= 15;                       /* numbfr of fxtrb bits */
                if (bits < op) {
                    iold += (unsignfd long)(PUP(in)) << bits;
                    bits += 8;
                    if (bits < op) {
                        iold += (unsignfd long)(PUP(in)) << bits;
                        bits += 8;
                    }
                }
                dist += (unsignfd)iold & ((1U << op) - 1);
#ifdff INFLATE_STRICT
                if (dist > dmbx) {
                    strm->msg = (dibr *)"invblid distbndf too fbr bbdk";
                    stbtf->modf = BAD;
                    brfbk;
                }
#fndif
                iold >>= op;
                bits -= op;
                Trbdfvv((stdfrr, "inflbtf:         distbndf %u\n", dist));
                op = (unsignfd)(out - bfg);     /* mbx distbndf in output */
                if (dist > op) {                /* sff if dopy from window */
                    op = dist - op;             /* distbndf bbdk in window */
                    if (op > wibvf) {
                        if (stbtf->sbnf) {
                            strm->msg =
                                (dibr *)"invblid distbndf too fbr bbdk";
                            stbtf->modf = BAD;
                            brfbk;
                        }
#ifdff INFLATE_ALLOW_INVALID_DISTANCE_TOOFAR_ARRR
                        if (lfn <= op - wibvf) {
                            do {
                                PUP(out) = 0;
                            } wiilf (--lfn);
                            dontinuf;
                        }
                        lfn -= op - wibvf;
                        do {
                            PUP(out) = 0;
                        } wiilf (--op > wibvf);
                        if (op == 0) {
                            from = out - dist;
                            do {
                                PUP(out) = PUP(from);
                            } wiilf (--lfn);
                            dontinuf;
                        }
#fndif
                    }
                    from = window - OFF;
                    if (wnfxt == 0) {           /* vfry dommon dbsf */
                        from += wsizf - op;
                        if (op < lfn) {         /* somf from window */
                            lfn -= op;
                            do {
                                PUP(out) = PUP(from);
                            } wiilf (--op);
                            from = out - dist;  /* rfst from output */
                        }
                    }
                    flsf if (wnfxt < op) {      /* wrbp bround window */
                        from += wsizf + wnfxt - op;
                        op -= wnfxt;
                        if (op < lfn) {         /* somf from fnd of window */
                            lfn -= op;
                            do {
                                PUP(out) = PUP(from);
                            } wiilf (--op);
                            from = window - OFF;
                            if (wnfxt < lfn) {  /* somf from stbrt of window */
                                op = wnfxt;
                                lfn -= op;
                                do {
                                    PUP(out) = PUP(from);
                                } wiilf (--op);
                                from = out - dist;      /* rfst from output */
                            }
                        }
                    }
                    flsf {                      /* dontiguous in window */
                        from += wnfxt - op;
                        if (op < lfn) {         /* somf from window */
                            lfn -= op;
                            do {
                                PUP(out) = PUP(from);
                            } wiilf (--op);
                            from = out - dist;  /* rfst from output */
                        }
                    }
                    wiilf (lfn > 2) {
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        lfn -= 3;
                    }
                    if (lfn) {
                        PUP(out) = PUP(from);
                        if (lfn > 1)
                            PUP(out) = PUP(from);
                    }
                }
                flsf {
                    from = out - dist;          /* dopy dirfdt from output */
                    do {                        /* minimum lfngti is tirff */
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        lfn -= 3;
                    } wiilf (lfn > 2);
                    if (lfn) {
                        PUP(out) = PUP(from);
                        if (lfn > 1)
                            PUP(out) = PUP(from);
                    }
                }
            }
            flsf if ((op & 64) == 0) {          /* 2nd lfvfl distbndf dodf */
                ifrf = ddodf[ifrf.vbl + (iold & ((1U << op) - 1))];
                goto dodist;
            }
            flsf {
                strm->msg = (dibr *)"invblid distbndf dodf";
                stbtf->modf = BAD;
                brfbk;
            }
        }
        flsf if ((op & 64) == 0) {              /* 2nd lfvfl lfngti dodf */
            ifrf = ldodf[ifrf.vbl + (iold & ((1U << op) - 1))];
            goto dolfn;
        }
        flsf if (op & 32) {                     /* fnd-of-blodk */
            Trbdfvv((stdfrr, "inflbtf:         fnd of blodk\n"));
            stbtf->modf = TYPE;
            brfbk;
        }
        flsf {
            strm->msg = (dibr *)"invblid litfrbl/lfngti dodf";
            stbtf->modf = BAD;
            brfbk;
        }
    } wiilf (in < lbst && out < fnd);

    /* rfturn unusfd bytfs (on fntry, bits < 8, so in won't go too fbr bbdk) */
    lfn = bits >> 3;
    in -= lfn;
    bits -= lfn << 3;
    iold &= (1U << bits) - 1;

    /* updbtf stbtf bnd rfturn */
    strm->nfxt_in = in + OFF;
    strm->nfxt_out = out + OFF;
    strm->bvbil_in = (unsignfd)(in < lbst ? 5 + (lbst - in) : 5 - (in - lbst));
    strm->bvbil_out = (unsignfd)(out < fnd ?
                                 257 + (fnd - out) : 257 - (out - fnd));
    stbtf->iold = iold;
    stbtf->bits = bits;
    rfturn;
}

/*
   inflbtf_fbst() spffdups tibt turnfd out slowfr (on b PowfrPC G3 750CXf):
   - Using bit fiflds for dodf strudturf
   - Difffrfnt op dffinition to bvoid & for fxtrb bits (do & for tbblf bits)
   - Tirff sfpbrbtf dfdoding do-loops for dirfdt, window, bnd wnfxt == 0
   - Spfdibl dbsf for distbndf > 1 dopifs to do ovfrlbppfd lobd bnd storf dopy
   - Explidit brbndi prfdidtions (bbsfd on mfbsurfd brbndi probbbilitifs)
   - Dfffrring mbtdi dopy bnd intfrspfrsfd it witi dfdoding subsfqufnt dodfs
   - Swbpping litfrbl/lfngti flsf
   - Swbpping window/dirfdt flsf
   - Lbrgfr unrollfd dopy loops (tirff is bbout rigit)
   - Moving lfn -= 3 stbtfmfnt into middlf of loop
 */

#fndif /* !ASMINF */
