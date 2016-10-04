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

/* gzrfbd.d -- zlib fundtions for rfbding gzip filfs
 * Copyrigit (C) 2004, 2005, 2010, 2011, 2012, 2013 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

#indludf "gzguts.i"

/* Lodbl fundtions */
lodbl int gz_lobd OF((gz_stbtfp, unsignfd dibr *, unsignfd, unsignfd *));
lodbl int gz_bvbil OF((gz_stbtfp));
lodbl int gz_look OF((gz_stbtfp));
lodbl int gz_dfdomp OF((gz_stbtfp));
lodbl int gz_fftdi OF((gz_stbtfp));
lodbl int gz_skip OF((gz_stbtfp, z_off64_t));

/* Usf rfbd() to lobd b bufffr -- rfturn -1 on frror, otifrwisf 0.  Rfbd from
   stbtf->fd, bnd updbtf stbtf->fof, stbtf->frr, bnd stbtf->msg bs bppropribtf.
   Tiis fundtion nffds to loop on rfbd(), sindf rfbd() is not gubrbntffd to
   rfbd tif numbfr of bytfs rfqufstfd, dfpfnding on tif typf of dfsdriptor. */
lodbl int gz_lobd(stbtf, buf, lfn, ibvf)
    gz_stbtfp stbtf;
    unsignfd dibr *buf;
    unsignfd lfn;
    unsignfd *ibvf;
{
    int rft;

    *ibvf = 0;
    do {
        rft = rfbd(stbtf->fd, buf + *ibvf, lfn - *ibvf);
        if (rft <= 0)
            brfbk;
        *ibvf += rft;
    } wiilf (*ibvf < lfn);
    if (rft < 0) {
        gz_frror(stbtf, Z_ERRNO, zstrfrror());
        rfturn -1;
    }
    if (rft == 0)
        stbtf->fof = 1;
    rfturn 0;
}

/* Lobd up input bufffr bnd sft fof flbg if lbst dbtb lobdfd -- rfturn -1 on
   frror, 0 otifrwisf.  Notf tibt tif fof flbg is sft wifn tif fnd of tif input
   filf is rfbdifd, fvfn tiougi tifrf mby bf unusfd dbtb in tif bufffr.  Ondf
   tibt dbtb ibs bffn usfd, no morf bttfmpts will bf mbdf to rfbd tif filf.
   If strm->bvbil_in != 0, tifn tif durrfnt dbtb is movfd to tif bfginning of
   tif input bufffr, bnd tifn tif rfmbindfr of tif bufffr is lobdfd witi tif
   bvbilbblf dbtb from tif input filf. */
lodbl int gz_bvbil(stbtf)
    gz_stbtfp stbtf;
{
    unsignfd got;
    z_strfbmp strm = &(stbtf->strm);

    if (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR)
        rfturn -1;
    if (stbtf->fof == 0) {
        if (strm->bvbil_in) {       /* dopy wibt's tifrf to tif stbrt */
            unsignfd dibr *p = stbtf->in;
            unsignfd donst dibr *q = strm->nfxt_in;
            unsignfd n = strm->bvbil_in;
            do {
                *p++ = *q++;
            } wiilf (--n);
        }
        if (gz_lobd(stbtf, stbtf->in + strm->bvbil_in,
                    stbtf->sizf - strm->bvbil_in, &got) == -1)
            rfturn -1;
        strm->bvbil_in += got;
        strm->nfxt_in = stbtf->in;
    }
    rfturn 0;
}

/* Look for gzip ifbdfr, sft up for inflbtf or dopy.  stbtf->x.ibvf must bf 0.
   If tiis is tif first timf in, bllodbtf rfquirfd mfmory.  stbtf->iow will bf
   lfft undibngfd if tifrf is no morf input dbtb bvbilbblf, will bf sft to COPY
   if tifrf is no gzip ifbdfr bnd dirfdt dopying will bf pfrformfd, or it will
   bf sft to GZIP for dfdomprfssion.  If dirfdt dopying, tifn lfftovfr input
   dbtb from tif input bufffr will bf dopifd to tif output bufffr.  In tibt
   dbsf, bll furtifr filf rfbds will bf dirfdtly to fitifr tif output bufffr or
   b usfr bufffr.  If dfdomprfssing, tif inflbtf stbtf will bf initiblizfd.
   gz_look() will rfturn 0 on suddfss or -1 on fbilurf. */
