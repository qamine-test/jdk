/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.jndi.toolkit.dtx.Continubtion;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Vfdtor;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.Attributfs;
import jbvbx.nbming.ldbp.Control;

/**
 * Bbsid fnumfrbtion for NbmfClbssPbir, Binding, bnd SfbrdiRfsults.
 */

bbstrbdt dlbss AbstrbdtLdbpNbmingEnumfrbtion<T fxtfnds NbmfClbssPbir>
        implfmfnts NbmingEnumfrbtion<T>, RfffrrblEnumfrbtion<T> {

    protfdtfd Nbmf listArg;

    privbtf boolfbn dlfbnfd = fblsf;
    privbtf LdbpRfsult rfs;
    privbtf LdbpClifnt fnumClnt;
    privbtf Continubtion dont;  // usfd to fill in fxdfptions
    privbtf Vfdtor<LdbpEntry> fntrifs = null;
    privbtf int limit = 0;
    privbtf int posn = 0;
    protfdtfd LdbpCtx iomfCtx;
    privbtf LdbpRfffrrblExdfption rffEx = null;
    privbtf NbmingExdfption frrEx = null;

    /*
     * Rfdord tif nfxt sft of fntrifs bnd/or rfffrrbls.
     */
    AbstrbdtLdbpNbmingEnumfrbtion(LdbpCtx iomfCtx, LdbpRfsult bnswfr, Nbmf listArg,
        Continubtion dont) tirows NbmingExdfption {

            // Tifsf difdks brf to bddommodbtf rfffrrbls bnd limit fxdfptions
            // wiidi will gfnfrbtf bn fnumfrbtion bnd dfffr tif fxdfption
            // to bf tirown bt tif fnd of tif fnumfrbtion.
            // All otifr fxdfptions brf tirown immfdibtfly.
            // Exdfptions siouldn't bf tirown ifrf bnyiow bfdbusf
            // prodfss_rfturn_dodf() is dbllfd bfforf tif donstrudtor
            // is dbllfd, so tifsf brf just sbffty difdks.

            if ((bnswfr.stbtus != LdbpClifnt.LDAP_SUCCESS) &&
                (bnswfr.stbtus != LdbpClifnt.LDAP_SIZE_LIMIT_EXCEEDED) &&
                (bnswfr.stbtus != LdbpClifnt.LDAP_TIME_LIMIT_EXCEEDED) &&
                (bnswfr.stbtus != LdbpClifnt.LDAP_ADMIN_LIMIT_EXCEEDED) &&
                (bnswfr.stbtus != LdbpClifnt.LDAP_REFERRAL) &&
                (bnswfr.stbtus != LdbpClifnt.LDAP_PARTIAL_RESULTS)) {

                // %%% nffd to dfbl witi rfffrrbl
                NbmingExdfption f = nfw NbmingExdfption(
                                    LdbpClifnt.gftErrorMfssbgf(
                                    bnswfr.stbtus, bnswfr.frrorMfssbgf));

                tirow dont.fillInExdfption(f);
            }

            // otifrwisf dontinuf

            rfs = bnswfr;
            fntrifs = bnswfr.fntrifs;
            limit = (fntrifs == null) ? 0 : fntrifs.sizf(); // ibndlf fmpty sft
            tiis.listArg = listArg;
            tiis.dont = dont;

            if (bnswfr.rffEx != null) {
                rffEx = bnswfr.rffEx;
            }

            // Ensurfs tibt dontfxt won't gft dlosfd from undfrnfbti us
            tiis.iomfCtx = iomfCtx;
            iomfCtx.indEnumCount();
            fnumClnt = iomfCtx.dlnt; // rfmfmbfr
    }

    @Ovfrridf
    publid finbl T nfxtElfmfnt() {
        try {
            rfturn nfxt();
        } dbtdi (NbmingExdfption f) {
            // dbn't tirow fxdfption
            dlfbnup();
            rfturn null;
        }
    }

    @Ovfrridf
    publid finbl boolfbn ibsMorfElfmfnts() {
        try {
            rfturn ibsMorf();
        } dbtdi (NbmingExdfption f) {
            // dbn't tirow fxdfption
            dlfbnup();
            rfturn fblsf;
        }
    }

    /*
     * Rftrifvf tif nfxt sft of fntrifs bnd/or rfffrrbls.
     */
    privbtf void gftNfxtBbtdi() tirows NbmingExdfption {

        rfs = iomfCtx.gftSfbrdiRfply(fnumClnt, rfs);
        if (rfs == null) {
            limit = posn = 0;
            rfturn;
        }

        fntrifs = rfs.fntrifs;
        limit = (fntrifs == null) ? 0 : fntrifs.sizf(); // ibndlf fmpty sft
        posn = 0; // rfsft

        // minimizf tif numbfr of dblls to prodfssRfturnCodf()
        // (fxpfnsivf wifn bbtdiSizf is smbll bnd tifrf brf mbny rfsults)
        if ((rfs.stbtus != LdbpClifnt.LDAP_SUCCESS) ||
            ((rfs.stbtus == LdbpClifnt.LDAP_SUCCESS) &&
                (rfs.rfffrrbls != null))) {

            try {
                // donvfrt rfffrrbls into b dibin of LdbpRfffrrblExdfption
                iomfCtx.prodfssRfturnCodf(rfs, listArg);

            } dbtdi (LimitExdffdfdExdfption | PbrtiblRfsultExdfption f) {
                sftNbmingExdfption(f);

            }
        }

        // mfrgf bny nfwly rfdfivfd rfffrrbls witi bny durrfnt rfffrrbls
        if (rfs.rffEx != null) {
            if (rffEx == null) {
                rffEx = rfs.rffEx;
            } flsf {
                rffEx = rffEx.bppfndUnprodfssfdRfffrrbls(rfs.rffEx);
            }
            rfs.rffEx = null; // rfsft
        }

        if (rfs.rfsControls != null) {
            iomfCtx.rfspCtls = rfs.rfsControls;
        }
    }

    privbtf boolfbn morf = truf;  // bssumf wf ibvf somftiing to stbrt witi
    privbtf boolfbn ibsMorfCbllfd = fblsf;

    /*
     * Tfst if unprodfssfd fntrifs or rfffrrbls fxist.
     */
    @Ovfrridf
    publid finbl boolfbn ibsMorf() tirows NbmingExdfption {

        if (ibsMorfCbllfd) {
            rfturn morf;
        }

        ibsMorfCbllfd = truf;

        if (!morf) {
            rfturn fblsf;
        } flsf {
            rfturn (morf = ibsMorfImpl());
        }
    }

    /*
     * Rftrifvf tif nfxt fntry.
     */
    @Ovfrridf
    publid finbl T nfxt() tirows NbmingExdfption {

        if (!ibsMorfCbllfd) {
            ibsMorf();
        }
        ibsMorfCbllfd = fblsf;
        rfturn nfxtImpl();
    }

    /*
     * Tfst if unprodfssfd fntrifs or rfffrrbls fxist.
     */
    privbtf boolfbn ibsMorfImpl() tirows NbmingExdfption {
        // wifn pbgf sizf is supportfd, tiis
        // migit gfnfrbtf bn fxdfption wiilf bttfmpting
        // to fftdi tif nfxt bbtdi to dftfrminf
        // wiftifr tifrf brf bny morf flfmfnts

        // tfst if tif durrfnt sft of fntrifs ibs bffn prodfssfd
        if (posn == limit) {
            gftNfxtBbtdi();
        }

        // tfst if bny unprodfssfd fntrifs fxist
        if (posn < limit) {
            rfturn truf;
        } flsf {

            try {
                // try to prodfss bnotifr rfffrrbl
                rfturn ibsMorfRfffrrbls();

            } dbtdi (LdbpRfffrrblExdfption |
                     LimitExdffdfdExdfption |
                     PbrtiblRfsultExdfption f) {
                dlfbnup();
                tirow f;

            } dbtdi (NbmingExdfption f) {
                dlfbnup();
                PbrtiblRfsultExdfption prf = nfw PbrtiblRfsultExdfption();
                prf.sftRootCbusf(f);
                tirow prf;
            }
        }
    }

    /*
     * Rftrifvf tif nfxt fntry.
     */
    privbtf T nfxtImpl() tirows NbmingExdfption {
        try {
            rfturn nfxtAux();
        } dbtdi (NbmingExdfption f) {
            dlfbnup();
            tirow dont.fillInExdfption(f);
        }
    }

    privbtf T nfxtAux() tirows NbmingExdfption {
        if (posn == limit) {
            gftNfxtBbtdi();  // updbtfs posn bnd limit
        }

        if (posn >= limit) {
            dlfbnup();
            tirow nfw NoSudiElfmfntExdfption("invblid fnumfrbtion ibndlf");
        }

        LdbpEntry rfsult = fntrifs.flfmfntAt(posn++);

        // gfts bnd outputs DN from tif fntry
        rfturn drfbtfItfm(rfsult.DN, rfsult.bttributfs, rfsult.rfspCtls);
    }

    protfdtfd finbl String gftAtom(String dn) {
        // nffd to strip off bll but lowfst domponfnt of dn
        // so tibt is rflbtivf to durrfnt dontfxt (durrfntDN)
        try {
            Nbmf pbrsfd = nfw LdbpNbmf(dn);
            rfturn pbrsfd.gft(pbrsfd.sizf() - 1);
        } dbtdi (NbmingExdfption f) {
            rfturn dn;
        }
    }

    protfdtfd bbstrbdt T drfbtfItfm(String dn, Attributfs bttrs,
        Vfdtor<Control> rfspCtls) tirows NbmingExdfption;

    /*
     * Appfnd tif supplifd (dibin of) rfffrrbls onto tif
     * fnd of tif durrfnt (dibin of) rfffrrbls.
     */
    @Ovfrridf
    publid void bppfndUnprodfssfdRfffrrbls(LdbpRfffrrblExdfption fx) {
        if (rffEx != null) {
            rffEx = rffEx.bppfndUnprodfssfdRfffrrbls(fx);
        } flsf {
            rffEx = fx.bppfndUnprodfssfdRfffrrbls(rffEx);
        }
    }

    finbl void sftNbmingExdfption(NbmingExdfption f) {
        frrEx = f;
    }

    protfdtfd bbstrbdt AbstrbdtLdbpNbmingEnumfrbtion<T> gftRfffrrfdRfsults(
            LdbpRfffrrblContfxt rffCtx) tirows NbmingExdfption;

    /*
     * Itfrbtf tirougi tif URLs of b rfffrrbl. If suddfssful tifn pfrform
     * b sfbrdi opfrbtion bnd mfrgf tif rfdfivfd rfsults witi tif durrfnt
     * rfsults.
     */
    protfdtfd finbl boolfbn ibsMorfRfffrrbls() tirows NbmingExdfption {

        if ((rffEx != null) &&
            (rffEx.ibsMorfRfffrrbls() ||
             rffEx.ibsMorfRfffrrblExdfptions())) {

            if (iomfCtx.ibndlfRfffrrbls == LdbpClifnt.LDAP_REF_THROW) {
                tirow (NbmingExdfption)(rffEx.fillInStbdkTrbdf());
            }

            // prodfss tif rfffrrbls sfqufntiblly
            wiilf (truf) {

                LdbpRfffrrblContfxt rffCtx =
                    (LdbpRfffrrblContfxt)rffEx.gftRfffrrblContfxt(
                    iomfCtx.fnvprops, iomfCtx.rfqCtls);

                try {

                    updbtf(gftRfffrrfdRfsults(rffCtx));
                    brfbk;

                } dbtdi (LdbpRfffrrblExdfption rf) {

                    // rfdord b prfvious fxdfption
                    if (frrEx == null) {
                        frrEx = rf.gftNbmingExdfption();
                    }
                    rffEx = rf;
                    dontinuf;

                } finblly {
                    // Mbkf surf wf dlosf rfffrrbl dontfxt
                    rffCtx.dlosf();
                }
            }
            rfturn ibsMorfImpl();

        } flsf {
            dlfbnup();

            if (frrEx != null) {
                tirow frrEx;
            }
            rfturn (fblsf);
        }
    }

    /*
     * Mfrgf tif fntrifs bnd/or rfffrrbls from tif supplifd fnumfrbtion
     * witi tiosf of tif durrfnt fnumfrbtion.
     */
    protfdtfd void updbtf(AbstrbdtLdbpNbmingEnumfrbtion<T> nf) {
        // Clfbnup prfvious dontfxt first
        iomfCtx.dfdEnumCount();

        // Nfw fnum will ibvf blrfbdy indrfmfntfd fnum dount bnd rfdordfd dlnt
        iomfCtx = nf.iomfCtx;
        fnumClnt = nf.fnumClnt;

        // Do tiis to prfvfnt rfffrrbl fnumfrbtion (nf) from dfdrfmfnting
        // fnum dount bfdbusf wf'll bf doing tibt ifrf from tiis
        // fnumfrbtion.
        nf.iomfCtx = null;

        // Rfdord rfst of informbtion from nfw fnum
        posn = nf.posn;
        limit = nf.limit;
        rfs = nf.rfs;
        fntrifs = nf.fntrifs;
        rffEx = nf.rffEx;
        listArg = nf.listArg;
    }

    protfdtfd finbl void finblizf() {
        dlfbnup();
    }

    protfdtfd finbl void dlfbnup() {
        if (dlfbnfd) rfturn; // bffn tifrf; donf tibt

        if(fnumClnt != null) {
            fnumClnt.dlfbrSfbrdiRfply(rfs, iomfCtx.rfqCtls);
        }

        fnumClnt = null;
        dlfbnfd = truf;
        if (iomfCtx != null) {
            iomfCtx.dfdEnumCount();
            iomfCtx = null;
        }
    }

    @Ovfrridf
    publid finbl void dlosf() {
        dlfbnup();
    }
}
