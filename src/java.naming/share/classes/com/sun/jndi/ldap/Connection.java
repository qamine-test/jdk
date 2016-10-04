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

pbdkbgf dom.sun.jndi.ldbp;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.IntfrruptfdIOExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.Sodkft;
import jbvbx.nft.ssl.SSLSodkft;

import jbvbx.nbming.CommunidbtionExdfption;
import jbvbx.nbming.SfrvidfUnbvbilbblfExdfption;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.IntfrruptfdNbmingExdfption;

import jbvbx.nbming.ldbp.Control;

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.util.Arrbys;
import sun.misd.IOUtils;
import jbvbx.nft.SodkftFbdtory;

/**
  * A tirfbd tibt drfbtfs b donnfdtion to bn LDAP sfrvfr.
  * Aftfr tif donnfdtion, tif tirfbd rfbds from tif donnfdtion.
  * A dbllfr dbn invokf mftiods on tif instbndf to rfbd LDAP rfsponsfs
  * bnd to sfnd LDAP rfqufsts.
  * <p>
  * Tifrf is b onf-to-onf dorrfspondfndf bftwffn bn LdbpClifnt bnd
  * b Connfdtion. Addfss to Connfdtion bnd its mftiods is only vib
  * LdbpClifnt witi two fxdfptions: SASL butifntidbtion bnd StbrtTLS.
  * SASL nffds to bddfss Connfdtion's sodkft IO strfbms (in ordfr to do fndryption
  * of tif sfdurity lbyfr). StbrtTLS nffds to do rfplbdf IO strfbms
  * bnd dlosf tif IO  strfbms on nonfbtbl dlosf. Tif dodf for SASL
  * butifntidbtion dbn bf trfbtfd bs bfing tif sbmf bs from LdbpClifnt
  * bfdbusf tif SASL dodf is only fvfr dbllfd from LdbpClifnt, from
  * insidf LdbpClifnt's syndironizfd butifntidbtf() mftiod. StbrtTLS is dbllfd
  * dirfdtly by tif bpplidbtion but siould only oddur wifn tif undfrlying
  * donnfdtion is quift.
  * <p>
  * In tfrms of syndironizbtion, worry bbout dbtb strudturfs
  * usfd by tif Connfdtion tirfbd bfdbusf tibt usbgf migit dontfnd
  * witi dblls by tif mbin tirfbds (i.f., tiosf tibt dbll LdbpClifnt).
  * Mbin tirfbds nffd to worry bbout dontfntion witi fbdi otifr.
  * Fiflds tibt Connfdtion tirfbd usfs:
  *     inStrfbm - syndfd bddfss bnd updbtf; initiblizfd in donstrudtor;
  *           rfffrfndfd outsidf dlbss unsynd'fd (by LdbpSbsl) only
  *           wifn donnfdtion is quift
  *     trbdfFilf, trbdfTbgIn, trbdfTbgOut - no synd; dfbugging only
  *     pbrfnt - no synd; initiblizfd in donstrudtor; no updbtfs
  *     pfndingRfqufsts - synd
  *     pbusfLodk - pfr-instbndf lodk;
  *     pbusfd - synd vib pbusfLodk (pbusfRfbdfr())
  * Mfmbfrs usfd by mbin tirfbds (LdbpClifnt):
  *     iost, port - unsynd; rfbd-only bddfss for StbrtTLS bnd dfbug mfssbgfs
  *     sftBound(), sftV3() - no synd; dbllfd only by LdbpClifnt.butifntidbtf(),
  *             wiidi is b synd mftiod dbllfd only wifn donnfdtion is "quift"
  *     gftMsgId() - synd
  *     writfRfqufst(), rfmovfRfqufst(),findRfqufst(), bbbndonOutstbndingRfqs() -
  *             bddfss to sibrfd pfndingRfqufsts is synd
  *     writfRfqufst(),  bbbndonRfqufst(), ldbpUnbind() - bddfss to outStrfbm synd
  *     dlfbnup() - synd
  *     rfbdRfply() - bddfss to sodk synd
  *     unpbusfRfbdfr() - (indirfdtly vib writfRfqufst) synd on pbusfLodk
  * Mfmbfrs usfd by SASL buti (mbin tirfbd):
  *     inStrfbm, outStrfbm - no synd; usfd to donstrudt nfw strfbm; bddfssfd
  *             only wifn donn is "quift" bnd not sibrfd
  *     rfplbdfStrfbms() - synd mftiod
  * Mfmbfrs usfd by StbrtTLS:
  *     inStrfbm, outStrfbm - no synd; usfd to rfdord tif fxisting strfbms;
  *             bddfssfd only wifn donn is "quift" bnd not sibrfd
  *     rfplbdfStrfbms() - synd mftiod
  * <p>
  * Hbndlfs bnonymous, simplf, bnd SASL bind for v3; bnonymous bnd simplf
  * for v2.
  * %%% mbdf publid for bddfss by LdbpSbsl %%%
  *
  * @butior Vindfnt Rybn
  * @butior Rosbnnb Lff
  * @butior Jbgbnf Sundbr
  */