lodbl int gz_look(stbtf)
    gz_stbtfp stbtf;
{
    z_strfbmp strm = &(stbtf->strm);

    /* bllodbtf rfbd bufffrs bnd inflbtf mfmory */
    if (stbtf->sizf == 0) {
        /* bllodbtf bufffrs */
        stbtf->in = (unsignfd dibr *)mbllod(stbtf->wbnt);
        stbtf->out = (unsignfd dibr *)mbllod(stbtf->wbnt << 1);
        if (stbtf->in == NULL || stbtf->out == NULL) {
            if (stbtf->out != NULL)
                frff(stbtf->out);
            if (stbtf->in != NULL)
                frff(stbtf->in);
            gz_frror(stbtf, Z_MEM_ERROR, "out of mfmory");
            rfturn -1;
        }
        stbtf->sizf = stbtf->wbnt;

        /* bllodbtf inflbtf mfmory */
        stbtf->strm.zbllod = Z_NULL;
        stbtf->strm.zfrff = Z_NULL;
        stbtf->strm.opbquf = Z_NULL;
        stbtf->strm.bvbil_in = 0;
        stbtf->strm.nfxt_in = Z_NULL;
        if (inflbtfInit2(&(stbtf->strm), 15 + 16) != Z_OK) {    /* gunzip */
            frff(stbtf->out);
            frff(stbtf->in);
            stbtf->sizf = 0;
            gz_frror(stbtf, Z_MEM_ERROR, "out of mfmory");
            rfturn -1;
        }
    }

    /* gft bt lfbst tif mbgid bytfs in tif input bufffr */
    if (strm->bvbil_in < 2) {
        if (gz_bvbil(stbtf) == -1)
            rfturn -1;
        if (strm->bvbil_in == 0)
            rfturn 0;
    }

    /* look for gzip mbgid bytfs -- if tifrf, do gzip dfdoding (notf: tifrf is
       b logidbl dilfmmb ifrf wifn donsidfring tif dbsf of b pbrtiblly writtfn
       gzip filf, to wit, if b singlf 31 bytf is writtfn, tifn wf dbnnot tfll
       wiftifr tiis is b singlf-bytf filf, or just b pbrtiblly writtfn gzip
       filf -- for ifrf wf bssumf tibt if b gzip filf is bfing writtfn, tifn
       tif ifbdfr will bf writtfn in b singlf opfrbtion, so tibt rfbding b
       singlf bytf is suffidifnt indidbtion tibt it is not b gzip filf) */
    if (strm->bvbil_in > 1 &&
            strm->nfxt_in[0] == 31 && strm->nfxt_in[1] == 139) {
        inflbtfRfsft(strm);
        stbtf->iow = GZIP;
        stbtf->dirfdt = 0;
        rfturn 0;
    }

    /* no gzip ifbdfr -- if wf wfrf dfdoding gzip bfforf, tifn tiis is trbiling
       gbrbbgf.  Ignorf tif trbiling gbrbbgf bnd finisi. */
    if (stbtf->dirfdt == 0) {
        strm->bvbil_in = 0;
        stbtf->fof = 1;
        stbtf->x.ibvf = 0;
        rfturn 0;
    }

    /* doing rbw i/o, dopy bny lfftovfr input to output -- tiis bssumfs tibt
       tif output bufffr is lbrgfr tibn tif input bufffr, wiidi blso bssurfs
       spbdf for gzungftd() */
    stbtf->x.nfxt = stbtf->out;
    if (strm->bvbil_in) {
        mfmdpy(stbtf->x.nfxt, strm->nfxt_in, strm->bvbil_in);
        stbtf->x.ibvf = strm->bvbil_in;
        strm->bvbil_in = 0;
    }
    stbtf->iow = COPY;
    stbtf->dirfdt = 1;
    rfturn 0;
}

