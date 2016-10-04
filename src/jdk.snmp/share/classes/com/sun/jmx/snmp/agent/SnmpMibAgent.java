/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.bgfnt;



// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.SfrvidfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpEnginf;

/**
 * Abstrbdt dlbss for rfprfsfnting bn SNMP bgfnt.
 *
 * Tif dlbss is usfd by tif SNMP protodol bdbptor bs tif fntry point in
 * tif SNMP bgfnt to qufry.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpMibAgfnt
    implfmfnts SnmpMibAgfntMBfbn, MBfbnRfgistrbtion, Sfriblizbblf {

    /**
     * Dffbult donstrudtor.
     */
    publid SnmpMibAgfnt() {
    }

    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------

    /**
     * Initiblizfs tif MIB (witi no rfgistrbtion of tif MBfbns into tif
     * MBfbn sfrvfr).
     *
     * @fxdfption IllfgblAddfssExdfption Tif MIB dbn not bf initiblizfd.
     */
    publid bbstrbdt void init() tirows IllfgblAddfssExdfption;

    /**
     * Initiblizfs tif MIB but fbdi singlf MBfbn rfprfsfnting tif MIB
     * is insfrtfd into tif MBfbn sfrvfr.
     *
     * @pbrbm sfrvfr Tif MBfbn sfrvfr to rfgistfr tif sfrvidf witi.
     * @pbrbm nbmf Tif objfdt nbmf.
     *
     * @rfturn Tif nbmf of tif SNMP MIB rfgistfrfd.
     *
     * @fxdfption jbvb.lbng.Exdfption
     */
    @Ovfrridf
    publid bbstrbdt ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr,
                                           ObjfdtNbmf nbmf)
        tirows jbvb.lbng.Exdfption;

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    publid void postRfgistfr (Boolfbn rfgistrbtionDonf) {
    }

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    publid void prfDfrfgistfr() tirows jbvb.lbng.Exdfption {
    }

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    publid void postDfrfgistfr() {
    }

    /**
     * Prodfssfs b <CODE>gft</CODE> opfrbtion.
     * Tiis mftiod must updbtf tif SnmpVbrBinds dontbinfd in tif
     * <vbr>{@link SnmpMibRfqufst} rfq</vbr> pbrbmftfr.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to
     *            bf rftrifvfd. Tiis list is domposfd of
     *            <CODE>SnmpVbrBind</CODE> objfdts.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */
    @Ovfrridf
    publid bbstrbdt void gft(SnmpMibRfqufst rfq)
        tirows SnmpStbtusExdfption;

    /**
     * Prodfssfs b <CODE>gftNfxt</CODE> opfrbtion.
     * Tiis mftiod must updbtf tif SnmpVbrBinds dontbinfd in tif
     * <vbr>{@link SnmpMibRfqufst} rfq</vbr> pbrbmftfr.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif list of
     *            OIDs from wiidi tif nfxt vbribblfs siould bf rftrifvfd.
     *            Tiis list is domposfd of <CODE>SnmpVbrBind</CODE> objfdts.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */
    @Ovfrridf
    publid bbstrbdt void gftNfxt(SnmpMibRfqufst rfq)
        tirows SnmpStbtusExdfption;

    /**
     * Prodfssfs b <CODE>gftBulk</CODE> opfrbtion.
     * Tiis mftiod must updbtf tif SnmpVbrBinds dontbinfd in tif
     * <vbr>{@link SnmpMibRfqufst} rfq</vbr> pbrbmftfr.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to
     *            bf rftrifvfd. Tiis list is domposfd of
     *            <CODE>SnmpVbrBind</CODE> objfdts.
     *
     * @pbrbm nonRfpfbt Tif numbfr of vbribblfs, stbrting witi tif first
     *    vbribblf in tif vbribblf-bindings, for wiidi b singlf
     *    lfxidogrbpiid suddfssor is rfqufstfd.
     *
     * @pbrbm mbxRfpfbt Tif numbfr of lfxidogrbpiid suddfssors rfqufstfd
     *    for fbdi of tif lbst R vbribblfs. R is tif numbfr of vbribblfs
     *    following tif first <CODE>nonRfpfbt</CODE> vbribblfs for wiidi
     *    multiplf lfxidogrbpiid suddfssors brf rfqufstfd.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */
    @Ovfrridf
    publid bbstrbdt void gftBulk(SnmpMibRfqufst rfq, int nonRfpfbt,
                                 int mbxRfpfbt)
        tirows SnmpStbtusExdfption;

    /**
     * Prodfssfs b <CODE>sft</CODE> opfrbtion.
     * Tiis mftiod must updbtf tif SnmpVbrBinds dontbinfd in tif
     * <vbr>{@link SnmpMibRfqufst} rfq</vbr> pbrbmftfr.
     * Tiis mftiod is dbllfd during tif sfdond pibsf of tif SET two-pibsf
     * dommit.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to
     *            bf sft. Tiis list is domposfd of
     *            <CODE>SnmpVbrBind</CODE> objfdts.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     *            Tirowing bn fxdfption in tiis mftiod will brfbk tif
     *            btomidity of tif SET opfrbtion. Cbrf must bf tbkfn so tibt
     *            tif fxdfption is tirown in tif {@link #difdk(SnmpMibRfqufst)}
     *            mftiod instfbd.
     */
    @Ovfrridf
    publid bbstrbdt void sft(SnmpMibRfqufst rfq)
        tirows SnmpStbtusExdfption;


    /**
     * Cifdks if b <CODE>sft</CODE> opfrbtion dbn bf pfrformfd.
     * If tif opfrbtion dbn not bf pfrformfd, tif mftiod siould tirow bn
     * <CODE>SnmpStbtusExdfption</CODE>.
     * Tiis mftiod is dbllfd during tif first pibsf of tif SET two-pibsf
     * dommit.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to
     *            bf sft. Tiis list is domposfd of
     *            <CODE>SnmpVbrBind</CODE> objfdts.
     *
     * @fxdfption SnmpStbtusExdfption Tif <CODE>sft</CODE> opfrbtion
     *    dbnnot bf pfrformfd.
     */
    @Ovfrridf
    publid bbstrbdt void difdk(SnmpMibRfqufst rfq)
        tirows SnmpStbtusExdfption;

    /**
     * Gfts tif root objfdt idfntififr of tif MIB.
     * <P>Tif root objfdt idfntififr is tif objfdt idfntififr uniqufly
     * idfntifying tif MIB.
     *
     * @rfturn Tif root objfdt idfntififr.
     */
    publid bbstrbdt long[] gftRootOid();

    // ---------------------------------------------------------------------
    // GETTERS AND SETTERS
    // ---------------------------------------------------------------------

    /**
     * Gfts tif rfffrfndf to tif MBfbn sfrvfr in wiidi tif SNMP MIB is
     * rfgistfrfd.
     *
     * @rfturn Tif MBfbn sfrvfr or null if tif MIB is not rfgistfrfd in bny
     *     MBfbn sfrvfr.
     */
    @Ovfrridf
    publid MBfbnSfrvfr gftMBfbnSfrvfr() {
        rfturn sfrvfr;
    }

    /**
     * Gfts tif rfffrfndf to tif SNMP protodol bdbptor to wiidi tif MIB is
     * bound.
     *
     * @rfturn Tif SNMP MIB ibndlfr.
     */
    @Ovfrridf
    publid SnmpMibHbndlfr gftSnmpAdbptor() {
        rfturn bdbptor;
    }

    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdd tiis nfw MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm stbdk Tif SNMP MIB ibndlfr.
     */
    @Ovfrridf
    publid void sftSnmpAdbptor(SnmpMibHbndlfr stbdk) {
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis);
        }
        bdbptor = stbdk;
        if (bdbptor != null) {
            bdbptor.bddMib(tiis);
        }
    }

     /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdd tiis nfw MIB in tif SNMP MIB ibndlfr.
     * Tiis mftiod is to bf dbllfd to sft b spfdifid bgfnt to b spfdifid OID. Tiis dbn bf usfful wifn dfbling witi MIB ovfrlbpping.
     * Somf OID dbn bf implfmfntfd in morf tibn onf MIB. In tiis dbsf, tif OID nfbrfst tif bgfnt will bf usfd on SNMP opfrbtions.
     * @pbrbm stbdk Tif SNMP MIB ibndlfr.
     * @pbrbm oids Tif sft of OIDs tiis bgfnt implfmfnts.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftSnmpAdbptor(SnmpMibHbndlfr stbdk, SnmpOid[] oids) {
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis);
        }
        bdbptor = stbdk;
        if (bdbptor != null) {
            bdbptor.bddMib(tiis, oids);
        }
    }

    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdds tiis nfw MIB in tif SNMP MIB ibndlfr.
     * Adds b nfw dontfxtublizfd MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm stbdk Tif SNMP MIB ibndlfr.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf. If null is pbssfd, will bf rfgistfrfd in tif dffbult dontfxt.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftSnmpAdbptor(SnmpMibHbndlfr stbdk, String dontfxtNbmf) {
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis, dontfxtNbmf);
        }
        bdbptor = stbdk;
        if (bdbptor != null) {
            bdbptor.bddMib(tiis, dontfxtNbmf);
        }
    }
    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdds tiis nfw MIB in tif SNMP MIB ibndlfr.
     * Adds b nfw dontfxtublizfd MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm stbdk Tif SNMP MIB ibndlfr.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf. If null is pbssfd, will bf rfgistfrfd in tif dffbult dontfxt.
     * @pbrbm oids Tif sft of OIDs tiis bgfnt implfmfnts.
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftSnmpAdbptor(SnmpMibHbndlfr stbdk,
                               String dontfxtNbmf,
                               SnmpOid[] oids) {
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis, dontfxtNbmf);
        }
        bdbptor = stbdk;
        if (bdbptor != null) {
            bdbptor.bddMib(tiis, dontfxtNbmf, oids);
        }
    }

    /**
     * Gfts tif objfdt nbmf of tif SNMP protodol bdbptor to wiidi tif MIB
     * is bound.
     *
     * @rfturn Tif nbmf of tif SNMP protodol bdbptor.
     */
    @Ovfrridf
    publid ObjfdtNbmf gftSnmpAdbptorNbmf() {
        rfturn bdbptorNbmf;
    }

    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdd tiis nfw MIB in tif SNMP MIB ibndlfr
     * bssodibtfd to tif spfdififd <CODE>nbmf</CODE>.
     *
     * @pbrbm nbmf Tif nbmf of tif SNMP protodol bdbptor.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif SNMP protodol bdbptor dofs
     *     not fxist in tif MBfbn sfrvfr.
     *
     * @fxdfption SfrvidfNotFoundExdfption Tiis SNMP MIB is not rfgistfrfd
     *     in tif MBfbn sfrvfr or tif rfqufstfd sfrvidf is not supportfd.
     */
    @Ovfrridf
    publid void sftSnmpAdbptorNbmf(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption, SfrvidfNotFoundExdfption {

        if (sfrvfr == null) {
            tirow nfw SfrvidfNotFoundExdfption(mibNbmf + " is not rfgistfrfd in tif MBfbn sfrvfr");
        }
        // First rfmovf tif rfffrfndf on tif old bdbptor sfrvfr.
        //
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis);
        }

        // Tifn updbtf tif rfffrfndf to tif nfw bdbptor sfrvfr.
        //
        Objfdt[] pbrbms = {tiis};
        String[] signbturf = {"dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt"};
        try {
            bdbptor = (SnmpMibHbndlfr)(sfrvfr.invokf(nbmf, "bddMib", pbrbms,
                                                     signbturf));
        } dbtdi (InstbndfNotFoundExdfption f) {
            tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
        } dbtdi (RfflfdtionExdfption f) {
            tirow nfw SfrvidfNotFoundExdfption(nbmf.toString());
        } dbtdi (MBfbnExdfption f) {
            // Siould nfvfr oddur...
        }

        bdbptorNbmf = nbmf;
    }
    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdd tiis nfw MIB in tif SNMP MIB ibndlfr
     * bssodibtfd to tif spfdififd <CODE>nbmf</CODE>.
     * Tiis mftiod is to bf dbllfd to sft b spfdifid bgfnt to b spfdifid OID. Tiis dbn bf usfful wifn dfbling witi MIB ovfrlbpping.
     * Somf OID dbn bf implfmfntfd in morf tibn onf MIB. In tiis dbsf, tif OID nfbrfr bgfnt will bf usfd on SNMP opfrbtions.
     * @pbrbm nbmf Tif nbmf of tif SNMP protodol bdbptor.
     * @pbrbm oids Tif sft of OIDs tiis bgfnt implfmfnts.
     * @fxdfption InstbndfNotFoundExdfption Tif SNMP protodol bdbptor dofs
     *     not fxist in tif MBfbn sfrvfr.
     *
     * @fxdfption SfrvidfNotFoundExdfption Tiis SNMP MIB is not rfgistfrfd
     *     in tif MBfbn sfrvfr or tif rfqufstfd sfrvidf is not supportfd.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftSnmpAdbptorNbmf(ObjfdtNbmf nbmf, SnmpOid[] oids)
        tirows InstbndfNotFoundExdfption, SfrvidfNotFoundExdfption {

        if (sfrvfr == null) {
            tirow nfw SfrvidfNotFoundExdfption(mibNbmf + " is not rfgistfrfd in tif MBfbn sfrvfr");
        }
        // First rfmovf tif rfffrfndf on tif old bdbptor sfrvfr.
        //
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis);
        }

        // Tifn updbtf tif rfffrfndf to tif nfw bdbptor sfrvfr.
        //
        Objfdt[] pbrbms = {tiis, oids};
        String[] signbturf = {"dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt",
        oids.gftClbss().gftNbmf()};
        try {
            bdbptor = (SnmpMibHbndlfr)(sfrvfr.invokf(nbmf, "bddMib", pbrbms,
                                                     signbturf));
        } dbtdi (InstbndfNotFoundExdfption f) {
            tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
        } dbtdi (RfflfdtionExdfption f) {
            tirow nfw SfrvidfNotFoundExdfption(nbmf.toString());
        } dbtdi (MBfbnExdfption f) {
            // Siould nfvfr oddur...
        }

        bdbptorNbmf = nbmf;
    }
    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdd tiis nfw MIB in tif SNMP MIB ibndlfr
     * bssodibtfd to tif spfdififd <CODE>nbmf</CODE>.
     *
     * @pbrbm nbmf Tif nbmf of tif SNMP protodol bdbptor.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf. If null is pbssfd, will bf rfgistfrfd in tif dffbult dontfxt.
     * @fxdfption InstbndfNotFoundExdfption Tif SNMP protodol bdbptor dofs
     *     not fxist in tif MBfbn sfrvfr.
     *
     * @fxdfption SfrvidfNotFoundExdfption Tiis SNMP MIB is not rfgistfrfd
     *     in tif MBfbn sfrvfr or tif rfqufstfd sfrvidf is not supportfd.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftSnmpAdbptorNbmf(ObjfdtNbmf nbmf, String dontfxtNbmf)
        tirows InstbndfNotFoundExdfption, SfrvidfNotFoundExdfption {

        if (sfrvfr == null) {
            tirow nfw SfrvidfNotFoundExdfption(mibNbmf + " is not rfgistfrfd in tif MBfbn sfrvfr");
        }

        // First rfmovf tif rfffrfndf on tif old bdbptor sfrvfr.
        //
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis, dontfxtNbmf);
        }

        // Tifn updbtf tif rfffrfndf to tif nfw bdbptor sfrvfr.
        //
        Objfdt[] pbrbms = {tiis, dontfxtNbmf};
        String[] signbturf = {"dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt", "jbvb.lbng.String"};
        try {
            bdbptor = (SnmpMibHbndlfr)(sfrvfr.invokf(nbmf, "bddMib", pbrbms,
                                                     signbturf));
        } dbtdi (InstbndfNotFoundExdfption f) {
            tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
        } dbtdi (RfflfdtionExdfption f) {
            tirow nfw SfrvidfNotFoundExdfption(nbmf.toString());
        } dbtdi (MBfbnExdfption f) {
            // Siould nfvfr oddur...
        }

        bdbptorNbmf = nbmf;
    }

    /**
     * Sfts tif rfffrfndf to tif SNMP protodol bdbptor tirougi wiidi tif MIB
     * will bf SNMP bddfssiblf bnd bdd tiis nfw MIB in tif SNMP MIB ibndlfr
     * bssodibtfd to tif spfdififd <CODE>nbmf</CODE>.
     *
     * @pbrbm nbmf Tif nbmf of tif SNMP protodol bdbptor.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf. If null is pbssfd, will bf rfgistfrfd in tif dffbult dontfxt.
     * @pbrbm oids Tif sft of OIDs tiis bgfnt implfmfnts.
     * @fxdfption InstbndfNotFoundExdfption Tif SNMP protodol bdbptor dofs
     *     not fxist in tif MBfbn sfrvfr.
     *
     * @fxdfption SfrvidfNotFoundExdfption Tiis SNMP MIB is not rfgistfrfd
     *     in tif MBfbn sfrvfr or tif rfqufstfd sfrvidf is not supportfd.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftSnmpAdbptorNbmf(ObjfdtNbmf nbmf,
                                   String dontfxtNbmf, SnmpOid[] oids)
        tirows InstbndfNotFoundExdfption, SfrvidfNotFoundExdfption {

        if (sfrvfr == null) {
            tirow nfw SfrvidfNotFoundExdfption(mibNbmf + " is not rfgistfrfd in tif MBfbn sfrvfr");
        }

        // First rfmovf tif rfffrfndf on tif old bdbptor sfrvfr.
        //
        if (bdbptor != null) {
            bdbptor.rfmovfMib(tiis, dontfxtNbmf);
        }

        // Tifn updbtf tif rfffrfndf to tif nfw bdbptor sfrvfr.
        //
        Objfdt[] pbrbms = {tiis, dontfxtNbmf, oids};
        String[] signbturf = {"dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt", "jbvb.lbng.String", oids.gftClbss().gftNbmf()};
        try {
            bdbptor = (SnmpMibHbndlfr)(sfrvfr.invokf(nbmf, "bddMib", pbrbms,
                                                     signbturf));
        } dbtdi (InstbndfNotFoundExdfption f) {
            tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
        } dbtdi (RfflfdtionExdfption f) {
            tirow nfw SfrvidfNotFoundExdfption(nbmf.toString());
        } dbtdi (MBfbnExdfption f) {
            // Siould nfvfr oddur...
        }

        bdbptorNbmf = nbmf;
    }

    /**
     * Indidbtfs wiftifr or not tif MIB modulf is bound to b SNMP protodol
     * bdbptor.
     * As b rfmindfr, only bound MIBs dbn bf bddfssfd tirougi SNMP protodol
     * bdbptor.
     *
     * @rfturn <CODE>truf</CODE> if tif MIB modulf is bound,
     *         <CODE>fblsf</CODE> otifrwisf.
     */
    @Ovfrridf
    publid boolfbn gftBindingStbtf() {
        if (bdbptor == null)
            rfturn fblsf;
        flsf
            rfturn truf;
    }

    /**
     * Gfts tif MIB nbmf.
     *
     * @rfturn Tif MIB nbmf.
     */
    @Ovfrridf
    publid String gftMibNbmf() {
        rfturn mibNbmf;
    }

    /**
     * Tiis is b fbdtory mftiod for drfbting nfw SnmpMibRfqufst objfdts.
     * @pbrbm rfqPdu Tif rfdfivfd PDU.
     * @pbrbm vblist   Tif vfdtor of SnmpVbrBind objfdts in wiidi tif
     *        MIB dondfrnfd by tiis rfqufst is involvfd.
     * @pbrbm vfrsion  Tif protodol vfrsion of tif SNMP rfqufst.
     * @pbrbm usfrDbtb Usfr bllodbtfd dontfxtubl dbtb.
     *
     * @rfturn A nfw SnmpMibRfqufst objfdt.
     *
     * @sindf 1.5
     **/
    publid stbtid SnmpMibRfqufst nfwMibRfqufst(SnmpPdu rfqPdu,
                                               Vfdtor<SnmpVbrBind> vblist,
                                               int vfrsion,
                                               Objfdt usfrDbtb)
    {
        rfturn nfw SnmpMibRfqufstImpl(null,
                                      rfqPdu,
                                      vblist,
                                      vfrsion,
                                      usfrDbtb,
                                      null,
                                      SnmpDffinitions.noAutiNoPriv,
                                      gftSfdurityModfl(vfrsion),
                                      null,null);
    }
    /**
     * Tiis is b fbdtory mftiod for drfbting nfw SnmpMibRfqufst objfdts.
     * @pbrbm fnginf Tif lodbl fnginf.
     * @pbrbm rfqPdu Tif rfdfivfd pdu.
     * @pbrbm vblist Tif vfdtor of SnmpVbrBind objfdts in wiidi tif
     *        MIB dondfrnfd by tiis rfqufst is involvfd.
     * @pbrbm vfrsion Tif protodol vfrsion of tif SNMP rfqufst.
     * @pbrbm usfrDbtb Usfr bllodbtfd dontfxtubl dbtb.
     *
     * @rfturn A nfw SnmpMibRfqufst objfdt.
     *
     * @sindf 1.5
     **/
    publid stbtid SnmpMibRfqufst nfwMibRfqufst(SnmpEnginf fnginf,
                                               SnmpPdu rfqPdu,
                                               Vfdtor<SnmpVbrBind> vblist,
                                               int vfrsion,
                                               Objfdt usfrDbtb,
                                               String prindipbl,
                                               int sfdurityLfvfl,
                                               int sfdurityModfl,
                                               bytf[] dontfxtNbmf,
                                               bytf[] bddfssContfxtNbmf) {
        rfturn nfw SnmpMibRfqufstImpl(fnginf,
                                      rfqPdu,
                                      vblist,
                                      vfrsion,
                                      usfrDbtb,
                                      prindipbl,
                                      sfdurityLfvfl,
                                      sfdurityModfl,
                                      dontfxtNbmf,
                                      bddfssContfxtNbmf);
    }
    // ---------------------------------------------------------------------
    // PACKAGE METHODS
    // ---------------------------------------------------------------------

    /**
     * Prodfssfs b <CODE>gftBulk</CODE> opfrbtion using dbll to
     * <CODE>gftNfxt</CODE>.
     * Tif mftiod implfmfnts tif <CODE>gftBulk</CODE> opfrbtion by dblling
     * bppropribtfly tif <CODE>gftNfxt</CODE> mftiod.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst dontbining tif vbribblf list to bf
     *        rftrifvfd.
     *
     * @pbrbm nonRfpfbt Tif numbfr of vbribblfs, stbrting witi tif first
     *    vbribblf in tif vbribblf-bindings, for wiidi b singlf lfxidogrbpiid
     *    suddfssor is rfqufstfd.
     *
     * @pbrbm mbxRfpfbt Tif numbfr of lfxidogrbpiid suddfssors
     *    rfqufstfd for fbdi of tif lbst R vbribblfs. R is tif numbfr of
     *    vbribblfs following tif first nonRfpfbt vbribblfs for wiidi
     *    multiplf lfxidogrbpiid suddfssors brf rfqufstfd.
     *
     * @rfturn Tif vbribblf list dontbining rfturnfd vblufs.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */
    void gftBulkWitiGftNfxt(SnmpMibRfqufst rfq, int nonRfpfbt, int mbxRfpfbt)
        tirows SnmpStbtusExdfption {
        finbl Vfdtor<SnmpVbrBind> list = rfq.gftSubList();

        // RFC 1905, Sfdtion 4.2.3, p14
        finbl int L = list.sizf() ;
        finbl int N = Mbti.mbx(Mbti.min(nonRfpfbt, L), 0) ;
        finbl int M = Mbti.mbx(mbxRfpfbt, 0) ;
        finbl int R = L - N ;

        // Lft's build tif vbrBindList for tif rfsponsf pdu
        //
        // int frrorStbtus = SnmpDffinitions.snmpRspNoError ;
        // int frrorIndfx = 0 ;
        if (L != 0) {

            // Non-rfpfbtfrs bnd first row of rfpfbtfrs
            //
            gftNfxt(rfq);

            // Now tif rfmbining rfpfbtfrs
            //
            Vfdtor<SnmpVbrBind> rfpfbtfrs= splitFrom(list, N);
            SnmpMibRfqufstImpl rfpfbtfdRfq =
                nfw SnmpMibRfqufstImpl(rfq.gftEnginf(),
                                       rfq.gftPdu(),
                                       rfpfbtfrs,
                                       SnmpDffinitions.snmpVfrsionTwo,
                                       rfq.gftUsfrDbtb(),
                                       rfq.gftPrindipbl(),
                                       rfq.gftSfdurityLfvfl(),
                                       rfq.gftSfdurityModfl(),
                                       rfq.gftContfxtNbmf(),
                                       rfq.gftAddfssContfxtNbmf());
            for (int i = 2 ; i <= M ; i++) {
                gftNfxt(rfpfbtfdRfq);
                dondbtVfdtor(rfq, rfpfbtfrs);
            }
        }
    }


    // ---------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------

    /**
     * Tiis mftiod drfbtfs b nfw Vfdtor wiidi dofs not dontbin tif first
     * flfmfnt up to tif spfdififd limit.
     *
     * @pbrbm originbl Tif originbl vfdtor.
     * @pbrbm limit Tif limit.
     */
    privbtf Vfdtor<SnmpVbrBind> splitFrom(Vfdtor<SnmpVbrBind> originbl, int limit) {

        int mbx= originbl.sizf();
        Vfdtor<SnmpVbrBind> rfsult= nfw Vfdtor<>(mbx - limit);
        int i= limit;

        // Ok tif loop looks b bit strbngf. But in ordfr to improvf tif
        // pfrf, wf try to bvoid rfffrfndf to tif limit vbribblf from
        // witiin tif loop ...
        //
        for(Enumfrbtion<SnmpVbrBind> f= originbl.flfmfnts(); f.ibsMorfElfmfnts(); --i) {
            SnmpVbrBind vbr= f.nfxtElfmfnt();
            if (i >0)
                dontinuf;
            rfsult.bddElfmfnt(nfw SnmpVbrBind(vbr.oid, vbr.vbluf));
        }
        rfturn rfsult;
    }

    privbtf void dondbtVfdtor(SnmpMibRfqufst rfq, Vfdtor<SnmpVbrBind> sourdf) {
        for(Enumfrbtion<SnmpVbrBind> f= sourdf.flfmfnts(); f.ibsMorfElfmfnts(); ) {
            SnmpVbrBind vbr= f.nfxtElfmfnt();
            // Wf nffd to duplidbtf tif SnmpVbrBind otifrwisf it is going
            // to bf ovfrlobdfd by tif nfxt gft Nfxt ...
            rfq.bddVbrBind(nfw SnmpVbrBind(vbr.oid, vbr.vbluf));
        }
    }

    privbtf stbtid int gftSfdurityModfl(int vfrsion) {
        switdi(vfrsion) {
        dbsf SnmpDffinitions.snmpVfrsionOnf:
            rfturn SnmpDffinitions.snmpV1SfdurityModfl;
        dffbult:
            rfturn SnmpDffinitions.snmpV2SfdurityModfl;
        }
    }

    // ---------------------------------------------------------------------
    // PROTECTED VARIABLES
    // ---------------------------------------------------------------------

    /**
     * Tif objfdt nbmf of tif MIB.
     * @sfribl
     */
    protfdtfd String mibNbmf;

    /**
     * Tif rfffrfndf to tif MBfbn sfrvfr.
     * @sfribl
     */
    protfdtfd MBfbnSfrvfr sfrvfr;

    // ---------------------------------------------------------------------
    // PRIVATE VARIABLES
    // ---------------------------------------------------------------------

    /**
     * Tif objfdt nbmf of tif SNMP protodol bdbptor.
     * @sfribl
     */
    privbtf ObjfdtNbmf bdbptorNbmf;

    /**
     * Tif rfffrfndf to tif SNMP stbdk.
     */
    privbtf trbnsifnt SnmpMibHbndlfr bdbptor;
}
