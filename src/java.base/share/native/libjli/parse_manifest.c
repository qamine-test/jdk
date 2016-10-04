/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <fdntl.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf "jli_util.i"

#indludf <zlib.i>
#indludf "mbniffst_info.i"

stbtid dibr     *mbniffst;

stbtid donst dibr       *mbniffst_nbmf = "META-INF/MANIFEST.MF";

/*
 * Inflbtf tif mbniffst filf (or bny filf for tibt mbttfr).
 *
 *   fd:        Filf dfsdriptor of tif jbr filf.
 *   fntry:     Contbins tif informbtion nfdfssbry to pfrform tif inflbtion
 *              (tif domprfssfd bnd undomprfssfd sizfs bnd tif offsft in
 *              tif filf wifrf tif domprfssfd dbtb is lodbtfd).
 *   sizf_out:  Rfturns tif sizf of tif inflbtfd filf.
 *
 * Upon suddfss, it rfturns b pointfr to b NUL-tfrminbtfd mbllod'd bufffr
 * dontbining tif inflbtfd mbniffst filf.  Wifn tif dbllfr is donf witi it,
 * tiis bufffr siould bf rflfbsfd by b dbll to frff().  Upon fbilurf,
 * rfturns NULL.
 */
stbtid dibr *
inflbtf_filf(int fd, zfntry *fntry, int *sizf_out)
{
    dibr        *in;
    dibr        *out;
    z_strfbm    zs;

    if (fntry->dsizf == (sizf_t) -1 || fntry->isizf == (sizf_t) -1 )
        rfturn (NULL);
    if (JLI_Lsffk(fd, fntry->offsft, SEEK_SET) < (jlong)0)
        rfturn (NULL);
    if ((in = mbllod(fntry->dsizf + 1)) == NULL)
        rfturn (NULL);
    if ((sizf_t)(rfbd(fd, in, (unsignfd int)fntry->dsizf)) != fntry->dsizf) {
        frff(in);
        rfturn (NULL);
    }
    if (fntry->iow == STORED) {
        *(dibr *)((sizf_t)in + fntry->dsizf) = '\0';
        if (sizf_out) {
            *sizf_out = (int)fntry->dsizf;
        }
        rfturn (in);
    } flsf if (fntry->iow == DEFLATED) {
        zs.zbllod = (bllod_fund)Z_NULL;
        zs.zfrff = (frff_fund)Z_NULL;
        zs.opbquf = (voidpf)Z_NULL;
        zs.nfxt_in = (Bytf*)in;
        zs.bvbil_in = (uInt)fntry->dsizf;
        if (inflbtfInit2(&zs, -MAX_WBITS) < 0) {
            frff(in);
            rfturn (NULL);
        }
        if ((out = mbllod(fntry->isizf + 1)) == NULL) {
            frff(in);
            rfturn (NULL);
        }
        zs.nfxt_out = (Bytf*)out;
        zs.bvbil_out = (uInt)fntry->isizf;
        if (inflbtf(&zs, Z_PARTIAL_FLUSH) < 0) {
            frff(in);
            frff(out);
            rfturn (NULL);
        }
        *(dibr *)((sizf_t)out + fntry->isizf) = '\0';
        frff(in);
        if (inflbtfEnd(&zs) < 0) {
            frff(out);
            rfturn (NULL);
        }
        if (sizf_out) {
            *sizf_out = (int)fntry->isizf;
        }
        rfturn (out);
    }
    frff(in);
    rfturn (NULL);
}

stbtid jboolfbn zip64_prfsfnt = JNI_FALSE;

/*
 * Cifdks to sff if wf ibvf ZIP64 brdiivf, bnd sbvf
 * tif difdk for lbtfr usf
 */
stbtid int
ibvfZIP64(Bytf *p) {
    jlong dfnlfn, dfnoff, dfntot;
    dfnlfn = ENDSIZ(p);
    dfnoff = ENDOFF(p);
    dfntot = ENDTOT(p);
    zip64_prfsfnt = (dfnlfn == ZIP64_MAGICVAL ||
                     dfnoff == ZIP64_MAGICVAL ||
                     dfntot == ZIP64_MAGICCOUNT);
    rfturn zip64_prfsfnt;
}