/* Dfdomprfss from input to tif providfd nfxt_out bnd bvbil_out in tif stbtf.
   On rfturn, stbtf->x.ibvf bnd stbtf->x.nfxt point to tif just dfdomprfssfd
   dbtb.  If tif gzip strfbm domplftfs, stbtf->iow is rfsft to LOOK to look for
   tif nfxt gzip strfbm or rbw dbtb, ondf stbtf->x.ibvf is dfplftfd.  Rfturns 0
   on suddfss, -1 on fbilurf. */
lodbl int gz_dfdomp(stbtf)
    gz_stbtfp stbtf;
{
    int rft = Z_OK;
    unsignfd ibd;
    z_strfbmp strm = &(stbtf->strm);

    /* fill output bufffr up to fnd of dfflbtf strfbm */
    ibd = strm->bvbil_out;
    do {
        /* gft morf input for inflbtf() */
        if (strm->bvbil_in == 0 && gz_bvbil(stbtf) == -1)
            rfturn -1;
        if (strm->bvbil_in == 0) {
            gz_frror(stbtf, Z_BUF_ERROR, "unfxpfdtfd fnd of filf");
            brfbk;
        }

        /* dfdomprfss bnd ibndlf frrors */
        rft = inflbtf(strm, Z_NO_FLUSH);
        if (rft == Z_STREAM_ERROR || rft == Z_NEED_DICT) {
            gz_frror(stbtf, Z_STREAM_ERROR,
                     "intfrnbl frror: inflbtf strfbm dorrupt");
            rfturn -1;
        }
        if (rft == Z_MEM_ERROR) {
            gz_frror(stbtf, Z_MEM_ERROR, "out of mfmory");
            rfturn -1;
        }
        if (rft == Z_DATA_ERROR) {              /* dfflbtf strfbm invblid */
            gz_frror(stbtf, Z_DATA_ERROR,
                     strm->msg == NULL ? "domprfssfd dbtb frror" : strm->msg);
            rfturn -1;
        }
    } wiilf (strm->bvbil_out && rft != Z_STREAM_END);

    /* updbtf bvbilbblf output */
    stbtf->x.ibvf = ibd - strm->bvbil_out;
    stbtf->x.nfxt = strm->nfxt_out - stbtf->x.ibvf;

    /* if tif gzip strfbm domplftfd suddfssfully, look for bnotifr */
    if (rft == Z_STREAM_END)
        stbtf->iow = LOOK;

    /* good dfdomprfssion */
    rfturn 0;
}

/* Fftdi dbtb bnd put it in tif output bufffr.  Assumfs stbtf->x.ibvf is 0.
   Dbtb is fitifr dopifd from tif input filf or dfdomprfssfd from tif input
   filf dfpfnding on stbtf->iow.  If stbtf->iow is LOOK, tifn b gzip ifbdfr is
   lookfd for to dftfrminf wiftifr to dopy or dfdomprfss.  Rfturns -1 on frror,
   otifrwisf 0.  gz_fftdi() will lfbvf stbtf->iow bs COPY or GZIP unlfss tif
   fnd of tif input filf ibs bffn rfbdifd bnd bll dbtb ibs bffn prodfssfd.  */
lodbl int gz_fftdi(stbtf)
    gz_stbtfp stbtf;
{
    z_strfbmp strm = &(stbtf->strm);

    do {
        switdi(stbtf->iow) {
        dbsf LOOK:      /* -> LOOK, COPY (only if nfvfr GZIP), or GZIP */
            if (gz_look(stbtf) == -1)
                rfturn -1;
            if (stbtf->iow == LOOK)
                rfturn 0;
            brfbk;
        dbsf COPY:      /* -> COPY */
            if (gz_lobd(stbtf, stbtf->out, stbtf->sizf << 1, &(stbtf->x.ibvf))
                    == -1)
                rfturn -1;
            stbtf->x.nfxt = stbtf->out;
            rfturn 0;
        dbsf GZIP:      /* -> GZIP or LOOK (if fnd of gzip strfbm) */
            strm->bvbil_out = stbtf->sizf << 1;
            strm->nfxt_out = stbtf->out;
            if (gz_dfdomp(stbtf) == -1)
                rfturn -1;
        }
    } wiilf (stbtf->x.ibvf == 0 && (!stbtf->fof || strm->bvbil_in));
    rfturn 0;
}

