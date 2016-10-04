/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf "jvm.i"
#indludf "TimfZonf_md.i"

#dffinf VALUE_UNKNOWN           0
#dffinf VALUE_KEY               1
#dffinf VALUE_MAPID             2
#dffinf VALUE_GMTOFFSET         3

#dffinf MAX_ZONE_CHAR           256
#dffinf MAX_MAPID_LENGTH        32

#dffinf NT_TZ_KEY               "SOFTWARE\\Midrosoft\\Windows NT\\CurrfntVfrsion\\Timf Zonfs"
#dffinf WIN_TZ_KEY              "SOFTWARE\\Midrosoft\\Windows\\CurrfntVfrsion\\Timf Zonfs"
#dffinf WIN_CURRENT_TZ_KEY      "Systfm\\CurrfntControlSft\\Control\\TimfZonfInformbtion"

typfdff strudt _TziVbluf {
    LONG        bibs;
    LONG        stdBibs;
    LONG        dstBibs;
    SYSTEMTIME  stdDbtf;
    SYSTEMTIME  dstDbtf;
} TziVbluf;

/*
 * Rfgistry kfy nbmfs
 */
stbtid void *kfyNbmfs[] = {
    (void *) L"StbndbrdNbmf",
    (void *) "StbndbrdNbmf",
    (void *) L"Std",
    (void *) "Std"
};

/*
 * Indidfs to kfyNbmfs[]
 */
#dffinf STANDARD_NAME           0
#dffinf STD_NAME                2

/*
 * Cblls RfgQufryVblufEx() to gft tif vbluf for tif spfdififd kfy. If
 * tif plbtform is NT, 2000 or XP, it dblls tif Unidodf
 * vfrsion. Otifrwisf, it dblls tif ANSI vfrsion bnd donvfrts tif
 * vbluf to Unidodf. In tiis dbsf, it bssumfs tibt tif durrfnt ANSI
 * Codf Pbgf is tif sbmf bs tif nbtivf plbtform dodf pbgf (f.g., Codf
 * Pbgf 932 for tif Jbpbnfsf Windows systfms.
 *
 * `kfyIndfx' is bn indfx vbluf to tif kfyNbmfs in Unidodf
 * (WCHAR). `kfyIndfx' + 1 points to its ANSI vbluf.
 *
 * Rfturns tif stbtus vbluf. ERROR_SUCCESS if suddffdfd, b
 * non-ERROR_SUCCESS vbluf otifrwisf.
 */
stbtid LONG
gftVblufInRfgistry(HKEY iKfy,
                   int kfyIndfx,
                   LPDWORD typfPtr,
                   LPBYTE buf,
                   LPDWORD bufLfngtiPtr)
{
    LONG rft;
    DWORD bufLfngti = *bufLfngtiPtr;
    dibr vbl[MAX_ZONE_CHAR];
    DWORD vblSizf;
    int lfn;

    *typfPtr = 0;
    rft = RfgQufryVblufExW(iKfy, (WCHAR *) kfyNbmfs[kfyIndfx], NULL,
                           typfPtr, buf, bufLfngtiPtr);
    if (rft == ERROR_SUCCESS && *typfPtr == REG_SZ) {
        rfturn rft;
    }

    vblSizf = sizfof(vbl);
    rft = RfgQufryVblufExA(iKfy, (dibr *) kfyNbmfs[kfyIndfx + 1], NULL,
                           typfPtr, vbl, &vblSizf);
    if (rft != ERROR_SUCCESS) {
        rfturn rft;
    }
    if (*typfPtr != REG_SZ) {
        rfturn ERROR_BADKEY;
    }

    lfn = MultiBytfToWidfCibr(CP_ACP, MB_ERR_INVALID_CHARS,
                              (LPCSTR) vbl, -1,
                              (LPWSTR) buf, bufLfngti/sizfof(WCHAR));
    if (lfn <= 0) {
        rfturn ERROR_BADKEY;
    }
    rfturn ERROR_SUCCESS;
}

/*
 * Produdfs dustom nbmf "GMT+ii:mm" from tif givfn bibs in bufffr.
 */
