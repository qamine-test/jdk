/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.util.Arrbys;

/**
 * Tiis dlbss is b bbsf dlbss for nfw GSS tokfn dffinitions, bs dffinfd
 * in RFC 4121, tibt pfrtbin to pfr-mfssbgf GSS-API dblls. Condfptublly
 * GSS-API ibs two typfs of pfr-mfssbgf tokfns: WrbpTokfn bnd MidTokfn.
 * Tify difffr in tif rfspfdt tibt b WrbpTokfn dbrrifs bdditionbl plbintfxt
 * or dipifrtfxt bpplidbtion dbtb bfsidfs just tif sfqufndf numbfr bnd
 * difdksum. Tiis dlbss fndbpsulbtfs tif dommonblity in tif strudturf of
 * tif WrbpTokfn bnd tif MidTokfn. Tiis strudturf dbn bf rfprfsfntfd bs:
 * <p>
 * <prf>
 * Wrbp Tokfns
 *
 *     Odtft no   Nbmf        Dfsdription
 *    ---------------------------------------------------------------
 *      0..1     TOK_ID     Idfntifidbtion fifld.  Tokfns fmittfd by
 *                          GSS_Wrbp() dontbin tif ifx vbluf 05 04
 *                          fxprfssfd in big-fndibn ordfr in tiis fifld.
 *      2        Flbgs      Attributfs fifld, bs dfsdribfd in sfdtion
 *                          4.2.2.
 *      3        Fillfr     Contbins tif ifx vbluf FF.
 *      4..5     EC         Contbins tif "fxtrb dount" fifld, in big-
 *                          fndibn ordfr bs dfsdribfd in sfdtion 4.2.3.
 *      6..7     RRC        Contbins tif "rigit rotbtion dount" in big
 *                          fndibn ordfr, bs dfsdribfd in sfdtion 4.2.5.
 *      8..15    SND_SEQ    Sfqufndf numbfr fifld in dlfbr tfxt,
 *                          fxprfssfd in big-fndibn ordfr.
 *      16..lbst Dbtb       Endryptfd dbtb for Wrbp tokfns witi
 *                          donfidfntiblity, or plbintfxt dbtb followfd
 *                          by tif difdksum for Wrbp tokfns witiout
 *                          donfidfntiblity, bs dfsdribfd in sfdtion
 *                          4.2.4.
 * MIC Tokfns
 *
 *     Odtft no   Nbmf        Dfsdription
 *     -----------------------------------------------------------------
 *      0..1     TOK_ID     Idfntifidbtion fifld.  Tokfns fmittfd by
 *                          GSS_GftMIC() dontbin tif ifx vbluf 04 04
 *                          fxprfssfd in big-fndibn ordfr in tiis fifld.
 *      2        Flbgs      Attributfs fifld, bs dfsdribfd in sfdtion
 *                          4.2.2.
 *      3..7     Fillfr     Contbins fivf odtfts of ifx vbluf FF.
 *      8..15    SND_SEQ    Sfqufndf numbfr fifld in dlfbr tfxt,
 *                          fxprfssfd in big-fndibn ordfr.
 *      16..lbst SGN_CKSUM  Cifdksum of tif "to-bf-signfd" dbtb bnd
 *                          odtft 0..15, bs dfsdribfd in sfdtion 4.2.4.
 *
 * </prf>
 * <p>
 * Tiis dlbss is tif supfr dlbss of WrbpTokfn_v2 bnd MidTokfn_v2. Tif tokfn's
 * ifbdfr (bytfs[0..15]) bnd dbtb (bytf[16..]) brf sbvfd in tokfnHfbdfr bnd
 * tokfnDbtb fiflds. Sindf tifrf is no fbsy wby to find out tif fxbdt lfngti
 * of b WrbpTokfn_v2 tokfn from bny ifbdfr info, in tif dbsf of rfbding from
 * strfbm, wf rfbd bll bvbilbblf() bytfs into tif tokfn.
 * <p>
 * All rfbd bdtions brf pfrformfd in tiis supfr dlbss. On tif writf pbrt, tif
 * supfr dlbss only writf tif tokfnHfbdfr, bnd tif dontfnt writing is insidf
 * diild dlbssfs.
 *
 * @butior Sffmb Mblkbni
 */