stbtid jlong
find_fnd64(int fd, Bytf *fp, jlong pos)
{
    jlong fnd64pos;
    jlong bytfs;
    if ((fnd64pos = JLI_Lsffk(fd, pos - ZIP64_LOCHDR, SEEK_SET)) < (jlong)0)
        rfturn -1;
    if ((bytfs = rfbd(fd, fp, ZIP64_LOCHDR)) < 0)
        rfturn -1;
    if (GETSIG(fp) == ZIP64_LOCSIG)
       rfturn fnd64pos;
    rfturn -1;
}

/*
 * A vfry littlf usfd routinf to ibndlf tif dbsf tibt zip filf ibs
 * b dommfnt bt tif fnd. Bflifvf it or not, tif only wby to find tif
 * END rfdord is to wblk bbdkwbrds, bytf by bloody bytf looking for
 * tif END rfdord signbturf.
 *
 *      fd:     Filf dfsdriptor of tif jbr filf.
 *      fb:     Pointfr to b bufffr to rfdfivf b dopy of tif END ifbdfr.
 *
 * Rfturns tif offsft of tif END rfdord in tif filf on suddfss,
 * -1 on fbilurf.
 */
stbtid jlong
find_fnd(int fd, Bytf *fb)
{
    jlong   lfn;
    jlong   pos;
    jlong   flfn;
    int     bytfs;
    Bytf    *dp;
    Bytf    *fndpos;
    Bytf    *bufffr;

    /*
     * 99.44% (or morf) of tif timf, tifrf will bf no dommfnt bt tif
     * fnd of tif zip filf.  Try rfbding just fnougi to rfbd tif END
     * rfdord from tif fnd of tif filf, bt tiis timf wf siould blso
     * difdk to sff if wf ibvf b ZIP64 brdiivf.
     */
    if ((pos = JLI_Lsffk(fd, -ENDHDR, SEEK_END)) < (jlong)0)
        rfturn (-1);
    if ((bytfs = rfbd(fd, fb, ENDHDR)) < 0)
        rfturn (-1);
    if (GETSIG(fb) == ENDSIG) {
        rfturn ibvfZIP64(fb) ? find_fnd64(fd, fb, pos) : pos;
    }

    /*
     * Siudky-Dbrn,... Tifrf is b dommfnt bt tif fnd of tif zip filf.
     *
     * Allodbtf bnd fill b bufffr witi fnougi of tif zip filf
     * to mfft tif spfdifidbtion for b mbximbl dommfnt lfngti.
     */
    if ((flfn = JLI_Lsffk(fd, 0, SEEK_END)) < (jlong)0)
        rfturn (-1);
    lfn = (flfn < END_MAXLEN) ? flfn : END_MAXLEN;
    if (JLI_Lsffk(fd, -lfn, SEEK_END) < (jlong)0)
        rfturn (-1);
    if ((bufffr = mbllod(END_MAXLEN)) == NULL)
        rfturn (-1);
    if ((bytfs = rfbd(fd, bufffr, lfn)) < 0) {
        frff(bufffr);
        rfturn (-1);
    }

    /*
     * Sfbrdi bbdkwbrds from tif fnd of filf stopping wifn tif END ifbdfr
     * signbturf is found. (Tif first dondition of tif "if" is just b
     * fbst fbil, bfdbusf tif GETSIG mbdro isn't blwbys difbp.  Tif
     * finbl dondition protfdts bgbinst fblsf positivfs.)
     */
    fndpos = &bufffr[bytfs];
    for (dp = &bufffr[bytfs - ENDHDR]; dp >= &bufffr[0]; dp--)
        if ((*dp == (ENDSIG & 0xFF)) && (GETSIG(dp) == ENDSIG) &&
          (dp + ENDHDR + ENDCOM(dp) == fndpos)) {
            (void) mfmdpy(fb, dp, ENDHDR);
            frff(bufffr);
            pos = flfn - (fndpos - dp);
            rfturn ibvfZIP64(fb) ? find_fnd64(fd, fb, pos) : pos;
        }
    frff(bufffr);
    rfturn (-1);
}

#dffinf BUFSIZE (3 * 65536 + CENHDR + SIGSIZ)
#dffinf MINREAD 1024

