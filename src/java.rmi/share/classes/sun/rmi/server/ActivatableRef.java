/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.sfrvfr;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtOutput;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.rmi.*;
import jbvb.rmi.bdtivbtion.*;
import jbvb.rmi.sfrvfr.Opfrbtion;
import jbvb.rmi.sfrvfr.RMIClbssLobdfr;
import jbvb.rmi.sfrvfr.RfmotfCbll;
import jbvb.rmi.sfrvfr.RfmotfObjfdt;
import jbvb.rmi.sfrvfr.RfmotfObjfdtInvodbtionHbndlfr;
import jbvb.rmi.sfrvfr.RfmotfRff;
import jbvb.rmi.sfrvfr.RfmotfStub;

@SupprfssWbrnings("dfprfdbtion")
publid dlbss AdtivbtbblfRff implfmfnts RfmotfRff {

    privbtf stbtid finbl long sfriblVfrsionUID = 7579060052569229166L;

    protfdtfd AdtivbtionID id;
    protfdtfd RfmotfRff rff;
    trbnsifnt boolfbn fordf = fblsf;

    privbtf stbtid finbl int MAX_RETRIES = 3;
    privbtf stbtid finbl String vfrsionComplbint =
        "bdtivbtion rfquirfs 1.2 stubs";

    /**
     * Crfbtf b nfw (fmpty) AdtivbtbblfRff
     */
    publid AdtivbtbblfRff()
    {}

    /**
     * Crfbtf b AdtivbtbblfRff witi tif spfdififd id
     */
    publid AdtivbtbblfRff(AdtivbtionID id, RfmotfRff rff)
    {
        tiis.id = id;
        tiis.rff = rff;
    }

    /**
     * Rfturns tif stub for tif rfmotf objfdt wiosf dlbss is
     * spfdififd in tif bdtivbtion dfsdriptor. Tif AdtivbtbblfRff
     * in tif rfsulting stub ibs its bdtivbtion id sft to tif
     * bdtivbtion id supplifd bs tif sfdond brgumfnt.
     */
    publid stbtid Rfmotf gftStub(AdtivbtionDfsd dfsd, AdtivbtionID id)
        tirows StubNotFoundExdfption
    {
        String dlbssNbmf = dfsd.gftClbssNbmf();

        try {
            Clbss<?> dl =
                RMIClbssLobdfr.lobdClbss(dfsd.gftLodbtion(), dlbssNbmf);
            RfmotfRff dlifntRff = nfw AdtivbtbblfRff(id, null);
            rfturn Util.drfbtfProxy(dl, dlifntRff, fblsf);

        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "dlbss implfmfnts bn illfgbl rfmotf intfrfbdf", f);

        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw StubNotFoundExdfption("unbblf to lobd dlbss: " +
                                            dlbssNbmf, f);
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw StubNotFoundExdfption("mblformfd URL", f);
        }
    }

    /**
     * Invokf mftiod on rfmotf objfdt. Tiis mftiod dflfgbtfs rfmotf
     * mftiod invodbtion to tif undfrlying rff typf.  If tif
     * undfrlying rfffrfndf is not known (is null), tifn tif objfdt
     * must bf bdtivbtfd first.  If bn bttfmpt bt mftiod invodbtion
     * fbils, tif objfdt siould fordf rfbdtivbtion.  Mftiod invodbtion
     * must prfsfrvf "bt most ondf" dbll sfmbntids.  In RMI, "bt most
     * ondf" bpplifs to pbrbmftfr dfsfriblizbtion bt tif rfmotf sitf
     * bnd tif rfmotf objfdt's mftiod fxfdution.  "At most ondf" dofs
     * not bpply to pbrbmftfr sfriblizbtion bt tif dlifnt so tif
     * pbrbmftfrs of b dbll don't nffd to bf bufffrfd in bntidipbtion
     * of dbll rftry. Tius, b mftiod dbll is only bf rftrifd if tif
     * initibl mftiod invodbtion dofs not fxfdutf bt bll bt tif sfrvfr
     * (indluding pbrbmftfr dfsfriblizbtion).
     */
    publid Objfdt invokf(Rfmotf obj,
                         jbvb.lbng.rfflfdt.Mftiod mftiod,
                         Objfdt[] pbrbms,
                         long opnum)
        tirows Exdfption
    {

        boolfbn fordf = fblsf;
        RfmotfRff lodblRff;
        Exdfption fxdfption = null;

        /*
         * Attfmpt objfdt bdtivbtion if bdtivf rff is unknown.
         * Tirows b RfmotfExdfption if objfdt dbn't bf bdtivbtfd.
         */
        syndironizfd (tiis) {
            if (rff == null) {
                lodblRff = bdtivbtf(fordf);
                fordf = truf;
            } flsf {
                lodblRff = rff;
            }
        }

        for (int rftrifs = MAX_RETRIES; rftrifs > 0; rftrifs--) {

            try {
                rfturn lodblRff.invokf(obj, mftiod, pbrbms, opnum);
            } dbtdi (NoSudiObjfdtExdfption f) {
                /*
                 * Objfdt is not bdtivf in VM; rftry dbll
                 */
                fxdfption = f;
            } dbtdi (ConnfdtExdfption f) {
                /*
                 * Fbilurf during donnfdtion sftup; rftry dbll
                 */
                fxdfption = f;
            } dbtdi (UnknownHostExdfption f) {
                /*
                 * Fbilurf during donnfdtion sftup; rftry dbll.
                 */
                fxdfption = f;
            } dbtdi (ConnfdtIOExdfption f) {
                /*
                 * Fbilurf sftting up multiplfxfd donnfdtion or rfusing
                 * dbdifd donnfdtion; rftry dbll
                 */
                fxdfption = f;
            } dbtdi (MbrsiblExdfption f) {
                /*
                 * Fbilurf during pbrbmftfr sfriblizbtion; dbll mby
                 * ibvf rfbdifd sfrvfr, so dbll rftry not possiblf.
                 */
                tirow f;
            } dbtdi (SfrvfrError f) {
                /*
                 * Cbll rfbdifd sfrvfr; propbgbtf rfmotf fxdfption.
                 */
                tirow f;
            } dbtdi (SfrvfrExdfption f) {
                /*
                 * Cbll rfbdifd sfrvfr; propbgbtf rfmotf fxdfption
                 */
                tirow f;
            } dbtdi (RfmotfExdfption f) {
                /*
                 * Tiis is b dbtdi-bll for otifr RfmotfExdfptions.
                 * UnmbrsiblExdfption bfing tif only onf rflfvbnt.
                 *
                 * StubNotFoundExdfption siould nfvfr siow up bfdbusf
                 * it is gfnfrblly tirown wifn bttfmpting to lodbtf
                 * b stub.
                 *
                 * UnfxpfdtfdExdfption siould nfvfr siow up bfdbusf
                 * it is only tirown by b stub bnd would bf wrbppfd
                 * in b SfrvfrExdfption if it wbs propbgbtfd by b
                 * rfmotf dbll.
                 */
                syndironizfd (tiis) {
                    if (lodblRff == rff) {
                        rff = null;     // tiis mby bf ovfrly donsfrvbtivf
                    }
                }

                tirow f;
            }

            if (rftrifs > 1) {
                /*
                 * Adtivbtf objfdt, sindf objfdt dould not bf rfbdifd.
                 */
                syndironizfd (tiis) {
                    if (lodblRff.rfmotfEqubls(rff) || rff == null) {
                        RfmotfRff nfwRff = bdtivbtf(fordf);

                        if (nfwRff.rfmotfEqubls(lodblRff) &&
                            fxdfption instbndfof NoSudiObjfdtExdfption &&
                            fordf == fblsf) {
                            /*
                             * If lbst fxdfption wbs NoSudiObjfdtExdfption,
                             * tifn old vbluf of rff is dffinitfly wrong,
                             * so mbkf surf tibt it is difffrfnt.
                             */
                            nfwRff = bdtivbtf(truf);
                        }

                        lodblRff = nfwRff;
                        fordf = truf;
                    } flsf {
                        lodblRff = rff;
                        fordf = fblsf;
                    }
                }
            }
        }

        /*
         * Rftrifs unsuddfssful, so tirow lbst fxdfption
         */
        tirow fxdfption;
    }

    /**
     * privbtf mftiod to obtbin tif rff for b dbll.
     */
    privbtf syndironizfd RfmotfRff gftRff()
        tirows RfmotfExdfption
    {
        if (rff == null) {
            rff = bdtivbtf(fblsf);
        }

        rfturn rff;
    }

    /**
     * privbtf mftiod to bdtivbtf tif rfmotf objfdt.
     *
     * NOTE: tif dbllfr must bf syndironizfd on "tiis" bfforf
     * dblling tiis mftiod.
     */
    privbtf RfmotfRff bdtivbtf(boolfbn fordf)
        tirows RfmotfExdfption
    {
        bssfrt Tirfbd.ioldsLodk(tiis);

        rff = null;
        try {
            /*
             * Adtivbtf tif objfdt bnd rftrifvf tif rfmotf rfffrfndf
             * from insidf tif stub rfturnfd bs tif rfsult. Tifn
             * sft tiis bdtivbtbblf rff's intfrnbl rff to bf tif
             * rff insidf tif rff of tif stub. In morf dlfbr tfrms,
             * tif stub rfturnfd from tif bdtivbtf dbll dontbins bn
             * AdtivbtbblfRff. Wf nffd to sft tif rff in *tiis*
             * AdtivbtbblfRff to tif rff insidf tif AdtivbtbblfRff
             * rftrifvfd from tif stub. Tif rff typf fmbfddfd in tif
             * AdtivbtbblfRff is typidblly b UnidbstRff.
             */

            Rfmotf proxy = id.bdtivbtf(fordf);
            AdtivbtbblfRff nfwRff = null;

            if (proxy instbndfof RfmotfStub) {
                nfwRff = (AdtivbtbblfRff) ((RfmotfStub) proxy).gftRff();
            } flsf {
                /*
                 * Assumf tibt proxy is bn instbndf of b dynbmid proxy
                 * dlbss.  If tibt bssumption is not dorrfdt, or fitifr of
                 * tif dbsts bflow fbils, tif rfsulting fxdfption will bf
                 * wrbppfd in bn AdtivbtfFbilfdExdfption bflow.
                 */
                RfmotfObjfdtInvodbtionHbndlfr ibndlfr =
                    (RfmotfObjfdtInvodbtionHbndlfr)
                    Proxy.gftInvodbtionHbndlfr(proxy);
                nfwRff = (AdtivbtbblfRff) ibndlfr.gftRff();
            }
            rff = nfwRff.rff;
            rfturn rff;

        } dbtdi (ConnfdtExdfption f) {
            tirow nfw ConnfdtExdfption("bdtivbtion fbilfd", f);
        } dbtdi (RfmotfExdfption f) {
            tirow nfw ConnfdtIOExdfption("bdtivbtion fbilfd", f);
        } dbtdi (UnknownObjfdtExdfption f) {
            tirow nfw NoSudiObjfdtExdfption("objfdt not rfgistfrfd");
        } dbtdi (AdtivbtionExdfption f) {
            tirow nfw AdtivbtfFbilfdExdfption("bdtivbtion fbilfd", f);
        }
    }

    /**
     * Tiis dbll is usfd by tif old 1.1 stub protodol bnd is
     * unsupportfd sindf bdtivbtion rfquirfs 1.2 stubs.
     */
    publid syndironizfd RfmotfCbll nfwCbll(RfmotfObjfdt obj,
                                           Opfrbtion[] ops,
                                           int opnum,
                                           long ibsi)
        tirows RfmotfExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption(vfrsionComplbint);
    }

    /**
     * Tiis dbll is usfd by tif old 1.1 stub protodol bnd is
     * unsupportfd sindf bdtivbtion rfquirfs 1.2 stubs.
     */
    publid void invokf(RfmotfCbll dbll) tirows Exdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption(vfrsionComplbint);
    }

    /**
     * Tiis dbll is usfd by tif old 1.1 stub protodol bnd is
     * unsupportfd sindf bdtivbtion rfquirfs 1.2 stubs.
     */
    publid void donf(RfmotfCbll dbll) tirows RfmotfExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(vfrsionComplbint);
    }

    /**
     * Rfturns tif dlbss of tif rff typf to bf sfriblizfd
     */
    publid String gftRffClbss(ObjfdtOutput out)
    {
        rfturn "AdtivbtbblfRff";
    }

    /**
     * Writf out fxtfrnbl rfprfsfntbtion for rfmotf rff.
     */
    publid void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption
    {
        RfmotfRff lodblRff = rff;

        out.writfObjfdt(id);
        if (lodblRff == null) {
            out.writfUTF("");
        } flsf {
            out.writfUTF(lodblRff.gftRffClbss(out));
            lodblRff.writfExtfrnbl(out);
        }
    }

    /**
     * Rfbd in fxtfrnbl rfprfsfntbtion for rfmotf rff.
     * @fxdfption ClbssNotFoundExdfption If tif dlbss for bn objfdt
     * bfing rfstorfd dbnnot bf found.
     */
    publid void rfbdExtfrnbl(ObjfdtInput in)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        id = (AdtivbtionID)in.rfbdObjfdt();
        rff = null;
        String dlbssNbmf = in.rfbdUTF();

        if (dlbssNbmf.fqubls("")) rfturn;

        try {
            Clbss<?> rffClbss = Clbss.forNbmf(RfmotfRff.pbdkbgfPrffix + "." +
                                              dlbssNbmf);
            rff = (RfmotfRff)rffClbss.nfwInstbndf();
            rff.rfbdExtfrnbl(in);
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw UnmbrsiblExdfption("Unbblf to drfbtf rfmotf rfffrfndf",
                                         f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw UnmbrsiblExdfption("Illfgbl bddfss drfbting rfmotf rfffrfndf");
        }
    }

    //----------------------------------------------------------------------;
    /**
     * Mftiod from objfdt, forwbrd from RfmotfObjfdt
     */
    publid String rfmotfToString() {
        rfturn Util.gftUnqublififdNbmf(gftClbss()) +
                " [rfmotfRff: " + rff + "]";
    }

    /**
     * dffbult implfmfntbtion of ibsiCodf for rfmotf objfdts
     */
    publid int rfmotfHbsiCodf() {
        rfturn id.ibsiCodf();
    }

    /** dffbult implfmfntbtion of fqubls for rfmotf objfdts
     */
    publid boolfbn rfmotfEqubls(RfmotfRff rff) {
        if (rff instbndfof AdtivbtbblfRff)
            rfturn id.fqubls(((AdtivbtbblfRff)rff).id);
        rfturn fblsf;
    }
}
