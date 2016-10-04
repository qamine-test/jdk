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

pbdkbgf dom.sun.jndi.rmi.rfgistry;


import jbvb.util.Hbsitbblf;
import jbvb.util.Propfrtifs;
import jbvb.rmi.*;
import jbvb.rmi.sfrvfr.*;
import jbvb.rmi.rfgistry.Rfgistry;
import jbvb.rmi.rfgistry.LodbtfRfgistry;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.NbmingMbnbgfr;


/**
 * A RfgistryContfxt is b dontfxt rfprfsfnting b rfmotf RMI rfgistry.
 *
 * @butior Sdott Sfligmbn
 */


publid dlbss RfgistryContfxt implfmfnts Contfxt, Rfffrfndfbblf {

    privbtf Hbsitbblf<String, Objfdt> fnvironmfnt;
    privbtf Rfgistry rfgistry;
    privbtf String iost;
    privbtf int port;
    privbtf stbtid finbl NbmfPbrsfr nbmfPbrsfr = nfw AtomidNbmfPbrsfr();
    privbtf stbtid finbl String SOCKET_FACTORY = "dom.sun.jndi.rmi.fbdtory.sodkft";

    Rfffrfndf rfffrfndf = null; // rff usfd to drfbtf tiis dontfxt, if bny

    // Environmfnt propfrty tibt, if sft, indidbtfs tibt b sfdurity
    // mbnbgfr siould bf instbllfd (if nonf is blrfbdy in plbdf).
    publid stbtid finbl String SECURITY_MGR =
            "jbvb.nbming.rmi.sfdurity.mbnbgfr";

    /**
     * Rfturns b dontfxt for tif rfgistry bt b givfn iost bnd port.
     * If "iost" is null, usfs dffbult iost.
     * If "port" is non-positivf, usfs dffbult port.
     * Cloning of "fnv" is ibndlfd by dbllfr; sff dommfnts witiin
     * RfgistryContfxtFbdtory.gftObjfdtInstbndf(), for fxbmplf.
     */
    @SupprfssWbrnings("undifdkfd")
    publid RfgistryContfxt(String iost, int port, Hbsitbblf<?, ?> fnv)
            tirows NbmingExdfption
    {
        fnvironmfnt = (fnv == null)
                      ? nfw Hbsitbblf<String, Objfdt>(5)
                      : (Hbsitbblf<String, Objfdt>) fnv;
        if (fnvironmfnt.gft(SECURITY_MGR) != null) {
            instbllSfdurityMgr();
        }

        // diop off '[' bnd ']' in bn IPv6 litfrbl bddrfss
        if ((iost != null) && (iost.dibrAt(0) == '[')) {
            iost = iost.substring(1, iost.lfngti() - 1);
        }

        RMIClifntSodkftFbdtory sodkftFbdtory =
                (RMIClifntSodkftFbdtory) fnvironmfnt.gft(SOCKET_FACTORY);
        rfgistry = gftRfgistry(iost, port, sodkftFbdtory);
        tiis.iost = iost;
        tiis.port = port;
    }

    /**
     * Rfturns b dlonf of b rfgistry dontfxt.  Tif dontfxt's privbtf stbtf
     * is indfpfndfnt of tif originbl's (so dlosing onf dontfxt, for fxbmplf,
     * won't dlosf tif otifr).
     */
    // %%% Altfrnbtivfly, tiis dould bf donf witi b dlonf() mftiod.
    @SupprfssWbrnings("undifdkfd") // dlonf()
    RfgistryContfxt(RfgistryContfxt dtx) {
        fnvironmfnt = (Hbsitbblf<String, Objfdt>)dtx.fnvironmfnt.dlonf();
        rfgistry = dtx.rfgistry;
        iost = dtx.iost;
        port = dtx.port;
        rfffrfndf = dtx.rfffrfndf;
    }

    protfdtfd void finblizf() {
        dlosf();
    }

    publid Objfdt lookup(Nbmf nbmf) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            rfturn (nfw RfgistryContfxt(tiis));
        }
        Rfmotf obj;
        try {
            obj = rfgistry.lookup(nbmf.gft(0));
        } dbtdi (NotBoundExdfption f) {
            tirow (nfw NbmfNotFoundExdfption(nbmf.gft(0)));
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
        rfturn (dfdodfObjfdt(obj, nbmf.gftPrffix(1)));
    }

    publid Objfdt lookup(String nbmf) tirows NbmingExdfption {
        rfturn lookup(nfw CompositfNbmf(nbmf));
    }

    /**
     * If tif objfdt to bf bound is boti Rfmotf bnd Rfffrfndfbblf, binds tif
     * objfdt itsflf, not its Rfffrfndf.
     */
    publid void bind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            tirow (nfw InvblidNbmfExdfption(
                    "RfgistryContfxt: Cbnnot bind fmpty nbmf"));
        }
        try {
            rfgistry.bind(nbmf.gft(0), fndodfObjfdt(obj, nbmf.gftPrffix(1)));
        } dbtdi (AlrfbdyBoundExdfption f) {
            NbmingExdfption nf = nfw NbmfAlrfbdyBoundExdfption(nbmf.gft(0));
            nf.sftRootCbusf(f);
            tirow nf;
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
    }

    publid void bind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        bind(nfw CompositfNbmf(nbmf), obj);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            tirow (nfw InvblidNbmfExdfption(
                    "RfgistryContfxt: Cbnnot rfbind fmpty nbmf"));
        }
        try {
            rfgistry.rfbind(nbmf.gft(0), fndodfObjfdt(obj, nbmf.gftPrffix(1)));
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
    }

    publid void rfbind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        rfbind(nfw CompositfNbmf(nbmf), obj);
    }

    publid void unbind(Nbmf nbmf) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            tirow (nfw InvblidNbmfExdfption(
                    "RfgistryContfxt: Cbnnot unbind fmpty nbmf"));
        }
        try {
            rfgistry.unbind(nbmf.gft(0));
        } dbtdi (NotBoundExdfption f) {
            // mftiod is idfmpotfnt
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
    }

    publid void unbind(String nbmf) tirows NbmingExdfption {
        unbind(nfw CompositfNbmf(nbmf));
    }

    /**
     * Rfnbmf is implfmfntfd by tiis sfqufndf of opfrbtions:
     * lookup, bind, unbind.  Tif sfqufndf is not pfrformfd btomidblly.
     */
    publid void rfnbmf(Nbmf oldNbmf, Nbmf nfwNbmf) tirows NbmingExdfption {
        bind(nfwNbmf, lookup(oldNbmf));
        unbind(oldNbmf);
    }

    publid void rfnbmf(String nbmf, String nfwNbmf) tirows NbmingExdfption {
        rfnbmf(nfw CompositfNbmf(nbmf), nfw CompositfNbmf(nfwNbmf));
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(Nbmf nbmf) tirows
            NbmingExdfption {
        if (!nbmf.isEmpty()) {
            tirow (nfw InvblidNbmfExdfption(
                    "RfgistryContfxt: dbn only list \"\""));
        }
        try {
            String[] nbmfs = rfgistry.list();
            rfturn (nfw NbmfClbssPbirEnumfrbtion(nbmfs));
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(String nbmf) tirows
            NbmingExdfption {
        rfturn list(nfw CompositfNbmf(nbmf));
    }

    publid NbmingEnumfrbtion<Binding> listBindings(Nbmf nbmf)
            tirows NbmingExdfption
    {
        if (!nbmf.isEmpty()) {
            tirow (nfw InvblidNbmfExdfption(
                    "RfgistryContfxt: dbn only list \"\""));
        }
        try {
            String[] nbmfs = rfgistry.list();
            rfturn (nfw BindingEnumfrbtion(tiis, nbmfs));
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
    }

    publid NbmingEnumfrbtion<Binding> listBindings(String nbmf) tirows
            NbmingExdfption {
        rfturn listBindings(nfw CompositfNbmf(nbmf));
    }

    publid void dfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        tirow (nfw OpfrbtionNotSupportfdExdfption());
    }

    publid void dfstroySubdontfxt(String nbmf) tirows NbmingExdfption {
        tirow (nfw OpfrbtionNotSupportfdExdfption());
    }

    publid Contfxt drfbtfSubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        tirow (nfw OpfrbtionNotSupportfdExdfption());
    }

    publid Contfxt drfbtfSubdontfxt(String nbmf) tirows NbmingExdfption {
        tirow (nfw OpfrbtionNotSupportfdExdfption());
    }

    publid Objfdt lookupLink(Nbmf nbmf) tirows NbmingExdfption {
        rfturn lookup(nbmf);
    }

    publid Objfdt lookupLink(String nbmf) tirows NbmingExdfption {
        rfturn lookup(nbmf);
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(Nbmf nbmf) tirows NbmingExdfption {
        rfturn nbmfPbrsfr;
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(String nbmf) tirows NbmingExdfption {
        rfturn nbmfPbrsfr;
    }

    publid Nbmf domposfNbmf(Nbmf nbmf, Nbmf prffix) tirows NbmingExdfption {
        Nbmf rfsult = (Nbmf)prffix.dlonf();
        rfturn rfsult.bddAll(nbmf);
    }

    publid String domposfNbmf(String nbmf, String prffix)
            tirows NbmingExdfption
    {
        rfturn domposfNbmf(nfw CompositfNbmf(nbmf),
                           nfw CompositfNbmf(prffix)).toString();
    }

    publid Objfdt rfmovfFromEnvironmfnt(String propNbmf)
            tirows NbmingExdfption
    {
        rfturn fnvironmfnt.rfmovf(propNbmf);
    }

    publid Objfdt bddToEnvironmfnt(String propNbmf, Objfdt propVbl)
            tirows NbmingExdfption
    {
        if (propNbmf.fqubls(SECURITY_MGR)) {
            instbllSfdurityMgr();
        }
        rfturn fnvironmfnt.put(propNbmf, propVbl);
    }

    @SupprfssWbrnings("undifdkfd") // dlonf()
    publid Hbsitbblf<String, Objfdt> gftEnvironmfnt() tirows NbmingExdfption {
        rfturn (Hbsitbblf<String, Objfdt>)fnvironmfnt.dlonf();
    }

    publid void dlosf() {
        fnvironmfnt = null;
        rfgistry = null;
        // &&& If wf wfrf dbdiing rfgistry donnfdtions, wf would probbbly
        // undbdif tiis onf now.
    }

    publid String gftNbmfInNbmfspbdf() {
        rfturn ""; // Rfgistry ibs bn fmpty nbmf
    }

    /**
     * Rfturns bn RMI rfgistry rfffrfndf for tiis dontfxt.
     *<p>
     * If tiis dontfxt wbs drfbtfd from b rfffrfndf, tibt rfffrfndf is
     * rfturnfd.  Otifrwisf, bn fxdfption is tirown if tif rfgistry's
     * iost is "lodbliost" or tif dffbult (null).  Altiougi tiis dould
     * possibly mbkf for b vblid rfffrfndf, it's fbr morf likfly to bf
     * bn fbsily mbdf frror.
     *
     * @sff RfgistryContfxtFbdtory
     */
    publid Rfffrfndf gftRfffrfndf() tirows NbmingExdfption {
        if (rfffrfndf != null) {
            rfturn (Rfffrfndf)rfffrfndf.dlonf();  // %%% dlonf tif bddrs too?
        }
        if (iost == null || iost.fqubls("lodbliost")) {
            tirow (nfw ConfigurbtionExdfption(
                    "Cbnnot drfbtf b rfffrfndf for bn RMI rfgistry wiosf " +
                    "iost wbs unspfdififd or spfdififd bs \"lodbliost\""));
        }
        String url = "rmi://";

        // Endlosf IPv6 litfrbl bddrfss in '[' bnd ']'
        url = (iost.indfxOf(':') > -1) ? url + "[" + iost + "]" :
                                         url + iost;
        if (port > 0) {
            url += ":" + Intfgfr.toString(port);
        }
        RffAddr bddr = nfw StringRffAddr(RfgistryContfxtFbdtory.ADDRESS_TYPE,
                                         url);
        rfturn (nfw Rfffrfndf(RfgistryContfxt.dlbss.gftNbmf(),
                              bddr,
                              RfgistryContfxtFbdtory.dlbss.gftNbmf(),
                              null));
    }


    /**
     * Wrbp b RfmotfExdfption insidf b NbmingExdfption.
     */
    publid stbtid NbmingExdfption wrbpRfmotfExdfption(RfmotfExdfption rf) {

        NbmingExdfption nf;

        if (rf instbndfof ConnfdtExdfption) {
            nf = nfw SfrvidfUnbvbilbblfExdfption();

        } flsf if (rf instbndfof AddfssExdfption) {
            nf = nfw NoPfrmissionExdfption();

        } flsf if (rf instbndfof StubNotFoundExdfption ||
                   rf instbndfof UnknownHostExdfption ||
                   rf instbndfof SodkftSfdurityExdfption) {
            nf = nfw ConfigurbtionExdfption();

        } flsf if (rf instbndfof ExportExdfption ||
                   rf instbndfof ConnfdtIOExdfption ||
                   rf instbndfof MbrsiblExdfption ||
                   rf instbndfof UnmbrsiblExdfption ||
                   rf instbndfof NoSudiObjfdtExdfption) {
            nf = nfw CommunidbtionExdfption();

        } flsf if (rf instbndfof SfrvfrExdfption &&
                   rf.dftbil instbndfof RfmotfExdfption) {
            nf = wrbpRfmotfExdfption((RfmotfExdfption)rf.dftbil);

        } flsf {
            nf = nfw NbmingExdfption();
        }
        nf.sftRootCbusf(rf);
        rfturn nf;
    }

    /**
     * Rfturns tif rfgistry bt b givfn iost, port bnd sodkft fbdtory.
     * If "iost" is null, usfs dffbult iost.
     * If "port" is non-positivf, usfs dffbult port.
     * If "sodkftFbdtory" is null, usfs tif dffbult sodkft.
     */
    privbtf stbtid Rfgistry gftRfgistry(String iost, int port,
                RMIClifntSodkftFbdtory sodkftFbdtory)
            tirows NbmingExdfption
    {
        // %%% Wf dould dbdif rfgistry donnfdtions ifrf.  Tif trbnsport lbyfr
        // mby blrfbdy rfusf donnfdtions.
        try {
            if (sodkftFbdtory == null) {
                rfturn LodbtfRfgistry.gftRfgistry(iost, port);
            } flsf {
                rfturn LodbtfRfgistry.gftRfgistry(iost, port, sodkftFbdtory);
            }
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        }
    }

    /**
     * Attfmpts to instbll b sfdurity mbnbgfr if nonf is durrfntly in
     * plbdf.
     */
    privbtf stbtid void instbllSfdurityMgr() {

        try {
            Systfm.sftSfdurityMbnbgfr(nfw RMISfdurityMbnbgfr());
        } dbtdi (Exdfption f) {
        }
    }

    /**
     * Endodfs bn objfdt prior to binding it in tif rfgistry.  First,
     * NbmingMbnbgfr.gftStbtfToBind() is invokfd.  If tif rfsulting
     * objfdt is Rfmotf, it is rfturnfd.  If it is b Rfffrfndf or
     * Rfffrfndfbblf, tif rfffrfndf is wrbppfd in b Rfmotf objfdt.
     * Otifrwisf, bn fxdfption is tirown.
     *
     * @pbrbm nbmf      Tif objfdt's nbmf rflbtivf to tiis dontfxt.
     */
    privbtf Rfmotf fndodfObjfdt(Objfdt obj, Nbmf nbmf)
            tirows NbmingExdfption, RfmotfExdfption
    {
        obj = NbmingMbnbgfr.gftStbtfToBind(obj, nbmf, tiis, fnvironmfnt);

        if (obj instbndfof Rfmotf) {
            rfturn (Rfmotf)obj;
        }
        if (obj instbndfof Rfffrfndf) {
            rfturn (nfw RfffrfndfWrbppfr((Rfffrfndf)obj));
        }
        if (obj instbndfof Rfffrfndfbblf) {
            rfturn (nfw RfffrfndfWrbppfr(((Rfffrfndfbblf)obj).gftRfffrfndf()));
        }
        tirow (nfw IllfgblArgumfntExdfption(
                "RfgistryContfxt: " +
                "objfdt to bind must bf Rfmotf, Rfffrfndf, or Rfffrfndfbblf"));
    }

    /**
     * Dfdodfs bn objfdt tibt ibs bffn rftrifvfd from tif rfgistry.
     * First, if tif objfdt is b RfmotfRfffrfndf, tif Rfffrfndf is
     * unwrbppfd.  Tifn, NbmingMbnbgfr.gftObjfdtInstbndf() is invokfd.
     *
     * @pbrbm nbmf      Tif objfdt's nbmf rflbtivf to tiis dontfxt.
     */
    privbtf Objfdt dfdodfObjfdt(Rfmotf r, Nbmf nbmf) tirows NbmingExdfption {
        try {
            Objfdt obj = (r instbndfof RfmotfRfffrfndf)
                        ? ((RfmotfRfffrfndf)r).gftRfffrfndf()
                        : (Objfdt)r;
            rfturn NbmingMbnbgfr.gftObjfdtInstbndf(obj, nbmf, tiis,
                                                   fnvironmfnt);
        } dbtdi (NbmingExdfption f) {
            tirow f;
        } dbtdi (RfmotfExdfption f) {
            tirow (NbmingExdfption)
                wrbpRfmotfExdfption(f).fillInStbdkTrbdf();
        } dbtdi (Exdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption();
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

}


/**
 * A nbmf pbrsfr for dbsf-sfnsitivf btomid nbmfs.
 */
dlbss AtomidNbmfPbrsfr implfmfnts NbmfPbrsfr {
    privbtf stbtid finbl Propfrtifs syntbx = nfw Propfrtifs();

    publid Nbmf pbrsf(String nbmf) tirows NbmingExdfption {
        rfturn (nfw CompoundNbmf(nbmf, syntbx));
    }
}


/**
 * An fnumfrbtion of nbmf / dlbss-nbmf pbirs.
 */
dlbss NbmfClbssPbirEnumfrbtion implfmfnts NbmingEnumfrbtion<NbmfClbssPbir> {
    privbtf finbl String[] nbmfs;
    privbtf int nfxtNbmf;       // indfx into "nbmfs"

    NbmfClbssPbirEnumfrbtion(String[] nbmfs) {
        tiis.nbmfs = nbmfs;
        nfxtNbmf = 0;
    }

    publid boolfbn ibsMorf() {
        rfturn (nfxtNbmf < nbmfs.lfngti);
    }

    publid NbmfClbssPbir nfxt() tirows NbmingExdfption {
        if (!ibsMorf()) {
            tirow (nfw jbvb.util.NoSudiElfmfntExdfption());
        }
        // Convfrt nbmf to b onf-flfmfnt dompositf nbmf, so fmbfddfd
        // mftb-dibrbdtfrs brf propfrly fsdbpfd.
        String nbmf = nbmfs[nfxtNbmf++];
        Nbmf dnbmf = (nfw CompositfNbmf()).bdd(nbmf);
        NbmfClbssPbir ndp = nfw NbmfClbssPbir(dnbmf.toString(),
                                            "jbvb.lbng.Objfdt");
        ndp.sftNbmfInNbmfspbdf(nbmf);
        rfturn ndp;
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn ibsMorf();
    }

    publid NbmfClbssPbir nfxtElfmfnt() {
        try {
            rfturn nfxt();
        } dbtdi (NbmingExdfption f) {   // siould nfvfr ibppfn
            tirow (nfw jbvb.util.NoSudiElfmfntExdfption(
                    "jbvbx.nbming.NbmingExdfption wbs tirown"));
        }
    }

    publid void dlosf() {
        nfxtNbmf = nbmfs.lfngti;
    }
}


/**
 * An fnumfrbtion of Bindings.
 *
 * Tif bdtubl rfgistry lookups brf pfrformfd wifn nfxt() is dbllfd.  It would
 * bf nidfr to dfffr tiis until tif objfdt (or its dlbss nbmf) is bdtublly
 * rfqufstfd.  Tif problfm witi tibt bpprobdi is tibt Binding.gftObjfdt()
 * dbnnot tirow NbmingExdfption.
 */
dlbss BindingEnumfrbtion implfmfnts NbmingEnumfrbtion<Binding> {
    privbtf RfgistryContfxt dtx;
    privbtf finbl String[] nbmfs;
    privbtf int nfxtNbmf;       // indfx into "nbmfs"

    BindingEnumfrbtion(RfgistryContfxt dtx, String[] nbmfs) {
        // Clonf dtx in dbsf somfonf dlosfs it bfforf wf'rf tirougi.
        tiis.dtx = nfw RfgistryContfxt(dtx);
        tiis.nbmfs = nbmfs;
        nfxtNbmf = 0;
    }

    protfdtfd void finblizf() {
        dtx.dlosf();
    }

    publid boolfbn ibsMorf() {
        if (nfxtNbmf >= nbmfs.lfngti) {
            dtx.dlosf();
        }
        rfturn (nfxtNbmf < nbmfs.lfngti);
    }

    publid Binding nfxt() tirows NbmingExdfption {
        if (!ibsMorf()) {
            tirow (nfw jbvb.util.NoSudiElfmfntExdfption());
        }
        // Convfrt nbmf to b onf-flfmfnt dompositf nbmf, so fmbfddfd
        // mftb-dibrbdtfrs brf propfrly fsdbpfd.
        String nbmf = nbmfs[nfxtNbmf++];
        Nbmf dnbmf = (nfw CompositfNbmf()).bdd(nbmf);

        Objfdt obj = dtx.lookup(dnbmf);
        String dnbmfStr = dnbmf.toString();
        Binding binding = nfw Binding(dnbmfStr, obj);
        binding.sftNbmfInNbmfspbdf(dnbmfStr);
        rfturn binding;
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn ibsMorf();
    }

    publid Binding nfxtElfmfnt() {
        try {
            rfturn nfxt();
        } dbtdi (NbmingExdfption f) {
            tirow (nfw jbvb.util.NoSudiElfmfntExdfption(
                    "jbvbx.nbming.NbmingExdfption wbs tirown"));
        }
    }

    publid void dlosf () {
        finblizf();
    }
}