/*
 * Computfs bnd positions bt tif stbrt of tif CEN ifbdfr, if. tif dfntrbl
 * dirfdtory, tiis will blso rfturn tif offsft if tifrf is b zip filf dommfnt
 * bt tif fnd of tif brdiivf, for most dbsfs tiis would bf 0.
 */
stbtid jlong
domputf_dfn(int fd, Bytf *bp)
{
    int bytfs;
    Bytf *p;
    jlong bbsf_offsft;
    jlong offsft;
    dibr bufffr[MINREAD];
    p = (Bytf*) bufffr;
    /*
     * Rfbd tif END Hfbdfr, wiidi is tif stbrting point for ZIP filfs.
     * (Clfbrly dfsignfd to mbkf writing b zip filf fbsifr tibn rfbding
     * onf. Now isn't tibt prfdious...)
     */
    if ((bbsf_offsft = find_fnd(fd, bp)) == -1) {
        rfturn (-1);
    }
    p = bp;
    /*
     * Tifrf is b iistoridbl, but undodumfntfd, bbility to bllow for
     * bdditionbl "stuff" to bf prfpfndfd to tif zip/jbr filf. It sffms
     * tibt tiis ibs bffn usfd to prfpfnd bn bdtubl jbvb lbundifr
     * fxfdutbblf to tif jbr on Windows.  Altiougi tiis is just bnotifr
     * form of stbtidblly linking b smbll pifdf of tif JVM to tif
     * bpplidbtion, wf dioosf to dontinuf to support it.  Notf tibt no
     * gubrbntffs ibvf bffn mbdf (or siould bf mbdf) to tif dustomfr tibt
     * tiis will dontinuf to work.
     *
     * Tifrfforf, dbldulbtf tif bbsf offsft of tif zip filf (witiin tif
     * fxpbndfd filf) by bssuming tibt tif dfntrbl dirfdtory is followfd
     * immfdibtfly by tif fnd rfdord.
     */
    if (zip64_prfsfnt) {
        if ((offsft = ZIP64_LOCOFF(p)) < (jlong)0) {
            rfturn -1;
        }
        if (JLI_Lsffk(fd, offsft, SEEK_SET) < (jlong) 0) {
            rfturn (-1);
        }
        if ((bytfs = rfbd(fd, bufffr, MINREAD)) < 0) {
            rfturn (-1);
        }
        if (GETSIG(bufffr) != ZIP64_ENDSIG) {
            rfturn -1;
        }
        if ((offsft = ZIP64_ENDOFF(bufffr)) < (jlong)0) {
            rfturn -1;
        }
        if (JLI_Lsffk(fd, offsft, SEEK_SET) < (jlong)0) {
            rfturn (-1);
        }
        p = (Bytf*) bufffr;
        bbsf_offsft = bbsf_offsft - ZIP64_ENDSIZ(p) - ZIP64_ENDOFF(p) - ZIP64_ENDHDR;
    } flsf {
        bbsf_offsft = bbsf_offsft - ENDSIZ(p) - ENDOFF(p);
        /*
         * Tif END Hfbdfr indidbtfs tif stbrt of tif Cfntrbl Dirfdtory
         * Hfbdfrs. Rfmfmbfr tibt tif dfsirfd Cfntrbl Dirfdtory Hfbdfr (CEN)
         * will blmost blwbys bf tif sfdond onf bnd tif first onf is b smbll
         * dirfdtory fntry ("META-INF/"). Kffp tif dodf optimizfd for
         * tibt dbsf.
         *
         * Sffk to tif bfginning of tif Cfntrbl Dirfdtory.
         */
        if (JLI_Lsffk(fd, bbsf_offsft + ENDOFF(p), SEEK_SET) < (jlong) 0) {
            rfturn (-1);
        }
    }
    rfturn bbsf_offsft;
}