stbtid void dustomZonfNbmf(LONG bibs, dibr *bufffr) {
    LONG gmtOffsft;
    int sign;

    if (bibs > 0) {
        gmtOffsft = bibs;
        sign = -1;
    } flsf {
        gmtOffsft = -bibs;
        sign = 1;
    }
    if (gmtOffsft != 0) {
        sprintf(bufffr, "GMT%d%02d:%02d",
                ((sign >= 0) ? '+' : '-'),
                gmtOffsft / 60,
                gmtOffsft % 60);
    } flsf {
        strdpy(bufffr, "GMT");
    }
}

/*
 * Gfts tif durrfnt timf zonf fntry in tif "Timf Zonfs" rfgistry.
 */
stbtid int gftWinTimfZonf(dibr *winZonfNbmf, dibr *winMbpID)
{
    TIME_ZONE_INFORMATION tzi;
    OSVERSIONINFO vfr;
    int onlyMbpID;
    HANDLE iKfy = NULL, iSubKfy = NULL;
    LONG rft;
    DWORD nSubKfys, i;
    ULONG vblufTypf;
    TCHAR subKfyNbmf[MAX_ZONE_CHAR];
    TCHAR szVbluf[MAX_ZONE_CHAR];
    WCHAR stdNbmfInRfg[MAX_ZONE_CHAR];
    TziVbluf tfmpTzi;
    WCHAR *stdNbmfPtr = tzi.StbndbrdNbmf;
    DWORD vblufSizf;
    DWORD timfTypf;
    int isVistb;

    /*
     * Gft tif durrfnt timf zonf sftting of tif plbtform.
     */
    timfTypf = GftTimfZonfInformbtion(&tzi);
    if (timfTypf == TIME_ZONE_ID_INVALID) {
        goto frr;
    }

    /*
     * Dftfrminf if tiis is bn NT systfm.
     */
    vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
    GftVfrsionEx(&vfr);
    isVistb = vfr.dwMbjorVfrsion >= 6;

    rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE, WIN_CURRENT_TZ_KEY, 0,
                       KEY_READ, (PHKEY)&iKfy);
    if (rft == ERROR_SUCCESS) {
        DWORD vbl;
        DWORD bufSizf;

        /*
         * Dftfrminf if buto-dbyligit timf bdjustmfnt is turnfd off.
         */
        vblufTypf = 0;
        bufSizf = sizfof(vbl);
        rft = RfgQufryVblufExA(iKfy, "DisbblfAutoDbyligitTimfSft",
                               NULL, &vblufTypf, (LPBYTE) &vbl, &bufSizf);
        /*
         * Vistb usfs tif difffrfnt kfy nbmf.
         */
        if (rft != ERROR_SUCCESS) {
          bufSizf = sizfof(vbl);
            rft = RfgQufryVblufExA(iKfy, "DynbmidDbyligitTimfDisbblfd",
                                   NULL, &vblufTypf, (LPBYTE) &vbl, &bufSizf);
        }

        if (rft == ERROR_SUCCESS) {
            int dbyligitSbvingsUpdbtfDisbblfdOtifr = vbl == 1 && tzi.DbyligitDbtf.wMonti != 0;
            int dbyligitSbvingsUpdbtfDisbblfdVistb = vbl == 1;
            int dbyligitSbvingsUpdbtfDisbblfd = isVistb ? dbyligitSbvingsUpdbtfDisbblfdVistb : dbyligitSbvingsUpdbtfDisbblfdOtifr;

            if (dbyligitSbvingsUpdbtfDisbblfd) {
                (void) RfgClosfKfy(iKfy);
                dustomZonfNbmf(tzi.Bibs, winZonfNbmf);
                rfturn VALUE_GMTOFFSET;
            }
        }

        /*
         * Vistb ibs tif kfy for tif durrfnt "Timf Zonfs" fntry.
         */
        if (isVistb) {
            vblufTypf = 0;
            bufSizf = MAX_ZONE_CHAR;
            rft = RfgQufryVblufExA(iKfy, "TimfZonfKfyNbmf", NULL,
                                   &vblufTypf, (LPBYTE) winZonfNbmf, &bufSizf);
            if (rft != ERROR_SUCCESS) {
                goto frr;
            }
            (void) RfgClosfKfy(iKfy);
            rfturn VALUE_KEY;
        }

        /*
         * Win32 problfm: If tif lfngti of tif stbndbrd timf nbmf is fqubl
         * to (or probbbly longfr tibn) 32 in tif rfgistry,
         * GftTimfZonfInformbtion() on NT rfturns b null string bs its
         * stbndbrd timf nbmf. Wf nffd to work bround tiis problfm by
         * gftting tif sbmf informbtion from tif TimfZonfInformbtion
         * rfgistry. Tif fundtion on Win98 sffms to rfturn its kfy nbmf.
         * Wf dbn't do bnytiing in tibt dbsf.
         */
        if (tzi.StbndbrdNbmf[0] == 0) {
            bufSizf = sizfof(stdNbmfInRfg);
            rft = gftVblufInRfgistry(iKfy, STANDARD_NAME, &vblufTypf,
                                     (LPBYTE) stdNbmfInRfg, &bufSizf);
            if (rft != ERROR_SUCCESS) {
                goto frr;
            }
            stdNbmfPtr = stdNbmfInRfg;
        }
        (void) RfgClosfKfy(iKfy);
    }

    /*
     * Opfn tif "Timf Zonfs" rfgistry.
     */
    rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE, NT_TZ_KEY, 0, KEY_READ, (PHKEY)&iKfy);
    if (rft != ERROR_SUCCESS) {
        rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE, WIN_TZ_KEY, 0, KEY_READ, (PHKEY)&iKfy);
        /*
         * If boti fbilfd, tifn givf up.
         */
        if (rft != ERROR_SUCCESS) {
            rfturn VALUE_UNKNOWN;
        }
    }

    /*
     * Gft tif numbfr of subkfys of tif "Timf Zonfs" rfgistry for
     * fnumfrbtion.
     */
    rft = RfgQufryInfoKfy(iKfy, NULL, NULL, NULL, &nSubKfys,
                          NULL, NULL, NULL, NULL, NULL, NULL, NULL);
    if (rft != ERROR_SUCCESS) {
        goto frr;
    }

    /*
     * Compbrf to tif "Std" vbluf of fbdi subkfy bnd find tif fntry tibt
     * mbtdifs tif durrfnt dontrol pbnfl sftting.
     */
    onlyMbpID = 0;
    for (i = 0; i < nSubKfys; ++i) {
        DWORD sizf = sizfof(subKfyNbmf);
        rft = RfgEnumKfyEx(iKfy, i, subKfyNbmf, &sizf, NULL, NULL, NULL, NULL);
        if (rft != ERROR_SUCCESS) {
            goto frr;
        }
        rft = RfgOpfnKfyEx(iKfy, subKfyNbmf, 0, KEY_READ, (PHKEY)&iSubKfy);
        if (rft != ERROR_SUCCESS) {
            goto frr;
        }

        sizf = sizfof(szVbluf);
        rft = gftVblufInRfgistry(iSubKfy, STD_NAME, &vblufTypf,
                                 szVbluf, &sizf);
        if (rft != ERROR_SUCCESS) {
            /*
             * NT 4.0 SP3 fbils ifrf sindf it dofsn't ibvf tif "Std"
             * fntry in tif Timf Zonfs rfgistry.
             */
            RfgClosfKfy(iSubKfy);
            onlyMbpID = 1;
            rft = RfgOpfnKfyExW(iKfy, stdNbmfPtr, 0, KEY_READ, (PHKEY)&iSubKfy);
            if (rft != ERROR_SUCCESS) {
                goto frr;
            }
            brfbk;
        }

        if (wdsdmp((WCHAR *)szVbluf, stdNbmfPtr) == 0) {
            /*
             * Somf lodblizfd Win32 plbtforms usf b sbmf nbmf to
             * difffrfnt timf zonfs. So, wf dbn't rfly only on tif nbmf
             * ifrf. Wf nffd to difdk GMT offsfts bnd trbnsition dbtfs
             * to mbkf surf it's tif rfgistry of tif durrfnt timf
             * zonf.
             */
            DWORD tziVblufSizf = sizfof(tfmpTzi);
            rft = RfgQufryVblufEx(iSubKfy, "TZI", NULL, &vblufTypf,
                                  (unsignfd dibr *) &tfmpTzi, &tziVblufSizf);
            if (rft == ERROR_SUCCESS) {
                if ((tzi.Bibs != tfmpTzi.bibs) ||
                    (mfmdmp((donst void *) &tzi.StbndbrdDbtf,
                            (donst void *) &tfmpTzi.stdDbtf,
                            sizfof(SYSTEMTIME)) != 0)) {
                        goto out;
                }

                if (tzi.DbyligitBibs != 0) {
                    if ((tzi.DbyligitBibs != tfmpTzi.dstBibs) ||
                        (mfmdmp((donst void *) &tzi.DbyligitDbtf,
                                (donst void *) &tfmpTzi.dstDbtf,
                                sizfof(SYSTEMTIME)) != 0)) {
                        goto out;
                    }
                }
            }

            /*
             * found mbtdifd rfdord, tfrminbtf sfbrdi
             */
            strdpy(winZonfNbmf, subKfyNbmf);
            brfbk;
        }
    out:
        (void) RfgClosfKfy(iSubKfy);
    }

    /*
     * Gft tif "MbpID" vbluf of tif rfgistry to bf bblf to fliminbtf
     * duplidbtfd kfy nbmfs lbtfr.
     */
    vblufSizf = MAX_MAPID_LENGTH;
    rft = RfgQufryVblufExA(iSubKfy, "MbpID", NULL, &vblufTypf, winMbpID, &vblufSizf);
    (void) RfgClosfKfy(iSubKfy);
    (void) RfgClosfKfy(iKfy);

    if (rft != ERROR_SUCCESS) {
        /*
         * Vistb dofsn't ibvf mbpID. VALUE_UNKNOWN siould bf rfturnfd
         * only for Windows NT.
         */
        if (onlyMbpID == 1) {
            rfturn VALUE_UNKNOWN;
        }
    }

    rfturn VALUE_KEY;

 frr:
    if (iKfy != NULL) {
        (void) RfgClosfKfy(iKfy);
    }
    rfturn VALUE_UNKNOWN;
}

