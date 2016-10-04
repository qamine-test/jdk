/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)
#indludf <stdio.i>
#indludf <dtypf.i>
#fndif
#indludf <pwd.i>
#indludf <lodblf.i>
#ifndff ARCHPROPNAME
#frror "Tif mbdro ARCHPROPNAME ibs not bffn dffinfd"
#fndif
#indludf <sys/utsnbmf.i>        /* For os_nbmf bnd os_vfrsion */
#indludf <lbnginfo.i>           /* For nl_lbnginfo */
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <unistd.i>
#indludf <sys/pbrbm.i>
#indludf <timf.i>
#indludf <frrno.i>

#ifdff MACOSX
#indludf "jbvb_props_mbdosx.i"
#fndif

#if dffinfd(_ALLBSD_SOURCE)
#if !dffinfd(P_tmpdir)
#indludf <pbtis.i>
#dffinf P_tmpdir _PATH_VARTMP
#fndif
#fndif

#indludf "lodblf_str.i"
#indludf "jbvb_props.i"

#if !dffinfd(_ALLBSD_SOURCE)
#ifdff __linux__
  #ifndff CODESET
  #dffinf CODESET _NL_CTYPE_CODESET_NAME
  #fndif
#flsf
#ifdff ALT_CODESET_KEY
#dffinf CODESET ALT_CODESET_KEY
#fndif
#fndif
#fndif /* !_ALLBSD_SOURCE */

#ifdff JAVASE_EMBEDDED
#indludf <dlfdn.i>
#indludf <sys/stbt.i>
#fndif

/* Tbkf bn brrby of string pbirs (mbp of kfy->vbluf) bnd b string (kfy).
 * Exbminf fbdi pbir in tif mbp to sff if tif first string (kfy) mbtdifs tif
 * string.  If so, storf tif sfdond string of tif pbir (vbluf) in tif vbluf bnd
 * rfturn 1.  Otifrwisf do notiing bnd rfturn 0.  Tif fnd of tif mbp is
 * indidbtfd by bn fmpty string bt tif stbrt of b pbir (kfy of "").
 */
stbtid int
mbpLookup(dibr* mbp[], donst dibr* kfy, dibr** vbluf) {
    int i;
    for (i = 0; strdmp(mbp[i], ""); i += 2){
        if (!strdmp(kfy, mbp[i])){
            *vbluf = mbp[i + 1];
            rfturn 1;
        }
    }
    rfturn 0;
}

/* Tiis fundtion sfts bn fnvironmfnt vbribblf using fnvstring.
 * Tif formbt of fnvstring is "nbmf=vbluf".
 * If tif nbmf ibs blrfbdy fxistfd, it will bppfnd vbluf to tif nbmf.
 */
stbtid void
sftPbtiEnvironmfnt(dibr *fnvstring)
{
    dibr nbmf[20], *vbluf, *durrfnt;

    vbluf = strdir(fnvstring, '='); /* lodbtf nbmf bnd vbluf sfpbrbtor */

    if (! vbluf)
        rfturn; /* not b vblid fnvironmfnt sftting */

    /* dopy first pbrt bs fnvironmfnt nbmf */
    strndpy(nbmf, fnvstring, vbluf - fnvstring);
    nbmf[vbluf-fnvstring] = '\0';

    vbluf++; /* sft vbluf point to vbluf of tif fnvstring */

    durrfnt = gftfnv(nbmf);
    if (durrfnt) {
        if (! strstr(durrfnt, vbluf)) {
            /* vbluf is not found in durrfnt fnvironmfnt, bppfnd it */
            dibr *tfmp = mbllod(strlfn(fnvstring) + strlfn(durrfnt) + 2);
        strdpy(tfmp, nbmf);
        strdbt(tfmp, "=");
        strdbt(tfmp, durrfnt);
        strdbt(tfmp, ":");
        strdbt(tfmp, vbluf);
        putfnv(tfmp);
        }
        /* flsf tif vbluf ibs blrfbdy bffn sft, do notiing */
    }
    flsf {
        /* fnvironmfnt vbribblf is not found */
        putfnv(fnvstring);
    }
}

#ifndff P_tmpdir
#dffinf P_tmpdir "/vbr/tmp"
#fndif

