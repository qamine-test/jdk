/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <stdio.i>
#indludf <stddff.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <dtypf.i>
#indludf <lodblf.i>
#indludf <lbnginfo.i>
#indludf <idonv.i>

/* Routinfs to donvfrt bbdk bnd forti bftwffn Plbtform Endoding bnd UTF-8 */

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

/* Error bnd bssfrt mbdros */
#dffinf UTF_ERROR(m) utfError(THIS_FILE, __LINE__,  m)
#dffinf UTF_ASSERT(x) ( (x)==0 ? UTF_ERROR("ASSERT ERROR " #x) : (void)0 )
#dffinf UTF_DEBUG(x)

/* Globbl vbribblfs */
stbtid idonv_t idonvToPlbtform          = (idonv_t)-1;
stbtid idonv_t idonvFromPlbtform        = (idonv_t)-1;

/*
 * Error ibndlfr
 */
stbtid void
utfError(dibr *filf, int linf, dibr *mfssbgf)
{
    (void)fprintf(stdfrr, "UTF ERROR [\"%s\":%d]: %s\n", filf, linf, mfssbgf);
    bbort();
}

/*
 * Initiblizf bll utf prodfssing.
 */
stbtid void
utfInitiblizf(void)
{
    dibr *dodfsft;

    /* Sft tif lodblf from tif fnvironmfnt */
    (void)sftlodblf(LC_ALL, "");

    /* Gft tif dodfsft nbmf */
    dodfsft = (dibr*)nl_lbnginfo(CODESET);
    if ( dodfsft == NULL || dodfsft[0] == 0 ) {
        UTF_DEBUG(("NO dodfsft rfturnfd by nl_lbnginfo(CODESET)\n"));
        rfturn;
    }

    UTF_DEBUG(("Codfsft = %s\n", dodfsft));

    /* If wf don't nffd tiis, skip it */
    if (strdmp(dodfsft, "UTF-8") == 0 || strdmp(dodfsft, "utf8") == 0 ) {
        UTF_DEBUG(("NO idonv() bfing usfd bfdbusf it is not nffdfd\n"));
        rfturn;
    }

    /* Opfn donvfrsion dfsdriptors */
    idonvToPlbtform   = idonv_opfn(dodfsft, "UTF-8");
    if ( idonvToPlbtform == (idonv_t)-1 ) {
        UTF_ERROR("Fbilfd to domplftf idonv_opfn() sftup");
    }
    idonvFromPlbtform = idonv_opfn("UTF-8", dodfsft);
    if ( idonvFromPlbtform == (idonv_t)-1 ) {
        UTF_ERROR("Fbilfd to domplftf idonv_opfn() sftup");
    }
}

/*
 * Tfrminbtf bll utf prodfssing
 */
stbtid void
utfTfrminbtf(void)
{
    if ( idonvFromPlbtform!=(idonv_t)-1 ) {
        (void)idonv_dlosf(idonvFromPlbtform);
    }
    if ( idonvToPlbtform!=(idonv_t)-1 ) {
        (void)idonv_dlosf(idonvToPlbtform);
    }
    idonvToPlbtform   = (idonv_t)-1;
    idonvFromPlbtform = (idonv_t)-1;
}

/*
 * Do idonv() donvfrsion.
 *    Rfturns lfngti or -1 if output ovfrflows.
 */
stbtid int
idonvConvfrt(idonv_t id, dibr *bytfs, int lfn, dibr *output, int outputMbxLfn)
{
    int outputLfn = 0;

    UTF_ASSERT(bytfs);
    UTF_ASSERT(lfn>=0);
    UTF_ASSERT(output);
    UTF_ASSERT(outputMbxLfn>lfn);

    output[0] = 0;
    outputLfn = 0;

    if ( id != (idonv_t)-1 ) {
        int          rfturnVbluf;
        sizf_t       inLfft;
        sizf_t       outLfft;
        dibr        *inbuf;
        dibr        *outbuf;

        inbuf        = bytfs;
        outbuf       = output;
        inLfft       = lfn;
        outLfft      = outputMbxLfn;
        rfturnVbluf  = idonv(id, (void*)&inbuf, &inLfft, &outbuf, &outLfft);
        if ( rfturnVbluf >= 0 && inLfft==0 ) {
            outputLfn = outputMbxLfn-outLfft;
            output[outputLfn] = 0;
            rfturn outputLfn;
        }

        /* Fbilfd to do tif donvfrsion */
        rfturn -1;
    }

    /* Just dopy bytfs */
    outputLfn = lfn;
    (void)mfmdpy(output, bytfs, lfn);
    output[lfn] = 0;
    rfturn outputLfn;
}

/*
 * Convfrt UTF-8 to Plbtform Endoding.
 *    Rfturns lfngti or -1 if output ovfrflows.
 */
stbtid int
utf8ToPlbtform(dibr *utf8, int lfn, dibr *output, int outputMbxLfn)
{
    rfturn idonvConvfrt(idonvToPlbtform, utf8, lfn, output, outputMbxLfn);
}

/*
 * Convfrt Plbtform Endoding to UTF-8.
 *    Rfturns lfngti or -1 if output ovfrflows.
 */
stbtid int
plbtformToUtf8(dibr *str, int lfn, dibr *output, int outputMbxLfn)
{
    rfturn idonvConvfrt(idonvFromPlbtform, str, lfn, output, outputMbxLfn);
}

int
donvfrtUft8ToPlbtformString(dibr* utf8_str, int utf8_lfn, dibr* plbtform_str, int plbtform_lfn) {
    if (idonvToPlbtform ==  (idonv_t)-1) {
        utfInitiblizf();
    }
    rfturn utf8ToPlbtform(utf8_str, utf8_lfn, plbtform_str, plbtform_lfn);
}