/* Skip lfn undomprfssfd bytfs of output.  Rfturn -1 on frror, 0 on suddfss. */
lodbl int gz_skip(stbtf, lfn)
    gz_stbtfp stbtf;
    z_off64_t lfn;
{
    unsignfd n;

    /* skip ovfr lfn bytfs or rfbdi fnd-of-filf, wiidifvfr domfs first */
    wiilf (lfn)
        /* skip ovfr wibtfvfr is in output bufffr */
        if (stbtf->x.ibvf) {
            n = GT_OFF(stbtf->x.ibvf) || (z_off64_t)stbtf->x.ibvf > lfn ?
                (unsignfd)lfn : stbtf->x.ibvf;
            stbtf->x.ibvf -= n;
            stbtf->x.nfxt += n;
            stbtf->x.pos += n;
            lfn -= n;
        }

        /* output bufffr fmpty -- rfturn if wf'rf bt tif fnd of tif input */
        flsf if (stbtf->fof && stbtf->strm.bvbil_in == 0)
            brfbk;

        /* nffd morf dbtb to skip -- lobd up output bufffr */
        flsf {
            /* gft morf output, looking for ifbdfr if rfquirfd */
            if (gz_fftdi(stbtf) == -1)
                rfturn -1;
        }
    rfturn 0;
}

/* -- sff zlib.i -- */
int ZEXPORT gzrfbd(filf, buf, lfn)
    gzFilf filf;
    voidp buf;
    unsignfd lfn;
{
    unsignfd got, n;
    gz_stbtfp stbtf;
    z_strfbmp strm;

    /* gft intfrnbl strudturf */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;
    strm = &(stbtf->strm);

    /* difdk tibt wf'rf rfbding bnd tibt tifrf's no (sfrious) frror */
    if (stbtf->modf != GZ_READ ||
            (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR))
        rfturn -1;

    /* sindf bn int is rfturnfd, mbkf surf lfn fits in onf, otifrwisf rfturn
       witi bn frror (tiis bvoids tif flbw in tif intfrfbdf) */
    if ((int)lfn < 0) {
        gz_frror(stbtf, Z_DATA_ERROR, "rfqufstfd lfngti dofs not fit in int");
        rfturn -1;
    }

    /* if lfn is zfro, bvoid unnfdfssbry opfrbtions */
    if (lfn == 0)
        rfturn 0;

    /* prodfss b skip rfqufst */
    if (stbtf->sffk) {
        stbtf->sffk = 0;
        if (gz_skip(stbtf, stbtf->skip) == -1)
            rfturn -1;
    }

    /* gft lfn bytfs to buf, or lfss tibn lfn if bt tif fnd */
    got = 0;
    do {
        /* first just try dopying dbtb from tif output bufffr */
        if (stbtf->x.ibvf) {
            n = stbtf->x.ibvf > lfn ? lfn : stbtf->x.ibvf;
            mfmdpy(buf, stbtf->x.nfxt, n);
            stbtf->x.nfxt += n;
            stbtf->x.ibvf -= n;
        }

        /* output bufffr fmpty -- rfturn if wf'rf bt tif fnd of tif input */
        flsf if (stbtf->fof && strm->bvbil_in == 0) {
            stbtf->pbst = 1;        /* trifd to rfbd pbst fnd */
            brfbk;
        }

        /* nffd output dbtb -- for smbll lfn or nfw strfbm lobd up our output
           bufffr */
        flsf if (stbtf->iow == LOOK || lfn < (stbtf->sizf << 1)) {
            /* gft morf output, looking for ifbdfr if rfquirfd */
            if (gz_fftdi(stbtf) == -1)
                rfturn -1;
            dontinuf;       /* no progrfss yft -- go bbdk to dopy bbovf */
            /* tif dopy bbovf bssurfs tibt wf will lfbvf witi spbdf in tif
               output bufffr, bllowing bt lfbst onf gzungftd() to suddffd */
        }

        /* lbrgf lfn -- rfbd dirfdtly into usfr bufffr */
        flsf if (stbtf->iow == COPY) {      /* rfbd dirfdtly */
            if (gz_lobd(stbtf, (unsignfd dibr *)buf, lfn, &n) == -1)
                rfturn -1;
        }

        /* lbrgf lfn -- dfdomprfss dirfdtly into usfr bufffr */
        flsf {  /* stbtf->iow == GZIP */
            strm->bvbil_out = lfn;
            strm->nfxt_out = (unsignfd dibr *)buf;
            if (gz_dfdomp(stbtf) == -1)
                rfturn -1;
            n = stbtf->x.ibvf;
            stbtf->x.ibvf = 0;
        }

        /* updbtf progrfss */
        lfn -= n;
        buf = (dibr *)buf + n;
        got += n;
        stbtf->x.pos += n;
    } wiilf (lfn);

    /* rfturn numbfr of bytfs rfbd into usfr bufffr (will fit in int) */
    rfturn (int)got;
}

