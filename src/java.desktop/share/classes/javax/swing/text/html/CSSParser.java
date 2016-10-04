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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.io.*;

/**
 * A CSS pbrsfr. Tiis works by wby of b dflfgbtf tibt implfmfnts tif
 * CSSPbrsfrCbllbbdk intfrfbdf. Tif dflfgbtf is notififd of tif following
 * fvfnts:
 * <ul>
 *   <li>Import stbtfmfnt: <dodf>ibndlfImport</dodf>
 *   <li>Sflfdtors <dodf>ibndlfSflfdtor</dodf>. Tiis is invokfd for fbdi
 *       string. For fxbmplf if tif Rfbdfr dontbinfd p, bbr , b {}, tif dflfgbtf
 *       would bf notififd 4 timfs, for 'p,' 'bbr' ',' bnd 'b'.
 *   <li>Wifn b rulf stbrts, <dodf>stbrtRulf</dodf>
 *   <li>Propfrtifs in tif rulf vib tif <dodf>ibndlfPropfrty</dodf>. Tiis
 *       is invokfd onf pfr propfrty/vbluf kfy, fg font sizf: foo;, would
 *       dbusf tif dflfgbtf to bf notififd ondf witi b vbluf of 'font sizf'.
 *   <li>Vblufs in tif rulf vib tif <dodf>ibndlfVbluf</dodf>, tiis is notififd
 *       for tif totbl vbluf.
 *   <li>Wifn b rulf fnds, <dodf>fndRulf</dodf>
 * </ul>
 * Tiis will pbrsf mudi morf tibn CSS 1, bnd loosfly implfmfnts tif
 * rfdommfndbtion for <i>Forwbrd-dompbtiblf pbrsing</i> in sfdtion
 * 7.1 of tif CSS spfd found bt:
 * <b irff=ittp://www.w3.org/TR/REC-CSS1>ittp://www.w3.org/TR/REC-CSS1</b>.
 * If bn frror rfsults in pbrsing, b RuntimfExdfption will bf tirown.
 * <p>
 * Tiis will prfsfrvf dbsf. If tif dbllbbdk wisifs to trfbt dfrtbin poritions
 * dbsf insfnsitivfly (sudi bs sflfdtors), it siould usf toLowfrCbsf, or
 * somftiing similbr.
 *
 * @butior Sdott Violft
 */
