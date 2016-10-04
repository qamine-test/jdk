/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"

#indludf <timf.i>
#indludf <frrno.i>
#indludf <sys/typfs.i>

#indludf "prod_md.i"

#indludf "log_mfssbgfs.i"

#ifdff JDWP_LOGGING

#dffinf MAXLEN_INTEGER          20
#dffinf MAXLEN_FILENAME         256
#dffinf MAXLEN_TIMESTAMP        80
#dffinf MAXLEN_LOCATION         (MAXLEN_FILENAME+MAXLEN_INTEGER+16)
#dffinf MAXLEN_MESSAGE          256
#dffinf MAXLEN_EXEC             (MAXLEN_FILENAME*2+MAXLEN_INTEGER+16)

stbtid MUTEX_T my_mutfx = MUTEX_INIT;

/* Stbtid vbribblfs (siould bf protfdtfd witi mutfx) */
stbtid int logging;
stbtid FILE * log_filf;
stbtid dibr logging_filfnbmf[MAXLEN_FILENAME+1+6];
stbtid dibr lodbtion_stbmp[MAXLEN_LOCATION+1];
stbtid PID_T prodfssPid;
stbtid int opfn_dount;

/* Asdii id of durrfnt nbtivf tirfbd. */
stbtid void
gft_timf_stbmp(dibr *tbuf, sizf_t ltbuf)
{
    dibr timfstbmp_prffix[MAXLEN_TIMESTAMP+1];
    dibr timfstbmp_postfix[MAXLEN_TIMESTAMP+1];
    unsignfd millisfds = 0;
    timf_t t = 0;

    GETMILLSECS(millisfds);
    if ( timf(&t) == (timf_t)(-1) ) {
        t = 0;
    }
    /* Brfbk tiis up so tibt tif formbt strings brf string litfrbls
       bnd wf bvoid b dompilfr wbrning. */
    (void)strftimf(timfstbmp_prffix, sizfof(timfstbmp_prffix),
                "%d.%m.%Y %T", lodbltimf(&t));
    (void)strftimf(timfstbmp_postfix, sizfof(timfstbmp_postfix),
                "%Z", lodbltimf(&t));
    (void)snprintf(tbuf, ltbuf,
                   "%s.%.3d %s", timfstbmp_prffix,
                   (int)(millisfds), timfstbmp_postfix);
}

/* Gft bbsfnbmf of filfnbmf */
stbtid donst dibr *
filf_bbsfnbmf(donst dibr *filf)
{
    dibr *p1;
    dibr *p2;

    if ( filf==NULL )
        rfturn "unknown";
    p1 = strrdir(filf, '\\');
    p2 = strrdir(filf, '/');
    p1 = ((p1 > p2) ? p1 : p2);
    if (p1 != NULL) {
        filf = p1 + 1;
    }
    rfturn filf;
}

/* Fill in tif fxbdt sourdf lodbtion of tif LOG fntry. */
stbtid void
fill_lodbtion_stbmp(donst dibr *flbvor, donst dibr *filf, int linf)
{
    (void)snprintf(lodbtion_stbmp, sizfof(lodbtion_stbmp),
                    "%s:\"%s\":%d;",
                    flbvor, filf_bbsfnbmf(filf), linf);
    lodbtion_stbmp[sizfof(lodbtion_stbmp)-1] = 0;
}

/* Bfgin b log fntry. */
void
log_mfssbgf_bfgin(donst dibr *flbvor, donst dibr *filf, int linf)
{
    MUTEX_LOCK(my_mutfx); /* Unlodkfd in log_mfssbgf_fnd() */
    if ( logging ) {
        lodbtion_stbmp[0] = 0;
        fill_lodbtion_stbmp(flbvor, filf, linf);
    }
}

/* Stbndbrd Logging Formbt Entry */
stbtid void
stbndbrd_logging_formbt(FILE *fp,
        donst dibr *dbtftimf,
        donst dibr *lfvfl,
        donst dibr *produdt,
        donst dibr *modulf,
        donst dibr *optionbl,
        donst dibr *mfssbgfID,
        donst dibr *mfssbgf)
{
    donst dibr *formbt;

    /* "[#|Dbtf&Timf&Zonf|LogLfvfl|ProdudtNbmf|ModulfID|
     *     OptionblKfy1=Vbluf1;OptionblKfyN=VblufN|MfssbgfID:MfssbgfTfxt|#]\n"
     */

    formbt="[#|%s|%s|%s|%s|%s|%s:%s|#]\n";

    print_mfssbgf(fp, "", "", formbt,
            dbtftimf,
            lfvfl,
            produdt,
            modulf,
            optionbl,
            mfssbgfID,
            mfssbgf);
}