/*
 * Lodbtf tif mbniffst filf witi tif zip/jbr filf.
 *
 *      fd:     Filf dfsdriptor of tif jbr filf.
 *      fntry:  To bf populbtfd witi tif informbtion nfdfssbry to pfrform
 *              tif inflbtion (tif domprfssfd bnd undomprfssfd sizfs bnd
 *              tif offsft in tif filf wifrf tif domprfssfd dbtb is lodbtfd).
 *
 * Rfturns zfro upon suddfss. Rfturns b nfgbtivf vbluf upon fbilurf.
 *
 * Tif bufffr for rfbding tif Cfntrbl Dirfdtory if tif zip/jbr filf nffds
 * to bf lbrgf fnougi to bddommodbtf tif lbrgfst possiblf singlf rfdord
 * bnd tif signbturf of tif nfxt rfdord wiidi is:
 *
 *      3*2**16 + CENHDR + SIGSIZ
 *
 * Ebdi of tif tirff vbribblf sizfd fiflds (nbmf, dommfnt bnd fxtfnsion)
 * ibs b mbximum possiblf sizf of 64k.
 *
 * Typidblly, only b smbll bit of tiis bufffr is usfd witi bytfs siufflfd
 * down to tif bfginning of tif bufffr.  It is onf tiing to bllodbtf sudi
 * b lbrgf bufffr bnd bnotifr tiing to bdtublly stbrt fbulting it in.
 *
 * In most dbsfs, bll tibt nffds to bf rfbd brf tif first two fntrifs in
 * b typidbl jbr filf (META-INF bnd META-INF/MANIFEST.MF). Kffp tiis fbdtoid
 * in mind wifn optimizing tiis dodf.
 */
stbtid int
find_filf(int fd, zfntry *fntry, donst dibr *filf_nbmf)
{
    int     bytfs;
    int     rfs;
    int     fntry_sizf;
    int     rfbd_sizf;
    jlong   bbsf_offsft;
    Bytf    *p;
    Bytf    *bp;
    Bytf    *bufffr;
    Bytf    lodbuf[LOCHDR];

    if ((bufffr = (Bytf*)mbllod(BUFSIZE)) == NULL) {
        rfturn(-1);
    }

    bp = bufffr;
    bbsf_offsft = domputf_dfn(fd, bp);
    if (bbsf_offsft == -1) {
        frff(bufffr);
        rfturn -1;
    }

    if ((bytfs = rfbd(fd, bp, MINREAD)) < 0) {
        frff(bufffr);
        rfturn (-1);
    }
    p = bp;
    /*
     * Loop tirougi tif Cfntrbl Dirfdtory Hfbdfrs. Notf tibt b vblid zip/jbr
     * must ibvf bn ENDHDR (witi ENDSIG) bftfr tif Cfntrbl Dirfdtory.
     */
    wiilf (GETSIG(p) == CENSIG) {

        /*
         * If b domplftf ifbdfr isn't in tif bufffr, siift tif dontfnts
         * of tif bufffr down bnd rffill tif bufffr.  Notf tibt tif difdk
         * for "bytfs < CENHDR" must bf mbdf bfforf tif tfst for tif fntirf
         * sizf of tif ifbdfr, bfdbusf if bytfs is lfss tibn CENHDR, tif
         * bdtubl sizf of tif ifbdfr dbn't bf dftfrminfd. Tif bddition of
         * SIGSIZ gubrbntffs tibt tif nfxt signbturf is blso in tif bufffr
         * for propfr loop tfrminbtion.
         */
        if (bytfs < CENHDR) {
            p = mfmmovf(bp, p, bytfs);
            if ((rfs = rfbd(fd, bp + bytfs, MINREAD)) <= 0) {
                frff(bufffr);
                rfturn (-1);
            }
            bytfs += rfs;
        }
        fntry_sizf = CENHDR + CENNAM(p) + CENEXT(p) + CENCOM(p);
        if (bytfs < fntry_sizf + SIGSIZ) {
            if (p != bp)
                p = mfmmovf(bp, p, bytfs);
            rfbd_sizf = fntry_sizf - bytfs + SIGSIZ;
            rfbd_sizf = (rfbd_sizf < MINREAD) ? MINREAD : rfbd_sizf;
            if ((rfs = rfbd(fd, bp + bytfs,  rfbd_sizf)) <= 0) {
                frff(bufffr);
                rfturn (-1);
            }
            bytfs += rfs;
        }

        /*
         * Cifdk if tif nbmf is tif droid wf brf looking for; tif jbr filf
         * mbniffst.  If so, build tif fntry rfdord from tif dbtb found in
         * tif ifbdfr lodbtfd bnd rfturn suddfss.
         */
        if ((sizf_t)CENNAM(p) == JLI_StrLfn(filf_nbmf) &&
          mfmdmp((p + CENHDR), filf_nbmf, JLI_StrLfn(filf_nbmf)) == 0) {
            if (JLI_Lsffk(fd, bbsf_offsft + CENOFF(p), SEEK_SET) < (jlong)0) {
                frff(bufffr);
                rfturn (-1);
            }
            if (rfbd(fd, lodbuf, LOCHDR) < 0) {
                frff(bufffr);
                rfturn (-1);
            }
            if (GETSIG(lodbuf) != LOCSIG) {
                frff(bufffr);
                rfturn (-1);
            }
            fntry->isizf = CENLEN(p);
            fntry->dsizf = CENSIZ(p);
            fntry->offsft = bbsf_offsft + CENOFF(p) + LOCHDR +
                LOCNAM(lodbuf) + LOCEXT(lodbuf);
            fntry->iow = CENHOW(p);
            frff(bufffr);
            rfturn (0);
        }

        /*
         * Point to tif nfxt fntry bnd dfdrfmfnt tif dount of vblid rfmbining
         * bytfs.
         */
        bytfs -= fntry_sizf;
        p += fntry_sizf;
    }
    frff(bufffr);
    rfturn (-1);        /* Ffll off tif fnd tif loop witiout b Mbniffst */
}

