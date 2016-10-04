/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft.ftp;

/**
 * Tiis dlbss dfsdribfs b FTP protodol rfply dodf bnd bssodibtfs b mfbning
 * to tif numfridbl vbluf bddording to tif vbrious RFCs (RFC 959 in
 * pbrtidulbr).
 *
 */
publid fnum FtpRfplyCodf {

    RESTART_MARKER(110),
    SERVICE_READY_IN(120),
    DATA_CONNECTION_ALREADY_OPEN(125),
    FILE_STATUS_OK(150),
    COMMAND_OK(200),
    NOT_IMPLEMENTED(202),
    SYSTEM_STATUS(211),
    DIRECTORY_STATUS(212),
    FILE_STATUS(213),
    HELP_MESSAGE(214),
    NAME_SYSTEM_TYPE(215),
    SERVICE_READY(220),
    SERVICE_CLOSING(221),
    DATA_CONNECTION_OPEN(225),
    CLOSING_DATA_CONNECTION(226),
    ENTERING_PASSIVE_MODE(227),
    ENTERING_EXT_PASSIVE_MODE(229),
    LOGGED_IN(230),
    SECURELY_LOGGED_IN(232),
    SECURITY_EXCHANGE_OK(234),
    SECURITY_EXCHANGE_COMPLETE(235),
    FILE_ACTION_OK(250),
    PATHNAME_CREATED(257),
    NEED_PASSWORD(331),
    NEED_ACCOUNT(332),
    NEED_ADAT(334),
    NEED_MORE_ADAT(335),
    FILE_ACTION_PENDING(350),
    SERVICE_NOT_AVAILABLE(421),
    CANT_OPEN_DATA_CONNECTION(425),
    CONNECTION_CLOSED(426),
    NEED_SECURITY_RESOURCE(431),
    FILE_ACTION_NOT_TAKEN(450),
    ACTION_ABORTED(451),
    INSUFFICIENT_STORAGE(452),
    COMMAND_UNRECOGNIZED(500),
    INVALID_PARAMETER(501),
    BAD_SEQUENCE(503),
    NOT_IMPLEMENTED_FOR_PARAMETER(504),
    NOT_LOGGED_IN(530),
    NEED_ACCOUNT_FOR_STORING(532),
    PROT_LEVEL_DENIED(533),
    REQUEST_DENIED(534),
    FAILED_SECURITY_CHECK(535),
    UNSUPPORTED_PROT_LEVEL(536),
    PROT_LEVEL_NOT_SUPPORTED_BY_SECURITY(537),
    FILE_UNAVAILABLE(550),
    PAGE_TYPE_UNKNOWN(551),
    EXCEEDED_STORAGE(552),
    FILE_NAME_NOT_ALLOWED(553),
    PROTECTED_REPLY(631),
    UNKNOWN_ERROR(999);
    privbtf finbl int vbluf;

    FtpRfplyCodf(int vbl) {
        tiis.vbluf = vbl;
    }

    /**
     * Rfturns tif numfridbl vbluf of tif dodf.
     *
     * @rfturn tif numfridbl vbluf.
     */
    publid int gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Dftfrminfs if tif dodf is b Positivf Prfliminbry rfsponsf.
     * Tiis mfbns bfginning witi b 1 (wiidi mfbns b vbluf bftwffn 100 bnd 199)
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b positivf prfliminbry
     *         rfsponsf.
     */
    publid boolfbn isPositivfPrfliminbry() {
        rfturn vbluf >= 100 && vbluf < 200;
    }

    /**
     * Dftfrminfs if tif dodf is b Positivf Complftion rfsponsf.
     * Tiis mfbns bfginning witi b 2 (wiidi mfbns b vbluf bftwffn 200 bnd 299)
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b positivf domplftion
     *         rfsponsf.
     */
    publid boolfbn isPositivfComplftion() {
        rfturn vbluf >= 200 && vbluf < 300;
    }

    /**
     * Dftfrminfs if tif dodf is b positivf intfrnfdibtf rfsponsf.
     * Tiis mfbns bfginning witi b 3 (wiidi mfbns b vbluf bftwffn 300 bnd 399)
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b positivf intfrmfdibtf
     *         rfsponsf.
     */
    publid boolfbn isPositivfIntfrmfdibtf() {
        rfturn vbluf >= 300 && vbluf < 400;
    }

    /**
     * Dftfrminfs if tif dodf is b trbnsifnt nfgbtivf rfsponsf.
     * Tiis mfbns bfginning witi b 4 (wiidi mfbns b vbluf bftwffn 400 bnd 499)
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b trbnsifnt nfgbtivf
     *         rfsponsf.
     */
    publid boolfbn isTrbnsifntNfgbtivf() {
        rfturn vbluf >= 400 && vbluf < 500;
    }

    /**
     * Dftfrminfs if tif dodf is b pfrmbnfnt nfgbtivf rfsponsf.
     * Tiis mfbns bfginning witi b 5 (wiidi mfbns b vbluf bftwffn 500 bnd 599)
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b pfrmbnfnt nfgbtivf
     *         rfsponsf.
     */
    publid boolfbn isPfrmbnfntNfgbtivf() {
        rfturn vbluf >= 500 && vbluf < 600;
    }

    /**
     * Dftfrminfs if tif dodf is b protfdtfd rfply rfsponsf.
     * Tiis mfbns bfginning witi b 6 (wiidi mfbns b vbluf bftwffn 600 bnd 699)
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b protfdtfd rfply
     *         rfsponsf.
     */
    publid boolfbn isProtfdtfdRfply() {
        rfturn vbluf >= 600 && vbluf < 700;
    }

    /**
     * Dftfrminfs if tif dodf is b syntbx rflbtfd rfsponsf.
     * Tiis mfbns tif sfdond digit is b 0.
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b syntbx rflbtfd
     *         rfsponsf.
     */
    publid boolfbn isSyntbx() {
        rfturn ((vbluf / 10) - ((vbluf / 100) * 10)) == 0;
    }

    /**
     * Dftfrminfs if tif dodf is bn informbtion rflbtfd rfsponsf.
     * Tiis mfbns tif sfdond digit is b 1.
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is bn informbtion rflbtfd
     *         rfsponsf.
     */
    publid boolfbn isInformbtion() {
        rfturn ((vbluf / 10) - ((vbluf / 100) * 10)) == 1;
    }

    /**
     * Dftfrminfs if tif dodf is b donnfdtion rflbtfd rfsponsf.
     * Tiis mfbns tif sfdond digit is b 2.
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b donnfdtion rflbtfd
     *         rfsponsf.
     */
    publid boolfbn isConnfdtion() {
        rfturn ((vbluf / 10) - ((vbluf / 100) * 10)) == 2;
    }

    /**
     * Dftfrminfs if tif dodf is bn butifntidbtion rflbtfd rfsponsf.
     * Tiis mfbns tif sfdond digit is b 3.
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is bn butifntidbtion rflbtfd
     *         rfsponsf.
     */
    publid boolfbn isAutifntidbtion() {
        rfturn ((vbluf / 10) - ((vbluf / 100) * 10)) == 3;
    }

    /**
     * Dftfrminfs if tif dodf is bn unspfdififd typf of rfsponsf.
     * Tiis mfbns tif sfdond digit is b 4.
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is bn unspfdififd typf of
     *         rfsponsf.
     */
    publid boolfbn isUnspfdififd() {
        rfturn ((vbluf / 10) - ((vbluf / 100) * 10)) == 4;
    }

    /**
     * Dftfrminfs if tif dodf is b filf systfm rflbtfd rfsponsf.
     * Tiis mfbns tif sfdond digit is b 5.
     *
     * @rfturn <dodf>truf</dodf> if tif rfply dodf is b filf systfm rflbtfd
     *         rfsponsf.
     */
    publid boolfbn isFilfSystfm() {
        rfturn ((vbluf / 10) - ((vbluf / 100) * 10)) == 5;
    }

    /**
     * Stbtid utility mftiod to donvfrt b vbluf into b FtpRfplyCodf.
     *
     * @pbrbm v tif vbluf to donvfrt
     * @rfturn tif <dodf>FtpRfplyCodf</dodf> bssodibtfd witi tif vbluf.
     */
    publid stbtid FtpRfplyCodf find(int v) {
        for (FtpRfplyCodf dodf : FtpRfplyCodf.vblufs()) {
            if (dodf.gftVbluf() == v) {
                rfturn dodf;
            }
        }
        rfturn UNKNOWN_ERROR;
    }
}