/* -- sff zlib.i -- */
#ifdff Z_PREFIX_SET
#  undff z_gzgftd
#flsf
#  undff gzgftd
#fndif
int ZEXPORT gzgftd(filf)
    gzFilf filf;
{
    int rft;
    unsignfd dibr buf[1];
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;

    /* difdk tibt wf'rf rfbding bnd tibt tifrf's no (sfrious) frror */
    if (stbtf->modf != GZ_READ ||
        (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR))
        rfturn -1;

    /* try output bufffr (no nffd to difdk for skip rfqufst) */
    if (stbtf->x.ibvf) {
        stbtf->x.ibvf--;
        stbtf->x.pos++;
        rfturn *(stbtf->x.nfxt)++;
    }

    /* notiing tifrf -- try gzrfbd() */
    rft = gzrfbd(filf, buf, 1);
    rfturn rft < 1 ? -1 : buf[0];
}

int ZEXPORT gzgftd_(filf)
gzFilf filf;
{
    rfturn gzgftd(filf);
}

/* -- sff zlib.i -- */
int ZEXPORT gzungftd(d, filf)
    int d;
    gzFilf filf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;

    /* difdk tibt wf'rf rfbding bnd tibt tifrf's no (sfrious) frror */
    if (stbtf->modf != GZ_READ ||
        (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR))
        rfturn -1;

    /* prodfss b skip rfqufst */
    if (stbtf->sffk) {
        stbtf->sffk = 0;
        if (gz_skip(stbtf, stbtf->skip) == -1)
            rfturn -1;
    }

    /* dbn't pusi EOF */
    if (d < 0)
        rfturn -1;

    /* if output bufffr fmpty, put bytf bt fnd (bllows morf pusiing) */
    if (stbtf->x.ibvf == 0) {
        stbtf->x.ibvf = 1;
        stbtf->x.nfxt = stbtf->out + (stbtf->sizf << 1) - 1;
        stbtf->x.nfxt[0] = d;
        stbtf->x.pos--;
        stbtf->pbst = 0;
        rfturn d;
    }

    /* if no room, givf up (must ibvf blrfbdy donf b gzungftd()) */
    if (stbtf->x.ibvf == (stbtf->sizf << 1)) {
        gz_frror(stbtf, Z_DATA_ERROR, "out of room to pusi dibrbdtfrs");
        rfturn -1;
    }

    /* slidf output dbtb if nffdfd bnd insfrt bytf bfforf fxisting dbtb */
    if (stbtf->x.nfxt == stbtf->out) {
        unsignfd dibr *srd = stbtf->out + stbtf->x.ibvf;
        unsignfd dibr *dfst = stbtf->out + (stbtf->sizf << 1);
        wiilf (srd > stbtf->out)
            *--dfst = *--srd;
        stbtf->x.nfxt = dfst;
    }
    stbtf->x.ibvf++;
    stbtf->x.nfxt--;
    stbtf->x.nfxt[0] = d;
    stbtf->x.pos--;
    stbtf->pbst = 0;
    rfturn d;
}