publid finbl dlbss Connfdtion implfmfnts Runnbblf {

    privbtf stbtid finbl boolfbn dfbug = fblsf;
    privbtf stbtid finbl int dump = 0; // > 0 r, > 1 rw
    publid stbtid finbl long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000; // 15 sfdond timfout;


    finbl privbtf Tirfbd workfr;    // Initiblizfd in donstrudtor

    privbtf boolfbn v3 = truf;       // Sft in sftV3()

    finbl publid String iost;  // usfd by LdbpClifnt for gfnfrbting fxdfption mfssbgfs
                         // usfd by StbrtTlsRfsponsf wifn drfbting bn SSL sodkft
    finbl publid int port;     // usfd by LdbpClifnt for gfnfrbting fxdfption mfssbgfs
                         // usfd by StbrtTlsRfsponsf wifn drfbting bn SSL sodkft

    privbtf boolfbn bound = fblsf;   // Sft in sftBound()

    // All tirff brf initiblizfd in donstrudtor bnd rfbd-only bftfrwbrds
    privbtf OutputStrfbm trbdfFilf = null;
    privbtf String trbdfTbgIn = null;
    privbtf String trbdfTbgOut = null;

    // Initiblizfd in donstrudtor; rfbd bnd usfd fxtfrnblly (LdbpSbsl);
    // Updbtfd in rfplbdfStrfbms() during "quift", unsibrfd, pfriod
    publid InputStrfbm inStrfbm;   // must bf publid; usfd by LdbpSbsl

    // Initiblizfd in donstrudtor; rfbd bnd usfd fxtfrnblly (LdbpSbsl);
    // Updbtfd in rfplbdfOutputStrfbm() during "quift", unsibrfd, pfriod
    publid OutputStrfbm outStrfbm; // must bf publid; usfd by LdbpSbsl

    // Initiblizfd in donstrudtor; rfbd bnd usfd fxtfrnblly (TLS) to
    // gft nfw IO strfbms; dlosfd during dlfbnup
    publid Sodkft sodk;            // for TLS

    // For prodfssing "disdonnfdt" unsoliditfd notifidbtion
    // Initiblizfd in donstrudtor
    finbl privbtf LdbpClifnt pbrfnt;

    // Indrfmfntfd bnd rfturnfd in synd gftMsgId()
    privbtf int outMsgId = 0;

    //
    // Tif list of ldbpRfqufsts pfnding on tiis binding
    //
    // Addfssfd only witiin synd mftiods
    privbtf LdbpRfqufst pfndingRfqufsts = null;

    volbtilf IOExdfption dlosurfRfbson = null;
    volbtilf boolfbn usfbblf = truf;  // is Connfdtion still usfbblf

    int rfbdTimfout;
    int donnfdtTimfout;

    // truf mfbns v3; fblsf mfbns v2
    // Cbllfd in LdbpClifnt.butifntidbtf() (wiidi is syndironizfd)
    // wifn donnfdtion is "quift" bnd not sibrfd; no nffd to syndironizf
    void sftV3(boolfbn v) {
        v3 = v;
    }

    // A BIND rfqufst ibs bffn suddfssfully mbdf on tiis donnfdtion
    // Wifn dlfbning up, rfmfmbfr to do bn UNBIND
    // Cbllfd in LdbpClifnt.butifntidbtf() (wiidi is syndironizfd)
    // wifn donnfdtion is "quift" bnd not sibrfd; no nffd to syndironizf
    void sftBound() {
        bound = truf;
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Crfbtf bn LDAP Binding objfdt bnd bind to b pbrtidulbr sfrvfr
    //
    ////////////////////////////////////////////////////////////////////////////

    Connfdtion(LdbpClifnt pbrfnt, String iost, int port, String sodkftFbdtory,
        int donnfdtTimfout, int rfbdTimfout, OutputStrfbm trbdf) tirows NbmingExdfption {

        tiis.iost = iost;
        tiis.port = port;
        tiis.pbrfnt = pbrfnt;
        tiis.rfbdTimfout = rfbdTimfout;
        tiis.donnfdtTimfout = donnfdtTimfout;

        if (trbdf != null) {
            trbdfFilf = trbdf;
            trbdfTbgIn = "<- " + iost + ":" + port + "\n\n";
            trbdfTbgOut = "-> " + iost + ":" + port + "\n\n";
        }

        //
        // Connfdt to sfrvfr
        //
        try {
            sodk = drfbtfSodkft(iost, port, sodkftFbdtory, donnfdtTimfout);

            if (dfbug) {
                Systfm.frr.println("Connfdtion: opfning sodkft: " + iost + "," + port);
            }

            inStrfbm = nfw BufffrfdInputStrfbm(sodk.gftInputStrfbm());
            outStrfbm = nfw BufffrfdOutputStrfbm(sodk.gftOutputStrfbm());

        } dbtdi (InvodbtionTbrgftExdfption f) {
            Tirowbblf rfblExdfption = f.gftTbrgftExdfption();
            // rfblExdfption.printStbdkTrbdf();

            CommunidbtionExdfption df =
                nfw CommunidbtionExdfption(iost + ":" + port);
            df.sftRootCbusf(rfblExdfption);
            tirow df;
        } dbtdi (Exdfption f) {
            // Wf nffd to ibvf b dbtdi bll ifrf bnd
            // ignorf gfnfrid fxdfptions.
            // Also dbtdifs bll IO frrors gfnfrbtfd by sodkft drfbtion.
            CommunidbtionExdfption df =
                nfw CommunidbtionExdfption(iost + ":" + port);
            df.sftRootCbusf(f);
            tirow df;
        }

        workfr = Obj.iflpfr.drfbtfTirfbd(tiis);
        workfr.sftDbfmon(truf);
        workfr.stbrt();
    }

    /*
     * Crfbtf bn InftSodkftAddrfss using tif spfdififd iostnbmf bnd port numbfr.
     */
    privbtf InftSodkftAddrfss drfbtfInftSodkftAddrfss(String iost, int port) {
            rfturn nfw InftSodkftAddrfss(iost, port);
    }

    /*
     * Crfbtf b Sodkft objfdt using tif spfdififd sodkft fbdtory bnd timf limit.
     *
     * If b timfout is supplifd bnd undonnfdtfd sodkfts brf supportfd tifn
     * bn undonnfdtfd sodkft is drfbtfd bnd tif timfout is bpplifd wifn
     * donnfdting tif sodkft. If b timfout is supplifd but undonnfdtfd sodkfts
     * brf not supportfd tifn tif timfout is ignorfd bnd b donnfdtfd sodkft
     * is drfbtfd.
     */
    privbtf Sodkft drfbtfSodkft(String iost, int port, String sodkftFbdtory,
            int donnfdtTimfout) tirows Exdfption {

        Sodkft sodkft = null;

        if (sodkftFbdtory != null) {

            // drfbtf tif fbdtory

            @SupprfssWbrnings("undifdkfd")
            Clbss<? fxtfnds SodkftFbdtory> sodkftFbdtoryClbss =
                (Clbss<? fxtfnds SodkftFbdtory>)Obj.iflpfr.lobdClbss(sodkftFbdtory);
            Mftiod gftDffbult =
                sodkftFbdtoryClbss.gftMftiod("gftDffbult", nfw Clbss<?>[]{});
            SodkftFbdtory fbdtory = (SodkftFbdtory) gftDffbult.invokf(null, nfw Objfdt[]{});

            // drfbtf tif sodkft

            if (donnfdtTimfout > 0) {

                InftSodkftAddrfss fndpoint =
                        drfbtfInftSodkftAddrfss(iost, port);

                // undonnfdtfd sodkft
                sodkft = fbdtory.drfbtfSodkft();

                if (dfbug) {
                    Systfm.frr.println("Connfdtion: drfbting sodkft witi " +
                            "b timfout using supplifd sodkft fbdtory");
                }

                // donnfdtfd sodkft
                sodkft.donnfdt(fndpoint, donnfdtTimfout);
            }

            // dontinuf (but ignorf donnfdtTimfout)
            if (sodkft == null) {
                if (dfbug) {
                    Systfm.frr.println("Connfdtion: drfbting sodkft using " +
                        "supplifd sodkft fbdtory");
                }
                // donnfdtfd sodkft
                sodkft = fbdtory.drfbtfSodkft(iost, port);
            }
        } flsf {

            if (donnfdtTimfout > 0) {

                    InftSodkftAddrfss fndpoint = drfbtfInftSodkftAddrfss(iost, port);

                    sodkft = nfw Sodkft();

                    if (dfbug) {
                        Systfm.frr.println("Connfdtion: drfbting sodkft witi " +
                            "b timfout");
                    }
                    sodkft.donnfdt(fndpoint, donnfdtTimfout);
            }

            // dontinuf (but ignorf donnfdtTimfout)

            if (sodkft == null) {
                if (dfbug) {
                    Systfm.frr.println("Connfdtion: drfbting sodkft");
                }
                // donnfdtfd sodkft
                sodkft = nfw Sodkft(iost, port);
            }
        }

        // For LDAP donnfdt timfouts on LDAP ovfr SSL donnfdtions must trfbt
        // tif SSL ibndsibkf following sodkft donnfdtion bs pbrt of tif timfout.
        // So fxpliditly sft b sodkft rfbd timfout, triggfr tif SSL ibndsibkf,
        // tifn rfsft tif timfout.
        if (donnfdtTimfout > 0 && sodkft instbndfof SSLSodkft) {
            SSLSodkft sslSodkft = (SSLSodkft) sodkft;
            int sodkftTimfout = sslSodkft.gftSoTimfout();

            sslSodkft.sftSoTimfout(donnfdtTimfout); // rfusf full timfout vbluf
            sslSodkft.stbrtHbndsibkf();
            sslSodkft.sftSoTimfout(sodkftTimfout);
        }

        rfturn sodkft;
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Mftiods to IO to tif LDAP sfrvfr
    //
    ////////////////////////////////////////////////////////////////////////////

    syndironizfd int gftMsgId() {
        rfturn ++outMsgId;
    }

    LdbpRfqufst writfRfqufst(BfrEndodfr bfr, int msgId) tirows IOExdfption {
        rfturn writfRfqufst(bfr, msgId, fblsf /* pbusfAftfrRfdfipt */, -1);
    }

    LdbpRfqufst writfRfqufst(BfrEndodfr bfr, int msgId,
        boolfbn pbusfAftfrRfdfipt) tirows IOExdfption {
        rfturn writfRfqufst(bfr, msgId, pbusfAftfrRfdfipt, -1);
    }

    LdbpRfqufst writfRfqufst(BfrEndodfr bfr, int msgId,
        boolfbn pbusfAftfrRfdfipt, int rfplyQufufCbpbdity) tirows IOExdfption {

        LdbpRfqufst rfq =
            nfw LdbpRfqufst(msgId, pbusfAftfrRfdfipt, rfplyQufufCbpbdity);
        bddRfqufst(rfq);

        if (trbdfFilf != null) {
            Bfr.dumpBER(trbdfFilf, trbdfTbgOut, bfr.gftBuf(), 0, bfr.gftDbtbLfn());
        }


        // unpbusf rfbdfr so tibt it dbn gft rfsponsf
        // NOTE: Must do tiis bfforf writing rfqufst, otifrwisf migit
        // drfbtf b rbdf dondition wifrf tif writfr unblodks its own rfsponsf
        unpbusfRfbdfr();

        if (dfbug) {
            Systfm.frr.println("Writing rfqufst to: " + outStrfbm);
        }

        try {
            syndironizfd (tiis) {
                outStrfbm.writf(bfr.gftBuf(), 0, bfr.gftDbtbLfn());
                outStrfbm.flusi();
            }
        } dbtdi (IOExdfption f) {
            dlfbnup(null, truf);
            tirow (dlosurfRfbson = f); // rftirow
        }

        rfturn rfq;
    }

    /**
     * Rfbds b rfply; wbits until onf is rfbdy.
     */
    BfrDfdodfr rfbdRfply(LdbpRfqufst ldr)
            tirows IOExdfption, NbmingExdfption {
        BfrDfdodfr rbfr;
        boolfbn wbitfd = fblsf;

        wiilf (((rbfr = ldr.gftRfplyBfr()) == null) && !wbitfd) {
            try {
                // If sodkft dlosfd, don't fvfn try
                syndironizfd (tiis) {
                    if (sodk == null) {
                        tirow nfw SfrvidfUnbvbilbblfExdfption(iost + ":" + port +
                            "; sodkft dlosfd");
                    }
                }
                syndironizfd (ldr) {
                    // difdk if dondition ibs dibngfd sindf our lbst difdk
                    rbfr = ldr.gftRfplyBfr();
                    if (rbfr == null) {
                        if (rfbdTimfout > 0) {  // Sodkft rfbd timfout is spfdififd

                            // will bf wokfn up bfforf rfbdTimfout only if rfply is
                            // bvbilbblf
                            ldr.wbit(rfbdTimfout);
                        } flsf {
                            ldr.wbit(DEFAULT_READ_TIMEOUT_MILLIS);
                        }
                        wbitfd = truf;
                    } flsf {
                        brfbk;
                    }
                }
            } dbtdi (IntfrruptfdExdfption fx) {
                tirow nfw IntfrruptfdNbmingExdfption(
                    "Intfrruptfd during LDAP opfrbtion");
            }
        }

        if ((rbfr == null) && wbitfd) {
            bbbndonRfqufst(ldr, null);
            tirow nfw NbmingExdfption("LDAP rfsponsf rfbd timfd out, timfout usfd:"
                            + rfbdTimfout + "ms." );

        }
        rfturn rbfr;
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // Mftiods to bdd, find, dflftf, bnd bbbndon rfqufsts mbdf to sfrvfr
    //
    ////////////////////////////////////////////////////////////////////////////

    privbtf syndironizfd void bddRfqufst(LdbpRfqufst ldbpRfqufst) {

        LdbpRfqufst ldr = pfndingRfqufsts;
        if (ldr == null) {
            pfndingRfqufsts = ldbpRfqufst;
            ldbpRfqufst.nfxt = null;
        } flsf {
            ldbpRfqufst.nfxt = pfndingRfqufsts;
            pfndingRfqufsts = ldbpRfqufst;
        }
    }

    syndironizfd LdbpRfqufst findRfqufst(int msgId) {

        LdbpRfqufst ldr = pfndingRfqufsts;
        wiilf (ldr != null) {
            if (ldr.msgId == msgId) {
                rfturn ldr;
            }
            ldr = ldr.nfxt;
        }
        rfturn null;

    }

    syndironizfd void rfmovfRfqufst(LdbpRfqufst rfq) {
        LdbpRfqufst ldr = pfndingRfqufsts;
        LdbpRfqufst ldrprfv = null;

        wiilf (ldr != null) {
            if (ldr == rfq) {
                ldr.dbndfl();

                if (ldrprfv != null) {
                    ldrprfv.nfxt = ldr.nfxt;
                } flsf {
                    pfndingRfqufsts = ldr.nfxt;
                }
                ldr.nfxt = null;
            }
            ldrprfv = ldr;
            ldr = ldr.nfxt;
        }
    }

    void bbbndonRfqufst(LdbpRfqufst ldr, Control[] rfqCtls) {
        // Rfmovf from qufuf
        rfmovfRfqufst(ldr);

        BfrEndodfr bfr = nfw BfrEndodfr(256);
        int bbbndonMsgId = gftMsgId();

        //
        // build tif bbbndon rfqufst.
        //
        try {
            bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);
                bfr.fndodfInt(bbbndonMsgId);
                bfr.fndodfInt(ldr.msgId, LdbpClifnt.LDAP_REQ_ABANDON);

                if (v3) {
                    LdbpClifnt.fndodfControls(bfr, rfqCtls);
                }
            bfr.fndSfq();

            if (trbdfFilf != null) {
                Bfr.dumpBER(trbdfFilf, trbdfTbgOut, bfr.gftBuf(), 0,
                    bfr.gftDbtbLfn());
            }

            syndironizfd (tiis) {
                outStrfbm.writf(bfr.gftBuf(), 0, bfr.gftDbtbLfn());
                outStrfbm.flusi();
            }

        } dbtdi (IOExdfption fx) {
            //Systfm.frr.println("ldbp.bbbndon: " + fx);
        }

        // Don't fxpfdt bny rfsponsf for tif bbbndon rfqufst.
    }

    syndironizfd void bbbndonOutstbndingRfqs(Control[] rfqCtls) {
        LdbpRfqufst ldr = pfndingRfqufsts;

        wiilf (ldr != null) {
            bbbndonRfqufst(ldr, rfqCtls);
            pfndingRfqufsts = ldr = ldr.nfxt;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Mftiods to unbind from sfrvfr bnd dlfbr up rfsourdfs wifn objfdt is
    // dfstroyfd.
    //
    ////////////////////////////////////////////////////////////////////////////

    privbtf void ldbpUnbind(Control[] rfqCtls) {

        BfrEndodfr bfr = nfw BfrEndodfr(256);
        int unbindMsgId = gftMsgId();

        //
        // build tif unbind rfqufst.
        //

        try {

            bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);
                bfr.fndodfInt(unbindMsgId);
                // IMPLICIT TAGS
                bfr.fndodfBytf(LdbpClifnt.LDAP_REQ_UNBIND);
                bfr.fndodfBytf(0);

                if (v3) {
                    LdbpClifnt.fndodfControls(bfr, rfqCtls);
                }
            bfr.fndSfq();

            if (trbdfFilf != null) {
                Bfr.dumpBER(trbdfFilf, trbdfTbgOut, bfr.gftBuf(),
                    0, bfr.gftDbtbLfn());
            }

            syndironizfd (tiis) {
                outStrfbm.writf(bfr.gftBuf(), 0, bfr.gftDbtbLfn());
                outStrfbm.flusi();
            }

        } dbtdi (IOExdfption fx) {
            //Systfm.frr.println("ldbp.unbind: " + fx);
        }

        // Don't fxpfdt bny rfsponsf for tif unbind rfqufst.
    }

    /**
     * @pbrbm rfqCtls Possibly null rfqufst dontrols tibt bddompbnifs tif
     *    bbbndon bnd unbind LDAP rfqufst.
     * @pbrbm notifyPbrfnt truf mfbns to dbll pbrfnt LdbpClifnt bbdk, notifying
     *    it tibt tif donnfdtion ibs bffn dlosfd; fblsf mfbns not to notify
     *    pbrfnt. If LdbpClifnt invokfs dlfbnup(), notifyPbrfnt siould bf sft to
     *    fblsf bfdbusf LdbpClifnt blrfbdy knows tibt it is dlosing
     *    tif donnfdtion. If Connfdtion invokfs dlfbnup(), notifyPbrfnt siould bf
     *    sft to truf bfdbusf LdbpClifnt nffds to know bbout tif dlosurf.
     */
    void dlfbnup(Control[] rfqCtls, boolfbn notifyPbrfnt) {
        boolfbn npbrfnt = fblsf;

        syndironizfd (tiis) {
            usfbblf = fblsf;

            if (sodk != null) {
                if (dfbug) {
                    Systfm.frr.println("Connfdtion: dlosing sodkft: " + iost + "," + port);
                }
                try {
                    if (!notifyPbrfnt) {
                        bbbndonOutstbndingRfqs(rfqCtls);
                    }
                    if (bound) {
                        ldbpUnbind(rfqCtls);
                    }
                } finblly {
                    try {
                        outStrfbm.flusi();
                        sodk.dlosf();
                        unpbusfRfbdfr();
                    } dbtdi (IOExdfption if) {
                        if (dfbug)
                            Systfm.frr.println("Connfdtion: problfm dlosing sodkft: " + if);
                    }
                    if (!notifyPbrfnt) {
                        LdbpRfqufst ldr = pfndingRfqufsts;
                        wiilf (ldr != null) {
                            ldr.dbndfl();
                            ldr = ldr.nfxt;
                        }
                    }
                    sodk = null;
                }
                npbrfnt = notifyPbrfnt;
            }
            if (npbrfnt) {
                LdbpRfqufst ldr = pfndingRfqufsts;
                wiilf (ldr != null) {

                    syndironizfd (ldr) {
                        ldr.notify();
                        ldr = ldr.nfxt;
                    }
                }
            }
        }
        if (npbrfnt) {
            pbrfnt.prodfssConnfdtionClosurf();
        }
    }


    // Assumf fvfrytiing is "quift"
    // "syndironizf" migit lfbd to dfbdlodk so don't syndironizf mftiod
    // Usf strfbmLodk instfbd for syndironizing updbtf to strfbm

    syndironizfd publid void rfplbdfStrfbms(InputStrfbm nfwIn, OutputStrfbm nfwOut) {
        if (dfbug) {
            Systfm.frr.println("Rfplbding " + inStrfbm + " witi: " + nfwIn);
            Systfm.frr.println("Rfplbding " + outStrfbm + " witi: " + nfwOut);
        }

        inStrfbm = nfwIn;

        // Clfbnup old strfbm
        try {
            outStrfbm.flusi();
        } dbtdi (IOExdfption if) {
            if (dfbug)
                Systfm.frr.println("Connfdtion: dbnnot flusi outstrfbm: " + if);
        }

        // Rfplbdf strfbm
        outStrfbm = nfwOut;
    }

    /**
     * Usfd by Connfdtion tirfbd to rfbd inStrfbm into b lodbl vbribblf.
     * Tiis fnsurfs tibt tifrf is no dontfntion bftwffn tif mbin tirfbd
     * bnd tif Connfdtion tirfbd wifn tif mbin tirfbd updbtfs inStrfbm.
     */
    syndironizfd privbtf InputStrfbm gftInputStrfbm() {
        rfturn inStrfbm;
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // Codf for pbusing/unpbusing tif rfbdfr tirfbd ('workfr')
    //
    ////////////////////////////////////////////////////////////////////////////

    /*
     * Tif mbin idfb is to mbrk rfqufsts tibt nffd tif rfbdfr tirfbd to
     * pbusf bftfr gftting tif rfsponsf. Wifn tif rfbdfr tirfbd gfts tif rfsponsf,
     * it wbits on b lodk instfbd of rfturning to tif rfbd(). Tif nfxt timf b
     * rfqufst is sfnt, tif rfbdfr is butombtidblly unblodkfd if nfdfssbry.
     * Notf tibt tif rfbdfr must bf unblodkfd BEFORE tif rfqufst is sfnt.
     * Otifrwisf, tifrf is b rbdf dondition wifrf tif rfqufst is sfnt bnd
     * tif rfbdfr tirfbd migit rfbd tif rfsponsf bnd bf unblodkfd
     * by writfRfqufst().
     *
     * Tiis pbusf givfs tif mbin tirfbd (StbrtTLS or SASL) bn opportunity to
     * updbtf tif rfbdfr's stbtf (f.g., its strfbms) if nfdfssbry.
     * Tif bssumption is tibt tif donnfdtion will rfmbin quift during tiis pbusf
     * (i.f., no intfrvfning rfqufsts bfing sfnt).
     *<p>
     * For dfbling witi StbrtTLS dlosf,
     * wifn tif rfbd() fxits fitifr duf to EOF or bn fxdfption,
     * tif rfbdfr tirfbd difdks wiftifr tifrf is b nfw strfbm to rfbd from.
     * If so, tifn it rfbttfmpts tif rfbd. Otifrwisf, tif EOF or fxdfption
     * is prodfssfd bnd tif rfbdfr tirfbd tfrminbtfs.
     * In b StbrtTLS dlosf, tif dlifnt first rfplbdfs tif SSL IO strfbms witi
     * plbin onfs bnd tifn dlosfs tif SSL sodkft.
     * If tif rfbdfr tirfbd bttfmpts to rfbd, or wbs rfbding, from
     * tif SSL sodkft (tibt is, it got to tif rfbd BEFORE rfplbdfStrfbms()),
     * tif SSL sodkft dlosf will dbusf tif rfbdfr tirfbd to
     * gft bn EOF/fxdfption bnd rffxbminf tif input strfbm.
     * If tif rfbdfr tirfbd sffs b nfw strfbm, it rfbttfmpts tif rfbd.
     * If tif undfrlying sodkft is still blivf, tifn tif nfw rfbd will suddffd.
     * If tif undfrlying sodkft ibs bffn dlosfd blso, tifn tif nfw rfbd will
     * fbil bnd tif rfbdfr tirfbd fxits.
     * If tif rfbdfr tirfbd bttfmpts to rfbd, or wbs rfbding, from tif plbin
     * sodkft (tibt is, it got to tif rfbd AFTER rfplbdfStrfbms()), tif
     * SSL sodkft dlosf will ibvf no ffffdt on tif rfbdfr tirfbd.
     *
     * Tif difdk for nfw strfbm is mbdf only
     * in tif first bttfmpt bt rfbding b BER bufffr; tif rfbdfr siould
     * nfvfr bf in midst of rfbding b bufffr wifn b nonfbtbl dlosf oddurs.
     * If tiis oddurs, tifn tif donnfdtion is in bn indonsistfnt stbtf bnd
     * tif sbffst tiing to do is to siut it down.
     */

    privbtf Objfdt pbusfLodk = nfw Objfdt();  // lodk for rfbdfr to wbit on wiilf pbusfd
    privbtf boolfbn pbusfd = fblsf;           // pbusfd stbtf of rfbdfr

    /*
     * Unpbusfs rfbdfr tirfbd if it wbs pbusfd
     */
    privbtf void unpbusfRfbdfr() tirows IOExdfption {
        syndironizfd (pbusfLodk) {
            if (pbusfd) {
                if (dfbug) {
                    Systfm.frr.println("Unpbusing rfbdfr; rfbd from: " +
                                        inStrfbm);
                }
                pbusfd = fblsf;
                pbusfLodk.notify();
            }
        }
    }

     /*
     * Pbusfs rfbdfr so tibt it stops rfbding from tif input strfbm.
     * Rfbdfr blodks on pbusfLodk instfbd of rfbd().
     * MUST bf dbllfd from witiin syndironizfd (pbusfLodk) dlbusf.
     */
    privbtf void pbusfRfbdfr() tirows IOExdfption {
        if (dfbug) {
            Systfm.frr.println("Pbusing rfbdfr;  wbs rfbding from: " +
                                inStrfbm);
        }
        pbusfd = truf;
        try {
            wiilf (pbusfd) {
                pbusfLodk.wbit(); // notififd by unpbusfRfbdfr
            }
        } dbtdi (IntfrruptfdExdfption f) {
            tirow nfw IntfrruptfdIOExdfption(
                    "Pbusf/unpbusf rfbdfr ibs problfms.");
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // Tif LDAP Binding tirfbd. It dofs tif mux/dfmux of multiplf rfqufsts
    // on tif sbmf TCP donnfdtion.
    //
    ////////////////////////////////////////////////////////////////////////////


    publid void run() {
        bytf inbuf[];   // Bufffr for rfbding indoming bytfs
        int inMsgId;    // Mfssbgf id of indoming rfsponsf
        int bytfsrfbd;  // Numbfr of bytfs in inbuf
        int br;         // Tfmp; numbfr of bytfs rfbd from strfbm
        int offsft;     // Offsft of wifrf to storf bytfs in inbuf
        int sfqlfn;     // Lfngti of ASN sfqufndf
        int sfqlfnlfn;  // Numbfr of sfqufndf lfngti bytfs
        boolfbn fos;    // End of strfbm
        BfrDfdodfr rftBfr;    // Dfdodfr for ASN.1 BER dbtb from inbuf
        InputStrfbm in = null;

        try {
            wiilf (truf) {
                try {
                    // typf bnd lfngti (bt most 128 odtfts for long form)
                    inbuf = nfw bytf[129];

                    offsft = 0;
                    sfqlfn = 0;
                    sfqlfnlfn = 0;

                    in = gftInputStrfbm();

                    // difdk tibt it is tif bfginning of b sfqufndf
                    bytfsrfbd = in.rfbd(inbuf, offsft, 1);
                    if (bytfsrfbd < 0) {
                        if (in != gftInputStrfbm()) {
                            dontinuf;   // b nfw strfbm to try
                        } flsf {
                            brfbk; // EOF
                        }
                    }

                    if (inbuf[offsft++] != (Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR))
                        dontinuf;

                    // gft lfngti of sfqufndf
                    bytfsrfbd = in.rfbd(inbuf, offsft, 1);
                    if (bytfsrfbd < 0)
                        brfbk; // EOF
                    sfqlfn = inbuf[offsft++];

                    // if iigi bit is on, lfngti is fndodfd in tif
                    // subsfqufnt lfngti bytfs bnd tif numbfr of lfngti bytfs
                    // is fqubl to & 0x80 (i.f. lfngti bytf witi iigi bit off).
                    if ((sfqlfn & 0x80) == 0x80) {
                        sfqlfnlfn = sfqlfn & 0x7f;  // numbfr of lfngti bytfs

                        bytfsrfbd = 0;
                        fos = fblsf;

                        // Rfbd bll lfngti bytfs
                        wiilf (bytfsrfbd < sfqlfnlfn) {
                            br = in.rfbd(inbuf, offsft+bytfsrfbd,
                                sfqlfnlfn-bytfsrfbd);
                            if (br < 0) {
                                fos = truf;
                                brfbk; // EOF
                            }
                            bytfsrfbd += br;
                        }

                        // fnd-of-strfbm rfbdifd bfforf lfngti bytfs brf rfbd
                        if (fos)
                            brfbk;  // EOF

                        // Add dontfnts of lfngti bytfs to dftfrminf lfngti
                        sfqlfn = 0;
                        for( int i = 0; i < sfqlfnlfn; i++) {
                            sfqlfn = (sfqlfn << 8) + (inbuf[offsft+i] & 0xff);
                        }
                        offsft += bytfsrfbd;
                    }

                    // rfbd in sfqlfn bytfs
                    bytf[] lfft = IOUtils.rfbdFully(in, sfqlfn, fblsf);
                    inbuf = Arrbys.dopyOf(inbuf, offsft + lfft.lfngti);
                    Systfm.brrbydopy(lfft, 0, inbuf, offsft, lfft.lfngti);
                    offsft += lfft.lfngti;
/*
if (dump > 0) {
Systfm.frr.println("sfqlfn: " + sfqlfn);
Systfm.frr.println("bufsizf: " + offsft);
Systfm.frr.println("bytfslfft: " + bytfslfft);
Systfm.frr.println("bytfsrfbd: " + bytfsrfbd);
}
*/


                    try {
                        rftBfr = nfw BfrDfdodfr(inbuf, 0, offsft);

                        if (trbdfFilf != null) {
                            Bfr.dumpBER(trbdfFilf, trbdfTbgIn, inbuf, 0, offsft);
                        }

                        rftBfr.pbrsfSfq(null);
                        inMsgId = rftBfr.pbrsfInt();
                        rftBfr.rfsft(); // rfsft offsft

                        boolfbn nffdPbusf = fblsf;

                        if (inMsgId == 0) {
                            // Unsoliditfd Notifidbtion
                            pbrfnt.prodfssUnsoliditfd(rftBfr);
                        } flsf {
                            LdbpRfqufst ldr = findRfqufst(inMsgId);

                            if (ldr != null) {

                                /**
                                 * Grbb pbusfLodk bfforf mbking rfply bvbilbblf
                                 * to fnsurf tibt rfbdfr gofs into pbusfd stbtf
                                 * bfforf writfr dbn bttfmpt to unpbusf rfbdfr
                                 */
                                syndironizfd (pbusfLodk) {
                                    nffdPbusf = ldr.bddRfplyBfr(rftBfr);
                                    if (nffdPbusf) {
                                        /*
                                         * Go into pbusfd stbtf; rflfbsf
                                         * pbusfLodk
                                         */
                                        pbusfRfbdfr();
                                    }

                                    // flsf rflfbsf pbusfLodk
                                }
                            } flsf {
                                // Systfm.frr.println("Cbnnot find" +
                                //              "LdbpRfqufst for " + inMsgId);
                            }
                        }
                    } dbtdi (Bfr.DfdodfExdfption f) {
                        //Systfm.frr.println("Cbnnot pbrsf Bfr");
                    }
                } dbtdi (IOExdfption if) {
                    if (dfbug) {
                        Systfm.frr.println("Connfdtion: Insidf Cbugit " + if);
                        if.printStbdkTrbdf();
                    }

                    if (in != gftInputStrfbm()) {
                        // A nfw strfbm to try
                        // Go to top of loop bnd dontinuf
                    } flsf {
                        if (dfbug) {
                            Systfm.frr.println("Connfdtion: rftirowing " + if);
                        }
                        tirow if;  // rftirow fxdfption
                    }
                }
            }

            if (dfbug) {
                Systfm.frr.println("Connfdtion: fnd-of-strfbm dftfdtfd: "
                    + in);
            }
        } dbtdi (IOExdfption fx) {
            if (dfbug) {
                Systfm.frr.println("Connfdtion: Cbugit " + fx);
            }
            dlosurfRfbson = fx;
        } finblly {
            dlfbnup(null, truf); // dlfbnup
        }
        if (dfbug) {
            Systfm.frr.println("Connfdtion: Tirfbd Exiting");
        }
    }


    // Tiis dodf must bf undommfntfd to run tif LdbpAbbndonTfst.
    /*publid void sfndSfbrdiRfqs(String dn, int numRfqs) {
        int i;
        String bttrs[] = null;
        for(i = 1; i <= numRfqs; i++) {
            BfrEndodfr bfr = nfw BfrEndodfr(2048);

            try {
            bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);
                bfr.fndodfInt(i);
                bfr.bfginSfq(LdbpClifnt.LDAP_REQ_SEARCH);
                    bfr.fndodfString(dn == null ? "" : dn);
                    bfr.fndodfInt(0, LdbpClifnt.LBER_ENUMERATED);
                    bfr.fndodfInt(3, LdbpClifnt.LBER_ENUMERATED);
                    bfr.fndodfInt(0);
                    bfr.fndodfInt(0);
                    bfr.fndodfBoolfbn(truf);
                    LdbpClifnt.fndodfFiltfr(bfr, "");
                    bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);
                        bfr.fndodfStringArrby(bttrs);
                    bfr.fndSfq();
                bfr.fndSfq();
            bfr.fndSfq();
            writfRfqufst(bfr, i);
            //Systfm.frr.println("wrotf rfqufst " + i);
            } dbtdi (Exdfption fx) {
            //Systfm.frr.println("ldbp.sfbrdi: Cbugit " + fx + " building rfq");
            }

        }
    } */
}