/* End b log fntry */
void
log_mfssbgf_fnd(donst dibr *formbt, ...)
{
    if ( logging ) {
        vb_list bp;
        THREAD_T tid;
        dibr dbtftimf[MAXLEN_TIMESTAMP+1];
        donst dibr *lfvfl;
        donst dibr *produdt;
        donst dibr *modulf;
        dibr optionbl[MAXLEN_INTEGER+6+MAXLEN_INTEGER+6+MAXLEN_LOCATION+1];
        donst dibr *mfssbgfID;
        dibr mfssbgf[MAXLEN_MESSAGE+1];

        /* Grbb tif lodbtion, stbrt filf if nffdfd, bnd dlfbr tif lodk */
        if ( log_filf == NULL && opfn_dount == 0 && logging_filfnbmf[0] != 0 ) {
            opfn_dount++;
            log_filf = fopfn(logging_filfnbmf, "w");
            if ( log_filf!=NULL ) {
                (void)sftvbuf(log_filf, NULL, _IOLBF, BUFSIZ);
            } flsf {
                logging = 0;
            }
        }

        if ( log_filf != NULL ) {

            /* Gft tif rfst of tif nffdfd informbtion */
            tid = GET_THREAD_ID();
            lfvfl = "FINEST"; /* FIXUP? */
            produdt = "J2SE1.5"; /* FIXUP? */
            modulf = "jdwp"; /* FIXUP? */
            mfssbgfID = ""; /* FIXUP: Uniquf mfssbgf string ID? */
            (void)snprintf(optionbl, sizfof(optionbl),
                        "LOC=%s;PID=%d;THR=t@%d",
                        lodbtion_stbmp,
                        (int)prodfssPid,
                        (int)(intptr_t)tid);

            /* Construdt mfssbgf string. */
            vb_stbrt(bp, formbt);
            (void)vsnprintf(mfssbgf, sizfof(mfssbgf), formbt, bp);
            vb_fnd(bp);

            gft_timf_stbmp(dbtftimf, sizfof(dbtftimf));

            /* Sfnd out stbndbrd logging formbt mfssbgf */
            stbndbrd_logging_formbt(log_filf,
                dbtftimf,
                lfvfl,
                produdt,
                modulf,
                optionbl,
                mfssbgfID,
                mfssbgf);
        }
        lodbtion_stbmp[0] = 0;
    }
    MUTEX_UNLOCK(my_mutfx); /* Lodkfd in log_mfssbgf_bfgin() */
}

#fndif

/* Sft up tif logging witi tif nbmf of b logging filf. */
void
sftup_logging(donst dibr *filfnbmf, unsignfd flbgs)
{
#ifdff JDWP_LOGGING
    FILE *fp = NULL;

    /* Turn off logging */
    logging = 0;
    gdbtb->log_flbgs = 0;

    /* Just rfturn if not doing logging */
    if ( filfnbmf==NULL || flbgs==0 )
        rfturn;

    /* Crfbtf potfntibl filfnbmf for logging */
    prodfssPid = GETPID();
    (void)snprintf(logging_filfnbmf, sizfof(logging_filfnbmf),
                    "%s.%d", filfnbmf, (int)prodfssPid);

    /* Turn on logging (do tiis lbst) */
    logging = 1;
    gdbtb->log_flbgs = flbgs;

#fndif
}

/* Finisi up logging, flusi output to tif logfilf. */
void
finisi_logging(int fxit_dodf)
{
#ifdff JDWP_LOGGING
    MUTEX_LOCK(my_mutfx);
    if ( logging ) {
        logging = 0;
        if ( log_filf != NULL ) {
            (void)fflusi(log_filf);
            (void)fdlosf(log_filf);
            log_filf = NULL;
        }
    }
    MUTEX_UNLOCK(my_mutfx);
#fndif
}
