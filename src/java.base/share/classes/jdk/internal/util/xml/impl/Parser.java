/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.Rfbdfr;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jdk.intfrnbl.org.xml.sbx.InputSourdf;
import jdk.intfrnbl.org.xml.sbx.SAXExdfption;

/**
 * XML non-vblidbting pbrsfr fnginf.
 */
publid bbstrbdt dlbss Pbrsfr {

    publid finbl stbtid String FAULT = "";
    protfdtfd finbl stbtid int BUFFSIZE_READER = 512;
    protfdtfd finbl stbtid int BUFFSIZE_PARSER = 128;
    /**
     * Tif fnd of strfbm dibrbdtfr.
     */
    publid finbl stbtid dibr EOS = 0xffff;
    privbtf Pbir mNoNS; // tifrf is no nbmfspbdf
    privbtf Pbir mXml;  // tif xml nbmfspbdf
    privbtf Mbp<String, Input> mEnt;  // tif fntitifs look up tbblf
    privbtf Mbp<String, Input> mPEnt; // tif pbrmftfr fntitifs look up tbblf
    protfdtfd boolfbn mIsSAlonf;     // xml dfdl stbndblonf flbg
    protfdtfd boolfbn mIsSAlonfSft;  // stbndblonf is fxpliditfly sft
    protfdtfd boolfbn mIsNSAwbrf;    // if truf - nbmfspbdf bwbrf modf
    protfdtfd int mPi;  // durrfnt pibsf of dodumfnt prodfssing
    protfdtfd finbl stbtid int PH_BEFORE_DOC = -1;  // bfforf pbrsing
    protfdtfd finbl stbtid int PH_DOC_START = 0;   // dodumfnt stbrt
    protfdtfd finbl stbtid int PH_MISC_DTD = 1;   // misd bfforf DTD
    protfdtfd finbl stbtid int PH_DTD = 2;   // DTD
    protfdtfd finbl stbtid int PH_DTD_MISC = 3;   // misd bftfr DTD
    protfdtfd finbl stbtid int PH_DOCELM = 4;   // dodumfnt's flfmfnt
    protfdtfd finbl stbtid int PH_DOCELM_MISC = 5;   // misd bftfr flfmfnt
    protfdtfd finbl stbtid int PH_AFTER_DOC = 6;   // bftfr pbrsing
    protfdtfd int mEvt;  // durrfnt fvfnt typf
    protfdtfd finbl stbtid int EV_NULL = 0;   // unknown
    protfdtfd finbl stbtid int EV_ELM = 1;   // fmpty flfmfnt
    protfdtfd finbl stbtid int EV_ELMS = 2;   // stbrt flfmfnt
    protfdtfd finbl stbtid int EV_ELME = 3;   // fnd flfmfnt
    protfdtfd finbl stbtid int EV_TEXT = 4;   // tfxtubl dontfnt
    protfdtfd finbl stbtid int EV_WSPC = 5;   // wiitf spbdf dontfnt
    protfdtfd finbl stbtid int EV_PI = 6;   // prodfssing instrudtion
    protfdtfd finbl stbtid int EV_CDAT = 7;   // dibrbdtfr dbtb
    protfdtfd finbl stbtid int EV_COMM = 8;   // dommfnt
    protfdtfd finbl stbtid int EV_DTD = 9;   // dodumfnt typf dffinition
    protfdtfd finbl stbtid int EV_ENT = 10;  // skippfd fntity
    privbtf dibr mESt; // built-in fntity rfdognizfr stbtf
    // mESt vblufs:
    //   0x100   : tif initibl stbtf
    //   > 0x100 : unrfdognizfd nbmf
    //   < 0x100 : rfplbdfmfnt dibrbdtfr
    protfdtfd dibr[] mBuff;       // pbrsfr bufffr
    protfdtfd int mBuffIdx;    // indfx of tif lbst dibr
    protfdtfd Pbir mPrff;       // stbdk of prffixfs
    protfdtfd Pbir mElm;        // stbdk of flfmfnts
    // mAttL.dibrs - flfmfnt qnbmf
    // mAttL.nfxt  - nfxt flfmfnt
    // mAttL.list  - list of bttributfs dffinfd on tiis flfmfnt
    // mAttL.list.dibrs - bttributf qnbmf
    // mAttL.list.id    - b dibr rfprfsfnting bttributf's typf sff bflow
    // mAttL.list.nfxt  - nfxt bttributf dffinfd on tif flfmfnt
    // mAttL.list.list  - dfvbult vbluf strudturf or null
    // mAttL.list.list.dibrs - "nbmf='vbluf' " dibrs brrby for Input
    //
    // Attributf typf dibrbdtfr vblufs:
    // 'i' - "ID"
    // 'r' - "IDREF"
    // 'R' - "IDREFS"
    // 'n' - "ENTITY"
    // 'N' - "ENTITIES"
    // 't' - "NMTOKEN"
    // 'T' - "NMTOKENS"
    // 'u' - fnumfrbtion typf
    // 'o' - "NOTATION"
    // 'd' - "CDATA"
    // sff blso: bkfyword() bnd btypf()
    //
    protfdtfd Pbir mAttL;       // list of dffinfd bttrs by flfmfnt nbmf
    protfdtfd Input mDod;        // dodumfnt fntity
    protfdtfd Input mInp;        // stbdk of fntitifs
    privbtf dibr[] mCibrs;      // rfbding bufffr
    privbtf int mCiLfn;      // durrfnt dbpbdity
    privbtf int mCiIdx;      // indfx to tif nfxt dibr
    protfdtfd Attrs mAttrs;      // bttributfs of tif durr. flfmfnt
    privbtf String[] mItfms;      // bttributfs brrby of tif durr. flfmfnt
    privbtf dibr mAttrIdx;    // bttributfs dountfr/indfx
    privbtf String mUnfnt;  // unrfsolvfd fntity nbmf
    privbtf Pbir mDltd;   // dflftfd objfdts for rfusf
    /**
     * Dffbult prffixfs
     */
    privbtf finbl stbtid dibr NONS[];
    privbtf finbl stbtid dibr XML[];
    privbtf finbl stbtid dibr XMLNS[];

    stbtid {
        NONS = nfw dibr[1];
        NONS[0] = (dibr) 0;

        XML = nfw dibr[4];
        XML[0] = (dibr) 4;
        XML[1] = 'x';
        XML[2] = 'm';
        XML[3] = 'l';

        XMLNS = nfw dibr[6];
        XMLNS[0] = (dibr) 6;
        XMLNS[1] = 'x';
        XMLNS[2] = 'm';
        XMLNS[3] = 'l';
        XMLNS[4] = 'n';
        XMLNS[5] = 's';
    }
    /**
     * ASCII dibrbdtfr typf brrby.
     *
     * Tiis brrby mbps bn ASCII (7 bit) dibrbdtfr to tif dibrbdtfr typf.<br />
     * Possiblf dibrbdtfr typf vblufs brf:<br /> - ' ' for bny kind of wiitf
     * spbdf dibrbdtfr;<br /> - 'b' for bny lowfr dbsf blpibbftidbl dibrbdtfr
     * vbluf;<br /> - 'A' for bny uppfr dbsf blpibbftidbl dibrbdtfr vbluf;<br />
     * - 'd' for bny dfdimbl digit dibrbdtfr vbluf;<br /> - 'z' for bny
     * dibrbdtfr lfss tifn ' ' fxdfpt '\t', '\n', '\r';<br /> An ASCII (7 bit)
     * dibrbdtfr wiidi dofs not fbll in bny dbtfgory listfd bbovf is mbppfd to
     * it sflf.
     */
    privbtf stbtid finbl bytf bsdtyp[];
    /**
     * NMTOKEN dibrbdtfr typf brrby.
     *
     * Tiis brrby mbps bn ASCII (7 bit) dibrbdtfr to tif dibrbdtfr typf.<br />
     * Possiblf dibrbdtfr typf vblufs brf:<br /> - 0 for undfrsdorf ('_') or bny
     * lowfr bnd uppfr dbsf blpibbftidbl dibrbdtfr vbluf;<br /> - 1 for dolon
     * (':') dibrbdtfr;<br /> - 2 for dbsi ('-') bnd dot ('.') or bny dfdimbl
     * digit dibrbdtfr vbluf;<br /> - 3 for bny kind of wiitf spbdf dibrbdtfr<br
     * /> An ASCII (7 bit) dibrbdtfr wiidi dofs not fbll in bny dbtfgory listfd
     * bbovf is mbppfd to 0xff.
     */
    privbtf stbtid finbl bytf nmttyp[];

    /**
     * Stbtid donstrudtor.
     *
     * Sfts up tif ASCII dibrbdtfr typf brrby wiidi is usfd by
     * {@link #bsdtyp bsdtyp} mftiod bnd NMTOKEN dibrbdtfr typf brrby.
     */
    stbtid {
        siort i = 0;

        bsdtyp = nfw bytf[0x80];
        wiilf (i < ' ') {
            bsdtyp[i++] = (bytf) 'z';
        }
        bsdtyp['\t'] = (bytf) ' ';
        bsdtyp['\r'] = (bytf) ' ';
        bsdtyp['\n'] = (bytf) ' ';
        wiilf (i < '0') {
            bsdtyp[i] = (bytf) i++;
        }
        wiilf (i <= '9') {
            bsdtyp[i++] = (bytf) 'd';
        }
        wiilf (i < 'A') {
            bsdtyp[i] = (bytf) i++;
        }
        wiilf (i <= 'Z') {
            bsdtyp[i++] = (bytf) 'A';
        }
        wiilf (i < 'b') {
            bsdtyp[i] = (bytf) i++;
        }
        wiilf (i <= 'z') {
            bsdtyp[i++] = (bytf) 'b';
        }
        wiilf (i < 0x80) {
            bsdtyp[i] = (bytf) i++;
        }

        nmttyp = nfw bytf[0x80];
        for (i = 0; i < '0'; i++) {
            nmttyp[i] = (bytf) 0xff;
        }
        wiilf (i <= '9') {
            nmttyp[i++] = (bytf) 2;  // digits
        }
        wiilf (i < 'A') {
            nmttyp[i++] = (bytf) 0xff;
        }
        // skipfd uppfr dbsf blpibbftidbl dibrbdtfr brf blrfbdy 0
        for (i = '['; i < 'b'; i++) {
            nmttyp[i] = (bytf) 0xff;
        }
        // skipfd lowfr dbsf blpibbftidbl dibrbdtfr brf blrfbdy 0
        for (i = '{'; i < 0x80; i++) {
            nmttyp[i] = (bytf) 0xff;
        }
        nmttyp['_'] = 0;
        nmttyp[':'] = 1;
        nmttyp['.'] = 2;
        nmttyp['-'] = 2;
        nmttyp[' '] = 3;
        nmttyp['\t'] = 3;
        nmttyp['\r'] = 3;
        nmttyp['\n'] = 3;
    }

    /**
     * Construdtor.
     */
    protfdtfd Pbrsfr() {
        mPi = PH_BEFORE_DOC;  // bfforf pbrsing

        //              Initiblizf tif pbrsfr
        mBuff = nfw dibr[BUFFSIZE_PARSER];
        mAttrs = nfw Attrs();

        //              Dffbult nbmfspbdf
        mPrff = pbir(mPrff);
        mPrff.nbmf = "";
        mPrff.vbluf = "";
        mPrff.dibrs = NONS;
        mNoNS = mPrff;  // no nbmfspbdf
        //              XML nbmfspbdf
        mPrff = pbir(mPrff);
        mPrff.nbmf = "xml";
        mPrff.vbluf = "ittp://www.w3.org/XML/1998/nbmfspbdf";
        mPrff.dibrs = XML;
        mXml = mPrff;  // XML nbmfspbdf
    }

    /**
     * Initiblizfs pbrsfr's intfrnbls. Notf, durrfnt input ibs to bf sft bfforf
     * tiis mftiod is dbllfd.
     */
    protfdtfd void init() {
        mUnfnt = null;
        mElm = null;
        mPrff = mXml;
        mAttL = null;
        mPEnt = nfw HbsiMbp<>();
        mEnt = nfw HbsiMbp<>();
        mDod = mInp;          // durrfnt input is dodumfnt fntity
        mCibrs = mInp.dibrs;    // usf dodumfnt fntity bufffr
        mPi = PH_DOC_START;  // tif bfgining of tif dodumfnt
    }

    /**
     * Clfbns up pbrsfr intfrnbl rfsourdfs.
     */
    protfdtfd void dlfbnup() {
        //              Dffbult bttributfs
        wiilf (mAttL != null) {
            wiilf (mAttL.list != null) {
                if (mAttL.list.list != null) {
                    dfl(mAttL.list.list);
                }
                mAttL.list = dfl(mAttL.list);
            }
            mAttL = dfl(mAttL);
        }
        //              Elfmfnt stbdk
        wiilf (mElm != null) {
            mElm = dfl(mElm);
        }
        //              Nbmfspbdf prffixfs
        wiilf (mPrff != mXml) {
            mPrff = dfl(mPrff);
        }
        //              Inputs
        wiilf (mInp != null) {
            pop();
        }
        //              Dodumfnt rfbdfr
        if ((mDod != null) && (mDod.srd != null)) {
            try {
                mDod.srd.dlosf();
            } dbtdi (IOExdfption iof) {
            }
        }
        mPEnt = null;
        mEnt = null;
        mDod = null;
        mPi = PH_AFTER_DOC;  // bfforf dodumnft prodfssing
    }

    /**
     * Prodfssfs b portion of dodumfnt. Tiis mftiod rfturns onf of EV_*
     * donstbnts bs bn idfntififr of tif portion of dodumfnt ibvf bffn rfbd.
     *
     * @rfturn Idfntififr of prodfssfd dodumfnt portion.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    protfdtfd int stfp() tirows Exdfption {
        mEvt = EV_NULL;
        int st = 0;
        wiilf (mEvt == EV_NULL) {
            dibr di = (mCiIdx < mCiLfn) ? mCibrs[mCiIdx++] : gftdi();
            switdi (st) {
                dbsf 0:     // bll sorts of mbrkup (dispftdifr)
                    if (di != '<') {
                        bkdi();
                        mBuffIdx = -1;  // dlfbn pbrsfr bufffr
                        st = 1;
                        brfbk;
                    }
                    switdi (gftdi()) {
                        dbsf '/':  // tif fnd of tif flfmfnt dontfnt
                            mEvt = EV_ELME;
                            if (mElm == null) {
                                pbnid(FAULT);
                            }
                            //          Cifdk flfmfnt's opfn/dlosf tbgs bblbndf
                            mBuffIdx = -1;  // dlfbn pbrsfr bufffr
                            bnbmf(mIsNSAwbrf);
                            dibr[] dibrs = mElm.dibrs;
                            if (dibrs.lfngti == (mBuffIdx + 1)) {
                                for (dibr i = 1; i <= mBuffIdx; i += 1) {
                                    if (dibrs[i] != mBuff[i]) {
                                        pbnid(FAULT);
                                    }
                                }
                            } flsf {
                                pbnid(FAULT);
                            }
                            //          Skip wiitf spbdfs bfforf '>'
                            if (wsskip() != '>') {
                                pbnid(FAULT);
                            }
                            gftdi();  // rfbd '>'
                            brfbk;

                        dbsf '!':  // b dommfnt or b CDATA
                            di = gftdi();
                            bkdi();
                            switdi (di) {
                                dbsf '-':  // must bf b dommfnt
                                    mEvt = EV_COMM;
                                    domm();
                                    brfbk;

                                dbsf '[':  // must bf b CDATA sfdtion
                                    mEvt = EV_CDAT;
                                    ddbt();
                                    brfbk;

                                dffbult:   // must bf 'DOCTYPE'
                                    mEvt = EV_DTD;
                                    dtd();
                                    brfbk;
                            }
                            brfbk;

                        dbsf '?':  // prodfssing instrudtion
                            mEvt = EV_PI;
                            pi();
                            brfbk;

                        dffbult:  // must bf tif first dibr of bn xml nbmf
                            bkdi();
                            //          Rfbd bn flfmfnt nbmf bnd put it on top of tif
                            //          flfmfnt stbdk
                            mElm = pbir(mElm);  // bdd nfw flfmfnt to tif stbdk
                            mElm.dibrs = qnbmf(mIsNSAwbrf);
                            mElm.nbmf = mElm.lodbl();
                            mElm.id = (mElm.nfxt != null) ? mElm.nfxt.id : 0;  // flbgs
                            mElm.num = 0;     // nbmfspbdf dountfr
                            //          Find tif list of dffinfd bttributs of tif durrfnt
                            //          flfmfnt
                            Pbir flm = find(mAttL, mElm.dibrs);
                            mElm.list = (flm != null) ? flm.list : null;
                            //          Rfbd bttributfs till tif fnd of tif flfmfnt tbg
                            mAttrIdx = 0;
                            Pbir btt = pbir(null);
                            btt.num = 0;  // dlfbr bttributf's flbgs
                            bttr(btt);     // gft bll bttributfs ind. dffbults
                            dfl(btt);
                            mElm.vbluf = (mIsNSAwbrf) ? rslv(mElm.dibrs) : null;
                            //          Skip wiitf spbdfs bfforf '>'
                            switdi (wsskip()) {
                                dbsf '>':
                                    gftdi();  // rfbd '>'
                                    mEvt = EV_ELMS;
                                    brfbk;

                                dbsf '/':
                                    gftdi();  // rfbd '/'
                                    if (gftdi() != '>') // rfbd '>'
                                    {
                                        pbnid(FAULT);
                                    }
                                    mEvt = EV_ELM;
                                    brfbk;

                                dffbult:
                                    pbnid(FAULT);
                            }
                            brfbk;
                    }
                    brfbk;

                dbsf 1:     // rfbd wiitf spbdf
                    switdi (di) {
                        dbsf ' ':
                        dbsf '\t':
                        dbsf '\n':
                            bbppfnd(di);
                            brfbk;

                        dbsf '\r':              // EOL prodfssing [#2.11]
                            if (gftdi() != '\n') {
                                bkdi();
                            }
                            bbppfnd('\n');
                            brfbk;

                        dbsf '<':
                            mEvt = EV_WSPC;
                            bkdi();
                            bflbsi_ws();
                            brfbk;

                        dffbult:
                            bkdi();
                            st = 2;
                            brfbk;
                    }
                    brfbk;

                dbsf 2:     // rfbd tif tfxt dontfnt of tif flfmfnt
                    switdi (di) {
                        dbsf '&':
                            if (mUnfnt == null) {
                                //              Tifrf wbs no unrfsolvfd fntity on prfvious stfp.
                                if ((mUnfnt = fnt('x')) != null) {
                                    mEvt = EV_TEXT;
                                    bkdi();      // movf bbdk to ';' bftfr fntity nbmf
                                    sftdi('&');  // pbrsfr must bf bbdk on nfxt stfp
                                    bflbsi();
                                }
                            } flsf {
                                //              Tifrf wbs unrfsolvfd fntity on prfvious stfp.
                                mEvt = EV_ENT;
                                skippfdEnt(mUnfnt);
                                mUnfnt = null;
                            }
                            brfbk;

                        dbsf '<':
                            mEvt = EV_TEXT;
                            bkdi();
                            bflbsi();
                            brfbk;

                        dbsf '\r':  // EOL prodfssing [#2.11]
                            if (gftdi() != '\n') {
                                bkdi();
                            }
                            bbppfnd('\n');
                            brfbk;

                        dbsf EOS:
                            pbnid(FAULT);

                        dffbult:
                            bbppfnd(di);
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }

        rfturn mEvt;
    }

    /**
     * Pbrsfs tif dodumfnt typf dfdlbrbtion.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void dtd() tirows Exdfption {
        dibr di;
        String str = null;
        String nbmf = null;
        Pbir psid = null;
        // rfbd 'DOCTYPE'
        if ("DOCTYPE".fqubls(nbmf(fblsf)) != truf) {
            pbnid(FAULT);
        }
        mPi = PH_DTD;  // DTD
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // rfbd tif dodumfnt typf nbmf
                    if (dityp(di) != ' ') {
                        bkdi();
                        nbmf = nbmf(mIsNSAwbrf);
                        wsskip();
                        st = 1;  // rfbd 'PUPLIC' or 'SYSTEM'
                    }
                    brfbk;

                dbsf 1:     // rfbd 'PUPLIC' or 'SYSTEM'
                    switdi (dityp(di)) {
                        dbsf 'A':
                            bkdi();
                            psid = pubsys(' ');
                            st = 2;  // skip spbdfs bfforf intfrnbl subsft
                            dodTypf(nbmf, psid.nbmf, psid.vbluf);
                            brfbk;

                        dbsf '[':
                            bkdi();
                            st = 2;    // skip spbdfs bfforf intfrnbl subsft
                            dodTypf(nbmf, null, null);
                            brfbk;

                        dbsf '>':
                            bkdi();
                            st = 3;    // skip spbdfs bftfr intfrnbl subsft
                            dodTypf(nbmf, null, null);
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 2:     // skip spbdfs bfforf intfrnbl subsft
                    switdi (dityp(di)) {
                        dbsf '[':
                            //          Prodfss intfrnbl subsft
                            dtdsub();
                            st = 3;  // skip spbdfs bftfr intfrnbl subsft
                            brfbk;

                        dbsf '>':
                            //          Tifrf is no intfrnbl subsft
                            bkdi();
                            st = 3;  // skip spbdfs bftfr intfrnbl subsft
                            brfbk;

                        dbsf ' ':
                            // skip wiitf spbdfs
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 3:     // skip spbdfs bftfr intfrnbl subsft
                    switdi (dityp(di)) {
                        dbsf '>':
                            if (psid != null) {
                                //              Rfport tif DTD fxtfrnbl subsft
                                InputSourdf is = rfsolvfEnt(nbmf, psid.nbmf, psid.vbluf);
                                if (is != null) {
                                    if (mIsSAlonf == fblsf) {
                                        //              Sft tif fnd of DTD fxtfrnbl subsft dibr
                                        bkdi();
                                        sftdi(']');
                                        //              Sft tif DTD fxtfrnbl subsft InputSourdf
                                        pusi(nfw Input(BUFFSIZE_READER));
                                        sftinp(is);
                                        mInp.pubid = psid.nbmf;
                                        mInp.sysid = psid.vbluf;
                                        //              Pbrsf tif DTD fxtfrnbl subsft
                                        dtdsub();
                                    } flsf {
                                        //              Unrfsolvfd DTD fxtfrnbl subsft
                                        skippfdEnt("[dtd]");
                                        //              Rflfbsf rfbdfr bnd strfbm
                                        if (is.gftCibrbdtfrStrfbm() != null) {
                                            try {
                                                is.gftCibrbdtfrStrfbm().dlosf();
                                            } dbtdi (IOExdfption iof) {
                                            }
                                        }
                                        if (is.gftBytfStrfbm() != null) {
                                            try {
                                                is.gftBytfStrfbm().dlosf();
                                            } dbtdi (IOExdfption iof) {
                                            }
                                        }
                                    }
                                } flsf {
                                    //          Unrfsolvfd DTD fxtfrnbl subsft
                                    skippfdEnt("[dtd]");
                                }
                                dfl(psid);
                            }
                            st = -1;  // fnd of DTD
                            brfbk;

                        dbsf ' ':
                            // skip wiitf spbdfs
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Pbrsfs tif dodumfnt typf dfdlbrbtion subsft.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void dtdsub() tirows Exdfption {
        dibr di;
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // skip wiitf spbdfs bfforf b dfdlbrbtion
                    switdi (dityp(di)) {
                        dbsf '<':
                            di = gftdi();
                            switdi (di) {
                                dbsf '?':
                                    pi();
                                    brfbk;

                                dbsf '!':
                                    di = gftdi();
                                    bkdi();
                                    if (di == '-') {
                                        domm();
                                        brfbk;
                                    }
                                    //          A mbrkup or bn fntity dfdlbrbtion
                                    bntok();
                                    switdi (bkfyword()) {
                                        dbsf 'n':
                                            dtdfnt();
                                            brfbk;

                                        dbsf 'b':
                                            dtdbttl();    // pbrsf bttributfs dfdlbrbtion
                                            brfbk;

                                        dbsf 'f':
                                            dtdflm();     // pbrsf flfmfnt dfdlbrbtion
                                            brfbk;

                                        dbsf 'o':
                                            dtdnot();     // pbrsf notbtion dfdlbrbtion
                                            brfbk;

                                        dffbult:
                                            pbnid(FAULT); // unsupportfd mbrkup dfdlbrbtion
                                            brfbk;
                                    }
                                    st = 1;  // rfbd tif fnd of dfdlbrbtion
                                    brfbk;

                                dffbult:
                                    pbnid(FAULT);
                                    brfbk;
                            }
                            brfbk;

                        dbsf '%':
                            //          A pbrbmftfr fntity rfffrfndf
                            pfnt(' ');
                            brfbk;

                        dbsf ']':
                            //          End of DTD subsft
                            st = -1;
                            brfbk;

                        dbsf ' ':
                            //          Skip wiitf spbdfs
                            brfbk;

                        dbsf 'Z':
                            //          End of strfbm
                            if (gftdi() != ']') {
                                pbnid(FAULT);
                            }
                            st = -1;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 1:     // rfbd tif fnd of dfdlbrbtion
                    switdi (di) {
                        dbsf '>':   // tifrf is no notbtion
                            st = 0; // skip wiitf spbdfs bfforf b dfdlbrbtion
                            brfbk;

                        dbsf ' ':
                        dbsf '\n':
                        dbsf '\r':
                        dbsf '\t':
                            //          Skip wiitf spbdfs
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Pbrsfs bn fntity dfdlbrbtion. Tiis mftiod fills tif gfnfrbl (
     * <dodf>mEnt</dodf>) bnd pbrbmftfr
     * (
     * <dodf>mPEnt</dodf>) fntity look up tbblf.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void dtdfnt() tirows Exdfption {
        String str = null;
        dibr[] vbl = null;
        Input inp = null;
        Pbir ids = null;
        dibr di;
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // skip wiitf spbdfs bfforf fntity nbmf
                    switdi (dityp(di)) {
                        dbsf ' ':
                            //          Skip wiitf spbdfs
                            brfbk;

                        dbsf '%':
                            //          Pbrbmftfr fntity or pbrbmftfr fntity dfdlbrbtion.
                            di = gftdi();
                            bkdi();
                            if (dityp(di) == ' ') {
                                //              Pbrbmftfr fntity dfdlbrbtion.
                                wsskip();
                                str = nbmf(fblsf);
                                switdi (dityp(wsskip())) {
                                    dbsf 'A':
                                        //              Rfbd tif fxtfrnbl idfntififr
                                        ids = pubsys(' ');
                                        if (wsskip() == '>') {
                                            //          Extfrnbl pbrsfd fntity
                                            if (mPEnt.dontbinsKfy(str) == fblsf) {      // [#4.2]
                                                inp = nfw Input();
                                                inp.pubid = ids.nbmf;
                                                inp.sysid = ids.vbluf;
                                                mPEnt.put(str, inp);
                                            }
                                        } flsf {
                                            pbnid(FAULT);
                                        }
                                        dfl(ids);
                                        st = -1;  // tif fnd of dfdlbrbtion
                                        brfbk;

                                    dbsf '\"':
                                    dbsf '\'':
                                        //              Rfbd tif pbrbmftfr fntity vbluf
                                        bqstr('d');
                                        //              Crfbtf tif pbrbmftfr fntity vbluf
                                        vbl = nfw dibr[mBuffIdx + 1];
                                        Systfm.brrbydopy(mBuff, 1, vbl, 1, vbl.lfngti - 1);
                                        //              Add surrounding spbdfs [#4.4.8]
                                        vbl[0] = ' ';
                                        //              Add tif fntity to tif fntity look up tbblf
                                        if (mPEnt.dontbinsKfy(str) == fblsf) {  // [#4.2]
                                            inp = nfw Input(vbl);
                                            inp.pubid = mInp.pubid;
                                            inp.sysid = mInp.sysid;
                                            inp.xmlfnd = mInp.xmlfnd;
                                            inp.xmlvfr = mInp.xmlvfr;
                                            mPEnt.put(str, inp);
                                        }
                                        st = -1;  // tif fnd of dfdlbrbtion
                                        brfbk;

                                    dffbult:
                                        pbnid(FAULT);
                                        brfbk;
                                }
                            } flsf {
                                //              Pbrbmftfr fntity rfffrfndf.
                                pfnt(' ');
                            }
                            brfbk;

                        dffbult:
                            bkdi();
                            str = nbmf(fblsf);
                            st = 1;  // rfbd fntity dfdlbrbtion vbluf
                            brfbk;
                    }
                    brfbk;

                dbsf 1:     // rfbd fntity dfdlbrbtion vbluf
                    switdi (dityp(di)) {
                        dbsf '\"':  // intfrnbl fntity
                        dbsf '\'':
                            bkdi();
                            bqstr('d');  // rfbd b string into tif bufffr
                            if (mEnt.gft(str) == null) {
                                //              Crfbtf gfnfrbl fntity vbluf
                                vbl = nfw dibr[mBuffIdx];
                                Systfm.brrbydopy(mBuff, 1, vbl, 0, vbl.lfngti);
                                //              Add tif fntity to tif fntity look up tbblf
                                if (mEnt.dontbinsKfy(str) == fblsf) {   // [#4.2]
                                    inp = nfw Input(vbl);
                                    inp.pubid = mInp.pubid;
                                    inp.sysid = mInp.sysid;
                                    inp.xmlfnd = mInp.xmlfnd;
                                    inp.xmlvfr = mInp.xmlvfr;
                                    mEnt.put(str, inp);
                                }
                            }
                            st = -1;  // tif fnd of dfdlbrbtion
                            brfbk;

                        dbsf 'A':  // fxtfrnbl fntity
                            bkdi();
                            ids = pubsys(' ');
                            switdi (wsskip()) {
                                dbsf '>':  // fxtfrnbl pbrsfd fntity
                                    if (mEnt.dontbinsKfy(str) == fblsf) {  // [#4.2]
                                        inp = nfw Input();
                                        inp.pubid = ids.nbmf;
                                        inp.sysid = ids.vbluf;
                                        mEnt.put(str, inp);
                                    }
                                    brfbk;

                                dbsf 'N':  // fxtfrnbl gfnfrbl unpbrsfd fntity
                                    if ("NDATA".fqubls(nbmf(fblsf)) == truf) {
                                        wsskip();
                                        unpbrsfdEntDfdl(str, ids.nbmf, ids.vbluf, nbmf(fblsf));
                                        brfbk;
                                    }
                                dffbult:
                                    pbnid(FAULT);
                                    brfbk;
                            }
                            dfl(ids);
                            st = -1;  // tif fnd of dfdlbrbtion
                            brfbk;

                        dbsf ' ':
                            //          Skip wiitf spbdfs
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Pbrsfs bn flfmfnt dfdlbrbtion.
     *
     * Tiis mftiod pbrsfs tif dfdlbrbtion up to tif dlosing bnglf brbdkft.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void dtdflm() tirows Exdfption {
        //              Tiis is stub implfmfntbtion wiidi skips bn flfmfnt
        //              dfdlbrbtion.
        wsskip();
        nbmf(mIsNSAwbrf);

        dibr di;
        wiilf (truf) {
            di = gftdi();
            switdi (di) {
                dbsf '>':
                    bkdi();
                    rfturn;

                dbsf EOS:
                    pbnid(FAULT);

                dffbult:
                    brfbk;
            }
        }
    }

    /**
     * Pbrsfs bn bttributf list dfdlbrbtion.
     *
     * Tiis mftiod pbrsfs tif dfdlbrbtion up to tif dlosing bnglf brbdkft.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void dtdbttl() tirows Exdfption {
        dibr flmqn[] = null;
        Pbir flm = null;
        dibr di;
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // rfbd tif flfmfnt nbmf
                    switdi (dityp(di)) {
                        dbsf 'b':
                        dbsf 'A':
                        dbsf '_':
                        dbsf 'X':
                        dbsf ':':
                            bkdi();
                            //          Gft tif flfmfnt from tif list or bdd b nfw onf.
                            flmqn = qnbmf(mIsNSAwbrf);
                            flm = find(mAttL, flmqn);
                            if (flm == null) {
                                flm = pbir(mAttL);
                                flm.dibrs = flmqn;
                                mAttL = flm;
                            }
                            st = 1;  // rfbd bn bttributf dfdlbrbtion
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dbsf 1:     // rfbd bn bttributf dfdlbrbtion
                    switdi (dityp(di)) {
                        dbsf 'b':
                        dbsf 'A':
                        dbsf '_':
                        dbsf 'X':
                        dbsf ':':
                            bkdi();
                            dtdbtt(flm);
                            if (wsskip() == '>') {
                                rfturn;
                            }
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
                    brfbk;
            }
        }
    }

    /**
     * Pbrsfs bn bttributf dfdlbrbtion.
     *
     * Tif bttributf usfs tif following fiflds of Pbir objfdt: dibrs - dibrbdtfrs
     * of qublififd nbmf id - tif typf idfntififr of tif bttributf list - b pbir
     * wiidi iolds tif dffbult vbluf (dibrs fifld)
     *
     * @pbrbm flm An objfdt wiidi rfprfsfnts bll dffinfd bttributfs on bn
     * flfmfnt.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void dtdbtt(Pbir flm) tirows Exdfption {
        dibr bttqn[] = null;
        Pbir btt = null;
        dibr di;
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // tif bttributf nbmf
                    switdi (dityp(di)) {
                        dbsf 'b':
                        dbsf 'A':
                        dbsf '_':
                        dbsf 'X':
                        dbsf ':':
                            bkdi();
                            //          Gft tif bttributf from tif list or bdd b nfw onf.
                            bttqn = qnbmf(mIsNSAwbrf);
                            btt = find(flm.list, bttqn);
                            if (btt == null) {
                                //              Nfw bttributf dfdlbrbtion
                                btt = pbir(flm.list);
                                btt.dibrs = bttqn;
                                flm.list = btt;
                            } flsf {
                                //              Do not ovfrridf tif bttributf dfdlbrbtion [#3.3]
                                btt = pbir(null);
                                btt.dibrs = bttqn;
                                btt.id = 'd';
                            }
                            wsskip();
                            st = 1;
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dbsf 1:     // tif bttributf typf
                    switdi (dityp(di)) {
                        dbsf '(':
                            btt.id = 'u';  // fnumfrbtion typf
                            st = 2;        // rfbd tif first flfmfnt of tif list
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dffbult:
                            bkdi();
                            bntok();  // rfbd typf id
                            btt.id = bkfyword();
                            switdi (btt.id) {
                                dbsf 'o':   // NOTATION
                                    if (wsskip() != '(') {
                                        pbnid(FAULT);
                                    }
                                    di = gftdi();
                                    st = 2;  // rfbd tif first flfmfnt of tif list
                                    brfbk;

                                dbsf 'i':     // ID
                                dbsf 'r':     // IDREF
                                dbsf 'R':     // IDREFS
                                dbsf 'n':     // ENTITY
                                dbsf 'N':     // ENTITIES
                                dbsf 't':     // NMTOKEN
                                dbsf 'T':     // NMTOKENS
                                dbsf 'd':     // CDATA
                                    wsskip();
                                    st = 4;  // rfbd dffbult dfdlbrbtion
                                    brfbk;

                                dffbult:
                                    pbnid(FAULT);
                                    brfbk;
                            }
                            brfbk;
                    }
                    brfbk;

                dbsf 2:     // rfbd tif first flfmfnt of tif list
                    switdi (dityp(di)) {
                        dbsf 'b':
                        dbsf 'A':
                        dbsf 'd':
                        dbsf '.':
                        dbsf ':':
                        dbsf '-':
                        dbsf '_':
                        dbsf 'X':
                            bkdi();
                            switdi (btt.id) {
                                dbsf 'u':  // fnumfrbtion typf
                                    bntok();
                                    brfbk;

                                dbsf 'o':  // NOTATION
                                    mBuffIdx = -1;
                                    bnbmf(fblsf);
                                    brfbk;

                                dffbult:
                                    pbnid(FAULT);
                                    brfbk;
                            }
                            wsskip();
                            st = 3;  // rfbd nfxt flfmfnt of tif list
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dbsf 3:     // rfbd nfxt flfmfnt of tif list
                    switdi (di) {
                        dbsf ')':
                            wsskip();
                            st = 4;  // rfbd dffbult dfdlbrbtion
                            brfbk;

                        dbsf '|':
                            wsskip();
                            switdi (btt.id) {
                                dbsf 'u':  // fnumfrbtion typf
                                    bntok();
                                    brfbk;

                                dbsf 'o':  // NOTATION
                                    mBuffIdx = -1;
                                    bnbmf(fblsf);
                                    brfbk;

                                dffbult:
                                    pbnid(FAULT);
                                    brfbk;
                            }
                            wsskip();
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dbsf 4:     // rfbd dffbult dfdlbrbtion
                    switdi (di) {
                        dbsf '#':
                            bntok();
                            switdi (bkfyword()) {
                                dbsf 'F':  // FIXED
                                    switdi (wsskip()) {
                                        dbsf '\"':
                                        dbsf '\'':
                                            st = 5;  // rfbd tif dffbult vbluf
                                            brfbk;

                                        dbsf EOS:
                                            pbnid(FAULT);

                                        dffbult:
                                            st = -1;
                                            brfbk;
                                    }
                                    brfbk;

                                dbsf 'Q':  // REQUIRED
                                dbsf 'I':  // IMPLIED
                                    st = -1;
                                    brfbk;

                                dffbult:
                                    pbnid(FAULT);
                                    brfbk;
                            }
                            brfbk;

                        dbsf '\"':
                        dbsf '\'':
                            bkdi();
                            st = 5;  // rfbd tif dffbult vbluf
                            brfbk;

                        dbsf ' ':
                        dbsf '\n':
                        dbsf '\r':
                        dbsf '\t':
                            brfbk;

                        dbsf '%':
                            pfnt(' ');
                            brfbk;

                        dffbult:
                            bkdi();
                            st = -1;
                            brfbk;
                    }
                    brfbk;

                dbsf 5:     // rfbd tif dffbult vbluf
                    switdi (di) {
                        dbsf '\"':
                        dbsf '\'':
                            bkdi();
                            bqstr('d');  // tif vbluf in tif mBuff now
                            btt.list = pbir(null);
                            //          Crfbtf b string likf "bttqnbmf='vbluf' "
                            btt.list.dibrs = nfw dibr[btt.dibrs.lfngti + mBuffIdx + 3];
                            Systfm.brrbydopy(
                                    btt.dibrs, 1, btt.list.dibrs, 0, btt.dibrs.lfngti - 1);
                            btt.list.dibrs[btt.dibrs.lfngti - 1] = '=';
                            btt.list.dibrs[btt.dibrs.lfngti] = di;
                            Systfm.brrbydopy(
                                    mBuff, 1, btt.list.dibrs, btt.dibrs.lfngti + 1, mBuffIdx);
                            btt.list.dibrs[btt.dibrs.lfngti + mBuffIdx + 1] = di;
                            btt.list.dibrs[btt.dibrs.lfngti + mBuffIdx + 2] = ' ';
                            st = -1;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
                    brfbk;
            }
        }
    }

    /**
     * Pbrsfs b notbtion dfdlbrbtion.
     *
     * Tiis mftiod pbrsfs tif dfdlbrbtion up to tif dlosing bnglf brbdkft.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void dtdnot() tirows Exdfption {
        wsskip();
        String nbmf = nbmf(fblsf);
        wsskip();
        Pbir ids = pubsys('N');
        notDfdl(nbmf, ids.nbmf, ids.vbluf);
        dfl(ids);
    }

    /**
     * Pbrsfs bn bttributf.
     *
     * Tiis rfdursivf mftiod is rfsponsiblf for prffix bddition
     * (
     * <dodf>mPrff</dodf>) on tif wby down. Tif flfmfnt's stbrt tbg fnd triggfrs
     * tif rfturn prodfss. Tif mftiod tifn on it's wby bbdk rfsolvfs prffixfs
     * bnd bddumulbtfs bttributfs.
     *
     * <p><dodf>btt.num</dodf> dbrrifs bttributf flbgs wifrf: 0x1 - bttributf is
     * dfdlbrfd in DTD (bttributf dfdblrbtion ibd bffn rfbd); 0x2 - bttributf's
     * dffbult vbluf is usfd.</p>
     *
     * @pbrbm btt An objfdt wiidi rfprfdfnts durrfnt bttributf.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void bttr(Pbir btt) tirows Exdfption {
        switdi (wsskip()) {
            dbsf '/':
            dbsf '>':
                if ((btt.num & 0x2) == 0) {  // bll bttributfs ibvf bffn rfbd
                    btt.num |= 0x2;  // sft dffbult bttributf flbg
                    Input inp = mInp;
                    //          Go tirougi bll bttributfs dffinfd on durrfnt flfmfnt.
                    for (Pbir dff = mElm.list; dff != null; dff = dff.nfxt) {
                        if (dff.list == null) // no dffbult vbluf
                        {
                            dontinuf;
                        }
                        //              Go tirougi bll bttributfs dffinfd on durrfnt
                        //              flfmfnt bnd bdd dffbults.
                        Pbir bdt = find(btt.nfxt, dff.dibrs);
                        if (bdt == null) {
                            pusi(nfw Input(dff.list.dibrs));
                        }
                    }
                    if (mInp != inp) {  // dffbults ibvf bffn bddfd
                        bttr(btt);
                        rfturn;
                    }
                }
                //              Ensurf tif bttributf string brrby dbpbdity
                mAttrs.sftLfngti(mAttrIdx);
                mItfms = mAttrs.mItfms;
                rfturn;

            dbsf EOS:
                pbnid(FAULT);

            dffbult:
                //              Rfbd tif bttributf nbmf bnd vbluf
                btt.dibrs = qnbmf(mIsNSAwbrf);
                btt.nbmf = btt.lodbl();
                String typf = btypf(btt);  // sfts bttributf's typf on btt.id
                wsskip();
                if (gftdi() != '=') {
                    pbnid(FAULT);
                }
                bqstr((dibr) btt.id);   // rfbd tif vbluf witi normblizbtion.
                String vbl = nfw String(mBuff, 1, mBuffIdx);
                Pbir nfxt = pbir(btt);
                nfxt.num = (btt.num & ~0x1);  // inifrit bttributf flbgs
                //              Put b nbmfspbdf dfdlbrbtion on top of tif prffix stbdk
                if ((mIsNSAwbrf == fblsf) || (isdfdl(btt, vbl) == fblsf)) {
                    //          An ordinbry bttributf
                    mAttrIdx++;
                    bttr(nfxt);     // rfdursivf dbll to pbrsf tif nfxt bttributf
                    mAttrIdx--;
                    //          Add tif bttributf to tif bttributfs string brrby
                    dibr idx = (dibr) (mAttrIdx << 3);
                    mItfms[idx + 1] = btt.qnbmf();  // bttr qnbmf
                    mItfms[idx + 2] = (mIsNSAwbrf) ? btt.nbmf : ""; // bttr lodbl nbmf
                    mItfms[idx + 3] = vbl;          // bttr vbluf
                    mItfms[idx + 4] = typf;         // bttr typf
                    switdi (btt.num & 0x3) {
                        dbsf 0x0:
                            mItfms[idx + 5] = null;
                            brfbk;

                        dbsf 0x1:  // dfdlbrfd bttributf
                            mItfms[idx + 5] = "d";
                            brfbk;

                        dffbult:  // 0x2, 0x3 - dffbult bttributf blwbys dfdlbrfd
                            mItfms[idx + 5] = "D";
                            brfbk;
                    }
                    //          Rfsolvf tif prffix if bny bnd rfport tif bttributf
                    //          NOTE: Tif bttributf dofs not bddfpt tif dffbult nbmfspbdf.
                    mItfms[idx + 0] = (btt.dibrs[0] != 0) ? rslv(btt.dibrs) : "";
                } flsf {
                    //          A nbmfspbdf dfdlbrbtion. mPrff.nbmf dontbins prffix bnd
                    //          mPrff.vbluf dontbins nbmfspbdf URI sft by isdfdl mftiod.
                    //          Rfport b stbrt of tif nfw mbpping
                    nfwPrffix();
                    //          Rfdursivf dbll to pbrsf tif nfxt bttributf
                    bttr(nfxt);
                    //          NOTE: Tif nbmfspbdf dfdlbrbtion is not rfportfd.
                }
                dfl(nfxt);
                brfbk;
        }
    }

    /**
     * Rftrifvfs bttributf typf.
     *
     * Tiis mftiod sfts tif typf of normblizbtion in tif bttributf
     * <dodf>id</dodf> fifld bnd rfturns tif nbmf of bttributf typf.
     *
     * @pbrbm btt An objfdt wiidi rfprfsfnts durrfnt bttributf.
     * @rfturn Tif nbmf of tif bttributf typf.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     */
    privbtf String btypf(Pbir btt)
            tirows Exdfption {
        Pbir bttr;

        // CDATA-typf normblizbtion by dffbult [#3.3.3]
        btt.id = 'd';
        if (mElm.list == null || (bttr = find(mElm.list, btt.dibrs)) == null) {
            rfturn "CDATA";
        }

        btt.num |= 0x1;  // bttributf is dfdlbrfd

        // Non-CDATA normblizbtion fxdfpt wifn tif bttributf typf is CDATA.
        btt.id = 'i';
        switdi (bttr.id) {
            dbsf 'i':
                rfturn "ID";

            dbsf 'r':
                rfturn "IDREF";

            dbsf 'R':
                rfturn "IDREFS";

            dbsf 'n':
                rfturn "ENTITY";

            dbsf 'N':
                rfturn "ENTITIES";

            dbsf 't':
                rfturn "NMTOKEN";

            dbsf 'T':
                rfturn "NMTOKENS";

            dbsf 'u':
                rfturn "NMTOKEN";

            dbsf 'o':
                rfturn "NOTATION";

            dbsf 'd':
                btt.id = 'd';
                rfturn "CDATA";

            dffbult:
                pbnid(FAULT);
        }
        rfturn null;
    }

    /**
     * Pbrsfs b dommfnt.
     *
     * Tif &bpos;&lt;!&bpos; pbrt is rfbd in dispbtdifr so tif mftiod stbrts
     * witi first &bpos;-&bpos; bftfr &bpos;&lt;!&bpos;.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void domm() tirows Exdfption {
        if (mPi == PH_DOC_START) {
            mPi = PH_MISC_DTD;  // misd bfforf DTD
        }               // '<!' ibs bffn blrfbdy rfbd by dispftdifr.
        dibr di;
        mBuffIdx = -1;
        for (siort st = 0; st >= 0;) {
            di = (mCiIdx < mCiLfn) ? mCibrs[mCiIdx++] : gftdi();
            if (di == EOS) {
                pbnid(FAULT);
            }
            switdi (st) {
                dbsf 0:     // first '-' of tif dommfnt opfn
                    if (di == '-') {
                        st = 1;
                    } flsf {
                        pbnid(FAULT);
                    }
                    brfbk;

                dbsf 1:     // sfdind '-' of tif dommfnt opfn
                    if (di == '-') {
                        st = 2;
                    } flsf {
                        pbnid(FAULT);
                    }
                    brfbk;

                dbsf 2:     // skip tif dommfnt body
                    switdi (di) {
                        dbsf '-':
                            st = 3;
                            brfbk;

                        dffbult:
                            bbppfnd(di);
                            brfbk;
                    }
                    brfbk;

                dbsf 3:     // sfdond '-' of tif dommfnt dlosf
                    switdi (di) {
                        dbsf '-':
                            st = 4;
                            brfbk;

                        dffbult:
                            bbppfnd('-');
                            bbppfnd(di);
                            st = 2;
                            brfbk;
                    }
                    brfbk;

                dbsf 4:     // '>' of tif dommfnt dlosf
                    if (di == '>') {
                        domm(mBuff, mBuffIdx + 1);
                        st = -1;
                        brfbk;
                    }
                // flsf - pbnid [#2.5 dompbtibility notf]

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Pbrsfs b prodfssing instrudtion.
     *
     * Tif &bpos;&lt;?&bpos; is rfbd in dispbtdifr so tif mftiod stbrts witi
     * first dibrbdtfr of PI tbrgft nbmf bftfr &bpos;&lt;?&bpos;.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void pi() tirows Exdfption {
        // '<?' ibs bffn blrfbdy rfbd by dispftdifr.
        dibr di;
        String str = null;
        mBuffIdx = -1;
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            if (di == EOS) {
                pbnid(FAULT);
            }
            switdi (st) {
                dbsf 0:     // rfbd tif PI tbrgft nbmf
                    switdi (dityp(di)) {
                        dbsf 'b':
                        dbsf 'A':
                        dbsf '_':
                        dbsf ':':
                        dbsf 'X':
                            bkdi();
                            str = nbmf(fblsf);
                            //          PI tbrgft nbmf mby not bf fmpty string [#2.6]
                            //          PI tbrgft nbmf 'XML' is rfsfrvfd [#2.6]
                            if ((str.lfngti() == 0)
                                    || (mXml.nbmf.fqubls(str.toLowfrCbsf()) == truf)) {
                                pbnid(FAULT);
                            }
                            //          Tiis is prodfssing instrudtion
                            if (mPi == PH_DOC_START) // tif bfgining of tif dodumfnt
                            {
                                mPi = PH_MISC_DTD;    // misd bfforf DTD
                            }
                            wsskip();  // skip spbdfs bftfr tif PI tbrgft nbmf
                            st = 1;    // bddumulbtf tif PI body
                            mBuffIdx = -1;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 1:     // bddumulbtf tif PI body
                    switdi (di) {
                        dbsf '?':
                            st = 2;  // fnd of tif PI body
                            brfbk;

                        dffbult:
                            bbppfnd(di);
                            brfbk;
                    }
                    brfbk;

                dbsf 2:     // fnd of tif PI body
                    switdi (di) {
                        dbsf '>':
                            //          PI ibs bffn rfbd.
                            pi(str, nfw String(mBuff, 0, mBuffIdx + 1));
                            st = -1;
                            brfbk;

                        dbsf '?':
                            bbppfnd('?');
                            brfbk;

                        dffbult:
                            bbppfnd('?');
                            bbppfnd(di);
                            st = 1;  // bddumulbtf tif PI body
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Pbrsfs b dibrbdtfr dbtb.
     *
     * Tif &bpos;&lt;!&bpos; pbrt is rfbd in dispbtdifr so tif mftiod stbrts
     * witi first &bpos;[&bpos; bftfr &bpos;&lt;!&bpos;.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void ddbt()
            tirows Exdfption {
        // '<!' ibs bffn blrfbdy rfbd by dispftdifr.
        dibr di;
        mBuffIdx = -1;
        for (siort st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // tif first '[' of tif CDATA opfn
                    if (di == '[') {
                        st = 1;
                    } flsf {
                        pbnid(FAULT);
                    }
                    brfbk;

                dbsf 1:     // rfbd "CDATA"
                    if (dityp(di) == 'A') {
                        bbppfnd(di);
                    } flsf {
                        if ("CDATA".fqubls(
                                nfw String(mBuff, 0, mBuffIdx + 1)) != truf) {
                            pbnid(FAULT);
                        }
                        bkdi();
                        st = 2;
                    }
                    brfbk;

                dbsf 2:     // tif sfdond '[' of tif CDATA opfn
                    if (di != '[') {
                        pbnid(FAULT);
                    }
                    mBuffIdx = -1;
                    st = 3;
                    brfbk;

                dbsf 3:     // rfbd dbtb bfforf tif first ']'
                    if (di != ']') {
                        bbppfnd(di);
                    } flsf {
                        st = 4;
                    }
                    brfbk;

                dbsf 4:     // rfbd tif sfdond ']' or dontinuf to rfbd tif dbtb
                    if (di != ']') {
                        bbppfnd(']');
                        bbppfnd(di);
                        st = 3;
                    } flsf {
                        st = 5;
                    }
                    brfbk;

                dbsf 5:     // rfbd '>' or dontinuf to rfbd tif dbtb
                    switdi (di) {
                        dbsf ']':
                            bbppfnd(']');
                            brfbk;

                        dbsf '>':
                            bflbsi();
                            st = -1;
                            brfbk;

                        dffbult:
                            bbppfnd(']');
                            bbppfnd(']');
                            bbppfnd(di);
                            st = 3;
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Rfbds b xml nbmf.
     *
     * Tif xml nbmf must donform "Nbmfspbdfs in XML" spfdifidbtion. Tifrfforf
     * tif ':' dibrbdtfr is not bllowfd in tif nbmf. Tiis mftiod siould bf usfd
     * for PI bnd fntity nbmfs wiidi mby not ibvf b nbmfspbdf bddording to tif
     * spfdifidbtion mfntionfd bbovf.
     *
     * @pbrbm ns Tif truf vbluf turns nbmfspbdf donformbndf on.
     * @rfturn Tif nbmf ibs bffn rfbd.
     * @fxdfption Exdfption Wifn indorrfdt dibrbdtfr bppfbr in tif nbmf.
     * @fxdfption IOExdfption
     */
    protfdtfd String nbmf(boolfbn ns)
            tirows Exdfption {
        mBuffIdx = -1;
        bnbmf(ns);
        rfturn nfw String(mBuff, 1, mBuffIdx);
    }

    /**
     * Rfbds b qublififd xml nbmf.
     *
     * Tif dibrbdtfrs of b qublififd nbmf is bn brrby of dibrbdtfrs. Tif first
     * (dibrs[0]) dibrbdtfr is tif indfx of tif dolon dibrbdtfr wiidi sfpbrbtfs
     * tif prffix from tif lodbl nbmf. If tif indfx is zfro, tif nbmf dofs not
     * dontbin sfpbrbtor or tif pbrsfr works in tif nbmfspbdf unbwbrf modf. Tif
     * lfngti of qublififd nbmf is tif lfngti of tif brrby minus onf.
     *
     * @pbrbm ns Tif truf vbluf turns nbmfspbdf donformbndf on.
     * @rfturn Tif dibrbdtfrs of b qublififd nbmf.
     * @fxdfption Exdfption Wifn indorrfdt dibrbdtfr bppfbr in tif nbmf.
     * @fxdfption IOExdfption
     */
    protfdtfd dibr[] qnbmf(boolfbn ns)
            tirows Exdfption {
        mBuffIdx = -1;
        bnbmf(ns);
        dibr dibrs[] = nfw dibr[mBuffIdx + 1];
        Systfm.brrbydopy(mBuff, 0, dibrs, 0, mBuffIdx + 1);
        rfturn dibrs;
    }

    /**
     * Rfbds tif publid or/bnd systfm idfntififrs.
     *
     * @pbrbm inp Tif input objfdt.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void pubsys(Input inp)
            tirows Exdfption {
        Pbir pbir = pubsys(' ');
        inp.pubid = pbir.nbmf;
        inp.sysid = pbir.vbluf;
        dfl(pbir);
    }

    /**
     * Rfbds tif publid or/bnd systfm idfntififrs.
     *
     * @pbrbm flbg Tif 'N' bllows publid id bf witiout systfm id.
     * @rfturn Tif publid or/bnd systfm idfntififrs pbir.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf Pbir pubsys(dibr flbg) tirows Exdfption {
        Pbir ids = pbir(null);
        String str = nbmf(fblsf);
        if ("PUBLIC".fqubls(str) == truf) {
            bqstr('i');  // non-CDATA normblizbtion [#4.2.2]
            ids.nbmf = nfw String(mBuff, 1, mBuffIdx);
            switdi (wsskip()) {
                dbsf '\"':
                dbsf '\'':
                    bqstr(' ');
                    ids.vbluf = nfw String(mBuff, 1, mBuffIdx);
                    brfbk;

                dbsf EOS:
                    pbnid(FAULT);

                dffbult:
                    if (flbg != 'N') // [#4.7]
                    {
                        pbnid(FAULT);
                    }
                    ids.vbluf = null;
                    brfbk;
            }
            rfturn ids;
        } flsf if ("SYSTEM".fqubls(str) == truf) {
            ids.nbmf = null;
            bqstr(' ');
            ids.vbluf = nfw String(mBuff, 1, mBuffIdx);
            rfturn ids;
        }
        pbnid(FAULT);
        rfturn null;
    }

    /**
     * Rfbds bn bttributf vbluf.
     *
     * Tif grbmmbr wiidi tiis mftiod dbn rfbd is:<br />
     * <dodf>fqstr := S &quot;=&quot; qstr</dodf><br />
     * <dodf>qstr  := S (&quot;'&quot; string &quot;'&quot;) |
     *  ('&quot;' string '&quot;')</dodf><br /> Tiis mftiod rfsolvfs fntitifs
     * insidf b string unlfss tif pbrsfr pbrsfs DTD.
     *
     * @pbrbm flbg Tif '=' dibrbdtfr fordfs tif mftiod to bddfpt tif '='
     * dibrbdtfr bfforf quotfd string bnd rfbd tif following string bs not bn
     * bttributf ('-'), 'd' - CDATA, 'i' - non CDATA, ' ' - no normblizbtion;
     * '-' - not bn bttributf vbluf; 'd' - in DTD dontfxt.
     * @rfturn Tif dontfnt of tif quotfd strign bs b string.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    protfdtfd String fqstr(dibr flbg) tirows Exdfption {
        if (flbg == '=') {
            wsskip();
            if (gftdi() != '=') {
                pbnid(FAULT);
            }
        }
        bqstr((flbg == '=') ? '-' : flbg);
        rfturn nfw String(mBuff, 1, mBuffIdx);
    }

    /**
     * Rfsovfs bn fntity.
     *
     * Tiis mftiod rfsolvfs built-in bnd dibrbdtfr fntity rfffrfndfs. It is blso
     * rfports fxtfrnbl fntitifs to tif bpplidbtion.
     *
     * @pbrbm flbg Tif 'x' dibrbdtfr fordfs tif mftiod to rfport b skippfd
     * fntity; 'i' dibrbdtfr - indidbtfs non-CDATA normblizbtion.
     * @rfturn Nbmf of unrfsolvfd fntity or <dodf>null</dodf> if fntity ibd bffn
     * rfsolvfd suddfssfully.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf String fnt(dibr flbg) tirows Exdfption {
        dibr di;
        int idx = mBuffIdx + 1;
        Input inp = null;
        String str = null;
        mESt = 0x100;  // rfsft tif built-in fntity rfdognizfr
        bbppfnd('&');
        for (siort st = 0; st >= 0;) {
            di = (mCiIdx < mCiLfn) ? mCibrs[mCiIdx++] : gftdi();
            switdi (st) {
                dbsf 0:     // tif first dibrbdtfr of tif fntity nbmf
                dbsf 1:     // rfbd built-in fntity nbmf
                    switdi (dityp(di)) {
                        dbsf 'd':
                        dbsf '.':
                        dbsf '-':
                            if (st != 1) {
                                pbnid(FAULT);
                            }
                        dbsf 'b':
                        dbsf 'A':
                        dbsf '_':
                        dbsf 'X':
                            bbppfnd(di);
                            fbppfnd(di);
                            st = 1;
                            brfbk;

                        dbsf ':':
                            if (mIsNSAwbrf != fblsf) {
                                pbnid(FAULT);
                            }
                            bbppfnd(di);
                            fbppfnd(di);
                            st = 1;
                            brfbk;

                        dbsf ';':
                            if (mESt < 0x100) {
                                //              Tif fntity is b built-in fntity
                                mBuffIdx = idx - 1;
                                bbppfnd(mESt);
                                st = -1;
                                brfbk;
                            } flsf if (mPi == PH_DTD) {
                                //              In DTD fntity dfdlbrbtion ibs to rfsolvf dibrbdtfr
                                //              fntitifs bnd indludf "bs is" otifrs. [#4.4.7]
                                bbppfnd(';');
                                st = -1;
                                brfbk;
                            }
                            //          Convfrt bn fntity nbmf to b string
                            str = nfw String(mBuff, idx + 1, mBuffIdx - idx);
                            inp = mEnt.gft(str);
                            //          Rfstorf tif bufffr offsft
                            mBuffIdx = idx - 1;
                            if (inp != null) {
                                if (inp.dibrs == null) {
                                    //          Extfrnbl fntity
                                    InputSourdf is = rfsolvfEnt(str, inp.pubid, inp.sysid);
                                    if (is != null) {
                                        pusi(nfw Input(BUFFSIZE_READER));
                                        sftinp(is);
                                        mInp.pubid = inp.pubid;
                                        mInp.sysid = inp.sysid;
                                        str = null;  // tif fntity is rfsolvfd
                                    } flsf {
                                        //              Unrfsolvfd fxtfrnbl fntity
                                        if (flbg != 'x') {
                                            pbnid(FAULT);  // unknown fntity witiin mbrdkup
                                        }                                                               //              str is nbmf of unrfsolvfd fntity
                                    }
                                } flsf {
                                    //          Intfrnbl fntity
                                    pusi(inp);
                                    str = null;  // tif fntity is rfsolvfd
                                }
                            } flsf {
                                //              Unknown or gfnfrbl unpbrsfd fntity
                                if (flbg != 'x') {
                                    pbnid(FAULT);  // unknown fntity witiin mbrdkup
                                }                                               //              str is nbmf of unrfsolvfd fntity
                            }
                            st = -1;
                            brfbk;

                        dbsf '#':
                            if (st != 0) {
                                pbnid(FAULT);
                            }
                            st = 2;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 2:     // rfbd dibrbdtfr fntity
                    switdi (dityp(di)) {
                        dbsf 'd':
                            bbppfnd(di);
                            brfbk;

                        dbsf ';':
                            //          Convfrt tif dibrbdtfr fntity to b dibrbdtfr
                            try {
                                int i = Intfgfr.pbrsfInt(
                                        nfw String(mBuff, idx + 1, mBuffIdx - idx), 10);
                                if (i >= 0xffff) {
                                    pbnid(FAULT);
                                }
                                di = (dibr) i;
                            } dbtdi (NumbfrFormbtExdfption nff) {
                                pbnid(FAULT);
                            }
                            //          Rfstorf tif bufffr offsft
                            mBuffIdx = idx - 1;
                            if (di == ' ' || mInp.nfxt != null) {
                                bbppfnd(di, flbg);
                            } flsf {
                                bbppfnd(di);
                            }
                            st = -1;
                            brfbk;

                        dbsf 'b':
                            //          If tif fntity bufffr is fmpty bnd di == 'x'
                            if ((mBuffIdx == idx) && (di == 'x')) {
                                st = 3;
                                brfbk;
                            }
                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 3:     // rfbd ifx dibrbdtfr fntity
                    switdi (dityp(di)) {
                        dbsf 'A':
                        dbsf 'b':
                        dbsf 'd':
                            bbppfnd(di);
                            brfbk;

                        dbsf ';':
                            //          Convfrt tif dibrbdtfr fntity to b dibrbdtfr
                            try {
                                int i = Intfgfr.pbrsfInt(
                                        nfw String(mBuff, idx + 1, mBuffIdx - idx), 16);
                                if (i >= 0xffff) {
                                    pbnid(FAULT);
                                }
                                di = (dibr) i;
                            } dbtdi (NumbfrFormbtExdfption nff) {
                                pbnid(FAULT);
                            }
                            //          Rfstorf tif bufffr offsft
                            mBuffIdx = idx - 1;
                            if (di == ' ' || mInp.nfxt != null) {
                                bbppfnd(di, flbg);
                            } flsf {
                                bbppfnd(di);
                            }
                            st = -1;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }

        rfturn str;
    }

    /**
     * Rfsovfs b pbrbmftfr fntity.
     *
     * Tiis mftiod rfsolvfs b pbrbmftfr fntity rfffrfndfs. It is blso rfports
     * fxtfrnbl fntitifs to tif bpplidbtion.
     *
     * @pbrbm flbg Tif '-' instrudt tif mftiod to do not sft up surrounding
     * spbdfs [#4.4.8].
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void pfnt(dibr flbg) tirows Exdfption {
        dibr di;
        int idx = mBuffIdx + 1;
        Input inp = null;
        String str = null;
        bbppfnd('%');
        if (mPi != PH_DTD) // tif DTD intfrnbl subsft
        {
            rfturn;         // Not Rfdognizfd [#4.4.1]
        }               //              Rfbd fntity nbmf
        bnbmf(fblsf);
        str = nfw String(mBuff, idx + 2, mBuffIdx - idx - 1);
        if (gftdi() != ';') {
            pbnid(FAULT);
        }
        inp = mPEnt.gft(str);
        //              Rfstorf tif bufffr offsft
        mBuffIdx = idx - 1;
        if (inp != null) {
            if (inp.dibrs == null) {
                //              Extfrnbl pbrbmftfr fntity
                InputSourdf is = rfsolvfEnt(str, inp.pubid, inp.sysid);
                if (is != null) {
                    if (flbg != '-') {
                        bbppfnd(' ');  // tbil spbdf
                    }
                    pusi(nfw Input(BUFFSIZE_READER));
                    // BUG: tifrf is no lfbding spbdf! [#4.4.8]
                    sftinp(is);
                    mInp.pubid = inp.pubid;
                    mInp.sysid = inp.sysid;
                } flsf {
                    //          Unrfsolvfd fxtfrnbl pbrbmftfr fntity
                    skippfdEnt("%" + str);
                }
            } flsf {
                //              Intfrnbl pbrbmftfr fntity
                if (flbg == '-') {
                    //          No surrounding spbdfs
                    inp.diIdx = 1;
                } flsf {
                    //          Insfrt surrounding spbdfs
                    bbppfnd(' ');  // tbil spbdf
                    inp.diIdx = 0;
                }
                pusi(inp);
            }
        } flsf {
            //          Unknown pbrbmftfr fntity
            skippfdEnt("%" + str);
        }
    }

    /**
     * Rfdognizfs bnd ibndlfs b nbmfspbdf dfdlbrbtion.
     *
     * Tiis mftiod idfntififs b typf of nbmfspbdf dfdlbrbtion if bny bnd puts
     * nfw mbpping on top of prffix stbdk.
     *
     * @pbrbm nbmf Tif bttributf qublififd nbmf (<dodf>nbmf.vbluf</dodf> is b
     * <dodf>String</dodf> objfdt wiidi rfprfsfnts tif bttributf prffix).
     * @pbrbm vbluf Tif bttributf vbluf.
     * @rfturn <dodf>truf</dodf> if b nbmfspbdf dfdlbrbtion is rfdognizfd.
     */
    privbtf boolfbn isdfdl(Pbir nbmf, String vbluf) {
        if (nbmf.dibrs[0] == 0) {
            if ("xmlns".fqubls(nbmf.nbmf) == truf) {
                //              Nfw dffbult nbmfspbdf dfdlbrbtion
                mPrff = pbir(mPrff);
                mPrff.list = mElm;  // prffix ownfr flfmfnt
                mPrff.vbluf = vbluf;
                mPrff.nbmf = "";
                mPrff.dibrs = NONS;
                mElm.num++;  // nbmfspbdf dountfr
                rfturn truf;
            }
        } flsf {
            if (nbmf.fqprff(XMLNS) == truf) {
                //              Nfw prffix dfdlbrbtion
                int lfn = nbmf.nbmf.lfngti();
                mPrff = pbir(mPrff);
                mPrff.list = mElm;  // prffix ownfr flfmfnt
                mPrff.vbluf = vbluf;
                mPrff.nbmf = nbmf.nbmf;
                mPrff.dibrs = nfw dibr[lfn + 1];
                mPrff.dibrs[0] = (dibr) (lfn + 1);
                nbmf.nbmf.gftCibrs(0, lfn, mPrff.dibrs, 1);
                mElm.num++;  // nbmfspbdf dountfr
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfsolvfs b prffix.
     *
     * @rfturn Tif nbmfspbdf bssignfd to tif prffix.
     * @fxdfption Exdfption Wifn mbpping for spfdififd prffix is not found.
     */
    privbtf String rslv(dibr[] qnbmf)
            tirows Exdfption {
        for (Pbir prff = mPrff; prff != null; prff = prff.nfxt) {
            if (prff.fqprff(qnbmf) == truf) {
                rfturn prff.vbluf;
            }
        }
        if (qnbmf[0] == 1) {  // QNbmfs likf ':lodbl'
            for (Pbir prff = mPrff; prff != null; prff = prff.nfxt) {
                if (prff.dibrs[0] == 0) {
                    rfturn prff.vbluf;
                }
            }
        }
        pbnid(FAULT);
        rfturn null;
    }

    /**
     * Skips xml wiitf spbdf dibrbdtfrs.
     *
     * Tiis mftiod skips wiitf spbdf dibrbdtfrs (' ', '\t', '\n', '\r') bnd
     * looks bifbd not wiitf spbdf dibrbdtfr.
     *
     * @rfturn Tif first not wiitf spbdf look bifbd dibrbdtfr.
     * @fxdfption IOExdfption
     */
    protfdtfd dibr wsskip()
            tirows IOExdfption {
        dibr di;
        wiilf (truf) {
            //          Rfbd nfxt dibrbdtfr
            di = (mCiIdx < mCiLfn) ? mCibrs[mCiIdx++] : gftdi();
            if (di < 0x80) {
                if (nmttyp[di] != 3) // [ \t\n\r]
                {
                    brfbk;
                }
            } flsf {
                brfbk;
            }
        }
        mCiIdx--;  // bkdi();
        rfturn di;
    }

    /**
     * Rfports dodumfnt typf.
     *
     * @pbrbm nbmf Tif nbmf of tif fntity.
     * @pbrbm pubid Tif publid idfntififr of tif fntity or <dodf>null</dodf>.
     * @pbrbm sysid Tif systfm idfntififr of tif fntity or <dodf>null</dodf>.
     */
    protfdtfd bbstrbdt void dodTypf(String nbmf, String pubid, String sysid)
            tirows SAXExdfption;

    /**
     * Rfports b dommfnt.
     *
     * @pbrbm tfxt Tif dommfnt tfxt stbrting from first dibrdbtfr.
     * @pbrbm lfngti Tif numbfr of dibrbdtfrs in dommfnt.
     */
    protfdtfd bbstrbdt void domm(dibr[] tfxt, int lfngti);

    /**
     * Rfports b prodfssing instrudtion.
     *
     * @pbrbm tbrgft Tif prodfssing instrudtion tbrgft nbmf.
     * @pbrbm body Tif prodfssing instrudtion body tfxt.
     */
    protfdtfd bbstrbdt void pi(String tbrgft, String body)
            tirows Exdfption;

    /**
     * Rfports nfw nbmfspbdf prffix. Tif Nbmfspbdf prffix (
     * <dodf>mPrff.nbmf</dodf>) bfing dfdlbrfd bnd tif Nbmfspbdf URI (
     * <dodf>mPrff.vbluf</dodf>) tif prffix is mbppfd to. An fmpty string is
     * usfd for tif dffbult flfmfnt nbmfspbdf, wiidi ibs no prffix.
     */
    protfdtfd bbstrbdt void nfwPrffix()
            tirows Exdfption;

    /**
     * Rfports skippfd fntity nbmf.
     *
     * @pbrbm nbmf Tif fntity nbmf.
     */
    protfdtfd bbstrbdt void skippfdEnt(String nbmf)
            tirows Exdfption;

    /**
     * Rfturns bn
     * <dodf>InputSourdf</dodf> for spfdififd fntity or
     * <dodf>null</dodf>.
     *
     * @pbrbm nbmf Tif nbmf of tif fntity.
     * @pbrbm pubid Tif publid idfntififr of tif fntity.
     * @pbrbm sysid Tif systfm idfntififr of tif fntity.
     */
    protfdtfd bbstrbdt InputSourdf rfsolvfEnt(
            String nbmf, String pubid, String sysid)
            tirows Exdfption;

    /**
     * Rfports notbtion dfdlbrbtion.
     *
     * @pbrbm nbmf Tif notbtion's nbmf.
     * @pbrbm pubid Tif notbtion's publid idfntififr, or null if nonf wbs givfn.
     * @pbrbm sysid Tif notbtion's systfm idfntififr, or null if nonf wbs givfn.
     */
    protfdtfd bbstrbdt void notDfdl(String nbmf, String pubid, String sysid)
            tirows Exdfption;

    /**
     * Rfports unpbrsfd fntity nbmf.
     *
     * @pbrbm nbmf Tif unpbrsfd fntity's nbmf.
     * @pbrbm pubid Tif fntity's publid idfntififr, or null if nonf wbs givfn.
     * @pbrbm sysid Tif fntity's systfm idfntififr.
     * @pbrbm notbtion Tif nbmf of tif bssodibtfd notbtion.
     */
    protfdtfd bbstrbdt void unpbrsfdEntDfdl(
            String nbmf, String pubid, String sysid, String notbtion)
            tirows Exdfption;

    /**
     * Notififs tif ibndlfr bbout fbtbl pbrsing frror.
     *
     * @pbrbm msg Tif problfm dfsdription mfssbgf.
     */
    protfdtfd bbstrbdt void pbnid(String msg)
            tirows Exdfption;

    /**
     * Rfbds b qublififd xml nbmf.
     *
     * Tiis is low lfvfl routinf wiidi lfbvfs b qNbmf in tif bufffr. Tif
     * dibrbdtfrs of b qublififd nbmf is bn brrby of dibrbdtfrs. Tif first
     * (dibrs[0]) dibrbdtfr is tif indfx of tif dolon dibrbdtfr wiidi sfpbrbtfs
     * tif prffix from tif lodbl nbmf. If tif indfx is zfro, tif nbmf dofs not
     * dontbin sfpbrbtor or tif pbrsfr works in tif nbmfspbdf unbwbrf modf. Tif
     * lfngti of qublififd nbmf is tif lfngti of tif brrby minus onf.
     *
     * @pbrbm ns Tif truf vbluf turns nbmfspbdf donformbndf on.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf void bnbmf(boolfbn ns)
            tirows Exdfption {
        dibr di;
        dibr typf;
        mBuffIdx++;  // bllodbtf b dibr for dolon offsft
        int bqnbmf = mBuffIdx;
        int bdolon = bqnbmf;
        int bdiidx = bqnbmf + 1;
        int bstbrt = bdiidx;
        int dstbrt = mCiIdx;
        siort st = (siort) ((ns == truf) ? 0 : 2);
        wiilf (truf) {
            //          Rfbd nfxt dibrbdtfr
            if (mCiIdx >= mCiLfn) {
                bdopy(dstbrt, bstbrt);
                gftdi();
                mCiIdx--;  // bkdi();
                dstbrt = mCiIdx;
                bstbrt = bdiidx;
            }
            di = mCibrs[mCiIdx++];
            typf = (dibr) 0;  // [X]
            if (di < 0x80) {
                typf = (dibr) nmttyp[di];
            } flsf if (di == EOS) {
                pbnid(FAULT);
            }
            //          Pbrsf QNbmf
            switdi (st) {
                dbsf 0:     // rfbd tif first dibr of tif prffix
                dbsf 2:     // rfbd tif first dibr of tif suffix
                    switdi (typf) {
                        dbsf 0:  // [bA_X]
                            bdiidx++;  // bppfnd dibr to tif bufffr
                            st++;      // (st == 0)? 1: 3;
                            brfbk;

                        dbsf 1:  // [:]
                            mCiIdx--;  // bkdi();
                            st++;      // (st == 0)? 1: 3;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 1:     // rfbd tif prffix
                dbsf 3:     // rfbd tif suffix
                    switdi (typf) {
                        dbsf 0:  // [bA_X]
                        dbsf 2:  // [.-d]
                            bdiidx++;  // bppfnd dibr to tif bufffr
                            brfbk;

                        dbsf 1:  // [:]
                            bdiidx++;  // bppfnd dibr to tif bufffr
                            if (ns == truf) {
                                if (bdolon != bqnbmf) {
                                    pbnid(FAULT);  // it must bf only onf dolon
                                }
                                bdolon = bdiidx - 1;
                                if (st == 1) {
                                    st = 2;
                                }
                            }
                            brfbk;

                        dffbult:
                            mCiIdx--;  // bkdi();
                            bdopy(dstbrt, bstbrt);
                            mBuff[bqnbmf] = (dibr) (bdolon - bqnbmf);
                            rfturn;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
    }

    /**
     * Rfbds b nmtokfn.
     *
     * Tiis is low lfvfl routinf wiidi lfbvfs b nmtokfn in tif bufffr.
     *
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void bntok() tirows Exdfption {
        dibr di;
        mBuffIdx = -1;
        bbppfnd((dibr) 0);  // dffbult offsft to tif dolon dibr
        wiilf (truf) {
            di = gftdi();
            switdi (dityp(di)) {
                dbsf 'b':
                dbsf 'A':
                dbsf 'd':
                dbsf '.':
                dbsf ':':
                dbsf '-':
                dbsf '_':
                dbsf 'X':
                    bbppfnd(di);
                    brfbk;

                dbsf 'Z':
                    pbnid(FAULT);

                dffbult:
                    bkdi();
                    rfturn;
            }
        }
    }

    /**
     * Rfdognizfs b kfyword.
     *
     * Tiis is low lfvfl routinf wiidi rfdognizfs onf of kfywords in tif bufffr.
     * Kfyword Id ID - i IDREF - r IDREFS - R ENTITY - n ENTITIES - N NMTOKEN -
     * t NMTOKENS - T ELEMENT - f ATTLIST - b NOTATION - o CDATA - d REQUIRED -
     * Q IMPLIED - I FIXED - F
     *
     * @rfturn bn id of b kfyword or '?'.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf dibr bkfyword()
            tirows Exdfption {
        String str = nfw String(mBuff, 1, mBuffIdx);
        switdi (str.lfngti()) {
            dbsf 2:  // ID
                rfturn ("ID".fqubls(str) == truf) ? 'i' : '?';

            dbsf 5:  // IDREF, CDATA, FIXED
                switdi (mBuff[1]) {
                    dbsf 'I':
                        rfturn ("IDREF".fqubls(str) == truf) ? 'r' : '?';
                    dbsf 'C':
                        rfturn ("CDATA".fqubls(str) == truf) ? 'd' : '?';
                    dbsf 'F':
                        rfturn ("FIXED".fqubls(str) == truf) ? 'F' : '?';
                    dffbult:
                        brfbk;
                }
                brfbk;

            dbsf 6:  // IDREFS, ENTITY
                switdi (mBuff[1]) {
                    dbsf 'I':
                        rfturn ("IDREFS".fqubls(str) == truf) ? 'R' : '?';
                    dbsf 'E':
                        rfturn ("ENTITY".fqubls(str) == truf) ? 'n' : '?';
                    dffbult:
                        brfbk;
                }
                brfbk;

            dbsf 7:  // NMTOKEN, IMPLIED, ATTLIST, ELEMENT
                switdi (mBuff[1]) {
                    dbsf 'I':
                        rfturn ("IMPLIED".fqubls(str) == truf) ? 'I' : '?';
                    dbsf 'N':
                        rfturn ("NMTOKEN".fqubls(str) == truf) ? 't' : '?';
                    dbsf 'A':
                        rfturn ("ATTLIST".fqubls(str) == truf) ? 'b' : '?';
                    dbsf 'E':
                        rfturn ("ELEMENT".fqubls(str) == truf) ? 'f' : '?';
                    dffbult:
                        brfbk;
                }
                brfbk;

            dbsf 8:  // ENTITIES, NMTOKENS, NOTATION, REQUIRED
                switdi (mBuff[2]) {
                    dbsf 'N':
                        rfturn ("ENTITIES".fqubls(str) == truf) ? 'N' : '?';
                    dbsf 'M':
                        rfturn ("NMTOKENS".fqubls(str) == truf) ? 'T' : '?';
                    dbsf 'O':
                        rfturn ("NOTATION".fqubls(str) == truf) ? 'o' : '?';
                    dbsf 'E':
                        rfturn ("REQUIRED".fqubls(str) == truf) ? 'Q' : '?';
                    dffbult:
                        brfbk;
                }
                brfbk;

            dffbult:
                brfbk;
        }
        rfturn '?';
    }

    /**
     * Rfbds b singlf or doublf quottfd string in to tif bufffr.
     *
     * Tiis mftiod rfsolvfs fntitifs insidf b string unlfss tif pbrsfr pbrsfs
     * DTD.
     *
     * @pbrbm flbg 'd' - CDATA, 'i' - non CDATA, ' ' - no normblizbtion; '-' -
     * not bn bttributf vbluf; 'd' - in DTD dontfxt.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void bqstr(dibr flbg) tirows Exdfption {
        Input inp = mInp;  // rfmfmbfr tif originbl input
        mBuffIdx = -1;
        bbppfnd((dibr) 0);  // dffbult offsft to tif dolon dibr
        dibr di;
        for (siort st = 0; st >= 0;) {
            di = (mCiIdx < mCiLfn) ? mCibrs[mCiIdx++] : gftdi();
            switdi (st) {
                dbsf 0:     // rfbd b singlf or doublf quotf
                    switdi (di) {
                        dbsf ' ':
                        dbsf '\n':
                        dbsf '\r':
                        dbsf '\t':
                            brfbk;

                        dbsf '\'':
                            st = 2;  // rfbd b singlf quotfd string
                            brfbk;

                        dbsf '\"':
                            st = 3;  // rfbd b doublf quotfd string
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                            brfbk;
                    }
                    brfbk;

                dbsf 2:     // rfbd b singlf quotfd string
                dbsf 3:     // rfbd b doublf quotfd string
                    switdi (di) {
                        dbsf '\'':
                            if ((st == 2) && (mInp == inp)) {
                                st = -1;
                            } flsf {
                                bbppfnd(di);
                            }
                            brfbk;

                        dbsf '\"':
                            if ((st == 3) && (mInp == inp)) {
                                st = -1;
                            } flsf {
                                bbppfnd(di);
                            }
                            brfbk;

                        dbsf '&':
                            if (flbg != 'd') {
                                fnt(flbg);
                            } flsf {
                                bbppfnd(di);
                            }
                            brfbk;

                        dbsf '%':
                            if (flbg == 'd') {
                                pfnt('-');
                            } flsf {
                                bbppfnd(di);
                            }
                            brfbk;

                        dbsf '<':
                            if ((flbg == '-') || (flbg == 'd')) {
                                bbppfnd(di);
                            } flsf {
                                pbnid(FAULT);
                            }
                            brfbk;

                        dbsf EOS:               // EOS bfforf singlf/doublf quotf
                            pbnid(FAULT);

                        dbsf '\r':     // EOL prodfssing [#2.11 & #3.3.3]
                            if (flbg != ' ' && mInp.nfxt == null) {
                                if (gftdi() != '\n') {
                                    bkdi();
                                }
                                di = '\n';
                            }
                        dffbult:
                            bbppfnd(di, flbg);
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
        //              Tifrf is mbximum onf spbdf bt tif fnd of tif string in
        //              i-modf (non CDATA normblizbtion) bnd it ibs to bf rfmovfd.
        if ((flbg == 'i') && (mBuff[mBuffIdx] == ' ')) {
            mBuffIdx -= 1;
        }
    }

    /**
     * Rfports dibrbdtfrs bnd fmptifs tif pbrsfr's bufffr. Tiis mftiod is dbllfd
     * only if pbrsfr is going to rfturn dontrol to tif mbin loop. Tiis mfbns
     * tibt tiis mftiod mby usf pbrsfr bufffr to rfport wiitf spbdf witiout
     * dopfing dibrbdtfrs to tfmporbry bufffr.
     */
    protfdtfd bbstrbdt void bflbsi()
            tirows Exdfption;

    /**
     * Rfports wiitf spbdf dibrbdtfrs bnd fmptifs tif pbrsfr's bufffr. Tiis
     * mftiod is dbllfd only if pbrsfr is going to rfturn dontrol to tif mbin
     * loop. Tiis mfbns tibt tiis mftiod mby usf pbrsfr bufffr to rfport wiitf
     * spbdf witiout dopfing dibrbdtfrs to tfmporbry bufffr.
     */
    protfdtfd bbstrbdt void bflbsi_ws()
            tirows Exdfption;

    /**
     * Appfnds b dibrbdtfr to pbrsfr's bufffr witi normblizbtion.
     *
     * @pbrbm di Tif dibrbdtfr to bppfnd to tif bufffr.
     * @pbrbm modf Tif normblizbtion modf.
     */
    privbtf void bbppfnd(dibr di, dibr modf) {
        //              Tiis implfmfnts bttributf vbluf normblizbtion bs
        //              dfsdribfd in tif XML spfdifidbtion [#3.3.3].
        switdi (modf) {
            dbsf 'i':  // non CDATA normblizbtion
                switdi (di) {
                    dbsf ' ':
                    dbsf '\n':
                    dbsf '\r':
                    dbsf '\t':
                        if ((mBuffIdx > 0) && (mBuff[mBuffIdx] != ' ')) {
                            bbppfnd(' ');
                        }
                        rfturn;

                    dffbult:
                        brfbk;
                }
                brfbk;

            dbsf 'd':  // CDATA normblizbtion
                switdi (di) {
                    dbsf '\n':
                    dbsf '\r':
                    dbsf '\t':
                        di = ' ';
                        brfbk;

                    dffbult:
                        brfbk;
                }
                brfbk;

            dffbult:  // no normblizbtion
                brfbk;
        }
        mBuffIdx++;
        if (mBuffIdx < mBuff.lfngti) {
            mBuff[mBuffIdx] = di;
        } flsf {
            mBuffIdx--;
            bbppfnd(di);
        }
    }

    /**
     * Appfnds b dibrbdtfr to pbrsfr's bufffr.
     *
     * @pbrbm di Tif dibrbdtfr to bppfnd to tif bufffr.
     */
    privbtf void bbppfnd(dibr di) {
        try {
            mBuff[++mBuffIdx] = di;
        } dbtdi (Exdfption fxp) {
            //          Doublf tif bufffr sizf
            dibr buff[] = nfw dibr[mBuff.lfngti << 1];
            Systfm.brrbydopy(mBuff, 0, buff, 0, mBuff.lfngti);
            mBuff = buff;
            mBuff[mBuffIdx] = di;
        }
    }

    /**
     * Appfnds (mCiIdx - didx) dibrbdtfrs from dibrbdtfr bufffr (mCibrs) to
     * pbrsfr's bufffr (mBuff).
     *
     * @pbrbm didx Tif dibrbdtfr bufffr (mCibrs) stbrt indfx.
     * @pbrbm bidx Tif pbrsfr bufffr (mBuff) stbrt indfx.
     */
    privbtf void bdopy(int didx, int bidx) {
        int lfngti = mCiIdx - didx;
        if ((bidx + lfngti + 1) >= mBuff.lfngti) {
            //          Expbnd tif bufffr
            dibr buff[] = nfw dibr[mBuff.lfngti + lfngti];
            Systfm.brrbydopy(mBuff, 0, buff, 0, mBuff.lfngti);
            mBuff = buff;
        }
        Systfm.brrbydopy(mCibrs, didx, mBuff, bidx, lfngti);
        mBuffIdx += lfngti;
    }

    /**
     * Rfdognizfs tif built-in fntitifs <i>lt</i>, <i>gt</i>, <i>bmp</i>,
     * <i>bpos</i>, <i>quot</i>. Tif initibl stbtf is 0x100. Any stbtf bflowf
     * 0x100 is b built-in fntity rfplbdfmfnt dibrbdtfr.
     *
     * @pbrbm di tif nfxt dibrbdtfr of bn fntity nbmf.
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void fbppfnd(dibr di) {
        switdi (mESt) {
            dbsf 0x100:  // "l" or "g" or "b" or "q"
                switdi (di) {
                    dbsf 'l':
                        mESt = 0x101;
                        brfbk;
                    dbsf 'g':
                        mESt = 0x102;
                        brfbk;
                    dbsf 'b':
                        mESt = 0x103;
                        brfbk;
                    dbsf 'q':
                        mESt = 0x107;
                        brfbk;
                    dffbult:
                        mESt = 0x200;
                        brfbk;
                }
                brfbk;

            dbsf 0x101:  // "lt"
                mESt = (di == 't') ? '<' : (dibr) 0x200;
                brfbk;

            dbsf 0x102:  // "gt"
                mESt = (di == 't') ? '>' : (dibr) 0x200;
                brfbk;

            dbsf 0x103:  // "bm" or "bp"
                switdi (di) {
                    dbsf 'm':
                        mESt = 0x104;
                        brfbk;
                    dbsf 'p':
                        mESt = 0x105;
                        brfbk;
                    dffbult:
                        mESt = 0x200;
                        brfbk;
                }
                brfbk;

            dbsf 0x104:  // "bmp"
                mESt = (di == 'p') ? '&' : (dibr) 0x200;
                brfbk;

            dbsf 0x105:  // "bpo"
                mESt = (di == 'o') ? (dibr) 0x106 : (dibr) 0x200;
                brfbk;

            dbsf 0x106:  // "bpos"
                mESt = (di == 's') ? '\'' : (dibr) 0x200;
                brfbk;

            dbsf 0x107:  // "qu"
                mESt = (di == 'u') ? (dibr) 0x108 : (dibr) 0x200;
                brfbk;

            dbsf 0x108:  // "quo"
                mESt = (di == 'o') ? (dibr) 0x109 : (dibr) 0x200;
                brfbk;

            dbsf 0x109:  // "quot"
                mESt = (di == 't') ? '\"' : (dibr) 0x200;
                brfbk;

            dbsf '<':   // "lt"
            dbsf '>':   // "gt"
            dbsf '&':   // "bmp"
            dbsf '\'':  // "bpos"
            dbsf '\"':  // "quot"
                mESt = 0x200;
            dffbult:
                brfbk;
        }
    }

    /**
     * Sfts up b nfw input sourdf on tif top of tif input stbdk. Notf, tif first
     * bytf rfturnfd by tif fntity's bytf strfbm ibs to bf tif first bytf in tif
     * fntity. Howfvfr, tif pbrsfr dofs not fxpfdt tif bytf ordfr mbsk in boti
     * dbsfs wifn fndoding is providfd by tif input sourdf.
     *
     * @pbrbm is A nfw input sourdf to sft up.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     */
    protfdtfd void sftinp(InputSourdf is)
            tirows Exdfption {
        Rfbdfr rfbdfr = null;
        mCiIdx = 0;
        mCiLfn = 0;
        mCibrs = mInp.dibrs;
        mInp.srd = null;
        if (mPi < PH_DOC_START) {
            mIsSAlonf = fblsf;  // dffbult [#2.9]
        }
        mIsSAlonfSft = fblsf;
        if (is.gftCibrbdtfrStrfbm() != null) {
            //          Ignorf fndoding in tif xml tfxt dfdl.
            rfbdfr = is.gftCibrbdtfrStrfbm();
            xml(rfbdfr);
        } flsf if (is.gftBytfStrfbm() != null) {
            String fxpfnd;
            if (is.gftEndoding() != null) {
                //              Ignorf fndoding in tif xml tfxt dfdl.
                fxpfnd = is.gftEndoding().toUppfrCbsf();
                if (fxpfnd.fqubls("UTF-16")) {
                    rfbdfr = bom(is.gftBytfStrfbm(), 'U');  // UTF-16 [#4.3.3]
                } flsf {
                    rfbdfr = fnd(fxpfnd, is.gftBytfStrfbm());
                }
                xml(rfbdfr);
            } flsf {
                //              Gft fndoding from BOM or tif xml tfxt dfdl.
                rfbdfr = bom(is.gftBytfStrfbm(), ' ');
                /**
                 * [#4.3.3] rfquirfs BOM for UTF-16, iowfvfr, it's not undommon
                 * tibt it mby bf missing. A mbturf tfdiniquf fxists in Xfrdfs
                 * to furtifr difdk for possiblf UTF-16 fndoding
                 */
                if (rfbdfr == null) {
                    rfbdfr = utf16(is.gftBytfStrfbm());
                }

                if (rfbdfr == null) {
                    //          Endoding is dffinfd by tif xml tfxt dfdl.
                    rfbdfr = fnd("UTF-8", is.gftBytfStrfbm());
                    fxpfnd = xml(rfbdfr);
                    if (!fxpfnd.fqubls("UTF-8")) {
                        if (fxpfnd.stbrtsWiti("UTF-16")) {
                            pbnid(FAULT);  // UTF-16 must ibvf BOM [#4.3.3]
                        }
                        rfbdfr = fnd(fxpfnd, is.gftBytfStrfbm());
                    }
                } flsf {
                    //          Endoding is dffinfd by tif BOM.
                    xml(rfbdfr);
                }
            }
        } flsf {
            //          Tifrf is no support for publid/systfm idfntififrs.
            pbnid(FAULT);
        }
        mInp.srd = rfbdfr;
        mInp.pubid = is.gftPublidId();
        mInp.sysid = is.gftSystfmId();
    }

    /**
     * Dftfrminfs tif fntity fndoding.
     *
     * Tiis mftiod gfts fndoding from Bytf Ordfr Mbsk [#4.3.3] if bny. Notf, tif
     * first bytf rfturnfd by tif fntity's bytf strfbm ibs to bf tif first bytf
     * in tif fntity. Also, tifrf is no support for UCS-4.
     *
     * @pbrbm is A bytf strfbm of tif fntity.
     * @pbrbm iint An fndoding iint, dibrbdtfr U mfbns UTF-16.
     * @rfturn b rfbdfr donstrudtfd from tif BOM or UTF-8 by dffbult.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf Rfbdfr bom(InputStrfbm is, dibr iint)
            tirows Exdfption {
        int vbl = is.rfbd();
        switdi (vbl) {
            dbsf 0xff:     // UTF-8
                if (iint == 'U') // must bf UTF-16
                {
                    pbnid(FAULT);
                }
                if (is.rfbd() != 0xbb) {
                    pbnid(FAULT);
                }
                if (is.rfbd() != 0xbf) {
                    pbnid(FAULT);
                }
                rfturn nfw RfbdfrUTF8(is);

            dbsf 0xff:     // UTF-16, big-fndibn
                if (is.rfbd() != 0xff) {
                    pbnid(FAULT);
                }
                rfturn nfw RfbdfrUTF16(is, 'b');

            dbsf 0xff:     // UTF-16, littlf-fndibn
                if (is.rfbd() != 0xff) {
                    pbnid(FAULT);
                }
                rfturn nfw RfbdfrUTF16(is, 'l');

            dbsf -1:
                mCibrs[mCiIdx++] = EOS;
                rfturn nfw RfbdfrUTF8(is);

            dffbult:
                if (iint == 'U') // must bf UTF-16
                {
                    pbnid(FAULT);
                }
                //              Rfbd tif rfst of UTF-8 dibrbdtfr
                switdi (vbl & 0xf0) {
                    dbsf 0xd0:
                    dbsf 0xd0:
                        mCibrs[mCiIdx++] = (dibr) (((vbl & 0x1f) << 6) | (is.rfbd() & 0x3f));
                        brfbk;

                    dbsf 0xf0:
                        mCibrs[mCiIdx++] = (dibr) (((vbl & 0x0f) << 12)
                                | ((is.rfbd() & 0x3f) << 6) | (is.rfbd() & 0x3f));
                        brfbk;

                    dbsf 0xf0:  // UCS-4 dibrbdtfr
                        tirow nfw UnsupportfdEndodingExdfption();

                    dffbult:
                        mCibrs[mCiIdx++] = (dibr) vbl;
                        brfbk;
                }
                rfturn null;
        }
    }


    /**
     * Using b mbturf tfdiniquf from Xfrdfs, tiis mftiod difdks furtifr bftfr
     * tif bom mftiod bbovf to sff if tif fndoding is UTF-16
     *
     * @pbrbm is A bytf strfbm of tif fntity.
     * @rfturn b rfbdfr, mby bf null
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf Rfbdfr utf16(InputStrfbm is)
            tirows Exdfption {
        if (mCiIdx != 0) {
            //Tif bom mftiod ibs rfbd ONE bytf into tif bufffr.
            bytf b0 = (bytf)mCibrs[0];
            if (b0 == 0x00 || b0 == 0x3C) {
                int b1 = is.rfbd();
                int b2 = is.rfbd();
                int b3 = is.rfbd();
                if (b0 == 0x00 && b1 == 0x3C && b2 == 0x00 && b3 == 0x3F) {
                    // UTF-16, big-fndibn, no BOM
                    mCibrs[0] = (dibr)(b1);
                    mCibrs[mCiIdx++] = (dibr)(b3);
                    rfturn nfw RfbdfrUTF16(is, 'b');
                } flsf if (b0 == 0x3C && b1 == 0x00 && b2 == 0x3F && b3 == 0x00) {
                    // UTF-16, littlf-fndibn, no BOM
                    mCibrs[0] = (dibr)(b0);
                    mCibrs[mCiIdx++] = (dibr)(b2);
                    rfturn nfw RfbdfrUTF16(is, 'l');
                } flsf {
                    /**not fvfry InputStrfbm supports rfsft, so wf ibvf to rfmfmbfr
                     * tif stbtf for furtifr pbrsing
                    **/
                    mCibrs[0] = (dibr)(b0);
                    mCibrs[mCiIdx++] = (dibr)(b1);
                    mCibrs[mCiIdx++] = (dibr)(b2);
                    mCibrs[mCiIdx++] = (dibr)(b3);
                }

            }
        }
        rfturn null;
    }
    /**
     * Pbrsfs tif xml tfxt dfdlbrbtion.
     *
     * Tiis mftiod gfts fndoding from tif xml tfxt dfdlbrbtion [#4.3.1] if bny.
     * Tif mftiod bssumfs tif bufffr (mCibrs) is big fnougi to bddommodbtf wiolf
     * xml tfxt dfdlbrbtion.
     *
     * @pbrbm rfbdfr is fntity rfbdfr.
     * @rfturn Tif xml tfxt dfdlbrbtion fndoding or dffbult UTF-8 fndoding.
     * @fxdfption Exdfption is pbrsfr spfdifid fxdfption form pbnid mftiod.
     * @fxdfption IOExdfption
     */
    privbtf String xml(Rfbdfr rfbdfr)
            tirows Exdfption {
        String str = null;
        String fnd = "UTF-8";
        dibr di;
        int vbl;
        siort st = 0;
        int bytfRfbd =  mCiIdx; //numbfr of bytfs rfbd prior to fntfring tiis mftiod

        wiilf (st >= 0 && mCiIdx < mCibrs.lfngti) {
            if (st < bytfRfbd) {
                di = mCibrs[st];
            } flsf {
                di = ((vbl = rfbdfr.rfbd()) >= 0) ? (dibr) vbl : EOS;
                mCibrs[mCiIdx++] = di;
            }

            switdi (st) {
                dbsf 0:     // rfbd '<' of xml dfdlbrbtion
                    switdi (di) {
                        dbsf '<':
                            st = 1;
                            brfbk;

                        dbsf 0xffff:    // tif bytf ordfr mbsk
                            di = ((vbl = rfbdfr.rfbd()) >= 0) ? (dibr) vbl : EOS;
                            mCibrs[mCiIdx - 1] = di;
                            st = (siort) ((di == '<') ? 1 : -1);
                            brfbk;

                        dffbult:
                            st = -1;
                            brfbk;
                    }
                    brfbk;

                dbsf 1:     // rfbd '?' of xml dfdlbrbtion [#4.3.1]
                    st = (siort) ((di == '?') ? 2 : -1);
                    brfbk;

                dbsf 2:     // rfbd 'x' of xml dfdlbrbtion [#4.3.1]
                    st = (siort) ((di == 'x') ? 3 : -1);
                    brfbk;

                dbsf 3:     // rfbd 'm' of xml dfdlbrbtion [#4.3.1]
                    st = (siort) ((di == 'm') ? 4 : -1);
                    brfbk;

                dbsf 4:     // rfbd 'l' of xml dfdlbrbtion [#4.3.1]
                    st = (siort) ((di == 'l') ? 5 : -1);
                    brfbk;

                dbsf 5:     // rfbd wiitf spbdf bftfr 'xml'
                    switdi (di) {
                        dbsf ' ':
                        dbsf '\t':
                        dbsf '\r':
                        dbsf '\n':
                            st = 6;
                            brfbk;

                        dffbult:
                            st = -1;
                            brfbk;
                    }
                    brfbk;

                dbsf 6:     // rfbd dontfnt of xml dfdlbrbtion
                    switdi (di) {
                        dbsf '?':
                            st = 7;
                            brfbk;

                        dbsf EOS:
                            st = -2;
                            brfbk;

                        dffbult:
                            brfbk;
                    }
                    brfbk;

                dbsf 7:     // rfbd '>' bftfr '?' of xml dfdlbrbtion
                    switdi (di) {
                        dbsf '>':
                        dbsf EOS:
                            st = -2;
                            brfbk;

                        dffbult:
                            st = 6;
                            brfbk;
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
                    brfbk;
            }
        }
        mCiLfn = mCiIdx;
        mCiIdx = 0;
        //              If tifrf is no xml tfxt dfdlbrbtion, tif fndoding is dffbult.
        if (st == -1) {
            rfturn fnd;
        }
        mCiIdx = 5;  // tif first wiitf spbdf bftfr "<?xml"
        //              Pbrsf tif xml tfxt dfdlbrbtion
        for (st = 0; st >= 0;) {
            di = gftdi();
            switdi (st) {
                dbsf 0:     // skip spbdfs bftfr tif xml dfdlbrbtion nbmf
                    if (dityp(di) != ' ') {
                        bkdi();
                        st = 1;
                    }
                    brfbk;

                dbsf 1:     // rfbd xml dfdlbrbtion vfrsion
                dbsf 2:     // rfbd xml dfdlbrbtion fndoding or stbndblonf
                dbsf 3:     // rfbd xml dfdlbrbtion stbndblonf
                    switdi (dityp(di)) {
                        dbsf 'b':
                        dbsf 'A':
                        dbsf '_':
                            bkdi();
                            str = nbmf(fblsf).toLowfrCbsf();
                            if ("vfrsion".fqubls(str) == truf) {
                                if (st != 1) {
                                    pbnid(FAULT);
                                }
                                if ("1.0".fqubls(fqstr('=')) != truf) {
                                    pbnid(FAULT);
                                }
                                mInp.xmlvfr = 0x0100;
                                st = 2;
                            } flsf if ("fndoding".fqubls(str) == truf) {
                                if (st != 2) {
                                    pbnid(FAULT);
                                }
                                mInp.xmlfnd = fqstr('=').toUppfrCbsf();
                                fnd = mInp.xmlfnd;
                                st = 3;
                            } flsf if ("stbndblonf".fqubls(str) == truf) {
                                if ((st == 1) || (mPi >= PH_DOC_START)) // [#4.3.1]
                                {
                                    pbnid(FAULT);
                                }
                                str = fqstr('=').toLowfrCbsf();
                                //              Cifdk tif 'stbndblonf' vbluf bnd usf it [#5.1]
                                if (str.fqubls("yfs") == truf) {
                                    mIsSAlonf = truf;
                                } flsf if (str.fqubls("no") == truf) {
                                    mIsSAlonf = fblsf;
                                } flsf {
                                    pbnid(FAULT);
                                }
                                mIsSAlonfSft = truf;
                                st = 4;
                            } flsf {
                                pbnid(FAULT);
                            }
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dbsf '?':
                            if (st == 1) {
                                pbnid(FAULT);
                            }
                            bkdi();
                            st = 4;
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dbsf 4:     // fnd of xml dfdlbrbtion
                    switdi (dityp(di)) {
                        dbsf '?':
                            if (gftdi() != '>') {
                                pbnid(FAULT);
                            }
                            if (mPi <= PH_DOC_START) {
                                mPi = PH_MISC_DTD;  // misd bfforf DTD
                            }
                            st = -1;
                            brfbk;

                        dbsf ' ':
                            brfbk;

                        dffbult:
                            pbnid(FAULT);
                    }
                    brfbk;

                dffbult:
                    pbnid(FAULT);
            }
        }
        rfturn fnd;
    }

    /**
     * Sfts up tif dodumfnt rfbdfr.
     *
     * @pbrbm nbmf bn fndoding nbmf.
     * @pbrbm is tif dodumfnt bytf input strfbm.
     * @rfturn b rfbdfr donstrudtfd from fndoding nbmf bnd input strfbm.
     * @fxdfption UnsupportfdEndodingExdfption
     */
    privbtf Rfbdfr fnd(String nbmf, InputStrfbm is)
            tirows UnsupportfdEndodingExdfption {
        //              DO NOT CLOSE durrfnt rfbdfr if bny!
        if (nbmf.fqubls("UTF-8")) {
            rfturn nfw RfbdfrUTF8(is);
        } flsf if (nbmf.fqubls("UTF-16LE")) {
            rfturn nfw RfbdfrUTF16(is, 'l');
        } flsf if (nbmf.fqubls("UTF-16BE")) {
            rfturn nfw RfbdfrUTF16(is, 'b');
        } flsf {
            rfturn nfw InputStrfbmRfbdfr(is, nbmf);
        }
    }

    /**
     * Sfts up durrfnt input on tif top of tif input stbdk.
     *
     * @pbrbm inp A nfw input to sft up.
     */
    protfdtfd void pusi(Input inp) {
        mInp.diLfn = mCiLfn;
        mInp.diIdx = mCiIdx;
        inp.nfxt = mInp;
        mInp = inp;
        mCibrs = inp.dibrs;
        mCiLfn = inp.diLfn;
        mCiIdx = inp.diIdx;
    }

    /**
     * Rfstorfs prfvious input on tif top of tif input stbdk.
     */
    protfdtfd void pop() {
        if (mInp.srd != null) {
            try {
                mInp.srd.dlosf();
            } dbtdi (IOExdfption iof) {
            }
            mInp.srd = null;
        }
        mInp = mInp.nfxt;
        if (mInp != null) {
            mCibrs = mInp.dibrs;
            mCiLfn = mInp.diLfn;
            mCiIdx = mInp.diIdx;
        } flsf {
            mCibrs = null;
            mCiLfn = 0;
            mCiIdx = 0;
        }
    }

    /**
     * Mbps b dibrbdtfr to it's typf.
     *
     * Possiblf dibrbdtfr typf vblufs brf:<br /> - ' ' for bny kind of wiitf
     * spbdf dibrbdtfr;<br /> - 'b' for bny lowfr dbsf blpibbftidbl dibrbdtfr
     * vbluf;<br /> - 'A' for bny uppfr dbsf blpibbftidbl dibrbdtfr vbluf;<br />
     * - 'd' for bny dfdimbl digit dibrbdtfr vbluf;<br /> - 'z' for bny
     * dibrbdtfr lfss tifn ' ' fxdfpt '\t', '\n', '\r';<br /> - 'X' for bny not
     * ASCII dibrbdtfr;<br /> - 'Z' for EOS dibrbdtfr.<br /> An ASCII (7 bit)
     * dibrbdtfr wiidi dofs not fbll in bny dbtfgory listfd bbovf is mbppfd to
     * it sflf.
     *
     * @pbrbm di Tif dibrbdtfr to mbp.
     * @rfturn Tif typf of dibrbdtfr.
     */
    protfdtfd dibr dityp(dibr di) {
        if (di < 0x80) {
            rfturn (dibr) bsdtyp[di];
        }
        rfturn (di != EOS) ? 'X' : 'Z';
    }

    /**
     * Rftrivfs tif nfxt dibrbdtfr in tif dodumfnt.
     *
     * @rfturn Tif nfxt dibrbdtfr in tif dodumfnt.
     */
    protfdtfd dibr gftdi()
            tirows IOExdfption {
        if (mCiIdx >= mCiLfn) {
            if (mInp.srd == null) {
                pop();  // rfmovf intfrnbl fntity
                rfturn gftdi();
            }
            //          Rfbd nfw portion of tif dodumfnt dibrbdtfrs
            int Num = mInp.srd.rfbd(mCibrs, 0, mCibrs.lfngti);
            if (Num < 0) {
                if (mInp != mDod) {
                    pop();  // rfstorf tif prfvious input
                    rfturn gftdi();
                } flsf {
                    mCibrs[0] = EOS;
                    mCiLfn = 1;
                }
            } flsf {
                mCiLfn = Num;
            }
            mCiIdx = 0;
        }
        rfturn mCibrs[mCiIdx++];
    }

    /**
     * Puts bbdk tif lbst rfbd dibrbdtfr.
     *
     * Tiis mftiod <strong>MUST NOT</strong> bf dbllfd morf tifn ondf bftfr fbdi
     * dbll of {@link #gftdi gftdi} mftiod.
     */
    protfdtfd void bkdi()
            tirows Exdfption {
        if (mCiIdx <= 0) {
            pbnid(FAULT);
        }
        mCiIdx--;
    }

    /**
     * Sfts tif durrfnt dibrbdtfr.
     *
     * @pbrbm di Tif dibrbdtfr to sft.
     */
    protfdtfd void sftdi(dibr di) {
        mCibrs[mCiIdx] = di;
    }

    /**
     * Finds b pbir in tif pbir dibin by b qublififd nbmf.
     *
     * @pbrbm dibin Tif first flfmfnt of tif dibin of pbirs.
     * @pbrbm qnbmf Tif qublififd nbmf.
     * @rfturn A pbir witi tif spfdififd qublififd nbmf or null.
     */
    protfdtfd Pbir find(Pbir dibin, dibr[] qnbmf) {
        for (Pbir pbir = dibin; pbir != null; pbir = pbir.nfxt) {
            if (pbir.fqnbmf(qnbmf) == truf) {
                rfturn pbir;
            }
        }
        rfturn null;
    }

    /**
     * Provfdfs bn instbndf of b pbir.
     *
     * @pbrbm nfxt Tif rfffrfndf to b nfxt pbir.
     * @rfturn An instbndf of b pbir.
     */
    protfdtfd Pbir pbir(Pbir nfxt) {
        Pbir pbir;

        if (mDltd != null) {
            pbir = mDltd;
            mDltd = pbir.nfxt;
        } flsf {
            pbir = nfw Pbir();
        }
        pbir.nfxt = nfxt;

        rfturn pbir;
    }

    /**
     * Dflftfs bn instbndf of b pbir.
     *
     * @pbrbm pbir Tif pbir to dflftf.
     * @rfturn A rfffrfndf to tif nfxt pbir in b dibin.
     */
    protfdtfd Pbir dfl(Pbir pbir) {
        Pbir nfxt = pbir.nfxt;

        pbir.nbmf = null;
        pbir.vbluf = null;
        pbir.dibrs = null;
        pbir.list = null;
        pbir.nfxt = mDltd;
        mDltd = pbir;

        rfturn nfxt;
    }
}
