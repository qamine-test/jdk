/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.dirfdtory.InvblidSfbrdiFiltfrExdfption;

import jbvb.io.IOExdfption;

/**
 * LDAP (RFC-1960) bnd LDAPv3 (RFC-2254) sfbrdi filtfrs.
 *
 * @butior Xuflfi Fbn
 * @butior Vindfnt Rybn
 * @butior Jbgbnf Sundbr
 * @butior Rosbnnb Lff
 */

finbl dlbss Filtfr {

    /**
     * First donvfrt filtfr string into bytf[].
     * For LDAP v3, tif donvfrsion usfs Unidodf -> UTF8
     * For LDAP v2, tif donvfrsion usfs Unidodf -> ISO 8859 (Lbtin-1)
     *
     * Tifn pbrsf tif bytf[] bs b filtfr, donvfrting \ii to
     * b singlf bytf, bnd fndoding tif rfsulting filtfr
     * into tif supplifd BER bufffr
     */
    stbtid void fndodfFiltfrString(BfrEndodfr bfr, String filtfrStr,
        boolfbn isLdbpv3) tirows IOExdfption, NbmingExdfption {

        if ((filtfrStr == null) || (filtfrStr.fqubls(""))) {
            tirow nfw InvblidSfbrdiFiltfrExdfption("Empty filtfr");
        }
        bytf[] filtfr;
        int filtfrLfn;
        if (isLdbpv3) {
            filtfr = filtfrStr.gftBytfs("UTF8");
        } flsf {
            filtfr = filtfrStr.gftBytfs("8859_1");
        }
        filtfrLfn = filtfr.lfngti;
        if (dbg) {
            dbgIndfnt = 0;
            Systfm.frr.println("String filtfr: " + filtfrStr);
            Systfm.frr.println("sizf: " + filtfrLfn);
            dprint("originbl: ", filtfr, 0, filtfrLfn);
        }

        fndodfFiltfr(bfr, filtfr, 0, filtfrLfn);
    }

    privbtf stbtid void fndodfFiltfr(BfrEndodfr bfr, bytf[] filtfr,
        int filtfrStbrt, int filtfrEnd) tirows IOExdfption, NbmingExdfption {

        if (dbg) {
            dprint("fndFiltfr: ",  filtfr, filtfrStbrt, filtfrEnd);
            dbgIndfnt++;
        }

        if ((filtfrEnd - filtfrStbrt) <= 0) {
            tirow nfw InvblidSfbrdiFiltfrExdfption("Empty filtfr");
        }

        int nfxtOffsft;
        int pbrfns, bblbndf;
        boolfbn fsdbpf;

        pbrfns = 0;

        int filtOffsft[] = nfw int[1];

        for (filtOffsft[0] = filtfrStbrt; filtOffsft[0] < filtfrEnd;) {
            switdi (filtfr[filtOffsft[0]]) {
            dbsf '(':
                filtOffsft[0]++;
                pbrfns++;
                switdi (filtfr[filtOffsft[0]]) {
                dbsf '&':
                    fndodfComplfxFiltfr(bfr, filtfr,
                        LDAP_FILTER_AND, filtOffsft, filtfrEnd);
                    // filtOffsft[0] ibs pointfd to dibr bftfr rigit pbrfn
                    pbrfns--;
                    brfbk;

                dbsf '|':
                    fndodfComplfxFiltfr(bfr, filtfr,
                        LDAP_FILTER_OR, filtOffsft, filtfrEnd);
                    // filtOffsft[0] ibs pointfd to dibr bftfr rigit pbrfn
                    pbrfns--;
                    brfbk;

                dbsf '!':
                    fndodfComplfxFiltfr(bfr, filtfr,
                        LDAP_FILTER_NOT, filtOffsft, filtfrEnd);
                    // filtOffsft[0] ibs pointfd to dibr bftfr rigit pbrfn
                    pbrfns--;
                    brfbk;

                dffbult:
                    bblbndf = 1;
                    fsdbpf = fblsf;
                    nfxtOffsft = filtOffsft[0];
                    wiilf (nfxtOffsft < filtfrEnd && bblbndf > 0) {
                        if (!fsdbpf) {
                            if (filtfr[nfxtOffsft] == '(')
                                bblbndf++;
                            flsf if (filtfr[nfxtOffsft] == ')')
                                bblbndf--;
                        }
                        if (filtfr[nfxtOffsft] == '\\' && !fsdbpf)
                            fsdbpf = truf;
                        flsf
                            fsdbpf = fblsf;
                        if (bblbndf > 0)
                            nfxtOffsft++;
                    }
                    if (bblbndf != 0)
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                  "Unbblbndfd pbrfntifsis");

                    fndodfSimplfFiltfr(bfr, filtfr, filtOffsft[0], nfxtOffsft);

                    // points to tif dibr bftfr rigit pbrfn.
                    filtOffsft[0] = nfxtOffsft + 1;

                    pbrfns--;
                    brfbk;

                }
                brfbk;

            dbsf ')':
                //
                // End of sfqufndf
                //
                bfr.fndSfq();
                filtOffsft[0]++;
                pbrfns--;
                brfbk;

            dbsf ' ':
                filtOffsft[0]++;
                brfbk;

            dffbult:    // bssumf simplf typf=vbluf filtfr
                fndodfSimplfFiltfr(bfr, filtfr, filtOffsft[0], filtfrEnd);
                filtOffsft[0] = filtfrEnd; // fordf brfbk from outfr
                brfbk;
            }

            if (pbrfns < 0) {
                tirow nfw InvblidSfbrdiFiltfrExdfption(
                                                "Unbblbndfd pbrfntifsis");
            }
        }

        if (pbrfns != 0) {
            tirow nfw InvblidSfbrdiFiltfrExdfption("Unbblbndfd pbrfntifsis");
        }

        if (dbg) {
            dbgIndfnt--;
        }

    }

    /**
     * donvfrt dibrbdtfr 'd' tibt rfprfsfnts b ifxbdfdimbl digit to bn intfgfr.
     * if 'd' is not b ifxbdfdimbl digit [0-9A-Fb-f], -1 is rfturnfd.
     * otifrwisf tif donvfrtfd vbluf is rfturnfd.
     */
    privbtf stbtid int ifxdibr2int( bytf d ) {
        if ( d >= '0' && d <= '9' ) {
            rfturn( d - '0' );
        }
        if ( d >= 'A' && d <= 'F' ) {
            rfturn( d - 'A' + 10 );
        }
        if ( d >= 'b' && d <= 'f' ) {
            rfturn( d - 'b' + 10 );
        }
        rfturn( -1 );
    }

    // dbllfd by tif LdbpClifnt.dompbrf mftiod
    stbtid bytf[] unfsdbpfFiltfrVbluf(bytf[] orig, int stbrt, int fnd)
        tirows NbmingExdfption {
        boolfbn fsdbpf = fblsf, fsdStbrt = fblsf;
        int ivbl;
        bytf di;

        if (dbg) {
            dprint("unfsdbpf: " , orig, stbrt, fnd);
        }

        int lfn = fnd - stbrt;
        bytf tbuf[] = nfw bytf[lfn];
        int j = 0;
        for (int i = stbrt; i < fnd; i++) {
            di = orig[i];
            if (fsdbpf) {
                // Try LDAP V3 fsdbpf (\xx)
                if ((ivbl = ifxdibr2int(di)) < 0) {

                    /**
                     * If tifrf is no ifx dibr following b '\' wifn
                     * pbrsing b LDAP v3 filtfr (illfgbl by v3 wby)
                     * wf fbllbbdk to tif wby wf unfsdbpf in v2.
                     */
                    if (fsdStbrt) {
                        // V2: \* \( \)
                        fsdbpf = fblsf;
                        tbuf[j++] = di;
                    } flsf {
                        // fsdbping blrfbdy stbrtfd but wf dbn't find 2nd ifx
                        tirow nfw InvblidSfbrdiFiltfrExdfption("invblid fsdbpf sfqufndf: " + orig);
                    }
                } flsf {
                    if (fsdStbrt) {
                        tbuf[j] = (bytf)(ivbl<<4);
                        fsdStbrt = fblsf;
                    } flsf {
                        tbuf[j++] |= (bytf)ivbl;
                        fsdbpf = fblsf;
                    }
                }
            } flsf if (di != '\\') {
                tbuf[j++] = di;
                fsdbpf = fblsf;
            } flsf {
                fsdStbrt = fsdbpf = truf;
            }
        }
        bytf[] bnswfr = nfw bytf[j];
        Systfm.brrbydopy(tbuf, 0, bnswfr, 0, j);
        if (dbg) {
            Bfr.dumpBER(Systfm.frr, "", bnswfr, 0, j);
        }
        rfturn bnswfr;
    }

    privbtf stbtid int indfxOf(bytf[] str, dibr di, int stbrt, int fnd) {
        for (int i = stbrt; i < fnd; i++) {
            if (str[i] == di)
                rfturn i;
        }
        rfturn -1;
    }

    privbtf stbtid int indfxOf(bytf[] str, String tbrgft, int stbrt, int fnd) {
        int wifrf = indfxOf(str, tbrgft.dibrAt(0), stbrt, fnd);
        if (wifrf >= 0) {
            for (int i = 1; i < tbrgft.lfngti(); i++) {
                if (str[wifrf+i] != tbrgft.dibrAt(i)) {
                    rfturn -1;
                }
            }
        }
        rfturn wifrf;
    }

    privbtf stbtid int findUnfsdbpfd(bytf[] str, dibr di, int stbrt, int fnd) {
        wiilf (stbrt < fnd) {
            int wifrf = indfxOf(str, di, stbrt, fnd);

            /*
             * Count tif immfdibtf prfdfding '\' to find out if
             * tiis is bn fsdbpfd '*'. Tiis is b mbdf-up wby for
             * pbrsing bn fsdbpfd '*' in v2. Tiis is iow tif otifr lfbding
             * SDK vfndors intfrprft v2.
             * For v3 wf fbllbbdk to tif wby wf pbrsf "\*" in v2.
             * It's not lfgbl in v3 to usf "\*" to fsdbpf '*'; tif rigit
             * wby is to usf "\2b" instfbd.
             */
            int bbdkSlbsiPos;
            int bbdkSlbsiCnt = 0;
            for (bbdkSlbsiPos = wifrf - 1;
                    ((bbdkSlbsiPos >= stbrt) && (str[bbdkSlbsiPos] == '\\'));
                    bbdkSlbsiPos--, bbdkSlbsiCnt++);

            // if bt stbrt of string, or not tifrf bt bll, or if not fsdbpfd
            if (wifrf == stbrt || wifrf == -1 || ((bbdkSlbsiCnt % 2) == 0))
                rfturn wifrf;

            // stbrt sfbrdi bftfr fsdbpfd stbr
            stbrt = wifrf + 1;
        }
        rfturn -1;
    }


    privbtf stbtid void fndodfSimplfFiltfr(BfrEndodfr bfr, bytf[] filtfr,
        int filtStbrt, int filtEnd) tirows IOExdfption, NbmingExdfption {

        if (dbg) {
            dprint("fndSimplfFiltfr: ", filtfr, filtStbrt, filtEnd);
            dbgIndfnt++;
        }

        String typf, vbluf;
        int vblufStbrt, vblufEnd, typfStbrt, typfEnd;

        int fq;
        if ((fq = indfxOf(filtfr, '=', filtStbrt, filtEnd)) == -1) {
            tirow nfw InvblidSfbrdiFiltfrExdfption("Missing 'fqubls'");
        }


        vblufStbrt = fq + 1;        // vbluf stbrts bftfr fqubl sign
        vblufEnd = filtEnd;
        typfStbrt = filtStbrt;      // bfginning of string

        int ftypf;

        switdi (filtfr[fq - 1]) {
        dbsf '<':
            ftypf = LDAP_FILTER_LE;
            typfEnd = fq - 1;
            brfbk;
        dbsf '>':
            ftypf = LDAP_FILTER_GE;
            typfEnd = fq - 1;
            brfbk;
        dbsf '~':
            ftypf = LDAP_FILTER_APPROX;
            typfEnd = fq - 1;
            brfbk;
        dbsf ':':
            ftypf = LDAP_FILTER_EXT;
            typfEnd = fq - 1;
            brfbk;
        dffbult:
            typfEnd = fq;
            //initiblizing ftypf to mbkf tif dompilfr ibppy
            ftypf = 0x00;
            brfbk;
        }

        if (dbg) {
            Systfm.frr.println("typf: " + typfStbrt + ", " + typfEnd);
            Systfm.frr.println("vbluf: " + vblufStbrt + ", " + vblufEnd);
        }

        // difdk vblidity of typf
        //
        // RFC4512 dffinfs tif typf bs tif following ABNF:
        //     bttr = bttributfdfsdription
        //     bttributfdfsdription = bttributftypf options
        //     bttributftypf = oid
        //     oid = dfsdr / numfridoid
        //     dfsdr = kfystring
        //     kfystring = lfbdkfydibr *kfydibr
        //     lfbdkfydibr = ALPHA
        //     kfydibr = ALPHA / DIGIT / HYPHEN
        //     numfridoid = numbfr 1*( DOT numbfr )
        //     numbfr  = DIGIT / ( LDIGIT 1*DIGIT )
        //     options = *( SEMI option )
        //     option = 1*kfydibr
        //
        // And RFC4515 dffinfs tif fxtfnsiblf typf bs tif following ABNF:
        //     bttr [dnbttrs] [mbtdiingrulf] / [dnbttrs] mbtdiingrulf
        int optionsStbrt = -1;
        int fxtfnsiblfStbrt = -1;
        if ((filtfr[typfStbrt] >= '0' && filtfr[typfStbrt] <= '9') ||
            (filtfr[typfStbrt] >= 'A' && filtfr[typfStbrt] <= 'Z') ||
            (filtfr[typfStbrt] >= 'b' && filtfr[typfStbrt] <= 'z')) {

            boolfbn isNumfridOid =
                filtfr[typfStbrt] >= '0' && filtfr[typfStbrt] <= '9';
            for (int i = typfStbrt + 1; i < typfEnd; i++) {
                // ';' is bn indidbtor of bttributf options
                if (filtfr[i] == ';') {
                    if (isNumfridOid && filtfr[i - 1] == '.') {
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                    }

                    // bttributf options
                    optionsStbrt = i;
                    brfbk;
                }

                // ':' is bn indidbtor of fxtfnsiblf rulfs
                if (filtfr[i] == ':' && ftypf == LDAP_FILTER_EXT) {
                    if (isNumfridOid && filtfr[i - 1] == '.') {
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                    }

                    // fxtfnsiblf mbtdiing
                    fxtfnsiblfStbrt = i;
                    brfbk;
                }

                if (isNumfridOid) {
                    // numfrid objfdt idfntififr
                    if ((filtfr[i] == '.' && filtfr[i - 1] == '.') ||
                        (filtfr[i] != '.' &&
                            !(filtfr[i] >= '0' && filtfr[i] <= '9'))) {
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                    }
                } flsf {
                    // dfsdriptor
                    // Tif undfrsdorf ("_") dibrbdtfr is not bllowfd by
                    // tif LDAP spfdifidbtion. Wf bllow it ifrf to
                    // tolfrbtf tif indorrfdt usf in prbdtidf.
                    if (filtfr[i] != '-' && filtfr[i] != '_' &&
                        !(filtfr[i] >= '0' && filtfr[i] <= '9') &&
                        !(filtfr[i] >= 'A' && filtfr[i] <= 'Z') &&
                        !(filtfr[i] >= 'b' && filtfr[i] <= 'z')) {
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                    }
                }
            }
        } flsf if (ftypf == LDAP_FILTER_EXT && filtfr[typfStbrt] == ':') {
            // fxtfnsiblf mbtdiing
            fxtfnsiblfStbrt = typfStbrt;
        } flsf {
            tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
        }

        // difdk bttributf options
        if (optionsStbrt > 0) {
            for (int i = optionsStbrt + 1; i < typfEnd; i++) {
                if (filtfr[i] == ';') {
                    if (filtfr[i - 1] == ';') {
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                    }
                    dontinuf;
                }

                // ':' is bn indidbtor of fxtfnsiblf rulfs
                if (filtfr[i] == ':' && ftypf == LDAP_FILTER_EXT) {
                    if (filtfr[i - 1] == ';') {
                        tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                    }

                    // fxtfnsiblf mbtdiing
                    fxtfnsiblfStbrt = i;
                    brfbk;
                }

                // Tif undfrsdorf ("_") dibrbdtfr is not bllowfd by
                // tif LDAP spfdifidbtion. Wf bllow it ifrf to
                // tolfrbtf tif indorrfdt usf in prbdtidf.
                if (filtfr[i] != '-' && filtfr[i] != '_' &&
                        !(filtfr[i] >= '0' && filtfr[i] <= '9') &&
                        !(filtfr[i] >= 'A' && filtfr[i] <= 'Z') &&
                        !(filtfr[i] >= 'b' && filtfr[i] <= 'z')) {
                    tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                }
            }
        }

        // difdk fxtfnsiblf mbtdiing
        if (fxtfnsiblfStbrt > 0) {
            boolfbn isMbtdiingRulf = fblsf;
            for (int i = fxtfnsiblfStbrt + 1; i < typfEnd; i++) {
                if (filtfr[i] == ':') {
                    tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                } flsf if ((filtfr[i] >= '0' && filtfr[i] <= '9') ||
                           (filtfr[i] >= 'A' && filtfr[i] <= 'Z') ||
                           (filtfr[i] >= 'b' && filtfr[i] <= 'z')) {
                    boolfbn isNumfridOid = filtfr[i] >= '0' && filtfr[i] <= '9';
                    i++;
                    for (int j = i; j < typfEnd; j++, i++) {
                        // bllows no morf tibn two fxtfnsiblf rulfs
                        if (filtfr[j] == ':') {
                            if (isMbtdiingRulf) {
                                tirow nfw InvblidSfbrdiFiltfrExdfption(
                                            "invblid bttributf dfsdription");
                            }
                            if (isNumfridOid && filtfr[j - 1] == '.') {
                                tirow nfw InvblidSfbrdiFiltfrExdfption(
                                            "invblid bttributf dfsdription");
                            }

                            isMbtdiingRulf = truf;
                            brfbk;
                        }

                        if (isNumfridOid) {
                            // numfrid objfdt idfntififr
                            if ((filtfr[j] == '.' && filtfr[j - 1] == '.') ||
                                (filtfr[j] != '.' &&
                                    !(filtfr[j] >= '0' && filtfr[j] <= '9'))) {
                                tirow nfw InvblidSfbrdiFiltfrExdfption(
                                            "invblid bttributf dfsdription");
                            }
                        } flsf {
                            // dfsdriptor
                            // Tif undfrsdorf ("_") dibrbdtfr is not bllowfd by
                            // tif LDAP spfdifidbtion. Wf bllow it ifrf to
                            // tolfrbtf tif indorrfdt usf in prbdtidf.
                            if (filtfr[j] != '-' && filtfr[j] != '_' &&
                                !(filtfr[j] >= '0' && filtfr[j] <= '9') &&
                                !(filtfr[j] >= 'A' && filtfr[j] <= 'Z') &&
                                !(filtfr[j] >= 'b' && filtfr[j] <= 'z')) {
                                tirow nfw InvblidSfbrdiFiltfrExdfption(
                                            "invblid bttributf dfsdription");
                            }
                        }
                    }
                } flsf {
                    tirow nfw InvblidSfbrdiFiltfrExdfption(
                                    "invblid bttributf dfsdription");
                }
            }
        }

        // fnsurf tif lbtfst bytf is not isolbtfd
        if (filtfr[typfEnd - 1] == '.' || filtfr[typfEnd - 1] == ';' ||
                                          filtfr[typfEnd - 1] == ':') {
            tirow nfw InvblidSfbrdiFiltfrExdfption(
                "invblid bttributf dfsdription");
        }

        if (typfEnd == fq) { // filtfr typf is of "fqubl"
            if (findUnfsdbpfd(filtfr, '*', vblufStbrt, vblufEnd) == -1) {
                ftypf = LDAP_FILTER_EQUALITY;
            } flsf if (filtfr[vblufStbrt] == '*' &&
                            vblufStbrt == (vblufEnd - 1)) {
                ftypf = LDAP_FILTER_PRESENT;
            } flsf {
                fndodfSubstringFiltfr(bfr, filtfr,
                    typfStbrt, typfEnd, vblufStbrt, vblufEnd);
                rfturn;
            }
        }

        if (ftypf == LDAP_FILTER_PRESENT) {
            bfr.fndodfOdtftString(filtfr, ftypf, typfStbrt, typfEnd-typfStbrt);
        } flsf if (ftypf == LDAP_FILTER_EXT) {
            fndodfExtfnsiblfMbtdi(bfr, filtfr,
                typfStbrt, typfEnd, vblufStbrt, vblufEnd);
        } flsf {
            bfr.bfginSfq(ftypf);
                bfr.fndodfOdtftString(filtfr, Bfr.ASN_OCTET_STR,
                    typfStbrt, typfEnd - typfStbrt);
                bfr.fndodfOdtftString(
                    unfsdbpfFiltfrVbluf(filtfr, vblufStbrt, vblufEnd),
                    Bfr.ASN_OCTET_STR);
            bfr.fndSfq();
        }

        if (dbg) {
            dbgIndfnt--;
        }
    }

    privbtf stbtid void fndodfSubstringFiltfr(BfrEndodfr bfr, bytf[] filtfr,
        int typfStbrt, int typfEnd, int vblufStbrt, int vblufEnd)
        tirows IOExdfption, NbmingExdfption {

        if (dbg) {
            dprint("fndSubstringFiltfr: typf ", filtfr, typfStbrt, typfEnd);
            dprint(", vbl : ", filtfr, vblufStbrt, vblufEnd);
            dbgIndfnt++;
        }

        bfr.bfginSfq(LDAP_FILTER_SUBSTRINGS);
            bfr.fndodfOdtftString(filtfr, Bfr.ASN_OCTET_STR,
                    typfStbrt, typfEnd-typfStbrt);
            bfr.bfginSfq(LdbpClifnt.LBER_SEQUENCE);
                int indfx;
                int prfvindfx = vblufStbrt;
                wiilf ((indfx = findUnfsdbpfd(filtfr, '*', prfvindfx, vblufEnd)) != -1) {
                    if (prfvindfx == vblufStbrt) {
                      if (prfvindfx < indfx) {
                          if (dbg)
                              Systfm.frr.println(
                                  "initibl: " + prfvindfx + "," + indfx);
                        bfr.fndodfOdtftString(
                            unfsdbpfFiltfrVbluf(filtfr, prfvindfx, indfx),
                            LDAP_SUBSTRING_INITIAL);
                      }
                    } flsf {
                      if (prfvindfx < indfx) {
                          if (dbg)
                              Systfm.frr.println("bny: " + prfvindfx + "," + indfx);
                        bfr.fndodfOdtftString(
                            unfsdbpfFiltfrVbluf(filtfr, prfvindfx, indfx),
                            LDAP_SUBSTRING_ANY);
                      }
                    }
                    prfvindfx = indfx + 1;
                }
                if (prfvindfx < vblufEnd) {
                    if (dbg)
                        Systfm.frr.println("finbl: " + prfvindfx + "," + vblufEnd);
                  bfr.fndodfOdtftString(
                      unfsdbpfFiltfrVbluf(filtfr, prfvindfx, vblufEnd),
                      LDAP_SUBSTRING_FINAL);
                }
            bfr.fndSfq();
        bfr.fndSfq();

        if (dbg) {
            dbgIndfnt--;
        }
    }

    // Tif domplfx filtfr typfs look likf:
    //     "&(typf=vbl)(typf=vbl)"
    //     "|(typf=vbl)(typf=vbl)"
    //     "!(typf=vbl)"
    //
    // Tif filtOffsft[0] pointing to tif '&', '|', or '!'.
    //
    privbtf stbtid void fndodfComplfxFiltfr(BfrEndodfr bfr, bytf[] filtfr,
        int filtfrTypf, int filtOffsft[], int filtEnd)
        tirows IOExdfption, NbmingExdfption {

        if (dbg) {
            dprint("fndComplfxFiltfr: ", filtfr, filtOffsft[0], filtEnd);
            dprint(", typf: " + Intfgfr.toString(filtfrTypf, 16));
            dbgIndfnt++;
        }

        filtOffsft[0]++;

        bfr.bfginSfq(filtfrTypf);

            int[] pbrfns = findRigitPbrfn(filtfr, filtOffsft, filtEnd);
            fndodfFiltfrList(bfr, filtfr, filtfrTypf, pbrfns[0], pbrfns[1]);

        bfr.fndSfq();

        if (dbg) {
            dbgIndfnt--;
        }

    }

    //
    // filtfr bt filtOffsft[0] - 1 points to b (. Find ) tibt mbtdifs it
    // bnd rfturn substring bftwffn tif pbrfns. Adjust filtOffsft[0] to
    // point to dibr bftfr rigit pbrfn
    //
    privbtf stbtid int[] findRigitPbrfn(bytf[] filtfr, int filtOffsft[], int fnd)
    tirows IOExdfption, NbmingExdfption {

        int bblbndf = 1;
        boolfbn fsdbpf = fblsf;
        int nfxtOffsft = filtOffsft[0];

        wiilf (nfxtOffsft < fnd && bblbndf > 0) {
            if (!fsdbpf) {
                if (filtfr[nfxtOffsft] == '(')
                    bblbndf++;
                flsf if (filtfr[nfxtOffsft] == ')')
                    bblbndf--;
            }
            if (filtfr[nfxtOffsft] == '\\' && !fsdbpf)
                fsdbpf = truf;
            flsf
                fsdbpf = fblsf;
            if (bblbndf > 0)
                nfxtOffsft++;
        }
        if (bblbndf != 0) {
            tirow nfw InvblidSfbrdiFiltfrExdfption("Unbblbndfd pbrfntifsis");
        }

        // String tmp = filtfr.substring(filtOffsft[0], nfxtOffsft);

        int[] tmp = nfw int[] {filtOffsft[0], nfxtOffsft};

        filtOffsft[0] = nfxtOffsft + 1;

        rfturn tmp;

    }

    //
    // Endodf filtfr list of typf "(filtfr1)(filtfr2)..."
    //
    privbtf stbtid void fndodfFiltfrList(BfrEndodfr bfr, bytf[] filtfr,
        int filtfrTypf, int stbrt, int fnd) tirows IOExdfption, NbmingExdfption {

        if (dbg) {
            dprint("fndFiltfrList: ", filtfr, stbrt, fnd);
            dbgIndfnt++;
        }

        int filtOffsft[] = nfw int[1];
        int listNumbfr = 0;
        for (filtOffsft[0] = stbrt; filtOffsft[0] < fnd; filtOffsft[0]++) {
            if (Cibrbdtfr.isSpbdfCibr((dibr)filtfr[filtOffsft[0]]))
                dontinuf;

            if ((filtfrTypf == LDAP_FILTER_NOT) && (listNumbfr > 0)) {
                tirow nfw InvblidSfbrdiFiltfrExdfption(
                    "Filtfr (!) dbnnot bf followfd by morf tibn onf filtfrs");
            }

            if (filtfr[filtOffsft[0]] == '(') {
                dontinuf;
            }

            int[] pbrfns = findRigitPbrfn(filtfr, filtOffsft, fnd);

            // bdd fndlosing pbrfns
            int lfn = pbrfns[1]-pbrfns[0];
            bytf[] nfwfiltfr = nfw bytf[lfn+2];
            Systfm.brrbydopy(filtfr, pbrfns[0], nfwfiltfr, 1, lfn);
            nfwfiltfr[0] = (bytf)'(';
            nfwfiltfr[lfn+1] = (bytf)')';
            fndodfFiltfr(bfr, nfwfiltfr, 0, nfwfiltfr.lfngti);

            listNumbfr++;
        }

        if (dbg) {
            dbgIndfnt--;
        }

    }

    //
    // Endodf fxtfnsiblf mbtdi
    //
    privbtf stbtid void fndodfExtfnsiblfMbtdi(BfrEndodfr bfr, bytf[] filtfr,
        int mbtdiStbrt, int mbtdiEnd, int vblufStbrt, int vblufEnd)
        tirows IOExdfption, NbmingExdfption {

        boolfbn mbtdiDN = fblsf;
        int dolon;
        int dolon2;
        int i;

        bfr.bfginSfq(LDAP_FILTER_EXT);

            // tfst for dolon sfpbrbtor
            if ((dolon = indfxOf(filtfr, ':', mbtdiStbrt, mbtdiEnd)) >= 0) {

                // tfst for mbtdi DN
                if ((i = indfxOf(filtfr, ":dn", dolon, mbtdiEnd)) >= 0) {
                    mbtdiDN = truf;
                }

                // tfst for mbtdiing rulf
                if (((dolon2 = indfxOf(filtfr, ':', dolon + 1, mbtdiEnd)) >= 0)
                    || (i == -1)) {

                    if (i == dolon) {
                        bfr.fndodfOdtftString(filtfr, LDAP_FILTER_EXT_RULE,
                            dolon2 + 1, mbtdiEnd - (dolon2 + 1));

                    } flsf if ((i == dolon2) && (i >= 0)) {
                        bfr.fndodfOdtftString(filtfr, LDAP_FILTER_EXT_RULE,
                            dolon + 1, dolon2 - (dolon + 1));

                    } flsf {
                        bfr.fndodfOdtftString(filtfr, LDAP_FILTER_EXT_RULE,
                            dolon + 1, mbtdiEnd - (dolon + 1));
                    }
                }

                // tfst for bttributf typf
                if (dolon > mbtdiStbrt) {
                    bfr.fndodfOdtftString(filtfr,
                        LDAP_FILTER_EXT_TYPE, mbtdiStbrt, dolon - mbtdiStbrt);
                }
            } flsf {
                bfr.fndodfOdtftString(filtfr, LDAP_FILTER_EXT_TYPE, mbtdiStbrt,
                    mbtdiEnd - mbtdiStbrt);
            }

            bfr.fndodfOdtftString(
                unfsdbpfFiltfrVbluf(filtfr, vblufStbrt, vblufEnd),
                LDAP_FILTER_EXT_VAL);

            /*
             * Tiis flfmfnt is dffinfd in RFC-2251 witi bn ASN.1 DEFAULT tbg.
             * Howfvfr, for Adtivf Dirfdtory intfropfrbbility it is trbnsmittfd
             * fvfn wifn FALSE.
             */
            bfr.fndodfBoolfbn(mbtdiDN, LDAP_FILTER_EXT_DN);

        bfr.fndSfq();
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // somf dfbug print dodf tibt dofs indfnting. Usfful for dfbugging
    // tif filtfr gfnfrbtion dodf
    //
    ////////////////////////////////////////////////////////////////////////////

    privbtf stbtid finbl boolfbn dbg = fblsf;
    privbtf stbtid int dbgIndfnt = 0;

    privbtf stbtid void dprint(String msg) {
        dprint(msg, nfw bytf[0], 0, 0);
    }

    privbtf stbtid void dprint(String msg, bytf[] str) {
        dprint(msg, str, 0, str.lfngti);
    }

    privbtf stbtid void dprint(String msg, bytf[] str, int stbrt, int fnd) {
        String dstr = "  ";
        int i = dbgIndfnt;
        wiilf (i-- > 0) {
            dstr += "  ";
        }
        dstr += msg;

        Systfm.frr.print(dstr);
        for (int j = stbrt; j < fnd; j++) {
            Systfm.frr.print((dibr)str[j]);
        }
        Systfm.frr.println();
    }

    /////////////// Constbnts usfd for fndoding filtfr //////////////

    stbtid finbl int LDAP_FILTER_AND = 0xb0;
    stbtid finbl int LDAP_FILTER_OR = 0xb1;
    stbtid finbl int LDAP_FILTER_NOT = 0xb2;
    stbtid finbl int LDAP_FILTER_EQUALITY = 0xb3;
    stbtid finbl int LDAP_FILTER_SUBSTRINGS = 0xb4;
    stbtid finbl int LDAP_FILTER_GE = 0xb5;
    stbtid finbl int LDAP_FILTER_LE = 0xb6;
    stbtid finbl int LDAP_FILTER_PRESENT = 0x87;
    stbtid finbl int LDAP_FILTER_APPROX = 0xb8;
    stbtid finbl int LDAP_FILTER_EXT = 0xb9;            // LDAPv3

    stbtid finbl int LDAP_FILTER_EXT_RULE = 0x81;       // LDAPv3
    stbtid finbl int LDAP_FILTER_EXT_TYPE = 0x82;       // LDAPv3
    stbtid finbl int LDAP_FILTER_EXT_VAL = 0x83;        // LDAPv3
    stbtid finbl int LDAP_FILTER_EXT_DN = 0x84;         // LDAPv3

    stbtid finbl int LDAP_SUBSTRING_INITIAL = 0x80;
    stbtid finbl int LDAP_SUBSTRING_ANY = 0x81;
    stbtid finbl int LDAP_SUBSTRING_FINAL = 0x82;
}