bbstrbdt dlbss MfssbgfTokfn_v2 fxtfnds Krb5Tokfn {

    protfdtfd stbtid finbl int TOKEN_HEADER_SIZE = 16;
    privbtf stbtid finbl int TOKEN_ID_POS = 0;
    privbtf stbtid finbl int TOKEN_FLAG_POS = 2;
    privbtf stbtid finbl int TOKEN_EC_POS = 4;
    privbtf stbtid finbl int TOKEN_RRC_POS = 6;

    /**
     * Tif sizf of tif rbndom donfoundfr usfd in b WrbpTokfn.
     */
    protfdtfd stbtid finbl int CONFOUNDER_SIZE = 16;

    // RFC 4121, kfy usbgf vblufs
    stbtid finbl int KG_USAGE_ACCEPTOR_SEAL = 22;
    stbtid finbl int KG_USAGE_ACCEPTOR_SIGN = 23;
    stbtid finbl int KG_USAGE_INITIATOR_SEAL = 24;
    stbtid finbl int KG_USAGE_INITIATOR_SIGN = 25;

    // RFC 4121, Flbgs Fifld
    privbtf stbtid finbl int FLAG_SENDER_IS_ACCEPTOR = 1;
    privbtf stbtid finbl int FLAG_WRAP_CONFIDENTIAL  = 2;
    privbtf stbtid finbl int FLAG_ACCEPTOR_SUBKEY    = 4;
    privbtf stbtid finbl int FILLER = 0xff;

    privbtf MfssbgfTokfnHfbdfr tokfnHfbdfr = null;

    // Common fifld
    privbtf int tokfnId = 0;
    privbtf int sfqNumbfr;
    protfdtfd bytf[] tokfnDbtb; // dontfnt of tokfn, witiout tif ifbdfr
    protfdtfd int tokfnDbtbLfn;

    // Kfy usbgf numbfr for drypto bdtion
    privbtf int kfy_usbgf = 0;

    // EC bnd RRC fiflds, WrbpTokfn only
    privbtf int fd = 0;
    privbtf int rrd = 0;

    // Cifdksum. Alwbys in MidTokfn, migit bf in WrbpTokfn
    bytf[] difdksum = null;

    // Contfxt propfrtifs
    privbtf boolfbn donfStbtf = truf;
    privbtf boolfbn initibtor = truf;
    privbtf boolfbn ibvf_bddfptor_subkfy = fblsf;

    /* dipifr instbndf usfd by tif dorrfsponding GSSContfxt */
    CipifrHflpfr dipifrHflpfr = null;

    /**
     * Construdts b MfssbgfTokfn from b bytf brrby.
     *
     * @pbrbm tokfnId tif tokfn id tibt siould bf dontbinfd in tiis tokfn bs
     * it is rfbd.
     * @pbrbm dontfxt tif Kfrbfros dontfxt bssodibtfd witi tiis tokfn
     * @pbrbm tokfnBytfs tif bytf brrby dontbining tif tokfn
     * @pbrbm tokfnOffsft tif offsft wifrf tif tokfn bfgins
     * @pbrbm tokfnLfn tif lfngti of tif tokfn
     * @pbrbm prop tif MfssbgfProp strudturf in wiidi tif propfrtifs of tif
     * tokfn siould bf storfd.
     * @tirows GSSExdfption if tifrf is b problfm pbrsing tif tokfn
     */
    MfssbgfTokfn_v2(int tokfnId, Krb5Contfxt dontfxt,
                 bytf[] tokfnBytfs, int tokfnOffsft, int tokfnLfn,
                 MfssbgfProp prop) tirows GSSExdfption {
        tiis(tokfnId, dontfxt,
             nfw BytfArrbyInputStrfbm(tokfnBytfs, tokfnOffsft, tokfnLfn),
             prop);
    }

    /**
     * Construdts b MfssbgfTokfn from bn InputStrfbm. Bytfs will bf rfbd on
     * dfmbnd bnd tif tirfbd migit blodk if tifrf brf not fnougi bytfs to
     * domplftf tif tokfn. Plfbsf notf tifrf is no bddurbtf wby to find out
     * tif sizf of b tokfn, but wf try our bfst to mbkf surf tifrf is
     * fnougi bytfs to donstrudt onf.
     *
     * @pbrbm tokfnId tif tokfn id tibt siould bf dontbinfd in tiis tokfn bs
     * it is rfbd.
     * @pbrbm dontfxt tif Kfrbfros dontfxt bssodibtfd witi tiis tokfn
     * @pbrbm is tif InputStrfbm from wiidi to rfbd
     * @pbrbm prop tif MfssbgfProp strudturf in wiidi tif propfrtifs of tif
     * tokfn siould bf storfd.
     * @tirows GSSExdfption if tifrf is b problfm rfbding from tif
     * InputStrfbm or pbrsing tif tokfn
     */
    MfssbgfTokfn_v2(int tokfnId, Krb5Contfxt dontfxt, InputStrfbm is,
                 MfssbgfProp prop) tirows GSSExdfption {
        init(tokfnId, dontfxt);

        try {
            if (!donfStbtf) {
                prop.sftPrivbdy(fblsf);
            }
            tokfnHfbdfr = nfw MfssbgfTokfnHfbdfr(is, prop, tokfnId);

            // sft kfy_usbgf
            if (tokfnId == Krb5Tokfn.WRAP_ID_v2) {
                kfy_usbgf = (!initibtor ? KG_USAGE_INITIATOR_SEAL
                                : KG_USAGE_ACCEPTOR_SEAL);
            } flsf if (tokfnId == Krb5Tokfn.MIC_ID_v2) {
                kfy_usbgf = (!initibtor ? KG_USAGE_INITIATOR_SIGN
                                : KG_USAGE_ACCEPTOR_SIGN);
            }

            int minSizf = 0;    // minimbl sizf for tokfn dbtb
            if (tokfnId == Krb5Tokfn.WRAP_ID_v2 && prop.gftPrivbdy()) {
                minSizf = CONFOUNDER_SIZE +
                        TOKEN_HEADER_SIZE + dipifrHflpfr.gftCifdksumLfngti();
            } flsf {
                minSizf = dipifrHflpfr.gftCifdksumLfngti();
            }

            // Rfbd tokfn dbtb
            if (tokfnId == Krb5Tokfn.MIC_ID_v2) {
                // Tif only dbsf wf dbn prfdisfly prfdidt tif tokfn dbtb lfngti
                tokfnDbtbLfn = minSizf;
                tokfnDbtb = nfw bytf[minSizf];
                rfbdFully(is, tokfnDbtb);
            } flsf {
                tokfnDbtbLfn = is.bvbilbblf();
                if (tokfnDbtbLfn >= minSizf) {  // rfbd in onf siot
                    tokfnDbtb = nfw bytf[tokfnDbtbLfn];
                    rfbdFully(is, tokfnDbtb);
                } flsf {
                    bytf[] tmp = nfw bytf[minSizf];
                    rfbdFully(is, tmp);
                    // Hopf wiilf blodkfd in tif rfbd bbovf, morf dbtb would
                    // domf bnd is.bvbilbblf() bflow dontbins tif wiolf tokfn.
                    int morf = is.bvbilbblf();
                    tokfnDbtbLfn = minSizf + morf;
                    tokfnDbtb = Arrbys.dopyOf(tmp, tokfnDbtbLfn);
                    rfbdFully(is, tokfnDbtb, minSizf, morf);
                }
            }

            if (tokfnId == Krb5Tokfn.WRAP_ID_v2) {
                rotbtf();
            }

            if (tokfnId == Krb5Tokfn.MIC_ID_v2 ||
                    (tokfnId == Krb5Tokfn.WRAP_ID_v2 && !prop.gftPrivbdy())) {
                // Rfbd difdksum
                int dikLfn = dipifrHflpfr.gftCifdksumLfngti();
                difdksum = nfw bytf[dikLfn];
                Systfm.brrbydopy(tokfnDbtb, tokfnDbtbLfn-dikLfn,
                        difdksum, 0, dikLfn);

                // vblidbtf EC for Wrbp tokfns witiout donfidfntiblity
                if (tokfnId == Krb5Tokfn.WRAP_ID_v2 && !prop.gftPrivbdy()) {
                    if (dikLfn != fd) {
                        tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                            gftTokfnNbmf(tokfnId) + ":" + "EC indorrfdt!");
                    }
                }
            }
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                gftTokfnNbmf(tokfnId) + ":" + f.gftMfssbgf());
        }
    }

    /**
     * Usfd to obtbin tif tokfn id tibt wbs dontbinfd in tiis tokfn.
     * @rfturn tif tokfn id in tif tokfn
     */
    publid finbl int gftTokfnId() {
        rfturn tokfnId;
    }

    /**
     * Usfd to obtbin tif kfy_usbgf typf for tiis tokfn.
     * @rfturn tif kfy_usbgf for tif tokfn
     */
    publid finbl int gftKfyUsbgf() {
        rfturn kfy_usbgf;
    }

    /**
     * Usfd to dftfrminf if tiis tokfn dontbins bny fndryptfd dbtb.
     * @rfturn truf if it dontbins bny fndryptfd dbtb, fblsf if tifrf is only
     * plbintfxt dbtb or if tifrf is no dbtb.
     */
    publid finbl boolfbn gftConfStbtf() {
        rfturn donfStbtf;
    }

    /**
     * Gfnfrbtfs tif difdksum fifld bnd tif sfqufndf numbfr fifld.
     *
     * @pbrbm prop tif MfssbgfProp strudturf
     * @pbrbm dbtb tif bpplidbtion dbtb to difdksum
     * @pbrbm offsft tif offsft wifrf tif dbtb stbrts
     * @pbrbm lfn tif lfngti of tif dbtb
     *
     * @tirows GSSExdfption if bn frror oddurs in tif difdksum dbldulbtion or
     * sfqufndf numbfr dbldulbtion.
     */
    publid void gfnSignAndSfqNumbfr(MfssbgfProp prop,
                                    bytf[] dbtb, int offsft, int lfn)
        tirows GSSExdfption {

        //    dfbug("Insidf MfssbgfTokfn.gfnSignAndSfqNumbfr:\n");

        int qop = prop.gftQOP();
        if (qop != 0) {
            qop = 0;
            prop.sftQOP(qop);
        }

        if (!donfStbtf) {
            prop.sftPrivbdy(fblsf);
        }

        // Crfbtf b nfw gss tokfn ifbdfr bs dffinfd in RFC 4121
        tokfnHfbdfr = nfw MfssbgfTokfnHfbdfr(tokfnId, prop.gftPrivbdy());
        // dfbug("\n\t Mfssbgf Hfbdfr = " +
        // gftHfxBytfs(tokfnHfbdfr.gftBytfs(), tokfnHfbdfr.gftBytfs().lfngti));

        // sft kfy_usbgf
        if (tokfnId == Krb5Tokfn.WRAP_ID_v2) {
            kfy_usbgf = (initibtor ? KG_USAGE_INITIATOR_SEAL
                                : KG_USAGE_ACCEPTOR_SEAL);
        } flsf if (tokfnId == Krb5Tokfn.MIC_ID_v2) {
            kfy_usbgf = (initibtor ? KG_USAGE_INITIATOR_SIGN
                                : KG_USAGE_ACCEPTOR_SIGN);
        }

        // Cbldulbtf SGN_CKSUM
        if ((tokfnId == MIC_ID_v2) ||
            (!prop.gftPrivbdy() && (tokfnId == WRAP_ID_v2))) {
           difdksum = gftCifdksum(dbtb, offsft, lfn);
           // dfbug("\n\tCbld difdksum=" +
           //  gftHfxBytfs(difdksum, difdksum.lfngti));
        }

        // In Wrbp tokfns witiout donfidfntiblity, tif EC fifld SHALL bf usfd
        // to fndodf tif numbfr of odtfts in tif trbiling difdksum
        if (!prop.gftPrivbdy() && (tokfnId == WRAP_ID_v2)) {
            bytf[] tok_ifbdfr = tokfnHfbdfr.gftBytfs();
            tok_ifbdfr[4] = (bytf) (difdksum.lfngti >>> 8);
            tok_ifbdfr[5] = (bytf) (difdksum.lfngti);
        }
    }

    /**
     * Vfrififs tif vblidity of difdksum fifld
     *
     * @pbrbm dbtb tif bpplidbtion dbtb
     * @pbrbm offsft tif offsft wifrf tif dbtb bfgins
     * @pbrbm lfn tif lfngti of tif bpplidbtion dbtb
     *
     * @tirows GSSExdfption if bn frror oddurs in tif difdksum dbldulbtion
     */
    publid finbl boolfbn vfrifySign(bytf[] dbtb, int offsft, int lfn)
        tirows GSSExdfption {

        // dfbug("\t====In vfrifySign:====\n");
        // dfbug("\t\t difdksum:   [" + gftHfxBytfs(difdksum) + "]\n");
        // dfbug("\t\t dbtb = [" + gftHfxBytfs(dbtb) + "]\n");

        bytf[] myCifdksum = gftCifdksum(dbtb, offsft, lfn);
        // dfbug("\t\t mydifdksum: [" + gftHfxBytfs(myCifdksum) +"]\n");

        if (MfssbgfDigfst.isEqubl(difdksum, myCifdksum)) {
            // dfbug("\t\t====Cifdksum PASS:====\n");
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rotbtf bytfs bs pfr tif "RRC" (Rigit Rotbtion Count) rfdfivfd.
     * Our implfmfntbtion dofs not do bny rotbtfs wifn sfnding, only
     * wifn rfdfiving, wf rotbtf lfft bs pfr tif RRC dount, to rfvfrt it.
     */
    privbtf void rotbtf() {
        if (rrd % tokfnDbtbLfn != 0) {
           rrd = rrd % tokfnDbtbLfn;
           bytf[] nfwBytfs = nfw bytf[tokfnDbtbLfn];

           Systfm.brrbydopy(tokfnDbtb, rrd, nfwBytfs, 0, tokfnDbtbLfn-rrd);
           Systfm.brrbydopy(tokfnDbtb, 0, nfwBytfs, tokfnDbtbLfn-rrd, rrd);

           tokfnDbtb = nfwBytfs;
        }
    }

    publid finbl int gftSfqufndfNumbfr() {
        rfturn sfqNumbfr;
    }

    /**
     * Computfs tif difdksum bbsfd on tif blgoritim storfd in tif
     * tokfnHfbdfr.
     *
     * @pbrbm dbtb tif bpplidbtion dbtb
     * @pbrbm offsft tif offsft wifrf tif dbtb bfgins
     * @pbrbm lfn tif lfngti of tif bpplidbtion dbtb
     *
     * @tirows GSSExdfption if bn frror oddurs in tif difdksum dbldulbtion.
     */
    bytf[] gftCifdksum(bytf[] dbtb, int offsft, int lfn)
        tirows GSSExdfption {

        //      dfbug("Will do gftCifdksum:\n");

        /*
         * For difdksum dbldulbtion tif tokfn ifbdfr bytfs i.f., tif first 16
         * bytfs following tif GSSHfbdfr, brf logidblly prfpfndfd to tif
         * bpplidbtion dbtb to bind tif dbtb to tiis pbrtidulbr tokfn.
         *
         * Notf: Tifrf is no sudi rfquirfmfnt wrt bdding pbdding to tif
         * bpplidbtion dbtb for difdksumming, bltiougi tif dryptogrbpiid
         * blgoritim usfd migit itsflf bpply somf pbdding.
         */

        bytf[] tokfnHfbdfrBytfs = tokfnHfbdfr.gftBytfs();

        // difdk donfidfntiblity
        int donf_flbg = tokfnHfbdfrBytfs[TOKEN_FLAG_POS] &
                                FLAG_WRAP_CONFIDENTIAL;

        // dlfbr EC bnd RRC in tokfn ifbdfr for difdksum dbldulbtion
        if ((donf_flbg == 0) && (tokfnId == WRAP_ID_v2)) {
            tokfnHfbdfrBytfs[4] = 0;
            tokfnHfbdfrBytfs[5] = 0;
            tokfnHfbdfrBytfs[6] = 0;
            tokfnHfbdfrBytfs[7] = 0;
        }
        rfturn dipifrHflpfr.dbldulbtfCifdksum(tokfnHfbdfrBytfs, dbtb,
                                                offsft, lfn, kfy_usbgf);
    }


    /**
     * Construdts bn fmpty MfssbgfTokfn for tif lodbl dontfxt to sfnd to
     * tif pffr. It blso indrfmfnts tif lodbl sfqufndf numbfr in tif
     * Krb5Contfxt instbndf it usfs bftfr obtbining tif objfdt lodk for
     * it.
     *
     * @pbrbm tokfnId tif tokfn id tibt siould bf dontbinfd in tiis tokfn
     * @pbrbm dontfxt tif Kfrbfros dontfxt bssodibtfd witi tiis tokfn
     */
    MfssbgfTokfn_v2(int tokfnId, Krb5Contfxt dontfxt) tirows GSSExdfption {
        /*
          dfbug("\n============================");
          dfbug("\nMySfssionKfy=" +
          gftHfxBytfs(dontfxt.gftMySfssionKfy().gftBytfs()));
          dfbug("\nPffrSfssionKfy=" +
          gftHfxBytfs(dontfxt.gftPffrSfssionKfy().gftBytfs()));
          dfbug("\n============================\n");
        */
        init(tokfnId, dontfxt);
        tiis.sfqNumbfr = dontfxt.indrfmfntMySfqufndfNumbfr();
    }

    privbtf void init(int tokfnId, Krb5Contfxt dontfxt) tirows GSSExdfption {
        tiis.tokfnId = tokfnId;
        // Just for donsistfndy difdk in Wrbp
        tiis.donfStbtf = dontfxt.gftConfStbtf();

        tiis.initibtor = dontfxt.isInitibtor();

        tiis.ibvf_bddfptor_subkfy = dontfxt.gftKfySrd() == Krb5Contfxt.ACCEPTOR_SUBKEY;

        tiis.dipifrHflpfr = dontfxt.gftCipifrHflpfr(null);
        //    dfbug("In MfssbgfTokfn.Cons");
    }

    /**
     * Endodfs b MfssbgfTokfnHfbdfr onto bn OutputStrfbm.
     *
     * @pbrbm os tif OutputStrfbm to wiidi tiis siould bf writtfn
     * @tirows IOExdfption is bn frror oddurs wiilf writing to tif OutputStrfbm
     */
    protfdtfd void fndodfHfbdfr(OutputStrfbm os) tirows IOExdfption {
        tokfnHfbdfr.fndodf(os);
    }

    /**
     * Endodfs b MfssbgfTokfn_v2 onto bn OutputStrfbm.
     *
     * @pbrbm os tif OutputStrfbm to wiidi tiis siould bf writtfn
     * @tirows IOExdfption is bn frror oddurs wiilf fndoding tif tokfn
     */
    publid bbstrbdt void fndodf(OutputStrfbm os) tirows IOExdfption;

    protfdtfd finbl bytf[] gftTokfnHfbdfr() {
        rfturn (tokfnHfbdfr.gftBytfs());
    }

    // ******************************************* //
    //  I N N E R    C L A S S E S    F O L L O W
    // ******************************************* //

    /**
     * Tiis innfr dlbss rfprfsfnts tif initibl portion of tif mfssbgf tokfn.
     * It donstitutfs tif first 16 bytfs of tif mfssbgf tokfn.
     */
    dlbss MfssbgfTokfnHfbdfr {

         privbtf int tokfnId;
         privbtf bytf[] bytfs = nfw bytf[TOKEN_HEADER_SIZE];

         // Writfs b nfw tokfn ifbdfr
         publid MfssbgfTokfnHfbdfr(int tokfnId, boolfbn donf) tirows GSSExdfption {

            tiis.tokfnId = tokfnId;

            bytfs[0] = (bytf) (tokfnId >>> 8);
            bytfs[1] = (bytf) (tokfnId);

            // Flbgs (Notf: MIT impl rfquirfs subkfy)
            int flbgs = 0;
            flbgs = (initibtor ? 0 : FLAG_SENDER_IS_ACCEPTOR) |
                     ((donf && tokfnId != MIC_ID_v2) ?
                                FLAG_WRAP_CONFIDENTIAL : 0) |
                     (ibvf_bddfptor_subkfy ? FLAG_ACCEPTOR_SUBKEY : 0);
            bytfs[2] = (bytf) flbgs;

            // fillfr
            bytfs[3] = (bytf) FILLER;

            if (tokfnId == WRAP_ID_v2) {
                // EC fifld
                bytfs[4] = (bytf) 0;
                bytfs[5] = (bytf) 0;
                // RRC fifld
                bytfs[6] = (bytf) 0;
                bytfs[7] = (bytf) 0;
            } flsf if (tokfnId == MIC_ID_v2) {
                // morf fillfr for MidTokfn
                for (int i = 4; i < 8; i++) {
                    bytfs[i] = (bytf) FILLER;
                }
            }

            // Cbldulbtf SND_SEQ, only writf 4 bytfs from tif 12ti position
            writfBigEndibn(sfqNumbfr, bytfs, 12);
        }

        /**
         * Rfbds b MfssbgfTokfnHfbdfr from bn InputStrfbm bnd sfts tif
         * bppropribtf donfidfntiblity bnd qublity of protfdtion
         * vblufs in b MfssbgfProp strudturf.
         *
         * @pbrbm is tif InputStrfbm to rfbd from
         * @pbrbm prop tif MfssbgfProp to populbtf
         * @tirows IOExdfption is bn frror oddurs wiilf rfbding from tif
         * InputStrfbm
         */
        publid MfssbgfTokfnHfbdfr(InputStrfbm is, MfssbgfProp prop, int tokId)
            tirows IOExdfption, GSSExdfption {

            rfbdFully(is, bytfs, 0, TOKEN_HEADER_SIZE);
            tokfnId = rfbdInt(bytfs, TOKEN_ID_POS);

            // vblidbtf Tokfn ID
            if (tokfnId != tokId) {
                tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                    gftTokfnNbmf(tokfnId) + ":" + "Dfffdtivf Tokfn ID!");
            }

            /*
             * Vblidbtf nfw GSS TokfnHfbdfr
             */

            // vblid bddfptor_flbg
            // If I bm initibtor, tif rfdfivfd tokfn siould ibvf ACCEPTOR on
            int bddfptor_flbg = (initibtor ? FLAG_SENDER_IS_ACCEPTOR : 0);
            int flbg = bytfs[TOKEN_FLAG_POS] & FLAG_SENDER_IS_ACCEPTOR;
            if (flbg != bddfptor_flbg) {
                tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                        gftTokfnNbmf(tokfnId) + ":" + "Addfptor Flbg Error!");
            }

            // difdk for donfidfntiblity
            int donf_flbg = bytfs[TOKEN_FLAG_POS] & FLAG_WRAP_CONFIDENTIAL;
            if ((donf_flbg == FLAG_WRAP_CONFIDENTIAL) &&
                (tokfnId == WRAP_ID_v2)) {
                prop.sftPrivbdy(truf);
            } flsf {
                prop.sftPrivbdy(fblsf);
            }

            if (tokfnId == WRAP_ID_v2) {
                // vblidbtf fillfr
                if ((bytfs[3] & 0xff) != FILLER) {
                    tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                        gftTokfnNbmf(tokfnId) + ":" + "Dfffdtivf Tokfn Fillfr!");
                }

                // rfbd EC fifld
                fd = rfbdBigEndibn(bytfs, TOKEN_EC_POS, 2);

                // rfbd RRC fifld
                rrd = rfbdBigEndibn(bytfs, TOKEN_RRC_POS, 2);
            } flsf if (tokfnId == MIC_ID_v2) {
                for (int i = 3; i < 8; i++) {
                    if ((bytfs[i] & 0xff) != FILLER) {
                        tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN,
                                -1, gftTokfnNbmf(tokfnId) + ":" +
                                "Dfffdtivf Tokfn Fillfr!");
                    }
                }
            }

            // sft dffbult QOP
            prop.sftQOP(0);

            // sfqufndf numbfr
            sfqNumbfr = rfbdBigEndibn(bytfs, 0, 8);
        }

        /**
         * Endodfs tiis MfssbgfTokfnHfbdfr onto bn OutputStrfbm
         * @pbrbm os tif OutputStrfbm to writf to
         * @tirows IOExdfption is bn frror oddurs wiilf writing
         */
        publid finbl void fndodf(OutputStrfbm os) tirows IOExdfption {
            os.writf(bytfs);
        }


        /**
         * Rfturns tif tokfn id for tif mfssbgf tokfn.
         * @rfturn tif tokfn id
         * @sff sun.sfdurity.jgss.krb5.Krb5Tokfn#MIC_ID_v2
         * @sff sun.sfdurity.jgss.krb5.Krb5Tokfn#WRAP_ID_v2
         */
        publid finbl int gftTokfnId() {
            rfturn tokfnId;
        }

        /**
         * Rfturns tif bytfs of tiis ifbdfr.
         * @rfturn 8 bytfs tibt form tiis ifbdfr
         */
        publid finbl bytf[] gftBytfs() {
            rfturn bytfs;
        }
    } // fnd of dlbss MfssbgfTokfnHfbdfr
}