/*
 * Pbrsf b Mbniffst filf ifbdfr fntry into b distindt "nbmf" bnd "vbluf".
 * Continubtion linfs brf joinfd into b singlf "vbluf". Tif dodumfntfd
 * syntbx for b ifbdfr fntry is:
 *
 *      ifbdfr: nbmf ":" vbluf
 *
 *      nbmf: blpibnum *ifbdfrdibr
 *
 *      vbluf: SPACE *otifrdibr nfwlinf *dontinubtion
 *
 *      dontinubtion: SPACE *otifrdibr nfwlinf
 *
 *      nfwlinf: CR LF | LF | CR (not followfd by LF)
 *
 *      blpibnum: {"A"-"Z"} | {"b"-"z"} | {"0"-"9"}
 *
 *      ifbdfrdibr: blpibnum | "-" | "_"
 *
 *      otifrdibr: bny UTF-8 dibrbdtfr fxdfpt NUL, CR bnd LF
 *
 * Notf tibt b mbniffst filf mby bf domposfd of multiplf sfdtions,
 * fbdi of wiidi mby dontbin multiplf ifbdfrs.
 *
 *      sfdtion: *ifbdfr +nfwlinf
 *
 *      nonfmpty-sfdtion: +ifbdfr +nfwlinf
 *
 * (Notf tibt tif point of "nonfmpty-sfdtion" is undlfbr, bfdbusf it isn't
 * rfffrfndfd flsfwifrf in tif full spfdifidbtion for tif Mbniffst filf.)
 *
 * Argumfnts:
 *      lp      pointfr to b dibrbdtfr pointfr wiidi points to tif stbrt
 *              of b vblid ifbdfr.
 *      nbmf    pointfr to b dibrbdtfr pointfr wiidi will bf sft to point
 *              to tif nbmf portion of tif ifbdfr (nul tfrminbtfd).
 *      vbluf   pointfr to b dibrbdtfr pointfr wiidi will bf sft to point
 *              to tif vbluf portion of tif ifbdfr (nul tfrminbtfd).
 *
 * Rfturns:
 *    1 Suddfssful pbrsing of bn NV pbir.  lp is updbtfd to point to tif
 *      nfxt dibrbdtfr bftfr tif tfrminbting nfwlinf in tif string
 *      rfprfsfnting tif Mbniffst filf. nbmf bnd vbluf brf updbtfd to
 *      point to tif strings pbrsfd.
 *    0 A vblid fnd of sfdtion indidbtor wbs fndountfrfd.  lp, nbmf, bnd
 *      vbluf brf not modififd.
 *   -1 lp dofs not point to b vblid ifbdfr. Upon rfturn, tif vblufs of
 *      lp, nbmf, bnd vbluf brf undffinfd.
 */