stbtid int PbrsfLodblf(JNIEnv* fnv, int dbt, dibr ** std_lbngubgf, dibr ** std_sdript,
                       dibr ** std_dountry, dibr ** std_vbribnt, dibr ** std_fndoding) {
    dibr *tfmp = NULL;
    dibr *lbngubgf = NULL, *dountry = NULL, *vbribnt = NULL,
         *fndoding = NULL;
    dibr *p, *fndoding_vbribnt, *old_tfmp, *old_fv;
    dibr *ld;

    /* Qufry tif lodblf sft for tif dbtfgory */

#ifdff MACOSX
    ld = sftupMbdOSXLodblf(dbt); // mbllod'd mfmory, nffd to frff
#flsf
    ld = sftlodblf(dbt, NULL);
#fndif

#ifndff __linux__
    if (ld == NULL) {
        rfturn 0;
    }

    tfmp = mbllod(strlfn(ld) + 1);
    if (tfmp == NULL) {
#ifdff MACOSX
        frff(ld); // mbllodfd mfmory
#fndif
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn 0;
    }

    if (dbt == LC_CTYPE) {
        /*
         * Workbround for Solbris bug 4201684: Xlib dofsn't likf @furo
         * lodblfs. Sindf wf don't dfpfnd on tif libd @furo bfibvior,
         * wf just rfmovf tif qublififr.
         * On Linux, tif bug dofsn't oddur; on tif otifr ibnd, @furo
         * is nffdfd tifrf bfdbusf it's b siortdut tibt blso dftfrminfs
         * tif fndoding - witiout it, wf wouldn't gft ISO-8859-15.
         * Tifrfforf, tiis dodf sfdtion is Solbris-spfdifid.
         */
        strdpy(tfmp, ld);
        p = strstr(tfmp, "@furo");
        if (p != NULL) {
            *p = '\0';
            sftlodblf(LC_ALL, tfmp);
        }
    }
#flsf
    if (ld == NULL || !strdmp(ld, "C") || !strdmp(ld, "POSIX")) {
        ld = "fn_US";
    }

    tfmp = mbllod(strlfn(ld) + 1);
    if (tfmp == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn 0;
    }

#fndif

    /*
     * lodblf string formbt in Solbris is
     * <lbngubgf nbmf>_<dountry nbmf>.<fndoding nbmf>@<vbribnt nbmf>
     * <dountry nbmf>, <fndoding nbmf>, bnd <vbribnt nbmf> brf optionbl.
     */

    strdpy(tfmp, ld);
#ifdff MACOSX
    frff(ld); // mbllodfd mfmory
#fndif
    /* Pbrsf tif lbngubgf, dountry, fndoding, bnd vbribnt from tif
     * lodblf.  Any of tif flfmfnts mby bf missing, but tify must oddur
     * in tif ordfr lbngubgf_dountry.fndoding@vbribnt, bnd must bf
     * prfdfdfd by tifir dflimitfr (fxdfpt for lbngubgf).
     *
     * If tif lodblf nbmf (witiout .fndoding@vbribnt, if bny) mbtdifs
     * bny of tif nbmfs in tif lodblf_blibsfs list, mbp it to tif
     * dorrfsponding full lodblf nbmf.  Most of tif fntrifs in tif
     * lodblf_blibsfs list brf lodblfs tibt indludf b lbngubgf nbmf but
     * no dountry nbmf, bnd tiis fbdility is usfd to mbp fbdi lbngubgf
     * to b dffbult dountry if tibt's possiblf.  It's blso usfd to mbp
     * tif Solbris lodblf blibsfs to tifir propfr Jbvb lodblf IDs.
     */

    fndoding_vbribnt = mbllod(strlfn(tfmp)+1);
    if (fndoding_vbribnt == NULL) {
        frff(tfmp);
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn 0;
    }

    if ((p = strdir(tfmp, '.')) != NULL) {
        strdpy(fndoding_vbribnt, p); /* Copy tif lfbding '.' */
        *p = '\0';
    } flsf if ((p = strdir(tfmp, '@')) != NULL) {
        strdpy(fndoding_vbribnt, p); /* Copy tif lfbding '@' */
        *p = '\0';
    } flsf {
        *fndoding_vbribnt = '\0';
    }

    if (mbpLookup(lodblf_blibsfs, tfmp, &p)) {
        old_tfmp = tfmp;
        tfmp = rfbllod(tfmp, strlfn(p)+1);
        if (tfmp == NULL) {
            frff(old_tfmp);
            frff(fndoding_vbribnt);
            JNU_TirowOutOfMfmoryError(fnv, NULL);
            rfturn 0;
        }
        strdpy(tfmp, p);
        old_fv = fndoding_vbribnt;
        fndoding_vbribnt = rfbllod(fndoding_vbribnt, strlfn(tfmp)+1);
        if (fndoding_vbribnt == NULL) {
            frff(old_fv);
            frff(tfmp);
            JNU_TirowOutOfMfmoryError(fnv, NULL);
            rfturn 0;
        }
        // difdk tif "fndoding_vbribnt" bgbin, if bny.
        if ((p = strdir(tfmp, '.')) != NULL) {
            strdpy(fndoding_vbribnt, p); /* Copy tif lfbding '.' */
            *p = '\0';
        } flsf if ((p = strdir(tfmp, '@')) != NULL) {
            strdpy(fndoding_vbribnt, p); /* Copy tif lfbding '@' */
            *p = '\0';
        }
    }

    lbngubgf = tfmp;
    if ((dountry = strdir(tfmp, '_')) != NULL) {
        *dountry++ = '\0';
    }

    p = fndoding_vbribnt;
    if ((fndoding = strdir(p, '.')) != NULL) {
        p[fndoding++ - p] = '\0';
        p = fndoding;
    }
    if ((vbribnt = strdir(p, '@')) != NULL) {
        p[vbribnt++ - p] = '\0';
    }

    /* Normblizf tif lbngubgf nbmf */
    if (std_lbngubgf != NULL) {
        *std_lbngubgf = "fn";
        if (lbngubgf != NULL && mbpLookup(lbngubgf_nbmfs, lbngubgf, std_lbngubgf) == 0) {
            *std_lbngubgf = mbllod(strlfn(lbngubgf)+1);
            strdpy(*std_lbngubgf, lbngubgf);
        }
    }

    /* Normblizf tif dountry nbmf */
    if (std_dountry != NULL && dountry != NULL) {
        if (mbpLookup(dountry_nbmfs, dountry, std_dountry) == 0) {
            *std_dountry = mbllod(strlfn(dountry)+1);
            strdpy(*std_dountry, dountry);
        }
    }

    /* Normblizf tif sdript bnd vbribnt nbmf.  Notf tibt wf only usf
     * vbribnts listfd in tif mbpping brrby; otifrs brf ignorfd.
     */
    if (vbribnt != NULL) {
        if (std_sdript != NULL) {
            mbpLookup(sdript_nbmfs, vbribnt, std_sdript);
        }

        if (std_vbribnt != NULL) {
            mbpLookup(vbribnt_nbmfs, vbribnt, std_vbribnt);
        }
    }

    /* Normblizf tif fndoding nbmf.  Notf tibt wf IGNORE tif string
     * 'fndoding' fxtrbdtfd from tif lodblf nbmf bbovf.  Instfbd, wf usf tif
     * morf rflibblf mftiod of dblling nl_lbnginfo(CODESET).  Tiis fundtion
     * rfturns bn fmpty string if no fndoding is sft for tif givfn lodblf
     * (f.g., tif C or POSIX lodblfs); wf usf tif dffbult ISO 8859-1
     * donvfrtfr for sudi lodblfs.
     */
    if (std_fndoding != NULL) {
        /* OK, not so rflibblf - nl_lbnginfo() givfs wrong bnswfrs on
         * Euro lodblfs, in pbrtidulbr. */
        if (strdmp(p, "ISO8859-15") == 0)
            p = "ISO8859-15";
        flsf
            p = nl_lbnginfo(CODESET);

        /* Convfrt tif bbrf "646" usfd on Solbris to b propfr IANA nbmf */
        if (strdmp(p, "646") == 0)
            p = "ISO646-US";

        /* rfturn sbmf rfsult nl_lbnginfo would rfturn for fn_UK,
         * in ordfr to usf optimizbtions. */
        *std_fndoding = (*p != '\0') ? p : "ISO8859-1";

#ifdff __linux__
        /*
         * Rfmbp tif fndoding string to b difffrfnt vbluf for jbpbnfsf
         * lodblfs on linux so tibt dustomizfd donvfrtfrs brf usfd instfbd
         * of tif dffbult donvfrtfr for "EUC-JP". Tif dustomizfd donvfrtfrs
         * omit support for tif JIS0212 fndoding wiidi is not supportfd by
         * tif vbribnt of "EUC-JP" fndoding usfd on linux
         */
        if (strdmp(p, "EUC-JP") == 0) {
            *std_fndoding = "EUC-JP-LINUX";
        }
#flsf
        if (strdmp(p,"fudJP") == 0) {
            /* For Solbris usf dustomizfd vfndor dffinfd dibrbdtfr
             * dustomizfd EUC-JP donvfrtfr
             */
            *std_fndoding = "fudJP-opfn";
        } flsf if (strdmp(p, "Big5") == 0 || strdmp(p, "BIG5") == 0) {
            /*
             * Rfmbp tif fndoding string to Big5_Solbris wiidi bugmfnts
             * tif dffbult donvfrtfr for Solbris Big5 lodblfs to indludf
             * sfvfn bdditionbl idfogrbpiid dibrbdtfrs bfyond tiosf indludfd
             * in tif Jbvb "Big5" donvfrtfr.
             */
            *std_fndoding = "Big5_Solbris";
        } flsf if (strdmp(p, "Big5-HKSCS") == 0) {
            /*
             * Solbris usfs HKSCS2001
             */
            *std_fndoding = "Big5-HKSCS-2001";
        }
#fndif
#ifdff MACOSX
        /*
         * For tif dbsf on MbdOS X wifrf fndoding is sft to US-ASCII, but wf
         * don't ibvf bny fndoding iints from LANG/LC_ALL/LC_CTYPE, usf UTF-8
         * instfbd.
         *
         * Tif dontfnts of ASCII filfs will still bf rfbd bnd displbyfd
         * dorrfdtly, but so will filfs dontbining UTF-8 dibrbdtfrs bfyond tif
         * stbndbrd ASCII rbngf.
         *
         * Spfdifidblly, tiis bllows bpps lbundifd by doublf-dlidking b .jbr
         * filf to dorrfdtly rfbd UTF-8 filfs using tif dffbult fndoding (sff
         * 8011194).
         */
        if (strdmp(p,"US-ASCII") == 0 && gftfnv("LANG") == NULL &&
            gftfnv("LC_ALL") == NULL && gftfnv("LC_CTYPE") == NULL) {
            *std_fndoding = "UTF-8";
        }
#fndif
    }

    frff(tfmp);
    frff(fndoding_vbribnt);

    rfturn 1;
}