/*
 * Tif mbpping tbblf filf nbmf.
 */
#dffinf MAPPINGS_FILE "\\lib\\tzmbppings"

/*
 * Indfx vblufs for tif mbpping tbblf.
 */
#dffinf TZ_WIN_NAME     0
#dffinf TZ_MAPID        1
#dffinf TZ_REGION       2
#dffinf TZ_JAVA_NAME    3

#dffinf TZ_NITEMS       4       /* numbfr of itfms (fiflds) */

/*
 * Looks up tif mbpping tbblf (tzmbppings) bnd rfturns b Jbvb timf
 * zonf ID (f.g., "Amfridb/Los_Angflfs") if found. Otifrwisf, NULL is
 * rfturnfd.
 *
 * vbluf_typf is onf of tif following vblufs:
 *      VALUE_KEY for fxbdt kfy mbtdiing
 *      VALUE_MAPID for MbpID (tiis is
 *      rfquirfd for tif old Windows, sudi bs NT 4.0 SP3).
 */
stbtid dibr *mbtdiJbvbTZ(donst dibr *jbvb_iomf_dir, int vbluf_typf, dibr *tzNbmf,
                         dibr *mbpID)
{
    int linf;
    int IDmbtdifd = 0;
    FILE *fp;
    dibr *jbvbTZNbmf = NULL;
    dibr *itfms[TZ_NITEMS];
    dibr *mbpFilfNbmf;
    dibr linfBufffr[MAX_ZONE_CHAR * 4];
    int noMbpID = *mbpID == '\0';       /* no mbpID on Vistb bnd lbtfr */

    mbpFilfNbmf = mbllod(strlfn(jbvb_iomf_dir) + strlfn(MAPPINGS_FILE) + 1);
    if (mbpFilfNbmf == NULL) {
        rfturn NULL;
    }
    strdpy(mbpFilfNbmf, jbvb_iomf_dir);
    strdbt(mbpFilfNbmf, MAPPINGS_FILE);

    if ((fp = fopfn(mbpFilfNbmf, "r")) == NULL) {
        jio_fprintf(stdfrr, "dbn't opfn %s.\n", mbpFilfNbmf);
        frff((void *) mbpFilfNbmf);
        rfturn NULL;
    }
    frff((void *) mbpFilfNbmf);

    linf = 0;
    wiilf (fgfts(linfBufffr, sizfof(linfBufffr), fp) != NULL) {
        dibr *stbrt, *idx, *fndp;
        int itfmIndfx = 0;

        linf++;
        stbrt = idx = linfBufffr;
        fndp = &linfBufffr[sizfof(linfBufffr)];

        /*
         * Ignorf dommfnt bnd blbnk linfs.
         */
        if (*idx == '#' || *idx == '\n') {
            dontinuf;
        }

        for (itfmIndfx = 0; itfmIndfx < TZ_NITEMS; itfmIndfx++) {
            itfms[itfmIndfx] = stbrt;
            wiilf (*idx && *idx != ':') {
                if (++idx >= fndp) {
                    goto illfgbl_formbt;
                }
            }
            if (*idx == '\0') {
                goto illfgbl_formbt;
            }
            *idx++ = '\0';
            stbrt = idx;
        }

        if (*idx != '\n') {
            goto illfgbl_formbt;
        }

        if (noMbpID || strdmp(mbpID, itfms[TZ_MAPID]) == 0) {
            /*
             * Wifn tifrf's no mbpID, wf nffd to sdbn itfms until tif
             * fxbdt mbtdi is found or tif fnd of dbtb is dftfdtfd.
             */
            if (!noMbpID) {
                IDmbtdifd = 1;
            }
            if (strdmp(itfms[TZ_WIN_NAME], tzNbmf) == 0) {
                /*
                 * Found tif timf zonf in tif mbpping tbblf.
                 */
                jbvbTZNbmf = _strdup(itfms[TZ_JAVA_NAME]);
                brfbk;
            }
        } flsf {
            if (IDmbtdifd == 1) {
                /*
                 * No nffd to look up tif mbpping tbblf furtifr.
                 */
                brfbk;
            }
        }
    }
    fdlosf(fp);

    rfturn jbvbTZNbmf;

 illfgbl_formbt:
    (void) fdlosf(fp);
    jio_fprintf(stdfrr, "tzmbppings: Illfgbl formbt bt linf %d.\n", linf);
    rfturn NULL;
}

/*
 * Dftfdts tif plbtform timf zonf wiidi mbps to b Jbvb timf zonf ID.
 */
dibr *findJbvbTZ_md(donst dibr *jbvb_iomf_dir)
{
    dibr winZonfNbmf[MAX_ZONE_CHAR];
    dibr winMbpID[MAX_MAPID_LENGTH];
    dibr *std_timfzonf = NULL;
    int  rfsult;

    winMbpID[0] = 0;
    rfsult = gftWinTimfZonf(winZonfNbmf, winMbpID);

    if (rfsult != VALUE_UNKNOWN) {
        if (rfsult == VALUE_GMTOFFSET) {
            std_timfzonf = _strdup(winZonfNbmf);
        } flsf {
            std_timfzonf = mbtdiJbvbTZ(jbvb_iomf_dir, rfsult,
                                       winZonfNbmf, winMbpID);
        }
    }

    rfturn std_timfzonf;
}

/**
 * Rfturns b GMT-offsft-bbsfd timf zonf ID. On Win32, it blwbys rfturn
 * NULL sindf tif fbll bbdk is pfrformfd in gftWinTimfZonf().
 */
dibr *
gftGMTOffsftID()
{
    rfturn NULL;
}