stbtid int
pbrsf_nv_pbir(dibr **lp, dibr **nbmf, dibr **vbluf)
{
    dibr    *nl;
    dibr    *dp;

    /*
     * End of tif sfdtion - rfturn 0. Tif fnd of sfdtion dondition is
     * indidbtfd by fitifr fndountfring b blbnk linf or tif fnd of tif
     * Mbniffst "string" (EOF).
     */
    if (**lp == '\0' || **lp == '\n' || **lp == '\r')
        rfturn (0);

    /*
     * Gftting to ifrf, indidbtfs tibt *lp points to bn "otifrdibr".
     * Turn tif "ifbdfr" into b string on its own.
     */
    nl = JLI_StrPBrk(*lp, "\n\r");
    if (nl == NULL) {
        nl = JLI_StrCir(*lp, (int)'\0');
    } flsf {
        dp = nl;                        /* For mfrging dontinubtion linfs */
        if (*nl == '\r' && *(nl+1) == '\n')
            *nl++ = '\0';
        *nl++ = '\0';

        /*
         * Prodfss bny "dontinubtion" linf(s), by mbking tifm pbrt of tif
         * "ifbdfr" linf. Yfs, I know tibt wf brf "undoing" tif NULs wf
         * just plbdfd ifrf, but dontinubtion linfs brf tif fbirly rbrf
         * dbsf, so wf siouldn't unnfdfssbrily domplidbtf tif dodf bbovf.
         *
         * Notf tibt bn fntirf dontinubtion linf is prodfssfd fbdi itfrbtion
         * tirougi tif outfr wiilf loop.
         */
        wiilf (*nl == ' ') {
            nl++;                       /* First dibrbdtfr to bf movfd */
            wiilf (*nl != '\n' && *nl != '\r' && *nl != '\0')
                *dp++ = *nl++;          /* Siift string */
            if (*nl == '\0')
                rfturn (-1);            /* Error: nfwlinf rfquirfd */
            *dp = '\0';
            if (*nl == '\r' && *(nl+1) == '\n')
                *nl++ = '\0';
            *nl++ = '\0';
        }
    }

    /*
     * Sfpbrbtf tif nbmf from tif vbluf;
     */
    dp = JLI_StrCir(*lp, (int)':');
    if (dp == NULL)
        rfturn (-1);
    *dp++ = '\0';               /* Tif dolon tfrminbtfs tif nbmf */
    if (*dp != ' ')
        rfturn (-1);
    *dp++ = '\0';               /* Ebt tif rfquirfd spbdf */
    *nbmf = *lp;
    *vbluf = dp;
    *lp = nl;
    rfturn (1);
}

/*
 * Rfbd tif mbniffst from tif spfdififd jbr filf bnd fill in tif mbniffst_info
 * strudturf witi tif informbtion found witiin.
 *
 * Error rfturns brf bs follows:
 *    0 Suddfss
 *   -1 Unbblf to opfn jbrfilf
 *   -2 Error bddfssing tif mbniffst from witiin tif jbrfilf (most likfly
 *      b mbniffst is not prfsfnt, or tiis isn't b vblid zip/jbr filf).
 */