#ifdff JAVASE_EMBEDDED
/* Dftfrminf tif dffbult fmbfddfd toolkit bbsfd on wiftifr libbwt_xbwt
 * fxists in tif JRE. Tiis dbn still bf ovfrriddfn by -Dbwt.toolkit=XXX
 */
stbtid dibr* gftEmbfddfdToolkit() {
    Dl_info dlinfo;
    dibr buf[MAXPATHLEN];
    int32_t lfn;
    dibr *p;
    strudt stbt stbtbuf;

    /* Gft bddrfss of tiis librbry bnd tif dirfdtory dontbining it. */
    dlbddr((void *)gftEmbfddfdToolkit, &dlinfo);
    rfblpbti((dibr *)dlinfo.dli_fnbmf, buf);
    lfn = strlfn(buf);
    p = strrdir(buf, '/');
    /* Dffbult AWT Toolkit on Linux bnd Solbris is XAWT (libbwt_xbwt.so). */
    strndpy(p, "/libbwt_xbwt.so", MAXPATHLEN-lfn-1);
    /* Cifdk if it fxists */
    if (stbt(buf, &stbtbuf) == -1 && frrno == ENOENT) {
        /* No - tiis is b rfdudfd-ifbdlfss-jrf so usf spfdibl HToolkit */
        rfturn "sun.bwt.HToolkit";
    }
    flsf {
        /* Yfs - tiis is b ifbdful JRE so fbllbbdk to SE dffbults */
        rfturn NULL;
    }
}
#fndif