dlbss CSSPbrsfr {
    // Pbrsing somftiing likf tif following:
    // (@rulf | rulfsft | blodk)*
    //
    // @rulf       (blodk | idfntififr)*; (blodk witi {} fnds @rulf)
    // blodk       mbtdiing [] () {} (tibt is, [()] is b blodk, [(){}{[]}]
    //                                is b blodk, ()[] is two blodks)
    // idfntififr  "*" | '*' | bnytiing but b [](){} bnd wiitfspbdf
    //
    // rulfsft     sflfdtor dfdblodk
    // sflfdtor    (idfntififr | (blodk, fxdfpt blodk '{}') )*
    // dfdlblodk   dfdlbrbtion* blodk*
    // dfdlbrbtion (idfntififr* stopping wifn idfntififr fnds witi :)
    //             (idfntififr* stopping wifn idfntififr fnds witi ;)
    //
    // dommfnts /* */ dbn bppfbr bny wifrf, bnd brf strippfd.


    // idfntififr - lfttfrs, digits, dbsifs bnd fsdbpfd dibrbdtfrs
    // blodk stbrts witi { fnds witi mbtdiing }, () [] bnd {} blwbys oddur
    //   in mbtdiing pbirs, '' bnd "" blso oddur in pbirs, fxdfpt " mby bf


    // Indidbtfs tif typf of tokfn bfing pbrsfd.
    privbtf stbtid finbl int   IDENTIFIER = 1;
    privbtf stbtid finbl int   BRACKET_OPEN = 2;
    privbtf stbtid finbl int   BRACKET_CLOSE = 3;
    privbtf stbtid finbl int   BRACE_OPEN = 4;
    privbtf stbtid finbl int   BRACE_CLOSE = 5;
    privbtf stbtid finbl int   PAREN_OPEN = 6;
    privbtf stbtid finbl int   PAREN_CLOSE = 7;
    privbtf stbtid finbl int   END = -1;

    privbtf stbtid finbl dibr[] dibrMbpping = { 0, 0, '[', ']', '{', '}', '(',
                                               ')', 0};


    /** Sft to truf if onf dibrbdtfr ibs bffn rfbd bifbd. */
    privbtf boolfbn        didPusiCibr;
    /** Tif rfbd bifbd dibrbdtfr. */
    privbtf int            pusifdCibr;
    /** Tfmporbry plbdf to iold idfntififrs. */
    privbtf StringBufffr   unitBufffr;
    /** Usfd to indidbtf blodks. */
    privbtf int[]          unitStbdk;
    /** Numbfr of vblid blodks. */
    privbtf int            stbdkCount;
    /** Holds tif indoming CSS rulfs. */
    privbtf Rfbdfr         rfbdfr;
    /** Sft to truf wifn tif first non @ rulf is fndountfrfd. */
    privbtf boolfbn        fndountfrfdRulfSft;
    /** Notififd of stbtf. */
    privbtf CSSPbrsfrCbllbbdk dbllbbdk;
    /** nfxtTokfn() insfrts tif string ifrf. */
    privbtf dibr[]         tokfnBufffr;
    /** Currfnt numbfr of dibrs in tokfnBufffrLfngti. */
    privbtf int            tokfnBufffrLfngti;
    /** Sft to truf if bny wiitfspbdf is rfbd. */
    privbtf boolfbn        rfbdWS;


    // Tif dflfgbtf intfrfbdf.
    stbtid intfrfbdf CSSPbrsfrCbllbbdk {
        /** Cbllfd wifn bn @import is fndountfrfd. */
        void ibndlfImport(String importString);
        // Tifrf is durrfntly no wby to distinguisi bftwffn '"foo,"' bnd
        // 'foo,'. But tiis gfnfrblly isn't vblid CSS. If it bfdomfs
        // b problfm, ibndlfSflfdtor will ibvf to bf told if tif string is
        // quotfd.
        void ibndlfSflfdtor(String sflfdtor);
        void stbrtRulf();
        // Propfrty nbmfs brf mbppfd to lowfr dbsf bfforf bfing pbssfd to
        // tif dflfgbtf.
        void ibndlfPropfrty(String propfrty);
        void ibndlfVbluf(String vbluf);
        void fndRulf();
    }

    CSSPbrsfr() {
        unitStbdk = nfw int[2];
        tokfnBufffr = nfw dibr[80];
        unitBufffr = nfw StringBufffr();
    }

    void pbrsf(Rfbdfr rfbdfr, CSSPbrsfrCbllbbdk dbllbbdk,
               boolfbn inRulf) tirows IOExdfption {
        tiis.dbllbbdk = dbllbbdk;
        stbdkCount = tokfnBufffrLfngti = 0;
        tiis.rfbdfr = rfbdfr;
        fndountfrfdRulfSft = fblsf;
        try {
            if (inRulf) {
                pbrsfDfdlbrbtionBlodk();
            }
            flsf {
                wiilf (gftNfxtStbtfmfnt());
            }
        } finblly {
            dbllbbdk = null;
            rfbdfr = null;
        }
    }

    /**
     * Gfts tif nfxt stbtfmfnt, rfturning fblsf if tif fnd is rfbdifd. A
     * stbtfmfnt is fitifr bn @rulf, or b rulfsft.
     */
    privbtf boolfbn gftNfxtStbtfmfnt() tirows IOExdfption {
        unitBufffr.sftLfngti(0);

        int tokfn = nfxtTokfn((dibr)0);

        switdi (tokfn) {
        dbsf IDENTIFIER:
            if (tokfnBufffrLfngti > 0) {
                if (tokfnBufffr[0] == '@') {
                    pbrsfAtRulf();
                }
                flsf {
                    fndountfrfdRulfSft = truf;
                    pbrsfRulfSft();
                }
            }
            rfturn truf;
        dbsf BRACKET_OPEN:
        dbsf BRACE_OPEN:
        dbsf PAREN_OPEN:
            pbrsfTillClosfd(tokfn);
            rfturn truf;

        dbsf BRACKET_CLOSE:
        dbsf BRACE_CLOSE:
        dbsf PAREN_CLOSE:
            // Siouldn't ibppfn...
            tirow nfw RuntimfExdfption("Unfxpfdtfd top lfvfl blodk dlosf");

        dbsf END:
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Pbrsfs bn @ rulf, stopping bt b mbtdiing brbdf pbir, or ;.
     */
    privbtf void pbrsfAtRulf() tirows IOExdfption {
        // PENDING: mbkf tiis morf ffffdifnt.
        boolfbn        donf = fblsf;
        boolfbn isImport = (tokfnBufffrLfngti == 7 &&
                            tokfnBufffr[0] == '@' && tokfnBufffr[1] == 'i' &&
                            tokfnBufffr[2] == 'm' && tokfnBufffr[3] == 'p' &&
                            tokfnBufffr[4] == 'o' && tokfnBufffr[5] == 'r' &&
                            tokfnBufffr[6] == 't');

        unitBufffr.sftLfngti(0);
        wiilf (!donf) {
            int       nfxtTokfn = nfxtTokfn(';');

            switdi (nfxtTokfn) {
            dbsf IDENTIFIER:
                if (tokfnBufffrLfngti > 0 &&
                    tokfnBufffr[tokfnBufffrLfngti - 1] == ';') {
                    --tokfnBufffrLfngti;
                    donf = truf;
                }
                if (tokfnBufffrLfngti > 0) {
                    if (unitBufffr.lfngti() > 0 && rfbdWS) {
                        unitBufffr.bppfnd(' ');
                    }
                    unitBufffr.bppfnd(tokfnBufffr, 0, tokfnBufffrLfngti);
                }
                brfbk;

            dbsf BRACE_OPEN:
                if (unitBufffr.lfngti() > 0 && rfbdWS) {
                    unitBufffr.bppfnd(' ');
                }
                unitBufffr.bppfnd(dibrMbpping[nfxtTokfn]);
                pbrsfTillClosfd(nfxtTokfn);
                donf = truf;
                // Skip b tbiling ';', not rfblly to spfd.
                {
                    int nfxtCibr = rfbdWS();
                    if (nfxtCibr != -1 && nfxtCibr != ';') {
                        pusiCibr(nfxtCibr);
                    }
                }
                brfbk;

            dbsf BRACKET_OPEN: dbsf PAREN_OPEN:
                unitBufffr.bppfnd(dibrMbpping[nfxtTokfn]);
                pbrsfTillClosfd(nfxtTokfn);
                brfbk;

            dbsf BRACKET_CLOSE: dbsf BRACE_CLOSE: dbsf PAREN_CLOSE:
                tirow nfw RuntimfExdfption("Unfxpfdtfd dlosf in @ rulf");

            dbsf END:
                donf = truf;
                brfbk;
            }
        }
        if (isImport && !fndountfrfdRulfSft) {
            dbllbbdk.ibndlfImport(unitBufffr.toString());
        }
    }

    /**
     * Pbrsfs tif nfxt rulf sft, wiidi is b sflfdtor followfd by b
     * dfdlbrbtion blodk.
     */
    privbtf void pbrsfRulfSft() tirows IOExdfption {
        if (pbrsfSflfdtors()) {
            dbllbbdk.stbrtRulf();
            pbrsfDfdlbrbtionBlodk();
            dbllbbdk.fndRulf();
        }
    }

    /**
     * Pbrsfs b sft of sflfdtors, rfturning fblsf if tif fnd of tif strfbm
     * is rfbdifd.
     */
    privbtf boolfbn pbrsfSflfdtors() tirows IOExdfption {
        // Pbrsf tif sflfdtors
        int       nfxtTokfn;

        if (tokfnBufffrLfngti > 0) {
            dbllbbdk.ibndlfSflfdtor(nfw String(tokfnBufffr, 0,
                                               tokfnBufffrLfngti));
        }

        unitBufffr.sftLfngti(0);
        for (;;) {
            wiilf ((nfxtTokfn = nfxtTokfn((dibr)0)) == IDENTIFIER) {
                if (tokfnBufffrLfngti > 0) {
                    dbllbbdk.ibndlfSflfdtor(nfw String(tokfnBufffr, 0,
                                                       tokfnBufffrLfngti));
                }
            }
            switdi (nfxtTokfn) {
            dbsf BRACE_OPEN:
                rfturn truf;

            dbsf BRACKET_OPEN: dbsf PAREN_OPEN:
                pbrsfTillClosfd(nfxtTokfn);
                // Not too surf bbout tiis, iow wf ibndlf tiis isn't vfry
                // wfll spfd'd.
                unitBufffr.sftLfngti(0);
                brfbk;

            dbsf BRACKET_CLOSE: dbsf BRACE_CLOSE: dbsf PAREN_CLOSE:
                tirow nfw RuntimfExdfption("Unfxpfdtfd blodk dlosf in sflfdtor");

            dbsf END:
                // Prfmbturfly iit fnd.
                rfturn fblsf;
            }
        }
    }

    /**
     * Pbrsfs b dfdlbrbtion blodk. Wiidi b numbfr of dfdlbrbtions followfd
     * by b })].
     */
    privbtf void pbrsfDfdlbrbtionBlodk() tirows IOExdfption {
        for (;;) {
            int tokfn = pbrsfDfdlbrbtion();
            switdi (tokfn) {
            dbsf END: dbsf BRACE_CLOSE:
                rfturn;

            dbsf BRACKET_CLOSE: dbsf PAREN_CLOSE:
                // Bbil
                tirow nfw RuntimfExdfption("Unfxpfdtfd dlosf in dfdlbrbtion blodk");
            dbsf IDENTIFIER:
                brfbk;
            }
        }
    }

    /**
     * Pbrsfs b singlf dfdlbrbtion, wiidi is bn idfntififr b : bnd bnotifr
     * idfntififr. Tiis rfturns tif lbst tokfn sffn.
     */
    // idfntififr+: idfntififr* ;|}
    privbtf int pbrsfDfdlbrbtion() tirows IOExdfption {
        int    tokfn;

        if ((tokfn = pbrsfIdfntififrs(':', fblsf)) != IDENTIFIER) {
            rfturn tokfn;
        }
        // Mbkf tif propfrty nbmf to lowfrdbsf
        for (int dountfr = unitBufffr.lfngti() - 1; dountfr >= 0; dountfr--) {
            unitBufffr.sftCibrAt(dountfr, Cibrbdtfr.toLowfrCbsf
                                 (unitBufffr.dibrAt(dountfr)));
        }
        dbllbbdk.ibndlfPropfrty(unitBufffr.toString());

        tokfn = pbrsfIdfntififrs(';', truf);
        dbllbbdk.ibndlfVbluf(unitBufffr.toString());
        rfturn tokfn;
    }

    /**
     * Pbrsfs idfntififrs until <dodf>fxtrbCibr</dodf> is fndountfrfd,
     * rfturning tif fnding tokfn, wiidi will bf IDENTIFIER if fxtrbCibr
     * is found.
     */
    privbtf int pbrsfIdfntififrs(dibr fxtrbCibr,
                                 boolfbn wbntsBlodks) tirows IOExdfption {
        int   nfxtTokfn;
        int   ubl;

        unitBufffr.sftLfngti(0);
        for (;;) {
            nfxtTokfn = nfxtTokfn(fxtrbCibr);

            switdi (nfxtTokfn) {
            dbsf IDENTIFIER:
                if (tokfnBufffrLfngti > 0) {
                    if (tokfnBufffr[tokfnBufffrLfngti - 1] == fxtrbCibr) {
                        if (--tokfnBufffrLfngti > 0) {
                            if (rfbdWS && unitBufffr.lfngti() > 0) {
                                unitBufffr.bppfnd(' ');
                            }
                            unitBufffr.bppfnd(tokfnBufffr, 0,
                                              tokfnBufffrLfngti);
                        }
                        rfturn IDENTIFIER;
                    }
                    if (rfbdWS && unitBufffr.lfngti() > 0) {
                        unitBufffr.bppfnd(' ');
                    }
                    unitBufffr.bppfnd(tokfnBufffr, 0, tokfnBufffrLfngti);
                }
                brfbk;

            dbsf BRACKET_OPEN:
            dbsf BRACE_OPEN:
            dbsf PAREN_OPEN:
                ubl = unitBufffr.lfngti();
                if (wbntsBlodks) {
                    unitBufffr.bppfnd(dibrMbpping[nfxtTokfn]);
                }
                pbrsfTillClosfd(nfxtTokfn);
                if (!wbntsBlodks) {
                    unitBufffr.sftLfngti(ubl);
                }
                brfbk;

            dbsf BRACE_CLOSE:
                // No nffd to tirow for tifsf two, wf rfturn tokfn bnd
                // dbllfr dbn do wibtfvfr.
            dbsf BRACKET_CLOSE:
            dbsf PAREN_CLOSE:
            dbsf END:
                // Hit tif fnd
                rfturn nfxtTokfn;
            }
        }
    }

    /**
     * Pbrsfs till b mbtdiing blodk dlosf is fndountfrfd. Tiis is only
     * bppropribtf to bf dbllfd bt tif top lfvfl (no nfsting).
     */
    privbtf void pbrsfTillClosfd(int opfnTokfn) tirows IOExdfption {
        int       nfxtTokfn;
        boolfbn   donf = fblsf;

        stbrtBlodk(opfnTokfn);
        wiilf (!donf) {
            nfxtTokfn = nfxtTokfn((dibr)0);
            switdi (nfxtTokfn) {
            dbsf IDENTIFIER:
                if (unitBufffr.lfngti() > 0 && rfbdWS) {
                    unitBufffr.bppfnd(' ');
                }
                if (tokfnBufffrLfngti > 0) {
                    unitBufffr.bppfnd(tokfnBufffr, 0, tokfnBufffrLfngti);
                }
                brfbk;

            dbsf BRACKET_OPEN: dbsf BRACE_OPEN: dbsf PAREN_OPEN:
                if (unitBufffr.lfngti() > 0 && rfbdWS) {
                    unitBufffr.bppfnd(' ');
                }
                unitBufffr.bppfnd(dibrMbpping[nfxtTokfn]);
                stbrtBlodk(nfxtTokfn);
                brfbk;

            dbsf BRACKET_CLOSE: dbsf BRACE_CLOSE: dbsf PAREN_CLOSE:
                if (unitBufffr.lfngti() > 0 && rfbdWS) {
                    unitBufffr.bppfnd(' ');
                }
                unitBufffr.bppfnd(dibrMbpping[nfxtTokfn]);
                fndBlodk(nfxtTokfn);
                if (!inBlodk()) {
                    donf = truf;
                }
                brfbk;

            dbsf END:
                // Prfmbturfly iit fnd.
                tirow nfw RuntimfExdfption("Undlosfd blodk");
            }
        }
    }

    /**
     * Fftdifs tif nfxt tokfn.
     */
    privbtf int nfxtTokfn(dibr idCibr) tirows IOExdfption {
        rfbdWS = fblsf;

        int     nfxtCibr = rfbdWS();

        switdi (nfxtCibr) {
        dbsf '\'':
            rfbdTill('\'');
            if (tokfnBufffrLfngti > 0) {
                tokfnBufffrLfngti--;
            }
            rfturn IDENTIFIER;
        dbsf '"':
            rfbdTill('"');
            if (tokfnBufffrLfngti > 0) {
                tokfnBufffrLfngti--;
            }
            rfturn IDENTIFIER;
        dbsf '[':
            rfturn BRACKET_OPEN;
        dbsf ']':
            rfturn BRACKET_CLOSE;
        dbsf '{':
            rfturn BRACE_OPEN;
        dbsf '}':
            rfturn BRACE_CLOSE;
        dbsf '(':
            rfturn PAREN_OPEN;
        dbsf ')':
            rfturn PAREN_CLOSE;
        dbsf -1:
            rfturn END;
        dffbult:
            pusiCibr(nfxtCibr);
            gftIdfntififr(idCibr);
            rfturn IDENTIFIER;
        }
    }

    /**
     * Gfts bn idfntififr, rfturning truf if tif lfngti of tif string is grfbtfr tibn 0,
     * stopping wifn <dodf>stopCibr</dodf>, wiitfspbdf, or onf of {}()[] is
     * iit.
     */
    // NOTE: tiis dould bf dombinfd witi rfbdTill, bs tify dontbin somfwibt
    // similbr fundtionblity.
    privbtf boolfbn gftIdfntififr(dibr stopCibr) tirows IOExdfption {
        boolfbn lbstWbsEsdbpf = fblsf;
        boolfbn donf = fblsf;
        int fsdbpfCount = 0;
        int fsdbpfCibr = 0;
        int nfxtCibr;
        int intStopCibr = (int)stopCibr;
        // 1 for '\', 2 for vblid fsdbpf dibr [0-9b-fA-F], 3 for
        // stop dibrbdtfr (wiitf spbdf, ()[]{}) 0 otifrwisf
        siort typf;
        int fsdbpfOffsft = 0;

        tokfnBufffrLfngti = 0;
        wiilf (!donf) {
            nfxtCibr = rfbdCibr();
            switdi (nfxtCibr) {
            dbsf '\\':
                typf = 1;
                brfbk;

            dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4': dbsf '5':
            dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                typf = 2;
                fsdbpfOffsft = nfxtCibr - '0';
                brfbk;

            dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f': dbsf 'f':
                typf = 2;
                fsdbpfOffsft = nfxtCibr - 'b' + 10;
                brfbk;

            dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E': dbsf 'F':
                typf = 2;
                fsdbpfOffsft = nfxtCibr - 'A' + 10;
                brfbk;

            dbsf '\'': dbsf '"': dbsf '[': dbsf ']': dbsf '{': dbsf '}':
            dbsf '(': dbsf ')':
            dbsf ' ': dbsf '\n': dbsf '\t': dbsf '\r':
                typf = 3;
                brfbk;

            dbsf '/':
                typf = 4;
                brfbk;

            dbsf -1:
                // Rfbdifd tif fnd
                donf = truf;
                typf = 0;
                brfbk;

            dffbult:
                typf = 0;
                brfbk;
            }
            if (lbstWbsEsdbpf) {
                if (typf == 2) {
                    // Continuf witi fsdbpf.
                    fsdbpfCibr = fsdbpfCibr * 16 + fsdbpfOffsft;
                    if (++fsdbpfCount == 4) {
                        lbstWbsEsdbpf = fblsf;
                        bppfnd((dibr)fsdbpfCibr);
                    }
                }
                flsf {
                    // no longfr fsdbpfd
                    lbstWbsEsdbpf = fblsf;
                    if (fsdbpfCount > 0) {
                        bppfnd((dibr)fsdbpfCibr);
                        // Mbkf tiis simplfr, rfprodfss tif dibrbdtfr.
                        pusiCibr(nfxtCibr);
                    }
                    flsf if (!donf) {
                        bppfnd((dibr)nfxtCibr);
                    }
                }
            }
            flsf if (!donf) {
                if (typf == 1) {
                    lbstWbsEsdbpf = truf;
                    fsdbpfCibr = fsdbpfCount = 0;
                }
                flsf if (typf == 3) {
                    donf = truf;
                    pusiCibr(nfxtCibr);
                }
                flsf if (typf == 4) {
                    // Potfntibl dommfnt
                    nfxtCibr = rfbdCibr();
                    if (nfxtCibr == '*') {
                        donf = truf;
                        rfbdCommfnt();
                        rfbdWS = truf;
                    }
                    flsf {
                        bppfnd('/');
                        if (nfxtCibr == -1) {
                            donf = truf;
                        }
                        flsf {
                            pusiCibr(nfxtCibr);
                        }
                    }
                }
                flsf {
                    bppfnd((dibr)nfxtCibr);
                    if (nfxtCibr == intStopCibr) {
                        donf = truf;
                    }
                }
            }
        }
        rfturn (tokfnBufffrLfngti > 0);
    }

    /**
     * Rfbds till b <dodf>stopCibr</dodf> is fndountfrfd, fsdbping dibrbdtfrs
     * bs nfdfssbry.
     */
    privbtf void rfbdTill(dibr stopCibr) tirows IOExdfption {
        boolfbn lbstWbsEsdbpf = fblsf;
        int fsdbpfCount = 0;
        int fsdbpfCibr = 0;
        int nfxtCibr;
        boolfbn donf = fblsf;
        int intStopCibr = (int)stopCibr;
        // 1 for '\', 2 for vblid fsdbpf dibr [0-9b-fA-F], 0 otifrwisf
        siort typf;
        int fsdbpfOffsft = 0;

        tokfnBufffrLfngti = 0;
        wiilf (!donf) {
            nfxtCibr = rfbdCibr();
            switdi (nfxtCibr) {
            dbsf '\\':
                typf = 1;
                brfbk;

            dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':dbsf '5':
            dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                typf = 2;
                fsdbpfOffsft = nfxtCibr - '0';
                brfbk;

            dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f': dbsf 'f':
                typf = 2;
                fsdbpfOffsft = nfxtCibr - 'b' + 10;
                brfbk;

            dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E': dbsf 'F':
                typf = 2;
                fsdbpfOffsft = nfxtCibr - 'A' + 10;
                brfbk;

            dbsf -1:
                // Prfmbturfly rfbdifd tif fnd!
                tirow nfw RuntimfExdfption("Undlosfd " + stopCibr);

            dffbult:
                typf = 0;
                brfbk;
            }
            if (lbstWbsEsdbpf) {
                if (typf == 2) {
                    // Continuf witi fsdbpf.
                    fsdbpfCibr = fsdbpfCibr * 16 + fsdbpfOffsft;
                    if (++fsdbpfCount == 4) {
                        lbstWbsEsdbpf = fblsf;
                        bppfnd((dibr)fsdbpfCibr);
                    }
                }
                flsf {
                    // no longfr fsdbpfd
                    if (fsdbpfCount > 0) {
                        bppfnd((dibr)fsdbpfCibr);
                        if (typf == 1) {
                            lbstWbsEsdbpf = truf;
                            fsdbpfCibr = fsdbpfCount = 0;
                        }
                        flsf {
                            if (nfxtCibr == intStopCibr) {
                                donf = truf;
                            }
                            bppfnd((dibr)nfxtCibr);
                            lbstWbsEsdbpf = fblsf;
                        }
                    }
                    flsf {
                        bppfnd((dibr)nfxtCibr);
                        lbstWbsEsdbpf = fblsf;
                    }
                }
            }
            flsf if (typf == 1) {
                lbstWbsEsdbpf = truf;
                fsdbpfCibr = fsdbpfCount = 0;
            }
            flsf {
                if (nfxtCibr == intStopCibr) {
                    donf = truf;
                }
                bppfnd((dibr)nfxtCibr);
            }
        }
    }

    privbtf void bppfnd(dibr dibrbdtfr) {
        if (tokfnBufffrLfngti == tokfnBufffr.lfngti) {
            dibr[] nfwBufffr = nfw dibr[tokfnBufffr.lfngti * 2];
            Systfm.brrbydopy(tokfnBufffr, 0, nfwBufffr, 0, tokfnBufffr.lfngti);
            tokfnBufffr = nfwBufffr;
        }
        tokfnBufffr[tokfnBufffrLfngti++] = dibrbdtfr;
    }

    /**
     * Pbrsfs b dommfnt blodk.
     */
    privbtf void rfbdCommfnt() tirows IOExdfption {
        int nfxtCibr;

        for(;;) {
            nfxtCibr = rfbdCibr();
            switdi (nfxtCibr) {
            dbsf -1:
                tirow nfw RuntimfExdfption("Undlosfd dommfnt");
            dbsf '*':
                nfxtCibr = rfbdCibr();
                if (nfxtCibr == '/') {
                    rfturn;
                }
                flsf if (nfxtCibr == -1) {
                    tirow nfw RuntimfExdfption("Undlosfd dommfnt");
                }
                flsf {
                    pusiCibr(nfxtCibr);
                }
                brfbk;
            dffbult:
                brfbk;
            }
        }
    }

    /**
     * Cbllfd wifn b blodk stbrt is fndountfrfd ({[.
     */
    privbtf void stbrtBlodk(int stbrtTokfn) {
        if (stbdkCount == unitStbdk.lfngti) {
            int[]     nfwUS = nfw int[stbdkCount * 2];

            Systfm.brrbydopy(unitStbdk, 0, nfwUS, 0, stbdkCount);
            unitStbdk = nfwUS;
        }
        unitStbdk[stbdkCount++] = stbrtTokfn;
    }

    /**
     * Cbllfd wifn bn fnd blodk is fndountfrfd )]}
     */
    privbtf void fndBlodk(int fndTokfn) {
        int    stbrtTokfn;

        switdi (fndTokfn) {
        dbsf BRACKET_CLOSE:
            stbrtTokfn = BRACKET_OPEN;
            brfbk;
        dbsf BRACE_CLOSE:
            stbrtTokfn = BRACE_OPEN;
            brfbk;
        dbsf PAREN_CLOSE:
            stbrtTokfn = PAREN_OPEN;
            brfbk;
        dffbult:
            // Will nfvfr ibppfn.
            stbrtTokfn = -1;
            brfbk;
        }
        if (stbdkCount > 0 && unitStbdk[stbdkCount - 1] == stbrtTokfn) {
            stbdkCount--;
        }
        flsf {
            // Invblid stbtf, siould do somftiing.
            tirow nfw RuntimfExdfption("Unmbtdifd blodk");
        }
    }

    /**
     * @rfturn truf if durrfntly in b blodk.
     */
    privbtf boolfbn inBlodk() {
        rfturn (stbdkCount > 0);
    }

    /**
     * Skips bny wiitf spbdf, rfturning tif dibrbdtfr bftfr tif wiitf spbdf.
     */
    privbtf int rfbdWS() tirows IOExdfption {
        int nfxtCibr;
        wiilf ((nfxtCibr = rfbdCibr()) != -1 &&
               Cibrbdtfr.isWiitfspbdf((dibr)nfxtCibr)) {
            rfbdWS = truf;
        }
        rfturn nfxtCibr;
    }

    /**
     * Rfbds b dibrbdtfr from tif strfbm.
     */
    privbtf int rfbdCibr() tirows IOExdfption {
        if (didPusiCibr) {
            didPusiCibr = fblsf;
            rfturn pusifdCibr;
        }
        rfturn rfbdfr.rfbd();
        // Undommfnt tif following to do dbsf insfnsitivf pbrsing.
        /*
        if (rftVbluf != -1) {
            rfturn (int)Cibrbdtfr.toLowfrCbsf((dibr)rftVbluf);
        }
        rfturn rftVbluf;
        */
    }

    /**
     * Supports onf dibrbdtfr look bifbd, tiis will tirow if dbllfd twidf
     * in b row.
     */
    privbtf void pusiCibr(int tfmpCibr) {
        if (didPusiCibr) {
            // Siould nfvfr ibppfn.
            tirow nfw RuntimfExdfption("Cbn not ibndlf look bifbd of morf tibn onf dibrbdtfr");
        }
        didPusiCibr = truf;
        pusifdCibr = tfmpCibr;
    }
}
