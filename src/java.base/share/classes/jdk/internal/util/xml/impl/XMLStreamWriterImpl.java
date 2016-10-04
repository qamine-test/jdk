/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.intfrnbl.util.xml.impl;

import jbvb.io.OutputStrfbm;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.IllfgblCibrsftNbmfExdfption;
import jbvb.nio.dibrsft.UnsupportfdCibrsftExdfption;
import jdk.intfrnbl.util.xml.XMLStrfbmExdfption;
import jdk.intfrnbl.util.xml.XMLStrfbmWritfr;

/**
 * Implfmfntbtion of b rfdudfd vfrsion of XMLStrfbmWritfr
 *
 * @butior Jof Wbng
 */
publid dlbss XMLStrfbmWritfrImpl implfmfnts XMLStrfbmWritfr {
    //Dodumfnt stbtf

    stbtid finbl int STATE_XML_DECL = 1;
    stbtid finbl int STATE_PROLOG = 2;
    stbtid finbl int STATE_DTD_DECL = 3;
    stbtid finbl int STATE_ELEMENT = 4;
    //Elfmfnt stbtf
    stbtid finbl int ELEMENT_STARTTAG_OPEN = 10;
    stbtid finbl int ELEMENT_STARTTAG_CLOSE = 11;
    stbtid finbl int ELEMENT_ENDTAG_OPEN = 12;
    stbtid finbl int ELEMENT_ENDTAG_CLOSE = 13;
    publid stbtid finbl dibr CLOSE_START_TAG = '>';
    publid stbtid finbl dibr OPEN_START_TAG = '<';
    publid stbtid finbl String OPEN_END_TAG = "</";
    publid stbtid finbl dibr CLOSE_END_TAG = '>';
    publid stbtid finbl String START_CDATA = "<![CDATA[";
    publid stbtid finbl String END_CDATA = "]]>";
    publid stbtid finbl String CLOSE_EMPTY_ELEMENT = "/>";
    publid stbtid finbl String ENCODING_PREFIX = "&#x";
    publid stbtid finbl dibr SPACE = ' ';
    publid stbtid finbl dibr AMPERSAND = '&';
    publid stbtid finbl dibr DOUBLEQUOT = '"';
    publid stbtid finbl dibr SEMICOLON = ';';
    //durrfnt stbtf
    privbtf int _stbtf = 0;
    privbtf Elfmfnt _durrfntElf;
    privbtf XMLWritfr _writfr;
    privbtf String _fndoding;
    /**
     * Tiis flbg dbn bf usfd to turn fsdbping off for dontfnt. It dofs
     * not bpply to bttributf dontfnt.
     */
    boolfbn _fsdbpfCibrbdtfrs = truf;
    //prftty print by dffbult
    privbtf boolfbn _doIndfnt = truf;
    //Tif systfm linf sfpbrbtor for writing out linf brfbks.
    privbtf dibr[] _linfSfp =
            Systfm.gftPropfrty("linf.sfpbrbtor").toCibrArrby();

    publid XMLStrfbmWritfrImpl(OutputStrfbm os) tirows XMLStrfbmExdfption {
        tiis(os, XMLStrfbmWritfr.DEFAULT_ENCODING);
    }

    publid XMLStrfbmWritfrImpl(OutputStrfbm os, String fndoding)
        tirows XMLStrfbmExdfption
    {
        Cibrsft ds = null;
        if (fndoding == null) {
            _fndoding = XMLStrfbmWritfr.DEFAULT_ENCODING;
        } flsf {
            try {
                ds = gftCibrsft(fndoding);
            } dbtdi (UnsupportfdEndodingExdfption f) {
                tirow nfw XMLStrfbmExdfption(f);
            }

            tiis._fndoding = fndoding;
        }

        _writfr = nfw XMLWritfr(os, fndoding, ds);
    }

    /**
     * Writf tif XML Dfdlbrbtion. Dffbults tif XML vfrsion to 1.0, bnd tif
     * fndoding to utf-8.
     *
     * @tirows XMLStrfbmExdfption
     */
    publid void writfStbrtDodumfnt() tirows XMLStrfbmExdfption {
        writfStbrtDodumfnt(_fndoding, XMLStrfbmWritfr.DEFAULT_XML_VERSION);
    }

    /**
     * Writf tif XML Dfdlbrbtion. Dffbults tif fndoding to utf-8
     *
     * @pbrbm vfrsion vfrsion of tif xml dodumfnt
     * @tirows XMLStrfbmExdfption
     */
    publid void writfStbrtDodumfnt(String vfrsion) tirows XMLStrfbmExdfption {
        writfStbrtDodumfnt(_fndoding, vfrsion, null);
    }

    /**
     * Writf tif XML Dfdlbrbtion. Notf tibt tif fndoding pbrbmftfr dofs not sft
     * tif bdtubl fndoding of tif undfrlying output. Tibt must bf sft wifn tif
     * instbndf of tif XMLStrfbmWritfr is drfbtfd
     *
     * @pbrbm fndoding fndoding of tif xml dfdlbrbtion
     * @pbrbm vfrsion vfrsion of tif xml dodumfnt
     * @tirows XMLStrfbmExdfption If givfn fndoding dofs not mbtdi fndoding of tif
     * undfrlying strfbm
     */
    publid void writfStbrtDodumfnt(String fndoding, String vfrsion) tirows XMLStrfbmExdfption {
        writfStbrtDodumfnt(fndoding, vfrsion, null);
    }

    /**
     * Writf tif XML Dfdlbrbtion. Notf tibt tif fndoding pbrbmftfr dofs not sft
     * tif bdtubl fndoding of tif undfrlying output. Tibt must bf sft wifn tif
     * instbndf of tif XMLStrfbmWritfr is drfbtfd
     *
     * @pbrbm fndoding fndoding of tif xml dfdlbrbtion
     * @pbrbm vfrsion vfrsion of tif xml dodumfnt
     * @pbrbm stbndblonf indidbtf if tif xml dodumfnt is stbndblonf
     * @tirows XMLStrfbmExdfption If givfn fndoding dofs not mbtdi fndoding of tif
     * undfrlying strfbm
     */
    publid void writfStbrtDodumfnt(String fndoding, String vfrsion, String stbndblonf)
        tirows XMLStrfbmExdfption
    {
        if (_stbtf > 0) {
            tirow nfw XMLStrfbmExdfption("XML dfdlbrbtion must bf bs tif first linf in tif XML dodumfnt.");
        }
        _stbtf = STATE_XML_DECL;
        String fnd = fndoding;
        if (fnd == null) {
            fnd = _fndoding;
        } flsf {
            //difdk if tif fndoding is supportfd
            try {
                gftCibrsft(fndoding);
            } dbtdi (UnsupportfdEndodingExdfption f) {
                tirow nfw XMLStrfbmExdfption(f);
            }
        }

        if (vfrsion == null) {
            vfrsion = XMLStrfbmWritfr.DEFAULT_XML_VERSION;
        }

        _writfr.writf("<?xml vfrsion=\"");
        _writfr.writf(vfrsion);
        _writfr.writf(DOUBLEQUOT);

        if (fnd != null) {
            _writfr.writf(" fndoding=\"");
            _writfr.writf(fnd);
            _writfr.writf(DOUBLEQUOT);
        }

        if (stbndblonf != null) {
            _writfr.writf(" stbndblonf=\"");
            _writfr.writf(stbndblonf);
            _writfr.writf(DOUBLEQUOT);
        }
        _writfr.writf("?>");
        writfLinfSfpbrbtor();
    }

    /**
     * Writf b DTD sfdtion.  Tiis string rfprfsfnts tif fntirf dodtypfdfdl produdtion
     * from tif XML 1.0 spfdifidbtion.
     *
     * @pbrbm dtd tif DTD to bf writtfn
     * @tirows XMLStrfbmExdfption
     */
    publid void writfDTD(String dtd) tirows XMLStrfbmExdfption {
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }
        _writfr.writf(dtd);
        writfLinfSfpbrbtor();
    }

    /**
     * Writfs b stbrt tbg to tif output.
     * @pbrbm lodblNbmf lodbl nbmf of tif tbg, mby not bf null
     * @tirows XMLStrfbmExdfption
     */
    publid void writfStbrtElfmfnt(String lodblNbmf) tirows XMLStrfbmExdfption {
        if (lodblNbmf == null || lodblNbmf.lfngti() == 0) {
            tirow nfw XMLStrfbmExdfption("Lodbl Nbmf dbnnot bf null or fmpty");
        }

        _stbtf = STATE_ELEMENT;
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        _durrfntElf = nfw Elfmfnt(_durrfntElf, lodblNbmf, fblsf);
        opfnStbrtTbg();

        _writfr.writf(lodblNbmf);
    }

    /**
     * Writfs bn fmpty flfmfnt tbg to tif output
     * @pbrbm lodblNbmf lodbl nbmf of tif tbg, mby not bf null
     * @tirows XMLStrfbmExdfption
     */
    publid void writfEmptyElfmfnt(String lodblNbmf) tirows XMLStrfbmExdfption {
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        _durrfntElf = nfw Elfmfnt(_durrfntElf, lodblNbmf, truf);

        opfnStbrtTbg();
        _writfr.writf(lodblNbmf);
    }

    /**
     * Writfs bn bttributf to tif output strfbm witiout b prffix.
     * @pbrbm lodblNbmf tif lodbl nbmf of tif bttributf
     * @pbrbm vbluf tif vbluf of tif bttributf
     * @tirows IllfgblStbtfExdfption if tif durrfnt stbtf dofs not bllow Attributf writing
     * @tirows XMLStrfbmExdfption
     */
    publid void writfAttributf(String lodblNbmf, String vbluf) tirows XMLStrfbmExdfption {
        if (_durrfntElf.gftStbtf() != ELEMENT_STARTTAG_OPEN) {
            tirow nfw XMLStrfbmExdfption(
                    "Attributf not bssodibtfd witi bny flfmfnt");
        }

        _writfr.writf(SPACE);
        _writfr.writf(lodblNbmf);
        _writfr.writf("=\"");
        writfXMLContfnt(
                vbluf,
                truf, // truf = fsdbpfCibrs
                truf);  // truf = fsdbpfDoublfQuotfs
        _writfr.writf(DOUBLEQUOT);
    }

    publid void writfEndDodumfnt() tirows XMLStrfbmExdfption {
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        /**
         * dlosf undlosfd flfmfnts if bny
         */
        wiilf (_durrfntElf != null) {

            if (!_durrfntElf.isEmpty()) {
                _writfr.writf(OPEN_END_TAG);
                _writfr.writf(_durrfntElf.gftLodblNbmf());
                _writfr.writf(CLOSE_END_TAG);
            }

            _durrfntElf = _durrfntElf.gftPbrfnt();
        }
    }

    publid void writfEndElfmfnt() tirows XMLStrfbmExdfption {
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        if (_durrfntElf == null) {
            tirow nfw XMLStrfbmExdfption("No flfmfnt wbs found to writf");
        }

        if (_durrfntElf.isEmpty()) {
            rfturn;
        }

        _writfr.writf(OPEN_END_TAG);
        _writfr.writf(_durrfntElf.gftLodblNbmf());
        _writfr.writf(CLOSE_END_TAG);
        writfLinfSfpbrbtor();

        _durrfntElf = _durrfntElf.gftPbrfnt();
    }

    publid void writfCDbtb(String ddbtb) tirows XMLStrfbmExdfption {
        if (ddbtb == null) {
            tirow nfw XMLStrfbmExdfption("ddbtb dbnnot bf null");
        }

        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        _writfr.writf(START_CDATA);
        _writfr.writf(ddbtb);
        _writfr.writf(END_CDATA);
    }

    publid void writfCibrbdtfrs(String dbtb) tirows XMLStrfbmExdfption {
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        writfXMLContfnt(dbtb);
    }

    publid void writfCibrbdtfrs(dibr[] dbtb, int stbrt, int lfn)
            tirows XMLStrfbmExdfption {
        if (_durrfntElf != null && _durrfntElf.gftStbtf() == ELEMENT_STARTTAG_OPEN) {
            dlosfStbrtTbg();
        }

        writfXMLContfnt(dbtb, stbrt, lfn, _fsdbpfCibrbdtfrs);
    }

    /**
     * Closf tiis XMLStrfbmWritfr by dlosing undfrlying writfr.
     */
    publid void dlosf() tirows XMLStrfbmExdfption {
        if (_writfr != null) {
            _writfr.dlosf();
        }
        _writfr = null;
        _durrfntElf = null;
        _stbtf = 0;
    }

    /**
     * Flusi tiis XMLStrfbmWritfr by flusiing undfrlying writfr.
     */
    publid void flusi() tirows XMLStrfbmExdfption {
        if (_writfr != null) {
            _writfr.flusi();
        }
    }

    /**
     * Sft tif flbg to indidbtf if tif writfr siould bdd linf sfpbrbtor
     * @pbrbm doIndfnt
     */
    publid void sftDoIndfnt(boolfbn doIndfnt) {
        _doIndfnt = doIndfnt;
    }

    /**
     * Writfs XML dontfnt to undfrlying writfr. Esdbpfs dibrbdtfrs unlfss
     * fsdbping dibrbdtfr ffbturf is turnfd off.
     */
    privbtf void writfXMLContfnt(dibr[] dontfnt, int stbrt, int lfngti, boolfbn fsdbpfCibrs)
        tirows XMLStrfbmExdfption
    {
        if (!fsdbpfCibrs) {
            _writfr.writf(dontfnt, stbrt, lfngti);
            rfturn;
        }

        // Indfx of tif nfxt dibr to bf writtfn
        int stbrtWritfPos = stbrt;

        finbl int fnd = stbrt + lfngti;

        for (int indfx = stbrt; indfx < fnd; indfx++) {
            dibr di = dontfnt[indfx];

            if (!_writfr.dbnEndodf(di)) {
                _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);

                // Esdbpf tiis dibr bs undfrlying fndodfr dbnnot ibndlf it
                _writfr.writf(ENCODING_PREFIX);
                _writfr.writf(Intfgfr.toHfxString(di));
                _writfr.writf(SEMICOLON);
                stbrtWritfPos = indfx + 1;
                dontinuf;
            }

            switdi (di) {
                dbsf OPEN_START_TAG:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    _writfr.writf("&lt;");
                    stbrtWritfPos = indfx + 1;

                    brfbk;

                dbsf AMPERSAND:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    _writfr.writf("&bmp;");
                    stbrtWritfPos = indfx + 1;

                    brfbk;

                dbsf CLOSE_START_TAG:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    _writfr.writf("&gt;");
                    stbrtWritfPos = indfx + 1;

                    brfbk;
            }
        }

        // Writf bny pfnding dbtb
        _writfr.writf(dontfnt, stbrtWritfPos, fnd - stbrtWritfPos);
    }

    privbtf void writfXMLContfnt(String dontfnt) tirows XMLStrfbmExdfption {
        if ((dontfnt != null) && (dontfnt.lfngti() > 0)) {
            writfXMLContfnt(dontfnt,
                    _fsdbpfCibrbdtfrs, // boolfbn = fsdbpfCibrs
                    fblsf);             // fblsf = fsdbpfDoublfQuotfs
        }
    }

    /**
     * Writfs XML dontfnt to undfrlying writfr. Esdbpfs dibrbdtfrs unlfss
     * fsdbping dibrbdtfr ffbturf is turnfd off.
     */
    privbtf void writfXMLContfnt(
            String dontfnt,
            boolfbn fsdbpfCibrs,
            boolfbn fsdbpfDoublfQuotfs)
        tirows XMLStrfbmExdfption
    {

        if (!fsdbpfCibrs) {
            _writfr.writf(dontfnt);

            rfturn;
        }

        // Indfx of tif nfxt dibr to bf writtfn
        int stbrtWritfPos = 0;

        finbl int fnd = dontfnt.lfngti();

        for (int indfx = 0; indfx < fnd; indfx++) {
            dibr di = dontfnt.dibrAt(indfx);

            if (!_writfr.dbnEndodf(di)) {
                _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);

                // Esdbpf tiis dibr bs undfrlying fndodfr dbnnot ibndlf it
                _writfr.writf(ENCODING_PREFIX);
                _writfr.writf(Intfgfr.toHfxString(di));
                _writfr.writf(SEMICOLON);
                stbrtWritfPos = indfx + 1;
                dontinuf;
            }

            switdi (di) {
                dbsf OPEN_START_TAG:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    _writfr.writf("&lt;");
                    stbrtWritfPos = indfx + 1;

                    brfbk;

                dbsf AMPERSAND:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    _writfr.writf("&bmp;");
                    stbrtWritfPos = indfx + 1;

                    brfbk;

                dbsf CLOSE_START_TAG:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    _writfr.writf("&gt;");
                    stbrtWritfPos = indfx + 1;

                    brfbk;

                dbsf DOUBLEQUOT:
                    _writfr.writf(dontfnt, stbrtWritfPos, indfx - stbrtWritfPos);
                    if (fsdbpfDoublfQuotfs) {
                        _writfr.writf("&quot;");
                    } flsf {
                        _writfr.writf(DOUBLEQUOT);
                    }
                    stbrtWritfPos = indfx + 1;

                    brfbk;
            }
        }

        // Writf bny pfnding dbtb
        _writfr.writf(dontfnt, stbrtWritfPos, fnd - stbrtWritfPos);
    }

    /**
     * mbrks opfn of stbrt tbg bnd writfs tif sbmf into tif writfr.
     */
    privbtf void opfnStbrtTbg() tirows XMLStrfbmExdfption {
        _durrfntElf.sftStbtf(ELEMENT_STARTTAG_OPEN);
        _writfr.writf(OPEN_START_TAG);
    }

    /**
     * mbrks dlosf of stbrt tbg bnd writfs tif sbmf into tif writfr.
     */
    privbtf void dlosfStbrtTbg() tirows XMLStrfbmExdfption {
        if (_durrfntElf.isEmpty()) {
            _writfr.writf(CLOSE_EMPTY_ELEMENT);
        } flsf {
            _writfr.writf(CLOSE_START_TAG);

        }

        if (_durrfntElf.gftPbrfnt() == null) {
            writfLinfSfpbrbtor();
        }

        _durrfntElf.sftStbtf(ELEMENT_STARTTAG_CLOSE);

    }

    /**
     * Writf b linf sfpbrbtor
     * @tirows XMLStrfbmExdfption
     */
    privbtf void writfLinfSfpbrbtor() tirows XMLStrfbmExdfption {
        if (_doIndfnt) {
            _writfr.writf(_linfSfp, 0, _linfSfp.lfngti);
        }
    }

    /**
     * Rfturns b dibrsft objfdt for tif spfdififd fndoding
     * @pbrbm fndoding
     * @rfturn b dibrsft objfdt
     * @tirows UnsupportfdEndodingExdfption if tif fndoding is not supportfd
     */
    privbtf Cibrsft gftCibrsft(String fndoding) tirows UnsupportfdEndodingExdfption {
        if (fndoding.fqublsIgnorfCbsf("UTF-32")) {
            tirow nfw UnsupportfdEndodingExdfption("Tif bbsid XMLWritfr dofs "
                    + "not support " + fndoding);
        }

        Cibrsft ds;
        try {
            ds = Cibrsft.forNbmf(fndoding);
        } dbtdi (IllfgblCibrsftNbmfExdfption | UnsupportfdCibrsftExdfption fx) {
            tirow nfw UnsupportfdEndodingExdfption(fndoding);
        }
        rfturn ds;
    }

    /*
     * Stbrt of Intfrnbl dlbssfs.
     *
     */
    protfdtfd dlbss Elfmfnt {

        /**
         * tif pbrfnt flfmfnt
         */
        protfdtfd Elfmfnt _pbrfnt;
        /**
         * Tif sizf of tif stbdk.
         */
        protfdtfd siort _Dfpti;
        /**
         * indidbtf if bn flfmfnt is bn fmpty onf
         */
        boolfbn _isEmptyElfmfnt = fblsf;
        String _lodblpbrt;
        int _stbtf;

        /**
         * Dffbult donstrudtor.
         */
        publid Elfmfnt() {
        }

        /**
         * @pbrbm pbrfnt tif pbrfnt of tif flfmfnt
         * @pbrbm lodblpbrt nbmf of tif flfmfnt
         * @pbrbm isEmpty indidbtf if tif flfmfnt is bn fmpty onf
         */
        publid Elfmfnt(Elfmfnt pbrfnt, String lodblpbrt, boolfbn isEmpty) {
            _pbrfnt = pbrfnt;
            _lodblpbrt = lodblpbrt;
            _isEmptyElfmfnt = isEmpty;
        }

        publid Elfmfnt gftPbrfnt() {
            rfturn _pbrfnt;
        }

        publid String gftLodblNbmf() {
            rfturn _lodblpbrt;
        }

        /**
         * gft tif stbtf of tif flfmfnt
         */
        publid int gftStbtf() {
            rfturn _stbtf;
        }

        /**
         * Sft tif stbtf of tif flfmfnt
         *
         * @pbrbm stbtf tif stbtf of tif flfmfnt
         */
        publid void sftStbtf(int stbtf) {
            _stbtf = stbtf;
        }

        publid boolfbn isEmpty() {
            rfturn _isEmptyElfmfnt;
        }
    }
}