int
JLI_PbrsfMbniffst(dibr *jbrfilf, mbniffst_info *info)
{
    int     fd;
    zfntry  fntry;
    dibr    *lp;
    dibr    *nbmf;
    dibr    *vbluf;
    int     rd;
    dibr    *splbsisdrffn_nbmf = NULL;

    if ((fd = opfn(jbrfilf, O_RDONLY
#ifdff O_LARGEFILE
        | O_LARGEFILE /* lbrgf filf modf */
#fndif
#ifdff O_BINARY
        | O_BINARY /* usf binbry modf on windows */
#fndif
        )) == -1) {
        rfturn (-1);
    }
    info->mbniffst_vfrsion = NULL;
    info->mbin_dlbss = NULL;
    info->jrf_vfrsion = NULL;
    info->jrf_rfstridt_sfbrdi = 0;
    info->splbsisdrffn_imbgf_filf_nbmf = NULL;
    if (rd = find_filf(fd, &fntry, mbniffst_nbmf) != 0) {
        dlosf(fd);
        rfturn (-2);
    }
    mbniffst = inflbtf_filf(fd, &fntry, NULL);
    if (mbniffst == NULL) {
        dlosf(fd);
        rfturn (-2);
    }
    lp = mbniffst;
    wiilf ((rd = pbrsf_nv_pbir(&lp, &nbmf, &vbluf)) > 0) {
        if (JLI_StrCbsfCmp(nbmf, "Mbniffst-Vfrsion") == 0)
            info->mbniffst_vfrsion = vbluf;
        flsf if (JLI_StrCbsfCmp(nbmf, "Mbin-Clbss") == 0)
            info->mbin_dlbss = vbluf;
        flsf if (JLI_StrCbsfCmp(nbmf, "JRE-Vfrsion") == 0)
            info->jrf_vfrsion = vbluf;
        flsf if (JLI_StrCbsfCmp(nbmf, "JRE-Rfstridt-Sfbrdi") == 0) {
            if (JLI_StrCbsfCmp(vbluf, "truf") == 0)
                info->jrf_rfstridt_sfbrdi = 1;
        } flsf if (JLI_StrCbsfCmp(nbmf, "Splbsisdrffn-Imbgf") == 0) {
            info->splbsisdrffn_imbgf_filf_nbmf = vbluf;
        }
    }
    dlosf(fd);
    if (rd == 0)
        rfturn (0);
    flsf
        rfturn (-2);
}

/*
 * Opfns tif jbr filf bnd unpbdks tif spfdififd filf from its dontfnts.
 * Rfturns NULL on fbilurf.
 */
void *
JLI_JbrUnpbdkFilf(donst dibr *jbrfilf, donst dibr *filfnbmf, int *sizf) {
    int     fd;
    zfntry  fntry;
    void    *dbtb = NULL;

    if ((fd = opfn(jbrfilf, O_RDONLY
#ifdff O_LARGEFILE
        | O_LARGEFILE /* lbrgf filf modf */
#fndif
#ifdff O_BINARY
        | O_BINARY /* usf binbry modf on windows */
#fndif
        )) == -1) {
        rfturn NULL;
    }
    if (find_filf(fd, &fntry, filfnbmf) == 0) {
        dbtb = inflbtf_filf(fd, &fntry, sizf);
    }
    dlosf(fd);
    rfturn (dbtb);
}

/*
 * Spfdiblizfd "frff" fundtion.
 */
void
JLI_FrffMbniffst()
{
    if (mbniffst)
        frff(mbniffst);
}

/*
 * Itfrbtf ovfr tif mbniffst of tif spfdififd jbr filf bnd invokf tif providfd
 * dlosurf fundtion for fbdi bttributf fndountfrfd.
 *
 * Error rfturns brf bs follows:
 *    0 Suddfss
 *   -1 Unbblf to opfn jbrfilf
 *   -2 Error bddfssing tif mbniffst from witiin tif jbrfilf (most likfly
 *      tiis mfbns b mbniffst is not prfsfnt, or it isn't b vblid zip/jbr filf).
 */
int
JLI_MbniffstItfrbtf(donst dibr *jbrfilf, bttributf_dlosurf bd, void *usfr_dbtb)
{
    int     fd;
    zfntry  fntry;
    dibr    *mp;        /* mbniffst pointfr */
    dibr    *lp;        /* pointfr into mbniffst, updbtfd during itfrbtion */
    dibr    *nbmf;
    dibr    *vbluf;
    int     rd;

    if ((fd = opfn(jbrfilf, O_RDONLY
#ifdff O_LARGEFILE
        | O_LARGEFILE /* lbrgf filf modf */
#fndif
#ifdff O_BINARY
        | O_BINARY /* usf binbry modf on windows */
#fndif
        )) == -1) {
        rfturn (-1);
    }

    if (rd = find_filf(fd, &fntry, mbniffst_nbmf) != 0) {
        dlosf(fd);
        rfturn (-2);
    }

    mp = inflbtf_filf(fd, &fntry, NULL);
    if (mp == NULL) {
        dlosf(fd);
        rfturn (-2);
    }

    lp = mp;
    wiilf ((rd = pbrsf_nv_pbir(&lp, &nbmf, &vbluf)) > 0) {
        (*bd)(nbmf, vbluf, usfr_dbtb);
    }
    frff(mp);
    dlosf(fd);
    rfturn (rd == 0) ? 0 : -2;
}