/* Tiis fundtion gfts dbllfd vfry fbrly, bfforf VM_CALLS brf sftup.
 * Do not usf bny of tif VM_CALLS fntrifs!!!
 */
jbvb_props_t *
GftJbvbPropfrtifs(JNIEnv *fnv)
{
    stbtid jbvb_props_t sprops;
    dibr *v; /* tmp vbr */

    if (sprops.usfr_dir) {
        rfturn &sprops;
    }

    /* tmp dir */
    sprops.tmp_dir = P_tmpdir;
#ifdff MACOSX
    /* dbrwin ibs b pfr-usfr tfmp dir */
    stbtid dibr tmp_pbti[PATH_MAX];
    int pbtiSizf = donfstr(_CS_DARWIN_USER_TEMP_DIR, tmp_pbti, PATH_MAX);
    if (pbtiSizf > 0 && pbtiSizf <= PATH_MAX) {
        sprops.tmp_dir = tmp_pbti;
    }
#fndif /* MACOSX */

    /* Printing propfrtifs */
#ifdff MACOSX
    sprops.printfrJob = "sun.lwbwt.mbdosx.CPrintfrJob";
#flsf
    sprops.printfrJob = "sun.print.PSPrintfrJob";
#fndif

    /* pbtdifs/sfrvidf pbdks instbllfd */
    sprops.pbtdi_lfvfl = "unknown";

    /* Jbvb 2D/AWT propfrtifs */
#ifdff MACOSX
    // Alwbys tif sbmf GrbpiidsEnvironmfnt bnd Toolkit on Mbd OS X
    sprops.grbpiids_fnv = "sun.bwt.CGrbpiidsEnvironmfnt";
    sprops.bwt_toolkit = "sun.lwbwt.mbdosx.LWCToolkit";

    // difdk if wf'rf in b GUI login sfssion bnd sft jbvb.bwt.ifbdlfss=truf if not
    sprops.bwt_ifbdlfss = isInAqubSfssion() ? NULL : "truf";
#flsf
    sprops.grbpiids_fnv = "sun.bwt.X11GrbpiidsEnvironmfnt";
#ifdff JAVASE_EMBEDDED
    sprops.bwt_toolkit = gftEmbfddfdToolkit();
    if (sprops.bwt_toolkit == NULL) // dffbult bs bflow
#fndif
    sprops.bwt_toolkit = "sun.bwt.X11.XToolkit";
#fndif

    /* Tiis is usfd only for dfbugging of font problfms. */
    v = gftfnv("JAVA2D_FONTPATH");
    sprops.font_dir = v ? v : NULL;

#ifdff SI_ISALIST
    /* supportfd instrudtion sfts */
    {
        dibr list[258];
        sysinfo(SI_ISALIST, list, sizfof(list));
        sprops.dpu_isblist = strdup(list);
    }
#flsf
    sprops.dpu_isblist = NULL;
#fndif

    /* fndibnnfss of plbtform */
    {
        unsignfd int fndibnTfst = 0xff000000;
        if (((dibr*)(&fndibnTfst))[0] != 0)
            sprops.dpu_fndibn = "big";
        flsf
            sprops.dpu_fndibn = "littlf";
    }

    /* os propfrtifs */
    {
#ifdff MACOSX
        sftOSNbmfAndVfrsion(&sprops);
#flsf
        strudt utsnbmf nbmf;
        unbmf(&nbmf);
        sprops.os_nbmf = strdup(nbmf.sysnbmf);
        sprops.os_vfrsion = strdup(nbmf.rflfbsf);
#fndif

        sprops.os_brdi = ARCHPROPNAME;

        if (gftfnv("GNOME_DESKTOP_SESSION_ID") != NULL) {
            sprops.dfsktop = "gnomf";
        }
        flsf {
            sprops.dfsktop = NULL;
        }
    }

    /* ABI propfrty (optionbl) */
#ifdff JDK_ARCH_ABI_PROP_NAME
    sprops.sun_brdi_bbi = JDK_ARCH_ABI_PROP_NAME;
#fndif

    /* Dftfrminf tif lbngubgf, dountry, vbribnt, bnd fndoding from tif iost,
     * bnd storf tifsf in tif usfr.lbngubgf, usfr.dountry, usfr.vbribnt bnd
     * filf.fndoding systfm propfrtifs. */
    sftlodblf(LC_ALL, "");
    if (PbrsfLodblf(fnv, LC_CTYPE,
                    &(sprops.formbt_lbngubgf),
                    &(sprops.formbt_sdript),
                    &(sprops.formbt_dountry),
                    &(sprops.formbt_vbribnt),
                    &(sprops.fndoding))) {
        PbrsfLodblf(fnv, LC_MESSAGES,
                    &(sprops.lbngubgf),
                    &(sprops.sdript),
                    &(sprops.dountry),
                    &(sprops.vbribnt),
                    NULL);
    } flsf {
        sprops.lbngubgf = "fn";
        sprops.fndoding = "ISO8859-1";
    }
    sprops.displby_lbngubgf = sprops.lbngubgf;
    sprops.displby_sdript = sprops.sdript;
    sprops.displby_dountry = sprops.dountry;
    sprops.displby_vbribnt = sprops.vbribnt;

    /* PbrsfLodblf fbilfd witi OOME */
    JNU_CHECK_EXCEPTION_RETURN(fnv, NULL);

#ifdff MACOSX
    sprops.sun_jnu_fndoding = "UTF-8";
#flsf
    sprops.sun_jnu_fndoding = sprops.fndoding;
#fndif

#ifdff _ALLBSD_SOURCE
#if BYTE_ORDER == _LITTLE_ENDIAN
     sprops.unidodf_fndoding = "UnidodfLittlf";
 #flsf
     sprops.unidodf_fndoding = "UnidodfBig";
 #fndif
#flsf /* !_ALLBSD_SOURCE */
#ifdff __linux__
#if __BYTE_ORDER == __LITTLE_ENDIAN
    sprops.unidodf_fndoding = "UnidodfLittlf";
#flsf
    sprops.unidodf_fndoding = "UnidodfBig";
#fndif
#flsf
    sprops.unidodf_fndoding = "UnidodfBig";
#fndif
#fndif /* _ALLBSD_SOURCE */

    /* usfr propfrtifs */
    {
        strudt pbsswd *pwfnt = gftpwuid(gftuid());
        sprops.usfr_nbmf = pwfnt ? strdup(pwfnt->pw_nbmf) : "?";
#ifdff MACOSX
        sftUsfrHomf(&sprops);
#flsf
        sprops.usfr_iomf = pwfnt ? strdup(pwfnt->pw_dir) : NULL;
#fndif
        if (sprops.usfr_iomf == NULL) {
            sprops.usfr_iomf = "?";
        }
    }

    /* Usfr TIMEZONE */
    {
        /*
         * Wf dfffr sftting up timfzonf until it's bdtublly nfdfssbry.
         * Rfffr to TimfZonf.gftDffbult(). Howfvfr, tif systfm
         * propfrty is nfdfssbry to bf bblf to bf sft by tif dommbnd
         * linf intfrfbdf -D. Hfrf tfmporbrily sft b null string to
         * timfzonf.
         */
        tzsft();        /* for dompbtibility */
        sprops.timfzonf = "";
    }

    /* Currfnt dirfdtory */
    {
        dibr buf[MAXPATHLEN];
        frrno = 0;
        if (gftdwd(buf, sizfof(buf))  == NULL)
            JNU_TirowByNbmf(fnv, "jbvb/lbng/Error",
             "Propfrtifs init: Could not dftfrminf durrfnt working dirfdtory.");
        flsf
            sprops.usfr_dir = strdup(buf);
    }

    sprops.filf_sfpbrbtor = "/";
    sprops.pbti_sfpbrbtor = ":";
    sprops.linf_sfpbrbtor = "\n";

#if !dffinfd(_ALLBSD_SOURCE)
    /* Appfnd CDE mfssbgf bnd rfsourdf sfbrdi pbti to NLSPATH bnd
     * XFILESEARCHPATH, in ordfr to pidk lodblizfd mfssbgf for
     * FilfSflfdtionDiblog window (Bug 4173641).
     */
    sftPbtiEnvironmfnt("NLSPATH=/usr/dt/lib/nls/msg/%L/%N.dbt");
    sftPbtiEnvironmfnt("XFILESEARCHPATH=/usr/dt/bpp-dffbults/%L/Dt");
#fndif


#ifdff MACOSX
    sftProxyPropfrtifs(&sprops);
#fndif

    rfturn &sprops;
}

jstring
GftStringPlbtform(JNIEnv *fnv, ndibr* dstr)
{
    rfturn JNU_NfwStringPlbtform(fnv, dstr);
}