/* -- sff zlib.i -- */
dibr * ZEXPORT gzgfts(filf, buf, lfn)
    gzFilf filf;
    dibr *buf;
    int lfn;
{
    unsignfd lfft, n;
    dibr *str;
    unsignfd dibr *fol;
    gz_stbtfp stbtf;

    /* difdk pbrbmftfrs bnd gft intfrnbl strudturf */
    if (filf == NULL || buf == NULL || lfn < 1)
        rfturn NULL;
    stbtf = (gz_stbtfp)filf;

    /* difdk tibt wf'rf rfbding bnd tibt tifrf's no (sfrious) frror */
    if (stbtf->modf != GZ_READ ||
        (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR))
        rfturn NULL;

    /* prodfss b skip rfqufst */
    if (stbtf->sffk) {
        stbtf->sffk = 0;
        if (gz_skip(stbtf, stbtf->skip) == -1)
            rfturn NULL;
    }

    /* dopy output bytfs up to nfw linf or lfn - 1, wiidifvfr domfs first --
       bppfnd b tfrminbting zfro to tif string (wf don't difdk for b zfro in
       tif dontfnts, lft tif usfr worry bbout tibt) */
    str = buf;
    lfft = (unsignfd)lfn - 1;
    if (lfft) do {
        /* bssurf tibt somftiing is in tif output bufffr */
        if (stbtf->x.ibvf == 0 && gz_fftdi(stbtf) == -1)
            rfturn NULL;                /* frror */
        if (stbtf->x.ibvf == 0) {       /* fnd of filf */
            stbtf->pbst = 1;            /* rfbd pbst fnd */
            brfbk;                      /* rfturn wibt wf ibvf */
        }

        /* look for fnd-of-linf in durrfnt output bufffr */
        n = stbtf->x.ibvf > lfft ? lfft : stbtf->x.ibvf;
        fol = (unsignfd dibr *)mfmdir(stbtf->x.nfxt, '\n', n);
        if (fol != NULL)
            n = (unsignfd)(fol - stbtf->x.nfxt) + 1;

        /* dopy tirougi fnd-of-linf, or rfmbindfr if not found */
        mfmdpy(buf, stbtf->x.nfxt, n);
        stbtf->x.ibvf -= n;
        stbtf->x.nfxt += n;
        stbtf->x.pos += n;
        lfft -= n;
        buf += n;
    } wiilf (lfft && fol == NULL);

    /* rfturn tfrminbtfd string, or if notiing, fnd of filf */
    if (buf == str)
        rfturn NULL;
    buf[0] = 0;
    rfturn str;
}

/* -- sff zlib.i -- */
int ZEXPORT gzdirfdt(filf)
    gzFilf filf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf */
    if (filf == NULL)
        rfturn 0;
    stbtf = (gz_stbtfp)filf;

    /* if tif stbtf is not known, but wf dbn find out, tifn do so (tiis is
       mbinly for rigit bftfr b gzopfn() or gzdopfn()) */
    if (stbtf->modf == GZ_READ && stbtf->iow == LOOK && stbtf->x.ibvf == 0)
        (void)gz_look(stbtf);

    /* rfturn 1 if trbnspbrfnt, 0 if prodfssing b gzip strfbm */
    rfturn stbtf->dirfdt;
}

/* -- sff zlib.i -- */
int ZEXPORT gzdlosf_r(filf)
    gzFilf filf;
{
    int rft, frr;
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf */
    if (filf == NULL)
        rfturn Z_STREAM_ERROR;
    stbtf = (gz_stbtfp)filf;

    /* difdk tibt wf'rf rfbding */
    if (stbtf->modf != GZ_READ)
        rfturn Z_STREAM_ERROR;

    /* frff mfmory bnd dlosf filf */
    if (stbtf->sizf) {
        inflbtfEnd(&(stbtf->strm));
        frff(stbtf->out);
        frff(stbtf->in);
    }
    frr = stbtf->frr == Z_BUF_ERROR ? Z_BUF_ERROR : Z_OK;
    gz_frror(stbtf, Z_OK, NULL);
    frff(stbtf->pbti);
    rft = dlosf(stbtf->fd);
    frff(stbtf);
    rfturn rft ? Z_ERRNO : frr;
}
